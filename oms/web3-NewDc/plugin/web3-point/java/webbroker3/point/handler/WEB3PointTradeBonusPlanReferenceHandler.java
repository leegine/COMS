head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.02.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointTradeBonusPlanReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���[�h�{�[�i�X�v�����Ɖ�n���h��(WEB3PointTradeBonusPlanReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceResponse;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceRequest;
import webbroker3.point.service.delegate.WEB3PointTradeBonusPlanReferenceService;
import webbroker3.util.WEB3LogUtility;


/**
 * (�g���[�h�{�[�i�X�v�����Ɖ�n���h��)<BR>
 * �g���[�h�{�[�i�X�v�����Ɖ�n���h���N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointTradeBonusPlanReferenceHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PointTradeBonusPlanReferenceHandler.class);        
    
    /**
     * @@roseuid 421D7B380271
     */
    public WEB3PointTradeBonusPlanReferenceHandler() 
    {
     
    }
    
    /**
     * (�g���[�h�{�[�i�X�v�����Ɖ�)<BR>
     * �g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * �g���[�h�{�[�i�X�v�����Ɖ�<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointTradeBonusPlanReferenceResponse
     * @@roseuid 42071DE300F7
     */
    public WEB3PointTradeBonusPlanReferenceResponse tradeBonusPlanReference(WEB3PointTradeBonusPlanReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " tradeBonusPlanReference(WEB3PointTradeBonusPlanReferenceRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3PointTradeBonusPlanReferenceResponse l_response = null;
        WEB3PointTradeBonusPlanReferenceService l_service = null;      

        try
        {
            //get service
            l_service = 
                (WEB3PointTradeBonusPlanReferenceService)Services.getService(
                    WEB3PointTradeBonusPlanReferenceService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointTradeBonusPlanReferenceResponse)l_request.createResponse();
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
                "�g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response = (WEB3PointTradeBonusPlanReferenceResponse)l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3PointTradeBonusPlanReferenceResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�g���[�h�{�[�i�X�v�����Ɖ�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
