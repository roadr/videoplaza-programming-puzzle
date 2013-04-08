package ijsenese.videoplazatest.domain;

/**
 * CustomerCampaignSpecification is the class from which objects are instantiated
 * to represent the campaign specification that each client is willing to purchase.
 * 
 * @author ivo.senese
 */
public class CustomerCampaignSpecification {

    private String customerName;
    private int impressionsPerCampaign;
    private int revenuePerCampaign;

    public CustomerCampaignSpecification(String customerName, int impressionsPerCampaign, int revenuePerCampaign) {
        this.customerName = customerName;
        this.impressionsPerCampaign = impressionsPerCampaign;
        this.revenuePerCampaign = revenuePerCampaign;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public int getImpressionsPerCampaign() {
        return this.impressionsPerCampaign;
    }

    public int getRevenuePerCampaign() {
        return this.revenuePerCampaign;
    }
}