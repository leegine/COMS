head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.19.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3TriggerOrderMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3TriggerOrderMasterDatabaseExtensions extends Plugin {

  private WEB3TriggerOrderMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3TriggerOrderMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.triggerorder.base.data.TriggerOrderStopPK.class );
    regClass(  webbroker3.triggerorder.base.data.TriggerOrderStopParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "trigger_order_stop",
      webbroker3.triggerorder.base.data.TriggerOrderStopPK.class,
      webbroker3.triggerorder.base.data.TriggerOrderStopParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.triggerorder.base.data.TriggerOrderStopRow.class,
        webbroker3.triggerorder.base.data.TriggerOrderStopDao.FACTORY );
  }

}
@
