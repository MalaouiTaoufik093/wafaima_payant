package com.matu.mvc.services;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.matu.mvc.models.WAFAIMA_Contrats;
import com.matu.mvc.models.WAFAIMA_Garanties;
import com.matu.mvc.repositories.WAFAIMA_ContratsRpository;

@Service
@Transactional
@CrossOrigin("*")
public class Contrat_service {
	private final WAFAIMA_ContratsRpository contrarrepository;
	private HSSFWorkbook workbook;

	@Autowired
	public Contrat_service(WAFAIMA_ContratsRpository contrarrepository) {
		this.contrarrepository = contrarrepository;
	}
	@CrossOrigin("*")
	public List<WAFAIMA_Contrats> get_clients_wafa_ima() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		return contrarrepository.clients_ayant_offers(idsite);
	}
	@CrossOrigin("*")
	public WAFAIMA_Contrats get_client_by_ida_ffaire(Integer id) {
		return contrarrepository.affaire_formule(id);
	}
	
	
	
	
	
	
	@CrossOrigin("*")
	public boolean createPdf(WAFAIMA_Contrats client,List<WAFAIMA_Garanties> garanties, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A3.rotate(), 10, 10, 10, 10);
		// Document document = new Document(PageSize.A4, 15, 15, 45,30);

		try {
			String filePath = context.getRealPath("/resources/report");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();

			
			//Image img = Image.getInstance("https://www.wafaassurance.ma/sites/default/files/x2fichier_23x.png");
			//Image img = Image.getInstance("http://beleefmarokko.com/images/logo3.PNG");
			Image img = Image.getInstance("http://beleefmarokko.com/images/wafaima.png");
			img.setAlignment(Element.ALIGN_RIGHT);
			img.scaleAbsolute(200, 50);
			
			
			
			
			Font mainFont = FontFactory.getFont("Arial", 12, BaseColor.BLACK);
			Font mainFont2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.RED);
			Font mainFont3 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);

			Paragraph paragraph = new Paragraph("EXTRAIT DES CONDITIONS PARTICULIERES DU CONTRAT", mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setPaddingTop(500);
			paragraph.setSpacingBefore(-20);

			Paragraph paragraph2 = new Paragraph(
					"VEHICULE TOURISME & C1 - CONTRAT ASSASS ASSISTANCE 1 an (RC + BGD et/ou INC et/ou VOL)",
					mainFont2);
			paragraph2.setAlignment(Element.ALIGN_CENTER);
			paragraph2.setIndentationLeft(50);
			paragraph2.setIndentationRight(50);
			paragraph2.setPaddingTop(-50);
			paragraph2.setSpacingAfter(10);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			Paragraph paragraph3 = new Paragraph(
					"  Wafa Ima Assistance - Entreprise régie par loi N° 17-99 Portant Code des Assurances,Sa au capital de 50 000 000 DH"
							+ "\n - Adresse : Casablanca Business Centre - lot n°2 lotissement mandaouna,sidi Maarouf "
							+ " - Tél : 05 22 58 29 00-R.C/CASA N°119935"
							+ "\n Le présent contrat,objet de la décision de l'autorité de controle des assurances et de la prévoyance sociale n°....\n"
							+ " en date du : "+ "  "+dtf.format(now) + "  "+"\n est composé des présentes conditions particuliéres et des conditions générales ci-jointes."
							+ "\n N° de contrat : ... "+client.getId_contrat()+"...... ",
					mainFont3);
			paragraph3.setAlignment(Element.ALIGN_LEFT);
			paragraph3.setPaddingTop(100);
			paragraph3.setSpacingAfter(10);

			document.add(img);
			document.add(paragraph);
			document.add(paragraph2);
			document.add(paragraph3);

			PdfPTable table = new PdfPTable(2);// column amount
			table.setWidthPercentage(50);
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.setSpacingBefore(0f);
			
			table.setSpacingAfter(10);

			Font tableHeader = FontFactory.getFont("Arial", 12, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
			Font tableBody_véhicule = FontFactory.getFont("Arial", 11, BaseColor.BLACK);
			Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
			
			float[] columnWidths = {2, 6};

			table.setWidths(columnWidths);

			PdfPCell name = new PdfPCell(new Paragraph("Souscripteur", boldFont));
			name.setBorderColor(BaseColor.BLACK);
			name.setHorizontalAlignment(Element.ALIGN_CENTER);
			name.setVerticalAlignment(Element.ALIGN_CENTER);
			name.setBackgroundColor(BaseColor.ORANGE);
			name.setExtraParagraphSpace(2f);
			table.addCell(name);

			PdfPCell email = new PdfPCell(new Paragraph("Assuré", boldFont));
			email.setBorderColor(BaseColor.BLACK);
			email.setHorizontalAlignment(Element.ALIGN_CENTER);
			email.setVerticalAlignment(Element.ALIGN_CENTER);
			email.setBackgroundColor(BaseColor.ORANGE);
			email.setExtraParagraphSpace(5f);
			table.addCell(email);
			

			PdfPCell nameValue = new PdfPCell(new Paragraph(
					"Mutelle d'assurance des transports Unis 215,Bd Mohamed Zerktouni Casablanca", tableHeader));
			nameValue.setBorderColor(BaseColor.BLACK);
			nameValue.setPaddingTop(20);
			nameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			nameValue.setVerticalAlignment(Element.ALIGN_CENTER);
			nameValue.setBackgroundColor(BaseColor.WHITE);
			nameValue.setExtraParagraphSpace(7f);
			table.addCell(nameValue);

			PdfPCell emailValue = new PdfPCell(new Paragraph(
					     "" + " Nom et Prénom / RS  :     " + client.getNom_client()
							+ "\n Adresse domicile        :    " + client.getAdresse()
							+ "\n Code postale/Ville      :     " +client.getVille_cient()
							+ "\n TELEPHONE             :      "+ client.getTel_client() ,
					tableBody));
			emailValue.setBorderColor(BaseColor.BLACK);
			emailValue.setHorizontalAlignment(Element.ALIGN_LEFT);
			emailValue.setVerticalAlignment(Element.ALIGN_RIGHT);
			emailValue.setBackgroundColor(BaseColor.WHITE);
			emailValue.setExtraParagraphSpace(5f);
			table.addCell(emailValue);

		
			
			
			PdfPTable table_garanties = new PdfPTable(3);

			table_garanties.setWidthPercentage(50);
			table_garanties.setSpacingBefore(-50);
			table_garanties.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_garanties.setWidthPercentage(250 / 5.23f);
			table_garanties.getDefaultCell();
			table_garanties.setSpacingBefore(0f);
			table_garanties.setSpacingAfter(50f);
			table_garanties.setTotalWidth(new float[] { 100,100,100});

			PdfPCell prestation = new PdfPCell(
					new Paragraph("Principales prestations d'assistance garanties", tableHeader));
			prestation.setBorderColor(BaseColor.BLACK);
			prestation.setPaddingLeft(5);

			prestation.setHorizontalAlignment(Element.ALIGN_CENTER);
			prestation.setVerticalAlignment(Element.ALIGN_CENTER);
			prestation.setBackgroundColor(BaseColor.ORANGE);
			prestation.setExtraParagraphSpace(2f);
			prestation.setColspan(4);
			table_garanties.addCell(prestation);

 
			PdfPCell gar = new PdfPCell(new Paragraph("Garanties", boldFont));
			gar.setBorderColor(BaseColor.BLACK);
			gar.setPaddingLeft(5);
			gar.setHorizontalAlignment(Element.ALIGN_CENTER);
			gar.setVerticalAlignment(Element.ALIGN_CENTER);
			gar.setBackgroundColor(BaseColor.ORANGE);
			gar.setExtraParagraphSpace(2f);
			table_garanties.addCell(gar);
			PdfPCell panne = new PdfPCell(new Paragraph("Panne", boldFont));
			panne.setBorderColor(BaseColor.BLACK);
			panne.setPaddingLeft(5);
			panne.setHorizontalAlignment(Element.ALIGN_CENTER);
			panne.setVerticalAlignment(Element.ALIGN_CENTER);
			panne.setBackgroundColor(BaseColor.ORANGE);
			panne.setExtraParagraphSpace(2f);
			table_garanties.addCell(panne);
			PdfPCell accident = new PdfPCell(new Paragraph("Accident", boldFont));
			accident.setBorderColor(BaseColor.BLACK);
			accident.setPaddingLeft(5);
			accident.setHorizontalAlignment(Element.ALIGN_CENTER);
			accident.setVerticalAlignment(Element.ALIGN_CENTER);
			accident.setBackgroundColor(BaseColor.ORANGE);
			accident.setExtraParagraphSpace(2f);
			table_garanties.addCell(accident);
		
          for (WAFAIMA_Garanties garatie : garanties){
              PdfPCell garantieValue = new PdfPCell(new Paragraph(garatie.getLibelle(), tableBody));
              garantieValue.setBorderColor(BaseColor.BLACK);
              garantieValue.setPaddingLeft(10);
              garantieValue.setHorizontalAlignment(Element.ALIGN_CENTER);
              garantieValue.setVerticalAlignment(Element.ALIGN_CENTER);
              garantieValue.setBackgroundColor(BaseColor.WHITE);
              garantieValue.setExtraParagraphSpace(5f);
              table_garanties.addCell(garantieValue);

              PdfPCell panneValue = new PdfPCell(new Paragraph(garatie.getPanne(), tableBody));
              panneValue.setBorderColor(BaseColor.BLACK);
              panneValue.setPaddingLeft(10);
              panneValue.setHorizontalAlignment(Element.ALIGN_CENTER);
              panneValue.setVerticalAlignment(Element.ALIGN_CENTER);
              panneValue.setBackgroundColor(BaseColor.WHITE);
              panneValue.setExtraParagraphSpace(5f);
              table_garanties.addCell(panneValue);

              PdfPCell accidentValue = new PdfPCell(new Paragraph(garatie.getAccident(), tableBody));
              accidentValue.setBorderColor(BaseColor.BLACK);
              accidentValue.setPaddingLeft(10);
              accidentValue.setHorizontalAlignment(Element.ALIGN_CENTER);
              accidentValue.setVerticalAlignment(Element.ALIGN_CENTER);
              accidentValue.setBackgroundColor(BaseColor.WHITE);
              accidentValue.setExtraParagraphSpace(5f);
              table_garanties.addCell(accidentValue);

          }
			
			
			
			
			
			
			
			
			
			
//			}
//                PdfPCell mobileValue = new PdfPCell(new Paragraph(employee.getService(), tableHeader));
//                mobileValue.setBorderColor(BaseColor.BLACK);
//                mobileValue.setPaddingLeft(10);
//                mobileValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//                mobileValue.setVerticalAlignment(Element.ALIGN_CENTER);
//                mobileValue.setBackgroundColor(BaseColor.WHITE);
//                mobileValue.setExtraParagraphSpace(5f);
//                table.addCell(mobileValue);

//                PdfPCell addressValue = new PdfPCell(new Paragraph(employee.getDirection(), tableHeader));
//                addressValue.setBorderColor(BaseColor.BLACK);
//                addressValue.setPaddingLeft(10);
//                addressValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//                addressValue.setVerticalAlignment(Element.ALIGN_CENTER);
//                addressValue.setBackgroundColor(BaseColor.WHITE);
//                addressValue.setExtraParagraphSpace(5f);
//                table.addCell(addressValue);
//            }
//                On créer un objet table dans lequel on intialise ça taille.
			PdfPTable table2 = new PdfPTable(4);

			table2.setWidthPercentage(50);
			table2.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.setSpacingBefore(10f);
			table2.setSpacingAfter(10);

			// On créer l'objet cellule.
//			PdfPCell cell;

			PdfPCell vehicule = new PdfPCell(new Paragraph("Véhicule Assuré", boldFont));
			vehicule.setBorderColor(BaseColor.BLACK);
			vehicule.setPaddingLeft(5);

			vehicule.setHorizontalAlignment(Element.ALIGN_CENTER);
			vehicule.setVerticalAlignment(Element.ALIGN_CENTER);
			vehicule.setBackgroundColor(BaseColor.ORANGE);
			vehicule.setExtraParagraphSpace(2f);
			vehicule.setColspan(4);
			table2.addCell(vehicule);

//                PdfPCell nnn = new PdfPCell(new Paragraph("Souscripteur", tableHeader));
//                nnn.setRowspan(2);
//                table2.addCell(nnn);
			// contenu du tableau.
//                for(int aw=0;aw<1 ; aw++){   
			
			table2.addCell(new Paragraph("Marque", tableHeader));
			table2.addCell(new Paragraph(client.getMarque(), tableBody_véhicule));
			table2.addCell(new Paragraph("Model", tableHeader));
			table2.addCell("....................");
			table2.addCell(new Paragraph("Immatriculation", tableHeader));
			table2.addCell(new Paragraph(client.getMatricule(), tableBody_véhicule));
			table2.addCell(new Paragraph("Energie", tableHeader));
			table2.addCell(new Paragraph(client.getEssence(), tableBody_véhicule));
			table2.addCell(new Paragraph("Usage", tableHeader));
			table2.addCell(new Paragraph(client.getUsage(), tableBody_véhicule));
			table2.addCell(new Paragraph("Formule", tableHeader));
			table2.addCell(new Paragraph(client.getFormule(), tableBody_véhicule));
			table2.addCell(new Paragraph("Date de mise en circulation", tableHeader));
			table2.addCell(new Paragraph(client.getDatemise(), tableBody_véhicule));
			table2.addCell(new Paragraph("Catégorie de véhicule", tableHeader));
			table2.addCell(new Paragraph(client.getUsage_formule(), tableBody_véhicule));
			table2.addCell(new Paragraph("Nombre places", tableHeader));
			table2.addCell(new Paragraph(client.getNb_places(), tableBody_véhicule));
			table2.addCell(new Paragraph("PTC", tableHeader));
			table2.addCell(client.getTonnage());
//                }

			PdfPTable table3 = new PdfPTable(1);

			table3.setWidthPercentage(50);
			table3.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.setSpacingBefore(10f);
			table3.setSpacingAfter(10);
//                

			PdfPCell contrat = new PdfPCell(new Paragraph(
					"Autres contrats d'assurance / d'assistance couvrant les memes risques", boldFont));
			contrat.setBorderColor(BaseColor.BLACK);
			contrat.setPaddingLeft(5);
			contrat.setHorizontalAlignment(Element.ALIGN_CENTER);
			contrat.setVerticalAlignment(Element.ALIGN_CENTER);
			contrat.setBackgroundColor(BaseColor.ORANGE);
			contrat.setExtraParagraphSpace(2f);
			contrat.setColspan(4);
			table3.addCell(contrat);

			PdfPCell contrat1 = new PdfPCell(new Paragraph("Contrat N° :", tableBody));
			contrat1.setBorderColor(BaseColor.BLACK);
//                emailValue.setPaddingLeft(10);
			contrat1.setHorizontalAlignment(Element.ALIGN_LEFT);
			contrat1.setVerticalAlignment(Element.ALIGN_LEFT);
			contrat1.setBackgroundColor(BaseColor.WHITE);
			contrat1.setExtraParagraphSpace(5f);
			table3.addCell(contrat1);
			PdfPCell contrat2 = new PdfPCell(new Paragraph("Contrat N° :", tableBody));
			contrat2.setBorderColor(BaseColor.BLACK);
//                emailValue.setPaddingLeft(10);
			contrat2.setHorizontalAlignment(Element.ALIGN_LEFT);
			contrat2.setVerticalAlignment(Element.ALIGN_LEFT);
			contrat2.setBackgroundColor(BaseColor.WHITE);
			contrat2.setExtraParagraphSpace(5f);
			table3.addCell(contrat2);

			//Image img2 = Image.getInstance("https://www.wafaassurance.ma/sites/default/files/x2fichier_23x.png");
			Image img2 = Image.getInstance("http://beleefmarokko.com/images/logo3.PNG");
			// img.setAbsolutePosition(450f, 10f);
			img2.setAlignment(Element.ALIGN_RIGHT);
			img2.scaleAbsolute(200, 50);
			
			double mnt=0.0;
			if( client.getPeriode_assure() >= 89 && client.getPeriode_assure() <= 92) {mnt=client.getMontantTTC89_92();}
			if(client.getPeriode_assure() >= 93 && client.getPeriode_assure() <= 184) {mnt=client.getMontantTTC93_184();}
			if(client.getPeriode_assure() > 184) {mnt=client.getMontantttc184();}
			
			Paragraph paragraph4 = new Paragraph(
					"Les circonstances susceptibles d'aggraver les risques sont : ........................................................................"
					+ ".....................................                                 Prime à régler : "+ mnt  + "DH",
					mainFont3);

			paragraph4.setPaddingTop(100);
			paragraph4.setSpacingAfter(10);

			PdfPTable table4 = new PdfPTable(1);

			table4.setWidthPercentage(50);
			table4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.setSpacingBefore(10f);
			table4.setSpacingAfter(10);
//                

			PdfPCell effet = new PdfPCell(new Paragraph(
					"Effet de contrat : " + client.getDate_debut()+   "                "+     "Expiration de contrat : "+ client.getDate_fin(),
					boldFont));
			effet.setBorderColor(BaseColor.BLACK);
			effet.setPaddingLeft(5);
			effet.setHorizontalAlignment(Element.ALIGN_CENTER);
			effet.setVerticalAlignment(Element.ALIGN_CENTER);
			effet.setBackgroundColor(BaseColor.ORANGE);
			effet.setExtraParagraphSpace(2f);
			effet.setColspan(4);
			table4.addCell(effet);

			PdfPCell loi_effet = new PdfPCell(new Paragraph(
					"Les garanties sont acquises aux personnes assurées pour la période allant de la date d'effet"
							+ "j'ausqu'à la date d'éxpération indiquées aux conditions particuliéres du présent contrat."
							+ "\n les prestations d'assistance sont acquise, en cas de panne, au delà de quinze (15) jours suivant la date d'effer du contrat."
							+ "\n la souscripteur certifie l'exactitude et la sincérité des déclarations ci dessus."
							+ "\n le souscripteur déclare quee l'assuré a lu et accepté la clausee relative au<<PROTECTION DES DONNEES A CARACTERE PERSONNEL >>"
							+ "	prévue au paragraphe 6 du titre [ VII dispositions particuliéres ] des conditions générales. :",
					tableBody));
			loi_effet.setBorderColor(BaseColor.BLACK);
//                emailValue.setPaddingLeft(10);
			loi_effet.setHorizontalAlignment(Element.ALIGN_LEFT);
			loi_effet.setVerticalAlignment(Element.ALIGN_LEFT);
			loi_effet.setBackgroundColor(BaseColor.WHITE);
			loi_effet.setExtraParagraphSpace(5f);
			table4.addCell(loi_effet);

			PdfPTable table5 = new PdfPTable(1);

			table5.setWidthPercentage(50);
			table5.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.setSpacingBefore(10f);
			table5.setSpacingAfter(10);
//                

			PdfPCell sinistre = new PdfPCell(new Paragraph("Déclaration de sinistre", boldFont));
			sinistre.setBorderColor(BaseColor.BLACK);
			sinistre.setPaddingLeft(5);
			sinistre.setHorizontalAlignment(Element.ALIGN_CENTER);
			sinistre.setVerticalAlignment(Element.ALIGN_CENTER);
			sinistre.setBackgroundColor(BaseColor.ORANGE);
			sinistre.setExtraParagraphSpace(2f);
			sinistre.setColspan(4);
			table5.addCell(sinistre);

			PdfPCell loi_sinistre = new PdfPCell(new Paragraph(
					" Dés la survenance du sinitre et sauf cas fortuit ou de force majeure,l'assuré doit déclarer"
							+ ",sous peine de déchéance au plus tar dans les (5) cinque jours"
							+ "\n de suvenance,aupré de Wafa Ima assistance,afin qu'elle puisse le faire bénéficier des prestations garanties par le contrat. ",
					tableBody));
			loi_sinistre.setBorderColor(BaseColor.BLACK);
//                emailValue.setPaddingLeft(10);
			loi_sinistre.setHorizontalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setVerticalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setBackgroundColor(BaseColor.WHITE);
			loi_sinistre.setExtraParagraphSpace(5f);
			table5.addCell(loi_sinistre);

			
			
			// Main table
		    PdfPTable mainTable = new PdfPTable(2);
		    mainTable.setWidthPercentage(100.0f);

		    // First table
		    PdfPCell firstTableCell = new PdfPCell();
		    firstTableCell.setBorder(PdfPCell.NO_BORDER);
		    PdfPTable firstTable = new PdfPTable(2);
		    //......... add some cells here ...........
		    firstTableCell.addElement(firstTable);
		    mainTable.addCell(firstTableCell);

		    // Second table
		    PdfPCell secondTableCell = new PdfPCell();
		    secondTableCell.setBorder(PdfPCell.NO_BORDER);
		    PdfPTable secondTable = new PdfPTable(2);
		    //......... add some cells here ...........
		    secondTableCell.addElement(secondTable);
		    mainTable.addCell(secondTableCell);

		    paragraph.add(mainTable);
		    document.add(paragraph);
		    
		    Paragraph prime_regle = new Paragraph(
					"Prime à régler :  OFFERT PAR MATU",
					mainFont3);

		    prime_regle.setPaddingTop(100);
			paragraph4.setSpacingAfter(10);

		    
		    
		    PdfPTable outer = new PdfPTable(2);
		    outer.addCell(table);
		    outer.addCell(table_garanties);
		    outer.setWidthPercentage(100);
		    outer.setHorizontalAlignment(Element.ALIGN_LEFT);
            outer.setSpacingBefore(0f);
			
            

            PdfPTable table_exclusion = new PdfPTable(1);
            table_exclusion.setWidthPercentage(50);
            table_exclusion.setHorizontalAlignment(Element.ALIGN_LEFT);
            table_exclusion.setSpacingBefore(50);
            table_exclusion.setSpacingAfter(10);
			PdfPCell exclusions = new PdfPCell(new Paragraph(
					"Principales Exclusions",
					boldFont));
			exclusions.setBorderColor(BaseColor.BLACK);
			exclusions.setPaddingLeft(5);
			exclusions.setHorizontalAlignment(Element.ALIGN_CENTER);
			exclusions.setVerticalAlignment(Element.ALIGN_CENTER);
			exclusions.setBackgroundColor(BaseColor.ORANGE);
			exclusions.setExtraParagraphSpace(2f);
			exclusions.setColspan(4);
			table_exclusion.addCell(exclusions);
			PdfPCell values_exclusion = new PdfPCell(new Paragraph(
					"Le souscripteur reconnait avoir pris connaissance des exclusions prévues au niveau de l'articlee V(EXCLUSIONS APPLICABLESAU PRESENT CONTRAT)"
					+ "des conditions générales notamment:\n"
					+ "- Les dommages subis par le véhicule assuré lorsque le condicteur conduit sous l'emprise de l'alcool (PV de police ou de gendaremerie faisant foi)\n"
					+ "- Les frais de réparation des véhicules;\n"
					+ "- Les frais engagés pour la consommation de carburant et les péages,lié au véhcule de remplcement;",
					tableBody));
			values_exclusion.setBorderColor(BaseColor.BLACK);
			values_exclusion.setHorizontalAlignment(Element.ALIGN_LEFT);
			values_exclusion.setVerticalAlignment(Element.ALIGN_LEFT);
			values_exclusion.setBackgroundColor(BaseColor.WHITE);
			values_exclusion.setExtraParagraphSpace(5f);
			table_exclusion.addCell(values_exclusion);
			
            PdfPTable table_reception = new PdfPTable(1);
            table_exclusion.setWidthPercentage(50);
            table_exclusion.setHorizontalAlignment(Element.ALIGN_LEFT);
            table_exclusion.setSpacingBefore(10f);
            table_exclusion.setSpacingAfter(10);
			PdfPCell reception = new PdfPCell(new Paragraph(
					"Réception des conditions générales",
					boldFont));
			reception.setBorderColor(BaseColor.BLACK);
			reception.setPaddingLeft(5);
			reception.setHorizontalAlignment(Element.ALIGN_CENTER);
			reception.setVerticalAlignment(Element.ALIGN_CENTER);
			reception.setBackgroundColor(BaseColor.ORANGE);
			reception.setExtraParagraphSpace(2f);
			exclusions.setColspan(4);
			table_reception.addCell(exclusions);
			PdfPCell values_reception = new PdfPCell(new Paragraph(
					"(Cette case doit etre renseignée au mement de souscrption du présent contrat, de facon manuscrite par le souscripteur,"
					+ "pour attester qu'il a recu une copie des conditions générales de contrat)\n"
					+ "................................................................................................................... ",
					tableBody));
			values_reception.setBorderColor(BaseColor.BLACK);
			values_reception.setHorizontalAlignment(Element.ALIGN_LEFT);
			values_reception.setVerticalAlignment(Element.ALIGN_LEFT);
			values_reception.setBackgroundColor(BaseColor.WHITE);
			values_reception.setExtraParagraphSpace(5f);
			table_reception.addCell(values_reception);
            
			
			    PdfPTable outer2 = new PdfPTable(2);
			    outer2.addCell(table2);
			    outer2.addCell(table_exclusion);
			    outer2.setWidthPercentage(100);
			    outer2.setHorizontalAlignment(Element.ALIGN_LEFT);
			    outer2.setSpacingBefore(0f);
			    
			    PdfPTable outer3 = new PdfPTable(2);
			    outer3.addCell(table3);
			    outer3.addCell(table_reception);
			    outer3.setWidthPercentage(100);
			    outer3.setHorizontalAlignment(Element.ALIGN_LEFT);
			    outer3.setSpacingBefore(0f);
            
			    
			    
			    PdfPTable table_signature = new PdfPTable(2);// column amount
			    table_signature.setWidthPercentage(50);
			    table_signature.setHorizontalAlignment(Element.ALIGN_LEFT);
			    table_signature.setSpacingBefore(0f);
				float[] columnWidths2 = {7, 3};
				table_signature.setWidths(columnWidths2);
				PdfPCell date_signe = new PdfPCell(new Paragraph("Fait à : "+ client.getVille_cient() +"\n le : "+dtf.format(now), boldFont));
				date_signe.setBorderColor(BaseColor.BLACK);
				date_signe.setHorizontalAlignment(Element.ALIGN_CENTER);
				date_signe.setVerticalAlignment(Element.ALIGN_CENTER);
				date_signe.setBackgroundColor(BaseColor.ORANGE);
				date_signe.setExtraParagraphSpace(2f);
				table_signature.addCell(date_signe);

				PdfPCell   signature= new PdfPCell(new Paragraph("Signature Wafa IMA Assistance", boldFont));
				signature.setBorderColor(BaseColor.BLACK);
				signature.setHorizontalAlignment(Element.ALIGN_CENTER);
				signature.setVerticalAlignment(Element.ALIGN_CENTER);
				signature.setBackgroundColor(BaseColor.ORANGE);
				signature.setExtraParagraphSpace(5f);
				table_signature.addCell(signature);
				

//	            for (Employer employee : employees){
				PdfPCell valuedate_signe = new PdfPCell(new Paragraph(
						"La durée de présent contrat est entre  : " + client.getDate_debut()+  " et "+ client.getDate_fin() +  "\n Signature de sousripteur\n précédée par la mention << Lu et Approuvé >>", tableHeader));
				valuedate_signe.setBorderColor(BaseColor.BLACK);
				valuedate_signe.setPaddingTop(20);
				valuedate_signe.setHorizontalAlignment(Element.ALIGN_CENTER);
				valuedate_signe.setVerticalAlignment(Element.ALIGN_CENTER);
				valuedate_signe.setBackgroundColor(BaseColor.WHITE);
				valuedate_signe.setExtraParagraphSpace(7f);
				table_signature.addCell(valuedate_signe);

				PdfPCell valuedate_signatue = new PdfPCell(new Paragraph("",tableBody));
				valuedate_signatue.setBorderColor(BaseColor.BLACK);
				valuedate_signatue.setHorizontalAlignment(Element.ALIGN_LEFT);
				valuedate_signatue.setVerticalAlignment(Element.ALIGN_RIGHT);
				valuedate_signatue.setBackgroundColor(BaseColor.WHITE);
				valuedate_signatue.setExtraParagraphSpace(5f);
				table_signature.addCell(valuedate_signatue);

				 PdfPTable outer4 = new PdfPTable(2);
				 outer4.addCell(table4);
				 outer4.addCell(table_signature);
				 outer4.setWidthPercentage(100);
				 outer4.setHorizontalAlignment(Element.ALIGN_LEFT);
				 outer4.setSpacingBefore(0f);
            
            
            
            
            
		    document.add(outer);
		    document.add(outer2);
		    document.add(outer3);
		    document.add(paragraph4);
		    document.add(outer4);
		    document.add(firstTableCell);document.add(secondTableCell);
			//document.add(table);
			//document.add(table2);
//			document.add(table3);
			
//			document.add(table4);
			document.add(table5);
			//document.add(table_garanties);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean createExcell(List<WAFAIMA_Contrats> contrats, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {

		String filePath = context.getRealPath("/resources/report");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if (!exists) {
			new File(filePath).mkdirs();
		}
		try {
			FileOutputStream outputStream = new FileOutputStream(file + "/" + "contrats" + ".xls");
			workbook = new HSSFWorkbook();
			HSSFSheet workSheet = workbook.createSheet("contrats");
			workSheet.setDefaultColumnWidth(30);
//			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
//			headerCellStyle.setFillBackgroundColor(HSSFColor.BLUE.index);
//            headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//			HSSFRow headerRow = workSheet.createRow(0);
//			HSSFCell nomclient1 = headerRow.createCell(0);
//			nomclient1.setCellStyle(headerCellStyle);
//			HSSFCell matricule1 = headerRow.createCell(1);
//			matricule1.setCellStyle(headerCellStyle);
//            HSSFCell attestation1 = headerRow.createCell(2);
//            attestation1.setCellStyle(headerCellStyle);
//            HSSFCell formule1 = headerRow.createCell(3);
//            formule1.setCellStyle(headerCellStyle);
			
			 HSSFFont headerFont = workbook.createFont();
		      headerFont.setBold(true);
		      headerFont.setColor(IndexedColors.BLUE.getIndex());
		      headerFont.setItalic(false);
		      
		   
		      CellStyle headerCellStyle = workbook.createCellStyle();
		      headerCellStyle.setFont(headerFont);

		      // Header
//		      for (int col = 0; col < 1; col++) {

		          // Row for Header
		        Row headerRow = workSheet.createRow(0);
		        Cell cell = headerRow.createCell(0);
		        cell.setCellStyle(headerCellStyle);

		        cell.setCellValue("Client");
		        cell = headerRow.createCell(1);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue("Matricule");
		        cell = headerRow.createCell(2);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue("Attestation");
		        cell = headerRow.createCell(3);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue("Formule");
		        
//		        cell.setCellValue("bb");
//		        cell.setCellValue("cc");
//		        cell.setCellValue("ddddd");
		
		   
//		      }
            
			Integer i = 1;

			for (WAFAIMA_Contrats contrat : contrats) {
				HSSFRow bodyRow = workSheet.createRow(i);

				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

				HSSFCell nomclient = bodyRow.createCell(0);
				nomclient.setCellValue(contrat.getNom_client());
				nomclient.setCellStyle(bodyCellStyle);

				HSSFCell matricule = bodyRow.createCell(1);
				matricule.setCellValue(contrat.getMatricule());
				matricule.setCellStyle(bodyCellStyle);

				HSSFCell attestation = bodyRow.createCell(2);
				attestation.setCellValue(contrat.getAttestation());
				attestation.setCellStyle(bodyCellStyle);

				HSSFCell formule = bodyRow.createCell(3);
				formule.setCellValue(contrat.getFormule());
				formule.setCellStyle(bodyCellStyle);

				i++;
			}

			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}
}
