head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.faq.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3FaqSessionDatabaseExtensions extends Plugin {

  private WEB3FaqSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3FaqSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.faq.data.FaqPK.class );
    regClass(  webbroker3.faq.data.FaqParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "faq",
      webbroker3.faq.data.FaqPK.class,
      webbroker3.faq.data.FaqParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.faq.data.FaqRow.class,
        webbroker3.faq.data.FaqDao.FACTORY );
  }

}
@
