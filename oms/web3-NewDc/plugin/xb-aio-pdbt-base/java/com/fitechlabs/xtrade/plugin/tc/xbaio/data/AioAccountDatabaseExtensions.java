head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.56.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88640f7e4e;
filename	AioAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbaio.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class AioAccountDatabaseExtensions extends Plugin {

  private AioAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( AioAccountDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "aio_fin_transaction",
      com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionParams.class,
      null,
      null );
    regDbExtension( "account", "aio_order",
      com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams.class,
      null,
      null );
    regDbExtension( "account", "aio_order_action",
      com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionParams.class,
      null,
      null );
    regDbExtension( "account", "aio_order_unit",
      com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao.FACTORY );
  }

}
@
