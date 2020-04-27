package id.khisoft.javaspring.Repository;

import id.khisoft.javaspring.Entity.Invoice_Header;

public interface InvoiceRepository {
    public Invoice_Header getInvoice(int id);
    public int save(Invoice_Header invoice_header);
}
