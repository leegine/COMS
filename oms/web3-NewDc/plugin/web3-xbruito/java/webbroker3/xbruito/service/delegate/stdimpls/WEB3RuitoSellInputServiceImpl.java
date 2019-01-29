head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投解約入力サービス実装クラス(WEB3RuitoSellInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 李志強 (中訊) 新規作成
                   2004/12/06 韋念瓊 (中訊) 残対応
*/

package webbroker3.xbruito.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoAsset;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoAssetDetail;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoOrderManagerReusableValidationsCheck;
import webbroker3.xbruito.WEB3RuitoPositionManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.message.WEB3RuitoSellInputRequest;
import webbroker3.xbruito.message.WEB3RuitoSellInputResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellInputService;

/**
 * 累投解約入力サービス実装クラス<BR>
 */
public class WEB3RuitoSellInputServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoSellInputService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellInputServiceImpl.class);

    /**
     * 累積投資解約注文入力サービス処理を実施する。<BR>
     * シーケンス図「（累投）解約入力」参照<BR>
     * <BR>
     * 1.3 取引可能顧客チェックを実施し、エラーの場合は例外をスローする 
     *      classpath:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00275<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 407627ED0151
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
		log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当パラメータにNull値は設定できません。");
        }


		WEB3RuitoSellInputRequest l_ruitoSellInputRequset = null;
		if (l_request instanceof WEB3RuitoSellInputRequest)
		{
			l_ruitoSellInputRequset = (WEB3RuitoSellInputRequest) l_request;
		}
		else
		{
			log.debug(STR_METHOD_NAME + " __Error[入力値が不正です]__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80018,
			    this.getClass().getName() + "." + STR_METHOD_NAME);			
		}
	
		l_ruitoSellInputRequset.validate();
		
        //1.1 補助口座取得
        SubAccount l_subAccount = getSubAccount();
        
        //1.2　@顧客別取引停止属性チェック
        //－FinApp.getCommonOrderValidator()をコールし、注文チェックオブジェクトを取得する。
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        //1.3 －注文チェック.validate取引可能顧客()をコールする。
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        //－チェックエラーの場合はを例外をスローする。
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引停止顧客エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "取引停止顧客チェックエラー");
        }        
        
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();

        RuitoProduct l_ruitoProduct = null;
        RuitoAsset l_ruitoAsset = null;
        WEB3RuitoProduct l_web3RuitoProduct = null;

        //銘柄ID
        long l_lngProductId = 0;
        //銘柄名
        String l_strProductName = null;
        //累投タイプ
        RuitoTypeEnum l_ruitoTypeEnum = null;
        //指定方法@
        String l_strPaymentMethodSell = null;
        //受渡方法@
        String l_strPaymentMethod = null;

        WEB3RuitoPositionManager l_web3RuitoPositionManager = null;

        try
        {
            //1.4 累投銘柄オブジェクトを取得する 
            l_ruitoProduct =
                l_web3RuitoProductManager.getRuitoProduct(
                    l_subAccount.getInstitution(),
                    l_ruitoSellInputRequset.ruitoProductCode,
                    "0");
                    
            l_web3RuitoProduct = (WEB3RuitoProduct) l_ruitoProduct;
            
            //1.5 累投銘柄より累投タイプを取得する
            l_ruitoTypeEnum = l_web3RuitoProduct.getRuitoType();
            log.debug("累投タイプ = " + l_ruitoTypeEnum);
            
            //1.6 累投銘柄より銘柄IDを取得する
            l_lngProductId = l_web3RuitoProduct.getProductId();
            log.debug("銘柄ID = " + l_lngProductId);
            
            //1.7 累投銘柄より銘柄名を取得する
            l_strProductName = l_web3RuitoProduct.getProductName();
			log.debug("銘柄名 = " + l_strProductName);

            //1.8 累投銘柄より指定方法@を取得する
            l_strPaymentMethodSell = l_web3RuitoProduct.getPaymentMethodSell();
			log.debug("指定方法@ = " + l_strPaymentMethodSell);
            
            //1.9 累投銘柄より受渡方法@を取得する
            l_strPaymentMethod = l_web3RuitoProduct.getPaymentMethod();
			log.debug("受渡方法@ = " + l_strPaymentMethod);

            //受付時間チェック、システム取引停止チェック
            if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum))
            {
            	log.debug("RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum)");
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                    WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
                WEB3GentradeTradingTimeManagement.setTimestamp();
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            else if (RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum))
            {
				log.debug("RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum)");
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                    WEB3TradingTimeTypeDef.MMF_SET_CANCEL);
                WEB3GentradeTradingTimeManagement.setTimestamp();
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }

            //1.10 取引可能銘柄チェック
            WEB3RuitoOrderManagerReusableValidationsCheck l_ruitoOrderManagerReusableValidCheck =
                new WEB3RuitoOrderManagerReusableValidationsCheck();

            l_ruitoOrderManagerReusableValidCheck.validateTradedProduct(
                l_web3RuitoProduct,
                false);
			
            //1.12 指定銘柄の保有資産情報を取得する 
            l_web3RuitoPositionManager =
                (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getPositionManager();
            
            l_ruitoAsset =
                (RuitoAsset)l_web3RuitoPositionManager.getAsset(
                    l_subAccount, l_ruitoProduct);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
			log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (OrderValidationException l_ex)
        {
			log.error("__OrderValidationException__", l_ex);
			log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getValidationResult().toString(),
                l_ex);
        }

        //1.13 累投保有資産明細取得
        AssetRow l_ruitoAssetRow =
            (AssetRow) l_ruitoAsset.getDataSourceObject();
        double l_dblCountBeforePenalty = l_ruitoAssetRow.getCountBeforePenalty();
		log.debug("l_dblCountBeforePenalty = " + l_dblCountBeforePenalty);

        WEB3RuitoAssetDetail l_web3RuitoAssetDetail =
            new WEB3RuitoAssetDetail(
                l_ruitoProduct.getProductId(),
                l_ruitoAsset.getQuantity(),
                l_dblCountBeforePenalty,
                l_ruitoTypeEnum);

        l_web3RuitoPositionManager.getRuitoAssetGroup(
            l_subAccount,
            l_web3RuitoAssetDetail);

        //累投解約注文入力レスポンスオブジェクト生成

        // Modify by Alan Wang 2004/08/11 according to Bug#:  70  ---------- Begin
//      WEB3RuitoSellInputResponse l_response = new WEB3RuitoSellInputResponse();

        WEB3RuitoSellInputResponse l_response =
            (WEB3RuitoSellInputResponse)l_request.createResponse();

        // Modify by Alan Wang 2004/08/11 according to Bug#:  70   ---------- End

        //1.15 [累投解約注文入力レスポンスに設定する値] 
        //銘柄名
        l_response.ruitoProductName = l_strProductName;
        //解約可能残高

        // ----------------- Start
        // Modify by Alan wang 2004/08/13 according to formating type double to type String
//        l_response.ruitoSellPossBalance = l_web3RuitoAssetDetail.getSellPossibleBalance() + "";
        l_response.ruitoSellPossBalance = 
            WEB3StringTypeUtility.formatNumber(l_web3RuitoAssetDetail.getSellPossibleBalance());
        // ----------------- End
        
		//指定方法@一覧
        if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strPaymentMethodSell)
            || WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strPaymentMethodSell))
        {
            String l_strSpecifyDivs[] = new String[2];
			l_strSpecifyDivs[0] = WEB3SellDivDef.ALL_DESIGNATE;
            l_strSpecifyDivs[1] = l_strPaymentMethodSell;
            l_response.specifyDivList = l_strSpecifyDivs;
        }
        else
        {
            String l_strSpecifyDivs[] = new String[3];
            l_strSpecifyDivs[0] = WEB3SellDivDef.ALL_DESIGNATE;
            l_strSpecifyDivs[1] = WEB3SellDivDef.MONEY_DESIGNATE;
			l_strSpecifyDivs[2] = WEB3SellDivDef.COUNT_DESIGNATE;
            l_response.specifyDivList = l_strSpecifyDivs;
        }
        
        //受渡方法@一覧
        if (WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_strPaymentMethod)
            || WEB3PaymentMethodDef.SECURITIES_ACCOUNT_INPUT.equals(l_strPaymentMethod))
        {
            String l_strPaymentMethods[] = new String[1];
            l_strPaymentMethods[0] = l_strPaymentMethod;
            l_response.deliveryDivList = l_strPaymentMethods;
        }
        else
        {
            String l_strPaymentMethods[] = new String[2];
            l_strPaymentMethods[0] = WEB3PaymentMethodDef.BANK_TRANSFER;
            l_strPaymentMethods[1] = WEB3PaymentMethodDef.SECURITIES_ACCOUNT_INPUT;
            l_response.deliveryDivList = l_strPaymentMethods;
        }

		log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
