head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : FXから振替サービスImpl(WEB3FXTransFromFXServiceImpl)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/21 黄建(中訊) 新規作成
                  : 2006/04/25 肖志偉 (中訊) 仕様変更・モデル535,536,544
                  : 2006/06/05 鈴木（SCS） 仕様変更 No.589・DB更新仕様 091
                  : 2006/07/12 丁昭奎 (中訊) 仕様変更・モデルNo.599
                  : 2006/08/23 鈴木(SCS)　@モデルNo.630、NO.631対応
                  : 2006/10/12 何文敏 (中訊) 仕様変更・モデルNo.666
 Revesion History : 2007/07/12 孫洪江(中訊) 仕様変更モデルNo.731
 Revision History : 2007/07/28 孟亜南 (中訊) 仕様変更モデル742
 Revesion History : 2008/04/09 王志葵 (中訊) 仕様変更モデル.832,842,844
 Revesion History : 2008/04/23 王志葵 (中訊) 仕様変更モデル.845
 Revesion History : 2008/04/28 王志葵 (中訊) 仕様変更モデル.847
                  : 2008/05/08 佐藤 (SCS) 仕様変更・モデル832
                  : 2008/05/23 三島 (SCS)
 Revesion History : 2008/05/20 柴双紅 (中訊) 仕様変更モデル858,868
 Revesion History : 2008/06/18 柴双紅 (中訊) 仕様変更モデル900
 Revesion History : 2008/09/24 馮海濤 (中訊) 仕様変更モデル995、1041
 Revesion History : 2008/11/19 SCS大嶋 仕様変更モデル.1086
                  : 2009/02/10 萩原 (SCS)仕様変更・モデル1102,1103
 Revesion History : 2009/03/11 柴双紅 (中訊) 仕様変更モデル1111、1144、1147、1148
 Revesion History : 2009/04/20 車進 (中訊) 仕様変更・モデル1162
 Revesion History : 2009/06/26 武波 (中訊) 仕様変更・モデル1176、1187
 Revesion History : 2009/09/16 孟亞南 (中訊) 仕様変更・モデル1197,1215
 */

