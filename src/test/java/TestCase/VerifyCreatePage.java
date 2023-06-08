package TestCase;

import java.util.Map;
import org.testng.annotations.Test;
import com.eva.vtiger.Marketing.Leads.MarkCreateLeadsPage;
import com.eva.vtiger.Marketing.Leads.MarketingLeadsDetailsValidationPage;
import com.eva.vtiger.Marketing.Leads.MarketingLeadsPage;
import com.eva.vtiger.Sales.Invice.CreatingInvoiceLandingPage;
import com.eva.vtiger.Sales.Invice.SalesInvoiceLandingPage;
import ReadExcelData_Utility.ExcelUtility;

public class VerifyCreatePage extends BaseMethodOfTestNG {

	@Test
	public void createLeads() {
		try {
			int mapCount = ExcelUtility.listAllData.size();
			MarketingLeadsPage lp = homeObj.gotoLeads();
			MarkCreateLeadsPage mCLP = lp.clickCreateLeadsButton();
			for (int i = 0; i < mapCount; i++) {
				Map<String, String> rowData = ExcelUtility.listAllData.get(i);
				mCLP.basicInformation(rowData).saveButton();
				String scenarioType = rowData.get("ScenarioType");
				String expectedTextPopUp = rowData.get("ExpErrorMsg");
				if (scenarioType.equalsIgnoreCase("Invalid")) {
					gn.validatePopUpText(expectedTextPopUp);
					gn.acceptAlert(expectedTextPopUp);
				} else {
					new MarketingLeadsDetailsValidationPage(gn).validateText(rowData.get("FirstName"));
				}
			}
			gn.getFlush();
		} finally {
			gn.getFlush();
		}
	}

	@Test
	public void createInvoice() {
		try {
			Map<String, String> rowData = ExcelUtility.listAllData.get(0);
			SalesInvoiceLandingPage invoice = homeObj.gotoInvoice();
			CreatingInvoiceLandingPage cIL = invoice.createButton();
			
			cIL.basicInformation(rowData).saveBt();
			gn.getFlush();
		} catch (Exception e) {
			gn.getFlush();
		}
	}

}
