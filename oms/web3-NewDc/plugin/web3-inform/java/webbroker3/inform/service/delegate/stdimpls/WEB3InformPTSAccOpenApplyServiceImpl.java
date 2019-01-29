head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS口座開設申込サービスImpl(WEB3InformPTSAccOpenApplyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 謝旋 (中訊) 新規作成 モデルNo.124,127
Revision History : 2008/03/04 謝旋 (中訊) モデルNo.131
Revision History : 2008/03/26 王志葵 (中訊) モデルNo.133,134,ＤＢ更新仕様No.022
Revision History : 2009/02/12 SCS大嶋 モデルNo.137
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExtDiv1Def;
import webbroker3.common.define.WEB3ExtDiv2Def;
import webbroker3.common.define.WEB3PTSAccOpenDivDef;
import webbroker3.common.define.WEB3QuestionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.data.QuestionRow;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.WEB3InformClientRequestService;
import webbroker3.inform.define.WEB3InformQuestionAnswerDef;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpResponse;
import webbroker3.inform.message.WEB3InformPTSTradeAgreementUnit;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.inform.service.delegate.WEB3InformPTSAccOpenApplyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (PTS口座開設申込サービスImpl)<BR>
 * PTS口座開設申込サービス実装クラス
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyServiceImpl extends WEB3InformClientRequestService
    implements WEB3InformPTSAccOpenApplyService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformPTSAccOpenApplyServiceImpl.class);

    /**
     * @@roseuid 47B9271A000F
     */
    public WEB3InformPTSAccOpenApplyServiceImpl()
    {

    }

    /**
     * PTS口座開設申込サービス処理を行う。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A013220328
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3InformPTSAccOpenApplyInpRequest)
        {
            //get入力画面
            l_response = this.getInputScreen((WEB3InformPTSAccOpenApplyInpRequest)l_request);
        }
        else if (l_request instanceof WEB3InformPTSAccOpenApplyCnfRequest)
        {
            //validate申込
            l_response = this.validateApply((WEB3InformPTSAccOpenApplyCnfRequest)l_request);
        }
        else if (l_request instanceof WEB3InformPTSAccOpenApplyCmpRequest)
        {
            //submit申込
            l_response = this.submitApply((WEB3InformPTSAccOpenApplyCmpRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * PTS口座開設申込入力画面の取得を行う。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（PTS口座開設申込）get入力画面」 参照。<BR>
     * ======================================================== <BR>
     * 　@　@具体位置：  顧客行.PTS口座開設区分 == "1：口座開設 "の場合、例外をthrowする。<BR>
     * 　@　@classes  :　@WEB3BusinessLayerException<BR>
     * 　@　@tag      :　@BUSINESS_ERROR_03024<BR>
     * ======================================================== <BR>
     * ======================================================== <BR>
     * 　@　@具体位置：  get各種連絡の戻り値 != null 且つ　@<BR>
     * 　@　@　@　@　@　@　@　@各種連絡.get区分２() == "0：未開設"の場合、例外をthrowする。<BR>
     * 　@　@classes  :　@WEB3BusinessLayerException<BR>
     * 　@　@tag      :　@BUSINESS_ERROR_03025<BR>
     * ======================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3InformPTSAccOpenApplyInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A013350249
     */
    protected WEB3InformPTSAccOpenApplyInpResponse getInputScreen(WEB3InformPTSAccOpenApplyInpRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3InformPTSAccOpenApplyInpRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする
        l_request.validate();

        //get補助口座( )
        SubAccount l_subAccount = this.getSubAccount();

        //validate注文(補助口座)
        this.validateOrder(l_subAccount);

        //顧客オブジェクトを取得する
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //顧客行オブジェクトを取得する
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //顧客行.PTS口座開設区分 == "1：口座開設 "の場合、例外をthrowする。
        if (WEB3PTSAccOpenDivDef.ACCOUNT_OPEN.equals(l_mainAccountRow.getPtsAccOpenDiv()))
        {
            log.debug("既に口座開設済みです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03024,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "既に口座開設済みです。");
        }

        //get各種連絡(顧客, String)
        //顧客：　@顧客オブジェクト
        //連絡種別：　@リクエストデータ.連絡種別
        WEB3Inform l_inform =
            WEB3Inform.getVariousInform(l_mainAccount, l_request.informType);

        //get各種連絡の戻り値 != null 且つ
        //各種連絡.get区分２() == "0：未開設"の場合、例外をthrowする
        if (l_inform != null && WEB3ExtDiv2Def.NOT_OPEN.equals(l_inform.getExtDiv2()))
        {
            log.debug("口座開設の申込を受付できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03025,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設の申込を受付できません。");
        }

        //get質問(String, String)
        //証券会社コード： 顧客.証券会社コード
        //部店コード：　@顧客.部店コード
        QuestionParams[] l_questionParams = this.getQuestion(
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode());

        WEB3InformPTSTradeAgreementUnit[] l_informPTSTradeAgreementUnits = null;
        if (l_questionParams != null)
        {
            List l_lisInformPTSTradeAgreementUnits = new ArrayList();
            for (int i = 0; i < l_questionParams.length; i++)
            {
                WEB3InformPTSTradeAgreementUnit l_informPTSTradeAgreementUnit = new WEB3InformPTSTradeAgreementUnit();
                l_informPTSTradeAgreementUnit.questionNumber = l_questionParams[i].getQuestionNo();
                l_informPTSTradeAgreementUnit.questionContents = l_questionParams[i].getQuestion();

                l_lisInformPTSTradeAgreementUnits.add(l_informPTSTradeAgreementUnit);
            }

            l_informPTSTradeAgreementUnits =
                new WEB3InformPTSTradeAgreementUnit[l_lisInformPTSTradeAgreementUnits.size()];
            l_lisInformPTSTradeAgreementUnits.toArray(l_informPTSTradeAgreementUnits);
        }

        //レスポンスデータを生成する
        WEB3InformPTSAccOpenApplyInpResponse l_response =
            (WEB3InformPTSAccOpenApplyInpResponse)l_request.createResponse();

        //顧客名：　@顧客行.名前（苗字）
        l_response.accountName = l_mainAccountRow.getFamilyName();
        //　@PTS取引同意質問情報一覧：
        //　@－get質問の戻り値 == null の場合、null
        //　@－get質問の戻り値 != null の場合、上記で編集したPTS取引同意質問情報の配列
        l_response.ptsTradeAgreementList = l_informPTSTradeAgreementUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate申込)<BR>
     * PTS口座開設申込確認処理を行う。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（PTS口座開設申込）validate申込」 参照。<BR>
     * =============================================== <BR>
     * 　@具体位置 : 顧客行.PTS口座開設区分 == "1：口座開設 "の場合、<BR>
     * 　@　@　@　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException  <BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_03024<BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * 　@具体位置 :get各種連絡の戻り値 != null且つ <BR>
     * 　@　@　@　@　@　@各種連絡.get区分２() == "0：未開設"の場合、例外をthrowする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException  <BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_03025<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3InformPTSAccOpenApplyCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A01373026B
     */
    protected WEB3InformPTSAccOpenApplyCnfResponse validateApply(WEB3InformPTSAccOpenApplyCnfRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateApply(WEB3InformPTSAccOpenApplyCnfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        // get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //validate注文(補助口座)
        this.validateOrder(l_subAccount);
        //getMainAccount( )
        //顧客オブジェクトを取得する
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        //顧客行オブジェクトを取得する
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //顧客行.PTS口座開設区分 == "1：口座開設 "の場合、例外をthrowする
        if (WEB3PTSAccOpenDivDef.ACCOUNT_OPEN.equals(l_mainAccountRow.getPtsAccOpenDiv()))
        {
            log.debug("既に口座開設済みです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03024,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "既に口座開設済みです。");
        }

        //get各種連絡(顧客, String)
        WEB3Inform l_inform = WEB3Inform.getVariousInform(
            (WEB3GentradeMainAccount)l_mainAccount, l_request.informType);

        //get各種連絡の戻り値 != null 且つ　@各種連絡.get区分２() == "0：未開設"の場合、例外をthrowする。
        if (l_inform != null && WEB3ExtDiv2Def.NOT_OPEN.equals(l_inform.getExtDiv2()))
        {
            log.debug("口座開設の申込を受付できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03025,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設の申込を受付できません。");
        }

        //リクエストデータ.PTS取引同意質問情報一覧 != nullの場合、以下の処理を行う
        if (l_request.ptsTradeAgreementList != null)
        {
            //validatePTS口座開設質問(PTS取引同意質問情報[])
            //[引数]
            // PTS取引同意質問情報一覧： リクエストデータ.PTS取引同意質問情報一覧
            this.validatePTSAccOpenQuestion(l_request.ptsTradeAgreementList);
        }

        //電子鳩チェックを行う
        //リクエスト.電子鳩チェックフラグ == trueの場合
        String[] l_tradingDocReadHistorys = null;
        if (l_request.batoCheckFlag)
        {
            //validatePTS取引ドキュメント閲覧履歴
            l_tradingDocReadHistorys =
                this.validatePTSTradingDocReadHistory(l_request.typeCode, l_request.productCode);
        }

        WEB3InformPTSAccOpenApplyCnfResponse l_response =
            (WEB3InformPTSAccOpenApplyCnfResponse)l_request.createResponse();
        //レスポンスデータプロパティに以下の通り値をセットする。
        //validatePTS取引ドキュメント閲覧履歴()の戻り値配列（nullの場合はセットしない）
        //※リクエスト.電子鳩チェックフラグ == falseの場合セットしない
        if (l_tradingDocReadHistorys != null && l_request.batoCheckFlag)
        {
            l_response.productCode = l_tradingDocReadHistorys;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit申込)<BR>
     * PTS口座開設申込完了処理を行う。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（PTS口座開設申込）submit申込」 参照。<BR>
     * ======================================================== <BR>
     * 　@　@具体位置：  顧客行.PTS口座開設区分 == "1：口座開設 "の場合、例外をthrowする。<BR>
     * 　@　@classes  :　@WEB3BusinessLayerException<BR>
     * 　@　@tag      :　@BUSINESS_ERROR_03024<BR>
     * ======================================================== <BR>
     * ======================================================== <BR>
     * 　@　@具体位置：  get各種連絡の戻り値 != null 且つ　@<BR>
     * 　@　@　@　@　@　@　@　@各種連絡.get区分２() == "0：未開設"の場合、例外をthrowする。<BR>
     * 　@　@classes  :　@WEB3BusinessLayerException<BR>
     * 　@　@tag      :　@BUSINESS_ERROR_03025<BR>
     * ======================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3InformPTSAccOpenApplyCmpResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A0137602F7
     */
    protected WEB3InformPTSAccOpenApplyCmpResponse submitApply(WEB3InformPTSAccOpenApplyCmpRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitApply(WEB3InformPTSAccOpenApplyCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする
        l_request.validate();

        //get口座
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();
        
        //顧客行オブジェクトを取得する
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //FinApp, GenTradeAccountManager, OpLoginSecurityService
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();   	
        
        //lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        l_accManager.lockAccount(
                l_mainAccountRow.getInstitutionCode(),
                l_mainAccountRow.getBranchCode(),
                l_mainAccountRow.getAccountCode());
        
        //get補助口座( )
        SubAccount l_subAccount = this.getSubAccount();

        //validate注文(補助口座)
        this.validateOrder(l_subAccount);

        //get代理入力者( )
        Trader l_trader = this.getTrader();

        //validate取引パスワード
        //代理入力者： get代理入力者()の戻り値
        //補助口座： get補助口座()の戻り値
        //パスワード： リクエストデータ.暗証番号
        WEB3GentradeOrderValidator l_validator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_orderValidationResult =
            l_validator.validateTradingPassword(l_trader, l_subAccount, l_request.password);

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug(STR_METHOD_NAME
                + l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
        }



        //顧客行.PTS口座開設区分 == "1：口座開設 "の場合、例外をthrowする
        if (WEB3PTSAccOpenDivDef.ACCOUNT_OPEN.equals(l_mainAccountRow.getPtsAccOpenDiv()))
        {
            log.debug("既に口座開設済みです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03024,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "既に口座開設済みです。");
        }

        //get各種連絡(顧客, String)
        //顧客：　@顧客オブジェクト
        //連絡種別：　@リクエストデータ.連絡種別
        WEB3Inform l_inform =
            WEB3Inform.getVariousInform(l_mainAccount, l_request.informType);

        //get各種連絡の戻り値 != null 且つ
        //各種連絡.get区分２() == "0：未開設"の場合、例外をthrowする
        if (l_inform != null && WEB3ExtDiv2Def.NOT_OPEN.equals(l_inform.getExtDiv2()))
        {
            log.debug("口座開設の申込を受付できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03025,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設の申込を受付できません。");
        }

        //get新規識別コード
        //[引数]
        //証券会社コード： 顧客行.証券会社コード
        //連絡種別： リクエストデータ.連絡種別
        WEB3InformHostReqOrderNumberManageService l_informHostReqOrderNumberManageService =
            (WEB3InformHostReqOrderNumberManageService)Services.getService(
                WEB3InformHostReqOrderNumberManageService.class);
        String l_strNewOrderRequestCode = l_informHostReqOrderNumberManageService.getNewOrderRequestCode(
            l_mainAccountRow.getInstitutionCode(),
            l_request.informType);

        //リクエストデータ.PTS取引同意質問情報一覧 != nullの場合、以下の処理を行う
        if (l_request.ptsTradeAgreementList != null)
        {
            //validatePTS口座開設質問(PTS取引同意質問情報[])
            //PTS取引同意質問情報一覧： リクエストデータ.PTS取引同意質問情報一覧
            this.validatePTSAccOpenQuestion(l_request.ptsTradeAgreementList);

            //get新規識別コード
            //[引数の設定]
            //証券会社コード： 顧客行.証券会社コード
            //部店コード：　@顧客行.部店コード
            //銘柄タイプ： ProductTypeEnum.その他
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);

            String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_mainAccountRow.getInstitutionCode(),
                l_mainAccountRow.getBranchCode(),
                ProductTypeEnum.OTHER);

            //insert質問回答(String, String, String, PTS取引同意質問情報[])
            //[引数]
            //証券会社コード： 顧客.証券会社コード
            //部店コード： 顧客.部店コード
            //識別コード： 注文識別コード採番サービス.get新規識別コード()の戻り値
            //質問情報一覧： リクエストデータ.PTS取引同意質問情報一覧
            this.insertQuestionAnswer(
                l_mainAccountRow.getInstitutionCode(),
                l_mainAccountRow.getBranchCode(),
                l_strNewNumber,
                l_request.ptsTradeAgreementList);
        }

        String l_strLastUpdater = null;
        if (l_trader != null)
        {
            //－代理入力の場合、代理入力者.扱者コード
            l_strLastUpdater = l_trader.getTraderCode();
        }
        else
        {
            //　@－顧客入力の場合、顧客行.口座コード
            l_strLastUpdater = l_mainAccountRow.getAccountCode().substring(0, 6);
        }

        //get各種連絡の戻り値 != null の場合、以下の処理を行う。
        if (l_inform != null)
        {
            //以下の内容のMapオブジェクトを生成する。
            Map l_map = new HashMap();
            //区分１：　@"0"（無効）
            l_map.put("ext_div1", WEB3ExtDiv1Def.INVALIDITY);

            //更新者コード：　@（以下のとおり）
            //　@－顧客入力の場合、顧客行.口座コードの頭6桁
            //　@－代理入力の場合、代理入力者.扱者コード
            l_map.put("last_updater", l_strLastUpdater);

            //更新日時：　@処理日時
            l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            //updatePTS各種連絡(Map)
            l_inform.updatePTSVariousInform(l_map);
        }

        //createNew各種連絡
        //顧客：　@顧客オブジェクト
        //連絡種別：　@リクエストデータ.連絡種別
        //PTS口座開設区分：　@リクエストデータ.PTS口座開設区分
        //更新者コード：　@（以下のとおり）
        // －顧客入力の場合、顧客行.口座コードの頭6桁
        // －代理入力の場合、代理入力者.扱者コード
        //識別コード：　@連絡管理識別コード採番サービス.get新規識別コード()の戻り値
        WEB3Inform l_newInform = WEB3Inform.createNewVariousInform(
            l_mainAccount,
            l_request.informType,
            l_request.ptsAccOpenDiv,
            l_strLastUpdater,
            l_strNewOrderRequestCode);

        //saveNew各種連絡
        l_newInform.saveNewVariousInform();

        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //顧客マスタの以下の項目を更新する。
            //【ｘTrade】補足資料.DB更新「PTS口座開設申込_顧客マスター」参照
            Map l_mainAccountMap = new HashMap();
            //PTS口座開設区分：　@リクエストデータ.PTS口座開設区分
            l_mainAccountMap.put("pts_acc_open_div", l_request.ptsAccOpenDiv);
            //PTS口座開設区分更新者コード：　@（以下のとおり）
            //顧客入力の場合、顧客マスタテーブル.口座コードの頭6桁
            //代理入力の場合、代理入力者.扱者コード
            l_mainAccountMap.put("pts_acc_open_div_last_updater", l_strLastUpdater);
            //PTS口座開設区分更新日時：　@処理日時
            l_mainAccountMap.put("pts_acc_open_div_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

            //primaryKey
            MainAccountPK l_mainAccountPK = new MainAccountPK(l_mainAccountRow.getAccountId());
            //doUpdateQuery(arg0 : PrimaryKey, arg1 : Map)
            l_queryProcessor.doUpdateQuery(l_mainAccountPK, l_mainAccountMap);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        WEB3InformPTSAccOpenApplyCmpResponse l_response =
            (WEB3InformPTSAccOpenApplyCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate注文)<BR>
     * 注文共通チェックを実施する。<BR>
     * <BR>
     * １）　@受付時間チェック・システム取引停止チェックを行う。<BR>
     * 　@１－１）　@取引時間管理.validate注文受付可能()をコールする。<BR>
     * <BR>
     * ２）　@顧客別取引停止チェックを行う。<BR>
     * 　@２－１）　@GtlUtils.getCommonOrderValidator()メソッドをコールし、<BR>
     * 　@　@注文チェックオブジェクトを取得する。<BR>
     * <BR>
     * 　@２－２）　@注文チェック.validate取引可能顧客()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[validate取引可能顧客()にセットするパラメータ]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 47B11D8A024B
     */
    private void validateOrder(SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(SubAccount)";
        log.entering(STR_METHOD_NAME);

        //受付時間チェック・システム取引停止チェックを行う。
        // 取引時間管理.validate注文受付可能()をコールする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //顧客別取引停止チェックを行う。
        // 　@GtlUtils.getCommonOrderValidator()メソッドをコールし、
        // 　@　@注文チェックオブジェクトを取得する。
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

        //注文チェック.validate取引可能顧客()メソッドをコールする
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug(STR_METHOD_NAME
                + l_validationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get質問)<BR>
     * 引数の証券会社コード、部店コードに一致する<BR>
     * 質問Paramsを返却する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@質問テーブル(question)を以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * 　@部店コード=引数.部店コード<BR>
     * 　@質問区分="0003"（PTS）<BR>
     * <BR>
     * ２）検索結果を「質問番号」項目の昇順でソートし、返却する。<BR>
     * 　@※検索結果が取得できなかった場合、nullを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@return QuestionParams[]
     * @@throws WEB3BaseException
     * @@roseuid 47A2C3110250
     */
    private QuestionParams[] getQuestion(String l_strInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getQuestion(String, String)";
        log.entering(STR_METHOD_NAME);

        //DB検索<BR>
        //質問テーブル(question)を以下の条件で検索する。
        // 　@[条件]
        // 　@証券会社コード=引数.証券会社コード
        // 　@部店コード=引数.部店コード
        // 　@質問区分="0003"（PTS）
        StringBuffer l_sbWhere = new StringBuffer();

        l_sbWhere.append(" institution_code = ?");
        l_sbWhere.append(" and branch_code = ?");
        l_sbWhere.append(" and question_div = ?");

        Object[] l_sqlValues = {
            l_strInstitutionCode,
            l_strBranchCode,
            WEB3QuestionDivDef.PTS};

        //検索結果を「質問番号」項目の昇順でソートし、返却する。
        String l_strOrderBy = " question_no asc";

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                QuestionRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_sqlValues);
        }
        catch (DataNetworkException l_dnEx)
        {
            log.error("DBへのアクセスに失敗しました。", l_dnEx);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dnEx.getMessage(),
                l_dnEx);
        }
        catch (DataQueryException l_dqEx)
        {
            log.error("DBへのアクセスに失敗しました。", l_dqEx);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqEx.getMessage(),
                l_dqEx);
        }

        //※検索結果が取得できなかった場合、nullを返却する。
        if (l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        QuestionParams[] l_questionParams = new QuestionParams[l_lisRecords.size()];
        for (int i = 0; i < l_lisRecords.size(); i++)
        {
            QuestionRow l_questionRow = (QuestionRow)l_lisRecords.get(i);
            l_questionParams[i] = new QuestionParams(l_questionRow);
        }

        log.exiting(STR_METHOD_NAME);
        return l_questionParams;
    }

    /**
     * (validatePTS口座開設質問)<BR>
     * PTS口座開設質問に対する回答の整合性をチェックする。 <BR>
     * <BR>
     * 引数.PTS取引同意質問情報一覧の要素ごとのLoop処理にて、 <BR>
     * 以下のチェックを行う。 <BR>
     * <BR>
     * PTS取引同意質問情報.質問回答≠”1：同意”の場合、例外をthorwする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_03026<BR>
     * <BR>
     * @@param l_ptsTradeAgreementList - (PTS取引同意質問情報一覧)<BR>
     * PTS取引同意質問情報一覧
     * @@throws WEB3BaseException
     * @@roseuid 47A8174302AC
     */
    private void validatePTSAccOpenQuestion(WEB3InformPTSTradeAgreementUnit[] l_ptsTradeAgreementList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSAccOpenQuestion(WEB3InformPTSTradeAgreementUnit[])";
        log.entering(STR_METHOD_NAME);

        //引数.PTS取引同意質問情報一覧の要素ごとのLoop処理にて、
        for (int i = 0; i < l_ptsTradeAgreementList.length; i++)
        {
            //PTS取引同意質問情報.質問回答≠”1：同意”の場合、例外をthorwする。
            if (!WEB3InformQuestionAnswerDef.YES.equals(l_ptsTradeAgreementList[i].questionAnswer))
            {
                log.debug("質問回答は同意ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03026,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "質問回答は同意ではありません。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert質問回答)<BR>
     * PTS取引同意質問情報の内容で質問回答テーブル(question_answer)に行のinsertを行う。
     * <BR>
     * ※引数.PTS取引同意質問情報一覧の要素数分のLoop処理を行い、要素ごとに行のinsertを行う。<BR>
     * <BR>
     * 挿入する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「PTS口座開設申込_質問回答テーブル.xls」<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード
     * @@param l_ptsTradeAgreementList - (PTS取引同意質問情報一覧)<BR>
     * PTS取引同意質問情報一覧
     * @@throws WEB3BaseException
     * @@roseuid 47A9793003BD
     */
    private void insertQuestionAnswer(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strRequestNumber,
        WEB3InformPTSTradeAgreementUnit[] l_ptsTradeAgreementList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertQuestionAnswer(String, String, String, WEB3InformPTSTradeAgreementUnit[])";
        log.entering(STR_METHOD_NAME);

        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //引数.PTS取引同意質問情報一覧の要素数分のLoop処理を行い、要素ごとに行のinsertを行う
            for (int i = 0; i < l_ptsTradeAgreementList.length; i++)
            {
                QuestionAnswerParams l_questionAnswerParams = new QuestionAnswerParams();
                //証券会社コード
                l_questionAnswerParams.setInstitutionCode(l_strInstitutionCode);
                //部店コード
                l_questionAnswerParams.setBranchCode(l_strBranchCode);
                //質問区分
                l_questionAnswerParams.setQuestionDiv(WEB3QuestionDivDef.PTS);
                //識別コード
                l_questionAnswerParams.setOrderRequestNumber(l_strRequestNumber);
                //質問番号
                l_questionAnswerParams.setQuestionNo(l_ptsTradeAgreementList[i].questionNumber);
                //質問回答
                l_questionAnswerParams.setQuestionAnswer(l_ptsTradeAgreementList[i].questionAnswer);
                //更新者コード
                l_questionAnswerParams.setLastUpdater(null);
                //作成日付
                l_questionAnswerParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                //更新日付
                l_questionAnswerParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                //insertを行う
                l_queryProcessor.doInsertQuery(l_questionAnswerParams);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePTS取引ドキュメント閲覧履歴)<BR>
     * 電子鳩システムへ接続し、該当顧客の閲覧履歴が存在するかのチェックを行う。<BR>
     * <BR>
     * １）　@目論見書閲覧チェック<BR>
     * 　@１－１）　@ArrayListの作成。<BR>
     * <BR>
     * 　@１－２）　@銘柄コードの要素数分、以下の処理を実施。<BR>
     * <BR>
     * 　@　@１－２－１）　@電子鳩システム接続サービスImpl.validate目論見書閲覧を実施。<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@種別コード：引数.種別コード<BR>
     * 　@　@　@　@銘柄コード：引数.銘柄コード[index]<BR>
     * <BR>
     * 　@　@１－２－２）　@取得した目論見書閲覧チェック結果.チェック結果が<BR>
     * 　@　@「1： 閲覧未済」の場合、ArrayListに銘柄コードを追加。<BR>
     * <BR>
     * ２）　@目論見書閲覧チェック結果の確認<BR>
     * 　@２－１）　@作成したArrayList（１-１で作成）の要素数が「０」の場合、<BR>
     * 　@「NULL」を返却する。<BR>
     * <BR>
     * 　@２－２）　@作成したArrayList（１-１で作成）の要素数が「０」でない場合、<BR>
     * 　@配列に変換した銘柄コードを返却する。<BR>
     * @@param l_strTypeCode - (種別コード)<BR>
     * 種別コード
     * @@param l_strProductCodes - (銘柄コード)<BR>
     * 銘柄コード
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 47ABE1D8016E
     */
    private String[] validatePTSTradingDocReadHistory(String l_strTypeCode, String[] l_strProductCodes)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSTradingDocReadHistory(String, String[])";
        log.entering(STR_METHOD_NAME);

        //ArrayListの作成。
        List l_lisProductCodes = new ArrayList();

        WEB3GentradeBatoClientService l_batoClientServiceImpl =
            (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);
        WEB3GentradeProspectusResult l_prospectusResult = null;

        //電子鳩システム接続サービスImpl.validate目論見書閲覧を実施。
        //        [引数]
        //        種別コード：引数.種別コード
        //        銘柄コード：引数.銘柄コード[index]
        for (int i = 0; i <l_strProductCodes.length; i++)
        {
            l_prospectusResult = l_batoClientServiceImpl.validateProspectus(l_strTypeCode, l_strProductCodes[i]);
            //取得した目論見書閲覧チェック結果.チェック結果が
            //「1： 閲覧未済」の場合、ArrayListに銘柄コードを追加。
            if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(l_prospectusResult.checkResult))
            {
                l_lisProductCodes.add(l_strProductCodes[i]);
            }
        }

        //目論見書閲覧チェック結果の確認
        // 　@作成したArrayListの要素数が「０」の場合、NULL」を返却する。
        String[] l_strProductCodeReturns = null;
        if (l_lisProductCodes.size() != 0)
        {
            //作成したArrayListの要素数が「０」でない場合、
            // 　@配列に変換した銘柄コードを返却する。
            l_strProductCodeReturns = new String[l_lisProductCodes.size()];
            l_lisProductCodes.toArray(l_strProductCodeReturns);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strProductCodeReturns;
    }
}
@
