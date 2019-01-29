head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleMarketAdapterSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class SleMarketAdapterSessionDatabaseExtensions extends Plugin {

  private SleMarketAdapterSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( SleMarketAdapterSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.slebase.data.SleSendQPK.class );
    regClass(  webbroker3.slebase.data.SleSendQParams.class );
    regClass(  webbroker3.slebase.data.SleSendQErrorsPK.class );
    regClass(  webbroker3.slebase.data.SleSendQErrorsParams.class );
    regClass(  webbroker3.slebase.data.SleRcvdQPK.class );
    regClass(  webbroker3.slebase.data.SleRcvdQParams.class );
    regClass(  webbroker3.slebase.data.SleExchangeOrderKeyMngPK.class );
    regClass(  webbroker3.slebase.data.SleExchangeOrderKeyMngParams.class );
    regClass(  webbroker3.slebase.data.SleConnStatusChangesPK.class );
    regClass(  webbroker3.slebase.data.SleConnStatusChangesParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "sle_send_q",
      webbroker3.slebase.data.SleSendQPK.class,
      webbroker3.slebase.data.SleSendQParams.class,
      null,
      null );
    regDbExtension( "session", "sle_send_q_errors",
      webbroker3.slebase.data.SleSendQErrorsPK.class,
      webbroker3.slebase.data.SleSendQErrorsParams.class,
      null,
      null );
    regDbExtension( "session", "sle_rcvd_q",
      webbroker3.slebase.data.SleRcvdQPK.class,
      webbroker3.slebase.data.SleRcvdQParams.class,
      null,
      null );
    regDbExtension( "session", "sle_exchange_order_key_mng",
      webbroker3.slebase.data.SleExchangeOrderKeyMngPK.class,
      webbroker3.slebase.data.SleExchangeOrderKeyMngParams.class,
      null,
      null );
    regDbExtension( "session", "sle_conn_status_changes",
      webbroker3.slebase.data.SleConnStatusChangesPK.class,
      webbroker3.slebase.data.SleConnStatusChangesParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.slebase.data.SleSendQRow.class,
        webbroker3.slebase.data.SleSendQDao.FACTORY );
    regDao(
        webbroker3.slebase.data.SleSendQErrorsRow.class,
        webbroker3.slebase.data.SleSendQErrorsDao.FACTORY );
    regDao(
        webbroker3.slebase.data.SleRcvdQRow.class,
        webbroker3.slebase.data.SleRcvdQDao.FACTORY );
    regDao(
        webbroker3.slebase.data.SleExchangeOrderKeyMngRow.class,
        webbroker3.slebase.data.SleExchangeOrderKeyMngDao.FACTORY );
    regDao(
        webbroker3.slebase.data.SleConnStatusChangesRow.class,
        webbroker3.slebase.data.SleConnStatusChangesDao.FACTORY );
  }

}
@
