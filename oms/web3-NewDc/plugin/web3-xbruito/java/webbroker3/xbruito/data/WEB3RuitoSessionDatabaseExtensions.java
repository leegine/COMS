head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.xbruito.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3RuitoSessionDatabaseExtensions extends Plugin {

  private WEB3RuitoSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3RuitoSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.xbruito.data.HostRuitoOrderAcceptParams.class );
    regClass(  webbroker3.xbruito.data.HostRuitoOrderCancelParams.class );
    regClass(  webbroker3.xbruito.data.HostRuitoCancelAcceptParams.class );
    regClass(  webbroker3.xbruito.data.HostMrfOrderParams.class );
    regClass(  webbroker3.xbruito.data.HostRuitoOrderParams.class );
    regClass(  webbroker3.xbruito.data.HostRuitoOrderNotifyParams.class );
    regClass(  webbroker3.xbruito.data.HostSellCancelParams.class );
    regClass(  webbroker3.xbruito.data.HostMrfOrderCancelParams.class );
    regClass(  webbroker3.xbruito.data.HostMrfCancelAcceptParams.class );
    regClass(  webbroker3.xbruito.data.HostMrfOrderAcceptParams.class );
    regClass(  webbroker3.xbruito.data.HostRuitoSellParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "host_ruito_order_accept",
      null,
      webbroker3.xbruito.data.HostRuitoOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_ruito_order_cancel",
      null,
      webbroker3.xbruito.data.HostRuitoOrderCancelParams.class,
      null,
      null );
    regDbExtension( "session", "host_ruito_cancel_accept",
      null,
      webbroker3.xbruito.data.HostRuitoCancelAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_mrf_order",
      null,
      webbroker3.xbruito.data.HostMrfOrderParams.class,
      null,
      null );
    regDbExtension( "session", "host_ruito_order",
      null,
      webbroker3.xbruito.data.HostRuitoOrderParams.class,
      null,
      null );
    regDbExtension( "session", "host_ruito_order_notify",
      null,
      webbroker3.xbruito.data.HostRuitoOrderNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "host_sell_cancel",
      null,
      webbroker3.xbruito.data.HostSellCancelParams.class,
      null,
      null );
    regDbExtension( "session", "host_mrf_order_cancel",
      null,
      webbroker3.xbruito.data.HostMrfOrderCancelParams.class,
      null,
      null );
    regDbExtension( "session", "host_mrf_cancel_accept",
      null,
      webbroker3.xbruito.data.HostMrfCancelAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_mrf_order_accept",
      null,
      webbroker3.xbruito.data.HostMrfOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_ruito_sell",
      null,
      webbroker3.xbruito.data.HostRuitoSellParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.xbruito.data.HostRuitoOrderAcceptRow.class,
        webbroker3.xbruito.data.HostRuitoOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.xbruito.data.HostRuitoOrderCancelRow.class,
        webbroker3.xbruito.data.HostRuitoOrderCancelDao.FACTORY );
    regDao(
        webbroker3.xbruito.data.HostRuitoCancelAcceptRow.class,
        webbroker3.xbruito.data.HostRuitoCancelAcceptDao.FACTORY );
    regDao(
        webbroker3.xbruito.data.HostMrfOrderRow.class,
        webbroker3.xbruito.data.HostMrfOrderDao.FACTORY );
    regDao(
        webbroker3.xbruito.data.HostRuitoOrderRow.class,
        webbroker3.xbruito.data.HostRuitoOrderDao.FACTORY );
    regDao(
        webbroker3.xbruito.data.HostRuitoOrderNotifyRow.class,
        webbroker3.xbruito.data.HostRuitoOrderNotifyDao.FACTORY );
    regDao(
        webbroker3.xbruito.data.HostSellCancelRow.class,
        webbroker3.xbruito.data.HostSellCancelDao.FACTORY );
    regDao(
        webbroker3.xbruito.data.HostMrfOrderCancelRow.class,
        webbroker3.xbruito.data.HostMrfOrderCancelDao.FACTORY );
    regDao(
        webbroker3.xbruito.data.HostMrfCancelAcceptRow.class,
        webbroker3.xbruito.data.HostMrfCancelAcceptDao.FACTORY );
    regDao(
        webbroker3.xbruito.data.HostMrfOrderAcceptRow.class,
        webbroker3.xbruito.data.HostMrfOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.xbruito.data.HostRuitoSellRow.class,
        webbroker3.xbruito.data.HostRuitoSellDao.FACTORY );
  }

}
@
