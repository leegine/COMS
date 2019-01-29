head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DirsecMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3DirsecMasterDatabaseExtensions extends Plugin {

  private WEB3DirsecMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3DirsecMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.dirsec.data.SubmitTriggerInfoPK.class );
    regClass(  webbroker3.dirsec.data.SubmitTriggerInfoParams.class );
    regClass(  webbroker3.dirsec.data.ApManagementPK.class );
    regClass(  webbroker3.dirsec.data.ApManagementParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "submit_trigger_info",
      webbroker3.dirsec.data.SubmitTriggerInfoPK.class,
      webbroker3.dirsec.data.SubmitTriggerInfoParams.class,
      null,
      null );
    regDbExtension( "master", "ap_management",
      webbroker3.dirsec.data.ApManagementPK.class,
      webbroker3.dirsec.data.ApManagementParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.dirsec.data.SubmitTriggerInfoRow.class,
        webbroker3.dirsec.data.SubmitTriggerInfoDao.FACTORY );
    regDao(
        webbroker3.dirsec.data.ApManagementRow.class,
        webbroker3.dirsec.data.ApManagementDao.FACTORY );
  }

}
@
