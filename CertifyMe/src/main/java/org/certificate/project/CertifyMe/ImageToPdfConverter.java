package org.certificate.project.CertifyMe;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class ImageToPdfConverter {

	private String image;
	private String generatedPdf;

	public ImageToPdfConverter(String image, String generatedPdf) {
		this.image = image;
		this.generatedPdf = generatedPdf;
	}
	
	public PdfDocument process() throws MalformedURLException{
		Image certificate = new Image(ImageDataFactory.create(image));
		PdfWriter pdfWriter = null;
		try {
			pdfWriter = new PdfWriter(new FileOutputStream(generatedPdf));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// create a new method 
			e.printStackTrace();
		}
		PdfDocument pdfDoc = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDoc,
				new PageSize(new Rectangle(certificate.getImageWidth(), certificate.getImageHeight())));
		document.setMargins(0, 0, 0, 0);
		
		document.add(certificate);
		
		System.out.println("done converting image to pdf");
		document.close();
		
		return pdfDoc;
	}
	
	// create a new empty pdf
}
