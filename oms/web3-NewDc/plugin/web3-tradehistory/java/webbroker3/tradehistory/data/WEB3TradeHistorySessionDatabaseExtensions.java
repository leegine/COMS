head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistorySessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3TradeHistorySessionDatabaseExtensions extends Plugin {

  private WEB3TradeHistorySessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3TradeHistorySessionDatabaseExtensions.class );
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
    regClass(  webbroker3.tradehistory.data.SettleDetailHistoryPK.class );
    regClass(  webbroker3.tradehistory.data.SettleDetailHistoryParams.class );
    regClass(  webbroker3.tradehistory.data.TransactionHistoryPK.class );
    regClass(  webbroker3.tradehistory.data.TransactionHistoryParams.class );
    regClass(  webbroker3.tradehistory.data.TradeDetailHistoryPK.class );
    regClass(  webbroker3.tradehistory.data.TradeDetailHistoryParams.class );
    regClass(  webbroker3.tradehistory.data.TradeHistoryPK.class );
    regClass(  webbroker3.tradehistory.data.TradeHistoryParams.class );
    regClass(  webbroker3.tradehistory.data.BookValueSpecPK.class );
    regClass(  webbroker3.tradehistory.data.BookValueSpecParams.class );
    regClass(  webbroker3.tradehistory.data.ProfitLossSpecPK.class );
    regClass(  webbroker3.tradehistory.data.ProfitLossSpecParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "settle_detail_history",
      webbroker3.tradehistory.data.SettleDetailHistoryPK.class,
      webbroker3.tradehistory.data.SettleDetailHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "transaction_history",
      webbroker3.tradehistory.data.TransactionHistoryPK.class,
      webbroker3.tradehistory.data.TransactionHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "trade_detail_history",
      webbroker3.tradehistory.data.TradeDetailHistoryPK.class,
      webbroker3.tradehistory.data.TradeDetailHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "trade_history",
      webbroker3.tradehistory.data.TradeHistoryPK.class,
      webbroker3.tradehistory.data.TradeHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "book_value_spec",
      webbroker3.tradehistory.data.BookValueSpecPK.class,
      webbroker3.tradehistory.data.BookValueSpecParams.class,
      null,
      null );
    regDbExtension( "session", "profit_loss_spec",
      webbroker3.tradehistory.data.ProfitLossSpecPK.class,
      webbroker3.tradehistory.data.ProfitLossSpecParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.tradehistory.data.SettleDetailHistoryRow.class,
        webbroker3.tradehistory.data.SettleDetailHistoryDao.FACTORY );
    regDao(
        webbroker3.tradehistory.data.TransactionHistoryRow.class,
        webbroker3.tradehistory.data.TransactionHistoryDao.FACTORY );
    regDao(
        webbroker3.tradehistory.data.TradeDetailHistoryRow.class,
        webbroker3.tradehistory.data.TradeDetailHistoryDao.FACTORY );
    regDao(
        webbroker3.tradehistory.data.TradeHistoryRow.class,
        webbroker3.tradehistory.data.TradeHistoryDao.FACTORY );
    regDao(
        webbroker3.tradehistory.data.BookValueSpecRow.class,
        webbroker3.tradehistory.data.BookValueSpecDao.FACTORY );
    regDao(
        webbroker3.tradehistory.data.ProfitLossSpecRow.class,
        webbroker3.tradehistory.data.ProfitLossSpecDao.FACTORY );
  }

}
@
