head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  投資信託売買注文通知サービス実装クラス(WEB3MutualTradeOrderNotifyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建  (中訊) 新規作成
                   2004/08/24 王蘭芬 (中訊) レビュー
Revesion History : 2007/12/26 武波 (中訊) 仕様変更 No589
Revesion History : 2009/05/13 馮海濤 (中訊) 仕様変更 No642
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;
import webbroker3.mf.data.HostXbmfOrderNotifyRow;
import webbroker3.mf.message.WEB3MutualTradeOrderNotifyRequest;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyService;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * 投資信託売買注文通知サービス実装クラス<BR>
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3MutualTradeOrderNotifyServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualTradeOrderNotifyService
{

    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualTradeOrderNotifyServiceImpl.class);

    /**
     * 投資信託売買注文通知サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（投資信託）売買注文通知」参照。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40567D300345
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //If (l_request == null)
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //１.1）　@クエリプロセッサのインスタンスを取得する。
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();

            //１.2） 投信売買注文通知TransactionCallbackのインスタンスを生成する。
            WEB3MutualTradeOrderNotifyTransactionCallback
                l_mutualTradeOrderNotifyTransactionCallback =
                    new WEB3MutualTradeOrderNotifyTransactionCallback();

            // １.3）　@DBトランザクション処理を実施する。
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_mutualTradeOrderNotifyTransactionCallback);

            //投信売買注文通知レスポンスを生成し、リターンする。

            // 投信売買注文通知リクエスト取得
            WEB3MutualTradeOrderNotifyRequest l_mutualTradeOrderNotifyRequest =
                (WEB3MutualTradeOrderNotifyRequest) l_request;

            //－投信売買注文通知リクエスト.createレスポンス()をコールし、
             // 投信売買注文通知レスポンスオブジェクトを生成する。
            WEB3BackResponse l_backResponse =
                l_mutualTradeOrderNotifyRequest.createResponse();

            //－生成した投信売買注文通知レスポンスを返す。
            log.exiting(STR_METHOD_NAME);
            return l_backResponse;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("クエリプロセッサのインスタンスを取得失敗");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("クエリプロセッサのインスタンスを取得失敗");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    public class WEB3MutualTradeOrderNotifyTransactionCallback
        implements TransactionCallback
    {
        /**
         *  ログユーティリティ<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3MutualTradeOrderNotifyTransactionCallback.class);

        /**
         * (投信売買注文通知TransactionCallback)<BR>
         * デフォルトコンストラクタ<BR>
         * @@roseuid 40A4334D0137
         */
        public WEB3MutualTradeOrderNotifyTransactionCallback()
        {

        }

        /**
         * 投信売買注文通知サービス処理を実施する。 <BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（投信売買注文通知）process」参照。 <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40A4343E01C4
         */

        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            m_log.entering(STR_METHOD_NAME);

            //1.1） 処理対象レコード取得
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();

            //投信注文通知キューテーブルを、行ロック（select for update）にて読み込む。
            String l_strWhere = " status = ?";
            String l_strCondition = null;
            String[] l_strBindValues = new String[1];

            //投信注文通知キューParams.処理区分 = ”0：未処理”
            l_strBindValues[0] = WEB3StatusDef.NOT_DEAL;

            /*検索*/
            List l_lisMFOrderNotifyresults =
                l_queryProcessor.doFindAllQuery(
                    HostXbmfOrderNotifyRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_strBindValues);

            //1.2）　@投信売買注文通知UnitServiceを取得する。
            WEB3MutualTradeOrderNotifyUnitService
                l_mutualTradeOrderNotifyUnitService =
                    (WEB3MutualTradeOrderNotifyUnitService) Services.getService(
                        WEB3MutualTradeOrderNotifyUnitService.class);
            
            int l_intSize = 0;
            if(l_lisMFOrderNotifyresults != null)
            {
                l_intSize = l_lisMFOrderNotifyresults.size();
            }

            //以降の処理は、検索結果の投信注文通知キューテーブルの各行に対して実施する。

            //投信注文通知キューParams
            HostXbmfOrderNotifyParams l_orderNotifyParams = null;

            /*検索*/
            for (int i = 0; i < l_intSize; i++)
            {
                //検索結果の投信注文通知キューテーブルの各行に対して実施する
                l_orderNotifyParams =
                    (HostXbmfOrderNotifyParams) l_lisMFOrderNotifyresults.get(i);

                //証券会社コード取得
                String l_strInstatutionCode =
                    l_orderNotifyParams.getInstitutionCode();

                //部店コード取得
                String l_strBranchCode =
                    l_orderNotifyParams.getBranchCode();

                //識別コード取得
                String l_strOrderRequestNumber =
                    l_orderNotifyParams.getOrderRequestNumber();

                HashMap l_map = new HashMap();         //処理区分を設定
                String l_strUpdateWhere =
                    " institution_code = ? "+          //更新証券会社コード
                    " and branch_code = ? "+           //更新部店コード
                    " and order_request_number = ? ";  //更新識別コード
                String[] l_updateParams = {
                    l_strInstatutionCode,
                    l_strBranchCode,
                    l_strOrderRequestNumber };

                //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                //証券会社コード：投信注文通知キューテーブルParams．get証券会社コード()
                //部店コード：投信注文通知キューテーブルParams．get部店コード()
                //口座コード： 投信注文通知キューテーブルParams．get顧客コード()
                String l_strAccountCode = l_orderNotifyParams.getAccountCode();
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accMgr =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                try
                {
                    l_accMgr.getMainAccount(
                        l_strInstatutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
                }
                catch (WEB3BaseException l_ex)
                {
                    l_map.put("status", WEB3StatusDef.DEALT);

                    l_queryProcessor.doUpdateAllQuery(
                        HostXbmfOrderNotifyRow.TYPE,
                        l_strUpdateWhere,
                        l_updateParams,
                        l_map);

                    continue;
                }

                try
                {
                    //証券会社オブジェクトを取得する。
                    //[引数]
                    //会社コード： 投信注文通知キューテーブルParams.get会社コード()
                    Institution l_strInstitution =
                        l_accMgr.getInstitution(l_strInstatutionCode);

                    try
                    {
                        //get投信銘柄(証券会社 : Institution, 銘柄コード : String)
                        //アイテムの定義
                        //拡張投信銘柄オブジェクトを取得する。
                        //[引数]
                        //証券会社： 取得した証券会社オブジェクト
                        //銘柄コード： 投信注文通知キューParams.get銘柄コード()の戻り値
                        WEB3MutualFundProductManager l_mutualFundProductManager =
                            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                                ProductTypeEnum.MUTUAL_FUND).getProductManager();
                        String l_strProductCode = l_orderNotifyParams.getProductCode();

                        l_mutualFundProductManager.getMutualFundProduct(
                            l_strInstitution, l_strProductCode);
                    }
                    catch (NotFoundException l_ex)
                    {
                        l_map.put("status", WEB3StatusDef.DEALT);

                        l_queryProcessor.doUpdateAllQuery(
                            HostXbmfOrderNotifyRow.TYPE,
                            l_strUpdateWhere,
                            l_updateParams,
                            l_map);

                        continue;
                    }

                    // TransactionCallback生成
                    WEB3MutualTradeOrderNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3MutualTradeOrderNotifyNormalTransactionCallback(
                            l_orderNotifyParams);

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
							log.debug("口座ロック失敗：" + l_orderNotifyParams.toString());
							continue;
						}
					}
                    //それ以外の例外をスローした場合、
                    //処理対象の投信注文通知キューレコード.処理区分に”
                    //9：エラー”をセットし更新する。
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    m_log.error("一件処理にて例外が発生した場合", l_ex);

                    l_queryProcessor.doUpdateAllQuery(
                        HostXbmfOrderNotifyRow.TYPE,
                        l_strUpdateWhere,
                        l_updateParams,
                        l_map);
                }
            }
            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
