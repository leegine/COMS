head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractSettleSpecify.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 現注文内容の返済指定情報(WEB3TPContractSettleSpecify.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/02 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.tradingpower.updtpower;

import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (現注文内容の返済指定情報) <BR>
 * 余力計算時使用する現注文内容の返済指定情報を表現する。
 */
public class WEB3TPContractSettleSpecify
{

    /**
     * 建玉ID
     */
    public long contractId;

    /**
     * 返済数量
     */
    public double quantity;

    /**
     * @@roseuid 4136B0440277
     */
    public WEB3TPContractSettleSpecify()
    {

    }

    /**
     * (get建玉ID)<BR>
     * <BR>
     * 建玉IDを取得する。<BR>
     * @@return long
     * @@roseuid 4100C81703E4
     */
    public long getContractId()
    {
        return contractId;
    }

    /**
     * (set建玉ID)<BR>
     * <BR>
     * 建玉IDをセットする。<BR>
     * @@param l_lngContractId - 建玉ID
     * @@roseuid 4100C818001B
     */
    public void setContractId(long l_lngContractId)
    {
        contractId = l_lngContractId;
    }

    /**
     * (get返済数量)<BR>
     * <BR>
     * 返済数量を取得する。<BR>
     * @@return double
     * @@roseuid 4100CAD402A5
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * (set返済数量)<BR>
     * <BR>
     * 返済数量をセットする。<BR>
     * @@param l_dblQuantity - 返済数量
     * @@roseuid 4100CAD402C4
     */
    public void setQuantity(double l_dblQuantity)
    {
        quantity = l_dblQuantity;
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("contractId", contractId)
            .append("quantity", quantity)
            .toString();
    }

}
@
