head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3AccInfoSessionDatabaseExtensions extends Plugin {

  private WEB3AccInfoSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3AccInfoSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.accountinfo.data.HostLockRegistPK.class );
    regClass(  webbroker3.accountinfo.data.HostLockRegistParams.class );
    regClass(  webbroker3.accountinfo.data.HostLockRegiAcceptPK.class );
    regClass(  webbroker3.accountinfo.data.HostLockRegiAcceptParams.class );
    regClass(  webbroker3.accountinfo.data.CommAccountSendPK.class );
    regClass(  webbroker3.accountinfo.data.CommAccountSendParams.class );
    regClass(  webbroker3.accountinfo.data.CommCampaignCondMstPK.class );
    regClass(  webbroker3.accountinfo.data.CommCampaignCondMstParams.class );
    regClass(  webbroker3.accountinfo.data.CommCampaignProductMstPK.class );
    regClass(  webbroker3.accountinfo.data.CommCampaignProductMstParams.class );
    regClass(  webbroker3.accountinfo.data.CommCampaignAccHistoryPK.class );
    regClass(  webbroker3.accountinfo.data.CommCampaignAccHistoryParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "host_lock_regist",
      webbroker3.accountinfo.data.HostLockRegistPK.class,
      webbroker3.accountinfo.data.HostLockRegistParams.class,
      null,
      null );
    regDbExtension( "session", "host_lock_regi_accept",
      webbroker3.accountinfo.data.HostLockRegiAcceptPK.class,
      webbroker3.accountinfo.data.HostLockRegiAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "comm_account_send",
      webbroker3.accountinfo.data.CommAccountSendPK.class,
      webbroker3.accountinfo.data.CommAccountSendParams.class,
      null,
      null );
    regDbExtension( "session", "comm_campaign_cond_mst",
      webbroker3.accountinfo.data.CommCampaignCondMstPK.class,
      webbroker3.accountinfo.data.CommCampaignCondMstParams.class,
      null,
      null );
    regDbExtension( "session", "comm_campaign_product_mst",
      webbroker3.accountinfo.data.CommCampaignProductMstPK.class,
      webbroker3.accountinfo.data.CommCampaignProductMstParams.class,
      null,
      null );
    regDbExtension( "session", "comm_campaign_acc_history",
      webbroker3.accountinfo.data.CommCampaignAccHistoryPK.class,
      webbroker3.accountinfo.data.CommCampaignAccHistoryParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.accountinfo.data.HostLockRegistRow.class,
        webbroker3.accountinfo.data.HostLockRegistDao.FACTORY );
    regDao(
        webbroker3.accountinfo.data.HostLockRegiAcceptRow.class,
        webbroker3.accountinfo.data.HostLockRegiAcceptDao.FACTORY );
    regDao(
        webbroker3.accountinfo.data.CommAccountSendRow.class,
        webbroker3.accountinfo.data.CommAccountSendDao.FACTORY );
    regDao(
        webbroker3.accountinfo.data.CommCampaignCondMstRow.class,
        webbroker3.accountinfo.data.CommCampaignCondMstDao.FACTORY );
    regDao(
        webbroker3.accountinfo.data.CommCampaignProductMstRow.class,
        webbroker3.accountinfo.data.CommCampaignProductMstDao.FACTORY );
    regDao(
        webbroker3.accountinfo.data.CommCampaignAccHistoryRow.class,
        webbroker3.accountinfo.data.CommCampaignAccHistoryDao.FACTORY );
  }

}
@
