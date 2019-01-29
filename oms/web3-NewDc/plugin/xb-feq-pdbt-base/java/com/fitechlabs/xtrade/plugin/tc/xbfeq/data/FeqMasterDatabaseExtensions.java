head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.02.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88656402a7;
filename	FeqMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbfeq.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class FeqMasterDatabaseExtensions extends Plugin {

  private FeqMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( FeqMasterDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductUpdqPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductUpdqParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "feq_product",
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams.class,
      null,
      null );
    regDbExtension( "master", "feq_traded_product",
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams.class,
      null,
      null );
    regDbExtension( "master", "feq_traded_product_updq",
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductUpdqPK.class,
      com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductUpdqParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductUpdqRow.class,
        com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductUpdqDao.FACTORY );
  }

}
@
