head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.03.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginChangeCloseMarginRequestAdapterTest.java;


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

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ÅiWEB3MarginChangeOpenMarginRequestAdapterTestÅj
 * @@author âΩï∂ïq
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginRequestAdapterTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginChangeOpenMarginRequestAdapterTest.class);
    WEB3MarginCloseMarginChangeConfirmRequest l_marginOpenMarginChangeConfirmRequest = new WEB3MarginCloseMarginChangeConfirmRequest();
    WEB3MarginChangeCloseMarginRequestAdapter l_adapter1 = WEB3MarginChangeCloseMarginRequestAdapter.create(l_marginOpenMarginChangeConfirmRequest);
    WEB3MarginCloseMarginChangeCompleteRequest l_marginOpenMarginChangeCompleteRequest = new WEB3MarginCloseMarginChangeCompleteRequest();
    WEB3MarginChangeCloseMarginRequestAdapter l_adapter2 = WEB3MarginChangeCloseMarginRequestAdapter.create(l_marginOpenMarginChangeCompleteRequest);

    public WEB3MarginChangeCloseMarginRequestAdapterTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_marginOpenMarginChangeConfirmRequest.id = "123456789";
        l_marginOpenMarginChangeCompleteRequest.id = "123456789";
        Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
        WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
        WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
        
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetModifiedExpirationDate_case0001()
    {
        final String STR_METHOD_NAME = "testGetModifiedExpirationDate_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070618", "yyyyMMdd"));
            
            Date l_datExpirationDate = l_adapter1.getModifiedExpirationDate();
            assertEquals("20070618", WEB3DateUtility.formatDate(l_datExpirationDate, "yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetModifiedExpirationDate_case0002()
    {
        final String STR_METHOD_NAME = "testGetModifiedExpirationDate_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setInstitutionCode("33");
            l_eqtypeProductParams.setProductCode("N8080");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enOrderCondParam = TestDBUtility.getEnableOrderConditionRow();
            l_enOrderCondParam.setInstitutionCode("33");
            l_enOrderCondParam.setProductType(ProductTypeEnum.EQUITY);
            l_enOrderCondParam.setFutureOptionDiv("0");
            l_enOrderCondParam.setMarginTradingDiv("0");
            l_enOrderCondParam.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_enOrderCondParam);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            l_marginOpenMarginChangeConfirmRequest.id = "123456789";
            l_marginOpenMarginChangeConfirmRequest.expirationDate = WEB3DateUtility.getDate("20070628", "yyyyMMdd");
            
            Date l_datExpirationDate = l_adapter1.getModifiedExpirationDate();
            log.debug("!!!!!!!!!!!!!!!!!!!!!!!!! l_datExpirationDate = " + l_datExpirationDate);
            assertEquals("20070628", WEB3DateUtility.formatDate(l_datExpirationDate, "yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetModifiedExpirationDate_case0003()
    {
        final String STR_METHOD_NAME = "testGetModifiedExpirationDate_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
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
            
            l_marginOpenMarginChangeConfirmRequest.id = "123456789";
            l_marginOpenMarginChangeConfirmRequest.expirationDate = WEB3DateUtility.getDate("20070628", "yyyyMMdd");
            
            l_adapter1.getModifiedExpirationDate();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetModifiedExpirationDate_case0004()
    {
        final String STR_METHOD_NAME = "testGetModifiedExpirationDate_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070618", "yyyyMMdd"));
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            Date l_datExpirationDate = l_adapter2.getModifiedExpirationDate();
            assertEquals("20070618", WEB3DateUtility.formatDate(l_datExpirationDate, "yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetModifiedExpirationDate_case0005()
    {
        final String STR_METHOD_NAME = "testGetModifiedExpirationDate_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setInstitutionCode("33");
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
            
            l_marginOpenMarginChangeCompleteRequest.id = "123456789";
            l_marginOpenMarginChangeCompleteRequest.expirationDate = WEB3DateUtility.getDate("20070628", "yyyyMMdd");
            
            Date l_datExpirationDate = l_adapter2.getModifiedExpirationDate();
            log.debug("!!!!!!!!!!!!!!!!!!!!!!!!! l_datExpirationDate = " + l_datExpirationDate);
            assertEquals("20070628", WEB3DateUtility.formatDate(l_datExpirationDate, "yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetModifiedExpirationDate_case0006()
    {
        final String STR_METHOD_NAME = "testGetModifiedExpirationDate_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
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
            
            l_marginOpenMarginChangeConfirmRequest.id = "123456789";
            l_marginOpenMarginChangeConfirmRequest.expirationDate = WEB3DateUtility.getDate("20070628", "yyyyMMdd");
            
            l_adapter2.getModifiedExpirationDate();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetModifiedExpirationDate_case0007()
    {
        final String STR_METHOD_NAME = "testGetModifiedExpirationDate_case0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3MarginOpenMarginConfirmRequest l_request = new WEB3MarginOpenMarginConfirmRequest();
            WEB3MarginChangeCloseMarginRequestAdapter l_adapter3 = WEB3MarginChangeCloseMarginRequestAdapter.create(l_request);
            l_adapter3.getModifiedExpirationDate();
            
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
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setInstitutionCode("33");
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
            
            l_marginOpenMarginChangeCompleteRequest.id = "123456789";
            l_marginOpenMarginChangeCompleteRequest.expirationDate = WEB3DateUtility.getDate("20070628", "yyyyMMdd");
            Date l_datExpirationDate = l_adapter2.getExpirationDate();
            assertEquals("20070628", WEB3DateUtility.formatDate(l_datExpirationDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
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
            WEB3MarginChangeCloseMarginRequestAdapter l_adapter3 = WEB3MarginChangeCloseMarginRequestAdapter.create(l_request);
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

}
@
