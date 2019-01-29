head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨MMF注文受付サービスImpl(WEB3MutualFrgnMmfOrderAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 柴双紅 (中訊) 新規作成 (モデル534)
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
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
import webbroker3.mf.data.HostFrgnMmfOrderAcceptParams;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptRow;
import webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptResponse;
import webbroker3.mf.service.delegate.WEB3MutualFrgnMmfOrderAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外貨MMF注文受付サービスImpl)<BR>
 * 外貨MMF注文受付サービス実装クラス<BR>
 * 
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3MutualFrgnMmfOrderAcceptServiceImpl extends WEB3MutualClientRequestService implements WEB3MutualFrgnMmfOrderAcceptService 
{

    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFrgnMmfOrderAcceptServiceImpl.class);

    /**
     * @@roseuid 45C440D700B2
     */
    public WEB3MutualFrgnMmfOrderAcceptServiceImpl() 
    {
     
    }

    /**
     * 外貨MMF注文受付サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図「（外貨MMF）注文受付」参照。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B9C837011B
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_processor = Processors.getDefaultProcessor();

            //外貨MMF注文受付TransactionCallback( )
            WB3MutualFrgnMmfOrderAcceptTransactionCallback l_callback =
                new WB3MutualFrgnMmfOrderAcceptTransactionCallback();

            //doTransaction(int, TransactionCallback)
            //[doTransactionに渡すパラメタ]
            //　@　@トランザクション属性： TX_CREATE_NEW
            //　@　@トランザクションコールバック： 外貨MMF注文受付TransactionCallbackインスタンス
            l_processor.doTransaction(TransactionalInterceptor.TX_CREATE_NEW, l_callback);
        }
        catch(DataException l_exc)
        {
            log.error("DBへのアクセスに失敗しました。",l_exc);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }

        //createレスポンス( )
        WEB3MutualFrgnMmfOrderAcceptResponse l_response =
            (WEB3MutualFrgnMmfOrderAcceptResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (外貨MMF注文受付TransactionCallback)<BR>
     * 外貨MMF注文受付サービス処理を実施する。<BR>
     */
    public class WB3MutualFrgnMmfOrderAcceptTransactionCallback implements TransactionCallback
    {

        /**
         * (外貨MMF注文受付TransactionCallback)<BR>
         * デフォルトコンストラクタ<BR>
         * @@roseuid 45B9C87F033E
         */
        public WB3MutualFrgnMmfOrderAcceptTransactionCallback()
        {
         
        }

        /**
         * 外貨MMF注文受付サービス処理を実施する。 <BR>
         * <BR>
         * シーケンス図 「（外貨MMF注文受付）process」参照。 <BR>
         * @@return Object
         * @@throws DataNetworkException, DataQueryException, DataCallbackException
         * @@roseuid 45B9C8AF034E
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            //処理対象レコード取得 外貨MMF注文受付キューテーブル
            String l_strWhere = " status = ? ";
            Object[] l_objWhereValues = {WEB3StatusDef.NOT_DEAL};
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            /*検索*/
            List l_lisMFOrderAccepResult =
                l_queryProcessor.doFindAllQuery(
                    HostFrgnMmfOrderAcceptRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);

            //投信注文受付UnitServiceを取得する
            WEB3MutualOrderAcceptUnitService l_orderAcceptUnitService =
                (WEB3MutualOrderAcceptUnitService)Services.getService(
                    WEB3MutualOrderAcceptUnitService.class);

            //投信受付確定インタセプタ( )
            WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor =
                new WEB3MutualFundAcceptConfirmInterceptor();

            //＜LOOP処理＞検索結果の外貨MMF注文受付キューテーブルの各行に対して実施する
            int l_intResultLength = 0;
            if (l_lisMFOrderAccepResult != null && l_lisMFOrderAccepResult.size() != 0)
            {
                l_intResultLength = l_lisMFOrderAccepResult.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();

            HostFrgnMmfOrderAcceptParams l_orderAcceptParams = null;
            for (int i = 0; i < l_intResultLength; i++)
            {
                // 外貨MMF注文受付キューParamsの取得
                l_orderAcceptParams =
                    (HostFrgnMmfOrderAcceptParams)l_lisMFOrderAccepResult.get(i);

                HashMap l_map = new HashMap();
                String l_strUpdateWhere =
                    " institution_code = ? " +
                    " and branch_code = ? " +
                    " and order_request_number = ? ";
                String[] l_strUpdateParams =
                    {l_orderAcceptParams.getInstitutionCode(),
                     l_orderAcceptParams.getBranchCode(),
                     l_orderAcceptParams.getOrderRequestNumber()};

                try
                {
                    //set注文エラー理由コード(String)
                    //[set注文エラー理由コードに渡すパラメタ]
                    //　@注文エラー理由コード： 外貨MMF注文受付キューParams.getエラーコード()の戻り値
                    l_confirmInterceptor.setOrderErrorReasonCode(
                        l_orderAcceptParams.getErrorMessage());

                    //get注文単位(String, String, String)
                    //［get注文単位に渡すパラメタ］
                    //　@　@証券会社コード： 外貨MMF注文受付キューParams.get証券会社コード()の戻り値
                    //　@　@部店コード： 外貨MMF注文受付キューParams.get部店コード()の戻り値
                    //　@  識別コード： 外貨MMF注文受付キューParams.get識別コード()の戻り値
                    MutualFundOrderUnit l_mutualFundOrderUnit =
                        l_orderManager.getOrderUnit(
                            l_orderAcceptParams.getInstitutionCode(),
                            l_orderAcceptParams.getBranchCode(),
                            l_orderAcceptParams.getOrderRequestNumber());

                    //外貨MMF受付正常処理一件TransactionCallback(
                    // MutualFundOrderUnit, 投信受付確定インタセプタ, 外貨MMF注文受付キューParams)
                    WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback l_normalTransactionCallback =
                        new WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback(
                            l_mutualFundOrderUnit,
                            l_confirmInterceptor,
                            l_orderAcceptParams);

                    //doTransaction(int, TransactionCallback)
                    //[doTransactionに渡すパラメタ]
                    //　@　@トランザクション属性： TX_CREATE_NEW
                    //　@　@トランザクションコールバック：
                    //       外貨MMF注文受付正常処理一件TransactionCallbackインスタンス
                    l_queryProcessor.doTransaction(
                        TransactionalInterceptor.TX_CREATE_NEW,
                        l_normalTransactionCallback);
                }
                catch(Exception l_exc)
                {
                    //口座ロックに失敗してエラーが発生した場合、
                    //処理対象キューを更新しない。
                    if(l_exc instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException)l_exc;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
                        {
                            continue;
                        }
                    }

                    // それ以外のエラーが発生した場合、
                    // キューテーブルの処理区分”9：エラー”をセットし更新する
                    log.error("一件処理にて例外が発生した場合", l_exc);
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    // do update
                    l_queryProcessor.doUpdateAllQuery(
                        HostFrgnMmfOrderAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strUpdateParams,
                        l_map);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
