head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.50.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	WEB3IpoMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3IpoMasterDatabaseExtensions extends Plugin {

  private WEB3IpoMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3IpoMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.ipo.data.IpoProductPK.class );
    regClass(  webbroker3.ipo.data.IpoProductParams.class );
    regClass(  webbroker3.ipo.data.IpoBookbuildingPK.class );
    regClass(  webbroker3.ipo.data.IpoBookbuildingParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "ipo_product",
      webbroker3.ipo.data.IpoProductPK.class,
      webbroker3.ipo.data.IpoProductParams.class,
      null,
      null );
    regDbExtension( "master", "ipo_bookbuilding",
      webbroker3.ipo.data.IpoBookbuildingPK.class,
      webbroker3.ipo.data.IpoBookbuildingParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.ipo.data.IpoProductRow.class,
        webbroker3.ipo.data.IpoProductDao.FACTORY );
    regDao(
        webbroker3.ipo.data.IpoBookbuildingRow.class,
        webbroker3.ipo.data.IpoBookbuildingDao.FACTORY );
  }

}
@
