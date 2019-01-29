head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  ꎉ� (���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g)<BR>
 * �Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g�N���X<BR>
 * 
 * @@author ꎉ�(���u)
 * @@version 1.0
 */

public class WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest extends WEB3GenRequest
{
	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = 
		"admin_dirsec_daemon_trigger_table_update_confirm";

	/**
	 * SerialVersionUID<BR>
	 */
	public static final long serialVersionUID = 200607192050L;
	
	/**
	 * ���O���[�e�B���e�B<BR>
	 */
	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(
			WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.class);
	
	/**
	 * (�X�V�X�e�[�^�X)<BR>
	 * �X�V�X�e�[�^�X<BR>
	 */
	public String updateTriggerStatus;

	/**
	 * @@roseuid 44BE20C1004E
	 */
	public WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest()
	{

	}

	/**
	 * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
	 * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
	 * <BR>
	 * �P)�X�V�X�e�[�^�X�̃`�F�b�N���s���B<BR>
	 * <BR>
	 * �P�|�P�jthis.�X�V�X�e�[�^�X == null�̏ꍇ�A <BR>
	 * �u�X�V�X�e�[�^�X�����w��ł��B�v<BR>
	 * �̗�O���X���[����B <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02516<BR>
	 * <BR>
	 * �P�|�Q�jthis.�X�V�X�e�[�^�X == ���p�����ȊO�̏ꍇ�A<BR>
	 * �u�X�V�X�e�[�^�X�𔼊p�����œ��͂��ĉ������B�v<BR>
	 * �̗�O���X���[����<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02517<BR>
	 * <BR>
	 * �P�|�R�jthis.�X�V�X�e�[�^�X�̃T�C�Y != 1���̏ꍇ�A<BR>
	 * �u�X�V�X�e�[�^�X��1���œ��͂��ĉ������B�v<BR>
	 * �̗�O���X���[����<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02518<BR>
	 * 
	 * @@throws WEB3BaseException
	 * @@roseuid 44B309B202F4
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);
		
		//�P�j�X�V�X�e�[�^�X�̃`�F�b�N���s���B
        //�P�|�P�jthis.�X�V�X�e�[�^�X == null�̏ꍇ�A 
		//�u�X�V�X�e�[�^�X�����w��ł��B�v �̗�O���X���[����B
		if (this.updateTriggerStatus == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02516, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"�X�V�X�e�[�^�X�����w��ł��B");
		}
		
		// �P�|�Q�jthis.�X�V�X�e�[�^�X == ���p�����ȊO�̏ꍇ�A 
		//�u�X�V�X�e�[�^�X�𔼊p�����œ��͂��ĉ������B�v �̗�O���X���[����
		if (!WEB3StringTypeUtility.isDigit(this.updateTriggerStatus))
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02517, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"�X�V�X�e�[�^�X�𔼊p�����œ��͂��ĉ������B");
		}
		
		//�P�|�R�jthis.�X�V�X�e�[�^�X�̃T�C�Y != 1���̏ꍇ�A 
		//�u�X�V�X�e�[�^�X��1���œ��͂��ĉ������B�v �̗�O���X���[����
		if (this.updateTriggerStatus.length() != 1)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02518,
				this.getClass().getName() + STR_METHOD_NAME, 
				"�X�V�X�e�[�^�X��1���œ��͂��ĉ������B");
		}
		log.exiting(STR_METHOD_NAME);

	}
	
	/**
	 * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
	 * <BR>
	 * 
	 * @@return ���X�|���X�I�u�W�F�N�g
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse(this);
	}
}
@
