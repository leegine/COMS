head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.41.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvregiMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.service.delegate.stdimpls;

import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadUnitService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;

public class WEB3SrvregiMockAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvregiMockAppPlugin.class);

    public WEB3SrvregiMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public static void plug()
    throws Exception
    {
        plug(WEB3SrvregiMockAppPlugin.class);
    }
    
    public static void onPlug()
        throws Exception
    {
      final String STR_METHOD_NAME = "plugSrvregi()";
      log.entering(STR_METHOD_NAME);
      
      //WEB3SrvRegiRegistServiceImplForMock
      Services.overrideService(WEB3SrvRegiRegistService.class, new WEB3SrvRegiRegistServiceImplForMock());
      // WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock.java
      Services.overrideService(WEB3AdminSrvRegiAccountDataUploadUnitService.class, new WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock());
      log.exiting(STR_METHOD_NAME);
    }
}
@
