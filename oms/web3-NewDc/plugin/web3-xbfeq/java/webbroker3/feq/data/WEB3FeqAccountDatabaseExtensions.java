head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3FeqAccountDatabaseExtensions extends Plugin {

  private WEB3FeqAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3FeqAccountDatabaseExtensions.class );
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
    regClass(  webbroker3.feq.data.FCashBalancePK.class );
    regClass(  webbroker3.feq.data.FCashBalanceParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "f_cash_balance",
      webbroker3.feq.data.FCashBalancePK.class,
      webbroker3.feq.data.FCashBalanceParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.feq.data.FCashBalanceRow.class,
        webbroker3.feq.data.FCashBalanceDao.FACTORY );
  }

}
@
