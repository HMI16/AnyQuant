package bl_serviceTest;

import com.bigwork.bl.managementServiceImpl.Fluctuation_Impl;
import com.bigwork.bl_service.Fluctuation_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Administrator on 2016/6/9.
 */
public class Fluctuation_ImplTest extends TestCase {
    private Fluctuation_service test;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new Fluctuation_Impl();
    }

    @Test
    public void testGetFluctuation() throws Exception {
        assertTrue(test.getFluctuation("sh600979","2015")>=0);
    }
}
