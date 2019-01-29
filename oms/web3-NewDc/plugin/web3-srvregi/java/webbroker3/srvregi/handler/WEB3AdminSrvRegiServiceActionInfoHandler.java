head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceActionInfoHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�������n���h��(WEB3AdminSrvRegiServiceActionInfoHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceActionInfoService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�������n���h��)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�������n���h���N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceActionInfoHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceActionInfoHandler.class);
    
    /**
     * @@roseuid 416F415C0000
     */
    public WEB3AdminSrvRegiServiceActionInfoHandler() 
    {
     
    }
    
    /**
     * (search�T�[�r�X����)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�������Ɖ�����s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�������T�[�r�X���擾���Aexecute( )<BR>
     * ���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X������񃊃N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryResponse
     * @@roseuid 40F519AF01EC
     */
    public WEB3AdminSrvRegiServiceHistoryResponse searchSrvAction(WEB3AdminSrvRegiServiceHistoryRequest l_request) 
    {
        final String STR_METHOD_NAME = " searchSrvAction(WEB3AdminSrvRegiServiceHistoryRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceHistoryResponse l_response = null;
        WEB3AdminSrvRegiServiceActionInfoService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceActionInfoService)Services.getService(WEB3AdminSrvRegiServiceActionInfoService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceHistoryResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �T�[�r�X���p�Ǘ��҃T�[�r�X�������Ɖ�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�������T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminSrvRegiServiceHistoryResponse) l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �T�[�r�X���p�Ǘ��҃T�[�r�X�������Ɖ�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
