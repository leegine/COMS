head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPSearchAdvanceCustomerHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋��ێ�������/���֋������ڋq�����n���h���N���X(WEB3AdminTPSearchAdvanceCustomerHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/02/08 asano(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchListResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPSearchAdvanceCustomerService;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPSearchAdvanceCustomerServiceImpl;
import webbroker3.util.WEB3LogUtility;


/**
 * (�ۏ؋��ێ�������/���֋������ڋq�����n���h���N���X)<BR>
 * <BR>
 * MessegeHandler�N���X���g���B<BR>
 */
public class WEB3AdminTPSearchAdvanceCustomerHandler implements MessageHandler 
{
    /**
     * (���O)
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPSearchAdvanceCustomerHandler.class);
    
    
   /**
    * �R���X�g���N�^
    */
   public WEB3AdminTPSearchAdvanceCustomerHandler() 
   {
   }

   /**
    * (get�ۏ؋��ێ�������/���֋������ڋq����)<BR>
    * <BR>
    * @@param l_request �ۏ؋��ێ�������/���֋������ڋq�������͉�ʕ\�����N�G�X�g
    * @@return WEB3AdminTPAdvanceCustomerSearchInputResponse �ۏ؋��ێ�������/���֋������ڋq�������͉�ʕ\�����X�|���X
    */
   public WEB3AdminTPAdvanceCustomerSearchInputResponse getAdvanceCustomerInput(WEB3AdminTPAdvanceCustomerSearchInputRequest l_request) 
   {
       //�ۏ؋��ێ�������/���֋������ڋq�����T�[�r�X
       WEB3AdminTPSearchAdvanceCustomerService service = null;
       //�ۏ؋��ێ�������/���֋������ڋq�������͉�ʃ��X�|���X
       WEB3AdminTPAdvanceCustomerSearchInputResponse l_response = null;
       
       try
       {
           //�ۏ؋��ێ�������/���֋������ڋq�����T�[�r�X�擾
           service = (WEB3AdminTPSearchAdvanceCustomerService)Services.getService(WEB3AdminTPSearchAdvanceCustomerService.class);
           //�ۏ؋��ێ�������/���֋������ڋq�����T�[�r�X���s
           l_response = (WEB3AdminTPAdvanceCustomerSearchInputResponse)service.execute(l_request);           
       }
       catch(WEB3BaseException e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchInputResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(WEB3BaseRuntimeException e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchInputResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(Exception e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchInputResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
       }
       
       //�ۏ؋��ێ�������/���֋������ڋq�������͉�ʕ\�����X�|���X�ԋp
       return l_response;
   }

   
   /**
    * (get�ۏ؋��ێ�������/���֋������ڋq�ꗗ)<BR>
    * <BR>
    * @@param l_request �ۏ؋��ێ�������/���֋������ڋq�����ꗗ��ʕ\�����N�G�X�g
    * @@return WEB3AdminTPAdvanceCustomerSearchListResponse �ۏ؋��ێ�������/���֋������ڋq�����ꗗ��ʕ\�����X�|���X
    */
   public WEB3AdminTPAdvanceCustomerSearchListResponse getAdvanceCustomerList(WEB3AdminTPAdvanceCustomerSearchListRequest l_request) 
   {
       //�ۏ؋��ێ�������/���֋������ڋq�����T�[�r�X
       WEB3AdminTPSearchAdvanceCustomerService service = null;
       //�ۏ؋��ێ�������/���֋������ڋq�����ꗗ��ʕ\�����X�|���X
       WEB3AdminTPAdvanceCustomerSearchListResponse l_response = null;
       try
       {
           //�ۏ؋��ێ�������/���֋������ڋq�����T�[�r�X�擾
           service = (WEB3AdminTPSearchAdvanceCustomerService)Services.getService(WEB3AdminTPSearchAdvanceCustomerService.class);
           //�ۏ؋��ێ�������/���֋������ڋq�����T�[�r�X���s
           l_response = (WEB3AdminTPAdvanceCustomerSearchListResponse)service.execute(l_request);           
       }
       catch(WEB3BaseException e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchListResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(WEB3BaseRuntimeException e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchListResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(Exception e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchListResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
       }
              
       //�ۏ؋��ێ�������/���֋������ڋq�����ꗗ��ʕ\�����X�|���X�ԋp
       return l_response;
   }
   
   
   /**
    * (get�ۏ؋��ێ�������/���֋������ڋq�ڍ�)<BR>
    * <BR>
    * @@param l_request  �ۏ؋��ێ�������/���֋������ڋq�ڍ׉�ʕ\�����N�G�X�g
    * @@return WEB3AdminTPAdvanceCustomerDetailResponse  �ۏ؋��ێ�������/���֋������ڋq�ڍ׉�ʕ\�����X�|���X
    */
   public WEB3AdminTPAdvanceCustomerDetailResponse getMarginMaintenanceRateDebitAmountCustomerDetail(WEB3AdminTPAdvanceCustomerDetailRequest l_request) 
   {
       //�ۏ؋��ێ�������/���֋������ڋq�����T�[�r�X
       WEB3AdminTPSearchAdvanceCustomerService service = null;
       //�ۏ؋��ێ�������/���֋������ڋq�ڍ׉�ʕ\�����X�|���X
       WEB3AdminTPAdvanceCustomerDetailResponse l_response = null;
       try
       {
           //�ۏ؋��ێ�������/���֋������ڋq�����T�[�r�X�擾
           service = (WEB3AdminTPSearchAdvanceCustomerService)Services.getService(WEB3AdminTPSearchAdvanceCustomerService.class);
           //�ۏ؋��ێ�������/���֋������ڋq�����T�[�r�X���s
           l_response = (WEB3AdminTPAdvanceCustomerDetailResponse)service.execute(l_request);           
       }
       catch(WEB3BaseException e)
       {
           log.error(e.getMessage(), e);
           l_response = (WEB3AdminTPAdvanceCustomerDetailResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(WEB3BaseRuntimeException e)
       {
           log.error(e.getMessage(), e);
           l_response =  (WEB3AdminTPAdvanceCustomerDetailResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(Exception e)
       {   
           log.error(e.getMessage(), e);
           l_response =  (WEB3AdminTPAdvanceCustomerDetailResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
       }
       
       //�ۏ؋��ێ�������/���֋������ڋq�ڍ׉�ʕ\�����X�|���X�ԋp
       return l_response;
   }
   
}
@
