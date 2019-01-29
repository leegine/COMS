head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.50.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3trialcalcAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.trialcalc.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3trialcalcAccountDatabaseExtensions extends Plugin {

  private WEB3trialcalcAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3trialcalcAccountDatabaseExtensions.class );
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
    regClass(  webbroker3.trialcalc.data.AccountPortfolioPK.class );
    regClass(  webbroker3.trialcalc.data.AccountPortfolioParams.class );
    regClass(  webbroker3.trialcalc.data.AccountPortfolioProductPK.class );
    regClass(  webbroker3.trialcalc.data.AccountPortfolioProductParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "account_portfolio",
      webbroker3.trialcalc.data.AccountPortfolioPK.class,
      webbroker3.trialcalc.data.AccountPortfolioParams.class,
      null,
      null );
    regDbExtension( "account", "account_portfolio_product",
      webbroker3.trialcalc.data.AccountPortfolioProductPK.class,
      webbroker3.trialcalc.data.AccountPortfolioProductParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.trialcalc.data.AccountPortfolioRow.class,
        webbroker3.trialcalc.data.AccountPortfolioDao.FACTORY );
    regDao(
        webbroker3.trialcalc.data.AccountPortfolioProductRow.class,
        webbroker3.trialcalc.data.AccountPortfolioProductDao.FACTORY );
  }

}
@
