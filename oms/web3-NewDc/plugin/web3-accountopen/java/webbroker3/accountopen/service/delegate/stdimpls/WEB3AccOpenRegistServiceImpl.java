head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込サービスImpl(WEB3AccOpenRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 鄭海良(中訊) 新規作成
                 : 2006/08/14 張騰宇(中訊) 仕様変更 モデル 090
Revesion History : 2009/08/10 張騰宇(中訊) 仕様変更 モデル167
Revesion History : 2009/09/02 張騰宇(中訊) 仕様変更 モデル205 208 ＤＢ更新仕様 052
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.List;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.data.MailAddressRegiDao;
import webbroker3.accountopen.data.MailAddressRegiParams;
import webbroker3.accountopen.data.MailAddressRegiRow;
import webbroker3.accountopen.define.WEB3AccOpenAccountCodeAutoFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenAdminDivDef;
import webbroker3.accountopen.define.WEB3MailSendDivDef;
import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRegistService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRequestNumberService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3ValidateTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (口座開設申込サービスImpl)<BR>
 * 口座開設申込サービス実装クラス<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenRegistServiceImpl implements WEB3AccOpenRegistService 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenRegistServiceImpl.class);
    
    /**
     * @@roseuid 41B45E7302FD
     */
    public WEB3AccOpenRegistServiceImpl() 
    {
     
    }
    
    /**
     * 口座開設申込処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、口座開設申込入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、口座開設申込確認リクエストの場合 <BR>
     * 　@－validate申込()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、口座開設申込完了リクエストの場合<BR> 
     * 　@－submit申込()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 419C900600ED
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AccOpenApplyInputRequest)
        {
            WEB3AccOpenApplyInputResponse l_response = 
                getInputScreen((WEB3AccOpenApplyInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AccOpenApplyConfirmRequest)
        {
            WEB3AccOpenApplyConfirmResponse l_response = 
                validateRegist((WEB3AccOpenApplyConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AccOpenApplyCompleteRequest)
        {
            WEB3AccOpenApplyCompleteResponse l_response = 
                submitRegist((WEB3AccOpenApplyCompleteRequest)l_request);
        
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
     * 口座開設申込入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（申込）get入力画面」参照。 <BR>
     * ======================================================== <BR>
     * シーケンス図 ：口座開設申込 / 口座開設（申込）get入力画面 <BR>
     * 具体位置：メールアドレス登録行.emailアドレス！=リクエスト.メールアドレスの場合、エラーをスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_01789 <BR>
     * ========================================================== <BR>
     * ======================================================== <BR>
     * シーケンス図 ：口座開設申込 / 口座開設（申込）get入力画面 <BR>
     * 具体位置：リクエスト.メールアドレス登録ID　@=　@null 又は　@リクエスト.メールアドレス=　@null の場合、エラーをスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_03183 <BR>
     * ========================================================== <BR>
     * @@param l_request - 口座開設申込入力リクエストデータオブジェクト
     * 
     * 
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 419C8F7301B8
     */
    protected WEB3AccOpenApplyInputResponse getInputScreen(WEB3AccOpenApplyInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AccOpenApplyInputRequest)";
        log.entering(STR_METHOD_NAME );
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        long l_lngLoginId = 0;
        try
        {
            //1.2 getLoginId( )
            log.debug("getLoginId");
            
            l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();//IllegalSessionStateException
        }
        catch (IllegalSessionStateException l_e)
        {
            log.debug("can't get login info.");
        }
        if (l_lngLoginId != 0)
        {
            //1.3 ログインＩＤが取得できた場合、管理者入力と判断し権限チェックを実施する。
            //1.3.1 getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
            
            //1.3.2 validate権限(String, boolean)
            log.debug("validate権限");
            l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
            
            //1.3.3 validate部店権限(String)
            log.debug("validate部店権限");
            l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        }

        //1.4 validate注文受付可能( )
        log.debug("validate注文受付可能");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();//WEB3BaseException
        
        //1.5 createResponse( )
        WEB3AccOpenApplyInputResponse l_response = (WEB3AccOpenApplyInputResponse)l_request.createResponse();

        //isメールアドレス登録チェック実施
        boolean l_blnIsMailAddressRegiCheck = this.isMailAddressRegiCheck(l_request.institutionCode);

        if (l_blnIsMailAddressRegiCheck &&  !WEB3AccOpenAdminDivDef.ADMIN.equals(l_request.adminDiv))
        {
            // リクエスト.メールアドレス登録ID　@=　@null 又は　@リクエスト.メールアドレス=　@null の場合
            if (WEB3StringTypeUtility.isEmpty(l_request.mailAddressID) || WEB3StringTypeUtility.isEmpty(l_request.mailAddress))
            {
                log.debug("メールアドレス登録ID 又はメールアドレスが未入力です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_03183,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "メールアドレス登録ID 又はメールアドレスが未入力です。");
            }
            //getメールアドレス登録行
            MailAddressRegiParams l_mailAddressRegiParams =
                this.getMailAddressRegiParams(l_request.mailAddressID);

            //メールアドレス登録行.emailアドレス！=リクエスト.メールアドレスの場合、エラーをスローする。
            if (!l_request.mailAddress.equals(l_mailAddressRegiParams.getEmailAddress()))
            {
                log.debug("メールアドレスが確認用のものと一致しておりません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_01789,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "メールアドレスが確認用のものと一致しておりません。");
            }
            //メールアドレス登録チェック実施の場合
            //  姓　@　@　@　@　@　@　@　@　@＝　@getメールアドレス登録行()の戻り値.顧客姓（漢字）
            l_response.accountFamilyName = l_mailAddressRegiParams.getFamilyName();
            //  名　@　@　@　@　@　@　@　@　@＝　@getメールアドレス登録行()の戻り値.顧客名（漢字）
            l_response.accountName = l_mailAddressRegiParams.getGivenName();
            //  メールアドレス　@　@　@＝　@getメールアドレス登録行()の戻り値.emailアドレス
            l_response.mailAddress = l_mailAddressRegiParams.getEmailAddress();
            //  仲介業扱者コード　@　@＝　@getメールアドレス登録行()の戻り値.仲介業扱者コード
            l_response.brokerageCode = l_mailAddressRegiParams.getBrokerageTraderCode();
            //  口座区分　@　@　@　@　@　@＝　@getメールアドレス登録行()の戻り値.口座区分
            l_response.accountType = l_mailAddressRegiParams.getAccountDiv();
            //  リンク元判別コード　@＝　@getメールアドレス登録行()の戻り値.リンク元判別コード
            l_response.linkCode = l_mailAddressRegiParams.getLinkDistinctionCode();
        }
        //上記以外の場合
        //　@姓　@　@　@　@　@　@　@　@　@＝　@null
        //　@名　@　@　@　@　@　@　@　@　@＝　@null
        //　@メールアドレス　@　@　@＝　@null
        //　@仲介業扱者コード　@　@＝　@null
        //　@口座区分　@　@　@　@　@　@＝　@null
        //　@リンク元判別コード　@＝　@null
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate申込)<BR>
     * 口座開設申込確認処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（申込）validate申込」参照。 <BR>
     * @@param l_request - 口座開設申込確認リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 419C8F7301C8
     */
    protected WEB3AccOpenApplyConfirmResponse validateRegist(WEB3AccOpenApplyConfirmRequest l_request)
        throws  WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AccOpenApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3Administrator l_admin = null;
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        long l_lngLoginId = 0;
        try
        {
            //1.2 getLoginId( )
            log.debug("getLoginId");
            
            l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();//IllegalSessionStateException            
        }
        catch (IllegalSessionStateException l_e)
        {
            log.debug("can't get login info.");
        }
        if (l_lngLoginId != 0)
        {
            //1.3 ログインＩＤが取得できた場合、管理者入力と判断し権限チェックを実施する。
            //1.3.1 getInstanceFromログイン情報( )
            l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
            
            //1.3.2 validate権限(機@能カテゴリコード（=口座開設） : String, is更新（=true） : boolean)
            log.debug("validate権限");
            l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
            
            //1.3.3 validate部店権限(String)
            log.debug("validate部店権限");
            l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);//WEB3BaseException
        }
        
        //1.4 validate注文受付可能( )
        log.debug("validate注文受付可能");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();//WEB3BaseException
        
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);        
        
        //1.5 to口座開設見込客(口座開設申込情報)
        WEB3AccOpenExpAccountOpen l_expAccountOpen = l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);
        
        //1.6 validate口座開設申込情報(String,String)
        if (l_admin != null)
        {
            l_expAccountOpen.validateAccountOpenRegistInfo(
                WEB3ValidateTypeDef.ADMINISTRATOR_REGIST,
                WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
        }
        else
        {
            l_expAccountOpen.validateAccountOpenRegistInfo(
                WEB3ValidateTypeDef.DEFAULT,
                WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
        }
        
        //1.7 createResponse( )
        WEB3AccOpenApplyConfirmResponse l_response = (WEB3AccOpenApplyConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit申込)<BR>
     * 口座開設申込完了処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（申込）submit申込」参照。 <BR>
     * @@param l_request - 口座開設申込完了リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 419C8F7301CA
     */
    protected WEB3AccOpenApplyCompleteResponse submitRegist(WEB3AccOpenApplyCompleteRequest l_request) 
        throws  WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitRegist(WEB3AccOpenApplyCompleteRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3Administrator l_admin = null;
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        long l_lngLoginId = 0;
        try
        {
            //1.2 getLoginId( )
            log.debug("getLoginId");
            
            l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();//IllegalSessionStateException            
        }
        catch (IllegalSessionStateException l_e)
        {
            log.debug("can't get login info.");
        }
        if (l_lngLoginId != 0)
        {
            //1.3 ログインＩＤが取得できた場合、管理者入力と判断し権限チェックを実施する。
            //1.3.1 getInstanceFromログイン情報( )
            l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        
            //1.3.2 validate権限(機@能カテゴリコード（=口座開設） : String, is更新（=true） : boolean)
            log.debug("validate権限");
            l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
            //1.3.3 validate部店権限(String)
            log.debug("validate部店権限");
            l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);//WEB3BaseException

            //1.3.4 validate取引パスワード(String)
            l_admin.validateTradingPassword(l_request.password);//WEB3BaseException
        }

        
        //1.4 validate注文受付可能( )
        log.debug("validate注文受付可能");
        WEB3GentradeTradingTimeManagement.validateOrderAccept();//WEB3BaseException
        
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);        
        //1.5 to口座開設見込客(口座開設申込情報)
        WEB3AccOpenExpAccountOpen l_expAccountOpen = l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);

        //1.6 validate口座開設申込情報(String,String)
        if (l_admin != null)
        {
            l_expAccountOpen.validateAccountOpenRegistInfo(
                WEB3ValidateTypeDef.ADMINISTRATOR_REGIST,
                WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
        }
        else
        {
            l_expAccountOpen.validateAccountOpenRegistInfo(
                WEB3ValidateTypeDef.DEFAULT,
                WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
        }

        WEB3AccOpenRequestNumberService l_requestNumberService = 
            (WEB3AccOpenRequestNumberService)Services.getService(WEB3AccOpenRequestNumberService.class);
            
        //1.7 get新規識別コード(String)
        String l_strNewRequestNumber = l_requestNumberService.getNewRequestNumber(l_request.accoutOpenApplyInfo.institutionCode);
        
        //1.8 saveNew口座開設見込客(String, String, String, String)
        if (l_admin != null)
        {
            l_expAccountOpen.saveNewExpAccountOpen(WEB3ValidateTypeDef.ADMINISTRATOR_REGIST,
                 l_admin.getAdministratorCode(), l_admin.getAdministratorCode(), l_strNewRequestNumber);
        }        
        else if (l_request.accoutOpenApplyInfo.creatorCode != null)
        {
            l_expAccountOpen.saveNewExpAccountOpen(WEB3ValidateTypeDef.DEFAULT, 
                l_request.accoutOpenApplyInfo.creatorCode, l_request.accoutOpenApplyInfo.creatorCode, l_strNewRequestNumber);
        }
        else
        {
            l_expAccountOpen.saveNewExpAccountOpen(WEB3ValidateTypeDef.DEFAULT, null, null, l_strNewRequestNumber);
        }
        
        try
        {
            //1.9 メール(String, String, String)
            String l_strDiscernmentId = null;
            String l_strMailText = null;
            String l_strCorporateMailSendDiv = this.getCorporateMailSendDiv(l_request.accoutOpenApplyInfo.institutionCode);
            if (WEB3MailSendDivDef.SENDED.equals(l_strCorporateMailSendDiv)
                && WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_request.accoutOpenApplyInfo.accountType))
            {
                l_strDiscernmentId = "0";
                l_strMailText = l_request.accoutOpenApplyInfo.extItemText6;
            }
            else
            {
                l_strDiscernmentId = "----";
            }
            WEB3GentradeMailInfo l_mail = new WEB3GentradeMailInfo(
                l_expAccountOpen.getInstitutionCode(), 
                WEB3SendmailDivDef.ACCOPEN_APPLICATION_MAIL, 
                l_strDiscernmentId);//WEB3BaseException

            //1.10 メールオブジェクトが取得できた場合のみ処理実施
            MailProcParams l_mailProcParams = new MailProcParams();
            l_mailProcParams.setInstitutionCode(l_expAccountOpen.getInstitutionCode());
            l_mailProcParams.setBranchCode(l_expAccountOpen.getBranchCode());
            l_mailProcParams.setSendmailDiv(WEB3SendmailDivDef.ACCOPEN_APPLICATION_MAIL);
            l_mailProcParams.setDiscernmentId(l_strDiscernmentId);
            l_mailProcParams.setAccountCode("----");
            l_mailProcParams.setMailId(Long.parseLong(l_strNewRequestNumber));
            l_mailProcParams.setDate1(GtlUtils.getTradingSystem().getBizDate());
            l_mailProcParams.setName1(l_request.accoutOpenApplyInfo.accountFamilyName);
            l_mailProcParams.setName2(l_request.accoutOpenApplyInfo.accountName);
            l_mailProcParams.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);
            l_mailProcParams.setEmailAddress(l_request.accoutOpenApplyInfo.mailAddress);
            l_mailProcParams.setMailText(l_strMailText);
            l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mailProcParams.setCreatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            l_mailProcParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());

            //1.10.1 doInsertQuery(Row)
            Processors.getDefaultProcessor().doInsertQuery(l_mailProcParams);//DataException
        }
        catch (WEB3BusinessLayerException l_e)
        {
            log.debug("メールオブジェクトが取得なぃ." + l_e.getErrorMessage());
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
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
        
        //1.11 createResponse( )
        WEB3AccOpenApplyCompleteResponse l_response = 
            (WEB3AccOpenApplyCompleteResponse)l_request.createResponse();
            
        //1.12 プロパティセット
        //識別コード
        l_response.requestNumber = l_strNewRequestNumber;
        
        //現在日時
        l_response.currentDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getメールアドレス登録行)<BR>
     * メールアドレス登録データを取得する。 <BR>
     * <BR>
     * １）　@以下の条件で、メールアドレス登録テーブルテーブルを検索する。 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@　@メールアドレス登録ID ＝ 引数.メールアドレス登録ID <BR>
     * <BR>
     * 検索結果が取得できた場合、メールアドレス登録行を返却する。 <BR>
     * 以外の場合、エラーをスローする。<BR>
     * @@param l_strMailAddressRegiID - (メールアドレス登録ID)<BR>
     * メールアドレス登録ID<BR>
     * @@return MailAddressRegiParams
     * @@throws WEB3BaseException
     */
    private MailAddressRegiParams getMailAddressRegiParams(String l_strMailAddressRegiID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "getMailAddressRegiParams(String)";
        log.entering(STR_METHOD_NAME );
        
        String l_strWhere1 = " mail_address_regi_id = ? and delete_flag = ? ";
        Object[] l_objConds1 =  new Object[]{l_strMailAddressRegiID, BooleanEnum.FALSE};
        QueryProcessor l_queryProcessor = null;
        List l_lisRecordexcs1 = null;
        try
        {
        	l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecordexcs1 = l_queryProcessor.doFindAllQuery(
                MailAddressRegiRow.TYPE,
                l_strWhere1,
                l_objConds1);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //該当行が存在する場合、エラーをスローする。
        if (l_lisRecordexcs1.isEmpty())
        {
        	log.error("メールアドレスが確認用のものと一致しておりません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_01789,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "メールアドレスが確認用のものと一致しておりません。");
        }

        log.exiting(STR_METHOD_NAME);
        return new MailAddressRegiParams((MailAddressRegiRow)l_lisRecordexcs1.get(0));
    }

    /**
     * (isメールアドレス登録チェック実施)<BR>
     * メールアドレス登録チェックを行うかを判定する。 <BR>
     * <BR>
     * １）引数.証券会社コードに該当する証券会社IDを取得する。<BR>
     * <BR>
     * ２）以下の条件で、証券会社プリファ@レンステーブルからレコードを取得する。<BR>
     * 　@　@[条件] <BR>
     * 　@　@証券会社ID = １）で取得した証券会社ID <BR>
     * 　@　@プリファ@レンス名 = "accountopen.mailadd.reg.check.div" <BR>
     * 　@　@プリファ@レンスの値 = "1" <BR>
     * <BR>
     * 　@　@レコードが取得できた場合、trueを返却する。<BR>
     * 　@　@以外場合、falseを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    private boolean isMailAddressRegiCheck(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "isMailAddressRegiCheck(String)";
        log.entering(STR_METHOD_NAME );

        //１）引数.証券会社コードに該当する証券会社IDを取得する。
        long l_lngInstitutionId = 0;
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        try
        {
            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            l_lngInstitutionId = l_institution.getInstitutionId();
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）以下の条件で、証券会社プリファ@レンステーブルからレコードを取得する。
        //　@　@[条件]
        //　@　@証券会社ID = １）で取得した証券会社ID
        //　@　@プリファ@レンス名 = "accountopen.mailadd.reg.check.div"
        //　@　@プリファ@レンスの値 = "1"
        String l_strWhere = " institution_id = ? and name = ? and value = ? ";
        Object[] l_obj =  new Object[]{
            new Long(l_lngInstitutionId), WEB3InstitutionPreferencesNameDef.ACCOUNTOPEN_MAILADD_REG_CHECK_DIV, "1"};
        List l_lisRows = null;
        try
        {
            l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                InstitutionPreferencesRow.TYPE,
                l_strWhere,
                l_obj);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //レコードが取得できた場合、trueを返却する。
        if (!l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get法@人用メール送信区分)<BR>
     * 法@人用メール送信の区分を取得する。 <BR>
     * <BR>
     * １）引数.証券会社コードに該当する証券会社IDを取得する。<BR>
     * <BR>
     * ２）以下の条件で、証券会社プリファ@レンステーブルからレコードを取得する。<BR>
     * 　@　@[条件] <BR>
     * 　@　@証券会社ID = １）で取得した証券会社ID <BR>
     * 　@　@プリファ@レンス名 = "accountopen.mail.send.div" <BR>
     * 　@　@プリファ@レンスの値 = "1" <BR>
     * <BR>
     * 　@　@レコードが取得できた場合、"1" を返却する。<BR>
     * 　@　@以外場合、nullを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    private String getCorporateMailSendDiv(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "getCorporateMailSendDiv(String)";
        log.entering(STR_METHOD_NAME );

        //１）引数.証券会社コードに該当する証券会社IDを取得する。
        long l_lngInstitutionId = 0;
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        try
        {
            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            l_lngInstitutionId = l_institution.getInstitutionId();
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）以下の条件で、証券会社プリファ@レンステーブルからレコードを取得する。
        //　@　@[条件]
        //　@　@証券会社ID = １）で取得した証券会社ID
        //　@　@プリファ@レンス名 = "accountopen.mail.send.div"
        //　@　@プリファ@レンスの値 = "1"
        String l_strWhere = " institution_id = ? and name = ? and value = ? ";
        Object[] l_obj =  new Object[]{
            new Long(l_lngInstitutionId), WEB3InstitutionPreferencesNameDef.ACCOUNTOPEN_MAIL_SEND_DIV, "1"};
        List l_lisRows = null;
        try
        {
            l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                InstitutionPreferencesRow.TYPE,
                l_strWhere,
                l_obj);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //レコードが取得できた場合、"1" を返却する。
        if (!l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3MailSendDivDef.SENDED;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
