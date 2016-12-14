package bl_serviceTest;

import java.text.ParseException;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.SingleStock_service;
import junit.framework.TestCase;

public class SingleStock_ImplTest extends TestCase {

	private SingleStock_service test;

	protected void setUp() throws Exception {
		super.setUp();
		test = new SingleStock_Impl();
	}

	public void testShow() throws ParseException{
		assertEquals(test.Show("sh600000").get(0).getId(), "sh600000");
	}

	public void testsetTime() throws ParseException{
		assertEquals(test.setTimeFromDB("sh600000", "2016-01-10", "2016-02-02").get(0).getDate()
				, "2016-01-11");
	}

}
