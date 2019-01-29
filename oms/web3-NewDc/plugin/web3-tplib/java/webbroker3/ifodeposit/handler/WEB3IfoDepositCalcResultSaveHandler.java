head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultSaveHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋����v�Z���ʕۑ��n���h���N���X(WEB3IfoDepositCalcResultSaveHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/22 ��(FLJ) �V�K�쐬
 */
package webbroker3.ifodeposit.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveResponse;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultSaveService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�؋����v�Z���ʕۑ��n���h��)<BR>
 * �؋����v�Z���ʕۑ��n���h���B<BR>
 * 
 * @@author Sun (FLJ)
 */
public class WEB3IfoDepositCalcResultSaveHandler implements MessageHandler
{

    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoDepositCalcResultSaveHandler.class);

    /**
     * @@roseuid 418617C201B4
     */
    public WEB3IfoDepositCalcResultSaveHandler()
    {

    }

    /**
     * (handle�؋����v�Z���ʕۑ�)<BR>
     * �؋����v�Z���ʕۑ��������s���B<BR>
     * <BR>
     * �؋����v�Z���ʕۑ��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request
     * @@return webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveResponse
     * @@roseuid 414528E10092
     */
    public WEB3IfoDepositCalcResultSaveResponse ifoDepositCalcResultSaveRequest(WEB3IfoDepositCalcResultSaveRequest l_request)
    {
        final String STR_METHOD_NAME = "ifoDepositCalcResultSaveRequest";
        log.entering(STR_METHOD_NAME);

        WEB3IfoDepositCalcResultSaveService l_service = null;
        WEB3IfoDepositCalcResultSaveResponse l_response = null;

        try
        {
            l_service =
                (WEB3IfoDepositCalcResultSaveService) Services.getService(
                    WEB3IfoDepositCalcResultSaveService.class);
        } 
        catch (Exception ex)
        {
            l_response =
                (WEB3IfoDepositCalcResultSaveResponse) l_request
                    .createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�؋����v�Z���ʕۑ��T�[�r�X���擾�ł��܂���ł����B",
                l_response.errorInfo,
                ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3IfoDepositCalcResultSaveResponse) l_service.execute(
                    l_request);
        } 
        catch (WEB3BaseException ex)
        {
            l_response =
                (WEB3IfoDepositCalcResultSaveResponse) l_request
                    .createResponse();
            l_response.errorInfo = ex.getErrorInfo();
            log.error(l_request, "�؋����v�Z���ʕۑ��Ɏ��s���܂����B", l_response.errorInfo, ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch(WEB3BaseRuntimeException rx)
        {
            l_response =
                (WEB3IfoDepositCalcResultSaveResponse) l_request
                    .createResponse();
            l_response.errorInfo = rx.getErrorInfo();
            log.error(l_request, "�؋����v�Z���ʕۑ��Ɏ��s���܂����B", l_response.errorInfo, rx);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch(Exception e)
        {
            l_response =
                (WEB3IfoDepositCalcResultSaveResponse) l_request
                    .createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�؋����v�Z���ʕۑ��Ɏ��s���܂����B", l_response.errorInfo, e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
