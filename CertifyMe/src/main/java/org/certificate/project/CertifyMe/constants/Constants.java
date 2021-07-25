package org.certificate.project.CertifyMe.constants;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;

public class Constants {

	// Image of the certificate
	public static final String IMG_BLACK_CERTIF = "img/img_certificates/c_1_template.png";
	public static final String IMG_WHITE_CERTIF = "img/img_certificates/c_2_template.jpg";

	// converted pdf of the certificate
	public static final String PDF_BLACK_CERTIF = "pdf/pdf_certificates/c_1_pdf_template.pdf";
	public static final String PDF_WHITE_CERTIF = ""; //"pdf/pdf_certificates/c_2_pdf_template.pdf";
	public static final String CERTIFICATE_1 = PDF_BLACK_CERTIF;
	public static final String CERTIFICATE_2 = PDF_WHITE_CERTIF;

	// Stamps
	public static final String STAMP1 = "img/stamps/stamp.png";
	public static final String STAMP2 = "img/stamps/stamp2.png";
	
	// Colours
	public static final Color COLOR_GOLD = new DeviceRgb(244, 213, 124);
	public static final Color COLOR_GREEN = new DeviceRgb(94,186,125);
	public static final Color COLOR_BLUE = new DeviceRgb(26,5,243);
	public static final Color COLOR_GREY = new DeviceRgb(140,140,140);
	public static final Color COLOR_BLACK = new DeviceRgb(53,53,53);
	public static final Color COLOR_RED = new DeviceRgb(255,0,0);
	public static final Color COLOR_CYAN = new DeviceRgb(0,255,255);

	// Fonts
	public static final String FOND_ITALIC = "fonts/Fondamento-Italic.ttf";
	public static final String FOND_REGULAR = "fonts/Fondamento-Regular.ttf";
	
	// Signatures
	public static final String SIGN_ASLAM = "img/sign/sign_aslam.jpg";
}
