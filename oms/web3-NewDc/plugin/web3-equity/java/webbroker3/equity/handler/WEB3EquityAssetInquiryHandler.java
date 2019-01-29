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
filename	WEB3EquityAssetInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���t�ꗗ�n���h��(WEB3EquityAssetInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 �C�ї� (���u) �V�K�쐬
                   2004/12/16 ���(SRA) �c�Č��Ή�(�u�ۗL���Y�ꗗ�v�ˁu���t�ꗗ�v)
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquitySellListRequest;
import webbroker3.equity.message.WEB3EquitySellListResponse;
import webbroker3.equity.service.delegate.WEB3EquityAssetInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���t�ꗗ�n���h���j�B<BR>
 * <BR>
 * ���t�ꗗ�n���h���N���X
 * @@version 1.0
 */
public class WEB3EquityAssetInquiryHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityAssetInquiryHandler.class);
    /**
     * @@roseuid 409F4650027E
     */
    public WEB3EquityAssetInquiryHandler()
    {

    }

    /**
     * (���t�ꗗ���N�G�X�g)<BR>
     * ���t�ꗗ�������s���B<BR>
     * <BR>
     * ���t�ꗗ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_requestData - ���N�G�X�g�f�[�^<BR>
     * ���t�ꗗ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.equity.message.WEB3EquityAssetInquiryResponse
     * @@roseuid 406031240056
     */
    public WEB3EquitySellListResponse assetInquiryRequest(WEB3EquitySellListRequest l_requestData)
    {
        final String STR_METHOD_NAME = "assetInquiryRequest(WEB3EquitySellListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityAssetInquiryService l_service = null;
        WEB3EquitySellListResponse l_response = null;

        try
        {
            //���t�ꗗ�T�[�r�X���擾
            l_service =
                (WEB3EquityAssetInquiryService) Services.getService(
                    WEB3EquityAssetInquiryService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3EquitySellListResponse) l_requestData.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_requestData, "���t�ꗗ�T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3EquitySellListResponse) l_service.execute(l_requestData);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquitySellListResponse) l_requestData.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_requestData, "���t�ꗗ�Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquitySellListResponse) l_requestData.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_requestData, "���t�ꗗ�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
            
        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
