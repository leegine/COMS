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
filename	WEB3AdminMutualTPACancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��җ]�͒�������n���h���N���X(WEB3AdminMutualTPACancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 ����  (���u) �V�K�쐬
*/

package webbroker3.mf.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPACancelService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * ���M�Ǘ��җ]�͒�������n���h���N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminMutualTPACancelHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPACancelHandler.class); 
    
    /**
     * (�]�͒�������ꗗ)<BR>
     * �����M���]�͒�������ꗗ�������s���B<BR>
     * <BR>
     * ���M�Ǘ��җ]�͒�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���M�Ǘ��җ]�͒�������ꗗ���N�G�X�g<BR>
     * @@return WEB3AdminMutualTPACancelListResponse
     * @@roseuid 40567E0801DE
     */
    public WEB3AdminMutualTPACancelListResponse tpACancelList(
        WEB3AdminMutualTPACancelListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "tpACancelList(WEB3AdminMutualTPACancelListRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //���M�Ǘ��җ]�͒�������T�[�r�X
        WEB3AdminMutualTPACancelService l_service = null;          
        
        //���M�Ǘ��җ]�͒�������ꗗ���X�|���X
        WEB3AdminMutualTPACancelListResponse l_response = null;     
        try
        {
            l_service = 
                (WEB3AdminMutualTPACancelService)Services.getService(
                    WEB3AdminMutualTPACancelService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
             //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminMutualTPACancelListResponse)l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�Ǘ��җ]�͒�������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualTPACancelListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualTPACancelListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�����M���]�͒�������ꗗ�����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�]�͒����������)<BR>
     * �����M���]�͒�����������������s���B<BR>
     * <BR>
     * ���M�Ǘ��җ]�͒�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���M�Ǘ��җ]�͒�������������N�G�X�g<BR>
     * 
     * @@return WEB3AdminMutualTPACancelCompleteResponse
     * @@roseuid 40567E0801DE
     */
    public WEB3AdminMutualTPACancelCompleteResponse tpACancelComplete(
        WEB3AdminMutualTPACancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "tpACancelComplete(WEB3AdminMutualTPACancelCompleteRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //���M�Ǘ��җ]�͒�������T�[�r�X
        WEB3AdminMutualTPACancelService l_service = null;          
        
        //�����M���Ǘ��җ]�͒�������������X�|���X�N���X
        WEB3AdminMutualTPACancelCompleteResponse l_response = null;     
        try
        {
            l_service = 
                (WEB3AdminMutualTPACancelService)Services.getService(
                    WEB3AdminMutualTPACancelService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
             //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminMutualTPACancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�Ǘ��җ]�͒�������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualTPACancelCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualTPACancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�����M���]�͒���������������Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
