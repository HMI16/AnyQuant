package bl_serviceTest;

import com.bigwork.bl.managementServiceImpl.BIAS_Impl;
import com.bigwork.bl_service.BIAS_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ryysuke on 16/4/14.
 */
public class BIAS_ImplTest extends TestCase {

    private BIAS_service test;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
      //  test = new BIAS_Impl("sz000908", "2015-08-09", "2015-08-14");
    }

    @Test
    public void testGetBIAS() throws Exception {
        //assertTrue(!((Double)test.getBIAS()).equals((Double) 0.0 ));
    }

}
