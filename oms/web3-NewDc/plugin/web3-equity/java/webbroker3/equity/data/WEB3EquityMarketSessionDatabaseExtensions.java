head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarketSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3EquityMarketSessionDatabaseExtensions extends Plugin {

  private WEB3EquityMarketSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3EquityMarketSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.equity.data.EqtypeOrderExecSendMailPK.class );
    regClass(  webbroker3.equity.data.EqtypeOrderExecSendMailParams.class );
    regClass(  webbroker3.equity.data.HostEqtypeCloseOrderNotifyPK.class );
    regClass(  webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams.class );
    regClass(  webbroker3.equity.data.HostEquityOrderExecNotifyPK.class );
    regClass(  webbroker3.equity.data.HostEquityOrderExecNotifyParams.class );
    regClass(  webbroker3.equity.data.HostEquityCarryoverSkipPK.class );
    regClass(  webbroker3.equity.data.HostEquityCarryoverSkipParams.class );
    regClass(  webbroker3.equity.data.HostEqtypeOrderAcceptPK.class );
    regClass(  webbroker3.equity.data.HostEqtypeOrderAcceptParams.class );
    regClass(  webbroker3.equity.data.HostEqtypeOrderClmdReceiptPK.class );
    regClass(  webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams.class );
    regClass(  webbroker3.equity.data.HostEqtypeOrderReceiptPK.class );
    regClass(  webbroker3.equity.data.HostEqtypeOrderReceiptParams.class );
    regClass(  webbroker3.equity.data.HostEqtypeOrderAllPK.class );
    regClass(  webbroker3.equity.data.HostEqtypeOrderAllParams.class );
    regClass(  webbroker3.equity.data.HostEqtypeSwapPK.class );
    regClass(  webbroker3.equity.data.HostEqtypeSwapParams.class );
    regClass(  webbroker3.equity.data.HostEqtypeSwapAcceptPK.class );
    regClass(  webbroker3.equity.data.HostEqtypeSwapAcceptParams.class );
    regClass(  webbroker3.equity.data.HostEqtypeSwapReceiptPK.class );
    regClass(  webbroker3.equity.data.HostEqtypeSwapReceiptParams.class );
    regClass(  webbroker3.equity.data.MarketNoticeManagementPK.class );
    regClass(  webbroker3.equity.data.MarketNoticeManagementParams.class );
    regClass(  webbroker3.equity.data.HostEqtypeOrderHistoryPK.class );
    regClass(  webbroker3.equity.data.HostEqtypeOrderHistoryParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "eqtype_order_exec_send_mail",
      webbroker3.equity.data.EqtypeOrderExecSendMailPK.class,
      webbroker3.equity.data.EqtypeOrderExecSendMailParams.class,
      null,
      null );
    regDbExtension( "session", "host_eqtype_close_order_notify",
      webbroker3.equity.data.HostEqtypeCloseOrderNotifyPK.class,
      webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "host_equity_order_exec_notify",
      webbroker3.equity.data.HostEquityOrderExecNotifyPK.class,
      webbroker3.equity.data.HostEquityOrderExecNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "host_equity_carryover_skip",
      webbroker3.equity.data.HostEquityCarryoverSkipPK.class,
      webbroker3.equity.data.HostEquityCarryoverSkipParams.class,
      null,
      null );
    regDbExtension( "session", "host_eqtype_order_accept",
      webbroker3.equity.data.HostEqtypeOrderAcceptPK.class,
      webbroker3.equity.data.HostEqtypeOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_eqtype_order_clmd_receipt",
      webbroker3.equity.data.HostEqtypeOrderClmdReceiptPK.class,
      webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams.class,
      null,
      null );
    regDbExtension( "session", "host_eqtype_order_receipt",
      webbroker3.equity.data.HostEqtypeOrderReceiptPK.class,
      webbroker3.equity.data.HostEqtypeOrderReceiptParams.class,
      null,
      null );
    regDbExtension( "session", "host_eqtype_order_all",
      webbroker3.equity.data.HostEqtypeOrderAllPK.class,
      webbroker3.equity.data.HostEqtypeOrderAllParams.class,
      null,
      null );
    regDbExtension( "session", "host_eqtype_swap",
      webbroker3.equity.data.HostEqtypeSwapPK.class,
      webbroker3.equity.data.HostEqtypeSwapParams.class,
      null,
      null );
    regDbExtension( "session", "host_eqtype_swap_accept",
      webbroker3.equity.data.HostEqtypeSwapAcceptPK.class,
      webbroker3.equity.data.HostEqtypeSwapAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_eqtype_swap_receipt",
      webbroker3.equity.data.HostEqtypeSwapReceiptPK.class,
      webbroker3.equity.data.HostEqtypeSwapReceiptParams.class,
      null,
      null );
    regDbExtension( "session", "market_notice_management",
      webbroker3.equity.data.MarketNoticeManagementPK.class,
      webbroker3.equity.data.MarketNoticeManagementParams.class,
      null,
      null );
    regDbExtension( "session", "host_eqtype_order_history",
      webbroker3.equity.data.HostEqtypeOrderHistoryPK.class,
      webbroker3.equity.data.HostEqtypeOrderHistoryParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.equity.data.EqtypeOrderExecSendMailRow.class,
        webbroker3.equity.data.EqtypeOrderExecSendMailDao.FACTORY );
    regDao(
        webbroker3.equity.data.HostEqtypeCloseOrderNotifyRow.class,
        webbroker3.equity.data.HostEqtypeCloseOrderNotifyDao.FACTORY );
    regDao(
        webbroker3.equity.data.HostEquityOrderExecNotifyRow.class,
        webbroker3.equity.data.HostEquityOrderExecNotifyDao.FACTORY );
    regDao(
        webbroker3.equity.data.HostEquityCarryoverSkipRow.class,
        webbroker3.equity.data.HostEquityCarryoverSkipDao.FACTORY );
    regDao(
        webbroker3.equity.data.HostEqtypeOrderAcceptRow.class,
        webbroker3.equity.data.HostEqtypeOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.equity.data.HostEqtypeOrderClmdReceiptRow.class,
        webbroker3.equity.data.HostEqtypeOrderClmdReceiptDao.FACTORY );
    regDao(
        webbroker3.equity.data.HostEqtypeOrderReceiptRow.class,
        webbroker3.equity.data.HostEqtypeOrderReceiptDao.FACTORY );
    regDao(
        webbroker3.equity.data.HostEqtypeOrderAllRow.class,
        webbroker3.equity.data.HostEqtypeOrderAllDao.FACTORY );
    regDao(
        webbroker3.equity.data.HostEqtypeSwapRow.class,
        webbroker3.equity.data.HostEqtypeSwapDao.FACTORY );
    regDao(
        webbroker3.equity.data.HostEqtypeSwapAcceptRow.class,
        webbroker3.equity.data.HostEqtypeSwapAcceptDao.FACTORY );
    regDao(
        webbroker3.equity.data.HostEqtypeSwapReceiptRow.class,
        webbroker3.equity.data.HostEqtypeSwapReceiptDao.FACTORY );
    regDao(
        webbroker3.equity.data.MarketNoticeManagementRow.class,
        webbroker3.equity.data.MarketNoticeManagementDao.FACTORY );
    regDao(
        webbroker3.equity.data.HostEqtypeOrderHistoryRow.class,
        webbroker3.equity.data.HostEqtypeOrderHistoryDao.FACTORY );
  }

}
@
