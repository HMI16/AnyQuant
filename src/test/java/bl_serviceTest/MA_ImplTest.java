package bl_serviceTest;

import com.bigwork.bl.managementServiceImpl.MA_Impl;
import com.bigwork.bl_service.MA_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Administrator on 2016/6/9.
 */
public class MA_ImplTest extends TestCase {
    private MA_service test;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new MA_Impl();
    }

    @Test
    public void testGetMA() throws Exception {
        assertTrue(!test.getMAGraph("sh600000","2014-02-03","2014-03-02",10).isEmpty());
    }
}
