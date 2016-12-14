package data_serviceImplTest;

import bl.managementServiceImpl.Hot_IndustryData_Impl;
import bl_service.Hot_IndustryData_service;
import junit.framework.TestCase;
import org.junit.Test;

import java.text.ParseException;

public class Hot_IndustryData_ImplTest extends TestCase {

    private Hot_IndustryData_service test;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new Hot_IndustryData_Impl();
    }

    @Test
    public void testGetOrderList() throws ParseException {
        assertTrue(!test.GetOrderList().isEmpty());
    }

    @Test
    public void testGetPieGraphData() throws ParseException {
        assertTrue(!test.GetPieGraphData().isEmpty());
    }

    @Test
    public void testGetBarChartData() throws ParseException {
        assertTrue(!test.GetBarChartData().isEmpty());
    }

}
