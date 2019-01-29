head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceDetailHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃n���h��(WEB3AdminSrvRegiServiceDetailHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceDetailsRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceDetailsResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceDetailService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃n���h��)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃n���h���N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceDetailHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceDetailHandler.class);

    /**
     * @@roseuid 416F415B01E4
     */
    public WEB3AdminSrvRegiServiceDetailHandler() 
    {
     
    }
    
    /**
     * (�T�[�r�X�ڍ׃��N�G�X�g)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׌����������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceDetailsResponse
     * @@roseuid 40F50CD00298
     */
    public WEB3AdminSrvRegiServiceDetailsResponse serviceDetailRequest(WEB3AdminSrvRegiServiceDetailsRequest l_request) 
    {
        final String STR_METHOD_NAME = " serviceDetailRequest(WEB3AdminSrvRegiServiceDetailsRequest) ";
            log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceDetailsResponse l_response = null;
        WEB3AdminSrvRegiServiceDetailService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceDetailService)Services.getService(WEB3AdminSrvRegiServiceDetailService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceDetailsResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׌����Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminSrvRegiServiceDetailsResponse) l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceDetailsResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׌����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
}
@
