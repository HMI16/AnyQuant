package bl_serviceTest;

import java.text.ParseException;

import com.bigwork.bl.listServiceImpl.Grail_Impl;
import com.bigwork.bl_service.Grail_service;
import junit.framework.TestCase;

public class Grail_ImplTest extends TestCase {

	private Grail_service test;

	protected void setUp() throws Exception {
		super.setUp();
		test = new Grail_Impl();
	}


	public void testsetTime() throws ParseException{
		assertTrue(!test.setTime("2015-10-11", "2015-12-12").isEmpty());
	}
}
