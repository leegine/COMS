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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���A��񗿏o���A���o���̏����󋵂��擾�̏����N���X(WEB3AioCashOutStatusUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01 ����� (���u)  �V�K�쐬
                   
*/
package webbroker3.aio;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import webbroker3.aio.define.WEB3AioJudgeResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3StatusDef;

/**
 *  �o���A��񗿏o���A���o���̏����󋵂��擾�̏����N���X�B<BR>
 * 
 * @@author ����� (���u)
 * @@version 1.0
 */
public class WEB3AioCashOutStatusUtility extends WEB3AioAbstractStatusUtility
{
    /**
     * �ڂ��������X�e�[�^�X�J����(Map)
     */
    private static Map cashOutStatusMap = new Hashtable();

    /**
     * �ڂ��������X�e�[�^�X�J����(String[][])
     */
    private static String[][] itemKey = null;

    static
    {
        String[][] itemKeyTemp = 
        {
            // �����P�ʃe�[�u��   �o��������t�L���[�e�[�u��
            //[�������] [��������敪] [�����敪] [���茋��]
            
            //0)  �P�F��t�ρi�V�K�����j �O�F�����l �| ---> D
            //0)  �P�F��t�ρi�V�K�����j �O�F�����l OTHER ---> D
            {
                OrderStatusEnum.ACCEPTED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.OTHER
            }, 
            {WEB3AioJudgeResultDef.D},
            //2)   14:�����ρi��������j�R�F�S��������� �| ---> I
            {
                OrderStatusEnum.CANCELLED.intValue() + "", 
                WEB3ModifyCancelTypeDef.CANCELED, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.I},
            //4)   �R�F�����ρi�V�K�����j�O�F�����l �| ---> E
            {
                OrderStatusEnum.ORDERED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.E},
            //6)   �U�F�������s�i�V�K�����j�O�F�����l �| ---> F
            {
                OrderStatusEnum.NOT_ORDERED.intValue() + "", 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY
            }, 
            {WEB3AioJudgeResultDef.F},
            //8)   �P�F��t�ρi�V�K�����j �O�F�����l �X�F�G���[ ---> J
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
     * (get�����󋵃��b�Z�[�W�R�[�h)<BR>
     * �����ɂ��A�o���A��񗿏o���A���o���̏ꍇ�A�����󋵂��擾���A�ԋp����<BR>
     * �u�c�a�X�V�d�l\10.���o��\���o���X�e�[�^�X�\���\.xls�v��<BR>
     *  �o���A��񗿏o���A���o��(4)���Q�Ƃ���<BR>
     * 
     * @@param orderType - �����P�ʃe�[�u���̒������
     * @@param orderCancelType - �����P�ʃe�[�u���̒�������敪
     * @@param orderStatus - ��t�L���[�e�[�u���̏����敪
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
