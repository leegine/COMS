head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.35.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	GenTradeMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class GenTradeMasterDatabaseExtensions extends Plugin {

  private GenTradeMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( GenTradeMasterDatabaseExtensions.class );
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
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.LimitPriceRangeDefsPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.LimitPriceRangeDefsParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketCalendarPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketCalendarParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.TickValuesDefsPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.TickValuesDefsParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqParams.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderPK.class );
    regClass(  com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "branch",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams.class,
      null,
      null );
    regDbExtension( "master", "institution",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams.class,
      null,
      null );
    regDbExtension( "master", "limit_price_range_defs",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.LimitPriceRangeDefsPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.LimitPriceRangeDefsParams.class,
      null,
      null );
    regDbExtension( "master", "main_account",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams.class,
      null,
      null );
    regDbExtension( "master", "market",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams.class,
      null,
      null );
    regDbExtension( "master", "market_calendar",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketCalendarPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketCalendarParams.class,
      null,
      null );
    regDbExtension( "master", "market_preferences",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams.class,
      null,
      null );
    regDbExtension( "master", "product",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams.class,
      null,
      null );
    regDbExtension( "master", "system_preferences",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams.class,
      null,
      null );
    regDbExtension( "master", "tick_values_defs",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.TickValuesDefsPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.TickValuesDefsParams.class,
      null,
      null );
    regDbExtension( "master", "traded_product",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams.class,
      null,
      null );
    regDbExtension( "master", "traded_product_calendar",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarParams.class,
      null,
      null );
    regDbExtension( "master", "traded_product_updq",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqParams.class,
      null,
      null );
    regDbExtension( "master", "trader",
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderPK.class,
      com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.LimitPriceRangeDefsRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.LimitPriceRangeDefsDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketCalendarRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketCalendarDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TickValuesDefsRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TickValuesDefsDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqDao.FACTORY );
    regDao(
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow.class,
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderDao.FACTORY );
  }

}
@
