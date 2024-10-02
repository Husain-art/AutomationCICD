package hcyclewala.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int counts = 0, 
    maxCounts = 1;
    
    @Override
    public boolean retry(ITestResult result) {
        if(counts<maxCounts){
            counts++;
            return true;
        }
        return false;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'retry'");
    }
    

}
