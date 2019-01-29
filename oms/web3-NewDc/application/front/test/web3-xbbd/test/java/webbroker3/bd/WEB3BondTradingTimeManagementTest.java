head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondTradingTimeManagementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (債券取引時間管理)
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3BondTradingTimeManagementTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondTradingTimeManagementTest.class);
    WEB3BondTradingTimeManagement l_management = new WEB3BondTradingTimeManagement();
    
    public WEB3BondTradingTimeManagementTest(String arg0)
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
    
    public void testGetBondDomesticLimitTime_case0001()
    {
        final String STR_METHOD_NAME = "testGetBondDomesticLimitTime_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("020701");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = 
                new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_clendarContext);
            
            Date l_datDate = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            String l_strTime = l_management.getBondDomesticLimitTime(l_datDate);
            assertEquals("020701" ,l_strTime);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBondDomesticLimitTime_case0002()
    {
        final String STR_METHOD_NAME = "testGetBondDomesticLimitTime_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = 
                new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_clendarContext);
            
            Date l_datDate = WEB3DateUtility.getDate("20070917", "yyyyMMdd");
            l_management.getBondDomesticLimitTime(l_datDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("データ不整合エラー", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
