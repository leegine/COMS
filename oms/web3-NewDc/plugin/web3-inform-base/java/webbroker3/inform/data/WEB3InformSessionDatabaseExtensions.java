head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.58.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	WEB3InformSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3InformSessionDatabaseExtensions extends Plugin {

  private WEB3InformSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3InformSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.inform.data.VariousInformPK.class );
    regClass(  webbroker3.inform.data.VariousInformParams.class );
    regClass(  webbroker3.inform.data.InformCtrlRequestNumberPK.class );
    regClass(  webbroker3.inform.data.InformCtrlRequestNumberParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "various_inform",
      webbroker3.inform.data.VariousInformPK.class,
      webbroker3.inform.data.VariousInformParams.class,
      null,
      null );
    regDbExtension( "session", "inform_ctrl_request_number",
      webbroker3.inform.data.InformCtrlRequestNumberPK.class,
      webbroker3.inform.data.InformCtrlRequestNumberParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.inform.data.VariousInformRow.class,
        webbroker3.inform.data.VariousInformDao.FACTORY );
    regDao(
        webbroker3.inform.data.InformCtrlRequestNumberRow.class,
        webbroker3.inform.data.InformCtrlRequestNumberDao.FACTORY );
  }

}
@
