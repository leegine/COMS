head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoFrontOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3IfoFrontOrderServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/01 徐宏偉 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAllDao;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3IfoFrontOrderServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoFrontOrderServiceImplTest.class);

    WEB3IfoFrontOrderServiceImpl l_ifoFrontOrderServiceImpl = 
        new WEB3IfoFrontOrderServiceImpl();

    public WEB3IfoFrontOrderServiceImplTest(String arg0)
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

    /*
     * 引数の市場コード == "東証"の場合  引数の市場コード == "1:東証"  
     * 2:東証、JASDAQオークション、名証"を返却する。 正常
     */
    public void testGetFrontOrderSystemCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderSystemCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //引数の市場コード == "東証"の場合  引数の市場コード == "1:東証" 
        String l_strMarketCode = "1";
        String l_strFrontOrderSystemCode =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderSystemCode(l_strMarketCode);

        log.debug("2:東証、JASDAQオークション = " + l_strFrontOrderSystemCode);
        assertEquals("2", l_strFrontOrderSystemCode);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数の市場コード == "大証"の場合  引数の市場コード == "2:大証"  
     * "1:大証"を返却する。    正常
     */
    public void testGetFrontOrderSystemCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderSystemCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //引数の市場コード == "大証"の場合  引数の市場コード == "2:大証" 
        String l_strMarketCode = "2";
        String l_strFrontOrderSystemCode =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderSystemCode(l_strMarketCode);

        log.debug("1:大証を返却" + l_strFrontOrderSystemCode);
        assertEquals("1", l_strFrontOrderSystemCode);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の市場コードが上記いずれでもない場合
     * 引数の市場コード  != ""東証"" &&
     * 引数の市場コード != ""大証""の場合
     * "   "引数の市場コード  != ""1:東証"" &&
     * 引数の市場コード != ""2:大証""
     * 引数の市場コード  == null"  "9:その他"を返却する    正常
     */
    public void testGetFrontOrderSystemCode_case003()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderSystemCode_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //* 引数の市場コード  != ""東証"" &&
        //* 引数の市場コード != ""大証""の場合
        String l_strMarketCode = null;
        String l_strFrontOrderSystemCode =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderSystemCode(l_strMarketCode);

        log.debug("9:その他を返却" + l_strFrontOrderSystemCode);
        assertEquals("9", l_strFrontOrderSystemCode);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の証券会社コード　@== 64
     * 引数の市場コード == 0
     * 発注先切替.get有効発注先切替()的 返回? == null"    
     * "OrderSwitchingRow表中不存在
     * （証券会社コード == 64 && 市場コード == 0 && 
     * フロント発注システム区分 == 9） 的記?
     * "   発注経路切替対象なし」の例外をthrow[BUSINESS_ERROR_02216]  異常
     */
    public void testGetSubmitOrderRouteDiv_case001()
    {
        final String STR_METHOD_NAME = ".testGetSubmitOrderRouteDiv_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strInstitutionCode = "01";
        String l_strMarketCode = "0";

        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("64");
            l_orderSwitchingParams.setMarketCode("0");
            l_orderSwitchingParams.setFrontOrderSystemCode("1");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            this.l_ifoFrontOrderServiceImpl.getSubmitOrderRouteDiv(l_strInstitutionCode, l_strMarketCode);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02216, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * 発注先切替.get有効発注先切替()的 返回? != nullの場合
     * 確認返回?是否正確"  "OrderSwitchingRow表中存在
     * （証券会社コード == 01 && 市場コード == 0 && 
     * フロント発注システム区分 == 9） 的記?
     * 発注経路区分 = 0：SONAR正系" "発注先切替.get有効発注先切替()の戻り値.発注経路区分 を返却する
     * return 0：SONAR正系"
     */
    public void testGetSubmitOrderRouteDiv_case002()
    {
        final String STR_METHOD_NAME = ".testGetSubmitOrderRouteDiv_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strInstitutionCode = "01";
        String l_strMarketCode = "0";

        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("01");
            l_orderSwitchingParams.setMarketCode("0");
            l_orderSwitchingParams.setFrontOrderSystemCode("9");
            l_orderSwitchingParams.setSubmitOrderRouteDiv("0");
            l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);
            l_orderSwitchingParams.setValidFlag("1");

            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            String l_strSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getSubmitOrderRouteDiv(l_strInstitutionCode, l_strMarketCode);
            assertEquals("0", l_strSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 返回?為16
     */
    public void testGetIndexOfOrderRevInCorpCode()
    {
        final String STR_METHOD_NAME = ".testGetIndexOfOrderRevInCorpCode()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intIndexOfOrderRevInCorpCode =
                this.l_ifoFrontOrderServiceImpl.getIndexOfOrderRevInCorpCode();
            assertEquals(16, l_intIndexOfOrderRevInCorpCode);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 返回?為16
     */
    public void testGetFigureOfOrderRev()
    {
        final String STR_METHOD_NAME = ".testGetFigureOfOrderRev()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intFigureOfOrderRev =
                this.l_ifoFrontOrderServiceImpl.getFigureOfOrderRev();
            assertEquals(2, l_intFigureOfOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "引数の注文単位 == nullの場合
     * 例外をthrow"   引数の注文単位 == null "パラメータ値不正」の例外をthrow
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetCorpCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetCorpCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_orderUnit = null;
            this.l_ifoFrontOrderServiceImpl.getCorpCode(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "引数の注文単位 != nullの場合
     * 部店オブジェクトを取得
     * 察@看返回?"  "引数の注文単位 ！= null
     * l_orderUnitRow.getBranchId() == 64246 
     * 在Branch表中存在branchId==64246的記lu
     * 引数の注文単位オブジェクト中 銘柄タイプ == 6、識別コード = 123456789、注文Rev = 3 
     * 部店オブジェクト中的 証券会社ID = 10、部店コード = 624" 
     * "証券会社ID＋部店コード＋銘柄タイプ＋識別コード＋注文Rev.＋""999""返回
     * （10624612345678903999）"
     */
    public void testGetCorpCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetCorpCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strCorpcode =
                this.l_ifoFrontOrderServiceImpl.getCorpCode(l_orderUnit);
            assertEquals("10624612345678903999", l_strCorpcode);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "引数の注文単位 != null
     * 例外をthrow"   "引数の注文単位 ！= null
     * l_orderUnitRow.getBranchId() == 64246 
     * 在Branch表中不存在branchId==64246的記?" "テーブルに該当するデータがありません」の例外をthrow
     * [SYSTEM_ERROR_80005]"
     */
    public void testGetCorpCode_case003()
    {
        final String STR_METHOD_NAME = ".testGetCorpCode_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            this.l_ifoFrontOrderServiceImpl.getCorpCode(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "引数の注文単位 == null
     * 例外をthrow"   引数の注文単位 == null "パラメータ値不正」の例外をthrow
     * [SYSTEM_ERROR_80017]"
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case001()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "以下の条件のいずれかに合致する場合はfalseを返却する。
     * [取引時間管理.is市場開局時間帯()==falseの場合（＝引け後／非営業日）
     * 取引時間管理.is取引所休憩時間帯()==falseの場合（＝場中で取引所は取引中）]"
     * "引数の注文単位 != null
     * 取引時間管理.is市場開局時間帯()==falseの場合（＝引け後／非営業日）
     * 取引時間管理.is取引所休憩時間帯() == true の場合"    falseを返却
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case002()//TODO 測試不可 這種情況不可能出現
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

//        try
//        {
//            //注文単位オブジェクト
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1);
//            l_ifoOrderUnitParams.setBranchId(64246);
//            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
//            l_ifoOrderUnitParams.setOrderRev("03");
//            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingMod =
//                l_finApp.getTradingModule(ProductTypeEnum.IFO);
//            WEB3OptionOrderManagerImpl l_orderMgr =
//                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
//
//            //取引時間管理.is市場開局時間帯()==falseの場合（＝引け後／非営業日）
//            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
//
//            //取引時間管理.is取引所休憩時間帯() == true の場合
//            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
//
//            boolean l_blnReturn =
//                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
//            assertTrue(!l_blnReturn);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception...", l_ex);
//            fail();
//        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "以下の条件のいずれかに合致する場合はfalseを返却する。
     * [取引時間管理.is市場開局時間帯()==falseの場合（＝引け後／非営業日）
     * 取引時間管理.is取引所休憩時間帯()==falseの場合（＝場中で取引所は取引中）]"
     * "引数の注文単位 != null
     * 取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
     * 取引時間管理.is取引所休憩時間帯() == false の場合"   falseを返却
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case003()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //取引時間管理.is取引所休憩時間帯() == false の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "以下の条件のいずれかに合致する場合はfalseを返却する。
     * [取引時間管理.is市場開局時間帯()==falseの場合（＝引け後／非営業日）
     * 取引時間管理.is取引所休憩時間帯()==falseの場合（＝場中で取引所は取引中）]"
     * "引数の注文単位 != null
     * 取引時間管理.is市場開局時間帯()==falseの場合（＝引け後／非営業日）
     * 取引時間管理.is取引所休憩時間帯() == false の場合"   falseを返却
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case004()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //取引時間管理.is市場開局時間帯()==falseの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            //取引時間管理.is取引所休憩時間帯() == false の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "引数の注文単位.注文状態  不在 下面内容中の場合
     * 　@　@　@　@--------------------------------- 
     * 　@　@　@　@　@
     * 　@　@　@　@　@ACCEPTED 
     * 　@　@　@　@　@MODIFY_ACCEPTED 
     * 　@　@　@　@　@ORDERING 
     * 　@　@　@　@--------------------------------- 
     * "   "引数の注文単位 != null
     * 取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
     * 取引時間管理.is取引所休憩時間帯() == true の場合
     * 引数の注文単位.注文状態  == NOT_ORDERED"   falseを返却
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case005()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //取引時間管理.is取引所休憩時間帯() == true の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    /**
     * "先物OP注文取引キューテーブルを以下の条件で検索
     * [条件] 
     * 　@　@証券会社コード = 引数の注文単位.getBranch().証券会社コード 
     * 　@　@かつ　@部店コード = 引数の注文単位.getBranch().部店コード 
     * 　@　@かつ　@識別コード = 引数の注文単位.識別コード 
     * 　@　@かつ　@社内処理項目に含まれる注文Rev.(*2) = 引数の注文単位.注文Rev. 
     * 　@　@かつ　@処理区分 != ""0：未処理"" 
     * 該当するデータが存在しない場合 、falseを返却する。 
     * "   "引数の注文単位 != null
     * 取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
     * 取引時間管理.is取引所休憩時間帯() == true の場合
     * 引数の注文単位.注文状態 == “ORDERING”
     * [檢索条件] 
     * 　@　@　@引数の注文単位.getBranch().証券会社コード  = 10
     * 　@　@かつ　@引数の注文単位.getBranch().部店コード = 624 
     * 　@　@かつ　@引数の注文単位.識別コード  = 123456789
     * 　@　@かつ　@引数の注文単位.注文Rev. = 50 
     * 　@　@かつ　@処理区分 != ""0：未処理"" 
     * 在先物OP注文取引キューテーブル中沒有上面相應數據"  falseを返却
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case006()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //取引時間管理.is取引所休憩時間帯() == true の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "先物OP注文取引キューテーブルを以下の条件で検索
     * [条件] 
     * 　@　@証券会社コード = 引数の注文単位.getBranch().証券会社コード 
     * 　@　@かつ　@部店コード = 引数の注文単位.getBranch().部店コード 
     * 　@　@かつ　@識別コード = 引数の注文単位.識別コード 
     * 　@　@かつ　@社内処理項目に含まれる注文Rev.(*2) = 引数の注文単位.注文Rev. 
     * 　@　@かつ　@処理区分 != ""0：未処理"" 
     * 該当するデータが存在しない場合 、falseを返却する。 
     * "   "引数の注文単位 != null
     * 取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
     * 取引時間管理.is取引所休憩時間帯() == true の場合
     * 引数の注文単位.注文状態 == “ORDERING”
     * [檢索条件] 
     * 　@　@　@引数の注文単位.getBranch().証券会社コード  = 10
     * 　@　@かつ　@引数の注文単位.getBranch().部店コード = 624 
     * 　@　@かつ　@引数の注文単位.識別コード  = 123456789
     * 　@　@かつ　@引数の注文単位.注文Rev. = 50 
     * 　@　@かつ　@処理区分 != ""0：未処理"" 
     * 在先物OP注文取引キューテーブル中沒有上面相應數據"  falseを返却
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case007()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //取引時間管理.is取引所休憩時間帯() == true の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "先物OP注文取引キューテーブルを以下の条件で検索
     * [条件] 
     * 　@　@証券会社コード = 引数の注文単位.getBranch().証券会社コード 
     * 　@　@かつ　@部店コード = 引数の注文単位.getBranch().部店コード 
     * 　@　@かつ　@識別コード = 引数の注文単位.識別コード 
     * 　@　@かつ　@社内処理項目に含まれる注文Rev.(*2) = 引数の注文単位.注文Rev. 
     * 　@　@かつ　@処理区分 != ""0：未処理"" 
     * 該当するデータが存在しない場合 、falseを返却する。 
     * "   "引数の注文単位 != null
     * 取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
     * 取引時間管理.is取引所休憩時間帯() == true の場合
     * 引数の注文単位.注文状態 == “ORDERING”
     * [檢索条件] 
     * 　@　@　@引数の注文単位.getBranch().証券会社コード  = 10
     * 　@　@かつ　@引数の注文単位.getBranch().部店コード = 624 
     * 　@　@かつ　@引数の注文単位.識別コード  = 123456789
     * 　@　@かつ　@引数の注文単位.注文Rev. = 50 
     * 　@　@かつ　@処理区分 != ""0：未処理"" 
     * 在先物OP注文取引キューテーブル中沒有上面相應數據"  falseを返却
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case008()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //取引時間管理.is取引所休憩時間帯() == true の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "先物OP注文取引キューテーブルを以下の条件で検索
     * [条件] 
     * 　@　@証券会社コード = 引数の注文単位.getBranch().証券会社コード 
     * 　@　@かつ　@部店コード = 引数の注文単位.getBranch().部店コード 
     * 　@　@かつ　@識別コード = 引数の注文単位.識別コード 
     * 　@　@かつ　@社内処理項目に含まれる注文Rev.(*2) = 引数の注文単位.注文Rev. 
     * 　@　@かつ　@処理区分 != ""0：未処理"" 
     * 該当するデータが存在しない場合 、falseを返却する。 
     * "   "引数の注文単位 != null
     * 取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
     * 取引時間管理.is取引所休憩時間帯() == true の場合
     * 引数の注文単位.注文状態 == “ORDERING”
     * [檢索条件] 
     * 　@　@　@引数の注文単位.getBranch().証券会社コード  = 10
     * 　@　@かつ　@引数の注文単位.getBranch().部店コード = 624 
     * 　@　@かつ　@引数の注文単位.識別コード  = 123456789
     * 　@　@かつ　@引数の注文単位.注文Rev. = 67 
     * 　@　@かつ　@処理区分 != ""0：未処理"" 
     * 在先物OP注文取引キューテーブル中有上面相應數據"  trueを返却
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case009()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("12345678901234567890");
            l_hostFotypeOrderAllParams.setStatus("1");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //取引時間管理.is取引所休憩時間帯() == true の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位 != null
     * 部店取得失敗的場合"  "引数の注文単位 != null
     * 注文単位.branchId = 64246
     * Branch表中沒有branchId為64246的記?"  "パラメータ値不正」の例外をthrow
     * [SYSTEM_ERROR_80005]"
     */
    public void testGetHostFotypeOrderAll_case001()
    {
        final String STR_METHOD_NAME = ".testGetHostFotypeOrderAll_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            this.l_ifoFrontOrderServiceImpl.getHostFotypeOrderAll(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位 != null
     * 部店取得成功
     * 指定の注文単位に該当する先物OP注文取引キューを取得し返す"  "引数の注文単位 != null
     * 注文単位.branchId = 64246
     * Branch表中有branchId為64246的記?
     * 先物OP注文取引キューテーブル中存在
     * 　@　@　@[条件] 
     * 　@　@　@注文単位.getBranch().証券会社コード  = 10
     * 　@　@かつ  引数の注文単位.getBranch().部店コード  = 624
     * 　@　@かつ　@引数の注文単位.識別コード  = 123456789
     * 　@　@かつ　@引数の注文単位.注文Rev.  = 67
     * 　@　@かつ　@処理区分 = ""未処理"" "   "返回檢索出的HostFotypeOrderAllParams
     *       表中主要内容如@下：
     *       　@　@　@証券会社コード = 10
     *       　@　@  部店コード = 624
     *       　@　@  識別コード = 引数の注文単位.識別コード = 123456789
     *       　@　@  社内処理項目に含まれる注文Rev. = 67
     *       　@　@  処理区分 = ""未処理"""
     */
    public void testGetHostFotypeOrderAll_case002()
    {
        final String STR_METHOD_NAME = ".testGetHostFotypeOrderAll_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("12345678901234567890");
            l_hostFotypeOrderAllParams.setStatus("0");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            HostFotypeOrderAllParams l_fotypeOrderAllParams =
                this.l_ifoFrontOrderServiceImpl.getHostFotypeOrderAll(l_orderUnit);

            assertEquals("0D", l_fotypeOrderAllParams.getInstitutionCode());
            assertEquals("624", l_fotypeOrderAllParams.getBranchCode());
            assertEquals("123456789", l_fotypeOrderAllParams.getOrderRequestNumber());
            assertEquals("0", l_fotypeOrderAllParams.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位 != null
     * 部店取得成功
     * 指定の注文単位に該当する先物OP注文取引キューを取得し返す"  "引数の注文単位 != null
     * 注文単位.branchId = 64246
     * Branch表中有branchId為64246的記?
     * 先物OP注文取引キューテーブル中不存在
     * 　@　@　@[条件] 
     * 　@　@　@注文単位.getBranch().証券会社コード  = 10
     * 　@　@かつ  引数の注文単位.getBranch().部店コード  = 624
     * 　@　@かつ　@引数の注文単位.識別コード  = 123456789
     * 　@　@かつ　@引数の注文単位.注文Rev.  = 67
     * 　@　@かつ　@処理区分 = ""未処理"" "   "返回檢索出的HostFotypeOrderAllParams
     *       表中主要内容如@下：
     *       　@　@　@証券会社コード = 10
     *       　@　@  部店コード = 624
     *       　@　@  識別コード = 引数の注文単位.識別コード = 123456789
     *       　@　@  社内処理項目に含まれる注文Rev. = 67
     *       　@　@  処理区分 = ""未処理"""
     *       返回null
     */
    public void testGetHostFotypeOrderAll_case003()
    {
        final String STR_METHOD_NAME = ".testGetHostFotypeOrderAll_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setInstitutionCode("10");
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            HostFotypeOrderAllParams l_fotypeOrderAllParams =
                this.l_ifoFrontOrderServiceImpl.getHostFotypeOrderAll(l_orderUnit);

            assertNull(l_fotypeOrderAllParams);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "引数の注文単位 == null
     * 例外をthrow"   引数の注文単位 == null "パラメータ値不正」の例外をthrow
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetHostFotypeOrderAll_case004()
    {
        final String STR_METHOD_NAME = ".testGetHostFotypeOrderAll_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getHostFotypeOrderAll(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "引数の注文単位 == null
     * 例外をthrow"   引数の注文単位 == null "パラメータ値不正」の例外をthrow
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetChangeOrderRev_case001()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "引数の注文単位 != null
     * 引数の注文単位.注文Rev = 50
     * OP注文マネージャ.is内容通知済注文(引数の注文単位)==true(mork實現)" 
     * "引数の注文単位.注文Rev.をそのまま返却する
     * ?為50"   正常
     */
    public void testGetChangeOrderRev_case002()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(true));

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("50", l_ifoOrderUnitParams.getOrderRev());

            String l_changeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("50", l_changeOrderRev);
            WEB3MockObjectParamsValue l_value =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class});

            assertEquals(l_orderUnit, l_value.getCalled(0)[0]);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "取引時間管理.is市場開局時間帯()！=falseの場合
     * 引数の注文単位.市場から確認済の数量==NaNの場合
     * 察@看返回?"  "引数の注文単位 != null
     * 引数の注文単位.注文Rev = 50
     * OP注文マネージャ.is内容通知済注文(引数の注文単位)==true(mork實現)
     * 取引時間管理.is市場開局時間帯()==true
     * 引数の注文単位.市場から確認済の数量==NaN"  "引数の注文単位.注文Rev.をそのまま返却する
     * ?為50"
     */
    public void testGetChangeOrderRev_case003()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(true));

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("50", l_ifoOrderUnitParams.getOrderRev());

            String l_changeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("50", l_changeOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "取引時間管理.is市場開局時間帯()=falseの場合
     * 引数の注文単位.市場から確認済の数量!=NaNの場合
     * 察@看返回?"  "引数の注文単位 != null
     * 引数の注文単位.注文Rev = 50
     * OP注文マネージャ.is内容通知済注文(引数の注文単位)==false(mork實現)
     * 取引時間管理.is市場開局時間帯()==false
     * l_orderUnitRow.getConfirmedQuantity() ==20.0D"  "引数の注文単位.注文Rev.をそのまま返却する
     * ?為50"
     */
    public void testGetChangeOrderRev_case004()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //取引時間管理.is市場開局時間帯()==falseの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("50", l_ifoOrderUnitParams.getOrderRev());

            String l_changeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("50", l_changeOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "取引時間管理.is市場開局時間帯()！=falseの場合
     * 引数の注文単位.市場から確認済の数量!=NaNの場合
     * 取引時間管理.is取引所休憩時間帯()==falseの場合
     * 注文Rev.の値が最大桁数を超過 2 の場合
     * 抛異常"    "引数の注文単位 != null
     * 引数の注文単位.注文Rev = 99
     * OP注文マネージャ.is内容通知済注文(引数の注文単位)==false(mork實現)
     * 取引時間管理.is市場開局時間帯()==true
     * l_orderUnitRow.getConfirmedQuantity() ==20.0D"  "注文Rev.の値が最大桁数を超過」の例外をthrow
     * BUSINESS_ERROR_02185]"
     */
    public void testGetChangeOrderRev_case005()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //取引時間管理.is取引所休憩時間帯() == false の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("99", l_ifoOrderUnitParams.getOrderRev());

            this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02185, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "取引時間管理.is市場開局時間帯()！=falseの場合
     * 引数の注文単位.市場から確認済の数量!=NaNの場合
     * 取引時間管理.is取引所休憩時間帯()==falseの場合
     * 注文Rev.の値が最大桁数を超過 2 の場合
     * 抛異常"    "引数の注文単位 != null
     * 引数の注文単位.注文Rev = 10
     * OP注文マネージャ.is内容通知済注文(引数の注文単位)==false(mork實現)
     * 取引時間管理.is市場開局時間帯()==true
     * l_orderUnitRow.getConfirmedQuantity() ==20.0D"  
     * return 11
     */
    public void testGetChangeOrderRev_case006()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("10");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //取引時間管理.is取引所休憩時間帯() == false の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("10", l_ifoOrderUnitParams.getOrderRev());

            String l_changeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("11", l_changeOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "取引時間管理.is市場開局時間帯()！=falseの場合
     * 引数の注文単位.市場から確認済の数量!=NaNの場合
     * 取引時間管理.is取引所休憩時間帯()==falseの場合
     * 注文Rev.の値が最大桁数を超過 2 の場合
     * 抛異常"    "引数の注文単位 != null
     * 引数の注文単位.注文Rev = "01"
     * OP注文マネージャ.is内容通知済注文(引数の注文単位)==false(mork實現)
     * 取引時間管理.is市場開局時間帯()==true
     * l_orderUnitRow.getConfirmedQuantity() ==20.0D"  
     * return "02"
     */
    public void testGetChangeOrderRev_case007()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("01");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //取引時間管理.is取引所休憩時間帯() == false の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("01", l_ifoOrderUnitParams.getOrderRev());

            String l_changeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("02", l_changeOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "取引時間管理.is取引所休憩時間帯()==trueの場合
     * 先物OP注文取引キューテーブルを以下の条件で検索
     * 注文Rev.の値が最大桁数を超過 2 の場合
     * ------>
     * 注文Rev.の値が桁数為3 の場合"  "引数の注文単位 != null
     * 注文単位.branchId = 64246
     * Branch表中有branchId為64246的記?
     * 先物OP注文取引キューテーブル中不存在
     * 　@　@　@[条件] 
     * 　@　@　@注文単位.getBranch().証券会社コード  = 10
     * 　@　@かつ  引数の注文単位.getBranch().部店コード  = 624
     * 　@　@かつ　@引数の注文単位.識別コード  = 20
     * 　@　@かつ　@引数の注文単位.注文Rev.  = 100
     * 　@　@かつ　@処理区分 = ""未処理"" "   "注文Rev.の値が最大桁数を超過」の例外をthrow
     * BUSINESS_ERROR_02185]"
     */
    public void testGetChangeOrderRev_case008()//TODO 注文Rev.の値が桁数為3 の場合這種情況 測試不了
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "取引時間管理.is取引所休憩時間帯()==trueの場合
     * 先物OP注文取引キューテーブルを以下の条件で検索
     * 注文Rev.の値が最大桁数を超過 2 の場合
     * ------>
     * 注文Rev.の値が桁数為2 の場合"  "引数の注文単位 != null
     * 注文単位.branchId = 64246
     * Branch表中有branchId為64246的記?
     * 先物OP注文取引キューテーブル中不存在
     * 　@　@　@[条件] 
     * 　@　@　@注文単位.getBranch().証券会社コード  = 10
     * 　@　@かつ  引数の注文単位.getBranch().部店コード  = 624
     * 　@　@かつ　@引数の注文単位.識別コード  = 20
     * 　@　@かつ　@引数の注文単位.注文Rev.  = 99
     * 　@　@かつ　@処理区分 = ""未処理"" "   "注文Rev.の値が最大桁数を超過」の例外をthrow
     * BUSINESS_ERROR_02185]"
     */
    public void testGetChangeOrderRev_case009()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //取引時間管理.is取引所休憩時間帯() == false の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("99", l_ifoOrderUnitParams.getOrderRev());

            this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02185, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "取引時間管理.is取引所休憩時間帯()==trueの場合
     * 先物OP注文取引キューテーブルを以下の条件で検索
     * 注文Rev.の値が最大桁数を不超過 2 の場合
     * ------>
     * 注文Rev.の値が桁数為1 の場合"  "引数の注文単位 != null
     * 注文単位.branchId = 64246
     * Branch表中有branchId為64246的記?
     * 先物OP注文取引キューテーブル中不存在
     * 　@　@　@[条件] 
     * 　@　@　@注文単位.getBranch().証券会社コード  = 10
     * 　@　@かつ  引数の注文単位.getBranch().部店コード  = 624
     * 　@　@かつ　@引数の注文単位.識別コード  = 20
     * 　@　@かつ　@引数の注文単位.注文Rev.  = 01
     * 　@　@かつ　@処理区分 = ""未処理"" "
     * "return "02"
     */
    public void testGetChangeOrderRev_case010()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("01");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //取引時間管理.is市場開局時間帯()==trueの場合（＝引け後／非営業日）
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //取引時間管理.is取引所休憩時間帯() == false の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("01", l_ifoOrderUnitParams.getOrderRev());

            String l_strChangeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);
            assertEquals("02", l_strChangeOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "取引時間管理.is取引所休憩時間帯()==trueの場合
     * 先物OP注文取引キューテーブルを以下の条件で検索  
     * 　@　@○３－２）で該当するレコードが存在する場合 
     * 　@　@　@引数の注文単位.注文Rev.をそのまま返却する。 "   "引数の注文単位 != null
     * 注文単位.branchId = 624
     * Branch表中有branchId為624的記?
     * 先物OP注文取引キューテーブル中存在
     * 　@　@　@[条件] 
     * 　@　@　@注文単位.getBranch().証券会社コード  = 10
     * 　@　@かつ  引数の注文単位.getBranch().部店コード  = 624
     * 　@　@かつ　@引数の注文単位.識別コード  = 20
     * 　@　@かつ　@引数の注文単位.注文Rev.  = 99
     * 　@　@かつ　@処理区分 = ""未処理"" "   返回99    正常
    */
    public void testGetChangeOrderRev_case011()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("12345678901234599890");
            l_hostFotypeOrderAllParams.setStatus("0");
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));


            //取引時間管理.is取引所休憩時間帯() == true の場合
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("99", l_ifoOrderUnitParams.getOrderRev());

            this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("99", l_ifoOrderUnitParams.getOrderRev());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 
     */
    public void testWEB3IfoOrderAllTransactionCallback()
    {
        final String STR_METHOD_NAME = ".testWEB3IfoOrderAllTransactionCallback()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("10624612345678999999");
            l_hostFotypeOrderAllParams.setStatus("0");
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            l_hostFotypeOrderAllParams.setStatus("0");
            l_hostFotypeOrderAllParams.setAccountId(123);

            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            this.l_ifoFrontOrderServiceImpl.lockHostFotypeOrderAll(l_orderUnit);
            boolean l_blnReturn =
                TestDBUtility.isTableLockedSuccessful(HostFotypeOrderAllRow.TYPE);
            assertTrue(l_blnReturn);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数の注文単位 == null  引数の注文単位 == null "パラメータ値不正」の例外をthrow
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetOrderMQDataCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位.発注経路区分==""9：発注停止""の場合
     * 察@看返回?"  引数の注文単位.発注経路区分=="9：発注停止"    nullを返却する
     */
    public void testGetOrderMQDataCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrderMQdataCode =
                this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_orderUnit);
            assertNull(l_strOrderMQdataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 引数の注文単位.発注経路区分==　@"SONAR正系"の場合
     * 引数の注文単位.先物/オプション区分==”先物”の場合 
     * 引数の注文単位.発注経路区分==　@"SONAR正系"
     * 引数の注文単位.先物/オプション区分==1  ”先物”   
     * return ”EI803T”（先物）
     */
    public void testGetOrderMQDataCode_case003()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrderMQdataCode =
                this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_orderUnit);
            assertEquals("EI803T", l_strOrderMQdataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位.発注経路区分==　@""SONAR正系""の場合
     * 引数の注文単位.先物/オプション区分==”先物”以外の場合"  
     * "引数の注文単位.発注経路区分==　@""SONAR正系""引数
     * の注文単位.先物/オプション区分==2  ”OPTION”"  return ”EI801T”（オプション）
     */
    public void testGetOrderMQDataCode_case004()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrderMQdataCode =
                this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_orderUnit);
            assertEquals("EI801T", l_strOrderMQdataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数の注文単位.発注経路区分==　@"フロント副系"の場合 
     * 引数の注文単位.発注経路区分==　@"3フロント副系"  
     * return "EI8X2T"を返却
     */
    public void testGetOrderMQDataCode_case005()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("3");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrderMQdataCode =
                this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_orderUnit);
            assertEquals("EI8X2T", l_strOrderMQdataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数の注文単位.発注経路区分が上記以外の場合は
     * 引数の注文単位.発注経路区分==　@"1：SONAR_SUB_FACTION"
     * nullを返却する
     */
    public void testGetOrderMQDataCode_case006()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrderMQdataCode =
                this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_orderUnit);
            assertNull(l_strOrderMQdataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    /*
     * 引数の注文単位 == null  引数の注文単位 == null "パラメータ値不正」の例外をthrow
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetChangeCancelMQDataCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位.発注経路区分==""9：発注停止""の場合
     * 察@看返回?"  引数の注文単位.発注経路区分=="9：発注停止"    nullを返却する
     */
    public void testGetChangeCancelMQDataCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strChangeCancelMQDataCode =
                this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_orderUnit);
            assertNull(l_strChangeCancelMQDataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * "引数の注文単位.発注経路区分==　@""SONAR正系""の場合
     * 引数の注文単位.先物/オプション区分==”先物”の場合" 
     * "引数の注文単位.発注経路区分==　@""SONAR正系"
     * 引数の注文単位.先物/オプション区分==”先物
     * return "EI804T";
     */
    public void testGetChangeCancelMQDataCode_case003()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strChangeCancelMQDataCode =
                this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_orderUnit);
            assertEquals("EI804T", l_strChangeCancelMQDataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位.発注経路区分==　@""SONAR正系""の場合
     * 引数の注文単位.先物/オプション区分==”オプション”の場合"
     * "引数の注文単位.発注経路区分==　@""SONAR正系""
     * 引数の注文単位.先物/オプション区分==”オプション”
     * return "EI802T";
     */
    public void testGetChangeCancelMQDataCode_case004()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strChangeCancelMQDataCode =
                this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_orderUnit);
            assertEquals("EI802T", l_strChangeCancelMQDataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数の注文単位.発注経路区分==　@"フロント副系"の場合
     * 引数の注文単位.発注経路区分==　@"フロント副系"  
     * return "EI8X2T";
     */
    public void testGetChangeCancelMQDataCode_case005()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("3");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strChangeCancelMQDataCode =
                this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_orderUnit);
            assertEquals("EI8X2T", l_strChangeCancelMQDataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位.発注経路区分が上記以外の場合
     * 引数の注文単位.発注経路区分==　@""SONAR副系""の場合" 
     * 引数の注文単位.発注経路区分==　@"SONAR副系" 
     * return null;
     */
    public void testGetChangeCancelMQDataCode_case006()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strChangeCancelMQDataCode =
                this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_orderUnit);
            assertNull(l_strChangeCancelMQDataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数の注文単位 == null  引数の注文単位 == null "パラメータ値不正」の例外をthrow
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetChangeSubmitOrderRouteDiv_case001()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "Market表中不存在marketId為 
     * 引数の注文単位.getMarketId()的記?"
     * "引数の注文単位.getMarketId() = 666
     * Market表中不存在marketId = 666 的記"
     * テーブルに該当するデータがありません」の例外をthrow[SYSTEM_ERROR_80005]
     */
    public void testGetChangeSubmitOrderRouteDiv_case002()//TODO 測試不可 case 不正確
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

//        try
//        {
//            //注文単位オブジェクト
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1);
//            l_ifoOrderUnitParams.setBranchId(64246);
//            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
//            l_ifoOrderUnitParams.setOrderRev("99");
//            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
//            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setMarketId(666);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingMod =
//                l_finApp.getTradingModule(ProductTypeEnum.IFO);
//            WEB3OptionOrderManagerImpl l_orderMgr =
//                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
//            this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug("Error in WEB3BaseException..." , l_ex);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception...", l_ex);
//            fail();
//        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位.発注経路区分 != ""発注停止""の場合
     * 発注先切替テーブルを検索抛80005異常"   
     * 引数の注文単位.発注経路区分() == 0：SONAR正系
     * " 返回 引数の注文単位.発注経路区分()
     * return 0：SONAR正系"
     */
    public void testGetChangeSubmitOrderRouteDiv_case003()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setMarketId(666);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //市場Row
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(666);

            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            assertEquals("0", l_ifoOrderUnitParams.getSubmitOrderRouteDiv());
            String l_strChangeSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
            assertEquals("0", l_strChangeSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位.発注経路区分 != ""発注停止""の場合
     * 発注先切替テーブルを検索抛80003異常"
     * テーブルに該当するデータがありません」の例外をthrow[SYSTEM_ERROR_80003]
     */
    public void testGetChangeSubmitOrderRouteDiv_case004()//TODO wait
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
//            //注文単位オブジェクト
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1);
//            l_ifoOrderUnitParams.setBranchId(64246);
//            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
//            l_ifoOrderUnitParams.setOrderRev("99");
//            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
//            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
//            l_ifoOrderUnitParams.setMarketId(666);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            //市場Row
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//
//            l_marketParams.setMarketId(666);
//
//            TestDBUtility.insertWithDel(l_marketParams);
//
//            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
//
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingMod =
//                l_finApp.getTradingModule(ProductTypeEnum.IFO);
//            WEB3OptionOrderManagerImpl l_orderMgr =
//                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
//            assertEquals("0", l_ifoOrderUnitParams.getSubmitOrderRouteDiv());
//            String l_strChangeSubmitOrderRouteDiv =
//                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
//            assertEquals("0", l_strChangeSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数の注文単位.訂正取消可能フラ = 0：不可の場合
     * "引数の注文単位.訂正取消可能フラ = 0：不可
     * 引数の注文単位.発注経路区分 = ""0：SONAR正系"
     * "返回 引数の注文単位.発注経路区分 = ""0：SONAR正系""
     */
    public void testGetChangeSubmitOrderRouteDiv_case005()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setMarketId(666);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //市場Row
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(666);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //発注先切替テーブル
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            l_orderSwitchingParams.setMarketCode("1");
            l_orderSwitchingParams.setFrontOrderSystemCode("2");
            l_orderSwitchingParams.setSubmitOrderRouteDiv("0");
            l_orderSwitchingParams.setChangeCancelEnableFlag("0");
            l_orderSwitchingParams.setOrderEngineDiv("0");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(01);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            assertEquals("0", l_ifoOrderUnitParams.getSubmitOrderRouteDiv());
            String l_strChangeSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
            assertEquals("0", l_strChangeSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "発注先切替Row.発注エンジン区分 == ""SONAR""の場合
     * "   "発注先切替Row.発注エンジン区分 == 1：SONAR
     * 引数の注文単位.発注経路区分 = ""0：SONAR正系""" 返回
     * 引数の注文単位.発注経路区分 = "0：SONAR正系"
     */
    public void testGetChangeSubmitOrderRouteDiv_case006()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setMarketId(666);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(666);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //部店オブジェクト
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(01);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //発注先切替テーブル
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            l_orderSwitchingParams.setMarketCode("1");
            l_orderSwitchingParams.setFrontOrderSystemCode("2");
            l_orderSwitchingParams.setSubmitOrderRouteDiv("0");
            l_orderSwitchingParams.setChangeCancelEnableFlag("1");
            l_orderSwitchingParams.setOrderEngineDiv("1");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
//            l_orderSwitchingParams.setValidFlag("1");
//            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            assertEquals("0", l_ifoOrderUnitParams.getSubmitOrderRouteDiv());
            String l_strChangeSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
            assertEquals("0", l_strChangeSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *発注先切替.get有効発注先切替()で戻り値 != nullの場合 
     *"引数の注文単位.getMarketId() = 666，
     *引数の注文単位.getBrandchId() = 64246，
     *引数の注文単位.発注経路区分 = ""0：SONAR正系""
     *1）在Market表中存在MarketId = 666的記?並且marketCode = 1：東京
     *2）在Brandch表中存在BrancdId= 64246的記?，並且在Brandch中的InstitutionCode = ""10""
     *発注先切替テーブル表中有滿足上面條件的記?
     *且発注先切替テーブル表中的発注経路区分=0：SONAR正系
     *"   "戻り値.発注経路区分 を返却する
     *return 0：SONAR正系"
     */
    public void testGetChangeSubmitOrderRouteDiv_case007()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            l_ifoOrderUnitParams.setMarketId(666);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(666);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //発注先切替テーブル
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            l_orderSwitchingParams.setMarketCode("1");
            l_orderSwitchingParams.setFrontOrderSystemCode("2");
            l_orderSwitchingParams.setSubmitOrderRouteDiv("0");
            l_orderSwitchingParams.setChangeCancelEnableFlag("2");
            l_orderSwitchingParams.setOrderEngineDiv("1");
            l_orderSwitchingParams.setValidFlag("1");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            assertEquals("0", l_orderSwitchingParams.getSubmitOrderRouteDiv());
            String l_strChangeSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
            assertEquals("0", l_strChangeSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 発注先切替.get有効発注先切替()で戻り値 == nullの場合
     * "引数の注文単位.getMarketId() = 666，
     * 引数の注文単位.getBrandchId() = 64246，
     * 引数の注文単位.発注経路区分 = ""0：SONAR正系""
     * 1）在Market表中存在MarketId = 666的記?並且marketCode = 1：東京
     * 2）在Brandch表中存在BrancdId= 64246的記?，並且在Brandch中的InstitutionCode = ""10""
     * 発注先切替テーブル表中沒有滿足上面條件的記?"
     * 「発注経路切替対象なし」の例外をthrowする[BUSINESS_ERROR_02216]
     */
    public void testGetChangeSubmitOrderRouteDiv_case008()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            l_ifoOrderUnitParams.setMarketId(666);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(666);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //部店オブジェクト
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //発注先切替テーブル
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            assertEquals("9", l_ifoOrderUnitParams.getSubmitOrderRouteDiv());
            String l_strChangeSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02216, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位 != null
     * 部店オブジェクトを取得"
     * "引数の注文単位 ！= null
     * l_orderUnitRow.getBranchId() == 64246 
     * 在Branch表中存在branchId==64246的記?
     * 引数の注文単位オブジェクト中 銘柄タイプ == 1、識別コード = 123456789、注文Rev = 3 
     * 部店オブジェクト中的 証券会社ID = 10、部店コード = 624" 
     * "証券会社ID＋部店コード＋銘柄タイプ＋識別コード＋注文Rev.＋""999""返回
     * （10624612345678903999）"
     */
    public void testGetOrgCorpCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetOrgCorpCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setConfirmedOrderRev("03");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrgCorpCode =
                this.l_ifoFrontOrderServiceImpl.getOrgCorpCode(l_orderUnit);
            assertEquals("10624612345678903999", l_strOrgCorpCode);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位 != null
     * 抛例外"    "引数の注文単位 ！= null
     * l_orderUnitRow.getBranchId() == 64246 
     * 在Branch表中不存在branchId==64246的記?" 
     * "テーブルに該当するデータがありません」の例外をthrow
     * [SYSTEM_ERROR_80005]"
     */
    public void testGetOrgCorpCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetOrgCorpCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            this.l_ifoFrontOrderServiceImpl.getOrgCorpCode(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数の市場コード＝"1" 返回 1    正常
     */
    public void testGetFrontOrderExchangeCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderExchangeCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strMarketCode = "1";
        String l_strFrontOrderExchangeCod =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderExchangeCode(l_strMarketCode);
        assertEquals("1", l_strFrontOrderExchangeCod);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の市場コード＝""2""（大証）の場合
     * 返回?是否正確。"   引数の市場コード＝"2"
     * 返回 2
     */
    public void testGetFrontOrderExchangeCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderExchangeCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strMarketCode = "2";
        String l_strFrontOrderExchangeCod =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderExchangeCode(l_strMarketCode);
        assertEquals("2", l_strFrontOrderExchangeCod);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の市場コード!＝""1""（東証）&&
     * 引数の市場コード!＝""2""（大証）の場合"
     * 引数の市場コード＝null   返回 null
     */
    public void testGetFrontOrderExchangeCode_case003()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderExchangeCode_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strMarketCode = null;
        String l_strFrontOrderExchangeCod =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderExchangeCode(l_strMarketCode);
        assertNull(l_strFrontOrderExchangeCod);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位 == null
     * 抛例外"    引数の注文単位 == null
     * "パラメータ値不正」の例外をthrow
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetOrderSwitching_case001()
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数の注文単位.発注経路区分=="9：発注停止"の場合
     * 引数の注文単位.発注経路区分==9
     * nullを返却
     */
    public void testGetOrderSwitching_case002()
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            WEB3GentradeOrderSwitching l_orderSwitching =
                this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_orderUnit);

            assertNull(l_orderSwitching);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "市場notfoundの場合
     * 抛例外"    "引数の注文単位 ！= null
     * l_orderUnitRow.getMarketId() == 555555 
     * 在Market表中不存在marketId==555555記?"
     * "テーブルに該当するデータがありません」の例外をthrow
     * [SYSTEM_ERROR_80005]"
     */
    public void testGetOrderSwitching_case003()
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //DeleteMarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数の注文単位 != null
     * branch檢索不到の場合
     * 抛例外"    "引数の注文単位 ！= null
     * l_orderUnitRow.getBranchId() == 64246 
     * 在Branch表中不存在branchId==64246的記?"
     * "テーブルに該当するデータがありません」の例外をthrow
     * [SYSTEM_ERROR_80005]"
     */
    public void testGetOrderSwitching_case004()
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //DeleteMarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(555555);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "発注先切替コンストラクタhrows SYSTEM_ERROR_80003の場合"
     * throws SYSTEM_ERROR_80003の例外
     */
    public void testGetOrderSwitching_case005()//TODO 如@何抛SYSTEM_ERROR_80003
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //DeleteMarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(555555);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //部店オブジェクト
            //部店オブジェクト
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);
//
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingMod =
//                l_finApp.getTradingModule(ProductTypeEnum.IFO);
//            WEB3OptionOrderManagerImpl l_orderMgr =
//                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
//
//            WEB3GentradeOrderSwitching l_orderSwitching =
//                this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_orderUnit);
//            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "発注先切替コンストラクタ
     * throws SYSTEM_ERROR_80005の場合"
     * return null
     */
    public void testGetOrderSwitching_case006()
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //DeleteMarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(555555);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //部店オブジェクト
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setInstitutionCode("10");
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //発注先切替テーブル
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            WEB3GentradeOrderSwitching l_orderSwitching =
                this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_orderUnit);
            assertNull(l_orderSwitching);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * 引数の注文単位（更新前）== null
     * 抛例throw"    "
     * 引数の注文単位（更新前） = null"
     * "パラメータ値不正」の例外をthrow
     * [SYSTEM_ERROR_80017]"
     */
    /*
     * "引数の注文単位 == null
     * 抛例外"    引数の注文単位 == null
     * "パラメータ値不正」の例外をthrow
     * [SYSTEM_ERROR_80017]"
     */
    public void testUpdateHostFotypeOrderAllAtAcceptOvertime_case001()
    {
        final String STR_METHOD_NAME = ".testUpdateHostFotypeOrderAllAtAcceptOvertime_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnitAfter = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
 
            IfoOrderUnit l_orderUnitBefore = null;
            boolean l_blnIsCancel = false;

            this.l_ifoFrontOrderServiceImpl.updateHostFotypeOrderAllAtAcceptOvertime(
                l_orderUnitBefore, l_orderUnitAfter, l_blnIsCancel);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数のis取消==true（取消）の場合
     * 字段是否被正確更新。" "注文単位（更新前）.ifoOrderUnit = 1001
     * 引数の注文単位（更新前）オブジェクト中 銘柄タイプ == 1、識別コード = 123456789、注文Rev = 30
     * 部店オブジェクト中的 証券会社ID = 10、部店コード = 624" "先物OP注文取引キュー Update
     * 処理区分更新前的?為を“1：送信済“
     * -------〉
     * 処理区分更新後的?為""未処理"""
     */
    public void testUpdateHostFotypeOrderAllAtAcceptOvertime_case002()
    {
        final String STR_METHOD_NAME = ".testUpdateHostFotypeOrderAllAtAcceptOvertime_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            l_ifoOrderUnitParams.setConfirmedOrderRev("30");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //部店オブジェクト
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setInstitutionCode("10");
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("10");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("10624612345678930999");
            l_hostFotypeOrderAllParams.setStatus("0");
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            l_hostFotypeOrderAllParams.setStatus("1");
            l_hostFotypeOrderAllParams.setAccountId(123);

            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnitAfter = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
 
            IfoOrderUnit l_orderUnitBefore = null;
            boolean l_blnIsCancel = true;

            log.debug("処理区分更新前為を1：送信済");
            assertEquals("1", l_hostFotypeOrderAllParams.getStatus());

            this.l_ifoFrontOrderServiceImpl.updateHostFotypeOrderAllAtAcceptOvertime(
                l_orderUnitBefore, l_orderUnitAfter, l_blnIsCancel);

            List l_lisUpdateAfterRows =
                HostFotypeOrderAllDao.findRowsByAccountIdOrderRequestNumber(
                    new Long(123), "123456789");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisUpdateAfterRows.get(0);

            log.debug("処理区分更新後為 未処理");            
            assertEquals("0", l_row.getStatus());
            log.debug("getLastUpdatedTimestamp" + l_row.getLastUpdatedTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "引数のis取消==false（取消以外）の場合
     *  字段是否被正確更新。
     *  引数の注文単位（更新前）オブジェクト中 銘柄タイプ == 1、識別コード = 123456789、注文Rev = 30 
     *  部店オブジェクト中的 証券会社ID = 10、部店コード = 624
     *  引数の注文単位（更新後）オブジェクト中 銘柄タイプ == 1、識別コード = 123456789、注文Rev = 30
     *  部店オブジェクト中的 証券会社ID = 11、部店コード = 624
     *  "   "先物OP注文取引キュー Update
     *  社内処理項目更新前：“11111111111”
     *   社内処理項目：　@this.get社内処理項目(引数の注文単位（更新後）)の戻り値
     *   証券会社ID＋部店コード＋銘柄タイプ＋識別コード＋注文Rev.＋""999""返回
     *  （11624123999）
     *   処理区分更新前的?為を“1：送信済“
     *   -------〉
     *  処理区分更新後的?為""未処理"""
     *                                                    
     */
    public void testUpdateHostFotypeOrderAllAtAcceptOvertime_case003()
    {
        final String STR_METHOD_NAME = ".testUpdateHostFotypeOrderAllAtAcceptOvertime_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //注文単位オブジェクト(Before)
            IfoOrderUnitParams l_beforeIfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_beforeIfoOrderUnitParams.setOrderUnitId(1);
            l_beforeIfoOrderUnitParams.setBranchId(64246);
            l_beforeIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_beforeIfoOrderUnitParams.setOrderRequestNumber("123456789");
            l_beforeIfoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_beforeIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_beforeIfoOrderUnitParams.setMarketId(555555);
            l_beforeIfoOrderUnitParams.setSubmitOrderRouteDiv("9");
            l_beforeIfoOrderUnitParams.setOrderRev("30");
            TestDBUtility.insertWithDel(l_beforeIfoOrderUnitParams);

            l_beforeIfoOrderUnitParams.setOrderUnitId(2);
            l_beforeIfoOrderUnitParams.setOrderRev("99");
            l_beforeIfoOrderUnitParams.setAccountId(10010002L);
            TestDBUtility.insertWithDel(l_beforeIfoOrderUnitParams);

            //部店オブジェクト
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setInstitutionCode("10");
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //先物OP注文キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("10");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("10624612345678999999");
            l_hostFotypeOrderAllParams.setStatus("0");
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            l_hostFotypeOrderAllParams.setStatus("1");
            l_hostFotypeOrderAllParams.setAccountId(123);

            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnitAfter = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            IfoOrderUnit l_orderUnitBefore = (IfoOrderUnit)l_orderMgr.getOrderUnit(2);
            boolean l_blnIsCancel = false;

            log.debug("処理区分更新前為を1：送信済");
            assertEquals("1", l_hostFotypeOrderAllParams.getStatus());
            assertEquals("10624612345678999999", l_hostFotypeOrderAllParams.getCorpCode());
            this.l_ifoFrontOrderServiceImpl.updateHostFotypeOrderAllAtAcceptOvertime(
                l_orderUnitAfter, l_orderUnitBefore, l_blnIsCancel);

            List l_lisUpdateAfterRows =
                HostFotypeOrderAllDao.findRowsByAccountIdOrderRequestNumber(
                    new Long(123), "123456789");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisUpdateAfterRows.get(0);

            log.debug("処理区分更新後為 未処理");            
            assertEquals("0", l_row.getStatus());
            assertEquals("10624612345678930999", l_row.getCorpCode());
            log.debug("getLastUpdatedTimestamp" + l_row.getLastUpdatedTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * 注文Rev.の値為1桁場合是否會前補0"
     * 引数の注文Rev = "8"  返回?為"09"
     */
    public void testGetNextOrderRev_case001()
    {
        final String STR_METHOD_NAME = ".testGetNextOrderRev_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strOrderRev = "8";

            String l_strNextOrderRev =
                this.l_ifoFrontOrderServiceImpl.getNextOrderRev(l_strOrderRev);

            assertEquals("09", l_strNextOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * 引数の注文Rev = "09"  返回?為"10"
     */
    public void testGetNextOrderRev_case002()
    {
        final String STR_METHOD_NAME = ".testGetNextOrderRev_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strOrderRev = "09";

            String l_strNextOrderRev =
                this.l_ifoFrontOrderServiceImpl.getNextOrderRev(l_strOrderRev);

            assertEquals("10", l_strNextOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * 引数の注文Rev = "09"  返回?為"10"
     */
    public void testGetNextOrderRev_case003()
    {
        final String STR_METHOD_NAME = ".testGetNextOrderRev_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strOrderRev = "09";

            String l_strNextOrderRev =
                this.l_ifoFrontOrderServiceImpl.getNextOrderRev(l_strOrderRev);

            assertEquals("10", l_strNextOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * 注文Rev.の値為2桁同時注文Rev.+1為3桁的場合是否正常返回"
     * "引数の注文Rev = ""13""
     * "   返回?為"14"
     */
    public void testGetNextOrderRev_case004()
    {
        final String STR_METHOD_NAME = ".testGetNextOrderRev_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strOrderRev = "13";

            String l_strNextOrderRev =
                this.l_ifoFrontOrderServiceImpl.getNextOrderRev(l_strOrderRev);

            assertEquals("14", l_strNextOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *注文Rev.の値為2桁同時注文Rev.+1為3桁的場合是否會出錯"
     *"引数の注文Rev = ""99""
     *"   "「注文Rev.の値が最大桁数を超過」の例外をthrow
     *[BUSINESS_ERROR_02185]"
     */
    public void testGetNextOrderRev_case005()
    {
        final String STR_METHOD_NAME = ".testGetNextOrderRev_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strOrderRev = "99";

            this.l_ifoFrontOrderServiceImpl.getNextOrderRev(l_strOrderRev);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02185, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
