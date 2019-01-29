head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡サービスImpl(WEB3AioCashinNoticeServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成  
                   2004/10/25 黄建 (中訊) レビュー 
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.aio.data.DepositInformParams;
import webbroker3.aio.message.WEB3AioCashinNoticeCompleteRequest;
import webbroker3.aio.message.WEB3AioCashinNoticeCompleteResponse;
import webbroker3.aio.message.WEB3AioCashinNoticeConfirmRequest;
import webbroker3.aio.message.WEB3AioCashinNoticeConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeMailSendService;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MailSendDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (入金連絡サービスImpl)<BR>
 * 入金連絡サービス実装クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeServiceImpl extends WEB3ClientRequestService 
    implements WEB3AioCashinNoticeService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeServiceImpl.class);
    
    /**
     * 入金連絡サービス処理を実施する。 <BR>
     * リクエストデータの型により、validate連絡()または、<BR>
     * submit連絡()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40EBFBFA03AA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (l_request instanceof WEB3AioCashinNoticeConfirmRequest)
        {
            WEB3AioCashinNoticeConfirmResponse l_aioCashinNoticeConfirmResponse = 
                this.validateNotice((WEB3AioCashinNoticeConfirmRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_aioCashinNoticeConfirmResponse;
        }
        else if(l_request instanceof WEB3AioCashinNoticeCompleteRequest)
        {
            WEB3AioCashinNoticeCompleteResponse l_aioCashinNoticeCompleteResponse = 
                this.submitNotice((WEB3AioCashinNoticeCompleteRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_aioCashinNoticeCompleteResponse;
        }
        else
        {
            log.debug(" パラメータ値が不正する！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (validate連絡)<BR>
     * 入金連絡の審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入金連絡）validate連絡」  参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / 入金連絡 」<BR>
     * （入金連絡）validate連絡<BR>
     *    : 1.6) WEB3StringTypeUtility.isMailAddress()をコールする。 <BR>
     *     戻り値がfalseの場合、例外をスローする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00777<BR>
     * <BR>
     *  ========================================================<BR>
     * <BR>
     *  =========================================================<BR>
     * 　@　@:  1.10.金融機@関(String, String)<BR>   
     *     インスタンスが取得できなかった場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00778<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AioCashinNoticeConfirmResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40EBFC0503AA
     */
    protected WEB3AioCashinNoticeConfirmResponse validateNotice(WEB3AioCashinNoticeConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "validateNotice(WEB3AioCashinNoticeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)リクエストデータの整合性をチェックする。
        l_request.validate();
        
        // 1.2)注文受付可能かどうかのチェックを行う。 
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // 1.3)補助口座を取得する。
        //[引数] 
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        // 1.4)証券会社オブジェクトを取得する
        Institution l_institution = l_subAccount.getInstitution();
        
        // 1.5)validateメール送信(Institution)
        boolean l_blnIsMailSendEnable = this.validateMailSend(l_institution);
        
        // 1.6)メールアドレスチェック 
        if (l_blnIsMailSendEnable && !WEB3StringTypeUtility.isMailAddress(l_request.emailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "メールアドレスチェック[" + l_request.emailAddress +
                "]エラー");
        }
        
        // 1.7)顧客オブジェクトを取得する
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        
        // 1.8)口座番号（顧客コード）を取得する
        String l_strAccountCode = l_gentradeMainAccount.getAccountCode().substring(0, 6);
        
        // 1.9)顧客名を取得する。
        String l_personNameDetails = l_gentradeMainAccount.getDisplayAccountName();
        
        
        // 1.10)金融機@関インスタンスを生成する。 
        //[引数] 
        //証券会社コード： 証券会社.getInstitutionCode() 
        //金融機@関コード： リクエストデータ.振込先金融機@関コード
        WEB3GentradeFinInstitution l_gentradeFinInstitution = 
            new WEB3GentradeFinInstitution(
                l_institution.getInstitutionCode(),
                l_request.finInstitutionCode);
        
        // 1.11)レスポンスデータを生成する
        WEB3AioCashinNoticeConfirmResponse l_aioCashinNoticeConfirmResponse
            = (WEB3AioCashinNoticeConfirmResponse) l_request.createResponse();
        
        //レスポンス.顧客名 = 顧客.get顧客表示名()の戻り値
        l_aioCashinNoticeConfirmResponse.accountName = l_personNameDetails;
        
        //レスポンス.顧客コード = 顧客.getAccountCode()の戻り値
        l_aioCashinNoticeConfirmResponse.accountCode = l_strAccountCode;
        
        //レスポンス.振込日 = リクエストデータ.振込日
        l_aioCashinNoticeConfirmResponse.transferDate = 
            WEB3DateUtility.formatDate(l_request.transferDate,"yyyyMMdd");
        
        //レスポンス.振込先金融機@関コード = リクエストデータ.振込先金融機@関コード
        l_aioCashinNoticeConfirmResponse.finInstitutionCode = l_request.finInstitutionCode;
        
        //レスポンス.振込先金融機@関名 = 金融機@関.get金融機@関名（漢字）()の戻り値
        l_aioCashinNoticeConfirmResponse.finInstitutionName 
            = l_gentradeFinInstitution.getFinInstitutionNameKanji();
        
        //レスポンス.入金金額 = リクエストデータ.入金金額
        l_aioCashinNoticeConfirmResponse.cashinAmt = l_request.cashinAmt;
        
        //レスポンス.メールアドレス = リクエストデータ.メールアドレス
        l_aioCashinNoticeConfirmResponse.emailAddress = l_request.emailAddress;
        
        log.exiting(STR_METHOD_NAME);
        return l_aioCashinNoticeConfirmResponse;
    }
    
    /**
     * (submit連絡)<BR>
     * 入金連絡の登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入金連絡）submit連絡」  参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / 入金連絡 」<BR>
     * （入金連絡）submit連絡 )<BR>
     * 　@　@:  1.5.validate取引パスワード<BR>   
     *     パスワードチェックがエラーだった場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00009<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *    : 1.8) WEB3StringTypeUtility.isMailAddress()をコールする。 <BR>
     *     戻り値がfalseの場合、例外をスローする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00777<BR>
     * <BR>
     *  ========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AioCashinNoticeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBFC10005E
     */
    protected WEB3AioCashinNoticeCompleteResponse submitNotice(WEB3AioCashinNoticeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "submitNotice(WEB3AioCashinNoticeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)リクエストデータの整合性をチェックする。
        l_request.validate();
        
        // 1.2)注文受付可能かどうかのチェックを行う。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // 1.3)補助口座を取得する。
        //[引数] 
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        // 1.4)代理入力者オブジェクトを取得する。
        Trader l_trader = this.getTrader();
        
        // 1.5)パスワードのチェックを行う。 
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //補助口座： get補助口座()の戻り値 
        //パスワード： リクエストデータ.暗証番号  
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);   
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        OrderValidationResult l_orderValidationResult = 
            l_gentradeOrderValidator.validateTradingPassword(l_trader, l_subAccount, 
            l_request.password);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("チェックエラーの場合はを例外をスローする" + 
                l_orderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.6)証券会社オブジェクトを取得する。
        Institution l_institution= l_subAccount.getInstitution();
        
        // 1.7)該当する証券会社が入金連絡メール送信を実施しているかどうかをチェックする。
        //[引数] 
        //証券会社： getInstitution()の戻り値 
        boolean l_blnMailSend = this.validateMailSend(l_institution);
        
        // 1.8)メールアドレスチェック 
        if (l_blnMailSend && !WEB3StringTypeUtility.isMailAddress(l_request.emailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "メールアドレスチェック[" + l_request.emailAddress +
                "]エラー");
        }

        // 1.9)顧客オブジェクトを取得する。 
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
                
        // 1.10)口座番号（顧客コード）を取得する。 
        String l_strAccoundcode = l_mainAccount.getAccountCode();
        
        // 1.11)顧客名を取得する。 
        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_mainAccount; 
        String l_personNameDetails = l_gentradeMainAccount.getDisplayAccountName();
        
        //1.12)部店オブジェクトを取得する。
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();
        
        // 1.13)金融機@関インスタンスを生成する。 
        //[引数] 
        //証券会社コード： 証券会社.getInstitutionCode() 
        //金融機@関コード： リクエストデータ.振込先金融機@関コード 
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        WEB3GentradeFinInstitution l_gentradeFinInstitution 
            = new WEB3GentradeFinInstitution(l_strInstitutionCode,                
                l_request.finInstitutionCode);
        
        // 1.14)新規の識別コードを取得する。
        //[引数] 
        //証券会社コード： 証券会社.getInstitutionCode()の戻り値 
        //部店コード： 部店.getBranchCode()の戻り値 
        //銘柄タイプ： 5（現金） 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class); 
        String l_strBranchCode = l_gentradeBranch.getBranchCode();
        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.CASH);
        log.debug("新規の識別コード = " + l_strNewNumber);
        log.debug("InstitutionCode = " + l_strInstitutionCode);
        log.debug("BranchCode = " + l_strBranchCode);
        
        
        //*1)入金連絡Paramsオブジェクトを生成し、以下のとおりプロパティをセットする。
        DepositInformParams l_depositInformParams = new DepositInformParams();
        
        //入金連絡Params.証券会社コード = 証券会社.getInstitutionCode()の戻り値
        l_depositInformParams.setInstitutionCode(l_strInstitutionCode);
        
        //入金連絡Params.部店コード = 部店.getBranchCode()の戻り値
        l_depositInformParams.setBranchCode(l_strBranchCode);
        
        //入金連絡Params.顧客コード = 顧客.getAccountCode()の戻り値
        l_depositInformParams.setAccountCode(l_strAccoundcode);
        
        //入金連絡Params.識別コード = 注文識別コード採番サービス.get新規識別コード()の戻り値
        l_depositInformParams.setOrderRequestNumber(l_strNewNumber);
        
        //入金連絡Params.作業日時 = システムタイムスタンプ
        l_depositInformParams.setWorkedTimestamp(GtlUtils.getSystemTimestamp());
        
        //入金連絡Params.振込日 = リクエストデータ.振込日
        l_depositInformParams.setTransferDate(l_request.transferDate);
        
        //入金連絡Params.金融機@関コード = リクエストデータ.振込先金融機@関コード
        l_depositInformParams.setFinInstitutionCode(l_request.finInstitutionCode);
        
        //入金連絡Params.入金額 = リクエストデータ.入金金額
        l_depositInformParams.setAmount(Long.parseLong(l_request.cashinAmt));
        
        try
        {
            // 1.15)入金連絡テーブルに入金連絡Paramsの内容を登録する。
            //[引数] 
            //入金連絡Params： 入金連絡Paramsオブジェクト 
            WEB3DataAccessUtility.insertRow(l_depositInformParams);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // 1.16)メール送信処理を行う。 
        if(l_blnMailSend)
        {           
            WEB3AioCashinNoticeMailSendService l_aioCashinNoticeMailSendService =
            (WEB3AioCashinNoticeMailSendService) Services.getService(
                WEB3AioCashinNoticeMailSendService.class);
        
            l_aioCashinNoticeMailSendService.createMail(
                    l_depositInformParams, l_request.emailAddress);
        }
        
        // 1.17)レスポンスデータを生成する。
        WEB3AioCashinNoticeCompleteResponse l_aioCashinNoticeCompleteResponse = 
            (WEB3AioCashinNoticeCompleteResponse) l_request.createResponse();
        
        // 1.18) (*)以下のとおり、プロパティをセットする
        //レスポンス.顧客名 = 顧客.get顧客表示名()の戻り値
        l_aioCashinNoticeCompleteResponse.accountName = l_personNameDetails;
        
        //レスポンス.顧客コード = 顧客.getAccountCode()の戻り値
        l_aioCashinNoticeCompleteResponse.accountCode = l_strAccoundcode.substring(0, 6);
        
        //レスポンス.振込日 = リクエストデータ.振込日
        l_aioCashinNoticeCompleteResponse.transferDate = 
            WEB3DateUtility.formatDate(l_request.transferDate, "yyyyMMdd");
        
        //レスポンス.振込先金融機@関コード = リクエストデータ.振込先金融機@関コード
        l_aioCashinNoticeCompleteResponse.finInstitutionCode = l_request.finInstitutionCode;
        
        //レスポンス.振込先金融機@関名 = 金融機@関.get金融機@関名（漢字）()の戻り値
        l_aioCashinNoticeCompleteResponse.finInstitutionName = l_gentradeFinInstitution.getFinInstitutionNameKanji();
        
        //レスポンス.入金金額 = リクエストデータ.入金金額
        l_aioCashinNoticeCompleteResponse.cashinAmt = l_request.cashinAmt;
        
        //レスポンス.メールアドレス = リクエストデータ.メールアドレス
        l_aioCashinNoticeCompleteResponse.emailAddress = l_request.emailAddress;
        
        //レスポンス.更新時間= 入金連絡Params.作業日時
        l_aioCashinNoticeCompleteResponse.lastUpdatedTimestamp = l_depositInformParams.worked_timestamp;
        
        log.exiting(STR_METHOD_NAME);
        return l_aioCashinNoticeCompleteResponse;
    }
    
    /**
     * (validateメール送信)<BR>
     * 該当する証券会社が入金連絡メールを<BR>
     * 送信するという設定になっているかどうかとチェックする。<BR>
     * <BR>
     * １）証券会社テーブルのレコードを取得する。<BR>
     * <BR>
     *   引数.証券会社.getDataSourceObject()<BR>
     * <BR>
     * ２）証券会社Params.入金連絡メール送信 = "0"（未実施）の<BR>
     * 場合は、falseを返す。<BR>
     *    証券会社Params.入金連絡メール送信 = "1"（実施）の<BR>
     * 場合は、trueを返す。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@return boolean<BR>
     * @@roseuid 40FDCFAB03D6
     */
    protected boolean validateMailSend(Institution l_institution) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "validateMailSend(Institution l_institution)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）証券会社テーブルのレコードを取得する
        InstitutionRow l_institutionRow = (InstitutionRow)l_institution.getDataSourceObject();
        
        //２）証券会社Params.入金連絡メール送信 = "0"（未実施）の場合は、falseを返す。 
        //証券会社Params.入金連絡メール送信 = "1"（実施）の場合は、trueを返す。
        if(WEB3MailSendDef.NOT_ENFORCEMENT.equals(l_institutionRow.getDepositInformMailSend()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else if(WEB3MailSendDef.ENFORCEMENT.equals(l_institutionRow.getDepositInformMailSend()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug(" パラメータ値が不正する！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
}
@
