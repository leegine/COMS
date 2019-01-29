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
filename	WEB3EquityBookValuePriceRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������뉿�P���o�^�n���h��(WEB3EquityBookValuePriceRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityBookPriceInputRequest;
import webbroker3.equity.message.WEB3EquityBookPriceInputResponse;
import webbroker3.equity.message.WEB3EquityBookPriceRegistRequest;
import webbroker3.equity.message.WEB3EquityBookPriceRegistResponse;
import webbroker3.equity.service.delegate.WEB3EquityBookValuePriceRegistService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * �i���������뉿�P���o�^�n���h���j�B<BR>
 * <BR>
 * ���������뉿�P���o�^�n���h���N���X<BR>
 */
public class WEB3EquityBookValuePriceRegistHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBookValuePriceRegistHandler.class);
         
   /**
     * @@roseuid 4206EF8F0079
     */
    public WEB3EquityBookValuePriceRegistHandler() 
    {
    
    }
   
    /**
     * ���������뉿�P���o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * ���������뉿�P���o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) ���������뉿�P���o�^���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3EquityBookPriceInputResponse<BR>
     * @@roseuid 41B9245A01F1<BR>
     */
    public WEB3EquityBookPriceInputResponse getInputScreen(WEB3EquityBookPriceInputRequest l_request) 
    {
       final String STR_METHOD_NAME = "getInputScreen(WEB3EquityBookPriceInputRequest)";
       log.entering(STR_METHOD_NAME);

       WEB3EquityBookValuePriceRegistService l_service = null;
       WEB3EquityBookPriceInputResponse l_response = null;

       try
       {
           //���������뉿�P���o�^�T�[�r�X���擾
           l_service =
               (WEB3EquityBookValuePriceRegistService) Services.getService(
                   WEB3EquityBookValuePriceRegistService.class);
       }
       catch (Exception e)
       {
           l_response =
               (WEB3EquityBookPriceInputResponse) l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.error(
               l_request,
               "���������뉿�P���o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
               l_response.errorInfo,
               e);
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
                
       try
       {        
           //execute()���\�b�h���R�[������B
           l_response =
               (WEB3EquityBookPriceInputResponse) l_service.execute(l_request);
       }
       catch (WEB3BaseException e)
       {
           l_response =
               (WEB3EquityBookPriceInputResponse) l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
           log.error(
               l_request, 
               "���������뉿�P���o�^�T�[�r�X.get���͉��()���\�b�h���s���ɃG���[���������܂����B", 
               e);
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
            
       log.exiting(STR_METHOD_NAME);

       // ���X�|���X�I�u�W�F�N�g��ԋp����B
       return l_response;
    }
   
    /**
     * ���������뉿�P���o�^�������s���B<BR>
     * <BR>
     * ���������뉿�P���o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param ���N�G�X�g�f�[�^ - ���������뉿�P���o�^���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return <BR>
     * webbroker3.equity.�����T�[�r�X���f��.���b�Z�[�W�i�����j.���������뉿�P���o�^���X
     * �|���X
     * @@roseuid 41B924A900E7
     */
    public WEB3EquityBookPriceRegistResponse completeBookValuePrice(WEB3EquityBookPriceRegistRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeBookValuePrice(WEB3EquityBookPriceRegistRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityBookValuePriceRegistService l_service = null;
        WEB3EquityBookPriceRegistResponse l_response = null;

        try
        {
            //���������뉿�P���o�^�T�[�r�X���擾
            l_service =
                (WEB3EquityBookValuePriceRegistService) Services.getService(
                    WEB3EquityBookValuePriceRegistService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3EquityBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���������뉿�P���o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3EquityBookPriceRegistResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "���������뉿�P���o�^�T�[�r�X.submit�뉿�P��()���\�b�h���s���ɃG���[���������܂����B", 
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
