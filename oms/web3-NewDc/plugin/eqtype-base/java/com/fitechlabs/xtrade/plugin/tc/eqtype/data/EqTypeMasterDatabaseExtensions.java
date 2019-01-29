head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	EqTypeMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.eqtype.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class EqTypeMasterDatabaseExtensions extends Plugin {

  private EqTypeMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( EqTypeMasterDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "eqtype_traded_product_updq",
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqPK.class,
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams.class,
      null,
      null );
    regDbExtension( "master", "eqtype_traded_product",
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams.class,
      null,
      null );
    regDbExtension( "master", "eqtype_product",
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow.class,
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao.FACTORY );
  }

}
@
