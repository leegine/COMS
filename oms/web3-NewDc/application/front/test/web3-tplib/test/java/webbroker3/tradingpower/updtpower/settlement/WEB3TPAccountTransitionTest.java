head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPAccountTransitionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客勘定推移(WEB3TPAccountTransition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/22 孟亞南（中訊）仕様変更モデルNo.214　@計算式書014
*/
package webbroker3.tradingpower.updtpower.settlement;

import java.util.ArrayList;
import java.util.List;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPAccountTransitionTest extends TestBaseForMock
{

    /*
     * ログ出力ユーティリティー
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPAccountTransitionTest.class);
    
    public WEB3TPAccountTransitionTest(String name)
    {
        super(name);
    }

    /**
     * calc日計り拘束金
     * 日計り拘束金 < 0の時
     * 日計り拘束金 = 0
     */
    public void test_calcDayTradeRestraint_0001()
    {
        final String STR_METHOD_NAME = "test_calcDayTradeRestraint_0001()";
        log.entering(STR_METHOD_NAME);
        
        //銘柄ごと取引情報
        WEB3TPSettlementReflector l_bufSettlementReflector = new WEB3TPSettlementReflector();
        
        //買付数量 > 0.0
        l_bufSettlementReflector.setBuyQuantity(1.0);
        //売付数量 > 0.0
        l_bufSettlementReflector.setSellQuantity(2.0);
        //未約定売付数量 > 0.0
        l_bufSettlementReflector.setUnexecutedSellQuantity(3.0);
        //指定日前日保有数量 < 0.0
        l_bufSettlementReflector.setExistQuantity(-2.0);
        
        List l_lis = new ArrayList();
        l_lis.add(l_bufSettlementReflector);
        WEB3TPAccountTransition l_tp = new WEB3TPAccountTransition();
        l_tp.setTotalPaymentAmount(19831022.0);
        l_tp.setLisSettlementReflectors(l_lis);
        
        assertEquals("0.0", "" + l_tp.calcDayTradeRestraint());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * calc日計り拘束金
     * 日計り拘束金 > 0の時
     * 日計り拘束金を返却
     */
    public void test_calcDayTradeRestraint_0002()
    {
        final String STR_METHOD_NAME = "test_calcDayTradeRestraint_0002()";
        log.entering(STR_METHOD_NAME);
        
        //銘柄ごと取引情報
        WEB3TPSettlementReflector l_bufSettlementReflector = new WEB3TPSettlementReflector();
        
        //買付数量 > 0.0
        l_bufSettlementReflector.setBuyQuantity(1.0);
        //売付数量 > 0.0
        l_bufSettlementReflector.setSellQuantity(2.0);
        //未約定売付数量 > 0.0
        l_bufSettlementReflector.setUnexecutedSellQuantity(3.0);
        //指定日前日保有数量 < 0.0
        l_bufSettlementReflector.setExistQuantity(-2.0);
        
        List l_lis = new ArrayList();
        l_lis.add(l_bufSettlementReflector);
        WEB3TPAccountTransition l_tp = new WEB3TPAccountTransition();
        l_tp.setTotalPaymentAmount(-1983.0);
        l_tp.setLisSettlementReflectors(l_lis);
        
        assertEquals("1983.0", "" + l_tp.calcDayTradeRestraint());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * calc日計り拘束金～0補正無し～
     * 要素数 = 0
     * return 0.0
     */
    public void test_calcDayTradeRestraintForCheck_0001()
    {
        final String STR_METHOD_NAME = "test_calcDayTradeRestraintForCheck_0001()";
        log.entering(STR_METHOD_NAME);
        
        //銘柄ごと取引情報
        WEB3TPSettlementReflector l_bufSettlementReflector = new WEB3TPSettlementReflector();
        
        //買付数量 > 0.0
        l_bufSettlementReflector.setBuyQuantity(1.0);
        //売付数量 > 0.0
        l_bufSettlementReflector.setSellQuantity(2.0);
        //未約定売付数量 > 0.0
        l_bufSettlementReflector.setUnexecutedSellQuantity(3.0);
        //指定日前日保有数量 < 0.0
        l_bufSettlementReflector.setExistQuantity(-2.0);
        
        List l_lis = new ArrayList();
        l_lis.add(l_bufSettlementReflector);
        WEB3TPAccountTransition l_tp = new WEB3TPAccountTransition();
        l_tp.setTotalPaymentAmount(-19831022.0);
//        l_tp.setLisSettlementReflectors(l_lis);
        
        assertEquals("0.0", "" + l_tp.calcDayTradeRestraintForCheck());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * calc日計り拘束金～0補正無し～
     * 要素数 != 0
     * return 
     */
    public void test_calcDayTradeRestraintForCheck_0002()
    {
        final String STR_METHOD_NAME = "test_calcDayTradeRestraintForCheck_0002()";
        log.entering(STR_METHOD_NAME);
        
        //銘柄ごと取引情報
        WEB3TPSettlementReflector l_bufSettlementReflector = new WEB3TPSettlementReflector();
        
        //買付数量 > 0.0
        l_bufSettlementReflector.setBuyQuantity(1.0);
        //売付数量 > 0.0
        l_bufSettlementReflector.setSellQuantity(2.0);
        //未約定売付数量 > 0.0
        l_bufSettlementReflector.setUnexecutedSellQuantity(3.0);
        //指定日前日保有数量 < 0.0
        l_bufSettlementReflector.setExistQuantity(-2.0);
        
        List l_lis = new ArrayList();
        l_lis.add(l_bufSettlementReflector);
        WEB3TPAccountTransition l_tp = new WEB3TPAccountTransition();
        l_tp.setTotalPaymentAmount(1022.0);
        l_tp.setLisSettlementReflectors(l_lis);
        
        assertEquals("-1022.0", "" + l_tp.calcDayTradeRestraintForCheck());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * calc日計り拘束金～0補正無し～
     * 要素数 != 0
     * return 
     */
    public void test_calcDayTradeRestraintForCheck_0003()
    {
        final String STR_METHOD_NAME = "test_calcDayTradeRestraintForCheck_0003()";
        log.entering(STR_METHOD_NAME);
        
        //銘柄ごと取引情報
        WEB3TPSettlementReflector l_bufSettlementReflector = new WEB3TPSettlementReflector();
        
        //買付数量 > 0.0
        l_bufSettlementReflector.setBuyQuantity(1.0);
        //売付数量 > 0.0
        l_bufSettlementReflector.setSellQuantity(2.0);
        //未約定売付数量 > 0.0
        l_bufSettlementReflector.setUnexecutedSellQuantity(3.0);
        //指定日前日保有数量 < 0.0
        l_bufSettlementReflector.setExistQuantity(-2.0);
        
        //銘柄ごと取引情報
        WEB3TPSettlementReflector l_bufSettlementReflector1 = new WEB3TPSettlementReflector();
        
        //買付数量 > 0.0
        l_bufSettlementReflector1.setBuyQuantity(1.0);
        //売付数量 > 0.0
        l_bufSettlementReflector1.setSellQuantity(2.0);
        //未約定売付数量 > 0.0
        l_bufSettlementReflector1.setUnexecutedSellQuantity(3.0);
        //指定日前日保有数量 < 0.0
        l_bufSettlementReflector1.setExistQuantity(-2.0);
        
        List l_lis = new ArrayList();
        l_lis.add(l_bufSettlementReflector);
        l_lis.add(l_bufSettlementReflector1);
        WEB3TPAccountTransition l_tp = new WEB3TPAccountTransition();
        l_tp.setTotalPaymentAmount(1022.0);
        l_tp.setLisSettlementReflectors(l_lis);
        
        assertEquals("-1022.0", "" + l_tp.calcDayTradeRestraintForCheck());
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
