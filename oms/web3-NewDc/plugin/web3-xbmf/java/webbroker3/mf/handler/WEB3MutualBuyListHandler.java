head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�ꗗ�Ɖ�n���h��(WEB3MutualBuyListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ��O�� (���u) �V�K�쐬
                         2004/08/23 ���� (���u) ���r���[ 
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualBuyListRequest;
import webbroker3.mf.message.WEB3MutualBuyListResponse;
import webbroker3.mf.service.delegate.WEB3MutualBuyListService;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M�����t�ꗗ�Ɖ�n���h��
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyListHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyListHandler.class);
 
    /**
     * (search����)<BR>
     * �����M�����t�ꗗ�Ɖ�������{����B<BR>
     * <BR>
     * �����M�����t�ꗗ�Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R<BR>�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualBuyListResponse
     * @@roseuid 40AC767801F3
     */
    public WEB3MutualBuyListResponse searchOrder(WEB3MutualBuyListRequest l_request) 
    {
        final String l_strMethodName = "searchOrder("
            + "WEB3MutualBuyListRequest l_request ";
        log.entering(l_strMethodName);
        
        WEB3MutualBuyListService l_service;
        WEB3MutualBuyListResponse l_response;
        try
        {
            l_service = 
                (WEB3MutualBuyListService)Services.getService(
                    WEB3MutualBuyListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualBuyListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����M�����t�ꗗ�Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3MutualBuyListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualBuyListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����M�����t�ꗗ�Ɖ�������s���܂����B", 
            l_response.errorInfo,
            l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;   

    }
}
@
