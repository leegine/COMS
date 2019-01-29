head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用取消更新インタセプタ(WEB3SrvRegiCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.aio.WEB3AioCashTransUpdateInterceptor;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用取消更新インタセプタ)<BR>
 * サービス利用取消更新インタセプタクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3SrvRegiCancelUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{

    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3SrvRegiCancelUpdateInterceptor.class);

    /**
     * (サービス利用取消更新インタセプタ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 4100D63A0212
     */
    public WEB3SrvRegiCancelUpdateInterceptor() 
    {
     
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * アセット振替取引注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。 <BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.アセット振替取引注文単位Paramsの<BR>
     * 　@拡張項目に値をセットし、返却する。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「サービス利用申込取消_注文単位テーブル仕様.xls」<BR>
     * 「サービス利用管理者・顧客アップロード_注文単位テーブル.xls」<BR>
     * 「サービス利用管理者・顧客変更_注文単位テーブル.xls」参照。 <BR>
     * @@param l_persistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_aioOrderUnitParams - (アセット振替取引注文単位Params)<BR>
     * 永続化前のアセット振替取引注文単位Params<BR>
     * 
     * @@return AioOrderUnitParams
     * @@roseuid 4100D7520389
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType, 
        OrderManagerPersistenceContext l_persistenceContext, 
        AioOrderUnitParams l_aioOrderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            " mutate(OrderManagerPersistenceType , OrderManagerPersistenceContext , AioOrderUnitParams )";
        log.entering(STR_METHOD_NAME);
        
        if (l_aioOrderUnitParams == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //注文取消区分       
        l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.CANCELED);
        
        log.exiting(STR_METHOD_NAME);
        return l_aioOrderUnitParams;
    }
}
@
