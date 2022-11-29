import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames
{
   
    public void totalBirths()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int totalGirls = 0;
        int totalBoys = 0;
        int totalBirths = 0;
        
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals("F")) {
                totalGirls += 1;
            }
            else if(rec.get(1).equals("M")) {
                totalBoys += 1;
            }
            totalBirths += 1;
        }
        
        System.out.println("Number of girls names is " + totalGirls);
        System.out.println("Number of boys names is " + totalBoys);
        System.out.println("Total names is " + totalBirths);
    }
    
    public int getRank (int year, String name, String gender)
    {
        int rank = 0;
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName (int year, int rank, String gender)
    {
        String name = "";
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int num = 0;
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                num +=1;
                if (num == rank) {
                    name = rec.get(0);
                    return name;
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        if (rank != -1) {
            String newname = getName(newYear, rank, gender);
            System.out.println(name+" born in " + year + " would be "+ newname+" if she was born in " + newYear);
        }
        else {
            System.out.println("NO NAME");
        
        }
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        String yob = null;
        int year = 0;
        int highestYear = 0;
        for (File f:dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fName = f.getName();
            yob = fName.substring(3,7);
            year = Integer.parseInt(yob);
            int currentRank = getRank2(year,name, gender,fr);
            if (highestRank == 0) {
                highestRank = currentRank;
            }else if (currentRank != -1 && currentRank < highestRank) {
                
                    highestRank = currentRank;
                    highestYear = year;
                
            }  

        }
        return highestYear;
    }
    
    public int getRank2(int year, String name, String gender, FileResource fr) {
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double totalRank = 0;
        String yob = null;
        int year = 0;
        int count = 0;
        for (File f:dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fName = f.getName();
            yob = fName.substring(3,7);
            year = Integer.parseInt(yob);
            int currentRank = getRank2(year,name, gender,fr);
            if (currentRank != -1) {
                totalRank += currentRank;
                count += 1; 
            }
            System.out.println(totalRank);
            
        }
        
        if (totalRank == 0) {
            return -1.0;
        }
        double averageRank = totalRank/count;
        return averageRank;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource();
        int totalBirths = 0;
        int rank = getRank(year, name, gender);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
		rank--;
		if (rank > 0) {
		    totalBirths += Integer.parseInt(rec.get(2));
                }
            }
        }
        return totalBirths;
    }
}
