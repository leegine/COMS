head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正取消受付サービスImpl(WEB3EquityChangeCancelAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 周玲玲(中訊) 新規作成
                   2004/09/14 李海波(中訊) 修正
                   2004/09/22 盧法@旭(中訊) 修正
                   2004/12/13 岡村和明(SRA) 残案件対応 Ｎｏ.３４２
                   2004/12/21 岡村和明(SRA) JavaDoc修正
                   2005/01/05 岡村和明(SRA) 口座ロック修正対応
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2005/03/09 劉（FLJ）キューテーブルによる下り処理のトランザクション制御変更、
 　@　@　@　@　@　@　@　@　@　@非同期処理部分WEB3AsynEquityChangeCancelAcceptServiceImplへ移す（新規クラス）
*/
package webbroker3.equity.service.delegate.stdimpls;


import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.equity.message.WEB3EquityChangeCancelAcceptRequest;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelAcceptService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;


/**
 *  (株式訂正取消受付サービスImpl）。<BR>
 * <BR>
 * 株式訂正取消受付サービス実装クラス
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3EquityChangeCancelAcceptServiceImpl
    implements WEB3EquityChangeCancelAcceptService
{
    /**
     * <p>（ログ出力ユーティリティ)。</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityChangeCancelAcceptServiceImpl.class);

    /**
     * <p>（execute）。</p>
     * <p>注文訂正取消受付処理を実施する。<br>
     * <br>
     * シーケンス図「（株式訂正取消受付サービス）訂正取消受付」参照。</p>
     * @@param l_request リクエストデータオブジェクト
     * @@return WEB3EquityChangeCancelAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 4137CF3500E0
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if(l_request == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityChangeCancelAcceptRequest l_eqOrderAcceptRequest =
            (WEB3EquityChangeCancelAcceptRequest) l_request;

        //1.1 validate()
        l_eqOrderAcceptRequest.validate();

        // 1.2. スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_eqOrderAcceptRequest.threadNo.longValue());
        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynEquityChangeCancelAcceptServiceImpl(
            l_eqOrderAcceptRequest));

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }

//>>>>>>以降処理WEB3AsynEquityChangeCancelAcceptServiceImplへ移す。2005/03/09  劉（FLJ）

//        // 株式訂正取消受付処理を行う
//        try
//        {
//            //1.2 getDefaultProcessor()
//            QueryProcessor l_qp = Processors.getDefaultProcessor();
//            //1.3 株式訂正取消受付TransactionCallback()
//            WEB3EquityChangeCancelAcceptServiceTransactionCallback l_callBack = new WEB3EquityChangeCancelAcceptServiceTransactionCallback();
//            //1.4 set識別コードプレフィクス一覧(識別コードプレフィクス一覧 : String[])
//            l_callBack.setOrderRequestNumberPrefixGroup(l_eqOrderAcceptRequest.orderRequestNumberPrefixGroup);
//            //1.5 doTransaction((トランザクション属性（=TX_CREATE_NEW) : int, arg1 : TransactionCallback)
//            WEB3BaseException l_baseExp = (WEB3BaseException) l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callBack);
//            if (l_baseExp != null)
//            {
//                log.exiting(STR_METHOD_NAME);
//                throw l_baseExp;
//            }
//        }
//        catch (DataException l_exp)
//        {
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, getClass().getName() + STR_METHOD_NAME);
//        }
//
//        WEB3EquityChangeCancelAcceptResponse l_eqOrderAcceptResponse =
//            (WEB3EquityChangeCancelAcceptResponse) l_eqOrderAcceptRequest.createResponse();
//        log.exiting(STR_METHOD_NAME);
//        return l_eqOrderAcceptResponse;
//    }
//
//    /**
//     * <p>（株式訂正取消受付TransactionCallback）。</p>
//     * <p>株式訂正取消受付TransactionCallbackクラス。</p>
//     */
//    private class WEB3EquityChangeCancelAcceptServiceTransactionCallback
//        implements TransactionCallback
//    {
//
//        /**
//         * <p>（識別コードプレフィクス一覧）。</p>
//         * <p>識別コードプレフィクス一覧。</p>
//         */
//        private String[] orderRequestNumberPrefixGroup;
//
//        /**
//         * <p>（株式訂正取消受付TransactionCallback ）。</p>
//         * <p>コンストラクタ。</p>
//         */
//        public WEB3EquityChangeCancelAcceptServiceTransactionCallback()
//        {
//        }
//
//        /**
//         * <p>（process）。</p>
//         * <p>トランザクション処理を実施する。<br>
//         * <br>
//         * シーケンス図<br>
//         * 「（株式訂正取消受付サービス）process」参照。</p>
//         * @@return WEB3BaseException（内部処理にてWEB3BaseException例外が発生した場合）<BR>
//         * @@throws DataCallbackException
//         * @@throws DataNetworkException
//         * @@throws DataQueryException
//         * @@roseuid 4137CF3501A8
//         */
//        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
//        {
//            final String STR_METHOD_NAME = "process()";
//            log.entering(STR_METHOD_NAME);
//
//            // 1.1 get識別コードプレフィクス一覧()
//            log.debug("1.1 get識別コードプレフィクス一覧");
//            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();
//
//            // 1.2 [検索条件]注文受付キューテーブル.データコード == "注文訂正取消"（AI80B）
//            // 注文受付キューテーブル.処理区分 == "未処理"
//            log.debug("1.2 注文受付キューテーブル読み込み");
//
//            StringBuffer l_sbWhere = new StringBuffer();
//            l_sbWhere.append(" request_code = ? ");
//            l_sbWhere.append(" and status = ? ");
//            int l_intRequestNumberPrefixGroupCnt = l_orderRequestNumberPrefixGroup.length;
//            l_sbWhere.append(" and (");
//            for(int i = 0 ; i < l_intRequestNumberPrefixGroupCnt ; i++)
//            {
//                if(i == 0)
//                {
//                    l_sbWhere.append(" substr(order_request_number , 1 , 2) = ?");
//                } else
//                {
//                    l_sbWhere.append(" or substr(order_request_number , 1 , 2) = ?");
//                }
//            }
//            l_sbWhere.append(" )");
//            Object[] l_objEquityOrderUnitWhere = new Object[l_intRequestNumberPrefixGroupCnt + 2];
//            l_objEquityOrderUnitWhere[0] = WEB3HostRequestCodeDef.EQUITY_CHANGE_CANCEL;
//            l_objEquityOrderUnitWhere[1] = WEB3StatusDef.NOT_DEAL;
//            for(int i = 0 ; i < l_intRequestNumberPrefixGroupCnt ; i++)
//            {
//                l_objEquityOrderUnitWhere[i + 2] = l_orderRequestNumberPrefixGroup[i];
//                log.debug("識別コードプレフィクス一覧：" + l_orderRequestNumberPrefixGroup[i]);
//            }
//
//            QueryProcessor l_qp = Processors.getDefaultProcessor();
//            Map l_mapChanges = new HashMap();
//
//            // 注文受付キューテーブルの読み込み実行
//            List l_lisHostEqtypeOrderAccept =
//                l_qp.doFindAllQuery(
//                    HostEqtypeOrderAcceptRow.TYPE,
//                    l_sbWhere.toString(),
//                    null,
//                    "FOR UPDATE",
//                    l_objEquityOrderUnitWhere);
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            TradingModule l_tm =
//            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
//            //WEB3EquityAccountManager l_tm
//            WEB3GentradeAccountManager l_accMa = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
//            WEB3EquityChangeCancelAcceptUnitService l_changeCancelService =
//                (WEB3EquityChangeCancelAcceptUnitService) Services.getService(WEB3EquityChangeCancelAcceptUnitService.class);
//            if(l_lisHostEqtypeOrderAccept != null)
//            {
//                Iterator l_iterator = l_lisHostEqtypeOrderAccept.iterator();
//                HostEqtypeOrderAcceptParams l_params;
//
//                // 1.3 * 取得したキューテーブルのレコード数分、Loop処理
//                log.debug("1.3 取得したキューテーブルのレコード数分、Loop処理");
//                while (l_iterator.hasNext())
//                {
//                    l_params = new HostEqtypeOrderAcceptParams((HostEqtypeOrderAcceptRow)l_iterator.next());
//                    try
//                    {
//                        // 1.3.1 notify訂正取消受付(株式注文受付キューParams : 株式注文受付キューParams)
//                        log.debug("1.3.1 notify訂正取消受付(株式注文受付キューParams : 株式注文受付キューParams)");
//                        l_changeCancelService.notifyChangeCancelAccept(l_params);
//
//                        // 1.3.2 * 株式注文受付キューテーブル.処理区分をupdateする
//                        log.debug("1.3.2 株式注文受付キューテーブル.処理区分をupdateする");
//                        l_mapChanges.put("status", WEB3StatusDef.DEALT);
//                        l_qp.doUpdateQuery(l_params.getPrimaryKey(),l_mapChanges);
//                    }
//                    catch(Exception l_ex)
//                    {
//                        if (l_ex instanceof WEB3BaseRuntimeException)
//                        {
//                            WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
//                            if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
//                            {
//                                log.debug("口座ロック失敗：" + l_params.toString());
//                                continue;
//                            }
//                        }
//                        // 1.3.2 * 株式注文受付キューテーブル.処理区分をupdateする
//                        log.debug("1.3.2 株式注文受付キューテーブル.処理区分をupdateする");
//                        l_mapChanges.put("status", WEB3StatusDef.DATA_ERROR);
//                        l_qp.doUpdateQuery(l_params.getPrimaryKey(),l_mapChanges);
//                    }
//                }
//            }
//
//            log.exiting(STR_METHOD_NAME);
//            return null;
//        }
//
//
//        /**
//         * <p>（get識別コードプレフィクス一覧）。</p>
//         * <p>識別コードプレフィクス一覧を取得する。</p>
//         * @@return 識別コードプレフィクス一覧
//         */
//        public String[] getOrderRequestNumberPrefixGroup() {
//            return this.orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * <p>（set識別コードプレフィクス一覧）。</p>
//         * <p>引数の識別コードプレフィクス一覧をプロパティにセットする。</p>
//         * @@param orderRequestNumberPrefixGroup 識別コードプレフィクス一覧
//         */
//        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup) {
//            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
//        }
//
//    }

}
@
