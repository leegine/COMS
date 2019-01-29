head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSettlementReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄ごと取引情報(WEB3TPSettlementReflector.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower.updtpower.settlement;

import java.math.BigDecimal;

/**
 * （銘柄ごと取引情報）<BR>
 * @@author  nakazato(ACT)
 * @@version 1.0
 */
public class WEB3TPSettlementReflector
{
 
    /**
     * （銘柄ID）<BR>
     */
    private long fundId;

    /**
     * （指定日前日保有数量）<BR>
     */
    private double existQuantity;

    /**
     * （買付数量）<BR>
     */
    private double buyQuantity;

    /**
     * （買付代金）<BR>
     */
    private double buyAmount;

    /**
     * （売付数量）<BR>
     */
    private double sellQuantity;

    /**
     * （売付代金）<BR>
     */
    private double sellAmount;

    /**
     * （未約定売付数量）<BR>
     */
    private double unexecutedSellQuantity;

    /**
     * （買付時点残高）<BR>
     */
    private double cashBalanceAfterBuy;

    /**
     * （銘柄ごと取引情報）<BR>
     * 
     * コンストラクタ<BR>
     * @@roseuid 40C56A9400AB
     */
    public WEB3TPSettlementReflector()
    {
        //フィールドの初期化
        this.fundId = 0;
        this.existQuantity = 0.0;
        this.buyQuantity = 0.0;
        this.sellQuantity = 0.0;
        this.buyAmount = 0.0;
        this.sellAmount = 0.0;
        this.unexecutedSellQuantity = 0.0;
        this.cashBalanceAfterBuy = 0.0;
    }

    /**
     * （get銘柄ID）<BR>
     * 
     * this.銘柄IDを返却する。<BR>
     * @@return long
     * @@roseuid 40BC66A70156
     */
    public long getFundId()
    {
        return this.fundId;
    }

    /**
     * （set銘柄ID<BR>
     * 
     * パラメータ.銘柄IDをthis.銘柄IDにセットする。<BR>
     * @@param l_lngFundId - （銘柄ID）
     * @@roseuid 40C51A5003AA
     */
    public void setFundId(long l_lngFundId)
    {
        this.fundId = l_lngFundId;
    }

    /**
     * （get指定日前日保有数量）<BR>
     * 
     * this.指定日前日保有数量を返却する。<BR>
     * @@return double
     * @@roseuid 40C519C10262
     */
    public double getExistQuantity()
    {
        return this.existQuantity;
    }

    /**
     * （set指定日前日保有数量）<BR>
     * 
     * パラメータ.指定日前日保有数量をthis.指定日前日保有数量にセットする。<BR>
     * @@param l_dblExistQuantity - （指定日前日保有数量）
     * @@roseuid 40C519C9034C
     */
    public void setExistQuantity(double l_dblExistQuantity)
    {
        this.existQuantity = l_dblExistQuantity;
    }

    /**
     * （get買付数量）<BR>
     * 
     * this.買付数量を返却する。<BR>
     * @@return double
     * @@roseuid 40C519D00233
     */
    public double getBuyQuantity()
    {
        return this.buyQuantity;
    }

    /**
     * （set買付数量）<BR>
     * 
     * パラメータ.買付数量をthis.買付数量にセットする。<BR>
     * @@param l_dblBuyQuantity - （買付数量）
     * @@roseuid 40C519DB03B9
     */
    public void setBuyQuantity(double l_dblBuyQuantity)
    {
        this.buyQuantity = l_dblBuyQuantity;
    }

    /**
     * （get買付代金）<BR>
     * 
     * this.買付代金を返却する。<BR>
     * @@return double
     * @@roseuid 40C519E1033C
     */
    public double getBuyAmount()
    {
        return this.buyAmount;
    }

    /**
     * （set買付代金）<BR>
     * 
     * パラメータ.買付代金をthis.買付代金にセットする。<BR>
     * @@param l_dblBuyAmount - （買付代金）
     * @@roseuid 40C519E80271
     */
    public void setBuyAmount(double l_dblBuyAmount)
    {
        this.buyAmount = l_dblBuyAmount;
    }

    /**
     * （get売付数量）<BR>
     * 
     * this.売付数量を返却する。<BR>
     * @@return double
     * @@roseuid 40C51A8D02DF
     */
    public double getSellQuantity()
    {
        return this.sellQuantity;
    }

    /**
     * （set売付数量）<BR>
     * 
     * パラメータ.売付数量をthis.売付数量にセットする。<BR>
     * @@param l_dblSellQuantity - （売付数量）
     * @@roseuid 40C51A940158
     */
    public void setSellQuantity(double l_dblSellQuantity)
    {
        this.sellQuantity = l_dblSellQuantity;
    }

    /**
     * （get売付代金）<BR>
     * 
     * this.売付代金を返却する。<BR>
     * @@return double
     * @@roseuid 40C519F80204
     */
    public double getSellAmount()
    {
        return this.sellAmount;
    }

    /**
     * （set売付代金）<BR>
     * 
     * パラメータ.売付代金をthis.売付代金にセットする。<BR>
     * @@param l_dblSellAmount - （売付代金）
     * @@roseuid 40C51A00039A
     */
    public void setSellAmount(double l_dblSellAmount)
    {
        this.sellAmount = l_dblSellAmount;
    }

    /**
     * （get未約定売付数量）<BR>
     * 
     * this.未約定売付代金を返却する。<BR>
     * @@return double
     * @@roseuid 40F64BAA025E
     */
    public double getUnexecutedSellQuantity()
    {
        return this.unexecutedSellQuantity;
    }

    /**
     * （set未約定売付数量）<BR>
     * 
     * パラメータ.未約定売付代金をthis.未約定売付代金にセットする。<BR>
     * @@param l_dblUnexecutedSellQuantity - （未約定売付代金）
     * @@roseuid 40F64BB501C2
     */
    public void setUnexecutedSellQuantity(double l_dblUnexecutedSellQuantity)
    {
        this.unexecutedSellQuantity = l_dblUnexecutedSellQuantity;
    }

    /**
     * （get買付時点残高）<BR>
     * 
     * this.買付時点残高を返却する。<BR>
     * @@return double
     * @@roseuid 4100B65B0128
     */
    public double getCashBalanceAfterBuy()
    {
        return this.cashBalanceAfterBuy;
    }

    /**
     * （set買付時点残高）<BR>
     * 
     * パラメータ.買付時点残高をthis.買付時点残高にセットする。<BR>
     * @@param l_dblCashBalanceAfterBuy - （買付時点残高）
     * @@roseuid 4100BA540251
     */
    public void setCashBalanceAfterBuy(double l_dblCashBalanceAfterBuy)
    {
        this.cashBalanceAfterBuy = l_dblCashBalanceAfterBuy;
    }

    /**
     * （calc差金決済売買差額）<BR>
     * 
     * 差金決済売買差額を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@return double
     * @@roseuid 40BC66B201F3
     */
    public double calcSettlementDiff()
    {
        //差金決済売買差額（= 差金決済売付代金 - 差金決済買付代金）を計算する
        double l_dblSettlementDiff =
            this.calcSettlementSellAmount() - this.calcSettlementBuyAmount();

        //差金決済売買差額を返却する。
        return l_dblSettlementDiff;
    }

    /**
     * （calc差金決済買付代金）<BR>
     * 
     * 差金決済買付代金を返却する<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@return double
     * @@roseuid 40AADD9C01E9
     */
    public double calcSettlementBuyAmount()
    {
        //差金決済相当外買付代金のうち同銘柄の売付代金より充当不可能な代金
        //（=MAX(（差金決済相当外買付代金 - 差金決済相当外売付代金）, 0)）を計算する。
        double l_dblAmount =
            Math.max(
                (this.calcExceptSettlementBuyAmount() - this.calcExceptSettlementSellAmount()),
                0.0);

        //差金決済買付代金（=差金決済相当買付代金 + ”充当不可能な買付代金”）
        double l_dblSettlementBuyAmount =
            this.calcSuitableSettlementBuyAmount() + l_dblAmount;

        //double型に変換して差金決済買付代金を返却する。
        return l_dblSettlementBuyAmount;
    }

    /**
     * （calc差金決済売付代金）<BR>
     * 
     * 差金決済売付代金を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@return double
     * @@roseuid 40AADDB101F9
     */
    public double calcSettlementSellAmount()
    {
        //差金決済売付代金（= 売付代金）を返却
        return this.sellAmount;
    }

    /**
     * （calc差金決済相当買付代金）<BR>
     * 
     * 差金決済相当外買付代金を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@return double
     */
    public double calcSuitableSettlementBuyAmount()
    {

        //買付数量(BigDecimal型)
        BigDecimal l_bdBuyQuantity = new BigDecimal(this.buyQuantity);
        //買付代金(BigDecimal型)
        BigDecimal l_bdBuyAmount = new BigDecimal(this.buyAmount);
        //売付数量(BigDecimal型)
        BigDecimal l_bdSellQuantity = new BigDecimal(this.sellQuantity);
        //未約定売付数量(BigDecimal型)
        BigDecimal l_bdUnexecutedSellQuantity = new BigDecimal(this.unexecutedSellQuantity);
        //指定日前日保有数量(BigDecimal型)
        BigDecimal l_bdExistQuantity = new BigDecimal(this.existQuantity);

        //未約定含む売付数量（= 売付数量 + 未約定売付数量）を計算する。
        BigDecimal l_bdSellQuantityIncUnExecuted = l_bdSellQuantity.add(l_bdUnexecutedSellQuantity);

    
        //買付数量=0の時
        if (l_bdBuyQuantity.compareTo(new BigDecimal(0.0)) == 0)
        {
            //差金決済相当外買付代金を返却する。
            return 0.0;
        }
    
        /*
         * 差金決済相当買付代金 = (未約定含む売付数量 - 指定日前日保有数量) * 買付代金 / 買付数量
         */
        BigDecimal l_bdSuitableBuyAmount =
            l_bdSellQuantityIncUnExecuted.subtract(l_bdExistQuantity).multiply(
                l_bdBuyAmount).divide(
                l_bdBuyQuantity,
                10,
                BigDecimal.ROUND_HALF_EVEN);

        //小数点以下切上げを行う。
        l_bdSuitableBuyAmount = l_bdSuitableBuyAmount.setScale(0, BigDecimal.ROUND_UP);

        //double型に変換して差金決済相当外買付代金を返却する。
        return l_bdSuitableBuyAmount.doubleValue();
    }

    /**
     * （calc差金決済相当売付代金）<BR>
     * 
     * 差金決済相当売付代金を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@return double
     */
    public double calcSuitableSettlementSellAmount()
    {
        //売付数量(BigDecimal型)
        BigDecimal l_bdSellQuantity = new BigDecimal(this.sellQuantity);
        //売付代金(BigDecimal型)
        BigDecimal l_bdSellAmount = new BigDecimal(this.sellAmount);
        //未約定売付数量(BigDecimal型)
        BigDecimal l_bdUnexecutedSellQuantity = new BigDecimal(this.unexecutedSellQuantity);
        //指定日前日保有数量(BigDecimal型)
        BigDecimal l_bdExistQuantity = new BigDecimal(this.existQuantity);

        //未約定含む売付数量（= 売付数量 + 未約定売付数量）を計算する。
        BigDecimal l_bdSellQuantityIncUnExecuted = l_bdSellQuantity.add(l_bdUnexecutedSellQuantity);

        //売付数量=0の時
        if (l_bdSellQuantity.compareTo(new BigDecimal(0.0)) == 0)
        {
            //差金決済相当外売付代金を返却する。
            return 0.0;
        }

        /*
         * 差金決済相当売付代金 = (未約定含む売付数量 - 指定日前日保有数量) * 売付代金 / 未約定含む売付数量
         */
        BigDecimal l_bdSuitableSellAmount =
            l_bdSellQuantityIncUnExecuted.subtract(l_bdExistQuantity).multiply(
                l_bdSellAmount).divide(
                l_bdSellQuantityIncUnExecuted,
                10,
                BigDecimal.ROUND_HALF_EVEN);

        //小数点以下切捨てを行う。
        l_bdSuitableSellAmount = l_bdSuitableSellAmount.setScale(0,BigDecimal.ROUND_DOWN);

        //double型に変換して差金決済相当売付代金を返却する。
        return l_bdSuitableSellAmount.doubleValue();

    }

    /**
     * （calc差金決済相当外買付代金）<BR>
     * 
     * 差金決済相当外買付代金を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@return double
     * @@roseuid 40AADDD802C4
     */
    public double calcExceptSettlementBuyAmount()
    {
        /*
         * 差金決済相当外買付代金 = 買付代金 - 差金決済相当買付代金
         */
        return this.getBuyAmount() - this.calcSuitableSettlementBuyAmount();
    }

    /**
     * （calc差金決済相当外売付代金）<BR>
     * 
     * 差金決済相当外売付代金を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@return double
     * @@roseuid 40AADDE801AA
     */
    public double calcExceptSettlementSellAmount()
    {
        /*
         * 差金決済相当外売付代金 = 売付代金 - 差金決済相当売付代金
         */
        return this.getSellAmount() - this.calcSuitableSettlementSellAmount();
    }

    /**
     * （validate差金決済対象銘柄）<BR>
     * 
     * 当該オブジェクトが差金決済対象銘柄かどうか判別する。<BR>
     * <BR>
     * [返却値]<BR>
     * 　@true：　@差金決済対象<BR>
     * 　@false：　@差金決済対象外<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力_差金決済）.doc」参照。<BR>
     * @@return boolean
     * @@roseuid 40F7398D0203
     */
    public boolean validateSettlementFundForDayTrade()
    {
        //買付数量(BigDecimal型)
        BigDecimal l_bdBuyQuantity = new BigDecimal(this.buyQuantity);
        //売付数量(BigDecimal型)
        BigDecimal l_bdSellQuantity = new BigDecimal(this.sellQuantity);
        //未約定売付数量(BigDecimal型)
        BigDecimal l_bdUnexecutedSellQuantity = new BigDecimal(this.unexecutedSellQuantity);
        //指定日前日保有数量(BigDecimal型)
        BigDecimal l_bdExistQuantity = new BigDecimal(this.existQuantity);

        //未約定含む売付数量（= 売付数量 + 未約定売付数量）を計算する。
        BigDecimal l_bdSellQuantityIncUnExecuted = l_bdSellQuantity.add(l_bdUnexecutedSellQuantity);

        //未約定含む売付数量が指定日前日保有数量より大きい　@かつ　@買付数量が0より大きい時
        if (l_bdSellQuantityIncUnExecuted.compareTo(l_bdExistQuantity) > 0
            && l_bdBuyQuantity.compareTo(new BigDecimal(0.0)) > 0)
        {
            //差金決済対象、TRUEを返却する。
            return true;
        }
        //以外の時
        else
        {
            //差金決済対象外、FALSEを返却する。
            return false;
        }
    }
}
@
