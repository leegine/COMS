head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡入力サービス実装クラス(WEB3AioCashinNoticeInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 周勇 (中訊) 新規作成
                   2004/10/25 黄建 (中訊) レビュー 
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioCashinNoticeInputResponse;
import webbroker3.aio.message.WEB3AioFinancialInstitutionUnit;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金連絡入力サービスImpl)<BR>
 * 入金連絡入力サービス実装クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0 
 */
public class WEB3AioCashinNoticeInputServiceImpl extends WEB3ClientRequestService implements WEB3AioCashinNoticeInputService 
{
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeInputServiceImpl.class);
        
    /**
     * 入金連絡入力サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入金連絡入力）入力画面表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40EBFC430168
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)注文受付可能かどうかのチェックを行う。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // 1.2)補助口座を取得する。
        //[引数] 
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        // 1.3)口座（顧客）オブジェクトを取得する。 
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount) this.getMainAccount();
        
        // 1.4)口座番号（顧客コード）を取得する。 
        String l_strAccountCode = l_gentradeMainAccount.getAccountCode().substring(0, 6);
        
        // 1.5)顧客の表示名を取得する。 
        String l_strPersonNameDetails = l_gentradeMainAccount.getDisplayAccountName();
        
        // 1.6)顧客行オブジェクトを取得する。 
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_gentradeMainAccount.getDataSourceObject();
        MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);
        
        // 1.7)メールアドレスを取得する。
        String l_strEmailAddress = l_mainAccountParams.getEmailAddress();
        
        // 1.8)振込先記入機@関明細の配列を取得する。
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_AioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        WEB3AioFinancialInstitutionUnit[] l_aioFinancialInstitutionUnit = 
            l_AioOrderManager.createFinancialInstitutionDetails(l_subAccount);
        
        // 1.9)レスポンスデータを生成する。
        WEB3AioCashinNoticeInputResponse l_aioCashinNoticeInputResponse = 
            (WEB3AioCashinNoticeInputResponse)l_request.createResponse();

        // 1.10)(*)以下のとおりにプロパティをセットする。
        //レスポンス.顧客名 ＝ 顧客.get顧客表示名()の戻り値
        l_aioCashinNoticeInputResponse.accountName =  l_strPersonNameDetails;
        
        //レスポンス.顧客コード ＝ 顧客.getAccountCode()の戻り値
        l_aioCashinNoticeInputResponse.accountCode = l_strAccountCode;
        
        //レスポンス.メールアドレス ＝ MainAccountParams.getEmailAddress()の戻り値
        l_aioCashinNoticeInputResponse.emailAddress = l_strEmailAddress;
        
        //レスポンス.振込先金融機@関一覧 ＝ 入出金注文マネージャ.create振込先金融機@関明細()の戻り値
        l_aioCashinNoticeInputResponse.financialInstitutionUnits = l_aioFinancialInstitutionUnit;    

        log.exiting(STR_METHOD_NAME);
        return l_aioCashinNoticeInputResponse;
    }
}
@
