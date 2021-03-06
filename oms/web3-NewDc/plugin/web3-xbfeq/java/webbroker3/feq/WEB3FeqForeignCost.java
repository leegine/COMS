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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : »noï(WEB3FeqForeignCost.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 °I (u) VKì¬
                 : 2005/07/26 ¤ûU(u) r[
*/

package webbroker3.feq;


/**
 * (»noï) <BR>
 * »noï <BR>
 *  <BR>
 * @@ author °I <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3FeqForeignCost 
{
    
    /**
     * (»nè¿) <BR>
     * »nè¿
     */
    private double foreignCommissionFee = 0;
    
    /**
     * (»næøÅ) <BR>
     * »næøÅ
     */
    private double foreignTax = 0;
    
    /**
     * (»Ì¼RXgP) <BR>
     * »Ì¼RXgP
     */
    private double foreignFeeExt1 = 0;
    
    /**
     * (»Ì¼RXgQ) <BR>
     * »Ì¼RXgQ
     */
    private double foreignFeeExt2 = 0;
    
    /**
     * (»n´Zãà) <BR>
     * »n´Zãà
     */
    private double balanceAmountFc = 0;
    
    /**
     * @@roseuid 42CE39E70222
     */
    public WEB3FeqForeignCost() 
    {
     
    }
    
    /**
     * (get»nè¿) <BR>
     * »nè¿ðæ¾·éB<BR>
     * <BR>
     * this.»nè¿ðÔp·éB<BR>
     * @@return double
     * @@roseuid 4282E7E6008F
     */
    public double getForeignCommissionFee() 
    {
        return foreignCommissionFee;
    }
    
    /**
     * (get»næøÅ) <BR>
     * »næøÅðæ¾·éB <BR>
     *  <BR>
     * this.»næøÅðÔp·éB <BR>
     * @@return double
     * @@roseuid 4282E80203DB
     */
    public double getForeignTax() 
    {
        return foreignTax;
    }
    
    /**
     * (get»Ì¼RXgP) <BR>
     * »Ì¼RXgPðæ¾·éB <BR>
     *  <BR>
     * this.»Ì¼RXgPðÔp·éB <BR>
     * @@return double
     * @@roseuid 4282E811015A
     */
    public double getForeignFeeExt1() 
    {
        return foreignFeeExt1;
    }
    
    /**
     * (get»Ì¼RXgQ) <BR>
     * »Ì¼RXgQðæ¾·éB <BR>
     *  <BR>
     * this.»Ì¼RXgQðÔp·éB <BR>
     * @@return double
     * @@roseuid 4282E82001B8
     */
    public double getForeignFeeExt2() 
    {
        return foreignFeeExt2;
    }
    
    /**
     * (get»n´Zãà) <BR>
     * »n´Zãàðæ¾·éB <BR>
     *  <BR>
     * this.»n´ZãàðÔp·éB <BR>
     * @@return double
     * @@roseuid 4283020702C2
     */
    public double getBalanceAmountFc() 
    {
        return balanceAmountFc;
    }
    
    /**
     * (set»nè¿) <BR>
     * »nè¿ðZbg·éB <BR>
     *  <BR>
     * this.»nè¿É»nè¿ðZbg·éB <BR>
     * @@param l_dblForeignCommisionFee - (»nè¿)
     * @@roseuid 4282E82A015A
     */
    public void setForeignCommissionFee(double l_dblForeignCommisionFee) 
    {
        foreignCommissionFee = l_dblForeignCommisionFee;
    }
    
    /**
     * (set»næøÅ) <BR>
     * »næøÅðZbg·éB <BR>
     *  <BR>
     * this.»næøÅÉ»næøÅðZbg·éB <BR>
     * @@param l_dblForeignTax - »næøÅ
     * @@roseuid 4282E854038D
     */
    public void setForeignTax(double l_dblForeignTax) 
    {
        foreignTax = l_dblForeignTax; 
    }
    
    /**
     * (set»Ì¼RXgP) <BR>
     * »Ì¼RXgPðZbg·éB <BR>
     *  <BR>
     * this.»Ì¼RXgPÉ»Ì¼RXgPðZbg·éB <BR>
     * @@param l_dblForeignFeeExt1 - »Ì¼RXgP
     * @@roseuid 4282E8700003
     */
    public void setForeignFeeExt1(double l_dblForeignFeeExt1) 
    {
        foreignFeeExt1 = l_dblForeignFeeExt1; 
    }
    
    /**
     * (set»Ì¼RXgQ) <BR>
     * »Ì¼RXgQðZbg·éB <BR>
     *  <BR>
     * this.»Ì¼RXgQÉ»Ì¼RXgQðZbg·éB <BR>
     * @@param l_dblForeignFeeExt2 - »Ì¼RXgQ
     * @@roseuid 4282E87000CE
     */
    public void setForeignFeeExt2(double l_dblForeignFeeExt2) 
    {
        foreignFeeExt2 = l_dblForeignFeeExt2;
    }
    
    /**
     * (set»n´Zãà) <BR>
     * »n´ZãàðZbg·éB <BR>
     *  <BR>
     * this.»n´ZãàÉ»n´ZãàðZbg·éB <BR>
     * @@param l_dblBalanceAmountFc - (»n´Zãà)
     * @@roseuid 4283022F03AC
     */
    public void setBalanceAmountFc(double l_dblBalanceAmountFc) 
    {
        balanceAmountFc = l_dblBalanceAmountFc; 
    }
}
@
