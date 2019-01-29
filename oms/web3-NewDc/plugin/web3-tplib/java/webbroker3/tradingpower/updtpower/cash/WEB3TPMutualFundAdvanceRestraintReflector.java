head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMutualFundAdvanceRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPMutualFundAdvanceRestraintReflector.java
Author Name      : Daiwa Institute of Research
Revision History : 2005/10/17 齋藤 栄三(FLJ) 新規作成
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (投資信託前受拘束金)<BR>
 * 投資信託前受拘束金を表現する。
 */
public class WEB3TPMutualFundAdvanceRestraintReflector
    extends WEB3TPRestraintReflector
{

    /** ログ　@ユーティリティ　@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPMutualFundAdvanceRestraintReflector.class);

    /**
     * (トランザクション発生日)<BR>
     */
    private Date finTransactionDate;

    public WEB3TPMutualFundAdvanceRestraintReflector()
    {

    }

    /**
     * (create投資信託前受拘束金)<BR>
     * 投資信託前受拘束金を生成し、返却する。<BR>
     * <BR>
     * １）インスタンス生成<BR>
     * <BR>
     * ２）値を設定<BR>
     * 	拘束金      ＝  受渡代金<BR>
     * 	変動反映開始日、変動反映終了日設定：calc変動反映日（受渡日）<BR>
     * <BR>
     * ３）インスタンスを返却<BR>
     * @@param l_dblAmount - (受渡代金)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return WEB3TPMutualFundAdvanceRestraintReflector
     */
    public static WEB3TPMutualFundAdvanceRestraintReflector create(WEB3TPCalcCondition
        l_calcCondition, double l_dblAmount, Date l_datFinTransactionDate, Date l_datDeliveryDate)
    {
        WEB3TPMutualFundAdvanceRestraintReflector l_instance = new
            WEB3TPMutualFundAdvanceRestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_dblAmount);
        l_instance.setFinTransactionDate(l_datFinTransactionDate);
        l_instance.calcReflectDay(l_datDeliveryDate);
        return l_instance;
    }

    /**
     * (getトランザクション発生日)<BR>
     * トランザクションタイプを返す。<BR>
     * @@return Date
     */
    public Date getFinTransactionDate()
    {
        return finTransactionDate;
    }

    /**
     * (setトランザクション発生日)<BR>
     * 引数をトランザクション発生日にセットする。<BR>
     * @@param l_finTransactionDate - (トランザクション発生日)
     */
    public void setFinTransactionDate(Date l_finTransactionDate)
    {
        finTransactionDate = l_finTransactionDate;
    }


    /**
     * (calc変動反映日)<BR>
     * 変動反映開始日、変動反映終了日を以下のようにセットする。<BR>
     * <BR>
     * [a. トランザクション発生日＜営業日(T+0)の場合]<BR>
     * 　@変動反映開始日＝営業日(T+0)<BR>
     * [a. 上記以外の場合]<BR>
     * 　@変動反映開始日＝トランザクション発生日<BR>
     * <BR>
     * [a. 営業日(T+5)＜受渡日の場合]<BR>
     * 　@変動反映終了日＝営業日(T+4)<BR>
     * [a. 上記以外の場合]<BR>
     * 　@変動反映終了日＝受渡日-1<BR>
     * @@param l_datDeliveryDate - (受渡日)
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        
        Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datT4 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_4);
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
                       
        //トランザクション発生日＜営業日(T+0)の場合
        if (WEB3DateUtility.compareToDay(finTransactionDate, l_datT0) < 0)
        {
            //営業日(T+0)以降拘束
            setReflectStartDay(l_datT0);
        }
        //
        else
        {
            //トランザクション発生日以降拘束
            setReflectStartDay(finTransactionDate);
        }
        
        //受渡日＝nullの場合
        if(l_datDeliveryDate == null)
        {
            l_datDeliveryDate = l_datT5;
        }
        
        //営業日(T+5)＜受渡日の場合
        if(WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) < 0)
        {
            //営業日(T+4)まで拘束
            setReflectEndDay(l_datT4);
        }
        //それ以外
        else
        {
            //受渡日-1まで拘束
            setReflectEndDay(getCalcCondition().rollBizDate(l_datDeliveryDate, -1));
        }
    }

    /**
     * このオブジェクトの文字列表現を返す。
     * 
     * @@return String
     */
    public String toString()
    {
        String l_strYYYYMMDDFormat = "yyyy/MM/dd";

        return ToStringUtils
            .newToStringBuilder(this)
            .append("finTransactionDate", WEB3DateUtility.formatDate(this.getFinTransactionDate(), l_strYYYYMMDDFormat))
            .appendSuper(super.toString())
            .toString();
    }

}
@
