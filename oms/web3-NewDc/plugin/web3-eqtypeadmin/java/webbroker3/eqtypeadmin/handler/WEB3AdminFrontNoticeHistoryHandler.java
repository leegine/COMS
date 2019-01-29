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
filename	WEB3AdminFrontNoticeHistoryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғʒm�����Q�ƃn���h���N���X(WEB3AdminFrontNoticeHistoryHandler.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontNoticeHistoryService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ғʒm�����Q�ƃn���h��)<BR>
 * <BR>
 * �Ǘ��Ғʒm�����Q�ƃn���h���N���X<BR>
 * <BR>
 * WEB3AdminFrontNoticeHistoryHandler<BR>
 * @@author SCS ���{
 * @@version 1.0
 */
public class WEB3AdminFrontNoticeHistoryHandler implements MessageHandler 
{
   
	/**
	 * @@log WEB3LogUtility
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminFrontNoticeHistoryHandler.class);

   /**
    * @@roseuid 43001B0A00AF
    */
   public WEB3AdminFrontNoticeHistoryHandler() 
   {
    
   }
   
   /**
    * �Ǘ��Ғʒm�����Q�Ɠ��͉�ʕ\���������s���B<BR>
    * <BR>
    * �Ǘ��Ғʒm�����Q�ƃT�[�r�XImpl���擾���A<BR>
    * execute()���\�b�h���R�[������B<BR>
    * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE�ʒm�����Q�Ɠ��̓��N�G�X�g�I�u�W�F�N�g
    * @@return �Ǘ��ҁE�ʒm�����Q�Ɠ��̓��X�|���X
    * @@roseuid 42D214B4037F
    */
   public WEB3AdminFrontNoticeHistoryInputResponse getInputScreen(WEB3AdminFrontNoticeHistoryInputRequest requestdata) 
   {
		final String STR_METHOD_NAME =
			"getInputScreen(WEB3AdminFrontNoticeHistoryInputRequest)";
		log.entering(STR_METHOD_NAME);
	
		WEB3AdminFrontNoticeHistoryInputResponse l_response = null;
		WEB3AdminFrontNoticeHistoryService l_service = null;
	
		try
		{
			l_service =
				(WEB3AdminFrontNoticeHistoryService) Services.getService(
			WEB3AdminFrontNoticeHistoryService.class);
	
		} catch (Exception l_exp)
		{
			l_response =
				(WEB3AdminFrontNoticeHistoryInputResponse) requestdata.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
	
			log.error(
				requestdata,
				"Failed while acquiring WEB3AdminFrontNoticeHistoryServiceImpl ",
				l_response.errorInfo,
				l_exp);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response =
				(WEB3AdminFrontNoticeHistoryInputResponse) l_service.execute(requestdata);
		} catch (WEB3BaseException l_exp)
		{
			log.error(requestdata, "Failed to access getInputScreen()", l_exp);
			l_response =
				(WEB3AdminFrontNoticeHistoryInputResponse) requestdata.createResponse();
			l_response.errorInfo = l_exp.getErrorInfo();
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
   }
   
   /**
    * �Ǘ��Ғʒm�����Q�Ə������s���B<BR>
    * <BR>
    * �Ǘ��Ғʒm�����Q�ƃT�[�r�XImpl���擾���A<BR>
    * execute()���\�b�h���R�[������B<BR>
    * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE�ʒm�����Q�ƃ��N�G�X�g�I�u�W�F�N�g
    * @@return �Ǘ��ҁE�ʒm�����Q�ƃ��X�|���X
    * @@roseuid 42D214BD015C
    */
   public WEB3AdminFrontNoticeHistoryReferenceResponse getReferenceScreen(WEB3AdminFrontNoticeHistoryReferenceRequest requestdata) 
   {
		final String STR_METHOD_NAME =
			"getReferenceScreen(WEB3AdminFrontNoticeHistoryReferenceRequest)";
		log.entering(STR_METHOD_NAME);
	
		WEB3AdminFrontNoticeHistoryReferenceResponse l_response = null;
		WEB3AdminFrontNoticeHistoryService l_service = null;
		try
		{
			l_service =
				(WEB3AdminFrontNoticeHistoryService) Services.getService(
			WEB3AdminFrontNoticeHistoryService.class);
	
		} catch (Exception l_exp)
		{
			l_response =
				(WEB3AdminFrontNoticeHistoryReferenceResponse) requestdata.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(
				requestdata,
				"Failed while acquiring WEB3AdminFrontNoticeHistoryServiceImpl ",
				l_response.errorInfo,
				l_exp);
			log.exiting(STR_METHOD_NAME);
			return l_response;
		}
		try
		{
			l_response =
				(WEB3AdminFrontNoticeHistoryReferenceResponse) l_service.execute(requestdata);
		} catch (WEB3BaseException l_exp)
		{
			log.error(requestdata, "Failed to access getReferenceScreen()", l_exp);
			l_response =
				(WEB3AdminFrontNoticeHistoryReferenceResponse) requestdata.createResponse();
			l_response.errorInfo = l_exp.getErrorInfo();
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
   }
}
@
