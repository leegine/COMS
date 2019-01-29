head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoExecMailDistributionChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l�����^����胁�[���z�M�ݒ�ύX�n���h��(WEB3AccInfoExecMailDistributionChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoExecMailDistributionChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoExecMailDistributionChangeCompleteResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoExecMailDistributionChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l�����^����胁�[���z�M�ݒ�ύX�n���h��)<BR>
 * ���q�l�����^����胁�[���z�M�ݒ�ύX�n���h��<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AccInfoExecMailDistributionChangeHandler implements MessageHandler 
{                  
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoExecMailDistributionChangeHandler.class);
    /**
     * @@roseuid 418F3A1102CE
     */
    public WEB3AccInfoExecMailDistributionChangeHandler() 
    {
     
    }
    
    /**
     * (���^����胁�[���z�M�ݒ�ύX����)<BR>
     * ���^����胁�[���z�M�ݒ��ύX����B<BR>
     * <BR>
     * ���q�l�����^����胁�[���z�M�ݒ�ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l�����^����胁�[���z�M�ݒ�ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoExecMailDistributionChangeCompleteResponse
     * @@roseuid 413C010A02AD
     */
    public WEB3AccInfoExecMailDistributionChangeCompleteResponse execMailDistributionChangeComplete(WEB3AccInfoExecMailDistributionChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " execMailDistributionChangeComplete(WEB3AccInfoExecMailDistributionChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoExecMailDistributionChangeCompleteResponse l_response = null;
        WEB3AccInfoExecMailDistributionChangeService l_service = null;
        
        //���q�l�����^����胁�[���z�M�ݒ�ύX�T�[�r�X���擾
        try
        {
            l_service = (WEB3AccInfoExecMailDistributionChangeService)Services.getService(WEB3AccInfoExecMailDistributionChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoExecMailDistributionChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l�����^����胁�[���z�M�ݒ�ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //���q�l�����^����胁�[���z�M�ݒ�ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AccInfoExecMailDistributionChangeCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoExecMailDistributionChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l�����^����胁�[���z�M�ݒ�ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
}
@
