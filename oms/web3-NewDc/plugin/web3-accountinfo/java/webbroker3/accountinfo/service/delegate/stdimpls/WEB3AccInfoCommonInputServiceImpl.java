head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommonInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񋤒ʓ��̓T�[�r�XImpl(WEB3AccInfoCommonInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoCommonInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoCommonInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommonInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l��񋤒ʓ��̓T�[�r�XImpl)<BR>
 * ���q�l��񋤒ʓ��̓T�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoCommonInputServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoCommonInputService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommonInputServiceImpl.class);
    
    /**
     * @@roseuid 418F39FD01E4
     */
    public WEB3AccInfoCommonInputServiceImpl() 
    {
     
    }
    
    /**
     * ���͉�ʕ\�����ʏ��������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A<BR>�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���q�l��񋤒ʓ��̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41456FFE0191
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AccInfoCommonInputRequest)
        {
            l_response = getInputScreen((WEB3AccInfoCommonInputRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request
                );
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʕ\�����ʏ��������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i���ʁjget���͉�ʁv�Q�ƁB<BR>
     * 
     * @@param l_request - ���q�l��񋤒ʓ��̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommonInputResponse
     * @@roseuid 41456FC300F5
     */
    protected WEB3AccInfoCommonInputResponse getInputScreen(WEB3AccInfoCommonInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AccInfoCommonInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //��t���ԃ`�F�b�N���s���B  
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        WEB3AccInfoCommonInputResponse l_response = (WEB3AccInfoCommonInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
