head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.25.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRcpSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rcp.data.qtp;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3QtpRcpSessionDatabaseExtensions extends Plugin {

  private WEB3QtpRcpSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3QtpRcpSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushHistoryPK.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushHistoryParams.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushHistoryTopPK.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushHistoryTopParams.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptPK.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptParams.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptPK.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptParams.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelPK.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelParams.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushEquityContPK.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushEquityContParams.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushEquityLapsePK.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushEquityLapseParams.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptPK.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptParams.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelPK.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelParams.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushIfoContPK.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushIfoContParams.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushIfoLapsePK.class );
    regClass(  webbroker3.rcp.data.qtp.QtpRichPushIfoLapseParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "qtp_rich_push_history",
      webbroker3.rcp.data.qtp.QtpRichPushHistoryPK.class,
      webbroker3.rcp.data.qtp.QtpRichPushHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "qtp_rich_push_history_top",
      webbroker3.rcp.data.qtp.QtpRichPushHistoryTopPK.class,
      webbroker3.rcp.data.qtp.QtpRichPushHistoryTopParams.class,
      null,
      null );
    regDbExtension( "session", "qtp_rich_push_eq_orderaccept",
      webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptPK.class,
      webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptParams.class,
      null,
      null );
    regDbExtension( "session", "qtp_rich_push_sw_orderaccept",
      webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptPK.class,
      webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptParams.class,
      null,
      null );
    regDbExtension( "session", "qtp_rich_push_eq_changecancel",
      webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelPK.class,
      webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelParams.class,
      null,
      null );
    regDbExtension( "session", "qtp_rich_push_equity_cont",
      webbroker3.rcp.data.qtp.QtpRichPushEquityContPK.class,
      webbroker3.rcp.data.qtp.QtpRichPushEquityContParams.class,
      null,
      null );
    regDbExtension( "session", "qtp_rich_push_equity_lapse",
      webbroker3.rcp.data.qtp.QtpRichPushEquityLapsePK.class,
      webbroker3.rcp.data.qtp.QtpRichPushEquityLapseParams.class,
      null,
      null );
    regDbExtension( "session", "qtp_rich_push_ifo_orderaccept",
      webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptPK.class,
      webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptParams.class,
      null,
      null );
    regDbExtension( "session", "qtp_rich_push_ifo_changecancel",
      webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelPK.class,
      webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelParams.class,
      null,
      null );
    regDbExtension( "session", "qtp_rich_push_ifo_cont",
      webbroker3.rcp.data.qtp.QtpRichPushIfoContPK.class,
      webbroker3.rcp.data.qtp.QtpRichPushIfoContParams.class,
      null,
      null );
    regDbExtension( "session", "qtp_rich_push_ifo_lapse",
      webbroker3.rcp.data.qtp.QtpRichPushIfoLapsePK.class,
      webbroker3.rcp.data.qtp.QtpRichPushIfoLapseParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.rcp.data.qtp.QtpRichPushHistoryRow.class,
        webbroker3.rcp.data.qtp.QtpRichPushHistoryDao.FACTORY );
    regDao(
        webbroker3.rcp.data.qtp.QtpRichPushHistoryTopRow.class,
        webbroker3.rcp.data.qtp.QtpRichPushHistoryTopDao.FACTORY );
    regDao(
        webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptRow.class,
        webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptDao.FACTORY );
    regDao(
        webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptRow.class,
        webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptDao.FACTORY );
    regDao(
        webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelRow.class,
        webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelDao.FACTORY );
    regDao(
        webbroker3.rcp.data.qtp.QtpRichPushEquityContRow.class,
        webbroker3.rcp.data.qtp.QtpRichPushEquityContDao.FACTORY );
    regDao(
        webbroker3.rcp.data.qtp.QtpRichPushEquityLapseRow.class,
        webbroker3.rcp.data.qtp.QtpRichPushEquityLapseDao.FACTORY );
    regDao(
        webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptRow.class,
        webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptDao.FACTORY );
    regDao(
        webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelRow.class,
        webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelDao.FACTORY );
    regDao(
        webbroker3.rcp.data.qtp.QtpRichPushIfoContRow.class,
        webbroker3.rcp.data.qtp.QtpRichPushIfoContDao.FACTORY );
    regDao(
        webbroker3.rcp.data.qtp.QtpRichPushIfoLapseRow.class,
        webbroker3.rcp.data.qtp.QtpRichPushIfoLapseDao.FACTORY );
  }

}
@
