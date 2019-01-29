head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約入力サービス実装クラス(WEB3MutualSellInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 韋念瓊 (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2004/12/11 于美麗 (中訊) 残対応
Revesion History : 2006/05/15 肖志偉(中訊) 仕様変更（モデル) :411,415
Revesion History : 2006/10/16 周捷 仕様変更・モデル500、509
Revesion History : 2006/10/25  張騰宇 (中訊) モデル 510
Revesion History : 2007/02/05 丁昭奎 (中訊) モデル No.527
Revesion History : 2007/03/26 趙林鵬 (中訊) モデル No.551
Revesion History : 2007/04/06 唐性峰 (中訊) 実装005, モデル558
Revesion History : 2008/05/07 松本 (SRA) 暫定対応
Revesion History : 2008/05/12 武波 (中訊) 仕様変更 No602
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSettlementDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3DeliveryMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualSellInputRequest;
import webbroker3.mf.message.WEB3MutualSellInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualSellInputService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託解約入力サービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualSellInputServiceImpl extends WEB3MutualClientRequestService implements WEB3MutualSellInputService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellInputServiceImpl.class);
        
    /**
     * 投資信託解約入力サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投信)解約入力」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)解約入力」: <BR>
     *        12((取扱可能銘柄チェック、<BR>
     *        取得した拡張投信銘柄.isシステム取扱()をコールする。<BR>
     *         isシステム取扱()が false を返す場合は例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00362<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)解約入力」: <BR>
     *        14((取引可能銘柄チェック、<BR>
     *        is解約乗換可能()が false を返す場合は例外をスローする。<BR>
     *         （取引不可銘柄エラー<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00363<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)解約入力」: <BR>
     *        解約可能残高口数==0 の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00390<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B16F85034B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualSellInputRequest l_mfSellInputRequset = null;
        if (l_request instanceof WEB3MutualSellInputRequest)
        {
            //リクエストデータの具象データ型が「投信解約確認リクエスト」の場合
            l_mfSellInputRequset = (WEB3MutualSellInputRequest) l_request;
        }
        else
        {
            log.debug(STR_METHOD_NAME + "Error[入力値が不正です]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
        //１）入力内容チェック 
        l_mfSellInputRequset.validate();

        //２）補助口座取得
        SubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //拡張投信ポジションマネージャを取得する
        WEB3MutualFundPositionManager l_web3MfPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        //３）　@保有資産を取得する
        MutualFundAsset l_mfAsset = null;
        try
        {
            l_mfAsset =
                (MutualFundAsset)l_web3MfPositionMgr.getAsset(
                    Long.parseLong(l_mfSellInputRequset.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //拡張投信銘柄マネージャを取得する
        WEB3MutualFundProductManager l_web3MfProductMgr =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        WEB3MutualFundProduct l_web3MfProduct = null;
        WEB3MutualSellInputResponse l_response = null;        

        try
        {
            // ４）　@拡張投信銘柄取得
            //拡張投信銘柄マネージャ.getProduct()をコールし、銘柄オブジェクトを取得する
            l_web3MfProduct = (WEB3MutualFundProduct)
                l_web3MfProductMgr.getProduct(l_mfAsset.getProduct().getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                
        //取得できない場合は例外をスローする
        if (l_web3MfProduct == null)
        {
            log.debug("拡張投信銘柄取得できない場合エラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "拡張投信銘柄取得できない場合エラー");
        }                       

        //FinAppクラスのgetCommonOrderValidator()をコールし
        //注文チェックオブジェクトを取得する      
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //５）　@受付時間チェック、システム取引停止チェック  
        //投信取引時間管理.validete注文受付可能()をコールする
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //６）　@取引時間の再設定
        //投信取引時間管理.reset銘柄コード()をコール
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_web3MfProduct.getProductCode());
        //受付日時、日付ロールをセットする
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
		//７）　@顧客別取引停止属性チェック   
        //[get投信発注日の引数] 
        // 注文種別 ： OrderTypeEnum.投資信託売注文 
        // 一括区分 ： 取得した拡張投信銘柄．is特定日取引銘柄()の戻り値 
        // [is特定日取引銘柄の引数] 
        // 注文種別 ： OrderTypeEnum．投資信託売注文
        //注文チェック.validate取引可能顧客()をコールする。
		Timestamp l_datBizDateValidate = 
			new Timestamp(
                WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(
                    OrderTypeEnum.MF_SELL, 
                    l_web3MfProduct.isUnitTypeProduct(OrderTypeEnum.MF_SELL)).getTime());

        //特定日取引銘柄の場合の発注日取得 
        long l_lngUnitProductId = l_web3MfProduct.getProductId();
        if (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L)
        {
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow) l_web3MfProduct.getDataSourceObject();
        	l_datBizDateValidate = l_mfProductRow.getSellSwtEndDate();
        }
		
		WEB3GentradeMainAccount l_genMainAccount = 
			(WEB3GentradeMainAccount) l_subAccount.getMainAccount();
		OrderValidationResult l_validationResult =  
			l_orderValidator.validateAccountForTrading(
				l_genMainAccount,
				l_datBizDateValidate);
            
		if (l_validationResult.getProcessingResult().isFailedResult())
		{
            log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
        
        //８）　@取扱可能銘柄チェック
        //引数.拡張投信銘柄.isシステム取扱()をコールする
        if (!l_web3MfProduct.isSystemHandling())
        {
            log.debug(" __取扱不可銘柄エラー__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取扱不可銘柄エラー");
        }
        
        //９）取引可能銘柄チェック
        //取得した拡張投信銘柄.is解約乗換可能()をコールする
        //９－１）is解約乗換可能()が false を返す場合は例外をスローする。
        Date l_datArgIsSellSwitchingPossible = null;
        if (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L)
        {
        	l_datArgIsSellSwitchingPossible = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        else
        {
        	l_datArgIsSellSwitchingPossible = l_datBizDateValidate;
        }
        if (!l_web3MfProduct.isSellSwitchingPossible(l_datArgIsSellSwitchingPossible))
		{
			log.debug(" __取引不可銘柄エラー__");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00363,
				this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引不可銘柄エラー");
		}
		
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        
		//取得した拡張投信銘柄.is国内投信()の戻り値 == true 
        // AND 投信解約入力リクエスト.請求方法@＝"買取"の場合
		if (l_web3MfProduct.isDomesticFund() && WEB3ClaimDivDef.BUY.equals(l_mfSellInputRequset.sellBuyDiv))
		{			
			//is買取請求可能()が false を返す場合は例外をスローする
			if (!l_validationsCheck.isBuyingRequestPossible(
                l_datBizDateValidate,
			    l_subAccount,
			    l_web3MfProduct))
			{
				log.debug(" __取引不可銘柄エラー__");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00363,
					this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引不可銘柄エラー");
			}
		}
		
        try
        {
            //１０）緊急停止チェック 
            //投信発注審査個別チェック.validate緊急停止()をコールする
            l_validationsCheck.validateEmergencyStop(
                    l_web3MfProduct, WEB3ProcessDivDef.SELL);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("緊急停止エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //１１）取引停止時間チェック
        //投信取引時間管理.validete注文受付可能()をコールする
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        //validate外貨MMF二重注文
        l_validationsCheck.validateFrgnMmfDoubleOrder(
                l_subAccount,
                l_web3MfProduct,
                l_datBizDateValidate);
        //１２）解約可能残高口数を取得する  
        double l_dblSellPossiblePositionQty = 0;  
        l_dblSellPossiblePositionQty = 
            l_web3MfPositionMgr.calcSellPossiblePositionQty(
                l_subAccount, l_web3MfProduct, l_mfAsset.getAssetId() + "");
        //U00824対応
        //start
        if(l_dblSellPossiblePositionQty == 0)
        {
			log.error("解約可能残高口数がありません。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00390,
				this.getClass().getName() + "." + STR_METHOD_NAME
				);	
        }
        //end
                
        //１３）約定日取得 
        Date l_datExecutedDate = null;
        //拡張投信銘柄マネージャ.get約定日()をコールし、約定日を取得する
        l_datExecutedDate = l_web3MfProductMgr.getExecutedDate(
            l_subAccount.getInstitution(),
            l_web3MfProduct.getProductCode(),
            l_datBizDateValidate);

        // 特定日取引銘柄の場合の約定日取得【暫定対応】
        long l_lngProductId = l_web3MfProduct.getProductId();
        if (l_lngProductId == 3303910181800L || l_lngProductId == 3303911181800L)
        {
            TradingCalendar l_tradingCalendar = null;
            if (l_lngProductId == 3303910181800L)
            {
                l_tradingCalendar = GtlUtils.getFinObjectManager().getTradingCalendar(
                    330003910181800L);
            }
            else
            {
                l_tradingCalendar = GtlUtils.getFinObjectManager().getTradingCalendar(
                    330003911181800L);
            }
        	l_datExecutedDate = l_tradingCalendar.roll(l_datBizDateValidate, 2);
        }
        
        //１４）受渡日取得
        Date l_datDeliveryDate = null;
        //拡張投信銘柄マネージャ.get受渡日()をコールし、受渡日を取得する
        l_datDeliveryDate = l_web3MfProductMgr.getDeliveryDate(
            l_subAccount.getInstitution(),
            l_web3MfProduct.getProductCode(),
            false,
            l_datExecutedDate);
            
        //１５）投信解約入力レスポンスオブジェクトを生成し、リターンする。 
        //投信解約入力レスポンスオブジェクトには、以下の値を設定する。
        l_response = (WEB3MutualSellInputResponse) l_request.createResponse();
        
        //[投信解約入力レスポンスに設定する値] 
        //解約可能口数：
        l_response.sellAbleQty = 
            WEB3StringTypeUtility.formatNumber(l_dblSellPossiblePositionQty);
        //評価額：
        l_response.marketValue = 
            WEB3StringTypeUtility.formatNumber(
                l_web3MfPositionMgr.calcMarketValue(l_subAccount,l_web3MfProduct, l_mfAsset.getAssetId() + ""));
        //銘柄コード：
        l_response.mutualProductCode = l_web3MfProduct.getProductCode();
        log.debug("銘柄コード = " + l_web3MfProduct.getProductCode());
            
        //銘柄名：
        l_response.mutualProductName = l_web3MfProduct.getMutualProductName();
        
        //口座区分：
        String l_strTaxType = null;
        //取得した保有残高オブジェクト.getTaxType()の値がTaxTypeEnum.一般の場合
        if (TaxTypeEnum.NORMAL.equals(l_mfAsset.getTaxType()))
        {
            l_strTaxType = WEB3MFAccountDivDef.NORMAL;
        }            
        //取得した保有残高オブジェクト.getTaxType()の値がTaxTypeEnum.特定または 
        //     TaxTypeEnum.特定口座かつ源泉徴収の場合
        else if (TaxTypeEnum.SPECIAL.equals(l_mfAsset.getTaxType()) ||
            TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mfAsset.getTaxType()))
        {
            l_strTaxType = WEB3MFAccountDivDef.SPECIAL;
        }
        //取得した保有残高オブジェクト.getTaxType()の値がTaxTypeEnum.その他の場合
        else if (TaxTypeEnum.UNDEFINED.equals(l_mfAsset.getTaxType()))
        {
            l_strTaxType = WEB3MFAccountDivDef.OTHER;
        }            
        log.debug("口座区分 = " + l_strTaxType);
        l_response.taxType = l_strTaxType;
        
        //解約価額通貨コード：
        l_response.sellValueCurrencyCode = l_web3MfProduct.getCurrencyCode();
        
        //解約価額：
        if (!((MutualFundProductRow)l_web3MfProduct.getDataSourceObject()).getSellConstantValueIsNull())
        {
            l_response.sellValue = 
                WEB3StringTypeUtility.formatNumber(l_web3MfProduct.getSellValue());
        }
        
        //基準価額適用日：
        l_response.constantValueAppDate = 
            l_web3MfProduct.getConstantValueAppDate();
        
        //請求方法@一覧：
        //－取得した拡張投信銘柄.is外貨MMF（）の戻り値==false の場合、 
        if (!l_web3MfProduct.isFrgnMmf())
        {
            //(*) 投信解約入力リクエスト.請求方法@がnullでない場合
            log.debug("請求方法@ = " + l_mfSellInputRequset.sellBuyDiv);
            if (l_mfSellInputRequset.sellBuyDiv != null)
            {
                String[] l_strSellBuyDivList = {l_mfSellInputRequset.sellBuyDiv};
                l_response.sellBuyDivList = l_strSellBuyDivList;
            }
            //(*) 投信解約入力リクエスト.請求方法@がnullの場合
            else
            {
                //取得した拡張投信銘柄.is外国投信（）の戻り値==true
                // OR 取得した.拡張投信銘柄.isFWF（）の戻り値==trueの場合、
                //”１：買取”を設定。
                boolean l_blnIsForeignFund = l_web3MfProduct.isForeignFund();
                boolean l_blnIsFWF = l_web3MfProduct.isFWF();
                if (l_blnIsForeignFund || l_blnIsFWF)
                {
                    String[] l_strSpecifyDivList = {WEB3ClaimDivDef.BUY};
                    l_response.sellBuyDivList = l_strSpecifyDivList; 
                }
                else
                {
                    //取得した拡張投信銘柄.is買取請求可能()をコールする
                    //is買取請求可能()が true を返す場合は”0：解約”と”1：買取”
                    boolean l_blnBuyPossible = false; 
                    l_blnBuyPossible = 
                        l_validationsCheck.isBuyingRequestPossible(
                            l_datBizDateValidate,
                            l_subAccount,
                            l_web3MfProduct);
                
                    log.debug("is買取請求可能()" + l_blnBuyPossible);
                    if (l_blnBuyPossible)
                    {
                        String[] l_strSellBuyDiv = 
                            {
                                WEB3ClaimDivDef.SELL,
                                WEB3ClaimDivDef.BUY
                            };
                        l_response.sellBuyDivList = l_strSellBuyDiv;                
                    }
                    //is買取請求可能()が false を返す場合は”0：解約”を設定する
                    else
                    {
                        String[] l_strSpecifyDivList = {WEB3ClaimDivDef.SELL};
                        l_response.sellBuyDivList = l_strSpecifyDivList; 
                    }
                }
            }
        }
        else
        {
            l_response.sellBuyDivList = null;
        }
        //指定方法@一覧：
        //(*) 取得した拡張投信銘柄.get指定方法@（解約）()の戻り値が”0：選択指定” 
        if (WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(l_web3MfProduct.getSellSelectable()))
        {
            String[] l_strSpecifyDivList = 
                {
                    WEB3SellDivDef.ALL_DESIGNATE,
                    WEB3SellDivDef.MONEY_DESIGNATE,
                    WEB3SellDivDef.COUNT_DESIGNATE
                };
            l_response.specifyDivList = l_strSpecifyDivList;           
        }
        else if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_web3MfProduct.getSellSelectable()))
        {
            String[] l_strSpecifyDivList = 
                {
                    WEB3SellDivDef.ALL_DESIGNATE,
                    WEB3SellDivDef.MONEY_DESIGNATE,                    
                };
            l_response.specifyDivList = l_strSpecifyDivList;           
        }
        else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_web3MfProduct.getSellSelectable()))
        {
            String[] l_strSpecifyDivList = 
                {
                    WEB3SellDivDef.ALL_DESIGNATE,
                    WEB3SellDivDef.COUNT_DESIGNATE,
                };
            l_response.specifyDivList = l_strSpecifyDivList;
        }
        
        //解約時単位口数：
        //(*) 取得した拡張投信銘柄.get単位口数（解約）()の戻り値を設定する
        l_response.sellUnitQty = l_web3MfProduct.getSellUnitQty() + "";
        
        //解約時最低口数：
        //(*) 取得した拡張投信銘柄.get最低口数（解約）()の戻り値を設定する
        l_response.sellMinQty = l_web3MfProduct.getSellMinQty() + "";
        
        //解約時単位金額：
        //(*) 取得した拡張投信銘柄.get単位金額（解約）()の戻り値を設定する
        l_response.sellUnitAmt = l_web3MfProduct.getSellUnitAmt() + "";
        
        //解約時最低金額：
        //(*) 取得した拡張投信銘柄.get最低金額（解約）()の戻り値を設定する
        l_response.sellMinAmt = l_web3MfProduct.getSellMinAmt() + "";
        
        //決済方法@一覧：
        //(*) 取得した拡張投信銘柄.get決済（解約）()の戻り値が”0：選択指定”の場合
        if (WEB3BuySellSettlementDivDef.SELECT_DESIGNATE.equals(
            l_web3MfProduct.getSellSettlement()))
        {
            String[] l_strSettleDivList = 
                {
                    WEB3SettlementDivDef.JAPANESE_CURRENCY,
                    WEB3SettlementDivDef.FOREIGN_CURRENCY,
                };
            l_response.settleDivList = l_strSettleDivList;           
        }
        else if (WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY.equals(
            l_web3MfProduct.getSellSettlement()))
        {
            String[] l_strSettleDivList = {WEB3SettlementDivDef.JAPANESE_CURRENCY};
            l_response.settleDivList = l_strSettleDivList; 
        }
        else if (WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY.equals(
            l_web3MfProduct.getSellSettlement()))
        {
            String[] l_strSettleDivList = {WEB3SettlementDivDef.FOREIGN_CURRENCY};
            l_response.settleDivList = l_strSettleDivList;
        }
        
        //受渡方法@一覧：
        //(*) 取得した拡張投信銘柄.get受渡方法@()の戻り値が”0：選択指定”の場合
        if (WEB3DeliveryMethodDef.SELECT_DESIGNATE.equals(
            l_web3MfProduct.getDeliveryDiv()))
        {
            String[] l_strDeliveryDivList = 
                {
                    WEB3DeliveryMethodDef.BANK_TRANSFER,
                    WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL,
                };
            l_response.deliveryDivList = l_strDeliveryDivList;
        }
        else if (WEB3DeliveryMethodDef.BANK_TRANSFER.equals(
            l_web3MfProduct.getDeliveryDiv()))
        {
            String[] l_strDeliveryDivList = {WEB3DeliveryMethodDef.BANK_TRANSFER};
            l_response.deliveryDivList = l_strDeliveryDivList;
        }
        else if (WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL.equals(
            l_web3MfProduct.getDeliveryDiv()))
        {
            String[] l_strDeliveryDivList = 
                {WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL};
            l_response.deliveryDivList = l_strDeliveryDivList;            
        }
        //発注日： 取得した発注日
        l_response.orderBizDate = l_datBizDateValidate;            
        //約定日： 取得した約定日 
        l_response.executionTimestamp = l_datExecutedDate;            
        //受渡日： 取得した受渡日 
        l_response.deliveryDate = l_datDeliveryDate;

        //・円転基準価額 
        //　@　@1）投信銘柄.get通貨コード( )がT0　@または  
        //　@　@　@　@拡張投信銘柄.is外貨MMF = true　@の場合
        //　@　@　@　@nullを設定する。
        //　@　@2）投信銘柄.get通貨コード( )がT0でない場合 
        //　@　@　@　@拡張投信銘柄.get円転基準価額(WEB3MFProcessDivDef.解約)をセットする。 
        //・参考レート
        //　@　@1）投信銘柄.get通貨コード( )がT0の場合
        //　@　@　@ nullをセットする。
        //　@　@2）投信銘柄.get通貨コード( )がT0でない場合
        //       2-1) 投信銘柄.is外貨MMF ＝ trueの場合
        //              拡張投信銘柄.get外貨MMF為替レート()の戻り値外貨MMF為替レートParamsのTTB
        //              をセットする。（小数第３位で四捨五入）
        //       2-2) 投信銘柄.is外貨MMF ＝ falseの場合
        //              拡張投信銘柄.get為替レート()の戻り値為替レートParamsのTTB / 同為替レート計算単位
        //              をセットする。（小数第３位で四捨五入）
        //・参考レート確定日
        //　@　@1）投信銘柄.get通貨コード( )がT0の場合 
        //　@　@　@ nullをセットする。
        //　@　@2）投信銘柄.get通貨コード( )がT0でない場合 
        //        2-1) 投信銘柄.is外貨MMF ＝ trueの場合
        //             拡張投信銘柄.get外貨MMF為替レート()の戻り値
        //             外貨MMF為替レートParamsの為替レート確定日をセットする。
        //        2-2) 投信銘柄.is外貨MMF ＝ falseの場合
        //             拡張投信銘柄.get為替レート()の戻り値
        //             為替レートParamsの為替レート確定日をセットする。
        if (l_web3MfProduct.isFrgnMmf())
        {
            l_response.yenConstantValue = null;
        }
        if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_web3MfProduct.getCurrencyCode()))
        {
            l_response.yenConstantValue = null;
            l_response.referenceRate = null;
            l_response.referenceRateFixedDay = null;
        }
        else 
        {
            if (l_web3MfProduct.isFrgnMmf())
            {
                BigDecimal l_bdTtBuyingRate =
                    new BigDecimal(l_web3MfProduct.getFrgnMmfExchangeRate().getTtBuyingRate());
                l_response.referenceRate =
                    WEB3StringTypeUtility.formatNumber(
                        l_bdTtBuyingRate.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                l_response.referenceRateFixedDay = 
                    l_web3MfProduct.getFrgnMmfExchangeRate().getExecTimestamp();
            }
            else
            {
                l_response.yenConstantValue = 
                    l_web3MfProduct.getYenConstantValue(WEB3MFProcessDivDef.SELL);
                BigDecimal l_bdTtBuyingRate = 
                    new BigDecimal(l_web3MfProduct.getExchangeRate().getTtBuyingRate());
                BigDecimal l_bdExchangeCalcUnit = 
                    new BigDecimal(l_web3MfProduct.getExchangeRate().getExchangeCalcUnit());      
                l_response.referenceRate = 
                    WEB3StringTypeUtility.formatNumber(
                        l_bdTtBuyingRate.divide(l_bdExchangeCalcUnit, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                l_response.referenceRateFixedDay = 
                    l_web3MfProduct.getExchangeRate().getExecTimestamp();
            }
        }

        MutualFundProductRow l_mfProductRow = 
            (MutualFundProductRow)l_web3MfProduct.getDataSourceObject();
        //解約時外貨単位金額：
        // (*) 取得した拡張投信銘柄.get外貨単位金額（解約）()の戻り値を設定する。
        if (!l_mfProductRow.getFrgnSellUnitAmtIsNull())
        {
            l_response.sellFrgnUnitAmt =
                l_web3MfProduct.getFrgnSellUnitAmt() + "";
        }

        //解約時外貨最低金額：
        // (*) 取得した拡張投信銘柄.get外貨最低金額（解約）()の戻り値を設定する。
        if (!l_mfProductRow.getFrgnSellMinAmtIsNull())
        {
            l_response.sellFrgnMinAmt =
                l_web3MfProduct.getFrgnSellMinAmt() + "";
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
