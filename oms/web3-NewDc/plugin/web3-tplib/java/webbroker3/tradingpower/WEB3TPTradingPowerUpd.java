head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerUpd.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 余力更新サービス(WEB3TPTradingPowerUpd.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/8/03 劉 (fitechlabs) 新規作成
                    2006/09/11 徐宏偉 (中訊) モデルNo.011
                    2007/01/22 李木子 (中訊) モデルNo.092
 Revesion History : 2007/07/28 崔遠鵬 (中訊) モデルNo.115
 Revesion History : 2007/09/28 孟亞南 (中訊) モデルNo.178                   
 Revesion History : 2008/01/23 孟亞南 (中訊) モデルNo.232 233
 Revesion History : 2008/01/31 トウ鋒鋼 (中訊) モデルNo.252
 Revesion History : 2008/04/01 崔遠鵬 (中訊) モデルNo.267 268 269 270
 Revesion History : 2008/10/20 陸文静 (中訊) モデルNo.326
 Revesion History : 2008/11/26 張少傑 (中訊) モデルNo.374
 Revesion History : 2009/12/24 武波 (中訊) モデルNo.432
 */
package webbroker3.tradingpower;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CashoutTodayDepositTransferDivDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationPerProduct;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuation;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryDayTradeSwapContract;
import webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryOpenContract;
import webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryUndeliveredContract;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlement;
import webbroker3.util.WEB3DateUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * (余力更新サービス)<BR>
 * <BR>
 * 余力更新サービス実装クラス<BR>
 *
 * @@author 劉 (fitechlabs)
 * @@version 1.0
 */
public class WEB3TPTradingPowerUpd
{

    /**
     * 顧客属性 <BR>
     */
    protected WEB3TPAccountInfo accountInfo;

    /**
     * 計算条件 <BR>
     */
    protected WEB3TPCalcCondition calcCondition;

    /**
     * 余力計算時使用する現注文内容 <BR>
     */
    protected WEB3TPNewOrderSpec[] newOrderSpecs;

    /**
     * 証券評価額 <BR>
     */
    protected WEB3TPSecurityValuation securityValuation;

    /**
     * 建玉情報 <BR>
     */
    protected WEB3TPContractInfo contractInfo;

    /**
     * 総資金 <BR>
     */
    protected WEB3TPCashValuation cashValuation;

    /**
     * 差金決済 <BR>
     */
    protected WEB3TPSettlement settlement;

    /**
     * @@roseuid 410DF8980247
     */
    public WEB3TPTradingPowerUpd()
    {

    }

    /**
     * （余力更新サービス）<BR>
     *
     * 余力更新サービスコンストラクタ<BR>
     * <BR>
     * <BR>
     * シーケンス図「余力更新サービスコンストラクト」参照<BR>
     *
     * @@param l_lngAccountId - 口座ID
     * @@param l_blnMarginFlag - 信用顧客フラグ
     * @@param l_calcCondition - 計算条件
     * @@param l_newOrderSpecs - 現注文内容
     * @@roseuid 410DDDBE00AC
     */
    public WEB3TPTradingPowerUpd(long l_lngAccountId, boolean l_blnMarginFlag,
                                 WEB3TPCalcCondition l_calcCondition,
                                 WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {

        //　@1.1 顧客属性(口座ID : long, 信用顧客フラグ : boolean) -- 顧客属性作成
        WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
            l_lngAccountId,
            l_blnMarginFlag);
        this.accountInfo = l_accountInfo;
        this.calcCondition = l_calcCondition;
        this.newOrderSpecs = l_newOrderSpecs;

        List l_todaysEquityOrders = WEB3TPPersistentDataManager.getInstance().getTodaysEquityOrders(
            l_accountInfo, l_calcCondition);
        
        // 1.2 create建玉情報(顧客属性 : 顧客属性, 計算条件 : 計算条件, 現注文内容 : 現注文内容[])
        // --建玉情報作成
        WEB3TPContractInfo l_contractInfo = WEB3TPContractInfo.create(this.accountInfo,
            this.calcCondition, this.newOrderSpecs);
        l_contractInfo.setTodaysEquityOrders(l_todaysEquityOrders);
        this.contractInfo = l_contractInfo;
        // 1.3 do建玉情報ロード( ) --建玉情報ロード
        this.contractInfo.loadContractInfo();

        // 1.4 create証券評価(顧客属性 : 顧客属性, 計算条件 : 計算条件, 現注文内容 : 現注文内容[], 建玉情報 : 建玉情報)
        // --証券評価作成
        WEB3TPSecurityValuation l_securityValuation = WEB3TPSecurityValuation.
            create(this.accountInfo,
                   this.calcCondition, this.newOrderSpecs, l_contractInfo);
        l_securityValuation.setTodaysEquityOrders(l_todaysEquityOrders);
        this.securityValuation = l_securityValuation;
        // 1.5 do銘柄ごと証券評価額ロード( )--銘柄ごと証券評価額ロード
        this.securityValuation.doSecurityValuationPerProductLoad();

        // 1.6 create総資金((顧客属性 : 顧客属性, 計算条件 : 計算条件, 現注文内容 : 現注文内容[]))
        // --総資金作成
        WEB3TPCashValuation l_cashValuation = WEB3TPCashValuation.create(
            this.accountInfo,
            this.calcCondition, this.newOrderSpecs);
        l_cashValuation.setTodaysEquityOrders(l_todaysEquityOrders);
        this.cashValuation = l_cashValuation;
        // 1.7 do総資金ロード( )--総資金ロード
        this.cashValuation.loadAll();

        // 1.8 差金決済
        this.settlement = new WEB3TPSettlement(this.cashValuation, this.securityValuation,
                                               this.calcCondition);
    }

    /**
     * (calc余力更新内容<現物顧客>）<BR>
     *<BR>
     * 現物顧客の余力更新内容を計算する<BR>
     * <BR>
     * シーケンス図「calc余力更新内容<現物顧客>」参照<BR>
     * @@return java.util.List
     * @@roseuid 410DDDBE00B3
     */
    public List calcTradingpowerUpdResultEquity()
    {
        //余力計算結果
        TpCalcResultEquityParams l_params = new
            TpCalcResultEquityParams();

        //余力計算結果詳細
        TpCalcResultEquityDetailParams l_paramsDetail = new
        TpCalcResultEquityDetailParams();

        //T+0とT+5を取得する
        Date l_datBizDate0 = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datBizDate5 = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5);
        
        //1.1.口座ID
        l_params.setAccountId(this.accountInfo.getAccountId());
        l_paramsDetail.setAccountId(this.accountInfo.getAccountId());

        // 1.3.　@指定日=T+0～T+5までループする
        for (int i = WEB3TPSpecifiedPointDef.T_0; i <= WEB3TPSpecifiedPointDef.T_5; i++)
        {
            //1.3.1.get営業日(int)
            Date l_datBizDate = this.calcCondition.getBizDate(i);

            // 1.3.2　@get預り金残高(指定日 : Date)
            double l_dblAccountBalance = this.cashValuation.calcCashBalance(
                l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setAccountBalance0(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setAccountBalance1(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setAccountBalance2(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setAccountBalance3(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setAccountBalance4(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setAccountBalance5(l_dblAccountBalance);
                    break;
                default:
                    break;
            }

            // 1.3.3　@get当日未約定代金(指定日 : Date)
            double l_dblTodaysUnexecutedTotal = this.cashValuation.
                calcTodaysUnexecutedTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setTodayUnexecutedAmount0(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setTodayUnexecutedAmount1(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setTodayUnexecutedAmount2(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setTodayUnexecutedAmount3(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setTodayUnexecutedAmount4(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setTodayUnexecutedAmount5(l_dblTodaysUnexecutedTotal);
                    break;
                default:
                    break;
            }

            // 1.3.4　@get当日約定済代金(指定日 : Date)
            double l_dblTodaysExecutedTotal = this.cashValuation.
                calcTodaysExecutedTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setTodayExecutedAmount0(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setTodayExecutedAmount1(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setTodayExecutedAmount2(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setTodayExecutedAmount3(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setTodayExecutedAmount4(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setTodayExecutedAmount5(l_dblTodaysExecutedTotal);
                    break;
                default:
                    break;
            }


            // 1.3.5　@getその他拘束金(指定日 : Date)
            double l_dblOtherRestraintsTotal = this.cashValuation.
                calcOtherRestraintsTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setOtherRestraint0(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setOtherRestraint1(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setOtherRestraint2(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setOtherRestraint3(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setOtherRestraint4(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setOtherRestraint5(l_dblOtherRestraintsTotal);
                    break;
                default:
                    break;
            }
            
            //1.3.6 calc預り金担保出金拘束金(Date)
            //預り金担保出金拘束金を取得する。 
            //[引数] 
            //Date = get営業日()の戻り値 
            double l_dblCashDepositRestraint = 
                this.cashValuation.caleCashDepositRestraint(l_datBizDate);
            
            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setCashDepositRestraint0(l_dblCashDepositRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setCashDepositRestraint1(l_dblCashDepositRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setCashDepositRestraint2(l_dblCashDepositRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setCashDepositRestraint3(l_dblCashDepositRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setCashDepositRestraint4(l_dblCashDepositRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setCashDepositRestraint5(l_dblCashDepositRestraint);
                    break;
                default:
                    break;
            }
            
            // 1.3.7　@get日計り拘束金(指定日 : Date)
            double l_dblDateTradeRestraint = this.settlement.
                getDayTradeRestraint(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setDayTradeRestraint0(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setDayTradeRestraint1(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setDayTradeRestraint2(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setDayTradeRestraint3(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setDayTradeRestraint4(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setDayTradeRestraint5(l_dblDateTradeRestraint);
                    break;
                default:
                    break;
            }
            
            // 1.3.8　@calc預り証券評価額(指定日 : Date)
            double l_dblValuationPrice = this.securityValuation.calcValuationPrice(
                l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setTrustSecurityAsset0(l_dblValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setTrustSecurityAsset1(l_dblValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setTrustSecurityAsset2(l_dblValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setTrustSecurityAsset3(l_dblValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setTrustSecurityAsset4(l_dblValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setTrustSecurityAsset5(l_dblValuationPrice);
                    break;
                default:
                    break;
            }

            // 1.3.9　@calc出金額(指定日 : Date)
            double l_dblCashOut = this.cashValuation.
                calcCashOut(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_paramsDetail.setPaymentAmountDesignate0(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_paramsDetail.setPaymentAmountDesignate1(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_paramsDetail.setPaymentAmountDesignate2(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_paramsDetail.setPaymentAmountDesignate3(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_paramsDetail.setPaymentAmountDesignate4(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_paramsDetail.setPaymentAmountDesignate5(l_dblCashOut);
                    break;
                default:
                    break;
            }
            
            //1.3.10. T+0 又は T+5の時実行(受残と約残)
            if((WEB3DateUtility.compareToDay(l_datBizDate0, l_datBizDate) == 0)
                    || (WEB3DateUtility.compareToDay(l_datBizDate5, l_datBizDate) == 0))
            {
                //1.3.10.1.calc預り資産評価額(指定日 : Date)
                // 株式評価額
                double l_dblEquityAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.EQUITY, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //1.3.10.2. 株式ミニ投資評価額
                double l_dblMiniStockAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.EQUITY, WEB3MiniStockDivDef.MINI_STOCK);
                //1.3.10.3. 累積投資評価額
                double l_dblRuitoAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.RUITO, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //1.3.10.4. 投資信託評価額
                double l_dblMutualFundAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.MUTUAL_FUND, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //1.3.10.5. 債券評価額
                double l_dblBondAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.BOND, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //外国株
                double l_dblFeqAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.FOREIGN_EQUITY, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                switch (i)
                {
                    case WEB3TPSpecifiedPointDef.T_0:
                        l_paramsDetail.setEquityAssetDelivered(l_dblEquityAsset);
                        l_paramsDetail.setMiniStockAssetDelivered(l_dblMiniStockAsset);
                        l_paramsDetail.setRuitoAssetDelivered(l_dblRuitoAsset);
                        l_paramsDetail.setMutualFundAssetDelivered(l_dblMutualFundAsset);
                        l_paramsDetail.setBondAssetDelivered(l_dblBondAsset);
                        l_paramsDetail.setForeignEquityAssetDelivered(l_dblFeqAsset);
                        break;
                    case WEB3TPSpecifiedPointDef.T_5:
                        l_paramsDetail.setEquityAssetExecuted(l_dblEquityAsset);
                        l_paramsDetail.setMiniStockAssetExecuted(l_dblMiniStockAsset);
                        l_paramsDetail.setRuitoAssetExecuted(l_dblRuitoAsset);
                        l_paramsDetail.setMutualFundAssetExecuted(l_dblMutualFundAsset);
                        l_paramsDetail.setBondAssetExecuted(l_dblBondAsset);
                        l_paramsDetail.setForeignEquityAssetExecuted(l_dblFeqAsset);
                        break;
                    default:
                        break;
                }
            }

        }

        Timestamp l_tsNow = this.calcCondition.getSystemTimeStamp();
        l_params.setCreatedTimestamp(l_tsNow);
        l_params.setLastUpdatedTimestamp(l_tsNow);
        l_paramsDetail.setCreatedTimestamp(l_tsNow);
        l_paramsDetail.setLastUpdatedTimestamp(l_tsNow);

        List l_list = new ArrayList();
        l_list.add(l_params);
        l_list.add(l_paramsDetail);
        return l_list;
    }

    /**
     * (calc余力更新内容<信用顧客>）
     *
     * 信用顧客の余力更新内容を計算する<BR>
     * <BR>
     * シーケンス図「calc余力更新内容<信用顧客>」参照<BR>
     * @@param l_strMarkToMarketDiv - 値洗い区分
     * @@return java.util.List
     * @@roseuid 410DDDBE00B4
     */
    public List calcTradingpowerUpdResultMargin(String l_strMarkToMarketDiv)
    {

        //余力計算結果
        TpCalcResultMarginParams l_params = new
            TpCalcResultMarginParams();

        //余力計算結果詳細
        TpCalcResultMarginDetailParams l_paramsDetail = new
        TpCalcResultMarginDetailParams();

        //T+0とT+5を取得する
        Date l_datBizDate0 = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datBizDate5 = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5);

        //口座ID
        l_params.setAccountId(this.accountInfo.getAccountId());
        l_paramsDetail.setAccountId(this.accountInfo.getAccountId());

        // 1.1　@指定日=T+0～T+5までループする
        for (int i = 0; i <= 5; i++)
        {

            Date l_datBizDate = this.calcCondition.getBizDate(i);

            // 1.1.1　@get預り金残高(指定日 : Date)
            double l_dblAccountBalance = this.cashValuation.calcCashBalance(
                l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setAccountBalance0(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setAccountBalance1(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setAccountBalance2(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setAccountBalance3(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setAccountBalance4(l_dblAccountBalance);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setAccountBalance5(l_dblAccountBalance);
                    break;
                default:
                    break;
            }

            // 1.1.2　@get当日未約定代金(指定日 : Date)
            double l_dblTodaysUnexecutedTotal = this.cashValuation.
                calcTodaysUnexecutedTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setTodayUnexecutedAmount0(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setTodayUnexecutedAmount1(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setTodayUnexecutedAmount2(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setTodayUnexecutedAmount3(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setTodayUnexecutedAmount4(l_dblTodaysUnexecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setTodayUnexecutedAmount5(l_dblTodaysUnexecutedTotal);
                    break;
                default:
                    break;
            }

            // 1.1.3　@get当日約定済代金(指定日 : Date)
            double l_dblTodaysExecutedTotal = this.cashValuation.
                calcTodaysExecutedTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setTodayExecutedAmount0(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setTodayExecutedAmount1(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setTodayExecutedAmount2(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setTodayExecutedAmount3(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setTodayExecutedAmount4(l_dblTodaysExecutedTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setTodayExecutedAmount5(l_dblTodaysExecutedTotal);
                    break;
                default:
                    break;
            }

            // 1.1.4　@get日計り拘束金(指定日 : Date)
            double l_dblDateTradeRestraint = this.settlement.
                getDayTradeRestraint(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setDayTradeRestraint0(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setDayTradeRestraint1(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setDayTradeRestraint2(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setDayTradeRestraint3(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setDayTradeRestraint4(l_dblDateTradeRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setDayTradeRestraint5(l_dblDateTradeRestraint);
                    break;
                default:
                    break;
            }

            // 1.1.5　@getその他拘束金(指定日 : Date)
            double l_dblOtherRestraintsTotal = this.cashValuation.
                calcOtherRestraintsTotal(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setOtherRestraint0(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setOtherRestraint1(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setOtherRestraint2(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setOtherRestraint3(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setOtherRestraint4(l_dblOtherRestraintsTotal);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setOtherRestraint5(l_dblOtherRestraintsTotal);
                    break;
                default:
                    break;
            }

            // 1.1.6　@get未決済建玉の集計(指定日 : Date)
            WEB3TPSummaryOpenContract l_openContract = this.
                contractInfo.getSummaryOpenContract(l_datBizDate);
            //純粋未決済建玉代金
            double l_dblContractAmount = l_openContract.getContractAmount();
            //純粋未決済必要保証金
            double l_dblMarginDeposit = l_openContract.getMarginDeposit();
            //純粋未決済現金必要保証金
            double l_dblCashMarginDeposit = l_openContract.getCashMarginDeposit();
            //発注分建玉代金
            double l_dblUnExecContractAmount = l_openContract.getUnExecContractAmount();
            //発注分必要保証金
            double l_dblUnExecMarginDeposit = l_openContract.getUnExecMarginDeposit();
            //発注分現金必要保証金
            double l_dblUnExecCashMarginDeposit = l_openContract.getUnExecCashMarginDeposit();
            //評価損
            double l_dblAssetLoss = l_openContract.getAssetLoss();
            //評価益
            double l_dblAssetProfit = l_openContract.getAssetProfit();
            //建手数料
            double l_dblSetupFee = l_openContract.getSetupFee();
            //日歩・逆日歩損
            double l_dblInterestLoss = l_openContract.getInterestLoss();
            //日歩・逆日歩益
            double l_dblInterestProfit = l_openContract.getInterestProfit();
            //その他建玉諸経費
            double l_dblOtherCost = l_openContract.getOtherCost();

            //計算結果テーブルが必要なのは、純粋未決済建玉 + 発注分建玉
            double l_dblContractAmountDB = l_dblContractAmount + l_dblUnExecContractAmount;
            double l_dblMarginDepositDB = l_dblMarginDeposit + l_dblUnExecMarginDeposit;
            double l_dblCashMarginDepositDB = l_dblCashMarginDeposit + l_dblUnExecCashMarginDeposit;
            
            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setContractAmount0(l_dblContractAmountDB);
                    l_params.setMarginDeposit0(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit0(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount0(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit0(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit0(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost(l_dblOtherCost);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setContractAmount1(l_dblContractAmountDB);
                    l_params.setMarginDeposit1(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit1(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss1(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost1(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount1(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit1(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit1(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss1(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit1(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee1(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss1(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit1(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost1(l_dblOtherCost);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setContractAmount2(l_dblContractAmountDB);
                    l_params.setMarginDeposit2(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit2(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss2(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost2(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount2(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit2(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit2(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss2(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit2(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee2(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss2(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit2(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost2(l_dblOtherCost);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setContractAmount3(l_dblContractAmountDB);
                    l_params.setMarginDeposit3(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit3(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss3(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost3(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount3(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit3(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit3(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss3(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit3(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee3(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss3(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit3(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost3(l_dblOtherCost);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setContractAmount4(l_dblContractAmountDB);
                    l_params.setMarginDeposit4(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit4(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss4(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost4(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount4(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit4(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit4(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss4(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit4(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee4(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss4(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit4(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost4(l_dblOtherCost);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setContractAmount5(l_dblContractAmountDB);
                    l_params.setMarginDeposit5(l_dblMarginDepositDB);
                    l_params.setCashMarginDeposit5(l_dblCashMarginDepositDB);
                    l_params.setContractAssetProfitLoss5(l_dblAssetProfit - l_dblAssetLoss);
                    l_params.setContractTotalCost5(l_dblSetupFee + l_dblInterestLoss + l_dblOtherCost);
                    l_paramsDetail.setUnexecContractAmount5(l_dblUnExecContractAmount);
                    l_paramsDetail.setUnexecMarginDeposit5(l_dblUnExecMarginDeposit);
                    l_paramsDetail.setUnexecCashMarginDeposit5(l_dblUnExecCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss5(l_dblAssetLoss);
                    l_paramsDetail.setContractAssetProfit5(l_dblAssetProfit);
                    l_paramsDetail.setSetupFee5(l_dblSetupFee);
                    l_paramsDetail.setContractInterestLoss5(l_dblInterestLoss);
                    l_paramsDetail.setContractInterestProfit5(l_dblInterestProfit);
                    l_paramsDetail.setContractOtherCost5(l_dblOtherCost);
                    break;
                default:
                    break;
            }

            // 1.1.7　@get未受渡建玉の集計(指定日 : Date)
            WEB3TPSummaryUndeliveredContract l_undeliveredContract = this.
                contractInfo.getSummaryUndeliveredContract(l_datBizDate);
            //未受渡建玉代金
            double l_dblUndeliveredContractAmount = l_undeliveredContract.
                getContractAmount();
            //未受渡建玉決済損
            double l_dblUndeliveredContractLoss = l_undeliveredContract.
                getContractLoss();
            //未受渡建玉決済益
            double l_dblUndeliveredContractProfit = l_undeliveredContract.
                getContractProfit();
            //未受渡建玉必要保証金
            double l_dblUndeliMarginDeposit = l_undeliveredContract.getMarginDeposit();
            //未受渡建玉現金必要保証金
            double l_dblUndeliCashMarginDeposit = l_undeliveredContract.
                getCashMarginDeposit();
            //建玉決済損<当日>
            double l_dblTodayRepayContractLoss = l_undeliveredContract.getTodayRepayContractLoss();
            //建玉決済益<当日>
            double l_dblTodayRepayContractProfit = l_undeliveredContract.getTodayRepayContractProfit();
            //決済建玉前日価格評価<当日>
            double l_dblTodayRepayContractPreAsset = l_undeliveredContract.getTodayRepayContractPrevAsset();
            //建玉決済損<指定日>
            double l_dblDesignateContractLoss = l_undeliveredContract.getDesignateDateContractLoss();
            //建玉決済益<指定日>
            double l_dblDesignateContractProfit = l_undeliveredContract.getDesignateDateContractProfit();

            //get日計り返済・現引現渡建玉の集計(指定日 : Date)
            WEB3TPSummaryDayTradeSwapContract l_dayTradeSwapContract =
                this.contractInfo.getSummaryDayTradeSwapContract(l_datBizDate);

            //現引現渡建玉決済損
            BigDecimal l_bdSwapContractSettleLoss =
                new BigDecimal(Double.toString(l_dayTradeSwapContract.getSwapContractSettleLoss()));
            
            BigDecimal l_bdUndeliveredContractLoss =
                new BigDecimal(Double.toString(l_dblUndeliveredContractLoss));

            double l_dblUndeliContractLoss =
                l_bdUndeliveredContractLoss.add(l_bdSwapContractSettleLoss).doubleValue();

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setUndeliContractAmount0(l_dblUndeliveredContractAmount);
                    l_params.setUndeliContractLoss0(l_dblUndeliContractLoss);
                    l_params.setUndeliContractProfit0(l_dblUndeliveredContractProfit);
                    l_params.setUndeliMarginDeposit0(l_dblUndeliMarginDeposit);
                    l_params.setUndeliCashMarginDeposit0(l_dblUndeliCashMarginDeposit);
                    l_paramsDetail.setTodayRepayContractLoss(l_dblTodayRepayContractLoss);
                    l_paramsDetail.setTodayRepayContractProfit(l_dblTodayRepayContractProfit);
                    l_paramsDetail.setTodayRepayContractPreAsset(l_dblTodayRepayContractPreAsset);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setUndeliContractAmount1(l_dblUndeliveredContractAmount);
                    l_params.setUndeliContractLoss1(l_dblUndeliContractLoss);
                    l_params.setUndeliContractProfit1(l_dblUndeliveredContractProfit);
                    l_params.setUndeliMarginDeposit1(l_dblUndeliMarginDeposit);
                    l_params.setUndeliCashMarginDeposit1(l_dblUndeliCashMarginDeposit);
                    l_paramsDetail.setContractLossDesignate1(l_dblDesignateContractLoss);
                    l_paramsDetail.setContractProfitDesignate1(l_dblDesignateContractProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setUndeliContractAmount2(l_dblUndeliveredContractAmount);
                    l_params.setUndeliContractLoss2(l_dblUndeliContractLoss);
                    l_params.setUndeliContractProfit2(l_dblUndeliveredContractProfit);
                    l_params.setUndeliMarginDeposit2(l_dblUndeliMarginDeposit);
                    l_params.setUndeliCashMarginDeposit2(l_dblUndeliCashMarginDeposit);
                    l_paramsDetail.setContractLossDesignate2(l_dblDesignateContractLoss);
                    l_paramsDetail.setContractProfitDesignate2(l_dblDesignateContractProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setUndeliContractAmount3(l_dblUndeliveredContractAmount);
                    l_params.setUndeliContractLoss3(l_dblUndeliContractLoss);
                    l_params.setUndeliContractProfit3(l_dblUndeliveredContractProfit);
                    l_params.setUndeliMarginDeposit3(l_dblUndeliMarginDeposit);
                    l_params.setUndeliCashMarginDeposit3(l_dblUndeliCashMarginDeposit);
                    l_paramsDetail.setContractLossDesignate3(l_dblDesignateContractLoss);
                    l_paramsDetail.setContractProfitDesignate3(l_dblDesignateContractProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_paramsDetail.setContractLossDesignate4(l_dblDesignateContractLoss);
                    l_paramsDetail.setContractProfitDesignate4(l_dblDesignateContractProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_paramsDetail.setContractLossDesignate5(l_dblDesignateContractLoss);
                    l_paramsDetail.setContractProfitDesignate5(l_dblDesignateContractProfit);
                    break;
                default:
                    break;
            }

            //日計り返済・現引現渡建玉代金
            double l_dblDayRepayContractAmount = l_dayTradeSwapContract.getContractAmount();
            //日計り返済・現引現渡建玉必要保証金
            double l_dblDayRepayMarginDeposit = l_dayTradeSwapContract.getMarginDeposit();
            //日計り返済・現引現渡建玉現金必要保証金
            double l_dblDayRepayCashMarginDeposit = l_dayTradeSwapContract.getCashMarginDeposit();

            //現引現渡建玉評価損
            double l_dblSwapLoss = l_dayTradeSwapContract.getSwapContractAssetLoss();
            //現引現渡建玉評価益
            double l_dblSwapProfit = l_dayTradeSwapContract.getSwapContractAssetProfit();
            
            //必要保証金（MarginDeposit）= 未決済建玉必要保証金＋日計り返済・現引現渡建玉必要保証金
            //必要現金保証金（CashMarginDeposit）= 未決済建玉必要現金保証金＋日計り返済・現引現渡建玉必要現金保証金
            //未決済建玉評価損益 = 未決済建玉評価損益＋現引現渡建玉評価益 - 現引現渡建玉評価損
            //未決済建玉評価益 = 未決済建玉評価益＋現引現渡建玉評価益
            //未決済建玉評価損 = 未決済建玉評価損＋現引現渡建玉評価損
            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setDayRepayContractAmount0(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit0(l_params.getMarginDeposit0() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit0(l_params.getCashMarginDeposit0() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss(l_params.getContractAssetProfitLoss() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit0(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit0(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss(l_paramsDetail.getContractAssetLoss() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit(l_paramsDetail.getContractAssetProfit() + l_dblSwapProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setDayRepayContractAmount1(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit1(l_params.getMarginDeposit1() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit1(l_params.getCashMarginDeposit1() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss1(l_params.getContractAssetProfitLoss1() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit1(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit1(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss1(l_paramsDetail.getContractAssetLoss1() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit1(l_paramsDetail.getContractAssetProfit1() + l_dblSwapProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setDayRepayContractAmount2(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit2(l_params.getMarginDeposit2() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit2(l_params.getCashMarginDeposit2() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss2(l_params.getContractAssetProfitLoss2() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit2(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit2(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss2(l_paramsDetail.getContractAssetLoss2() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit2(l_paramsDetail.getContractAssetProfit2() + l_dblSwapProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setDayRepayContractAmount3(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit3(l_params.getMarginDeposit3() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit3(l_params.getCashMarginDeposit3() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss3(l_params.getContractAssetProfitLoss3() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit3(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit3(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss3(l_paramsDetail.getContractAssetLoss3() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit3(l_paramsDetail.getContractAssetProfit3() + l_dblSwapProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setDayRepayContractAmount4(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit4(l_params.getMarginDeposit4() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit4(l_params.getCashMarginDeposit4() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss4(l_params.getContractAssetProfitLoss4() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit4(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit4(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss4(l_paramsDetail.getContractAssetLoss4() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit4(l_paramsDetail.getContractAssetProfit4() + l_dblSwapProfit);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setDayRepayContractAmount5(l_dblDayRepayContractAmount);
                    l_params.setMarginDeposit5(l_params.getMarginDeposit5() + l_dblDayRepayMarginDeposit);
                    l_params.setCashMarginDeposit5(l_params.getCashMarginDeposit5() + l_dblDayRepayCashMarginDeposit);
                    l_params.setContractAssetProfitLoss5(l_params.getContractAssetProfitLoss5() + l_dblSwapProfit - l_dblSwapLoss);
                    l_paramsDetail.setDayRepayMarginDeposit5(l_dblDayRepayMarginDeposit);
                    l_paramsDetail.setDayRepayCashMarginDeposit5(l_dblDayRepayCashMarginDeposit);
                    l_paramsDetail.setContractAssetLoss5(l_paramsDetail.getContractAssetLoss5() + l_dblSwapLoss);
                    l_paramsDetail.setContractAssetProfit5(l_paramsDetail.getContractAssetProfit5() + l_dblSwapProfit);
                    break;
                default:
                    break;
            }

            // 1.1.9　@calc代用証券評価額(指定日 : Date)
            double l_dblSubstituteValuationPrice = this.securityValuation.
                calcSubstituteValuationPrice(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_params.setSubstituteSecurityAsset0(l_dblSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_params.setSubstituteSecurityAsset1(l_dblSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_params.setSubstituteSecurityAsset2(l_dblSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_params.setSubstituteSecurityAsset3(l_dblSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_params.setSubstituteSecurityAsset4(l_dblSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_params.setSubstituteSecurityAsset5(l_dblSubstituteValuationPrice);
                    break;
                default:
                    break;
            }

            // 1.1.10　@calc出金額(指定日 : Date)
            double l_dblCashOut = this.cashValuation.calcCashOut(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_paramsDetail.setPaymentAmountDesignate0(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_paramsDetail.setPaymentAmountDesignate1(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_paramsDetail.setPaymentAmountDesignate2(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_paramsDetail.setPaymentAmountDesignate3(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_paramsDetail.setPaymentAmountDesignate4(l_dblCashOut);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_paramsDetail.setPaymentAmountDesignate5(l_dblCashOut);
                    break;
                default:
                    break;
            }

            // 1.1.11　@calc即日入金銘柄拘束金(営業日 : Date)
            double l_dblTodayDepFundRestraint = 
                this.cashValuation.getRestraint().calcTodayDepFundRestraint(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_paramsDetail.setTodayDepFundRestraint0(l_dblTodayDepFundRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_paramsDetail.setTodayDepFundRestraint1(l_dblTodayDepFundRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_paramsDetail.setTodayDepFundRestraint2(l_dblTodayDepFundRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_paramsDetail.setTodayDepFundRestraint3(l_dblTodayDepFundRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_paramsDetail.setTodayDepFundRestraint4(l_dblTodayDepFundRestraint);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_paramsDetail.setTodayDepFundRestraint5(l_dblTodayDepFundRestraint);
                    break;
                default:
                    break;
            }

            // 1.1.11　@calc発注分代用証券評価額(指定日 : Date)
            double l_dblUnExecSubstituteValuationPrice = this.securityValuation.
                calcUnExecSubstituteValuationPrice(l_datBizDate);

            switch (i)
            {
                case WEB3TPSpecifiedPointDef.T_0:
                    l_paramsDetail.setUnexecSubstiSecurityAsset0(l_dblUnExecSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_1:
                    l_paramsDetail.setUnexecSubstiSecurityAsset1(l_dblUnExecSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_2:
                    l_paramsDetail.setUnexecSubstiSecurityAsset2(l_dblUnExecSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_3:
                    l_paramsDetail.setUnexecSubstiSecurityAsset3(l_dblUnExecSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_4:
                    l_paramsDetail.setUnexecSubstiSecurityAsset4(l_dblUnExecSubstituteValuationPrice);
                    break;
                case WEB3TPSpecifiedPointDef.T_5:
                    l_paramsDetail.setUnexecSubstiSecurityAsset5(l_dblUnExecSubstituteValuationPrice);
                    break;
                default:
                    break;
            }
            
            // T+0 又は T+5の時実行(受残と約残)
            if((WEB3DateUtility.compareToDay(l_datBizDate0, l_datBizDate) == 0)
                    || (WEB3DateUtility.compareToDay(l_datBizDate5, l_datBizDate) == 0))
            {
                // 1.1.12　@calc預り資産評価額(指定日 : Date)
                // 株式評価額
                double l_dblEquityAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.EQUITY, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                // 株式ミニ投資評価額
                double l_dblMiniStockAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.EQUITY, WEB3MiniStockDivDef.MINI_STOCK);
                // 累積投資評価額
                double l_dblRuitoAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.RUITO, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                // 投資信託評価額
                double l_dblMutualFundAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.MUTUAL_FUND, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                // 債券評価額
                double l_dblBondAsset = this.securityValuation.
                    calcAssetValuationPrice(l_datBizDate, ProductTypeEnum.BOND, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                // 外国株
                double l_dblFeqAsset = this.securityValuation.calcAssetValuationPrice(
                    l_datBizDate, ProductTypeEnum.FOREIGN_EQUITY, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                switch (i)
                {
                    case WEB3TPSpecifiedPointDef.T_0:
                        l_paramsDetail.setEquityAssetDelivered(l_dblEquityAsset);
                        l_paramsDetail.setMiniStockAssetDelivered(l_dblMiniStockAsset);
                        l_paramsDetail.setRuitoAssetDelivered(l_dblRuitoAsset);
                        l_paramsDetail.setMutualFundAssetDelivered(l_dblMutualFundAsset);
                        l_paramsDetail.setBondAssetDelivered(l_dblBondAsset);
                        l_paramsDetail.setForeignEquityAssetDelivered(l_dblFeqAsset);
                        break;
                    case WEB3TPSpecifiedPointDef.T_5:
                        l_paramsDetail.setEquityAssetExecuted(l_dblEquityAsset);
                        l_paramsDetail.setMiniStockAssetExecuted(l_dblMiniStockAsset);
                        l_paramsDetail.setRuitoAssetExecuted(l_dblRuitoAsset);
                        l_paramsDetail.setMutualFundAssetExecuted(l_dblMutualFundAsset);
                        l_paramsDetail.setBondAssetExecuted(l_dblBondAsset);
                        l_paramsDetail.setForeignEquityAssetExecuted(l_dblFeqAsset);
                        break;
                    default:
                        break;
                }
            }

        }

        // get当日返済建玉代金の集計
        double l_dblSummaryTodayRepayContractAmount = this.contractInfo.getSummaryTodayRepayContractAmount();

        //calc代用証券評価額(前日単価評価)
        double l_dblPrevPriceSubstituteValuation = this.securityValuation.calcPrevPriceSubstituteValuation();

        l_paramsDetail.setTodayRepayContractAmount(l_dblSummaryTodayRepayContractAmount);
        l_paramsDetail.setSubstituteAssetOldDayValue(l_dblPrevPriceSubstituteValuation);

        l_params.setMarkToMarketDiv(l_strMarkToMarketDiv);
        Timestamp l_tsNow = this.calcCondition.getSystemTimeStamp();
        l_params.setCreatedTimestamp(l_tsNow);
        l_params.setLastUpdatedTimestamp(l_tsNow);
        l_paramsDetail.setCreatedTimestamp(l_tsNow);
        l_paramsDetail.setLastUpdatedTimestamp(l_tsNow);

        //(*)分岐フロー
        if (WEB3CashoutTodayDepositTransferDivDef.EXECUTE.equals(
            calcCondition.getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV)))
        {
            try
            {
                //calc出金拘束金(double, double, double, double)
                //double = 余力計算結果(信用顧客)Params.預り金残高(T+1)
                //double = 余力計算結果(信用顧客)Params.当日未約定代金(T+1)
                //double = 余力計算結果(信用顧客)Params.当日約定済代金(T+1)
                //double = 余力計算結果詳細(信用顧客)Params.即日入金対象銘柄拘束金(T+1)
                double l_dblCalcCashoutRestraint = cashValuation.calcCashoutRestraint(
                    l_params.getAccountBalance1(),
                    l_params.getTodayUnexecutedAmount1(),
                    l_params.getTodayExecutedAmount1(),
                    l_paramsDetail.getTodayDepFundRestraint1());

                //余力計算結果(信用顧客)Paramsオブジェクトに値をセットする
                //その他拘束金( T + 0 ) = その他拘束金( T + 0 ) + calc出金拘束金()の返却値
                BigDecimal l_bdOtherRestraint0 =
                    new BigDecimal(Double.toString(l_params.getOtherRestraint0()));
                BigDecimal l_bdCalcCashoutRestraint =
                    new BigDecimal(Double.toString(l_dblCalcCashoutRestraint));
                l_params.setOtherRestraint0(
                    l_bdOtherRestraint0.add(l_bdCalcCashoutRestraint).doubleValue());

                //余力計算結果詳細(信用顧客)Paramsオブジェクトに値をセットする
                //即日入金対象銘柄拘束金( T + 0 ) = 即日入金対象銘柄拘束金( T + 0 ) + calc出金拘束金()の返却値
                BigDecimal l_bdTodayDepFundRestraint0 =
                    new BigDecimal(Double.toString(l_paramsDetail.getTodayDepFundRestraint0()));
                l_paramsDetail.setTodayDepFundRestraint0(
                    l_bdTodayDepFundRestraint0.add(l_bdCalcCashoutRestraint).doubleValue());
            }
            catch (WEB3BaseException l_ex)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + ".calcTradingpowerUpdResultMargin",
                    "予期しないシステムエラーが発生しました。");
            }
        }
        List l_list = new ArrayList();
        l_list.add(l_params);
        l_list.add(l_paramsDetail);
        return l_list;

    }

    /**
     * （calc余力更新内容<現物顧客>～未約定売付注文考慮～）<BR>
     * <BR>
     * 現物顧客の余力更新内容を計算する <BR>
     * 未約定売付注文を考慮した日計り拘束金を取得する。<BR>
     * （出金を伴う注文の取引余力チェック時にコールされる。）<BR>
     * <BR>
     * １）this.calc余力更新内容<現物顧客>～未約定売付注文考慮～()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@　@差金決済相当外買付代金非考慮フラグ：false<BR>
     * <BR>
     * @@return List
     */
    public List calcTradingpowerUpdResultEquityIncUnexecSellOrder()
    {
    	return this.calcTradingpowerUpdResultEquityIncUnexecSellOrder(false);
    }

    /**
     * （calc余力更新内容<現物顧客>～未約定売付注文考慮～）<BR>
     * <BR>
     * 現物顧客の余力更新内容を計算する<BR>
     * 未約定売付注文を考慮した日計り拘束金を取得する。<BR>
     * （出金を伴う注文の取引余力チェック時にコールされる。）<BR>
     * <BR>
     * １）余力更新内容<現物顧客>リストを取得する。(※日計り拘束金は、未約定売付注文非考慮)<BR>
     * 　@- this.余力更新内容<現物顧客>()をコール<BR>
     * <BR>
     * ２）T+0～T+5までの、日計り拘束金～未約定売付注文考慮～を取得する。<BR>
     * <BR>
     * 　@＜LOOP処理（T+0～T+5）＞<BR>
     * 　@　@- this.差金決済.get日計り拘束金～未約定売付注文考慮～()をコール<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@指定日：T+n<BR>
     * 　@　@　@差金決済相当外買付代金非考慮フラグ：引数.差金決済相当外買付代金非考慮フラグ<BR>
     * <BR>
     * ３）１）で取得した余力更新内容<現物顧客>リストの日計り拘束金(T+0..5)の値を、<BR>
     * 　@　@２）で取得した日計り拘束金～未約定売付注文考慮～(T+0..5)で上書きする。<BR>
     * <BR>
     * 　@３－１）余力更新内容<現物顧客>リストより、余力計算結果Params<現物顧客>を取得する。<BR>
     * <BR>
     * 　@３－２）取得した余力計算結果Params<現物顧客>.日計り拘束金(T+0..5)に、値をセットする。<BR>
     * 　@　@　@- 余力計算結果Params<現物顧客>.set日計り拘束金(T+0..5)()をコール<BR>
     * <BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@double：２）で取得した日計り拘束金～未約定売付注文考慮～(T+0..5)<BR>
     * <BR>
     * ４）日計り拘束金～未約定売付注文～がセットされた余力更新内容<現物顧客>リストを返却する。<BR>
     * <BR>
     * @@param l_blnIsSettlement - (差金決済相当外買付代金非考慮フラグ)
     * @@return java.util.List
     */
    public List calcTradingpowerUpdResultEquityIncUnexecSellOrder(boolean l_blnIsSettlement)
    {
        /*
         * 余力更新内容 <現物顧客>リストを取得する。
         */
        List l_calcResult = this.calcTradingpowerUpdResultEquity();

        /*
         * Listより余力計算結果 <現物顧客>オブジェクトを取得し 
         * 日計り拘束金～未約定売付注文考慮～(T+0..5)をセットする。
         */
        //余力計算結果<現物顧客>Params
        TpCalcResultEquityParams l_params = null;
        //余力計算結果<現物顧客>詳細Params
        TpCalcResultEquityDetailParams l_paramsDetail = null;

        for(Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object) l_iter.next();

            if(l_element instanceof TpCalcResultEquityParams)
            {
                //余力計算結果<現物顧客>Paramsを取得
                l_params = (TpCalcResultEquityParams) l_element;

                //LOOP処理（指定日=T+0～T+5の間）
                for(int i = WEB3TPSpecifiedPointDef.T_0; i <= WEB3TPSpecifiedPointDef.T_5; i++)
                {
                    //指定日(Date型)を取得
                    Date l_datBizDate = this.calcCondition.getBizDate(i);

                    //日計り拘束金～未約定売付注文考慮～(指定日 : Date)を取得
                    double l_dblDateTradeRestraint = this.settlement
                        .getDayTradeRestraintIncUnexecSellOrder(l_datBizDate, l_blnIsSettlement);

                    //日計り拘束金～未約定売付注文考慮～(T+0..5)を上書き
                    switch(i)
                    {
                        case WEB3TPSpecifiedPointDef.T_0:
                            l_params
                                .setDayTradeRestraint0(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_1:
                            l_params
                                .setDayTradeRestraint1(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_2:
                            l_params
                                .setDayTradeRestraint2(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_3:
                            l_params
                                .setDayTradeRestraint3(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_4:
                            l_params
                                .setDayTradeRestraint4(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_5:
                            l_params
                                .setDayTradeRestraint5(l_dblDateTradeRestraint);
                            break;
                        default:
                            break;
                    }
                }
            }
            else if(l_element instanceof TpCalcResultEquityDetailParams)
            {
                l_paramsDetail = (TpCalcResultEquityDetailParams) l_element;
            }
        }

        //余力更新内容<現物顧客>～未約定売付注文考慮～リストを返却する。
        List l_list = new ArrayList();
        l_list.add(l_params);
        l_list.add(l_paramsDetail);
        return l_list;
    }

    /**
     * （calc余力更新内容<信用顧客>～未約定売付注文考慮～）<BR>
     * <BR>
     * 信用顧客の余力更新内容を計算する<BR>
     * 未約定売付注文を考慮した日計り拘束金を取得する。<BR>
     * （出金を伴う注文の取引余力チェック時にコールされる。）<BR>
     * <BR>
     * １）余力更新内容<信用顧客>リストを取得する。(※日計り拘束金は、未約定売付注文非考慮)<BR>
     * 　@- this.余力更新内容<信用顧客>()をコール<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@値洗い区分：0:通常<BR>
     * <BR>
     * ２）T+0～T+5までの、日計り拘束金～未約定売付注文考慮～を取得する。<BR>
     * <BR>
     * 　@＜LOOP処理（T+0～T+5）＞<BR>
     * 　@　@- this.差金決済.get日計り拘束金～未約定売付注文考慮～()をコール<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@指定日：T+n<BR>
     * <BR>
     * ３）１）で取得した余力更新内容<信用顧客>リストの日計り拘束金(T+0..5)の値を、<BR>
     * 　@　@２）で取得した日計り拘束金～未約定売付注文考慮～(T+0..5)で上書きする。<BR>
     * <BR>
     * 　@３－１）余力更新内容<信用顧客>リストより、余力計算結果Params<信用顧客>を取得する。<BR>
     * <BR>
     * 　@３－２）取得した余力計算結果Params<信用顧客>.日計り拘束金(T+0..5)に、値をセットする。<BR>
     * 　@　@　@- 余力計算結果Params<信用顧客>.set日計り拘束金(T+0..5)()をコール<BR>
     * <BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@double：２）で取得した日計り拘束金～未約定売付注文考慮～(T+0..5)<BR>
     * <BR>
     * ４）日計り拘束金～未約定売付注文～がセットされた余力更新内容<信用顧客>リストを返却する。<BR>
     * <BR>
     * @@return java.util.List
     */
    public List calcTradingpowerUpdResultMarginIncUnexecSellOrder()
    {
        /*
         * 余力更新内容 <信用顧客>リストを取得する。
         */
        List l_calcResult = this
            .calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

        /*
         * Listより余力計算結果 <信用顧客>オブジェクトを取得し 日計り拘束金～未約定売付注文考慮～(T+0..5)をセットする。
         */
        //余力計算結果<信用顧客>Params
        TpCalcResultMarginParams l_params = null;
        //余力計算結果<信用顧客>詳細Params
        TpCalcResultMarginDetailParams l_paramsDetail = null;

        for(Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object) l_iter.next();

            if(l_element instanceof TpCalcResultMarginParams)
            {
                //余力計算結果<信用顧客>Paramsを取得
                l_params = (TpCalcResultMarginParams) l_element;

                //LOOP処理（指定日=T+0～T+5の間）
                for(int i = WEB3TPSpecifiedPointDef.T_0; i <= WEB3TPSpecifiedPointDef.T_5; i++)
                {
                    //指定日(Date型)を取得
                    Date l_datBizDate = this.calcCondition.getBizDate(i);

                    //日計り拘束金～未約定売付注文考慮～(指定日 : Date)を取得
                    double l_dblDateTradeRestraint = this.settlement
                        .getDayTradeRestraintIncUnexecSellOrder(l_datBizDate);

                    //日計り拘束金～未約定売付注文考慮～(T+0..5)を上書き
                    switch(i)
                    {
                        case WEB3TPSpecifiedPointDef.T_0:
                            l_params
                                .setDayTradeRestraint0(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_1:
                            l_params
                                .setDayTradeRestraint1(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_2:
                            l_params
                                .setDayTradeRestraint2(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_3:
                            l_params
                                .setDayTradeRestraint3(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_4:
                            l_params
                                .setDayTradeRestraint4(l_dblDateTradeRestraint);
                            break;
                        case WEB3TPSpecifiedPointDef.T_5:
                            l_params
                                .setDayTradeRestraint5(l_dblDateTradeRestraint);
                            break;
                        default:
                            break;
                    }
                }
            }
            else if(l_element instanceof TpCalcResultMarginDetailParams)
            {
                l_paramsDetail = (TpCalcResultMarginDetailParams) l_element;
            }
        }

        //余力更新内容<信用顧客>～未約定売付注文考慮～リストを返却する。
        List l_list = new ArrayList();
        l_list.add(l_params);
        l_list.add(l_paramsDetail);
        return l_list;
    }

    /**
     * (get銘柄ごと代用証券評価額)
     *
     * 銘柄ごと代用証券評価額を取得する。<BR>
     *
     * <BR>
     * シーケンス図「get銘柄ごと代用証券評価額」参照<BR>
     * @@param l_lngProductId - 銘柄ID
     * @@param l_intSpecifiedPoint - 指定日
     * @@return double
     * @@roseuid 410DDDBE00B5
     */
    public double getSubstituteValuationPriceParProduct(long l_lngProductId,
        int l_intSpecifiedPoint)
    {

        // 1.1　@現物顧客の場合、0.0を返す
        if (!this.accountInfo.isMarginCustomer())
        {
            return 0.0;
        }

        // 1.2 get対象銘柄(銘柄ID : long) --対象銘柄を取得
        WEB3TPSecurityValuationProduct l_product = this.securityValuation.
            getProduct(l_lngProductId,WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

        //対象銘柄==nullの場合
        if(l_product == null)
        {
            return 0.0;
        }
        
        // 1.3 get銘柄ごと証券評価額(対象銘柄 --銘柄ごと証券評価額オブジェクトを取得
        WEB3TPSecurityValuationPerProduct l_valuationPerProduct = this.
            securityValuation.getSecurityValuationPerProduct(l_product);

        // 1.4 get営業日(指定日 : int) --営業日を取得
        java.util.Date l_datBizDate = this.calcCondition.getBizDate(l_intSpecifiedPoint);

        // 1.5　@get証券評価額(指定日 : Date, 預り区分) --銘柄ごと代用証券評価額を取得
        return l_valuationPerProduct.getValuationPrice(l_datBizDate,
            WEB3TPDepositTypeDef.SUBSTITUTE);

    }

    /**
     * (get建玉銘柄一覧)<BR>
     *
     * 保有建玉の銘柄ID一覧を取得する。<BR>
     * <BR>
     * シーケンス図「get建玉銘柄一覧」参照<BR>
     * @@param l_intSpecifiedPoint - 指定日
     * @@param l_contractType - 建区分
     * @@return long[]
     */
    public long[] getContractProducts(int l_intSpecifiedPoint, ContractTypeEnum l_contractType)
    {
        // 1.1　@現物顧客の場合、nullを返す
        if (!this.accountInfo.isMarginCustomer())
        {
            return null;
        }

        Date l_datDate = this.calcCondition.getBizDate(l_intSpecifiedPoint);
        return this.contractInfo.getContractProducts(l_datDate, l_contractType);
    }

    /**
     * (save余力更新内容<現物顧客>）
     *
     * 現物顧客の余力更新内容をDBへ保存する<BR>
     * <BR>
     * @@param java.util.List - 現物顧客の余力更新内容
     * @@roseuid 410DDDBE00B9
     */
    public void saveTradingpowerUpdResultEquity(List l_list)
    {

        WEB3TPPersistentDataManager.getInstance().
            saveTradingpowerUpdResultEquity(l_list);

    }

    /**
     * (save余力更新内容<信用顧客>）
     *
     * 信用顧客の余力更新内容をDBへ保存する<BR>
     * <BR>
     * @@param java.util.List - 信用顧客の余力更新内容
     * @@roseuid 410DDDBE00BB
     */
    public void saveTradingpowerUpdResultMargin(List l_list)
    {

        WEB3TPPersistentDataManager.getInstance().
            saveTradingpowerUpdResultMargin(l_list);

    }

    /**
     * (get顧客属性)<BR>
     * 顧客属性を取得する。
     * @@return WEB3TPAccountInfo
     */
    public WEB3TPAccountInfo getAccountInfo()
    {
        return this.accountInfo;
    }

    /**
     * (get計算条件)<BR>
     * 計算条件を取得する。
     * @@return WEB3TPCalcCondition
     */
    public WEB3TPCalcCondition getCalcCondition()
    {
        return this.calcCondition;
    }

    /**
     * (get総資金)<BR>
     * 総資金を取得する。
     * @@return WEB3TPCashValuation
     */
    public WEB3TPCashValuation getCashValuation()
    {
        return this.cashValuation;
    }

    /**
     * (get建玉情報)<BR>
     * 建玉情報を取得する。
     * @@return WEB3TPContractInfo
     */
    public WEB3TPContractInfo getContractInfo()
    {
        return this.contractInfo;
    }

    /**
     * (get証券評価)<BR>
     * 証券評価を取得する。
     * @@return WEB3TPSecurityValuation
     */
    public WEB3TPSecurityValuation getSecurityValuation()
    {
        return this.securityValuation;
    }

    /**
     * (get差金決済)<BR>
     * 差金決済を取得する。
     * @@return WEB3TPSettlement
     */
    public WEB3TPSettlement getSettlement()
    {
        return this.settlement;
    }

    /**
     * (get現注文内容)<BR>
     * 現注文内容を取得する。
     * @@return WEB3TPNewOrderSpec[]
     */
    public WEB3TPNewOrderSpec[] getNewOrderSpecs()
    {
        return this.newOrderSpecs;
    }

}
@
