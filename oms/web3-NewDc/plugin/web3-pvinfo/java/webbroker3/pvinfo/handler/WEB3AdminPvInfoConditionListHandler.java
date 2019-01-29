head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҕ\���ݒ�ꗗ�n���h��(WEB3AdminPvInfoConditionListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/27 ������(���u) �쐬
Revesion History : 2004/11/2  鰊](���u) �C��
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҕ\���ݒ�ꗗ�n���h��)<BR>
 * �Ǘ��ҕ\���ݒ�ꗗ�n���h���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionListHandler implements MessageHandler
{
    /** 
     *���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionListHandler.class);
    /**
     * (get�\���ݒ�ꗗ���)<BR>
     * �\���ݒ�ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҕ\���ݒ�ꗗ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListResponse
     * @@roseuid 415BD299037F
     */
    public WEB3AdminPvInfoConditionListResponse getConditionListScreen(WEB3AdminPvInfoConditionListRequest l_request)
    {
        final String STR_METHOD_NAME = " getConditionListScreen(WEB3AdminPvInfoConditionListRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminPvInfoConditionListResponse l_response = null;
        WEB3AdminPvInfoConditionListService l_service = null;
        
        //�Ǘ��ҕ\���ݒ�ꗗ�T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoConditionListService)Services.getService(WEB3AdminPvInfoConditionListService.class);             
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��ҕ\���ݒ�ꗗ�T�[�r�X���擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoConditionListResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }

    /**
     * (update���ݏ�)<BR>
     * ���ݏ󋵍X�V�������s���B<BR>
     * <BR>
     * �Ǘ��ҕ\���ݒ�ꗗ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���ݏ󋵍X�V���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateResponse
     * @@roseuid 415BED7B01F5
     */
    public WEB3AdminPvInfoConditionUpdateResponse updateCondition(WEB3AdminPvInfoConditionUpdateRequest l_request)
    {
        final String STR_METHOD_NAME = " updateCondition(WEB3AdminPvInfoConditionUpdateRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionUpdateResponse l_response = null;
        WEB3AdminPvInfoConditionListService l_service = null;
        
        //�Ǘ��ҕ\���ݒ�ꗗ�T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoConditionListService)Services.getService(WEB3AdminPvInfoConditionListService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionUpdateResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�Ǘ��ҕ\���ݒ�ꗗ�T�[�r�X���擾�Ɏ��s���܂����B", l_response.errorInfo,l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoConditionUpdateResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionUpdateResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
