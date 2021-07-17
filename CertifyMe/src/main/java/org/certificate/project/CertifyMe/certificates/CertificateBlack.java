package org.certificate.project.CertifyMe.certificates;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.certificate.project.CertifyMe.Certificate;
import org.certificate.project.CertifyMe.constants.CustomCertificates;

import com.itextpdf.kernel.geom.Rectangle;

public class CertificateBlack implements Certificate {

	private String name;
	private String description;
	private String signature;
	private String date;
	private String stamp;
	private boolean signatureIsImage, hasStamp;

	public CertificateBlack(String name, String description, String date, String signature, boolean signatureIsImage) {
		this.name = name;
		this.description = description;
		this.signature = signature;
		this.date = date;
		this.signatureIsImage = signatureIsImage;
	}

	public CertificateBlack(String name, String description, String signature, boolean signatureIsImage) {
		this(name, description, new SimpleDateFormat("dd MMMM yyyy").format(new Date()), signature, signatureIsImage);
	}

	public void addStamp(String stamp) {
		this.stamp = stamp;
		this.hasStamp = true;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getSignature() {
		return signature;
	}

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public String getStamp() {
		return hasStamp() ? this.stamp : null;
	}

	@Override
	public boolean signatureIsImage() {
		return signatureIsImage;
	}

	@Override
	public boolean hasStamp() {
		return hasStamp;
	}

	@Override
	public Rectangle getNamePosition() {
		return new Rectangle(65, 270, 764, 110);
	}

	@Override
	public Rectangle getDescriptionPosition() {
		return new Rectangle(65, 400, 764, 80);
	}

	@Override
	public Rectangle getSignaturePosition() {
		return new Rectangle(560, 80, 250, 110);
	}

	@Override
	public Rectangle getDatePosition() {
		return new Rectangle(125, 80, 250, 110);
	}

	@Override
	public Rectangle getStampPosition() {
		return this.hasStamp ? new Rectangle(377, 80, 150, 150) : null;
	}

	@Override
	public String getCertificateAsPdf() {
		return CustomCertificates.PDF.CERTIF_BLACK;
	}
}
