head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.03.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderRequestAdapterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ÅiWEB3EquityOrderRequestAdapterTestÅj
 * @@author âΩï∂ïq
 * @@version 1.0
 */
public class WEB3EquityOrderRequestAdapterTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginChangeOpenMarginRequestAdapterTest.class);
    WEB3EquityBuyConfirmRequest l_marginOpenMarginChangeConfirmRequest = new WEB3EquityBuyConfirmRequest();
    WEB3EquityOrderRequestAdapter l_adapter1 = WEB3EquityOrderRequestAdapter.create(l_marginOpenMarginChangeConfirmRequest);
    WEB3EquityBuyCompleteRequest l_marginOpenMarginChangeCompleteRequest = new WEB3EquityBuyCompleteRequest();
    WEB3EquityOrderRequestAdapter l_adapter2 = WEB3EquityOrderRequestAdapter.create(l_marginOpenMarginChangeCompleteRequest);
    WEB3EquitySellConfirmRequest l_sellConfirmRequest = new WEB3EquitySellConfirmRequest();
    WEB3EquityOrderRequestAdapter l_adapter3 = WEB3EquityOrderRequestAdapter.create(l_sellConfirmRequest);
    WEB3EquitySellCompleteRequest l_sellCompleteRequest = new WEB3EquitySellCompleteRequest();
    WEB3EquityOrderRequestAdapter l_adapter4 = WEB3EquityOrderRequestAdapter.create(l_sellCompleteRequest);

    public WEB3EquityOrderRequestAdapterTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testGetExpirationDate_case0001()
    {
        final String STR_METHOD_NAME = "testGetExpirationDate_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070618", "yyyyMMdd"));
            
            Date l_datExpirationDate = l_adapter1.getExpirationDate();
            assertEquals("", WEB3DateUtility.formatDate(l_datExpirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetExpirationDate_case0002()
    {
        final String STR_METHOD_NAME = "testGetExpirationDate_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {   
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setProductId(3304148080001L);
            l_eqtypeContractParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",l_tsOrderAcceptTime);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("33");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("33");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("33");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            l_marginOpenMarginChangeCompleteRequest.productCode = "N8080";
            l_marginOpenMarginChangeCompleteRequest.marketCode = "1";
            l_marginOpenMarginChangeCompleteRequest.expirationDate = WEB3DateUtility.getDate("20070628", "yyyyMMdd");
            Date l_datExpirationDate = l_adapter2.getExpirationDate();
            assertEquals("20070628", WEB3DateUtility.formatDate(l_datExpirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetExpirationDate_case0003()
    {
        final String STR_METHOD_NAME = "testGetExpirationDate_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginOpenMarginConfirmRequest l_request = new WEB3MarginOpenMarginConfirmRequest();
            WEB3MarginChangeOpenMarginRequestAdapter l_adapter3 = WEB3MarginChangeOpenMarginRequestAdapter.create(l_request);
            l_adapter3.getExpirationDate();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetExpirationDate_case0004()
    {
        final String STR_METHOD_NAME = "testGetExpirationDate_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            l_marginOpenMarginChangeCompleteRequest.expirationDate = WEB3DateUtility.getDate("20070628", "yyyyMMdd");
            l_adapter2.getExpirationDate();
           
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetExpirationDate_case0005()
    {
        final String STR_METHOD_NAME = "testGetExpirationDate_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070618", "yyyyMMdd"));
            
            Date l_datExpirationDate = l_adapter3.getExpirationDate();
            assertEquals("", WEB3DateUtility.formatDate(l_datExpirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetExpirationDate_case0006()
    {
        final String STR_METHOD_NAME = "testGetExpirationDate_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070618", "yyyyMMdd"));
            
            Date l_datExpirationDate = l_adapter4.getExpirationDate();
            assertEquals("", WEB3DateUtility.formatDate(l_datExpirationDate, "yyyyMMdd"));
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
