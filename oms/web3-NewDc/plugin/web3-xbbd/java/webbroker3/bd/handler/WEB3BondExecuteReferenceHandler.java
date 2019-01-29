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
filename	WEB3BondExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ɖ�n���h�� (WEB3BondExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 ���� (���u) �V�K�쐬
*/
package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3BondExecuteReferenceRequest;
import webbroker3.bd.message.WEB3BondExecuteReferenceResponse;
import webbroker3.bd.service.delegate.WEB3BondExecuteReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���������Ɖ�n���h�� )<BR>
 * ���������Ɖ�n���h���N���X
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3BondExecuteReferenceHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondExecuteReferenceHandler.class); 
    /**
     * @@roseuid 44E336310000
     */
    public WEB3BondExecuteReferenceHandler() 
    {
     
    }
    
    /**
     * (get�������Ɖ�)<BR>
     * ���������Ɖ�������{����B <BR>
     * <BR>
     * ���������Ɖ�T�[�r�X���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return WEB3BondExecuteReferenceResponse
     * @@roseuid 44B6061E038D
     */
    public WEB3BondExecuteReferenceResponse getExecuteReference(
        WEB3BondExecuteReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getExecuteReference(WEB3BondExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //���������Ɖ�T�[�r�X���擾��
        WEB3BondExecuteReferenceService l_service = null;
        WEB3BondExecuteReferenceResponse l_response = null;
        try
        {
            l_service =
                (WEB3BondExecuteReferenceService) 
                Services.getService(WEB3BondExecuteReferenceService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_response =
                (WEB3BondExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���������Ɖ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3BondExecuteReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            l_response =
                (WEB3BondExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "���������Ɖ�������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
