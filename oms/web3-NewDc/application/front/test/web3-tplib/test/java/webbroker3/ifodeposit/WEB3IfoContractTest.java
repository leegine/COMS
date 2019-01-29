head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoContractTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoContractTest
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/18 陸文靜（中訊）新規作成
*/
package webbroker3.ifodeposit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.enum.EnumeratedBoolean.IntValues;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3IfoContractTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3IfoContractTest.class);

    public WEB3IfoContractTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    WEB3IfoContract l_ifoContract = null;
    protected void setUp() throws Exception
    {
        super.setUp();
        l_ifoContract = new WEB3IfoContract();
    }
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //  建玉数量＜証拠金不足仮確定＞
    //  引数.発注日!=引数.建玉Params.建日 かつ 引数.建玉Params.立会区分==”夕場”の場合 
    // ZEROをセット。
    public void testGetIfoContract_C0001()
    {
        final String STR_METHOD_NAME = "testGetIfoContract_C0001()";
        log.entering(STR_METHOD_NAME);
        
        Date l_datBizDate = WEB3DateUtility.getDate("20040701","yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.session_type = WEB3SessionTypeDef.EVENING_SESSION;
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3IfoContract l_ifo = WEB3IfoContract.getIfoContract(l_ifoContractParams,l_datBizDate);
            assertEquals("1",l_ifo.sessionType);
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_ifo.quantityTemp));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }
    }
    //  建玉数量＜証拠金不足仮確定＞
    //  引数.発注日==引数.建玉Params.建日 かつ 引数.建玉Params.立会区分==”夕場”の場合
    public void testGetIfoContract_C0002()
    {
        final String STR_METHOD_NAME = "testGetIfoContract_C0002()";
        log.entering(STR_METHOD_NAME);
        
        Date l_datBizDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.session_type = WEB3SessionTypeDef.EVENING_SESSION;
            TestDBUtility.insertWithDel(l_ifoContractParams);

            WEB3IfoContract l_ifo = WEB3IfoContract.getIfoContract(l_ifoContractParams,l_datBizDate);
            assertEquals("1",l_ifo.sessionType);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_ifo.quantityTemp));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get先物決済損益[T+1]
    //先物OP保有建玉取引勘定明細.is先物 == false
    public void testGetNextBizDateFuturesCloseProfitLoss_C0001()
    {
        final String STR_METHOD_NAME = "testGetNextBizDateFuturesCloseProfitLoss_C0001()";
        log.entering(STR_METHOD_NAME);

        Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            double l_dbLPL = l_ifoContract.getNextBizDateFuturesCloseProfitLoss(l_datDeliveryDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbLPL));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get先物決済損益[T+1]
    //先物OP保有建玉取引勘定明細.is先物 == true &&
    //先物OP保有建玉取引勘定明細.受渡日 ==　@引数.受渡日
    public void testGetNextBizDateFuturesCloseProfitLoss_C0002()
    {
        final String STR_METHOD_NAME = "testGetNextBizDateFuturesCloseProfitLoss_C0002()";
        log.entering(STR_METHOD_NAME);

        Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            double l_dbLPL = l_ifoContract.getNextBizDateFuturesCloseProfitLoss(l_datDeliveryDate);
            assertEquals("5",WEB3StringTypeUtility.formatNumber(l_dbLPL));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get先物決済損益[T+2]
    //先物OP保有建玉取引勘定明細.is先物 == false
    public void testGetNext2BizDateFuturesCloseProfitLoss_C0001()
    {
        final String STR_METHOD_NAME = "testGetNext2BizDateFuturesCloseProfitLoss_C0001()";
        log.entering(STR_METHOD_NAME);

        Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            double l_dbLPL = l_ifoContract.getNext2BizDateFuturesCloseProfitLoss(l_datDeliveryDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbLPL));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get先物決済損益[T+2]
    //先物OP保有建玉取引勘定明細.is先物 == true &&
    //先物OP保有建玉取引勘定明細.受渡日 ==　@引数.受渡日
    public void testGetNext2BizDateFuturesCloseProfitLoss_C0002()
    {
        final String STR_METHOD_NAME = "testGetNext2BizDateFuturesCloseProfitLoss_C0002()";
        log.entering(STR_METHOD_NAME);

        Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            double l_dbLPL = l_ifoContract.getNext2BizDateFuturesCloseProfitLoss(l_datDeliveryDate);
            assertEquals("5",WEB3StringTypeUtility.formatNumber(l_dbLPL));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //getオプション受渡代金[T+1]
    //先物OP保有建玉取引勘定明細.is先物 == true
    public void testGetNextBizDateOptionNetAmount_C0001()
    {
        final String STR_METHOD_NAME = "testGetNextBizDateOptionNetAmount_C0001()";
        log.entering(STR_METHOD_NAME);

        Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            double l_dbLPL = l_ifoContract.getNextBizDateOptionNetAmount(l_datDeliveryDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbLPL));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //getオプション受渡代金[T+1]
    //先物OP保有建玉取引勘定明細.is先物 == false &&
    //先物OP保有建玉取引勘定明細.受渡日 ==　@引数.受渡日
    public void testGetNextBizDateOptionNetAmount_C0002()
    {
        final String STR_METHOD_NAME = "testGetNextBizDateOptionNetAmount_C0002()";
        log.entering(STR_METHOD_NAME);

        Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            double l_dbLPL = l_ifoContract.getNextBizDateOptionNetAmount(l_datDeliveryDate);
            assertEquals("5",WEB3StringTypeUtility.formatNumber(l_dbLPL));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //getオプション受渡代金[T+2]
    //先物OP保有建玉取引勘定明細.is先物 == true
    public void testGetNext2BizDateOptionNetAmount_C0001()
    {
        final String STR_METHOD_NAME = "testGetNext2BizDateOptionNetAmount_C0001()";
        log.entering(STR_METHOD_NAME);

        Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            double l_dbLPL = l_ifoContract.getNext2BizDateOptionNetAmount(l_datDeliveryDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbLPL));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //getオプション受渡代金[T+2]
    //先物OP保有建玉取引勘定明細.is先物 == false &&
    //先物OP保有建玉取引勘定明細.受渡日 ==　@引数.受渡日
    public void testGetNext2BizDateOptionNetAmount_C0002()
    {
        final String STR_METHOD_NAME = "testGetNext2BizDateOptionNetAmount_C0002()";
        log.entering(STR_METHOD_NAME);

        Date l_datDeliveryDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            double l_dbLPL = l_ifoContract.getNext2BizDateOptionNetAmount(l_datDeliveryDate);
            assertEquals("5",WEB3StringTypeUtility.formatNumber(l_dbLPL));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get受渡代金
    //this.建日 != 引数.建日の場合は、0を返却し、終了する。
    public void testGetNetAmount_C0001()
    {
        final String STR_METHOD_NAME = "testGetNetAmount_C0001()";
        log.entering(STR_METHOD_NAME);

        Date l_datOpenDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        long l_lngOrderUnitId = 12;
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            double l_dbLPL = l_ifoContract.getNetAmount(l_lngOrderUnitId,l_datOpenDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbLPL));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get受渡代金
    //this.建日 != 引数.建日の場合
    //先物OP保有建玉取引勘定明細.注文単位ID == 引数.注文単位ID
    public void testGetNetAmount_C0002()
    {
        final String STR_METHOD_NAME = "testGetNetAmount_C0002()";
        log.entering(STR_METHOD_NAME);

        Date l_datOpenDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        long l_lngOrderUnitId = 0;
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);

            l_ifoContract.setOpenDate(l_datOpenDate);
            double l_dbLPL = l_ifoContract.getNetAmount(l_lngOrderUnitId,l_datOpenDate);
            assertEquals("5",WEB3StringTypeUtility.formatNumber(l_dbLPL));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get受渡代金
    //this.建日 != 引数.建日の場合
    //先物OP保有建玉取引勘定明細.注文単位ID != 引数.注文単位ID
    public void testGetNetAmount_C0003()
    {
        final String STR_METHOD_NAME = "testGetNetAmount_C0003()";
        log.entering(STR_METHOD_NAME);

        Date l_datOpenDate = WEB3DateUtility.getDate("20040702","yyyyMMdd");
        long l_lngOrderUnitId = 1;
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);

            l_ifoContract.setOpenDate(l_datOpenDate);
            double l_dbLPL = l_ifoContract.getNetAmount(l_lngOrderUnitId,l_datOpenDate);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbLPL));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //subtract建玉数量
    //返済取引であるかの判定
    //引数.トランザクションカテゴリ == ”先物返済取引”
    public void testSubtractQuantity_C0001()
    {
        final String STR_METHOD_NAME = "testSubtractQuantity_C0001()";
        log.entering(STR_METHOD_NAME);
        
        FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE;
        double l_dblQuantity = 10;
        String l_strSessionType = WEB3SessionTypeDef.EVENING_SESSION;
        try
        {
            l_ifoContract.setQuantity(l_dblQuantity);
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            l_ifoContract.subtractQuantity(l_transactionCateg,l_dblQuantity,l_strSessionType);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_ifoContract.quantityTemp));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //subtract建玉数量
    //引数.立会区分!=”夕場”の場合
    public void testSubtractQuantity_C0002()
    {
        final String STR_METHOD_NAME = "testSubtractQuantity_C0002()";
        log.entering(STR_METHOD_NAME);
        
        FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE;
        double l_dblQuantity = 10;
        String l_strSessionType = WEB3SessionTypeDef.OTHER;
        try
        {
            l_ifoContract.setQuantity(l_dblQuantity);
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            l_ifoContract.subtractQuantity(l_transactionCateg,l_dblQuantity,l_strSessionType);
            assertEquals("-10",WEB3StringTypeUtility.formatNumber(l_ifoContract.quantityTemp));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //subtract建玉数量
    //返済取引であるかの判定
    // 引数.トランザクションカテゴリ == ”OP返済取引”
    public void testSubtractQuantity_C0003()
    {
        final String STR_METHOD_NAME = "testSubtractQuantity_C0003()";
        log.entering(STR_METHOD_NAME);
        
        FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE;
        double l_dblQuantity = 10;
        String l_strSessionType = WEB3SessionTypeDef.EVENING_SESSION;
        try
        {
            l_ifoContract.setQuantity(l_dblQuantity);
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            l_ifoContract.subtractQuantity(l_transactionCateg,l_dblQuantity,l_strSessionType);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_ifoContract.quantityTemp));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //subtract建玉数量
    //this.建玉数量＜証拠金不足仮確定＞ = this.建玉数量＜証拠金不足仮確定＞ - 引数.数量
    public void testSubtractQuantity_C0004()
    {
        final String STR_METHOD_NAME = "testSubtractQuantity_C0004()";
        log.entering(STR_METHOD_NAME);
        
        FinTransactionCateg l_transactionCateg = FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE;
        double l_dblQuantity = 10;
        String l_strSessionType = WEB3SessionTypeDef.OTHER;
        try
        {
            l_ifoContract.setQuantity(l_dblQuantity);
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            l_ifoContract.subtractQuantity(l_transactionCateg,l_dblQuantity,l_strSessionType);
            assertEquals("-10",WEB3StringTypeUtility.formatNumber(l_ifoContract.quantityTemp));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSubtractQuantity_C0005()
    {
        final String STR_METHOD_NAME = "testSubtractQuantity_C0005()";
        log.entering(STR_METHOD_NAME);
        
        FinTransactionCateg l_transactionCateg = FinTransactionCateg.OTHER;
        double l_dblQuantity = 10;
        String l_strSessionType = WEB3SessionTypeDef.OTHER;
        try
        {
            l_ifoContract.subtractQuantity(l_transactionCateg,l_dblQuantity,l_strSessionType);
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_ifoContract.quantityTemp));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //is決済済
    //this.建玉数量==0の場合、trueを返却する。
    public void testIsSettled_C0001()
    {
        final String STR_METHOD_NAME = "testIsSettled_C0001()";
        log.entering(STR_METHOD_NAME);

        double l_dblQuantity = 0;
        try
        {
            l_ifoContract.setQuantity(l_dblQuantity);
            boolean l_b = l_ifoContract.isSettled();
            assertEquals(true,l_b);   
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //is決済済
    //this.建玉数量==0以外の場合、falseを返却する。
    public void testIsSettled_C0002()
    {
        final String STR_METHOD_NAME = "testIsSettled_C0002()";
        log.entering(STR_METHOD_NAME);

        double l_dblQuantity = 1;
        try
        {
            l_ifoContract.setQuantity(l_dblQuantity);
            boolean l_b = l_ifoContract.isSettled();
            assertEquals(false,l_b);   
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //is買建
    //this.建区分==”買建”の場合、trueを返却する。
    public void testIsBuy_C0001()
    {
        final String STR_METHOD_NAME = "testIsBuy_C0001()";
        log.entering(STR_METHOD_NAME);

        double l_dblQuantity = 0;
        try
        {
            ContractTypeEnum l_contractType = ContractTypeEnum.LONG;
            l_ifoContract.setContractType(l_contractType);
            l_ifoContract.setQuantity(l_dblQuantity);
            boolean l_b = l_ifoContract.isBuy();
            assertEquals(true,l_b);   
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //is買建
    //this.建区分==”買建”以外の場合,falseを返却する。
    public void testIsBuy_C0002()
    {
        final String STR_METHOD_NAME = "testIsBuy_C0002()";
        log.entering(STR_METHOD_NAME);

        double l_dblQuantity = 0;
        try
        {
            ContractTypeEnum l_contractType = ContractTypeEnum.SHORT;
            l_ifoContract.setContractType(l_contractType);
            l_ifoContract.setQuantity(l_dblQuantity);
            boolean l_b = l_ifoContract.isBuy();
            assertEquals(false,l_b);   
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //is当日建
    //以外の場合,falseを返す。
    public void testIsTodayContract_C0001()
    {
        final String STR_METHOD_NAME = "testIsBuy_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
        try
        {
            boolean l_b = l_ifoContract.isTodayContract(l_ifoDepositCalcCondition);
            assertEquals(false,l_b);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    //証拠金計算基準日 == 1:当日注文時間帯以外の場合
    //以外の場合,falseを返す。
    public void testIsTodayContract_C0002()
    {
        final String STR_METHOD_NAME = "testIsTodayContract_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = new Date();
            l_bizDates[0] = WEB3DateUtility.getDate("20080804","yyyyMMdd");
            l_bizDates[1] = new Date();
            l_bizDates[1] = WEB3DateUtility.getDate("20080804","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intBaseDate = 1;
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(l_intBaseDate);

            boolean l_b = l_ifoContract.isTodayContract(l_ifoDepositCalcCondition);
            assertEquals(false,l_b);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    //証拠金計算基準日 == 1:当日注文時間帯 の場合
    //this.建日==営業日（T-1） かつ this.立会区分=夕場 or this.建日==営業日（T+0）trueを返す。
    public void testIsTodayContract_C0003()
    {
        final String STR_METHOD_NAME = "testIsTodayContract_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = new Date();
            l_bizDates[0] = WEB3DateUtility.getDate("20080804","yyyyMMdd");
            l_bizDates[1] = new Date();
            l_bizDates[1] = WEB3DateUtility.getDate("20080804","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intBaseDate = 1;
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(l_intBaseDate);
            Date l_datOpenDate = WEB3DateUtility.getDate("20080804","yyyyMMdd");
            l_ifoContract.setOpenDate(l_datOpenDate);
            l_ifoContract.sessionType = WEB3SessionTypeDef.EVENING_SESSION;

            boolean l_b = l_ifoContract.isTodayContract(l_ifoDepositCalcCondition);
            assertEquals(true,l_b);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    //証拠金計算基準日 == 2:翌日注文時間帯 の場合
    //this.建日==営業日（T+0） かつ this.立会区分=夕場,trueを返す
    public void testIsTodayContract_C0004()
    {
        final String STR_METHOD_NAME = "testIsTodayContract_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = new Date();
            l_bizDates[0] = WEB3DateUtility.getDate("20080804","yyyyMMdd");
            l_bizDates[1] = new Date();
            l_bizDates[1] = WEB3DateUtility.getDate("20080804","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intBaseDate = 2;
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(l_intBaseDate);
            Date l_datOpenDate = WEB3DateUtility.getDate("20080804","yyyyMMdd");
            l_ifoContract.setOpenDate(l_datOpenDate);
            l_ifoContract.sessionType = WEB3SessionTypeDef.EVENING_SESSION;

            boolean l_b = l_ifoContract.isTodayContract(l_ifoDepositCalcCondition);
            assertEquals(true,l_b);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    //証拠金計算基準日 == 2:翌日注文時間帯 の場合 
    //falseを返す
    public void testIsTodayContract_C0005()
    {
        final String STR_METHOD_NAME = "testIsTodayContract_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = new Date();
            l_bizDates[0] = WEB3DateUtility.getDate("20080804","yyyyMMdd");
            l_bizDates[1] = new Date();
            l_bizDates[1] = WEB3DateUtility.getDate("20080804","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intBaseDate = 2;
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(l_intBaseDate);
            Date l_datOpenDate = WEB3DateUtility.getDate("20080804","yyyyMMdd");
            l_ifoContract.setOpenDate(l_datOpenDate);
            l_ifoContract.sessionType = WEB3SessionTypeDef.OTHER;

            boolean l_b = l_ifoContract.isTodayContract(l_ifoDepositCalcCondition);
            assertEquals(false,l_b);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    //add先物OP保有建玉取引勘定明細
    public void testAddIfoFinTransaction()
    {
        final String STR_METHOD_NAME = "testAddIfoFinTransaction()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3IfoFinTransaction l_transaction = new WEB3IfoFinTransaction();
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderUnitId(12);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);

            l_ifoContract.addIfoFinTransaction(l_ifoFinTransactionParams);
            l_transaction = (WEB3IfoFinTransaction)l_ifoContract.ifoFinTransactionList.get(0);
            assertEquals(1,l_transaction.finTransactionId);
            assertEquals(12,l_transaction.orderUnitId);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }

}
@
