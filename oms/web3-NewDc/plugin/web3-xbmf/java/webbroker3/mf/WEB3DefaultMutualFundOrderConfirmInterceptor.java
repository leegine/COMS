head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3DefaultMutualFundOrderConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : デフォルト投信注文確定インタセプタ(WEB3DefaultMutualFundOrderConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 周勇 (中訊) 新規作成
                   2004/08/20 黄建 (中訊) レビュー    
*/

package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * デフォルト投信注文確定インタセプタ<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3DefaultMutualFundOrderConfirmInterceptor
    implements MutualFundOrderManagerPersistenceEventInterceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3DefaultMutualFundOrderConfirmInterceptor.class);
                
    /**
     * (更新値設定)<BR>
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、投信注文単位データの作成・更新時に呼ばれる。<BR>
     * <BR>
     * 引数.投信注文単位Paramsを返す。<BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderUnitParams - (投信注文単位Params)<BR>
     * 永続化前の投信注文単位Params<BR>
     * 
     * @@return MutualFundOrderUnitParams
     * @@roseuid 40AAF0FF00CA
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            "MutualFundOrderUnitParams l_mutualFundOrderUnitParams)";
        log.entering(STR_METHOD_NAME);    
        if (l_mutualFundOrderUnitParams == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderUnitParams;
        
    }
    /**
     * (更新値設定)<BR>
     * （mutateの実装）<BR>
     * <BR>
     * 約定処理の中で、投信注文単位データの作成・更新時に呼ばれる。<BR>
     * <BR>
     * 引数.投信約定Paramsを返す。<BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderExecutionParams - (投信約定Params)<BR>
     * 永続化前の投信約定Params<BR>
     * @@return MutualFundOrderExecutionParams
     * @@roseuid 40AAF0FF00E9
     */
    public MutualFundOrderExecutionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderExecutionParams l_mutualFundOrderExecutionParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            "MutualFundOrderExecutionParams l_mutualFundOrderExecutionParams)";
        log.entering(STR_METHOD_NAME);    
        if (l_mutualFundOrderExecutionParams == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        log.exiting(STR_METHOD_NAME);               
        return l_mutualFundOrderExecutionParams;
    }
    /**
     * (更新値設定)<BR>
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、投信注文履歴データの作成・更新時に呼ばれる。<BR>
     * <BR>
     * 引数.投信注文履歴Paramsを返す。<BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderActionParams - (投信注文履歴Params)<BR>
     * 永続化前の投信注文履歴Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AAF0FF00F9
     */
    public MutualFundOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderActionParams l_mutualFundOrderActionParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            "MutualFundOrderActionParams l_mutualFundOrderActionParams)";
        log.entering(STR_METHOD_NAME);    
        if (l_mutualFundOrderActionParams == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderActionParams;
    }
    /**
     * (get実行クエリ)<BR>
     * （getQueryToExecuteの実装）<BR>
     * <BR>
     * nullを返す。<BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_tableRowClass - (テーブルRowクラス)<BR>
     * どのテーブルを更新するか指定<BR>
     * @@return BatchedQuery
     * @@roseuid 40AAF0FF0118
     */
    public BatchedQuery getQueryToExecute(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        Class l_tableRowClass)
    {
        return null;
    }
}
@
