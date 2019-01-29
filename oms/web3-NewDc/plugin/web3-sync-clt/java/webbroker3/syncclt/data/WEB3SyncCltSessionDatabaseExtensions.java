head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.26.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d7db4ef1a89;
filename	WEB3SyncCltSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.syncclt.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3SyncCltSessionDatabaseExtensions extends Plugin {

  private WEB3SyncCltSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3SyncCltSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.syncclt.data.SyncProcStatusPK.class );
    regClass(  webbroker3.syncclt.data.SyncProcStatusParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "sync_proc_status",
      webbroker3.syncclt.data.SyncProcStatusPK.class,
      webbroker3.syncclt.data.SyncProcStatusParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.syncclt.data.SyncProcStatusRow.class,
        webbroker3.syncclt.data.SyncProcStatusDao.FACTORY );
  }

}
@
