package data.apiTest;


import com.bigwork.data.api.dataGetter.DataGetter;
import junit.framework.TestCase;

public class DataGetterTest extends TestCase {

	private DataGetter getter;

	public DataGetterTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		getter = new DataGetter();
	}

	public void testgetData(){

		//System.out.println(getter.getData("http://121.41.106.89:8010/api/stocks/"));
		assertTrue(!getter.getData("http://121.41.106.89:8010/api/stocks/").isEmpty());

	}
}