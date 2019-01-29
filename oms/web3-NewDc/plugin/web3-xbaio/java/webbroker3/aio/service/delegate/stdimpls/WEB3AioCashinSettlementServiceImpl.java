head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettlementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済依頼サービス実装クラス(WEB3AioCashinSettlementServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 屈陽 (中訊) 新規作成
                   2004/10/23 于美麗 (中訊) レビュー
                   2005/1/11 周勇 (中訊) 残対応
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import webbroker3.aio.WEB3AioCompanySettleInstitutionConditions;
import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSettleInstitution;
import webbroker3.aio.message.WEB3AioCashinConfirmRequest;
import webbroker3.aio.message.WEB3AioCashinConfirmResponse;
import webbroker3.aio.message.WEB3AioCashinSettlementRequest;
import webbroker3.aio.message.WEB3AioCashinSettlementResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettlementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (決済依頼サービスImpl)<BR>
 * 決済依頼サービス実装クラス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettlementServiceImpl extends WEB3ClientRequestService 
    implements WEB3AioCashinSettlementService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettlementServiceImpl.class);    
    
    /**
     * 決済依頼サービスを実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate決済()または、start決済()<BR>
     * メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F26055035D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        //リクエストデータの型により、validate決済()または、start決済()
        if (l_request instanceof WEB3AioCashinConfirmRequest)
        {
            l_response = 
                validateSettlement((WEB3AioCashinConfirmRequest)l_request);   
        }
        else if (l_request instanceof WEB3AioCashinSettlementRequest)
        {
            l_response =
                startSettlement((WEB3AioCashinSettlementRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (validate決済)<BR>
     * 決済依頼の発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（オンライン入金決済依頼）validate決済」  参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / （オンライン入金）決済依頼 ( )」) <BR>
     * 　@: （オンライン入金）決済依頼 / （オンライン入金決済依頼）validate決済<BR>   
     *    インスタンスの生成に失敗した場合は、<BR>
     *     リクエストデータの決済機@関IDに問題があるものとして、<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00763<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / （オンライン入金）決済依頼 ( )」) <BR>
     * 　@　@　@　@: （オンライン入金）決済依頼 / （オンライン入金決済依頼）validate決済<BR>   
     *     戻り値が false の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00826<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AioCashinConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F2631E00DC
     */
    protected WEB3AioCashinConfirmResponse validateSettlement(
        WEB3AioCashinConfirmRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "validateSettlement(WEB3AioCashinConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする
        l_request.validate();
        
        //1.2 補助口座オブジェクトを取得する
        //[引数] 
        //補助口座タイプ：1（預り金口座） 
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                
        //1.3 注文共通チェックを実施する
        //[引数] 
        //補助口座：get補助口座()の戻り値 
        FinApp l_finApp = GtlUtils.getFinApp();
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //l_aioOrderManager.validateOrder(l_subAccount);
		
		//1.3 注文受付可能かどうかのチェックを行う
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4 会社別決済機@関条件インスタンスを生成する
        //[引数] 
        //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        
        //部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        
        //決済機@関ID： リクエストデータ.決済機@関ID 
        String l_strPaySchemeId = l_request.paySchemeId;
        //インスタンスの生成に失敗した場合は、
        //リクエストデータの決済機@関IDに問題があるものとして、例外をスローする
        WEB3AioCompanySettleInstitutionConditions l_aioCompanySettleInstitutionConditions = null;
        try
        {
            l_aioCompanySettleInstitutionConditions =
                new WEB3AioCompanySettleInstitutionConditions(
                    l_strInstitutionCode, l_strBranchCode, l_strPaySchemeId);      
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        //1.5 決済機@関が受付時間内かどうかをチェックする
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード： 補助口座.部店コード 
        //決済機@関ID：リクエストデータ.決済機@関ID 
        //戻り値が false の場合、
        //例外をスローする
        if (!l_aioOrderManager.validatePaySchemeAcceptPossible(
            l_strInstitutionCode,
            l_strBranchCode,
            l_request.paySchemeId))
        {
            log.debug("決済機@関が受付時間内かどうかをチェック");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00826,
                this.getClass().getName() + "." + l_strMethodName,
                "決済機@関が受付時間外");
        }
        
        //1.6 入金額の妥当性をチェックする
        //[引数] 
        //補助口座：get補助口座()の戻り値 
        //決済機@関ID：リクエストデータ.決済機@関ID 
        //入金額：リクエストデータ.入金額 
        String l_strCashinAmt = l_request.cashinAmt;
        l_aioOrderManager.validateCreditAmount(
            l_subAccount, l_strPaySchemeId, Double.parseDouble(l_strCashinAmt));
        
        //1.7 提携決済機@関インスタンスを生成する
        //[引数] 
        //決済機@関ID：リクエストデータ.決済機@関ID 
        WEB3AioSettleInstitution l_aioSettleInstitution =
            new WEB3AioSettleInstitution(l_strPaySchemeId);
            
        //1.8 決済機@関名を取得する
        String l_strInstitutionName = l_aioSettleInstitution.getName();
        
        //========remain zhou-yong NO.2 begin =========
        
        //1.9 getオンライン入金発注日(String)
        //アイテムの定義
        //決済機@関の発注日を取得する。
        //[引数] 
        //決済機@関ID： リクエストデータ.決済機@関ID 
        Date l_onlineCashinBizDate = 
            l_aioOrderManager.getOnlineCashinBizDate(l_request.paySchemeId);
                
        //1.10 現時点での1日の総入金額を取得する
        //[引数] 
        //補助口座：get補助口座()の戻り値 
        //決済機@関ID：リクエストデータ.決済機@関ID 
        //発注日：getオンライン入金発注日()の戻り値  
        double l_dblTotalCreditAmount = 
            l_aioOrderManager.getTotalCreditAmount(
                l_subAccount, 
                l_strPaySchemeId, 
                l_onlineCashinBizDate);
            
        //1.11 現時点での1日の入金回数を取得する
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //決済機@関ID： リクエストデータ.決済機@関ID 
        //発注日： getオンライン入金発注日()の戻り値  
        long l_lngCashinOrderCount = 
            l_aioOrderManager.getCashinOrderCount(
                l_subAccount, 
                l_strPaySchemeId, 
                l_onlineCashinBizDate);
        
        //1.12 レスポンスデータを生成する
        WEB3AioCashinConfirmResponse l_aioCashinConfirmResponse = 
            (WEB3AioCashinConfirmResponse)l_request.createResponse();
            
        //1.13 以下のとおりにプロパティをセットする。
        //レスポンス.決済機@関ID = リクエストデータ.決済機@関ID
        l_aioCashinConfirmResponse.paySchemeId = l_strPaySchemeId;
        
        //レスポンス.決済機@関名 = 提携決済機@関.get名称()の戻り値
        l_aioCashinConfirmResponse.paySchemeName = l_strInstitutionName;
        
        //レスポンス.入金額 = リクエストデータ.入金額
        l_aioCashinConfirmResponse.cashinAmt = l_strCashinAmt;
        
        //レスポンス.入金回数 = AIO注文マネージャ.get入金注文回数()の戻り値 + 1
        l_aioCashinConfirmResponse.cashinTimes = String.valueOf(l_lngCashinOrderCount + 1);
        
        //レスポンス.入金合計金額 = AIO注文マネージャ.get総入金額()の戻り値 + リクエストデータ入金額
        l_aioCashinConfirmResponse.cashinTotalAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblTotalCreditAmount + Double.parseDouble(l_strCashinAmt));
        
        //レスポンス.確認時発注日 = getオンライン入金発注日()の戻り値 
        l_aioCashinConfirmResponse.checkDate = l_onlineCashinBizDate;

        //========remain zhou-yong NO.2 end =========
        
        log.exiting(l_strMethodName);
        
        return l_aioCashinConfirmResponse;
    }
    
    /**
     * (start決済)<BR>
     * 決済依頼処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>s
     * 「（オンライン入金決済依頼）start決済」  参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / （オンライン入金）決済依頼 ( )」)<BR>
     * 　@　@: （オンライン入金）決済依頼 / （オンライン入金決済依頼）start決済<BR>   
     *     インスタンスの生成に失敗した場合は、
           リクエストデータの決済機@関IDに問題があるものとして、<BR>
     *     例外をスローする。<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / （オンライン入金）決済依頼 ( )」)<BR>
     * 　@　@: （オンライン入金）決済依頼 / （オンライン入金決済依頼）start決済<BR>  
     *     戻り値が false の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00826<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AioCashinSettlementResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F2633302A1
     */
    protected WEB3AioCashinSettlementResponse startSettlement(WEB3AioCashinSettlementRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "startSettlement(WEB3AioCashinSettlementRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする
        l_request.validate();
        
        //1.2 補助口座オブジェクトを取得する
        //[引数] 
        //補助口座タイプ：1（預り金口座） 
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 注文共通チェックを実施する
        //[引数] 
        //補助口座：get補助口座()の戻り値 
        FinApp l_finApp = GtlUtils.getFinApp();
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //l_aioOrderManager.validateOrder(l_subAccount);
		// 1.3 注文受付可能かどうかのチェックを行う。 
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4 会社別決済機@関条件インスタンスを生成する
        //[引数] 
        //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        //部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        //決済機@関ID： リクエストデータ.決済機@関ID 
        String l_strPaySchemeId = l_request.paySchemeId;
        //インスタンスの生成に失敗した場合は、リクエストデータの決済機@関IDに問題があるものとして、例外をスローする            
        WEB3AioCompanySettleInstitutionConditions l_aioCompanySettleInstitutionConditions = null;
        try
        {
            l_aioCompanySettleInstitutionConditions =
                new WEB3AioCompanySettleInstitutionConditions(
                l_strInstitutionCode, l_strBranchCode, l_strPaySchemeId);      
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        //1.5 決済機@関が受付時間内かどうかをチェックする
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード： 補助口座.部店コード 
        //決済機@関ID： リクエストデータ.決済機@関ID 
        //戻り値が false の場合、
        //例外をスローする
        boolean l_validatePayAcceptPossible = 
            l_aioOrderManager.validatePaySchemeAcceptPossible(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strPaySchemeId);
        if (!l_validatePayAcceptPossible)
        {
            log.debug("決済機@関が受付時間内かどうかをチェック");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00826,
                this.getClass().getName() + "." + l_strMethodName,
                "決済機@関が受付時間外");   
        }
        
        //=========remain zhou-yong NO.3 begin ===========
        
        //1.6 getオンライン入金発注日(String)
        //アイテムの定義
        //決済機@関の発注日を取得する。
        //[引数] 
        //決済機@関ID： リクエストデータ.決済機@関ID 
        Date l_onlineCashinBizDate = 
            l_aioOrderManager.getOnlineCashinBizDate(l_request.paySchemeId);
        
        //取得した発注日 != リクエストデータ.確認時発注日 の場合、
        //例外をスローする。
        if(WEB3DateUtility.compare(l_request.checkDate, l_onlineCashinBizDate) != 0)
        {
            log.debug("取得した発注日 != リクエストデータ.確認時発注日");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                this.getClass().getName() + "." + l_strMethodName,
                "取得した発注日[" + l_onlineCashinBizDate +  "] != " + 
                "リクエストデータ.確認時発注日["+ l_request.checkDate + "]");   

        }
        
        //=========remain zhou-yong NO.3 end ===========
        
        //1.7 入金額の妥当性をチェックする
        //[引数] 
        
        //補助口座：get補助口座()の戻り値 
        //決済機@関ID：リクエストデータ.決済機@関ID 
        //入金額：リクエストデータ.入金額
        String l_strCashinAmt = l_request.cashinAmt;
        
        l_aioOrderManager.validateCreditAmount(
            l_subAccount, l_strPaySchemeId, Double.parseDouble(l_strCashinAmt));
        
        //1.8 代理入力者オブジェクトを取得する
        Trader l_trader = this.getTrader();
        
        //1.9 パスワードのチェックを行う
        //[引数] 
        //代理入力者：get代理入力者()の戻り値 
        //補助口座：get補助口座()の戻り値 
        //パスワード：リクエストデータ.暗証番号
        String l_strPassword = l_request.password;
        
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateTradingPassword(l_trader, l_subAccount, l_strPassword); 
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("パスワードのチェックを行う");
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + l_strMethodName);                          
        }
        
        //1.10 新規の識別コードを取得する
        //[引数] 
        //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 
        //部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
        //銘柄タイプ： 5（現金）
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService = 
            (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.CASH);
        
        //=======remain zhou-yong NO.1 begin ===========
        
		//1.11 発注日を取得する。 
		Date l_datOrderBizDate =  
			WEB3GentradeTradingTimeManagement.getOrderBizDate();
		log.debug("発注日を取得する = " + l_datOrderBizDate);           

        //1.12 金融機@関連携入出金状況テーブルにレコードを追加する
        //［引数］ 
        //代理入力者：get代理入力者()の戻り値 
        //補助口座：get補助口座()の戻り値 
        //決済機@関ID：リクエストデータ.決済機@関ID 
        //金額：リクエストデータ.入金金額 
        //発注日：get発注日()の戻り値
        //識別コード：get新規識別コード()の戻り値 

        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService =
            (WEB3AioMultiBankSettleControlService)Services.getService(WEB3AioMultiBankSettleControlService.class);
        l_aioMultiBankSettleControlService.insertCashTransSituation(
            l_trader, 
            l_subAccount, 
            l_strPaySchemeId, 
            l_strCashinAmt, 
			l_datOrderBizDate, 
            l_strNewNumber);
        
        //1.13 create決済依頼URL(PR層保持情報, SubAccount, String, String)
        //アイテムの定義
        //決済依頼にてリダイレクトするURLを生成する。
        //［引数］ 
        //PR層セッションID： リクエストデータ.PR層保持情報 
        //補助口座： get補助口座()の戻り値 
        //決済機@関ID： リクエストデータ.決済機@関ID 
        //識別コード： get新規識別コード()の戻り値 
        
        String l_strSettlementRequestURL = 
            l_aioMultiBankSettleControlService.createSettlementRequestURL(
                l_request.prSessionUnit,
                l_subAccount,
                l_strPaySchemeId,
                l_strNewNumber);    
            
        //========remain zhou-yong NO.1 end ===========
        
        //1.14 レスポンスデータを生成する
        WEB3AioCashinSettlementResponse l_aioCashinSettlementResponse = 
            (WEB3AioCashinSettlementResponse)l_request.createResponse();
            
        //1.15 以下のとおりにプロパティをセットする。
        //レスポンス.URL = create決済依頼URL()の戻り値
        l_aioCashinSettlementResponse.url = l_strSettlementRequestURL;

        log.exiting(l_strMethodName);
        return l_aioCashinSettlementResponse;
    }
}
@
