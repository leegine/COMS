head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : FXから振替入力サービスImpl(WEB3FXTransFromFXInputServiceImpl)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/24 黄建(中訊) 新規作成
                  : 2006/04/25 肖志偉 (中訊) 仕様変更・モデル535
                  : 2006/05/12 郭英 (中訊) 仕様変更・モデル571
                  : 2006/07/12 丁昭奎 (中訊) 仕様変更・モデルNo.595,No.599
 Revesion History : 2008/09/23 馮海濤 (中訊) 仕様変更・モデルNo.994                 
 Revesion History : 2009/03/12 王志葵 (中訊) 仕様変更・モデルNo.1110
 Revesion History : 2009/06/26 武波 (中訊) 仕様変更・モデル1175
 */

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.WEB3FXTransferAbleAmtDisplayService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXTransFromFXInputRequest;
import webbroker3.aio.message.WEB3FXTransFromFXInputResponse;
import webbroker3.aio.message.WEB3FXTransferAbleAmtUnit;
import webbroker3.aio.service.delegate.WEB3FXTransFromFXInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3GetTransferableAmtDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (FXから振替入力サービスImpl) <BR>
 * FXから振替入力サービス実装クラス <BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FXTransFromFXInputServiceImpl extends WEB3ClientRequestService
    implements WEB3FXTransFromFXInputService
{
    /**
     * @@roseuid 41E776E803D8
     */
    public WEB3FXTransFromFXInputServiceImpl()
    {
    }

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FXTransFromFXInputServiceImpl.class);
            
    /**
     * FXから振替入力サービス処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FXから振替入力）入力画面表示データ取得」 参照。 <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図(「(為替保証金サービスモデル) / FXから振替入力 」<BR>
     * （FXから振替入力）入力画面表示データ取得) <BR>
     * : 1.9 createFX口座情報一覧(String, String, String) <BR>
     * 戻り値がnullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ==========================================================
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BCFA6C0095
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1） get補助口座(SubAccountTypeEnum)
        // 補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2）  validate注文(SubAccount)
        //  以下のチェックを行う。 
        //　@－受付時間チェック 
        //　@－システム停止中チェック 
        //　@－顧客のチェック（Ｙ客、管理ロック等）
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //入出金注文マネージャクラスを取得する。
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
        //チェックを行う
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.3）get会社別FXシステム条件(String, String, String)
        //  会社別FXシステム条件Paramsを取得する。
        //  [引数の設定] 
        //  証券会社コード： 補助口座.証券会社コード 
        //  部店コード：　@補助口座.get取引店.getBranchCode()
        //  FXシステムコード：　@リクエストデータ.FXシステムコード
        WEB3FXDataControlService l_fXDataControlService =
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        String l_strInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = 
            l_subAccount.getMainAccount().getBranch().getBranchCode();
        WEB3FXTransFromFXInputRequest l_WEB3FXTransFromFXInputRequest =
            (WEB3FXTransFromFXInputRequest)l_request;
        String l_strFxSystemCode = l_WEB3FXTransFromFXInputRequest.fxSystemCode;
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams =
                l_fXDataControlService.getCompFxCondition(
                    l_strInstitutionCode, 
                    l_strBranchCode,
                    l_strFxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.4）validate外部システム受付可能(String)
        //  FXシステムの受付時間チェックを行う。 
        //  [引数の設定] 
        //  システムコード：　@会社別FXシステム条件Params.FXシステムコード
        l_aioOrderManager.validateOtherSystemAcceptPossible(
            l_compFxConditionParams.getFxSystemCode());

        //1.5) FXシステム区分別に、取引可能かチェックを行う。
        //[引数の設定]
        //補助口座：　@get補助口座()の戻り値
        //会社別FXシステム条件Params：　@get会社別ＦＸシステム条件()の戻り値
        l_fXDataControlService.validateChangePoss(
            l_subAccount,
            l_compFxConditionParams);

        //1.6）   get発注日()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.7）get振替回数(SubAccount, Date, OrderCategEnum)  
        //当日の振替回数を取得する。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        //注文カテゴリ：　@15（為替保証金振替）   
        int l_intTransferCount = 
            l_aioOrderManager.getTransferCount(
                l_subAccount, 
                l_datOrderBizDate, 
                OrderCategEnum.FX);
        
        //1.8）validate振替可能回数(SubAccount, Date, OrderCategEnum)
        //  振替可能回数のチェックを行う。 
        //  [引数] 
        //  補助口座： get補助口座()の戻り値 
        //  発注日： get発注日()の戻り値 
        //  注文カテゴリ：　@15（為替保証金振替）
        int l_intTransferPossibleCount =
            l_aioOrderManager.validateTransferPossibleCount(
                l_subAccount, 
                l_datOrderBizDate, 
                OrderCategEnum.FX);
        
        //1.9） createFX口座情報一覧(String, String, String)
        //FX口座情報の一覧を取得する。 
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店.getBranchCode() 
        //顧客コード：　@補助口座.getMainAccount().getAccountCode()
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        WEB3FXAccInformationUnit[] l_fXAccInformationUnit =
            l_fXDataControlService.createFXAccInformationUnits(
                l_strInstitutionCode,
                l_strBranchCode,
                l_subAccount.getMainAccount().getAccountCode(),
                l_WEB3FXTransFromFXInputRequest.fxSystemCode);
        if (l_fXAccInformationUnit == null)
        {
            log.debug("FX口座情報取得エラー。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX口座情報取得エラー。");
        }

        WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnits = null;
        //会社別FXシステム条件.FXからの振替可能額取得区分 = １：取得するの場合
        if (WEB3GetTransferableAmtDivDef.GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            //getFXから振替可能額（チェックなし）(補助口座 : SubAccount,
            //会社別FXシステム条件 : CompFxConditionParams)
            WEB3FXTransferAbleAmtDisplayService l_transferAbleAmtDisplayService =
                (WEB3FXTransferAbleAmtDisplayService)Services.getService(
                    WEB3FXTransferAbleAmtDisplayService.class);
            //[引数]
            //補助口座：取得した補助口座
            //会社別FXシステム条件：取得した会社別FXシステム条件
            l_transferAbleAmtUnits =
                l_transferAbleAmtDisplayService.getFXTransferAbleAmtNoCheck(
                    l_subAccount,
                    l_compFxConditionParams);
        }
        //1.10） createResponse( )
        WEB3FXTransFromFXInputResponse l_fxTransFromFXInputResponse =
            (WEB3FXTransFromFXInputResponse) l_request.createResponse();
        
        //1.11） プロパティセット
        //レスポンスデータにプロパティをセットする。

        //FXから振替可能額情報：
        //会社別FXシステム条件.FXからの振替可能額取得区分 = １：取得するの場合
        //getFXから振替可能額（チェックあり）()の戻り値
        if (WEB3GetTransferableAmtDivDef.GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            l_fxTransFromFXInputResponse.fxTransferAbleAmtList = l_transferAbleAmtUnits;
        }

        //会社別FXシステム条件.FXからの振替可能額取得区分 = ０：取得しないの場合
        //FXから振替可能額情報一覧.accCode　@=　@createFX口座情報一覧()の戻り値配列.FX口座情報.accCode
        //FXから振替可能額情報一覧.FX口座情報.courseDiv　@=　@createFX口座情報一覧()の戻り値配列.FX口座情報.courseDiv
        //（*）レスポンス.FXから振替可能額情報配列.振替可能額 = null
        else if (WEB3GetTransferableAmtDivDef.NOT_GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            int l_intCount = l_fXAccInformationUnit.length;
            WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnitNulls =
                new WEB3FXTransferAbleAmtUnit[l_intCount];
            WEB3FXTransferAbleAmtUnit l_transferAbleAmtUnit = null;
            for (int i = 0; i < l_intCount; i++)
            {
                l_transferAbleAmtUnit = new WEB3FXTransferAbleAmtUnit();
                l_transferAbleAmtUnit.fxAccountCode =
                    l_fXAccInformationUnit[i].fxAccountCode;
                l_transferAbleAmtUnit.fxCourseDiv =
                    l_fXAccInformationUnit[i].fxCourseDiv;
                l_transferAbleAmtUnit.transferableAmt = null;
                l_transferAbleAmtUnitNulls[i] = l_transferAbleAmtUnit;
            }
            l_fxTransFromFXInputResponse.fxTransferAbleAmtList = l_transferAbleAmtUnitNulls;
        }

        //振替上限回数：　@validate振替可能回数()の戻り値
        l_fxTransFromFXInputResponse.transferCountUpper = l_intTransferPossibleCount + "";
        //振替回数：　@get振替回数()の戻り値
        l_fxTransFromFXInputResponse.transferCount = l_intTransferCount + "";

        log.exiting(STR_METHOD_NAME);
        return l_fxTransFromFXInputResponse;
    }

}@
