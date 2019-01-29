head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.13.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundAccountDatabaseExtensions.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class MutualFundAccountDatabaseExtensions extends Plugin {

  private MutualFundAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( MutualFundAccountDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundFinTransactionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundFinTransactionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderExecutionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderExecutionParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "mutual_fund_fin_transaction",
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundFinTransactionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundFinTransactionParams.class,
      null,
      null );
    regDbExtension( "account", "mutual_fund_order",
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderParams.class,
      null,
      null );
    regDbExtension( "account", "mutual_fund_order_action",
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams.class,
      null,
      null );
    regDbExtension( "account", "mutual_fund_order_unit",
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams.class,
      null,
      null );
    regDbExtension( "account", "mutual_fund_order_execution",
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderExecutionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderExecutionParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundFinTransactionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundFinTransactionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderExecutionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderExecutionDao.FACTORY );
  }

}
@
