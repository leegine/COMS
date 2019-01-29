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
filename	WEB3EquityReceiveCancelSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式取消通知内容(WEB3EquityReceiveCancelSepc.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 李綱 (中訊) 新規作成
Revesion History : 2004/12/29 岡村和明 (SRA) パラメータ名を修正
Revesion History : 2005/01/05 岡村和明 (SRA) JavaDoc修正
                   2006/11/28 張騰宇(中訊) モデル 1065
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;

/**
 * （株式取消通知内容）。<BR>
 * <BR>
 * 株式取消通知内容。<BR>
 * 株式訂正取消通知サービスにて使用する。
 * @@version 1.0
 */
public class WEB3EquityReceiveCancelSpec
{

    /**
     * (訂正後執行条件)<BR>
     * <BR>
     * 【株式訂正取消通知キューテーブル】訂正後執行条件を、<BR>
     * xTradeのEqTypeExecutionConditionTypeに変換した値<BR>
     */
    private EqTypeExecutionConditionType changeAfterExecCond;

    /**
     * (訂正後値段条件)。<BR>
     * 訂正後の値段条件。（WEBⅢのコード体系）<BR>
     */
    private String changeAfterPriceConditionType;

    /**
     * (受渡代金)。<BR>
     * 受渡代金。<BR>
     */
    private double estimatedPrice;

    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     */
    private double limitPrice;

    /**
     * (株式取消通知内容)<BR>
     * <BR>
     * コンストラクタ<BR>
     * @@return 株式（web3-equity）.注文・約定エンティティ.株式取消通知内容)
     * @@roseuid 404583180290
     */
    public WEB3EquityReceiveCancelSpec()
    {

    }

    /**
     * (set訂正後執行条件)<BR>
     * @@param l_executionConditionType 訂正後執行条件
     * @@roseuid 4045830C0000
     */
    public void setChangeAfterExecCond(EqTypeExecutionConditionType l_executionConditionType)
    {
        this.changeAfterExecCond = l_executionConditionType;
    }

    /**
     * (get訂正後執行条件)<BR>
     * @@return EqTypeExecutionConditionType
     * @@roseuid 4045830C0002
     */
    public EqTypeExecutionConditionType getChangeAfterExecCond()
    {
        return this.changeAfterExecCond;
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
     * (set受渡代金)<BR>
     * 受渡代金をセットする。<BR>
     * @@param l_dblEstimatedPrice - (受渡代金)<BR>
     * 受渡代金
     */
    public void setEstimatedPrice(double l_dblEstimatedPrice)
    {
        this.estimatedPrice = l_dblEstimatedPrice;
    }
    
    /**
     * (get受渡代金)<BR>
     * 受渡代金を取得する。<BR>
     * @@return 受渡代金
     */
    public double getEstimatedPrice()
    {
        return this.estimatedPrice;
    }

    /**
     * (get注文単価)<BR>
     * 注文単価を取得する。<BR>
     * @@return double
     */
    public double getLimitPrice()
    {
        return this.limitPrice;
    }

    /**
     * (set注文単価)<BR>
     * 注文単価をセットする。<BR>
     * @@param l_dblLmitPrice - (注文単価)<BR>
     */
    public void setLimitPrice(double l_dblLmitPrice)
    {
        this.limitPrice = l_dblLmitPrice;
    }
}
@
