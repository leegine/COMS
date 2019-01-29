head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultOfferDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ実装(WEB3AdminIpoLotResultOfferDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/26 斉麟 (中訊) 新規作成
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>043
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>064,072
Revesion History : 2005/12/20 譚漢江 (中訊) 仕様変更No.104,No.105修正
                 : 2006/11/10 何文敏 (中訊) 仕様変更No.162
                 : 2006/11/10 何文敏 (中訊) 仕様変更No.164
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3EnableIpoQuantityChangeDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ipo.WEB3AdminIpoLotBuyListCsv;
import webbroker3.ipo.WEB3AdminIpoLotBuyListFewCsv;
import webbroker3.ipo.WEB3AdminIpoLotResultOfferCounter;
import webbroker3.ipo.WEB3AdminIpoLotResultUploadCsv;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3IpoCsvDivDef;
import webbroker3.ipo.define.WEB3IpoKeyItemDef;
import webbroker3.ipo.define.WEB3LotResultDivDef;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferResponse;
import webbroker3.ipo.message.WEB3IPOLotResultUnit;
import webbroker3.ipo.message.WEB3IPOOfferConditionUnit;
import webbroker3.ipo.message.WEB3IPOSortKey;
import webbroker3.ipo.message.WEB3IPOUploadHistoryUnit;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultOfferDownloadService;
import webbroker3.ipo.service.delegate.WEB3IpoUploadActionUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ実装クラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultOfferDownloadServiceImpl implements WEB3AdminIpoLotResultOfferDownloadService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoLotResultOfferDownloadServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE30041
     */
    public WEB3AdminIpoLotResultOfferDownloadServiceImpl() 
    {
     
    }
    
    /**
     * 管理者IPO抽選結果購入申込状況ダウンロード処理を実施する。<BR>
     * <BR>
     * １）　@業務日時設定<BR>
     * 　@取引時間管理.setBusinessTimestamp()をコールする。<BR>
     * <BR>
     * ２）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、<BR>
     * 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合<BR>
     * 　@－getダウンロード画面()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、<BR>
     * 管理者IPO抽選結果購入申込状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合<BR>
     * 　@－getダウンロードファ@イル()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、<BR>
     * 管理者IPO抽選結果購入申込状況ﾘｸｴｽﾄの場合<BR>
     * 　@－get抽選結果購入申込状況()をコールする。<BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40E140D400D3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_genResponse = null;
        
        // Timestampリセット
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        // Timestamp設定
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_processTime);
        
        if (l_request instanceof WEB3AdminIPOLotResultOfferDownloadRequest)  //管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合
        {
            l_genResponse =  this.getDownloadScreen((WEB3AdminIPOLotResultOfferDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIPOLotResultOfferFileDownloadRequest)  //管理者IPO抽選結果購入申込状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合
        {
            l_genResponse =  this.getDownloadFile((WEB3AdminIPOLotResultOfferFileDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIPOLotResultOfferRequest)  //管理者IPO抽選結果購入申込状況ﾘｸｴｽﾄの場合
        {
            l_genResponse =  this.getLotResultOfferState((WEB3AdminIPOLotResultOfferRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIPOLotResultOfferListRequest)  //管理者IPO抽選結果購入申込状況一覧ﾘｸｴｽﾄ
        {
            l_genResponse =  this.getLotResultOfferStateList((WEB3AdminIPOLotResultOfferListRequest)l_request);
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
        return l_genResponse;
    }
    
    /**
     * (getダウンロード画面)<BR>
     * IPO管理者IPO抽選結果購入申込状況画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果購入申込状況ＤＬ）getダウンロード画面」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「IPO（管理者・ＢＢ状況ＤＬ）getダウンロード画面」）: <BR>
     *         1.7(*1) （is中止() == true Or is削除() == true Or isブックビルディング開始() == 
     * false）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00587<BR>
     * ==========================================================<BR>
     * <BR>
     * ==========================================================<BR>
     * シーケンス図(「IPO（管理者・ＢＢ状況ＤＬ）getダウンロード画面」）: <BR>
     *         1.5(*1) 削除銘柄（is削除銘柄() == true）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00588<BR>
     * ==========================================================<BR>
     * @@param l_request - 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadResponse
     * @@roseuid 40E140CC0160
     */
    protected WEB3AdminIPOLotResultOfferDownloadResponse getDownloadScreen(WEB3AdminIPOLotResultOfferDownloadRequest l_request)
                throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminIPOLotResultOfferDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ
        WEB3AdminIPOLotResultOfferDownloadResponse l_response = (WEB3AdminIPOLotResultOfferDownloadResponse)l_request.createResponse();
        
        try
        {
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //IPO銘柄
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //1.4.is削除銘柄()
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            log.debug("is削除銘柄() = " + l_blnDeletionProduct);        
        
            //1.5.(is削除銘柄() == true)の場合、例外をスローする。
            if(l_blnDeletionProduct)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);            
            }
            
            //1.6.is中止()
            boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
            log.debug("is中止() = " + l_blnDiscontinuation); 
            
            //1.7.(is中止() == true)の場合、例外をスローする。
            if(l_blnDiscontinuation)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.8.isブックビルディング終了()
            boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
            log.debug("isブックビルディング終了() = " + l_blnBookBuildingEnd);
            
            //1.9.(isブックビルディング終了() == false)の場合、例外をスローする。
            if(!l_blnBookBuildingEnd)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            
            //1.10.is新規抽選終了()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();           
            
            //1.11.(is新規抽選終了() == false)の場合、例外をスローする。          
            if(!l_blnNewLotteryEnd)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //有効IPO申告
            WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
            WEB3IpoOrderImpl[] l_ipoOrders = l_ipoOrderManagerImpl.getOpenOrderUnits(l_ipoProduct.getProductId(), null);
                       
			if (l_ipoOrders == null){
				 log.exiting(STR_METHOD_NAME);
				 throw new WEB3BusinessLayerException(
				     WEB3ErrorCatalog.BUSINESS_ERROR_01037,
				     this.getClass().getName() + STR_METHOD_NAME);
			}
        
            //抽選結果・購入申込状況集計結果を生成
            WEB3AdminIpoLotResultOfferCounter l_ipoLotResultOfferCounter = new WEB3AdminIpoLotResultOfferCounter(l_ipoProduct);
            
            int l_intCount = l_ipoOrders.length;
            for (int i = 0; i < l_intCount; i++)
            {
                WEB3IpoOrderImpl l_ipoOrder = l_ipoOrders[i];
                this.calcLotResultOfferState(l_ipoLotResultOfferCounter, l_ipoOrder);
            }
            
            //（当選者）IPO購入申込概況明細を生成
            WEB3IPOOfferConditionUnit l_iopOfferConditionUnit1 = new WEB3IPOOfferConditionUnit();
                
            l_iopOfferConditionUnit1.offerQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerApplicationQuantity());  //購入申込数量
            l_iopOfferConditionUnit1.offerNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerOfferNumber());  //購入申込件数
            l_iopOfferConditionUnit1.declineQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDeclineQuantity());  //辞退数量
            l_iopOfferConditionUnit1.declineNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDeclineNumber());  //辞退件数
            l_iopOfferConditionUnit1.undecideQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerUndecideQuantity()); //未定数量
            l_iopOfferConditionUnit1.undecideNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerUndecideNumber());  //未定件数
            l_iopOfferConditionUnit1.totalQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerTotalQuantity());  //合計数量
            l_iopOfferConditionUnit1.totalNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerTotalNumber());   //合計件数
            l_iopOfferConditionUnit1.decisionQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDecisionQuantity());  //購入確定数量
            l_iopOfferConditionUnit1.decisionNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDecisionNumber());  //購入確定件数
            l_iopOfferConditionUnit1.declineRejectedQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDeclineRejectedQuantity());  //辞退落選数量
            l_iopOfferConditionUnit1.declineRejectedNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getPrizerDeclineRejectedNumber());  //辞退落選件数
                
            //（落選者）IPO購入申込概況明細を生成
            WEB3IPOOfferConditionUnit l_iopOfferConditionUnit2 = new WEB3IPOOfferConditionUnit();
                
            l_iopOfferConditionUnit2.offerQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingApplicationQuantity());  //購入申込数量
            l_iopOfferConditionUnit2.offerNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingOfferNumber());  //購入申込件数
            l_iopOfferConditionUnit2.declineQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingDeclineQuantity());  //辞退数量
            l_iopOfferConditionUnit2.declineNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingDeclineNumber());  //辞退件数
            l_iopOfferConditionUnit2.undecideQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingUndecideQuantity()); //未定数量
            l_iopOfferConditionUnit2.undecideNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingUndecideNumber());  //未定件数
            l_iopOfferConditionUnit2.totalQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingTotalQuantity());  //合計数量
            l_iopOfferConditionUnit2.totalNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingTotalNumber());   //合計件数
            l_iopOfferConditionUnit2.decisionQuantity
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingDecisionQuantity());  //購入確定数量
            l_iopOfferConditionUnit2.decisionNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingDecisionNumber());  //購入確定件数
            l_iopOfferConditionUnit2.declineRejectedNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getWaitingDeclineRejectedNumber());  //辞退落選件数                
                
            //（落選者）IPO購入申込概況明細を生成
            WEB3IPOOfferConditionUnit l_iopOfferConditionUnit3 = new WEB3IPOOfferConditionUnit();
                
            l_iopOfferConditionUnit3.totalNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getRejectedNumber());   //落選件数
            l_iopOfferConditionUnit3.declineRejectedNumber
                        = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getRejectedNumber());  //落選件数
                
            //抽選結果アップロードデータモデルを生成
            WEB3AdminIpoLotResultUploadCsv l_iopLotResultUploadcsv = new WEB3AdminIpoLotResultUploadCsv(l_ipoProduct);
                
            //最新のアップロード履歴を取得
            AdministratorUploadParams l_uploadParams = (AdministratorUploadParams)l_iopLotResultUploadcsv.getLatestUploadAction(l_ipoProductId);
                
            //アップロード履歴明細を作成
            WEB3IpoUploadActionUnitService l_service = (WEB3IpoUploadActionUnitService)Services.getService(WEB3IpoUploadActionUnitService.class);
            WEB3IPOUploadHistoryUnit l_ipoUploadActionUnit = l_service.createUploadActionUnit(l_uploadParams);
                
            //レスポンスデータを生成
            IpoProductRow l_ipoProductRow = (IpoProductRow)(l_ipoProduct.getDataSourceObject());
            l_response.productCode = l_ipoProductRow.getProductCode();  //銘柄コード
            l_response.productName = l_ipoProductRow.getStandardName(); //銘柄名
            l_response.uploadHistory = l_ipoUploadActionUnit;  //アップロード履歴
            if (!l_ipoProductRow.getDealingQuantityIsNull())
            {
                l_response.handlingQuantity 
                        = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getDealingQuantity()); //当社取扱数量
            }
            l_response.allotQuantity 
                    = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getAllotQuantity());  //割当確定数量
            l_response.advanceQuantity 
                    = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getAdvanceQuantity());  //繰上可能数量
            l_response.advanceWaitQuantity
                    = WEB3StringTypeUtility.formatNumber(l_ipoLotResultOfferCounter.getAdvanceWaitQuantity());  //繰上待ち数量
            l_response.prizedOfferCondition = l_iopOfferConditionUnit1;  //当選者購入申込概況
            l_response.waitingOfferCondition = l_iopOfferConditionUnit2;  //補欠者購入申込概況
            l_response.rejectedOfferCondition = l_iopOfferConditionUnit3;  //落選者購入申込概況
            l_response.displayUnitDiv = l_ipoProductRow.getIpoUnitDiv();  //表示用単位区分
            
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * IPO管理者・抽選結果購入申込状況ダウンロードファ@イルデータ取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果購入申込状況ＤＬ）getダウンロードファ@イル」参照。<BR>
     * @@param l_request - 管理者IPO抽選結果購入申込状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadResponse
     * @@roseuid 40E140CC018F
     */
    protected WEB3AdminIPOLotResultOfferFileDownloadResponse getDownloadFile(WEB3AdminIPOLotResultOfferFileDownloadRequest l_request)
                throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminIPOLotResultOfferFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        
        //1.17.管理者IPO抽選結果購入申込状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ
        WEB3AdminIPOLotResultOfferFileDownloadResponse l_response = (WEB3AdminIPOLotResultOfferFileDownloadResponse)l_request.createResponse();
        
        try
        {
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //1.3.IPO銘柄
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //1.4.is削除銘柄()
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            log.debug("is削除銘柄() = " + l_blnDeletionProduct);        
        
            //1.5.(is削除銘柄() == true)の場合、例外をスローする。
            if(l_blnDeletionProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);            
            
            }
            
            //1.6.is中止()
            boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
            log.debug("is中止() = " + l_blnDiscontinuation); 
            
            //1.7.(is中止() == true)の場合、例外をスローする。
            if(l_blnDiscontinuation)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }
            
            //1.8.isブックビルディング終了()
            boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
            log.debug("isブックビルディング終了() = " + l_blnBookBuildingEnd);
            
            //1.9.(isブックビルディング終了() == false)の場合、例外をスローする。
            if(!l_blnBookBuildingEnd)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            } 
            
            //1.10.is新規抽選終了()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();           
            
            //1.11.(is新規抽選終了() == false)の場合、例外をスローする。          
            if(!l_blnNewLotteryEnd)
            {

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }

            // getDataSourceObject()
            l_ipoProduct.getDataSourceObject();

            // getダウンロードデータ(IPO銘柄, IPOソートキー[])
            WEB3IpoOrderImpl[] l_ipoOrders = this.getDownloadData(l_ipoProduct, null);

            // リクエストデータ：CSV区分 = "1"（追加項目あり） の場合、以下の処理を実行
            if (WEB3IpoCsvDivDef.ADD_ITEM_ON.equals(l_request.csvDiv))
            {
                // 抽選結果・購入申込状況CSV(IPO銘柄)
                WEB3AdminIpoLotBuyListCsv l_ipoLotResultOfferCsv =
                    new WEB3AdminIpoLotBuyListCsv(l_ipoProduct);

                // create購入申込CSV情報
                String[] l_strIpoLotBuyListInfo =
                    this.creatIpoLotBuyListInfo(l_ipoOrders, l_ipoLotResultOfferCsv);

                // リクエストデータ：CSV区分 = "1"（追加項目あり） の場合,create購入申込CSV情報()の戻り値
                l_response.downloadFile = l_strIpoLotBuyListInfo;
            }

            // リクエストデータ：CSV区分 = "0"（追加項目なし） の場合、以下の処理を実行
            else if (WEB3IpoCsvDivDef.ADD_ITEM_OFF.equals(l_request.csvDiv))
            {
                // 抽選結果・購入申込状況FewCSV(IPO銘柄)
                WEB3AdminIpoLotBuyListFewCsv l_ipoLotBuyListFewCsv =
                    new WEB3AdminIpoLotBuyListFewCsv(l_ipoProduct);

                // create購入申込FewCSV情報
                String[] l_strIpoLotBuyListFewInfo =
                    this.creatIpoLotBuyListFewInfo(l_ipoOrders, l_ipoLotBuyListFewCsv);

                // リクエストデータ：CSV区分 = "0"（追加項目なし） の場合,create購入申込FewCSV情報()の戻り値
                l_response.downloadFile = l_strIpoLotBuyListFewInfo;
            }

            // レスポンスデータ.現在日時： （TradingSystem.getSystemTimestamp()）の戻り値
            l_response.currentDate = l_finApp.getTradingSystem().getSystemTimestamp();
        }
        catch (NotFoundException l_nfex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get抽選結果購入申込状況)<BR>
     * IPO抽選結果・購入申込状況データ取得処理を行う。<BR>
     * （管理者IPO抽選結果・購入申込状況画面に表示）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果購入申込状況ＤＬ）get抽選結果購入申込状況」参照。<BR>
     * @@param l_request - 管理者IPO抽選結果購入申込状況ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultOfferResponse
     * @@roseuid 40E140CC017F
     */
    protected WEB3AdminIPOLotResultOfferResponse getLotResultOfferState(WEB3AdminIPOLotResultOfferRequest l_request)
                throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getLotResultOfferState(WEB3AdminIPOLotResultOfferRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);

        //1.20.管理者IPO抽選結果購入申込状況ﾚｽﾎﾟﾝｽ
        WEB3AdminIPOLotResultOfferResponse l_response = (WEB3AdminIPOLotResultOfferResponse)l_request.createResponse();
        
        try
        {
            //1.3.IPO銘柄
            long l_ipoProductId = Long.parseLong(l_request.id);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3IpoProductManagerImpl l_ipoProductManager 
                        = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct 
                        = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //1.4.is削除銘柄()
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            log.debug("is削除銘柄() = " + l_blnDeletionProduct);        
        
            //1.5.(is削除銘柄() == true)の場合、例外をスローする。
            if(l_blnDeletionProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);            
            
            }
            
            //1.6.is中止()
            boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
            log.debug("is中止() = " + l_blnDiscontinuation); 
            
            //1.7.(is中止() == true)の場合、例外をスローする。
            if(l_blnDiscontinuation)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }
            
            //1.8.isブックビルディング終了()
            boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
            log.debug("isブックビルディング終了() = " + l_blnBookBuildingEnd);
            
            //1.9.(isブックビルディング終了() == false)の場合、例外をスローする。
            if(!l_blnBookBuildingEnd)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            } 
            
            //1.10.is新規抽選終了()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();           
            
            //1.11.(is新規抽選終了() == false)の場合、例外をスローする。          
            if(!l_blnNewLotteryEnd)
            {

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }        
               
            //1.12.証券会社コードを取得
            String l_strInstitutionCode = l_administartor.getInstitutionCode();
            
            //1.13.部店コードを取得
            String l_branchCode = l_request.branchCode;
