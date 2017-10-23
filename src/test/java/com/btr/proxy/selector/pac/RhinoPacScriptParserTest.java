package com.btr.proxy.selector.pac;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Calendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.btr.proxy.TestUtil;
import com.btr.proxy.util.ProxyException;

/*****************************************************************************
 * Tests for the Rhino PAC script parser. 
 * @author Bernd Rosstauscher (proxyvole@rosstauscher.de) Copyright 2009
 ****************************************************************************/

public class RhinoPacScriptParserTest {
	
	/*************************************************************************
	 * Set calendar for date and time base tests.
	 * Current date for all tests  is: 15. December 1994 12:00.00 
	 * its a Thursday 
	 ************************************************************************/
	@BeforeClass
	public static void setup() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1994);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 15);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 00);
		
		RhinoPacScriptParser.setCurrentTime(cal);
	}

	/*************************************************************************
	 * Cleanup after the tests.
	 ************************************************************************/
	@AfterClass
	public static void teadDown() {
		RhinoPacScriptParser.setCurrentTime(null);
	}
	
	/*************************************************************************
	 * Test method
	 * @throws ProxyException on proxy detection error.
	 * @throws MalformedURLException on URL errors 
	 ************************************************************************/
	@Test
	public void testScriptExecution() throws ProxyException, MalformedURLException {
		PacScriptParser p = new RhinoPacScriptParser(new UrlPacScriptSource(toUrl("test1.pac")));
		p.evaluate(TestUtil.HTTP_TEST_URI.toString(), "host1.unit-test.invalid");
	}

	/*************************************************************************
	 * Test method
	 * @throws ProxyException on proxy detection error.
	 * @throws MalformedURLException on URL errors 
	 ************************************************************************/
	@Test
	public void testCommentsInScript() throws ProxyException, MalformedURLException {
		PacScriptParser p = new RhinoPacScriptParser(new UrlPacScriptSource(toUrl("test2.pac")));
		p.evaluate(TestUtil.HTTP_TEST_URI.toString(), "host1.unit-test.invalid");
	}
	
	/*************************************************************************
	 * Test method
	 * @throws ProxyException on proxy detection error.
	 * @throws MalformedURLException on URL errors 
	 ************************************************************************/
	@Test
	public void testScriptWeekDayScript() throws ProxyException, MalformedURLException {
		PacScriptParser p = new RhinoPacScriptParser(new UrlPacScriptSource(toUrl("testWeekDay.pac")));
		p.evaluate(TestUtil.HTTP_TEST_URI.toString(), "host1.unit-test.invalid");
	}

	/*************************************************************************
	 * Test method
	 * @throws ProxyException on proxy detection error.
	 * @throws MalformedURLException on URL errors 
	 ************************************************************************/
	@Test
	public void testDateRangeScript() throws ProxyException, MalformedURLException {
		PacScriptParser p = new RhinoPacScriptParser(new UrlPacScriptSource(toUrl("testDateRange.pac")));
		p.evaluate(TestUtil.HTTP_TEST_URI.toString(), "host1.unit-test.invalid");
	}

	/*************************************************************************
	 * Test method
	 * @throws ProxyException on proxy detection error.
	 * @throws MalformedURLException on URL errors 
	 ************************************************************************/
	@Test
	public void testTimeRangeScript() throws ProxyException, MalformedURLException {
		PacScriptParser p = new RhinoPacScriptParser(new UrlPacScriptSource(toUrl("testTimeRange.pac")));
		p.evaluate(TestUtil.HTTP_TEST_URI.toString(), "host1.unit-test.invalid");
	}
	
	/*************************************************************************
	 * Helper method to build the url to the given test file
	 * @param testFile the name of the test file.
	 * @return the URL. 
	 * @throws MalformedURLException
	 ************************************************************************/
	
	private String toUrl(String testFile) throws MalformedURLException {
		return new File(TestUtil.TEST_DATA_FOLDER+"pac", testFile).toURI().toURL().toString();
	}
		
}

