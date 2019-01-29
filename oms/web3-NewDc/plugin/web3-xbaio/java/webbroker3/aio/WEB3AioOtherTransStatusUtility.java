head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOtherTransStatusUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���̑��̓����A�o���̏����󋵂��擾�̏����N���X(WEB3AioOtherTransStatusUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01 ���E (���u)  �V�K�쐬
                   
*/
package webbroker3.aio;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import webbroker3.aio.define.WEB3AioJudgeResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;

/**
 *  ���̑��̓����A�o���̏����󋵂��擾�̏����N���X�B<BR>
 * 
 * @@author ���E (���u)
 * @@version 1.0
 */
public class WEB3AioOtherTransStatusUtility extends WEB3AioAbstractStatusUtility
{
    private static Map cashOutStatusMap = new Hashtable();

    private static String[][] itemKey = null;

    static
    {
        String[][] itemKeyTemp = 
        {
            // �����P�ʃe�[�u��   �o��������t�L���[�e�[�u��
            //[�������] [��������敪] [�����敪] [���茋��]
            
            //0)  �R�F�����ρi�V�K�����j �O�F�����l �| ---> E
            {
                OrderStatusEnum.ORDERED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.E},
            //2)   �U�F�������s�i�V�K�����j�O�F�����l �| ---> F
            {
                OrderStatusEnum.NOT_ORDERED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.F},
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
