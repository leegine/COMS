head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDayInterestAdjustmentRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 堀野 和美(FLJ) 新規作成
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (日歩拘束金)<BR>
 * 日歩拘束金を表現する。<BR>
 */
public class WEB3TPDayInterestAdjustmentRestraintReflector
    extends WEB3TPRestraintReflector
{

    /**
     * (受渡日あたりの返済注文件数)<BR>
     */
    private int transactionCount;

    /**
     * (日歩調整額)<BR>
     */
    private double adjustment;
    
    /** ログ　@ユーティリティ　@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPDayInterestAdjustmentRestraintReflector.class);

    /**
     * @@roseuid 4104BDB00068
     */
    public WEB3TPDayInterestAdjustmentRestraintReflector()
    {

    }

    /**
     * (create日歩拘束金)<BR>
     * 日歩拘束金を生成し、返却する。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * <BR>
     * ２）　@値を設定<BR>
     * 	注文件数			＝  注文件数<BR>
     * 	日歩調整額        ＝　@日歩調整額<BR>
     * 	拘束金				＝　@calc日歩拘束金(受渡日)<BR>
     * 	変動反映開始日、変動反映終了日設定：calc変動反映日(受渡日)<BR>
     * <BR>
     * ３）　@インスタンスを返却<BR>
     * <BR>
     * @@param l_intTransactionCount - (注文件数)
     * @@param l_dblAdjustment - (日歩調整額)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return WEB3TPDayInterestRestraintReflector
     * @@roseuid 40D81603031C
     */
    public static WEB3TPDayInterestAdjustmentRestraintReflector create(
        WEB3TPCalcCondition l_calcCondition, int l_intTransactionCount,
        double l_dblAdjustment, Date l_datDeliveryDate)
    {
        WEB3TPDayInterestAdjustmentRestraintReflector l_instance = new
            WEB3TPDayInterestAdjustmentRestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setTransactionCount(l_intTransactionCount);
        l_instance.setAdjustment(l_dblAdjustment);
        l_instance.setAmount(l_instance.calcDayInterestRestraint(l_datDeliveryDate));
        l_instance.calcReflectDay(l_datDeliveryDate);
        return l_instance;

    }

    /**
     * (get注文件数)<BR>
     * 返済注文件数を返す。<BR>
     * @@return int
     * @@roseuid 40E00D990002
     */
    public int getTransactionCount()
    {
        return transactionCount;
    }

    /**
     * (set注文件数)<BR>
     * 引数を返済注文件数にセットする。<BR>
     * @@param l_intTransactionCount - (注文件数)
     * @@roseuid 40E00DA10282
     */
    public void setTransactionCount(int l_intTransactionCount)
    {
        transactionCount = l_intTransactionCount;

    }

    /**
     * (get日歩調整額)<BR>
     * 日歩調整額を返す。<BR>
     * @@return double
     * @@roseuid 40C5A6CC0128
     */
    public double getAdjustment()
    {
        return adjustment;
    }

    /**
     * (set日歩調整額)<BR>
     * 引数を日歩調整額にセットする。<BR>
     * @@param l_dblAdjustment - 日歩調整額
     * @@roseuid 40C5A6D40195
     */
    public void setAdjustment(double l_dblAdjustment)
    {
        adjustment = l_dblAdjustment;
    }

    /**
     * (calc日歩拘束金)<BR>
     * 受渡日あたりの日歩拘束金を算出する。<BR>
     * 返済注文1件に対して日歩調整額がつくので<BR>
     * 注文件数をかけあわせたものが受渡日あたりの日歩拘束金となる。<BR>
     * <BR>
     * １）　@日歩拘束金を計算する。<BR>
     * 　@日歩拘束金(n)　@= 返済注文数(n) × 日歩調整額<BR>
     * <BR>
     * ２）　@日歩拘束金(n)を返却する。<BR>
     * <BR>
     * @@param l_datDate　@(指定日)
     * @@return double
     * @@roseuid 40DA30D0004D
     */
    public double calcDayInterestRestraint(Date l_datDate)
    {
        double l_dblAmount = transactionCount * adjustment;
        return l_dblAmount;
    }

    /**
     * (calc変動反映日)<BR>
     * 変動反映開始日、変動反映終了日を以下のようにセットする。<BR>
     * <BR>
     * 変動反映開始日＝引数.受渡日<BR>
     * 変動反映終了日＝営業日[5]<BR>
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 40ECA12E01A5
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        if (l_datDeliveryDate == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        setReflectStartDay(l_datDeliveryDate);
        setReflectEndDay(getCalcCondition().getBizDate(5));

    }

    /**
     * このオブジェクトの文字列表現を返す。<BR>
     * 
     * @@return String 
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("transactionCount", this.getTransactionCount())
            .append("adjustment", this.getAdjustment())
            .appendSuper(super.toString())
            .toString();
    }
}
@
