head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替請求受付サービスImpl(WEB3AccTransChangeRequestAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/25 王蘭芬(中訊) レビュー
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostTransferAcceptRow;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (振替請求受付サービスImpl)<BR>
 * 振替請求受付サービス実装クラス<BR>
 *
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestAcceptServiceImpl
    implements WEB3AccTransChangeRequestAcceptService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestAcceptServiceImpl.class);

    /**
     * 振替請求受付処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（振替請求受付）振替請求受付」参照
     * @@param l_request - (リクエストデータ)
     *
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1C13036C
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            // １） 振替請求受付トランザクションコールバックインスタンスを生成する
            WEB3AccTransChangeRequestAcceptTransactionCallback l_transactionCallback =
                new WEB3AccTransChangeRequestAcceptTransactionCallback();

            // ２）　@クエリプロセッサを取得する
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // ３）DBトランザクション処理を実施する。
            // [doTransaction()に指定する引数]
            // トランザクション属性：　@TX_CREATE_NEW
            // トランザクションコールバック：　@振替請求受付TransactionCallbackインスタンス
            l_queryProcessor.doTransaction(l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME + " __Error[DBサーバとの通信に失敗した]__" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        WEB3BackResponse l_response =
            l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (振替請求受付TransactionCallbackクラス)
     */
    public class WEB3AccTransChangeRequestAcceptTransactionCallback
        implements TransactionCallback
    {

        /**
         * ログユーティリティ<BR>
         */
        private WEB3LogUtility l_log =
            WEB3LogUtility.getInstance(
                WEB3AccTransChangeRequestAcceptTransactionCallback.class);

        /**
         * デフォルトコンストラクタ
         * @@return WEB3AccTransChangeRequestAcceptTransactionCallback
         * @@roseuid 415A6F3F0270
         */
        public WEB3AccTransChangeRequestAcceptTransactionCallback()
        {

        }

        /**
         * 振替請求受付処理を実施する。 <BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（振替請求受付）process」参照
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 413C1FDC003F
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "process()";
            l_log.entering(STR_METHOD_NAME);

            // 1) 振替請求受付キューテーブル読込み
            String l_strWhere = "request_code = ? " +
                                "and status = ?";
            // 李志強 U01504の暫定対応 start
//            String l_strCondition = "for update";
            String l_strCondition = null;
            // 李志強 U01504の暫定対応 end

            Object[] l_objWhere = { WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ACCEPT ,
                WEB3StatusDef.NOT_DEAL };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                HostTransferAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhere);

            int l_intResultLength = 0;
            if (l_lisSearchResult != null && l_lisSearchResult.size() != 0)
            {
                l_intResultLength = l_lisSearchResult.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_orderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();

            WEB3AccTransChangeAcceptUnitService l_AcceptService =
                (WEB3AccTransChangeAcceptUnitService) Services.getService(
                    WEB3AccTransChangeAcceptUnitService.class);

            WEB3AccTransChangeCompleteUnitService l_CompleteService =
                (WEB3AccTransChangeCompleteUnitService) Services.getService(
                    WEB3AccTransChangeCompleteUnitService.class);


            // 2) 取得したレコード毎のLoop処理
            for (int i = 0; i < l_intResultLength; i++)
            {
                // 振替請求受付キューParamsの取得
                HostTransferAcceptRow l_hostTransferAcceptRow =
                    (HostTransferAcceptRow)l_lisSearchResult.get(i);


                // 2 - 1) 注文単位オブジェクトの配列を取得する。
                // [引数]
                // 証券会社コード： 振替請求受付キューテーブル.証券会社コード
                // 部店コード： 振替請求受付キューテーブル.部店コード
                // 顧客コード： 振替請求受付キューテーブル.顧客コード
                // 識別コード： 振替請求受付キューテーブル.識別コード
                AioOrderUnit[] l_aioOrderUnit = null;

                boolean l_blnIsFailProcess = false;
                Map l_map = new Hashtable();

                try
                {

                    // 李志強 U01504の暫定対応 start
                    WEB3AccTransChangeRequestAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3AccTransChangeRequestAcceptNormalTransactionCallback(
                        l_hostTransferAcceptRow);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    l_aioOrderUnit = l_orderManager.getTransferOrderUnit(
//                        l_hostTransferAcceptRow.getInstitutionCode(),
//                        l_hostTransferAcceptRow.getBranchCode(),
//                        l_hostTransferAcceptRow.getAccountCode(),
//                        l_hostTransferAcceptRow.getOrderRequestNumber());
//
//                    // 2 - 2) 取得した注文単位毎のLoop処理
//                    if(l_aioOrderUnit.length != 0)
//                    {
//                        for(int j = 0; j<l_aioOrderUnit.length; j++)
//                        {
//                            // 2 - 2 - 1)振替請求受付DB更新処理を行う。
//                            // [引数]
//                            // 注文単位： 注文単位オブジェクト
//                            // エラーコード： 振替請求受付キューテーブル.エラーメッセージ
//                            // 受付通知区分： 振替請求受付キューテーブル.受付通知区分
//                            log.debug("l_hostTransferAcceptRow.getErrorMessage() = "
//                                    + l_hostTransferAcceptRow.getErrorMessage());
//                            l_AcceptService.execute(
//                                l_aioOrderUnit[j],
//                                l_hostTransferAcceptRow.getErrorMessage(),
//                                l_hostTransferAcceptRow.getAcceptDiv());
//
//                            if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(
//                                l_hostTransferAcceptRow.getAcceptDiv()))
//                            {
//                                // 2 - 2 - 2)振替完了処理に伴う注文データの更新と
//                                // トランザクションデータの生成を行う。
//                                // [引数]
//                                // 注文単位： 注文単位オブジェクト
//                                l_CompleteService.completeChange(
//                                    l_aioOrderUnit[j]);
//                            }
//                        }
//                    }
                    // 李志強 U01504の暫定対応 end
                }
                  // 李志強 U01504の暫定対応 start
//                catch (WEB3BaseException l_ex)
//                {
//                    l_log.debug("__an WEB3BaseException__", l_ex);
//                    l_blnIsFailProcess = true;
//                }
//                catch (NotFoundException l_ex)
//                {
//                    l_log.debug(
//                        "__NotFoundException__ when l_orderManager.getTransferOrderUnit:"
//                        , l_ex);
//                    l_blnIsFailProcess = true;
//                }
                catch (Exception l_ex)
                {
                    l_log.debug("__an Exception__", l_ex);
                    l_blnIsFailProcess = true;
                }
                // 李志強 U01504の暫定対応 end

                // 李志強 U01504の暫定対応 start
//                if (l_blnIsFailProcess)
//                {
//                    // 処理対象の振替請求受付キューテーブル.処理区分を設定用
//                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
//                    l_log.debug("__an WEB3BaseException__ ");
//                }
//                else
//                {
//                    // 処理対象の振替請求受付キューテーブル.処理区分を設定用
//                    l_map.put("status", WEB3StatusDef.DEALT);
//                }
//
//                // 処理対象の振替請求受付キューテーブル.処理区分を設定用
//                String l_strUpdateWhere = " institution_code = ? "
//                                   + " and branch_code = ? "
//                                   + " and account_code = ? "
//                                   + "and order_request_number = ?";
//
//                String[] l_strArrayUpdateParams = {
//                    l_hostTransferAcceptRow.getInstitutionCode(),
//                    l_hostTransferAcceptRow.getBranchCode(),
//                    l_hostTransferAcceptRow.getAccountCode(),
//                    l_hostTransferAcceptRow.getOrderRequestNumber()
//                };
//
//                // 2 - 3）　@処理対象の取消受付キューレコード.処理区分を設定”
//                l_queryProcessor.doUpdateAllQuery(
//                    HostTransferAcceptRow.TYPE,
//                    l_strUpdateWhere,
//                    l_strArrayUpdateParams,
//                    l_map);

                if (l_blnIsFailProcess)
                {
                    // 処理対象の振替請求受付キューテーブル.処理区分を設定用
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);

                    // 処理対象の振替請求受付キューテーブル.処理区分を設定用
                    String l_strUpdateWhere = " institution_code = ? "
                                       + " and branch_code = ? "
                                       + " and account_code = ? "
                                       + "and order_request_number = ?";

                    String[] l_strArrayUpdateParams = {
                        l_hostTransferAcceptRow.getInstitutionCode(),
                        l_hostTransferAcceptRow.getBranchCode(),
                        l_hostTransferAcceptRow.getAccountCode(),
                        l_hostTransferAcceptRow.getOrderRequestNumber()
                    };

                    // 2 - 3）　@処理対象の取消受付キューレコード.処理区分を設定”
                    l_queryProcessor.doUpdateAllQuery(
                        HostTransferAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strArrayUpdateParams,
                        l_map);
                }
                // 李志強 U01504の暫定対応 end

            }

            l_log.entering(STR_METHOD_NAME);
            return null;
        }
    }
}
@
