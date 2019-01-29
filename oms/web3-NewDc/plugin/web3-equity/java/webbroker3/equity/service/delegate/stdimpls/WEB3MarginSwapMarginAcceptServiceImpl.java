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
filename	WEB3MarginSwapMarginAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡受付サービスImpl(WEB3MarginSwapMarginAcceptServiceImpl.java)
Revesion History : 2004/10/8 盧法@旭(中訊) 新規作成
                   2004/12/13 岡村和明(SRA) 残案件対応 Ｎｏ.３５５
                   2004/12/21 岡村和明(SRA) JavaDoc修正
                   2005/01/05 岡村和明(SRA) 口座ロック修正
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.data.HostEqtypeSwapAcceptParams;
import webbroker3.equity.data.HostEqtypeSwapAcceptRow;
import webbroker3.equity.message.WEB3MarginSwapMarginAcceptRequest;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptService;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptUnitService;
import webbroker3.gentrade.WEB3GentradeRequestCodesForRead;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引現引現渡受付サービスImpl）。<br>
 * <br>
 * 信用取引現引現渡受付サービス実装クラス
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptServiceImpl implements WEB3MarginSwapMarginAcceptService
{

    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginSwapMarginAcceptServiceImpl.class);

    /**
     * <p>（信用取引現引現渡受付サービスImpl）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3MarginSwapMarginAcceptServiceImpl()
    {
    }

    /**
     * <p>（execute）。</p>
     * <p>信用取引現引現渡受付サービス処理を実施する。<br>
     * <br>
     * シーケンス図<br>
     * 「（信用取引現引現渡受付サービス）現引現渡受付」参照。</p>
     * @@param l_request リクエストデータ
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "EXECUTE";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1 validate()
        WEB3MarginSwapMarginAcceptRequest l_marginSwapMarginAcceptRequest = (WEB3MarginSwapMarginAcceptRequest)l_request;
        l_marginSwapMarginAcceptRequest.validate();

        QueryProcessor l_QueryProcessor = null;
        try
        {
            // 1.2 getDefaultProcessor()
            l_QueryProcessor = Processors.getDefaultProcessor();
            // 1.3 信用取引現引現渡受付TransactionCallback
            WEB3MarginSwapMarginAcceptTransactionCallback l_callback = new WEB3MarginSwapMarginAcceptTransactionCallback();
            // 1.4 set識別コードプレフィクス一覧(識別コードプレフィクス一覧 : String[])
            l_callback.setOrderRequestNumberPrefixGroup(l_marginSwapMarginAcceptRequest.orderRequestNumberPrefixGroup);
            // 1.5 doTransaction
            WEB3BaseException l_baseExp = (WEB3BaseException) l_QueryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callback);
            if (l_baseExp != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw l_baseExp;
            }
        }
        catch (DataCallbackException l_dce)
        {
            WEB3BaseException l_wbe = (WEB3BaseException)l_dce.getDetails();
            throw l_wbe;
        }
        catch (DataException l_de)
        {
            log.debug(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }

    /**
     * <p>（信用取引現引現渡受付TransactionCallback）。</p>
     * <p>信用取引現引現渡受付TransactionCallbackクラス。</p>
     */
    public class WEB3MarginSwapMarginAcceptTransactionCallback implements TransactionCallback
    {

        /**
         * <p>（識別コードプレフィクス一覧）。</p>
         * <p>識別コードプレフィクス一覧。</p>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * <p>（信用取引現引現渡受付TransactionCallback）。</p>
         * <p>コンストラクタ。</p>
         */
        public WEB3MarginSwapMarginAcceptTransactionCallback()
        {
        }

        /**
         * <p>（process）。</p>
         * <p>トランザクション処理を実施する。<br>
         * <br>
         * シーケンス図<br>
         * 「（信用取引現引現渡受付サービス）process」参照。</p>
         * @@return Object
         */
        public Object process() throws DataQueryException, DataNetworkException, IllegalStateException
        {

            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //1.1
            //get識別コードプレフィクス一覧()
            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();
            
            // 処理対象データコードテーブル読込
            String[] l_requestCodesForReadList = null;
            try
            {
                l_requestCodesForReadList =
                    WEB3GentradeRequestCodesForRead.getRequestCodesForReadList(
                    WEB3MarginSwapMarginAcceptRequest.PTYPE);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            
            //1.2
            //[検索条件]
            //【現引現渡受付キューテーブル】を、select for updateにより以下の条件を指定して読み込む。
            log.debug("1.2 現引現渡受付キューテーブル読み込み");
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" status = ? "); //処理区分 == ”未処理”

            int l_intRequestNumberPrefixGroupCnt = l_orderRequestNumberPrefixGroup.length;

            l_sbWhere.append(" and (");
            for(int i = 0 ; i < l_intRequestNumberPrefixGroupCnt ; i++)
            {
                if(i == 0)
                {
                    l_sbWhere.append(" substr(order_request_number , 1 , 2) = ?");
                } else
                {
                    l_sbWhere.append(" or substr(order_request_number , 1 , 2) = ?");
                }
            }
            l_sbWhere.append(" )");
            
            int l_intRequestCodesForReadListCnt = 0;
            if (l_requestCodesForReadList != null)
            {
                l_intRequestCodesForReadListCnt = l_requestCodesForReadList.length;
            }
            if (l_intRequestCodesForReadListCnt > 0)
            {
                l_sbWhere.append(" and (request_code = ?");
                for (int i = 1; i < l_intRequestCodesForReadListCnt; i++)
                {
                    l_sbWhere.append(" or request_code = ?");
                }
                l_sbWhere.append(")");
            }
            
            Object[] l_objBindValues = new Object[l_intRequestNumberPrefixGroupCnt + l_intRequestCodesForReadListCnt + 1];
            l_objBindValues[0] = WEB3StatusDef.NOT_DEAL;
            int l_intSize = 1;

            for(int i = 0 ; i < l_intRequestNumberPrefixGroupCnt ; i++)
            {
                l_objBindValues[l_intSize++] = l_orderRequestNumberPrefixGroup[i];
                log.debug("識別コードプレフィクス一覧：" + l_orderRequestNumberPrefixGroup[i]);
            }
            for (int i = 0; i < l_intRequestCodesForReadListCnt; i++)
            {
                l_objBindValues[l_intSize++] = l_requestCodesForReadList[i];
                log.debug("処理対象データコード一覧[ " + i + "]：" + l_requestCodesForReadList[i]);
            }

            //スロー DataNetworkException, DataFindException
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            //注文受付キューテーブルの結果リスト
            List l_lisSearchResult = null;

            //l_lisSearchResult = l_QueryProcessor.doFindAllQuery(HostEqtypeSwapAcceptRow.TYPE, l_sbWhere.toString(), null, "FOR UPDATE", l_objIfoOrderUnitWhere);
            l_lisSearchResult = l_QueryProcessor.doFindAllQuery(HostEqtypeSwapAcceptRow.TYPE, l_sbWhere.toString(), null, null, l_objBindValues);
            int l_intNum = 0;
            if (l_lisSearchResult != null )
            {
                l_intNum = l_lisSearchResult.size();
            }

            //1.3(したキューテーブルのレコード数分、Loop処理）
            log.debug("1.3 取得したキューテーブルのレコード数分、Loop処理");
            WEB3MarginSwapMarginAcceptUnitService l_accept = (WEB3MarginSwapMarginAcceptUnitService) Services.getService(WEB3MarginSwapMarginAcceptUnitService.class);

            for (int i = 0; i < l_intNum; i++)
            {
                HostEqtypeSwapAcceptParams l_params = new HostEqtypeSwapAcceptParams((HostEqtypeSwapAcceptRow)l_lisSearchResult.get(i));

                try
                {
                    //1.3.1 notify現引現渡受付(現引現渡受付キューParams : 現引現渡受付キューParams)
                    l_accept.notifySwapMarginAccept(l_params);

                    //1.3.6 処理対象キューレコードエラーをupdateする
                    //一件処理内部へ移す.start
                    //log.debug("1.3.2 処理対象キューレコードをupdateする");
                    //l_mapChanges.put("status", WEB3StatusDef.DEALT);
                    //l_QueryProcessor.doUpdateQuery(l_params.getPrimaryKey(), l_mapChanges);
                    //.end
                }
                catch (Exception l_ex)
                {
                    //1.3.6 処理対象キューレコードエラーをupdateする
                    if (l_ex instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
                        if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
                        {
                            log.debug("口座ロック失敗：" + l_params.toString());
                            continue;
                        }
                    }
                    log.error("一件処理にてエラー発生", l_ex);
                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_QueryProcessor.doUpdateQuery(l_params);
                }
            }

            log.entering(STR_METHOD_NAME);
            return null;
        }


        /**
         * <p>（get識別コードプレフィクス一覧）。</p>
         * <p>識別コードプレフィクス一覧を取得する。</p>
         * @@return 識別コードプレフィクス一覧
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return this.orderRequestNumberPrefixGroup;
        }

        /**
         * <p>（set識別コードプレフィクス一覧）。</p>
         * <p>引数の識別コードプレフィクス一覧をプロパティにセットする。</p>
         * @@param orderRequestNumberPrefixGroup 識別コードプレフィクス一覧
         */
        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup)
        {
            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
        }

    }
}
@
