package cleo.primer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MaxMindWorldCitiesXML converts <a href="http://www.maxmind.com/app/worldcities">MaxMind</a> free world cities data to XML.
 * 
 * <pre>
 * Country,City,AccentCity,Region,Population,Latitude,Longitude
 * ad,aixirivali,Aixirivali,06,,42.4666667,1.5
 * us,winchester,Winchester,WY,,43.8600000,-108.1600000
 * </pre>
 * 
 * @author jwu
 * @since 01/15, 2012
 */
public class MaxMindWorldCitiesXML {
    public static String XML_CHARSET_NAME = "UTF-8";
    public static int XML_NUM_OF_ELEMENTS = 100000;
    
    /**
     * XMLize a String.
     */
    public static String xmlize(String s) {
        return s.replaceAll("\"|\\a|\\e|\\f|\\v", "")
                .replaceAll("&", "&amp;")
                .replaceAll("'", "&apos;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\\s+", " ")
                .trim();
    }
    
    /**
     * Generate MaxMind world cities in XML files.
     * 
     * <pre>
     * java MaxMindWorldCitiesXML &lt;WorldCitiesTextFile&gt; &lt;NumOfElementsPerXML&gt;
     * java MaxMindWorldCitiesXML world-cities.txt 100000
     * </pre>
     */
    public static void main(String[] args) throws IOException {
        File txtFile = new File(args[0]);
        String xmlPath = txtFile.getCanonicalPath().replaceAll(".txt$", "");
        
        int numElementsPerXML = XML_NUM_OF_ELEMENTS;
        try {
            numElementsPerXML = Integer.parseInt(args[1]);
        } catch(Exception e) {
            numElementsPerXML = XML_NUM_OF_ELEMENTS;
        }
        
        BufferedReader r = new BufferedReader(new FileReader(txtFile));
        
        PrintWriter w = null;
        
        int id = 0;
        String line = r.readLine();
        while((line = r.readLine()) != null) {
            if(id % numElementsPerXML == 0) {
                if (w != null) {
                    w.println("</element-list>");
                    w.flush();
                    w.close();
                }
                
                int xmlId = id / numElementsPerXML + 1;
                File xmlFile = new File(xmlPath + "." + xmlId + ".xml");
                w = new PrintWriter(xmlFile, XML_CHARSET_NAME);
                w.println("<?xml version=\"1.0\" encoding=\"" + XML_CHARSET_NAME + "\"?>");
                w.println("<element-list>");
            }
            
            // Split CSV
            String[] parts = line.split(",");
            
            String country = parts[0];
            String[] terms = parts[1].replaceAll("\"", "").split(",|-|\\s+");
            String name = parts[2];
            String region = parts[3];
            float score = parts[4].length() == 0 ? 0 : (Long.parseLong(parts[4]) / 1000000f);
            
            w.println("  <element>");
            w.printf ("    <id>%d</id>%n", ++id);
            w.printf ("    <name>%s (%s)</name>%n", xmlize(name), country.toUpperCase());
            w.printf ("    <country>%s</country>%n", country);
            w.printf ("    <region>%s</region>%n", region);
            w.printf ("    <latitude>%s</latitude>%n", parts[5]);
            w.printf ("    <longitude>%s</longitude>%n", parts[6]);
            w.printf ("    <score>%f</score>%n", score);
            for(String t : terms) {
                if(t.length() > 0) {
                    w.printf("    <term>%s</term>%n", xmlize(t));
                }
            }
            w.printf ("    <term>%s</term>%n", country);
            w.println("  </element>");
        }
        
        if (w != null) {
            w.println("</element-list>");
            w.flush();
            w.close();
        }
        
        r.close();
    }
}
