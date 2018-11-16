import java.io.*;
import java.util.*;

/**
 * Converts a Focus Tree into directly pasteable localisation files for the Game Hearts of Iron IV. For "The New Order" and "The Old Order"
 * @author Shizophren 
 * @version 2018-11-14
 */

public class Localisation {
    public static void main(String[] args) {
		try {
			FileOutputStream writer = new FileOutputStream("localisation.txt");
			writer.write(("").getBytes());
			writer.close();
		}
		catch (FileNotFoundException ex) {
			System.out.println("There has been a problem with the file localisation.txt");
		}
		catch(IOException ex) {
			System.out.println("There has been a problem with the file localisation.txt");
		}
        Scanner tag;
        tag = new Scanner(System.in);
		String tagstr;
        do {
			System.out.println("Please insert your nations tag");
            tagstr = tag.next();
        }while(tagstr.length() != 3);
        int priority = 0;
        do {
			System.out.println("Please insert your wanted priority");
            priority = tag.nextInt();
        }while(priority > 9 && priority < -1);
		boolean b = false;
        tagstr = tagstr.toUpperCase();
        if(reWrite(tagstr,priority,b) == true);
        else {
            System.out.println("The Text Document is not valid");
            System.exit(0);
        }
		System.out.println("The Code should be written in localisation.txt");
    }
	public static boolean reWrite(String tag, int priority, boolean b) {
          String fileName = "focuses.txt";
            String line = null;
			String locline = "";
			String name = "";
			String descline = "";
			String desc = "";
			Scanner sc = new Scanner(System.in);
			boolean be = false;
			System.out.println("Please insert if you want Descriptions (y/n)");
			desc = sc.next();
			if(desc.charAt(0) == 'y') be = true;
            try {
                FileReader fileReader = 
                    new FileReader(fileName);
                BufferedReader bufferedReader = 
                    new BufferedReader(fileReader);
				BufferedWriter writer;
                while((line = bufferedReader.readLine()) != null) {
                    if(hasID(line)) {
						line = FocusName(line);
						name = FocusLoc(line,tag);
						locline = line + ":" + priority + " \"" + name + "\"";
						writer = new BufferedWriter(new FileWriter("localisation.txt", true));
						writer.append(locline + "\n");
						if(be) {
							descline = line + "_desc:0 \"\"";
							writer.append(descline + "\n");
						}
						writer.close();
					}
                }
                bufferedReader.close();
          }
          catch(FileNotFoundException ex) {
              System.out.println(
                  "Unable to open file '" + 
                  fileName + "'");
          }
         catch(IOException ex) {
              System.out.println(
                  "Error reading file '" 
                  + fileName + "'");
          } 
        return true;
    }
	public static boolean hasID(String s) {
		if (s.contains("relative_position_id")) return false;
		if (s.contains("cancel_if_invalid")) return false;
		if (s.contains("continue_if_invalid")) return false;
		if (s.contains(".")) return false;
		if (s.contains("id =")) return true;
		if (s.contains("id=")) return true;
		if (s.contains("id  =")) return true;
		if (s.contains("id\t=")) return true;
		return false;
	}
	public static String FocusName(String s) {
		s = s.replaceAll("\\s+","");
		s = s.replaceAll(" ","");
		s = s.replaceAll("id=","");
		if(s.contains("#")) s = s.substring(0, s.indexOf('#'));
		return s;
	}
	public static String FocusLoc(String s, String tagstr) {
		s = s.replaceAll("_"," ");
		s = s.replaceAll(tagstr,"");
		s = s.substring(1, s.length());
		return s;
	}
}