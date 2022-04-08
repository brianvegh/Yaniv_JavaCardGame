package yaniv.controller;

import javax.swing.*;
import java.util.Scanner;

class InputValid {
    public static String getString(String prompt, String title, String errorMessage) {
      boolean invalid;
      String result = ""; 
      do {
         invalid = false;
         try {
            result = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE);
            if (result.length() == 0) throw new Exception("Input is blank.");         
         }
         catch (Exception e) {
            invalid = true;         
            JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+errorMessage, "ERROR", 
               JOptionPane.ERROR_MESSAGE);
         }
      } while (invalid);
      return result;
    }


    public static String getString(String prompt, String title, String errorMessage, String defVal) {
      boolean invalid;
      String result = ""; 
      do {
         invalid = false;
         try {
            result = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE);
            if (result == null) result = defVal;         
            else if (result.length() == 0) throw new Exception("Input is blank.");         
         }
         catch (Exception e) {
            invalid = true;         
            JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+errorMessage, "ERROR", 
               JOptionPane.ERROR_MESSAGE);
         }
      } while (invalid);
      return result;
    }


    public static int getInt(String prompt, String title, String errorMessage) {
      boolean invalid;
      String input = ""; 
      int result=0;
      do {
         invalid = false;
         try {
            input = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE);
            if (input.length() == 0) throw new Exception("Input is blank.");
            result = Integer.parseInt(input);
         }
         catch (Exception e) {
            invalid = true;         
            JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+errorMessage, "ERROR", 
               JOptionPane.ERROR_MESSAGE);
         }
      } while (invalid);
      return result;
    }

    public static int getInt(String prompt, String title, String errorMessage, String def) {
      boolean invalid;
      String input = ""; 
      int result=0;
      do {
         invalid = false;
         try {
            input = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE);
            if (input == null) input = def;
            else if (input.length() == 0) throw new Exception("Input is blank.");
            result = Integer.parseInt(input);
         }
         catch (Exception e) {
            invalid = true;         
            JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+errorMessage, "ERROR", 
               JOptionPane.ERROR_MESSAGE);
         }
      } while (invalid);
      return result;
    }


    public static int getInt(String prompt, String title, String errorMessage, int min, int max) {
      boolean invalid;
      String input = ""; 
      int result=0;
      do {
         invalid = false;
         try {
            input = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE);
            if (input.length() == 0) throw new Exception("Input is blank.");
            result = Integer.parseInt(input);
            if (result > max) throw new Exception("Input must be less than or equal to "+max);
            if (result < min) throw new Exception("Input must be greater than or equal to "+min);
         }
         catch (Exception e) {
            invalid = true;         
            JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+errorMessage, "ERROR", 
               JOptionPane.ERROR_MESSAGE);
         }
      } while (invalid);
      return result;
    }


    public static double getDouble(String prompt, String title, String errorMessage) {
      boolean invalid;
      String input = ""; 
      double result=0.0;
      do {
         invalid = false;
         try {
            input = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE);
            if (input.length() == 0) throw new Exception("Input is blank.");
            result = Double.parseDouble(input);
         }
         catch (Exception e) {
            invalid = true;         
            JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+errorMessage, "ERROR", 
               JOptionPane.ERROR_MESSAGE);
         }
      } while (invalid);
      return result;
    }
    
    public static double getDouble(String prompt, String title, String errorMessage, String defVal) {
      boolean invalid;
      String input = ""; 
      double result=0.0;
      do {
         invalid = false;
         try {
            input = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE);
            if (input == null) input = defVal;
            if (input.length() == 0) throw new Exception("Input is blank.");
            result = Double.parseDouble(input);
         }
         catch (Exception e) {
            invalid = true;         
            JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+errorMessage, "ERROR", 
               JOptionPane.ERROR_MESSAGE);
         }
      } while (invalid);
      return result;
    }
    public static char getChar(String prompt, String title, String errorMessage, String validChars) {
      boolean invalid;
      validChars = validChars.toUpperCase();
      String result = ""; 
      char cres=' ';
      do {
         invalid = false;
         try {
            result = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE);
            if (result.length() == 0) throw new Exception("Input is blank.");         
            cres = Character.toUpperCase(result.charAt(0));
            if (validChars.indexOf(cres) < 0) throw new Exception("Not a valid character!  Use one of the following: "+validChars);         
         }
         catch (Exception e) {
            invalid = true;         
            JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+errorMessage, "ERROR",
               JOptionPane.ERROR_MESSAGE);
         }
      } while (invalid);
      return cres;
    }
    
    public static boolean yesOrNo(String prompt, String title) {
        boolean check = false;
        int confirmed = JOptionPane.showConfirmDialog(null, prompt, title,JOptionPane.YES_NO_OPTION);
        //if (input == null) check = false;
        if(confirmed == JOptionPane.YES_OPTION) {
            check = true;
        }
        return check;
    }

    private static boolean  getBoolean() {
        Scanner key = new Scanner(System.in);
        boolean result;
        boolean invalid;
        String temp;
        do {
            temp = key.nextLine();
            invalid = false;
            try {
                if (temp.length() == 0) throw new Exception("Input is blank.");
                if (!temp.toLowerCase().startsWith("y") && !temp.toLowerCase().startsWith("n")) throw new Exception("Enter \"Yes\" or \"No\"") ;
            }
            catch (Exception e) {
                invalid = true;
                System.out.println(e.getMessage()+"\n");
            }
        } while (invalid);
        result = (temp.equalsIgnoreCase("y"));
        return result;
    }
    public static boolean  getBoolean(String message) {//accepts sysOut string
        System.out.println(message);
        return getBoolean();
    }

    private static char  getChar() {
        Scanner key = new Scanner(System.in);
        boolean invalid;
        String validChars ="[a-zA-Z]";
        char result =' ';
        do {

            invalid = false;
            try {
                String temp = key.nextLine();
                if (temp.length() == 0) throw new Exception("Input is blank.");
                result = temp.charAt(0);
                if (!(result>64) && !(result<91) || (!(result>96) && !(result<123)))  throw new Exception("Not a valid character!  Use one of the following: "+validChars);
            }
            catch (Exception e) {
                invalid = true;
                System.out.println(e.getMessage()+"\n");
            }
        } while (invalid);
        return result;
    }

    public static char  getChar(String message) {
        System.out.println(message);
        return getChar();
    }
}