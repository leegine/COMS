head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoProductChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 管理者IPO銘柄訂正サービスImpl(WEB3AdminIpoProductChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2004/08/16 彭巍 (中訊) 新規作成
                   2004/09/02 李頴淵 (中訊) 修正
Revesion History : 2004/12/27 坂上(SRA) 残対応>>>041
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>042,043
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>060
Revesion History : 2005/01/17 坂上(SRA) 修正
Revesion History : 2010/09/21 趙天月 (中訊) 実装の問題 No.018
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PublicMarketDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoBookbuildingOrderActionImpl;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoBookbuildingOrderActionParams;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.ipo.data.IpoOrderDao;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeInputResponse;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductChangeService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

/**
 * (管理者IPO銘柄訂正サービスImpl)<BR>
 * 
 * @@author 彭巍
 * @@version 1.0
 */

public class WEB3AdminIpoProductChangeServiceImpl implements WEB3AdminIpoProductChangeService 
{
    
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminIpoProductChangeServiceImpl.class);
     
    /**
     * (get入力画面)<BR>
     * 管理者IPO銘柄訂正入力画面表示データ作成処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・銘柄訂正）get入力画面」参照。<BR>
     * <BR>
     *  ========================================================<BR>                                    
     * シーケンス図(「IPO（管理者・銘柄訂正）get入力画面」): <BR>
     *      7（is削除銘柄() == true）の場合、例外をスローする<BR>                             
     * <BR>                                    
     *   class: WEB3BusinessLayerException<BR>                                   
     *   tag:   BUSINESS_ERROR_00588<BR>                                 
     * シーケンス図(「IPO（管理者・銘柄訂正）get入力画面」):<BR> 
     *      7（is募集中止() == true）の場合、例外をスローする<BR>                                 
     * <BR>                                    
     *   class: WEB3BusinessLayerException<BR>                                   
     *   tag:   BUSINESS_ERROR_00589<BR>                                 
     * ==========================================================<BR>
     * <BR>   
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄訂正入力リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductChangeInputResponse
     * @@roseuid 40C94577004A
     */
    protected WEB3AdminIPOProductChangeInputResponse getInputScreen(WEB3AdminIPOProductChangeInputRequest l_request) throws WEB3BaseException 
    {
       
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminIPOProductChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
                
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限( )       
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //IPO銘柄(long)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl =
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();

        try
        {
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);
            
            //is削除銘柄( )
            boolean l_blnProduct = l_ipoProduct.isDeletedProduct();
            
            //is中止( )
            boolean l_blnDiscontinuation = l_ipoProduct.isDiscontinuation();
            
            //(*1) （is削除銘柄() == true Or is中止() == true）の場合、例外をスローする
            // 2004/11/26 U00478 is削除銘柄とis中止のif節の評価式が逆　@坂上@@SRA START
            if(l_blnProduct)
            {  
                log.debug("is削除銘柄() == true");
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_00588, 
                    this.getClass().getName() + STR_METHOD_NAME);            
            }
            if(l_blnDiscontinuation)
            {  
                log.debug("is募集中止() == true");
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_00589, 
                    this.getClass().getName() + STR_METHOD_NAME);                              
            }
//						if(l_blnDiscontinuation)
//						{  
//							log.debug("is削除銘柄() == true");
//							throw new WEB3BusinessLayerException
//								(WEB3ErrorCatalog.BUSINESS_ERROR_00588, 
//								this.getClass().getName() + STR_METHOD_NAME);            
//						}
//						if(l_blnProduct)
//						{  
//							log.debug("is中止() == true");
//							throw new WEB3BusinessLayerException
//								(WEB3ErrorCatalog.BUSINESS_ERROR_00589, 
//								this.getClass().getName() + STR_METHOD_NAME);            
//						}            
			// 2004/11/26 U00478 is削除銘柄とis中止のif節の評価式が逆　@坂上@@SRA END
			
			            
            //getSystemTimestamp( )           
            Timestamp l_tsSystemTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();
       
            //createIPO銘柄情報(long)
            WEB3IpoProductInfoService l_ipoProductInfoService = (WEB3IpoProductInfoService)Services.getService
                (WEB3IpoProductInfoService.class);
            WEB3IPOProductInfo l_ipoProductInfo = l_ipoProductInfoService.createIpoProductInfo(l_lngId);
            
            //管理者IPO銘柄訂正入力レスポンス(WEB3GenRequest)
            WEB3AdminIPOProductChangeInputResponse l_ipoProductChangeInputResponse = (WEB3AdminIPOProductChangeInputResponse)l_request.createResponse();
            
            // (*2) プロパティセット
            l_ipoProductChangeInputResponse.publicOfferingMarketList = new String[17];
            
            l_ipoProductChangeInputResponse.publicOfferingMarketList[0] = WEB3PublicMarketDef.TOKYO_STOCK_EXCHANGE;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[1] = WEB3PublicMarketDef.TSE_NO_ONE_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[2] = WEB3PublicMarketDef.TSE_NO_TWO_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[3] = WEB3PublicMarketDef.MOTHERS;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[4] = WEB3PublicMarketDef.OSAKA_SECURITIES_EXCHANGE;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[5] = WEB3PublicMarketDef.OSE_NO_ONE_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[6] = WEB3PublicMarketDef.OSE_NO_TWO_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[7] = WEB3PublicMarketDef.NAGOYA_STOCK_EXCHANGE;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[8] = WEB3PublicMarketDef.NSE_NO_ONE_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[9] = WEB3PublicMarketDef.NSE_NO_TWO_DEPT;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[10] = WEB3PublicMarketDef.CENTREX;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[11] = WEB3PublicMarketDef.FUKUOKA_STOCK_EXCHANGE;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[12] = WEB3PublicMarketDef.Q_BOARD;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[13] = WEB3PublicMarketDef.SAPPORO_STOCK_EXCHANGE;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[14] = WEB3PublicMarketDef.AMBITIOUS;
            l_ipoProductChangeInputResponse.publicOfferingMarketList[15] = WEB3PublicMarketDef.JASDAQ_STANDARD;            //JASDAQ（スタンダード）
            l_ipoProductChangeInputResponse.publicOfferingMarketList[16] = WEB3PublicMarketDef.JASDAQ_CLOSE;               //JASDAQ（グロース）
            l_ipoProductChangeInputResponse.currentDate = l_tsSystemTimestamp;
            l_ipoProductChangeInputResponse.ipoProductInfo = l_ipoProductInfo;
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductChangeInputResponse;
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            // 2004/11/19 障害管理票No.U00443 ビジネスエラーでなくシステムエラーに変換している 水落@@SRA START
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
            // 2004/11/19 障害管理票No.U00443 ビジネスエラーでなくシステムエラーに変換している 水落@@SRA END
        }      
    }
    
    /**
     * (validate銘柄訂正)<BR>
     * 管理者IPO銘柄訂正確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・銘柄訂正）validate銘柄訂正」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄訂正確認リクエストデータオブジェクト
     * 
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmResponse
     * @@roseuid 40C945770069
     */
    protected WEB3AdminIPOProductChangeConfirmResponse validateProductChange(WEB3AdminIPOProductChangeConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateProductChange(WEB3AdminIPOProductChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.validate( )
        l_request.validate();
        log.debug("validate");
        
        //1.2.getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //1.3.validate権限( )
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.4.IPO銘柄(long)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        
        try
        {
            WEB3IpoProductManagerImpl l_ipoProductManagerImpl =
                (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProductBefore = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);

            //1.5.is削除銘柄()
            boolean l_blnProduct = l_ipoProductBefore.isDeletedProduct();
            //1.6.is中止()
            boolean l_blnDiscontinuation = l_ipoProductBefore.isDiscontinuation();
            //1.7.is削除銘柄() == true）の場合、例外をスローする
            if(l_blnProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }
            //(is中止() == true）の場合、例外をスローする
            if(l_blnDiscontinuation)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }       
       
            //1.8.createIPO銘柄(IPO銘柄Params, IPO銘柄情報, 管理者)
            WEB3IpoProductInfoService l_ipoProductInfoService = 
                (WEB3IpoProductInfoService)Services.getService(WEB3IpoProductInfoService.class);
            
            ////1.8.1.IPO銘柄        
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProductBefore.getDataSourceObject();
            IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow);                       
            WEB3IpoProductImpl l_ipoProductAfter = l_ipoProductInfoService.createIpoProduct
                (l_ipoProductParams, l_request.ipoProductInfo, l_administrator);           
            log.debug("createIPO銘柄");
            
            //1.9.validateスケジュール(null;)    
            l_ipoProductAfter.validateSchedule(null);
            log.debug("validateスケジュール");
            
            //1.10.get証券会社コード( )
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
            //1.11.validate期間重複登録(String, String, Date, Date, long)                 
            l_ipoProductManagerImpl.validateDuplicateTerm
                (l_strInstitutionCode, l_request.ipoProductInfo.productCode, l_request.ipoProductInfo.bookBuildingStartDate,
                l_request.ipoProductInfo.publicOfferingDate.startDate, l_lngId);    
            log.debug("validate期間重複登録(String, String, Date, Date, long)");
            
            //1.12.validateChangeIPO銘柄(IPO銘柄, IPO銘柄)
            log.debug("validateChangeIPO銘柄(IPO銘柄, IPO銘柄)");
            this.validateChangeIpoProduct(l_ipoProductBefore, l_ipoProductAfter); 
//            ////1.12.1.isスケジュール決定(訂正前)
//            boolean l_blSchChkBfr = l_ipoProductBefore.isScheduleDecision();
//
//            ////1.12.2.isスケジュール決定(訂正後)     
//            boolean l_blSchChkAft = l_ipoProductAfter.isScheduleDecision();
//            if(!(l_blSchChkBfr) || !(l_blSchChkAft))
//            {
//            
//                log.exiting(STR_METHOD_NAME);
//                throw new WEB3BusinessLayerException(
//                //"スケジュールが未定であることの通知"
//                    WEB3ErrorCatalog.BUSINESS_ERROR_00528,
//                    this.getClass().getName() + STR_METHOD_NAME);
//             
//            }
                      
            //1.13.validate株式銘柄()
            l_ipoProductAfter.validateProduct();
            log.debug("validate株式銘柄");
                                   
            //1.14.set銘柄名( )
            log.debug("set銘柄名( )");
            l_ipoProductAfter.setStandardName();
        
            //1.15.createResponse( )
            WEB3AdminIPOProductChangeConfirmResponse l_productChangeConfirmResponse =  
                (WEB3AdminIPOProductChangeConfirmResponse)l_request.createResponse();
                
            //1.16.get銘柄名( )
            String l_strStandardName = l_ipoProductAfter.getStandardName();
            log.debug("l_strStandardName = " + l_strStandardName);
        
            //1.17.(*1) プロパティセット
            l_productChangeConfirmResponse.productName = l_strStandardName;
 
            log.exiting(STR_METHOD_NAME);               
            return l_productChangeConfirmResponse;
    
        }
        catch (NotFoundException l_ex)
        {  
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
 
    }
    
    /**
     * (submit銘柄訂正)<BR>
     * 管理者IPO銘柄訂正完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・銘柄訂正）submit銘柄訂正」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄訂正完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteResponse
     * @@roseuid 40C945770078
     */
    protected WEB3AdminIPOProductChangeCompleteResponse submitProductChange(WEB3AdminIPOProductChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {   
        final String STR_METHOD_NAME = " submitProductChange(WEB3AdminIPOProductChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        // 1.2.getInstanceFromログイン情報( )     
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate権限( )     
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE,true);
        
        //1.4.validate取引パスワード(String)
        l_administrator.validateTradingPassword(l_request.password);
               
        //1.5.IPO銘柄(long)
        long l_lngId = Long.parseLong(l_request.id);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                
        try
        {
            WEB3IpoProductManagerImpl l_ipoProductManagerImpl =
                (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            WEB3IpoProductImpl l_ipoProductBefore = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(l_lngId);

            //1.6.is削除銘柄()
            boolean l_blnProduct = l_ipoProductBefore.isDeletedProduct();
            //1.7.is中止()
            boolean l_blnDiscontinuation = l_ipoProductBefore.isDiscontinuation();
            //1.8.is削除銘柄() == true）の場合、例外をスローする
            if(l_blnProduct)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00588,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }
            //(is中止() == true）の場合、例外をスローする
            if(l_blnDiscontinuation)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                    this.getClass().getName() + STR_METHOD_NAME);
            
            }       
       
            //1.9.createIPO銘柄(IPO銘柄Params, IPO銘柄情報, 管理者)
            WEB3IpoProductInfoService l_ipoProductInfoService = 
                (WEB3IpoProductInfoService)Services.getService(WEB3IpoProductInfoService.class);
            
            ////1.9.1.IPO銘柄        
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProductBefore.getDataSourceObject();
            IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow);                       
            WEB3IpoProductImpl l_ipoProductAfter = l_ipoProductInfoService.createIpoProduct
                (l_ipoProductParams, l_request.ipoProductInfo, l_administrator);           
            log.debug("createIPO銘柄");
            
            //1.10.validateスケジュール(新規作成日時　@Timestamp)    
            l_ipoProductAfter.validateSchedule(null);
            log.debug("validateスケジュール");
            
            //1.11.get証券会社コード( )
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
            //1.12.validate期間重複登録(String, String, Date, Date, long)                 
            l_ipoProductManagerImpl.validateDuplicateTerm
                (l_strInstitutionCode, l_request.ipoProductInfo.productCode, l_request.ipoProductInfo.bookBuildingStartDate,
                l_request.ipoProductInfo.publicOfferingDate.startDate, l_lngId);    
            log.debug("validate期間重複登録(String, String, Date, Date, long)");
            
            //1.13.validateChangeIPO銘柄(IPO銘柄, IPO銘柄)
            log.debug("validateChangeIPO銘柄(IPO銘柄, IPO銘柄)");
            this.validateChangeIpoProduct(l_ipoProductBefore, l_ipoProductAfter); 
