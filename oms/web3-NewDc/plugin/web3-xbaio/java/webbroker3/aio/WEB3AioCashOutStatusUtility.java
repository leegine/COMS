head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.28.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashOutStatusUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金、情報料出金、会費出金の処理状況を取得の処理クラス(WEB3AioCashOutStatusUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01 王蘭芬 (中訊)  新規作成
                   
*/
package webbroker3.aio;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import webbroker3.aio.define.WEB3AioJudgeResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3StatusDef;

/**
 *  出金、情報料出金、会費出金の処理状況を取得の処理クラス。<BR>
 * 
 * @@author 王蘭芬 (中訊)
 * @@version 1.0
 */
public class WEB3AioCashOutStatusUtility extends WEB3AioAbstractStatusUtility
{
    /**
     * 詳しく処理ステータスカラム(Map)
     */
    private static Map cashOutStatusMap = new Hashtable();

    /**
     * 詳しく処理ステータスカラム(String[][])
     */
    private static String[][] itemKey = null;

    static
    {
        String[][] itemKeyTemp = 
        {
            // 注文単位テーブル   出金請求受付キューテーブル
            //[注文状態] [注文取消区分] [処理区分] [判定結果]
            
            //0)  １：受付済（新規注文） ０：初期値 － ---> D
            //0)  １：受付済（新規注文） ０：初期値 OTHER ---> D
            {
                OrderStatusEnum.ACCEPTED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.OTHER
            }, 
            {WEB3AioJudgeResultDef.D},
            //2)   14:発注済（取消注文）３：全部取消完了 － ---> I
            {
                OrderStatusEnum.CANCELLED.intValue() + "", 
                WEB3ModifyCancelTypeDef.CANCELED, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.I},
            //4)   ３：発注済（新規注文）０：初期値 － ---> E
            {
                OrderStatusEnum.ORDERED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.E},
            //6)   ６：発注失敗（新規注文）０：初期値 － ---> F
            {
                OrderStatusEnum.NOT_ORDERED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.F},
            //8)   １：受付済（新規注文） ０：初期値 ９：エラー ---> J
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

    /**
     * (get処理状況メッセージコード)<BR>
     * 引数により、出金、情報料出金、会費出金の場合、処理状況を取得し、返却する<BR>
     * 「ＤＢ更新仕様\10.入出金\入出金ステータス構成表.xls」の<BR>
     *  出金、情報料出金、会費出金(4)を参照する<BR>
     * 
     * @@param orderType - 注文単位テーブルの注文状態
     * @@param orderCancelType - 注文単位テーブルの注文取消区分
     * @@param orderStatus - 受付キューテーブルの処理区分
     * @@return String
     */
    public String getStatus(
        String orderType,
        String orderCancelType,
        String orderStatus)
    {
        String[] l_params = {orderType, orderCancelType, orderStatus};
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
}
@
