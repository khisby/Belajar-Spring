package id.khisoft.javaspring.Repository;

import id.khisoft.javaspring.Entity.Invoice_Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InvoiceDetailRepository")
public class InvoiceDetailRepositoryImpl implements InvoiceDetailRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Invoice_Details> getInvoiceDetail(int invoiceId) {
        return jdbcTemplate.query(
                "SELECT * FROM public.invoice_details where Invoice_id = "+invoiceId,
                (rs, rowNum)->
                new Invoice_Details(
                        rs.getLong("detail_id"),
                        rs.getInt("Invoice_id"),
                        rs.getString("product_name"),
                        rs.getLong("qty"),
                        rs.getBigDecimal("total_price")
                ));
    }
}
