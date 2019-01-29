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
filename	WEB3EquityOffFloorProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����O���������ꗗ�n���h��(WEB3EquityOffFloorProductListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 �X�� (SRA) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityOffFloorProductListRequest;
import webbroker3.equity.message.WEB3EquityOffFloorProductListResponse;
import webbroker3.equity.service.delegate.WEB3EquityOffFloorProductListService;
import webbroker3.util.WEB3LogUtility;


/**
 * �i����O���������ꗗ�n���h���j�B<BR>
 * <BR>
 * ����O���������ꗗ�n���h���N���X
 * @@version 1.0
 */
public class WEB3EquityOffFloorProductListHandler implements MessageHandler
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOffFloorProductListHandler.class);


    /**
     * (�f�t�H���g�̃R���X�g���N�^)�B<BR>
     * <BR>
     * ����O���������ꗗ�n���h���𐶐�����B<BR>
     * 
     */
    public WEB3EquityOffFloorProductListHandler()
    {
    }
    

    /**
     * (get�����ꗗ)�B<BR>
     * <BR>
     * ����O���������ꗗ�\���������s���B <BR>
     * <BR>
     * ����O���������ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * <BR>
     * @@param  l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.equity.message.WEB3EquityOffFloorProductListResponse
     */
    public WEB3EquityOffFloorProductListResponse equityOffFloorProductListRequest(
        WEB3EquityOffFloorProductListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equityOffFloorProductListRequest(WEB3EquityOffFloorProductListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityOffFloorProductListResponse         l_response  = null;
        WEB3EquityOffFloorProductListService    l_service   = null;


        //--------------------
        //����O���������ꗗ�T�[�r�X�̎擾
        //--------------------
        try
        {
            l_service = (WEB3EquityOffFloorProductListService) Services
                .getService(WEB3EquityOffFloorProductListService.class);
        }
        catch (Exception l_exception)
        {
            l_response = (WEB3EquityOffFloorProductListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, "����O���������ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_exception);
                
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //--------------------
        //����O���������ꗗ�T�[�r�X�̎��s
        //--------------------
        try
        {
            l_response = (WEB3EquityOffFloorProductListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(l_request, "����O���������ꗗ�Ɏ��s���܂����B", l_wbe);
            l_response = (WEB3EquityOffFloorProductListResponse) l_request.createResponse();
            l_response.errorInfo = l_wbe.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_request, "����O���������ꗗ�Ɏ��s���܂����B", l_bre);
            l_response = (WEB3EquityOffFloorProductListResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
