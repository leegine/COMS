head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー削除サービスImpl(WEB3AdminPointCategoryDeleteServiceImpl.java)
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
import webbroker3.point.WEB3PointCategory;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.data.PointCategoryMasterRow;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryDeleteConfirmResponse;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryDeleteService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (カテゴリー削除サービスImpl)<BR>
 * カテゴリー削除サービス実装クラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryDeleteServiceImpl implements WEB3AdminPointCategoryDeleteService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointCategoryDeleteServiceImpl.class);

    /**
     * カテゴリー削除サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、validate削除()またはsubmit削除()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 4191BCC40028
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminPointCategoryDeleteConfirmRequest)
        {
            log.debug("WEB3AdminPointCategoryDeleteConfirmRequest");
            WEB3AdminPointCategoryDeleteConfirmResponse l_response = 
                validateDelete((WEB3AdminPointCategoryDeleteConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointCategoryDeleteCompleteRequest)
        {
            log.debug("WEB3AdminPointCategoryDeleteCompleteRequest");
            WEB3AdminPointCategoryDeleteCompleteResponse l_response = 
                submitDelete((WEB3AdminPointCategoryDeleteCompleteRequest)l_request);
        
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
     * 「（カテゴリー削除）validate削除」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryDeleteConfirmResponse
     * @@roseuid 4191BCC4002C
     */
    protected WEB3AdminPointCategoryDeleteConfirmResponse validateDelete(WEB3AdminPointCategoryDeleteConfirmRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " validateDelete(WEB3AdminPointCategoryDeleteConfirmRequest)";
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
        
        //1.4 validate取扱景品(String, String)
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
        
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //カテゴリー番号： リクエストデータ.カテゴリー番号     
        l_manager.validateHandingPremium(l_strInstitutionCode, l_request.categoryNo);
        
        //1.5 getカテゴリー(String, String)
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //カテゴリー番号： リクエストデータ.カテゴリー番号 
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
        
        //1.6 createResponse( )
        WEB3AdminPointCategoryDeleteConfirmResponse l_response = 
            (WEB3AdminPointCategoryDeleteConfirmResponse)l_request.createResponse();
        
        //1.7 プロパティセット
        //getカテゴリー名
        l_response.categoryName = l_pointCategory.getCategoryName();
        
        //getカテゴリー概要
        l_response.categoryOutline = l_pointCategory.getCategoryOutline();      
                
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit削除)<BR>
     * カテゴリーの削除を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（カテゴリー削除）submit削除」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryDeleteCompleteResponse
     * @@roseuid 4191BCC4002E
     */
    protected WEB3AdminPointCategoryDeleteCompleteResponse submitDelete(WEB3AdminPointCategoryDeleteCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitDelete(WEB3AdminPointCategoryDeleteCompleteRequest)";
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
        
        //1.5 validate取扱景品(String, String)
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
        
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //カテゴリー番号： リクエストデータ.カテゴリー番号 
        l_manager.validateHandingPremium(l_strInstitutionCode, l_request.categoryNo);
        
        //1.6 getカテゴリー(String, String)
        //[引数]
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //カテゴリー番号： リクエストデータ.カテゴリー番号 
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
        
        //1.7 deleteRow(Row)
        //[引数] 
        //カテゴリー行： ポイントカテゴリー.getDataSourceObject()の戻り値 
        try
        {
            WEB3DataAccessUtility.deleteRow((PointCategoryMasterRow)l_pointCategory.getDataSourceObject());//DataQueryException, DataNetworkException
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
        
        //1.8 createResponse()
        WEB3AdminPointCategoryDeleteCompleteResponse l_response = 
            (WEB3AdminPointCategoryDeleteCompleteResponse)l_request.createResponse();   
                
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
