head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqExecutionReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文約定照会サービスImplテスト(WEB3AdminFeqExecutionReferenceServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/27 趙林鵬 (中訊) 新規作成
*/

package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.ExchangeRateRow;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondRow;
import webbroker3.gentrade.data.FrgnMmfExchangeRateRow;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.data.MfSubAssetRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （管理者外国株式注文約定照会サービスImplテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminFeqExecutionReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminFeqExecutionReferenceServiceImplTest.class);

    public WEB3AdminFeqExecutionReferenceServiceImplTest(String arg0)
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

    public void test_getInputScreen_case0001()
    {
        final String STR_METHOD_NAME = " test_getInputScreen_case0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        this.deleteAll();
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminORFeqOrderExecutionRefInputRequest l_request = null;
        WEB3AdminORFeqOrderExecutionRefInputResponse l_response = null;
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImplForMock);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams =
            TestDBUtility.getFeqBranchMarketDealtCondRow();
        MarketPreferencesParams l_marketPreferencesParams = new MarketPreferencesParams();
        EnableOrderConditionParams l_enableOrderConditionParams = new EnableOrderConditionParams();

        l_marketParams.setMarketCode("SP");
        
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("0");
        
        l_marketPreferencesParams.setMarketId(3303L);
        l_marketPreferencesParams.setName("feq.sle.broker");
        l_marketPreferencesParams.setNameSerialNo(1);
        l_marketPreferencesParams.setValue("able");
        l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        
        l_enableOrderConditionParams.setInstitutionCode("0D");
        l_enableOrderConditionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_enableOrderConditionParams.setFutureOptionDiv("0");
        l_enableOrderConditionParams.setMarginTradingDiv("0");
        l_enableOrderConditionParams.setMarketCode("0");
        l_enableOrderConditionParams.setAtMarketOpen("1");
        l_enableOrderConditionParams.setStopOrder("1");
        l_enableOrderConditionParams.setCarriedOrder("1");
        l_enableOrderConditionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_enableOrderConditionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        
        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0402", false, true);
            
            l_request = new WEB3AdminORFeqOrderExecutionRefInputRequest();
            
            WEB3AdminFeqExecutionReferenceServiceImpl l_impl = new WEB3AdminFeqExecutionReferenceServiceImpl();

            String[] l_strbranchCode = {"381"};
            l_request.branchCode = l_strbranchCode;
            
            l_response = l_impl.getInputScreen(l_request);
            
            assertEquals("SP", l_response.marketList[0]);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);    

    }

    public void test_getInputScreen_case0002()
    {
        final String STR_METHOD_NAME = " test_getInputScreen_case0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        this.deleteAll();
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminORFeqOrderExecutionRefInputRequest l_request = null;
        WEB3AdminORFeqOrderExecutionRefInputResponse l_response = null;
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImplForMock);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams =
            TestDBUtility.getFeqBranchMarketDealtCondRow();
        MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
        EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();

        l_marketParams.setMarketCode("SP");
        
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("0");
        
        l_marketPreferencesParams.setMarketId(3304L);
        l_marketPreferencesParams.setName("feq.sle.broker");
        l_marketPreferencesParams.setNameSerialNo(1);
        l_marketPreferencesParams.setValue("able");
        l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        
        l_enableOrderConditionParams.setInstitutionCode("0D");
        l_enableOrderConditionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_enableOrderConditionParams.setFutureOptionDiv("0");
        l_enableOrderConditionParams.setMarginTradingDiv("0");
        l_enableOrderConditionParams.setMarketCode("0");
        l_enableOrderConditionParams.setAtMarketOpen("1");
        l_enableOrderConditionParams.setStopOrder("1");
        l_enableOrderConditionParams.setCarriedOrder("1");
        l_enableOrderConditionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_enableOrderConditionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        
        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0402", false, true);
            
            l_request = new WEB3AdminORFeqOrderExecutionRefInputRequest();
            
            WEB3AdminFeqExecutionReferenceServiceImpl l_impl = new WEB3AdminFeqExecutionReferenceServiceImpl();

            String[] l_strbranchCode = {"381"};
            l_request.branchCode = l_strbranchCode;
            
            l_response = l_impl.getInputScreen(l_request);
 
            assertEquals("SP", l_response.marketList[0]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);    

    }
   
    public void test_getInputScreen_case0003()
    {
        final String STR_METHOD_NAME = " test_getInputScreen_case0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        this.deleteAll();
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminORFeqOrderExecutionRefInputRequest l_request = null;
        WEB3AdminORFeqOrderExecutionRefInputResponse l_response = null;
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImplForMock);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
        FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams =
            TestDBUtility.getFeqBranchMarketDealtCondRow();
        FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams1 =
            TestDBUtility.getFeqBranchMarketDealtCondRow();
        FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams2 =
            TestDBUtility.getFeqBranchMarketDealtCondRow();
        MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
        EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();

        l_marketParams.setMarketCode("SP");
        l_marketParams1.setMarketId(3304);
        l_marketParams1.setMarketCode("SS");
        
        l_feqBranchMarketDealtCondParams1.setMarketCode("SS");
        l_feqBranchMarketDealtCondParams2.setMarketCode("PP");
        
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("0");
        
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("123");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SS");
        l_tradingTimeParams1.setProductCode("0");
        
        l_tradingTimeParams2.setInstitutionCode("0D");
        l_tradingTimeParams2.setBranchCode("123");
        l_tradingTimeParams2.setTradingTimeType("01");
        l_tradingTimeParams2.setBizDateType("1");
        l_tradingTimeParams2.setMarketCode("PP");
        l_tradingTimeParams2.setProductCode("0");
        
        l_marketPreferencesParams.setMarketId(3303L);
        l_marketPreferencesParams.setName("feq.sle.broker");
        l_marketPreferencesParams.setNameSerialNo(1);
        l_marketPreferencesParams.setValue("able");
        
        l_enableOrderConditionParams.setInstitutionCode("0D");
        l_enableOrderConditionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_enableOrderConditionParams.setFutureOptionDiv("0");
        l_enableOrderConditionParams.setMarginTradingDiv("0");
        l_enableOrderConditionParams.setMarketCode("0");
        l_enableOrderConditionParams.setAtMarketOpen("1");
        l_enableOrderConditionParams.setStopOrder("1");
        l_enableOrderConditionParams.setCarriedOrder("1");
        
        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_marketParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams1);
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams2);
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0402", false, true);
            
            l_request = new WEB3AdminORFeqOrderExecutionRefInputRequest();
            
            WEB3AdminFeqExecutionReferenceServiceImpl l_impl = new WEB3AdminFeqExecutionReferenceServiceImpl();

            String[] l_strbranchCode = {"381"};
            l_request.branchCode = l_strbranchCode;
            
            l_response = l_impl.getInputScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);    

    }

    public void test_getExecInputList_c0001()
    {
        final String STR_METHOD_NAME = "test_getExecInputList_c0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_admin);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_admin,
                "1",
                false,
                true);

            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_admin, "381", true);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NW");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            WEB3AdminFeqExecutionReferenceServiceImpl l_impl =
                new WEB3AdminFeqExecutionReferenceServiceImpl();
            WEB3AdminORFeqExecuteListRequest l_request = new WEB3AdminORFeqExecuteListRequestTest();
            l_request.branchCode = new String[]{"381"};
            l_impl.getExecInputList(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00398);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void test_getReferenceScreen_c0001()
    {
        final String STR_METHOD_NAME = "test_getReferenceScreen_c0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_admin);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_admin,
                "1",
                false,
                true);

            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_admin, "381", true);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NW");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            WEB3AdminFeqExecutionReferenceServiceImpl l_impl =
                new WEB3AdminFeqExecutionReferenceServiceImpl();
            WEB3AdminORFeqOrderExecutionRefReferenceRequest l_request =
                new WEB3AdminORFeqOrderExecutionRefReferenceRequestTest();
            l_request.branchCode = new String[]{"381"};
            l_impl.getReferenceScreen(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public class WEB3AdminORFeqExecuteListRequestTest extends WEB3AdminORFeqExecuteListRequest
    {
        public void validate() throws WEB3BaseException
        {
        }
    }

    public class WEB3AdminORFeqOrderExecutionRefReferenceRequestTest extends WEB3AdminORFeqOrderExecutionRefReferenceRequest
    {
        public void validate() throws WEB3BaseException
        {
        }
    }
    public void deleteAll()
    {
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAll(MfSubAssetRow.TYPE);
            TestDBUtility.deleteAll(MutualFundTradedProductRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(FrgnMmfExchangeRateRow.TYPE);
            TestDBUtility.deleteAll(ExchangeRateRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondRow.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info("*******************deleteAll***************** !!");
    }
   
}
@
