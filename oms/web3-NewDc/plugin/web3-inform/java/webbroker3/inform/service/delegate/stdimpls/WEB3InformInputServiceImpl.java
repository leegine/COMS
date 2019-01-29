head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡入力サービスImpl(WEB3InformInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/
package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.ExtMailProcParams;
import webbroker3.gentrade.data.MailInfoParams;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.WEB3InformClientRequestService;
import webbroker3.inform.data.InformCtrlItemAttributeRow;
import webbroker3.inform.data.InformDivPreferencesRow;
import webbroker3.inform.data.VariousInformDao;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.define.WEB3InformBlankDef;
import webbroker3.inform.message.WEB3InformAddInfoUnit;
import webbroker3.inform.message.WEB3InformCompleteRequest;
import webbroker3.inform.message.WEB3InformCompleteResponse;
import webbroker3.inform.message.WEB3InformConfirmRequest;
import webbroker3.inform.message.WEB3InformConfirmResponse;
import webbroker3.inform.message.WEB3InformInputRequest;
import webbroker3.inform.message.WEB3InformInputResponse;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.inform.service.delegate.WEB3InformInputService;
import webbroker3.inform.util.WEB3InformColumnSpec;
import webbroker3.inform.util.WEB3InformTableSpec;
import webbroker3.util.WEB3LogUtility;

/**
 * (連絡入力サービスImpl)
 * 連絡入力サービス実装クラス
 */
public class WEB3InformInputServiceImpl
    extends WEB3InformClientRequestService
    implements WEB3InformInputService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformInputServiceImpl.class);

    /**
     * @@roseuid 41EE632C001F
     */
    public WEB3InformInputServiceImpl()
    {

    }

    /**
     * 連絡入力サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * <BR>
     *    get入力画面()<BR>
     *    validate連絡()<BR>
     *    submit連絡()<BR>
     * <BR>
     * 上記メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@roseuid 419DA13A026B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3InformInputRequest)
        {
            l_response = getInformInputDisplay((WEB3InformInputRequest)l_request);
        }
        else if (l_request instanceof WEB3InformConfirmRequest)
        {
            l_response = validateInform((WEB3InformConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3InformCompleteRequest)
        {
            l_response = submitInform((WEB3InformCompleteRequest)l_request);
        }
        else
        {
            log.error("パラメータ値不正。");            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);   
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 入力画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連絡入力）get入力画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return webbroker3.inform.message.WEB3InformInputResponse
     * @@roseuid 419DA1AB0299
     */
    protected WEB3InformInputResponse getInformInputDisplay(WEB3InformInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInformInputDisplay(WEB3InformInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1 getLoginId
        //ログインIDが取得できた場合、メニュー画面からアクセスされた
        //ものと判断する。
        OpLoginSecurityService l_services = (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        WEB3InformInputResponse l_response = null;
        long l_lngLoginId = 0;
        try
        {
            try
            {
                l_lngLoginId = l_services.getLoginId();       
                
                if (l_lngLoginId == 0)
                {
                    log.info("ホームページからアクセスの場合");                
                }          
            }
            catch (IllegalSessionStateException l_ex)
            {          
                log.info("ホームページからアクセスの場合");     
            }
            


            //1.3(*1)メニュー画面からのアクセス（既にログイン済）の場合
            MainAccountRow l_mainAccountRow = null;
            String l_strAccountCode = "";
            if (l_lngLoginId != 0)
            {
                //1.2validate注文受付可能
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
                //1.3.1getAccountId( )(
                long l_lngAccountId = l_services.getAccountId();
                //1.3.2get顧客()(
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
                //1.3.3get顧客表示名( )(
                l_strAccountCode = l_mainAccount.getDisplayAccountName();
                //1.3.4getDataSourceObject(
                l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            
                //1.4createResponse( )(
                l_response = (WEB3InformInputResponse)l_request.createResponse();
                //1.5(*2)プロパティセット
                //(*2) 以下のとおりに、プロパティをセットする。
                //
                //１）メニュー画面からアクセスの場合
           
                //  レスポンス.部店コード = 顧客行オブジェクト.部店コード
                l_response.branchCode = l_mainAccountRow.getBranchCode();
                //  レスポンス.顧客コード = 顧客行オブジェクト.顧客コード
                l_response.accountCode = l_mainAccount.getDisplayAccountCode();
                //  レスポンス.顧客名 = get顧客表示名()の戻り値
                l_response.accountName = l_strAccountCode;
                //  レスポンス.メールアドレス = 顧客行オブジェクト.emailアドレス
                l_response.mailAddress = l_mainAccountRow.getEmailAddress();
            }
            else
            {
                l_response = (WEB3InformInputResponse)l_request.createResponse();
                //２）ホームページからアクセスの場合

                //  レスポンス.部店コード = null
                l_response.branchCode = null;
                //  レスポンス.顧客コード = null
                l_response.accountCode = null;
                //  レスポンス.顧客名 = null
                l_response.accountName = null;
                //  レスポンス.メールアドレス = null
                l_response.mailAddress = null;
            }
        }
        catch(NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate連絡)<BR>
     * 連絡の審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連絡入力）validate連絡」 参照。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return webbroker3.inform.message.WEB3InformConfirmResponse
     * @@roseuid 419DA13A0299
     */
    protected WEB3InformConfirmResponse validateInform(WEB3InformConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInform(WEB3InformConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1validate( 
        l_request.validate();
        //1.2各種連絡(各種連絡情報)(
        WEB3Inform l_web3Inform = new WEB3Inform(l_request.informInfoUnit);
        //1.3validate各種連絡情報( )(
        l_web3Inform.validateInformDetailInfoUnit();
        //1.4
        long l_lngLoginId = 0;
        OpLoginSecurityService l_services = (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);  
        try
        {
            l_lngLoginId = l_services.getLoginId();       
            
            if (l_lngLoginId == 0)
            {
                log.info("ホームページからアクセスの場合"); 
                //1.5
                l_context.setBranchCode(l_web3Inform.getBranchCode());
                WEB3GentradeTradingTimeManagement.setTimestamp();             
            }          
        }
        catch (IllegalSessionStateException l_ex)
        {          
            log.info("ホームページからアクセスの場合");   
            //1.5
            l_context.setBranchCode(l_web3Inform.getBranchCode());
            WEB3GentradeTradingTimeManagement.setTimestamp();   
        }
        //1.6validate注文受付可能(
        WEB3GentradeTradingTimeManagement.validateOrderAccept(); 


        //1.7 get付加情報( )(
        WEB3InformAddInfoUnit l_informAddInfoUnit = l_web3Inform.getInformAddInfoUnit();
        //1.6createResponse( 
        //1.7 (*)プロパティセット
        //(*)以下のとおりに、プロパティをセットする。

        //レスポンス.付加情報 = get付加情報()の戻り値
        WEB3InformConfirmResponse l_response = (WEB3InformConfirmResponse)l_request.createResponse();
        l_response.informAddUnit = l_informAddInfoUnit;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit連絡)<BR>
     * 連絡の登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連絡入力）submit連絡」 参照。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return WEB3InformCompleteResponse
     * @@roseuid 419DA13A02B9
     */
    protected WEB3InformCompleteResponse submitInform(WEB3InformCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitInform(WEB3InformCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1validate( )(
        l_request.validate();
        //1.2各種連絡(各種連絡情報)(
        WEB3Inform l_inform = new WEB3Inform(l_request.informInfoUnit);
        //1.3validate各種連絡情報( )(
        l_inform.validateInformDetailInfoUnit();
        //1.4getLoginId( )(
        OpLoginSecurityService l_services = (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        Trader l_trader = null;
        WEB3GentradeSubAccount l_subAccount = null;
        long l_lngLoginId = 0;
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);  

        try
        {
            l_lngLoginId = l_services.getLoginId();       
                
            if (l_lngLoginId == 0)
            {
                log.info("ホームページからアクセスの場合");  
                l_context.setBranchCode(l_inform.getBranchCode());     
                WEB3GentradeTradingTimeManagement.setTimestamp();          
            }          
        }
        catch (IllegalSessionStateException l_ex)
        {          
            log.info("ホームページからアクセスの場合");     
            l_context.setBranchCode(l_inform.getBranchCode());     
            WEB3GentradeTradingTimeManagement.setTimestamp();  
        }
        //1.6validate注文受付可能( )(
        WEB3GentradeTradingTimeManagement.validateOrderAccept();



        
        //1.7メニュー画面からアクセスの場合        
        if (l_lngLoginId != 0)
        {       
            //1.7.1get補助口座( )(
            l_subAccount = this.getSubAccount();
            //1.7.2get代理入力者( )(
            l_trader = this.getTrader();
            
            long l_lngBranchId = l_subAccount.getWeb3GenBranch().getBranchId();
            String l_strBranchId = Long.toString(l_lngBranchId);
            String l_strInformDiv = l_inform.getInformDiv();
            
            if (this.isCheckTradePassword(l_strBranchId, l_strInformDiv))
            {
                //1.7.3validate取引パスワード(Trader, SubAccount, String)( 
                WEB3GentradeOrderValidator l_validator = new WEB3GentradeOrderValidator();
                OrderValidationResult l_result = l_validator.validateTradingPassword(l_trader,l_subAccount,l_request.password);
                if (l_result.getProcessingResult().isFailedResult())
                {
                    log.error(STR_METHOD_NAME + l_result.getProcessingResult().getErrorInfo().error_message);
                    throw new WEB3BaseException(
                        l_result.getProcessingResult().getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);   
                }  
            }

        }



        //1.8get新規識別コード(String, String)(
        WEB3InformHostReqOrderNumberManageService l_hostReqOrderNumberManageService
            = (WEB3InformHostReqOrderNumberManageService)Services
                .getService(WEB3InformHostReqOrderNumberManageService.class);
        String l_strNewOrderRequestCode = 
            l_hostReqOrderNumberManageService.getNewOrderRequestCode(l_request.informInfoUnit.institutionCode,
            l_request.informInfoUnit.informType);

        //1.8saveNew各種連絡(String, String)(
        String l_strTraderCode = null;
        if (l_trader != null)
        {
            l_strTraderCode = l_trader.getTraderCode();
        }
        
        l_inform.saveNewInform(l_strTraderCode,l_strNewOrderRequestCode);
        //1.9createMail(各種連絡情報)(
        VariousInformParams l_params = (VariousInformParams)l_inform.getDataSourceObject();
        VariousInformRow l_row = null;
        try
        {
            l_row = VariousInformDao.findRowByInstitutionCodeInformDivRequestNumberBranchCode
                (l_params.getInstitutionCode(),l_params.getInformDiv(),l_strNewOrderRequestCode,l_params.getBranchCode()
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        VariousInformParams l_newParams = new VariousInformParams(l_row);
        this.createMail(l_newParams);
        //1.10createResponse( )(
        WEB3InformCompleteResponse l_response = (WEB3InformCompleteResponse)l_request.createResponse();
        l_response.requestNumber = l_strNewOrderRequestCode;
        l_response.updateTimeStamp = l_newParams.getLastUpdatedTimestamp();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * メール送信テーブルにレコードを登録する。<BR>
     * <BR>
     * １）以下の条件で、メールテーブルからレコードを取得する。<BR>
     * <BR>
     * [条件]<BR>
     * 証券会社コード = 引数.連絡情報.証券会社コード<BR>
     * 送信メール区分 = "05" + 引数.連絡情報.連絡種別<BR>
     * <BR>
     * ２）取得したメールテーブルのレコード毎にLoop処理を行う。<BR>
     * <BR>
     * ２－１）メール送信テーブルにレコードを登録する。<BR>
     * <BR>
     * ※更新内容については、DB更新仕様参照<BR>
     * <BR>
     * ２－２）メール送信拡張テーブルにレコードを登録する。<BR>
     * <BR>
     * ※更新内容については、DB更新仕様参照<BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行
     * @@roseuid 41C81FEA03B4
     */
    protected void createMail(VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createMail(VariousInformParams)";
        log.entering(STR_METHOD_NAME);
        //１）以下の条件で、メールテーブルからレコードを取得する。
        //
        //[条件]
        //証券会社コード = 引数.連絡情報.証券会社コード
        //送信メール区分 = "05" + 引数.連絡情報.連絡種別
        //
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strQuery = "institution_code = ? ";
            l_strQuery += " and sendmail_div = ?";
        
            Object[] l_queryContainer = new Object[] {
                l_variousInformParams.getInstitutionCode(),
                WEB3SendmailDivDef.INFORM + l_variousInformParams.getInformDiv()};
                        
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                MailInfoRow.TYPE,
                l_strQuery,
                l_queryContainer
                );
            //２）取得したメールテーブルのレコード毎にLoop処理を行う。
            //


            int l_lngLen = 0;
            if (l_lisRecords != null)
            {
                l_lngLen = l_lisRecords.size();
            }
            for (int i = 0;i < l_lngLen; i++)
            {
                MailProcParams l_mailProcParams = new MailProcParams();
                MailInfoParams l_mailInfoParams = (MailInfoParams)l_lisRecords.get(i);
                //２－１）メール送信テーブルにレコードを登録する。
                //
                //※更新内容については、DB更新仕様参照
                //
                //1   証券会社コード     institution_code     連絡情報.証券会社コード
                l_mailProcParams.setInstitutionCode(l_variousInformParams.getInstitutionCode());
                //2   部店コード       branch_code       連絡情報.部店コード
                l_mailProcParams.setBranchCode(l_variousInformParams.getBranchCode());  
                //3   送信メール区分     sendmail_div    メールテーブル.送信メール区分
                l_mailProcParams.setSendmailDiv(l_mailInfoParams.sendmail_div);
                //4   識別ID        discernment_id   メールテーブル.識別ID
                l_mailProcParams.setDiscernmentId(l_mailInfoParams.discernment_id);
                //5   口座コード       account_code     "連絡情報.口座番号 != null の場合、連絡情報.口座番号;
                //連絡情報.口座番号 == null の場合、"0000000"
                if (l_variousInformParams.getAccountCode()!= null)
                {
                    l_mailProcParams.setAccountCode(l_variousInformParams.getAccountCode());
                }
                else
                {
                    l_mailProcParams.setAccountCode("0000000");
                }
                //6   メールID       mail_id                              18  連絡情報.識別コード
                l_mailProcParams.setMailId(Long.parseLong(l_variousInformParams.request_number));  
                //7   年月日１        date_1                              null
                l_mailProcParams.setDate1(null);
                //8   年月日２        null
                l_mailProcParams.setDate2(null);
                //9   年月日３        null
                l_mailProcParams.setDate3(null);
                //10  年月日４        連絡情報.作成日時
                l_mailProcParams.setDate4(l_variousInformParams.created_timestamp);   
                //11  数量      quantity           18  null
                l_mailProcParams.setQuantity(null);
                //12  金額      amount                  DECIMAL             18  null
                l_mailProcParams.setAmount(null);
                //13  ID      order_id             18  null
                l_mailProcParams.setOrderId(null);
                //14  区分      division  1   null
                l_mailProcParams.setDivision(null);
                //15  名称1     name  50  各種連絡行.顧客名
                l_mailProcParams.setName1(l_variousInformParams.getAccountName());
                //16  名称2     name  50  null
                l_mailProcParams.setName2(null);
                //17  電子メール送信ステイタス        status1   0：未処理（Email未送信）
                l_mailProcParams.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND); 
                //18  電子メール送信日       send_process_date_time                              null
                l_mailProcParams.setSendProcessDateTime(null);
                //19  再送区分        resend_status 1   null
                l_mailProcParams.setResendStatus(null);
                //20  電子メール再送日時       resend_process_date_timeDATE                null
                l_mailProcParams.setResendProcessDateTime(null);
                //21  emailアドレス       email_address  "メールテーブル.送信先アドレス == null の場合：
                //                      連絡情報.メールアドレスメールテーブル.送信先アドレス != null の場合：メールテーブル.送信先アドレス"
                if (l_mailInfoParams.getSendAddress() == null)
                {
                    l_mailProcParams.setEmailAddress(l_variousInformParams.getEmailAddress()); 
                }
                else
                {
                    l_mailProcParams.setEmailAddress(l_mailInfoParams.getSendAddress());
                }
 
                //22  送信emailアドレス     send_email_address  "メールテーブル.送信先アドレス == null の場合：メールテーブル.差出人
                //                      メールテーブル.送信先アドレス != null の場合：連絡情報.メールアドレス"
                if (l_mailInfoParams.getSendAddress() == null)
                {
                    l_mailProcParams.setSendEmailAddress(l_mailInfoParams.getMailSender()); 
                }
                else
                {
                    l_mailProcParams.setSendEmailAddress(l_variousInformParams.getEmailAddress());
                }
                //23  件名      subject          null
                l_mailProcParams.setSubject(null);
                //24  メール本文       mail_text          "連絡情報.備考２※連絡情報.備考２=nullの場合は、ブランク"
                if (l_variousInformParams.getExtNote2() == null)
                {
                    l_mailProcParams.setMailText(WEB3InformBlankDef.BLANK);
                }
                else
                {
                    l_mailProcParams.setMailText(l_variousInformParams.getExtNote2());
                }

                //25  削除フラグ       delete_flag                              1   0:FALSE（有効）
                l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE); 
                //26  作成日時        created_timestamp                               処理日時
                Timestamp l_timeStamp = GtlUtils.getSystemTimestamp();
                l_mailProcParams.setCreatedTimestamp(l_timeStamp);
                //27  更新日時        last_updated_timestamp                              処理日時
                l_mailProcParams.setLastUpdatedTimestamp(l_timeStamp);
 
                l_queryProcessor.doInsertQuery(l_mailProcParams); 

                //２－２）メール送信拡張テーブルにレコードを登録する。
                //
                //※更新内容については、DB更新仕様参照
                ExtMailProcParams l_extMailProcParams = new ExtMailProcParams();
                //証券会社コード     institution_code  3   連絡情報.証券会社コード
                l_extMailProcParams.setInstitutionCode(l_variousInformParams.getInstitutionCode());
                //部店コード       branch_code                   3   連絡情報.部店コード 
                l_extMailProcParams.setBranchCode(l_variousInformParams.getBranchCode()); 
                //送信メール区分     sendmail_div  4   メールテーブル.送信メール区分
                l_extMailProcParams.setSendmailDiv(l_mailInfoParams.getSendmailDiv()); 
                //識別ID        discernment_id4   メールテーブル.識別ID
                l_extMailProcParams.setDiscernmentId(l_mailInfoParams.getDiscernmentId());
                //口座コード       account_code  7   "連絡情報.口座番号 != null の場合、連絡情報.口座番号
                //連絡情報.口座番号 == null の場合、"0000000"
                if (l_variousInformParams.getAccountCode() != null)
                {
                    l_extMailProcParams.setAccountCode(l_variousInformParams.getAccountCode());
                }
                else
                {
                    l_extMailProcParams.setAccountCode("0000000");
                }
 
                //メールID       mail_id                              18  連絡情報.識別コード  
                l_extMailProcParams.setMailId(Long.parseLong(l_variousInformParams.getRequestNumber()));
                WEB3InformTableSpec l_spec = new WEB3InformTableSpec();
                int l_intColumnLen = l_spec.getColumnSpecsByTableName(VariousInformRow.TYPE.getTableName()).length;
                for (int j = 0;j < l_intColumnLen;j++)
                {
                    WEB3InformColumnSpec l_loopSpec = l_spec.getColumnSpecsByTableName(VariousInformRow.TYPE.getTableName())[j];
                    if (!l_loopSpec.isCustomizeAble() || "ext_note2".equals(l_loopSpec.asHeader()))
                    {
                        continue;
                    }

                    Object l_valueObj = l_variousInformParams.getColumn(l_loopSpec.asHeader());
          
                    //項目名     item_name   "連絡情報(各種連絡)の列物理名
                    //※対象となるのは、連絡情報.区分１～連絡情報.備考１" 
                    l_extMailProcParams.setItemName(l_loopSpec.asHeader());

                    //項目内容：各種連絡行の項目に該当する、連絡管理項目属性テーブルの項目属性名
                    //１）会社、部店、連絡種別、項目物理名、項目属性値に該当する項目属性名をsetする。
                    StringBuffer l_strWhere = new StringBuffer();
                    l_strWhere.append(" institution_code = ? ");
                    l_strWhere.append(" and branch_code = ? ");
                    l_strWhere.append(" and inform_div = ? ");
                    l_strWhere.append(" and item_symbol_name = ? ");
                    l_strWhere.append(" and attribute_value = ? ");

                    Object[] l_objWhere = 
                    {
                        l_variousInformParams.getInstitutionCode(),
                        l_variousInformParams.getBranchCode(),
                        l_variousInformParams.getInformDiv(),
                        new String(l_loopSpec.asHeader()),
                        l_valueObj
                    };
                    List l_lisInformCtrlItemAttribute = 
                        l_queryProcessor.doFindAllQuery(
                            InformCtrlItemAttributeRow.TYPE,
                            l_strWhere.toString(),
                            l_objWhere);

                    String l_strAttributeName = null;

                    if (!l_lisInformCtrlItemAttribute.isEmpty())
                    {
                        //該当レコードが取得出来た場合、項目属性名をsetする。
                        l_strAttributeName = 
                            ((InformCtrlItemAttributeRow)l_lisInformCtrlItemAttribute.get(0)).getAttributeName();
                        l_extMailProcParams.setItemContents(l_strAttributeName);
                        
                    }
                    else
                    {
                        //２）１）で該当しない場合は、検索条件の部店コードを"000"にして、再度検索し、
                        //　@　@該当する項目属性名をsetする。
                        l_objWhere[1] = "000";

                        l_lisInformCtrlItemAttribute = 
                            l_queryProcessor.doFindAllQuery(
                                InformCtrlItemAttributeRow.TYPE,
                                l_strWhere.toString(),
                                l_objWhere);

                        if (!l_lisInformCtrlItemAttribute.isEmpty())
                        {
                            //該当レコードが取得出来た場合、項目属性名をsetする。
                            l_strAttributeName = 
                                ((InformCtrlItemAttributeRow)l_lisInformCtrlItemAttribute.get(0)).getAttributeName();
                            l_extMailProcParams.setItemContents(l_strAttributeName);
                        
                        }
                        else
                        {
                            //３）１）２）で該当データが無かった場合、入力値をsetする。
                            if (l_valueObj != null)
                            {
                                //入力値が数値型の場合、文字列に変換する。
                                l_extMailProcParams.setItemContents("" + l_valueObj);
                            }
                            else 
                            {
                                //入力値がnullの場合、ブランクをsetする。
                                l_extMailProcParams.setItemContents(WEB3InformBlankDef.BLANK);
                            }
                        }
                    }

                    //削除フラグ       delete_flag                              1   0:FALSE（有効）
                    l_extMailProcParams.setDeleteFlag(BooleanEnum.FALSE); 
                    //作成日時        created_timestamp                               処理日時
                    l_extMailProcParams.setCreatedTimestamp(l_timeStamp);
                    //更新日時        last_updated_timestamp                              処理日時
                    l_extMailProcParams.setLastUpdatedTimestamp(l_timeStamp);   

                    l_queryProcessor.doInsertQuery(l_extMailProcParams);
                }
            }
        }
        catch (DataFindException l_e) 
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }  
        catch (DataQueryException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is取引パスワードチェック )<BR> 
     * 部店IDと連絡種別から、取引パスワードチェックを実施するか判定する。<BR> 
     * <BR>
     * [戻り値] <BR>
     * true： 取引パスワードチェックを実施する <BR>
     * false： 取引パスワードチェックを実施しない <BR>
     * <BR>
     * １）以下の条件で、連絡種別用プリファ@レンステーブルからレコードを取得する。<BR> 
     * <BR>
     * [条件] <BR>
     * 部店ID = 引数.部店ID <BR>
     * 連絡種別 = 引数.連絡種別 <BR>
     * プリファ@レンス項目名 = "inform.password.check" <BR>
     * 項目名連番 = 1 <BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値 == ”チェックしない”の場合、falseを返却する。 <BR>
     * <BR>
     * ３）それ以外の場合、trueを返却する。<BR> 
     * ※レコードが取得出来なかった場合も含む。 <BR>
     * @@param String l_strBranchId<BR>
     * @@param String l_strInformDiv<BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     */
    protected boolean isCheckTradePassword(String l_strBranchId, String l_strInformDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isCheckTradePassword(String l_strBranchId, String l_strInformDiv)";
        log.entering(STR_METHOD_NAME);
        try
        {
            if (l_strBranchId == null || l_strInformDiv ==null)
            {
                //例外をスローする
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //部店IDと連絡種別から、取引パスワードチェックを実施するか判定する。
            //[戻り値]
            //true： 取引パスワードチェックを実施する
            //false： 取引パスワードチェックを実施しない
            
            String l_strQuery = "branch_id = ? ";
            l_strQuery += " and inform_div = ?";
            l_strQuery += " and name = ?";
            l_strQuery += " and name_serial_no = ?";
        
            //１）以下の条件で、連絡種別用プリファ@レンステーブルからレコードを取得する。
            //部店ID = 引数.部店ID
            //連絡種別 = 引数.連絡種別
            //プリファ@レンス項目名 = "inform.password.check"
            //    項目名連番 = 1
            Long l_lngBrnchId = new Long(l_strBranchId);
            String l_strName = "inform.password.check";
            Long l_lngNameSerialNo = new Long(1);
            Object[] l_queryContainer = new Object[] {
                l_lngBrnchId,
                l_strInformDiv,
                l_strName,
                l_lngNameSerialNo};
                        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                InformDivPreferencesRow.TYPE,
                l_strQuery,
                l_queryContainer
                );
            //※レコードが取得出来なかった場合も含む。
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            //２）取得したレコード.プリファ@レンスの値 == ”チェックしない”の場合、falseを返却する。
            InformDivPreferencesRow l_row = (InformDivPreferencesRow)l_lisRecords.get(0);
            if ("0".equals(l_row.getValue().trim()))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            //３）それ以外の場合、trueを返却する。
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        catch (DataQueryException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
    }
}
@
