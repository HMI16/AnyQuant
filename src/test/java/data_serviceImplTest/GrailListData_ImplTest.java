package data_serviceImplTest;

import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.GrailListData_management;
import junit.framework.TestCase;

public class GrailListData_ImplTest extends TestCase {

	private GrailListData_management test;

	public GrailListData_ImplTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		test = new GrailListData_management();
	}

	public void testGrailList(){
		DataGetter getter = new DataGetter();
		assertTrue(!test.GrailList(getter.getData("http://121.41.106.89:8010/api/benchmark/hs300")).isEmpty());
	}
}
