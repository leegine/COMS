head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3AccInfoMasterDatabaseExtensions extends Plugin {

  private WEB3AccInfoMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3AccInfoMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.accountinfo.data.CommissionCourseMasterPK.class );
    regClass(  webbroker3.accountinfo.data.CommissionCourseMasterParams.class );
    regClass(  webbroker3.accountinfo.data.InformationMailRequestPK.class );
    regClass(  webbroker3.accountinfo.data.InformationMailRequestParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "commission_course_master",
      webbroker3.accountinfo.data.CommissionCourseMasterPK.class,
      webbroker3.accountinfo.data.CommissionCourseMasterParams.class,
      null,
      null );
    regDbExtension( "master", "information_mail_request",
      webbroker3.accountinfo.data.InformationMailRequestPK.class,
      webbroker3.accountinfo.data.InformationMailRequestParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.accountinfo.data.CommissionCourseMasterRow.class,
        webbroker3.accountinfo.data.CommissionCourseMasterDao.FACTORY );
    regDao(
        webbroker3.accountinfo.data.InformationMailRequestRow.class,
        webbroker3.accountinfo.data.InformationMailRequestDao.FACTORY );
  }

}
@
