head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.06.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbifo.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class IfoAccountDatabaseExtensions extends Plugin {

  private IfoAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( IfoAccountDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "ifo_closing_contract_spec",
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams.class,
      null,
      null );
    regDbExtension( "account", "ifo_contract",
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams.class,
      null,
      null );
    regDbExtension( "account", "ifo_locked_contract_details",
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsParams.class,
      null,
      null );
    regDbExtension( "account", "ifo_fin_transaction",
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams.class,
      null,
      null );
    regDbExtension( "account", "ifo_order",
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams.class,
      null,
      null );
    regDbExtension( "account", "ifo_order_action",
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams.class,
      null,
      null );
    regDbExtension( "account", "ifo_order_execution",
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionParams.class,
      null,
      null );
    regDbExtension( "account", "ifo_order_unit",
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao.FACTORY );
  }

}
@
