package bl_serviceTest;

import com.bigwork.bl.managementServiceImpl.pR_Impl;
import com.bigwork.bl_service.pR_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Administrator on 2016/6/9.
 */
public class pR_ImplTest extends TestCase {
    private pR_service test;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new pR_Impl();
    }

    @Test
    public void testGetpR() throws Exception {
        assertTrue(!test.getpRGraphValue("sh600000","2016-05-01","2016-05-20").isEmpty());
    }
}
