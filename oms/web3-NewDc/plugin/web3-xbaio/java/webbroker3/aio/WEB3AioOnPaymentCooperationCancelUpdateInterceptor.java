head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOnPaymentCooperationCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金連携取消更新インタセプタ(WEB3AioInputOutputHistoryServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 李俊 (中訊) 新規作成
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金連携取消更新インタセプタ)<BR>
 * 出金連携取消更新インタセプタクラス
 * @@author 李俊
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationCancelUpdateInterceptor extends WEB3AioCashoutCancelUpdateInterceptor 
{
    
    /**
     * 振替記述
     */
    private String description;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioOnPaymentCooperationCancelUpdateInterceptor.class);  
    /**
     * @@roseuid 41EC855D000F
     */
    public WEB3AioOnPaymentCooperationCancelUpdateInterceptor() 
    {
     
    }
    
    /**
     * (set振替記述)<BR>
     * 振替記述をセットする。<BR>
     * @@param l_strDescription - 識別コード
     * @@roseuid 41E4FF010178
     */
    public void setDescription(String l_strDescription) 
    {
        description = l_strDescription;
    }

    /**
     * (更新値設定)<BR>
     *  （mutateメソッドの実装）<BR>  
     *  注文単位Paramsに拡張項目(*)設定し返却する。<BR>  
     * <BR>
     *  (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>  
     *  (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>  
     * <BR>
     * プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。<BR>  
     * <BR>
     *  【ｘTrade】補足資料.DB更新 <BR> 
     *  「出金取消_注文単位テーブル仕様.xls」参照。<BR>  
     *<BR> 
     *  １）振替記述以外の拡張項目の値を設定する。<BR> 
     *<BR> 
     *   superクラスの更新値設定()メソッドをコールする。<BR> 
     *<BR> 
     *  ２）振替記述の値をセットする。<BR> 
     *<BR> 
     *   this.振替記述の値をパラメータ.注文単位Paramsの同項目にセットする。<BR> 
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE <BR>
     *  （OrderManagerPersistenceTypeにて定数定義。）<BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     *  （OrderManagerPersistenceContextにて定数定義）
     * @@param l_aioOrderUnitParams - (注文単位Params)<BR>
     *   注文単位のパラメータクラス
     * @@return AioOrderUnitParams
     * @@roseuid 416CAF1A02D2
     */    
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType, 
        OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
        AioOrderUnitParams l_aioOrderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(" +
            "OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "AioOrderUnitParams l_aioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_aioOrderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        //プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。
        //【ｘTrade】補足資料.DB更新 「出金取消_注文単位テーブル仕様.xls」参照。
        
        //superクラスの更新値設定()メソッドをコールする   
        super.mutate(l_orderManagerPersistenceType,l_orderManagerPersistenceContext,l_aioOrderUnitParams);
        
        // ２）振替記述の値をセットする。
        //振替記述の値をセットする
        //this.振替記述の値をパラメータ.注文単位Paramsの同項目にセットする。
        l_aioOrderUnitParams.setDescription(this.description);
        
        log.exiting(STR_METHOD_NAME);   
        return l_aioOrderUnitParams;     
    }
}
@
