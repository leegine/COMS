head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3FeqMasterDatabaseExtensions extends Plugin {

  private WEB3FeqMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3FeqMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.feq.data.FeqTickValuesMstPK.class );
    regClass(  webbroker3.feq.data.FeqTickValuesMstParams.class );
    regClass(  webbroker3.feq.data.FeqLimitPriceRangeMstPK.class );
    regClass(  webbroker3.feq.data.FeqLimitPriceRangeMstParams.class );
    regClass(  webbroker3.feq.data.FeqOrderexecutionEndPK.class );
    regClass(  webbroker3.feq.data.FeqOrderexecutionEndParams.class );
    regClass(  webbroker3.feq.data.ForeignCostPK.class );
    regClass(  webbroker3.feq.data.ForeignCostParams.class );
    regClass(  webbroker3.feq.data.SpecialProductForeignCostPK.class );
    regClass(  webbroker3.feq.data.SpecialProductForeignCostParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "feq_tick_values_mst",
      webbroker3.feq.data.FeqTickValuesMstPK.class,
      webbroker3.feq.data.FeqTickValuesMstParams.class,
      null,
      null );
    regDbExtension( "master", "feq_limit_price_range_mst",
      webbroker3.feq.data.FeqLimitPriceRangeMstPK.class,
      webbroker3.feq.data.FeqLimitPriceRangeMstParams.class,
      null,
      null );
    regDbExtension( "master", "feq_orderexecution_end",
      webbroker3.feq.data.FeqOrderexecutionEndPK.class,
      webbroker3.feq.data.FeqOrderexecutionEndParams.class,
      null,
      null );
    regDbExtension( "master", "foreign_cost",
      webbroker3.feq.data.ForeignCostPK.class,
      webbroker3.feq.data.ForeignCostParams.class,
      null,
      null );
    regDbExtension( "master", "special_product_foreign_cost",
      webbroker3.feq.data.SpecialProductForeignCostPK.class,
      webbroker3.feq.data.SpecialProductForeignCostParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.feq.data.FeqTickValuesMstRow.class,
        webbroker3.feq.data.FeqTickValuesMstDao.FACTORY );
    regDao(
        webbroker3.feq.data.FeqLimitPriceRangeMstRow.class,
        webbroker3.feq.data.FeqLimitPriceRangeMstDao.FACTORY );
    regDao(
        webbroker3.feq.data.FeqOrderexecutionEndRow.class,
        webbroker3.feq.data.FeqOrderexecutionEndDao.FACTORY );
    regDao(
        webbroker3.feq.data.ForeignCostRow.class,
        webbroker3.feq.data.ForeignCostDao.FACTORY );
    regDao(
        webbroker3.feq.data.SpecialProductForeignCostRow.class,
        webbroker3.feq.data.SpecialProductForeignCostDao.FACTORY );
  }

}
@
