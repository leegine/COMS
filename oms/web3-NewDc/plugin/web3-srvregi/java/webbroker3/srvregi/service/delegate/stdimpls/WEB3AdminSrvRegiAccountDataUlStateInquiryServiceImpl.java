head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データUL状況照会サービスImpl(WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.service.delegate.stdimpls;

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
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.define.WEB3SrvRegiUploadStateDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUlStateInquiryService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者顧客データUL状況照会サービスImpl)<BR>
 * サービス利用管理者顧客データアップロード状況照会サービス　@実装クラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountDataUlStateInquiryService 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl.class);
    
    /**
     * @@roseuid 416F39290213
     */
    public WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用管理者顧客データアップロード状況照会処理を行う。<BR>
     * <BR>
     * （サービス利用管理者）顧客データアップロード状況照会シーケンス図参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 411AC7D800E8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3AdminSrvRegiUploadStateResponse l_response = null;
        if (l_request instanceof WEB3AdminSrvRegiUploadStateRequest)
        {
            //getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //validate権限(String, boolean)
            l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
            
            //validate注文受付可能( )
            WEB3SrvRegiTradingTimeManagement.validateOrderAccept();
            
            //getアップロード状況(String)
            WEB3AdminSrvRegiUploadStateRequest l_uploadStateRequest = 
                (WEB3AdminSrvRegiUploadStateRequest)l_request;
            AdministratorUploadParams l_uploadParams = this.getUploadState(l_uploadStateRequest.uploadId);
            
            //サービス利用管理者顧客データUL状況照会レスポンス( )
            l_response = (WEB3AdminSrvRegiUploadStateResponse)l_request.createResponse();

            //(*)プロパティセット
            
            //処理状態区分
            if (l_uploadParams.getUploadEndTimestamp() == null
                && l_uploadParams.getMessageCode() == null)
            {
                l_response.uploadState = WEB3SrvRegiUploadStateDef.UPLOADING;
            }
            if (l_uploadParams.getUploadEndTimestamp() != null
                && l_uploadParams.getMessageCode() != null)
            {
                l_response.uploadState = WEB3SrvRegiUploadStateDef.UPLOAD_ERROR;
            }
            if (l_uploadParams.getUploadEndTimestamp() != null
                && l_uploadParams.getMessageCode() == null)
            {
                l_response.uploadState = WEB3SrvRegiUploadStateDef.UPLOAD_COMPLETE;
            }
            
            //処理件数
            if (l_uploadParams.getUploadEndTimestamp() != null)
            {
                l_response.endCount = WEB3StringTypeUtility.formatNumber(l_uploadParams.getUploadCount());
            }
            else
            { 
                l_response.endCount = null;
            }
            
            //開始日時
            l_response.startDate = l_uploadParams.getUploadStartTimestamp();
            
            //end日時
            l_response.endDate = l_uploadParams.getUploadEndTimestamp();
            
            //error code
            l_response.errorCode = l_uploadParams.getMessageCode();
        }
        else
        {
            String l_strErrorMessage = "パラメータの類型が不正。";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getアップロード状況)<BR>
     * アップロードIDに対応するアップロードParamsを取得して返却する。<BR>
     * <BR>
     * [検索条件]<BR>
     * アップロードID ＝ 引数.アップロードID<BR>
     * @@param l_strUploadId - (アップロードID)<BR>
     * リクエストのアップロードID<BR>
     * @@return AdministratorUploadParams
     * @@roseuid 411C3E8E00E0
     */
    public AdministratorUploadParams getUploadState(String l_strUploadId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUploadState(String )";
        log.entering(STR_METHOD_NAME );

        AdministratorUploadParams l_params = null;
        try
        {
            l_params = (AdministratorUploadParams)
                AdministratorUploadDao.findRowByPk(Long.parseLong(l_strUploadId));
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch(DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }   
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
