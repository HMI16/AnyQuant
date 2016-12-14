package bl_serviceTest;

import com.bigwork.bl.managementServiceImpl.MACD_Impl;
import com.bigwork.bl_service.MACD_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Administrator on 2016/6/9.
 */
public class MACD_ImplTest extends TestCase {
    private MACD_service test;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new MACD_Impl();
    }

    @Test
    public void testGetMACD() throws Exception {
        assertTrue(!test.getMACDGraph("sh600000","2016-03-01","2016-06-06").isEmpty());
    }
}
