head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl(WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  ����� (���u) �V�K�쐬
				   2006/08/11  ����� (���u) ����̍X�E���f��015
                   2007/01/08  ����� (���u) �d�l�ύX�E���f��022
Revesion History : 2007/10/30  ���g   (���u) �d�l�ύX�E���f��114
Revesion History : 2007/11/02  ���g   (���u) �d�l�ύX�E���f��115
Revesion History : 2008/07/15  �k�v�u (���u) �d�l�ύX�E���f��131,�c�a�X�V�d�l010
Revesion History : 2008/08/28  ������ (���u) �d�l�̍X�E�����̖��013
*/

package webbroker3.dirsec.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.define.WEB3AdminDirOrderUnitTblKbnDef;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAioOrderUnitTableUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;

/**
 * (�Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�XImpl)<BR>
 * �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�T�[�r�X�����N���X�B<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl 
	implements WEB3AdminDirSecAioOrderUnitTableUpdateService
{
	/**
	 * ���O�o�̓��[�e�B���e�B�B
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl.class);

	/**
	 * @@roseuid 44BE20BE031C
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl()
	{

	}

	/**
	 * �Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�������J�n����B<BR>
	 * <BR>
	 * ���N�G�X�g�f�[�^�̌^�ɂ��A <BR>
	 * �ȉ��̃��\�b�h���Ăѕ�����B <BR>
	 * <BR>
	 * ���Ǘ��ҁE�����P�ʃe�[�u���������̓��N�G�X�g�̏ꍇ <BR>
	 * this.get�������()���R�[������B <BR>
	 * <BR>
	 * ���Ǘ��ҁE�����P�ʃe�[�u���������ʃ��N�G�X�g�̏ꍇ <BR>
	 * this.get�������ʉ��()���R�[������B<BR>
	 * <BR>
	 * ���Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�m�F���N�G�X�g�̏ꍇ <BR>
	 * this.get�X�V�m�F���()���R�[������B<BR>
	 * <BR>
	 * ���Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�������N�G�X�g�̏ꍇ<BR>
	 * this.get�X�V�������()���R�[������B<BR>
	 * 
	 * @@param l_request - ���N�G�X�g�f�[�^�B<BR>
	 * @@return WEB3GenResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 444E04A900F3
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

		// ���Ǘ��ҁE�����P�ʃe�[�u���������̓��N�G�X�g�̏ꍇ <BR>
		// this.get�������()���R�[������B
		if (l_request instanceof WEB3AdminDirSecAioOrderUnitTableSearchInputRequest)
		{
			l_response = 
				this.getSearchScreen(
					(WEB3AdminDirSecAioOrderUnitTableSearchInputRequest) l_request);
		}

		// ���Ǘ��ҁE�����P�ʃe�[�u���������ʃ��N�G�X�g�̏ꍇ <BR>
		// this.get�������ʉ��()���R�[������B
		else if (l_request instanceof WEB3AdminDirSecAioOrderUnitTableSearchResultRequest)
		{
			l_response = 
				this.getSearchResultScreen(
					(WEB3AdminDirSecAioOrderUnitTableSearchResultRequest) l_request);
		}

		// ���Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�m�F���N�G�X�g�̏ꍇ <BR>
		// this.get�X�V�m�F���)���R�[������B
		else if (l_request instanceof WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest)
		{
			l_response = 
				this.getUpdateConfirmScreen(
					(WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest) l_request);
		}

		// ���Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�������N�G�X�g�̏ꍇ<BR>
		// this.get�X�V�������()���R�[������B
		else if (l_request instanceof WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest)
		{
			l_response = 
				this.getUpdateCompleteScreen(
					(WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest) l_request);
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
	 * (get�������)<BR>
	 * �Ǘ��Ғ����P�ʃe�[�u�����R�[�h������ʕ\���������s���B <BR>
	 * <BR>
	 * =============================================== <BR>
	 * �V�[�P���X�} : �u�i�Ǘ��ҁjget�������ʉ�ʁv�Q�ƁB<BR>
	 * ��̈ʒu :1.4 isDIR�Ǘ���()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 * �Ǘ��ҁE�����P�ʃe�[�u���������̓��N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableSearchInputResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 444E0B04025A
	 */
	protected WEB3AdminDirSecAioOrderUnitTableSearchInputResponse getSearchScreen(
		WEB3AdminDirSecAioOrderUnitTableSearchInputRequest l_request) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			" getSearchScreen(WEB3AdminDirSecOrderUnitTableSearchInputRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// 1.1 getInstanceFrom���O�C�����( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
		// [validate�����i�j�Ɏw�肷�����]
		// �@@�\�J�e�S���R�[�h�F"Z0102" �i�V�X�e���Ǘ� �����P�ʃe�[�u��������ԍX�V�j
		// is�X�V�Ftrue
		l_admin.validateAuthority(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE, true);

		// 1.3 isDIR�Ǘ���( )
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

		// 1.4 DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A��O���X���[����B
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
		WEB3AdminDirSecAioOrderUnitTableSearchInputResponse l_response = 
			(WEB3AdminDirSecAioOrderUnitTableSearchInputResponse) l_request.createResponse();

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get�������ʉ��)<BR>
	 * �Ǘ��Ғ����P�ʃe�[�u�����R�[�h�������ʉ�ʕ\���������s���B<BR>
	 * <BR>
	 * =============================================== <BR>
	 * �V�[�P���X�} : �u�i�Ǘ��ҁjget�������ʉ�ʁv�Q�ƁB<BR>
	 * ��̈ʒu :1.5 isDIR�Ǘ���()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 * �Ǘ��ҁE�����P�ʃe�[�u���������ʃ��N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableSearchResultResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 444E0B0A011F
	 */
	protected WEB3AdminDirSecAioOrderUnitTableSearchResultResponse getSearchResultScreen(
		WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = 
			" getSearchResultScreen(WEB3AdminDirSecOrderUnitTableSearchResultRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate( )
		l_request.validate();

		// 1.2 getInstanceFrom���O�C�����( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
		// [validate�����i�j�Ɏw�肷�����]
		// �@@�\�J�e�S���R�[�h�F"Z0102" �i�V�X�e���Ǘ� �����P�ʃe�[�u��������ԍX�V�j
		// is�X�V�Ftrue
		l_admin.validateAuthority(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE, true);

		// 1.4 isDIR�Ǘ���( )
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

		// 1.5 DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A��O���X���[����B
		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
		}

        // 1.7 createResponse( )
        WEB3AdminDirSecAioOrderUnitTableSearchResultResponse l_response =
            (WEB3AdminDirSecAioOrderUnitTableSearchResultResponse) l_request.createResponse();

        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 0(�O��)�̏ꍇ�A�ȉ��̏������s���B
		//����t���[
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 0(�O��)�̏ꍇ�A
        //�ȉ��̏������s���B
        //get�O�������P�ʃe�[�u�����R�[�h(long)
        //�O�������P�ʃe�[�u���̃��R�[�h���������������ƂɎ擾����B
        //[get�O�������P�ʃe�[�u�����R�[�h(�j�Ɏw�肷�����]
        //���������f�[�^�@@�F�@@���N�G�X�g�f�[�^.�����P��ID
        if (WEB3AdminDirOrderUnitTblKbnDef.FEQ.equals(l_request.orderUnitTblKbn))
        {
            FeqOrderUnitRow l_feqOrderUnitRow = this.getFeqOrderUnitTableRecord(
                Long.parseLong(l_request.orderUnitId));

            // 1.8 �i*�j�v���p�e�B�Z�b�g
            // �i*�j���X�|���X�f�[�^�Ɉȉ��̓��e���Z�b�g����B
            // ����ID = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get����ID()
            l_response.accountId = String.valueOf(l_feqOrderUnitRow.getAccountId());

            // ���XID = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get���XID()
            l_response.branchId = String.valueOf(l_feqOrderUnitRow.getBranchId());

            // ����ID = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get����ID()
            l_response.orderId = String.valueOf(l_feqOrderUnitRow.getOrderId());

            // �����^�C�v = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�����^�C�v()
            l_response.productType = String.valueOf(l_feqOrderUnitRow.getProductType().intValue());

            // ������� = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�������()
            l_response.orderStatus = String.valueOf(l_feqOrderUnitRow.getOrderStatus().intValue());

            // �����L����� = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�����L�����()
            l_response.orderOpenStatus = String.valueOf(l_feqOrderUnitRow.getOrderOpenStatus().intValue());

            // ���ʃR�[�h = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get���ʃR�[�h()
            l_response.requestNumber = l_feqOrderUnitRow.getOrderRequestNumber();
        }
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 1(���o��)�̏ꍇ�A�ȉ��̏������s���B
        //����t���[
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 1(���o��)�̏ꍇ�A
        //�ȉ��̏������s���B
		//get�����P�ʃe�[�u�����R�[�h(long)
		//[get�����P�ʃe�[�u�����R�[�h(�j�Ɏw�肷�����]
		//���������f�[�^ �F ���N�G�X�g�f�[�^.�����P��ID
        else if (WEB3AdminDirOrderUnitTblKbnDef.AIO.equals(l_request.orderUnitTblKbn))
        {
    		AioOrderUnitRow l_aioOrderUnitRow = this.getOrderUnitTableRecord(
    			Long.parseLong(l_request.orderUnitId));

            // 1.8 �i*�j�v���p�e�B�Z�b�g
            // �i*�j���X�|���X�f�[�^�Ɉȉ��̓��e���Z�b�g����B
            // ����ID = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get����ID()
            l_response.accountId = String.valueOf(l_aioOrderUnitRow.getAccountId());

            // ���XID = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get���XID()
            l_response.branchId = String.valueOf(l_aioOrderUnitRow.getBranchId());

            // ����ID = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get����ID()
            l_response.orderId = String.valueOf(l_aioOrderUnitRow.getOrderId());

            // �����^�C�v = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�����^�C�v()
            l_response.productType = String.valueOf(l_aioOrderUnitRow.getProductType().intValue());

            // ������� = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�������()
            l_response.orderStatus = String.valueOf(l_aioOrderUnitRow.getOrderStatus().intValue());

            // �����L����� = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�����L�����()
            l_response.orderOpenStatus = String.valueOf(l_aioOrderUnitRow.getOrderOpenStatus().intValue());

            // ���ʃR�[�h = get�����P�ʃe�[�u�����R�[�h()�̖߂�l.get���ʃR�[�h()
            l_response.requestNumber = l_aioOrderUnitRow.getOrderRequestNumber();
        }
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 2(���M)�̏ꍇ�A�ȉ��̏������s���B
        //����t���[
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 2(���M)�̏ꍇ�A
        //�ȉ��̏������s���B
        else if (WEB3AdminDirOrderUnitTblKbnDef.MUTUAL.equals(l_request.orderUnitTblKbn))
        {
            // get���M�����P�ʃe�[�u�����R�[�h(long)
            MutualFundOrderUnitRow l_mutualFundOrderUnitRow =
                this.getMutualFundOrderUnitTableRecord(
                    Long.parseLong(l_request.orderUnitId));

            // �i*�j�v���p�e�B�Z�b�g
            // �i*�j���X�|���X�f�[�^�Ɉȉ��̓��e���Z�b�g����B
            // ����ID = get���M�����P�ʃe�[�u�����R�[�h()�̖߂�l.get����ID()
            l_response.accountId = String.valueOf(l_mutualFundOrderUnitRow.getAccountId());

            // ���XID = get���M�����P�ʃe�[�u�����R�[�h()�̖߂�l.get���XID()
            l_response.branchId = String.valueOf(l_mutualFundOrderUnitRow.getBranchId());

            // ����ID = get���M�����P�ʃe�[�u�����R�[�h()�̖߂�l.get����ID()
            l_response.orderId = String.valueOf(l_mutualFundOrderUnitRow.getOrderId());

            // �����^�C�v = get���M�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�����^�C�v()
            l_response.productType = String.valueOf(l_mutualFundOrderUnitRow.getProductType().intValue());

            // ������� = get���M�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�������()
            l_response.orderStatus = String.valueOf(l_mutualFundOrderUnitRow.getOrderStatus().intValue());

            // �����L����� = get���M�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�����L�����()
            l_response.orderOpenStatus = String.valueOf(l_mutualFundOrderUnitRow.getOrderOpenStatus().intValue());

            // ���ʃR�[�h = get���M�����P�ʃe�[�u�����R�[�h()�̖߂�l.get���ʃR�[�h()
            l_response.requestNumber = l_mutualFundOrderUnitRow.getOrderRequestNumber();

            // ����� = get���M�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�����()
            l_response.execStatus = l_mutualFundOrderUnitRow.getExecStatus();
        }
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 3(����)�̏ꍇ�A�ȉ��̏������s���B
        //����t���[
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 3(����)�̏ꍇ�A
        //�ȉ��̏������s���B
        else if (WEB3AdminDirOrderUnitTblKbnDef.EQ.equals(l_request.orderUnitTblKbn))
        {
        	EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                this.getEqtypeOrderUnitTableRecord(
                    Long.parseLong(l_request.orderUnitId));

            // �i*�j�v���p�e�B�Z�b�g
            // �i*�j���X�|���X�f�[�^�Ɉȉ��̓��e���Z�b�g����B
            // ����ID = get���������P�ʃe�[�u�����R�[�h()�̖߂�l.get����ID()
            l_response.accountId = String.valueOf(l_eqtypeOrderUnitRow.getAccountId());

            // ���XID = get���������P�ʃe�[�u�����R�[�h()�̖߂�l.get���XID()
            l_response.branchId = String.valueOf(l_eqtypeOrderUnitRow.getBranchId());

            // ����ID = get���������P�ʃe�[�u�����R�[�h()�̖߂�l.get����ID()
            l_response.orderId = String.valueOf(l_eqtypeOrderUnitRow.getOrderId());

            // �����^�C�v = get���������P�ʃe�[�u�����R�[�h()�̖߂�l.get�����^�C�v()
            l_response.productType = String.valueOf(l_eqtypeOrderUnitRow.getProductType().intValue());

            // ������� = get���������P�ʃe�[�u�����R�[�h()�̖߂�l.get�������()
            l_response.orderStatus = String.valueOf(l_eqtypeOrderUnitRow.getOrderStatus().intValue());

            // �����L����� = get���������P�ʃe�[�u�����R�[�h()�̖߂�l.get�����L�����()
            l_response.orderOpenStatus = String.valueOf(l_eqtypeOrderUnitRow.getOrderOpenStatus().intValue());

            // ���ʃR�[�h = get���������P�ʃe�[�u�����R�[�h()�̖߂�l.get���ʃR�[�h()
            l_response.requestNumber = l_eqtypeOrderUnitRow.getOrderRequestNumber();

            // ����� = null
            l_response.execStatus = null;

            // ������ = get���������P�ʃe�[�u�����R�[�h()�̖߂�l.get������()
            l_response.orderBizDate = l_eqtypeOrderUnitRow.getBizDate();
        }
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 4(�敨OP)�̏ꍇ�A�ȉ��̏������s���B
        //����t���[
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 4(�敨OP)�̏ꍇ�A
        //�ȉ��̏������s���B
        else if (WEB3AdminDirOrderUnitTblKbnDef.IFO.equals(l_request.orderUnitTblKbn))
        {
        	IfoOrderUnitRow l_ifoOrderUnitRow =
                this.getIfoOrderUnitTableRecord(
                    Long.parseLong(l_request.orderUnitId));

            // �i*�j�v���p�e�B�Z�b�g
            // �i*�j���X�|���X�f�[�^�Ɉȉ��̓��e���Z�b�g����B
            // ����ID = get�敨OP�����P�ʃe�[�u�����R�[�h()�̖߂�l.get����ID()
            l_response.accountId = String.valueOf(l_ifoOrderUnitRow.getAccountId());

            // ���XID = get�敨OP�����P�ʃe�[�u�����R�[�h()�̖߂�l.get���XID()
            l_response.branchId = String.valueOf(l_ifoOrderUnitRow.getBranchId());

            // ����ID = get�敨OP�����P�ʃe�[�u�����R�[�h()�̖߂�l.get����ID()
            l_response.orderId = String.valueOf(l_ifoOrderUnitRow.getOrderId());

            // �����^�C�v = get�敨OP�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�����^�C�v()
            l_response.productType = String.valueOf(l_ifoOrderUnitRow.getProductType().intValue());

            // ������� = get�敨OP�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�������()
            l_response.orderStatus = String.valueOf(l_ifoOrderUnitRow.getOrderStatus().intValue());

            // �����L����� = get�敨OP�����P�ʃe�[�u�����R�[�h()�̖߂�l.get�����L�����()
            l_response.orderOpenStatus = String.valueOf(l_ifoOrderUnitRow.getOrderOpenStatus().intValue());

            // ���ʃR�[�h = get�敨OP�����P�ʃe�[�u�����R�[�h()�̖߂�l.get���ʃR�[�h()
            l_response.requestNumber = l_ifoOrderUnitRow.getOrderRequestNumber();

            // ����� = null
            l_response.execStatus = null;

            // ������ = get�敨OP�����P�ʃe�[�u�����R�[�h()�̖߂�l.get������()
            l_response.orderBizDate = l_ifoOrderUnitRow.getBizDate();
        }

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get�X�V�m�F���)<BR>
	 * �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V�m�F��ʕ\���������s���B<BR>
	 * <BR>
	 * =============================================== <BR>
	 * �V�[�P���X�} : �u�i�Ǘ��ҁjget�X�V�m�F��ʁv�Q�ƁB<BR>
	 * ��̈ʒu :1.5 isDIR�Ǘ���()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 * �Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�m�F���N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecDaemonTriggerTableUpdateConfirmResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 444E0B0C0237
	 */
	protected WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse getUpdateConfirmScreen(
		WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest l_request) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getUpdateConfirmScreen"
			+ "(WEB3AdminDirSecOrderUnitTableUpdateConfirmRequest)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate()
		l_request.validate();

		//validate�X�V_������(String)
		//�X�V_�������̃`�F�b�N���s���B
		//[validate�X�V_�������i�j�Ɏw�肷�����]
		//�X�V_�������F���N�G�X�g�f�[�^.�X�V_������
		this.validateUpdateOrderBizDate(l_request.updateOrderBizDate);

		// 1.2 getInstanceFrom���O�C�����( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
		// [validate�����i�j�Ɏw�肷�����]
		// �@@�\�J�e�S���R�[�h�F"Z0102" �i�V�X�e���Ǘ� �����P�ʃe�[�u��������ԍX�V�j
		// is�X�V�Ftrue
		l_admin.validateAuthority(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE, true);

		// 1.4 isDIR�Ǘ���( )
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

		// 1.5 DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A��O���X���[����B
		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
		}

		// 1.6 createResponse()
		WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse l_response = 
			(WEB3AdminDirSecAioOrderUnitTableUpdateConfirmResponse) l_request.createResponse();

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get�X�V�������)<BR>
	 * �Ǘ��Ғ����P�ʃe�[�u��������ԍX�V������ʕ\���������s���B<BR>
	 * <BR>
	 * =============================================== <BR>
	 * �V�[�P���X�} : �u�i�Ǘ��ҁjget�X�V������ʁv�Q�ƁB<BR>
	 * ��̈ʒu :1.5 isDIR�Ǘ���()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
	 * class : WEB3BusinessLayerException <BR>
	 * tag : BUSINESS_ERROR_00857 <BR>
	 * =============================================== <BR>
	 * <BR>
	 * =============================================== <BR>
	 * �V�[�P���X�} : �u�i�Ǘ��ҁjget�X�V������ʁv�Q�ƁB<BR>
	 * ��̈ʒu :���N�G�X�g�f�[�^.�X�V_�������==null &&<BR>
     * ���N�G�X�g�f�[�^.�X�V_�����L�����==null &&<BR>
     * ���N�G�X�g�f�[�^.�X�V_�����==null &&<BR>
     * ���N�G�X�g�f�[�^.�X�V_������==null�̏ꍇ�́A��O���X���[����B<BR>
	 * class : WEB3BusinessLayerException <BR>
	 * �@@tag : BUSINESS_ERROR_02519<BR>
	 * =============================================== <BR>
	 * 
	 * @@param l_request - (���N�G�X�g�f�[�^)<BR>
	 * �Ǘ��ҁE�����P�ʃe�[�u��������ԍX�V�������N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecOrderUnitTableUpdateCompleteResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 444E0B0F018A
	 */
	protected WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse getUpdateCompleteScreen(
		WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getUpdateCompleteScreen"
			+ "(WEB3AdminDirSecOrderUnitTableUpdateCompleteRequest)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate()
		l_request.validate();

		//validate�X�V_������(String)
		//�X�V_�������̃`�F�b�N���s���B
		//[validate�X�V_�������i�j�Ɏw�肷�����]
		//�X�V_�������F���N�G�X�g�f�[�^.�X�V_������
		this.validateUpdateOrderBizDate(l_request.updateOrderBizDate);

		// 1.2 getInstanceFrom���O�C�����( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
		// [validate�����i�j�Ɏw�肷�����]
		// �@@�\�J�e�S���R�[�h�F"Z0102" �i�V�X�e���Ǘ� �����P�ʃe�[�u��������ԍX�V�j
		// is�X�V�Ftrue
		l_admin.validateAuthority(WEB3TransactionCategoryDef.ORDER_UNIT_UPDATE, true);

		// 1.4 isDIR�Ǘ���( )
		boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

		// 1.5 DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A��O���X���[����B
		if (!l_blnIsDirAdmin)
		{
			log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00857, 
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
		}

		// 1.6 validate����p�X���[�h(�p�X���[�h : String)
		l_admin.validateTradingPassword(l_request.password);

		// 1.7 ���N�G�X�g�f�[�^.�X�V_�������==null && ���N�G�X�g�f�[�^.�X�V_�����L�����==null
        // && ���N�G�X�g�f�[�^.�X�V_�����==null && 
		//���N�G�X�g�f�[�^.�X�V_������==null�̏ꍇ�́A��O���X���[����B
		if (l_request.updateOrderStatus == null
			&& l_request.updateOrderOpenStatus == null
            && l_request.updateExecStatus == null
            && l_request.updateOrderBizDate == null)
		{
            log.debug("�S�Ă̕ύX���ڂ����w��ł��B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02519, 
				this.getClass().getName() + STR_METHOD_NAME,
				"�S�Ă̕ύX���ڂ����w��ł��B");
		}

        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 0(�O��)�̏ꍇ�A�ȉ��̏������s���B
        //����t���[
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 0(�O��)�̏ꍇ�A
        //�ȉ��̏������s���B
		//update�O�������P�ʃe�[�u�����R�[�h(long, String)
        //�O�������P�ʃe�[�u���̃��R�[�h�X�V�����������Ȃ��B
        //[update�O�������P�ʃe�[�u�����R�[�h(�j�Ɏw�肷�����]
        //�����P��ID�@@�F�@@���N�G�X�g�f�[�^.�����P��ID
        //�X�V_������� �F ���N�G�X�g�f�[�^.�X�V_�������
        if (WEB3AdminDirOrderUnitTblKbnDef.FEQ.equals(l_request.orderUnitTblKbn))
        {
            this.updateFeqOrderUnitTableRecord(
                Long.parseLong(l_request.orderUnitId),
                l_request.updateOrderStatus);
        }
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 1(���o��)�̏ꍇ�A�ȉ��̏������s���B
        //����t���[
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 1(���o��)�̏ꍇ�A
        //�ȉ��̏������s���B
		//update�����P�ʃe�[�u�����R�[�h(long, String, String)
		//[update�����P�ʃe�[�u�����R�[�h(�j�Ɏw�肷�����]
		//�����P��ID �F ���N�G�X�g�f�[�^.�����P��ID
		//�X�V_������� �F ���N�G�X�g�f�[�^.�X�V_�������
		//�X�V_�����L����� �F ���N�G�X�g�f�[�^.�X�V_�����L�����
        else if (WEB3AdminDirOrderUnitTblKbnDef.AIO.equals(l_request.orderUnitTblKbn))
        {
    		this.updateOrderUnitTableRecord(Long.parseLong(l_request.orderUnitId),
    			l_request.updateOrderStatus,
    			l_request.updateOrderOpenStatus);
        }

        //���N�G�X�g�f�[�^.���M�����P�ʃe�[�u���敪 == 2(���M)�̏ꍇ�A�ȉ��̏������s���B
        //���N�G�X�g�f�[�^.���M�����P�ʃe�[�u���敪 == 2(���M)�̏ꍇ�A
        //�ȉ��̏������s���B
        else if (WEB3AdminDirOrderUnitTblKbnDef.MUTUAL.equals(l_request.orderUnitTblKbn))
        {
            //[update���M�����P�ʃe�[�u�����R�[�h(�j�Ɏw�肷�����]
            //�����P��ID�@@�F�@@���N�G�X�g�f�[�^.�����P��ID
            //�X�V_������� �F ���N�G�X�g�f�[�^.�X�V_�������
            //�X�V_�����L����� �F ���N�G�X�g�f�[�^.�X�V_�����L�����
            //�X�V_����� �F ���N�G�X�g�f�[�^.�X�V_�����
            this.updateMutualFundOrderUnit(Long.parseLong(l_request.orderUnitId),
                l_request.updateOrderStatus,
                l_request.updateOrderOpenStatus,
                l_request.updateExecStatus);
        }

        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 3(����)�̏ꍇ�A�ȉ��̏������s���B
        //����t���[
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 3(����)�̏ꍇ�A
        //�ȉ��̏������s���B
        else if (WEB3AdminDirOrderUnitTblKbnDef.EQ.equals(l_request.orderUnitTblKbn))
        {
        	//[update�����P�ʃe�[�u�����R�[�h(�j�Ɏw�肷�����]
        	//�����P��ID�@@�F�@@���N�G�X�g�f�[�^.�����P��ID
        	//�X�V_������� �F ���N�G�X�g�f�[�^.�X�V_�������
        	//�X�V_�����L����� �F ���N�G�X�g�f�[�^.�X�V_�����L�����
        	//�X�V_������ �F ���N�G�X�g�f�[�^.�X�V_������
            this.updateEqtypeOrderUnit(Long.parseLong(l_request.orderUnitId),
                l_request.updateOrderStatus,
                l_request.updateOrderOpenStatus,
                l_request.updateOrderBizDate);
        }

        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 4(�敨OP)�̏ꍇ�A�ȉ��̏������s���B
        //����t���[
        //���N�G�X�g�f�[�^.�����P�ʃe�[�u���敪 == 4(�敨OP)�̏ꍇ�A
        //�ȉ��̏������s���B
        else if (WEB3AdminDirOrderUnitTblKbnDef.IFO.equals(l_request.orderUnitTblKbn))
        {
        	//[update�����P�ʃe�[�u�����R�[�h(�j�Ɏw�肷�����]
        	//�����P��ID�@@�F�@@���N�G�X�g�f�[�^.�����P��ID
        	//�X�V_������� �F ���N�G�X�g�f�[�^.�X�V_�������
        	//�X�V_�����L����� �F ���N�G�X�g�f�[�^.�X�V_�����L�����
        	//�X�V_������ �F ���N�G�X�g�f�[�^.�X�V_������
            this.updateIfoOrderUnit(Long.parseLong(l_request.orderUnitId),
                l_request.updateOrderStatus,
                l_request.updateOrderOpenStatus,
                l_request.updateOrderBizDate);
        }

		// 1.9 createResponse()
		WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse l_response =
			(WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse) l_request.createResponse();

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get�����P�ʃe�[�u�����R�[�h)<BR>
	 * �����P�ʃe�[�u�����猟�������f�[�^�����Ƀ��R�[�h����������B<BR>
	 * <BR>
	 * �����P�ʃe�[�u������A�i�����j�����P��ID���L�[�Ƃ��Č������s���A<BR>
	 * �擾����Row�I�u�W�F�N�g��ԋp����B<BR>
	 * <BR>
	 * ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
	 * �G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
	 * �iBUSINESS_ERROR_01037�j<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_01037<BR>
	 * 
	 * @@param l_lngOrderUnitId - (�����P��ID)<BR>
	 * �����P��ID�B<BR>
	 * @@return AioOrderUnitRow
	 * @@throws WEB3BaseException
	 * @@roseuid 444F40E101CE
	 */
	private AioOrderUnitRow getOrderUnitTableRecord(long l_lngOrderUnitId) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getOrderUnitTableRecord" + "(long l_lngOrderUnitId)";
		log.entering(STR_METHOD_NAME);

		AioOrderUnitRow l_aioOrderUnitRow = null;
		try
		{	
			l_aioOrderUnitRow = (AioOrderUnitRow) AioOrderUnitDao.findRowByOrderUnitId(l_lngOrderUnitId);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + STR_METHOD_NAME, 
				l_ex.getMessage(), l_ex);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
				this.getClass().getName() + STR_METHOD_NAME, 
				l_ex.getMessage(), l_ex);
		}
		if (l_aioOrderUnitRow == null)
		{
			log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�����ɊY������f�[�^�����݂��Ȃ��B");	
		}

		log.exiting(STR_METHOD_NAME);
		return l_aioOrderUnitRow;
	}

	/**
	 * (update�����P�ʃe�[�u�����R�[�h)<BR>
	 * �����P�ʃe�[�u���̍X�V�����������Ȃ��B<BR>
	 * <BR>
	 * �i�����j�����P��ID���L�[�Ƃ��Ĉȉ����e�Œ����P�ʃe�[�u����UPDATE���s���B<BR>
	 * <BR>
	 * �P�j �i�����j�X�V_������� != null�̏ꍇ�A<BR>
	 * �E������ԁF�i�����j�X�V_�������<BR>
	 * �� �i�����j�X�V_������� == null�̏ꍇ�A<BR>
	 * �@@�@@������ԁF�X�V�s�v�i�����l�j<BR>
	 * <BR>
	 * �Q�j �i�����j�X�V_�����L����� != null�̏ꍇ�A<BR>
	 * �E�����L����ԁF�i�����j�X�V_�����L�����<BR>
	 * �� �i�����j�X�V_�����L����� == null�̏ꍇ�A<BR>
	 *     �����L����ԁF�X�V�s�v�i�����l�j<BR>
	 * <BR>
	 * @@param l_lngOrderUnitId - (�����P��ID)<BR>
	 * �����P��ID�B<BR>
	 * @@param l_strUpdateOrderStatus - (�X�V_�������)<BR>
	 * �X�V_������ԁB<BR>
	 * @@param l_strUpdateOrderOpenStatus - (�X�V_�����L�����)<BR>
	 * �X�V_�����L����ԁB<BR>
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	private void updateOrderUnitTableRecord(long l_lngOrderUnitId, String l_strUpdateOrderStatus,
		String l_strUpdateOrderOpenStatus) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " updateOrderUnitTableRecord"
			+ "(l_lngOrderUnitId, l_strUpdateOrderStatus,"
			+ "l_strUpdateOrderOpenStatus)";
		log.entering(STR_METHOD_NAME);
			
        //������Ԃ����ɐ�������Map�I�u�W�F�N�g 
        Map l_mapStatus = new HashMap();
        
        if (l_strUpdateOrderStatus != null)
        {
            l_mapStatus.put("order_status", l_strUpdateOrderStatus);
        }
 
        if (l_strUpdateOrderOpenStatus != null)
        {
        	l_mapStatus.put("order_open_status", l_strUpdateOrderOpenStatus);
        }
        
		try
		{   
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			
			//�X���b�h�ԍ������ɐ�������PrimaryKey�I�u�W�F�N�g 
			AioOrderUnitPK l_aioOrderUnitPK = new AioOrderUnitPK(l_lngOrderUnitId);
			l_queryProcessor.doUpdateQuery(l_aioOrderUnitPK, l_mapStatus);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + STR_METHOD_NAME, 
				l_ex.getMessage(), l_ex);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + STR_METHOD_NAME, 
				l_ex.getMessage(), l_ex);
		}

		log.exiting(STR_METHOD_NAME);
	}

    /**
     * (get�O�������P�ʃe�[�u�����R�[�h)<BR>
     * �O�������P�ʃe�[�u�����猟�������f�[�^�����Ƀ��R�[�h����������B<BR>
     * <BR>
     * �O�������P�ʃe�[�u������A�i�����j�����P��ID���L�[�Ƃ��Č������s���A<BR>
     * �擾����Row�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
     * �G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     * �iBUSINESS_ERROR_01037�j<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_01037<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID�B<BR>
     * @@return FeqOrderUnitRow
     * @@throws WEB3BaseException
     * @@roseuid 444F40E101CE
     */
    private FeqOrderUnitRow getFeqOrderUnitTableRecord(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnitTableRecord" + "(long l_lngOrderUnitId)";
        log.entering(STR_METHOD_NAME);

        FeqOrderUnitRow l_feqOrderUnitRow = null;
        try
        {
            l_feqOrderUnitRow = FeqOrderUnitDao.findRowByOrderUnitId(l_lngOrderUnitId);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        if (l_feqOrderUnitRow == null)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitRow;
    }

    /**
     * (update�O�������P�ʃe�[�u�����R�[�h)<BR>
     * �O�������P�ʃe�[�u���̍X�V�����������Ȃ��B<BR>
     * <BR>
     * �i�����j�����P��ID���L�[�Ƃ��Ĉȉ����e�ŊO�������P�ʃe�[�u����UPDATE���s���B<BR>
     * <BR>
     * �P�j �i�����j�X�V_������� != null�̏ꍇ�A<BR>
     * �E������ԁF�i�����j�X�V_�������<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID�B<BR>
     * @@param l_strUpdateOrderStatus - (�X�V_�������)<BR>
     * �X�V_������ԁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4451C7E80111
     */
    private void updateFeqOrderUnitTableRecord(long l_lngOrderUnitId,
        String l_strUpdateOrderStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateFeqOrderUnitTableRecord"
            + "(long, String)";
        log.entering(STR_METHOD_NAME);

        Map l_mapStatus = new HashMap();

        if (l_strUpdateOrderStatus != null)
        {
            l_mapStatus.put("order_status", l_strUpdateOrderStatus);

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                FeqOrderUnitPK l_feqOrderUnitPK = new FeqOrderUnitPK(l_lngOrderUnitId);
                l_queryProcessor.doUpdateQuery(l_feqOrderUnitPK, l_mapStatus);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get���M�����P�ʃe�[�u�����R�[�h (long �����P��ID))<BR>
     * ���M�����P�ʃe�[�u�����猟�������f�[�^�����Ƀ��R�[�h����������B<BR>
     * <BR>
     * ���M�����P�ʃe�[�u������A�i�����j�����P��ID���L�[�Ƃ��Č������s���A<BR>
     * �擾����Row�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
     * �G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     * �iBUSINESS_ERROR_01037�j�j<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_01037<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID<BR>
     * @@return MutualFundOrderUnitRow
     * @@throws WEB3BaseException
     */
    private MutualFundOrderUnitRow getMutualFundOrderUnitTableRecord(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMutualFundOrderUnitTableRecord(long)";
        log.entering(STR_METHOD_NAME);

        MutualFundOrderUnitRow l_mutualFundOrderUnitRow = null;
        try
        {
            // ���M�����P�ʃe�[�u������A�i�����j�����P��ID���L�[�Ƃ��Č������s���A<BR>
            // �擾����Row�I�u�W�F�N�g��ԋp����B
            l_mutualFundOrderUnitRow =
                MutualFundOrderUnitDao.findRowByOrderUnitId(l_lngOrderUnitId);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        // ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B
        // �G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v
        // �iBUSINESS_ERROR_01037�j�j
        if (l_mutualFundOrderUnitRow == null)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderUnitRow;
    }

    /**
     * (update���M�����P�ʃe�[�u�����R�[�h)<BR>
     * ���M�����P�ʃe�[�u���̍X�V�����������Ȃ��B<BR>
     * <BR>
     * �i�����j�����P��ID���L�[�Ƃ��Ĉȉ����e�œ��M�����P�ʃe�[�u����UPDATE���s���B<BR>
     * <BR>
     * �P�j �i�����j�X�V_������� != null�̏ꍇ�A<BR>
     * �E������ԁF�i�����j�X�V_�������<BR>
     * �� �i�����j�X�V_������� == null�̏ꍇ�A<BR>
     * �@@�@@������ԁF�X�V�s�v�i�����l�j<BR>
     * <BR>
     * �Q�j �i�����j�X�V_�����L����� != null�̏ꍇ�A<BR>
     * �E�����L����ԁF�i�����j�X�V_�����L�����<BR>
     * �� �i�����j�X�V_�����L����� == null�̏ꍇ�A<BR>
     * �@@�@@�����L����ԁF�X�V�s�v�i�����l�j<BR>
     * <BR>
     * �R�j �i�����j�X�V_����� != null�̏ꍇ�A<BR>
     * �E����ԁF�i�����j�X�V_�����<BR>
     * �� �i�����j�X�V_����� == null�̏ꍇ�A<BR>
     * �@@�@@����ԁF�X�V�s�v�i�����l�j<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID<BR>
     * @@param l_strUpdateOrderStatus - (�X�V_�������)<BR>
     * �X�V_������ԁB<BR>
     * @@param l_strOrderOpenStatus - (�X�V_�����L�����)<BR>
     * �X�V_�����L�����<BR>
     * @@param l_strExecStatus - (�X�V_�����)<BR>
     * �X�V_�����<BR>
     * @@throws WEB3BaseException
     */
    private void updateMutualFundOrderUnit(long l_lngOrderUnitId,
        String l_strUpdateOrderStatus,
        String l_strOrderOpenStatus,
        String l_strExecStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateMutualFundOrderUnit"
            + "(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�i�����j�����P��ID���L�[�Ƃ��Ĉȉ����e�œ��M�����P�ʃe�[�u����UPDATE���s���B
        Map l_mapStatus = new HashMap();

        //�P�j �i�����j�X�V_������� != null�̏ꍇ�A
        //�E������ԁF�i�����j�X�V_�������
        //�� �i�����j�X�V_������� == null�̏ꍇ�A
        //������ԁF�X�V�s�v�i�����l�j
        if (l_strUpdateOrderStatus != null)
        {
            l_mapStatus.put("order_status", l_strUpdateOrderStatus);
        }

        //�Q�j �i�����j�X�V_�����L����� != null�̏ꍇ�A
        //�E�����L����ԁF�i�����j�X�V_�����L�����
        //�� �i�����j�X�V_�����L����� == null�̏ꍇ�A
        //�����L����ԁF�X�V�s�v�i�����l�j
        if (l_strOrderOpenStatus != null)
        {
            l_mapStatus.put("order_open_status", l_strOrderOpenStatus);
        }

        //�R�j �i�����j�X�V_����� != null�̏ꍇ�A
        //�E����ԁF�i�����j�X�V_�����
        //�� �i�����j�X�V_����� == null�̏ꍇ�A
        //����ԁF�X�V�s�v�i�����l�j
        if (l_strExecStatus != null)
        {
            l_mapStatus.put("exec_status", l_strExecStatus);
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            MutualFundOrderUnitPK l_mutualFundOrderUnitPK =
                new MutualFundOrderUnitPK(l_lngOrderUnitId);
            l_queryProcessor.doUpdateQuery(l_mutualFundOrderUnitPK, l_mapStatus);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get���������P�ʃe�[�u�����R�[�h)<BR>
     * ���������P�ʃe�[�u�����猟�������f�[�^�����Ƀ��R�[�h����������B<BR>
     * <BR>
     * ���������P�ʃe�[�u������A�i�����j�����P��ID���L�[�Ƃ��Č������s���A<BR>
     * �擾����Row�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
     * �G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     * �iBUSINESS_ERROR_01037�j<BR>
     * class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01037<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID�B<BR>
     * @@return EqtypeOrderUnitRow
     * @@throws WEB3BaseException
     */
    private EqtypeOrderUnitRow getEqtypeOrderUnitTableRecord(long l_lngOrderUnitId)
        throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getEqtypeOrderUnitTableRecord(long)";
       log.entering(STR_METHOD_NAME);

       EqtypeOrderUnitRow l_eqtypeOrderUnitRow = null;
       try
       {
    	   l_eqtypeOrderUnitRow = EqtypeOrderUnitDao.findRowByOrderUnitId(l_lngOrderUnitId);
       }
       catch (DataQueryException l_ex)
       {
           log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
       catch (DataNetworkException l_ex)
       {
           log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
       if (l_eqtypeOrderUnitRow == null)
       {
           log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_01037,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�����ɊY������f�[�^�����݂��Ȃ��B");
       }

       log.exiting(STR_METHOD_NAME);
       return l_eqtypeOrderUnitRow;
   }

    /**
     * (update���������P�ʃe�[�u�����R�[�h)<BR>
     * ���������P�ʃe�[�u���̍X�V�����������Ȃ��B<BR>
     * <BR>
     * �i�����j�����P��ID���L�[�Ƃ��Ĉȉ����e�Ŋ��������P�ʃe�[�u����UPDATE���s���B<BR>
     * <BR>
     * �P�j �i�����j�X�V_������� != null�̏ꍇ�A<BR>
     * �E������ԁF�i�����j�X�V_�������<BR>
     * �� �i�����j�X�V_������� == null�̏ꍇ�A<BR>
     * �@@�@@������ԁF�X�V�s�v�i�����l�j<BR>
     * <BR>
     * �Q�j �i�����j�X�V_�����L����� != null�̏ꍇ�A<BR>
     * �E�����L����ԁF�i�����j�X�V_�����L�����<BR>
     * �� �i�����j�X�V_�����L����� == null�̏ꍇ�A<BR>
     * �@@�@@�����L����ԁF�X�V�s�v�i�����l�j<BR>
     * <BR>
     * �R�j �i�����j�X�V_������ != null�̏ꍇ�A<BR>
     * �E�������F�i�����j�X�V_������<BR>
     * �� �i�����j�X�V_������ == null�̏ꍇ�A<BR>
     * �@@�@@�������F�X�V�s�v�i�����l�j<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID<BR>
     * @@param l_strUpdateOrderStatus - (�X�V_�������)<BR>
     * �X�V_������ԁB<BR>
     * @@param l_strOrderOpenStatus - (�X�V_�����L�����)<BR>
     * �X�V_�����L�����<BR>
     * @@param l_strUpdateOrderBizDate - (�X�V_������)<BR>
     * �X�V_������<BR>
     * @@throws WEB3BaseException
     */
    private void updateEqtypeOrderUnit(
		long l_lngOrderUnitId,
        String l_strUpdateOrderStatus,
        String l_strOrderOpenStatus,
        String l_strUpdateOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateEqtypeOrderUnit"
            + "(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�i�����j�����P��ID���L�[�Ƃ��Ĉȉ����e�Ŋ��������P�ʃe�[�u����UPDATE���s���B
        Map l_mapStatus = new HashMap();

        //�P�j �i�����j�X�V_������� != null�̏ꍇ�A
        //�E������ԁF�i�����j�X�V_�������
        //�� �i�����j�X�V_������� == null�̏ꍇ�A
        //������ԁF�X�V�s�v�i�����l�j
        if (l_strUpdateOrderStatus != null)
        {
            l_mapStatus.put("order_status", l_strUpdateOrderStatus);
        }

        //�Q�j �i�����j�X�V_�����L����� != null�̏ꍇ�A
        //�E�����L����ԁF�i�����j�X�V_�����L�����
        //�� �i�����j�X�V_�����L����� == null�̏ꍇ�A
        //�����L����ԁF�X�V�s�v�i�����l�j
        if (l_strOrderOpenStatus != null)
        {
            l_mapStatus.put("order_open_status", l_strOrderOpenStatus);
        }

        //�R�j �i�����j�X�V_������ != null�̏ꍇ�A
        //�E�������F�i�����j�X�V_�����
        //�� �i�����j�X�V_������ == null�̏ꍇ�A
        //�������F�X�V�s�v�i�����l�j
        if (l_strUpdateOrderBizDate != null)
        {
            l_mapStatus.put("biz_date", l_strUpdateOrderBizDate);
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            EqtypeOrderUnitPK l_eqtypeOrderUnitPK =
                new EqtypeOrderUnitPK(l_lngOrderUnitId);
            l_queryProcessor.doUpdateQuery(l_eqtypeOrderUnitPK, l_mapStatus);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�敨OP�����P�ʃe�[�u�����R�[�h)<BR>
     * �敨OP�����P�ʃe�[�u�����猟�������f�[�^�����Ƀ��R�[�h����������B<BR>
     * <BR>
     * �敨OP�����P�ʃe�[�u������A�i�����j�����P��ID���L�[�Ƃ��Č������s���A<BR>
     * �擾����Row�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
     * �G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     * �iBUSINESS_ERROR_01037�j<BR>
     * class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01037<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID�B<BR>
     * @@return IfoOrderUnitRow
     * @@throws WEB3BaseException
     */
    private IfoOrderUnitRow getIfoOrderUnitTableRecord(long l_lngOrderUnitId)
        throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getIfoOrderUnitTableRecord(long)";
       log.entering(STR_METHOD_NAME);

       IfoOrderUnitRow l_ifoOrderUnitRow = null;
       try
       {
    	   l_ifoOrderUnitRow = IfoOrderUnitDao.findRowByOrderUnitId(l_lngOrderUnitId);
       }
       catch (DataQueryException l_ex)
       {
           log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
       catch (DataNetworkException l_ex)
       {
           log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
       if (l_ifoOrderUnitRow == null)
       {
           log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_01037,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�����ɊY������f�[�^�����݂��Ȃ��B");
       }

       log.exiting(STR_METHOD_NAME);
       return l_ifoOrderUnitRow;
   }

    /**
     * (update�敨OP�����P�ʃe�[�u�����R�[�h)<BR>
     * �敨OP�����P�ʃe�[�u���̍X�V�����������Ȃ��B<BR>
     * <BR>
     * �i�����j�����P��ID���L�[�Ƃ��Ĉȉ����e�Ŋ��������P�ʃe�[�u����UPDATE���s���B<BR>
     * <BR>
     * �P�j �i�����j�X�V_������� != null�̏ꍇ�A<BR>
     * �E������ԁF�i�����j�X�V_�������<BR>
     * �� �i�����j�X�V_������� == null�̏ꍇ�A<BR>
     * �@@�@@������ԁF�X�V�s�v�i�����l�j<BR>
     * <BR>
     * �Q�j �i�����j�X�V_�����L����� != null�̏ꍇ�A<BR>
     * �E�����L����ԁF�i�����j�X�V_�����L�����<BR>
     * �� �i�����j�X�V_�����L����� == null�̏ꍇ�A<BR>
     * �@@�@@�����L����ԁF�X�V�s�v�i�����l�j<BR>
     * <BR>
     * �R�j �i�����j�X�V_������ != null�̏ꍇ�A<BR>
     * �E�������F�i�����j�X�V_������<BR>
     * �� �i�����j�X�V_������ == null�̏ꍇ�A<BR>
     * �@@�@@�������F�X�V�s�v�i�����l�j<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID<BR>
     * @@param l_strUpdateOrderStatus - (�X�V_�������)<BR>
     * �X�V_������ԁB<BR>
     * @@param l_strOrderOpenStatus - (�X�V_�����L�����)<BR>
     * �X�V_�����L�����<BR>
     * @@param l_strUpdateOrderBizDate - (�X�V_������)<BR>
     * �X�V_������<BR>
     * @@throws WEB3BaseException
     */
    private void updateIfoOrderUnit(
		long l_lngOrderUnitId,
        String l_strUpdateOrderStatus,
        String l_strOrderOpenStatus,
        String l_strUpdateOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateIfoOrderUnit"
            + "(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�i�����j�����P��ID���L�[�Ƃ��Ĉȉ����e�Ŋ��������P�ʃe�[�u����UPDATE���s���B
        Map l_mapStatus = new HashMap();

        //�P�j �i�����j�X�V_������� != null�̏ꍇ�A
        //�E������ԁF�i�����j�X�V_�������
        //�� �i�����j�X�V_������� == null�̏ꍇ�A
        //������ԁF�X�V�s�v�i�����l�j
        if (l_strUpdateOrderStatus != null)
        {
            l_mapStatus.put("order_status", l_strUpdateOrderStatus);
        }

        //�Q�j �i�����j�X�V_�����L����� != null�̏ꍇ�A
        //�E�����L����ԁF�i�����j�X�V_�����L�����
        //�� �i�����j�X�V_�����L����� == null�̏ꍇ�A
        //�����L����ԁF�X�V�s�v�i�����l�j
        if (l_strOrderOpenStatus != null)
        {
            l_mapStatus.put("order_open_status", l_strOrderOpenStatus);
        }

        //�R�j �i�����j�X�V_������ != null�̏ꍇ�A
        //�E�������F�i�����j�X�V_�����
        //�� �i�����j�X�V_������ == null�̏ꍇ�A
        //�������F�X�V�s�v�i�����l�j
        if (l_strUpdateOrderBizDate != null)
        {
            l_mapStatus.put("biz_date", l_strUpdateOrderBizDate);
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            IfoOrderUnitPK l_ifoOrderUnitPK =
                new IfoOrderUnitPK(l_lngOrderUnitId);
            l_queryProcessor.doUpdateQuery(l_ifoOrderUnitPK, l_mapStatus);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�X�V_������)<BR>
     * �P�j�@@WEB3StringTypeUtility.isEmpty(�X�V_������)  == false�@@�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�|�P�jWEB3DateUtility.getDate(�X�V_������, "yyyyMMdd") == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�����������������t�ł͂���܂���B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_03109<BR>
     * �@@�P�|�Q�j�X�V_���������c�Ɠ��ȊO�̓��t�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�������͉c�Ɠ��ł͂���܂���B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_02019�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02019<BR>
     * @@param l_strUpdateOrderBizDate - (�X�V_������)<BR>
     * �X�V_������<BR>
     * @@throws WEB3BaseException
     */
    private void validateUpdateOrderBizDate(String l_strUpdateOrderBizDate) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validateUpdateOrderBizDate(String)";
        log.entering(STR_METHOD_NAME);

        //WEB3StringTypeUtility.isEmpty(�X�V_������)  == false�@@�̏ꍇ�A
        //�ȉ��̃`�F�b�N���s���B
        if (!WEB3StringTypeUtility.isEmpty(l_strUpdateOrderBizDate))
        {
        	//WEB3DateUtility.getDate(�X�V_������, "yyyyMMdd") == null �̏ꍇ�A
            //�u�����������������t�ł͂���܂���B�v�̗�O���X���[����B
            if (WEB3DateUtility.getDate(
        		l_strUpdateOrderBizDate,
        		WEB3GentradeTimeDef.DATE_FORMAT_YMD) == null)
            {
                log.debug("�����������������t�ł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03109,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����������������t�ł͂���܂���B");
            }

            //�X�V_���������c�Ɠ��ȊO�̓��t�ł������ꍇ�A
            //�u�������͉c�Ɠ��ł͂���܂���B�v�̗�O���X���[����B�iBUSINESS_ERROR_02019�j
            String l_strFlag = WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(WEB3DateUtility.getDate(
            		l_strUpdateOrderBizDate,
            		WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime()));
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFlag))
            {
            	log.debug("�������͉c�Ɠ��ł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02019,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������͉c�Ɠ��ł͂���܂���B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
