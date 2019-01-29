head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.33.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GenTradeMasterDatabaseExtensions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.boot.*;

public final class WEB3GenTradeMasterDatabaseExtensions extends Plugin {

  private WEB3GenTradeMasterDatabaseExtensions() {}

  public static void plug() throws Exception {
    plug( WEB3GenTradeMasterDatabaseExtensions.class );
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
    regClass(  webbroker3.gentrade.data.CalendarPK.class );
    regClass(  webbroker3.gentrade.data.CalendarParams.class );
    regClass(  webbroker3.gentrade.data.TradingTimePK.class );
    regClass(  webbroker3.gentrade.data.TradingTimeParams.class );
    regClass(  webbroker3.gentrade.data.TaxRateTablePK.class );
    regClass(  webbroker3.gentrade.data.TaxRateTableParams.class );
    regClass(  webbroker3.gentrade.data.OrderAcceptStatusPK.class );
    regClass(  webbroker3.gentrade.data.OrderAcceptStatusParams.class );
    regClass(  webbroker3.gentrade.data.InstBranchProductPK.class );
    regClass(  webbroker3.gentrade.data.InstBranchProductParams.class );
    regClass(  webbroker3.gentrade.data.BranchMarketDealtCondPK.class );
    regClass(  webbroker3.gentrade.data.BranchMarketDealtCondParams.class );
    regClass(  webbroker3.gentrade.data.BranchIndexDealtCondPK.class );
    regClass(  webbroker3.gentrade.data.BranchIndexDealtCondParams.class );
    regClass(  webbroker3.gentrade.data.EnableOrderConditionPK.class );
    regClass(  webbroker3.gentrade.data.EnableOrderConditionParams.class );
    regClass(  webbroker3.gentrade.data.EquityCommAccountCondMstPK.class );
    regClass(  webbroker3.gentrade.data.EquityCommAccountCondMstParams.class );
    regClass(  webbroker3.gentrade.data.EquityCommRevMstPK.class );
    regClass(  webbroker3.gentrade.data.EquityCommRevMstParams.class );
    regClass(  webbroker3.gentrade.data.EquityCommCondPK.class );
    regClass(  webbroker3.gentrade.data.EquityCommCondParams.class );
    regClass(  webbroker3.gentrade.data.EquityCommCondMstPK.class );
    regClass(  webbroker3.gentrade.data.EquityCommCondMstParams.class );
    regClass(  webbroker3.gentrade.data.OrderexecutionEndPK.class );
    regClass(  webbroker3.gentrade.data.OrderexecutionEndParams.class );
    regClass(  webbroker3.gentrade.data.AdministratorPK.class );
    regClass(  webbroker3.gentrade.data.AdministratorParams.class );
    regClass(  webbroker3.gentrade.data.BranchMarketRepayDealtCondPK.class );
    regClass(  webbroker3.gentrade.data.BranchMarketRepayDealtCondParams.class );
    regClass(  webbroker3.gentrade.data.TradingpowerCalcConditionPK.class );
    regClass(  webbroker3.gentrade.data.TradingpowerCalcConditionParams.class );
    regClass(  webbroker3.gentrade.data.FinInstitutionPK.class );
    regClass(  webbroker3.gentrade.data.FinInstitutionParams.class );
    regClass(  webbroker3.gentrade.data.TransferedFinInstitutionPK.class );
    regClass(  webbroker3.gentrade.data.TransferedFinInstitutionParams.class );
    regClass(  webbroker3.gentrade.data.FinInstitutionAvailableParams.class );
    regClass(  webbroker3.gentrade.data.MailInfoPK.class );
    regClass(  webbroker3.gentrade.data.MailInfoParams.class );
    regClass(  webbroker3.gentrade.data.SrvRegiApplicationPK.class );
    regClass(  webbroker3.gentrade.data.SrvRegiApplicationParams.class );
    regClass(  webbroker3.gentrade.data.AdministratorTypePK.class );
    regClass(  webbroker3.gentrade.data.AdministratorTypeParams.class );
    regClass(  webbroker3.gentrade.data.AdminPermissionPK.class );
    regClass(  webbroker3.gentrade.data.AdminPermissionParams.class );
    regClass(  webbroker3.gentrade.data.ProcessManagementPK.class );
    regClass(  webbroker3.gentrade.data.ProcessManagementParams.class );
    regClass(  webbroker3.gentrade.data.YellowCustomerPK.class );
    regClass(  webbroker3.gentrade.data.YellowCustomerParams.class );
    regClass(  webbroker3.gentrade.data.FinInstitutionBankPK.class );
    regClass(  webbroker3.gentrade.data.FinInstitutionBankParams.class );
    regClass(  webbroker3.gentrade.data.DailyAssetPK.class );
    regClass(  webbroker3.gentrade.data.DailyAssetParams.class );
    regClass(  webbroker3.gentrade.data.QuestionPK.class );
    regClass(  webbroker3.gentrade.data.QuestionParams.class );
    regClass(  webbroker3.gentrade.data.OtherOrgSrvTimePK.class );
    regClass(  webbroker3.gentrade.data.OtherOrgSrvTimeParams.class );
    regClass(  webbroker3.gentrade.data.OtherOrgOutOfSrvDatePK.class );
    regClass(  webbroker3.gentrade.data.OtherOrgOutOfSrvDateParams.class );
    regClass(  webbroker3.gentrade.data.OtherOrgOutOfSrvWeekPK.class );
    regClass(  webbroker3.gentrade.data.OtherOrgOutOfSrvWeekParams.class );
    regClass(  webbroker3.gentrade.data.LastClosingPricePK.class );
    regClass(  webbroker3.gentrade.data.LastClosingPriceParams.class );
    regClass(  webbroker3.gentrade.data.BatoFunctionPrefPK.class );
    regClass(  webbroker3.gentrade.data.BatoFunctionPrefParams.class );
    regClass(  webbroker3.gentrade.data.BatoBranchProductPrefPK.class );
    regClass(  webbroker3.gentrade.data.BatoBranchProductPrefParams.class );
    regClass(  webbroker3.gentrade.data.BatoInstBranchPrefPK.class );
    regClass(  webbroker3.gentrade.data.BatoInstBranchPrefParams.class );
    regClass(  webbroker3.gentrade.data.BatoDoctypeManagementPK.class );
    regClass(  webbroker3.gentrade.data.BatoDoctypeManagementParams.class );
    regClass(  webbroker3.gentrade.data.OrderSwitchingPK.class );
    regClass(  webbroker3.gentrade.data.OrderSwitchingParams.class );
    regClass(  webbroker3.gentrade.data.RequestCodesForReadPK.class );
    regClass(  webbroker3.gentrade.data.RequestCodesForReadParams.class );
    regClass(  webbroker3.gentrade.data.ProductEstimationRatioPK.class );
    regClass(  webbroker3.gentrade.data.ProductEstimationRatioParams.class );
    regClass(  webbroker3.gentrade.data.BranchPreferencesPK.class );
    regClass(  webbroker3.gentrade.data.BranchPreferencesParams.class );
    regClass(  webbroker3.gentrade.data.FeqBranchMarketDealtCondPK.class );
    regClass(  webbroker3.gentrade.data.FeqBranchMarketDealtCondParams.class );
    regClass(  webbroker3.gentrade.data.FeqCalendarPK.class );
    regClass(  webbroker3.gentrade.data.FeqCalendarParams.class );
    regClass(  webbroker3.gentrade.data.VirtualServerInformationPK.class );
    regClass(  webbroker3.gentrade.data.VirtualServerInformationParams.class );
    regClass(  webbroker3.gentrade.data.VirtualServerChangePK.class );
    regClass(  webbroker3.gentrade.data.VirtualServerChangeParams.class );
    regClass(  webbroker3.gentrade.data.ExchangeRatePK.class );
    regClass(  webbroker3.gentrade.data.ExchangeRateParams.class );
    regClass(  webbroker3.gentrade.data.CodeTranslationPK.class );
    regClass(  webbroker3.gentrade.data.CodeTranslationParams.class );
    regClass(  webbroker3.gentrade.data.ExclusiveUseAccountCondPK.class );
    regClass(  webbroker3.gentrade.data.ExclusiveUseAccountCondParams.class );
    regClass(  webbroker3.gentrade.data.SoapConnectPrefMsgPK.class );
    regClass(  webbroker3.gentrade.data.SoapConnectPrefMsgParams.class );
    regClass(  webbroker3.gentrade.data.SoapConnectPrefRpcPK.class );
    regClass(  webbroker3.gentrade.data.SoapConnectPrefRpcParams.class );
    regClass(  webbroker3.gentrade.data.GenCurrencyPK.class );
    regClass(  webbroker3.gentrade.data.GenCurrencyParams.class );
    regClass(  webbroker3.gentrade.data.StockOptionProductPK.class );
    regClass(  webbroker3.gentrade.data.StockOptionProductParams.class );
    regClass(  webbroker3.gentrade.data.CommCodeChgMstPK.class );
    regClass(  webbroker3.gentrade.data.CommCodeChgMstParams.class );
    regClass(  webbroker3.gentrade.data.MarketMarginRatioPK.class );
    regClass(  webbroker3.gentrade.data.MarketMarginRatioParams.class );
    regClass(  webbroker3.gentrade.data.FrgnMmfExchangeRatePK.class );
    regClass(  webbroker3.gentrade.data.FrgnMmfExchangeRateParams.class );
    regClass(  webbroker3.gentrade.data.FTransFinInstitutionPK.class );
    regClass(  webbroker3.gentrade.data.FTransFinInstitutionParams.class );
    regClass(  webbroker3.gentrade.data.DirectDebitParams.class );
    regClass(  webbroker3.gentrade.data.LoginWdogAttributePK.class );
    regClass(  webbroker3.gentrade.data.LoginWdogAttributeParams.class );
    regClass(  webbroker3.gentrade.data.BranchListmarketDealtCondPK.class );
    regClass(  webbroker3.gentrade.data.BranchListmarketDealtCondParams.class );
    regClass(  webbroker3.gentrade.data.DocDivManagementPK.class );
    regClass(  webbroker3.gentrade.data.DocDivManagementParams.class );
    regClass(  webbroker3.gentrade.data.BatoProductManagementPK.class );
    regClass(  webbroker3.gentrade.data.BatoProductManagementParams.class );
    regClass(  webbroker3.gentrade.data.SonarTraderPK.class );
    regClass(  webbroker3.gentrade.data.SonarTraderParams.class );
    regClass(  webbroker3.gentrade.data.FeqLastClosingPricePK.class );
    regClass(  webbroker3.gentrade.data.FeqLastClosingPriceParams.class );
    regClass(  webbroker3.gentrade.data.BranchMarketPtsDealtCondPK.class );
    regClass(  webbroker3.gentrade.data.BranchMarketPtsDealtCondParams.class );
    regClass(  webbroker3.gentrade.data.PtsOrderexecutionEndPK.class );
    regClass(  webbroker3.gentrade.data.PtsOrderexecutionEndParams.class );
    regClass(  webbroker3.gentrade.data.DocCategoryManagementPK.class );
    regClass(  webbroker3.gentrade.data.DocCategoryManagementParams.class );
    regClass(  webbroker3.gentrade.data.InstitutionPreferencesPK.class );
    regClass(  webbroker3.gentrade.data.InstitutionPreferencesParams.class );
    regClass(  webbroker3.gentrade.data.EraPK.class );
    regClass(  webbroker3.gentrade.data.EraParams.class );
  }

