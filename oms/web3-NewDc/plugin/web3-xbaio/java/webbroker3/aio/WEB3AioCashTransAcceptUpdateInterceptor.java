head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransAcceptUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金受付更新インタセプタ(WEB3AioCashTransAcceptUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 周勇 (中訊) 新規作成      
                   2004/10/23 于美麗 (中訊) レビュー             
*/
package webbroker3.aio;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;


/**
 * (入出金受付更新インタセプタ)<BR>
 * 入出金受付更新インタセプタクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashTransAcceptUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    
    /**
     * (エラーコード)
     */
    private String errorCode;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransAcceptUpdateInterceptor.class); 
    
    /**
     * (入出金受付更新インタセプタ)<BR>
     * コンストラクタ <BR>
     * <BR>
     * 引数のエラーコードを自身のプロパティにセット、自身のインスタンスを返却する。
     * @@param l_strErrorCode - (エラーコード)
     * @@return webbroker3.aio.WEB3AioCashTransAcceptUpdateInterceptor
     * @@roseuid 40FC88F602AD
     */
    public WEB3AioCashTransAcceptUpdateInterceptor(String l_strErrorCode) 
    {
        final String STR_METHOD_NAME = "WEB3AioCashTransAcceptUpdateInterceptor" +
            "(String l_strErrorCode)";
        log.entering(STR_METHOD_NAME);

        this.errorCode = l_strErrorCode;
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 注文単位Paramsに拡張項目(*)を設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。 <BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 更新内容は、「入出金受付_注文単位テーブル.xls」の <BR>
     * 「（入出金受付[受付完了]）注文単位テーブル」シート、 <BR>
     * 「（入出金受付[受付エラー]）注文単位テーブル」シート参照。<BR>
     * @@param l_updateType - ((更新タイプ)<BR>)<BR>
     * INSERTまたは、UPDATE<BR>
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。）
     * @@param l_process - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 注文単位のパラメータクラス
     * @@return AioOrderUnitParams
     * @@roseuid 40FC896602CC
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType l_updateType," +
            "OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        l_orderUnitParams.setErrorReasonCode(this.errorCode);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
