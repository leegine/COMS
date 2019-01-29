head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ実装クラス(WEB3AdminIpoLotResultUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李海波 (中訊) 新規作成
Revesion History : 2004/12/27 坂上(SRA) 残対応>>>038
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>043
Revesion History : 2005/01/05 坂上(SRA) 残対応>>>051,052
Revesion History : 2005/01/06 坂上(SRA) 残対応>>>058
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>066
Revesion History : 2007/04/19 何文敏 (中訊) 仕様変更NO.171,NO.172
Revesion History : 2007/07/19 趙林鵬 (中訊) 実装の問題013,014
Revesion History : 2007/07/25 趙林鵬 (中訊) 実装の問題016
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Arrays;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.ipo.WEB3AdminIpoLotResultUploadCsv;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoOrderValidator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputResponse;
import webbroker3.ipo.message.WEB3IPOUploadHistoryUnit;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadUnitService;
import webbroker3.ipo.service.delegate.WEB3IpoUploadActionUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ実装クラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultUploadServiceImpl implements WEB3AdminIpoLotResultUploadService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotResultUploadUnitServiceImpl.class);
    
    /**
     * @@roseuid 4112F1900137
     */
    public WEB3AdminIpoLotResultUploadServiceImpl() 
    {
     
    }
    
    /**
     * 管理者IPO抽選結果アップロード処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄの場合<BR>
     * 　@－getアップロード画面()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄの場合<BR>
     * 　@－validateアップロードファ@イル()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄの場合<BR>
     * 　@－submitアップロードファ@イル()をコールする。<BR>
     * <BR>
     * ※サービスメソッドにて例外が発生した場合は、<BR>
     * 　@例外オブジェクトの追加文字列（WEB3BaseException.errorMessage）を<BR>
     * レスポンスデータ.errorMessageにセットする。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40E145090150
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        if(l_request instanceof WEB3AdminIPOLotResultUploadInputRequest)
        {
			WEB3AdminIPOLotResultUploadInputResponse l_response = new WEB3AdminIPOLotResultUploadInputResponse();
            try
            {
                
                l_response = this.getUploadScreen((WEB3AdminIPOLotResultUploadInputRequest)l_request);
               
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                l_response.errorInfo = l_ex.getErrorInfo();
				l_response.errorMessage = l_ex.getErrorMessage();
                
            }
			log.exiting(STR_METHOD_NAME);
					return l_response; 
            
        }
        else if(l_request instanceof WEB3AdminIPOLotResultUploadConfirmRequest)
        {
			WEB3AdminIPOLotResultUploadConfirmResponse l_response = new WEB3AdminIPOLotResultUploadConfirmResponse();
            try
            {
            
                l_response = this.validateUploadFile((WEB3AdminIPOLotResultUploadConfirmRequest)l_request);
				          
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            
            }            
			log.exiting(STR_METHOD_NAME);
					return l_response; 
        }
        else if(l_request instanceof WEB3AdminIPOLotResultUploadCompleteRequest)
        {
			WEB3AdminIPOLotResultUploadCompleteResponse l_response = new WEB3AdminIPOLotResultUploadCompleteResponse();
            try
            {   
                
                l_response = this.submitUploadFile((WEB3AdminIPOLotResultUploadCompleteRequest)l_request);
				                
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            
            } 
			log.exiting(STR_METHOD_NAME);
					return l_response;            
            
        }
        else if(l_request instanceof WEB3AdminIPOLotResultUploadCancelRequest)
        {
			WEB3AdminIPOLotResultUploadCancelResponse l_response = new WEB3AdminIPOLotResultUploadCancelResponse();
            try
            {   
                
                l_response = this.undoUpload((WEB3AdminIPOLotResultUploadCancelRequest)l_request);
				
            }
            catch(WEB3BaseException l_ex)
            {
                
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            
            }            
			log.exiting(STR_METHOD_NAME);
					return l_response; 
        }
        else
        {
            
            log.error("パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
           
        } 
    }
   
    /**
     * (getアップロード画面)<BR>
     * IPO管理者IPO抽選結果アップロード画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果ＵＬ）getアップロード画面」参照。
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(IPO（管理者・抽選結果ＵＬ）getアップロード画面): <BR>
     *         8(*1) (is削除銘柄() == true) Or (is中止() == true) Or (isブックビルディング終了() ==<BR> 
     * false)の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00527<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(IPO（管理者・抽選結果ＵＬ）getアップロード画面): <BR>
     *         10(*2) （isスケジュール決定() == false）の場合、例外をスローする<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00528<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(IPO（管理者・抽選結果ＵＬ）getアップロード画面): <BR>
     *         12(*3) （is公開価格決定() == false）の場合、例外をスローする<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00529<BR>
     * ==========================================================<BR>
     * @@roseuid 40E145090122<BR>
     * @@param l_request - 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputResponse
     */
    protected WEB3AdminIPOLotResultUploadInputResponse getUploadScreen(WEB3AdminIPOLotResultUploadInputRequest l_request)
        throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " getUploadScreen(WEB3AdminIpoLotResultUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        //1.1getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4.IPO銘柄(long)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        WEB3IpoProductImpl l_ipoProduct = null; 
              
        try
        {
            
            l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);
            
        }
        catch(NotFoundException l_ex)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);

        }
        
        
        //1.5.抽選結果アップロードCSV(IPO銘柄)
        WEB3AdminIpoLotResultUploadCsv l_ipoLotResultUploadCsv = 
            new WEB3AdminIpoLotResultUploadCsv(l_ipoProduct);
        
        long l_administratorId = l_ipoLotResultUploadCsv.getAdministratorUploadId();
            
        //1.6.validate同時アップロード(long)
        l_ipoLotResultUploadCsv.validateSameTimeUpload(new Long(l_administratorId));
        
        
        //1.7.is削除銘柄()
        boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
        log.debug("is削除銘柄() = " + l_blnDeletionProduct);
        
        //1.8.is中止()
        boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
        log.debug("is中止() = " + l_blnDiscontinuation);
        
        //1.9.isブックビルディング終了()
        boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
        log.debug("isブックビルディング終了() = " + l_blnBookBuildingEnd);
        
        //1.10.(is削除銘柄() == true) Or (is中止() == true) Or (isブックビルディング終了() == false)
        //1.10.(is削除銘柄() == true)の場合、例外をスローする。
        if(l_blnDeletionProduct)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                this.getClass().getName() + STR_METHOD_NAME);            
            
        }
        //1.10.(is中止() == true)の場合、例外をスローする。
        else if(l_blnDiscontinuation)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //1.10.(isブックビルディング終了() == false)の場合、例外をスローする。
        else if(!l_blnBookBuildingEnd)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }       
        
        //1.11.isスケジュール決定()
        boolean l_blnScheduleDecision = l_ipoProduct.isScheduleDecision();
        
        //1.12.（isスケジュール決定() == false）の場合、例外をスローする
        if(!l_blnScheduleDecision)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00528,
                this.getClass().getName() + STR_METHOD_NAME); 
            
        }
        
        //1.13.is公開価格決定()
        boolean l_blnPublicPriceDecision = l_ipoProduct.isPublicPriceSettle();
        
        //1.14.（is公開価格決定() == false）の場合、例外をスローする
        if(!l_blnPublicPriceDecision)
        {
            
            log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00529,
                this.getClass().getName() + STR_METHOD_NAME); 
            
        }
      
        //1.15.getアップロード履歴()
        AdministratorUploadRow[] l_administratorRow = l_ipoLotResultUploadCsv.getUploadActions();
        
        //1.16.
        ArrayList l_list = new ArrayList();
        
        //1.17.
        int l_length = l_administratorRow.length;
        log.debug("l_length = " + l_length);
        for(int i =0; i < l_length; i++)
        {
            
            WEB3IpoUploadActionUnitService l_ipoService = 
                (WEB3IpoUploadActionUnitService)Services.getService(WEB3IpoUploadActionUnitService.class);
            //1.17.1.createアップロード履歴明細(（管理者共通）アップロードParams)
            WEB3IPOUploadHistoryUnit l_ipoUploadHistoryUnit = 
                l_ipoService.createUploadActionUnit(new AdministratorUploadParams(l_administratorRow[i]));
            //1.17.2.add(arg0 : Object)
            l_list.add(l_ipoUploadHistoryUnit);

        }
        
        //1.18.toArray()
        WEB3IPOUploadHistoryUnit[] l_ipoUploadHistoryUnit = new WEB3IPOUploadHistoryUnit[l_length];
        l_list.toArray(l_ipoUploadHistoryUnit);
        
        //1.19.管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ(WEB3GenRequest)
        WEB3AdminIPOLotResultUploadInputResponse l_response = 
            (WEB3AdminIPOLotResultUploadInputResponse)l_request.createResponse();
        
        //1.20.プロパティセット
        IpoProductRow l_ipoProductRow =
            (IpoProductRow)l_ipoProduct.getDataSourceObject();
        
        l_response.productCode   = l_ipoProductRow.getProductCode();
        l_response.productName  = l_ipoProductRow.getStandardName();
        l_response.uploadHistoryList = l_ipoUploadHistoryUnit;
        
        log.debug("l_response.productCode = " + l_response.productCode);
        log.debug("l_response.productName = " + l_response.productName);
        log.debug("l_response.uploadHistoryList = " + l_response.uploadHistoryList);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    
    /**
     * (validateアップロードファ@イル)<BR>
     * IPO管理者・抽選結果アップロード確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果ＵＬ）validateアップロードファ@イル」参照。<BR>
     * <BR>
     * ========================================================<BR>                        
     * シーケンス図(「IPO（管理者・抽選結果ＵＬ）validateアップロードファ@イル」): <BR>                       
     *   10(*1) (is削除銘柄() == true) の場合、例外をスローする。<BR>                      
     *   class: WEB3BusinessLayerException<BR>                       
     *   tag:   BUSINESS_ERROR_00588<BR>                     
     *   10(*1) (is中止() == true) の場合、例外をスローする。<BR>                        
     *   class: WEB3BusinessLayerException<BR>                       
     *   tag:   BUSINESS_ERROR_00589<BR>                     
     *   10(*1) (isブックビルディング終了() == false)の場合、例外をスローする。<BR>                       
     *   class: WEB3BusinessLayerException<BR>                       
     *   tag:   BUSINESS_ERROR_00527<BR>                     
     * ==========================================================<BR>  
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「IPO（管理者・抽選結果ＵＬ）validateアップロードファ@イル」): <BR>
     *         12(*2) （isスケジュール決定() == false）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00528<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「IPO（管理者・抽選結果ＵＬ）validateアップロードファ@イル」): <BR>
     *         14(*3) （is公開価格決定() == false）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00529<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「IPO（管理者・抽選結果ＵＬ）validateアップロードファ@イル」): <BR>
     *         21.2.(*5.1) （is購入申込終了（当社設定）() == false）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00529<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「IPO（管理者・抽選結果ＵＬ）validateアップロードファ@イル」): <BR>
     *        21.4.(*5.2) （is新規抽選終了() == true）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00530<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>                            
     * シーンス図(「IPO（管理者・抽選結果ＵＬ）validateアップロードファ@イル」): <BR>                           
     *        22.3.(*6.1) （is購入申込開始（当社設定）() == false）の場合、例外をスローする。<BR>                           
     *         class: WEB3BusinessLayerException<BR>                           
     *         tag:   BUSINESS_ERROR_00526<BR>                         
     *        22.3.(*6.1) （is購入申込終了（目論見書記載）() == true）の場合、例外をスローする。<BR>                         
     *         class: WEB3BusinessLayerException<BR>                           
     *         tag:   BUSINESS_ERROR_01744<BR>                         
     * ==========================================================<BR>  
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「IPO（管理者・抽選結果ＵＬ）validateアップロードファ@イル」): <BR>
     *         22.5(*6.2) （is新規抽選終了() == false）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00597<BR>
     * ==========================================================<BR>
     * @@param l_request - 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadConfirmResponse
     * @@roseuid 40E145090131
     */
    protected WEB3AdminIPOLotResultUploadConfirmResponse validateUploadFile(WEB3AdminIPOLotResultUploadConfirmRequest l_request)
        throws WEB3BaseException
    {
		
        final String STR_METHOD_NAME = " validateUploadFile(WEB3AdminIpoLotResultUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();       
        
        //1.2.validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.7.IPO銘柄(long)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        WEB3IpoProductImpl l_ipoProduct = null;    
        try
        {    
            
            l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);
            
        }
        catch(NotFoundException l_ex)
        {

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);

        }
        
        //1.4.抽選結果アップロードCSV(IPO銘柄)
        WEB3AdminIpoLotResultUploadCsv l_ipoLotResultUploadCsv = new WEB3AdminIpoLotResultUploadCsv(l_ipoProduct);
        
        //1.5. validate同時アップロード(long)
        long l_lngID = l_ipoLotResultUploadCsv.getAdministratorUploadId();                
        l_ipoLotResultUploadCsv.validateSameTimeUpload(null);  
        
        //1.6.saveアップロード開始(long, String, String, String, String)
        long l_lngProductId = l_ipoProduct.getProductId();
        String l_strLotDiv = l_request.lotDiv;
        String l_administratorCode = l_administartor.getAdministratorCode();
        
        l_ipoLotResultUploadCsv.saveUpLoadStart(l_administratorCode,l_strLotDiv);          

        //1.8.is削除銘柄()
        boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
        log.debug("is削除銘柄() = " + l_blnDeletionProduct);        
        //1.9.is中止()
        boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
        log.debug("is中止() = " + l_blnDiscontinuation);
        //1.10.isブックビルディング終了()
        boolean l_blnBookbuildingEnd = l_ipoProduct.isBookbuildingEnd();
        log.debug("isブックビルディング終了() = " + l_blnBookbuildingEnd);
        //1.11.(*1)(is削除銘柄() == true) Or (is中止() == true) Or (isブックビルディング終了() == false)
        //の場合例外をスローする。
        //1.11.(is削除銘柄() == true)の場合、例外をスローする。
        if(l_blnDeletionProduct)
        {
            
            //1.11.1.saveアップロードエラー(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00588);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //1.11.(is中止() == true)例外をスローする。
        else if(l_blnDiscontinuation)
        {
            
            //1.11.1.saveアップロードエラー(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00589);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //1.11.(isブックビルディング終了() == false)の場合、例外をスローする。
        else if(!l_blnBookbuildingEnd)
        {
            
            //1.11.1.saveアップロードエラー(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00527);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //1.12.isスケジュール決定()
        boolean l_blnScheduleDecision = l_ipoProduct.isScheduleDecision();
        log.debug("isスケジュール決定() = " + l_blnScheduleDecision);

        //1.13.(*12)（isスケジュール決定() == false）の場合、例外をスローする
        if(!l_blnScheduleDecision)
        {
            
            //1.13.1.saveアップロードエラー(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00528);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00528,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //1.14.is公開価格決定()
        boolean l_blnPublicPriceDecision = l_ipoProduct.isPublicPriceSettle();
        
        //1.15.(*3)(is公開価格決定() == false）の場合、例外をスローする
        log.debug("is公開価格決定() = " + l_blnPublicPriceDecision);
        if(!l_blnPublicPriceDecision)
        {
            
            //1.15.1.saveアップロードエラー(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00529);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00529,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }

        //1.16.validateキーヘッダ(String)
        String l_strUploadFile = l_request.uploadFile[0];
        try
        {
            
            l_ipoLotResultUploadCsv.validateKeyHeader(l_strUploadFile, l_request.lotDiv);
            
        }
        //1.17.(*4)validateキーヘッダ()にて例外が発生した場合、アップロードエラーを更新する。
        catch(WEB3BaseException l_ex)
        {
            
            //1.17.1.saveアップロードエラー(ErrorInfo)
            l_ipoLotResultUploadCsv.saveUploadError(l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            throw l_ex;

        }
        
        //1.18.get有効IPO申告(long)
        long l_lngIdx = l_ipoProduct.getProductId();
        WEB3IpoOrderImpl[] l_ipoOrders = l_ipoOrderManagerImpl.getOpenOrderUnits(l_lngIdx, null);
        
        //1.19.ArrayList(java.util.Arrays.asList(IPO申告[])
        if(l_ipoOrders == null)
        {
			//saveアップロードエラー(ErrorInfo)
			l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        ArrayList l_list = new ArrayList(Arrays.asList(l_ipoOrders));
        
        //1.20.is新規抽選()
        boolean l_blnNewLot = l_ipoLotResultUploadCsv.isNewLot();
        
        //1.21.(*5)新規抽選の場合（is新規抽選() == true)
        if(l_blnNewLot)
        {
            
            //1.21.1.is購入申込終了（当社設定）()
            boolean l_blnPurchaseApplicationEndCompanySetting = l_ipoProduct.isOfferEnd();
            
            //1.21.2.(*5.1)is購入申込終了（当社設定）() == true）の場合、例外をスローする。
            if(l_blnPurchaseApplicationEndCompanySetting)
            {

				//1.21.2.1.saveアップロードエラー(ErrorInfo)
				l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00525);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00525,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }
            
            //1.21.3.is新規抽選終了()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();
            
            //1.21.4.(*5.2)（is新規抽選終了() == true）の場合、例外をスローする
            if(l_blnNewLotteryEnd)
            {
                
				//1.21.4.1.saveアップロードエラー(ErrorInfo)
				l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00530);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00530,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }
            
            //1.21.5.validate明細行（新規抽選）(抽選結果アップロードCSV, ArrayList, String[])
            this.validateDetailsLineNewLot(l_ipoLotResultUploadCsv, l_list, l_request.uploadFile);
            
        }
        //1.22.(*6)繰上抽選の場合（is新規抽選() == false)
        else
        {
            
            //1.22.1.is購入申込開始（当社設定）()
            boolean l_blnPurchaseApplicationStartCompanySetting  = l_ipoProduct.isOfferStart();
            
            //1.22.2.is購入申込終了（目論見書記載）()
            boolean l_blnOfferEndPros = l_ipoProduct.isOfferEndProspec();
            
            //1.22.3.(*6.1)（is購入申込開始（当社設定）() == false Or is購入申込終了（目論見書記載）() == true）
            //の場合、例外をスローする。
            if(!l_blnPurchaseApplicationStartCompanySetting)
            {
                
				//1.22.3.1.saveアップロードエラー(ErrorInfo)
				l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00526);        
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00526,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }
            //(is購入申込終了（目論見書記載）() == true）の場合、例外をスローする
            else if(l_blnOfferEndPros)
            {
                
                //1.22.3.1.saveアップロードエラー
                l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_01744);              
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01744,
                    this.getClass().getName() + STR_METHOD_NAME); 
                
            }
            
            //1.22.4.is新規抽選終了()
            boolean l_blnNewLotteryEnd = l_ipoProduct.isNewLotteryEnd();
            
            //1.22.5.(*6.2)(is新規抽選終了() == false）の場合、例外をスローする
            log.debug("is新規抽選終了() = " + l_blnNewLotteryEnd);
            
            if(!l_blnNewLotteryEnd)
            {

				//1.22.5.1.saveアップロードエラー(ErrorInfo)
				l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00597);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                    this.getClass().getName() + STR_METHOD_NAME);
                
            }
            
            //1.22.6.validate明細行(繰上抽選)
            this.validateDetailsLineAdvanceLot(l_ipoLotResultUploadCsv, l_list, l_request.uploadFile);
            
        }
        
        //1.23.get明細行数()
        int l_intCount = l_ipoLotResultUploadCsv.getRowCount();
        
        //1.24.getアップロードＩＤ()
        long l_lngUploadId = l_ipoLotResultUploadCsv.getAdministratorUploadId();
        
        //1.25.saveアップロードTemp( )
        l_ipoLotResultUploadCsv.saveUploadTemp();
        
        //1.26.管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ確認ﾚｽﾎﾟﾝｽ(WEB3GenRequest)
        WEB3AdminIPOLotResultUploadConfirmResponse l_response = 
            (WEB3AdminIPOLotResultUploadConfirmResponse)l_request.createResponse();
         
        //1.27.(*6)プロパティセット   
        l_response.uploadNumber = String.valueOf(l_intCount);
        l_response.uploadID = String.valueOf(l_lngUploadId);
        log.debug("l_response.uploadNumber = " + l_response.uploadNumber);
        log.debug("l_response.uploadID = " + l_response.uploadID);    
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submitアップロードファ@イル)<BR>
     * IPO管理者・抽選結果アップロード完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果ＵＬ）submitアップロードファ@イル」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「IPO（管理者・抽選結果ＵＬ）submitアップロードファ@イル」): <BR>
     *        16.2.(*4.1)（is購入申込終了（当社設定）() == true）の場合、例外をスローする。<BR>
     *        16.4.(*4.2) 新規抽選で以下の条件にあてはまる場合は例外をスローする。<BR>
     *        アップロード期間のチェック（新規抽選）<BR>
     *       (*4) 新規抽選で以下の条件にあてはまる場合は例外をスローする。<BR>  
     * <BR>
     * 　@        －購入申込期間が終了している（is購入申込終了（当社設定）() == true）<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_00525<BR>
     * 　@        －新規抽選が終了している（is新規抽選() == true）<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_00530<BR> 
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「IPO（管理者・抽選結果ＵＬ）submitアップロードファ@イル」): <BR>
     *        17.3.(*5.1) （is購入申込開始（当社設定）() == false Or <BR>
     *                      is購入申込終了（目論見書記載）() == true）の場合、例外をスローする。<BR>
     *        17.5.(*5.2) （is新規抽選() == false）の場合、例外をスローする。<BR>
     *        アップロード期間のチェック（繰上抽選）<BR>
     *       (*5) 繰上抽選で以下の条件にあてはまる場合は例外をスローする。<BR>
     * <BR>
     * 　@        －購入申込期間が開始していない（is購入申込開始（当社設定）() == false）<BR>                          
     *              class: WEB3BusinessLayerException<BR>                           
     *              tag:   BUSINESS_ERROR_00526<BR>                         
