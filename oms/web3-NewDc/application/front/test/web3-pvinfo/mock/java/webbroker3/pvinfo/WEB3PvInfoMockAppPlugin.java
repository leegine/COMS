head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.05.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3PvInfoMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;


public class WEB3PvInfoMockAppPlugin extends Plugin
{

    public WEB3PvInfoMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void plug()
    throws Exception
    {
        plug(WEB3PvInfoMockAppPlugin.class);
    }

    public static void onPlug()
        throws Exception
    {
        Services.overrideService(WEB3PvInfoDataManager.class, new WEB3PvInfoDataManagerImplForMock());
    }
}
@
