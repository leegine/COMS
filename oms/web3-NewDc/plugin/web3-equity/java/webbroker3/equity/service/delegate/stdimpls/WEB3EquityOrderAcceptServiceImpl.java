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
filename	WEB3EquityOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文受付サービスクラス(WEB3EquityOrderAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/24 中尾　@寿彦(SRA) 新規作成
                   2004/09/20 鄒政 (中訊) 修正
                   2004/11/05 法@旭 修正
                   2004/12/15 森川  (SRA) 残案件対応
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2005/03/09 劉（FLJ）キューテーブルによる下り処理のトランザクション制御変更、
 　@　@　@　@　@　@　@　@　@　@非同期処理部分WEB3AsynEquityOrderAcceptServiceImplへ移す（新規クラス）

*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.equity.message.WEB3EquityOrderAcceptRequest;
import webbroker3.equity.message.WEB3EquityOrderAcceptResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderAcceptService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;

/**
 * （株式注文受付サービスImpl）。<BR>
 * <BR>
 * 株式注文受付サービスクラス。
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptServiceImpl implements WEB3EquityOrderAcceptService
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderAcceptServiceImpl.class);

    /**
     * （コンストラクタ）。
     * @@roseuid 4042ED5D016D
     */
    public WEB3EquityOrderAcceptServiceImpl()
    {
    }

    /**
     * （注文受付処理を実施する）。<BR>
     * <BR>
     * シーケンス図「（株式注文受付サービス）注文受付」参照。
     * @@param l_request リクエストデータオブジェクト
     * @@return WEB3EquityOrderAcceptResponse
     * @@roseuid 4042ED5D016E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityOrderAcceptRequest l_eqOrderAcceptRequest =
            (WEB3EquityOrderAcceptRequest)l_request;
        WEB3EquityOrderAcceptResponse l_eqOrderAcceptResponse =
            (WEB3EquityOrderAcceptResponse)l_eqOrderAcceptRequest.createResponse();

        //validate
        l_eqOrderAcceptRequest.validate();

        // 1.2. スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_eqOrderAcceptRequest.threadNo.longValue());
        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynEquityOrderAcceptServiceImpl(
            l_eqOrderAcceptRequest));

        log.exiting(STR_METHOD_NAME);
        return l_eqOrderAcceptResponse;
    }

//>>>>>>以降処理WEB3AsynEquityOrderAcceptServiceImplへ移す。2005/03/09  劉（FLJ）

//
//        try
//        {
//            //getDefaultProcessor
//            QueryProcessor l_qp = Processors.getDefaultProcessor();
//
//            //株式注文受付TransactionCallback
//            WEB3EquityOrderAcceptServiceTransactionCallback l_eqOdrAptSrvTranCallback;
//            l_eqOdrAptSrvTranCallback = new WEB3EquityOrderAcceptServiceTransactionCallback();
//
//            //set識別コードプレフィクス一覧
//            l_eqOdrAptSrvTranCallback.setOrderRequestNumberPrefixGroup(l_eqOrderAcceptRequest.orderRequestNumberPrefixGroup);
//
//			//doTransaction
//			WEB3BaseException l_baseExp =
//							  (WEB3BaseException)l_qp.doTransaction(
//								  QueryProcessor.TX_JOIN_EXISTING,
//								  l_eqOdrAptSrvTranCallback);
//            if (l_baseExp != null)
//            {
//                log.exiting(STR_METHOD_NAME);
//                throw l_baseExp;
//            }
//        }
//        catch (DataException l_de)
//        {
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_de.getMessage(),
//                l_de);
//        }
//
//        log.exiting(STR_METHOD_NAME);
//        return l_eqOrderAcceptResponse;
//    }
//
//    /**
//     * （処理区分をupdateする）。
//     * @@param l_hostEqtypeOrderAcceptParams 株式注文受付キューParams
//     * @@param l_strStatus 処理区分
//     * @@roseuid 4042ED5D016E
//     */
//    public void updateStatus(HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams,String l_strStatus)
//       throws WEB3SystemLayerException
//    {
//        final String STR_METHOD_NAME =
//            "updateStatus(HostEqtypeOrderAcceptParams, String)";
//		log.entering(STR_METHOD_NAME);
//        if (l_hostEqtypeOrderAcceptParams == null)
//        {
//            throw new WEB3BaseRuntimeException(
//               WEB3ErrorCatalog.SYSTEM_ERROR_80019,
//               this.getClass().getName() + "." + STR_METHOD_NAME);
//        }
//
//        try
//        {
//            l_hostEqtypeOrderAcceptParams.setStatus(l_strStatus);
//            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//            l_queryProcessor.doUpdateQuery(l_hostEqtypeOrderAcceptParams);
//        }
//        catch (DataException l_de)
//        {
//            log.error(STR_METHOD_NAME,l_de);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_de.getMessage(),
//                l_de);
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    /**
//     * （株式注文受付TransactionCallback）。<BR>
//     * <BR>
//     * 株式注文受付処理を行うトランザクション・コールバッククラス。
//     * @@version 1.0
//     */
//    private class WEB3EquityOrderAcceptServiceTransactionCallback implements TransactionCallback
//    {
//
//        /**
//         * （識別コードプレフィクス一覧）。
//         */
//        private String[] orderRequestNumberPrefixGroup;
//
//
//        /**
//         * （get識別コードプレフィクス一覧）。<BR>
//         * <BR>
//         * 識別コードプレフィクス一覧を取得する。
//         * @@return 識別コードプレフィクス一覧
//         */
//        public String[] getOrderRequestNumberPrefixGroup() {
//            return this.orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * （set識別コードプレフィクス一覧）。<BR>
//         * <BR>
//         * 引数の識別コードプレフィクス一覧をプロパティにセットする。
//         * @@param orderRequestNumberPrefixGroup 識別コードプレフィクス一覧
//         */
//        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup) {
//            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * （process）。<BR>
//         * <BR>
//         * トランザクション処理を実施する。 <BR>
//         * <BR>
//         * シーケンス図 <BR>
//         * 「（株式注文受付サービス）process」参照。 <BR>
//         * <BR>
//         * @@return WEB3BaseException（内部処理にてWEB3BaseException例外が発生した場合）<BR>
//         * @@throws DataNetworkException
//         * @@throws DataQueryException
//         * @@throws DataCallbackException
//         * @@roseuid 4050215102CD
//         */
//
//        public Object process()
//            throws DataNetworkException, DataQueryException, DataCallbackException
//        {
//            final String STR_METHOD_NAME =
//                "WEB3EquityOrderAcceptServiceTransactionCallback.process()";
//            log.entering(STR_METHOD_NAME);
//            HostEqtypeOrderAcceptRow l_hostEqtypeOrderAcceptRow;
//            HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams;
//
//            //get識別コードプレフィクス一覧
//            String[] l_odrReqNumPfxGrp = this.getOrderRequestNumberPrefixGroup();
//
//
//            //注文受付キューテーブル読込
//            List l_lisRecords;                          //キューテーブルレコードのリスト
//            String l_strWhereQuery = null;
//            Object l_objWhereQuerys[] = null;
//            final String l_strCondition = "for update";
//
//            final int l_iOdrReqNumPfxCnt       = l_odrReqNumPfxGrp.length;
//            final String l_strDataCodePart      = "request_code = ?";
//            final String l_strReqCodeCondPart   = "order_request_number like ?";
//            final String l_strStatusPart        = "status = ?";
//            int l_iQueryParamCnt;
//
//            if (l_iOdrReqNumPfxCnt > 0)
//            {
//                //where句パターン文字列の生成
//                l_strWhereQuery = l_strReqCodeCondPart;
//
//                for (int i = 1; i < l_iOdrReqNumPfxCnt; i++)
//                {
//                    l_strWhereQuery += " or " + l_strReqCodeCondPart;
//                }
//
//                l_strWhereQuery =
//                    l_strDataCodePart + " and (" + l_strWhereQuery + ") and " + l_strStatusPart;
//
//
//                //where句パラメータの生成
//                l_objWhereQuerys = new Object[l_iOdrReqNumPfxCnt + 2];
//
//                l_objWhereQuerys[0] = WEB3HostRequestCodeDef.EQUITY_ORDER_ACCEPT;
//                l_iQueryParamCnt = 1;
//
//				for (int i = 0; i < l_iOdrReqNumPfxCnt; i++)
//                {
//                    l_objWhereQuerys[l_iQueryParamCnt] = l_odrReqNumPfxGrp[i] + "%";
//                    l_iQueryParamCnt++;
//                }
//
//                l_objWhereQuerys[l_iQueryParamCnt] = WEB3HostStatusDef.NOT_STARTED_PROCESS;
//
//                //Log出力
//                log.debug("doFindAllQuery(arg1, arg2, arg3, arg4)");
//                log.debug("arg1 = [" + HostEqtypeOrderAcceptRow.TYPE.toString() + "]");
//                log.debug("arg2 = [" + l_strWhereQuery + "]");
//                log.debug("arg3 = [" + l_strCondition + "]");
//                String l_strParamListJoinedForLog = l_objWhereQuerys[0].toString();
//                for (int i = 0; i < l_objWhereQuerys.length - 1; i++)
//                {
//                    l_strParamListJoinedForLog += l_objWhereQuerys[i].toString() + ",";
//                }
//                l_strParamListJoinedForLog += l_objWhereQuerys[l_objWhereQuerys.length - 1].toString();
//                log.debug("arg4 = [" + l_strParamListJoinedForLog + "]");
//
//                // l_list
//                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//                l_lisRecords = l_queryProcessor.doFindAllQuery(
//                        HostEqtypeOrderAcceptRow.TYPE, l_strWhereQuery, l_strCondition, l_objWhereQuerys);
//            }
//            else
//            {
//                l_lisRecords = null;
//            }
//
//            //2) get 株式注文受付一件サービス
//            WEB3EquityOrderAcceptUnitService l_service =
//                (WEB3EquityOrderAcceptUnitService)Services.getService(
//                    WEB3EquityOrderAcceptUnitService.class);
//
//            //取得したキューテーブルのレコード数分、Loop処理
//            int l_intSize = 0 ;
//            if (l_lisRecords != null)
//            {
//                l_intSize = l_lisRecords.size();
//            }
//            for (int i = 0; i < l_intSize; i++)
//            {
//                //処理区分
//                String l_strStatus = WEB3HostStatusDef.COMPLETE_PROCESS;
//
//                l_hostEqtypeOrderAcceptRow =
//                    (HostEqtypeOrderAcceptRow) l_lisRecords.get(i);
//                l_hostEqtypeOrderAcceptParams =
//                    new HostEqtypeOrderAcceptParams(l_hostEqtypeOrderAcceptRow);
//
//                try
//                {
//                    //3)  株式注文受付一件サービス.notify注文受付
//                    l_service.notifyOrderAccept(l_hostEqtypeOrderAcceptParams);
//                }
//                catch (Exception l_e)
//                {
//                    //--------------------
//                    //処理対象キューUPDATE　@(エラー時)
//                    //--------------------
//                    if (l_e instanceof WEB3BaseRuntimeException)
//                    {
//                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_e;
//                        if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
//                        {
//                            log.debug("口座ロック失敗：" + l_hostEqtypeOrderAcceptParams.toString());
//                            continue;
//                        }
//                    }
//
//                    //エラーがそれ以外の場合　@(=>エラー)
//                    log.debug("一件処理にてエラー発生：" + l_hostEqtypeOrderAcceptParams.toString());
//                    l_strStatus = WEB3HostStatusDef.DATA_ERROR;
//                }
//
//                //株式注文受付キューテーブル.処理区分をupdateしcommitする
//                try
//                {
//                    updateStatus(l_hostEqtypeOrderAcceptParams, l_strStatus);
//                }
//                catch (WEB3SystemLayerException l_wse)
//                {
//                    log.error(STR_METHOD_NAME,l_wse);
//                    return l_wse;
//                }
//            }
//            log.exiting(STR_METHOD_NAME);
//            return null;
//
//        }
//    }

}
@
