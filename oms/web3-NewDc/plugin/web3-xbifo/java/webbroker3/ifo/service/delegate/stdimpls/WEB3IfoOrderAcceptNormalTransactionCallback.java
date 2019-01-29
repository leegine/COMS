head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文受付一件TransactionCallback(WEB3IfoOrderAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/11 李志強(日本中訊) 新規作成
Revesion History : 2007/01/25 周捷 (中訊) 仕様変更 モデル605
*/

package webbroker3.ifo.service.delegate.stdimpls;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * （先物OP注文受付一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3IfoOrderAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOrderAcceptNormalTransactionCallback.class);

    /**
      * 先物OP注文受付キューParamsオブジェクト。<BR>
      */
    private HostFotypeOrderAcceptParams hostFotypeOrderAcceptParams;
        
    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostFotypeOrderAcceptParams - (先物OP注文受付キューParams)
     */
    public WEB3IfoOrderAcceptNormalTransactionCallback(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
    {
        hostFotypeOrderAcceptParams = l_hostFotypeOrderAcceptParams;
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
                         
        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

        WEB3IfoOrderAcceptUnitService l_orderAcceptUnitService =
            (WEB3IfoOrderAcceptUnitService)Services.getService(
                WEB3IfoOrderAcceptUnitService.class);

        String l_strAcceptStatus = this.hostFotypeOrderAcceptParams.getAcceptStatus();

        try
        {
            //（　@分岐フロー：キュー.注文受付結果区分　@==　@”注文受付完了”
            //　@または　@”エラー”　@）　@の場合
            if (WEB3AcceptStatusDef.OVER.equals(l_strAcceptStatus)
                || WEB3AcceptStatusDef.ERROR.equals(l_strAcceptStatus))
            {
                //notify注文受付(先物OP注文受付キューParams)
                l_orderAcceptUnitService.notifyOrderAccept(this.hostFotypeOrderAcceptParams);
            }
            //（　@分岐フロー：キュー.注文受付結果区分　@==　@”前場受付時間外エラー”　@）　@の場合
            else if (WEB3AcceptStatusDef.MORN_SESS_ACCEPT_OVERTIME_ERROR.equals(
                l_strAcceptStatus))
            {
                //notify受付時間外(先物OP注文受付キューParams)
                l_orderAcceptUnitService.notifyOrderAcceptOvertime(this.hostFotypeOrderAcceptParams);
            }

            //1.2.2 (*2) キューテーブルに処理区分更新
            // －処理対象注文受付キューレコード.処理区分に”処理済”をセットしDB更新する。
            hostFotypeOrderAcceptParams.setStatus(WEB3StatusDef.DEALT);
            hostFotypeOrderAcceptParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_QueryProcessor.doUpdateQuery(hostFotypeOrderAcceptParams);
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }
                    
        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
