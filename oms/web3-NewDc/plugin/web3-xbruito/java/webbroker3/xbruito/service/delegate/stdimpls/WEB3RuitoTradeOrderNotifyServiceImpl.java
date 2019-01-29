head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資売買注文通知サービス実装クラス(WEB3RuitoTradeOrderNotifyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 杜森 (中訊) 新規作成
Revesion History : 2007/12/26 武波 (中訊) 仕様変更 No097
Revesion History : 2008/01/09 武井 (SRA) 仕様変更 No098
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;

import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyService;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyUnitService;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyRequest;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyResponse;
import webbroker3.xbruito.data.HostRuitoOrderNotifyRow;
import webbroker3.xbruito.data.HostRuitoOrderNotifyParams;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.WEB3RuitoTradedOrderNotifyDecisionInterceptor;

/**
 * 累積投資売買注文通知サービス実装クラス <BR>
 */
public class WEB3RuitoTradeOrderNotifyServiceImpl
    implements WEB3RuitoTradeOrderNotifyService
{
       /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3RuitoTradeOrderNotifyServiceImpl.class);

    final String STR_METHOD_NAME =
        "submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)";

    /**
     * @@roseuid 40B3EE9800AB
     */
    public WEB3RuitoTradeOrderNotifyServiceImpl()
    {

    }
   /**
    * 累積投資売買注文通知サービス処理を実施する。<BR>
    * <BR>
    * シーケンス図「（累積投資）売買注文通知」参照。<BR>
    * <BR>
    * @@param l_request - リクエストデータ <BR>
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 405A4E0300E9
    */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";

        log.entering(STR_METHOD_NAME);

        if(l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当パラメータにNull値は設定できません。");
        }
        WEB3RuitoDealingOrderNotifyRequest l_execOrderNotifyRequest =
            (WEB3RuitoDealingOrderNotifyRequest) l_request;

        //createレスポンス( )
        WEB3RuitoDealingOrderNotifyResponse l_execEndNotifyResponse =
            (WEB3RuitoDealingOrderNotifyResponse)
                l_execOrderNotifyRequest.createResponse();

        // 累投売買注文通知を行う
        try
        {
            //クエリプロセッサのインスタンスを取得する。
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            WEB3BaseException l_baseExp =
                (WEB3BaseException) l_qp.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    new WEB3RuitoTradeOrderNotifyTransactionCallback());
            if (l_baseExp != null)
            {
                throw l_baseExp;
            }
        }
        catch (DataException l_ex)
        {
            log.error("_DataException_");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                "累投売買注文通知キューテーブルへのアクセスに失敗しました。",
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_execEndNotifyResponse;

    }

    public class WEB3RuitoTradeOrderNotifyTransactionCallback
        implements TransactionCallback
    {

        /**
         * デフォルトコンストラクタ <BR>
         * @@roseuid 40876C14028B
         */
        public WEB3RuitoTradeOrderNotifyTransactionCallback()
        {

        }

        /**
         * 注文受付処理を実施する。<BR>
         * <BR>
         * シーケンス図「（累投注文通知）process」参照。<BR>
         * <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 40876C10002A
         */
        public Object process()
            throws
                DataNetworkException,
                DataQueryException,
                DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            String l_strWhere = "status=?";
            String l_strCondition = null;

            log.entering(STR_METHOD_NAME);
            // 1.1 処理対象レコード取得
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisHostRuitoOrderNotify = l_qp.doFindAllQuery(
                HostRuitoOrderNotifyRow.TYPE,
                l_strWhere,
                l_strCondition,
                new Object[] { WEB3StatusDef.NOT_DEAL });
            // 1.2　@累投売買注文通知UnitServiceを取得する。
            WEB3RuitoTradeOrderNotifyUnitService l_ruitoUnitService =
                (WEB3RuitoTradeOrderNotifyUnitService) Services.getService(
                    WEB3RuitoTradeOrderNotifyUnitService.class);
            int l_size = l_lisHostRuitoOrderNotify.size();

            // 1.3　@累投売買注文通知確定インタセプタを生成する。
            WEB3RuitoTradedOrderNotifyDecisionInterceptor l_ruitoInterceptor =
                new WEB3RuitoTradedOrderNotifyDecisionInterceptor();
            //1.4 ループ処理
            for (int i = 0; i < l_size; i++)
            {
                HostRuitoOrderNotifyParams l_orderNotifyParams =
                    (HostRuitoOrderNotifyParams) l_lisHostRuitoOrderNotify.get(i);
                HashMap l_map = new HashMap();
                String l_strUpdateWhere = " institution_code = ? "
                        + " and branch_code = ? " + " and order_request_number = ? ";
                String[] l_strUpdateWhereValues = {
                        l_orderNotifyParams.getInstitutionCode(),
                        l_orderNotifyParams.getBranchCode(),
                        l_orderNotifyParams.getOrderRequestNumber() };

                try
                {
                    //getInstitution(証券会社コード)
                    // 会社コード： 累投注文通知キューテーブルParams.get証券会社コード()
                    Institution l_institution = null;
                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                    WEB3GentradeAccountManager l_accMgr =
                        (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                    l_institution = l_accMgr.getInstitution(l_orderNotifyParams.getInstitutionCode());

                    try
                    {
                        //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                        //証券会社コード：投信注文通知キューテーブルParams．get証券会社コード()
                        //部店コード：投信注文通知キューテーブルParams．get部店コード()
                        //口座コード： 投信注文通知キューテーブルParams．get顧客コード()
                        l_accMgr.getMainAccount(
                            l_orderNotifyParams.getInstitutionCode(),
                            l_orderNotifyParams.getBranchCode(),
                            l_orderNotifyParams.getAccountCode());
                    }
                    catch (WEB3BaseException l_ex)
                    {
                        l_map.put("status", WEB3StatusDef.DEALT);

                        l_qp.doUpdateAllQuery(
                            HostRuitoOrderNotifyRow.TYPE,
                            l_strUpdateWhere,
                            l_strUpdateWhereValues,
                            l_map);

                        continue;
                    }

                    try
                    {
                        //get累投銘柄（withコースandプラン）(Institution, String, String)
                        //[引数]
                        //  証券会社： 取得した証券会社オブジェクト
                        //  コース： 累投注文通知キューParams.getコース()の戻り値
                        //  プラン： 累投注文通知キューParams.getプラン()の戻り値
                        TradingModule l_tradingModule =
                            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
                        WEB3RuitoProductManager l_ruitoProductManager =
                            (WEB3RuitoProductManager)l_tradingModule.getProductManager();

                        l_ruitoProductManager.getRuitoProductWithCoursePlan(
                            l_institution,
                            l_orderNotifyParams.getCourse(),
                            l_orderNotifyParams.getPlan());
                    }
                    catch (WEB3BaseRuntimeException l_ex)
                    {
                        if(WEB3ErrorCatalog.SYSTEM_ERROR_80006.equals(l_ex.getErrorInfo())){
                            l_map.put("status", WEB3StatusDef.DEALT);

                            l_qp.doUpdateAllQuery(
                                HostRuitoOrderNotifyRow.TYPE,
                                l_strUpdateWhere,
                                l_strUpdateWhereValues,
                                l_map);

                            continue;
                        }
                    }

                    // TransactionCallback生成
                    WEB3RuitoTradeOrderNotifyNormalTransactionCallback l_transactionCallback =
                    new WEB3RuitoTradeOrderNotifyNormalTransactionCallback(
                        l_orderNotifyParams,
                        l_ruitoInterceptor);

                    // doTransaction()
                    l_qp.doTransaction(
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
                    log.debug("__an Exception__ ");
                    log.error("一件処理にて例外が発生した場合",l_ex);
                    //処理対象の累投注文通知キューレコード.処理区分に”9：エラー”をセットし更新する
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);

                    //1.4.2 キューテーブルに処理済を更新
                    l_qp.doUpdateAllQuery(
                    HostRuitoOrderNotifyRow.TYPE,
                    l_strUpdateWhere,
                    l_strUpdateWhereValues,
                    l_map);
                }
            }           
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
