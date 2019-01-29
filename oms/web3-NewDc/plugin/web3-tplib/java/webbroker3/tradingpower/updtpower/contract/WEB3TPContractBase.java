head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractBase.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建玉基本情報(WEB3TPContractBase.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 齋藤　@栄三 (FLJ) 新規作成
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (建玉基本情報)
 */
public abstract class WEB3TPContractBase 
{
    
    /**
     * (建玉代金)
     */
    private double contractAmount;
    
    /**
     * (必要保証金)
     */
    private double marginDeposit;
    
    /**
     * (現金必要保証金)
     */
    private double cashMarginDeposit;
    
    /**
     * (get建玉代金)<BR>
     * 建玉代金を取得する。<BR>
     * @@return double
     * @@roseuid 40DA7E190296
     */
    public double getContractAmount() 
    {
        return contractAmount;
    }
    
    /**
     * (set建玉代金)<BR>
     * 引数の建玉代金をセットする。<BR>
     * @@param l_dblContractAmount - (建玉代金)
     * @@roseuid 40DA7E1902E4
     */
    public void setContractAmount(double l_dblContractAmount) 
    {
        contractAmount = l_dblContractAmount;
    }
    
    /**
     * (get必要保証金)<BR>
     * 必要保証金を取得する。<BR>
      * @@return double
     * @@roseuid 40DA7E1902A6
     */
    public double getMarginDeposit() 
    {
        return marginDeposit;
    }
    
    /**
     * (set必要保証金)<BR>
     * 引数の必要保証金をセットする。<BR>
     * @@param l_dblMarginDeposit - (必要保証金)
     * @@roseuid 40DA7E190303
     */
    public void setMarginDeposit(double l_dblMarginDeposit) 
    {
        marginDeposit = l_dblMarginDeposit;
    }
    
    /**
     * (get現金必要保証金)<BR>
     * 現金必要保証金を取得する。<BR>
      * @@return double
     * @@roseuid 40DA7E1902C5
     */
    public double getCashMarginDeposit() 
    {
        return cashMarginDeposit;
    }
    
    /**
     * (set現金必要保証金)<BR>
     * 引数の現金必要保証金をセットする。<BR>
     * @@param l_dblCashMarginDeposit - (現金必要保証金)
     * @@roseuid 40DA7E190313
     */
    public void setCashMarginDeposit(double l_dblCashMarginDeposit) 
    {
        cashMarginDeposit = l_dblCashMarginDeposit;
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("contractAmount", getContractAmount())
            .append("marginDeposit", getMarginDeposit())
            .append("cashMarginDeposit", getCashMarginDeposit())
            .toString();
    }
}
@
