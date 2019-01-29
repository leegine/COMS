head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MfMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3MfMasterDatabaseExtensions extends Plugin {

  private WEB3MfMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3MfMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.mf.data.MutualFundProductCategoryPK.class );
    regClass(  webbroker3.mf.data.MutualFundProductCategoryParams.class );
    regClass(  webbroker3.mf.data.MutualFund2ndProductSonarPK.class );
    regClass(  webbroker3.mf.data.MutualFund2ndProductSonarParams.class );
    regClass(  webbroker3.mf.data.MutualFundProductSonarPK.class );
    regClass(  webbroker3.mf.data.MutualFundProductSonarParams.class );
    regClass(  webbroker3.mf.data.MutualFundInstCondSonarPK.class );
    regClass(  webbroker3.mf.data.MutualFundInstCondSonarParams.class );
    regClass(  webbroker3.mf.data.MutualFundInstCommissionPK.class );
    regClass(  webbroker3.mf.data.MutualFundInstCommissionParams.class );
    regClass(  webbroker3.mf.data.MfExemptionAccountPK.class );
    regClass(  webbroker3.mf.data.MfExemptionAccountParams.class );
    regClass(  webbroker3.mf.data.MutualFundFrgncalPK.class );
    regClass(  webbroker3.mf.data.MutualFundFrgncalParams.class );
    regClass(  webbroker3.mf.data.MutualFundDayBalanceParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "mutual_fund_product_category",
      webbroker3.mf.data.MutualFundProductCategoryPK.class,
      webbroker3.mf.data.MutualFundProductCategoryParams.class,
      null,
      null );
    regDbExtension( "master", "mutual_fund_2nd_product_sonar",
      webbroker3.mf.data.MutualFund2ndProductSonarPK.class,
      webbroker3.mf.data.MutualFund2ndProductSonarParams.class,
      null,
      null );
    regDbExtension( "master", "mutual_fund_product_sonar",
      webbroker3.mf.data.MutualFundProductSonarPK.class,
      webbroker3.mf.data.MutualFundProductSonarParams.class,
      null,
      null );
    regDbExtension( "master", "mutual_fund_inst_cond_sonar",
      webbroker3.mf.data.MutualFundInstCondSonarPK.class,
      webbroker3.mf.data.MutualFundInstCondSonarParams.class,
      null,
      null );
    regDbExtension( "master", "mutual_fund_inst_commission",
      webbroker3.mf.data.MutualFundInstCommissionPK.class,
      webbroker3.mf.data.MutualFundInstCommissionParams.class,
      null,
      null );
    regDbExtension( "master", "mf_exemption_account",
      webbroker3.mf.data.MfExemptionAccountPK.class,
      webbroker3.mf.data.MfExemptionAccountParams.class,
      null,
      null );
    regDbExtension( "master", "mutual_fund_frgncal",
      webbroker3.mf.data.MutualFundFrgncalPK.class,
      webbroker3.mf.data.MutualFundFrgncalParams.class,
      null,
      null );
    regDbExtension( "master", "mutual_fund_day_balance",
      null,
      webbroker3.mf.data.MutualFundDayBalanceParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.mf.data.MutualFundProductCategoryRow.class,
        webbroker3.mf.data.MutualFundProductCategoryDao.FACTORY );
    regDao(
        webbroker3.mf.data.MutualFund2ndProductSonarRow.class,
        webbroker3.mf.data.MutualFund2ndProductSonarDao.FACTORY );
    regDao(
        webbroker3.mf.data.MutualFundProductSonarRow.class,
        webbroker3.mf.data.MutualFundProductSonarDao.FACTORY );
    regDao(
        webbroker3.mf.data.MutualFundInstCondSonarRow.class,
        webbroker3.mf.data.MutualFundInstCondSonarDao.FACTORY );
    regDao(
        webbroker3.mf.data.MutualFundInstCommissionRow.class,
        webbroker3.mf.data.MutualFundInstCommissionDao.FACTORY );
    regDao(
        webbroker3.mf.data.MfExemptionAccountRow.class,
        webbroker3.mf.data.MfExemptionAccountDao.FACTORY );
    regDao(
        webbroker3.mf.data.MutualFundFrgncalRow.class,
        webbroker3.mf.data.MutualFundFrgncalDao.FACTORY );
    regDao(
        webbroker3.mf.data.MutualFundDayBalanceRow.class,
        webbroker3.mf.data.MutualFundDayBalanceDao.FACTORY );
  }

}
@
