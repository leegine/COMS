head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算通知レスポンス(WEB3TPReCalcNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * (余力計算通知レスポンス)
 */
public class WEB3TPReCalcNotifyResponse extends WEB3BackResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503241100L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tp_recalc_notify";

    /**
     * @@roseuid 42354139023C
     */
    public WEB3TPReCalcNotifyResponse()
    {

    }

    public WEB3TPReCalcNotifyResponse(WEB3TPReCalcNotifyRequest l_request)
    {
        super(l_request);
    }
}
@
