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
filename	WEB3MstkBookValuePriceRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�����~�j�����뉿�P���o�^�n���h��(WEB3MstkBookValuePriceRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkBookPriceInputRequest;
import webbroker3.equity.message.WEB3MstkBookPriceInputResponse;
import webbroker3.equity.message.WEB3MstkBookPriceRegistRequest;
import webbroker3.equity.message.WEB3MstkBookPriceRegistResponse;
import webbroker3.equity.service.delegate.WEB3MstkBookValuePriceRegistService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * �i�����~�j�����뉿�P���o�^�n���h���j�B<BR>
 * <BR>
 * �����~�j�����뉿�P���o�^�n���h���N���X<BR>
 */
public class WEB3MstkBookValuePriceRegistHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBookValuePriceRegistHandler.class);
         
    /**
     * @@roseuid 4206F07D027C<BR>
     */
    public WEB3MstkBookValuePriceRegistHandler() 
    {
     
    }
    
    /**
     * �����~�j�����뉿�P���o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �����~�j�����뉿�P���o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) �����~�j�����뉿�P���o�^���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3MstkBookPriceInputResponse<BR>
     * @@roseuid 41C66F930245<BR>
     */
    public WEB3MstkBookPriceInputResponse getInputScreen(WEB3MstkBookPriceInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3MstkBookPriceInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MstkBookValuePriceRegistService l_service = null;
        WEB3MstkBookPriceInputResponse l_response = null;

        try
        {
            //�����~�j�����뉿�P���o�^�T�[�r�X���擾
            l_service =
                (WEB3MstkBookValuePriceRegistService) Services.getService(
                    WEB3MstkBookValuePriceRegistService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3MstkBookPriceInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j�����뉿�P���o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3MstkBookPriceInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3MstkBookPriceInputResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "�����~�j�����뉿�P���o�^�T�[�r�X.get���͉��()���\�b�h���s���ɃG���[���������܂����B", 
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
            
        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * �����~�j�����뉿�P���o�^�������s���B<BR>
     * <BR>
     * �����~�j�����뉿�P���o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param ���N�G�X�g�f�[�^ - �����~�j�����뉿�P���o�^���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return <BR>
     * webbroker3.equity.�����~�j�����T�[�r�X���f��.���b�Z�[�W�i�~�j���j.�����~�j������<BR>
     * ���P���o�^���X�|���X<BR>
     * @@roseuid 41C66F930264<BR>
     */
    public WEB3MstkBookPriceRegistResponse completeBookValuePrice(WEB3MstkBookPriceRegistRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeBookValuePrice(WEB3MstkBookPriceRegistRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MstkBookValuePriceRegistService l_service = null;
        WEB3MstkBookPriceRegistResponse l_response = null;

        try
        {
            //�����~�j�����뉿�P���o�^�T�[�r�X���擾
            l_service =
                (WEB3MstkBookValuePriceRegistService) Services.getService(
                    WEB3MstkBookValuePriceRegistService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3MstkBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j�����뉿�P���o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3MstkBookPriceRegistResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3MstkBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "�����~�j�����뉿�P���o�^�T�[�r�X.submit�뉿�P��()���\�b�h���s���ɃG���[���������܂����B", 
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
            
        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
