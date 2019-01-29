head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約サービス実装クラス(WEB3MutualSellServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 韋念瓊 (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2004/12/13 于美麗 (中訊) 残対応
Revesion History : 2005/10/21 黄建 (中訊) フィデリティ対応     
Revesion History : 2006/10/16 周捷 仕様変更・モデル500
Revesion History : 2007/02/07 丁昭奎 (中訊) モデル No.528   
Revesion History : 2007/02/25 唐性峰 (中訊) モデル No.542
Revesion History : 2007/03/26 趙林鵬 (中訊) モデル No.550
Revesion History : 2007/04/09 趙林鵬 (中訊) モデル No.556,実装005
Revesion History : 2008/02/14 柴双紅 (中訊) モデル No.591
Revesion History : 2008/05/07 松本 (SRA) 暫定対応
Revesion History : 2008/05/12 武波 (中訊) 仕様変更 No602
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioProductTypeOrderManagerReusableValidations;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.aio.WEB3AioCashTransOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductTypeOrderManagerReusableValidations;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3MfPaymentMethodCheckDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundNewOrderConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualSellCompleteRequest;
import webbroker3.mf.message.WEB3MutualSellCompleteResponse;
import webbroker3.mf.message.WEB3MutualSellConfirmRequest;
import webbroker3.mf.message.WEB3MutualSellConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualSellService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (投信解約サービスImpl)<BR>
 * 投資信託解約サービス実装クラス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualSellServiceImpl extends WEB3MutualClientRequestService implements WEB3MutualSellService 
{ 
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellServiceImpl.class);
        
    /**
     * 投資信託解約サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate()解約または、<BR>
     * submit解約()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException 
     * @@roseuid 40556A1F0156
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

        if (l_request instanceof WEB3MutualSellConfirmRequest)
        {
            //リクエストデータの具象データ型が「投信解約確認リクエスト」の場合
            log.exiting(STR_METHOD_NAME);
            WEB3MutualSellConfirmResponse l_confirmResponse = 
                this.validateSell((WEB3MutualSellConfirmRequest) l_request);
            return l_confirmResponse;
        }
        else if (l_request instanceof WEB3MutualSellCompleteRequest)
        {
            //リクエストデータの具象データ型が「投信解約正完了リクエスト」の場合
            log.exiting(STR_METHOD_NAME);
            WEB3MutualSellCompleteResponse l_completeResponse = 
                this.submitSell((WEB3MutualSellCompleteRequest) l_request);
            return l_completeResponse;
        }
        else
        {
            log.debug(STR_METHOD_NAME + "パラメータタイプ不正");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (validate解約)<BR>
     * 投資信託解約審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投信)解約審査」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)解約審査」: <BR>
     *        20((保有残高口数超過チェック <BR>
     *        取得した概算受渡代金オブジェクト.get概算売買口数()＞ <BR>
     *         取得した解約可能残高口数、の場合は例外をスローする。<BR>
     *        （保有残高口数超過エラー）<BR>
     *         例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00387<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)解約審査」: <BR>
     *        10((保有資産の取得 <BR>
     *        投信拡張ポジションマネージャ.getAsset( )をコールする <BR>
     *         検索結果が0件の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00204<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)解約審査」: <BR>
     *       15.2) is解約口数拘束率超過()の戻り値がtrueの場合、例外をスローする<BR>
     *         解約可能残高口数==0 の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02269<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualSellConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40556A7C0127
     */
    protected WEB3MutualSellConfirmResponse validateSell(WEB3MutualSellConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateSell(" +
            "WEB3MutualSellConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1）入力内容チェック 
        l_request.validate();

        //1.2）補助口座取得
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();
        //拡張投信銘柄マネージャを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_web3MfProductMgr =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        MutualFundProduct l_mfProduct = null;
        WEB3MutualFundProduct l_web3MfProduct = null;
        WEB3MutualSellConfirmResponse l_response = null;
        try
        {            
            //1.3) 拡張投信銘柄マネージャ.get投信銘柄()をコール
            l_mfProduct =
                l_web3MfProductMgr.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode);
                    
            //取得できない場合は例外をスローする        
            if (l_mfProduct == null)
            {
                log.debug("投信銘柄取得できない場合エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //拡張投信銘柄取得
            l_web3MfProduct = (WEB3MutualFundProduct) l_mfProduct;

            //顧客別取引停止属性チェック 
            //拡張投信注文マネージャを取得する
            WEB3MutualFundOrderManager l_web3MfOrderMgr =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();

            //1.4) FinAppクラスのgetCommonOrderValidator()をコールし
            //注文チェックオブジェクトを取得する      
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

            //発注日取得          
            Timestamp l_tsBizDate = 
                new Timestamp(l_request.orderedDate.getTime());
            
            WEB3GentradeMainAccount l_genMainAccount = 
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            
            //1.5) validate取引可能顧客(顧客, 発注日)
            OrderValidationResult l_validationResult =  
                l_orderValidator.validateAccountForTrading(
                    l_genMainAccount,
                    l_tsBizDate);
            
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //受付時間チェック、システム取引停止チェック 
            //1.6) 投信取引時間管理.validete注文受付可能()をコールする
            WEB3MutualFundTradingTimeManagement.validateOrderAccept();
            
            //取引時間の再設定
            //1.7) 投信取引時間管理.reset銘柄コード()をコール
            WEB3MutualFundTradingTimeManagement.resetProductCode(
                l_request.mutualProductCode);
                
            //1.8) 受付日時、日付ロールをセットする
            WEB3MutualFundTradingTimeManagement.setTimestamp();
            
            //拡張投信ポジションマネージャを取得する
            WEB3MutualFundPositionManager l_web3MfPositionMgr =
                (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
            //1.9)getAsset(long)
            //－投信拡張ポジションマネージャ.getAsset( )をコールする。 
            //　@[引数] 
            //　@リクエスト.ID：資産ID
            Asset l_asset = null;
            try
            {
                l_asset = l_web3MfPositionMgr.getAsset(Long.parseLong(l_request.id));
            }
            catch (NotFoundException l_ex)
            {
                //1.10)＜getAsset()の戻り値＝0件の場合、例外をスローする。＞
                log.error("保有資産オブジェクトデータなし。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
  
            //1.11）解約可能残高口数を取得する  
            //   補助口座： 取得した補助口座オブジェクト 
            //   拡張投信銘柄： 取得した拡張投信銘柄オブジェクト
            //   資産ID：　@リクエスト.ID
            double l_dblSellPossiblePositionQty = 0;  
            l_dblSellPossiblePositionQty = 
                l_web3MfPositionMgr.calcSellPossiblePositionQty(
                    l_subAccount, l_web3MfProduct, l_request.id);

            //1.12) 解約可能残高口数==0 の場合、例外をスローする
            if(l_dblSellPossiblePositionQty == 0)
            {
                log.error("残高なしエラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00390,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );    
            }
                     
            //発注審査
            String l_strProcessDiv = null;  //処理区分
            //(*) 取得した拡張投信銘柄オブジェクト.is外貨MMF()==true場合は
            //”2：解約”を指定
            //(*) 投信解約確認リクエスト.請求方法@の値が”0：解約請求”の場合は 
            //  ”2：解約”を指定
            if (l_web3MfProduct.isFrgnMmf() 
                || WEB3ClaimDivDef.SELL.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.SELL;
            }
            //(*) 投信解約確認リクエスト.請求方法@の値が”1：買取請求”の場合は 
            //  ”4：買取”を指定
            else if (WEB3ClaimDivDef.BUY.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.PURCHASE;
            }

            log.debug("請求方法@ = " + l_strProcessDiv);
            NewOrderValidationResult l_newOrderValidationResult = null;
            
            //1.13)－拡張投信注文マネージャ.validate新規注文()をコールし、発注審査を行う
			//［validate新規注文に渡すパラメタ］  
			//補助口座： 取得した補助口座オブジェクト  
			//拡張投信銘柄： 取得した拡張投信銘柄オブジェクト  
			//注文金額数量： リクエストデータ.数量  
			//処理区分：
            //(*) 取得した拡張投信銘柄オブジェクト.is外貨MMF()==true場合は
            //”2：解約”を指定
			//(*) リクエストデータ.請求方法@の値が”0：解約請求”の場合は 
			//”2：解約”を指定 
			//(*) リクエストデータ.請求方法@の値が”1：買取請求”の場合は 
			//”4：買取”を指定 
			//受渡方法@： リクエストデータ.受渡方法@ 
			//指定方法@： リクエストデータ.指定方法@ 
			//乗換先銘柄： null 
			//税区分：保有資産テーブルParams.get税区分()の戻り値
            //決済方法@： リクエストデータ.決済方法@
            double l_dblOrderQuantity = 0.0;
            if (!WEB3StringTypeUtility.isEmpty(l_request.mutualOrderQuantity))
            {
                l_dblOrderQuantity = 
                    Double.parseDouble(l_request.mutualOrderQuantity);
            }

            l_newOrderValidationResult = l_web3MfOrderMgr.validateNewOrder(
                l_subAccount, 
                l_web3MfProduct, 
                l_dblOrderQuantity,
                l_strProcessDiv,
                l_request.deliveryDiv,
                l_request.specifyDiv,
                null,
                l_asset.getTaxType(),
                l_request.settleDiv);

            if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("発注審査エラー");
                throw new WEB3BusinessLayerException(
                    l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            // 1.14）is金額指定解約中
			//[引数] 
			// 補助口座：取得した補助口座 
			// 拡張投信銘柄：取得した拡張投信銘柄 
			// 税区分：getAsset()によって取得した保有資産の税区分
            WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
                (WEB3MutualFundOrderManagerReusableValidationsCheck)
                    MutualFundProductTypeOrderManagerReusableValidations.getInstance();
            
            boolean l_blnIsPriceDesignateCancelling =
                l_validationsCheck.isPriceDesignateCancelling(
                    l_subAccount, 
                    l_web3MfProduct, 
                    l_asset.getTaxType());
            
            //1.15) 分岐：
            //以下に当てはまる場合、解約口数拘束率超過チェックをする。
            //リクエストデータ.指定方法@ != ”全部” and ((リクエストデータ.指定方法@ == ”金額
            //and (投信銘柄.is外貨MMF == false or リクエストデータ.決済区分 != ”外貨”))
            //or is金額指定解約中（）の戻り値 == true）
            boolean l_blnIsSellQtyLimitRateExcess = false;
            if (!WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) && 
                ((WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv) &&
                    (!l_web3MfProduct.isFrgnMmf() ||
                    !WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_request.settleDiv))) ||
                    l_blnIsPriceDesignateCancelling))
            {
                String l_strTaxType = null;
                if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
                {
                    l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
                }
                else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
                {
                    l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
                }
                //1.15.1)is解約口数拘束率超過
    			//補助口座： 取得した補助口座オブジェクト 
    			//銘柄： 取得した乗換元拡張投信銘柄オブジェクト 
    			//銘柄（乗換先）： null 
    			//資産ID：リクエスト.ID 
    			//解約可能残高口数：calc解約可能残高口数()の戻り値 
    			//処理区分：”2：解約”を指定  
    			//注文数量： リクエスト.数量 
    			//指定方法@： リクエスト.指定方法@ 
    			//決済方法@： リクエスト.決済方法@ 
    			//請求方法@： リクエスト.請求方法@ 
    			//口座区分： 取得した保有資産テーブルParams.get税区分()の戻り値  
    			//発注日： リクエスト.発注日
                l_blnIsSellQtyLimitRateExcess =
    	            l_validationsCheck.isSellQtyLimitRateExcess(
    	                l_subAccount,
    	                l_web3MfProduct,
    	                null,
    	                l_request.id,
    	                l_dblSellPossiblePositionQty,
    	                WEB3ProcessDivDef.SELL,
    	                l_dblOrderQuantity,
    	                l_request.specifyDiv,
    	                l_request.settleDiv,
    	                l_request.sellBuyDiv,
    	                l_strTaxType,
    	                l_request.orderedDate);
                
                //1.15.2) is解約口数拘束率超過()の戻り値がtrueの場合、例外をスローする
                if (l_blnIsSellQtyLimitRateExcess)
                {
                    log.error("解約口数拘束率を超過している。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02269,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "解約口数拘束率を超過している。");
                }   
            } 
            
            //1.16 <*>分岐：リクエストデータ.指定方法@の値が”全部”の場合、 
            //リクエストデータ.数量に解約可能残高口数 を設定する。
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv)) 
            {
                l_dblOrderQuantity = l_dblSellPossiblePositionQty;   
            }
            
            //1.17) <*>分岐：リクエストデータ.受渡方法@の値 == ”1：銀行振込み”の場合、
            //受渡方法@のチェックを実施する。
            //リクエストデータ.受渡方法@の値 == ”1：銀行振込み”の場合、受渡方法@のチェックを実施する。
            if (WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_request.deliveryDiv))
            {
                //1.17.1)validate受渡方法@(SubAccount, String,拡張投信銘柄)
				//［に渡すパラメタ］ 
				//補助口座：取得した補助口座オブジェクト 
				//資産ID：リクエストデータ.ID
                //拡張投信銘柄：取得した拡張投信銘柄
                this.validatePaymentMethod(l_subAccount, l_request.id, l_web3MfProduct);
            }

            // 1.18）発注日の取得　@(*)特定日取引銘柄の場合
            long l_lngUnitProductId = l_web3MfProduct.getProductId();
            Date l_datOrderBizDate = null;
            if (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L)
            {
                // 1.18.1）　@get特定日取引銘柄解約発注日(Date)
                //[get発注日に渡すパラメタ]  
                // 確認時発注日：　@リクエストデータ.発注日 
                l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getUnitTypeProductSellOrderBizDate(
                    l_request.orderedDate);
            }
            // 1.19）発注日の取得　@(*)特定日取引銘柄以外の場合
            else
            {
                // 1.19.1）　@get発注日(Date, OrderTypeEnum, boolean)
                //[get発注日に渡すパラメタ]  
                // 確認時発注日：　@リクエストデータ.発注日 
                // 注文種別　@　@　@：  OrderTypeEnum.投資信託売注文 
                // 一括区分　@　@　@：　@取得した拡張投信銘柄．is特定日取引銘柄 
                // [is特定日取引銘柄のパラメタ] 
                // 注文種別 ： OrderTypeEnum.投資信託売注文
                l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                    l_request.orderedDate,
                    OrderTypeEnum.MF_SELL,
                    l_web3MfProduct.isUnitTypeProduct(OrderTypeEnum.MF_SELL));

            }

            //1.19.get約定日(Institution, String, Date)
            //［get約定日に渡すパラメタ］ 
            //  証券会社： 取得した補助口座.getInstitution()の戻り値 
            //  銘柄コード： リクエストデータ.銘柄コード 
            //  発注日　@　@：取得した発注日
            Date l_datExecutedDate = 
                l_web3MfProductMgr.getExecutedDate(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode,
                    l_datOrderBizDate);

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
                l_datExecutedDate = l_tradingCalendar.roll(l_datOrderBizDate, 2);
            }

            //1.20.get受渡日(Institution, String, boolean, Date)
            //［get受渡日に渡すパラメタ］ 
            //証券会社： 取得した補助口座.getInstitution()の戻り値 
            //銘柄コード： リクエストデータ.銘柄コード 
            //is買付： false 
            //約定日：取得した約定日
            Timestamp l_tsDeliveryDate =
	            l_web3MfProductMgr.getDeliveryDate(
	                l_subAccount.getInstitution(),
	                l_request.mutualProductCode,
	                false,
                    l_datExecutedDate);
            
            //1.21)概算受渡代金を算出する。
            WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = null;

            //概算受渡代金取得 
			//［calc概算受渡代金に渡すパラメタ］ 
			//補助口座： 取得した補助口座オブジェクト 
			//銘柄： 取得した拡張投信銘柄オブジェクト 
			//銘柄（乗換先）： null 
			//処理区分：”2：解約”を指定 
			//注文数量： リクエストデータ.数量 
			//指定方法@： 
			//(*) リクエストデータ.指定方法@が”2：全部”の場合は”4：口数”を設定する。 
			//(*) そうでない場合はリクエストデータ.指定方法@を設定する。 
			//決済方法@： リクエストデータ.決済方法@ 
			//請求方法@： リクエストデータ.請求方法@ 
			//口座区分： 取得した保有資産テーブルParams.get税区分()の戻り値
			//注文チャネル： セッションから取得した注文チャネル 
			//発注日： リクエストデータ.発注日 

            //指定方法@：
            String l_strSpecifyDiv = null;
            log.debug("投信解約確認リクエスト.指定方法@" + l_request.specifyDiv);
            //(*) 投信解約確認リクエスト.指定方法@が”2：全部”の場合は”4：口数”を設定する
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
            {                
                l_strSpecifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
                log.debug("指定方法@ change 4");
            }
            //(*) そうでない場合は投信解約確認リクエスト.指定方法@を設定する
            else
            {
                l_strSpecifyDiv = l_request.specifyDiv;
            }
                
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);  
            String l_strOrderChanel = 
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
                
            String l_strTaxType = null;
            if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            //拡張投信注文マネージャ.calc概算受渡代金()をコールして、
            //概算受渡代金オブジェクトを取得する
            l_mfEstimatedPrice = l_web3MfOrderMgr.calcEstimateDeliveryAmount(
                l_subAccount,
                l_web3MfProduct,
                null,
                WEB3ProcessDivDef.SELL,
                l_dblOrderQuantity,
                l_strSpecifyDiv,
                l_request.settleDiv,
                l_request.sellBuyDiv,
                l_strTaxType,
                l_strOrderChanel,
                l_request.orderedDate);
              
            //1.22) 取得した概算受渡代金オブジェクト.get概算売買口数() ＞ 
            //取得した解約可能残高口数の場合、例外をスローする    
            if (l_mfEstimatedPrice.getEstimatedQty() > l_dblSellPossiblePositionQty)
            {
                log.debug("保有残高口数超過チェックエラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00387,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.23)is投信解約時出金注文生成( )
            WEB3GentradeBranch l_branch = 
                (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
            boolean l_blnIsPaymentOrderCreate = l_branch.isPaymentOrderCreate();
            
            //1.24) is投信解約時出金注文生成()の戻り値 == true and 
            //      リクエストデータ.受渡方法@　@== 1:銀行振込 の場合、出金金額範囲チェックを行なう。
            if (l_blnIsPaymentOrderCreate && 
                WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_request.deliveryDiv))
            {
                //1.24.1)validate出金金額範囲(double, Date)
    			//[validate出金金額範囲()に渡すパラメタ] 
    			//概算受渡代金：　@取得した概算受渡代金オブジェクト.get概算受渡代金()の戻り値 
    			//受渡日：　@get受渡日()の戻り値
                this.validatePaymentMoney(
                    l_mfEstimatedPrice.getEstimatedPrice(), 
                    l_tsDeliveryDate);
            }
            
            //1.25) 注文IDを採番する
            long l_lngOrderId = l_web3MfOrderMgr.createNewOrderId();
          
            //1.26）投信解約確認レスポンスオブジェクトを生成
            l_response = (WEB3MutualSellConfirmResponse) l_request.createResponse();
            
			//1.27) プロパティ・セット[投信解約確認レスポンスに設定する値] 
            //解約注文内容警告区分：
            if (l_blnIsSellQtyLimitRateExcess)
            {
                l_response.sellWarningType = "1";
            }
            else
            {
                l_response.sellWarningType = null;
            }
            
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
            {
                l_response.estimatedPriceCurrencyCode = WEB3MFOrderQuantityType.EN;
            }
            else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_request.settleDiv))
            {
                l_response.estimatedPriceCurrencyCode =
                    l_web3MfProduct.getCurrencyCode();
            }
            
            //概算受渡代金：
            l_response.estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(l_mfEstimatedPrice.getEstimatedPrice());
            //概算売買口数：
            l_response.estimatedQty = 
                WEB3StringTypeUtility.formatNumber(l_mfEstimatedPrice.getEstimatedQty());
            
            l_response.orderId = l_lngOrderId + "";

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
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit解約)<BR>
     * 投資信託解約登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投信)解約登録」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)解約登録」: <BR>
     *       23.2((保有残高口数超過チェック <BR>
     *        取得した概算受渡代金オブジェクト.get概算売買口数()＞ <BR>
     *         取得した解約可能残高口数、の場合は例外をスローする。<BR>
     *        （保有残高口数超過エラー）<BR>
     *         例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00387<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)解約登録」: <BR>
     *        11((保有資産の取得 <BR>
     *        投信拡張ポジションマネージャ.getAsset( )をコールする <BR>
     *         検索結果が0件の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00204<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)解約登録」: <BR>
     *       17.2) is解約口数拘束率超過()の戻り値がtrueの場合、例外をスローする<BR>
     *         解約可能残高口数==0 の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02269<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualSellCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40556A8301A4
     */
    protected WEB3MutualSellCompleteResponse submitSell(WEB3MutualSellCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitSell(" +
            "WEB3MutualSellCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1）入力内容チェック 
        l_request.validate();

        //1.2）補助口座取得
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) getSubAccount();
        //拡張投信銘柄マネージャを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_web3MfProductMgr =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        MutualFundProduct l_mfProduct = null;
        WEB3MutualFundProduct l_web3MfProduct = null;
        WEB3MutualSellCompleteResponse l_response = null;
        try
        {
            //1.3) 拡張投信銘柄マネージャ.get投信銘柄()をコール
            l_mfProduct =
                l_web3MfProductMgr.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode);
                    
            //取得できない場合は例外をスローする        
            if (l_mfProduct == null)
            {
                log.debug("Error in 投信銘柄を取得");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //拡張投信銘柄取得
            l_web3MfProduct = (WEB3MutualFundProduct) l_mfProduct;
            
            //1.4) 顧客別取引停止属性チェック 
            //拡張投信注文マネージャを取得する
            WEB3MutualFundOrderManager l_web3MfOrderMgr =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
                    
            //FinAppクラスのgetCommonOrderValidator()をコールし
            //注文チェックオブジェクトを取得する      
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();          

			//発注日の取得
            Timestamp l_datBizDate = 
                new Timestamp(l_request.orderedDate.getTime());
            
            //1.5) validate取引可能顧客
            WEB3GentradeMainAccount l_genMainAccount = 
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
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
            
            //1.6)get代理入力者( )
            Trader l_trader = this.getTrader();

            //1.7) validate取引パスワード( )をコールする。  
            OrderValidationResult l_validationResultPassword = 
                l_orderValidator.validateTradingPassword(
                    this.getTrader(),
                    l_subAccount,
                    l_request.password);
            
            if (l_validationResultPassword.getProcessingResult().isFailedResult())
            {
                log.debug("取引パスワードが不正です");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            // 受付時間チェック、システム取引停止チェック 
            //1.8) 投信取引時間管理.validete注文受付可能()をコールする
            WEB3MutualFundTradingTimeManagement.validateOrderAccept();

            // 取引時間の再設定
            //1.9) 投信取引時間管理.reset銘柄コード()をコール
            WEB3MutualFundTradingTimeManagement.resetProductCode(
                l_request.mutualProductCode);
                
            //1.10) 受付日時、日付ロールをセットする
            WEB3MutualFundTradingTimeManagement.setTimestamp();
            
            //拡張投信ポジションマネージャを取得する
            WEB3MutualFundPositionManager l_web3MfPositionMgr =
                (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
            //1.11) getAsset(資産ID)
            //getAsset()の戻り値＝0件の場合、例外をスローする
            //－投信拡張ポジションマネージャ.getAsset( )をコールする。 
            //　@[引数] 
            //　@リクエスト.ID：資産ID
            //検索結果が0件の場合、例外をスローする。 
            Asset l_asset = null;
            try
            {
                l_asset = l_web3MfPositionMgr.getAsset(Long.parseLong(l_request.id));
            }
            //1.12 該当保有資産0件の場合、例外をスローする
            catch (NotFoundException l_ex)
            {
                log.error("保有資産オブジェクトデータなし。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            
            if(l_asset == null)
            {
                log.debug("保有資産オブジェクトデータなし。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.13）解約可能残高口数を取得する 
            // calc解約可能残高口数(補助口座, 拡張投信銘柄, 資産ID)
            // ［calc解約可能残高口数に渡すパラメタ］
            // 　@補助口座： 取得した補助口座オブジェクト 
            // 　@拡張投信銘柄： 取得した拡張投信銘柄オブジェクト
            //  資産ID：　@リクエスト.ID
            double l_dblSellPossiblePositionQty = 0;  
            l_dblSellPossiblePositionQty = 
                l_web3MfPositionMgr.calcSellPossiblePositionQty(
                    l_subAccount, l_web3MfProduct, l_request.id);

            // 1.14) 解約可能残高口数==0 の場合、例外をスローする
            if(l_dblSellPossiblePositionQty == 0)
            {
                log.error("解約可能残高口数がありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00390,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );    
            }

            //1.15）発注審査
			//［validate新規注文に渡すパラメタ］  
			//補助口座： 取得した補助口座オブジェクト  
			//拡張投信銘柄： 取得した拡張投信銘柄オブジェクト  
			//注文金額数量： リクエストデータ.数量  
			//処理区分：
            //(*) 取得した拡張投信銘柄オブジェクト.is外貨MMF()==true場合は
            //”2：解約”を指定
			//(*) リクエストデータ.請求方法@の値が”0：解約請求”の場合は 
			//”2：解約”を指定 
			//(*) リクエストデータ.請求方法@の値が”1：買取請求”の場合は 
			//”4：買取”を指定 
			//受渡方法@： リクエストデータ.受渡方法@ 
			//指定方法@： リクエストデータ.指定方法@ 
			//乗換先銘柄： null 
			//税区分： 取得した保有資産Params.get税区分()の戻り値
            //決済方法@： リクエストデータ.決済方法@

            String l_strProcessDiv = null;  //処理区分
            //(*) 投信解約確認リクエスト.請求方法@の値が”0：解約請求”の場合は
            //  ”2：解約”を指定
            //(*) 取得した拡張投信銘柄オブジェクト.is外貨MMF()==true場合は
            //  ”2：解約”を指定
            if (l_web3MfProduct.isFrgnMmf() 
                || WEB3ClaimDivDef.SELL.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.SELL;
            }
            //(*) 投信解約確認リクエスト.請求方法@の値が”1：買取請求”の場合は 
            //  ”4：買取”を指定
            else if (WEB3ClaimDivDef.BUY.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.PURCHASE;
            }
            
            double l_dblOrderQuantity = 0.0;
            if (!WEB3StringTypeUtility.isEmpty(l_request.mutualOrderQuantity))
            {
                l_dblOrderQuantity = 
                    Double.parseDouble(l_request.mutualOrderQuantity);
            }
            NewOrderValidationResult l_newOrderValidationResult = null;
            //－拡張投信注文マネージャ.validate新規注文()をコールし、発注審査を行う
            l_newOrderValidationResult = l_web3MfOrderMgr.validateNewOrder(
                l_subAccount, 
                l_web3MfProduct, 
                l_dblOrderQuantity,
                l_strProcessDiv,
                l_request.deliveryDiv,
                l_request.specifyDiv,
                null,
                l_asset.getTaxType(),
                l_request.settleDiv);

            if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("発注審査エラー");
                throw new WEB3BusinessLayerException(
                    l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.16) is金額指定解約中
            WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
                (WEB3MutualFundOrderManagerReusableValidationsCheck)
                    MutualFundProductTypeOrderManagerReusableValidations.getInstance();
            boolean l_blnIsPriceDesignateCancelling =
                l_validationsCheck.isPriceDesignateCancelling(
                    l_subAccount, 
                    l_web3MfProduct, 
                    l_asset.getTaxType());
            
            //1.17) 分岐：
            //以下に当てはまる場合、解約口数拘束率超過チェックをする。
            //リクエストデータ.指定方法@ != ”全部” and ((リクエストデータ.指定方法@ == ”金額
            //and (投信銘柄.is外貨MMF == false or リクエストデータ.決済区分 != ”外貨”))
            //or is金額指定解約中（）の戻り値 == true）
            if (!WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) && 
                ((WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv) &&
                    (!l_web3MfProduct.isFrgnMmf() ||
                    !WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_request.settleDiv))) ||
                    l_blnIsPriceDesignateCancelling))
            {
                String l_strTaxType = null;
                if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
                {
                    l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
                }
                else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
                {
                    l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
                }
                //1.17.1)is解約口数拘束率超過
    			//補助口座： 取得した補助口座オブジェクト 
    			//銘柄： 取得した拡張投信銘柄オブジェクト 
    			//銘柄（乗換先）： null 
    			//資産ID：リクエスト.ID 
    			//解約可能残高口数：calc解約可能残高口数()の戻り値 
    			//処理区分：”2：解約”を指定 
    			//注文数量： リクエスト.数量 
    			//指定方法@： リクエスト.指定方法@ 
    			//決済方法@： リクエスト.決済方法@ 
    			//請求方法@： リクエスト.請求方法@ 
    			//口座区分： 取得した保有資産テーブルParams.get税区分()の戻り値  
    			//発注日： リクエスト.発注日
                boolean l_blnIsSellQtyLimitRateExcess =
    	            l_validationsCheck.isSellQtyLimitRateExcess(
    	                l_subAccount,
    	                l_web3MfProduct,
    	                null,
    	                l_request.id,
    	                l_dblSellPossiblePositionQty,
    	                WEB3ProcessDivDef.SELL,
    	                l_dblOrderQuantity,
    	                l_request.specifyDiv,
    	                l_request.settleDiv,
    	                l_request.sellBuyDiv,
    	                l_strTaxType,
    	                l_request.orderedDate);
                
                //1.17.2) is解約口数拘束率超過()の戻り値がtrueの場合、例外をスローする
                if (l_blnIsSellQtyLimitRateExcess)
                {
                    log.error("解約口数拘束率を超過している。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02269,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "解約口数拘束率を超過している。");
                }   
            } 
            
            //1.18) 分岐：リクエストデータ.指定方法@の値が”全部”の場合、
            //リクエストデータ.数量に 解約可能残高口数 を設定する。
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_dblOrderQuantity = l_dblSellPossiblePositionQty;
            }
            
            //1.19) <*>分岐：リクエストデータ.受渡方法@の値が”1：銀行振込み”の場合
            //、受渡方法@のチェックを実施する。
            if (WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_request.deliveryDiv))
            {
                //1.19.1)validate受渡方法@(SubAccount, String,拡張投信銘柄)
                this.validatePaymentMethod(l_subAccount, l_request.id, l_web3MfProduct);
            }

            // 1.20）発注日の取得　@(*)特定日取引銘柄の場合
            long l_lngUnitProductId = l_web3MfProduct.getProductId();
            Date l_datOrderBizDate = null;
            if (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L)
            {
                // 1.20.1）　@get特定日取引銘柄解約発注日(Date)
                //[get発注日に渡すパラメタ]  
                // 確認時発注日：　@リクエストデータ.発注日 
                l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getUnitTypeProductSellOrderBizDate(
                    l_request.orderedDate);
            }
            // 1.21）発注日の取得　@(*)特定日取引銘柄以外の場合
            else
            {
                //1.21.1get発注日(Date, OrderTypeEnum, boolean)
                //[get発注日に渡すパラメタ]  
                //確認時発注日：　@リクエストデータ.発注日 
                //注文種別　@　@　@：  OrderTypeEnum.投資信託売注文 
                //一括区分　@　@　@：　@取得した拡張投信銘柄．is特定日取引銘柄 
                //[is特定日取引銘柄のパラメタ] 
                //注文種別 ： OrderTypeEnum.投資信託売注文
                l_datOrderBizDate = 
                    WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                        l_request.orderedDate, 
                        OrderTypeEnum.MF_SELL, 
                        l_web3MfProduct.isUnitTypeProduct(OrderTypeEnum.MF_SELL));

            }

            //1.21.get約定日(Institution, String, Date)
            Date l_datExecutedDate = null;
            //［get約定日に渡すパラメタ］ 
            //証券会社： 取得補助口座.getInstitution()の戻り値 
            //銘柄コード： 取得した拡張投信銘柄.getProductCode()の戻り値 
            //発注日：取得した発注日
            l_datExecutedDate = l_web3MfProductMgr.getExecutedDate(
                l_subAccount.getInstitution(),
                l_web3MfProduct.getProductCode(),
                l_datOrderBizDate);

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
                l_datExecutedDate = l_tradingCalendar.roll(l_datOrderBizDate, 2);
            }

            //1.22.get受渡日(Institution, String, boolean, Date)
            Timestamp l_tsDeliveryDate = null;
            //［get受渡日に渡すパラメタ］ 
            //証券会社： 取得した補助口座.getInstitution()の戻り値 
            //銘柄コード： リクエストデータ.銘柄コード 
            //is買付： false 
            //約定日：取得した約定日
            l_tsDeliveryDate = l_web3MfProductMgr.getDeliveryDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                false,
                l_datExecutedDate);
            
            //1.23 概算受渡代金を算出する。
            WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = null;
            
            //概算受渡代金取得 
			//［calc概算受渡代金に渡すパラメタ］ 
			//補助口座： 取得した補助口座オブジェクト 
			//銘柄： 取得した拡張投信銘柄オブジェクト 
			//銘柄（乗換先）： null 
			//処理区分：”2：解約”を指定 
			//注文数量： リクエストデータ.数量 
			//指定方法@： 
			//(*) リクエストデータ.指定方法@が”2：全部”の場合は”4：口数”を設定する。 
			//(*) そうでない場合はリクエストデータ.指定方法@を設定する。 
			//決済方法@： リクエストデータ.決済方法@ 
			//請求方法@： リクエストデータ.請求方法@ 
			//口座区分： 取得した保有資産テーブルParams.get税区分()の戻り値  
			//注文チャネル： セッションから取得した注文チャネル 
			//発注日： リクエストデータ.発注日 

            //指定方法@：
            String l_strSpecifyDiv = null;
            log.debug("投信解約確認リクエスト.指定方法@" + l_request.specifyDiv);
            //(*) 投信解約確認リクエスト.指定方法@が”2：全部”の場合は”4：口数”を設定する
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
            {                
                l_strSpecifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
                log.debug("指定方法@ change 4");
            }
            //(*) そうでない場合は投信解約確認リクエスト.指定方法@を設定する
            else
            {
                l_strSpecifyDiv = l_request.specifyDiv;
            }
                
            String l_strOrderChanel = this.getLoginChannel();
                
            String l_strTaxType = null;
            if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            //拡張投信注文マネージャ.calc概算受渡代金()をコールして、
            //概算受渡代金オブジェクトを取得する
            l_mfEstimatedPrice = l_web3MfOrderMgr.calcEstimateDeliveryAmount(
                l_subAccount,
                l_web3MfProduct,
                null,
                WEB3ProcessDivDef.SELL,
                l_dblOrderQuantity,
                l_strSpecifyDiv,
                l_request.settleDiv,
                l_request.sellBuyDiv,
                l_strTaxType,
                l_strOrderChanel,
                l_request.orderedDate);
                
            //1.24) 取得した概算受渡代金オブジェクト.get概算売買口数() ＞ 
            //取得した解約可能残高口数の場合、例外をスローする   
            if (l_mfEstimatedPrice.getEstimatedQty() > l_dblSellPossiblePositionQty)
            {
                log.debug("保有残高口数超過チェックエラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00387,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "保有残高口数超過チェックエラー");
            }
            
            //1.25) is投信解約時出金注文生成( )
            WEB3GentradeBranch l_branch = 
                (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
            boolean l_blnIsPaymentOrderCreate = l_branch.isPaymentOrderCreate();
            
            //1.26) is投信解約時出金注文生成()の戻り値 == true and 
            //リクエストデータ.受渡方法@　@== 1:銀行振込 の場合、出金伝票を作成する。
            String l_strPaymentOrderReqNumber = null;
            if (l_blnIsPaymentOrderCreate && 
                WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_request.deliveryDiv))
            {
                //1.26.1) create出金伝票作成
				//[create出金伝票作成に渡すパラメタ] 
				//代理入力者：　@this.get代理入力者()の戻り値 
				//概算受渡代金：取得した概算受渡代金オブジェクト.get概算受渡代金()の戻り値 
				//受渡日：　@get受渡日()の戻り値 
				//暗証番号：　@リクエストデータ.暗証番号
                l_strPaymentOrderReqNumber =
	                this.createPaymentTicket(
	                    l_trader, 
	                    l_mfEstimatedPrice.getEstimatedPrice(), 
	                    l_tsDeliveryDate, 
	                    l_request.password);
            }

            //1.27）投信新規注文確定インタセプタを生成する
            WEB3MutualFundNewOrderConfirmInterceptor l_mfNewOrderConfirmInterceptor = 
                new WEB3MutualFundNewOrderConfirmInterceptor();
            
            //1.28) パラメータのセット
            // 投信新規注文確定インタセプタに受渡日を設定する
            // 投信新規注文確定インタセプタ.set受渡日()をコールする
            l_mfNewOrderConfirmInterceptor.setDeliveryDate(l_tsDeliveryDate);
            
            // 投信新規注文確定インタセプタに注文チャネルを設定する
            // 投信新規注文確定インタセプタ.set注文チャネル()
            l_mfNewOrderConfirmInterceptor.setOrderChannel(
                this.getLoginChannel());
                
            // 投信新規注文確定インタセプタに計算基準価額を設定する
            // 投信新規注文確定インタセプタ.set計算基準価額()をコールする
            if (l_web3MfProduct.isFrgnMmf())
            {
                l_mfNewOrderConfirmInterceptor.setConstantValue(Double.NaN);
            }
            else
            {
                l_mfNewOrderConfirmInterceptor.setConstantValue(
                    l_web3MfProduct.getSellValue());
            }
            // 投信新規注文確定インタセプタに計算基準価額（乗換先）を設定する
            // 投信新規注文確定インタセプタ.set計算基準価額（乗換先）()をコールする
            l_mfNewOrderConfirmInterceptor.setSwitchingConstantValue(Double.NaN);   
            
            // 投信新規注文確定インタセプタに基準価額適用日を設定する
            // 投信新規注文確定インタセプタ.set基準価額適用日()をコールする
            l_mfNewOrderConfirmInterceptor.setConstantValueAppDate(
                l_web3MfProduct.getConstantValueAppDate());
                
            // 投信新規注文確定インタセプタに概算受渡代金を設定する 
            // 投信新規注文確定インタセプタ.set概算受渡代金()をコールする    
            l_mfNewOrderConfirmInterceptor.setEstimatedPrice(
                l_mfEstimatedPrice.getEstimatedPrice());
            
            // 投信新規注文確定インタセプタに概算売買口数を設定する
            // 投信新規注文確定インタセプタ.set概算売買口数()をコールする 
            l_mfNewOrderConfirmInterceptor.setEstimatedQty(
                l_mfEstimatedPrice.getEstimatedQty());
                
            // 投信新規注文確定インタセプタに概算買付口数（乗換先）を設定する。 
            // 投信新規注文確定インタセプタ.set概算買付口数（乗換先）()をコールする。
            l_mfNewOrderConfirmInterceptor.setSwitchingEstimatedQty(Double.NaN);
            
            // 投信新規注文確定インタセプタに税区分（乗換先）を設定する。 
            // 投信新規注文確定インタセプタ.set税区分（乗換先）()をコールする。
            l_mfNewOrderConfirmInterceptor.setSwitchingSubjectTaxDivision(null);
            
            // 投信新規注文確定インタセプタに受渡方法@を設定する。 
            // 投信新規注文確定インタセプタ.set受渡方法@()をコールする。
            l_mfNewOrderConfirmInterceptor.setDeliveryDiv(l_request.deliveryDiv);
            
            // 投信新規注文確定インタセプタに投信タイプを設定する。 
            // 投信新規注文確定インタセプタ.set投信タイプ()をコールする。
            l_mfNewOrderConfirmInterceptor.setMutualFundType(
                l_web3MfProduct.getMutualFundType().intValue() + "");
                
            // 投信新規注文確定インタセプタに投信解約区分を設定する。 
            // 投信新規注文確定インタセプタ.set投信解約区分()をコールする。 
            l_mfNewOrderConfirmInterceptor.setMutualFundSellDiv(l_request.specifyDiv);
            
            // 投信新規注文確定インタセプタに約定日を設定する。 
            // 投信新規注文確定インタセプタ.set約定日()をコールする。
            Timestamp l_tsExecutedDate = new Timestamp(l_datExecutedDate.getTime());
            l_mfNewOrderConfirmInterceptor.setExecutionTimestamp(l_tsExecutedDate);
            
            // 投信新規注文確定インタセプタに決済区分を設定する。
            // 投信新規注文確定インタセプタ.set決済区分()をコールする。
            l_mfNewOrderConfirmInterceptor.setSettlementType(l_request.settleDiv);
            
            // 投信新規注文確定インタセプタに無手数料区分を設定する。 
            // 投信新規注文確定インタセプタ.set無手数料区分()をコールする。
            l_mfNewOrderConfirmInterceptor.setNoCommissionDivision(" ");
            
            // 投信新規注文確定インタセプタに銘柄コード（乗換先）を設定する。 
            // 投信新規注文確定インタセプタ.set銘柄コード（乗換先）()をコールする。
            l_mfNewOrderConfirmInterceptor.setSwitchingSubjectMutualProductCode(null);
            
            // 投信新規注文確定インタセプタに請求区分を設定する。 
            // 投信新規注文確定インタセプタ.set請求区分()をコールする。
            l_mfNewOrderConfirmInterceptor.setRequestDivision(
                l_request.sellBuyDiv);            
            
            // 投信新規注文確定インタセプタに注文経路区分を設定する。 
            // 投信新規注文確定インタセプタ.set注文経路区分()をコールする。
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            l_mfNewOrderConfirmInterceptor.setOrderChannelDivision(
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
            
			//⑲投信新規注文確定インタセプタに出金注文識別コードを設定する。 
			//[set出金注文識別コードに渡すパラメタ] 
			//出金注文識別コード：　@create出金伝票作成（）の戻り値 
			//但し、リクエストデータ.受渡方法@≠銀行振込 の場合、null
            l_mfNewOrderConfirmInterceptor.setPaymentOrderReqNumber(l_strPaymentOrderReqNumber);
            
            //⑳投信新規注文確定インタセプタに一括区分を設定する。 
            //[set一括区分に渡すパラメタ] 
            //　@　@一括区分：　@取得した拡張投信銘柄．is特定日取引銘柄 
            //　@　@[is特定日取引銘柄のパラメタ] 
            //　@　@注文種別 ： OrderTypeEnum．投資信託売注文 
            l_mfNewOrderConfirmInterceptor.setNorealDiv(
                l_web3MfProduct.isUnitTypeProduct(OrderTypeEnum.MF_SELL));
            //21投信新規注文確定インタセプタに注文終了日を設定する。 
            //[set注文終了日に渡すパラメタ] 
            //　@　@注文終了日：　@取得した拡張投信銘柄．getDataSourceObject().get解約乗換終了日()
            MutualFundProductRow l_productRow = 
                (MutualFundProductRow) l_web3MfProduct.getDataSourceObject();
            l_mfNewOrderConfirmInterceptor.setOrderEndDate(l_productRow.getSellSwtEndDate());
            
            // 注文数量タイプ
            QuantityTypeEnum l_qtyTypeEnum = null;
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_qtyTypeEnum = QuantityTypeEnum.QUANTITY;
            }
            else if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_qtyTypeEnum = QuantityTypeEnum.AMOUNT;
            }
            else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_qtyTypeEnum = QuantityTypeEnum.QUANTITY;
            }
            
			//1.29）投信新規注文確定インタセプタを拡張投信注文マネージャに設定する
			l_web3MfOrderMgr.setThreadLocalPersistenceEventInterceptor(
				l_mfNewOrderConfirmInterceptor);           
              
            //1.30）新規注文内容の生成 
            //拡張投信新規注文内容を生成する。
			//[拡張投信新規注文内容のコンストラクタに渡すパラメタ] 
			//代理入力者： this.get代理入力者()の戻り値 
			//is買付： false 
			//銘柄コード： リクエストデータ.銘柄コード 
			//注文数量： 
			//(*) リクエストデータ.指定方法@==”全部” and 
			//is金額指定解約中()の戻り値==true の場合は 
			//0を指定。  
			//(*)それ以外の場合は、 リクエストデータ.数量を指定。 
			//注文数量タイプ： 
			//(*) リクエストデータ.指定方法@が”2：全部”の場合は 
			//QuantityTypeEnum.数量を指定。 
			//(*) リクエストデータ.指定方法@が”3：金額”の場合は 
			//QuantityTypeEnum.金額を指定。 
			//(*) リクエストデータ.指定方法@が”4：口数”の場合は 
			//QuantityTypeEnum.数量を指定。 
			//税区分： 取得した保有資産Params.get税区分()の戻り値
			
			if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) && 
				    l_blnIsPriceDesignateCancelling)
			{
			    l_dblOrderQuantity = 0;
			}
			
            WEB3MutualFundNewOrderSpec l_mfNewOrderSpec = 
                new WEB3MutualFundNewOrderSpec(
                    this.getTrader(),
                    false,
                    l_request.mutualProductCode,
                    l_dblOrderQuantity,
                    l_qtyTypeEnum,
                    l_asset.getTaxType());
                     
            //1.31) 拡張投信注文マネージャ.submitNewOrder()をコールする。
            OrderSubmissionResult l_orderSubmissionResult = 
            l_web3MfOrderMgr.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.MUTUAL_FUND,
                l_mfNewOrderSpec,
                Long.parseLong(l_request.orderId),
                l_request.password,
                true);
                
            //拡張投信注文マネージャ.submitNewOrder()の戻り値判定
            if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                log.debug("Error In submitNewOrder()の戻り値");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.32）余力残高情報更新処理 
            //取引余力サービスを取得し、余力再計算( )をコールする。 
            //[余力再計算に渡す引数] 
            //補助口座：取得した補助口座オブジェクト
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
            
            //1.33）処理日時の取得
            Date l_datProcessDate = null;
            l_datProcessDate = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3MutualFundTradingTimeManagement.TIMESTAMP_TAG);
            
            //1.34）投信解約完了レスポンスオブジェクトを生成 
            l_response = (WEB3MutualSellCompleteResponse) l_request.createResponse();
            
			//1.35) 投信解約完了レスポンスオブジェクトに、以下の値を設定する。
            l_response.lastUpdatedTimestamp = l_datProcessDate;
            l_response.orderActionId = l_request.orderId;
            
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
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate受渡方法@)<BR>
     * 受渡方法@のチェックを実施する。<BR>
     * <BR>
     * １） 補助口座.getMainAccount()をコールして、顧客オブジェクトを取得する。<BR>
     * <BR>
     * ２） 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。 
     *  [条件] 
     *  部店ID = １）で取得した顧客の部店ID
     *  プリファ@レンス名 = "mf.payment.method.check" 
     *  プリファ@レンス名の連番 = 1
     * ３） 取得したレコード.プリファ@レンスの値 == ”受渡方法@チェック不要” の場合、以下の処理を行わず処理を抜ける。
     * <BR>
     * ４） 保有資産オブジェクトを取得する。<BR>
     * <BR>
     *  ［getAssetに渡すパラメタ］<BR>
     *   アセットID： 引数.資産ID<BR>
     * －getAsset()がNotFoundExceptionをスローした場合は、 <BR>
     *     例外をスローする <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00204<BR>
     *  <BR>
     * ５）顧客オブジェクト.getDataSourceObject().get税区分  <BR>
     *    == ”3：特定口座かつ源泉徴収” AND   <BR>
     * 保有資産オブジェクト.getDataSourceObject().get税区分 == ”２：特定口座” <BR>
     *     の場合、例外をスローする。（受渡方法@チェックエラー）<BR>
     *  <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02271<BR>
     * <BR>
     * ６）拡張投信銘柄.is外貨MMF()==trueの場合は、例外をスローする。<BR>
     *  （外貨MMF受渡方法@チェックエラー）<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02732<BR>
     *  <BR>
     * @@param l_subAccount - 補助口座
     * @@param l_strId - 資産ID
     * @@param l_web3MfProduct - (拡張投信銘柄)<BR>
     * 拡張投信銘柄<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40556A8301A4
     */
    public void validatePaymentMethod(SubAccount l_subAccount, String l_strId,
            WEB3MutualFundProduct l_web3MfProduct) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePaymentMethod(SubAccount, String, WEB3MutualFundProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		//１） 補助口座.getMainAccount()をコールして、顧客オブジェクトを取得する。 
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();

		//２） 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。 
		//[条件] 
		//　@部店ID = １）で取得した顧客の部店ID
		//　@プリファ@レンス名 = "mf.payment.method.check" 
		//　@プリファ@レンス名の連番 = 1 
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
            	l_mainAccountRow.getBranchId(), 
                WEB3BranchPreferencesNameDef.MF_PAYMENT_METHOD_CHECK,
                1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
		//３） 取得したレコード.プリファ@レンスの値 == ”受渡方法@チェック不要” の場合、以下の処理を行わず処理を抜ける。
        if(l_branchPreferencesRow !=null && l_branchPreferencesRow.getValue().equals(
		    WEB3MfPaymentMethodCheckDef.NO_CHECK))
        {
        	log.debug("validate受渡方法@ 実施不要");
        	log.exiting(STR_METHOD_NAME);
        	return;
        }

        //４）保有資産オブジェクトを取得する。  
		//［getAssetに渡すパラメタ］  
		//アセットID： 引数.資産ID  
		//－getAsset()がNotFoundExceptionをスローした場合は、例外をスローする。
        //拡張投信ポジションマネージャを取得する
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundPositionManager l_web3MfPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        //1.9)getAsset(long)
        //－投信拡張ポジションマネージャ.getAsset( )をコールする。 
        //　@[引数] 
        //　@リクエスト.ID：資産ID
        AssetRow l_assetRow = null;
        try
        {
           Asset l_asset =l_web3MfPositionMgr.getAsset(Long.parseLong(l_strId));
           l_assetRow = (AssetRow) l_asset.getDataSourceObject();
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("保有資産オブジェクトデータなし。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    
		//５）顧客オブジェクト.getDataSourceObject().get税区分 == ”3：特定口座かつ源泉徴収”　@
        //AND 保有資産オブジェクト.getDataSourceObject().get税区分 == ”２：特定口座”
		//の場合、例外をスローする。 
        
        if (TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mainAccountRow.getTaxType()) &&
            TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()))
        {
            log.error("受渡方法@チェックエラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02271 ,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //６）拡張投信銘柄.is外貨MMF()==trueの場合は、例外をスローする。
        //（外貨MMF受渡方法@チェックエラー）
        if (l_web3MfProduct.isFrgnMmf())
        {
            log.error("外貨MMF受渡方法@チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02732 ,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate出金金額範囲)<BR>
     * 出金金額範囲チェックを行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投信)validate出金金額範囲」参照。<BR>
     * <BR>
     * @@param l_dblEstimatedPrice - 概算受渡代金
     * @@param l_datDeliveryDate - 受渡日
     * @@throws WEB3BaseException
     * @@roseuid 40556A7C0127
     */
    public void validatePaymentMoney(double l_dblEstimatedPrice, Date l_datDeliveryDate) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePaymentMoney(double, Date)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getService(Class)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //1.2 getTradingModule(ProductTypeEnum)
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        //1.2.1 getOrderManager( )    
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_tradingModule.getOrderManager();
        
        //入出金発注審査個別チェックのオブジェクトを取得する。
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
       
        //1.3 get補助口座(SubAccountTypeEnum)
        //[引数] 
        //補助口座タイプ： ”預り金口座” 
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
       
        //1.4 出金金額チェックを行なう。
        //validate出金金額(subAccount, double, Date)
        //[validate出金金額（）に渡すパラメタ] 
    	//補助口座：　@取得した補助口座 
    	//金額：　@引数.概算受渡代金 
    	//受渡日：　@引数.受渡日
        l_reusableValidations.validatePaymentAmount(
            l_subAccount, 
            l_dblEstimatedPrice, 
            l_datDeliveryDate);
       
       log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create出金伝票作成)<BR>
     * 入出金伝票の作成を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投信)create出金伝票作成」参照。<BR>
     * <BR>
     * @@param l_trader - 代理入力者
     * @@param l_dblEstimatedPrice - 概算受渡代金
     * @@param l_tsDeliveryDate - 受渡日
     * @@param l_strPassword - 暗証番号
     * @@Return String
     * @@throws WEB3BaseException
     * @@roseuid 40556A7C0127
     */
    public String createPaymentTicket(
        Trader l_trader, 
        double l_dblEstimatedPrice, 
        Timestamp l_tsDeliveryDate,
        String l_strPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createPaymentTicket(Trader, double, Timestamp, String)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getService(Class)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //1.2 getTradingModule(ProductTypeEnum)
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        //1.2.1 getOrderManager()
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_tradingModule.getOrderManager();
            
        //1.3 get補助口座(SubAccountTypeEnum)
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.4 出金金額チェックを行なう。
        //validate出金金額(subAccount, double, Date)
    	//出金取引金額の範囲を超えていないかチェックする。 
    	//[validate出金金額()に渡すパラメタ] 
    	//補助口座：　@取得した補助口座オブジェクト 
    	//金額：　@引数.概算受渡代金の戻り値 
    	//受渡日：　@引数.受渡日
            
        //入出金発注審査個別チェックのオブジェクトを取得する。
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
            
        l_reusableValidations.validatePaymentAmount(
            l_subAccount, 
            l_dblEstimatedPrice, 
            l_tsDeliveryDate);
        
        //1.5 get商品ID(Institution)
        Institution l_institution = l_subAccount.getInstitution();
        
        //1.6 createNewOrderId( )
       long l_lngOrderId = l_aioOrderManager.createNewOrderId();
       
       //商品ID
       long l_lngProductId = l_aioOrderManager.getProductId(l_institution);
       
        //1.7 入出金注文内容
		//入出金注文内容を生成する。 
		//[入出金注文内容コンストラクタに渡すパラメタ]  
		//代理入力者：　@引数.代理入力者オブジェクト  
		//注文種別：　@1001(出金注文) 
		//振替タイプ：　@2（出金）  
		//商品ID：　@get商品ID()の戻り値  
		//金額：　@引数.概算売買代金 
		//記述：　@null  
		//振替予定日：　@引数.受渡日 
		//決済機@関ID：　@null  
		//注文ID：　@createNewOrderId( )の戻り値 
       WEB3AioNewOrderSpec l_aioNewOrderSpec = 
           new WEB3AioNewOrderSpec(
               l_trader,
               OrderTypeEnum.CASH_OUT,
               AssetTransferTypeEnum.CASH_OUT,
               l_lngProductId,
               l_dblEstimatedPrice,
               null,
               l_tsDeliveryDate,
               null,
               new Long(l_lngOrderId));

       //1.8 入出金注文更新インタセプタ(入出金注文内容)
       WEB3AioCashTransOrderUpdateInterceptor l_interceptor = 
           new WEB3AioCashTransOrderUpdateInterceptor(l_aioNewOrderSpec);
       
       //1.9 set受渡日(Date)
       l_interceptor.setDeliveryDate(l_tsDeliveryDate);
       
       //1.10 営業日計算(Timestamp)
       WEB3GentradeBizDate l_gentradeBizDate =
           new WEB3GentradeBizDate(l_tsDeliveryDate);
       
       //1.11 roll(int)
		Timestamp l_tsBizDate = l_gentradeBizDate.roll(-1);

       //1.12 set発注日(Date)
		l_interceptor.setBizDate(l_tsBizDate);
		
       //1.13 get新規識別コード(String, String, ProductTypeEnum)
		//[get新規識別コード()に渡すパラメタ]  
		// 証券会社コード： 補助口座から取得した証券会社コード  
		// 部店コード： 補助口座から取得した部店コード  
		// 銘柄タイプ： 5（現金） （ProductTypeEnum) 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        //(2)
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_institution.getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.CASH);
        log.debug("get新規識別コード() = " + l_strNewNumber);
        

        //1.14 set識別コード(String)
        l_interceptor.setOrderRequestNumber(l_strNewNumber);
        
        //1.15 set出金申込区分(String)
        l_interceptor.setPaymentApplicationDiv(WEB3AioPaymentApplicationDivDef.MF);
        
        //1.16 setThreadLocalPersistenceEventInterceptor(AioOrderManagerPersistenceEventInterceptor)
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //1.17 submitNewOrder
		//[submitNewOrderに渡すパラメタ] 
		//補助口座：　@取得した補助口座オブジェクト 
		//銘柄タイプ： ProductTypeEnum.5：現金 
		//新規注文内容： 生成した入出金注文内容 
		//注文ID： 取得した注文ID 
		//取引パスワード： リクエストデータ.暗証番号 
		//is発注審査省略： true
        OrderSubmissionResult l_orderSubmissionResult =
            l_aioOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_aioNewOrderSpec,
                l_lngOrderId,
                l_strPassword,
                true);
        
        //拡張投信注文マネージャ.submitNewOrder()の戻り値判定
        if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
        {
            log.debug("新規注文失敗");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "新規注文失敗");
        }
        
        //1.18 識別コード
        //get新規識別コード()の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strNewNumber;
    }
}
@
