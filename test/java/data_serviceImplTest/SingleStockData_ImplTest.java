package data_serviceImplTest;

import data.api.dataGetter.DataGetter;
import data.api.dataManagement.SingleStockData_management;
import junit.framework.TestCase;
import org.junit.Test;

public class SingleStockData_ImplTest extends TestCase {

	private SingleStockData_management test;
	public SingleStockData_ImplTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		test = new SingleStockData_management();
	}

    @Test
	public void testSingleStock(){
		DataGetter getter = new DataGetter();
		assertTrue(!test.SingleStock(getter.getData("http://121.41.106.89:8010/api/stock/sh600000/")).isEmpty());
	}

}
