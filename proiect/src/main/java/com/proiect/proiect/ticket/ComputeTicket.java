package com.proiect.proiect.ticket;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.proiect.proiect.model.Bilet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ComputeTicket {

    public static void computeBill(Bilet bilet) throws IOException, DocumentException, URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("static/qr.png").toURI());

        Font font1 = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ticket.pdf"));

        document.open();

        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello! This is your ticket:", font);
        document.add(chunk);

        //header
        PdfContentByte canvas = writer.getDirectContent();

        //rosu sus
        canvas.setColorFill(BaseColor.RED);
        canvas.rectangle(36, 720, 524, 50);
        canvas.fill();

        //rosu jos
        canvas.setColorFill(BaseColor.RED);
        canvas.rectangle(36, 500, 524, 30);
        canvas.fill();

        canvas.setLineWidth(3);
        canvas.setColorStroke(BaseColor.BLACK);
        //linie jos spre dreapta
        canvas.moveTo(36, 500);
        canvas.lineTo(560, 500);
        //linie sus spre dreapta
        canvas.moveTo(36, 770);
        canvas.lineTo(560, 770);
        //linie stanga in sus
        canvas.moveTo(36, 500);
        canvas.lineTo(36, 770);
        //linie dreapta in sus
        canvas.moveTo(560, 500);
        canvas.lineTo(560, 770);

        ColumnText ct = new ColumnText(canvas);
        Phrase myText = new Phrase("FLY TICKET", font);
        ct.setSimpleColumn(myText, 60, 760, 300, 30, 20, Element.ALIGN_LEFT);
        ct.go();
        myText = new Phrase("FIRST CLASS\nBOARDING PASS", font);
        ct.setSimpleColumn(myText, 500, 770, 50, 50, 20, Element.ALIGN_RIGHT);
        ct.go();

        canvas.closePathStroke();

        //for details
        PdfContentByte canvas1 = writer.getDirectContent();

        //canvas1.setColorFill(BaseColor.WHITE);
        // canvas1.rectangle(200, 590, 350,100);
        // canvas1.fill();

        ColumnText c = new ColumnText(canvas1);
        Phrase text = new Phrase("Name: " + bilet.getPersoana().getNumePersoana() + "\n\n" +
                "Flight" + "  " + "Persons" + "     " + "Date" + "        " + "Time\n" +
                "  " + bilet.getZborItem().getIdZbor() +
                "       " + bilet.getNrPasageri() +
                "     " + bilet.getData() +
                "    " + bilet.getZborItem().getOraPlecare()
                , font1);
        c.setSimpleColumn(text, 550, 700, 200, 50, 20, Element.ALIGN_LEFT);
        c.go();

        canvas1.setLineWidth(2);
        canvas1.setColorStroke(BaseColor.RED);

        canvas1.moveTo(200, 600);
        canvas1.lineTo(540, 600);

        canvas1.closePathStroke();

        //for company
        PdfContentByte canvas2 = writer.getDirectContent();

        canvas2.setColorFill(BaseColor.WHITE);
        canvas2.rectangle(50, 540, 160, 30);
        canvas2.fill();

        ColumnText c1 = new ColumnText(canvas2);
        Phrase myText1 = new Phrase("" + bilet.getZborItem().getCompanie(), font);
        c1.setSimpleColumn(myText1, 180, 575, 80, 30, 20, Element.ALIGN_LEFT);
        c1.go();

        canvas2.closePathStroke();

        //for from and to
        PdfContentByte canvas3 = writer.getDirectContent();

        canvas3.setColorFill(BaseColor.WHITE);
        canvas3.rectangle(220, 540, 300, 50);
        canvas3.fill();

        ColumnText c2 = new ColumnText(canvas3);
        Phrase myText2 = new Phrase("  From" + "          " + "To\n"
                + bilet.getFrom()
                + "       " + bilet.getTo()
                , font);
        c2.setSimpleColumn(myText2, 540, 590, 240, 30, 20, Element.ALIGN_LEFT);
        c2.go();


        canvas3.closePathStroke();

        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.setAbsolutePosition(45, 570);
        img.scalePercent(70);
        document.add(img);


        document.close();
    }


}
