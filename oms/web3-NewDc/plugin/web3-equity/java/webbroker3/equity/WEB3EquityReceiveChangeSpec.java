head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityReceiveChangeSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正通知内容(WEB3EquityReceiveChangeSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/19 周玲玲 (中訊) 新規作成
Revesion History : 2004/12/29 岡村和明 (SRA) パラメータ名を修正
Revesion History : 2005/01/05 岡村和明 (SRA) JavaDoc修正
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;

/**
 * （株式訂正通知内容）。<BR>
 * <BR>
 * 株式訂正通知内容。<BR>
 * 株式訂正取消通知サービスにて使用する。
 * @@version 1.0
 */
public class WEB3EquityReceiveChangeSpec
{

    /**
     * (訂正後執行条件)<BR>
     * 【株式訂正取消通知キューテーブル】訂正後執行条件を、<BR>
     * xTradeのEqTypeExecutionConditionTypeに変換した値<BR>
     */
    private EqTypeExecutionConditionType changeAfterExecCondType;

    /**
     * (注文単価)<BR>
     */
    private double limitPrice;

    /**
     * (概算受渡代金)<BR>
     */
    private double estimateDeliveryAmount;

    /**
     * (訂正後値段条件)。<BR>
     * 訂正後の値段条件。（WEBⅢのコード体系）<BR>
     */
    private String changeAfterPriceConditionType;

    /**
     * (訂正後注文Rev,)<BR>
     * 訂正後の注文Rev.。<BR>
     */
    private String changeAfterOrderRev;
    
    /**
     * (株式訂正通知内容)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.equity.WEB3EquityReceiveChangeSpec<BR>
     * @@roseuid 40457CE002EE<BR>
     */
    public WEB3EquityReceiveChangeSpec()
    {

    }

    /**
     * (set訂正後執行条件)<BR>
     * @@param l_changeAfterExecCondType (訂正後執行条件)<BR>
     * 訂正後執行条件<BR>
     * @@roseuid 40457A8E0158<BR>
     */
    public void setChangeAfterExecCondType(EqTypeExecutionConditionType l_changeAfterExecCondType)
    {
        this.changeAfterExecCondType = l_changeAfterExecCondType;
    }

    /**
     * (get訂正後執行条件)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType<BR>
     * @@roseuid 40457A8E0168<BR>
     */
    public EqTypeExecutionConditionType getChangeAfterExecCondType()
    {
        return this.changeAfterExecCondType;
    }

    /**
     * (set概算受渡代金)<BR>
     * 概算受渡代金をセットする。<BR>
     * @@param l_dblEstimatedPrice (概算金額)<BR>
     * @@roseuid 40457AC403D9<BR>
     */
    public void setEstimateDeliveryAmount(double l_dblEstimatedPrice)
    {
        this.estimateDeliveryAmount = l_dblEstimatedPrice;
    }

    /**
     * (get概算受渡代金)<BR>
     * 概算受渡代金を取得する。<BR>
     * @@return double<BR>
     * @@roseuid 40457AC50000<BR>
     */
    public double getEstimateDeliveryAmount()
    {
        return this.estimateDeliveryAmount;
    }

    /**
     * (set注文単価)<BR>
     * 注文単価をセットする。<BR>
     * @@param l_dblLimitPrice (注文単価)<BR>
     * @@roseuid 40457AC50001<BR>
     */
    public void setLimitPrice(double l_dblLimitPrice)
    {
        this.limitPrice = l_dblLimitPrice;
    }

    /**
     * (get注文単価)<BR>
     * 注文単価を取得する。<BR>
     * @@return String<BR>
     * @@roseuid 40457AC50010<BR>
     */
    public double getLimitPrice()
    {
        return this.limitPrice;
    }

    /**
     * (set訂正後値段条件)<BR>
     * 訂正後の値段条件をセットする。<BR>
     * @@param l_strChangeAfterPriceConditionType 訂正後値段条件
     */
    public void setChangeAfterPriceConditionType(String l_strChangeAfterPriceConditionType) 
    {
        this.changeAfterPriceConditionType = l_strChangeAfterPriceConditionType;
    }
    
    /**
     * (get訂正後値段条件)<BR>
     * 訂正後値段条件を取得する。<BR>
     * @@return String
     */
    public String getChangeAfterPriceConditionType() 
    {
        return this.changeAfterPriceConditionType;
    }
    
    /**
     * (set訂正後注文Rev)<BR>
     * 訂正後の注文Revをセットする。
     * @@param l_strChangeAfterOrderRev - (訂正後注文Rev)<BR>
     * 訂正後注文Rev
     */
    public void setChangeAfterOrderRev(String l_strChangeAfterOrderRev)
    {
        this.changeAfterOrderRev = l_strChangeAfterOrderRev;
    }
    
    /**
     * (get訂正後注文Rev)<BR>
     * 訂正後注文Revを取得する。
     * @@return String
     */
    public String getChangeAfterOrderRev()
    {
        return this.changeAfterOrderRev;
    }
}
@
