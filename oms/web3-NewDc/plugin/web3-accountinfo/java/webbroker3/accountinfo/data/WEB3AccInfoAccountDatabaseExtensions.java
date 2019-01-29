head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3AccInfoAccountDatabaseExtensions extends Plugin {

  private WEB3AccInfoAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3AccInfoAccountDatabaseExtensions.class );
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
    regClass(  webbroker3.accountinfo.data.CommissionCourseRegistPK.class );
    regClass(  webbroker3.accountinfo.data.CommissionCourseRegistParams.class );
    regClass(  webbroker3.accountinfo.data.MobileOfficeInfoRegistPK.class );
    regClass(  webbroker3.accountinfo.data.MobileOfficeInfoRegistParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "commission_course_regist",
      webbroker3.accountinfo.data.CommissionCourseRegistPK.class,
      webbroker3.accountinfo.data.CommissionCourseRegistParams.class,
      null,
      null );
    regDbExtension( "account", "mobile_office_info_regist",
      webbroker3.accountinfo.data.MobileOfficeInfoRegistPK.class,
      webbroker3.accountinfo.data.MobileOfficeInfoRegistParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.accountinfo.data.CommissionCourseRegistRow.class,
        webbroker3.accountinfo.data.CommissionCourseRegistDao.FACTORY );
    regDao(
        webbroker3.accountinfo.data.MobileOfficeInfoRegistRow.class,
        webbroker3.accountinfo.data.MobileOfficeInfoRegistDao.FACTORY );
  }

}
@
