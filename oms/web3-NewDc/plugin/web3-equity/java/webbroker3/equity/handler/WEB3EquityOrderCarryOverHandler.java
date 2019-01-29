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
filename	WEB3EquityOrderCarryOverHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �����J�z�n���h��(WEB3EquityOrderCarryOverHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 羐� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityOrderCarryOverRequest;
import webbroker3.equity.message.WEB3EquityOrderCarryOverResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����J�z�n���h���j�B
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderCarryOverHandler.class);

    /**
     * @@roseuid 40B2A1700289
     */
    public WEB3EquityOrderCarryOverHandler()
    {

    }

    /**
     * (complete�J�z)<BR>
     * �����J�z�������s���B<BR>
     * <BR>
     * �����J�z�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_requestData - �����J�z���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.equity.message.WEB3EquityOrderCarryOverResponse
     * @@roseuid 4057AA490015
     */
    public WEB3EquityOrderCarryOverResponse completeCarryOver(WEB3EquityOrderCarryOverRequest l_requestData)
    {
        final String STR_METHOD_NAME =
            "completeCarryOver(WEB3EquityOrderCarryOverRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityOrderCarryOverResponse l_response = 
            (WEB3EquityOrderCarryOverResponse) 
            
                l_requestData.createResponse();
        WEB3EquityOrderCarryOverService l_service = null;

        try
        {
            //�����J�z�T�[�r�X���擾���Aexecute()���\�b�h���R�[������
            l_service =
                (WEB3EquityOrderCarryOverService)Services.getService(
                    WEB3EquityOrderCarryOverService.class);
        }
        catch (Exception e)
        {
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorInfo.error_debug_info = "�����J�z�T�[�r�X�̎擾�Ɏ��s���܂����B";
            log.error(STR_METHOD_NAME + "___�����J�z�T�[�r�X�̎擾�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        try
        {
            l_response =
                (WEB3EquityOrderCarryOverResponse)l_service.execute(
                    l_requestData);
        }
        catch (WEB3BaseException e)
        {
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_requestData, "�����J�z�Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_requestData, "�����J�z�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
