head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信注文受付サービスImpl(WEB3MutualOrderAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建  (中訊) 新規作成
                   2004/08/23 王蘭芬 (中訊) レビュー
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
import webbroker3.mf.data.HostXbmfOrderAcceptParams;
import webbroker3.mf.data.HostXbmfOrderAcceptRow;
import webbroker3.mf.message.WEB3MutualOrderAcceptResponse;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信注文受付サービスImpl)
 * 投資信託注文受付サービス実装クラス<BR>
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3MutualOrderAcceptServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualOrderAcceptService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderAcceptServiceImpl.class);

    /**
     * 投資信託注文受付サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（投資信託）注文受付」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40566483009E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            // １）　@クエリプロセッサのインスタンスを取得する
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // ２） 投信注文受付TransactionCallbackのインスタンスを生成する
            WEB3MutualOrderAcceptTransactionCallback l_transactionCallback =
                new WEB3MutualOrderAcceptTransactionCallback();

            //３）DBトランザクション処理を実施する
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_transactionCallback);
            WEB3MutualOrderAcceptResponse l_response =
                (WEB3MutualOrderAcceptResponse) l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" __Error[DBサーバとの通信に失敗した]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    public class WEB3MutualOrderAcceptTransactionCallback
        implements TransactionCallback
    {
        /**
         * ログユーティリティ
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3MutualOrderAcceptTransactionCallback.class);

        /**
         * (投信注文受付TransactionCallback)<BR>
         * デフォルトコンストラクタ
         * @@roseuid 40A4332C0137
         */
        public WEB3MutualOrderAcceptTransactionCallback()
        {

        }

        /**
         * 投信注文受付サービス処理を実施する。 <BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（投信注文受付）process」参照。<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40A4342E0166
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "process()";
            m_log.entering(STR_METHOD_NAME);

            //１）　@処理対象レコード取得
            // 投信注文受付キューテーブルを、行ロック
            // select for update）にて読み込む
            String l_strWhere = " status = ?";
            String l_strCondition = null;
            Object[] l_objWhereValues = { WEB3StatusDef.NOT_DEAL };
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            /*検索*/
            List l_lisMFOrderAcceptUnitResult =
                l_queryProcessor.doFindAllQuery(
                    HostXbmfOrderAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhereValues);

            // ２）　@投信注文受付UnitServiceを取得する
            WEB3MutualOrderAcceptUnitService l_OrderAcceptUnitService =
                (WEB3MutualOrderAcceptUnitService) Services.getService(
                    WEB3MutualOrderAcceptUnitService.class);

            //３）　@投信受付確定インタセプタを生成する。
            WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor =
                new WEB3MutualFundAcceptConfirmInterceptor();

            //検索結果の投信注文受付キューテーブルの各行に対して実施する

            int l_intResultLength = 0;
            if (l_lisMFOrderAcceptUnitResult != null &&
                l_lisMFOrderAcceptUnitResult.size() != 0)
            {
                l_intResultLength = l_lisMFOrderAcceptUnitResult.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();

            HostXbmfOrderAcceptParams l_orderAcceptParams = null;

            for (int i = 0; i < l_intResultLength; i++)
            {
                // 投信注文受付キューParamsの取得
                l_orderAcceptParams =
                    (HostXbmfOrderAcceptParams) l_lisMFOrderAcceptUnitResult.get(i);
                HashMap l_map = new HashMap();
                String l_strUpdateWhere =
                    " institution_code = ? " +
                    " and branch_code = ? " +
                    " and order_request_number = ?";
                String[] l_updateParams =
                    {l_orderAcceptParams.getInstitutionCode(),
                     l_orderAcceptParams.getBranchCode(),
                     l_orderAcceptParams.getOrderRequestNumber()};

                try
                {
                    //４）　@生成した投信受付確定インタセプタオブジェクト.set注文エラー理由コード()を
                    //  コールし、注文エラー理由コードの設定を行う。
                    //  [set注文エラー理由コードに渡すパラメタ]
                    //  注文エラー理由コード： 投信注文受付キューParams.getエラーコード()の戻り値
                    l_confirmInterceptor.setOrderErrorReasonCode(
                        l_orderAcceptParams.getErrorMessage());

                    //５） キューレコードに該当する投信注文単位オブジェクトを取得する。
                    //  拡張投信注文マネージャ.get注文単位()をコールして、投信注文単位オブジェクトを取得する。
                    // ［get注文単位に渡すパラメタ］
                    //  証券会社コード： 投信注文受付キューParams.get証券会社コード()の戻り値
                    //  部店コード： 投信注文受付キューParams.get部店コード()の戻り値
                    //  顧客コード： 投信注文受付キューParams.get顧客コード()の戻り値
                    //  識別コード： 投信注文受付キューParams.get識別コード()の戻り値
                    MutualFundOrderUnit l_mfOrderUnit =
                        l_orderManager.getOrderUnit(
                            l_orderAcceptParams.getInstitutionCode(),
                            l_orderAcceptParams.getBranchCode(),
                            l_orderAcceptParams.getOrderRequestNumber());

                    //６）　@注文受付処理を行う。
                    //  －投信注文受付キューParams.get注文受付結果()の戻り値が”1：注文受付完了”の
                    //  場合 投信注文受付UnitService.notify注文受付完了()をコールする。
                    //  [notify注文受付完了に渡すパラメタ]
                    //  投信注文単位： 取得した投信注文単位オブジェクト
                    //  投信受付確定インタセプタ： 生成した投信受付確定インタセプタオブジェクト
                    String l_strAcceptStatus =
                        l_orderAcceptParams.getAcceptStatus();

                    // TransactionCallback生成
                    WEB3MutualOrderAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3MutualOrderAcceptNormalTransactionCallback(
                            l_mfOrderUnit,
                            l_confirmInterceptor,
                            l_orderAcceptParams);

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
							log.debug("口座ロック失敗：" + l_orderAcceptParams.toString());
							continue;
						}
					}
					// それ以外のエラーが発生した場合、
					// キューテーブルの処理区分”9：エラー”をセットし更新する
                    log.debug("__an Exception__ ");
					log.error("一件処理にて例外が発生した場合",l_ex);
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    // do update
                    l_queryProcessor.doUpdateAllQuery(
                        HostXbmfOrderAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_updateParams,
                        l_map);
                }
            }
			m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}@
