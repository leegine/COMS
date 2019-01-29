head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.23.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsGatewaySessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3RlsGatewaySessionDatabaseExtensions extends Plugin {

  private WEB3RlsGatewaySessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3RlsGatewaySessionDatabaseExtensions.class );
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
    regClass(  webbroker3.rlsgateway.data.OmsConOrderRequestPK.class );
    regClass(  webbroker3.rlsgateway.data.OmsConOrderRequestParams.class );
    regClass(  webbroker3.rlsgateway.data.RlsConOrderHitNotifyPK.class );
    regClass(  webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams.class );
    regClass(  webbroker3.rlsgateway.data.RlsManualSubmitHistoryPK.class );
    regClass(  webbroker3.rlsgateway.data.RlsManualSubmitHistoryParams.class );
    regClass(  webbroker3.rlsgateway.data.RlsOrderMissPK.class );
    regClass(  webbroker3.rlsgateway.data.RlsOrderMissParams.class );
    regClass(  webbroker3.rlsgateway.data.RlsAccountPK.class );
    regClass(  webbroker3.rlsgateway.data.RlsAccountParams.class );
    regClass(  webbroker3.rlsgateway.data.RlsCondOrderPK.class );
    regClass(  webbroker3.rlsgateway.data.RlsCondOrderParams.class );
    regClass(  webbroker3.rlsgateway.data.RlsOmsOrderPK.class );
    regClass(  webbroker3.rlsgateway.data.RlsOmsOrderParams.class );
    regClass(  webbroker3.rlsgateway.data.RlsPriceCondPK.class );
    regClass(  webbroker3.rlsgateway.data.RlsPriceCondParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "oms_con_order_request",
      webbroker3.rlsgateway.data.OmsConOrderRequestPK.class,
      webbroker3.rlsgateway.data.OmsConOrderRequestParams.class,
      null,
      null );
    regDbExtension( "session", "rls_con_order_hit_notify",
      webbroker3.rlsgateway.data.RlsConOrderHitNotifyPK.class,
      webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "rls_manual_submit_history",
      webbroker3.rlsgateway.data.RlsManualSubmitHistoryPK.class,
      webbroker3.rlsgateway.data.RlsManualSubmitHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "rls_order_miss",
      webbroker3.rlsgateway.data.RlsOrderMissPK.class,
      webbroker3.rlsgateway.data.RlsOrderMissParams.class,
      null,
      null );
    regDbExtension( "session", "rls_account",
      webbroker3.rlsgateway.data.RlsAccountPK.class,
      webbroker3.rlsgateway.data.RlsAccountParams.class,
      null,
      null );
    regDbExtension( "session", "rls_cond_order",
      webbroker3.rlsgateway.data.RlsCondOrderPK.class,
      webbroker3.rlsgateway.data.RlsCondOrderParams.class,
      null,
      null );
    regDbExtension( "session", "rls_oms_order",
      webbroker3.rlsgateway.data.RlsOmsOrderPK.class,
      webbroker3.rlsgateway.data.RlsOmsOrderParams.class,
      null,
      null );
    regDbExtension( "session", "rls_price_cond",
      webbroker3.rlsgateway.data.RlsPriceCondPK.class,
      webbroker3.rlsgateway.data.RlsPriceCondParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.rlsgateway.data.OmsConOrderRequestRow.class,
        webbroker3.rlsgateway.data.OmsConOrderRequestDao.FACTORY );
    regDao(
        webbroker3.rlsgateway.data.RlsConOrderHitNotifyRow.class,
        webbroker3.rlsgateway.data.RlsConOrderHitNotifyDao.FACTORY );
    regDao(
        webbroker3.rlsgateway.data.RlsManualSubmitHistoryRow.class,
        webbroker3.rlsgateway.data.RlsManualSubmitHistoryDao.FACTORY );
    regDao(
        webbroker3.rlsgateway.data.RlsOrderMissRow.class,
        webbroker3.rlsgateway.data.RlsOrderMissDao.FACTORY );
    regDao(
        webbroker3.rlsgateway.data.RlsAccountRow.class,
        webbroker3.rlsgateway.data.RlsAccountDao.FACTORY );
    regDao(
        webbroker3.rlsgateway.data.RlsCondOrderRow.class,
        webbroker3.rlsgateway.data.RlsCondOrderDao.FACTORY );
    regDao(
        webbroker3.rlsgateway.data.RlsOmsOrderRow.class,
        webbroker3.rlsgateway.data.RlsOmsOrderDao.FACTORY );
    regDao(
        webbroker3.rlsgateway.data.RlsPriceCondRow.class,
        webbroker3.rlsgateway.data.RlsPriceCondDao.FACTORY );
  }

}
@
