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
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : WEB3IfoSummaryContractPerProductContractPriceTest.java
Author Name         : Daiwa Institute of Research
Revision History    : 2008/08/18 ������ (���u) �V�K�쐬
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
    
    //�敨�̏ꍇ&&�������P���ʐ敨OP���ʏW�v.get�]�����v�v�Z�敪�i�j == �g1�F���������܂܂Ȃ��h�j �̏ꍇ
    public void testcalcFuturesBuyContractProfitLossC001()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossC001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.EXCEPT_TODAY_CONTRACT);
        ///����
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�敨��������
        l_ifoSummaryContractPerProductContractPrice.setBuyQuantity(2000.33);
        boolean l_blnIsBuy = true;
        double l_dblQuantity = 100;
        l_ifoSummaryContractPerProductContractPrice.addTodayQuantity(l_blnIsBuy, l_dblQuantity);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLoss();
        assertEquals(3800659,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }

    //�敨�̏ꍇ&&�������P���ʐ敨OP���ʏW�v.get�]�����v�v�Z�敪�i�j != �g1�F���������܂܂Ȃ��h�j �̏ꍇ
    public void testcalcFuturesBuyContractProfitLossC002()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossC002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.DEFAULT );
        ///����
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�敨��������
        l_ifoSummaryContractPerProductContractPrice.setBuyQuantity(2000.33);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLoss();
        assertEquals(4000659,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }
    
    //is�敨() == false �̏ꍇ
    public void testcalcFuturesBuyContractProfitLossC003()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossC003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.DEFAULT );
        ///����
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�敨��������
        l_ifoSummaryContractPerProductContractPrice.setBuyQuantity(2000.33);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLoss();
        assertEquals(0,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }
    
    //�敨�̏ꍇ&&�������P���ʐ敨OP���ʏW�v.get�]�����v�v�Z�敪�i�j == �g1�F���������܂܂Ȃ��h�j �̏ꍇ
    public void testcalcFuturesSellContractProfitLossC001()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossC001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.EXCEPT_TODAY_CONTRACT);
        ///����
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(80);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(100);
        //�敨��������
        l_ifoSummaryContractPerProductContractPrice.setSellQuantity(2000.33);
        boolean l_blnIsBuy = false;
        double l_dblQuantity = 100;
        l_ifoSummaryContractPerProductContractPrice.addTodayQuantity(l_blnIsBuy, l_dblQuantity);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLoss();
        assertEquals(3800659,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }
    
    //  �敨�̏ꍇ&&�������P���ʐ敨OP���ʏW�v.get�]�����v�v�Z�敪�i�j != �g1�F���������܂܂Ȃ��h�j �̏ꍇ
    public void testcalcFuturesSellContractProfitLossC002()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossC002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.DEFAULT);
        ///����
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(80);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(100);
        //�敨��������
        l_ifoSummaryContractPerProductContractPrice.setSellQuantity(2000.33);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLoss();
        assertEquals(4000659,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }
    
    //  �敨�̏ꍇ&&�������P���ʐ敨OP���ʏW�v.get�]�����v�v�Z�敪�i�j != �g1�F���������܂܂Ȃ��h�j �̏ꍇ
    public void testcalcFuturesSellContractProfitLossC003()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossC003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoSummaryContractPerProductContractPrice.setProfitLossCalcType(
            WEB3IfoDepositProfitLossCalcDef.DEFAULT);
        ///����
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(80);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(100);
        //�敨��������
        l_ifoSummaryContractPerProductContractPrice.setSellQuantity(2000.33);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = 
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLoss();
        assertEquals(0,l_dbReturn,0);

        log.exiting(STR_METHOD_NAME);
    }
    
    //�I�v�V�����̏ꍇ, �v�Z�����������P���ʃI�v�V���������]�����v��ԋp����B
    public void testcalcOptionBuyContractProfitLossC001()
    {
        String STR_METHOD_NAME = " testcalcOptionBuyContractProfitLossC001()";
        log.entering(STR_METHOD_NAME);
        
        //�I�v�V�����̏ꍇ
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        ///����
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�I�v�V������������
        l_ifoSummaryContractPerProductContractPrice.setBuyQuantity(2000.33);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = l_ifoSummaryContractPerProductContractPrice.calcOptionBuyContractProfitLoss();
        assertEquals(4000659,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //�敨�̏ꍇ,0��ԋp����
    public void testcalcOptionBuyContractProfitLossC002()
    {
        String STR_METHOD_NAME = " testcalcOptionBuyContractProfitLossC002()";
        log.entering(STR_METHOD_NAME);
        
        //�敨�̏ꍇ
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        ///����
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�I�v�V������������
        l_ifoSummaryContractPerProductContractPrice.setBuyQuantity(2000.33);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = l_ifoSummaryContractPerProductContractPrice.calcOptionBuyContractProfitLoss();
        assertEquals(0,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }

    //�I�v�V�����̏ꍇ, �v�Z�����������P���ʃI�v�V���������]�����v��ԋp����B
    public void testcalcOptionSellContractProfitLossC001()
    {
        String STR_METHOD_NAME = " testcalcOptionBuyContractProfitLossC001()";
        log.entering(STR_METHOD_NAME);
        
        //�I�v�V�����̏ꍇ
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        ///����
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�I�v�V������������
        l_ifoSummaryContractPerProductContractPrice.setSellQuantity(2000.33);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = l_ifoSummaryContractPerProductContractPrice.calcOptionSellContractProfitLoss();
        assertEquals(-4000659,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //�敨�̏ꍇ,0��ԋp����
    public void testcalcOptionSellContractProfitLossC002()
    {
        String STR_METHOD_NAME = " testcalcOptionBuyContractProfitLossC002()";
        log.entering(STR_METHOD_NAME);
        
        //�I�v�V�����̏ꍇ
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        ///����
        l_ifoSummaryContractPerProductContractPrice.setCurrentPrice(100);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�I�v�V������������
        l_ifoSummaryContractPerProductContractPrice.setSellQuantity(2000.33);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn = l_ifoSummaryContractPerProductContractPrice.calcOptionSellContractProfitLoss();
        assertEquals(0,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //is�敨() == true &&�������Z�l != 0�̏�
    public void testcalcFuturesBuyContractProfitLossTempC001()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossTempC001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //�敨�̏ꍇ
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        //�������Z�l
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(100);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�敨��������
        boolean l_blnIsBuy = true;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLossTemp();
        assertEquals(200639,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }


    //is�敨() == true &&�������Z�l == 0�̏�
    public void testcalcFuturesBuyContractProfitLossTempC002()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossTempC002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //�敨�̏ꍇ
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        //�������Z�l
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(0);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�敨��������
        boolean l_blnIsBuy = true;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLossTemp();
        assertEquals(-802559,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //is�敨() == false
    public void testcalcFuturesBuyContractProfitLossTempC003()
    {
        String STR_METHOD_NAME = " testcalcFuturesBuyContractProfitLossTempC003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //�I�v�V�����̏ꍇ
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
        //�������Z�l
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(0);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�敨��������
        boolean l_blnIsBuy = true;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesBuyContractProfitLossTemp();
        assertEquals(0,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //is�敨() == true &&�������Z�l != 0�̏�
    public void testcalcFuturesSellContractProfitLossTempC001()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossTempC001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //�I�v�V�����̏ꍇ
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        //�������Z�l
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(100);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�敨��������
        boolean l_blnIsBuy = false;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLossTemp();
        assertEquals(-200639,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //is�敨() == true &&�������Z�l == 0�̏�
    public void testcalcFuturesSellContractProfitLossTempC002()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossTempC002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //�I�v�V�����̏ꍇ
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        //�������Z�l
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(0);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�敨��������
        boolean l_blnIsBuy = false;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLossTemp();
        assertEquals(802559,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //is�敨() == false
    public void testcalcFuturesSellContractProfitLossTempC003()
    {
        String STR_METHOD_NAME = " testcalcFuturesSellContractProfitLossTempC003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContractPerProductContractPrice l_ifoSummaryContractPerProductContractPrice =
            new WEB3IfoSummaryContractPerProductContractPrice();
        //�I�v�V�����̏ꍇ
        l_ifoSummaryContractPerProductContractPrice.setIfoDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        //�������Z�l
        l_ifoSummaryContractPerProductContractPrice.setCurrentBizDateLiquidationPrice(0);
        //���P��
        l_ifoSummaryContractPerProductContractPrice.setContractPrice(80);
        //�敨��������
        boolean l_blnIsBuy = false;
        double l_dblQuantity = 100.32;
        l_ifoSummaryContractPerProductContractPrice.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        //�w���搔
        l_ifoSummaryContractPerProductContractPrice.setUnitSize(100);
        double l_dbReturn =
            l_ifoSummaryContractPerProductContractPrice.calcFuturesSellContractProfitLossTemp();
        assertEquals(0,l_dbReturn,0);
        log.exiting(STR_METHOD_NAME);
    }
}
@
