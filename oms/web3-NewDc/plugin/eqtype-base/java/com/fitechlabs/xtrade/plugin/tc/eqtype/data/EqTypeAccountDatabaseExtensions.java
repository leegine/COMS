head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.38.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqTypeAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class EqTypeAccountDatabaseExtensions extends Plugin {

  private EqTypeAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( EqTypeAccountDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "eqtype_fin_transaction",
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionPK.class,
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionParams.class,
      null,
      null );
    regDbExtension( "account", "eqtype_order",
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderPK.class,
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams.class,
      null,
      null );
    regDbExtension( "account", "eqtype_order_unit",
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitPK.class,
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams.class,
      null,
      null );
    regDbExtension( "account", "eqtype_order_action",
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionPK.class,
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams.class,
      null,
      null );
    regDbExtension( "account", "eqtype_order_execution",
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionPK.class,
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams.class,
      null,
      null );
    regDbExtension( "account", "eqtype_locked_contract_details",
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsPK.class,
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsParams.class,
      null,
      null );
    regDbExtension( "account", "eqtype_contract",
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractPK.class,
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams.class,
      null,
      null );
    regDbExtension( "account", "eqtype_closing_contract_spec",
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecPK.class,
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow.class,
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow.class,
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow.class,
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow.class,
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow.class,
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsRow.class,
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow.class,
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow.class,
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecDao.FACTORY );
  }

}
@
