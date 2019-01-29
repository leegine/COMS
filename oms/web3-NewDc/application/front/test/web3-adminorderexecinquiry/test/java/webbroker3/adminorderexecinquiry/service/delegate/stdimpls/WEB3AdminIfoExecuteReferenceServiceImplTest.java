head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoExecuteReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者先物OP注文約定照会サービスImplTest(WEB3AdminIfoExecuteReferenceServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/04 張騰宇 (中訊) 新規作成
*/
package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.adminorderexecinquiry.WEB3AdminOrderExecuteDataManager;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionSortKeyUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoExecuteReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIfoExecuteReferenceServiceImplTest.class);

    public WEB3AdminIfoExecuteReferenceServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            
            TestDBUtility.deleteAll(TradedProductUpdqRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);

            TestDBUtility.deleteAll(ProductRow.TYPE);
           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        

        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,19);
        Date date = ca.getTime();
        this.setExpectedDate(date);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public static String BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE = "web3.attributes.basetimestampfororderbizdate";
    public class WEB3AdminORFutOpOrderExecutionRefInputRequestForMock 
        extends WEB3AdminORFutOpOrderExecutionRefInputRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3AdminORFutOpOrderExecutionRefInputRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    public class WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock 
        extends WEB3AdminORFutOpOrderExecutionRefReferenceRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    /*
     * Test method for 'webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminIfoExecuteReferenceServiceImpl.getInputScreen(WEB3AdminORFutOpOrderExecutionRefInputRequest)'
     */
    public void testGetInputScreenCase1()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setProductCode("2");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("2");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("0");
            
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            Date date = ca.getTime();
            this.setExpectedDate(date);
            
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            WEB3AdminORFutOpOrderExecutionRefInputRequestForMock l_request =
                new WEB3AdminORFutOpOrderExecutionRefInputRequestForMock();
            String[] l_branchCodes = new String[1];
            l_branchCodes[0] = "123";
            l_request.branchCode = l_branchCodes;
            
            WEB3AdminORFutOpOrderExecutionRefInputResponse l_response =
                l_impl.getInputScreen(l_request);
            assertEquals(1, l_response.expirationDateTypeList.length);
            assertEquals("1", l_response.expirationDateTypeList[0]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreenCase2()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setProductCode("2");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setBizDateType("1");
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            Date date = ca.getTime();
            this.setExpectedDate(date);
            
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            WEB3AdminORFutOpOrderExecutionRefInputRequestForMock l_request =
                new WEB3AdminORFutOpOrderExecutionRefInputRequestForMock();
            String[] l_branchCodes = new String[1];
            l_branchCodes[0] = "123";
            l_request.branchCode = l_branchCodes;
            
            WEB3AdminORFutOpOrderExecutionRefInputResponse l_response =
                l_impl.getInputScreen(l_request);
            assertEquals(2, l_response.expirationDateTypeList.length);
            assertEquals("1", l_response.expirationDateTypeList[0]);
            assertEquals("2", l_response.expirationDateTypeList[1]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.adminorderexecinquiry.service.delegate.stdimpls.WEB3AdminIfoExecuteReferenceServiceImpl.getReferenceScreen(WEB3AdminORFutOpOrderExecutionRefReferenceRequest)'
     */
    public void testGetReferenceScreenCase1()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreenCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("123");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            
            OrderexecutionEndParams l_OrderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams.setInstitutionCode("0D");
            l_OrderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_OrderexecutionEndParams.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams.setCarryoverEndType("1");
            OrderexecutionEndParams l_OrderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams1.setInstitutionCode("0D");
            l_OrderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_OrderexecutionEndParams1.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams1.setCarryoverEndType("2");
            
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {

            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock();
            String[] l_branchCodes = new String[1];
            l_branchCodes[0] = "123";
            l_request.branchCode = l_branchCodes;
            WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeys = new WEB3AdminOROrderExecutionSortKeyUnit[1];
            WEB3AdminOROrderExecutionSortKeyUnit l_sortKey = new WEB3AdminOROrderExecutionSortKeyUnit();
            l_sortKey.ascDesc = "A";
            l_sortKeys[0] = l_sortKey;
            l_request.sortKeys = l_sortKeys;
            
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AdminORFutOpOrderExecutionRefReferenceResponse l_response =
                l_impl.getReferenceScreen(l_request);
            assertEquals("1", l_response.opCarryoverEndType);
            assertEquals("2", l_response.fuCarryoverEndType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * 
     */
    public void testCreateFutOpOrderExecutionRefReferenceUnitListCase1()
    {
        final String STR_METHOD_NAME = "testCreateFutOpOrderExecutionRefReferenceUnitListCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("123");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(101001010010L);
            l_MainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(123);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            
            OrderexecutionEndParams l_OrderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams.setInstitutionCode("0D");
            l_OrderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_OrderexecutionEndParams.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams.setCarryoverEndType("1");
            OrderexecutionEndParams l_OrderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams1.setInstitutionCode("0D");
            l_OrderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_OrderexecutionEndParams1.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams1.setCarryoverEndType("2");
            
            
            ProductParams l_productParams =  TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3301L);
            
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060005L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3301L);
            
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            MarketParams l_marketParams =TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3301L);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3301L);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setExecutedQuantity(10);
            l_IfoOrderUnitParams1.setBranchId(123);
            l_IfoOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_IfoOrderUnitParams1.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            l_IfoOrderUnitParams1.setBizDate("20040910");
            l_IfoOrderUnitParams1.setOrderUnitId(1001);
            l_IfoOrderUnitParams1.setAccountId(101001010010L);
            l_IfoOrderUnitParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_IfoOrderUnitParams1.setProductId(1006160060005L);
            l_IfoOrderUnitParams1.setEstimatedPrice(100);
            l_IfoOrderUnitParams1.setExecutedAmount(1000000);
            l_IfoOrderUnitParams1.setMarketId(3301);
            l_IfoOrderUnitParams1.setExpirationDate(WEB3DateUtility.getDate("20080706","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);
            IfoOrderUnit[] l_ifoOrderUnits = new IfoOrderUnit[1];
            l_ifoOrderUnits[0] = l_ifoOrderUnit;
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            
            WEB3AdminORFutOpOrderExecutionRefUnit[] l_ORFutOpOrderExecutionRefUnits =
                l_impl.createFutOpOrderExecutionRefReferenceUnitList(l_ifoOrderUnits);
            WEB3AdminORFutOpOrderExecutionRefUnit l_ORFutOpOrderExecutionRefUnit =
                new WEB3AdminORFutOpOrderExecutionRefUnit();
            l_ORFutOpOrderExecutionRefUnit = l_ORFutOpOrderExecutionRefUnits[0];
            assertFalse(l_ORFutOpOrderExecutionRefUnit.eveningSessionCarryoverFlag);
            assertEquals(l_ORFutOpOrderExecutionRefUnit.execPrice,"10");
            assertNull(l_ORFutOpOrderExecutionRefUnit.sessionType);
            assertNull(l_ORFutOpOrderExecutionRefUnit.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateFutOpOrderExecutionRefReferenceUnitListCase2()
    {
        final String STR_METHOD_NAME = "testCreateFutOpOrderExecutionRefReferenceUnitListCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("123");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(101001010010L);
            l_MainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(123);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            
            OrderexecutionEndParams l_OrderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams.setInstitutionCode("0D");
            l_OrderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_OrderexecutionEndParams.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams.setCarryoverEndType("1");
            OrderexecutionEndParams l_OrderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams1.setInstitutionCode("0D");
            l_OrderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_OrderexecutionEndParams1.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams1.setCarryoverEndType("2");
            
            
            ProductParams l_productParams =  TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3301L);
            
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060005L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3301L);
            
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            MarketParams l_marketParams =TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3301L);
            
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3301L);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit",
                    new Class[] {IfoOrderUnit.class },
                    new Boolean(true));
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setExecutedQuantity(10);
            l_IfoOrderUnitParams1.setBranchId(123);
            l_IfoOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_IfoOrderUnitParams1.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            l_IfoOrderUnitParams1.setBizDate("20040910");
            l_IfoOrderUnitParams1.setOrderUnitId(1001);
            l_IfoOrderUnitParams1.setOrderRootDiv("1");
            l_IfoOrderUnitParams1.setAccountId(101001010010L);
            l_IfoOrderUnitParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_IfoOrderUnitParams1.setProductId(1006160060005L);
            l_IfoOrderUnitParams1.setEstimatedPrice(100);
            l_IfoOrderUnitParams1.setMarketId(3301);
            l_IfoOrderUnitParams1.setExecutedAmount(100000);
            l_IfoOrderUnitParams1.setExpirationDate(WEB3DateUtility.getDate("20080706","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);
            IfoOrderUnit[] l_ifoOrderUnits = new IfoOrderUnit[1];
            l_ifoOrderUnits[0] = l_ifoOrderUnit;
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            
            WEB3AdminORFutOpOrderExecutionRefUnit[] l_ORFutOpOrderExecutionRefUnits =
                l_impl.createFutOpOrderExecutionRefReferenceUnitList(l_ifoOrderUnits);
            WEB3AdminORFutOpOrderExecutionRefUnit l_ORFutOpOrderExecutionRefUnit =
                new WEB3AdminORFutOpOrderExecutionRefUnit();
            l_ORFutOpOrderExecutionRefUnit = l_ORFutOpOrderExecutionRefUnits[0];
            assertEquals("1", l_ORFutOpOrderExecutionRefUnit.orderRootDiv);
            assertEquals(l_ORFutOpOrderExecutionRefUnit.execPrice,"1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'createQueryString(String[], WEB3AdminORFutOpOrderExecutionRefReferenceRequest)'
     */
    public void testCreateQueryStringAndCreateQueryDataContainerCase1()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringAndCreateQueryDataContainerCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("123");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            
            OrderexecutionEndParams l_OrderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams.setInstitutionCode("0D");
            l_OrderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_OrderexecutionEndParams.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams.setCarryoverEndType("1");
            OrderexecutionEndParams l_OrderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams1.setInstitutionCode("0D");
            l_OrderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_OrderexecutionEndParams1.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams1.setCarryoverEndType("2");
            
            this.setIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock();
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderState = WEB3OrderStatusDef.PART_INAFFECTED;
            String[] l_branchCodes = new String[1];
            l_branchCodes[0] = "123";
            l_request.branchCode = l_branchCodes;
            String l_strSortCond = "ifo_order_unit.delivery_date asc, ifo_order_unit.last_updated_timestamp asc";
            
            WEB3AdminOrderExecuteDataManager l_adminOrderExecDataManager =
                new WEB3AdminOrderExecuteDataManager();
            String l_strCommonQueryString =
                l_adminOrderExecDataManager.createCommonQueryString(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest)l_request);
            
            
            String[] l_strQueryCondDataContainers = null;
            String[] l_strCommonQueryDataContainers = null;
            String[] l_strQueryDataContainers = null;
            int l_intQueryCondDataContainerCnt = 0;
            int l_intCommonDataContainerCnt = 0;
            int l_intQueryCondContainerCnt = 0;       
            String l_strQueryString = l_impl.createQueryString(null,l_request);
            l_strQueryDataContainers =l_impl.createQueryDataContainer(null,l_request);
            
            l_strCommonQueryDataContainers =
                l_adminOrderExecDataManager.createCommonQueryDataContainer(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest) l_request);
            
            String l_strQueryCond = l_strCommonQueryString.concat(l_strQueryString);
            l_intCommonDataContainerCnt = l_strCommonQueryDataContainers.length;
            l_intQueryCondContainerCnt = l_strQueryDataContainers.length;
            l_intQueryCondDataContainerCnt = l_intCommonDataContainerCnt + l_intQueryCondContainerCnt;
            l_strQueryCondDataContainers = new String[l_intQueryCondDataContainerCnt];

            System.arraycopy(
                l_strCommonQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                0,
                l_intCommonDataContainerCnt);
            System.arraycopy(
                l_strQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                l_intCommonDataContainerCnt,
                l_intQueryCondContainerCnt);
           
            
            IfoOrderUnitParams[] l_orderUnits =
                l_impl.getOrderUnits(l_strQueryCond, l_strQueryCondDataContainers, l_strSortCond);
            assertEquals(1, l_orderUnits.length);
            assertEquals(1001,l_orderUnits[0].getOrderUnitId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testCreateQueryStringAndCreateQueryDataContainerCase2()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringAndCreateQueryDataContainerCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("123");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            
            OrderexecutionEndParams l_OrderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams.setInstitutionCode("0D");
            l_OrderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_OrderexecutionEndParams.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams.setCarryoverEndType("1");
            OrderexecutionEndParams l_OrderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams1.setInstitutionCode("0D");
            l_OrderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_OrderexecutionEndParams1.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams1.setCarryoverEndType("2");
            
            this.setIfoOrderUnitRow();

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock();
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_request.orderState = WEB3OrderStatusDef.FULL_INAFFECTED;
            String[] l_branchCodes = new String[1];
            l_branchCodes[0] = "123";
            l_request.branchCode = l_branchCodes;
            String l_strSortCond = "ifo_order_unit.delivery_date asc, ifo_order_unit.last_updated_timestamp asc";
            
            WEB3AdminOrderExecuteDataManager l_adminOrderExecDataManager =
                new WEB3AdminOrderExecuteDataManager();
            String l_strCommonQueryString =
                l_adminOrderExecDataManager.createCommonQueryString(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest)l_request);
            
            
            String[] l_strQueryCondDataContainers = null;
            String[] l_strCommonQueryDataContainers = null;
            String[] l_strQueryDataContainers = null;
            int l_intQueryCondDataContainerCnt = 0;
            int l_intCommonDataContainerCnt = 0;
            int l_intQueryCondContainerCnt = 0;       
            String l_strQueryString = l_impl.createQueryString(null,l_request);
            l_strQueryDataContainers =l_impl.createQueryDataContainer(null,l_request);
            
            l_strCommonQueryDataContainers =
                l_adminOrderExecDataManager.createCommonQueryDataContainer(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest) l_request);
            
            String l_strQueryCond = l_strCommonQueryString.concat(l_strQueryString);
            l_intCommonDataContainerCnt = l_strCommonQueryDataContainers.length;
            l_intQueryCondContainerCnt = l_strQueryDataContainers.length;
            l_intQueryCondDataContainerCnt = l_intCommonDataContainerCnt + l_intQueryCondContainerCnt;
            l_strQueryCondDataContainers = new String[l_intQueryCondDataContainerCnt];

            System.arraycopy(
                l_strCommonQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                0,
                l_intCommonDataContainerCnt);
            System.arraycopy(
                l_strQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                l_intCommonDataContainerCnt,
                l_intQueryCondContainerCnt);
           
            
            IfoOrderUnitParams[] l_orderUnits =
                l_impl.getOrderUnits(l_strQueryCond, l_strQueryCondDataContainers, l_strSortCond);
            assertEquals(1, l_orderUnits.length);
            assertEquals(1002,l_orderUnits[0].getOrderUnitId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testCreateQueryStringAndCreateQueryDataContainerCase3()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringAndCreateQueryDataContainerCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("123");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            
            OrderexecutionEndParams l_OrderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams.setInstitutionCode("0D");
            l_OrderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_OrderexecutionEndParams.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams.setCarryoverEndType("1");
            OrderexecutionEndParams l_OrderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams1.setInstitutionCode("0D");
            l_OrderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_OrderexecutionEndParams1.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams1.setCarryoverEndType("2");
            
            this.setIfoOrderUnitRow();

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock();
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER;
            l_request.orderState = WEB3OrderStatusDef.CLOSED;
            String[] l_branchCodes = new String[1];
            l_branchCodes[0] = "123";
            l_request.branchCode = l_branchCodes;
            String l_strSortCond = "ifo_order_unit.delivery_date asc, ifo_order_unit.last_updated_timestamp asc";
            
            WEB3AdminOrderExecuteDataManager l_adminOrderExecDataManager =
                new WEB3AdminOrderExecuteDataManager();
            String l_strCommonQueryString =
                l_adminOrderExecDataManager.createCommonQueryString(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest)l_request);
            
            
            String[] l_strQueryCondDataContainers = null;
            String[] l_strCommonQueryDataContainers = null;
            String[] l_strQueryDataContainers = null;
            int l_intQueryCondDataContainerCnt = 0;
            int l_intCommonDataContainerCnt = 0;
            int l_intQueryCondContainerCnt = 0;       
            String l_strQueryString = l_impl.createQueryString(null,l_request);
            l_strQueryDataContainers =l_impl.createQueryDataContainer(null,l_request);
            
            l_strCommonQueryDataContainers =
                l_adminOrderExecDataManager.createCommonQueryDataContainer(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest) l_request);
            
            String l_strQueryCond = l_strCommonQueryString.concat(l_strQueryString);
            l_intCommonDataContainerCnt = l_strCommonQueryDataContainers.length;
            l_intQueryCondContainerCnt = l_strQueryDataContainers.length;
            l_intQueryCondDataContainerCnt = l_intCommonDataContainerCnt + l_intQueryCondContainerCnt;
            l_strQueryCondDataContainers = new String[l_intQueryCondDataContainerCnt];

            System.arraycopy(
                l_strCommonQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                0,
                l_intCommonDataContainerCnt);
            System.arraycopy(
                l_strQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                l_intCommonDataContainerCnt,
                l_intQueryCondContainerCnt);
           
            
            IfoOrderUnitParams[] l_orderUnits =
                l_impl.getOrderUnits(l_strQueryCond, l_strQueryCondDataContainers, l_strSortCond);
            assertEquals(1, l_orderUnits.length);
            assertEquals(1003,l_orderUnits[0].getOrderUnitId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testCreateQueryStringAndCreateQueryDataContainerCase4()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringAndCreateQueryDataContainerCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("123");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            
            OrderexecutionEndParams l_OrderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams.setInstitutionCode("0D");
            l_OrderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_OrderexecutionEndParams.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams.setCarryoverEndType("1");
            OrderexecutionEndParams l_OrderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams1.setInstitutionCode("0D");
            l_OrderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_OrderexecutionEndParams1.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams1.setCarryoverEndType("2");
            
            this.setIfoOrderUnitRow();

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock();
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            l_request.orderState = WEB3OrderStatusDef.MANUAL_EXPIRED;
            String[] l_branchCodes = new String[1];
            l_branchCodes[0] = "123";
            l_request.branchCode = l_branchCodes;
            String l_strSortCond = "ifo_order_unit.delivery_date asc, ifo_order_unit.last_updated_timestamp asc";
            
            WEB3AdminOrderExecuteDataManager l_adminOrderExecDataManager =
                new WEB3AdminOrderExecuteDataManager();
            String l_strCommonQueryString =
                l_adminOrderExecDataManager.createCommonQueryString(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest)l_request);
            
            
            String[] l_strQueryCondDataContainers = null;
            String[] l_strCommonQueryDataContainers = null;
            String[] l_strQueryDataContainers = null;
            int l_intQueryCondDataContainerCnt = 0;
            int l_intCommonDataContainerCnt = 0;
            int l_intQueryCondContainerCnt = 0;       
            String l_strQueryString = l_impl.createQueryString(null,l_request);
            l_strQueryDataContainers =l_impl.createQueryDataContainer(null,l_request);
            
            l_strCommonQueryDataContainers =
                l_adminOrderExecDataManager.createCommonQueryDataContainer(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest) l_request);
            
            String l_strQueryCond = l_strCommonQueryString.concat(l_strQueryString);
            l_intCommonDataContainerCnt = l_strCommonQueryDataContainers.length;
            l_intQueryCondContainerCnt = l_strQueryDataContainers.length;
            l_intQueryCondDataContainerCnt = l_intCommonDataContainerCnt + l_intQueryCondContainerCnt;
            l_strQueryCondDataContainers = new String[l_intQueryCondDataContainerCnt];

            System.arraycopy(
                l_strCommonQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                0,
                l_intCommonDataContainerCnt);
            System.arraycopy(
                l_strQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                l_intCommonDataContainerCnt,
                l_intQueryCondContainerCnt);
           
            
            IfoOrderUnitParams[] l_orderUnits =
                l_impl.getOrderUnits(l_strQueryCond, l_strQueryCondDataContainers, l_strSortCond);
            assertEquals(1, l_orderUnits.length);
            assertEquals(1004,l_orderUnits[0].getOrderUnitId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testCreateQueryStringAndCreateQueryDataContainerCase5()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringAndCreateQueryDataContainerCase5()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("123");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            
            OrderexecutionEndParams l_OrderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams.setInstitutionCode("0D");
            l_OrderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_OrderexecutionEndParams.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams.setCarryoverEndType("1");
            OrderexecutionEndParams l_OrderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams1.setInstitutionCode("0D");
            l_OrderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_OrderexecutionEndParams1.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams1.setCarryoverEndType("2");
            
            this.setIfoOrderUnitRow();

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock();
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_request.orderState = WEB3OrderStatusDef.TRANSFERED;
            String[] l_branchCodes = new String[1];
            l_branchCodes[0] = "123";
            l_request.branchCode = l_branchCodes;
            String l_strSortCond = "ifo_order_unit.delivery_date asc, ifo_order_unit.last_updated_timestamp asc";
            
            WEB3AdminOrderExecuteDataManager l_adminOrderExecDataManager =
                new WEB3AdminOrderExecuteDataManager();
            String l_strCommonQueryString =
                l_adminOrderExecDataManager.createCommonQueryString(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest)l_request);
            
            
            String[] l_strQueryCondDataContainers = null;
            String[] l_strCommonQueryDataContainers = null;
            String[] l_strQueryDataContainers = null;
            int l_intQueryCondDataContainerCnt = 0;
            int l_intCommonDataContainerCnt = 0;
            int l_intQueryCondContainerCnt = 0;       
            String l_strQueryString = l_impl.createQueryString(null,l_request);
            l_strQueryDataContainers =l_impl.createQueryDataContainer(null,l_request);
            
            l_strCommonQueryDataContainers =
                l_adminOrderExecDataManager.createCommonQueryDataContainer(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest) l_request);
            
            String l_strQueryCond = l_strCommonQueryString.concat(l_strQueryString);
            l_intCommonDataContainerCnt = l_strCommonQueryDataContainers.length;
            l_intQueryCondContainerCnt = l_strQueryDataContainers.length;
            l_intQueryCondDataContainerCnt = l_intCommonDataContainerCnt + l_intQueryCondContainerCnt;
            l_strQueryCondDataContainers = new String[l_intQueryCondDataContainerCnt];

            System.arraycopy(
                l_strCommonQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                0,
                l_intCommonDataContainerCnt);
            System.arraycopy(
                l_strQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                l_intCommonDataContainerCnt,
                l_intQueryCondContainerCnt);
           
            
            IfoOrderUnitParams[] l_orderUnits =
                l_impl.getOrderUnits(l_strQueryCond, l_strQueryCondDataContainers, l_strSortCond);
            assertEquals(1, l_orderUnits.length);
            assertEquals(1005,l_orderUnits[0].getOrderUnitId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testCreateQueryStringAndCreateQueryDataContainerCase6()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringAndCreateQueryDataContainerCase6()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("123");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            
            OrderexecutionEndParams l_OrderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams.setInstitutionCode("0D");
            l_OrderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_OrderexecutionEndParams.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams.setCarryoverEndType("1");
            OrderexecutionEndParams l_OrderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams1.setInstitutionCode("0D");
            l_OrderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_OrderexecutionEndParams1.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams1.setCarryoverEndType("2");
            
            this.setIfoOrderUnitRow();

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock();
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_request.orderState = WEB3OrderStatusDef.NOT_TRANSFERED;
            String[] l_branchCodes = new String[1];
            l_branchCodes[0] = "123";
            l_request.branchCode = l_branchCodes;
            String l_strSortCond = "ifo_order_unit.delivery_date asc, ifo_order_unit.last_updated_timestamp asc";
            
            WEB3AdminOrderExecuteDataManager l_adminOrderExecDataManager =
                new WEB3AdminOrderExecuteDataManager();
            String l_strCommonQueryString =
                l_adminOrderExecDataManager.createCommonQueryString(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest)l_request);
            
            
            String[] l_strQueryCondDataContainers = null;
            String[] l_strCommonQueryDataContainers = null;
            String[] l_strQueryDataContainers = null;
            int l_intQueryCondDataContainerCnt = 0;
            int l_intCommonDataContainerCnt = 0;
            int l_intQueryCondContainerCnt = 0;       
            String l_strQueryString = l_impl.createQueryString(null,l_request);
            l_strQueryDataContainers =l_impl.createQueryDataContainer(null,l_request);
            
            l_strCommonQueryDataContainers =
                l_adminOrderExecDataManager.createCommonQueryDataContainer(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest) l_request);
            
            String l_strQueryCond = l_strCommonQueryString.concat(l_strQueryString);
            l_intCommonDataContainerCnt = l_strCommonQueryDataContainers.length;
            l_intQueryCondContainerCnt = l_strQueryDataContainers.length;
            l_intQueryCondDataContainerCnt = l_intCommonDataContainerCnt + l_intQueryCondContainerCnt;
            l_strQueryCondDataContainers = new String[l_intQueryCondDataContainerCnt];

            System.arraycopy(
                l_strCommonQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                0,
                l_intCommonDataContainerCnt);
            System.arraycopy(
                l_strQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                l_intCommonDataContainerCnt,
                l_intQueryCondContainerCnt);
           
            
            IfoOrderUnitParams[] l_orderUnits =
                l_impl.getOrderUnits(l_strQueryCond, l_strQueryCondDataContainers, l_strSortCond);
            assertEquals(1, l_orderUnits.length);
            assertEquals(1006,l_orderUnits[0].getOrderUnitId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testCreateQueryStringAndCreateQueryDataContainerCase7()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringAndCreateQueryDataContainerCase7()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("123");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            
            OrderexecutionEndParams l_OrderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams.setInstitutionCode("0D");
            l_OrderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_OrderexecutionEndParams.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams.setCarryoverEndType("1");
            OrderexecutionEndParams l_OrderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams1.setInstitutionCode("0D");
            l_OrderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_OrderexecutionEndParams1.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams1.setCarryoverEndType("2");
            
            this.setIfoOrderUnitRow();

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock();
            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            l_request.orderState = WEB3OrderStatusDef.MODIFYING;
            String[] l_branchCodes = new String[1];
            l_branchCodes[0] = "123";
            l_request.branchCode = l_branchCodes;
            String l_strSortCond = "ifo_order_unit.delivery_date asc, ifo_order_unit.last_updated_timestamp asc";
            
            WEB3AdminOrderExecuteDataManager l_adminOrderExecDataManager =
                new WEB3AdminOrderExecuteDataManager();
            String l_strCommonQueryString =
                l_adminOrderExecDataManager.createCommonQueryString(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest)l_request);
            
            
            String[] l_strQueryCondDataContainers = null;
            String[] l_strCommonQueryDataContainers = null;
            String[] l_strQueryDataContainers = null;
            int l_intQueryCondDataContainerCnt = 0;
            int l_intCommonDataContainerCnt = 0;
            int l_intQueryCondContainerCnt = 0;       
            String l_strQueryString = l_impl.createQueryString(null,l_request);
            l_strQueryDataContainers =l_impl.createQueryDataContainer(null,l_request);
            
            l_strCommonQueryDataContainers =
                l_adminOrderExecDataManager.createCommonQueryDataContainer(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest) l_request);
            
            String l_strQueryCond = l_strCommonQueryString.concat(l_strQueryString);
            l_intCommonDataContainerCnt = l_strCommonQueryDataContainers.length;
            l_intQueryCondContainerCnt = l_strQueryDataContainers.length;
            l_intQueryCondDataContainerCnt = l_intCommonDataContainerCnt + l_intQueryCondContainerCnt;
            l_strQueryCondDataContainers = new String[l_intQueryCondDataContainerCnt];

            System.arraycopy(
                l_strCommonQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                0,
                l_intCommonDataContainerCnt);
            System.arraycopy(
                l_strQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                l_intCommonDataContainerCnt,
                l_intQueryCondContainerCnt);
           
            
            IfoOrderUnitParams[] l_orderUnits =
                l_impl.getOrderUnits(l_strQueryCond, l_strQueryCondDataContainers, l_strSortCond);
            assertEquals(1, l_orderUnits.length);
            assertEquals(1007,l_orderUnits[0].getOrderUnitId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testCreateQueryStringAndCreateQueryDataContainerCase8()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringAndCreateQueryDataContainerCase8()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("123");
            
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.XBIFO_ORDER_EXEC_INQUIRY);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setOptionDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            l_branchParams.setFutureDiv(WEB3EnforcementDef.ENFORCEMENT);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            
            OrderexecutionEndParams l_OrderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams.setInstitutionCode("0D");
            l_OrderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_OrderexecutionEndParams.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams.setCarryoverEndType("1");
            OrderexecutionEndParams l_OrderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_OrderexecutionEndParams1.setInstitutionCode("0D");
            l_OrderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_OrderexecutionEndParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_OrderexecutionEndParams1.setOrderexecutionEndType("0");
            l_OrderexecutionEndParams1.setCarryoverEndType("2");
            
            this.setIfoOrderUnitRow();

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams);
            TestDBUtility.insertWithDel(l_OrderexecutionEndParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequestForMock();
//            l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
//            l_request.orderState = WEB3OrderStatusDef.MODIFYING_CHANGEORDER;
            String[] l_branchCodes = new String[1];
            l_branchCodes[0] = "123";
            l_request.branchCode = l_branchCodes;
            String l_strSortCond = "ifo_order_unit.delivery_date asc, ifo_order_unit.last_updated_timestamp asc";
            
            WEB3AdminOrderExecuteDataManager l_adminOrderExecDataManager =
                new WEB3AdminOrderExecuteDataManager();
            String l_strCommonQueryString =
                l_adminOrderExecDataManager.createCommonQueryString(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest)l_request);
            
            
            String[] l_strQueryCondDataContainers = null;
            String[] l_strCommonQueryDataContainers = null;
            String[] l_strQueryDataContainers = null;
            int l_intQueryCondDataContainerCnt = 0;
            int l_intCommonDataContainerCnt = 0;
            int l_intQueryCondContainerCnt = 0;       
            String l_strQueryString = l_impl.createQueryString(null,l_request);
            l_strQueryDataContainers =l_impl.createQueryDataContainer(null,l_request);
            
            l_strCommonQueryDataContainers =
                l_adminOrderExecDataManager.createCommonQueryDataContainer(
                    "0D",
                    (WEB3AdminOROrderExecutionRefCommonRequest) l_request);
            
            String l_strQueryCond = l_strCommonQueryString.concat(l_strQueryString);
            l_intCommonDataContainerCnt = l_strCommonQueryDataContainers.length;
            l_intQueryCondContainerCnt = l_strQueryDataContainers.length;
            l_intQueryCondDataContainerCnt = l_intCommonDataContainerCnt + l_intQueryCondContainerCnt;
            l_strQueryCondDataContainers = new String[l_intQueryCondDataContainerCnt];

            System.arraycopy(
                l_strCommonQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                0,
                l_intCommonDataContainerCnt);
            System.arraycopy(
                l_strQueryDataContainers,
                0,
                l_strQueryCondDataContainers,
                l_intCommonDataContainerCnt,
                l_intQueryCondContainerCnt);
           
            
            IfoOrderUnitParams[] l_orderUnits =
                l_impl.getOrderUnits(l_strQueryCond, l_strQueryCondDataContainers, l_strSortCond);
            assertEquals(7, l_orderUnits.length);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * パラメータ.銘柄ID一覧 = null　@且つ　@パラメータ.リクエストデータ.指数種別 != nullの場合、
     */
    public void testCreateQueryStringAndCreateQueryDataContainerCase9()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringAndCreateQueryDataContainerCase9()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(1006169090005L);
            l_ifoOrderUnitParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            WEB3AdminORFutOpOrderExecutionRefReferenceRequest l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequest();
            l_request.targetProductCode = "0005";

            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            String l_strQueryCond = l_impl.createQueryString(null, l_request);
            String[] l_strQueryCondDataContainer = l_impl.createQueryDataContainer(null, l_request);

            List l_lisSearchResult = null;
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    "order_unit_id = 1001 " + l_strQueryCond,
                    l_strQueryCondDataContainer);
            IfoOrderUnitRow l_return = (IfoOrderUnitRow)l_lisSearchResult.get(0);

            assertEquals(1, l_lisSearchResult.size());
            assertEquals(1001, l_return.getOrderId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * パラメータ.銘柄ID一覧 = null　@且つ　@パラメータ.リクエストデータ.指数種別 != nullの場合、
     */
    public void testCreateQueryString_C0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminORFutOpOrderExecutionRefReferenceRequest l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequest();
            l_request.targetProductCode = "0005";

            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            String l_strReturn = l_impl.createQueryString(null, l_request);

            assertEquals(" and product_id like ? ", l_strReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * パラメータ.銘柄ID一覧 = null　@且つ　@パラメータ.リクエストデータ.指数種別 != nullの場合、
     */
    public void testCreateQueryDataContainer_C0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminORFutOpOrderExecutionRefReferenceRequest l_request =
                new WEB3AdminORFutOpOrderExecutionRefReferenceRequest();
            l_request.targetProductCode = "0005";

            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            String[] l_strReturns = l_impl.createQueryDataContainer(null, l_request);

            assertEquals(1, l_strReturns.length);
            assertEquals("%05", l_strReturns[0]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * １）以下のパラメータが全てnullの場合は、nullを返却する。
     * 　@・限月
     * 　@・行使価格
     * 　@・オプション商品区分
     */
    public void testGetFutOpProductIdList_C0001()
    {
        final String STR_METHOD_NAME = "testGetFutOpProductIdList_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strInstitutionCode = "0D";
            String l_targetProduct = null;
            String l_deliveryMonth = null;
            String l_strikePrice = null;
            String l_opProductType = null;

            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            String[] l_strReturns = l_impl.getFutOpProductIdList(
                l_strInstitutionCode,
                l_targetProduct,
                l_deliveryMonth,
                l_strikePrice,
                l_opProductType);

            assertEquals(null, l_strReturns);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * 以下のパラメータが非全てnullの場合
     * 　@・限月
     */
    public void testGetFutOpProductIdList_C0002()
    {
        final String STR_METHOD_NAME = "testGetFutOpProductIdList_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strInstitutionCode = "0D";
            String l_targetProduct = "0005";
            String l_deliveryMonth = "200903";
            String l_strikePrice = null;
            String l_opProductType = null;

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(1006160060005L);
            l_ifoProductParams1.setProductCode("160030005");
            l_ifoProductParams1.setUnderlyingProductCode("0005");
            l_ifoProductParams1.setSplitType("123");
            l_ifoProductParams1.setInstitutionCode("0D");
            l_ifoProductParams1.setMonthOfDelivery("200903");
            TestDBUtility.insertWithDel(l_ifoProductParams1);

            IfoProductParams l_ifoProductParams2 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams2.setProductId(1006160060006L);
            l_ifoProductParams2.setProductCode("160030006");
            l_ifoProductParams2.setUnderlyingProductCode("0005");
            l_ifoProductParams1.setSplitType("321");
            l_ifoProductParams2.setInstitutionCode("0D");
            l_ifoProductParams2.setMonthOfDelivery("200903");
            TestDBUtility.insertWithDel(l_ifoProductParams2);

            WEB3AdminIfoExecuteReferenceServiceImpl l_impl =
                new WEB3AdminIfoExecuteReferenceServiceImpl();
            String[] l_strReturns = l_impl.getFutOpProductIdList(
                l_strInstitutionCode,
                l_targetProduct,
                l_deliveryMonth,
                l_strikePrice,
                l_opProductType);

            assertEquals(2, l_strReturns.length);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    private void setIfoOrderUnitRow()
    {
        final String STR_METHOD_NAME = "setIfoOrderUnitRow()";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
        l_IfoOrderUnitParams1.setExecutedQuantity(10);
        l_IfoOrderUnitParams1.setBranchId(33381);
        l_IfoOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_IfoOrderUnitParams1.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
        l_IfoOrderUnitParams1.setBizDate("20990910");
        l_IfoOrderUnitParams1.setOrderUnitId(1001);
        l_IfoOrderUnitParams1.setAccountId(101001010010L);
        l_IfoOrderUnitParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
        l_IfoOrderUnitParams1.setProductId(1006160060005L);
        l_IfoOrderUnitParams1.setEstimatedPrice(100);
        l_IfoOrderUnitParams1.setMarketId(3301);
        l_IfoOrderUnitParams1.setFirstOrderUnitId(null);
        
        IfoOrderUnitParams l_IfoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
        l_IfoOrderUnitParams2.setOrderUnitId(1002);
        l_IfoOrderUnitParams2.setAccountId(101001010020L);
        l_IfoOrderUnitParams2.setFirstOrderUnitId(1002);
        l_IfoOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_IfoOrderUnitParams2.setBizDate("20990910");
        l_IfoOrderUnitParams2.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
        
        IfoOrderUnitParams l_IfoOrderUnitParams3 = TestDBUtility.getIfoOrderUnitRow();
        l_IfoOrderUnitParams3.setOrderUnitId(1003);
        l_IfoOrderUnitParams3.setAccountId(101001010030L);
        l_IfoOrderUnitParams3.setBizDate("20990910");
        l_IfoOrderUnitParams3.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_IfoOrderUnitParams3.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_IfoOrderUnitParams3.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        
        IfoOrderUnitParams l_IfoOrderUnitParams4 = TestDBUtility.getIfoOrderUnitRow();
        l_IfoOrderUnitParams4.setOrderUnitId(1004);
        l_IfoOrderUnitParams4.setAccountId(101001010040L);
        l_IfoOrderUnitParams4.setBizDate("20990910");
        l_IfoOrderUnitParams4.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
        l_IfoOrderUnitParams4.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_IfoOrderUnitParams4.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
        l_IfoOrderUnitParams4.setErrorReasonCode("W004");
        
        IfoOrderUnitParams l_IfoOrderUnitParams5 = TestDBUtility.getIfoOrderUnitRow();
        l_IfoOrderUnitParams5.setOrderUnitId(1005);
        l_IfoOrderUnitParams5.setAccountId(101001010050L);
        l_IfoOrderUnitParams5.setBizDate("20990910");
        l_IfoOrderUnitParams5.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_IfoOrderUnitParams5.setFirstOrderUnitId(1005);
        l_IfoOrderUnitParams5.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
        
        IfoOrderUnitParams l_IfoOrderUnitParams6 = TestDBUtility.getIfoOrderUnitRow();
        l_IfoOrderUnitParams6.setOrderUnitId(1006);
        l_IfoOrderUnitParams6.setAccountId(101001010060L);
        l_IfoOrderUnitParams6.setBizDate("20990910");
        l_IfoOrderUnitParams6.setExpirationDate(WEB3DateUtility.getDate("20990706","yyyyMMdd"));
        l_IfoOrderUnitParams6.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_IfoOrderUnitParams6.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_IfoOrderUnitParams6.setErrorReasonCode("0006");
        l_IfoOrderUnitParams6.setFirstOrderUnitId(1006);
        
        IfoOrderUnitParams l_IfoOrderUnitParams7 = TestDBUtility.getIfoOrderUnitRow();
        l_IfoOrderUnitParams7.setOrderUnitId(1007);
        l_IfoOrderUnitParams7.setAccountId(101001010070L);
        l_IfoOrderUnitParams7.setBizDate("20990910");
        l_IfoOrderUnitParams7.setOrderStatus(OrderStatusEnum.ORDERING);
        l_IfoOrderUnitParams7.setFirstOrderUnitId(1007);
        try
        {
            
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams2);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams3);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams4);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams5);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams6);
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams7);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    private void setExpectedDate(Date l_expectDate)
    {
        final String STR_METHOD_NAME = "setExpectedDate()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_expectDate.getTime()), "1");
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0005");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setSessionType("1");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE, new Timestamp(l_expectDate.getTime()));   
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
}
@
