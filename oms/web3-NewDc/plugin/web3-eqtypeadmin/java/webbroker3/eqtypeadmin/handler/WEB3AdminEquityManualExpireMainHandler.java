head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualExpireMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����蓮�������C���n���h��(WEB3AdminEquityManualExpireMainHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/31 ������ (���u) �V�K�쐬
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�����蓮�������C���n���h��)<BR>
 * �Ǘ��ҁE�����蓮�������C���n���h���N���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminEquityManualExpireMainHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AdminEquityManualExpireMainHandler.class);
     
    /**
     * @@roseuid 447AC0CF02EE
     */
    public WEB3AdminEquityManualExpireMainHandler() 
    {
     
    }
    
    /**
     * (�蓮�������N�G�X�g)<BR>
     * �i�񓯊��j�蓮�����������N������B<BR>
     * <BR>
     * �Ǘ��ҁE�����蓮�������C���T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����蓮�������C�����N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainResponse
     * @@roseuid 446934A70279
     */
    public WEB3AdminEquityManualLapseMainResponse manualExpireRequest(WEB3AdminEquityManualLapseMainRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "manualExpireRequest(WEB3AdminEquityManualLapseMainRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityManualLapseMainResponse l_response = null;
        WEB3AdminEquityManualExpireMainService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityManualExpireMainService) Services.getService(
                        WEB3AdminEquityManualExpireMainService.class);
        } 
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminEquityManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminEquityManualExpireMainService",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminEquityManualLapseMainResponse) l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3AdminEquityManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "Failed to access manualExpireRequest", l_exp);
        }
        catch (WEB3BaseRuntimeException l_exp)
        {
            l_response =
                (WEB3AdminEquityManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "Failed to access manualExpireRequest", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