//            ////1.13.1.isスケジュール決定(訂正前)
//            boolean l_blSchChkBfr = l_ipoProductBefore.isScheduleDecision();
//
//            ////1.13.2.isスケジュール決定(訂正後)     
//            boolean l_blSchChkAft = l_ipoProductAfter.isScheduleDecision();
//            if(!(l_blSchChkBfr) || !(l_blSchChkAft))
//            {
//            
//                log.exiting(STR_METHOD_NAME);
//                throw new WEB3BusinessLayerException(
//                //"スケジュールが未定であることの通知"
//                    WEB3ErrorCatalog.BUSINESS_ERROR_00528,
//                    this.getClass().getName() + STR_METHOD_NAME);
//             
//            }
            
            //1.14.validate株式銘柄()
            l_ipoProductAfter.validateProduct();
            log.debug("validate株式銘柄");
                        
            //1.15.set銘柄名( )
            log.debug("set銘柄名( )");
            l_ipoProductAfter.setStandardName();
            
            //1.16.save銘柄(IPO銘柄)
            l_ipoProductManagerImpl.saveProduct(l_ipoProductAfter);
            log.debug("save銘柄(IPO銘柄)");
                        
            //1.17.(*2) ブックビルディング終了日時が訂正された場合
            IpoProductRow l_ipoProductRowAfter = (IpoProductRow)(l_ipoProductAfter.getDataSourceObject());
            IpoProductRow l_ipoProductRowBefore = (IpoProductRow)(l_ipoProductBefore.getDataSourceObject());
            
            if(l_ipoProductRowBefore.getBookbuildingEndDatetime().compareTo(l_ipoProductRowAfter.getBookbuildingEndDatetime()) != 0)
            {           
                //1.17.1.findRowsByIpoProductId()
                log.debug("findRowsByIpoProductId");
                long l_lngIpoProductId = l_ipoProductRowAfter.getIpoProductId();
                List l_lisIpoOrders = IpoOrderDao.findRowsByIpoProductId(l_lngIpoProductId);
                
                //1.17.2.get管理者コード( )
                String l_strAdministratorCode = l_administrator.getAdministratorCode();
                
                //1.17.3.(*1.1) ブックビルディング申告各要素ごとのLOOP
                IpoOrderRow[] l_ipoOrderRow = null;
                l_ipoOrderRow = new IpoOrderRow[l_lisIpoOrders.size()];
                
                l_lisIpoOrders.toArray(l_ipoOrderRow); 
                          
                for(int i = 0; i < l_ipoOrderRow.length; i++)
                {                   
                    log.debug("updateIPO申告");
                    //1.17.3.1.updateIPO申告(long, Date, String)                       
                    this.updateIpoOrder
                        (l_ipoOrderRow[i].getIpoOrderId(),l_ipoProductRowAfter.getBookbuildingEndDatetime(),l_strAdministratorCode);
                }                                          
            }
            //1.18.createResponse( )
            WEB3AdminIPOProductChangeCompleteResponse l_productChangeCompleteResponse =  
                (WEB3AdminIPOProductChangeCompleteResponse)l_request.createResponse();
                
            log.exiting(STR_METHOD_NAME);
            return l_productChangeCompleteResponse;   
        }    
        catch(NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        } 
        catch(DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName()  + STR_METHOD_NAME,
                l_ex);
            
        } 
        catch(DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }       
    }

    /** 
     * 管理者IPO銘柄訂正処理を実施する。<BR>
     * <BR>
     * １）　@業務日時設定<BR>
     * 　@取引時間管理.setBusinessTimestamp()をコールする。<BR>
     * <BR>
     * ２）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者IPO銘柄訂正入力リクエストの場合<BR>
     * 　@－get入力画面()をコールする。<BR>
     * ○ 引数のリクエストデータが、管理者IPO銘柄訂正確認リクエストの場合<BR>
     * 　@－validate銘柄訂正()をコールする。<BR>
     * ○ 引数のリクエストデータが、管理者IPO銘柄訂正完了リクエストの場合<BR>
     * 　@－submit銘柄訂正()をコールする。<BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40C945770098
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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
        
        if(l_request instanceof WEB3AdminIPOProductChangeInputRequest)
        {
            WEB3AdminIPOProductChangeInputResponse l_response = getInputScreen(
                (WEB3AdminIPOProductChangeInputRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if(l_request instanceof WEB3AdminIPOProductChangeConfirmRequest)
        {
            WEB3AdminIPOProductChangeConfirmResponse l_productChangeConfirmResponse = validateProductChange(
                (WEB3AdminIPOProductChangeConfirmRequest)l_request);                
            
            log.exiting(STR_METHOD_NAME);
            return l_productChangeConfirmResponse;
        }
        else if(l_request instanceof WEB3AdminIPOProductChangeCompleteRequest)
        {
            WEB3AdminIPOProductChangeCompleteResponse l_ipoProductChangeCompleteResponse = submitProductChange(
                (WEB3AdminIPOProductChangeCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductChangeCompleteResponse;
        }
        else
        {
            // Timestampリセット
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
 
    }
    
    /**
     * (validateChangeIPO銘柄)<BR>
     * 訂正項目をチェックする。<BR>
     * 　@○　@訂正項目があるかチェックを行う。<BR>
     * 　@○　@決定済スケジュールが未定に訂正されていないかチェックを行う。<BR>
     * 　@○　@（ブックビル期間短縮の場合）スケジュール項目が決定されているかチェックを行う。<BR>
     * 　@○　@各項目について、訂正可能期間内かチェックを行う。<BR>
     * <BR>
     * １）　@訂正項目があるかチェックを行う。<BR>
     * 　@IPO銘柄（訂正前）.IPO銘柄行とIPO銘柄（訂正後）.IPO銘柄行の以下の各<BR>項目について、値が同じであるかを判定する。<BR>
     * すべての値が同じである場合、訂正項目が一つもないと判定し、例外をスローする。<BR>
     * <BR>
     * 　@[比較する項目]<BR>
     * 　@IPO登録区分<BR>
     * 　@IPO登録区分詳細<BR>
     * 　@銘柄コード<BR>
     * 　@銘柄名<BR>
     * 　@公開日<BR>
     * 　@公開日日数<BR>
     *   WEB3-IPO-A-CD-0024.xls<BR>
     *  ×　@市場ID，上場区分（→削除）<BR>
     *  ○　@公開市場（→追加）<BR>
     *   公開市場<BR>
     * 　@仮条件区分<BR>
     * 　@仮条件下限値<BR>
     * 　@仮条件上限値<BR>
     * 　@仮条件提示日<BR>
     * 　@公募数量<BR>
     * 　@売出数量<BR>
     * 　@当社取扱数量<BR>
     * 　@主幹事証券会社名<BR>
     * 　@購入申込単位<BR>
     * 　@刻み<BR>
     * 　@表示用単位区分<BR>
     *   成行可能<BR>
     * 　@ブックビルディング開始日時<BR>
     * 　@ブックビルディング終了日時<BR>
     * 　@公開価格決定日<BR>
     * 　@公開価格<BR>
     * 　@公開価格（ディスカウント率）<BR>
     * 　@抽選日<BR>
     * 　@抽選日日数<BR>
     * 　@購入申込開始日時（当社設定）<BR>
     * 　@購入申込終了日時（当社設定）<BR>
     * 　@購入申込開始日日数（当社設定）<BR>
     * 　@購入申込終了日日数（当社設定）<BR>
     * 　@購入申込開始日（目論見書記載）<BR>
     * 　@購入申込終了日（目論見書記載）<BR>
     * 　@購入申込開始日日数（目論見書記載）<BR>
     * 　@購入申込終了日日数（目論見書記載）<BR>
     * 　@発行会社ロゴファ@イルＵＲＬ<BR>
     * 　@発行会社ウェブサイトＵＲＬ<BR>
     * 　@発行会社概要<BR>
     * 　@備考<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00547<BR>
     * <BR>
     * ２）　@スケジュール決定チェック<BR>
     * 　@２－１）　@未定への変更チェック<BR>
     * 　@　@決定済のスケジュールを未定に訂正された場合、例外をスローする。<BR>
     * <BR>
     * 　@　@[Error条件]<BR>
     * 　@　@（IPO銘柄（訂正前）.isスケジュール決定() == true） &&<BR>
     * 　@　@（IPO銘柄（訂正後）.isスケジュール決定() == false）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00548<BR>
     * <BR>
     * 　@２－２）　@ブックビルディング終了日時の訂正チェック<BR>
     * 　@　@ブックビルディング終了日時が現在日時以前に訂正されていた場合、<BR>
     * 　@　@訂正後のIPO銘柄のスケジュールが未定であれば例外をスローする。<BR>
     * <BR>
     * 　@　@[Error条件]<BR>
     * 　@　@（IPO銘柄（訂正前）.ブックビルディング終了日時 != IPO銘柄（訂正後）.ブック<BR>ビルディング終了日時） &&<BR>
     * 　@　@（IPO銘柄（訂正後）.isブックビルディング終了() == true） &&<BR>
     * 　@　@（IPO銘柄（訂正後）.isスケジュール決定() == false）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00549<BR>
     * <BR>
     * ３）　@訂正可能期間チェック<BR>
     * <BR>
     * 　@３－０）　@訂正可能期間が、（～ブックビルディング開始日時）の項目のチェック<BR>
     * 　@<BR>
     * 　@・「公開価格」がNullかどうかに関係なく訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00582<BR>
     * 　@・「公開価格（ディスカウント率）」がNullかどうかに関係なく訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00584<BR>
     * <BR>
     * 　@３－１）　@訂正可能期間が、ブックビルディング開始前までの項目のチェック<BR>
     * 　@（IPO銘柄（訂正前）.isブックビルディング開始() == true）の場合、以下のチェック<BR>を実施する。<BR>
     * 　@<BR>
     * 　@・「IPO登録区分」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00550<BR>
     * 　@・「IPO登録区分詳細」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00551<BR>
     * 　@・「銘柄名」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00552<BR>
     * 　@・「公開市場」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00553<BR>
     * 　@・「仮条件区分」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00554<BR>
     * 　@・「仮条件上限値」がnullに訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00555<BR>
     * 　@・「仮条件下限値」がnullに訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00556<BR>
     * 　@・「仮条件提示日」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00557<BR>
     * 　@・「購入申込単位」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00558<BR>
     * 　@・「刻み」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00559<BR>
     * 　@・「表示用単位区分」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00561<BR>
     *   ・「成行可能」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01234<BR>
     * 　@・「主幹事証券会社名」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00562<BR>
     * 　@・「ブックビルディング開始日時」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00563<BR>
     * <BR>
     * 　@３－２）　@訂正可能期間が、新規抽選終了までの項目のチェック<BR>
     * 　@（IPO銘柄（訂正前）.is新規抽選終了() == true）の場合、以下のチェックを実施する。<BR>
     * <BR>
     * 　@・「公開日」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00564<BR>
     * 　@・「仮条件上限値」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00565<BR>
     * 　@・「仮条件下限値」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00566<BR>
     * 　@・「ブックビルディング終了日時」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00567<BR>
     * 　@・「公開価格決定日」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00568<BR>
     * 　@・「抽選日」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00569<BR>
     * 　@・「公開価格」がnullに訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00582<BR>
     * <BR>
     * 　@３－３）　@訂正可能期間が、公開日までの項目のチェック<BR>
     * 　@（IPO銘柄（訂正前）.is公開済() == true）の場合、以下のチェックを実施する。<BR>
     * <BR>
     * 　@・「銘柄コード」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00570<BR>
     * 　@・「公募数量」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00571<BR>
     * 　@・「売出数量」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00572<BR>
     * 　@・「当社取扱数量」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00573<BR>
     * 　@・「発行会社ロゴファ@イルURL」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00574<BR>
     * 　@・「発行会社ウェブサイトURL」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00575<BR>
     * 　@・「発行会社概要」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00576<BR>
     * 　@・「備考」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00577<BR>
     * <BR>
     * 　@３－４）　@訂正可能期間が、購入申込開始日（目論見書記載）までの項目のチェック<BR>
     * 　@（IPO銘柄（訂正前）.is購入申込開始（目論見書記載）() == true）の場合、<BR>以下のチェックを実施する。<BR>
     * <BR>
     * 　@・「購入申込開始日（目論見書記載）」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00578<BR>
     * <BR>
     * 　@３－５）　@訂正可能期間が、購入申込終了日（目論見書記載）までの項目のチェック<BR>
     * 　@（IPO銘柄（訂正前）.is購入申込終了（目論見書記載）() == true）の場合、<BR>以下のチェックを実施する。<BR>
     * <BR>
     * 　@・「購入申込終了日（目論見書記載）」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00579<BR>
     * 　@<BR>
     * 　@３－６）　@訂正可能期間が、購入申込開始日時（当社設定）までの項目のチェック<BR>
     * 　@（IPO銘柄（訂正前）.is購入申込開始（当社設定）() == true）の場合、以下<BR>のチェックを実施する。<BR>
     * <BR>
     * 　@・「購入申込開始日時（当社設定）」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00580<BR>
     * 　@・「公開価格」がNullでないとき訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00582<BR>
     * 　@・「公開価格（ディスカウント率）」がNullでないとき訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00584<BR>
     * <BR>
     * 　@３－７）　@訂正可能期間が、購入申込終了日時（当社設定）までの項目のチェック<BR>
     * 　@（IPO銘柄（訂正前）.is購入申込終了（当社設定）() == true）の場合、以下<BR>のチェックを実施する。<BR>
     * <BR>
     * 　@・「購入申込終了日時（当社設定）」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00581<BR>
     * 　@<BR>
     * 　@３－８）　@訂正可能期間が、（ブックビルディング開始日時～新規抽選終了）の項目のチェック<BR>
     * 　@（IPO銘柄（訂正前）.isブックビルディング開始() == false Or<BR>
     * 　@　@IPO銘柄（訂正前）.is新規抽選終了() == true）の場合、以下のチェックを実施する。<BR>
     * <BR>
     * 　@・「公開価格」がnullに訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00582<BR>
     * <BR>
     * 　@３－９）　@訂正可能期間が、（ブックビルディング開始日時～購入申込開始日<BR>時（当社設定））の項目のチェック<BR>
     * 　@（IPO銘柄（訂正前）.isブックビルディング開始() == false Or<BR>
     * 　@　@IPO銘柄（訂正前）.is購入申込開始（当社設定）() == true）の場合、以下<BR>のチェックを実施する。<BR>
     * <BR>
     * 　@・「公開価格」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00583<BR>
     * 　@・「公開価格（ディスカウント率）」が訂正されていれば、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00584<BR>
     * @@param l_ipoProductBeforeChanging - (IPO銘柄（訂正前）)<BR>
     * IPO銘柄オブジェクト<BR>
     * ※ 既存データの値
     * 
     * @@param l_ipoProductChanged - (IPO銘柄（訂正後）)<BR>
     * IPO銘柄オブジェクト<BR>
     * ※ 訂正入力値
     * @@roseuid 40C95A87027C
     */
    protected void validateChangeIpoProduct(WEB3IpoProductImpl l_ipoProductBeforeChanging, WEB3IpoProductImpl l_ipoProductChanged) throws WEB3BusinessLayerException 
    {
        String STR_METHOD_NAME = " validateChangeIpoProduct(WEB3IpoProductImpl,WEB3IpoProductImpl)";
                 log.entering(STR_METHOD_NAME);
        
        IpoProductRow l_ipoProductRow = (IpoProductRow)(l_ipoProductBeforeChanging.getDataSourceObject());
        IpoProductParams l_ipoProductBeforeChangingParams = new IpoProductParams(l_ipoProductRow);
        
        IpoProductRow l_ipoProductChangedRow = (IpoProductRow)(l_ipoProductChanged.getDataSourceObject());
        IpoProductParams l_ipoProductChangedParams = new IpoProductParams(l_ipoProductChangedRow);
        
            //IPO登録区分                                
        if (l_ipoProductBeforeChangingParams.getIpoRegistDiv().equals(l_ipoProductChangedParams.getIpoRegistDiv())                   
            //IPO登録区分詳細        
            && l_ipoProductBeforeChangingParams.getIpoRegistDetailDiv().equals(l_ipoProductChangedParams.getIpoRegistDetailDiv()) 
            //銘柄コード
            && l_ipoProductBeforeChangingParams.getProductCode().equals(l_ipoProductChangedParams.getProductCode())                   
            //銘柄名
            && compareToString(l_ipoProductBeforeChangingParams.getStandardName(), l_ipoProductChangedParams.getStandardName())                 
            //公開日
            && (WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getPublicOfferingDate(), 
                l_ipoProductChangedParams.getPublicOfferingDate()) == 0)   
            //公開日日数    
            && l_ipoProductBeforeChangingParams.getPublicOfferingDateCountIsNull() == l_ipoProductChangedParams.getPublicOfferingDateCountIsNull()
            && l_ipoProductBeforeChangingParams.getPublicOfferingDateCount() == l_ipoProductChangedParams.getPublicOfferingDateCount()  
            //公開市場
            && l_ipoProductBeforeChangingParams.getPublicMarket().equals(l_ipoProductChangedParams.getPublicMarket())                                                                
            //仮条件区分                                                                                                       
            && l_ipoProductBeforeChangingParams.getProvisionalValueDiv().equals(l_ipoProductChangedParams.getProvisionalValueDiv())   
            //仮条件下限値
            && l_ipoProductBeforeChangingParams.getProvisionalMinValueIsNull() == l_ipoProductChangedParams.getProvisionalMinValueIsNull()
            && l_ipoProductBeforeChangingParams.getProvisionalMinValue() == l_ipoProductChangedParams.getProvisionalMinValue()  
            //仮条件上限値 
            && l_ipoProductBeforeChangingParams.getProvisionalMaxValueIsNull() == l_ipoProductChangedParams.getProvisionalMaxValueIsNull()
            && l_ipoProductBeforeChangingParams.getProvisionalMaxValue() == l_ipoProductChangedParams.getProvisionalMaxValue() 
            //仮条件提示日
            && (WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getProvisionalValueOpenDate(), 
                l_ipoProductChangedParams.getProvisionalValueOpenDate()) == 0)
            //公募数量
            && l_ipoProductBeforeChangingParams.getPublicOfferingQuantityIsNull() == l_ipoProductChangedParams.getPublicOfferingQuantityIsNull()
            && l_ipoProductBeforeChangingParams.getPublicOfferingQuantity() == l_ipoProductChangedParams.getPublicOfferingQuantity()
            //売出数量
            && l_ipoProductBeforeChangingParams.getPublicSalesQuantityIsNull() == l_ipoProductChangedParams.getPublicSalesQuantityIsNull()
            && l_ipoProductBeforeChangingParams.getPublicSalesQuantity() == l_ipoProductChangedParams.getPublicSalesQuantity() 
            //当社取扱数量
            && l_ipoProductBeforeChangingParams.getDealingQuantityIsNull() == l_ipoProductChangedParams.getDealingQuantityIsNull()
            && l_ipoProductBeforeChangingParams.getDealingQuantity() == l_ipoProductChangedParams.getDealingQuantity()     
            //主幹事証券会社名
            && compareToString(l_ipoProductBeforeChangingParams.getLeadManagingUnderwriter(), l_ipoProductChangedParams.getLeadManagingUnderwriter())
            //購入申込単位
            && l_ipoProductBeforeChangingParams.getLotSizeIsNull() == l_ipoProductChangedParams.getLotSizeIsNull()
            && l_ipoProductBeforeChangingParams.getLotSize() == l_ipoProductChangedParams.getLotSize()             
            //刻み
            && l_ipoProductBeforeChangingParams.getTickValueIsNull() == l_ipoProductChangedParams.getTickValueIsNull()
            && l_ipoProductBeforeChangingParams.getTickValue() == l_ipoProductChangedParams.getTickValue()         
            //表示用単位区分
            && compareToString(l_ipoProductBeforeChangingParams.getIpoUnitDiv(), l_ipoProductChangedParams.getIpoUnitDiv()) 
			//	2004/11/25 U00467 成行可能が訂正されたかどうかのチェック追加　@坂上@@SRA  START
            //成行可能
			&& compareToString(l_ipoProductBeforeChangingParams.getEnableMarketOrder(), l_ipoProductChangedParams.getEnableMarketOrder())
			//	2004/11/25 U00467 成行可能が訂正されたかどうかのチェック追加　@坂上@@SRA  END
            //ブックビルディング開始日時
			//	2004/11/25 U00466 日時分精度まで比較するように修正　@坂上@@SRA  START            
            && WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getBookbuildingStartDatetime(), 
                l_ipoProductChangedParams.getBookbuildingStartDatetime()) == 0
