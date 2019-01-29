head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoProductRegistrationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 管理者IPO銘柄登録サービスImpl(WEB3AdminIpoProductRegistrationServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2004/08/16 李頴淵 新規作成
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>043
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>060
Revesion History : 2010/09/21 趙天月 (中訊) 実装の問題 No.017
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PublicMarketDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputResponse;
import webbroker3.ipo.message.WEB3IPOProductInfo;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductRegistrationService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

/**
 * (管理者IPO銘柄登録サービスImpl)<BR>
 * 管理者IPO銘柄登録サービス実装クラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIpoProductRegistrationServiceImpl implements WEB3AdminIpoProductRegistrationService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoProductRegistrationServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE401E7
     */
    public WEB3AdminIpoProductRegistrationServiceImpl() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 管理者IPO銘柄新規登録入力画面表示データ作成処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・銘柄登録）get入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄新規登録入力リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIpoProductRegistrationInputResponse
     * @@roseuid 40C3D1A6018E
     */
    protected WEB3AdminIPOProductRegistrationInputResponse getInputScreen(WEB3AdminIPOProductRegistrationInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminIpoProductRegistrationInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductRegistrationInputResponse l_ipoProductRegistrationInputResponse;
        //1.getInstanceFromログイン情報
        log.debug("getInstanceFromログイン情報 before");
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報");
            
        //2.validate権限
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_REG,true);
        log.debug("validate権限 end");
            
        //3. createResponse
        l_ipoProductRegistrationInputResponse = (WEB3AdminIPOProductRegistrationInputResponse)l_request.createResponse();
            
        //4.getSystemTimestamp
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        log.debug("getSystemTimestamp end");
            
        //5.プロパティセット
        String[] l_strPublicMarketCodesTemp;
        l_strPublicMarketCodesTemp = new String[17];
        l_strPublicMarketCodesTemp[0] = WEB3PublicMarketDef.TOKYO_STOCK_EXCHANGE;      //東証
        l_strPublicMarketCodesTemp[1] = WEB3PublicMarketDef.TSE_NO_ONE_DEPT;           //東証一部
        l_strPublicMarketCodesTemp[2] = WEB3PublicMarketDef.TSE_NO_TWO_DEPT;           //東証二部
        l_strPublicMarketCodesTemp[3] = WEB3PublicMarketDef.MOTHERS;                   //マザーズ　@
        l_strPublicMarketCodesTemp[4] = WEB3PublicMarketDef.OSAKA_SECURITIES_EXCHANGE; //大証
        l_strPublicMarketCodesTemp[5] = WEB3PublicMarketDef.OSE_NO_ONE_DEPT;           //大証一部
        l_strPublicMarketCodesTemp[6] = WEB3PublicMarketDef.OSE_NO_TWO_DEPT;           //大証二部
        l_strPublicMarketCodesTemp[7] = WEB3PublicMarketDef.NAGOYA_STOCK_EXCHANGE;     //名証
        l_strPublicMarketCodesTemp[8] = WEB3PublicMarketDef.NSE_NO_ONE_DEPT;           //名証一部
        l_strPublicMarketCodesTemp[9] = WEB3PublicMarketDef.NSE_NO_TWO_DEPT;           //名証二部
        l_strPublicMarketCodesTemp[10] = WEB3PublicMarketDef.CENTREX;                  //セントレックス
        l_strPublicMarketCodesTemp[11] = WEB3PublicMarketDef.FUKUOKA_STOCK_EXCHANGE;   //福証
        l_strPublicMarketCodesTemp[12] = WEB3PublicMarketDef.Q_BOARD;                  //Q-Board
        l_strPublicMarketCodesTemp[13] = WEB3PublicMarketDef.SAPPORO_STOCK_EXCHANGE;   //札証
        l_strPublicMarketCodesTemp[14] = WEB3PublicMarketDef.AMBITIOUS;                //アンビシャス
        l_strPublicMarketCodesTemp[15] = WEB3PublicMarketDef.JASDAQ_STANDARD;          //JASDAQ（スタンダード）
        l_strPublicMarketCodesTemp[16] = WEB3PublicMarketDef.JASDAQ_CLOSE;             //JASDAQ（グロース）
        
        l_ipoProductRegistrationInputResponse.publicOfferingMarketList = l_strPublicMarketCodesTemp;
                    
        l_ipoProductRegistrationInputResponse.currentDate = l_tsCurrentTime;
            
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductRegistrationInputResponse; 

    }
    
    /**
     * (validate銘柄登録)<BR>
     * 管理者IPO銘柄新規登録確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・銘柄登録）validate銘柄登録」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄新規登録確認リクエストデータオブジェクト
     * 
     * @@return webbroker3.ipo.message.WEB3AdminIpoProductRegistrationConfirmResponse
     * @@roseuid 40C3CF8603DF
     */
    protected WEB3AdminIPOProductRegistrationConfirmResponse validateProductRegistration(WEB3AdminIPOProductRegistrationConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateProductRegistration(WEB3AdminIpoProductRegistrationConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.validate
        l_request.validate();
        
        //2.getInstanceFromログイン情報
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報");
        
        //3.validate権限
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_REG,true);
        log.debug("validate権限");
        
        //4.createNewIPO銘柄(IPO銘柄情報, 管理者)
        WEB3IpoProductImpl l_ipoProductImpl = this.createNewIpoProduct(l_request.ipoProductInfo,l_administrator);
        log.debug("l_ipoProductImpl.getDataSourceObject() = " + l_ipoProductImpl.getDataSourceObject());
        
        //5.validateスケジュール
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        log.debug("getSystemTimestamp");
        
        l_ipoProductImpl.validateSchedule(l_tsCurrentTime);
        log.debug("validateスケジュール");
                
        //6.validate株式銘柄()
        l_ipoProductImpl.validateProduct();
        log.debug("validate株式銘柄");
                
        //7.get証券会社コード
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);
        log.debug("get証券会社コード");
        
        //8.validate期間重複登録
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            
        log.debug("l_request.ipoProductInfo.productCode = " + l_request.ipoProductInfo.productCode);
        log.debug("l_request.ipoProductInfo.bookbuildingStartDatetime = " + l_request.ipoProductInfo.bookBuildingStartDate);
        log.debug("l_request.ipoProductInfo.publicOfferingDate.startDatetime = " + l_request.ipoProductInfo.publicOfferingDate.startDate);
        l_ipoProductManagerImpl.validateDuplicateTerm
            (l_strInstitutionCode, l_request.ipoProductInfo.productCode, 
            l_request.ipoProductInfo.bookBuildingStartDate, 
            l_request.ipoProductInfo.publicOfferingDate.startDate, 
            0);    
        log.debug("validate期間重複登録");
        
        //9.set銘柄名
        l_ipoProductImpl.setStandardName();
        
        //10.createResponse
        WEB3AdminIPOProductRegistrationConfirmResponse l_ipoProductRegistrationConfirmResponse = (WEB3AdminIPOProductRegistrationConfirmResponse)l_request.createResponse();
        
        //11.get銘柄名
        String l_strProductName = l_ipoProductImpl.getStandardName();
        
        //12.プロパティセット
        l_ipoProductRegistrationConfirmResponse.productName = l_strProductName;
        
        log.debug("l_strProductName = " + l_strProductName);
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductRegistrationConfirmResponse;
    }
    
    /**
     * (submit銘柄登録)<BR>
     * 管理者IPO銘柄新規登録完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・銘柄登録）submit銘柄登録」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄新規登録完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIpoProductRegistrationCompleteResponse
     * @@roseuid 40C3CF8D0324
     */
    protected WEB3AdminIPOProductRegistrationCompleteResponse submitProductRegistration(WEB3AdminIPOProductRegistrationCompleteRequest l_request)
        throws WEB3BaseException   
    {
        final String STR_METHOD_NAME = " submitProductRegistration(WEB3AdminIpoProductRegistrationCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.validate
        l_request.validate();
        
        //2.getInstanceFromログイン情報
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //3.validate権限
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_REG,true);
        
        //4.validate取引パスワード
        l_administrator.validateTradingPassword(l_request.password);
        
        //5.createNewIPO銘柄
        WEB3IpoProductImpl l_ipoProductImpl = this.createNewIpoProduct(l_request.ipoProductInfo,l_administrator);
        log.debug("createNewIPO銘柄");
        
        //6.validateスケジュール
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        
        l_ipoProductImpl.validateSchedule(l_tsCurrentTime);
        log.debug("validateスケジュール");
        
        //7.validate株式銘柄()
        l_ipoProductImpl.validateProduct();
        log.debug("validate株式銘柄");
        
        //8.get証券会社コード
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //9.validate期間重複登録
        WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
            (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        l_ipoProductManagerImpl.validateDuplicateTerm
            (l_strInstitutionCode,l_request.ipoProductInfo.productCode,l_request.ipoProductInfo.bookBuildingStartDate,l_request.ipoProductInfo.publicOfferingDate.startDate,0);    
        log.debug("validate期間重複登録");
        
        //10.set銘柄名
        l_ipoProductImpl.setStandardName();
        
        //11.saveNew銘柄
        log.debug("l_ipoProductImpl.getDataSourceObject" + l_ipoProductImpl.getDataSourceObject());
        l_ipoProductManagerImpl.saveNewProduct(l_ipoProductImpl);
        log.debug("saveNewProduct");
        
        //12.createResponse
        WEB3AdminIPOProductRegistrationCompleteResponse l_ipoProductRegistrationCompleteResponse = null;
        l_ipoProductRegistrationCompleteResponse = (WEB3AdminIPOProductRegistrationCompleteResponse)l_request.createResponse();
                
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductRegistrationCompleteResponse;
    }
    
    /**
     * 管理者IPO銘柄登録処理を実施する。<BR>
     * <BR>
     * １）　@業務日時設定<BR>
     * 　@取引時間管理.setBusinessTimestamp()をコールする。<BR>
     * <BR>
     * ２）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者IPO銘柄新規登録入力リクエストの場合<BR>
     * 　@－get入力画面()をコールする。<BR>
     * ○ 引数のリクエストデータが、管理者IPO銘柄新規登録確認リクエストの場合<BR>
     * 　@－validate銘柄登録()をコールする。<BR>
     * ○ 引数のリクエストデータが、管理者IPO銘柄新規登録完了リクエストの場合<BR>
     * 　@－submit銘柄登録()をコールする。<BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40C3CF9402B7
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
        
        if (l_request instanceof WEB3AdminIPOProductRegistrationInputRequest)
        {
            log.debug("WEB3AdminIpoProductRegistrationInputRequest");
            WEB3GenResponse l_response = getInputScreen(
                (WEB3AdminIPOProductRegistrationInputRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminIPOProductRegistrationConfirmRequest)
        {
            log.debug("WEB3AdminIpoProductRegistrationConfirmRequest");
            WEB3AdminIPOProductRegistrationConfirmResponse l_ipoProductRegistrationConfirmResponse = validateProductRegistration(
                (WEB3AdminIPOProductRegistrationConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductRegistrationConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminIPOProductRegistrationCompleteRequest)
        {
            log.debug("WEB3AdminIpoProductRegistrationCompleteRequest");
            WEB3AdminIPOProductRegistrationCompleteResponse l_ipoProductRegistrationCompleteResponse = submitProductRegistration(
                (WEB3AdminIPOProductRegistrationCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_ipoProductRegistrationCompleteResponse;
        }
        else
        {
            // Timestampリセット
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (createNewIPO銘柄)<BR>
     * IPO銘柄情報より、IPO銘柄オブジェクトを作成する。<BR>
     * <BR>
     * 初期化したIPO銘柄行オブジェクトを取得する。<BR>
     * <BR>
     * １）　@IPO銘柄行を生成する。<BR>
     * 　@IPO銘柄Paramsオブジェクトを生成する。<BR>
     * 　@※IPO銘柄ParamsクラスはDDLより自動生成。<BR>
     * <BR>
     * ２）　@IPO銘柄行の各項目に初期値をセットする。<BR>
     * 　@－日付型（Date）、文字列型（String）の初期値 ： null<BR>
     * 　@－数値型（Double,double,Long,long,Integer,int）の初期値 ： 0<BR>
     * <BR>
     * ３）　@入力内容よりIPO銘柄情報を作成する。<BR>
     * 　@IPO銘柄情報作成サービス.createIPO銘柄()をコールする。<BR>
     * <BR>
     * 　@[createIPO銘柄()に指定する引数]<BR>
     * 　@IPO銘柄行：　@（１）、２）で生成したオブジェクト）<BR>
     * 　@IPO銘柄情報入力メッセージ：　@引数.IPO銘柄情報入力メッセージ<BR>
     * 　@管理者：　@引数.管理者オブジェクト<BR>
     * @@param l_ipoProductInfoInputMessage - IPO銘柄情報入力メッセージ
     * @@param l_manager - (管理者)<BR>
     * 管理者オブジェクト
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40C5377E0211
     */
    protected WEB3IpoProductImpl createNewIpoProduct(WEB3IPOProductInfo l_ipoProductInfoInputMessage, WEB3Administrator l_manager) 
    {
        final String STR_METHOD_NAME = " createNewIpoProduct(WEB3IpoProductInfo,WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        //IPO銘柄行を生成する        
        IpoProductParams l_ipoProductParams = new IpoProductParams();
        
        //IPO銘柄行の各項目に初期値をセットする
        l_ipoProductParams.setIpoProductId(0);          //IPO銘柄ＩＤ
        l_ipoProductParams.setInstitutionCode(null);    //証券会社コード
        l_ipoProductParams.setProductCode(null);        //銘柄コード
        l_ipoProductParams.setStandardName(null);       //銘柄名
        l_ipoProductParams.setProductType(null);        //銘柄タイプ
        l_ipoProductParams.setIpoRegistDiv(null);      //IPO登録区分
        l_ipoProductParams.setIpoRegistDetailDiv(null); //IPO登録区分詳細
        l_ipoProductParams.setPublicOfferingDate(null);                          //公開日
        l_ipoProductParams.setPublicOfferingDateCount(0);                       //公開日日数  
        l_ipoProductParams.setPublicMarket(null);                                //公開市場
        l_ipoProductParams.setProvisionalValueDiv(null);                        //仮条件区分
        l_ipoProductParams.setProvisionalMinValue(null);                        //仮条件下限値
        l_ipoProductParams.setProvisionalMaxValue(null);                        //仮条件上限値
        l_ipoProductParams.setProvisionalValueOpenDate(null);                 //仮条件提示日
        l_ipoProductParams.setPublicOfferingQuantity(0);                          //公募数量  
        l_ipoProductParams.setPublicSalesQuantity(0);                          //売出数量
        l_ipoProductParams.setDealingQuantity(0);                             //当社取扱数量 
        l_ipoProductParams.setLeadManagingUnderwriter(null);                   //主幹事証券会社名
        l_ipoProductParams.setLotSize(0);                                  //購入申込単位
        l_ipoProductParams.setTickValue(null);                                 //刻み
        l_ipoProductParams.setIpoUnitDiv(null);                               //表示用単位区分
        l_ipoProductParams.setEnableMarketOrder(null);                        //成行可能
        l_ipoProductParams.setBookbuildingStartDatetime(null);                //ブックビルディング開始日時
        l_ipoProductParams.setBookbuildingEndDatetime(null);                  //ブックビルディング終了日時   
        l_ipoProductParams.setPublicPriceSettleDate(null);                   //公開価格決定日
        l_ipoProductParams.setPublicPrice(0);                      //公開価格
        l_ipoProductParams.setPublicPriceDiscountRate(0);        //公開価格（ディスカウント率）
        l_ipoProductParams.setLotDate(null);                                   //抽選日
        l_ipoProductParams.setLotDateCount(0);                   //抽選日日数
        l_ipoProductParams.setLotStatus(null);                                 //抽選状態  
        l_ipoProductParams.setOfferStartDatetime(null);                       //購入申込開始日時(当社設定)
        l_ipoProductParams.setOfferStartDateCount(0);           //購入申込開始日日数(当社設定）
        l_ipoProductParams.setOfferEndDatetime(null);                         //購入申込終了日時(当社設定) 
        l_ipoProductParams.setOfferEndDateCount(0);             //購入申込終了日日数(当社設定）
        l_ipoProductParams.setOfferStartDateProspec(null);                   //購入申込開始日(目論見書記載)
        l_ipoProductParams.setOfferStartDateCountProspec(0);   //購入申込開始日日数(目論見書記載)
        l_ipoProductParams.setOfferEndDateCountProspec(null);                     //購入申込終了日(目論見書記載)
        l_ipoProductParams.setOfferEndDateCountProspec(0);     //購入申込終了日日数(目論見書記載)
        l_ipoProductParams.setCompanyLogoUrl(null);                           //発行会社ロゴファ@イルURL
        l_ipoProductParams.setCompanyUrl(null);                                //発行会社ウェブサイトURL
        l_ipoProductParams.setCompanyOutline(null);                            //発行会社概要
        l_ipoProductParams.setNote(null);                                       //備考
        l_ipoProductParams.setIpoStop(null);                                   //IPO停止
        l_ipoProductParams.setDeleteFlag(null);                                //削除フラグ
        l_ipoProductParams.setLastUpdater(null);                               //更新者コード
        l_ipoProductParams.setCreatedTimestamp(null);                          //作成日時
        l_ipoProductParams.setLastUpdatedTimestamp(null);                     //更新日時
        
        //入力内容よりIPO銘柄情報を作成する
        WEB3IpoProductInfoService l_service =
                (WEB3IpoProductInfoService)Services.getService(WEB3IpoProductInfoService.class);
        log.debug("l_ipoProductParams = " + l_ipoProductParams);
        log.debug("l_ipoProductInfoInputMessage = " + l_ipoProductInfoInputMessage);        
        log.debug("l_manager = " + l_manager);
        WEB3IpoProductImpl l_ipoProductImpl = l_service.createIpoProduct(l_ipoProductParams,l_ipoProductInfoInputMessage,l_manager);              
                
        log.exiting(STR_METHOD_NAME);
        return l_ipoProductImpl;
        
    }
}
@
