head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済仮注文作成サービスImpl(WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 何文敏(中訊) 新規作成 仕様変更モデルNo.131、139、140
Revision History : 2007/06/04　@柴双紅(中訊) 仕様変更 モデルNo.156
Revision History : 2007/08/21 柴双紅(中訊) 仕様変更モデルNo.162
Revision History : 2008/01/17 孟亞南(中訊) 仕様変更モデルNo.179 ＤＢ更新仕様No.017
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ForcedSettleReasonType;
import webbroker3.common.define.WEB3ForcedsettleorderDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.define.WEB3ForcedSettleTypeDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleTempOrderCreateService;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;
import webbroker3.tradingpower.define.WEB3TPForcedSettleReasonDef;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (強制決済仮注文作成サービスImpl)<BR>
 * 強制決済仮注文作成サービス実装クラス<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl implements
    WEB3AdminEquityForcedSettleTempOrderCreateService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl.class);

    /**
     * @@roseuid 462CA4210321
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl()
    {

    }

    /**
     * 強制決済仮注文作成サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（強制決済仮注文作成）仮注文作成」参照<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@roseuid 46036ACC016F
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        // validate
        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストが未指定(null)です。");
        }

        if (!(l_request instanceof WEB3AdminEquityForcedSettleTempOrderCreateRequest))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        WEB3AdminEquityForcedSettleTempOrderCreateRequest l_adminEquityForcedSettleTempOrderCreateRequest =
            (WEB3AdminEquityForcedSettleTempOrderCreateRequest)l_request;

        l_adminEquityForcedSettleTempOrderCreateRequest.validate();

        Object l_objDoTransaction = null;
        boolean l_blnIsError = false;

        try
        {
            // getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // オンライン実行結果transactionCallback
            String l_strForcedSettleStatus = "";

            if (WEB3ForcedSettleTypeDef.BEFORE_ONLINE.equals(
                l_adminEquityForcedSettleTempOrderCreateRequest.forcedSettleType))
            {
                l_strForcedSettleStatus = WEB3OnlineServiceDiv.FORCED_SETTLE_BEFORE_ONLINE;
            }
            else if (WEB3ForcedSettleTypeDef.REST_TIMEZONE.equals(
                l_adminEquityForcedSettleTempOrderCreateRequest.forcedSettleType))
            {
                l_strForcedSettleStatus = WEB3OnlineServiceDiv.FORCED_SETTLE_MARKET;
            }

            WEB3GentradeOnlineRunStatusTransactionCallback l_onlineRunStatusTransactionCallback =
                new WEB3GentradeOnlineRunStatusTransactionCallback(
                    l_adminEquityForcedSettleTempOrderCreateRequest.institutionCode,
                    l_adminEquityForcedSettleTempOrderCreateRequest.rangeFrom,
                    l_adminEquityForcedSettleTempOrderCreateRequest.rangeTo,
                    l_strForcedSettleStatus);

            // doTransaction(TX_CREATE_NEW : int, オンライン実行結果TransactionCallback : TransactionCallback) 
            l_objDoTransaction = l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_onlineRunStatusTransactionCallback);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            // set処理中()から二重起動の例外がthrowされた場合
            Object l_exception = l_dataCallbackException.getDetails();
            if (l_exception instanceof WEB3BaseException) 
            {
                WEB3BaseException l_baseException = (WEB3BaseException)l_exception;
                if (WEB3ErrorCatalog.BUSINESS_ERROR_01992.equals(l_baseException.getErrorInfo()))
                {
                    log.error("指定AP起動中（二重起動エラー）。", l_baseException);
                    log.exiting(STR_METHOD_NAME);

                    WEB3AdminEquityForcedSettleTempOrderCreateResponse l_response =
                        (WEB3AdminEquityForcedSettleTempOrderCreateResponse)
                            l_adminEquityForcedSettleTempOrderCreateRequest.createResponse();

                    log.exiting(STR_METHOD_NAME);
                    return l_response;
                }
            }
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataNetworkException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }

        WEB3GentradeOnlineRunStatus l_onlineRunStatus =
            (WEB3GentradeOnlineRunStatus)l_objDoTransaction;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_genAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        // getInstitution
        Institution l_institution = null;
        try
        {
            l_institution = l_genAccountManager.getInstitution(
                l_adminEquityForcedSettleTempOrderCreateRequest.institutionCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(" テーブルに該当するデータがありません: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 強制決済対象の会社が連続注文取扱可能か判定する。 
        WEB3ToSuccOrderManagerImpl l_toOrderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //[引数]
        //証券会社コード：　@リクエスト.証券会社コード
        //銘柄タイプ：　@"株式"
        //先物／オプション区分：　@"DEFAULT"
        boolean l_blnIsSuccOrderHandling = true;
        try
        {
            l_toOrderManager.validateSuccOrderHandling(
                l_adminEquityForcedSettleTempOrderCreateRequest.institutionCode,
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT);
        }
        catch (WEB3BaseException l_ex)
        {
            l_blnIsSuccOrderHandling = false;
        }

        WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService l_service =
            (WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService)Services.getService
                (WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class);

        String l_strForcedSettleType =
            l_adminEquityForcedSettleTempOrderCreateRequest.forcedSettleType;

        InstitutionRow l_insRow = (InstitutionRow) l_institution.getDataSourceObject();
        String l_strForcedSettleOrderDiv = l_insRow.getForcedsettleorderDiv();

        // 分岐フロー
        if (WEB3ForcedSettleTypeDef.BEFORE_ONLINE.equals(l_strForcedSettleType)
            && (WEB3ForcedsettleorderDivDef.SETTLEDAY_COME.equals(
                l_strForcedSettleOrderDiv)
                || WEB3ForcedsettleorderDivDef.SETTLEDAY_COME_AND_GUARANTEE_MAINTENANCE_BREAK.equals(
                    l_strForcedSettleOrderDiv)))
        {
            // get強制決済期日到来建株一覧
            //[引数] 
            //証券会社 : getInstitution()の戻り値 
            //From口座ID：　@リクエスト.From口座ID 
            //To口座ID：　@リクエスト.To口座ID
            List l_lisContractList = WEB3EquityPositionManager.getForcedSettleCloseDateContractList(
                l_institution,
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeFrom,
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeTo
                );

            int l_intListSize = l_lisContractList.size();
            // get強制決済期日到来建株一覧()で取得したListの要素数分Loop処理
            for (int i = 0; i < l_intListSize; i++)
            {
                // exec仮注文作成(EqtypeContractRow, String, 建玉強制決済結果, boolean)
                try
                {
                    l_service.execTempOrderCreation(
                        (EqtypeContractRow)l_lisContractList.get(i),
                        WEB3ForcedSettleReasonType.FIXED_DATE_COMING,
                        null,
                        l_blnIsSuccOrderHandling
                        );
                }
                catch (Exception l_ex)
                {
                    log.error("__an WEB3BaseException of exec仮注文作成", l_ex);
                    l_blnIsError = true;
                }
            }
        }

        // String( )
        String l_strAccIdKey = "";

        // 分岐フロー
        if (WEB3ForcedSettleTypeDef.BEFORE_ONLINE.equals(l_strForcedSettleType)
            && (WEB3ForcedsettleorderDivDef.GUARANTEE_MAINTENANCE_BREAK.equals(
                l_strForcedSettleOrderDiv)
                || WEB3ForcedsettleorderDivDef.SETTLEDAY_COME_AND_GUARANTEE_MAINTENANCE_BREAK.equals(
                    l_strForcedSettleOrderDiv)))
        {
            // get建株一覧(From口座ID : long, To口座ID : long)
            List l_lisContracts = WEB3EquityPositionManager.getContracts(
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeFrom,
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeTo
                );

            WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService l_createUnitService =
                (WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService)Services.getService(
                    WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class);
            int l_intListSize = l_lisContracts.size();
            // get強制決済期日到来建株一覧()で取得したListの要素数分Loop処理
            WEB3TPContractForcedSettleResult l_tpResult = null;
            for (int i = 0; i < l_intListSize; i ++)
            {
                EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)l_lisContracts.get(i);
                String l_strAccId = l_eqtypeContractRow.getAccountId() + "";

                // 処理要素の口座ID ≠ 「口座IDキー」の場合
                if (!l_strAccId.equals(l_strAccIdKey))
                {
                    //validate建玉強制決済(EqtypeContractRow)
                    try
                    {
                        l_tpResult = l_createUnitService.validateContractForcedSettle(l_eqtypeContractRow);
                    }
                    catch (Exception l_ex)
                    {
                        log.error("__an WEB3BaseException of validate建玉強制決済", l_ex);
                        l_blnIsError = true;
                        continue;
                    }

                    // 処理要素の口座IDを「口座IDキー」に代入する。
                    l_strAccIdKey = l_eqtypeContractRow.getAccountId() + "";
                }

                // validate建玉強制決済()の戻り値.判定フラグ ＝ trueの場合、一件処理を行う。
                if (l_tpResult.resultFlg)
                {
                    String l_strForcedSettleResonType = null;

                    if (WEB3TPForcedSettleReasonDef.BEFORE_ONLINE_SLIGHTNESS.equals(
                        l_tpResult.forcedSettleReason))
                    {
                        l_strForcedSettleResonType =
                            WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS;
                    }
                    if (WEB3TPForcedSettleReasonDef.BEFORE_ONLINE_SERIOUSNESS.equals(
                        l_tpResult.forcedSettleReason))
                    {
                        l_strForcedSettleResonType =
                            WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS;
                    }
                    //[”オンライン開始前（法@定）”の場合]
                    //”保証金維持率割れ（オンライン開始前・法@定）”
                    if (WEB3TPForcedSettleReasonDef.BEFORE_ONLINE_LEGAL.equals(
                        l_tpResult.forcedSettleReason))
                    {
                        l_strForcedSettleResonType =
                            WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL;
                    }

                    // exec仮注文作成(EqtypeContractRow, String, 建玉強制決済結果, boolean)
                    try
                    {
                        l_createUnitService.execTempOrderCreation(
                            l_eqtypeContractRow,
                            l_strForcedSettleResonType,
                            l_tpResult,
                            l_blnIsSuccOrderHandling
                            );
                    }
                    catch (Exception l_ex)
                    {
                        log.error("__an WEB3BaseException of exec仮注文作成", l_ex);
                        l_blnIsError = true;
                    }
                }
            }
        }

        //分岐フロー
        //リクエストデータ.強制決済処理区分が、"休憩時間帯"かつ、
        //（証券会社.信用強制決済実施区分 ＝ "保証金維持率割れ"または、
        // "決済期日到来建＋保証金維持率割れ"）の場合実施する。
        if (WEB3ForcedSettleTypeDef.REST_TIMEZONE.equals(l_strForcedSettleType)
            && (WEB3ForcedsettleorderDivDef.GUARANTEE_MAINTENANCE_BREAK.equals(l_strForcedSettleOrderDiv)
                || WEB3ForcedsettleorderDivDef.SETTLEDAY_COME_AND_GUARANTEE_MAINTENANCE_BREAK.equals(
                    l_strForcedSettleOrderDiv)))
        {
            // get建株一覧(From口座ID : long, To口座ID : long)
            List l_lisContracts = WEB3EquityPositionManager.getContracts(
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeFrom,
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeTo);

            //強制決済保証金維持率割れ（場間）仮注文作成一件サービス
            WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService l_createUnitService =
                (WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService)Services.getService(
                    WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class);
            int l_intListSize = l_lisContracts.size();
            // get建株一覧()で取得したListの要素数分Loop処理
            WEB3TPContractForcedSettleResult l_tpResult = null;
            for (int i = 0; i < l_intListSize; i ++)
            {
                EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)l_lisContracts.get(i);
                String l_strAccId = l_eqtypeContractRow.getAccountId() + "";

                // 処理要素の口座ID ≠ 「口座IDキー」の場合
                if (!l_strAccId.equals(l_strAccIdKey))
                {
                    //validate建玉強制決済(EqtypeContractRow)
                    try
                    {
                        l_tpResult = l_createUnitService.validateContractForcedSettle(l_eqtypeContractRow);
                    }
                    catch (Exception l_ex)
                    {
                        log.error("__an WEB3BaseException of validate建玉強制決済", l_ex);
                        l_blnIsError = true;
                        continue;
                    }

                    // 処理要素の口座IDを「口座IDキー」に代入する。
                    l_strAccIdKey = l_eqtypeContractRow.getAccountId() + "";
                }

                // validate建玉強制決済()の戻り値.判定フラグ ＝ trueの場合、一件処理を行う。
                if (l_tpResult.resultFlg)
                {
                    // exec仮注文作成(EqtypeContractRow, String, 建玉強制決済結果, boolean)
                    try
                    {
                        l_createUnitService.execTempOrderCreation(
                            l_eqtypeContractRow,
                            WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET,
                            l_tpResult,
                            l_blnIsSuccOrderHandling);
                    }
                    catch (Exception l_ex)
                    {
                        log.error("__an WEB3BaseException of exec仮注文作成", l_ex);
                        l_blnIsError = true;
                    }
                }
            }
        }
        // update実行ステータス区分(実行ステータス区分 : String)
        //処理区分：　@上記処理中にエラーが発生した場合、"エラー"
        //　@　@　@　@　@　@　@　@以外、"処理済"
        String l_strRunStatusDiv = null;
        if(l_blnIsError)
        {
            l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
        }
        else
        {
            l_strRunStatusDiv = WEB3RunStatusDivDef.DEALED;
        }

        l_onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);

        WEB3AdminEquityForcedSettleTempOrderCreateResponse l_response =
            (WEB3AdminEquityForcedSettleTempOrderCreateResponse)
                l_adminEquityForcedSettleTempOrderCreateRequest.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (オンライン実行結果TransactionCallback)<BR>
     * オンライン実行結果TransactionCallbackクラス。<BR>
     */
    public class WEB3GentradeOnlineRunStatusTransactionCallback implements TransactionCallback
    {
        /**
         * (From口座ID)<BR>
         * From口座ID<BR>
         */
        private long rangeFrom;

        /**
         * (To口座ID)<BR>
         * To口座ID<BR>
         */
        private long rangeTo;

        /**
         * (証券会社コード)<BR>
         * 証券会社コード<BR>
         */
        private String institutionCode;

        /**
         * (オンラインサービス区分)<BR>
         * オンラインサービス区分<BR>
         */
        private String onlineServiceDiv;

        /**
         * トランザクション処理を実施する。<BR>
         * <BR>
         * オンライン実行結果テーブルへ"処理中"設定を行う。<BR>
         * <BR>
         * ※戻り値にオンライン実行結果オブジェクトを返却する。<BR>
         * @@return Object
         * @@roseuid 460380BC0363
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                //オンライン実行結果テーブルに処理開始レコードを反映する。  
                // 引数は以下の通りにセットする。  
                // [引数]
                //　@ 証券会社コード：　@プロパティ.証券会社コード  
                //　@ 銘柄タイプ：　@"株式"  
                //　@ 先物／オプション区分：　@"DEFAULT" 
                //　@ オンラインサービス区分：　@プロパティ.オンラインサービス区分
                //　@ From口座ID：　@プロパティ.From口座ID
                //　@ To口座ID：　@プロパティ.To口座ID
                l_onlineRunStatus =
                    WEB3GentradeOnlineRunStatus.setDealing(
                        this.institutionCode,
                        ProductTypeEnum.EQUITY,
                        WEB3FuturesOptionDivDef.DEFAULT,
                        this.onlineServiceDiv,
                        this.rangeFrom,
                        this.rangeTo);
            }
            catch (WEB3BaseException l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }

            //２）１）の戻り値を返す。
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }

        /**
         * (オンライン実行結果transactionCallback)<BR>
         * コンストラクタ。<BR>
         * @@param l_strInstitionCode - (証券会社コード。)<BR>
         * 証券会社コード。<BR>
         * @@param l_lngRangeFrom - (From口座ID)<BR>
         * From口座。<BR>
         * @@param l_lngRangeTo - (To口座ID)<BR>
         * To口座ID。<BR>
         * @@param l_strOnlineServiceDiv - (オンラインサービス区分)<BR>
         * オンラインサービス区分<BR>
         * @@roseuid 460380C2016F
         */
        public WEB3GentradeOnlineRunStatusTransactionCallback(
            String l_strInstitionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo,
            String l_strOnlineServiceDiv)
        {
            final String STR_METHOD_NAME = " WEB3GentradeOnlineRunStatusTransactionCallback("
                + "String, long, long, String)";
            log.entering(STR_METHOD_NAME);

            this.institutionCode = l_strInstitionCode;
            this.rangeFrom = l_lngRangeFrom;
            this.rangeTo = l_lngRangeTo;
            this.onlineServiceDiv = l_strOnlineServiceDiv;

            log.exiting(STR_METHOD_NAME);
        }
    }
}
@
