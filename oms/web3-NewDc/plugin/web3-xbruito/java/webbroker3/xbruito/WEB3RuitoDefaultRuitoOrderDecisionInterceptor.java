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
filename	WEB3RuitoDefaultRuitoOrderDecisionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : デフォルト累投注文確定インタセプタ(WEB3RuitoDefaultRuitoOrderDecisionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderExecutionParams;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;

/**
 * デフォルト累投注文確定インタセプタ<BR> 
 * <BR>
 * 新規注文、取消の累投注文確定インタセプタの標準動作を規定したクラス。<BR>
 */
public abstract class WEB3RuitoDefaultRuitoOrderDecisionInterceptor
    implements RuitoOrderManagerPersistenceEventInterceptor 
{

    /**
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、累投注文単位データの作成・更新時に呼ばれる。<BR>
     * <BR>
     * 引数.累投注文単位Paramsを返す。<BR>
     * @@param l_persistenceType - データの更新または挿入中に<BR>
     * 呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - 呼び出し時のコンテキストを指定。<BR>
     * 例えば現物注文中など。<BR>
     * @@param l__unitParams - 永続化前の累投注文単位Params<BR>
     * 
     * @@return RuitoOrderUnitParams
     * @@roseuid 4084C0A803B8
     */
    public RuitoOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderUnitParams l_unitParams)
    {
        return l_unitParams;
    }

    /**
     * （mutateの実装）<BR>
     * <BR>
     * 約定処理の中で、累投注文単位データの作成・更新時に呼ばれる。<BR>
     * <BR>
     * 引数.累投約定Paramsを返す。<BR>
     * @@param l_persistenceType - データの更新または挿入中に<BR>
     * 呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - 呼び出し時のコンテキストを指定。<BR>
     * 例えば現物注文中など。<BR>
     * @@param l_executionParams - 永続化前の累投約定Params<BR>
     * @@return RuitoOrderExecutionParams
     * @@roseuid 4084C0A803E7
     */
    public RuitoOrderExecutionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderExecutionParams l_executionParams)
    {
        return l_executionParams;
    }

    /**
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、累投注文履歴データの作成・更新時に呼ばれる。<BR>
     * <BR>
     * 引数.累投注文履歴Paramsを返す。<BR>
     * @@param l_persistenceType - データの更新または挿入中に<BR>
     * 呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - 呼び出し時のコンテキストを指定。<BR>
     * 例えば現物注文中など。<BR>
     * @@param l_actionParams - 永続化前の累投注文履歴Params<BR>
     * @@return RuitoOrderActionParams
     * @@roseuid 4084C0A9005D
     */
    public RuitoOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderActionParams l_actionParams)
    {
        return l_actionParams;
    }

    /**
     * （getQueryToExecuteの実装）<BR>
     * <BR>
     * nullを返す。<BR>
     * @@param l_persistenceType - データの更新または挿入中に<BR>
     * 呼ぶかどうか指定<BR>
     * @@param l_tableRow - どのテーブルを更新するか指定<BR>
     * @@return com.fitechlabs.xtrade.kernel.data.BatchedQuery
     * @@roseuid 4084C0A9002E
     */
    public BatchedQuery getQueryToExecute(
        OrderManagerPersistenceType l_persistenceType,
        Class l_tableRow)
    {
        return null;
    }

//    /**
//    	* @@param arg0
//    	* @@param arg1
//    	* @@param arg2
//    	* @@return RuitoOrderUnitParams
//    	* @@roseuid 40C481D20177
//    	*/
//    public RuitoOrderUnitParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderUnitParams arg2)
//    {
//        //引数.累投約定Paramsを返す。
//        return arg2;        
//    }
//
//    /**
//    	* @@param arg0
//    	* @@param arg1
//    	* @@param arg2
//    	* @@return RuitoOrderExecutionParams
//    	* @@roseuid 40C481D4006D
//    	*/
//    public RuitoOrderExecutionParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderExecutionParams arg2)
//    {       
//        //引数.累投約定Paramsを返す。
//        return arg2;
//    }
//
//    /**
//    	* @@param arg0
//    	* @@param arg1
//    	* @@param arg2
//    	* @@return RuitoOrderActionParams
//    	* @@roseuid 40C481D5032C
//    	*/
//    public RuitoOrderActionParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderActionParams arg2)
//    {
//        //引数.累投約定Paramsを返す。
//        return arg2;        
//    }
}
@
