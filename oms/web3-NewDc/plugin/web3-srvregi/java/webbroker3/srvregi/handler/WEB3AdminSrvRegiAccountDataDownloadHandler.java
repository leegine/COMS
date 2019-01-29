head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�n���h��(WEB3AdminSrvRegiAccountDataDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�n���h��)<BR>
 * �Ǘ��Ҍڋq�f�[�^�_�E�����[�h�n���h���N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataDownloadHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataDownloadHandler.class);
    
    /**
     * @@roseuid 416F415C01A5
     */
    public WEB3AdminSrvRegiAccountDataDownloadHandler() 
    {
     
    }
    
    /**
     * (�ڋq�_�E�����[�h)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��҃_�E�����[�h���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadResponse
     * @@roseuid 4107082302BE
     */
    public WEB3AdminSrvRegiDownloadResponse acountDownload(WEB3AdminSrvRegiDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " acountDownload(WEB3AdminSrvRegiDownloadRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiDownloadResponse l_response = null;
        WEB3AdminSrvRegiAccountDataDownloadService l_service = null;        
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataDownloadService)Services.getService(
                    WEB3AdminSrvRegiAccountDataDownloadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminSrvRegiDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSrvRegiDownloadResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ڋq�_�E�����[�h�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
