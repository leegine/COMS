head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.41.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3SrvRegiMasterDatabaseExtensions extends Plugin {

  private WEB3SrvRegiMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3SrvRegiMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.srvregi.data.SrvRegiMasterPK.class );
    regClass(  webbroker3.srvregi.data.SrvRegiMasterParams.class );
    regClass(  webbroker3.srvregi.data.SrvRegiLotInfoPK.class );
    regClass(  webbroker3.srvregi.data.SrvRegiLotInfoParams.class );
    regClass(  webbroker3.srvregi.data.SrvRegiKeyPK.class );
    regClass(  webbroker3.srvregi.data.SrvRegiKeyParams.class );
    regClass(  webbroker3.srvregi.data.SrvRegiChargePK.class );
    regClass(  webbroker3.srvregi.data.SrvRegiChargeParams.class );
    regClass(  webbroker3.srvregi.data.SrvRegiSetupPK.class );
    regClass(  webbroker3.srvregi.data.SrvRegiSetupParams.class );
    regClass(  webbroker3.srvregi.data.SrvRegiConsDocPK.class );
    regClass(  webbroker3.srvregi.data.SrvRegiConsDocParams.class );
    regClass(  webbroker3.srvregi.data.SrvRegiDealingCommPK.class );
    regClass(  webbroker3.srvregi.data.SrvRegiDealingCommParams.class );
    regClass(  webbroker3.srvregi.data.SrvRegiCommCondPK.class );
    regClass(  webbroker3.srvregi.data.SrvRegiCommCondParams.class );
    regClass(  webbroker3.srvregi.data.SrvRegiCommCondMasterPK.class );
    regClass(  webbroker3.srvregi.data.SrvRegiCommCondMasterParams.class );
    regClass(  webbroker3.srvregi.data.SrvRegiHistoryPK.class );
    regClass(  webbroker3.srvregi.data.SrvRegiHistoryParams.class );
    regClass(  webbroker3.srvregi.data.SrvAppliAttributePK.class );
    regClass(  webbroker3.srvregi.data.SrvAppliAttributeParams.class );
    regClass(  webbroker3.srvregi.data.OtherOrgInfoAdminPK.class );
    regClass(  webbroker3.srvregi.data.OtherOrgInfoAdminParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "srv_regi_master",
      webbroker3.srvregi.data.SrvRegiMasterPK.class,
      webbroker3.srvregi.data.SrvRegiMasterParams.class,
      null,
      null );
    regDbExtension( "master", "srv_regi_lot_info",
      webbroker3.srvregi.data.SrvRegiLotInfoPK.class,
      webbroker3.srvregi.data.SrvRegiLotInfoParams.class,
      null,
      null );
    regDbExtension( "master", "srv_regi_key",
      webbroker3.srvregi.data.SrvRegiKeyPK.class,
      webbroker3.srvregi.data.SrvRegiKeyParams.class,
      null,
      null );
    regDbExtension( "master", "srv_regi_charge",
      webbroker3.srvregi.data.SrvRegiChargePK.class,
      webbroker3.srvregi.data.SrvRegiChargeParams.class,
      null,
      null );
    regDbExtension( "master", "srv_regi_setup",
      webbroker3.srvregi.data.SrvRegiSetupPK.class,
      webbroker3.srvregi.data.SrvRegiSetupParams.class,
      null,
      null );
    regDbExtension( "master", "srv_regi_cons_doc",
      webbroker3.srvregi.data.SrvRegiConsDocPK.class,
      webbroker3.srvregi.data.SrvRegiConsDocParams.class,
      null,
      null );
    regDbExtension( "master", "srv_regi_dealing_comm",
      webbroker3.srvregi.data.SrvRegiDealingCommPK.class,
      webbroker3.srvregi.data.SrvRegiDealingCommParams.class,
      null,
      null );
    regDbExtension( "master", "srv_regi_comm_cond",
      webbroker3.srvregi.data.SrvRegiCommCondPK.class,
      webbroker3.srvregi.data.SrvRegiCommCondParams.class,
      null,
      null );
    regDbExtension( "master", "srv_regi_comm_cond_master",
      webbroker3.srvregi.data.SrvRegiCommCondMasterPK.class,
      webbroker3.srvregi.data.SrvRegiCommCondMasterParams.class,
      null,
      null );
    regDbExtension( "master", "srv_regi_history",
      webbroker3.srvregi.data.SrvRegiHistoryPK.class,
      webbroker3.srvregi.data.SrvRegiHistoryParams.class,
      null,
      null );
    regDbExtension( "master", "srv_appli_attribute",
      webbroker3.srvregi.data.SrvAppliAttributePK.class,
      webbroker3.srvregi.data.SrvAppliAttributeParams.class,
      null,
      null );
    regDbExtension( "master", "other_org_info_admin",
      webbroker3.srvregi.data.OtherOrgInfoAdminPK.class,
      webbroker3.srvregi.data.OtherOrgInfoAdminParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.srvregi.data.SrvRegiMasterRow.class,
        webbroker3.srvregi.data.SrvRegiMasterDao.FACTORY );
    regDao(
        webbroker3.srvregi.data.SrvRegiLotInfoRow.class,
        webbroker3.srvregi.data.SrvRegiLotInfoDao.FACTORY );
    regDao(
        webbroker3.srvregi.data.SrvRegiKeyRow.class,
        webbroker3.srvregi.data.SrvRegiKeyDao.FACTORY );
    regDao(
        webbroker3.srvregi.data.SrvRegiChargeRow.class,
        webbroker3.srvregi.data.SrvRegiChargeDao.FACTORY );
    regDao(
        webbroker3.srvregi.data.SrvRegiSetupRow.class,
        webbroker3.srvregi.data.SrvRegiSetupDao.FACTORY );
    regDao(
        webbroker3.srvregi.data.SrvRegiConsDocRow.class,
        webbroker3.srvregi.data.SrvRegiConsDocDao.FACTORY );
    regDao(
        webbroker3.srvregi.data.SrvRegiDealingCommRow.class,
        webbroker3.srvregi.data.SrvRegiDealingCommDao.FACTORY );
    regDao(
        webbroker3.srvregi.data.SrvRegiCommCondRow.class,
        webbroker3.srvregi.data.SrvRegiCommCondDao.FACTORY );
    regDao(
        webbroker3.srvregi.data.SrvRegiCommCondMasterRow.class,
        webbroker3.srvregi.data.SrvRegiCommCondMasterDao.FACTORY );
    regDao(
        webbroker3.srvregi.data.SrvRegiHistoryRow.class,
        webbroker3.srvregi.data.SrvRegiHistoryDao.FACTORY );
    regDao(
        webbroker3.srvregi.data.SrvAppliAttributeRow.class,
        webbroker3.srvregi.data.SrvAppliAttributeDao.FACTORY );
    regDao(
        webbroker3.srvregi.data.OtherOrgInfoAdminRow.class,
        webbroker3.srvregi.data.OtherOrgInfoAdminDao.FACTORY );
  }

}
@
