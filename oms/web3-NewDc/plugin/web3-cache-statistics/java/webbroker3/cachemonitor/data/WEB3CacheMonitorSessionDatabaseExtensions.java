head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheMonitorSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.cachemonitor.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3CacheMonitorSessionDatabaseExtensions extends Plugin {

  private WEB3CacheMonitorSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3CacheMonitorSessionDatabaseExtensions.class );
  }

  public static void onPlug() throws Exception {

    // dependencies
    com.fitechlabs.xtrade.kernel.boot.KernelPlugin.plug();

    // extensions
    regClasses();
    regDbExtensions();
    regDataObjectClasses();
  }

  private static void regClasses() throws Exception {
    regClass(  webbroker3.cachemonitor.data.CacheMonitorOrdAccStatusPK.class );
    regClass(  webbroker3.cachemonitor.data.CacheMonitorOrdAccStatusParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "cache_monitor_ord_acc_status",
      webbroker3.cachemonitor.data.CacheMonitorOrdAccStatusPK.class,
      webbroker3.cachemonitor.data.CacheMonitorOrdAccStatusParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.cachemonitor.data.CacheMonitorOrdAccStatusRow.class,
        webbroker3.cachemonitor.data.CacheMonitorOrdAccStatusDao.FACTORY );
  }

}
@
