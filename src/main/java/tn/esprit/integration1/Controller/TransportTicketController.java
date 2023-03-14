package tn.esprit.integration1.Controller;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.TransportTicket;
import tn.esprit.integration1.Interfaces.ITransportTicketService;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/TransportTicket")
@Slf4j

public class TransportTicketController {
    @Autowired
    ITransportTicketService transportTicketServiceService;

    @PostMapping("/AddandAssignTransportTicket/{idTransport}")

    public TransportTicket AddandAssignTransportTicket(@RequestBody TransportTicket Ticket, @PathVariable("idTransport") Integer idTransport) {
        TransportTicket transportTicket = transportTicketServiceService.AddAndAssignTransportTicketTotransport(Ticket, idTransport);
        return transportTicket;
    }
     /* @PostMapping("/AddTransportTicket")
    public TransportTicket addTransportTicket(@RequestBody TransportTicket f) {

 TransportTicket t=transportTicketServiceService.addTransportTicket(f);
    return t;
    }
     @PutMapping("/AssignTransportTicketTotransport/{idTransportTicket}/{idTransport}")
    public void AssignTransportTicketTotransport(@PathVariable Integer idTransportTicket, @PathVariable Integer idTransport) {
        transportTicketServiceService.AssignTransportTicketTotransport(idTransportTicket, idTransport);
    }*/

    /*@PostMapping("/calculateDistance")
    public double calculateDistanceCity(String dep, String des) {
        double d = transportTicketServiceService.calculateDistanceCity(dep, des);
        return d;
    }
    @PostMapping("/calculatePrice")
    public double calculatePrice(String dep, String des) {
        double P = transportTicketServiceService.calculatePrice(dep, des);
        return P;
    }*/

    @GetMapping("/retrieve-all-transportTickets")
    public List<TransportTicket> getTransportTickets() {
        List<TransportTicket> listTransportTickets = transportTicketServiceService.retrieveAllTransportTickets();
        return listTransportTickets;
    }



    @PutMapping("/update-transportTicket")
    public TransportTicket updateTransportTicket(@RequestBody TransportTicket c) {
        TransportTicket transportTicket = transportTicketServiceService.updateTransportTicket(c);
        return transportTicket;
    }

    @DeleteMapping("/remove-transportTicket/{idTransportTicket}")
    public void removeTransportTicket(@PathVariable("idTransportTicket") Integer idTransportTicket) {

        transportTicketServiceService.removeTransportTicket(idTransportTicket);
    }

    @PutMapping("/assign-transportTicket-to-user/{idTransportTicket}/{IdUser}")
    public void assignTransportTicketToUser(@PathVariable("idTransportTicket") Integer idTransportTicket,
                                            @PathVariable("IdUser") Integer IdUser) {
        transportTicketServiceService.assignTransportTicketToUser(idTransportTicket, IdUser);

    }

    /*   @PutMapping("/assign-transportTicket-to-transport/{Id}/{idTransport}")
       public void assignTransportTicketToTransport(@PathVariable("Id") Integer Id,
                                        @PathVariable("idTransport") Integer idTransport) {
           transportTicketServiceService.assignTransportTicketToTransport(Id,idTransport);

       }*/
    @GetMapping("/DownloadQRCode/{idTransportTicket}")
    public void retreviveTranportTicket(@PathVariable("idTransportTicket") Integer idTransportTicket, HttpServletResponse response) throws WriterException, IOException {
        TransportTicket t= transportTicketServiceService.retreviveTranportTicket2(idTransportTicket,response);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(t.toString(), BarcodeFormat.QR_CODE, 200, 200);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
        byte[] barcodeBytes = outputStream.toByteArray();

        response.setContentType("image/png");
        response.setContentLength(barcodeBytes.length);
        response.setHeader("Content-Disposition", "attachment; filename=\"barcode.png\"");

        response.getOutputStream().write(barcodeBytes);
        response.getOutputStream().flush();

    }
    @GetMapping(path = "/Generateqrcode/{idTransportTicket}", produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage generateQRCodeImage(@PathVariable("idTransportTicket") Integer idTransportTicket) throws Exception {
//QRcode generator logic
        TransportTicket t= transportTicketServiceService.retreviveTranportTicket(idTransportTicket);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(t.toString(), BarcodeFormat.QR_CODE, 250, 250);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }



}

