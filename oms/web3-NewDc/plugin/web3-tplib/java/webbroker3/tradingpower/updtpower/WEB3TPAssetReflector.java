head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 資産変動クラス(WEB3TPAssetReflector)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/07/30 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.tradingpower.updtpower;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;

/**
 * (資産変動) <BR>
 * 資産変動を表現する。
 */
public abstract class WEB3TPAssetReflector
{

    /**
     * 変動反映開始日 <BR>
     */
    private Date reflectStartDay;

    /**
     * 変動反映終了日 <BR>
     */
    private Date reflectEndDay;

    /**
     * 計算条件
     */
    private WEB3TPCalcCondition calcCondition;

    /**
     * @@roseuid 4136AE000065
     */
    public WEB3TPAssetReflector()
    {

    }

    /**
     * (calc変動反映日)
     * 変動反映日の計算の抽象定義<BR>
     * 変動反映開始日、変動反映終了日に計算結果を設定する<BR>
     * @@param l_datDeliveryDate 受渡日
     * @@roseuid 40D92B4A02A8
     */
    public abstract void calcReflectDay(Date l_datDeliveryDate);

    /**
     * (get変動反映開始日)<BR>
     * <BR>
     * 変動反映開始日を取得する。<BR>
     * @@return java.util.java.util.Date
     * @@roseuid 40EE6CFA0190
     */
    public Date getReflectStartDay()
    {
        return this.reflectStartDay;
    }

    /**
     * (set変動反映開始日)<BR>
     * <BR>
     * 変動反映開始日をセットする。<BR>
     * @@param l_datReflectStartDay - 変動反映開始日
     * @@roseuid 40EE6D1301ED
     */
    public void setReflectStartDay(Date l_datReflectStartDay)
    {
        this.reflectStartDay = l_datReflectStartDay;
    }

    /**
     * (get変動反映終了日)<BR>
     * <BR>
     * 変動反映終了日を取得する。<BR>
     * @@return java.util.java.util.Date
     * @@roseuid 40EE6D0B01AF
     */
    public Date getReflectEndDay()
    {
        return this.reflectEndDay;
    }

    /**
     * (set変動反映終了日)<BR>
     * <BR>
     * 変動反映終了日をセットする。<BR>
     * @@param l_datReflecEndDay - 変動反映開終了日
     * @@roseuid 40EE6D180047
     */
    public void setReflectEndDay(Date l_datReflectEndDay)
    {
        this.reflectEndDay = l_datReflectEndDay;
    }

    /**
     * (is変動反映期間中)<BR>
     * 変動期間内を判断する。<BR>
     * 変動反映開始日<= l_datBizDate <= 変動反映終了日の場合、trueを返す。<BR>
     * その以外場合、falseを返す。<BR>
     * @@param l_datBizDate- 営業日<BR>
     * @@return boolean
     * @@roseuid 40D92F7E01CD
     */
    public boolean isDuringReflectTime(Date l_datBizDate)
    {
        final String STR_METHOD_NAME =
            "isDuringReflectTime(Date l_datBizDate)";

        if (l_datBizDate == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        long l_lngBizDate = l_datBizDate.getTime();
        if ( (getReflectStartDay().getTime() <= l_lngBizDate)
            && (l_lngBizDate <= getReflectEndDay().getTime()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (get計算条件)
     * 計算条件を取得する。<BR>
     * @@return WEB3TPCalcCondition
     * @@roseuid 4136AC6403CB
     */
    public WEB3TPCalcCondition getCalcCondition()
    {
        return calcCondition;
    }

    /**
     * (set計算条件)
     * 計算条件を設定する。
     * @@param l_calcCondition - 余力計算条件
     * @@roseuid 4136AC650022
     */
    public void setCalcCondition(WEB3TPCalcCondition l_calcCondition)
    {
        calcCondition = l_calcCondition;
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        String l_strYYYYMMDDFormat = "yyyy/MM/dd";
        String l_datStartDay = WEB3DateUtility.formatDate(getReflectStartDay(), l_strYYYYMMDDFormat);
        String l_datEndDay = WEB3DateUtility.formatDate(getReflectEndDay(), l_strYYYYMMDDFormat);

        return ToStringUtils
            .newToStringBuilder(this)
            .append("reflectStartDay", l_datStartDay)
            .append("reflectEndDay", l_datEndDay)
            .toString();
    }

}
@