//            String l_branchCode = l_administartor.getBranchCode();
            
            //1.14.get顧客()
            WEB3GentradeAccountManager l_genAccountMgr = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            MainAccount l_mainAccount = 
                    l_genAccountMgr.getMainAccount(
                                        l_strInstitutionCode,
                                        l_branchCode,
                                        l_request.accountCode); 
                        
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            
			//1.15.顧客表示名を取得
			// 2004/12/10 障害管理票No.U00545 顧客名の値を「顧客の名前(苗字)＋ "　@" ＋名前(名前)」になるように修正。情野@@SRA START 
			String l_strName = l_mainAccountRow.getFamilyName();
//			  String l_strName = l_mainAccountRow.getFamilyName() + " " + l_mainAccountRow.getGivenName();
			// 2004/12/10 障害管理票No.U00545 顧客名の値を「顧客の名前(苗字)＋ "　@" ＋名前(名前)」になるように修正。情野@@SRA END
            
            //1.16.補助口座を取得
            SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            //1.17.指定顧客／指定IPO銘柄のIPO申告オブジェクト
            WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
            WEB3IpoOrderImpl l_ipoOrder = l_ipoOrderManagerImpl.getOrderUnit(l_subAccount, l_ipoProductId);        
        
			if (l_ipoOrder == null){
				 
				log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + STR_METHOD_NAME);
			}
        
            //1.18.(*5)新規申告日時がﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ終了前でない場合は例外をスローする
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            
            IpoProductRow l_ipoProductRow = (IpoProductRow) l_ipoProduct.getDataSourceObject();
            Timestamp l_ipoOrderDate = l_ipoOrderRow.getBookbuildingCreatedTimestamp();
            Timestamp l_ipoProductBBEnd = l_ipoProductRow.getBookbuildingEndDatetime();
            if(WEB3DateUtility.compareToMinute(l_ipoOrderDate,l_ipoProductBBEnd)>=0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01745,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            
            //1.19.(*6)取消済みのIPO申告の場合は例外をスローする
            if(l_ipoOrder.getOrderStatus().equals(OrderStatusEnum.CANCELLED))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01746,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            
            //1.21.レスポンスデータを生成
            l_response.traderCode = l_mainAccountRow.getSonarTraderCode();  //扱者コード
            l_response.accountName = l_strName;  //顧客名


			//公開価格 20060120修正
			if (l_ipoProductRow.getPublicPriceIsNull())
			{
				l_response.publicOfferingPrice = null;
			}
			else
			{
				l_response.publicOfferingPrice = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());
			}
            //l_response.publicOfferingPrice = 
            //    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());  //公開価格
            
            // 信用区分　@2006.04.27 SCS佐藤 追加************************************************************
            WEB3GentradeMainAccount l_genMainAccount = 
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
                
            if(l_genMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)){
                
                l_response.marginDiv = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN;
            }
            else{
                l_response.marginDiv = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN;
            }
            // 抽選番号　@2006.04.27 SCS佐藤
            l_response.lotNumber = l_ipoOrderRow.getLotNumber();
            // *************************************** 修正　@end ******************************************

            String l_strLotResult = l_ipoOrderRow.getLotResult();  //抽選結果
            String l_strLotResultRetry = l_ipoOrderRow.getLotResultRetry();  //抽選結果（繰上）
            
            //抽選結果区分
            if (WEB3LotResultDef.ELECTION.equalsIgnoreCase(l_strLotResult))
            {
                l_response.lotResultDiv = WEB3LotResultDef.ELECTION;  //当選
            }
            else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.ELECTION.equals(l_strLotResultRetry))
            {
                l_response.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT_ELECTION;  //補欠当選
            }
            else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.DEFEAT.equals(l_strLotResultRetry))
            {
                l_response.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT_DEFEAT;  //補欠落選
            }
            else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.DEFAULT.equals(l_strLotResultRetry))
            {
                l_response.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT;  //補欠
            }
            else if (WEB3LotResultDef.DEFEAT.equalsIgnoreCase(l_strLotResult))
            {
                l_response.lotResultDiv = WEB3LotResultDivDef.DEFEAT;  //落選
            }
            
            if (!l_ipoOrderRow.getElectedQuantityIsNull())
            {
                l_response.prizeQuantity = WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getElectedQuantity());  //当選数量
            }
            if (!l_ipoOrderRow.getApplicationQuantityIsNull())
            {
                l_response.offerQuantity = WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getApplicationQuantity());  //購入申込数量
            }

            l_response.offerCancelDate = l_ipoOrderRow.getOfferingTimestamp();  //購入申込辞退日時
            
            String l_strOfferDiv = l_ipoOrderRow.getOfferingDiv();  //購入申込区分
            if (WEB3OfferingDivDef.DEFAULT.equals(l_strOfferDiv))
            {
                l_response.offerStateDiv = null;
            }
            else
            {
                l_response.offerStateDiv = l_strOfferDiv;
            }
            
            l_response.receiveStateDiv = l_ipoOrderRow.getAcceptStatus();  //受付状態区分
            if (!l_ipoOrderRow.getSubstitutePriorityIsNull())
            {
                l_response.priority = WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getSubstitutePriority());  //優先順位
            }
            
            //IPO購入申込数量変更
            Institution l_institution = l_administartor.getInstitution();
            InstitutionRow l_institutionRow = (InstitutionRow)(l_institution.getDataSourceObject());
            String l_strEnableIpoQuantityChange = l_institutionRow.getEnableIpoQuantityChange();
            
            //購入申込数量変更可能フラグ
            if (WEB3EnableIpoQuantityChangeDef.CAN_CHANGE.equals(l_strEnableIpoQuantityChange))
            {
                l_response.offerQuantityFlag = true;
            }
            else
            {
                l_response.offerQuantityFlag = false;
            }
            
            l_response.displayUnitDiv = ((IpoProductRow)(l_ipoOrder.getProduct().getDataSourceObject())).getIpoUnitDiv();  //表示用単位区分
            
            
        }
        catch (NotFoundException l_nfex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get抽選結果購入申込状況一覧)<BR>
     * IPO抽選結果・購入申込状況一覧データ取得処理を行う。<BR>
     * （管理者IPO抽選結果・購入申込状況一覧画面に表示）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果購入申込状況ＤＬ）get抽選結果購入申込状況一覧」参照。<BR>
     * @@param l_request - 管理者IPO抽選結果購入申込状況一覧ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListResponse
     * @@roseuid 40EE069802BF
     */
    protected WEB3AdminIPOLotResultOfferListResponse getLotResultOfferStateList(WEB3AdminIPOLotResultOfferListRequest l_request)
                throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getLotResultOfferStateList(WEB3AdminIPOLotResultOfferListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,false);
        //証券会社オブジェクトを取得
        Institution l_institution = l_administartor.getInstitution();
        
        //管理者IPO抽選結果購入申込状況一覧ﾚｽﾎﾟﾝｽ
        WEB3AdminIPOLotResultOfferListResponse l_response = (WEB3AdminIPOLotResultOfferListResponse)l_request.createResponse();
        
        try
        {
            long l_ipoProductId = Long.parseLong(l_request.id);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //IPO銘柄
            WEB3IpoProductManagerImpl l_ipoProductManager = (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)(l_ipoProductManager.getProduct(l_ipoProductId));  //thorw NotFoundException
            
            //1.4.is削除銘柄()
            boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
            log.debug("is削除銘柄() = " + l_blnDeletionProduct);        
        
            //1.5.(is削除銘柄() == true)の場合、例外をスローする。
            if(l_blnDeletionProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);            
            
            }
            
            //1.6.is中止()
            boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
            log.debug("is中止() = " + l_blnDiscontinuation); 
            
            //1.7.(is中止() == true)の場合、例外をスローする。
            if(l_blnDiscontinuation)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }
            
            //1.8.isブックビルディング終了()
            boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
            log.debug("isブックビルディング終了() = " + l_blnBookBuildingEnd);
            
            //1.9.(isブックビルディング終了() == false)の場合、例外をスローする。
            if(!l_blnBookBuildingEnd)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            } 
            
            //1.10.is新規抽選終了()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();           
            
            //1.11.(is新規抽選終了() == false)の場合、例外をスローする。          
            if(!l_blnNewLotteryEnd)
            {

                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }           
            
            //抽選結果・購入申込一覧ダウンロードファ@イルデータを取得
            WEB3IpoOrderImpl[] l_ipoOrders = this.getDownloadData(l_ipoProduct, l_request.sortKeys);           
            if (!WEB3StringTypeUtility.isNumber(l_request.pageSize) || !WEB3StringTypeUtility.isNumber(l_request.pageIndex))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
			//ArrayListを生成
			ArrayList l_lstIopLotResultUnit = new ArrayList();
			for (int i = 0; i < l_ipoOrders.length; i++)
			{
				WEB3IpoOrderImpl l_ipoOrder = l_ipoOrders[i];
                
				//IPO申告行オブジェクトを取得
				IpoOrderRow l_ipoOrderRow = (IpoOrderRow)(l_ipoOrder.getDataSourceObject());
                
				//抽選結果明細メッセージデータオブジェクトを生成
				WEB3IPOLotResultUnit l_iopLotResultUnit = new WEB3IPOLotResultUnit();
                
				long l_lnBranchId = l_ipoOrder.getBranchId();  //部店ＩＤを取得
				long l_lnAccountId = l_ipoOrder.getAccountId();  //口座ＩＤを取得
                
				Branch l_branch = l_finApp.getAccountManager().getBranch(l_lnBranchId);  //throws NotFoundException
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_lnAccountId);  //throws NotFoundException
                
				MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
                
				//抽選結果明細メッセージデータプロパティに値をセット
				l_iopLotResultUnit.branchCode = l_branch.getBranchCode();  //部店コード
                l_iopLotResultUnit.traderCode = l_mainAccountRow.getSonarTraderCode();  //扱者コード
				l_iopLotResultUnit.accountCode = l_mainAccount.getDisplayAccountCode();  //表示顧客コード
