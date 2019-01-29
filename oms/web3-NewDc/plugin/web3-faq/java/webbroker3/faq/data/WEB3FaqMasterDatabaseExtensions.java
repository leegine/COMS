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
filename	WEB3FaqMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.faq.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3FaqMasterDatabaseExtensions extends Plugin {

  private WEB3FaqMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3FaqMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.faq.data.FaqNumberPK.class );
    regClass(  webbroker3.faq.data.FaqNumberParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "faq_number",
      webbroker3.faq.data.FaqNumberPK.class,
      webbroker3.faq.data.FaqNumberParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.faq.data.FaqNumberRow.class,
        webbroker3.faq.data.FaqNumberDao.FACTORY );
  }

}
@
