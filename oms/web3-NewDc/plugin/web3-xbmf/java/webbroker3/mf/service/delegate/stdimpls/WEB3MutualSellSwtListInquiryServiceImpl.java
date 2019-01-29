head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellSwtListInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約乗換一覧照会サービスImpl(WEB3MutualSellSwtListInquiryServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 韋念瓊 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
                   2004/12/13 于美麗 (中訊) 残対応
                   2005/10/23 黄建 (中訊) フィデリティ対応   
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.define.WEB3RemarkDivDef;
import webbroker3.mf.message.WEB3MutualAppraisalProfitLossComparator;
import webbroker3.mf.message.WEB3MutualMarketValueComparator;
import webbroker3.mf.message.WEB3MutualSellSwitchingProductGroup;
import webbroker3.mf.message.WEB3MutualSellSwtListRequest;
import webbroker3.mf.message.WEB3MutualSellSwtListResponse;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mf.message.WEB3MutualOrderCloseTimeComparator;
import webbroker3.mf.message.WEB3MutualTaxTypeComparator;
import webbroker3.mf.service.delegate.WEB3MutualSellSwtListInquiryService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託解約乗換一覧照会サービスImpl
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualSellSwtListInquiryServiceImpl extends 
    WEB3MutualClientRequestService implements WEB3MutualSellSwtListInquiryService 
{    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellSwtListInquiryServiceImpl.class);
        
    /**
     * 投資信託解約乗換一覧照会サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（投信）解約乗換一覧照会」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2ED11023F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3MutualSellSwtListRequest l_sellSwtListRequest = null;
        
        if (l_request instanceof WEB3MutualSellSwtListRequest)
        {
            l_sellSwtListRequest = (WEB3MutualSellSwtListRequest) l_request;
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[入力値が不正です]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
        //1.1）入力内容チェック 
        l_sellSwtListRequest.validate();

        // --顧客別取引停止属性チェック 
        //1.2)　@this.get補助口座( )をコールし、補助口座オブジェクトを取得する。
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.3) FinAppクラスのgetCommonOrderValidator()
        //  注文チェックオブジェクトを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);     
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4) get投信発注日（）       
        Timestamp l_datBizDate = 
            new Timestamp(
                    WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate().getTime());
            
        //1.5) 注文チェック.validate取引可能顧客( )をコールし例外が返された場合、 
        //    例外をスローする。 
        WEB3GentradeMainAccount l_genMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        OrderValidationResult l_validationResult =  
            l_orderValidator.validateAccountForTrading(
                l_genMainAccount,
                l_datBizDate);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引停止顧客エラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引停止顧客エラー");
        }
                
        // --受付時間チェック・システム取引停止チェック
        // 1.6) validate注文受付可能
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        //1.7) 保有資産銘柄一覧検索処理 
        WEB3MutualFundPositionManager l_mfPositionManager =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        log.debug("l_subAccount.getAccountId() = " + l_subAccount.getAccountId());   
        List l_lisAsset = new Vector();
        l_lisAsset = l_mfPositionManager.getAssets(
            l_subAccount, null, ProductTypeEnum.MUTUAL_FUND);
        log.debug("l_lisAsset.size() = "+ l_lisAsset.size());
        
        // --明細の作成
        //1.8) getAssets()の戻り値の件数分繰り返し、
        //    投信解約乗換銘柄一覧行オブジェクトの配列の作成
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        WEB3MutualFundProduct l_web3MfProduct = null;
                        
        //以下の処理を1.7)の処理で取得した保有銘柄の件数分行う。
        List l_lisSellSwtProductGroup = new Vector();
        for (int i = 0; i < l_lisAsset.size(); i++)
        {
            try
            {
                MutualFundAsset l_mfAsset = (MutualFundAsset) l_lisAsset.get(i);
                AssetRow l_assetRow = (AssetRow)l_mfAsset.getDataSourceObject();
                // 投信銘柄の取得
                // 投信拡張銘柄マネージャ.getProduct( )をコールし、 
                // 取得した銘柄オブジェクト.getDataSourceObject( )を取得
                log.debug("l_assetRow.getProductId() = " + l_assetRow.getProductId());
                //NotFoundException
                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow) l_mfProductManager.getProduct(
                        l_assetRow.getProductId()).getDataSourceObject();
                        
                //拡張投信銘柄マネージャ.to投信銘柄( )をコールする        
                l_web3MfProduct = (WEB3MutualFundProduct)
                    l_mfProductManager.toProduct(l_mutualFundProductRow);
                                               
                //取引時間コンテキスト更新
                WEB3MutualFundTradingTimeManagement.resetProductCode(
                    l_web3MfProduct.getProductCode());
                WEB3MutualFundTradingTimeManagement.setTimestamp();
                
                log.debug("l_web3MfProduct.getProductCode() = " + l_web3MfProduct.getProductCode());
                //表示銘柄明細の作成
                WEB3MutualSellSwitchingProductGroup l_sellSwtProductGroup =
                    new WEB3MutualSellSwitchingProductGroup();
                
                //1.8.7) ＜プロパティ・セット＞       
                l_sellSwtProductGroup.id = l_assetRow.getAssetId() + "";
                l_sellSwtProductGroup.mutualProductCode = l_web3MfProduct.getProductCode();
                log.debug("銘柄明細.mutualProductCode = " + l_web3MfProduct.getProductCode());
                l_sellSwtProductGroup.mutualProductName = 
                l_web3MfProduct.getMutualProductName();
                
                //口座区分
                TaxTypeEnum l_taxType = null;
                l_taxType = l_assetRow.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxType))
                {
                    l_sellSwtProductGroup.taxType = WEB3MFAccountDivDef.NORMAL;
                }
                else if (TaxTypeEnum.SPECIAL.equals(l_taxType) ||
                            TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
                {
                    l_sellSwtProductGroup.taxType = WEB3MFAccountDivDef.SPECIAL;
                }
                log.debug("銘柄明細.口座区分 = " + l_sellSwtProductGroup.taxType);
                
                //解約可能口数 = 拡張投信ポジションマネージャ.calc解約可能残高口数( ) 
                // 1.8.8.1.1) calc解約可能残高口数(補助口座, 拡張投信銘柄, 資産ID)
                // ［calc解約可能残高口数に渡すパラメタ］
                // 　@ 補助口座： 取得した補助口座オブジェクト 
                //　@　@拡張投信銘柄： 取得した拡張投信銘柄オブジェクト 
                //　@　@資産ID：　@取得した保有資産テーブルParams.get資産ID
                
                double l_dblSellPossiblePositionQty = l_mfPositionManager.calcSellPossiblePositionQty(
                    l_subAccount, l_web3MfProduct, l_mfAsset.getAssetId() + "");
                log.debug("拡張投信ポジションマネージャ.calc解約可能残高口数( ) = " + 
                    l_dblSellPossiblePositionQty);
                
                //1.8.8.1.2)拡張投信ポジションマネージャ.calc解約可能残高口数( )の戻り値が0の場合
                if ( l_dblSellPossiblePositionQty == 0)
                {
                    //1.8.8.1.2.1) ＜“全部解約中”のセット＞
					//投信解約乗換銘柄一覧行オブジェクトの以下のプロパティに”全部解約中”をセットする。 
					//解約可能区分 
					//買取可能区分 
					//乗換可能区分
                    l_sellSwtProductGroup.sellPossType =WEB3RemarkDivDef.All_SELLING;
                    l_sellSwtProductGroup.buyPossType =WEB3RemarkDivDef.All_SELLING;
                    l_sellSwtProductGroup.switchingPossType =WEB3RemarkDivDef.All_SELLING;
                }
                
                l_sellSwtProductGroup.sellableQty = 
                    WEB3StringTypeUtility.formatNumber(l_dblSellPossiblePositionQty);
                
                log.debug("銘柄明細.解約可能口数 = " + l_sellSwtProductGroup.sellableQty);
                
                //解約中概算口数 = 拡張投信ポジションマネージャ.calc解約中概算口数( )    
                l_sellSwtProductGroup.sellingEstimatedQty = 
                    WEB3StringTypeUtility.formatNumber(l_dblSellPossiblePositionQty);
                
                log.debug("銘柄明細.解約中概算口数 = " + l_sellSwtProductGroup.sellingEstimatedQty);
                
                //参考基準価額通貨コード= 投信銘柄.get通貨コード( )
                l_sellSwtProductGroup.constantValueCurrencyCode = 
                    l_web3MfProduct.getCurrencyCode();
                
                log.debug("銘柄明細.参考基準価額通貨コード = " + l_sellSwtProductGroup.constantValueCurrencyCode);
                
                //参考基準価額 = 投信銘柄.get解約価額( )
                l_sellSwtProductGroup.constantValue = 
                    WEB3StringTypeUtility.formatNumber(l_web3MfProduct.getSellValue());
                
                log.debug("銘柄明細.参考基準価額 = " + l_sellSwtProductGroup.constantValue);
                
                //基準価額適用日= 投信銘柄.get基準価額適用日( ) 
                l_sellSwtProductGroup.constantValueAppDate = WEB3DateUtility.toDay(
                    l_web3MfProduct.getConstantValueAppDate());
                
                log.debug("銘柄明細.基準価額適用日 = " + l_sellSwtProductGroup.constantValueAppDate);
                
                //個別元本 = 保有資産テーブルParams.getBookValue( )
                l_sellSwtProductGroup.indivPrincipal = 
                    WEB3StringTypeUtility.formatNumber(l_assetRow.getBookValue());
                
                log.debug("銘柄明細.個別元本 = " + l_sellSwtProductGroup.indivPrincipal);
                
                log.debug("投信銘柄.get投信タイプ( )= " + l_web3MfProduct.getMutualFundType());

                //評価額 = 拡張投信ポジションマネージャ.calc評価額( )
                l_sellSwtProductGroup.marketValue = 
                    WEB3StringTypeUtility.formatNumber(
                        l_mfPositionManager.calcMarketValue(
                        l_subAccount, l_web3MfProduct, l_mfAsset.getAssetId() + ""));
                            
                log.debug("銘柄明細.評価額 = " + l_sellSwtProductGroup.marketValue);
                //投信銘柄.get投信タイプ( )= "国外"であり、かつ上記口座区分が"一般"となる場合 
                //以外、以下を行う。
                if ((MutualFundTypeEnum.FOREIGN.equals(l_web3MfProduct.getMutualFundType()) &&
                    WEB3MFAccountDivDef.NORMAL.equals(l_sellSwtProductGroup.taxType)))
                {
                    //(条件に合致する銘柄の評価損益には、nullをセット)
                    l_sellSwtProductGroup.appraisalProfitLoss = null;
                }
                else
                {                  
                    //評価損益 = 拡張投信ポジションマネージャ.calc評価損益( ) 
                    l_sellSwtProductGroup.appraisalProfitLoss = 
                        WEB3StringTypeUtility.formatNumber(
                            l_mfPositionManager.calcAppraisalProfitLoss(
                            l_subAccount, l_web3MfProduct, l_mfAsset.getAssetId() + ""));
                            
                    log.debug("銘柄明細.評価損益 = " + l_sellSwtProductGroup.appraisalProfitLoss);
                }

                //解約時単位口数 = 投信銘柄.get単位口数(解約)( ) 
                l_sellSwtProductGroup.sellUnitQty = l_web3MfProduct.getSellUnitQty() + "";
                log.debug("銘柄明細.解約時単位口数 = " + l_sellSwtProductGroup.sellUnitQty);
                
                //解約時最低口数 = 投信銘柄.get最低口数(解約)( )
                l_sellSwtProductGroup.sellMinQty = l_web3MfProduct.getSellMinQty() + "";
                log.debug("銘柄明細.解約時最低口数 = " + l_sellSwtProductGroup.sellMinQty);
                
                //解約時単位金額 = 投信銘柄.get単位金額(解約)( ) 
                l_sellSwtProductGroup.sellUnitAmt = l_web3MfProduct.getSellUnitAmt() + "";
                log.debug("銘柄明細.解約時単位金額 = " + l_sellSwtProductGroup.sellUnitAmt);
                
                //解約時最低金額 = 投信銘柄.get最低金額(解約)( ) 
                l_sellSwtProductGroup.sellMinAmt = l_web3MfProduct.getSellMinAmt() + "";
                log.debug("銘柄明細.解約時最低金額 = " + l_sellSwtProductGroup.sellMinAmt);
                
                //乗換時単位口数 = 投信銘柄.get単位口数(乗換)( )
                l_sellSwtProductGroup.switchingUnitQty = 
                    l_web3MfProduct.getSwitchingUnitQty() + "";
                log.debug("銘柄明細.乗換時単位口数 = " + l_sellSwtProductGroup.switchingUnitQty);
                
                //乗換時最低口数 = 投信銘柄.get最低口数(乗換)( )                
                l_sellSwtProductGroup.switchingMinQty = 
                    l_web3MfProduct.getSwitchingMinQty() + "";
                log.debug("銘柄明細.乗換時最低口数 = " + l_sellSwtProductGroup.switchingMinQty);
                
                //乗換時単位金額 = 投信銘柄.get単位金額(乗換)( )
                l_sellSwtProductGroup.switchingUnitAmt = 
                    l_web3MfProduct.getSwitchingUnitAmt() + "";
                log.debug("銘柄明細.乗換時単位金額 = " + l_sellSwtProductGroup.switchingUnitAmt);
                
                //乗換時最低金額 = 投信銘柄.get最低金額(乗換)( ) 
                l_sellSwtProductGroup.switchingMinAmt = 
                    l_web3MfProduct.getSwitchingMinAmt() + "";
                log.debug("銘柄明細.乗換時最低金額 = " + l_sellSwtProductGroup.switchingMinAmt);
                
                //注文受付締切時間 = 投信取引時間管理.get注文受付締切時間( )をコールし、 
                //戻された値の１秒後の時間"HHMMSS"を"HH:MM"に編集してセットする。
                String l_strOrderCloseTime = 
                    WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
                Date l_datOrderCloseTime = 
                    WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
                l_strOrderCloseTime = 
                    WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss"); 
                l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
                    + WEB3GentradeTimeDef.STR_COLON
                    + l_strOrderCloseTime.substring(2, 4);
                l_sellSwtProductGroup.orderCloseTime = 
                    l_strOrderCloseTime;
                
                //1.8.8) ＜解約・買取・乗換可能区分のセット＞  
                //-start--------------------------------------------- 
                //a6) 以外の場合nullをセット
                l_sellSwtProductGroup.sellPossType = null;
                l_sellSwtProductGroup.buyPossType = null;
                l_sellSwtProductGroup.switchingPossType = null;
                
                //1.8.8.2) 緊急停止中チェック 
                WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
                    (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
                try
                {
                    //1.8.8.2.1) validate緊急停止(拡張投信銘柄, String)
                    l_validationsCheck.validateEmergencyStop(
                        l_web3MfProduct, WEB3ProcessDivDef.SELL);
                }
                catch (WEB3BaseException l_ex)
                {
                    //1.8.8.2.2) ＜validate緊急停止()から例外がスローされた場合＞            
                    //1.8.8.2.2.1) ＜“緊急停止中”のセット＞
                    l_sellSwtProductGroup.sellPossType = WEB3RemarkDivDef.EMERGENCY_STOP; 
                    //買取可能区分に"緊急停止中"をセットする
                    l_sellSwtProductGroup.buyPossType = WEB3RemarkDivDef.EMERGENCY_STOP;                               
                }
                //1.8.8.2.3) validate緊急停止(拡張投信銘柄, String)
                try
                {
                    l_validationsCheck.validateEmergencyStop(
                        l_web3MfProduct, WEB3ProcessDivDef.SWITCHING);
                }
                catch (WEB3BaseException l_ex)
                {
                    //1.8.8.2.4)＜validate緊急停止()から例外がスローされた場合＞
                    //1.8.8.2.4.1)＜“緊急停止中”のセット＞
                    l_sellSwtProductGroup.switchingPossType = WEB3RemarkDivDef.EMERGENCY_STOP;                
                }
                
                //1.8.8.3) ＜取引時間外チェック＞
                try
                {                    
                    //1.8.8.3.1) 投信取引時間管理.validate注文受付可能( )をコールし
                    WEB3MutualFundTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BaseException l_ex)
                {
                    //1.8.8.3.2) ＜validate注文受付可能()から例外がスローされた場合＞
                    //1.8.8.3.2.1) ＜“取引時間外”のセット＞
                    //解約可能区分 
                    l_sellSwtProductGroup.sellPossType = 
                        WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
                    //買取可能区分
                    l_sellSwtProductGroup.buyPossType = 
                        WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP; 
                    //乗換可能区分
                    l_sellSwtProductGroup.switchingPossType = 
                        WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;                       
                }
                //1.8.8.4) ＜取引不可チェック＞
                
                //1.8.8.4.1) get投信発注日( )
                Date l_datOrderBizDate =
                    WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
                
                //1.8.8.4.2) is解約乗換可能(Date)
                boolean l_blnSellSwtPossible = false;   //解約乗換可能
                
                l_blnSellSwtPossible = 
                    l_web3MfProduct.isSellSwitchingPossible(l_datOrderBizDate);
                
                //1.8.8.4.4)＜is解約乗換可能()＝false の場合＞
                if (!l_blnSellSwtPossible)
                {
                    //1.8.8.4.4.1)＜“取引不可”のセット＞
                    l_sellSwtProductGroup.sellPossType = 
                        WEB3RemarkDivDef.HANDLING_DISABLE;
                    l_sellSwtProductGroup.buyPossType = 
                        WEB3RemarkDivDef.HANDLING_DISABLE;
                    l_sellSwtProductGroup.switchingPossType = 
                        WEB3RemarkDivDef.HANDLING_DISABLE;
                }
                //1.8.8.4.3) ＜is解約乗換可能()＝true の場合＞    
                //1.8.8.4.3.1) is買取請求可能(Date, SubAccount, 拡張投信銘柄)
                boolean l_blnBuyingRequestPossible = false; //買取請求可能
                
                l_blnBuyingRequestPossible = l_validationsCheck.isBuyingRequestPossible(
                    WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(),
                    l_subAccount,
                    l_web3MfProduct);
                    
                log.debug("投信銘柄.is解約乗換可能( ) = " + l_blnSellSwtPossible);
                log.debug("投信銘柄.is買取請求可能( ) = " + l_blnBuyingRequestPossible);
                
                //1.8.8.4.3.2)＜is買取請求可能()＝false の場合＞
                if (l_blnSellSwtPossible && !l_blnBuyingRequestPossible)                
                {
                    //1.8.8.4.3.2.1) ＜“取引不可”のセット＞
                    l_sellSwtProductGroup.buyPossType = 
                        WEB3RemarkDivDef.HANDLING_DISABLE;
                }
                
                //1.8.8.4.5) is乗換可能( )
                //1.8.8.4.6)   ＜is乗換可能()＝false の場合＞
                // 乗換可能区分に"取扱不可"をセットする。
                log.debug("投信銘柄.is乗換可能(　@) = " + l_web3MfProduct.isSwitchingAble());
                if(!l_web3MfProduct.isSwitchingAble())
                {
                    //1.8.8.4.6.1) ＜“取扱不可”のセット＞
                    l_sellSwtProductGroup.switchingPossType = 
                        WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
                }
                
                //1.8.8.5)＜取扱不可チェック＞
                //1.8.8.5.1) isシステム取扱( )
                //1.8.8.5.2) ＜isシステム取扱()＝false の場合＞
                log.debug("投信銘柄.isシステム取扱( ) = " + l_web3MfProduct.isSystemHandling());
                if (!l_web3MfProduct.isSystemHandling())
                {
                    //1.8.8.5.2.1) ＜“取扱不可”のセット＞
                    l_sellSwtProductGroup.sellPossType = 
                        WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
                    l_sellSwtProductGroup.buyPossType = 
                        WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
                    l_sellSwtProductGroup.switchingPossType = 
                        WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
                }
                log.debug("銘柄明細.解約可能区分 = " + l_sellSwtProductGroup.sellPossType);
                log.debug("銘柄明細.買取可能区分 = " + l_sellSwtProductGroup.buyPossType);
                log.debug("銘柄明細.乗換可能区分 = " + l_sellSwtProductGroup.switchingPossType);
                l_lisSellSwtProductGroup.add(l_sellSwtProductGroup);
                //-end-----------------------------------------------                
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }   //for end
        WEB3MutualSellSwitchingProductGroup[] l_sellSwtProductGroups = 
            new WEB3MutualSellSwitchingProductGroup[l_lisSellSwtProductGroup.size()];
        l_lisSellSwtProductGroup.toArray(l_sellSwtProductGroups);
        
        //1.9) ソート処理
        //　@this.sort解約乗換一覧明細( )
        this.sortSellSwitchingListDetails(
            l_sellSwtProductGroups, l_sellSwtListRequest.sortKeys);


        //1.10)投信解約乗換一覧照会リクエスト.createレスポンス( ) 
        //     投信解約乗換一覧照会レスポンスを生成 
        WEB3MutualSellSwtListResponse l_sellSwtListresponse =
            (WEB3MutualSellSwtListResponse) l_sellSwtListRequest.createResponse();
          
        //1.11) ページング処理  
        //投信解約乗換一覧照会レスポンスの以下の項目を設定する 
        /*-start--------------------------------------------- 
         * ○投信解約乗換一覧照会レスポンス.総ページ数: 
         * ６)で確定した明細の要素数÷投信解約乗換一覧照会リクエスト.ページ内表示行数 
         * ※余りが出る場合は、＋１した値を設定。
         * ※６)で確定した明細の要素数＝0(表示対象データなし)の場合、0をセット。
         * -end----------------------------------------------- 
         */
         
        //○投信解約乗換一覧照会レスポンス.総ページ数: 
        ///*-start--------------------------------------------- 
        int l_intRequestPageSize = 0;  //ページ内表示行数
        
        l_intRequestPageSize = Integer.parseInt(l_sellSwtListRequest.pageSize);
        
        //※６)で確定した明細の要素数＝0(表示対象データなし)の場合、0をセット
        if (l_sellSwtProductGroups.length == 0)
        {
            l_sellSwtListresponse.totalPages = "0";
        }
        //※６)で確定した明細の要素数÷投信解約乗換一覧照会リクエスト.ページ内表示行数
        else if (l_sellSwtProductGroups.length % l_intRequestPageSize == 0) 
        {
            l_sellSwtListresponse.totalPages = 
                l_sellSwtProductGroups.length / l_intRequestPageSize + "";            
        }
        //※余りが出る場合は、＋１した値を設定
        else
        {
            l_sellSwtListresponse.totalPages = 
                l_sellSwtProductGroups.length / l_intRequestPageSize + 1 + "";
        }
        //-end----------------------------------------------- 
        log.debug("投信解約乗換一覧照会レスポンス.総ページ数: " + l_sellSwtListresponse.totalPages);
        
        //○投信解約乗換一覧照会レスポンス.総レコード数:　@６)で確定した明細の要素数 
        l_sellSwtListresponse.totalRecords = l_sellSwtProductGroups.length + "";
        log.debug("投信解約乗換一覧照会レスポンス.総レコード数: " + l_sellSwtListresponse.totalRecords);
        
        //○投信解約乗換一覧照会レスポンス.表示ページ番号(表示が何ページ目にあたるか): 
        /*-start--------------------------------------------- 
         * ６)で確定した明細の要素数 > (投信解約乗換一覧照会リクエスト.ページ内表示行数×
         * (投信解約乗換一覧照会リクエスト.要求ページ番号 - 1) )であれば、
         * 投信解約乗換一覧照会リクエスト.要求ページ番号。 
         * 上記以外の場合は、投信解約乗換一覧照会レスポンス.総ページ数 をそのまま設定。 
         * ※検索結果がPR層からの要求ページ番号に満たない場合は、最終ページに該当するデータを 
         * 投信解約乗換一覧照会レスポンスに設定する。 
         * -end----------------------------------------------- 
         */

        //投信解約乗換一覧照会リクエスト.要求ページ番号
        int l_intPageIndex = Integer.parseInt(l_sellSwtListRequest.pageIndex);
        
        if (l_sellSwtProductGroups.length > (l_intRequestPageSize * l_intPageIndex - 1))
        {
            l_sellSwtListresponse.pageIndex = l_sellSwtListRequest.pageIndex;
        }
        else
        {
            l_sellSwtListresponse.pageIndex = l_sellSwtListresponse.totalPages;
        }
        log.debug("投信解約乗換一覧照会レスポンス.要求ページ番号: " + 
            l_sellSwtListresponse.pageIndex);
        
        // 設定後、投信解約乗換一覧照会レスポンス.総ページ数 = 0 の場合
        // レスポンスデータ.解約乗換銘柄一覧(投信解約乗換銘柄一覧行[ ])に 
        // nullをせっとし、レスポンスをreturnする。
        if (Integer.parseInt(l_sellSwtListresponse.totalPages) == 0)
        {
            l_sellSwtListresponse.sellSwitchingProductGroups = null;
            return l_sellSwtListresponse;
        }
        
        /* (投信解約乗換一覧照会リクエスト.ページ内表示行数×( 
         * 投信解約乗換一覧照会レスポンス.表示ページ番号 - 1)数分、６)で確定した
         * 投信解約乗換一覧照会レスポンス明細データ一覧の要素をスキップする。
         */
        int l_intBeginRecordNumber =  
            l_intRequestPageSize * (Integer.parseInt(l_sellSwtListresponse.pageIndex) - 1);
        int l_intEndRecordNumber =  
            l_intRequestPageSize * Integer.parseInt(l_sellSwtListresponse.pageIndex);
        if (l_intEndRecordNumber > l_sellSwtProductGroups.length)
        {
            l_intEndRecordNumber = l_sellSwtProductGroups.length;
        }

       /* 上記で決定した投信解約乗換一覧照会レスポンス明細データ一覧の要素番号～
        * (投信解約乗換一覧照会レスポンス明細データ一覧の要素番号＋投信解約乗換一覧照会 
        *  リクエスト.ページ内表示行数)までに該当する投信解約乗換一覧照会レスポンス
        * 明細データを、投信解約乗換一覧照会レスポンスデータ.解約乗換銘柄一覧として 
        *  セットする。 
        */
        List l_lisProducts = new Vector(); 
        for (int i = l_intBeginRecordNumber; i < l_intEndRecordNumber; i++)
        {
            l_lisProducts.add(l_sellSwtProductGroups[i]);
        }
        WEB3MutualSellSwitchingProductGroup[] l_products = 
            new WEB3MutualSellSwitchingProductGroup[l_lisProducts.size()];
        l_lisProducts.toArray(l_products);
        
        l_sellSwtListresponse.sellSwitchingProductGroups = l_products;
       
        return l_sellSwtListresponse;
    }
    
    /**
     * (sort解約乗換一覧明細)<BR>
     * 指定されたソートキー、<BR>
     * 昇降順に基づいて投信解約乗換銘柄明細のソートを行う。 <BR>
     * <BR>
     * １)　@ArrayListを作成 <BR>
     * <BR>
     * ２)　@引数:ソートキーの配列数分Loop処理 <BR>
     * <BR>
     * 　@２－１)　@引数:ソートキー.キー項目を取得 <BR>
     * <BR>
     * 　@２－２)　@引数:ソートキー.昇順/降順を取得 <BR>
     * <BR>
     * 　@２－３)　@キー項目による分岐処理 <BR>
     * 　@　@キー項目が評価額であった場合、評価額Comparatorを生成 <BR>
     * 　@　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@　@キー項目が口座区分であった場合、口座区分Comparatorを生成 <BR>
     * 　@　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@　@キー項目が評価損益であった場合、評価損益Comparatorを生成 <BR>
     * 　@　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@　@キー項目が注文受付締切時間であった場合、<BR>
     * 注文受付締切時間Comparatorを生成 <BR>
     * 　@　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@２－４)　@２－３)にて作成したComparatorをArrayListに追加 <BR>
     * <BR>
     * ３)　@ArrayListからComparatorの配列を作成 <BR>
     * <BR>
     * ４)　@Comparatorの配列順のソート処理 <BR>
     * (web3-common)WEB3ArraysUtility.sort(引数:投信解約乗換銘柄明細、<BR>
     * Comparator[]) <BR>
     * <BR>
     * ５)　@ソートされた投信注文照会明細の配列を返却<BR>
     * <BR>
     * @@param l_sellSwitchingProductDetails - 投信解約乗換銘柄明細
     * 
     * @@param l_sortKey - ソートキー
     * @@roseuid 40BDAA350378
     */
    public void sortSellSwitchingListDetails(
        WEB3MutualSellSwitchingProductGroup[] l_sellSwitchingProductDetails, 
        WEB3MutualSortKey[] l_sortKey)
    {
        String STR_METHOD_NAME = "sortSellSwitchingListDetails()";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKey == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１)ArrayListを作成
        ArrayList l_lisComparator = new ArrayList();
        
        for (int i = 0; i < l_sortKey.length; i++)
        {
            Comparator l_comparator = null;
            
            //２－１)　@引数:ソートキー.キー項目を取得
            String l_strKeyItem = l_sortKey[i].keyItem;
            //２－２)　@引数:ソートキー.昇順/降順を取得
            String l_strAscDesc = l_sortKey[i].ascDesc;      
                  
            //２－３)　@キー項目による分岐処理
            //キー項目が評価額であった場合、評価額Comparatorを生成 
            if (WEB3MFSortkeyItemDef.MARKET_VALUE.equals(l_strKeyItem))
            {
                WEB3MutualMarketValueComparator l_marketValueComparator = 
                    new WEB3MutualMarketValueComparator(l_strAscDesc);
                l_comparator = l_marketValueComparator;
            }
            //キー項目が口座区分であった場合、口座区分Comparatorを生成
            else if (WEB3MFSortkeyItemDef.TAX_TYPE.equals(l_strKeyItem))
            {
                WEB3MutualTaxTypeComparator l_taxTypeComparator = 
                    new WEB3MutualTaxTypeComparator(l_strAscDesc);
                l_comparator = l_taxTypeComparator;                           
            }
            //キー項目が評価損益であった場合、評価損益Comparatorを生成
            else if (WEB3MFSortkeyItemDef.APPRAISAL_PROFIT_LOSS.equals(l_strKeyItem))
            {
                WEB3MutualAppraisalProfitLossComparator l_appraisalProfitLossComparator = 
                    new WEB3MutualAppraisalProfitLossComparator(l_strAscDesc); 
                l_comparator = l_appraisalProfitLossComparator;                                          
            }
            
            //キー項目が注文受付締切時間であった場合、注文受付締切時間Comparatorを生成
            else if (WEB3MFSortkeyItemDef.ORDER_CLOSE_TIME.equals(l_strKeyItem))
            {
                WEB3MutualOrderCloseTimeComparator l_orderCloseTimeComparator = 
                    new WEB3MutualOrderCloseTimeComparator(l_strAscDesc);  
                l_comparator = l_orderCloseTimeComparator;                                                        
            }
            //２－４)　@２－３)にて作成したComparatorをArrayListに追加 
            l_lisComparator.add(l_comparator);
        }
        //３)　@ArrayListからComparatorの配列を作成
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);
        
        //４)　@Comparatorの配列順のソート処理
        //(web3-common)WEB3ArraysUtility.sort(引数:投信解約乗換銘柄明細、Comparator[]) 
        WEB3ArraysUtility.sort(l_sellSwitchingProductDetails, l_comparators);

        log.exiting(STR_METHOD_NAME);        
    }
}
@
