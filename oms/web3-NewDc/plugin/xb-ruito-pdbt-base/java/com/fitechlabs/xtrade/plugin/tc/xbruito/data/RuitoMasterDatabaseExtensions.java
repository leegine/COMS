head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.16.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbruito.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class RuitoMasterDatabaseExtensions extends Plugin {

  private RuitoMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( RuitoMasterDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductUpdqPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductUpdqParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "ruito_traded_product",
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductParams.class,
      null,
      null );
    regDbExtension( "master", "ruito_product",
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams.class,
      null,
      null );
    regDbExtension( "master", "ruito_traded_product_updq",
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductUpdqPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductUpdqParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductUpdqRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductUpdqDao.FACTORY );
  }

}
@
