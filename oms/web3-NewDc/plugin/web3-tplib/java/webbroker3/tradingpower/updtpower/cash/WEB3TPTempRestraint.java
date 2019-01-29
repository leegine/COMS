head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTempRestraint.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 仮拘束金(WEB3TPTempRestraint.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/05 孟亞南(中訊) 新規作成 モデルNo.219、No.220
*/
package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (仮拘束金)<BR>
 * 仮拘束金<BR>
 * @@author 孟亞南(中訊)
 * @@version 1.0
 */
public class WEB3TPTempRestraint
    extends WEB3TPRestraintReflector
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTempRestraint.class);

    /**
     * (トランザクション発生日)<BR>
     */
    private Date transactionDate;

    /**
     * (受渡日)<BR>
     */
    private Date deliveryDate;

    /**
     * (拘束金種別)<BR>
     */
    private String restraintDiv;

    /**
     * (デフォルトコンストラクタ)<BR>
     */
    public WEB3TPTempRestraint()
    {

    }

    /**
     * (calc変動反映日)<BR>
     * <BR>
     * 変動反映開始日、変動反映終了日をセットする。<BR>
     * <BR>
     * １）変動反映開始日をセットする。<BR>
     * <BR>
     * 　@　@[a.引数.受渡日 < T+0 の場合]<BR>
     * 　@　@　@－this.set変動反映開始日(:Date = T+0)<BR>
     * <BR>
     * 　@　@[b.引数.受渡日 > T+5 の場合]<BR>
     * 　@　@　@－this.set変動反映開始日(:Date = T+5)<BR>
     * <BR>
     * 　@　@[c.その他の場合]<BR>
     * 　@　@　@－this.set変動反映開始日(:Date = 引数.受渡日)<BR>
     * <BR>
     * ２）変動反映終了日をセットする。<BR>
     * <BR>
     * 　@　@－this.set変動反映終了日(:Date = T+5)<BR>
     * <BR>
     * ※T+0 = this.get余力計算条件().get営業日(:int = 0)<BR>
     * ※T+5 = this.get余力計算条件().get営業日(:int = 5)<BR>
     * <BR>
     * @@param l_datDeliveryDate - (受渡日)
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date)";
        log.entering(STR_METHOD_NAME);

        //T+0 = this.get余力計算条件().get営業日(:int = 0)
        Date l_datBizDateT0 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        //T+5 = this.get余力計算条件().get営業日(:int = 5)
        Date l_datBizDateT5 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);

        //変動反映開始日
        //引数.受渡日 < T+0 の場合
        if (WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datBizDateT0) < 0)
        {
            //this.set変動反映開始日(:Date = T+0)
            this.setReflectStartDay(l_datBizDateT0);
        }
        //引数.受渡日 > T+5 の場合
        else if (WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datBizDateT5) > 0)
        {
            //this.set変動反映開始日(:Date = T+5)
            this.setReflectStartDay(l_datBizDateT5);
        }
        //その他の場合
        else
        {
            //this.set変動反映開始日(:Date = 引数.受渡日)
            this.setReflectStartDay(l_datDeliveryDate);
        }

        //変動反映終了日
        //this.set変動反映終了日(:Date = T+5)
        this.setReflectEndDay(l_datBizDateT5);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create仮拘束金)<BR>
     * (static)(create仮拘束金)<BR>
     * <BR>
     * 仮拘束金を作成し、返却する。<BR>
     * <BR>
     * 1)仮拘束金インスタンスを生成する。<BR>
     * 　@-デフォルトコンストラクタをコール<BR>
     * <BR>
     * 2)生成した仮拘束金インスタンスの属性に値をセット<BR>
     * <BR>
     * 　@－this.set余力計算条件(:余力計算条件 = 引数.余力計算条件)<BR>
     * 　@－this.set拘束金(:double = 引数.拘束金)<BR>
     * 　@－this.setトランザクション発生日(Date = 引数.トランザクション発生日)<BR>
     * 　@－this.calc変動反映日(:Date = 引数.受渡日)<BR>
     * 　@－this.set拘束金種別(String = 引数.拘束金種別)<BR>
     * <BR>
     * 3)仮拘束金インスタンスを返却する。<BR>
     * <BR>
     * @@param l_calcCondition - (余力計算条件)<BR>
     * @@param l_dbRestraint - (拘束金)<BR>
     * @@param l_datTransactionDate - (トランザクション発生日)<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * @@param l_strRestraintDiv - (拘束金種別)<BR>
     * @@return WEB3TPTempRestraint
     */
    public static WEB3TPTempRestraint createTempRestraint(
        WEB3TPCalcCondition l_calcCondition,
        double l_dbRestraint,
        Date l_datTransactionDate,
        Date l_datDeliveryDate,
        String l_strRestraintDiv)
    {
        final String STR_METHOD_NAME = "createTempRestraint(" +
            "WEB3TPCalcCondition, double, Date, Date, String)";
        log.entering(STR_METHOD_NAME);

        //仮拘束金インスタンスを生成する。
        WEB3TPTempRestraint l_temp = new WEB3TPTempRestraint();

        //this.set余力計算条件(:余力計算条件 = 引数.余力計算条件)
        l_temp.setCalcCondition(l_calcCondition);
        //this.set拘束金(:double = 引数.拘束金)
        l_temp.setAmount(l_dbRestraint);
        //this.setトランザクション発生日(Date = 引数.トランザクション発生日)
        l_temp.setTransactionDate(l_datTransactionDate);
        //this.calc変動反映日(:Date = 引数.受渡日)
        l_temp.calcReflectDay(l_datDeliveryDate);
        //this.set拘束金種別(String = 引数.拘束金種別)
        l_temp.setRestraintDiv(l_strRestraintDiv);

        //仮拘束金インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_temp;
    }

    /**
     * (getトランザクション発生日)<BR>
     * <BR>
     * this.トランザクション発生日を返却する。<BR>
     * @@return Date
     */
    public Date getTransactionDate()
    {
        return this.transactionDate;
    }

    /**
     * (setトランザクション発生日)<BR>
     * <BR>
     * 引数.トランザクション発生日を、this.トランザクション発生日にセットする。<BR>
     * @@param l_datTransactionDate - (トランザクション発生日)<BR>
     */
    public void setTransactionDate(Date l_datTransactionDate)
    {
        this.transactionDate = l_datTransactionDate;
    }

    /**
     * (get受渡日)<BR>
     * <BR>
     * this.受渡日を返却する。<BR>
     * @@return Date
     */
    public Date getDeliveryDate()
    {
        return this.deliveryDate;
    }

    /**
     * (set受渡日)<BR>
     * <BR>
     * 引数.受渡日を、this.受渡日にセットする。<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        this.deliveryDate = l_datDeliveryDate;
    }

    /**
     * (get拘束金種別)<BR>
     * <BR>
     * this.拘束金種別を返却する。<BR>
     * @@return String
     */
    public String getRestraintDiv()
    {
        return this.restraintDiv;
    }

    /**
     * (set拘束金種別)<BR>
     * <BR>
     * 引数.拘束金種別を、this.拘束金種別にセットする。<BR>
     * <BR>
     * @@param l_strRestraintDiv - (拘束金種別)<BR>
     */
    public void setRestraintDiv(String l_strRestraintDiv)
    {
        this.restraintDiv = l_strRestraintDiv;
    }
}
@
