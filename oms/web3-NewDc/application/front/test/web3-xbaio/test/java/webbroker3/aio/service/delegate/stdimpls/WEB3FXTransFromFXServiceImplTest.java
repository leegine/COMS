head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransFromFXServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXから振替サービスImplTest(FXから振替サービスImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 趙林鵬(中訊) 新規作成 
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.gftforex.soap.api.ResultInfoBase;
import com.gftforex.soap.api.SendSyncRequestResponse;

import test.util.TestDBUtility;

import webbroker3.aio.WEB3FXDataControlServiceImpl;
import webbroker3.aio.WEB3FXTelegramProcessService;
import webbroker3.aio.WEB3FXTelegramProcessServiceImpl;
import webbroker3.aio.WEB3GFTConnectionSystemTest;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountCodeRow;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.FxTransferMasterRow;
import webbroker3.aio.data.GftMessageRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTransFromFXAskingRequest;
import webbroker3.aio.message.WEB3FXTransFromFXAskingResponse;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteResponse;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXTransFromFXConfirmRequest;
import webbroker3.aio.message.WEB3FXTransFromFXConfirmResponse;
import webbroker3.aio.message.WEB3FXTransferAbleAmtUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ConnectDivDef;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AccOpenDivParams;
import webbroker3.gentrade.data.AccOpenDivRow;
import webbroker3.gentrade.data.OtherOrgOutOfSrvDateRow;
import webbroker3.gentrade.data.OtherOrgOutOfSrvWeekRow;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXTransFromFXServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FXTransFromFXServiceImplTest.class);
    
    public WEB3FXTransFromFXServiceImplTest(String arg0)
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
    
    public void testSubmitOrderC0001()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("1");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class });
           assertEquals("381", ((WEB3FXGftResultNoticeTelegramUnit)l_paramsValue1.getFirstCalled()[0]).branchCode);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderC0002()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("1");
            l_compFxConditionParams.setSoapConnectDiv("2");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = null;
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class });
           assertEquals("381", ((WEB3FXGftResultNoticeTelegramUnit)l_paramsValue1.getFirstCalled()[0]).branchCode);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderC0003()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = null;
            l_unit.cashinoutAmt2 = "100";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "03";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class });
           assertEquals("381", ((WEB3FXGftResultNoticeTelegramUnit)l_paramsValue1.getFirstCalled()[0]).branchCode);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //======
    public void testSubmitOrderC0004()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderC0005()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setFxSystemCode("1");
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitOrderC0006()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setFxSystemCode("1");
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(123);
            l_fxTransferMasterParams.setTransferDiv("0");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
    public void testSubmitOrderC0007()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0007";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();

            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderC0008()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0008";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_2);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();

            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderC0009()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0009";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_3);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();

            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderC0010()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0010";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_4);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();

            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderC0011()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0011";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_5);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();

            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderC0012()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0012";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_6);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();

            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderC0013()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0013";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_7);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();

            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderC0014()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0014";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_8);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();

            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderC0015()
    {
        final String STR_METHOD_NAME = " testSubmitOrderC0015";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", 
                new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "zty");
            
            
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
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
             new Class[] {SubAccount.class,
                ProductTypeEnum.class,
                OrderTypeEnum.class,
                NewOrderSpec.class,
                AioOrderManagerPersistenceEventInterceptor.class, 
                long.class, 
                String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_8);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("0");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();

            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAllAndCommit(GftMessageRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "1980003";
            l_unit.cashinoutAmt = "100";
            l_unit.cashinoutAmt2 = "200.456";
            l_unit.dirSendTime = "20070717130000";
            l_unit.gftOperationDiv = "02";
            l_unit.fxFirstLoginId = "100161000";
            l_unit.groupName = "00001";
            l_unit.wolfSession = "161000011111";
            l_unit.wolfAid = "161000022222";
            l_unit.regetServiceId = "161000033333";
            l_unit.wolfSsid = "000";
            l_unit.accountCode = "2512246";
            l_unit.resultCode = "00000000";
            l_unit.gftSendTime = "20070717140000";
            l_unit.hashValue = "658416f10fd0ade8d8a22efc3e299d65";
            l_unit.fxAccountCode = "100401";

            WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
            l_request.orderId = "1111";
            l_request.checkDate = WEB3DateUtility.getDate("20070717", "yyyyMMdd");
            l_request.fxSystemCode = "01";
            l_request.password = "123456";
            l_request.fxGftResultNoticeTelegramUnit = l_unit;
            
            WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
            
            l_impl.submitOrder(l_request);

          WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
              "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
              "reCalcTradingPower",
              new Class[] {WEB3GentradeSubAccount.class});
          
          assertEquals(101001010010L, ((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getAccountId());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrderSoapC0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrderSoapC0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxAccountCode = "100401";
            l_unit.fxCourseDiv = "0";

            WEB3FXTransFromFXCompleteSoapRequest l_request =
                new WEB3FXTransFromFXCompleteSoapRequest();
            l_request.wolfSession = "1";
            l_request.wolfAid = "1";
            l_request.regetServiceId = "1";
            l_request.wolfSsid = "1";
            l_request.fxAccInformationUnit = l_unit;
            l_request.transferAmount = "1";
            l_request.fxSystemCode = "01";
            WEB3FXTransFromFXServiceImpl l_fxTransFromFXServiceImpl =
                new WEB3FXTransFromFXServiceImpl();
    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "validateChangePoss",
                    new Class[]{ SubAccount.class, CompFxConditionParams.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "validateTransferPossibleCount",
                    new Class[]{SubAccount.class, Date.class, OrderCategEnum.class},
                    null);
            
            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123));
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAllAndCommit(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setFxSystemDiv("2");//<<<<<<<<<<<<<<<<<<<<<<
            
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setExtConnectSystemCode("05");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_compFxConditionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            TestDBUtility.deleteAllAndCommit(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_FxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_FxTransferMasterParams.setFxSystemId(6241001);
            l_FxTransferMasterParams.setTransferDiv("0");
            TestDBUtility.insertWithDelAndCommit(l_FxTransferMasterParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //口座ＩＤ]
            l_subAccountParams.setAccountId(123456);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(33381251220301L);
            //補助口座タイプ
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(33);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            TestDBUtility.deleteAllAndCommit(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDelAndCommit(l_fxAccountParams);
            l_fxTransFromFXServiceImpl.submitOrderSoap(l_request);
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03184, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitOrderSoapC0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrderSoapC0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImplForTest();
        WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
        l_unit.fxAccountCode = "100401";
        l_unit.fxCourseDiv = "0";

        WEB3FXTransFromFXCompleteSoapRequest l_request =
            new WEB3FXTransFromFXCompleteSoapRequest();
        l_request.wolfSession = "1";
        l_request.wolfAid = "1";
        l_request.regetServiceId = "1";
        l_request.wolfSsid = "1";
        l_request.fxAccInformationUnit = l_unit;
        l_request.transferAmount = "1";
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512203L));         

            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
    
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(222);
            l_soapConnectPrefRpcParams.setConnectDiv(WEB3ConnectDivDef.GFT);
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("operationname");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");
            
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //口座ＩＤ]
            l_subAccountParams.setAccountId(333812512203L);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(33381251220301L);
            //補助口座タイプ
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_request.password = "0001";
//            WEB3FXTransFromFXCompleteSoapResponse l_response = 
//                l_impl.submitOrderSoap(l_request);
            
//            assertEquals(l_response.lastUpdatedTimestamp,new Date(2008,10,10,10,10));
//            assertEquals(l_response.orderActionId,"001");
//            assertEquals(l_response.deliveryDate,new Date(2008,10,10,10,10));
            
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
        
        
    }
    public void testSubmitOrderSoapC0003()
    {
        final String STR_METHOD_NAME = "testSubmitOrderSoapC0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxAccountCode = "100401";
            l_unit.fxCourseDiv = "0";

            WEB3FXTransFromFXCompleteSoapRequest l_request =
                new WEB3FXTransFromFXCompleteSoapRequest();
            l_request.wolfSession = "1";
            l_request.wolfAid = "1";
            l_request.regetServiceId = "1";
            l_request.wolfSsid = "1";
            l_request.fxAccInformationUnit = l_unit;
            l_request.transferAmount = "1";
            WEB3FXTransFromFXServiceImpl l_fxTransFromFXServiceImpl =
                new WEB3FXTransFromFXServiceImpl();
            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class},
                    "1232");
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            TestDBUtility.deleteAllAndCommit(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_FxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_FxTransferMasterParams.setFxSystemId(6241001);
            l_FxTransferMasterParams.setTransferDiv("0");
            TestDBUtility.insertWithDelAndCommit(l_FxTransferMasterParams);
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setIfoAccOpenDivOsaka(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setTransferCount(10);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setAccountId(123456);
            l_SubAccountParams.setBranchId(1001);
            l_SubAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDelAndCommit(l_SubAccountParams);
            
            TestDBUtility.deleteAllAndCommit(CompFxConditionRow.TYPE);
            CompFxConditionParams l_CompFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_CompFxConditionParams.setInstitutionCode("0D");
            l_CompFxConditionParams.setBranchCode("381");
            l_CompFxConditionParams.setGetTransferableAmtDiv("1");
            l_CompFxConditionParams.setExtConnectSystemCode("01");
            TestDBUtility.insertWithDelAndCommit(l_CompFxConditionParams);

            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);

            TestDBUtility.deleteAllAndCommit(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("02");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDelAndCommit(l_fxAccountParams);
            l_fxTransFromFXServiceImpl.submitOrderSoap(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03075,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //===============
    public void testSubmitOrderSoapC0004()
    {
        final String STR_METHOD_NAME = "testSubmitOrderSoapC0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImplForTest();
        WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
        l_unit.fxAccountCode = "100401";
        l_unit.fxCourseDiv = "0";

        WEB3FXTransFromFXCompleteSoapRequest l_request =
            new WEB3FXTransFromFXCompleteSoapRequest();
        l_request.wolfSession = "1";
        l_request.wolfAid = "1";
        l_request.regetServiceId = "1";
        l_request.wolfSsid = "1";
        l_request.fxAccInformationUnit = l_unit;
        l_request.transferAmount = "1";
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));         

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setTransferCount(3);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAllAndCommit(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(l_mainAccountParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(WEB3ConnectDivDef.GFT);
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("operationname");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");

            TestDBUtility.insertWithDelAndCommit(l_soapConnectPrefRpcParams);

            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAllAndCommit(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_compFxConditionParams);
  
            
//            Services.unregisterService(WEB3FXDataControlService.class);
            Services.unregisterService(WEB3FXTelegramProcessService.class);
            
            
//            Services.registerService(
//                    WEB3FXDataControlService.class,
//                    new WEB3FXDataControlServiceImplForTest());
            
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.aio.WEB3GFTConnectionSystem", 
//                    "validateConnect",
//                    new Class[] {SoapConnectPrefRpcParams.class},
//                    null);
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
//                    "getHashValue",
//                    new Class[] {String.class, String.class, String.class},
//                    "111");
//            
            SendSyncRequestResponse l_requestResponse = new SendSyncRequestResponse();
            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
            resin.setMajorStatusCode(1);
            resin.setMinorStatusCode(2);
            l_requestResponse.setSendSyncRequestResult(resin);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "sendSoapMessage",
                    new Class[] {
                            WEB3FXGftAskingTelegramUnit.class,
                            SoapConnectPrefRpcParams.class},
                    l_requestResponse);
 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "updateGFTTransferStatus", 
                    new Class[]{ String.class, String.class, String.class, String.class},
                    null);
            
            WEB3FXGftResultNoticeTelegramUnit l_unit1 = new WEB3FXGftResultNoticeTelegramUnit();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXTransConnectionImpl",
                    "doTransfer", 
                    new Class[]{ CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class},
                    l_unit1);

            l_request.password = "0001";
            
            WEB3FXTransFromFXCompleteSoapResponse l_response = 
            l_impl.submitOrderSoap(l_request);
            
            assertEquals(l_response.lastUpdatedTimestamp,new Date(2008,10,10,10,10));
            assertEquals(l_response.orderActionId,"001");
            assertEquals(l_response.deliveryDate,new Date(2008,10,10,10,10));

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    public void testSubmitOrderSoapC0005()
    {
        final String STR_METHOD_NAME = "testSubmitOrderSoapC0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImplForTest();
        WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
        l_unit.fxAccountCode = "100401";
        l_unit.fxCourseDiv = "0";

        WEB3FXTransFromFXCompleteSoapRequest l_request =
            new WEB3FXTransFromFXCompleteSoapRequest();
        l_request.wolfSession = "1";
        l_request.wolfAid = "1";
        l_request.regetServiceId = "1";
        l_request.wolfSsid = "1";
        l_request.fxAccInformationUnit = l_unit;
        l_request.transferAmount = "1";

        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));         

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
    
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setTransferCount(3);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAllAndCommit(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(l_mainAccountParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(WEB3ConnectDivDef.GFT);
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("operationname");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");

            TestDBUtility.insertWithDelAndCommit(l_soapConnectPrefRpcParams);

            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAllAndCommit(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_compFxConditionParams);
  
           
            Services.unregisterService(WEB3FXTelegramProcessService.class);
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.aio.WEB3GFTConnectionSystem", 
//                    "validateConnect",
//                    new Class[] {SoapConnectPrefRpcParams.class},
//                    null);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
//                    "getHashValue",
//                    new Class[] {String.class, String.class, String.class},
//                    "111");
            
            SendSyncRequestResponse l_requestResponse = new SendSyncRequestResponse();
            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
            resin.setMajorStatusCode(1);
            resin.setMinorStatusCode(2);
            l_requestResponse.setSendSyncRequestResult(resin);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "sendSoapMessage",
                    new Class[] {
                            WEB3FXGftAskingTelegramUnit.class,
                            SoapConnectPrefRpcParams.class},
                    l_requestResponse);
 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "updateGFTTransferStatus", 
                    new Class[]{ String.class, String.class, String.class, String.class},
                    null);
            WEB3FXGftResultNoticeTelegramUnit l_unit1 = new WEB3FXGftResultNoticeTelegramUnit();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXTransConnectionImpl",
                    "doTransfer", 
                    new Class[]{ CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class},
                    l_unit1);
            l_request.password = "0001";
            
            WEB3FXTransFromFXCompleteSoapResponse l_response = 
            l_impl.submitOrderSoap(l_request);
            
            assertEquals(l_response.lastUpdatedTimestamp,new Date(2008,10,10,10,10));
            assertEquals(l_response.orderActionId,"001");
            assertEquals(l_response.deliveryDate,new Date(2008,10,10,10,10));

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    
    public class WEB3FXTransFromFXServiceImplForTest extends WEB3FXTransFromFXServiceImpl{
        
        protected WEB3FXTransFromFXAskingResponse startOrder(
                WEB3FXTransFromFXAskingRequest l_request) throws WEB3BaseException
            {
                final String STR_METHOD_NAME = 
                    "startOrder(WEB3FXTransFromFXAskingRequest l_request)";
                log.entering(STR_METHOD_NAME);
                
   
                //1.12) GFT依頼電文明細( )
                //アイテムの定義
                //GFT依頼電文明細オブジェクトを生成する。
                WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
                
                //1.13) (*)プロパティセット
                //GFT依頼電文明細に必要なプロパティをセットする（下記以外のプロパティは設定しない）
                
                //DIR→GFT送信日時   ：現在時刻（システムタイムスタンプ）
                l_fXGftAskingTelegramUnit.dirSendTime = 
                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHHmmss");
                
                //処理区分      ：01(口座開設）
                l_fXGftAskingTelegramUnit.gftOperationDiv = WEB3GftMessageOperationDef.ACCOUNT_OPEN;
                    
                
                //初期ログインID  ：get新規FXログインID()の戻り値
                l_fXGftAskingTelegramUnit.fxFirstLoginId = "bbb";
                
                
                //担当区分      ：会社別FXシステム条件Params.担当区分
                l_fXGftAskingTelegramUnit.groupName = "lll";
                
                //会社コード     ：補助口座.証券会社コード
                l_fXGftAskingTelegramUnit.institutionCode = "0D";
                
                //WOLFセッションキー   ：リクエストデータ.WOLFセッションキー
                l_fXGftAskingTelegramUnit.wolfSession = l_request.wolfSession;
                
                //アプリケーションID    ：リクエストデータ.アプリケーションID
                l_fXGftAskingTelegramUnit.wolfAid = l_request.wolfAid;
                
                //再生成サービスID ：リクエストデータ.再生成サービスID
                l_fXGftAskingTelegramUnit.regetServiceId = l_request.regetServiceId;
                
                //SSID      ：リクエストデータ.SSID
                l_fXGftAskingTelegramUnit.wolfSsid = l_request.wolfSsid;
                
                //部店コード     ：補助口座.get取引店().getBranchCode()
                l_fXGftAskingTelegramUnit.branchCode = "381";
                
                //顧客コード     ：補助口座.getMainAccount().getAccountCode()
                l_fXGftAskingTelegramUnit.accountCode = "123";
                
                //識別コード     ：get新規識別コード()の戻り値
                l_fXGftAskingTelegramUnit.requestNumber = "456";
                
                
                l_fXGftAskingTelegramUnit.fxLastName = "fengfht";
                
                //1.14) createGFT電文ハッシュ値(GFT依頼電文明細)
                //アイテムの定義
                //GFT電文のハッシュ値を取得する。 
                //[引数の設定] 
                //GFT依頼電文明細：　@プロパティセットを行ったGFT依頼電文明細
                WEB3FXTelegramProcessService l_fXTelegramProcessService = 
                    (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);
                
                String l_strGFTTelegramHashValue = "ffff";
                    ///l_fXTelegramProcessService.createGFTTelegramHashValue(l_fXGftAskingTelegramUnit);
                
                //createGFT電文ハッシュ値()の戻り値のハッシュ値を
                //GFT依頼電文明細.ハッシュ値にセットする。
                l_fXGftAskingTelegramUnit.hashValue = l_strGFTTelegramHashValue;

                WEB3FXTransFromFXAskingResponse l_response = new WEB3FXTransFromFXAskingResponse();
                    

                l_response.fxUrl = "asdasd";
                l_response.fxGftAskingTelegramUnit = l_fXGftAskingTelegramUnit;
                
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

        protected WEB3FXTransFromFXCompleteResponse submitOrder(
                WEB3FXTransFromFXCompleteRequest l_request) throws WEB3BaseException
        {

            final String STR_METHOD_NAME = "submitOrder(WEB3FXTransFromFXCompleteRequest l_request)";
            log.entering(STR_METHOD_NAME);

            WEB3FXTransFromFXCompleteResponse l_fxTransFromFXCompleteResponse =
                new WEB3FXTransFromFXCompleteResponse();
            assertEquals(l_request.password,"0001");
            l_fxTransFromFXCompleteResponse.lastUpdatedTimestamp = new Date(2008,10,10,10,10);
            l_fxTransFromFXCompleteResponse.orderActionId = "001";
            l_fxTransFromFXCompleteResponse.deliveryDate = new Date(2008,10,10,10,10);

            log.exiting(STR_METHOD_NAME);
            return l_fxTransFromFXCompleteResponse;
        }

    }

    public class WEB3FXDataControlServiceImplForTest extends WEB3FXDataControlServiceImpl{
        
        public void updateGFTTransferStatus(
                String l_strInstitutionCode,
                String l_strBranchCode,
                String l_strOrderRequestNumber,
                String l_strResultCode)
                    throws WEB3BaseException{
            
        }
    }
    
    
    public class WEB3FXTelegramProcessServiceImplForTest extends WEB3FXTelegramProcessServiceImpl
    {
        
        
            public String createGFTTelegramHashValue(WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)
            throws WEB3BaseException
        {
    
            return "aaa";
            
        }
        
    }
    
    public class forTestWEB3FXTransFromFXServiceImpl extends WEB3FXTransFromFXServiceImpl
    {
        public SubAccount getSubAccount(SubAccountTypeEnum l_subAccountType)
        throws WEB3SystemLayerException
        {
            SubAccount l_subAccount = null;
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                MainAccountParams l_mainAccountParams =
                    TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setAccountId(333812512203L);
                l_mainAccountParams.setInstitutionId(33381251220301L);
                l_mainAccountParams.setIfoAccOpenDivTokyo("9");
                l_mainAccountParams.setTransferCount(5);
                TestDBUtility.insertWithDel(l_mainAccountParams);
                
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setAccountId(333812512203L);
                l_subAccountParams.setInstitutionId(33381251220301L);
                TestDBUtility.insertWithDel(l_subAccountParams);
                
                 l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);

            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                
                fail();
            }
            return l_subAccount;
        }
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
            Trader l_trader = null;
            try
            {
              TestDBUtility.deleteAll(TraderRow.TYPE);
              TraderParams l_traderParams = TestDBUtility.getTraderRow();
              TestDBUtility.insertWithDel(l_traderParams);
              l_trader = new WEB3GentradeTrader(3338111123L, true);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                
                fail();
            }
            return l_trader;
        }
    }

    public void testStartOrder_C001()
    {
        final String STR_METHOD_NAME = "testStartOrder_C001()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            forTestWEB3FXTransFromFXServiceImpl l_mpl2 = new forTestWEB3FXTransFromFXServiceImpl();
            WEB3FXTransFromFXAskingRequest l_request = new WEB3FXTransFromFXAskingRequest();
            l_request.wolfSession = "ab";
            l_request.wolfAid = "abc";
            l_request.regetServiceId = "100";
            l_request.wolfSsid = "101";
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv = "0";
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "12345";
            l_request.fxSystemCode = "10";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", 
                    "validateOrder",
                    new Class[] {SubAccount.class},
                    null);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33381251220301L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("10");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(OtherOrgOutOfSrvWeekRow.TYPE);
            TestDBUtility.deleteAll(OtherOrgOutOfSrvDateRow.TYPE);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20080504));
            
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                    new Class[] { Trader.class, SubAccount.class, String.class },
                    l_result);
            
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("10");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    "111");
            
