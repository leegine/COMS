head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����ʒm�n���h��(WEB3FuturesOrderNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderNotifyService;
import webbroker3.ifo.message.WEB3FuturesOrderNotifyResponse;
import webbroker3.ifo.message.WEB3FuturesOrderNotifyRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����w���敨�����ʒm�n���h��)<BR>
 * �����w���敨�����ʒm�n���h���N���X<BR>
 * @@author  : ������
 * @@version : 1.0
 */
public class WEB3FuturesOrderNotifyHandler implements MessageHandler 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderNotifyHandler.class);
   
    /**
     * @@roseuid 41AD6546029F
     */
    public WEB3FuturesOrderNotifyHandler() 
    {
    
    }
   
    /**
     * (�敨�����ʒm���N�G�X�g)<BR>
     * �����w���敨�����ʒm�������s���B<BR>
     * <BR>
     * �����w���敨�����ʒm�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���敨�����ʒm���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3FuturesOrderNotifyResponse
     * @@roseuid 417501300079
     */
    public WEB3FuturesOrderNotifyResponse futuresOrderNotifyRequest(WEB3FuturesOrderNotifyRequest l_request) 
    {
        final String METHOD_NAME = "futuresOrderNotifyRequest()";
        log.entering(METHOD_NAME);

        WEB3FuturesOrderNotifyResponse l_response = null;
        WEB3FuturesOrderNotifyService l_service = null;
        
        //�敨�����ʒm�T�[�r�X���擾
        try
        {
            l_service =
                (WEB3FuturesOrderNotifyService)Services.getService(
                    WEB3FuturesOrderNotifyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�敨�����ʒm�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //�敨�����ʒm�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3FuturesOrderNotifyResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨�����ʒm�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
