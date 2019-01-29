head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAmountCalcResultFactor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式金額計算結果（按分）(WEB3FeqAmountCalcResultFactor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 芦露 (中訊) 新規作成
                   2005/07/27 魏馨(中訊) レビュー
*/

package webbroker3.feq;


/**
 * (外国株式金額計算結果（按分）) <BR>
 * 外国株式金額計算結果（按分）<BR>
 * <BR>
 * @@ author 芦露 <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3FeqAmountCalcResultFactor 
{
    
    /**
     * (外国株式金額計算結果（明細）) <BR>
     * 外国株式金額計算結果（明細）
     */
    private WEB3FeqAmountCalcResult[] feqAmountCalcResultDetails;
    
    /**
     * (外国株式金額計算結果（合計）) <BR>
     * 外国株式金額計算結果（合計）
     */
    private WEB3FeqAmountCalcResult feqAmountCalcResultTotal;
    
    /**
     * @@roseuid 42CE39E700CB
     */
    public WEB3FeqAmountCalcResultFactor() 
    {
     
    }
    
    /**
     * (外国株式金額計算結果（按分）) <BR>
     * <BR>
     * 引数の値をプロパティにセットしてオブジェクトを生成する。<BR>
     * @@param l_feqAmountCalcResultDetails - (外国株式金額計算結果（明細）) <BR>
     * 外国株式金額計算結果（明細）
     * @@param l_feqAmountCalcResultTotal - (外国株式金額計算結果（合計）) <BR>
     * 外国株式金額計算結果（合計）
     * @@roseuid 428AA6940214
     */
    public WEB3FeqAmountCalcResultFactor(WEB3FeqAmountCalcResult[] l_feqAmountCalcResultDetails, WEB3FeqAmountCalcResult l_feqAmountCalcResultTotal) 
    {
        feqAmountCalcResultDetails = l_feqAmountCalcResultDetails;
        feqAmountCalcResultTotal = l_feqAmountCalcResultTotal;  
    }
    
    /**
     * (get外国株式金額計算結果（合計）) <BR>
     * this.外国株式金額計算結果（合計）を返却する。
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 428AA6D6006E
     */
    public WEB3FeqAmountCalcResult getFeqAmountCalcResultTotal() 
    {
        return feqAmountCalcResultTotal;
    }
    
    /**
     * (get外国株式金額計算結果（明細）) <BR>
     * this.外国株式金額計算結果（明細）[index]を返却する。
     * @@param l_intIndex - index（：要素番号）
     * 
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 42B27B0B02D2
     */
    public WEB3FeqAmountCalcResult getFeqAmountCalcResultDetails(int l_intIndex) 
    {
        return feqAmountCalcResultDetails[l_intIndex];
    }
    
    /**
     * (set外国株式金額計算結果（明細）) <BR>
     * 外国株式金額計算結果（明細）をセットする。
     * @@param l_feqAmountCalcResultDetails - (外国株式金額計算結果（明細）) <BR>
     * 外国株式金額計算結果（明細）
     * @@roseuid 428AA6F503CA
     */
    public void setFeqAmountCalcResultDetails(WEB3FeqAmountCalcResult[] l_feqAmountCalcResultDetails) 
    {
        this.feqAmountCalcResultDetails = l_feqAmountCalcResultDetails;
    }
    
    /**
     * (set外国株式金額計算結果（合計)) <BR>
     * 外国株式金額計算結果（合計）をセットする。
     * @@param l_feqAmountCalcResultTotal - 外国株式金額計算結果（合計）
     * @@roseuid 428AA6F503CB
     */
    public void setFeqAmountCalcResultTotal(WEB3FeqAmountCalcResult l_feqAmountCalcResultTotal) 
    {
        feqAmountCalcResultTotal = l_feqAmountCalcResultTotal; 
    }
    
    /**
     * (getその他コスト１) <BR>
     * その他コスト１を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細）[index].getその他コスト１()  <BR>
     * を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC00027
     */
    public double getForeignFeeExt1(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getForeignFeeExt1();
    }
    
    /**
     * (getその他コスト２) <BR>
     * その他コスト２を取得する。<BR>
     * <BR>
     * this.外国株式金額計算結果（明細）[index].getその他コスト２() <BR>
     * を返却する。<BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC00046
     */
    public double getForeignFeeExt2(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getForeignFeeExt2();
    }
    
    /**
     * 委託手数料を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細） <BR>
     * [index].get委託手数料()を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC00055
     */
    public double getCommisionFee(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommissionFee();
    }
    
    /**
     * (get委託手数料（外貨）) <BR>
     * 委託手数料（外貨）を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細） <BR>
     * [index].get委託手数料（外貨）()を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC00065
     */
    public double getCommisionFeeFc(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommissionFeeFc();
    }
    
    /**
     * (get委託手数料消費税) <BR>
     * 委託手数料消費税を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細） <BR>
     * [index].get委託手数料消費税()を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC00075
     */
    public double getCommisionFeeTax(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommisionFeeTax();
    }
    
    /**
     * (get委託手数料消費税（外貨）) <BR>
     * 委託手数料消費税（外貨）を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細） <BR>
     * [index].get委託手数料消費税（外貨）()を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC00084
     */
    public double getCommisionFeeTaxFc(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommisionFeeTaxFc();
    }
    
    /**
     * (get現地取引税) <BR>
     * 現地取引税を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細）[index].get現地取引税()を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC00094
     */
    public double getForeignTax(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getForeignTax();
    }
    
    /**
     * (get現地手数料) <BR>
     * 現地手数料を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細）[index].get現地手数料() <BR>
     * を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC000A4
     */
    public double getForeignCommissionFee(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getForignCommissionFee();
    }
    
    /**
     * (get現地清算代金) <BR>
     * 現地清算代金を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細） <BR>
     * [index].get現地清算代金()を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC000B3
     */
    public double getBalanceAmountFc(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getBalanceAmountFc();
    }
    
    /**
     * (get現地清算代金（円貨）) <BR>
     * 現地清算代金（円貨）を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細） <BR>
     * [index].get現地清算代金（円貨）()を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC000D2
     */
    public double getBalanceAmount(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getBalanceAmount();
    }
    
    /**
     * (get手数料No) <BR>
     * 手数料Noを取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細）[index].get手数料No.() <BR>
     * を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return String
     * @@roseuid 42A80EC000F2
     */
    public String getCommisionNumber(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommissionNumber();
    }
    
    /**
     * (get手数料No枝番) <BR>
     * 手数料No枝番を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細）[index].get委託No.枝番() <BR>
     * を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return String
     * @@roseuid 42A80EC00101
     */
    public String getCommisionBranchNumber(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommissionBranchNumber();
    }
    
    /**
     * (get受渡代金) <BR>
     * 受渡代金を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細）[index].get受渡代金()を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC00111
     */
    public double getNetAmount(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getNetAmount();
    }
    
    /**
     * (get受渡代金（外貨）) <BR>
     * 受渡代金（外貨）を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細） <BR>
     * [index].get受渡代金（外貨）()を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC00121
     */
    public double getNetAmountFc(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getNetAmountFc();
    }
    
    /**
     * (get売買代金（円貨）) <BR>
     * 売買代金（円貨）を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細） <BR>
     * [index].get売買代金（円貨）()を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC00130
     */
    public double getTradePrice(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getTradePrice();
    }
    
    /**
     * (get売買代金（外貨）) <BR>
     * 売買代金（外貨）を取得する。 <BR>
     *  <BR>
     * this.外国株式金額計算結果（明細） <BR>
     * [index].get売買代金（外貨）()を返却する。 <BR>
     * @@param l_intIndex - (index) <BR>
     * index（：要素番号）
     * 
     * @@return double
     * @@roseuid 42A80EC00140
     */
    public double getTradePriceFc(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getTradePriceFc();
    }
}
@
