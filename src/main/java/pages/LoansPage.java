
package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import java.net.MalformedURLException;
import java.net.URL;

public class LoansPage extends BasePage {
   public LoansPage(Page page) {
      super(page);
   }

   public String getLoansPageTitle() {
      String title = page.title();
      System.out.println("page title: " + title);
      return title;
   }

   public String getLoansPageURL() {
      String url = page.url();
      System.out.println("page url: " + url);
      return url;
   }

   public String extractPathSegment(String urlString) throws MalformedURLException {
      URL url = new URL(urlString);
      String path = url.getPath();
      String[] segments = path.split("/");
      return segments.length > 2 ? segments[2] : "";
   }

   public String validaDataWithOrganicJorney(String Month, String Day, String Year, String fName, String LName,
         String title, String email, String postCode, String fullAddress, String loanTerm, String loanAmount)
         throws MalformedURLException {
      click("loans_XPATH");
      click("getMypersonalisedQuote_XPATH");
      click("editButton_XPATH");
      select("selectLoanMonths_XPATH", loanTerm);
      input("borrowLoanAmount_XPATH", loanAmount);

      if (title.equals("MR")) {
         click("mrTitle_XPATH");
      } else if (title.equals("MRS")) {
         click("mrsTitle_XPATH");
      } else if (title.equals("MISS")) {
         click("missTitle_XPATH");
      } else {
         click("mrTitle_XPATH");
      }

      input("firstName_XPATH", fName);
      input("lastName_XPATH", LName);
      input("DD_XPATH", Day);
      input("MM_XPATH", Month);
      input("YYYY_XPATH", Year);
      input("email_XPATH", email);
      input("mobileNumber_XPATH", "7770065929");
      input("postCode_XPATH", postCode);
      click("findMyAddress_XPATH");
      click("useLoanFor_XPATH");
      input("pleaseSpecify_XPATH", "Education");
      
      page.waitForSelector("select[formcontrolname='addressFormat']");
      // page.evaluate(
      //       "value => { let select = document.querySelector('select[formcontrolname=\"addressFormat\"]');select.value = value;select.dispatchEvent(new Event('change'));}",
      //       fullAddress);
         
            page.evaluate(
               "value => { let select = document.querySelectorAll('select[formcontrolname=\"addressFormat\"]')[0];select.value = value;select.dispatchEvent(new Event('change'));}",
               fullAddress);
            
         
      click("whenDidYouMoveIn_XPATH");
      input("income_XPATH", "90000");
      click("dependentOnYou_XPATH");
      select("livingSituation_XPATH", "Homeowner with a mortgage");
      input("rent_XPATH", "500");
      select("employmentStatus_XPATH", "Full-time employed");
      input("jobTitle_XPATH", "Account Director");
      page.locator("//input[@aria-autocomplete=\"list\"]").press("Enter");
      input("companyName_XPATH", "Gojoko");
      click("getMypersonalisedQuote_XPATH");
      return isElementPresent("offerURL_PATH") ? extractPathSegment(getLoansPageURL()) : "quote_rejected";
   }

