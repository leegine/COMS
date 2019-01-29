head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoSummaryContractTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : WEB3IfoSummaryContractTest.java
Author Name         : Daiwa Institute of Research
Revision History    : 2008/08/18 張少傑 (中訊) 新規作成
*/

package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoSummaryContractTest extends TestBaseForMock
{
    WEB3LogUtility
    log = WEB3LogUtility.getInstance(WEB3IfoNewOrderSpecTest.class);
    public WEB3IfoSummaryContractTest(String arg0)
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

    //買建(引数.is買建 == true)の場合
    public void testAddQuantityC001()
    {
        String STR_METHOD_NAME  = "testAddQuantityC001()";
        log.entering(STR_METHOD_NAME);
        boolean l_blnIsBuy = true;
        double l_dblQuantity = 225.0D;
        WEB3IfoSummaryContract l_ifoSummaryContract = new WEB3IfoSummaryContract();
        l_ifoSummaryContract.setBuyQuantity(1000.0D);

        l_ifoSummaryContract.addQuantity(l_blnIsBuy, l_dblQuantity);
        assertEquals(1225.0, l_ifoSummaryContract.getBuyQuantity(), 0.0);
        log.exiting(STR_METHOD_NAME);
    }

    //売建(引数.is買建 == false)の場合
    public void testAddQuantityC002()
    {
        String STR_METHOD_NAME  = "testAddQuantityC002()";
        log.entering(STR_METHOD_NAME);
        boolean l_blnIsBuy = false;
        double l_dblQuantity = 225.0D;
        WEB3IfoSummaryContract l_ifoSummaryContract = new WEB3IfoSummaryContract();
        l_ifoSummaryContract.setSellQuantity(1000.0D);

        l_ifoSummaryContract.addQuantity(l_blnIsBuy, l_dblQuantity);
        assertEquals(1225.0, l_ifoSummaryContract.getSellQuantity(), 0.0);
        log.exiting(STR_METHOD_NAME);
    }

    //買建(引数.is買建 == true)の場合、当日買建数量 = this.get当日買建数量( ) + 引数.数量
    public void testAddTodayQuantityC001()
    {
        String STR_METHOD_NAME  = "testAddTodayQuantityC001()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        boolean l_blnIsBuy = true;
        double l_dblQuantity = 250.0D;
        WEB3IfoSummaryContract l_ifoSummaryContract = new WEB3IfoSummaryContract();

        l_ifoSummaryContract.addTodayQuantity(l_blnIsBuy, l_dblQuantity);
        assertEquals(250.0, l_ifoSummaryContract.getTodayBuyQuantity(), 0.0);
        log.exiting(STR_METHOD_NAME);
    }
    //買建(引数.is買建 == false)の場合、当日売建数量 = this.get当日売建数量( ) + 引数.数量
    public void testAddTodayQuantityC002()
    {
        String STR_METHOD_NAME  = "testAddTodayQuantityC002()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        boolean l_blnIsBuy = false;
        double l_dblQuantity = 235.0D;
        WEB3IfoSummaryContract l_ifoSummaryContract = new WEB3IfoSummaryContract();

        l_ifoSummaryContract.addTodayQuantity(l_blnIsBuy, l_dblQuantity);
        assertEquals(235.0, l_ifoSummaryContract.getTodaySellQuantity(), 0.0);
        log.exiting(STR_METHOD_NAME);
    }
    
    //物オプション商品区分==”先物”の場合、trueを返却する
    public void testIsFuturesC001()
    {
        String STR_METHOD_NAME  = "testIsFuturesC001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContract l_ifoSummaryContract = new WEB3IfoSummaryContract();
        l_ifoSummaryContract.setIfoDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        boolean isFutures = l_ifoSummaryContract.isFutures();
        assertEquals(true,isFutures);
        log.exiting(STR_METHOD_NAME);
    }
    //物オプション商品区分!=”先物”の場合、trueを返却する
    public void testIsFuturesC002()
    {
        String STR_METHOD_NAME  = "testIsFuturesC002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoSummaryContract l_ifoSummaryContract = new WEB3IfoSummaryContract();
        l_ifoSummaryContract.setIfoDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
        boolean isFutures = l_ifoSummaryContract.isFutures();
        assertEquals(false,isFutures);
        log.exiting(STR_METHOD_NAME);
    }
    //買建(引数.is買建 == true)の場合 
    public void testAddQuantityTempC001()
    {
        String STR_METHOD_NAME  = "testAddQuantityTempC001()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsBuy = true;
        double l_dblQuantity = 125;
        WEB3IfoSummaryContract l_ifoSummaryContract = new WEB3IfoSummaryContract();
        l_ifoSummaryContract.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        assertEquals(125,l_ifoSummaryContract.getBuyQuantityTemp(),0.0);
        log.exiting(STR_METHOD_NAME);
    }
    //買建(引数.is買建 == false)の場合 
    public void testAddQuantityTempC002()
    {
        String STR_METHOD_NAME  = "testAddQuantityTempC002()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsBuy = false;
        double l_dblQuantity = 125;
        WEB3IfoSummaryContract l_ifoSummaryContract = new WEB3IfoSummaryContract();
        l_ifoSummaryContract.addQuantityTemp(l_blnIsBuy, l_dblQuantity);
        assertEquals(125,l_ifoSummaryContract.getSellQuantityTemp(),0.0);
        log.exiting(STR_METHOD_NAME);
    }
}
@
