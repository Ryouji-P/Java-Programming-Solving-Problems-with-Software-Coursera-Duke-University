
/**
 * Part 1: Finding a Gene - Using the Simplified Algorithm
 * 
 * @Ryouji-P
 * @2022/11/10
 */
public class Part1
{

    public String findSimpleGene(String dna)
    {
        String result = "";
        // start codon is ATG
        // stop codon is TAA
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){//no ATG
            return "";
        };
        
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1){//no TAA
            return "";
        };
        result = dna.substring(startIndex, stopIndex+3);
        if (result.length() % 3 != 0){//substring not divided by 3
            return "";
        }; 
        return result;
    }
    public void testSimpleGene(){
        String DNAwithnoATG = "ACGCACAGATAA";
        System.out.println("DNA strand is " + DNAwithnoATG);
        String gene = findSimpleGene(DNAwithnoATG);
        System.out.println("Gene is " + gene);
        
        String DNAwithnoTAA = "ATGCAGAAGATA";
        System.out.println("DNA strand is " + DNAwithnoTAA);
        gene = findSimpleGene(DNAwithnoTAA);
        System.out.println("Gene is " + gene);
        
        String DNAwithnoATGorTAA = "ACGATAGACAT";
        System.out.println("DNA strand is " + DNAwithnoATGorTAA);
        gene = findSimpleGene(DNAwithnoATGorTAA);
        System.out.println("Gene is " + gene);
        
        String DNAnotdividedby3 = "ATGTCATGAGTAA";
        System.out.println("DNA strand is " + DNAnotdividedby3);
        gene = findSimpleGene(DNAnotdividedby3);
        System.out.println("Gene is " + gene);
        
        String DNAdividedby3 = "ATGTCATGATAA";
        System.out.println("DNA strand is " + DNAdividedby3);
        gene = findSimpleGene(DNAdividedby3);
        System.out.println("Gene is " + gene);
        
    }
}
