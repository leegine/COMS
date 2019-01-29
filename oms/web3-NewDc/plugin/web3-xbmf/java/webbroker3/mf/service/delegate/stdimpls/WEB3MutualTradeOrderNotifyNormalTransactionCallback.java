head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託売買注文通知正常処理一件TransactionCallback(WEB3MutualTradeOrderNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 李志強(日本中訊) 新規作成
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.HashMap;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;
import webbroker3.mf.data.HostXbmfOrderNotifyRow;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * （投資信託売買注文通知正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3MutualTradeOrderNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualTradeOrderNotifyNormalTransactionCallback.class);

    /**
      * 投信注文通知キューParamsオブジェクト。<BR>
      */
    private HostXbmfOrderNotifyParams orderNotifyParams;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_orderNotifyParams - (投信注文通知キューParams)
     */
    public WEB3MutualTradeOrderNotifyNormalTransactionCallback(
        HostXbmfOrderNotifyParams l_orderNotifyParams)
    {
        orderNotifyParams = l_orderNotifyParams;
    }

   /**
     * トランザクション処理を実施する。<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        //1.2）　@投信売買注文通知UnitServiceを取得する。
        WEB3MutualTradeOrderNotifyUnitService
            l_mutualTradeOrderNotifyUnitService =
                (WEB3MutualTradeOrderNotifyUnitService) Services.getService(
                    WEB3MutualTradeOrderNotifyUnitService.class);

        HostXbmfOrderNotifyParams l_orderNotifyParams = orderNotifyParams;
        try
        {
            //1.4）注文処理を行う
            //投信売買注文通知UnitService.notify売買注文通知()をコールする。
            l_mutualTradeOrderNotifyUnitService.notifyTradeOrderNotify(
                l_orderNotifyParams);
        }
		catch (WEB3BaseException l_ex)
		{
			ErrorInfo l_errorInfo = l_ex.getErrorInfo();
			l_errorInfo.setErrorClass(l_ex.getClass().getName());
			throw new DataCallbackException(
				l_ex.getErrorMessage(),
				l_errorInfo);
		}

        HashMap l_map = new HashMap();

        //証券会社コード取得
        String l_strInstatutionCode =
            l_orderNotifyParams.getInstitutionCode();

        //部店コード取得
        String l_strBranchCode =
            l_orderNotifyParams.getBranchCode();

        //識別コード取得
        String l_strOrderRequestNumber =
            l_orderNotifyParams.getOrderRequestNumber();

        String l_strUpdateWhere =
            " institution_code = ? "+          //更新証券会社コード
            " and branch_code = ? "+           //更新部店コード
            " and order_request_number = ? ";  //更新識別コード
        String[] l_updateParams = {
            l_strInstatutionCode,
            l_strBranchCode,
            l_strOrderRequestNumber };

        // 1.5）キューテーブルの処理区分を更新
        //－注文処理が正常終了した場合、
         //処理対象の投信注文通知キューレコード.処理区分に”
          //1：処理済”をセットし更新する。

        l_map.put("status", WEB3StatusDef.DEALT);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        // do update
        l_queryProcessor.doUpdateAllQuery(
            HostXbmfOrderNotifyRow.TYPE,
            l_strUpdateWhere,
            l_updateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}

@