　@   *           －購入申込終了（目論見書記載）が終了している（is購入申込終了（目論見書記載）() == true）<BR>                            
     *              class: WEB3BusinessLayerException<BR>                          
     *              tag:   BUSINESS_ERROR_01744<BR>
     * 　@        －新規抽選が終了していない（is新規抽選() == false）<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_00597<BR>  
     * ==========================================================<BR>
     * @@param l_request - 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadCompleteResponse
     * @@roseuid 40E146B300A5
     */
    protected WEB3AdminIPOLotResultUploadCompleteResponse submitUploadFile(WEB3AdminIPOLotResultUploadCompleteRequest l_request)
        throws WEB3BaseException 

    {
        final String STR_METHOD_NAME = " submitUploadFile(WEB3AdminIpoLotResultUploadCompleteRequest)";
            
        log.entering(STR_METHOD_NAME);    
        
        //1.1.getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();       
        
        //1.2.validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.validate取引パスワード(String)
        l_administartor.validateTradingPassword(l_request.password);
        
        //1.4.validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.7.IPO銘柄(Row)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();            
        WEB3IpoProductImpl l_ipoProduct = null;    
        try
        {    
            
            l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);
            
        }
        catch(NotFoundException l_ex)
        {

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
   
        } 
               
        //1.5.抽選結果アップロードCSV(IPO銘柄)
        WEB3AdminIpoLotResultUploadCsv l_ipoLotResultUploadCsv = new WEB3AdminIpoLotResultUploadCsv(l_ipoProduct);        
        
        //1.6.validate同時アップロード(long)
        String l_strUploadID = l_request.uploadID;
        long l_lngUploadId = 0;
        if(l_strUploadID != null)
        {
            
            l_lngUploadId = Long.parseLong(l_strUploadID);
            
        }
        else
        {
            
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
            
        }
        l_ipoLotResultUploadCsv.validateSameTimeUpload(new Long(l_lngUploadId));
        
        //1.18.setDataFromアップロードTemp(long) 
        //saveUploadするためにthis.administratorUploadIdにアップロードIDをセットする必要があるためここに移動。       
        l_ipoLotResultUploadCsv.setDataFromUploadTemp(l_lngUploadId);
               
        //1.8.is削除銘柄()
        boolean l_blnDeletionProduct = l_ipoProduct.isDeletedProduct();
        log.debug("is削除銘柄() = " + l_blnDeletionProduct);
        
        //1.9.is中止()
        boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
        log.debug("is中止() = " + l_blnDiscontinuation);

        //1.10.isブックビルディング終了()
        boolean l_blnBookBuildingEnd = l_ipoProduct.isBookbuildingEnd();
        log.debug("isブックビルディング終了() = " + l_blnBookBuildingEnd);
        
        //1.11.(is削除銘柄() == true) Or (is中止() == true) Or (isブックビルディング終了() == false)の場合、例外をスローする。
        if(l_blnDeletionProduct)
        {
            
            //1.11.1.saveアップロードエラー
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00588);
            //deleteアップロードTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                this.getClass().getName() + STR_METHOD_NAME);                        
        
        }
        else if(l_blnDiscontinuation)
        {
            
            //1.11.1.saveアップロードエラー
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00589);
            //deleteアップロードTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + STR_METHOD_NAME);             
        
        }
        else if(!(l_blnBookBuildingEnd))
        {
            
            //1.11.1.saveアップロードエラー
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00527);
            //deleteアップロードTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00527,
                this.getClass().getName() + STR_METHOD_NAME);             
        
        }
        
        //1.12.isスケジュール決定()
        boolean l_blnScheduleDecision = l_ipoProduct.isScheduleDecision();
        log.debug("isスケジュール決定() = " + l_blnScheduleDecision);

        //1.13.（isスケジュール決定() == false）の場合、例外をスローする
        if(!l_blnScheduleDecision)
        {
            
            //1.13.1.saveアップロードエラー
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00528);
            //deleteアップロードTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00528,
                this.getClass().getName() + STR_METHOD_NAME); 
            
        }
        
        //1.14.is公開価格決定()
        boolean l_blnPublicPriceDecision = l_ipoProduct.isPublicPriceSettle();
        log.debug("is公開価格決定() = " + l_blnPublicPriceDecision); 
        
        //1.15.「is公開価格決定()==false」の場合例外をスローする        
        if(!l_blnPublicPriceDecision)
        {
            
            //1.15.1.saveアップロードエラー
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00529);            
            //deleteアップロードTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00529,
                this.getClass().getName() + STR_METHOD_NAME); 
            
        }
        
         //1.19.is新規抽選()
         boolean l_blnNewLot = l_ipoLotResultUploadCsv.isNewLot();
         
         //1.16.(*4)新規抽選の場合(is新規抽選==true)
         if(l_blnNewLot)
         {
            
             ////1.16.1.is購入申込終了(当社設定)
             boolean l_blnSetting = l_ipoProduct.isOfferEnd();
             
             ////1.16.2.「is購入申込終了(当社設定)==true」の場合例外をスローする
             if(l_blnSetting)
             {
                 
                 //1.16.2.1.saveアップロードエラー
                 l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00525);                 
                 //deleteアップロードTemp()
                 l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00525,
                     this.getClass().getName() + STR_METHOD_NAME);
                
             }
             
             ////1.16.3.is新規抽選終了()
             boolean l_blNewLotEnd = l_ipoProduct.isNewLotteryEnd();
             
             ////1.16.4.「is新規抽選終了()==true」の場合例外をスローする
             if(l_blNewLotEnd)
             {
                 
                 //1.16.4.1.saveアップロードエラー
                 l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00530);                  
                 //deleteアップロードTemp()
                 l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                      WEB3ErrorCatalog.BUSINESS_ERROR_00530,
                      this.getClass().getName() + STR_METHOD_NAME);
             
             }    
         }
         //1.17.(*5)繰上げ抽選の場合(is新規抽選==false)
         else
         {

             ////1.17.1.is購入申込開始(当社設定)
             boolean l_blnStartCompanySetting = l_ipoProduct.isOfferStart();
             
             ////1.17.2.is購入申込終了(目論見書記載)
             boolean l_blnOfferEndPros = l_ipoProduct.isOfferEndProspec();
             
             ////1.17.3.「is購入申込開始(当社設定)==true Or is購入申込終了(目論見書記載)==true」の場合例外をスローする
             if(!(l_blnStartCompanySetting))
             {
                 
                 //1.17.3.1.saveアップロードエラー
                 l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00526);              
                 //deleteアップロードTemp()
                 l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00526,
                     this.getClass().getName() + STR_METHOD_NAME);
            
             }
             else if(l_blnOfferEndPros)
             {
                 
                 //1.17.3.1.saveアップロードエラー
                 l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_01744);              
                 //deleteアップロードTemp()
                 l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_01744,
                     this.getClass().getName() + STR_METHOD_NAME); 
                                    
             }
             
             ////1.17.4.is新規抽選終了()
             boolean l_blNewLotEnd = l_ipoProduct.isNewLotteryEnd();
             
             ////1.17.5.「is新規抽選終了()==false」の場合例外をスローする
             if(!(l_blNewLotEnd))  
             {
                 
                 //1.17.5.1.saveアップロードエラー
                 l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00597);                 
                 //deleteアップロードTemp()
                 l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00597,
                     this.getClass().getName() + STR_METHOD_NAME);                
                         
             }
                   
         }

