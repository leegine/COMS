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
filename	WEB3MutualSwtProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�抷������ꗗ�Ɖ�n���h��(WEB3MutualSwtProductListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 ��O�� (���u) �V�K�쐬                   
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualSwTargetListRequest;
import webbroker3.mf.message.WEB3MutualSwTargetListResponse;
import webbroker3.mf.service.delegate.WEB3MutualSwtProductListService;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M�抷������ꗗ�Ɖ�n���h��
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualSwtProductListHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwtProductListHandler.class);
 
    /**
     * (���M�抷������ꗗ���N�G�X�g)<BR>
     * ���M�抷������ꗗ�Ɖ�������{����B <BR>
     * <BR>
     * ���M�抷������ꗗ�Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MutualSwTargetListResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2EDB5002C
     */
    public WEB3MutualSwTargetListResponse swtListRequest(WEB3MutualSwTargetListRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "swtListRequest(WEB3MutualSwTargetListRequest l_request)";
        log.entering(STR_METHOD_NAME);        
        
        //���M�抷������ꗗ�Ɖ�T�[�r�X���擾��
        WEB3MutualSwtProductListService l_service = null;
        WEB3MutualSwTargetListResponse l_response = null;
        try
        {
            l_service =
                (WEB3MutualSwtProductListService) 
                Services.getService(WEB3MutualSwtProductListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSwTargetListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�抷������ꗗ�Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;   
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3MutualSwTargetListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MutualSwTargetListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���M�抷������ꗗ�Ɖ�������s���܂����B",
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;   
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
