head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteProtoSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.prototype.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3QuoteProtoSessionDatabaseExtensions extends Plugin {

  private WEB3QuoteProtoSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3QuoteProtoSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoPK.class );
    regClass(  webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "web3_quote_proto",
      webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoPK.class,
      webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoRow.class,
        webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoDao.FACTORY );
  }

}
@
