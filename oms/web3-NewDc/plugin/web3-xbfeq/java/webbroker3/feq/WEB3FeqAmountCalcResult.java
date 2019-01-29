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
filename	WEB3FeqAmountCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式金額計算結果(WEB3FeqAmountCalcResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 芦露 (中訊) 新規作成
                   2005/07/26 王亞洲(中訊) レビュー
*/

package webbroker3.feq;


/**
 * (外国株式金額計算結果) <BR>
 * 外国株式金額計算結果 <BR>
 * <BR>
 * @@ author 芦露 <BR> 
 * @@ version 1.0 <BR>
 */
public class WEB3FeqAmountCalcResult 
{
    
    /**
     * (現地諸経費)<BR>
     * 現地諸経費
     */
    private WEB3FeqForeignCost foreignCost;
    
    /**
     * (売買代金（外貨）)<BR>
     * 売買代金（外貨）
     */
    private double tradePriceFc = 0;
    
    /**
     * (売買代金（円貨）)<BR>
     * 売買代金（円貨）
     */
    private double tradePrice = 0;
    
    /**
     * (現地清算代金（円貨）)<BR>
     * 現地清算代金（円貨）
     */
    private double balanceAmount = 0;
    
    /**
     * (受渡代金)<BR>
     * 受渡代金
     */
    private double netAmount = 0;
    
    /**
     * (受渡代金（外貨）)<BR>
     * 受渡代金（外貨）
     */
    private double netAmountFc = 0;
    
    /**
     * (委託手数料)<BR>
     * 委託手数料
     */
    private double commissionFee = 0;
    
    /**
     * (委託手数料消費税)<BR>
     * 委託手数料消費税
     */
    private double commissionFeeTax = 0;
    
    /**
     * (委託手数料（外貨）) <BR>
     * 委託手数料（外貨）
     */
    private double commissionFeeFc = 0;
    
    /**
     * (委託手数料消費税（外貨）)<BR>
     * 委託手数料消費税（外貨）
     */
    private double commissionFeeTaxFc = 0;
    
    /**
     * (手数料No) <BR>
     * 手数料計算に使用した手数料No。 <BR>
     * calc委託手数料が返却する。 <BR>
     */
    private String commissionNumber;
    
    /**
     * (手数料No枝番) <BR>
     * 手数料計算に使用した手数料No枝番。 <BR>
     * calc委託手数料が返却する。 <BR>
     */
    private String commissionBranchNumber;
    
    /**
     * (徴収率)<BR>
     * 手数料計算に使用した徴収率。
     */
    private double chargeRatio;
    
    /**
     * (外国株式金額計算結果) <BR>
     * デフォルトコンストラクタ
     * @@roseuid 4289A57E008C
     */
    public WEB3FeqAmountCalcResult() 
    {
        foreignCost = new WEB3FeqForeignCost();
    }
    
    /**
     * (get現地手数料) <BR>
     * 現地手数料を取得する。 <BR>
     *  <BR>
     * this.現地諸経費.現地手数料を返却する。 <BR>
     * @@return double
     * @@roseuid 4289910A01A5
     */
    public double getForignCommissionFee() 
    {
        return foreignCost.getForeignCommissionFee();
    }
    
    /**
     * (現地取引税) <BR>
     * 現地取引税を取得する。 <BR>
     *  <BR>
     * this.現地諸経費.現地取引税を返却する。 <BR>
     * @@return double
     * @@roseuid 4289910A01C4
     */
    public double getForeignTax() 
    {
        return foreignCost.getForeignTax();
    }
    
    /**
     * (getその他コスト１) <BR>
     * その他コスト１を取得する。 <BR>
     *  <BR>
     * this.現地諸経費.その他コスト１を返却する。 <BR>
     * @@return double
     * @@roseuid 4289910A01D4
     */
    public double getForeignFeeExt1() 
    {
        return foreignCost.getForeignFeeExt1();
    }
    
    /**
     * (getその他コスト２)<BR>
     * その他コスト２を取得する。<BR>
     * <BR>
     * this.現地諸経費.その他コスト２を返却する。<BR>
     * @@return double
     * @@roseuid 4289910A01F3
     */
    public double getForeignFeeExt2() 
    {
        return foreignCost.getForeignFeeExt2();
    }
    
    /**
     * (get現地清算代金) <BR>
     * 現地清算代金を取得する。 <BR>
     *  <BR>
     * this.現地諸経費.現地清算代金を返却する。 <BR>
     * @@return double
     * @@roseuid 4289910A0203
     */
    public double getBalanceAmountFc() 
    {
        return foreignCost.getBalanceAmountFc();
    }
    
    /**
     * (get現地清算代金（円貨）) <BR>
     * 現地清算代金（円貨）を取得する。 <BR>
     *  <BR>
     * this.現地清算代金（円貨）を返却する。 <BR>
     * @@return double
     * @@roseuid 4289915C0241
     */
    public double getBalanceAmount() 
    {
        return balanceAmount;
    }
    
    /**
     * (get受渡代金) <BR>
     * 受渡代金を取得する。 <BR>
     *  <BR>
     * this.受渡代金を返却する。 <BR>
     * @@return double
     * @@roseuid 4289918F0157
     */
    public double getNetAmount() 
    {
        return netAmount;
    }
    
    /**
     * (get受渡代金（外貨）) <BR>
     * 受渡代金（外貨）を取得する。 <BR>
     *  <BR>
     * this.受渡代金（外貨）を返却する。 <BR>
     * @@return double
     * @@roseuid 428991B703E7
     */
    public double getNetAmountFc() 
    {
        return netAmountFc;
    }
    
    /**
     * (get委託手数料) <BR>
     * 委託手数料を取得する。 <BR>
     *  <BR>
     * this.委託手数料を返却する。 <BR>
     * @@return double
     * @@roseuid 428991FB02BE
     */
    public double getCommissionFee() 
    {
        return commissionFee;
    }
    
    /**
     * (get委託手数料（外貨）) <BR>
     * 委託手数料（外貨）を取得する。 <BR>
     *  <BR>
     * this.委託手数料（外貨）を返却する。 <BR>
     * @@return double
     * @@roseuid 428991FB02CE
     */
    public double getCommissionFeeFc() 
    {
        return commissionFeeFc;
    }
    
    /**
     * (get委託手数料消費税) <BR>
     * 委託手数料消費税を取得する。 <BR>
     *  <BR>
     * this.委託手数料消費税を返却する。 <BR>
     * @@return double
     * @@roseuid 4289925A00EA
     */
    public double getCommisionFeeTax() 
    {
        return commissionFeeTax;
    }
    
    /**
     * (get委託手数料消費税（外貨）) <BR>
     * 委託手数料消費税（外貨）を取得する。 <BR>
     *  <BR>
     * this.委託手数料消費税（外貨）を返却する。 <BR>
     * @@return double
     * @@roseuid 4289925A0109
     */
    public double getCommisionFeeTaxFc() 
    {
        return commissionFeeTaxFc;
    }
    
    /**
     * (get売買代金（円貨）) <BR>
     * 売買代金（円貨）を取得する。 <BR>
     *  <BR>
     * this.売買代金（円貨）を返却する。 <BR>
     * @@return double
     * @@roseuid 4289A4B3028F
     */
    public double getTradePrice() 
    {
        return tradePrice;
    }
    
    /**
     * (get売買代金（外貨）) <BR>
     * 売買代金（外貨）を取得する。 <BR>
     *  <BR>
     * this.売買代金（外貨）を返却する。 <BR>
     * @@return double
     * @@roseuid 4289A4B30290
     */
    public double getTradePriceFc() 
    {
        return tradePriceFc;
    }
    
    /**
     * (get手数料No) <BR>
     * 手数料Noを取得する。
     * @@return String
     * @@roseuid 4289AD73030E
     */
    public String getCommissionNumber() 
    {
        return commissionNumber;
    }
    
    /**
     * (get手数料No枝番) <BR>
     * 手数料No枝番を取得する。
     * @@return String
     * @@roseuid 4289AD73031E
     */
    public String getCommissionBranchNumber() 
    {
        return commissionBranchNumber;
    }
    
    /**
     * (set現地手数料) <BR>
     * 現地手数料をセットする。 <BR>
     *  <BR>
     * this.現地諸経費.現地手数料に現地手数料をセットする。 <BR>
     * @@param l_dblForeignCommFee - (現地手数料) <BR>
     * 現地手数料
     * @@roseuid 4289910A0222
     */
    public void setForeignCommissionFee(double l_dblForeignCommFee) 
    {
        foreignCost.setForeignCommissionFee(l_dblForeignCommFee);
    }
    
    /**
     * (set現地取引税) <BR>
     * 現地取引税をセットする。 <BR>
     *  <BR>
     * this.現地諸経費.現地取引税に現地取引税をセットする。 <BR>
     * @@param l_dblForeignTax - (現地取引税) <BR>
     * 現地取引税
     * @@roseuid 4289910A0241
     */
    public void setForeignTax(double l_dblForeignTax) 
    {
        foreignCost.setForeignTax(l_dblForeignTax); 
    }
    
    /**
     * (setその他コスト１) <BR>
     * その他コスト１をセットする。 <BR>
     *  <BR>
     * this.現地諸経費.その他コスト１にその他コスト１をセットする。 <BR>
     * @@param l_dblForeignFeeExt1 - (その他コスト１) <BR>
     * その他コスト１
     * @@roseuid 4289910A0251
     */
    public void setForeignFeeExt1(double l_dblForeignFeeExt1) 
    {
        foreignCost.setForeignFeeExt1(l_dblForeignFeeExt1);   
    }
    
    /**
     * (setその他コスト２) <BR>
     * その他コスト２をセットする。 <BR>
     *  <BR>
     * this.現地諸経費.その他コスト２にその他コスト２をセットする。 <BR>
     * @@param l_dblForeignFeeExt2 - (その他コスト２) <BR>
     * その他コスト２
     * @@roseuid 4289910A0270
     */
    public void setForeignFeeExt2(double l_dblForeignFeeExt2) 
    {
        foreignCost.setForeignFeeExt2(l_dblForeignFeeExt2);    
    }
    
    /**
     * (set現地清算代金) <BR>
     * 現地清算代金をセットする。 <BR>
     *  <BR>
     * this.現地諸経費.現地清算代金に現地清算代金をセットする。 <BR>
     * @@param l_dblBalanceAmountFc - (現地清算代金) <BR>
     * 現地清算代金
     * @@return Void
     * @@roseuid 4289910A028F
     */
    public void setBalanceAmountFc(double l_dblBalanceAmountFc) 
    {
        foreignCost.setBalanceAmountFc(l_dblBalanceAmountFc);
    }
    
    /**
     * (set現地清算代金（円貨）) <BR>
     * 現地清算代金（円貨）をセットする。 <BR>
     *  <BR>
     * this.現地清算代金（円貨）に現地清算代金（円貨）をセットする。 <BR>
     * @@param l_dblBalanceAmount - (現地清算代金（円貨）) <BR>
     * 現地清算代金（円貨）
     * @@roseuid 4289915C0261
     */
    public void setBalanceAmount(double l_dblBalanceAmount) 
    {
        balanceAmount = l_dblBalanceAmount;    
    }
    
    /**
     * (set受渡代金) <BR>
     * 受渡代金をセットする。 <BR>
     *  <BR>
     * this.受渡代金に受渡代金をセットする。 <BR>
     * @@param l_dblNetAmount - (受渡代金) <BR>
     * @@roseuid 4289918F0176
     */
    public void setNetAmount(double l_dblNetAmount) 
    {
        netAmount = l_dblNetAmount;
    }
    
    /**
     * (set受渡代金（外貨）) <BR>
     * 受渡代金（外貨）をセットする。 <BR>
     *  <BR>
     * this.受渡代金（外貨）に受渡代金（外貨）をセットする。 <BR>
     * @@param l_dblNetAmountFc - 受渡代金（外貨） <BR>
     * @@roseuid 428991B8001E
     */
    public void setNetAmountFc(double l_dblNetAmountFc) 
    {
        netAmountFc = l_dblNetAmountFc; 
    }
    
    /**
     * (set委託手数料) <BR>
     * 委託手数料をセットする。<BR>
     * <BR>
     * this.委託手数料に委託手数料をセットする。<BR>
     * @@param l_dblCommisionFee - 委託手数料
     * @@roseuid 428991FB02ED
     */
    public void setCommissionFee(double l_dblCommisionFee) 
    {
        commissionFee = l_dblCommisionFee;
    }
    
    /**
     * (set委託手数料（外貨）) <BR>
     * 委託手数料（外貨）をセットする。 <BR>
     *  <BR>
     * this.委託手数料（外貨）に委託手数料（外貨）をセットする。 <BR>
     * @@param l_dblCommisionFeeFc - 委託手数料（外貨）
     * @@roseuid 428991FB031C
     */
    public void setCommissionFeeFc(double l_dblCommisionFeeFc) 
    {
        commissionFeeFc = l_dblCommisionFeeFc; 
    }
    
    /**
     * (set委託手数料消費税) <BR>
     * 委託手数料消費税をセットする。 <BR>
     *  <BR>
     * this.委託手数料消費税に委託手数料消費税をセットする。 <BR>
     * @@param l_dblCommisionFeeTax - 委託手数料消費税
     * @@roseuid 4289925A0128
     */
    public void setCommissionFeeTax(double l_dblCommisionFeeTax) 
    {
        commissionFeeTax = l_dblCommisionFeeTax;
    }
    
    /**
     * (set委託手数料消費税（外貨）) <BR>
     * 委託手数料消費税（外貨）をセットする。 <BR>
     *  <BR>
     * this.委託手数料消費税（外貨）に委託手数料消費税（外貨）をセットする。 <BR>
     * @@param l_dblCommisionFeeTaxFc - 委託手数料消費税（外貨）
     * @@roseuid 4289925A0147
     */
    public void setCommissionFeeTaxFc(double l_dblCommisionFeeTaxFc) 
    {
        commissionFeeTaxFc = l_dblCommisionFeeTaxFc;    
    }
    
    /**
     * (set売買代金（円貨）) <BR>
     * 売買代金（円貨）をセットする。 <BR>
     *  <BR>
     * this.売買代金（円貨）に売買代金（円貨）をセットする。 <BR>
     * @@param l_dblTradePrice - 売買代金（円貨）
     * @@roseuid 4289A4B30291
     */
    public void setTradePrice(double l_dblTradePrice) 
    {
        tradePrice = l_dblTradePrice; 
    }
    
    /**
     * (set売買代金（外貨）) <BR>
     * 売買代金（外貨）をセットする。 <BR>
     *  <BR>
     * this.売買代金（外貨）に売買代金（外貨）をセットする。 <BR>
     * @@param l_dblTradePriceFc - 売買代金（外貨）
     * @@roseuid 4289A4B30293
     */
    public void setTradePriceFc(double l_dblTradePriceFc) 
    {
        tradePriceFc = l_dblTradePriceFc;
    }
    
    /**
     * (set現地諸経費) <BR>
     * 現地諸経費をセットする。 <BR>
     *  <BR>
     * this.現地諸経費に現地諸経費をセットする。 <BR>
     * @@param l_foreignCost - 現地諸経費
     * @@roseuid 4289AB840118
     */
    public void setForeignCost(WEB3FeqForeignCost l_foreignCost) 
    {
        this.foreignCost = l_foreignCost;
    }
    
    /**
     * (set手数料No) <BR>
     * 手数料Noをセットする。
     * @@param l_strCommisionNumber - 手数料No
     * @@roseuid 4289AD73030C
     */
    public void setCommissionNumber(String l_strCommisionNumber) 
    {
        commissionNumber = l_strCommisionNumber;
    }
    
    /**
     * (set手数料No枝番) <BR>
     * 手数料No枝番をセットする。
     * @@param l_strCommBranchNumber - 手数料No枝番
     * @@roseuid 4289AD73031C
     */
    public void setCommissionBranchNumber(String l_strCommBranchNumber) 
    {
        commissionBranchNumber = l_strCommBranchNumber;  
    }
    
    /**
     * (set徴収率)<BR>
     * 徴収率をセットする。
     * @@param l_dblChargeRatio - 徴収率
     */
    public void setChargeRatio(double l_dblChargeRatio)
    {
        this.chargeRatio = l_dblChargeRatio;
    }
    
    /**
     * (get徴収率)<BR>
     * 徴収率を取得する。
     * @@return double
     */
    public double getChargeRatio()
    {
        return this.chargeRatio;
    }
}
@
