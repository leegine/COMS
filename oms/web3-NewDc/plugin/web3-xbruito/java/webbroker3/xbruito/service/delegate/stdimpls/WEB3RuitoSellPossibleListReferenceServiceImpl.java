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
filename	WEB3RuitoSellPossibleListReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投解約可能一覧照会サービス実装クラス(WEB3RuitoSellPossibleListReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 李志強 (中訊) 新規作成
                   2004/12/06 韋念瓊 (中訊) 残対応
*/

package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoAsset;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3StopDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoAssetDetail;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoOrderManagerReusableValidationsCheck;
import webbroker3.xbruito.WEB3RuitoPositionManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.WEB3RuitoSellOrderDetail;
import webbroker3.xbruito.define.WEB3RuitoSellPossibleDivDef;
import webbroker3.xbruito.message.WEB3RuitoAssetGroup;
import webbroker3.xbruito.message.WEB3RuitoSellListRequest;
import webbroker3.xbruito.message.WEB3RuitoSellListResponse;
import webbroker3.xbruito.message.WEB3RuitoSellOrderUnit;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellPossibleListReferenceService;

/**
 * 累投解約可能一覧照会サービス実装クラス<BR>
 */
public class WEB3RuitoSellPossibleListReferenceServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoSellPossibleListReferenceService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellPossibleListReferenceServiceImpl.class);
    /**
     * 入力画面表示処理<BR>
     * 累積投資の解約可能一覧照会画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図「（累投）解約可能一覧照会」参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406A91D10387
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
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
                "パラメータ値がNULL");
        }

		WEB3RuitoSellListRequest l_web3RuitoSellListRequest = null;
		
		if (l_request instanceof WEB3RuitoSellListRequest)
		{
			l_web3RuitoSellListRequest = (WEB3RuitoSellListRequest) l_request;
		}
		else
		{
			log.debug(STR_METHOD_NAME + " __Error[入力値が不正です]__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80018,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}			

        // 1.1 補助口座取得 
        SubAccount l_subAccount = getSubAccount();
        
		log.debug("SubAccount Status = " + l_subAccount.getSubAccountStatus());
		log.debug("MainAccount Status = " + l_subAccount.getMainAccount().getAccountStatus());

		// 1.2 顧客別取引属性チェック 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // 1.3 取引可能顧客チェックを実施する。 
        OrderValidationResult l_orderValidationResult = 
            l_finApp.getCommonOrderValidator().validateSubAccountForTrading(l_subAccount);
            
        if (l_orderValidationResult.getProcessingResult().isFailedResult()) 
        {
			log.debug("取引停止顧客エラー");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00275,
				this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.4 累投取引口座開設チェック 
        WEB3RuitoOrderManagerReusableValidationsCheck l_ruitoOrderManagerReusableValidationsCheck =
            (WEB3RuitoOrderManagerReusableValidationsCheck) 
                WEB3RuitoOrderManagerReusableValidationsCheck.getInstance();

        try
        {
            l_ruitoOrderManagerReusableValidationsCheck
                .validateRuitoTradedAccountEstablish(
                l_subAccount);
        }
        catch (OrderValidationException l_ex)
        {
        	log.debug("__OrderValidationException__");
			log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getValidationResult().toString(),
                l_ex);
        }
        
        boolean l_blnMidiumFundValidFlag = true;
		boolean l_blnMMFValidFlag = true;
		int l_intFundError = 0;
        int l_intMmfError = 0;
                
        // 受付時間チェック、システム取引停止チャック 
        //中期国債ファ@ンドの注文受付可能チェックを行う
        try
        {           
            //－中期国債ファ@ンドの注文受付可能チェックを行う。 
            log.debug("中期国債ファ@ンドの注文受付可能チェックを行う。");
			WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch(WEB3BaseException l_ex)
        {
        	log.debug("中期国債ファ@ンド 受付時間チェック、システム取引停止エラー");
			l_blnMidiumFundValidFlag = false;
            
            if (WEB3ErrorCatalog.BUSINESS_ERROR_00011.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 1;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00012.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 2;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00013.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 3;
            }
        }        
        
        //－取引時間管理.reset受付時間区分()をコールし
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(
            WEB3TradingTimeTypeDef.MMF_SET_CANCEL);
        
        //－受付日時、日付ロールをセットする。 
        WEB3GentradeTradingTimeManagement.setTimestamp();
        try
        {
            //－MMFの注文受付可能チェックを行う。 
            log.debug("MMFの注文受付可能チェックを行う。 ");
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_ex)
        {
			log.debug("MMF 受付時間チェック、システム取引停止受付時間エラー");
			l_blnMMFValidFlag = false;
            
            if (WEB3ErrorCatalog.BUSINESS_ERROR_00011.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 1;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00012.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 2;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00013.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 3;
            }
        }
        
        String l_strChuukokuFundSellPossdiv = null;
        String l_strMmfSellPossdiv = null;
        
        //－中期国債ファ@ンドとMMFの両方、またはいずれかで例外が返された場合、以下を行う。 
        if (!l_blnMidiumFundValidFlag || !l_blnMMFValidFlag)
        {
            //(1)中期国債ファ@ンドの注文のチェックの場合 
            if (!l_blnMidiumFundValidFlag)                    
            {
                log.debug("中期国債ファ@ンドの注文のチェックの場合 ");
                // ・「バッチ処理中」の例外が返された場合 
                //  「緊急停止中」の例外が返された場合
                // * 変数.中国ファ@ンド解約可能区分に”システム取引停止中”をセットする。 
                if (l_intFundError == 1 || l_intFundError == 2)
                {
                    log.debug("変数.中国ファ@ンド解約可能区分に”システム取引停止中”をセットする。");
                    l_strChuukokuFundSellPossdiv = 
                        WEB3RuitoSellPossibleDivDef.SYSTEM_TRADING_STOP_ERROR;
                }  
                //「受付不可時間エラー」の例外が返された場合
                //*  変数.中国ファ@ンド解約可能区分に”受付時間エラー”をセットする。 
                else if (l_intFundError == 3)
                {
                    log.debug("変数.中国ファ@ンド解約可能区分に”受付時間エラー”をセットする。");
                    l_strChuukokuFundSellPossdiv = 
                        WEB3RuitoSellPossibleDivDef.ACCEPTED_TIME_ERROR;
                }
                //・例外が返されなかった場合、変数.中国ファ@ンド解約可能区分にNULLをセットする。 
                else
                {
                    log.debug("変数.中国ファ@ンド解約可能区分にNULLをセットする。");
                    l_strChuukokuFundSellPossdiv = null;
                }
            }
            //(2)MMFの注文のチェックの場合 
            if (!l_blnMMFValidFlag)                    
            {
                log.debug("MMFの注文のチェックの場合");
                // ・「バッチ処理中」の例外が返された場合 
                //  「緊急停止中」の例外が返された場合
                // * 変数.中国ファ@ンド解約可能区分に”システム取引停止中”をセットする。 
                if (l_intMmfError == 1 || l_intMmfError == 2)
                {                    
                    log.debug("変数.中国ファ@ンド解約可能区分に”システム取引停止中”をセットする。");
                    l_strMmfSellPossdiv = 
                        WEB3RuitoSellPossibleDivDef.SYSTEM_TRADING_STOP_ERROR;
                }  
                //「受付不可時間エラー」の例外が返された場合
                //*  変数.中国ファ@ンド解約可能区分に”受付時間エラー”をセットする。 
                else if (l_intMmfError == 3)
                {
                    log.debug("変数.中国ファ@ンド解約可能区分に”受付時間エラー”をセットする。");
                    l_strMmfSellPossdiv = 
                        WEB3RuitoSellPossibleDivDef.ACCEPTED_TIME_ERROR;
                }
                //・例外が返されなかった場合、変数.MMF解約可能区分にNULLをセットする。
                else
                {
                    log.debug("変数.中国ファ@ンド解約可能区分にNULLをセットする。");
                    l_strMmfSellPossdiv = null;
                }
            }            
        } 

        // 1.11 顧客が保有する保有資産オブジェクトを全件取得する。
        WEB3RuitoPositionManager l_web3RuitoPositionManager =
            (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getPositionManager();

        List l_lisAssetList =
            l_web3RuitoPositionManager.getAssets(
                l_subAccount,
                new DefaultSortKeySpec(" product_id asc "),
                ProductTypeEnum.RUITO);
                 
		log.debug("l_lisAssetList.size() = " + l_lisAssetList.size());
		int l_intRuitoAssetListSize = l_lisAssetList.size();

        // 累投解約可能一覧照会レスポンスオブジェクト生成
        WEB3RuitoSellListResponse l_response =
            (WEB3RuitoSellListResponse) l_web3RuitoSellListRequest.createResponse();
        WEB3RuitoAssetGroup[] l_web3RuitoAssetGroup =
            new WEB3RuitoAssetGroup[l_intRuitoAssetListSize];

		log.debug("l_web3RuitoAssetGroup.length = " + l_web3RuitoAssetGroup.length);

        // 1.12 取得した保有資産の銘柄数分、繰り返し処理をする
        for (int i = 0; i < l_intRuitoAssetListSize; i++)
        {
            RuitoAsset l_ruitoAsset = (RuitoAsset) l_lisAssetList.get(i);
			WEB3RuitoProduct l_ruitoProduct = (WEB3RuitoProduct) l_ruitoAsset.getProduct();

            AssetRow l_ruitoAssetRow =
                (AssetRow) l_ruitoAsset.getDataSourceObject();
            double l_countBeforePenalty = l_ruitoAssetRow.getCountBeforePenalty();
            
			log.debug("30日未満経過残高 = " + l_countBeforePenalty);
			log.debug("累投銘柄.get銘柄ID() = " + l_ruitoProduct.getProductId());
			log.debug("保有資産.getQuantity() = " + l_ruitoAsset.getQuantity());
			log.debug("累投銘柄.getRuitoType() = " + l_ruitoProduct.getRuitoType());

            //1.12.6  累投保有資産明細取得 
            WEB3RuitoAssetDetail l_ruitoAssetDetail =
                new WEB3RuitoAssetDetail(
                    l_ruitoProduct.getProductId(),
                    l_ruitoAsset.getQuantity(),
                    l_countBeforePenalty,
                    l_ruitoProduct.getRuitoType());

            //1.12.7  累投保有資産明細に値を設定する。 
            l_web3RuitoPositionManager.getRuitoAssetGroup(
                l_subAccount,
                l_ruitoAssetDetail);
			
			l_web3RuitoAssetGroup[i] = new WEB3RuitoAssetGroup();

            l_web3RuitoAssetGroup[i].ruitoProductCode = l_ruitoProduct.getProductCode();
            l_web3RuitoAssetGroup[i].ruitoProductName = l_ruitoProduct.getProductName();

            l_web3RuitoAssetGroup[i].ruitoSellPossBalance =
                WEB3StringTypeUtility.formatNumber(l_ruitoAssetDetail.getSellPossibleBalance());
            l_web3RuitoAssetGroup[i].ruitoBalance = 
                WEB3StringTypeUtility.formatNumber(l_ruitoAsset.getQuantity());
            l_web3RuitoAssetGroup[i].ruito30DayPassBal =
                WEB3StringTypeUtility.formatNumber(l_ruitoAssetDetail.getCountAfterPenalty());
            l_web3RuitoAssetGroup[i].ruito30DayNotPassBal =
                WEB3StringTypeUtility.formatNumber(l_ruitoAssetDetail.getCountBeforePenalty());
            l_web3RuitoAssetGroup[i].estateReserve =
                WEB3StringTypeUtility.formatNumber(l_ruitoAssetDetail.getTrustFortunePenaltyPrice());
            //解約（売付）可能区分　@　@= (*) 
            String l_strSellPossDiv = null;
            
            //－取引時間管理.setTimestamp( )をコール。
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //－拡張累投銘柄マネージャ.get累投取引銘柄( )をコールし、累投取引銘柄オブジェクトを取得する。
            WEB3RuitoProductManager l_ruitoProductManager =   
               (WEB3RuitoProductManager) l_finApp.getTradingModule(
                   ProductTypeEnum.RUITO).getProductManager(); 
            RuitoTradedProduct l_ruitoTradedProduct = null;
            try
            {
                log.debug("l_web3RuitoAssetGroup[" + i + "].ruitoProductCode" + 
                        l_web3RuitoAssetGroup[i].ruitoProductCode);
                
                l_ruitoTradedProduct = 
                    l_ruitoProductManager.getRuitoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_web3RuitoAssetGroup[i].ruitoProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("拡張累投取引銘柄がない");                
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00250,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            RuitoTradedProductRow l_ruitoTradedProductRow = 
                (RuitoTradedProductRow)l_ruitoTradedProduct.getDataSourceObject();
           
            if (RuitoTypeEnum.MMF.equals(l_ruitoAssetDetail.getRuitoType()))
            {
                log.debug("累投保有資産明細.累投タイプ=”MMF”の場合");
                //－累投保有資産明細.累投タイプ=”MMF”の場合は
                //”MMF（設定）”を引数に、reset注文受付商品( )をコール。 
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                        WEB3TradingTimeTypeDef.MMF_SET);
                
                if (l_ruitoAssetDetail.getSellPossibleBalance() == 0)
                {
                    log.debug("累投保有資産明細.解約可能残高＝0の場合、”全部解約中”をセットする。");
                    //(1)全部解約中のチェック 
                    //－累投保有資産明細.解約可能残高＝0の場合、”全部解約中”をセットする。
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.ALL_SELLING;
                }
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradedProductRow.getSellStop()))
                {
                    log.debug("取得した累投取引銘柄オブジェクト.get解約停止( )＝”停止中”の場合、" +  
                            "”緊急停止中”をセットする。");
                    //(2)緊急停止中のチェック 
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.SCRAM_STOPING;
                }               
                if (!l_ruitoProduct.isSellPossible(
                        WEB3GentradeTradingTimeManagement.getOrderBizDate()))
                {
                    log.debug("拡張累投銘柄.is解約可能( )=falseの場合、”取引停止中”をセットする。");
                    //拡張累投銘柄.is解約可能( )=falseの場合、”取引停止中”をセットする。 
                    //(3)拡張累投銘柄.is解約可能( )=falseの場合、”取引停止中”をセットする。 
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.TRADING_STOPING;
                }              
                if (l_strMmfSellPossdiv != null)
                {
                    log.debug("中国ファ@ンド解約可能区分!=NULLの場合");
                    //(4)受付時間エラー、システム停止中エラーのチェック 
                    //  変数.MMF解約可能区分!=NULLの場合、 
                    //  変数.MMF解約可能区分（４の処理で設定）をセットする。 
                    l_strSellPossDiv = l_strMmfSellPossdiv;
                }
                log.debug("解約（売付）可能区分 = " + l_strSellPossDiv);
            }
            else if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoAssetDetail.getRuitoType()))
            {
                log.debug("累投保有資産明細.累投タイプ=”中国F”の場合");
                //累投保有資産明細.累投タイプ=”中国F”の 場合は
                //”中国F”を引数に、reset注文受付商品( )をコール。
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                        WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
                
                if (l_ruitoAssetDetail.getSellPossibleBalance() == 0)
                {
                    log.debug("累投保有資産明細.解約可能残高＝0の場合、”全部解約中”をセットする。");
                    //(1)全部解約中のチェック 
                    //－累投保有資産明細.解約可能残高＝0の場合、”全部解約中”をセットする。
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.ALL_SELLING;
                }                
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradedProductRow.getSellStop()))
                {
                    log.debug("取得した累投取引銘柄オブジェクト.get解約停止( )＝”停止中”の場合、" +  
                        "”緊急停止中”をセットする。");
                    //(2)緊急停止中のチェック 
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.SCRAM_STOPING;
                }
                if (!l_ruitoProduct.isSellPossible(
                        WEB3GentradeTradingTimeManagement.getOrderBizDate()))
                {
                    log.debug("拡張累投銘柄.is解約可能( )=falseの場合、”取引停止中”をセットする。");
                    //(3)拡張累投銘柄.is解約可能( )=falseの場合、”取引停止中”をセットする。 
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.TRADING_STOPING;
                }
                if (l_strChuukokuFundSellPossdiv != null)
                {
                    log.debug("中国ファ@ンド解約可能区分!=NULLの場合");
                    //(4)受付時間エラー、システム停止中エラーのチェック 
                    // 変数.中国ファ@ンド解約可能区分!=NULLの場合、 
                    // 変数.中国ファ@ンド解約可能区分（４の処理で設定）をセットする。 
                    l_strSellPossDiv = l_strChuukokuFundSellPossdiv;
                }
                log.debug("解約（売付）可能区分 = " + l_strSellPossDiv);
            }
            
            l_web3RuitoAssetGroup[i].sellPossDiv = l_strSellPossDiv;
            log.debug("l_web3RuitoAssetGroup[" + i + "].sellPossDiv = " + 
                    l_web3RuitoAssetGroup[i].sellPossDiv);

            WEB3RuitoSellOrderDetail[] l_web3RuitoSellOrderDetail =
                l_ruitoAssetDetail.getSellOrderDetail();

			WEB3RuitoSellOrderUnit[] l_ruitoSellOrderUnits = null;

            if (l_web3RuitoSellOrderDetail != null)
            {
				l_ruitoSellOrderUnits =
					new WEB3RuitoSellOrderUnit[l_web3RuitoSellOrderDetail.length];
					
				log.debug("l_web3RuitoSellOrderDetail.length = " 
				    + l_web3RuitoSellOrderDetail.length);

				for (int j = 0; j < l_web3RuitoSellOrderDetail.length; j++)
				{
					l_ruitoSellOrderUnits[j] = new WEB3RuitoSellOrderUnit();
					
					l_ruitoSellOrderUnits[j].orderDate =
						l_web3RuitoSellOrderDetail[j].getOrderTime();
					l_ruitoSellOrderUnits[j].orderState =
						l_web3RuitoSellOrderDetail[j].getOrderStatusDiv();
                    
					log.debug("OrderQuantityType = "
						+ l_web3RuitoSellOrderDetail[j].getOrderQuantityType());
				
					if ((QuantityTypeEnum.QUANTITY.intValue() + "")
						.equals(l_web3RuitoSellOrderDetail[j].getOrderQuantityType()))
					{
						l_ruitoSellOrderUnits[j].ruitoOrderQuantityType =
							WEB3SellDivDef.COUNT_DESIGNATE;
					}
					else if (
					    (QuantityTypeEnum.AMOUNT.intValue() + "").equals(
						l_web3RuitoSellOrderDetail[j].getOrderQuantityType()))
					{
						l_ruitoSellOrderUnits[j].ruitoOrderQuantityType =
							WEB3SellDivDef.MONEY_DESIGNATE;
					}
					
					l_ruitoSellOrderUnits[j].ruitoOrderQuantity =

                    WEB3StringTypeUtility.formatNumber(l_web3RuitoSellOrderDetail[j].getOrderQuantity()) + "";
                    
					log.debug("OrderQuantity = "
						+ l_web3RuitoSellOrderDetail[j].getOrderQuantity());
				}
            }

            l_web3RuitoAssetGroup[i].ruitoSellOrderUnits = l_ruitoSellOrderUnits;
        }
        
		// 保有資産オブジェクトが０件の場合nullをセットする。 
        if(l_intRuitoAssetListSize ==0)
        {
			l_response.ruitoAssetGroups = null;    
        }
        else
        {
			l_response.ruitoAssetGroups = l_web3RuitoAssetGroup;
        }
		log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
