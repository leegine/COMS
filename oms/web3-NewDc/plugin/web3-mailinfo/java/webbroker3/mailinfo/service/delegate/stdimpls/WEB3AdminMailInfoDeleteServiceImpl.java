head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.23.04.51.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1cc4d897c1210b4;
filename	WEB3AdminMailInfoDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報削除サービスImpl(WEB3AdminMailInfoDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.mailinfo.WEB3AdminMailInfoClientRequestService;
import webbroker3.mailinfo.WEB3AdminMailInfoManager;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoDeleteService;
import webbroker3.util.WEB3LogUtility;

/**
 * ( メール情報削除サービスImpl )<BR>
 * <BR>
 * メール情報削除サービス実装クラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoDeleteServiceImpl extends WEB3AdminMailInfoClientRequestService implements WEB3AdminMailInfoDeleteService 
{    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoDeleteServiceImpl.class);      

    /**
     * @@roseuid 416F1DCC035B
     */
    public WEB3AdminMailInfoDeleteServiceImpl() 
    {
     
    }
    
    /**
     * メール情報削除処理を行う。<BR>
     * <BR>
     * リクエストデータによって、validateメール情報削除( )<BR>
     * またはsubmitメール情報削除( )をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C3E83030D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if(l_request instanceof WEB3AdminMailInfoDeleteCompleteRequest)
        {
            l_response = this.submitMailInfoDelete((WEB3AdminMailInfoDeleteCompleteRequest)l_request);
           
        }
        else if (l_request instanceof WEB3AdminMailInfoDeleteConfirmRequest)
        {
            l_response = this.validateMailInfoDelete((WEB3AdminMailInfoDeleteConfirmRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80018.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * ( validateメール情報削除 )<BR>
     * メール情報削除審査処理を行う。<BR>
     * <BR>
     * シーケンス図「（メール情報削除）validate」参照<BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （メール情報削除）validate             <BR>
     *         具体位置    :  1.4  isDIR管理者( )                    <BR>
     *        ログイン管理者がDIR管理者かどうかチェックする。 <BR>
     *       （isDIR管理者( )==falseの場合、[権限エラー]として例外をスローする。） <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857           <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （メール情報削除）validate             <BR>
     *         具体位置    :  1.5  getメール(String, String, String) <BR>
     *         削除対象のメールが存在するかどうかチェックする。<BR>
     *        （getメール( )==nullの場合、[データ不整合]として例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00859          <BR>
     * =============================================== <BR>
     * @@param l_request - ( リクエストデータ )<BR>
     * <BR>
     * メール情報削除確認リクエストオブジェクト<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C3EA302EE
     */
    protected WEB3AdminMailInfoDeleteConfirmResponse validateMailInfoDelete(WEB3AdminMailInfoDeleteConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateMailInfoDelete(WEB3AdminMailInfoDeleteConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
                
        WEB3AdminMailInfoDeleteConfirmResponse l_response = null;
        
        // (1.1)validate        
        l_request.validate();
        
        //(1.2)getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //(1.3)validate権限()        
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO,true);
        
        //1.4 isDIR管理者( )        
        boolean l_blnDir = l_administartor.isDirAdministrator();
        if (l_blnDir == false)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00857, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        String l_institutionCode = null;
        l_institutionCode = l_administartor.getInstitutionCode();
        
        //1.5 getメール(String, String, String)
        WEB3GentradeMailInfo l_mail = null;
        l_mail = WEB3AdminMailInfoManager.getMail(l_institutionCode, l_request.sendMailDiv, l_request.discernId);
        if (l_mail == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00859.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00859, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6 createレスポンス( )
        l_response = (WEB3AdminMailInfoDeleteConfirmResponse) l_request.createResponse();
        
        //1.7 プロパティ・セット
        l_response.mailName = l_mail.getMailName();
        l_response.mailFrom = l_mail.getMailSender();
        l_response.sendAddress = l_mail.getSendAddress();
        l_response.mailSubject = l_mail.getSubject();
        l_response.mailHeader = l_mail.getMailHeader();
        l_response.mailBody = l_mail.getMailText();
        l_response.mailFooter = l_mail.getMailFooter();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * ( submitメール情報削除 ) <BR>
     * <BR>
     * メール情報削除処理を行う。<BR>
     * <BR>
     * シーケンス図「（メール情報削除）submit」参照<BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （メール情報削除）submit               <BR>
     *         具体位置    :  1.5  isDIR管理者( )                     <BR>
     *         ログイン管理者がDIR管理者かどうかチェックする。  <BR>
     *        （isDIR管理者( )==falseの場合、[権限エラー]として例外をスローする。  <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （メール情報削除）submit               <BR>
     *         具体位置    :  1.6 getMail(String, String, String)    <BR>
     *         削除対象のメールが存在するかどうかチェックする。<BR>
     *        （getメール( )==nullの場合、[データ不整合]として例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00859           <BR>
     * =============================================== <BR>
     * @@param l_request - ( リクエストデータ )<BR>
     * <BR>
     * メール情報削除完了リクエストオブジェクト<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C3EE502AF
     */
    protected WEB3AdminMailInfoDeleteCompleteResponse submitMailInfoDelete(WEB3AdminMailInfoDeleteCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitMailInfoDelete(WEB3AdminMailInfoDeleteCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoDeleteCompleteResponse l_response = null;
        
        // (1.1)validate        
        l_request.validate();
        
        //(1.2)getInstanceFromログイン情報()        
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //(1.3)validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO,true);
        
        //1.4 validate取引パスワード(パスワード : String)
        l_administartor.validateTradingPassword(l_request.password);
        
        //1.5 isDIR管理者()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00857, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        String l_institutionCode = null;
        l_institutionCode = l_administartor.getInstitutionCode();
        
        //1.6 getメール(String, String, String)
        WEB3GentradeMailInfo l_mail = null;
        l_mail = WEB3AdminMailInfoManager.getMail(l_institutionCode,l_request.sendMailDiv,l_request.discernId);
        if (l_mail == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00859.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00859, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.7 removeメール(証券会社コード : String, 送信メール区分 : String, 識別ID : String)
        WEB3GentradeMailInfo.removeMail(l_institutionCode,l_request.sendMailDiv,l_request.discernId);
        
        //1.8 createレスポンス( )
        l_response = (WEB3AdminMailInfoDeleteCompleteResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }    
}
@
