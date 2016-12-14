package bl_serviceTest;


import com.bigwork.bl.managementServiceImpl.MarketLineGraphData_Impl;
import com.bigwork.bl_service.MarketLineGraphData_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ryysuke on 16/4/14.
 */
public class MarketLineGraphData_ImplTest extends TestCase{

    private MarketLineGraphData_service test;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new MarketLineGraphData_Impl("2016-04-01", "2016-04-10");
    }

    @Test
    public void testGetAdjData() throws Exception {
        assertTrue(!test.getAdjData("2016-04-01", "2016-04-10").get(0).getName().isEmpty());
    }

    @Test
    public void testGetVolData() throws Exception {
        assertTrue(!test.getVolData("2016-04-01", "2016-04-10").get(0).getName().isEmpty());
    }

}
