head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.44.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.stdimpls.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3QuoteSessionDatabaseExtensions extends Plugin {

  private WEB3QuoteSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3QuoteSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.quoteadaptor.stdimpls.data.QuoteStatusPK.class );
    regClass(  webbroker3.quoteadaptor.stdimpls.data.QuoteStatusParams.class );
    regClass(  webbroker3.quoteadaptor.stdimpls.data.QuoteMonitorProductPK.class );
    regClass(  webbroker3.quoteadaptor.stdimpls.data.QuoteMonitorProductParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "quote_status",
      webbroker3.quoteadaptor.stdimpls.data.QuoteStatusPK.class,
      webbroker3.quoteadaptor.stdimpls.data.QuoteStatusParams.class,
      null,
      null );
    regDbExtension( "session", "quote_monitor_product",
      webbroker3.quoteadaptor.stdimpls.data.QuoteMonitorProductPK.class,
      webbroker3.quoteadaptor.stdimpls.data.QuoteMonitorProductParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.quoteadaptor.stdimpls.data.QuoteStatusRow.class,
        webbroker3.quoteadaptor.stdimpls.data.QuoteStatusDao.FACTORY );
    regDao(
        webbroker3.quoteadaptor.stdimpls.data.QuoteMonitorProductRow.class,
        webbroker3.quoteadaptor.stdimpls.data.QuoteMonitorProductDao.FACTORY );
  }

}
@
