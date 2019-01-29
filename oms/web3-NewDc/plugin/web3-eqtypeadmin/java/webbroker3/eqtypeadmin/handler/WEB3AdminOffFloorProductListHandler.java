head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������ꗗ�n���h��(WEB3AdminOffFloorProductListHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorProductListService;

/**
 * (�Ǘ��җ���O���������ꗗ�n���h��)<BR>
 * <BR>
 * �Ǘ��җ���O���������ꗗ�n���h���N���X<BR>
 * <BR>
 * WEB3AdminOffFloorProductListHandler class<BR>
 * @@author Anupama
 * @@version 1.0
 */
 public class WEB3AdminOffFloorProductListHandler implements MessageHandler
{
    /**
    * log variable
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorProductListHandler.class);

   /**
    * @@roseuid 421AE49B037D
    */
   public WEB3AdminOffFloorProductListHandler()
   {

   }

   /**
    * (get�����ꗗ)<BR>
    * <BR>
    * �Ǘ��җ���O���������ꗗ�\���������s���B<BR>
    * <BR>
    * �Ǘ��җ���O���������ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
    * <BR>
    * ----<English>------------<BR>
    * <BR>
    * getProductList<BR>
    * <BR>
    * Execute WEB3AdminOffFloorProductListService input screen process<BR>
    * <BR>
    * Acquire WEB3AdminOffFloorProductListService, and call execute()<BR>
    * <BR>
    * @@param l_request - (���N�G�X�g�f�[�^)<BR>
    * <BR>
    * �Ǘ��җ���O���������ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
    * <BR>
    * ----<English>-------------<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * WEB3AdminOffFloorProductListRequest object<BR>
    * <BR>
    *
    * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListResponse
    * @@roseuid 41BCF928023E
    */
   public WEB3AdminOffFloorProductListResponse getProductList(
    WEB3AdminOffFloorProductListRequest l_request)
   {
       final String STR_METHOD_NAME = "getProductList(WEB3AdminOffFloorProductListRequest)";
       log.entering(STR_METHOD_NAME);

       WEB3AdminOffFloorProductListService l_service = null;
       WEB3AdminOffFloorProductListResponse l_response = null;

       try
       {
           l_service =
               (WEB3AdminOffFloorProductListService) Services.getService(
           WEB3AdminOffFloorProductListService.class);

       } catch (Exception l_exp)
       {
           l_response = (WEB3AdminOffFloorProductListResponse) l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.error(
               l_request,
               "Failed while getProductList request is called",
               l_response.errorInfo,
               l_exp);
           log.exiting(STR_METHOD_NAME);
           return l_response;

       }
       try
       {
           l_response = (WEB3AdminOffFloorProductListResponse) l_service.execute(l_request);
       } catch (WEB3BaseException l_baseExp)
       {
           l_response = (WEB3AdminOffFloorProductListResponse) l_request.createResponse();
           l_response.errorInfo = l_baseExp.getErrorInfo();
           log.error(l_request, "getProductList Request failed", l_baseExp);
       }
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
}
@
