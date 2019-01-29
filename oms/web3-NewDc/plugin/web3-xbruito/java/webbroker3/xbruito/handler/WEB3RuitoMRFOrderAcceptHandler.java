head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMRFOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ���MRF������t�n���h���N���X(WEB3RuitoMRFOrderAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/13 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoMRFCancelAcceptServiceImpl;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptService;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.util.WEB3LogUtility;

/**
 * �ݐϓ���MRF������t�n���h���N���X<BR>
 */
public class WEB3RuitoMRFOrderAcceptHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFOrderAcceptHandler.class);
    /**
     * �ݐϓ���MRF������t�������s���B<BR>
     * <BR>
     * �ݐϓ���MRF������t�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �ݐϓ���MRF������t���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptRequest
     * @@roseuid 4058152B0031
     */
    public WEB3RuitoMRFOrderAcceptResponse mrfOrderAcceptRequest(
                WEB3RuitoMRFOrderAcceptRequest l_request)
    {
        String STR_METHOD_NAME = "mrfOrderAcceptRequest(" +
                "WEB3RuitoMRFOrderAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);
                
        if (l_request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");            
        }

        /**
         * ���O�o�̓��[�e�B���e�B�B<BR>
         */
        final WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3RuitoMRFCancelAcceptServiceImpl.class);

        // �ݐϓ���MRF�����t�T�[�r�X�I�u�W�F�N�g���擾����
        WEB3RuitoMRFOrderAcceptResponse l_response = null;
        WEB3RuitoMRFOrderAcceptService l_service = null;

        try
        {
            l_service =
                (WEB3RuitoMRFOrderAcceptService) Services.getService(
                    WEB3RuitoMRFOrderAcceptService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A
        // �G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response = (WEB3RuitoMRFOrderAcceptResponse) 
                            l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            // ��Е��X�戵�\�s��Params���擾�ł��܂���ł���
            log.error(l_request, "�ݐϓ���MRF�����t�T�[�r�X�̎擾�Ɏ��s���܂����B", 
            l_response.errorInfo, e);
            return l_response;
        }
        try
        {
            l_response = (WEB3RuitoMRFOrderAcceptResponse) 
                            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3RuitoMRFOrderAcceptResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, 
                    "�ݐϓ���MRF�����t�T�[�r�X�̎擾�Ɏ��s���܂����B", e);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����     
        return l_response;
    }
}
@