package webbroker3.aio.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrder;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXTransConnection;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.WEB3FXTelegramProcessService;
import webbroker3.aio.WEB3FXTransferAbleAmtDisplayService;
import webbroker3.aio.WEB3FXTransferOrderUpdateInterceptor;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioCashInOutAmountDivDef;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTransFromFXAskingRequest;
import webbroker3.aio.message.WEB3FXTransFromFXAskingResponse;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteResponse;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXTransFromFXConfirmRequest;
import webbroker3.aio.message.WEB3FXTransFromFXConfirmResponse;
import webbroker3.aio.message.WEB3FXTransferAbleAmtUnit;
import webbroker3.aio.service.delegate.WEB3FXTransFromFXService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioSoapConnectDivDef;
import webbroker3.common.define.WEB3AioTransferDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FxDeliveryDateInsertCheckDef;
import webbroker3.common.define.WEB3FxSystemCodeDef;
import webbroker3.common.define.WEB3FxSystemDivDef;
import webbroker3.common.define.WEB3FxTransferMasterRemarkCodeDef;
import webbroker3.common.define.WEB3GetTransferableAmtDivDef;
import webbroker3.common.define.WEB3GftErrorReasonCodeDef;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (FXから振替サービスImpl) <BR>
 * FXから振替サービスImpl
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FXTransFromFXServiceImpl extends WEB3ClientRequestService
    implements WEB3FXTransFromFXService
{
    /**
     * @@roseuid 41E7721102CE
     */
    public WEB3FXTransFromFXServiceImpl()
    {
    }
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FXTransFromFXServiceImpl.class);

    /**
     * FXから振替サービス処理を行う。 <BR>
     * <BR>
     * リクエストデータの型により、以下のいずれかのメソッドをコールする。 <BR>
     * ・validate注文() <BR>
     * ・start注文() <BR>
     * ・submit注文() <BR>
     * ・submit注文()（SOAP接続)<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BE51600267
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
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FXTransFromFXConfirmRequest)
        {
            //validate注文()メソッド
            l_response =
                this.validateOrder(
                    (WEB3FXTransFromFXConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FXTransFromFXCompleteSoapRequest)
        {
            //・submit注文()（SOAP接続)
            l_response =
                this.submitOrderSoap(
                    (WEB3FXTransFromFXCompleteSoapRequest)l_request);
        }
        else if (l_request instanceof WEB3FXTransFromFXAskingRequest)
        {
            //start注文
            l_response =
                this.startOrder(
                    (WEB3FXTransFromFXAskingRequest) l_request);
        }
        else if (l_request instanceof WEB3FXTransFromFXCompleteRequest)
        {
            //submit注文()メソッド
            l_response =
                this.submitOrder(
                    (WEB3FXTransFromFXCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                    "リクエストデータが"
                    + " WEB3FXTransFromFXConfirmRequest "
                    + "と WEB3FXTransFromFXAskingRequest "
                    + "と WEB3FXTransFromFXCompleteRequest"
                    + "と WEB3FXTransFromFXCompleteSoapRequest以外である, but is "
                    + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate注文) <BR>
     * 振替注文の発注審査を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FXから振替）validate注文」参照。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransFromFXConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BE52430332
     */
    protected WEB3FXTransFromFXConfirmResponse validateOrder(
        WEB3FXTransFromFXConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FXTransFromFXConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1） FXから振替確認リクエスト. validate( ) 
        l_request.validate();
        
        //1.2） get補助口座(SubAccountTypeEnum)
        // 補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3） validate注文(SubAccount)
        //  以下のチェックを行う。 
        //　@－受付時間チェック 
        //　@－システム停止中チェック 
        //　@－顧客のチェック（Ｙ客、管理ロック等）
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //入出金注文マネージャクラスを取得する。
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //チェックを行う
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4）get会社別FXシステム条件(String, String, String)
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
        String l_strFxSystemCode = l_request.fxSystemCode;
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams =
                l_fXDataControlService.getCompFxCondition(
                    l_strInstitutionCode, l_strBranchCode, l_strFxSystemCode);
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

        //getFX振替条件マスタ(long, String)
        //【引数】
        //  FXシステム条件ID　@= 会社別FXシステム条件Params.FXシステム条件ID
        //  振替区分 = 0：入金
        FxTransferMasterParams l_fxTransferMasterParams =
            l_fXDataControlService.getFxTransferMasterParams(
                l_compFxConditionParams.getFxSystemId(),
                WEB3AioTransferDivDef.CASHIN);

        //1.5）validate外部システム受付可能(String)
        //  FXシステムの受付時間チェックを行う。 
        //  [引数の設定] 
        //  システムコード：　@会社別FXシステム条件Params.FXシステムコード
        l_aioOrderManager.validateOtherSystemAcceptPossible(
            l_compFxConditionParams.getFxSystemCode());

        //validate振替可能(SubAccount, 会社別FXシステム条件Params)
        //FXシステム区分別に、取引可能かチェックを行う。
        //[引数の設定]
        //補助口座：　@get補助口座()の戻り値
        //会社別FXシステム条件Params：　@get会社別ＦＸシステム条件()の戻り値
        l_fXDataControlService.validateChangePoss(
            l_subAccount,
            l_compFxConditionParams);

        //1.7）get発注日()
        Date l_datOrderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get受渡日(Date, 補助口座, String)
        //[引数の設定]
        //発注日：　@get発注日()の戻り値
        //補助口座：　@get補助口座()の戻り値
        //受渡日設定区分：　@getFX振替条件マスタ()の戻り値.受渡日設定区分
        Date l_datDeliveryDate =
            l_fXDataControlService.getDeliveryDate(
                l_datOrderBizDate,
                l_subAccount,
                l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.9） validate振替可能回数(SubAccount, Date, OrderCategEnum)
        //  振替可能回数のチェックを行う。 
        //  [引数] 
        //  補助口座： get補助口座()の戻り値 
        //  発注日： get発注日()の戻り値 
        //  注文カテゴリ：　@15（為替保証金振替）
    
        l_aioOrderManager.validateTransferPossibleCount(
            l_subAccount, 
            l_datOrderBizDate, 
            OrderCategEnum.FX);

        WEB3FXTransferAbleAmtUnit l_transferAbleAmtUnit = null;
        //会社別FXシステム条件.FXからの振替可能額取得区分 = １：取得するの場合
        if (WEB3GetTransferableAmtDivDef.GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            //getFXから振替可能額（チェックあり）(補助口座 : SubAccount, 
            //会社別FXシステム条件 : CompFxConditionParams, 振替金額 : String, コース区分 : String)
            WEB3FXTransferAbleAmtDisplayService l_transferAbleAmtDisplayService =
                (WEB3FXTransferAbleAmtDisplayService)Services.getService(
                    WEB3FXTransferAbleAmtDisplayService.class);
            //[引数]
            //補助口座：取得した補助口座
            //会社別FXシステム条件：取得した会社別FXシステム条件
            //振替金額：リクエスト.振替金額
            //コース区分：リクエスト.FX口座情報.コース区分
            String l_strFxCourseDiv = null;
            if (l_request.fxAccInformationUnit != null)
            {
                l_strFxCourseDiv = l_request.fxAccInformationUnit.fxCourseDiv;
            }
            l_transferAbleAmtUnit =
                l_transferAbleAmtDisplayService.getFXTransferAbleAmtCheck(
                    l_subAccount,
                    l_compFxConditionParams,
                    l_request.transferAmount,
                    l_strFxCourseDiv);
        }

        //1.10） createResponse( )
        WEB3FXTransFromFXConfirmResponse 
              l_FXTransFromFXConfirmResponse =
                  (WEB3FXTransFromFXConfirmResponse) l_request.createResponse();  
        
        //1.11) (*)プロパティセット
        //レスポンスデータにプロパティセットする
        //受渡日:get受渡日()の戻り値
        l_FXTransFromFXConfirmResponse.deliveryDate = l_datDeliveryDate;

        //FXから振替可能額情報：
        //会社別FXシステム条件.FXからの振替可能額取得区分 = １：取得するの場合
        //getFXから振替可能額（チェックあり）()の戻り値
        if (WEB3GetTransferableAmtDivDef.GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            l_FXTransFromFXConfirmResponse.fxTransferAbleAmtUnit = l_transferAbleAmtUnit;
        }
        //会社別FXシステム条件.FXからの振替可能額取得区分 = ０：取得しないの場合
        //　@　@レスポンス.FXから振替可能額情報.口座番号 = リクエストデータ.FX口座情報.口座番号
        //       (FX口座情報がnullの場合、nullをセット)
        //　@　@レスポンス.FXから振替可能額情報.コース区分 = リクエストデータ.FX口座情報.コース区分
        //       (FX口座情報がnullの場合、nullをセット)
        //　@　@（*）レスポンス.FXから振替可能額情報.振替可能額 = null
        else if (WEB3GetTransferableAmtDivDef.NOT_GET.equals(l_compFxConditionParams.getGetTransferableAmtDiv()))
        {
            WEB3FXTransferAbleAmtUnit l_transferAbleAmtUnitNull =
                new WEB3FXTransferAbleAmtUnit();

            String l_strFxAccountCode = null;
            String l_strFxCourseDiv = null;
            if (l_request.fxAccInformationUnit != null)
            {
                l_strFxAccountCode = l_request.fxAccInformationUnit.fxAccountCode;
                l_strFxCourseDiv = l_request.fxAccInformationUnit.fxCourseDiv;
            }

            l_transferAbleAmtUnitNull.fxAccountCode = l_strFxAccountCode;
            l_transferAbleAmtUnitNull.fxCourseDiv = l_strFxCourseDiv;
            l_transferAbleAmtUnitNull.transferableAmt = null;
            l_FXTransFromFXConfirmResponse.fxTransferAbleAmtUnit = l_transferAbleAmtUnitNull;
        }

        log.exiting(STR_METHOD_NAME);
        return l_FXTransFromFXConfirmResponse;
    }

    /**
     * (start注文) <BR>
     * 振替注文の依頼処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FXから振替）start注文」参照。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransFromFXAskingResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BE52A90054
     */
    protected WEB3FXTransFromFXAskingResponse startOrder(
        WEB3FXTransFromFXAskingRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "startOrder(WEB3FXTransFromFXAskingRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1） FXから振替依頼リクエスト. validate( ) 
        l_request.validate();
        
        //1.2） get補助口座(SubAccountTypeEnum)
        // 補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3） validate注文(SubAccount)
        //  以下のチェックを行う。 
        //　@－受付時間チェック 
        //　@－システム停止中チェック 
        //　@－顧客のチェック（Ｙ客、管理ロック等）
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //入出金注文マネージャクラスを取得する。
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //チェックを行う
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4）get会社別FXシステム条件(String, String, String)
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
        String l_strFxSysCode = l_request.fxSystemCode;
        CompFxConditionParams l_compFxConditionParams = null;        
        try
        {
            l_compFxConditionParams =
                l_fXDataControlService.getCompFxCondition(
                    l_strInstitutionCode, 
                    l_strBranchCode,
                    l_strFxSysCode);
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

        //getFX振替条件マスタ(long, String)
        //【引数】
        //  FXシステム条件ID　@= 会社別FXシステム条件Params.FXシステム条件ID
        //  振替区分 = 0：入金
        FxTransferMasterParams l_fxTransferMasterParams =
            l_fXDataControlService.getFxTransferMasterParams(
                l_compFxConditionParams.getFxSystemId(),
                WEB3AioTransferDivDef.CASHIN);

        //1.5） validate外部システム受付可能(String)
        //  FXシステムの受付時間チェックを行う。 
        //  [引数の設定] 
        //  システムコード：　@会社別FXシステム条件Params.FXシステムコード
        l_aioOrderManager.validateOtherSystemAcceptPossible(
            l_compFxConditionParams.getFxSystemCode());

        //validate振替可能(SubAccount, 会社別FXシステム条件Params)
        //FXシステム区分別に、取引可能かチェックを行う。
        //[引数の設定]
        //補助口座：　@get補助口座()の戻り値
        //会社別FXシステム条件Params：　@get会社別ＦＸシステム条件()の戻り値
        l_fXDataControlService.validateChangePoss(
            l_subAccount,
            l_compFxConditionParams);

        //1.7） get発注日()
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get受渡日(Date, 補助口座, String)
        //[引数]
        //発注日：　@get発注日()の戻り値
        //補助口座：　@get補助口座()の戻り値
        //受渡日設定区分：　@getFX振替条件マスタ().受渡日設定区分
        Date l_datDeliveryDate =
            l_fXDataControlService.getDeliveryDate(l_datOrderBizDate, l_subAccount,
                l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.9）validate振替可能回数(SubAccount, Date, OrderCategEnum)
        //  振替可能回数のチェックを行う。 
        //  [引数] 
        //  補助口座： get補助口座()の戻り値 
        //  発注日： get発注日()の戻り値 
        //  注文カテゴリ：　@15（為替保証金振替）
        l_aioOrderManager.validateTransferPossibleCount(
            l_subAccount, 
            l_datOrderBizDate, 
            OrderCategEnum.FX);
        
        //1.10）get代理入力者( )
        Trader l_trader = this.getTrader();
        
        //1.11） validate取引パスワード(Trader, SubAccount, String)
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //補助口座： get補助口座()の戻り値 
        //パスワード： リクエストデータ.暗証番号 
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult = 
            l_gentradeOrderValidator.validateTradingPassword(
                l_trader, 
                l_subAccount, 
                l_request.password);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("チェックエラーの場合はを例外をスローする");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "チェックエラーの場合はを例外をスローする");
        }
        
        //1.12） getFX顧客(String, String, String, String)
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店().getBranchCode() 
        //FXシステムコード：　@会社別FXシステム条件.FXシステムコード 
        //顧客コード：　@補助口座.getMainAccount().getAccountCode()
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        FxAccountParams l_fxAccountParams = null;
        try
        {
            l_fxAccountParams =
                l_fXDataControlService.getFXAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strFxSystemCode,
                    l_strAccountCode);
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
        
        //1.13） get新規識別コード(String, String, ProductTypeEnum)
        // [引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店().getBranchCode() 
        //銘柄タイプ： 5（現金）
        WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);    
        String l_strNewNumber = 
            l_reqOrderNumberManageService.getNewNumber(
                l_strInstitutionCode,
                l_strBranchCode,
                ProductTypeEnum.CASH);
        
        //1.14） GFT依頼電文明細( )
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = 
            new WEB3FXGftAskingTelegramUnit();
            
        //1.15)  get処理区分(FXシステムコード : String)
        //[get処理設区分()に指定する引数]  
        //FXシステムコード：　@リクエストデータ．FXシステムコード
        String l_strOperationDiv = getOperationDiv(l_request.fxSystemCode);

        //get変換FXログインID(long, String, String, long)
        //[引数]
        // 証券会社ID：　@補助口座.getInstitution.getInstitutionId()
        // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード
        // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字
        // FXログインID：　@FX顧客Params.FXログインID
        String l_strChangedFXLoginID = l_fXDataControlService.getChangedFXLoginID(
            l_subAccount.getInstitution().getInstitutionId(),
            l_compFxConditionParams.getFxSystemCode(),
            l_compFxConditionParams.getFxHeadOfLoginId(),
            l_fxAccountParams.getFxLoginId());

        //1.17） (*)プロパティセット
        //(*)GFT依頼電文明細に必要なプロパティをセットする（下記以外のプロパティは設定しない）
        Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime());
        //DIR→GFT送信日時     ：現在時刻（システムタイムスタンプ）
        l_fXGftAskingTelegramUnit.dirSendTime = 
            WEB3DateUtility.formatDate(l_tsSystemTimestamp, "yyyyMMddHHmmss");

        //処理区分            ：get処理区分の戻り値
        l_fXGftAskingTelegramUnit.gftOperationDiv = l_strOperationDiv;
        //為替保証金口座番号   ：リクエストデータ.FX口座情報.口座番号
        l_fXGftAskingTelegramUnit.fxAccountCode = l_request.fxAccInformationUnit.fxAccountCode;
        //初期ログインID        ：get変換FXログインIDの戻り値
        l_fXGftAskingTelegramUnit.fxFirstLoginId = l_strChangedFXLoginID;
        //担当区分            ：会社別FXシステム条件Params.担当区分
        l_fXGftAskingTelegramUnit.groupName = l_compFxConditionParams.getGroupName();
        //入出金額            ：リクエストデータ.振替金額
        l_fXGftAskingTelegramUnit.cashinoutAmt = l_request.transferAmount;
        //WOLFセッションキー     ：リクエストデータ.WOLFセッションキー
        l_fXGftAskingTelegramUnit.wolfSession = l_request.wolfSession;
        //アプリケーションID      ：リクエストデータ.アプリケーションID
        l_fXGftAskingTelegramUnit.wolfAid = l_request.wolfAid;
        //再生成サービスID       ：リクエストデータ.再生成サービスID
        l_fXGftAskingTelegramUnit.regetServiceId = l_request.regetServiceId;
        //SSID            ：リクエストデータ.SSID
        l_fXGftAskingTelegramUnit.wolfSsid = l_request.wolfSsid;
        //会社コード           ：補助口座.証券会社コード
        l_fXGftAskingTelegramUnit.institutionCode = l_strInstitutionCode;
        //部店コード           ：補助口座.get取引店().getBranchCode()
        l_fXGftAskingTelegramUnit.branchCode = l_strBranchCode;
        //顧客コード           ：補助口座.getMainAccount().getAccountCode()
        l_fXGftAskingTelegramUnit.accountCode = l_strAccountCode;
        //識別コード           ：get新規識別コード()の戻り値
        l_fXGftAskingTelegramUnit.requestNumber = l_strNewNumber;

        String l_strDeliveryDate =
            WEB3DateUtility.formatDate(
                l_datDeliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        //受渡日:get受渡日()の戻り値
        l_fXGftAskingTelegramUnit.deliveryDate = l_strDeliveryDate;

        //1.18） createGFT電文ハッシュ値(GFT依頼電文明細)
        //[引数の設定] 
        //GFT依頼電文明細：　@プロパティセットを行ったGFT依頼電文明細
        WEB3FXTelegramProcessService l_fXTelegramProcessService=
            (WEB3FXTelegramProcessService)Services.getService(
                WEB3FXTelegramProcessService.class);  
        l_fXGftAskingTelegramUnit.hashValue =  
            l_fXTelegramProcessService.createGFTTelegramHashValue(l_fXGftAskingTelegramUnit);
        
        // 1.19）insertGFT電文保存(GFT依頼電文明細)
        //[引数の設定] 
        //GFT依頼電文明細：　@プロパティセットを行ったGFT依頼電文明細
        l_fXDataControlService.insertGFTMessage(l_fXGftAskingTelegramUnit);
        
        //insertGFT振替状況(GFT依頼電文明細, String, String, String, 会社別FXシステム条件Params, String)
        //GFT振替状況テーブルに行をinsertする。 
        //[引数の設定] 
        //GFT依頼電文明細：　@プロパティセットを行ったGFT依頼電文明細 
        //コース区分：　@リクエストデータ.FX口座情報.コース区分 
        //受渡予定日： get受渡日()の戻り値 
        //信用振替用識別コード：　@null
        //会社別FXシステム条件Params：会社別FXシステム条件Params
        //入出金一覧取引区分:　@　@FX振替条件マスタParams.入出金一覧取引区分
        l_fXDataControlService.insertGFTTransferStatus(
            l_fXGftAskingTelegramUnit,
            l_request.fxAccInformationUnit.fxCourseDiv,
            l_strDeliveryDate,
            null,
            l_compFxConditionParams,
            l_fxTransferMasterParams.getIoListTradeDiv());

        //1.21） createNewOrderId( )
       long l_lngNewOrderId = l_aioOrderManager.createNewOrderId();
        
        //1.22）   createResponse( )
        WEB3FXTransFromFXAskingResponse 
              l_fxTransFromFXAskingResponse =
                  (WEB3FXTransFromFXAskingResponse) l_request.createResponse();  
        
        //1.23）(*)プロパティセット
        // レスポンスデータにプロパティをセットする。
        // URL： 会社別FXシステム条件Params.URL
        l_fxTransFromFXAskingResponse.fxUrl = l_compFxConditionParams.getUrl();
        // GFT依頼電文明細：　@(*　@上記で編集を行ったGFT依頼電文明細)
        l_fxTransFromFXAskingResponse.fxGftAskingTelegramUnit = l_fXGftAskingTelegramUnit;
        // 注文ID：　@createNewOrderId()の戻り値
        l_fxTransFromFXAskingResponse.orderId = l_lngNewOrderId + "";
        // 確認時発注日：　@get発注日()の戻り値
        l_fxTransFromFXAskingResponse.checkDate = l_datOrderBizDate;
        log.exiting(STR_METHOD_NAME);
        return l_fxTransFromFXAskingResponse;
    }

    /**
     * (submit注文) <BR>
     * 振替注文の登録処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FXから振替）submit注文」参照。 <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図(「(為替保証金サービスモデル) / FXから振替 」<BR>
     * （FXから振替）submit注文) <BR>
     * : 1.3 getGFT振替状況(String, String, String) <BR>
     * 戻り値がnullの場合、例外をthorwする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図(「(為替保証金サービスモデル) / FXから振替 」<BR>
     * （FXから振替）submit注文 <BR>
     * : 1.7.2 (*)例外をthrow <BR>
     * ①@の場合：「2重受信エラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01972 <BR>
     * <BR>
     * ②③④の場合：「その他のFXシステムエラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01800 <BR>
     * <BR>
     * ⑤の場合：GFT結果通知電文明細.受付結果に応じて以下の例外となる <BR>
     * 00000105(ホスト処理時間外)の場合 ：「受付時間外エラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01801 <BR>
     * <BR>
     * 00000199(ホストシステムエラー)の場合 ：「通信エラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01802 <BR>
     * <BR>
     * 00000204(残高不足エラー)の場合 ：「残高不足エラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01803 <BR>
     * <BR>
     * 00000205（為替口座の純資産残高不足）:「純資産残高が不足しています」<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02672 <BR>
     * <BR>
     * 00000206（為替口座の現金残高不足）：「現金残高が不足しています」<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02673 <BR>
     * <BR>
     * 00000207（為替口座にマイナス通貨あり）：「為替口座における通貨現金残高で、マイナス通貨があリます。<BR>
     * 為替口座でコンバージヨン後に再度振替を依賴してださい。」<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02674 <BR>
     * <BR>
     * 00000801(2重送信エラー)の場合 ：「2重送信エラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01799 <BR>
     * <BR>
     * 00000501（該当する証拠金口座が存在しない）の場合　@：[証拠金口座未開設エラー]<BR>
     * <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02436<BR>
     * <BR>
     * 00000503（該当する為替保証金口座が存在しない）：「為替口座ヘの振替です。<BR>
     * この機@能は為替口座を開設し、かつ振替同意書に記入・ご送付くいただいた <BR>
     * お客樣しかご利用いただくことはどきすせん」<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02675 <BR>
     * <BR>
     * 以外の受付結果の場合 ：「その他のFXシステムエラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01800 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransFromFXCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BE52D40238
     */
    protected WEB3FXTransFromFXCompleteResponse submitOrder(
        WEB3FXTransFromFXCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FXTransFromFXCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1） validate( )
        l_request.validate();
        
        //1.2） insertGFT電文保存(GFT結果通知電文明細)
        //[引数の設定] 
        //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
        WEB3FXDataControlService l_fXDataControlService =
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        l_fXDataControlService.insertGFTMessage(
            l_request.fxGftResultNoticeTelegramUnit);
        
        //1.3） getGFT振替状況(String, String, String)
        //[引数の設定] 
        //証券会社コード：　@リクエストデータ.GFT結果通知電文明細.会社コード 
        //部店コード：　@リクエストデータ.GFT結果通知電文明細.部店コード 
        //識別コード：　@リクエストデータ.GFT結果通知電文明細.識別コード
        GftTransferStatusParams l_gftTransferStatusParams = 
            l_fXDataControlService.getGFTTransferStatus(
                l_request.fxGftResultNoticeTelegramUnit.institutionCode, 
                l_request.fxGftResultNoticeTelegramUnit.branchCode, 
                l_request.fxGftResultNoticeTelegramUnit.requestNumber);
        if (l_gftTransferStatusParams == null)
        {
            log.debug("GFT振替状況取得エラー。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()+ "." + STR_METHOD_NAME,
                "GFT振替状況取得エラー。");
        }        

        //get補助口座(SubAccountTypeEnum)
        //[引数]
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount =
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //get会社別FXシステム条件(String, String, String)
        //[引数の設定]
        //証券会社コード： 補助口座.証券会社コード
        //部店コード：　@補助口座.get取引店.getBranchCode()
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        String l_strInstitutionCode =
            l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode =
            l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strFxSystemCode = l_request.fxSystemCode;
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
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getFX振替条件マスタ(long, String)
        //【引数】
        //FXシステム条件ID　@= 会社別FXシステム条件Params.FXシステム条件ID
        //振替区分 = 0：入金
        FxTransferMasterParams l_fxTransferMasterParams =
            l_fXDataControlService.getFxTransferMasterParams(
                l_compFxConditionParams.getFxSystemId(),
                WEB3AioTransferDivDef.CASHIN);

        boolean l_blnIsGFTTelegramSet = true;
        boolean l_blnIsGFTTelegramLengthPropSame = true;
        boolean l_blnIsGFTTelegramSendAndReceiveMailSame = true;
        //会社別FXシステム条件Params.SOAP接続実施区分== 0：SOAP接続未実施 or 2：口座開設のみ実施の場合
        if (WEB3AioSoapConnectDivDef.SOAP_CONNECT_NOT_ENFORCEMENT.equals(l_compFxConditionParams.getSoapConnectDiv())
           || WEB3AioSoapConnectDivDef.TRANSFER_ENFORCEMENT.equals(l_compFxConditionParams.getSoapConnectDiv()))
        {
            //1.41） isGFT電文項目設定(GFT結果通知電文明細)
            //[引数の設定]
            //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
            WEB3FXTelegramProcessService l_fXTelegramProcessService =
                (WEB3FXTelegramProcessService) Services.getService(WEB3FXTelegramProcessService.class);
            l_blnIsGFTTelegramSet = 
                l_fXTelegramProcessService.isGFTTelegramSet(l_request.fxGftResultNoticeTelegramUnit);

            //1.42） isGFT電文桁数属性一致(GFT結果通知電文明細)
            //[引数の設定]
            //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
            l_blnIsGFTTelegramLengthPropSame = 
                l_fXTelegramProcessService.isGFTTelegramLengthPropSame(
                    l_request.fxGftResultNoticeTelegramUnit);

            //isGFT電文項目設定()=true and isGFT電文桁数属性一致()=true の場合、実施
            if (l_blnIsGFTTelegramSet && l_blnIsGFTTelegramLengthPropSame)
            {
                //1.43） isGFT電文送受信項目一致(GFT結果通知電文明細)
                //[引数の設定]
                //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
                l_blnIsGFTTelegramSendAndReceiveMailSame = 
                    l_fXTelegramProcessService.isGFTTelegramSendAndReceiveValueSame(
                        l_request.fxGftResultNoticeTelegramUnit);
            }
        }

        //以下のいずれかに当てはまる場合、実施する。
        // ※会社別FXシステム条件Params.SOAP接続実施区分== 1：SOAP接続実施の場合、
        //   ③、④は条件に含めない
        boolean l_blnFlag1 = WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(
            l_gftTransferStatusParams.getSendRcvDiv());
        boolean l_blnFlag2 = WEB3SendRcvDivDef.SEND_COMPLETE.equals(
            l_gftTransferStatusParams.getSendRcvDiv());
        boolean l_blnFlag3 =
            WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                l_request.fxGftResultNoticeTelegramUnit.resultCode);
        boolean l_blnIsResultNoticeTelegramPro = false;
        boolean l_blnIsTelegramSendAndReceiveSame = false;
        if (!WEB3AioSoapConnectDivDef.SOAP_CONNECT_ENFORCEMENT.equals(
            l_compFxConditionParams.getSoapConnectDiv()))
        {
            if (!(l_blnIsGFTTelegramSet && l_blnIsGFTTelegramLengthPropSame))
            {
                l_blnIsResultNoticeTelegramPro = true;
            }

            if (!l_blnIsGFTTelegramSendAndReceiveMailSame)
            {
                l_blnIsTelegramSendAndReceiveSame = true;
            }
        }

        //①@送受信区分が不正の場合（2重受信エラー）
        //（getGFT振替状況()の戻り値のGFT振替状況Params.送受信区分 ＝ 2(受信済)）
        //②送受信区分が不正の場合（その他エラー）
        //（getGFT振替状況()の戻り値のGFT振替状況Params.送受信区分 != (1(送信済)、2(受信済))
        //③結果通知電文内容の妥当性が不正の場合
        //（isGFT電文項目設定()＝fasle、または、isGFT電文桁数属性一致()＝false）
        //④送受信電文の項目が不一致の場合
        //（isGFT電文送受信項目一致()＝false）
        //⑤振替が正常に受付られなかった場合
        //(リクエストデータ.GFT結果通知電文明細.受付結果 != 00000000（正常））
                
        if (l_blnFlag1
            || !(l_blnFlag1 || l_blnFlag2)
            || l_blnIsResultNoticeTelegramPro
            || l_blnIsTelegramSendAndReceiveSame
            || !l_blnFlag3)
        {
            if (!l_blnFlag1)
            {
                //1.7.1）(*2)
                //①@以外の場合、実施する。
                //7.1.1 updateGFT振替状況(GFT振替状況Params, GFT結果通知電文明細, String, String)
                //[引数の設定] 
                //GFT振替状況Params：　@getGFT振替状況()の戻り値 
                //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細 
                //更新後受渡予定日：　@null 
                //エラー理由コード：　@ 
                //②の場合：0001（送受信区分チェックエラー） 
                //③の場合：0002（パラメータ妥当性チェックエラー） 
                //④の場合：0003（パラメータ一致チェックエラー） 
                //⑤の場合：0004（受付結果コードチェックエラー）
                //エラー理由コード
                String l_strErrorReasonCode = null;
                if (!(l_blnFlag1 || l_blnFlag2))
                {
                    l_strErrorReasonCode = WEB3GftErrorReasonCodeDef.SENDRCV_ERROR;
                }
                if (!(l_blnIsGFTTelegramSet && l_blnIsGFTTelegramLengthPropSame))
                {
                    l_strErrorReasonCode = WEB3GftErrorReasonCodeDef.PARAM_VALIDITY_ERROR;
                }
                if (!l_blnIsGFTTelegramSendAndReceiveMailSame)
                {
                    l_strErrorReasonCode = WEB3GftErrorReasonCodeDef.PARAM_MISMATCH_ERROR;
                }
                if (!l_blnFlag3)
                {
                    l_strErrorReasonCode = WEB3GftErrorReasonCodeDef.RESULT_CODE_ERROR;
                }
                l_fXDataControlService.updateGFTTransferStatus(
                    l_gftTransferStatusParams,
                    l_request.fxGftResultNoticeTelegramUnit,
                    null,
                    l_strErrorReasonCode);
            
            }
            if (l_blnFlag1)
            {
                // ①@の場合：「2重受信エラー」
                log.debug("2重受信エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01972,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "2重受信エラー。");
            }
            if (!(l_blnFlag1 || l_blnFlag2)
                || !(l_blnIsGFTTelegramSet && l_blnIsGFTTelegramLengthPropSame)
                || !l_blnIsGFTTelegramSendAndReceiveMailSame)
            {
                // ②③④の場合：「その他のFXシステムエラー」
                log.debug("その他のFXシステムエラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "その他のFXシステムエラー。");
            }
            if (!l_blnFlag3)
            {
                //1.7.2）(*)例外をthrow
                //1.7.2.1）(*)例外をthrowする。　@　@
                //⑤の場合：GFT結果通知電文明細.受付結果に応じて以下の例外となる
                //　@00000105(ホスト処理時間外)の場合   ：「受付時間外エラー」
                //　@00000199(ホストシステムエラー)の場合 ：「通信エラー」
                //　@00000204(残高不足エラー)の場合    ：「残高不足エラー」
                //  00000205(為替口座の純資産残高不足)の場合    ：「純資産残高が不足しています」
                //  00000206(為替口座の現金残高不足)の場合    ：「現金残高が不足しています」
                //  00000207(為替口座にマイナス通貨あり)の場合    ：「為替口座における通貨現金残高で、マイナス通貨があリます。
                //  為替口座でコンバージヨン後に再度振替を依賴してださい。」
                //　@00000801(2重送信エラー)の場合    ：「2重送信エラー」
                //  00000501（該当する証拠金口座が存在しない）の場合　@：[証拠金口座未開設エラー]
                //  00000503（該当する為替保証金口座が存在しない）：「為替口座ヘの振替です。
                // この機@能は為替口座を開設し、かつ振替同意書に記入・ご送付くいただいた 
                // お客樣しかご利用いただくことはどきすせん」
                //　@以外の受付結果の場合      ：「その他のFXシステムエラー」
                if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105.equals(
                        l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("受付時間外エラー。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01801,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "受付時間外エラー。");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("通信エラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01802,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "通信エラー。");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000204.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("残高不足エラー。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01803,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "残高不足エラー。");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000205.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("純資産残高不足しています。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02672,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "純資産残高不足しています。");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000206.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("現金残高が不足しています。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02673,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "現金残高が不足しています。");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000207.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("為替口座における通貨現金残高で、マイナス通貨があリます。" +
                            "為替口座でコンバージヨン後に再度振替を依賴してださい。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02674,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "為替口座における通貨現金残高で、マイナス通貨があリます。" +
                        "為替口座でコンバージヨン後に再度振替を依賴してださい。");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("2重送信エラー。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01799,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "2重送信エラー。");
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("該当する証拠金口座が存在しない。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02436,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "該当する証拠金口座が存在しない。");          
                }
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000503.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("為替口座ヘの振替です。" +
                            "この機@能は為替口座を開設し、かつ振替同意書に記入・ご送付くいただいた" +
                            "お客樣しかご利用いただくことはどきすせん。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02675,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "為替口座ヘの振替です。" +
                        "この機@能は為替口座を開設し、かつ振替同意書に記入・ご送付くいただいた" +
                        "お客樣しかご利用いただくことはどきすせん。");          
                }
                else
                {
                    log.debug("その他のFXシステムエラー。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "その他のFXシステムエラー。");
                }
            }
        }

        //1.9）get代理入力者( )
        Trader l_trader = this.getTrader();

        //1.11） get発注日( )
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate(); 

        //get受渡日(Date, 補助口座, String)
        //[引数の設定]
        //発注日： get発注日()の戻り値
        //補助口座：　@get補助口座()の戻り値
        //受渡日設定区分：　@getFX振替条件マスタ().受渡日設定区分
        Date l_datDeliveryDate =
            l_fXDataControlService.getDeliveryDate(
                l_datOrderBizDate,
                l_subAccount,
                l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.13） get商品ID(Institution)
        //[引数] 
        //証券会社： 補助口座.get取引店().getInstitution()の戻り値 
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //入出金注文マネージャクラスを取得する。
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        long l_lngProductId =
            l_aioOrderManager.getProductId(l_subAccount.getInstitution());
        
        //1.14. get入出金額区分(GFT結果通知電文明細)
        //[引数] 
        //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細        
        String l_strCashInOutAmountDiv = 
            l_fXDataControlService.getCashInOutAmountDiv(l_request.fxGftResultNoticeTelegramUnit);        

        //get口座( )
        //顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();

        //is信用口座開設(弁済区分 : String)
        //顧客が、信用取引口座を開設しているかどうかを判定する。
        //[引数の指定]
        //弁済区分：　@"0"（指定無し）
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //発注日 == 受渡日の場合、処理を行う
        //分岐フロー
        //発注日 == 受渡日（get発注日() == get受渡日()）の場合、処理を行う
        if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datDeliveryDate) == 0)
        {
            //分岐フロー
            //顧客が信用口座を開設している（is信用口座開設()==TRUE）場合、処理を行う
            if (l_blnIsMarginAccountEstablished)
            {
                //入金額：  ・get入出金額区分()の戻り値が1の場合、
                //             リクエストデータ.GFT結果通知電文明細.入出金額
                //         ・get入出金額区分()の戻り値が2の場合、
                //             リクエストデータ.GFT結果通知電文明細.入出金額2
                //         ・get入出金額区分()の戻り値が3の場合、
                //             リクエストデータ.GFT結果通知電文明細.入出金額 +
                //             リクエストデータ.GFT結果通知電文明細.入出金額2 の合計額
                double l_dblCashinAmt = 0D;
                if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT.equals(l_strCashInOutAmountDiv))
                {
                    l_dblCashinAmt = Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt);
                }
                else if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT2.equals(l_strCashInOutAmountDiv))
                {
                    l_dblCashinAmt = Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2);
                }
                else if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT_AND_AMOUNT2.equals(l_strCashInOutAmountDiv))
                {
                    BigDecimal l_bdCashinAmt = new BigDecimal(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt);
                    BigDecimal l_bdCashinAmt2 = new BigDecimal(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2);

                    l_dblCashinAmt = l_bdCashinAmt.add(l_bdCashinAmt2).doubleValue();
                }

                //submit保証金振替(顧客, Date, double, String)(保証金への
                //振替サービスImpl::submit保証金振替)
                //預り金から信用保証金への振替を行う。
                //[引数の設定]
                //顧客：　@get口座()の戻り値
                //受渡日：　@get受渡日()の戻り値
                //入金額：  ・get入出金額区分()の戻り値が1の場合、
                //             リクエストデータ.GFT結果通知電文明細.入出金額
                //         ・get入出金額区分()の戻り値が2の場合、
                //             リクエストデータ.GFT結果通知電文明細.入出金額2
                //         ・get入出金額区分()の戻り値が3の場合、
                //             リクエストデータ.GFT結果通知電文明細.入出金額 +
                //             リクエストデータ.GFT結果通知電文明細.入出金額2 の合計額
                //暗証番号：　@リクエスストデータ.暗証番号
                //代理入力者：　@get代理入力者()の戻り値
                WEB3MarginTransferService l_marginTransferService =
                    (WEB3MarginTransferService)Services.getService(WEB3MarginTransferService.class);

                l_marginTransferService.submitMarginTransfer(
                    l_mainAccount,
                    l_datDeliveryDate,
                    l_dblCashinAmt,
                    l_request.password,
                    l_trader);
            }
        }

        //注文種別： FX振替条件マスタ.注文種別
        OrderTypeEnum l_orderTypeEnum = l_fxTransferMasterParams.getOrderType();

        //金額：　@get入出金額区分()の戻り値が1、3の場合、 
        //　@         リクエストデータ.GFT結果通知電文明細.入出金額 
        //　@      get入出金額区分()の戻り値が2の場合、 
        //　@         リクエストデータ.GFT結果通知電文明細.入出金額2 
        double l_dblCashinoutAmt = 0D;
        if(WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT.equals(l_strCashInOutAmountDiv)
            || WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT_AND_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {
            l_dblCashinoutAmt = Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt);
        }
        else if(WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {
            l_dblCashinoutAmt = Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2);
        }       

        //摘要コード：　@get会社別FXシステム条件()の戻り値.FXシステム区分　@=　@1（先OPシステム）
        //  && get入出金額区分()の戻り値が2の場合、71（株先証拠金：東証）
        //上記以外の場合、 FX振替条件マスタ.摘要コード
        String l_strRemarkCode = l_fxTransferMasterParams.getRemarkCode();
        String l_strFxSystemDiv = l_compFxConditionParams.getFxSystemDiv();
        if (WEB3FxSystemDivDef.FUOP_SYSTEM.equals(l_strFxSystemDiv) &&
            WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {
            l_strRemarkCode = WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO;
        }

        //入出金注文内容(Trader, OrderTypeEnum,AssetTransferTypeEnum,
        // long, double, String, Date, String, Long, String, String)
        //入出金注文内容を生成する。 
        //[引数の設定] 
        //代理入力者：　@get代理入力者()の戻り値 
        //注文種別： FX振替条件マスタ.注文種別
        //振替タイプ：　@1（入金） 
        //商品ID：　@get商品ID()の戻り値 
        //金額：　@get入出金額区分()の戻り値が1、3の場合、 
        //　@         リクエストデータ.GFT結果通知電文明細.入出金額 
        //　@      get入出金額区分()の戻り値が2の場合、 
        //　@         リクエストデータ.GFT結果通知電文明細.入出金額2 
        //記述：　@null
        //振替予定日：　@get受渡日()の戻り値 
        //決済機@関ID：　@null 
        //注文ID：　@null
        //摘要コード：　@get会社別FXシステム条件()の戻り値.FXシステム区分　@=　@1（先OPシステム）
        //  && get入出金額区分()の戻り値が2の場合、71（株先証拠金：東証）
        //上記以外の場合、 FX振替条件マスタ.摘要コード
        //摘要名：　@FX振替条件マスタ.摘要名
        WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
            l_trader,
            l_orderTypeEnum,
            AssetTransferTypeEnum.CASH_IN,
            l_lngProductId,
            l_dblCashinoutAmt,
            null,
            l_datDeliveryDate,
            null,
            null,
            l_strRemarkCode,
            l_fxTransferMasterParams.getRemarkName());

        //1.16） FX振替注文更新インタセプタ(入出金注文内容)
        //[引数の設定] 
        //入出金注文内容：　@入出金注文内容オブジェクト
        WEB3FXTransferOrderUpdateInterceptor l_fxTransferOrderUpdateInterceptor =
            new WEB3FXTransferOrderUpdateInterceptor(l_aioNewOrderSpec);
    
        //1.17） (*)プロパティセット
        //(*)以下のとおりにプロパティをセットする。
        //発注日：　@get発注日()の戻り値
        l_fxTransferOrderUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);
        
        //受渡日：　@get受渡日()の戻り値 
        l_fxTransferOrderUpdateInterceptor.setDeliveryDate(l_datDeliveryDate);
        
        //識別コード：　@リクエストデータ.GFT結果通知電文明細.識別コード
        l_fxTransferOrderUpdateInterceptor.setOrderRequestNumber(
            l_request.fxGftResultNoticeTelegramUnit.requestNumber);
        //MQステータス：　@1(送信済み)
        l_fxTransferOrderUpdateInterceptor.setMQStatus(WEB3MqStatusDef.MAIL_SENDED);
        
        //1.18） submit振替注文(SubAccount, ProductTypeEnum, OrderTypeEnum, 
        //NewOrderSpec, AioOrderManagerPersistenceInterceptor, long, String)(
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //銘柄タイプ： 5（現金） 
        //注文種別： FX振替条件マスタ.注文種別
        //注文内容： 入出金注文内容オブジェクト 
        //インタセプタ： FX振替注文更新インタセプタオブジェクト 
        //注文ID： リクエストデータ.注文ID 
        //パスワード： リクエストデータ.暗証番号        
        
        l_aioOrderManager.submitTransferOrder(
            l_subAccount,
            ProductTypeEnum.CASH,
            l_orderTypeEnum,
            l_aioNewOrderSpec,
            l_fxTransferOrderUpdateInterceptor,
            Long.parseLong(l_request.orderId),
            l_request.password);
            
        //1.19） (*)get入出金額区分()の戻り値が"3"の場合
        if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT_AND_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {
            //入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, long,
            //  double, String, Date, String, Long, String, String)
            //入出金注文内容を生成する。 
            //[引数の設定] 
            //代理入力者：　@get代理入力者()の戻り値 
            //注文種別： FX振替条件マスタ.注文種別
            //振替タイプ：　@1（入金） 
            //商品ID：　@get商品ID()の戻り値 
            //金額：　@リクエストデータ.GFT結果通知電文明細.入出金額2 
            //記述：　@null
            //振替予定日：　@get受渡日()の戻り値 
            //決済機@関ID：　@null 
            //注文ID：　@null
            //摘要コード：　@71（株先証拠金：東証）
            //摘要名：　@FX振替条件マスタ.摘要名
            WEB3AioNewOrderSpec l_aioNewOrderSpec1 = new WEB3AioNewOrderSpec(
                l_trader,
                l_orderTypeEnum,
                AssetTransferTypeEnum.CASH_IN,
                l_lngProductId,
                Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2),
                null,
                l_datDeliveryDate,
                null,
                null,
                WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO,
                l_fxTransferMasterParams.getRemarkName());

            //1.19.2） FX振替注文更新インタセプタ(入出金注文内容)
            //FX振替注文更新インタセプタを生成する。 
            //[引数の設定] 
            //入出金注文内容：　@入出金注文内容オブジェクト
            WEB3FXTransferOrderUpdateInterceptor l_fxTransferOrderUpdateInterceptor1 =
                 new WEB3FXTransferOrderUpdateInterceptor(l_aioNewOrderSpec1);
    
            //1.19.3） get新規識別コード(証券会社コード : String, 部店コード : String, 銘柄タイプ : ProductTypeEnum)
            //[引数] 
            //証券会社コード：補助口座.証券会社コード 
            //部店コード：　@補助口座.get取引店（）.getBranchCode() 
            //銘柄タイプ：　@5（現金）
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class); 
            
            String l_strNewNumber1 = l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                ProductTypeEnum.CASH);           
            
            //1.19.4） (*2)プロパティセット
            //発注日：　@get発注日()の戻り値
            l_fxTransferOrderUpdateInterceptor1.setOrderBizDate(l_datOrderBizDate);
            
            //受渡日：　@get受渡日()の戻り値 
            l_fxTransferOrderUpdateInterceptor1.setDeliveryDate(l_datDeliveryDate);
            
            //識別コード：　@get識別コード()の戻り値 
            l_fxTransferOrderUpdateInterceptor1.setOrderRequestNumber(l_strNewNumber1);
            
            //MQステータス：　@1(送信済み)
            l_fxTransferOrderUpdateInterceptor1.setMQStatus(WEB3MqStatusDef.MAIL_SENDED);    
            
            //1.19.5） createNewOrderId( )
            //新規注文IDを採番する。
            long l_lngNewOrderId = l_aioOrderManager.createNewOrderId();        
        
            //1.19.6） submit振替注文(SubAccount, ProductTypeEnum, OrderTypeEnum, NewOrderSpec, 
            //AioOrderManagerPersistenceInterceptor, long, String)
            //振替注文を登録する。 
            //[引数] 
            //補助口座： get補助口座()の戻り値 
            //銘柄タイプ： 5（現金） 
            //注文種別： FX振替条件マスタ.注文種別
            //注文内容： 入出金注文内容オブジェクト 
            //インタセプタ： FX振替注文更新インタセプタオブジェクト 
            //注文ID： createNewOrderId()の戻り値 
            //パスワード： リクエストデータ.暗証番号 
            l_aioOrderManager.submitTransferOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_orderTypeEnum,
                l_aioNewOrderSpec1,
                l_fxTransferOrderUpdateInterceptor1,
                l_lngNewOrderId,
                l_request.password);            
        }
        
        //1.20） 余力再計算(補助口座 : 補助口座) 
        //[引数の設定] 
        //補助口座：　@get補助口座()の戻り値
         
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        l_service.reCalcTradingPower(l_gentradeSubAccount);
        
        //1.21） updateGFT振替状況(GFT振替状況Params, GFT結果通知電文明細, String, String)
        //[引数の設定] 
        //GFT振替状況Params：　@getGFT振替状況()の戻り値 
        //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細 
        //更新後受渡予定日：　@(*) 
        //エラー理由コード：　@0000（正常） 
        //(*)以下のとおり 
        //リクエストデータ.確認時発注日 == get発注日()の場合、null 
        //それ以外の場合、get受渡日()の戻り値  

        if (WEB3DateUtility.compareToDay(l_request.checkDate, l_datOrderBizDate) == 0)
        {            
            l_fXDataControlService.updateGFTTransferStatus(
                l_gftTransferStatusParams, 
                l_request.fxGftResultNoticeTelegramUnit, 
                null,
                WEB3ErrorReasonCodeDef.NORMAL);
        }
        else
        {                      
            l_fXDataControlService.updateGFTTransferStatus(
                l_gftTransferStatusParams, 
                l_request.fxGftResultNoticeTelegramUnit, 
                WEB3DateUtility.formatDate(
                        l_datDeliveryDate, "yyyyMMdd"),
                WEB3ErrorReasonCodeDef.NORMAL);
        }
      
        //1.22） getOrder(long)
        //[引数の設定] 
        //arg0：　@リクエストデータ.注文ID
        AioOrder l_aioOrder = null;
        try
        {
            l_aioOrder = 
                (AioOrder) l_aioOrderManager.getOrder(Long.parseLong(l_request.orderId));
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文を取得する。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.23） createResponse( )
        WEB3FXTransFromFXCompleteResponse 
              l_fxTransFromFXCompleteResponse =
                  (WEB3FXTransFromFXCompleteResponse) l_request.createResponse();  
        
        //1.24） (*)プロパティセット
        //レスポンスデータにプロパティをセットする。
        //更新時間：　@注文.更新日時
        l_fxTransFromFXCompleteResponse.lastUpdatedTimestamp = 
            ((AioOrderRow)l_aioOrder.getDataSourceObject()).getLastUpdatedTimestamp();
        //識別番号：　@リクエストデータ.注文ID
        l_fxTransFromFXCompleteResponse.orderActionId = l_request.orderId;

        //受渡日: get受渡日()の戻り値 
        l_fxTransFromFXCompleteResponse.deliveryDate = l_datDeliveryDate;
        
        log.exiting(STR_METHOD_NAME);
        return l_fxTransFromFXCompleteResponse;
    }

    /**
     * (submit注文（SOAP接続）)<BR>
     * 振替注文の完了処理を行う。<BR>
     * ※接続にて行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（FXからの振替）submit注文（SOAP接続）」参照。<BR>
     * <BR>
     * @@param l_request リクエストデータ
     * @@return WEB3FXTransFromFXCompleteSoapResponse
     * @@throws WEB3BaseException
     */
    protected WEB3FXTransFromFXCompleteSoapResponse submitOrderSoap(
        WEB3FXTransFromFXCompleteSoapRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrderSoap(WEB3FXTransFromFXCompleteSoapRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        
        //会社別FXシステム条件Paramsを取得する。
        //
        //[引数の設定]
        //証券会社コード： get口座.getInstitution().getInstitutionCode()
        //部店コード：　@get口座.getBranch().getBranchCode()
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = l_fxDataControlService.getCompFxCondition(
                this.getMainAccount().getInstitution().getInstitutionCode(),
                this.getMainAccount().getBranch().getBranchCode(),
                l_request.fxSystemCode);
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

        //start注文(FXから振替依頼レスポンス)
        //[引数]
        //リクエストデータ：引数.リクエストデータをFXから振替依頼リクエストにキャストしたもの
        WEB3FXTransFromFXAskingRequest l_fxTransFromFXAskingRequest =
            new WEB3FXTransFromFXAskingRequest();
        l_fxTransFromFXAskingRequest.fxSystemCode = l_request.fxSystemCode;
        l_fxTransFromFXAskingRequest.fxAccInformationUnit =
            l_request.fxAccInformationUnit;
        l_fxTransFromFXAskingRequest.transferAmount =
            l_request.transferAmount;
        l_fxTransFromFXAskingRequest.password = l_request.password;
        l_fxTransFromFXAskingRequest.wolfSession = l_request.wolfSession;
        l_fxTransFromFXAskingRequest.wolfAid = l_request.wolfAid;
        l_fxTransFromFXAskingRequest.regetServiceId = l_request.regetServiceId;
        l_fxTransFromFXAskingRequest.wolfSsid = l_request.wolfSsid;
        l_fxTransFromFXAskingRequest.complianceInfo = l_request.complianceInfo;

        WEB3FXTransFromFXAskingResponse l_fxTransFromFXAskingResponse = null;
        try
        {
            //FXから振替依頼TransactionCallback(FXから振替依頼リクエスト)
            WEB3FXTransFromFXAskingTransactionCallback l_transactionCallback =
                new WEB3FXTransFromFXAskingTransactionCallback(l_fxTransFromFXAskingRequest);
    
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //doTransaction(トランザクション属性 : int, トランザクションコールバック : TransactionCallback)
            //[引数]
            //トランザクション属性：　@TX_CREATE_NEW
            //トランザクションコールバック：　@FXから振替依頼TransactionCallbackインスタンス
            l_fxTransFromFXAskingResponse = (WEB3FXTransFromFXAskingResponse) l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_CREATE_NEW, l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error( "DBアクセスエラー。" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBアクセスエラー" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //do振替実行
        WEB3FXTransConnection l_controlService =
            (WEB3FXTransConnection)Services.getService(WEB3FXTransConnection.class);
        WEB3FXGftResultNoticeTelegramUnit  l_fxGftResultNoticeTelegramUnit =
            l_controlService.doTransfer(l_compFxConditionParams, l_fxTransFromFXAskingResponse.fxGftAskingTelegramUnit);

        //インスタンス生成
        //コンストラクタにてインスタンス生成する。
        WEB3FXTransFromFXCompleteRequest l_fxTransFromFXCompleteRequest =
            new WEB3FXTransFromFXCompleteRequest();

        //プロパティセット
        //FXから振替完了リクエストのプロパティをセットする。
        //GFT結果通知電文明細： 生成した結果通知電文明細
        l_fxTransFromFXCompleteRequest.fxGftResultNoticeTelegramUnit =
            l_fxGftResultNoticeTelegramUnit;
        //注文ID： FXから振替依頼レスポンス.注文ID
        l_fxTransFromFXCompleteRequest.orderId =
            l_fxTransFromFXAskingResponse.orderId;
        //確認時発注日： FXから振替依頼レスポンス.確認時発注日
        l_fxTransFromFXCompleteRequest.checkDate =
            l_fxTransFromFXAskingResponse.checkDate;
        //暗証番号：FXから振替完了リクエスト（SOAP接続）.暗証番号
        l_fxTransFromFXCompleteRequest.password = l_request.password;
        //FXシステムコード：FXから振替完了リクエスト（SOAP接続）.FXシステムコード
        l_fxTransFromFXCompleteRequest.fxSystemCode = l_request.fxSystemCode;

        //submit注文(FXから振替完了リクエスト)
        //振替注文完了処理を行う。
        //[引数]
        //リクエストデータ： 生成したFXから振替完了リクエスト
        WEB3FXTransFromFXCompleteResponse l_fxTransFromFXCompleteResponse = null;
        try
        {
            //1.18 FXから振替完了TransactionCallback(FXから振替完了リクエスト)
            WEB3FXTransFromFXCompleteTransactionCallback l_transactionCallback =
                new WEB3FXTransFromFXCompleteTransactionCallback(l_fxTransFromFXCompleteRequest);
    
            //1.19 getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.20 doTransaction(トランザクション属性 : int, トランザクションコールバック : TransactionCallback)
            //[引数]
            //トランザクション属性：　@TX_CREATE_NEW
            //トランザクションコールバック：　@FXから振替完了TransactionCallbackインスタンス
            l_fxTransFromFXCompleteResponse = (WEB3FXTransFromFXCompleteResponse) l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_CREATE_NEW, l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error( "DBアクセスエラー。" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBアクセスエラー" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3FXTransFromFXCompleteSoapResponse l_response =
            (WEB3FXTransFromFXCompleteSoapResponse)l_request.createResponse();

        //プロパティセット
        //以下のとおりに、プロパティをセットする。
        //更新時間： FXから振替完了レスポンス.更新時間
        l_response.lastUpdatedTimestamp =
            l_fxTransFromFXCompleteResponse.lastUpdatedTimestamp;
        //識別番号： FXから振替完了レスポンス.識別番号
        l_response.orderActionId = l_fxTransFromFXCompleteResponse.orderActionId;
        //受渡日： FXから振替完了レスポンス.受渡日
        l_response.deliveryDate = l_fxTransFromFXCompleteResponse.deliveryDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get処理区分)<BR>
     * １）引数．FXシステムコード：06（Hits先OP振替）の場合 <BR> 
     * 　@・05（入金：先OP）を返却する。 <BR> 
     * <BR> 
     * ２）それ以外の場合は、02（入金：FX）を返却する。<BR>  
     * ※引数がnullの場合も含む。 <BR> 
     * <BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード
     * @@return string
     */
    public String getOperationDiv(String l_strFxSystemCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getOperationDiv(String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);
 
        //１）引数．FXシステムコード：06（Hits先OP振替）の場合 
        //　@・05（入金：先OP）を返却する。 
        if (WEB3FxSystemCodeDef.HITS_FUOP_TRANSFER.equals(l_strFxSystemCode))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GftMessageOperationDef.CASH_IN_FUOP;
        }
        
        //２）それ以外の場合は、02（入金：FX）を返却する。 
        //※引数がnullの場合も含む。 
        log.exiting(STR_METHOD_NAME);
        return WEB3GftMessageOperationDef.CASH_IN_FX;        
    }
    
    /**
     * (is受渡日)<BR>
     * 受渡日セットを判定する。 <BR> 
     * <BR>
     * [戻り値] <BR> 
     * true：受渡日をセットする。 <BR>
     * false：受渡日をセットしない。 <BR>
     * <BR>
     * 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR>  
     * <BR>
     * [条件]  <BR>
     * 部店ID = 引き数.部店ID <BR> 
     * プリファ@レンス名 = "fx.deliverydate.insert.check"<BR>  
     * プリファ@レンス名の連番 = 1  <BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値 == ”受渡日をセットする” の場合、true を返却する。<BR>  
     * <BR>
     * ３）それ以外の場合は、falseを返却する。<BR>  
     * ※レコードが取得できなかった場合も含む。<BR>
     * <BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isDeliveryDate(long l_lngBranchId) throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "isDeliveryDate(long l_lngBranchId)";
        log.entering(STR_METHOD_NAME);
        
        //以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。  
        //[条件]  
        //部店ID = 引き数.部店ID  
        //プリファ@レンス名 = "fx.deliverydate.insert.check"  
        //プリファ@レンス名の連番 = 1 
        BranchPreferencesRow l_branchReferencesRow = null;
        try
        {
            l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(              
                l_lngBranchId,
                WEB3BranchPreferencesNameDef.FX_DELIVERY_DATE_INSERT_CHECK,
                1);
        } 
        catch (DataNetworkException l_dqex) 
        {
            log.error("DBへのアクセスに失敗しました:", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataQueryException l_ex) 
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        
        //２）取得したレコード.プリファ@レンスの値 == ”受渡日をセットする” の場合、true を返却する。
        if (l_branchReferencesRow != null && 
            WEB3FxDeliveryDateInsertCheckDef.CHECK.equals(l_branchReferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //３）それ以外の場合は、falseを返却する。  
        //※レコードが取得できなかった場合も含む。
        log.exiting(STR_METHOD_NAME);
        return false;        
    }

    /**
     * (FXから振替依頼TransactionCallbackクラス)<BR>
     */
    public class WEB3FXTransFromFXAskingTransactionCallback implements TransactionCallback
    {
        /**
         * ログユーティリティ<BR>
         */
        private WEB3LogUtility l_log = WEB3LogUtility.getInstance(WEB3FXTransFromFXAskingTransactionCallback.class);

        /**
         * デフォルトコンストラクタ<BR>
         * 引数.リクエストデータを該当の変数に保存する。<BR>
         * @@param l_request - (リクエストデータ)<BR>
         * @@return WEB3FXTransFromFXAskingTransactionCallback
         * @@roseuid 415A6F3F0270
         */
        public WEB3FXTransFromFXAskingTransactionCallback(WEB3FXTransFromFXAskingRequest l_request)
        {
            this.l_fxTransFromFXAskingRequest = l_request;
        }

        /**
         * (FXから振替依頼リクエス) <BR>
         * FXから振替依頼リクエス <BR>
         */
        public WEB3FXTransFromFXAskingRequest l_fxTransFromFXAskingRequest;
        
        /**
         * 振替注文の依頼処理を行う。 <BR>
         *  <BR>
         * １）口座をロックする。 <BR>
         *  <BR>
         *    拡張アカウントマネージャ.lock口座()をコールする。 <BR>
         *  <BR>
         *    ※引数はOpLoginSecurityServiceより編集。 <BR>
         *  <BR>
         * ２）FXから振替サービスImpl.start注文()をコールする。 <BR>
         *  <BR>
         *    [引数] <BR>
         *    リクエストデータ： this.リクエストデータ <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 413C1FDC003F
         */
        public Object process() throws 
            DataNetworkException,
            DataQueryException,
            DataCallbackException
        {
            String STR_METHOD_NAME = " process()";
            l_log.entering(STR_METHOD_NAME);

            WEB3FXTransFromFXAskingResponse l_fxTransFromFXAskingResponse = null;
            //振替注文の依頼処理を行う。
            //１）口座をロックする。
            //   拡張アカウントマネージャ.lock口座()をコールする。
            //   ※引数はOpLoginSecurityServiceより編集。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
            OpLoginSecurityService l_opLoginSecurityService = 
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            try
            {
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
                //証券会社コードを取得する
                String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
                //部店コードを取得する
                String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                //口座コードを取得する
                String l_accountCode = l_mainAccount.getAccountCode(); 
                
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_accountCode);
            
                //２）FXから振替サービスImpl.start注文()をコールする。
                //   [引数]
                //   リクエストデータ： this.リクエストデータ
                l_fxTransFromFXAskingResponse = startOrder(this.l_fxTransFromFXAskingRequest);
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getMainAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_log.entering(STR_METHOD_NAME);
            return l_fxTransFromFXAskingResponse;
        }
    }

    /**
     * (FXから振替完了TransactionCallbackクラス)<BR>
     */
    public class WEB3FXTransFromFXCompleteTransactionCallback implements TransactionCallback
    {
        /**
         * ログユーティリティ<BR>
         */
        private WEB3LogUtility l_log = WEB3LogUtility.getInstance(WEB3FXTransFromFXCompleteTransactionCallback.class);

        /**
         * デフォルトコンストラクタ<BR>
         * 引数.リクエストデータを該当の変数に保存する。<BR>
         * @@param l_request - (リクエストデータ)<BR>
         * @@return WEB3FXTransFromFXCompleteTransactionCallback
         * @@roseuid 415A6F3F0270
         */
        public WEB3FXTransFromFXCompleteTransactionCallback(WEB3FXTransFromFXCompleteRequest l_request)
        {
            this.l_fxTransFromFXCompleteRequest = l_request;
        }

        /**
         * (FXから振替完了リクエスト) <BR>
         * FXから振替完了リクエスト <BR>
         */
        public WEB3FXTransFromFXCompleteRequest l_fxTransFromFXCompleteRequest;
        
        /**
         * 振替注文の登録処理を行う。 <BR>
         *  <BR>
         * １）口座をロックする。 <BR>
         *  <BR>
         *    拡張アカウントマネージャ.lock口座()をコールする。 <BR>
         *  <BR>
         *    ※引数はOpLoginSecurityServiceより編集。 <BR>
         *  <BR>
         * ２）FXから振替サービスImpl.submit注文()をコールする。 <BR>
         *  <BR>
         *    [引数] <BR>
         *    リクエストデータ： this.リクエストデータ <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 413C1FDC003F
         */
        public Object process() throws 
            DataNetworkException,
            DataQueryException,
            DataCallbackException
        {
            String STR_METHOD_NAME = " process()";
            l_log.entering(STR_METHOD_NAME);

            WEB3FXTransFromFXCompleteResponse l_fxTransFromFXCompleteResponse = null;
            //振替注文の登録処理を行う。
            //１）口座をロックする。
            //   拡張アカウントマネージャ.lock口座()をコールする。
            //   ※引数はOpLoginSecurityServiceより編集。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
            OpLoginSecurityService l_opLoginSecurityService = 
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            try
            {
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
                //証券会社コードを取得する
                String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
                //部店コードを取得する
                String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                //口座コードを取得する
                String l_accountCode = l_mainAccount.getAccountCode(); 
                
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_accountCode);
            
                //２）FXから振替サービスImpl.submit注文()をコールする。
                //   [引数]
                //   リクエストデータ： this.リクエストデータ
                l_fxTransFromFXCompleteResponse = submitOrder(this.l_fxTransFromFXCompleteRequest);
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getMainAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_log.entering(STR_METHOD_NAME);
            return l_fxTransFromFXCompleteResponse;
        }
    }
}@
