head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	WEB3IpoAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3IpoAccountDatabaseExtensions extends Plugin {

  private WEB3IpoAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3IpoAccountDatabaseExtensions.class );
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
    regClass(  webbroker3.ipo.data.IpoOrderPK.class );
    regClass(  webbroker3.ipo.data.IpoOrderParams.class );
    regClass(  webbroker3.ipo.data.IpoBookbuildingOrderActionPK.class );
    regClass(  webbroker3.ipo.data.IpoBookbuildingOrderActionParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "ipo_order",
      webbroker3.ipo.data.IpoOrderPK.class,
      webbroker3.ipo.data.IpoOrderParams.class,
      null,
      null );
    regDbExtension( "account", "ipo_bookbuilding_order_action",
      webbroker3.ipo.data.IpoBookbuildingOrderActionPK.class,
      webbroker3.ipo.data.IpoBookbuildingOrderActionParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.ipo.data.IpoOrderRow.class,
        webbroker3.ipo.data.IpoOrderDao.FACTORY );
    regDao(
        webbroker3.ipo.data.IpoBookbuildingOrderActionRow.class,
        webbroker3.ipo.data.IpoBookbuildingOrderActionDao.FACTORY );
  }

}
@
