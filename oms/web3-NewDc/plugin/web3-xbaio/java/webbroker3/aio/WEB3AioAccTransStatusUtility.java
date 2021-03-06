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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : æ¨OPØàUÖÌóµðæ¾ÌNX(WEB3AioAccTransStatusUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01 üE (u)  VKì¬
                   
*/
package webbroker3.aio;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import webbroker3.aio.define.WEB3AioJudgeResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3StatusDef;

/**
 *  æ¨OPØàUÖÌóµðæ¾ÌNXB<BR>
 * 
 * @@author üE (u)
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
            // ¶PÊe[uiâûÀ^Cvaèàj    UÖ¿ótL[e[u
            //[¶óÔ] [¶æÁæª] [æª] [»èÊ]
            
            //0)  PFótÏiVK¶j OFúl | ---> D
            {
                OrderStatusEnum.ACCEPTED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.OTHER
            }, 
            {WEB3AioJudgeResultDef.D},
            //2)   RF­ÏiVK¶jOFúl | ---> E
            {
                OrderStatusEnum.ORDERED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.E},
            //4)   UF­¸siVK¶jOFúl | ---> F
            {
                OrderStatusEnum.NOT_ORDERED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.F},
            //6)   PFótÏiVK¶jOFúl | ---> J
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