//              2005/01/22 障害管理票No.U00545 にあわせて修正。坂上@@SRA START
				l_iopLotResultUnit.accountName = l_mainAccountRow.getFamilyName() ;  //顧客名
//                l_iopLotResultUnit.accountName = l_mainAccountRow.getFamilyName() + " " + l_mainAccountRow.getGivenName();  //顧客名
                IpoProductRow l_ipoProductRow = (IpoProductRow) l_ipoProduct.getDataSourceObject();

				//公開価格 20060120修正
				if (l_ipoProductRow.getPublicPriceIsNull())
				{
					l_iopLotResultUnit.publicOfferingPrice = null;
				}
				else
				{
					l_iopLotResultUnit.publicOfferingPrice = WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());
				}
                //l_iopLotResultUnit.publicOfferingPrice = 
                //    WEB3StringTypeUtility.formatNumber(l_ipoProductRow.getPublicPrice());  //公開価格

                // 信用区分　@2006.04.27 SCS佐藤 追加************************************************************
                if(l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)){
                
                    l_iopLotResultUnit.marginDiv = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN;
                }
                else{
                    l_iopLotResultUnit.marginDiv = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN;
                }
                // 抽選番号　@2006.04.27 SCS佐藤
                l_iopLotResultUnit.lotNumber = l_ipoOrderRow.getLotNumber();
                // *************************************** 修正　@end ******************************************

