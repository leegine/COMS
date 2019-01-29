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
filename	WEB3FeqSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3FeqSessionDatabaseExtensions extends Plugin {

  private WEB3FeqSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3FeqSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.feq.data.FeqOrderEmpNumberPK.class );
    regClass(  webbroker3.feq.data.FeqOrderEmpNumberParams.class );
    regClass(  webbroker3.feq.data.HostFeqCloseOrderNotifyPK.class );
    regClass(  webbroker3.feq.data.HostFeqCloseOrderNotifyParams.class );
    regClass(  webbroker3.feq.data.HostFeqOrderExecNotifyPK.class );
    regClass(  webbroker3.feq.data.HostFeqOrderExecNotifyParams.class );
    regClass(  webbroker3.feq.data.HostFeqOrderPK.class );
    regClass(  webbroker3.feq.data.HostFeqOrderParams.class );
    regClass(  webbroker3.feq.data.HostFeqOrderAcceptPK.class );
    regClass(  webbroker3.feq.data.HostFeqOrderAcceptParams.class );
    regClass(  webbroker3.feq.data.HostFeqChangecancelOrderPK.class );
    regClass(  webbroker3.feq.data.HostFeqChangecancelOrderParams.class );
    regClass(  webbroker3.feq.data.FeqOrderChangeStatusPK.class );
    regClass(  webbroker3.feq.data.FeqOrderChangeStatusParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "feq_order_emp_number",
      webbroker3.feq.data.FeqOrderEmpNumberPK.class,
      webbroker3.feq.data.FeqOrderEmpNumberParams.class,
      null,
      null );
    regDbExtension( "session", "host_feq_close_order_notify",
      webbroker3.feq.data.HostFeqCloseOrderNotifyPK.class,
      webbroker3.feq.data.HostFeqCloseOrderNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "host_feq_order_exec_notify",
      webbroker3.feq.data.HostFeqOrderExecNotifyPK.class,
      webbroker3.feq.data.HostFeqOrderExecNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "host_feq_order",
      webbroker3.feq.data.HostFeqOrderPK.class,
      webbroker3.feq.data.HostFeqOrderParams.class,
      null,
      null );
    regDbExtension( "session", "host_feq_order_accept",
      webbroker3.feq.data.HostFeqOrderAcceptPK.class,
      webbroker3.feq.data.HostFeqOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_feq_changecancel_order",
      webbroker3.feq.data.HostFeqChangecancelOrderPK.class,
      webbroker3.feq.data.HostFeqChangecancelOrderParams.class,
      null,
      null );
    regDbExtension( "session", "feq_order_change_status",
      webbroker3.feq.data.FeqOrderChangeStatusPK.class,
      webbroker3.feq.data.FeqOrderChangeStatusParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.feq.data.FeqOrderEmpNumberRow.class,
        webbroker3.feq.data.FeqOrderEmpNumberDao.FACTORY );
    regDao(
        webbroker3.feq.data.HostFeqCloseOrderNotifyRow.class,
        webbroker3.feq.data.HostFeqCloseOrderNotifyDao.FACTORY );
    regDao(
        webbroker3.feq.data.HostFeqOrderExecNotifyRow.class,
        webbroker3.feq.data.HostFeqOrderExecNotifyDao.FACTORY );
    regDao(
        webbroker3.feq.data.HostFeqOrderRow.class,
        webbroker3.feq.data.HostFeqOrderDao.FACTORY );
    regDao(
        webbroker3.feq.data.HostFeqOrderAcceptRow.class,
        webbroker3.feq.data.HostFeqOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.feq.data.HostFeqChangecancelOrderRow.class,
        webbroker3.feq.data.HostFeqChangecancelOrderDao.FACTORY );
    regDao(
        webbroker3.feq.data.FeqOrderChangeStatusRow.class,
        webbroker3.feq.data.FeqOrderChangeStatusDao.FACTORY );
  }

}
@
