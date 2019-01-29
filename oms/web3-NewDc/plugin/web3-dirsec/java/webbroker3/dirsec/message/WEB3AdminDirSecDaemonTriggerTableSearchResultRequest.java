head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�f�[�����g���K�[�e�[�u���������ʃ��N�G�X�g(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.java)
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
 * (�Ǘ��ҁE�f�[�����g���K�[�e�[�u���������ʃ��N�G�X�g)<BR>
 * �Ǘ��ҁE�f�[�����g���K�[�e�[�u���������ʃ��N�G�X�g�N���X�B<BR>
 * 
 * @@author ꎉ�(���u)
 * @@version 1.0
 */

public class WEB3AdminDirSecDaemonTriggerTableSearchResultRequest extends WEB3GenRequest
{	
	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = 
		"admin_dirsec_daemon_trigger_table_search_result";

	/**
	 * SerialVersionUID<BR>
	 */
	public static final long serialVersionUID = 200607192050L;
	
	/**
	 * ���O���[�e�B���e�B<BR>
	 */
	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.class);

	/**
	 * (�X���b�h�ԍ�)<BR>
	 * �X���b�h�ԍ�<BR>
	 */
	public String threadNo;

	/**
	 * (�X�e�[�^�X)<BR>
	 * �X�e�[�^�X<BR>
	 */
	public String triggerStatus;

	/**
	 * @@roseuid 44BE20C1033C
	 */
	public WEB3AdminDirSecDaemonTriggerTableSearchResultRequest()
	{

	}

	/**
	 * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
	 * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
	 * <BR>
	 * �P�j�X���b�h�ԍ��̃`�F�b�N<BR>
	 * <BR>
	 * �P�|�P�jthis.�X���b�h�ԍ� == null�̏ꍇ�A<BR>
	 * �u�X���b�h�ԍ������w��ł��B�v<BR>
	 * �̗�O���X���[����<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02511<BR>
	 * <BR>
	 * �P�|�Q�jthis.�X���b�h�ԍ� == ���p�����ȊO�̏ꍇ�A<BR>
	 * �u�X���b�h�ԍ��𔼊p�����œ��͂��ĉ������B�v<BR>
	 * �̗�O���X���[����<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02512<BR>
	 * <BR>
	 * �P�|�R�jthis.�X���b�h�ԍ��̃T�C�Y �� 18���̏ꍇ�A<BR>
	 * �u�X���b�h�ԍ���18���ȉ��œ��͂��ĉ������B�v<BR>
	 * �̗�O���X���[����<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02513<BR>
	 * <BR>
	 * �Q�j������Ԃ̃`�F�b�N<BR>
	 * this.������� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s��<BR>
	 * <BR>
	 * �Q�|�P�jthis.������� == ���p�����ȊO�̏ꍇ�A<BR>
	 * �u�X�e�[�^�X�𔼊p�����œ��͂��ĉ������B�v<BR>
	 * �̗�O���X���[����<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02514<BR>
	 * <BR>
	 * �Q�|�Q�jthis.������Ԃ̃T�C�Y != 1���̏ꍇ�A<BR>
	 * �u�X�e�[�^�X��1���œ��͂��ĉ������B�v<BR>
	 * �̗�O���X���[����<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02515<BR>
	 * 
	 * @@throws WEB3BaseException
	 * @@roseuid 44B2FBD60114
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);

		//�P�j�X���b�h�ԍ��̃`�F�b�N �P�|�P�jthis.�X���b�h�ԍ� == null�̏ꍇ�A 
		//�X���b�h�ԍ������w��ł��B�v �̗�O���X���[����
		if (this.threadNo == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02511, 
				this.getClass().getName() + STR_METHOD_NAME, 
				 "�X���b�h�ԍ������w��ł��B");
		}
		
		//�P�|�Q�jthis.�X���b�h�ԍ� == ���p�����ȊO�̏ꍇ�A 
		//�X���b�h�ԍ��𔼊p�����œ��͂��ĉ������B�v 
		//�̗�O���X���[����
		if (!WEB3StringTypeUtility.isDigit(this.threadNo))
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02512, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"�X���b�h�ԍ��𔼊p�����œ��͂��ĉ������B");
		}
		
		//�P�|�R�jthis.�X���b�h�ԍ��̃T�C�Y �� 18���̏ꍇ 
		//�u�X���b�h�ԍ���18���ȉ��œ��͂��ĉ������B�v �̗�O���X���[����		
		if (this.threadNo.length() > 18)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02513, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"�X���b�h�ԍ���18���ȉ��œ��͂��ĉ������B");
		}
		
        //�Q�j������Ԃ̃`�F�b�N this.������� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s��
		//�Q�|�P�jthis.������� == ���p�����ȊO�̏ꍇ�A �u�X�e�[�^�X�𔼊p�����œ��͂��ĉ������B�v
		//�̗�O���X���[���� 
		if (this.triggerStatus != null)
		{
			if (!WEB3StringTypeUtility.isDigit(this.triggerStatus))
			{
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02514, 
					this.getClass().getName() + STR_METHOD_NAME, 
					"�X�e�[�^�X�𔼊p�����œ��͂��ĉ������B");
			}
			
			//�Q�|�Q�jthis.������Ԃ̃T�C�Y != 1���̏ꍇ�A 
		    //�u�X�e�[�^�X��1���œ��͂��ĉ������B�v �̗�O���X���[����
			if (this.triggerStatus.length() != 1)
			{
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02515, 
					this.getClass().getName() + STR_METHOD_NAME, 
					"�X�e�[�^�X��1���œ��͂��ĉ������B");
			}
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
		return new WEB3AdminDirSecDaemonTriggerTableSearchResultResponse(this);
	}
}
@
