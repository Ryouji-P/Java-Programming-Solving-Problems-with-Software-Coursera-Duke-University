import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData
{

    public CSVRecord coldestHourInFile (CSVParser parser) 
    {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    
    public void testColdestHourInFile() 
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord smallest = coldestHourInFile (parser);
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEDT"));
    }
    
    public String fileWithColdestTemperature() 
    {
        CSVRecord coldestSoFar = null;
        String filename = "";
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRecord = coldestHourInFile(fr.getCSVParser());
            if(coldestSoFar == null) {
            coldestSoFar = currentRecord;
        }
        else {
            double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if (currentTemp < smallestTemp) {
                coldestSoFar = currentRecord;
                filename = f.getPath();
            }
        }
            //filename = f.getPath();
        }
        return filename;
    }
    
    public void testFileWithColdestTemperature() {
        String filename = fileWithColdestTemperature();
        FileResource fr = new FileResource(filename);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file "+ filename);
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
        System.out.println("All the Temperature on the coldest day were:");
        for (CSVRecord currentRow : fr.getCSVParser()) {
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
        }
    }
    
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar)
    {
        if(smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currentTemp < smallestTemp && currentTemp != -9999) {
                smallestSoFar = currentRow;
                }
        }
        return smallestSoFar;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
    
    CSVRecord lowestSoFar = null;
    
    for (CSVRecord currentRow : parser) {
    
            lowestSoFar = getSmallestHumidityOfTwo(currentRow, lowestSoFar);
    }

    return lowestSoFar;
    }
    
    public CSVRecord getSmallestHumidityOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {

        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
    }
    else {
            if (currentRow.get("Humidity").length() != 3){
            double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));

            if (currentTemp < smallestTemp && currentTemp != -9999) {
                smallestSoFar = currentRow;
            }
            }
        }
    return smallestSoFar;
    }
    
    public void testlowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest humidity was " + smallest.get("Humidity") + " at " + smallest.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getSmallestHumidityOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser){
        double sum=0;
        double avarage =0;
        int count = 1;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            sum += currentTemp;
            avarage =sum/count;
            count++;
        }
        return avarage;
    }
    
    public void  testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avarage = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avarage);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value ){
        
        double sum=0;
        double avarage =0;
        int count = 1;
        for (CSVRecord currentRow : parser) {
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                if(currentHumidity>=value){
                    sum += currentTemp;
                    avarage =sum/count;
                    count++;
                }
        }   
        return avarage;
    }

    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avarage = averageTemperatureWithHighHumidityInFile(parser,80);
        if(avarage==0)System.out.println("No temperatures with that humidity");
        else System.out.println("Average temperature when high Humidity is " + avarage);
    }
    
}
