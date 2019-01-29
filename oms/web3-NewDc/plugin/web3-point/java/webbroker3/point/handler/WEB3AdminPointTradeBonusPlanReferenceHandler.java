head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.02.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointTradeBonusPlanReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�n���h��(WEB3AdminPointTradeBonusPlanReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/06/14 �s�p(���u) �V�K�쐬
*/

package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3AdminPointTradeBonusPlanReferenceRequest;
import webbroker3.point.message.WEB3AdminPointTradeBonusPlanReferenceResponse;
import webbroker3.point.service.delegate.WEB3AdminPointTradeBonusPlanReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�n���h��)<BR>
 * �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�n���h���N���X<BR>
 */
public class WEB3AdminPointTradeBonusPlanReferenceHandler implements MessageHandler
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointTradeBonusPlanReferenceHandler.class);
    
    /**
     * @@roseuid 42AE33FF007D
     */
    public WEB3AdminPointTradeBonusPlanReferenceHandler() 
    {
     
    }
    
    /**
     * (get�Ɖ���)<BR>
     * �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     *  
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointTradeBonusPlanReferenceResponse
     * @@roseuid 42A500390201
     */
    public WEB3AdminPointTradeBonusPlanReferenceResponse getReferenceScreen(WEB3AdminPointTradeBonusPlanReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminPointTradeBonusPlanReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointTradeBonusPlanReferenceResponse l_response = null;
        WEB3AdminPointTradeBonusPlanReferenceService l_service = null;
        
        try
        {
            l_service = (WEB3AdminPointTradeBonusPlanReferenceService)
                Services.getService(WEB3AdminPointTradeBonusPlanReferenceService.class);//Exception
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
            
            l_response = (WEB3AdminPointTradeBonusPlanReferenceResponse) l_request.createResponse();
            
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
                " �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X���擾�Ɏ��s���܂����B",
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

            l_response = (WEB3AdminPointTradeBonusPlanReferenceResponse) l_service.execute(l_request);//WEB3BaseException
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
            
            l_response = (WEB3AdminPointTradeBonusPlanReferenceResponse)l_request.createResponse();
            
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