//        //1.18.setDataFromアップロードTemp(long)        
//        l_ipoLotResultUploadCsv.setDataFromUploadTemp(l_lngUploadId);
        
        //1.19.is新規抽選()
        ////1.16の前で判定が必要のため移動。
        
        //1.20.get明細行数()
        int l_intRowCount = l_ipoLotResultUploadCsv.getRowCount();
        
        //1.21.get有効IPO申告For抽選(Product_id,String,boolean)
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();        
        WEB3IpoOrderImpl[] l_ipoOrders = l_orderManagerImpl.getOpenOrderUnits(l_ipoProduct.getProductId(), null,l_blnNewLot);
		if(l_ipoOrders == null)
		{
        
            //saveアップロードエラー
            l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.SYSTEM_ERROR_80005);            
            //deleteアップロードTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp(); 
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
		
        }

        //1.22.ArrayList(java.utilArrays.asList(IPO申告[])
        ArrayList l_lisOrders = new ArrayList(Arrays.asList(l_ipoOrders));
        
        //1.23.(*6)明細行の数分LOOP処理
        for(int i = 0; i < l_intRowCount; i++)
        {
            
            //1.23.1.get部店コード(int)
            String l_strBranchCode = l_ipoLotResultUploadCsv.getBranchCode(i);
            
            //1.23.2.get顧客コード(int)
            String l_strAccountCode = l_ipoLotResultUploadCsv.getAccountCode(i);
            
            //1.23.3.get証券会社()
            Institution l_institution = l_ipoProduct.getInstitution();
            
            //1.23.4.getBranch(Institution, 論理ビュー::java::lang::String)
            Branch l_branch = null;
            MainAccount l_mainAccount = null;
            try
            {
                
                l_branch = l_finApp.getAccountManager().getBranch(l_institution, l_strBranchCode);
                
            }
            catch(NotFoundException l_ex)
            {
                
                //saveアップロードエラー
                l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.SYSTEM_ERROR_80005);                
                //deleteアップロードTemp()
                l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
                
            }
            
            //1.23.5.getMainAccount(long, long, 論理ビュー::java::lang::String)
            try
            {
                
                l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_institution,l_branch,l_strAccountCode);
                
            }
            catch(NotFoundException l_ex)
            {
                
                //saveアップロードエラー
                l_ipoLotResultUploadCsv.saveUploadError(WEB3ErrorCatalog.SYSTEM_ERROR_80005); 
                //deleteアップロードTemp()
                l_ipoLotResultUploadCsv.deleteUploadTemp(); 
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
                
            }
            
            //1.23.6.get抽選結果(int)
            String l_strLotResult = l_ipoLotResultUploadCsv.getLotResult(i);
            
            //1.23.7.get当選数量(int)
            double l_dblPrizeQuantity = l_ipoLotResultUploadCsv.getElectedQuantity(i);
            
            //1.23.8.get優先順位(int)
            Long l_lngSubstitutePriority = l_ipoLotResultUploadCsv.getSubstitutePriority(i);
            
            //1.23.9.update抽選結果(ArrayList, boolean, 管理者, 顧客, String, double, long)
            WEB3AdminIpoLotResultUploadUnitService l_ipoLotResultUploadUnit = 
                (WEB3AdminIpoLotResultUploadUnitService)Services.getService(WEB3AdminIpoLotResultUploadUnitService.class);
            l_ipoLotResultUploadUnit.updateLotResult(
                l_lisOrders,
                l_blnNewLot,
                l_administartor,
                l_mainAccount,
                l_strLotResult,
                l_dblPrizeQuantity,
                l_lngSubstitutePriority);
        }
        
        if(l_blnNewLot)
        {
            
            //1.24.（is新規抽選() == true）の場合、落選者のデータを更新する
            int l_intSize = l_lisOrders.size();
            WEB3IpoOrderImpl[] l_ipoOrderList = new WEB3IpoOrderImpl[l_intSize];
            
            //1.24.1.toArray()
            l_lisOrders.toArray(l_ipoOrderList);
            
            //1.24.2.IPO申告[]（toArray()の戻り値）各要素ごとのLOOP処理
            for(int i = 0; i < l_intSize; i++)
            {
                
                WEB3AdminIpoLotResultUploadUnitService l_ipoLotResultUploadUnit = 
                    (WEB3AdminIpoLotResultUploadUnitService)Services.getService(WEB3AdminIpoLotResultUploadUnitService.class);
                //1.24.2.1.update抽選結果(IPO申告, boolean, 管理者, String, double, long)                     
                l_ipoLotResultUploadUnit.updateLotResult(
                    l_ipoOrderList[i],
                    true,
                    l_administartor,
                    WEB3LotResultDef.DEFEAT,
                    0,
                    new Long(0));
            }
            
        }
        
        //1.25.更新用の行オブジェクトを生成する。
        l_ipoProduct.createForUpdateParams();
        
        //1.26.set抽選終了(boolean)(IPO銘柄::set抽選終了)
        l_ipoProduct.setLotteryEnd(l_blnNewLot);
        
        //1.27.save銘柄(IPO銘柄)
        l_ipoProductManagerImpl.saveProduct(l_ipoProduct);
        
        //アップロード完了更新処理を追加
		l_ipoLotResultUploadCsv.saveUploadEnd(); 
        
        //1.28.deleteアップロードTemp()
        l_ipoLotResultUploadCsv.deleteUploadTemp();   
                  
        //1.29.管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽ(WEB3GenRequest)
        WEB3AdminIPOLotResultUploadCompleteResponse l_response = 
            (WEB3AdminIPOLotResultUploadCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (undoアップロード)<BR>
     * IPO管理者・抽選結果アップロード中止処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果ＵＬ）undoアップロード」参照。
     * @@param l_request - 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIpoLotResultUploadCancelResponse
     * @@roseuid 40F77C280307
     */
    protected WEB3AdminIPOLotResultUploadCancelResponse undoUpload(WEB3AdminIPOLotResultUploadCancelRequest l_request) 
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " undoUpload(WEB3AdminIpoLotResultUploadCancelRequest)";
            
        log.entering(STR_METHOD_NAME);
        
        //(1.1)抽選結果アップロードCSV(long)
        long l_lngUploadId = Long.parseLong(l_request.uploadID);
        WEB3AdminIpoLotResultUploadCsv l_ipoLotResultUploadCsv = new WEB3AdminIpoLotResultUploadCsv(l_lngUploadId);
        //(1.2)deleteアップロードTemp()
            l_ipoLotResultUploadCsv.deleteUploadTemp();
        //(1.3)saveアップロード中止()
        l_ipoLotResultUploadCsv.saveUploadStop();        
        WEB3AdminIPOLotResultUploadCancelResponse l_response = 
            (WEB3AdminIPOLotResultUploadCancelResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;    
        
    }
    
    /**
     * (validate明細行（新規抽選）)<BR>
     * 明細行（新規抽選）のチェックを行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果ＵＬ）validate明細行（新規抽選）」参照。
     * <BR>
     * "部店コード,顧客コード,顧客名,抽選結果,当選数量,優先順位"
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「IPO（管理者・抽選結果ＵＬ）validate明細行（新規抽選）」): <BR>
     *        1.6(*2) （当選数量合計値 > 当社取扱数量）の場合、例外をスローする<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00598<BR>
     * ==========================================================<BR>
     * @@roseuid 40F4E15C018F
     * @@param l_lotResultCsv - 抽選結果アップロードCSVデータモデルオブジェクト
     * 
     * @@param l_assessmentList - IPO申告のArrayList
     * 
     * @@param l_strDetailsLines - 明細行の配列<BR>
     */
    protected void validateDetailsLineNewLot(
        WEB3AdminIpoLotResultUploadCsv l_lotResultCsv, 
        ArrayList l_assessmentList, 
        String[] l_strDetailsLines) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateDetailsLineNewLot(WEB3AdminIPOLotResultUploadCsv, ArrayList, String[])";
            
        log.entering(STR_METHOD_NAME);
        
        //1.1.getIPO銘柄()
        WEB3IpoProductImpl l_product = l_lotResultCsv.getIpoProduct();
        //1.2.getOrderValidator()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        l_orderManagerImpl.getOrderValidator();
                    
        //1.3.明細行[1]～各要素毎のLOOP処理
        int l_intLength = l_strDetailsLines.length;
        int l_intIndex = 0;
        
        for(int i = 1; i < l_intLength; i++)
        {
            String l_strBranchCode =null;
            String l_strAccountCode =null;
            
            try
            {
                
                //1.3.1.add明細行(String)
                l_intIndex = l_lotResultCsv.addRow(l_strDetailsLines[i]);
                
            }
            catch(WEB3SystemLayerException l_ex)
            {
                //1.3.2.add明細行()で例外が発生した場合、アップロードエラーを更新する
                //1.3.2.1.saveアップロードエラー(ErrorInfo)

                l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());
                throw new WEB3SystemLayerException(l_ex.getErrorInfo(), l_ex.getErrorMethod(),
                    "明細行文字列（=" + l_strDetailsLines[i] + "）", l_ex.getException());
                
            }
			//2004/12/7 U00525 例外処理のスコープが不適切　@坂上@@SRA START
            String l_strBranchCodeErrDisp = null;
            String l_strAccountCodeErrDisp = null;
            try
            {
                //1.3.3.(*) 空行の場合（add明細行()の戻り値 < 0）、当該要素の処理を中断（continue;）
                if (l_intIndex < 0)
                {
                    continue;
                }

                l_strBranchCodeErrDisp = l_lotResultCsv.getBranchCode(l_intIndex);
                l_strAccountCodeErrDisp = l_lotResultCsv.getAccountCode(l_intIndex);
                
                //1.3.4.validate項目レングス(行番号:int)
                l_lotResultCsv.validateItemLength(l_intIndex);
                //1.3.5.get当選数量(int)
                double l_dblPrizeQuantity = l_lotResultCsv.getElectedQuantity(l_intIndex);
                //1.3.6.get抽選結果(int)
                String l_strLotResult = l_lotResultCsv.getLotResult(l_intIndex);
                //1.3.7.抽選結果 == 当選または、補欠の場合
                WEB3IpoOrderValidator l_ipoOrderCheck = new WEB3IpoOrderValidator();
                if(WEB3LotResultDef.ELECTION.equals(l_strLotResult)
                   || WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult))
                {
                    
                    //1.3.7.1.validate数量(IPO銘柄, double)
                    l_ipoOrderCheck.validateQuantity(l_product,l_dblPrizeQuantity);//throw WEB3BaseException
                    
                }
                //1.3.8.validate新規抽選抽選結果(String)
                l_lotResultCsv.validateNewLotLotResult(l_strLotResult);
                //1.3.9.get優先順位(int)
                Long l_lngSubstitutePriority = l_lotResultCsv.getSubstitutePriority(l_intIndex);
                //1.3.10.validate優先順位(String, long)
                l_lotResultCsv.validateSubstitutePriority(l_strLotResult, l_lngSubstitutePriority);
//                //1.3.11.validate重複顧客(int)
//                l_lotResultCsv.validateDuplicateAccount(l_intIndex);
                //1.3.12.get部店コード(行番号（=add明細行の戻り値） : int)
                l_strBranchCode = l_lotResultCsv.getBranchCode(l_intIndex);
    //			String l_strBranchCode = l_lotResultCsv.getBranchCode(l_intIndex);
                //1.3.13.get証券会社()
                Institution l_institution = l_product.getInstitution();
                //1.3.14.getBranch(Institution, 論理ビュー::java::lang::String)
                Branch l_branch = null;
    //            try
    //            {
                    
                    l_branch = l_finApp.getAccountManager().getBranch(l_institution,l_strBranchCode);
                    
    //            }
    //            catch(NotFoundException l_ex)
    //            {
    //                
    //                log.exiting(STR_METHOD_NAME);
    //				//2004/12/06 U00520 ﾋﾞｼﾞﾈｽｴﾗｰに修正　@坂上@@SRA START
    //				throw new WEB3BusinessLayerException(
    //				WEB3ErrorCatalog.BUSINESS_ERROR_00017,
    ////				throw new WEB3SystemLayerException(
    ////				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
    //				//2004/12/06 U00520 ﾋﾞｼﾞﾈｽｴﾗｰに修正　@坂上@@SRA END
    //                    STR_METHOD_NAME,
    //                    l_ex.getMessage(),
    //                    l_ex);
    //                
    //            }
                //1.3.15.get顧客コード(int)
                l_strAccountCode = l_lotResultCsv.getAccountCode(l_intIndex);
    //			String l_strAccountCode = l_lotResultCsv.getAccountCode(l_intIndex);
                //1.3.16.getMainAccount(long, 論理ビュー::java::lang::String, 論理ビュー::java::lang::String)(
    //            try
    //            {
                    
    //                l_finApp.getAccountManager().getMainAccount(l_institution,l_branch,l_strAccountCode);

                //1.3.16.get顧客
                WEB3GentradeAccountManager l_genAccountMgr = null;
                MainAccount l_maMainAccount = null;
                try
                {
                    l_genAccountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                    l_maMainAccount = l_genAccountMgr.getMainAccount(
                        l_institution.getInstitutionCode(),
                        l_strBranchCode,
                        l_strAccountCode);
                }
                catch(WEB3SystemLayerException l_ex)
                {
                    if (WEB3ErrorCatalog.SYSTEM_ERROR_80003.equals(l_ex.getErrorInfo())
                        || WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_ex.getErrorInfo())
                        || WEB3ErrorCatalog.SYSTEM_ERROR_80006.equals(l_ex.getErrorInfo()))
                    {
                        log.debug("該当するデータが存在しません。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                            l_ex.getErrorMethod());
                    }
                    else if (WEB3ErrorCatalog.SYSTEM_ERROR_80017.equals(l_ex.getErrorInfo()))
                    {
                        log.debug("入力パラメータチェックエラー。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00830,
                            l_ex.getErrorMethod());
                    }
                    throw l_ex;
                }

                    //1.3.17.set顧客
                    l_lotResultCsv.setAccount(l_intIndex,l_maMainAccount.getAccountId());
                    
                 //1.3.10.validate重複顧客(int)←1.3.16で顧客コードが6桁から7桁にセットしなおされるため、7桁同士の比較ができるよう移動
                              l_lotResultCsv.validateDuplicateAccount(l_intIndex);
    //            }
    //            catch(NotFoundException l_ex)
    //            {
    //                
    //                log.exiting(STR_METHOD_NAME);
    //
    //				//2004/12/06 U00520 ﾋﾞｼﾞﾈｽｴﾗｰに修正　@坂上@@SRA START
    //				throw new WEB3BusinessLayerException(
    //				WEB3ErrorCatalog.BUSINESS_ERROR_00017,
    ////				throw new WEB3SystemLayerException(
    ////				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
    //				//2004/12/06 U00520 ﾋﾞｼﾞﾈｽｴﾗｰに修正　@坂上@@SRA END
    //                    STR_METHOD_NAME,
    //                    l_ex.getMessage(),
    //                    l_ex);
    //                
    //            }
                //1.3.18.(*1.3)(抽選結果==当選)の場合
                if(WEB3LotResultDef.ELECTION.equals(l_strLotResult))
                {
                    //1.3.18.1.add当選数量(double)
                    l_lotResultCsv.addElectedQuantity(l_dblPrizeQuantity);
                }
                
                //1.3.19 抽選結果 == 当選または、補欠の場合
                if (WEB3LotResultDef.ELECTION.equals(l_strLotResult)
                    || WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult))
                {
                    //1.3.19.1 validateIPO申告（新規抽選）(ArrayList, String, String, double)
                    validateIpoOrderNewLot(l_assessmentList,
                        l_lotResultCsv.getAccountCode(l_intIndex),
                        l_lotResultCsv.getBranchCode(l_intIndex),
                        l_dblPrizeQuantity);
                }
    //            try
    //            {
                    
                    
                    
    //            }
    //            catch(WEB3BaseException l_ex)
    //            {
    //                
    //                //(1.3.17)get当選数量()～validateIPO申告()｝の手続きで例外が発生した場合、アップロードエラーを更新する
    //                l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());
    //                
    //                log.exiting(STR_METHOD_NAME);
    //                log.error(STR_METHOD_NAME, l_ex);
    //                throw new WEB3BaseException(l_ex.getErrorInfo(), STR_METHOD_NAME, l_strBranchCode + "." + l_strAccountCode);
    //                
    //            }
			}
			catch(WEB3BaseException l_ex)
			{           
				//1.3.20.1.get当選数量()～validateIPO申告()の手続きで例外が発生した場合、アップロードエラーを更新する
				l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());                
				log.exiting(STR_METHOD_NAME);
				log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),                
                    STR_METHOD_NAME,                
                    l_strBranchCodeErrDisp + "," + l_strAccountCodeErrDisp);                
			}
			catch(NotFoundException nf_ex)
			{
				l_lotResultCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_01386);
				log.exiting(STR_METHOD_NAME);
				log.error(STR_METHOD_NAME, nf_ex);
                throw new WEB3BusinessLayerException(               
                    WEB3ErrorCatalog.BUSINESS_ERROR_01386,              
                    STR_METHOD_NAME,                
                    l_strBranchCodeErrDisp + "," + l_strAccountCodeErrDisp);                
			}
			//2004/12/7 U00525 例外処理のスコープが不適切　@坂上@@SRA END
        }
        
        //1.4.get当選数量合計値( )
        double l_dblPrizeQuantityTotal = l_lotResultCsv.getElectedQuantityTotal();
        
        //1.5.get当社取扱数量()
        double l_dblDealingQuantity = l_product.getDealingQuantity();
        //1.6.（当選数量合計値 > 当社取扱数量）の場合、例外をスローする
        
        if(l_dblPrizeQuantityTotal > l_dblDealingQuantity)
        {
            
            //1.6.1.saveアップロードエラー(ErrorInfo)
            l_lotResultCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00598);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
            //2004/12/7 U00526 ﾋﾞｼﾞﾈｽｴﾗｰが不適切のため修正　@坂上@@SRA  START
                WEB3ErrorCatalog.BUSINESS_ERROR_00598,
