package bl_serviceTest;

import com.bigwork.bl.managementServiceImpl.RSI_Impl;
import com.bigwork.bl_service.RSI_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Administrator on 2016/6/9.
 */
public class RSI_ImplTest extends TestCase{
    private RSI_service test;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new RSI_Impl();
    }

    @Test
    public void testGetRSI() throws Exception {
        assertTrue(!test.getRSIGraphValue("sh600000","2016-05-01","2016-05-20").isEmpty());
    }
}
