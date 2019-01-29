head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.23.04.50.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1cc4d897c1210b4;
filename	WEB3AdminMailInfoRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報登録サービスImpl(WEB3AdminMailInfoRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.gentrade.data.MailProcRow;
import webbroker3.mailinfo.WEB3AdminMailInfoClientRequestService;
import webbroker3.mailinfo.WEB3AdminMailInfoManager;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (メール情報登録サービスImpl)<BR>
 * メール情報登録サービス実装クラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0.1 
 */
public class WEB3AdminMailInfoRegistServiceImpl 
        extends WEB3AdminMailInfoClientRequestService 
            implements WEB3AdminMailInfoRegistService 
{    
    /**
     * ログ出力ユーティリティ。
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminMailInfoRegistServiceImpl.class);            

    /**
     * @@roseuid 416F1DC90271
     */
    public WEB3AdminMailInfoRegistServiceImpl() 
    {
     
    }
    
    /**
     * メール情報登録処理を行う。<BR>
     * <BR>
     * リクエストデータによって、validateメール情報登録( )または<BR>
     * submitメール情報登録( )をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C2C8003A9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if(l_request instanceof WEB3AdminMailInfoRegistCompleteRequest)
        {
            l_response = this.submitMailInfoRegist((WEB3AdminMailInfoRegistCompleteRequest)l_request);
           
        }
        else if (l_request instanceof WEB3AdminMailInfoRegistConfirmRequest)
        {
            l_response = this.validateMailInfoRegist((WEB3AdminMailInfoRegistConfirmRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + 
                WEB3ErrorCatalog.SYSTEM_ERROR_80018.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);        
        return l_response;    
    }
    
    /**
     * (validateメール情報登録)<BR>
     * メール情報登録審査処理を行う。<BR>
     * <BR>
     * シーケンス図「（メール情報登録）validate」参照<BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  「（メール情報登録）validate」          <BR>
     *         具体位置    :  1.6 isDIR管理者( )                     <BR>
     *         ログイン管理者がDIR管理者かどうかチェックする。<BR>
     *        （isDIR管理者( )==falseの場合、例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857           <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :   「（メール情報登録）validate」         <BR>
     *         具体位置    :  1.8 getMail(String, String, String)    <BR>
     *         登録対象のメールが既に登録されていないかチェックする。<BR>
     *         （getメール( )の戻り値!=nullの場合、<BR>
     *          [プログラムID・識別ID重複エラー]例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00860          <BR>
     * =============================================== <BR>
     * @@param  l_request - (リクエストデータ)<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmResponse
     * @@roseuid 413C2CE601C5
     */
    protected WEB3AdminMailInfoRegistConfirmResponse validateMailInfoRegist(
                    WEB3AdminMailInfoRegistConfirmRequest  l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
             " validateMailInfoRegist(WEB3AdminMailInfoRegistConfirmRequest  l_request)";
        log.entering(STR_METHOD_NAME);         
        
        WEB3AdminMailInfoRegistConfirmResponse l_response = null;
        WEB3Administrator l_administartor = null;    
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.4 isMailAddress(リクエストデータ.差出人)
        if (l_request.mailFrom != null)
        {
            boolean l_blnAddress = WEB3StringTypeUtility.isMailAddress(l_request.mailFrom);
            if(!l_blnAddress)
            {
                log.error(STR_METHOD_NAME +
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
       
        //1.5 リクエストデータ.送信先メールアドレス≠nullの場合にチェックをする
        if (l_request.sendAddress != null )
        {
            //1.5.1 区切り文字単位ごとのString[]に分割する。
            String l_strCharacter = ",";
            String[] l_lisSendAddress = l_request.sendAddress.split(l_strCharacter);
            
            //1.5.2 String[]＞10件の場合、例外をスローする。
            if(l_lisSendAddress.length > 10)
            {
                log.error(STR_METHOD_NAME + 
                    WEB3ErrorCatalog.BUSINESS_ERROR_00877.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00877, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.5.3 String[]の要素数分、1件ごとに文字数チェック、メールアドレスチェックを行なう。
            for(int i = 0; i < l_lisSendAddress.length; ++i)
            {
                //1.5.3.1 1件の文字数＞100byteの場合、例外をスローする。
                if(WEB3StringTypeUtility.getByteLength(l_lisSendAddress[i]) > 100)
                {
                    log.error(STR_METHOD_NAME + 
                        WEB3ErrorCatalog.BUSINESS_ERROR_00877.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00877, 
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                
                //1.5.3.2 isMailAddress(String[index])
                boolean l_blnmailAddress = WEB3StringTypeUtility.isMailAddress(l_lisSendAddress[i]);
                if (!l_blnmailAddress)
                {
                    log.error(STR_METHOD_NAME + 
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        
        //1.6 isDIR管理者( )
        boolean l_blnDir = l_administartor.isDirAdministrator();
        if (!l_blnDir)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.7 get証券会社コード( )
        String l_strInstCode = l_administartor.getInstitutionCode();
                       
        //1.8  getメール(String, String, String)
        WEB3GentradeMailInfo l_mail = null;
        l_mail = WEB3AdminMailInfoManager.getMail(
            l_strInstCode, l_request.sendMailDiv, l_request.discernId);
        if (l_mail != null)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00860.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00860,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        //1.9 createレスポンス( )
        l_response = (WEB3AdminMailInfoRegistConfirmResponse)l_request.createResponse();
        
        //1.10 警告メッセージの設定
        //     取得した管理者オブジェクトにメールアドレスが登録されていない場合
        //     1をセットする。それ以外はnullをセットする。
        AdministratorParams l_adminParams =
            (AdministratorParams)l_administartor.getDataSourceObject();
        if(l_adminParams.getEmailAddress() == null)
        {
            l_response.warnMessage = "1";
        }
        else
        {
            l_response.warnMessage = null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submitメール情報登録)<BR>
     * メール情報登録処理を行う。<BR>
     * <BR>
     * シーケンス図「（メール情報登録）submit」参照<BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （メール情報登録）submit               <BR>
     *         具体位置    :  1.7 isDIR管理者( )                     <BR>
     *         ログイン管理者がDIR管理者かどうかチェックする。  <BR>
     * （isDIR管理者( )==falseの場合、[権限エラー]として例外をスローする。）  <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857           <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （メール情報登録）submit               <BR>
     *         具体位置    :  1.9 getメール(String, String, String)   <BR>
     *         登録対象のメールが既に登録されていないかチェックする。<BR>
     *         （getメール( )の戻り値!=nullの場合、<BR>
     *          [プログラムID・識別ID重複エラー]例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00860          <BR>
     * =============================================== <BR>
     * @@param  l_request - (リクエストデータ)<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C2CF0038A
     */
    protected WEB3AdminMailInfoRegistCompleteResponse submitMailInfoRegist(
                    WEB3AdminMailInfoRegistCompleteRequest  l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitMailInfoRegist(WEB3AdminMailInfoRegistCompleteRequest  l_request)";
        log.entering(STR_METHOD_NAME);
     
        WEB3AdminMailInfoRegistCompleteResponse l_response = null;
        WEB3Administrator l_administartor = null;
           
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.4 validate取引パスワード(パスワード : String)
        l_administartor.validateTradingPassword(l_request.password);
        
        //1.5 isMailAddress(リクエストデータ.差出人)
        if (l_request.mailFrom != null)
        {
            boolean l_blnAddress = WEB3StringTypeUtility.isMailAddress(l_request.mailFrom);
            if (!l_blnAddress)
            {
                log.error(STR_METHOD_NAME + 
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //1.6 リクエストデータ.送信先メールアドレス≠nullの場合にチェックをする
        if (l_request.sendAddress != null )
        {
            //1.6.1 区切り文字単位ごとのString[]に分割する。
            String l_strCharacter = ",";
            String[] l_lisSendAddress = l_request.sendAddress.split(l_strCharacter);
            
            //1.6.2 String[]＞10件の場合、例外をスローする。
            if(l_lisSendAddress.length > 10)
            {
                log.error(STR_METHOD_NAME + 
                    WEB3ErrorCatalog.BUSINESS_ERROR_00877.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00877, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.6.3 String[]の要素数分、1件ごとに文字数チェック、メールアドレスチェックを行なう。
            for(int i = 0; i < l_lisSendAddress.length; ++i)
            {
                //1.6.3.1 1件の文字数＞100byteの場合、例外をスローする。
                if(WEB3StringTypeUtility.getByteLength(l_lisSendAddress[i]) > 100)
                {
                    log.error(STR_METHOD_NAME + 
                        WEB3ErrorCatalog.BUSINESS_ERROR_00877.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00877, 
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                
                //1.6.3.2 isMailAddress(String[index])
                boolean l_mailAddress = WEB3StringTypeUtility.isMailAddress(l_lisSendAddress[i]);
                if (!l_mailAddress)
                {
                    log.error(STR_METHOD_NAME + 
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777.error_message);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        
        //1.7 isDIR管理者( )
        boolean l_blnDir = l_administartor.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        //1.8 get証券会社コード( )
        String l_strInstCode = l_administartor.getInstitutionCode();
        
        //1.9  getメール(String, String, String)
        WEB3GentradeMailInfo l_mail = null;
        l_mail = WEB3AdminMailInfoManager.getMail(
            l_strInstCode, l_request.sendMailDiv, l_request.discernId);
        if (l_mail != null)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00860.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00860,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        else
        {
            //1.10 createNewメール(証券会社コード : String, 送信メール区分 : String, 識別ID : String)
            WEB3GentradeMailInfo l_genMail = WEB3GentradeMailInfo.createNewMail(
                    l_strInstCode, l_request.sendMailDiv, l_request.discernId);
            
            //1.11 setメール名称(メール名称 : String)
            l_genMail.setMailName(l_request.mailName);
        
            //1.12 set差出人(差出人 : String)
            l_genMail.setMailSender(l_request.mailFrom);
            
            //1.13 set件名(件名 : String)
            l_genMail.setSubject(l_request.mailSubject);
            
            //1.14 setメールヘッダー(メールヘッダー : String)
            l_genMail.setMailHeader(l_request.mailHeader);
            
            //1.15 setメール本文(メール本文 : String)
            l_genMail.setMailText(l_request.mailBody);
            
            //1.16 setメールヘッダー(メールヘッダー : String)
            l_genMail.setMailFooter(l_request.mailFooter);
            
            //1.17 set送信先メールアドレス(メールアドレス: String)
            l_genMail.setSendAddress(l_request.sendAddress);
            
            //1.18 saveNewメール( )
            l_genMail.saveNewMail();
        }
        
        //1.19 管理者オブジェクトのメールアドレス！＝nullの場合
        //     確認メールを送信する。
        AdministratorParams l_adminParams =
            (AdministratorParams)l_administartor.getDataSourceObject();
        String l_strEmailAddress = l_adminParams.getEmailAddress();                
        if(l_strEmailAddress != null)
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
 
                MailProcParams l_mailProcParams = new MailProcParams();
                
                l_mailProcParams.setInstitutionCode(l_strInstCode);
                l_mailProcParams.setBranchCode(l_administartor.getBranchCode());
                l_mailProcParams.setSendmailDiv(l_request.sendMailDiv);
                if (l_request.discernId != null)
                {
                    l_mailProcParams.setDiscernmentId(l_request.discernId);
                }
                else
                {
                    l_mailProcParams.setDiscernmentId("----");
                }                
                l_mailProcParams.setAccountCode("0000000");
                
                //Get MailID
                long l_lngMailId = 0;
                try
                {
                    QueryProcessor l_queryProcessorFind;
                    l_queryProcessorFind = Processors.getDefaultProcessor();
                    List l_lisVars = new ArrayList();
                    l_lisVars.add(l_request.sendMailDiv);
                    l_lisVars.add(l_mailProcParams.getDiscernmentId());
                    l_lisVars.add(l_mailProcParams.getAccountCode());
                    List l_lisMailProc = l_queryProcessorFind.doFindAllQuery(
                        MailProcRow.TYPE,
                        "sendmail_div = ? and discernment_id = ? and account_code = ?",
                        "mail_id DESC",
                        null,
                        l_lisVars.toArray(),
                        1,
                        0                        
                    );
                    if (l_lisMailProc.size() > 0)
                    {
                        MailProcParams l_lmpParams = (MailProcParams)l_lisMailProc.get(0);
                        l_lngMailId = l_lmpParams.getMailId();
                    }                    
                }
                catch (DataFindException l_ex)
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
                l_mailProcParams.setMailId(l_lngMailId + 1);
                l_mailProcParams.setDate1(null);
                l_mailProcParams.setDate2(null);
                l_mailProcParams.setDate3(null);
                l_mailProcParams.setDate4(null);
                l_mailProcParams.setQuantity(null);
                l_mailProcParams.setAmount(null);
                l_mailProcParams.setOrderId(null);
                l_mailProcParams.setDivision(null);
                l_mailProcParams.setName1(null);
                l_mailProcParams.setName2(null);
                l_mailProcParams.setStatus("0");
                l_mailProcParams.setSendProcessDateTime(null);
                l_mailProcParams.setErrorCode(null);
                l_mailProcParams.setAdminMailDiv("1");
                l_mailProcParams.setResendStatus("0");                
                l_mailProcParams.setResendProcessDateTime(null);
               l_mailProcParams.setEmailAddress(l_strEmailAddress);
               
               if (l_request.mailFrom != null)
               {
                   l_mailProcParams.setSendEmailAddress(l_request.mailFrom);
               }
               else
               {
                   l_mailProcParams.setSendEmailAddress(l_strEmailAddress);   
               }
               
               l_mailProcParams.setSubject(null);
               l_mailProcParams.setMailText(null);
               l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
               l_mailProcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
               l_mailProcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
               l_queryProcessor.doInsertQuery(l_mailProcParams);
           }
           catch (DataException de)
           {
               log.error(STR_METHOD_NAME, de);
               throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
           }
        }
        //1.20 createレスポンス( )
        l_response = (WEB3AdminMailInfoRegistCompleteResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
