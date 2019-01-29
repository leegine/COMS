head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverSkipHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������J�z�X�L�b�v�����ʒm�n���h��(WEB3EquityOrderCarryOverSkipHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/26 羐� (���u) �V�K�쐬
                   2004/12/13 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityCarryOverSkipRequest;
import webbroker3.equity.message.WEB3EquityCarryOverSkipResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������J�z�X�L�b�v�����ʒm�n���h���j�B
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverSkipHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderCarryOverSkipHandler.class);

    /**
     * @@roseuid 40B2E5C90102
     */
    public WEB3EquityOrderCarryOverSkipHandler()
    {

    }

    /**
     * (complete�ʒm)<BR>
     * �����J�z�X�L�b�v�����ʒm�������s���B<BR>
     * <BR>
     * �����J�z�X�L�b�v�����ʒm�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�X�L�b�v�����ʒm�j�J�z�X�L�b�v�v�Q�ƁB<BR>
     * @@param l_requestData - ���N�G�X�g�f�[�^<BR>
     * �����J�z�X�L�b�v�����ʒm���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3EquityCarryOverSkipResponse
     * @@roseuid 4056CFB4002D
     */
    public WEB3EquityCarryOverSkipResponse completeNotify(WEB3EquityCarryOverSkipRequest l_requestData)
    {
        final String STR_METHOD_NAME =
            "completeNotify(WEB3EquityCarryOverSkipRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityCarryOverSkipResponse l_response = 
            (WEB3EquityCarryOverSkipResponse)l_requestData.createResponse();
        WEB3EquityOrderCarryOverSkipService l_service = null;
        try
        {
            //�����J�z�X�L�b�v�����ʒm�T�[�r�X���擾���Aexecute()���\�b�h���R�[������
            l_service =
                (WEB3EquityOrderCarryOverSkipService) Services.getService(
                    WEB3EquityOrderCarryOverSkipService.class);
        }
        catch (Exception e)
        {        
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorInfo.error_debug_info = "�����J�z�X�L�b�v�����ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B";
            log.error(STR_METHOD_NAME + "___�����J�z�X�L�b�v�����ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        try
        {
            l_response =
                (WEB3EquityCarryOverSkipResponse)l_service.execute(
                    l_requestData);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_requestData, "�����J�z�X�L�b�v�����ʒm�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_requestData, "�����J�z�X�L�b�v�����ʒm�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
