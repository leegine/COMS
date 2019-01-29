head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.47.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3SockPoolCtrlResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@(WEB3SockPoolCtrlResponse.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/06 劉(FLJ) 新規作成
 */
package webbroker3.sockpoolctrl.message;

import webbroker3.common.message.*;

public class WEB3SockPoolCtrlResponse
    extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "sockpool_ctrl";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131106L;

    public WEB3SockPoolCtrlResponse()
    {

    }

    public WEB3SockPoolCtrlResponse(WEB3GenRequest l_request)
    {

        super(l_request);

    }
}
@
