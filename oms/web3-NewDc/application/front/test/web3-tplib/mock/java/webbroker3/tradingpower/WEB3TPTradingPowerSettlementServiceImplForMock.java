head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerSettlementServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower;

import java.util.Date;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


public class WEB3TPTradingPowerSettlementServiceImplForMock extends WEB3TPTradingPowerSettlementServiceImpl {

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3TPTradingPowerSettlementServiceImplForMock.class);
    
    /**
     * （get差金決済買付可能額）<BR>
     * <BR>
     * シーケンス図「(差金決済取引余力サービス)get差金決済買付可能額」参照
     * <BR>
     * @@param l_subAccount - （補助口座）
     * @@param l_datSpecifiedDate - （指定日）
     * @@param l_lngOrderFundId - （注文銘柄ID）
     * @@param l_blnTodayRepFund - （即日入金対象銘柄フラグ）
     * 　@・指定銘柄が即日入金対象銘柄の場合⇒true
     * 　@・指定銘柄が即日入金対象銘柄でない場合⇒false
     * @@return double
     */
    public double getBuyOrderPossibleAmount(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datSpecifiedDate,
        long l_lngOrderFundId,
        boolean l_blnTodayRepFund)
        throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.tradingpower.WEB3TPTradingPowerSettlementServiceImpl",
            "getBuyOrderPossibleAmount",
            new Class[] {WEB3GentradeSubAccount.class,Date.class, long.class,boolean.class},
            new Object[]{l_subAccount, l_datSpecifiedDate, new Long(l_lngOrderFundId), new Boolean(l_blnTodayRepFund)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.tradingpower.WEB3TPTradingPowerSettlementServiceImpl", 
            "getBuyOrderPossibleAmount",
            new Class[] {WEB3GentradeSubAccount.class,Date.class, long.class,boolean.class}))
        {
            log.debug("webbroker3.tradingpower.WEB3TPTradingPowerSettlementServiceImpl.getBuyOrderPossibleAmount");
            return (double) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerSettlementServiceImpl", 
                "getBuyOrderPossibleAmount",
                new Class[] {WEB3GentradeSubAccount.class, Date.class, long.class, boolean.class})
                .asDouble();
        }
        return super.getBuyOrderPossibleAmount(l_subAccount, l_datSpecifiedDate, l_lngOrderFundId, l_blnTodayRepFund);
    }
    
    public double getSellOrderPossibleQuantity(WEB3GentradeSubAccount l_subAccount, Date l_datSpecifiedDate,
            long l_lngOrderFundId, long l_lngMarketId, double l_dblLimitPrice, double l_dblLotSize)
            throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerSettlementServiceImpl", "getSellOrderPossibleQuantity",
                new Class[]
                { WEB3GentradeSubAccount.class, Date.class, long.class, long.class, double.class, double.class },
                new Object[]
                { l_subAccount, l_datSpecifiedDate, new Long(l_lngOrderFundId), new Long(l_lngMarketId),
                        new Double(l_dblLimitPrice), new Double(l_dblLotSize) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.tradingpower.WEB3TPTradingPowerSettlementServiceImpl",
                "getSellOrderPossibleQuantity", new Class[]
                { WEB3GentradeSubAccount.class, Date.class, long.class, long.class, double.class, double.class }))
        {
            log
                    .debug("webbroker3.tradingpower.WEB3TPTradingPowerSettlementServiceImplForMock.getSellOrderPossibleQuantity()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerSettlementServiceImpl", "getSellOrderPossibleQuantity",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class, long.class, long.class, double.class, double.class })
                    .asDouble();
        }
        return super.getSellOrderPossibleQuantity(l_subAccount, l_datSpecifiedDate, l_lngOrderFundId, l_lngMarketId,
                l_dblLimitPrice, l_dblLotSize);
    }
}
@
