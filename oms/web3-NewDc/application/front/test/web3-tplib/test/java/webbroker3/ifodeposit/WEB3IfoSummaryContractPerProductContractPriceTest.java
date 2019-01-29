head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.29.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoSummaryContractPerProductContractPriceTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : WEB3IfoSummaryContractPerProductContractPriceTest.java
Author Name         : Daiwa Institute of Research
Revision History    : 2008/08/18 張少傑 (中訊) 新規作成
*/

package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.ifodeposit.define.WEB3IfoDepositProfitLossCalcDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoSummaryContractPerProductContractPriceTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoSummaryContractPerProductContractPriceTest.class);
    public WEB3IfoSummaryContractPerProductContractPriceTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    //先物の場合&&銘柄建単価別先物OP建玉集計.get評価損益計算区分（） == “1：当日建を含まない”） の場合
    public void testcalcFuturesBuyContractProfitLossC001()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossC001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.EXCEPT_TODAY_CONTRACT);
        ///時価
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //先物買建数量
        l_ifoSummaryContractPerProductContractPrice.setBuyQuantity(2000.33);
        boolean l_blnIsBuy = true;
        double l_dblQuantity = 100;
        l_ifoSummaryContractPerProductContractPrice.addTodayQuantity(l_blnIsBuy, l_dblQuantity);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLoss();
        assertEquals(3800659,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }

    //先物の場合&&銘柄建単価別先物OP建玉集計.get評価損益計算区分（） != “1：当日建を含まない”） の場合
    public void testcalcFuturesBuyContractProfitLossC002()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossC002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.DEFAULT );
        ///時価
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //先物買建数量
        l_ifoSummaryContractPerProductContractPrice.setBuyQuantity(2000.33);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLoss();
        assertEquals(4000659,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }
    
    //is先物() == false の場合
    public void testcalcFuturesBuyContractProfitLossC003()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossC003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.DEFAULT );
        ///時価
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //先物買建数量
        l_ifoSummaryContractPerProductContractPrice.setBuyQuantity(2000.33);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLoss();
        assertEquals(0,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }
    
    //先物の場合&&銘柄建単価別先物OP建玉集計.get評価損益計算区分（） == “1：当日建を含まない”） の場合
    public void testcalcFuturesSellContractProfitLossC001()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossC001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.EXCEPT_TODAY_CONTRACT);
        ///時価
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(80);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(100);
        //先物買建数量
        l_ifoSummaryContractPerProductContractPrice.setSellQuantity(2000.33);
        boolean l_blnIsBuy = false;
        double l_dblQuantity = 100;
        l_ifoSummaryContractPerProductContractPrice.addTodayQuantity(l_blnIsBuy, l_dblQuantity);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLoss();
        assertEquals(3800659,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }
    
    //  先物の場合&&銘柄建単価別先物OP建玉集計.get評価損益計算区分（） != “1：当日建を含まない”） の場合
    public void testcalcFuturesSellContractProfitLossC002()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossC002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.DEFAULT);
        ///時価
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(80);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(100);
        //先物買建数量
        l_ifoSummaryContractPerProductContractPrice.setSellQuantity(2000.33);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLoss();
        assertEquals(4000659,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }
    
    //  先物の場合&&銘柄建単価別先物OP建玉集計.get評価損益計算区分（） != “1：当日建を含まない”） の場合
    public void testcalcFuturesSellContractProfitLossC003()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossC003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.DEFAULT);
        ///時価
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(80);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(100);
        //先物買建数量
        l_ifoSummaryContractPerProductContractPrice.setSellQuantity(2000.33);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLoss();
        assertEquals(0,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }
    
    //オプションの場合, 計算した銘柄建単価別オプション買建評価損益を返却する。
    public void testcalcOptionBuyContractProfitLossC001()
    {
        String STR_METHOD_NAME = " testcalcOptionBuyContractProfitLossC001()";
        log.entering(STR_METHOD_NAME);
        
        //オプションの場合
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        ///時価
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //オプション買建数量
        l_ifoSummaryContractPerProductContractPrice.setBuyQuantity(2000.33);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = l_ifoSummaryContractPerProductContractPrice.calcOptionBuyContractProfitLoss();
        assertEquals(4000659,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物の場合,0を返却する
    public void testcalcOptionBuyContractProfitLossC002()
    {
        String STR_METHOD_NAME = " testcalcOptionBuyContractProfitLossC002()";
        log.entering(STR_METHOD_NAME);
        
        //先物の場合
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        ///時価
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //オプション買建数量
        l_ifoSummaryContractPerProductContractPrice.setBuyQuantity(2000.33);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = l_ifoSummaryContractPerProductContractPrice.calcOptionBuyContractProfitLoss();
        assertEquals(0,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }

    //オプションの場合, 計算した銘柄建単価別オプション売建評価損益を返却する。
    public void testcalcOptionSellContractProfitLossC001()
    {
        String STR_METHOD_NAME = " testcalcOptionBuyContractProfitLossC001()";
        log.entering(STR_METHOD_NAME);
        
        //オプションの場合
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        ///時価
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //オプション売建数量
        l_ifoSummaryContractPerProductContractPrice.setSellQuantity(2000.33);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = l_ifoSummaryContractPerProductContractPrice.calcOptionSellContractProfitLoss();
        assertEquals(-4000659,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物の場合,0を返却する
    public void testcalcOptionSellContractProfitLossC002()
    {
        String STR_METHOD_NAME = " testcalcOptionBuyContractProfitLossC002()";
        log.entering(STR_METHOD_NAME);
        
        //オプションの場合
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        ///時価
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //オプション売建数量
        l_ifoSummaryContractPerProductContractPrice.setSellQuantity(2000.33);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = l_ifoSummaryContractPerProductContractPrice.calcOptionSellContractProfitLoss();
        assertEquals(0,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //is先物() == true &&当日清算値 != 0の場
    public void testcalcFuturesBuyContractProfitLossTempC001()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossTempC001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //先物の場合
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        //当日清算値
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(100);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //先物買建数量
        boolean l_blnIsBuy = true;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLossTemp();
        assertEquals(200639,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }


    //is先物() == true &&当日清算値 == 0の場
    public void testcalcFuturesBuyContractProfitLossTempC002()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossTempC002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //先物の場合
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        //当日清算値
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(0);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //先物買建数量
        boolean l_blnIsBuy = true;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLossTemp();
        assertEquals(-802559,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //is先物() == false
    public void testcalcFuturesBuyContractProfitLossTempC003()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossTempC003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //オプションの場合
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
        //当日清算値
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(0);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //先物買建数量
        boolean l_blnIsBuy = true;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLossTemp();
        assertEquals(0,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //is先物() == true &&当日清算値 != 0の場
    public void testcalcFuturesSellContractProfitLossTempC001()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossTempC001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //オプションの場合
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        //当日清算値
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(100);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //先物売建数量
        boolean l_blnIsBuy = false;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLossTemp();
        assertEquals(-200639,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //is先物() == true &&当日清算値 == 0の場
    public void testcalcFuturesSellContractProfitLossTempC002()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossTempC002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //オプションの場合
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        //当日清算値
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(0);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //先物売建数量
        boolean l_blnIsBuy = false;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLossTemp();
        assertEquals(802559,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //is先物() == false
    public void testcalcFuturesSellContractProfitLossTempC003()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossTempC003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //オプションの場合
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        //当日清算値
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(0);
        //建単価
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //先物売建数量
        boolean l_blnIsBuy = false;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //指数乗数
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLossTemp();
        assertEquals(0,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
}
@
