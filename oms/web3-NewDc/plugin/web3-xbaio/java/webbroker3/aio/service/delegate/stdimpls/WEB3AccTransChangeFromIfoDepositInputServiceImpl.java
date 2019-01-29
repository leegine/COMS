head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeFromIfoDepositInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金から振替入力サービスImpl(WEB3AccTransChangeFromIfoDepositInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/26 周勇(中訊) レビュー
                   2004/12/09 周勇 (中訊) 残対応
Revesion History : 2007/08/23 武波 (中訊) 仕様変更・モデル752
Revesion History : 2009/03/16 車進 (中訊) 仕様変更・モデル1141
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositInputResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeFromIfoDepositInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (証拠金から振替入力サービスImpl)<BR>
 * 証拠金から振替入力サービス実装クラス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeFromIfoDepositInputServiceImpl extends WEB3ClientRequestService implements WEB3AccTransChangeFromIfoDepositInputService 
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3AccTransChangeFromIfoDepositInputServiceImpl.class);
    
    /**
     * 証拠金から振替入力サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証拠金から振替入力）入力画面表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413435FC014D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1) 補助口座オブジェクトを取得する。 
        // [引数] 
        // 補助口座タイプ： 7（証拠金口座） 
        SubAccount l_subAccount = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_tradingModule.getOrderManager();
        
        // 1.2)　@validate注文(SubAccount)
        // －受付時間チェック 
        // －システム停止中チェック 
        // －顧客のチェック（Ｙ客、管理ロック等）
        l_orderManager.validateOrder(l_subAccount);
        
        // 1.3) 先物取引口座を開設しているかをチェックする
        l_orderManager.validateOpenFuturesTradedAccount(l_subAccount);
        
        // 1.4) get発注日()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        // 1.7) 当日の振替回数を取得する。 
        // [引数] 
        // 補助口座： get補助口座()の戻り値 
        // 発注日： get発注日()の戻り値 
        // 注文カテゴリ：13（振替）
        int l_intTransferCount = l_orderManager.getTransferCount(
                l_subAccount,l_datBizDate, OrderCategEnum.CASH_TRANSFER);       
        
        //=========== remain zhou-yong NO.1 bengin =================
        
        //1.8) get証拠金計算(補助口座 : 補助口座)
        //アイテムの定義
        //証拠金計算インスタンスを生成する。
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService) Services.getService(
                WEB3IfoDepositCalcService.class);

        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        WEB3IfoDepositCalc l_ifoDepositCalc = l_service.getIfoDepositCalc(l_gentradeSubAccount);

        //証拠金振替の指定日を取得する。
        int l_intBizDate = l_orderManager.getMarginTransferDesignatedDate(l_datBizDate);

        //1.9)  calc証拠金残高(指定日 : int)
        //証拠金残高を算出する
        //[引数] 
        //指定日： get証拠金振替指定日()の戻り値
        double l_dblCalcIfoDepositBalance = 
            l_ifoDepositCalc.calcIfoDepositBalance(l_intBizDate);
        
        //1.10) calc証拠金振替余力額( )
        //アイテムの定義
        //振替可能額を算出する。
        double l_dblCalcIfoDepositTransferableAmount = 
            l_ifoDepositCalc.calcIfoDepositTransferableAmount();
        
        //1.11) get補助口座(SubAccountTypeEnum) （補助口座②） 
        //アイテムの定義
        //補助口座オブジェクトを取得する。
        //[引数] 
        //補助口座タイプ： 1（預り金口座） 
        SubAccount l_subAccount2 = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        // 1.12) get出金可能額(補助口座 : 補助口座, 受渡日 : Date) 
        //出金可能額を取得する。 
        //[引数] 
        //補助口座： get補助口座()の戻り値（補助口座②） 
        //受渡日： get発注日()の戻り値 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount2 = (WEB3GentradeSubAccount)l_subAccount2;
        //出金可能額
        double l_dblCashoutPossiblePrice =  l_tPTradingPowerService.getPaymentTradingPower(
            l_gentradeSubAccount2, l_datBizDate);

        //=========== remain zhou-yong NO.1 bengin =================
        
        // 1.13) レスポンスデータを生成する
        WEB3AccTransChangeFromIfoDepositInputResponse l_response = 
            (WEB3AccTransChangeFromIfoDepositInputResponse) l_request.createResponse();
            
        // 1.14) レスポンスのプロパティセット
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        l_response.changeCountUpper = 
            ((MainAccountRow)l_mainAccount.getDataSourceObject()).getTransferCount() + "";
        l_response.changeCount = l_intTransferCount + "";
        l_response.changePossAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblCalcIfoDepositTransferableAmount);
        l_response.ifoDepositBal = 
            WEB3StringTypeUtility.formatNumber(l_dblCalcIfoDepositBalance);
        
        //レスポンス.お預かり金残高 = 取引余力サービス.get出金可能額()の戻り値
        l_response.depositBal = WEB3StringTypeUtility.formatNumber(
            l_dblCashoutPossiblePrice);

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
}
@
