head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.27.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioAccTransStatusUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP証拠金振替の処理状況を取得の処理クラス(WEB3AioAccTransStatusUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01 周勇 (中訊)  新規作成
                   
*/
package webbroker3.aio;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import webbroker3.aio.define.WEB3AioJudgeResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3StatusDef;

/**
 *  先物OP証拠金振替の処理状況を取得の処理クラス。<BR>
 * 
 * @@author 周勇 (中訊)
 * @@version 1.0
 */
public class WEB3AioAccTransStatusUtility extends WEB3AioAbstractStatusUtility
{
    private static Map cashOutStatusMap = new Hashtable();

    private static String[][] itemKey = null;

    static
    {
        String[][] itemKeyTemp = 
        {
            // 注文単位テーブル（補助口座タイプ＝預り金）    振替請求受付キューテーブル
            //[注文状態] [注文取消区分] [処理区分] [判定結果]
            
            //0)  １：受付済（新規注文） ０：初期値 － ---> D
            {
                OrderStatusEnum.ACCEPTED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.OTHER
            }, 
            {WEB3AioJudgeResultDef.D},
            //2)   ３：発注済（新規注文）０：初期値 － ---> E
            {
                OrderStatusEnum.ORDERED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.E},
            //4)   ６：発注失敗（新規注文）０：初期値 － ---> F
            {
                OrderStatusEnum.NOT_ORDERED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.F},
            //6)   １：受付済（新規注文）０：初期値 － ---> J
            {
                OrderStatusEnum.ACCEPTED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                WEB3StatusDef.DATA_ERROR
            }, 
            {WEB3AioJudgeResultDef.J},
        };
        itemKey = itemKeyTemp;
        
        for (int m = 0; m < itemKey.length; m = m + 2)
        {
            cashOutStatusMap.put(new DefaultStatus(itemKey[m]), itemKey[m + 1][0]);
        }
    }

    public String getStatus(
        String orderStatus,
        String orderCancelType,
        String transactionStatus)
    {
        String[] l_params = {orderStatus, orderCancelType, transactionStatus};
        Status l_status = new DefaultStatus(l_params);
        return super.getStatus(l_status);
    }
    /* (non-Javadoc)
     * @@see webbroker3.aio.WEB3AioAbstractStatusUtility#getStatusMap()
     */
    protected Map getStatusMap()
    {
        return cashOutStatusMap;
    }

    protected String[][] getStringStatus()
    {
        return itemKey;
    }
}
@
