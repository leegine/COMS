head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.21.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeMainAccountTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : å⁄ãqÉNÉâÉXÉeÉXÉg(WEB3GentradeMainAccountTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/09  âhÉC (íÜêu) êVãKçÏê¨
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AccountProductOrderStopParams;
import webbroker3.gentrade.data.MainAccountAllParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (å⁄ãqÉNÉâÉXÉeÉXÉg)<BR>
 * 
 * @@author âhÉC (íÜêu)
 * @@version 1.0
 */
public class WEB3GentradeMainAccountTest extends TestBaseForMock
{
    /**
     * ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3GentradeMainAccountTest.class);

    public WEB3GentradeMainAccountTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsPTSAccountOpen_T01()
    {
        final String STR_METHOD_NAME = "testIsPTSAccountOpen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //InstitutionParamsy
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAcccount =
                new WEB3GentradeMainAccount(
                    l_institutionParams.getInstitutionId(),
                    l_branchParams.getBranchCode(),
                    l_mainAccountParams.getAccountCode());
            boolean l_isAccontOpen = l_mainAcccount.isPTSAccountOpen();
            assertTrue(!l_isAccontOpen);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    public void testIsPTSAccountOpen_T02()
    {
        final String STR_METHOD_NAME = "testIsPTSAccountOpen_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //InstitutionParamsy
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAcccount =
                new WEB3GentradeMainAccount(
                    l_institutionParams.getInstitutionId(),
                    l_branchParams.getBranchCode(),
                    l_mainAccountParams.getAccountCode());
            boolean l_isAccontOpen = l_mainAcccount.isPTSAccountOpen();
            assertTrue(l_isAccontOpen);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsPTSOrderStopProduct_T01()
    {
        final String STR_METHOD_NAME = "testIsPTSOrderStopProduct_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAcccount =
                new WEB3GentradeMainAccount(
                    l_institutionParams.getInstitutionId(),
                    l_branchParams.getBranchCode(),
                    l_mainAccountParams.getAccountCode());
            
            WEB3GentradeTradingClendarContext l_clendarContext = new 
                WEB3GentradeTradingClendarContext();

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);
            boolean l_isPTSOrderStopProduct =
                l_mainAcccount.isPTSTradeStopProduct(123l, null);
            assertTrue(!l_isPTSOrderStopProduct);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsPTSOrderStopProduct_T02()
    {
        final String STR_METHOD_NAME = "testIsPTSOrderStopProduct_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAcccount =
                new WEB3GentradeMainAccount(
                    l_institutionParams.getInstitutionId(),
                    l_branchParams.getBranchCode(),
                    l_mainAccountParams.getAccountCode());
            
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                null);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("090000");
            l_tradingTimeParams.setEndTime("230000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext = new 
                WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setBizdateCalcParameter("0");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);
            
            //AccountProductOrderStop
            TestDBUtility.deleteAll(AccountProductOrderStopParams.TYPE);

            boolean l_isPTSOrderStopProduct =
                l_mainAcccount.isPTSTradeStopProduct(123l, null);
            assertTrue(!l_isPTSOrderStopProduct);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPTSOrderStopProduct_T03()
    {
        final String STR_METHOD_NAME = "testIsPTSOrderStopProduct_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAcccount =
                new WEB3GentradeMainAccount(
                    l_institutionParams.getInstitutionId(),
                    l_branchParams.getBranchCode(),
                    l_mainAccountParams.getAccountCode());
            
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                null);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("090000");
            l_tradingTimeParams.setEndTime("230000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext = new 
                WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setBizdateCalcParameter("0");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            //AccountProductOrderStopParams
            TestDBUtility.deleteAll(AccountProductOrderStopParams.TYPE);
            AccountProductOrderStopParams l_accountProductOrderStopParams
                = TestDBUtility.getAccountProductOrderStopRow();
            l_accountProductOrderStopParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_accountProductOrderStopParams.setBranchId(l_branchParams.getBranchId());
            l_accountProductOrderStopParams.setAccountId(l_mainAccountParams.getAccountId());
            l_accountProductOrderStopParams.setProductId(0);
            l_accountProductOrderStopParams.setApplyStartDate(new Date(2002-1900,12,12));
            l_accountProductOrderStopParams.setApplyEndDate(new Date(2005-1900,12,12));
            TestDBUtility.insertWithDel(l_accountProductOrderStopParams);

            boolean l_isPTSOrderStopProduct =
                l_mainAcccount.isPTSTradeStopProduct(123l, null);
            assertTrue(!l_isPTSOrderStopProduct);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPTSOrderStopProduct_T04()
    {
        final String STR_METHOD_NAME = "testIsPTSOrderStopProduct_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAcccount =
                new WEB3GentradeMainAccount(
                    l_institutionParams.getInstitutionId(),
                    l_branchParams.getBranchCode(),
                    l_mainAccountParams.getAccountCode());
            
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                null);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("090000");
            l_tradingTimeParams.setEndTime("230000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext = new 
                WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setBizdateCalcParameter("0");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            //AccountProductOrderStopParams
            TestDBUtility.deleteAll(AccountProductOrderStopParams.TYPE);
            AccountProductOrderStopParams l_accountProductOrderStopParams
                = TestDBUtility.getAccountProductOrderStopRow();
            l_accountProductOrderStopParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_accountProductOrderStopParams.setBranchId(l_branchParams.getBranchId());
            l_accountProductOrderStopParams.setAccountId(l_mainAccountParams.getAccountId());
            l_accountProductOrderStopParams.setProductId(123l);
            l_accountProductOrderStopParams.setApplyStartDate(new Date(2002-1900,12,12));
            l_accountProductOrderStopParams.setApplyEndDate(new Date(2005-1900,12,12));
            TestDBUtility.insertWithDel(l_accountProductOrderStopParams);

            boolean l_isPTSOrderStopProduct =
                l_mainAcccount.isPTSTradeStopProduct(123l, null);
            assertTrue(!l_isPTSOrderStopProduct);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPTSOrderStopProduct_T05()
    {
        final String STR_METHOD_NAME = "testIsPTSOrderStopProduct_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAcccount =
                new WEB3GentradeMainAccount(
                    l_institutionParams.getInstitutionId(),
                    l_branchParams.getBranchCode(),
                    l_mainAccountParams.getAccountCode());
            
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                null);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("090000");
            l_tradingTimeParams.setEndTime("230000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext = new 
                WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setBizdateCalcParameter("0");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            //AccountProductOrderStopParams
            TestDBUtility.deleteAll(AccountProductOrderStopParams.TYPE);
            AccountProductOrderStopParams l_accountProductOrderStopParams
                = TestDBUtility.getAccountProductOrderStopRow();
            l_accountProductOrderStopParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_accountProductOrderStopParams.setBranchId(l_branchParams.getBranchId());
            l_accountProductOrderStopParams.setAccountId(l_mainAccountParams.getAccountId());
            l_accountProductOrderStopParams.setProductId(123l);
            l_accountProductOrderStopParams.setApplyStartDate(new Date(2002-1900,12,12));
            l_accountProductOrderStopParams.setApplyEndDate(new Date(2005-1900,12,12));
            TestDBUtility.insertWithDel(l_accountProductOrderStopParams);

            boolean l_isPTSOrderStopProduct =
                l_mainAcccount.isPTSTradeStopProduct(123l, OrderTypeEnum.EQUITY_BUY);
            assertTrue(l_isPTSOrderStopProduct);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPTSOrderStopProduct_T06()
    {
        final String STR_METHOD_NAME = "testIsPTSOrderStopProduct_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAcccount =
                new WEB3GentradeMainAccount(
                    l_institutionParams.getInstitutionId(),
                    l_branchParams.getBranchCode(),
                    l_mainAccountParams.getAccountCode());
            
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                null);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("090000");
            l_tradingTimeParams.setEndTime("230000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext = new 
                WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setBizdateCalcParameter("0");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            //AccountProductOrderStopParams
            TestDBUtility.deleteAll(AccountProductOrderStopParams.TYPE);
            AccountProductOrderStopParams l_accountProductOrderStopParams
                = TestDBUtility.getAccountProductOrderStopRow();
            l_accountProductOrderStopParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_accountProductOrderStopParams.setBranchId(l_branchParams.getBranchId());
            l_accountProductOrderStopParams.setAccountId(l_mainAccountParams.getAccountId());
            l_accountProductOrderStopParams.setProductId(0);
            l_accountProductOrderStopParams.setStopTradeDivSellCash("1");
            l_accountProductOrderStopParams.setApplyStartDate(new Date(2002-1900,10,12));
            l_accountProductOrderStopParams.setApplyEndDate(new Date(2005-1900,10,12));
            TestDBUtility.insertWithDel(l_accountProductOrderStopParams);

            boolean l_isPTSOrderStopProduct =
                l_mainAcccount.isPTSTradeStopProduct(123l, OrderTypeEnum.EQUITY_SELL);
            assertTrue(l_isPTSOrderStopProduct);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    //testIsForeignCont_0001:this.(äO)å_ñÒèëí•é˚ãÊï™ÅÅÅhí•é˚ÅhÇÃèÍçá
//    public void testIsForeignCont_0001()
//    {
//        final String STR_METHOD_NAME = " testIsForeignCont_0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
//        try
//        {
//            MainAccountParams l_mainAccountParams = new MainAccountParams();
//            l_mainAccountParams.setForeignContDiv("1");
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//            boolean l_blnResult = l_mainAccount.isForeignCont();
//            assertEquals(true, l_blnResult);
//        }
//        catch (Exception e)
//        {
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    //testIsForeignCont_0002:this.(äO)å_ñÒèëí•é˚ãÊï™!ÅÅÅhí•é˚ÅhÇÃèÍçá
//    public void testIsForeignCont_0002()
//    {
//        final String STR_METHOD_NAME = " testIsForeignCont_0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
//        try
//        {
//            MainAccountParams l_mainAccountParams = new MainAccountParams();
//            l_mainAccountParams.setForeignContDiv("0");
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//            boolean l_blnResult = l_mainAccount.isForeignCont();
//            assertEquals(false, l_blnResult);
//        }
//        catch (Exception e)
//        {
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//
//    //testIsForeignCont_0003:this.(äO)å_ñÒèëí•é˚ãÊï™ÅÅnullÇÃèÍçá
//    public void testIsForeignCont_0003()
//    {
//        final String STR_METHOD_NAME = " testIsForeignCont_0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
//        try
//        {
//            MainAccountParams l_mainAccountParams = new MainAccountParams();
//            l_mainAccountParams.setForeignContDiv(null);
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//            boolean l_blnResult = l_mainAccount.isForeignCont();
//            assertEquals(false, l_blnResult);
//        }
//        catch (Exception e)
//        {
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//

    //testIsJapaneseCurrencyBankAccountRegi_0001:this.êUçûêÊÅiã‚çså˚ç¿Åjìoò^ÅÅ1ÇÃèÍçá
    public void testIsJapaneseCurrencyBankAccountRegi_0001()
    {
        final String STR_METHOD_NAME = " testIsJapaneseCurrencyBankAccountRegi_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_mainAccountParams.setBankAccountRegi("1");  
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            boolean l_blnResult = l_mainAccount.isJapaneseCurrencyBankAccountRegi();
            assertEquals(true, l_blnResult); 
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    //testIsJapaneseCurrencyBankAccountRegi_0002:this.êUçûêÊÅiã‚çså˚ç¿Åjìoò^ÅÅ3ÇÃèÍçá
    public void testIsJapaneseCurrencyBankAccountRegi_0002()
    {
        final String STR_METHOD_NAME = " testIsJapaneseCurrencyBankAccountRegi_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_mainAccountParams.setBankAccountRegi("3");  
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            boolean l_blnResult = l_mainAccount.isJapaneseCurrencyBankAccountRegi();
            assertEquals(true, l_blnResult); 
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    //testIsJapaneseCurrencyBankAccountRegi_0003:this.êUçûêÊÅiã‚çså˚ç¿Åjìoò^ÅÅ2ÇÃèÍçá
    public void testIsJapaneseCurrencyBankAccountRegi_0003()
    {
        final String STR_METHOD_NAME = " testIsJapaneseCurrencyBankAccountRegi_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_mainAccountParams.setBankAccountRegi("2");  
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            boolean l_blnResult = l_mainAccount.isJapaneseCurrencyBankAccountRegi();
            assertEquals(false, l_blnResult); 
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    //testIsJapaneseCurrencyBankAccountRegi_0004:this.êUçûêÊÅiã‚çså˚ç¿Åjìoò^ÅÅnullÇÃèÍçá
    public void testIsJapaneseCurrencyBankAccountRegi_0004()
    {
        final String STR_METHOD_NAME = " testIsJapaneseCurrencyBankAccountRegi_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_mainAccountParams.setBankAccountRegi(null);  
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            boolean l_blnResult = l_mainAccount.isJapaneseCurrencyBankAccountRegi();
            assertEquals(false, l_blnResult); 
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    //testIsForeignCurrencyBankAccountRegi_0001:this.êUçûêÊÅiã‚çså˚ç¿Åjìoò^ÅÅ2ÇÃèÍçá
    public void testIsForeignCurrencyBankAccountRegi_0001()
    {
        final String STR_METHOD_NAME = " testIsForeignCurrencyBankAccountRegi_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_mainAccountParams.setBankAccountRegi("2");  
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            boolean l_blnResult = l_mainAccount.isForeignCurrencyBankAccountRegi();
            assertEquals(true, l_blnResult); 
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    //testIsForeignCurrencyBankAccountRegi_0002:this.êUçûêÊÅiã‚çså˚ç¿Åjìoò^ÅÅ3ÇÃèÍçá
    public void testIsForeignCurrencyBankAccountRegi_0002()
    {
        final String STR_METHOD_NAME = " testIsForeignCurrencyBankAccountRegi_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_mainAccountParams.setBankAccountRegi("3");  
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            boolean l_blnResult = l_mainAccount.isForeignCurrencyBankAccountRegi();
            assertEquals(true, l_blnResult); 
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    //testIsForeignCurrencyBankAccountRegi_0003:this.êUçûêÊÅiã‚çså˚ç¿Åjìoò^ÅÅ1ÇÃèÍçá
    public void testIsForeignCurrencyBankAccountRegi_0003()
    {
        final String STR_METHOD_NAME = " testIsForeignCurrencyBankAccountRegi_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_mainAccountParams.setBankAccountRegi("1");  
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            boolean l_blnResult = l_mainAccount.isForeignCurrencyBankAccountRegi();
            assertEquals(false, l_blnResult); 
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    //testIsForeignCurrencyBankAccountRegi_0004:this.êUçûêÊÅiã‚çså˚ç¿Åjìoò^ÅÅnullÇÃèÍçá
    public void testIsForeignCurrencyBankAccountRegi_0004()
    {
        final String STR_METHOD_NAME = " testIsForeignCurrencyBankAccountRegi_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_mainAccountParams.setBankAccountRegi(null);  
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            boolean l_blnResult = l_mainAccount.isForeignCurrencyBankAccountRegi();
            assertEquals(false, l_blnResult); 
        }
        catch (Exception e)
        {
            log.error("ERROR:", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
