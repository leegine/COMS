head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontNoticeHistoryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғʒm�����Q�ƃT�[�r�XImpl(WEB3AdminFrontNoticeHistoryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/27  ������ (���u) �d�l�ύX���f��No.122
Revision History : 2007/02/27  ������ (���u) �d�l�ύX���f��No.123
Revision History : 2007/02/27  ������ (���u) �d�l�ύX���f��No.125
Revision History : 2007/06/21  �Ӑ� (���u) �d�l�ύX�����̖��No.005
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.define.WEB3AdminFrontMarketKeyItemDef;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontMarketNoticeHistoryUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontSortKey;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontNoticeHistoryService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontOrderCommonService;
import webbroker3.equity.data.MarketNoticeManagementRow;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �Ǘ��Ғʒm�����Q�ƃT�[�r�X�����N���X<BR>
 */
public class WEB3AdminFrontNoticeHistoryServiceImpl implements WEB3AdminFrontNoticeHistoryService 
{
	/**
	 * Log Variable.<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminFrontNoticeHistoryServiceImpl.class);

   /**
    * @@roseuid 43001B0903AC
    */
   public WEB3AdminFrontNoticeHistoryServiceImpl() 
   {
    
   }
   
   /**
    * �Ǘ��Ғʒm�����Q�ƃT�[�r�X���s���B<BR>
    * <BR>
    * ���N�G�X�g�f�[�^�̌^�ɂ��ȉ��̃��\�b�h��<BR>
    * �Ăѕ�����B<BR>
    * <BR>
    * ���N�G�X�g�f�[�^���A<BR>
    * �@@[�Ǘ��ҁE�ʒm�����Q�Ɠ��̓��N�G�X�g�̏ꍇ]<BR>
    * �@@�@@this.get���͉��()���R�[������B<BR>
    * <BR>
    * �@@[�Ǘ��ҁE�ʒm�����Q�ƃ��N�G�X�g�̏ꍇ]<BR>
    * �@@�@@this.get�Q�Ɖ��()���R�[������B<BR>
    * @@param l_request - ���N�G�X�g<BR>
    * @@return WEB3GenResponse<BR>
    * @@throws WEB3BaseException<BR>
    * @@roseuid 42D2158701C9
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
   {
		final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
		log.entering(STR_METHOD_NAME);
	
		WEB3GenResponse l_response = null;
	
//		try 
//		{
			//get���͉��
			if (l_request instanceof WEB3AdminFrontNoticeHistoryInputRequest) {
				l_response =
					this.getInputScreen(
						(WEB3AdminFrontNoticeHistoryInputRequest) l_request);
	
			//get�Q�Ɖ��
			} else if (
				l_request instanceof WEB3AdminFrontNoticeHistoryReferenceRequest) {
				l_response =
					this.getReferenceScreen(
						(WEB3AdminFrontNoticeHistoryReferenceRequest) l_request);
	
			//���N�G�X�g�f�[�^����L�Q�ȊO�̂Ƃ��̓G���[
			} else 
			{
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80017,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"INPUT ���N�G�X�g NOT �Ǘ��ҁE�ʒm�����Q�Ɠ��̓��N�G�X�g�A�Ǘ��ҁE�ʒm�����Q�ƃ��N�G�X�g");
			}

//		}
//		catch (WEB3BusinessLayerException l_bussinesException)
//		{
//			log.error(l_bussinesException.getMessage(), l_bussinesException);
//			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//				this.getClass().getName() + STR_METHOD_NAME,l_bussinesException.getMessage(),l_bussinesException);
//		} 
		
		/*
		catch (DataQueryException l_dqex)
		{
			String l_strMsg = "Error while aquiring the Data ";
			log.error(l_strMsg, l_dqex);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,l_dqex.toString(),l_dqex);

		} 
		catch (DataNetworkException l_dnex)
		{
			String l_strMsg = "Error while aquiring the Data ";
			log.error(l_strMsg, l_dnex);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,l_dnex.toString(),l_dnex);
		}
		*/
		
		log.exiting(STR_METHOD_NAME);
		return l_response;
	
   }
   
   /**
    * �Ǘ��Ғʒm�����Q�Ɠ��͉�ʕ\���������s���B<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �u�i�ʒm�����Q�Ɓjget���͉�ʁv�Q��<BR>
    * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE�ʒm�����Q�Ɠ��̓��N�G�X�g�I�u�W�F�N�g<BR>
    * @@return �Ǘ��ҁE�ʒm�����Q�Ɠ��̓��X�|���X<BR>
    * @@roseuid 42D215D7011D
    */
   protected WEB3AdminFrontNoticeHistoryInputResponse getInputScreen(WEB3AdminFrontNoticeHistoryInputRequest requestdata) 
   throws WEB3BaseException,WEB3BusinessLayerException
   {
		final String STR_METHOD_NAME = "getInputScreen()";
		log.entering(STR_METHOD_NAME);

		WEB3Administrator l_web3Administrator = null;
		
		WEB3AdminFrontOrderCommonService l_adminFrontOrderCommonService = 
		(WEB3AdminFrontOrderCommonService) Services.getService(WEB3AdminFrontOrderCommonService.class);
		
		//���O�C�����擾
		l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

		//�Ǘ��҂̌����`�F�b�N
		l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_FRONT_NOTICE_HIST_REF,false);

		//�،���ЃR�[�h�擾
		String institutionCode = l_web3Administrator.getInstitutionCode();

        //get�����^�C�v�ꗗ(String)
        String[] l_strProductTypeList = this.getProductTypeList(institutionCode);
        
		//���X�|���X�f�[�^�쐬    	
		WEB3AdminFrontNoticeHistoryInputResponse l_response =
			(WEB3AdminFrontNoticeHistoryInputResponse) requestdata.createResponse();

		//�s��R�[�h�ꗗ�擾
		l_response.convertMarketCodeList = l_adminFrontOrderCommonService.getFindPossibleMarketCode(institutionCode);

        //�����^�C�v�ꗗ
        l_response.productTypeList = l_strProductTypeList;
        
		//get�ʒm��M���t�ꗗ 
		l_response.createdTimestampList = l_adminFrontOrderCommonService.getNoticeReceivedDateRef();
		log.exiting(STR_METHOD_NAME);
	
		return l_response;
   }
   
	/**
    * �Ǘ��Ғʒm�����Q�Ə������s���B<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �u�i�ʒm�����Q�Ɓjget�Q�Ɖ�ʁv�Q��<BR>
    * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE�ʒm�����Q�ƃ��N�G�X�g�I�u�W�F�N�g<BR>
    * @@return �Ǘ��ҁE�ʒm�����Q�ƃ��X�|���X<BR>
    * @@roseuid 42D215D7013C
    */
   protected WEB3AdminFrontNoticeHistoryReferenceResponse getReferenceScreen(WEB3AdminFrontNoticeHistoryReferenceRequest requestdata) 
   throws WEB3BaseException,WEB3BusinessLayerException
   {
		final String STR_METHOD_NAME = "getReferenceScreen()";
		log.entering(STR_METHOD_NAME);
	
		WEB3Administrator l_web3Administrator = null;
		WEB3PageIndexInfo  l_web3PageIndexInfo  = null;
		WEB3AdminFrontSortKey[] l_web3AdminFrontSortKey = null;
		WEB3AdminFrontMarketNoticeHistoryUnit[] l_web3AdminFrontMarketNoticeHistoryUnit = null;
	  	
		WEB3AdminFrontOrderCommonService l_adminFrontOrderCommonService = 
		(WEB3AdminFrontOrderCommonService) Services.getService(WEB3AdminFrontOrderCommonService.class);
		int l_intPageIndex = 0;
		int l_intPageSize = 0;


		//���N�G�X�g�f�[�^validate�`�F�b�N
		requestdata.validate();
	 
		//���O�C�����擾
		l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

		// �Ǘ��҂̌����`�F�b�N
		l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_FRONT_NOTICE_HIST_REF,false);

		// �،���ЃR�[�h�擾
		String institutionCode = l_web3Administrator.getInstitutionCode();

		//�t�����g����������敪�R�[�h�擾
		String frontOrderTradeCode = l_adminFrontOrderCommonService.getFrontOrderExchangeCode(requestdata.convertMarketCode);

		//�t�����g�����V�X�e���敪�擾
		String frontOrderSystemCode = l_adminFrontOrderCommonService.getFrontSystemDiv(requestdata.convertMarketCode);

		//create��������������( �߂� String )
		String search_Condition_Character_String = createSearchConditionCharacterString(institutionCode, frontOrderTradeCode, frontOrderSystemCode, requestdata); 

		//create���������f�[�^�R���e�i( �߂� String[] )
//		String[] search_Condition_Data_Container = (String[]) createSearchConditionDataContainer(institutionCode, frontOrderTradeCode, frontOrderSystemCode, requestdata);
        Object[] search_Condition_Data_Container = createSearchConditionDataContainer(institutionCode, frontOrderTradeCode, frontOrderSystemCode, requestdata);

  		l_web3AdminFrontSortKey = requestdata.sortKeys;

		//create�\�[�g����(WEB3AdminFrontSortKey SortKey) ( �߂� String )
		String create_Sort_Condition = createSortCondition(l_web3AdminFrontSortKey);
        
        //get�ʒm�����ꗗ( �߂� List ) 
		List Notice_History_List = getNoticeHistoryList(search_Condition_Character_String, search_Condition_Data_Container, create_Sort_Condition, null ,requestdata.pageSize, requestdata.pageIndex);
        
		//create�ʒm�����Q�ƈꗗ( �߂� �s��ʒm���𖾍�[])
		l_web3AdminFrontMarketNoticeHistoryUnit = l_adminFrontOrderCommonService.createNoticeHistryRefList(Notice_History_List);
		
		//�v���ɂ��A���X�g�̃y�[�W���߂��鏈�����s��
        // ListPage�^�ɃL���X�g
        ListPage page = (ListPage)Notice_History_List;
        
//		l_intPageIndex = Integer.parseInt(requestdata.pageIndex);
//		l_intPageSize = Integer.parseInt(requestdata.pageSize);
//		l_web3PageIndexInfo = new WEB3PageIndexInfo(l_web3AdminFrontMarketNoticeHistoryUnit, l_intPageIndex, l_intPageSize);

		//���X�|���X�f�[�^�쐬    	
		WEB3AdminFrontNoticeHistoryReferenceResponse l_response =
			(WEB3AdminFrontNoticeHistoryReferenceResponse) requestdata.createResponse();

		//�V�[�P���X�} get�Q�Ɖ�ʂ�1.16-�v���p�e�B�Z�b�g�̏���
//		l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_web3PageIndexInfo.getTotalRecords());
//		l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_web3PageIndexInfo.getTotalPages());
//		l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_web3PageIndexInfo.getPageIndex());
        // �����R�[�h��
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(page.totalSize());
        // ���y�[�W��
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(page.totalPages());
        // �\���y�[�W�ԍ�
        l_response.pageIndex = requestdata.pageIndex;
        // ���׈ꗗ���Z�b�g
		l_response.referenceList = l_web3AdminFrontMarketNoticeHistoryUnit;

		log.exiting(STR_METHOD_NAME);
	
		return l_response;
   }

   /**
    * ����������������쐬����B<BR>
    * <BR>
    * �P�j�@@��������������C���X�^���X�𐶐�����B <BR>
    * <BR>
    * �Q�j�@@�،���ЃR�[�h����������������ɒǉ�����B<BR>
    * <BR>
    * �@@"institution_code = ? "<BR>
    * <BR>
    * �R�j�@@�p�����[�^:�t�����g����������敪�R�[�h!=null�̏ꍇ�A<BR>
    * �@@�@@ �t�����g����������敪�R�[�h����������������ɒǉ�����B<BR>
    * <BR>
    *   �������������� += "and front_order_exchange_code = ? " <BR>
    * <BR>
    * �S�j�@@�p�����[�^:�t�����g�����V�X�e���敪!=null�̏ꍇ�A<BR>
    * �@@�@@ �t�����g�����V�X�e���敪����������������ɒǉ�����B<BR>
    * <BR>
    *   �������������� += "and front_order_system_code = ? " <BR>
    * <BR>
    * �T�j�@@�p�����[�^:�����^�C�v != null�̏ꍇ�A<BR>
    * �@@�@@ �����^�C�v����������������ɒǉ�����B<BR>
    * <BR>
    *   �������������� += "and product_type = ? "�@@ <BR>
    * <BR>
    * �U�j�@@�p�����[�^:�f�[�^��ʃR�[�h!= null�̏ꍇ�A <BR>
    *   �@@�f�[�^��ʃR�[�h����������������ɒǉ�����B<BR>
    * <BR>
    *   �������������� += "and data_class_code = ? " <BR>
    * <BR>
    * �V�j�@@�p�����[�^:���X�R�[�h!= null�̏ꍇ�A <BR>
    *   �@@���X�R�[�h����������������ɒǉ�����B<BR>
    * <BR>
    *   �������������� += "and branch_code = ? " <BR>
    * <BR>
    * �W�j�@@���z�T�[�oNo!= null�̏ꍇ�A <BR>
    *   �@@���z�T�[�oNo����������������ɒǉ�����B<BR>
    * <BR>
    *   �������������� += "and virtual_server_number_market = ? " <BR>
    * <BR>
    * �X�j�@@�����R�[�h!= null�̏ꍇ�A <BR>
    *   �@@�����R�[�h����������������ɒǉ�����B<BR>
    * <BR>
    *   �������������� += "and SUBSTR�iaccount_code,1,6�j = ? " <BR>
    * <BR>
    * �P�O�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A <BR>
    * �@@�@@�����R�[�h����������������ɒǉ�����B <BR>
    * <BR>
    * �@@�������������� += "and product_code = ? " <BR>
    * <BR>
	* �P�P�j�@@�p�����[�^.�ʒm��M���t != null�̏ꍇ�A<BR> 
	*�@@�@@�@@ �ʒm��M���t����������������ɒǉ�����B<BR> 
	*
	*�@@ �������������� += "and TO_CHAR(created_timestamp,'YYYYMMDD') = ? " <BR>
    * <BR>
    * �P�Q�j�@@�p�����[�^.�ʒm��M����From != null�̏ꍇ�A <BR>
    * �@@�@@�@@�ʒm��M����From����������������ɒǉ�����B <BR>
    * <BR>
    * �@@�������������� += "and TO_CHAR(created_timestamp,'HH24MISS') >= ? " <BR>
    * <BR>
    * �P�R�j�@@�p�����[�^.�ʒm��M�����s�� != null�̏ꍇ�A <BR>
    * �@@�@@�@@�ʒm��M����To����������������ɒǉ�����B <BR>
    * <BR>
    * �@@�������������� += "and TO_CHAR(created_timestamp,'HH24MISS') <= ? " <BR>
    * <BR>
    * �P�S�j�@@�쐬������������������C���X�^���X��ԋp����B<BR>
    * @@param �،���ЃR�[�h - �،���ЃR�[�h<BR>
    * @@param �t�����g����������敪�R�[�h - <BR>
    * get�t�����g����������敪�R�[�h�ŁA�擾�����l�B<BR>
    * @@param �t�����g�����V�X�e���敪 - get�t�����g�����V�X�e���敪�ŁA�擾�����l�B<BR>
    * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE�ʒm�����Q�ƃ��N�G�X�g�I�u�W�F�N�g<BR>
    * @@return String<BR>
    * @@roseuid 42D6087E0387
    */
   protected String createSearchConditionCharacterString(String institutionCode, String frontOrderTradeCode, String frontOrderSystemCode, WEB3AdminFrontNoticeHistoryReferenceRequest requestdata) 
   {
		final String STR_METHOD_NAME = "createSearchConditionCharacterString(String, String, String, WEB3AdminFrontNoticeHistoryReferenceRequest)";
		log.entering(STR_METHOD_NAME);
	
		//��������������p�����[�^�C���X�^���X�쐬
		StringBuffer l_sbQueryCond = new StringBuffer();
	
		// �،���ЃR�[�h����������������ɒǉ�
		l_sbQueryCond.append("institution_code = ? ");
	
		// �t�����g����������敪�R�[�h�`�F�b�N frontOrderTradeCode is not null
		if (frontOrderTradeCode != null)
		{
			l_sbQueryCond.append("and front_order_exchange_code = ? ");
		}
	
		// �t�����g�����V�X�e���敪�`�F�b�N frontOrderSystemCode is not null
		if (frontOrderSystemCode != null)
		{
			l_sbQueryCond.append("and front_order_system_code = ? ");
		}

        // �����^�C�v�`�F�b�N productType is not null
        if (requestdata.productType != null)
        {
            l_sbQueryCond.append("and product_type = ? ");
        }
        
		// �f�[�^��ʃR�[�h�`�F�b�N dataClassCode is not null
		if (requestdata.dataClassCode != null)
		{
			l_sbQueryCond.append("and data_class_code = ? ");
		}

		// ���X�R�[�h�`�F�b�N branchCode is not null
		if (requestdata.branchCode != null)
		{
			l_sbQueryCond.append("and branch_code = ? ");
		}

		// ���z�T�[�o�m���`�F�b�N virtualServerNumber is not null
		if (requestdata.virtualServerNumber != null)
		{
			l_sbQueryCond.append("and virtual_server_number_market = ? ");
		}

		// �����R�[�h�`�F�b�N accountCode is not null
		if (requestdata.accountCode != null)
		{
			l_sbQueryCond.append("and SUBSTR(account_code,1,6) = ? ");
		}

		// �����R�[�h�`�F�b�N productCode is not null
		if (requestdata.productCode != null)
		{
			l_sbQueryCond.append("and product_code = ? ");
		}

		// �ʐM��M���t�`�F�b�N createdTimestamp is not null
		if (requestdata.createdTimestamp != null)
		{
			//l_sbQueryCond.append("and createdTimestamp = ? ");
			l_sbQueryCond.append("and TO_CHAR(created_Timestamp,'YYYYMMDD')= ? ");
		}

		// �ʐM��M����From�`�F�b�N createdTimestampFrom is not null
		if (requestdata.createdTimestampFrom != null)
		{
			l_sbQueryCond.append("and TO_CHAR(created_timestamp,'HH24MISS') >= ? ");
		}

		// �ʐM��M����To�`�F�b�N createdTimestampTo is not null
		if (requestdata.createdTimestampTo != null)
		{
			l_sbQueryCond.append("and TO_CHAR(created_timestamp,'HH24MISS') <= ? ");
		}

		log.exiting(STR_METHOD_NAME);
 		//�쐬������������������C���X�^���X��ԋp����
    	return l_sbQueryCond.toString();
   }
   
   /**
    * ���������f�[�^�R���e�i��ҏW����B<BR>
    * <BR>
    * �P�j�@@�����l���i�[����List�I�u�W�F�N�g�𐶐�����B<BR>
    * <BR>
    * �Q�j�@@�،���ЃR�[�h��List�I�u�W�F�N�g�ɒǉ�����B<BR>
    * <BR>
    * �@@List�I�u�W�F�N�g.add(�،���ЃR�[�h);<BR>
    * <BR>
    * �R�j�@@�p�����[�^:�t�����g����������敪�R�[�h!=null�̏ꍇ�A<BR>
    * �@@�@@ �t�����g����������敪�R�[�h��List�I�u�W�F�N�g�ɒǉ�����B<BR>
    * <BR>
    * �@@List�I�u�W�F�N�g.add(�t�����g����������敪�R�[�h);<BR>
    * <BR>
    * �S�j�@@�p�����[�^:�t�����g�����V�X�e���敪!=null�̏ꍇ�A<BR>
    * �@@�@@ �t�����g�����V�X�e���敪��List�I�u�W�F�N�g�ɒǉ�����B<BR>
    * <BR>
    * �@@List�I�u�W�F�N�g.add(�t�����g�����V�X�e���敪);<BR>
    * <BR>
    * �T�j�@@�p�����[�^:�����^�C�v != null�̏ꍇ�A <BR>
    * �@@�@@ �����^�C�v��List�I�u�W�F�N�g�ɒǉ�����B <BR>
    * <BR>   
    * �@@List�I�u�W�F�N�g.add(�����^�C�v); <BR>
    * <BR>
    * �U�j�@@�p�����[�^:�f�[�^��ʃR�[�h!= null�̏ꍇ�A <BR>
    *   �@@ �f�[�^��ʃR�[�h��List�I�u�W�F�N�g�ɒǉ�����B<BR>
    * <BR>
    * �@@List�I�u�W�F�N�g.add(�f�[�^��ʃR�[�h);<BR>
    * <BR>
    * �V�j  �p�����[�^:���X�R�[�h!= null�̏ꍇ�A <BR>
    *      ���X�R�[�h��List�I�u�W�F�N�g�ɒǉ�����B<BR>
    * <BR>
    * �@@List�I�u�W�F�N�g.add(���X�R�[�h);<BR>
    * <BR>
    * �W�j  ���z�T�[�oNo!= null�̏ꍇ�A <BR>
    *      ���z�T�[�oNo��List�I�u�W�F�N�g�ɒǉ�����B<BR>
    * <BR>
    * �@@List�I�u�W�F�N�g.add(���z�T�[�oNo);<BR>
    * <BR>
    * �X�j  �����R�[�h!= null�̏ꍇ�A <BR>
    *      �����R�[�h��List�I�u�W�F�N�g�ɒǉ�����B<BR>
    * <BR>
    * �@@List�I�u�W�F�N�g.add(�����R�[�h);<BR>
    * <BR>
    * �P�O�j  �p�����[�^.�����R�[�h != null�̏ꍇ�A <BR> 
    * �@@   �����R�[�h��List�I�u�W�F�N�g�ɒǉ�����B<BR>
    * <BR>
    * �@@List�I�u�W�F�N�g.add(�����R�[�h);<BR>
    * <BR>
	* �P�P�j �p�����[�^.�ʒm��M���t != null�̏ꍇ�A<BR> 
	*	�@@ �@@�ʒm��M���t��List�I�u�W�F�N�g�ɒǉ�����B<BR> 
    * <BR>	
	*	�@@List�I�u�W�F�N�g.add(�ʒm��M���t);<BR>  
	* <BR>
    * �P�Q�j  �p�����[�^.�ʒm��M����From != null�̏ꍇ�A <BR>
    * �@@   �@@�ʒm��M����From��List�I�u�W�F�N�g�ɒǉ�����B<BR>
    * <BR>
    * �@@List�I�u�W�F�N�g.add(�ʒm��M����From);<BR>
    * <BR>
    * �P�R�j  �p�����[�^.�ʒm��M�����s�� != null�̏ꍇ�A <BR>
    *     �@@ �ʒm��M����To��List�I�u�W�F�N�g�ɒǉ�����B<BR>
    * <BR>
    * �@@List�I�u�W�F�N�g.add(�ʒm��M����To);<BR>
    * <BR>
    * �P�S�j  �i�[�������X�g��z��I�u�W�F�N�g�ɕϊ�����B<BR>
    * <BR>
    * �P�T�j�@@�쐬�����z��I�u�W�F�N�g��ԋp����B<BR>
    * @@param �،���ЃR�[�h - �،���ЃR�[�h<BR>
    * @@param �t�����g����������敪�R�[�h - <BR>
    * get�t�����g����������敪�R�[�h����擾�����l�B<BR>
    * @@param �t�����g�����V�X�e���敪 - get�t�����g�����V�X�e���敪����擾�����l�B<BR>
    * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE�ʒm�����Q�ƃ��N�G�X�g�I�u�W�F�N�g<BR>
    * @@return Object[]<BR>
    * @@roseuid 42D62AC70135
    */
   protected Object[] createSearchConditionDataContainer(String institutionCode, String frontOrderTradeCode, String frontOrderSystemCode, WEB3AdminFrontNoticeHistoryReferenceRequest requestdata)
   {
		final String STR_METHOD_NAME =	"createSearchConditionDataContainer(String, String, String, String, WEB3AdminFrontNoticeHistoryReferenceRequest)";
		log.entering(STR_METHOD_NAME);
	
		ArrayList l_arrQueryDataList = new ArrayList();
		Object[] l_queryData = null;

		// �،���ЃR�[�h�����������f�[�^�R���e�i�ɒǉ�
		l_arrQueryDataList.add(institutionCode);

		// �t�����g����������敪�R�[�h�`�F�b�N frontOrderTradeCode is not null
		if (frontOrderTradeCode != null)
		{
			l_arrQueryDataList.add(frontOrderTradeCode);
		}

		// �t�����g�����V�X�e���敪�`�F�b�N frontOrderSystemCode is not null
		if (frontOrderSystemCode != null)
		{
			l_arrQueryDataList.add(frontOrderSystemCode);
		}
        
        // �����^�C�v�`�F�b�N productType is not null
        if (requestdata.productType != null)
        {
            l_arrQueryDataList.add(requestdata.productType);
        }

		// �f�[�^��ʃR�[�h�`�F�b�N dataClassCode is not null
		if (requestdata.dataClassCode != null)
		{
			l_arrQueryDataList.add(requestdata.dataClassCode);
		}

		// ���X�R�[�h�`�F�b�N branchCode is not null
		if (requestdata.branchCode != null)
		{
			l_arrQueryDataList.add(requestdata.branchCode);
		}

		// ���z�T�[�oNo�`�F�b�N virtualServerNumber is not null
		if (requestdata.virtualServerNumber != null)
		{
			l_arrQueryDataList.add(requestdata.virtualServerNumber);
		}

		// �����R�[�h�`�F�b�N accountCode is not null
		if (requestdata.accountCode != null)
		{
			l_arrQueryDataList.add(requestdata.accountCode);
		}

		// �����R�[�h�`�F�b�N productCode is not null
		if (requestdata.productCode != null)
		{
			l_arrQueryDataList.add(requestdata.productCode);
		}

		// �ʒm��M���t�`�F�b�N createdTimestamp is not null
		if (requestdata.createdTimestamp != null)
		{
			l_arrQueryDataList.add(requestdata.createdTimestamp);
		}

		// �ʒm��M����From�`�F�b�N createdTimestampFrom is not null
		if (requestdata.createdTimestampFrom != null)
		{
			l_arrQueryDataList.add(requestdata.createdTimestampFrom);
		}
	
		// �ʒm��M����To�`�F�b�N createdTimestampTo is not null
		if (requestdata.createdTimestampTo != null)
		{
			l_arrQueryDataList.add(requestdata.createdTimestampTo);
		}

		// �i�[�������X�g��z��I�u�W�F�N�g�ɕϊ�����
		l_queryData = l_arrQueryDataList.toArray();
	
		log.exiting(STR_METHOD_NAME);
		// �쐬�����z��I�u�W�F�N�g��ԋp����
		return l_queryData;

   }
   
   /**
    * �\�[�g�������쐬����B <BR>
    * <BR>
    * �@@�P�j�@@�\�[�g����������(�FString)���쐬����B<BR>
    * <BR>
    * �@@�Q�j�@@�p�����[�^.�\�[�g�L�[ == null�̏ꍇ�A<BR>
    * <BR>
    * �@@�@@"�쐬���t�A�f�[�^��ʃR�[�h ����"�̃\�[�g������ԋp����B<BR>
    * <BR>
    * �@@�R�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
    * <BR>
    * �@@�R�|�P�j�@@�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A�쐬�����\�[�g����������ɒǉ�����B<BR>
    * <BR>
    * �@@�E�u�ʒm��M�����v�@@���@@�s��ʒm�Ǘ�.�쐬����<BR>
    * �@@�E�u�f�[�^��ʃR�[�h�v�@@���@@�s��ʒm�Ǘ�.�f�[�^��ʃR�[�h<BR>
    * �@@�E�u���X�R�[�h�v�@@���@@�s��ʒm�Ǘ�.���X�R�[�h<BR>
    * �@@�E�u�����R�[�h�v�@@���@@�s��ʒm�Ǘ�.�����R�[�h<BR>
    * �@@�E�u�����R�[�h�v�@@���@@�s��ʒm�Ǘ�.�����R�[�h<BR>
    * �@@�E�u���z�T�[�oNo�v�@@���@@�s��ʒm�Ǘ�.���z�T�[�oNo<BR>
    * <BR>
    * �@@�R�|�Q�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or <BR>
    * desc)���\�[�g����������ɒǉ�����B<BR>
    * <BR>
    * �@@�S�j�@@�쐬�����\�[�g�����������ԋp����B<BR>
    * @@param �\�[�g�L�[ - �ʒm�����Q�ƃ\�[�g�L�[<BR>
    * @@return String<BR>
    * @@roseuid 42D749C900A5
    */
   protected String createSortCondition(WEB3AdminFrontSortKey[] SortKey) 
   {
		final String STR_METHOD_NAME = "createSortCondition()";
		log.entering(STR_METHOD_NAME);
		int i = 0;
	
		StringBuffer l_sortCondList = new StringBuffer();
		String sortMoji = null;
		
		//�\�[�g����������(�FString)���쐬����
		
		//�p�����[�^.�\�[�g�L�[ == null�̏ꍇ
		if(SortKey == null){
			//"�쐬���t�A�f�[�^��ʃR�[�h ����"�̃\�[�g������ԋp����
			sortMoji = "created_timestamp,data_class_code asc";
			log.exiting(STR_METHOD_NAME);
			return sortMoji;
				
		}else{

			//�v�f��
			int l_intLen = SortKey.length;
			
			//�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ�
			for (i = 0; i < l_intLen; i++)
			{
				 //If sortCondList is not empty(""), add ","(comma) to sortCondList before processing
				 if (!l_sortCondList.toString().equals(""))
				 {
					l_sortCondList.append(", ");
				 }

				 //�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A�쐬�����\�[�g����������ɒǉ�����
                if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.CREATED_TIMESTAMP))
                {
                   //�u�ʒm��M�����v�@@���@@�s��ʒm�Ǘ�.�ʒm��M����
                   l_sortCondList.append("created_timestamp");
                }

				 if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.DATA_CLASS_CODE))
				 {
				 	//�u�f�[�^��ʃR�[�h�v�@@���@@�s��ʒm�Ǘ�.�f�[�^��ʃR�[�h
				    l_sortCondList.append("data_class_code");
				 }

				 if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.BRANCH_CODE))
				 {
				 	//�u���X�R�[�h�v�@@���@@�s��ʒm�Ǘ�.���X�R�[�h
					l_sortCondList.append("branch_code");
				 }
	
				 if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.PRODUCT_CODE))
				 {
				    //�u�����R�[�h�v�@@���@@�s��ʒm�Ǘ�.�����R�[�h
				    l_sortCondList.append("product_code");
				 }

				 if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.ACCOUNT_CODE))
				 {
				 	//�u�����R�[�h�v�@@���@@�s��ʒm�Ǘ�.�����R�[�h
					l_sortCondList.append("account_code");
				 }
	
				 if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.VIRTUAL_SERVER_NUMBER))
				 {
				 	//�u���z�T�[�oNo�v�@@���@@�s��ʒm�Ǘ�.���z�T�[�oNo
					l_sortCondList.append("virtual_server_number_market");
				 }
	
				 //�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)���\�[�g����������ɒǉ�����
				 if (SortKey[i].ascDesc.equals(WEB3AscDescDef.ASC))
				 {
					l_sortCondList.append(" asc");
				 }

				 if (SortKey[i].ascDesc.equals(WEB3AscDescDef.DESC))
				 {
					l_sortCondList.append(" desc");
				 }

			}	// for END
			
			//�쐬�����\�[�g�����������ԋp����	
			log.exiting(STR_METHOD_NAME);
			return l_sortCondList.toString();
		}
   }
   
   /**
    * �����̏����ɊY������ʒm�����̈ꗗ��ԋp����B <BR>
    * <BR>
    * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>
    * <BR>
    * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
    * �@@�@@arg0�F�@@�s��ʒm�Ǘ�Row.TYPE <BR>
    * �@@�@@arg1�F�@@�p�����[�^.�������������� <BR>
    * �@@�@@arg2�F�@@�p�����[�^.�\�[�g���� <BR>
    * �@@�@@arg3�F�@@null <BR>
    * �@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i <BR>
    * �@@�@@arg5�F�@@�p�����[�^.�y�[�W���\���s�� <BR>
    * �@@�@@arg6�F�@@�p�����[�^.�\���y�[�W�ԍ� <BR>
    * <BR>
    * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A�G���[�������s���B <BR>
    * <BR>
    * <BR>
    * �Q�j�������ʂ�ԋp����B<BR>
    * @@param �������������� - ��������������<BR>
    * @@param ���������f�[�^�R���e�i - ���������f�[�^�R���e�i<BR>
    * @@param �\�[�g���� - �\�[�g����<BR>
    * @@param �N�G������������<BR>
    * @@param �y�[�W���\���s�� - �y�[�W���ɕ\������s���B<BR>
    * @@param �\���y�[�W�ԍ� - ���y�[�W�ԍ��B<BR>
    * @@return List<BR>
    * @@roseuid 42D75B690367
    */