//			&& WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getBookbuildingStartDatetime(), 
//						l_ipoProductChangedParams.getBookbuildingStartDatetime()) == 0
   			//	2004/11/25 U00466 日時分精度まで比較するように修正　@坂上@@SRA  END                 
            //ブックビルディング終了日時
			//	2004/11/25 U00466 日時分精度まで比較するように修正　@坂上@@SRA  START  
            && WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(), 
                l_ipoProductChangedParams.getBookbuildingEndDatetime()) == 0
//			&& WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(), 
//			l_ipoProductChangedParams.getBookbuildingEndDatetime()) == 0
			//	2004/11/25 U00466 日時分精度まで比較するように修正　@坂上@@SRA  END     
            //公開価格決定日
            && WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getPublicPriceSettleDate(), 
                l_ipoProductChangedParams.getPublicPriceSettleDate()) == 0
            //公開価格
            && l_ipoProductBeforeChangingParams.getPublicPriceIsNull() == l_ipoProductChangedParams.getPublicPriceIsNull()
            && l_ipoProductBeforeChangingParams.getPublicPrice() == l_ipoProductChangedParams.getPublicPrice()         
            //公開価格（ディスカウント率）
            && l_ipoProductBeforeChangingParams.getPublicPriceDiscountRateIsNull() == l_ipoProductChangedParams.getPublicPriceDiscountRateIsNull()
            && l_ipoProductBeforeChangingParams.getPublicPriceDiscountRate() == l_ipoProductChangedParams.getPublicPriceDiscountRate() 
            //抽選日
            && WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getLotDate(), 
                l_ipoProductChangedParams.getLotDate()) == 0        
            //抽選日日数
            && l_ipoProductBeforeChangingParams.getLotDateCountIsNull() == l_ipoProductChangedParams.getLotDateCountIsNull()
            && l_ipoProductBeforeChangingParams.getLotDateCount() == l_ipoProductChangedParams.getLotDateCount()       
            //購入申込開始日時（当社設定）
			//	2004/11/25 U00466 日時分精度まで比較するように修正　@坂上@@SRA  START
            && WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getOfferStartDatetime(), 
                l_ipoProductChangedParams.getOfferStartDatetime()) == 0
