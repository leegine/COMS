head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 問合せ管理お問合せ入力サービスImpl(WEB3FaqInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.faq.WEB3Faq;
import webbroker3.faq.message.WEB3FaqCompleteRequest;
import webbroker3.faq.message.WEB3FaqCompleteResponse;
import webbroker3.faq.message.WEB3FaqConfirmRequest;
import webbroker3.faq.message.WEB3FaqConfirmResponse;
import webbroker3.faq.message.WEB3FaqInputRequest;
import webbroker3.faq.message.WEB3FaqInputResponse;
import webbroker3.faq.service.delegate.WEB3FaqInputService;
import webbroker3.faq.service.delegate.WEB3FaqNumberService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.MailInfoDao;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (問合せ管理お問合せ入力サービスImpl)<BR>
 * 問合せ管理お問合せ入力サービス実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3FaqInputServiceImpl implements WEB3FaqInputService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqInputServiceImpl.class);   
        
    /**
     * @@roseuid 41C25C8B005D
     */
    public WEB3FaqInputServiceImpl() 
    {
     
    }
    
    /**
     * 問合せ入力処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、問合せ管理お問合せ入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、問合せ管理お問合せ確認リクエストの場合 <BR>
     * 　@－validate問合せ()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、問合せ管理お問合せ完了リクエストの場合 <BR>
     * 　@－submit問合せ()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41ABFA870312
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3FaqInputRequest)
        {
            l_response = getInputScreen((WEB3FaqInputRequest)l_request);
        }
        else if (l_request instanceof WEB3FaqConfirmRequest)
        {
            l_response = validateFaq((WEB3FaqConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3FaqCompleteRequest)
        {
            l_response = submitFaq((WEB3FaqCompleteRequest)l_request);
        }
        else
        {
            log.error("パラメータタイプ不正。");
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request
                );
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「問合せ管理（問合せ入力）get入力画面」参照。 <BR>
     * @@param l_request - 問合せ管理お問合せ入力リクエストデータオブジェクト
     * 
     * 
     * @@return WEB3FaqInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41ABFA870332
     */
    protected WEB3FaqInputResponse getInputScreen(WEB3FaqInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3FaqInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //受付時間チェックを行う。 
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //レスポンスデータを生成する。 
        WEB3FaqInputResponse l_response = (WEB3FaqInputResponse)l_request.createResponse();
        
        //ログインセッションより口座ＩＤを取得する。 
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        try
        {
            if (l_opLoginSec.isAccountIdSet())
            {
                //口座ＩＤ（accountId）が取得できた場合、ログイン済みと判断し、
                //顧客の情報をレスポンスにセットする。 
            
                long l_lngAccountId = l_opLoginSec.getAccountId();
            
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                AccountManager l_accountMgr = l_finApp.getAccountManager();

                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            
                //部店コード：　@顧客.顧客行.部店コード
                l_response.branchCode = l_mainAccountRow.getBranchCode();            

                //顧客コード：　@顧客.get表示顧客コード()
                l_response.accountCode = l_mainAccount.getDisplayAccountCode();
            
                //顧客名：　@顧客.get顧客表示名()
                l_response.accountName = l_mainAccount.getDisplayAccountName();
            
                //メールアドレス：　@顧客.顧客行.emailアドレス
                l_response.mailAddress = l_mainAccountRow.getEmailAddress();
            }               
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
                );
        }
        catch (IllegalSessionStateException l_ex)
        {
            log.debug("未ログインの場合",l_ex);
            l_response.branchCode = l_request.branchCode;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate問合せ)<BR>
     * 問合せ入力確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>  
     * 「問合せ管理（問合せ入力）validate問合せ」参照。 <BR>
     * <BR>
     * =============================================== <BR>
     *          シーケンス図 : お問合せ入力 / 問合せ管理（問合せ入力）validate問合せ <BR>
     *          具体位置     : 1.6 メールアドレスとして適切な値でない場合（isMailAddress() == false）、<BR>
     *                         例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00777 <BR>
     * =============================================== <BR>
     * @@param l_request - 問合せ管理お問合せ確認リクエストデータオブジェクト
     * @@return WEB3FaqConfirmResponse
     * @@roseuid 41ABFA870351
     */
    protected WEB3FaqConfirmResponse validateFaq(WEB3FaqConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateFaq(WEB3FaqConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータの整合性をチェックする。 
        l_request.validate();

        //受付時間チェックを行う。 
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //ログインセッションより口座ＩＤを取得する。 
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        //口座ＩＤが取得できなかった場合（未ログインの場合）            
        //未ログインの場合、メールアドレスの必須チェックを行う。            
        try
        {
              
            if (!l_opLoginSec.isAccountIdSet())
            {
                l_request.faqInfo.validateMailAddress();
                //メールアドレスとして適切な文字列かを判定する。
                if (!WEB3StringTypeUtility.isMailAddress(l_request.faqInfo.mailAddress))
                {
                    //メールアドレスとして適切な値でない場合（isMailAddress() == false）、例外をスローする。 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                        this.getClass().getName() + STR_METHOD_NAME,
                        "メールアドレスとして適切ではありません。「メールアドレス」 = " + l_request.faqInfo.mailAddress);  
                }
            }
            else
            {
                if ((l_request.faqInfo.subject == null) || ( "".equals(l_request.faqInfo.subject)))
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00871,
                        this.getClass().getName() + STR_METHOD_NAME
                        );
                }
            }
                                      
        }
        catch(IllegalSessionStateException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            l_request.faqInfo.validateMailAddress();
            //メールアドレスとして適切な文字列かを判定する。
            if (!WEB3StringTypeUtility.isMailAddress(l_request.faqInfo.mailAddress))
            {
                //メールアドレスとして適切な値でない場合（isMailAddress() == false）、例外をスローする。 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレスとして適切ではありません。「メールアドレス」 = " + l_request.faqInfo.mailAddress);  
            }
        }
                
        //レスポンスデータを生成する。 
        WEB3FaqConfirmResponse l_response = (WEB3FaqConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * (submit問合せ)<BR>
     * 問合せ入力完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「問合せ管理（問合せ入力）submit問合せ」参照。 <BR>
     * <BR>
     * =============================================== <BR>
     *          シーケンス図 : お問合せ入力 / 問合せ管理（問合せ入力）submit問合せ <BR>
     *          具体位置     : 1.6 メールアドレスとして適切な値でない場合（isMailAddress() == false）、<BR>
     *                         例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00777 <BR>
     * =============================================== <BR>
     * @@param l_request - 問合せ管理お問合せ完了リクエストデータオブジェクト
     * @@return webbroker3.faq.message.WEB3FaqCompleteResponse
     * @@roseuid 41ABFA880015
     */
    protected WEB3FaqCompleteResponse submitFaq(WEB3FaqCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitFaq(WEB3FaqCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータの整合性をチェックする。 
        l_request.validate();

        //受付時間チェックを行う。 
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //ログインセッションより口座ＩＤを取得する。 
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        try
        {
            if (!l_opLoginSec.isAccountIdSet())
            {
                //口座ＩＤが取得できなかった場合（未ログインの場合）            
                //未ログインの場合、メールアドレスの必須チェックを行う。
                l_request.faqInfo.validateMailAddress();
                //メールアドレスとして適切な文字列かを判定する。
                if (!WEB3StringTypeUtility.isMailAddress(l_request.faqInfo.mailAddress))
                {
                    //メールアドレスとして適切な値でない場合（isMailAddress() == false）、例外をスローする。 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                        this.getClass().getName() + STR_METHOD_NAME,
                        "メールアドレスとして適切ではありません。「メールアドレス」 = " + l_request.faqInfo.mailAddress);  
                }
            }

        }
        catch (IllegalSessionStateException l_ex)
        {
            log.debug("未ログインの場合",l_ex);
            l_request.faqInfo.validateMailAddress();
            //メールアドレスとして適切な文字列かを判定する。
            if (!WEB3StringTypeUtility.isMailAddress(l_request.faqInfo.mailAddress))
            {
                //メールアドレスとして適切な値でない場合（isMailAddress() == false）、例外をスローする。 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレスとして適切ではありません。「メールアドレス」 = " + l_request.faqInfo.mailAddress);  
            }
            
        }


        
        WEB3Faq l_faq = new WEB3Faq();
        
        //証券会社コードをセットする。
        l_faq.setInstitutionCode(l_request.institutionCode);
        

        //口座ＩＤが取得できた場合
        try
        {
            l_opLoginSec.getAccountId(); 
            
            long l_lngAccountId = l_opLoginSec.getAccountId();
        
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);
            
            //部店コードをセットする。
            l_faq.setBranchCode(l_mainAccount.getBranch().getBranchCode());            

            //顧客コードをセットする。
            l_faq.setAccountCode(l_mainAccount.getAccountCode());                                
        }
        catch (IllegalSessionStateException l_ex)
        {
            //口座ＩＤが取得できなかった場合
            //部店コードに 000（：指定なし）をセットする。 
            l_faq.setBranchCode(WEB3BranchCodeDef.DEFAULT);    
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
                );
        }            
    
        //顧客名（漢字）をセットする。 
        l_faq.setName(l_request.faqInfo.accountName);
        
        //メールアドレスをセットする。 
        l_faq.setEmailAddress(l_request.faqInfo.mailAddress);
        
        //件名をセットする。
        l_faq.setSubject(l_request.faqInfo.subject);
        
        //問合せ内容をセットする。
        l_faq.setFaqText(l_request.faqInfo.faqText);
        
        //機@能ＩＤをセットする。 
        l_faq.setTransactionId(l_request.faqInfo.transactionId);
        
        //問合せコードを採番する。 
        WEB3FaqNumberService l_faqNumberService =
            (WEB3FaqNumberService)Services.getService(WEB3FaqNumberService.class);
        String l_strFaqNumber = l_faqNumberService.getNewFaqNumber(l_request.institutionCode);
        
        //問合せ管理行をDBにinsertする。
        l_faq.saveNewFaq(l_strFaqNumber);
        
        //メール送信テーブルに行を挿入する。
        try
        {
            MailProcParams l_mailProcParams = new MailProcParams();
            //証券会社コード = 問合せテーブル.証券会社コード            
            l_mailProcParams.setInstitutionCode(l_faq.getInstitutionCode());
                     
            //部店コード = 問合せテーブル.部店コード
            l_mailProcParams.setBranchCode(l_faq.getBranchCode());
           
            //送信メール区分 = 問合せ管理（0301）
            l_mailProcParams.setSendmailDiv(WEB3SendmailDivDef.FAQ_INPUT);
            
            //識別ID = 問合せテーブル.機@能ＩＤ
            l_mailProcParams.setDiscernmentId(l_faq.getTransactionId());
            
            //口座コード = 問合せテーブル.口座コード
            //（口座コード == null）の場合、"0000000"をセット。
            if (l_faq.getAccountCode() != null)
            {
                l_mailProcParams.setAccountCode(l_faq.getAccountCode());
            }
            else
            {
                l_mailProcParams.setAccountCode("0000000");
            }

            //メールID = 問合せテーブル.問合せコードを数値に変換した値。
            l_mailProcParams.setMailId(Long.parseLong(l_faq.getFaqNumber()));
            
            //年月日１ = 問合せテーブル.作成日時
            l_mailProcParams.setDate1(l_faq.getFaqDatetime());
            
            //名称1 =  問合せテーブル.顧客名（漢字）
            l_mailProcParams.setName1(l_faq.getName());
            
            //名称2 = 問合せテーブル.機@能ＩＤ
            l_mailProcParams.setName2(l_faq.getTransactionId());

            //電子メール送信ステイタス = 0：未処理（Email未送信）
            l_mailProcParams.setStatus(WEB3StatusDef.NOT_DEAL);
          
            //emailアドレス = メールテーブル.送信先アドレス
            //※メールテーブル：　@証券会社コード，送信メール区分，識別ＩＤに該当する行の値を使用。
            
            try
            {
                MailInfoRow l_mailInfoRow = (MailInfoRow)MailInfoDao.findRowByPk(
                    l_mailProcParams.institution_code,
                    l_mailProcParams.sendmail_div,
                    l_mailProcParams.discernment_id
                    );
                
                l_mailProcParams.setEmailAddress(l_mailInfoRow.getSendAddress());
            }
            catch (DataFindException l_ex)
            {
                //該当データがない場合は、例外をスローする。
                log.debug("該当データが存在しない。" + 
                    "「証券会社コード」 = " + l_mailProcParams.institution_code +
                    "「送信メール区分」 = " + l_mailProcParams.sendmail_div + 
                    "「識別ＩＤ」 = " + l_mailProcParams.discernment_id);                    
            }
            
            //送信emailアドレス = 問合せテーブル.emailアドレス
            l_mailProcParams.setSendEmailAddress(l_faq.getEmailAddress());
            
            //件名 = 問合せテーブル.件名
            l_mailProcParams.setSubject(l_faq.getSubject());
            
            //メール本文 = 問合せテーブル.問合せ内容
            l_mailProcParams.setMailText(l_faq.getFaqText());
            
            //削除フラグ = 0:FALSE（有効）
            l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
            
            //処理日時
            Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
            
            //作成日時 = 処理日時
            l_mailProcParams.setCreatedTimestamp(l_tsProcessDate);
            
            //更新日時 = 処理日時
            l_mailProcParams.setLastUpdatedTimestamp(l_tsProcessDate);
            
                  
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                
            l_queryProcessor.doInsertQuery(l_mailProcParams);
            
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
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
        
		// 問合せ受付メールを挿入する。
		try
		{
			MailInfoRow l_mailInfoRow = (MailInfoRow)MailInfoDao.findRowByPk(
				l_faq.getInstitutionCode(),
				WEB3SendmailDivDef.FAQ_COMPLETE,
				l_faq.getTransactionId()
				); 
					
			MailProcParams l_mailProcParams = new MailProcParams();
			
			//証券会社コード = 問合せテーブル.証券会社コード            
			l_mailProcParams.setInstitutionCode(l_faq.getInstitutionCode());
                    
			//部店コード = 問合せテーブル.部店コード
			l_mailProcParams.setBranchCode(l_faq.getBranchCode());
          
			//送信メール区分 = 問合せ受付（0302）
			l_mailProcParams.setSendmailDiv(WEB3SendmailDivDef.FAQ_COMPLETE);
            
			//識別ID = 問合せテーブル.機@能ＩＤ
			l_mailProcParams.setDiscernmentId(l_faq.getTransactionId());
            
			//口座コード = 問合せテーブル.口座コード
			//（口座コード == null）の場合、"0000000"をセット。
			if (l_faq.getAccountCode() != null)
			{
				l_mailProcParams.setAccountCode(l_faq.getAccountCode());
			}
			else
			{
				l_mailProcParams.setAccountCode("0000000");
			}
			
			//メールID = 問合せテーブル.問合せコードを数値に変換した値。
			l_mailProcParams.setMailId(Long.parseLong(l_faq.getFaqNumber()));
            
			//年月日１ = 問合せテーブル.作成日時
			l_mailProcParams.setDate1(l_faq.getFaqDatetime());
            
			//名称1 =  問合せテーブル.顧客名（漢字）
			l_mailProcParams.setName1(l_faq.getName());
           
			//名称2 = 問合せテーブル.機@能ＩＤ
			l_mailProcParams.setName2(l_faq.getTransactionId());

			//電子メール送信ステイタス = 0：未処理（Email未送信）
			l_mailProcParams.setStatus(WEB3StatusDef.NOT_DEAL);
		
			//emailアドレス = 問合せテーブル.emailアドレス
			l_mailProcParams.setEmailAddress(l_faq.getEmailAddress());
		
			//削除フラグ = 0:FALSE（有効）
			l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
		
			//処理日時
			Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
           
			//作成日時 = 処理日時
			l_mailProcParams.setCreatedTimestamp(l_tsProcessDate);
           
			//更新日時 = 処理日時
			l_mailProcParams.setLastUpdatedTimestamp(l_tsProcessDate);
                     
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
               
			l_queryProcessor.doInsertQuery(l_mailProcParams);
					              
		}
		catch (DataFindException l_ex)
		{
			//該当データがない場合は、問合せ受付メールを挿入しない。
			log.debug("該当データが存在しない場合、問合せ受付メールは挿入しない。" +
				"「証券会社コード」 = " + l_faq.getInstitutionCode() +
				"「送信メール区分」 = " + WEB3SendmailDivDef.FAQ_COMPLETE +
				"「識別ＩＤ」 = " +  l_faq.getTransactionId());
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
        

        //レスポンスデータを生成する。 
        WEB3FaqCompleteResponse l_response = (WEB3FaqCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;   
    }
}
@
