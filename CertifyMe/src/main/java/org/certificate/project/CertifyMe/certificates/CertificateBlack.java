package org.certificate.project.CertifyMe.certificates;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.certificate.project.CertifyMe.constants.Constants;

import com.itextpdf.kernel.geom.Rectangle;

public class CertificateBlack implements Certificate {

	private String name;
	private String description;
	private String signature;
	private String date = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
	private String stamp;
	private boolean signatureIsImage, hasStamp;

	private String error;

	public CertificateBlack(String name, String description, String date, String signature, boolean signatureIsImage) {
		this.name = name;
		this.description = description;
		this.signature = signature;
		this.date = date;
		this.signatureIsImage = signatureIsImage;

		if (this.description.length() > 140)
			throw new IllegalArgumentException("Description exceeded 140 characters");
		else if (this.signature.length() > 23 && !signatureIsImage)
			throw new IllegalArgumentException("Signature exceeded 23 characters");
		else if (this.date.length() > 20) 
			throw new IllegalArgumentException("Date exceeded 20 characters");
	}

	public CertificateBlack(String name, String description, String signature, boolean signatureIsImage) {
		this(name, description, new SimpleDateFormat("dd MMMM yyyy").format(new Date()), signature, signatureIsImage);
	}

	public CertificateBlack() {
		if (!init())
			throw new IllegalArgumentException(this.error);
	}

	private boolean init() {
		
		System.out.println("######################## Certificate Creation ########################");
		Scanner sc = new Scanner(System.in);
		
		//name
		System.out.print("Name : ");
		this.name = sc.nextLine();

		//description
		System.out.print("Description (Max 140 characters) : ");
		String desc = sc.nextLine();
		if (desc.length() > 140) {
			System.out.println("Description exceeded 140 characters.Try again !!!");
			this.error = "Description exceeded 140 characters";
			sc.close();
			return false;
		}
		this.description = desc;

		//signature
		String sign="";
		System.out.print("Signature: \nIs it image or text? (i/t) : ");
		String type = sc.next();
		if (type.equals("image") || type.equals("i")) {
			System.out.print("Image location: ");
			sc.nextLine();
			sign = sc.nextLine();
			this.signatureIsImage = true;
		} else if (type.equals("text") || type.equals("t")) {
			System.out.print("Signature (Max 23 characters) : ");
			sc.nextLine();
			sign = sc.nextLine();
			if (sign.length() > 23) {
				System.out.println("Signature is lengthy. Add an image instead");
				this.error = "Signature exceeded 23 characters";
				sc.close();
				return false;
			}
		} else if (!type.equals("")) {
			this.error = "Invalid input. type i/image for image (or) t/text for text";
			sc.close();
			return false;
		}
		this.signature = sign;
		
		//stamp
		System.out.print("Stamp:\nWould you like to add a Stamp? (y/n) : ");
		String stampCheck = sc.next();
		if (stampCheck.equals("y")) {
			System.out.print("Stamp's location: ");
			this.stamp = sc.next();
			this.hasStamp = true;
		}

		//date
		System.out.print("Date:\nWould you like to provide a date? (y/n) : ");
		String dateCheck = sc.next();
		if (dateCheck.equals("y")) {
			System.out.print("Custom date (Max 20 characters) : ");
			sc.nextLine();
			String customDate = sc.nextLine();
			if (customDate.length() > 20) {
				System.out.println("Date is lengthy");
				this.error = "Date exceeded 20 characters";
				sc.close();
				return false;
			}
			this.date = customDate;
		}

		sc.close();
		return true;
	}

	@Override
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
		return new Rectangle(65, 240, 764, 110);
	}

	@Override
	public Rectangle getDescriptionPosition() {
		return new Rectangle(65, 400, 764, 80);
	}

	@Override
	public Rectangle getSignaturePosition() {
		if (signatureIsImage)
			return new Rectangle(540, 140, 250, 110);
		return new Rectangle(540, 80, 250, 110);
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
		return Constants.PDF_BLACK_CERTIF;
	}
}
