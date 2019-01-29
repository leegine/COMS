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
filename	WEB3BondRecruitBuyInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t���̓n���h��(WEB3BondRecruitBuyInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/08 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3BondApplyBuyInputRequest;
import webbroker3.bd.message.WEB3BondApplyBuyInputResponse;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyInputService;

/**
 * (������/���t���̓n���h��)<BR>
 * ������/���t���̓n���h��<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondRecruitBuyInputHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyInputHandler.class); 
    
    /**
     * @@roseuid 44FBFD3A0242
     */
    public WEB3BondRecruitBuyInputHandler() 
    {
     
    }
    
    /**
     * (������/���t����)<BR>
     * ������/���t���͏������s���B<BR> 
     * <BR>
     * ������/���t���̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.bd.message.WEB3BondApplyBuyInputResponse
     * @@roseuid 44C453640111
     */
    public WEB3BondApplyBuyInputResponse inputBondRecruitBuy(WEB3BondApplyBuyInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmBondSell(WEB3BondSellConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondRecruitBuyInputService l_service = null;
        WEB3BondApplyBuyInputResponse l_response = null;
        
        try
        {
            //������/���t���̓T�[�r�X���擾��
            l_service = 
                (WEB3BondRecruitBuyInputService)Services.getService(
                        WEB3BondRecruitBuyInputService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondApplyBuyInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "������/���t���̓T�[�r�X���擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3BondApplyBuyInputResponse)l_service.execute(l_request);           
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3BondApplyBuyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "������/���t���͏��������s���܂����B",
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
