head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetValuation.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 資産評価(WEB3TPAssetValuation.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/08/02 齋藤　@栄三 (FLJ) 新規作成
 *                  2004/08/04 劉 ((FLJ)) 実装修正
 */
package webbroker3.tradingpower.updtpower;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (資産評価) <BR>
 * 資産評価を表現する。
 */
public abstract class WEB3TPAssetValuation
{

    /**
     * 顧客属性 <BR>
     */
    private WEB3TPAccountInfo accountInfo;

    /**
     * 計算条件 <BR>
     */
    private WEB3TPCalcCondition calcCondition;

    /**
     * 余力計算時使用する現注文内容 <BR>
     */
    private webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec[] newOrderSpecs;

    /**
     * 余力計算時使用する当日注文一覧 <BR>
     */
    private java.util.List todaysEquityOrders;
    
    /**
     * @@roseuid 4136A96003C8
     */
    public WEB3TPAssetValuation()
    {

    }

    /**
     * (get余力計算条件)<BR>
     * <BR>
     * 余力計算条件を取得する。<BR>
     * @@return WEB3TPCalcCondition
     * @@roseuid 40BA8F6C0276
     */
    public WEB3TPCalcCondition getCalcCondition()
    {
        return this.calcCondition;
    }

    /**
     * (set余力計算条件)<BR>
     * <BR>
     * 余力計算条件をセットする。<BR>
     * @@param l_calcCondition - 余力計算条件
     * @@roseuid 40BA8F79012E
     */
    public void setCalcCondition(WEB3TPCalcCondition l_calcCondition)
    {
        this.calcCondition = l_calcCondition;
    }

    /**
     * (get顧客属性)<BR>
     * <BR>
     * 顧客属性を取得する。<BR>
     * @@return WEB3TPAccountInfo
     * @@roseuid 40BA8F9001F9
     */
    public WEB3TPAccountInfo getAccountInfo()
    {
        return this.accountInfo;
    }

    /**
     * (set顧客属性)<BR>
     * <BR>
     * 顧客属性をセットする。<BR>
     * @@param l_accountInfo- 顧客属性
     * @@param l_accountInfo
     * @@roseuid 40BA8F9F015D
     */
    public void setAccountInfo(WEB3TPAccountInfo l_accountInfo)
    {
        this.accountInfo = l_accountInfo;
    }

    /**
     * (get現注文内容)<BR>
     * <BR>
     * 現注文内容を取得する。<BR>
     * @@return  WEB3TPNewOrderSpec[]
     * @@roseuid 40F3C6950371
     */
    public WEB3TPNewOrderSpec[] getNewOrderSpecs()
    {
        return this.newOrderSpecs;
    }

    /**
     * (set現注文内容)<BR>
     * <BR>
     * 現注文内容をセットする。<BR>
     * @@param l_newOrderSpecs - 現注文内容配列
     * @@roseuid 40F3C6A00343
     */
    public void setNewOrderSpecs(WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        this.newOrderSpecs = l_newOrderSpecs;
    }

    /**
     * (get当日注文一覧)<BR>
     * <BR>
     * 当日注文一覧を取得する。<BR>
     * @@return List
     */
    public java.util.List getTodaysEquityOrders()
    {
        return this.todaysEquityOrders;
    }

    /**
     * (set当日注文一覧)<BR>
     * <BR>
     * 当日注文一覧をセットする。<BR>
     * @@param l_todaysEquityOrders - 当日注文一覧
     */
    public void setTodaysEquityOrders(java.util.List l_todaysEquityOrders)
    {
        this.todaysEquityOrders = l_todaysEquityOrders;
    }
    
    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("accountInfo", accountInfo)
            .append("calcCondition", calcCondition)
            .append("newOrderSpecs", newOrderSpecs)
            .toString();
    }

}
@
