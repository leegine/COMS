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
filename	WEB3OptionsOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�����ʒm�n���h��(WEB3OptionsOrderNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3OptionsOrderNotifyService;
import webbroker3.ifo.message.WEB3OptionsOrderNotifyResponse;
import webbroker3.ifo.message.WEB3OptionsOrderNotifyRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP�����ʒm�n���h��)<BR>
 * �����w���I�v�V���������ʒm�n���h���N���X<BR>
 * @@author  : ������
 * @@version : 1.0
 */
public class WEB3OptionsOrderNotifyHandler implements MessageHandler 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsOrderNotifyHandler.class);

   
    /**
     * @@roseuid 41AAE845036B
     */
    public WEB3OptionsOrderNotifyHandler() 
    {
      
    }
   
    /**
     * (�I�v�V���������ʒm���N�G�X�g)<BR>
     * �����w���I�v�V���������ʒm�������s���B<BR>
     * <BR>
     * �����w���I�v�V���������ʒm�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���I�v�V���������ʒm���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3OptionsOrderNotifyResponse
     * @@roseuid 4163A6CC0358
     */
    public WEB3OptionsOrderNotifyResponse optionsOrderNotifyRequest(WEB3OptionsOrderNotifyRequest l_request) 
    {
        final String METHOD_NAME = "optionsOrderNotifyRequest()";
        log.entering(METHOD_NAME);

        WEB3OptionsOrderNotifyResponse l_response = null;
        WEB3OptionsOrderNotifyService l_service = null;
        
        //OP�����ʒm�T�[�r�X���擾
        try
        {
            l_service =
                (WEB3OptionsOrderNotifyService)Services.getService(
                    WEB3OptionsOrderNotifyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP�����ʒm�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //OP�����ʒm�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3OptionsOrderNotifyResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "OP�����ʒm�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
