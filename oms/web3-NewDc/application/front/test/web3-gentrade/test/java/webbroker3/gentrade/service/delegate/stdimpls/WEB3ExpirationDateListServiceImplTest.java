head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.59.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ExpirationDateListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ExpirationDateListServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/20 于瀟（中訊）新規作成
*/
package webbroker3.gentrade.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.message.WEB3DocumentDeliverRequest;
import webbroker3.gentrade.message.WEB3ExpirationDateListRequest;
import webbroker3.gentrade.message.WEB3ExpirationDateListResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ExpirationDateListServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3ExpirationDateListServiceImplTest.class);

    public WEB3ExpirationDateListServiceImplTest(String arg0)
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

    /**
     * パラメータ値不正。
     * 
     * ?出異常信息:SYSTEM_ERROR_80017
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ExpirationDateListServiceImpl l_expirationDateListServiceImpl =
            new WEB3ExpirationDateListServiceImpl();
        
        WEB3ExpirationDateListRequest l_request = null;
        try
        {
            l_expirationDateListServiceImpl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * パラメータタイプ不正。
     * 
     * ?出異常信息:SYSTEM_ERROR_80018
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ExpirationDateListServiceImpl l_expirationDateListServiceImpl =
            new WEB3ExpirationDateListServiceImpl();
        
        WEB3DocumentDeliverRequest l_request =
            new WEB3DocumentDeliverRequest();
        try
        {
            l_expirationDateListServiceImpl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     *
     * 當l_request.commodityType = WEB3CommodityDivDef.EQUITY時
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ExpirationDateListServiceImpl l_expirationDateListServiceImpl =
            new WEB3ExpirationDateListServiceImpl();
        
        WEB3ExpirationDateListRequest l_request =
            new WEB3ExpirationDateListRequest();
        l_request.commodityType = WEB3CommodityDivDef.EQUITY;
        l_request.marketCode = WEB3MarketCodeDef.TOKYO;
        l_request.targetProductCode = null;
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            Date l_dat = WEB3DateUtility.getDate("20080220", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_dat);
            WEB3ExpirationDateListResponse l_response =
                (WEB3ExpirationDateListResponse)l_expirationDateListServiceImpl.execute(l_request);
            
            assertEquals("1", l_response.expirationDateTypeList[0]);
            assertEquals("2", l_response.expirationDateTypeList[1]);
            
            Date l_datExceptStart = WEB3DateUtility.getDate("20080220", "yyyyMMdd");
            String l_strExceptStart = WEB3DateUtility.formatDate(l_datExceptStart, "yyyyMMdd");
            String l_strTrueStart = WEB3DateUtility.formatDate(l_response.expirationStartDate, "yyyyMMdd");
            assertEquals(l_strExceptStart, l_strTrueStart);
            
            Date l_datExceptEnd = WEB3DateUtility.getDate("20080220", "yyyyMMdd");
            String l_strExceptEnd = WEB3DateUtility.formatDate(l_datExceptEnd, "yyyyMMdd");
            String l_strTrueEnd = WEB3DateUtility.formatDate(l_response.expirationEndDate, "yyyyMMdd");
            assertEquals(l_strExceptEnd, l_strTrueEnd);
            
            assertNull(l_response.holidayList);
            assertNull(l_response.sessionType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     *
     * 當l_request.commodityType = WEB3CommodityDivDef.FUTURE時
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ExpirationDateListServiceImpl l_expirationDateListServiceImpl =
            new WEB3ExpirationDateListServiceImpl();
        
        WEB3ExpirationDateListRequest l_request =
            new WEB3ExpirationDateListRequest();
        l_request.commodityType = WEB3CommodityDivDef.FUTURE;
        l_request.marketCode = WEB3MarketCodeDef.TOKYO;
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            Date l_dat = WEB3DateUtility.getDate("20080220", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_dat);
            
            WEB3ExpirationDateListResponse l_response =
                (WEB3ExpirationDateListResponse)l_expirationDateListServiceImpl.execute(l_request);
            
            assertEquals("1", l_response.expirationDateTypeList[0]);
            assertEquals("2", l_response.expirationDateTypeList[1]);
            
            Date l_datExceptStart = WEB3DateUtility.getDate("20080220", "yyyyMMdd");
            String l_strExceptStart = WEB3DateUtility.formatDate(l_datExceptStart, "yyyyMMdd");
            String l_strTrueStart = WEB3DateUtility.formatDate(l_response.expirationStartDate, "yyyyMMdd");
            assertEquals(l_strExceptStart, l_strTrueStart);
            
            Date l_datExceptEnd = WEB3DateUtility.getDate("20080220", "yyyyMMdd");
            String l_strExceptEnd = WEB3DateUtility.formatDate(l_datExceptEnd, "yyyyMMdd");
            String l_strTrueEnd = WEB3DateUtility.formatDate(l_response.expirationEndDate, "yyyyMMdd");
            assertEquals(l_strExceptEnd, l_strTrueEnd);
            
            assertNull(l_response.holidayList);
            assertNull(l_response.sessionType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     *
     * 當l_request.commodityType = WEB3CommodityDivDef.OPTION時
     */
    public void testExecute_C0005()
    {
        final String STR_METHOD_NAME = "testExecute_C0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ExpirationDateListServiceImpl l_expirationDateListServiceImpl =
            new WEB3ExpirationDateListServiceImpl();
        
        WEB3ExpirationDateListRequest l_request =
            new WEB3ExpirationDateListRequest();
        l_request.commodityType = WEB3CommodityDivDef.OPTION;
        l_request.marketCode = WEB3MarketCodeDef.TOKYO;
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("2");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            Date l_dat = WEB3DateUtility.getDate("20080220", "yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_dat);
            
            WEB3ExpirationDateListResponse l_response =
                (WEB3ExpirationDateListResponse)l_expirationDateListServiceImpl.execute(l_request);
            
            assertEquals("1", l_response.expirationDateTypeList[0]);
            assertEquals("2", l_response.expirationDateTypeList[1]);
            
            Date l_datExceptStart = WEB3DateUtility.getDate("20080220", "yyyyMMdd");
            String l_strExceptStart = WEB3DateUtility.formatDate(l_datExceptStart, "yyyyMMdd");
            String l_strTrueStart = WEB3DateUtility.formatDate(l_response.expirationStartDate, "yyyyMMdd");
            assertEquals(l_strExceptStart, l_strTrueStart);
            
            Date l_datExceptEnd = WEB3DateUtility.getDate("20080220", "yyyyMMdd");
            String l_strExceptEnd = WEB3DateUtility.formatDate(l_datExceptEnd, "yyyyMMdd");
            String l_strTrueEnd = WEB3DateUtility.formatDate(l_response.expirationEndDate, "yyyyMMdd");
            assertEquals(l_strExceptEnd, l_strTrueEnd);
            
            assertNull(l_response.holidayList);
            assertNull(l_response.sessionType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

}
@
