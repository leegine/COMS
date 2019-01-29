head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiSrvListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�n���h��(WEB3AdminSrvRegiSrvListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiSrvListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�n���h��)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�n���h���N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiSrvListHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiSrvListHandler.class);
    
    /**
     * @@roseuid 416F415B0138
     */
    public WEB3AdminSrvRegiSrvListHandler() 
    {
     
    }
    
    /**
     * (search�T�[�r�X)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�Ɖ�����s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceResponse
     * @@roseuid 40F5029003B1
     */
    public WEB3AdminSrvRegiServiceReferenceResponse searchSrv(WEB3AdminSrvRegiServiceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " searchSrv(WEB3AdminSrvRegiServiceReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceReferenceResponse l_response = null;
        WEB3AdminSrvRegiSrvListService l_service = null;
        
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�X���擾
        try
        {
            log.debug("l_service");
            l_service =
                (WEB3AdminSrvRegiSrvListService)Services.getService(
                WEB3AdminSrvRegiSrvListService.class);                  //Exception
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiServiceReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);    
            return l_response;      
        }
        
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            log.debug("l_response");
            l_response =
                (WEB3AdminSrvRegiServiceReferenceResponse)l_service.execute(
                    l_request);                        //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiServiceReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);    
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
