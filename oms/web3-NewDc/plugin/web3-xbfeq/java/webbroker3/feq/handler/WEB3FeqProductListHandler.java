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
filename	WEB3FeqProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������ꗗ�n���h��(WEB3FeqProductListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 ���� (���u) �V�K�쐬   
                 : 2005/08/03 �A�C��(���u) ���r���[       
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqProductListRequest;
import webbroker3.feq.message.WEB3FeqProductListResponse;
import webbroker3.feq.service.delegate.WEB3FeqProductListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�����������ꗗ�n���h��)<BR>
 * �O�����������ꗗ�n���h��
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqProductListHandler implements MessageHandler 
{
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqProductListHandler.class);
    
    /**
     * @@roseuid 42D0DA1A01D4
     */
    public WEB3FeqProductListHandler() 
    {
     
    }
        
    /**
     * (get�������ꗗ)<BR>
     * �O�����������ꗗ���������{����B<BR>
     * <BR>
     * �O�����������ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�����������ꗗ���N�G�X�g
     * @@return webbroker3.feq.message.WEB3FeqProductListResponse
     * @@roseuid 4297247B0157
     */
    public WEB3FeqProductListResponse getProductInformationList(WEB3FeqProductListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getProductInformationList(WEB3FeqProductListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�����������ꗗ�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqProductListService l_service = null;
         
        //�O�����������m�F���X�|���X
        WEB3FeqProductListResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqProductListService) Services.getService(
                    WEB3FeqProductListService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqProductListResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�����������ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqProductListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqProductListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O�����������ꗗ�����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
