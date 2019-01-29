head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformPTSAccOpenStateChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformPTSAccOpenStateChangeServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformPTSAccOpenStateChangeServiceImplTest.class);
    WEB3AdminInformPTSAccOpenStateChangeServiceImpl l_impl =
        new WEB3AdminInformPTSAccOpenStateChangeServiceImpl();

    private static int errorDiv;

    public WEB3AdminInformPTSAccOpenStateChangeServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        //取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集
        l_context.setInstitutionCode("0D");
        //取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集
        l_context.setBranchCode("624");
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //取引カレンダコンテキスト.受付時間区分 = ”00：その他”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
        //取引カレンダコンテキスト.商品コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        //取引カレンダコンテキスト.注文受付商品 = ”22：顧客サービス”
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
        //取引カレンダコンテキスト.注文受付トランザクション = ”00：DEFAULT”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);

        //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて
        //取引時間コンテキストをセットする。
        //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        //TradingTimeParams
        TradingTimeParams l_tradingTimeParams =
            TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("624");
        l_tradingTimeParams.setMarketCode("0");
        l_tradingTimeParams.setTradingTimeType("00");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        TestDBUtility.insertWithDel(l_tradingTimeParams);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testExecute()
    {

    }

    public void testGetSearchScreen_T01()
    {
        final String STR_METHOD_NAME = "testGetSearchScreen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("112");
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            WEB3AdminInformPTSAccOpenStateChangeSrcRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeSrcRequest();
            l_impl.getSearchScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetSearchScreen_T02()
    {
        final String STR_METHOD_NAME = "testGetSearchScreen_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("A0501");
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            WEB3AdminInformPTSAccOpenStateChangeSrcRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeSrcRequest();
            l_impl.getSearchScreen(l_request);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreen_T01()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeInpRequest();
            l_request.branchCode = null;
            l_impl.getInputScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());   
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetInputScreen_T02()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeInpRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";

            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            l_impl.getInputScreen(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());   
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreen_T03()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeInpRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("112");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            l_impl.getInputScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());   
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreen_T04()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeInpRequest();
            l_request.branchCode = "111";
            l_request.accountCode = "123456";

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("A0501");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            l_impl.getInputScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());   
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreen_T05()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeInpRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("A0501");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("jiddk");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3AdminInformPTSAccOpenStateChangeInpResponse l_response =
                l_impl.getInputScreen(l_request);
            assertEquals("jiddk", l_response.accountName);
            assertEquals("1", l_response.beforePtsAccOpenDiv);
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());   
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChange_T01()
    {
        final String STR_METHOD_NAME = "testValidateChange_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCnfRequest();
            l_request.branchCode = null;
            l_impl.validateChange(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());   
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChange_T02()
    {
        final String STR_METHOD_NAME = "testValidateChange_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCnfRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "0";
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            l_impl.validateChange(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChange_T03()
    {
        final String STR_METHOD_NAME = "testValidateChange_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCnfRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "0";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("112");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            l_impl.validateChange(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChange_T04()
    {
        final String STR_METHOD_NAME = "testValidateChange_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCnfRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "0";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("A0501");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            l_impl.validateChange(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());   
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChange_T05()
    {
        final String STR_METHOD_NAME = "testValidateChange_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCnfRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "1";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("A0501");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("jiddk");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_impl.validateChange(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03046, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidateChange_T06()
    {
        final String STR_METHOD_NAME = "testValidateChange_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCnfRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "0";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("A0501");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("jiddk");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_impl.validateChange(l_request);
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03046, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChange_T01()
    {
        final String STR_METHOD_NAME = "testSubmitChange_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCmpRequest();
            l_request.branchCode = null;
            l_impl.submitChange(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());   
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChange_T02()
    {
        final String STR_METHOD_NAME = "testSubmitChange_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCmpRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "0";
            l_request.informType = "123";
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            l_impl.submitChange(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChange_T03()
    {
        final String STR_METHOD_NAME = "testSubmitChange_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCmpRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "0";
            l_request.informType = "123";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("112");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            l_impl.submitChange(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChange_T04()
    {
        final String STR_METHOD_NAME = "testSubmitChange_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCmpRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "0";
            l_request.informType = "123";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("A0501");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            l_impl.submitChange(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());   
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChange_T05()
    {
        final String STR_METHOD_NAME = "testSubmitChange_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCmpRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "0";
            l_request.informType = "123";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("A0501");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("jiddk");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //OpLoginSecurityService
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(l_administratorParams.getLoginId()));
            
            //OpLoginAdminServiceImpl
            HashMap l_resultMap = new HashMap();
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH, "5");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH, "7");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE, "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_resultMap);
            
            l_impl.submitChange(l_request);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());   
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testSubmitChange_T06()
    {
        final String STR_METHOD_NAME = "testSubmitChange_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCmpRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "0";
            l_request.informType = "123";
            l_request.password = "123";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setTradingPassword("123");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("A0501");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("jiddk");
            l_mainAccountParams.setPtsAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //OpLoginSecurityService
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(l_administratorParams.getLoginId()));
            
            //OpLoginAdminServiceImpl
            HashMap l_resultMap = new HashMap();
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH, "5");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH, "7");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_resultMap);
            
            l_impl.submitChange(l_request);
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03046, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChange_T07()
    {
        final String STR_METHOD_NAME = "testSubmitChange_T07()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCmpRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "0";
            l_request.informType = "1";
            l_request.password = "123";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setTradingPassword("123");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("A0501");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("jiddk");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //OpLoginSecurityService
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(l_administratorParams.getLoginId()));
            
            //OpLoginAdminServiceImpl
            HashMap l_resultMap = new HashMap();
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH, "5");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH, "7");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_resultMap);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
                "getNewOrderRequestCode", new Class[]
                { String.class, String.class },
                "22");

            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInform =
                TestDBUtility.getVariousInformRow();
            l_variousInform.setInstitutionCode("0D");
            l_variousInform.setBranchCode("624");
            l_variousInform.setAccountCode("123456");
            l_variousInform.setInformDiv("1");
            l_variousInform.setExtDiv1("1");
            TestDBUtility.insertWithDel(l_variousInform);

            l_impl.submitChange(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisVariousInformRows =
                l_queryProcessor.doFindAllQuery(VariousInformParams.TYPE);
            assertEquals(2, l_lisVariousInformRows.size());
            assertEquals("1", ((VariousInformRow)l_lisVariousInformRows.get(0)).getInformDiv());
            assertEquals("1", ((VariousInformRow)l_lisVariousInformRows.get(1)).getInformDiv());

            List l_lisMainAccountRows =
                l_queryProcessor.doFindAllQuery(MainAccountParams.TYPE);
            assertEquals(1, l_lisMainAccountRows.size());
            assertEquals(l_request.afterPtsAccOpenDiv,
                ((MainAccountRow)l_lisMainAccountRows.get(0)).getPtsAccOpenDiv());
            assertEquals(l_administrator.getAdministratorCode(),
                ((MainAccountRow)l_lisMainAccountRows.get(0)).getPtsAccOpenDivLastUpdater());
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03046, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChange_T08()
    {
        final String STR_METHOD_NAME = "testSubmitChange_T08()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCmpRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            l_request.afterPtsAccOpenDiv = "0";
            l_request.informType = "1";
            l_request.password = "123";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setTradingPassword("123");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("A0501");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("jiddk");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //OpLoginSecurityService
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(l_administratorParams.getLoginId()));
            
            //OpLoginAdminServiceImpl
            HashMap l_resultMap = new HashMap();
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH, "5");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH, "7");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_resultMap);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
                "getNewOrderRequestCode", new Class[]
                { String.class, String.class },
                "22");

            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInform =
                TestDBUtility.getVariousInformRow();
            l_variousInform.setInstitutionCode("0D");
            l_variousInform.setBranchCode("624");
            l_variousInform.setAccountCode("123456");
            l_variousInform.setInformDiv("0");
            l_variousInform.setExtDiv1("1");
            TestDBUtility.insertWithDel(l_variousInform);

            l_impl.submitChange(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisVariousInformRows =
                l_queryProcessor.doFindAllQuery(VariousInformParams.TYPE);
            assertEquals(2, l_lisVariousInformRows.size());
            assertEquals("0", ((VariousInformRow)l_lisVariousInformRows.get(0)).getInformDiv());
            assertEquals("1", ((VariousInformRow)l_lisVariousInformRows.get(1)).getInformDiv());

            List l_lisMainAccountRows =
                l_queryProcessor.doFindAllQuery(MainAccountParams.TYPE);
            assertEquals(1, l_lisMainAccountRows.size());
            assertEquals(l_request.afterPtsAccOpenDiv,
                ((MainAccountRow)l_lisMainAccountRows.get(0)).getPtsAccOpenDiv());
            assertEquals(l_administrator.getAdministratorCode(),
                ((MainAccountRow)l_lisMainAccountRows.get(0)).getPtsAccOpenDivLastUpdater());
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03046, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateApplyDivIsChange_T01()
    {
        final String STR_METHOD_NAME = "testValidateApplyDivIsChange_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Method method = l_impl.getClass().getDeclaredMethod(
                "validateApplyDivIsChange",
                new Class[]{WEB3GentradeMainAccount.class, String.class});
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount =
                new WEB3GentradeMainAccount(l_mainAccountParams);
            Object[] obj = new Object[]{l_mainAccount, "1"};
            method.setAccessible(true); 
            method.invoke(l_impl, obj);
        }
        catch(InvocationTargetException l_exc)
        {
            WEB3BusinessLayerException l_ex =
                (WEB3BusinessLayerException)l_exc.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03046, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateApplyDivIsChange_T02()
    {
        final String STR_METHOD_NAME = "testValidateApplyDivIsChange_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Method method = l_impl.getClass().getDeclaredMethod(
                "validateApplyDivIsChange",
                new Class[]{WEB3GentradeMainAccount.class, String.class});
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount =
                new WEB3GentradeMainAccount(l_mainAccountParams);
            Object[] obj = new Object[]{l_mainAccount, "0"};
            method.setAccessible(true); 
            method.invoke(l_impl, obj);
        }
        catch(InvocationTargetException l_exc)
        {
            WEB3BusinessLayerException l_ex =
                (WEB3BusinessLayerException)l_exc.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03046, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateApplyDivIsChange_T03()
    {
        final String STR_METHOD_NAME = "testValidateApplyDivIsChange_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Method method = l_impl.getClass().getDeclaredMethod(
                "validateApplyDivIsChange",
                new Class[]{WEB3GentradeMainAccount.class, String.class});
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount =
                new WEB3GentradeMainAccount(l_mainAccountParams);
            Object[] obj = new Object[]{l_mainAccount, "0"};
            method.setAccessible(true); 
            method.invoke(l_impl, obj);
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03046, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcut_T01()
    {
        final String STR_METHOD_NAME = "testExcut_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock l_implMock =
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock();
            l_implMock.execute(null);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcut_T02()
    {
        final String STR_METHOD_NAME = "testExcut_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            errorDiv= 1;
            WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock l_implMock =
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock();
            l_implMock.execute(new WEB3AdminInformPTSAccOpenStateChangeSrcRequest());
            fail();
            errorDiv = 0;
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcut_T03()
    {
        final String STR_METHOD_NAME = "testExcut_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            errorDiv= 2;
            WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock l_implMock =
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock();
            l_implMock.execute(new WEB3AdminInformPTSAccOpenStateChangeInpRequest());
            fail();
            errorDiv = 0;
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcut_T04()
    {
        final String STR_METHOD_NAME = "testExcut_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            errorDiv= 3;
            WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock l_implMock =
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock();
            l_implMock.execute(new WEB3AdminInformPTSAccOpenStateChangeCnfRequest());
            fail();
            errorDiv = 0;
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcut_T05()
    {
        final String STR_METHOD_NAME = "testExcut_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            errorDiv= 4;
            WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock l_implMock =
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock();
            l_implMock.execute(new WEB3AdminInformPTSAccOpenStateChangeCmpRequest());
            fail();
            errorDiv = 0;
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcut_T06()
    {
        final String STR_METHOD_NAME = "testExcut_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            errorDiv= 0;
            WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock l_implMock =
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock();
            l_implMock.execute(new WEB3AdminInformAccSwElecDeliApplyCmpRequest());
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3AdminInformPTSAccOpenStateChangeServiceImplForMock extends
    WEB3AdminInformPTSAccOpenStateChangeServiceImpl
    {
        protected WEB3AdminInformPTSAccOpenStateChangeSrcResponse getSearchScreen(
            WEB3AdminInformPTSAccOpenStateChangeSrcRequest l_request) throws WEB3BaseException
        {
            if (errorDiv == 1)
            {
                log.debug("パラメータタイプ不正。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName(),
                    "パラメータタイプ不正。");
            }
            else
            {
                return new WEB3AdminInformPTSAccOpenStateChangeSrcResponse();
            }
        }
        
        protected WEB3AdminInformPTSAccOpenStateChangeInpResponse getInputScreen(
            WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request) throws WEB3BaseException
        {
            if (errorDiv == 2)
            {
                log.debug("パラメータタイプ不正。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName(),
                    "パラメータタイプ不正。");
            }
            else
            {
                return new WEB3AdminInformPTSAccOpenStateChangeInpResponse();
            }
        }
        
        protected WEB3AdminInformPTSAccOpenStateChangeCnfResponse validateChange(
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request) throws WEB3BaseException
        {
            if (errorDiv == 3)
            {
                log.debug("パラメータタイプ不正。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName(),
                    "パラメータタイプ不正。");
            }
            else
            {
                return new WEB3AdminInformPTSAccOpenStateChangeCnfResponse();
            }
        }

        protected WEB3AdminInformPTSAccOpenStateChangeCmpResponse submitChange(
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request) throws WEB3BaseException
        {
            if (errorDiv == 4)
            {
                log.debug("パラメータタイプ不正。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName(),
                    "パラメータタイプ不正。");
            }
            else
            {
                return new WEB3AdminInformPTSAccOpenStateChangeCmpResponse();
            }
        }
    }
}


@
