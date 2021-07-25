package org.certificate.project.CertifyMe.certificates;

import com.itextpdf.kernel.geom.Rectangle;

public interface Certificate {
	
	public void addStamp(String location);
	
	public String getName();
	public String getDescription();
	public String getSignature();
	public String getDate();
	public String getStamp();

	public boolean signatureIsImage();
	public boolean hasStamp();

	public Rectangle getNamePosition();
	public Rectangle getDescriptionPosition();
	public Rectangle getSignaturePosition();
	public Rectangle getDatePosition();
	public Rectangle getStampPosition();
	
	public String getCertificateAsPdf();
}