// protected List getNoticeHistoryList(String searchConditionCharacterString, String[] searchConditionDataContainer, String sortCondition, String query,String pageSize, String pageIndex)
   protected List getNoticeHistoryList(String searchConditionCharacterString, Object[] searchConditionDataContainer, String sortCondition, String query,String pageSize, String pageIndex) 
   throws WEB3SystemLayerException 
   {
		final String STR_METHOD_NAME = "getNoticeHistoryList()";
		log.entering(STR_METHOD_NAME);
		
		int i_pageSize = Integer.parseInt(pageSize);
		int i_pageIndex = Integer.parseInt(pageIndex)-1;
		
		// ArrayList�I�u�W�F�N�g�̐���
		List noticeHistoryList = new ArrayList(); 

		try
		{
			//QueryProcessor.doFindAllQuery()���\�b�h���R�[������
			QueryProcessor queryProcessor = Processors.getDefaultProcessor();

			noticeHistoryList = queryProcessor.doFindAllQuery(
				MarketNoticeManagementRow.TYPE,
				searchConditionCharacterString,
				sortCondition,
				null,
				searchConditionDataContainer,
				i_pageSize,
				i_pageIndex
			);
   	
		}
		catch (DataException de)
		{
			//�������ʂ��擾�ł��Ȃ������ꍇ�A�G���[�������s��
			log.error(STR_METHOD_NAME, de);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
			this.getClass().getName() + "." + STR_METHOD_NAME,de.getMessage(),de);
		}
   	
		//�������ʂ�ԋp����
   		return noticeHistoryList;
   }
   
   /**
    * (get�����^�C�v�ꗗ)<BR>
    * �s��ʒm�Ǘ��e�[�u���̌����ɓK���������^�C�v���擾����B<BR>
    * <BR>
    * �P�j�@@ArrayList�I�u�W�F�N�g�𐶐�����B <BR>
    * <BR>
    *�@@�Q�j�@@DB���� <BR>
    *�@@�ȉ��̏����Ŕ�����ؑփe�[�u������������B <BR>
    *�@@�������^�C�v�ŁA�\�[�g����B <BR>
    *�@@<BR>
    *�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h and <BR>
    *�@@�����^�C�v in (1�F����, 6�F�敨�I�v�V����) and <BR>
    *�@@�����o�H�敪 = 2�F�t�����g�������n <BR>
    * <BR>
    *�@@�������ʂ��擾�ł��Ȃ������ꍇ�A��O���X���[����B <BR>
    * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
    * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01279<BR>
    * <BR>
    * �R�j�@@�擾����List�̃T�C�Y���ALoop�������s���B <BR>
    * <BR>
    * �R�|�P�j�@@List�I�u�W�F�N�g����A�����^�C�v���擾����B <BR>
    * <BR>
    * �R�|�Q�j�@@�擾���������^�C�v���A����ArrayList�I�u�W�F�N�g��add()����Ă���icontains() == true�j�ꍇ�Acontinue����B <BR>
    * <BR>
    * �R�|�R�j�@@�擾���������^�C�v��ArrayList�I�u�W�F�N�g��add()����B <BR>
    * <BR>
    * �S�j�@@ArrayList�I�u�W�F�N�g��String�z��ɕϊ�����B <BR>
    * <BR>
    * �T�j�@@�ϊ�����String�z���ԋp����B<BR>
    * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
    * @@return �����^�C�vString[]�B<BR>
    * @@throws WEB3BaseException <BR>
    */
   
   public String[] getProductTypeList(String l_strInstitutionCode) throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = ".getProductTypeList(String)";
       log.entering(STR_METHOD_NAME);
       
       // ArrayList�I�u�W�F�N�g�̐���
       List l_lstConvertCodes = new ArrayList();
       // ArrayList�I�u�W�F�N�g�̐���
       List l_lstAddProductType = new ArrayList();
       // ������ؑփe�[�u����������List
       List l_switchingResult = new ArrayList();
       // return�pString�^�z��
       String[] l_dispMarketLists = null;
       
       // ��������������̐���
       StringBuffer l_sbWhere = new StringBuffer();
       l_sbWhere.append(" institution_code = ? ");
       l_sbWhere.append(" and product_type in (�@@?,?)");
       l_sbWhere.append(" and submit_order_route_div = ? ");
       
       // ���������R���e�i�̐���
       Object[] l_objWhere =
           {
               l_strInstitutionCode,
               Integer.toString(ProductTypeEnum.IFO.intValue()),
               Integer.toString(ProductTypeEnum.EQUITY.intValue()),
               WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION
           };
       
       try {
           // DB����
           QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

           l_switchingResult = l_queryProcessor.doFindAllQuery(
               OrderSwitchingRow.TYPE,
               l_sbWhere.toString(),
               null,
               l_objWhere);
           
           // �������ʂ�0���̏ꍇ�A�G���[���b�Z�[�W���X���[����B
           if (l_switchingResult.size() == 0)
           {
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01279, 
                   this.getClass().getName() + STR_METHOD_NAME);                 
           }
       }
       catch (DataException l_de)
       {
           log.error(STR_METHOD_NAME, l_de);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + STR_METHOD_NAME,
               l_de.getMessage(),
               l_de);
       }  

       Iterator l_ObjSwitchResult = l_switchingResult.iterator();
       
       while (l_ObjSwitchResult.hasNext())
       {   
           // ������ؑփe�[�u��Row�I�u�W�F�N�g�̎擾      
           OrderSwitchingRow switchRow = (OrderSwitchingParams)l_ObjSwitchResult.next();
           
           // �����^�C�v���擾
           ProductTypeEnum l_productType = switchRow.getProductType();
           
           // ����ArrayList�I�u�W�F�N�g��add()����Ă���icontains() == true�j�ꍇ�Acontinue����B 
           if (l_lstConvertCodes.contains("" + l_productType.intValue()))
           {
               continue;
           }
           
           // List�I�u�W�F�N�g�ɒǉ�
           l_lstConvertCodes.add("" + l_productType.intValue());
       }
       
       l_dispMarketLists = new String[l_lstConvertCodes.size()];
       
       // List����z��ɕϊ�
       for (int i=0;i<l_lstConvertCodes.size();i++)
       {
           l_lstAddProductType.add(l_lstConvertCodes.get(i));
       }
       
       l_lstAddProductType.toArray(l_dispMarketLists);

       log.exiting(STR_METHOD_NAME);
       return l_dispMarketLists;
   }
}

@
