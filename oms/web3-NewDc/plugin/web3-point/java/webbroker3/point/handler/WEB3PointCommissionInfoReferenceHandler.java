head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.02.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointCommissionInfoReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����萔���������Ɖ�n���h��(WEB3PointCommissionInfoReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3PointCommissionInfoReferenceRequest;
import webbroker3.point.message.WEB3PointCommissionInfoReferenceResponse;
import webbroker3.point.service.delegate.WEB3PointCommissionInfoReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����萔���������Ɖ�n���h��)<BR>
 * �����萔���������Ɖ�n���h���N���X<BR>
 * 
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3PointCommissionInfoReferenceHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PointCommissionInfoReferenceHandler.class);

    /**
     * @@roseuid 421D7B3802DE
     */
    public WEB3PointCommissionInfoReferenceHandler() 
    {
     
    }
    
    /**
     * (�����萔���������Ɖ�)<BR>
     * �����萔���������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointCommissionInfoReferenceResponse
     * @@roseuid 4207160B0174
     */
    public WEB3PointCommissionInfoReferenceResponse commissionInfoReference(WEB3PointCommissionInfoReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " commissionInfoReference(WEB3PointCommissionInfoReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3PointCommissionInfoReferenceResponse l_response = null;
        WEB3PointCommissionInfoReferenceService l_service = null;
        
        try
        {
            l_service = (WEB3PointCommissionInfoReferenceService)
                Services.getService(WEB3PointCommissionInfoReferenceService.class);//Exception
        }        
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            l_response = (WEB3PointCommissionInfoReferenceResponse) l_request.createResponse();
            
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;  
              
            log.error(
                l_request, 
                " �����萔���������Ɖ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response = (WEB3PointCommissionInfoReferenceResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            l_response = (WEB3PointCommissionInfoReferenceResponse)l_request.createResponse();
            
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, " �����萔���������Ɖ�T�[�r�X���擾���N�G�X�g�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
