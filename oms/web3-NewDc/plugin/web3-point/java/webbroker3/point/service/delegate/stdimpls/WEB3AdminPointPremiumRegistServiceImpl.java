head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品登録サービスImpl(WEB3AdminPointPremiumRegistServiceImpl.java)
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
import webbroker3.point.WEB3PointPremium;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmResponse;
import webbroker3.point.message.WEB3AdminPointPremiumRegistInputRequest;
import webbroker3.point.message.WEB3AdminPointPremiumRegistInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (景品登録サービスImpl)<BR>
 * 景品登録サービス実装クラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumRegistServiceImpl implements WEB3AdminPointPremiumRegistService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumRegistServiceImpl.class);

    /**
     * 景品登録サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、get入力画面()、validate登録()<BR>
     * またはsubmit登録()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 4192E9C10185
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointPremiumRegistInputRequest)
        {
            log.debug("WEB3AdminPointPremiumRegistInputRequest");
            WEB3AdminPointPremiumRegistInputResponse l_response = 
                getInputScreen((WEB3AdminPointPremiumRegistInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointPremiumRegistConfirmRequest)
        {
            log.debug("WEB3AdminPointPremiumRegistConfirmRequest");
            WEB3AdminPointPremiumRegistConfirmResponse l_response = 
                validateRegist((WEB3AdminPointPremiumRegistConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointPremiumRegistCompleteRequest)
        {
            log.debug("WEB3AdminPointPremiumRegistCompleteRequest");
            WEB3AdminPointPremiumRegistCompleteResponse l_response = 
                submitRegist((WEB3AdminPointPremiumRegistCompleteRequest)l_request);
        
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
     * 「（景品登録）get入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumRegistInputResponse
     * @@roseuid 4192E9C1026F
     */
    protected WEB3AdminPointPremiumRegistInputResponse getInputScreen(WEB3AdminPointPremiumRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointPremiumRegistInputRequest)";
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
    
        //1.3 createResponse()
        WEB3AdminPointPremiumRegistInputResponse l_response = 
            (WEB3AdminPointPremiumRegistInputResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate登録)<BR>
     * 登録の審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（景品登録）validate登録」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumRegistConfirmResponse
     * @@roseuid 4192E9C10398
     */
    protected WEB3AdminPointPremiumRegistConfirmResponse validateRegist(WEB3AdminPointPremiumRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AdminPointPremiumRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
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
    
        //1.4 validate重複景品番号(String, String)
        WEB3PointProductManager l_manager = 
            (WEB3PointProductManager) Services.getService(WEB3PointProductManager.class);
        
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        l_manager.validateDupliPremiumNo(l_admin.getInstitutionCode(), l_request.premiumNo);    
    
        //1.5 validate景品内容(String, String, String, Date, Date)
        //[引数] 
        //景品番号： リクエストデータ.景品番号 
        //景品名： リクエストデータ.景品名 
        //必要ポイント： リクエストデータ.必要ポイント 
        //提供開始日時： リクエストデータ.提供開始日時 
        //提供終了日時： リクエストデータ.提供終了日時 
        l_manager.validatePremiumSpec(l_request.premiumNo, 
            l_request.premiumName, l_request.requiredPoint, 
            l_request.startDate, l_request.endDate);
                
        //1.6 createResponse()
        WEB3AdminPointPremiumRegistConfirmResponse l_response = 
            (WEB3AdminPointPremiumRegistConfirmResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit登録)<BR>
     * 登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（景品登録）submit登録」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumRegistCompleteResponse
     * @@roseuid 4192E9C200C9
     */
    protected WEB3AdminPointPremiumRegistCompleteResponse submitRegist(WEB3AdminPointPremiumRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRegist(WEB3AdminPointPremiumRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
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
        
        //1.5 validate重複景品番号(String, String)
        WEB3PointProductManager l_manager = 
            (WEB3PointProductManager) Services.getService(WEB3PointProductManager.class);
        
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        l_manager.validateDupliPremiumNo(l_admin.getInstitutionCode(), l_request.premiumNo);
        
        //1.6  validate景品内容(String, String, String, Date, Date)
        //[引数] 
        //景品番号： リクエストデータ.景品番号 
        //景品名： リクエストデータ.景品名 
        //必要ポイント： リクエストデータ.必要ポイント 
        //提供開始日時： リクエストデータ.提供開始日時 
        //提供終了日時： リクエストデータ.提供終了日時 
        l_manager.validatePremiumSpec(l_request.premiumNo, 
            l_request.premiumName, l_request.requiredPoint, 
            l_request.startDate, l_request.endDate);

        //1.7 ポイント景品(String, long, String, String, long, Date, Date)
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //カテゴリー番号： リクエストデータ.カテゴリー番号 
        //景品番号： リクエストデータ.景品番号 
        //景品名： リクエストデータ.景品名 
        //必要ポイント： リクエストデータ.必要ポイント 
        //提供開始日時： リクエストデータ.提供開始日時 
        //提供終了日時： リクエストデータ.提供終了日時         
        long l_lngCategoryNo = Long.parseLong(l_request.categoryNo);     
        
        long l_lngRequiredPoint = Long.parseLong(l_request.requiredPoint);

        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3PointPremium l_pointPremium = new WEB3PointPremium(
            l_strInstitutionCode, l_lngCategoryNo, l_request.premiumNo, 
            l_request.premiumName, l_lngRequiredPoint, l_request.startDate, l_request.endDate);
        
        //1.8 saveNew景品(ポイント景品, 管理者)
        //[引数] 
        //景品： ポイント景品オブジェクト 
        //管理者： 管理者オブジェクト 
        l_manager.saveNewPremium(l_pointPremium, l_admin);
                
        //1.9 createResponse()
        WEB3AdminPointPremiumRegistCompleteResponse l_response = 
            (WEB3AdminPointPremiumRegistCompleteResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
