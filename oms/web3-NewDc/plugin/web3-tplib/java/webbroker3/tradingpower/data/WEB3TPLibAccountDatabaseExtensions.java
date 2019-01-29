head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPLibAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3TPLibAccountDatabaseExtensions extends Plugin {

  private WEB3TPLibAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3TPLibAccountDatabaseExtensions.class );
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
    regClass(  webbroker3.tradingpower.data.TpCalcResultEquityPK.class );
    regClass(  webbroker3.tradingpower.data.TpCalcResultEquityParams.class );
    regClass(  webbroker3.tradingpower.data.TpCalcResultEquityDetailPK.class );
    regClass(  webbroker3.tradingpower.data.TpCalcResultEquityDetailParams.class );
    regClass(  webbroker3.tradingpower.data.TpCalcResultMarginPK.class );
    regClass(  webbroker3.tradingpower.data.TpCalcResultMarginParams.class );
    regClass(  webbroker3.tradingpower.data.TpCalcResultMarginDetailPK.class );
    regClass(  webbroker3.tradingpower.data.TpCalcResultMarginDetailParams.class );
    regClass(  webbroker3.tradingpower.data.OrixTpCalcResultEquityPK.class );
    regClass(  webbroker3.tradingpower.data.OrixTpCalcResultEquityParams.class );
    regClass(  webbroker3.tradingpower.data.OrixTpCalcResultMarginPK.class );
    regClass(  webbroker3.tradingpower.data.OrixTpCalcResultMarginParams.class );
    regClass(  webbroker3.tradingpower.data.PaymentRequisitMngPK.class );
    regClass(  webbroker3.tradingpower.data.PaymentRequisitMngParams.class );
    regClass(  webbroker3.tradingpower.data.TpCashBalanceFrgnPK.class );
    regClass(  webbroker3.tradingpower.data.TpCashBalanceFrgnParams.class );
    regClass(  webbroker3.tradingpower.data.TpOtherTempRestraintParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "tp_calc_result_equity",
      webbroker3.tradingpower.data.TpCalcResultEquityPK.class,
      webbroker3.tradingpower.data.TpCalcResultEquityParams.class,
      null,
      null );
    regDbExtension( "account", "tp_calc_result_equity_detail",
      webbroker3.tradingpower.data.TpCalcResultEquityDetailPK.class,
      webbroker3.tradingpower.data.TpCalcResultEquityDetailParams.class,
      null,
      null );
    regDbExtension( "account", "tp_calc_result_margin",
      webbroker3.tradingpower.data.TpCalcResultMarginPK.class,
      webbroker3.tradingpower.data.TpCalcResultMarginParams.class,
      null,
      null );
    regDbExtension( "account", "tp_calc_result_margin_detail",
      webbroker3.tradingpower.data.TpCalcResultMarginDetailPK.class,
      webbroker3.tradingpower.data.TpCalcResultMarginDetailParams.class,
      null,
      null );
    regDbExtension( "account", "orix_tp_calc_result_equity",
      webbroker3.tradingpower.data.OrixTpCalcResultEquityPK.class,
      webbroker3.tradingpower.data.OrixTpCalcResultEquityParams.class,
      null,
      null );
    regDbExtension( "account", "orix_tp_calc_result_margin",
      webbroker3.tradingpower.data.OrixTpCalcResultMarginPK.class,
      webbroker3.tradingpower.data.OrixTpCalcResultMarginParams.class,
      null,
      null );
    regDbExtension( "account", "payment_requisit_mng",
      webbroker3.tradingpower.data.PaymentRequisitMngPK.class,
      webbroker3.tradingpower.data.PaymentRequisitMngParams.class,
      null,
      null );
    regDbExtension( "account", "tp_cash_balance_frgn",
      webbroker3.tradingpower.data.TpCashBalanceFrgnPK.class,
      webbroker3.tradingpower.data.TpCashBalanceFrgnParams.class,
      null,
      null );
    regDbExtension( "account", "tp_other_temp_restraint",
      null,
      webbroker3.tradingpower.data.TpOtherTempRestraintParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.tradingpower.data.TpCalcResultEquityRow.class,
        webbroker3.tradingpower.data.TpCalcResultEquityDao.FACTORY );
    regDao(
        webbroker3.tradingpower.data.TpCalcResultEquityDetailRow.class,
        webbroker3.tradingpower.data.TpCalcResultEquityDetailDao.FACTORY );
    regDao(
        webbroker3.tradingpower.data.TpCalcResultMarginRow.class,
        webbroker3.tradingpower.data.TpCalcResultMarginDao.FACTORY );
    regDao(
        webbroker3.tradingpower.data.TpCalcResultMarginDetailRow.class,
        webbroker3.tradingpower.data.TpCalcResultMarginDetailDao.FACTORY );
    regDao(
        webbroker3.tradingpower.data.OrixTpCalcResultEquityRow.class,
        webbroker3.tradingpower.data.OrixTpCalcResultEquityDao.FACTORY );
    regDao(
        webbroker3.tradingpower.data.OrixTpCalcResultMarginRow.class,
        webbroker3.tradingpower.data.OrixTpCalcResultMarginDao.FACTORY );
    regDao(
        webbroker3.tradingpower.data.PaymentRequisitMngRow.class,
        webbroker3.tradingpower.data.PaymentRequisitMngDao.FACTORY );
    regDao(
        webbroker3.tradingpower.data.TpCashBalanceFrgnRow.class,
        webbroker3.tradingpower.data.TpCashBalanceFrgnDao.FACTORY );
    regDao(
        webbroker3.tradingpower.data.TpOtherTempRestraintRow.class,
        webbroker3.tradingpower.data.TpOtherTempRestraintDao.FACTORY );
  }

}
@