//              2005/01/22 障害管理票No.U00545 にあわせて修正。坂上@@SRA 
				String l_strLotResult = l_ipoOrderRow.getLotResult();  //抽選結果
				String l_strLotResultRetry = l_ipoOrderRow.getLotResultRetry();  //抽選結果（繰上）
                
				//抽選結果区分
				if (WEB3LotResultDef.ELECTION.equalsIgnoreCase(l_strLotResult))
				{
					l_iopLotResultUnit.lotResultDiv = WEB3LotResultDef.ELECTION;  //当選
				}
				else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.ELECTION.equals(l_strLotResultRetry))
				{
					l_iopLotResultUnit.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT_ELECTION;  //補欠当選
				}
				else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.DEFEAT.equals(l_strLotResultRetry))
				{
					l_iopLotResultUnit.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT_DEFEAT;  //補欠落選
				}
				else if (WEB3LotResultDef.SUPPLEMENT.equalsIgnoreCase(l_strLotResult) && WEB3LotResultRetryDef.DEFAULT.equals(l_strLotResultRetry))
				{
					l_iopLotResultUnit.lotResultDiv = WEB3LotResultDivDef.SUPPLEMENT;  //補欠
				}
				else if (WEB3LotResultDef.DEFEAT.equalsIgnoreCase(l_strLotResult))
				{
					l_iopLotResultUnit.lotResultDiv = WEB3LotResultDivDef.DEFEAT;  //落選
				}
				if (!l_ipoOrderRow.getElectedQuantityIsNull())
				{
					l_iopLotResultUnit.prizeQuantity 
							= WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getElectedQuantity());  //当選数量
				}
				if (!l_ipoOrderRow.getApplicationQuantityIsNull())
				{
					l_iopLotResultUnit.offerQuantity 
							= WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getApplicationQuantity());  //購入申込数量
				}

				l_iopLotResultUnit.offerCancelDate = l_ipoOrderRow.getOfferingTimestamp();  //購入申込辞退日時
                
				String l_strOfferDiv = l_ipoOrderRow.getOfferingDiv();  //購入申込区分
				if (WEB3OfferingDivDef.DEFAULT.equals(l_strOfferDiv))
				{
					l_iopLotResultUnit.offerStateDiv = null;
				}
				else
				{
					l_iopLotResultUnit.offerStateDiv = l_strOfferDiv;
				}
                
				l_iopLotResultUnit.receiveStateDiv = l_ipoOrderRow.getAcceptStatus();  //受付状態区分
				if (!l_ipoOrderRow.getSubstitutePriorityIsNull())
				{
					l_iopLotResultUnit.priority = WEB3StringTypeUtility.formatNumber(l_ipoOrderRow.getSubstitutePriority());  //優先順位
				}
                
				//ArrayListに抽選結果明細を追加
				l_lstIopLotResultUnit.add(l_iopLotResultUnit);
			}
            
			//抽選結果明細Listを配列に変換
			WEB3IPOLotResultUnit[] l_ipoLotResultUnits = new WEB3IPOLotResultUnit[l_lstIopLotResultUnit.size()];
			l_lstIopLotResultUnit.toArray(l_ipoLotResultUnits);
            
			//ページをめくる処理クラスを使用
			int l_intpageIndex = Integer.parseInt(l_request.pageIndex);
			int l_intpageSize  = Integer.parseInt(l_request.pageSize);
			WEB3PageIndexInfo l_pageInfo = new WEB3PageIndexInfo(l_lstIopLotResultUnit,l_intpageIndex,l_intpageSize);
                
			//レスポンスデータを生成
			IpoProductRow l_ipoProductRow = (IpoProductRow)(l_ipoProduct.getDataSourceObject());
                
			l_response.productCode = l_ipoProductRow.getProductCode();  //銘柄コード
			l_response.productName = l_ipoProductRow.getStandardName();  //銘柄名
			l_response.lotResultList = (WEB3IPOLotResultUnit[]) l_pageInfo.getArrayReturned(WEB3IPOLotResultUnit.class);
