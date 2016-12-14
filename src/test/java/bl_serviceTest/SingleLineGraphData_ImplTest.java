package bl_serviceTest;


import com.bigwork.bl.managementServiceImpl.SingleLineGraphData_Impl;
import com.bigwork.bl_service.SingleLineGraphData_service;
import junit.framework.TestCase;
import org.junit.Test;

import java.net.PasswordAuthentication;

/**
 * Created by ryysuke on 16/4/14.
 */
public class SingleLineGraphData_ImplTest extends TestCase {

    private SingleLineGraphData_service test;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new SingleLineGraphData_Impl("sh600129");
    }

    @Test
    public void testGetVol() throws Exception {
        assertTrue(test.getVolData("2015-04-10", "2015-04-23").size() != 0);
    }

    @Test
    public void testGetDeal() throws Exception {
        assertTrue(test.getDealData("2015-04-10", "2015-04-23").size() != 0);
    }

    @Test
    public void testGetVolDefault() throws Exception {
        assertTrue(test.getVolData().size() != 0);
    }

    @Test
    public void testGetDealDefault() throws Exception {
        assertTrue(test.getDealData().size() != 0);
    }


}
