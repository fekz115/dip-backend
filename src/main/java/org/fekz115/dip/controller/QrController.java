package org.fekz115.dip.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.fekz115.dip.model.Article;
import org.fekz115.dip.repository.ArticleRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/qr")
@AllArgsConstructor
public class QrController {

    ArticleRepository articleRepository;
    public static final String FONT = "fonts/arial.ttf";

    @GetMapping(value = "/article/{articleId}", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<ByteArrayResource> generateQrToArticle(@PathVariable int articleId) throws WriterException, DocumentException, IOException {
        var article = articleRepository.findById(articleId).orElseThrow();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(article.getTitle(), StandardCharsets.UTF_8.toString()) + ".pdf\"")
                .body(new ByteArrayResource(generateDocument(article)));
    }

    private BufferedImage generateQRCodeImage(String barcodeText) throws WriterException {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    private byte[] generateDocument(Article article) throws DocumentException, WriterException, IOException {
        var ans = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, ans);
        document.open();

        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        Font font = new Font(bf, 18, Font.NORMAL);
        Chunk chunk = new Chunk(article.getTitle(), font);

        Image img = Image.getInstance(toByteArray(generateQRCodeImage("/article/" + article.getId())));
        img.setAlignment(Element.ALIGN_CENTER);

        Phrase phrase = new Phrase();
        phrase.add(chunk);

        Paragraph para = new Paragraph();
        para.add(phrase);
        para.setAlignment(Element.ALIGN_CENTER);

        document.add(para);
        document.add(img);

        document.close();
        return ans.toByteArray();
    }

    private byte[] toByteArray(BufferedImage bi)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", baos);
        return baos.toByteArray();

    }

}
