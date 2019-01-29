head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.15.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3AccOpenSessionDatabaseExtensions extends Plugin {

  private WEB3AccOpenSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3AccOpenSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.accountopen.data.ExpAccountOpenPK.class );
    regClass(  webbroker3.accountopen.data.ExpAccountOpenParams.class );
    regClass(  webbroker3.accountopen.data.AccOpenInvalidValuesPK.class );
    regClass(  webbroker3.accountopen.data.AccOpenInvalidValuesParams.class );
    regClass(  webbroker3.accountopen.data.AccOpenRequestNumberPK.class );
    regClass(  webbroker3.accountopen.data.AccOpenRequestNumberParams.class );
    regClass(  webbroker3.accountopen.data.YCustomerPK.class );
    regClass(  webbroker3.accountopen.data.YCustomerParams.class );
    regClass(  webbroker3.accountopen.data.PasswordVoucherPK.class );
    regClass(  webbroker3.accountopen.data.PasswordVoucherParams.class );
    regClass(  webbroker3.accountopen.data.AccOpenVoucherMasterPK.class );
    regClass(  webbroker3.accountopen.data.AccOpenVoucherMasterParams.class );
    regClass(  webbroker3.accountopen.data.AccOpenVoucherItemPK.class );
    regClass(  webbroker3.accountopen.data.AccOpenVoucherItemParams.class );
    regClass(  webbroker3.accountopen.data.AccOpenVoucherStatusPK.class );
    regClass(  webbroker3.accountopen.data.AccOpenVoucherStatusParams.class );
    regClass(  webbroker3.accountopen.data.TradeConditionVoucherPK.class );
    regClass(  webbroker3.accountopen.data.TradeConditionVoucherParams.class );
    regClass(  webbroker3.accountopen.data.ImpConfirmVoucherPK.class );
    regClass(  webbroker3.accountopen.data.ImpConfirmVoucherParams.class );
    regClass(  webbroker3.accountopen.data.IdConfirmVoucherPK.class );
    regClass(  webbroker3.accountopen.data.IdConfirmVoucherParams.class );
    regClass(  webbroker3.accountopen.data.HostMrfAccVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostMrfAccVoucherParams.class );
    regClass(  webbroker3.accountopen.data.HostContMrgVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostContMrgVoucherParams.class );
    regClass(  webbroker3.accountopen.data.HostAccRegVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostAccRegVoucherParams.class );
    regClass(  webbroker3.accountopen.data.HostAccOpenAcceptPK.class );
    regClass(  webbroker3.accountopen.data.HostAccOpenAcceptParams.class );
    regClass(  webbroker3.accountopen.data.HostBankTransVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostBankTransVoucherParams.class );
    regClass(  webbroker3.accountopen.data.HostPostalTransVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostPostalTransVoucherParams.class );
    regClass(  webbroker3.accountopen.data.HostAgreeTransVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostAgreeTransVoucherParams.class );
    regClass(  webbroker3.accountopen.data.HostChargedInfoVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostChargedInfoVoucherParams.class );
    regClass(  webbroker3.accountopen.data.HostGpRegVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostGpRegVoucherParams.class );
    regClass(  webbroker3.accountopen.data.HostRealnameRegVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostRealnameRegVoucherParams.class );
    regClass(  webbroker3.accountopen.data.HostStockholderRegVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostStockholderRegVoucherParams.class );
    regClass(  webbroker3.accountopen.data.HostInsiderRegVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostInsiderRegVoucherParams.class );
    regClass(  webbroker3.accountopen.data.AccOpenAccountCodePK.class );
    regClass(  webbroker3.accountopen.data.AccOpenAccountCodeParams.class );
    regClass(  webbroker3.accountopen.data.HostFDepositVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostFDepositVoucherParams.class );
    regClass(  webbroker3.accountopen.data.BatchBranchPK.class );
    regClass(  webbroker3.accountopen.data.BatchBranchParams.class );
    regClass(  webbroker3.accountopen.data.BatchPreferencesPK.class );
    regClass(  webbroker3.accountopen.data.BatchPreferencesParams.class );
    regClass(  webbroker3.accountopen.data.HostConditionRegVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostConditionRegVoucherParams.class );
    regClass(  webbroker3.accountopen.data.MailAddressRegiPK.class );
    regClass(  webbroker3.accountopen.data.MailAddressRegiParams.class );
    regClass(  webbroker3.accountopen.data.HostAgencyNotifyVoucherPK.class );
    regClass(  webbroker3.accountopen.data.HostAgencyNotifyVoucherParams.class );
    regClass(  webbroker3.accountopen.data.ExpAccountOpenTempPK.class );
    regClass(  webbroker3.accountopen.data.ExpAccountOpenTempParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "exp_account_open",
      webbroker3.accountopen.data.ExpAccountOpenPK.class,
      webbroker3.accountopen.data.ExpAccountOpenParams.class,
      null,
      null );
    regDbExtension( "session", "acc_open_invalid_values",
      webbroker3.accountopen.data.AccOpenInvalidValuesPK.class,
      webbroker3.accountopen.data.AccOpenInvalidValuesParams.class,
      null,
      null );
    regDbExtension( "session", "acc_open_request_number",
      webbroker3.accountopen.data.AccOpenRequestNumberPK.class,
      webbroker3.accountopen.data.AccOpenRequestNumberParams.class,
      null,
      null );
    regDbExtension( "session", "y_customer",
      webbroker3.accountopen.data.YCustomerPK.class,
      webbroker3.accountopen.data.YCustomerParams.class,
      null,
      null );
    regDbExtension( "session", "password_voucher",
      webbroker3.accountopen.data.PasswordVoucherPK.class,
      webbroker3.accountopen.data.PasswordVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "acc_open_voucher_master",
      webbroker3.accountopen.data.AccOpenVoucherMasterPK.class,
      webbroker3.accountopen.data.AccOpenVoucherMasterParams.class,
      null,
      null );
    regDbExtension( "session", "acc_open_voucher_item",
      webbroker3.accountopen.data.AccOpenVoucherItemPK.class,
      webbroker3.accountopen.data.AccOpenVoucherItemParams.class,
      null,
      null );
    regDbExtension( "session", "acc_open_voucher_status",
      webbroker3.accountopen.data.AccOpenVoucherStatusPK.class,
      webbroker3.accountopen.data.AccOpenVoucherStatusParams.class,
      null,
      null );
    regDbExtension( "session", "trade_condition_voucher",
      webbroker3.accountopen.data.TradeConditionVoucherPK.class,
      webbroker3.accountopen.data.TradeConditionVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "imp_confirm_voucher",
      webbroker3.accountopen.data.ImpConfirmVoucherPK.class,
      webbroker3.accountopen.data.ImpConfirmVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "id_confirm_voucher",
      webbroker3.accountopen.data.IdConfirmVoucherPK.class,
      webbroker3.accountopen.data.IdConfirmVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "host_mrf_acc_voucher",
      webbroker3.accountopen.data.HostMrfAccVoucherPK.class,
      webbroker3.accountopen.data.HostMrfAccVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "host_cont_mrg_voucher",
      webbroker3.accountopen.data.HostContMrgVoucherPK.class,
      webbroker3.accountopen.data.HostContMrgVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "host_acc_reg_voucher",
      webbroker3.accountopen.data.HostAccRegVoucherPK.class,
      webbroker3.accountopen.data.HostAccRegVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "host_acc_open_accept",
      webbroker3.accountopen.data.HostAccOpenAcceptPK.class,
      webbroker3.accountopen.data.HostAccOpenAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_bank_trans_voucher",
      webbroker3.accountopen.data.HostBankTransVoucherPK.class,
      webbroker3.accountopen.data.HostBankTransVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "host_postal_trans_voucher",
      webbroker3.accountopen.data.HostPostalTransVoucherPK.class,
      webbroker3.accountopen.data.HostPostalTransVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "host_agree_trans_voucher",
      webbroker3.accountopen.data.HostAgreeTransVoucherPK.class,
      webbroker3.accountopen.data.HostAgreeTransVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "host_charged_info_voucher",
      webbroker3.accountopen.data.HostChargedInfoVoucherPK.class,
      webbroker3.accountopen.data.HostChargedInfoVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "host_gp_reg_voucher",
      webbroker3.accountopen.data.HostGpRegVoucherPK.class,
      webbroker3.accountopen.data.HostGpRegVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "host_realname_reg_voucher",
      webbroker3.accountopen.data.HostRealnameRegVoucherPK.class,
      webbroker3.accountopen.data.HostRealnameRegVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "host_stockholder_reg_voucher",
      webbroker3.accountopen.data.HostStockholderRegVoucherPK.class,
      webbroker3.accountopen.data.HostStockholderRegVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "host_insider_reg_voucher",
      webbroker3.accountopen.data.HostInsiderRegVoucherPK.class,
      webbroker3.accountopen.data.HostInsiderRegVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "acc_open_account_code",
      webbroker3.accountopen.data.AccOpenAccountCodePK.class,
      webbroker3.accountopen.data.AccOpenAccountCodeParams.class,
      null,
      null );
    regDbExtension( "session", "host_f_deposit_voucher",
      webbroker3.accountopen.data.HostFDepositVoucherPK.class,
      webbroker3.accountopen.data.HostFDepositVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "batch_branch",
      webbroker3.accountopen.data.BatchBranchPK.class,
      webbroker3.accountopen.data.BatchBranchParams.class,
      null,
      null );
    regDbExtension( "session", "batch_preferences",
      webbroker3.accountopen.data.BatchPreferencesPK.class,
      webbroker3.accountopen.data.BatchPreferencesParams.class,
      null,
      null );
    regDbExtension( "session", "host_condition_reg_voucher",
      webbroker3.accountopen.data.HostConditionRegVoucherPK.class,
      webbroker3.accountopen.data.HostConditionRegVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "mail_address_regi",
      webbroker3.accountopen.data.MailAddressRegiPK.class,
      webbroker3.accountopen.data.MailAddressRegiParams.class,
      null,
      null );
    regDbExtension( "session", "host_agency_notify_voucher",
      webbroker3.accountopen.data.HostAgencyNotifyVoucherPK.class,
      webbroker3.accountopen.data.HostAgencyNotifyVoucherParams.class,
      null,
      null );
    regDbExtension( "session", "exp_account_open_temp",
      webbroker3.accountopen.data.ExpAccountOpenTempPK.class,
      webbroker3.accountopen.data.ExpAccountOpenTempParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.accountopen.data.ExpAccountOpenRow.class,
        webbroker3.accountopen.data.ExpAccountOpenDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.AccOpenInvalidValuesRow.class,
        webbroker3.accountopen.data.AccOpenInvalidValuesDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.AccOpenRequestNumberRow.class,
        webbroker3.accountopen.data.AccOpenRequestNumberDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.YCustomerRow.class,
        webbroker3.accountopen.data.YCustomerDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.PasswordVoucherRow.class,
        webbroker3.accountopen.data.PasswordVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.AccOpenVoucherMasterRow.class,
        webbroker3.accountopen.data.AccOpenVoucherMasterDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.AccOpenVoucherItemRow.class,
        webbroker3.accountopen.data.AccOpenVoucherItemDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.AccOpenVoucherStatusRow.class,
        webbroker3.accountopen.data.AccOpenVoucherStatusDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.TradeConditionVoucherRow.class,
        webbroker3.accountopen.data.TradeConditionVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.ImpConfirmVoucherRow.class,
        webbroker3.accountopen.data.ImpConfirmVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.IdConfirmVoucherRow.class,
        webbroker3.accountopen.data.IdConfirmVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostMrfAccVoucherRow.class,
        webbroker3.accountopen.data.HostMrfAccVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostContMrgVoucherRow.class,
        webbroker3.accountopen.data.HostContMrgVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostAccRegVoucherRow.class,
        webbroker3.accountopen.data.HostAccRegVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostAccOpenAcceptRow.class,
        webbroker3.accountopen.data.HostAccOpenAcceptDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostBankTransVoucherRow.class,
        webbroker3.accountopen.data.HostBankTransVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostPostalTransVoucherRow.class,
        webbroker3.accountopen.data.HostPostalTransVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostAgreeTransVoucherRow.class,
        webbroker3.accountopen.data.HostAgreeTransVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostChargedInfoVoucherRow.class,
        webbroker3.accountopen.data.HostChargedInfoVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostGpRegVoucherRow.class,
        webbroker3.accountopen.data.HostGpRegVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostRealnameRegVoucherRow.class,
        webbroker3.accountopen.data.HostRealnameRegVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostStockholderRegVoucherRow.class,
        webbroker3.accountopen.data.HostStockholderRegVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostInsiderRegVoucherRow.class,
        webbroker3.accountopen.data.HostInsiderRegVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.AccOpenAccountCodeRow.class,
        webbroker3.accountopen.data.AccOpenAccountCodeDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostFDepositVoucherRow.class,
        webbroker3.accountopen.data.HostFDepositVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.BatchBranchRow.class,
        webbroker3.accountopen.data.BatchBranchDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.BatchPreferencesRow.class,
        webbroker3.accountopen.data.BatchPreferencesDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostConditionRegVoucherRow.class,
        webbroker3.accountopen.data.HostConditionRegVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.MailAddressRegiRow.class,
        webbroker3.accountopen.data.MailAddressRegiDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.HostAgencyNotifyVoucherRow.class,
        webbroker3.accountopen.data.HostAgencyNotifyVoucherDao.FACTORY );
    regDao(
        webbroker3.accountopen.data.ExpAccountOpenTempRow.class,
        webbroker3.accountopen.data.ExpAccountOpenTempDao.FACTORY );
  }

}
@
