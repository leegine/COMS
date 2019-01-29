head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.03.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88656402a7;
filename	FeqAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbfeq.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class FeqAccountDatabaseExtensions extends Plugin {

  private FeqAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( FeqAccountDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "feq_fin_transaction",
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams.class,
      null,
      null );
    regDbExtension( "account", "feq_order",
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams.class,
      null,
      null );
    regDbExtension( "account", "feq_order_action",
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams.class,
      null,
      null );
    regDbExtension( "account", "feq_order_execution",
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams.class,
      null,
      null );
    regDbExtension( "account", "feq_order_unit",
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao.FACTORY );
  }

}
@
