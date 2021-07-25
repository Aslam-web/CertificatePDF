package org.certificate.project.CertifyMe;

import java.io.IOException;
import java.net.MalformedURLException;

import org.certificate.project.CertifyMe.certificates.Certificate;
import org.certificate.project.CertifyMe.constants.Constants;

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
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

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
				writeSignature(true);						// #3a - if the signature is a Image
			} else 
				writeSignature();							// #3b - if the signature is an String
			
			writeDate();									// #4 - date
			
			if (this.certificate.hasStamp()) {
				writeStamp();								// #5 - stamp (optional)
			}
			
			pdf.close();
			System.out.println("-------------------------------------------------\n");
			System.out.println("Process Completed successfully");
			System.out.println("Collect your certificate from "+DEST);
	}

	private void writeName() throws IOException {
	
		PdfFont nameFont = PdfFontFactory.createFont(StandardFonts.COURIER_BOLDOBLIQUE);
		
		canvas = new Canvas(pdfCanvas, this.certificate.getNamePosition());
		canvas.setHorizontalAlignment(HorizontalAlignment.CENTER);
		Text name = new Text(certificate.getName())
				.setFont(nameFont)
				.setUnderline()
				.setFontColor(Constants.COLOR_CYAN)
				.setFontSize(42)
				.setTextAlignment(TextAlignment.CENTER);
		Paragraph paragraph = new Paragraph().add(name);
		paragraph.setTextAlignment(TextAlignment.CENTER);
		canvas.add(paragraph);

		System.out.println("\n-------------------------------------------------\n"
				+ "Added Name: "+this.certificate.getName());
		canvas.close();
	}

	private void writeDescription() throws IOException {
		
		PdfFont descFont = PdfFontFactory.createFont(StandardFonts.TIMES_ITALIC);
		
		canvas = new Canvas(pdfCanvas, this.certificate.getDescriptionPosition());
		canvas.setHorizontalAlignment(HorizontalAlignment.CENTER);
		Text disc = new Text(certificate.getDescription())
				.setFont(descFont)
				.setFontColor(Constants.COLOR_GREY)
				.setFontSize(24)
				.setTextAlignment(TextAlignment.CENTER);
		Paragraph paragraph = new Paragraph().add(disc);
		paragraph.setTextAlignment(TextAlignment.CENTER);
		canvas.add(paragraph);

		System.out.println("Added Description: "+this.certificate.getDescription());
		canvas.close();
	}

	private void writeSignature() throws IOException {
		
		//font for signature
		PdfFont signFont = PdfFontFactory.createFont(FontProgramFactory.createFont(Constants.FOND_ITALIC));
		
		canvas = new Canvas(pdfCanvas, this.certificate.getSignaturePosition());
		canvas.setBorder(new SolidBorder(2000));
		canvas.setHorizontalAlignment(HorizontalAlignment.CENTER);
		Text signature = new Text(this.certificate.getSignature())
				.setFont(signFont)
				.setFontColor(Constants.COLOR_GREEN)
				.setFontSize(25)
				.setItalic()
				.setTextAlignment(TextAlignment.CENTER);
		Paragraph paragraph = new Paragraph().add(signature);
		paragraph.setTextAlignment(TextAlignment.CENTER);
		paragraph.setVerticalAlignment(VerticalAlignment.BOTTOM);
		canvas.add(paragraph);

		System.out.println("Added Signature: "+this.certificate.getSignature());
		canvas.close();
	}

	private void writeSignature(boolean image) throws MalformedURLException {

		Image signature = new Image(ImageDataFactory.create(this.certificate.getSignature()));
		Rectangle rect = this.certificate.getSignaturePosition();
		
		canvas = new Canvas(pdfCanvas, rect);
		signature.scaleAbsolute(rect.getWidth(), rect.getHeight());
		canvas.add(signature);

		System.out.println("Added Signature Location: "+this.certificate.getSignature());
		canvas.close();
	}

	private void writeDate() throws IOException {
		
		// font for date
		PdfFont dateFont = PdfFontFactory.createFont(FontProgramFactory.createFont(Constants.FOND_REGULAR));
		
		canvas = new Canvas(pdfCanvas, this.certificate.getDatePosition());
		Text date = new Text(certificate.getDate())
				.setFont(dateFont)
				.setFontColor(Constants.COLOR_RED)
				.setFontSize(25)
				.setTextAlignment(TextAlignment.CENTER);
		Paragraph paragraph = new Paragraph().add(date);
		paragraph.setTextAlignment(TextAlignment.CENTER);
		canvas.add(paragraph);

		System.out.println("Added Date: "+this.certificate.getDate());
		canvas.close();
	}

	private void writeStamp() throws IOException {

		String stampLocation = certificate.getStamp() != null ? certificate.getStamp() : Constants.STAMP1;
		
		Image stamp = new Image(ImageDataFactory.create(stampLocation));
		Rectangle rect = this.certificate.getStampPosition();
		
		canvas = new Canvas(pdfCanvas, rect);
		stamp.scaleAbsolute(rect.getWidth(), rect.getHeight());
		canvas.add(stamp);

		System.out.println("Added Stamp Location: "+this.certificate.getStamp());
		canvas.close();
	}
}