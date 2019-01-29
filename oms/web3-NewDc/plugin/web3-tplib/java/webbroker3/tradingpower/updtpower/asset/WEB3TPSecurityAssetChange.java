head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityAssetChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 既存保有変動(WEB3TPSecurityAssetChange.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (既存保有変動)
 */
public class WEB3TPSecurityAssetChange
    extends WEB3TPSecurityChange
{

    /**
     * (分割新株式区分)<BR>
     * 受渡日別保有資産テーブル.分割新株式区分="1"の場合、trueをセット。<BR>
     * 以外の場合、false分割新株式区分 <BR>
     */
    private boolean splitNewStock = false;

    /**
     * (原約定日)<BR>
     */
    private Date OriginalExecDate;
    
    /**
     * @@roseuid 41087D8103AC
     */
    public WEB3TPSecurityAssetChange()
    {

    }

    /**
     * (create既存保有変動)<BR>
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityAssetChange
     * @@roseuid 40D8160C00B9
     */
    public static WEB3TPSecurityAssetChange create()
    {
        return new WEB3TPSecurityAssetChange();
    }

    /**
     * (calc変動反映日)<BR>
     * <BR>
     * 変動開始日、変動終了日は<BR>
     * 既存保有変動.calc変動反映日(受渡日別保有資産テーブル.受渡日)で設定する。<BR>
     * <BR>
     * 変動反映開始日：<BR>
     * ①@[分割新株区分 == true かつ 預り区分 == "1:代用"の場合]<BR>
     * 変動反映開始日 = 原約定日<BR>
     * <BR>
     * ②[上記以外]<BR>
     * 　@a)[受渡日別保有資産テーブル.受渡日 > 営業日(T+5)の場合]<BR>
     * 　@　@変動反映開始日 = 営業日(T+5)<BR>
     * 　@b)[a)以外]<BR>
     * 　@　@変動反映開始日 =　@受渡日別保有資産テーブル.受渡日<BR>
     * <BR>
     * 変動反映終了日：<BR>
     * 変動反映終了日 = 営業日(T+5)<BR>
     * <BR>
     * @@param l_datDeliveryDate - (受渡日)
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        //[分割新株区分 == true かつ 預り区分 == "1:代用"の場合]
        if(this.isSplitNewStock() == true
            && WEB3TPDepositTypeDef.SUBSTITUTE.equals(this.getDepositType()) == true)
        {
            //変動反映開始日 = 原約定日
            setReflectStartDay(this.getOriginalExecDate());
        }
        else if(l_datDeliveryDate.getTime() > getCalcCondition().getBizDate(
            WEB3TPSpecifiedPointDef.T_5).getTime())
        {
            setReflectStartDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));
        }
        else
        {
            setReflectStartDay(l_datDeliveryDate);
        }

        setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));

        setDeliveryDate(l_datDeliveryDate);    
    }

    /**
     * (is分割新株式区分)<BR>
     * <BR>
     * this.分割新株式区分を返却する。<BR>
     * <BR>
     */
    public boolean isSplitNewStock()
    {
        return splitNewStock;
    }

    /**
     * (set分割新株式区分)<BR>
     * <BR>
     * 引数.分割新株式区分をthis.分割新株式区分にセットする。<BR>
     * <BR>
     * @@param l_splitNewStock - (分割新株式区分)
     */
    public void setSplitNewStock(boolean l_splitNewStock)
    {
        splitNewStock = l_splitNewStock;
    }

    /**
     * (get原約定日)<BR>
     * <BR>
     * this.原約定日を返却する。<BR>
     * <BR>
     */
    public Date getOriginalExecDate()
    {
        return OriginalExecDate;
    }

    /**
     * (set原約定日)<BR>
     * <BR>
     * 引数.原約定日をthis.原約定日にセットする。<BR>
     * <BR>
     * @@param l_OriginalExecDate - (原約定日)
     */
    public void setOriginalExecDate(Date l_OriginalExecDate)
    {
        OriginalExecDate = l_OriginalExecDate;
    }

    
    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("splitNewStock", splitNewStock)
            .toString();
    }
}
@
