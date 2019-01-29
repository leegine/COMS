head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondEstimatedPriceCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 債券受渡代金計算結果(WEB3BondEstimatedPriceCalcResult.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  齊珂 (中訊) 新規作成
 */

package webbroker3.bd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * (債券受渡代金計算結果)<BR>
 * 債券受渡代金計算結果クラス<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3BondEstimatedPriceCalcResult 
{
    
    /**
     * (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     */
    private WEB3BondOrderTypeJudge bondOrderTypeJudge;
    
    /**
     * (売買代金（円貨）)<BR>
     * 売買代金（円貨）<BR>
     */
    private BigDecimal tradingPrice;
    
    /**
     * (経過利子（円貨）)<BR>
     * 経過利子（円貨）<BR>
     */
    private BigDecimal accruedInterest;
    
    /**
     * (受渡代金（円貨）)<BR>
     * 受渡代金（円貨）<BR>
     */
    private BigDecimal estimatedPrice;
    
    /**
     * (売買代金（外貨）)<BR>
     * 売買代金（外貨）<BR>
     */
    private BigDecimal foreignTradePrice;
    
    /**
     * (経過利子（外貨）)<BR>
     * 経過利子（外貨）<BR>
     */
    private BigDecimal foreignAccruedInterest;
    
    /**
     * (受渡代金（外貨）)<BR>
     * 受渡代金（外貨）<BR>
     */
    private BigDecimal foreignEstimatedPrice;
    
    /**
     * (経過日数)<BR>
     * 経過日数<BR>
     */
    private Integer elapsedDays;
    
    /**
     * (基準日数)<BR>
     * 基準日数<BR>
     */
    private Integer calcBaseDays;
    
    /**
     * (為替レート)<BR>
     * 為替レート<BR>
     */
    private BigDecimal fxRate;
    
    /**
     * (単価)<BR>
     * 単価<BR>
     */
    private BigDecimal price;
    
    /**
     * (数量)<BR>
     * 数量<BR>
     */
    private BigDecimal quantity;
    
    /**
     * (警告区分一覧)<BR>
     * 警告区分一覧<BR>
     * <BR>
     * 　@警告区分のStringを複数保持可能とするリスト<BR>
     */
    private List warningDivList;
    
    /**
     * @@roseuid 44E336120177
     */
    public WEB3BondEstimatedPriceCalcResult() 
    {
     
    }
    
    /**
     * (get売買代金（円貨）)<BR>
     * get売買代金（円貨）<BR>
     * @@return BigDecimaｌ
     * @@roseuid 44C813D40251
     */
    public BigDecimal getTradingPrice() 
    {
        return this.tradingPrice;
    }
    
    /**
     * (set売買代金（円貨）)<BR>
     * set売買代金（円貨）<BR>
     * @@param l_bdTradingPrice - (売買代金（円貨）)<BR>
     * 売買代金（円貨）<BR>
     * @@roseuid 44C81432037A
     */
    public void setTradingPrice(BigDecimal l_bdTradingPrice) 
    {
        this.tradingPrice = l_bdTradingPrice;
    }
    
    /**
     * (get経過利子（円貨）)<BR>
     * get経過利子（円貨）<BR>
     * @@return BigDecimaｌ
     * @@roseuid 44C813E60138
     */
    public BigDecimal getAccruedInterest() 
    {
        return this.accruedInterest;
    }
    
    /**
     * (set経過利子（円貨）)<BR>
     * set経過利子（円貨）<BR>
     * @@param l_bdAccruedInterest - (経過利子（円貨）)<BR>
     * 経過利子（円貨）<BR>
     * @@roseuid 44C8144802DE
     */
    public void setAccruedInterest(BigDecimal l_bdAccruedInterest) 
    {
        this.accruedInterest = l_bdAccruedInterest;
    }
    
    /**
     * (get受渡代金（円貨）)<BR>
     * get受渡代金（円貨）<BR>
     * @@return BigDecimaｌ
     * @@roseuid 44C813F101F4
     */
    public BigDecimal getEstimatedPrice() 
    {
        return this.estimatedPrice;
    }
    
    /**
     * (set受渡代金（円貨）)<BR>
     * set受渡代金（円貨）<BR>
     * @@param l_bdEstimatedPrice - (受渡代金（円貨）)<BR>
     * 受渡代金（円貨）<BR>
     * @@roseuid 44C8144C01E4
     */
    public void setEstimatedPrice(BigDecimal l_bdEstimatedPrice) 
    {
        this.estimatedPrice = l_bdEstimatedPrice;
    }
    
    /**
     * (get売買代金（外貨）)<BR>
     * get売買代金（外貨）<BR>
     * @@return BigDecimaｌ
     * @@roseuid 44C8140103A9
     */
    public BigDecimal getForeignTradePrice() 
    {
        return this.foreignTradePrice;
    }
    
    /**
     * (set売買代金（外貨）)<BR>
     * set売買代金（外貨）<BR>
     * @@param l_bdForeignTradePrice - (売買代金（外貨）)<BR>
     * 売買代金（外貨）<BR>
     * @@roseuid 44C8221C009C
     */
    public void setForeignTradePrice(BigDecimal l_bdForeignTradePrice) 
    {
        this.foreignTradePrice = l_bdForeignTradePrice;
    }
    
    /**
     * (get経過利子（外貨）)<BR>
     * get経過利子（外貨）<BR>
     * @@return BigDecimaｌ
     * @@roseuid 44C8140103B9
     */
    public BigDecimal getForeignAccruedInterest() 
    {
        return this.foreignAccruedInterest;
    }
    
    /**
     * (set経過利子（外貨）)<BR>
     * set経過利子（外貨）<BR>
     * @@param l_bdForeignAccruedInterest - (経過利子（外貨）)<BR>
     * 経過利子（外貨）<BR>
     * @@roseuid 44C8221C00BB
     */
    public void setForeignAccruedInterest(BigDecimal l_bdForeignAccruedInterest) 
    {
        this.foreignAccruedInterest = l_bdForeignAccruedInterest;
    }
    
    /**
     * (get受渡代金（外貨）)<BR>
     * get受渡代金（外貨）<BR>
     * @@return BigDecimaｌ
     * @@roseuid 44C8140103D8
     */
    public BigDecimal getForeignEstimatedPrice() 
    {
         return this.foreignEstimatedPrice;
    }
    
    /**
     * (set受渡代金（外貨）)<BR>
     * set受渡代金（外貨）<BR>
     * @@param l_bdForeignEstimatedPrice - (受渡代金（外貨）)<BR>
     * 受渡代金（外貨）<BR>
     * @@roseuid 44C8221C00DA
     */
    public void setForeignEstimatedPrice(BigDecimal l_bdForeignEstimatedPrice) 
    {
        this.foreignEstimatedPrice = l_bdForeignEstimatedPrice;
    }
    
    /**
     * (get経過日数)<BR>
     * get経過日数<BR>
     * @@return Integer
     * @@roseuid 44C81416008C
     */
    public Integer getElapsedDays() 
    {
        return this.elapsedDays;
    }
    
    /**
     * (set経過日数)<BR>
     * set経過日数<BR>
     * @@param l_intElapsedDays - (経過日数)<BR>
     * 経過日数<BR>
     * @@roseuid 44C822510399
     */
    public void setElapsedDays(Integer l_intElapsedDays) 
    {
        this.elapsedDays = l_intElapsedDays;
    }
    
    /**
     * (get基準日数)<BR>
     * get基準日数<BR>
     * @@return Integer
     * @@roseuid 44C8142600CB
     */
    public Integer getCalcBaseDays() 
    {
        return this.calcBaseDays;
    }
    
    /**
     * (set基準日数)<BR>
     * set基準日数<BR>
     * @@param l_intCalcBaseDays - (基準日数)<BR>
     * 基準日数<BR>
     * @@roseuid 44C8228901C5
     */
    public void setCalcBaseDays(Integer l_intCalcBaseDays) 
    {
        this.calcBaseDays = l_intCalcBaseDays;
    }
    
    /**
     * (calc受渡代金（円貨）)<BR>
     * calc受渡代金（円貨）<BR>
     * <BR>
     * 次の計算結果を返す<BR>
     * this.get売買代金（円貨）　@＋　@this.get経過利子（円貨）<BR>
     * @@return BigDecimal
     * @@roseuid 44CB177A014A
     */
    public BigDecimal calcEstimatedPrice() 
    {
        BigDecimal l_bdTemp = null;
        if (this.tradingPrice != null && this.accruedInterest != null)
        {    
            l_bdTemp = this.getTradingPrice().add(this.getAccruedInterest());
        }
        return l_bdTemp;
    }
    
    /**
     * (calc受渡代金（外貨）)<BR>
     * calc受渡代金（外貨）<BR>
     * <BR>
     * 次の計算結果を返す<BR>
     * this.get売買代金（外貨）　@＋　@this.get経過利子（外貨）<BR>
     * @@return BigDecimal
     * @@roseuid 44CB17CC033F
     */
    public BigDecimal calcForeignEstimatedPrice() 
    {
        BigDecimal l_bdTemp = null;
        if (this.getForeignTradePrice() != null && this.getForeignAccruedInterest() != null)
        {    
            l_bdTemp = this.getForeignTradePrice().add(this.getForeignAccruedInterest());
        }
        return l_bdTemp;
    }
    
    /**
     * (get債券注文種別判定)<BR>
     * get債券注文種別判定<BR>
     * @@return 債券注文種別判定
     * @@roseuid 44CB260701E7
     */
    public WEB3BondOrderTypeJudge getBondOrderTypeJudge() 
    {
        return this.bondOrderTypeJudge;
    }
    
    /**
     * (set債券注文種別判定)<BR>
     * set債券注文種別判定<BR>
     * @@param l_bondOrderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@roseuid 44CB262201E7
     */
    public void setBondOrderTypeJudge(WEB3BondOrderTypeJudge l_bondOrderTypeJudge) 
    {
        this.bondOrderTypeJudge = l_bondOrderTypeJudge;
    }
    
    /**
     * (get為替レート)<BR>
     * get為替レート<BR>
     * @@return BigDecimaｌ
     * @@roseuid 44CB2A5801F5
     */
    public BigDecimal getFxRate() 
    {
        return this.fxRate;
    }
    
    /**
     * (set為替レート)<BR>
     * set為替レート<BR>
     * @@param l_bdFxRate - (為替レート)<BR>
     * 為替レート<BR>
     * @@roseuid 44CB2A580243
     */
    public void setFxRate(BigDecimal l_bdFxRate) 
    {
        this.fxRate = l_bdFxRate;
    }
    
    /**
     * (get単価)<BR>
     * get単価<BR>
     * @@return BigDecimaｌ
     * @@roseuid 44CB2AAB0254
     */
    public BigDecimal getPrice() 
    {
        return this.price;
    }
    
    /**
     * (set単価)<BR>
     * set単価<BR>
     * @@param l_bdPrice - (単価)<BR>
     * 単価<BR>
     * @@roseuid 44CB2AAB0292
     */
    public void setPrice(BigDecimal l_bdPrice) 
    {
        this.price = l_bdPrice;
    }
    
    /**
     * (get数量)<BR>
     * get数量<BR>
     * @@return BigDecimaｌ
     * @@roseuid 44CB2B2B014C
     */
    public BigDecimal getQuantity() 
    {
        return this.quantity;
    }
    
    /**
     * (set数量)<BR>
     * set数量<BR>
     * @@param l_bdQuantity - (数量)<BR>
     * 数量<BR>
     * @@roseuid 44CB2B2B017B
     */
    public void setQuantity(BigDecimal l_bdQuantity) 
    {
        this.quantity = l_bdQuantity;
    }
    
    /**
     * (get警告区分一覧)<BR>
     * get警告区分一覧<BR>
     * <BR>
     * 属性の警告区分一覧のコピーListを戻す。<BR>
     * @@return List
     * @@roseuid 44DA7CA40000
     */
    public List getWarningDivList() 
    {
        return this.warningDivList;
    }
    
    /**
     * (add警告区分)<BR>
     * add警告区分<BR>
     * <BR>
     * 　@警告区分一覧に引数.警告区分が存在しない場合、<BR>
     * 　@警告区分一覧に引数.警告区分を追加する。<BR>
     * @@param l_strWarningDiv - (警告区分)<BR>
     * 警告区分<BR>
     * @@roseuid 44DA7CEA0196
     */
    public void addWarningDiv(String l_strWarningDiv) 
    {
        int l_intFlag = 1;
        int l_intSize = 0;
        
        if (this.warningDivList == null)
        {
            this.warningDivList = new ArrayList();
        }
        
        if (this.warningDivList != null && !this.warningDivList.isEmpty())
        {
            l_intSize = this.warningDivList.size();
        }
        for (int i = 0; i < l_intSize; i++)
        {
            if (this.warningDivList.get(i).equals(l_strWarningDiv))
            {
                l_intFlag = 0;
                break;
            }
        }
        if (l_intFlag != 0)
        {

            this.warningDivList.add(l_strWarningDiv);
        }    
    }
}@
