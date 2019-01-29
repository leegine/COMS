head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託乗換入力サービス実装クラス(WEB3MutualSwitchingInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
Revesion History : 2004/08/24 韋念瓊 (中訊) レビュー  
Revesion History : 2004/12/11 于美麗 (中訊) 残対応  
Revesion History : 2005/10/18 黄建 (中訊) フィデリティ対応
Revesion History : 2007/04/06 唐性峰 (中訊) 実装005
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.message.WEB3MutualSwitchingInputRequest;
import webbroker3.mf.message.WEB3MutualSwitchingInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualSwitchingInputService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

/**
 * (投信乗換入力サービスImpl)<BR>
 * 投資信託乗換入力サービス実装クラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwitchingInputServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualSwitchingInputService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingInputServiceImpl.class);

    /**
     * 投資信託乗換入力サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投信)乗換入力」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)乗換入力」: <BR>
     *         13((取扱可能銘柄チェック、<BR>
     *         isシステム取扱()が false を返す場合は例外をスローする。<BR>
     *         例外をスローする。<BR>
     * 　@　@   （取扱不可銘柄エラー）<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_00362<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)乗換入力」: <BR>
     *         14((取引可能銘柄チェック、<BR>
     *         is解約乗換可能()が false を返す場合は例外をスローする。<BR>
     *         例外をスローする。<BR>
     * 　@　@   （取引不可銘柄エラー）<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_00363<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)乗換入力」: <BR>
     *        １２）乗換可能残高口数を取得する
     *         解約可能残高口数==0 の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00390<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B17441037A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 投信乗換入力リクエストの取得
        WEB3MutualSwitchingInputRequest l_swtInputRequest = null;
        if (l_request instanceof WEB3MutualSwitchingInputRequest)
        {
            l_swtInputRequest = (WEB3MutualSwitchingInputRequest) l_request;
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[入力値が不正です]__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1　@入力内容チェック
        // 　@投信乗換入力リクエスト.validate()をコールする
        l_swtInputRequest.validate();

        // 1.2　@補助口座取得
        SubAccount l_subAccount = this.getSubAccount();
        //拡張投信ポジションマネージャを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundPositionManager l_web3MfPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        //1.3　@保有資産を取得する
        MutualFundAsset l_mfAsset = null;
        try
        {
            l_mfAsset =
                (MutualFundAsset)l_web3MfPositionMgr.getAsset(
                    Long.parseLong(l_swtInputRequest.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00393,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.4　@拡張投信銘柄取得
        // 　@－拡張投信銘柄マネージャを取得する
        WEB3MutualFundProductManager l_productManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_mfProduct = null;
        try
        {
            //－拡張投信銘柄マネージャ.getProduct()をコールし、銘柄オブジェクトを取得する            
            Product l_product = 
                l_productManager.getProduct(
                    l_mfAsset.getProduct().getProductId());
            
            //1.5 to銘柄(Row)
            MutualFundProductRow l_mfProductrow = (MutualFundProductRow) l_product.getDataSourceObject();
            l_mfProduct = (WEB3MutualFundProduct) l_productManager.toProduct(l_mfProductrow);
            log.debug("拡張投信銘柄.getProductId = " + l_mfProduct.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when l_productManager.getProduct("
                    + "productId = "
                    + l_mfAsset.getProduct().getProductId());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // 1.6 getProduct(arg0 : long)
        //乗換先の銘柄オブジェクトを取得する。 
        //[引数] 
        //arg0： リクエスト.乗換先銘柄ID 

        WEB3MutualFundProduct l_mfSwtProduct = null;
        try
        {
            //－拡張投信銘柄マネージャ.getProduct()をコールし、銘柄オブジェクトを取得する
            Product l_product = 
                l_productManager.getProduct(Long.parseLong(l_swtInputRequest.switchingProductId));
            
            // 1.7 to銘柄(Row)
            MutualFundProductRow l_mfSwtProductRow = (MutualFundProductRow) l_product.getDataSourceObject();
            l_mfSwtProduct = (WEB3MutualFundProduct) l_productManager.toProduct(l_mfSwtProductRow);
            log.debug("乗換先の銘柄.getProductId = " + l_mfSwtProduct.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when l_productManager.getProduct("
                    + "productId = "
                    + l_swtInputRequest.switchingProductId);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // 1.8 FinAppクラスのgetCommonOrderValidator()をコールし
        //注文チェックオブジェクトを取得する      
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        // 1.9 受付時間チェック、システム取引停止チェック 
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

		//1.10 reset銘柄コード()
		//［引数］
		//銘柄コード： 取得した拡張投信銘柄.getProductCode()の戻り値
		WEB3MutualFundTradingTimeManagement.resetProductCode(l_mfProduct.getProductCode());

		//1.11 setTimestamp()
		WEB3MutualFundTradingTimeManagement.setTimestamp();
		
		//1.12 get乗換発注日
		// 乗換注文の発注日を取得する。
        // [引数]
        //乗換元銘柄コード： 取得した乗換元銘柄の銘柄コード
        //乗換先銘柄コード： 取得した乗換先銘柄の銘柄コード
		Timestamp l_datBizDate = 
			new Timestamp(WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(
                l_mfProduct.getProductCode(),l_mfSwtProduct.getProductCode()).getTime());
		WEB3GentradeMainAccount l_genMainAccount = 
			(WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        
        //1.13 validate取引可能顧客(顧客, 発注日)           
        // 　@－注文チェック.validate取引可能顧客()をコールする
		OrderValidationResult l_validationResult =  
			l_orderValidator.validateAccountForTrading(
				l_genMainAccount,
				l_datBizDate);
        
		if (l_validationResult.getProcessingResult().isFailedResult())
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
		
		// 1.14 validate乗換可能銘柄
		//[引数] 
		//補助口座
		//乗換元銘柄： 乗換元の投信銘柄オブジェクト 
		//乗換先銘柄： 乗換先の投信銘柄オブジェクト 
		//発注日： get投信発注日()の戻り値
		WEB3MutualFundOrderManager l_mfOrderManager = 
		    (WEB3MutualFundOrderManager)l_finApp.getTradingModule(
		        ProductTypeEnum.MUTUAL_FUND).getOrderManager();
		l_mfOrderManager.validateSwitchingPossProduct(
		    l_subAccount, 
		    l_mfProduct, 
		    l_mfSwtProduct, 
		    l_datBizDate);

        // 　@－投信発注審査個別チェック.validate緊急停止()をコールする
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();

        // 1.15 解約可能残高口数を取得する
        // 　@拡張投信ポジションマネージャ.calc解約可能残高口数()をコール
        double l_dblSellPossiblePositionQty = 0;
        l_dblSellPossiblePositionQty =
            l_web3MfPositionMgr.calcSellPossiblePositionQty(
                l_subAccount,
                l_mfProduct,
                l_mfAsset.getAssetId() + "");
        
        //1.16 解約可能残高口数==0 の場合、例外をスローする。        
        if(l_dblSellPossiblePositionQty == 0)
        {
            log.error("解約可能残高口数がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00390,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );  
        }
        
        //電子鳩チェックフラグ==trueの場合実施
        // validate目論見書閲覧
        WEB3GentradeProspectusResult l_validateBataResult = null;
        if(l_swtInputRequest.batoCheckFlag)
        {
            // 1.17 validate目論見書閲覧(String, String)
			//[引数] 
			// 種別コード： リクエスト.種別コード 
			// 銘柄コード： 乗換先の投信銘柄.銘柄コード
            WEB3GentradeBatoClientService l_bataService =
                (WEB3GentradeBatoClientService)Services.getService(
                    WEB3GentradeBatoClientService.class);                
            l_validateBataResult = 
                l_bataService.validateProspectus(
                    l_swtInputRequest.typeCode, 
                    l_mfSwtProduct.getProductCode());
        }          
        
        // 1.18 get乗換約定日
		//［引数］ 
		//証券会社： 補助口座.getInstitution()の戻り値 
		//乗換元銘柄コード： 乗換元投信銘柄.銘柄コード 
		//乗換先銘柄コード： 乗換先投信銘柄.銘柄コード 
        Date l_datSwtExecutedDate = 
            l_productManager.getSwtExecutedDate(
                l_subAccount.getInstitution(), 
                l_mfProduct.getProductCode(),
                l_mfSwtProduct.getProductCode());   
        
        // 1.19 get乗換受渡日
		//［引数］ 
		//証券会社： 補助口座.getInstitution()の戻り値 
		//乗換元銘柄コード： 乗換元投信銘柄.銘柄コード 
		//乗換先銘柄コード： 乗換先投信銘柄.銘柄コード 
        Date l_datSwtDeliveryDate = 
            l_productManager.getSwtDeliveryDate(
                l_subAccount.getInstitution(), 
                l_mfProduct.getProductCode(),
                l_mfSwtProduct.getProductCode());

        // 1.20 投信乗換入力レスポンスオブジェクトを生成し、リターンする
        WEB3MutualSwitchingInputResponse l_response =
            (WEB3MutualSwitchingInputResponse) l_swtInputRequest.createResponse();

        // 1.21 投信乗換入力レスポンスオブジェクトには、以下の値を設定する
        // 　@　@乗換可能口数： 取得した乗換可能残高口数
        l_response.switchingAbleQty = WEB3StringTypeUtility.formatNumber(l_dblSellPossiblePositionQty);

        // 　@　@評価額：拡張投信ポジションマネージャ.calc評価額()の戻り値
        l_response.marketValue = WEB3StringTypeUtility.formatNumber(
            l_web3MfPositionMgr.calcMarketValue(l_subAccount, l_mfProduct, l_mfAsset.getAssetId() + ""));

        // 　@　@銘柄コード： 取得した拡張投信銘柄.getProductCode()の戻り値
        l_response.mutualProductCode = l_mfProduct.getProductCode();

        // 　@　@銘柄名： 取得した拡張投信銘柄.get銘柄名()の戻り値
        l_response.mutualProductName = l_mfProduct.getMutualProductName();

        //　@　@解約価額通貨コード：取得した拡張投信銘柄.get通貨コード()の戻り値
        l_response.sellValueCurrencyCode = l_mfProduct.getCurrencyCode();

        // 　@　@解約価額： 取得した拡張投信銘柄.get解約価額()の戻り値
        l_response.sellValue = WEB3StringTypeUtility.formatNumber(l_mfProduct.getSellValue());

        // 　@　@基準価額適用日： 取得した拡張投信銘柄.get基準価額適用日()の戻り値
        l_response.constantValueAppDate = WEB3DateUtility.toDay(
                    l_mfProduct.getConstantValueAppDate());

        // 　@　@解約口座区分：
        TaxTypeEnum l_taxType = l_mfAsset.getTaxType();
        if (TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            l_response.taxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else
        {
            if (TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType)
                || TaxTypeEnum.SPECIAL.equals(l_taxType))
            {
                l_response.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            else
            {
                log.debug("税区分がその他である");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00393,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "税区分がその他である");
            }
        }

        // 　@　@請求方法@一覧：
        String[] l_lisSellBuyDivList = null;
        //－取得した拡張投信銘柄.is外国投信（）の戻り値==true OR 取得した拡張投信銘柄.isFWF（）の戻り値==trueの場合、 
		//”１：買取”を設定する。
		if (l_mfProduct.isForeignFund() || l_mfProduct.isFWF())
		{
			l_lisSellBuyDivList = new String[1];
			l_lisSellBuyDivList[0] = WEB3ClaimDivDef.BUY;
		}
        //－上記以外の場合は、取得した投信発注審査個別チェック.is買取請求可能()をコールする
        else if (l_validationsCheck.isBuyingRequestPossible(
            l_datBizDate,
            l_subAccount,
            l_mfProduct))
        {
            l_lisSellBuyDivList = new String[2];
            l_lisSellBuyDivList[0] = WEB3ClaimDivDef.SELL;
            l_lisSellBuyDivList[1] = WEB3ClaimDivDef.BUY;
        }
        else
        {
            l_lisSellBuyDivList = new String[1];
            l_lisSellBuyDivList[0] = WEB3ClaimDivDef.SELL;           
        }
        l_response.sellBuyDivList = l_lisSellBuyDivList;
        
        // 指定方法@一覧：
        String[] l_strArraySwtDiv = null;
        String l_strSwtDiv = l_mfProduct.getSwitchingSelectable();
        if (WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(l_strSwtDiv))
        {
            l_strArraySwtDiv = new String[3];
            l_strArraySwtDiv[0] = WEB3SellDivDef.ALL_DESIGNATE;
            l_strArraySwtDiv[1] = WEB3SellDivDef.COUNT_DESIGNATE;
            l_strArraySwtDiv[2] = WEB3SellDivDef.MONEY_DESIGNATE;
        }
        else
        {
            if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strSwtDiv))
            {
                l_strArraySwtDiv = new String[2];
                l_strArraySwtDiv[0] = WEB3SellDivDef.ALL_DESIGNATE;
                l_strArraySwtDiv[1] = WEB3SellDivDef.MONEY_DESIGNATE;                
            }
            else
            {
                if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strSwtDiv))
                {
                    l_strArraySwtDiv = new String[2];
                    l_strArraySwtDiv[0] = WEB3SellDivDef.ALL_DESIGNATE;
                    l_strArraySwtDiv[1] = WEB3SellDivDef.COUNT_DESIGNATE;
                }
                else
                {
                    log.debug("指定方法@不正");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "予期しないシステムエラーが発生しました。");
                }
            }
        }
        l_response.specifyDivList = l_strArraySwtDiv;

        // 　@　@乗換時単位口数：
        // 　@　@　@(*) 取得した拡張投信銘柄.get単位口数（乗換）()の戻り値を設定する
        l_response.switchingUnitQty = l_mfProduct.getSwitchingUnitQty() + "";

        // 　@　@乗換時最低口数：
        // 　@　@　@(*) 取得した拡張投信銘柄.get最低口数（乗換）()の戻り値を設定する
        l_response.switchingMinQty = l_mfProduct.getSwitchingMinQty() + "";

        // 　@　@乗換時単位金額：
        // 　@　@　@(*) 取得した拡張投信銘柄.get単位金額（乗換）()の戻り値を設定する
        l_response.switchingUnitAmt = l_mfProduct.getSwitchingUnitAmt() + "";

        // 　@　@乗換時最低金額：
        // 　@　@　@(*) 取得した拡張投信銘柄.get最低金額（乗換）()の戻り値を設定する
        l_response.switchingMinAmt = l_mfProduct.getSwitchingMinAmt() + "";

        // 　@　@発注日： 取得した発注日
        l_response.orderBizDate = WEB3DateUtility.toDay(l_datBizDate);

        // 　@　@約定日： 取得した約定日
        l_response.executionTimestamp = WEB3DateUtility.toDay(l_datSwtExecutedDate);

        // 　@　@受渡日： 取得した受渡日
        l_response.deliveryDate = WEB3DateUtility.toDay(l_datSwtDeliveryDate);
        
		//銘柄コード（乗換先）： 乗換先の投信銘柄.getProductCode()の戻り値 
        l_response.switchingProductCode = l_mfSwtProduct.getProductCode();
        
		//銘柄名（乗換先）： 乗換先の投信銘柄.get銘柄名()の戻り値 
        l_response.switchingProductName = l_mfSwtProduct.getMutualProductName();
        
        //買付基準価額通貨コード： 乗換先の投信銘柄.get通貨コード()の戻り値 
        l_response.buyConstantValueCurrencyCode = l_mfSwtProduct.getCurrencyCode();
        
		//買付基準価額： 乗換先の投信銘柄.get買付基準価額()の戻り値 
        l_response.buyConstantValue = 
            WEB3StringTypeUtility.formatNumber(l_mfSwtProduct.getConstantValue());
        
        //買付基準価額適用日： 乗換先の投信銘柄.get基準価額適用日()の戻り値         
        l_response.buyConstantValueAppDate = WEB3DateUtility.toDay(
            l_mfSwtProduct.getConstantValueAppDate());
		
        //買付口座区分一覧：  
		//　@－顧客.is特定口座開設()==true and 乗換先投信銘柄.is株式型()==trueの場合 
		//　@　@ ”一般”と”特定”を設定する。 
		//　@－それ以外の場合 
		//　@　@ ”一般”を設定する。 
        boolean l_blnIsSpecialAccountEstablished = 
            l_genMainAccount.isSpecialAccountEstablished(
                l_datSwtDeliveryDate,
                l_subAccount);
        boolean l_blnStockType = l_mfSwtProduct.isStockType();
        

        if(l_blnIsSpecialAccountEstablished && l_blnStockType)
        {
            String[] l_strAccountDivDef = new String[2];
            l_strAccountDivDef[0] = WEB3TaxTypeSpecialDef.NORMAL;
            l_strAccountDivDef[1] = WEB3TaxTypeSpecialDef.SPECIAL;
            l_response.taxTypeList = l_strAccountDivDef;
        }
        else
        {
            String[] l_strAccountDivDef = new String[1];
            l_strAccountDivDef[0] = WEB3TaxTypeSpecialDef.NORMAL;
            l_response.taxTypeList = l_strAccountDivDef;
        }

		//目論見書閲覧チェック結果： validate目論見書閲覧()の戻り値 
        l_response.prospectusResult = l_validateBataResult;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
