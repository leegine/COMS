head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailDistributionChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���ē����[���z�M�ݒ�ύX�n���h��(WEB3AccInfoMailDistributionChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoMailDistributionChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailDistributionChangeCompleteResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMailDistributionChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l���ē����[���z�M�ݒ�ύX�n���h��)<BR>
 * ���q�l���ē����[���z�M�ݒ�ύX�n���h��<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AccInfoMailDistributionChangeHandler implements MessageHandler 
{                  
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMailDistributionChangeHandler.class);
    /**
     * @@roseuid 418F3A0B02BF
     */
    public WEB3AccInfoMailDistributionChangeHandler() 
    {
     
    }
    
    /**
     * (���q�l���ē����[���z�M�ݒ�ύX����)<BR>
     * �ē����[���z�M�ݒ��ύX����B<BR>
     * <BR>
     * ���q�l���ē��z�M�ݒ�ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l���ē����[���z�M�ݒ�ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailDistributionChangeCompleteResponse
     * @@roseuid 413C1EDE00B9
     */
    public WEB3AccInfoMailDistributionChangeCompleteResponse accountInfoMailDistributionChangeComplete(WEB3AccInfoMailDistributionChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountInfoMailDistributionChangeComplete(WEB3AccInfoMailDistributionChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMailDistributionChangeCompleteResponse l_response = null;
        WEB3AccInfoMailDistributionChangeService l_service = null;
        
        //���q�l���ē��z�M�ݒ�ύX�T�[�r�X���擾
        try
        {
            l_service = (WEB3AccInfoMailDistributionChangeService)Services.getService(WEB3AccInfoMailDistributionChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoMailDistributionChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l���ē��z�M�ݒ�ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //���q�l���ē��z�M�ݒ�ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AccInfoMailDistributionChangeCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoMailDistributionChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l��񃁁[���A�h���X�ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
