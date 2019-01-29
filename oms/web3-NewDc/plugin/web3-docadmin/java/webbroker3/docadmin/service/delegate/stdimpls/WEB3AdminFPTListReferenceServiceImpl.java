head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTListReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�XImpl(WEB3AdminFPTListReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 ���g (���u) �V�K�쐬
Revision History : 2007/10/15 ���g (���u) ���f��No.005
Revision History : 2007/10/16 Inomata (SCS) ���f��No.007
Revision History : 2007/10/18 Inomata (SCS) ���f��008
Revision History : 2008/01/28 �đo�g (���u) ���f��No.025
Revision History : 2008/03/17 �g�C�� (���u) ���f��No.047
Revision History : 2008/04/18 �đo�g (���u) ���f��No.053
*/

package webbroker3.docadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTDocCategoryManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDivManagement;
import webbroker3.docadmin.define.WEB3AdminFPTSortKeyItemDef;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTSortKey;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTHistoryInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTListReferenceService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�XImpl)<BR>
 * �Ǘ��ҋ����@@��t�{���Ɖ�T�[�r�X�����N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTListReferenceServiceImpl implements WEB3AdminFPTListReferenceService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTListReferenceServiceImpl.class);

    /**
     * @@roseuid 46FDDD360157
     */
    public WEB3AdminFPTListReferenceServiceImpl()
    {

    }

    /**
     * �����@@��t�{���Ɖ�������{����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �P�|�P�j�@@�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���������̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get��t�{����������()���R�[������B<BR>
     * <BR>
     * �P�|�Q�j�@@�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�N�G�X�g�̏ꍇ<BR>
     * �@@�|get��t�{���Ɖ�()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46F1D60C02A4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminFPTSearchInputRequest)
        {
            //�Ǘ��ҋ����@@��t�{���������̓��N�G�X�g�̏ꍇ
            l_response = this.getListSearchInput((WEB3AdminFPTSearchInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTListReferenceRequest)
        {
            //�Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�N�G�X�g
            l_response = this.getListReference((WEB3AdminFPTListReferenceRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get��t�{����������)<BR>
     * �Ǘ��ҋ����@@��t�{���������͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget��t�{���������́v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 46F1F2CE011D
     */
    protected WEB3AdminFPTSearchInputResponse getListSearchInput(
        WEB3AdminFPTSearchInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSearchInput(WEB3AdminFPTSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FPT_REGIST_INQUIRY, false);

        //get�،���ЃR�[�h()
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get���X�R�[�h()
        String l_strBranchCode = l_admin.getBranchCode();

        //���ʋ敪�Ǘ�(String, String, String, String)
        // [����]
        // �،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h() �̖߂�l
        // ���X�R�[�h�F �Ǘ���.get���X�R�[�h() �̖߂�n
        // ���ʋ敪�F null
        // ���ʎ�ރR�[�h�F null
        WEB3AdminFPTDocDivManagement l_adminDocAdminFPTDocDivManagement =
            new WEB3AdminFPTDocDivManagement(
                l_strInstitutionCode,
                l_strBranchCode,
                null,
                null);

        //get���ʋ敪�Ǘ��ꗗ()
        WEB3FPTDocumentDivAdminInfoUnit[] l_fPTDocumentDivAdminInfoUnits =
            l_adminDocAdminFPTDocDivManagement.getDocDivManagementLists();

        //creatResponse
        WEB3AdminFPTSearchInputResponse l_response =
            (WEB3AdminFPTSearchInputResponse)l_request.createResponse();

        //���X�|���X.���ʋ敪�Ǘ��ꗗ = get���ʋ敪�Ǘ��ꗗ()
        l_response.documentDivList = l_fPTDocumentDivAdminInfoUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get��t�{���Ɖ�)<BR>
     * �Ǘ��ҋ����@@��t�{���Ɖ��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget��t�{���Ɖ�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFPTListReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 46F1F2E602FE
     */
    protected WEB3AdminFPTListReferenceResponse getListReference(
        WEB3AdminFPTListReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListReference(WEB3AdminFPTListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FPT_REGIST_INQUIRY, false);

        //validate���X����(���X�R�[�h : String[])
        l_admin.validateBranchPermission(l_request.branchCode);

        //get�،���ЃR�[�h()
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //validate��������(String, �Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�N�G�X�g)
        // [����]
        //  �،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l
        //  ���������F  �Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�N�G�X�g
        this.validateQueryCondition(l_strInstitutionCode, l_request);

        //create��������������(�Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�N�G�X�g)
        String l_strQueryString = this.createQueryString(l_request);

        //create���������f�[�^�R���e�i(String, �Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�N�G�X�g)
        Object[] l_queryContainers = this.createQueryDataContainer(l_strInstitutionCode, l_request);

        //create�\�[�g�L�[(�Ǘ��ҋ����@@�\�[�g�L�[[])
        String l_strSortKey = this.createSortKey(l_request.sortKeys);

        WEB3AdminFPTListReferenceResponse l_response;

        try
        {
            //getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            ListPage l_lisDocDeliveryManagements = l_queryProcessor.doFindAllQuery(
                DocDeliveryManagementRow.TYPE,
                l_strQueryString,
                l_strSortKey,
                null,
                l_queryContainers,
                Integer.parseInt(l_request.pageSize),
                Integer.parseInt(l_request.pageIndex) - 1);

			//���R�[�h���擾�ł��Ȃ��ꍇ�A��O�́u�Y���Ȃ��G���[�v��throw����B
			if (l_lisDocDeliveryManagements == null || l_lisDocDeliveryManagements.isEmpty())
			{
				log.debug("�Y������f�[�^�����݂��܂���B");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00398,
					this.getClass().getName() + STR_METHOD_NAME);
			}

            WEB3PageIndexInfo l_lisViewPageIndexInfo =
                new WEB3PageIndexInfo(
                	l_lisDocDeliveryManagements,
                    Integer.parseInt(l_request.pageIndex),
                    Integer.parseInt(l_request.pageSize));

            int l_intRowsLength = l_lisDocDeliveryManagements.size();

            //get�،����()
            WEB3GentradeInstitution l_gentradeInstitution =
                (WEB3GentradeInstitution)l_admin.getInstitution();

            List l_lisHistoryInfoUnits = new ArrayList();
            //�擾�������R�[�h�̗v�f���ALoop����
            for (int i = 0; i < l_intRowsLength; i++)
            {
                //create�����@@��t�{�����(���ʌ�t�Ǘ�Params, �،����)
                DocDeliveryManagementParams l_docDeliveryManagementParams =
                    new DocDeliveryManagementParams(
                        (DocDeliveryManagementRow)l_lisDocDeliveryManagements.get(i));
                WEB3FPTHistoryInfoUnit l_fPTHistoryInfoUnit =
                    this.createHistoryInfoUnit(l_docDeliveryManagementParams, l_gentradeInstitution);

                //add(arg0 : Object)
                l_lisHistoryInfoUnits.add(l_fPTHistoryInfoUnit);
            }

            //createResponse()
            l_response = (WEB3AdminFPTListReferenceResponse)l_request.createResponse();

            //�����@@��t�{���ꗗ
            WEB3FPTHistoryInfoUnit[] l_fPTHistoryInfoUnits =
                new WEB3FPTHistoryInfoUnit[l_lisHistoryInfoUnits.size()];
            l_lisHistoryInfoUnits.toArray(l_fPTHistoryInfoUnits);
            l_response.financialProductTradeList = l_fPTHistoryInfoUnits;

            //�\���y�[�W�ԍ�
            l_response.pageIndex = String.valueOf(l_lisViewPageIndexInfo.getPageIndex());

            //���y�[�W��
            l_response.totalPages = String.valueOf(l_lisViewPageIndexInfo.getTotalPages());

            //�����R�[�h��
            l_response.totalRecords = String.valueOf(l_lisViewPageIndexInfo.getTotalRecords());
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
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * �擾�����̕�����𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     * �@@"institution_code=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j ���X�R�[�h <BR>
     * �R-�P�j ����:�������. ���X�R�[�h�̒���=1 �̏ꍇ�A<BR> 
     * ���X�R�[�h����������������ɒǉ�����B  <BR>
     * �������������� += "and branch_code = ? "  <BR>
     * �R-�Q�j ����:�������. ���X�R�[�h�̒��� > 1�̏ꍇ�A <BR>
     * ���X�R�[�h�z��̗v�f�����A���X�R�[�h������ǉ�����B<BR>
     * �������������� +="and branch_code in (?,?, �c�j"   <BR>
     * <BR>
     * �S�j�@@����:�������. �ڋq�R�[�h != null �̏ꍇ�A<BR>
     * �ڋq�R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �������������� += "and account_code like ? %"<BR>
     * <BR>
     * �T�j�@@���ʋ敪�E���ʎ�ރR�[�h<BR>
     * �@@�T-�P�j ����:�������.���ʋ敪�Ǘ��ꗗ�̒��� = 1 �̏ꍇ�A<BR>
     * �@@�@@���ʋ敪�A���ʎ�ރR�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and document_div = ? and document_category = ? "<BR>
     * <BR>
     * �@@�T-�Q�j ����:�������.���ʋ敪�Ǘ��ꗗ�̒��� > 1 �̏ꍇ�A<BR>
     * �@@�@@�z��̗v�f�����A���ʋ敪�A���ʎ�ރR�[�h�̃y�A����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and ((document_div = ? and document_category = ?) or (�c))"<BR>
     * <BR>
     * �U�j�@@����:�������.���ʌ�t��from != null�̏ꍇ�A<BR>
     * �@@�@@���ʌ�t��from����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and delivery_date�@@>=�@@?"<BR>
     * <BR>
     * �V�j�@@����:�������.���ʌ�t��to != null�̏ꍇ�A<BR>
     * �@@�@@���ʌ�t��to����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and delivery_date�@@<=�@@?"<BR>
     * <BR>
     * <BR>
     * �W�j�@@�쐬������������������C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_request - (��������)<BR>
     * ��������<BR>
     * @@return String
     * @@roseuid 46F20689028C
     */
    private String createQueryString(WEB3AdminFPTListReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminFPTListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j��̕�����𐶐�����B
        StringBuffer l_sbQueryString = new StringBuffer();

        //�Q�j�،���ЃR�[�h
        //"institution_code=?" ���P�j�̕�����ɒǉ�����B
        l_sbQueryString.append(" institution_code = ? ");

		//�R�j�@@���X�R�[�h 
		//  �R-�P�j ����:�������. ���X�R�[�h�̒���=1 �̏ꍇ�A  
		//		 ���X�R�[�h����������������ɒǉ�����B  
		//		 �������������� += "and branch_code = ? "  
		//  �R-�Q�j ����:�������. ���X�R�[�h�̒��� > 1�̏ꍇ�A 
		//		���X�R�[�h�z��̗v�f�����A���X�R�[�h������ǉ�����B   
		//	   �������������� +="and branch_code in (?,?, �c�j"   
    	if (l_request.branchCode.length == 1)
    	{
			l_sbQueryString.append(" and branch_code = ? ");
    	} else if (l_request.branchCode.length > 1)
    	{
			l_sbQueryString.append(" and branch_code in (");

			for (int i = 0; i < l_request.branchCode.length; i++)
			{
				l_sbQueryString.append("?");
				if (i != l_request.branchCode.length-1){
					l_sbQueryString.append(", ");
				}
			}
			l_sbQueryString.append(")");
    	}

        //�S�j�@@����:�������. �ڋq�R�[�h != null �̏ꍇ�A
        //�ڋq�R�[�h����������������ɒǉ�����B
        //�������������� += "and account_code like ? %"
        if (l_request.acceptCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%' ");
        }

        //���ʋ敪�E���ʎ�ރR�[�h
        // ����:�������.���ʋ敪�Ǘ��ꗗ�̒��� = 1 �̏ꍇ
        // ���ʋ敪�A���ʎ�ރR�[�h����������������ɒǉ�����B
        // �������������� += "and document_div = ? and document_category = ? "
        if (l_request.documentDivList.length == 1)
        {
            l_sbQueryString.append(" and document_div = ? and document_category = ? ");
        }

        // ����:�������.���ʋ敪�Ǘ��ꗗ�̒��� > 1 �̏ꍇ�A
        // �z��̗v�f�����A���ʋ敪�A���ʎ�ރR�[�h�̃y�A����������������ɒǉ�����B
        // �������������� += "and ((document_div = ? and document_category = ?) or (�c))"
        if (l_request.documentDivList.length > 1)
        {
            l_sbQueryString.append(" and( ");
            for (int i = 0; i < l_request.documentDivList.length; i++)
            {
                l_sbQueryString.append(" (document_div = ? and document_category = ?) ");
                if (i < l_request.documentDivList.length - 1)
                {
                    l_sbQueryString.append(" or ");
                }
            }
            l_sbQueryString.append(" ) ");
        }

        //�V�j�@@����:�������.���ʌ�t��from != null�̏ꍇ�A
        //���ʌ�t��from����������������ɒǉ�����B
        //�������������� += "and delivery_date�@@>=�@@?"
        if (l_request.docuDeliDateFrom != null)
        {
            l_sbQueryString.append(" and delivery_date >= ? ");
        }

        //�W�j�@@����:�������.���ʌ�t��to != null�̏ꍇ�A
        //���ʌ�t��to����������������ɒǉ�����B
        //�������������� += "and delivery_date�@@<=�@@?"
        if (l_request.docuDeliDateTo != null)
        {
            l_sbQueryString.append(" and delivery_date <= ? ");
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �擾�����ɃZ�b�g����l�̔z��𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     * �@@����.�،���ЃR�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * <BR>
     *   �R-�P�j ����:�������. ���X�R�[�h�̒���=1 �̏ꍇ�A  <BR>
     *   ����:�������.���X�R�[�h[0]  ���P�j��List�ɒǉ�����B<BR>
     *   �R-�Q�j ����:�������. ���X�R�[�h�̒��� > 1�̏ꍇ�A<BR>
     *   ����:�������.���X�R�[�h�z��̑S�Ă̗v�f���P�j��List�ɒǉ�����B<BR> 
     * �@@����:�������.���X�R�[�h�̊e�v�f ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j�ڋq�R�[�h<BR>
     * <BR>
     * �@@����:�������.�ڋq�R�[�h != null�̏ꍇ<BR>
     * <BR>
     * �@@����:�������.�ڋq�R�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �T�j���ʋ敪�A���ʎ�ރR�[�h<BR>
     * �@@�T-�P�j ����:�������.���ʋ敪�Ǘ��ꗗ�̒��� = 1 �̏ꍇ�A<BR>
     * <BR>
     * �@@�@@���ʋ敪�A���ʎ�ރR�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �@@�T-�Q�j ����:�������.���ʋ敪�Ǘ��ꗗ�̒��� > 1 �̏ꍇ�A<BR>
     * �@@�@@�z��̑S�Ă̗v�f�� ���ʋ敪�A���ʎ�ރR�[�h���A�P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �U�j���ʌ�t��from<BR>
     * <BR>
     * �@@����:�������.���ʌ�t��from != null�̏ꍇ<BR>
     * <BR>
     * �@@����:�������.���ʌ�t��from ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �V�j���ʌ�t��to<BR>
     * <BR>
     * �@@����:�������.���ʌ�t��to != null�̏ꍇ<BR>
     * <BR>
     * �@@����:�������.���ʌ�t��to ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �W�j�������ꂽList����z����擾���A�ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_request - (�������)<BR>
     * �������<BR>
     * @@return Object[]
     * @@roseuid 46F2069C0049
     */
    private Object[] createQueryDataContainer(String l_strInstitutionCode, WEB3AdminFPTListReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(String, WEB3AdminFPTListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j���ArrayList�𐶐�����B
        List l_lisQueryContainers = new ArrayList();

        //�Q�j�،���ЃR�[�h
        //����.�،���ЃR�[�h ���P�j��List�ɒǉ�����B
        l_lisQueryContainers.add(l_strInstitutionCode);

		//   �R-�P�j ����:�������. ���X�R�[�h�̒���=1 �̏ꍇ�A
		//   ����:�������.���X�R�[�h[0]  ���P�j��List�ɒǉ�����B
		//   �R-�Q�j ����:�������. ���X�R�[�h�̒��� > 1�̏ꍇ�A<BR>
		//   ����:�������.���X�R�[�h�z��̑S�Ă̗v�f���P�j��List�ɒǉ�����B
		// �@@����:�������.���X�R�[�h�̊e�v�f ���P�j��List�ɒǉ�����B
    	if (l_request.branchCode.length == 1)
    	{
        	l_lisQueryContainers.add(l_request.branchCode[0]);
        } else if (l_request.branchCode.length > 1)
        {
			for (int i = 0; i < l_request.branchCode.length; i++)
			{
				l_lisQueryContainers.add(l_request.branchCode[i]);
			}
        }
        

        //�S�j�ڋq�R�[�h
        //   ����:�������.�ڋq�R�[�h != null�̏ꍇ
        //   ����:�������.�ڋq�R�[�h ���P�j��List�ɒǉ�����B
        if (l_request.acceptCode != null)
        {
            l_lisQueryContainers.add(l_request.acceptCode);
        }

        //���ʋ敪�A���ʎ�ރR�[�h
        // ����:�������.���ʋ敪�Ǘ��ꗗ�̒��� = 1 �̏ꍇ
        // ���ʋ敪�A���ʎ�ރR�[�h���P�j��List�ɒǉ�����
        if (l_request.documentDivList.length == 1)
        {
            l_lisQueryContainers.add(l_request.documentDivList[0].documentDiv);
            l_lisQueryContainers.add(l_request.documentDivList[0].documentCategory);
        }

        // ����:�������.���ʋ敪�Ǘ��ꗗ�̒��� > 1 �̏ꍇ
        // �z��̑S�Ă̗v�f�� ���ʋ敪�A���ʎ�ރR�[�h���A�P�j��List�ɒǉ�����B
        if (l_request.documentDivList.length > 1)
        {
            for (int i = 0; i < l_request.documentDivList.length; i++)
            {
                l_lisQueryContainers.add(l_request.documentDivList[i].documentDiv);
                l_lisQueryContainers.add(l_request.documentDivList[i].documentCategory);
            }
        }

        //�V�j���ʌ�t��from
        //  ����:�������.���ʌ�t��from != null�̏ꍇ
        //  ����:�������.���ʌ�t��from ���P�j��List�ɒǉ�����B
        if (l_request.docuDeliDateFrom != null)
        {
            l_lisQueryContainers.add(l_request.docuDeliDateFrom);
        }

        //�W�j���ʌ�t��to
        //����:�������.���ʌ�t��to != null�̏ꍇ
        //����:�������.���ʌ�t��to ���P�j��List�ɒǉ�����B
        if (l_request.docuDeliDateTo != null)
        {
            l_lisQueryContainers.add(l_request.docuDeliDateTo);
        }

        //�X�j�������ꂽList����z����擾���A�ԋp����B
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }

    /**
     * (create�\�[�g�L�[)<BR>
     * �\�[�g�����������ҏW����B<BR>
     * <BR>
     * �e�[�u���񕨗������g�p���A�ȉ��̒ʂ�A<BR>
     * �\�[�g����������iorderby��j��ҏW����B<BR>
     * <BR>
     * �P�j����:�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A<BR>
     * �@@�@@�@@�\�[�g������������쐬����B<BR>
     * �@@�P�|�P�j�\�[�g�L�[��ҏW<BR>
     * �@@�@@�P�|�P�|�P�j�\�[�g�����[ = ���X�R�[�h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���ʌ�t�Ǘ��e�[�u��.branch_code<BR>
     * <BR>
     * �@@�@@�P�|�P�|�Q�j�\�[�g�����[ = �ڋq�R�[�h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���ʌ�t�Ǘ��e�[�u��.account_code<BR>
     * <BR>
     * �@@�@@�P�|�P�|�R�j�\�[�g���� = ���ʌ�t���̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���ʌ�t�Ǘ��e�[�u��.delivery_date<BR>
     * <BR>
     * �@@�@@�P�|�P�|�S�j�\�[�g���� = ���ʎ�� �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���ʌ�t�Ǘ��e�[�u��.document_category<BR>
     * <BR>
     * �@@�@@�P�|�P�|�T�j�\�[�g���� = �d�q�������R�[�h �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���ʌ�t�Ǘ��e�[�u��.product_code<BR>
     * <BR>
     * �@@�@@�P�|�P�|�U�j�\�[�g���� = �݂Ȃ���t�� �̏ꍇ<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@���ʌ�t�Ǘ��e�[�u��.deemed_delivery_date <BR>
     * <BR>
     * �@@�P�|�Q�j�\�[�g�����ɊY������\�[�g������ҏW����B<BR>
     * �@@�@@�@@�@@�@@�����Fasc<BR>
     * �@@�@@�@@�@@�@@�~���Fdesc<BR>
     * <BR>
     * �@@�@@���P�|�P�|�U)�݂Ȃ���t�����Y������ꍇ�\�[�g�����͈ȉ��ݒ�Ƃ��邱�ƁB<BR>
     * �@@�@@�@@�@@�@@�����Fasc nulls first<BR>
     * �@@�@@�@@�@@�@@�~���Fdesc nulls last<BR>
     * <BR>
     * �Q�j �쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * @@return String
     * @@roseuid 46F206EF0287
     */
    private String createSortKey(WEB3AdminFPTSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = " createSortKey(WEB3AdminFPTSortKey)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortKey = new StringBuffer();

        //�P�j����:�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A�\�[�g������������쐬����B
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //�P�|�P�j�\�[�g�L�[��ҏW
            if (WEB3AdminFPTSortKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                //�P�|�P�|�P�j�\�[�g�����[ = ���X�R�[�h�̏ꍇ
                //  ���ʌ�t�Ǘ��e�[�u��.branch_code
                l_sbSortKey.append(" branch_code ");
            }
            else if (WEB3AdminFPTSortKeyItemDef.ACCEPT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //�P�|�P�|�Q�j�\�[�g�����[ = �ڋq�R�[�h�̏ꍇ
                //�@@�@@�@@�@@���ʌ�t�Ǘ��e�[�u��.account_code
                l_sbSortKey.append(" account_code ");
            }
            else if (WEB3AdminFPTSortKeyItemDef.DOCU_DELI_DATE.equals(l_sortKeys[i].keyItem))
            {
                //�P�|�P�|�R�j�\�[�g���� = ���ʌ�t���̏ꍇ
                //�@@�@@�@@�@@�@@���ʌ�t�Ǘ��e�[�u��.delivery_date
                l_sbSortKey.append(" delivery_date ");
            }
            else if (WEB3AdminFPTSortKeyItemDef.DOCUMENT_CATEGORY.equals(l_sortKeys[i].keyItem))
            {
                //�\�[�g���� = ���ʎ�� �̏ꍇ
                // ���ʌ�t�Ǘ��e�[�u��.document_category
                l_sbSortKey.append(" document_category ");
            }
            else if (WEB3AdminFPTSortKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //�\�[�g���� = �d�q�������R�[�h �̏ꍇ
                // ���ʌ�t�Ǘ��e�[�u��.product_code
                l_sbSortKey.append(" product_code ");
            }
            else if (WEB3AdminFPTSortKeyItemDef.DEEMED_DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
            {
                //�\�[�g���� = �݂Ȃ���t�� �̏ꍇ
                //���ʌ�t�Ǘ��e�[�u��.deemed_delivery_date
                l_sbSortKey.append(" deemed_delivery_date ");
            }
            else
            {
                continue;
            }

            //�P�|�Q�j�\�[�g�����ɊY������\�[�g������ҏW����B
            //�@@�@@�@@�����Fasc
            //�@@�@@�@@�~���Fdesc
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                if (WEB3AdminFPTSortKeyItemDef.DEEMED_DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
                {
                    //�݂Ȃ���t�����Y������ꍇ�\�[�g�����͈ȉ��ݒ�Ƃ��邱��
                    //�@@�����Fasc nulls first
                    l_sbSortKey.append(" ASC NULLS FIRST ");
                }
                else
                {
                    l_sbSortKey.append(" ASC ");
                }
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                if (WEB3AdminFPTSortKeyItemDef.DEEMED_DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
                {
                    //�݂Ȃ���t�����Y������ꍇ�\�[�g�����͈ȉ��ݒ�Ƃ��邱��
                    //�@@�~���Fdesc nulls last
                    l_sbSortKey.append(" DESC NULLS LAST ");
                }
                else
                {
                    l_sbSortKey.append(" DESC ");
                }
            }

            l_sbSortKey.append(" , ");
        }

        String l_strSortKey = l_sbSortKey.toString();

        if (!WEB3StringTypeUtility.isEmpty(l_strSortKey))
        {
            l_strSortKey = l_strSortKey.substring(0, l_strSortKey.length() - 2);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSortKey;
    }

    /**
     * (create�����@@��t�{�����)<BR>
     * �����@@��t�{���������C���X�^���X�𐶐�����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����@@��t�{���������C���X�^���X�𐶐�<BR>
     * <BR>
     * �Q�j�@@���ʃ`�F�b�N�敪�̎擾<BR>
     * �@@�Q�|�P�j�@@���ʋ敪�Ǘ��N���X�I�u�W�F�N�g�̐���<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h�F����:���ʌ�t�Ǘ�Params.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�F����:���ʌ�t�Ǘ�Params.���X�R�[�h<BR>
     * �@@���ʋ敪�F����:���ʌ�t�Ǘ�Params.���ʋ敪<BR>
     * �@@���ʎ�ރR�[�h�F����:���ʌ�t�Ǘ�Params.���ʎ�ރR�[�h<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@get���ʋ敪�Ǘ��e�[�u���s�i�j�ŏ��ʋ敪�Ǘ��e�[�u���s���擾<BR>
     * <BR>
     * �R�j�@@�ȉ��̂悤�ɁA�S�ۖ����o�^���C���X�^���X�ɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�����@@��t�{���������.���X�R�[�h =<BR>
     * �@@�@@����:���ʌ�t�Ǘ�Params.���X�R�[�h<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�����@@��t�{���������.�ڋq�R�[�h =<BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq�i�j.get�\���ڋq�R�[�h()<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[get�ڋq�i�j�̈���]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@����:���ʌ�t�Ǘ�Params.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���X�R�[�h�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@����:���ʌ�t�Ǘ�Params.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ڋq�R�[�h�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@����:���ʌ�t�Ǘ�Params.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�R�|�R�j�@@�����@@��t�{���������.�ڋq�� =<BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq�i�j.get�ڋq�\����()<BR>
     * <BR>
     * �@@�R�|�S�j�@@�����@@��t�{���������.������ = ""<BR>
     * <BR>
     * �@@�R�|�T�j�@@�����@@��t�{���������.���ʋ敪 =<BR>
     * �@@�@@����:���ʌ�t�Ǘ�Params.���ʋ敪<BR>
     * <BR>
     * �@@�R�|�U�j�@@�����@@��t�{���������.���ʖ��� =<BR>
     * �@@�@@�Q�j�Ŏ擾�����l.get���ʖ��́i�j<BR>
     * <BR>
     * �@@�R�|�V�j�@@�����@@��t�{���������.�d�q�������R�[�h =<BR>
     * �@@�@@����:���ʌ�t�Ǘ�Params.�����R�[�h<BR>
     * <BR>
     * �@@�R�|�W�j�@@�����@@��t�{���������.���ʎ�ރR�[�h =<BR>
     * �@@�@@����:���ʌ�t�Ǘ�Params.���ʎ�ރR�[�h<BR>
     * <BR>
     * �@@�R�|�X�j�@@�����@@��t�{���������.���ʎ�ޖ��� =<BR>
     * �@@�@@���ʎ�ފǗ�#.get���ʎ�ޖ���() �̖߂�l<BR>
     * �@@�@@�@@�@@[���ʎ�ފǗ�()�̈���]<BR>
     * �@@�@@�@@�@@�@@�،���ЃR�[�h �F ����:���ʌ�t�Ǘ�Params.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@���X�R�[�h �F ����:���ʌ�t�Ǘ�Params.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@���ʎ�ރR�[�h �F ����:���ʌ�t�Ǘ�Params.���ʎ�ރR�[�h<BR>
     * <BR>
     * �@@�R�|�P�O�j�@@�����@@��t�{���������.���ʎ�ޒʔ� =<BR>
     * �@@�@@�i����:���ʌ�t�Ǘ�Params.�����R�[�h)�̉E4��<BR>
     * <BR>
     * �@@�R�|�P�P�j�@@�����@@��t�{���������.���ʌ�t�� =<BR>
     * �@@�@@����:���ʌ�t�Ǘ�Params.���ʌ�t��<BR>
     * <BR>
     * �@@�R�|�P�Q�j�@@�����@@��t�{���������.�폜�t���O =<BR>
     * �@@�@@����:���ʌ�t�Ǘ�Params.�폜�t���O<BR>
     * <BR>
     * �@@�R�|�P�R�j�@@�����@@��t�{���������.�X�V�҃R�[�h =<BR>
     * �@@�@@����:���ʌ�t�Ǘ�Params.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�R�|�P�S�j�@@�����@@��t�{���������.�쐬���t =<BR>
     * �@@�@@����:���ʌ�t�Ǘ�Params.�쐬���t<BR>
     * <BR>
     * �@@�R�|�P�T�j�@@�����@@��t�{���������.�X�V���t =<BR>
     * �@@�@@����:���ʌ�t�Ǘ�Params.�X�V���t<BR>
     * <BR>
     * �@@�R�|�P�U�j�@@�����@@��t�{���������.�݂Ȃ���t�� =<BR>
     * �@@�@@����:���ʌ�t�Ǘ�Params.�݂Ȃ���t��<BR>
     * <BR>
     * <BR>
     * �S�j�@@�S�ۖ����o�^���C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_docDeliveryManagementParams - (���ʌ�t�Ǘ�Params�I�u�W�F�N�g)<BR>
     * ���ʌ�t�Ǘ�Params�I�u�W�F�N�g<BR>
     * @@param l_institution - (�،���ЃI�u�W�F�N�g)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@return WEB3FPTHistoryInfoUnit
     * @@throws WEB3BaseException
     * @@roseuid 46F35CAC02D5
     */
    private WEB3FPTHistoryInfoUnit createHistoryInfoUnit(
        DocDeliveryManagementParams l_docDeliveryManagementParams,
        WEB3GentradeInstitution l_institution) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createHistoryInfoUnit(DocDeliveryManagementParams, WEB3GentradeInstitution)";
        log.entering(STR_METHOD_NAME);

        if (l_docDeliveryManagementParams == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@�����@@��t�{���������C���X�^���X�𐶐�
        WEB3FPTHistoryInfoUnit l_fPTHistoryInfoUnit = new WEB3FPTHistoryInfoUnit();

        //�Q�j�@@���ʃ`�F�b�N�敪�̎擾
        //�Q�|�P�j�@@���ʋ敪�Ǘ��N���X�I�u�W�F�N�g�̐���
        WEB3AdminFPTDocDivManagement l_adminDocAdminFPTDocDivManagement =
            new WEB3AdminFPTDocDivManagement(
                l_docDeliveryManagementParams.getInstitutionCode(),
                l_docDeliveryManagementParams.getBranchCode(),
                l_docDeliveryManagementParams.getDocumentDiv(),
                l_docDeliveryManagementParams.getDocumentCategory());

        //�Q�|�Q�j�@@get���ʋ敪�Ǘ��e�[�u���s�i�j�ŏ��ʋ敪�Ǘ��e�[�u���s���擾
        DocDivManagementRow l_docDivManagementRow = l_adminDocAdminFPTDocDivManagement.getDocDivManagementParams();

        //�R�j�@@�ȉ��̂悤�ɁA�S�ۖ����o�^���C���X�^���X�ɒl���Z�b�g����B
        //�R�|�P�j�@@�����@@��t�{���������.���X�R�[�h = ����:���ʌ�t�Ǘ�Params.���X�R�[�h
        l_fPTHistoryInfoUnit.branchCode = l_docDeliveryManagementParams.getBranchCode();

        //�R�|�Q�j�@@�����@@��t�{���������.�ڋq�R�[�h = �g���A�J�E���g�}�l�[�W��.get�ڋq�i�j.get�\���ڋq�R�[�h()
		FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
		WEB3GentradeAccountManager l_accountMananger =
			(WEB3GentradeAccountManager)l_finApp.getAccountManager();
		 WEB3GentradeMainAccount l_mainAccount= l_accountMananger.getMainAccount(
			l_docDeliveryManagementParams.getInstitutionCode(),
			l_docDeliveryManagementParams.getBranchCode(),
			l_docDeliveryManagementParams.getAccountCode());
        l_fPTHistoryInfoUnit.acceptCode = l_mainAccount.getDisplayAccountCode();

        //�R�|�R�j�@@�����@@��t�{���������.�ڋq�� = �g���A�J�E���g�}�l�[�W��.get�ڋq�i�j.get�ڋq�\����()

		l_fPTHistoryInfoUnit.acceptName = l_mainAccount.getDisplayAccountName();

        //�R�|�S�j�@@�����@@��t�{���������.�d�q�������R�[�h = ����:���ʌ�t�Ǘ�Params.�����R�[�h
        l_fPTHistoryInfoUnit.batoProductCode = l_docDeliveryManagementParams.getProductCode();

        //�R�|�T�j�@@�����@@��t�{���������.������
//        WEB3AdminFPTCommon l_adminDocAdminFPTCommon = new WEB3AdminFPTCommon();
//        l_fPTHistoryInfoUnit.productName = l_adminDocAdminFPTCommon.getProductName(
//            l_institution,
//            l_docDeliveryManagementParams.getProductCode(),
//            l_docDivManagementRow.getDocumentCheckDiv());
		l_fPTHistoryInfoUnit.productName = "";

        //�R�|�U�j�@@�����@@��t�{���������.���ʋ敪 = ����:���ʌ�t�Ǘ�Params.���ʋ敪
        l_fPTHistoryInfoUnit.documentDiv = l_docDeliveryManagementParams.getDocumentDiv();

        //�R�|�V�j�@@�����@@��t�{���������.���ʖ��� = �Q�j�Ŏ擾�����l.get���ʖ��́i�j
        l_fPTHistoryInfoUnit.documentNames = l_docDivManagementRow.getDocumentName();

        //�����@@��t�{���������.���ʎ�ރR�[�h = ����:���ʌ�t�Ǘ�Params.���ʎ�ރR�[�h
        l_fPTHistoryInfoUnit.documentCategory = l_docDeliveryManagementParams.getDocumentCategory();

        //�����@@��t�{���������.���ʎ�ޖ��� = ���ʎ�ފǗ�#.get���ʎ�ޖ���() �̖߂�l
        // [���ʎ�ފǗ�()�̈���]
        //   �،���ЃR�[�h �F ����:���ʌ�t�Ǘ�Params.�،���ЃR�[�h
        //   ���X�R�[�h �F ����:���ʌ�t�Ǘ�Params.���X�R�[�h
        //   ���ʎ�ރR�[�h �F ����:���ʌ�t�Ǘ�Params.���ʎ�ރR�[�h
        WEB3AdminFPTDocCategoryManagement  l_docCategoryManagement =
            new WEB3AdminFPTDocCategoryManagement(
                l_docDeliveryManagementParams.getInstitutionCode(),
                new String[]{l_docDeliveryManagementParams.getBranchCode()},
                l_docDeliveryManagementParams.getDocumentCategory());
        l_fPTHistoryInfoUnit.documentCategoryName = l_docCategoryManagement.getDocumentCateName();

        //�����@@��t�{���������.���ʎ�ޒʔ� = �i����:���ʌ�t�Ǘ�Params.�����R�[�h)�̉E4��
        String l_strProductCode = l_docDeliveryManagementParams.getProductCode();
        l_fPTHistoryInfoUnit.documentCategoryNumber =
            l_strProductCode.substring(l_strProductCode.length() - 4);

        //�R�|�W�j�@@�����@@��t�{���������.���ʌ�t�� = ����:���ʌ�t�Ǘ�Params.���ʌ�t��
        l_fPTHistoryInfoUnit.docuDeliDate = l_docDeliveryManagementParams.getDeliveryDate();

        //�R�|�X�j�@@�����@@��t�{���������.�폜�t���O = ����:���ʌ�t�Ǘ�Params.�폜�t���O
        l_fPTHistoryInfoUnit.deleteFlg = l_docDeliveryManagementParams.getDeleteFlag().intValue() + "";

        //�R�|�P�O�j�@@�����@@��t�{���������.�X�V�҃R�[�h = ����:���ʌ�t�Ǘ�Params.�X�V�҃R�[�h
        l_fPTHistoryInfoUnit.updaterCode = l_docDeliveryManagementParams.getLastUpdater();

        //�R�|�P�P�j�@@�����@@��t�{���������.�쐬���t = ����:���ʌ�t�Ǘ�Params.�쐬���t
        l_fPTHistoryInfoUnit.createDate = l_docDeliveryManagementParams.getCreatedTimestamp();

        //�R�|�P�Q�j�@@�����@@��t�{���������.�X�V���t = ����:���ʌ�t�Ǘ�Params.�X�V���t
        l_fPTHistoryInfoUnit.updateTimeStamp = l_docDeliveryManagementParams.getLastUpdatedTimestamp();

        //�����@@��t�{���������.�݂Ȃ���t�� = ����:���ʌ�t�Ǘ�Params.�݂Ȃ���t��
        l_fPTHistoryInfoUnit.deemedDeliveryDate = l_docDeliveryManagementParams.getDeemedDeliveryDate();

        log.exiting(STR_METHOD_NAME);
        return l_fPTHistoryInfoUnit;
    }

    /**
     * (validate��������)<BR>
     * ���������̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j ����.�������.���ʋ敪�Ǘ��ꗗ �̒��� > 0 �̏ꍇ�A������LOOP����<BR>
     * <BR>
     * �@@�P-�P�j ���ʎ�ރR�[�h�`�F�b�N<BR>
     * �@@�@@�P-�P-�P�j ����.�������.���ʋ敪�Ǘ��ꗗ[index].���ʎ�ރR�[�h<BR>
     * �@@�@@�@@�@@!= null �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���ʎ�ފǗ��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[���ʎ�ފǗ�()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@���X�R�[�h�F ����.�������.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@���ʎ�ރR�[�h�F ����.�������.���ʋ敪�Ǘ��ꗗ[index]<BR>
     * �@@�@@�@@�@@�@@�@@.���ʎ�ރR�[�h<BR>
     * <BR>
     * �@@�@@�P-�P-�Q�j ���ʎ�ފǗ�#is���ʎ�ރR�[�h()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�߂�l�� false �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�u���ʎ�ރR�[�h���s���ł��B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02997<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * �،���ЃR�[�h
     * @@param l_request - (�������)
     * �������
     * @@throws WEB3BaseException
     */
    private void validateQueryCondition(
        String l_strInstitutionCode,
        WEB3AdminFPTListReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateQueryCondition(String, WEB3AdminFPTListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //����.�������.���ʋ敪�Ǘ��ꗗ �̒��� > 0 �̏ꍇ�A������LOOP����
        int l_intCnt = 0;
        if (l_request.documentDivList != null)
        {
            l_intCnt = l_request.documentDivList.length;
        }

        for (int i = 0; i < l_intCnt; i++)
        {
            //���ʎ�ރR�[�h�`�F�b�N
            //����.�������.���ʋ敪�Ǘ��ꗗ[index].���ʎ�ރR�[�h != null �̏ꍇ
            //���ʎ�ފǗ��I�u�W�F�N�g�𐶐�����B
            // [���ʎ�ފǗ�()�Ɏw�肷�����]
            //   �،���ЃR�[�h�F ����.�،���ЃR�[�h
            //   ���X�R�[�h�F ����.�������.���X�R�[�h
            //   ���ʎ�ރR�[�h�F ����.�������.���ʋ敪�Ǘ��ꗗ[index].���ʎ�ރR�[�h
            if (l_request.documentDivList[i] != null)
            {
                WEB3AdminFPTDocCategoryManagement l_docCategoryManagement =
                    new WEB3AdminFPTDocCategoryManagement(
                        l_strInstitutionCode,
                        l_request.branchCode,
                        l_request.documentDivList[i].documentCategory);

                // ���ʎ�ފǗ�#is���ʎ�ރR�[�h()���R�[������B
                //�߂�l�� false �̏ꍇ�́A�u���ʎ�ރR�[�h���s���ł��B�v��O���X���[����B
                if (!l_docCategoryManagement.isDocumentCategory())
                {
                    log.debug("���ʎ�ރR�[�h���s���ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���ʎ�ރR�[�h���s���ł��B");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
