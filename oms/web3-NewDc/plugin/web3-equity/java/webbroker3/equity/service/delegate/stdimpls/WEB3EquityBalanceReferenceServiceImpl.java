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
filename	WEB3EquityBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 現物株式残高照会サービスImpl(WEB3EquityBalanceReferenceServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
                  : 2005/03/31 劉(FLJ)　@性能チューニング修正
                  : 2006/08/29 李俊　@(中訊)　@仕様変更 モデルNo.971
 */

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityAsset;
import webbroker3.equity.WEB3EquityBalanceReferenceComparator;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.message.WEB3EquityBalanceReferenceDetailUnit;
import webbroker3.equity.message.WEB3EquityBalanceReferenceRequest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceResponse;
import webbroker3.equity.message.WEB3EquityBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceTotalResponse;
import webbroker3.equity.message.WEB3EquityProductCodeNameUnit;
import webbroker3.equity.message.WEB3EquitySortKey;
import webbroker3.equity.service.delegate.WEB3EquityBalanceReferenceService;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （現物株式残高照会サービスImpl）。<BR>
 *<BR>
 * 現物株式残高照会サービス実装クラス<BR>
 */
public class WEB3EquityBalanceReferenceServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityBalanceReferenceService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceServiceImpl.class);

    /**
     * @@roseuid 4206CC4A0163
     */
    public WEB3EquityBalanceReferenceServiceImpl()
    {

    }

    /**
     * 現物株式残高照会処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○現物株式残高照会リクエストの場合<BR>
     * 　@this.get残高照会()メソッドをコールする。<BR>
     * <BR>
     * ○現物株式残高照会残高合計リクエストの場合<BR>
     * 　@this.get残高合計()メソッドをコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41B5909E001C<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "WEB3EquityBalanceReferenceServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("パラメータ.リクエストデータがnullです。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3EquityBalanceReferenceRequest)
        {
            l_response =
                this.getBalanceReference( (WEB3EquityBalanceReferenceRequest) l_request);
        }
        else if (l_request instanceof WEB3EquityBalanceReferenceTotalRequest)
        {
            l_response =
                this.getBalanceTotal( (WEB3EquityBalanceReferenceTotalRequest) l_request);
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
     *<BR>
     * 現物株式残高照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(現物株式残高照会サービス)get残高照会」<BR>
     * 参照<BR>
     * @@param l_request - (リクエストデータ) 現物株式残高照会リクエストオブジェクト<BR>
     * @@return WEB3EquityBalanceReferenceResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B590D9023F<BR>
     */
    protected WEB3EquityBalanceReferenceResponse getBalanceReference(
        WEB3EquityBalanceReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getBalanceReference(WEB3EquityBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        // リクエストデータ.validate()
        l_request.validate();

        // validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        // create銘柄コード名称()
        //性能チューニング修正 劉(FLJ)
        //銘柄コード名称の一覧作成機@能削除
        //delete begin
        //WEB3EquityProductCodeNameUnit[] l_productCodeNameUnits =
        //    this.createProductCodeNameUnit(l_subAccount);
        //delete end

        // get取引中取扱可能市場()
        //性能チューニング修正 劉(FLJ)
        //市場機@能削除
        //delete begin
        //WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        //String[] l_strHandlingMarketCodes =
        //    WEB3GentradeBranchMarketDealtCond.getTradingHandlingPossibleMarket(l_branch, ProductTypeEnum.EQUITY);

        //if (l_strHandlingMarketCodes == null || l_strHandlingMarketCodes.length == 0)
        //{
        //    throw new WEB3BusinessLayerException(
        //        WEB3ErrorCatalog.BUSINESS_ERROR_00643,
        //        this.getClass().getName() + "." + STR_METHOD_NAME);
        //}
        //delete end

        // create検索条件文字列()
        String l_strProductCode = l_request.productCode;
        String l_strQueryString = this.createQueryString(l_strProductCode,l_request.taxTypeList);

        // create検索条件データコンテナ()
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution) l_subAccount.
            getInstitution();
        String[] l_strQueryDataContainger = this.createQueryContainer(l_institution,
            l_strProductCode, l_request.taxTypeList);

        // create残高照会明細一覧()
        //性能チューニング修正 劉(FLJ)
        //市場機@能削除
//modify begin
//>>>>>>>>>
//        String l_strMarketCode = l_request.marketCode;
//        WEB3EquityBalanceReferenceDetailUnit[] l_balanceReferenceUnits =
//            this.createBalanceReferenceDetailUnitList(l_subAccount,
//                                                        l_strMarketCode,
//                                                        l_strHandlingMarketCodes,
//                                                        l_strQueryString,
//                                                        l_strQueryDataContainger,
//                                                        null);

        //性能チューニング修正 劉(FLJ)
        //銘柄指定しない検索際、getBalanceTotalと同じ検索条件、xTrade cache が効く
        //発注注数量を一括取得
        Hashtable l_lockedAssetDetails=getLockedAssetDetails(l_subAccount);

        WEB3EquityBalanceReferenceDetailUnit[] l_balanceReferenceUnits =
            this.createBalanceReferenceDetailUnitList(l_subAccount,l_lockedAssetDetails,
            l_strQueryString,
            l_strQueryDataContainger,
            " product_id asc ");
//>>>>>>>>>
//modify end


        // sort残高照会明細
        WEB3EquitySortKey[] l_sortKeys = l_request.sortKeys;
        this.sortBalanceReferenceDetailUnit(l_balanceReferenceUnits, l_sortKeys);

        // 表示対象行の抽出
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_balanceReferenceUnits, l_intPageIndex, l_intPageSize);

        // createResponse()
        WEB3EquityBalanceReferenceResponse l_response =
            (WEB3EquityBalanceReferenceResponse) l_request.createResponse();

        // プロパティセット
        // 銘柄一覧
        //性能チューニング修正 劉(FLJ)
        //銘柄コード名称の一覧作成機@能削除 delete begin
        //l_response.productCodeNames = l_productCodeNameUnits;
        //delete end

        // 市場コード一覧
        //性能チューニング修正 劉(FLJ)
        //市場機@能削除 delete begin
        //l_response.marketList = l_strHandlingMarketCodes;
        //delete end

        // 残高照会明細
        l_response.equityBalanceReferenceDetail =
            (WEB3EquityBalanceReferenceDetailUnit[]) l_pageIndexInfo.getArrayReturned(
            WEB3EquityBalanceReferenceDetailUnit.class);
        // 表示ページ番号
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        // 総ページ数
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        // 総レコード数
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get残高合計)<BR>
     *<BR>
     * 現物株式残高合計処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(現物株式残高照会サービス)get残高合計」<BR>
     * 参照<BR>
     * @@param l_request - (リクエストデータ) 現物株式残高照会残高合計リクエストオブジェクト<BR>
     * @@return WEB3EquityBalanceReferenceTotalResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B590D9025E<BR>
     */
    protected WEB3EquityBalanceReferenceTotalResponse getBalanceTotal(
        WEB3EquityBalanceReferenceTotalRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getBalanceTotal(WEB3EquityBalanceReferenceTotalRequest)";
        log.entering(STR_METHOD_NAME);

        // validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        // get保有資産一覧()
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 拡張ポジションマネージャ
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        List l_lstAssets = null;
        l_lstAssets = l_positionManager.getAssets(l_subAccount, ProductTypeEnum.EQUITY, null, null,
                                                  " product_id asc ");

        // レスポンスデータ生成
        WEB3EquityBalanceReferenceTotalResponse l_response =
            (WEB3EquityBalanceReferenceTotalResponse) l_request.createResponse();

        // 該当データなしの場合
        if (l_lstAssets == null || l_lstAssets.size() == 0)
        {
            // レスポンスに初期値をセットして終了
            l_response.capitalGainTotalAsset = "0";
            l_response.capitalGainTotalAppraisalProfitLoss = "0";
            l_response.normalAccountTotalAsset = "0";
            l_response.normalAccountTotalAppraisalProfitLoss = "0";
            l_response.stockOptionTotalAsset = "0";
            l_response.stockOptionTotalAppraisalProfitLoss = "0";

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        HashMap l_hmProductQuote = new HashMap();

        WEB3EquityProduct l_eqtypeProduct = null;
        WEB3EquityProductQuote l_productQuote = null;
        double l_dblNormalEstimatedValueTotal = 0.0D;
        double l_dblNormalProfitLossTotal = 0.0D;
        double l_dblSpecialEstimatedValueTotal = 0.0D;
        double l_dblSpecialProfitLossTotal = 0.0D;
        double l_dblStockOptionEstimatedValueTotal = 0.0D;
        double l_dblStockOptionProfitLossTotal = 0.0D;

        // get保有資産一覧()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_lstAssets.size(); i++)
        {
            WEB3EquityAsset l_asset = (WEB3EquityAsset) l_lstAssets.get(i);
            AssetRow l_assetRow = (AssetRow) l_asset.getDataSourceObject();

            // 株式銘柄取得
            l_eqtypeProduct = (WEB3EquityProduct) l_asset.getProduct();

            // 時価情報取得
            l_productQuote = this.getEquityProductQuote(l_subAccount, l_eqtypeProduct,
                l_hmProductQuote);

            // 時価情報が取得できなかった場合は、次の保有資産へ処理を移行する。
            if (l_productQuote == null)
            {
                continue;
            }

            // 時価
            double l_dblCurrentPrice = l_productQuote.getQuote();
            // 残高株数
            double l_dblBalanceQuantity = l_asset.getQuantity() +
                l_assetRow.getQuantityCannotSell();
            // 評価額を算出
            double l_dblEstimatedValue = this.calcEstimatedValue(l_dblCurrentPrice,
                l_dblBalanceQuantity);

            // 概算簿価単価
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
                
            double l_dblBookValue = l_bizLogicProvider.calcEstimatedBookPrice(
                l_assetRow.getBookValue(), l_assetRow.getQuantityForBookValue(), 0);

            // 評価損益を算出(概算簿価単価 != 0の場合のみ)
            double l_dblProfitLoss = 0.0D;
            if (l_dblBookValue != 0)
            {
                l_dblProfitLoss = this.calcProfitLoss(l_dblCurrentPrice,
                    l_dblBookValue, l_dblBalanceQuantity);
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
            // 保有資産.税区分 == ("ストックオプション")の場合
            else if (l_asset.getTaxType().equals(TaxTypeEnum.STOCK_OPTION))
            {
                // 特定口座評価額合計、特定口座評価損益合計に加算する
                l_dblStockOptionEstimatedValueTotal += l_dblEstimatedValue;
                l_dblStockOptionProfitLossTotal += l_dblProfitLoss;
            }
        }

        l_response.capitalGainTotalAsset = WEB3StringTypeUtility.formatNumber(
            l_dblSpecialEstimatedValueTotal);
        l_response.capitalGainTotalAppraisalProfitLoss = WEB3StringTypeUtility.
            formatNumber(l_dblSpecialProfitLossTotal);
        l_response.normalAccountTotalAsset = WEB3StringTypeUtility.formatNumber(
            l_dblNormalEstimatedValueTotal);
        l_response.normalAccountTotalAppraisalProfitLoss = WEB3StringTypeUtility.
            formatNumber(l_dblNormalProfitLossTotal);
        l_response.stockOptionTotalAsset = WEB3StringTypeUtility.formatNumber(
            l_dblStockOptionEstimatedValueTotal);
        l_response.stockOptionTotalAppraisalProfitLoss =  WEB3StringTypeUtility.
        	formatNumber(l_dblStockOptionProfitLossTotal);   
        return l_response;
    }

    /**
     * (create銘柄コード名称)<BR>
     *<BR>
     * 引数の補助口座に該当する保有資産テーブルデータの<BR>
     * 銘柄コード名称の一覧を作成し、返却する。<BR>
     * <BR>
     * １）保有資産取得<BR>
     * 　@株式ポジションマネージャ.get保有資産一覧()メソッドをコールする。<BR>
     * <BR>
     * 　@[get保有資産一覧()にセットするパラメータ]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@銘柄タイプ：　@ProductTypeEnum.株式<BR>
     * 　@　@検索条件文字列：　@null<BR>
     * 　@　@検索条件データコンテナ：　@null<BR>
     * 　@　@ソート条件：　@"product_id asc"<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ２）ArrayListを生成する。<BR>
     * <BR>
     * ３）１）の戻り値の要素(=保有資産オブジェクト)数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@３－１）拡張株式プロダクトマネージャ.getProduct()にて<BR>
     * 　@　@株式銘柄を取得する。<BR>
     * <BR>
     * 　@　@[getProduct()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@保有資産.銘柄ID<BR>
     * <BR>
     * 　@３－２）銘柄コード重複チェック<BR>
     * 　@　@ArrayListに、取得した株式銘柄.銘柄コードと同じ<BR>
     * 　@　@銘柄コードを保持する要素が存在する場合、<BR>
     * 　@　@次の要素へ処理を移行する。(continue;)<BR>
     * <BR>
     * 　@３－３）現物株式銘柄コード名称インスタンスを生成する。<BR>
     * <BR>
     * 　@３－４）生成したインスタンスに、以下のプロパティをセットする。<BR>
     * 　@　@銘柄コード = 取得した株式銘柄.銘柄コード<BR>
     * 　@　@銘柄名 = 取得した株式銘柄.銘柄名<BR>
     * <BR>
     * 　@３－５）生成したArrayListにプロパティセットした<BR>
     * 　@　@インスタンスを追加する。<BR>
     * <BR>
     * ４）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト<BR>
     * @@return WEB3EquityProductCodeNameUnit[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B5A2E1017D<BR>
     */
    protected WEB3EquityProductCodeNameUnit[] createProductCodeNameUnit(
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createProductCodeNameUnit(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 拡張ポジションマネージャ
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        // 保有資産取得
        List l_lstAssets = l_positionManager.getAssets(l_subAccount,
            ProductTypeEnum.EQUITY,
            null,
            null,
            "product_id asc");
        // 該当データなしの場合
        if (l_lstAssets == null || l_lstAssets.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        ArrayList l_lstProductCodeNames = new ArrayList();
        HashMap l_hmProductCodeCheck = new HashMap();
        WEB3EquityAsset l_asset = null;
        WEB3EquityProduct l_eqtypeProduct = null;
        // 検索結果の要素数分Loop処理
        for (int i = 0; i < l_lstAssets.size(); i++)
        {
            l_asset = (WEB3EquityAsset) l_lstAssets.get(i);
            // 株式銘柄取得
            l_eqtypeProduct = (WEB3EquityProduct) l_asset.getProduct();

            // 銘柄コード重複チェック
            if (l_hmProductCodeCheck.get(l_eqtypeProduct.getProductCode()) == null)
            {
                // 現物株式銘柄コード名称インスタンスを生成する。
                WEB3EquityProductCodeNameUnit l_productCodeNameUnit = new
                    WEB3EquityProductCodeNameUnit();

                // 生成したインスタンスにプロパティセット
                // 銘柄コード
                l_productCodeNameUnit.productCode = l_eqtypeProduct.getProductCode();
                // 銘柄名
                EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow) l_eqtypeProduct.
                    getDataSourceObject();
                l_productCodeNameUnit.productName = l_eqtypeProductRow.getStandardName();

                // ArrayListに追加
                l_lstProductCodeNames.add(l_productCodeNameUnit);
                // 銘柄コードチェック用HashMapに追加
                l_hmProductCodeCheck.put(l_productCodeNameUnit.productCode,
                                         l_productCodeNameUnit.productCode);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return (WEB3EquityProductCodeNameUnit[]) l_lstProductCodeNames.toArray(new
            WEB3EquityProductCodeNameUnit[0]);
    }

    /**
     * (create検索条件文字列)<BR>
     *<BR>
     * 検索条件（where以下指定の文字列）を作成する。<BR>
     * <BR>
     * １）戻り値となる文字列のインスタンスを生成 <BR>
     * <BR>
     * ２）パラメータ.銘柄コード != nullの場合、 <BR>
     *     銘柄コードに該当する銘柄IDの検索条件文字列を作成する。<BR> 
     * <BR>
     *   検索条件文字列 = " and product_id = ? "<BR> 
     * <BR>
     * ３）パラメータ.口座区分一覧の要素数 != null　@の場合、<BR> 
     *     口座区分一覧に該当する口座区分の検索条件文字列を作成する。<BR> 
     * <BR>
     *    検索条件文字列 += "and tax_type in (?, ?,,,) "<BR>  
     * <BR>
     * ３）作成した検索条件文字列を返却する。<BR>
     * @@param l_strProductCode - (銘柄コード) 銘柄コード<BR>
     * @@param l_strTaxTypeList - (口座区分一覧)口座区分一覧<BR>
     * @@return String<BR>
     * @@roseuid 41B662B7006F<BR>
     */
    protected String createQueryString(String l_strProductCode, String[] l_strTaxTypeList)
    {
        final String STR_METHOD_NAME = "createQueryString(String,String[])";
        log.entering(STR_METHOD_NAME);

        String l_strQueryString = "";
        // パラメータ.銘柄コード == nullの場合
        if (l_strProductCode != null)
        {
            l_strQueryString = " and product_id = ? ";
        }
       
        //パラメータ.口座区分一覧の要素数 != null　@の場合
        if (l_strTaxTypeList != null)
        {
            if (l_strTaxTypeList.length == 1)
            {
                l_strQueryString = l_strQueryString + " and tax_type in (?) ";
            }
            if (l_strTaxTypeList.length > 1)
            {
                l_strQueryString = l_strQueryString + " and tax_type in (?";
                for (int i = 0; i < l_strTaxTypeList.length - 1; i++)
                {
                    l_strQueryString = l_strQueryString + ", ?";
                }
                l_strQueryString = l_strQueryString + ") ";
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     *<BR>
     * 検索条件（where以下指定の文字列）のパラメータの文字列配列を作成する。<BR>
     * <BR>
     * １）文字列配列を生成 <BR>
     * <BR>
     * ２）パラメータ.銘柄コード != nullの場合、パラメータ.銘柄コードに該当する <BR>
     *     銘柄ID(*1)を生成し文字列配列に追加する。  <BR>
     * <BR>
     * ３）パラメータ.口座区分一覧の要素数 != null　@の場合 <BR>
     *     各要素に該当する下記の値を文字列配列に追加する。 <BR>
     *     ３－１）株式データアダプタ.getAP口座区分(パラメータ.口座区分)を使用して、<BR> 
     *             AP用の口座区分に変換する。 <BR>
     *     ３－２）文字列配列に３－１）で取得した口座区分をセットする。<BR> 
     * <BR>
     * ４）２）、３）にてパラメータをセットした文字列配列を返却 <BR>
     * (*1)拡張プロダクトマネージャ.get銘柄(パラメータ.証券会社, <BR>
     * パラメータ.銘柄コード).銘柄ID<BR>
     * @@param l_institution - (証券会社) 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード) 銘柄コード<BR>
     * @@param l_strTaxTypeList - (口座区分一覧) 口座区分一覧<BR>
     * @@return String[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B662B70073<BR>
     */
    protected String[] createQueryContainer(WEB3GentradeInstitution l_institution,
                                            String l_strProductCode,
                                            String[] l_strTaxTypeList) throws
        WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createQueryContainer(WEB3GentradeInstitution, String, String[])";
        log.entering(STR_METHOD_NAME);
        
        ArrayList l_strQueryDataList = new ArrayList();

        // パラメータ.銘柄コード != nullの場合
        if (l_strProductCode != null)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            // 拡張プロダクトマネージャ
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager) l_tradingModule.getProductManager();

            EqTypeProduct l_eqtypeProduct = null;
            try
            {
                l_eqtypeProduct =
                    l_productManager.getProduct(l_institution, l_strProductCode);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "条件に該当する株式銘柄が取得できません。");
            }

            // 検索条件データコンテナ作成
            l_strQueryDataList.add(
                String.valueOf(l_eqtypeProduct.getProductId()));
            
        }
        //パラメータ.口座区分一覧の要素数 != null　@の場合
        if (l_strTaxTypeList != null)
        {
            for (int i = 0; i < l_strTaxTypeList.length; i++)
            {
                l_strQueryDataList.add(
                        WEB3EquityDataAdapter.getApTaxType(l_strTaxTypeList[i]));
            }
        }
        
        String[] l_strQueryDataContainer = new String[l_strQueryDataList.size()];
        log.exiting(STR_METHOD_NAME);
        l_strQueryDataList.toArray(l_strQueryDataContainer);
        return l_strQueryDataContainer;
    }

    /**
     * (create残高照会明細一覧)<BR>
     *<BR>
     * 顧客に該当する残高照会明細の一覧を作成し、返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(現物株式残高照会サービス)create残高照会明細一覧」<BR>
     * 参照<BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト<BR>
     * @@param l_strSpecifiedMarketCode - (指定市場コード) 指定市場コード<BR>
     * @@param l_strHandlingMarketCodes - (取扱可能市場コード一覧) 取扱可能市場コードの配列<BR>
     * @@param l_strQueryString - (検索条件文字列) 検索条件文字列オブジェクト<BR>
     * @@param l_strQueryDataContainer - (検索条件データコンテナ) 検索条件データコンテナオブジェクト<BR>
     * @@param l_strSortCond - (ソート条件) ソート条件<BR>
     * @@return WEB3EquityBalanceReferenceDetailUnit[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B7B2DF033E<BR>
     */
//    protected WEB3EquityBalanceReferenceDetailUnit[] createBalanceReferenceDetailUnitList(WEB3GentradeSubAccount l_subAccount, String l_strSpecifiedMarketCode, String[] l_strHandlingMarketCodes, String l_strQueryString, String[] l_strQueryDataContainer, String l_strSortCond)
    protected WEB3EquityBalanceReferenceDetailUnit[] createBalanceReferenceDetailUnitList(
        WEB3GentradeSubAccount l_subAccount, Hashtable l_lockedAssetDetails,String l_strQueryString,
        String[] l_strQueryDataContainer, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createBalanceReferenceDetailUnitList(WEB3GentradeSubAccount, String, String[], String, String[], String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 拡張ポジションマネージャ
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        // get保有資産一覧
        List l_lstAssets = l_positionManager.getAssets(l_subAccount,
            ProductTypeEnum.EQUITY,
            l_strQueryString,
            l_strQueryDataContainer,
            l_strSortCond);

        // 検索結果のチェック
        if (l_lstAssets == null || l_lstAssets.size() == 0)
        {
            return null;
        }

        // 時価情報格納用HashMap生成
        HashMap l_hmEquityProductQuote = new HashMap();
        // 残高照会明細格納用ArrayList生成
        ArrayList l_lstBalanceReferenceUnit = new ArrayList();
        // 市場コード一覧格納用ArrayList生成
        //性能チューニング修正 劉(FLJ)
        //市場機@能削除
        //delete begin
        //ArrayList l_lstMarketCodes = new ArrayList();
        //delete end

        // 買／売付の注文受付可能チェック（全市場対象）
        //性能チューニング修正 劉(FLJ)
        //市場機@能削除
        //begin
        // boolean l_blnBuyOrderOkAllMarket = this.isOrderAcceptPossible(true, null);
        // boolean l_blnSellOrderOkAllMarket = this.isOrderAcceptPossible(false, null);
        //end

        WEB3EquityAsset l_asset = null;
        WEB3EquityProduct l_eqtypeProduct = null;

        // 取引可能顧客チェック
        //性能チューニング修正 劉(FLJ)
        //LOOP外移動
        // begin
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.
            getOrderManager();
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();
        OrderValidationResult l_orderValidationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        //end

        // get保有資産一覧()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_lstAssets.size(); i++)
        {
            l_asset = (WEB3EquityAsset) l_lstAssets.get(i);
            AssetRow l_assetRow = (AssetRow) l_asset.getDataSourceObject();
            Double l_dblLockedQuantity = (Double) l_lockedAssetDetails.get(new Long(
                l_asset.
                getAssetId()));
            double l_lockedQuantity = 0.0;
            if (l_dblLockedQuantity != null)
            {
                l_lockedQuantity = l_dblLockedQuantity.doubleValue();
            }

            // 株式銘柄を取得
            l_eqtypeProduct = (WEB3EquityProduct) l_asset.getProduct();

            // 現物株式残高照会明細インスタンスを生成
            WEB3EquityBalanceReferenceDetailUnit l_balanceReferenceUnit =
                new WEB3EquityBalanceReferenceDetailUnit();

            // 売付可能数量 > 注文中数量の場合
            //if (l_asset.getQuantity() > l_asset.getLockedQuantity())
            if (l_asset.getQuantity() > l_lockedQuantity)
            {
                log.debug("売付可能数量 > 注文中数量 なので、売付可能フラグ == true");
                // 売付可能フラグ = trueをセット
                l_balanceReferenceUnit.sellPossFlag = true;
            }
            else
            {
                log.debug("売付可能数量 <= 注文中数量 なので、売付可能フラグ == false");
                // 売付可能フラグ = falseをセット
                l_balanceReferenceUnit.sellPossFlag = false;
            }

            if (!l_orderValidationResult.equals(OrderValidationResult.
                                                VALIDATION_OK_RESULT))
            {
                log.debug("取引不可能顧客なので、買付／売付可能フラグ == false");
                // チェックOKでない場合は、買付/売付可能フラグにfalseをセット。
                l_balanceReferenceUnit.buyPossFlag = false;
                l_balanceReferenceUnit.sellPossFlag = false;
            }

            try
            {
                if (l_balanceReferenceUnit.buyPossFlag == true
                    || l_balanceReferenceUnit.sellPossFlag == true)
                {
                    // 現物株式売買停止チェック
                    l_orderManager.validateProductCode(l_eqtypeProduct.getProductCode(),
                        l_eqtypeProduct.getInstitution().getInstitutionCode());

                    // インサイダーチェック
                    l_orderManager.validateInsider(l_subAccount, l_eqtypeProduct);
                }
            }
            catch (WEB3BusinessLayerException l_wbex)
            {
                log.debug("銘柄の現物株式売買停止 または インサイダー顧客なので、買付／売付可能フラグ == false");
                // チェックOKでない場合は、買付/売付可能フラグにfalseをセット。
                l_balanceReferenceUnit.buyPossFlag = false;
                l_balanceReferenceUnit.sellPossFlag = false;
            }

            //性能チューニング修正 劉(FLJ)
            //市場機@能削除
//begin
//>>>>>
//            // 買付のis注文受付可能() == falseの場合
//            if (l_blnBuyOrderOkAllMarket == false)
//            {
//                log.debug("買付の注文受付不可能なので、買付可能フラグ == false");
//                // 買付可能フラグにfalseをセット。
//                l_balanceReferenceUnit.buyPossFlag = false;
//            }
//
//            // 売付のis注文受付可能() == falseの場合
//            if (l_blnSellOrderOkAllMarket == false)
//            {
//                log.debug("売付の注文受付不可能なので、売付可能フラグ == false");
//                // 売付可能フラグにfalseをセット。
//                l_balanceReferenceUnit.sellPossFlag = false;
//            }
//>>>>>
//end


            // 買付または、売付が可能な場合
            //性能チューニング修正 劉(FLJ)
            //市場機@能削除
            //delete begin
//>>>>>>>>>
//            if (l_balanceReferenceUnit.buyPossFlag == true
//                || l_balanceReferenceUnit.sellPossFlag == true)
//            {
//                boolean l_blnBuyOkThisMarket = false;
//                boolean l_blnSellOkThisMarket = false;
//
//                int l_intTradedMarketSize = l_strHandlingMarketCodes.length;
//                // パラメータ.取引可能市場一覧の要素数分Loop処理
//                for (int j = 0; j < l_intTradedMarketSize; j++)
//                {
//                    String l_strMarketCode = l_strHandlingMarketCodes[j];
//
//                    // 買付注文受付可能チェック
//                    l_blnBuyOkThisMarket = this.isOrderAcceptPossible(true, l_strMarketCode);
//
//                    // 売付注文受付可能チェック
//                    l_blnSellOkThisMarket = this.isOrderAcceptPossible(false, l_strMarketCode);
//
//                    // 買付・売付の注文受付可能が両方ともfalseの場合
//                    if (l_blnBuyOkThisMarket == false
//                        && l_blnSellOkThisMarket == false)
//                    {
//                        continue;
//                    }
//
//                    // reset市場コード
//                    WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
//
//                    // 買付の顧客銘柄別取引停止チェック
//                    try
//                    {
//                        l_orderManager.validateAccountProductOrderStop(l_subAccount,
//                                                                        l_eqtypeProduct.getProductId(),
//                                                                        OrderTypeEnum.EQUITY_BUY);
//                    } catch (WEB3BusinessLayerException l_wbex_buy) {
//                        try
//                        {
//	                        // 売付の顧客銘柄別取引停止チェック
//	                        l_orderManager.validateAccountProductOrderStop(l_subAccount,
//	                                                                        l_eqtypeProduct.getProductId(),
//	                                                                        OrderTypeEnum.EQUITY_SELL);
//	                    } catch (WEB3BusinessLayerException l_wbex_sell) {
//                           // 買付・売付両方とも例外をスローした場合は、次の要素へ処理を移行する。
//	                       continue;
//	                    }
//                    }
//
//                    // 市場オブジェクト取得
//                    WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
//                    WEB3GentradeMarket l_market = null;
//                    try
//                    {
//                        l_market =
//                            (WEB3GentradeMarket)l_finObjectManager.getMarket(l_institution, l_strMarketCode);
//                    } catch (NotFoundException l_ex) {
//                        log.error(STR_METHOD_NAME);
//                        throw new WEB3SystemLayerException(
//                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                            this.getClass().getName() + "." + STR_METHOD_NAME,
//                            "市場オブジェクトが取得できません。");
//                    }
//
//                    // 取引銘柄の売買規制チェック
//                    WEB3EquityTradedProduct l_tradedProduct = null;
//                    try
//                    {
//                        // 買付規制チェック
//                        l_tradedProduct =
//                            (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(l_subAccount, l_eqtypeProduct, l_market, false);
//                    } catch (WEB3BusinessLayerException l_wbex_buy) {
//                        try {
//	                        // 売付規制チェック
//	                        l_tradedProduct =
//	                            (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(l_subAccount, l_eqtypeProduct, l_market, true);
//                        } catch (WEB3BusinessLayerException l_wbex_sell) {
//                            // 買付・売付両方とも例外をスローした場合は、次の要素へ処理を移行する。
//                            continue;
//                        }
//                    }
//
//                    // 部店のJASDAQ銘柄取扱チェック
//                    try
//                    {
//                        WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_subAccount.getWeb3GenBranch();
//                        l_tradedProduct.validateJASDAQProductHandling(l_branch);
//                    } catch (WEB3BusinessLayerException l_wbex) {
//                        // 例外をスローした場合は、次の要素へ処理を移行する。
//                        continue;
//                    }
//
//                    // 市場コード一覧格納リストに追加
//                    l_lstMarketCodes.add(l_strMarketCode);
//                }
//
//            }
//
//            // 取引可能市場コードの一覧を取得
//            String[] l_strTradingMarketCode = (String[])l_lstMarketCodes.toArray(new String[0]);
//
//            if (l_strTradingMarketCode.length == 0)
//            {
//                log.debug("取引可能市場なしなので、買・売付可能フラグ = fales");
//                l_balanceReferenceUnit.buyPossFlag = false;
//                l_balanceReferenceUnit.sellPossFlag = false;
//            }
//
//            // 市場指定(パラメータ.指定市場コード != null)の場合
//            if (l_strSpecifiedMarketCode != null)
//            {
//                // 表示対象チェック
//                // ①@取引可能市場コード一覧の要素数が0
//                if (l_strTradingMarketCode.length == 0)
//                {
//                    // 次の保有資産へ
//                    continue;
//                }
//
//                // ②パラメータ.指定市場コードが取引可能市場コード一覧に存在しない
//                boolean l_blnIsContains;
//                l_blnIsContains = l_lstMarketCodes.contains(l_strSpecifiedMarketCode);
//                if (l_blnIsContains == false)
//                {
//                    // 市場コード一覧格納リストをクリア
//                    l_lstMarketCodes.clear();
//                    // 次の保有資産へ
//                    continue;
//                }
//            }
//>>>>>>>>>>>>>>
//delete end

            // 時価情報を取得する
            WEB3EquityProduct l_eqtypeProductForCurrentPrice = null;
            // 募集銘柄の場合
            if (l_eqtypeProduct.isSubscriptionProduct())
            {
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                l_eqtypeProductForCurrentPrice =
                    l_productManager.getProductFromSubscriptionProduct(l_eqtypeProduct);
            }
            else
            {
                l_eqtypeProductForCurrentPrice = l_eqtypeProduct;
            }
            WEB3EquityProductQuote l_productQuote =
                this.getEquityProductQuote(l_subAccount, l_eqtypeProductForCurrentPrice,
                                           l_hmEquityProductQuote);

            // 残高照会明細にプロパティをセットする。
            // ID
            l_balanceReferenceUnit.id = String.valueOf(l_asset.getAssetId());
            // 銘柄コード
            l_balanceReferenceUnit.productCode = l_eqtypeProduct.getProductCode();
            // 銘柄名
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow) l_eqtypeProduct.
                getDataSourceObject();
            l_balanceReferenceUnit.productName = l_eqtypeProductRow.getStandardName();
            // 口座区分
            l_balanceReferenceUnit.taxType = WEB3EquityDataAdapter.getTaxType(l_asset.getTaxType());
            // 売付可能株数
            l_balanceReferenceUnit.sellPossQuantity = WEB3StringTypeUtility.formatNumber(
                //l_asset.getQuantity() - l_asset.getLockedQuantity());
                l_asset.getQuantity() -l_lockedQuantity);
            // 注文中株数
            l_balanceReferenceUnit.orderedQuantity = WEB3StringTypeUtility.formatNumber(
                //l_asset.getLockedQuantity());
                l_lockedQuantity);
            // 市場コード一覧
            //性能チューニング修正 劉(FLJ)
            //市場機@能削除
            //delete begin
            //l_balanceReferenceUnit.marketList = l_strTradingMarketCode;
            //delete end

            // 残高株数
            double l_dblBalanceQuantity;
            l_dblBalanceQuantity = l_asset.getQuantity() +
                l_assetRow.getQuantityCannotSell();
            l_balanceReferenceUnit.balanceQuantity = WEB3StringTypeUtility.formatNumber(
                l_dblBalanceQuantity);
            // 売付不能数量
            l_balanceReferenceUnit.sellImpossQuantity = WEB3StringTypeUtility.
                formatNumber(l_assetRow.getQuantityCannotSell());
            // 概算簿価単価
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblBookValuePrice = l_bizLogicProvider.calcEstimatedBookPrice(
                l_assetRow.getBookValue(), l_assetRow.getQuantityForBookValue(), 0);
            if (l_dblBookValuePrice == 0)
            {
                l_balanceReferenceUnit.estimatedBookPrice = null;
            }
            else
            {
                l_balanceReferenceUnit.estimatedBookPrice = WEB3StringTypeUtility.formatNumber(
                    l_dblBookValuePrice);
            }
            // 簿価単価入力済フラグ
            if (l_assetRow.getInputBookValueIsNull() == false)
            {
                l_balanceReferenceUnit.estimatedBookPriceInputFlag = true;
            }
            // 時価が取得できた場合
            if (l_productQuote != null)
            {
                log.debug("***** 時価取得OK *****");
                // 時価
                double l_dblCurrentPrice = l_productQuote.getQuote();
                l_balanceReferenceUnit.currentPrice = WEB3StringTypeUtility.formatNumber(
                    l_dblCurrentPrice);
                // 前日比
                if (l_productQuote.getMarketCode() != null)
                {
	                l_balanceReferenceUnit.comparedPreviousDay = WEB3StringTypeUtility.
	                    formatNumber(l_productQuote.getComparedPreviousDay());
                }
                // 時価取得時間(HH:MM)
                log.debug("時価取得区分:[" + l_productQuote.getQuoteFromDiv() + "]");
                Timestamp l_quoteTime = l_productQuote.getQuoteTime();
                if (l_quoteTime != null)
                {
                    l_balanceReferenceUnit.currentPriceTime = WEB3DateUtility.formatDate(
                        l_quoteTime, "HH:mm");
                }
                // 優先市場コード
                l_balanceReferenceUnit.primaryMarketCode = l_productQuote.getMarketCode();
                // 概算評価額（残高株数）
                l_balanceReferenceUnit.estimatedAssetBalanceQuantity =
                    WEB3StringTypeUtility.formatNumber(this.calcEstimatedValue(
                    l_dblCurrentPrice, l_dblBalanceQuantity));
                // 概算評価額（売付可能株数）
                l_balanceReferenceUnit.estimatedAssetSellPossQuantity =
                    WEB3StringTypeUtility.formatNumber(this.calcEstimatedValue(
                    //l_dblCurrentPrice, l_asset.getQuantity() - l_asset.getLockedQuantity()));
                    l_dblCurrentPrice, l_asset.getQuantity() - l_lockedQuantity));

                // 概算評価額（注文中株数）
                l_balanceReferenceUnit.estimatedAssetOrderedQuantity =
                    WEB3StringTypeUtility.formatNumber(this.calcEstimatedValue(
                    //l_dblCurrentPrice, l_asset.getLockedQuantity()));
                    l_dblCurrentPrice, l_lockedQuantity));

                // 概算評価額（売付不能株数）
                l_balanceReferenceUnit.estimatedAssetSellImpossQuantity =
                    WEB3StringTypeUtility.formatNumber(this.calcEstimatedValue(
                    l_dblCurrentPrice, l_assetRow.getQuantityCannotSell()));

                // 概算簿価単価 != 0の場合、概算評価損益を算出する
                if (l_dblBookValuePrice != 0)
                {
                    // 概算評価損益（残高株数）
                    l_balanceReferenceUnit.estimatedAppraisalProfitLossBalanceQuantity =
                        WEB3StringTypeUtility.formatNumber(this.calcProfitLoss(
                        l_dblCurrentPrice, l_dblBookValuePrice,
                        l_dblBalanceQuantity));
                    // 概算評価損益（売付可能株数）
                    l_balanceReferenceUnit.estimatedAppraisalProfitLossSellPossQuantity =
                        WEB3StringTypeUtility.formatNumber(this.calcProfitLoss(
                        l_dblCurrentPrice, l_dblBookValuePrice,
                        //l_asset.getQuantity() - l_asset.getLockedQuantity()));
                        l_asset.getQuantity() - l_lockedQuantity));

                    // 概算評価損益（注文中株数）
                    l_balanceReferenceUnit.estimatedAppraisalProfitLossOrderedQuantity =
                        WEB3StringTypeUtility.formatNumber(this.calcProfitLoss(
                        l_dblCurrentPrice, l_dblBookValuePrice,
                        //l_asset.getLockedQuantity()));
                        l_lockedQuantity));
                    // 概算評価損益（売付不能株数）
                    l_balanceReferenceUnit.estimatedAppraisalProfitLossSellImpossQuantity =
                        WEB3StringTypeUtility.formatNumber(this.calcProfitLoss(
                        l_dblCurrentPrice, l_dblBookValuePrice,
                        l_assetRow.getQuantityCannotSell()));
                }
            }

            // 残高照会明細格納リストに追加
            l_lstBalanceReferenceUnit.add(l_balanceReferenceUnit);
            // 市場コード一覧格納リストをクリア
            //性能チューニング修正 劉(FLJ)
            //市場機@能削除
            //delete begin
            //l_lstMarketCodes.clear();
            //end
        }

        // 残高照会明細の配列を生成して返却
        log.exiting(STR_METHOD_NAME);
        return (WEB3EquityBalanceReferenceDetailUnit[]) l_lstBalanceReferenceUnit.toArray(new
            WEB3EquityBalanceReferenceDetailUnit[0]);
    }

    /**
     * (sort残高照会明細)<BR>
     *<BR>
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
     * 　@　@　@①@現物株式残高照会Comparatorを生成する。<BR>
     * <BR>
     * 　@　@　@　@[コンストラクタにセットするパラメータ] <BR>
     * 　@　@　@　@　@orderBy： ソートキー.昇順／降順<BR>
     * 　@　@　@　@　@比較項目：　@ソートキー.キー項目<BR>
     * <BR>
     * 　@　@　@②ArrayListに生成したComparatorを追加する。<BR>
     * <BR>
     * ４）WEB3ArraysUtility.sort()メソッドをコールする。 <BR>
     * <BR>
     * 　@[sort()にセットするパラメータ] <BR>
     * 　@　@obj：　@パラメータ.残高照会明細<BR>
     * 　@　@com：　@生成したArrayList.toArray()の戻り値<BR>
     * @@param l_balanceReferenceDetailUnit - (残高照会明細) 現物株式残高照会明細の配列<BR>
     * @@param l_sortKey - (ソートキー) 現物株式ソートキーの配列<BR>
     * @@roseuid 41B66ABC0198<BR>
     */
    protected void sortBalanceReferenceDetailUnit(WEB3EquityBalanceReferenceDetailUnit[]
                                                  l_balanceReferenceDetailUnit,
                                                  WEB3EquitySortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME =
            "sortBalanceReferenceDetailUnit(WEB3EquityBalanceReferenceDetailUnit, WEB3EquitySortKey)";
        log.entering(STR_METHOD_NAME);

        // パラメータ.残高照会明細 == nullの場合、処理終了
        if (l_balanceReferenceDetailUnit == null ||
            l_balanceReferenceDetailUnit.length == 0)
        {
            return;
        }

        ArrayList l_lstComparators = new ArrayList();

        // パラメータ.ソートキーの要素数分Loop処理
        WEB3EquityBalanceReferenceComparator l_comparator = null;
        String l_strOrderBy = null;
        String l_strKeyItem = null;
        for (int i = 0; i < l_sortKey.length; i++)
        {
            l_strOrderBy = l_sortKey[i].ascDesc;
            l_strKeyItem = l_sortKey[i].keyItem;
            log.debug("第" + (i + 1) + "ソートキー：" + l_strKeyItem + " " + l_strOrderBy);

            // 現物株式残高照会Comparatorを生成
            l_comparator = new WEB3EquityBalanceReferenceComparator(l_strOrderBy,
                l_strKeyItem);
            // ArrayListにComparatorを追加
            l_lstComparators.add(l_comparator);
        }

        // ソート
        WEB3ArraysUtility.sort(l_balanceReferenceDetailUnit,
                               (WEB3EquityBalanceReferenceComparator[]) l_lstComparators.
                               toArray(new WEB3EquityBalanceReferenceComparator[0]));
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get時価情報)<BR>
     *<BR>
     * 時価情報を取得する。<BR>
     * <BR>
     * １）時価情報検索<BR>
     * 　@パラメータ.時価情報格納リスト.get()メソッドをコールする。<BR>
     * <BR>
     * 　@[get()にセットするパラメータ]<BR>
     * 　@　@key：　@パラメータ.株式銘柄.銘柄コード<BR>
     * 　@　@　@　@　@+ パラメータ.株式銘柄.優先市場<BR>
     * <BR>
     * ２）時価情報返却<BR>
     * 　@１）の戻り値 != nullの場合、取得した時価情報を返却する。<BR>
     * <BR>
     * ３）時価情報取得<BR>
     * 　@１）の戻り値 == nullの場合、以下の手順で時価情報を取得する。<BR>
     * 　@３－１）パラメータ.株式銘柄.get優先市場時価情報()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[get優先市場時価情報()にセットするパラメータ]<BR>
     * 　@　@　@補助口座：　@パラメータ.補助口座<BR>
     * <BR>
     * 　@３－２）パラメータ.時価情報格納リスト.put()メソッドをコールし、<BR>
     * 　@　@取得した時価情報を格納する。<BR>
     * <BR>
     * 　@　@[put()にセットするパラメータ]<BR>
     * 　@　@　@key：　@パラメータ.株式銘柄.銘柄コード<BR>
     * 　@　@　@　@　@　@+ パラメータ.株式銘柄.優先市場<BR>
     * 　@　@　@value：　@取得した時価情報<BR>
     * <BR>
     * 　@３－３）取得した時価情報を返却する。<BR>
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト<BR>
     * @@param l_eqtypeProduct - (株式銘柄) 株式銘柄オブジェクト<BR>
     * @@param l_hmProductQuote - (時価情報格納リスト) - <BR>
     * 銘柄コード+市場コードをキーとして、時価情報を格納するリスト<BR>
     * @@return WEB3EquityProductQuote<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41B697730077<BR>
     */
    protected WEB3EquityProductQuote getEquityProductQuote(WEB3GentradeSubAccount
        l_subAccount, WEB3EquityProduct l_eqtypeProduct, HashMap l_hmProductQuote) throws
        WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEquityProductQuote(WEB3GentradeSubAccount, WEB3EquityProduct, HashMap)";
        log.entering(STR_METHOD_NAME);

        // 優先市場取得
        WEB3GentradeMarket l_market =
            (WEB3GentradeMarket) l_eqtypeProduct.getPrimaryMarket();

        // 時価情報検索
        String l_strKey = "";
        WEB3EquityProductQuote l_productQuote = null;
        if (l_market != null)
        {
            l_strKey = l_eqtypeProduct.getProductCode() + l_market.getMarketCode();
            l_productQuote = (WEB3EquityProductQuote) l_hmProductQuote.get(l_strKey);
        }
        else
        {
            Market[] l_tradedMarkets = l_eqtypeProduct.getTradedMarkets();
            if (l_tradedMarkets == null)
            {
                return null;
            }

            for (int i = 0; i < l_tradedMarkets.length; i++)
            {
                l_strKey = l_eqtypeProduct.getProductCode() +
                    l_tradedMarkets[i].getMarketCode();
                l_productQuote = (WEB3EquityProductQuote) l_hmProductQuote.get(l_strKey);
                if (l_productQuote != null)
                {
                    break;
                }
            }
        }

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
                // 優先市場の時価情報を取得する。
                l_productQuote = l_eqtypeProduct.getPrimaryMarketProductQuote(
                    l_subAccount);

                // パラメータ.時価情報格納リストに取得した時価情報を追加する
                // key: パラメータ.株式銘柄.銘柄コード + パラメータ.株式銘柄.優先市場
                l_hmProductQuote.put(l_strKey, l_productQuote);

            }
            catch (WEB3BusinessLayerException l_wbex)
            {
                l_productQuote = null;
            }
            catch (WEB3SystemLayerException l_wsex)
            {
                throw l_wsex;
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
     * @@param l_dblPrice - (評価単価) 評価単価<BR>
     * @@param l_dblQuantity - (数量) 数量<BR>
     * @@return double<BR>
     * @@roseuid 41B69CF50077<BR>
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
     *<BR>
     * 評価損益を算出し、返却する。<BR>
     * ※手数料は含まない。<BR>
     * <BR>
     * (パラメータ.評価単価 - パラメータ.簿価) * パラメータ.数量<BR>
     * の結果を返却する。<BR>
     * @@param l_dblPrice - (評価単価) 評価単価<BR>
     * @@param l_dblBookValue - (簿価) 簿価<BR>
     * @@param l_dblQuantity - (数量) 数量<BR>
     * @@return double<BR>
     * @@roseuid 41B69D4A00A5<BR>
     */
    protected double calcProfitLoss(double l_dblPrice, double l_dblBookValue,
                                    double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "calcProfitLoss(double, double, double)";
        log.entering(STR_METHOD_NAME);

        // 評価損益 = (パラメータ.評価単価 - パラメータ.簿価) * パラメータ.数量
        double l_dblResult = (l_dblPrice - l_dblBookValue) * l_dblQuantity;

        log.exiting(STR_METHOD_NAME);
        return l_dblResult;
    }

    /**
     * (is注文受付可能)<BR>
     *<BR>
     * 引数に指定された取引、市場が注文受付可能であるか<BR>
     * 判別する。<BR>
     * 注文受付可能である場合は、true。以外、falseを返却する。<BR>
     * <BR>
     * １）注文受付トランザクションのリセット<BR>
     * 　@取引時間管理.reset注文受付トランザクション()をコールする。<BR>
     * <BR>
     * 　@[reset注文受付トランザクション()にセットするパラメータ]<BR>
     * 　@　@注文受付トランザクション：<BR>
     * 　@　@　@パラメータ.is買注文 == trueの場合、"買付"をセット。<BR>
     * 　@　@　@以外、"売付"をセット。<BR>
     * <BR>
     * ２）市場コードのリセット<BR>
     * 　@パラメータ.市場コード != nullの場合、<BR>
     * 　@取引時間管理.reset市場コード()をコールする。<BR>
     * <BR>
     * 　@[reset市場コード()にセットするパラメータ]<BR>
     * 　@　@市場コード：　@パラメータ.市場コード<BR>
     * <BR>
     * ３）注文受付可能のチェック<BR>
     * 　@取引時間管理.validate注文受付可能()をコールし、<BR>
     * 　@例外がスローされた場合は、falseを返却する。<BR>
     * 　@以外、trueを返却する。<BR>
     * <BR>
     * 　@※true/falseを返却する前に、<BR>
     * 　@　@取引時間管理.reset市場コード(null)メソッドを<BR>
     * 　@　@コールし、市場コードをnullで初期化すること。<BR>
     * @@param l_blnIsBuyOrder - (is買注文) 買注文かどうかのフラグ<BR>
     * <BR>
     * false：　@売付注文<BR>
     * true：　@買付注文<BR>
     * @@param l_strMarketCode - (市場コード) 市場コード<BR>
     * <BR>
     * ※全市場対象の場合は、nullをセット。<BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C0F70903E4<BR>
     */
    protected boolean isOrderAcceptPossible(boolean l_blnIsBuyOrder,
                                            String l_strMarketCode) throws
        WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOrderAcceptPossible(boolean, String)";
        log.entering(STR_METHOD_NAME);

        // 注文受付トランザクションのリセット
        if (l_blnIsBuyOrder)
        {
            // 注文受付トランザクション = "買付"をセット
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
        }
        else
        {
            // 注文受付トランザクション = "売付"をセット
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        }

        // パラメータ.市場コード != nullの場合、市場コードをリセットする。
        if (l_strMarketCode != null)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }

        // 注文受付可能のチェック
        try
        {
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BusinessLayerException l_wbex)
        {
            // 例外がスローされた場合はfalseを返却
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (WEB3SystemLayerException l_wsex)
        {
            throw l_wsex;
        }
        finally
        {
            // 取引カレンダコンテキスト.市場コードをnullで初期化
            WEB3GentradeTradingTimeManagement.resetMarketCode(null);
        }

        // 例外がスローされない場合はtrueを返却
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    private Hashtable getLockedAssetDetails(
        WEB3GentradeSubAccount l_subAccount
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getLockedAssetDetails(SubAccount)";
        log.entering(STR_METHOD_NAME);
        Hashtable l_ret = new Hashtable(5);

        if (l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                                               STR_METHOD_NAME);
        }
        try
        {
            List list = LockedAssetDetailsDao.findRowsByAccountIdSubAccountId( (
                SubAccountRow)
                l_subAccount.getDataSourceObject());
            for (int i = 0; i < list.size(); i++)
            {
                LockedAssetDetailsRow row = (LockedAssetDetailsRow) list.get(i);
                l_ret.put(new Long(row.getAssetId()), new Double(row.getLockedQuantity()));
            }
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                               this.getClass().getName() + "." +
                                               STR_METHOD_NAME, e.getMessage(), e);

        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                               this.getClass().getName() + "." +
                                               STR_METHOD_NAME, e.getMessage(), e);

        }
        log.exiting(STR_METHOD_NAME);
        return l_ret;
    }

}
@
