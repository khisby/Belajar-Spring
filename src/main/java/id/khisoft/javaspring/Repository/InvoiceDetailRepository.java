package id.khisoft.javaspring.Repository;

import id.khisoft.javaspring.Entity.Invoice_Details;

import java.util.List;

public interface InvoiceDetailRepository {
    public List<Invoice_Details> getInvoiceDetail(int invoiceId);
}
