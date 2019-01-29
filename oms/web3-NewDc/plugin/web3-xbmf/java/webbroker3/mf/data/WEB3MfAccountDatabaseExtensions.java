head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MfAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3MfAccountDatabaseExtensions extends Plugin {

  private WEB3MfAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3MfAccountDatabaseExtensions.class );
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
    regClass(  webbroker3.mf.data.MfSubAssetPK.class );
    regClass(  webbroker3.mf.data.MfSubAssetParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "mf_sub_asset",
      webbroker3.mf.data.MfSubAssetPK.class,
      webbroker3.mf.data.MfSubAssetParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.mf.data.MfSubAssetRow.class,
        webbroker3.mf.data.MfSubAssetDao.FACTORY );
  }

}
@
