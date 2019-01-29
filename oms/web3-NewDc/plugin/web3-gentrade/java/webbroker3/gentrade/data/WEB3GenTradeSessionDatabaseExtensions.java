head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GenTradeSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3GenTradeSessionDatabaseExtensions extends Plugin {

  private WEB3GenTradeSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3GenTradeSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.gentrade.data.AdministratorUploadPK.class );
    regClass(  webbroker3.gentrade.data.AdministratorUploadParams.class );
    regClass(  webbroker3.gentrade.data.AdministratorUploadTempPK.class );
    regClass(  webbroker3.gentrade.data.AdministratorUploadTempParams.class );
    regClass(  webbroker3.gentrade.data.IfoDepositPK.class );
    regClass(  webbroker3.gentrade.data.IfoDepositParams.class );
    regClass(  webbroker3.gentrade.data.MailProcPK.class );
    regClass(  webbroker3.gentrade.data.MailProcParams.class );
    regClass(  webbroker3.gentrade.data.QuestionAnswerPK.class );
    regClass(  webbroker3.gentrade.data.QuestionAnswerParams.class );
    regClass(  webbroker3.gentrade.data.ExtMailProcPK.class );
    regClass(  webbroker3.gentrade.data.ExtMailProcParams.class );
    regClass(  webbroker3.gentrade.data.OnlineRunStatusPK.class );
    regClass(  webbroker3.gentrade.data.OnlineRunStatusParams.class );
    regClass(  webbroker3.gentrade.data.MailProcTempPK.class );
    regClass(  webbroker3.gentrade.data.MailProcTempParams.class );
    regClass(  webbroker3.gentrade.data.ExtMailProcTempPK.class );
    regClass(  webbroker3.gentrade.data.ExtMailProcTempParams.class );
    regClass(  webbroker3.gentrade.data.SoapMessageParams.class );
    regClass(  webbroker3.gentrade.data.MainAccountAllPK.class );
    regClass(  webbroker3.gentrade.data.MainAccountAllParams.class );
    regClass(  webbroker3.gentrade.data.SecurityShortageAccountHistPK.class );
    regClass(  webbroker3.gentrade.data.SecurityShortageAccountHistParams.class );
    regClass(  webbroker3.gentrade.data.QuoteClosingPricePK.class );
    regClass(  webbroker3.gentrade.data.QuoteClosingPriceParams.class );
    regClass(  webbroker3.gentrade.data.LoginHistoryPastPK.class );
    regClass(  webbroker3.gentrade.data.LoginHistoryPastParams.class );
    regClass(  webbroker3.gentrade.data.LoginHistoryPK.class );
    regClass(  webbroker3.gentrade.data.LoginHistoryParams.class );
    regClass(  webbroker3.gentrade.data.LoginRejectIpPK.class );
    regClass(  webbroker3.gentrade.data.LoginRejectIpParams.class );
    regClass(  webbroker3.gentrade.data.TraderAccountInfoPK.class );
    regClass(  webbroker3.gentrade.data.TraderAccountInfoParams.class );
    regClass(  webbroker3.gentrade.data.AccountTransferPK.class );
    regClass(  webbroker3.gentrade.data.AccountTransferParams.class );
    regClass(  webbroker3.gentrade.data.CommSerialNumbersPK.class );
    regClass(  webbroker3.gentrade.data.CommSerialNumbersParams.class );
    regClass(  webbroker3.gentrade.data.DocDeliveryManagementHistPK.class );
    regClass(  webbroker3.gentrade.data.DocDeliveryManagementHistParams.class );
    regClass(  webbroker3.gentrade.data.FixSendQPK.class );
    regClass(  webbroker3.gentrade.data.FixSendQParams.class );
    regClass(  webbroker3.gentrade.data.FixRcvdQPK.class );
    regClass(  webbroker3.gentrade.data.FixRcvdQParams.class );
    regClass(  webbroker3.gentrade.data.MailAssortmentPK.class );
    regClass(  webbroker3.gentrade.data.MailAssortmentParams.class );
    regClass(  webbroker3.gentrade.data.AccountMailAddressPK.class );
    regClass(  webbroker3.gentrade.data.AccountMailAddressParams.class );
    regClass(  webbroker3.gentrade.data.HostRequestOrderNumberPK.class );
    regClass(  webbroker3.gentrade.data.HostRequestOrderNumberParams.class );
    regClass(  webbroker3.gentrade.data.HostOrderexecutionEndPK.class );
    regClass(  webbroker3.gentrade.data.HostOrderexecutionEndParams.class );
    regClass(  webbroker3.gentrade.data.DaemonTriggerPK.class );
    regClass(  webbroker3.gentrade.data.DaemonTriggerParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "administrator_upload",
      webbroker3.gentrade.data.AdministratorUploadPK.class,
      webbroker3.gentrade.data.AdministratorUploadParams.class,
      null,
      null );
    regDbExtension( "session", "administrator_upload_temp",
      webbroker3.gentrade.data.AdministratorUploadTempPK.class,
      webbroker3.gentrade.data.AdministratorUploadTempParams.class,
      null,
      null );
    regDbExtension( "session", "ifo_deposit",
      webbroker3.gentrade.data.IfoDepositPK.class,
      webbroker3.gentrade.data.IfoDepositParams.class,
      null,
      null );
    regDbExtension( "session", "mail_proc",
      webbroker3.gentrade.data.MailProcPK.class,
      webbroker3.gentrade.data.MailProcParams.class,
      null,
      null );
    regDbExtension( "session", "question_answer",
      webbroker3.gentrade.data.QuestionAnswerPK.class,
      webbroker3.gentrade.data.QuestionAnswerParams.class,
      null,
      null );
    regDbExtension( "session", "ext_mail_proc",
      webbroker3.gentrade.data.ExtMailProcPK.class,
      webbroker3.gentrade.data.ExtMailProcParams.class,
      null,
      null );
    regDbExtension( "session", "online_run_status",
      webbroker3.gentrade.data.OnlineRunStatusPK.class,
      webbroker3.gentrade.data.OnlineRunStatusParams.class,
      null,
      null );
    regDbExtension( "session", "mail_proc_temp",
      webbroker3.gentrade.data.MailProcTempPK.class,
      webbroker3.gentrade.data.MailProcTempParams.class,
      null,
      null );
    regDbExtension( "session", "ext_mail_proc_temp",
      webbroker3.gentrade.data.ExtMailProcTempPK.class,
      webbroker3.gentrade.data.ExtMailProcTempParams.class,
      null,
      null );
    regDbExtension( "session", "soap_message",
      null,
      webbroker3.gentrade.data.SoapMessageParams.class,
      null,
      null );
    regDbExtension( "session", "main_account_all",
      webbroker3.gentrade.data.MainAccountAllPK.class,
      webbroker3.gentrade.data.MainAccountAllParams.class,
      null,
      null );
    regDbExtension( "session", "security_shortage_account_hist",
      webbroker3.gentrade.data.SecurityShortageAccountHistPK.class,
      webbroker3.gentrade.data.SecurityShortageAccountHistParams.class,
      null,
      null );
    regDbExtension( "session", "quote_closing_price",
      webbroker3.gentrade.data.QuoteClosingPricePK.class,
      webbroker3.gentrade.data.QuoteClosingPriceParams.class,
      null,
      null );
    regDbExtension( "session", "login_history_past",
      webbroker3.gentrade.data.LoginHistoryPastPK.class,
      webbroker3.gentrade.data.LoginHistoryPastParams.class,
      null,
      null );
    regDbExtension( "session", "login_history",
      webbroker3.gentrade.data.LoginHistoryPK.class,
      webbroker3.gentrade.data.LoginHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "login_reject_ip",
      webbroker3.gentrade.data.LoginRejectIpPK.class,
      webbroker3.gentrade.data.LoginRejectIpParams.class,
      null,
      null );
    regDbExtension( "session", "trader_account_info",
      webbroker3.gentrade.data.TraderAccountInfoPK.class,
      webbroker3.gentrade.data.TraderAccountInfoParams.class,
      null,
      null );
    regDbExtension( "session", "account_transfer",
      webbroker3.gentrade.data.AccountTransferPK.class,
      webbroker3.gentrade.data.AccountTransferParams.class,
      null,
      null );
    regDbExtension( "session", "comm_serial_numbers",
      webbroker3.gentrade.data.CommSerialNumbersPK.class,
      webbroker3.gentrade.data.CommSerialNumbersParams.class,
      null,
      null );
    regDbExtension( "session", "doc_delivery_management_hist",
      webbroker3.gentrade.data.DocDeliveryManagementHistPK.class,
      webbroker3.gentrade.data.DocDeliveryManagementHistParams.class,
      null,
      null );
    regDbExtension( "session", "fix_send_q",
      webbroker3.gentrade.data.FixSendQPK.class,
      webbroker3.gentrade.data.FixSendQParams.class,
      null,
      null );
    regDbExtension( "session", "fix_rcvd_q",
      webbroker3.gentrade.data.FixRcvdQPK.class,
      webbroker3.gentrade.data.FixRcvdQParams.class,
      null,
      null );
    regDbExtension( "session", "mail_assortment",
      webbroker3.gentrade.data.MailAssortmentPK.class,
      webbroker3.gentrade.data.MailAssortmentParams.class,
      null,
      null );
    regDbExtension( "session", "account_mail_address",
      webbroker3.gentrade.data.AccountMailAddressPK.class,
      webbroker3.gentrade.data.AccountMailAddressParams.class,
      null,
      null );
    regDbExtension( "session", "host_request_order_number",
      webbroker3.gentrade.data.HostRequestOrderNumberPK.class,
      webbroker3.gentrade.data.HostRequestOrderNumberParams.class,
      null,
      null );
    regDbExtension( "session", "host_orderexecution_end",
      webbroker3.gentrade.data.HostOrderexecutionEndPK.class,
      webbroker3.gentrade.data.HostOrderexecutionEndParams.class,
      null,
      null );
    regDbExtension( "session", "daemon_trigger",
      webbroker3.gentrade.data.DaemonTriggerPK.class,
      webbroker3.gentrade.data.DaemonTriggerParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.gentrade.data.AdministratorUploadRow.class,
        webbroker3.gentrade.data.AdministratorUploadDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.AdministratorUploadTempRow.class,
        webbroker3.gentrade.data.AdministratorUploadTempDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.IfoDepositRow.class,
        webbroker3.gentrade.data.IfoDepositDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.MailProcRow.class,
        webbroker3.gentrade.data.MailProcDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.QuestionAnswerRow.class,
        webbroker3.gentrade.data.QuestionAnswerDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.ExtMailProcRow.class,
        webbroker3.gentrade.data.ExtMailProcDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.OnlineRunStatusRow.class,
        webbroker3.gentrade.data.OnlineRunStatusDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.MailProcTempRow.class,
        webbroker3.gentrade.data.MailProcTempDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.ExtMailProcTempRow.class,
        webbroker3.gentrade.data.ExtMailProcTempDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.SoapMessageRow.class,
        webbroker3.gentrade.data.SoapMessageDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.MainAccountAllRow.class,
        webbroker3.gentrade.data.MainAccountAllDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.SecurityShortageAccountHistRow.class,
        webbroker3.gentrade.data.SecurityShortageAccountHistDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.QuoteClosingPriceRow.class,
        webbroker3.gentrade.data.QuoteClosingPriceDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.LoginHistoryPastRow.class,
        webbroker3.gentrade.data.LoginHistoryPastDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.LoginHistoryRow.class,
        webbroker3.gentrade.data.LoginHistoryDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.LoginRejectIpRow.class,
        webbroker3.gentrade.data.LoginRejectIpDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.TraderAccountInfoRow.class,
        webbroker3.gentrade.data.TraderAccountInfoDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.AccountTransferRow.class,
        webbroker3.gentrade.data.AccountTransferDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.CommSerialNumbersRow.class,
        webbroker3.gentrade.data.CommSerialNumbersDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.DocDeliveryManagementHistRow.class,
        webbroker3.gentrade.data.DocDeliveryManagementHistDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.FixSendQRow.class,
        webbroker3.gentrade.data.FixSendQDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.FixRcvdQRow.class,
        webbroker3.gentrade.data.FixRcvdQDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.MailAssortmentRow.class,
        webbroker3.gentrade.data.MailAssortmentDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.AccountMailAddressRow.class,
        webbroker3.gentrade.data.AccountMailAddressDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.HostRequestOrderNumberRow.class,
        webbroker3.gentrade.data.HostRequestOrderNumberDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.HostOrderexecutionEndRow.class,
        webbroker3.gentrade.data.HostOrderexecutionEndDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.DaemonTriggerRow.class,
        webbroker3.gentrade.data.DaemonTriggerDao.FACTORY );
  }

}
@
