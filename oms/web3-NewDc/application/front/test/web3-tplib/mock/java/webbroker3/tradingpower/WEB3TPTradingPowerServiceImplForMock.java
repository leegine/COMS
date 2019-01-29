head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 
 Author Name      : Daiwa Institute of Research
 Revesion History : 
 */
package webbroker3.tradingpower;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPTradingPowerServiceImplForMock extends WEB3TPTradingPowerServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3TPTradingPowerServiceImplForMock.class);
    
    public WEB3TPTradingPowerResult validateTradingPower(WEB3GentradeSubAccount l_subAccount,
            Object[] l_orderSpecIntercepter, Object[] l_orderSpec, OrderTypeEnum l_orderTypeEnum, boolean l_blnUpdateFlg)
            throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
            "validateTradingPower",
            new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
            new Object[]{l_subAccount, l_orderSpecIntercepter, l_orderSpec, l_orderTypeEnum, new Boolean(l_blnUpdateFlg)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class}))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl.validateTradingPower");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class}).asWEB3SystemLayerException();
            return (WEB3TPTradingPowerResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class})
                    .asObject();
        }
        return super.validateTradingPower(l_subAccount, l_orderSpecIntercepter, l_orderSpec, l_orderTypeEnum,
                l_blnUpdateFlg);
    }

    public double getPaymentTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getPaymentTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class},
                new Object[]{l_subAccount, l_datDeliveryDate});

            if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getPaymentTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class}))
            {
                log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl.getPaymentTradingPower");
                
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getPaymentTradingPower",
                        new Class[] {WEB3GentradeSubAccount.class, Date.class}).asWEB3SystemLayerException();
                
                return (double) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getPaymentTradingPower",
                        new Class[] {WEB3GentradeSubAccount.class, Date.class})
                        .asDouble();
            }
            return super.getPaymentTradingPower(l_subAccount, l_datDeliveryDate);
    }

    /**
     * (余力再計算(Mock))<BR>
     * 余力再計算を実施し、引数で指定された顧客の余力状態を最新にする。<BR>
     * <BR>
     * ※シーケンス図「(取引余力サービス)余力再計算」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     */
    public void reCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "reCalcTradingPower(WEB3GentradeSubAccount)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
            "reCalcTradingPower",
            new Class[] {WEB3GentradeSubAccount.class},
            new Object[]{l_subAccount});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
            "reCalcTradingPower",
            new Class[] {WEB3GentradeSubAccount.class}))
        {

            //2)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class}).asVoid();
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.reCalcTradingPower(l_subAccount);
    }

    /**
     * (get投資信託買付可能額(Mock))<BR>
     * 投信買付可能額を取得する。<BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_orderTypeEnum - (注文種別)
     * @@return double
     */
    public double getMutualFundBuyTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate,
        OrderTypeEnum l_orderTypeEnum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getMutualFundBuyTradingPower(WEB3GentradeSubAccount, Date, OrderTypeEnum)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
            "getMutualFundBuyTradingPower",
            new Class[] {WEB3GentradeSubAccount.class, Date.class, OrderTypeEnum.class},
            new Object[]{l_subAccount, l_datDeliveryDate, l_orderTypeEnum});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
            "getMutualFundBuyTradingPower",
            new Class[] {WEB3GentradeSubAccount.class, Date.class, OrderTypeEnum.class}))
        {

            //2)MockFor --〉 asVoid
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getMutualFundBuyTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class, OrderTypeEnum.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getMutualFundBuyTradingPower(l_subAccount, l_datDeliveryDate, l_orderTypeEnum);
    }
    
    
    /**
     * (getその他商品買付可能額)<BR>
     * 現物株式、信用取引以外の取引可能額を取得する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     */
    public double getOtherTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOtherTradingPower(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);
        
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
            "getOtherTradingPower",
            new Class[] {WEB3GentradeSubAccount.class, Date.class},
            new Object[]{l_subAccount, l_datDeliveryDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
            "getOtherTradingPower",
            new Class[] {WEB3GentradeSubAccount.class, Date.class}))
        {
            //2)MockFor --〉 asDouble
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getOtherTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getOtherTradingPower(l_subAccount, l_datDeliveryDate);
    }
    
    public Double getOptionBuyTradingPower(WEB3GentradeSubAccount l_subAccount, IfoProduct l_ifoProduct)
            throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getOptionBuyTradingPower", new Class[]
                { WEB3GentradeSubAccount.class, IfoProduct.class }, new Object[]
                { l_subAccount, l_ifoProduct });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getOptionBuyTradingPower", new Class[]
                { WEB3GentradeSubAccount.class, IfoProduct.class }))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.getOptionBuyTradingPower()");

            return (Double) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getOptionBuyTradingPower", new Class[]
                    { WEB3GentradeSubAccount.class, IfoProduct.class }).asObject();
        }
        return super.getOptionBuyTradingPower(l_subAccount, l_ifoProduct);
    }
    
    public Double getFuturesOptionTradingPower(WEB3GentradeSubAccount l_subAccount, boolean l_blnLongFlg,
            IfoProduct l_ifoProduct) throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getFuturesOptionTradingPower", new Class[]
                { WEB3GentradeSubAccount.class, boolean.class, IfoProduct.class }, new Object[]
                { l_subAccount, new Boolean(l_blnLongFlg), l_ifoProduct });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getFuturesOptionTradingPower", new Class[]
                { WEB3GentradeSubAccount.class, boolean.class, IfoProduct.class }))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.getFuturesOptionTradingPower()");

            return (Double) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getFuturesOptionTradingPower",
                    new Class[]
                    { WEB3GentradeSubAccount.class, boolean.class, IfoProduct.class }).asObject();
        }
        return super.getFuturesOptionTradingPower(l_subAccount, l_blnLongFlg, l_ifoProduct);
    }
    
    public double getOtherTradingPowerForCheck(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
            throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getOtherTradingPowerForCheck", new Class[]
                { WEB3GentradeSubAccount.class, Date.class }, new Object[]
                { l_subAccount, l_datDeliveryDate });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getOtherTradingPowerForCheck", new Class[]
                { WEB3GentradeSubAccount.class, Date.class }))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.getOtherTradingPowerForCheck()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getOtherTradingPowerForCheck",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class }).asDouble();
        }
        return super.getOtherTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
    }
    
    public double getTransferAmountToDeposit(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate,
            double l_dblPayAmount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getTransferAmountToDeposit", new Class[]
                { WEB3GentradeSubAccount.class, Date.class, double.class }, new Object[]
                { l_subAccount, l_datDeliveryDate, new Double(l_dblPayAmount) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getTransferAmountToDeposit", new Class[]
                { WEB3GentradeSubAccount.class, Date.class, double.class }))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.getTransferAmountToDeposit()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getTransferAmountToDeposit", new Class[]
                    { WEB3GentradeSubAccount.class, Date.class, double.class }).asDouble();
        }
        return super.getTransferAmountToDeposit(l_subAccount, l_datDeliveryDate, l_dblPayAmount);
    }
    
    public WEB3ForeignPositionContract getForeignPositionContract(WEB3GentradeSubAccount l_subAccount,
            String l_strCurrencyCode) throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getForeignPositionContract", new Class[]
                { WEB3GentradeSubAccount.class, String.class }, new Object[]
                { l_subAccount, l_strCurrencyCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getForeignPositionContract", new Class[]
                { WEB3GentradeSubAccount.class, String.class }))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.getForeignPositionContract()");
            return (WEB3ForeignPositionContract) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getForeignPositionContract", new Class[]
                    { WEB3GentradeSubAccount.class, String.class }).asObject();
        }
        return super.getForeignPositionContract(l_subAccount, l_strCurrencyCode);
    }
    
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(WEB3GentradeSubAccount l_subAccount)
            throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getTradingPowerCalcEquity", new Class[]
                { WEB3GentradeSubAccount.class }, new Object[]
                { l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getTradingPowerCalcEquity", new Class[]
                { WEB3GentradeSubAccount.class }))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.getTradingPowerCalcEquity()");
            return (WEB3TPTradingPowerCalcEquity) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getTradingPowerCalcEquity", new Class[]
                    { WEB3GentradeSubAccount.class }).asObject();
        }
        return super.getTradingPowerCalcEquity(l_subAccount);
    }
    
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(WEB3GentradeSubAccount l_subAccount)
            throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getTradingPowerCalcMargin", new Class[]
                { WEB3GentradeSubAccount.class }, new Object[]
                { l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getTradingPowerCalcMargin", new Class[]
                { WEB3GentradeSubAccount.class }))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.getTradingPowerCalcMargin()");
            return (WEB3TPTradingPowerCalcMargin) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getTradingPowerCalcMargin", new Class[]
                    { WEB3GentradeSubAccount.class }).asObject();
        }
        return super.getTradingPowerCalcMargin(l_subAccount);
    }
    
    public double getSLChangePossAmt(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
            throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getSLChangePossAmt", new Class[]
                { WEB3GentradeSubAccount.class, Date.class }, new Object[]
                { l_subAccount, l_datDeliveryDate });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getSLChangePossAmt", new Class[]
                { WEB3GentradeSubAccount.class, Date.class }))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.getSLChangePossAmt()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getSLChangePossAmt", new Class[]
                    { WEB3GentradeSubAccount.class, Date.class }).asDouble();
        }
        return super.getSLChangePossAmt(l_subAccount, l_datDeliveryDate);
    }
    
    public double getEquityTradingPower(WEB3GentradeSubAccount l_subAccount) throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getEquityTradingPower", new Class[]
                { WEB3GentradeSubAccount.class }, new Object[]
                { l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getEquityTradingPower", new Class[]
                { WEB3GentradeSubAccount.class }))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.getEquityTradingPower()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getEquityTradingPower", new Class[]
                    { WEB3GentradeSubAccount.class }).asDouble();
        }
        return super.getEquityTradingPower(l_subAccount);
    }
    
    public WEB3TPContractForcedSettleResult validateContractForcedSettleBefOnline(WEB3GentradeSubAccount l_subAccount)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateContractForcedSettleBefOnline", new Class[]
                {WEB3GentradeSubAccount.class}, new Object[]
                {l_subAccount});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateContractForcedSettleBefOnline", new Class[]
                {WEB3GentradeSubAccount.class}))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.validateContractForcedSettleBefOnline()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateContractForcedSettleBefOnline", new Class[]
                    {WEB3GentradeSubAccount.class}).asWEB3BaseException();
            return (WEB3TPContractForcedSettleResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateContractForcedSettleBefOnline",
                    new Class[]
                    {WEB3GentradeSubAccount.class}).asObject();
        }
        return super.validateContractForcedSettleBefOnline(l_subAccount);
    }
    
    public WEB3TPContractForcedSettleResult validateContractForcedSettleIntermission(WEB3GentradeSubAccount l_subAccount)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateContractForcedSettleIntermission", new Class[]
                { WEB3GentradeSubAccount.class }, new Object[]
                { l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateContractForcedSettleIntermission", new Class[]
                { WEB3GentradeSubAccount.class }))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.validateContractForcedSettleIntermission()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateContractForcedSettleIntermission", new Class[]
                    { WEB3GentradeSubAccount.class }).asWEB3BaseException();
            return (WEB3TPContractForcedSettleResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateContractForcedSettleIntermission",
                    new Class[]
                    { WEB3GentradeSubAccount.class }).asObject();
        }
        return super.validateContractForcedSettleIntermission(l_subAccount);
    }

    public double getPaymentTradingPowerForCheck(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getPaymentTradingPowerForCheck", new Class[]
                { WEB3GentradeSubAccount.class, Date.class }))
        {
            return  TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getPaymentTradingPowerForCheck",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class }).asDouble();
        }
        return super.getPaymentTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
    }

    public double getTransferAmountToEquitySubAcount(
        WEB3GentradeSubAccount l_subAccount,
        double l_dblNecessaryCash,
        Date l_datDeliveryDate)throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getTransferAmountToEquitySubAcount",
                new Class[]
                    { WEB3GentradeSubAccount.class, double.class, Date.class },
                new Object[]
                    { l_subAccount, new Double(l_dblNecessaryCash), l_datDeliveryDate });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getTransferAmountToEquitySubAcount",
                new Class[]{ WEB3GentradeSubAccount.class, double.class, Date.class }))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock.getTransferAmountToEquitySubAcount()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getTransferAmountToEquitySubAcount",
                new Class[]
                    { WEB3GentradeSubAccount.class, double.class, Date.class }).asWEB3SystemLayerException();

            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getTransferAmountToEquitySubAcount",
                new Class[]
                    { WEB3GentradeSubAccount.class, double.class, Date.class }).asDouble();
        }
        return super.getTransferAmountToEquitySubAcount(l_subAccount, l_dblNecessaryCash, l_datDeliveryDate );
    }

    public double getNextBizDateShortfall(WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getNextBizDateShortfall",
                new Class[]{ WEB3GentradeSubAccount.class}))
        {
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getNextBizDateShortfall",
                new Class[]
                    { WEB3GentradeSubAccount.class}).asDouble();
        }
        return super.getNextBizDateShortfall(l_subAccount);
    }

    public double getPaymentTradingPowerAioCashoutInput(
            WEB3GentradeSubAccount l_subAccount,
            Date l_datDeliveryDate) throws WEB3BaseException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getPaymentTradingPowerAioCashoutInput",
                new Class[]{ WEB3GentradeSubAccount.class, Date.class}))
        {
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getPaymentTradingPowerAioCashoutInput",
                new Class[]
                    { WEB3GentradeSubAccount.class, Date.class}).asDouble();
        }
        return super.getPaymentTradingPowerAioCashoutInput(l_subAccount, l_datDeliveryDate);
    }
}
@
