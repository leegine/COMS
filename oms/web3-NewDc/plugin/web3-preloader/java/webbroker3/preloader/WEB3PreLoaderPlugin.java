head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoaderPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3PreLoaderPluginクラス(WEB3PreLoaderPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/21 山田　@卓司 (FLJ) 新規作成
                  : 2005/04/19 山田　@卓司 (FLJ) プリロードをリードオンリーモードで実行するように変更
                  : 2005/05/20 山田　@卓司（FLJ）ACCOUNT_PRODUCT_ORDER_STOPテーブルのプリロードを実行しないように変更 
 */
package webbroker3.preloader;

import java.net.InetAddress;

import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.gentrade.data.DailyAssetRow;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.EquityCommAccountCondMstRow;
import webbroker3.gentrade.data.EquityCommCondRow;
import webbroker3.gentrade.data.LastClosingPriceRow;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TaxRateTableRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.preloader.handler.WEB3PreLoadHandler;
import webbroker3.preloader.message.WEB3PreLoadRequest;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackEquityCommCondImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackLastClosingPriceImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackMainAccountImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackBranchImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackBranchMarketDealtCondImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackDailyAssetImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackEqtypeFixedContractImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackEqtypeProductImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackEqtypeTradedProductImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackEqtypeTradedProductUpdqImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackFixedFinTransactionImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackInstitutionImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackLoginAccountManagerImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackLoginUsernameImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackOrderAcceptStatusImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackTaxRateTableImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackTpCashBalanceImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackTradedProductImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackTraderByLoginIdImpl;
import webbroker3.preloader.stdimpls.WEB3PreLoaderCallbackTradingpowerCalcConditionImpl;
import webbroker3.preloader.stdimpls.WEB3RangeBasedParamsFactory;
import webbroker3.tradingpower.data.EqtypeFixedContractRow;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAccountManagerRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeAttributeRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

/**
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3PreLoaderPlugin extends Plugin
{
    
    /**
     * プリロードを実行するスレッド数を設定するSYSTEM_PREFERNCESテーブルのnameフィールドの値
     */
    public static final String PRELOADER_THREADS_SIZE_PREF_NAME = "webbroker3.preloader.threads.size";
    
    /**
     * プリロードを実行するスレッド数のデフォルト値
     */
    public static final int DEFAULT_PRELOADER_THREADS_SIZE = 10;

    private static final WEB3LogUtility LOG =
        WEB3LogUtility.getInstance(WEB3PreLoaderPlugin.class);

    private static WEB3PreLoaderManager manager;

    public WEB3PreLoaderPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3PreLoaderPlugin.class);
    }
    
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();
        
        // サービスの登録
        manager = new WEB3PreLoaderManagerImpl();
        Services.registerService(WEB3PreLoaderManager.class, manager);
        
        // メッセージの登録
        regClass(WEB3PreLoadRequest.class);
        
        // ハンドラ―の登録
        regHandler(
            WEB3PreLoaderPlugin.class,
            WEB3PreLoadRequest.class,
            WEB3PreLoadHandler.class,
            "handle");
        
        // プリローダーの登録
        regPreLoader();
        
        // プリロード開始
        manager.loadAll();
        
        manager = null;
        
        LOG.info("WEB3PreLoaderPlugin bootstrap succeeded.");
        
    }

    public static void regPreLoader() throws Exception
    {

        // 対象となるAccountIdの範囲を取得する。
        String l_strServerName = InetAddress.getLocalHost().getHostName();
        WEB3RangeBasedParamsFactory factory = 
            new WEB3RangeBasedParamsFactory();
        WEB3DefaultPreLoaderParams l_accountIdRangeParams =
            factory.createAccountIdRangeParams(l_strServerName);
        WEB3DefaultPreLoaderParams l_loginIdRangeParams =
            factory.createLoginIdRangeParams(l_strServerName);
        WEB3DefaultPreLoaderParams l_loginRowTypeLoginIdRangeParams =
            factory.createLoginIdRangeParams(l_strServerName, LoginRow.TYPE);

        // oplogin ------------------------------------------------------------

        // login_type
        addPreLoader(LoginTypeRow.TYPE);
        // login_type_attribute
        addPreLoader(LoginTypeAttributeRow.TYPE);
        // login_account_manager
        addPreLoader(
            LoginAccountManagerRow.TYPE,
            new WEB3PreLoaderCallbackLoginAccountManagerImpl(),
            l_loginIdRangeParams);
        // login_username
        addPreLoader(
            LoginUsernameRow.TYPE,
            new WEB3PreLoaderCallbackLoginUsernameImpl(),
            l_loginIdRangeParams);

        // gtl ----------------------------------------------------------------
        
        // institution
        addPreLoader(
            InstitutionRow.TYPE,
            new WEB3PreLoaderCallbackInstitutionImpl());
        // branch
        addPreLoader(BranchRow.TYPE, new WEB3PreLoaderCallbackBranchImpl());
        // market
        addPreLoader(MarketRow.TYPE);
        // main_account
        addPreLoader(
            MainAccountRow.TYPE, 
            new WEB3PreLoaderCallbackMainAccountImpl(), 
            l_accountIdRangeParams);
        // product
        addPreLoader(ProductRow.TYPE);
        // traded_product
        addPreLoader(
            TradedProductRow.TYPE,
            new WEB3PreLoaderCallbackTradedProductImpl());
        // trader
        addPreLoader(
            TraderRow.TYPE,
            new WEB3PreLoaderCallbackTraderByLoginIdImpl(),
            l_loginRowTypeLoginIdRangeParams);

        // eqtype -------------------------------------------------------------
        
        // eqtype_product
        addPreLoader(
            EqtypeProductRow.TYPE,
            new WEB3PreLoaderCallbackEqtypeProductImpl());
        // eqtype_traded_product
        addPreLoader(
            EqtypeTradedProductRow.TYPE,
            new WEB3PreLoaderCallbackEqtypeTradedProductImpl());
        // eqtype_traded_product_updq
		addPreLoader(
			EqtypeTradedProductUpdqRow.TYPE,
			new WEB3PreLoaderCallbackEqtypeTradedProductUpdqImpl());
		
		// web3-gentrade ------------------------------------------------------
		
		// branch_market_dealt_cond
		addPreLoader(
		    BranchMarketDealtCondRow.TYPE,
		    new WEB3PreLoaderCallbackBranchMarketDealtCondImpl());
		// daily_asset
		addPreLoader(
		    DailyAssetRow.TYPE, 
		    new WEB3PreLoaderCallbackDailyAssetImpl(),
		    l_accountIdRangeParams);
		// enable_order_conditions
		addPreLoader(
		    EnableOrderConditionRow.TYPE);
		// equity_comm_account_cond_mst
		addPreLoader(
		    EquityCommAccountCondMstRow.TYPE,
		    l_accountIdRangeParams);
		// equity_comm_cond
		addPreLoader(
		    EquityCommCondRow.TYPE,
		    new WEB3PreLoaderCallbackEquityCommCondImpl());
		// order_accept_status
		addPreLoader(
		    OrderAcceptStatusRow.TYPE, 
		    new WEB3PreLoaderCallbackOrderAcceptStatusImpl());
		// tax_rate_table
		addPreLoader(
		    TaxRateTableRow.TYPE,
		    new WEB3PreLoaderCallbackTaxRateTableImpl());
		// tradingpower_cacl_condition
		addPreLoader(
		    TradingpowerCalcConditionRow.TYPE,
		    new WEB3PreLoaderCallbackTradingpowerCalcConditionImpl(),
		    l_accountIdRangeParams);
		// last_closing_price
		addPreLoader(
		    LastClosingPriceRow.TYPE,
		    new WEB3PreLoaderCallbackLastClosingPriceImpl(),
		    new WEB3DefaultPreLoaderParams(
		            ProductRow.TYPE, 
		            "product_type=?", 
		            null, 
		            null,
		            new Object[] { ProductTypeEnum.EQUITY }));
		
		// web3-tplib ---------------------------------------------------------
		
		// eqtype_fixed_contract
		addPreLoader(
		    EqtypeFixedContractRow.TYPE,
		    new WEB3PreLoaderCallbackEqtypeFixedContractImpl(),
		    l_accountIdRangeParams);
		
		// fixed_fin_transaction
		addPreLoader(
		    FixedFinTransactionRow.TYPE,
		    new WEB3PreLoaderCallbackFixedFinTransactionImpl(),
		    l_accountIdRangeParams);
		
		// tp_cash_balance
		addPreLoader(
		    TpCashBalanceRow.TYPE,
		    new WEB3PreLoaderCallbackTpCashBalanceImpl(),
		    l_accountIdRangeParams);

    }

    private static final void addPreLoader(RowType l_rowType)
    {
        manager.registerPreLoader(new WEB3DefaultPreLoader(l_rowType));
    }

    private static final void addPreLoader(
        RowType l_rowType,
        WEB3PreLoaderCallback l_callback)
    {
        manager.registerPreLoader(new WEB3DefaultPreLoader(l_rowType, l_callback));
    }

    private static final void addPreLoader(
        RowType l_rowType, 
        WEB3DefaultPreLoaderParams l_params)
    {
        manager.registerPreLoader(
            new WEB3DefaultPreLoader(l_rowType, l_params));
    }

    private static final void addPreLoader(
        RowType l_rowType,
        WEB3PreLoaderCallback l_callback,
        WEB3DefaultPreLoaderParams l_params)
    {
        manager.registerPreLoader(
            new WEB3DefaultPreLoader(l_rowType, l_callback, l_params));
    }

}
@
