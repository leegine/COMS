head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPClosingContractSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建玉返済指定情報(WEB3TPClosingContractSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 齋藤　@栄三 (FLJ) 新規作成
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (建玉返済指定情報)
 */
public class WEB3TPClosingContractSpec 
{
    
    /**
     * (建玉ID)
     */
    private long contractId;
    
    /**
     * (建単価)
     */
    private double contractPrice;
    
    /**
     * (返済注文数量)
     */
    private double quantity;
    
    /**
     * (返済約定数量)
     */
    private double executedQuantity;
    
    /**
     * @@roseuid 4104AB480203
     */
    public WEB3TPClosingContractSpec() 
    {
     
    }
    
    /**
     * (create建玉返済指定情報)<BR>
     * 建玉返済指定情報を生成する。<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPClosingContractSpec
     * @@roseuid 40EE0BC20066
     */
    public static WEB3TPClosingContractSpec create() 
    {
        return new WEB3TPClosingContractSpec();
    }
    
    /**
     * (get建玉ID)<BR>
     * 建玉IDを取得する。<BR>
     * @@return long
     * @@roseuid 40EDF6880289
     */
    public long getContractId() 
    {
        return contractId;
    }
    
    /**
     * (set建玉ID)<BR>
     * 引数の建玉IDをセットする。<BR>
     * @@param l_lngContractId - (建玉ID)
     * @@roseuid 40EDF68802A9
     */
    public void setContractId(long l_lngContractId) 
    {
        contractId = l_lngContractId;
    }
    
    /**
     * (get建単価)<BR>
     * 建単価を取得する。<BR>
     * @@return double
     * @@roseuid 40EDF68802F7
     */
    public double getContractPrice() 
    {
        return contractPrice;
    }
    
    /**
     * (set建単価)<BR>
     * 引数の建単価をセットする。<BR>
     * @@param l_dblContractPrice - (建単価)
     * @@roseuid 40EDF6880306
     */
    public void setContractPrice(double l_dblContractPrice) 
    {
        contractPrice = l_dblContractPrice;
    }
    
    /**
     * (get返済注文数量)<BR>
     * 返済注文数量を取得する。<BR>
     * @@return double
     * @@roseuid 40EDF6880326
     */
    public double getQuantity() 
    {
        return quantity;
    }
    
    /**
     * (set返済注文数量)<BR>
     * 引数の返済注文数量をセットする。<BR>
     * @@param l_dblQuantity - (返済注文数量)
     * @@roseuid 40EDF6880345
     */
    public void setQuantity(double l_dblQuantity) 
    {
        quantity = l_dblQuantity;
    }
    
    /**
     * (get返済約定数量)<BR>
     * 返済約定数量を取得する。<BR>
     * @@return double
     * @@roseuid 40EDF6880354
     */
    public double getExecutedQuantity() 
    {
        return executedQuantity;
    }
    
    /**
     * (set返済約定数量)<BR>
     * 引数の返済約定数量をセットする。<BR>
     * @@param l_dblExecutedQuantity - (返済約定数量)
     * @@roseuid 40EDF6880374
     */
    public void setExecutedQuantity(double l_dblExecutedQuantity) 
    {
        executedQuantity = l_dblExecutedQuantity;
    }
    
    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString() 
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("contractId", getContractId())
            .append("contractPrice", getContractPrice())
            .append("quantity", getQuantity())
            .append("executedQuantity", getExecutedQuantity())
            .toString();
    }
}
@
