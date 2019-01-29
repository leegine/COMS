head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoBaseInfoReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l����{���Ɖ�n���h��(WEB3AccInfoBaseInfoReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceRequest;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoBaseInfoReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l����{���Ɖ�n���h��)<BR>
 * ���q�l����{���Ɖ�n���h��<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoBaseInfoReferenceHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoBaseInfoReferenceHandler.class);
        
    /**
     * @@roseuid 418F3A1101A5
     */
    public WEB3AccInfoBaseInfoReferenceHandler() 
    {
     
    }
    
    /**
     * (��{���Ɖ�)<BR>
     * ��{���Ɖ�����s���B<BR>
     * <BR>
     * ���q�l����{���Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���q�l����{���Ɖ�N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceResponse
     * @@roseuid 4163B666033E
     */
    public WEB3AccInfoAccountBaseInfoReferenceResponse baseInfoReference(WEB3AccInfoAccountBaseInfoReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " baseInfoReference(WEB3AccInfoAccountBaseInfoReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoAccountBaseInfoReferenceResponse l_response = null;
        WEB3AccInfoBaseInfoReferenceService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AccInfoBaseInfoReferenceService)Services.getService(
                    WEB3AccInfoBaseInfoReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoAccountBaseInfoReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l����{���Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //���q�l����{���Ɖ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AccInfoAccountBaseInfoReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoAccountBaseInfoReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���q�l����{���Ɖ�T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
