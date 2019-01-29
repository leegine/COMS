head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityDeliveryChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 出庫変動(WEB3TPSecurityDeliveryChange.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (出庫変動)
 */
public class WEB3TPSecurityDeliveryChange
    extends WEB3TPSecurityChange
{

    /**
     * @@roseuid 41087DA301D7
     */
    public WEB3TPSecurityDeliveryChange()
    {

    }

    /**
     * (create出庫変動)<BR>
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityDeliveryChange
     * @@roseuid 40D7F483027E
     */
    public static WEB3TPSecurityDeliveryChange create()
    {
        return new WEB3TPSecurityDeliveryChange();
    }

    /**
     * (calc変動反映日)<BR>
     * 変動反映日を計算する。<BR>
     * <BR>
     * 変動反映開始日=営業日(T+0)<BR>
     * 変動反映終了日=営業日(T+5)
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 40B55562022C
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        setReflectStartDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0));
        setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));

        setDeliveryDate(l_datDeliveryDate);    
    }

    /**
     * このオブジェクトの文字列表現を返す
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .toString();
    }

}
@
