head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.59.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class BondAccountDatabaseExtensions extends Plugin {

  private BondAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( BondAccountDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondFinTransactionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondFinTransactionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "bond_fin_transaction",
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondFinTransactionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondFinTransactionParams.class,
      null,
      null );
    regDbExtension( "account", "bond_order",
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams.class,
      null,
      null );
    regDbExtension( "account", "bond_order_action",
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams.class,
      null,
      null );
    regDbExtension( "account", "bond_order_execution",
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams.class,
      null,
      null );
    regDbExtension( "account", "bond_order_unit",
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondFinTransactionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondFinTransactionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitDao.FACTORY );
  }

}
@
