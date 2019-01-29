head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.08.49.27;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5cc4d8b0586158f;
filename	WEB3GenTradeAccountDatabaseExtensions.java;

1.1
date	2011.03.14.05.36.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GenTradeAccountDatabaseExtensions.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3GenTradeAccountDatabaseExtensions extends Plugin {

  private WEB3GenTradeAccountDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3GenTradeAccountDatabaseExtensions.class );
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
    regClass(  webbroker3.gentrade.data.InsiderPK.class );
    regClass(  webbroker3.gentrade.data.InsiderParams.class );
    regClass(  webbroker3.gentrade.data.AccountProductOrderStopPK.class );
    regClass(  webbroker3.gentrade.data.AccountProductOrderStopParams.class );
    regClass(  webbroker3.gentrade.data.AccountInfoMstPK.class );
    regClass(  webbroker3.gentrade.data.AccountInfoMstParams.class );
    regClass(  webbroker3.gentrade.data.ExclusiveUseAccountPK.class );
    regClass(  webbroker3.gentrade.data.ExclusiveUseAccountParams.class );
    regClass(  webbroker3.gentrade.data.OrderUnitIntroduceDivPK.class );
    regClass(  webbroker3.gentrade.data.OrderUnitIntroduceDivParams.class );
    regClass(  webbroker3.gentrade.data.SecurityShortageAccountPK.class );
    regClass(  webbroker3.gentrade.data.SecurityShortageAccountParams.class );
    regClass(  webbroker3.gentrade.data.StockSecuredLoanPK.class );
    regClass(  webbroker3.gentrade.data.StockSecuredLoanParams.class );
    regClass(  webbroker3.gentrade.data.SecurityCashoutRestraintPK.class );
    regClass(  webbroker3.gentrade.data.SecurityCashoutRestraintParams.class );
    regClass(  webbroker3.gentrade.data.DocDeliveryManagementPK.class );
    regClass(  webbroker3.gentrade.data.DocDeliveryManagementParams.class );
    regClass(  webbroker3.gentrade.data.AccOpenDivPK.class );
    regClass(  webbroker3.gentrade.data.AccOpenDivParams.class );
    regClass(  webbroker3.gentrade.data.EleDeliveryManagementPK.class );
    regClass(  webbroker3.gentrade.data.EleDeliveryManagementParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "account", "insider",
      webbroker3.gentrade.data.InsiderPK.class,
      webbroker3.gentrade.data.InsiderParams.class,
      null,
      null );
    regDbExtension( "account", "account_product_order_stop",
      webbroker3.gentrade.data.AccountProductOrderStopPK.class,
      webbroker3.gentrade.data.AccountProductOrderStopParams.class,
      null,
      null );
    regDbExtension( "account", "account_info_mst",
      webbroker3.gentrade.data.AccountInfoMstPK.class,
      webbroker3.gentrade.data.AccountInfoMstParams.class,
      null,
      null );
    regDbExtension( "account", "exclusive_use_account",
      webbroker3.gentrade.data.ExclusiveUseAccountPK.class,
      webbroker3.gentrade.data.ExclusiveUseAccountParams.class,
      null,
      null );
    regDbExtension( "account", "order_unit_introduce_div",
      webbroker3.gentrade.data.OrderUnitIntroduceDivPK.class,
      webbroker3.gentrade.data.OrderUnitIntroduceDivParams.class,
      null,
      null );
    regDbExtension( "account", "security_shortage_account",
      webbroker3.gentrade.data.SecurityShortageAccountPK.class,
      webbroker3.gentrade.data.SecurityShortageAccountParams.class,
      null,
      null );
    regDbExtension( "account", "stock_secured_loan",
      webbroker3.gentrade.data.StockSecuredLoanPK.class,
      webbroker3.gentrade.data.StockSecuredLoanParams.class,
      null,
      null );
    regDbExtension( "account", "security_cashout_restraint",
      webbroker3.gentrade.data.SecurityCashoutRestraintPK.class,
      webbroker3.gentrade.data.SecurityCashoutRestraintParams.class,
      null,
      null );
    regDbExtension( "account", "doc_delivery_management",
      webbroker3.gentrade.data.DocDeliveryManagementPK.class,
      webbroker3.gentrade.data.DocDeliveryManagementParams.class,
      null,
      null );
    regDbExtension( "account", "acc_open_div",
      webbroker3.gentrade.data.AccOpenDivPK.class,
      webbroker3.gentrade.data.AccOpenDivParams.class,
      null,
      null );
    regDbExtension( "account", "ele_delivery_management",
      webbroker3.gentrade.data.EleDeliveryManagementPK.class,
      webbroker3.gentrade.data.EleDeliveryManagementParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.gentrade.data.InsiderRow.class,
        webbroker3.gentrade.data.InsiderDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.AccountProductOrderStopRow.class,
        webbroker3.gentrade.data.AccountProductOrderStopDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.AccountInfoMstRow.class,
        webbroker3.gentrade.data.AccountInfoMstDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.ExclusiveUseAccountRow.class,
        webbroker3.gentrade.data.ExclusiveUseAccountDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.OrderUnitIntroduceDivRow.class,
        webbroker3.gentrade.data.OrderUnitIntroduceDivDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.SecurityShortageAccountRow.class,
        webbroker3.gentrade.data.SecurityShortageAccountDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.StockSecuredLoanRow.class,
        webbroker3.gentrade.data.StockSecuredLoanDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.SecurityCashoutRestraintRow.class,
        webbroker3.gentrade.data.SecurityCashoutRestraintDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.DocDeliveryManagementRow.class,
        webbroker3.gentrade.data.DocDeliveryManagementDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.AccOpenDivRow.class,
        webbroker3.gentrade.data.AccOpenDivDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.EleDeliveryManagementRow.class,
        webbroker3.gentrade.data.EleDeliveryManagementDao.FACTORY );
  }

}
@


1.1
log
@*** empty log message ***
@
text
@d45 2
d100 5
d138 3
@

