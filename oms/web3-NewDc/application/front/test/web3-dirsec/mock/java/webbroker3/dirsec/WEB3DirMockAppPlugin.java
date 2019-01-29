head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3DirMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAPMngForcedStartService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImplForMock;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImplForMock;

public class WEB3DirMockAppPlugin extends Plugin {

	public WEB3DirMockAppPlugin() {
		super();
		// TODO Auto-generated constructor stub
	}
    public static void plug()
    throws Exception
	{
	    plug(WEB3DirMockAppPlugin.class);
	}
	
	public static void onPlug()
	    throws Exception
	{
		Services.overrideService(WEB3AdminDirSecFrontOrderCommonService.class,new WEB3AdminDirSecFrontOrderCommonServiceImplForMock());
        
        //管理者下り処理強制起動サービスImplForMock
        Services.overrideService(WEB3AdminDirSecAPMngForcedStartService.class,new WEB3AdminDirSecAPMngForcedStartServiceImplForMock());
	}
}
@
