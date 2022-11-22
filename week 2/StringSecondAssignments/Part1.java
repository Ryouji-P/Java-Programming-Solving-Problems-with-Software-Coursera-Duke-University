

public class Part1
{

    public int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        //stopCodon are TAA, TAG, TGA
        //startCodon is ATG
        int currIndex = dna.indexOf(stopCodon,startIndex+3);
        //System.out.println(currIndex);
        while (currIndex != -1){
            int diff = currIndex - startIndex;
            if (diff % 3 == 0){
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dna.length();
    }
    
    public void testFindStopCodon()
    {
        int startIndex = 0;
        //            ATG      TAG
        //String dna = "ATGTCGGATTAGGAT";
        //int stopIndex = findStopCodon(dna, startIndex, "TAG");
        //System.out.println(stopIndex);
        
        String dna = "ATGTCGGGAGGGAGA";
        int stopIndex = findStopCodon(dna, startIndex, "TAG");
        System.out.println(stopIndex);
    }
    
    public String findGene(String dna, int where)
    {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1)
        {
            return "";
        }
         //stopCodon are TAA, TAG, TGA
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int minIndex = Math.min(taaIndex,Math.min(tagIndex,tgaIndex));
        if (minIndex == dna.length()){ return "";}
        
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void testFindGene()
    {
        // string with no ATG
        String dna = "AGCGAGTTTATA";
        String gene = findGene(dna,0);
        System.out.println(gene);
        
        //string with ATG and one valid stop codon
        dna = "ATGGAAAGGGGAAAGTAA";
        gene = findGene(dna,0);
        System.out.println(gene);
        
        //string with ATG and multiple valid stop codons
        dna = "ATGGAATGATTTAAATAG";
        gene = findGene(dna,0);
        System.out.println(gene);
        
        //string with ATG and no valid stop codon
        dna = "ATGGAAAGAGAGATATAT";
        gene = findGene(dna,0);
        System.out.println(gene);
    }
    
    public void printAllGenes()
    {
        //            012345678901234567890123456
        String dna = "ATGGATTTTTAATTGATGGGGTGATATGTAAAAGTAG";
        int startIndex = 0;
        
        while( true ) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
            //System.out.println(startIndex);
        }
    }
}
