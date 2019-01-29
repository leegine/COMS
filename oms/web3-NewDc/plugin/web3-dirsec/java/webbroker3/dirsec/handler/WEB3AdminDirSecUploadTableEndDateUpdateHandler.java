head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecUploadTableEndDateUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�n���h��(WEB3AdminDirSecUploadTableEndDateUpdateHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/08/11  ꎉ� (���u) �V�K�쐬
*/

package webbroker3.dirsec.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecUploadTableEndDateUpdateService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (�Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�n���h��)<BR>
 * �Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�n���h���N���X�B<BR>
 * 
 * @@author ꎉ�(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableEndDateUpdateHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecUploadTableEndDateUpdateHandler.class);
	
    /**
	 * (get�ꗗ���)<BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ꗗ��ʕ\���������s���B<BR>
	 * <BR>
	 * �Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�XImpl���擾���A <BR>
	 * execute()���\�b�h���R�[������B<BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 * �Ǘ��ҁE�A�b�v���[�h�e�[�u�����R�[�h�������ʃ��N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecUploadTableListResponse
	 * @@roseuid 44B324D002A4
	 */
	public WEB3AdminDirSecUploadTableListResponse getListScreen(
		WEB3AdminDirSecUploadTableListRequest l_request)
	{
		final String STR_METHOD_NAME = 
			"getDisplayScreen(WEB3AdminDirSecUploadTableListRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecUploadTableListResponse l_response = null;
		WEB3AdminDirSecUploadTableEndDateUpdateService l_service = null;

		// �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X���擾
		try
		{
			l_service = (WEB3AdminDirSecUploadTableEndDateUpdateService) Services.getService(
				WEB3AdminDirSecUploadTableEndDateUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableListResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, 
				"�Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�XImpl l���擾�Ɏ��s���܂����B",
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response = 
				(WEB3AdminDirSecUploadTableListResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableListResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, 
				"�A�b�v���[�h�e�[�u�����R�[�h�ꗗ��ʕ\�������̎��{�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

    /**
	 * (get�X�V�m�F���)<BR>
	 * �A�b�v���[�h�e�[�u���I�������X�V�m�F��ʕ\���������s���B <BR>
	 * <BR>
	 * �Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�XImpl���擾���A <BR>
	 * execute()���\�b�h���R�[������B<BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 * �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�m�F���N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecUploadTableUpdateConfirmResponse
	 * @@roseuid 44B324D002A4
	 */
	public WEB3AdminDirSecUploadTableUpdateConfirmResponse getUpdateConfirmScreen(
		WEB3AdminDirSecUploadTableUpdateConfirmRequest l_request)
	{
		final String STR_METHOD_NAME = 
			"getUpdateConfirm(WEB3AdminDirSecUploadTableUpdateConfirmRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecUploadTableUpdateConfirmResponse l_response = null;
		WEB3AdminDirSecUploadTableEndDateUpdateService l_service = null;

		// �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X���擾
		try
		{
			l_service = (WEB3AdminDirSecUploadTableEndDateUpdateService) Services.getService(
				WEB3AdminDirSecUploadTableEndDateUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateConfirmResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, 
				"�Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�XImpl l���擾�Ɏ��s���܂����B",
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateConfirmResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateConfirmResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, 
				"�A�b�v���[�h�e�[�u���I�������X�V�m�F��ʕ\�������̎��{�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
	
    /**
	 * (get�X�V�������)<BR>
	 * �A�b�v���[�h�e�[�u���I�������X�V������ʕ\���������s���B  <BR>
	 * <BR>
	 * �Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�XImpl���擾���A <BR>
	 * execute()���\�b�h���R�[������B<BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 * �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecUploadTableUpdateCompleteResponse
	 * @@roseuid 44B324D002A4
	 */
	public WEB3AdminDirSecUploadTableUpdateCompleteResponse getUpdateCompleteScreen(
		WEB3AdminDirSecUploadTableUpdateCompleteRequest l_request)
	{
		final String STR_METHOD_NAME = 
			"getUpdateComplete(WEB3AdminDirSecUploadTableUpdateCompleteRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecUploadTableUpdateCompleteResponse l_response = null;
		WEB3AdminDirSecUploadTableEndDateUpdateService l_service = null;

		// �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X���擾
		try
		{
			l_service = (WEB3AdminDirSecUploadTableEndDateUpdateService) Services.getService(
				WEB3AdminDirSecUploadTableEndDateUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateCompleteResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, 
				"�Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�XImpl l���擾�Ɏ��s���܂����B",
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateCompleteResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = 
				(WEB3AdminDirSecUploadTableUpdateCompleteResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, 
				"�A�b�v���[�h�e�[�u���I�������X�V������ʕ\�������̎��{�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
	
}
@
