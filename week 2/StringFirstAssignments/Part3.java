
/**
 * Write a description of class Part3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part3
{
    // instance variables - replace the example below with your own
    
    public String twoOccurrences (String stringa, String stringb)
    {
        String result = "false";//default false
        if (stringb.indexOf(stringa) != -1){//first occurrence test
            int pos = stringb.indexOf(stringa);//fisrt occurrence position
            if (stringb.indexOf(stringa, pos+1) != -1){//second occurrence test
                result = "true";
            }
        }
        return result;
    }
    
    public String lastPart(String stringa, String stringb)
    {
        String result = stringb;//default not included, show stringb
        int pos = stringb.indexOf(stringa);
        if (pos != -1){
            result = stringb.substring(pos+stringa.length());
        }
        return result;
    }
    
    public void testing ()
    {
        String stringa = "by";
        String stringb = "A story by Abby Long";
        String test = twoOccurrences(stringa,stringb);
        System.out.println("\"" + stringa + "\" in \"" + stringb + "\" is " +test);
        
        stringa = "a";
        stringb = "banana";
        test = twoOccurrences(stringa,stringb);
        System.out.println("\"" + stringa + "\" in \"" + stringb + "\" is " +test);
        
        stringa = "atg";
        stringb = "ctgtatgta";
        test = twoOccurrences(stringa,stringb);
        System.out.println("\"" + stringa + "\" in \"" + stringb + "\" is " +test);
        
        stringa = "an";
        stringb = "banana";
        test = lastPart(stringa,stringb);
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + test);
        
        stringa = "zoo";
        stringb = "forest";
        test = lastPart(stringa,stringb);
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + test);
    }
}
