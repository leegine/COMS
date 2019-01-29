head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.12.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3PvInfoMasterDatabaseExtensions extends Plugin {

  private WEB3PvInfoMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3PvInfoMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.pvinfo.data.DisplayConditionPK.class );
    regClass(  webbroker3.pvinfo.data.DisplayConditionParams.class );
    regClass(  webbroker3.pvinfo.data.DisplayContentsPK.class );
    regClass(  webbroker3.pvinfo.data.DisplayContentsParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "display_condition",
      webbroker3.pvinfo.data.DisplayConditionPK.class,
      webbroker3.pvinfo.data.DisplayConditionParams.class,
      null,
      null );
    regDbExtension( "master", "display_contents",
      webbroker3.pvinfo.data.DisplayContentsPK.class,
      webbroker3.pvinfo.data.DisplayContentsParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.pvinfo.data.DisplayConditionRow.class,
        webbroker3.pvinfo.data.DisplayConditionDao.FACTORY );
    regDao(
        webbroker3.pvinfo.data.DisplayContentsRow.class,
        webbroker3.pvinfo.data.DisplayContentsDao.FACTORY );
  }

}
@
