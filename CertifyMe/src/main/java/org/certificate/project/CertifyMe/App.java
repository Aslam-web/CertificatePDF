package org.certificate.project.CertifyMe;

import org.certificate.project.CertifyMe.certificates.Certificate;
import org.certificate.project.CertifyMe.certificates.CertificateBlack;
import org.certificate.project.CertifyMe.certificates.CertificateWhite;
import org.certificate.project.CertifyMe.constants.Constants;

public class App {
	
	public static final String output1 = "output\\project_out_1.pdf";
	public static final String output2 = "output\\project_out_2.pdf";
	public static final String output3 = "output\\project_out_3.pdf";

	public static void main(String[] args) throws Exception {

		// To convert any image to a pdf(cropped perfectly)
//		ImageToPdfConverter converter = new ImageToPdfConverter(Constants.IMG_BLACK_CERTIF, output1);
//		converter.process();

		
		
		// 1. Create certificate from input from console
		Certificate certificate = new CertificateBlack();
		new CertificateWriter(certificate, output1).write();	
		
		
		
		// 2. Create certificate using hardcoded values
//		Certificate certificate = new CertificateBlack(
//				"M.N Aslam",
//				"Completed the course provided by Harish Infotech In the year 2021. some additional text",
//				"25-07-2021", "my signature", false);
//		certificate.addStamp(Constants.STAMP_IT);						// add a stamp (optional)
//		new CertificateWriter(certificate, output2).write();			// writing to the given location	
	}
}



