//			  l_response.lotResultList = l_ipoLotResultUnits;  //抽選結果一覧
			l_response.displayUnitDiv = l_ipoProductRow.getIpoUnitDiv();  //表示用単位区分
            
			//IPO購入申込数量変更
			InstitutionRow l_institutionRow = (InstitutionRow)(l_institution.getDataSourceObject());
			String l_strEnableIpoQuantityChange = l_institutionRow.getEnableIpoQuantityChange();
            
			//購入申込数量変更可能フラグ
			if (WEB3EnableIpoQuantityChangeDef.CAN_CHANGE.equals(l_strEnableIpoQuantityChange))
			{
				l_response.offerQuantityFlag = true;
			}
			else
			{
				l_response.offerQuantityFlag = false;
			}
			l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_pageInfo.getTotalPages());
			l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_pageInfo.getTotalRecords());
			l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_pageInfo.getPageIndex());
            
		}
		catch (NotFoundException l_nfex)
		{
			log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
		}
        
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
    
    /**
     * (getダウンロードデータ)<BR>
     * 処理対象のデータを取得し、sortして返却する。<BR>
     * <BR>
     * １）　@データ取得<BR>
     * 　@引数のIPO銘柄に該当するIPO申告オブジェクトで有効なものを取得する。<BR>
     * <BR>
     * 　@[get有効IPO申告()に指定する引数]<BR>
     * 　@IPO銘柄ＩＤ：　@IPO銘柄.getIPO銘柄ＩＤ()<BR>
     * <BR>
     * ２）　@sort<BR>
     * 　@１）で取得したIPO申告の配列を、<BR>
     * WEB3ArraysUtility.sort()にてsortし返却する。<BR>
     * <BR>
     * 　@○ ソートキー指定なし（引数.IPOソートキー == null）の場合<BR>
     * <BR>
     * 　@[sortに指定する引数]<BR>
     * 　@Object[]：　@１）で取得したIPO申告の配列<BR>
     * 　@Comparator[]：　@<BR>
     * 　@　@com[0] = new IPO申告.抽選結果Comparator(昇順（：asc）)<BR>
     * 　@　@com[1] = new IPO申告.抽選結果（繰上）Comparator(昇順（：asc）)<BR>
     * 　@　@com[2] = new IPO申告.購入申込区分Comparator(昇順（：asc）)<BR>
     * 　@　@com[3] = new IPO申告.優先順位Comparator(昇順（：asc）)<BR>
     * 　@　@com[4] = new IPO申告.顧客コードComparator(昇順（：asc）)<BR>
     * <BR>
     * 　@○ ソートキー指定（引数.IPOソートキー != null）の場合<BR>
     * <BR>
     * 　@[sortに指定する引数]<BR>
     * 　@Object[]：　@１）で取得したIPO申告の配列<BR>
     * 　@Comparator[]：　@<BR>
     * 　@　@※　@引数.IPOソートキー[]の配列順序で、<BR>
     * 指定の通りComparatorを生成して指定する。<BR>
     * <BR>
     * 　@　@－（IPOソートキー.キー項目 == IPO抽選結果明細.部店コード）の場合<BR>
     * 　@　@　@new IPO申告.部店コードComparator(IPOソートキー.昇順／降順)<BR>
     * <BR>
     * 　@　@－（IPOソートキー.キー項目 == IPO抽選結果明細.顧客コード）の場合<BR>
     * 　@　@　@new IPO申告.顧客コードComparator(IPOソートキー.昇順／降順)<BR>
     * <BR>
     * 　@　@－（IPOソートキー.キー項目 == IPO抽選結果明細.抽選結果区分）の場合<BR>
     * 　@　@　@new IPO申告.抽選結果Comparator(IPOソートキー.昇順／降順)<BR>
     * 　@　@　@new IPO申告.抽選結果（繰上）Comparator(IPOソートキー.昇順／降順)<BR>
     * <BR>
     * 　@　@－（IPOソートキー.キー項目 == IPO抽選結果明細.当選数量）の場合<BR>
     * 　@　@　@new IPO申告.当選数量Comparator(IPOソートキー.昇順／降順)<BR>
     * <BR>
     * 　@　@－（IPOソートキー.キー項目 == IPO抽選結果明細.購入申込数量）の場合<BR>
     * 　@　@　@new IPO申告.購入申込数量Comparator(IPOソートキー.昇順／降順)<BR>
     * <BR>
     * 　@　@－（IPOソートキー.キー項目 == IPO抽選結果明細.購入申込／辞退日時）の場合<BR>
     * 　@　@　@new IPO申告.購入申込／辞退日時Comparator(IPOソートキー.昇順／降順)<BR>
     * <BR>
     * 　@　@－（IPOソートキー.キー項目 == IPO抽選結果明細.購入申込状況区分）の場合<BR>
     * 　@　@　@new IPO申告.購入申込区分Comparator(IPOソートキー.昇順／降順)
     * <BR>
     * 　@　@－（IPOソートキー.キー項目 == IPO抽選結果明細.受付状態区分）の場合<BR>
     * 　@　@　@new IPO申告.受付状態Comparator(IPOソートキー.昇順／降順)<BR>
     * <BR>
     * 　@　@－（IPOソートキー.キー項目 == IPO抽選結果明細.優先順位）の場合<BR>
     * 　@　@　@new IPO申告.優先順位Comparator(IPOソートキー.昇順／降順)<BR>
     * <BR>
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * @@param l_ipoSortKeys - IPOソートキー
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     * @@roseuid 40ECD557008E
     */
    protected WEB3IpoOrderImpl[] getDownloadData(WEB3IpoProductImpl l_ipoProduct, WEB3IPOSortKey[] l_ipoSortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDownloadData(WEB3IpoProductImpl, WEB3IPOSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        String sortCond = "";
        
        if (l_ipoSortKeys == null)
        {
//            Comparator[] l_comparator = new Comparator[5];
//            //IPO申告.抽選結果Comparator(昇順（：asc）)
//            l_comparator[0] = new WEB3IpoOrderLotResultComparator(WEB3AscDescDef.ASC);  
//            //IPO申告.抽選結果（繰上）Comparator(昇順（：asc）)
//            l_comparator[1] = new WEB3IpoOrderLotResultRetryComparator(WEB3AscDescDef.ASC);  
//            //IPO申告.購入申込区分Comparator(昇順（：asc）)
//            l_comparator[2] = new WEB3IpoOrderOfferingDivComparator(WEB3AscDescDef.ASC);  
//            //IPO申告.優先順位Comparator(昇順（：asc）)
//            l_comparator[3] = new WEB3IpoOrderSubstitutePriorityComparator(WEB3AscDescDef.ASC);
//            //IPO申告.顧客コードComparator(昇順（：asc）)
//            l_comparator[4] = new WEB3IpoOrderAccountCodeComparator(WEB3AscDescDef.ASC);
//            WEB3ArraysUtility.sort(l_ipoOrders, l_comparator);
            
            sortCond = " lot_result, lot_result_retry, offering_div, substitute_priority asc ";
        }
        else if (l_ipoSortKeys != null)
        {
            int l_intCount = l_ipoSortKeys.length;
            int l_lastIndex = l_intCount - 1;
            //ArrayList l_comparators = new ArrayList();
            
            for (int i = 0; i < l_intCount; i++)
            {
                //（IPOソートキー.キー項目 == IPO抽選結果明細.部店コード）の場合
                if (WEB3IpoKeyItemDef.BRANCH_CODE.equals(l_ipoSortKeys[i].keyItem))
                {
                    //l_comparators.add(new WEB3IpoOrderBranchCodeComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "BRANCH_ID ASC";
                    } else 
                    {
                        sortCond = sortCond + "BRANCH_ID DESC";
                    }
                }
                
                //（IPOソートキー.キー項目 == IPO抽選結果明細.顧客コード）の場合
                if (WEB3IpoKeyItemDef.ACCOUNT_CODE.equals(l_ipoSortKeys[i].keyItem))
                {
                    //l_comparators.add(new WEB3IpoOrderAccountCodeComparator(l_ipoSortKeys[i].ascDesc));
                    continue;
                }
                
                //（IPOソートキー.キー項目 == IPO抽選結果明細.抽選結果区分）の場合
                if (WEB3IpoKeyItemDef.LOT_RESULT_DIV.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderLotResultComparator(l_ipoSortKeys[i].ascDesc));
                    //comparators.add(new WEB3IpoOrderLotResultRetryComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "LOT_RESULT ASC, LOT_RESULT_RETRY ASC";
                    } else 
                    {
                        sortCond = sortCond + "LOT_RESULT DESC, LOT_RESULT_RETRY DESC";
                    }
                }
                
                //（IPOソートキー.キー項目 == IPO抽選結果明細.当選数量）の場合
                if (WEB3IpoKeyItemDef.PRIZE_QUANTITY.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderElectedQuantityComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "ELECTED_QUANTITY ASC";
                    } else 
                    {
                        sortCond = sortCond + "ELECTED_QUANTITY DESC";
                    }
                }
                
                //（IPOソートキー.キー項目 == IPO抽選結果明細.購入申込数量）の場合
                if (WEB3IpoKeyItemDef.OFFER_QUANTITY.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderApplicationQuantityComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "APPLICATION_QUANTITY ASC";
                    } else 
                    {
                        sortCond = sortCond + "APPLICATION_QUANTITY DESC";
                    }
                }
                
                //（IPOソートキー.キー項目 == IPO抽選結果明細.購入申込／辞退日時）の場合
                if (WEB3IpoKeyItemDef.OFFER_CANCEL_DATE.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderOfferingTimestampComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "OFFERING_TIMESTAMP ASC";
                    } else 
                    {
                        sortCond = sortCond + "OFFERING_TIMESTAMP DESC";
                    }
                }
                
                //（IPOソートキー.キー項目 == IPO抽選結果明細.購入申込状況区分）の場合
                if (WEB3IpoKeyItemDef.OFFER_STATE_DIV.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderOfferingDivComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "OFFERING_DIV ASC";
                    } else 
                    {
                        sortCond = sortCond + "OFFERING_DIV DESC";
                    }
                }
                
                //（IPOソートキー.キー項目 == IPO抽選結果明細.受付状態区分）の場合
                if (WEB3IpoKeyItemDef.RECEIVE_STATE_DIV.equals(l_ipoSortKeys[i].keyItem))
                {
                    //comparators.add(new WEB3IpoOrderAcceptStatusComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "ACCEPT_STATUS ASC";
                    } else 
                    {
                        sortCond = sortCond + "ACCEPT_STATUS DESC";
                    }
                }
                
                //（IPOソートキー.キー項目 == IPO抽選結果明細.優先順位）の場合
                if (WEB3IpoKeyItemDef.PRIORITY.equals(l_ipoSortKeys[i].keyItem))
                {
                    //l_comparators.add(new WEB3IpoOrderSubstitutePriorityComparator(l_ipoSortKeys[i].ascDesc));
                    if (!sortCond.equals(""))
                    {
                        sortCond = sortCond + ", ";
                    }
                    if (l_ipoSortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
                    {
                        sortCond = sortCond + "SUBSTITUTE_PRIORITY ASC";
                    } else 
                    {
                        sortCond = sortCond + "SUBSTITUTE_PRIORITY DESC";
                    }
                }
            }
        }
        //データ取得
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoOrderImpl[] l_ipoOrders = l_ipoOrderManagerImpl.getOpenOrderUnits(
            l_ipoProduct.getProductId(), sortCond
            );
        
		if (l_ipoOrders == null){
			 log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
		}
        
