head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消サービス実装クラス(WEB3MutualCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
Revesion History : 2004/08/20 韋念瓊 (中訊) レビュー  
Revesion History : 2004/12/10 于美麗 (中訊) 残対応  
Revesion History : 2005/10/21 黄建 (中訊) フィデリティ対応
Revesion History : 2006/10/16 周捷 仕様変更・モデル508
Revesion History : 2006/10/24 徐大方 仕様変更・モデル514
Revesion History : 2007/02/09 張騰宇 (中訊) 仕様変更・モデル540
Revesion History : 2007/03/26 徐大方 (中訊) 仕様変更・モデル553
Revesion History : 2007/04/06 唐性峰 (中訊) 実装005
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.aio.WEB3AioCashoutCancelUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3MfRecruitMqSendDivDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundCancelConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualCancelCompleteRequest;
import webbroker3.mf.message.WEB3MutualCancelCompleteResponse;
import webbroker3.mf.message.WEB3MutualCancelConfirmRequest;
import webbroker3.mf.message.WEB3MutualCancelConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualCancelService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託取消サービス実装クラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualCancelServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualCancelService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelServiceImpl.class);

    /**
     * 投資信託取消サービス処理を実施する。<BR>
     * <BR>
     * メッセージクラスの型により、validate取消()メソッド、<BR>
     * submit取消()メソッドのいずれかをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 405575350108
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3MutualCancelConfirmRequest)
        {
            //validate取消()メソッド
            l_response =
                this.validateCancel((WEB3MutualCancelConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3MutualCancelCompleteRequest)
        {
            //submit取消()メソッド
            l_response =
                this.submitCancel((WEB3MutualCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                STR_METHOD_NAME
                    + " __Request "
                    + " WEB3MutualCancelCompleteRequest "
                    + " と WEB3MutualCancelConfirmRequest以外である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate取消)<BR>
     * 投資信託取消審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投資信託)取消審査」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 405575AC005C
     */
    protected WEB3MutualCancelConfirmResponse validateCancel(WEB3MutualCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancel(WEB3MutualCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@入力内容チェック
        l_request.validate();

        //２）　@補助口座取得
        SubAccount l_subAccount = this.getSubAccount();

        //３）　@注文単位オブジェクトの取得
        //－拡張注文マネージャの取得 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            
        long l_lngRequestId = 0;    
        if (WEB3StringTypeUtility.isNumber(l_request.id))
        {
            l_lngRequestId = Long.parseLong(l_request.id);
        }        

        MutualFundOrderUnitParams l_mfOrderUnitParams = null;

        //－拡張注文マネージャ.getOrderUnits()をコールし、
        OrderUnit l_orderUnit[] =
            l_orderManager.getOrderUnits(l_lngRequestId);

        //投資信託注文単位Paramsを取得する。
        l_mfOrderUnitParams =
            new MutualFundOrderUnitParams(
                (MutualFundOrderUnitRow) l_orderUnit[0].getDataSourceObject());

        //４）　@投信銘柄取得
        //－拡張投信銘柄マネージャを取得する
        WEB3MutualFundProductManager l_produceManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_mfProduct = null;
        try
        {
            //－拡張投信銘柄マネージャ.getProduct()をコールし、
            // 銘柄オブジェクトを取得する
            l_mfProduct =
                (WEB3MutualFundProduct) l_produceManager.getProduct(
                    l_mfOrderUnitParams.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when "
                    + "l_produceManager.getProduct(ProductId) with "
                    + "ProductId = "
                    + l_mfOrderUnitParams.getProductId());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //－銘柄コードの取得
        //拡張投信銘柄.get銘柄コードをコールし、銘柄コードを取得する
        String l_strProductCode = l_mfProduct.getProductCode();

        // 　@－銘柄名の取得 
        //　@　@拡張投信銘柄.get銘柄名()をコールし、銘柄名を取得する
        String l_strProductName = l_mfProduct.getMutualProductName();

        // 　@－通貨コードの取得<BR>
        // 　@　@拡張投信銘柄.get通貨コード()をコールし、通貨コードを取得する
        String l_strProductCurrencyCode = l_mfProduct.getCurrencyCode();

        //FinAppクラスのgetCommonOrderValidator()をコールし
        //注文チェックオブジェクトを取得する      
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        // ６）　@受付時間チェック、システム取引停止チェック
        //－投信取引時間管理.validate注文受付可能()をコール
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        // 取引時間の再設定 
        // ７）投信取引時間管理.reset銘柄コード()をコールし、
        WEB3MutualFundTradingTimeManagement.resetProductCode(l_strProductCode);

        // ８）受付日時、日付ロールをセットする
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
        // ９）取引時間管理.get投信発注日()をコールし、確認処理時の発注日を取得する
        //Date l_datBizDate = 
        //   WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
          
        String l_strMutualTradeDiv = null;

        // １０）売買区分（投信）の取得
        // 　@－拡張投信注文マネージャ.get売買区分（投信）( )をコール
        MutualFundOrderUnit l_mfOrderUnit = 
            (MutualFundOrderUnit) l_orderManager.toOrderUnit(l_mfOrderUnitParams);
        l_strMutualTradeDiv =
            l_orderManager.getMutualTradeDiv(l_mfOrderUnit);
        
		// １１）投信発注日を取得する 
        OrderTypeEnum l_orderTypeEnum = null;
        if (WEB3ProcessDivDef.BUY.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_BUY;
        }
        else if (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SELL;
        }
        else if (WEB3ProcessDivDef.RECRUIT.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_RECRUIT;
        }
        else if (WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SWITCHING;
        }
        boolean l_blnNorealDiv = false;
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_mfOrderUnit.getBranchId(), 
                WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
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
                
        boolean l_blnValue;
        if (l_branchPreferencesRow == null || WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_branchPreferencesRow.getValue()))
        {
            l_blnValue = true;
        }
        else
        {
            l_blnValue = false;
        }
        if ((WEB3ProcessDivDef.RECRUIT.equals(l_strMutualTradeDiv) && l_blnValue)
            || (WEB3ProcessDivDef.BUY.equals(l_strMutualTradeDiv) && 
                l_mfProduct.isUnitTypeProduct(l_mfOrderUnit.getOrderType()))
            || (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv) && 
                l_mfProduct.isUnitTypeProduct(l_mfOrderUnit.getOrderType())))
        {
            l_blnNorealDiv = true;
        }
        //特定日取引銘柄の場合の発注日取得 
        Date l_datBizDate = null;
        long l_lngUnitProductId = l_mfProduct.getProductId();
        if (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv) && 
            l_mfProduct.isUnitTypeProduct(l_mfOrderUnit.getOrderType()) &&
            (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L))
        {
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow) l_mfProduct.getDataSourceObject();
            l_datBizDate = l_mfProductRow.getSellSwtEndDate();
        }
        //特定日取引銘柄以外の場合の発注日取得 
        else
        {
            l_datBizDate =
                WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(l_orderTypeEnum, l_blnNorealDiv);
        }

        Timestamp l_datBizDateValidate = 
			new Timestamp(l_datBizDate.getTime());

        // １２）顧客別取引停止属性チェック
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

        // １３）乗換先の投信銘柄取得
        //　@－拡張投信銘柄マネージャ.get投信銘柄()をコールし、拡張投信銘柄を取得する
        WEB3MutualFundProduct l_swtProduct = null;
        String l_strSwtProductName = null;
        String l_strSwtCurrencyCode = null;
        if(WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv))
        {
            try
            {
                //－拡張投信銘柄マネージャ.getProduct()をコールし、
                // 銘柄オブジェクトを取得する
                l_swtProduct =
                    (WEB3MutualFundProduct) l_produceManager.getMutualFundProduct(
                        l_subAccount.getInstitution(),
                        l_mfOrderUnitParams.getSwtProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error(
                    "__NotFoundException__ when "
                        + " l_produceManager.getMutualFundProduct() with "
                        + " Institution = "
                        + l_subAccount.getInstitution().getInstitutionCode()
                        + " ProductCode = "
                        + l_mfOrderUnitParams.getSwtProductCode());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            // 　@－銘柄名の取得 
            // 　@　@拡張投信銘柄.get銘柄名()をコールし、銘柄名を取得する
            l_strSwtProductName = l_swtProduct.getMutualProductName();

            // 　@－通貨コードの取得 <BR>
            // 　@　@拡張投信銘柄.get通貨コード()をコールし、通貨コードを取得する
            l_strSwtCurrencyCode = l_swtProduct.getCurrencyCode();
        }

        // １４）投信取消注文内容オブジェクトを生成する
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngRequestId);

        // １５）発注審査 
        //－拡張投信注文マネージャ.validate取消注文()をコール
        OrderValidationResult l_cancelValidationResult =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_cancelValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("発注審査チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_cancelValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注審査チェックエラー");
        }

        // １６）　@投信注文取消確認レスポンスオブジェクトを生成し、リターンする
        WEB3MutualCancelConfirmResponse l_response = (WEB3MutualCancelConfirmResponse) l_request.createResponse();

        // 　@投信注文取消確認レスポンスオブジェクトを設定する        
        //　@1. 銘柄名<BR>  →　@４）で取得した銘柄名
        l_response.mutualProductName = l_strProductName;

        //　@2. 計算基準価額通貨コード　@→　@４）で取得した通貨コード
        l_response.constantValueCurrencyCode = l_strProductCurrencyCode;

        //3. 計算基準価額
        //投信注文単位Params．get投信タイプ() =外貨MMFの場合 → null
        //それ以外の場合 →　@投信注文単位Params．get計算基準価額()の戻り値
        if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mfOrderUnitParams.getFundType()))
        {
            l_response.constantValue = null;
        }
        else
        {
            l_response.constantValue =
                WEB3StringTypeUtility.formatNumber(
                    l_mfOrderUnitParams.getCalcConstantValue());
        }

        //　@4. 基準価額適用日　@→　@投信注文単位Params．get基準価額適用日()の戻り値
        l_response.constantValueAppDate =
            WEB3DateUtility.toDay(l_mfOrderUnitParams.getConstantValueAppDate());

        //　@5. 口座区分　@→　@(以下の手順で取得する)<BR>
        //        -start---------------------------------------------<BR>
        //          a)　@引数:投信注文単位.get税区分( )をコールする。<BR>
        //          a-1)　@戻り値が"1:一般"の場合、"0:一般"をセット。<BR>
        //          a-2)　@戻り値が"2:特定"または"3:特定口座かつ源泉徴収"の場合、<BR>
        //              "1:特定"をセット。<BR>
        //        -end-----------------------------------------------<BR> 
        
        if (TaxTypeEnum.NORMAL.equals(l_mfOrderUnitParams.getTaxType()))
        {
            l_response.taxType =
                WEB3MFAccountDivDef.NORMAL;
        }
        else
        {
            if (TaxTypeEnum.SPECIAL.equals(l_mfOrderUnitParams.getTaxType())
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mfOrderUnitParams.getTaxType()))
            {
                l_response.taxType =
                    WEB3MFAccountDivDef.SPECIAL;
            }
            else if (TaxTypeEnum.UNDEFINED.equals(l_mfOrderUnitParams.getTaxType()))
            {
                l_response.taxType = WEB3MFAccountDivDef.OTHER;
            }
        }

        //　@6. 売買区分（投信）　@→　@８）で取得した売買区分
        l_response.mutualDealingType = l_strMutualTradeDiv;

        //　@7. 請求方法@　@→　@投信注文単位Params.get請求区分()の戻り値
        l_response.sellBuyDiv = l_mfOrderUnitParams.getRequestDiv();

        //　@8. 指定方法@　@→　@（以下の手順でセットする）<BR>
        //○投信注文単位Params．get注文種別( )の戻り値="201：投資信託買注文"
        //または　@"203：投資信託募集注文"の場合 
        //  投信注文単位Params.get数量タイプ( )="数量"の場合、"口数指定"をセット。 
        //  投信注文単位Params.get数量タイプ( )="金額"の場合、"金額指定"をセット。 
        //○上記以外の場合、投信注文単位Params．get投信解約区分()の戻り値をセットする。
        if (OrderTypeEnum.MF_BUY.equals(l_mfOrderUnitParams.getOrderType()) ||
            OrderTypeEnum.MF_RECRUIT.equals(l_mfOrderUnitParams.getOrderType()))
        {
            if (QuantityTypeEnum.QUANTITY.equals(l_mfOrderUnitParams.getQuantityType()))
            {
                l_response.specifyDiv = WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE;
            }
            else if (QuantityTypeEnum.AMOUNT.equals(l_mfOrderUnitParams.getQuantityType()))
            {
                l_response.specifyDiv = WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE;
            }           
        }
        else
        {
            l_response.specifyDiv = l_mfOrderUnitParams.getFundSellDiv();
        }

        //　@9. 注文数量区分　@→　@拡張投信注文マネージャ.get注文数量区分()の戻り値
        l_response.mutualOrderQuantityType =
            l_orderManager.getOrderQuantityDiv(l_mfOrderUnit);

        //　@10. 注文数量　@→　@投信注文単位Params.get注文数量()の戻り値
        l_response.mutualOrderQuantity =
            WEB3StringTypeUtility.formatNumber(
                l_mfOrderUnitParams.getQuantity());        
        if (l_mfOrderUnitParams.getSwtProductCode() == null || "".equals(l_mfOrderUnitParams.getSwtProductCode()))
        {
            //  11. 概算受渡代金通貨コード　@→　@投信注文単位.get決済区分()が「円貨」の場合
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_mfOrderUnitParams.getSettlementDiv()))
            {
                // "T0"をセットする
                l_response.estimatedPriceCurrencyCode = WEB3MFOrderQuantityType.EN;
            }
            else
            {
                //４）で取得した通貨コードをセットする
                l_response.estimatedPriceCurrencyCode = l_strProductCurrencyCode;
            }

            //　@12. 概算受渡代金　@→ 投信注文単位Params．get概算受渡代金()の戻り値
            l_response.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_mfOrderUnitParams.getEstimatedPrice());

            //　@13. 概算売買口数　@→ 投信注文単位Params．get概算売買口数()の戻り値
            l_response.estimatedQty =
            WEB3StringTypeUtility.formatNumber(
            l_mfOrderUnitParams.getEstimateDealingQty());

            // 14. 乗換取消時以外null
            l_response.switchingProductName = null;

            // 15. 乗換取消時以外nulｌ
            l_response.switchingConstantValueCurrencyCode = null;

            // 16. 乗換取消時以外null
            l_response.switchingConstantValue = null;

            // 17. 乗換取消時以外null
            l_response.switchingTaxType = null;

            // 18. 乗換取消時以外null
            l_response.switchingEstimatedQty = null;

            //　@19. 決済方法@　@→　@投信注文単位Params．get決済区分()の戻り値
            l_response.settleDiv = l_mfOrderUnitParams.getSettlementDiv();

            //　@20. 受渡方法@　@→　@投信注文単位Params．get受渡方法@()の戻り値
            l_response.deliveryDiv = l_mfOrderUnitParams.getPaymentMethod();
        }
        else
        {
            //  11. 乗換取消時null
            l_response.estimatedPriceCurrencyCode = null;

            //  12. 乗換取消時null
            l_response.estimatedPrice = null;
            
            //  13. 乗換取消時null
            l_response.estimatedQty = null;

            //　@14. 銘柄名（乗換先）　@→　@９）で取得した銘柄名
            l_response.switchingProductName = l_strSwtProductName;

            //　@15. 計算基準価額通貨コード（乗換先）　@→　@９）で取得した通貨コード
            l_response.switchingConstantValueCurrencyCode = l_strSwtCurrencyCode;

            //　@16. 計算基準価額（乗換先）　@→　@投信注文単位Params．get計算基準価額（乗換先）()の戻り値
            l_response.switchingConstantValue = WEB3StringTypeUtility.formatNumber(
            l_mfOrderUnitParams.getSwtCalcConstantValue());

            //　@17. 口座区分（乗換先）　@→　@(以下の手順で取得する)<BR>
            //        -start---------------------------------------------
            //         b)　@投信注文単位.getDataSourceObject( ).get税区分（乗換先）( )をコールする。<BR>
            //         b-1)　@戻り値が"1:一般"の場合、"0:一般"をセット。<BR>
            //         b-2)　@戻り値が"2:特定"または"3:特定口座かつ源泉徴収"の場合、"1:特定"をセット。<BR>
            //        -end-----------------------------------------------
                
            if(TaxTypeEnum.NORMAL.equals(l_mfOrderUnitParams.getSwtTaxType()))
            {
                l_response.switchingTaxType =
                    WEB3TaxTypeSpecialDef.NORMAL;
            }
            else
            {
                if (TaxTypeEnum.SPECIAL.equals(l_mfOrderUnitParams.getSwtTaxType())
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mfOrderUnitParams.getSwtTaxType()))
                {
                    l_response.switchingTaxType =
                        WEB3TaxTypeSpecialDef.SPECIAL;
                }
            }

            //　@18. 概算買付口数（乗換先）　@→　@投信注文単位Params．get概算買付口数（乗換先）
            l_response.switchingEstimatedQty = 
            WEB3StringTypeUtility.formatNumber(
            l_mfOrderUnitParams.getSwtEstimateDealingQty());

            // 19. 乗換取消時null
            l_response.settleDiv = null;

            // 20.乗換取消時null
            l_response.deliveryDiv = null;
        }

        //　@21. 発注日　@→　@注文単位Params．getBizDate()の戻り値
        l_response.orderBizDate =
            WEB3DateUtility.getDate(
                l_mfOrderUnitParams.getBizDate(),
                "yyyyMMdd");

        //　@22. 約定日　@→　@投信注文単位Params．get約定日()の戻り値
        l_response.executionTimestamp = WEB3DateUtility.toDay(
            l_mfOrderUnitParams.getExecDate());

        //　@23. 受渡日　@→　@投信注文単位Params．get受渡日()の戻り値
        l_response.deliveryDate = WEB3DateUtility.toDay(
            l_mfOrderUnitParams.getDeliveryDate());

        //　@24. 確認時発注日　@→　@１２）で取得した発注日
        l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);

        return l_response;
    }

    /**
     * (submit取消)<BR>
     * 投資信託取消登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投資信託)取消登録」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 405575B50166
     */
    protected WEB3MutualCancelCompleteResponse submitCancel(WEB3MutualCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitCancel(WEB3MutualCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1　@入力内容チェック
        l_request.validate();

        //1.2　@補助口座取得
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3　@注文単位オブジェクトの取得
        //－拡張注文マネージャの取得 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        long l_lngRequestId = 0;    
        if (WEB3StringTypeUtility.isNumber(l_request.id))
        {
            l_lngRequestId = Long.parseLong(l_request.id);
        } 

        MutualFundOrderUnitParams l_mfOrderUnitParams = null;
        
        //－拡張注文マネージャ.getOrderUnits()をコールし、
        OrderUnit l_orderUnits[] =
            l_orderManager.getOrderUnits(l_lngRequestId);

        //投資信託注文単位Paramsを取得する。
        l_mfOrderUnitParams =
            new MutualFundOrderUnitParams(
                (MutualFundOrderUnitRow) l_orderUnits[0].getDataSourceObject());

        //1.4　@投信銘柄取得
        //－拡張投信銘柄マネージャを取得する
        WEB3MutualFundProductManager l_produceManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_mfProduct = null;
        try
        {
            //－拡張投信銘柄マネージャ.getProduct()をコールし、
            // 銘柄オブジェクトを取得する
            l_mfProduct =
                (WEB3MutualFundProduct) l_produceManager.getProduct(
                    l_mfOrderUnitParams.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when "
                    + "l_produceManager.getProduct(ProductId) with "
                    + "ProductId = "
                    + l_mfOrderUnitParams.getProductId());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        //－銘柄コードの取得
        //拡張投信銘柄.get銘柄コードをコールし、銘柄コードを取得する
        String l_strProductCode = l_mfProduct.getProductCode();
        
        // 1.6　@顧客別取引停止属性チェック
        //FinAppクラスのgetCommonOrderValidator()をコールし
        //注文チェックオブジェクトを取得する      
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        // 1.7 get代理入力者( )
        Trader l_trader = this.getTrader();
        
        // 1.8 －validate取引パスワード( )をコールする。  
        OrderValidationResult l_validationResultPassword = 
            l_orderValidator.validateTradingPassword(
                l_trader,
                l_subAccount,
                l_request.password);
        
        if (l_validationResultPassword.getProcessingResult().isFailedResult())
        {
            log.debug("取引パスワードが不正です");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引パスワードが不正です");
        }

        // 1.9　@受付時間チェック、システム取引停止チェック
        //－投信取引時間管理.validate注文受付可能()をコール
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        // 1.10 取引時間の再設定 
        // 　@－投信取引時間管理.reset銘柄コード()をコールし、
        WEB3MutualFundTradingTimeManagement.resetProductCode(l_strProductCode);

        // 1.11 －受付日時、日付ロールをセットする
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
        // 1.12 －注文チェック.validate取引可能顧客()をコールする
        //発注日取得         
        Timestamp l_datBizDate = 
            new Timestamp(l_request.checkDate.getTime());
        
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
        // 1.13 売買区分（投信）の取得 
        MutualFundOrderUnit l_orderUnit = 
            (MutualFundOrderUnit) l_orderManager.toOrderUnit(l_mfOrderUnitParams);
        String l_strMutualTradeDiv = l_orderManager.getMutualTradeDiv(l_orderUnit);

        // 1.14　@投信取消注文内容オブジェクトを生成する
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngRequestId);

        // 1.15　@発注審査 
        //－拡張投信注文マネージャ.validate取消注文()をコール
        OrderValidationResult l_cancelValidationResult =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_cancelValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("発注審査チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_cancelValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注審査チェックエラー");
        }

        // 1.16　@発注日取得 
        OrderTypeEnum l_orderTypeEnum = null;
        if (WEB3ProcessDivDef.BUY.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_BUY;
        }
        else if (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SELL;
        }
        else if (WEB3ProcessDivDef.RECRUIT.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_RECRUIT;
        }
        else if (WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SWITCHING;
        }
        boolean l_blnNorealDiv = false;
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_orderUnit.getBranchId(), 
                WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
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
               
        boolean l_blnValue;
        if (l_branchPreferencesRow == null || WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_branchPreferencesRow.getValue()))
        {
            l_blnValue = true;
        }
        else
        {
            l_blnValue = false;
        }
        if ((WEB3ProcessDivDef.RECRUIT.equals(l_strMutualTradeDiv) && l_blnValue)
            || (WEB3ProcessDivDef.BUY.equals(l_strMutualTradeDiv) && 
                l_mfProduct.isUnitTypeProduct(l_orderUnit.getOrderType()))
            || (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv) && 
                l_mfProduct.isUnitTypeProduct(l_orderUnit.getOrderType())))
        {
            l_blnNorealDiv = true;
        }
        // 1.16.1）発注日の取得　@(*)特定日取引銘柄の場合
        long l_lngUnitProductId = l_mfProduct.getProductId();
        Date l_datOrderBizDate = null;
        if (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv) && 
            l_mfProduct.isUnitTypeProduct(l_orderUnit.getOrderType()) &&
            (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L))
        {
            // 1.16.1.1）　@get特定日取引銘柄解約発注日(Date)
            //[get発注日に渡すパラメタ]  
            // 確認時発注日：　@リクエストデータ.発注日 
            l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getUnitTypeProductSellOrderBizDate(
                l_request.checkDate);
        }
        //　@1.16.2）発注日の取得　@(*)特定日取引銘柄以外の場合
        else
        {
            WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                l_request.checkDate, 
                l_orderTypeEnum, 
                l_blnNorealDiv);
        }

        // 1.17　@投信取消確定インタセプタを生成する
        WEB3MutualFundCancelConfirmInterceptor l_cancelInterceptor =
            new WEB3MutualFundCancelConfirmInterceptor();

        // 1.18　@投信取消確定インタセプタを投信注文マネージャに設定する
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_cancelInterceptor);

        // 1.19　@注文取消
        //　@－拡張投信注文マネージャ.submitCancelOrder()をコールする
        OrderSubmissionResult l_submissionResult =
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        if (l_submissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("注文取消失敗である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00394,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文取消失敗である");
        }
        
		//1.20 投信注文単位.注文種別 == ”投信売注文” and
		//投信注文単位.受渡方法@ == ”銀行振込” and
		//補助口座.get取引店().is投信解約時出金注文生成() == true の場
        WEB3GentradeBranch l_branch = 
            (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        if (OrderTypeEnum.MF_SELL.equals(l_orderUnit.getOrderType()) &&
            WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_mfOrderUnitParams.getPaymentMethod()) &&
            l_branch.isPaymentOrderCreate())
        {
            //1.20.1 出金注文取消(String, String)
			//[引数] 
			// 識別コード： 投信注文単位.出金注文識別コード 
			// パスワード： リクエストデータ.暗証番号 
            this.paymentOrderCancel(
                l_mfOrderUnitParams.getPaymentOrderReqNumber(), 
                l_request.password);
        }
        
        // 1.21　@取引余力更新処理 
        //取引余力サービスを取得し、余力再計算( )をコールする。 
        //[余力再計算に渡す引数] 
        //補助口座：取得した補助口座オブジェクト
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
        
		// getAttribute(String)
		Date l_date = null;            
		l_date = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
			WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);                
		log.debug("WEB3MutualBuyServiceImpl.submitCancelOrder::l_date = " + l_date);       

        // 1.22　@投信注文取消完了レスポンスオブジェクトを生成し、リターンする
        WEB3MutualCancelCompleteResponse l_response = 
            (WEB3MutualCancelCompleteResponse) l_request.createResponse();
        
        //投信注文取消完了レスポンスオブジェクトを設定する
        l_response.lastUpdatedTimestamp =l_date;
        l_response.orderActionId = Long.toString(
            l_mfOrderUnitParams.getOrderId());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (出金注文取消)<BR>
     * 出金注文の取消を行う。
     * <BR>
     * シーケンス図「（投信）出金注文取消」参照。<BR>
     * <BR>
     * @@param l_strRequestNumber - 識別コード 
     * @@param l_strPassWord - パスワード
     * @@throws WEB3BaseException
     * @@roseuid 405575350108
     */
    protected void paymentOrderCancel(String l_strRequestNumber, String l_strPassWord) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "paymentOrderCancel(String l_strRequestNumber, String l_strPassWord)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 get補助口座(SubAccountTypeEnum)
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
		//以下の条件に該当する入出金の注文単位を取得する。
		//[条件]
		//口座ID = 取得した補助口座オブジェクト.getAccountId()の戻り値
		//補助口座ID = 取得した補助口座オブジェクト.getSubAccountId()の戻り値
		//注文種別 = ”出金注文”
		//出金申込区分 = ”投信解約”
		//識別コード = 引数.識別コード
		//注文有効状態 = ”OPEN”
		//※取得できなかった場合は、例外をスローする。
        
        List l_lisRows = new ArrayList();

        String l_strWhere = 
            "account_id = ? and sub_account_id = ? and order_type = ? " +
            "and payment_application_div = ? and order_request_number = ? and order_open_status = ?";
        
        Object[] l_bindVars = {
            new Long(l_subAccount.getAccountId()),
            new Long(l_subAccount.getSubAccountId()),
            OrderTypeEnum.CASH_OUT, 
            WEB3AioPaymentApplicationDivDef.MF, 
            l_strRequestNumber, 
            OrderOpenStatusEnum.OPEN};   
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                AioOrderUnitRow.TYPE,
                l_strWhere,
                null, 
                l_bindVars);
        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        
        if(l_lisRows == null || l_lisRows.size() == 0)
        {
            //※取得できなかった場合は、例外をスローする。
            log.debug("入出金注文単位が取得できなかった場合。"); 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        AioOrderUnitParams l_aioOrderUnitParams = (AioOrderUnitParams)l_lisRows.get(0);		
                
        //1.3 CancelOrderSpec(注文ID : long)
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_aioOrderUnitParams.getOrderId());
        
        //1.4 出金取消更新インタセプタ( )
        WEB3AioCashoutCancelUpdateInterceptor l_interceptor = new WEB3AioCashoutCancelUpdateInterceptor();
        
        //1.5 setThreadLocalPersistenceEventInterceptor(インタセプタ : AioOrderManagerPersistenceEventInterceptor)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //1.6 submitCancelOrder
		//[引数] 
		// 補助口座： 取得した補助口座オブジェクト 
		// 取消注文内容： 取消注文内容オブジェクト 
		// パスワード： 引数.パスワード 
		// isSkip発注審査： true 
        OrderSubmissionResult l_submitCancelOrderResult = 
            l_aioOrderManager.submitCancelOrder(
                l_subAccount, 
                l_cancelOrderSpec,
                l_strPassWord,
                true);
        
        if (l_submitCancelOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("submitCancelOrder Error" +
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        log.debug(STR_METHOD_NAME);
    }
}
@
