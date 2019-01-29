head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqBizLogicProviderForMock.java;


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
package webbroker3.feq;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqBizLogicProviderForMock extends WEB3FeqBizLogicProvider
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3FeqBizLogicProviderForMock.class);
    
    public WEB3FeqAmountCalcResult calcFeqAmount(
            WEB3GentradeSubAccount l_subAccount,
            WEB3FeqProduct l_feqProduct,
            WEB3GentradeMarket l_market,
            Date l_datBaseDate,
            Date l_datExecDate,
            double l_dblTradePriceFc,
            double l_dblFxRate,
            boolean l_blnIsBuy,
            boolean l_blnIsExecCalc,
            boolean l_blnIsLimitPrice,
            String l_strOrderChannel) throws WEB3BaseException
        {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmount",
                new Class[]
                {
                    WEB3GentradeSubAccount.class,
                    WEB3FeqProduct.class,
                    WEB3GentradeMarket.class,
                    Date.class,
                    Date.class,
                    double.class,
                    double.class,
                    boolean.class,
                    boolean.class,
                    boolean.class,
                    String.class},
                new Object[]{
                    l_subAccount,
                    l_feqProduct,
                    l_market,
                    l_datBaseDate,
                    l_datExecDate,
                    new Double(l_dblTradePriceFc),
                    new Double(l_dblFxRate),
                    new Boolean(l_blnIsBuy),
                    new Boolean(l_blnIsExecCalc),
                    new Boolean(l_blnIsLimitPrice),
                    l_strOrderChannel});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmount",
                new Class[] {WEB3GentradeSubAccount.class,WEB3FeqProduct.class,WEB3GentradeMarket.class,
                        Date.class,Date.class,double.class,double.class,boolean.class,boolean.class,
                        boolean.class,String.class}))
            {
            log.debug("webbroker3.feq.WEB3FeqBizLogicProvider.calcFeqAmount");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcFeqAmount",
                    new Class[] {WEB3GentradeSubAccount.class,WEB3FeqProduct.class,WEB3GentradeMarket.class,
                    Date.class,Date.class,double.class,double.class,boolean.class,boolean.class,
                    boolean.class,String.class}).asWEB3BaseException();
            
            return (WEB3FeqAmountCalcResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcFeqAmount",
                    new Class[] {WEB3GentradeSubAccount.class,WEB3FeqProduct.class,WEB3GentradeMarket.class,
                    Date.class,Date.class,double.class,double.class,boolean.class,boolean.class,
                    boolean.class,String.class}).asObject();
            }
        
        return super.calcFeqAmount(l_subAccount,l_feqProduct,l_market,
                l_datBaseDate,l_datExecDate,l_dblTradePriceFc,l_dblFxRate,l_blnIsBuy,
                l_blnIsExecCalc,l_blnIsLimitPrice,l_strOrderChannel);
        }
    
    public WEB3FeqAmountCalcResultFactor calcFeqAmountFactor(FeqFinTransactionParams[] l_feqFinTransactionParams)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmountFactor", new Class[]
                { FeqFinTransactionParams[].class },
                new Object[]{l_feqFinTransactionParams});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqBizLogicProvider", "calcFeqAmountFactor",
                new Class[]
                { FeqFinTransactionParams[].class }))
        {
            log.debug("webbroker3.feq.WEB3FeqBizLogicProviderForMock.calcFeqAmountFactor()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcFeqAmountFactor", new Class[]
                    { FeqFinTransactionParams[].class }).asWEB3BaseException();
            return (WEB3FeqAmountCalcResultFactor) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider", "calcFeqAmountFactor", new Class[]
                    { FeqFinTransactionParams[].class }).asObject();
        }
        return super.calcFeqAmountFactor(l_feqFinTransactionParams);
    }
    
    public double calcCapitalProfitLoss(
            double l_dblTradeAmount,
            double l_dblSellStockQuantity,
            long l_lngProductId,
            WEB3GentradeSubAccount l_subAccount,
            TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcCapitalProfitLoss", new Class[]
                { double.class, double.class, long.class, WEB3GentradeSubAccount.class, TaxTypeEnum.class},
                new Object[]{ new Double(l_dblTradeAmount) ,new Double(l_dblSellStockQuantity),
                 new Long(l_lngProductId), l_subAccount,l_taxType});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcCapitalProfitLoss",
                new Class[]{double.class,
                double.class,
                long.class, 
                WEB3GentradeSubAccount.class,
                TaxTypeEnum.class}))
        {

            log.debug("webbroker3.feq.WEB3FeqBizLogicProviderForMock.calcFeqAmountFactor()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcCapitalProfitLoss", new Class[]
                    {double.class, double.class, long.class, WEB3GentradeSubAccount.class, TaxTypeEnum.class}).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider", "calcCapitalProfitLoss", new Class[]
                    { double.class, double.class, long.class, WEB3GentradeSubAccount.class, TaxTypeEnum.class}).asDouble();
        }
            return super.calcCapitalProfitLoss(l_dblTradeAmount, l_dblSellStockQuantity, l_lngProductId, l_subAccount, l_taxType);

    }
    
    public double calcForeignCCYAmount(
            double l_dblAmount,
            double l_dblRate,
            long l_lngProductId,
            boolean l_blnIsBuy,
            boolean l_blnIsExecCalc)
            throws WEB3BaseException
        {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcForeignCCYAmount", new Class[]
                { double.class, double.class, long.class, boolean.class, boolean.class},
                new Object[]{ new Double(l_dblAmount) ,new Double(l_dblRate),
                 new Long(l_lngProductId), new Boolean(l_blnIsBuy), new Boolean(l_blnIsExecCalc)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcForeignCCYAmount",
                new Class[]{double.class,
                double.class,
                long.class, 
                boolean.class,
                boolean.class}))
        {
            log.debug("webbroker3.feq.WEB3FeqBizLogicProviderForMock.calcForeignCCYAmount()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount", new Class[]
                    {double.class, double.class, long.class, boolean.class, boolean.class}).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider", "calcForeignCCYAmount", new Class[]
                    { double.class, double.class, long.class, boolean.class, boolean.class}).asDouble();
        }
            return super.calcForeignCCYAmount(l_dblAmount, l_dblRate, l_lngProductId, l_blnIsBuy, l_blnIsExecCalc);
        }

    public WEB3FeqAmountCalcResult calcChangeFeqAmount(
            WEB3GentradeSubAccount l_subAccount,
            WEB3FeqProduct l_feqProduct,
            WEB3GentradeMarket l_market,
            Date l_datBaseDate,
            double l_dblTradePriceFc,
            double l_dblFxRate,
            boolean l_blnIsBuy,
            boolean l_blnIsExecCalc,
            boolean l_blnIsLimitPrice,
            FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.feq.WEB3FeqBizLogicProvider",
            "calcChangeFeqAmount",
            new Class[]{WEB3GentradeSubAccount.class, WEB3FeqProduct.class,
            WEB3GentradeMarket.class, Date.class, double.class, double.class, boolean.class,
            boolean.class, boolean.class, FeqOrderUnit.class},
            new Object[]{l_subAccount, l_feqProduct, l_market, l_datBaseDate,
            new Double(l_dblTradePriceFc), new Double(l_dblFxRate), new Boolean(l_blnIsBuy),
            new Boolean(l_blnIsExecCalc), new Boolean(l_blnIsLimitPrice), l_feqOrderUnit});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.WEB3FeqBizLogicProvider",
            "calcChangeFeqAmount",
            new Class[]{WEB3GentradeSubAccount.class, WEB3FeqProduct.class,
            WEB3GentradeMarket.class, Date.class, double.class, double.class, boolean.class,
            boolean.class, boolean.class, FeqOrderUnit.class}))
        {
            return (WEB3FeqAmountCalcResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider", "calcChangeFeqAmount", new Class[]
                {WEB3GentradeSubAccount.class, WEB3FeqProduct.class,
                WEB3GentradeMarket.class, Date.class, double.class, double.class, boolean.class,
                boolean.class, boolean.class, FeqOrderUnit.class}).asObject();
        }
            return super.calcChangeFeqAmount(l_subAccount, l_feqProduct, l_market, l_datBaseDate, l_dblTradePriceFc, l_dblFxRate,l_blnIsBuy,
                l_blnIsExecCalc, l_blnIsLimitPrice, l_feqOrderUnit);
    }
    
    public BigDecimal calcForeignCCYAmount(
            BigDecimal l_bdAmount,
            double l_dblRate,
            int l_intScale,
            String l_strChangeFccyRoundDiv) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcForeignCCYAmount", new Class[]
                { BigDecimal.class, double.class, int.class, String.class},
                new Object[]{l_bdAmount ,new Double(l_dblRate),
                 new Integer(l_intScale), l_strChangeFccyRoundDiv});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcForeignCCYAmount",
                new Class[]{ BigDecimal.class, double.class, int.class, String.class}))
        {
            log.debug("webbroker3.feq.WEB3FeqBizLogicProviderForMock.calcForeignCCYAmount()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcForeignCCYAmount", new Class[]
                    { BigDecimal.class, double.class, int.class, String.class}).asWEB3BaseException();
            return (BigDecimal)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider", "calcForeignCCYAmount", new Class[]
                    { BigDecimal.class, double.class, int.class, String.class}).asObject();
        }
            return super.calcForeignCCYAmount(l_bdAmount, l_dblRate, l_intScale, l_strChangeFccyRoundDiv);
        
    }
    
    public double calcJPYAmount(
        double l_dblForeignAmount, 
        double l_dblRate, 
        String l_strChangeFccyRoundDiv)
    {
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
			"webbroker3.feq.WEB3FeqBizLogicProvider",
            "calcJPYAmount",
			new Class[]{
                double.class,
                double.class,
                String.class
			},
            new Object[]{
                new Double(l_dblForeignAmount),
                new Double(l_dblRate),
                l_strChangeFccyRoundDiv
            });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcJPYAmount",
                new Class[]{
                    double.class,
                    double.class,
                    String.class
                }))
        {
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcJPYAmount",
                    new Class[]{
                        double.class,
                        double.class,
                        String.class
                    }).asDouble();
        }
        return super.calcJPYAmount(l_dblForeignAmount, l_dblRate, l_strChangeFccyRoundDiv);
    }
    
    public void calcCommission(WEB3GentradeCommission l_commission, SubAccount l_subAccount) 
        throws WEB3BaseException
        {
            TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcCommission",
                new Class[]{
                    WEB3GentradeCommission.class,
                    SubAccount.class
                },
                new Object[]{
                    l_commission,
                    l_subAccount
                });
            
            if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcCommission",
                    new Class[]{
                            WEB3GentradeCommission.class,
                            SubAccount.class
                        }))
            {
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.feq.WEB3FeqBizLogicProvider",
                        "calcCommission",
                        new Class[]{
                                WEB3GentradeCommission.class,
                                SubAccount.class
                            }).asVoid();
                return;
            }
            super.calcCommission( l_commission,  l_subAccount);
        }
    
    public double calcSalesTax(double l_dblAmount, Date l_tsBaseDate, SubAccount l_subAccount)
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcSalesTax", new Class[]
                { double.class, Date.class, SubAccount.class }, new Object[]
                { new Double(l_dblAmount), l_tsBaseDate, l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.feq.WEB3FeqBizLogicProvider", "calcSalesTax",
                new Class[]
                { double.class, Date.class, SubAccount.class }))
        {
            log.debug("webbroker3.feq.WEB3FeqBizLogicProviderForMock.calcSalesTax()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcSalesTax", new Class[]
                    { double.class, Date.class, SubAccount.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider", "calcSalesTax", new Class[]
                    { double.class, Date.class, SubAccount.class }).asDouble();
        }
        return super.calcSalesTax(l_dblAmount, l_tsBaseDate, l_subAccount);
    }
}
@
