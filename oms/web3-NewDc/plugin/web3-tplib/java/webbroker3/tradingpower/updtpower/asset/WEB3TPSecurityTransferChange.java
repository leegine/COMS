head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityTransferChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 振替変動(WEB3TPSecurityTransferChange.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (振替変動)
 */
public class WEB3TPSecurityTransferChange
    extends WEB3TPSecurityChange
{

    /**
     * @@roseuid 41087D92012C
     */
    public WEB3TPSecurityTransferChange()
    {

    }

    /**
     * (create振替変動)
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityTransferChange
     * @@roseuid 40D7D99A01F1
     */
    public static WEB3TPSecurityTransferChange create()
    {
        return new WEB3TPSecurityTransferChange();
    }

    /**
     * (calc変動反映日)<BR>
     * 変動反映日を計算する。<BR>
     * <BR>
     * 変動反映開始日=営業日(T+0)<BR>
     * 変動反映終了日=営業日(T+5)
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 40B5555F0019
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
