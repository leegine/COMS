head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeFeqBranchMarketDealtCondForMockTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeFeqBranchMarketDealtCondForMockTest extends TestBaseForMock
{
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3GentradeFeqBranchMarketDealtCondForMockTest.class);
   
    public WEB3GentradeFeqBranchMarketDealtCondForMockTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCondForMock.mockGetFeqHandlingCondBranchMarket(WEB3GentradeBranch, WEB3GentradeFeqBranchMarketDealtCond[])'
     */
    public void testMockGetFeqHandlingCondBranchMarket()
    {
        final String STR_METHOD_NAME = "testMockGetFeqHandlingCondBranchMarket";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3GentradeBranchForMock l_branch;
        
        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            l_branch = new WEB3GentradeBranchForMock(33381);
            
            FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams = 
                TestDBUtility.getFeqBranchMarketDealtCondRow();
            
            l_feqBranchMarketDealtCondParams.setInstitutionCode("0D");
            l_feqBranchMarketDealtCondParams.setBranchCode("381");
            l_feqBranchMarketDealtCondParams.setMarketCode("N1");
            
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
            
            WEB3GentradeFeqBranchMarketDealtCond l_expect = 
                new WEB3GentradeFeqBranchMarketDealtCond("0D","381", "N1");
            
            WEB3GentradeFeqBranchMarketDealtCondForMock.mockGetFeqHandlingCondBranchMarket(l_branch,
                new WEB3GentradeFeqBranchMarketDealtCond[] {l_expect});
            
            assertEquals(
                l_expect.getMarketCode(), 
                WEB3GentradeFeqBranchMarketDealtCondForMock.getFeqHandlingCondBranchMarket(l_branch)[0].getMarketCode());            
        } 
        catch (Exception e)
        {
            log.error("", e);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCondForMock.mockGetTradingHandlingPossibleMarket(WEB3GentradeBranch, ProductTypeEnum, String[])'
     */
    public void testMockGetTradingHandlingPossibleMarket()
    {
        final String STR_METHOD_NAME = "testMockGetTradingHandlingPossibleMarket";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3GentradeBranchForMock l_branch;
        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeFeqBranchMarketDealtCondForMock.mockGetTradingHandlingPossibleMarket(
                l_branch, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                new String[]{"D1","N1"});
            
            String[] l_strReturnValue = 
                WEB3GentradeFeqBranchMarketDealtCond.getTradingHandlingPossibleMarket(l_branch, 
                ProductTypeEnum.FOREIGN_EQUITY);
            
            assertEquals("D1", l_strReturnValue[0]);
            assertEquals("N1", l_strReturnValue[1]);
            
            WEB3GentradeFeqBranchMarketDealtCondForMock.mockGetTradingHandlingPossibleMarket(
                l_branch, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                new String[]{"01","03","05","06"});
            
            l_strReturnValue = 
                WEB3GentradeFeqBranchMarketDealtCond.getTradingHandlingPossibleMarket(l_branch, 
                ProductTypeEnum.FOREIGN_EQUITY);
            
            assertEquals("01", l_strReturnValue[0]);
            assertEquals("03", l_strReturnValue[1]);
            assertEquals("05", l_strReturnValue[2]);
            assertEquals("06", l_strReturnValue[3]);
            
            WEB3GentradeFeqBranchMarketDealtCondForMock.mockGetTradingHandlingPossibleMarket(
                l_branch, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                new String[]{});
            
            l_strReturnValue = 
                WEB3GentradeFeqBranchMarketDealtCond.getTradingHandlingPossibleMarket(l_branch, 
                ProductTypeEnum.FOREIGN_EQUITY);
            
            assertEquals(0, l_strReturnValue.length);
        } 
        catch (Exception e)
        {
            log.error("", e);
            fail();
        }        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
