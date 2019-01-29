head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引余力サービスインタフェース(WEB3TPTradingPowerService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 nakazato(ACT) 新規作成
                   2006/09/11 徐宏偉 (中訊) モデルNo.005
                   2006/11/13 徐大方 (中訊) モデルNo.073
Revesion History : 2007/07/12 金傑 (中訊) モデルNo.106、モデルNo.108
Revesion History : 2007/07/19 孟亜南 (中訊) モデルNo.109
Revesion History : 2007/08/06 金傑 (中訊) モデルNo.123
Revesion History : 2007/09/20 孟亜南 (中訊) モデルNo.169
Revesion History : 2007/10/22 趙林鵬（中訊）モデルNo.210
Revesion History : 2008/01/18 トウ鋒鋼 (中訊) モデルNo.247
Revesion History : 2009/12/11 張騰宇 (中訊) モデルNo.404 モデルNo.405
Revesion History : 2010/01/13 武波　@ (中訊)モデルNo.440
Revesion History : 2010/01/28 武波　@ (中訊)モデルNo.448
*/
package webbroker3.tradingpower;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailRow;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;

/**
 * (取引余力サービス)
 * 取引余力サービスインターフェース
 */
public interface WEB3TPTradingPowerService extends Service
{

    /**
     * (validate取引余力)<BR>
     * 
     * 引数.注文種別において指定された注文の取引余力チェックを実施する。<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@param l_orderSpecIntercepter - (注文内容インタセプタ)
     * @@param l_orderSpec - (注文内容)
     * @@param l_orderTypeEnum - (注文種別)
     * @@param l_blnUpdateFlg - (余力更新フラグ)
     * trueの時、余力再計算処理を実施する
     * falseの時、余力再計算処理を実施しない
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     * @@roseuid 41786F070261
     */
    public WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        Object[] l_orderSpecIntercepter,
        Object[] l_orderSpec,
        OrderTypeEnum l_orderTypeEnum,
        boolean l_blnUpdateFlg)
        throws WEB3SystemLayerException;

    /**
     * (get株式買付可能額)<BR>
     * 
     * 現物株式買付可能額を取得する。<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@return double
     * @@roseuid 41786F070280
     */
    public double getEquityTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get信用現引可能額)<BR>
     * 
     * 信用現引可能額を取得する。<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@return double
     * @@roseuid 41786F07029F
     */
    public double getActualReceiptTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get信用新規建可能額)<BR>
     * 
     * 信用新規建可能額を取得する。<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@return double
     * @@roseuid 41786F0702CE
     */
    public double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (getオプション新規買建可能額)<BR>
     * 
     * オプション新規建可能額を取得する。<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@param l_ifoProduct - (銘柄)
     * @@return Double
     */
    public Double getOptionBuyTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        IfoProduct l_ifoProduct)
        throws WEB3SystemLayerException;

    /**
     * (get先物オプション新規建可能額)<BR>
     * 
     * 先物オプション新規建可能額を取得する<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@param l_blnLongFlg - (is買建)
     * true：買建　@false：売建
     * @@param l_ifoProduct - (銘柄)
     * 銘柄指定時のみ設定。以外、null。
     * @@return Double
     * @@roseuid 41786F07030D
     */
    public Double getFuturesOptionTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        boolean l_blnLongFlg,
        IfoProduct l_ifoProduct)
        throws WEB3SystemLayerException;

    /**
     * (getその他商品買付可能額)<BR>
     * 
     * その他商品買付可能額を取得する<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     * @@roseuid 41786F07032C
     */
    public double getOtherTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException;

    /**
     * (getその他商品買付可能額～0補正無し～)<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日
     * @@return double
     */
    public double getOtherTradingPowerForCheck(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException;

    /**
     * (get出金可能額～0補正有り～)<BR>
     * 
     * 出金可能額を取得する（0補正有り）<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     */
    public double getPaymentTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException;

    /**
     * (get出金可能額～0補正無し～)<BR>
     * 
     * 出金可能額を取得する（0補正なし）<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     */
    public double getPaymentTradingPowerForCheck(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException;
    
    /**
     * (get投資信託買付可能額)<BR>
     * 
     * 投信買付可能額を取得する<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_orderTypeEnum - (注文種別)
     * @@return double
     */
    public double getMutualFundBuyTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate,
        OrderTypeEnum l_orderTypeEnum)
        throws WEB3SystemLayerException;

    /**
     * (get預り金への振替額)<BR>
     * 
     * 預り金への振替額を取得する<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@param l_dblNecessaryCash - (当日必要現金)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     * @@roseuid 41786F07034B
     */
    public double getTransferAmountToEquitySubAcount(
        WEB3GentradeSubAccount l_subAccount,
        double l_dblNecessaryCash,
        Date l_datDeliveryDate)
        throws WEB3SystemLayerException;

    /**
     * (余力再計算)<BR>
     * 
     * 余力再計算処理を処理を実施する<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@roseuid 41786F07037A
     */
    public void reCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get資産余力情報<現物顧客>)<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get資産余力情報<信用顧客>)<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get資産余力情報<現物顧客>～時価評価～)<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquityQuote(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get資産余力情報<信用顧客>～時価評価～)<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMarginQuote(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get資産余力情報<現物顧客>)<BR>
     * 
     * @@param l_lngCalcResultId - (余力計算結果ID)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(long l_lngCalcResultId)
        throws WEB3SystemLayerException;

    /**
     * (get資産余力情報<信用顧客>)<BR>
     * 
     * @@param l_lngCalcResultId - (余力計算結果ID)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(long l_lngCalcResultId)
        throws WEB3SystemLayerException;

    /**
     * (get資産余力情報<現物顧客>)<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@param l_resultRow - (余力計算結果<現物顧客>)
     * @@param l_resultDetailRow - (余力計算結果詳細<現物顧客>)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultEquityRow l_resultRow,
        TpCalcResultEquityDetailRow l_resultDetailRow)
        throws WEB3SystemLayerException;

    /**
     * (get資産余力情報<信用顧客>)<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@param l_resultRow - (余力計算結果<信用顧客>)
     * @@param l_resultDetailRow - (余力計算結果詳細<信用顧客>)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultMarginRow l_resultRow,
        TpCalcResultMarginDetailRow l_resultDetailRow)
        throws WEB3SystemLayerException;

    /**
     * (get二階建銘柄一覧)<BR>
     * 
     * @@param l_subAccount - (補助口座)
     * @@return WEB3TPMarginSecurityInfo[]
     */
    public WEB3TPMarginSecurityInfo[] getMarginSecurityInfo(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (getその他商品買付可能額～余力計算結果ROW指定～)
     * <BR>
     * 余力計算結果Rowならびに余力計算結果詳細Rowを指定して<BR>
     * その他商品買付可能額を取得する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_tpCalcResultEquityRow - (余力計算結果<現物顧客>Row)
     * @@param l_tpCalcResultEquityDetailRow - (余力計算結果詳細<現物顧客>Row)
     * @@param l_tpCalcResultMarginRow - (余力計算結果<信用顧客>Row)
     * @@param l_tpCalcResultMarginDetailRow - (余力計算結果詳細<信用顧客>Row)l_subAccount - (補助口座)
     * @@return double
     */
    public double getOtherTradingPower(
            WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate,
            TpCalcResultEquityRow l_tpCalcResultEquityRow,
            TpCalcResultEquityDetailRow l_tpCalcResultEquityDetailRow,
            TpCalcResultMarginRow l_tpCalcResultMarginRow,
            TpCalcResultMarginDetailRow l_tpCalcResultMarginDetailRow)
            throws WEB3SystemLayerException;
    
    /**
     * (get株式買付可能額～連続注文～)<BR>
     * 連続注文用の株式買付可能額を計算し返却する。<BR>
     * <BR>
     * 「返却値 >= 概算受渡代金」⇒取引OK<BR>
     * 「返却値 <  概算受渡代金」⇒取引NG<BR>
     * 注）返却値 >= 0 とする。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_estimatedPrice - (訂正前概算受渡代金)
     * @@return double
     */
    public double getSuccEquityTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Date l_datDeliveryDate,
            Double l_estimatedPrice) throws WEB3SystemLayerException;

    /**
     * (get信用新規建可能額～連続注文～)<BR>
     * 連続注文用の信用新規建可能額を計算し返却する。<BR>
     * <BR>
     * 「返却値 >= 概算受渡代金」⇒取引OK<BR>
     * 「返却値 <  概算受渡代金」⇒取引NG<BR>
     * 注）返却値 >= 0 とする。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_estimatedPrice - (訂正前概算受渡代金)
     * @@return double
     */
    public double getSuccMarginTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Double l_estimatedPrice) throws WEB3SystemLayerException;

    /**
     * (get信用現引可能額～連続注文～)<BR>
     * 連続注文用の信用現引可能額を計算し返却する。<BR>
     * <BR>
     * 「返却値 >= 概算受渡代金」⇒取引OK<BR>
     * 「返却値 <  概算受渡代金」⇒取引NG<BR>
     * 注）返却値 >= 0 とする。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     */
    public double getSuccActualReceiptTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Date l_datDeliveryDate) throws WEB3SystemLayerException;
    
    /**
     * (get大証金への振替可能額) <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getOsakaTransferableTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Date l_datDeliveryDate) throws WEB3SystemLayerException;

    /**
     * (get出金可能額&lt;DB時価&gt;～0補正無し～)<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getPaymentTradingPowerDBQuote(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate) throws WEB3SystemLayerException;
    
    /**
     * (get保証金への振替額) <BR>
     * <BR>
     * 保証金への振替額を取得する。<BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get保証金への振替額」参照 <BR>
     * <BR>
     * ※指定された顧客が信用口座未開設の時、保証金への振替額(=0)を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_dblPayAmount - (入金額)
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getTransferAmountToDeposit(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate,
        double l_dblPayAmount) throws WEB3BaseException;

    /**
     * (get外貨残高情報) <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_strCurrencyCode - (通貨コード)
     * @@return WEB3ForeignPositionContract
     * @@throws WEB3SystemLayerException
     */
    public WEB3ForeignPositionContract getForeignPositionContract(
        WEB3GentradeSubAccount l_subAccount,String l_strCurrencyCode) throws WEB3SystemLayerException;
    
    /**
     * (get担保ローン振替可能額) <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getSLChangePossAmt(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException;

    /**
     * (validate建玉強制決済～オンライン開始前～) <BR>
     * validate建玉強制決済～オンライン開始前～
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettleBefOnline(
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (validate建玉強制決済～場間～)<BR>
     * validate建玉強制決済～場間～<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettleIntermission(
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (get翌日不足金)<BR>
     * (get翌日不足金) <BR>
     * <BR>
     * シーケンス図「(取引余力サービス)get翌日不足金」参照 <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getNextBizDateShortfall(WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException;

    /**
     * (get当日保証金引出余力)<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_strDbCurrentPriceCheckDiv - (DB時価余力チェック区分)<BR>
     * 補助口座<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getTodayDepositWithdrawTradingPower(
        WEB3GentradeSubAccount l_subAccount, String l_strDbCurrentPriceCheckDiv)
        throws WEB3BaseException;

    /**
     * (get出金可能額～出金入力画面表示用～)<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getPaymentTradingPowerAioCashoutInput(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate) throws WEB3BaseException;
}
@
