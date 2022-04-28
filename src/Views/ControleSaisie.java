/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hsine
 */
public class ControleSaisie {
    public boolean testnomprenom(String nom)
	{
		//************************ nom et prenom contiennent que des alphabets ***************************
		String masque = "^[a-zA-Z]+[a-z]";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(nom);
		return controler.matches();
	}
	public boolean testadresse(String adresse)
	{
		//************************ nom et prenom contiennent que des alphabets ***************************
		String masque = "^[a-zA-Z]+[a-z]";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(adresse);
		return controler.matches();
	}
//
//	public int testdateEMB_dateEXP(String d1, String d2) throws ParseException {
//		******************* test date embauche inférieur a la date expiration*****************************
//		java.util.Date dateEMB = convert(d1);
//		java.util.Date dateEXP = convert(d2);
//		return dateEMB.compareTo(dateEXP);
//	}
//
//	/**
//	 * ************************ Password 8
//	 * chiffres**************************************************************
//	 */
//	public boolean testpassword(String password) {
//		return (password.length() >= 8);
//	}
//        
//        
//        
//        
//        
//********************************Numero telephone contient 8 chiffres **************************************/
//
//	public boolean GSM(String num) {
//		String masque = "[0-9]{8}$";
//		Pattern pattern = Pattern.compile(masque);
//		Matcher controler = pattern.matcher(num);
//		if (controler.matches()) {
//			return true;
//		}
//		return false;
//	}
//********************** test format mail *****************************************************************************
//
//	public boolean mailformat(String mail) {
//		String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
//				+ "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
//		Pattern pattern = Pattern.compile(masque);
//		Matcher controler = pattern.matcher(mail);
//		return controler.matches();
//
//	}
// *********************************** test login format ************************************************************
//
//	public boolean testUsername(String login) {
//		String masque = "^[a-zA-Z]+[a-zA-Z0-9]";
//		Pattern pattern = Pattern.compile(masque);
//		Matcher controler = pattern.matcher(login);
//		return controler.matches();
//	}
//********************************** test login inutilisé ***********************************************************
//
//	public boolean loginvalide(String login) {
//		boolean test = true;
//		String req = "select * from user ";
//		PreparedStatement preparedStatement;
//		try {
//			preparedStatement = connection.prepareStatement(req);
//
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
//				String LoginRecup = resultSet.getString("login");
//				if (login.equals(LoginRecup)) {
//					test = false;
//				}
//			}
//
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//		return test;
//	}
//************************************* convertion de String à Date  ********************************
//
//	public java.util.Date convert(String date) throws ParseException {
//
//		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
//		java.util.Date date1 = sdf1.parse(date);
//
//		return date1;
//	}
//        
//        
//         *********************************** test Adresse format ************************************************************
//
//	public boolean testAdr(String adr) {
//		String masque = "^[a-zA-Z]+[a-zA-Z0-9-\" \" ]+";
//		Pattern pattern = Pattern.compile(masque);
//		Matcher controler = pattern.matcher(adr);
//		return controler.matches();
//	}
//        
//        ********************************Code Postale contient 4 chiffres **************************************/
//
	public boolean Num(String num) {
		String masque = "[0-99999999]";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(num);
		if (controler.matches()) {
			return true;
		}
		return false;}
                    public boolean flaotd(String num) {
		String masque = "[0-9]"+"."+"[0-9]";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(num);
		
		return controler.matches();
    


   
}}