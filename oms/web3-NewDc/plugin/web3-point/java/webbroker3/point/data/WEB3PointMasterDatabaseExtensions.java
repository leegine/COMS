head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.50.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3PointMasterDatabaseExtensions extends Plugin {

  private WEB3PointMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3PointMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.point.data.PointCategoryMasterPK.class );
    regClass(  webbroker3.point.data.PointCategoryMasterParams.class );
    regClass(  webbroker3.point.data.PointConvertMasterPK.class );
    regClass(  webbroker3.point.data.PointConvertMasterParams.class );
    regClass(  webbroker3.point.data.PointPremiumMasterPK.class );
    regClass(  webbroker3.point.data.PointPremiumMasterParams.class );
    regClass(  webbroker3.point.data.PointTotalPK.class );
    regClass(  webbroker3.point.data.PointTotalParams.class );
    regClass(  webbroker3.point.data.PointTradeDataParams.class );
    regClass(  webbroker3.point.data.PointApplyPK.class );
    regClass(  webbroker3.point.data.PointApplyParams.class );
    regClass(  webbroker3.point.data.PointAdjustParams.class );
    regClass(  webbroker3.point.data.PointTermPK.class );
    regClass(  webbroker3.point.data.PointTermParams.class );
    regClass(  webbroker3.point.data.OrixTradeBonusPlanPK.class );
    regClass(  webbroker3.point.data.OrixTradeBonusPlanParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "point_category_master",
      webbroker3.point.data.PointCategoryMasterPK.class,
      webbroker3.point.data.PointCategoryMasterParams.class,
      null,
      null );
    regDbExtension( "master", "point_convert_master",
      webbroker3.point.data.PointConvertMasterPK.class,
      webbroker3.point.data.PointConvertMasterParams.class,
      null,
      null );
    regDbExtension( "master", "point_premium_master",
      webbroker3.point.data.PointPremiumMasterPK.class,
      webbroker3.point.data.PointPremiumMasterParams.class,
      null,
      null );
    regDbExtension( "master", "point_total",
      webbroker3.point.data.PointTotalPK.class,
      webbroker3.point.data.PointTotalParams.class,
      null,
      null );
    regDbExtension( "master", "point_trade_data",
      null,
      webbroker3.point.data.PointTradeDataParams.class,
      null,
      null );
    regDbExtension( "master", "point_apply",
      webbroker3.point.data.PointApplyPK.class,
      webbroker3.point.data.PointApplyParams.class,
      null,
      null );
    regDbExtension( "master", "point_adjust",
      null,
      webbroker3.point.data.PointAdjustParams.class,
      null,
      null );
    regDbExtension( "master", "point_term",
      webbroker3.point.data.PointTermPK.class,
      webbroker3.point.data.PointTermParams.class,
      null,
      null );
    regDbExtension( "master", "orix_trade_bonus_plan",
      webbroker3.point.data.OrixTradeBonusPlanPK.class,
      webbroker3.point.data.OrixTradeBonusPlanParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.point.data.PointCategoryMasterRow.class,
        webbroker3.point.data.PointCategoryMasterDao.FACTORY );
    regDao(
        webbroker3.point.data.PointConvertMasterRow.class,
        webbroker3.point.data.PointConvertMasterDao.FACTORY );
    regDao(
        webbroker3.point.data.PointPremiumMasterRow.class,
        webbroker3.point.data.PointPremiumMasterDao.FACTORY );
    regDao(
        webbroker3.point.data.PointTotalRow.class,
        webbroker3.point.data.PointTotalDao.FACTORY );
    regDao(
        webbroker3.point.data.PointTradeDataRow.class,
        webbroker3.point.data.PointTradeDataDao.FACTORY );
    regDao(
        webbroker3.point.data.PointApplyRow.class,
        webbroker3.point.data.PointApplyDao.FACTORY );
    regDao(
        webbroker3.point.data.PointAdjustRow.class,
        webbroker3.point.data.PointAdjustDao.FACTORY );
    regDao(
        webbroker3.point.data.PointTermRow.class,
        webbroker3.point.data.PointTermDao.FACTORY );
    regDao(
        webbroker3.point.data.OrixTradeBonusPlanRow.class,
        webbroker3.point.data.OrixTradeBonusPlanDao.FACTORY );
  }

}
@
