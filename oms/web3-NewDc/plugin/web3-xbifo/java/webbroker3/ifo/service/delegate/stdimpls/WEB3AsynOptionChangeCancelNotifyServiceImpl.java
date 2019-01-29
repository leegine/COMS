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
filename	WEB3AsynOptionChangeCancelNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正取消通知サービスImpl(WEB3AsynOptionChangeCancelNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 同期版履歴：
Revesion History : 2004/6/15 盧法@旭 (中訊) 新規作成
Revesion History : 2007/01/30 何文敏 (中訊) 仕様変更 No.612,616
Revesion History : 2007/04/24 孟亜南(中訊) 仕様変更モデルNo.637
**/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataRollbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyRow;
import webbroker3.ifo.define.WEB3IfoCanmodReceiptTypeDef;
import webbroker3.ifo.message.WEB3OptionsChangeCancelNotifyRequest;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyUnitService;
import webbroker3.util.WEB3LogUtility;


/**
 * （非同期対応OP訂正取消通知サービス実装クラス）。
 * @@author  : 李志強（日本中訊）
 * @@version : 1.0
 */
public class WEB3AsynOptionChangeCancelNotifyServiceImpl
    implements Runnable
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynOptionChangeCancelNotifyServiceImpl.class);

    /**
     * 株式指数オプション訂正取消通知リクエスト
     */
    private WEB3OptionsChangeCancelNotifyRequest request;


    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3AsynOptionChangeCancelNotifyServiceImpl(WEB3OptionsChangeCancelNotifyRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynOptionChangeCancelNotifyServiceImpl：run()";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;

        WEB3OptionsChangeCancelNotifyRequest l_optionsChangeCancelNotifyRequest =
            (WEB3OptionsChangeCancelNotifyRequest) request;
        try
        {
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
            }
            catch (DataFindException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //3: 注文受付トランザクションコールバックサービスを生成する
            WEB3OptionChangeCancelNotifyTransactionCallback l_callback = new WEB3OptionChangeCancelNotifyTransactionCallback();

            //set識別コードプレフィクス一覧()
            l_callback.setOrderRequestNumberPrefixGroup(
                l_optionsChangeCancelNotifyRequest.orderRequestNumberPrefixGroup);

            //5: DBトランザクション処理を実施する
            try
            {
                l_queryProcessor.doTransaction(TransactionalInterceptor.TX_CREATE_NEW, l_callback);
            }
            catch (DataCallbackException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataRollbackException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }

        //スレッド開放
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(request.threadNo.
                longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }
        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (OP訂正取消通知TransactionCallback)<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     */
    public class WEB3OptionChangeCancelNotifyTransactionCallback implements TransactionCallback
    {

        /**
         * (OP訂正取消通知TransactionCallback)
         * デフォルトコンストラクタ
         * @@return
         * webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyServiceImpl.WEB3OptionChangeCancelNotifyTransaction
         * Callback
         * @@roseuid 4084B3CF0365
         */
        public WEB3OptionChangeCancelNotifyTransactionCallback()
        {
        }

        /**
         * 識別コードプレフィクス一覧<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * (set引数の識別コードプレフィクス一覧)<BR>
         * <BR>
         * 引数の識別コードプレフィクス一覧をプロパティにセットする。<BR>
         * @@params String[] - 識別コードプレフィクス一覧<BR>
         */
        public void setOrderRequestNumberPrefixGroup(String[]
            l_orderRequestNumberPrefixGroup)
        {
            orderRequestNumberPrefixGroup = l_orderRequestNumberPrefixGroup;
        }

        /**
         * (get引数の識別コードプレフィクス一覧)<BR>
         * <BR>
         * 識別コードプレフィクス一覧を取得する。<BR>
         * @@return String[]<BR>
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return orderRequestNumberPrefixGroup;
        }

        /**
         * 注文受付処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（OP訂正取消通知）process」参照。<BR>
         * <BR>
         * １） 処理対象レコード取得<BR>
         * 訂正取消通知キューテーブルを、<BR>
         * 行ロック（select for update）にて読み込む。<BR>
         * <BR>
         * 　@[検索条件]<BR>
         * 　@先物OP訂正取消通知キューテーブル.データコード == <BR>
         * ”オプション訂正取消通知”（EI812）<BR>
         * 　@先物OP訂正取消通知キューテーブル.処理区分 == ”未処理”<BR>
         * <BR>
         * 以降の処理は、<BR>
         * 索結果の注文受付キューテーブルの各行に対して実施する。<BR>
         * <BR>
         * ２） インタセプタセット<BR>
         * OP訂正取消通知インタセプタオブジェクトを生成し、<BR>
         * 以下の通りプロパティをセットする。<BR>
         * <BR>
         * 　@インタセプタ.訂正後数量 = <BR>
         * 先物OP訂正取消通知キュー.訂正後数量<BR>
         * 　@インタセプタ.訂正後指値 = <BR>
         * 先物OP訂正取消通知キュー.訂正後指値<BR>
         * 　@インタセプタ.訂正後執行条件 = <BR>
         * 先物OP訂正取消通知キュー.訂正後執行条件<BR>
         * 　@インタセプタ.訂正取消結果コード = <BR>
         * 先物OP訂正取消通知キュー.訂正取消結果コード<BR>
         * 　@インタセプタ.訂正取消通知区分 = <BR>
         * 先物OP訂正取消通知キュー.訂正取消通知区分<BR>
         * <BR>
         * setThreadLocalPersistenceEventInterceptor()にて、<BR>
         * 注文マネージャのThreadLocalにセットする。<BR>
         * <BR>
         * ３） キューに該当する注文単位を取得<BR>
         * 注文マネージャ.get注文単位()をコールし、<BR>
         * 処理対象キューに該当する注文単位オブジェクトを取得する。<BR>
         * <BR>
         * 　@[引数]<BR>
         * 　@証券会社コード　@：<BR>
         * 先物OP訂正取消通知キューテーブル.証券会社コード<BR>
         * 　@部店コード　@：先物OP訂正取消通知キューテーブル.部店コード<BR>
         * 　@商品タイプ　@：”先物オプション”<BR>
         * 　@識別コード　@：先物OP訂正取消通知キューテーブル.識別コード<BR>
         * <BR>
         * ４） 訂正通知、取消通知を注文に更新する。<BR>
         * 処理対象キューの内容を判定し、以下の処理を実施する。<BR>
         * <BR>
         * ４－１）　@訂正通知<BR>
         * 　@（先物OP訂正取消通知キューテーブル.注文受付結果 == <BR>
         * "訂正完了"または、<BR>
         * 　@先物OP訂正取消通知キューテーブル.注文受付結果 == <BR>
         * "訂正エラー"の場合）<BR>
         * <BR>
         * 　@OP訂正取消通知UnitServiceを取得し、<BR>
         * notify訂正()メソッドをコールする。<BR>
         * <BR>
         * 　@[引数]<BR>
         * 　@注文単位：　@（get注文単位()戻り値）<BR>
         * 　@インタセプタ：　@（生成したOP訂正取消通知インタセプタオブジェクト）<BR>
         * <BR>
         * ４－２）　@取消通知<BR>
         * 　@（先物OP訂正取消通知キューテーブル.注文受付結果 == <BR>
         * "取消完了"または、<BR>
         * 　@先物OP訂正取消通知キューテーブル.注文受付結果 ==<BR>
         *  "取消エラー"の場合）<BR>
         * <BR>
         * 　@OP訂正取消通知UnitServiceを取得し、<BR>
         * notify取消()メソッドをコールする。<BR>
         * <BR>
         * 　@[引数]<BR>
         * 　@注文単位：　@（get注文単位()戻り値）<BR>
         * 　@インタセプタ：　@（生成したOP訂正取消通知インタセプタオブジェクト）<BR>
         * <BR>
         * ６） キューテーブルに処理済を更新<BR>
         * 処理対象訂正取消通知キューレコード.処理区分<BR>
         * を以下の通りセットし更新する。<BR>
         * <BR>
         * 　@[更新内容]<BR>
         * 　@”エラー”：上記処理でエラーが発生した場合<BR>
         * 　@”処理済”：以外の場合<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4084B3CF0364
         */
        public Object process()
            throws DataNetworkException,DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "Object process";
            log.entering(STR_METHOD_NAME);

            //
            WEB3OptionChangeCancelNotifyUnitService l_optionChangeCancelNotifyUnitService =
                (WEB3OptionChangeCancelNotifyUnitService) Services.getService(WEB3OptionChangeCancelNotifyUnitService.class);
            //2: 先物OP訂正取消通知キューテーブル読込
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("request_code = ? ");
            l_sbWhere.append("and status= ?");

            int l_intPrefixGroupLength = 0;
            if (orderRequestNumberPrefixGroup != null)
            {
                l_intPrefixGroupLength = orderRequestNumberPrefixGroup.length;
            }
            if (l_intPrefixGroupLength > 0)
            {
                l_sbWhere.append(" and (");
                for (int i = 0; i < l_intPrefixGroupLength; i++)
                {
                    if (i > 0)
                    {
                        l_sbWhere.append(" or ");
                    }
                    l_sbWhere.append("order_request_number like ?");
                }
                l_sbWhere.append(")");
            }

            Object[] l_objWhere = new Object[l_intPrefixGroupLength + 2];
            l_objWhere[0] = WEB3HostRequestCodeDef.OPTION_CHANGE_CANCEL_NOTICE;
            l_objWhere[1] = WEB3StatusDef.NOT_DEAL;

            for (int i = 0; i < l_intPrefixGroupLength; i++)
            {
                l_objWhere[i + 2] = orderRequestNumberPrefixGroup[i] + "%";
            }

            List l_listRecords = null;

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_listRecords =
                l_queryProcessor.doFindAllQuery
                    (HostFotypeOrderClmdNotifyRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    null,
                    l_objWhere);

            int l_intLength;
            l_intLength = l_listRecords.size();
            log.debug("Get the size of the records" + l_intLength);
            HostFotypeOrderClmdNotifyRow l_orderChangeCancelNotifyRow = null;

            for (int i = 0; i < l_intLength; i++)
            {
                try
                {
                    l_orderChangeCancelNotifyRow =
                        (HostFotypeOrderClmdNotifyRow) l_listRecords.get(i);

                    //証券会社コード
                    String l_strInstitutionCode = l_orderChangeCancelNotifyRow.getInstitutionCode();
                    log.debug("証券会社コード" + l_strInstitutionCode);
                    //部店コード
                    String l_strBranchCode = l_orderChangeCancelNotifyRow.getBranchCode();
                    log.debug("部店コード:" + l_strBranchCode);

                    //識別コード
                    String l_strOrderRequestNumber = l_orderChangeCancelNotifyRow.getOrderRequestNumber();
                    log.debug("識別コード:" + l_strOrderRequestNumber);

                    //4: 先物OP訂正取消通知更新インタセプタ生成
                    WEB3IfoChangeCancelNotifyUpdateInterceptor l_ChangeCancelNotifyUpdateInterceptor =
                        new WEB3IfoChangeCancelNotifyUpdateInterceptor();
                    //5: 訂正後数量をセットする
                    double l_dblModifieQuantity = l_orderChangeCancelNotifyRow.getModifiedQuantity();
                    l_ChangeCancelNotifyUpdateInterceptor.setChangedQuantity(l_dblModifieQuantity);

                    log.debug("訂正後数量:" + l_orderChangeCancelNotifyRow.getModifiedQuantity());
                    //6: set訂正後指値
                    double l_dblModifiedLimitPrice = l_orderChangeCancelNotifyRow.getModifiedLimitPrice();

                    l_ChangeCancelNotifyUpdateInterceptor.setChangedLimitPrice(l_dblModifiedLimitPrice);
                    log.debug("訂正後指値:" + l_orderChangeCancelNotifyRow.getModifiedLimitPrice());

                    //7: set訂正後執行条件
                    String l_strModifiedExecutionType = l_orderChangeCancelNotifyRow.getModifiedExecutionType();
                    log.debug("訂正後執行条件: " + l_strModifiedExecutionType);
                    //setChangedExecCondType
                    IfoOrderExecutionConditionType l_conditionType = null;

                    if (WEB3ExecutionConditionDef.NO_CONDITION.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.NONE;
                    }
                    else if (WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;
                    }
                    else if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
                    }
                    else if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
                    }

                    l_ChangeCancelNotifyUpdateInterceptor.setChangedExecCondType(l_conditionType);
                    log.debug("ChangedExecCondType is :" + l_conditionType);

                    //8: set訂正取消結果コード
                    l_ChangeCancelNotifyUpdateInterceptor.setChangeCancelResultCode
                        (l_orderChangeCancelNotifyRow.getModifiedResult());
                    log.debug("訂正取消結果コード:" + l_orderChangeCancelNotifyRow.getModifiedResult());

                    //9: set訂正取消通知区分
                    l_ChangeCancelNotifyUpdateInterceptor.setChangeCancelNotifyDivision
                        (l_orderChangeCancelNotifyRow.getCanmodReceiptType());
                    log.debug("訂正取消通知区分:" + l_orderChangeCancelNotifyRow.getCanmodReceiptType());

                    // set訂正後発注経路区分(String)
                    // インタセプタに訂正後発注経路区分をセットする。
                    // 訂正後発注経路区分：　@先物OP訂正取消通知キュー.訂正後発注経路区分
                    l_ChangeCancelNotifyUpdateInterceptor.setModSubmitOrderRouteDiv(
                        l_orderChangeCancelNotifyRow.getModSubmitOrderRouteDiv());

                    // set訂正後注文Rev.(String)
                    // インタセプタに訂正後注文Rev.をセットする。
                    // 訂正後注文Rev.：　@先物OP訂正取消通知キュー.訂正後注文Rev.
                    l_ChangeCancelNotifyUpdateInterceptor.setModifiedOrderRev(
                        l_orderChangeCancelNotifyRow.getModifiedOrderRev());

                    //12: 訂正取消通知区分
                    log.debug("canmod_receipt_type: " + l_orderChangeCancelNotifyRow.getCanmodReceiptType());

                    if ((WEB3IfoCanmodReceiptTypeDef.CHANGED_COMPLETE.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType()))
                        || (WEB3IfoCanmodReceiptTypeDef.CHANGED_FAILED.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType())))
                    {
                        WEB3OptionChangeCancelNotifyNotifyChangeTransactionCallback l_transactionCallback =
                            new WEB3OptionChangeCancelNotifyNotifyChangeTransactionCallback(
                            l_ChangeCancelNotifyUpdateInterceptor,
                            l_orderChangeCancelNotifyRow);

                        // doTransaction()
                        l_queryProcessor.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            l_transactionCallback);
                    }

                    //14: 取消通知
                    if ((WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType()))
                        || (WEB3IfoCanmodReceiptTypeDef.CANCELED_FAILED.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType())))
                    {
                        WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallback l_transactionCallback =
                            new WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallback(
                            l_ChangeCancelNotifyUpdateInterceptor,
                            l_orderChangeCancelNotifyRow);

                        // doTransaction()
                        l_queryProcessor.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            l_transactionCallback);
                    }
                }
                catch (Exception l_exp)
                {
                    //処理対象キューUPDATE　@(エラー時)
                    if (l_exp instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_exp;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
                        {
                            log.debug("口座ロック失敗：");
                            continue;
                        }
                    }

                    //エラーがそれ以外の場合　@(=>エラー)
                    log.debug("一件処理にてエラー発生：");
                    Map l_changesMap1 = new HashMap();
                    l_changesMap1.put("status", WEB3StatusDef.DATA_ERROR);
                    l_changesMap1.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_orderChangeCancelNotifyRow.getPrimaryKey(),l_changesMap1);
                }
            }


            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
        @