//            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            Services.unregisterService(WEB3FXTelegramProcessService.class);
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());
            
            long l_lngProductId = 10;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "createNewOrderId",
                new Class[]{},
                new Long(l_lngProductId));
            
            
           // TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            
            WEB3FXTransFromFXAskingResponse l_response = l_mpl2.startOrder(l_request);
            assertEquals(l_compFxConditionParams.url,l_response.fxUrl);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書 NO:1221
     * testStartOrder_C002()
     */
    public void testStartOrder_C002()
    {
        final String STR_METHOD_NAME = "testStartOrder_C002()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            forTestWEB3FXTransFromFXServiceImpl l_mpl2 = new forTestWEB3FXTransFromFXServiceImpl();
            WEB3FXTransFromFXAskingRequest l_request = new WEB3FXTransFromFXAskingRequest();
            l_request.wolfSession = "ab";
            l_request.wolfAid = "abc";
            l_request.regetServiceId = "100";
            l_request.wolfSsid = "101";
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv = "0";
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "12345";
            l_request.fxSystemCode = "10";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", 
                    "validateOrder",
                    new Class[] {SubAccount.class},
                    null);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33381251220301L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("0");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("10");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(OtherOrgOutOfSrvWeekRow.TYPE);
            TestDBUtility.deleteAll(OtherOrgOutOfSrvDateRow.TYPE);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20080504));
            
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                    new Class[] { Trader.class, SubAccount.class, String.class },
                    l_result);
            
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("10");
            l_fxAccountParams.setAccountCode("2512246");
            l_fxAccountParams.setFxLoginId(123456l);
            TestDBUtility.insertWithDel(l_fxAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    "111");
            
