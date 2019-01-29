head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBatoClientHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�d�q���ڑ��n���h��(WEB3IpoBatoClientHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/26 ���g(���u) �V�K�쐬
*/
package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOBatoUrlResponse;
import webbroker3.ipo.message.WEB3IPOBatoUrlRequest;
import webbroker3.ipo.service.delegate.WEB3IpoBatoClientService;
import webbroker3.util.WEB3LogUtility;

/**
 * (IPO�d�q���ڑ��n���h��)<BR>
 * IPO�d�q���ڑ��n���h���N���X
 * 
 * @@author ���g
 * @@version 1.0
 */
public class WEB3IpoBatoClientHandler implements MessageHandler 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoBatoClientHandler.class);
    
    /**
     * @@roseuid 43D8344F031C
     */
    public WEB3IpoBatoClientHandler() 
    {
     
    }
    
    /**
     * (�d�q��URL�擾)<BR>
     * �d�q��URL�擾����<BR>
     * <BR>
     * IPO�d�q���ڑ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - IPO�d�q��URL�擾���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IPOBatoUrlResponse
     * @@roseuid 43D08DD300AE
     */
    public WEB3IPOBatoUrlResponse getBatoUrl(WEB3IPOBatoUrlRequest l_request) 
    {

        final String STR_METHOD_NAME = " getBatoUrl(WEB3IPOBatoUrlRequest)";
                
        log.entering(STR_METHOD_NAME);

        WEB3IPOBatoUrlResponse l_response = null;
        WEB3IpoBatoClientService l_service = null;
             
        try
        {        
            // IPO�d�q���ڑ��T�[�r�X���擾��
            l_service = (WEB3IpoBatoClientService)
                Services.getService(WEB3IpoBatoClientService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_e)
        {
            l_response = (WEB3IPOBatoUrlResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO�d�q���ڑ��T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_e);
            return l_response;
        }
        
        try
        {        
            //IPO�d�q���ڑ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������
            l_response = (WEB3IPOBatoUrlResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3IPOBatoUrlResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�d�q��URL�擾�����̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3IPOBatoUrlResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�d�q��URL�擾�����̎��{�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
        
}
@
