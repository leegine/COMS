head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��җ]�͒����n���h���N���X(WEB3AdminMutualTPAdjustHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 ��O�� (���u) �V�K�쐬                   
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPAdjustService;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M�Ǘ��җ]�͒����n���h���N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualTPAdjustHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPAdjustHandler.class);
 
    /**
     * (�]�͒����m�F)
     * �����M���]�͒����m�F�������s���B<BR> 
     * <BR>
     * ���M�Ǘ��җ]�͒����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminMutualTPAdjustConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2EDB5002C
     */
    public WEB3AdminMutualTPAdjustConfirmResponse tpAdjustConfirm(
        WEB3AdminMutualTPAdjustConfirmRequest l_request)
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "tpAdjustConfirm(WEB3AdminMutualTPAdjustConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);        
        
        //���M�Ǘ��җ]�͒����T�[�r�X���擾��
        WEB3AdminMutualTPAdjustService l_service = null;
        WEB3AdminMutualTPAdjustConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualTPAdjustService) 
                    Services.getService(WEB3AdminMutualTPAdjustService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualTPAdjustConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�Ǘ��җ]�͒����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;   
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMutualTPAdjustConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMutualTPAdjustConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���M�Ǘ��җ]�͒������������s���܂����B",
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;   
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�]�͒�������)
     * �����M���]�͒��������������s���B <BR>
     * <BR>
     * ���M�Ǘ��җ]�͒����T�[�r�X���擾���Aexecute()���\�b�h <BR>
     * ���R�[������B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminMutualTPAdjustCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2EDB5002C
     */
    public WEB3AdminMutualTPAdjustCompleteResponse tpAdjustComplete(
        WEB3AdminMutualTPAdjustCompleteRequest l_request)
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "tpAdjustComplete(WEB3AdminMutualTPAdjustCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);        
        
        //���M�Ǘ��җ]�͒����T�[�r�X���擾��
        WEB3AdminMutualTPAdjustService l_service = null;
        WEB3AdminMutualTPAdjustCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualTPAdjustService) 
                    Services.getService(WEB3AdminMutualTPAdjustService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualTPAdjustCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�Ǘ��җ]�͒����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;   
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminMutualTPAdjustCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMutualTPAdjustCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���M�Ǘ��җ]�͒������������s���܂����B",
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;   
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
