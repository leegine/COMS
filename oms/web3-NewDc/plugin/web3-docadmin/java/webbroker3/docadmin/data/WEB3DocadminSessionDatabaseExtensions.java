head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocadminSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3DocadminSessionDatabaseExtensions extends Plugin {

  private WEB3DocadminSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3DocadminSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.docadmin.data.DocForceLogoutRunStatusPK.class );
    regClass(  webbroker3.docadmin.data.DocForceLogoutRunStatusParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "doc_force_logout_run_status",
      webbroker3.docadmin.data.DocForceLogoutRunStatusPK.class,
      webbroker3.docadmin.data.DocForceLogoutRunStatusParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.docadmin.data.DocForceLogoutRunStatusRow.class,
        webbroker3.docadmin.data.DocForceLogoutRunStatusDao.FACTORY );
  }

}
@
