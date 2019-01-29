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
filename	WEB3RuitoCancelAcceptedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資取消受付サービス (WEB3RuitoCancelAcceptedServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15  王艶芳 (中訊) 新規作成
Revesion History : 2009/01/23  王志葵 (中訊) 仕様変更モデルNo.099
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
import webbroker3.xbruito.data.HostRuitoCancelAcceptParams;
import webbroker3.xbruito.data.HostRuitoCancelAcceptRow;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedService;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedUnitService;

/**
 * 累積投資取消受付サービス実装クラス<BR>
 */
public class WEB3RuitoCancelAcceptedServiceImpl
        implements WEB3RuitoCancelAcceptedService
{
    private static WEB3LogUtility log =
    WEB3LogUtility.getInstance(WEB3RuitoCancelAcceptedServiceImpl.class);

   /**
    * 累積投資取消受付サービス処理を実施する。<BR>
    * <BR>
    * シーケンス図<BR>
    * 「（累投）取消受付」参照。<BR>
    * <BR>
    * １）　@クエリプロセッサのインスタンスを取得する。<BR>
    * <BR>
    * ２） 累投取消受付TransactionCallbackのインスタンスを生成する。<BR>
    * <BR>
    * ３）　@DBトランザクション処理を実施する。<BR>
    * 　@   [doTransactionに渡すパラメタ]<BR>
    * 　@　@ トランザクション属性： TX_CREATE_NEW<BR>
    * 　@　@ トランザクションコールバック：
    * 累投取消受付TransactionCallbackインスタンス<BR>
    * <BR>
    * ４）　@累投取消受付レスポンスを生成し、リターンする。<BR>
    * 　@－累投取消受付リクエスト.createレスポンス()をコールし、<BR>
    *      累投取消受付レスポンスオブジェクトを生成する。<BR>
    * 　@－生成した累投取消受付レスポンスを返す。<BR>
    *
    * @@param l_request
    *  - リクエストデータ <BR>
    *
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 405820C8012B
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
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1)クエリプロセッサのインスタンスを取得する
        QueryProcessor l_queryProcessor = null; //クエリプロセッサ
        WEB3RuitoCancelAcceptedTransactionCallback
            l_ruitoOrderAcceptTransactionCallback = null;
        try
        {
            //DataFindException,DataNetworkException
            l_queryProcessor = Processors.getDefaultProcessor();

            //２）累投注文受付TransactionCallbackのインスタンスを生成する。

            l_ruitoOrderAcceptTransactionCallback =
                    new WEB3RuitoCancelAcceptedTransactionCallback();

            //３）DBトランザクション処理を実施する
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_ruitoOrderAcceptTransactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error[DBサーバとの通信に失敗した]");
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //４）累投注文受付レスポンスを生成し、リターンする。
        WEB3RuitoCancelAcceptRequest l_ruitoCancelAcceptRequest =
            (WEB3RuitoCancelAcceptRequest) l_request;

        WEB3RuitoCancelAcceptResponse l_ruitoCancelAcceptResponse =
            (WEB3RuitoCancelAcceptResponse)
        l_ruitoCancelAcceptRequest.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_ruitoCancelAcceptResponse;
    }

   /**
    * 累積投資取消受付サービス処理を実施する。<BR>
    */
    public class WEB3RuitoCancelAcceptedTransactionCallback
    implements TransactionCallback
    {

      /**
       * 累投売買注文通知UnitServiceImpl <BR>
       * @@roseuid 40BFE5BC0192
       */
        public WEB3RuitoCancelAcceptedTransactionCallback()
        {

        }

      /**
       * 累積投資取消受付サービス処理を実施する。<BR>
       * <BR>
       * シーケンス図「（累投取消受付）process」参照。 <BR>
       * <BR>
       * @@return Object
       * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
       * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
       * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
       * @@roseuid 408893DD0232
       */
        public Object process() throws
            DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //1.1　@処理対象レコード取得
            String l_strWhere = "status = ?";
            String l_strCondition = null;

            Object[] l_objWhere = {WEB3StatusDef.NOT_DEAL};
            log.debug("where status = " + WEB3StatusDef.NOT_DEAL);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult =
                    l_queryProcessor.doFindAllQuery(
                    HostRuitoCancelAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhere);

            //　@累投取消受付UnitServiceを取得する。
            WEB3RuitoCancelAcceptedUnitService l_cancelAcceptUnitService =
                (WEB3RuitoCancelAcceptedUnitService) Services.getService(
                    WEB3RuitoCancelAcceptedUnitService.class);

            //1.2　@累投受付確定インタセプタを生成する。
            WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptedDecisionInterceptor =
                new WEB3RuitoAcceptedDecisionInterceptor();
            //以降の処理は、検索結果の累投取消受付キューテーブルの各行に対して実施する。

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
            WEB3RuitoOrderManager l_ruitoOrderMgr =
                (WEB3RuitoOrderManager) l_tm.getOrderManager();

            l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor
                (l_ruitoAcceptedDecisionInterceptor);

            String l_strAcceptStatus = null;
            HostRuitoCancelAcceptParams l_cancelAcceptParams = null;

            int l_intSize = l_lisSearchResult.size();
            //1.3
            for (int i = 0; i < l_intSize; i++)
            {
                l_cancelAcceptParams =
                    (HostRuitoCancelAcceptParams) l_lisSearchResult.get(i);
                boolean l_blnIsFail = false;
                try
                {
                    //1.3.1 生成した累投受付確定インタセプタオブジェクト.set注文エラー理由コード()を
                    //コールし、注文エラー理由コードの設定を行う。
                    l_ruitoAcceptedDecisionInterceptor.setOrderErrorReasonCode(
                            l_cancelAcceptParams.getErrorCode());

                    //1.3.2 キューレコードに該当する累投注文単位オブジェクトを取得する。
                    RuitoOrderUnit l_ruitoOrderUnit = l_ruitoOrderMgr.getRuitoOrderUnit(
                        l_cancelAcceptParams.getInstitutionCode(),
                        l_cancelAcceptParams.getBranchCode(),
                        l_cancelAcceptParams.getOrderRequestNumber());

                    //1.3.3 取消受付処理を行う。
                    l_strAcceptStatus = l_cancelAcceptParams.getAcceptStatus();
                    log.debug("受付通知区分:l_strAcceptStatus = "
                                    + l_strAcceptStatus);

                    // TransactionCallback生成
                    WEB3RuitoCancelAcceptedNormalTransactionCallback l_transactionCallback =
                        new WEB3RuitoCancelAcceptedNormalTransactionCallback(
                            l_ruitoOrderUnit,
                            l_ruitoAcceptedDecisionInterceptor,
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

                    if (l_ex instanceof WEB3SystemLayerException)
                    {
                        //該当注文存在しない場合、スキップ処理を行う。
                        //処理対象の累投取消受付キューレコード.処理区分を”１：処理済”に更新し、処理1.3へ戻る。
                        WEB3SystemLayerException l_systemLayerException =
                            (WEB3SystemLayerException)l_ex;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(
                            l_systemLayerException.getErrorInfo()))
                        {
                            HashMap l_map = new HashMap();
                            l_map.put("status", WEB3StatusDef.DEALT);

                            String l_strUpdateWhere = " institution_code = ? "
                                + " and branch_code = ? "
                                + " and order_request_number = ? ";
                            String[] l_strArrayUpdateParams = {
                                l_cancelAcceptParams.getInstitutionCode(),
                                l_cancelAcceptParams.getBranchCode(),
                                l_cancelAcceptParams.getOrderRequestNumber()};

                            l_queryProcessor.doUpdateAllQuery(
                                HostRuitoCancelAcceptRow.TYPE,
                                l_strUpdateWhere,
                                l_strArrayUpdateParams,
                                l_map);

                            continue;
                        }
                    }

					// それ以外のエラーが発生した場合
                    log.debug("__an Exception__ ",l_ex);
					log.debug("一件処理にて例外が発生した場合");
	                //1.3.4 キューテーブルの処理区分”9：エラー”をセットし更新する
    	            HashMap l_map = new HashMap();
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    String l_strUpdateWhere = " institution_code = ? "
                            + " and branch_code = ? "
                            + " and order_request_number = ?";
                    String[] l_strArrayUpdateParams = {
                            l_cancelAcceptParams.getInstitutionCode(),
                            l_cancelAcceptParams.getBranchCode(),
                            l_cancelAcceptParams.getOrderRequestNumber() };
                    l_queryProcessor.doUpdateAllQuery(
                        HostRuitoCancelAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strArrayUpdateParams,
                        l_map);
                }
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
