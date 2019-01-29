head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品訂正サービスImpl(WEB3AdminPointPremiumChangeServiceImpl.java)
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
import webbroker3.point.message.WEB3AdminPointPremiumChangeCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumChangeConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeConfirmResponse;
import webbroker3.point.message.WEB3AdminPointPremiumChangeInputRequest;
import webbroker3.point.message.WEB3AdminPointPremiumChangeInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (景品訂正サービスImpl)<BR>
 * 景品訂正サービス実装クラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumChangeServiceImpl implements WEB3AdminPointPremiumChangeService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumChangeServiceImpl.class);

    /**
     * 景品訂正サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、get入力画面()、validate訂正()<BR>
     * またはsubmit訂正()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41933860001D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointPremiumChangeInputRequest)
        {
            log.debug("WEB3AdminPointPremiumChangeInputRequest");
            WEB3AdminPointPremiumChangeInputResponse l_response = 
                getInputScreen((WEB3AdminPointPremiumChangeInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointPremiumChangeConfirmRequest)
        {
            log.debug("WEB3AdminPointPremiumChangeConfirmRequest");
            WEB3AdminPointPremiumChangeConfirmResponse l_response = 
                validateChange((WEB3AdminPointPremiumChangeConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointPremiumChangeCompleteRequest)
        {
            log.debug("WEB3AdminPointPremiumChangeCompleteRequest");
            WEB3AdminPointPremiumChangeCompleteResponse l_response = 
                submitChange((WEB3AdminPointPremiumChangeCompleteRequest)l_request);
        
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
     * ]入力画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（景品訂正）get入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumChangeInputResponse
     * @@roseuid 41933860001F
     */
    protected WEB3AdminPointPremiumChangeInputResponse getInputScreen(WEB3AdminPointPremiumChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminPointPremiumChangeInputRequest)";
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
        
        //1.4 get景品(String, String)
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //景品番号： リクエストデータ.景品番号 

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
            
        WEB3PointPremium l_premium = l_manager.getPremium(l_strInstitutionCode, l_request.premiumNo);
        
        if (l_premium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.5  createResponse()
        WEB3AdminPointPremiumChangeInputResponse l_response = 
            (WEB3AdminPointPremiumChangeInputResponse)l_request.createResponse();
        
        //1.6 プロパティセット
        //景品名
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        l_response.beforePremiumName = l_premium.getPremiumName();
        
        //必要ポイント
        l_response.beforeRequiredPoint = new Long(l_premium.getRequiredPoint()).toString();
        
        //提供開始日時
        l_response.beforeStartDate = l_premium.getStartDateTime();
        
        //提供終了日時
        l_response.beforeEndDate = l_premium.getEndDateTime();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate訂正)<BR>
     * 訂正の審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（景品訂正）validate訂正」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumChangeConfirmResponse
     * @@roseuid 419338600021
     */
    protected WEB3AdminPointPremiumChangeConfirmResponse validateChange(WEB3AdminPointPremiumChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminPointPremiumChangeConfirmRequest)";
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
        
        //1.4 get景品(String, String)
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //景品番号： リクエストデータ.景品番号 

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
            
        WEB3PointPremium l_premium = l_manager.getPremium(
            l_strInstitutionCode, l_request.premiumNo);
        
        //1.5 validate訂正景品内容(String, String, String, Date, Date, ポイント景品)
        //[引数] 
        //景品番号： リクエストデータ.景品番号 
        //景品名： リクエストデータ.訂正後景品名 
        //必要ポイント： リクエストデータ.訂正後必要ポイント 
        //提供開始日時： リクエストデータ.訂正後提供開始日時 
        //提供終了日時： リクエストデータ.訂正後提供終了日時 
        //景品： get景品()の戻り値 

        l_manager.validateChangePremiumSpec(l_request.premiumNo, 
            l_request.afterPremiumName, l_request.afterRequiredPoint, 
            l_request.afterStartDate, l_request.afterEndDate, l_premium);
                
        //1.6  createResponse()
        WEB3AdminPointPremiumChangeConfirmResponse l_response = 
            (WEB3AdminPointPremiumChangeConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit訂正)<BR>
     * 景品の訂正を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（景品訂正）submit訂正」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumChangeCompleteResponse
     * @@roseuid 419338600023
     */
    protected WEB3AdminPointPremiumChangeCompleteResponse submitChange(WEB3AdminPointPremiumChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminPointPremiumChangeCompleteRequest)";
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
        
        //1.5 get景品(String, String)
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //景品番号： リクエストデータ.景品番号 

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
            
        WEB3PointPremium l_premium = l_manager.getPremium(
            l_strInstitutionCode, l_request.premiumNo);
        
        //1.6 validate訂正景品内容(String, String, String, Date, Date, ポイント景品)
        //[引数] 
        //景品番号： リクエストデータ.景品番号 
        //景品名： リクエストデータ.訂正後景品名 
        //必要ポイント： リクエストデータ.訂正後必要ポイント 
        //提供開始日時： リクエストデータ.訂正後提供開始日時 
        //提供終了日時： リクエストデータ.訂正後提供終了日時 
        //景品： get景品()の戻り値 

        l_manager.validateChangePremiumSpec(l_request.premiumNo, 
            l_request.afterPremiumName, l_request.afterRequiredPoint, 
            l_request.afterStartDate, l_request.afterEndDate, l_premium);
        
        if (l_premium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.7 createForUpdateParams( )
        l_premium.createForUpdateParams();
        
        //1.8 set景品名(String)
        //[引数] 
        //景品名： リクエストデータ.訂正後景品名 
        l_premium.setPremiumName(l_request.afterPremiumName);
        
        //1.9 set必要ポイント(long)
        //[引数] 
        //必要ポイント： リクエストデータ.訂正後必要ポイント        
        long l_lngAfterRequiredPoint = Long.parseLong(l_request.afterRequiredPoint);
        
        l_premium.setRequiredPoint(l_lngAfterRequiredPoint);
        
        //1.10 set提供開始日時(Date)
        //[引数] 
        //提供開始日時： リクエストデータ.訂正後提供開始日時 
        l_premium.setStartDateTime(l_request.afterStartDate);
        
        //1.11 set提供終了日時(Date)
        //[引数] 
        //提供終了日時： リクエストデータ.訂正後提供終了日時 
        l_premium.setEndDateTime(l_request.afterEndDate);
        
        //1.12 save景品(ポイント景品, 管理者)
        //[引数] 
        //景品： ポイント景品オブジェクト 
        //管理者： 管理者オブジェクト 
        l_manager.savePremium(l_premium, l_admin);
                
        //1.13  createResponse()
        WEB3AdminPointPremiumChangeCompleteResponse l_response = 
            (WEB3AdminPointPremiumChangeCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;     
    }
}
@
