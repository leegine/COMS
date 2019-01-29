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
filename	WEB3MstkBookValuePriceRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :株式ミニ投資簿価単価登録サービスImpl(WEB3MstkBookValuePriceRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
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
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3MiniClientRequestService;
import webbroker3.equity.message.WEB3MstkBookPriceInputRequest;
import webbroker3.equity.message.WEB3MstkBookPriceInputResponse;
import webbroker3.equity.message.WEB3MstkBookPriceRegistRequest;
import webbroker3.equity.message.WEB3MstkBookPriceRegistResponse;
import webbroker3.equity.service.delegate.WEB3MstkBookValuePriceRegistService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （株式ミニ投資簿価単価登録サービスImpl）。<BR>
 * <BR>
 * 株式ミニ投資簿価単価登録サービス実装クラス<BR>
 */
public class WEB3MstkBookValuePriceRegistServiceImpl
extends WEB3MiniClientRequestService
implements WEB3MstkBookValuePriceRegistService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBookValuePriceRegistServiceImpl.class);    
        
    /**
     * @@roseuid 4206F07D03B5<BR>
     */
    public WEB3MstkBookValuePriceRegistServiceImpl() 
    {
     
    }
    
    /**
     * 株式ミニ投資簿価単価登録処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○株式ミニ投資簿価単価登録入力リクエストの場合<BR>
     * 　@this.get入力画面()メソッドをコールする。<BR>
     * <BR>
     * ○株式ミニ投資簿価単価登録リクエストの場合<BR>
     * 　@this.submit簿価単価登録()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41C6703F028E<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "WEB3MstkBookValuePriceRegistServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.error("パラメータ.リクエストデータがnullです。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3MstkBookPriceInputRequest)
        {
            l_response = 
                this.getInputScreen((WEB3MstkBookPriceInputRequest)l_request);
        }
        else if (l_request instanceof WEB3MstkBookPriceRegistRequest)
        {
            l_response = 
                this.submitBookValuePrice((WEB3MstkBookPriceRegistRequest)l_request);
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
     * 株式ミニ投資簿価単価登録入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ミニ株簿価単価登録サービス）get入力画面」<BR>
     * 参照<BR>
     * @@param l_request - (リクエストデータ) 株式ミニ投資簿価単価登録入力リクエストオブジェクト<BR>
     * @@return WEB3MstkBookPriceInputResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C670450230<BR>
     */
    protected WEB3MstkBookPriceInputResponse getInputScreen(WEB3MstkBookPriceInputRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3MstkBookPriceInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate
        l_request.validate();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 拡張ポジションマネージャ取得
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            
        // getAsset
        WEB3EquityAsset l_asset = null;
        try
        {
            l_asset =
                (WEB3EquityAsset)l_positionManager.getAsset(Long.parseLong(l_request.assetId));
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産ID:[" + l_request.assetId + "]に該当するデータが存在しません。");
        }
        
        // 一般口座チェック
        if (!l_asset.getTaxType().equals(TaxTypeEnum.NORMAL))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産ID:[" + l_request.assetId + "]は税区分：一般の保有資産ではありません。");
        }
        
        // getProduct
        WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_asset.getProduct();
        
        // レスポンスデータ生成&プロパティセット
        WEB3MstkBookPriceInputResponse l_response =
            (WEB3MstkBookPriceInputResponse)l_request.createResponse();
        
        // 保有資産ID
        l_response.assetId = String.valueOf(l_asset.getAssetId());
        // 銘柄コード
        l_response.productCode = l_eqtypeProduct.getProductCode();
        // 銘柄名
        EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
        l_response.productName = l_eqtypeProductRow.getStandardName();
        // 口座区分
        if (l_asset.getTaxType().equals(TaxTypeEnum.NORMAL))
        {
            l_response.taxType = WEB3TaxTypeDef.NORMAL;
        }
        else if (l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL)
            || l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
        {
            l_response.taxType = WEB3TaxTypeDef.OTHER;
        }
        // 残高株数
        AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
        double l_dblBalanceQuantity = l_asset.getQuantity() + l_assetRow.getQuantityCannotSell();
        l_response.balanceQuantity = WEB3StringTypeUtility.formatNumber(l_dblBalanceQuantity);
        
        // 入力簿価単価&簿価単価入力日時
        if (l_assetRow.getInputBookValueIsNull())
        {
            l_response.inputBookPrice = null;
            l_response.bookPriceInputDate = null;
        }
        else
        {
            l_response.inputBookPrice = WEB3StringTypeUtility.formatNumber(l_assetRow.getInputBookValue());
            l_response.bookPriceInputDate = l_assetRow.getInputTimestamp();
        }
        // 概算簿価単価
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblBookValuePrice = l_bizLogicProvider.calcEstimatedBookPrice(
            l_asset.getBookValue(),
            l_assetRow.getQuantityForBookValue(),
            0);
        
        l_response.estimatedBookPrice = WEB3StringTypeUtility.formatNumber(l_dblBookValuePrice);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * 株式ミニ投資簿価単価登録処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ミニ株簿価単価登録サービス）submit簿価単価登録」<BR>
     * 参照<BR>
     * @@param l_request - (リクエストデータ) 株式ミニ投資簿価単価登録リクエストオブジェクト<BR>
     * @@return WEB3MstkBookPriceRegistResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C67045024F<BR>
     */
    protected WEB3MstkBookPriceRegistResponse submitBookValuePrice(WEB3MstkBookPriceRegistRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitBookValuePrice(WEB3EquityBookPriceRegistRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate
        l_request.validate();
        
        // get補助口座
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        // 拡張アカウントマネージャ、拡張ポジションマネージャ取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            
        // 顧客情報取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
            
        // lock口座
        l_accountManager.lockAccount(
            l_institution.getInstitutionCode(),
            l_branch.getBranchCode(),
            l_mainAccount.getAccountCode());
        
        // getAsset
        WEB3EquityAsset l_asset = null;
        try
        {
            l_asset =
                (WEB3EquityAsset)l_positionManager.getAsset(Long.parseLong(l_request.assetId));
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産ID:[" + l_request.assetId + "]に該当するデータが存在しません。");
        }
        
        // 計算した簿価の算出
        AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
        double l_dblAfterBookValuePrice = Double.parseDouble(l_request.aftBookPrice);
        // 計算した簿価 = (getAsset()の戻り値.数量 + getAsset()の戻り値.売付不能数量) * リクエストデータ.変更後簿価単価
        double l_dblCalcBookValue =
            (l_asset.getQuantity() + l_assetRow.getQuantityCannotSell()) * l_dblAfterBookValuePrice;
        
        // 計算した簿価の桁数チェック
        if (WEB3StringTypeUtility.formatNumber(l_dblCalcBookValue).length() >= 12)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01921,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力した簿価単価の値が大きすぎます。(計算後の簿価の桁数が12桁以上)");
        }
        
        // 一般口座チェック
        if (!l_asset.getTaxType().equals(TaxTypeEnum.NORMAL))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産ID:[" + l_request.assetId + "]は税区分：一般の保有資産ではありません。");
        }
        
        // create保有資産Params
        AssetParams l_assetParams = this.createAssetParams(l_asset, l_dblAfterBookValuePrice);
        
        // 保有資産を更新する
        try
        {
            WEB3DataAccessUtility.updateRow(l_assetParams);
        } catch (DataException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産の更新処理に失敗しました。", l_ex);
        }
        
        // レスポンスデータ生成
        WEB3MstkBookPriceRegistResponse l_response =
            (WEB3MstkBookPriceRegistResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * 引数の保有資産から、保有資産Paramsを生成する。<BR>
     * <BR>
     * １）AssetRowオブジェクトを取得する。<BR>
     * 　@パラメータ.保有資産.getDataSourceObject()メソッドを<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）AssetParamsインスタンスを生成する。<BR>
     * <BR>
     * ３）データのコピー<BR>
     * 　@GtlUtils.copyRow2Params()メソッドをコールする。<BR>
     * <BR>
     * 　@[copyRow2Params()にセットするパラメータ]<BR>
     * 　@　@arg0：　@１）の戻り値<BR>
     * 　@　@arg1：　@２）の戻り値<BR>
     * <BR>
     * ４）プロパティセット<BR>
     * 　@生成したAssetParamsに以下のプロパティをセットする。<BR>
     * <BR>
     * 　@数量(簿価単価計算用) = AssetParams.数量<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ AssetParams.売付不能数量<BR>
     * 　@簿価(簿価単価計算用) =  (AssetParams.数量<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ AssetParams.売付不能数量)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@* パラメータ.簿価単価<BR>
     * 　@入力簿価単価 = パラメータ.簿価単価<BR>
     * 　@簿価単価入力日時 = 現在時刻(*1)<BR>
     * 　@更新日付 = 現在時刻<BR>
     * <BR>
     * ５）プロパティセットしたAssetParamsを返却する。<BR>
     * <BR>
     * (*1)<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG)<BR>
     * にて取得した現在時刻。<BR>
     * @@param l_asset - (保有資産) 保有資産オブジェクト<BR>
     * @@param l_dblBookValuePrice - (簿価単価) 簿価単価<BR>
     * @@return AssetParams<BR>
     * @@roseuid 41C67130039F<BR>
     */
    protected AssetParams createAssetParams(WEB3EquityAsset l_asset, double l_dblBookValuePrice) 
    {
        final String STR_METHOD_NAME = "createAssetParams(WEB3EquityAsset, double)";
        log.entering(STR_METHOD_NAME);
        
        // AssetRowオブジェクトの取得
        AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
        
        // AssetParamsインスタンスの生成
        AssetParams l_assetParams = new AssetParams();
        GtlUtils.copyRow2Params(l_assetRow, l_assetParams);
        
        // プロパティセット
        // 数量（簿価単価計算用）
        l_assetParams.quantity_for_book_value = l_assetParams.quantity + l_assetParams.quantity_cannot_sell;
        // 簿価（簿価単価計算用）
        l_assetParams.book_value =
            (l_assetParams.quantity + l_assetParams.quantity_cannot_sell) * l_dblBookValuePrice;
        // 入力簿価単価
        l_assetParams.input_book_value = new Double(l_dblBookValuePrice);
        
        // 現在時刻を取得
        Timestamp l_currentTime = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        // 簿価単価入力日時
        l_assetParams.input_timestamp = l_currentTime;
        // 更新日付
        l_assetParams.last_updated_timestamp = l_currentTime;
        
        log.exiting(STR_METHOD_NAME);
        return l_assetParams;
    }
}
@
