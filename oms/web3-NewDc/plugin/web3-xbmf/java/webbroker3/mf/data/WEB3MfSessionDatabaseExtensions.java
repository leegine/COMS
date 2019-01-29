head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MfSessionDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3MfSessionDatabaseExtensions extends Plugin {

  private WEB3MfSessionDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3MfSessionDatabaseExtensions.class );
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
    regClass(  webbroker3.mf.data.HostXbmfCancelAcceptPK.class );
    regClass(  webbroker3.mf.data.HostXbmfCancelAcceptParams.class );
    regClass(  webbroker3.mf.data.HostXbmfOrderPK.class );
    regClass(  webbroker3.mf.data.HostXbmfOrderParams.class );
    regClass(  webbroker3.mf.data.HostXbmfOrderCancelPK.class );
    regClass(  webbroker3.mf.data.HostXbmfOrderCancelParams.class );
    regClass(  webbroker3.mf.data.HostXbmfOrderAcceptPK.class );
    regClass(  webbroker3.mf.data.HostXbmfOrderAcceptParams.class );
    regClass(  webbroker3.mf.data.HostXbmfOrderNotifyPK.class );
    regClass(  webbroker3.mf.data.HostXbmfOrderNotifyParams.class );
    regClass(  webbroker3.mf.data.HostFrgnMmfOrderAcceptPK.class );
    regClass(  webbroker3.mf.data.HostFrgnMmfOrderAcceptParams.class );
    regClass(  webbroker3.mf.data.HostFrgnMmfOrderPK.class );
    regClass(  webbroker3.mf.data.HostFrgnMmfOrderParams.class );
    regClass(  webbroker3.mf.data.MutualFundExchangeRatePK.class );
    regClass(  webbroker3.mf.data.MutualFundExchangeRateParams.class );
    regClass(  webbroker3.mf.data.MfFixedBuyingCondPK.class );
    regClass(  webbroker3.mf.data.MfFixedBuyingCondParams.class );
    regClass(  webbroker3.mf.data.MfFixedBuyingDrawAccountPK.class );
    regClass(  webbroker3.mf.data.MfFixedBuyingDrawAccountParams.class );
    regClass(  webbroker3.mf.data.MfFixedBuyingChangeHistPK.class );
    regClass(  webbroker3.mf.data.MfFixedBuyingChangeHistParams.class );
    regClass(  webbroker3.mf.data.MfFixedBuyingChangePK.class );
    regClass(  webbroker3.mf.data.MfFixedBuyingChangeParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "session", "host_xbmf_cancel_accept",
      webbroker3.mf.data.HostXbmfCancelAcceptPK.class,
      webbroker3.mf.data.HostXbmfCancelAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_xbmf_order",
      webbroker3.mf.data.HostXbmfOrderPK.class,
      webbroker3.mf.data.HostXbmfOrderParams.class,
      null,
      null );
    regDbExtension( "session", "host_xbmf_order_cancel",
      webbroker3.mf.data.HostXbmfOrderCancelPK.class,
      webbroker3.mf.data.HostXbmfOrderCancelParams.class,
      null,
      null );
    regDbExtension( "session", "host_xbmf_order_accept",
      webbroker3.mf.data.HostXbmfOrderAcceptPK.class,
      webbroker3.mf.data.HostXbmfOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_xbmf_order_notify",
      webbroker3.mf.data.HostXbmfOrderNotifyPK.class,
      webbroker3.mf.data.HostXbmfOrderNotifyParams.class,
      null,
      null );
    regDbExtension( "session", "host_frgn_mmf_order_accept",
      webbroker3.mf.data.HostFrgnMmfOrderAcceptPK.class,
      webbroker3.mf.data.HostFrgnMmfOrderAcceptParams.class,
      null,
      null );
    regDbExtension( "session", "host_frgn_mmf_order",
      webbroker3.mf.data.HostFrgnMmfOrderPK.class,
      webbroker3.mf.data.HostFrgnMmfOrderParams.class,
      null,
      null );
    regDbExtension( "session", "mutual_fund_exchange_rate",
      webbroker3.mf.data.MutualFundExchangeRatePK.class,
      webbroker3.mf.data.MutualFundExchangeRateParams.class,
      null,
      null );
    regDbExtension( "session", "mf_fixed_buying_cond",
      webbroker3.mf.data.MfFixedBuyingCondPK.class,
      webbroker3.mf.data.MfFixedBuyingCondParams.class,
      null,
      null );
    regDbExtension( "session", "mf_fixed_buying_draw_account",
      webbroker3.mf.data.MfFixedBuyingDrawAccountPK.class,
      webbroker3.mf.data.MfFixedBuyingDrawAccountParams.class,
      null,
      null );
    regDbExtension( "session", "mf_fixed_buying_change_hist",
      webbroker3.mf.data.MfFixedBuyingChangeHistPK.class,
      webbroker3.mf.data.MfFixedBuyingChangeHistParams.class,
      null,
      null );
    regDbExtension( "session", "mf_fixed_buying_change",
      webbroker3.mf.data.MfFixedBuyingChangePK.class,
      webbroker3.mf.data.MfFixedBuyingChangeParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.mf.data.HostXbmfCancelAcceptRow.class,
        webbroker3.mf.data.HostXbmfCancelAcceptDao.FACTORY );
    regDao(
        webbroker3.mf.data.HostXbmfOrderRow.class,
        webbroker3.mf.data.HostXbmfOrderDao.FACTORY );
    regDao(
        webbroker3.mf.data.HostXbmfOrderCancelRow.class,
        webbroker3.mf.data.HostXbmfOrderCancelDao.FACTORY );
    regDao(
        webbroker3.mf.data.HostXbmfOrderAcceptRow.class,
        webbroker3.mf.data.HostXbmfOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.mf.data.HostXbmfOrderNotifyRow.class,
        webbroker3.mf.data.HostXbmfOrderNotifyDao.FACTORY );
    regDao(
        webbroker3.mf.data.HostFrgnMmfOrderAcceptRow.class,
        webbroker3.mf.data.HostFrgnMmfOrderAcceptDao.FACTORY );
    regDao(
        webbroker3.mf.data.HostFrgnMmfOrderRow.class,
        webbroker3.mf.data.HostFrgnMmfOrderDao.FACTORY );
    regDao(
        webbroker3.mf.data.MutualFundExchangeRateRow.class,
        webbroker3.mf.data.MutualFundExchangeRateDao.FACTORY );
    regDao(
        webbroker3.mf.data.MfFixedBuyingCondRow.class,
        webbroker3.mf.data.MfFixedBuyingCondDao.FACTORY );
    regDao(
        webbroker3.mf.data.MfFixedBuyingDrawAccountRow.class,
        webbroker3.mf.data.MfFixedBuyingDrawAccountDao.FACTORY );
    regDao(
        webbroker3.mf.data.MfFixedBuyingChangeHistRow.class,
        webbroker3.mf.data.MfFixedBuyingChangeHistDao.FACTORY );
    regDao(
        webbroker3.mf.data.MfFixedBuyingChangeRow.class,
        webbroker3.mf.data.MfFixedBuyingChangeDao.FACTORY );
  }

}
@