  private static void regDbExtensions() throws Exception {
    regDbExtension( "master", "calendar",
      webbroker3.gentrade.data.CalendarPK.class,
      webbroker3.gentrade.data.CalendarParams.class,
      null,
      null );
    regDbExtension( "master", "trading_time",
      webbroker3.gentrade.data.TradingTimePK.class,
      webbroker3.gentrade.data.TradingTimeParams.class,
      null,
      null );
    regDbExtension( "master", "tax_rate_table",
      webbroker3.gentrade.data.TaxRateTablePK.class,
      webbroker3.gentrade.data.TaxRateTableParams.class,
      null,
      null );
    regDbExtension( "master", "order_accept_status",
      webbroker3.gentrade.data.OrderAcceptStatusPK.class,
      webbroker3.gentrade.data.OrderAcceptStatusParams.class,
      null,
      null );
    regDbExtension( "master", "inst_branch_product",
      webbroker3.gentrade.data.InstBranchProductPK.class,
      webbroker3.gentrade.data.InstBranchProductParams.class,
      null,
      null );
    regDbExtension( "master", "branch_market_dealt_cond",
      webbroker3.gentrade.data.BranchMarketDealtCondPK.class,
      webbroker3.gentrade.data.BranchMarketDealtCondParams.class,
      null,
      null );
    regDbExtension( "master", "branch_index_dealt_cond",
      webbroker3.gentrade.data.BranchIndexDealtCondPK.class,
      webbroker3.gentrade.data.BranchIndexDealtCondParams.class,
      null,
      null );
    regDbExtension( "master", "enable_order_condition",
      webbroker3.gentrade.data.EnableOrderConditionPK.class,
      webbroker3.gentrade.data.EnableOrderConditionParams.class,
      null,
      null );
    regDbExtension( "master", "equity_comm_account_cond_mst",
      webbroker3.gentrade.data.EquityCommAccountCondMstPK.class,
      webbroker3.gentrade.data.EquityCommAccountCondMstParams.class,
      null,
      null );
    regDbExtension( "master", "equity_comm_rev_mst",
      webbroker3.gentrade.data.EquityCommRevMstPK.class,
      webbroker3.gentrade.data.EquityCommRevMstParams.class,
      null,
      null );
    regDbExtension( "master", "equity_comm_cond",
      webbroker3.gentrade.data.EquityCommCondPK.class,
      webbroker3.gentrade.data.EquityCommCondParams.class,
      null,
      null );
    regDbExtension( "master", "equity_comm_cond_mst",
      webbroker3.gentrade.data.EquityCommCondMstPK.class,
      webbroker3.gentrade.data.EquityCommCondMstParams.class,
      null,
      null );
    regDbExtension( "master", "orderexecution_end",
      webbroker3.gentrade.data.OrderexecutionEndPK.class,
      webbroker3.gentrade.data.OrderexecutionEndParams.class,
      null,
      null );
    regDbExtension( "master", "administrator",
      webbroker3.gentrade.data.AdministratorPK.class,
      webbroker3.gentrade.data.AdministratorParams.class,
      null,
      null );
    regDbExtension( "master", "branch_market_repay_dealt_cond",
      webbroker3.gentrade.data.BranchMarketRepayDealtCondPK.class,
      webbroker3.gentrade.data.BranchMarketRepayDealtCondParams.class,
      null,
      null );
    regDbExtension( "master", "tradingpower_calc_condition",
      webbroker3.gentrade.data.TradingpowerCalcConditionPK.class,
      webbroker3.gentrade.data.TradingpowerCalcConditionParams.class,
      null,
      null );
    regDbExtension( "master", "fin_institution",
      webbroker3.gentrade.data.FinInstitutionPK.class,
      webbroker3.gentrade.data.FinInstitutionParams.class,
      null,
      null );
    regDbExtension( "master", "transfered_fin_institution",
      webbroker3.gentrade.data.TransferedFinInstitutionPK.class,
      webbroker3.gentrade.data.TransferedFinInstitutionParams.class,
      null,
      null );
    regDbExtension( "master", "fin_institution_available",
      null,
      webbroker3.gentrade.data.FinInstitutionAvailableParams.class,
      null,
      null );
    regDbExtension( "master", "mail_info",
      webbroker3.gentrade.data.MailInfoPK.class,
      webbroker3.gentrade.data.MailInfoParams.class,
      null,
      null );
    regDbExtension( "master", "srv_regi_application",
      webbroker3.gentrade.data.SrvRegiApplicationPK.class,
      webbroker3.gentrade.data.SrvRegiApplicationParams.class,
      null,
      null );
    regDbExtension( "master", "administrator_type",
      webbroker3.gentrade.data.AdministratorTypePK.class,
      webbroker3.gentrade.data.AdministratorTypeParams.class,
      null,
      null );
    regDbExtension( "master", "admin_permission",
      webbroker3.gentrade.data.AdminPermissionPK.class,
      webbroker3.gentrade.data.AdminPermissionParams.class,
      null,
      null );
    regDbExtension( "master", "process_management",
      webbroker3.gentrade.data.ProcessManagementPK.class,
      webbroker3.gentrade.data.ProcessManagementParams.class,
      null,
      null );
    regDbExtension( "master", "yellow_customer",
      webbroker3.gentrade.data.YellowCustomerPK.class,
      webbroker3.gentrade.data.YellowCustomerParams.class,
      null,
      null );
    regDbExtension( "master", "fin_institution_bank",
      webbroker3.gentrade.data.FinInstitutionBankPK.class,
      webbroker3.gentrade.data.FinInstitutionBankParams.class,
      null,
      null );
    regDbExtension( "master", "daily_asset",
      webbroker3.gentrade.data.DailyAssetPK.class,
      webbroker3.gentrade.data.DailyAssetParams.class,
      null,
      null );
    regDbExtension( "master", "question",
      webbroker3.gentrade.data.QuestionPK.class,
      webbroker3.gentrade.data.QuestionParams.class,
      null,
      null );
    regDbExtension( "master", "other_org_srv_time",
      webbroker3.gentrade.data.OtherOrgSrvTimePK.class,
      webbroker3.gentrade.data.OtherOrgSrvTimeParams.class,
      null,
      null );
    regDbExtension( "master", "other_org_out_of_srv_date",
      webbroker3.gentrade.data.OtherOrgOutOfSrvDatePK.class,
      webbroker3.gentrade.data.OtherOrgOutOfSrvDateParams.class,
      null,
      null );
    regDbExtension( "master", "other_org_out_of_srv_week",
      webbroker3.gentrade.data.OtherOrgOutOfSrvWeekPK.class,
      webbroker3.gentrade.data.OtherOrgOutOfSrvWeekParams.class,
      null,
      null );
    regDbExtension( "master", "last_closing_price",
      webbroker3.gentrade.data.LastClosingPricePK.class,
      webbroker3.gentrade.data.LastClosingPriceParams.class,
      null,
      null );
    regDbExtension( "master", "bato_function_pref",
      webbroker3.gentrade.data.BatoFunctionPrefPK.class,
      webbroker3.gentrade.data.BatoFunctionPrefParams.class,
      null,
      null );
    regDbExtension( "master", "bato_branch_product_pref",
      webbroker3.gentrade.data.BatoBranchProductPrefPK.class,
      webbroker3.gentrade.data.BatoBranchProductPrefParams.class,
      null,
      null );
    regDbExtension( "master", "bato_inst_branch_pref",
      webbroker3.gentrade.data.BatoInstBranchPrefPK.class,
      webbroker3.gentrade.data.BatoInstBranchPrefParams.class,
      null,
      null );
    regDbExtension( "master", "bato_doctype_management",
      webbroker3.gentrade.data.BatoDoctypeManagementPK.class,
      webbroker3.gentrade.data.BatoDoctypeManagementParams.class,
      null,
      null );
    regDbExtension( "master", "order_switching",
      webbroker3.gentrade.data.OrderSwitchingPK.class,
      webbroker3.gentrade.data.OrderSwitchingParams.class,
      null,
      null );
    regDbExtension( "master", "request_codes_for_read",
      webbroker3.gentrade.data.RequestCodesForReadPK.class,
      webbroker3.gentrade.data.RequestCodesForReadParams.class,
      null,
      null );
    regDbExtension( "master", "product_estimation_ratio",
      webbroker3.gentrade.data.ProductEstimationRatioPK.class,
      webbroker3.gentrade.data.ProductEstimationRatioParams.class,
      null,
      null );
    regDbExtension( "master", "branch_preferences",
      webbroker3.gentrade.data.BranchPreferencesPK.class,
      webbroker3.gentrade.data.BranchPreferencesParams.class,
      null,
      null );
    regDbExtension( "master", "feq_branch_market_dealt_cond",
      webbroker3.gentrade.data.FeqBranchMarketDealtCondPK.class,
      webbroker3.gentrade.data.FeqBranchMarketDealtCondParams.class,
      null,
      null );
    regDbExtension( "master", "feq_calendar",
      webbroker3.gentrade.data.FeqCalendarPK.class,
      webbroker3.gentrade.data.FeqCalendarParams.class,
      null,
      null );
    regDbExtension( "master", "virtual_server_information",
      webbroker3.gentrade.data.VirtualServerInformationPK.class,
      webbroker3.gentrade.data.VirtualServerInformationParams.class,
      null,
      null );
    regDbExtension( "master", "virtual_server_change",
      webbroker3.gentrade.data.VirtualServerChangePK.class,
      webbroker3.gentrade.data.VirtualServerChangeParams.class,
      null,
      null );
    regDbExtension( "master", "exchange_rate",
      webbroker3.gentrade.data.ExchangeRatePK.class,
      webbroker3.gentrade.data.ExchangeRateParams.class,
      null,
      null );
    regDbExtension( "master", "code_translation",
      webbroker3.gentrade.data.CodeTranslationPK.class,
      webbroker3.gentrade.data.CodeTranslationParams.class,
      null,
      null );
    regDbExtension( "master", "exclusive_use_account_cond",
      webbroker3.gentrade.data.ExclusiveUseAccountCondPK.class,
      webbroker3.gentrade.data.ExclusiveUseAccountCondParams.class,
      null,
      null );
    regDbExtension( "master", "soap_connect_pref_msg",
      webbroker3.gentrade.data.SoapConnectPrefMsgPK.class,
      webbroker3.gentrade.data.SoapConnectPrefMsgParams.class,
      null,
      null );
    regDbExtension( "master", "soap_connect_pref_rpc",
      webbroker3.gentrade.data.SoapConnectPrefRpcPK.class,
      webbroker3.gentrade.data.SoapConnectPrefRpcParams.class,
      null,
      null );
    regDbExtension( "master", "gen_currency",
      webbroker3.gentrade.data.GenCurrencyPK.class,
      webbroker3.gentrade.data.GenCurrencyParams.class,
      null,
      null );
    regDbExtension( "master", "stock_option_product",
      webbroker3.gentrade.data.StockOptionProductPK.class,
      webbroker3.gentrade.data.StockOptionProductParams.class,
      null,
      null );
    regDbExtension( "master", "comm_code_chg_mst",
      webbroker3.gentrade.data.CommCodeChgMstPK.class,
      webbroker3.gentrade.data.CommCodeChgMstParams.class,
      null,
      null );
    regDbExtension( "master", "market_margin_ratio",
      webbroker3.gentrade.data.MarketMarginRatioPK.class,
      webbroker3.gentrade.data.MarketMarginRatioParams.class,
      null,
      null );
    regDbExtension( "master", "frgn_mmf_exchange_rate",
      webbroker3.gentrade.data.FrgnMmfExchangeRatePK.class,
      webbroker3.gentrade.data.FrgnMmfExchangeRateParams.class,
      null,
      null );
    regDbExtension( "master", "f_trans_fin_institution",
      webbroker3.gentrade.data.FTransFinInstitutionPK.class,
      webbroker3.gentrade.data.FTransFinInstitutionParams.class,
      null,
      null );
    regDbExtension( "master", "direct_debit",
      null,
      webbroker3.gentrade.data.DirectDebitParams.class,
      null,
      null );
    regDbExtension( "master", "login_wdog_attribute",
      webbroker3.gentrade.data.LoginWdogAttributePK.class,
      webbroker3.gentrade.data.LoginWdogAttributeParams.class,
      null,
      null );
    regDbExtension( "master", "branch_listmarket_dealt_cond",
      webbroker3.gentrade.data.BranchListmarketDealtCondPK.class,
      webbroker3.gentrade.data.BranchListmarketDealtCondParams.class,
      null,
      null );
    regDbExtension( "master", "doc_div_management",
      webbroker3.gentrade.data.DocDivManagementPK.class,
      webbroker3.gentrade.data.DocDivManagementParams.class,
      null,
      null );
    regDbExtension( "master", "bato_product_management",
      webbroker3.gentrade.data.BatoProductManagementPK.class,
      webbroker3.gentrade.data.BatoProductManagementParams.class,
      null,
      null );
    regDbExtension( "master", "sonar_trader",
      webbroker3.gentrade.data.SonarTraderPK.class,
      webbroker3.gentrade.data.SonarTraderParams.class,
      null,
      null );
    regDbExtension( "master", "feq_last_closing_price",
      webbroker3.gentrade.data.FeqLastClosingPricePK.class,
      webbroker3.gentrade.data.FeqLastClosingPriceParams.class,
      null,
      null );
    regDbExtension( "master", "branch_market_pts_dealt_cond",
      webbroker3.gentrade.data.BranchMarketPtsDealtCondPK.class,
      webbroker3.gentrade.data.BranchMarketPtsDealtCondParams.class,
      null,
      null );
    regDbExtension( "master", "pts_orderexecution_end",
      webbroker3.gentrade.data.PtsOrderexecutionEndPK.class,
      webbroker3.gentrade.data.PtsOrderexecutionEndParams.class,
      null,
      null );
    regDbExtension( "master", "doc_category_management",
      webbroker3.gentrade.data.DocCategoryManagementPK.class,
      webbroker3.gentrade.data.DocCategoryManagementParams.class,
      null,
      null );
    regDbExtension( "master", "institution_preferences",
      webbroker3.gentrade.data.InstitutionPreferencesPK.class,
      webbroker3.gentrade.data.InstitutionPreferencesParams.class,
      null,
      null );
    regDbExtension( "master", "era",
      webbroker3.gentrade.data.EraPK.class,
      webbroker3.gentrade.data.EraParams.class,
      null,
      null );
  }

