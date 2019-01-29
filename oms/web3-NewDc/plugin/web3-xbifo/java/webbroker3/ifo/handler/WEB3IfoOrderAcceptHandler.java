head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright       : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name       :�敨OP������t�n���h���N���X(WEB3IfoOrderAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22   ���Ō� (Sinocom) �V�K�쐬 
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptService;
import webbroker3.ifo.message.WEB3IfoOrderAcceptRequest;
import webbroker3.ifo.message.WEB3IfoOrderAcceptResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP������t�n���h��)<BR>
 * �敨OP������t�n���h���N���X<BR>
 * @@author  : ���Ō�
 * @@version : 1.0
 */
public class WEB3IfoOrderAcceptHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoOrderAcceptHandler.class);
            
    /**
     * @@roseuid 40C07553031C
     */
    public WEB3IfoOrderAcceptHandler() 
    {
     
    }
    
    /**
     * (�敨OP������t���N�G�X�g)<BR>
     * �敨OP������t���������{����B<BR>
     * <BR>
     * �敨OP������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���I�v�V����������t���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3IfoOrderAcceptRequest
     * @@roseuid 4057CFE50169
     */
    public WEB3IfoOrderAcceptResponse optionOrderAcceptRequest(WEB3IfoOrderAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + "optionOrderAcceptRequest(WEB3IfoOrderAcceptRequest l_request)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoOrderAcceptResponse l_response = null;
        WEB3IfoOrderAcceptService l_service = null;
        
        //�����w���I�v�V���������V�K���T�[�r�X���擾����
        try
        {
            log.debug("Enter the try path");
            l_service =
                (WEB3IfoOrderAcceptService)Services.getService(
            WEB3IfoOrderAcceptService.class);
            log.debug("Exit the try path");
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            log.debug("Enter the catch path");
            l_response =
                (WEB3IfoOrderAcceptResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���I�v�V����������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.debug("Exit the catch path");
            return l_response;      
        }
        
        //�����w���I�v�V���������V�K���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            log.debug("Enter the try path");
            l_response =
                (WEB3IfoOrderAcceptResponse)l_service.execute(
                    l_request);
            log.debug("Exit the try path");
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Enter the catch path.");
            l_response =
                (WEB3IfoOrderAcceptResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V����������t�Ɏ��s���܂����B", l_ex);
            log.debug("Exit the try path.");
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
