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
filename	WEB3RuitoCancelDescitionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投取消確定インタセプタ(WEB3RuitoCancelDescitionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams;

/**
 * 累投取消確定インタセプタ<BR>
 */
public class WEB3RuitoCancelDescitionInterceptor
    extends WEB3RuitoDefaultRuitoOrderDecisionInterceptor
{

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 4091A1D401CA
     */
    public WEB3RuitoCancelDescitionInterceptor()
    {

    }

    /**
     * （mutateのオーバーライド）<BR>
     * <BR>
     * 引数で与えられた累投注文履歴Paramsに値を設定し、<BR>
     *    累投注文履歴Paramsを返す。<BR>
     * <BR>
     * １）　@注文エラー理由コードの設定を行う。<BR>
     * 　@－累投注文履歴Params.set注文エラー理由コード()をコールし、<BR>
     *      注文エラー理由コードの設定を行う。<BR>
     * 　@　@［set注文エラー理由コードに渡すパラメタ］<BR>
     * 　@　@　@注文エラー理由コード： null<BR>
     * <BR>
     * ２）　@引数で与えられた累投注文履歴Paramsを返す。<BR>
     * @@param l_persistenceType - データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - 
     * 呼び出し時のコンテキストを指定。例えば現物注文中など<BR>
     * @@param l_actionParams - 永続化前の累投注文履歴Params<BR>
     * @@return RuitoOrderActionParams
     * @@roseuid 4091A2030005
     */
    public RuitoOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderActionParams l_actionParams)
    {
        //１）　@注文エラー理由コードの設定を行う。
        l_actionParams.setErrorReasonCode(null);
        //２）　@引数で与えられた累投注文履歴Paramsを返す。       
        return l_actionParams;
    }

//    /**
//     * @@param l_persistenceType
//     * @@param l_persistenceContext
//     * @@param l_unitParams
//     * @@return RuitoOrderUnitParams
//     * @@roseuid 40C01AA20198
//     */
//    public RuitoOrderUnitParams setUpdatePrice(
//        OrderManagerPersistenceType l_persistenceType,
//        OrderManagerPersistenceContext l_persistenceContext,
//        RuitoOrderUnitParams l_unitParams)
//    {
//        l_unitParams.setErrorReasonCode(null);
//        return l_unitParams;
//    }
//
//    /**
//     * @@param l_persistenceType
//     * @@param l_persistenceContext
//     * @@param l_executionParams
//     * @@return RuitoOrderExecutionParams
//     * @@roseuid 40C01AA203D3
//     */
//    public RuitoOrderExecutionParams setUpdatePrice(
//        OrderManagerPersistenceType l_persistenceType,
//        OrderManagerPersistenceContext l_persistenceContext,
//        RuitoOrderExecutionParams l_executionParams)
//    {
//        return l_executionParams;
//    }

//    /**
//     * @@param l_persistenceType
//     * @@param l_tableRow
//     * @@return com.fitechlabs.xtrade.kernel.data.BatchedQuery
//     * @@roseuid 40C01AA3021C
//     */
//    public BatchedQuery getQueryToExecute(
//        OrderManagerPersistenceType l_persistenceType,
//        Class l_tableRow)
//    {
//        return null;
//    }
}
@
