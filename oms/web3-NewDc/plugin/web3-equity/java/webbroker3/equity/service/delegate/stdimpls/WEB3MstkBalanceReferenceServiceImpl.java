head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資残高照会サービスImpl(WEB3MstkBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityAsset;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MiniClientRequestService;
import webbroker3.equity.WEB3MstkBalanceReferenceComparator;
import webbroker3.equity.message.WEB3MstkBalanceReferenceDetailUnit;
import webbroker3.equity.message.WEB3MstkBalanceReferenceRequest;
import webbroker3.equity.message.WEB3MstkBalanceReferenceResponse;
import webbroker3.equity.message.WEB3MstkBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3MstkBalanceReferenceTotalResponse;
import webbroker3.equity.message.WEB3MstkProductCodeNameUnit;
import webbroker3.equity.message.WEB3MstkSortKey;
import webbroker3.equity.service.delegate.WEB3MstkBalanceReferenceService;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （株式ミニ投資残高照会サービスImpl）。<BR>
 * <BR>
 * 株式ミニ投資残高照会サービス実装クラス<BR>
 */
public class WEB3MstkBalanceReferenceServiceImpl
extends WEB3MiniClientRequestService
implements WEB3MstkBalanceReferenceService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceServiceImpl.class);    
        
    /**
     * @@roseuid 4206CCB4002B<BR>
     */
    public WEB3MstkBalanceReferenceServiceImpl() 
    {
     
    }
    
    /**
     * 株式ミニ投資残高照会処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○株式ミニ投資残高照会残高合計リクエストの場合<BR>
     * 　@this.get残高合計()メソッドをコールする。<BR>
     * <BR>
     * ○株式ミニ投資残高照会リクエストの場合<BR>
     * 　@this.get残高照会()メソッドをコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41C2D24B021A<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "WEB3MstkBalanceReferenceServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.error("パラメータ.リクエストデータがnullです。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3MstkBalanceReferenceRequest)
        {
            l_response = 
                this.getBalanceReference((WEB3MstkBalanceReferenceRequest)l_request);
        }
        else if (l_request instanceof WEB3MstkBalanceReferenceTotalRequest)
        {
            l_response = 
                this.getBalanceTotal((WEB3MstkBalanceReferenceTotalRequest)l_request);
        }
        else
        {
            log.error("パラメータ.リクエストデータの型が不正です。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get残高照会)<BR>
     * <BR>
     * 株式ミニ投資残高照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ミニ株残高照会サービス）get残高照会」<BR>
     * 参照<BR>
     * @@param l_request - (リクエストデータ) 株式ミニ投資残高照会リクエストオブジェクト<BR>
     * @@return WEB3MstkBalanceReferenceResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C2D27201FB<BR>
     */
    protected WEB3MstkBalanceReferenceResponse getBalanceReference(WEB3MstkBalanceReferenceRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3MstkBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate
        l_request.validate();
        
        // get補助口座
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        // validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // レスポンスデータ生成
        WEB3MstkBalanceReferenceResponse l_response =
            (WEB3MstkBalanceReferenceResponse)l_request.createResponse();
            
        // create銘柄コード名称
        this.createProductCodeNames(l_subAccount, l_response);
        
        // create残高照会明細一覧
        this.createBalanceReferenceDetailUnitList(l_response, l_subAccount, l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get残高合計)<BR>
     *<BR>
     * 株式ミニ投資残高合計処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ミニ株残高照会サービス）get残高合計」<BR>
     * 参照<BR>
     * @@param l_request - (リクエストデータ) 株式ミニ投資残高照会残高合計リクエストオブジェクト<BR>
     * @@return WEB3MstkBalanceReferenceTotalResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C2D272021B<BR>
     */
    protected WEB3MstkBalanceReferenceTotalResponse getBalanceTotal(WEB3MstkBalanceReferenceTotalRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceTotal(WEB3MstkBalanceReferenceTotalRequest)";
        log.entering(STR_METHOD_NAME); 
        
        // validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // get補助口座
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        // レスポンスデータを生成
        WEB3MstkBalanceReferenceTotalResponse l_response =
            (WEB3MstkBalanceReferenceTotalResponse)l_request.createResponse();
            
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 拡張ポジションマネージャ取得
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        // getミニ株保有資産一覧
        List l_lstMiniStockAssets =
            l_positionManager.getMiniStockAssets(l_subAccount, ProductTypeEnum.EQUITY, null, null, "product_id asc");
        
        if (l_lstMiniStockAssets == null || l_lstMiniStockAssets.size() == 0)
        {
            // レスポンスに初期値をセットして終了
            l_response.capitalGainTotalAsset = "0";
            l_response.normalAccountTotalAsset = "0";
            l_response.capitalGainTotalAppraisalProfitLoss = "0";
            l_response.normalAccountTotalAppraisalProfitLoss = "0";
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        HashMap l_hmProductQuote = new HashMap();
        
        double l_dblNormalEstimatedValueTotal = 0.0D;
        double l_dblNormalProfitLossTotal = 0.0D;
        double l_dblSpecialEstimatedValueTotal = 0.0D;
        double l_dblSpecialProfitLossTotal = 0.0D;
        
        // 拡張プロダクトマネージャを取得
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        
        // getミニ株保有資産一覧の戻り値の要素数分Loop処理
        for (int i = 0; i < l_lstMiniStockAssets.size(); i++)
        {
            WEB3EquityAsset l_asset = (WEB3EquityAsset)l_lstMiniStockAssets.get(i);
            AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
            
            // 株式銘柄を取得
            WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_asset.getProduct();
            // ミニ株市場を取得
            Market l_miniStockMarket = l_eqtypeProduct.getMiniStockMarket();
            
            WEB3EquityTradedProduct l_tradedProduct = null;
            WEB3EquityProductQuote l_productQuote = null;
            try
            {
                if (l_miniStockMarket != null)
                {
	                l_tradedProduct =
	                    (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_eqtypeProduct, l_miniStockMarket);
		            // 時価情報を取得
		            l_productQuote = this.getEquityProductQuote(l_subAccount, l_tradedProduct, l_hmProductQuote);
                }
            } catch (NotFoundException l_ex) {
                log.debug("*** 取引銘柄なし *** 銘柄ID:[" + l_eqtypeProduct.getProductId() +
                    "] 市場コード:[" + l_miniStockMarket + "]に該当する有効な取引銘柄が存在しません。");
            }
            
            // 時価情報が取得できなかった場合は、次の保有資産へ処理を移行する。
            if (l_productQuote == null)
            {
                continue;
            }
            // 時価
            double l_dblCurrentPrice = l_productQuote.getQuote();
            // 残高株数
            double l_dblBalanceQuantity = l_asset.getQuantity() + l_assetRow.getQuantityCannotSell();
            // 評価額を算出
            double l_dblEstimatedValue = this.calcEstimatedValue(l_dblCurrentPrice, l_dblBalanceQuantity);
            
            // 概算簿価単価
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblBookValuePrice = l_bizLogicProvider.calcEstimatedBookPrice(
                l_assetRow.getBookValue(),
                l_assetRow.getQuantityForBookValue(),
                0);
            
            // 評価損益を算出
            double l_dblProfitLoss = 0.0D;
            if (l_dblBookValuePrice != 0)
            {
                l_dblProfitLoss = this.calcProfitLoss(l_dblCurrentPrice, l_dblBookValuePrice, l_dblBalanceQuantity);
            }
            
            // 保有資産.税区分 == "一般"の場合
            if (l_asset.getTaxType().equals(TaxTypeEnum.NORMAL))
            {
                // 一般口座評価額合計、一般口座評価損益合計に加算する
                l_dblNormalEstimatedValueTotal += l_dblEstimatedValue;
                l_dblNormalProfitLossTotal += l_dblProfitLoss;
            }
            // 保有資産.税区分 == "特定" or "特定口座かつ源泉徴収"の場合
            else if (l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL)
                || l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
            {
                // 特定口座評価額合計、特定口座評価損益合計に加算する
               l_dblSpecialEstimatedValueTotal += l_dblEstimatedValue;
               l_dblSpecialProfitLossTotal += l_dblProfitLoss;
            }
        }
        
        l_response.capitalGainTotalAsset = WEB3StringTypeUtility.formatNumber(l_dblSpecialEstimatedValueTotal);
        l_response.capitalGainTotalAppraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblSpecialProfitLossTotal);
        l_response.normalAccountTotalAsset = WEB3StringTypeUtility.formatNumber(l_dblNormalEstimatedValueTotal);
        l_response.normalAccountTotalAppraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblNormalProfitLossTotal);

        return l_response;
    }
    
    /**
     * (create銘柄コード名称)<BR>
     * <BR>
     * 　@指定口座の保持するミニ株保有資産の銘柄コードと銘柄名の一覧を作成し、レスポンス<BR>
     * データにセットする。 <BR>
     * 該当データが存在しない場合にはnullをセットする。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（ミニ株残高照会サービス）create銘柄コード名称」参照。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト<BR>
     * @@param l_response - (レスポンスデータ) 株式ミニ投資残高照会レスポンスデータオブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C2D58A007A<BR>
     */
    protected void createProductCodeNames(WEB3GentradeSubAccount l_subAccount, WEB3MstkBalanceReferenceResponse l_response) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductCodeNames(WEB3GentradeSubAccount, WEB3MstkBalanceReferenceResponse)";
        log.entering(STR_METHOD_NAME); 
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 拡張ポジションマネージャ取得
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        // getミニ株保有資産一覧
        List l_lstMiniStockAssets =
            l_positionManager.getMiniStockAssets(l_subAccount, ProductTypeEnum.EQUITY, null, null, "product_id asc");
        
        if (l_lstMiniStockAssets == null || l_lstMiniStockAssets.size() == 0)
        {
            l_response.productCodeNames = null;
            
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        TreeMap l_tmProductCodeNames = new TreeMap();
        
        // getミニ株保有資産一覧の戻り値の要素数分Loop処理
        for (int i = 0; i < l_lstMiniStockAssets.size(); i++)
        {
            WEB3EquityAsset l_asset = (WEB3EquityAsset)l_lstMiniStockAssets.get(i);
            AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
            
            String l_strProductId = String.valueOf(l_assetRow.getProductId());
            
            // 未追加の場合の処理実施
            if (l_tmProductCodeNames.get(l_strProductId) == null)
            {
                // 株式銘柄を取得
                WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_asset.getProduct();
                // 株式ミニ株銘柄コード名称を作成し、プロパティセット
                WEB3MstkProductCodeNameUnit l_productCodeNameUnit = new WEB3MstkProductCodeNameUnit();
                l_productCodeNameUnit.productCode = l_eqtypeProduct.getProductCode();
                EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
                l_productCodeNameUnit.productName = l_eqtypeProductRow.getStandardName();
                
                l_tmProductCodeNames.put(l_strProductId, l_productCodeNameUnit);
            }
        }
        // レスポンスデータ.銘柄一覧にプロパティセット
        l_response.productCodeNames =
            (WEB3MstkProductCodeNameUnit[])l_tmProductCodeNames.values().toArray(new WEB3MstkProductCodeNameUnit[0]);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create残高照会明細一覧)<BR>
     * <BR>
     * 指定口座の保持するミニ株保有資産より、<BR>
     * 残高照会明細を作成し、レスポンスデータにセットする。 <BR>
     * 該当データが存在しない場合にはnullをセットする。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（ミニ株残高照会サービス）create残高照会明細一覧」参照。<BR>
     * <BR>
     * @@param l_response - (レスポンスデータ) 株式ミニ投資残高照会レスポンスデータオブジェクト<BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト<BR>
     * @@param l_request - (リクエストデータ) 株式ミニ投資残高照会リクエストデータオブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C2DBCC021A<BR>
     */
    protected void createBalanceReferenceDetailUnitList(WEB3MstkBalanceReferenceResponse l_response, WEB3GentradeSubAccount l_subAccount, WEB3MstkBalanceReferenceRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createBalanceReferenceDetailUnitList(WEB3MstkBalanceReferenceResponse, WEB3GentradeSubAccount, WEB3MstkBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME); 
     
        // create検索条件文字列
        String l_strProductCode = l_request.productCode;
        String l_strQueryString = this.createQueryString(l_strProductCode);
        
        // create検索条件データコンテナ
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        String[] l_strQueryDataContainer = this.createQueryContainer(l_institution, l_strProductCode);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 拡張ポジションマネージャ取得
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            
        // getミニ株保有資産一覧
        List l_lstMiniStockAssets =
            l_positionManager.getMiniStockAssets(l_subAccount, ProductTypeEnum.EQUITY, l_strQueryString, l_strQueryDataContainer, null);
        
        if (l_lstMiniStockAssets == null || l_lstMiniStockAssets.size() == 0)
        {
            l_response.mstkBalanceReferenceDetail = null;
            l_response.pageIndex = "0";
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        HashMap l_hmProductQuote = new HashMap();
        ArrayList l_lstBalanceReference = new ArrayList();
        
        // 拡張プロダクトマネージャ取得
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        // 株式注文マネージャ取得
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            
        // getミニ株保有資産一覧の要素数分Loop処理
        for (int i = 0; i < l_lstMiniStockAssets.size(); i++)
        {
            WEB3EquityAsset l_asset = (WEB3EquityAsset)l_lstMiniStockAssets.get(i);
            AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
            
            WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_asset.getProduct();
            
            // ミニ株市場を取得する
            Market l_miniStockMarket = l_eqtypeProduct.getMiniStockMarket();
            
            WEB3EquityTradedProduct l_tradedProduct = null;
            try
            {
                if (l_miniStockMarket != null)
                {
	                l_tradedProduct =
	                    (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_eqtypeProduct, l_miniStockMarket);
                }
            } catch (NotFoundException l_ex) {
                log.debug("*** 取引銘柄なし *** 銘柄ID:[" + l_eqtypeProduct.getProductId() +
                    "] 市場コード:[" + l_miniStockMarket + "]に該当する有効な取引銘柄が存在しません。");
            }
            
            long l_lngAccountId = l_subAccount.getMainAccount().getAccountId();
            long l_lngSubAccountId = l_subAccount.getSubAccountId();
            long l_lngProductId = l_eqtypeProduct.getProductId();
            // getミニ株注文中株数(買注文)
            double l_dblBuyOrderingQuantity =
                l_orderManager.getMiniStockOrderingQuantity(l_lngAccountId, l_lngSubAccountId, l_lngProductId, false);
                
            // getミニ株注文中株数(売注文)
            double l_dblSellOrderingQuantity =
                l_orderManager.getMiniStockOrderingQuantity(l_lngAccountId, l_lngSubAccountId, l_lngProductId, true);
                
            // 時価情報取得
            WEB3EquityProductQuote l_productQuote = null;
            if (l_tradedProduct != null)
            {
                l_productQuote = this.getEquityProductQuote(l_subAccount, l_tradedProduct, l_hmProductQuote);
            }
            
            // 株式ミニ投資残高照会明細を作成
            WEB3MstkBalanceReferenceDetailUnit l_balanceReferenceUnit = new WEB3MstkBalanceReferenceDetailUnit();
			// プロパティ初期化（売付可能フラグ）
			l_balanceReferenceUnit.sellPossFlag = true;
			
            // プロパティセット
            // 銘柄コード
            l_balanceReferenceUnit.productCode = l_eqtypeProduct.getProductCode();
            // 銘柄名
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject(); 
            l_balanceReferenceUnit.productName = l_eqtypeProductRow.getStandardName();
            // 市場コード
            if (l_miniStockMarket != null)
            {
	            l_balanceReferenceUnit.marketCode = l_miniStockMarket.getMarketCode();
            }
            else
            {
	            l_balanceReferenceUnit.marketCode = null;
				l_balanceReferenceUnit.sellPossFlag = false;
            }
            
            // 残高株数
            double l_dblBalanceQuantity = l_asset.getQuantity() + l_assetRow.getQuantityCannotSell();
            l_balanceReferenceUnit.balanceQuantity = WEB3StringTypeUtility.formatNumber(l_dblBalanceQuantity);
            // 買付注文中株数
            l_balanceReferenceUnit.buyOrderedQuantity = WEB3StringTypeUtility.formatNumber(l_dblBuyOrderingQuantity);
            // 売付注文中株数
            l_balanceReferenceUnit.sellOrderedQuantity = WEB3StringTypeUtility.formatNumber(l_dblSellOrderingQuantity);
            
            double l_dblSellPossQuantity = 0.0D;
            // getミニ株保有株数()
            double l_dblMiniBalanceQuantity = l_positionManager.getMiniStockQuantity(l_lngAccountId, l_lngSubAccountId, l_lngProductId);
            
            // 売付可能株数
            // validateミニ株重複注文が例外をスローした場合は、売付可能株数 = 0をセット
            // 以外、（getミニ株保有株数()の戻り値 - 売付注文中株数）の計算結果
            try
            {
                if (l_tradedProduct != null)
                {
	                l_orderManager.validateMiniStockDuplicateOrder(l_subAccount, l_tradedProduct);
	                l_dblSellPossQuantity = l_dblMiniBalanceQuantity - l_dblSellOrderingQuantity;
	                
	                // 買付可能フラグにtrueをセット
	                l_balanceReferenceUnit.buyPossFlag = true;
                }
                else
                {
                    // 買付可能フラグにfalse、売付可能数量に0をセット
                    l_balanceReferenceUnit.buyPossFlag = false;
	                l_dblSellPossQuantity = 0.0D;
                }
            } catch (WEB3BaseException l_wbex) {
                l_dblSellPossQuantity = 0.0D;
                
                // 買付可能フラグにfalseをセット
                l_balanceReferenceUnit.buyPossFlag = false;
            }
            finally
            {
                l_balanceReferenceUnit.sellPossQuantity = WEB3StringTypeUtility.formatNumber(l_dblSellPossQuantity);
            }
            
            // 売付可能フラグ
            if (l_dblSellPossQuantity == 0)
            {
                l_balanceReferenceUnit.sellPossFlag = false;
            }
            
            // 保有資産ID
            l_balanceReferenceUnit.id = String.valueOf(l_asset.getAssetId());
            // 口座区分
            if (l_assetRow.getTaxType().equals(TaxTypeEnum.NORMAL))
            {
                l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.NORMAL;
            }
            else if (l_assetRow.getTaxType().equals(TaxTypeEnum.SPECIAL)
                || l_assetRow.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
            {
                l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.SPECIAL;
            }
            
            // 概算簿価単価
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblBookValuePrice = l_bizLogicProvider.calcEstimatedBookPrice(l_assetRow.getBookValue(), l_assetRow.getQuantityForBookValue(), 0);
            
            if (l_assetRow.getQuantityForBookValue() == 0)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "保有資産.数量（簿価単価計算用）に0が設定されています");
            }
            
            if (l_dblBookValuePrice == 0)
            {
                l_balanceReferenceUnit.estimatedBookPrice = null;
            }
            else
            {
                l_balanceReferenceUnit.estimatedBookPrice =
                    WEB3StringTypeUtility.formatNumber(l_dblBookValuePrice);
            }
            
            // 簿価単価入力済フラグ
            if (!l_assetRow.getInputBookValueIsNull())
            {
                l_balanceReferenceUnit.estimatedBookPriceInputFlag = true;
            }
            else
            {
                l_balanceReferenceUnit.estimatedBookPriceInputFlag = false;
            }
            
            // 時価情報が取得できた場合
            if (l_productQuote != null)
            {
                // 時価
                double l_dblCurrentPrice = l_productQuote.getQuote();
                l_balanceReferenceUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
                // 前日比
                l_balanceReferenceUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_productQuote.getComparedPreviousDay());
                // 時価取得時間(HH:MM)
                log.debug("時価取得区分:[" + l_productQuote.getQuoteFromDiv() + "]");
                Timestamp l_quoteTime = l_productQuote.getQuoteTime();
                if (l_quoteTime != null)
                {
                    l_balanceReferenceUnit.currentPriceTime = WEB3DateUtility.formatDate(l_quoteTime, "HH:mm");
                }
                else
                {
                    l_balanceReferenceUnit.currentPriceTime = null;
                }
                // 概算評価額
                l_balanceReferenceUnit.estimatedAsset =
                    WEB3StringTypeUtility.formatNumber(this.calcEstimatedValue(l_dblCurrentPrice, l_dblBalanceQuantity));
                // 概算評価損益
                if (l_dblBookValuePrice != 0)
                {
	                l_balanceReferenceUnit.estimatedlAppraisalProfitLoss =
	                    WEB3StringTypeUtility.formatNumber(this.calcProfitLoss(l_dblCurrentPrice, l_dblBookValuePrice, l_dblBalanceQuantity));
	            }
            }
            else
            {
                l_balanceReferenceUnit.currentPrice = null;
                l_balanceReferenceUnit.comparedPreviousDay = null;
                l_balanceReferenceUnit.currentPriceTime = null;
                l_balanceReferenceUnit.estimatedAsset = null;
                l_balanceReferenceUnit.estimatedlAppraisalProfitLoss = null;
            }
            
            l_lstBalanceReference.add(l_balanceReferenceUnit);
        }
        
        WEB3MstkBalanceReferenceDetailUnit[] l_balanceReferenceUnits =
            (WEB3MstkBalanceReferenceDetailUnit[])l_lstBalanceReference.toArray(new WEB3MstkBalanceReferenceDetailUnit[0]);
        
        // sort残高照会明細
        this.sortBalanceReferenceDetailUnit(l_balanceReferenceUnits, l_request.sortKeys);
        
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_balanceReferenceUnits, l_intPageIndex, l_intPageSize);
        
        // レスポンスデータにプロパティをセットする。
        // 残高照会明細一覧
        l_response.mstkBalanceReferenceDetail =
            (WEB3MstkBalanceReferenceDetailUnit[])l_pageIndexInfo.getArrayReturned(WEB3MstkBalanceReferenceDetailUnit.class);
        // 総ページ数
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        // 総レコード数
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());
        // 表示ページ番号
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create検索条件文字列)<BR>
     * <BR>
     * リクエストデータをもとに、検索条件（where以下指定の文字列）を作成する。<BR>
     * <BR>
     * １）　@パラメータ.銘柄コード == nullの場合、nullを返却して終了する。<BR>
     * <BR>
     * ２）　@戻り値となる文字列のインスタンスを生成する。<BR>
     * <BR>
     * ３）　@パラメータ.銘柄コード != null（銘柄コード指定）の場合、<BR>
     * 　@銘柄ID指定を文字列インスタンスに追加する。<BR>
     * <BR>
     * 　@　@　@" and product_id = ?"<BR>
     * <BR>
     * ４）　@文字列インスタンスを返却する。<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード) 銘柄コード<BR>
     * @@return String<BR>
     * @@roseuid 41C2DDC803D3<BR>
     */
    protected String createQueryString(String l_strProductCode) 
    {
        final String STR_METHOD_NAME = "createQueryString(String)";
        log.entering(STR_METHOD_NAME);
        
        // パラメータ.銘柄コード == nullの場合
        if (l_strProductCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        String l_strQueryString = " and product_id = ? ";
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * <BR>
     * リクエストデータから、検索条件（where以下指定の文字列）のパラメータリストを作成?
<BR>
     * る。<BR>
     * <BR>
     * １）　@パラメータ.銘柄コード == nullの場合、nullを返却して終了する。<BR>
     * <BR>
     * ２）　@文字列配列を作成する。<BR>
     * <BR>
     * ３）　@パラメータ.銘柄コード != null（銘柄コード指定）の場合、銘柄IDを<BR>
     * 　@　@文字列配列にセットする。<BR>
     * <BR>
     * 　@　@　@銘柄ID = 拡張プロダクトマネージャ.get銘柄(パラメータ.証券会社, <BR>
     * パラメータ.銘柄コード).銘柄ID<BR>
     * <BR>
     * ４）　@文字列配列を返却する。<BR>
     * <BR>
     * @@param l_institution - (証券会社) 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード) - 銘柄コード<BR>
     * @@return String[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C2DDC9000A<BR>
     */
    protected String[] createQueryContainer(WEB3GentradeInstitution l_institution, String l_strProductCode) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryContainer(WEB3GentradeInstitution, String)";
        log.entering(STR_METHOD_NAME);
        
        // パラメータ.銘柄コード == nullの場合
        if (l_strProductCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 拡張プロダクトマネージャ
        WEB3EquityProductManager l_productManager = 
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
            
        EqTypeProduct l_eqtypeProduct = null;
        try
        {
            l_eqtypeProduct =
                l_productManager.getProduct(l_institution, l_strProductCode);
        } catch (NotFoundException l_ex) {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当する株式銘柄が取得できません。");
        }
        
        // 検索条件データコンテナ作成
        String[] l_strQueryDataContainer = {String.valueOf(l_eqtypeProduct.getProductId())};
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryDataContainer;
    }
    
    /**
     * (get時価情報)<BR>
     * <BR>
     * 時価情報を取得する。 <BR>
     * <BR>
     * １）時価情報検索 <BR>
     * 　@パラメータ.時価情報格納リスト.get()メソッドをコールする。 <BR>
     * <BR>
     * 　@[get()にセットするパラメータ] <BR>
     * 　@　@key：　@パラメータ.取引銘柄.銘柄IDに該当する銘柄コード <BR>
     * 　@　@　@　@　@+ パラメータ.取引銘柄.get市場コード() <BR>
     * <BR>
     * ２）時価情報返却 <BR>
     * 　@１）の戻り値 != nullの場合、取得した時価情報を返却する。 <BR>
     * <BR>
     * ３）時価情報取得 <BR>
     * 　@１）の戻り値 == nullの場合、以下の手順で時価情報を取得する。 <BR>
     * 　@３－１）拡張プロダクトマネージャ.get時価情報()メソッドをコールする。 <BR>
     * <BR>
     * 　@　@[get時価情報()にセットするパラメータ] <BR>
     * 　@　@　@取引銘柄：　@パラメータ.取引銘柄 <BR>
     * 　@　@　@RealType：　@"リアル" <BR>
     * 　@　@　@is終値テーブル無条件read：　@true（無条件にreadする）<BR>
     * <BR>
     * 　@３－２）パラメータ.時価情報格納リスト.put()メソッドをコールし、 <BR>
     * 　@　@取得した時価情報を格納する。 <BR>
     * <BR>
     * 　@　@[put()にセットするパラメータ] <BR>
     * 　@　@　@key：　@パラメータ.取引銘柄.銘柄IDに該当する銘柄コード <BR>
     * 　@　@　@　@　@+ パラメータ.取引銘柄.get市場コード() <BR>
     * 　@　@　@value：　@取得した時価情報 <BR>
     * <BR>
     * 　@３－３）取得した時価情報を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト<BR>
     * @@param l_tradedProduct - (取引銘柄) - 取引銘柄オブジェクト<BR>
     * @@param l_hmProductQuote - (時価情報格納リスト) <BR>
     * 銘柄コード+市場コードをキーとして、時価情報を格納するリスト<BR>
     * @@return WEB3EquityProductQuote<BR>
     * @@roseuid 41C65E810057<BR>
     */
    protected WEB3EquityProductQuote getEquityProductQuote(WEB3GentradeSubAccount l_subAccount, WEB3EquityTradedProduct l_tradedProduct, HashMap l_hmProductQuote)
    {
        final String STR_METHOD_NAME = "getEquityProductQuote(WEB3GentradeSubAccount, WEB3EquityProduct, HashMap)";
        log.entering(STR_METHOD_NAME);
        
        // 時価情報検索
        String l_strKey = l_tradedProduct.getProductCode() + l_tradedProduct.getMarketCode();
        WEB3EquityProductQuote l_productQuote =
            (WEB3EquityProductQuote)l_hmProductQuote.get(l_strKey);
        
        // 時価情報が取得できた場合は、返却して終了
        if (l_productQuote != null)
        {
            log.debug(STR_METHOD_NAME + " パラメータ.時価情報格納リストから時価を取得");
            log.exiting(STR_METHOD_NAME);
            return l_productQuote;
        }
        else // 時価情報が取得できなかった場合
        {
            log.debug(STR_METHOD_NAME + " 時価サーバーから時価を取得");
            try
            {
	            // 時価情報を取得する。
	            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
	            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                    
	            l_productQuote = l_productManager.getProductQuote(l_tradedProduct, RealType.REAL, true);
	            
	            // パラメータ.時価情報格納リストに取得した時価情報を追加する
	            l_hmProductQuote.put(l_strKey, l_productQuote);
            } catch (WEB3BaseException l_wbex) {
                l_productQuote = null;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_productQuote;
    }
    
    /**
     * (calc評価額)<BR>
     *<BR>
     * 評価額を算出し、返却する。<BR>
     * ※手数料は含まない。<BR>
     * <BR>
     * パラメータ.評価単価 * パラメータ.数量の結果を返却する。<BR>
     * <BR>
     * @@param l_dblPrice - (評価単価) 評価単価<BR>
     * @@param l_dblQuantity - (数量) 数量<BR>
     * @@return double<BR>
     * @@roseuid 41C65E810067<BR>
     */
    protected double calcEstimatedValue(double l_dblPrice, double l_dblQuantity) 
    {
        final String STR_METHOD_NAME = "calcEstimatedValue(double, double)";
        log.entering(STR_METHOD_NAME);
        
        // 評価額 = パラメータ.評価単価 * パラメータ.数量
        double l_dblResult = l_dblPrice * l_dblQuantity;
        
        log.exiting(STR_METHOD_NAME);
        return l_dblResult;
    }
    
    /**
     * (calc評価損益)<BR>
     * <BR>
     * 評価損益を算出し、返却する。<BR>
     * ※手数料は含まない。<BR>
     * <BR>
     * (パラメータ.評価単価 - パラメータ.簿価) * パラメータ.数量<BR>
     * の結果を返却する。<BR>
     * <BR>
     * @@param l_dblPrice - (評価単価) 評価単価<BR>
     * @@param l_dblBookValue - (簿価) 簿価<BR>
     * @@param l_dblQuantity - (数量) 数量<BR>
     * @@return double<BR>
     * @@roseuid 41C65E810096<BR>
     */
    protected double calcProfitLoss(double l_dblPrice, double l_dblBookValue, double l_dblQuantity) 
    {
        final String STR_METHOD_NAME = "calcProfitLoss(double, double, double)";
        log.entering(STR_METHOD_NAME);
        
        // 評価損益 = (パラメータ.評価単価 - パラメータ.簿価) * パラメータ.数量
        double l_dblResult = (l_dblPrice - l_dblBookValue) * l_dblQuantity;
        
        log.exiting(STR_METHOD_NAME);
        return l_dblResult;
    }
    
    /**
     * (sort残高照会明細)<BR>
     * <BR>
     * 指定されたソートキー、昇降順にもどついて残高照会明細のソートを行う。<BR>
     * <BR>
     * １）パラメータ.残高照会明細 == nullの場合、<BR>
     * 　@処理を終了する。<BR>
     * <BR>
     * ２）ArrayListを生成する。 <BR>
     * <BR>
     * ３）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@３－１）ソートキー.キー項目の値に対応する比較項目のComparatorを生成し、<BR>
     * 　@　@　@ArrayListに追加する。 <BR>
     * <BR>
     * 　@　@　@①@株式ミニ投資残高照会Comparatorを生成する。<BR>
     * <BR>
     * 　@　@　@　@[コンストラクタにセットするパラメータ] <BR>
     * 　@　@　@　@　@orderBy： ソートキー.昇順／降順<BR>
     * 　@　@　@　@　@比較項目：　@ソートキー.キー項目<BR>
     * <BR>
     * 　@　@　@②ArrayListに生成したComparatorを追加する。<BR>
     * <BR>
     * ４）口座区分をソートするComparatorの作成<BR>
     * 　@株式ミニ投資残高照会Comparatorを作成し、<BR>
     * 　@ArrayListに追加する。<BR>
     * <BR>
     * 　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@orderBy：　@"昇順"<BR>
     * 　@　@比較項目：　@株式ミニ投資残高照会明細.口座区分<BR>
     * <BR>
     * ５）WEB3ArraysUtility.sort()メソッドをコールする。 <BR>
     * <BR>
     * 　@[sort()にセットするパラメータ] <BR>
     * 　@　@obj：　@パラメータ.残高照会明細<BR>
     * 　@　@com：　@生成したArrayList.toArray()の戻り値<BR>
     * <BR>
     * @@param l_balanceReferenceDetailUnit - (残高照会明細) 株式ミニ投資残高照会明細の配列<BR>
     * @@param l_sortKey - (ソートキー) 株式ミニ投資ソートキーの配列<BR>
     * @@roseuid 41C664290123<BR>
     */
    protected void sortBalanceReferenceDetailUnit(WEB3MstkBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnit, WEB3MstkSortKey[] l_sortKey) 
    {
        final String STR_METHOD_NAME = "sortBalanceReferenceDetailUnit(WEB3MstkBalanceReferenceDetailUnit, WEB3MstkSortKey)";
        log.entering(STR_METHOD_NAME);
        
        // パラメータ.残高照会明細 == nullの場合、処理終了
        if (l_balanceReferenceDetailUnit == null || l_balanceReferenceDetailUnit.length == 0)
        {
            return;
        }
        
        ArrayList l_lstComparators = new ArrayList();
        
        // パラメータ.ソートキーの要素数分Loop処理
        WEB3MstkBalanceReferenceComparator l_comparator = null;
        String l_strOrderBy = null;
        String l_strKeyItem = null;
        for (int i = 0; i < l_sortKey.length; i++)
        {
            l_strOrderBy = l_sortKey[i].ascDesc;
            l_strKeyItem = l_sortKey[i].keyItem;
            log.debug("第" + (i + 1) + "ソートキー：" + l_strKeyItem + " " + l_strOrderBy);
            
            // 現物株式残高照会Comparatorを生成
            l_comparator = new WEB3MstkBalanceReferenceComparator(l_strOrderBy, l_strKeyItem);
            // ArrayListにComparatorを追加
            l_lstComparators.add(l_comparator);
        }
            
        // ソート
        WEB3ArraysUtility.sort(l_balanceReferenceDetailUnit,
            (WEB3MstkBalanceReferenceComparator[])l_lstComparators.toArray(new WEB3MstkBalanceReferenceComparator[0]));
        log.exiting(STR_METHOD_NAME);
    }
}
@
