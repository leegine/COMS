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
filename	WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�XImpl(WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/11  ����� (���u) �V�K�쐬
 */

package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUnit;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecUploadTableEndDateUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�XImpl)<BR>
 * �Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�XImpl�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl implements
	WEB3AdminDirSecUploadTableEndDateUpdateService
{
	/**
	 * ���O�o�̓��[�e�B���e�B�B
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(
		WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl.class);

	/**
	 * �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������J�n����B<BR>
	 * <BR>
	 * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
	 * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
	 * <BR>
	 * ���Ǘ��ҁE�A�b�v���[�h�e�[�u�����R�[�h�ꗗ���N�G�X�g�̏ꍇ<BR>
	 * this.get�ꗗ���()���R�[������B<BR>
	 * <BR>
	 * ���Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�m�F���N�G�X�g�̏ꍇ<BR>
	 * this.get�X�V�m�F���)���R�[������B<BR>
	 * <BR>
	 * ���Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������N�G�X�g�̏ꍇ<BR>
	 * this.get�X�V�������()���R�[������B<BR>
	 * 
	 * @@param l_request -
	 *            ���N�G�X�g�f�[�^�B<BR>
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

		// ���Ǘ��ҁE�A�b�v���[�h�e�[�u�����R�[�h�ꗗ���N�G�X�g�̏ꍇ<BR>
		// this.get�ꗗ���()���R�[������B
		if (l_request instanceof WEB3AdminDirSecUploadTableListRequest)
		{
			l_response = this.getListScreen(
				(WEB3AdminDirSecUploadTableListRequest) l_request);
		}

		// ���Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�m�F���N�G�X�g�̏ꍇ<BR>
		// this.get�X�V�m�F���)���R�[������B
		else if (l_request instanceof WEB3AdminDirSecUploadTableUpdateConfirmRequest)
		{
			l_response = this.getUpdateConfirmScreen(
				(WEB3AdminDirSecUploadTableUpdateConfirmRequest) l_request);
		}

		// ���Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������N�G�X�g�̏ꍇ<BR>
		// this.get�X�V�������()���R�[������B
		else if (l_request instanceof WEB3AdminDirSecUploadTableUpdateCompleteRequest)
		{
			l_response = this.getUpdateCompleteScreen(
				(WEB3AdminDirSecUploadTableUpdateCompleteRequest) l_request);
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
	 * (create�A�b�v���[�h�e�[�u�����R�[�h�ڍ�)<BR>
	 * �A�b�v���[�h�e�[�u�����R�[�hList���A�A�b�v���[�h�e�[�u�����R�[�h�ڍ׌^�z����쐬����B<BR>
	 * <BR>
	 * �P�j ArrayList�I�u�W�F�N�g�̐����B<BR>
	 * <BR>
	 * �Q�j ����:�A�b�v���[�h�e�[�u�����R�[�hList�̗v�f���ALoop�����������Ȃ��B<BR>
	 * <BR>
	 * �Q�|�P�j �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃N���X�̃I�u�W�F�N�g�𐶐�����B<BR>
	 * <BR>
	 * �Q�|�Q�j �Q�|�P�j�Ő��������I�u�W�F�N�g�Ɉȉ��̓��e���Z�b�g����B<BR>
	 * <BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�A�b�v���[�hID = <BR>
	 * �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�A�b�v���[�hID()<BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�،���ЃR�[�h = <BR>
	 * �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�،���ЃR�[�h() <BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.���X�R�[�h = <BR>
	 * �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get���X�R�[�h() <BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�����^�C�v = <BR>
	 * �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�����^�C�v() <BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�A�b�v���[�h�J�n���� =<BR>
	 * �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�A�b�v���[�h�J�n����() <BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�A�b�v���[�h�I������ = <BR>
	 * �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�A�b�v���[�h�I������() <BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.���b�Z�[�W�R�[�h = <BR>
	 * �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get���b�Z�[�W�R�[�h() <BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�A�b�v���[�h���� = <BR>
	 * �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�A�b�v���[�h����() <BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�X�V�҃R�[�h = <BR>
	 * �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�X�V�҃R�[�h() <BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�f�[�^�L�[ = <BR>
	 * �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�f�[�^�L�[() <BR>
	 * <BR>
	 * �Q�|�R�j �P�j�Ő�������ArrayList�I�u�W�F�N�g�ɃA�b�v���[�h�e�[�u�����R�[�h�ڍ�<BR>
	 * �I�u�W�F�N�g��add()����B<BR>
	 * <BR>
	 * �R�j �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g��<BR>
	 * ArrayList�I�u�W�F�N�g�̃T�C�Y�Ő�������B<BR>
	 * <BR>
	 * �S�j toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B<BR>
	 * <BR>
	 * ArrayList�I�u�W�F�N�g.toArray(<BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g);<BR>
	 * <BR>
	 * �T�j �ϊ������z��I�u�W�F�N�g��ԋp����B<BR>
	 * 
	 * @@param l_lisUploadTableRecordList -
	 *            (�A�b�v���[�h�e�[�u�����R�[�hList)<BR>
	 *            �A�b�v���[�h�e�[�u�����R�[�hList�B<BR>
	 * @@return WEB3AdminDirSecUploadTableRecordDetail[]
	 * @@roseuid 4451C7E80111
	 */
	private WEB3AdminDirSecUploadTableUnit[] createUploadTableRecordDetail(
		List l_lisUploadTableRecordList)
	{
		// �P�j ArrayList�I�u�W�F�N�g�̐����B
		List l_lisArrayList = new ArrayList();

		// �Q�j ����:�A�b�v���[�h�e�[�u�����R�[�hList�̗v�f���ALoop�����������Ȃ��B
		int l_intUploadTableRecordList = 0;
		if (l_lisUploadTableRecordList != null)
		{
			l_intUploadTableRecordList = l_lisUploadTableRecordList.size();
		}
		for (int i = 0; i < l_intUploadTableRecordList; i++)
		{
			AdministratorUploadRow l_row = (AdministratorUploadRow) l_lisUploadTableRecordList.get(i);
			// �Q�|�P�j �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃N���X�̃I�u�W�F�N�g�𐶐�����B
			WEB3AdminDirSecUploadTableUnit l_dirSecUploadTableUnit = new WEB3AdminDirSecUploadTableUnit();
			// �Q�|�Q�j �Q�|�P�j�Ő��������I�u�W�F�N�g�Ɉȉ��̓��e���Z�b�g����B
			// �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�A�b�v���[�hID =
			// �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�A�b�v���[�hID()
			l_dirSecUploadTableUnit.administratorUploadId = 
				l_row.getAdministratorUploadId() + "";

			// �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�،���ЃR�[�h =
			// �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�،���ЃR�[�h()
			l_dirSecUploadTableUnit.institutionCode = 
				l_row.getInstitutionCode();

			// �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.���X�R�[�h =
			// �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get���X�R�[�h()
			l_dirSecUploadTableUnit.branchCode = l_row.getBranchCode();

			// �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�����^�C�v =
			// �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�����^�C�v()
			l_dirSecUploadTableUnit.productType = 
				l_row.getProductType().intValue() + "";

			// �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�A�b�v���[�h�J�n���� =
			// �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�A�b�v���[�h�J�n����()
            l_dirSecUploadTableUnit.uploadStartTimestamp = 
                WEB3DateUtility.formatDate(l_row.getUploadStartTimestamp(), "yyyyMMddHHmmss");  

			// �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�A�b�v���[�h�I������ =
			// �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�A�b�v���[�h�I������()
            if (l_row.getUploadEndTimestamp() != null)
            {
                l_dirSecUploadTableUnit.uploadEndTimestamp = 
                    WEB3DateUtility.formatDate(l_row.getUploadEndTimestamp(), "yyyyMMddHHmmss"); 
            }

			// �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.���b�Z�[�W�R�[�h =
			// �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get���b�Z�[�W�R�[�h()
			l_dirSecUploadTableUnit.messageCode = l_row.getMessageCode();

			// �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�A�b�v���[�h���� =
			// �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�A�b�v���[�h����()
			l_dirSecUploadTableUnit.uploadCount = l_row.getUploadCount() + "";

			// �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�X�V�҃R�[�h =
			// �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�X�V�҃R�[�h()
			l_dirSecUploadTableUnit.lastUpdater = l_row.getLastUpdater();

			// �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�f�[�^�L�[ =
			// �i�����j�A�b�v���[�h�e�[�u�����R�[�hList.get(index).get�f�[�^�L�[()
			l_dirSecUploadTableUnit.uploadKey = l_row.getUploadKey() + "";

			// �Q�|�R�j �P�j�Ő�������ArrayList�I�u�W�F�N�g��
			// �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g��add()����B
			l_lisArrayList.add(l_dirSecUploadTableUnit);
		}

		// �R�j �A�b�v���[�h�e�[�u�����R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g��
		// ArrayList�I�u�W�F�N�g�̃T�C�Y�Ő�������B
		WEB3AdminDirSecUploadTableUnit[] l_dirSecUploadTableUnits = 
			new WEB3AdminDirSecUploadTableUnit[l_lisArrayList.size()];

		// �S�j toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B
		// ArrayList�I�u�W�F�N�g.toArray(�A�b�v���[�h�e�[�u�����R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g);
		l_lisArrayList.toArray(l_dirSecUploadTableUnits);

		// �T�j �ϊ������z��I�u�W�F�N�g��ԋp����B
		return l_dirSecUploadTableUnits;
	}

	/**
	 * (get�A�b�v���[�h�e�[�u�����R�[�h)<BR>
	 * �A�b�v���[�h�e�[�u������A�A�b�v���[�h�I��������null�̃��R�[�h���������A<BR>
	 * �擾�������R�[�h��List�ŕԋp����B<BR>
	 * <BR>
	 * �P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
	 * <BR>
	 * [doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
	 * arg0�F �A�b�v���[�h�e�[�u��RowType <BR>
	 * arg1�F "upload_end_timestamp is null"<BR>
	 * arg2�F "administrator_upload_id" <BR>
	 * arg3�F null <BR>
	 * arg4�F null <BR>
	 * <BR>
	 * <BR>
	 * ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B <BR>
	 * �G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v�iBUSINESS_ERROR_01037�j�j<BR>
	 * 
     * @@param l_institutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
	 * @@return List
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	private List getUploadTableRecord(String l_institutionCode) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getUploadTableRecord(String)";
		log.entering(STR_METHOD_NAME);

		List l_lisAdministratorUploadRow = null;

		String[] l_strInstitutionCode = {l_institutionCode};
		try
		{
			// �P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisAdministratorUploadRow = l_queryProcessor.doFindAllQuery(
				AdministratorUploadRow.TYPE,
				"institution_code=? and upload_end_timestamp is null",
				"administrator_upload_id",
				null, 
				l_strInstitutionCode);
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

		// ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B
		if (l_lisAdministratorUploadRow == null
			|| l_lisAdministratorUploadRow.size() == 0)
		{
			log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�����ɊY������f�[�^�����݂��Ȃ��B");
		}

		log.exiting(STR_METHOD_NAME);
		return l_lisAdministratorUploadRow;
	}

	/**
	 * (get�A�b�v���[�h�e�[�u�����R�[�h)<BR>
	 * �A�b�v���[�h�e�[�u������A�i�����j�A�b�v���[�hID���L�[�Ƃ��Č������s���A<BR>
	 * �擾�������R�[�h��List�ŕԋp����B<BR>
	 * <BR>
	 * �P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
	 * <BR>
	 * [doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
	 * arg0�F �A�b�v���[�h�e�[�u��RowType <BR>
	 * arg1�F "administrator_upload_id in (?,?,?�c)"(��) <BR>
	 * arg2�F "administrator_upload_id" <BR>
	 * arg3�F null <BR>
	 * arg4�F �i�����j�A�b�v���[�hID<BR>
	 * <BR> �i�� ? �́i�����j�A�b�v���[�hID�v�f�����L�q�j <BR>
	 * 
	 * @@param l_strUploadID -
	 *            (�A�b�v���[�hID)<BR>
	 *            �X�V�ΏۃA�b�v���[�hID�̔z��B<BR>
	 * @@return List
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	private List getUploadTableRecord(String[] l_strUploadId)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getUploadTableRecord(String[])";
		log.entering(STR_METHOD_NAME);

		List l_lisAdministratorUploadRow = null;
		String l_strWhere = "administrator_upload_id in (?";
		int l_intUploadIdLength = l_strUploadId.length - 1;
		for (int i = 0; i < l_intUploadIdLength; i ++)
		{
			l_strWhere = l_strWhere + ",?";
		}
		l_strWhere = l_strWhere + ")";

		try
		{
			// �P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisAdministratorUploadRow = l_queryProcessor.doFindAllQuery(
				AdministratorUploadRow.TYPE, 
				l_strWhere,
				"administrator_upload_id", 
				null, 
				l_strUploadId);
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

		log.exiting(STR_METHOD_NAME);
		return l_lisAdministratorUploadRow;
	}

	/**
	 * (get�ꗗ���)<BR>
	 * �A�b�v���[�h�e�[�u�����R�[�h�ꗗ��ʕ\���������s���B <BR>
	 * <BR>
	 * �V�[�P���X�} <BR>
	 * �u�i�Ǘ��ҁjget�ꗗ��ʁv�Q�ƁB<BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i�Ǘ��ҁjget�L���[�e�[�u���ꗗ       <BR>
     *         ��̈ʒu    :  1.4 isDIR�Ǘ���( )  <BR>
     *         DIR�Ǘ��Ҍ����`�F�b�N �B<BR>
     *        �iisDIR�Ǘ���( ) == false �̏ꍇ�A<BR>
     *         [DIR�Ǘ��Ҍ����`�F�b�N�G���[�B]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
	 * 
	 * @@param l_request -
	 *            (�Ǘ��ҁE�A�b�v���[�h�e�[�u�����R�[�h�ꗗ���N�G�X�g)<BR>
	 *            �Ǘ��ҁE�A�b�v���[�h�e�[�u�����R�[�h�������ʃ��N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecUploadTableListResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	public WEB3AdminDirSecUploadTableListResponse getListScreen(
		WEB3AdminDirSecUploadTableListRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getListScreen(WEB3AdminDirSecUploadTableListRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// get�A�b�v���[�h�e�[�u�����R�[�h�i�j�Ŏ擾�����l
		List l_lisTemp = null;

		WEB3AdminDirSecUploadTableUnit[] l_unit = null;

		// 1.1 getInstanceFrom���O�C�����( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.2validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
		l_admin.validateAuthority(
			WEB3TransactionCategoryDef.UPLOAD_END_TIMESTAMP_UPDATE, true);

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

		// 1.5 get�A�b�v���[�h�e�[�u�����R�[�h
		l_lisTemp = this.getUploadTableRecord(l_admin.getInstitutionCode());

		// 1.6 create�A�b�v���[�h�e�[�u�����R�[�h�ڍ�
		l_unit = this.createUploadTableRecordDetail(l_lisTemp);

		// 1.7 createResponse( )
		WEB3AdminDirSecUploadTableListResponse l_response = 
			(WEB3AdminDirSecUploadTableListResponse) l_request.createResponse();

		l_response.uploadTables = l_unit;

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (get�X�V�m�F���)<BR>
	 * �A�b�v���[�h�e�[�u���I�������X�V�m�F��ʕ\���������s���B <BR>
	 * <BR>
	 * �V�[�P���X�} <BR>
	 * �u�i�Ǘ��ҁjget�X�V�m�F��ʁv�Q�ƁB<BR>
	 * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i�Ǘ��ҁjget�L���[�e�[�u���ꗗ       <BR>
     *         ��̈ʒu    :  1.5 isDIR�Ǘ���( )  <BR>
     *         DIR�Ǘ��Ҍ����`�F�b�N �B<BR>
     *        �iisDIR�Ǘ���( ) == false �̏ꍇ�A<BR>
     *         [DIR�Ǘ��Ҍ����`�F�b�N�G���[�B]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * 
	 * @@param l_request -
	 *            (�Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�m�F���N�G�X�g)<BR>
	 *            �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�m�F���N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecUploadTableEndDateUpdateConfirmResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	public WEB3AdminDirSecUploadTableUpdateConfirmResponse getUpdateConfirmScreen(
		WEB3AdminDirSecUploadTableUpdateConfirmRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getUpdateConfirmScreen(WEB3AdminDirSecUploadTableUpdateConfirmRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// 1.1 validate( )
		l_request.validate();

		// 1.2 getInstanceFrom���O�C�����( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
		l_admin.validateAuthority(
			WEB3TransactionCategoryDef.UPLOAD_END_TIMESTAMP_UPDATE, true);

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

		// 1.6 createResponse( )
		WEB3AdminDirSecUploadTableUpdateConfirmResponse l_response =
			(WEB3AdminDirSecUploadTableUpdateConfirmResponse) l_request.createResponse();
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

    /**
	 * (get�X�V�������)<BR>
	 * �A�b�v���[�h�e�[�u���I�������X�V������ʕ\���������s���B<BR>
	 * <BR>
	 * �V�[�P���X�} <BR>
	 * �u�i�Ǘ��ҁjget�X�V������ʁv�Q�ƁB<BR>
     * <BR>
     * =============================================== <BR>
     *         �V�[�P���X�} :  �i�Ǘ��ҁjget�L���[�e�[�u���ꗗ       <BR>
     *         ��̈ʒu    :  1.5 isDIR�Ǘ���( )  <BR>
     *         DIR�Ǘ��Ҍ����`�F�b�N �B<BR>
     *        �iisDIR�Ǘ���( ) == false �̏ꍇ�A<BR>
     *         [DIR�Ǘ��Ҍ����`�F�b�N�G���[�B]�Ƃ��ė�O���X���[����B�j<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
	 * 
	 * @@param l_request -
	 *            (�Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������N�G�X�g)<BR>
	 *            �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������N�G�X�g�N���X�B<BR>
	 * @@return WEB3AdminDirSecUploadTableEndDateUpdateCompleteResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	public WEB3AdminDirSecUploadTableUpdateCompleteResponse getUpdateCompleteScreen(
		WEB3AdminDirSecUploadTableUpdateCompleteRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getUpdateCompleteScreen(WEB3AdminDirSecUploadTableUpdateCompleteRequest l_request)";
		log.entering(STR_METHOD_NAME);

		// get�A�b�v���[�h�e�[�u�����R�[�h�i�j�Ŏ擾�����l
		List l_lisTemp = null;

		WEB3AdminDirSecUploadTableUnit[] l_unit = null;

		// 1.1 validate( )
		l_request.validate();

		// 1.2 getInstanceFrom���O�C�����( )
		WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

		// 1.3validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
		l_admin.validateAuthority(
			WEB3TransactionCategoryDef.UPLOAD_END_TIMESTAMP_UPDATE, true);

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

		// 1.7 update�A�b�v���[�h�e�[�u��
		this.updateUploadTable(l_request.administratorUploadId);

		// 1.8 get�A�b�v���[�h�e�[�u�����R�[�h
		l_lisTemp = this.getUploadTableRecord(l_request.administratorUploadId);

		// 1.9 create�A�b�v���[�h�e�[�u�����R�[�h�ڍ�
		l_unit = this.createUploadTableRecordDetail(l_lisTemp);

		// 1.10 createResponse( )
		WEB3AdminDirSecUploadTableUpdateCompleteResponse l_response = 
			(WEB3AdminDirSecUploadTableUpdateCompleteResponse) l_request.createResponse();

		l_response.uploadTables = l_unit;

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * (update�A�b�v���[�h�e�[�u�� )<BR>
	 * �w�肵���A�b�v���[�h�e�[�u���̍X�V�����������Ȃ��B(DB�X�V�d�l�Q��) <BR>
	 * <BR>
	 * <BR>
	 * �P�j �X�V���e���u�R������-�l�v�̃y�A�Ŏ���Map�I�u�W�F�N�g�𐶐�����B<BR>
	 * �R�������F"upload_end_timestamp" <BR>
	 * �l�FTradingSystem#getSystemTimestamp()�̖߂�l <BR>
	 * <BR>
	 * �Q�j �i�����j�A�b�v���[�hID�̗v�f����ȉ����J��Ԃ��B <BR>
	 * <BR>
	 * �Q�|�P�j QueryProcessor.doUpdateAllQuery()���\�b�h���R�[������B <BR>
	 * <BR>
	 * [doUpdateAllQuery()�ɃZ�b�g����p�����[�^] <BR>
	 * arg0�F �A�b�v���[�h�e�[�u��RowType <BR>
	 * arg1�F "administrator_upload_id =?" <BR>
	 * arg2�F �i�����j�A�b�v���[�hID[index] <BR>
	 * arg3�F �P�j�ō쐬����Map <BR>
	 * 
	 * @@param l_strUploadId - (�A�b�v���[�hID)<BR>
	 * �X�V�ΏۃA�b�v���[�hID�̔z��B<BR>
	 * @@throws WEB3BaseException
	 * @@roseuid 4451C7E80111
	 */
	private void updateUploadTable(String[] l_strUploadId)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "updateUploadTable(String[] l_strUploadId)";
		log.entering(STR_METHOD_NAME);
		
		String l_strWhere = "administrator_upload_id in (?";
		int l_intUploadIdLength = l_strUploadId.length - 1;
		for (int i = 0; i < l_intUploadIdLength; i ++)
		{
			l_strWhere = l_strWhere + ",?";
		}
		l_strWhere = l_strWhere + ")";

		// �P�j �X�V���e���u�R������-�l�v�̃y�A�Ŏ���Map�I�u�W�F�N�g�𐶐�����B
		Map l_mapStatus = new HashMap();
		l_mapStatus.put("upload_end_timestamp", GtlUtils.getSystemTimestamp());

		// �Q�|�P�j QueryProcessor.doUpdateAllQuery()���\�b�h���R�[������.
		QueryProcessor l_queryProcessor;
		try
		{
			l_queryProcessor = Processors.getDefaultProcessor();
			l_queryProcessor.doUpdateAllQuery(
				AdministratorUploadRow.TYPE,
				l_strWhere, 
				l_strUploadId, 
				l_mapStatus);
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

		log.exiting(STR_METHOD_NAME);
	}


}
@
