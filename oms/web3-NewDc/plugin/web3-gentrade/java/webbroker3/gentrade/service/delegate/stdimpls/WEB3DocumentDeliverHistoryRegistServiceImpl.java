head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocumentDeliverHistoryRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʌ�t����o�^�T�[�r�XImpl(WEB3DocumentDeliverHistoryRegistServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 ���V��@@(SRA) �V�K�쐬
Revesion History : 2008/01/23 鰃L��(���u) �d�l�ύX ���f��No.309
Revesion History : 2008/01/26 �h�C (���u) �d�l�ύX�E���f��No.308�A312�A313��Ή�
Revesion History : 2008/01/31 �h�C (���u) �d�l�ύX�E�c�a�X�V�d�lNo.007��Ή�
*/
package webbroker3.gentrade.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DocumentCheckDivDef;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BatoProductManagementDao;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3DocumentDeliverHistoryRegistService;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ʌ�t����o�^�T�[�r�XImpl)<BR>
 * <BR>
 * ���ʌ�t����o�^�T�[�r�X�����N���X <BR>
 * @@author ���V��@@
 * @@version 1.0
 */
public class WEB3DocumentDeliverHistoryRegistServiceImpl 
    extends WEB3ClientRequestService implements WEB3DocumentDeliverHistoryRegistService 
{

    /**
     * (���O�o�̓��[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3DocumentDeliverHistoryRegistServiceImpl.class);
    
    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3DocumentDeliverHistoryRegistServiceImpl() 
    {
    }

    /**
     * (execute)<BR>
     * <BR>
     * ���ʌ�t����o�^�T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u���ʌ�t����o�^�v�Q�ƁB<BR>
     * <BR> 
     * @@param l_request ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3DocumentDeliverHistoryRegistRequest l_regRequest = 
            (WEB3DocumentDeliverHistoryRegistRequest) l_request;
        
        // validate()
        l_regRequest.validate();
        
        // get�㗝���͎�()
        Trader l_trader = this.getTrader();
        
        // createResponse()
        WEB3DocumentDeliverHistoryRegistResponse l_response = 
            (WEB3DocumentDeliverHistoryRegistResponse)l_regRequest.createResponse();
        
        // �㗝���͎҃C���X�^���X���擾�ł����ꍇ
        if (l_trader != null)
        {
            // �ȍ~�̏������s�Ȃ킸�A���X�|���X��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        WEB3GentradeBatoClientService l_service = 
            (WEB3GentradeBatoClientService) 
                Services.getService(WEB3GentradeBatoClientService.class);
        
        // ���N�G�X�g.�d�q���`�F�b�N�t���O == false �̏ꍇ
        if (!l_regRequest.eleBatoCheckFlg)
        {
            // �ȍ~�̏������s�Ȃ킸�A���X�|���X��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // ���N�G�X�g�D�����R�[�h������LOOP�������s��
        for (int i = 0; i < l_regRequest.productCode.length; i++)
        {
            // validate�d�q�������R�[�h
            validateBatoProductCode(l_regRequest.typeCode, l_regRequest.productCode[i]);

            WEB3GentradeProspectusResult l_result = null;
            try
            {
                // validate�ژ_�����{��
                l_result =
                    l_service.validateProspectus(l_regRequest.typeCode, l_regRequest.productCode[i]);
            }
            catch (WEB3BaseException l_ex)
            {
                // validate�ژ_�����{��()����O���X���[ �̏ꍇ
                if (l_ex instanceof WEB3BusinessLayerException)
                {
                    // "[�d�q���V�X�e����Q��]��Q�������s�B"�̏ꍇ
                    // ��O�����̂܂܃X���[����
                    if (WEB3ErrorCatalog.BUSINESS_ERROR_01984.equals(l_ex.getErrorInfo()))
                    {
                        throw l_ex;
                    }
                }
                // ��L�ȊO�̏ꍇ�ALOOP�����̎n�߂ɖ߂�
                continue;
            }

            // is�����@@����
            boolean l_blnFinancialProductsExchangeLawDocument =
                this.isFinancialProductsExchangeLawDocument(
                    l_regRequest.typeCode,
                    l_regRequest.productCode[i].substring(0, 3));

            // �ژ_�����{���`�F�b�N����.�`�F�b�N���� == �h�{�����ρh and
            // is�����@@����()�̖߂�l == true�̏ꍇ
            if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(l_result.checkResult)
                && l_blnFinancialProductsExchangeLawDocument)
            {
                // �u���ʖ���t�G���[�v�̗�O���X���[����B
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02940,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }

            // �ژ_�����{���`�F�b�N����.�`�F�b�N���� != �h�{���ρh�̏ꍇ
            if (!WEB3GentradeBatoCheckResultDef.INSPECTION.equals(l_result.checkResult))
            {
                // ���X�|���X�ԋp
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            // save��t����()
            this.saveDeliveryHistory(l_regRequest.typeCode, l_regRequest.productCode[i]);
        }

        log.exiting(STR_METHOD_NAME);
        
        // ���X�|���X�ԋp
        return l_response;
    }

    /**
     * (save��t����)<BR>
     * <BR>
     * ��t������o�^����B<BR>
     * <BR>
     * �P�j�ڋq�C���X�^���X���擾����B<BR>
     * <BR>
     * �@@�@@this.get����()���R�[������B<BR>
     * <BR>
     * �Q�j�ȉ��̏����ŏ��ʌ�t�Ǘ��e�[�u������������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@����ID = �ڋq.getAccountId()�̖߂�l<BR>
     * �@@�@@���ʋ敪 = ����.���ʋ敪<BR>
     * �@@�@@�����R�[�h = ����.�����R�[�h<BR>
     * �@@�@@���ʌ�t�� = ���ݓ����̓��t����<BR>
     * �@@�@@�폜�t���O = �h�L���h<BR>
     * <BR>
     * �@@�@@���R�[�h���擾�ł����ꍇ�́A�ȍ~�̏������s�킸�ɏI������B<BR>
     * <BR>
     * �R�j���ʌ�t�Ǘ��s�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �S�j�e���ڂɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�@@���ڍׂ́ADB�X�V�d�l�Q�ƁB<BR>
     * <BR>
     * �T�j���R�[�h��ǉ�����B<BR>
     * <BR>
     * �@@�@@WEB3DataAccessUtillity.insertRow() ���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@l_row �F ���������s�C���X�^���X<BR>
     * <BR>
     * @@param l_documentDiv ���ʋ敪�i��ʃR�[�h�j
     * @@param l_productCode �����R�[�h
     * @@throws WEB3BaseException
     */    
    protected void saveDeliveryHistory(String l_documentDiv, String l_productCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveDeliveryHistory(String, String)";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intSize;
        Date l_datBizDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        
        // �P�j�ڋq�C���X�^���X���擾����B
        // this.get����()���R�[������B
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)this.getMainAccount();
        
        // �Q�j�ȉ��̏����ŏ��ʌ�t�Ǘ��e�[�u������������B
        // [����]
        // ����ID = �ڋq.getAccountId()�̖߂�l
        // ���ʋ敪 = ����.���ʋ敪
        // �����R�[�h = ����.�����R�[�h
        // ���ʌ�t�� = ���ݓ����̓��t����
        // �폜�t���O = �h�L���h
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and document_div = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and delivery_date = to_date(?, 'yyyyMMdd') ");
        l_sbWhere.append(" and delete_flag = ? ");

        Object[] l_objWhere = {
            String.valueOf(l_mainAccount.getAccountId()),
            l_documentDiv,
            l_productCode,
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"),
            BooleanEnum.FALSE
        };
        
        List l_lisRecords = null;
        QueryProcessor l_queryProcessor;
        try 
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = 
                l_queryProcessor.doFindAllQuery(
                    DocDeliveryManagementRow.TYPE, 
                    l_sbWhere.toString(), 
                    l_objWhere); 

        } 
        catch (DataException e) 
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);

        } 
        
        l_intSize = l_lisRecords.size();

        // ���R�[�h���擾�ł����ꍇ�́A�ȍ~�̏������s�킸�ɏI������B
        if (l_intSize > 0)
        {
            log.debug("���R�[�h���擾�ł����ꍇ�́A�ȍ~�̏������s�킸�ɏI������B");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        // �R�j���ʌ�t�Ǘ��s�C���X�^���X�𐶐�����B
        DocDeliveryManagementParams l_params = new DocDeliveryManagementParams();
        
        // �S�j�e���ڂɒl���Z�b�g����B
        // �����h�c�F�ڋq.����ID
        l_params.setAccountId(l_mainAccount.getAccountId());
        
        // �،���ЃR�[�h�F�ڋq.�،���ЃR�[�h
        l_params.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
        
        // ���X�R�[�h�F�ڋq.���X�R�[�h
        l_params.setBranchCode(l_mainAccount.getBranch().getBranchCode());
        
        // �ڋq�R�[�h�F�ڋq.�����R�[�h
        l_params.setAccountCode(l_mainAccount.getAccountCode());
        
        // ���ʋ敪�F����.���ʋ敪
        l_params.setDocumentDiv(l_documentDiv);
        
        // �����R�[�h�F����.�����R�[�h
        l_params.setProductCode(l_productCode);
        
        // ���ʌ�t���F���ݓ����̓��t����
        l_params.setDeliveryDate(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
        
        // �폜�t���O�F�h�L���h
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        
        // �X�V�҃R�[�h�F�ڋq.�����R�[�h
        l_params.setLastUpdater(l_mainAccount.getAccountCode());
        
        // �쐬���t�F���ݓ���
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        // �X�V���t�F���ݓ���
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        // ���ʎ�ރR�[�h�F����.�����R�[�h��1���ڂ���3����
        l_params.setDocumentCategory(l_productCode.substring(0, 3));
        
        // �T�j���R�[�h��ǉ�����B
        try 
        {
            WEB3DataAccessUtility.insertRow(l_params);
        } 
        catch (DataException e) 
        {
            // ��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME);
        } 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is�����@@����)<BR>
     * <BR>
     * ���Y���ʂ������@@���ʂ��ǂ����𔻒肷��B<BR>
     * <BR>
     * �P�j�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@this.get����()���R�[������B<BR>
     * <BR>
     * �Q�j���ʋ敪�Ǘ��e�[�u����������@@���ʂ̏��ʋ敪���擾����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()�̖߂�l<BR>
     * �@@�@@���X�R�[�h = �ڋq.getBranch().getBranchCode()�̖߂�l<BR>
     * �@@�@@���ʃ`�F�b�N�敪 = �h�����@@�h<BR>
     * �@@�@@���ʒʔ� = �h0�h<BR>
     * �@@�@@���ʎ�ރR�[�h = ����.���ʎ�� <BR>
     * <BR>
     * �R�j����.���ʋ敪 == �Q�j�Ŏ擾�������ʋ敪 �̏ꍇ��true���A<BR>
     * �@@�@@�����łȂ��ꍇ��false��ԋp����B<BR>
     * <BR>
     * @@param l_documentDivision ���ʋ敪
     * @@param l_strDocumentType ���ʎ��
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isFinancialProductsExchangeLawDocument(String l_documentDiv, String l_strDocumentType)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "isFinancialProductsExchangeLawDocument(String, String)";
        
        log.entering(STR_METHOD_NAME);

        // �P�j�ڋq�I�u�W�F�N�g���擾����
        //  this.get����()���R�[������B
        WEB3GentradeMainAccount l_account = 
            (WEB3GentradeMainAccount)this.getMainAccount();

        // �Q�j���ʋ敪�Ǘ��e�[�u����������@@���ʂ̏��ʋ敪���擾����B
        // [����]
        // �،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()�̖߂�l
        // ���X�R�[�h = �ڋq.getBranch().getBranchCode()�̖߂�l
        // ���ʃ`�F�b�N�敪 = �h�����@@�h
        // ���ʒʔ� = �h0�h
        // ���ʎ�ރR�[�h = ����.���ʎ��
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and document_check_div = ? ");
        l_sbWhere.append(" and document_number = ? ");
        l_sbWhere.append(" and document_category = ? ");

        Object[] l_objWhere = {
            l_account.getInstitution().getInstitutionCode(),
            l_account.getBranch().getBranchCode(),
            WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW,
            "0",
            l_strDocumentType
        };
        
        List l_lisRecords = null;
        QueryProcessor l_queryProcessor;
        
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = 
                l_queryProcessor.doFindAllQuery(
                    DocDivManagementRow.TYPE, 
                    l_sbWhere.toString(), 
                    l_objWhere); 

        }
        catch(DataException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        
        if (l_lisRecords.size() < 1)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        DocDivManagementRow l_docDivManagementRow = 
            (DocDivManagementRow)l_lisRecords.get(0);

        // �R�j����.���ʋ敪 == �Q�j�Ŏ擾�������ʋ敪 �̏ꍇ��true���A
        //    �����łȂ��ꍇ��false��ԋp����B
        if (l_documentDiv.equals(l_docDivManagementRow.getDocumentDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
    }

    /**
     * (validate�d�q�������R�[�h)<BR>
     * <BR>
     * �d�q�������R�[�h���L�����ǂ������m�F����B<BR>
     * <BR>
     * �P�j�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@this.get����()���R�[������B<BR>
     * <BR>
     * �Q�j�ȉ��̏����œd�q�������R�[�h�Ǘ��e�[�u������������B <BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()�̖߂�l <BR>
     * �@@�@@���X�R�[�h = �ڋq.getBranch().getBranchCode()�̖߂�l <BR>
     * �@@�@@���ʋ敪 = ����.���ʋ敪 <BR>
     * �@@�@@�d�q�������R�[�h = ����.�d�q�������R�[�h <BR>
     * <BR>
     * �R�j�擾�������R�[�h�̗L���敪���u1:invalid�v�̏ꍇ�A<BR>
     * �@@�@@�u�d�q�������R�[�h�������ł��v�̗�O���X���[����B<BR>
     * <BR>
     * @@param l_strDocumentDivision ���ʋ敪
     * @@param l_strBatoProductCode �d�q�������R�[�h
     * @@throws WEB3BaseException
     */
    protected void validateBatoProductCode(String l_strDocumentDivision, String l_strBatoProductCode)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validateBatoProductCode(String, String)";

        log.entering(STR_METHOD_NAME);

        // �P�j�ڋq�I�u�W�F�N�g���擾����
        //  this.get����()���R�[������B
        WEB3GentradeMainAccount l_account =
            (WEB3GentradeMainAccount)this.getMainAccount();

        // �Q�j�ȉ��̏����œd�q�������R�[�h�Ǘ��e�[�u������������
        // [����]
        // �،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()�̖߂�l
        // ���X�R�[�h = �ڋq.getBranch().getBranchCode()�̖߂�l
        // ���ʋ敪 = ����.���ʋ敪
        // �d�q�������R�[�h = ����.�d�q�������R�[�h
        BatoProductManagementRow l_row = null;

        try
        {
            l_row = BatoProductManagementDao.findRowByPk(
                l_account.getInstitution().getInstitutionCode(),
                l_account.getBranch().getBranchCode(),
                l_strDocumentDivision,
                l_strBatoProductCode
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }


        //�擾�������R�[�h�̗L���敪���u1:invalid�v�̏ꍇ�A
        //�u�d�q�������R�[�h�������ł��v�̗�O���X���[����B
        String l_strValidFlag = l_row.getValidFlag();

        if (WEB3ValidFlagDef.INVALID.equals(l_strValidFlag))
        {
            log.debug("�d�q�������R�[�h�������ł�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03004,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q�������R�[�h�������ł�");

        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
