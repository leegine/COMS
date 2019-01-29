head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.43.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3AioSessionDatabaseExtensions extends Plugin {

  private WEB3AioSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3AioSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.aio.data.BankCashTransferStatusPK.class );
    regClass(  webbroker3.aio.data.BankCashTransferStatusParams.class );
    regClass(  webbroker3.aio.data.BankSettleStartRequestPK.class );
    regClass(  webbroker3.aio.data.BankSettleStartRequestParams.class );
    regClass(  webbroker3.aio.data.BankSettleResultResponsePK.class );
    regClass(  webbroker3.aio.data.BankSettleResultResponseParams.class );
    regClass(  webbroker3.aio.data.BankOrderRequestPK.class );
    regClass(  webbroker3.aio.data.BankOrderRequestParams.class );
    regClass(  webbroker3.aio.data.DepositInformPK.class );
    regClass(  webbroker3.aio.data.DepositInformParams.class );
    regClass(  webbroker3.aio.data.AioDepositInformSendMailPK.class );
    regClass(  webbroker3.aio.data.AioDepositInformSendMailParams.class );
    regClass(  webbroker3.aio.data.BankDepositNotifyPK.class );
    regClass(  webbroker3.aio.data.BankDepositNotifyParams.class );
    regClass(  webbroker3.aio.data.BankDepositErrorHistoryPK.class );
    regClass(  webbroker3.aio.data.BankDepositErrorHistoryParams.class );
    regClass(  webbroker3.aio.data.HostPaymentAcceptPK.class );
    regClass(  webbroker3.aio.data.HostPaymentAcceptParams.class );
    regClass(  webbroker3.aio.data.HostPaymentOrderPK.class );
    regClass(  webbroker3.aio.data.HostPaymentOrderParams.class );
    regClass(  webbroker3.aio.data.HostCashTransferPK.class );
    regClass(  webbroker3.aio.data.HostCashTransferParams.class );
    regClass(  webbroker3.aio.data.HostCashTransOrderPK.class );
    regClass(  webbroker3.aio.data.HostCashTransOrderParams.class );
    regClass(  webbroker3.aio.data.HostCashTransOrderAcceptPK.class );
    regClass(  webbroker3.aio.data.HostCashTransOrderAcceptParams.class );
    regClass(  webbroker3.aio.data.HostTransferAcceptPK.class );
    regClass(  webbroker3.aio.data.HostTransferAcceptParams.class );
    regClass(  webbroker3.aio.data.HostTransferOrderPK.class );
    regClass(  webbroker3.aio.data.HostTransferOrderParams.class );
    regClass(  webbroker3.aio.data.HostTransferReceiptPK.class );
    regClass(  webbroker3.aio.data.HostTransferReceiptParams.class );
    regClass(  webbroker3.aio.data.HostMrgsecTransNotifyParams.class );
    regClass(  webbroker3.aio.data.HostMrgsecTransAcceptParams.class );
    regClass(  webbroker3.aio.data.HostMrgsecTransferParams.class );
    regClass(  webbroker3.aio.data.HostSpsecTransNotifyParams.class );
    regClass(  webbroker3.aio.data.HostSecDeliveryTransferPK.class );
    regClass(  webbroker3.aio.data.HostSecDeliveryTransferParams.class );
    regClass(  webbroker3.aio.data.HostTransferPaymentPK.class );
    regClass(  webbroker3.aio.data.HostTransferPaymentParams.class );
    regClass(  webbroker3.aio.data.HostForeignCashTransferPK.class );
    regClass(  webbroker3.aio.data.HostForeignCashTransferParams.class );
    regClass(  webbroker3.aio.data.HostForeignCashTransOrderPK.class );
    regClass(  webbroker3.aio.data.HostForeignCashTransOrderParams.class );
    regClass(  webbroker3.aio.data.HostFCashTransOrderAcceptPK.class );
    regClass(  webbroker3.aio.data.HostFCashTransOrderAcceptParams.class );
    regClass(  webbroker3.aio.data.PayRequiredAmountPK.class );
    regClass(  webbroker3.aio.data.PayRequiredAmountParams.class );
    regClass(  webbroker3.aio.data.GftAccountOpenStatusPK.class );
    regClass(  webbroker3.aio.data.GftAccountOpenStatusParams.class );
    regClass(  webbroker3.aio.data.GftTransferStatusPK.class );
    regClass(  webbroker3.aio.data.GftTransferStatusParams.class );
    regClass(  webbroker3.aio.data.GftMessageParams.class );
    regClass(  webbroker3.aio.data.SsoMessageParams.class );
    regClass(  webbroker3.aio.data.UwgTransferStatusPK.class );
    regClass(  webbroker3.aio.data.UwgTransferStatusParams.class );
    regClass(  webbroker3.aio.data.UwgAccountOpenStatusPK.class );
    regClass(  webbroker3.aio.data.UwgAccountOpenStatusParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "bank_cash_transfer_status",
      webbroker3.aio.data.BankCashTransferStatusPK.class,
      webbroker3.aio.data.BankCashTransferStatusParams.class,
      null,
      null );
    regDbExtension( "session", "bank_settle_start_request",
      webbroker3.aio.data.BankSettleStartRequestPK.class,
      webbroker3.aio.data.BankSettleStartRequestParams.class,
      null,
      null );
    regDbExtension( "session", "bank_settle_result_response",
      webbroker3.aio.data.BankSettleResultResponsePK.class,
      webbroker3.aio.data.BankSettleResultResponseParams.class,
      null,
      null );
    regDbExtension( "session", "bank_order_request",
      webbroker3.aio.data.BankOrderRequestPK.class,
      webbroker3.aio.data.BankOrderRequestParams.class,
      null,
      null );
    regDbExtension( "session", "deposit_inform",
      webbroker3.aio.data.DepositInformPK.class,
      webbroker3.aio.data.DepositInformParams.class,
      null,
      null );
    regDbExtension( "session", "aio_deposit_inform_send_mail",
      webbroker3.aio.data.AioDepositInformSendMailPK.class,
      webbroker3.aio.data.AioDepositInformSendMailParams.class,
      null,
      null );
    regDbExtension( "session", "bank_deposit_notify",
      webbroker3.aio.data.BankDepositNotifyPK.class,
      webbroker3.aio.data.BankDepositNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "bank_deposit_error_history",
      webbroker3.aio.data.BankDepositErrorHistoryPK.class,
      webbroker3.aio.data.BankDepositErrorHistoryParams.class,
      null,
      null );
    regDbExtension( "session", "host_payment_accept",
      webbroker3.aio.data.HostPaymentAcceptPK.class,
      webbroker3.aio.data.HostPaymentAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_payment_order",
      webbroker3.aio.data.HostPaymentOrderPK.class,
      webbroker3.aio.data.HostPaymentOrderParams.class,
      null,
      null );
    regDbExtension( "session", "host_cash_transfer",
      webbroker3.aio.data.HostCashTransferPK.class,
      webbroker3.aio.data.HostCashTransferParams.class,
      null,
      null );
    regDbExtension( "session", "host_cash_trans_order",
      webbroker3.aio.data.HostCashTransOrderPK.class,
      webbroker3.aio.data.HostCashTransOrderParams.class,
      null,
      null );
    regDbExtension( "session", "host_cash_trans_order_accept",
      webbroker3.aio.data.HostCashTransOrderAcceptPK.class,
      webbroker3.aio.data.HostCashTransOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_transfer_accept",
      webbroker3.aio.data.HostTransferAcceptPK.class,
      webbroker3.aio.data.HostTransferAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_transfer_order",
      webbroker3.aio.data.HostTransferOrderPK.class,
      webbroker3.aio.data.HostTransferOrderParams.class,
      null,
      null );
    regDbExtension( "session", "host_transfer_receipt",
      webbroker3.aio.data.HostTransferReceiptPK.class,
      webbroker3.aio.data.HostTransferReceiptParams.class,
      null,
      null );
    regDbExtension( "session", "host_mrgsec_trans_notify",
      null,
      webbroker3.aio.data.HostMrgsecTransNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "host_mrgsec_trans_accept",
      null,
      webbroker3.aio.data.HostMrgsecTransAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_mrgsec_transfer",
      null,
      webbroker3.aio.data.HostMrgsecTransferParams.class,
      null,
      null );
    regDbExtension( "session", "host_spsec_trans_notify",
      null,
      webbroker3.aio.data.HostSpsecTransNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "host_sec_delivery_transfer",
      webbroker3.aio.data.HostSecDeliveryTransferPK.class,
      webbroker3.aio.data.HostSecDeliveryTransferParams.class,
      null,
      null );
    regDbExtension( "session", "host_transfer_payment",
      webbroker3.aio.data.HostTransferPaymentPK.class,
      webbroker3.aio.data.HostTransferPaymentParams.class,
      null,
      null );
    regDbExtension( "session", "host_foreign_cash_transfer",
      webbroker3.aio.data.HostForeignCashTransferPK.class,
      webbroker3.aio.data.HostForeignCashTransferParams.class,
      null,
      null );
    regDbExtension( "session", "host_foreign_cash_trans_order",
      webbroker3.aio.data.HostForeignCashTransOrderPK.class,
      webbroker3.aio.data.HostForeignCashTransOrderParams.class,
      null,
      null );
    regDbExtension( "session", "host_f_cash_trans_order_accept",
      webbroker3.aio.data.HostFCashTransOrderAcceptPK.class,
      webbroker3.aio.data.HostFCashTransOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "pay_required_amount",
      webbroker3.aio.data.PayRequiredAmountPK.class,
      webbroker3.aio.data.PayRequiredAmountParams.class,
      null,
      null );
    regDbExtension( "session", "gft_account_open_status",
      webbroker3.aio.data.GftAccountOpenStatusPK.class,
      webbroker3.aio.data.GftAccountOpenStatusParams.class,
      null,
      null );
    regDbExtension( "session", "gft_transfer_status",
      webbroker3.aio.data.GftTransferStatusPK.class,
      webbroker3.aio.data.GftTransferStatusParams.class,
      null,
      null );
    regDbExtension( "session", "gft_message",
      null,
      webbroker3.aio.data.GftMessageParams.class,
      null,
      null );
    regDbExtension( "session", "sso_message",
      null,
      webbroker3.aio.data.SsoMessageParams.class,
      null,
      null );
    regDbExtension( "session", "uwg_transfer_status",
      webbroker3.aio.data.UwgTransferStatusPK.class,
      webbroker3.aio.data.UwgTransferStatusParams.class,
      null,
      null );
    regDbExtension( "session", "uwg_account_open_status",
      webbroker3.aio.data.UwgAccountOpenStatusPK.class,
      webbroker3.aio.data.UwgAccountOpenStatusParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.aio.data.BankCashTransferStatusRow.class,
        webbroker3.aio.data.BankCashTransferStatusDao.FACTORY );
    regDao(
        webbroker3.aio.data.BankSettleStartRequestRow.class,
        webbroker3.aio.data.BankSettleStartRequestDao.FACTORY );
    regDao(
        webbroker3.aio.data.BankSettleResultResponseRow.class,
        webbroker3.aio.data.BankSettleResultResponseDao.FACTORY );
    regDao(
        webbroker3.aio.data.BankOrderRequestRow.class,
        webbroker3.aio.data.BankOrderRequestDao.FACTORY );
    regDao(
        webbroker3.aio.data.DepositInformRow.class,
        webbroker3.aio.data.DepositInformDao.FACTORY );
    regDao(
        webbroker3.aio.data.AioDepositInformSendMailRow.class,
        webbroker3.aio.data.AioDepositInformSendMailDao.FACTORY );
    regDao(
        webbroker3.aio.data.BankDepositNotifyRow.class,
        webbroker3.aio.data.BankDepositNotifyDao.FACTORY );
    regDao(
        webbroker3.aio.data.BankDepositErrorHistoryRow.class,
        webbroker3.aio.data.BankDepositErrorHistoryDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostPaymentAcceptRow.class,
        webbroker3.aio.data.HostPaymentAcceptDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostPaymentOrderRow.class,
        webbroker3.aio.data.HostPaymentOrderDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostCashTransferRow.class,
        webbroker3.aio.data.HostCashTransferDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostCashTransOrderRow.class,
        webbroker3.aio.data.HostCashTransOrderDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostCashTransOrderAcceptRow.class,
        webbroker3.aio.data.HostCashTransOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostTransferAcceptRow.class,
        webbroker3.aio.data.HostTransferAcceptDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostTransferOrderRow.class,
        webbroker3.aio.data.HostTransferOrderDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostTransferReceiptRow.class,
        webbroker3.aio.data.HostTransferReceiptDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostMrgsecTransNotifyRow.class,
        webbroker3.aio.data.HostMrgsecTransNotifyDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostMrgsecTransAcceptRow.class,
        webbroker3.aio.data.HostMrgsecTransAcceptDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostMrgsecTransferRow.class,
        webbroker3.aio.data.HostMrgsecTransferDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostSpsecTransNotifyRow.class,
        webbroker3.aio.data.HostSpsecTransNotifyDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostSecDeliveryTransferRow.class,
        webbroker3.aio.data.HostSecDeliveryTransferDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostTransferPaymentRow.class,
        webbroker3.aio.data.HostTransferPaymentDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostForeignCashTransferRow.class,
        webbroker3.aio.data.HostForeignCashTransferDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostForeignCashTransOrderRow.class,
        webbroker3.aio.data.HostForeignCashTransOrderDao.FACTORY );
    regDao(
        webbroker3.aio.data.HostFCashTransOrderAcceptRow.class,
        webbroker3.aio.data.HostFCashTransOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.aio.data.PayRequiredAmountRow.class,
        webbroker3.aio.data.PayRequiredAmountDao.FACTORY );
    regDao(
        webbroker3.aio.data.GftAccountOpenStatusRow.class,
        webbroker3.aio.data.GftAccountOpenStatusDao.FACTORY );
    regDao(
        webbroker3.aio.data.GftTransferStatusRow.class,
        webbroker3.aio.data.GftTransferStatusDao.FACTORY );
    regDao(
        webbroker3.aio.data.GftMessageRow.class,
        webbroker3.aio.data.GftMessageDao.FACTORY );
    regDao(
        webbroker3.aio.data.SsoMessageRow.class,
        webbroker3.aio.data.SsoMessageDao.FACTORY );
    regDao(
        webbroker3.aio.data.UwgTransferStatusRow.class,
        webbroker3.aio.data.UwgTransferStatusDao.FACTORY );
    regDao(
        webbroker3.aio.data.UwgAccountOpenStatusRow.class,
        webbroker3.aio.data.UwgAccountOpenStatusDao.FACTORY );
  }

}
@
