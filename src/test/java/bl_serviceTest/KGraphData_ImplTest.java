package bl_serviceTest;


import com.bigwork.bl.managementServiceImpl.KGraphData_Impl;
import com.bigwork.bl_service.KGraphData_service;
import junit.framework.TestCase;
import org.junit.Test;

import java.text.ParseException;

/**
 * Created by ryysuke on 16/4/14.
 */
public class KGraphData_ImplTest extends TestCase {

    private KGraphData_service test;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new KGraphData_Impl("sz300137", "2015-06-07", "2016-06-15");
    }

    @Test
    public void testgetKGraphData() throws ParseException {
        assertTrue((test.getKGraphData().get(0).getClose()) != 0);
    }
}
