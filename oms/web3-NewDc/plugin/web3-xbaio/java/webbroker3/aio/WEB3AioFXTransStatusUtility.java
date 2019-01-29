head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioFXTransStatusUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ב֕ۏ؋��A�g�̏����󋵂��擾�̏����N���X(WEB3AioFXTransStatusUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01 ���z (���u) �V�K�쐬 
                 : 2006/07/13 ��O�� (���u) �d�l�ύX ���f��595
                 : 2006/11/16 ��� (SCS) �d�l�ύX ���f��686
*/

package webbroker3.aio;

import java.util.Hashtable;
import java.util.Map;

import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioJudgeResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;


/**
 * �ב֕ۏ؋��A�g�̏����󋵂��擾�̏����N���X
 * 
 * @@author ���z (���u)
 * @@version 1.0
 */
public class WEB3AioFXTransStatusUtility extends WEB3AioAbstractStatusUtility
{
    /**
     * �ڂ��������X�e�[�^�X�J����(Map)
     */
    private static Map fxStatusMap = new Hashtable(); 
    
    /**
     * �ڂ��������X�e�[�^�X�J����(String[][])
     */
    private static String[][] itemKey = null;
    //initialize the fxStatusMap
    static
    {
        //�U�֏󋵋敪   ����M�敪    ��t���ʃR�[�h    �������    ��������敪   �����敪        
        
        String[][] itemKeytemp = 
        {
            //1> ���ϒ�  ���M��   �|   �|   �|  �|   -----> Q
            {WEB3TransferStatusDivDef.PROCESSING, 
                WEB3SendRcvDivDef.SEND_COMPLETE, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.Q},
            //2> ���ϒ�  ���̑�  �|   �|   �|  �|   -----> J  
            {WEB3TransferStatusDivDef.PROCESSING, 
                DefaultStatus.OTHER, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J},
            //3> ���ϊ���   ��M��    00000000    NO RECORD     NO RECORD    NO RECORD  -----> J
            {WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000, 
                null, 
                null, 
                null}, {WEB3AioJudgeResultDef.J},
            //4> ���σG���[  ��M��   00000105    �|   �|   �|  �|   -----> S        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.S},
            //5> ���σG���[     ��M��         00000199    �|   �|   �|  �|   -----> T        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.T},
            //6> ���σG���[     ��M��         00000204    �|   �|   �|  �|   -----> R        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000204, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.R},
            //7> ���σG���[     ��M��         00000205    �|   �|   �|  �|   -----> N
            {WEB3TransferStatusDivDef.PROCESS_ERROR,
                WEB3SendRcvDivDef.RECEIVE_COMPLETE,
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000205,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.N},
            //8> ���σG���[     ��M��         00000206    �|   �|   �|  �|   -----> O
            {WEB3TransferStatusDivDef.PROCESS_ERROR,
                WEB3SendRcvDivDef.RECEIVE_COMPLETE,
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000206,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.O},
            //9> ���σG���[     ��M��         00000207    �|   �|   �|  �|   -----> P
            {WEB3TransferStatusDivDef.PROCESS_ERROR,
                WEB3SendRcvDivDef.RECEIVE_COMPLETE,
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000207,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.P},
            //10> ���σG���[     ��M��         00000801    �|   �|   �|  �|   -----> U        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.U},    
            //11> ���σG���[     ��M��         00000501    �|   �|   �|  �|   -----> U        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.V},    
            //12> ���σG���[     ��M��         00000503    �|   �|   �|  �|   -----> U
            {WEB3TransferStatusDivDef.PROCESS_ERROR,
                WEB3SendRcvDivDef.RECEIVE_COMPLETE,
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000503,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.V},

            //13> ���σG���[     ��M��		���̑�    �|   �|   �|  �|   -----> J        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
				WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                DefaultStatus.OTHER, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J},
			//14> ���σG���[     ���̑�    �|   �|   �|  �|   -----> J        
			{WEB3TransferStatusDivDef.PROCESS_ERROR, 
				DefaultStatus.OTHER, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J}, 
            
            //15> ���ϊ���    ��M��     00000000   �P�F��t��      �O�F�����l     �|   -----> D       
            //16> ���ϊ���    ��M��     00000000   �P�F��t��      �O�F�����l     OTHER   -----> D       
            {WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000, 
                WEB3OrderStatusDef.ACCEPTED_OPENORDER, 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.OTHER}, {WEB3AioJudgeResultDef.D},
            //17> ���ϊ���  ��M��      00000000   �R�F������      �O�F�����l     �|   -----> E        
            {WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000, 
                WEB3OrderStatusDef.MODIFYED, 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.E},
            //18> ���ϊ���  ��M��     00000000   �U�F�������s     �O�F�����l     �|    -----> F        
            {WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000, 
                WEB3OrderStatusDef.MODIFY_FAIL_OPENORDER, 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.F},
            //19> ���ϊ���  ��M��     00000000   �P�F��t��      �O�F�����l  �X�F�G���[    -----> J
            {WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000, 
                WEB3OrderStatusDef.ACCEPTED_OPENORDER, 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                WEB3StatusDef.DATA_ERROR}, {WEB3AioJudgeResultDef.J},
            //20> ���̑�  �|   �|   �|  �|   �|   -----> J     
            {DefaultStatus.OTHER, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J},
        };
        itemKey = itemKeytemp;
        for (int m = 0; m < itemKey.length; m = m + 2)
        {
            fxStatusMap.put(new DefaultStatus(itemKey[m]), itemKey[m + 1][0]);
        }
    }
    
    /**
     * (get�����󋵃��b�Z�[�W�R�[�h)<BR>
     * �����ɂ��A�ב֕ۏ؋��A�g�̏ꍇ�A�����󋵂��擾���A�ԋp����<BR>
     * �u�c�a�X�V�d�l\10.���o��\���o���X�e�[�^�X�\���\.xls�v��<BR>
     *  �ב֕ۏ؋��A�g(2)���Q�Ƃ���<BR>
     * 
     * @@param l_transferStatusDiv - GFT�U�֏󋵃e�[�u���̐U�֏󋵋敪
     * @@param sendRcvDiv - GFT�U�֏󋵃e�[�u���̑���M�敪
     * @@param resultCode - GFT�U�֏󋵃e�[�u���̎�t���ʃR�[�h
     * @@param orderSatus - �����P�ʃe�[�u���̒������
     * @@param cancel_type - �����P�ʃe�[�u���̒�������敪
     * @@param status - ��t�L���[�e�[�u���̏����敪
     * @@return String
     */
    public String getResult(
        String l_transferStatusDiv, 
        String sendRcvDiv, 
        String resultCode,
        String orderSatus,
        String cancel_type,
        String status)
    {
        String[] l_strParams = new String[]{
            l_transferStatusDiv, 
            sendRcvDiv, 
            resultCode, 
            orderSatus,
            cancel_type,
            status};
        
        WEB3AioAbstractStatusUtility.DefaultStatus l_status = 
            new DefaultStatus(l_strParams);
        
        return this.getStatus(l_status);
    }
    

    /* (non-Javadoc)
     * @@see webbroker3.aio.WEB3AioAbstractStatusUtility#getStatusMap()
     */
    public Map getStatusMap()
    {
        return WEB3AioFXTransStatusUtility.fxStatusMap;
    }
}
@
