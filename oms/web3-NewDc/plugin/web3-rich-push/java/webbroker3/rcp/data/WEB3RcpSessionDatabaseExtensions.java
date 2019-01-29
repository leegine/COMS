head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.28.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RcpSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3RcpSessionDatabaseExtensions extends Plugin {

  private WEB3RcpSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3RcpSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.rcp.data.RichPushHistoryPK.class );
    regClass(  webbroker3.rcp.data.RichPushHistoryParams.class );
    regClass(  webbroker3.rcp.data.RichPushEquityOrderAcceptPK.class );
    regClass(  webbroker3.rcp.data.RichPushEquityOrderAcceptParams.class );
    regClass(  webbroker3.rcp.data.RichPushSwapOrderAcceptPK.class );
    regClass(  webbroker3.rcp.data.RichPushSwapOrderAcceptParams.class );
    regClass(  webbroker3.rcp.data.RichPushEquityChangeCancelPK.class );
    regClass(  webbroker3.rcp.data.RichPushEquityChangeCancelParams.class );
    regClass(  webbroker3.rcp.data.RichPushEquityContPK.class );
    regClass(  webbroker3.rcp.data.RichPushEquityContParams.class );
    regClass(  webbroker3.rcp.data.RichPushEquityLapsePK.class );
    regClass(  webbroker3.rcp.data.RichPushEquityLapseParams.class );
    regClass(  webbroker3.rcp.data.RichPushIfoOrderAcceptPK.class );
    regClass(  webbroker3.rcp.data.RichPushIfoOrderAcceptParams.class );
    regClass(  webbroker3.rcp.data.RichPushIfoChangeCancelPK.class );
    regClass(  webbroker3.rcp.data.RichPushIfoChangeCancelParams.class );
    regClass(  webbroker3.rcp.data.RichPushIfoContPK.class );
    regClass(  webbroker3.rcp.data.RichPushIfoContParams.class );
    regClass(  webbroker3.rcp.data.RichPushIfoLapsePK.class );
    regClass(  webbroker3.rcp.data.RichPushIfoLapseParams.class );
    regClass(  webbroker3.rcp.data.RichPushHistoryTopPK.class );
    regClass(  webbroker3.rcp.data.RichPushHistoryTopParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "rich_push_history",
      webbroker3.rcp.data.RichPushHistoryPK.class,
      webbroker3.rcp.data.RichPushHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "rich_push_equity_order_accept",
      webbroker3.rcp.data.RichPushEquityOrderAcceptPK.class,
      webbroker3.rcp.data.RichPushEquityOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "rich_push_swap_order_accept",
      webbroker3.rcp.data.RichPushSwapOrderAcceptPK.class,
      webbroker3.rcp.data.RichPushSwapOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "rich_push_equity_change_cancel",
      webbroker3.rcp.data.RichPushEquityChangeCancelPK.class,
      webbroker3.rcp.data.RichPushEquityChangeCancelParams.class,
      null,
      null );
    regDbExtension( "session", "rich_push_equity_cont",
      webbroker3.rcp.data.RichPushEquityContPK.class,
      webbroker3.rcp.data.RichPushEquityContParams.class,
      null,
      null );
    regDbExtension( "session", "rich_push_equity_lapse",
      webbroker3.rcp.data.RichPushEquityLapsePK.class,
      webbroker3.rcp.data.RichPushEquityLapseParams.class,
      null,
      null );
    regDbExtension( "session", "rich_push_ifo_order_accept",
      webbroker3.rcp.data.RichPushIfoOrderAcceptPK.class,
      webbroker3.rcp.data.RichPushIfoOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "rich_push_ifo_change_cancel",
      webbroker3.rcp.data.RichPushIfoChangeCancelPK.class,
      webbroker3.rcp.data.RichPushIfoChangeCancelParams.class,
      null,
      null );
    regDbExtension( "session", "rich_push_ifo_cont",
      webbroker3.rcp.data.RichPushIfoContPK.class,
      webbroker3.rcp.data.RichPushIfoContParams.class,
      null,
      null );
    regDbExtension( "session", "rich_push_ifo_lapse",
      webbroker3.rcp.data.RichPushIfoLapsePK.class,
      webbroker3.rcp.data.RichPushIfoLapseParams.class,
      null,
      null );
    regDbExtension( "session", "rich_push_history_top",
      webbroker3.rcp.data.RichPushHistoryTopPK.class,
      webbroker3.rcp.data.RichPushHistoryTopParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.rcp.data.RichPushHistoryRow.class,
        webbroker3.rcp.data.RichPushHistoryDao.FACTORY );
    regDao(
        webbroker3.rcp.data.RichPushEquityOrderAcceptRow.class,
        webbroker3.rcp.data.RichPushEquityOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.rcp.data.RichPushSwapOrderAcceptRow.class,
        webbroker3.rcp.data.RichPushSwapOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.rcp.data.RichPushEquityChangeCancelRow.class,
        webbroker3.rcp.data.RichPushEquityChangeCancelDao.FACTORY );
    regDao(
        webbroker3.rcp.data.RichPushEquityContRow.class,
        webbroker3.rcp.data.RichPushEquityContDao.FACTORY );
    regDao(
        webbroker3.rcp.data.RichPushEquityLapseRow.class,
        webbroker3.rcp.data.RichPushEquityLapseDao.FACTORY );
    regDao(
        webbroker3.rcp.data.RichPushIfoOrderAcceptRow.class,
        webbroker3.rcp.data.RichPushIfoOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.rcp.data.RichPushIfoChangeCancelRow.class,
        webbroker3.rcp.data.RichPushIfoChangeCancelDao.FACTORY );
    regDao(
        webbroker3.rcp.data.RichPushIfoContRow.class,
        webbroker3.rcp.data.RichPushIfoContDao.FACTORY );
    regDao(
        webbroker3.rcp.data.RichPushIfoLapseRow.class,
        webbroker3.rcp.data.RichPushIfoLapseDao.FACTORY );
    regDao(
        webbroker3.rcp.data.RichPushHistoryTopRow.class,
        webbroker3.rcp.data.RichPushHistoryTopDao.FACTORY );
  }

}
@
