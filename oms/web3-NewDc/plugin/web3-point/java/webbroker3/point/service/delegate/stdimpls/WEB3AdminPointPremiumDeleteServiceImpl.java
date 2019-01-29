head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品削除サービスImpl(WEB3AdminPointPremiumDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.WEB3PointApplyManager;
import webbroker3.point.WEB3PointPremium;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.data.PointPremiumMasterRow;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteCompleteRequest;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteCompleteResponse;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteConfirmRequest;
import webbroker3.point.message.WEB3AdminPointPremiumDeleteConfirmResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumDeleteService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (景品削除サービスImpl)<BR>
 * 景品削除サービス実装クラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumDeleteServiceImpl implements WEB3AdminPointPremiumDeleteService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumDeleteServiceImpl.class);

    /**
     * 景品削除サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、validate削除()またはsubmit削除()<BR>
     * メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 4193451F0369
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointPremiumDeleteConfirmRequest)
        {
            log.debug("WEB3AdminPointPremiumDeleteConfirmRequest");
            WEB3AdminPointPremiumDeleteConfirmResponse l_response = 
                validateDelete((WEB3AdminPointPremiumDeleteConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointPremiumDeleteCompleteRequest)
        {
            log.debug("WEB3AdminPointPremiumDeleteCompleteRequest");
            WEB3AdminPointPremiumDeleteCompleteResponse l_response = 
                submitDelete((WEB3AdminPointPremiumDeleteCompleteRequest)l_request);
        
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
     * (validate削除)<BR>
     * 削除の審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（景品削除）validate削除」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumDeleteConfirmResponse
     * @@roseuid 4193451F036B
     */
    protected WEB3AdminPointPremiumDeleteConfirmResponse validateDelete(WEB3AdminPointPremiumDeleteConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDelete(WEB3AdminPointPremiumDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
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
        
        //1.4 get申込件数(String, String)
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //景品番号： リクエストデータ.景品番号 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager) 
            Services.getService(WEB3PointApplyManager.class);
            
        if (l_applyManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        long l_lngApplyNumber = l_applyManager.getApplyNumber(
            l_strInstitutionCode, l_request.premiumNo);
        
        //1.5 get景品(String, String)
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //景品番号： リクエストデータ.景品番号 
        WEB3PointProductManager l_productManager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
            
        if (l_productManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        WEB3PointPremium l_premium = l_productManager.getPremium(l_strInstitutionCode, l_request.premiumNo);
        
        if (l_premium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6 createResponse()
        WEB3AdminPointPremiumDeleteConfirmResponse l_response = 
            (WEB3AdminPointPremiumDeleteConfirmResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.7 プロパティセット
        //申込件数
        l_response.applyCount = new Long(l_lngApplyNumber).toString();
        
        //景品名
        l_response.premiumName = l_premium.getPremiumName();
        
        //必要ポイント
        l_response.requiredPoint = new Long(l_premium.getRequiredPoint()).toString();
        
        //提供開始日時
        l_response.startDate = l_premium.getStartDateTime();
        
        //提供終了日時
        l_response.endDate = l_premium.getEndDateTime();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit削除)<BR>
     * 景品の削除を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（景品削除）submit削除」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointPremiumDeleteCompleteResponse
     * @@roseuid 4193451F036D
     */
    protected WEB3AdminPointPremiumDeleteCompleteResponse submitDelete(WEB3AdminPointPremiumDeleteCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitDelete(WEB3AdminPointPremiumDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
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
        
        WEB3PointProductManager l_productManager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
            
        if (l_productManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        WEB3PointPremium l_premium = l_productManager.getPremium(l_strInstitutionCode, l_request.premiumNo);
        
        if (l_premium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6  deleteRow(Row)
        //[引数] 
        //景品行： ポイント景品.getDataSourceObject()の戻り値 
        PointPremiumMasterRow l_row = (PointPremiumMasterRow)l_premium.getDataSourceObject();
        try
        {
            WEB3DataAccessUtility.deleteRow(l_row);//DataQueryException, DataNetworkException
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.7 createResponse()
        WEB3AdminPointPremiumDeleteCompleteResponse l_response = 
            (WEB3AdminPointPremiumDeleteCompleteResponse)l_request.createResponse();       
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
