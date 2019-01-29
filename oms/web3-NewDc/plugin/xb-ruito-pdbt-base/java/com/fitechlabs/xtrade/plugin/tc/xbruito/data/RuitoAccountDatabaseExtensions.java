head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.16.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbruito.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class RuitoAccountDatabaseExtensions extends Plugin {

  private RuitoAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( RuitoAccountDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoFinTransactionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoFinTransactionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderExecutionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderExecutionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "ruito_fin_transaction",
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoFinTransactionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoFinTransactionParams.class,
      null,
      null );
    regDbExtension( "account", "ruito_order_execution",
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderExecutionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderExecutionParams.class,
      null,
      null );
    regDbExtension( "account", "ruito_order",
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderParams.class,
      null,
      null );
    regDbExtension( "account", "ruito_order_unit",
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams.class,
      null,
      null );
    regDbExtension( "account", "ruito_order_action",
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoFinTransactionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoFinTransactionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderExecutionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderExecutionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionDao.FACTORY );
  }

}
@
