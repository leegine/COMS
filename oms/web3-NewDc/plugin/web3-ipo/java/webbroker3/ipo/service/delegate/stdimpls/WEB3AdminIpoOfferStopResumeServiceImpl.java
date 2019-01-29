head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoOfferStopResumeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO募集停止再開サービス実装クラス(WEB3AdminIpoOfferStopResumeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/26 李海波 (中訊) 新規作成
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>043
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>061
*/

package webbroker3.ipo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmResponse;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.service.delegate.WEB3AdminIpoOfferStopResumeService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3LogUtility;

/**
 * 管理者IPO募集停止再開サービス実装クラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIpoOfferStopResumeServiceImpl implements WEB3AdminIpoOfferStopResumeService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoOfferStopResumeServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE30290
     */
    public WEB3AdminIpoOfferStopResumeServiceImpl() 
    {
     
    }
    
    /**
     * (validate募集停止再開)<BR>
     * 管理者IPO募集停止再開確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・募集停止／再開）validate募集停止再開」参照。<BR>
     * <BR>
     *  ========================================================<BR>                        
     * シーケンス図(「IPO（管理者・募集停止／再開）validate募集停止再開」): <BR>                      
     *      1.6(*1) （is削除銘柄() == true）の場合、例外をスローする<BR>                      
     *          class: WEB3BusinessLayerException<BR>                       
     *          tag:   BUSINESS_ERROR_00588<BR>                     
     *      1.6(*1) （is募集中止() == true）の場合、例外をスローする<BR>                      
     *          class: WEB3BusinessLayerException<BR>                       
     *          tag:   BUSINESS_ERROR_00589<BR>                     
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「IPO（管理者・募集停止／再開）validate募集停止再開」): <BR>
     *         1.10(*2) （isスケジュール決定() == true && is購入申込終了（当社設定）() == true 
     *                    && is取扱中() == true）の場合、例外をスローする<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00590<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図(「IPO（管理者・募集停止／再開）validate募集停止再開」): <BR>
     *         1.12(*3) （isスケジュール決定() == true && is公開済() == true）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00560<BR>
     * ==========================================================<BR>
     * @@param l_request - 管理者IPO募集停止再開確認リクエストデータオブジェクト
     * 
     * @@return webbroker3.ipo.message.WEB3AdminIpoOfferStopResumeConfirmResponse
     * @@roseuid 40D0166F01B3
     */
    protected WEB3AdminIPOOfferStopResumeConfirmResponse validateOfferStopResume(
        WEB3AdminIPOOfferStopResumeConfirmRequest l_request) throws  WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateOfferStopResume(WEB3AdminIpoOfferStopResumeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.IPO銘柄(long) l_lng = id
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
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,STR_METHOD_NAME);
            
        }
           
        //1.4.is削除銘柄()
        boolean l_blnProduct = l_ipoProduct.isDeletedProduct();
        
        //1.5.is中止()
        boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
        
        //1.6.is削除銘柄() == true）の場合、例外をスローする
        if(l_blnProduct)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //(is募集中止() == true）の場合、例外をスローする
        if(l_blnDiscontinuation)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //1.7.isスケジュール決定()
        boolean l_blnScheduleDecision = l_ipoProduct.isScheduleDecision();
        
        //1.8.is購入申込終了（当社設定）()
        boolean l_blnSetting = l_ipoProduct.isOfferEnd();
        
        //1.9.is取扱中()
        boolean l_blnDealtInProcess = l_ipoProduct.isDealtInProcess();
        
        //1.10.（isスケジュール決定() == true && is購入申込終了（当社設定）() == true && is取扱中() == true）の場合、例外をスローする
        if(l_blnScheduleDecision && l_blnSetting && l_blnDealtInProcess)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00590,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //1.11.is公開済()
        boolean l_blnPublicEnd = l_ipoProduct.isPublicEnd();
        
        //1.12.(*3)（isスケジュール決定() == true && is公開済() == true）の場合、例外をスローする
        if(l_blnScheduleDecision && l_blnPublicEnd)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00560,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }        
        
        //1.13.createIPO銘柄情報(long)
        WEB3IpoProductInfoService l_ipoProductInfoServiceImpl = 
            (WEB3IpoProductInfoService)Services.getService(WEB3IpoProductInfoService.class);        
        WEB3IPOProductInfo l_info = l_ipoProductInfoServiceImpl.createIpoProductInfo(l_lngId);
        
        //1.14.管理者IPO募集停止再開確認レスポンス(WEB3GenRequest)
        WEB3GenResponse l_genResponse = l_request.createResponse();        
        WEB3AdminIPOOfferStopResumeConfirmResponse l_response = (WEB3AdminIPOOfferStopResumeConfirmResponse)l_genResponse;
        
        l_response.ipoProductInfo = l_info;
        l_response.offerStateDiv = l_blnDealtInProcess;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (submit募集停止再開)<BR>
     * 管理者IPO募集停止再開完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・募集停止／再開）submit募集停止再開」参照。<BR>
     * @@param l_request - 管理者IPO募集停止再開完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIpoOfferStopResumeCompleteResponse
     * @@roseuid 40D0166F01D2
     */
    protected WEB3AdminIPOOfferStopResumeCompleteResponse submitOfferStopResume(
        WEB3AdminIPOOfferStopResumeCompleteRequest l_request) throws  WEB3BaseException
    {

        final String STR_METHOD_NAME = " submitOfferStopResume(WEB3AdminIpoOfferStopResumeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        //1.1.getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.3.validate取引パスワード(String)
        String l_strPassword = l_request.password;
        l_administartor.validateTradingPassword(l_strPassword);
        
        //1.4.IPO銘柄(long) l_lng = id
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
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,STR_METHOD_NAME);
            
        }
        
        //1.5.is削除銘柄()
        boolean l_blnProduct = l_ipoProduct.isDeletedProduct();
        
        //1.6.is中止()
        boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
        
        //1.7.is削除銘柄() == true）の場合、例外をスローする
        if(l_blnProduct)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        //(is募集中止() == true）の場合、例外をスローする
        if(l_blnDiscontinuation)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //1.8.isスケジュール決定()
        boolean l_blnScheduleDecision = l_ipoProduct.isScheduleDecision();
        
        //1.9.is購入申込終了（当社設定）()
        boolean l_blnSetting = l_ipoProduct.isOfferEnd();
        
        //1.10.is取扱中()
        boolean l_blnDealtInProcess = l_ipoProduct.isDealtInProcess();
        
        //1.11.（isスケジュール決定() == true && is購入申込終了（当社設定）() == true && is取扱中() == true）の場合、例外をスローする
        if(l_blnScheduleDecision && l_blnSetting && l_blnDealtInProcess)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00590,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //1.12.is公開済()
        boolean l_blnPublicEnd = l_ipoProduct.isPublicEnd();
        
        //1.13.(*3)（isスケジュール決定() == true && is公開済() == true）の場合、例外をスローする
        if(l_blnScheduleDecision && l_blnPublicEnd)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00560,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }        
        
        //1.14.更新用の行オブジェクトを生成する。
        l_ipoProduct.createForUpdateParams();   
        
        //1.15.set募集停止／再開( )
        l_ipoProduct.setRecruitStopResumption();
        //1.16.IPOプロダクトマネージャ
        l_ipoProductManagerImpl.saveProduct(l_ipoProduct);
        
        //1.17.createResponse()
        WEB3GenResponse l_genResponse = l_request.createResponse();        
        WEB3AdminIPOOfferStopResumeCompleteResponse l_response = (WEB3AdminIPOOfferStopResumeCompleteResponse)l_genResponse;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * 管理者IPO募集停止再開処理を実施する。<BR>
     * <BR>
     * １）　@業務日時設定<BR>
     * 　@取引時間管理.setBusinessTimestamp()をコールする。<BR>
     * <BR>
     * ２）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者IPO募集停止再開確認リクエストの場合<BR>
     * 　@－validate募集停止再開()をコールする。<BR>
     * ○ 引数のリクエストデータが、管理者IPO募集停止再開完了リクエストの場合<BR>
     * 　@－submit募集停止再開()をコールする。<BR>
     * <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40D0166F02BC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        // Timestampリセット
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        // Timestamp設定
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_processTime);

        WEB3GenResponse l_response = null;
        
        if(l_request instanceof WEB3AdminIPOOfferStopResumeConfirmRequest)
        {

            WEB3AdminIPOOfferStopResumeConfirmResponse l_ipoOfferStopResumeConfirmResponse = 
                this.validateOfferStopResume((WEB3AdminIPOOfferStopResumeConfirmRequest)l_request);
                
            l_response = l_ipoOfferStopResumeConfirmResponse;
            
        }
        else if(l_request instanceof WEB3AdminIPOOfferStopResumeCompleteRequest)
        {
            
            WEB3AdminIPOOfferStopResumeCompleteResponse l_ipoOfferStopResumeCompleteResponse = 
                this.submitOfferStopResume((WEB3AdminIPOOfferStopResumeCompleteRequest)l_request);
                
            l_response = l_ipoOfferStopResumeCompleteResponse;
            
        }
        else
        {
            // Timestampリセット
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        // Timestampリセット
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
}
@