   public String formfilling(String LoanPurposeOther, String Title, String Forename, String Surname, String Day,
         String Month, String Year, String Email, String Phone_Number, String Postcode, String FullAddress,
         String MoveIn, String PrevPostcode, String PrevFullAddress, String CurrentIncome, String Dependent,
         String ResidentialStatus, String RentOrMortgage, String EmploymentStatus, String Employment_Sector,
         String Employer) throws MalformedURLException {
      try {
         Thread.sleep(3000);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      click("loans_XPATH");
      click("getMypersonalisedQuote_XPATH");
      click("editButton_XPATH");
      if (!Title.equals("false")) {
         if (Title.equals("MR")) {
            click("mrTitle_XPATH");
         } else if (Title.equals("MRS")) {
            click("mrsTitle_XPATH");
         } else if (Title.equals("MISS")) {
            click("missTitle_XPATH");
         } else {
            click("mrTitle_XPATH");
         }
      }

      if (!Forename.toLowerCase().equals("false")) {
         input("firstName_XPATH", Forename);
      }

      if (!Surname.toLowerCase().equals("false")) {
         input("lastName_XPATH", Surname);
      }

      if (!Day.equals("0")) {
         input("DD_XPATH", Day);
      }

      if (!Month.toLowerCase().equals("0")) {
         input("MM_XPATH", Month);
      }

      if (!Year.equals("0")) {
         input("YYYY_XPATH", Year);
      }

      if (!Email.toLowerCase().equals("false")) {
         input("email_XPATH", Email);
      }

      if (!Phone_Number.toLowerCase().equals("false")) {
         input("mobileNumber_XPATH", Phone_Number);
      }

      if (!Postcode.toLowerCase().equals("false")) {
         input("postCode_XPATH", Postcode);
         click("findMyAddress_XPATH");
      }

      // if (!FullAddress.toLowerCase().equals("false")) {
      //    page.waitForSelector("select[formcontrolname='addressFormat']");
      //    page.evaluate(
      //          "value => { let select = document.querySelector('select[formcontrolname=\"addressFormat\"]');select.value = value;select.dispatchEvent(new Event('change'));}",
      //          FullAddress);
      // }


      if (!FullAddress.toLowerCase().equals("false")) {
         page.waitForSelector("select[formcontrolname='addressFormat']");
         page.evaluate(
               "value => { let select = document.querySelectorAll('select[formcontrolname=\"addressFormat\"]')[0];select.value = value;select.dispatchEvent(new Event('change'));}",
               FullAddress);
      }

      if (!MoveIn.toLowerCase().equals("false")) {
         click("whenDidYouMoveInMoreThanThree_XPATH");
      }

      if (MoveIn.equals("Less Than 3 Years Ago")) {
         // previouse address
         if (MoveIn.equals("Less Than 3 Years Ago")) {
            click("whenDidYouMoveInLessThanThree_XPATH");
         }
         if (!PrevPostcode.toLowerCase().equals("false")) {
            input("prevPostCode_XPATH", PrevPostcode);
            click("findMyAddress_XPATH");
         }
         // if (!FullAddress.toLowerCase().equals("false")) {
         //    page.waitForSelector("select[formcontrolname='addressFormat']");
         //    page.evaluate(
         //          "value => { let select = document.querySelector('select[formcontrolname=\"addressFormat\"]');select.value = value;select.dispatchEvent(new Event('change'));}",
         //          PrevFullAddress);
         // }
         if (!PrevFullAddress.toLowerCase().equals("false")) {
            page.waitForSelector("select[formcontrolname='addressFormat']");
            page.evaluate(
                  "value => { let select = document.querySelectorAll('select[formcontrolname=\"addressFormat\"]')[1];select.value = value;select.dispatchEvent(new Event('change'));}",
                  PrevFullAddress);
         }
      }
      
     
      // end previouse address

      if (!LoanPurposeOther.toLowerCase().equals("false")) {
         click("useLoanFor_XPATH");
         input("pleaseSpecify_XPATH", "Education");
      }

      if (!CurrentIncome.toLowerCase().equals("false")) {
         input("income_XPATH", CurrentIncome);
      }

      if (!Dependent.toLowerCase().equals("false")) {
         click("dependentOnYou_XPATH");
      }

      if (!ResidentialStatus.toLowerCase().equals("false")) {
         select("livingSituation_XPATH", ResidentialStatus);
      }

      if (!RentOrMortgage.toLowerCase().equals("false")) {
         input("rent_XPATH", RentOrMortgage);
      }

      if (!EmploymentStatus.toLowerCase().equals("false")) {
         select("employmentStatus_XPATH", EmploymentStatus);
      }


      if (!Employment_Sector.toLowerCase().equals("false")) {
         input("jobTitle_XPATH", Employment_Sector);
         page.locator("//input[@aria-autocomplete=\"list\"]").press("Enter");
      }

      
      if(!Employer.toLowerCase().equals("false")) {
    	  
    	  input("companyName_XPATH", "Gojoko");
         
      }
      
      click("getMypersonalisedQuote_XPATH");
      

      try {
         Thread.sleep(2000L);
      } catch (InterruptedException var23) {
         var23.printStackTrace();
      }

      String errorMessage = page.locator("//div[contains(@class,'min-h-11 mt-1 rounded-sm bg-form-bg p-3')]//p")
            .textContent();
      return errorMessage;
   }
}
