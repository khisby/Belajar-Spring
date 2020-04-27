package id.khisoft.javaspring.Repository;

import id.khisoft.javaspring.Entity.Invoice_Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
                "select * from public.invoice_header where invoice_id=" + id,
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
    public int save(Invoice_Header invoice_header) {
        return 0;
    }
}
