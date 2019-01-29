head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoSummaryContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP建玉集計(WEB3IfoSummaryContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 nakazato(ACT) 新規作成
Revesion History : 2007/07/06 hijikata(SRA) 夕場対応 モデルNo.061⑥, No.069, No.074②
Revesion History : 2007/08/02 hijikata(SRA) 夕場対応 モデルNo.104
*/

package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

/**
 * (先物OP建玉集計)<BR>
 * 建玉集計を表すクラス。<BR>
 */
public class WEB3IfoSummaryContract
{

    /**
     * (銘柄ID)
     */
    private long productId;

    /**
     * (銘柄コード)
     */
    private String productCode;

    /**
     * (先物オプション商品区分) <BR>
     * 1：先物<BR>
     * 2：コールオプション<BR>
     * 3：プットオプション<BR>
     */
    private IfoDerivativeTypeEnum ifoDerivativeType;

    /**
     * (時価)
     */
    private double currentPrice;

    /**
     * (指数乗数)<BR>
     * 
     * TOPIX：10000<BR>
     * 日経225：1000<BR>
     * 日経300：10000<BR>
     */
    private double unitSize;

    /**
     * (買建数量)
     */
    private double buyQuantity = 0;

    /**
     * (売建数量)
     */
    private double sellQuantity = 0;
    
    /**
     * (当日買建数量)
     */
    private double todayBuyQuantity = 0;

    /**
     * (当日売建数量)
     */
    private double todaySellQuantity = 0;
    
    /**
     * (評価損益計算区分)<BR>
     * 0：当日建を含む<BR>
     * 1：当日建を含まない<BR>
     */
    private String profitLossCalcType;
    
    /**
     * (当日清算値)
     */
    private double currentBizDateLiquidationPrice;
       
    /**
     * (買建数量＜証拠金不足仮確定＞)
     */
    private double buyQuantityTemp = 0;

    /**
     * (売建数量＜証拠金不足仮確定＞)
     */
    private double sellQuantityTemp = 0;
    

    /**
     * (add建数量)<BR>
     * 
     * 買建/売建であるかにもとづき、該当する建数量の加算処理を行う。<BR>
     * 
     * ○買建(引数.is買建 == true)の場合<BR>
     * 　@　@this.買建数量 = this.get買建数量( ) + 引数.数量<BR>
     * 
     * ○売建(引数.is買建 == false)の場合<BR>
     * 　@　@this.売建数量 = this.get売建数量( ) + 引数.数量<BR>
     * 
     * @@param l_blnIsBuy - (is買建)<BR>
     * 建玉が買建である場合、true。以外、false。<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * @@roseuid 412B45740230
     */
    public void addQuantity(boolean l_blnIsBuy, double l_dblQuantity)
    {
    	
        //買建の場合
        if (l_blnIsBuy == true)
        {
            //this.買建数量に引数.建数量を加算する。
            this.buyQuantity = this.buyQuantity + l_dblQuantity;

        }
        //売建の場合     
        else
        {
            //this.売建数量に引数.建数量を加算する
            this.sellQuantity = this.sellQuantity + l_dblQuantity;
        }
    }
    
    /**
     * (add当日建数量)<BR>
     * 
     * 買建/売建であるかにもとづき、該当する当日建数量の加算処理を行う。<BR>
     * 
     * ○買建(引数.is買建 == true)の場合<BR>
     * 　@　@this.当日買建数量 = this.get当日買建数量( ) + 引数.数量<BR>
     * 
     * ○売建(引数.is買建 == false)の場合<BR>
     * 　@　@this.当日売建数量 = this.get当日売建数量( ) + 引数.数量<BR>
     * @@param l_blnIsBuy - (is買建)<BR>
     * 
     * 建玉が買建である場合、true。以外、false。<BR>
     * @@param l_dblQuantity - (数量)
     */
    public void addTodayQuantity(boolean l_blnIsBuy, double l_dblQuantity)
    {
        //買建の場合
        if (l_blnIsBuy == true)
        {
            //this.買建数量に引数.建数量を加算する。
            this.todayBuyQuantity = this.todayBuyQuantity + l_dblQuantity;
        }
        //売建の場合     
        else
        {
            //this.売建数量に引数.建数量を加算する
            this.todaySellQuantity = this.todaySellQuantity + l_dblQuantity;
        }
    }
     
    /**
     * (add建数量＜証拠金不足仮確定＞)<BR>
     * 
     * 買建/売建であるかにもとづき、該当する建数量＜証拠金不足仮確定＞の加算処理を行う。<BR>
     * 
     * ○買建(引数.is買建 == true)の場合<BR>
     * 　@　@this.買建数量＜証拠金不足仮確定＞ = this.get買建数量＜証拠金不足仮確定＞( ) + 引数.数量<BR>
     * 
     * ○売建(引数.is買建 == false)の場合<BR>
     * 　@　@this.売建数量＜証拠金不足仮確定＞ = this.get売建数量＜証拠金不足仮確定＞( ) + 引数.数量<BR>
     * @@param l_blnIsBuy - (is買建)<BR>
     * 
     * 建玉が買建である場合、true。以外、false。<BR>
     * @@param l_dblQuantity - (数量)
     */
    public void addQuantityTemp(boolean l_blnIsBuy, double l_dblQuantity)
    {
        //買建の場合
        if (l_blnIsBuy == true)
        {
            //this.買建数量＜証拠金不足仮確定＞に引数.建数量を加算する。
            this.buyQuantityTemp = this.getBuyQuantityTemp() + l_dblQuantity;
        }
        //売建の場合     
        else
        {
            //this.売建数量＜証拠金不足仮確定＞に引数.建数量を加算する
            this.sellQuantityTemp = this.getSellQuantityTemp() + l_dblQuantity;
        }
    }

   /**
     * (set銘柄ID)<BR>
     * 
     * 引数.銘柄IDをthis.銘柄IDにセットする。<BR>
     * @@param lo_lngProductId - (銘柄ID)<BR>
     * @@roseuid 4124859802AA
     */
    public void setProductId(long l_lngProductId)
    {
        this.productId = l_lngProductId;
    }

    /**
     * (set銘柄コード)<BR>
     * 
     * 引数.銘柄コードをthis.銘柄コードにセットする。<BR>
     * @@param l_strProductCode - 銘柄コード
     * @@roseuid 412485550095
     */
    public void setProductCode(String l_strProductCode)
    {
        this.productCode = l_strProductCode;
    }

    /**
     * (set先物オプション商品区分)<BR>
     *
     * 引数.先物オプション商品区分をthis.先物オプション商品区分にセットする。<BR>
     * @@param l_ifoDerivativeType - (先物オプション商品区分)<BR>
     * 
     * 
     * 1：先物<BR>
     * 2：コールオプション<BR>
     * 3：プットオプション<BR>
     * @@roseuid 412484CD01FB
     */
    public void setIfoDerivativeType(IfoDerivativeTypeEnum l_ifoDerivativeType)
    {
        this.ifoDerivativeType = l_ifoDerivativeType;
    }

    /**
     * (set時価)<BR>
     * 
     * 引数.時価をthis.時価にセットする。<BR>
     * @@param l_dblCurrentPrice - (時価)
     * @@roseuid 412485D301DF
     */
    public void setCurrentPrice(double l_dblCurrentPrice)
    {
        this.currentPrice = l_dblCurrentPrice;
    }

    /**
     * (set指数乗数)<BR>
     * 
     * 引数.指数乗数をthis.指数乗数にセットする。<BR>
     * @@param l_dblUnitSize - (指数乗数)
     * @@roseuid 41248603021E
     */
    public void setUnitSize(double l_dblUnitSize)
    {
        this.unitSize = l_dblUnitSize;
    }

    /**
     * (set買建数量)<BR>
     * 
     * 引数.買建数量をthis.買建数量にセットする。<BR>
     * @@param l_dblBuyQuantity - (買建数量)<BR>
     */
    public void setBuyQuantity(double l_dblBuyQuantity)
    {
        this.buyQuantity = l_dblBuyQuantity;
    }

    /**
     * (set売建数量)<BR>
     * 
     * 引数.売建数量をthis.売建数量にセットする。<BR>
     * @@param l_dblSellQuantity - (売建数量)<BR>
     */
    public void setSellQuantity(double l_dblSellQuantity)
    {
        this.sellQuantity = l_dblSellQuantity;
    }
    
    /**
     * (set評価損益計算区分)<BR>
     *
     * 引数.評価損益計算区分をthis.評価損益計算区分にセットする。<BR>
     * @@param l_strProfitLossCalcType - (評価損益計算区分)<BR>
     * 
     * 0：当日建を含む<BR>
     * 1：当日建を含まない<BR>
     */
    public void setProfitLossCalcType(String l_strProfitLossCalcType)
    {
        this.profitLossCalcType = l_strProfitLossCalcType;
    }
    /**
     * (set当日清算値)<BR>
     * 
     * 引数.当日清算値をthis.当日清算値にセットする。<BR>
     * @@param dblCurrentBizDateLiquidationPrice - (当日清算値)<BR>
     */
    public void setCurrentBizDateLiquidationPrice(double l_dblCurrentBizDateLiquidationPrice)
    {
        this.currentBizDateLiquidationPrice = l_dblCurrentBizDateLiquidationPrice;
    }


    /**
     * (get銘柄ID)<BR>
     * 
     * this.銘柄IDを返却する。<BR>
     * @@return long
     * @@roseuid 412486C40156
     */
    public long getProductId()
    {
        return this.productId;
    }

    /**
     * (get銘柄コード)<BR>
     * 
     * this.銘柄コードを返却する。<BR>
     * @@return String
     * @@roseuid 412486F103E7
     */
    public String getProductCode()
    {
        return this.productCode;
    }

    /**
     * (get先物オプション商品区分)<BR>
     *
     * this.先物オプション商品区分を返却する。<BR>
     * @@return IfoDerivativeTypeEnum
     */
    public IfoDerivativeTypeEnum getIfoDerivativeType()
    {
        return this.ifoDerivativeType;
    }

    /**
     * (get時価)<BR>
     * 
     * this.時価を返却する。<BR>
     * @@return double
     * @@roseuid 412487020185
     */
    public double getCurrentPrice()
    {
        return this.currentPrice;
    }

    /**
     * (get指数乗数)<BR>
     * 
     * this.指数乗数を返却する。<BR>
     * @@return double
     * @@roseuid 4124872802FD
     */
    public double getUnitSize()
    {
        return this.unitSize;
    }

    /**
     * (get買建数量)<BR>
     * 
     * this.買建数量を返却する。<BR>
     * @@return double
     * @@roseuid 4124807001CE
     */
    public double getBuyQuantity()
    {
        return this.buyQuantity;
    }

    /**
     * (get売建数量)<BR>
     * 
     * this.売建数量を返却する。<BR>
     * @@return double
     * @@roseuid 4124867C0378
     */
    public double getSellQuantity()
    {
        return this.sellQuantity;
    }
    
    /**
     * (get当日買建数量)<BR>
     * 
     * this.当日買建数量を返却する。<BR>
     * @@return double
     */
    public double getTodayBuyQuantity()
    {
        return this.todayBuyQuantity;
    }

    /**
     * (get当日売建数量)<BR>
     * 
     * this.当日売建数量を返却する。<BR>
     * @@return double
     */
    public double getTodaySellQuantity()
    {
        return this.todaySellQuantity;
    }
    
    /**
     * (get評価損益計算区分)<BR>
     *
     * this.評価損益計算区分を返却する。<BR>
     * @@return String
     */
    public String getProfitLossCalcType()
    {
        return this.profitLossCalcType;
    }

    /**
     * (get当日清算値)<BR>
     *
     * this.評価損益計算区分を返却する。<BR>
     * @@return String
     */
    public double getCurrentBizDateLiquidationPrice()
    {
        return this.currentBizDateLiquidationPrice;
    }
          
    /**
     * (get買建数量＜証拠金不足仮確定＞)<BR>
     * 
     * this.買建数量＜証拠金不足仮確定＞を返却する。<BR>
     * @@return double
     * @@roseuid 4124807001CE
     */
    public double getBuyQuantityTemp()
    {
        return this.buyQuantityTemp;
    }

    /**
     * (get売建数量＜証拠金不足仮確定＞)<BR>
     * 
     * this.売建数量＜証拠金不足仮確定＞を返却する。<BR>
     * @@return double
     * @@roseuid 4124867C0378
     */
    public double getSellQuantityTemp()
    {
        return this.sellQuantityTemp;
    }

    /**
     * (is先物)<BR>
     * 
     * 該当先物OP建玉集計が先物であるかを判定する。<BR>
     * 
     * this.先物オプション商品区分==”先物”の場合、trueを返却する。以外、falseを返却す
     * る。<BR>
     * @@return boolean
     * @@roseuid 411A1EAF032C
     */
    public boolean isFutures()
    {
        //this.先物オプション商品区分==”先物”の場合
        if (this.ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES)
        {
            //trueを返却する
            return true;
        }
        //それ以外
        else
        {
            //falseを返却する
            return false;
        }
    }
}
@
