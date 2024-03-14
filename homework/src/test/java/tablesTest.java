import ge.tbcitacademy.data.Constants;
import org.testng.annotations.Test;
import util.universalTableHandler;

// driver.open/close შიგნითვე მიწერია და SwitchToTest-ში არ დავტოვე ორ სხვადასხვა ტაბს ხსნიდა before/after-ის გამო

public class tablesTest {
    @Test
    public void techList(){
        new universalTableHandler().tableTest(Constants.TECHLISTURL,Constants.BURJKHALIFA);
    }

    @Test
    public void carsTest(){new universalTableHandler().tableTest(Constants.CARSURL,Constants.HONDA); }

    @Test
    public void herokuApp(){
        new universalTableHandler().tableTest(Constants.HEROKUAPP2,Constants.HEROKUSTR);
    }

    @Test
    public void extraWe3Schools(){
        new universalTableHandler().tableTest(Constants.WE3SCHOOLS,Constants.UK);
    }

    @Test
    public void extraTags(){ new universalTableHandler().tableTest(Constants.HTMLTAGS, Constants.HTMLSTR); }
}
