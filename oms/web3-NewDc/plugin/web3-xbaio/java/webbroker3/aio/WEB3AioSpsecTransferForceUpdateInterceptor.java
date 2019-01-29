head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSpsecTransferForceUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定口座振替強制更新インタセプタ (WEB3AioSpsecTransferForceUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/18 韋念瓊 (中訊) 新規作成 
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (特定口座振替強制更新インタセプタ)<BR>
 * 特定口座振替強制更新インタセプタ クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceUpdateInterceptor extends WEB3AioSecurityTransferForceUpdateInterceptor 
{
    /**
    * ログユーティリティ
    */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioSpsecTransferForceUpdateInterceptor.class);
        
    /**
     * (ミニ株区分)<BR>
     * ミニ株区分
     */
    private String miniStockDiv;
    
    /**
     * @@roseuid 41B0458B01F4
     */
    public WEB3AioSpsecTransferForceUpdateInterceptor() 
    {
     
    }
    
    /**
     * (証券振替強制更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。
     * @@param l_securityTransferForceUpdateInterceptor - 入出金注文内容オブジェクト
     * @@return webbroker3.aio.WEB3AioSecurityTransferForceUpdateInterceptor
     * @@roseuid 416CAF1A02D0
     */
    public WEB3AioSpsecTransferForceUpdateInterceptor(WEB3AioNewOrderSpec l_aioNewOrderSpec) 
    {      
        //インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。
        this.cashTransOrderSpec = l_aioNewOrderSpec; 
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装） 
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     *(*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     *(*) xTradeマニュアル「更新内容のカスタマイズ」参照。 <BR>
     * <BR>
     *　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。<BR>
     * <BR>
     *１）ミニ株区分以外の拡張項目の値を設定する。 <BR>
     * <BR>
     *superクラスの更新値設定()メソッドをコールする。 <BR>
     * <BR>
     *２）ミニ株区分の値をセットする。 <BR>
     * <BR>
     *this.ミニ株区分の値をパラメータ.注文単位Paramsの同項目にセットする。<BR>
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
        String l_strMethodName = 
            "mutate(" +
            "OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "AioOrderUnitParams l_aioOrderUnitParams)";
        log.entering(l_strMethodName);
        
        if (l_aioOrderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);            
        }
        
        //１）ミニ株区分以外の拡張項目の値を設定する。 
        //superクラスの更新値設定()メソッドをコールする。 
        super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext, 
                l_aioOrderUnitParams);
        
        //２）ミニ株区分の値をセットする。 
        //this.ミニ株区分の値をパラメータ.注文単位Paramsの同項目にセットする。
        l_aioOrderUnitParams.setMiniStockDiv(this.miniStockDiv);
        
        log.exiting(l_strMethodName);
        
        return l_aioOrderUnitParams;
    }
    
    /**
     * (setミニ株区分)<BR>
     * ミニ株区分をセットする。
     * @@param l_strMiniStockDiv - ミニ株区分
     * @@roseuid 416CAF1A02D6
     */
    public void setMiniStockDiv(String l_strMiniStockDiv) 
    {       
        this.miniStockDiv = l_strMiniStockDiv;     
    }   
}
@
