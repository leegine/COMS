head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionDetailHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҕ\���ݒ�ڍ׃n���h��(WEB3AdminPvInfoConditionDetailHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/26 ���O�B(���u) �쐬
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDetailService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҕ\���ݒ�ڍ׃n���h��)<BR>
 * �Ǘ��ҕ\���ݒ�ڍ׃n���h���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionDetailHandler implements MessageHandler
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionDetailHandler.class);


    /**
     * (get�\���ݒ�ڍ׉��)<BR>
     * �\���ݒ�ڍ׉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҕ\���ݒ�ڍ׃T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�ڍ׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailResponse
     * @@roseuid 415CB8DD00B4
     */
    public WEB3AdminPvInfoConditionDetailResponse getConditionDetailScreen(WEB3AdminPvInfoConditionDetailRequest l_request)
    {
        final String STR_METHOD_NAME = " getConditionDetailScreen(WEB3AdminPvInfoConditionDetailRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionDetailResponse l_response = null;
        WEB3AdminPvInfoConditionDetailService l_service = null;
        
        //�Ǘ��ҕ\���ݒ�ڍ׃T�[�r�X���擾��
        try
        {
            l_service = (WEB3AdminPvInfoConditionDetailService)Services.getService(
                WEB3AdminPvInfoConditionDetailService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDetailResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҕ\���ݒ�ڍׂ̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //�Ǘ��ҕ\���ݒ�ڍ׃T�[�r�X.execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3AdminPvInfoConditionDetailResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�Ǘ��ҕ\���ݒ�ڍ׃T�[�r�X.execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
        
    }
}
@
