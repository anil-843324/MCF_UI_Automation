package testcases;

import java.net.MalformedURLException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// import com.aventstack.extentreports.util.Assert;

import base.BaseTest;
import pages.LoansPage;
import utilities.Constants;
import utilities.DataProviderUtil;
import utilities.ReusableFunction;


public class LoansTest extends BaseTest {

    private LoansPage loans;



    @BeforeMethod
    public void setUp() {
        // browser = getBrowser("chrome");
        browser = getBrowser("headless");
        navigate(browser, Constants.URL);
        loans = new LoansPage(page);
    }

//    @Test
//    public void homePageTitleTest() {
//   
//    try {
//    Thread.sleep(2000);
//   } catch (InterruptedException e) {
//   e.printStackTrace();    }
//     String actualTitle = loans.getLoansPageTitle();
//    Assert.assertEquals(actualTitle, "My Community Finance");
//     Assert.assertEquals(actualTitle, AppContant.LEARN_ABOUT_OUR_HISTORY);
//    }

@Test(dataProvider = "validData", dataProviderClass = DataProviderUtil.class)
  public void validateLoanOrganicJourneyWithValidData(Hashtable<String, String>
  data) throws MalformedURLException {

  int[] dateComponents = ReusableFunction.parseDate(data.get("DOB"));

  // Print the components
  String Month = Integer.toString(dateComponents[0]);
  String Day = Integer.toString(dateComponents[1]);
  String Year = Integer.toString(dateComponents[2]);
  String fName = data.get("Forename");
  String LName = data.get("Surname");
  String title = data.get("Title");
  String postCode = data.get("postcode");
  String fullAddress = data.get("FullAddress");
  String loanTerm = data.get("loanterm");
  String loanAmount = data.get("loanval");
  
//   System.out.println(fullAddress);

  loanTerm = Integer.toString((int) Double.parseDouble(loanTerm));
  loanAmount = Integer.toString((int) Double.parseDouble(loanAmount));

  try {
  Thread.sleep(2000);
  } catch (InterruptedException e) {
  e.printStackTrace();
  }

  String loanStatus = loans.validaDataWithOrganicJorney(Month, Day, Year,
  ReusableFunction.generateRandomName(fName), LName, title,
  ReusableFunction.generateRandomEmail(), postCode, fullAddress, loanTerm, loanAmount);

  Assert.assertEquals(loanStatus, "loan-offer");
  
  }

    // // Error validation
    // @Test(dataProvider = "invalidData", dataProviderClass = DataProviderUtil.class)
    // public void formValidation2(Hashtable<String, String> data) throws MalformedURLException {

    	
    // 	System.out.println(
    			
    //             data.get("LoanPurposeOther") + " " + data.get("Title") + ", " +
    //             data.get("Forename") + ", " + data.get("Surname") + ", " +
    //             String.valueOf((int) Float.parseFloat(data.get("Day(DOB)"))) + "/" + 
    //             String.valueOf((int) Float.parseFloat(data.get("Month(DOB)"))) + "/" + 
    //             String.valueOf((int) Float.parseFloat(data.get("Year(DOB)"))) + ", " +
    //             data.get("Email") + ", " + data.get("Phone_Number") + ", " +
    //             data.get("Postcode") + ", " + data.get("FullAddress") + ", " +
    //             data.get("MoveIn") + ", " + data.get("PrevPostcode") + ", " +
    //             data.get("PrevFullAddress") + ", " + data.get("CurrentIncome") + ", " +
    //             data.get("Dependent") + ", " + data.get("Residential Status") + ", " +
    //             data.get("RentOrMortgage") + ", " + data.get("EmploymentStatus") + ", " +
    //             data.get("Employment_Sector") + ", " + data.get("Employer")
    //         );

    //     String validationMessage = loans.formfilling(data.get("LoanPurposeOther"), data.get("Title"),
    //             data.get("Forename"), data.get("Surname"), String.valueOf((int) Float.parseFloat(data.get("Day(DOB)"))) , String.valueOf((int) Float.parseFloat(data.get("Month(DOB)"))),
    //             String.valueOf((int) Float.parseFloat(data.get("Year(DOB)"))) , data.get("Email"), data.get("Phone_Number"), data.get("Postcode"),
    //             data.get("FullAddress"), data.get("MoveIn"), data.get("PrevPostcode"), data.get("PrevFullAddress"),
    //             data.get("CurrentIncome"), data.get("Dependent"), data.get("Residential Status"),
    //             data.get("RentOrMortgage"), data.get("EmploymentStatus"), data.get("Employment_Sector"),
    //             data.get("Employer"));
        
    //     System.out.println(validationMessage);

    // }


 

}

