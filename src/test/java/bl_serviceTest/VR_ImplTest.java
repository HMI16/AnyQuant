package bl_serviceTest;

import com.bigwork.bl.managementServiceImpl.VR_Impl;
import com.bigwork.bl_service.VR_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Administrator on 2016/6/9.
 */
public class VR_ImplTest extends TestCase{
    private VR_service test;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new VR_Impl();
    }

    @Test
    public void testGetVR() throws Exception {
        assertTrue(!test.getVRGraphValue("sh600000","2016-05-01","2016-05-20").isEmpty());
    }
}
