head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSettlement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 差金決済(WEB3TPSettlement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 nakazato(ACT) 新規作成
                   2006/09/15 車進　@  (中訊)モデルNo.45
Revesion History : 2007/09/28 孟亞南　@(中訊)モデルNo.177,No.191,No.192
Revesion History : 2007/10/22 孟亞南　@(中訊)モデルNo.215
Revesion History : 2007/11/12 孟亞南　@(中訊)モデルNo.230
Revesion History : 2008/05/27 張騰宇　@(中訊)モデルNo.283,No.284
Revesion History : 2008/06/10 孟亞南　@(中訊)モデルNo.288,No.289,No.290
Revesion History : 2010/01/11 武波　@  (中訊)モデルNo.419
*/
package webbroker3.tradingpower.updtpower.settlement;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationPerProduct;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflector;
import webbroker3.util.WEB3LogUtility;

/**
 * （差金決済）
 * @@author  nakazato(ACT)
 * @@version 1.0
 */
public class WEB3TPSettlement
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPSettlement.class);

    /**
     * （総資金）
     */
    protected WEB3TPCashValuation cashValuation;

    /**
     * （証券評価額）
     */
    protected WEB3TPSecurityValuation securityValuation;

    /**
     * （顧客勘定推移）
     */
    protected WEB3TPAccountTransition accountTransition;

    /**
     * （余力計算条件）
     */
    protected WEB3TPCalcCondition calcCondtion;

    /**
     * （差金決済）<BR>
     * 
     * コンストラクタ<BR>
     * <BR>
     * １）パラメータをプロパティにセットする。<BR>
     * <BR>
     * ２）顧客勘定推移のインスタンスを生成する。<BR>
     * <BR>
     * ３）２）で生成した顧客勘定推移をプロパティにセットする。<BR>
     * <BR>
     * @@param l_cashValuation - （総資金）
     * @@param l_securityValuation - （証券評価額）
     * @@param l_calcCondition - （余力計算条件）
     * @@roseuid 40F73A1700E9
     */
    public WEB3TPSettlement(
        WEB3TPCashValuation l_cashValuation,
        WEB3TPSecurityValuation l_securityValuation,
        WEB3TPCalcCondition l_calcCondition)
    {
        this.cashValuation = l_cashValuation;
        this.securityValuation = l_securityValuation;
        this.calcCondtion = l_calcCondition;

        this.accountTransition = new WEB3TPAccountTransition();
    }

    /**
     * (get日計り拘束金～未約定売付注文考慮～)<BR>
     * <BR>
     * パラメータ.指定日の未約定売付注文を考慮した<BR>
     * 日計拘束金を取得し返却する。<BR>
     * <BR>
     * １）this.get日計り拘束金～未約定売付注文考慮～()をコールする。 <BR>
     * <BR>
     *    [引数]<BR>
     * 　@　@　@　@指定日：引数.指定日<BR>
     * 　@　@　@　@差金決済相当外買付代金非考慮フラグ：false<BR>
     * <BR>
     * @@param l_datSpecifiedDate - （指定日）
     * @@return double
     */
    public double getDayTradeRestraintIncUnexecSellOrder(Date l_datSpecifiedDate)
    {
        //１）this.get日計り拘束金～未約定売付注文考慮～()をコールする。
        return this.getDayTradeRestraintIncUnexecSellOrder(l_datSpecifiedDate, false);
    }

    /**
     * （get日計り拘束金）<BR>
     * 
     * パラメータ.指定日の日計拘束金を取得し返却する。<BR>
     * <BR>
     * シーケンス図「(差金決済)get日計り拘束金」参照<BR>
     * @@param l_datSpecifiedDate - （指定日）
     * @@return double
     * @@roseuid 40F73A1F0399
     */
    public double getDayTradeRestraint(Date l_datSpecifiedDate)
    {
        //do指定日の顧客勘定推移ロード()をコールする。
        this.loadAccountTransition(l_datSpecifiedDate, false);
        
        //日計り拘束金を計算する。
        double l_dblDayTradeRestraint = this.accountTransition.calcDayTradeRestraint();

        //日計り拘束金を返却する。
        return l_dblDayTradeRestraint;
    }

    
    /**
     * （get差金決済売付可能数量）<BR>
     * 
     * パラメータ.指定日、パラメータ注文銘柄の<BR>
     * 差金決済売付可能数量を取得し返却する。<BR>
     * <BR>
     * シーケンス図「(差金決済)get差金決済売付可能数量」参照<BR>
     * @@param l_datSpecifiedDate - （指定日）
     * @@param l_lngOrderFundId - （注文銘柄ID）
     * @@param l_dblOrderQuantity - （注文数量）
     * @@param l_dblLotSize - （売買単位）
     * @@return WEB3TPSellOrderPossibleQuantityResult
     */
    public WEB3TPSellOrderPossibleQuantityResult getSellOrderPossibleQuantity(
        Date l_datSpecifiedDate,
        long l_lngOrderFundId,
        double l_dblOrderQuantity,
        double l_dblLotSize)
    {
        //do指定日の顧客勘定推移ロード()をコールする。
        this.loadAccountTransition(l_datSpecifiedDate, true);

        //対象銘柄を取得する。
        WEB3TPSecurityValuationProduct l_valuationProduct =
            this.securityValuation.getProduct(l_lngOrderFundId,WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        //銘柄ごと証券評価額を取得する。
        WEB3TPSecurityValuationPerProduct l_valuationPerProduct =
            this.securityValuation.getSecurityValuationPerProduct(l_valuationProduct);

        //既存保有数量<確定>を取得する。
        double l_dblExistQuantity;
        if(l_valuationPerProduct != null) 
        {
            l_dblExistQuantity = l_valuationPerProduct.calcAssetQuantity(
                    l_datSpecifiedDate, WEB3TPDepositTypeDef.TRUST);
        }
        else
        {
            l_dblExistQuantity = 0.0d;
        }                                                       

        //差金決済売付可能数量を計算する。
        WEB3TPSellOrderPossibleQuantityResult l_sellOrderPossQtyResult = this.accountTransition
            .calcSellOrderPossibleQuantity(
                    l_lngOrderFundId, l_dblOrderQuantity, l_dblLotSize,
                    l_dblExistQuantity);

        //差金決済売付可能数量を返却する。
        return l_sellOrderPossQtyResult;
    }

    /**
     * （get差金決済買付可能額）<BR>
     * <BR>
     * 引数.指定日、引数.注文銘柄IDの<BR>
     * 差金決済買付可能額を取得し返却する。<BR>
     * <BR>
     * 引数.注文銘柄IDが、日計り対象銘柄でない時、<BR>
     * 引数.株式買付可能額が返却される。<BR>
     * <BR>
     * シーケンス図「(差金決済)get差金決済買付可能額」参照<BR>
     * <BR>
     * @@param l_datSpecifiedDate - （指定日）
     * @@param l_lngOrderFundId - （注文銘柄ID）
     * @@return double
     */
    public double getBuyOrderPossibleAmount(Date l_datSpecifiedDate, long l_lngOrderFundId)
    {
        //do指定日の顧客勘定推移ロード()をコールする。
        this.loadAccountTransition(l_datSpecifiedDate, true);

        //差金決済買付可能額を計算する。
        double l_dblBuyOrderPossibleAmount =
            this.accountTransition.calcBuyOrderPossibleAmount(l_lngOrderFundId);

        //差金決済買付可能額を返却する。
        return l_dblBuyOrderPossibleAmount;
    }

    /**
     * （get銘柄ごと取引情報）<BR>
     * <BR>
     * シーケンス図「(差金決済)get銘柄ごと取引情報」参照
     * <BR>
     * @@param l_datSpecifiedDate - （指定日）
     * @@param l_lngOrderFundId - （注文銘柄ID）
     * @@return WEB3TPSettlementReflector
     */
    public WEB3TPSettlementReflector getSettlementReflector(
        Date l_datSpecifiedDate,
        long l_lngOrderFundId)
    {
        final String STR_METHOD_NAME = "getSettlementReflector(Date, long)";
        log.entering(STR_METHOD_NAME);

        //do指定日の顧客勘定推移ロード()をコールする。
        this.loadAccountTransition(l_datSpecifiedDate, true);

        //銘柄ごと取引情報一覧を取得
        List l_list = this.accountTransition.getLisSettlementReflectors();

        //LOOP処理：銘柄ごと取引情報一覧の要素数回
        for(Iterator l_iter = l_list.iterator(); l_iter.hasNext();)
        {
            //要素オブジェクトを取得
            WEB3TPSettlementReflector l_element = (WEB3TPSettlementReflector) l_iter.next();

            //要素オブジェクトの銘柄ID == 引数.銘柄ID の場合 
            if(l_element.getFundId() == l_lngOrderFundId)
            {
                //銘柄ごと取引情報オブジェクトを返却
                log.exiting(STR_METHOD_NAME);
                return l_element;
            }
        }

        /*
         * 以下、指定銘柄の銘柄ごと取引情報オブジェクトが取得できなかった場合の処理
         */
        //対象銘柄を取得する。
        WEB3TPSecurityValuationProduct l_valuationProduct = this.securityValuation.getProduct(
            l_lngOrderFundId,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        //銘柄ごと証券評価額を取得する。
        WEB3TPSecurityValuationPerProduct l_valuationPerProduct = this.securityValuation.getSecurityValuationPerProduct(l_valuationProduct);

        //既存保有数量<確定>を取得する。
        double l_dblExistQuantity;
        if(l_valuationPerProduct != null)
        {
            //パラメータ.指定日の前営業日を取得する。
            Date l_datPrevDate = this.calcCondtion.rollBizDate(l_datSpecifiedDate, -1);
            
            //指定日前日の既存保有数量<確定>を取得する。
            double l_dblAssetQuantity = l_valuationPerProduct
                .calcAssetQuantity(
                        l_datPrevDate, WEB3TPDepositTypeDef.TRUST);

            //指定日前日の確定取引数量<当日以降>を取得する。
            double l_dblExecutedTransactionQuantity = l_valuationPerProduct
                .calcExecutedTransactionQuantity(l_datPrevDate);

            //既存保有数量<確定> = 指定日前日の既存保有数量<確定> + 指定日前日の確定取引数量<当日以降>
            l_dblExistQuantity = l_dblAssetQuantity
                    + l_dblExecutedTransactionQuantity;
        }
        else
        {
            l_dblExistQuantity = 0.0d;
        }

        /*
         * 銘柄ごと取引情報オブジェクトを生成
         */
        WEB3TPSettlementReflector l_reflector = new WEB3TPSettlementReflector();
        //銘柄ID
        l_reflector.setFundId(l_lngOrderFundId);
        //指定日前日保有数量
        l_reflector.setExistQuantity(l_dblExistQuantity);

        //銘柄ごと取引情報オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_reflector;
    }
    
    /**
     * （do指定日の顧客勘定推移ロード）<BR>
     * 指定日の取引情報を取得し、顧客勘定推移を表現する。<BR>
     * <BR>
     * １）this.do指定日の顧客勘定推移ロード()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@指定日：引数.指定日 <BR>
     * 　@　@　@未約定売付注文ロードフラグ：引数.未約定売付注文ロードフラグ <BR>
     * 　@　@　@差金決済相当外買付代金非考慮フラグ：false<BR>
     * <BR>
     * @@param l_datSpecifiedDate - （指定日）
     * @@param l_blnUnexecSellOrder - （未約定売付注文ロードフラグ）
     */
    protected void loadAccountTransition(Date l_datSpecifiedDate, boolean l_blnUnexecSellOrder)
    {
    	this.loadAccountTransition(l_datSpecifiedDate, l_blnUnexecSellOrder, false);
    }

    /**
     * (get日計り拘束金～未約定売付注文考慮～)<BR>
     * <BR>
     * パラメータ.指定日の未約定売付注文を考慮した<BR>
     * 日計拘束金を取得し返却する。<BR>
     * <BR>
     * シーケンス図「(差金決済)get日計り拘束金～未約定売付注文考慮～」参照（get日計り拘束金）<BR>
     * <BR>
     * @@param l_datSpecifiedDate - （指定日）
     * @@param l_blnIsSettlement - (差金決済相当外買付代金非考慮フラグ)
     * @@return double
     */
    public double getDayTradeRestraintIncUnexecSellOrder(Date l_datSpecifiedDate, boolean l_blnIsSettlement)
    {
        //do指定日の顧客勘定推移ロード()をコールする。
        this.loadAccountTransition(l_datSpecifiedDate, true, l_blnIsSettlement);
        //日計り拘束金
        double l_dblDayTradeRestraint = 0D;

        if (l_blnIsSettlement)
        {
            //calc日計り拘束金～0補正無し～
            l_dblDayTradeRestraint = this.accountTransition.calcDayTradeRestraintForCheck();
        }
        else
        {
            //calc日計り拘束金
            l_dblDayTradeRestraint = this.accountTransition.calcDayTradeRestraint();
        }

        //日計り拘束金を返却する。
        return l_dblDayTradeRestraint;
    }

    /**
     * （do指定日の顧客勘定推移ロード）<BR>
     * 指定日の取引情報を取得し、顧客勘定推移を表現する。<BR>
     * <BR>
     * シーケンス図「(顧客勘定推移)do指定日の顧客勘定推移ロード）」参照<BR>
     * <BR>
     * @@param l_datSpecifiedDate - （指定日）
     * @@param l_blnUnexecSellOrder - （未約定売付注文ロードフラグ）
     * @@param l_blnIsSettlement - (差金決済相当外買付代金非考慮フラグ)
     */
    protected void loadAccountTransition(Date l_datSpecifiedDate,
        boolean l_blnUnexecSellOrder,
        boolean l_blnIsSettlement)
    {
        //パラメータ.指定日をint型に変換
        int l_intSpecifiedDate = this.calcCondtion.calcSpecifiedPoint(l_datSpecifiedDate);

        //指定日(int)をthis.顧客勘定推移にセットする。
        this.accountTransition.setSpecifiedDate(l_intSpecifiedDate);

        //パラメータ.指定日の前営業日を取得する。
        Date l_datPrevDate = this.calcCondtion.rollBizDate(l_datSpecifiedDate, -1);

        //預り金残高（指定日）を取得する。
        double l_dblSpecifiedCashBalance = this.cashValuation.calcCashBalance(l_datSpecifiedDate);

        //当日約定済代金（指定日）を取得する。
        double l_dblSpecifiedTodaysExecutedTotal =
            this.cashValuation.calcTodaysExecutedTotal(l_datSpecifiedDate);

        //当日約定済代金（指定日-1）を取得する。
        double l_dblPrevTodaysExecutedTotal =
            this.cashValuation.calcTodaysExecutedTotal(l_datPrevDate);

        //当日未約定代金（指定日）を取得する。
        double l_dblSpecifiedTodaysUnexecutedTotal =
            this.cashValuation.calcTodaysUnexecutedTotal(l_datSpecifiedDate);

        //当日未約定代金（指定日-1）を取得する。
        double l_dblPrevTodaysUnexecutedTotal =
            this.cashValuation.calcTodaysUnexecutedTotal(l_datPrevDate);

        //その他拘束金（指定日）を取得する
        double l_dblSpecifiedOtherRestraintsTotal =
            this.cashValuation.calcOtherRestraintsTotal(l_datSpecifiedDate);

        //その他拘束金（指定日-1）を取得する
        double l_dblPrevOtherRestraintsTotal =
            this.cashValuation.calcOtherRestraintsTotal(l_datPrevDate);

        //即日入金銘柄拘束金（指定日-1）を取得する
        double l_dblPrevTodayDepFundRestraint =
            this.cashValuation.getRestraint().calcTodayDepFundRestraint(l_datPrevDate);

        //即日入金銘柄拘束金（指定日）を取得する
        double l_dblSpecifiedTodayDepFundRestraint =
            this.cashValuation.getRestraint().calcTodayDepFundRestraint(l_datSpecifiedDate);

        //IPO拘束金（指定日-1）を取得する
        double l_dblPrevIPORestraint =
            this.cashValuation.getRestraint().calcIPORestraint(l_datPrevDate);

        //IPO拘束金（指定日）を取得する
        double l_dblSpecifiedIPORestraint =
            this.cashValuation.getRestraint().calcIPORestraint(l_datSpecifiedDate);

        //サービス利用拘束金（指定日-1）を取得する
        double l_dblPrevServiceRestraint =
            this.cashValuation.getRestraint().calcServiceChargeRestraint(l_datPrevDate);

        //サービス利用（指定日）を取得する
        double l_dblSpecifiedServiceRestraint =
            this.cashValuation.getRestraint().calcServiceChargeRestraint(l_datSpecifiedDate);       

        //投資信託前受拘束金（指定日-1）を取得する
        double l_dblPrevMutualFundAdvanceRestraint =
            this.cashValuation.getRestraint().calcMutualFundAdvanceRestraint(l_datPrevDate);

        //投資信託前受拘束金（指定日）を取得する
        double l_dblSpecifiedMutualFundAdvanceRestraint =
            this.cashValuation.getRestraint().calcMutualFundAdvanceRestraint(l_datSpecifiedDate);       
        
        //ストックオプション売付代金拘束金（指定日）を取得する
        double l_dblSpecifiedStockOptionSellAmountRestraint = 
            this.cashValuation.getRestraint().calcStockOptionSellAmountRestraint(l_datSpecifiedDate);
        
        //ストックオプション売付代金拘束金（指定日-1）を取得する
        double l_dbPrevlStockOptionSellAmountRestraint = 
            this.cashValuation.getRestraint().calcStockOptionSellAmountRestraint(l_datPrevDate);

        //get顧客勘定残高反映対象外拘束金種別一覧
        String[] l_accountCashBalanceReflectObjectRestraintDivList = 
            this.cashValuation.getRestraint().getAccountCashBalanceReflectObjectRestraintDivList();

        //calc仮拘束金(Date, String[])
        //[引数]
        //Date = 引数.指定日
        //String[] = 拘束金.get顧客勘定残高反映対象外拘束金種別一覧()の戻り値
        double l_dbTempRestraintSpecified =
            this.cashValuation.getRestraint().calcTempRestraint(
                l_datSpecifiedDate, l_accountCashBalanceReflectObjectRestraintDivList);

        //calc仮拘束金(Date, String[])
        //[引数]
        //Date = roll営業日()の戻り値
        //String[] = 拘束金.get顧客勘定残高反映対象外拘束金種別一覧()の戻り値
        double l_dbTempRestraintPrev =
            this.cashValuation.getRestraint().calcTempRestraint(
                l_datPrevDate, l_accountCashBalanceReflectObjectRestraintDivList);

        //出金要素集計<確定>（指定日）
        double l_dblSpecifiedFixedMinusTotal =
            this.cashValuation.getTransactionAmount().getFixedMinusTotal(l_datSpecifiedDate);

        //入金要素集計<確定>（指定日）
        double l_dblSpecifiedFixedPlusTotal =
            this.cashValuation.getTransactionAmount().getFixedPlusTotal(l_datSpecifiedDate);

        /*
         * 前受拘束金集計<日計り拘束金計上分>
         */
        double l_dblAdRestOffset = 0.0d;

        //指定日が、株の発注日以前の場合
        if(l_intSpecifiedDate <= this.calcCondtion.getEquityBizDate())
        {
            //前受拘束金集計<日計り拘束金計上分>を取得
            l_dblAdRestOffset = this.cashValuation.getRestraint().calcAdvanceRestraintOffset(
                l_datSpecifiedDate);
        }

        //※計算1
        //
        //その他拘束金出金要素(指定日-1)
        //=
        //その他拘束金(指定日-1)
        //-　@即日入金銘柄拘束金(指定日-1)
        //-　@IPO拘束金(指定日-1)
        //-　@サービス利用拘束金(指定日-1)
        //-　@投資信託前受拘束金(指定日-1)
        //-　@ストックオプション売付代金拘束金(指定日-1)
        //-　@仮拘束金(指定日-1)(*)
        //
        //(*)
        //仮拘束金(指定日-1) = calc仮拘束金(指定日-1、拘束金種別一覧)()の戻り値
        double l_dblPrevOtherRestraintsElement =
            l_dblPrevOtherRestraintsTotal
                - l_dblPrevTodayDepFundRestraint
                - l_dblPrevIPORestraint
                - l_dblPrevServiceRestraint
                - l_dblPrevMutualFundAdvanceRestraint
                - l_dbPrevlStockOptionSellAmountRestraint
                - l_dbTempRestraintPrev;

        //"指定日前日からの顧客勘定残高"
        //＝
        //預り金残高(指定日)　@
        //-　@当日約定済代金(指定日-1)　@
        //-　@当日未約定代金(指定日-1)
        //-　@その他拘束金出金要素(指定日-1)　@※計算1
        //+　@出金要素集計<確定>
        //-　@入金要素集計<確定>
        //
        //注）
        //前受拘束金は加味しない。
        double l_dblPrevDateBalance =
            l_dblSpecifiedCashBalance
                - l_dblPrevTodaysExecutedTotal
                - l_dblPrevTodaysUnexecutedTotal
                - l_dblPrevOtherRestraintsElement
                + l_dblSpecifiedFixedMinusTotal
                - l_dblSpecifiedFixedPlusTotal;

        //※計算2
        //
        //その他拘束金出金要素(指定日)
        //=
        //その他拘束金(指定日)
        //-　@即日入金対象銘柄拘束金(指定日)
        //-　@IPO拘束金(指定日)
        //-　@サービス利用拘束金(指定日)
        //-　@投資信託前受拘束金(指定日)
        //-　@ストックオプション売付代金拘束金(指定日)
        //-　@仮拘束金(指定日)(*)
        //
        //(*)
        //仮拘束金(指定日) = calc仮拘束金(指定日、拘束金種別一覧)()の戻り値
        double l_dblSpecifiedOtherRestraintsElement =
            l_dblSpecifiedOtherRestraintsTotal
                - l_dblSpecifiedTodayDepFundRestraint
                - l_dblSpecifiedIPORestraint
                - l_dblSpecifiedServiceRestraint
                - l_dblSpecifiedMutualFundAdvanceRestraint
                - l_dblSpecifiedStockOptionSellAmountRestraint
                - l_dbTempRestraintSpecified;

        //"指定日の顧客勘定残高"
        //＝
        //預り金残高(指定日)　@
        //-　@当日約定済代金(指定日)　@
        //-　@当日未約定代金(指定日)
        //-　@その他拘束金出金要素(指定日)　@※計算2
        //-　@前受拘束金集計<日計り拘束金計上分>(指定日)
        //注）
        //前受拘束金は加味しない。
        //ただし、発生日については日計り拘束金と相殺可とする。
        double l_dblSpecifiedDateBalance =
            l_dblSpecifiedCashBalance
                - l_dblSpecifiedTodaysExecutedTotal
                - l_dblSpecifiedTodaysUnexecutedTotal
                - l_dblSpecifiedOtherRestraintsElement
                - l_dblAdRestOffset;

        //指定日の繰越顧客勘定残高を顧客勘定推移にセットする。
        this.accountTransition.setPrevDateBalance(l_dblPrevDateBalance);

        //指定日の顧客勘定残高を顧客勘定推移にセットする。
        this.accountTransition.setSpecifiedDateBalance(l_dblSpecifiedDateBalance);

        //出金要素集計
        double l_dblSpecifiedMinusTotal =
            this.cashValuation.getTransactionAmount().getMinusTotal(l_datSpecifiedDate);

        //MAX(その他拘束金出金要素(指定日)　@※計算2
        //-　@その他拘束金出金要素(指定日-1)　@※計算1 , 0)
        double l_dblMaxOtherRestraints =
            Math.max(l_dblSpecifiedOtherRestraintsElement - l_dblPrevOtherRestraintsElement, 0);

        //"出金要素合計"
        //＝
        //出金要素集計(指定日)
        //+ MAX(その他拘束金出金要素(指定日)　@※計算2
        //-　@その他拘束金出金要素(指定日-1)　@※計算1 , 0)
        //+　@前受拘束金集計<日計り拘束金計上分>(指定日)
        //注）
        //前受拘束金は加味しない。
        //ただし、発生日については日計り拘束金と相殺可とする。
        this.accountTransition.setTotalPaymentAmount(
            l_dblSpecifiedMinusTotal
                + l_dblMaxOtherRestraints
                + l_dblAdRestOffset);

        //入金要素合計を顧客勘定推移にセットする。
        this.accountTransition.setTotalReceiptAmount(
            this.cashValuation.getTransactionAmount().getPlusTotal(l_datSpecifiedDate));

        //取得したいトランザクションタイプ配列を作成する。
        //現物買付、現物売付、信用現引、信用現渡、外国買い、外国売り
        FinTransactionType[] l_finTransactionTypes =
            {
                FinTransactionType.EQTYPE_EQUITY_BUY,
                FinTransactionType.EQTYPE_EQUITY_SELL,
                FinTransactionType.EQTYPE_SWAP_MARGIN_LONG,
                FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT,
                FinTransactionType.EQTYPE_FEQ_BUY,
                FinTransactionType.EQTYPE_FEQ_SELL
            };

        //株式取引情報を取得する。
        WEB3TPTransactionReflector[] l_transactionReflectors =
            this.cashValuation.getTransactionAmount().getEquityTransactions(
                l_finTransactionTypes,
                l_datSpecifiedDate);

        //銘柄ごと取引情報のリスト
        List l_lisSettlementReflectors = new ArrayList();

        //取引情報の配列の要素数を取得する。
        int l_intLength = l_transactionReflectors.length;

        //項番を0～”要素数-1”で繰り返し処理
        for (int index = 0; index < l_intLength; index++)
        {
            //取引情報(index)を取得する。
            WEB3TPTransactionReflector l_bufTransactionReflector = l_transactionReflectors[index];

            //税区分を取得する。 
            TaxTypeEnum l_taxType = l_bufTransactionReflector.getTaxType();
            //ストックオプション残高に対する取引の場合
            //(get税区分() == 5：ストックオプション)
            if (TaxTypeEnum.STOCK_OPTION.equals(l_taxType))
            {
                //以降の処理を行わずに次のLOOPへ
                continue;
            }
            
            //銘柄ID(index)を取得する
            long l_lngBufProductId = l_bufTransactionReflector.getProductId();
            //トランザクションタイプ(index)を取得する
            FinTransactionType l_bufFinTransactionType =
                l_bufTransactionReflector.getFinTransactionType();
            //約定済数量(index)を取得する
            double l_dblBufExecutedQuantity = l_bufTransactionReflector.getExecutedQuantity();
            //約定済代金(index)を取得する
            double l_dblBufExecutedAmount = Math.abs(l_bufTransactionReflector.getExecutedAmount());
            //未約定数量(index)を取得する
            double l_dblBufUnexecutedQuantity = l_bufTransactionReflector.getUnexecutedQuantity();
            //未約定代金(index)を取得する
            double l_dblBufUnexecutedAmount = Math.abs(l_bufTransactionReflector.getUnexecutedAmount());

            //リスト内に取引情報(index).銘柄IDと同じ銘柄IDをもつ銘柄ごと取引情報が存在するかどうかのフラグ
            boolean l_isFlg = false;
            //反復子を取得する。
            Iterator l_iterator = l_lisSettlementReflectors.iterator();
            //要素が存在し、フラグが「false」の間、繰り返し処理
            while (l_iterator.hasNext() && l_isFlg == false)
            {
                //銘柄ごと取引情報(カレント)を取得する。 
                WEB3TPSettlementReflector l_curSettlementReflector =
                    (WEB3TPSettlementReflector)l_iterator.next();
                //銘柄ごと取引情報(カレント).銘柄IDを取得する。
                long l_lngCurFundId = l_curSettlementReflector.getFundId();

                //取引情報(index).銘柄IDと取得した銘柄IDが等しい時
                if (l_lngBufProductId == l_lngCurFundId)
                {
                	WEB3TPSettlementReflector l_newSettlementReflector = null;
                	//差金決済相当外買付代金非考慮フラグ = true
                	if (l_blnIsSettlement)
                	{
                		//銘柄ごと取引情報<差金決済相当外買付代金非考慮>
                		WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount l_settlementRef =
                			new WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount();
                		l_newSettlementReflector = l_settlementRef;
                	}
                	else
                	{
                        //新しい銘柄ごと取引情報を作成する。
                        l_newSettlementReflector =
                            new WEB3TPSettlementReflector();
                	}
                    //新しい銘柄ごと取引情報に取引情報をセットする。
                    l_newSettlementReflector = l_curSettlementReflector;

                    //現物買付または信用現引 或いは"外株買い"の時
                    if (l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_EQUITY_BUY)
                        == true
                        || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_SWAP_MARGIN_LONG)
                            == true
                        || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_FEQ_BUY))
                    {
                        //買付数量（= 約定済数量(index) + 未約定数量(index) + 銘柄ごと取引情報.買付数量）を計算する。
                        double l_dblBuyQuantity =
                            l_dblBufExecutedQuantity
                                + l_dblBufUnexecutedQuantity
                                + l_curSettlementReflector.getBuyQuantity();

                        //買付代金（= 約定済代金(index) + 未約定代金(index) + 銘柄ごと取引情報.買付代金）を計算する。
                        double l_dblBuyAmount =
                            l_dblBufExecutedAmount
                                + l_dblBufUnexecutedAmount
                                + l_curSettlementReflector.getBuyAmount();

                        //新しい銘柄ごと取引情報に買付数量をセットする。
                        l_newSettlementReflector.setBuyQuantity(l_dblBuyQuantity);
                        //新しい銘柄ごと取引情報に買付代金をセットする。
                        l_newSettlementReflector.setBuyAmount(l_dblBuyAmount);

                    }
                    //現物売付 或いは"外株売り"の時
                    else if (
                        l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_EQUITY_SELL)
                            == true
                        || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_FEQ_SELL))
                    {
                        //売付数量（= 約定済数量(index) + 銘柄ごと取引情報.売付数量）を計算する。
                        double l_dblSellQuantity =
                            l_dblBufExecutedQuantity + l_curSettlementReflector.getSellQuantity();

                        //売付代金（= 約定済代金(index) + 銘柄ごと取引情報.売付代金）を計算する。
                        double l_dblSellAmount =
                            l_dblBufExecutedAmount + l_curSettlementReflector.getSellAmount();

                        /*
                         * 未約定売付数量（= 未約定数量(index) + 銘柄ごと取引情報.未約定売付数量）を計算する。
                         */
                        double l_dblUnexecutedSellQuantity;

                        //未約定売付注文ロードフラグ == trueの場合
                        if(l_blnUnexecSellOrder == true)
                        {
                            //未約定売付数量 = 未約定数量(index) + 銘柄ごと取引情報.未約定売付数量
                            l_dblUnexecutedSellQuantity = l_dblBufUnexecutedQuantity
                                    + l_curSettlementReflector
                                        .getUnexecutedSellQuantity();
                        }
                        //以外（未約定売付注文ロードフラグ == false）の場合
                        else
                        {
                            //未約定売付数量 = 0
                            l_dblUnexecutedSellQuantity = 0.0d;
                        }
                           
                        //新しい銘柄ごと取引情報に売付数量をセットする
                        l_newSettlementReflector.setSellQuantity(l_dblSellQuantity);
                        //新しい銘柄ごと取引情報に売付代金をセットする
                        l_newSettlementReflector.setSellAmount(l_dblSellAmount);
                        //新しい銘柄ごと取引情報に未約定売付数量をセットする
                        l_newSettlementReflector.setUnexecutedSellQuantity(
                            l_dblUnexecutedSellQuantity);

                    }
                    //信用現渡の時
                    else if (
                        l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT)
                            == true)
                    {
                        //売付数量（= 約定済数量(index) + 未約定売付数量(index) + 銘柄ごと取引情報.売付数量）を計算する。
                        double l_dblSellQuantity =
                            l_dblBufExecutedQuantity
                                + l_dblBufUnexecutedQuantity
                                + l_curSettlementReflector.getSellQuantity();

                        //売付代金（= 約定済代金(index) + 未約定売付代金(index) + 銘柄ごと取引情報.売付代金）を計算する。
                        double l_dblSellAmount =
                            l_dblBufExecutedAmount
                                + l_dblBufUnexecutedAmount
                                + l_curSettlementReflector.getSellAmount();

                        //新しい銘柄ごと取引情報に売付数量をセットする
                        l_newSettlementReflector.setSellQuantity(l_dblSellQuantity);
                        //新しい銘柄ごと取引情報に売付代金をセットする
                        l_newSettlementReflector.setSellAmount(l_dblSellAmount);

                    }

                    //古い銘柄ごと取引情報をリストより削除する。
                    l_iterator.remove();
                    //新しい銘柄ごと取引情報をリストに追加する。
                    l_lisSettlementReflectors.add(l_newSettlementReflector);
                    //フラグにTRUEを代入する。
                    l_isFlg = true;

                }
            }

            if (l_isFlg == false)
            {
            	WEB3TPSettlementReflector l_newSettlementReflector = null;
            	//差金決済相当外買付代金非考慮フラグ = true
            	if (l_blnIsSettlement)
            	{
            		//銘柄ごと取引情報<差金決済相当外買付代金非考慮>
            		WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount l_settlementRef =
            			new WEB3TPSettlementReflectorExcludeExceptSettleBuyAmount();
            		l_newSettlementReflector = l_settlementRef;
            	}
            	else
            	{
                    //新しい銘柄ごと取引情報を作成する。
                    l_newSettlementReflector =
                        new WEB3TPSettlementReflector();
            	}

                /*
                 * 既存保有数量<確定>を取得
                 */
                //対象銘柄を取得する。
                WEB3TPSecurityValuationProduct l_valuationProduct =
                    this.securityValuation.getProduct(l_lngBufProductId,WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //銘柄ごと証券評価額を取得する。
                WEB3TPSecurityValuationPerProduct l_valuationPerProduct =
                    this.securityValuation.getSecurityValuationPerProduct(l_valuationProduct);

                //既存保有数量<確定>
                double l_dblExistQuantity;
                if(l_valuationPerProduct != null)
                {
                    //指定日前日の既存保有数量<確定>を取得する。
                    double l_dblAssetQuantity = l_valuationPerProduct
                        .calcAssetQuantity(
                                l_datPrevDate, WEB3TPDepositTypeDef.TRUST);

                    //指定日前日の確定取引数量<当日以降>を取得する。
                    double l_dblExecutedTransactionQuantity = l_valuationPerProduct
                        .calcExecutedTransactionQuantity(l_datPrevDate);

                    //既存保有数量<確定> = 指定日前日の既存保有数量<確定> + 指定日前日の確定取引数量<当日以降>
                    l_dblExistQuantity = l_dblAssetQuantity
                            + l_dblExecutedTransactionQuantity;
                }
                else
                {
                    l_dblExistQuantity = 0.0d;
                }
                
                /*
                 * 新しい銘柄ごと取引情報のプロパティに値をセット
                 */
                //新しい銘柄ごと取引情報に銘柄IDをセットする。
                l_newSettlementReflector.setFundId(l_lngBufProductId);
                //新しい銘柄ごと取引情報に指定日前日保有数量をセットする。
                l_newSettlementReflector.setExistQuantity(l_dblExistQuantity);

                //現物買付または信用現引 或いは"外株買い"の時
                if (l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_EQUITY_BUY) == true
                    || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_SWAP_MARGIN_LONG)
                        == true
                    || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_FEQ_BUY))
                {
                    //買付数量（= 約定済数量(index) + 未約定数量(index)）を計算する。
                    double l_dblBuyQuantity = l_dblBufExecutedQuantity + l_dblBufUnexecutedQuantity;

                    //買付代金（= 約定済代金(index) + 未約定代金(index)）を計算する。
                    double l_dblBuyAmount = l_dblBufExecutedAmount + l_dblBufUnexecutedAmount;

                    //新しい銘柄ごと取引情報に買付数量をセットする。
                    l_newSettlementReflector.setBuyQuantity(l_dblBuyQuantity);
                    //新しい銘柄ごと取引情報に買付代金をセットする。
                    l_newSettlementReflector.setBuyAmount(l_dblBuyAmount);

                }
                //現物売付  或いは"外株売り"の時
                else if (
                    l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_EQUITY_SELL) == true
                    || l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_FEQ_SELL))
                {
                    //売付数量（= 約定済数量(index)）を計算する。
                    double l_dblSellQuantity = l_dblBufExecutedQuantity;

                    //売付代金（= 約定済代金(index)）を計算する。
                    double l_dblSellAmount = l_dblBufExecutedAmount;

                    /*
                     * 未約定売付数量（= 未約定数量(index)）を計算する。
                     */
                    double l_dblUnexecutedSellQuantity;

                    //未約定売付注文ロードフラグ == trueの場合
                    if(l_blnUnexecSellOrder == true)
                    {
                        //未約定売付数量　@= 未約定数量(index)
                        l_dblUnexecutedSellQuantity = l_dblBufUnexecutedQuantity;
                    }
                    //以外（未約定売付注文ロードフラグ == false）の場合
                    else
                    {
                        //未約定売付数量 = 0
                        l_dblUnexecutedSellQuantity = 0.0d;
                    }

                    //新しい銘柄ごと取引情報に売付数量をセットする
                    l_newSettlementReflector.setSellQuantity(l_dblSellQuantity);
                    //新しい銘柄ごと取引情報に売付代金をセットする
                    l_newSettlementReflector.setSellAmount(l_dblSellAmount);
                    //新しい銘柄ごと取引情報に未約定売付数量をセットする
                    l_newSettlementReflector.setUnexecutedSellQuantity(l_dblUnexecutedSellQuantity);

                }
                //信用現渡の時
                else if (
                    l_bufFinTransactionType.equals(FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT)
                        == true)
                {
                    //売付数量（= 約定済数量(index) + 未約定売付数量(index)）を計算する。
                    double l_dblSellQuantity =
                        l_dblBufExecutedQuantity + l_dblBufUnexecutedQuantity;

                    //売付代金（= 約定済代金(index) + 未約定売付代金(index)）を計算する。
                    double l_dblSellAmount = l_dblBufExecutedAmount + l_dblBufUnexecutedAmount;

                    //新しい銘柄ごと取引情報に売付数量をセットする
                    l_newSettlementReflector.setSellQuantity(l_dblSellQuantity);
                    //新しい銘柄ごと取引情報に売付代金をセットする
                    l_newSettlementReflector.setSellAmount(l_dblSellAmount);

                }

                /*
                 * 新しい銘柄ごと取引情報をリストに追加する。
                 */
                l_lisSettlementReflectors.add(l_newSettlementReflector);

            }
        }

        //銘柄ごと取引情報のリストを顧客勘定推移にセットする。
        this.accountTransition.setLisSettlementReflectors(l_lisSettlementReflectors);
    }
}
@
