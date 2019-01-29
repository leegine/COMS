head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheMonitorRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@(WEB3CacheMonitorRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */

package webbroker3.cachemonitor.message;

import webbroker3.common.message.*;

public class WEB3CacheMonitorRequest
    extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "cache_monitor";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200604201106L;

    public WEB3CacheMonitorRequest()
    {

    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD60131
     */
    public WEB3GenResponse createResponse()
    {

        return new WEB3CacheMonitorResponse(this);

    }
}
@
