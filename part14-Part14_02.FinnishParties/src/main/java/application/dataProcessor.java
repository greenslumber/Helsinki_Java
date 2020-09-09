package application;

import java.nio.file.Paths;
import java.util.*;

public class dataProcessor {

    private List <Integer> listOfYears;

    public dataProcessor(){
    }

    private List<String> getData (String fileName) {

        List <String> rows = new ArrayList<>();

        try (Scanner fileR = new Scanner(Paths.get(fileName))){
            while (fileR.hasNextLine()){
                String row = fileR.nextLine();
                rows.add(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rows;
    }

    public Map <String, Map<Integer, Double>> workData (String fileName){

        //create master hashmap
        HashMap <String, Map<Integer, Double>> results = new HashMap<>();

        //pass filename to convert data into list of strings
        List <String> rawData = getData(fileName);

        //as data is mixture of Integers and Double, makes tricky to import so splitting import of line 1 vs other lines.
        //create list of years
        this.listOfYears = getYears(rawData);

        //now for remaining lines which is polling data per party
        for (int i = 1; i < rawData.size(); i++){

            //get data line
            String [] line = rawData.get(i).split("\t");

            //get party name
            String party = line[0];

            //create each parties data entry
            Map <Integer, Double> partyResults = processPartyData(line);

            //place the dataset against the party name key
            results.put(party, partyResults);
        }
        return results;
    }

    private List <Integer> getYears (List <String> rawData){

        List <Integer> listOfYears = new ArrayList<>();

        //add to list of years polled
        String [] topLine = rawData.get(0).split("\t");

        //identify list of years within data
        for(int i = 1; i < topLine.length; i++){
            int year = Integer.valueOf(topLine[i]);
            listOfYears.add(year);
        }

        return listOfYears;
    }

    private Map <Integer, Double> processPartyData (String [] line){

        HashMap <Integer, Double> partyResults = new HashMap <> ();

        //will start at second array item as i = 1 which will be first data point, not [0] (which would be party)
        for (int j = 1; j < line.length; j++) {

            //assume its 0.0 to initialise the variable
            double result = 0.0;

            //check if the entry isn't blank, otherwise leave at zero
            if (!line[j].contains("-")) {
                result = Double.parseDouble(line[j]);
            }

            //this is j-1, as the first item in the list is the first year heading, and j starts at 1 rather than 0 as discussed
            int year = listOfYears.get(j-1);

            //place into data hashmap
            partyResults.put(year, result);
        }
        return partyResults;
    }
}