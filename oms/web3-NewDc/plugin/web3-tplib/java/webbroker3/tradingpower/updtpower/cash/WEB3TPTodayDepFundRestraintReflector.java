head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTodayDepFundRestraintReflector.java;


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
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (即日入金銘柄拘束金)<BR>
 * 即日入金銘柄拘束金を表現する。
 */
public class WEB3TPTodayDepFundRestraintReflector
    extends WEB3TPRestraintReflector
{

    /** ログ　@ユーティリティ　@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPTodayDepFundRestraintReflector.class);

    /**
     * (トランザクション発生日)<BR>
     */
    private Date finTransactionDate;


    /**
     * @@roseuid 410491590165
     */
    public WEB3TPTodayDepFundRestraintReflector()
    {

    }

    /**
     * (create即日入金銘柄拘束金)<BR>
     * 即日入金銘柄拘束金を生成し、返却する。<BR>
     * <BR>
     * １）インスタンス生成<BR>
     * <BR>
     * ２）値を設定<BR>
     * 	拘束金      ＝  受渡代金<BR>
     * 	変動反映開始日、変動反映終了日設定：calc変動反映日（受渡日）<BR>
     * <BR>
     * ３）インスタンスを返却<BR>
     * @@param l_dblDeliveryAmount - (受渡金額)
     * @@param l_datDeliveryDate - (受渡日)
     * @@return WEB3TPTodayDepFundRestraintReflector
     * @@roseuid 40D829C70186
     */
    public static WEB3TPTodayDepFundRestraintReflector create(WEB3TPCalcCondition
        l_calcCondition, double l_dblDeliveryAmount, Date l_datFinTransactionDate, Date l_datDeliveryDate)
    {
        WEB3TPTodayDepFundRestraintReflector l_instance = new
            WEB3TPTodayDepFundRestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_dblDeliveryAmount);
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
     * [a. トランザクション発生日が当日より先（翌日注文）の場合]
     * 　@変動反映開始日＝トランザクション発生日(T+1)<BR>
     * [a. トランザクション発生日が当日より先（翌日注文）の場合]
     * 　@変動反映開始日＝T+0<BR>
     * <BR>
     * 変動反映終了日＝引数.受渡日-1<BR>
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 40D66F180172
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        
        Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
                       
        //T+0以前のデータはロードされないはず
        if (WEB3DateUtility.compareToDay(l_datT0, l_datDeliveryDate) >= 0)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //反映開始日
        
        //翌日(以降)注文の場合
        if(WEB3DateUtility.compareToDay(l_datT0, finTransactionDate) < 0)
        {
            setReflectStartDay(finTransactionDate);
        }
        //それ以外
        else
        {
            setReflectStartDay(l_datT0);
        }
        
        //反映終了日
        setReflectEndDay(getCalcCondition().rollBizDate(l_datDeliveryDate, -1));

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
