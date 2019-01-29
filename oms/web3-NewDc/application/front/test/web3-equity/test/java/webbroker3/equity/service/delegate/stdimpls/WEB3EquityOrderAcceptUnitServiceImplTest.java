head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderAcceptUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3EquityOrderAcceptUnitServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/28 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3ReserveOrderExistFlagDef;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.data.HostEqtypeOrderAcceptRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvEqOrderActionRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderAcceptUnitServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility
    .getInstance(WEB3EquityOrderAcceptUnitServiceImplTest.class);
    
    private WEB3EquityOrderAcceptUnitServiceImpl  l_serviceImpl= null;

    public WEB3EquityOrderAcceptUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3EquityOrderAcceptUnitServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //株式注文受付キューParams=null
    public void testNotifyOrderAccept_C0001()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderAcceptParams acceptParams=null;
            l_serviceImpl.notifyOrderAccept(acceptParams);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //受付対象注文はクローズ済
    public void testNotifyOrderAccept_C0002()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAcceptRow.TYPE);
            HostEqtypeOrderAcceptParams acceptParams= TestDBUtility.getHostEqtypeOrderAcceptRow();
            acceptParams.setBranchCode("381");
            acceptParams.setAccountCode("2512246");
            acceptParams.setTraderCode("11123");
            acceptParams.setInstitutionCode("0D");
            acceptParams.setAcceptNumber("1111");
            acceptParams.setStatus("0");
            TestDBUtility.insertWithDel(acceptParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setAccountId(333812512246L);
            eqtypeOrderUnitParams.setOrderRequestNumber("000022001");
            eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            l_serviceImpl.notifyOrderAccept(acceptParams);

            assertEquals(WEB3HostStatusDef.COMPLETE_PROCESS, acceptParams.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //補助口座の取得失敗
    public void testNotifyOrderAccept_C0003()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAcceptRow.TYPE);
            HostEqtypeOrderAcceptParams acceptParams= TestDBUtility.getHostEqtypeOrderAcceptRow();
            acceptParams.setBranchCode("381");
            acceptParams.setAccountCode("2512246");
            acceptParams.setTraderCode("11123");
            acceptParams.setInstitutionCode("0D");
            acceptParams.setAcceptNumber("1111");
            acceptParams.setStatus("0");
            TestDBUtility.insertWithDel(acceptParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setAccountId(333812512246L);
            eqtypeOrderUnitParams.setOrderRequestNumber("000022001");
            eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subaccountParams = TestDBUtility.getSubAccountRow();
            subaccountParams.setAccountId(333812512246L);
            subaccountParams.setSubAccountId(11111);
            TestDBUtility.insertWithDel(subaccountParams);
            
            l_serviceImpl.notifyOrderAccept(acceptParams);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //株式注文受付キューParams.注文受付結果＝"注文受付完了"の場合
    //l_result.isSuccessResult()
    //処理区分更新
    public void testNotifyOrderAccept_C0004()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAcceptRow.TYPE);
            HostEqtypeOrderAcceptParams acceptParams= TestDBUtility.getHostEqtypeOrderAcceptRow();
            acceptParams.setBranchCode("381");
            acceptParams.setAccountCode("2512246");
            acceptParams.setTraderCode("11123");
            acceptParams.setInstitutionCode("0D");
            acceptParams.setAcceptNumber("1111");
            acceptParams.setStatus("0");
            TestDBUtility.insertWithDel(acceptParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setAccountId(333812512246L);
            eqtypeOrderUnitParams.setOrderRequestNumber("000022001");
            eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setAccountId(333812512246L);
            l_eqtypeOrderParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subaccountParams = TestDBUtility.getSubAccountRow();
            subaccountParams.setAccountId(333812512246L);
            subaccountParams.setSubAccountId(33381251220301L);
            subaccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subaccountParams);
            
            l_serviceImpl.notifyOrderAccept(acceptParams);
            
            assertEquals(WEB3HostStatusDef.COMPLETE_PROCESS, acceptParams.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //株式注文受付キューParams.注文受付結果＝"注文受付完了"の場合
    //l_result.isFailedResult()
    public void testNotifyOrderAccept_C0005()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAcceptRow.TYPE);
            HostEqtypeOrderAcceptParams acceptParams= TestDBUtility.getHostEqtypeOrderAcceptRow();
            acceptParams.setBranchCode("381");
            acceptParams.setAccountCode("2512246");
            acceptParams.setTraderCode("11123");
            acceptParams.setInstitutionCode("0D");
            acceptParams.setAcceptNumber("1111");
            acceptParams.setStatus("0");
            TestDBUtility.insertWithDel(acceptParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setAccountId(333812512246L);
            eqtypeOrderUnitParams.setOrderRequestNumber("000022001");
            eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setAccountId(333812512246L);
            l_eqtypeOrderParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subaccountParams = TestDBUtility.getSubAccountRow();
            subaccountParams.setAccountId(333812512246L);
            subaccountParams.setSubAccountId(33381251220301L);
            subaccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subaccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(marketParams);
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00004);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    processingResult);
            
            l_serviceImpl.notifyOrderAccept(acceptParams);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00004, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //株式注文受付キューParams.注文受付結果＝"エラー"の場合
    //l_result.isSuccessResult()
    //処理区分更新
    public void testNotifyOrderAccept_C0006()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAcceptRow.TYPE);
            HostEqtypeOrderAcceptParams acceptParams= TestDBUtility.getHostEqtypeOrderAcceptRow();
            acceptParams.setBranchCode("381");
            acceptParams.setAccountCode("2512246");
            acceptParams.setTraderCode("11123");
            acceptParams.setInstitutionCode("0D");
            acceptParams.setAcceptNumber("1111");
            acceptParams.setStatus("0");
            acceptParams.setAcceptStatus(WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR);
            TestDBUtility.insertWithDel(acceptParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setAccountId(333812512246L);
            eqtypeOrderUnitParams.setOrderRequestNumber("000022001");
            eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setAccountId(333812512246L);
            l_eqtypeOrderParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subaccountParams = TestDBUtility.getSubAccountRow();
            subaccountParams.setAccountId(333812512246L);
            subaccountParams.setSubAccountId(33381251220301L);
            subaccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subaccountParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(marketParams);
            
            l_serviceImpl.notifyOrderAccept(acceptParams);
            
            assertEquals(WEB3HostStatusDef.COMPLETE_PROCESS, acceptParams.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //株式注文受付キューParams.注文受付結果＝"エラー"の場合
    //l_result.isFailedResult()
    public void testNotifyOrderAccept_C0007()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAcceptRow.TYPE);
            HostEqtypeOrderAcceptParams acceptParams= TestDBUtility.getHostEqtypeOrderAcceptRow();
            acceptParams.setBranchCode("381");
            acceptParams.setAccountCode("2512246");
            acceptParams.setTraderCode("11123");
            acceptParams.setInstitutionCode("0D");
            acceptParams.setAcceptNumber("1111");
            acceptParams.setStatus("0");
            acceptParams.setAcceptStatus(WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR);
            TestDBUtility.insertWithDel(acceptParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setAccountId(333812512246L);
            eqtypeOrderUnitParams.setOrderRequestNumber("000022001");
            eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setAccountId(333812512246L);
            l_eqtypeOrderParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subaccountParams = TestDBUtility.getSubAccountRow();
            subaccountParams.setAccountId(333812512246L);
            subaccountParams.setSubAccountId(33381251220301L);
            subaccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subaccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(marketParams);
            
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00004);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    processingResult);
            
            l_serviceImpl.notifyOrderAccept(acceptParams);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00004, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //株式注文受付キューParams.注文受付結果＝"エラー"の場合
    //l_result.isSuccessResult()
    //is予約注文確認要=true
    //処理区分更新
    public void testNotifyOrderAccept_C0008()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RsvEqOrderActionRow.TYPE);
            TestDBUtility.deleteAll(HostEqtypeOrderAcceptRow.TYPE);
            HostEqtypeOrderAcceptParams acceptParams= TestDBUtility.getHostEqtypeOrderAcceptRow();
            acceptParams.setBranchCode("381");
            acceptParams.setAccountCode("2512246");
            acceptParams.setTraderCode("11123");
            acceptParams.setInstitutionCode("0D");
            acceptParams.setAcceptNumber("1111");
            acceptParams.setStatus("0");
            acceptParams.setAcceptStatus(WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR);
            TestDBUtility.insertWithDel(acceptParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setAccountId(333812512246L);
            eqtypeOrderUnitParams.setOrderRequestNumber("000022001");
            eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            eqtypeOrderUnitParams.setReserveOrderExistFlag(WEB3ReserveOrderExistFlagDef.SET_POSSIBLE);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setAccountId(333812512246L);
            l_eqtypeOrderParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subaccountParams = TestDBUtility.getSubAccountRow();
            subaccountParams.setAccountId(333812512246L);
            subaccountParams.setSubAccountId(33381251220301L);
            subaccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subaccountParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(marketParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl",
                    "invalidateOrderUnit",
                    new Class[] {RsvEqOrderUnitRow.class,String.class},
                    null);
            
            l_serviceImpl.notifyOrderAccept(acceptParams);
            
            assertEquals(WEB3HostStatusDef.COMPLETE_PROCESS, acceptParams.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //株式注文受付キューParams.注文受付結果＝"エラー"の場合
    //l_result.isSuccessResult()
    //市場コード：　@注文単位.市場IDに該当する市場オブジェクト.市場コード取得失敗
    //SYSTEM_ERROR_80005
    public void testNotifyOrderAccept_C0009()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAcceptRow.TYPE);
            HostEqtypeOrderAcceptParams acceptParams= TestDBUtility.getHostEqtypeOrderAcceptRow();
            acceptParams.setBranchCode("381");
            acceptParams.setAccountCode("2512246");
            acceptParams.setTraderCode("11123");
            acceptParams.setInstitutionCode("0D");
            acceptParams.setAcceptNumber("1111");
            acceptParams.setStatus("0");
            acceptParams.setAcceptStatus(WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR);
            TestDBUtility.insertWithDel(acceptParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setAccountId(333812512246L);
            eqtypeOrderUnitParams.setOrderRequestNumber("000022001");
            eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            eqtypeOrderUnitParams.setReserveOrderExistFlag(WEB3ReserveOrderExistFlagDef.NOT_SET);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setAccountId(333812512246L);
            l_eqtypeOrderParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subaccountParams = TestDBUtility.getSubAccountRow();
            subaccountParams.setAccountId(333812512246L);
            subaccountParams.setSubAccountId(33381251220301L);
            subaccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subaccountParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            marketParams.setMarketId(3302L);
            TestDBUtility.insertWithDel(marketParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl",
                    "invalidateOrderUnit",
                    new Class[] {RsvEqOrderUnitRow.class,String.class},
                    null);
            
            l_serviceImpl.notifyOrderAccept(acceptParams);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //株式注文受付キューParams=null
    //SYSTEM_ERROR_80017
    public void testUpdateStatus_C0001()
    {
        final String STR_METHOD_NAME = "testUpdateStatus_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderAcceptParams acceptParams=null;
            l_serviceImpl.updateStatus(acceptParams, null);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //処理区分をupdate
    public void testUpdateStatus_C0002()
    {
        final String STR_METHOD_NAME = "testUpdateStatus_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderAcceptParams acceptParams = new HostEqtypeOrderAcceptParams();
            acceptParams.setStatus("0");

            l_serviceImpl.updateStatus(acceptParams, "1");
            assertEquals(acceptParams.getStatus(), "1");
            assertEquals(acceptParams.getLastUpdatedTimestamp(), GtlUtils.getSystemTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //【株式注文受付キューテーブル】注文受付のキューデータ一件に対する処理を行う。
    public void testNotifyOrderAcceptOvertime_C0001()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAcceptOvertime_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostEqtypeOrderAcceptRow.TYPE);
            HostEqtypeOrderAcceptParams acceptParams= TestDBUtility.getHostEqtypeOrderAcceptRow();
            acceptParams.setBranchCode("381");
            acceptParams.setAccountCode("2512246");
            acceptParams.setTraderCode("11123");
            acceptParams.setInstitutionCode("0D");
            acceptParams.setAcceptNumber("1111");
            acceptParams.setStatus("0");
            TestDBUtility.insertWithDel(acceptParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setAccountId(333812512246L);
            eqtypeOrderUnitParams.setOrderRequestNumber("000022001");
            eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subaccountParams = TestDBUtility.getSubAccountRow();
            subaccountParams.setAccountId(333812512246L);
            subaccountParams.setSubAccountId(11111);
            TestDBUtility.insertWithDel(subaccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                "updateHostEqtypeOrderAllAtAcceptOvertime",
                new Class[] {EqTypeOrderUnit.class,
                    EqTypeOrderUnit.class,
                    boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "updateOrderData", new Class[]
                    { EqTypeOrderUnit.class,
                    boolean.class},
                    null);
            
            l_serviceImpl.notifyOrderAcceptOvertime(acceptParams);
            
            assertEquals(WEB3HostStatusDef.COMPLETE_PROCESS, acceptParams.getStatus());
            assertEquals(GtlUtils.getSystemTimestamp(), acceptParams.getLastUpdatedTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
