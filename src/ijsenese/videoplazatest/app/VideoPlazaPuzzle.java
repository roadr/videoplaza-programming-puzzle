package ijsenese.videoplazatest.app;

import ijsenese.videoplazatest.domain.CustomerCampaignSale;
import ijsenese.videoplazatest.domain.CustomerCampaignSpecification;
import ijsenese.videoplazatest.exception.InvalidFileFormatException;
import ijsenese.videoplazatest.knapsack.KnapsackElement;
import ijsenese.videoplazatest.knapsack.KnapsackSolver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This application has been written to solve the Video Plaza programming puzzle.
 * 
 * @author ivo.senese
 */
public class VideoPlazaPuzzle {

    private static BufferedReader bufferedReader;

    public static void main(String[] args) throws Exception {
        try {
            if(args.length != 1) {
                System.err.println("Wrong number of arguments.");
                System.exit(1);
            }
            
            FileReader fileReader = new FileReader(args[0]);
            bufferedReader = new BufferedReader(fileReader);
            String firstLine = bufferedReader.readLine();
            int numberOfImpressionsPerMonth = Integer.valueOf(firstLine);

            //Parse input data
            Map<String, CustomerCampaignSpecification> campaignOptions = loadInputData(bufferedReader);

            //Transform input to have a knapsack problem
            List<KnapsackElement> knapifiedListOfCampaigns = knapifyCampainSpecificationList(campaignOptions, numberOfImpressionsPerMonth);
            
            //Solve knapsack problem
            KnapsackSolver solver = new KnapsackSolver(knapifiedListOfCampaigns, numberOfImpressionsPerMonth);
            List<KnapsackElement> choosenKnapsackElements = solver.solve();
            
            //De-knappify solution
            Map<String, CustomerCampaignSale> solution = deKnappifySolution(choosenKnapsackElements, campaignOptions);
        
            //Print solution
            int totalRevenue = 0;
            int totalNumberOfImpression = 0;
            for(String customerName: solution.keySet()) {
                totalRevenue += solution.get(customerName).getTotalRevenue();
                totalNumberOfImpression += solution.get(customerName).getTotalImpressions();
                System.out.print(customerName+',');
                System.out.print(Integer.toString(solution.get(customerName).getNumberOfSales()) +',');
                System.out.print(Integer.toString(solution.get(customerName).getTotalImpressions()) +',');
                System.out.print(Integer.toString(solution.get(customerName).getTotalRevenue()));
                System.out.println();
            }
            System.out.println(Integer.toString(totalNumberOfImpression)+','+totalRevenue);
        
        } catch(IOException | InvalidFileFormatException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } finally {
            bufferedReader.close();
        }
    }

    /**
     * Reads data from input file, and to do so it assumes the data to be formatted
     * correctly.
     * 
     * @param bufferedReader
     * @return
     * @throws IOException
     * @throws InvalidFileFormatException 
     */
    public static Map<String, CustomerCampaignSpecification> loadInputData(BufferedReader bufferedReader)throws IOException, InvalidFileFormatException {
        String line = bufferedReader.readLine();
        Map<String, CustomerCampaignSpecification> campaignOptions = new HashMap<String,CustomerCampaignSpecification>();
        String[] lineElements;
        while(line != null) {
            try {
                lineElements = line.split(","); 
                campaignOptions.put(lineElements[0], new CustomerCampaignSpecification(lineElements[0], Integer.valueOf(lineElements[1]), Integer.valueOf(lineElements[2])));
                line = bufferedReader.readLine();
            } catch(Exception e) {
                throw new InvalidFileFormatException();
            }
        }
        return campaignOptions;
    }

    /**
     * Transforms campaign elements for each client into elements to choose from 
     * for the knapsack problem
     * 
     * Knapifying campaign elements means that each element will be transformed in
     * a set of elements corresponding in cardinality to the maximum integer number of
     * times a campaign can be sold given the monthly impression limit we must respect.
     * 
     * @param availableOptions
     * @param monthlyInventory
     * @return List<KnapsackElement>
     */
    private static List<KnapsackElement> knapifyCampainSpecificationList(Map<String, CustomerCampaignSpecification> availableOptions, int monthlyInventory) {
        int maxSalesForCampaign;
        int impressionsPerCampaign;
        List<KnapsackElement> knapifiedList = new ArrayList<KnapsackElement>();
        for(String customerName: availableOptions.keySet()) {
            impressionsPerCampaign = availableOptions.get(customerName).getImpressionsPerCampaign();
            maxSalesForCampaign = (int)Math.floor(((double)monthlyInventory)/impressionsPerCampaign);
            for(int i=0; i < maxSalesForCampaign; i++) {
                knapifiedList.add(
                    new KnapsackElement(customerName, 
                        availableOptions.get(customerName).getImpressionsPerCampaign(), 
                        availableOptions.get(customerName).getRevenuePerCampaign()
                    )
                );
            }
        }
        return knapifiedList;
    }
    
    /**
     * Reverses the knapifying procedure of the knapifyCampainSpecificationList
     * method, in order to produce the list of campaigns, with respective number
     * of sales for each campaign, which the optimum solution contains.
     * 
     * @param list
     * @param availableOptions
     * @return Map<String, CustomerCampaignSale>
     */
    private static Map<String, CustomerCampaignSale> deKnappifySolution(List<KnapsackElement> list, Map<String, CustomerCampaignSpecification> availableOptions) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(KnapsackElement element: list) {
            if(map.get(element.getName()) == null) {
                map.put(element.getName(), 0);
            }
            map.put(element.getName(), map.get(element.getName()) + 1 );
        }
        Map<String, CustomerCampaignSale> originalProblemSolution = new HashMap<String,CustomerCampaignSale>();
        for(String customerName: map.keySet()) {
            originalProblemSolution.put(customerName, 
                new CustomerCampaignSale(
                    customerName, 
                    map.get(customerName),
                    availableOptions.get(customerName).getImpressionsPerCampaign()*map.get(customerName),
                    availableOptions.get(customerName).getRevenuePerCampaign()*map.get(customerName)
                )
            );
        }
        return originalProblemSolution;
    }
}