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
filename	WEB3BondAutoExecuteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������n���h��(WEB3BondAutoExecuteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/28 ꎉ� (���u) �V�K�쐬
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3BondAutoExecRequest;
import webbroker3.bd.message.WEB3BondAutoExecResponse;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���������n���h��)<BR>
 * ���������n���h���N���X<BR>
 * 
 * @@author ꎉ�(���u)
 * @@version 1.0 
 */
public class WEB3BondAutoExecuteHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondAutoExecuteHandler.class);
    
    public WEB3BondAutoExecuteHandler() 
    {
     
    }
    
    /**
     * (complete�������)<BR>
     * complete������� <BR>
     * <BR>
     * �@@���������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3BondAutoExecResponse
     */
    public WEB3BondAutoExecResponse completeAutoExecute(WEB3BondAutoExecRequest l_request)
    {
        final String STR_METHOD_NAME = " completeAutoExecute(WEB3BondAutoExecRequest)";
        log.entering(STR_METHOD_NAME);
        
        //���������T�[�r�X���擾
        WEB3BondAutoExecuteService l_service = null;
        WEB3BondAutoExecResponse l_response = null;
        try
        {
            l_service =(WEB3BondAutoExecuteService) 
                Services.getService(WEB3BondAutoExecuteService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3BondAutoExecResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���������T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3BondAutoExecResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3BondAutoExecResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "complete������肪���s���܂����B", 
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
