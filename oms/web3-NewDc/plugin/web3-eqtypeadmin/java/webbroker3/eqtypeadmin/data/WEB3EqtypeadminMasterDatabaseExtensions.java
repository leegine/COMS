head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EqtypeadminMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3EqtypeadminMasterDatabaseExtensions extends Plugin {

  private WEB3EqtypeadminMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3EqtypeadminMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.eqtypeadmin.data.EqtypeProductConditionPK.class );
    regClass(  webbroker3.eqtypeadmin.data.EqtypeProductConditionParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "eqtype_product_condition",
      webbroker3.eqtypeadmin.data.EqtypeProductConditionPK.class,
      webbroker3.eqtypeadmin.data.EqtypeProductConditionParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.eqtypeadmin.data.EqtypeProductConditionRow.class,
        webbroker3.eqtypeadmin.data.EqtypeProductConditionDao.FACTORY );
  }

}
@
