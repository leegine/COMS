head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.30.05.57.32;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d92c6286688;
filename	WEB3FPTDocumentGetServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (��)��a�����r�W�l�X�E�C�m�x�[�V����
 File Name           : �����@@���ʏ��擾�T�[�r�XImpl�N���X(WEB3FPTDocumentGetServiceImpl.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/17 �����C(�k�����u) �V�K�쐬 �d�l�ύX���f��No.354
 */
package webbroker3.gentrade.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DocumentCheckDivDef;
import webbroker3.common.define.WEB3DocumentDeliverDivDef;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocCategoryManagementRow;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.gentrade.message.WEB3FPTDocumentGetRequest;
import webbroker3.gentrade.message.WEB3FPTDocumentGetResponse;
import webbroker3.gentrade.message.WEB3FPTDocumentInfoUnit;
import webbroker3.gentrade.service.delegate.WEB3FPTDocumentGetService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����@@���ʏ��擾�T�[�r�XImpl)<BR>
 * �����@@���ʏ��擾�T�[�r�XImpl�N���X<BR>
 * <BR>
 * @@author �����C(�k�����u)
 * @@version 1.0
 */
public class WEB3FPTDocumentGetServiceImpl extends WEB3ClientRequestService
    implements WEB3FPTDocumentGetService
{
    /**
     * (���O�o�̓��[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FPTDocumentGetServiceImpl.class);

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FPTDocumentGetServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * <BR>
     * �����@@���ʏ��擾�T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�����@@���ʏ��擾�v�Q�ƁB<BR>
     * <BR> 
     * @@param l_request ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
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

        WEB3FPTDocumentGetRequest l_fptDocumentGetRequest = null;

        if (l_request instanceof WEB3FPTDocumentGetRequest)
        {
            l_fptDocumentGetRequest =
                (WEB3FPTDocumentGetRequest)l_request;

            l_fptDocumentGetRequest.validate();
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

        //get����( )
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)this.getMainAccount();

        //���ʎ�ނ��擾����
        //���ʎ�ފǗ��e�[�u�������L�����Ō�������B
        //[����]
        //�،���ЃR�[�h = �ڋq.�،���ЃR�[�h
        //���X�R�[�h = �ڋq.���X�R�[�h
        //��t�Ώ� = ���N�G�X�g.��t�Ώ�
        List l_lisDocCategoryManagements = null;

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and delivery_target = ? ");

        Object[] l_objWhere = {
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_fptDocumentGetRequest.deliveryTarget
        };

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisDocCategoryManagements = l_queryProcessor.doFindAllQuery(
                DocCategoryManagementRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
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

        //�擾�������ʎ�ފǗ��e�[�u���̌���
        int l_intDocCategoryManagementsLength =l_lisDocCategoryManagements.size();

        //���ʏ��
        List l_lisFPTDocumentInfoUnits = new ArrayList();

        //�擾�������ʎ�ފǗ��e�[�u���̌��������[�v����
        for (int i = 0; i < l_intDocCategoryManagementsLength; i++)
        {
            //���ʋ敪���擾����
            //���ʋ敪�Ǘ��e�[�u�����珑�ʋ敪���擾����B
            //[����]
            //�،���ЃR�[�h = �ڋq.�،���ЃR�[�h
            //���X�R�[�h = �ڋq.���X�R�[�h
            //���ʃ`�F�b�N�敪 = �h�����@@�h
            //���ʒʔ� = 0
            //���ʎ�ރR�[�h = �擾�������ʎ��
            List l_lisDocDivManagementRows = null;

            StringBuffer l_sbWhereDocDivManagementRow = new StringBuffer();
            l_sbWhereDocDivManagementRow.append(" institution_code = ?");
            l_sbWhereDocDivManagementRow.append(" and branch_code = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_check_div = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_number = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_category = ? ");

            Object[] l_valueDocDivManagementRows = new Object[5];
            DocCategoryManagementRow l_docCategoryManagementRow =
                (DocCategoryManagementRow)l_lisDocCategoryManagements.get(i);
            l_valueDocDivManagementRows[0] = l_mainAccount.getInstitution().getInstitutionCode();
            l_valueDocDivManagementRows[1] = l_mainAccount.getBranch().getBranchCode();
            l_valueDocDivManagementRows[2] = WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW;
            l_valueDocDivManagementRows[3] = "0";
            l_valueDocDivManagementRows[4] = l_docCategoryManagementRow.getDocumentCategory();

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisDocDivManagementRows = l_queryProcessor.doFindAllQuery(
                    DocDivManagementRow.TYPE,
                    l_sbWhereDocDivManagementRow.toString(),
                    l_valueDocDivManagementRows);
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

            //�����R�[�h���擾�ł��Ȃ������ꍇ�́A���[�v�̎n�߂ɖ߂�B
            if (l_lisDocDivManagementRows.isEmpty())
            {
                continue;
            }

            //�d�q�������R�[�h���擾����
            //�d�q�������R�[�h�Ǘ��e�[�u������d�q�������R�[�h���擾����
            //[����]
            //�،���ЃR�[�h = �ڋq.�،���ЃR�[�h
            //���X�R�[�h = �ڋq.���X�R�[�h
            //���ʋ敪 = �擾�������ʋ敪
            //�d�q�������R�[�h��1�`3������ = �擾�������ʎ�� 
            //�L���敪 = �u0�Fvalid�v
            List l_lisBatoProductManagements = null;

            StringBuffer l_sbWhereBatoProductManagement = new StringBuffer();
            l_sbWhereBatoProductManagement.append(" institution_code = ? ");
            l_sbWhereBatoProductManagement.append(" and branch_code = ? ");
            l_sbWhereBatoProductManagement.append(" and document_div = ? ");
            l_sbWhereBatoProductManagement.append(" and substr(bato_product_code, 1, 3) = ? ");
            l_sbWhereBatoProductManagement.append(" and valid_flag = ? ");

            Object[] l_valueBatoProductManagements = new Object[5];
            DocDivManagementRow l_docDivManagementRow =
                (DocDivManagementRow)l_lisDocDivManagementRows.get(0);
            l_valueBatoProductManagements[0] = l_mainAccount.getInstitution().getInstitutionCode();
            l_valueBatoProductManagements[1] = l_mainAccount.getBranch().getBranchCode();
            l_valueBatoProductManagements[2] = l_docDivManagementRow.getDocumentDiv();
            l_valueBatoProductManagements[3] = l_docCategoryManagementRow.getDocumentCategory();
            l_valueBatoProductManagements[4] = WEB3ValidFlagDef.VALID;

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisBatoProductManagements = l_queryProcessor.doFindAllQuery(
                    BatoProductManagementRow.TYPE,
                    l_sbWhereBatoProductManagement.toString(),
                    l_valueBatoProductManagements);
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

            //�����R�[�h���擾�ł��Ȃ������ꍇ�́A���[�v�̎n�߂ɖ߂�B
            if (l_lisBatoProductManagements.isEmpty())
            {
                continue;
            }

            //�����������R�[�h���擾�ł����ꍇ�́A
            //�Ɩ��G���[�u�L���ȓd�q�������R�[�h���������݂��܂��B�v�̗�O���X���[����B
            if (l_lisBatoProductManagements.size() > 1)
            {
                log.debug("�L���ȓd�q�������R�[�h���������݂��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�L���ȓd�q�������R�[�h���������݂��܂��B");
            }

            BatoProductManagementRow l_batoProductManagementRow =
                (BatoProductManagementRow)l_lisBatoProductManagements.get(0);

            //�ڋq.is���ʌ�t���R�[������
            //[����]
            //���ʋ敪 = �擾�������ʋ敪
            //�����R�[�h = �擾�����d�q�������R�[�h
            boolean l_blnIsDocumentDelivery =
                l_mainAccount.isDocumentDelivery(
                    l_docDivManagementRow.getDocumentDiv(),
                    l_batoProductManagementRow.getBatoProductCode());

            //�v���p�e�B�Z�b�g
            WEB3FPTDocumentInfoUnit l_fptDocumentInfoUnit = new WEB3FPTDocumentInfoUnit();

            //���ʋ敪 �� �擾�������ʋ敪
            l_fptDocumentInfoUnit.documentDiv = l_docDivManagementRow.getDocumentDiv();

            //���ʎ�� �� �擾�������ʎ��
            l_fptDocumentInfoUnit.documentCategory = l_docCategoryManagementRow.getDocumentCategory();

            //�d�q�������R�[�h �� �擾�����d�q�������R�[�h
            l_fptDocumentInfoUnit.batoProductCode = l_batoProductManagementRow.getBatoProductCode();

            //��t�σt���O
            if (l_blnIsDocumentDelivery)
            {
                //is���ʌ�t���\�b�h�̖߂�l��true�̏ꍇ�A �u1�F��t�ρv
                l_fptDocumentInfoUnit.deliverFlag = WEB3DocumentDeliverDivDef.DELIVERY;
            }
            else
            {
                //is���ʌ�t���\�b�h�̖߂�l��false�̏ꍇ�A�u0�F��t���ρv
                l_fptDocumentInfoUnit.deliverFlag = WEB3DocumentDeliverDivDef.UNDELIVERY;
            }

            //���ʏ����ꌏ�ǉ�����
            l_lisFPTDocumentInfoUnits.add(l_fptDocumentInfoUnit);
        }

        //�����������ʏ������^�[������
        WEB3FPTDocumentInfoUnit[] l_fptDocumentInfoUnits =
            new WEB3FPTDocumentInfoUnit[l_lisFPTDocumentInfoUnits.size()];

        l_lisFPTDocumentInfoUnits.toArray(l_fptDocumentInfoUnits);

        // createResponse()
        WEB3FPTDocumentGetResponse l_response = 
            (WEB3FPTDocumentGetResponse)l_request.createResponse();

        l_response.documentList = l_fptDocumentInfoUnits;
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�ԋp
        return l_response;
    }
}
@
