head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quotecheck.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3QuoteCheckSessionDatabaseExtensions extends Plugin {

  private WEB3QuoteCheckSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3QuoteCheckSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.quotecheck.data.PrimaryMarketCskPK.class );
    regClass(  webbroker3.quotecheck.data.PrimaryMarketCskParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "primary_market_csk",
      webbroker3.quotecheck.data.PrimaryMarketCskPK.class,
      webbroker3.quotecheck.data.PrimaryMarketCskParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.quotecheck.data.PrimaryMarketCskRow.class,
        webbroker3.quotecheck.data.PrimaryMarketCskDao.FACTORY );
  }

}
@
