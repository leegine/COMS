head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAccountTransition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客勘定推移(WEB3TPAccountTransition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 nakazato(ACT) 新規作成
Revesion History : 2007/10/22 トウ鋒鋼（中訊）仕様変更モデルNo.214　@計算式書014
*/
package webbroker3.tradingpower.updtpower.settlement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （顧客勘定推移）
 * 
 * 指定日の顧客勘定推移を表現するクラス
 */
public class WEB3TPAccountTransition
{
    /*
     * ログ出力ユーティリティー
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPAccountTransition.class);

    /**
     * （指定日）
     */
    private int specifiedDate;

    /**
     * （指定日前日からの顧客勘定残高）
     */
    private double prevDateBalance;

    /**
     * （指定日の顧客勘定残高）
     */
    private double specifiedDateBalance;

    /**
     * （入金要素合計）
     */
    private double totalReceiptAmount;

    /**
     * （出金要素合計）
     */
    private double totalPaymentAmount;

    /**
     * （銘柄ごとの取引情報）
     * 
     * 指定日に受渡が行われる取引の銘柄ごと取引情報オブジェクトのリスト
     */
    private List lisSettlementReflectors;

    /**
     * （顧客勘定推移）<BR>
     * 
     * コンストラクタ。<BR>
     */
    public WEB3TPAccountTransition()
    {
        //フィールドの初期化
        this.specifiedDate = 0;
        this.prevDateBalance = 0.0;
        this.specifiedDateBalance = 0.0;
        this.totalReceiptAmount = 0.0;
        this.totalPaymentAmount = 0.0;
        this.lisSettlementReflectors = new ArrayList();
    }

    /**
     * （calc日計り拘束金）<BR>
     * <BR>
     * 0補正有りの日計り拘束金を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@return double
     * @@roseuid 40C4296C0172
     */
    public double calcDayTradeRestraint()
    {
        final String STR_METHOD_NAME = "calcDayTradeRestraint()";
        log.entering(STR_METHOD_NAME);

        //0補正無しの日計り拘束金を取得する。
        double l_dblDayTradeRestraint = this.calcDayTradeRestraintForCheck();

        //日計り拘束金を0補正する。
        l_dblDayTradeRestraint = Math.max(l_dblDayTradeRestraint, 0.0);

        //日計り拘束金を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblDayTradeRestraint;
    }

    /**
     * (calc日計り拘束金～0補正無し～)<BR>
     * <BR>
     * 0補正無しの日計り拘束金を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * <BR>
     * @@return double
     */
    public double calcDayTradeRestraintForCheck()
    {
        final String STR_METHOD_NAME = "calcDayTradeRestraintForCheck()";
        log.entering(STR_METHOD_NAME);

        //差金決済情報リスト
        List l_lisSettlementReflectors = new ArrayList();
        //その他出金要素
        double l_dblOtherPaymentAmount = this.totalPaymentAmount;

        //銘柄ごと取引情報のバッファ@領域
        WEB3TPSettlementReflector l_bufSettlementReflector = null;
        //銘柄ごと取引情報のリストの要素数
        int l_intSize = this.lisSettlementReflectors.size();

        //項番を0～”要素数-1”で繰り返し処理
        for (int index = 0; index < l_intSize; index++)
        {
            //リストより、銘柄ごと取引情報(index)を取得する。
            l_bufSettlementReflector =
                (WEB3TPSettlementReflector)this.lisSettlementReflectors.get(index);

            //銘柄ごと取引情報(index)が差金決済対象銘柄の時
            if (l_bufSettlementReflector.validateSettlementFundForDayTrade())
            {
                //差金決済情報リストに銘柄ごと取引情報(index)を追加する。
                l_lisSettlementReflectors.add(l_bufSettlementReflector);

                //銘柄ごと取引情報(index).差金決済買付代金）を取得する。
                double l_dblIndexSettlementBuyAmount =
                    l_bufSettlementReflector.calcSettlementBuyAmount();

                //その他出金要素（= その他出金要素 - 銘柄ごと取引情報(index).差金決済買付代金）を計算する。
                l_dblOtherPaymentAmount = l_dblOtherPaymentAmount - l_dblIndexSettlementBuyAmount;
            }
        }

        //銘柄ごと取引情報のリストの要素数取得
        int l_intSizeArray = l_lisSettlementReflectors.size();

        //差金決済情報リストを配列に変換する
        WEB3TPSettlementReflector l_settlementReflectors[] = new WEB3TPSettlementReflector[l_intSizeArray];

        //配列にリストを格納する
        for (int RoopCnt = 0; RoopCnt < l_intSizeArray; RoopCnt++)
        {
            l_settlementReflectors[RoopCnt] = (WEB3TPSettlementReflector)l_lisSettlementReflectors.get(RoopCnt);
        }

        //決済順序決定ルールに従ってソートする。
        this.calcSettlementOrder(l_settlementReflectors);

        //日計り拘束金
        double l_dblDayTradeRestraint = 0.0;
        //日計り拘束金のバッファ@領域
        double l_dblBufDayTradeRestraint = 0.0;
        //差金決済情報の要素数
        int l_intLength = l_settlementReflectors.length;

        //日計り拘束金に初期値をセットする。
        if (l_intLength == 0)
        {
            l_dblDayTradeRestraint = 0.0;
        }
        else
        {
            l_dblDayTradeRestraint = -Double.MAX_VALUE;
        }

        //項番を”要素数-1”～0で繰り返し処理
        for (int index = (l_intLength - 1); 0 <= index; index--)
        {
            //項番が”要素数-1”の時
            if (index == (l_intLength - 1))
            {
                //差金決済売付代金(index)を取得する。
                double l_dblIndexSettlementSellAmount =
                    l_settlementReflectors[index].calcSettlementSellAmount();

                //日計り拘束金(index)（= 差金決済売付代金(index) - その他出金要素）を計算する。
                l_dblBufDayTradeRestraint =
                    l_dblIndexSettlementSellAmount - l_dblOtherPaymentAmount;
            }
            //それ以外
            else
            {
                //差金決済売付代金(index)を取得する。
                double l_dblIndexSettlementSellAmount =
                    l_settlementReflectors[index].calcSettlementSellAmount();

                //差金決済買付代金(index+1)を取得する。
                double l_dblIndexSettlementBuylAmount =
                    l_settlementReflectors[index + 1].calcSettlementBuyAmount();

                //日計り拘束金(index)（= 差金決済売付代金(index) - 差金決済買付代金(index+1) + 日計り拘束金(index+1)）を計算する。
                l_dblBufDayTradeRestraint =
                    l_dblIndexSettlementSellAmount
                        - l_dblIndexSettlementBuylAmount
                        + l_dblBufDayTradeRestraint;
            }

            //日計り拘束金と日計り拘束金(index)の大きい方を日計り拘束金として採用する。
            l_dblDayTradeRestraint = Math.max(l_dblDayTradeRestraint, l_dblBufDayTradeRestraint);
        }

        //日計り拘束金を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblDayTradeRestraint;
    }

    /**
     * （calc差金決済売付可能数量）<BR>
     * 
     * 差金決済売付可能数量を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@param l_lngOrderFundId - （注文銘柄ID）
     * @@param l_dblOrderQuantity - （注文数量）
     * @@param l_dblLotSize - （売買単位）
     * @@param l_dblOrderExistQuantity - （注文銘柄既存保有数量<確定>）
     * @@return WEB3TPSellOrderPossibleQuantityResult
     * @@roseuid 4100BB0700AB
     */
    public WEB3TPSellOrderPossibleQuantityResult calcSellOrderPossibleQuantity(
        long l_lngOrderFundId,
        double l_dblOrderQuantity,
        double l_dblLotSize,
        double l_dblOrderExistQuantity)
    {
        final String STR_METHOD_NAME = "calcSellOrderPossibleQuantity(long, double, double, double)";
        log.entering(STR_METHOD_NAME);

        //注文銘柄情報
        WEB3TPSettlementReflector l_orderReflector = null;
        //差金決済情報リスト
        List l_lisSettlementReflectors = new ArrayList();
        //その他入金要素
        double l_dblOtherReceiptAmount = this.totalReceiptAmount;
        //売付可能数量
        double l_dblSellPossQuantity = 0.0;

        //銘柄ごと取引情報のバッファ@領域
        WEB3TPSettlementReflector l_bufSettlementReflector = null;
        //銘柄ごと取引情報のリストの要素数
        int l_intSize = this.lisSettlementReflectors.size();

        //項番を0～”要素数-1”で繰り返し処理
        for (int index = 0; index < l_intSize; index++)
        {
            //銘柄ごと取引情報(index)を取得する。
            l_bufSettlementReflector =
                (WEB3TPSettlementReflector)this.lisSettlementReflectors.get(index);

            //パラメータ.銘柄IDと銘柄ごと取引情報(index).銘柄IDが等しい時
            if (l_lngOrderFundId == l_bufSettlementReflector.getFundId())
            {
                //注文銘柄情報に銘柄ごと取引情報(index)を代入する。
                l_orderReflector = l_bufSettlementReflector;
                //ループから抜ける。
                index = l_intSize;
            }
        }

        //注文銘柄情報を取得できた時
        if (l_orderReflector != null)
        {
            //注文銘柄情報.指定日の売付数量（注文数量 + 売付数量 + 未約定売付数量）を計算する。
            double l_dblSellQuantity =
                l_dblOrderQuantity
                    + l_orderReflector.getSellQuantity()
                    + l_orderReflector.getUnexecutedSellQuantity();

            //注文銘柄情報.指定日の売付数量（BigDecimal型）                   
            BigDecimal l_bdSellQuantity = new BigDecimal(l_dblSellQuantity);
            //注文銘柄情報.買付数量（BigDecimal型）
            BigDecimal l_bdBuyQuantity = new BigDecimal(l_orderReflector.getBuyQuantity());
            //注文銘柄情報.指定日前日保有数量（BigDecimal型）
            BigDecimal l_bdExistQuantity = new BigDecimal(l_orderReflector.getExistQuantity());
            //this.指定日の顧客勘定残高（BigDecimal型）
            BigDecimal l_bdSpecifiedDateBalance = new BigDecimal(this.specifiedDateBalance);

            //注文銘柄情報.買付数量が0、または、
            //this.指定日の顧客勘定残高が0以上かつ、
            //注文銘柄情報.指定日前日保有数量が注文銘柄情報.指定日の売付数量以上、または
            //this.指定日 = T+5 の時（差金決済対象外）
            if (l_bdBuyQuantity.compareTo(new BigDecimal(0.0)) == 0
                || (l_bdSpecifiedDateBalance.compareTo(new BigDecimal(0.0)) >= 0
                    && l_bdExistQuantity.compareTo(l_bdSellQuantity) >= 0)
                || this.specifiedDate == WEB3TPSpecifiedPointDef.T_5)
            {
                //売付可能数量（指定日前日保有数量 + 買付数量　@- 売付数量　@- 未約定売付数量）を計算する。
                l_dblSellPossQuantity =
                    l_orderReflector.getExistQuantity()
                        + l_orderReflector.getBuyQuantity()
                        - l_orderReflector.getSellQuantity()
                        - l_orderReflector.getUnexecutedSellQuantity();

                /*
                 * 差金決済売付可能数量結果オブジェクトへ値をセット
                 */
                WEB3TPSellOrderPossibleQuantityResult l_result = new WEB3TPSellOrderPossibleQuantityResult();
                l_result.dayTradeFundFlg = false;
                l_result.sellPossQuantity = Math.max(l_dblSellPossQuantity, 0.0);
                l_result.lackAmt = 0.0;

                //差金決済売付可能数量オブジェクトを返却する
                log.exiting(STR_METHOD_NAME);
                return l_result;

            }
            //それ以外（差金決済対象）
            else
            {
                //注文銘柄情報を差金決済情報リストに追加する
                l_lisSettlementReflectors.add(l_orderReflector);

                //注文銘柄情報.差金決済売付代金を取得する。
                double l_dblOrderSettlementSellAmount = l_orderReflector.calcSettlementSellAmount();

                //その他入金要素（= その他入金要素 - 注文銘柄情報.差金決済売付代金）を計算する
                l_dblOtherReceiptAmount = l_dblOtherReceiptAmount - l_dblOrderSettlementSellAmount;
            }
        }
        //注文銘柄情報を取得できなかった時
        else
        {
            //売付可能数量（= パラメータ.注文銘柄既存保有数量<確定>）を返却する
            l_dblSellPossQuantity = l_dblOrderExistQuantity;

            /*
             * 差金決済売付可能数量結果オブジェクトへ値をセット
             */
            WEB3TPSellOrderPossibleQuantityResult l_result = new WEB3TPSellOrderPossibleQuantityResult();
            l_result.dayTradeFundFlg = false;
            l_result.sellPossQuantity = Math.max(l_dblSellPossQuantity, 0.0);
            l_result.lackAmt = 0.0;

            //差金決済売付可能数量オブジェクトを返却する
            log.exiting(STR_METHOD_NAME);
            return l_result;
        }

        //項番を0～”要素数-1”で繰り返し処理
        for (int index = 0; index < l_intSize; index++)
        {
            //銘柄ごと取引情報(index)を取得する。
            l_bufSettlementReflector =
                (WEB3TPSettlementReflector)this.lisSettlementReflectors.get(index);

            //銘柄ごと取引情報(index)が、差金決済対象銘柄であり、注文銘柄情報でない時
            if (l_bufSettlementReflector.validateSettlementFundForDayTrade() == true
                && l_bufSettlementReflector.getFundId() != l_lngOrderFundId)
            {
                //差金決済情報リストに銘柄ごと取引情報(index)を追加する
                l_lisSettlementReflectors.add(l_bufSettlementReflector);

                //銘柄ごと取引情報(index).差金決済売付代金を取得する。
                double l_dblIndexSettlementSellAmount =
                    l_bufSettlementReflector.calcSettlementSellAmount();

                //その他入金要素（= その他入金要素 - 銘柄ごと取引情報(index).差金決済売付代金）を計算する。
                l_dblOtherReceiptAmount = l_dblOtherReceiptAmount - l_dblIndexSettlementSellAmount;
            }
        }

        //差金決済情報リストの要素数の取得
        int l_intSizeArray = l_lisSettlementReflectors.size();

        //差金決済情報リストを配列に変換する
        WEB3TPSettlementReflector l_settlementReflectors[] = new WEB3TPSettlementReflector[l_intSizeArray];

        //配列にリストを格納する
        for (int RoopCnt = 0; RoopCnt < l_intSizeArray; RoopCnt++)
        {
            l_settlementReflectors[RoopCnt] = (WEB3TPSettlementReflector)l_lisSettlementReflectors.get(RoopCnt);
        }

        //決済順序決定ルールに従ってソートする。
        this.calcSettlementOrder(l_settlementReflectors);
        //今回売付注文後の注文銘柄の決済順位を予測し決済順序を並び替える。注文銘柄の決済順位を取得する。
        int l_intOrder =
            this.calcSettlementOrderForOrder(
                l_settlementReflectors,
                l_lngOrderFundId,
                l_dblOtherReceiptAmount);

        //差金決済売付可能額
        double l_dblSettlementPower = Double.MAX_VALUE;
        //出金可能額
        double l_dblPaymentPower = Double.MAX_VALUE;
        
        //出金可能額のバッファ@領域
        double l_dblBufPaymentPower = 0.0;
        //差金決済情報の要素数
        int l_intLength = l_settlementReflectors.length;

        //項番を0～”要素数-1”で繰り返し処理
        for (int index = 0; index < l_intLength; index++)
        {
            //項番が0の時
            if (index == 0)
            {
                //差金決済買付代金(index)を取得する。
                double l_dblIndexSettlementBuyAmount =
                    l_settlementReflectors[index].calcSettlementBuyAmount();

                //出金可能額(index)（= 指定日前日からの顧客勘定残高　@+ その他入金要素合計 - 差金決済買付代金(index)）を計算する。
                l_dblBufPaymentPower =
                    this.prevDateBalance + l_dblOtherReceiptAmount - l_dblIndexSettlementBuyAmount;
            }
            //以外の時
            else
            {
                //差金決済売付代金(index-1)を取得する。
                double l_dblIndexSettlementSellAmount =
                    l_settlementReflectors[index - 1].calcSettlementSellAmount();
                //差金決済買付代金(index)を取得する。
                double l_dblIndexSettlementBuyAmount =
                    l_settlementReflectors[index].calcSettlementBuyAmount();

                //出金可能額(index)（= 出金可能額(index-1) + 差金決済売付代金(index-1) - 差金決済買付代金(index)）を計算する。
                l_dblBufPaymentPower =
                    l_dblBufPaymentPower
                        + l_dblIndexSettlementSellAmount
                        - l_dblIndexSettlementBuyAmount;
            }

            //出金可能額と出金可能額(index)の小さい方を出金可能額とする
            l_dblPaymentPower = Math.min(l_dblPaymentPower, l_dblBufPaymentPower);

            //項番が「注文銘柄の決済順位」以上の時
            if (l_intOrder <= index)
            {
                //差金決済売付可能額と出金可能額(index)の小さい方を差金決済売付可能額とする。
                l_dblSettlementPower = Math.min(l_dblSettlementPower, l_dblBufPaymentPower);
            }

        }

        //日計り拘束金を取得する。
        double l_dblDayTradeRestraint = this.calcDayTradeRestraint();

        //今回注文以前の出金可能額（指定日の顧客勘定残高 - 日計り拘束金）を計算する。
        double l_dblPrevPaymentPower = this.specifiedDateBalance - l_dblDayTradeRestraint;

        //出金可能額(BigDecimal型)
        BigDecimal l_bdPaymentPower = new BigDecimal(l_dblPaymentPower);
        //今回注文以前の出金可能額(BigDecimal型)
        BigDecimal l_bdPrevPaymentPower = new BigDecimal(l_dblPrevPaymentPower);

        //出金可能額が0以上、または、出金可能額が今回注文以前の出金可能額以上の時
        if (l_bdPaymentPower.compareTo(new BigDecimal(0.0)) >= 0
            || l_bdPaymentPower.compareTo(l_bdPrevPaymentPower) >= 0)
        {
            //注文銘柄情報.差金決済買付代金を取得する
            double l_dblSettlementBuyAmount = l_orderReflector.calcSettlementBuyAmount();
            //注文銘柄の売付可能額（=差金決済売付可能額 + 注文銘柄情報.差金決済買付代金）
            double l_dblSellPossPower = l_dblSettlementPower + l_dblSettlementBuyAmount;

            //注文銘柄の売付可能額(BigDecimal型)
            BigDecimal l_bdSellPossPower = new BigDecimal(l_dblSellPossPower);
            //注文銘柄情報.買付代金(BigDecimal型)
            BigDecimal l_bdBuyAmount = new BigDecimal(l_orderReflector.getBuyAmount());
            //注文銘柄情報.買付数量(BigDecimal型)
            BigDecimal l_bdBuyQuantity = new BigDecimal(l_orderReflector.getBuyQuantity());

            //注文銘柄の単価（=注文銘柄情報.買付代金 / 注文銘柄情報.買付数量）
            BigDecimal l_bdUnitPrice =
                l_bdBuyAmount.divide(l_bdBuyQuantity, 10, BigDecimal.ROUND_HALF_EVEN);

            //差金決済売付可能数量（=注文銘柄の売付可能額 / 注文銘柄の単価）(少数点以下切捨て)
            BigDecimal l_bdSettlementSellPossQuantity =
                l_bdSellPossPower.divide(l_bdUnitPrice, 0, BigDecimal.ROUND_FLOOR);

            //差金決済売付可能数量
            double l_dblSettlementSellPossQuantity = l_bdSettlementSellPossQuantity.doubleValue();

            //差金決済売付可能数量と注文銘柄情報.買付数量の小さい方を差金決済売付可能数量とする  
            l_dblSettlementSellPossQuantity =
                Math.min(l_dblSettlementSellPossQuantity, l_orderReflector.getBuyQuantity());

            //売付可能数量(=差金決済売付可能数量 + 指定日前日保有数量 - 売付数量 - 未約定売付数量)を計算する。
            l_dblSellPossQuantity =
                l_dblSettlementSellPossQuantity
                    + l_orderReflector.getExistQuantity()
                    - l_orderReflector.getSellQuantity()
                    - l_orderReflector.getUnexecutedSellQuantity();

            //売付可能数量(BigDecimal型)
            BigDecimal l_bdSellPossQuantity = new BigDecimal(l_dblSellPossQuantity);
            //パラメータ.売買単位(BigDecimal型)
            BigDecimal l_bdLotSize = new BigDecimal(l_dblLotSize);

            //注文単位株数量（=売付可能数量 / パラメータ.売買単位）(少数点以下切捨て)
            BigDecimal l_bdOrderUnitQuantity =
                l_bdSellPossQuantity.divide(l_bdLotSize, 0, BigDecimal.ROUND_FLOOR);

            //売付可能数量(注文単位株数量 * パラメータ.売買単位)
            l_bdSellPossQuantity = l_bdOrderUnitQuantity.multiply(l_bdLotSize);
            l_dblSellPossQuantity = l_bdSellPossQuantity.doubleValue();

        }
        //以外の時
        else
        {
            //売付可能数量(指定日前日保有数量 - 売付数量 - 未約定売付数量)を計算する
            l_dblSellPossQuantity =
                l_orderReflector.getExistQuantity()
                    - l_orderReflector.getSellQuantity()
                    - l_orderReflector.getUnexecutedSellQuantity();
        }

        /*
         * 預り金不足額を計算する。
         * 
         * ※日計り資産画面よりコールされた（引数.注文数量==Double.MAX_VALUE）場合
         * 　@預り金不足額 =0 
         */
        double l_dblLackAmt;

        //引数.注文数量==Double.MAX_VALUEの場合
        if(l_dblOrderQuantity == Double.MAX_VALUE)
        {
            //預り金不足額 = 0
            l_dblLackAmt = 0.0;
        }
        //以外の場合
        else
        {
	        //売付可能数量 < 引数.注文数量 の場合
	        if(l_dblSellPossQuantity < l_dblOrderQuantity)
	        {
	            /*
	             * 預り金不足額 = 
	             *  TRUNC(
	             * 　@　@(引数.注文数量 + 売付数量 + 未約定売付数量 ? 指定日前日保有数量) 
	             *   　@× (買付代金 / 買付数量)
	             *　@) 
	             *  － 差金決済売付可能額 - 差金決済買付代金
	             */
	            //売越数量 = 引数.注文数量 + 売付数量 + 未約定売付数量 ? 指定日前日保有数量
	            double l_dblSellOver = l_dblOrderQuantity
	                    + l_orderReflector.getSellQuantity()
	                    + l_orderReflector.getUnexecutedSellQuantity()
	                    - l_orderReflector.getExistQuantity();
	
	            //単価 = 買付代金 / 買付数量
	            double l_dblUnitPrice = l_orderReflector.getBuyAmount()
	                    / l_orderReflector.getBuyQuantity();
	
	            //預り金不足額 = TRUNC(売越数量 × 単価) - 差金決済売付可能額 - 差金決済買付代金
	            l_dblLackAmt = Math.floor(l_dblSellOver * l_dblUnitPrice)
	                    - l_dblSettlementPower
	                    - l_orderReflector.calcSettlementBuyAmount();
	        }
	        //以外（売付可能数量 >= 引数.注文数量）の場合
	        else
	        {
	            //預り金不足額 = 0
	            l_dblLackAmt = 0.0;
	        }
        }

        /*
         * 差金決済売付可能数量結果オブジェクトへ値をセット
         */
        WEB3TPSellOrderPossibleQuantityResult l_result = new WEB3TPSellOrderPossibleQuantityResult();
        l_result.dayTradeFundFlg = true;
        l_result.sellPossQuantity = Math.max(l_dblSellPossQuantity, 0.0);
        l_result.lackAmt = l_dblLackAmt;

        //差金決済売付可能数量オブジェクトを返却する
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * （calc差金決済買付可能額）<BR>
     * <BR>
     * 差金決済買付可能額を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * <BR>
     * @@param l_lngOrderFundId - （注文銘柄ID）
     * @@return double
     */
    public double calcBuyOrderPossibleAmount(long l_lngOrderFundId)
    {
        final String STR_METHOD_NAME = "calcBuyOrderPossibleAmount(long)";
        log.entering(STR_METHOD_NAME);

        /*
         * 銘柄ごとの取引情報リストより、引数.注文銘柄IDと同銘柄の銘柄ごと取引情報オブジェクトを取得
         */
        //(注文銘柄)銘柄ごと取引情報
        WEB3TPSettlementReflector l_orderReflector = null;

        //銘柄ごと取引リストの要素数回LOOP処理            
        for (Iterator l_iter = this.lisSettlementReflectors.iterator(); l_iter.hasNext();)
        {
            //銘柄ごと取引情報(index)を取得
            WEB3TPSettlementReflector l_element = (WEB3TPSettlementReflector)l_iter.next();

            //引数.注文銘柄 == 銘柄ごと取引情報(index).銘柄IDの場合
            if (l_lngOrderFundId == l_element.getFundId())
            {
                //(注文銘柄)銘柄ごと取引情報に、銘柄ごと取引情報オブジェクト(index)を代入
                l_orderReflector = l_element;
                //ループより抜ける
                break;
            }
        }

        /*
         * 差金決済情報リストを作成する。
         */
        //差金決済情報リスト
        List l_lisSettlementReflectors = new ArrayList();
        //その他入金要素
        double l_dblOtherReceiptAmount = this.totalReceiptAmount;
        //日計り銘柄フラグ(true:日計り銘柄/false:非日計り銘柄)
        boolean l_blnDayTrade;
        
        //非日計り銘柄の場合
        //(注文銘柄)銘柄ごと取引情報 == null または、
        //(売付数量 + 未約定売付数量) <= 指定日前日保有数量 または、
        //this.指定日 = T+5
        if (l_orderReflector == null
            || l_orderReflector.getSellQuantity() + l_orderReflector.getUnexecutedSellQuantity()
                <= l_orderReflector.getExistQuantity()
            || this.specifiedDate == WEB3TPSpecifiedPointDef.T_5)
        {
            //日計り銘柄フラグ = false
            l_blnDayTrade = false;
        }
        //以外（日計り銘柄）の場合
        else
        {
            //日計り銘柄フラグ = true
            l_blnDayTrade = true;
            //差金決済情報リストに、(注文銘柄)銘柄ごと取引情報を追加
            l_lisSettlementReflectors.add(l_orderReflector);
            //その他入金要素（= その他入金要素 - (注文銘柄)銘柄ごと取引情報.差金決済売付代金）を計算する
            l_dblOtherReceiptAmount = l_dblOtherReceiptAmount
                - l_orderReflector.calcSettlementSellAmount();
        }

        //銘柄ごと取引リストの要素数回LOOP処理            
        for (Iterator l_iter = this.lisSettlementReflectors.iterator(); l_iter.hasNext();)
        {
            //銘柄ごと取引情報(index)を取得
            WEB3TPSettlementReflector l_element = (WEB3TPSettlementReflector)l_iter.next();

            //銘柄ごと取引情報(index)が、差金決済対象銘柄であり、注文銘柄情報でない時
            if (l_element.validateSettlementFundForDayTrade() == true
                && l_element.getFundId() != l_lngOrderFundId)
            {
                //差金決済情報リストに銘柄ごと取引情報(index)を追加する。
                l_lisSettlementReflectors.add(l_element);
                //その他入金要素（= その他入金要素 - 銘柄ごと取引情報(index).差金決済売付代金）を計算する
                l_dblOtherReceiptAmount =
                    l_dblOtherReceiptAmount - l_element.calcSettlementSellAmount();
            }
        }

        /*
         * 作成された差金決済情報リストを配列に変換して、決済順序決定ルールに従ってソート
         */
        //差金決済情報の配列に変換
        WEB3TPSettlementReflector l_settlementReflectors[] =
            new WEB3TPSettlementReflector[l_lisSettlementReflectors.size()];
        l_settlementReflectors =
            (WEB3TPSettlementReflector[])l_lisSettlementReflectors.toArray(l_settlementReflectors);

        //決済順序決定ルールに従ってソート
        this.calcSettlementOrder(l_settlementReflectors);

        /*
         * 注文銘柄が日計り銘柄の場合、注文後の決済順位を予測し並び替え、注文銘柄の決済順位を取得する。
         */
        //注文銘柄の決済順位
        int l_intOrder = Integer.MAX_VALUE;

        //注文銘柄が日計り銘柄の場合
        if(l_blnDayTrade == true)
        {
            l_intOrder =
                this.calcSettlementOrderForOrder(
                    l_settlementReflectors,
                    l_lngOrderFundId,
                    l_dblOtherReceiptAmount);
        }

        /*
         * 差金決済銘柄買付可能額を計算する。
         */
        //差金決済買付可能額
        double l_dblSettlementPower = Double.MAX_VALUE;
        //出金可能額
        double l_dblPaymentPower = Double.MAX_VALUE;
        //出金可能額(n)
        double l_dblBufPaymentPower = 0.0;

        //項番を0～”要素数-1”で繰り返し処理
        for (int index = 0; index < l_settlementReflectors.length; index++)
        {
            //項番が0の時
            if (index == 0)
            {
                //差金決済買付代金(index)を取得する。
                double l_dblIndexSettlementBuyAmount =
                    l_settlementReflectors[index].calcSettlementBuyAmount();

                //出金可能額(index)（= 指定日前日からの顧客勘定残高　@+ その他入金要素合計 - 差金決済買付代金(index)）を計算する。
                l_dblBufPaymentPower =
                    this.prevDateBalance + l_dblOtherReceiptAmount - l_dblIndexSettlementBuyAmount;
            }
            //以外の時
            else
            {
                //差金決済売付代金(index-1)を取得する。
                double l_dblIndexSettlementSellAmount =
                    l_settlementReflectors[index - 1].calcSettlementSellAmount();
                //差金決済買付代金(index)を取得する。
                double l_dblIndexSettlementBuyAmount =
                    l_settlementReflectors[index].calcSettlementBuyAmount();

                //出金可能額(index)（= 出金可能額(index-1) + 差金決済売付代金(index-1) - 差金決済買付代金(index)）を計算する。
                l_dblBufPaymentPower =
                    l_dblBufPaymentPower
                        + l_dblIndexSettlementSellAmount
                        - l_dblIndexSettlementBuyAmount;
            }

            //出金可能額の最小値を計算する。
            l_dblPaymentPower = Math.min(l_dblPaymentPower, l_dblBufPaymentPower);
            
            //項番が「注文銘柄の決済順位」以上の時
            if (l_intOrder <= index)
            {
                //差金決済買付可能額と出金可能額(index)の小さい方を差金決済買付可能額とする。
                l_dblSettlementPower = Math.min(l_dblSettlementPower, l_dblBufPaymentPower);
            }
        }

        /*
         * 差金決済買付可能額を計算する。
         */
        //日計り銘柄の場合
        if(l_blnDayTrade == true)
        {
            /*
             * (差金決済買付可能額
             * 　@+MAX(注文銘柄の差金決済相当外売付代金合計 - 注文銘柄の差金決済相当外買付代金合計, 0)
             * と
             * 指定日の顧客勘定残高
             * との小さい方を差金決済買付可能額とする。
             */
            l_dblSettlementPower =
                l_dblSettlementPower
                    + Math.max(
                        l_orderReflector.calcExceptSettlementSellAmount()
                            - l_orderReflector.calcExceptSettlementBuyAmount(),
                        0);
            l_dblSettlementPower = Math.min(l_dblSettlementPower, this.specifiedDateBalance);

            //差金決済買付可能額を0補正する
            l_dblSettlementPower = Math.max(l_dblSettlementPower, 0.0);
        }
        //非日計り銘柄の場合
        else
        {
            //差金決済買付可能額 = -1
            l_dblSettlementPower = -1;
        }

        /*
         * 出金可能額チェック
         */
        //出金可能額 < 0 の場合
        if(l_dblPaymentPower < 0)
        {
            //差金決済買付可能額 = 0
            l_dblSettlementPower = 0;
        }

        log.exiting(STR_METHOD_NAME);
        //計算した差金決済買付可能額を返却する
        return l_dblSettlementPower;
    }

    /**
     * （get指定日）<BR>
     * 
     * this.指定日を返却する。<BR>
     * @@return int
     * @@roseuid 410601E50223
     */
    public int getSpecifiedDate()
    {
        return this.specifiedDate;
    }

    /**
     * （set指定日）<BR>
     * 
     * パラメータ.指定日をthis.指定日にセットする。<BR>
     * @@param l_datSpecifiedDate - （指定日）
     * @@roseuid 410601EE00EB
     */
    public void setSpecifiedDate(int l_intSpecifiedDate)
    {
        this.specifiedDate = l_intSpecifiedDate;
    }

    /**
     * （get指定日前日からの顧客勘定残高）<BR>
     * 
     * this.指定日前日からの顧客勘定残高を返却する。<BR>
     * @@return double
     * @@roseuid 40F744880352
     */
    public double getPrevDateBalance()
    {
        return this.prevDateBalance;
    }

    /**
    * （set指定日前日からの顧客勘定残高）<BR>
    * 
    * パラメータ.指定日前日からの顧客勘定残高をthis.指定日前日からの顧客勘定残高にセットする。
    * <BR>
    * @@param l_dblPrevDateBalance - （指定日前日からの顧客勘定残高）
    * @@roseuid 40F744940372
    */
    public void setPrevDateBalance(double l_dblPrevDateBalance)
    {
        this.prevDateBalance = l_dblPrevDateBalance;
    }

    /**
     * （get指定日の顧客勘定残高）<BR>
     *
     * this.指定日の顧客勘定残高を返却する。<BR>
     * @@return double
     * @@roseuid 4105FF8D00AC
     */
    public double getSpecifiedDateBalance()
    {
        return this.specifiedDateBalance;
    }

    /**
     * （set指定日の顧客勘定残高）<BR>
     * 
     * パラメータ.指定日の顧客勘定残高をthis.指定日の顧客勘定残高にセットする。<BR>
     * @@param l_dblSpecifiedDateBalance - （指定日の顧客勘定残高）
     * @@roseuid 4105FF990158
     */
    public void setSpecifiedDateBalance(double l_dblSpecifiedDateBalance)
    {
        this.specifiedDateBalance = l_dblSpecifiedDateBalance;
    }

    /**
     * （get入金要素合計）<BR>
     * 
     * this.入金要素合計を返却する。<BR>
     * @@return double
     * @@roseuid 40F744AF020A
     */
    public double getTotalReceiptAmount()
    {
        return this.totalReceiptAmount;
    }

    /**
     * （set入金要素合計）<BR>
     * 
     * パラメータ.入金要素合計をthis.入金要素合計にセットする。<BR>
     * @@param l_dblTotalReceiptAmount - （入金要素合計）
     * @@roseuid 40F744B70268
     */
    public void setTotalReceiptAmount(double l_dblTotalReceiptAmount)
    {
        this.totalReceiptAmount = l_dblTotalReceiptAmount;
    }

    /**
     * （get出金要素合計）<BR>
     * 
     * this.出金要素合計を返却する。<BR>
     * @@return double
     * @@roseuid 40C42B0B00A7
     */
    public double getTotalPaymentAmount()
    {
        return this.totalPaymentAmount;
    }

    /**
     * （set出金要素合計）<BR>
     * 
     * パラメータ.出金要素合計をthis.出金要素合計にセットする。<BR>
     * @@param l_dblTotalPaymentAmount - （出金要素合計）
     * @@roseuid 40C42B7003E3
     */
    public void setTotalPaymentAmount(double l_dblTotalPaymentAmount)
    {
        this.totalPaymentAmount = l_dblTotalPaymentAmount;
    }

    /**
     * （get銘柄ごと取引情報）<BR>
     * 
     * this.銘柄ごと取引情報を返却する。<BR>
     * @@return List
     * @@roseuid 40C42B160078
     */
    public List getLisSettlementReflectors()
    {
        return this.lisSettlementReflectors;
    }

    /**
     * （set銘柄ごと取引情報）<BR>
     * 
     * パラメータ.銘柄ごと取引情報をthis.銘柄ごと取引情報にセットする。<BR>
     * @@param l_lisSettlementReflectors - （銘柄ごとの取引情報）
     * @@roseuid 40C42B810191
     */
    public void setLisSettlementReflectors(List l_lisSettlementReflectors)
    {
        this.lisSettlementReflectors = l_lisSettlementReflectors;
    }

    /**
     * （calc決済順序）<BR>
     * 
     * 決済順序を決定する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@param l_settlementReflectors - （差金決済対象となる銘柄ごと取引情報の配列）
     * @@roseuid 40BC6DCC028F
     */
    protected void calcSettlementOrder(WEB3TPSettlementReflector[] l_settlementReflectors)
    {
        //差金決済情報の配列の要素数を取得する
        int l_intLength = l_settlementReflectors.length;
        //決済順位入れ替えフラグ
        boolean l_changeFlag = false;

        //項番を0～”要素数-1”で繰り返し処理
        for (int index1 = 0; index1 < l_intLength - 1; index1++)
        {
            for (int index2 = 0; index2 < l_intLength - 1; index2++)
            {

                //差金決済売買差額(index2)
                BigDecimal l_bdCurSettlementDiff =
                    new BigDecimal(l_settlementReflectors[index2].calcSettlementDiff());
                //差金決済買付代金(index2)
                BigDecimal l_bdCurSettlementBuyAmount =
                    new BigDecimal(l_settlementReflectors[index2].calcSettlementBuyAmount());
                //差金決済売付代金(index2)
                BigDecimal l_bdCurSettlementSellAmount =
                    new BigDecimal(l_settlementReflectors[index2].calcSettlementSellAmount());

                //差金決済売買差額(index2+1)
                BigDecimal l_bdNextSettlementDiff =
                    new BigDecimal(l_settlementReflectors[index2 + 1].calcSettlementDiff());
                //差金決済買付代金(index2+1)
                BigDecimal l_bdNextSettlementBuyAmount =
                    new BigDecimal(l_settlementReflectors[index2 + 1].calcSettlementBuyAmount());
                //差金決済売付代金(index2+1)
                BigDecimal l_bdNextSettlementSellAmount =
                    new BigDecimal(l_settlementReflectors[index2 + 1].calcSettlementSellAmount());

                //差金決済売買差額(index2)がプラスの時
                if (l_bdCurSettlementDiff.compareTo(new BigDecimal(0.0)) >= 0)
                {
                    //差金決済売買差額(index2+1)がプラスの時
                    if (l_bdNextSettlementDiff.compareTo(new BigDecimal(0.0)) >= 0)
                    {
                        //差金決済買付代金(index2)が差金決済買付代金(index2+1)より大きい時
                        if (l_bdCurSettlementBuyAmount.compareTo(l_bdNextSettlementBuyAmount) > 0)
                        {
                            //決済順位入れ替えフラグにTRUEをセットする。
                            l_changeFlag = true;
                        }
                        //差金決済買付代金(index2)が差金決済買付代金(index2+1)と等しい時
                        else if (
                            l_bdCurSettlementBuyAmount.compareTo(l_bdNextSettlementBuyAmount) == 0)
                        {
                            //差金決済売付代金(index2)が差金決済売付代金(index2+1)より小さい時
                            if (l_bdCurSettlementSellAmount.compareTo(l_bdNextSettlementSellAmount)
                                < 0)
                            {
                                //決済順位入れ替えフラグにTRUEをセットする。
                                l_changeFlag = true;
                            }
                            //差金決済売付代金(index2)が差金決済売付代金(index2+1)以上の時
                            else
                            {
                                //決済順位入れ替えフラグにFALSEをセットする。
                                l_changeFlag = false;
                            }
                        }
                        //差金決済買付代金(index2)が差金決済買付代金(index2+1)より小さい時
                        else
                        {
                            //決済順位入れ替えフラグにFALSEをセットする。
                            l_changeFlag = false;
                        }
                    }
                    //差金決済売買差額(index2+1)がマイナスの時
                    else
                    {
                        //決済順位入れ替えフラグにFALSEをセットする。
                        l_changeFlag = false;
                    }
                }
                //差金決済売買差額(index2)がマイナスの時
                else
                {
                    //差金決済売買差額(index2+1)がプラスの時
                    if (l_bdNextSettlementDiff.compareTo(new BigDecimal(0.0)) >= 0)
                    {
                        //決済順位入れ替えフラグにTRUEをセットする。
                        l_changeFlag = true;
                    }
                    //差金決済売買差額(index2+1)がマイナスの時
                    else
                    {
                        //差金決済売付代金(index2)が差金決済売付代金(index2+1)より小さい時
                        if (l_bdCurSettlementSellAmount.compareTo(l_bdNextSettlementSellAmount)
                            < 0)
                        {
                            //決済順位入れ替えフラグにTRUEをセットする。
                            l_changeFlag = true;
                        }
                        //差金決済売付代金(index2)が差金決済売付代金(index2+1)と等しい時
                        else if (
                            l_bdCurSettlementSellAmount.compareTo(l_bdNextSettlementSellAmount)
                                == 0)
                        {
                            //差金決済買付代金(index2)が差金決済買付代金(index2+1)より大きい時
                            if (l_bdCurSettlementBuyAmount.compareTo(l_bdNextSettlementBuyAmount)
                                > 0)
                            {
                                //決済順位入れ替えフラグにTRUEをセットする。
                                l_changeFlag = true;
                            }
                            //差金決済買付代金(index2)が差金決済買付代金(index2+1)以下の時
                            else
                            {
                                //決済順位入れ替えフラグにFALSEをセットする。
                                l_changeFlag = false;
                            }
                        }
                        //差金決済売付代金(index2)が差金決済売付代金(index2+1)より大きい時
                        else
                        {
                            //決済順位入れ替えフラグにFALSEをセットする。
                            l_changeFlag = false;
                        }
                    }
                }

                //入れ替えフラグがTRUEの時
                if (l_changeFlag == true)
                {
                    //差金決済情報の配列の順位を前後入れ替える。
                    WEB3TPSettlementReflector l_bufSettlementReflector =
                        l_settlementReflectors[index2];
                    l_settlementReflectors[index2] = l_settlementReflectors[index2 + 1];
                    l_settlementReflectors[index2 + 1] = l_bufSettlementReflector;
                }
            }
        }
    }

    /**
     * （calc決済順序<差金決済>）<BR>
     * <BR>
     * 注文後の注文銘柄の決済順位を予測し、決済順序を並び替える<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@param l_settlementReflectors - （差金決済対象となる銘柄ごと取引情報）
     * @@param l_lngOrderFundId - （注文銘柄ID）
     * @@param l_dblOtherReceiptAmount - （その他入金要素）
     * @@return int
     * @@roseuid 4104A81A028E
     */
    protected int calcSettlementOrderForOrder(
        WEB3TPSettlementReflector[] l_settlementReflectors,
        long l_lngOrderFundId,
        double l_dblOtherReceiptAmount)
    {
        //買付時点残高に初期値（= this.指定日前日からの顧客勘定残高 + パラメータ.その他入金要素）を代入する。
        double l_dblCurCashBalanceAfterBuy = this.prevDateBalance + l_dblOtherReceiptAmount;
        //注文銘柄情報の決済順位
        int l_intOrder = 0;
        //差金決済情報の配列の要素数を取得する
        int l_intLength = l_settlementReflectors.length;

        //項番を0～”要素数-1”で繰り返し処理
        for (int index = 0; index < l_intLength; index++)
        {
            //差金決済買付代金（= 差金決済情報(index).差金決済買付代金）を取得する。
            double l_dblSettlementBuyAmount =
                l_settlementReflectors[index].calcSettlementBuyAmount();
            //買付時点残高（=買付時点残高 - 差金決済買付代金）を計算する。
            l_dblCurCashBalanceAfterBuy = l_dblCurCashBalanceAfterBuy - l_dblSettlementBuyAmount;

            //差金決済情報(index).買付時点残高に買付時点残高を代入する。
            l_settlementReflectors[index].setCashBalanceAfterBuy(l_dblCurCashBalanceAfterBuy);

            //差金決済売付代金（= 差金決済情報(index).差金決済売付代金）を取得する。
            double l_dblSettlementSellAmount =
                l_settlementReflectors[index].calcSettlementSellAmount();
            //買付時点残高（=買付時点残高 + 差金決済売付代金）を計算する。
            l_dblCurCashBalanceAfterBuy = l_dblCurCashBalanceAfterBuy + l_dblSettlementSellAmount;

            //銘柄ID（= 差金決済情報(index).銘柄ID）を取得する。
            long l_lngFundId = l_settlementReflectors[index].getFundId();

            //パラメータ.注文銘柄IDと銘柄IDが等しい時
            if (l_lngOrderFundId == l_lngFundId)
            {
                //項番を注文銘柄の決済順位に代入する
                l_intOrder = index;
            }
        }

        //注文銘柄情報.差金決済売付代金
        BigDecimal l_bdOrderSettlementSellAmount =
            new BigDecimal(l_settlementReflectors[l_intOrder].calcSettlementSellAmount());
        //売付後決済順位①@
        int l_intAfterOrder1 = 0;

        //項番を0～”要素数-1”で繰り返し処理
        for (int index = 0; index < l_intLength; index++)
        {
            //差金決済売買差額(index)
            BigDecimal l_bdCurSettlementDiff =
                new BigDecimal(l_settlementReflectors[index].calcSettlementDiff());
            //差金決済売付代金(index)
            BigDecimal l_bdCurSettlementSellAmount =
                new BigDecimal(l_settlementReflectors[index].calcSettlementSellAmount());

            //売付後決済順位①@をカーソル位置にする。
            l_intAfterOrder1 = index;

            //差金決済売買差額(index)がマイナスかつ
            //差金決済売付代金(index)が注文銘柄情報.差金決済売付代金より小さい時
            if (l_bdCurSettlementDiff.compareTo(new BigDecimal(0.0)) < 0
                && l_bdCurSettlementSellAmount.compareTo(l_bdOrderSettlementSellAmount) < 0)
            {
                //売付後決済順位①@をカーソル位置の１つ手前にする。
                l_intAfterOrder1 = index - 1;
                //ループから抜ける。
                index = l_intLength;
            }

        }

        //売付後決済順位②
        int l_intAfterOrder2 = l_intOrder;
        //注文銘柄情報.差金決済売買差額
        BigDecimal l_bdOrderSettlementDiff =
            new BigDecimal(l_settlementReflectors[l_intOrder].calcSettlementDiff());

        //注文銘柄情報.差金決済売買差額が0より大きい時
        if (l_bdOrderSettlementDiff.compareTo(new BigDecimal(0.0)) > 0)
        {
            //項番を”注文銘柄情報の決済順位+1”～”要素数-1”で繰り返し処理
            for (int index = l_intOrder + 1; index < l_intLength; index++)
            {
                //売付後決済順位②をカーソル位置にする。
                l_intAfterOrder2 = index;

                //買付時点残高(index)
                BigDecimal l_bdCashBalanceAfterBuy =
                    new BigDecimal(l_settlementReflectors[index].getCashBalanceAfterBuy());

                //買付時点残高(index) - 注文銘柄情報.差金決済売買差額を計算する。
                BigDecimal l_bdProfit = l_bdCashBalanceAfterBuy.subtract(l_bdOrderSettlementDiff);

                //注文銘柄の売買益がなくなることによって、立替金が発生する場合
                if (l_bdProfit.compareTo(new BigDecimal(0.0)) < 0)
                {
                    //売付後決済順位②をカーソル位置の１つ手前にする。
                    l_intAfterOrder2 = index - 1;

                    //ループから抜ける
                    index = l_intLength;
                }
            }
        }

        //売付後決済順位①@と②とで小さい方を売付後決済順位とする。
        int l_intAfterOrder = Math.min(l_intAfterOrder1, l_intAfterOrder2);

        //売付後決済順位と注文銘柄の決済順位で大きい方を売付後決済順位とする
        l_intAfterOrder = Math.max(l_intAfterOrder, l_intOrder);

        //項番を”注文銘柄情報の決済順位”～”売付後決済順位-1”で繰り返し処理
        for (int index = l_intOrder; index < l_intAfterOrder; index++)
        {
            //決済順位を入れ替える。
            WEB3TPSettlementReflector l_bufSettlementReflector = l_settlementReflectors[index];
            l_settlementReflectors[index] = l_settlementReflectors[index + 1];
            l_settlementReflectors[index + 1] = l_bufSettlementReflector;
        }

        //売付後決済順位を返却する。
        return l_intAfterOrder;
    }
}
@
