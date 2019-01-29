head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderAndExecuteCommonInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者新規約定入力インタセプタ(WEB3AdminBondOrderAndExecuteCommonInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/15 齋藤栄三 (FTL) 新規作成
*/

package webbroker3.bd;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;


/**
 * (債券管理者新規約定入力インタセプタ)<BR>
 * 債券管理者新規約定入力インタセプタクラス<BR>
 * 
 * @@author 齋藤栄三 (FTL)
 * @@version 1.0 
 */
public class WEB3AdminBondOrderAndExecuteCommonInterceptor implements BondOrderManagerPersistenceEventInterceptor
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderAndExecuteCommonInterceptor.class);
   
    /**
     * (債券管理者新規注文更新インタセプタ)<BR>
     * 債券管理者新規注文更新インタセプタ<BR>
     */
    private WEB3AdminBondNewOrderUpdateInterceptor adminBondNewOrderUpdateInterceptor;
    
    /**
     * (債券管理者新規約定更新インタセプタ)<BR>
     * 債券管理者新規約定更新インタセプタ<BR>
     */
    private WEB3AdminBondNewExecuteUpdateInterceptor adminBondNewExecuteUpdateInterceptor;
    
    /**
     * (債券管理者新規約定入力インタセプタ)<BR>
     * コンストラクタ<BR>
     * 
     */
    public WEB3AdminBondOrderAndExecuteCommonInterceptor() 
    {
     
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * １）債券管理者新規注文更新インタセプタ.(注文単位)更新値設定()をコールし、<BR>
     * 　@　@債券注文単位Paramsを取得。<BR>
     * 　@[(注文単位)更新値設定()の引数]<BR>
     * 　@　@　@DB更新タイプ =　@引数.OrderManagerPersistenceType<BR>
     * 　@　@　@コンテキスト = 引数.OrderManagerPersistenceContext<BR>
     * 　@　@　@注文単位Params = 引数.BondOrderUnitParams<BR>
     * <BR>
     * ２）債券管理者新規約定更新インタセプタ.(注文単位)更新値設定()をコールし、<BR>
     * 　@　@債券注文単位Paramsを取得。<BR>
     * 　@[(注文単位)更新値設定()の引数]<BR>
     * 　@　@　@DB更新タイプ =　@引数.OrderManagerPersistenceType<BR>
     * 　@　@　@コンテキスト = 引数.OrderManagerPersistenceContext<BR>
     * 　@　@　@注文単位Params = 引数.BondOrderUnitParams<BR>
     * <BR>
     * ３）１）、２）実行後の注文単位Paramsを返す。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * 
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType, 
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        //１）債券管理者新規注文更新インタセプタ.(注文単位)更新値設定()をコールし
        l_params = 
                this.adminBondNewOrderUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        //２）債券管理者新規約定更新インタセプタ.(注文単位)更新値設定()をコールし
        l_params = 
            this.adminBondNewExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (（注文履歴）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * １）債券管理者新規注文更新インタセプタ.(注文履歴)更新値設定()をコールし、<BR>
     * 　@　@債券注文履歴Paramsを取得。<BR>
     * 　@[(注文履歴)更新値設定()の引数]<BR>
     * 　@　@　@DB更新タイプ =　@引数.OrderManagerPersistenceType<BR>
     * 　@　@　@コンテキスト = 引数.OrderManagerPersistenceContext<BR>
     * 　@　@　@注文履歴Params = 引数.BondOrderActionParams<BR>
     * <BR>
     * ２）債券管理者新規約定更新インタセプタ.(注文履歴)更新値設定()をコールし、<BR>
     * 　@　@債券注文履歴Paramsを取得。<BR>
     * 　@[(注文履歴)更新値設定()の引数]<BR>
     * 　@　@　@DB更新タイプ =　@引数.OrderManagerPersistenceType<BR>
     * 　@　@　@コンテキスト = 引数.OrderManagerPersistenceContext<BR>
     * 　@　@　@注文履歴Params = 引数.BondOrderActionParams<BR>
     * <BR>
     * ３）１）、２）実行後の注文履歴Paramsを返す。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderActionParams
     * 
     */
    public BondOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType, 
        OrderManagerPersistenceContext l_context, 
        BondOrderActionParams l_params) 
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        //１）債券管理者新規注文更新インタセプタ.(注文履歴)更新値設定()をコールし
        l_params = 
            this.adminBondNewOrderUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        //２）債券管理者新規約定更新インタセプタ.(注文履歴)更新値設定()をコールし
        l_params = 
            this.adminBondNewExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (（約定）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * １）債券管理者新規注文更新インタセプタ.（約定）更新値設定()をコールし、<BR>
     * 　@　@債券約定Paramsを取得。<BR>
     * 　@[(約定)更新値設定()の引数]<BR>
     * 　@　@　@DB更新タイプ =　@引数.OrderManagerPersistenceType<BR>
     * 　@　@　@コンテキスト = 引数.OrderManagerPersistenceContext<BR>
     * 　@　@　@約定Params = 引数.BondOrderExecutionParams<BR>
     * <BR>
     * ２）債券管理者新規約定更新インタセプタ.（約定）更新値設定()をコールし、<BR>
     * 　@　@債券約定Paramsを取得。<BR>
     * 　@[(約定)更新値設定()の引数]<BR>
     * 　@　@　@DB更新タイプ =　@引数.OrderManagerPersistenceType<BR>
     * 　@　@　@コンテキスト = 引数.OrderManagerPersistenceContext<BR>
     * 　@　@　@約定Params = 引数.BondOrderExecutionParams<BR>
     * <BR>
     * ３）１）、２）実行後の債券約定Paramsを返す。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderExecutionParams
     * 
     */
    public BondOrderExecutionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderExecutionParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderExecutionParams)";
        log.entering(STR_METHOD_NAME);
        
        //１）債券管理者新規注文更新インタセプタ.(約定)更新値設定()をコールし
        l_params = 
                this.adminBondNewOrderUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        //２）債券管理者新規約定更新インタセプタ.(約定)更新値設定()をコールし
        l_params = 
            this.adminBondNewExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (get債券管理者新規注文更新インタセプタ)<BR>
     * 債券管理者新規注文更新インタセプタを返却する<BR>
     * @@return WEB3AdminBondNewOrderUpdateInterceptor
     * 
     */
    public WEB3AdminBondNewOrderUpdateInterceptor getBondNewOrderUpdateInterceptor() 
    {
        return this.adminBondNewOrderUpdateInterceptor;
    }
    
    /**
     * (set債券管理者新規注文更新インタセプタ)<BR>
     * 債券管理者新規注文更新インタセプタをセットする<BR>
     * @@param l_newOrderUpdateInterceptor - (債券管理者新規注文更新インタセプタ)<BR>
     * 債券管理者新規注文更新インタセプタ<BR>
     * 
     */
    public void setBondNewOrderUpdateInterceptor(
        WEB3AdminBondNewOrderUpdateInterceptor l_newOrderUpdateInterceptor) 
    {
        this.adminBondNewOrderUpdateInterceptor = l_newOrderUpdateInterceptor;
    }
    
    /**
     * (get債券管理者新規約定更新インタセプタ)<BR>
     * 債券管理者新規約定更新インタセプタを返却する<BR>
     * @@return WEB3AdminBondNewExecuteUpdateInterceptor
     * 
     */
    public WEB3AdminBondNewExecuteUpdateInterceptor getBondNewExecuteUpdateInterceptor() 
    {
        return this.adminBondNewExecuteUpdateInterceptor;
    }
    
    /**
     * (set債券管理者新規約定更新インタセプタ)<BR>
     * 債券管理者新規約定更新インタセプタをセットする<BR>
     * @@param l_newExecuteUpdateInterceptor - (管理者債券約定更新インタセプタ)<BR>
     * 管理者債券約定更新インタセプタ<BR>
     * 
     */
    public void setBondNewExecuteUpdateInterceptor(WEB3AdminBondNewExecuteUpdateInterceptor l_newExecuteUpdateInterceptor) 
    {
        this.adminBondNewExecuteUpdateInterceptor = l_newExecuteUpdateInterceptor;
    }

    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1)
    {
        return null;
    }

}
@
