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
filename	WEB3IfoBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�c���Ɖ�n���h���N���X(WEB3IfoBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ �V�K�쐬         
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3IfoBalanceReferenceService;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�c���Ɖ�n���h��)<BR>
 * �敨OP�c���Ɖ�n���h���N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3IfoBalanceReferenceHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoBalanceReferenceHandler.class);
  
    /**
     * (get�c���Ɖ�)<BR>
     * �����w���敨/�I�v�V�����c���Ɖ�����s���B<BR>
     * <BR>
     * �敨OP�c���Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �敨OP�c���Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceResponse
     * @@roseuid 41AAC6FC00B1
     */
    public WEB3FuturesOptionsBalanceReferenceResponse getBalanceReference(WEB3FuturesOptionsBalanceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = ".getBalanceReference(WEB3FuturesOptionsBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesOptionsBalanceReferenceResponse l_response = null;
        WEB3IfoBalanceReferenceService l_service = null;
  
        //�����w���I�v�V�����̐V�K�������R�����s���B
        try
        {
            l_service = (WEB3IfoBalanceReferenceService)Services.getService(WEB3IfoBalanceReferenceService.class);
  
            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        }
        catch (Exception l_ex)
        {
  
            l_response = (WEB3FuturesOptionsBalanceReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���I�v�V�����c���Ɖ�T�[�r�X�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
  
            return l_response;
        }
         
        //�����w���I�v�V�����c���Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3FuturesOptionsBalanceReferenceResponse) l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOptionsBalanceReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V�����c���Ɖ�̎c���Ɖ���Ɏ��s���܂����B", l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
  
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (get�c�����v)<BR>
     * �����w���敨/�I�v�V�����c�����v�������s���B<BR>
     * <BR>
     * �敨OP�c���Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �敨OP�c���Ɖ�c�����v���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalResponse
     * @@roseuid 41AD2DBD0178
     */
    public WEB3FuturesOptionsBalanceReferenceTotalResponse 
        getBalanceReferenceTotal(WEB3FuturesOptionsBalanceReferenceTotalRequest l_request) 
    {
        final String STR_METHOD_NAME = ".getBalanceReferenceTotal(WEB3FuturesOptionsBalanceReferenceTotalRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesOptionsBalanceReferenceTotalResponse l_response = null;
        WEB3IfoBalanceReferenceService l_service = null;
  
        //�����w���I�v�V�����̎c���Ɖ���R�����s���B
        try
        {
            l_service = (WEB3IfoBalanceReferenceService)Services.getService(WEB3IfoBalanceReferenceService.class);
  
            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        }
        catch (Exception l_ex)
        {
  
            l_response = (WEB3FuturesOptionsBalanceReferenceTotalResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���敨�I�v�V�����c�����v�T�[�r�X�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
  
            return l_response;
        }
         
        //�����w���I�v�V�����V�K�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3FuturesOptionsBalanceReferenceTotalResponse) l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOptionsBalanceReferenceTotalResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�I�v�V�����c�����v�̎c�����v�����Ɏ��s���܂����B", l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
  
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
