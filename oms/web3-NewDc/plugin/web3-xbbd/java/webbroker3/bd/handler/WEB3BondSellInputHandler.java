head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����p���̓n���h��(WEB3BondSellInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3BondSellInputRequest;
import webbroker3.bd.message.WEB3BondSellInputResponse;
import webbroker3.bd.service.delegate.WEB3BondSellInputService;

/**
 * (�����p���̓n���h��)<BR>
 * �����p���̓n���h��<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondSellInputHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSellInputHandler.class); 
    
    /**
     * @@roseuid 44FBFD3A032C
     */
    public WEB3BondSellInputHandler() 
    {
     
    }
    
    /**
     * (�����p����)<BR>
     * �����p���͕\���������s���B<BR>
     * <BR>
     * �����p���̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����p���̓��N�G�X�g<BR>
     * @@return webbroker3.bd.message.WEB3BondSellInputResponse
     * @@roseuid 44C024F10066
     */
    public WEB3BondSellInputResponse inputBondSell(WEB3BondSellInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputBondSell(WEB3BondSellInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondSellInputService l_service = null;
        WEB3BondSellInputResponse l_response = null;
        
        try
        {
            //�����p���̓T�[�r�X���擾��
            l_service =
                (WEB3BondSellInputService)Services.getService(
                    WEB3BondSellInputService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondSellInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����p���̓T�[�r�X���擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[��
            l_response = 
                (WEB3BondSellInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondSellInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����p���͕\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
