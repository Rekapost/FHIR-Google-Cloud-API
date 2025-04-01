package hooks;
    import java.io.File;  
    import java.awt.Desktop;
    import java.io.IOException;
    import io.cucumber.java.After;
    import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

    public class hook {
        //public String scenarioName;
        //public static String scenarioName; // Store scenario name globally
        //public static ThreadLocal<String> featureName = new ThreadLocal<>();
        public static ThreadLocal<String> scenarioName = new ThreadLocal<>();

        @Before
        public void beforeScenario(Scenario scenario) {
             //scenarioName.set(scenario.getName()); // Store scenario name per thread
             // Extract Feature Name from Scenario Source Tag
            //scenarioName= scenario.getName();
            scenarioName.set(scenario.getName());
            System.out.println("Running scenario: " + scenarioName);
            //String rawFeatureName = scenario.getUri().toString();
            //String featureFileName = rawFeatureName.substring(rawFeatureName.lastIndexOf('/') + 1).replace(".feature", "");
        
        //featureName.set(featureFileName); // ✅ Store feature name
        //scenarioName.set(scenario.getName()); // ✅ Store scenario name
        }
        public static String getScenarioName() {
            return scenarioName.get(); // Get the scenario name safely
        }

       @After
            public void openHtmlReport(){
                // Generate HTML report here
                try { 
                    File htmlReportFile = new File("/target/cucumber-html-report");
                    if(htmlReportFile.exists()){
                        Desktop.getDesktop().browse(htmlReportFile.toURI());
                    }else{
                        System.out.println("Report file not found:" + htmlReportFile.getAbsolutePath());
                        }
                    }catch(IOException e){
                    System.out.println("Report File not found");
                }
           
                //Allure
                try{
                    ProcessBuilder builder = new ProcessBuilder("C:/Users/nreka/Tools/allure-2.30.0/bin/allure", "serve", "allure-results");
                    builder.inheritIO();
                    Process process= builder.start();
                    process.waitFor();
    
                    //allure serve cmd automatically opnes report in browser
                    System.out.println("Report File found");
                } catch (IOException | InterruptedException e) {
                    System.out.println("Report File not found");
                }
            }

        //    @After
            public void stopDockerGrid() throws IOException, InterruptedException
            {
                Runtime.getRuntime().exec("cmd /c start stopDockerGrid.bat");
                Thread.sleep(15000);
                
                Runtime.getRuntime().exec("taskkill /f /im cmd.exe"); // kills all process closes command prompt
            }
    
        //    @Before  
            public void startDockerGrid() throws IOException, InterruptedException
            {
                Runtime.getRuntime().exec("cmd /c start startDockerGrid.bat");
                Thread.sleep(15000);
            }
    }
    

