head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerUpdAfterSell.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 売付後余力更新(WEB3TPTradingPowerUpdAfterSell.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/08 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower;

import java.util.List;

import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuationAfterSell;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflectorNewSellOrder;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlement;
import webbroker3.util.WEB3LogUtility;

/**
 * (売付後余力更新)
 * ※余力更新クラスの拡張クラス
 * ※現物売付注文/信用現渡注文の取引余力チェック時に使用する。
 */
public class WEB3TPTradingPowerUpdAfterSell extends WEB3TPTradingPowerUpd
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTradingPowerUpdAfterSell.class);

    /**
     * (デフォルトコンストラクタ)
     */
    public WEB3TPTradingPowerUpdAfterSell()
    {
        super();
    }

    /**
     * (コンストラクタ)
     * 
     * ※シーケンス図「(売付後余力更新)コンストラクタ」参照
     * 
     * @@param l_lngAccountId - (口座ID)
     * @@param l_blnMarginFlag - (信用顧客フラグ)
     * @@param l_calcCondition - (余力計算条件)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@param l_lngOrderProductId - (注文銘柄ID)
     */
    public WEB3TPTradingPowerUpdAfterSell(
        long l_lngAccountId,
        boolean l_blnMarginFlag,
        WEB3TPCalcCondition l_calcCondition,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        long l_lngOrderProductId)
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerUpdAfterSell(long, boolean, WEB3TPCalcCondition, WEB3TPNewOrderSpec[], long)";
        log.entering(STR_METHOD_NAME);

        /*
         * 顧客属性オブジェクト生成       
         */
        WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(l_lngAccountId, l_blnMarginFlag);

        this.accountInfo = l_accountInfo;
        this.calcCondition = l_calcCondition;
        this.newOrderSpecs = l_newOrderSpecs;

        /*
         * 当日株式注文一覧を取得
         */
        List l_todaysEquityOrders = WEB3TPPersistentDataManager.getInstance()
            .getTodaysEquityOrders(l_accountInfo, l_calcCondition);
        /*
         * 建玉情報オブジェクト作成
         */
        WEB3TPContractInfo l_contractInfo = WEB3TPContractInfo.create(
            this.accountInfo,
            this.calcCondition,
            this.newOrderSpecs);
        l_contractInfo.setTodaysEquityOrders(l_todaysEquityOrders);

        //建玉情報ロード
        l_contractInfo.loadContractInfo();
        this.contractInfo = l_contractInfo;

        /*
         * 証券評価オブジェクト作成
         */
        WEB3TPSecurityValuation l_securityValuation = WEB3TPSecurityValuation.create(
            this.accountInfo,
            this.calcCondition,
            this.newOrderSpecs,
            l_contractInfo);
        l_securityValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //銘柄ごと証券評価額ロード
        l_securityValuation.doSecurityValuationPerProductLoad();
        this.securityValuation = l_securityValuation;

        /*
         * 売付後総資金オブジェクト作成
         */
        WEB3TPCashValuationAfterSell l_cashValuation = WEB3TPCashValuationAfterSell.createWEB3TPCashValuationAfterSell(
            this.accountInfo,
            this.calcCondition,
            this.newOrderSpecs,
            l_lngOrderProductId);
        l_cashValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //売付後総資金ロード
        l_cashValuation.loadAll();
        this.cashValuation = l_cashValuation;

        /*
         * 差金決済オブジェクトを生成
         */
        this.settlement = new WEB3TPSettlement(
            this.cashValuation,
            this.securityValuation,
            this.calcCondition);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (reSet今回売付注文分取引情報)
     * 
     * 今回売付注文分取引情報の内容を変換する。
     * 
     * ※シーケンス図「(売付後余力更新)reSet今回売付注文分取引情報」参照
     * 
     * @@param l_dblOrderQuantity - (注文数量)
     */
    public void reSetWEB3TPTransactionReflectorNewSellOrder(double l_dblOrderQuantity)
    {
        final String STR_METHOD_NAME = "reSetWEB3TPTransactionReflectorNewSellOrder(double)";
        log.entering(STR_METHOD_NAME);

        /*
         * 取引情報一覧<当日>を取得する。
         */
        List l_todayTransactions = this.cashValuation.getTransactionAmount()
            .getTodaysTransactions();

        /*
         * LOOP処理：取引情報一覧<当日>の要素数回
         */
        for(int index = 0; index < l_todayTransactions.size(); index++)
        {
            //要素オブジェクトを取得する。
            Object l_element = l_todayTransactions.get(index);

            //要素オブジェクトが、今回売付注文分取引情報クラスの場合
            if(l_element instanceof WEB3TPTransactionReflectorNewSellOrder)
            {
                //要素オブジェクトを今回売付注文分取引情報クラスにキャストする。
                WEB3TPTransactionReflectorNewSellOrder l_reflector = (WEB3TPTransactionReflectorNewSellOrder) l_element;

                //注文単価を取得
                double l_price = l_reflector.getPrice();

                /*
                 * “約定済代金”を計算する。
                 * 
                 * [計算式]
                 * “約定済代金” = 取得した注文単価 × 引数.注文数量
                 */
                double l_dblExecutedAmount = l_price * l_dblOrderQuantity;

                l_reflector.setExecutedQuantity(l_dblOrderQuantity);
                l_reflector.setExecutedAmount(l_dblExecutedAmount);

                //LOOP処理より抜ける
                break;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}@
