/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.MyDb;


public class pdf {
public void imprimer(){
try{
String file_name="D:\\reloua.pdf";
Document document=new Document();
PdfWriter.getInstance(document, new FileOutputStream(file_name));
document.open();
MyDb obJDBConnection=new MyDb();
Connection connection=obJDBConnection.getCnx();
PreparedStatement ps=null;
ResultSet rs=null;

String query="select * from facture";
ps=connection.prepareStatement(query);
rs=ps.executeQuery();
Paragraph para=new Paragraph("Welcome To Reloua\n"
 + "Liste Des Factures\n");
document.add(para);
while(rs.next()){
Paragraph parag=new Paragraph(rs.getInt("id")+" "+rs.getInt("commande_id")+" "+rs.getString("date_fact")+" "+rs.getInt("remise")+" "+rs.getInt("total"));
document.add(parag);
document.add(new Paragraph(" "));

}

document.add(Image.getInstance("C:\\reloua.png"));
document.close();
System.out.println("PDF");
}catch (Exception e){
System.err.println(e);
}
}
}