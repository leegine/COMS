head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.08.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbifo.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class IfoMasterDatabaseExtensions extends Plugin {

  private IfoMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( IfoMasterDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "ifo_product",
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams.class,
      null,
      null );
    regDbExtension( "master", "ifo_traded_product",
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams.class,
      null,
      null );
    regDbExtension( "master", "ifo_traded_product_updq",
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqDao.FACTORY );
  }

}
@
