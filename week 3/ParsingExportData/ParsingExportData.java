import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData 
{
    //This method returns a string of information about the country or returns “NOT FOUND” if there is no information about the country. 
    public String countryInfo (CSVParser parser, String country)
    {
        String info = "NOT FOUND";
        for (CSVRecord record : parser) {
            String getCountry = record.get("Country");
            if (getCountry.contains(country)) {
                String getExport = record.get("Exports");
                String getValue = record.get("Value (dollars)");
                info = getCountry + ":" + getExport + ":" + getValue;
            }
        }
        return info;
    }
    
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2)
    {
        for (CSVRecord record : parser) {
            String getExport = record.get("Exports");
            if (getExport.contains(exportItem1) && getExport.contains(exportItem2)) {
                String getCountry = record.get("Country");
                System.out.println(getCountry);
            }
        }
        
    }
    
    public int numberOfExporters (CSVParser parser, String exportItem) 
    {
        int count = 0;
        for (CSVRecord record : parser) {
            String getExport = record.get("Exports");
            if (getExport.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters (CSVParser parser, String amount) 
    {
        String countryValue = "";
        for (CSVRecord record : parser) {
            String getValue = record.get("Value (dollars)");
            if (getValue.length() > amount.length()) {
                String getCountry = record.get("Country");
                countryValue = getCountry + " " + getValue;
                System.out.println(countryValue);
            }
        }
    }

    //test
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String country = countryInfo(parser, "Germany");
        System.out.println(country);
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        parser = fr.getCSVParser();
        int count = numberOfExporters(parser, "gold");
        System.out.println(count);
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999");
    }
}
