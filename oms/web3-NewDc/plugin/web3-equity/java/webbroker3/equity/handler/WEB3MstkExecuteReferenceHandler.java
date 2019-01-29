head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����������Ɖ�n���h��(WEB3MstkExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12  �d��(���u) �V�K�쐬
                   2004/12/29 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkExecuteReferenceRequest;
import webbroker3.equity.message.WEB3MstkExecuteReferenceResponse;
import webbroker3.equity.service.delegate.WEB3MstkExecuteReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j�����������Ɖ�n���h���j�B<BR>
 * <BR>
 * �����~�j�����������Ɖ�n���h���N���X
 * @@author �d��
 * @@version 1.0
 */
public class WEB3MstkExecuteReferenceHandler implements MessageHandler 
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkExecuteReferenceHandler.class);
    
    /**
     * 
     */
    public WEB3MstkExecuteReferenceHandler() 
    {
     
    }
    
    /**
     * �isearch�������Ɖ�j�B<BR>
     * <BR>
     * �����~�j�����������Ɖ�������{����B<BR>
     * <BR>
     * �����~�j�����������Ɖ�T�[�r�X���擾���Aexecute()<BR>
     * ���\�b�h���R�[������B
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �@@�@@�@@�����~�j�����������Ɖ�N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkExecuteReferenceResponse
     */
    public WEB3MstkExecuteReferenceResponse handleSearchOrderExecuteReference(WEB3MstkExecuteReferenceRequest l_request) 
    {
        //�����~�������Ɖ�T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME =
            "searchOrderExecuteReference(WEB3MarginExecuteReferenceRequest l_request)";

        log.entering(STR_METHOD_NAME);

        WEB3MstkExecuteReferenceResponse l_response = null;
        WEB3MstkExecuteReferenceService l_service = null;

        //�����~�j�����������Ɖ�T�[�r�X���擾���Aexecute()
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3MstkExecuteReferenceService)Services.getService(
                    WEB3MstkExecuteReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MstkExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�������Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MstkExecuteReferenceResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MstkExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����~�������Ɖ�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response =
				(WEB3MstkExecuteReferenceResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "�����~�������Ɖ�Ɏ��s���܂����B", l_ex);
			return l_response;
		}

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
     
    }
}
@
