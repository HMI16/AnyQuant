package bl_serviceTest;

import bl.listServiceImpl.StockList_Impl;
import bl_service.StockList_service;
import junit.framework.TestCase;
import vo.StockVO;

public class StockList_serviceTest extends TestCase {

	private StockList_service test;

	protected void setUp() throws Exception {
		super.setUp();
		test = new StockList_Impl();
	}

	public void testShowAll(){
		assertTrue(!test.ShowAll().isEmpty());
	}

	/*public void testSelect(){
		assertEquals(test.Select("sh600000").get(0).getId(), "sh600000");
	}*/

	public void testFilter(){
		StockVO high = new StockVO("", -1, -1, 19, -1, -1, -1, "", -1, -1, -1);
		StockVO low = new StockVO("", -1, -1, 1, -1, -1, -1, "", -1, -1, -1);
		assertTrue(!test.filter(low, high).isEmpty());
	}

}
