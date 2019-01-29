head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3BondMasterDatabaseExtensions extends Plugin {

  private WEB3BondMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3BondMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.bd.data.CustodianPK.class );
    regClass(  webbroker3.bd.data.CustodianParams.class );
    regClass(  webbroker3.bd.data.BondAutoExecLimitActionPK.class );
    regClass(  webbroker3.bd.data.BondAutoExecLimitActionParams.class );
    regClass(  webbroker3.bd.data.BondProductCouponPK.class );
    regClass(  webbroker3.bd.data.BondProductCouponParams.class );
    regClass(  webbroker3.bd.data.AccruedInterestCalcCondPK.class );
    regClass(  webbroker3.bd.data.AccruedInterestCalcCondParams.class );
    regClass(  webbroker3.bd.data.BondBranchConditionPK.class );
    regClass(  webbroker3.bd.data.BondBranchConditionParams.class );
    regClass(  webbroker3.bd.data.BondIssueCouponTypeParams.class );
    regClass(  webbroker3.bd.data.BondOrderAcceptActionPK.class );
    regClass(  webbroker3.bd.data.BondOrderAcceptActionParams.class );
    regClass(  webbroker3.bd.data.BondBranchRecruitLimitPK.class );
    regClass(  webbroker3.bd.data.BondBranchRecruitLimitParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "custodian",
      webbroker3.bd.data.CustodianPK.class,
      webbroker3.bd.data.CustodianParams.class,
      null,
      null );
    regDbExtension( "master", "bond_auto_exec_limit_action",
      webbroker3.bd.data.BondAutoExecLimitActionPK.class,
      webbroker3.bd.data.BondAutoExecLimitActionParams.class,
      null,
      null );
    regDbExtension( "master", "bond_product_coupon",
      webbroker3.bd.data.BondProductCouponPK.class,
      webbroker3.bd.data.BondProductCouponParams.class,
      null,
      null );
    regDbExtension( "master", "accrued_interest_calc_cond",
      webbroker3.bd.data.AccruedInterestCalcCondPK.class,
      webbroker3.bd.data.AccruedInterestCalcCondParams.class,
      null,
      null );
    regDbExtension( "master", "bond_branch_condition",
      webbroker3.bd.data.BondBranchConditionPK.class,
      webbroker3.bd.data.BondBranchConditionParams.class,
      null,
      null );
    regDbExtension( "master", "bond_issue_coupon_type",
      null,
      webbroker3.bd.data.BondIssueCouponTypeParams.class,
      null,
      null );
    regDbExtension( "master", "bond_order_accept_action",
      webbroker3.bd.data.BondOrderAcceptActionPK.class,
      webbroker3.bd.data.BondOrderAcceptActionParams.class,
      null,
      null );
    regDbExtension( "master", "bond_branch_recruit_limit",
      webbroker3.bd.data.BondBranchRecruitLimitPK.class,
      webbroker3.bd.data.BondBranchRecruitLimitParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.bd.data.CustodianRow.class,
        webbroker3.bd.data.CustodianDao.FACTORY );
    regDao(
        webbroker3.bd.data.BondAutoExecLimitActionRow.class,
        webbroker3.bd.data.BondAutoExecLimitActionDao.FACTORY );
    regDao(
        webbroker3.bd.data.BondProductCouponRow.class,
        webbroker3.bd.data.BondProductCouponDao.FACTORY );
    regDao(
        webbroker3.bd.data.AccruedInterestCalcCondRow.class,
        webbroker3.bd.data.AccruedInterestCalcCondDao.FACTORY );
    regDao(
        webbroker3.bd.data.BondBranchConditionRow.class,
        webbroker3.bd.data.BondBranchConditionDao.FACTORY );
    regDao(
        webbroker3.bd.data.BondIssueCouponTypeRow.class,
        webbroker3.bd.data.BondIssueCouponTypeDao.FACTORY );
    regDao(
        webbroker3.bd.data.BondOrderAcceptActionRow.class,
        webbroker3.bd.data.BondOrderAcceptActionDao.FACTORY );
    regDao(
        webbroker3.bd.data.BondBranchRecruitLimitRow.class,
        webbroker3.bd.data.BondBranchRecruitLimitDao.FACTORY );
  }

}
@
