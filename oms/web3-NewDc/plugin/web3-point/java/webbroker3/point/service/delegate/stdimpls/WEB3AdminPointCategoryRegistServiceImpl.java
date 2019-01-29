head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー登録サービスImpl(WEB3AdminPointCategoryRegistServiceImpl.java)
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
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (カテゴリー登録サービスImpl)<BR>
 * カテゴリー登録サービス実装クラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryRegistServiceImpl implements WEB3AdminPointCategoryRegistService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointCategoryRegistServiceImpl.class);

    /**
     * カテゴリー登録サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、get入力画面()、validate登録()またはsubmit登録()メソッド<BR>をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 419179760101
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointCategoryRegistInputRequest)
        {
            log.debug("WEB3AdminPointCategoryRegistInputRequest");
            WEB3AdminPointCategoryRegistInputResponse l_response = 
                getInputScreen((WEB3AdminPointCategoryRegistInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointCategoryRegistConfirmRequest)
        {
            log.debug("WEB3AdminPointCategoryRegistConfirmRequest");
            WEB3AdminPointCategoryRegistConfirmResponse l_response = 
                validateRegist((WEB3AdminPointCategoryRegistConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointCategoryRegistCompleteRequest)
        {
            log.debug("WEB3AdminPointCategoryRegistCompleteRequest");
            WEB3AdminPointCategoryRegistCompleteResponse l_response = 
                submitRegist((WEB3AdminPointCategoryRegistCompleteRequest)l_request);
        
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
     * 「（カテゴリー登録）get入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistInputResponse
     * @@roseuid 41917A4F0046
     */
    protected WEB3AdminPointCategoryRegistInputResponse getInputScreen(WEB3AdminPointCategoryRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointCategoryRegistInputRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate権限(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.3 createResponse( )
        WEB3AdminPointCategoryRegistInputResponse l_response = 
            (WEB3AdminPointCategoryRegistInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate登録)<BR>
     * 登録の審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（カテゴリー登録）validate登録」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmResponse
     * @@roseuid 4191A8B30159
     */
    protected WEB3AdminPointCategoryRegistConfirmResponse validateRegist(WEB3AdminPointCategoryRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AdminPointCategoryRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate権限(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.3 validateカテゴリー内容(String, String)
        //[引数] 
        //カテゴリー名： リクエストデータ.カテゴリー名 
        //概要： リクエストデータ.カテゴリー概要 
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
           
        l_manager.validateCategorySpec(l_request.categoryName, l_request.categoryOutline);
                
        //1.4 createResponse( )
        WEB3AdminPointCategoryRegistConfirmResponse l_response = 
            (WEB3AdminPointCategoryRegistConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit登録)<BR>
     * カテゴリーの登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（カテゴリー登録）submit登録」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteResponse
     * @@roseuid 41917ABC03D0
     */
    protected WEB3AdminPointCategoryRegistCompleteResponse submitRegist(WEB3AdminPointCategoryRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRegist(WEB3AdminPointCategoryRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);  
        
        //1.1 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate権限(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, true);
        
        //1.3 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.4 validateカテゴリー内容(String, String)
        //[引数] 
        //カテゴリー名： リクエストデータ.カテゴリー名 
        //概要： リクエストデータ.カテゴリー概要 
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
           
        l_manager.validateCategorySpec(l_request.categoryName, l_request.categoryOutline);

        //1.5 ポイントカテゴリー(String, String, String)
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //カテゴリー名： リクエストデータ.カテゴリー名 
        //概要： リクエストデータ.カテゴリー概要 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3PointCategory l_pointCategory = new WEB3PointCategory(l_strInstitutionCode, 
            l_request.categoryName, l_request.categoryOutline);
            
        //1.6 saveNewカテゴリー(ポイントカテゴリー, 管理者)
        //[引数] 
        //カテゴリー： ポイントカテゴリーオブジェクト 
        //管理者： 管理者オブジェクト 
        l_manager.saveNewCategory(l_pointCategory, l_admin);
        
        //1.7 createResponse( )
        WEB3AdminPointCategoryRegistCompleteResponse l_response = 
            (WEB3AdminPointCategoryRegistCompleteResponse)l_request.createResponse();
        
        return l_response;
    }
}
@
