package experiment.moostest;

import experiment.test.BasicTest;

/**
 * Created by kkhan on 06/09/18.
 */
public class FindCustomerRiskProfileTest extends BasicTest{

    public static void main(String args[]){
        FindCustomerRiskProfileTest stest = new FindCustomerRiskProfileTest();
        stest.exeuteFullCycle();
    }
    private String idNo = "920519016204";
    @Override
    public String getURI() {
        return "/harmoney2/moos/customer/"+idNo+"/get-risk-profile";
    }

}
