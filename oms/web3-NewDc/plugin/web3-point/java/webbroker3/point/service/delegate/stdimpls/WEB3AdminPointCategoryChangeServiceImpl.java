head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー訂正サービスImpl(WEB3AdminPointCategoryChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.WEB3PointCategory;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.message.WEB3AdminPointCategoryChangeCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryChangeConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryChangeInputRequest;
import webbroker3.point.message.WEB3AdminPointCategoryChangeInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (カテゴリー訂正サービスImpl)<BR>
 * カテゴリー訂正サービス実装クラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryChangeServiceImpl implements WEB3AdminPointCategoryChangeService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointCategoryChangeServiceImpl.class);

    /**
     * カテゴリー訂正サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、get入力画面()、validate訂正()または<BR>submit訂正()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 4191A5A001C6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointCategoryChangeInputRequest)
        {
            log.debug("WEB3AdminPointCategoryChangeInputRequest");
            WEB3AdminPointCategoryChangeInputResponse l_response = 
                getInputScreen((WEB3AdminPointCategoryChangeInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointCategoryChangeConfirmRequest)
        {
            log.debug("WEB3AdminPointCategoryChangeConfirmRequest");
            WEB3AdminPointCategoryChangeConfirmResponse l_response = 
                validateChange((WEB3AdminPointCategoryChangeConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointCategoryChangeCompleteRequest)
        {
            log.debug("WEB3AdminPointCategoryChangeCompleteRequest");
            WEB3AdminPointCategoryChangeCompleteResponse l_response = 
                submitChange((WEB3AdminPointCategoryChangeCompleteRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（カテゴリー訂正）get入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryChangeInputResponse
     * @@roseuid 4191AA060149
     */
    protected WEB3AdminPointCategoryChangeInputResponse getInputScreen(WEB3AdminPointCategoryChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointCategoryChangeInputRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1  validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.3 validate権限(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.4  getカテゴリー(String, String)
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //カテゴリー番号： リクエストデータ.カテゴリー番号 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3PointProductManager l_manager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
            
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        WEB3PointCategory l_pointCategory = l_manager.getCategory(
            l_strInstitutionCode, l_request.categoryNo);
            
        if (l_pointCategory == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.5 createResponse( )
        WEB3AdminPointCategoryChangeInputResponse l_response = 
            (WEB3AdminPointCategoryChangeInputResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        
        //1.6 プロパティセット
        //カテゴリー名
        l_response.beforeCategoryName = l_pointCategory.getCategoryName();
        
        //カテゴリー概要
        l_response.beforeCategoryOutline = l_pointCategory.getCategoryOutline(); 
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate訂正)<BR>
     * 訂正の審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（カテゴリー訂正）validate訂正」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryChangeConfirmResponse
     * @@roseuid 4191A5A001C8
     */
    protected WEB3AdminPointCategoryChangeConfirmResponse validateChange(WEB3AdminPointCategoryChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminPointCategoryChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1  validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.3 validate権限(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.4  getカテゴリー(String, String)
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //カテゴリー番号： リクエストデータ.カテゴリー番号 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3PointProductManager l_manager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
            
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        WEB3PointCategory l_pointCategory = l_manager.getCategory(
            l_strInstitutionCode, l_request.categoryNo);
        
        //1.5 validate訂正カテゴリー内容(String, String, ポイントカテゴリー)
        //[引数] 
        //カテゴリー名： リクエストデータ.訂正後カテゴリー名 
        //概要： リクエストデータ.訂正後カテゴリー概要 
        //カテゴリー： getカテゴリー()の戻り値 
        l_manager.validateChangeCategorySpec(l_request.afterCategoryName, 
            l_request.afterCategoryOutline, l_pointCategory);
                
        //1.6 createResponse()
        WEB3AdminPointCategoryChangeConfirmResponse l_response = 
            (WEB3AdminPointCategoryChangeConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit訂正)<BR>
     * カテゴリーの訂正を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（カテゴリー訂正）submit訂正」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryChangeCompleteResponse
     * @@roseuid 4191A5A001D6
     */
    protected WEB3AdminPointCategoryChangeCompleteResponse submitChange(WEB3AdminPointCategoryChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminPointCategoryChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1  validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.3 validate権限(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.4 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 getカテゴリー(String, String)
        //[引数]
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //カテゴリー番号： リクエストデータ.カテゴリー番号 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3PointProductManager l_manager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
            
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        WEB3PointCategory l_pointCategory = l_manager.getCategory(
            l_strInstitutionCode, l_request.categoryNo);
        
        //1.6 validate訂正カテゴリー内容(String, String, ポイントカテゴリー)
        //[引数] 
        //カテゴリー名： リクエストデータ.訂正後カテゴリー名 
        //概要： リクエストデータ.訂正後カテゴリー概要 
        //カテゴリー： getカテゴリー()の戻り値 
        l_manager.validateChangeCategorySpec(l_request.afterCategoryName, 
            l_request.afterCategoryOutline, l_pointCategory);
                
        if (l_pointCategory == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.7 createForUpdateParams()
        l_pointCategory.createForUpdateParams();
        
        //1.8 setカテゴリー名(String)
        //[引数] 
        //カテゴリー名： リクエストデータ.訂正後カテゴリー名
        l_pointCategory.setCategoryName(l_request.afterCategoryName);
        
        //1.9 setカテゴリー概要(String)
        //[引数] 
        //概要： リクエストデータ.訂正後カテゴリー概要 
        l_pointCategory.setCategoryOutline(l_request.afterCategoryOutline);
        
        //1.10 saveカテゴリー(ポイントカテゴリー, 管理者)
        //[引数] 
        //カテゴリー： ポイントカテゴリーオブジェクト 
        //管理者： 管理者オブジェクト 
        l_manager.saveCategory(l_pointCategory, l_admin);
        
        //1.11 createResponse( )
        WEB3AdminPointCategoryChangeCompleteResponse l_response = 
            (WEB3AdminPointCategoryChangeCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
