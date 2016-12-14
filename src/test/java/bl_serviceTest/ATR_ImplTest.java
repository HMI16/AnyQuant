package bl_serviceTest;


import com.bigwork.bl.managementServiceImpl.ATR_Impl;
import com.bigwork.bl_service.ATR_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ryysuke on 16/4/14.
 */
public class ATR_ImplTest extends TestCase {

    private ATR_service test;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
       // test = new ATR_Impl("sz000908", "2015-08-09", "2015-08-14");
    }

    @Test
    public void testGetATR() throws Exception {
       // assertTrue(!((Double)test.GetATR()).equals((Double) 0.0 ));
    }


}
