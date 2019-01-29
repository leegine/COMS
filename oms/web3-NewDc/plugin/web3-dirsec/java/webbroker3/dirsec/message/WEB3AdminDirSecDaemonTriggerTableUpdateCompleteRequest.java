head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.java)
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

/**
 * (�Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g)<BR>
 * �Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�N���X<BR>
 * 
 * @@author ꎉ�(���u)
 * @@version 1.0
 */

public class WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest extends WEB3GenRequest
{
	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = 
		"admin_dirsec_daemon_trigger_table_update_complete";

	/**
	 * SerialVersionUID<BR>
	 */
	public static final long serialVersionUID = 200607192050L;
	
	/**
	 * ���O���[�e�B���e�B<BR>
	 */
	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.class);

	/**
	 * (�X���b�h�ԍ�)<BR>
	 * �X���b�h�ԍ�<BR>
	 */
	public String threadNo;

	/**
	 * (�X�V�X�e�[�^�X)<BR>
	 * �X�V�X�e�[�^�X<BR>
	 */
	public String updateTriggerStatus;

	/**
	 * (�Ïؔԍ�)<BR>
	 * �Ïؔԍ�<BR>
	 */
	public String password;

	/**
	 * @@roseuid 44BE20C101C5
	 */
	public WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest()
	{

	}

	/**
	 * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
	 * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
	 * <BR>
	 * �P�j�X���b�h�ԍ��̃`�F�b�N���s���B<BR>
	 * <BR>
	 * �P�|�P�jthis.�X���b�h�ԍ� == null�̏ꍇ�A <BR>
	 * �u�X���b�h�ԍ������w��ł��B�v<BR>
	 * �̗�O���X���[����B <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02511<BR>
	 * <BR>
	 * �Q�j�X�V�X�e�[�^�X�̃`�F�b�N���s���B<BR>
	 * <BR>
	 * �Q�|�P�jthis.�X�V�X�e�[�^�X == null�̏ꍇ�A <BR>
	 * �u�X�V�X�e�[�^�X�����w��ł��B�v<BR>
	 * �̗�O���X���[����B <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02516<BR>
	 * <BR>
	 * �R)�Ïؔԍ��̃`�F�b�N���s���B<BR>
	 * <BR>
	 * �R�|�P�jthis.�Ïؔԍ� == null�̏ꍇ�A <BR>
	 * �u�Ïؔԍ��������͂ł��B�v<BR>
	 * �̗�O���X���[����B <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02510<BR>
	 * 
	 * @@throws WEB3BaseException
	 * @@roseuid 44B30B8200C0
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);
		
        //�P�j�X���b�h�ԍ��̃`�F�b�N���s���B
	    //�P�|�P�jthis.�X���b�h�ԍ� == null�̏ꍇ�A 
		//�u�X���b�h�ԍ������w��ł��B�v �̗�O���X���[����B
		if (this.threadNo == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02511, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"�X���b�h�ԍ������w��ł��B");
		}
		
		//�Q�j�X�V�X�e�[�^�X�̃`�F�b�N���s���B
		//�Q�|�P�jthis.�X�V�X�e�[�^�X == null�̏ꍇ�A
		//�u�X�V�X�e�[�^�X�����w��ł��B�v �̗�O���X���[����B
		if (this.updateTriggerStatus == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02516, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"�X�V�X�e�[�^�X�����w��ł��B");
		}
		
		//�R)�Ïؔԍ��̃`�F�b�N���s���B
		//�R�|�P�jthis.�Ïؔԍ� == null�̏ꍇ�A 
		// �u�Ïؔԍ��������͂ł��B�v�̗�O���X���[����B
		if (this.password == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02510, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"�Ïؔԍ��������͂ł��B");
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
		return new WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse(this);
	}
}
@
