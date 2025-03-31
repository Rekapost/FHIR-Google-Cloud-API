package reporting;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.http.Header;

public class ExtentReportManager {
	
	public static ExtentReports extentReports;
	
	public static ExtentReports createExtentReport(String fileName, String reportName, String documentTiltle) {
		
		ExtentSparkReporter sparkReport=new ExtentSparkReporter(fileName);		
		//sparkReport.config().setReportName("API Automation Report");
		sparkReport.config().setReportName(reportName);
		sparkReport.config().setDocumentTitle(documentTiltle);
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setEncoding("utf-8");
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReport);
		
		return extentReports;		
	}	
	
	public static String getReportNameWithTimeStamp() {
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        //String formattedTime = dateTimeFormatter.format(localDateTime);
       
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  // Time Stamp
		
		String reportName=  "TestReport" + timeStamp + ".html";
		return reportName;
		
	}
	
	// custom report giving colour pass greesn , fail red	
			public static void logPassDetails(String log) {
				// SetUp.extentTestThreadLocal.get().pass(log);  // prints normally without colur
				SetUp.extentTestThreadLocal.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
				// current test log info for testcase called by thread
				// testcase running by thread
			}
			
			public static void logFailDetails(String log) {
				SetUp.extentTestThreadLocal.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
			}
		  
			public static void logExceptionDetails(String log) {
		        SetUp.extentTestThreadLocal.get().fail(log);
		    }
			
			public static void logInfoDetails(String log) {		    
				SetUp.extentTestThreadLocal.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
			  }
			 
			public static void logWarningInfoDetails(String log) {	    
				//pass the test case with warning 
				SetUp.extentTestThreadLocal.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
			  }
			
			// to print request/response body in json
			public static void logJson(String json) {
		        SetUp.extentTestThreadLocal.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
		    }
			
			// to print header in table like key value pair
			 public static void logHeaders(List<Header> headersList) {

			        String[][] arrayHeaders = headersList.stream()
			        				.map(header -> new String[] 
			        						{header.getName(), header.getValue()})
			                        .toArray(String[][] :: new);
			       
			/*	 Headers  headers=   res.getHeaders();
					for(Header  header: headers)
					{
					System.out.println(header.getName());
					System.out.println(header.getValue());	
					}
			*/
			        SetUp.extentTestThreadLocal.get().info(MarkupHelper.createTable(arrayHeaders));
			    }
			
}
