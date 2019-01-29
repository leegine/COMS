head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EqtypeadminSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3EqtypeadminSessionDatabaseExtensions extends Plugin {

  private WEB3EqtypeadminSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3EqtypeadminSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderParams.class );
    regClass(  webbroker3.eqtypeadmin.data.ForcedSettleOrderInqPK.class );
    regClass(  webbroker3.eqtypeadmin.data.ForcedSettleOrderInqParams.class );
    regClass(  webbroker3.eqtypeadmin.data.AttentionInfoHistoryPK.class );
    regClass(  webbroker3.eqtypeadmin.data.AttentionInfoHistoryParams.class );
    regClass(  webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyPK.class );
    regClass(  webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "admin_eq_forced_settle_order",
      null,
      webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderParams.class,
      null,
      null );
    regDbExtension( "session", "forced_settle_order_inq",
      webbroker3.eqtypeadmin.data.ForcedSettleOrderInqPK.class,
      webbroker3.eqtypeadmin.data.ForcedSettleOrderInqParams.class,
      null,
      null );
    regDbExtension( "session", "attention_info_history",
      webbroker3.eqtypeadmin.data.AttentionInfoHistoryPK.class,
      webbroker3.eqtypeadmin.data.AttentionInfoHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "host_attention_info_notify",
      webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyPK.class,
      webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow.class,
        webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderDao.FACTORY );
    regDao(
        webbroker3.eqtypeadmin.data.ForcedSettleOrderInqRow.class,
        webbroker3.eqtypeadmin.data.ForcedSettleOrderInqDao.FACTORY );
    regDao(
        webbroker3.eqtypeadmin.data.AttentionInfoHistoryRow.class,
        webbroker3.eqtypeadmin.data.AttentionInfoHistoryDao.FACTORY );
    regDao(
        webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyRow.class,
        webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyDao.FACTORY );
  }

}
@
