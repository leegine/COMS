head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.38.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3AioMasterDatabaseExtensions extends Plugin {

  private WEB3AioMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3AioMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.aio.data.CompBankConditionPK.class );
    regClass(  webbroker3.aio.data.CompBankConditionParams.class );
    regClass(  webbroker3.aio.data.CooperateBankMasterPK.class );
    regClass(  webbroker3.aio.data.CooperateBankMasterParams.class );
    regClass(  webbroker3.aio.data.OtherOrderExecutedCountPK.class );
    regClass(  webbroker3.aio.data.OtherOrderExecutedCountParams.class );
    regClass(  webbroker3.aio.data.AmountRangePK.class );
    regClass(  webbroker3.aio.data.AmountRangeParams.class );
    regClass(  webbroker3.aio.data.CompBankCareerManagementPK.class );
    regClass(  webbroker3.aio.data.CompBankCareerManagementParams.class );
    regClass(  webbroker3.aio.data.CareerShopIdPK.class );
    regClass(  webbroker3.aio.data.CareerShopIdParams.class );
    regClass(  webbroker3.aio.data.SecurityProductPK.class );
    regClass(  webbroker3.aio.data.SecurityProductParams.class );
    regClass(  webbroker3.aio.data.FxAccountPK.class );
    regClass(  webbroker3.aio.data.FxAccountParams.class );
    regClass(  webbroker3.aio.data.FxAccountCodePK.class );
    regClass(  webbroker3.aio.data.FxAccountCodeParams.class );
    regClass(  webbroker3.aio.data.CompFxConditionPK.class );
    regClass(  webbroker3.aio.data.CompFxConditionParams.class );
    regClass(  webbroker3.aio.data.FxUnnecessaryExplanationPK.class );
    regClass(  webbroker3.aio.data.FxUnnecessaryExplanationParams.class );
    regClass(  webbroker3.aio.data.FxTransferMasterPK.class );
    regClass(  webbroker3.aio.data.FxTransferMasterParams.class );
    regClass(  webbroker3.aio.data.SecReceiptDeliveryActionPK.class );
    regClass(  webbroker3.aio.data.SecReceiptDeliveryActionParams.class );
    regClass(  webbroker3.aio.data.FeqAccountPK.class );
    regClass(  webbroker3.aio.data.FeqAccountParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "comp_bank_condition",
      webbroker3.aio.data.CompBankConditionPK.class,
      webbroker3.aio.data.CompBankConditionParams.class,
      null,
      null );
    regDbExtension( "master", "cooperate_bank_master",
      webbroker3.aio.data.CooperateBankMasterPK.class,
      webbroker3.aio.data.CooperateBankMasterParams.class,
      null,
      null );
    regDbExtension( "master", "other_order_executed_count",
      webbroker3.aio.data.OtherOrderExecutedCountPK.class,
      webbroker3.aio.data.OtherOrderExecutedCountParams.class,
      null,
      null );
    regDbExtension( "master", "amount_range",
      webbroker3.aio.data.AmountRangePK.class,
      webbroker3.aio.data.AmountRangeParams.class,
      null,
      null );
    regDbExtension( "master", "comp_bank_career_management",
      webbroker3.aio.data.CompBankCareerManagementPK.class,
      webbroker3.aio.data.CompBankCareerManagementParams.class,
      null,
      null );
    regDbExtension( "master", "career_shop_id",
      webbroker3.aio.data.CareerShopIdPK.class,
      webbroker3.aio.data.CareerShopIdParams.class,
      null,
      null );
    regDbExtension( "master", "security_product",
      webbroker3.aio.data.SecurityProductPK.class,
      webbroker3.aio.data.SecurityProductParams.class,
      null,
      null );
    regDbExtension( "master", "fx_account",
      webbroker3.aio.data.FxAccountPK.class,
      webbroker3.aio.data.FxAccountParams.class,
      null,
      null );
    regDbExtension( "master", "fx_account_code",
      webbroker3.aio.data.FxAccountCodePK.class,
      webbroker3.aio.data.FxAccountCodeParams.class,
      null,
      null );
    regDbExtension( "master", "comp_fx_condition",
      webbroker3.aio.data.CompFxConditionPK.class,
      webbroker3.aio.data.CompFxConditionParams.class,
      null,
      null );
    regDbExtension( "master", "fx_unnecessary_explanation",
      webbroker3.aio.data.FxUnnecessaryExplanationPK.class,
      webbroker3.aio.data.FxUnnecessaryExplanationParams.class,
      null,
      null );
    regDbExtension( "master", "fx_transfer_master",
      webbroker3.aio.data.FxTransferMasterPK.class,
      webbroker3.aio.data.FxTransferMasterParams.class,
      null,
      null );
    regDbExtension( "master", "sec_receipt_delivery_action",
      webbroker3.aio.data.SecReceiptDeliveryActionPK.class,
      webbroker3.aio.data.SecReceiptDeliveryActionParams.class,
      null,
      null );
    regDbExtension( "master", "feq_account",
      webbroker3.aio.data.FeqAccountPK.class,
      webbroker3.aio.data.FeqAccountParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.aio.data.CompBankConditionRow.class,
        webbroker3.aio.data.CompBankConditionDao.FACTORY );
    regDao(
        webbroker3.aio.data.CooperateBankMasterRow.class,
        webbroker3.aio.data.CooperateBankMasterDao.FACTORY );
    regDao(
        webbroker3.aio.data.OtherOrderExecutedCountRow.class,
        webbroker3.aio.data.OtherOrderExecutedCountDao.FACTORY );
    regDao(
        webbroker3.aio.data.AmountRangeRow.class,
        webbroker3.aio.data.AmountRangeDao.FACTORY );
    regDao(
        webbroker3.aio.data.CompBankCareerManagementRow.class,
        webbroker3.aio.data.CompBankCareerManagementDao.FACTORY );
    regDao(
        webbroker3.aio.data.CareerShopIdRow.class,
        webbroker3.aio.data.CareerShopIdDao.FACTORY );
    regDao(
        webbroker3.aio.data.SecurityProductRow.class,
        webbroker3.aio.data.SecurityProductDao.FACTORY );
    regDao(
        webbroker3.aio.data.FxAccountRow.class,
        webbroker3.aio.data.FxAccountDao.FACTORY );
    regDao(
        webbroker3.aio.data.FxAccountCodeRow.class,
        webbroker3.aio.data.FxAccountCodeDao.FACTORY );
    regDao(
        webbroker3.aio.data.CompFxConditionRow.class,
        webbroker3.aio.data.CompFxConditionDao.FACTORY );
    regDao(
        webbroker3.aio.data.FxUnnecessaryExplanationRow.class,
        webbroker3.aio.data.FxUnnecessaryExplanationDao.FACTORY );
    regDao(
        webbroker3.aio.data.FxTransferMasterRow.class,
        webbroker3.aio.data.FxTransferMasterDao.FACTORY );
    regDao(
        webbroker3.aio.data.SecReceiptDeliveryActionRow.class,
        webbroker3.aio.data.SecReceiptDeliveryActionDao.FACTORY );
    regDao(
        webbroker3.aio.data.FeqAccountRow.class,
        webbroker3.aio.data.FeqAccountDao.FACTORY );
  }

}
@