//			&& WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferStartDatetime(), 
//						l_ipoProductChangedParams.getOfferStartDatetime()) == 0
			//	2004/11/25 U00466 日時分精度まで比較するように修正　@坂上@@SRA  END 
            //購入申込終了日時
			//	2004/11/25 U00466 日時分精度まで比較するように修正　@坂上@@SRA  START
            && WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getOfferEndDatetime(), 
                l_ipoProductChangedParams.getOfferEndDatetime()) == 0
//			&& WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferEndDatetime(), 
//						l_ipoProductChangedParams.getOfferEndDatetime()) == 0
			//	2004/11/25 U00466 日時分精度まで比較するように修正　@坂上@@SRA  END 
            //購入申込開始日日数（当社設定） 
            && l_ipoProductBeforeChangingParams.getOfferStartDateCountIsNull() == l_ipoProductChangedParams.getOfferStartDateCountIsNull()
            && l_ipoProductBeforeChangingParams.getOfferStartDateCount() == l_ipoProductChangedParams.getOfferStartDateCount()
            //購入申込終了日日数（当社設定）
            && l_ipoProductBeforeChangingParams.getOfferEndDateCountIsNull() == l_ipoProductChangedParams.getOfferEndDateCountIsNull()
            && l_ipoProductBeforeChangingParams.getOfferEndDateCount() == l_ipoProductChangedParams.getOfferEndDateCount()
            //購入申込開始日（目論見書記載）
            && WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferStartDateProspec(),
                l_ipoProductChangedParams.getOfferStartDateProspec()) == 0
            //購入申込終了日（目論見書記載）
            && WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferEndDateProspec(),
                l_ipoProductChangedParams.getOfferEndDateProspec()) == 0 
            //購入申込開始日日数（目論見書記載)
            && l_ipoProductBeforeChangingParams.getOfferStartDateCountProspecIsNull() == l_ipoProductChangedParams.getOfferStartDateCountProspecIsNull() 
            && l_ipoProductBeforeChangingParams.getOfferStartDateCountProspec() == l_ipoProductChangedParams.getOfferStartDateCountProspec()
		    //購入申込終了日日数（目論見書記載）　@
            && l_ipoProductBeforeChangingParams.getOfferEndDateCountProspecIsNull() == l_ipoProductChangedParams.getOfferEndDateCountProspecIsNull()
            && l_ipoProductBeforeChangingParams.getOfferEndDateCountProspec() == l_ipoProductChangedParams.getOfferEndDateCountProspec()
            //発行会社ロゴファ@イルＵＲＬ
            && compareToString(l_ipoProductBeforeChangingParams.getCompanyLogoUrl(),l_ipoProductChangedParams.getCompanyLogoUrl()) 
            //発行会社ウェブサイトＵＲＬ
            && compareToString(l_ipoProductBeforeChangingParams.getCompanyUrl(), l_ipoProductChangedParams.getCompanyUrl())
            //発行会社概要
            && compareToString(l_ipoProductBeforeChangingParams.getCompanyOutline(), l_ipoProductChangedParams.getCompanyOutline())
            //　@備考
            && compareToString(l_ipoProductBeforeChangingParams.getNote(), l_ipoProductChangedParams.getNote())
            // 目論見書閲覧区分 
            && compareToString(l_ipoProductBeforeChangingParams.getDocReadingDiv(), l_ipoProductChangedParams.getDocReadingDiv()))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00547,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //スケジュール決定チェック
        //２－１）　@未定への変更チェック
        if(l_ipoProductBeforeChanging.isScheduleDecision() && !l_ipoProductChanged.isScheduleDecision())
        {  
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00548,
                this.getClass().getName() + STR_METHOD_NAME);         
        }
        //２－２）　@ブックビルディング終了日時の訂正チェック
		//2004/11/17 U00433 日時分精度の比較に修正　@坂上@@SRA   START
        if (WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(),
            l_ipoProductChangedParams.getBookbuildingEndDatetime()) != 0            
            && l_ipoProductChanged.isBookbuildingEnd()
            && !l_ipoProductChanged.isScheduleDecision())
