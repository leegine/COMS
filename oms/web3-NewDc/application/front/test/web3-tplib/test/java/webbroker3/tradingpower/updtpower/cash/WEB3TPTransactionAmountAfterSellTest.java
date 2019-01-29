head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTransactionAmountAfterSellTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3TPTransactionAmountAfterSellTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2010/01/15 武波 (中訊) 新規作成
*/
package webbroker3.tradingpower.updtpower.cash;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSellOrderPriceDivDef;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPTransactionAmountAfterSellTest extends TestBaseForMock
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPTransactionAmountAfterSellTest.class);
    public WEB3TPTransactionAmountAfterSellTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void test_createWEB3TPTransactionReflector_case0001()
    {
        final String STR_METHOD_NAME = "test_createWEB3TPTransactionReflector_case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitRow.setOrderType(OrderTypeEnum.FEQ_SELL);
            l_feqOrderUnitRow.setProductId(1);
            WEB3TPTransactionAmountAfterSell l_transactionAmount = new WEB3TPTransactionAmountAfterSellInner();
            l_transactionAmount.createWEB3TPTransactionReflector(l_feqOrderUnitRow);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_createWEB3TPTransactionReflector_case0002()
    {
        final String STR_METHOD_NAME = "test_createWEB3TPTransactionReflector_case0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitRow.setOrderType(OrderTypeEnum.FEQ_SELL);
            l_feqOrderUnitRow.setProductId(1);
            l_feqOrderUnitRow.setOrderUnitId(-1);
            WEB3TPTransactionAmountAfterSell l_transactionAmount = new WEB3TPTransactionAmountAfterSellInner1();
            l_transactionAmount.createWEB3TPTransactionReflector(l_feqOrderUnitRow);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_createWEB3TPTransactionReflector_case0003()
    {
        final String STR_METHOD_NAME = "test_createWEB3TPTransactionReflector_case0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitRow.setOrderType(OrderTypeEnum.FEQ_SELL);
            l_feqOrderUnitRow.setProductId(2);
            WEB3TPTransactionAmountAfterSell l_transactionAmount = new WEB3TPTransactionAmountAfterSellInner();
            l_transactionAmount.createWEB3TPTransactionReflector(l_feqOrderUnitRow);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_createWEB3TPTransactionReflector_case0004()
    {
        final String STR_METHOD_NAME = "test_createWEB3TPTransactionReflector_case0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitRow.setOrderType(OrderTypeEnum.FEQ_SELL);
            l_feqOrderUnitRow.setProductId(2);
            l_feqOrderUnitRow.setConfirmedQuantity(null);
            WEB3TPTransactionAmountAfterSell l_transactionAmount = new WEB3TPTransactionAmountAfterSellInner1();
            l_transactionAmount.createWEB3TPTransactionReflector(l_feqOrderUnitRow);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_createWEB3TPTransactionReflector_case0005()
    {
        final String STR_METHOD_NAME = "test_createWEB3TPTransactionReflector_case0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitRow.setOrderType(OrderTypeEnum.FEQ_SELL);
            l_feqOrderUnitRow.setProductId(2);
            l_feqOrderUnitRow.setConfirmedQuantity(1);
            WEB3TPTransactionAmountAfterSell l_transactionAmount = new WEB3TPTransactionAmountAfterSellInner1();
            l_transactionAmount.createWEB3TPTransactionReflector(l_feqOrderUnitRow);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_createWEB3TPTransactionReflector_case0006()
    {
        final String STR_METHOD_NAME = "test_createWEB3TPTransactionReflector_case0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitRow.setOrderType(OrderTypeEnum.FEQ_BUY);
            l_feqOrderUnitRow.setProductId(2);
            l_feqOrderUnitRow.setConfirmedQuantity(null);
            WEB3TPTransactionAmountAfterSell l_transactionAmount = new WEB3TPTransactionAmountAfterSellInner1();
            l_transactionAmount.createWEB3TPTransactionReflector(l_feqOrderUnitRow);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_createWEB3TPTransactionReflector_case0007()
    {
        final String STR_METHOD_NAME = "test_createWEB3TPTransactionReflector_case0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitRow.setOrderType(OrderTypeEnum.FEQ_BUY);
            l_feqOrderUnitRow.setProductId(2);
            l_feqOrderUnitRow.setQuantity(0);
            l_feqOrderUnitRow.setConfirmedQuantity(null);
            WEB3TPTransactionAmountAfterSell l_transactionAmount = new WEB3TPTransactionAmountAfterSellInner1();
            l_transactionAmount.createWEB3TPTransactionReflector(l_feqOrderUnitRow);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    class WEB3TPTransactionAmountAfterSellInner extends WEB3TPTransactionAmountAfterSell
    {
        public String getInstBranCalcCondition(String l_strName)
        {
            return WEB3TPSellOrderPriceDivDef.QUOTE_PRICE;
        }

        public long getOrderProductId()
        {
            return 1;
        }

        public WEB3TPCalcCondition getCalcCondition()
        {
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            return l_calcCondition;
        }
    }


    class WEB3TPTransactionAmountAfterSellInner1 extends WEB3TPTransactionAmountAfterSell
    {
        public String getInstBranCalcCondition(String l_strName)
        {
            return WEB3TPSellOrderPriceDivDef.DEFAULT;
        }

        public long getOrderProductId()
        {
            return 1;
        }

        public WEB3TPCalcCondition getCalcCondition()
        {
            return new WEB3TPCalcCondition();
        }
    }
}
@