//			    WEB3ErrorCatalog.BUSINESS_ERROR_00599,
			//2004/12/7 U00526 ﾋﾞｼﾞﾈｽｴﾗｰが不適切のため修正　@坂上@@SRA  END
                this.getClass().getName() + STR_METHOD_NAME);
            
        }

    }
    
    /**
     * 明細行（繰上抽選）のチェックを行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果ＵＬ）validate明細行（繰上抽選）」参照。
     * "部店コード,顧客コード,顧客名,抽選結果,当選数量,優先順位"
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「IPO（管理者・抽選結果ＵＬ）validate明細行（繰上抽選）」): <BR>
     *        1.9(*3) { 当選数量合計値 > （当社取扱数量 - 割当確定数量） }の場合、例外をスローする<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00599<BR>
     * ==========================================================<BR>
     * @@param l_lotResultCsv - 抽選結果アップロードCsvデータモデルオブジェクト<BR>
     * <BR>
     * @@param l_assessmentList - IPO申告のArrayList<BR>
     * <BR>
     * @@param l_strDetailsLines - 明細行の配列<BR>
     * @@roseuid 40F4E45D0141
     */
    protected void validateDetailsLineAdvanceLot(
        WEB3AdminIpoLotResultUploadCsv l_lotResultCsv, 
        ArrayList l_assessmentList, 
        String[] l_strDetailsLines) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateDetailsLineAdvanceLot(WEB3AdminIPOLotResultUploadCsv, ArrayList, String[])";
            
        log.entering(STR_METHOD_NAME);
        
        //1.1.toArray()
        int l_intLength = l_assessmentList.size();
        WEB3IpoOrderImpl[] l_ipoOrders = new WEB3IpoOrderImpl[l_intLength];
        l_assessmentList.toArray(l_ipoOrders);
        //(1.3) getIPO銘柄()
        WEB3IpoProductImpl l_product = l_lotResultCsv.getIpoProduct();
        //1.2.IPO申告[]（IPO申告List.toArray()）各要素毎のLOOP処理
        
        for(int i = 0; i < l_intLength; i++)
        {
            //1.2.1.add割当確定数量(IPO申告, IPO銘柄)
            l_lotResultCsv.addAllotQuantity(l_ipoOrders[i], l_product);
            //1.2.2.is補欠者()
            boolean l_blnWaiting = l_ipoOrders[i].isWaiting();
            //1.2.3.補欠者でない場合（is補欠者() == false）、対象要素をIPO申告Listより削除する
            if(!l_blnWaiting)
            {
                
                //1.2.3.1.indexOf((arg0（=IPO申告[index]) : Object)
                int l_intIndex = l_assessmentList.indexOf(l_ipoOrders[i]);
                //1.2.3.2.remove(arg0 : int)
                l_assessmentList.remove(l_intIndex);
                
            }
 
        }
        //1.4.getOrderValidator( )            
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3IpoOrderManagerImpl l_orderManagerImpl = 
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        
        l_orderManagerImpl.getOrderValidator();
                
        //1.5.明細行[1]～の各要素毎のLOOP処理
        int l_intLgn = l_strDetailsLines.length;
        int l_intRowIndex = 0;
        for(int i = 1; i < l_intLgn; i++)
        {
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            
            //1.5.1.add明細行(明細行文字列（=明細行[index]） : String)
            try
            {
                
                l_intRowIndex = l_lotResultCsv.addRow(l_strDetailsLines[i]);
                
            }
            catch(WEB3SystemLayerException l_ex)
            {
                
                //1.5.2.add明細行()で例外が発生した場合、アップロードエラーを更新する
                //1.5.2.1.saveアップロードエラー(ErrorInfo)
                l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());
                    
                throw new WEB3SystemLayerException(l_ex.getErrorInfo(), l_ex.getErrorMethod(),
                    "明細行文字列（=" + l_strDetailsLines[i] + "）", l_ex.getException());

            }
            
            //2004/12/7 U00525 例外処理のスコープが不適切　@坂上@@SRA START
            String l_strBranchCodeErrDisp = null;
            String l_strAccountCodeErrDisp = null;
            try
            {
                //1.5.3.(*) 空行の場合（add明細行()の戻り値 < 0）、当該要素の処理を中断（continue;）
                if (l_intRowIndex < 0)
                {
                    continue;
                }
                
                l_strBranchCodeErrDisp = l_lotResultCsv.getBranchCode(l_intRowIndex);
                l_strAccountCodeErrDisp = l_lotResultCsv.getAccountCode(l_intRowIndex);

                //1.5.4.validate項目レングス(行番号:int)
                l_lotResultCsv.validateItemLength(l_intRowIndex);            	
                //1.5.5.get当選数量(int)
                double l_dblPrizeQuantity = l_lotResultCsv.getElectedQuantity(l_intRowIndex);
                //1.5.6.get抽選結果(int)
                String l_strLotResult = l_lotResultCsv.getLotResult(l_intRowIndex);
                //1.5.7.抽選結果 == 当選の場合
                if(WEB3LotResultDef.ELECTION.equals(l_strLotResult))
                {
                    
                    //1.5.7.1.validate数量(IPO銘柄, double)
                    WEB3IpoOrderValidator l_ipoOrderCheck = new WEB3IpoOrderValidator();
                    l_ipoOrderCheck.validateQuantity(l_product, l_dblPrizeQuantity);
                    
                }
                //1.5.8.validate繰上抽選抽選結果(String, double)
                l_lotResultCsv.validateAdvanceLotLotResult(l_strLotResult, l_dblPrizeQuantity);
                //1.5.9.get優先順位(int)
                Long l_lngSubstitutePriority = l_lotResultCsv.getSubstitutePriority(l_intRowIndex);
                //1.5.10.validate優先順位(String, long)
                l_lotResultCsv.validateSubstitutePriority(l_strLotResult, l_lngSubstitutePriority);
//                //1.5.11.validate重複顧客(int)
//                l_lotResultCsv.validateDuplicateAccount(l_intRowIndex);
                //1.5.12.get部店コード(int)
                l_strBranchCode = l_lotResultCsv.getBranchCode(l_intRowIndex);
    //			String l_strBranchCode = l_lotResultCsv.getBranchCode(l_intRowIndex);
                //1.5.13.get証券会社()
                Institution l_institution = l_product.getInstitution();
                //1.5.14.getBranch(long)
                Branch l_branch = null;
                
    //            try
    //            {
                    
                    l_branch = l_finApp.getAccountManager().getBranch(l_institution, l_strBranchCode);
                    
    //            }
    //            catch(NotFoundException l_ex)
    //            {
    //                
    //                log.exiting(STR_METHOD_NAME);
    //				//2004/12/06 U00520 ﾋﾞｼﾞﾈｽｴﾗｰに修正　@坂上@@SRA START
    //				throw new WEB3BusinessLayerException(
    //				WEB3ErrorCatalog.BUSINESS_ERROR_00017,
    ////				throw new WEB3SystemLayerException(
    ////				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
    //				//2004/12/06 U00520 ﾋﾞｼﾞﾈｽｴﾗｰに修正　@坂上@@SRA END
    //                    STR_METHOD_NAME,
    //                    l_ex.getMessage(),
    //                    l_ex);
    //                
    //            }
                //1.5.15.get顧客コード(int)
                l_strAccountCode = l_lotResultCsv.getAccountCode(l_intRowIndex);
    //			String l_strAccountCode = l_lotResultCsv.getAccountCode(l_intRowIndex);
    //            try
    //            {
                    
    //                //(1.5.15)getMainAccount(long)
    //                l_finApp.getAccountManager().getMainAccount(l_institution, l_branch, l_strAccountCode);

                //1.5.16.get顧客
                WEB3GentradeAccountManager l_genAccountMgr = null;
                MainAccount l_maMainAccount = null;
                try
                {
                    l_genAccountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                    l_maMainAccount = l_genAccountMgr.getMainAccount(
                        l_institution.getInstitutionCode(),
                        l_strBranchCode,
                        l_strAccountCode);
                }
                catch(WEB3SystemLayerException l_ex)
                {
                    if (WEB3ErrorCatalog.SYSTEM_ERROR_80003.equals(l_ex.getErrorInfo())
                        || WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_ex.getErrorInfo())
                        || WEB3ErrorCatalog.SYSTEM_ERROR_80006.equals(l_ex.getErrorInfo()))
                    {
                        log.debug("該当するデータが存在しません。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                            l_ex.getErrorMethod());
                    }
                    else if (WEB3ErrorCatalog.SYSTEM_ERROR_80017.equals(l_ex.getErrorInfo()))
                    {
                        log.debug("入力パラメータチェックエラー。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00830,
                            l_ex.getErrorMethod());
                    }
                    throw l_ex;
                }

                //1.5.17.set顧客
                    l_lotResultCsv.setAccount(l_intRowIndex,l_maMainAccount.getAccountId());
    
                //1.3.10.validate重複顧客(int)←1.5.16で顧客コードが6桁から7桁にセットしなおされるため、7桁同士の比較ができるよう移動
                l_lotResultCsv.validateDuplicateAccount(l_intRowIndex);                    
    //            }
    //            catch(NotFoundException l_ex)
    //            {
    //                
    //                log.exiting(STR_METHOD_NAME);
    //				//2004/12/06 U00520 ﾋﾞｼﾞﾈｽｴﾗｰに修正　@坂上@@SRA START
    //				throw new WEB3BusinessLayerException(
    //				WEB3ErrorCatalog.BUSINESS_ERROR_00017,
    ////				throw new WEB3SystemLayerException(
    ////				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
    //				//2004/12/06 U00520 ﾋﾞｼﾞﾈｽｴﾗｰに修正　@坂上@@SRA END
    //                    STR_METHOD_NAME,
    //                    l_ex.getMessage(),
    //                    l_ex);
    //                
    //            }            
                //1.5.18.add当選数量(double)
                l_lotResultCsv.addElectedQuantity(l_dblPrizeQuantity);
                //1.5.19.validateIPO申告（繰上抽選）(ArrayList, String, String, double)
    //            try
    //            {
                    
                    this.validateIpoOrderAdvanceLot(l_assessmentList,
                        l_lotResultCsv.getAccountCode(l_intRowIndex),
                        l_lotResultCsv.getBranchCode(l_intRowIndex),
                        l_dblPrizeQuantity);
                    
    //            }
    //            catch(WEB3BaseException l_ex)
    //            {
    //                
    //                //(1.5.17)get当選数量()～validateIPO申告()｝の手続きで例外が発生した場合、アップロードエラーを更新する
    //                l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());
    //                
    //                log.exiting(STR_METHOD_NAME);
    //                log.error(STR_METHOD_NAME, l_ex);
    //                throw new WEB3BaseException(l_ex.getErrorInfo(), STR_METHOD_NAME, l_strBranchCode + "." + l_strAccountCode);
    //                
    //            }
			  }
			  catch(WEB3BaseException l_ex)
			  {
                
				  //1.5.19.get当選数量()～validateIPO申告()の手続きで例外が発生した場合、アップロードエラーを更新する
				  l_lotResultCsv.saveUploadError(l_ex.getErrorInfo());                
				  log.exiting(STR_METHOD_NAME);
				  log.error(STR_METHOD_NAME, l_ex);
				  throw new WEB3BaseException(
                      l_ex.getErrorInfo(), 
                      STR_METHOD_NAME,
                      l_strBranchCodeErrDisp + "," + l_strAccountCodeErrDisp);                
			  }
			  catch(NotFoundException nf_ex)
			  {
				l_lotResultCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_01386);
				log.exiting(STR_METHOD_NAME);
				log.error(STR_METHOD_NAME, nf_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                    STR_METHOD_NAME,
                    l_strBranchCodeErrDisp + "," + l_strAccountCodeErrDisp);
			  }
			//2004/12/7 U00525 例外処理のスコープが不適切　@坂上@@SRA END       
        }
        //1.6.get割当確定数量合計値()
        double l_dblQuantityTotal = l_lotResultCsv.getAllotQuantityTotal();
        //1.7.get当選数量合計値()
        double l_dblPrizeQuantityTotal = l_lotResultCsv.getElectedQuantityTotal();
        //1.8.get当社取扱数量()
        double l_dblDealingQuantity = l_product.getDealingQuantity();
        //1.9.{ 当選数量合計値 > （当社取扱数量 - 割当確定数量） }の場合、例外をスローする
        double l_dblRemoteQuantity = l_dblDealingQuantity - l_dblQuantityTotal;
        if(l_dblPrizeQuantityTotal > l_dblRemoteQuantity)
        {
            
            //1.9.1.saveアップロードエラー(ErrorInfo)
            l_lotResultCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_00599);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00599,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
    }
    
    /**
     * (validateIPO申告（新規抽選）)<BR>
     * 新規抽選アップロード行について、IPO申告データのチェックを行う。<BR>
     * <BR>
     * １）　@配列取得<BR>
     * 　@IPO申告List.toArray()にて、IPO申告の配列を取得する。<BR>
     * <BR>
     * ２）　@顧客に該当するIPO申告を取得する。<BR>
     * 　@１）で取得した配列より、以下の条件に一致する要素を取得する。<BR>
     * 　@取得できなかった場合は、申告済みの顧客ではないと判定し、例外をスローする。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@顧客コード == IPO申告[index].get顧客コード()<BR>
     * 　@部店コード == IPO申告[index].getMainAccount().getBranch().getBranchCode()<BR>  
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00510<BR>
     * <BR>
     * ３）　@顧客に該当するIPO申告のチェック<BR>
     * <BR>
     * 　@３－１）　@取消のチェック<BR>
     * 　@　@IPO申告が有効でない場合（IPO申告[index].getIPOI申告有効状態() == 
     * <BR>OrderOpenStatusEnum.CLOSE）、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00511<BR>
     * <BR>
     * 　@３－２）　@抽選結果のチェック<BR>
     * 　@　@IPO申告が抽選結果更新済みの場合（IPO申告[index].is抽選結果更新済()<BR> == true）、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00512<BR>
     * <BR>
     * 　@３－３）　@数量のチェック<BR>
     * 　@　@当選数量が申告数量を超えている場合（IPO申告[index].get数量() < <BR>当選数量）、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00513<BR>
     * <BR>
     * ４）　@チェック後の要素をListより削除<BR>
     * 　@IPO申告List.indexOf(), remove()にて、該当要素をListより削除する。<BR>
     * <BR>
     * 　@[indexOf()に指定する引数]<BR>
     * 　@arg0：　@IPO申告[index]<BR>
     * <BR>
     * 　@[remove()に指定する引数]<BR>
     * 　@arg0：　@（indexOf()の戻り値）<BR>
     * <BR>
     * @@param l_orderList - IPO申告のArrayList
     * 
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_dblElectedQuantity - 当選数量
     * @@roseuid 40F5292B019F
     */
    protected void validateIpoOrderNewLot(ArrayList l_orderList,
        String l_strAccountCode, 
        String l_strBranchCode,
        double l_dblElectedQuantity)
        throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " validateIpoAssessmentNewLot(ArrayList, String, String, double)";
            
        log.entering(STR_METHOD_NAME);
        
        if (l_strAccountCode == null || l_strBranchCode == null)
        {
            log.debug("入力パラメータチェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00830,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力パラメータチェックエラー。");
        }

        //１）配列取得
        int l_intLength = l_orderList.size();
        WEB3IpoOrderImpl[] l_ipoOrders = new WEB3IpoOrderImpl[l_intLength];
        l_orderList.toArray(l_ipoOrders);
        
        int l_intCnt = 0;
        for(int i = 0; i < l_intLength; i++)
        {
            
            //２）　@顧客に該当するIPO申告を取得する。
            String l_strCode = l_ipoOrders[i].getAccountCode();
            String l_strBranchCodes = l_ipoOrders[i].getMainAccount().getBranch().getBranchCode();
            if(l_strAccountCode.equals(l_strCode) &&
                l_strBranchCode.equals(l_strBranchCodes))
            {
                
                l_intCnt++;
                //３）　@顧客に該当するIPO申告のチェック
                OrderOpenStatusEnum l_orderOpenStatus = l_ipoOrders[i].getOrderOpenStatus();
                //３－１）　@取消のチェック
                if(OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatus))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00511,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //３－２）　@抽選結果のチェック
                if(l_ipoOrders[i].isLotResultUpdated())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00512,
                        this.getClass().getName() + STR_METHOD_NAME);                
                }
                //３－３）　@数量のチェック
                if(l_ipoOrders[i].getQuantity() < l_dblElectedQuantity)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00513,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //４）　@チェック後の要素をListより削除
                int l_intIndex = l_orderList.indexOf(l_ipoOrders[i]);
                l_orderList.remove(l_intIndex);
                log.exiting(STR_METHOD_NAME);
                break;
            }
        }
        if(l_intCnt == 0)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00510,
                this.getClass().getName() + STR_METHOD_NAME);  
            
        }

    }
    
    /**
     * (validateIPO申告（繰上抽選）)<BR>
     * 繰上抽選アップロード行について、IPO申告データのチェックを行う。<BR>
     * <BR>
     * １）　@配列取得<BR>
     * 　@IPO申告List.toArray()にて、IPO申告の配列を取得する。<BR>
     * <BR>
     * ２）　@顧客に該当するIPO申告を取得する。<BR>
     * 　@１）で取得した配列より、以下の条件に一致する要素を取得する。<BR>
     * 　@取得できなかった場合は、補欠者のデータが存在しないと判定し、例外をスローする。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@顧客コード == IPO申告[index].get顧客コード()<BR>
     * 　@部店コード == IPO申告[index].getMainAccount().getBranch().getBranchCode()<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01995<BR>
     * <BR>
     * ３）　@顧客に該当するIPO申告のチェック<BR>
     * <BR>
     * 　@３－１）　@取消のチェック<BR>
     * 　@　@IPO申告が有効でない場合（IPO申告[index].getIPOI申告有効状態() == 
     * <BR>OrderOpenStatusEnum.CLOSE）、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00511<BR>
     * <BR>
     * 　@３－２）　@（補欠当選の場合）購入申込のチェック<BR>
     * 　@　@補欠当選でIPO申告が購入申込済みでない場合（当選数量 != 0 && <BR>IPO申告[index].is購入申込() == 
     * false）、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00514<BR>
     * <BR>
     * 　@３－３）　@数量のチェック<BR>
     * 　@　@当選数量が購入申込数量を超えている場合<BR>
     * （IPO申告[index].IPO申告行.購入申込数量 < 当選数量）、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01991<BR>
     * <BR>
     * 　@３－４）　@繰上抽選済みのチェック<BR>
     * 　@　@既に繰上抽選結果が更新されている場合<BR>
     * （IPO申告[index].IPO申告行.抽選結果（繰上） != 0：DEFAULT（未抽選））、<BR>例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00515<BR>
     * <BR>
     *   ３－５）　@（補欠落選の場合）辞退のチェック<BR>
     *   補欠落選でIPO申告が辞退済の場合<BR>
     * （当選数量 == 0 && IPO申告[index].is辞退() == true）、<BR>
     *  例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01741<BR>
     * <BR>
     * ４）　@チェック後の要素をListより削除<BR>
     * 　@IPO申告List.indexOf(), remove()にて、該当要素をListより削除する。<BR>
     * <BR>
     * 　@[indexOf()に指定する引数]<BR>
     * 　@arg0：　@IPO申告[index]<BR>
     * <BR>
     * 　@[remove()に指定する引数]<BR>
     * 　@arg0：　@（indexOf()の戻り値）<BR>
     * @@param l_orderList - IPO申告のArrayList
     * 
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_dblElectedQuantity - 当選数量
     * @@roseuid 40F606170273
     */
    protected void validateIpoOrderAdvanceLot(
        ArrayList l_orderList,
        String l_strAccountCode,
        String l_strBranchCode,
        double l_dblElectedQuantity) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateIpoOrderAdvanceLot(ArrayList, String, String, double)";
            
        log.entering(STR_METHOD_NAME);
        
        if (l_strAccountCode == null || l_strBranchCode == null)
        {
            log.debug("入力パラメータチェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00830,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力パラメータチェックエラー。");
        }
        //１）配列取得
        int l_intLength = l_orderList.size();
        WEB3IpoOrderImpl[] l_ipoOrders = new WEB3IpoOrderImpl[l_intLength];
        l_orderList.toArray(l_ipoOrders);
        int l_intFlag = 0;
        for(int i = 0; i < l_intLength; i++)
        {
            
            //２）　@顧客に該当するIPO申告を取得する。
            String l_strCode = l_ipoOrders[i].getAccountCode();
            log.debug("l_strCode = " + l_strCode);
            String l_strBranchCodes = l_ipoOrders[i].getMainAccount().getBranch().getBranchCode();
            if(l_strAccountCode.equals(l_strCode) &&
                l_strBranchCode.equals(l_strBranchCodes))
            {
                
                l_intFlag++;
                //３）　@顧客に該当するIPO申告のチェック
                OrderOpenStatusEnum l_orderOpenStatus = l_ipoOrders[i].getOrderOpenStatus();
                //３－１）　@取消のチェック
                if(OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatus))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00511,
                    this.getClass().getName() + STR_METHOD_NAME);
                }
                //３－２）　@（補欠当選の場合）購入申込のチェック
                if(l_dblElectedQuantity != 0 && !(l_ipoOrders[i].isOffered()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00514,
                        this.getClass().getName() + STR_METHOD_NAME);                
                }
                //３－３）　@数量のチェック
                IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_ipoOrders[i].getDataSourceObject();
				if(l_ipoOrderParams.getApplicationQuantity() < l_dblElectedQuantity)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01991,
                        this.getClass().getName() + STR_METHOD_NAME);         
                }
                //３－４）　@繰上抽選済みのチェック
                if(!WEB3LotResultRetryDef.DEFAULT.equals(l_ipoOrderParams.getLotResultRetry()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00515,
                        this.getClass().getName() + STR_METHOD_NAME);                  
                }
                //３－５）　@（補欠落選の場合）辞退のチェック
                if(l_dblElectedQuantity == 0 && (l_ipoOrders[i].isDecline()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01741,
                        this.getClass().getName() + STR_METHOD_NAME);                
                }                
                //４）　@チェック後の要素をListより削除
                int l_intIndex = l_orderList.indexOf(l_ipoOrders[i]);
                l_orderList.remove(l_intIndex);
            
                log.exiting(STR_METHOD_NAME);
                break;            
            }
        } 
        if(l_intFlag == 0)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01995,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
    }
}
@