//		if (WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(),
//					l_ipoProductChangedParams.getBookbuildingEndDatetime()) != 0            
//					&& l_ipoProductChanged.isBookbuildingEnd()
//					&& !l_ipoProductChanged.isScheduleDecision())
		//2004/11/17 U00433 日時分精度の比較に修正　@坂上@@SRA   END
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00549,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //訂正可能期間チェック        
		//３－０）　@訂正可能期間が、（～ブックビルディング開始日時）の項目のチェック   
			  // 2004/11/16 U00423 BB開始より前の日に訂正が出来ない不具合対応　@坂上@@SRA  START         
			  if (!l_ipoProductBeforeChanging.isBookbuildingStart())
			  {
				  //「公開価格」がNullかどうかに関係なく訂正されていれば、例外をスローする
				  if(l_ipoProductBeforeChangingParams.getPublicPrice() != l_ipoProductChangedParams.getPublicPrice())
				  {
					// 2004/11/25 U00465 エラーメッセージが不適切のため対応　@坂上@@SRA  START
					  throw new WEB3BusinessLayerException(
						  WEB3ErrorCatalog.BUSINESS_ERROR_00583,
						  this.getClass().getName() + STR_METHOD_NAME);
//					  throw new WEB3BusinessLayerException(
//											  WEB3ErrorCatalog.BUSINESS_ERROR_00582,
//											  this.getClass().getName() + STR_METHOD_NAME);
					// 2004/11/25 U00465 エラーメッセージが不適切のため対応　@坂上@@SRA  END	  
				  }
				  //「公開価格（ディスカウント率）」がNullかどうかに関係なく訂正されていれば、例外をスローする
				  if(l_ipoProductBeforeChangingParams.getPublicPriceDiscountRate()
					  != l_ipoProductChangedParams.getPublicPriceDiscountRate())
				  {    
					  throw new WEB3BusinessLayerException(
						  WEB3ErrorCatalog.BUSINESS_ERROR_00584,
						  this.getClass().getName() + STR_METHOD_NAME); 
				  }  
			  }
			  // 2004/11/16 U00423 BB開始より前の日に訂正が出来ない不具合対応　@坂上@@SRA  END           
        
        //３－１）　@訂正可能期間が、ブックビルディング開始前までの項目のチェック
        if(l_ipoProductBeforeChanging.isBookbuildingStart())
        {
            
            //「IPO登録区分」が訂正されていれば、例外をスローする。
            if (!compareToString(l_ipoProductBeforeChangingParams.getIpoRegistDiv(), 
                l_ipoProductChangedParams.getIpoRegistDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00550,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //「IPO登録区分詳細」が訂正されていれば、例外をスローする。
            if(!compareToString(l_ipoProductBeforeChangingParams.getIpoRegistDetailDiv(), 
                l_ipoProductChangedParams.getIpoRegistDetailDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00551,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //「銘柄名」が訂正されていれば、例外をスローする。
            if(!compareToString(l_ipoProductBeforeChangingParams.getStandardName(), 
                l_ipoProductChangedParams.getStandardName()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00552,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //「公開市場」が訂正されていれば、例外をスローする。
            if(!compareToString(l_ipoProductBeforeChangingParams.getPublicMarket(),
                l_ipoProductChangedParams.getPublicMarket()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00553,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //「仮条件区分」が訂正されていれば、例外をスローする。
            if(!compareToString(l_ipoProductBeforeChangingParams.getProvisionalValueDiv(),
                l_ipoProductChangedParams.getProvisionalValueDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00554,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //「仮条件上限値」がnullに訂正されていれば、例外をスローする。
            if (l_ipoProductChangedParams.getProvisionalMaxValueIsNull() ||
                Double.isNaN(l_ipoProductChangedParams.getProvisionalMaxValue())) 
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00555,
                    this.getClass().getName() + STR_METHOD_NAME);
            }     
            //「仮条件下限値」がnullに訂正されていれば、例外をスローする。
            if (l_ipoProductChangedParams.getProvisionalMinValueIsNull()|| 
                Double.isNaN(l_ipoProductChangedParams.getProvisionalMinValue()))    
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00556,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //「仮条件提示日」が訂正されていれば、例外をスローする。
            if (WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getProvisionalValueOpenDate(),
                l_ipoProductChangedParams.getProvisionalValueOpenDate()) != 0)        
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00557,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //「購入申込単位」が訂正されていれば、例外をスローする。
            if((!l_ipoProductBeforeChangingParams.getLotSizeIsNull() || !l_ipoProductChangedParams.getLotSizeIsNull()) &&
                l_ipoProductBeforeChangingParams.getLotSize() != l_ipoProductChangedParams.getLotSize()) 
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00558,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //「刻み」が訂正されていれば、例外をスローする。
            if((!l_ipoProductBeforeChangingParams.getTickValueIsNull() || !l_ipoProductChangedParams.getTickValueIsNull()) &&
                l_ipoProductBeforeChangingParams.getTickValue() != l_ipoProductChangedParams.getTickValue())   
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00559,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //「表示用単位区分」が訂正されていれば、例外をスローする。
            if(!compareToString(l_ipoProductBeforeChangingParams.getIpoUnitDiv(),
                l_ipoProductChangedParams.getIpoUnitDiv()))  
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00561,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            // 2004/11/25 U00467 「成行可能」が訂正されているときのチェック追加　@坂上@@SRA START
//			//「成行可能」が訂正されていれば、例外をスローする。
			if(!compareToString(l_ipoProductBeforeChangingParams.getEnableMarketOrder(),
			    l_ipoProductChangedParams.getEnableMarketOrder()))  
			{
			   throw new WEB3BusinessLayerException(
			  	    WEB3ErrorCatalog.BUSINESS_ERROR_01234,
				    this.getClass().getName() + STR_METHOD_NAME);
			} 
			// 2004/11/25 U00467 「成行可能」が訂正されているときのチェック追加　@坂上@@SRA END       
            //「主幹事証券会社名」が訂正されていれば、例外をスローする。
            if(!compareToString(l_ipoProductBeforeChangingParams.getLeadManagingUnderwriter(),
                l_ipoProductChangedParams.getLeadManagingUnderwriter()))   
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00562,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //「ブックビルディング開始日時」が訂正されていれば、例外をスローする。
            //2004/11/17 U00433 日時分精度の比較に修正　@坂上@@SRA   START
			if (WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getBookbuildingStartDatetime(),
							l_ipoProductChangedParams.getBookbuildingStartDatetime()) != 0) 
//            if (WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getBookbuildingStartDatetime(),
//                l_ipoProductChangedParams.getBookbuildingStartDatetime()) != 0) 
			//2004/11/17 U00433 日時分精度の比較に修正　@坂上@@SRA   END
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00563,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
        }
        //３－２）　@訂正可能期間が、新規抽選終了までの項目のチェック
        if(l_ipoProductBeforeChanging.isNewLotteryEnd())
        {
            //「公開日」が訂正されていれば、例外をスローする。   
            if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getPublicOfferingDate(),
                l_ipoProductChangedParams.getPublicOfferingDate()) != 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00564,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //「仮条件上限値」が訂正されていれば、例外をスローする
            if((!l_ipoProductBeforeChangingParams.getProvisionalMaxValueIsNull() || !l_ipoProductChangedParams.getProvisionalMaxValueIsNull()) &&
                l_ipoProductBeforeChangingParams.getProvisionalMaxValue() != l_ipoProductChangedParams.getProvisionalMaxValue())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00565,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //「仮条件下限値」が訂正されていれば、例外をスローする
            if((!l_ipoProductBeforeChangingParams.getProvisionalMinValueIsNull() || !l_ipoProductChangedParams.getProvisionalMinValueIsNull()) && 
                l_ipoProductBeforeChangingParams.getProvisionalMinValue() != l_ipoProductChangedParams.getProvisionalMinValue())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00566,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //「ブックビルディング終了日時」が訂正されていれば、例外をスローする
			//2004/11/17 U00433 日時分精度の比較に修正　@坂上@@SRA   START            
            if(WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(),
                l_ipoProductChangedParams.getBookbuildingEndDatetime()) != 0)
//			if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getBookbuildingEndDatetime(),
//							l_ipoProductChangedParams.getBookbuildingEndDatetime()) != 0)
			//2004/11/17 U00433 日時分精度の比較に修正　@坂上@@SRA   END                
            {   
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00567,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //「公開価格決定日」が訂正されていれば、例外をスローする
            if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getPublicPriceSettleDate(),
                l_ipoProductChangedParams.getPublicPriceSettleDate()) != 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00568,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //「抽選日」が訂正されていれば、例外をスローする
            if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getLotDate(), 
                l_ipoProductChangedParams.getLotDate()) != 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00569,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
			// 2004/11/16 U00423 BB開始より前の日に訂正が出来ない不具合対応　@坂上@@SRA  START 
			//「公開価格」がnullに訂正されていれば、例外をスローする
			if(l_ipoProductChangedParams.getPublicPriceIsNull() || 
				Double.isNaN(l_ipoProductChangedParams.getPublicPrice()))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00582,
					this.getClass().getName() + STR_METHOD_NAME);
			}
			// 2004/11/16 U00423 BB開始より前の日に訂正が出来ない不具合対応　@坂上@@SRA  END			

        }
            //３－３）　@訂正可能期間が、公開日までの項目のチェック
        if(l_ipoProductBeforeChanging.isPublicEnd()) 
        {
            //「銘柄コード」が訂正されていれば、例外をスローする
            if(!compareToString(l_ipoProductBeforeChangingParams.getProductCode(),
                l_ipoProductChangedParams.getProductCode()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00570,
                    this.getClass().getName() + STR_METHOD_NAME);
            } 
            //「公募数量」が訂正されていれば、例外をスローする
            if((!l_ipoProductBeforeChangingParams.getPublicOfferingQuantityIsNull() || !l_ipoProductChangedParams.getPublicOfferingQuantityIsNull()) 
                && l_ipoProductBeforeChangingParams.getPublicOfferingQuantity() != l_ipoProductChangedParams.getPublicOfferingQuantity())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00571,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //「売出数量」が訂正されていれば、例外をスローする
            if((!l_ipoProductBeforeChangingParams.getPublicSalesQuantityIsNull() || !l_ipoProductChangedParams.getPublicSalesQuantityIsNull())
                && l_ipoProductBeforeChangingParams.getPublicSalesQuantity() != l_ipoProductChangedParams.getPublicSalesQuantity())
            {
          
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00572,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //「当社取扱数量」が訂正されていれば、例外をスローする
            if((!l_ipoProductBeforeChangingParams.getDealingQuantityIsNull() || !l_ipoProductChangedParams.getDealingQuantityIsNull())
                && l_ipoProductBeforeChangingParams.getDealingQuantity() != l_ipoProductChangedParams.getDealingQuantity())
            {
           
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00573,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //「発行会社ロゴファ@イルURL」が訂正されていれば、例外をスローする
            if(!compareToString(l_ipoProductBeforeChangingParams.getCompanyLogoUrl(),
                l_ipoProductChangedParams.getCompanyLogoUrl()))
            {
          
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00574,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //「発行会社ウェブサイトURL」が訂正されていれば、例外をスローする
            if(!compareToString(l_ipoProductBeforeChangingParams.getCompanyUrl(),
                l_ipoProductChangedParams.getCompanyUrl()))
            {
          
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00575,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //「発行会社概要」が訂正されていれば、例外をスローする
            if(!compareToString(l_ipoProductBeforeChangingParams.getCompanyOutline(),
                l_ipoProductChangedParams.getCompanyOutline()))
            {
            
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00576,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //「備考」が訂正されていれば、例外をスローする
            if(!compareToString(l_ipoProductBeforeChangingParams.getNote(),
                l_ipoProductChangedParams.getNote()))
            {
         
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00577,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }                        
        //３－４）　@訂正可能期間が、購入申込開始日（目論見書記載）までの項目のチェック
        if(l_ipoProductBeforeChanging.isOfferStartProspec()) 
        {
            //「購入申込開始日（目論見書記載）」が訂正されていれば、例外をスローする
            if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferStartDateProspec(),
                l_ipoProductChangedParams.getOfferStartDateProspec()) != 0) 
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00578,
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
        }                                                
        //３－５）　@訂正可能期間が、購入申込終了日（目論見書記載）までの項目のチェック 
        if(l_ipoProductBeforeChanging.isOfferEndProspec())
        {
            //「購入申込終了日（目論見書記載）」が訂正されていれば、例外をスローする
            if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferEndDateProspec(),
                l_ipoProductChangedParams.getOfferEndDateProspec()) != 0)
            {          
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00579,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        //３－６）　@訂正可能期間が、購入申込開始日時（当社設定）までの項目のチェック
        if(l_ipoProductBeforeChanging.isOfferStart())
        {
            //「購入申込開始日時（当社設定）」が訂正されていれば、例外をスローする
			//2004/11/17 U00433 日時分精度の比較に修正　@坂上@@SRA   START  
            if(WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getOfferStartDatetime(),
                l_ipoProductChangedParams.getOfferStartDatetime()) != 0)
//			if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferStartDatetime(),
//						   l_ipoProductChangedParams.getOfferStartDatetime()) != 0)
			//2004/11/17 U00433 日時分精度の比較に修正　@坂上@@SRA   END                  
            {            
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00580,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
			//2004/11/16 U00423 BB開始より前の日に訂正が出来ない不具合対応　@坂上@@SRA  START        	 
			//「公開価格」が訂正されていれば、例外をスローする
			if((!l_ipoProductBeforeChangingParams.getPublicPriceIsNull() || !l_ipoProductChangedParams.getPublicPriceIsNull())
			   && l_ipoProductBeforeChangingParams.getPublicPrice() != l_ipoProductChangedParams.getPublicPrice()) 
			{
			    throw new WEB3BusinessLayerException(
					 WEB3ErrorCatalog.BUSINESS_ERROR_00583,
					 this.getClass().getName() + STR_METHOD_NAME); 
			}
			//「公開価格（ディスカウント率）」が訂正されていれば、例外をスローする
			if((!l_ipoProductBeforeChangingParams.getPublicPriceDiscountRateIsNull()
				 || !l_ipoProductChangedParams.getPublicPriceDiscountRateIsNull())
				 && l_ipoProductBeforeChangingParams.getPublicPriceDiscountRate()
				 != l_ipoProductChangedParams.getPublicPriceDiscountRate())
			{    
				 throw new WEB3BusinessLayerException(
					 WEB3ErrorCatalog.BUSINESS_ERROR_00584,
					 this.getClass().getName() + STR_METHOD_NAME); 
			}
			// 2004/11/16 U00423 BB開始より前の日に訂正が出来ない不具合対応　@坂上@@SRA  END            
        }
        //３－７）　@訂正可能期間が、購入申込終了日時（当社設定）までの項目のチェック<BR>
        if(l_ipoProductBeforeChanging.isOfferEnd())
        { 
            //「購入申込終了日時（当社設定）」が訂正されていれば、例外をスローする
			//2004/11/17 U00433 日時分精度の比較に修正　@坂上@@SRA   START 
            if(WEB3DateUtility.compareToMinute(l_ipoProductBeforeChangingParams.getOfferEndDatetime(),
                l_ipoProductChangedParams.getOfferEndDatetime()) != 0)
//			if(WEB3DateUtility.compareToDay(l_ipoProductBeforeChangingParams.getOfferEndDatetime(),
//							l_ipoProductChangedParams.getOfferEndDatetime()) != 0)
			//2004/11/17 U00433 日時分精度の比較に修正　@坂上@@SRA   END
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00581,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        

        
        //３－８）　@訂正可能期間が、（ブックビルディング開始日時～新規抽選終了）の項目のチェック   
        // 2004/11/16 U00423 BB開始より前の日に訂正が出来ない不具合対応　@坂上@@SRA  START         
//		if (!l_ipoProductBeforeChanging.isBookbuildingStart() || l_ipoProductBeforeChanging.isNewLotteryEnd())
//		{ 
//			//「公開価格」がnullに訂正されていれば、例外をスローする
//			if(l_ipoProductChangedParams.getPublicPriceIsNull() || 
//				Double.isNaN(l_ipoProductChangedParams.getPublicPrice()))
//			{
//				throw new WEB3BusinessLayerException(
//					WEB3ErrorCatalog.BUSINESS_ERROR_00582,
//					this.getClass().getName() + STR_METHOD_NAME);
//			}		
//		}
		// 2004/11/16 U00423 BB開始より前の日に訂正が出来ない不具合対応　@坂上@@SRA  END	
		
		
        //３－９）　@訂正可能期間が、（ブックビルディング開始日時～購入申込開始日<BR>時（当社設定））の項目のチェック
		// 2004/11/16 U00423 BB開始より前の日に訂正が出来ない不具合対応　@坂上@@SRA  START        
//        if (!l_ipoProductBeforeChanging.isBookbuildingStart() 
//            || l_ipoProductBeforeChanging.isOfferStart())
//        {       	 
//            //「公開価格」が訂正されていれば、例外をスローする
//            if((!l_ipoProductBeforeChangingParams.getPublicPriceIsNull() || !l_ipoProductChangedParams.getPublicPriceIsNull())
//            && l_ipoProductBeforeChangingParams.getPublicPrice() != l_ipoProductChangedParams.getPublicPrice()) 
//            {
//                throw new WEB3BusinessLayerException(
//                    WEB3ErrorCatalog.BUSINESS_ERROR_00583,
//                    this.getClass().getName() + STR_METHOD_NAME); 
//            }
//            //「公開価格（ディスカウント率）」が訂正されていれば、例外をスローする
//            if((!l_ipoProductBeforeChangingParams.getPublicPriceDiscountRateIsNull()
//                || !l_ipoProductChangedParams.getPublicPriceDiscountRateIsNull())
//                && l_ipoProductBeforeChangingParams.getPublicPriceDiscountRate()
//                != l_ipoProductChangedParams.getPublicPriceDiscountRate())
//            {    
//                throw new WEB3BusinessLayerException(
//                    WEB3ErrorCatalog.BUSINESS_ERROR_00584,
//                    this.getClass().getName() + STR_METHOD_NAME); 
//            }
//            
//        }
		// 2004/11/16 U00423 BB開始より前の日に訂正が出来ない不具合対応　@坂上@@SRA  END                
        log.exiting(STR_METHOD_NAME);
    }
   
    /**
     * (updateIPO申告)<BR>
     * シーケンス図<BR>
     * 「IPO（管理者・銘柄訂正）updateIPO申告」参照。
     * @@param l_lngIpoOrderId - IPO申告ＩＤ
     * @@param l_datBookbuildingEndDate - (ブックビルディング終了日時)<BR>
     * ブックビルディング終了日時（訂正後）
     * @@param l_strLastUpdater - 更新者コード
     * @@roseuid 40CE903103D2
     */
    protected void updateIpoOrder(long l_lngIpoOrderId, Date l_datBookbuildingEndDate, String l_strLastUpdater) 
        throws NotInstalledException, NotFoundException
    {   
        String STR_METHOD_NAME = " updateIpoOrder(long, Date, String)";
        log.entering(STR_METHOD_NAME);
        
        //getIPO申告(long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl =
            (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_ipoOrderManagerImpl.getOrderUnit(l_lngIpoOrderId);
                
        IpoOrderRow l_ipoOrdertRow = (IpoOrderRow)(l_ipoOrder.getDataSourceObject());
        IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrdertRow);
                
        //getブックビルディング申告履歴( )
        WEB3IpoBookbuildingOrderActionImpl[] l_ipoBookbuildingOrderAction = null; 
        l_ipoBookbuildingOrderAction = new WEB3IpoBookbuildingOrderActionImpl[l_ipoOrder.getOrderActions().length];
        log.debug("l_ipoOrder.getOrderActions().length = " + l_ipoOrder.getOrderActions().length);
        
        for (int i = 0; i < l_ipoOrder.getOrderActions().length; i++)
        {
            l_ipoBookbuildingOrderAction[i] = (WEB3IpoBookbuildingOrderActionImpl)l_ipoOrder.getOrderActions()[i];
            log.debug("l_ipoBookbuildingOrderAction[i].getDataSourceObject(); = " + l_ipoBookbuildingOrderAction[i].getDataSourceObject()); 
        }
        
        
        //(*1) ブックビルディング終了日時以降に新規申告されたデータの場合
        int l_intLength = l_ipoBookbuildingOrderAction.length;
        log.debug("l_intLength = " + l_intLength);
        // 2004/11/19 U00451 日時分精度の比較に修正　@坂上@@SRA　@START
		if(WEB3DateUtility.compareToMinute(l_ipoOrderParams.getCreatedTimestamp(), l_datBookbuildingEndDate) >= 0)
//        if(WEB3DateUtility.compareToDay(l_ipoOrderParams.getCreatedTimestamp(), l_datBookbuildingEndDate) >= 0)
		// 2004/11/19 U00451 日時分精度の比較に修正　@坂上@@SRA　@END        
        {                     
            log.debug("ブックビルディング終了日時以降に新規申告されたデータの場合");
            //updateIPO申告Fromブックビルディング履歴(IPO申告, ブックビルディング申告履歴, OrderOpenStatusEnum, String)
            this.updateIpoOrderFromBookbuildingAction
                (l_ipoOrder, l_ipoBookbuildingOrderAction[0], OrderOpenStatusEnum.CLOSED, l_strLastUpdater);
        }        
        //(*2) ブックビルディング終了日時より前に新規申告されたデータの場合
        else
        {           
            log.debug("ブックビルディング終了日時より前に新規申告されたデータの場合");
            int l_intTemp = 0;
            //2004/11/24 U00450 IPO_ORDERテーブルが更新されない　@坂上@@SRA  START
            for (int i = l_intLength - 1; i >= 0 ; i--)
//			for (int i = l_intLength - 1; i == 0 ; i--)
			//2004/11/24 U00450 IPO_ORDERテーブルが更新されない　@坂上@@SRA  END            
            {
                log.debug("i = " + i);
                Date l_datOrderActionTimestamp = l_ipoBookbuildingOrderAction[i].getOrderActionTimestamp();
				// 2004/11/19 U00451 日時分精度の比較に修正　@坂上@@SRA　@START
				if(WEB3DateUtility.compareToMinute(l_datOrderActionTimestamp,
									l_datBookbuildingEndDate) < 0)                
//                if(WEB3DateUtility.compareToDay(l_datOrderActionTimestamp,
//                    l_datBookbuildingEndDate) < 0)
				// 2004/11/19 U00451 日時分精度の比較に修正　@坂上@@SRA　@END                    
                {
                    l_intTemp = i;
                    log.debug("l_intTemp = " + l_intTemp);
                    break;
                }
            }

            // updateIPO申告Fromブックビルディング履歴(IPO申告, ブックビルディング申告履歴, OrderOpenStatusEnum, String)
            IpoBookbuildingOrderActionRow l_ipoBookbuildingOrderActionRow = 
                (IpoBookbuildingOrderActionRow)l_ipoBookbuildingOrderAction[l_intTemp].getDataSourceObject();
            log.debug("l_ipoBookbuildingOrderActionRow.getCreatedTimestamp() = " + l_ipoBookbuildingOrderActionRow.getCreatedTimestamp());
            this.updateIpoOrderFromBookbuildingAction
                (l_ipoOrder,l_ipoBookbuildingOrderAction[l_intTemp],l_ipoBookbuildingOrderActionRow.getOrderOpenStatus(),l_strLastUpdater);
        }
              
        //(*3) ブックビルディング申告履歴各要素ごとのLOOP
        //updateブックビルディング申告履歴(ブックビルディング申告履歴, Date, String)  
        for(int i = 0; i <= l_intLength - 1; i++)
        {
            //更新用の行オブジェクトを生成する。
            //((WEB3IpoBookbuildingOrderActionImpl)l_ipoBookbuildingOrderAction[i]).createForUpdateParams();
            log.debug("updateブックビルディング申告履歴");
            this.updateBookbuildingOrderAction
                (l_ipoBookbuildingOrderAction[i], l_datBookbuildingEndDate, l_strLastUpdater);   
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (updateIPO申告Fromブックビルディング履歴)<BR>
     * ブックビルディング申告履歴の内容より、IPO申告行に値をセットし更新する。<BR>
     * <BR>
     * １）　@行オブジェクト取得<BR>
     * 　@－引数,IPO申告.getDataSourceObject()にて、IPO申告行オブジェクトを取得する。<BR>
     * 　@－引数,ブックビルディング申告履歴.getDataSourceObject()にて、ブックビルディ<BR>ング申告履歴行オブジェクトを取得する。
     * <BR>
     * <BR>
     * ２）　@変更判定<BR>
     * 　@（IPO申告行.更新日時 == ブックビルディング申告履歴行.更新日時） &&<BR>
     * 　@（IPO申告行.ブックビルディング有効状態 == 引数.IPO申告有効状態）の場合<BR>
     * 　@変更なしと判定し、処理を終了（return）する。<BR>
     * <BR>
     * ３）　@変更値セット<BR>
     * 　@IPO申告行に変更値を以下の通りセットする。<BR>
     * <BR>
     * 　@[変更内容]<BR>
     * 　@IPO申告行.数量 = ブックビルディング申告履歴行.数量<BR>
     * 　@IPO申告行.指値 = ブックビルディング申告履歴行.指値<BR>
     * 　@IPO申告行.ブックビルディング申告状態 = ブックビルディング申告履歴行.ブックビ<BR>ルディング申告状態<BR>
     * 　@IPO申告行.IPO申告有効状態 = 引数.IPO申告有効状態<BR>
     * 　@IPO申告行.計算単価 = ブックビルディング申告履歴行.計算単価<BR>
     * 　@IPO申告行.基準値（時価） = ブックビルディング申告履歴行.基準値（時価）<BR>
     * 　@IPO申告行.申告相当額 = ブックビルディング申告履歴行.申告相当額<BR>
     *   IPO申告行.新規申告日時 = ブックビルディング申告履歴行.新規申告日時(20040916_仕様変更)<BR>
     * 　@IPO申告行.取引者ID = ブックビルディング申告履歴行.取引者ID <BR>
     * 　@IPO申告行.注文経路区分 = ブックビルディング申告履歴行.注文経路区分<BR> 
     * 　@IPO申告行.更新者コード = 引数の更新者コード<BR>
     *   IPO申告行.更新日時 = 現在日時<BR>
     * <BR>
     * ４）　@DB更新<BR>
     * 　@QueryProcessor.doUpdateQuery() にて、PO申告行をDB更新（Update）する。<BR>
     * @@param l_ipoOrder - (IPO申告)<BR>
     * IPO申告オブジェクト
     * @@param l_bookbuildingOrderAction - (ブックビルディング申告履歴)<BR>
     * ブックビルディング申告履歴オブジェクト
     * @@param l_ipoOrderOpenStatus - IPO申告有効状態
     * @@param l_strLastUpdater - 更新者コード
     * @@roseuid 40CEA0120038
     */
    protected void updateIpoOrderFromBookbuildingAction(WEB3IpoOrderImpl l_ipoOrder, WEB3IpoBookbuildingOrderActionImpl l_bookbuildingOrderAction, 
        OrderOpenStatusEnum l_ipoOrderOpenStatus, String l_strLastUpdater)
    {
        
        String STR_METHOD_NAME = " updateIpoOrderFromBookbuildingAction(WEB3IpoOrderImpl,WEB3IpoBookbuildingOrderAction,OrderOpenStatusEnum,String)";
        log.entering(STR_METHOD_NAME);
        
        //行オブジェクト取得
        IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
        IpoBookbuildingOrderActionRow l_ipoBookbuildingOrderActionRow = (IpoBookbuildingOrderActionRow)l_bookbuildingOrderAction.getDataSourceObject();
                
        //　@変更判定
		// 2004/11/24 U00457 残案件０１６の取込みで変更判定ロジックを削除　@坂上@@SRA START        
		// 2004/11/19 U00451 日時分精度の比較に修正　@坂上@@SRA　@START        
//        if (WEB3DateUtility.compareToMinute(l_ipoOrderRow.getLastUpdatedTimestamp(), 
//            l_ipoBookbuildingOrderActionRow.getLastUpdatedTimestamp()) == 0 &&
//            l_ipoOrderOpenStatus.equals(l_ipoOrderRow.getOrderOpenStatus()))
////		if (WEB3DateUtility.compareToDay(l_ipoOrderRow.getLastUpdatedTimestamp(), 
////					l_ipoBookbuildingOrderActionRow.getLastUpdatedTimestamp()) == 0 &&
////					l_ipoOrderOpenStatus.equals(l_ipoOrderRow.getOrderOpenStatus()))
//		// 2004/11/19 U00451 日時分精度の比較に修正　@坂上@@SRA　@END            
//        {
//            log.exiting(STR_METHOD_NAME);
//            log.debug("return");
//            return;        
//        }
//		// 2004/11/24 U00457 残案件０１６の取込みで変更判定ロジックを削除　@坂上@@SRA END
        IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrderRow);

        //変更値セット
        l_ipoOrderParams.setQuantity(l_ipoBookbuildingOrderActionRow.getQuantity());
        l_ipoOrderParams.setLimitPrice(l_ipoBookbuildingOrderActionRow.getLimitPrice()); 
        l_ipoOrderParams.setOrderStatus(l_ipoBookbuildingOrderActionRow.getOrderStatus());
        l_ipoOrderParams.setOrderOpenStatus(l_ipoOrderOpenStatus);
        l_ipoOrderParams.setPrice(l_ipoBookbuildingOrderActionRow.getPrice());
        if(!l_ipoBookbuildingOrderActionRow.getCurrentPriceIsNull())
        {
            l_ipoOrderParams.setCurrentPrice(l_ipoBookbuildingOrderActionRow.getCurrentPrice());
        }
        else
        {
            l_ipoOrderParams.setCurrentPrice(null);
        }
        l_ipoOrderParams.setBookbuildingPrice(l_ipoBookbuildingOrderActionRow.getBookbuildingPrice());
        l_ipoOrderParams.setBookbuildingCreatedTimestamp(l_ipoBookbuildingOrderActionRow.getBookbuildingCreatedTimestamp()); 
        l_ipoOrderParams.setTraderId(l_ipoBookbuildingOrderActionRow.getTraderId());
        l_ipoOrderParams.setOrderRootDiv(l_ipoBookbuildingOrderActionRow.getOrderRootDiv());       
        l_ipoOrderParams.setLastUpdater(l_strLastUpdater);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        l_ipoOrderParams.setLastUpdatedTimestamp(l_finApp.getTradingSystem().getSystemTimestamp());
        
        
        //DB更新
        try
        {
            Processors.getDefaultProcessor().doUpdateQuery(l_ipoOrderParams);
        }
        catch (DataFindException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        } 
        catch (DataNetworkException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (updateIPO申告Fromブックビルディング履歴)<BR>
     * ブックビルディング申告履歴を有効または、無効に更新する。<BR>
     * <BR>
     * １）　@行オブジェクト取得<BR>
     * 　@引数,ブックビルディング申告履歴.getDataSourceObject()にて<BR>
     * ブックビルディング申告履歴行オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@削除フラグ判定<BR>
     * 　@（ブックビルディング申告履歴行.作成日時　@< 引数.ブックビルディング終了日時）の場合、<BR>
     * 　@　@－ブックビルディング申告履歴行.削除フラグ == ”削除”（BooleanEnum.True）であれば、<BR>
     * 　@　@　@以下の通り、ブックビルディング申告履歴行に値をセットしてDBに更新（Update）を行う。<BR>
     * <BR>
     * 　@　@　@[セット内容]<BR>
     * 　@　@　@削除フラグ = ”削除でない”（BooleanEnum.False）<BR>
     * 　@　@　@更新者コード = 引数.更新者コード<BR>
     * 　@　@　@更新日時 = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * 　@（ブックビルディング申告履歴行.作成日時　@>=  引数.ブックビルディング終了日<BR>時）の場合、<BR>
     * 　@　@－ブックビルディング申告履歴行.削除フラグ == ”削除でない”（<BR>BooleanEnum.False）であれば、<BR>
     * 　@　@　@以下の通り、ブックビルディング申告履歴行に値をセットしてDBに更新（<BR>Update）を行う。<BR>
     * <BR>
     * 　@　@　@[セット内容]<BR>
     * 　@　@　@削除フラグ = ”削除”（BooleanEnum.True）<BR>
     * 　@　@　@更新者コード = 引数.更新者コード<BR>
     * 　@　@　@更新日時 = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * 　@
     * @@param l_bookbuildingOrderAction - (ブックビルディング申告履歴)<BR>
     * ブックビルディング申告履歴オブジェクト
     * @@param l_datBookbuildingEndDate - ブックビルディング終了日時
     * 
     * @@param l_strLastUpdater - 更新者コード
     * @@roseuid 40CEADE802A9
     */
    protected void updateBookbuildingOrderAction(WEB3IpoBookbuildingOrderActionImpl l_bookbuildingOrderAction, Date l_datBookbuildingEndDate, String l_strLastUpdater) 
    {
        String STR_METHOD_NAME = " updateBookbuildingOrderAction(WEB3IpoBookbuildingOrderAction,Date,String)";
        log.entering(STR_METHOD_NAME);
        
        //行オブジェクト取得
        IpoBookbuildingOrderActionRow l_bookbuildingOrderActionRow = 
            ((IpoBookbuildingOrderActionRow)l_bookbuildingOrderAction.getDataSourceObject());
        
        //削除フラグ判定
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Timestamp l_tsSystemTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();       
            
        IpoBookbuildingOrderActionParams l_ipobookbuildingOrderActionParams 
            = new IpoBookbuildingOrderActionParams(l_bookbuildingOrderActionRow);
                        
        try
        {
            QueryProcessor l_queryProcessor;

            l_queryProcessor = Processors.getDefaultProcessor();
			// 2004/11/19 U00451 日時分精度の比較に修正　@坂上@@SRA　@START             
            if(WEB3DateUtility.compareToMinute(l_bookbuildingOrderActionRow.getCreatedTimestamp(), l_datBookbuildingEndDate) < 0
                 && (BooleanEnum.TRUE).equals(l_bookbuildingOrderActionRow.getDeleteFlag()))
//			if(WEB3DateUtility.compareToDay(l_bookbuildingOrderActionRow.getCreatedTimestamp(), l_datBookbuildingEndDate) < 0
//							 && (BooleanEnum.TRUE).equals(l_bookbuildingOrderActionRow.getDeleteFlag()))
			// 2004/11/19 U00451 日時分精度の比較に修正　@坂上@@SRA　@END                  
            {
                log.debug("削除フラグ判定1");
                l_ipobookbuildingOrderActionParams.setDeleteFlag(BooleanEnum.FALSE);
                l_ipobookbuildingOrderActionParams.setLastUpdater(l_strLastUpdater);
				// 2004/11/19 U00453 作成日時を更新しているため更新日時を更新に修正　@坂上@@SRA　@START
				l_ipobookbuildingOrderActionParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
//                l_ipobookbuildingOrderActionParams.setCreatedTimestamp(l_tsSystemTimestamp);
				// 2004/11/19 U00453 作成日時を更新しているため更新日時を更新に修正　@坂上@@SRA　@END
         
                l_queryProcessor.doUpdateQuery(l_ipobookbuildingOrderActionParams);
            }
			// 2004/11/19 U00451 日時分精度の比較に修正　@坂上@@SRA　@START          
            if(WEB3DateUtility.compareToMinute(l_bookbuildingOrderActionRow.getCreatedTimestamp(), l_datBookbuildingEndDate) >= 0
                && (BooleanEnum.FALSE).equals(l_bookbuildingOrderActionRow.getDeleteFlag()))
//			if(WEB3DateUtility.compareToDay(l_bookbuildingOrderActionRow.getCreatedTimestamp(), l_datBookbuildingEndDate) >= 0
//							&& (BooleanEnum.FALSE).equals(l_bookbuildingOrderActionRow.getDeleteFlag()))
			// 2004/11/19 U00451 日時分精度の比較に修正　@坂上@@SRA　@END
            {                   
                log.debug("削除フラグ判定2");
                l_ipobookbuildingOrderActionParams.setDeleteFlag(BooleanEnum.TRUE);
                l_ipobookbuildingOrderActionParams.setLastUpdater(l_strLastUpdater);
				// 2004/11/19 U00453 作成日時を更新しているため更新日時を更新に修正　@坂上@@SRA　@START
				l_ipobookbuildingOrderActionParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
//				  l_ipobookbuildingOrderActionParams.setCreatedTimestamp(l_tsSystemTimestamp);
				// 2004/11/19 U00453 作成日時を更新しているため更新日時を更新に修正　@坂上@@SRA　@END
                
                l_queryProcessor.doUpdateQuery(l_ipobookbuildingOrderActionParams);
            }
        }
 
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }
        catch(DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }   
        log.exiting(STR_METHOD_NAME);          
    }
    
    /**
     * compareToString<BR>
     * String類型の比較<BR>
     * <BR>
     * @@param l_str1 l_str2<BR>
     * @@return boolean
     * @@roseuid 40C945770078
     */    
    private boolean compareToString(String l_str1, String l_str2)
    {
        if (l_str1 == null && l_str2 == null)
        {
            return true;
        }
        else if (l_str1 == null || l_str2 == null)
        {
            return false;
        }
        else
        {
            return l_str1.equals(l_str2);
        }
    }
    
}
@
