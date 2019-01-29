head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.23.04.51.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1cc4d897c1210b4;
filename	WEB3AdminMailInfoDetailsServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報詳細サービスImpl(WEB3AdminMailInfoDetailsServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
Revesion History : 2004/10/19  奚靖(中訊) 作成
*/
package webbroker3.mailinfo.service.delegate.stdimpls;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.mailinfo.WEB3AdminMailInfoManager;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsResponse;
import webbroker3.mailinfo.WEB3AdminMailInfoClientRequestService;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoDetailsService;
import webbroker3.util.WEB3LogUtility;

/**
 * （メール情報詳細サービスImpl）<BR>
 * メール情報詳細サービス実装クラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoDetailsServiceImpl extends WEB3AdminMailInfoClientRequestService implements WEB3AdminMailInfoDetailsService 
{    
    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoDetailsServiceImpl.class);
    
    /**
     * @@roseuid 416F1DCD031C
     */
    public WEB3AdminMailInfoDetailsServiceImpl() 
    {
     
    }
    
    
    /**
     * メール情報詳細照会処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス情報詳細）execute」参照<BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  「（サービス情報詳細）execute」        <BR>
     *         具体位置    :  1.5 getMail(String, String, String)    <BR>
     *         対象のメールが存在するかチェックする。  <BR>
     *        （getメール( )==nullの場合、[データ不整合]として例外をスローする。）  
     * <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00859           <BR>
     * =============================================== <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C4F2C0320
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
                
        WEB3GentradeMailInfo l_gentradeMailInfo = null;      
        
        if(!(l_request instanceof WEB3AdminMailInfoDetailsRequest))  //メール情報詳細リクエスト
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80018.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3AdminMailInfoDetailsRequest l_mailInfoDetailsRequest = (WEB3AdminMailInfoDetailsRequest) l_request;

        // (1.1)入力チェック
        l_mailInfoDetailsRequest.validate();

        // (1.2)管理者権限チェック
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        // (1.3)validate権限(String, boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, false);

        // (1.4)get証券会社コード()
        String l_institutionCode = l_administartor.getInstitutionCode();

        // (1.5)getMail()
        l_gentradeMailInfo = WEB3AdminMailInfoManager.getMail(l_institutionCode, l_mailInfoDetailsRequest.sendMailDiv, l_mailInfoDetailsRequest.discernId);

        if (l_gentradeMailInfo == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00859.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00859,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // (1.6)createResponse()
        WEB3AdminMailInfoDetailsResponse l_mailInfoDetailsResponse =(WEB3AdminMailInfoDetailsResponse)l_request.createResponse( ); 
        
        // (1.7)レスポンス・セット
        l_mailInfoDetailsResponse.sendMailDiv = l_gentradeMailInfo.getSendmailDiv();
        l_mailInfoDetailsResponse.discernId = l_gentradeMailInfo.getDiscernmentId();
        l_mailInfoDetailsResponse.mailName = l_gentradeMailInfo.getMailName();
        l_mailInfoDetailsResponse.mailFrom = l_gentradeMailInfo.getMailSender();
        l_mailInfoDetailsResponse.sendAddress = l_gentradeMailInfo.getSendAddress();
        l_mailInfoDetailsResponse.mailSubject = l_gentradeMailInfo.getSubject();
        l_mailInfoDetailsResponse.mailHeader =  l_gentradeMailInfo.getMailHeader();
        l_mailInfoDetailsResponse.mailBody =  l_gentradeMailInfo.getMailText();
        l_mailInfoDetailsResponse.mailFooter = l_gentradeMailInfo.getMailFooter();
        
        MailInfoRow l_mailInfoRow = (MailInfoRow )l_gentradeMailInfo.getDataSourceObject();
        
        l_mailInfoDetailsResponse.lastUpdater = l_mailInfoRow.getLastUpdater();
        l_mailInfoDetailsResponse.lastUpdateTime = l_mailInfoRow.getLastUpdatedTimestamp();
        
        
        log.exiting(STR_METHOD_NAME);
        return l_mailInfoDetailsResponse;
    }
}
@
