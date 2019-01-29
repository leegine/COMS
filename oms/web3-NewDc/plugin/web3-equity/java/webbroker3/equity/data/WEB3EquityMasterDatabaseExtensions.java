head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3EquityMasterDatabaseExtensions extends Plugin {

  private WEB3EquityMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3EquityMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.equity.data.EquityTickValuesMstPK.class );
    regClass(  webbroker3.equity.data.EquityTickValuesMstParams.class );
    regClass(  webbroker3.equity.data.EquityLimitPriceRangeMstPK.class );
    regClass(  webbroker3.equity.data.EquityLimitPriceRangeMstParams.class );
    regClass(  webbroker3.equity.data.OrderCarryoverSkipProdPK.class );
    regClass(  webbroker3.equity.data.OrderCarryoverSkipProdParams.class );
    regClass(  webbroker3.equity.data.OffFloorOrderProductPK.class );
    regClass(  webbroker3.equity.data.OffFloorOrderProductParams.class );
    regClass(  webbroker3.equity.data.ShortSellingRestraintTimePK.class );
    regClass(  webbroker3.equity.data.ShortSellingRestraintTimeParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "equity_tick_values_mst",
      webbroker3.equity.data.EquityTickValuesMstPK.class,
      webbroker3.equity.data.EquityTickValuesMstParams.class,
      null,
      null );
    regDbExtension( "master", "equity_limit_price_range_mst",
      webbroker3.equity.data.EquityLimitPriceRangeMstPK.class,
      webbroker3.equity.data.EquityLimitPriceRangeMstParams.class,
      null,
      null );
    regDbExtension( "master", "order_carryover_skip_prod",
      webbroker3.equity.data.OrderCarryoverSkipProdPK.class,
      webbroker3.equity.data.OrderCarryoverSkipProdParams.class,
      null,
      null );
    regDbExtension( "master", "off_floor_order_product",
      webbroker3.equity.data.OffFloorOrderProductPK.class,
      webbroker3.equity.data.OffFloorOrderProductParams.class,
      null,
      null );
    regDbExtension( "master", "short_selling_restraint_time",
      webbroker3.equity.data.ShortSellingRestraintTimePK.class,
      webbroker3.equity.data.ShortSellingRestraintTimeParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.equity.data.EquityTickValuesMstRow.class,
        webbroker3.equity.data.EquityTickValuesMstDao.FACTORY );
    regDao(
        webbroker3.equity.data.EquityLimitPriceRangeMstRow.class,
        webbroker3.equity.data.EquityLimitPriceRangeMstDao.FACTORY );
    regDao(
        webbroker3.equity.data.OrderCarryoverSkipProdRow.class,
        webbroker3.equity.data.OrderCarryoverSkipProdDao.FACTORY );
    regDao(
        webbroker3.equity.data.OffFloorOrderProductRow.class,
        webbroker3.equity.data.OffFloorOrderProductDao.FACTORY );
    regDao(
        webbroker3.equity.data.ShortSellingRestraintTimeRow.class,
        webbroker3.equity.data.ShortSellingRestraintTimeDao.FACTORY );
  }

}
@
