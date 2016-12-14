package bl_serviceTest;

import com.bigwork.bl.listServiceImpl.StockList_Impl;
import com.bigwork.bl_service.StockList_service;
import com.bigwork.model.Stock;
import junit.framework.TestCase;

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
		Stock high = new Stock("", -1, -1, 19, -1, -1, -1, "", -1, -1, -1);
		Stock low = new Stock("", -1, -1, 1, -1, -1, -1, "", -1, -1, -1);
		assertTrue(!test.filter(low, high).isEmpty());
	}

}
