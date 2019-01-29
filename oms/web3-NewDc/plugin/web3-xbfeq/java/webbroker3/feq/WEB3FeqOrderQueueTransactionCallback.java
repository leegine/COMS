head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderQueueTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文キューTransactionCallback(WEB3FeqOrderQueueTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/07/15 大辻(SRA) 新規作成
Revesion History : 2008/07/25 黒釜(SRA) 実装:No.019
*/
package webbroker3.feq;

import java.util.List;

import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;

/**
 * （外国株式注文キューTransactionCallback）。<BR>
 * <BR>
 * 外国株式注文キュートランザクション処理を実施するクラス。<BR>
 * @@version 1.0
 */
public class WEB3FeqOrderQueueTransactionCallback implements TransactionCallback
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderQueueTransactionCallback.class);

    /**
     * (注文単位)<BR>
     * 注文単位<BR>
     */
    private WEB3FeqOrderUnit orderUnit;

    /**
     * コンストラクタ<BR>
     * @@param l_orderUnit 外国株式注文単位
     */
    public WEB3FeqOrderQueueTransactionCallback(FeqOrderUnit l_orderUnit)
    {
        this.orderUnit = (WEB3FeqOrderUnit)l_orderUnit;
    }

    /**
     * トランザクション処理を実施する。<BR>
     * <BR>
     * 注文キューテーブルにロックをかける。<BR>
     * this.lockSLE送信キューをコールする。<BR>
     * <BR>
     * @@return Object
     * @@throws DataNetworkException
     * @@throws DataQueryException
     */
    public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = " process() ";
        log.entering(STR_METHOD_NAME);
        
        // 注文キューテーブルにロックをかける。                
        this.lockSleSendQueue();

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (lockSLE送信キュー)<BR>
     * SLE_SEND_Qテーブルに対し、以下の条件で<BR>
     * ”select for update nowait ”を使用してレコードをロックする。<BR>
     * <BR>
　@     * [ 検索条件 ]<BR>
　@     * 注文ID　@　@　@　@   =　@プロパティの注文単位.注文ID<BR>
　@     * オペレータタイプ = "新規" <BR>
　@     * 処理区分　@　@　@　@=　@"未送信"<BR>
     *
     */
    private void lockSleSendQueue() throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = " lockSleSendQueue() ";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        String l_strWhere = " order_id = ? and op_type = ? and status = ? ";
        Object[] l_objWhere = new Object[3];
        l_objWhere[0] = orderUnit.getOrderId() + "";
        l_objWhere[1] = SleSendqOpTypeEnum.NEW_ORDER;
        l_objWhere[2] = SleSendqProcStatusEnum.NOT_PROCESSED;

        List l_lisSleSendQRow =  
            l_queryProcessor.doFindAllQuery(
                SleSendQRow.TYPE,
                l_strWhere,
                "for update nowait",
                l_objWhere);

        if (l_lisSleSendQRow == null || l_lisSleSendQRow.size() == 0)
        {
            log.error("lock対象に該当するSLE_SEND_Qレコードが存在しません。");
            throw new DataCallbackException("Error while locking SLE_SEND_Q :"
                + " order_id = " + orderUnit.getOrderId()
                + " op_type = " + SleSendqOpTypeEnum.NEW_ORDER
                + " status = " + SleSendqProcStatusEnum.NOT_PROCESSED);
        }

        log.exiting(STR_METHOD_NAME);

    }
}@
