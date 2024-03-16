import ge.tbcitacademy.data.Constants;
import org.testng.annotations.Test;
import util.universalTableHandler;

// driver.open/close შიგნითვე მიწერია და SwitchToTest-ში არ დავტოვე ორ სხვადასხვა ტაბს ხსნიდა before/after-ის გამო

public class tablesTest {
    universalTableHandler tableHandler = new universalTableHandler();
    @Test
    public void techList(){
        tableHandler.tableTest(Constants.TECHLISTURL,Constants.BURJKHALIFA);
    }

    @Test
    public void carsTest(){tableHandler.tableTest(Constants.CARSURL,Constants.HONDA); }

    @Test
    public void herokuApp(){ tableHandler.tableTest(Constants.HEROKUAPP2,Constants.HEROKUSTR); }

    @Test
    public void extraWe3Schools(){
        tableHandler.tableTest(Constants.WE3SCHOOLS,Constants.UK);
    }

    @Test
    public void extraTags(){ tableHandler.tableTest(Constants.HTMLTAGS, Constants.HTMLSTR); }
}
