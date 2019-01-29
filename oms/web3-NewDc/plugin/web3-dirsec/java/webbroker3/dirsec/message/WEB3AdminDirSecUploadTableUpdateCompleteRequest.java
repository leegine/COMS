head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecUploadTableUpdateCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������N�G�X�g(WEB3AdminDirSecUploadTableUpdateConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/11����(���u) �V�K�쐬
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������N�G�X�g)<BR>
 * �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������N�G�X�g�N���X�B
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableUpdateCompleteRequest extends WEB3GenRequest
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "adminDirsec_UploadTable_Complete";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200608111321L;
	
	/**
	 * ���O���[�e�B���e�B<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
			WEB3AdminDirSecUploadTableUpdateCompleteRequest.class);
	
	/**
	 * (�A�b�v���[�hID)<BR>
	 * �I�����ꂽ���R�[�h�̃A�b�v���[�hID�̔z��B
	 */
	public String[] administratorUploadId;
	
	/**
	 * (�Ïؔԍ�)<BR>
	 * �Ïؔԍ��B
	 */
	public String password;
	
	/**
	 * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>  
	 * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>  
	 * <BR>
	 * �P�j �A�b�v���[�hID�`�F�b�N<BR>  
	 * �@@�P�|�P�jthis.�A�b�v���[�hID == null ���� this.�A�b�v���[�hID�̒���==0 �̏ꍇ�A<BR>  
	 * �@@�@@�@@�@@�@@�u�X�V�Ώۃ��R�[�h���I������Ă��܂���v�̗�O���X���[����B<BR>  
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02523<BR>
	 * <BR>
	 * �Q�j �Ïؔԍ��`�F�b�N<BR> 
	 * �@@�Q�|�P�j�@@this.�Ïؔԍ� == null�̏ꍇ�A<BR>  
	 * �@@�@@�@@�@@�@@�u�Ïؔԍ������w��ł��B�v�̗�O���X���[����B<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_01090<BR>
	 * @@throws WEB3BaseException
	 */
	public void validate() throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validate()";
		log.entering(STR_METHOD_NAME);
		//�P�j �A�b�v���[�hID�`�F�b�N
		//�P�|�P�jthis.�A�b�v���[�hID == null ���� this.�A�b�v���[�hID�̒���==0 �̏ꍇ�A
		//�u�X�V�Ώۃ��R�[�h���I������Ă��܂���v�̗�O���X���[����B
		if (administratorUploadId == null || administratorUploadId.length == 0)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02523, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"�X�V�Ώۃ��R�[�h���I������Ă��܂���B");
		}
		
		//�Q�j �Ïؔԍ��`�F�b�N
		//�Q�|�P�j�@@this.�Ïؔԍ� == null�̏ꍇ�A
		//�u�Ïؔԍ������w��ł��B�v�̗�O���X���[����B
		if (password == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01090, 
				this.getClass().getName() + STR_METHOD_NAME, 
				"�Ïؔԍ������w��ł��B");
		}
		log.exiting(STR_METHOD_NAME);
	}
	
	/**
	 * @@roseuid 44BE20C0033C
	 */
	public WEB3AdminDirSecUploadTableUpdateCompleteRequest()
	{

	}

	/**
	 * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
	 * <BR>
	 * 
	 * @@return ���X�|���X�I�u�W�F�N�g
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminDirSecUploadTableUpdateCompleteResponse(this);
	}
}
@
