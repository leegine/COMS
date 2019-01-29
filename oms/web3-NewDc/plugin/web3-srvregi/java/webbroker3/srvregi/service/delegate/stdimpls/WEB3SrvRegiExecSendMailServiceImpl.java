head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiExecSendMailServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用確認メール送信サービスImpl(WEB3SrvRegiExecSendMailServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 李頴淵 新規作成
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3ResendStatusDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.gentrade.data.MailProcRow;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiExecSendMailService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用確認メール送信サービスImpl)<BR>
 * @@author 李頴淵
 * @@version 1.0 
 * 
 * サービス利用確認メール送信サービス実装クラス<BR>
 */
public class WEB3SrvRegiExecSendMailServiceImpl implements WEB3SrvRegiExecSendMailService 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3SrvRegiExecSendMailServiceImpl.class);
    
    /**
     * @@roseuid 416F392902AF
     */
    public WEB3SrvRegiExecSendMailServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用申込確認メール送信処理を行う。<BR>
     * <BR>
     * メール送信テーブルにInsert処理を行う。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR> 
     *「サービス利用申込_メール送信テーブル仕様.xls」参照。<BR>
     * @@param l_serviceRegist - (サービス申込登録)<BR>
     * サービス申込登録オブジェクト<BR>
     * @@roseuid 41492D06032F
     */
    public void sendMailProcess(WEB3GentradeSrvRegiApplication l_serviceRegist) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sendMailProcess(WEB3GentradeSrvRegiApplication)";
        log.entering(STR_METHOD_NAME);
        MailProcParams l_mailProcParams = new MailProcParams();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        
        OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);

        try
        {
            //証券会社コード
            String l_strInstitutionId = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.INSTITUTION_ID);
            String l_strInstitutionCode = 
                l_accountManager.getInstitution(Long.parseLong(l_strInstitutionId)).getInstitutionCode(); //NotFoundException
            l_mailProcParams.setInstitutionCode(l_strInstitutionCode);
            
            //部店コード
            String l_strBranchId = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.BRANCH_ID);
            String l_strBranchCode = l_accountManager.getBranch(Long.parseLong(l_strBranchId)).getBranchCode(); //NotFoundException
            l_mailProcParams.setBranchCode(l_strBranchCode);
            
            //送信メール区分
            l_mailProcParams.setSendmailDiv(WEB3SendmailDivDef.SRVREGI_CONFIRM_MAIL);
        
            //識別ID
            String l_strSrvDiv = l_serviceRegist.getSrvDiv();
            l_mailProcParams.setDiscernmentId(l_strSrvDiv);
        
            //口座コード
            String l_strAccountCode = l_serviceRegist.getAccountCode();
            l_mailProcParams.setAccountCode(l_strAccountCode);
        
            //メールID
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            String l_strWhere =
                    "institution_code = ? and " +
                    "branch_code = ? and " +
                    "sendmail_div = ? and " +
                    "discernment_id = ? and " +
                    "account_code = ? ";
            
            Object l_bindVars[] =
                {   l_strInstitutionCode,
                    l_strBranchCode,
                    "0101", 
                    l_strSrvDiv,
                    l_strAccountCode};
                    
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                MailProcRow.TYPE,
                l_strWhere,
                "mail_id desc",
                null,
                l_bindVars);
                
            long l_lngMailId = 0;
            if(l_lisRows.size() == 0)
            {
                log.debug("l_lngMailId = 0;");
                l_lngMailId = 0;
            }
            else
            {
                MailProcRow l_mailProcRow = 
                    (MailProcRow)l_lisRows.get(0);
                l_lngMailId = l_mailProcRow.getMailId() + 1;
                log.debug("l_lngMailId = l_mailProcRow.getMailId() + 1;");
            }
            
            l_mailProcParams.setMailId(l_lngMailId);            
            
            //年月日１
            l_mailProcParams.setDate1(l_serviceRegist.getAppliDate());
        
            //年月日２
            l_mailProcParams.setDate2(l_serviceRegist.getAppliStartDate());
        
            //年月日３
            l_mailProcParams.setDate3(l_serviceRegist.getAppliEndDate());
        
            //年月日４
            l_mailProcParams.setDate4(l_serviceRegist.getPaymentDate());
        
            //数量
            l_mailProcParams.setQuantity(1);
        
            //金額
            l_mailProcParams.setAmount(l_serviceRegist.getUseAmt());
        
            //ID
            l_mailProcParams.setOrderId(l_serviceRegist.getRegistId());
            
            //名称１
			//getサービスマスター
			WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
			WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
				l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_serviceRegist.getSrvDiv(), false);
			l_mailProcParams.setName1(l_srvRegiServiceMaster.getSrvName());
        
            //区分
            l_mailProcParams.setDivision(l_serviceRegist.getPaymentDiv());
        
            //電子メール送信ステイタス
            l_mailProcParams.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);
        
            //電子メール送信日時
            l_mailProcParams.setSendProcessDateTime(null);
        
            //再送区分
            l_mailProcParams.setResendStatus(WEB3ResendStatusDef.DEFAULT);
        
            //電子メール再送日時
            l_mailProcParams.setResendProcessDateTime(null);
        
            //emailアドレス
			long l_lngAccountId = l_opLoginSec.getAccountId();
			MainAccountRow l_mainAccountRow = (MainAccountRow)l_accountManager.getMainAccount(l_lngAccountId).getDataSourceObject();
            l_mailProcParams.setEmailAddress(l_mainAccountRow.getEmailAddress());
        
            //削除フラグ
            l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
                
            Timestamp l_currentTime = l_finApp.getTradingSystem().getSystemTimestamp();  
            //作成日時
            l_mailProcParams.setCreatedTimestamp(l_currentTime);
        
            //更新日時
            l_mailProcParams.setLastUpdatedTimestamp(l_currentTime);
            
            l_queryProcesser.doInsertQuery(l_mailProcParams);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
