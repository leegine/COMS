head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҉��菈�������N���n���h��(WEB3AdminDirSecAPMngForcedStartHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/21 �k�v�u(���u) �V�K�쐬���f�� 132
*/

package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAPMngForcedStartService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҉��菈�������N���n���h��)<BR>
 * �Ǘ��҉��菈�������N���n���h���N���X�B<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAPMngForcedStartHandler.class);

    /**
     * @@roseuid 488439E900E2
     */
    public WEB3AdminDirSecAPMngForcedStartHandler() 
    {

    }

    /**
     * (get���菈���ꗗ)<BR>
     * �Ǘ��҉��菈���ꗗ��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҉��菈�������N���T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���菈���ꗗ���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAPMngListResponse
     * @@roseuid 487599280216
     */
    public WEB3AdminDirSecAPMngListResponse getAPMngList(WEB3AdminDirSecAPMngListRequest l_request) 
    {
        final String STR_METHOD_NAME = "getAPMngList(WEB3AdminDirSecAPMngListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAPMngListResponse l_response = null;
        WEB3AdminDirSecAPMngForcedStartService l_service = null;

        //�Ǘ��҉��菈�������N���T�[�r�XImpl���擾
        try
        {
            l_service = (WEB3AdminDirSecAPMngForcedStartService)Services.getService(
                WEB3AdminDirSecAPMngForcedStartService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҉��菈�������N���T�[�r�XImpl���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAPMngListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҉��菈���ꗗ��ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҉��菈���ꗗ��ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���菈�������N������)<BR>
     * ���菈�������N�����͉�ʕ\�����s���B <BR>
     * <BR>
     * �Ǘ��҉��菈�������N���T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���菈�������N�����̓��N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartInpResponse
     * @@roseuid 487C46D90136
     */
    public WEB3AdminDirSecAPMngForcedStartInpResponse getAPMngForcedStartInp(
        WEB3AdminDirSecAPMngForcedStartInpRequest l_request) 
    {
        final String STR_METHOD_NAME = "getAPMngForcedStartInp(WEB3AdminDirSecAPMngForcedStartInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAPMngForcedStartInpResponse l_response = null;
        WEB3AdminDirSecAPMngForcedStartService l_service = null;

        //�Ǘ��҉��菈�������N���T�[�r�XImpl���擾
        try
        {
            l_service = (WEB3AdminDirSecAPMngForcedStartService)Services.getService(
                WEB3AdminDirSecAPMngForcedStartService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҉��菈�������N���T�[�r�XImpl���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAPMngForcedStartInpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���菈�������N�����͉�ʕ\���Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���菈�������N�����͉�ʕ\���Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate���菈�������N���m�F)<BR>
     * ���菈�������N���m�F��ʕ\�����s���B <BR>
     * <BR>
     * �Ǘ��҉��菈�������N���T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���菈�������N���m�F���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartCnfResponse
     * @@roseuid 487599280218
     */
    public WEB3AdminDirSecAPMngForcedStartCnfResponse validateAPMngForcedStartCnf(
        WEB3AdminDirSecAPMngForcedStartCnfRequest l_request) 
    {
        final String STR_METHOD_NAME = "validateAPMngForcedStartCnf(WEB3AdminDirSecAPMngForcedStartCnfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAPMngForcedStartCnfResponse l_response = null;
        WEB3AdminDirSecAPMngForcedStartService l_service = null;

        //�Ǘ��҉��菈�������N���T�[�r�XImpl���擾
        try
        {
            l_service = (WEB3AdminDirSecAPMngForcedStartService)Services.getService(
                WEB3AdminDirSecAPMngForcedStartService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCnfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҉��菈�������N���T�[�r�XImpl���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAPMngForcedStartCnfResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���菈�������N���m�F��ʕ\���Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���菈�������N���m�F��ʕ\���Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit���菈�������N������)<BR>
     * �Ǘ��҉��菈�������N��������ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҉��菈�������N���T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���菈�������N���������N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartCmpResponse
     * @@roseuid 48759928021A
     */
    public WEB3AdminDirSecAPMngForcedStartCmpResponse submitAPMngForcedStartCmp(
        WEB3AdminDirSecAPMngForcedStartCmpRequest l_request) 
    {
        final String STR_METHOD_NAME = "submitAPMngForcedStartCmp(WEB3AdminDirSecAPMngForcedStartCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAPMngForcedStartCmpResponse l_response = null;
        WEB3AdminDirSecAPMngForcedStartService l_service = null;

        //�Ǘ��҉��菈�������N���T�[�r�XImpl���擾
        try
        {
            l_service = (WEB3AdminDirSecAPMngForcedStartService)Services.getService(
                WEB3AdminDirSecAPMngForcedStartService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҉��菈�������N���T�[�r�XImpl���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminDirSecAPMngForcedStartCmpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҉��菈�������N��������ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminDirSecAPMngForcedStartCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҉��菈�������N��������ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
