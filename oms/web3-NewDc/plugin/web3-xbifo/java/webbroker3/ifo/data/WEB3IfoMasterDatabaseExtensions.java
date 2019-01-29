head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3IfoMasterDatabaseExtensions extends Plugin {

  private WEB3IfoMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3IfoMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.ifo.data.IfoTickValuesMasterPK.class );
    regClass(  webbroker3.ifo.data.IfoTickValuesMasterParams.class );
    regClass(  webbroker3.ifo.data.IfoLimitPriceRangeMasterPK.class );
    regClass(  webbroker3.ifo.data.IfoLimitPriceRangeMasterParams.class );
    regClass(  webbroker3.ifo.data.IfoIndexMasterPK.class );
    regClass(  webbroker3.ifo.data.IfoIndexMasterParams.class );
    regClass(  webbroker3.ifo.data.IfoOrderCarryoverSkipProdPK.class );
    regClass(  webbroker3.ifo.data.IfoOrderCarryoverSkipProdParams.class );
    regClass(  webbroker3.ifo.data.IfoDeliveryMonthMasterPK.class );
    regClass(  webbroker3.ifo.data.IfoDeliveryMonthMasterParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "ifo_tick_values_master",
      webbroker3.ifo.data.IfoTickValuesMasterPK.class,
      webbroker3.ifo.data.IfoTickValuesMasterParams.class,
      null,
      null );
    regDbExtension( "master", "ifo_limit_price_range_master",
      webbroker3.ifo.data.IfoLimitPriceRangeMasterPK.class,
      webbroker3.ifo.data.IfoLimitPriceRangeMasterParams.class,
      null,
      null );
    regDbExtension( "master", "ifo_index_master",
      webbroker3.ifo.data.IfoIndexMasterPK.class,
      webbroker3.ifo.data.IfoIndexMasterParams.class,
      null,
      null );
    regDbExtension( "master", "ifo_order_carryover_skip_prod",
      webbroker3.ifo.data.IfoOrderCarryoverSkipProdPK.class,
      webbroker3.ifo.data.IfoOrderCarryoverSkipProdParams.class,
      null,
      null );
    regDbExtension( "master", "ifo_delivery_month_master",
      webbroker3.ifo.data.IfoDeliveryMonthMasterPK.class,
      webbroker3.ifo.data.IfoDeliveryMonthMasterParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.ifo.data.IfoTickValuesMasterRow.class,
        webbroker3.ifo.data.IfoTickValuesMasterDao.FACTORY );
    regDao(
        webbroker3.ifo.data.IfoLimitPriceRangeMasterRow.class,
        webbroker3.ifo.data.IfoLimitPriceRangeMasterDao.FACTORY );
    regDao(
        webbroker3.ifo.data.IfoIndexMasterRow.class,
        webbroker3.ifo.data.IfoIndexMasterDao.FACTORY );
    regDao(
        webbroker3.ifo.data.IfoOrderCarryoverSkipProdRow.class,
        webbroker3.ifo.data.IfoOrderCarryoverSkipProdDao.FACTORY );
    regDao(
        webbroker3.ifo.data.IfoDeliveryMonthMasterRow.class,
        webbroker3.ifo.data.IfoDeliveryMonthMasterDao.FACTORY );
  }

}
@
