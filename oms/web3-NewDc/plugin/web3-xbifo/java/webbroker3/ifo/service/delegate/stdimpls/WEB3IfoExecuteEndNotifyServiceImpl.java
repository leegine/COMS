head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来終了通知サービス実装クラス(WEB3IfoExecuteEndNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/22 艾興 (中訊) 新規作成
              001: 2004/07/29 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000063 execute()を修正
              002: 2004/07/29 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000068、70、71、72、73 execute()を修正
Revesion History : 2007/06/08 趙林鵬 (中訊) モデルNo.694
Revesion History : 2007/11/19 孟亞南 仕様変更モデルNo.802,813,821 ＤＢ更新仕様No.194
*/
package webbroker3.ifo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3IfoExecEndNotifyRequest;
import webbroker3.ifo.message.WEB3IfoExecEndNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyService;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataRollbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * (先物OP出来終了通知サービス実装)<BR>
 * 先物OP出来終了通知サービス実装クラス<BR>
 */
public class WEB3IfoExecuteEndNotifyServiceImpl
    implements WEB3IfoExecuteEndNotifyService
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoExecuteEndNotifyServiceImpl.class);

    /**
    * @@roseuid 40C0753000CB
    */
    public WEB3IfoExecuteEndNotifyServiceImpl()
    {

    }

    /**
     * 先物OP出来終了通知サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OPサービス）先物OP出来終了通知」参照<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057BA0E0169
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execute(WEB3IfoExecEndNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //先物OP出来終了通知リクエスト.validate()
        WEB3IfoExecEndNotifyRequest l_ifoExecEndNotifyRequest =
            (WEB3IfoExecEndNotifyRequest)l_request;
        l_ifoExecEndNotifyRequest.validate();

        //メッセージ getDefaultProcessor( )(Processors::getDefaultProcessor)
        QueryProcessor l_queryProcessor = null;

        //メッセージ doTransaction(TransactionCallback)(QueryProcessor::doTransaction)
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME);
        }
        //オンライン実行結果TransactionCallback
        WEB3OnlineRunStatusTransactionCallback l_onlineCallBack =
            new WEB3OnlineRunStatusTransactionCallback(
                l_ifoExecEndNotifyRequest.institutionCode,
                l_ifoExecEndNotifyRequest.rangeFrom,
                l_ifoExecEndNotifyRequest.rangeTo,
                l_ifoExecEndNotifyRequest.fuOpDiv,
                l_ifoExecEndNotifyRequest.execEndDiv);
        WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
        try
        {
            //オンライン実行結果TransactionCallback::オンライン実行結果TransactionCallback)
            l_onlineRunStatus =
                (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_onlineCallBack);
        }
        catch (DataCallbackException l_dce)
        {
            // set処理中()から二重起動の例外がthrowされた場合
            Object l_exception = l_dce.getDetails();
            if (l_exception instanceof WEB3BaseException)
            {
                WEB3BaseException l_baseException = (WEB3BaseException)l_exception;
                if (WEB3ErrorCatalog.BUSINESS_ERROR_01992.equals(l_baseException.getErrorInfo()))
                {
                    log.debug("指定AP起動中（二重起動エラー）。", l_baseException);

                    //何もせずにそのままreturnする。
                    WEB3IfoExecEndNotifyResponse l_response =
                        (WEB3IfoExecEndNotifyResponse)l_request.createResponse();

                    log.exiting(STR_METHOD_NAME);
                    return l_response;
                }
                throw l_baseException;
            }
        }
        catch (DataRollbackException l_drbe)
        {
            log.error(STR_METHOD_NAME, l_drbe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_drbe.getMessage(),
                l_drbe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        try
        {
            //先物OP出来終了通知TransactionCallback::先物OP出来終了通知TransactionCallback)
            WEB3IfoExecuteEndNotifyTransactionCallback l_ifoCallBack =
                new WEB3IfoExecuteEndNotifyTransactionCallback(
                    l_ifoExecEndNotifyRequest.institutionCode,
                    l_ifoExecEndNotifyRequest.rangeFrom,
                    l_ifoExecEndNotifyRequest.rangeTo,
                    l_ifoExecEndNotifyRequest.fuOpDiv,
                    l_ifoExecEndNotifyRequest.execEndDiv,
                    l_onlineRunStatus);

            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_ifoCallBack);
        }
        catch (DataCallbackException l_dce)
        {
            log.error(STR_METHOD_NAME, l_dce);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dce.getMessage(),
                l_dce);
        }
        catch (DataRollbackException l_drbe)
        {
            log.error(STR_METHOD_NAME, l_drbe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_drbe.getMessage(),
                l_drbe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        WEB3IfoExecEndNotifyResponse l_response =
            (WEB3IfoExecEndNotifyResponse)l_request.createResponse();

        return l_response;
    }


    /**
     * 先物OP出来終了通知TransactionCallback<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     */
    public class WEB3IfoExecuteEndNotifyTransactionCallback
        implements TransactionCallback
    {

        /**
         * (証券会社コード)<BR>
         */
        private String institutionCode;

        /**
         * (From口座ID)<BR>
         */
        private long rangeFrom;

        /**
         * (To口座ID)<BR>
         */
        private long rangeTo;

        /**
         * (先物／オプション区分)<BR>
         */
        private String futureOpDiv;

        /**
         * (出来終了区分)<BR>
         */
        private String execEndDiv;

        /**
         * (オンライン実行結果)<BR>
         * オンライン実行結果クラス。<BR>
         */
        private WEB3GentradeOnlineRunStatus onlineRunStatus;

        /**
         * デフォルトコンストラクタ
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード
         * @@param l_lngFangeFrom - (From口座ID)<BR>
         * From口座ID
         * @@param l_longRangeTo - (To口座ID)<BR>
         * To口座ID
         * @@param l_strFutureOpDiv - (先物／オプション区分)<BR>
         * 先物／オプション区分
         * @@param l_strExecEndDiv - (出来終了区分)<BR>
         * 出来終了区分
         * @@param l_onlineRunStatus - (オンライン実行結果)<BR>
         * オンライン実行結果クラス。
         * @@roseuid 408C92B00113
         */
        public WEB3IfoExecuteEndNotifyTransactionCallback(
            String l_strInstitutionCode,
            long l_lngFangeFrom,
            long l_longRangeTo,
            String l_strFutureOpDiv,
            String l_strExecEndDiv,
            WEB3GentradeOnlineRunStatus l_onlineRunStatus)
        {
            //証券会社コード
            this.institutionCode = l_strInstitutionCode;
            //From口座ID
            this.rangeFrom = l_lngFangeFrom;
            //To口座ID
            this.rangeTo = l_longRangeTo;
            //先物／オプション区分
            this.futureOpDiv = l_strFutureOpDiv;
            //出来終了区分
            this.execEndDiv = l_strExecEndDiv;
            //オンライン実行結果
            this.onlineRunStatus = l_onlineRunStatus;
        }

        /**
         * 出来終了通知処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（先物OP出来終了通知）process」参照。<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 408C92B00112
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            //OP注文マネージャ
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = null;
            l_optionOrderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            WEB3IfoExecuteEndNotifyUnitService l_unitservice =
                (WEB3IfoExecuteEndNotifyUnitService)Services.getService(WEB3IfoExecuteEndNotifyUnitService.class);
            Institution l_institution = null;

            try
            {
                l_institution =
                    l_finApp.getAccountManager().getInstitution(this.institutionCode);
            }
            catch (NotFoundException l_ex)
            {
                ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
                l_errorInfo.setErrorClass(l_ex.getClass().getName());
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_errorInfo);
            }

            OrderUnit[] l_orderUnits = null;
            try
            {
                //get当日有効注文単位
                l_orderUnits = l_optionOrderManagerImpl.getTodayOpenOrderUnits(
                    this.futureOpDiv,
                    l_institution,
                    this.rangeFrom,
                    this.rangeTo);
            }
            catch (WEB3BaseException l_exp)
            {
                ErrorInfo l_errorInfo = l_exp.getErrorInfo();
                l_errorInfo.setErrorClass(l_exp.getClass().getName());
                throw new DataCallbackException(
                    l_exp.getErrorMessage(),
                    l_errorInfo);
            }

            //get当日有効注文単位() != nullの場合、取得した注文単位ごとのLoop
            boolean l_blnIsCarryOverAllAccountsSuccess = true;

            if (l_orderUnits != null)
            {
                int l_intOrderUnitLength = l_orderUnits.length;
                for (int i = 0; i < l_intOrderUnitLength; i++)
                {
                    try
                    {
                        l_unitservice.notifyExecuteEnd(l_orderUnits[i], this.execEndDiv);
                    }
                    catch (Exception l_ex)
                    {
                        log.debug(STR_METHOD_NAME, l_ex);
                        l_blnIsCarryOverAllAccountsSuccess = false;
                    }
                }
            }

            //update実行ステータス区分
            //実行ステータス区分：
            //繰越処理が全顧客で正常終了した場合は"処理済"
            //1顧客でもシステムエラーがスローされた場合は"エラー"をセット。
            String l_strRunStatusDiv = null;
            if (l_blnIsCarryOverAllAccountsSuccess)
            {
                //繰越処理が全顧客で正常終了した場合は"処理済"
                l_strRunStatusDiv = WEB3RunStatusDivDef.DEALED;
            }
            else
            {
                //1顧客でもシステムエラーがスローされた場合は"エラー"をセット。
                l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
            }

            try
            {
                //update実行ステータス区分
                this.onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);
            }
            catch (WEB3BaseException l_exp)
            {
                ErrorInfo l_errorInfo = l_exp.getErrorInfo();
                l_errorInfo.setErrorClass(l_exp.getClass().getName());
                throw new DataCallbackException(
                    l_exp.getErrorMessage(),
                    l_errorInfo);
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * オンライン実行結果TransactionCallbackクラス。<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     */
    public class WEB3OnlineRunStatusTransactionCallback
        implements TransactionCallback
    {
        /**
         * (証券会社コード)<BR>
         */
        private String institutionCode;

        /**
         * (From口座ID)<BR>
         */
        private long rangeFrom;

        /**
         * (To口座ID)<BR>
         */
        private long rangeTo;

        /**
         * (先物／オプション区分)<BR>
         */
        private String futureOpDiv;

        /**
         * (出来終了区分)<BR>
         */
        private String execEndDiv;

        /**
         * デフォルトコンストラクタ
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード
         * @@param l_lngFangeFrom - (From口座ID)<BR>
         * From口座ID
         * @@param l_longRangeTo - (To口座ID)<BR>
         * To口座ID
         * @@param l_strfutureOpDiv - (先物／オプション区分)<BR>
         * 先物／オプション区分
         * @@param l_strExecEndDiv - (出来終了区分)<BR>
         * 出来終了区分
         */
        public WEB3OnlineRunStatusTransactionCallback(
            String l_strInstitutionCode,
            long l_lngFangeFrom,
            long l_longRangeTo,
            String l_strfutureOpDiv,
            String l_strExecEndDiv)
        {
            //証券会社コード
            this.institutionCode = l_strInstitutionCode;
            //From口座ID
            this.rangeFrom = l_lngFangeFrom;
            //To口座ID
            this.rangeTo = l_longRangeTo;
            //先物／オプション区分
            this.futureOpDiv = l_strfutureOpDiv;
            //出来終了区分
            this.execEndDiv = l_strExecEndDiv;
        }
        /**
         * トランザクション処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（先物OPサービス）先物OP出来終了通知」参照。<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //オンラインサービス区分
            String l_strOnlineServiceDiv = null;
            // オンライン実行結果.set処理中()メソッドをコールする。
            // 証券会社コード：　@this.証券会社コード
            // 銘柄タイプ：　@"先物オプション"
            // 先物／オプション区分：　@this.先物／オプション区分
            // オンラインサービス区分：　@以下のとおりに設定する。
            //  this.出来終了区分 == "1：夕場前出来終了"の場合、"9：夕場前出来終了通知"
            //  this.出来終了区分 == "0：（最終）出来終了"の場合、"1：出来終了通知"
            // From口座ID：　@this.From口座ID
            // To口座ID：　@this.To口座ID
            if (WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END.equals(this.execEndDiv))
            {
                //"9：夕場前出来終了通知"
                l_strOnlineServiceDiv = WEB3OnlineServiceDiv.BEFORE_EVENING_SESSION_ORDER_EXEC_END;
            }
            else if (WEB3OrderexecutionEndTypeDef.DEFAULT.equals(this.execEndDiv))
            {
                //"1：出来終了通知"
                l_strOnlineServiceDiv = WEB3OnlineServiceDiv.ORDER_EXEC_END;
            }

            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
                    this.institutionCode,
                    ProductTypeEnum.IFO,
                    this.futureOpDiv,
                    l_strOnlineServiceDiv,
                    this.rangeFrom,
                    this.rangeTo);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }
            //set処理中()の戻り値を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }
}
@
