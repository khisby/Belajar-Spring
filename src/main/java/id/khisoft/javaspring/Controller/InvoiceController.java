package id.khisoft.javaspring.Controller;

import id.khisoft.javaspring.Entity.Invoice_Header;
import id.khisoft.javaspring.Repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class InvoiceController {

    @Autowired
    @Qualifier("invoiceRepository")
    private InvoiceRepository invoiceRepository;

    @GetMapping("/invoice")
    public Invoice_Header getInvoiceById(@RequestParam("id") int id){
        return invoiceRepository.getInvoice(id);
    }

    @PostMapping("/invoice")
    public int saveInvoice(@Valid @RequestBody Invoice_Header invoice_header){
        return invoiceRepository.save(invoice_header);
    }


}
