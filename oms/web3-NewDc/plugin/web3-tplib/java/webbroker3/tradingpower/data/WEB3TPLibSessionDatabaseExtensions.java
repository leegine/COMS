head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.29.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPLibSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3TPLibSessionDatabaseExtensions extends Plugin {

  private WEB3TPLibSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3TPLibSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.tradingpower.data.TpAssetHistoryPK.class );
    regClass(  webbroker3.tradingpower.data.TpAssetHistoryParams.class );
    regClass(  webbroker3.tradingpower.data.TpCalcResultExecNotifyPK.class );
    regClass(  webbroker3.tradingpower.data.TpCalcResultExecNotifyParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "tp_asset_history",
      webbroker3.tradingpower.data.TpAssetHistoryPK.class,
      webbroker3.tradingpower.data.TpAssetHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "tp_calc_result_exec_notify",
      webbroker3.tradingpower.data.TpCalcResultExecNotifyPK.class,
      webbroker3.tradingpower.data.TpCalcResultExecNotifyParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.tradingpower.data.TpAssetHistoryRow.class,
        webbroker3.tradingpower.data.TpAssetHistoryDao.FACTORY );
    regDao(
        webbroker3.tradingpower.data.TpCalcResultExecNotifyRow.class,
        webbroker3.tradingpower.data.TpCalcResultExecNotifyDao.FACTORY );
  }

}
@
