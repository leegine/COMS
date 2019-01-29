head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqForeignCost.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現地諸経費(WEB3FeqForeignCost.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 芦露 (中訊) 新規作成
                 : 2005/07/26 王煜(中訊) レビュー
*/

package webbroker3.feq;


/**
 * (現地諸経費) <BR>
 * 現地諸経費 <BR>
 *  <BR>
 * @@ author 芦露 <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3FeqForeignCost 
{
    
    /**
     * (現地手数料) <BR>
     * 現地手数料
     */
    private double foreignCommissionFee = 0;
    
    /**
     * (現地取引税) <BR>
     * 現地取引税
     */
    private double foreignTax = 0;
    
    /**
     * (その他コスト１) <BR>
     * その他コスト１
     */
    private double foreignFeeExt1 = 0;
    
    /**
     * (その他コスト２) <BR>
     * その他コスト２
     */
    private double foreignFeeExt2 = 0;
    
    /**
     * (現地清算代金) <BR>
     * 現地清算代金
     */
    private double balanceAmountFc = 0;
    
    /**
     * @@roseuid 42CE39E70222
     */
    public WEB3FeqForeignCost() 
    {
     
    }
    
    /**
     * (get現地手数料) <BR>
     * 現地手数料を取得する。<BR>
     * <BR>
     * this.現地手数料を返却する。<BR>
     * @@return double
     * @@roseuid 4282E7E6008F
     */
    public double getForeignCommissionFee() 
    {
        return foreignCommissionFee;
    }
    
    /**
     * (get現地取引税) <BR>
     * 現地取引税を取得する。 <BR>
     *  <BR>
     * this.現地取引税を返却する。 <BR>
     * @@return double
     * @@roseuid 4282E80203DB
     */
    public double getForeignTax() 
    {
        return foreignTax;
    }
    
    /**
     * (getその他コスト１) <BR>
     * その他コスト１を取得する。 <BR>
     *  <BR>
     * this.その他コスト１を返却する。 <BR>
     * @@return double
     * @@roseuid 4282E811015A
     */
    public double getForeignFeeExt1() 
    {
        return foreignFeeExt1;
    }
    
    /**
     * (getその他コスト２) <BR>
     * その他コスト２を取得する。 <BR>
     *  <BR>
     * this.その他コスト２を返却する。 <BR>
     * @@return double
     * @@roseuid 4282E82001B8
     */
    public double getForeignFeeExt2() 
    {
        return foreignFeeExt2;
    }
    
    /**
     * (get現地清算代金) <BR>
     * 現地清算代金を取得する。 <BR>
     *  <BR>
     * this.現地清算代金を返却する。 <BR>
     * @@return double
     * @@roseuid 4283020702C2
     */
    public double getBalanceAmountFc() 
    {
        return balanceAmountFc;
    }
    
    /**
     * (set現地手数料) <BR>
     * 現地手数料をセットする。 <BR>
     *  <BR>
     * this.現地手数料に現地手数料をセットする。 <BR>
     * @@param l_dblForeignCommisionFee - (現地手数料)
     * @@roseuid 4282E82A015A
     */
    public void setForeignCommissionFee(double l_dblForeignCommisionFee) 
    {
        foreignCommissionFee = l_dblForeignCommisionFee;
    }
    
    /**
     * (set現地取引税) <BR>
     * 現地取引税をセットする。 <BR>
     *  <BR>
     * this.現地取引税に現地取引税をセットする。 <BR>
     * @@param l_dblForeignTax - 現地取引税
     * @@roseuid 4282E854038D
     */
    public void setForeignTax(double l_dblForeignTax) 
    {
        foreignTax = l_dblForeignTax; 
    }
    
    /**
     * (setその他コスト１) <BR>
     * その他コスト１をセットする。 <BR>
     *  <BR>
     * this.その他コスト１にその他コスト１をセットする。 <BR>
     * @@param l_dblForeignFeeExt1 - その他コスト１
     * @@roseuid 4282E8700003
     */
    public void setForeignFeeExt1(double l_dblForeignFeeExt1) 
    {
        foreignFeeExt1 = l_dblForeignFeeExt1; 
    }
    
    /**
     * (setその他コスト２) <BR>
     * その他コスト２をセットする。 <BR>
     *  <BR>
     * this.その他コスト２にその他コスト２をセットする。 <BR>
     * @@param l_dblForeignFeeExt2 - その他コスト２
     * @@roseuid 4282E87000CE
     */
    public void setForeignFeeExt2(double l_dblForeignFeeExt2) 
    {
        foreignFeeExt2 = l_dblForeignFeeExt2;
    }
    
    /**
     * (set現地清算代金) <BR>
     * 現地清算代金をセットする。 <BR>
     *  <BR>
     * this.現地清算代金に現地清算代金をセットする。 <BR>
     * @@param l_dblBalanceAmountFc - (現地清算代金)
     * @@roseuid 4283022F03AC
     */
    public void setBalanceAmountFc(double l_dblBalanceAmountFc) 
    {
        balanceAmountFc = l_dblBalanceAmountFc; 
    }
}
@
