package dataHelperTest;

import data.dataHelper.Refresh;
import junit.framework.TestCase;

public class RefreshTest extends TestCase {

	private Refresh test;
	public RefreshTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		test = new Refresh();
	}

	public void testrefresh(){
		assertTrue(test.refresh());
	}
}
