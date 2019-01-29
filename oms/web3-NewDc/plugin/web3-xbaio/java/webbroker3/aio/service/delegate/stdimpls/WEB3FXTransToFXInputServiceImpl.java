head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替入力サービスImpl(WEB3FXTransToFXInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/1/21 屈陽 (中訊) 新規作成
                   2006/4/27 周捷(中訊) 仕様変更 NO.541
                   2006/07/12 丁昭奎 (中訊) 仕様変更・モデルNo.595,600
                   2006/08/24 鈴木 (SCS) 仕様変更・モデルNo.630
Revesion History : 2008/09/23 馮海濤 (中訊) 仕様変更・モデルNo.996
Revesion History : 2009/03/12 王志葵 (中訊) 仕様変更・モデルNo.1112
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXTransToFXInputRequest;
import webbroker3.aio.message.WEB3FXTransToFXInputResponse;
import webbroker3.aio.service.delegate.WEB3FXTransToFXInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioTransferDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

/**
 * (FXへの振替入力サービスImpl) <BR>
 * FXへの振替入力サービス実装クラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransToFXInputServiceImpl extends WEB3ClientRequestService
    implements WEB3FXTransToFXInputService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXInputServiceImpl.class);  
    
    /**
     * @@roseuid 41E780B101C5
     */
    public WEB3FXTransToFXInputServiceImpl()
    {
    }

    /**
     * FXへの振替入力サービス処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FXへの振替入力）入力画面表示データ取得」 参照。 <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図(「(為替保証金サービスモデル) / FXから振替入力 」<BR>
     * （FXから振替入力）入力画面表示データ取得)<BR> 
     * <BR>: 1.10 createFX口座情報一覧(String, String, String) <BR>
     * 戻り値がnullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ==========================================================
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@roseuid 41BCF31B0066
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1. get補助口座(SubAccountTypeEnum)
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2. validate注文(SubAccount)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.3. get会社別FXシステム条件(String, String, String)
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店.getBranchCode()
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        //(1)produce FXデータ制御サービス 
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //(2)
        CompFxConditionParams l_params;        
        WEB3FXTransToFXInputRequest l_fxTransToFXInputRequest = null;
        
        if (l_request instanceof WEB3FXTransToFXInputRequest)
        {
        	l_fxTransToFXInputRequest = (WEB3FXTransToFXInputRequest)l_request;              
        }        
        else
        {
            log.debug(
                "リクエストデータが"
                + " WEB3FXTransToFXInputRequest以外である, but is " + 
                l_request.getClass().getName());
            
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        try
        {
            l_params =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_fxTransToFXInputRequest.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("get会社別FXシステム条件", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //getFX振替条件マスタ(long, String)
        //【引数】
        //FXシステム条件ID　@= 会社別FXシステム条件Params.FXシステム条件ID
        //振替区分 = 1：出金
        FxTransferMasterParams l_fxTransferMasterParams =
            l_dataControlService.getFxTransferMasterParams(
                l_params.getFxSystemId(),
                WEB3AioTransferDivDef.CASHOUT);

        //1.4. validate外部システム受付可能(String)
        //[引数の設定] 
        //システムコード：　@会社別FXシステム条件Params.FXシステムコード
        l_orderManager.validateOtherSystemAcceptPossible(l_params.getFxSystemCode());

        //1.5) FXシステム区分別に、取引可能かチェックを行う。
        //[引数の設定]
        //補助口座：　@get補助口座()の戻り値
        //会社別FXシステム条件Params：　@　@get会社別ＦＸシステム条件()の戻り値
        l_dataControlService.validateChangePoss(
            l_subAccount,
            l_params);

        //1.6. get発注日()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //1.7. get受渡日(Date, SubAccount, String)
        //受渡日を取得する。 
        //[引数の設定] 
        //発注日： get発注日()の戻り値 
        //補助口座：　@get補助口座()の戻り値
        //受渡日設定区分：　@getFX振替条件マスタ().受渡日設定区分
        Date l_datDeliveryDate = l_dataControlService.getDeliveryDate(
            l_datOrderBizDate,
            l_subAccount,
            l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.8. get振替回数(SubAccount, Date, OrderCategEnum)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        //注文カテゴリ：　@15（為替保証金振替）
        int l_intTransferCount = 
            l_orderManager.getTransferCount(
                l_subAccount, l_datOrderBizDate, OrderCategEnum.FX);
        
        //1.9. validate振替可能回数(SubAccount, Date, OrderCategEnum)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        //注文カテゴリ：　@15（為替保証金振替）
        int l_intPossibleCount = 
            l_orderManager.validateTransferPossibleCount(
                l_subAccount, l_datOrderBizDate, OrderCategEnum.FX);
        
        //1.10. get出金可能額(補助口座 : 補助口座, 受渡日 : Date) 
        //出金可能額を取得する。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //受渡日： get受渡日()の戻り値 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);

        // パラメータ・受渡日の決定
        WEB3GentradeSubAccount l_genSubAccount = (WEB3GentradeSubAccount) l_subAccount;

        double l_dblCashoutPossiblePrice =  
            l_tPTradingPowerService.getPaymentTradingPower(
                    l_genSubAccount, l_datDeliveryDate);
                
        //1.11. createFX口座情報一覧(String, String, String)
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店.getBranchCode() 
        //顧客コード：　@補助口座.getMainAccount().getAccountCode()
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        WEB3FXAccInformationUnit[] l_accInformationUnit =
            l_dataControlService.createFXAccInformationUnits(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_fxTransToFXInputRequest.fxSystemCode);
                
        if (l_accInformationUnit == null)
        {
            log.debug("FX口座情報取得エラー");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                "FX口座情報取得エラー");
        }
        
        //1.12. createResponse()
        WEB3FXTransToFXInputResponse l_response = 
            (WEB3FXTransToFXInputResponse)l_request.createResponse();
            
        //1.13. レスポンスデータにプロパティをセットする。
        //FX口座情報一覧：createFX口座情報一覧()の戻り値
        l_response.fxAccInformationList = l_accInformationUnit;
        
        //振替上限回数：validate振替可能回数()の戻り値
        l_response.transferCountUpper = String.valueOf(l_intPossibleCount);
        
        //振替回数：get振替回数()の戻り値
        l_response.transferCount = String.valueOf(l_intTransferCount);
        
        //振替可能額：get出金可能額()の戻り値
        l_response.transferableAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblCashoutPossiblePrice);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}@
