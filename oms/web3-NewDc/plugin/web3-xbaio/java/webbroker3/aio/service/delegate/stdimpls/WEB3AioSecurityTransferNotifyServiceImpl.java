head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替通知サービス実装クラス(WEB3AioSecurityTransferNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 王蘭芬(中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioUtility;
import webbroker3.aio.data.HostMrgsecTransAcceptRow;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券振替通知サービスImpl)
 * 証券振替通知サービス実装クラス
 *
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferNotifyServiceImpl implements WEB3AioSecurityTransferNotifyService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferNotifyServiceImpl.class);

    /**
     * @@roseuid 41B0317503A9
     */
    public WEB3AioSecurityTransferNotifyServiceImpl()
    {

    }

    /**
     * 証券振替通知処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券振替通知）execute」 参照
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 415781B7037A
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 証券振替通知TransactionCallback( )
        WEB3AioSecurityTransferNotifyTransactionCallback l_callBackService =
            new WEB3AioSecurityTransferNotifyTransactionCallback();

        //1.2 getDefaultProcessor( )
        QueryProcessor l_processors = WEB3AioUtility.getProcessor();

        try
        {
            //1.3 doTransaction(TransactionCallback)
            l_processors.doTransaction(l_callBackService);

            //1.4 createResponse( )
            WEB3BackResponse l_response = l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataException l_ex)
        {
            log.error("DBトランザクション処理を実施エラー", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (証券振替通知TransactionCallback)<BR>
     * 証券振替通知TransactionCallbackクラス
     */
    public class WEB3AioSecurityTransferNotifyTransactionCallback implements TransactionCallback
    {
        /**
         *  ログユーティリティ<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioSecurityTransferNotifyTransactionCallback.class);

        /**
         * (証券振替通知TransactionCallback)<BR>
         * デフォルトコンストラクタ
         * @@roseuid 4157822D02ED
         */
        public WEB3AioSecurityTransferNotifyTransactionCallback()
        {
        }

        /**
         * 注文受付処理を実施する。 <BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（証券振替通知）process」 参照
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 415781E3031C
         */
        public Object process()
            throws DataQueryException, DataNetworkException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            m_log.entering(STR_METHOD_NAME);

            // 1.1 代用振替受付キューテーブル読込み
            //クエリプロセッサを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //(*1) 以下の条件で 代用振替受付キューテーブルから
            //行ロックオプションにてレコードを取得する。
            //[検索条件]
            //データコード = "GI80G"（代用振替請求受付）
            //処理区分 = "0"（未処理）
            List l_lisHostMrgsecTransAcceptRows =
                l_queryProcessor.doFindAllQuery(
                    HostMrgsecTransAcceptRow.TYPE,
                    "request_code = ? and status = ?",
                    // 李志強 U01362の暫定対応 start
//                    "for update",
                    null,
                    // 李志強 U01362の暫定対応 end
                    new Object[]{
                        WEB3HostRequestCodeDef.AIO_MRGSEC_TRANS_ACCEPT,
                        WEB3HostStatusDef.NOT_STARTED_PROCESS});

            // 1.2 取得したキューテーブルのレコード毎のLoop処理
            for (int i = 0; i < l_lisHostMrgsecTransAcceptRows.size(); i++)
            {
                HostMrgsecTransAcceptRow l_hostMrgsecTransAcceptRow =
                    (HostMrgsecTransAcceptRow)l_lisHostMrgsecTransAcceptRows.get(i);

                HashMap l_map = new HashMap();
                String l_strUpdateWhere =
                    " institution_code = ? and branch_code = ? and account_code = ? and order_request_number = ? ";
                String[] l_strUpdateWhereValues = {
                        l_hostMrgsecTransAcceptRow.getInstitutionCode(),
                        l_hostMrgsecTransAcceptRow.getBranchCode(),
                        l_hostMrgsecTransAcceptRow.getAccountCode(),
                        l_hostMrgsecTransAcceptRow.getOrderRequestNumber() };

                WEB3AioOrderManager l_orderManager =
                    (WEB3AioOrderManager)GtlUtils.getTradingModule(ProductTypeEnum.AIO).getOrderManager();

                try
                {
                    //1.2.1  get振替注文単位(String, String, String, String)
                    //[引数]
                    // 証券会社コード： 代用振替受付キュー.証券会社コード
                    // 部店コード： 代用振替受付キュー.部店コード
                    // 顧客コード： 代用振替受付キュー.顧客コード
                    // 識別コード： 代用振替受付キュー.識別コード
                    AioOrderUnit[] l_aioOrderUnits =
                        l_orderManager.getTransferOrderUnit(
                            l_hostMrgsecTransAcceptRow.getInstitutionCode(),
                            l_hostMrgsecTransAcceptRow.getBranchCode(),
                            l_hostMrgsecTransAcceptRow.getAccountCode(),
                            l_hostMrgsecTransAcceptRow.getOrderRequestNumber());

                    //
                    if (l_aioOrderUnits == null || l_aioOrderUnits.length < 1)
                    {
                        log.error("エラー：該当の振替注文単位が存在しない");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                    }

                    // 李志強 U01362の暫定対応 start
                    // TransactionCallback生成
                    WEB3AioSecurityTransferNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3AioSecurityTransferNotifyNormalTransactionCallback(
                            l_hostMrgsecTransAcceptRow,
                            l_aioOrderUnits);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    WEB3AioSecurityTransferNotifyUnitService l_unitService =
//                        (WEB3AioSecurityTransferNotifyUnitService)
//                            Services.getService(WEB3AioSecurityTransferNotifyUnitService.class);
//
//                    //1.2.2 execute(AioOrderUnit[], String, String)
//                    //[引数]
//                    // 注文単位： get振替注文単位()の戻り値
//                    // エラーコード： 代用振替受付キュー.エラーメッセージ
//                    // 受付通知区分： 代用振替受付キュー.受付通知区分
//                    l_unitService.execute(
//                        l_aioOrderUnits,
//                        l_hostMrgsecTransAcceptRow.getErrorMessage(),
//                        l_hostMrgsecTransAcceptRow.getAcceptDiv());
//
//                    l_map.put("status", WEB3HostStatusDef.COMPLETE_PROCESS);
                    // 李志強 U01362の暫定対応 end
                }
                // 李志強 U01362の暫定対応 start
//                catch (NotFoundException l_ex)
//                {
//                    log.error("エラー：該当の振替注文単位が存在しない", l_ex);
//                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);
//                }
//                catch (WEB3BaseException l_ex)
//                {
//                    log.error("エラー：証券振替通知処理中のエラー", l_ex);
//                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);
//                }
//
//                //  1.2.3 キューテーブルのレコードの処理区分の更新
//                l_queryProcessor.doUpdateAllQuery(
//                    HostMrgsecTransAcceptRow.TYPE,
//                    l_strUpdateWhere,
//                    l_strUpdateWhereValues,
//                    l_map);

                catch (WEB3BaseRuntimeException l_ex)
                {
                    log.error("__an WEB3BaseRuntimeException__ ", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //  1.2.3 キューテーブルのレコードの処理区分の更新
                    l_queryProcessor.doUpdateAllQuery(
                        HostMrgsecTransAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strUpdateWhereValues,
                        l_map);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("エラー：該当の振替注文単位が存在しない", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //  1.2.3 キューテーブルのレコードの処理区分の更新
                    l_queryProcessor.doUpdateAllQuery(
                        HostMrgsecTransAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strUpdateWhereValues,
                        l_map);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error("エラー：証券振替通知処理中のエラー", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //  1.2.3 キューテーブルのレコードの処理区分の更新
                    l_queryProcessor.doUpdateAllQuery(
                        HostMrgsecTransAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strUpdateWhereValues,
                        l_map);
                }
				catch (Exception l_ex)
				{
					log.error("__an Exception__ ", l_ex);
					l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

					//  1.2.3 キューテーブルのレコードの処理区分の更新
					l_queryProcessor.doUpdateAllQuery(
						HostMrgsecTransAcceptRow.TYPE,
						l_strUpdateWhere,
						l_strUpdateWhereValues,
						l_map);
				}
                // 李志強 U01362の暫定対応 end
            }
            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
