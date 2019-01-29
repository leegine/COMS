head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文繰越メインサービスImpl(WEB3IfoOrderCarryOverMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/22 趙林鵬(中訊) 新規作成 モデルNo.669
Revision History : 2007/07/11 趙林鵬(中訊) モデルNo.774
Revision History : 2008/04/11 趙林鵬 (中訊) モデルNo.277,278
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3CarryoverProcessTypeDef;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.message.WEB3IfoOrderCarryOverMainRequest;
import webbroker3.triggerorder.message.WEB3IfoOrderCarryOverMainResponse;
import webbroker3.triggerorder.service.delegate.WEB3FuturesOrderCarryOverService;
import webbroker3.triggerorder.service.delegate.WEB3IfoOrderCarryOverMainService;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP注文繰越メインサービスImpl)<BR>
 * （抽象クラス）<BR>
 * 先物OP注文繰越メインサービス実装クラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public abstract class WEB3IfoOrderCarryOverMainServiceImpl
    implements WEB3IfoOrderCarryOverMainService
{
    /**
     * ログ出力ユーティリティ。 <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoOrderCarryOverMainServiceImpl.class);

    /**
     * 先物OP注文繰越メインサービスImpl<BR>
     */
    public WEB3IfoOrderCarryOverMainServiceImpl()
    {

    }

    /**
     * 先物OP注文繰越共通処理を実施する。<BR>
     * <BR>
     * リクエストデータを先物OP注文繰越メインリクエストにキャストし、<BR>
     * this.exec先物OP注文繰越()をコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
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

        WEB3BackResponse l_response = null;

        //リクエストデータを先物OP注文繰越メインリクエストにキャストし、
        //this.exec先物OP注文繰越()をコールする。
        if (l_request instanceof WEB3IfoOrderCarryOverMainRequest)
        {
            l_response =
                this.execIfoOrderCarryOver(
                    (WEB3IfoOrderCarryOverMainRequest)l_request);
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
     * (create翌日注文)<BR>
     * 翌日注文を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP共通サービス）注文繰越」内の<BR>
     * create翌日注文()部分を参照<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strFutureOptionDiv - (先物／オプション区分)<BR>
     * 先物／オプション区分<BR>
     * @@param l_strCarryoverProcessType - (注文繰越処理区分)<BR>
     * 注文繰越処理区分<BR>
     * @@throws WEB3BaseException
     */
    public void createNextOrder(
        MainAccount l_mainAccount,
        String l_strFutureOptionDiv,
        String l_strCarryoverProcessType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createNextOrder(MainAccount, String, String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //lock口座
        //証券会社コード：　@顧客[index].証券会社コード
        //部店コード：　@顧客[index].部店コード
        //口座コード：　@顧客[index].口座コード
        l_accountManager.lockAccount(
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_mainAccount.getAccountCode());

        //get当日有効注文単位
        //先物／オプション区分：　@引数の「先物／オプション区分」
        //顧客：　@顧客[index]
        //注文繰越処理区分：　@引数の「注文繰越処理区分」
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

        OrderUnit[] l_orderUnits = l_orderManager.getTodayOpenOrderUnits(
            l_strFutureOptionDiv,
            l_mainAccount,
            l_strCarryoverProcessType);

        // HashMap( )
        HashMap l_map = new HashMap();

        WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);
        //get当日有効注文単位() != nullの場合、取得した注文単位ごとのLoop
        if (l_orderUnits != null && l_orderUnits.length != 0)
        {
            int l_intOrderUnitCnt = l_orderUnits.length;
            for (int i = 0; i < l_intOrderUnitCnt; i++)
            {
                //get有効予約注文単位一覧(long)
                //親注文の注文ID：　@処理対象の注文単位.注文ID
                List l_lisOpenReserveIfoOrderUnits =
                    l_ifoOrderUpdateService.getOpenReserveIfoOrderUnits(l_orderUnits[i].getOrderId());

                //put
                l_map.put(new Long(l_orderUnits[i].getOrderId()), l_lisOpenReserveIfoOrderUnits);

                // expire繰越元注文(OrderUnit)
                //注文単位：　@処理対象の注文単位
                this.expireCarryOverOriginOrder(l_orderUnits[i]);
            }
        }

        //余力再計算(顧客)
        //顧客：　@処理対象の顧客
        this.reCalcTradingPower(l_mainAccount);

        //get当日有効注文単位() != nullの場合、取得した注文単位ごとのLoop
        if (l_orderUnits != null && l_orderUnits.length != 0)
        {
            int l_intOrderUnitCnt = l_orderUnits.length;
            for (int i = 0; i < l_intOrderUnitCnt; i++)
            {
                //注文単位を再取得する。
                OrderUnit l_expiredOrderUnit = null;
                try
                {
                    l_expiredOrderUnit =
                        l_orderManager.getOrderUnit(l_orderUnits[i].getOrderUnitId());
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

                //get(arg0 : Object)
                List l_lisRsvIfoOrderUnits = (List)l_map.get(new Long(l_orderUnits[i].getOrderId()));

                //submit翌日注文(IfoOrderUnit)
                try
                {
                    this.submitNextOrder((IfoOrderUnit)l_expiredOrderUnit, l_lisRsvIfoOrderUnits);
                }
                catch (WEB3BusinessLayerException l_web3ble)
                {
                    //update繰越元注文(OrderUnit, String)
                    //[引数の設定]
                    //注文単位：　@注文単位[index]
                    //注文エラー理由コード：その他
                    this.updateCarryOverOriginOrder(l_expiredOrderUnit,
                        WEB3ErrorReasonCodeDef.OTHRE_ERROR);
                }
                catch (WEB3SystemLayerException l_web3sle)
                {
                    ErrorInfo l_errInfo = l_web3sle.getErrorInfo();
                    if (WEB3ErrorCatalog.BUSINESS_ERROR_02216.equals(l_errInfo)
                        || WEB3ErrorCatalog.BUSINESS_ERROR_02187.equals(l_errInfo))
                    {
                        //update繰越元注文(OrderUnit, String)
                        //[引数の設定]
                        //注文単位：　@注文単位[index]
                        //注文エラー理由コード：その他
                        this.updateCarryOverOriginOrder(l_expiredOrderUnit,
                            WEB3ErrorReasonCodeDef.OTHRE_ERROR);
                    }
                    else
                    {
                        throw l_web3sle;
                    }
                }
                catch (WEB3BaseRuntimeException l_web3bre)
                {
                    ErrorInfo l_errInfo = l_web3bre.getErrorInfo();
                    if (WEB3ErrorCatalog.BUSINESS_ERROR_02216.equals(l_errInfo)
                        || WEB3ErrorCatalog.BUSINESS_ERROR_02187.equals(l_errInfo))
                    {
                        //update繰越元注文(OrderUnit, String)
                        //[引数の設定]
                        //注文単位：　@注文単位[index]
                        //注文エラー理由コード：その他
                        this.updateCarryOverOriginOrder(l_expiredOrderUnit,
                            WEB3ErrorReasonCodeDef.OTHRE_ERROR);
                    }
                    else
                    {
                        throw l_web3bre;
                    }
                }
            }
        }
    }

    /**
     * (exec先物OP注文繰越)<BR>
     * 先物OP注文繰越共通処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP共通サービス）注文繰越」参照<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 先物OP注文繰越メインリクエストオブジェクト<BR>
     * @@return WEB3IfoOrderCarryOverMainResponse
     * @@throws WEB3BaseException
     */
    protected WEB3IfoOrderCarryOverMainResponse execIfoOrderCarryOver(
        WEB3IfoOrderCarryOverMainRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execIfoOrderCarryOver(WEB3IfoOrderCarryOverMainRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // get先物／オプション区分( )
        String l_strFutureOptionDiv = this.getFutureOptionDiv();

        //get注文繰越処理区分( )
        String l_strCarryoverProcessType = this.getCarryoverProcessType();

        //getオンラインサービス区分(String, String)
        //証券会社コード：　@リクエスト.証券会社コード
        //注文繰越処理区分：　@get注文繰越処理区分()の戻り値
        String l_strOnlineServiceDiv = this.getOnlineServiceDiv(
            l_request.institutionCode,
            l_strCarryoverProcessType);

        //getオンラインサービス区分()の戻り値がnullの場合
        if (l_strOnlineServiceDiv == null)
        {
            //何もせずにそのままreturnする。
            //※出来終了通知が処理完了していない場合
            WEB3IfoOrderCarryOverMainResponse l_response =
                (WEB3IfoOrderCarryOverMainResponse)l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //先物OPオンライン実行結果TransactionCallback(String, long, long, String, String)
        //証券会社コード：　@リクエスト.証券会社コード
        //From口座ID：　@リクエスト.From口座ID
        //To口座ID：　@リクエスト.To口座ID
        //先物／オプション区分：　@get先物／オプション区分()の戻り値
        //オンラインサービス区分：　@getオンラインサービス区分()の戻り値
        WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
        try
        {
            // getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //先物OPオンライン実行結果TransactionCallback(String, long, long, String, String)
            //証券会社コード：　@リクエスト.証券会社コード
            //From口座ID：　@リクエスト.From口座ID
            //To口座ID：　@リクエスト.To口座ID
            //先物／オプション区分：　@get先物／オプション区分()の戻り値
            //オンラインサービス区分：　@getオンラインサービス区分()の戻り値
            WEB3IfoOnlineRunStatusTransactionCallback l_ifoOnlineRunStatusTransactionCallback =
                new WEB3IfoOnlineRunStatusTransactionCallback(
                    l_request.institutionCode,
                    l_request.rangeFrom,
                    l_request.rangeTo,
                    l_strFutureOptionDiv,
                    l_strOnlineServiceDiv);

            //doTransaction(TX_CREATE_NEW : int, オンライン実行結果TransactionCallback : TransactionCallback)
            l_onlineRunStatus = (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_ifoOnlineRunStatusTransactionCallback);
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
                    log.debug("指定AP起動中（二重起動エラー）。", l_baseException);

                    //何もせずにそのままreturnする。
                    WEB3IfoOrderCarryOverMainResponse l_response =
                        (WEB3IfoOrderCarryOverMainResponse)l_request.createResponse();

                    log.exiting(STR_METHOD_NAME);
                    return l_response;
                }
                throw l_baseException;
            }
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

        //get当日有効注文顧客一覧(String, 証券会社, long, long, String)
        //先物／オプション区分：　@get先物／オプション区分()の戻り値
        //証券会社：　@リクエストデータ.証券会社コードに該当する証券会社
        //From口座ID：　@リクエストデータ.From口座ID
        //To口座ID：　@リクエストデータ.To口座ID
        //注文繰越処理区分：　@get注文繰越処理区分()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

        Institution l_institution = null;
        try
        {
            l_institution =
                l_finApp.getAccountManager().getInstitution(l_request.institutionCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        MainAccount[] l_mainAccounts = l_orderManager.getTodayOpenOrderMainAcounts(
            l_strFutureOptionDiv,
            l_institution,
            l_request.rangeFrom,
            l_request.rangeTo,
            l_strCarryoverProcessType);

        boolean l_blnIsCarryOverAllAccountsSuccess = true;

        WEB3IfoOrderCarryOverMainService l_orderCarryOverMainService = null;

        if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv))
        {
            l_orderCarryOverMainService =
                (WEB3OptionOrderCarryOverService)Services.getService(WEB3OptionOrderCarryOverService.class);
        }
        else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv))
        {
            l_orderCarryOverMainService =
                (WEB3FuturesOrderCarryOverService)Services.getService(WEB3FuturesOrderCarryOverService.class); 
        }

        //get当日有効注文顧客一覧()の戻り値 != nullの場合、取得した顧客ごとのLoop
        if (l_mainAccounts != null)
        {
            int l_intMainAccountCnt = l_mainAccounts.length;
            for (int i = 0; i < l_intMainAccountCnt; i++)
            {
                //create翌日注文(MainAccount)
                //顧客：　@処理対象の顧客
                try
                {
                    l_orderCarryOverMainService.createNextOrder(
                        l_mainAccounts[i],
                        l_strFutureOptionDiv,
                        l_strCarryoverProcessType);
                }
                catch (Exception l_ex)
                {
                    log.debug("顧客単位繰越エラー発生 :口座ID[" + l_mainAccounts[i].getAccountId() + "]");
                    log.debug(STR_METHOD_NAME, l_ex);
                    l_blnIsCarryOverAllAccountsSuccess = false;
                }
            }
        }

        //update実行ステータス区分
        //実行ステータス区分：
        //繰越処理が全顧客で正常終了した場合は"処理済"
        //1顧客でもシステムエラーがスローされた場合は"エラー"をセット。
        String l_strRunStatus = null;
        if (l_blnIsCarryOverAllAccountsSuccess)
        {
            l_strRunStatus = WEB3RunStatusDivDef.DEALED;
        }
        else
        {
            l_strRunStatus = WEB3RunStatusDivDef.ERROR;
        }
        l_onlineRunStatus.updateRunStatusDiv(l_strRunStatus);

        WEB3IfoOrderCarryOverMainResponse l_response =
            (WEB3IfoOrderCarryOverMainResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get先物／オプション区分)<BR>
     * （抽象メソッド）<BR>
     * 先物／オプション区分を取得する。<BR>
     * <BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected abstract String getFutureOptionDiv();

    /**
     * (expire繰越元注文)<BR>
     * （抽象メソッド）<BR>
     * 繰越元注文の失効処理を行う。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * （繰越元）注文単位オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    protected abstract void expireCarryOverOriginOrder(OrderUnit l_orderUnit)
        throws WEB3BaseException;

    /**
     * (余力再計算)<BR>
     * （抽象メソッド）<BR>
     * 余力再計算を行う。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    protected abstract void reCalcTradingPower(MainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (submit翌日注文)<BR>
     * （抽象メソッド）<BR>
     * 翌日注文を登録する。<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_lisRsvIfoOrderUnits - (予約注文単位一覧)<BR>
     * 予約注文単位一覧<BR>
     * @@throws WEB3BaseException
     */
    protected abstract void submitNextOrder(IfoOrderUnit l_ifoOrderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException;

    /**
     * (update繰越元注文)<BR>
     * （抽象メソッド）<BR>
     * 繰越元注文の注文エラー理由コードを更新する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * （繰越元）注文単位オブジェクト<BR>
     * @@param l_strOrderErrorReasonCode - (注文エラー理由コード)<BR>
     * (注文エラー理由コード)<BR>
     * <BR>
     * DBレイアウト<BR>
     * 注文単位テーブル仕様.xls<BR>
     * 「（注文単位テーブル補足）注文エラー理由コード」シート参照。<BR>
     * @@throws WEB3BaseException
     */
    protected abstract void updateCarryOverOriginOrder(
        OrderUnit l_orderUnit, String l_strOrderErrorReasonCode)
            throws WEB3BaseException;

    /**
     * (getオンラインサービス区分)<BR>
     * 出来終了通知の結果を取得し、 <BR>
     * オンライン実行結果テーブルを更新するための <BR>
     * オンラインサービス区分値を返却する。 <BR>
     * <BR>
     * １）　@出来終了テーブルの検索 <BR>
     * 　@以下の条件で出来終了テーブルを検索する。 <BR>
     * <BR>
     * 　@　@　@＜検索条件＞ <BR>
     * 　@　@証券会社コード：　@引数.証券会社コード <BR>
     * 　@　@銘柄タイプ：　@"先物オプション" <BR>
     * 　@　@先物／オプション区分：　@this.get先物／オプション区分()の戻り値 <BR>
     * 　@　@出来終了区分： <BR>
     * 　@　@　@[引数.注文繰越処理区分＝"注文繰越"の場合] <BR>
     * 　@　@　@　@"DEFAULT" <BR>
     * 　@　@　@[引数.注文繰越処理区分＝"夕場前注文繰越"の場合] <BR>
     * 　@　@　@　@"夕場前出来終了" <BR>
     * <BR>
     * ２）　@返却値の判定 <BR>
     * 　@２－１）　@１）で取得したレコード数＝0件の場合 <BR>
     * 　@　@出来終了通知が未完了と判定し、nullを返却する。 <BR>
     * <BR>
     * 　@２－２）　@１）で取得したレコード数＝１件の場合 <BR>
     * 　@　@引数.注文繰越処理区分に該当するオンラインサービス区分を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strCarryoverProcessType - (注文繰越処理区分)<BR>
     * 注文繰越処理区分<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getOnlineServiceDiv(String l_strInstitutionCode, String l_strCarryoverProcessType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOnlineServiceDiv(String, String)";
        log.entering(STR_METHOD_NAME);

        //出来終了区分：
        //[引数.注文繰越処理区分＝"注文繰越"の場合]
        //"DEFAULT"
        //[引数.注文繰越処理区分＝"夕場前注文繰越"の場合]
        //"夕場前出来終了"
        String l_strOrderExecutionType =
            WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END;

        if (WEB3CarryoverProcessTypeDef.ORDER_CARRY_OVER.equals(l_strCarryoverProcessType))
        {
            l_strOrderExecutionType = WEB3OrderexecutionEndTypeDef.DEFAULT;
        }

        //　@＜検索条件＞
        // 証券会社コード：　@引数.証券会社コード
        // 銘柄タイプ：　@"先物オプション"
        // 先物／オプション区分：　@this.get先物／オプション区分()の戻り値
        // 出来終了区分：
        StringBuffer l_sbQueryCond = new StringBuffer();
        l_sbQueryCond.append("institution_code = ? ");
        l_sbQueryCond.append(" and product_type = ? ");
        l_sbQueryCond.append(" and future_option_div = ? ");
        l_sbQueryCond.append(" and orderexecution_end_type = ?");

        Object[] l_objectWheres =
        {
            l_strInstitutionCode,
            ProductTypeEnum.IFO,
            this.getFutureOptionDiv(),
            l_strOrderExecutionType
        };

        List l_lisResultList = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisResultList = l_queryProcessor.doFindAllQuery(
                OrderexecutionEndRow.TYPE,
                l_sbQueryCond.toString(),
                l_objectWheres);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２－１）　@１）で取得したレコード数＝0件の場合
        //出来終了通知が未完了と判定し、nullを返却する。
        if (l_lisResultList == null || l_lisResultList.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //取得したレコード数＝１件の場合
        //引数.注文繰越処理区分に該当するオンラインサービス区分を返却する。
        //注文繰越処理区分＝"注文繰越"の場合：　@2：注文繰越
        //注文繰越処理区分＝"夕場前注文繰越"の場合：　@8：夕場前注文繰越
        if (WEB3CarryoverProcessTypeDef.ORDER_CARRY_OVER.equals(l_strCarryoverProcessType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3OnlineServiceDiv.ORDER_CARRY_OVER;
        }

        log.exiting(STR_METHOD_NAME);
        return WEB3OnlineServiceDiv.BEFORE_EVENING_SESSION_ORDER_CARRY_OVER;
    }

    /**
     * (get注文繰越処理区分)<BR>
     * 注文繰越処理区分を取得する。<BR>
     * <BR>
     * １）　@時間帯の判定<BR>
     * 　@取引時間管理.is夕場時間帯()をcallする。<BR>
     * <BR>
     * ２）　@返却値の判定<BR>
     * 　@１）の戻り値＝trueの場合、"夕場前注文繰越"を返却する。<BR>
     * 　@以外の場合、"注文繰越"を返却する。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getCarryoverProcessType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCarryoverProcessType()";
        log.entering(STR_METHOD_NAME);

        //時間帯の判定
        //取引時間管理.is夕場時間帯()をcallする。
        boolean l_blnIsEveningSessionTimeZone =
            WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone();

        //１）の戻り値＝trueの場合、"夕場前注文繰越"を返却する。
        if (l_blnIsEveningSessionTimeZone)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3CarryoverProcessTypeDef.BEFORE_EVENING_SESSION_ORDER_CARRY_OVER;
        }

        //以外の場合、"注文繰越"を返却する。
        log.exiting(STR_METHOD_NAME);
        return WEB3CarryoverProcessTypeDef.ORDER_CARRY_OVER;
    }

    /**
     * 先物OPオンライン実行結果TransactionCallback<BR>
     * 先物OPオンライン実行結果TransactionCallbackクラス<BR>
     */
    public class WEB3IfoOnlineRunStatusTransactionCallback implements TransactionCallback
    {
        /**
         * (証券会社コード)<BR>
         * 繰越対象の証券会社コード。<BR>
         */
        public String institutionCode;

        /**
         * (From口座ID)<BR>
         * From口座ID<BR>
         */
        public long rangeFrom;

        /**
         * (To口座ID)<BR>
         * To口座ID<BR>
         */
        public long rangeTo;

        /**
         * (先物／オプション区分)<BR>
         * 先物／オプション区分<BR>
         */
        public String futureOptionDiv;

        /**
         * (オンラインサービス区分)<BR>
         * オンラインサービス区分<BR>
         */
        public String onlineServiceDiv;

        /**
         * (先物OPオンライン実行結果TransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * @@param l_strInstitutionCode -(証券会社コード)<BR>
         * 証券会社コード<BR>
         * @@param l_lngRangeFrom - (From口座ID)<BR>
         * From口座ID<BR>
         * @@param l_lngRangeTo - (To口座ID)<BR>
         * To口座ID<BR>
         * @@param l_strFutureOptionDiv - (先物／オプション区分)<BR>
         * 先物／オプション区分<BR>
         * @@param l_strOnlineServiceDiv - (オンラインサービス区分)<BR>
         * オンラインサービス区分<BR>
         */
        public WEB3IfoOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo,
            String l_strFutureOptionDiv,
            String l_strOnlineServiceDiv)
        {
            this.institutionCode = l_strInstitutionCode;
            this.rangeFrom = l_lngRangeFrom;
            this.rangeTo = l_lngRangeTo;
            this.futureOptionDiv = l_strFutureOptionDiv;
            this.onlineServiceDiv = l_strOnlineServiceDiv;
        }

        /**
         * オンライン実行結果テーブルへ"処理中"設定を行う。<BR>
         * <BR>
         * １）　@オンライン実行結果.set処理中()メソッドをコールする。<BR>
         * 　@[set処理中()に指定する引数] <BR>
         * 　@　@証券会社コード：　@this.証券会社コード<BR>
         * 　@　@銘柄タイプ：　@"先物オプション" <BR>
         * 　@　@先物／オプション区分：　@this.先物／オプション区分<BR>
         * 　@　@オンラインサービス区分：　@this.オンラインサービス区分<BR>
         * 　@　@From口座ID：　@this.From口座ID<BR>
         * 　@　@To口座ID：　@this.To口座ID<BR>
         * <BR>
         * ２）　@set処理中()の戻り値を返却する。<BR>
         * @@return Object
         * @@throws DataCallbackException；
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //１）　@オンライン実行結果.set処理中()メソッドをコールする。
            // 証券会社コード：　@this.証券会社コード
            // 銘柄タイプ：　@"先物オプション"
            // 先物／オプション区分：　@this.先物／オプション区分
            // オンラインサービス区分：　@this.オンラインサービス区分
            // From口座ID：　@this.From口座ID
            // To口座ID：　@this.To口座ID
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
                    this.institutionCode,
                    ProductTypeEnum.IFO,
                    this.futureOptionDiv,
                    this.onlineServiceDiv,
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
            //２）　@set処理中()の戻り値を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }
}
@
