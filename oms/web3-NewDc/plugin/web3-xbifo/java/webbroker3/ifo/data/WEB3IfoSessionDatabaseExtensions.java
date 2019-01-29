head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3IfoSessionDatabaseExtensions extends Plugin {

  private WEB3IfoSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3IfoSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.ifo.data.IfoOrderExecSendMailPK.class );
    regClass(  webbroker3.ifo.data.IfoOrderExecSendMailParams.class );
    regClass(  webbroker3.ifo.data.HostFotypeClosingContSpecPK.class );
    regClass(  webbroker3.ifo.data.HostFotypeClosingContSpecParams.class );
    regClass(  webbroker3.ifo.data.HostFotypeCloseOrderNotifyPK.class );
    regClass(  webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams.class );
    regClass(  webbroker3.ifo.data.HostOptionOrderExecNotifyPK.class );
    regClass(  webbroker3.ifo.data.HostOptionOrderExecNotifyParams.class );
    regClass(  webbroker3.ifo.data.HostFotypeOrderAcceptPK.class );
    regClass(  webbroker3.ifo.data.HostFotypeOrderAcceptParams.class );
    regClass(  webbroker3.ifo.data.HostFotypeOrderClmdNotifyPK.class );
    regClass(  webbroker3.ifo.data.HostFotypeOrderClmdNotifyParams.class );
    regClass(  webbroker3.ifo.data.HostFotypeOrderReceiptPK.class );
    regClass(  webbroker3.ifo.data.HostFotypeOrderReceiptParams.class );
    regClass(  webbroker3.ifo.data.HostFotypeOrderHistoryPK.class );
    regClass(  webbroker3.ifo.data.HostFotypeOrderHistoryParams.class );
    regClass(  webbroker3.ifo.data.HostFotypeOrderAllPK.class );
    regClass(  webbroker3.ifo.data.HostFotypeOrderAllParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "ifo_order_exec_send_mail",
      webbroker3.ifo.data.IfoOrderExecSendMailPK.class,
      webbroker3.ifo.data.IfoOrderExecSendMailParams.class,
      null,
      null );
    regDbExtension( "session", "host_fotype_closing_cont_spec",
      webbroker3.ifo.data.HostFotypeClosingContSpecPK.class,
      webbroker3.ifo.data.HostFotypeClosingContSpecParams.class,
      null,
      null );
    regDbExtension( "session", "host_fotype_close_order_notify",
      webbroker3.ifo.data.HostFotypeCloseOrderNotifyPK.class,
      webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "host_option_order_exec_notify",
      webbroker3.ifo.data.HostOptionOrderExecNotifyPK.class,
      webbroker3.ifo.data.HostOptionOrderExecNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "host_fotype_order_accept",
      webbroker3.ifo.data.HostFotypeOrderAcceptPK.class,
      webbroker3.ifo.data.HostFotypeOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_fotype_order_clmd_notify",
      webbroker3.ifo.data.HostFotypeOrderClmdNotifyPK.class,
      webbroker3.ifo.data.HostFotypeOrderClmdNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "host_fotype_order_receipt",
      webbroker3.ifo.data.HostFotypeOrderReceiptPK.class,
      webbroker3.ifo.data.HostFotypeOrderReceiptParams.class,
      null,
      null );
    regDbExtension( "session", "host_fotype_order_history",
      webbroker3.ifo.data.HostFotypeOrderHistoryPK.class,
      webbroker3.ifo.data.HostFotypeOrderHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "host_fotype_order_all",
      webbroker3.ifo.data.HostFotypeOrderAllPK.class,
      webbroker3.ifo.data.HostFotypeOrderAllParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.ifo.data.IfoOrderExecSendMailRow.class,
        webbroker3.ifo.data.IfoOrderExecSendMailDao.FACTORY );
    regDao(
        webbroker3.ifo.data.HostFotypeClosingContSpecRow.class,
        webbroker3.ifo.data.HostFotypeClosingContSpecDao.FACTORY );
    regDao(
        webbroker3.ifo.data.HostFotypeCloseOrderNotifyRow.class,
        webbroker3.ifo.data.HostFotypeCloseOrderNotifyDao.FACTORY );
    regDao(
        webbroker3.ifo.data.HostOptionOrderExecNotifyRow.class,
        webbroker3.ifo.data.HostOptionOrderExecNotifyDao.FACTORY );
    regDao(
        webbroker3.ifo.data.HostFotypeOrderAcceptRow.class,
        webbroker3.ifo.data.HostFotypeOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.ifo.data.HostFotypeOrderClmdNotifyRow.class,
        webbroker3.ifo.data.HostFotypeOrderClmdNotifyDao.FACTORY );
    regDao(
        webbroker3.ifo.data.HostFotypeOrderReceiptRow.class,
        webbroker3.ifo.data.HostFotypeOrderReceiptDao.FACTORY );
    regDao(
        webbroker3.ifo.data.HostFotypeOrderHistoryRow.class,
        webbroker3.ifo.data.HostFotypeOrderHistoryDao.FACTORY );
    regDao(
        webbroker3.ifo.data.HostFotypeOrderAllRow.class,
        webbroker3.ifo.data.HostFotypeOrderAllDao.FACTORY );
  }

}
@
