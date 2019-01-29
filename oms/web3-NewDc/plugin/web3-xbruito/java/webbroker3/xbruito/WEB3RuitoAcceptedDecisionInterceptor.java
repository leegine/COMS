head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoAcceptedDecisionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投受付確定インタセプタ(WEB3RuitoAcceptedDecisionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;

import webbroker3.util.WEB3LogUtility;

/**
 * 累投受付確定インタセプタ<BR>
 */
public class WEB3RuitoAcceptedDecisionInterceptor
         extends WEB3RuitoDefaultRuitoOrderDecisionInterceptor
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoAcceptedDecisionInterceptor.class);

    /**
     * 注文エラー理由コード<BR>
     */
    private String orderErrorReasonCode;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40891614030C
     */
    public WEB3RuitoAcceptedDecisionInterceptor()
    {

    }

    /**
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、累投注文履歴データの作成・更新時に呼ばれる。<BR>
     * <BR>
     * １） this.注文エラー理由コードを、<BR>
     * 引数累投注文履歴Params.注文エラー理由コードに設定する。<BR>
     * @@param l_persistenceType - データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - 
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_actionParams - 永続化前の累投注文履歴Params<BR>
     * @@return RuitoOrderActionParams
     * @@roseuid 408913A403E7
     */
    public RuitoOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderActionParams l_actionParams)
    {
        String STR_METHOD_NAME = "mutate(" +
            "OrderManagerPersistenceType l_persistenceType," +
            "OrderManagerPersistenceContext l_persistenceContext," +
            "RuitoOrderActionParams l_actionParams)";
        log.entering(STR_METHOD_NAME);
        //１） this.注文エラー理由コードを、引数累投注文単位Params.
        //注文エラー理由コードに設定する。
        //this.orderErrorReasonCode = l_actionParams.getErrorReasonCode();        
        l_actionParams.setErrorReasonCode(this.getOrderErrorReasonCode());
        
        log.debug("l_actionParams.getErrorReasonCode()=" + l_actionParams.getErrorReasonCode());
        
        return l_actionParams;
    }

    /**
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、累投注文単位データの作成・更新時に呼ばれる。<BR>
     * <BR>
     * １） this.注文エラー理由コードを、<BR>
     *    引数累投注文単位Params.注文エラー理由コードに設定する。<BR>
     * @@param l_persistenceType - データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - 
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_unitParams - 永続化前の累投注文単位Params<BR>
     * @@return RuitoOrderUnitParams
     * @@roseuid 408C5D080022
     */
    public RuitoOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderUnitParams l_unitParams)
    {
        String STR_METHOD_NAME = "mutate(" +
            "OrderManagerPersistenceType l_persistenceType," +
            "OrderManagerPersistenceContext l_persistenceContext," +
            "RuitoOrderUnitParams l_unitParams)";
            
        log.entering(STR_METHOD_NAME);
        log.debug("this.getOrderErrorReasonCode()=" + this.getOrderErrorReasonCode());
        //１） this.注文エラー理由コードを、
        //引数累投注文単位Params.注文エラー理由コードに設定する。
        //this.orderErrorReasonCode = l_unitParams.getErrorReasonCode();        
        l_unitParams.setErrorReasonCode(this.getOrderErrorReasonCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_unitParams;
    }

    /**
     * 注文エラー理由コードの設定を行う。<BR>
     * @@param l_strErrorCode - エラーコード<BR>
     * @@roseuid 408913F20109
     */
    public void setOrderErrorReasonCode(String l_strErrorCode)
    {
        log.debug("l_strErrorCode = " + l_strErrorCode);
        orderErrorReasonCode = l_strErrorCode;
    }

    /**
     * this.注文エラー理由コードを返す。<BR>
     * @@return String
     * @@roseuid 408913FF0251
     */
    public String getOrderErrorReasonCode()
    {
        return orderErrorReasonCode;
    }

//    /**
//     * @@param l_persistenceType
//     * @@param l_persistenceContext
//     * @@param l_executionParams
//     * @@return RuitoOrderExecutionParams
//     * @@roseuid 40C01A7D0127
//     */
//    public RuitoOrderExecutionParams setUpdatePrice(
//        OrderManagerPersistenceType l_persistenceType,
//        OrderManagerPersistenceContext l_persistenceContext,
//        RuitoOrderExecutionParams l_executionParams)
//    {
//        return l_executionParams;
//    }
//
//    /**
//     * @@param l_persistenceType
//     * @@param l_tableRow
//     * @@return com.fitechlabs.xtrade.kernel.data.BatchedQuery
//     * @@roseuid 40C01A7D0380
//     */
//    public BatchedQuery getQueryToExecute(
//        OrderManagerPersistenceType l_persistenceType,        
//        Class l_tableRow)
//    {
//        return null;
//    }
}
@
