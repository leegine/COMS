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
filename	WEB3DirsecSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3DirsecSessionDatabaseExtensions extends Plugin {

  private WEB3DirsecSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3DirsecSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.dirsec.data.HostManagementPK.class );
    regClass(  webbroker3.dirsec.data.HostManagementParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "host_management",
      webbroker3.dirsec.data.HostManagementPK.class,
      webbroker3.dirsec.data.HostManagementParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.dirsec.data.HostManagementRow.class,
        webbroker3.dirsec.data.HostManagementDao.FACTORY );
  }

}
@
