head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPLibMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3TPLibMasterDatabaseExtensions extends Plugin {

  private WEB3TPLibMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3TPLibMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.tradingpower.data.EqtypeFixedContractPK.class );
    regClass(  webbroker3.tradingpower.data.EqtypeFixedContractParams.class );
    regClass(  webbroker3.tradingpower.data.FixedFinTransactionPK.class );
    regClass(  webbroker3.tradingpower.data.FixedFinTransactionParams.class );
    regClass(  webbroker3.tradingpower.data.TpCashBalancePK.class );
    regClass(  webbroker3.tradingpower.data.TpCashBalanceParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "eqtype_fixed_contract",
      webbroker3.tradingpower.data.EqtypeFixedContractPK.class,
      webbroker3.tradingpower.data.EqtypeFixedContractParams.class,
      null,
      null );
    regDbExtension( "master", "fixed_fin_transaction",
      webbroker3.tradingpower.data.FixedFinTransactionPK.class,
      webbroker3.tradingpower.data.FixedFinTransactionParams.class,
      null,
      null );
    regDbExtension( "master", "tp_cash_balance",
      webbroker3.tradingpower.data.TpCashBalancePK.class,
      webbroker3.tradingpower.data.TpCashBalanceParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.tradingpower.data.EqtypeFixedContractRow.class,
        webbroker3.tradingpower.data.EqtypeFixedContractDao.FACTORY );
    regDao(
        webbroker3.tradingpower.data.FixedFinTransactionRow.class,
        webbroker3.tradingpower.data.FixedFinTransactionDao.FACTORY );
    regDao(
        webbroker3.tradingpower.data.TpCashBalanceRow.class,
        webbroker3.tradingpower.data.TpCashBalanceDao.FACTORY );
  }

}
@
