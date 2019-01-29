head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMRFOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 累積投資MRF注文受付サービス実装クラス(WEB3RuitoMRFOrderAcceptServiceImpl)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/06/14 韋念瓊 (中訊) 新規作成
 */
package webbroker3.xbruito.service.delegate.stdimpls;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.data.HostMrfOrderAcceptParams;
import webbroker3.xbruito.data.HostMrfOrderAcceptRow;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptService;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptUnitService;

/**
 * 累積投資MRF注文受付サービス実装クラス<BR>
 */
public class WEB3RuitoMRFOrderAcceptServiceImpl implements
        WEB3RuitoMRFOrderAcceptService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3RuitoMRFOrderAcceptServiceImpl.class);

    /**
     * 累積投資MRF注文受付サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（累投）MRF注文受付」参照。<BR>
     * <BR>
     * １）　@クエリプロセッサのインスタンスを取得する。<BR>
     * <BR>
     * ２） 累投MRF注文受付TransactionCallbackの<BR>
     *    インスタンスを生成する。<BR>
     * <BR>
     * ３）　@DBトランザクション処理を実施する。<BR>
     *  　@[doTransactionに渡すパラメタ]<BR>
     * 　@ トランザクション属性： TX_CREATE_NEW<BR>
     * 　@ トランザクションコールバック： <BR>
     *    累投MRF注文受付TransactionCallbackインスタンス<BR>
     * <BR>
     * ４）　@累投MRF注文受付レスポンスを生成し、リターンする。<BR>
     * 　@－累投MRF注文受付リクエスト.createレスポンス()<BR>
     *    をコールし、累投MRF注文受付レスポンスオブジェクト<BR>
     *    を生成する。<BR>
     * 　@－生成した累投MRF注文受付レスポンスを返す。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405814D1013B
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        // 1)クエリプロセッサのインスタンスを取得する
        QueryProcessor l_queryProcessor = null; //クエリプロセッサ
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //２） 累投MRF注文受付TransactionCallbackのインスタンスを生成する。
        WEB3RuitoMRFOrderAcceptTransactionCallback l_ruitoMRFOrderAcceptTransactionCallback = null;
        l_ruitoMRFOrderAcceptTransactionCallback = new WEB3RuitoMRFOrderAcceptTransactionCallback();

        // 3)DBトランザクション処理を実施する
        try
        {
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_ruitoMRFOrderAcceptTransactionCallback);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //４）　@累投MRF注文受付レスポンスを生成し、リターンする。
        WEB3RuitoMRFOrderAcceptRequest l_ruitoMRFOrderAcceptRequest = (WEB3RuitoMRFOrderAcceptRequest) l_request;

        WEB3RuitoMRFOrderAcceptResponse l_ruitoMRFOrderAcceptResponse = (WEB3RuitoMRFOrderAcceptResponse) l_ruitoMRFOrderAcceptRequest
                .createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_ruitoMRFOrderAcceptResponse;

    }

    /**
     * 累積投資MRF注文受付サービス処理を実施する。<BR>
     */
    public class WEB3RuitoMRFOrderAcceptTransactionCallback implements
            TransactionCallback
    {

        /**
         * 累積投資MRF注文受付サービス処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（累投MRF注文受付）process」参照。<BR>
         * <BR>
         * １）　@処理対象レコード取得<BR>
         * 　@MRF注文受付キューテーブルを、<BR>
         *   行ロック（select for update）にて読み込む。<BR>
         * <BR>
         * 　@［検索条件］<BR>
         * 　@処理区分 = ”0：未処理”<BR>
         * 　@［condition］<BR>
         * 　@”for update”<BR>
         * <BR>
         * ２）　@累投MRF注文受付UnitServiceを取得する。<BR>
         * <BR>
         * ３）　@累投受付確定インタセプタを生成する。<BR>
         * <BR>
         *   以降の処理は、<BR>
         *   検索結果の累投MRF注文受付キューテーブルの<BR>
         *   各行に対して実施する。<BR>
         * <BR>
         * ４）　@生成した累投受付確定インタセプタオブジェクト.<BR>
         *    set注文エラー理由コード()を<BR>
         * 　@コールし、注文エラー理由コードの設定を行う。<BR>
         * 　@[set注文エラー理由コードに渡すパラメタ]<BR>
         * 　@注文エラー理由コード： <BR>
         *    MRF注文受付キューParams.getエラーコード()の戻り値<BR>
         * <BR>
         * ５） キューレコードに該当する累投注文単位オブジェクト<BR>
         *    を取得する。<BR>
         * 　@ 拡張累投注文マネージャ.get注文単位()をコールして、<BR>
         *    累投注文単位オブジェクトを取得する。<BR>
         *  　@［get注文単位に渡すパラメタ］<BR>
         * 　@　@証券会社コード：<BR>
         *      MRF注文受付キューParams.get証券会社コード()の戻り値<BR>
         * 　@　@部店コード： <BR>
         *     MRF注文受付キューParams.get部店コード()の戻り値<BR>
         * 　@　@顧客コード： <BR>
         *      MRF注文受付キューParams.get顧客コード()の戻り値<BR>
         * 　@　@識別コード： <BR>
         *      MRF注文受付キューParams.get識別コード()の戻り値<BR>
         * <BR>
         * ６）　@注文受付処理を行う。<BR>
         * 　@－生成した累投受付確定インタセプタオブジェクト.setエラーコード()<BR>
         *     をコールし、エラーコードの設定を行う。<BR>
         * 　@ 　@[setエラーコードに渡すパラメタ]<BR>
         * 　@ 　@エラーコード： <BR>
         *      MRF注文受付キューParams.getエラーコード()の戻り値<BR>
         * <BR>
         * 　@ －MRF注文受付キューParams.get受付通知区分()<BR>
         *     の戻り値が”1：注文受付完了”の場合、<BR>
         * 　@　@累投MRF注文受付UnitService.notify注文受付完了()<BR>
         *     をコールする。<BR>
         * 　@　@[notify注文受付完了に渡すパラメタ]<BR>
         * 　@　@累投注文単位： 取得した累投注文単位オブジェクト<BR>
         * 　@　@累投受付確定インタセプタ： <BR>
         *     生成した累投受付確定インタセプタオブジェクト<BR>
         * <BR>
         * 　@ －MRF注文受付キューParams.get受付通知区分()<BR>
         *     の戻り値が”1：注文受付完了”でない場合、<BR>
         * 　@ 累投MRF注文受付UnitService.notify注文受付失敗()<BR>
         *     をコールする。<BR>
         * 　@  [notify注文受付失敗に渡すパラメタ]<BR>
         * 　@  累投注文単位： 取得した累投注文単位オブジェクト<BR>
         * 　@　@累投受付確定インタセプタ： <BR>
         *     生成した累投受付確定インタセプタオブジェクト<BR>
         * <BR>
         * ７）　@キューテーブルの処理区分を更新<BR>
         *    正常終了した場合、<BR>
         * 処理対象のMRF注文受付キューレコード.処理区分に ”1：処理済”をセットし更新する。<BR>
         * <BR>
         *    例外をスローした場合、<BR>
         * 処理対象のMRF注文受付キューレコード.処理区分に”9：エラー”をセットし更新する。 <BR>
         * 但し、サービスインタセプタonCallで例外発生時は、処理区分を更新しない<BR>
         * <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 408769A200E9
         */
        public Object process() throws DataNetworkException,
                DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "Object process()";
            log.entering(STR_METHOD_NAME);

            //１）　@処理対象レコード取得
            String l_strWhere = "status = ?";
            String l_strCondition = null;
            Object[] l_objWhere = { WEB3StatusDef.NOT_DEAL };
            log.debug("where status = " + WEB3StatusDef.NOT_DEAL);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
                HostMrfOrderAcceptRow.TYPE, l_strWhere, null, l_strCondition,
                l_objWhere);

            //２）　@累投MRF注文受付UnitServiceを取得する。
            WEB3RuitoMRFOrderAcceptUnitService l_unitService =
                (WEB3RuitoMRFOrderAcceptUnitService) Services.getService(WEB3RuitoMRFOrderAcceptUnitService.class);
            //以降の処理は、検索結果の累投MRF取消受付キューテーブルの各行に対して実施する。

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
            WEB3RuitoOrderManager l_ruitoOrderMgr = (WEB3RuitoOrderManager) l_tm.getOrderManager();

            String l_strAcceptStatus;
            HostMrfOrderAcceptParams l_params = null;
            int i = 0;
            int l_intSize = 0;
            l_intSize = l_lisSearchResult.size();
            log.debug("Output the number of search records = " + l_intSize);
            for (i = 0; i < l_intSize; i++)
            {
                log.debug("Enter for path.");

                l_params = (HostMrfOrderAcceptParams) l_lisSearchResult.get(i);

                //３）　@累投受付確定インタセプタを生成する。
                WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptedDecisionInterceptor =
                    new WEB3RuitoAcceptedDecisionInterceptor();
                l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(l_ruitoAcceptedDecisionInterceptor);
                boolean l_blnIsFail = false;
                try
                {
                    // ４）　@生成した累投受付確定
                    l_ruitoAcceptedDecisionInterceptor
                            .setOrderErrorReasonCode(l_params.error_code);

                    //５） キューレコードに該当する累投注文単位オブジェクトを取得する。

                    log.debug("HostMrfOrderAccept.BranchCode = "
                            + l_params.getBranchCode());
                    log.debug("HostMrfOrderAccept.RequstOrderNumber = "
                            + l_params.getOrderRequestNumber());

                    RuitoOrderUnit l_ruitoOrderUnit = null;
                    //NotFoundException
                    l_ruitoOrderUnit = l_ruitoOrderMgr.getRuitoOrderUnit(
                        l_params.getInstitutionCode(),
                        l_params.getBranchCode(),
                        l_params.getOrderRequestNumber());

                    // TransactionCallback生成
                    WEB3RuitoMRFOrderAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3RuitoMRFOrderAcceptNormalTransactionCallback(
                            l_ruitoOrderUnit,
                            l_ruitoAcceptedDecisionInterceptor,
                            l_params);

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
							log.debug("口座ロック失敗：" + l_params.toString());
							continue;
						}
					}
					//それ以外のエラーが発生した場合
                    log.debug("__an Exception__ ",l_ex);
					log.debug("一件処理にて例外が発生した場合");
         	        // ７）　@キューテーブルの処理区分を更新
            		String l_strUpdateWhere = " institution_code = ?"
                        + " and branch_code = ?"
                        + " and order_request_number = ?";
                	String[] l_strArrayUpdateParams = {
                        l_params.getInstitutionCode(),
                        l_params.getBranchCode(),
                        l_params.getOrderRequestNumber() };
                	// 処理対象のMRF取消受付キューレコード.処理区分”9：エラー”をセットし更新する
                	HashMap l_map = new HashMap();
                    l_map.put(
                        "status", WEB3StatusDef.DATA_ERROR);

                    // ７）　@処理対象の取消受付キューレコード.処理区分を設定”
                    l_queryProcessor.doUpdateAllQuery(
                        HostMrfOrderAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strArrayUpdateParams,
                        l_map);
                }
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * デフォルトコンストラクタ。<BR>
         * @@roseuid 40877B150128
         */
        public WEB3RuitoMRFOrderAcceptTransactionCallback()
        {

        }
    }
}@
