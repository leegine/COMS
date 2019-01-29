head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegisterHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Җ����o�^�n���h��(WEB3AdminBondProductRegisterHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 ����(���u) �V�K�쐬         
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondProductRegisterService;

/**
 * (���Ǘ��Җ����o�^�n���h��)<BR>
 * ���Ǘ��Җ����o�^�n���h�� �N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminBondProductRegisterHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductRegisterHandler.class); 
    /**
     * @@roseuid 44E336310000
     */
    public WEB3AdminBondProductRegisterHandler() 
    {
     
    }
    
    /**
     * (�����o�^���̓��N�G�X�g)<BR>
     * �Ǘ��ҍ������o�^���͏������s���B<BR>
     * <BR>
     * <BR>
     * ���Ǘ��Җ����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �������o�^���̓��N�G�X�g
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistInputResponse
     * @@roseuid 44B6061E038D
     */
    public WEB3AdminBondProductRegistInputResponse inputProductRegister(
        WEB3AdminBondProductRegistInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "inputProductRegister(WEB3AdminBondProductRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //���Ǘ��Җ����o�^�T�[�r�X���擾��
        WEB3AdminBondProductRegisterService l_service = null;
        WEB3AdminBondProductRegistInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondProductRegisterService) 
                Services.getService(WEB3AdminBondProductRegisterService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���Ǘ��Җ����o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminBondProductRegistInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "���Ǘ��Җ����o�^���͏��������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�����o�^�m�F���N�G�X�g)<BR>
     * �Ǘ��ҍ������o�^�m�F�������s���B <BR>
     * <BR>
     * <BR>
     * ���Ǘ��Җ����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �������o�^�m�F���N�G�X�g
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistConfirmResponse
     * @@roseuid 44B6061E038F
     */
    public WEB3AdminBondProductRegistConfirmResponse confirmProductRegister(
        WEB3AdminBondProductRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "confirmProductRegister(WEB3AdminBondProductRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //�Ǘ��ҍ������o�^�T�[�r�X���擾��
        WEB3AdminBondProductRegisterService l_service = null;
        WEB3AdminBondProductRegistConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondProductRegisterService) 
                Services.getService(WEB3AdminBondProductRegisterService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҍ������o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminBondProductRegistConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�Ǘ��ҍ������o�^�m�F���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�����o�^�������N�G�X�g)<BR>
     * �Ǘ��ҍ������o�^�����������s���B <BR>
     * <BR>
     * ���Ǘ��Җ����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - �����o�^�������N�G�X�g
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistCompleteResponse
     * @@roseuid 44BADCAB003E
     */
    public WEB3AdminBondProductRegistCompleteResponse completeProductRegister(
        WEB3AdminBondProductRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "completeProductRegister(WEB3AdminBondProductRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //�Ǘ��ҍ������o�^�T�[�r�X���擾��
        WEB3AdminBondProductRegisterService l_service = null;
        WEB3AdminBondProductRegistCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminBondProductRegisterService) 
                Services.getService(WEB3AdminBondProductRegisterService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҍ������o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminBondProductRegistCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�Ǘ��ҍ������o�^�������������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
