/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ijsenese.videoplazatest.domain;

/**
 * CustomerCampaignSale is the class whose objects represent the actual campaign 
 * sales that the solution contains.
 * 
 * @author ivo.senese
 */
public class CustomerCampaignSale {
    private String customerName;
    private int numberOfSales;
    private int totalImpressions;
    private int totalRevenue;
    
    
    public CustomerCampaignSale(String customerName, int numberOfSales, int totalImpressions, int totalRevenue) {
        this.customerName = customerName;
        this.numberOfSales = numberOfSales;
        this.totalImpressions = totalImpressions;
        this.totalRevenue = totalRevenue;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public int getTotalImpressions() {
        return totalImpressions;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }
    
}
