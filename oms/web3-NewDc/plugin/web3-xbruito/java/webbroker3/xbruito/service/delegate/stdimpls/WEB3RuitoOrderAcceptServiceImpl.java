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
filename	WEB3RuitoOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資注文受付サービス実装クラス (WEB3RuitoOrderAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  周勇 (中訊) 新規作成
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
import webbroker3.xbruito.data.HostRuitoOrderAcceptParams;
import webbroker3.xbruito.data.HostRuitoOrderAcceptRow;
import webbroker3.xbruito.message.WEB3RuitoOrderAcceptRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptService;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptUnitService;
/**
 * 累積投資注文受付サービス実装クラス<BR>
 */
public class WEB3RuitoOrderAcceptServiceImpl
    implements WEB3RuitoOrderAcceptService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderAcceptServiceImpl.class);
    /**
     * 累積投資注文受付サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（累投）注文受付」参照。<BR>
     * <BR>
     * １）　@クエリプロセッサのインスタンスを取得する。<BR>
     * <BR>
     * ２） 累投注文受付TransactionCallbackのインスタンスを生成する。<BR>
     * <BR>
     * ３）　@DBトランザクション処理を実施する。<BR>
     * 　@[doTransactionに渡すパラメタ]<BR>
     * 　@　@トランザクション属性： TX_CREATE_NEW<BR>
     * 　@　@トランザクションコールバック： <BR>
     *         累投注文受付TransactionCallbackインスタンス<BR>
     * <BR>
     * ４）　@累投注文受付レスポンスを生成し、リターンする。<BR>
     * 　@－累投注文受付リクエスト.createレスポンス()をコールし、<BR>
     *       累投注文受付レスポンスオブジェクトを生成する。<BR>
     * 　@－生成した累投注文受付レスポンスを返す。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405A49A60260
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
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
        //クエリプロセッサのインスタンスを取得する

        //クエリプロセッサ
        QueryProcessor l_queryProcessor = null;
        try
        {
            //累投注文受付TransactionCallbackのインスタンスを生成する
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW,
                    new WEB3RuitoOrderAcceptTransactionCallback());
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //累投注文受付レスポンスを生成し、リターンする
        WEB3RuitoOrderAcceptRequest l_ruitoOrderAcceptRequest =
            (WEB3RuitoOrderAcceptRequest) l_request;
        WEB3BackResponse l_backResponse =
            l_ruitoOrderAcceptRequest.createResponse();
        return l_backResponse;
    }
    /**
     * 累積投資注文受付サービス処理を実施する。<BR>
     */
    public class WEB3RuitoOrderAcceptTransactionCallback
        implements TransactionCallback
    {
        /**
         * デフォルトコンストラクタ<BR>
         * @@roseuid 4088F41D034B
         */
        public WEB3RuitoOrderAcceptTransactionCallback()
        {
        }
        /**
         * 累積投資注文受付サービス処理を実施する。<BR>
         * <BR>
         * シーケンス図「（累投注文受付）process」参照。 <BR>
         * <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 4088F45400BA
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //1.1　@処理対象レコード取得
            QueryProcessor l_processorObject =
                Processors.getDefaultProcessor();

            String l_strWhere = " status = ? ";
            String l_strCondition = null;
            String[] l_strBindValues = new String[1];
            l_strBindValues[0] = WEB3StatusDef.NOT_DEAL;
            List l_ruitoOrderAcceptresults =
                l_processorObject.doFindAllQuery(
                    HostRuitoOrderAcceptRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_strBindValues);

            String l_strInstatutionCode = null;     //更新証券会社コード
            String l_strOrderRequestNumber = null;  //更新識別コード
            String l_strBranchCode = null;          //更新部店コード
            RuitoOrderUnit l_ruitoOrderUnit = null; //累投注文単位
            String l_strAcceptStatus = null;        //受付通知区分
            String l_strErrorCode = null;           //エラーコード

            //1.2　@累投注文受付UnitServiceを取得する。
            WEB3RuitoOrderAcceptUnitService l_ruitoOrderAcceptUnitService =
                (WEB3RuitoOrderAcceptUnitService) Services.getService(
                    WEB3RuitoOrderAcceptUnitService.class);

            //1.3　@累投受付確定インタセプタを生成する。
            WEB3RuitoAcceptedDecisionInterceptor
                l_ruitoAcceptedDecisionInterceptor =
                    new WEB3RuitoAcceptedDecisionInterceptor();

            int l_intSize = 0;
            if(l_ruitoOrderAcceptresults != null)
            {
                l_intSize = l_ruitoOrderAcceptresults.size();
            }

            //以降の処理は、検索結果の累投注文受付キューテーブルの<BR>
              //各行に対して実施する。<BR>

            //拡張累投注文マネージャ的取得
            FinApp l_finApp =
                (FinApp) Services.getService(FinApp.class);
            WEB3RuitoOrderManager l_web3RuitoOrderManager =
                (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getOrderManager();

            //累投注文受付キューParams
            HostRuitoOrderAcceptParams l_hostRuitoOrderAcceptParams = null;

            //1.4
            for (int i = 0; i < l_intSize; i++)
            {
                l_hostRuitoOrderAcceptParams =
                    (HostRuitoOrderAcceptParams) l_ruitoOrderAcceptresults.get(i);

                //証券会社コード的取得
                l_strInstatutionCode =
                    l_hostRuitoOrderAcceptParams.getInstitutionCode();

                //部店コード的取得
                l_strBranchCode =
                    l_hostRuitoOrderAcceptParams.getBranchCode();

                //識別コード的取得
                l_strOrderRequestNumber =
                    l_hostRuitoOrderAcceptParams.getOrderRequestNumber();

                //エラーコード的取得
                l_strErrorCode =
                    l_hostRuitoOrderAcceptParams.getErrorMessage();

                //受付通知区分的取得
                l_strAcceptStatus =
                    l_hostRuitoOrderAcceptParams.getAcceptStatus();
                try
                {
                    //1.4.1　@生成した累投受付確定インタセプタオ
                     //ブジェクト.set注文エラー理由コード()を<BR>
                      //コールし、注文エラー理由コードの設定を行う。
                    l_ruitoAcceptedDecisionInterceptor.setOrderErrorReasonCode(
                        l_strErrorCode);

                   //1.4.2 キューレコードに該当する累投注文単位オブジェクトを取得する
                    //拡張累投注文マネージャ.get注文単位()をコールして、<BR>
                     //累投注文単位オブジェクトを取得する。
                    l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
                        l_ruitoAcceptedDecisionInterceptor);
                    l_ruitoOrderUnit =
                        l_web3RuitoOrderManager.getRuitoOrderUnit(
                            l_strInstatutionCode,
                            l_strBranchCode,
                            l_strOrderRequestNumber);

                     //1.4.3　@注文受付処理を行う。
                    // TransactionCallback生成
                    WEB3RuitoOrderAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3RuitoOrderAcceptNormalTransactionCallback(
                            l_ruitoOrderUnit,
                            l_ruitoAcceptedDecisionInterceptor,
                            l_hostRuitoOrderAcceptParams);

                    // doTransaction()
                    l_processorObject.doTransaction(
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
							log.debug("口座ロック失敗：" + l_hostRuitoOrderAcceptParams.toString());
							continue;
						}
					}
					// それ以外のエラーが発生した場合
                    log.debug("__an Exception__ ",l_ex);
					log.debug("一件処理にて例外が発生した場合");
                	// 1.4.4　@キューテーブルの処理区分を更新
                	String l_strUpdateWhere =
                    	" institution_code = ?" +
                    	" and branch_code = ?" +
                    	" and order_request_number = ?";
                	String[] l_strArrayUpdateParams =
                    	{   l_strInstatutionCode,
                        	l_strBranchCode,
                        	l_strOrderRequestNumber};
                	// 処理対象のMRF取消受付キューレコード.処理区分”9：エラー”をセットし更新する
                	HashMap l_map = new HashMap();
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    // do update
                    l_processorObject.doUpdateAllQuery(
                        HostRuitoOrderAcceptParams.TYPE,
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