  private static void regDataObjectClasses() throws Exception {
    regDao(
        webbroker3.gentrade.data.CalendarRow.class,
        webbroker3.gentrade.data.CalendarDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.TradingTimeRow.class,
        webbroker3.gentrade.data.TradingTimeDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.TaxRateTableRow.class,
        webbroker3.gentrade.data.TaxRateTableDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.OrderAcceptStatusRow.class,
        webbroker3.gentrade.data.OrderAcceptStatusDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.InstBranchProductRow.class,
        webbroker3.gentrade.data.InstBranchProductDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.BranchMarketDealtCondRow.class,
        webbroker3.gentrade.data.BranchMarketDealtCondDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.BranchIndexDealtCondRow.class,
        webbroker3.gentrade.data.BranchIndexDealtCondDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.EnableOrderConditionRow.class,
        webbroker3.gentrade.data.EnableOrderConditionDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.EquityCommAccountCondMstRow.class,
        webbroker3.gentrade.data.EquityCommAccountCondMstDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.EquityCommRevMstRow.class,
        webbroker3.gentrade.data.EquityCommRevMstDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.EquityCommCondRow.class,
        webbroker3.gentrade.data.EquityCommCondDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.EquityCommCondMstRow.class,
        webbroker3.gentrade.data.EquityCommCondMstDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.OrderexecutionEndRow.class,
        webbroker3.gentrade.data.OrderexecutionEndDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.AdministratorRow.class,
        webbroker3.gentrade.data.AdministratorDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.BranchMarketRepayDealtCondRow.class,
        webbroker3.gentrade.data.BranchMarketRepayDealtCondDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.TradingpowerCalcConditionRow.class,
        webbroker3.gentrade.data.TradingpowerCalcConditionDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.FinInstitutionRow.class,
        webbroker3.gentrade.data.FinInstitutionDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.TransferedFinInstitutionRow.class,
        webbroker3.gentrade.data.TransferedFinInstitutionDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.FinInstitutionAvailableRow.class,
        webbroker3.gentrade.data.FinInstitutionAvailableDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.MailInfoRow.class,
        webbroker3.gentrade.data.MailInfoDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.SrvRegiApplicationRow.class,
        webbroker3.gentrade.data.SrvRegiApplicationDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.AdministratorTypeRow.class,
        webbroker3.gentrade.data.AdministratorTypeDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.AdminPermissionRow.class,
        webbroker3.gentrade.data.AdminPermissionDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.ProcessManagementRow.class,
        webbroker3.gentrade.data.ProcessManagementDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.YellowCustomerRow.class,
        webbroker3.gentrade.data.YellowCustomerDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.FinInstitutionBankRow.class,
        webbroker3.gentrade.data.FinInstitutionBankDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.DailyAssetRow.class,
        webbroker3.gentrade.data.DailyAssetDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.QuestionRow.class,
        webbroker3.gentrade.data.QuestionDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.OtherOrgSrvTimeRow.class,
        webbroker3.gentrade.data.OtherOrgSrvTimeDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.OtherOrgOutOfSrvDateRow.class,
        webbroker3.gentrade.data.OtherOrgOutOfSrvDateDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.OtherOrgOutOfSrvWeekRow.class,
        webbroker3.gentrade.data.OtherOrgOutOfSrvWeekDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.LastClosingPriceRow.class,
        webbroker3.gentrade.data.LastClosingPriceDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.BatoFunctionPrefRow.class,
        webbroker3.gentrade.data.BatoFunctionPrefDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.BatoBranchProductPrefRow.class,
        webbroker3.gentrade.data.BatoBranchProductPrefDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.BatoInstBranchPrefRow.class,
        webbroker3.gentrade.data.BatoInstBranchPrefDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.BatoDoctypeManagementRow.class,
        webbroker3.gentrade.data.BatoDoctypeManagementDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.OrderSwitchingRow.class,
        webbroker3.gentrade.data.OrderSwitchingDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.RequestCodesForReadRow.class,
        webbroker3.gentrade.data.RequestCodesForReadDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.ProductEstimationRatioRow.class,
        webbroker3.gentrade.data.ProductEstimationRatioDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.BranchPreferencesRow.class,
        webbroker3.gentrade.data.BranchPreferencesDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.FeqBranchMarketDealtCondRow.class,
        webbroker3.gentrade.data.FeqBranchMarketDealtCondDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.FeqCalendarRow.class,
        webbroker3.gentrade.data.FeqCalendarDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.VirtualServerInformationRow.class,
        webbroker3.gentrade.data.VirtualServerInformationDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.VirtualServerChangeRow.class,
        webbroker3.gentrade.data.VirtualServerChangeDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.ExchangeRateRow.class,
        webbroker3.gentrade.data.ExchangeRateDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.CodeTranslationRow.class,
        webbroker3.gentrade.data.CodeTranslationDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.ExclusiveUseAccountCondRow.class,
        webbroker3.gentrade.data.ExclusiveUseAccountCondDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.SoapConnectPrefMsgRow.class,
        webbroker3.gentrade.data.SoapConnectPrefMsgDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.SoapConnectPrefRpcRow.class,
        webbroker3.gentrade.data.SoapConnectPrefRpcDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.GenCurrencyRow.class,
        webbroker3.gentrade.data.GenCurrencyDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.StockOptionProductRow.class,
        webbroker3.gentrade.data.StockOptionProductDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.CommCodeChgMstRow.class,
        webbroker3.gentrade.data.CommCodeChgMstDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.MarketMarginRatioRow.class,
        webbroker3.gentrade.data.MarketMarginRatioDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.FrgnMmfExchangeRateRow.class,
        webbroker3.gentrade.data.FrgnMmfExchangeRateDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.FTransFinInstitutionRow.class,
        webbroker3.gentrade.data.FTransFinInstitutionDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.DirectDebitRow.class,
        webbroker3.gentrade.data.DirectDebitDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.LoginWdogAttributeRow.class,
        webbroker3.gentrade.data.LoginWdogAttributeDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.BranchListmarketDealtCondRow.class,
        webbroker3.gentrade.data.BranchListmarketDealtCondDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.DocDivManagementRow.class,
        webbroker3.gentrade.data.DocDivManagementDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.BatoProductManagementRow.class,
        webbroker3.gentrade.data.BatoProductManagementDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.SonarTraderRow.class,
        webbroker3.gentrade.data.SonarTraderDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.FeqLastClosingPriceRow.class,
        webbroker3.gentrade.data.FeqLastClosingPriceDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.BranchMarketPtsDealtCondRow.class,
        webbroker3.gentrade.data.BranchMarketPtsDealtCondDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.PtsOrderexecutionEndRow.class,
        webbroker3.gentrade.data.PtsOrderexecutionEndDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.DocCategoryManagementRow.class,
        webbroker3.gentrade.data.DocCategoryManagementDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.InstitutionPreferencesRow.class,
        webbroker3.gentrade.data.InstitutionPreferencesDao.FACTORY );
    regDao(
        webbroker3.gentrade.data.EraRow.class,
        webbroker3.gentrade.data.EraDao.FACTORY );
  }

}
@
