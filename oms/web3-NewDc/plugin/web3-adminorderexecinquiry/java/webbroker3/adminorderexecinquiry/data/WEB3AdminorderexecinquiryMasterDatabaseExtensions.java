head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminorderexecinquiryMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.adminorderexecinquiry.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3AdminorderexecinquiryMasterDatabaseExtensions extends Plugin {

  private WEB3AdminorderexecinquiryMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3AdminorderexecinquiryMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.adminorderexecinquiry.data.OrderExecutedCountPK.class );
    regClass(  webbroker3.adminorderexecinquiry.data.OrderExecutedCountParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "order_executed_count",
      webbroker3.adminorderexecinquiry.data.OrderExecutedCountPK.class,
      webbroker3.adminorderexecinquiry.data.OrderExecutedCountParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.adminorderexecinquiry.data.OrderExecutedCountRow.class,
        webbroker3.adminorderexecinquiry.data.OrderExecutedCountDao.FACTORY );
  }

}
@
