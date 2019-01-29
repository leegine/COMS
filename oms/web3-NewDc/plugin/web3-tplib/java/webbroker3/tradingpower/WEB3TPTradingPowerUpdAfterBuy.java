head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerUpdAfterBuy.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 ネットトレードシステム開発部
 File Name        : 買付後余力更新(WEB3TPTradingPowerUpdAfterBuy.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/31 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower;

import java.util.List;

import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationAfterBuy;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuation;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlement;
import webbroker3.util.WEB3LogUtility;

/**
 * (買付後余力更新)
 * ※余力更新クラスの拡張クラス
 * ※現物株式注文の取引余力チェック時に使用する。
 */
public class WEB3TPTradingPowerUpdAfterBuy extends WEB3TPTradingPowerUpd
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTradingPowerUpdAfterBuy.class);

    /**
     * (デフォルトコンストラクタ)
     */
    public WEB3TPTradingPowerUpdAfterBuy()
    {
        super();
    }

    /**
     * (コンストラクタ)
     * 
     * ※シーケンス図「(買付後余力更新)コンストラクタ」参照
     * 
     * @@param l_lngAccountId - (口座ID)
     * @@param l_blnMarginFlag - (信用顧客フラグ)
     * @@param l_calcCondition - (余力計算条件)
     * @@param l_newOrderSpecs - (現注文内容)
     */
    public WEB3TPTradingPowerUpdAfterBuy(
            long l_lngAccountId,
            boolean l_blnMarginFlag,
            WEB3TPCalcCondition l_calcCondition,
            WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerUpdAfterBuy(long, boolean, WEB3TPCalcCondition, WEB3TPNewOrderSpec[])";
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
        List l_todaysEquityOrders = WEB3TPPersistentDataManager.getInstance().getTodaysEquityOrders(
                l_accountInfo,
                l_calcCondition);
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
         * 買付後証券評価オブジェクト作成
         */
        WEB3TPSecurityValuationAfterBuy l_securityValuation = WEB3TPSecurityValuationAfterBuy.createWEB3TPSecurityValuationAfterBuy(
                this.accountInfo,
                this.calcCondition,
                this.newOrderSpecs,
                l_contractInfo);
        l_securityValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //銘柄ごと証券評価額ロード
        l_securityValuation.doSecurityValuationPerProductLoad();
        this.securityValuation = l_securityValuation;

        /*
         * 総資金オブジェクト作成
         */
        WEB3TPCashValuation l_cashValuation = WEB3TPCashValuation.create(
                this.accountInfo,
                this.calcCondition,
                this.newOrderSpecs);
        l_cashValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //総資金ロード
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
}@
