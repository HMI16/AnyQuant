package data_serviceImplTest;

import java.text.ParseException;

import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.StockListData_management;
import junit.framework.TestCase;

public class StockListData_ImplTest extends TestCase {

	private StockListData_management test;
	public StockListData_ImplTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		test = new StockListData_management();
	}

	public void testStockList() throws ParseException{
		DataGetter getter = new DataGetter();
		//getter.getData("http://121.41.106.89:8010/api/stocks/");
		//test.StockList(getter.getData("http://121.41.106.89:8010/api/stocks/"));
		assertTrue(!test.StockList(getter.getData("http://121.41.106.89:8010/api/stocks/")).isEmpty());
	}
}
