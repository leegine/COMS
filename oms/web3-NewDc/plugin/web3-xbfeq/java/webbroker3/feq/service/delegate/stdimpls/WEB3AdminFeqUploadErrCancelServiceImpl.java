head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqUploadErrCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービスImpl(WEB3AdminFeqUploadErrCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/24 郭英 (中訊) 新規作成
                 : 2005/08/01 韋念瓊(中訊) レビュー   
*/

package webbroker3.feq.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqExecuteResultInputCSV;
import webbroker3.feq.WEB3FeqOrderAcceptResultUploadCSV;
import webbroker3.feq.define.WEB3FeqErrorCancelTargetDivDef;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqUploadErrCancelService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービスImpl)<BR>
 * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞ解除サービス実装クラス<BR>
 *    
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqUploadErrCancelServiceImpl implements WEB3AdminFeqUploadErrCancelService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqUploadErrCancelServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F202DE
     */
    public WEB3AdminFeqUploadErrCancelServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式同時アップロードエラー解除処理を実施する。<BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。<BR>
     * <BR>
     * －get入力画面()<BR>
     * －validate解除()<BR>
     * －submit解除()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB75F032B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqUploadErrCancelInputRequest)
        {
            //get入力画面()
            l_response = 
                this.getInputScreen((WEB3AdminFeqUploadErrCancelInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqUploadErrCancelConfirmRequest)
        {
            //validate解除()
            l_response = 
                this.validateRelease((WEB3AdminFeqUploadErrCancelConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqUploadErrCancelCompleteRequest)
        {
            //submit解除()
            l_response = 
                this.submitRelease((WEB3AdminFeqUploadErrCancelCompleteRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図「（(管)ｴﾗｰ解除）get入力画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除入力リクエスト<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    protected WEB3AdminFeqUploadErrCancelInputResponse getInputScreen(
        WEB3AdminFeqUploadErrCancelInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqUploadErrCancelInputRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "管理者のログイン情報が存在しない。");
        }
        
        //1.2:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.3:createResponse( )
        WEB3AdminFeqUploadErrCancelInputResponse l_response = 
            (WEB3AdminFeqUploadErrCancelInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate解除)<BR>
     * 確認画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図「（(管)ｴﾗｰ解除）validate解除」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除確認リクエスト<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB676010A
     */
    protected WEB3AdminFeqUploadErrCancelConfirmResponse validateRelease(
        WEB3AdminFeqUploadErrCancelConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRelease(WEB3AdminFeqUploadErrCancelConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate()
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "管理者のログイン情報が存在しない。");
        }
        
        //1.3:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:createResponse( )
        WEB3AdminFeqUploadErrCancelConfirmResponse l_response = 
            (WEB3AdminFeqUploadErrCancelConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit解除)<BR>
     * 同時ｱｯﾌﾟﾛｰﾄﾞ解除処理を実施する。<BR>
     * <BR>
     * シーケンス図「（(管)ｴﾗｰ解除）submit解除」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除完了リクエスト<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB676010C
     */
    protected WEB3AdminFeqUploadErrCancelCompleteResponse submitRelease(
        WEB3AdminFeqUploadErrCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRelease(WEB3AdminFeqUploadErrCancelCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate()
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "管理者のログイン情報が存在しない。");
        }
        
        //1.3:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5:(*) 注文受付（リクエストデータ.エラー解除対象機@能区分 == ”注文受付”）の場合
        if (WEB3FeqErrorCancelTargetDivDef.ORDER_ACCEPT.equals(l_request.errorCancelTargetDiv))
        {
            //1.5.1:saveAllアップロード中止( )
            WEB3FeqOrderAcceptResultUploadCSV l_csv = new WEB3FeqOrderAcceptResultUploadCSV();
            l_csv.saveAllUploadStop();
        }
        
        //1.6:(*) 約定（リクエストデータ.エラー解除対象機@能区分 == ”約定”）の場合
        if (WEB3FeqErrorCancelTargetDivDef.EXECUTED.equals(l_request.errorCancelTargetDiv))
        {
            //1.6.1:saveAllアップロード中止( )
            WEB3FeqExecuteResultInputCSV l_csv = new WEB3FeqExecuteResultInputCSV();
            l_csv.saveAllUploadStop();
        }
        
        //1.7:createResponse( )
        WEB3AdminFeqUploadErrCancelCompleteResponse l_response = 
            (WEB3AdminFeqUploadErrCancelCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
