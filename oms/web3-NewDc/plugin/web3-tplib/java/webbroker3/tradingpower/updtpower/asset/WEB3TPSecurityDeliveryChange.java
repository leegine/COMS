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
 Copyright        : ()ๅaค ุ\[VVXeๆ๑
 File Name        : oษฯฎ(WEB3TPSecurityDeliveryChange.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 Rc@@์i (FLJ) VK์ฌ
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (oษฯฎ)
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
     * (createoษฯฎ)<BR>
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityDeliveryChange
     * @@roseuid 40D7F483027E
     */
    public static WEB3TPSecurityDeliveryChange create()
    {
        return new WEB3TPSecurityDeliveryChange();
    }

    /**
     * (calcฯฎฝf๚)<BR>
     * ฯฎฝf๚๐vZท้B<BR>
     * <BR>
     * ฯฎฝfJn๚=cฦ๚(T+0)<BR>
     * ฯฎฝfIน๚=cฦ๚(T+5)
     * @@param l_datDeliveryDate - (๓n๚)
     * @@roseuid 40B55562022C
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        setReflectStartDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0));
        setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));

        setDeliveryDate(l_datDeliveryDate);    
    }

    /**
     * ฑฬIuWFNgฬถ๑\ป๐ิท
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
