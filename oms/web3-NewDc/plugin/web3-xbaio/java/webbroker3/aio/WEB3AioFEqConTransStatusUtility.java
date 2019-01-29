head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.30.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioFEqConTransStatusUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���U�֘A�g�̏����󋵂��擾�̏����N���X(WEB3AioFEqConTransStatusUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/09 �ʉ� �a�m (SRA) �V�K�쐬 
*/

package webbroker3.aio;

import java.util.Hashtable;
import java.util.Map;

import webbroker3.aio.define.WEB3AioJudgeResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;


/**
 * �O���U�֘A�g�̏����󋵂��擾�̏����N���X
 * 
 * @@author �ʉ� �a�m (SRA)
 * @@version 1.0
 */
public class WEB3AioFEqConTransStatusUtility extends WEB3AioAbstractStatusUtility
{
	/**
	 * �ڂ��������X�e�[�^�X�J����(Map)
	 */
	private static Map feqConStatusMap = new Hashtable(); 
    
	/**
	 * �ڂ��������X�e�[�^�X�J����(String[][])
	 */
	private static String[][] itemKey = null;
	//initialize the feqConStatusMap
	static
	{
		//�U�֏󋵋敪   ����M�敪    ��t���ʃR�[�h    �������    ��������敪        
        
		String[][] itemKeytemp = 
		{
			//1> ���ϒ�  �|  �|  �|  �|  -----> Q
			{WEB3TransferStatusDivDef.PROCESSING, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY}, {WEB3AioJudgeResultDef.Q},
			//2> ���σG���[  �|  �|  �|  �|  -----> J        
			{WEB3TransferStatusDivDef.PROCESS_ERROR, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J},
			//3> ���ϊ���  �|  �|  �P�F��t��  �O�F�����l  -----> D       
			{WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				WEB3OrderStatusDef.ACCEPTED_OPENORDER, 
				WEB3ModifyCancelTypeDef.INITIAL_VALUE}, {WEB3AioJudgeResultDef.D},
			//4> ���ϊ���  �|  �|  �R�F������  �O�F�����l  -----> E        
			{WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				WEB3OrderStatusDef.MODIFYED, 
				WEB3ModifyCancelTypeDef.INITIAL_VALUE}, {WEB3AioJudgeResultDef.E},
			//5> ���ϊ���  �|  �|  �U�F�������s  �O�F�����l  -----> F        
			{WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				WEB3OrderStatusDef.MODIFY_FAIL_OPENORDER, 
				WEB3ModifyCancelTypeDef.INITIAL_VALUE}, {WEB3AioJudgeResultDef.F},
			//6> ���ϊ���  �|  �|  �P�F��t��  �O�F�����l  -----> J
			{WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				WEB3OrderStatusDef.ACCEPTED_OPENORDER, 
				WEB3ModifyCancelTypeDef.INITIAL_VALUE}, {WEB3AioJudgeResultDef.J},
			//7> ���ϊ���  �|  �|  NO RECORD  NO RECORD  -----> J
			{WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				null, 
				null}, {WEB3AioJudgeResultDef.J},
			//8> ���  �|  �|  �P�S�F������(���)  �R�F�S���������  -----> I
			{WEB3TransferStatusDivDef.CANCEL, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				WEB3OrderStatusDef.MODIFYED_CANCELORDER, 
				WEB3ModifyCancelTypeDef.CANCELED}, {WEB3AioJudgeResultDef.I},
			//9> ���̑�  �|  �|  �|  �|  -----> J     
			{DefaultStatus.OTHER, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J},
		};
		itemKey = itemKeytemp;
		for (int m = 0; m < itemKey.length; m = m + 2)
		{
			feqConStatusMap.put(new DefaultStatus(itemKey[m]), itemKey[m + 1][0]);
		}
	}
    
	/**
	 * (get�����󋵃��b�Z�[�W�R�[�h)<BR>
	 * �����ɂ��A�O���U�֘A�g�̏ꍇ�A�����󋵂��擾���A�ԋp����<BR>
	 * �u�c�a�X�V�d�l\10.���o��\���o���X�e�[�^�X�\���\.xls�v��<BR>
	 *  �O�������A�g(3)���Q�Ƃ���<BR>
	 * 
	 * @@param l_transferStatusDiv - UWG�U�֏󋵃e�[�u���̐U�֏󋵋敪
	 * @@param sendRcvDiv - UWG�U�֏󋵃e�[�u���̑���M�敪
	 * @@param resultCode - UWG�U�֏󋵃e�[�u���̎�t���ʃR�[�h
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
		String cancel_type)
	{
		String[] l_strParams = new String[]{
			l_transferStatusDiv, 
			sendRcvDiv, 
			resultCode, 
			orderSatus,
			cancel_type};
        
		WEB3AioAbstractStatusUtility.DefaultStatus l_status = 
			new DefaultStatus(l_strParams);
        
		return this.getStatus(l_status);
	}
    

	/* (non-Javadoc)
	 * @@see webbroker3.aio.WEB3AioAbstractStatusUtility#getStatusMap()
	 */
	public Map getStatusMap()
	{
		return WEB3AioFEqConTransStatusUtility.feqConStatusMap;
	}
}
@
