package id.khisoft.javaspring.Repository;

import id.khisoft.javaspring.Entity.Invoice_Details;
import id.khisoft.javaspring.Entity.Invoice_Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("invoiceRepository")
public class InvoiceRepositoryImpl implements InvoiceRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("InvoiceDetailRepository")
    private InvoiceDetailRepositoryImpl invoiceDetailRepository;

    @Override
    public Invoice_Header getInvoice(int id) {
        Invoice_Header ih = jdbcTemplate.queryForObject(
                "select * from public.invoice_header where invoice_id =" + id,
                (rs, rowNum)->
                        new Invoice_Header(
                                rs.getLong("invoice_id"),
                                rs.getString("invoice_to"),
                                rs.getString("email"),
                                rs.getBigDecimal("subtotal"),
                                rs.getBigDecimal("discount"),
                                rs.getBigDecimal("total")
                        )
        );

        ih.setInvoice_details(invoiceDetailRepository.getInvoiceDetail(id));
        return ih;
    }

    @Override
    @Transactional
    public int save(Invoice_Header invoice_header) {
        String sql = "INSERT INTO public.invoice_header(Invoice_to, email, subtotal, discount, total) VALUES(?,?,?,?,?)";
        String sql_invoice_detail = "INSERT INTO public.invoice_details (invoice_id, product_name, qty, total_price) VALUES (?,?,?,?)";

        KeyHolder kh = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,new String[]{"invoice_id"});
            ps.setString(1, invoice_header.getInvoice_to());
            ps.setString(2,invoice_header.getEmail());
            ps.setBigDecimal(3,invoice_header.getSubtotal());
            ps.setBigDecimal(4,invoice_header.getDiscount());
            ps.setBigDecimal(5,invoice_header.getTotal());
            return ps;
        },kh);

        List<Invoice_Details> id = invoice_header.getInvoice_details();
        id.forEach(invoice_details -> {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql_invoice_detail, new String[]{"detail_id"});
                ps.setInt(1, (Integer) kh.getKey());
                ps.setString(2, invoice_details.getProduct_name());
                ps.setLong(3, invoice_details.getQty());
                ps.setBigDecimal(4, invoice_details.getTotal_price());
                return ps;
            });
        });

        return result;
    }
}
