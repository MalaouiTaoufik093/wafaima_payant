package com.matu.mvc.services;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.Border;
import javax.swing.text.TabExpander;

import org.apache.poi.hssf.record.Margin;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Info_Client_Facture;


@Service
@Transactional
@CrossOrigin("*")
public class Facture {

	public boolean createPdf(List<Details_facture> quittances_payyes, Info_Client_Facture infoclient,
			ServletContext context, HttpServletRequest request, HttpServletResponse response) {
		 //Document document = new Document(PageSize.A4, 15, 15, 45,30);
		Document document = new Document(PageSize.A3.rotate(), 30, 30, 10, 10);

		try {
			String filePath = context.getRealPath("/resources/report");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();

			/*
			 * Image img = Image.getInstance("http://beleefmarokko.com/images/logo3.PNG");
			 * // img.setAbsolutePosition(10f, 10f); img.scaleAbsolute(100, 100);
			 * img.setAlignment(Element.);
			 */

			Image img = Image.getInstance("http://beleefmarokko.com/images/logo3.PNG");
			img.setAlignment(Element.ALIGN_LEFT);
			img.scaleAbsolute(150, 60);

			Font mainFont = FontFactory.getFont("Arial", 20, BaseColor.BLUE);
			Font mainFont2 = FontFactory.getFont("Arial", 11, BaseColor.BLACK);

			Paragraph paragraphe = new Paragraph("Siége sociale : 215 Boulvard zerktouni Casablanca, Maroc"
					+ "\n Tél : 05 22 95 45 00" + "\n Fax : 05 22 95 45 04" + "\n Ice : 0011696879000083"
					+ "\n Email : info@matu-assurance.ma", mainFont2);
			paragraphe.setAlignment(Element.ALIGN_LEFT);
			paragraphe.setIndentationLeft(0);
			paragraphe.setIndentationRight(50);
			paragraphe.setPaddingTop(-300);
			paragraphe.setSpacingAfter(10);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
			LocalDateTime now = LocalDateTime.now();

			Paragraph paragraphee = new Paragraph(
					"Facture : " + infoclient.getNumfacture() + "\n Date : " + dtf.format(now), mainFont2);
			paragraphee.setAlignment(Element.ALIGN_RIGHT);
			paragraphee.setIndentationLeft(10);
			paragraphee.setIndentationRight(20);
			paragraphee.setPaddingTop(-300);
			paragraphee.setSpacingAfter(-27);

			Font tableBody = FontFactory.getFont("Arial", 11, BaseColor.BLACK);
			Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);

			PdfPTable table5 = new PdfPTable(1);

			table5.setWidthPercentage(50);
			table5.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.setSpacingBefore(10f);
			table5.setSpacingAfter(10);
//			                

			PdfPCell sinistre = new PdfPCell(new Paragraph("Information de Client ", boldFont));
			sinistre.setBorderColor(BaseColor.BLACK);
			sinistre.setPaddingLeft(5);
			sinistre.setHorizontalAlignment(Element.ALIGN_CENTER);
			sinistre.setVerticalAlignment(Element.ALIGN_CENTER);
			sinistre.setBackgroundColor(BaseColor.LIGHT_GRAY);
			sinistre.setExtraParagraphSpace(2f);
			sinistre.setColspan(4);
			table5.addCell(sinistre);

			PdfPCell loi_sinistre = new PdfPCell(new Paragraph(
					" Client : " + infoclient.getClient() + "" + "\n Adresse : " + infoclient.getAdresse() + ""
							+ "\n Téléphone :  +212  " + infoclient.getTelephone() + "",
					tableBody));
			loi_sinistre.setBorderColor(BaseColor.BLACK);
//			                emailValue.setPaddingLeft(10);
			loi_sinistre.setHorizontalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setVerticalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setBackgroundColor(BaseColor.WHITE);
			loi_sinistre.setExtraParagraphSpace(5f);
			table5.addCell(loi_sinistre);

			Paragraph paragraph = new Paragraph("Facture Détaillée ", mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setPaddingTop(-200);
			// paragraph.setSpacingBefore(200);

			document.add(img);

			document.add(paragraphee);
			document.add(paragraphe);
			document.add(table5);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(5);// column amount
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBodyy = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

			float[] columnWidths = { 2f, 2f, 2f, 2f, 2f };
			table.setWidths(columnWidths);

//	            PdfPCell numfacture = new PdfPCell(new Paragraph("N° Facture", tableHeader));
//	            numfacture.setBorderColor(BaseColor.BLACK);
//	            numfacture.setPaddingLeft(10);
//	            numfacture.setHorizontalAlignment(Element.ALIGN_CENTER);
//	            numfacture.setVerticalAlignment(Element.ALIGN_CENTER);
//	            numfacture.setBackgroundColor(BaseColor.LIGHT_GRAY);
//	            numfacture.setExtraParagraphSpace(5f);
//	            table.addCell(numfacture);

			/*
			 * PdfPCell client = new PdfPCell(new Paragraph("Client", tableHeader));
			 * client.setBorderColor(BaseColor.BLACK); client.setPaddingLeft(10);
			 * client.setHorizontalAlignment(Element.ALIGN_CENTER);
			 * client.setVerticalAlignment(Element.ALIGN_CENTER);
			 * client.setBackgroundColor(BaseColor.LIGHT_GRAY);
			 * client.setExtraParagraphSpace(5f); table.addCell(client);
			 */

			PdfPCell police = new PdfPCell(new Paragraph("Police", tableHeader));
			police.setBorderColor(BaseColor.BLACK);
			police.setPaddingLeft(10);
			police.setHorizontalAlignment(Element.ALIGN_CENTER);
			police.setVerticalAlignment(Element.ALIGN_CENTER);
			police.setBackgroundColor(BaseColor.LIGHT_GRAY);
			police.setExtraParagraphSpace(5f);
			table.addCell(police);

			PdfPCell matricule = new PdfPCell(new Paragraph("Matricule", tableHeader));
			matricule.setBorderColor(BaseColor.BLACK);
			matricule.setPaddingLeft(10);
			matricule.setHorizontalAlignment(Element.ALIGN_CENTER);
			matricule.setVerticalAlignment(Element.ALIGN_CENTER);
			matricule.setBackgroundColor(BaseColor.LIGHT_GRAY);
			matricule.setExtraParagraphSpace(5f);
			table.addCell(matricule);

			PdfPCell pht = new PdfPCell(new Paragraph("PHT", tableHeader));
			pht.setBorderColor(BaseColor.BLACK);
			pht.setPaddingLeft(10);
			pht.setHorizontalAlignment(Element.ALIGN_CENTER);
			pht.setVerticalAlignment(Element.ALIGN_CENTER);
			pht.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pht.setExtraParagraphSpace(5f);
			table.addCell(pht);

			PdfPCell taxe = new PdfPCell(new Paragraph("Taxe", tableHeader));
			taxe.setBorderColor(BaseColor.BLACK);
			taxe.setPaddingLeft(10);
			taxe.setHorizontalAlignment(Element.ALIGN_CENTER);
			taxe.setVerticalAlignment(Element.ALIGN_CENTER);
			taxe.setBackgroundColor(BaseColor.LIGHT_GRAY);
			taxe.setExtraParagraphSpace(5f);
			table.addCell(taxe);

			PdfPCell pttc = new PdfPCell(new Paragraph("PTTC", tableHeader));
			pttc.setBorderColor(BaseColor.BLACK);
			pttc.setPaddingLeft(10);
			pttc.setHorizontalAlignment(Element.ALIGN_CENTER);
			pttc.setVerticalAlignment(Element.ALIGN_CENTER);
			pttc.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pttc.setExtraParagraphSpace(5f);
			table.addCell(pttc);

			Double total_pht = 0.0;
			Double total_taxe = 0.0;
			Double total_pttc = 0.0;

			String pattern = "###,###.##";
			DecimalFormat decimalFormat = new DecimalFormat(pattern);

			for (Details_facture quittance : quittances_payyes) {

				total_pht += quittance.getPht();
				total_taxe += quittance.getTaxe();
				total_pttc += quittance.getPttc();

				/*
				 * PdfPCell clientvalue = new PdfPCell(new Paragraph(quittance.getClient(),
				 * tableHeader)); clientvalue.setBorderColor(BaseColor.BLACK);
				 * clientvalue.setPaddingLeft(10);
				 * clientvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * clientvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				 * clientvalue.setBackgroundColor(BaseColor.WHITE);
				 * clientvalue.setExtraParagraphSpace(5f); table.addCell(clientvalue);
				 */
				PdfPCell policevalue = new PdfPCell(new Paragraph(quittance.getPolice().toString(), tableHeader));
				policevalue.setBorderColor(BaseColor.BLACK);
				policevalue.setPaddingLeft(10);
				policevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				policevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				policevalue.setBackgroundColor(BaseColor.WHITE);
				policevalue.setExtraParagraphSpace(5f);
				table.addCell(policevalue);
				PdfPCell matriculevalue = new PdfPCell(new Paragraph(quittance.getMatricule().toString(), tableHeader));
				matriculevalue.setBorderColor(BaseColor.BLACK);
				matriculevalue.setPaddingLeft(10);
				matriculevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				matriculevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				matriculevalue.setBackgroundColor(BaseColor.WHITE);
				matriculevalue.setExtraParagraphSpace(5f);
				table.addCell(matriculevalue);
				PdfPCell phtvlaue = new PdfPCell(new Paragraph(decimalFormat.format(quittance.getPht()), tableHeader));
				phtvlaue.setBorderColor(BaseColor.BLACK);
				phtvlaue.setPaddingLeft(10);
				phtvlaue.setHorizontalAlignment(Element.ALIGN_CENTER);
				phtvlaue.setVerticalAlignment(Element.ALIGN_CENTER);
				phtvlaue.setBackgroundColor(BaseColor.WHITE);
				phtvlaue.setExtraParagraphSpace(5f);
				table.addCell(phtvlaue);
				PdfPCell taxevalue = new PdfPCell(
						new Paragraph(decimalFormat.format(quittance.getTaxe()), tableHeader));
				taxevalue.setBorderColor(BaseColor.BLACK);
				taxevalue.setPaddingLeft(10);
				taxevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				taxevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				taxevalue.setBackgroundColor(BaseColor.WHITE);
				taxevalue.setExtraParagraphSpace(5f);
				table.addCell(taxevalue);
				PdfPCell pttcvalue = new PdfPCell(
						new Paragraph(decimalFormat.format(quittance.getPttc()), tableHeader));
				pttcvalue.setBorderColor(BaseColor.BLACK);
				pttcvalue.setPaddingLeft(10);
				pttcvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setBackgroundColor(BaseColor.WHITE);
				pttcvalue.setExtraParagraphSpace(5f);
				table.addCell(pttcvalue);

			}

			PdfPTable tbl = new PdfPTable(2);
			tbl.setWidthPercentage(50);
			tbl.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl.setSpacingBefore(10f);
			tbl.setSpacingAfter(10);
			
			

			PdfPCell cell = new PdfPCell(new Paragraph("Montant Total HT"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(10);
			cell.setExtraParagraphSpace(5f);
			cell.setBorder(0);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph(decimalFormat.format(total_pht)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);			
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph("Taxe des Assurances"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(10);
			cell.setExtraParagraphSpace(5f);
			cell.setBorder(0);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph(decimalFormat.format(total_taxe)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph("Montatnt Total PTTC"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(10);
			cell.setExtraParagraphSpace(5f);
			cell.setBorder(0);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph(decimalFormat.format(total_pttc)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.PINK);
			tbl.addCell(cell);

			/*
			 * Paragraph footer = new
			 * Paragraph("Vueillez libeller tous les chéques à l'odrre de MATU Assurance" +
			 * "\n le relevé détaillé est joint à cette Facture" +
			 * "\n\n\n Nous Vous remercion de votre confiance.", mainFont2);
			 * footer.setAlignment(Element.ALIGN_LEFT); footer.setIndentationLeft(0);
			 * footer.setIndentationRight(50); footer.setPaddingTop(300);
			 * footer.setSpacingAfter(10); footer.setSpacingBefore(200);
			 */
			

			document.add(table);
			document.add(tbl);
			//document.add(footer);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

}
