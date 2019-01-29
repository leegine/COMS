head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminTriggerorderSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.admintriggerorder.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3AdminTriggerorderSessionDatabaseExtensions extends Plugin {

  private WEB3AdminTriggerorderSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3AdminTriggerorderSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.admintriggerorder.data.AdmintoIfoOrderUnitPK.class );
    regClass(  webbroker3.admintriggerorder.data.AdmintoIfoOrderUnitParams.class );
    regClass(  webbroker3.admintriggerorder.data.AdmintoEqtypeOrderUnitPK.class );
    regClass(  webbroker3.admintriggerorder.data.AdmintoEqtypeOrderUnitParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "adminto_ifo_order_unit",
      webbroker3.admintriggerorder.data.AdmintoIfoOrderUnitPK.class,
      webbroker3.admintriggerorder.data.AdmintoIfoOrderUnitParams.class,
      null,
      null );
    regDbExtension( "session", "adminto_eqtype_order_unit",
      webbroker3.admintriggerorder.data.AdmintoEqtypeOrderUnitPK.class,
      webbroker3.admintriggerorder.data.AdmintoEqtypeOrderUnitParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.admintriggerorder.data.AdmintoIfoOrderUnitRow.class,
        webbroker3.admintriggerorder.data.AdmintoIfoOrderUnitDao.FACTORY );
    regDao(
        webbroker3.admintriggerorder.data.AdmintoEqtypeOrderUnitRow.class,
        webbroker3.admintriggerorder.data.AdmintoEqtypeOrderUnitDao.FACTORY );
  }

}
@
