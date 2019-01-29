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
filename	WEB3AdminDirSecAioOrderUnitTableUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�n���h��(WEB3AdminDirSecAioOrderUnitTableUpdateHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  ����� (���u) �V�K�쐬
*/

package webbroker3.dirsec.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAioOrderUnitTableUpdateService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (�Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�n���h��)<BR>
 * �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�n���h���N���X�B<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableUpdateHandler implements MessageHandler
{
	/**
	 * ���O�o�̓��[�e�B���e�B�B<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecAioOrderUnitTableUpdateHandler.class);

	/**
	 * @@roseuid 44BE20BF005D
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateHandler()
	{

	}

	/**
	 * (get�������)<BR>
	 * �����P�ʃe�[�u�����R�[�h������ʕ\���������s���B <BR>
	 * <BR>
	 * �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾���A <BR>
	 * execute()���\�b�h���R�[������B<BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 * �Ǘ��ҁE�����P�ʃe�[�u���������̓��N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableSearchInputResponse
	 * @@roseuid 444E0A340162
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchInputResponse getSearchScreen(
		WEB3AdminDirSecAioOrderUnitTableSearchInputRequest l_request)
	{
		final String STR_METHOD_NAME = " getSearchScreen(WEB3AdminDirSecOrderUnitTableSearchInputRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecAioOrderUnitTableSearchInputResponse l_response = null;
		WEB3AdminDirSecAioOrderUnitTableUpdateService l_service = null;

		// �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾
		try
		{
			l_service = (WEB3AdminDirSecAioOrderUnitTableUpdateService) Services.getService(
				WEB3AdminDirSecAioOrderUnitTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchInputResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, "�Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾�Ɏ��s���܂����B", 
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		try
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchInputResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchInputResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "�����P�ʃe�[�u�����R�[�h������ʕ\�������̎��{�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get�������ʉ��)<BR>
	 * �����P�ʃe�[�u�����R�[�h�������ʉ�ʕ\���������s���B <BR>
	 * <BR>
	 * �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾���A <BR>
	 * execute()���\�b�h���R�[������B<BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 * �Ǘ��ҁE�����P�ʃe�[�u���������ʃ��N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableSearchResultResponse
	 * @@roseuid 444E0A3502AA
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchResultResponse getSearchResultScreen(
		WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request)
	{
		final String STR_METHOD_NAME = " getSearchResultScreen(WEB3AdminDirSecOrderUnitTableSearchResultRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecAioOrderUnitTableSearchResultResponse l_response = null;
		WEB3AdminDirSecAioOrderUnitTableUpdateService l_service = null;

		// �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾
		try
		{
			l_service = (WEB3AdminDirSecAioOrderUnitTableUpdateService) Services.getService(
				WEB3AdminDirSecAioOrderUnitTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchResultResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, "�Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾�Ɏ��s���܂����B",
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		try
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchResultResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableSearchResultResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "�����P�ʃe�[�u�����R�[�h�������ʉ�ʕ\�������̎��{�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get�X�V�m�F���)<BR>
	 * �����P�ʃe�[�u��������ԍX�V�m�F��ʕ\���������s���B <BR>
	 * <BR>
	 * �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾���A <BR>
	 * execute()���\�b�h���R�[������B<BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 * �Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�m�F���N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableUpdateConfirmResponse
	 * @@roseuid 444E0A3702F7
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse getUpdateConfirmScreen(
		WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest l_request)
	{
		final String STR_METHOD_NAME = " getUpdateConfirmScreen(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse l_response = null;
		WEB3AdminDirSecAioOrderUnitTableUpdateService l_service = null;

		// �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾
		try
		{
			l_service = (WEB3AdminDirSecAioOrderUnitTableUpdateService) Services.getService(
				WEB3AdminDirSecAioOrderUnitTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, "�Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾�Ɏ��s���܂����B", 
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		try
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "�����P�ʃe�[�u��������ԍX�V�m�F��ʕ\�������̎��{�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get�X�V�������)<BR>
	 * �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V������ʕ\���������s���B<BR>
	 * <BR>
	 * �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾���A <BR>
	 * execute()���\�b�h���R�[������B<BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 * �Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�������N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableUpdateCompleteResponse
	 * @@roseuid 444E0A3703D1
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse getUpdateCompleteScreen(
		WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request)
	{
		final String STR_METHOD_NAME = " getUpdateCompleteScreen(WEB3AdminDirSecOrderUnitTableUpdateCompleteRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse l_response = null;
		WEB3AdminDirSecAioOrderUnitTableUpdateService l_service = null;

		// �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾
		try
		{
			l_service = (WEB3AdminDirSecAioOrderUnitTableUpdateService) Services.getService(
				WEB3AdminDirSecAioOrderUnitTableUpdateService.class);
		}
		catch (Exception l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(l_request, "�Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl���擾�Ɏ��s���܂����B", 
				l_response.errorInfo, l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		try
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse) l_service.execute(l_request);
		}
		catch (WEB3BaseException l_ex)
		{
			l_response = (WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse) l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "�Ǘ��Ғ����P�ʃe�[�u��������ԍX�V������ʕ\�������̎��{�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
}
@
