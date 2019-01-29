head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.40.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminToMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.admintriggerorder;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.admintriggerorder.service.delegate.WEB3AdminToEquityOrderReferenceService;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToIfoOrderReferenceService;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToEquityOrderReferenceServiceImplForMock;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImplForMock;

public class WEB3AdminToMockAppPlugin extends Plugin
{
    public WEB3AdminToMockAppPlugin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static void plug()
    throws Exception
    {
        plug(WEB3AdminToMockAppPlugin.class);
    }
    public static void onPlug()
    throws Exception
    {
        //WEB3AdminToEquityOrderReferenceServiceImplForMock
        Services.overrideService(WEB3AdminToEquityOrderReferenceService.class,
                new WEB3AdminToEquityOrderReferenceServiceImplForMock());
        //WEB3AdminToIfoOrderReferenceServiceImplForMock
        Services.overrideService(WEB3AdminToIfoOrderReferenceService.class,
                new WEB3AdminToIfoOrderReferenceServiceImplForMock());
    }
}
@
