head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��MMF������t�n���h��(WEB3MutualFrgnMmfOrderAcceptHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 �����F (���u) �V�K�쐬 (���f��534)
*/
package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptResponse;
import webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptRequest;
import webbroker3.mf.service.delegate.WEB3MutualFrgnMmfOrderAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O��MMF������t�n���h��)<BR>
 * �O��MMF������t�n���h���N���X<BR>
 * 
 * @@author �����F(���u)
 * @@version 1.0
 */
public class WEB3MutualFrgnMmfOrderAcceptHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFrgnMmfOrderAcceptHandler.class);
    
    /**
     * @@roseuid 45C440D701DB
     */
    public WEB3MutualFrgnMmfOrderAcceptHandler() 
    {
     
    }
    
    /**
     * (������t���N�G�X�g)<BR>
     * �O��MMF������t�������s���B<BR>
     * <BR>
     * �O��MMF������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ��`�Ȃ�<BR>
     * @@return webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptResponse
     * @@roseuid 45B9C8F101D7
     */
    public WEB3MutualFrgnMmfOrderAcceptResponse orderAcceptRequest(
        WEB3MutualFrgnMmfOrderAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "orderAcceptRequest(WEB3MutualFrgnMmfOrderAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFrgnMmfOrderAcceptService l_service = null;
        WEB3MutualFrgnMmfOrderAcceptResponse l_response = null;

        try
        {
            l_service =
                (WEB3MutualFrgnMmfOrderAcceptService) Services.getService(
                    WEB3MutualFrgnMmfOrderAcceptService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualFrgnMmfOrderAcceptResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O��MMF������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MutualFrgnMmfOrderAcceptResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualFrgnMmfOrderAcceptResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�O��MMF������t���������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
