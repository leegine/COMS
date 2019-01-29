head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.21.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioMutilBankStatusUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Z�@@�֌��ϘA�g�̏����󋵂��擾�̏����N���X(WEB3AioMutilBankStatusUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01 ���� (���u)  �V�K�쐬                 
Revesion History : 2007/05/08 �����q (���u)  �d�l�ύX No.724
*/

package webbroker3.aio;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import webbroker3.aio.define.WEB3AioJudgeResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionStatusDef;

/**
 * ���Z�@@�֌��ϘA�g�̏����󋵂��擾�̏����N���X�B<BR>
 * 
 * @@author ���� (���u)
 * @@version 1.0
 */

public class WEB3AioMutilBankStatusUtility extends WEB3AioAbstractStatusUtility
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
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �O�F������
            //�����P�ʃe�[�u��              [�������]
            //�����P�ʃe�[�u��              [��������敪]
            //���o���`�[��t�L���[�e�[�u��   [�����敪]
            //0)                           [���茋��]  �| ---> A      
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.NOT_DEAL,
                WEB3StartStatusFlgDef.NOT_DEAL,
                WEB3ResultStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,                
            }, 
            {WEB3AioJudgeResultDef.A},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �P�F�v����M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �O�F������
            //�����P�ʃe�[�u��              [�������]
            //�����P�ʃe�[�u��              [��������敪]
            //���o���`�[��t�L���[�e�[�u��   [�����敪]
            //2)                           [���茋��]  �| ---> A   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.REPUEST_RECEIPT,
                WEB3StartStatusFlgDef.NOT_DEAL,
                WEB3ResultStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.A},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �O�F������
            //�����P�ʃe�[�u��              [�������]
            //�����P�ʃe�[�u��              [��������敪]
            //���o���`�[��t�L���[�e�[�u��   [�����敪]
            //4)                           [���茋��]  �| ---> A   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.NOT_DEAL,
                WEB3ResultStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.A},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �P�F�v����M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �O�F������
            //�����P�ʃe�[�u��              [�������]
            //�����P�ʃe�[�u��              [��������敪]
            //���o���`�[��t�L���[�e�[�u��   [�����敪]
            //6)                           [���茋��]  �| ---> A   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.REPUEST_RECEIPT,
                WEB3ResultStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.A},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �O�F������
            //�����P�ʃe�[�u��              [�������]
            //�����P�ʃe�[�u��              [��������敪]
            //���o���`�[��t�L���[�e�[�u��   [�����敪]
            //8)                           [���茋��]  �| ---> P   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.P},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] ���̑�
            //�����P�ʃe�[�u��              [�������]
            //�����P�ʃe�[�u��              [��������敪]
            //���o���`�[��t�L���[�e�[�u��   [�����敪]
            //10)                           [���茋��]  �| ---> B   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.B},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     ���̑�
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] ���̑�
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] ���̑�
            //�����P�ʃe�[�u��              [�������]
            //�����P�ʃe�[�u��              [��������敪]
            //���o���`�[��t�L���[�e�[�u��   [�����敪]
            //12)                          [���茋��]  �| ---> C   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.C},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �P�F�ʒm��M
            //�����P�ʃe�[�u��              [�������]           �P�F��t�ρi�V�K�����j 
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           ���̑� 
            //14)                          [���茋��]  �| ---> D   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.OTHER,      
            }, 
            {WEB3AioJudgeResultDef.D},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �P�F�ʒm��M
            //�����P�ʃe�[�u��              [�������]           �R�F�����ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]
            //16)                          [���茋��]  �| ---> E   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT,
                OrderStatusEnum.ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.E},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �P�F�ʒm��M
            //�����P�ʃe�[�u��              [�������]           �U�F�������s�i�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]
            //18)                          [���茋��]  �| ---> F   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT,
                OrderStatusEnum.NOT_ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.F},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �P�F�ʒm��M
            //�����P�ʃe�[�u��              [�������]           �P�F��t�ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]          �X�F�G���[
            //20)                          [���茋��]  �| ---> J   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                WEB3StatusDef.DATA_ERROR,      
            }, 
            {WEB3AioJudgeResultDef.J},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �P�F�ʒm��M
            //�����P�ʃe�[�u��              [�������]           NO RECORD
            //�����P�ʃe�[�u��              [��������敪]       NO RECORD
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           NO RECORD
            //22)                          [���茋��]  �| ---> E   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT,
                null,
                null,
                null,      
            }, 
            {WEB3AioJudgeResultDef.E},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �Q�F�������M
            //�����P�ʃe�[�u��              [�������]          �P�F��t�ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           ���̑�
            //24)                          [���茋��]  �| ---> D   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.OTHER,      
            }, 
            {WEB3AioJudgeResultDef.D},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �Q�F�������M
            //�����P�ʃe�[�u��              [�������]          �R�F�����ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //26)                          [���茋��]  �| ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                OrderStatusEnum.ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.E},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �Q�F�������M
            //�����P�ʃe�[�u��              [�������]          �U�F�������s�i�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //28)                          [���茋��]  �| ---> F 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                OrderStatusEnum.NOT_ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.F},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �Q�F�������M
            //�����P�ʃe�[�u��              [�������]          �P�F��t�ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           �X�F�G���[
            //30)                          [���茋��]  �| ---> J 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                WEB3StatusDef.DATA_ERROR,      
            }, 
            {WEB3AioJudgeResultDef.J},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �Q�F�������M
            //�����P�ʃe�[�u��              [�������]          �P�S�F�����ρi��������j
            //�����P�ʃe�[�u��              [��������敪]        �R�F�S���������
            //���o���`�[��t�L���[�e�[�u��   [�����敪]
            //                          [���茋��]  �| ---> I
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                OrderStatusEnum.CANCELLED.intValue() + "",
                WEB3ModifyCancelTypeDef.CANCELED,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.I},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �Q�F�������M
            //�����P�ʃe�[�u��              [�������]          NO RECORD
            //�����P�ʃe�[�u��              [��������敪]       NO RECORD
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           NO RECORD
            //32)                          [���茋��]  �| ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                null,
                null,
                null,      
            }, 
            {WEB3AioJudgeResultDef.E},
       
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �W�F�]�͌v�Z���s
            //�����P�ʃe�[�u��              [�������]          �P�F��t�ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           ���̑�
            //34)                          [���茋��]  �| ---> K   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.OTHER,
            },
            {WEB3AioJudgeResultDef.K},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �W�F�]�͌v�Z���s
            //�����P�ʃe�[�u��              [�������]          �R�F�����ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //36)                          [���茋��]  �| ---> L 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL,
                OrderStatusEnum.ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.L},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �W�F�]�͌v�Z���s
            //�����P�ʃe�[�u��              [�������]          �U�F�������s�i�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //38)                          [���茋��]  �| ---> M 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL,
                OrderStatusEnum.NOT_ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.M},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �W�F�]�͌v�Z���s
            //�����P�ʃe�[�u��              [�������]          �P�F��t�ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           �X�F�G���[
            //40)                          [���茋��]  �| ---> J 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                WEB3StatusDef.DATA_ERROR,
            },
            {WEB3AioJudgeResultDef.J},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �W�F�]�͌v�Z���s
            //�����P�ʃe�[�u��              [�������]          NO RECORD
            //�����P�ʃe�[�u��              [��������敪]       NO RECORD
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           NO RECORD
            //42)                          [���茋��]  �| ---> L 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL,
                null,
                null,
                null,
            },
            {WEB3AioJudgeResultDef.L},                
                
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �X�F�]�͍Čv�Z����
            //�����P�ʃe�[�u��              [�������]          �P�F��t�ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           ���̑�
            //44)                          [���茋��]  �| ---> D   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.OTHER,
            },
                    {WEB3AioJudgeResultDef.D},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �X�F�]�͍Čv�Z����
            //�����P�ʃe�[�u��              [�������]          �R�F�����ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //46)                          [���茋��]  �| ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE,
                OrderStatusEnum.ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.E},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �X�F�]�͍Čv�Z����
            //�����P�ʃe�[�u��              [�������]          �U�F�������s�i�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //48)                          [���茋��]  �| ---> F 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE,
                OrderStatusEnum.NOT_ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.F},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �X�F�]�͍Čv�Z����
            //�����P�ʃe�[�u��              [�������]          �P�F��t�ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           �X�F�G���[
            //50)                          [���茋��]  �| ---> J 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                WEB3StatusDef.DATA_ERROR,
            },
            {WEB3AioJudgeResultDef.J},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �X�F�]�͍Čv�Z����
            //�����P�ʃe�[�u��              [�������]          NO RECORD
            //�����P�ʃe�[�u��              [��������敪]       NO RECORD
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           NO RECORD
            //52)                          [���茋��]  �| ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE,
                null,
                null,
                null,
            },
            {WEB3AioJudgeResultDef.E},
          
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] A�F���ύď�������
            //�����P�ʃe�[�u��              [�������]          �P�F��t�ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           ���̑�
            //54)                          [���茋��]  �| ---> D  
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.OTHER,
            },
            {WEB3AioJudgeResultDef.D},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] A�F���ύď�������
            //�����P�ʃe�[�u��              [�������]          �R�F�����ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //56)                          [���茋��]  �| ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE,
                OrderStatusEnum.ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.E},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] A�F���ύď�������
            //�����P�ʃe�[�u��              [�������]          �U�F�������s�i�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //58)                          [���茋��]  �| ---> F 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE,
                OrderStatusEnum.NOT_ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.F},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] A�F���ύď�������
            //�����P�ʃe�[�u��              [�������]          �P�F��t�ρi�V�K�����j
            //�����P�ʃe�[�u��              [��������敪]       �O�F�����l
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           �X�F�G���[
            //60)                          [���茋��]  �| ---> J 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                WEB3StatusDef.DATA_ERROR,
            },
            {WEB3AioJudgeResultDef.J},            
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] A�F���ύď�������
            //�����P�ʃe�[�u��              [�������]          NO RECORD
            //�����P�ʃe�[�u��              [��������敪]       NO RECORD
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           NO RECORD
            //62)                          [���茋��]  �| ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE,
                null,
                null,
                null,
            },
            {WEB3AioJudgeResultDef.E},
                
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �P�F���ϊ���
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     ���̑�
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] ���̑�
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] ���̑�
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //64)                          [���茋��]  �| ---> B 
            {
                WEB3TransactionStatusDef.OK,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.B},
                    
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �Q�F���ϒ��~
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �R�F�ʒm�G���[�iFAIL)
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //66)                          [���茋��]  �| ---> G 
            {
                WEB3TransactionStatusDef.NG,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_ERROR_FAIL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.G},
                    
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �Q�F���ϒ��~
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     ���̑�
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] ���̑�
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] ���̑�
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //68)                          [���茋��]  �| ---> B 
            {
                WEB3TransactionStatusDef.NG,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.B},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �R�F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] 
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] 
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //70)                          [���茋��]  �| ---> C 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �R�F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �P�F�v����M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] 
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] 
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //72)                          [���茋��]  �| ---> C 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.REPUEST_RECEIPT,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �R�F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �O�F������
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] 
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //74)                          [���茋��]  �| ---> C 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},
                
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �R�F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �P�F�v����M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] 
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //76)                          [���茋��]  �| ---> C 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.REPUEST_RECEIPT,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},    
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �R�F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �S�F�ʒm�G���[�iERROR)
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //78)                          [���茋��]  �| ---> H 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_ERROR_ERROR,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.H},    
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �R�F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �U�F�Z�b�V�����G���[�iCOMPLETE)
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //80)                          [���茋��]  �| ---> N 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SESSION_ERROR_COMPLETE,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.N},   
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �R�F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] �V�F�Z�b�V�����G���[�iCOMPLETE�ȊO)
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //82)                          [���茋��]  �| ---> O
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SESSION_ERROR_COMPLETE_EXCEPT,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.O},   
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �R�F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] ���̑�
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //84)                          [���茋��]  �| ---> B
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.B},  
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �R�F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] �T�F�L�����Z��
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] 
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //86)                          [���茋��]  �| ---> I
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.CANCEL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.I},  
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �R�F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     �Q�F�������M
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] ���̑�
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] ���̑�
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //88)                          [���茋��]  �| ---> C
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},  
                                    
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           �R�F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     ���̑�
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] ���̑�
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] ���̑�
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //90)                          [���茋��]  �| ---> C
            {
                WEB3TransactionStatusDef.ERROR,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},  
            
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [�����敪]           ���̑��F�G���[
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(����)]     
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ϊJ�n)] 
            //���Z�@@�֘A�g���o���󋵃e�[�u�� [����FLAG(���ό���)] 
            //�����P�ʃe�[�u��              [�������]          
            //�����P�ʃe�[�u��              [��������敪]       
            //���o���`�[��t�L���[�e�[�u��   [�����敪]           
            //92)                          [���茋��]  �| ---> J
            {
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
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
     * �����ɂ��A�I�����C�������̏ꍇ�A�����󋵂��擾���A�ԋp����<BR>
     * �u�c�a�X�V�d�l\10.���o��\���o���X�e�[�^�X�\���\.xls�v�̋��Z�@@�֌��ϘA�g�i1�j���Q�Ƃ���<BR>
     *
     * @@param l_strTransferStatus - (���Z�@@�֘A�g���o���󋵃e�[�u��.�����敪)
     * @@param l_strOrderStatusFlag - (����FLAG�i�����j)
     * @@param l_strStartStatusFlg - (����FLAG�i���ϊJ�n�j)
     * @@param l_strResultStatusFlag - (����FLAG�i���ό��ʁj)
     * @@param l_strOrderStatus - (�������)
     * @@param l_strCancelType - (��������敪)
     * @@param l_strKeyTableStatus - (���o���`�[��t�L���[�e�[�u��.�����敪)
     * @@return String
     */
    public String getStatus(
        String l_strTransferStatus,
        String l_strOrderStatusFlag,
        String l_strStartStatusFlg,
        String l_strResultStatusFlag,
        String l_StrOrderStatus,
        String l_StrOrderCancelType,
        String l_StrKeyTableStatus)
    {
        String[] l_params = 
        {
            l_strTransferStatus, 
            l_strOrderStatusFlag, 
            l_strStartStatusFlg, 
            l_strResultStatusFlag, 
            l_StrOrderStatus, 
            l_StrOrderCancelType, 
            l_StrKeyTableStatus
        };
        
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
