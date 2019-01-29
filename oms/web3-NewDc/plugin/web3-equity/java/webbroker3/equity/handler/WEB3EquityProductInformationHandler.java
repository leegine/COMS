head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductInformationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������\���n���h���N���X(WEB3EquityProductInformationHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 SRA��� �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityProductInformationRequest;
import webbroker3.equity.message.WEB3EquityProductInformationResponse;
import webbroker3.equity.service.delegate.WEB3EquityProductInformationService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����������\���n���h���j�B<BR>
 * <BR>
 * �����������\���n���h���N���X
 * @@version 1.0
 */
public class WEB3EquityProductInformationHandler implements MessageHandler
{
	/**
	 * ���O�o�̓��[�e�B���e�B�B
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3EquityProductInformationHandler.class);

	/**
	 * @@roseuid XXXXXXXXXX
	 */
	public WEB3EquityProductInformationHandler()
	{

	}

	/**
	 * (�����������\�����N�G�X�g)<BR>
	 * �����������\���������s���B<BR>
	 * <BR>
	 * �����������\���T�[�r�X���擾���A<BR>
	 * execute()���\�b�h���R�[������B<BR>
	 * @@param l_request - �����������\�����N�G�X�g�I�u�W�F�N�g
	 * @@return webbroker3.equity.message.WEB3EquityProductInformationResponse
	 * @@roseuid XXXXXXXXXX
	 */
	public WEB3EquityProductInformationResponse equityProductInformationRequest(WEB3EquityProductInformationRequest l_request)
	{
		final String STR_METHOD_NAME = "equityProductInformationRequest()";
		log.entering(STR_METHOD_NAME);

		WEB3EquityProductInformationResponse l_response = null;
		WEB3EquityProductInformationService l_service = null;

		try
		{
			l_service =
				(WEB3EquityProductInformationService) Services.getService(
					WEB3EquityProductInformationService.class);
		}
		catch (Exception e)
		{
			l_response =
				(WEB3EquityProductInformationResponse) l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(
			l_request,
				"�����������\���Ɏ��s���܂����B",
				l_response.errorInfo,
				e);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}

		try
		{
			l_response =
				(WEB3EquityProductInformationResponse) l_service.execute(l_request);
		}
        catch (WEB3BaseException e)
        {
            log.error(l_request, "�����������\���Ɏ��s���܂����B", e);
            l_response =
                (WEB3EquityProductInformationResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_request, "�����������\���Ɏ��s���܂����B", l_bre);
            l_response =
                (WEB3EquityProductInformationResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
}
@
