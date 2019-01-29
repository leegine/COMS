head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteChangeCommonInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者約定変更インタセプタ(WEB3AdminBondExecuteChangeCommonInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
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
 * (債券管理者約定変更インタセプタ)<BR>
 * 債券管理者約定変更インタセプタクラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteChangeCommonInterceptor implements BondOrderManagerPersistenceEventInterceptor
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteChangeCommonInterceptor.class);
   
    /**
     * (債券管理者約定取消更新インタセプタ)<BR>
     * 債券管理者約定取消更新インタセプタ<BR>
     */
    private WEB3AdminBondExecuteCancelUpdateInterceptor adminBondExecuteCancelUpdateInterceptor;
    
    /**
     * (債券管理者約定更新インタセプタ)<BR>
     * 債券管理者約定更新インタセプタ<BR>
     */
    private WEB3AdminBondExecuteUpdateInterceptor adminBondExecuteUpdateInterceptor;
    
    /**
     * (債券管理者約定変更インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D6A1D7010B
     */
    public WEB3AdminBondExecuteChangeCommonInterceptor() 
    {
     
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * １）債券管理者約定取消更新インタセプタ != nullの場合、<BR>
     * 　@　@債券管理者約定取消更新インタセプタ.(注文単位)更新値設定()をコールし、<BR>
     * 　@　@債券注文単位Paramsを取得。<BR>
     * 　@[(注文単位)更新値設定()の引数]<BR>
     * 　@　@　@DB更新タイプ =　@引数.OrderManagerPersistenceType<BR>
     * 　@　@　@コンテキスト = 引数.OrderManagerPersistenceContext<BR>
     * 　@　@　@注文単位Params = 引数.BondOrderUnitParams<BR>
     * <BR>
     * ２）this.債券管理者約定更新インタセプタ.(注文単位)更新値設定()をコールし、<BR>
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
     * @@roseuid 44D6A1D400CF
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType, 
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        BondOrderUnitParams l_orderUnitParams = new BondOrderUnitParams();
        //１）債券管理者約定取消更新インタセプタ != nullの場合、
        if (this.adminBondExecuteCancelUpdateInterceptor != null)
        {
            l_orderUnitParams = 
                this.adminBondExecuteCancelUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        }
        
        //２）this.債券管理者約定更新インタセプタ.(注文単位)更新値設定()をコールし
        l_orderUnitParams = 
            this.adminBondExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (（注文履歴）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * １）債券管理者約定取消更新インタセプタ != nullの場合、<BR>
     * 　@　@債券管理者約定取消更新インタセプタ.(注文履歴)更新値設定()をコールし、<BR>
     * 　@　@債券注文履歴Paramsを取得。<BR>
     * 　@[(注文履歴)更新値設定()の引数]<BR>
     * 　@　@　@DB更新タイプ =　@引数.OrderManagerPersistenceType<BR>
     * 　@　@　@コンテキスト = 引数.OrderManagerPersistenceContext<BR>
     * 　@　@　@注文履歴Params = 引数.BondOrderActionParams<BR>
     * <BR>
     * ２）this.債券管理者約定更新インタセプタ.(注文履歴)更新値設定()をコールし、<BR>
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
     * @@roseuid 44D7F7B8025B
     */
    public BondOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType, 
        OrderManagerPersistenceContext l_context, 
        BondOrderActionParams l_params) 
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        BondOrderActionParams l_orderActionParams = new BondOrderActionParams();
        //１）債券管理者約定取消更新インタセプタ != nullの場合、
        if (this.adminBondExecuteCancelUpdateInterceptor != null)
        {
            l_orderActionParams = 
                this.adminBondExecuteCancelUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        }
        
        //２）this.債券管理者約定更新インタセプタ.(注文単位)更新値設定()をコールし
        l_orderActionParams = 
            this.adminBondExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
    
    /**
     * (（約定）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * １）債券管理者約定取消更新インタセプタ != nullの場合、<BR>
     * 　@　@債券管理者約定取消更新インタセプタ.（約定）更新値設定()をコールし、<BR>
     * 　@　@債券約定Paramsを取得。<BR>
     * 　@[(約定)更新値設定()の引数]<BR>
     * 　@　@　@DB更新タイプ =　@引数.OrderManagerPersistenceType<BR>
     * 　@　@　@コンテキスト = 引数.OrderManagerPersistenceContext<BR>
     * 　@　@　@約定Params = 引数.BondOrderExecutionParams<BR>
     * <BR>
     * ２）this.債券管理者約定更新インタセプタ.（約定）更新値設定()をコールし、<BR>
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
     * @@roseuid 44D7FA450222
     */
    public BondOrderExecutionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderExecutionParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderExecutionParams)";
        log.entering(STR_METHOD_NAME);
        
        BondOrderExecutionParams l_orderExecutionParams = new BondOrderExecutionParams();
        //１）債券管理者約定取消更新インタセプタ != nullの場合、
        if (this.adminBondExecuteCancelUpdateInterceptor != null)
        {
            l_orderExecutionParams = 
                this.adminBondExecuteCancelUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        }
        
        //２）this.債券管理者約定更新インタセプタ.(注文単位)更新値設定()をコールし
        l_orderExecutionParams = 
            this.adminBondExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_orderExecutionParams;
    }
    
    /**
     * (get債券管理者約定取消更新インタセプタ)<BR>
     * 債券管理者約定取消更新インタセプタを返却する<BR>
     * @@return WEB3AdminBondExecuteCancelUpdateInterceptor
     * @@roseuid 44D6D876006D
     */
    public WEB3AdminBondExecuteCancelUpdateInterceptor getBondExecuteCancelUpdateInterceptor() 
    {
        return this.adminBondExecuteCancelUpdateInterceptor;
    }
    
    /**
     * (set債券管理者約定取消更新インタセプタ)<BR>
     * 債券管理者約定取消更新インタセプタをセットする<BR>
     * @@param l_executeCancelUpdateInterceptor - (債券管理者約定取消更新インタセプタ)<BR>
     * 債券管理者約定取消更新インタセプタ<BR>
     * @@roseuid 44D6D95A00A2
     */
    public void setBondExecuteCancelUpdateInterceptor(
        WEB3AdminBondExecuteCancelUpdateInterceptor l_executeCancelUpdateInterceptor) 
    {
        this.adminBondExecuteCancelUpdateInterceptor = l_executeCancelUpdateInterceptor;
    }
    
    /**
     * (get債券管理者約定更新インタセプタ)<BR>
     * 債券管理者約定更新インタセプタを返却する<BR>
     * @@return WEB3AdminBondExecuteCancelUpdateInterceptor
     * @@roseuid 44D6D9BA0017
     */
    public WEB3AdminBondExecuteUpdateInterceptor getBondExecuteUpdateInterceptor() 
    {
        return this.adminBondExecuteUpdateInterceptor;
    }
    
    /**
     * (set債券管理者約定更新インタセプタ)<BR>
     * 債券管理者約定更新インタセプタをセットする<BR>
     * @@param l_executeUpdateInterceptor - (管理者債券約定更新インタセプタ)<BR>
     * 管理者債券約定更新インタセプタインスタンス<BR>
     * @@roseuid 44D6DA1F0389
     */
    public void setBondExecuteUpdateInterceptor(WEB3AdminBondExecuteUpdateInterceptor l_executeUpdateInterceptor) 
    {
        this.adminBondExecuteUpdateInterceptor = l_executeUpdateInterceptor;
    }

    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1)
    {
        return null;
    }

}
@
