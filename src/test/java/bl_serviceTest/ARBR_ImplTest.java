package bl_serviceTest;

import com.bigwork.bl.managementServiceImpl.ARBR_Impl;
import com.bigwork.bl_service.ARBR_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Administrator on 2016/6/9.
 */
public class ARBR_ImplTest extends TestCase {
    private ARBR_service test;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new ARBR_Impl();
    }

    @Test
    public void testGetARBR() throws Exception {
        assertTrue(!test.getARBRGraph("sh600979","2016-04-02","2016-04-12").isEmpty());
    }
}
