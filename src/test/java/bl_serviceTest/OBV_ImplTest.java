package bl_serviceTest;

import com.bigwork.bl.managementServiceImpl.OBV_Impl;
import com.bigwork.bl_service.OBV_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Administrator on 2016/6/9.
 */
public class OBV_ImplTest extends TestCase {
    private OBV_service test;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new OBV_Impl();
    }

    @Test
    public void testGetOBV() throws Exception {
        assertTrue(!test.getOBVGraphValue("sh600000","206-05-01","2016-05-20").isEmpty());
    }
}
