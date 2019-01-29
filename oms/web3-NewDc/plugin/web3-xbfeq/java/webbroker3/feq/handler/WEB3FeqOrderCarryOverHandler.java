head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCarryOverHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������J�z�n���h��(WEB3FeqOrderCarryOverHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬    
				 : 2005/08/01 �s�p(���u) ���r���[          
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqOrderTransferRequest;
import webbroker3.feq.message.WEB3FeqOrderTransferResponse;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�����������J�z�n���h��)<BR>
 * �O�����������J�z�n���h���N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqOrderCarryOverHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderCarryOverHandler.class);
    
    /**
     * @@roseuid 42D0DA1901A5
     */
    public WEB3FeqOrderCarryOverHandler() 
    {
     
    }
    
    /**
     * (submit�����J�z)<BR>
     * �O�����������J�z�������s���B<BR>
     * <BR>
     * �O�����������J�z�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�����������J�z���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FeqOrderTransferResponse
     * @@roseuid 42B8A25003B4
     */
    public WEB3FeqOrderTransferResponse submitOrderCarryOver(
        WEB3FeqOrderTransferRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "submitOrderCarryOver(WEB3FeqOrderTransferRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�����������J�z�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqOrderCarryOverService l_service = null;
         
        //�O�����������J�z���X�|���X
        WEB3FeqOrderTransferResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqOrderCarryOverService) Services.getService(
                    WEB3FeqOrderCarryOverService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqOrderTransferResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�����������J�z�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqOrderTransferResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqOrderTransferResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O�����������J�z�����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
