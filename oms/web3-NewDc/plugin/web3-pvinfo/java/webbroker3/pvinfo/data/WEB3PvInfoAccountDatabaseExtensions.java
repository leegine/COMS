head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.12.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3PvInfoAccountDatabaseExtensions extends Plugin {

  private WEB3PvInfoAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3PvInfoAccountDatabaseExtensions.class );
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
    regClass(  webbroker3.pvinfo.data.BrowseHistoryPK.class );
    regClass(  webbroker3.pvinfo.data.BrowseHistoryParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "browse_history",
      webbroker3.pvinfo.data.BrowseHistoryPK.class,
      webbroker3.pvinfo.data.BrowseHistoryParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.pvinfo.data.BrowseHistoryRow.class,
        webbroker3.pvinfo.data.BrowseHistoryDao.FACTORY );
  }

}
@
