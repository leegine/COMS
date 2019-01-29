head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositPersistentDataManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3IfoDepositPersistentDataManagerTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/14 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.ifodeposit;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.IfoDepositParams;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositPersistentDataManagerTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositPersistentDataManagerTest.class);

    public WEB3IfoDepositPersistentDataManagerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //建玉テーブル(ifo_contract)を引数の条件で検索した結果を返却する。
    //レコードが存在の場合
    public void testGetContractParamsList_C0001()
    {
        final String STR_METHOD_NAME = "testGetContractParamsList_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams ifoContractParams = TestDBUtility.getIfoContractRow();
            TestDBUtility.insertWithDel(ifoContractParams);

            IfoContractParams[] ifoContractParams2 =
                WEB3IfoDepositPersistentDataManager.getContractParamsList(101001010000L, 10100101000007L);
            assertEquals(ifoContractParams2[0].getAccountId(), 101001010000L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //建玉テーブル(ifo_contract)を引数の条件で検索した結果を返却する。
    //レコードがnullの場合
    public void testGetContractParamsList_C0002()
    {
        final String STR_METHOD_NAME = "testGetContractParamsList_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams ifoContractParams = TestDBUtility.getIfoContractRow();
            TestDBUtility.insertWithDel(ifoContractParams);

            IfoContractParams[] ifoContractParams2 =
                WEB3IfoDepositPersistentDataManager.getContractParamsList(101001010000L, 1);
            assertNull(ifoContractParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //トランザクション（取引勘定明細）テーブル(ifo_fin_transaction)を引数の条件で検索した結果を返却する。
    //当レコードが存在の場合
    public void testGetFinTransactionParamsList_C0001()    
    {
        final String STR_METHOD_NAME = "testGetFinTransactionParamsList_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams finTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            finTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20080815", "yyyyMMdd"));
            TestDBUtility.insertWithDel(finTransactionParams);

            IfoFinTransactionParams[] finTransactionParams2 =
                WEB3IfoDepositPersistentDataManager.getFinTransactionParamsList(
                    333812512203L, 33381251220301L, WEB3DateUtility.getDate("20080815", "yyyyMMdd"));
            assertEquals(finTransactionParams2[0].getAccountId(), 333812512203L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //トランザクション（取引勘定明細）テーブル(ifo_fin_transaction)を引数の条件で検索した結果を返却する。
    //当レコードがnullの場合
    public void testGetFinTransactionParamsList_C0002()    
    {
        final String STR_METHOD_NAME = "testGetFinTransactionParamsList_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams finTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            finTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20080815", "yyyyMMdd"));
            TestDBUtility.insertWithDel(finTransactionParams);

            IfoFinTransactionParams[] finTransactionParams2 =
                WEB3IfoDepositPersistentDataManager.getFinTransactionParamsList(
                    333812512203L, 1, WEB3DateUtility.getDate("20080815", "yyyyMMdd"));
            assertNull(finTransactionParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //注文単位テーブル(ifo_order_unit)を引数の条件で検索した結果を返却する。
    //当レコードが存在の場合
    public void testGetTodayOpenOrderUnitParamsList_C0001()
    {
        final String STR_METHOD_NAME = "testGetTodayOpenOrderUnitParamsList_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            ifoOrderUnitParams.setBizDate("20080815");
            TestDBUtility.insertWithDel(ifoOrderUnitParams);

            IfoOrderUnitParams[] ifoOrderUnitParams2 =
                WEB3IfoDepositPersistentDataManager.getTodayOpenOrderUnitParamsList(
                    101001010010L, 10100101001007L, WEB3DateUtility.getDate("20080815", "yyyyMMdd"));
            assertEquals(ifoOrderUnitParams2[0].getAccountId(), 101001010010L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //注文単位テーブル(ifo_order_unit)を引数の条件で検索した結果を返却する。
    //当レコードがnullの場合
    public void testGetTodayOpenOrderUnitParamsList_C0002()
    {
        final String STR_METHOD_NAME = "testGetTodayOpenOrderUnitParamsList_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            ifoOrderUnitParams.setBizDate("20080815");
            TestDBUtility.insertWithDel(ifoOrderUnitParams);

            IfoOrderUnitParams[] ifoOrderUnitParams2 =
                WEB3IfoDepositPersistentDataManager.getTodayOpenOrderUnitParamsList(
                    101001010010L, 1, WEB3DateUtility.getDate("20080815", "yyyyMMdd"));
            assertNull(ifoOrderUnitParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //入出金の注文単位テーブル(aio_order_unit)を引数の条件で検索した結果を返却する。
    //当レコードが存在の場合
    public void testGetTodayAioOrderUnitParamsListC0001()
    {
        final String STR_METHOD_NAME = "testGetTodayAioOrderUnitParamsListC0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            aioOrderUnitParams.setBizDate("20080815");
            aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            TestDBUtility.insertWithDel(aioOrderUnitParams);

            AioOrderUnitParams[] aioOrderUnitParams2 =
                WEB3IfoDepositPersistentDataManager.getTodayAioOrderUnitParamsList(
                    111101111010L, 11110111101001L, WEB3DateUtility.getDate("20080815", "yyyyMMdd"));
            assertEquals(aioOrderUnitParams2[0].getAccountId(), 111101111010L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //入出金の注文単位テーブル(aio_order_unit)を引数の条件で検索した結果を返却する。
    //当レコードがnullの場合
    public void testGetTodayAioOrderUnitParamsListC0002()
    {
        final String STR_METHOD_NAME = "testGetTodayAioOrderUnitParamsListC0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            aioOrderUnitParams.setBizDate("20080815");
            aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            TestDBUtility.insertWithDel(aioOrderUnitParams);

            AioOrderUnitParams[] aioOrderUnitParams2 =
                WEB3IfoDepositPersistentDataManager.getTodayAioOrderUnitParamsList(
                    111101111010L, 1, WEB3DateUtility.getDate("20080815", "yyyyMMdd"));
            assertNull(aioOrderUnitParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する先物OP銘柄Paramsを取得する。
    //検索結果が存在の場合
    public void testGetProductParams_C0001()
    {
        final String STR_METHOD_NAME = "testGetProductParams_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams ifoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(ifoProductParams);

            IfoProductParams ifoProductParams2 =
                WEB3IfoDepositPersistentDataManager.getProductParams(1006160060005L);
            assertEquals(ifoProductParams2.getProductId(), 1006160060005L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する先物OP銘柄Paramsを取得する。
    //検索結果がnullの場合
    public void testGetProductParams_C0002()
    {
        final String STR_METHOD_NAME = "testGetProductParams_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams ifoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(ifoProductParams);

            IfoProductParams ifoProductParams2 =
                WEB3IfoDepositPersistentDataManager.getProductParams(1);
            assertNull(ifoProductParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する先物OP取引銘柄Paramsを取得する。
    //検索結果が存在の場合
    public void testGetTradedProductParamsLongLong_C0001()
    {
        final String STR_METHOD_NAME = "testGetTradedProductParamsLongLong_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TestDBUtility.insertWithDel(ifoTradedProductParams);

            IfoTradedProductParams ifoTradedProductParams2 =
                WEB3IfoDepositPersistentDataManager.getTradedProductParams(1006139070605L, 1001L);
            assertEquals(ifoTradedProductParams2.getProductId(), 1006139070605L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する先物OP取引銘柄Paramsを取得する。
    //検索結果が存nullの場合
    public void testGetTradedProductParamsLongLong_C0002()
    {
        final String STR_METHOD_NAME = "testGetTradedProductParamsLongLong_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TestDBUtility.insertWithDel(ifoTradedProductParams);

            IfoTradedProductParams ifoTradedProductParams2 =
                WEB3IfoDepositPersistentDataManager.getTradedProductParams(1, 1001L);
            assertNull(ifoTradedProductParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する先物OP取引銘柄Paramsを取得する。(有効日指定)
    //検索結果が存在の場合
    public void testGetTradedProductParamsLongLongDate_C0001()
    {
        final String STR_METHOD_NAME = "testGetTradedProductParamsLongLongDate_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            ifoTradedProductParams.setProductId(1006139070605L);
            ifoTradedProductParams.setMarketId(1001L);
            ifoTradedProductParams.setValidForBizDate("20040707");
            TestDBUtility.insertWithDel(ifoTradedProductParams);

            IfoTradedProductParams ifoTradedProductParams2 =
                WEB3IfoDepositPersistentDataManager.getTradedProductParams(1006139070605L, 1001L, WEB3DateUtility.getDate("20040707", "yyyyMMdd"));
            assertEquals(ifoTradedProductParams2.getProductId(), 1006139070605L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する先物OP取引銘柄Paramsを取得する。(有効日指定)
    //検索結果がnullの場合
    public void testGetTradedProductParamsLongLongDate_C0002()
    {
        final String STR_METHOD_NAME = "testGetTradedProductParamsLongLongDate_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TestDBUtility.insertWithDel(ifoTradedProductParams);

            IfoTradedProductParams ifoTradedProductParams2 =
                WEB3IfoDepositPersistentDataManager.getTradedProductParams(1006139070605L, 1001L, WEB3DateUtility.getDate("20080815", "yyyyMMdd"));
            assertNull(ifoTradedProductParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * get取引銘柄Params nullの場合
     * 検索結果がnullの場合
     */
    public void testGetTradedProductParamsLongLongDate_C0003()
    {
        final String STR_METHOD_NAME = "testGetTradedProductParamsLongLongDate_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            
            IfoTradedProductParams ifoTradedProductParams2 =
                WEB3IfoDepositPersistentDataManager.getTradedProductParams(1006139070605L, 1001L, WEB3DateUtility.getDate("20080815", "yyyyMMdd"));
            assertNull(ifoTradedProductParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する先物OP取引銘柄一時Paramsを取得する。
    //検索結果が存在の場合
    public void testGetTradedProductUpdqParams_C0001()
    {
        final String STR_METHOD_NAME = "testGetTradedProductUpdqParams_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            TestDBUtility.insertWithDel(ifoTradedProductUpdqParams);

            IfoTradedProductUpdqParams ifoTradedProductUpdqParams2 =
                WEB3IfoDepositPersistentDataManager.getTradedProductUpdqParams(
                    1006139070L, 1001L, "10", WEB3DateUtility.getDate("20070628", "yyyyMMdd"));
            assertEquals(ifoTradedProductUpdqParams2.getProductId(), 1006139070L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する先物OP取引銘柄一時Paramsを取得する。
    //検索結果がnullの場合
    public void testGetTradedProductUpdqParams_C0002()
    {
        final String STR_METHOD_NAME = "testGetTradedProductUpdqParams_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            TestDBUtility.insertWithDel(ifoTradedProductUpdqParams);

            IfoTradedProductUpdqParams ifoTradedProductUpdqParams2 =
                WEB3IfoDepositPersistentDataManager.getTradedProductUpdqParams(
                    1006139070L, 1, "10", WEB3DateUtility.getDate("20070628", "yyyyMMdd"));
            assertNull(ifoTradedProductUpdqParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する余力条件Paramsを取得する。
    //検索結果が存在の場合
    public void testGetTradingpowerCalcConditionParams_C0001()
    {
        final String STR_METHOD_NAME = "testGetTradingpowerCalcConditionParams_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            TestDBUtility.insertWithDel(tradingpowerCalcConditionParams);

            TradingpowerCalcConditionParams tradingpowerCalcConditionParams2 =
                WEB3IfoDepositPersistentDataManager.getTradingpowerCalcConditionParams(55464654654L);
            assertEquals(tradingpowerCalcConditionParams2.getAccountId(), 55464654654L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する余力条件Paramsを取得する。
    //検索結果がnullの場合
    public void testGetTradingpowerCalcConditionParams_C0002()
    {
        final String STR_METHOD_NAME = "testGetTradingpowerCalcConditionParams_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            TestDBUtility.insertWithDel(tradingpowerCalcConditionParams);

            TradingpowerCalcConditionParams tradingpowerCalcConditionParams2 =
                WEB3IfoDepositPersistentDataManager.getTradingpowerCalcConditionParams(1);
            assertNull(tradingpowerCalcConditionParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する証拠金Paramsを取得する。
    //当レコードが存在の場合
    public void testGetIfoDepositParams_C0001()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositParams_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoDepositRow.TYPE);
            IfoDepositParams ifoDepositParams = new IfoDepositParams();
            ifoDepositParams.setAccountCode("1");
            ifoDepositParams.setInstitutionCode("2");
            ifoDepositParams.setBranchCode("3");
            ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20070628", "yyyyMMdd"));
            TestDBUtility.insertWithDel(ifoDepositParams);

            IfoDepositParams ifoDepositParams2 =
                WEB3IfoDepositPersistentDataManager.getIfoDepositParams(
                    "2", "3", "1", WEB3DateUtility.getDate("20070628", "yyyyMMdd"));
            assertEquals(ifoDepositParams2.getAccountCode(), "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する証拠金Paramsを取得する。
    //当レコードがnullの場合
    public void testGetIfoDepositParams_C0002()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositParams_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoDepositRow.TYPE);
            IfoDepositParams ifoDepositParams = new IfoDepositParams();
            ifoDepositParams.setAccountCode("1");
            ifoDepositParams.setInstitutionCode("2");
            ifoDepositParams.setBranchCode("3");
            ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20070628", "yyyyMMdd"));
            TestDBUtility.insertWithDel(ifoDepositParams);

            IfoDepositParams ifoDepositParams2 =
                WEB3IfoDepositPersistentDataManager.getIfoDepositParams(
                    "1", "3", "1", WEB3DateUtility.getDate("20070628", "yyyyMMdd"));
            assertNull(ifoDepositParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * 計算日 instanceof Timestamp
     * 当レコードが存在の場合
     */
    public void testGetIfoDepositParams_C0003()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositParams_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoDepositRow.TYPE);
            IfoDepositParams ifoDepositParams = new IfoDepositParams();
            ifoDepositParams.setAccountCode("1");
            ifoDepositParams.setInstitutionCode("2");
            ifoDepositParams.setBranchCode("3");
            ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20070628", "yyyyMMdd"));
            TestDBUtility.insertWithDel(ifoDepositParams);
            
            Timestamp l_datEstimateDate = new Timestamp(WEB3DateUtility.getDate("20070628", "yyyyMMdd").getTime());
            
            IfoDepositParams ifoDepositParams2 =
                WEB3IfoDepositPersistentDataManager.getIfoDepositParams(
                    "2", "3", "1", l_datEstimateDate);
            assertEquals(ifoDepositParams2.getAccountCode(), "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する顧客勘定残高マスタ情報Paramsを取得する。
    //当レコードが存在の場合
    public void testGetTpCashBalanceParams_C0001()
    {
        final String STR_METHOD_NAME = "testGetTpCashBalanceParams_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams cashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            TestDBUtility.insertWithDel(cashBalanceParams);

            TpCashBalanceParams cashBalanceParams2 =
                WEB3IfoDepositPersistentDataManager.getTpCashBalanceParams(333812512203L, 33381251220301L);
            assertEquals(cashBalanceParams2.getAccountId(), 333812512203L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する顧客勘定残高マスタ情報Paramsを取得する。
    //当レコードがnullの場合
    public void testGetTpCashBalanceParams_C0002()
    {
        final String STR_METHOD_NAME = "testGetTpCashBalanceParams_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams cashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            TestDBUtility.insertWithDel(cashBalanceParams);

            TpCashBalanceParams cashBalanceParams2 =
                WEB3IfoDepositPersistentDataManager.getTpCashBalanceParams(1, 33381251220301L);
            assertNull(cashBalanceParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致するプロセス管理Paramsを取得する。
    //当レコードが存在の場合
    public void testGetProcessManagementParams_C0001()
    {
        final String STR_METHOD_NAME = "testGetProcessManagementParams_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            ProcessManagementParams managementParams = new ProcessManagementParams();
            managementParams.setProcessId("1");
            managementParams.setInstitutionCode("2");
            managementParams.setBranchCode("3");
            TestDBUtility.insertWithDel(managementParams);

            ProcessManagementParams managementParams2 =
                WEB3IfoDepositPersistentDataManager.getProcessManagementParams("1", "2", "3");
            assertEquals(managementParams2.getBranchCode(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致するプロセス管理Paramsを取得する。
    //当レコードがnullの場合
    public void testGetProcessManagementParams_C0002()
    {
        final String STR_METHOD_NAME = "testGetProcessManagementParams_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            ProcessManagementParams managementParams = new ProcessManagementParams();
            managementParams.setProcessId("1");
            managementParams.setInstitutionCode("2");
            managementParams.setBranchCode("3");
            TestDBUtility.insertWithDel(managementParams);

            ProcessManagementParams managementParams2 =
                WEB3IfoDepositPersistentDataManager.getProcessManagementParams("0", "2", "3");
            assertNull(managementParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する部店用プリファ@レンスParamsの一覧を取得する。 
    //当レコードが存在の場合
    public void testGetBranchPreferencesParamsList_C0001()
    {
        final String STR_METHOD_NAME = "testGetBranchPreferencesParamsList_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            TestDBUtility.insertWithDel(branchPreferencesParams);

            BranchPreferencesParams[] branchPreferencesParams2 =
                WEB3IfoDepositPersistentDataManager.getBranchPreferencesParamsList(87871);
            assertEquals(branchPreferencesParams2[0].getBranchId(), 87871);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //引数で指定された条件に一致する部店用プリファ@レンスParamsの一覧を取得する。 
    //当レコードがnullの場合
    public void testGetBranchPreferencesParamsList_C0002()
    {
        final String STR_METHOD_NAME = "testGetBranchPreferencesParamsList_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            TestDBUtility.insertWithDel(branchPreferencesParams);

            BranchPreferencesParams[] branchPreferencesParams2 =
                WEB3IfoDepositPersistentDataManager.getBranchPreferencesParamsList(1);
            assertNull(branchPreferencesParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //注文単位テーブル(ifo_order_unit)を引数の条件で検索した結果を返却する
    //該当レコードが存在の場合
    public void testGetIfoOrderUnitParams_C0001()
    {
        final String STR_METHOD_NAME = "testGetIfoOrderUnitParams_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(ifoOrderUnitParams);

            IfoOrderUnitParams ifoOrderUnitParams2 =
                WEB3IfoDepositPersistentDataManager.getIfoOrderUnitParams(1001);
            assertEquals(ifoOrderUnitParams2.getOrderUnitId(), 1001);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //注文単位テーブル(ifo_order_unit)を引数の条件で検索した結果を返却する
    //該当レコードがnullの場合
    public void testGetIfoOrderUnitParams_C0002()
    {
        final String STR_METHOD_NAME = "testGetIfoOrderUnitParams_C0002)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(ifoOrderUnitParams);

            IfoOrderUnitParams ifoOrderUnitParams2 =
                WEB3IfoDepositPersistentDataManager.getIfoOrderUnitParams(1);
            assertNull(ifoOrderUnitParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

}
@
