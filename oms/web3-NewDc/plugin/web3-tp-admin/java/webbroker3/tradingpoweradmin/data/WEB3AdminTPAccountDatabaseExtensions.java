head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.57.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3AdminTPAccountDatabaseExtensions extends Plugin {

  private WEB3AdminTPAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3AdminTPAccountDatabaseExtensions.class );
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
    regClass(  webbroker3.tradingpoweradmin.data.DepositAutotransferStopPK.class );
    regClass(  webbroker3.tradingpoweradmin.data.DepositAutotransferStopParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "deposit_autotransfer_stop",
      webbroker3.tradingpoweradmin.data.DepositAutotransferStopPK.class,
      webbroker3.tradingpoweradmin.data.DepositAutotransferStopParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.tradingpoweradmin.data.DepositAutotransferStopRow.class,
        webbroker3.tradingpoweradmin.data.DepositAutotransferStopDao.FACTORY );
  }

}
@
