head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t�����ꗗ�n���h��(WEB3MutualFixedBuyConditionListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/22 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListResponse;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�莞��z���t�����ꗗ�n���h��)<BR>
 * ���M�莞��z���t�����ꗗ�n���h��<BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyConditionListHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionListHandler.class);

    /**
     * (search����)<BR>
     * ���M�莞��z���t�����ꗗ���������{����B<BR>
     * <BR>
     * ���M�莞��z���t�����ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MutualFixedBuyConditionListResponse
     */
    public WEB3MutualFixedBuyConditionListResponse searchOrder(
        WEB3MutualFixedBuyConditionListRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "searchOrder(WEB3MutualFixedBuyConditionListRequest)";
        log.entering(STR_METHOD_NAME);        
        
        //���M�莞��z���t�����ꗗ�T�[�r�X���擾��
        WEB3MutualFixedBuyConditionListService l_service = null;
        WEB3MutualFixedBuyConditionListResponse l_response = null;
        try
        {
            l_service =
                (WEB3MutualFixedBuyConditionListService) 
                    Services.getService(WEB3MutualFixedBuyConditionListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyConditionListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���M�莞��z���t�����ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;   
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3MutualFixedBuyConditionListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MutualFixedBuyConditionListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "���M�莞��z���t�����ꗗ���������s���܂����B",
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;   
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
