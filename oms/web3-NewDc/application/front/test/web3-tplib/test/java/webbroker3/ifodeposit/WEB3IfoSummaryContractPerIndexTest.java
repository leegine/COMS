head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.29.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoSummaryContractPerIndexTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit;

import java.lang.reflect.Field;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum.IntValues;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author 祁永星
 * @@version 1.0
 */

public class WEB3IfoSummaryContractPerIndexTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoSummaryContractPerIndexTest.class);
    
    WEB3IfoSummaryContractPerIndex l_perindex = null;

    public WEB3IfoSummaryContractPerIndexTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_perindex = new WEB3IfoSummaryContractPerIndex();
    }

    protected void tearDown() throws Exception
    {
        l_perindex = null;
        super.tearDown();
    }
    
    //先物買建の場合
    //  (引数.先物オプション商品区分 == ”先物” && 引数.is買建 == true)
    //  先物買建数量 = 先物買建数量( ) + 引数.数量
    public void testAddContractQuantityCase1()
    {
        final String STR_METHOD_NAME = "TestAddContractQuantityCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setFuturesBuyContractQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.FUTURES, "FUTURES");
            boolean l_blnIsBuy = true;
            double l_dblQuantity = 10;
            l_perindex.addContractQuantity(l_ifoDerivativeType, l_blnIsBuy, l_dblQuantity);
            assertEquals("" + l_perindex.getFuturesBuyContractQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物売建の場合
    //(引数.先物オプション商品区分 == ”先物” && 引数.is買建 == false)
    //先物売建数量 = 先物売建数量( ) + 引数.数量
    public void testAddContractQuantityCase2()
    {
        final String STR_METHOD_NAME = "TestAddContractQuantityCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setFuturesSellContractQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.FUTURES, "FUTURES");
            boolean l_blnIsBuy = false;
            double l_dblQuantity = 10;
            l_perindex.addContractQuantity(l_ifoDerivativeType, l_blnIsBuy, l_dblQuantity);
            assertEquals("" + l_perindex.getFuturesSellContractQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //オプションプット売建の場合
    //先物売建数量 = t先物売建数量( ) + 引数.数量
    public void testAddContractQuantityCase3()
    {
        final String STR_METHOD_NAME = "TestAddContractQuantityCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setOptionPutSellContractQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS, "PUT_OPTIONS");
            boolean l_blnIsBuy = false;
            double l_dblQuantity = 10;
            l_perindex.addContractQuantity(l_ifoDerivativeType, l_blnIsBuy, l_dblQuantity);
            assertEquals("" + l_perindex.getOptionPutSellContractQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //オプションプット売建の場合
    //オプションコール売建数量 = オプションコール売建数量( ) + 引数.数量
    public void testAddContractQuantityCase4()
    {
        final String STR_METHOD_NAME = "TestAddContractQuantityCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setOptionCallSellContractQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS, "CALL_OPTIONS");
            boolean l_blnIsBuy = false;
            double l_dblQuantity = 10;
            l_perindex.addContractQuantity(l_ifoDerivativeType, l_blnIsBuy, l_dblQuantity);
            assertEquals("" + l_perindex.getOptionCallSellContractQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //以外の時
    //何も行わない
    public void testAddContractQuantityCase5()
    {
        final String STR_METHOD_NAME = "TestAddContractQuantityCase5()";
        log.entering(STR_METHOD_NAME);
        try
        {
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IntValues.OTHER, "OTHER");
            boolean l_blnIsBuy = false;
            double l_dblQuantity = 10;
            l_perindex.addContractQuantity(l_ifoDerivativeType, l_blnIsBuy, l_dblQuantity);
            assertEquals("" + l_perindex.getFuturesBuyContractQuantity(),"0.0");
            assertEquals("" + l_perindex.getFuturesSellContractQuantity(),"0.0");
            assertEquals("" + l_perindex.getOptionPutSellContractQuantity(),"0.0");
            assertEquals("" + l_perindex.getOptionCallSellContractQuantity(),"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(add建数量＜証拠金不足仮確定＞)
    //先物買建の場合
    public void testAddContractQuantityTempCase1()
    {
        final String STR_METHOD_NAME = "testAddContractQuantityTempCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.FUTURES, "FUTURES");
            boolean l_blnIsBuy = true;
            double l_dblQuantity = 10;
            l_perindex.addContractQuantityTemp(l_ifoDerivativeType, l_blnIsBuy, l_dblQuantity);
            assertEquals("" + l_perindex.getFuturesBuyContractQuantityTemp(),"10.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    // 先物売建の場合
    public void testAddContractQuantityTempCase2()
    {
        final String STR_METHOD_NAME = "testAddContractQuantityTempCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.FUTURES, "FUTURES");
            boolean l_blnIsBuy = false;
            double l_dblQuantity = 20;
            l_perindex.addContractQuantityTemp(l_ifoDerivativeType, l_blnIsBuy, l_dblQuantity);
            assertEquals("" + l_perindex.getFuturesSellContractQuantityTemp(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    // オプションプット売建の場合
    public void testAddContractQuantityTempCase3()
    {
        final String STR_METHOD_NAME = "testAddContractQuantityTempCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS, "PUT_OPTIONS");
            boolean l_blnIsBuy = false;
            double l_dblQuantity = 30;
            l_perindex.addContractQuantityTemp(l_ifoDerivativeType, l_blnIsBuy, l_dblQuantity);
            assertEquals("" + l_perindex.getOptionPutSellContractQuantityTemp(),"30.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    // オプションプット売建の場合
    public void testAddContractQuantityTempCase4()
    {
        final String STR_METHOD_NAME = "testAddContractQuantityTempCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS, "CALL_OPTIONS");
            boolean l_blnIsBuy = false;
            double l_dblQuantity = 40;
            l_perindex.addContractQuantityTemp(l_ifoDerivativeType, l_blnIsBuy, l_dblQuantity);
            assertEquals("" + l_perindex.getOptionCallSellContractQuantityTemp(),"40.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(add注文数量)
    //先物買建[T+0]の場合
    public void testAddOrderQuantityCase1()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantityCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.FUTURES, "FUTURES");
            boolean l_blnIsBuy = true;
            Date l_datOrderDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("20040710", "yyyyMMdd");
            double l_dblQuantity =10;
            l_perindex.addOrderQuantity(l_ifoDerivativeType, l_blnIsBuy, l_datOrderDate,
                    l_datCurrentBizDate, l_datNextBizDate, l_dblQuantity);
            assertEquals("" + l_perindex.getCurrentBizDateFuturesBuyOrderQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    // 先物買建[T+1]の場合
    public void testAddOrderQuantityCase2()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantityCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.FUTURES, "FUTURES");
            boolean l_blnIsBuy = true;
            Date l_datOrderDate = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("200407008", "yyyyMMdd");
            double l_dblQuantity =10;
            l_perindex.addOrderQuantity(l_ifoDerivativeType, l_blnIsBuy, l_datOrderDate,
                    l_datCurrentBizDate, l_datNextBizDate, l_dblQuantity);
            assertEquals("" + l_perindex.getNextBizDateFuturesBuyOrderQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物売建[T+0]の場合
    public void testAddOrderQuantityCase3()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantityCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.FUTURES, "FUTURES");
            boolean l_blnIsBuy = false;
            Date l_datOrderDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("200407008", "yyyyMMdd");
            double l_dblQuantity =10;
            l_perindex.addOrderQuantity(l_ifoDerivativeType, l_blnIsBuy, l_datOrderDate,
                    l_datCurrentBizDate, l_datNextBizDate, l_dblQuantity);
            assertEquals("" + l_perindex.getCurrentBizDateFuturesSellOrderQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物売建[T+1]の場合
    public void testAddOrderQuantityCase4()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantityCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.FUTURES, "FUTURES");
            boolean l_blnIsBuy = false;
            Date l_datOrderDate = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("200407008", "yyyyMMdd");
            double l_dblQuantity =10;
            l_perindex.addOrderQuantity(l_ifoDerivativeType, l_blnIsBuy, l_datOrderDate,
                    l_datCurrentBizDate, l_datNextBizDate, l_dblQuantity);
            assertEquals("" + l_perindex.getNextBizDateFuturesSellOrderQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //オプションプット売建[T+0]の場合
    public void testAddOrderQuantityCase5()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantityCase5()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS, "PUT_OPTIONS");
            boolean l_blnIsBuy = false;
            Date l_datOrderDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("200407008", "yyyyMMdd");
            double l_dblQuantity =10;
            l_perindex.addOrderQuantity(l_ifoDerivativeType, l_blnIsBuy, l_datOrderDate,
                    l_datCurrentBizDate, l_datNextBizDate, l_dblQuantity);
            assertEquals("" + l_perindex.getCurrentBizDateOptionPutSellOrderQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //オプションプット売建[T+1]の場合
    public void testAddOrderQuantityCase6()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantityCase6()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS, "PUT_OPTIONS");
            boolean l_blnIsBuy = false;
            Date l_datOrderDate = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("200407008", "yyyyMMdd");
            double l_dblQuantity =10;
            l_perindex.addOrderQuantity(l_ifoDerivativeType, l_blnIsBuy, l_datOrderDate,
                    l_datCurrentBizDate, l_datNextBizDate, l_dblQuantity);
            assertEquals("" + l_perindex.getNextBizDateOptionPutSellOrderQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //オプションコール売建[T+0]の場合
    public void testAddOrderQuantityCase7()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantityCase7()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS, "CALL_OPTIONS");
            boolean l_blnIsBuy = false;
            Date l_datOrderDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("200407008", "yyyyMMdd");
            double l_dblQuantity =10;
            l_perindex.addOrderQuantity(l_ifoDerivativeType, l_blnIsBuy, l_datOrderDate,
                    l_datCurrentBizDate, l_datNextBizDate, l_dblQuantity);
            assertEquals("" + l_perindex.getCurrentBizDateOptionCallSellOrderQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //オプションコール売建[T+1]の場合
    public void testAddOrderQuantityCase8()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantityCase8()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS, "CALL_OPTIONS");
            boolean l_blnIsBuy = false;
            Date l_datOrderDate = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("200407008", "yyyyMMdd");
            double l_dblQuantity =10;
            l_perindex.addOrderQuantity(l_ifoDerivativeType, l_blnIsBuy, l_datOrderDate,
                    l_datCurrentBizDate, l_datNextBizDate, l_dblQuantity);
            assertEquals("" + l_perindex.getNextBizDateOptionCallSellOrderQuantity(),"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //以外の時
    //何もしない
    public void testAddOrderQuantityCase9()
    {
        final String STR_METHOD_NAME = "testAddOrderQuantityCase9()";
        log.entering(STR_METHOD_NAME);
        try
        {
            IfoDerivativeTypeEnum l_ifoDerivativeType =
                new IfoDerivativeTypeEnum(IntValues.OTHER, "OTHER");
            boolean l_blnIsBuy = false;
            Date l_datOrderDate = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            Date l_datCurrentBizDate = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            Date l_datNextBizDate = WEB3DateUtility.getDate("200407008", "yyyyMMdd");
            double l_dblQuantity =10;
            l_perindex.addOrderQuantity(l_ifoDerivativeType, l_blnIsBuy, l_datOrderDate,
                    l_datCurrentBizDate, l_datNextBizDate, l_dblQuantity);
            assertEquals("" + l_perindex.getNextBizDateOptionCallSellOrderQuantity(),"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション建玉)
    // 引数.指定日が0の時
    public void testCalcBuyContractQtyCase1()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
//            
            double l_dblBuyPosition = 0;
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            int l_intReservedDate = 0;
            l_dblBuyPosition = l_perindex.calcBuyContractQty(l_intReservedDate);

            
            assertEquals("" + l_dblBuyPosition,"40.0");
        }
    catch (Exception l_ex)
    {
        log.error(""+ l_ex);
        log.exiting(STR_METHOD_NAME);
        fail();
    }
    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
    log.exiting(STR_METHOD_NAME);
    }
    
    //引数.指定日が1または2の時 (1)
    public void testCalcBuyContractQtyCase2()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblBuyPosition = 0;
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            int l_intReservedDate = 1;
            l_dblBuyPosition = l_perindex.calcBuyContractQty(l_intReservedDate);

            
            assertEquals("" + l_dblBuyPosition,"60.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //引数.指定日が1または2の時 (2)
    public void testCalcBuyContractQtyCase3()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblBuyPosition = 0;
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            int l_intReservedDate = 2;
            l_dblBuyPosition = l_perindex.calcBuyContractQty(l_intReservedDate);

            
            assertEquals("" + l_dblBuyPosition,"60.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //それ以外 (3)
    public void testCalcBuyContractQtyCase4()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblBuyPosition = 0;
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            int l_intReservedDate = 3;
            l_dblBuyPosition = l_perindex.calcBuyContractQty(l_intReservedDate);

            
            assertEquals("" + l_dblBuyPosition,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別売ポジション建玉)
    //引数.指定日が0の時
    public void testCalcSellContractQtyCase1()
    {
        final String STR_METHOD_NAME = "tesCalcSellContractQtyCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblSellPosition = 0;
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            int l_intReservedDate = 0;
            l_dblSellPosition = l_perindex.calcSellContractQty(l_intReservedDate);

            
            assertEquals("" + l_dblSellPosition,"40.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //引数.指定日が1または2の時 (1)
    public void testCalcSellContractQtyCase2()
    {
        final String STR_METHOD_NAME = "tesCalcSellContractQtyCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblSellPosition = 0;
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            int l_intReservedDate = 1;
            l_dblSellPosition = l_perindex.calcSellContractQty(l_intReservedDate);

            
            assertEquals("" + l_dblSellPosition,"60.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //引数.指定日が1または2の時 (2)
    public void testCalcSellContractQtyCase3()
    {
        final String STR_METHOD_NAME = "tesCalcSellContractQtyCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblSellPosition = 0;
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            int l_intReservedDate = 2;
            l_dblSellPosition = l_perindex.calcSellContractQty(l_intReservedDate);

            
            assertEquals("" + l_dblSellPosition,"60.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //引数.指定日が1または2の時以外 (3)
    public void testCalcSellContractQtyCase4()
    {
        final String STR_METHOD_NAME = "tesCalcSellContractQtyCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblSellPosition = 0;
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            int l_intReservedDate = 3;
            l_dblSellPosition = l_perindex.calcSellContractQty(l_intReservedDate);

            
            assertEquals("" + l_dblSellPosition,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別注文中買ポジション建玉)
    //引数.指定日が0の時
    public void testCalcBuyOrderQtyCase1()
    {
        final String STR_METHOD_NAME = "testCalcBuyOrderQtyCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblOrderBuyPosition = 0;
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            int l_intReservedDate = 0;
            l_dblOrderBuyPosition = l_perindex.calcBuyOrderQty(l_intReservedDate);

            
            assertEquals("" + l_dblOrderBuyPosition,"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //引数.指定日が1または2の時 (1)
    public void testCalcBuyOrderQtyCase2()
    {
        final String STR_METHOD_NAME = "testCalcBuyOrderQtyCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblOrderBuyPosition = 0;
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            int l_intReservedDate = 1;
            l_dblOrderBuyPosition = l_perindex.calcBuyOrderQty(l_intReservedDate);

            
            assertEquals("" + l_dblOrderBuyPosition,"40.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //引数.指定日が1または2の時 (2)
    public void testCalcBuyOrderQtyCase3()
    {
        final String STR_METHOD_NAME = "testCalcBuyOrderQtyCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblOrderBuyPosition = 0;
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            int l_intReservedDate = 2;
            l_dblOrderBuyPosition = l_perindex.calcBuyOrderQty(l_intReservedDate);

            
            assertEquals("" + l_dblOrderBuyPosition,"40.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //引数.指定日が1または2の時以外 (3)
    public void testCalcBuyOrderQtyCase4()
    {
        final String STR_METHOD_NAME = "testCalcBuyOrderQtyCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblOrderBuyPosition = 0;
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            int l_intReservedDate = 3;
            l_dblOrderBuyPosition = l_perindex.calcBuyOrderQty(l_intReservedDate);

            
            assertEquals("" + l_dblOrderBuyPosition,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc指数別注文中売ポジション建玉
    //引数.指定日が0の時
    public void testCalcSellOrderQtyCase1()
    {
        final String STR_METHOD_NAME = "testCalcSellOrderQtyCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblOrderSellPosition = 0;
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            int l_intReservedDate = 0;
            l_dblOrderSellPosition = l_perindex.calcSellOrderQty(l_intReservedDate);

            
            assertEquals("" + l_dblOrderSellPosition,"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //引数.指定日が1または2の時 (1)
    public void testCalcSellOrderQtyCase2()
    {
        final String STR_METHOD_NAME = "testCalcSellOrderQtyCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblOrderSellPosition = 0;
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            int l_intReservedDate = 1;
            l_dblOrderSellPosition = l_perindex.calcSellOrderQty(l_intReservedDate);

            
            assertEquals("" + l_dblOrderSellPosition,"40.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //引数.指定日が1または2の時 (2)
    public void testCalcSellOrderQtyCase3()
    {
        final String STR_METHOD_NAME = "testCalcSellOrderQtyCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblOrderSellPosition = 0;
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            int l_intReservedDate = 2;
            l_dblOrderSellPosition = l_perindex.calcSellOrderQty(l_intReservedDate);

            
            assertEquals("" + l_dblOrderSellPosition,"40.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //それ以外 (3)
    public void testCalcSellOrderQtyCase4()
    {
        final String STR_METHOD_NAME = "testCalcSellOrderQtyCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            double l_dblOrderSellPosition = 0;
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            int l_intReservedDate = 3;
            l_dblOrderSellPosition = l_perindex.calcSellOrderQty(l_intReservedDate);

            
            assertEquals("" + l_dblOrderSellPosition,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション金額 )
    //引数.指定日が0の時
    public void testCalcPossibleBuyAmtCase1()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setIfoDepositPerUnit(10);
            int l_intReservedDate = 0;
            double l_dblPossAmt = l_perindex.calcPossibleBuyAmt(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"400.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション金額 )
    //引数.指定日が1の時
    public void testCalcPossibleBuyAmtCase2()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setIfoDepositPerUnit(10);
            int l_intReservedDate = 1;
            double l_dblPossAmt = l_perindex.calcPossibleBuyAmt(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"600.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション金額 )
    //引数.指定日が2の時
    public void testCalcPossibleBuyAmtCase3()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtCase3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setIfoDepositPerUnit(10);
            int l_intReservedDate = 2;
            double l_dblPossAmt = l_perindex.calcPossibleBuyAmt(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"600.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション金額 )
    //引数.指定日が3の時
    public void testCalcPossibleBuyAmtCase4()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtCase4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnit(10);
            int l_intReservedDate = 3;
            double l_dblPossAmt = l_perindex.calcPossibleBuyAmt(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別売ポジション金額)
    //引数.指定日が0の時
    public void testCalcPossibleSellAmtCase1()
    {
        final String STR_METHOD_NAME = "testCalcPossibleSellAmtCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnit(10);
            int l_intReservedDate = 0;
            double l_respons = l_perindex.calcPossibleSellAmt(l_intReservedDate);
            assertEquals("" + l_respons,"400.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別売ポジション金額)
    //引数.指定日が1の時
    public void testCalcPossibleSellAmtCase2()
    {
        final String STR_METHOD_NAME = "testCalcPossibleSellAmtCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnit(10);
            int l_intReservedDate = 1;
            double l_respons = l_perindex.calcPossibleSellAmt(l_intReservedDate);
            assertEquals("" + l_respons,"600.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別売ポジション金額)
    //引数.指定日が2の時
    public void testCalcPossibleSellAmtCase3()
    {
        final String STR_METHOD_NAME = "testCalcPossibleSellAmtCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnit(10);
            int l_intReservedDate = 2;
            double l_respons = l_perindex.calcPossibleSellAmt(l_intReservedDate);
            assertEquals("" + l_respons,"600.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別売ポジション金額)
    //引数.指定日が3の時
    public void testCalcPossibleSellAmtCase4()
    {
        final String STR_METHOD_NAME = "testCalcPossibleSellAmtCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnit(10);
            int l_intReservedDate = 3;
            double l_respons = l_perindex.calcPossibleSellAmt(l_intReservedDate);
            assertEquals("" + l_respons,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション金額レッド)
    //引数.指定日が0の時
    public void testCalcPossibleBuyAmtRedCase1()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtRedCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnitRed(10);
            int l_intReservedDate = 0;
            double l_dblPossAmt = l_perindex.calcPossibleBuyAmtRed(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"400.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション金額レッド)
    //引数.指定日が1の時
    public void testCalcPossibleBuyAmtRedCase2()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtRedCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnitRed(10);
            int l_intReservedDate = 1;
            double l_dblPossAmt = l_perindex.calcPossibleBuyAmtRed(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"600.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション金額レッド)
    //引数.指定日が2の時
    public void testCalcPossibleBuyAmtRedCase3()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtRedCase3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnitRed(10);
            int l_intReservedDate = 2;
            double l_dblPossAmt = l_perindex.calcPossibleBuyAmtRed(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"600.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション金額レッド)
    //引数.指定日が3の時
    public void testCalcPossibleBuyAmtRedCase4()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtRedCase4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesBuyContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setNextBizDateFuturesBuyOrderQuantity(10);
            l_perindex.setOptionPutSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionPutSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionPutSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnitRed(10);
            int l_intReservedDate = 3;
            double l_dblPossAmt = l_perindex.calcPossibleBuyAmtRed(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //(calc指数別売ポジション金額レッド )
    //引数.指定日が0の時
    public void testCalcPossibleSellAmtRedCase1()
    {
        final String STR_METHOD_NAME = "testCalcPossibleSellAmtRedCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnitRed(10);
            int l_intReservedDate = 0;
            double l_dblPossAmt = l_perindex.calcPossibleSellAmtRed(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"400.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別売ポジション金額レッド )
    //引数.指定日が1の時
    public void testCalcPossibleSellAmtRedCase2()
    {
        final String STR_METHOD_NAME = "testCalcPossibleSellAmtRedCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnitRed(10);
            int l_intReservedDate = 1;
            double l_dblPossAmt = l_perindex.calcPossibleSellAmtRed(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"600.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別売ポジション金額レッド )
    //引数.指定日が2の時
    public void testCalcPossibleSellAmtRedCase3()
    {
        final String STR_METHOD_NAME = "testCalcPossibleSellAmtRedCase3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnitRed(10);
            int l_intReservedDate = 2;
            double l_dblPossAmt = l_perindex.calcPossibleSellAmtRed(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"600.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別売ポジション金額レッド )
    //引数.指定日が3の時
    public void testCalcPossibleSellAmtRedCase4()
    {
        final String STR_METHOD_NAME = "testCalcPossibleSellAmtRedCase4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_perindex.setFuturesSellContractQuantity(10);
            l_perindex.setCurrentBizDateFuturesSellOrderQuantity(10);
            l_perindex.setNextBizDateFuturesSellOrderQuantity(10);
            l_perindex.setOptionCallSellContractQuantity(10);
            l_perindex.setCurrentBizDateOptionCallSellOrderQuantity(10);
            l_perindex.setNextBizDateOptionCallSellOrderQuantity(10);
            
            l_perindex.setIfoDepositPerUnitRed(10);
            int l_intReservedDate = 3;
            double l_dblPossAmt = l_perindex.calcPossibleSellAmtRed(l_intReservedDate);
            assertEquals("" + l_dblPossAmt,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    //(calc指数別買ポジション建玉＜証拠金不足仮確定＞)
    //引数.指定日が0の時
    public void testCalcBuyContractQtyTempCase1()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresBuyContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionPutSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            int l_intReservedDate = 0;
            double l_dblBuyPositionTemp = l_perindex.calcBuyContractQtyTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション建玉＜証拠金不足仮確定＞)
    //引数.指定日が1の時
    public void testCalcBuyContractQtyTempCase2()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresBuyContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionPutSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            int l_intReservedDate = 1;
            double l_dblBuyPositionTemp = l_perindex.calcBuyContractQtyTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション建玉＜証拠金不足仮確定＞)
    //引数.指定日が2の時
    public void testCalcBuyContractQtyTempCase3()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresBuyContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionPutSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            int l_intReservedDate = 2;
            double l_dblBuyPositionTemp = l_perindex.calcBuyContractQtyTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション建玉＜証拠金不足仮確定＞)
    //指定日が範囲外の場合(nが0以上2以下でない場合)
    public void testCalcBuyContractQtyTempCase4()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresBuyContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionPutSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            int l_intReservedDate = 3;
            double l_dblBuyPositionTemp = l_perindex.calcBuyContractQtyTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション建玉＜証拠金不足仮確定＞)
    //引数.指定日が0の時
    public void testCalcSellContractQtyTempCase1()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresSellContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionCallSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            int l_intReservedDate = 0;
            double l_dblBuyPositionTemp = l_perindex.calcSellContractQtyTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション建玉＜証拠金不足仮確定＞)
    //引数.指定日が1の時
    public void testCalcSellContractQtyTempCase2()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresSellContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionCallSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            int l_intReservedDate = 1;
            double l_dblBuyPositionTemp = l_perindex.calcSellContractQtyTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション建玉＜証拠金不足仮確定＞)
    //引数.指定日が2の時
    public void testCalcSellContractQtyTempCase3()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresSellContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionCallSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            int l_intReservedDate = 2;
            double l_dblBuyPositionTemp = l_perindex.calcSellContractQtyTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"20.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション建玉＜証拠金不足仮確定＞)
    //指定日が範囲外の場合(nが0以上2以下でない場合)
    public void testCalcSellContractQtyTempCase4()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresSellContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionCallSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            int l_intReservedDate = 3;
            double l_dblBuyPositionTemp = l_perindex.calcSellContractQtyTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }

    //(calc指数別買ポジション金額＜証拠金不足仮確定＞)
    //引数.指定日が0の時
    public void testCalcPossibleBuyAmtTempCase1()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtTempCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresBuyContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionPutSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            l_perindex.setIfoDepositPerUnitTemp(10.0);
            int l_intReservedDate = 0;
            double l_dblPossAmtTemp = l_perindex.calcPossibleBuyAmtTemp(l_intReservedDate);
            assertEquals("" + l_dblPossAmtTemp,"200.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション金額＜証拠金不足仮確定＞)
    //引数.指定日が1の時
    public void testCalcPossibleBuyAmtTempCase2()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtTempCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresBuyContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionPutSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            l_perindex.setIfoDepositPerUnitTemp(10.0);
            int l_intReservedDate = 1;
            double l_dblPossAmtTemp = l_perindex.calcPossibleBuyAmtTemp(l_intReservedDate);
            assertEquals("" + l_dblPossAmtTemp,"200.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション金額＜証拠金不足仮確定＞)
    //引数.指定日が2の時
    public void testCalcPossibleBuyAmtTempCase3()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtTempCase3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresBuyContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionPutSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            l_perindex.setIfoDepositPerUnitTemp(10.0);
            int l_intReservedDate = 2;
            double l_dblPossAmtTemp = l_perindex.calcPossibleBuyAmtTemp(l_intReservedDate);
            assertEquals("" + l_dblPossAmtTemp,"200.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //(calc指数別買ポジション金額＜証拠金不足仮確定＞)
    //引数.指定日が3の時
    public void testCalcPossibleBuyAmtTempCase4()
    {
        final String STR_METHOD_NAME = "testCalcPossibleBuyAmtTempCase4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresBuyContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionPutSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            l_perindex.setIfoDepositPerUnitTemp(10.0);
            int l_intReservedDate = 3;
            double l_dblPossAmtTemp = l_perindex.calcPossibleBuyAmtTemp(l_intReservedDate);
            assertEquals("" + l_dblPossAmtTemp,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc指数別売ポジション金額＜証拠金不足仮確定＞
    //引数.指定日が0の時
    public void testCalcPossibleSellAmtTempCase1()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresSellContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionCallSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            l_perindex.setIfoDepositPerUnitTemp(10.0);
            int l_intReservedDate = 0;
            double l_dblBuyPositionTemp = l_perindex.calcPossibleSellAmtTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"200.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc指数別売ポジション金額＜証拠金不足仮確定＞
    //引数.指定日が1の時
    public void testCalcPossibleSellAmtTempCase2()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresSellContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionCallSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            l_perindex.setIfoDepositPerUnitTemp(10.0);
            int l_intReservedDate = 1;
            double l_dblBuyPositionTemp = l_perindex.calcPossibleSellAmtTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"200.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc指数別売ポジション金額＜証拠金不足仮確定＞
    //引数.指定日が2の時
    public void testCalcPossibleSellAmtTempCase3()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresSellContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionCallSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            l_perindex.setIfoDepositPerUnitTemp(10.0);
            int l_intReservedDate = 2;
            double l_dblBuyPositionTemp = l_perindex.calcPossibleSellAmtTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"200.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    //calc指数別売ポジション金額＜証拠金不足仮確定＞
    //引数.指定日が3の時
    public void testCalcPossibleSellAmtTempCase4()
    {
        final String STR_METHOD_NAME = "testCalcBuyContractQtyTempCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Field field1 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("futuresSellContractQuantityTemp");
            field1.setAccessible(true);
            field1.set(l_perindex, new Double(10.0));
            
            Field field2 = WEB3IfoSummaryContractPerIndex.class.getDeclaredField("optionCallSellContractQuantityTemp");
            field2.setAccessible(true);
            field2.set(l_perindex, new Double(10.0));
 
            l_perindex.setIfoDepositPerUnitTemp(10.0);
            int l_intReservedDate = 3;
            double l_dblBuyPositionTemp = l_perindex.calcPossibleSellAmtTemp(l_intReservedDate);
            assertEquals("" + l_dblBuyPositionTemp,"0.0");
        }
        catch (Exception l_ex)
        {
            log.error(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
}@
