head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.57.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	WEB3InformMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3InformMasterDatabaseExtensions extends Plugin {

  private WEB3InformMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3InformMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.inform.data.InformCtrlItemMasterPK.class );
    regClass(  webbroker3.inform.data.InformCtrlItemMasterParams.class );
    regClass(  webbroker3.inform.data.InformCtrlItemAttributePK.class );
    regClass(  webbroker3.inform.data.InformCtrlItemAttributeParams.class );
    regClass(  webbroker3.inform.data.InformDlFormMasterPK.class );
    regClass(  webbroker3.inform.data.InformDlFormMasterParams.class );
    regClass(  webbroker3.inform.data.InformDivPreferencesPK.class );
    regClass(  webbroker3.inform.data.InformDivPreferencesParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "inform_ctrl_item_master",
      webbroker3.inform.data.InformCtrlItemMasterPK.class,
      webbroker3.inform.data.InformCtrlItemMasterParams.class,
      null,
      null );
    regDbExtension( "master", "inform_ctrl_item_attribute",
      webbroker3.inform.data.InformCtrlItemAttributePK.class,
      webbroker3.inform.data.InformCtrlItemAttributeParams.class,
      null,
      null );
    regDbExtension( "master", "inform_dl_form_master",
      webbroker3.inform.data.InformDlFormMasterPK.class,
      webbroker3.inform.data.InformDlFormMasterParams.class,
      null,
      null );
    regDbExtension( "master", "inform_div_preferences",
      webbroker3.inform.data.InformDivPreferencesPK.class,
      webbroker3.inform.data.InformDivPreferencesParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.inform.data.InformCtrlItemMasterRow.class,
        webbroker3.inform.data.InformCtrlItemMasterDao.FACTORY );
    regDao(
        webbroker3.inform.data.InformCtrlItemAttributeRow.class,
        webbroker3.inform.data.InformCtrlItemAttributeDao.FACTORY );
    regDao(
        webbroker3.inform.data.InformDlFormMasterRow.class,
        webbroker3.inform.data.InformDlFormMasterDao.FACTORY );
    regDao(
        webbroker3.inform.data.InformDivPreferencesRow.class,
        webbroker3.inform.data.InformDivPreferencesDao.FACTORY );
  }

}
@
