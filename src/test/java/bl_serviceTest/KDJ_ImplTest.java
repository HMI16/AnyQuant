package bl_serviceTest;

import com.bigwork.bl.managementServiceImpl.KDJ_Impl;
import com.bigwork.bl_service.KDJ_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Administrator on 2016/6/9.
 */
public class KDJ_ImplTest extends TestCase {
    private KDJ_service test;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new KDJ_Impl();
    }

    @Test
    public void testGetKDJ() throws Exception {
        assertTrue(!test.getKDJGraph("sh600979","2015-02-03","2015-03-02",8).isEmpty());
    }
}
