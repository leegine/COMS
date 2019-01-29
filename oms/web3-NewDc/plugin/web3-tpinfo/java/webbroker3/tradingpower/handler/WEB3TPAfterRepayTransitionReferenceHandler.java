head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAfterRepayTransitionReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �ԍό�]�͏���ʕ\���n���h��(WEB3TPAfterRepayTransitionReferenceHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/04/08 nakazato(ACT) �V�K�쐬
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.define.WEB3MarginBeforeRequestDivDef;
import webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceRequest;
import webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAfterRepayTransitionReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ԍό�]�͏���ʕ\���n���h��)
 */
public class WEB3TPAfterRepayTransitionReferenceHandler
        implements MessageHandler
{

    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3TPAfterRepayTransitionReferenceHandler.class);

    /**
     * @@roseuid 4255DAAC03D9
     */
    public WEB3TPAfterRepayTransitionReferenceHandler()
    {

    }

    /**
     * (get�ԍό�]�͏����) �M�p����ԍϒ�����̗]�͐��ډ�ʂ�\������B
     * 
     * �ԍό�]�͏���ʕ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * 
     * @@param (�ԍό�]��)�M�p����ԍϒ����m�F���N�G�X�g -
     *            ((�ԍό�]��)�M�p����ԍϒ����m�F���N�G�X�g)
     * 
     * @@return webbroker3.tradingpower.message.WEB3TPAfterRepayTransitionReferenceResponse
     * @@roseuid 4248A63D030F
     */
    public WEB3TPAfterRepayTransitionReferenceResponse getAfterRepayTransitionReference(
            WEB3TPAfterRepayTransitionReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = "getAfterRepayTransitionReference(WEB3TPAfterRepayTransitionReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //�ԍό�]�͏���ʕ\���T�[�r�X
        WEB3TPAfterRepayTransitionReferenceService l_service = null;
        //�ԍό�]�͏���ʕ\�����X�|���X
        WEB3TPAfterRepayTransitionReferenceResponse l_response = null;

        //���N�G�X�g.�v�����敪��1:�ԍό�]�͕\�����Z�b�g
        l_request.requestFromType = WEB3MarginBeforeRequestDivDef.AFTER_REPAY;

        try
        {
            //�ԍό�]�͏���ʕ\���T�[�r�X���擾
            l_service = (WEB3TPAfterRepayTransitionReferenceService) Services
                .getService(WEB3TPAfterRepayTransitionReferenceService.class);

            //execute()���\�b�h���R�[��
            l_response = (WEB3TPAfterRepayTransitionReferenceResponse) l_service
                .execute(l_request);

            //�ԍό�]�͏���ʕ\�����X�|���X��ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch(WEB3BaseException be)
        {
            l_response = (WEB3TPAfterRepayTransitionReferenceResponse) l_request
                .createResponse();
            l_response.errorInfo = be.getErrorInfo();
            log.error(l_request, "�ԍό�]�͏���ʕ\�������Ɏ��s���܂����B", be);
            return l_response;
        }
        catch(WEB3BaseRuntimeException bre)
        {
            l_response = (WEB3TPAfterRepayTransitionReferenceResponse) l_request
                .createResponse();
            l_response.errorInfo = bre.getErrorInfo();
            log.error(l_request, "�ԍό�]�͏���ʕ\�������Ɏ��s���܂����B", bre);
            return l_response;
        }
        catch(Exception e)
        {
            l_response = (WEB3TPAfterRepayTransitionReferenceResponse) l_request
                .createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                      l_request, "�ԍό�]�͏���ʕ\�������Ɏ��s���܂����B", l_response.errorInfo,
                      e);
            return l_response;
        }
    }
}@
