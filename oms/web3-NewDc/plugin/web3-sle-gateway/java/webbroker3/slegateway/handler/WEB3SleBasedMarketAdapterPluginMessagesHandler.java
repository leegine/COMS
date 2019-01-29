head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.02.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleBasedMarketAdapterPluginMessagesHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SleBasedMarketAdapterPluginMessagesHandler�N���X
Author Name      : Daiwa Institute of Research
Revision History : 2006/04/24 �� �V�K�쐬
Revision History : 2006/09/22 ���iFLJ) WEB3BaseException�n���h��������ǉ�
Revision History : 2006/10/26 �� (FLJ) �񓯊�(Asyn)�T�[�r�X����                  
*/
package webbroker3.slegateway.handler;

import webbroker3.slegateway.message.WEB3ProcessSleRecoveryRequest;
import webbroker3.slegateway.message.WEB3ProcessSleRecoveryResponse;
import webbroker3.slegateway.message.WEB3ProcessSleSendqRequest;
import webbroker3.slegateway.message.WEB3ProcessSleSendqResponse;
import webbroker3.slegateway.service.delegate.WEB3SleRecoveryProcessorManagerService;
import webbroker3.slegateway.service.delegate.WEB3SleSendqProcessorManagerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.message.Response;

/**
 * plugin�̃n���h���N���X.
 */
public class WEB3SleBasedMarketAdapterPluginMessagesHandler implements
        MessageHandler {

    private static final WEB3LogUtility m_log = WEB3LogUtility
            .getInstance(WEB3SleBasedMarketAdapterPluginMessagesHandler.class);

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Send_q���M�T�[�r�X�̃C���X�^���X���쐬���A�T�[�r�X���N������
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Response handleSendQ(final WEB3ProcessSleSendqRequest l_request) {
    	
		final String STR_METHOD_NAME = " handleSendQ(WEB3ProcessSleSendqRequest )";
		m_log.entering(STR_METHOD_NAME);
    	
		if (l_request == null)
		{
			m_log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + STR_METHOD_NAME,
				"���N�G�X�g��null�ł��B");
		}
    	
		WEB3ProcessSleSendqResponse l_response = null;
		WEB3SleSendqProcessorManagerService l_service = null;
       
        try
        {
        	l_service = (WEB3SleSendqProcessorManagerService)Services.getService(WEB3SleSendqProcessorManagerService.class);
			if (l_service == null)
			{
				m_log.exiting(STR_METHOD_NAME);
				throw new WEB3BaseRuntimeException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80002,
					this.getClass().getName() + STR_METHOD_NAME,
					"send_q���M�T�[�r�X���擾�Ɏ��s���܂����B");
			}
			l_response =(WEB3ProcessSleSendqResponse)l_service.execute(l_request);
        }
		catch (WEB3BaseException l_ex)
		{
			l_response = (WEB3ProcessSleSendqResponse)l_request.createResponse();
			if (l_response == null)
			{
				m_log.exiting(STR_METHOD_NAME);
				throw new WEB3BaseRuntimeException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80002,
					this.getClass().getName() + STR_METHOD_NAME);
			}
			l_response.errorInfo = l_ex.getErrorInfo();
			m_log.error(
			 l_request, 
				"send_q���M�������{�Ɏ��s���܂����B",
				l_response.errorInfo,
				l_ex);
		}
		catch (Exception l_ex)
		{
			l_response = (WEB3ProcessSleSendqResponse) l_request.createResponse();
			if (l_response == null)
			{
				m_log.exiting(STR_METHOD_NAME);
				throw new WEB3BaseRuntimeException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80002,
					this.getClass().getName() + STR_METHOD_NAME);
			}
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
			m_log.error(
				l_request, 
				"send_q���M�������{���������{�Ɏ��s���܂����B",
				l_response.errorInfo, 
				l_ex);
		}
		
        m_log.exiting(STR_METHOD_NAME);

        return l_response;

    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Recovery���M�T�[�r�X�̃C���X�^���X���쐬���A�T�[�r�X���N������
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Response handleRecovery(final WEB3ProcessSleRecoveryRequest req) {
        m_log.entering("handle(final ProcessSleRecoveryRequest req)");

        WEB3SleRecoveryProcessorManagerService service = (WEB3SleRecoveryProcessorManagerService)Services.getService(WEB3SleRecoveryProcessorManagerService.class);
        service.initService(req);
        service.startProcessor();


        WEB3ProcessSleRecoveryResponse response = new WEB3ProcessSleRecoveryResponse();
        response.date = new java.sql.Date(System.currentTimeMillis());
       
        m_log.exiting("handle(final ProcessSleRecoveryRequest req)");

        return response;

    }    
}@
