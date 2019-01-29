head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.59.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class BondMasterDatabaseExtensions extends Plugin {

  private BondMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( BondMasterDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductUpdqPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductUpdqParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "bond_product",
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams.class,
      null,
      null );
    regDbExtension( "master", "bond_traded_product",
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductParams.class,
      null,
      null );
    regDbExtension( "master", "bond_traded_product_updq",
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductUpdqPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductUpdqParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductUpdqRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondTradedProductUpdqDao.FACTORY );
  }

}
@
