head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Җ����ꗗ�n���h��(WEB3AdminBondProductListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q (���u) �V�K�쐬
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputResponse;
import webbroker3.bd.message.WEB3AdminBondProductSearchListRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchListResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondProductListService;

/**
 * (���Ǘ��Җ����ꗗ�n���h��)<BR>
 * ���Ǘ��Җ����ꗗ�n���h���N���X�B<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondProductListHandler implements MessageHandler 
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondProductListHandler.class);
    
    /**
     * (�����ꗗ��ʕ\�����N�G�X�g)<BR>
     * ���Ǘ��Җ����ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * ���Ǘ��Җ����ꗗ�T�[�r�X���擾���Aexecute()���\�b�h<BR>
     * ���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���Ǘ��Җ����ꗗ��ʕ\�����N�G�X�g<BR>
     * @@return WEB3AdminBondProductSearchInputResponse
     * @@roseuid 44B603CE029E
     */
    public WEB3AdminBondProductSearchInputResponse inputProductList(
        WEB3AdminBondProductSearchInputRequest l_request) 
    {
        String STR_METHOD_NAME = 
            "inputProductList(WEB3AdminBondProductSearchInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondProductListService l_service = null;
        WEB3AdminBondProductSearchInputResponse l_response = null;
        
        try
        {
            //���Ǘ��Җ����ꗗ�T�[�r�X���擾��
            l_service = 
                (WEB3AdminBondProductListService)Services.getService(
                    WEB3AdminBondProductListService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminBondProductSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "�Ǘ��ҍ������ꗗ�T�[�r�X���擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminBondProductSearchInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondProductSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���Ǘ��Җ����ꗗ��ʕ\�����������s���܂����B", 
                l_ex);
           
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�����ꗗ�������N�G�X�g)<BR>
     * ���Ǘ��Җ����ꗗ�����������s���B<BR>
     * <BR>
     * ���Ǘ��Җ����ꗗ�T�[�r�X���擾���Aexecute()���\�b�h<BR>
     * ���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���Ǘ��Җ����ꗗ�������N�G�X�g<BR>
     * @@return WEB3AdminBondProductSearchListResponse
     * @@roseuid 44B603E502BE
     */
    public WEB3AdminBondProductSearchListResponse searchProductList(
        WEB3AdminBondProductSearchListRequest l_request) 
    {
        String STR_METHOD_NAME = 
            "searchProductList(WEB3AdminBondProductSearchListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondProductListService l_service = null;
        WEB3AdminBondProductSearchListResponse l_response = null;
        
        try
        {
            //���Ǘ��Ғ������Ɖ�T�[�r�X���擾��
            l_service = 
                (WEB3AdminBondProductListService)Services.getService(
                    WEB3AdminBondProductListService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminBondProductSearchListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "�Ǘ��ҍ������ꗗ�T�[�r�X���擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3AdminBondProductSearchListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondProductSearchListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���Ǘ��Җ����ꗗ�������������s���܂����B", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;     
    }
}
@
