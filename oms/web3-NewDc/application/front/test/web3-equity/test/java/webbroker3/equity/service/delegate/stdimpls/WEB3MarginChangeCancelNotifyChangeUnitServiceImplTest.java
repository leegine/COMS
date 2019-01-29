head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginChangeCancelNotifyChangeUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3MarginChangeCancelNotifyChangeUnitServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/31 �k�v�u(���u) �V�K�쐬 ���f�� 132
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionSONARDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginChangeCancelNotifyChangeUnitServiceImplTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginChangeCancelNotifyChangeUnitServiceImplTest.class);

    WEB3MarginChangeCancelNotifyChangeUnitServiceImpl l_serviceImpl= null;

    public WEB3MarginChangeCancelNotifyChangeUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3MarginChangeCancelNotifyChangeUnitServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //getOrderUnit(�����P��.�����P��ID : long)�Ȃ�
    //WEB3ErrorCatalog.SYSTEM_ERROR_80005
    public void testNotifyChange_C0001()
    {
        final String STR_METHOD_NAME = "testCalcEstimateDeliveryAmount_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            EqTypeOrderUnitForTest forTest = new EqTypeOrderUnitForTest();
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_serviceImpl.notifyChange(l_params, forTest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
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

    //validate�������ʃR�[�h()
    public void testNotifyChange_C0002()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.PARTIALLY_CANCEL);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00129, l_ex.getErrorInfo());
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
    
    // is�X�g�b�v�����L��(EqTypeOrderUnit)=true
    //1.11. getInstitution()
    //1.13. getMainAccount()
    //1.14. getSubAccount()
    //OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()) �����������T�Z��� = null calc�����������
    //1.22. �ʒm�L���[.��������ʒm�敪��"��������"�̏ꍇ ,l_result.isSuccessResult()
    //1.25.4 ������ɑS�����ƂȂ����ꍇ
    public void testNotifyChange_C0003()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedContractPrice price = new WEB3EquityEstimatedContractPrice();
            price.setCalcUnitPrice(100);
            price.setEstimatedContractPrice(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                            String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                            EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                            price);
            
            ProcessingResult result = ProcessingResult.newSuccessResultInstance();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    result);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
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

    //is�X�g�b�v�����L��(EqTypeOrderUnit)=false
    //1.11. getInstitution()
    //1.13. getMainAccount()
    //1.14. getSubAccount()
    //OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()) �����������̏ꍇ get�X�g�b�v�����������T�Z����v�Z����
    //1.22. �ʒm�L���[.��������ʒm�敪��"��������"�̏ꍇ ,l_result.isSuccessResult()
    public void testNotifyChange_C0004()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedContractPrice price = new WEB3EquityEstimatedContractPrice();
            price.setCalcUnitPrice(100);
            price.setEstimatedContractPrice(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                            String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                            EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                            price);
            
            ProcessingResult result = ProcessingResult.newSuccessResultInstance();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    result);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGE_FAILED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
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

    //is�X�g�b�v�����L��(EqTypeOrderUnit)=true
    //1.11. getInstitution()�Ȃ�
    public void testNotifyChange_C0005()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("00");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
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

    //is�X�g�b�v�����L��(EqTypeOrderUnit)=true
    //1.11. getInstitution()
    //1.13. getMainAccount()�Ȃ�
    public void testNotifyChange_C0006()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("382");
            l_params.setAccountCode("2512246");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
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

    //is�X�g�b�v�����L��(EqTypeOrderUnit)=true
    //1.11. getInstitution()
    //1.13. getMainAccount()
    //1.14. getSubAccount()
    //OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg())  adjust�ԍώw����() setIs�w�l(true)
    //1.22. �ʒm�L���[.��������ʒm�敪��"��������"�̏ꍇ ,l_result.isSuccessResult()
    //1.25.4 ������ɑS�����ƂȂ����ꍇ
    public void testNotifyChange_C0007()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedContractPrice price = new WEB3EquityEstimatedContractPrice();
            price.setCalcUnitPrice(100);
            price.setEstimatedContractPrice(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                            String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                            EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                            price);
            
            ProcessingResult result = ProcessingResult.newSuccessResultInstance();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    result);

            WEB3EquityRealizedProfitAndLossPrice price2 = new WEB3EquityRealizedProfitAndLossPrice();
            price2.setCalcUnitPrice(100);
            price2.setEstimatedRealizedProfitAndLossAmount(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[]{WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class,
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class},
                            price2);
                            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGED);
            l_params.setModifiedLimitPrice(11);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
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

    //is�X�g�b�v�����L��(EqTypeOrderUnit)=true
    //1.11. getInstitution()
    //1.13. getMainAccount()
    //1.14. getSubAccount()
    //OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()) �����������T�Z��� = null calc�����������
    //1.22. �ʒm�L���[.��������ʒm�敪��"��������"�̏ꍇ ,l_result.isFailedResult()
    //1.25.4 ������ɑS�����ƂȂ����ꍇ
    public void testNotifyChange_C0008()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedContractPrice price = new WEB3EquityEstimatedContractPrice();
            price.setCalcUnitPrice(100);
            price.setEstimatedContractPrice(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                            String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                            EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                            price);
            
            ProcessingResult result = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00003);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    result);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003, l_ex.getErrorInfo());
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

    //is�X�g�b�v�����L��(EqTypeOrderUnit)=true
    //1.11. getInstitution()
    //1.13. getMainAccount()
    //1.14. getSubAccount()
    //OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()) �����������T�Z��� = null calc�����������
    //1.22. �ʒm�L���[.��������ʒm�敪��"��������"�̏ꍇ ,l_result.isSuccessResult()
    //1.25.4 ������ɑS�����ƂȂ����ꍇ isFullyExecuted() = true
    public void testNotifyChange_C0009()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedContractPrice price = new WEB3EquityEstimatedContractPrice();
            price.setCalcUnitPrice(100);
            price.setEstimatedContractPrice(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                            String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                            EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                            price);
            
            ProcessingResult result = ProcessingResult.newSuccessResultInstance();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    result);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecutedMailSenderServiceImpl",
                    "sendMailProcess", 
                    new Class[]{OrderUnit.class, String.class},
                    null);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
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

    //is�X�g�b�v�����L��(EqTypeOrderUnit)=false
    //1.11. getInstitution()
    //1.13. getMainAccount()
    //1.14. getSubAccount()
    //OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()) �����������̏ꍇ get�X�g�b�v�����������T�Z����v�Z����
    //1.22. �ʒm�L���[.��������ʒm�敪��"��������"�̏ꍇ ,l_result.isFailedResult()
    public void testNotifyChange_C0010()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedContractPrice price = new WEB3EquityEstimatedContractPrice();
            price.setCalcUnitPrice(100);
            price.setEstimatedContractPrice(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                            String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                            EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                            price);
            
            ProcessingResult result = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00003);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    result);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGE_FAILED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003, l_ex.getErrorInfo());
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

    //is�X�g�b�v�����L��(EqTypeOrderUnit)=true
    //1.11. getInstitution()
    //1.13. getMainAccount()
    //1.14. getSubAccount()
    //OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg())  adjust�ԍώw����() setIs�w�l(false)
    //1.22. �ʒm�L���[.��������ʒm�敪��"��������"�̏ꍇ ,l_result.isSuccessResult()
    //1.25.4 ������ɑS�����ƂȂ����ꍇ
    public void testNotifyChange_C0011()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedContractPrice price = new WEB3EquityEstimatedContractPrice();
            price.setCalcUnitPrice(100);
            price.setEstimatedContractPrice(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class, String.class,
                            String.class, String.class, boolean.class, boolean.class, SubAccount.class,
                            EqTypeTradedProduct.class, double.class, double.class, double.class, boolean.class },
                            price);
            
            ProcessingResult result = ProcessingResult.newSuccessResultInstance();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    result);

            WEB3EquityRealizedProfitAndLossPrice price2 = new WEB3EquityRealizedProfitAndLossPrice();
            price2.setCalcUnitPrice(100);
            price2.setEstimatedRealizedProfitAndLossAmount(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[]{WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class,
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class},
                            price2);
                            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGED);
            l_params.setModifiedLimitPrice(0);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
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

    //�R�[�h�l���h0?�h�i���������ɊY���j�̏ꍇ�A�ȉ��̒����P�[�X�̂����ꂩ�ɊY�����邩�`�F�b�N����B
    //02�F�S�������i���o���Ȃ��j 
    //03�F�S�������i���o������j  
    //05�F�ꕔ���� 
    //08�F�ꕔ�����s�\�i���o���Ȃ��j 
    //09�F�ꕔ�����s�\�i���o������j
    public void testValidateChangeResultCode_C0001()
    {
        final String STR_METHOD_NAME = "testValidateChangeResultCode_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult("02");
            l_serviceImpl.validateChangeResultCode(l_params);
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

    //�R�[�h�l���h0?�h�i���������ɊY���j�̏ꍇ�A�ȉ��̒����P�[�X�̂����ꂩ�ɊY�����邩�`�F�b�N����B 
    //������ɂ��Y�����Ȃ��ꍇ�́A��O��throw����B 
    public void testValidateChangeResultCode_C0002()
    {
        final String STR_METHOD_NAME = "testValidateChangeResultCode_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult("04");
            l_serviceImpl.validateChangeResultCode(l_params);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_00129, l_ex.getErrorInfo());
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

    //�������ɂ�����A�y��������ʒm�L���[�e�[�u���z�́u�������ʃR�[�h�v�̒l�̐������`�F�b�N�Ȃ����s���B 
    public void testValidateChangeResultCode_C0003()
    {
        final String STR_METHOD_NAME = "testValidateChangeResultCode_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult("11");
            l_serviceImpl.validateChangeResultCode(l_params);
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


    private class EqTypeOrderUnitForTest implements EqTypeOrderUnit
    {

        public EqTypeOrder getEqTypeOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getBranchId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public OrderAction[] getOrderActions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getReceivedTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getExpirationTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Order getOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExecution[] getExecutions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderOpenStatusEnum getOrderOpenStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderTypeEnum getOrderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderCategEnum getOrderCateg()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public TaxTypeEnum getTaxType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public SideEnum getSide()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderStatusEnum getOrderStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExpirationStatusEnum getExpirationStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isExpired()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isFullyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPartiallyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isUnexecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isConfirmedPriceMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getExecutedAmount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getExecutedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getLimitPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
}
@