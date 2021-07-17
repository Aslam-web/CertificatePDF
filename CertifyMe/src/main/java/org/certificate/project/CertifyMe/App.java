package org.certificate.project.CertifyMe;

import org.certificate.project.CertifyMe.certificates.CertificateBlack;

public class App {

	public static final String out1 = "pdf\\project_out_1.pdf";
	public static final String out2 = "pdf\\project_out_2.pdf";
	
	public static void main(String[] args) throws Exception {
		
		// 1. create a Certificate object with neccessary details
		// 2. use that Certificate instance to write using CertificateWriter object
		
		// 1.
		Certificate myFirstCertificate = new CertificateBlack("M.N Aslam", "Completed the course provided by Harish Infotech\r\n"
				+ "In the year 2021", "Shoiab Infotech", false);
		
		// 2.
		CertificateWriter certificateWriter = new CertificateWriter(myFirstCertificate, out1);
		certificateWriter.write();
	}
}
