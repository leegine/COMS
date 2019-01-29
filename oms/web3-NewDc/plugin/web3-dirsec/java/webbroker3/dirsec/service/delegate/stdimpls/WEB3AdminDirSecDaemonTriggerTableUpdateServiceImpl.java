head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��҃f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl(WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/07/19  ꎉ� (���u) �V�K�쐬
*/

package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchResultResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecDaemonTriggerTableUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.DaemonTriggerPK;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * (�Ǘ��҃f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�T�[�r�XImpl)<BR>
 * �Ǘ��҃f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�T�[�r�X�����N���X�B<BR>
 * 
 * @@author ꎉ�(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl implements
	WEB3AdminDirSecDaemonTriggerTableUpdateService
{
	/**
	 * ���O�o�̓��[�e�B���e�B�B
	 */
	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl.class);

	/**
	 * @@roseuid 44BE20BE01E4
	 */
	public WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl()
	{

	}

	/**
	 * �Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�������J�n����B<BR>
	 * <BR>
	 * ���N�G�X�g�f�[�^�̌^�ɂ��A <BR>
	 * �ȉ��̃��\�b�h���Ăѕ�����B <BR>
	 * <BR>
	 * ���Ǘ��ҁE�f�[�����g���K�[�e�[�u���������̓��N�G�X�g�̏ꍇ <BR>
	 * this.get�����������͉��()���R�[������B<BR>
	 * <BR>
	 * ���Ǘ��ҁE�f�[�����g���K�[�e�[�u���������ʃ��N�G�X�g�̏ꍇ <BR>
	 * this.get�������ʉ��()���R�[������B<BR>
	 * <BR>
	 * ���Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g�̏ꍇ <BR>
	 * this.validate�X�V�m�F���()���R�[������B<BR>
	 * <BR>
	 * ���Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�̏ꍇ <BR>
	 * this.submit�X�V�������()���R�[������B<BR>
	 * 
	 * @@param l_request - ���N�G�X�g�f�[�^�B<BR>
	 * @@return WEB3GenResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 44B32B290169
	 */
	public WEB3GenResponse execute(WEB3GenRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
		log.entering(STR_METHOD_NAME);

		if (l_request == null)
		{
			log.debug("�p�����[�^�l�s���B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"�p�����[�^�l�s���B");
		}

		WEB3GenResponse l_response = null;

		// ���Ǘ��ҁE�f�[�����g���K�[�e�[�u���������̓��N�G�X�g�̏ꍇ
		// this.get�����������͉��()���R�[������B
		if (l_request instanceof WEB3AdminDirSecDaemonTriggerTableSearchInputRequest)
		{
			l_response = this.getSearchConditionInput(
				(WEB3AdminDirSecDaemonTriggerTableSearchInputRequest) l_request);
		}

		// ���Ǘ��ҁE�f�[�����g���K�[�e�[�u���������ʃ��N�G�X�g�̏ꍇ 
		// this.get�������ʉ��()���R�[������B
		else if (l_request instanceof WEB3AdminDirSecDaemonTriggerTableSearchResultRequest)
		{
			l_response = this.getSearchResult(
				(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest) l_request);
		}

		// ���Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g�̏ꍇ
		// this.validate�X�V�m�F���()���R�[������B
		else if (l_request instanceof WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest)
		{
			l_response = this.validateUpdateConfirm(
				(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest) l_request);
		}

		// ���Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�̏ꍇ 
		// this.submit�X�V�������()���R�[������B
		else if (l_request instanceof WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest)
		{
			l_response = this.submitUpdateComplete(
				(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest) l_request);
		}
		else
		{
			log.debug("�p�����[�^�^�C�v�s���B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"�p�����[�^�^�C�v�s���B");
		}
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get������������)<BR>
	 * �Ǘ��҃f�[�����g���K�[�e�[�u��������ʕ\���������s���B <BR>
	 * <BR>
	 * =============================================== <BR>
	 * �V�[�P���X�} : �u�i�Ǘ��ҁjget�����������͉�ʁv�Q�ƁB<BR>
	 * ��̈ʒu : 1.3 isDIR�Ǘ���()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
	 * ��O���X���[����B <BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 *         �Ǘ��ҁE�f�[�����g���K�[�e�[�u���������̓��N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableSearchInputResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 44B32CA10198
	 */
	protected WEB3AdminDirSecDaemonTriggerTableSearchInputResponse getSearchConditionInput(
		WEB3AdminDirSecDaemonTriggerTableSearchInputRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = 
			" getSearchConditionInput(WEB3AdminDirSecDaemonTriggerTableSearchInputRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// 1.1 getInstanceFrom���O�C�����( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.2validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
		l_admin.validateAuthority(WEB3TransactionCategoryDef.DAEMON_TRIGGER_UPDATE, true);

		// 1.3 isDIR�Ǘ���( )
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
		}

		// 1.4 createResponse( )
		WEB3AdminDirSecDaemonTriggerTableSearchInputResponse l_response = 
			(WEB3AdminDirSecDaemonTriggerTableSearchInputResponse) l_request.createResponse();
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get��������)<BR>
	 * �Ǘ��҃f�[�����g���K�[�e�[�u��������ʕ\���������s���B <BR>
	 * <BR>
	 * =============================================== <BR>
	 * �V�[�P���X�} : �u�i�Ǘ��ҁjget�������ʉ�ʁv�Q�ƁB<BR>
	 * ��̈ʒu : 1.4 isDIR�Ǘ���()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
	 * ��O���X���[����B <BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 *         �Ǘ��ҁE�f�[�����g���K�[�e�[�u���������ʃ��N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableSearchResultResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 44B32DB70228
	 */
	protected WEB3AdminDirSecDaemonTriggerTableSearchResultResponse getSearchResult(
		WEB3AdminDirSecDaemonTriggerTableSearchResultRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = 
			" getSearchResult(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate( ) 
		l_request.validate();

		// 1.2 getInstanceFrom���O�C�����( ) 
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean) 
		l_admin.validateAuthority(WEB3TransactionCategoryDef.DAEMON_TRIGGER_UPDATE, true);

		// 1.4 isDIR�Ǘ���( ) 
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName()+ "." + STR_METHOD_NAME, 
				"DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
		}
		// 1.5 get�f�[�����g���K�[�e�[�u�����R�[�h(String, String) 
		DaemonTriggerRow l_daemonTriggerRow = this.getDaemonTriggerTableRecord(
			l_request.threadNo, l_request.triggerStatus);

		// 1.6 createResponse( ) 
		WEB3AdminDirSecDaemonTriggerTableSearchResultResponse l_response = 
			(WEB3AdminDirSecDaemonTriggerTableSearchResultResponse) l_request.createResponse();

		// 1.7 �v���p�e�B�Z�b�g 
		// ���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����
		
		// �����^�C�v = get�f�[�����g���K�[�e�[�u�����R�[�h�̖߂�l.get�����^�C�v
		l_response.triggerType = l_daemonTriggerRow.getTriggerType();
		
		// ���ʃR�[�h = get�f�[�����g���K�[�e�[�u�����R�[�h�̖߂�l.get���ʃR�[�h
		l_response.orderRequestNumber = l_daemonTriggerRow.getOrderRequestNumber();
		
		// �ڋq�R�[�h�i���j = get�f�[�����g���K�[�e�[�u�����R�[�h�̖߂�l.get�ڋq�R�[�h�i���j 
		l_response.rangeFrom = String.valueOf(l_daemonTriggerRow.getRangeFrom());
		
		// �ڋq�R�[�h�i���j = get�f�[�����g���K�[�e�[�u�����R�[�h�̖߂�l.get�ڋq�R�[�h�i���j
		l_response.rangeTo = String.valueOf(l_daemonTriggerRow.getRangeTo());
		
		// �X�e�[�^�X = get�f�[�����g���K�[�e�[�u�����R�[�h�̖߂�l.get�������
		l_response.triggerStatus = l_daemonTriggerRow.getTriggerStatus();
		
		// �ŏI�������� = get�f�[�����g���K�[�e�[�u�����R�[�h�̖߂�l.get�ŏI��������
		l_response.triggerDate = l_daemonTriggerRow.getTriggerDate();

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (validate�X�V�m�F)<BR>
	 * �Ǘ��҃f�[�����g���K�[�e�[�u��������ʕ\���������s���B <BR>
	 * <BR>
	 * =============================================== <BR>
	 * �V�[�P���X�} : �u�i�Ǘ��ҁjvalidate�X�V�m�F��ʁv�Q�ƁB <BR>
	 * ��̈ʒu : 1.4 isDIR�Ǘ���()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
	 * ��O���X���[����B <BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 *         �Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�m�F���N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 44B32DB90014
	 */
	protected WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse validateUpdateConfirm(
		WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validateUpdateConfirm"
				+ "(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate()
		l_request.validate();

		// 1.2 getInstanceFrom���O�C�����( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
		l_admin.validateAuthority(WEB3TransactionCategoryDef.DAEMON_TRIGGER_UPDATE, true);

		// 1.4 isDIR�Ǘ���( )
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
		}

		// 1.5 createResponse( )
		WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse l_response = 
			(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse) l_request.createResponse();

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (submit�X�V����)<BR>
	 * �Ǘ��҃f�[�����g���K�[�e�[�u��������ʕ\���������s���B <BR>
	 * <BR>
	 * =============================================== <BR>
	 * �V�[�P���X�} : �u�i�Ǘ��ҁjsubmit�X�V������ʁv�Q�ƁB <BR>
	 * ��̈ʒu : 1.4 isDIR�Ǘ���()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
	 * ��O���X���[����B <BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 *         �Ǘ��ҁE�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 44B32DB903CE
	 */
	protected WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse submitUpdateComplete(
		WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " submitUpdateComplete"
				+ "(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate() 
		l_request.validate();
		
		// 1.2 getInstanceFrom���O�C�����( ) 
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean) 
		l_admin.validateAuthority(WEB3TransactionCategoryDef.DAEMON_TRIGGER_UPDATE, true);

		// 1.4 isDIR�Ǘ���( ) 
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
		}

		// 1.5 validate����p�X���[�h(�p�X���[�h : String) 
		l_admin.validateTradingPassword(l_request.password);
		
		// 1.6 update�f�[�����g���K�[�e�[�u�����R�[�h
		this.updateDaemonTriggerTableRecord(l_request.threadNo,
			l_request.updateTriggerStatus);

		// 1.7 createResponse 
		WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse l_response = 
			(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse) l_request.createResponse();

		log.exiting(STR_METHOD_NAME);
		return l_response;

	}

	/**
	 * (get�f�[�����g���K�[�e�[�u�����R�[�h)<BR>
	 * ���L�����ɊY������s���f�[�����g���K�[�e�[�u����茟������B<BR>
	 * <BR>
	 * �P�j�����F������Ԃ̒l�ɂ�茟�������ɉ��L���w�肷��<BR>
	 * <BR>
	 * �������F�X�e�[�^�X == Null�̏ꍇ<BR>
	 * �����F�X���b�h�ԍ�<BR>
	 * <BR>
	 * �������F�X�e�[�^�X != Null�̏ꍇ<BR>
	 * �����F�X���b�h�ԍ��A�����F�������<BR>
	 * <BR>
	 * �Q�j�������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
	 * �G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_01037<BR>
	 * 
	 * @@param l_strThreadNo - (�X���b�h�ԍ�)<BR>
	 *         �X���b�h�ԍ��B<BR>
	 * @@param l_strTriggerStatus - (�������)<BR>
	 *         ������ԁB<BR>
	 * @@return DaemonTriggerRow
	 * @@throws WEB3BaseException
	 * @@roseuid 44B365D60367
	 */
	private DaemonTriggerRow getDaemonTriggerTableRecord(String l_strThreadNo,
		String l_strTriggerStatus) 
	    throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getDaemonTriggerTableRecord"
			+ "(l_strThreadNo,l_strTriggerStatus)";
		log.entering(STR_METHOD_NAME);
		
		DaemonTriggerRow l_daemonTriggerRow = null;
		String l_strWhere = null;
		Object[] l_objbindVars = null;
		List l_lisDaemonTriggerRowList = null;
		//�����F������Ԃ̒l�ɂ�茟�������ɉ��L���w�肷��
		//�����F�X�e�[�^�X == Null�̏ꍇ
		//�����F�X���b�h�ԍ�
		if (l_strTriggerStatus == null)
		{
			l_strWhere = " thread_no = ?  ";
			l_objbindVars =  new Object[1];
			l_objbindVars[0] = l_strThreadNo;
			
		}
		//�����F�X�e�[�^�X != Null�̏ꍇ
		//�����F�X���b�h�ԍ��A�����F�������
		else
		{
			l_strWhere = " thread_no = ? and  trigger_status = ?  ";
			l_objbindVars =  new Object[2];
			l_objbindVars[0] = l_strThreadNo;
			l_objbindVars[1] = l_strTriggerStatus;	
		}

		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisDaemonTriggerRowList = 
				l_queryProcessor.doFindAllQuery(
					DaemonTriggerRow.TYPE, 
					l_strWhere,
					null, 
					l_objbindVars);
		}
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (l_lisDaemonTriggerRowList == null || l_lisDaemonTriggerRowList.isEmpty())
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }		
		l_daemonTriggerRow = 
			(DaemonTriggerRow) l_lisDaemonTriggerRowList.get(0);
		log.exiting(STR_METHOD_NAME);
		return l_daemonTriggerRow;
	}

	/**
	 * (update�f�[�����g���K�[�e�[�u�����R�[�h)<BR>
     * �f�[�����g���K�[�e�[�u���̍X�V�����������Ȃ��B <BR>
	 * <BR>
	 * �P�j�@@QueryProcessor.doUpdateAllQuery()���\�b�h���R�[������B <BR>
	 * [doUpdateAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     * arg0�F�@@�����F�X���b�h�ԍ������ɐ�������PrimaryKey�I�u�W�F�N�g <BR>
     * arg1�F�@@�����F������Ԃ����ɐ�������Map�I�u�W�F�N�g <BR>
     * 
	 * @@param l_strThreadNo - (�X���b�h�ԍ�)<BR>
	 *         �X���b�h�ԍ��B<BR>
	 * @@param l_strTriggerStatus - (�������)<BR>
	 *         ������ԁB<BR>
	 * @@throws WEB3BaseException
	 * @@roseuid 44B3662C0368
	 */
	private void updateDaemonTriggerTableRecord(String l_strThreadNo,
		String l_strTriggerStatus) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " updateDaemonTriggerTableRecord"
			+ "(String l_strThreadNo, String l_strTriggerStatus)";
		log.entering(STR_METHOD_NAME);
		
        //������Ԃ����ɐ�������Map�I�u�W�F�N�g 
        Map l_mapStatus = new HashMap();
        l_mapStatus.put("trigger_status", l_strTriggerStatus);

		try
		{   
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			
			//�X���b�h�ԍ������ɐ�������PrimaryKey�I�u�W�F�N�g 
			long l_lngThreadNo = Long.parseLong(l_strThreadNo);
			DaemonTriggerPK l_daemonTriggerPK = new DaemonTriggerPK(l_lngThreadNo);
			l_queryProcessor.doUpdateQuery(l_daemonTriggerPK, l_mapStatus);
		}
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
		log.exiting(STR_METHOD_NAME);
	}

}
@
