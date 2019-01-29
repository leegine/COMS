head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.15.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3AccOpenMasterDatabaseExtensions extends Plugin {

  private WEB3AccOpenMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3AccOpenMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.accountopen.data.AccOpenItemMasterPK.class );
    regClass(  webbroker3.accountopen.data.AccOpenItemMasterParams.class );
    regClass(  webbroker3.accountopen.data.AccOpenItemAttributePK.class );
    regClass(  webbroker3.accountopen.data.AccOpenItemAttributeParams.class );
    regClass(  webbroker3.accountopen.data.AccOpenDlFormMasterPK.class );
    regClass(  webbroker3.accountopen.data.AccOpenDlFormMasterParams.class );
    regClass(  webbroker3.accountopen.data.AccOpenWaitingPK.class );
    regClass(  webbroker3.accountopen.data.AccOpenWaitingParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "acc_open_item_master",
      webbroker3.accountopen.data.AccOpenItemMasterPK.class,
      webbroker3.accountopen.data.AccOpenItemMasterParams.class,
      null,
      null );
    regDbExtension( "master", "acc_open_item_attribute",
      webbroker3.accountopen.data.AccOpenItemAttributePK.class,
      webbroker3.accountopen.data.AccOpenItemAttributeParams.class,
      null,
      null );
    regDbExtension( "master", "acc_open_dl_form_master",
      webbroker3.accountopen.data.AccOpenDlFormMasterPK.class,
      webbroker3.accountopen.data.AccOpenDlFormMasterParams.class,
      null,
      null );
    regDbExtension( "master", "acc_open_waiting",
      webbroker3.accountopen.data.AccOpenWaitingPK.class,
      webbroker3.accountopen.data.AccOpenWaitingParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.accountopen.data.AccOpenItemMasterRow.class,
        webbroker3.accountopen.data.AccOpenItemMasterDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.AccOpenItemAttributeRow.class,
        webbroker3.accountopen.data.AccOpenItemAttributeDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.AccOpenDlFormMasterRow.class,
        webbroker3.accountopen.data.AccOpenDlFormMasterDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.AccOpenWaitingRow.class,
        webbroker3.accountopen.data.AccOpenWaitingDao.FACTORY );
  }

}
@
