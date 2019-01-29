head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン入金入力サービス実装クラス(WEB3AioCashinInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 屈陽 (中訊) 新規作成
                   2004/10/25 黄建 (中訊) レビュー 
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.WEB3AioCompanySettleInstitutionConditions;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSettleInstitution;
import webbroker3.aio.message.WEB3AioCashinInputRequest;
import webbroker3.aio.message.WEB3AioCashinInputResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (オンライン入金入力サービスImpl)<BR>
 * オンライン入金入力サービス実装クラス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinInputServiceImpl extends WEB3ClientRequestService implements WEB3AioCashinInputService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinInputServiceImpl.class);        
    
    /**
     * オンライン入金入力サービス処理を実施する。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（オンライン入金入力）入力画面表示データ取得」 参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / オンライン入金入力 」<BR>
     *  （オンライン入金入力）入力画面表示データ取得　@)<BR>
     * 　@　@　@:  1.4.会社別決済機@関条件(String, String, String)<BR>   
     *     インスタンスの生成に失敗した場合は、
     *     リクエストデータの決済機@関IDに問題があるものとして、<BR>
     * 例外をスローする。<BR>
     * 
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00763<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / オンライン入金入力 」<BR>
     *    （オンライン入金入力）入力画面表示データ取得 )<BR>
     * 　@　@　@:  1.5.validate決済機@関受付可能(String)<BR>   
     *     戻り値が false の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00826<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F240CB0198
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException 
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        WEB3AioCashinInputRequest l_aioCashinInputRequest =
            (WEB3AioCashinInputRequest)l_request;
        
        //1.1 リクエストデータの整合性をチェックする
        l_aioCashinInputRequest.validate();
        
        //1.2 補助口座オブジェクトを取得する 
        //[引数]補助口座タイプ：1（預り金口座） 
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //AIO注文マネージャオブジェクトを取得する
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //1.3 注文共通チェックを実施する
        //l_aioOrderManager.validateOrder(l_subAccount);
		//1.3 注文受付可能かどうかのチェックを行う。
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        //部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        //決済機@関ID： リクエストデータ.決済機@関ID 
        String l_strPaySchemeID = l_aioCashinInputRequest.paySchemeId;
        
        //1.4 会社別決済機@関条件インスタンスを生成する
        ////インスタンスの生成に失敗した場合は、
        //リクエストデータの決済機@関IDに問題があるものとして、例外をスローする
        WEB3AioCompanySettleInstitutionConditions l_AioCompanySettleInstitutionConditions = null;
        try
        {
            l_AioCompanySettleInstitutionConditions = 
                new WEB3AioCompanySettleInstitutionConditions(
                    l_strInstitutionCode, l_strBranchCode, l_strPaySchemeID);                            
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        //1.5 決済機@関が受付時間かどうかをチェックする
        //validate決済機@関受付可能(String)
        //戻り値が false の場合、例外をスローする
        boolean l_blnPaySchemeAcceptPossible = l_aioOrderManager.validatePaySchemeAcceptPossible(
            l_strInstitutionCode,
            l_strBranchCode,
            l_aioCashinInputRequest.paySchemeId);
        if (!l_blnPaySchemeAcceptPossible)
        {
            log.debug("error in validate決済機@関受付可能");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00826,
                this.getClass().getName() + "." + l_strMethodName,
                "validate決済機@関受付可能がfalse");
        }

        //1.6 提携決済機@関インスタンスを生成する
        //[引数]決済機@関ID：リクエストデータ.決済機@関ID 
        WEB3AioSettleInstitution l_aioSettleInstitution =
            new WEB3AioSettleInstitution(l_aioCashinInputRequest.paySchemeId);

        //1.7 決済機@関名を取得する
        String l_strAioSettleInstitutionName = l_aioSettleInstitution.getName();

        //1.8 1回当たりの上限入金金額を取得する
        double l_dblMaxAmountOnce = l_AioCompanySettleInstitutionConditions.getMaxAmountOnce();

        //1.9 1回当たりの下限入金金額を取得する
        double l_dblMinAccoutOnce = l_AioCompanySettleInstitutionConditions.getMinAmountOnce();

        //1.10 単位入金金額を取得する
        double l_dblAmountUnit = l_AioCompanySettleInstitutionConditions.getAmountUnit();

        //1.11 1日当たりの上限入金金額を取得する
        double l_dblMaxAmountDaily = l_AioCompanySettleInstitutionConditions.getMaxAmountDaily();

        //1.12 1日の上限入金回数を取得する
        double l_dblMaxCount = l_AioCompanySettleInstitutionConditions.getMaxCount();

        //1.13 レスポンスデータを生成する
        WEB3AioCashinInputResponse l_aioCashinInputResponse = 
            (WEB3AioCashinInputResponse)l_aioCashinInputRequest.createResponse();

        //1.14 以下のとおりにプロパティをセットする
        //レスポンス.決済機@関ID = リクエストデータ.決済機@関ID
        l_aioCashinInputResponse.paySchemeId = l_aioCashinInputRequest.paySchemeId;
        //レスポンス.決済機@関名 = 提携決済機@関.get名称()の戻り値
        l_aioCashinInputResponse.paySchemeName = l_strAioSettleInstitutionName;
        //レスポンス.上限金額 = 会社別決済機@関条件.get上限入金金額（1回当たり）()の戻り値
        l_aioCashinInputResponse.maxAmount = WEB3StringTypeUtility.formatNumber(l_dblMaxAmountOnce);
        //レスポンス.下限金額 = 会社別決済機@関条件.get下限入金金額（1回当たり）()の戻り値
        l_aioCashinInputResponse.minAmount = WEB3StringTypeUtility.formatNumber(l_dblMinAccoutOnce);
        //レスポンス.振込単位 = 会社別決済機@関条件.get単位入金金額()の戻り値
        l_aioCashinInputResponse.transferUnit = WEB3StringTypeUtility.formatNumber(l_dblAmountUnit);
        //レスポンス.総入金上限額 = 会社別決済機@関条件.get上限入金金額（1日当たり）()の戻り値
        l_aioCashinInputResponse.maxTotalAmount = WEB3StringTypeUtility.formatNumber(l_dblMaxAmountDaily);
        //レスポンス.入金上限回数 = 会社別決済機@関条件.get上限回数（1日当たり）()の戻り値
        l_aioCashinInputResponse.cashinMaxTimes = WEB3StringTypeUtility.formatNumber(l_dblMaxCount);

        log.exiting(l_strMethodName); 

        return l_aioCashinInputResponse;                     

    }
}
@
