package dataHelperTest;

import java.sql.SQLException;
import java.text.ParseException;

import com.bigwork.data.dataHelper.Refresh;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;
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

	public void testrefresh() throws ParseException, SQLException, SqlImplNullPointerException, SqlLinkException {
		assertTrue(test.refresh());
	}
}
