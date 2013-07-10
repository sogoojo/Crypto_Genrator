package org.etz.core;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.EditField;

public class FieldValidator {

	public static final int EXACT = 0;
	public static final int GREATER_THAN =1;
	public static final int LESS_THAN =2;
	
	private StringBuffer errs;

	public FieldValidator() {
		errs = new StringBuffer();
	}
	
	private boolean isValidAmt(String text)
	{
		if(text != null)
		{
			try{
				Double.valueOf(text);
				return true;
			}
			catch(Exception ex)
			{
				return false;
			}
		}
		return false;
	}

	private boolean isValidEmail(String text) {
		if (text.lastIndexOf('@') < 0) {
			return false;
		}
		if (text.lastIndexOf('.') > 0) {
			int ind = text.lastIndexOf('.');
			if (text.substring(ind).length() > 2)
				return true;
		}

		return false;
	}
	
	public void compareValues(String label1, DateField fld1, String label2,DateField fld2, int style)
	{
		long date1 = fld1.getDate();
		long date2 = fld2.getDate();
		if(style == GREATER_THAN){
			if(date1 < date2)
				errs.append(label1 + " date cannot be earlier than "+label2+" date\r\n");
		}
						
	}
	
	public void checkMoney(BasicEditField fld)
	{
		if(!isValidAmt(fld.getText()))
			errs.append("Amount figure is invalid\r\n");
	}
	
	public void compareValues(String label1, BasicEditField fld1, String label2,BasicEditField fld2, int style)
	{
		if(style == EXACT){
			if(!(fld1.getText().equalsIgnoreCase(fld2.getText())))
				errs.append("contents of " +label1+ " and " + label2 + " must be the same\r\n");
		}
		else{
			if((fld1.getText().equalsIgnoreCase(fld2.getText())))
				errs.append("contents of " +label1+ " and " + label2 + " cannot be the same\r\n");
		}
										
	}
	

	public void checkValidEmail(String label, BasicEditField fld) {
		boolean valid = isValidEmail(fld.getText());
		if (!valid)
			errs.append(label + " is not a valid email \r\n");
	}

	public void checkLenght(String label, BasicEditField fld, int lenght, int style) {
		boolean valid = false;
		int fldlen = fld.getText().length();
		if(style == EXACT){ valid = fldlen  == lenght;}
		if(style == GREATER_THAN){valid = fldlen > lenght;}
		if(style == LESS_THAN){valid = fldlen < lenght;}
		
		if (!valid && (style == EXACT))
			errs.append(label + " is invalid expects " + lenght
					+ " characters\n");
		if(!valid && (style == GREATER_THAN))
		{
			errs.append(label + " is invalid expects more than " + lenght
					+ " characters\n");
		}
		if(!valid && (style == LESS_THAN))
		{
			errs.append(label + " is invalid expects less than " + lenght
					+ " characters\n");
		}
		
	}
	
	public void checkExists(String label, BasicEditField fld)
	{
		if(fld.getText() == null || "".equalsIgnoreCase(fld.getText())){
			errs.append(label + " cannot be empty\n");
		}
	}

	public boolean isValid() {
		return errs.toString().trim().equalsIgnoreCase("");
	}

	public String getErrorSummary() {
		return errs.toString();
	}

	public void clear() {
		errs.delete(0, errs.toString().length());
	}

}
