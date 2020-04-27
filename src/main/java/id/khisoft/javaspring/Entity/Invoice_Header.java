package id.khisoft.javaspring.Entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Invoice_Header {
    private Long invoice_id;
    private String invoice_to;
    private String email;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal total;
    private List<Invoice_Details> invoice_details;

    public Invoice_Header(Long invoice_id, String invoice_to, String email, BigDecimal subtotal, BigDecimal discount, BigDecimal total) {
        this.invoice_id = invoice_id;
        this.invoice_to = invoice_to;
        this.email = email;
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = total;
    }
}
