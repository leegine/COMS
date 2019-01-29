head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheMonitorResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@(WEB3CacheMonitorResponse.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */
package webbroker3.cachemonitor.message;

import webbroker3.common.message.*;

public class WEB3CacheMonitorResponse
    extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "cache_monitor";

    public String dirty_table_name;

    public boolean is_dirty;

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131106L;

    public WEB3CacheMonitorResponse()
    {

    }

    public WEB3CacheMonitorResponse(WEB3GenRequest l_request)
    {

        super(l_request);

    }
}
@
