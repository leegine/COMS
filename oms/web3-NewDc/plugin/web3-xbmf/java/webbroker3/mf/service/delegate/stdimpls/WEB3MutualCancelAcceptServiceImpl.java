head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消受付サービス実装クラス(WEB3MutualCancelAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/23 韋念瓊 (中訊) レビュー
Revesion History : 2009/05/13 馮海濤 (中訊) 仕様変更 No643
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.data.HostXbmfCancelAcceptParams;
import webbroker3.mf.data.HostXbmfCancelAcceptRow;
import webbroker3.mf.message.WEB3MutualCancelAcceptResponse;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託取消受付サービス実装クラス
 *
 * @@author 于美麗(中訊)
 * @@version 1.0*
 */
public class WEB3MutualCancelAcceptServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualCancelAcceptService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelAcceptServiceImpl.class);


    /**
     * 投資信託取消受付サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（投資信託）取消受付」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40565F3A039B
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
            // １）　@クエリプロセッサのインスタンスを取得する
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // ２） 投信取消受付TransactionCallbackのインスタンスを生成する
            WEB3MutualCancelAcceptTransactionCallback l_transactionCallback =
                new WEB3MutualCancelAcceptTransactionCallback();

            //３）DBトランザクション処理を実施する
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME + " __Error[DBサーバとの通信に失敗した]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました when l_queryProcessor.doTransaction()");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        WEB3MutualCancelAcceptResponse l_response = (WEB3MutualCancelAcceptResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (投信取消受付TransactionCallback)<BR>
     * 投信取消受付サービス処理を実施する。<BR>
     */
    public class WEB3MutualCancelAcceptTransactionCallback
        implements TransactionCallback
    {

        /**
         * ログユーティリティ
         */
        private  WEB3LogUtility l_log =
            WEB3LogUtility.getInstance(WEB3MutualCancelAcceptTransactionCallback.class);

        /**
         * デフォルトコンストラクタ
         * @@roseuid 40A433050166
         */
        public WEB3MutualCancelAcceptTransactionCallback()
        {

        }

        /**
         * 投信取消受付サービス処理を実施する。 <BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（投信取消受付）process」参照。 <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40A4341B01F3
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "process()";
            l_log.entering(STR_METHOD_NAME);

            // １）　@処理対象レコード取得
            // 　@投信取消受付キューテーブルを、行ロック（select for update）にて読み込む
            String l_strWhere = "status = ?";
            String l_strCondition = null;

            Object[] l_objWhere = { WEB3StatusDef.NOT_DEAL };
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    HostXbmfCancelAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhere);

            // ２）　@投信取消受付UnitServiceを取得する
            WEB3MutualCancelAcceptUnitService l_cancelAcceptUnitService =
                (WEB3MutualCancelAcceptUnitService) Services.getService(
                    WEB3MutualCancelAcceptUnitService.class);

            //検索結果の投信取消受付キューテーブルの各行に対して実施する
            int l_intResultLength = 0;
            if (l_lisSearchResult != null && l_lisSearchResult.size() != 0)
            {
                l_intResultLength = l_lisSearchResult.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getOrderManager();

            HostXbmfCancelAcceptParams l_cancelAcceptParams = null;

            for (int i = 0; i < l_intResultLength; i++)
            {
                // ３）　@投信受付確定インタセプタを生成する
                WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor =
                    new WEB3MutualFundAcceptConfirmInterceptor();

                l_orderManager.setThreadLocalPersistenceEventInterceptor(l_confirmInterceptor);

                // 投信取消受付キューParamsの取得
                l_cancelAcceptParams =
                    (HostXbmfCancelAcceptParams) l_lisSearchResult.get(i);

                HashMap l_map = new HashMap();
                try
                {
                    // ５） 生成した投信受付確定インタセプタオブジェクト.set注文エラー理由コード()
                    l_confirmInterceptor
                            .setOrderErrorReasonCode(l_cancelAcceptParams
                                    .getErrorMessage());

                    MutualFundOrderUnit l_mfOrderUnit = null;
                    try
                    {
                        // ６） キューレコードに該当する投信注文単位オブジェクトを取得する。<BR>
                        // 　@拡張投信注文マネージャ.get注文単位()をコールして、投信注文単位オブジェクトを取得
                        l_mfOrderUnit = l_orderManager.getOrderUnit(
                            l_cancelAcceptParams.getInstitutionCode(),
                            l_cancelAcceptParams.getBranchCode(),
                            l_cancelAcceptParams.getOrderRequestNumber());
                    }
                    catch (WEB3BaseException l_ex)
                    {
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_ex.getErrorInfo()))
                        {
                            //処理対象の取消受付キューレコード.処理区分を設定用
                            String l_strUpdateWhere = " institution_code = ? "
                                + " and branch_code = ? "
                                + " and order_request_number = ?";
                            String[] l_strArrayUpdateParams = {
                                l_cancelAcceptParams.getInstitutionCode(),
                                l_cancelAcceptParams.getBranchCode(),
                                l_cancelAcceptParams.getOrderRequestNumber()
                            };

                            l_map.put("status", WEB3StatusDef.DEALT);
                            // 処理対象の取消受付キューレコード.処理区分を設定”
                            l_queryProcessor.doUpdateAllQuery(
                                HostXbmfCancelAcceptRow.TYPE,
                                l_strUpdateWhere,
                                l_strArrayUpdateParams,
                                l_map);

                             continue;
                        }
                    }
                    // ７） 取消受付処理を行う
                    String l_strAcceptStatus = l_cancelAcceptParams
                            .getAcceptStatus();

                    // TransactionCallback生成
                    WEB3MutualCancelAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3MutualCancelAcceptNormalTransactionCallback(
                            l_mfOrderUnit,
                            l_confirmInterceptor,
                            l_cancelAcceptParams);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
				catch (Exception l_ex)
				{
					if(l_ex instanceof WEB3BaseRuntimeException)
					{
						//口座ロックに失敗してエラーが発生した場合、
						//処理対象キューを更新しない。
						WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
						if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
						{
							log.debug("口座ロック失敗：" + l_cancelAcceptParams.toString());
							continue;
						}
					}
					// それ以外のエラーが発生した場合
					// 処理対象の取消受付キューレコード.処理区分”9：エラー”をセットし更新する
                    log.debug("__an Exception__ ");
					log.error("一件処理にて例外が発生した場合",l_ex);
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);

                    String l_strUpdateWhere = " institution_code = ? "
                                       + " and branch_code = ? "
                                       + " and order_request_number = ?";
                    String[] l_strArrayUpdateParams = {
                        l_cancelAcceptParams.getInstitutionCode(),
                        l_cancelAcceptParams.getBranchCode(),
                        l_cancelAcceptParams.getOrderRequestNumber()
                    	};
                    l_queryProcessor.doUpdateAllQuery(
                        HostXbmfCancelAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strArrayUpdateParams,
                        l_map);
                }
            }
            l_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
