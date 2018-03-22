package my.great.pkg;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void testSmartAdd() throws Exception {
		assertEquals(15, Utils.smartAdd(7,8));
	}
}