//            Comparator[] l_comparator = new Comparator[l_comparators.size()];
//            l_comparators.toArray(l_comparator);
//            
//            WEB3ArraysUtility.sort(l_ipoOrders, l_comparator);
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoOrders;
    }
    
    /**
     * (calc抽選結果購入申込状況)<BR>
     * 対象IPO申告オブジェクトについて、抽選結果／購入申込数量、件数を加算する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果購入申込状況ＤＬ）calc抽選結果購入申込状況」参照。<BR>
     * @@param l_lotResultOfferCounter - 抽選結果・購入申込状況集計結果オブジェクト
     * @@param l_ipoOrder - IPO申告オブジェクト
     * @@roseuid 40EBA9010118
     */
    protected void calcLotResultOfferState(WEB3AdminIpoLotResultOfferCounter l_lotResultOfferCounter, WEB3IpoOrderImpl l_ipoOrder) 
    {
        final String STR_METHOD_NAME = " calcLotResultOfferState(WEB3AdminIpoLotResultOfferCounter, WEB3IpoOrderImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lotResultOfferCounter == null || l_ipoOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //行オブジェクトを取得
        IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
        
        if (l_ipoOrder.isElected())
        {
            //当選者購入申込数量を加算
            if (l_ipoOrder.isOffered())
            {
                l_lotResultOfferCounter.addPrizerApplicationQuantity(l_ipoOrderRow.getApplicationQuantity());
            }
            
            //当選者辞退数量を加算
            if (l_ipoOrder.isDecline())
            {
                l_lotResultOfferCounter.addPrizerDeclineQuantity(l_ipoOrderRow.getElectedQuantity());
            }
            
            //当選者で、購入申込／辞退オペレーション未済の数量を加算
            if (!l_ipoOrder.isOffered() && !l_ipoOrder.isDecline())
            {
                l_lotResultOfferCounter.addPrizerUndecideQuantity(l_ipoOrderRow.getElectedQuantity());
            }
        }
        
        if (l_ipoOrder.isWaiting())
        {
            //補欠者購入申込数量を加算
            if (l_ipoOrder.isOffered())
            {
                l_lotResultOfferCounter.addWaitingApplicationQuantity(l_ipoOrderRow.getApplicationQuantity());
            }
            
            //補欠者辞退数量を加算
            if (l_ipoOrder.isDecline())
            {
                l_lotResultOfferCounter.addWaitingDeclineQuantity(l_ipoOrderRow.getElectedQuantity());
            }
            
            //補欠者で、購入申込／辞退オペレーション未済の数量を加算
            if (!l_ipoOrder.isOffered() && !l_ipoOrder.isDecline())
            {
                l_lotResultOfferCounter.addWaitingUndecideQuantity(l_ipoOrderRow.getElectedQuantity());
            }
            
            //補欠当選数量を加算
            if (WEB3LotResultRetryDef.ELECTION.equals(l_ipoOrderRow.getLotResultRetry()) && l_ipoOrder.isOffered())
            {
                l_lotResultOfferCounter.addWaitingPrizeQuantity(l_ipoOrderRow.getElectedQuantity());
            }
            
            //補欠落選件数をインクリメント
            if (WEB3LotResultRetryDef.DEFEAT.equals(l_ipoOrderRow.getLotResultRetry()))
            {
                l_lotResultOfferCounter.addWaitingRejectedNumber();
            }
            
            //補欠落選件数をインクリメント
            if (WEB3LotResultRetryDef.DEFAULT.equals(l_ipoOrderRow.getLotResultRetry()))
            {
                if (!l_ipoOrder.isDecline() && !l_ipoOrder.isOffered() 
                        && ((WEB3IpoProductImpl)l_ipoOrder.getProduct()).isOfferEnd())
                {
                    l_lotResultOfferCounter.addWaitingRejectedNumber();
                }
            }
        }
        
        //落選件数をインクリメント
        if (l_ipoOrder.isRejected())
        {
            l_lotResultOfferCounter.addRejectedNumber();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create購入申込CSV情報)<BR>
     * 抽選結果・購入申込状況CSV情報を生成する。<BR>
     * <BR>
     * シーケンス図：「IPO（管理者・抽選結果購入申込状況DL）create購入申込CSV情報」参照<BR>
     * <BR>
     * @@param l_ipoOrder - (IPO申告[])<BR>
     * IPO申告行[]
     * @@param l_csv - (抽選結果・購入申込状況CSV)<BR>
     * 抽選結果・購入申込状況CSVオブジェクト
     * @@return String[]
     * @@throws WEB3BaseException 
     */
    protected String[] creatIpoLotBuyListInfo(
        WEB3IpoOrderImpl[] l_ipoOrder,
        WEB3AdminIpoLotBuyListCsv l_csv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "creatIpoLotBuyListInfo(WEB3IpoOrderImpl[], WEB3AdminIpoLotBuyListCsv)";
        log.entering(STR_METHOD_NAME);

        if (l_ipoOrder == null || l_csv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // 引数；IPO申告[]各要素毎のLOOP処理
        int l_intIpoOrder = l_ipoOrder.length;
        for (int i = 0; i < l_intIpoOrder; i++)
        {
            // add明細行( )
            int l_intAddRow = l_csv.addRow();

            // get部店ID( )
            long l_lngBranchId = l_ipoOrder[i].getBranchId();

            // set部店コード(int, long)
            l_csv.setBranchCode(i, l_lngBranchId);
            try
            {
                // get口座ID
                long l_lngAccountId = l_ipoOrder[i].getAccountId();

                // set信用区分
                // [set信用区分()に指定する引数]
                // 行番号：　@（add明細行()の戻り値）
                // 口座ＩＤ：　@IPO申告[index].get口座ＩＤ()
                l_csv.setMarginDiv(l_intAddRow, l_lngAccountId);

                // set扱者コード
                // [set扱者コード()に指定する引数]
                // 行番号：　@（add明細行()の戻り値)
                // 口座ＩＤ：　@IPO申告[index].get口座ＩＤ()
                l_csv.setTradeCode(l_intAddRow, l_lngAccountId);

                // set顧客
                // [set顧客()に指定する引数]
                // 行番号：　@IPO申告の要素番号（index）
                // 口座ＩＤ：　@IPO申告[index].get口座ＩＤ()
                l_csv.setAccount(i, l_lngAccountId);
            }
            // 顧客オブジェクト取得に失敗した場合
            // 顧客オブジェクト取得に失敗した場合(set顧客にて例外が発生)
            // 、該當顧客の明細行は作成しない。
            catch (WEB3BaseException l_ex)
            {
                // delete明細行(行番号 : int) 
                l_csv.deleteRow(l_intAddRow);
                continue;
            }

            // set公開価格        
            IpoProductRow l_ipoProductRow =
                (IpoProductRow)l_ipoOrder[i].getProduct().getDataSourceObject();
            IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow);
            if (!l_ipoProductParams.getPublicPriceIsNull())
            {
                double l_dbPublicPrice = l_ipoProductRow.getPublicPrice();
                Double l_dbPublicPrice1 = new Double(l_dbPublicPrice);
                l_csv.setPublicOfferingPrice(l_intAddRow, l_dbPublicPrice1);
            }           

            // getDataSourceObject( )
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder[i].getDataSourceObject();

            // set抽選結果
            // [set抽選結果()に指定する引数]
            // 行番号：　@IPO申告の要素番号（index）
            // 抽選結果：　@IPO申告[index].IPO申告行.抽選結果
            // 抽選結果（繰上）：　@IPO申告[index].IPO申告行.抽選結果（繰上）
            l_csv.setLotResult(
                i,
                l_ipoOrderRow.getLotResult(),
                l_ipoOrderRow.getLotResultRetry());

            // set当選数量
            l_csv.setElectedQuantity(i, l_ipoOrderRow.getElectedQuantity());

            // set購入申込数量
            if (l_ipoOrderRow.getApplicationQuantityIsNull())
            {
                l_csv.setApplicationQuantity(i, 0.0D/0.0D);
            }
            else
            {
                l_csv.setApplicationQuantity(i, l_ipoOrderRow.getApplicationQuantity());
            }

            // set購入申込／辞退日時
            // [set購入申込／辞退日時()に指定する引数]
            // 行番号：　@IPO申告の要素番号（index）
            // 購入申込／辞退日時：　@IPO申告[index].IPO申告行.購入申込／辞退日時
            l_csv.setOfferingTimestamp(i, l_ipoOrderRow.getOfferingTimestamp());

            // set購入申込状態(int, String)
            // [set購入申込状態()に指定する引数]
            // 行番号：　@IPO申告の要素番号（index）
            // 購入申込状態：　@IPO申告[index].IPO申告行.購入申込区分
            l_csv.setOfferStatus(i, l_ipoOrderRow.getOfferingDiv());

            // 受付状態(int, String)
            // [set受付状態()に指定する引数]
            // 行番号：　@IPO申告の要素番号（index）
            // 受付状態：　@IPO申告[index].IPO申告行.受付状態
            l_csv.setAcceptStatus(i, l_ipoOrderRow.getAcceptStatus());

            // set優先順位(int, long)
            // [set優先順位()に指定する引数]
            // 行番号：　@IPO申告の要素番号（index）
            // 優先順位：　@IPO申告[index].IPO申告行.優先順位
            if (!l_ipoOrderRow.getSubstitutePriorityIsNull())
            {
                Long l_lngSubstitutePriority =
                    new Long(l_ipoOrderRow.getSubstitutePriority());
                l_csv.setSubstitutePriority(i, l_lngSubstitutePriority);
            }
            // set抽選番号(int, String)
            // [set抽選番号()に指定する引数]
            // 行番号：　@IPO申告の要素番号（index）
            // 優先順位：　@IPO申告[index].IPO申告行.抽選番号
            l_csv.setLotNumber(i, l_ipoOrderRow.getLotNumber());
        }

        // getCSVファ@イル行( )
        String[] l_strCsvFileLines = l_csv.getCsvFileLines();

        log.exiting(STR_METHOD_NAME);
        return l_strCsvFileLines;
    }

    /**
     * (create購入申込FewCSV情報)<BR>
     * 抽選結果・購入申込状況CSV情報を生成する。<BR>
     * （扱者コード、信用区分、公開価格、抽選番号なし）<BR>
     * <BR>
     * シーケンス図：「IPO（管理者・抽選結果購入申込状況DL）create購入申込FewCSV情報」参照<BR>
     * <BR>
     * @@param l_ipoOrder - (IPO申告[])<BR>
     * IPO申告行[]
     * @@param l_csv - (抽選結果・購入申込FewCSV情報)<BR>
     * 抽選結果・購入申込FewCSV情報オブジェクト
     * @@return String[]
     * @@throws WEB3BaseException
     */
    protected String[] creatIpoLotBuyListFewInfo(
        WEB3IpoOrderImpl[] l_ipoOrder,
        WEB3AdminIpoLotBuyListFewCsv l_csv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "creatIpoLotBuyListFewInfo(WEB3IpoOrderImpl[], WEB3AdminIpoLotBuyListFewCsv)";
        log.entering(STR_METHOD_NAME);

        if (l_ipoOrder == null || l_csv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // 引数；IPO申告[]各要素毎のLOOP処理
        int l_intIpoOrder = l_ipoOrder.length;
        for (int i = 0; i < l_intIpoOrder; i++)
        {
            // add明細行( )
            int l_intAddRow = l_csv.addRow();

            // get部店ID( )
            long l_lngBranchId = l_ipoOrder[i].getBranchId();

            // set部店コード(int, long)
            l_csv.setBranchCode(i, l_lngBranchId);
            try
            {
                // get口座ID
                long l_lngAccountId = l_ipoOrder[i].getAccountId();

                // set顧客
                // [set顧客()に指定する引数]
                // 行番号：　@IPO申告の要素番号（index）
                // 口座ＩＤ：　@IPO申告[index].get口座ＩＤ()
                l_csv.setAccount(i, l_lngAccountId);
            }
            // 顧客オブジェクト取得に失敗した場合
            // 顧客オブジェクト取得に失敗した場合(set顧客にて例外が発生)
            // 、該當顧客の明細行は作成しない。
            catch (WEB3BaseException l_ex)
            {
                // delete明細行(行番号 : int)
                l_csv.deleteRow(l_intAddRow);
                continue;
            }

            // getDataSourceObject( )
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder[i].getDataSourceObject();

            // set抽選結果
            // [set抽選結果()に指定する引数]
            // 行番号：　@IPO申告の要素番号（index）
            // 抽選結果：　@IPO申告[index].IPO申告行.抽選結果
            // 抽選結果（繰上）：　@IPO申告[index].IPO申告行.抽選結果（繰上）  
            l_csv.setLotResult(
                i,
                l_ipoOrderRow.getLotResult(),
                l_ipoOrderRow.getLotResultRetry());

            // set当選数量
            l_csv.setElectedQuantity(i, l_ipoOrderRow.getElectedQuantity());

            // set購入申込数量
            if (l_ipoOrderRow.getApplicationQuantityIsNull())
            {
                l_csv.setApplicationQuantity(i, 0.0D/0.0D);
            }
            else
            {
                l_csv.setApplicationQuantity(i, l_ipoOrderRow.getApplicationQuantity());
            }

            // set購入申込／辞退日時
            // [set購入申込／辞退日時()に指定する引数]
            // 行番号：　@IPO申告の要素番号（index)
            // 購入申込／辞退日時：　@IPO申告[index].IPO申告行.購入申込／辞退日時
            Date l_datOfferingTimestamp = l_ipoOrderRow.getOfferingTimestamp();
            if (l_datOfferingTimestamp != null)
            {
                l_csv.setOfferingTimestamp(i, l_datOfferingTimestamp);
            }

            // set購入申込状態(int, String)
            // [set購入申込状態()に指定する引数]
            // 行番号：　@IPO申告の要素番号（index）
            // 購入申込状態：　@IPO申告[index].IPO申告行.購入申込区分
            l_csv.setOfferStatus(i, l_ipoOrderRow.getOfferingDiv());

            // 受付状態(int, String)
            // [set受付状態()に指定する引数]
            // 行番号：　@IPO申告の要素番号（index）
            // 受付状態：　@IPO申告[index].IPO申告行.受付状態
            l_csv.setAcceptStatus(i, l_ipoOrderRow.getAcceptStatus());

            // set優先順位(int, long)
            // [set優先順位()に指定する引数]
            // 行番号：　@IPO申告の要素番号（index）
            // 優先順位：　@IPO申告[index].IPO申告行.優先順位
            if (!l_ipoOrderRow.getSubstitutePriorityIsNull())
            {
                l_csv.setSubstitutePriority(i, l_ipoOrderRow.getSubstitutePriority());
            }
        }
        
        // getCSVファ@イル行( )
        String[] l_strCsvFileLines = l_csv.getCsvFileLines();

        // return
        log.exiting(STR_METHOD_NAME);
        return l_strCsvFileLines;
    }
}
@
