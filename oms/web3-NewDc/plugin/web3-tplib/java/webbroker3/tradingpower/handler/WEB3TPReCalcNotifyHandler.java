head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z�ʒm�n���h��(WEB3TPReCalcNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyRequest;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]�͌v�Z�ʒm�n���h��)
 */
public class WEB3TPReCalcNotifyHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPReCalcNotifyHandler.class);

    /**
     * @@roseuid 423541390077
     */
    public WEB3TPReCalcNotifyHandler()
    {

    }

    /**
     * (�]�͌v�Z�ʒm���N�G�X�g)<BR>
     * <BR>
     * �N���C�A���g��胊�N�G�X�g���󂯁A�]�͌v�Z�ʒm���X�|���X�𐶐����ԋp����B <BR>
     * <BR>
     * �P�j�@@�]�͌v�Z�ʒm�T�[�r�X�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@�]�͌v�Z�ʒm�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A <BR>
     * �@@�@@�@@�������ʂł���]�͌v�Z�ʒm���X�|���X�I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �R�j�@@�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��B<BR> 
     * <BR>
     * �S�j�@@�]�͌v�Z�ʒm���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_reCalcRequest - (�]�͌v�Z�ʒm���N�G�X�g)
     * @@return webbroker3.tradingpower.message.WEB3TPReCalcNotifyResponse
     * @@roseuid 41F4A66002A6
     */
    public WEB3TPReCalcNotifyResponse reCalcNotifyRequest(WEB3TPReCalcNotifyRequest l_reCalcRequest)
    {
        final String STR_METHOD_NAME = "reCalcNotifyRequest(WEB3TPReCalcNotifyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TPReCalcNotifyResponse l_response = null;
        WEB3TPReCalcNotifyService l_service = null;

        /*
         * �]�͌v�Z�ʒm�T�[�r�X�I�u�W�F�N�g���擾
         */
        try
        {
            l_service =
                (WEB3TPReCalcNotifyService)Services.getService(WEB3TPReCalcNotifyService.class);
        }
        catch (Exception e)
        {
            l_response = (WEB3TPReCalcNotifyResponse)l_reCalcRequest.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_reCalcRequest, "�]�͌v�Z�ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        /*
         * �]�͌v�Z�ʒm�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[��
         */
        try
        {
            l_response = (WEB3TPReCalcNotifyResponse)l_service.execute(l_reCalcRequest);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3TPReCalcNotifyResponse)l_reCalcRequest.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_reCalcRequest, "�]�͍Čv�Z�ʒm�����Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3TPReCalcNotifyResponse)l_reCalcRequest.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_reCalcRequest, "�]�͍Čv�Z�ʒm�����Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �]�͌v�Z�ʒm���X�|���X�I�u�W�F�N�g��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
