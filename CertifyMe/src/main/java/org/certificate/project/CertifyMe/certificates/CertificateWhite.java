package org.certificate.project.CertifyMe.certificates;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.certificate.project.CertifyMe.constants.Constants;

import com.itextpdf.kernel.geom.Rectangle;

public class CertificateWhite implements Certificate{

	private String name;
	private String description;
	private String signature;
	private String date;
	private String stamp;
	private boolean signatureIsImage, hasStamp;

	public CertificateWhite(String name, String description, String date, String signature, boolean signatureIsImage) {
		this.name = name;
		this.description = description;
		this.signature = signature;
		this.date = date;
		this.signatureIsImage = signatureIsImage;
	}

	public CertificateWhite(String name, String description, String signature, boolean signatureIsImage) {
		this(name, description, new SimpleDateFormat("dd MMMM yyyy").format(new Date()), signature, signatureIsImage);
	}
	
	public CertificateWhite() {
		init();
	}
	
	private void init() {
		//name
		
		//description
		
		//signature
		
		//stamp
		
		//date
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getDescriptionPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getSignaturePosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getDatePosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getStampPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCertificateAsPdf() {
		return Constants.PDF_WHITE_CERTIF;
	}

}
