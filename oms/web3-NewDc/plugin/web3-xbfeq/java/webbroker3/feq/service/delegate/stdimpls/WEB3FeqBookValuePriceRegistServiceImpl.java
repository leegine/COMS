head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookValuePriceRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式簿価単価登録サービスImpl(WEB3FeqBookValuePriceRegistServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 黄建 (中訊) 新規作成   
                 : 2005/08/03 鄭海良(中訊) レビュー       
Revesion History : 2008/01/14 柴双紅(中訊) モデルNo.371、No.374、No.376、No.380、ＤＢ更新仕様No.088、No.089
Revesion History : 2008/01/29 柴双紅(中訊) モデルNo.388
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.message.WEB3FeqBookPriceConfirmRequest;
import webbroker3.feq.message.WEB3FeqBookPriceConfirmResponse;
import webbroker3.feq.message.WEB3FeqBookPriceInputRequest;
import webbroker3.feq.message.WEB3FeqBookPriceInputResponse;
import webbroker3.feq.message.WEB3FeqBookPriceRegistRequest;
import webbroker3.feq.message.WEB3FeqBookPriceRegistResponse;
import webbroker3.feq.service.delegate.WEB3FeqBookValuePriceRegistService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式簿価単価登録サービスImpl)<BR>
 * 外国株式簿価単価登録サービス実装クラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqBookValuePriceRegistServiceImpl extends WEB3FeqClientRequestService 
    implements WEB3FeqBookValuePriceRegistService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FeqBookValuePriceRegistServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F802EE
     */
    public WEB3FeqBookValuePriceRegistServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式簿価単価登録サービスを行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * リクエストデータが、<BR>
     * [外国株式簿価単価登録入力リクエストの場合]<BR>
     * 　@this.入力画面()をコールする。<BR>
     * <BR>
     * [外国株式簿価単価確認リクエストの場合]<BR>
     * 　@this.validate簿価単価()をコールする。<BR>
     * <BR>
     * [外国株式簿価単価登録リクエストの場合]<BR>
     * 　@this.submit簿価単価()をコールする。<BR>
     * @@param l_request - <リクエスト><BR>
     * リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A937900079
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FeqBookPriceInputRequest)
        {
            //get入力画面
            l_response =
                this.getInputScreen(
                    (WEB3FeqBookPriceInputRequest) l_request);
        }
        else if (l_request instanceof WEB3FeqBookPriceConfirmRequest)
        {
            //外国株式簿価単価確認リクエストの場合
            l_response =
                this.validateBookValuePrice(
                    (WEB3FeqBookPriceConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3FeqBookPriceRegistRequest)
        {
            //validate注文
            l_response =
                this.submitBookValuePrice(
                    (WEB3FeqBookPriceRegistRequest) l_request);
        }
        else
        {
            log.debug(
                "リクエストデータが"
                    + " WEB3FeqBookPriceInputRequest "
                    + "と WEB3FeqBookPriceRegistRequest 以外である, but is "
                    + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
  
        log.exiting(STR_METHOD_NAME); 
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 外国株式簿価単価登録入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株簿価単価登録）get入力画面」参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@外株簿価単価登録」(外株簿価単価登録）get入力画面)<BR>
     * 　@　@:  1.3 (*)一般口座チェック<BR> 
     * 　@　@(一般口座チェック)<BR>
     *     getAsset()の戻り値.税区分 != "一般"の場合、<BR>
     *     「処理対象外データ」の例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02178<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式簿価単価登録入力リクエストオブジェクト
     * @@return webbroker3.feq.message.WEB3FeqBookPriceInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A94C3C01F0
     */
    protected WEB3FeqBookPriceInputResponse getInputScreen(WEB3FeqBookPriceInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqBookPriceInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);    
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2  getAsset(arg0 : long)
        //保有資産を取得する。 
        //[引数] 
        //arg0：　@リクエストデータ.保有資産ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        //保有資産        
        Asset l_asset = null;
        long l_lngAsstId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
        {
            l_lngAsstId = Long.parseLong(l_request.assetId);
        }
        try
        {
            l_asset = 
                l_feqPositionManager.getAsset(l_lngAsstId);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //保有資産.保有資産ID
        long l_lngAssetId = l_asset.getAssetId();
        
        //1.3 (*)一般口座チェック
        //(一般口座チェック)
        //getAsset()の戻り値.税区分 != "一般"の場合、
        //「処理対象外データ」の例外をスローする。
        TaxTypeEnum l_taxType = l_asset.getTaxType();
        log.debug("getAsset()の戻り値.税区分 = " + l_taxType);
        if (!TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            log.debug("処理対象外データ。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理対象外データ。");
        }
              
        //1.4 getProduct(arg0 : long)
        //外国株式銘柄を取得する。 
        //[引数] 
        //arg0：　@保有資産.銘柄ID
        
        //銘柄ID 
        long l_lngProductId = l_asset.getProduct().getProductId();
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct l_feqProduct =
            (WEB3FeqProduct) l_feqProductManager.getFeqProduct(l_lngProductId);
        
        //1.5 createResponse( )
        //レスポンスデータを生成する。
        WEB3FeqBookPriceInputResponse l_response = 
            (WEB3FeqBookPriceInputResponse) l_request.createResponse();
            
        //1.6 (*)レスポンスデータにプロパティをセットする。
        //保有資産ID  ＝　@保有資産.保有資産ID
        l_response.assetId = l_lngAssetId + "";
        
        //銘柄コード       ＝　@getProduct()の戻り値.銘柄コード
        l_response.productCode = l_feqProduct.getProductCode();
                
        //現地銘柄コード ＝　@getProduct()の戻り値.現地銘柄コード
        l_response.localProductCode = l_feqProduct.getOffshoreProductCode();
        
        //銘柄名     ＝　@getProduct()の戻り値.get表示銘柄名()の戻り値
        l_response.productName = l_feqProduct.getDisplayProductName();
        
        //口座区分   ＝保有資産.税区分 == "一般"の場合、"一般"をセット。
        String l_strTaxType = null;
        if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else 
        {
            if (TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
        }
        l_response.taxType = l_strTaxType;
        
        AssetRow l_assetRow = (AssetRow) l_asset.getDataSourceObject();
        
        //残高株数 ＝保有資産.数量 + 保有資産.売付不能数量
        double l_dbBalanceQuantity =  
            l_assetRow.getQuantity() + l_assetRow.getQuantityCannotSell();
        l_response.balanceQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dbBalanceQuantity);
        
        //売付可能株数 ＝保有資産.数量 - 保有資産.getLockedQuantity()
        double l_dblSellPossQuantity = 
            l_asset.getQuantity() - l_asset.getLockedQuantity();
        l_response.sellPossQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblSellPossQuantity);
        
        //注文中株数   ＝保有資産.getLockedQuantity()
        l_response.orderedQuantity = 
            WEB3StringTypeUtility.formatNumber(l_asset.getLockedQuantity());
        
        //売付不能株数  ＝保有資産.売付不能株数
        l_response.sellImpossQuantity = 
            WEB3StringTypeUtility.formatNumber(l_assetRow.getQuantityCannotSell());

        //入力簿価単価  ＝保有資産.入力簿価単価
        if (l_assetRow.getInputBookValueIsNull())
        {
            l_response.inputBookPrice = null;
        }
        else
        {
            l_response.inputBookPrice = 
                WEB3StringTypeUtility.formatNumber(l_assetRow.getInputBookValue());
        }
            
        //簿価単価入力日時    ＝保有資産.簿価単価入力日時
        l_response.bookPriceInputDate = l_assetRow.getInputTimestamp();
        
        //概算簿価単価  ＝外国株式計算サービス.calc概算簿価単価(
        //  保有資産.簿価(簿価単価計算用), 保有資産.数量(簿価単価計算用))
        //　@　@※小数点6位を四捨五入する。

        //外国株式計算サービス
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider) l_tradingModule.getBizLogicProvider();
        BigDecimal l_bdPrice =
            l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                l_assetRow.getBookValue(),
                l_assetRow.getQuantityForBookValue());
        BigDecimal l_bdEstimatedBookPrice = l_bdPrice.setScale(5, BigDecimal.ROUND_HALF_UP);
        l_response.estimatedBookPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdEstimatedBookPrice.doubleValue());
                
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit簿価単価)<BR>
     * 外国株式簿価単価登録処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株簿価単価登録）submit簿価単価」参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@外株簿価単価登録」(外株簿価単価登録）submit簿価単価)<BR>
     * 　@　@:  1.5 (*)桁数チェック<BR> 
     *     (桁数チェック)<BR> 
     *     計算した簿価(*1)の桁数　@>= 12桁の場合、<BR> 
     *     「計算後簿価エラー（桁数超過）」の例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01921<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /<BR>
     * 　@ 外株簿価単価登録」(外株簿価単価登録）submit簿価単価)<BR>
     * 　@　@:  1.6 (*)一般口座チェック<BR> 
     *     (一般口座チェック)<BR> 
     *     getAsset()の戻り値.税区分 != "一般"の場合、<BR>
     *     「処理対象外データ」の例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02178<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式簿価単価登録リクエストオブジェクト
     * @@return webbroker3.feq.message.WEB3FeqBookPriceRegistResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A94C3C0210
     */
    protected WEB3FeqBookPriceRegistResponse submitBookValuePrice(WEB3FeqBookPriceRegistRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitBookValuePrice(WEB3FeqBookPriceRegistRequest l_request) ";
        log.entering(STR_METHOD_NAME);    
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2 get補助口座( )
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();    
            
        //1.3 lock口座(String, String, String)   
        //口座をロックする。 
        //[引数] 
        //証券会社コード：　@補助口座.証券会社コード 
        //部店コード：　@補助口座.getMainAccount().部店コード 
        //口座コード：　@補助口座.getMainAccount().口座コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        //証券会社コード
        String l_strInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        //部店コード
        String l_strBranchCode = 
            l_subAccount.getMainAccount().getBranch().getBranchCode();
        //口座コード
        String l_strAccountCode = 
            l_subAccount.getMainAccount().getAccountCode();     
        l_accountManager.lockAccount(
            l_strInstitutionCode, 
            l_strBranchCode, 
            l_strAccountCode);
                
        //1.4  getAsset(arg0 : long)
        //保有資産を取得する。 
        //[引数] 
        //arg0：　@リクエストデータ.保有資産ID
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        //保有資産        
        Asset l_asset = null;
        long l_lngAsstId = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.assetId))
        {
            l_lngAsstId = Long.parseLong(l_request.assetId);
        }
        try
        {
            l_asset = 
                l_feqPositionManager.getAsset(l_lngAsstId);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //validate残高株数(Asset, Double)
        //[引数]
        //保有資産　@：　@getAsset()の戻り値
        //残高株数　@：　@リクエストデータ．残高株数
        double l_dblBalanceQuantity = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.balanceQuantity))
        {
            l_dblBalanceQuantity = Double.parseDouble(l_request.balanceQuantity);
        }
        this.validateBalanceQuantity(
            l_asset,
            l_dblBalanceQuantity);

        //1.6 (一般口座チェック)
        //getAsset()の戻り値.税区分 != "一般"の場合、
        //「処理対象外データ」の例外をスローする。
        
        TaxTypeEnum l_taxType = l_asset.getTaxType();
        log.debug("getAsset()の戻り値.税区分 = " + l_taxType);
        if (!TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            log.debug("処理対象外データ。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理対象外データ。");
        }
        
        //1.7 create保有資産Params(Asset, double)
        //保有資産Paramsを作成する。 
        //[引数] 
        //保有資産：　@getAsset()の戻り値 
        //簿価単価：　@リクエストデータ.変更後簿価単価
        //簿価金額：　@リクエストデータ.計算用入力簿価金額
        //残高株数：　@リクエストデータ.残高株数
        AssetParams l_assetParams =
            this.createAssetParams(
                l_asset, 
                Double.parseDouble(l_request.aftBookPrice),
                Double.parseDouble(l_request.aftBookAmount),
                l_dblBalanceQuantity);
        
        //1.8 updateRow(l_row : Row)
        //保有資産を更新する。 
        //[引数] 
        //保有資産Params：　@create保有資産Params()の戻り値
        try
        {
            WEB3DataAccessUtility.updateRow(l_assetParams);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.9 createResponse( )
        WEB3FeqBookPriceRegistResponse l_response = 
            (WEB3FeqBookPriceRegistResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (create保有資産Params)<BR>
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
     * 　@数量(簿価単価計算用) = パラメータ．残高株数<BR>
     * 　@簿価(簿価単価計算用) =  パラメータ．簿価金額<BR>
     * 　@入力簿価単価 = パラメータ.簿価単価<BR>
     * 　@簿価単価入力日時 = 現在時刻(*1)<BR>
     * 　@更新日付 = 現在時刻<BR>
     * <BR>
     * ５）プロパティセットした保有資産Paramsを返却する。<BR>
     * <BR>
     * (*1)<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG)<BR>
     * にて取得した現在時刻。<BR>
     * @@param l_asset - (保有資産)<BR>
     * 保有資産オブジェクト
     * @@param l_dblBookPrice - (簿価単価)<BR>
     * 簿価単価
     * @@param l_dblBookAmount - (簿価金額)<BR>
     * 簿価金額
     * @@param l_dblBalanceQuantity - (残高株数)<BR>
     * 残高株数
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams
     * @@roseuid 42A95515026D
     */
    protected AssetParams createAssetParams(
        Asset l_asset,
        double l_dblBookPrice,
        double l_dblBookAmount,
        double l_dblBalanceQuantity) 
    {
        final String STR_METHOD_NAME = 
            "createAssetParams(Asset, double, double, double)";
        log.entering(STR_METHOD_NAME);    
        
        //引数の保有資産から、保有資産Paramsを生成する。 
        //１）AssetRowオブジェクトを取得する。 
        //パラメータ.保有資産.getDataSourceObject()メソッドを 
        //コールする。 
        AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
        
        //２）AssetParamsインスタンスを生成する。 
        //３）データのコピー 
        //GtlUtils.copyRow2Params()メソッドをコールする。 
        //[copyRow2Params()にセットするパラメータ] 
        //arg0：　@１）の戻り値 
        //arg1：　@２）の戻り値 
        AssetParams l_assetParams = new AssetParams(l_assetRow);
        
        //４）プロパティセット 
        //生成したAssetParamsに以下のプロパティをセットする。 
        //数量(簿価単価計算用) = パラメータ．残高株数
        l_assetParams.setQuantityForBookValue(l_dblBalanceQuantity);

        //簿価(簿価単価計算用) =  パラメータ．簿価金額
        l_assetParams.setBookValue(l_dblBookAmount);
        
        //入力簿価単価 = パラメータ.簿価単価  
        l_assetParams.setInputBookValue(l_dblBookPrice);

        //簿価単価入力日時 = 現在時刻(*1) 
        //(*1) 
        //ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG) 
        //にて取得した現在時刻。
        
        //現在時刻
        Timestamp l_tsCurrentTime = GtlUtils.getSystemTimestamp(); 
        //簿価単価入力日時
        l_assetParams.setInputTimestamp(l_tsCurrentTime);

        //更新日付 = 現在時刻 
        l_assetParams.setLastUpdatedTimestamp(l_tsCurrentTime);
        
        //５）プロパティセットした保有資産Paramsを返却する。 
        log.exiting(STR_METHOD_NAME);
        return l_assetParams;
    }

    /**
     * (validate簿価単価)<BR>
     * 外国株式簿価単価確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株簿価単価登録）validate簿価単価」参照<BR>
     * <BR>
     * =============================================== <BR>
     * 　@　@具体位置 : (*)桁数チェック<BR>
     * 　@　@　@　@　@　@　@　@calc概算簿価単価()の戻り値.整数部の桁数 > 8桁の場合<BR>
     * 　@　@　@　@　@　@　@　@「変更後概算簿価単価が不正な値」の例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02110<BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式簿価単価登録確認リクエストオブジェクト<BR>
     * @@return WEB3FeqBookPriceConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3FeqBookPriceConfirmResponse validateBookValuePrice(
        WEB3FeqBookPriceConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBookValuePrice(WEB3FeqBookPriceConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_feqPositionManager =
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();

        //getAsset(arg0 : long)
        //[引数]
        //  arg0 ： リクエストデータ．保有資産ID
        Asset l_asset = null;
        try
        {
            l_asset =
                l_feqPositionManager.getAsset(Long.parseLong(l_request.assetId));
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //validate残高株数(Asset, Double)
        //[引数]
        // 保有資産　@：　@getAsset()の戻り値
        // 残高株数　@：　@リクエストデータ．残高株数
        double l_dblBalanceQuantity = 0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.balanceQuantity))
        {
            l_dblBalanceQuantity = Double.parseDouble(l_request.balanceQuantity);
        }
        this.validateBalanceQuantity(
            l_asset,
            l_dblBalanceQuantity);

        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();

        //calc概算簿価単価(double, double)
        //[引数]
        //　@簿価　@：　@リクエストデータ．計算用入力簿価単価
        //　@数量　@：　@リクエストデータ．残高株数
        BigDecimal l_bdPrice = l_feqBizLogicProvider.calcEstimatedBookValuePrice(
            Double.parseDouble(l_request.aftBookAmount),
            l_dblBalanceQuantity);

        //calc概算簿価単価()の戻り値.整数部の桁数 > 8桁の場合
        //「変更後概算簿価単価が不正な値」の例外をスローする。
        String l_strPrice = l_bdPrice.toString();
        if (l_strPrice.split("\\.")[0].length() > 8)
        {
            log.debug("変更後概算簿価単価が不正な値になっています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02110,
                this.getClass().getName() + STR_METHOD_NAME,
                "変更後概算簿価単価が不正な値になっています。");
        }

        //createResponse( )
        WEB3FeqBookPriceConfirmResponse l_response =
            (WEB3FeqBookPriceConfirmResponse)l_request.createResponse();

        //プロパティセット
        //変更後概算簿価単価　@＝　@calc概算簿価単価()の戻り値．小数点第6位を四捨五入
        BigDecimal l_bdAftBookPrice = l_bdPrice.setScale(5, BigDecimal.ROUND_HALF_UP);
        l_response.aftBookPrice =
            WEB3StringTypeUtility.formatNumber(l_bdAftBookPrice.doubleValue());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate残高株数)<BR>
     * 引数．残高株数が、<BR>
     * 引数．保有資産の残高株数（数量＋売付不能数量）と同じかチェックする。<BR>
     * <BR>
     * １）　@保有資産の残高株数の算出<BR>
     * 　@　@引数．保有資産．数量　@＋　@引数．保有資産．売付不能数量　@を計算する。<BR>
     * <BR>
     * ２）　@残高株数のチェック<BR>
     * 　@１）で算出した値　@と、引数．残高株数を比較し、<BR>
     * 　@値が異る場合は「データ不整合エラー（残高株数データ不整合）」をスローする。<BR>
     * 　@　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag   : BUSINESS_ERROR_02982<BR>
     * <BR>
     * @@param l_asset - (保有資産)<BR>
     * 保有資産<BR>
     * @@param l_dblBalanceQuantity - (残高株数)<BR>
     * 残高株数<BR>
     * @@throws WEB3BaseException
     */
    protected void validateBalanceQuantity(
        Asset l_asset,
        double l_dblBalanceQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBalanceQuantity(Asset, double)";
        log.entering(STR_METHOD_NAME);

        if (l_asset == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();

        //保有資産の残高株数の算出
        //  引数．保有資産．数量　@＋　@引数．保有資産．売付不能数量　@を計算する。
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_assetRow.getQuantity()));
        BigDecimal l_bdQuantityCannotSell =
            new BigDecimal(String.valueOf(l_assetRow.getQuantityCannotSell()));

        double l_dblAssetBalanceQuantity =
            (l_bdQuantity.add(l_bdQuantityCannotSell)).doubleValue();

        //算出した値　@と、引数．残高株数を比較し、
        //　@値が異る場合は「データ不整合エラー（残高株数データ不整合）」をスローする。
        if (l_dblAssetBalanceQuantity != l_dblBalanceQuantity)
        {
            log.debug("データ不整合エラー（残高株数データ不整合）");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02982,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー（残高株数データ不整合）");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
