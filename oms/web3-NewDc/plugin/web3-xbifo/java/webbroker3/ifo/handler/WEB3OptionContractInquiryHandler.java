head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionContractInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP���ʏƉ�n���h��(WEB3OptionContractInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/13 ���� (���u) �V�K�쐬
              001: 2004/07/26 ���Ō� (���u) �Ή��o�b�O IFO_UT-000015 log�̒�`���C���AgetContract()���C��
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.ifo.service.delegate.WEB3OptionContractInquiryService;
import webbroker3.ifo.message.WEB3OptionsContractReferenceResponse;
import webbroker3.ifo.message.WEB3OptionsContractReferenceRequest;

/**
 * (OP���ʏƉ�n���h��)<BR>
 * �����w���I�v�V�������ʏƉ�n���h���N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionContractInquiryHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionContractInquiryHandler.class);    

    /**
     * @@roseuid 40C0754D037A
     */
    public WEB3OptionContractInquiryHandler()
    {

    }

    /**
     * (get����)<BR>
     * �����w���I�v�V�������ʏƉ�����s���B<BR>
     * <BR>
     * �����w���I�v�V�������ʏƉ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V�������ʏƉ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsContractReferenceResponse
     * @@roseuid 4057F9AE02C1
     */
    public WEB3OptionsContractReferenceResponse getContract(WEB3OptionsContractReferenceRequest l_request)
    {       
        final String STR_METHOD_NAME =
            getClass().getName() + ".getContract(WEB3OptionsContractReferenceRequest l_request)";           

        
        log.debug(STR_METHOD_NAME + " : ENTER");

        WEB3OptionsContractReferenceResponse l_response = null;
        WEB3OptionContractInquiryService l_service = null;

        try
        {
            //�����w���I�v�V�������ʏƉ�T�[�r�X�I�u�W�F�N�g���擾����
            log.debug("�����w���I�v�V�������ʏƉ�T�[�r�X�I�u�W�F�N�g: ENTER");

            l_service =
                (WEB3OptionContractInquiryService)Services.getService(
                    WEB3OptionContractInquiryService.class);
            log.debug("�����w���I�v�V�������ʏƉ�T�[�r�X�I�u�W�F�N�g: END");
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3OptionsContractReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���I�v�V�������ʏƉ�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }

        try
        {
            //�����w���I�v�V�������ʏƉ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
            //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
            log.debug("�����w���I�v�V�������ʏƉ�T�[�r�X�I�u�W�F�N�g.execute�i): ENTER");
            l_response = (WEB3OptionsContractReferenceResponse)l_service.execute(l_request);
            log.debug("�����w���I�v�V�������ʏƉ�T�[�r�X�I�u�W�F�N�g.execute�i): END");
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsContractReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V�������ʏƉ�Ɏ��s���܂����B", l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }

        log.debug(STR_METHOD_NAME + " : END");

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