//            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            Services.unregisterService(WEB3FXTelegramProcessService.class);
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());
            
            long l_lngProductId = 10;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "createNewOrderId",
                new Class[]{},
                new Long(l_lngProductId));
            
           // TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            //get変換FXログイン
            //[引数] 
            // 証券会社ID：　@補助口座.getInstitution.getInstitutionId() 
            // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード 
            // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字 
            // FXログインID：　@FX顧客Params.FXログインID
            
            //証券会社ID：333812512246L
            //FXシステムコード："10"
            //FXログインID頭文字："159"
            // FXログインID：123456l

            WEB3FXTransFromFXAskingResponse l_response = l_mpl2.startOrder(l_request);
            assertEquals("123456",l_response.fxGftAskingTelegramUnit.fxFirstLoginId);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidateOrder()
    {
        final String STR_METHOD_NAME = "testValidateOrder";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
        WEB3FXTransFromFXConfirmRequest l_request = new WEB3FXTransFromFXConfirmRequest();
        l_request.transferAmount = "101";
        WEB3FXAccInformationUnit l_fxAccInformationUnit =
            new WEB3FXAccInformationUnit();
        l_fxAccInformationUnit.fxAccountCode = "123456";
        l_fxAccInformationUnit.fxCourseDiv = "1";
        l_request.fxAccInformationUnit = l_fxAccInformationUnit;
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXTransferAbleAmtDisplayServiceImpl",
                    "getFXTransferAbleAmtCheck", new Class[]
                   { SubAccount.class,  CompFxConditionParams.class, String.class, String.class},
                   new WEB3FXTransferAbleAmtUnit());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "getHashValue", new Class[]
                { String.class, String.class, String.class },
                "1");
            
            WEB3FXGftResultNoticeTelegramUnit l_unit1 = new WEB3FXGftResultNoticeTelegramUnit();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXTransConnectionImpl",
                    "doTransfer", 
                    new Class[]{ CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class},
                    l_unit1);
           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "sendSoapMessage", new Class[]
                 {WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class },
                 WEB3GFTConnectionSystemTest.getSendSyncRequestResponse(1, true,0,0,0,0));
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setTransferCount(3);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams = TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(333812512246L);
            l_accOpenDivParams.setAccType("1");
            l_accOpenDivParams.setAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_accOpenDivParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setFxSystemId(123);
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setExtConnectSystemCode(WEB3ExtConnectSystemCodeDef.GFT);
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountParams);
            
            TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("381");
            l_fxAccountCodeParams.setFxSystemCode("01");
            l_fxAccountCodeParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(123);
            l_fxTransferMasterParams.setTransferDiv("0");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(l_branchParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(l_compFxConditionParams.getFxSystemCode());
            l_soapConnectPrefRpcParams.setEndpointName("url");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("0");
            l_soapConnectPrefRpcParams.setOperationName("0");
            l_soapConnectPrefRpcParams.setParameterList("1");
            l_soapConnectPrefRpcParams.setServiceName("1");
            l_soapConnectPrefRpcParams.setPortTypeName("1");
            l_soapConnectPrefRpcParams.setResponseParamType("1");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            Date l_datOrderBizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();

            WEB3FXTransFromFXConfirmResponse l_response =
                l_impl.validateOrder(l_request);

            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(l_datOrderBizDate.getTime()));

            Date l_datDeliveryDate = l_gentradeBizDate.roll(2);
            int l_intCompareResult = WEB3DateUtility.compareToDay(
                l_response.deliveryDate,
                l_datDeliveryDate);
            assertEquals(0, l_intCompareResult);

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    public void testValidateOrder001()
    {
        final String STR_METHOD_NAME = "testValidateOrder001";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransFromFXServiceImpl l_impl = new WEB3FXTransFromFXServiceImpl();
        WEB3FXTransFromFXConfirmRequest l_request = new WEB3FXTransFromFXConfirmRequest();
        l_request.transferAmount = "101";
        WEB3FXAccInformationUnit l_fxAccInformationUnit =
            new WEB3FXAccInformationUnit();
        l_fxAccInformationUnit.fxAccountCode = "123456";
        l_fxAccInformationUnit.fxCourseDiv = "1";
        l_request.fxAccInformationUnit = l_fxAccInformationUnit;
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXTransferAbleAmtDisplayServiceImpl",
                    "getFXTransferAbleAmtCheck", new Class[]
                   { SubAccount.class,  CompFxConditionParams.class, String.class, String.class},
                   new WEB3FXTransferAbleAmtUnit());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "getHashValue", new Class[]
                { String.class, String.class, String.class },
                "1");

           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "sendSoapMessage", new Class[]
                 {WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class, String.class, String.class },
                 WEB3GFTConnectionSystemTest.getSendSyncRequestResponse(1, true,0,0,0,0));
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setTransferCount(3);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams = TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(333812512246L);
            l_accOpenDivParams.setAccType("1");
            l_accOpenDivParams.setAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_accOpenDivParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setFxSystemId(123);
            l_compFxConditionParams.setGetTransferableAmtDiv("0");
            l_compFxConditionParams.setExtConnectSystemCode(WEB3ExtConnectSystemCodeDef.GFT);
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(123);
            l_fxTransferMasterParams.setTransferDiv("0");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(l_branchParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(l_compFxConditionParams.getFxSystemCode());
            l_soapConnectPrefRpcParams.setEndpointName("url");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("0");
            l_soapConnectPrefRpcParams.setOperationName("0");
            l_soapConnectPrefRpcParams.setParameterList("1");
            l_soapConnectPrefRpcParams.setServiceName("1");
            l_soapConnectPrefRpcParams.setPortTypeName("1");
            l_soapConnectPrefRpcParams.setResponseParamType("1");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            Date l_datOrderBizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();

            WEB3FXTransFromFXConfirmResponse l_response =
                l_impl.validateOrder(l_request);

            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(l_datOrderBizDate.getTime()));

            Date l_datDeliveryDate = l_gentradeBizDate.roll(2);
            int l_intCompareResult = WEB3DateUtility.compareToDay(
                l_response.deliveryDate,
                l_datDeliveryDate);
            assertEquals(0, l_intCompareResult);

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
 
}
@
