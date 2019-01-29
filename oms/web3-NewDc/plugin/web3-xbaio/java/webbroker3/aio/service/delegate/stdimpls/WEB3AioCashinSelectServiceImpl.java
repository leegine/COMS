head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSelectServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン入金選択サービス実装クラス(WEB3AioCashinSelectServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 屈陽 (中訊) 新規作成
                   2004/10/25 黄建 (中訊) レビュー 
                   2006/04/14 李小健 仕様変更・モデル526
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.message.WEB3AioCashinSelectRequest;
import webbroker3.aio.message.WEB3AioCashinSelectResponse;
import webbroker3.aio.message.WEB3AioSelectSettleInstitutionUnit;
import webbroker3.aio.service.delegate.WEB3AioCashinSelectService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (オンライン入金選択サービスImpl)<BR>
 * オンライン入金選択サービス実装クラス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSelectServiceImpl extends WEB3ClientRequestService implements WEB3AioCashinSelectService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSelectServiceImpl.class);
         
    /**
     * オンライン入金選択サービス処理を実施する。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（オンライン入金選択）選択画面表示データ取得」 参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F1F35B0215
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
        WEB3AioCashinSelectRequest l_aioCashinSelectRequest = (WEB3AioCashinSelectRequest)l_request;
        
        //1.1補助口座オブジェクトを取得する
        //[引数] 
        //補助口座タイプ：1（預り金口座） 
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //AIO注文マネージャオブジェクトを取得する
        /*
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.AIO);         
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //1.2注文共通チェックを実施する
        l_aioOrderManager.validateOrder(l_subAccount);
        */
		// 1.2注文受付可能かどうかのチェックを行う。 
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
                      
        //マルチバンク決済制御サービス実装オブジェクトを取得する
        WEB3AioMultiBankSettleControlService l_multiBankSettleControlService = 
            (WEB3AioMultiBankSettleControlService)Services.getService(
            WEB3AioMultiBankSettleControlService.class);
        
        //1.3getキャリア区分(String)
        OpLoginSecurityService l_opLoginSecurityService = 
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv = l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        String l_strCareerDiv = l_multiBankSettleControlService.getCareerDiv(l_strOrderRootDiv);
        
        //1.4選択可能な決済機@関の一覧を取得する
        //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        //部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        WEB3AioSelectSettleInstitutionUnit[] l_aioSelectSettleInstitutionUnit = 
            l_multiBankSettleControlService.getSelectPaySchemeDetails(l_strInstitutionCode, l_strBranchCode, l_strCareerDiv);
        
        //1.5レスポンスデータを生成する
        WEB3AioCashinSelectResponse l_response = 
            (WEB3AioCashinSelectResponse)l_aioCashinSelectRequest.createResponse();
        
        //1.6レスポンス.決済機@関一覧 = マルチバンク決済制御サービスImpl.get選択決済機@関明細()の戻り値
        l_response.selectSettleInstitutionUnit = l_aioSelectSettleInstitutionUnit;
                 
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
