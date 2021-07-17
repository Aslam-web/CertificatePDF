package org.certificate.project.CertifyMe;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.certificate.project.CertifyMe.constants.CustomColours;
import org.certificate.project.CertifyMe.constants.CustomFontPaths;
import org.certificate.project.CertifyMe.constants.CustomStampPaths;

import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

public class CertificateWriter {
	private Certificate certificate;
	private String DEST;
	private String SRC;
	private PdfCanvas pdfCanvas;
	private Canvas canvas;
	

	public CertificateWriter(Certificate certificate, String DEST) {
		this.certificate = certificate;
		this.DEST=DEST;
		this.SRC = this.certificate.getCertificateAsPdf();
	}
	
	public void write() throws Exception{
		
			System.out.println("Preparing everything ...");
			Thread.sleep(1000);
		
			// get source and destination
			PdfDocument pdf = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
			
			// get pdfcanvas from the first page
			PdfPage page1 = pdf.getFirstPage();
			pdfCanvas = new PdfCanvas(page1);

			// start adding mini canvases		
			writeName();									// #1 - name
			
			writeDescription();								// #2 - description

			if (this.certificate.signatureIsImage()) {		
				writeSignature(true);						// #3a - if the signature is a String
			} else 
				writeSignature();							// #3b - if the signature is an Image
			
			writeDate();									// #4 - date
			
			if (this.certificate.hasStamp()) {
				writeStamp();								// #5 - stamp (optional)
			}
			
			pdf.close();
			System.out.println("Process Completed successfully");
			System.out.println("Collect your certificate from "+DEST);
	}

	private void writeName() throws IOException {
	
		PdfFont nameFont = PdfFontFactory.createFont(StandardFonts.COURIER_BOLDOBLIQUE);
		
		canvas = new Canvas(pdfCanvas, this.certificate.getNamePosition());
		canvas.setHorizontalAlignment(HorizontalAlignment.CENTER);
		Text name = new Text("M.N Mohamed Thaqi")
				.setFont(nameFont)
				.setUnderline()
				.setFontColor(CustomColours.GOLD)
				.setFontSize(42)
				.setTextAlignment(TextAlignment.CENTER);
		Paragraph paragraph = new Paragraph().add(name);
		paragraph.setTextAlignment(TextAlignment.CENTER);
		canvas.add(paragraph);
		
		System.out.println("Added Name ...");
		canvas.close();
	}

	private void writeDescription() throws IOException {
		
		PdfFont descFont = PdfFontFactory.createFont(StandardFonts.TIMES_ITALIC);
		
		canvas = new Canvas(pdfCanvas, this.certificate.getDescriptionPosition());
		canvas.setHorizontalAlignment(HorizontalAlignment.CENTER);
		Text disc = new Text("Completing the course provided by Harish Infotech\nIn the year 2021")
				.setFont(descFont)
				.setFontColor(CustomColours.GREY)
				.setFontSize(24)
				.setTextAlignment(TextAlignment.CENTER);
		Paragraph paragraph = new Paragraph().add(disc);
		paragraph.setTextAlignment(TextAlignment.CENTER);
		canvas.add(paragraph);

		System.out.println("Added description ...");
		canvas.close();
	}

	private void writeSignature() throws IOException {
		
		//font for signature
		PdfFont signFont = PdfFontFactory.createFont(FontProgramFactory.createFont(CustomFontPaths.Fondamento_Italic));
		
		canvas = new Canvas(pdfCanvas, this.certificate.getSignaturePosition());
		Text signature = new Text("Shoiab infoTech")
				.setFont(signFont)
				.setFontColor(CustomColours.GOLD)
				.setFontSize(25)
				.setItalic()
				.setTextAlignment(TextAlignment.LEFT);
		Paragraph paragraph = new Paragraph().add(signature);
		paragraph.setTextAlignment(TextAlignment.LEFT);
		canvas.add(paragraph);

		System.out.println("Added Signature ...");
		canvas.close();
	}

	private void writeSignature(boolean image) throws MalformedURLException {

		Image signature = new Image(ImageDataFactory.create(this.certificate.getSignature()));
		Rectangle rect = this.certificate.getSignaturePosition();
		
		canvas = new Canvas(pdfCanvas, rect);
		signature.scaleAbsolute(rect.getWidth(), rect.getHeight());
		canvas.add(signature);

		System.out.println("Added Signature ...");
		canvas.close();
	}

	private void writeDate() throws IOException {
		
		// font for date
		PdfFont dateFont = PdfFontFactory.createFont(FontProgramFactory.createFont(CustomFontPaths.Fondamento_Regular));
		
		canvas = new Canvas(pdfCanvas, this.certificate.getDatePosition());
		SimpleDateFormat DateFor = new SimpleDateFormat("dd MMMM yyyy");
		String stringDate = DateFor.format(new Date());
		Text date = new Text(stringDate)
				.setFont(dateFont)
				.setFontColor(CustomColours.GOLD)
				.setFontSize(25)
				.setTextAlignment(TextAlignment.CENTER);
		Paragraph paragraph = new Paragraph().add(date);
		paragraph.setTextAlignment(TextAlignment.CENTER);
		canvas.add(paragraph);

		System.out.println("Added Date ...");
		canvas.close();
	}

	private void writeStamp() throws IOException {

		Image stamp = new Image(ImageDataFactory.create(CustomStampPaths.STAMP1));
		Rectangle rect = this.certificate.getStampPosition();
		
		canvas = new Canvas(pdfCanvas, rect);
		stamp.scaleAbsolute(rect.getWidth(), rect.getHeight());
		canvas.add(stamp);

		System.out.println("Added Stamp ...");
		canvas.close();
	}
}






















