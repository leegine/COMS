head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoBookbuildingStateDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービス実装(WEB3AdminIpoBookbuildingStateDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/23 斉麟 (中訊) 新規作成
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>043,044
Revesion History : 2005/01/06 坂上(SRA) 残対応>>>056
Revesion History : 2005/01/11 坂上(SRA) 残対応>>>044(⑦⑧余力修正追加分)
Revision History : 2005/08/19 沢田(SRA) 未取込案件IPO-No.76（パフォーマンス改善）
Revesion History : 2005/12/20 譚漢江 (中訊) 仕様変更No.104修正
Revision History : 2006/11/09 齊珂 (中訊) 仕様変更No.161
                   2006/11/09 齊珂 (中訊) 仕様変更No.163
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3AdminIpoBookbuildingCounter;
import webbroker3.ipo.WEB3AdminIpoDemandListCsv;
import webbroker3.ipo.WEB3AdminIpoDemandListFewCsv;
import webbroker3.ipo.WEB3AdminIpoInvalidOperationCsv;
import webbroker3.ipo.WEB3AdminIpoInvalidOperationFewCsv;
import webbroker3.ipo.WEB3IpoBookbuildingOrderActionImpl;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoBookbuildingOrderActionParams;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3IpoBBStateFileTypeDef;
import webbroker3.ipo.define.WEB3IpoCsvDivDef;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadResponse;
import webbroker3.ipo.message.WEB3IPODemandDistributionUnit;
import webbroker3.ipo.message.WEB3IPODemandHistoryUnit;
import webbroker3.ipo.service.delegate.WEB3AdminIpoBookbuildingStateDownloadService;
import webbroker3.ipo.service.delegate.WEB3IpoOrderActionUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービス実装クラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIpoBookbuildingStateDownloadServiceImpl implements WEB3AdminIpoBookbuildingStateDownloadService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoBookbuildingStateDownloadServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE20221
     */
    public WEB3AdminIpoBookbuildingStateDownloadServiceImpl() 
    {
     
    }
    
    /**
     * (getダウンロード画面)<BR>
     * IPOブックビルディング状況取得処理を行う。<BR>
     * （管理者IPOブックビルディング状況ダウンロード画面に表示）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・ＢＢ状況ＤＬ）getダウンロード画面」参照。<BR>
     * <BR>
     *  ========================================================<BR>                            
     * シーケンス図(「IPO（管理者・ＢＢ状況ＤＬ）getダウンロード画面」）: <BR>                          
     *   1.7(*1) （is中止() == true）の場合、例外をスローする。<BR>                           
     *   class: WEB3BusinessLayerException<BR>                           
     *   tag:   BUSINESS_ERROR_00589<BR>                         
     *   1.7(*1) （is削除() == true）の場合、例外をスローする。<BR>                           
     *   class: WEB3BusinessLayerException<BR>                           
     *   tag:   BUSINESS_ERROR_00588<BR>                         
     *   1.7(*1) （isブックビルディング開始() == false）の場合、例外をスローする。<BR>                         
     *   class: WEB3BusinessLayerException<BR>                           
     *   tag:   BUSINESS_ERROR_00587<BR>                         
     * ==========================================================<BR>  
     * @@param l_request - 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadResponse
     * @@roseuid 40E136780373
     */
    protected WEB3AdminIPOBookBuildingStateDownloadResponse getDownloadScreen(WEB3AdminIPOBookBuildingStateDownloadRequest l_request)
               throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminIPOBookBuildingStateDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //1.10.管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ
        WEB3AdminIPOBookBuildingStateDownloadResponse l_response = (WEB3AdminIPOBookBuildingStateDownloadResponse)l_request.createResponse();
        
        try
        {
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //1.3.IPO銘柄
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //1.4.is中止()
            boolean l_blisDiscont = l_ipoProduct.isDiscontinuation();
            
            //1.5.is削除銘柄()
            boolean l_blisDeletedProd = l_ipoProduct.isDeletedProduct();
            
            //1.6.isブックビルディング開始()
            boolean l_blisBookbuildingStart = l_ipoProduct.isBookbuildingStart();
            
            //1.7.(*1)(is中止()==true Or is削除銘柄()==true Or isブックビルディング開始()==false)
            if (l_blisDiscont)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (l_blisDeletedProd)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (!l_blisBookbuildingStart)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00587, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }    
            
            //ブックビルディング状況集計結果
            WEB3AdminIpoBookbuildingCounter l_ipoBookbuildingCounter = new WEB3AdminIpoBookbuildingCounter();
            
            //IPO申告
            WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
            WEB3IpoOrderImpl[] l_ipoOrders = l_ipoOrderManagerImpl.getOrderUnits(l_ipoProduct);       
        
			if (l_ipoOrders == null)
            {
			     log.exiting(STR_METHOD_NAME);
                //U01801           
				 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_01980,
                     STR_METHOD_NAME);
			}
            
            //calcブックビルディング状況
            this.calcBookbuildingSituation(l_ipoBookbuildingCounter, l_ipoOrders);
            
            //プロパティセット
            l_response.productCode = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();     //銘柄コード
            l_response.productName = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getStandardName();    //銘柄名
            l_response.lotTargetNumber 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getLotTargetOrderNumber());     //抽選対象申告件数
            l_response.cancelNumber 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getCancelNumber());        //取消件数
            l_response.allDemandNumber 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getAllOrderNumber());     //全申告件数
            l_response.averageDemandPrice 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getAverageOrderPrice());  //平均申告価格
            l_response.lotTargetQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getLotTargetOrderQuantity());   //抽選対象申告数量                              
            l_response.cancelQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getCancelQuantity());      //取消数量
            l_response.allDemandQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getAllOrderQuantity());   //全申告数量
            l_response.paymentPowerHolderTotalNumber 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getTradingPowerHolderTotalNumber());     //出金余力保持者合計人数
            l_response.paymentPowerHolderTotalQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoBookbuildingCounter.getTradingPowerHolderTotalQuantity());   //出金余力保持者合計数量
            l_response.displayUnitDiv = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getIpoUnitDiv();   //表示用単位区分
            l_response.temporaryConditionDiv 
                        = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProvisionalValueDiv();        //仮条件区分
        }
        catch (NotFoundException l_nfex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_nfex.getMessage(), l_nfex);

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get申告分布図)<BR>
     * IPOブックビルディング申告分布図データ取得処理を行う。<BR>
     * （管理者IPOブックビルディング状況申告分布図画面に表示）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・ＢＢ状況ＤＬ）get申告分布図」参照。<BR>
     * @@param l_request - 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告分布図ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapResponse
     * @@roseuid 40E137310066
     */
    protected WEB3AdminIPOBookBuildingDemandMapResponse getOrderDistributionChart(WEB3AdminIPOBookBuildingDemandMapRequest l_request) 
    {
        //xx
     return null;
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * IPOブックビルディング状況ダウンロードファ@イルデータ取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・ＢＢ状況ＤＬ）getダウンロードファ@イル」参照。<BR>
     * @@param l_request - 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadResponse
     * @@roseuid 40E137AD0066
     */
    protected WEB3AdminIPOBookBuildingStateFileDownloadResponse getDownloadFile(WEB3AdminIPOBookBuildingStateFileDownloadRequest l_request)
               throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminIPOBookBuildingStateFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //1.10.管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ
        WEB3AdminIPOBookBuildingStateFileDownloadResponse l_response = (WEB3AdminIPOBookBuildingStateFileDownloadResponse)l_request.createResponse();
        
        try
        {
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //1.3.IPO銘柄
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException

            //1.4.is中止()
            boolean l_blisDiscont = l_ipoProduct.isDiscontinuation();
            
            //1.5.is削除銘柄()
            boolean l_blisDeletedProd = l_ipoProduct.isDeletedProduct();
            
            //1.6.isブックビルディング開始()
            boolean l_blisBookbuildingStart = l_ipoProduct.isBookbuildingStart();
            
            //1.7.(*1)(is中止()==true Or is削除銘柄()==true Or isブックビルディング開始()==false)
            if (l_blisDiscont)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (l_blisDeletedProd)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (!l_blisBookbuildingStart)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00587, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            
            // 分岐フロー（リクエストデータ.ファ@イル種別==無効データ
            // かつ リクエストデータ.CSV区分 = "0"(追加項目なし)）の場合
            if (WEB3IpoBBStateFileTypeDef.INVALID_OP.equals(l_request.fileTypeCode)
                && WEB3IpoCsvDivDef.ADD_ITEM_OFF.equals(l_request.csvDiv))
            {
                // createFew無効オペレーション履歴ファ@イル(IPO銘柄)
                l_response.downloadFile =
                    this.createFewInvalidOperationFiles(l_ipoProduct);
            }

            // 分岐フロー（リクエストデータ.ファ@イル種別==無効データ
            // かつ リクエストデータ.CSV区分 = "1"(追加項目あり)）の場合
            if (WEB3IpoBBStateFileTypeDef.INVALID_OP.equals(l_request.fileTypeCode)
                && WEB3IpoCsvDivDef.ADD_ITEM_ON.equals(l_request.csvDiv))
            {
                // create無効オペレーション履歴ファ@イル(IPO銘柄)
                l_response.downloadFile =
                    this.createInvalidOperationActionFiles(l_ipoProduct);
            }

            // 分岐フロー（リクエストデータ.ファ@イル種別==BB状況データ（余力あり）
            // or BB状況データ（余力なし） かつ リクエストデータ.CSV区分 = "0"（追加項目なし））の場合
            if ((WEB3IpoBBStateFileTypeDef.BB_STATE_TP_ON.equals(l_request.fileTypeCode)
                || WEB3IpoBBStateFileTypeDef.BB_STATE_TP_OFF.equals(l_request.fileTypeCode))
                && WEB3IpoCsvDivDef.ADD_ITEM_OFF.equals(l_request.csvDiv))
            {
                // createFewブックビルディング状況ファ@イル
                l_response.downloadFile =
                    this.createFewBookbuildingStateFiles(l_ipoProduct, l_request);
            }

            // 分岐フロー（リクエストデータ.ファ@イル種別==BB状況データ（余力あり） or
            // BB状況データ（余力なし） かつ リクエストデータ.CSV区分 = "1"（追加項目あり））の場合
            if ((WEB3IpoBBStateFileTypeDef.BB_STATE_TP_ON.equals(l_request.fileTypeCode)
                || WEB3IpoBBStateFileTypeDef.BB_STATE_TP_OFF.equals(l_request.fileTypeCode))
                && WEB3IpoCsvDivDef.ADD_ITEM_ON.equals(l_request.csvDiv))
            {
                // createブックビルディング状況ファ@イル
                l_response.downloadFile =
                    this.createBookbuildingStateFiles(l_ipoProduct, l_request);
            }

            // プロパティセット
            ////現在日時
            l_response.currentDate = l_finApp.getTradingSystem().getSystemTimestamp();
        }
        catch (NotFoundException l_nfex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 l_nfex.getMessage(), l_nfex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get申告履歴一覧)<BR>
     * IPOブックビルディング申告履歴一覧取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・ＢＢ状況ＤＬ）get申告履歴一覧」参照。<BR>
     * @@param l_request - 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryResponse
     * @@roseuid 40E138520131
     */
    protected WEB3AdminIPOBookBuildingHistoryResponse getOrderActionList(WEB3AdminIPOBookBuildingHistoryRequest l_request)
               throws WEB3BaseException   
    {
        final String STR_METHOD_NAME = " getOrderActionList(WEB3AdminIPOBookBuildingHistoryRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //1.15.管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ
        WEB3AdminIPOBookBuildingHistoryResponse l_response = (WEB3AdminIPOBookBuildingHistoryResponse)l_request.createResponse();
        
        try
        {
            
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //1.3.IPO銘柄
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException

            //1.4.is中止()
            boolean l_blisDiscont = l_ipoProduct.isDiscontinuation();
            
            //1.5.is削除銘柄()
            boolean l_blisDeletedProd = l_ipoProduct.isDeletedProduct();
            
            //1.6.isブックビルディング開始()
            boolean l_blisBookbuildingStart = l_ipoProduct.isBookbuildingStart();
            
            //1.7.(*1)(is中止()==true Or is削除銘柄()==true Or isブックビルディング開始()==false)
            if (l_blisDiscont)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (l_blisDeletedProd)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }            
            else if (!l_blisBookbuildingStart)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00587, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }                   

            //1.8.get証券会社コード()
            String l_strInstCode = l_administartor.getInstitutionCode();
            
            //1.9.get顧客(証券会社コード、部店コード、口座コード) 
            WEB3GentradeAccountManager l_genAccountMgr = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            String l_strBranchCode  = l_request.branchCode;
            String l_strAccountCode = l_request.accountCode;
            MainAccount l_maMainAccount = 
                l_genAccountMgr.getMainAccount(
                                        l_strInstCode,
                                        l_strBranchCode,
                                        l_strAccountCode);                
            
            //1.10.getSubAccount(口座ID、補助口座タイプ)
            long l_lngAccountID = l_maMainAccount.getAccountId() ;
            SubAccount l_saSubAccount = 
                l_finApp.getAccountManager().getSubAccount(
                                        l_lngAccountID,
                                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                       
            //1.11.getIPO申告(補助口座、IPO銘柄ID)
            WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl 
                    = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_ipoOrderManagerImpl.getOrderUnit(l_saSubAccount,l_ipoProductId);
            
            //1.12.getIPO銘柄
            //WEB3IpoProductImpl l_ipoProductfromOrder = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
                        
            //1.13.createIPO申告履歴明細(IPO申告)
            WEB3IpoOrderActionUnitService l_service = (WEB3IpoOrderActionUnitService)Services.getService(WEB3IpoOrderActionUnitService.class);
            WEB3IPODemandHistoryUnit[] l_ipoOrderActions = l_service.createOrderActionUnit(l_ipoOrder);
            
            //1.14.getその他商品買付可能額(補助口座,受渡日)
            TradingSystem l_trdSys = l_finApp.getTradingSystem();  
            WEB3TPTradingPowerService l_tpTPS  = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            double l_trdPow = l_tpTPS.getOtherTradingPower((WEB3GentradeSubAccount)l_saSubAccount,l_trdSys.getBizDate() );
                       
            //1.16.(*2)プロパティセット
            l_response.paymentPower = WEB3StringTypeUtility.formatNumber(l_trdPow);//出金余力
            l_response.demandHistoryList = l_ipoOrderActions;  //申告履歴一覧
            l_response.displayUnitDiv = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getIpoUnitDiv();  //表示用単位区分
            
        }
        catch (NotFoundException l_nfex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_nfex.getMessage(), l_nfex);

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * 管理者IPOブックビルディング状況ダウンロード処理を実施する。<BR>
     * <BR>
     * １）　@業務日時設定<BR>
     * 　@取引時間管理.setBusinessTimestamp()をコールする。<BR>
     * <BR>
     * ２）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、<BR>管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合<BR>
     * 　@－getダウンロード画面()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、<BR>管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告分布図ﾘｸｴｽﾄの場合<BR>
     * 　@－get申告分布図()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、<BR>管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合<BR>
     * 　@－getダウンロードファ@イル()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、<BR>管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴ﾘｸｴｽﾄの場合<BR>
     * 　@－get申告履歴一覧()をコールする。<BR>
     * <BR>
     * <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E138D50095
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        
        // Timestampリセット
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        // Timestamp設定
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_processTime);
        
        if (l_request instanceof WEB3AdminIPOBookBuildingStateDownloadRequest)  //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合
        {
            l_response =  this.getDownloadScreen((WEB3AdminIPOBookBuildingStateDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIPOBookBuildingStateFileDownloadRequest)  //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合
        {
            l_response =  this.getDownloadFile((WEB3AdminIPOBookBuildingStateFileDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIPOBookBuildingHistoryRequest)  //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴ﾘｸｴｽﾄの場合
        {
            l_response =  this.getOrderActionList((WEB3AdminIPOBookBuildingHistoryRequest)l_request);
        }
        else
        {
            // Timestampリセット
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // Timestampリセット
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (calcブックビルディング状況)<BR>
     * ブックビルディング状況集計処理を行う。<BR>
     * <BR>
     * 引数.IPO申告[]の各要素毎に以下の処理を繰り返す。<BR>
     * <BR>
     * --- LOOP START ---<BR>
     * <BR>
     * １）　@取消の場合<BR>
     * 　@　@（IPO申告[index].getブックビルディング申告状態 == <BR>OrderStatusEnum.CANCELLED（：取消））<BR>
     * <BR>
     *   １－１）　@取消の集計（※ 当該顧客に有効な申告がない場合）<BR>
     * 　@　@ブックビルディング状況集計結果.add取消数量()をコールする。<BR>
     * <BR>
     * 　@　@[add取消数量()に指定する引数]<BR>
     * 　@　@数量：　@対象要素（：IPO申告）.get数量()<BR>
     * <BR>
     * ２）　@取消以外の場合<BR>
     * 　@　@（IPO申告[index].getブックビルディング申告状態 != <BR>OrderStatusEnum.CANCELLED（：取消））
     * <BR>
     * 　@２－１）　@抽選対象の集計<BR>
     * 　@　@ブックビルディング状況集計結果.add抽選対象申告数量()をコールする。<BR>
     * <BR>
     * 　@　@[add抽選対象申告数量()に指定する引数]<BR>
     * 　@　@数量：　@対象要素（：IPO申告）.get数量()<BR>
     * <BR>
     * 　@２－２）　@申告価格の集計<BR>
     * 　@　@ブックビルディング状況集計結果.add申告価格()をコールする。<BR>
     * <BR>
     * 　@　@[add申告価格()に指定する引数]<BR>
     * 　@　@申告価格：　@対象要素（：IPO申告）.get申告価格()<BR>
     * <BR>
     * 　@２－３）　@出金余力保持者の集計<BR>
     * 　@　@以下の条件に当てはまる場合、<BR>
     * 　@　@ブックビルディング状況集計結果.add出金余力保持者合計数量()をコールする。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@対象要素（：IPO申告）.申告相当額 <= 出金余力※<BR>
     * <BR>
     * 　@　@[add出金余力保持者合計数量()に指定する引数]<BR>
     * 　@　@数量：　@対象要素（：IPO申告）.get数量()<BR>
     * <BR>
     * 　@　@※　@出金余力<BR>
     * 　@　@取引余力サービスImpl.getその他商品買付可能額()の戻り値 <BR>
     * <BR>
     *     [その他商品買付可能額()に指定する引数] <BR>
     *   　@補助口座：　@対象要素（：IPO申告）.get補助口座()<BR>
     * 　@　@受渡日：　@TradingSystem.getBizDate() <BR>
     * <BR>
     * --- LOOP END ---<BR>
     * <BR>
     * @@param l_bookbuildingCounter - ブックビルディング状況集計結果オブジェクト
     * 
     * @@param l_ipoOrders - (IPO申告リスト)<BR>
     * IPO申告の配列<BR>
     * @@roseuid 40E3A04A0246
     */
    protected void calcBookbuildingSituation(WEB3AdminIpoBookbuildingCounter l_bookbuildingCounter, WEB3IpoOrderImpl[] l_ipoOrders) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcBookbuildingSituation(WEB3AdminIpoBookbuildingCounter, WEB3IpoOrderImpl[])";
        log.entering(STR_METHOD_NAME );
                
        if (l_bookbuildingCounter == null || l_ipoOrders == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        int l_intCount = l_ipoOrders.length;
        log.debug("l_intCount = " + l_intCount);

        for (int i = 0; i < l_intCount; i++)
        {
            WEB3IpoOrderImpl l_ipoOrder =  l_ipoOrders[i];
        
            if (l_ipoOrder.getOrderStatus() == OrderStatusEnum.CANCELLED)
            {
                double l_dbQuantity = l_ipoOrder.getQuantity();  //数量を取得
                l_bookbuildingCounter.addCancelQuantity(l_dbQuantity);  //取消申告を集計
            }
            else
            {
                double l_dblQuantity = l_ipoOrder.getQuantity();  //数量を取得
                l_bookbuildingCounter.addLotTargetOrderQuantity(l_dblQuantity);  //抽選対象申告を集計
            
                double l_dblPrice = l_ipoOrder.getOrderPrice();  //申告価格を取得
                l_bookbuildingCounter.addOrderPrice(l_dblPrice);  //申告価格を集計
            }
        }                    
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create申告分布明細)<BR>
     * IPO申告分布明細の配列を生成し、<BR>
     * レンジ範囲（申告価格下限／申告価格上限）をセットする。<BR>
     * <BR>
     * １）　@申告価格分布レンジ計算<BR>
     * 　@レンジ範囲、レンジ数を計算する。<BR>
     * <BR>
     * 　@レンジ範囲は刻みによって決定する。<BR>
     * レンジ数の上限は10とする。<BR>
     * レンジ数が10より大きくなる場合は、<BR>
     * レンジ範囲を刻みのn倍に広げ、10以内にする。<BR>
     * <BR>
     * 　@以下の計算を実施する。<BR>
     * <BR>
     * 　@[計算式]<BR>
     * 　@（仮条件下限値(*1) - 仮条件上限値(*2)） ÷ 刻み(*3) + 1<BR>
     * <BR>
     * 　@(*1) IPO銘柄.IPO銘柄行.仮条件下限値<BR>
     * 　@(*2) IPO銘柄.IPO銘柄行.仮条件上限値<BR>
     * 　@(*3) IPO銘柄.IPO銘柄行.刻み<BR>
     * <BR>
     * 　@－（上記の計算結果 <= 10）の場合、<BR>
     * 　@　@　@・計算結果の小数点以下を切り捨てた整数値をレンジ数とする。<BR>
     * 　@　@　@・刻みをレンジ範囲とする。<BR>
     * <BR>
     * 　@－（上記の計算結果 > 10）の場合、<BR>
     * 　@　@　@計算式内の刻みの値を、（刻み×2）に変更して再計算する。<BR>
     * 　@　@　@（再計算結果 > 10）の場合は、（刻み×3）で再計算し、<BR>
     * 　@　@　@（再計算結果 <= 10）になるまで、（刻み×n）の再計算を繰り返す。<BR>
     * <BR>
     * 　@　@　@（再計算結果 <= 10）になった場合、<BR>
     * 　@　@　@・再計算結果の小数点以下を切り捨てた整数値をレンジ数とする。<BR>
     * 　@　@　@・（刻み×n）をレンジ範囲とする。<BR>
     * <BR>
     * ２）　@申告分布明細の配列生成<BR>
     * <BR>
     * 　@１）で求めたレンジ数で、IPO申告分布明細の配列を生成する。<BR>
     * <BR>
     * 　@IPO申告分布明細オブジェクトの配列を生成する。<BR>
     * 　@配列の要素数は、１）で求めたレンジ数とする。<BR>
     * <BR>
     * ３）　@申告分布明細レンジ範囲（申告価格下限／申告価格上限）セット<BR>
     * <BR>
     * 　@２）で生成した配列の要素数分以下を繰り返す。<BR>
     * 　@// for (int index = 0; index < IPO申告分布明細の配列.length; index ++) 
     * <BR>
     * 　@--- LOOP START ---<BR>
     * <BR>
     * 　@３－１）　@インスタンス生成<BR>
     * <BR>
     * 　@IPO申告分布明細オブジェクトをインスタンス化する。<BR>
     * （new IPO申告分布明細()）<BR>
     * <BR>
     * 　@３－２）　@申告価格下限／上限セット<BR>
     * <BR>
     * 　@－実価格（IPO銘柄.isディスカウント扱い() == false）の場合<BR>
     * 　@　@IPO申告分布明細.申告価格下限 = 仮条件上限値(*2) - (index × レンジ範囲）<BR>
     * 　@　@IPO申告分布明細.申告価格上限 = 仮条件上限値(*2) - ((index + 1) × レンジ範囲）<BR>
     * <BR>
     * 　@　@※　@但し、最終要素の場合（index + 1 = 配列.length）、<BR>
     * 　@　@　@　@申告価格下限、申告価格上限の両方に、<BR>
     * {仮条件上限値(*2) － (index × レンジ範囲）}<BR>
     * 　@　@　@　@をセットする。<BR>
     * <BR>
     * 　@－ディスカウント率（IPO銘柄.isディスカウント扱い() == true）の場合<BR>
     * 　@　@IPO申告分布明細.申告価格下限 = 仮条件下限値(*1) + (index × レンジ範囲）<BR>
     * 　@　@IPO申告分布明細.申告価格上限 = 仮条件下限値(*1) + ((index + 1) × レンジ範囲）<BR>
     * <BR>
     * 　@　@※　@但し、最終要素の場合（index + 1 = 配列.length）、<BR>
     * 　@　@　@　@申告価格下限、申告価格上限の両方に、<BR>{仮条件下限値(*1) ＋ (index × レンジ範囲）}をセットする。<BR>
     * <BR>
     * 　@３－３）　@配列にセットする。<BR>
     * <BR>
     * 　@２）で生成した配列[index] = IPO申告分布明細<BR>
     * <BR>
     * 　@--- LOOP END ---<BR>
     * <BR>
     * 　@(*4) IPO銘柄.IPO銘柄行.仮条件区分<BR>
     * <BR>
     * ４）　@配列返却<BR>
     * <BR>
     * 　@IPO申告分布明細オブジェクトの配列を返却する。<BR>
     * <BR>
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * 
     * @@return webbroker3.ipo.message.WEB3IpoOrderDistributionUnit
     * @@roseuid 40E4DE57009F
     */
    protected WEB3IPODemandDistributionUnit createOrderDistributionDetails(WEB3IpoProductImpl l_ipoProduct) 
    {
        //xx
     return null;
    }
    
    /**
     * (calc申告分布値)<BR>
     * IPO申告分布明細に申告分布値をセットし、平均申告価格を返却する。<BR>
     * <BR>
     * 件数／価格の集計<BR>
     * <BR>
     * IPO申告[]の各要素毎に以下の処理を繰り返す。<BR>
     * // for (int index = 0; index < IPO申告.length; index ++)<BR>
     * <BR>
     * --- LOOP START ---<BR>
     * <BR>
     * １）　@申告価格取得<BR>
     * 　@申告価格 = 対象要素（：IPO申告）.get申告価格()<BR>
     * <BR>
     * ２）　@件数カウント<BR>
     * <BR>
     * 　@－ディスカウント率（IPO銘柄.isディスカウント扱い() == true）の場合<BR>
     * 　@　@[条件]<BR>
     * 　@　@IPO申告分布明細[n].申告価格下限 > 申告価格 >= <BR>
     * IPO申告分布明細[n].申告価格上限<BR>
     *  　@　@<BR>
     * 　@　@[申告件数のインクリメント]<BR>
     * 　@　@IPO申告分布明細[n].申告件数 = IPO申告分布明細[n].申告件数 + 1
     * <BR>
     * 　@－実価格（IPO銘柄.isディスカウント扱い() == false）の場合<BR>
     * 　@　@引数の申告分布明細リスト（：IPO申告分布明細[]）の要素のうち、<BR>
     * 　@　@以下の条件に当てはまる要素の申告件数をインクリメントする。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@IPO申告分布明細[n].申告価格下限 < <BR>
     * 申告価格 <= IPO申告分布明細[n].申告価格上限<BR>
     *  　@　@<BR>
     * 　@　@[申告件数のインクリメント]<BR>
     * 　@　@IPO申告分布明細[n].申告件数 = IPO申告分布明細[n].申告件数 + 1<BR>
     * <BR>
     * ３）　@申告価格集計<BR>
     * 　@ブックビルディング状況集計結果.add抽選対象申告件数()をコールする。<BR>
     * 　@ブックビルディング状況集計結果.add申告価格()をコールする。<BR>
     * <BR>
     * 　@[add申告価格()に指定する引数]<BR>
     * 　@申告価格：　@申告価格<BR>
     * <BR>
     * --- LOOP END ---<BR>
     * <BR>
     * <BR>
     * @@param l_orderDistributionUnit - (申告分布明細リスト)<BR>
     * IPO申告分布明細の配列。<BR>
     * <BR>
     * ※　@指定前に各要素のインスタンス化、レンジの設定が必要。<BR>
     * 
     * @@param l_bookbuildingCounter - ブックビルディング状況集計結果オブジェクト
     * 
     * @@param l_ipoOrder - IPO申告の配列
     * 
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * @@roseuid 40E5065C03DB
     */
    protected void calcOrderDistributionPrice(WEB3IPODemandDistributionUnit l_orderDistributionUnit, WEB3AdminIpoBookbuildingCounter l_bookbuildingCounter, WEB3IpoOrderImpl l_ipoOrder, WEB3IpoProductImpl l_ipoProduct) 
    {
        //xx
     
    }
    
    /**
     * (create無効オペレーション履歴ファ@イル)<BR>
     * ダウンロード処理対象のデータを取得し、sortして返却する。<BR>
     * ※　@無効オペレーション履歴<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・ＢＢ状況ＤＬ）create無効オペレーション履歴ファ@イル」参照。<BR>
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * @@return String[]
     * @@roseuid 40EE7EBF0234
     */
    protected String[] createInvalidOperationActionFiles(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createInvalidOperationActionFiles(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME );
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        String[] l_invalidOperationActionFiles = null;
        
        //無効オペレーション履歴CSV(IPO銘柄)
        WEB3AdminIpoInvalidOperationCsv l_ipoInvalidOperationCsv = new WEB3AdminIpoInvalidOperationCsv(l_ipoProduct);
        
        //get無効ブックビルディング申告履歴
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoBookbuildingOrderActionImpl[] l_ipoBookBuildingOrderActions = l_ipoOrderManagerImpl.getInvalidOrderActions(l_ipoProduct.getProductId());
                 
        if (l_ipoBookBuildingOrderActions == null)
        {
            log.exiting(STR_METHOD_NAME);
            //U01801                       
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01981,
                STR_METHOD_NAME);
        }
                
        //sort
//        Comparator[] l_comparator = new Comparator[3];
//        l_comparator[0] = new WEB3IpoBookbuildingOrderActionBranchCodeComparator(WEB3AscDescDef.ASC);
//        l_comparator[1] = new WEB3IpoBookbuildingOrderActionAccountCodeComparator(WEB3AscDescDef.ASC);
//        l_comparator[2] = new WEB3IpoBookbuildingOrderActionCreatedTimestampComparator(WEB3AscDescDef.ASC);
//        WEB3ArraysUtility.sort(l_ipoBookBuildingOrderActions, l_comparator);
        
        //ブックビルディング申告履歴[]各要素毎のLOOP処理
        int l_intCount = l_ipoBookBuildingOrderActions.length;
        for (int i = 0; i < l_intCount; i ++)
        {
            WEB3IpoBookbuildingOrderActionImpl l_ipoBookBuildingOrderAction = l_ipoBookBuildingOrderActions[i];
            
            //明細行を追加
            int l_intRow = l_ipoInvalidOperationCsv.addRow();
            
            //ブックビルディング申告履歴行オブジェクトを取得
            IpoBookbuildingOrderActionRow l_ipoBookBuildingOrderActionRow = (IpoBookbuildingOrderActionRow)l_ipoBookBuildingOrderAction.getDataSourceObject();
            IpoBookbuildingOrderActionParams l_params = new IpoBookbuildingOrderActionParams(l_ipoBookBuildingOrderActionRow);
            
            //部店コードをセット
            long l_lngBranchCode = l_params.branch_id;
            l_ipoInvalidOperationCsv.setBranchCode(l_intRow, l_lngBranchCode);
            
            //顧客コード、顧客名をセット
            try
            {
                //口座ＩＤを取得
                long l_lngAccountId = l_ipoBookBuildingOrderAction.getAccountId();
                //1.3.5.set扱者コード(int, long)
                l_ipoInvalidOperationCsv.setTradeCode(l_intRow, l_lngAccountId);
                l_ipoInvalidOperationCsv.setAccount(l_intRow, l_lngAccountId);
            }
            catch (WEB3BaseException l_ex)
            {
                l_ipoInvalidOperationCsv.deleteRow(l_intRow);
                continue;
            }
            
            //作成日時を取得
            Timestamp l_timestamp = l_ipoBookBuildingOrderAction.getOrderActionTimestamp();
            
            //受付日時をセット
            l_ipoInvalidOperationCsv.setReceptionDate(l_intRow, l_timestamp);
            
            //数量を取得
            double l_dblQuantity = l_ipoBookBuildingOrderAction.getQuantity();
            
            //申告数量をセット、申告価格をセット
            l_ipoInvalidOperationCsv.setOrderQuantity(l_intRow, l_dblQuantity);   //申告数量をセット
            if (l_params.getPriceIsNull())
            {
                l_ipoInvalidOperationCsv.setOrderPrice(l_intRow, (0.0D / 0.0D));  //申告価格をセット
            }
            else
            {
                l_ipoInvalidOperationCsv.setOrderPrice(l_intRow, l_params.getPrice());  //申告価格をセット
            }
            
            //ブックビルディング申告状態を取得
            OrderStatusEnum l_orderStatusEnum = l_ipoBookBuildingOrderAction.getOrderStatus();
            
            //処理内容をセット
            l_ipoInvalidOperationCsv.setProcessing(l_intRow, l_orderStatusEnum);
            
            //指値を取得
            double l_dblPrice = l_ipoBookBuildingOrderAction.getPrice();
            
            //指値／成行をセット
            l_ipoInvalidOperationCsv.setLimitPriceMarketOrder(l_intRow, l_dblPrice);
        }
        
        //CSVファ@イル行を取得
        l_invalidOperationActionFiles = l_ipoInvalidOperationCsv.getCsvFileLines();
        
        log.exiting(STR_METHOD_NAME);
        return l_invalidOperationActionFiles;
    }
    
    /**
     * (createブックビルディング状況ファ@イル)<BR>
     * ダウンロード処理対象のデータを取得し、sortして返却する。<BR>
     * ※　@ブックビルディング状況<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・ＢＢ状況ＤＬ）createブックビルディング状況ファ@イル」参照。<BR>
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * @@param l_request - 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * @@return String[]
     * @@roseuid 40EE7EEF00CC
     */
    protected String[] createBookbuildingStateFiles(
        WEB3IpoProductImpl l_ipoProduct,
        WEB3AdminIPOBookBuildingStateFileDownloadRequest l_request
    ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createBookbuildingStateFiles(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME );
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        String[] l_bookbuildingStateFiles = null;
        
        //1.1.ブックビルディング状況CSVデータモデルを生成
        WEB3AdminIpoDemandListCsv l_ipoDemandListCsv = new WEB3AdminIpoDemandListCsv(l_ipoProduct);
        
        //1.2.銘柄に該当するIPO申告を取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoOrderImpl[] l_ipoOrders =
            l_ipoOrderManagerImpl.getOrderUnits(
                l_ipoProduct,
                l_request.branchCode,
                l_request.accountCodeFrom,
                l_request.accountCodeTo,
                l_request.bbCreatedTimestampFrom,
                l_request.bbCreatedTimestampTo
            );
        if (l_ipoOrders == null)
        {
            log.exiting(STR_METHOD_NAME);
            //U01801                       
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01980,
                STR_METHOD_NAME);
        }
        
        
        //1.3.sort
//        Comparator[] l_comparator = new Comparator[1];
//        l_comparator[0] = new WEB3IpoOrderBookbuildingCreatedTimestampComparator(WEB3AscDescDef.ASC);
//        WEB3ArraysUtility.sort(l_ipoOrders, l_comparator);
        
        //1.4.IPO申告[]各要素毎のLOOP処理
        int l_intCount = l_ipoOrders.length;
        for (int i = 0; i < l_intCount; i ++)
        {
            WEB3IpoOrderImpl l_ipoOrder = l_ipoOrders[i];
            
            //1.4.1.明細行を追加
            int l_intRow = l_ipoDemandListCsv.addRow();
            
            //1.4.2.部店ＩＤを取得
            long l_lngBranchCode = l_ipoOrder.getBranchId();
            
            //1.4.3.部店コードをセット            
            l_ipoDemandListCsv.setBranchCode(l_intRow, l_lngBranchCode);
            
            
            try
            {
                //1.4.4.口座ＩＤを取得
                long l_lngAccountId = l_ipoOrder.getAccountId();
                //1.3.5.set扱者コード(int, long)
                l_ipoDemandListCsv.setTradeCode(l_intRow, l_lngAccountId);
                //1.4.5.顧客名をセット
                l_ipoDemandListCsv.setAccount(l_intRow, l_lngAccountId);
            }
            //1.4.6.顧客オブジェクト取得失敗した場合
            catch (WEB3BaseException l_ex)
            {
                //1.4.6.1.delete明細行
                l_ipoDemandListCsv.deleteRow(l_intRow);
                continue;
            }
            
            //1.4.7.新規申告日時を取得
            Timestamp l_timestamp = ((IpoOrderRow)l_ipoOrder.getDataSourceObject()).getBookbuildingCreatedTimestamp();
            
            //1.4.8.新規申告日時をセット
            l_ipoDemandListCsv.setBookbuildingCreatedTimestamp(l_intRow, l_timestamp);
            
            //1.4.9.数量を取得
            double l_dblQuantity = l_ipoOrder.getQuantity();
            
            //1.4.10.申告数量をセット
            l_ipoDemandListCsv.setOrderQuantity(l_intRow, l_dblQuantity);
            
            //1.4.11.指値を取得
            double l_dblLimitPrice = l_ipoOrder.getLimitPrice();
            
            //1.4.12.指値／成行をセット
            l_ipoDemandListCsv.setLimitPriceMarketOrderPrice(l_intRow, l_dblLimitPrice);
            
            //IPO申告行オブジェクトを取得
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            
            //1.4.13.申告価格をセット
            if (!l_ipoOrderRow.getPriceIsNull())
            {
                double l_dblPrice = l_ipoOrderRow.getPrice();
                l_ipoDemandListCsv.setOrderPrice(l_intRow, l_dblPrice);
            }
            else
            {
                l_ipoDemandListCsv.setOrderPrice(l_intRow, (0.0D / 0.0D));
            }
            
            if (!l_ipoOrderRow.getBookbuildingPriceIsNull())
            {
                //1.4.14.申告相当額をセット
                double l_dblBookBuildingPrice = l_ipoOrderRow.getBookbuildingPrice();
                l_ipoDemandListCsv.setBookbuildingPrice(l_intRow, l_dblBookBuildingPrice);
            }
            else
            {
                l_ipoDemandListCsv.setBookbuildingPrice(l_intRow, (0.0D / 0.0D));
            }

            //1.4.15.基準値をセット
            if (!l_ipoOrderRow.getCurrentPriceIsNull())
            {
                double l_dblCurrentPrice = l_ipoOrderRow.getCurrentPrice();
                l_ipoDemandListCsv.setBasePrice(l_intRow, l_dblCurrentPrice);
            }
            else
            {
                l_ipoDemandListCsv.setBasePrice(l_intRow, (0.0D / 0.0D));
            }
            
            //BB状況データ（余力あり）
            if (l_request.fileTypeCode.equals(WEB3IpoBBStateFileTypeDef.BB_STATE_TP_ON))
            {
                //get補助口座()
                WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) l_ipoOrder.getSubAccount();
            
                //getその他商品買付可能額(補助口座,受渡日)
                TradingSystem l_trdSys = l_finApp.getTradingSystem();         
                WEB3TPTradingPowerService l_tpTPS  
                    = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                double l_trdPow = l_tpTPS.getOtherTradingPower(l_subAccount, l_trdSys.getBizDate() );

                //出金余力をセット
                l_ipoDemandListCsv.setTradingPower(l_intRow, l_trdPow);
            
                //申告額保持者をセット
                if (!l_ipoOrderRow.getBookbuildingPriceIsNull())
                {
                    double l_dblBookBuildingPrice = l_ipoOrderRow.getBookbuildingPrice();
                
                    l_ipoDemandListCsv.setTradingPowerCheck(l_intRow, l_dblBookBuildingPrice, l_trdPow);
                }
                else
                {
                    l_ipoDemandListCsv.setTradingPowerCheck(l_intRow, (0.0D / 0.0D), l_trdPow);
                }
            }
            //BB状況データ（余力なし）
            else
            {
                //出金余力をセット
                l_ipoDemandListCsv.setTradingPowerWithoutIndicate(l_intRow);
                //申告額保持者をセット
                l_ipoDemandListCsv.setTradingPowerCheckWithoutIndicate(l_intRow);
            }
            
            //1.4.20.ブックビルディング申告状態を取得
            OrderStatusEnum l_orderStatusEnum = l_ipoOrder.getOrderStatus();
            
            //1.4.21.申告状態をセット
            l_ipoDemandListCsv.setOrderStatus(l_intRow, l_orderStatusEnum);
        }
        
        //1.5.CSVファ@イル行を取得
        l_bookbuildingStateFiles = l_ipoDemandListCsv.getCsvFileLines();
        
        log.exiting(STR_METHOD_NAME);
        return l_bookbuildingStateFiles;
    }
    
    /**
     * (createFew無効オペレーション履歴ファ@イル)<BR>
     * ダウンロード処理対象のデータを取得し、sortして返却する。<BR>
     * ※　@無効オペレーション履歴<BR>
     * （扱者コードなし） <BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・ＢＢ状況ＤＬ）createFew無効オペレーション履歴ファ@イル」参照。<BR>
     * <BR>
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 40EE7EBF0234
     */
    protected String[] createFewInvalidOperationFiles(
        WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createFewInvalidOperationFiles(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME );

        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        String[] l_invalidOperationActionFewFiles = null;

        //無効オペレーション履歴FewCSVデータモデルを生成する。
        //[コンストラクタの引数]
        //IPO銘柄：　@IPO銘柄オブジェクト
        WEB3AdminIpoInvalidOperationFewCsv l_invalidOperationFewCsv =
            new WEB3AdminIpoInvalidOperationFewCsv(l_ipoProduct);

        //get無効ブックビルディング申告履歴
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl =
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoBookbuildingOrderActionImpl[] l_ipoBookBuildingOrderActions =
            l_ipoOrderManagerImpl.getInvalidOrderActions(l_ipoProduct.getProductId());

        if (l_ipoBookBuildingOrderActions == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01981,
                STR_METHOD_NAME);
        }

        //取得したＢＢ申告履歴[]各要素毎のLOOP処理
        int l_intCountActions = l_ipoBookBuildingOrderActions.length;
        for (int i = 0; i < l_intCountActions; i ++)
        {
            WEB3IpoBookbuildingOrderActionImpl l_ipoBookBuildingOrderAction =
                l_ipoBookBuildingOrderActions[i];

            //明細行を追加
            int l_intRow = l_invalidOperationFewCsv.addRow();

            //ブックビルディング申告履歴行オブジェクトを取得
            IpoBookbuildingOrderActionRow l_ipoBookBuildingOrderActionRow =
                (IpoBookbuildingOrderActionRow)l_ipoBookBuildingOrderAction.getDataSourceObject();

            //部店コードをセット
            long l_lngBranchCode = l_ipoBookBuildingOrderActionRow.getBranchId();
            l_invalidOperationFewCsv.setBranchCode(l_intRow, l_lngBranchCode);

            //顧客コード、顧客名をセット
            try
            {
                //口座ＩＤを取得
                long l_lngAccountId = l_ipoBookBuildingOrderAction.getAccountId();

                // set顧客(int, long)
                l_invalidOperationFewCsv.setAccount(l_intRow, l_lngAccountId);
            }
            // 顧客オブジェクト取得に失敗した場合
            catch (WEB3BaseException l_ex)
            {
                //delete明細行(行番号 : int)
                l_invalidOperationFewCsv.deleteRow(l_intRow);

                //continue
                continue;
            }

            // get作成日時
            Timestamp l_timestamp = l_ipoBookBuildingOrderAction.getOrderActionTimestamp();

            // set受付日時
            l_invalidOperationFewCsv.setReceptionDate(l_intRow, l_timestamp);

            // get数量
            double l_dblQuantity = l_ipoBookBuildingOrderAction.getQuantity();

            // set申告数量
            l_invalidOperationFewCsv.setOrderQuantity(l_intRow, l_dblQuantity);

            // set申告価格
            if (l_ipoBookBuildingOrderActionRow.getPriceIsNull())
            {
                l_invalidOperationFewCsv.setOrderPrice(l_intRow, (0.0D / 0.0D));
            }
            else
            {
                l_invalidOperationFewCsv.setOrderPrice(l_intRow, l_ipoBookBuildingOrderActionRow.getPrice());
            }

            // getブックビルディング申告状態
            OrderStatusEnum l_orderStatusEnum = l_ipoBookBuildingOrderAction.getOrderStatus();

            // set処理内容
            l_invalidOperationFewCsv.setProcessing(l_intRow, l_orderStatusEnum);

            // get指値
            double l_dblPrice = l_ipoBookBuildingOrderAction.getPrice();

            // set指値／成行値
            l_invalidOperationFewCsv.setLimitPriceMarketOrder(l_intRow, l_dblPrice);
        }

        // getCSVファ@イル行
        l_invalidOperationActionFewFiles = l_invalidOperationFewCsv.getCsvFileLines();

        log.exiting(STR_METHOD_NAME);
        return l_invalidOperationActionFewFiles;
    }   
    
    /**
     * (createFewブックビルディング状況ファ@イル)<BR>
     * ダウンロード処理対象のデータを取得し、sortして返却する。<BR>
     * ※　@ブックビルディング状況<BR>
     * （扱者コードなし） <BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・ＢＢ状況ＤＬ）createFewブックビルディング状況ファ@イル」参照。<BR>
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPOブックビルディング状況ファ@イルダウンロードリクエストデータオブジェクト<BR>
     * <BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 40EE7EBF0234
     */
    protected String[] createFewBookbuildingStateFiles(
        WEB3IpoProductImpl l_ipoProduct,
        WEB3AdminIPOBookBuildingStateFileDownloadRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createFewBookbuildingStateFiles(WEB3IpoProductImpl, " +
            "WEB3AdminIPOBookBuildingStateFileDownloadRequest)";
        log.entering(STR_METHOD_NAME );

        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // ブックビルディング状況fewCSV
        WEB3AdminIpoDemandListFewCsv l_csv =
            new WEB3AdminIpoDemandListFewCsv(l_ipoProduct);

        // getIPO申告(IPO銘柄, String[], String, String, Date, Date)
        // [getIPO申告()に指定する引数]
        // IPO銘柄：　@IPO銘柄
        // 引数.リクエストデータ.部店コードの配列
        // 引数.リクエストデータ.顧客コードfrom
        // 引数.リクエストデータ.顧客コードto
        // 引数.リクエストデータ.新規申告日時from
        // 引数.リクエストデータ.新規申告日時to
        String[] l_strBranchCodes = l_request.branchCode;
        String l_strAccountCodeFrom = l_request.accountCodeFrom;
        String l_strAccountCodeTo = l_request.accountCodeTo;
        Date l_datBbCreatedTimestampFrom = l_request.bbCreatedTimestampFrom;
        Date l_datBbCreatedTimestampTo = l_request.bbCreatedTimestampTo;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl =
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoOrderImpl[] l_ipoOrder =
            (WEB3IpoOrderImpl[])l_ipoOrderManagerImpl.getOrderUnits(
                l_ipoProduct,
                l_strBranchCodes,
                l_strAccountCodeFrom,
                l_strAccountCodeTo,
                l_datBbCreatedTimestampFrom,
                l_datBbCreatedTimestampTo);

        if (l_ipoOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01980,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // 取得したIPO申告[]各要素毎のLOOP処理
        int l_intIpoOrder = l_ipoOrder.length;
        for (int i = 0; i < l_intIpoOrder; i++)
        {
            // add明細行( )
            int l_intAddRow = l_csv.addRow();

            // get部店ID( )
            long l_lngBranchId = l_ipoOrder[i].getBranchId();

            // set部店コード(int, long)
            // [set部店コード()に指定する引数]
            // 行番号：　@（add明細行()の戻り値）
            // 部店ＩＤ：　@IPO申告[index].get部店ＩＤ()
            l_csv.setBranchCode(l_intAddRow, l_lngBranchId);

            try
            {
                // get口座ID( )
                long l_lngAccountId = l_ipoOrder[i].getAccountId();

                // set顧客(int, long)
                l_csv.setAccount(l_intAddRow, l_lngAccountId);
            }
            // 顧客オブジェクト取得に失敗した場合
            catch(WEB3BaseException l_ex)
            {
                // delete明細行(行番号 : int)
                l_csv.deleteRow(l_intAddRow);

                // continue
                continue;
            }

            // getDataSourceObject( )
            IpoOrderRow l_ipoOrderRow =
                (IpoOrderRow)l_ipoOrder[i].getDataSourceObject();

            // set新規申告日時(int, Timestamp)
            // [set新規申告日時()に指定する引数]
            // 行番号：　@（add明細行()の戻り値）
            // 新規申告日時：　@IPO申告[index].IPO申告行.新規申告日時
            Timestamp l_tsBookbuildingCreate =
                l_ipoOrderRow.getBookbuildingCreatedTimestamp();
            l_csv.setBookbuildingCreatedTimestamp(
                l_intAddRow,
                l_tsBookbuildingCreate);

            // get数量( )
            double l_dbQuantity = l_ipoOrder[i].getQuantity();

            // set申告数量(int, double)
            // [set申告数量()に指定する引数]
            // 行番号：　@（add明細行()の戻り値）
            // 申告数量：　@IPO申告[index].get数量()
            l_csv.setOrderQuantity(l_intAddRow, l_dbQuantity);

            // get指値( )
            double l_dbLimitPrice = l_ipoOrder[i].getLimitPrice();

            // set指値／成行値(int, double)
            // [set指値／成行()に指定する引数]
            // 行番号：　@（add明細行()の戻り値）
            // 指値：　@IPO申告[index].get指値()
            l_csv.setLimitPriceMarketOrderPrice(l_intAddRow, l_dbLimitPrice);

            // set申告価格(int, double)
            // [set申告価格()に指定する引数]
            // 行番号：　@（add明細行()の戻り値）
            // 申告価格：　@IPO申告[index].IPO申告行.計算単価
            if (l_ipoOrderRow.getPriceIsNull())
            {
                l_csv.setOrderPrice(l_intAddRow, (0.0D / 0.0D));
            }
            else
            {
                l_csv.setOrderPrice(l_intAddRow, l_ipoOrderRow.getPrice());
            }

            // set申告相当額(int, double)
            // [set申告相当額()に指定する引数]
            // 行番号：　@（add明細行()の戻り値）
            // 申告価格：　@IPO申告[index].IPO申告行.申告相当額
            if (l_ipoOrderRow.getBookbuildingPriceIsNull())
            {
                l_csv.setBookbuildingPrice(l_intAddRow, (0.0D / 0.0D));
            }
            else
            {
                l_csv.setBookbuildingPrice(l_intAddRow, l_ipoOrderRow.getBookbuildingPrice());
            }

            // set基準値(int, double)
            // [set基準値()に指定する引数]
            // 行番号：　@（add明細行()の戻り値）
            // 申告価格：　@IPO申告[index].IPO申告行.基準値（時価）
            if (l_ipoOrderRow.getCurrentPriceIsNull())
            {
                l_csv.setBasePrice(l_intAddRow, (0.0D / 0.0D));
            }
            else
            {
                l_csv.setBasePrice(l_intAddRow, l_ipoOrderRow.getCurrentPrice());
            }

            // BB状況データ（余力あり）のとき
            if (WEB3IpoBBStateFileTypeDef.BB_STATE_TP_ON.equals(
                l_request.fileTypeCode))
            {
                // get補助口座( )
                SubAccount l_dbSubAccount = l_ipoOrder[i].getSubAccount();

                // getその他商品買付可能額(補助口座 : 補助口座, 受渡日 : Date)
                TradingSystem l_trdSys = l_finApp.getTradingSystem();
                WEB3TPTradingPowerService l_tpTPS =
                    (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                double l_trdPow = l_tpTPS.getOtherTradingPower(
                    (WEB3GentradeSubAccount)l_dbSubAccount,
                    l_trdSys.getBizDate());

                // set出金余力(int, double)
                // [set出金余力()に指定する引数]
                // 行番号：　@（add明細行()の戻り値）
                // 出金余力：　@（getその他商品買付可能額()の戻り値)
                l_csv.setTradingPower(l_intAddRow, l_trdPow);

                // set申告額保持者(int, double, double)
                l_csv.setTradingPowerCheck(
                    l_intAddRow, l_ipoOrderRow.getBookbuildingPrice(), l_trdPow);
            }

            // BB状況データ（余力なし）のとき
            if (WEB3IpoBBStateFileTypeDef.BB_STATE_TP_OFF.equals(
                    l_request.fileTypeCode))
            {
                // set出金余力－余力表示なし(int)
                l_csv.setTradingPowerWithoutIndicate(l_intAddRow);

                // set申告額保持者－余力表示なし(int)
                l_csv.setTradingPowerCheckWithoutIndicate(l_intAddRow);
            }

            // getブックビルディング申告状態( )
            OrderStatusEnum l_orderStatus = l_ipoOrder[i].getOrderStatus();

            // set申告状態(int, OrderStatusEnum)
            // [set申告状態()に指定する引数]
            // 行番号：　@（add明細行()の戻り値）
            // IPO申告有効状態：　@IPO申告[index].getブックビルディング申告状態()
            l_csv.setOrderStatus(l_intAddRow, l_orderStatus);
        }
        // getCSVファ@イル行( )
        String[] l_strCsvFileLines = l_csv.getCsvFileLines();

        log.exiting(STR_METHOD_NAME);
        return l_strCsvFileLines;
    }
}
@
