head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����E���z���o�^�`�[�쐬�T�[�r�XImpl(WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 �Ӑ�(���u) �V�K�쐬 ���f��No.056�A066�A067�A070�A071�A072�A074
Revision History    : 2007/06/08 ���G��(���u) �C�� ���f��No.076�A077
Revision History    : 2007/06/11 ���g(���u) �C�� ���f��No.078
Revision History    : 2007/06/13 ���G��(���u) �C�� ���f��No.081
Revision History    : 2007/06/14 ���n�m(���u) �C�� ���f��No.082�A085
Revision History    : 2007/06/15 ���؎q(���u) �C�� ���f��No.089�ANo.090
Revision History    : 2007/06/22 ����(SCS) �C�� ���f��No.094
Revision History    : 2007/06/22 ����(SCS) �C�� ���f��No.096
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.DirectDebitRow;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.inform.WEB3AdminInformTransferApplyFinancialInstitutionVoucher;
import webbroker3.inform.WEB3AdminInformTransferApplyPostVoucher;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.define.WEB3InformFundCodeDef;
import webbroker3.inform.define.WEB3InformProductFirstDef;
import webbroker3.inform.define.WEB3InformRegistDivDef;
import webbroker3.inform.define.WEB3InformVoucherDataSendDivDef;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInfo;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistTransferInfo;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpResponse;
import webbroker3.inform.message.WEB3InformAddInfoUnit;
import webbroker3.inform.message.WEB3InformDetailHeaderInfoUnit;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistRegistVoucherMakeService;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����E���z���o�^�`�[�쐬�T�[�r�XImpl)<BR>
 * �����E���z���o�^�`�[�쐬�T�[�r�X�����N���X<BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformProfDistRegistVoucherMakeServiceImpl
    implements WEB3AdminInformProfDistRegistVoucherMakeService
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class);

    /**
     * @@roseuid 4663A9D5037A
     */
    public WEB3AdminInformProfDistRegistVoucherMakeServiceImpl()
    {

    }

    /**
     * �A����񌟍��T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * <BR>
     * get�o�^�󋵌�����ʁi�j<BR>
     * get�`�[�쐬���͉�ʁi�j<BR>
     * validate�`�[�쐬�m�F�i�j<BR>
     * submit�`�[�쐬�����i�j<BR>
     * get�U�֌������Q�Ɖ�ʁi�j<BR>
     * validate�������ύX�m�F�i�j<BR>
     * submit�������ύX�����i�j<BR>
     * validate����������m�F�i�j<BR>
     * submit��������������i�j<BR>
     * <BR>
     * ��L���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4642690A0264
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminInformProfDistStatusSearchInputRequest)
        {
            //get�o�^�󋵌������
            l_response = this.getRegistStatusSearchScreen((WEB3AdminInformProfDistStatusSearchInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherMakeInpRequest)
        {
            //get�`�[�쐬���͉��
            l_response = this.getVoucherMakeInpScreen((WEB3AdminInformProfDistVoucherMakeInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherMakeCnfRequest)
        {
            //validate�`�[�쐬�m�F
            l_response = this.validateVoucherMakeCnf((WEB3AdminInformProfDistVoucherMakeCnfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherMakeCmpRequest)
        {
            //submit�`�[�쐬����
            l_response = this.submitVoucherMakeCmp((WEB3AdminInformProfDistVoucherMakeCmpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherInfoRefRequest)
        {
            //get�U�֌������Q�Ɖ��
            l_response = this.getTransferAccountInfoRefScreen((WEB3AdminInformProfDistVoucherInfoRefRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherChgCnfRequest)
        {
            //validate�������ύX�m�F
            l_response = this.validateAccountInfoChgCnf((WEB3AdminInformProfDistVoucherChgCnfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherChgCmpRequest)
        {
            //submit�������ύX����
            l_response = this.submitAccountInfoChgCmp((WEB3AdminInformProfDistVoucherChgCmpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherCancCnfRequest)
        {
            //validate����������m�F
            l_response = this.validateAccountInfoCancCnf((WEB3AdminInformProfDistVoucherCancCnfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherCancCmpRequest)
        {
            //submit�������������
            l_response = this.submitAccountInfoCancCmp((WEB3AdminInformProfDistVoucherCancCmpRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�o�^�󋵌������)<BR>
     * �����E���z���o�^�󋵖⍇����ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget�o�^�󋵌�����ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistStatusSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 46426B50002A
     */
    public WEB3AdminInformProfDistStatusSearchInputResponse getRegistStatusSearchScreen(
        WEB3AdminInformProfDistStatusSearchInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRegistStatusSearchScreen(WEB3AdminInformProfDistStatusSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        // getInstanceFrom���O�C�����()
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU,
            false);

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminInformProfDistStatusSearchInputResponse l_response =
            (WEB3AdminInformProfDistStatusSearchInputResponse)l_request.createResponse();

        //���X�|���X.�O�c�Ɠ� = ���ݓ����̑O�c�Ɠ����t
        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();
        l_response.previousBizDate = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);

        //���X�|���X.�O�� = ���ݓ����̑O��
        l_response.previousDate = WEB3DateUtility.toDay(WEB3DateUtility.addDay(l_tsCurrentDate, -1));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�`�[�쐬���͉��)<BR>
     * �����E���z���U�֌����`�[�쐬���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget�`�[�쐬���͉�ʁv �Q�ƁB<BR>
     * ===============================================<BR>
     * �@@�V�[�P���X�}�@@:get�`�[�쐬���͉��<BR>
     * �@@��̈ʒu�@@�@@�@@:1.64�@@�����U�֓o�^�}�X�^�̒l���������A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���R�[�h���擾�ł��Ȃ��ꍇ�A��O���X���[<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag:�@@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistVoucherMakeInpResponse
     * @@roseuid 46426D820143
     */
    public WEB3AdminInformProfDistVoucherMakeInpResponse getVoucherMakeInpScreen(
        WEB3AdminInformProfDistVoucherMakeInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getVoucherMakeInpScreen(WEB3AdminInformProfDistVoucherMakeInpRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        WEB3AdminInformProfDistVoucherMakeInpResponse l_response = null;

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //�����`�F�b�N���s���B
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU,
            false);

        //validate������t�\( )
        //������t�\�����`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N
        l_request.validate();

        //���N�G�X�g�f�[�^.�o�^�敪 = 1�F�V�K�@@�̏ꍇ�A�ȉ��̏��������s
        if (WEB3InformRegistDivDef.REGISTRATION.equals(l_request.registDiv))
        {
            l_response = (WEB3AdminInformProfDistVoucherMakeInpResponse)l_request.createResponse();
        }
        else if (WEB3InformRegistDivDef.DELETE.equals(l_request.registDiv))
        {
            //���N�G�X�g�f�[�^.�o�^�敪 = 3�F�폜�̏ꍇ�A�ȉ��̏��������s
            //�،���ЃR�[�h���擾����B
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            //�����U�֓o�^�}�X�^�̌�������������𐶐�����B
            String l_strWhere = this.createQueryString(l_request);

            //���N�G�X�g�f�[�^����A�擾�����̃f�[�^���X�g�𐶐�����
            Object[] l_objWhereValue = this.createQueryDataContainer(l_strInstitutionCode, l_request);

            //�����U�֓o�^�}�X�^�̒l���������A�擾����
            //[����]
            //arg0 �F �����U�֓o�^�}�X�^Row.TYPE
            //arg1 �F create��������������̖߂�l
            //arg2 �F create���������f�[�^�R���e�i�̖߂�l
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                List l_lisDirectDebitRows =
                    l_queryProcessor.doFindAllQuery(DirectDebitRow.TYPE, l_strWhere, l_objWhereValue);

                if (l_lisDirectDebitRows == null || l_lisDirectDebitRows.isEmpty())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                        WEB3Inform.class.getName() + STR_METHOD_NAME,
                        "�Y������f�[�^�����݂��܂���B");
                }

                //�����E���z���E���p����U������(�����U�֓o�^�}�X�^Row)
                WEB3AdminInformProfDistTransferInfo l_transferInfo =
                    new WEB3AdminInformProfDistTransferInfo((DirectDebitRow)l_lisDirectDebitRows.get(0));

                // �ڋq���i�����j�A�ڋq���i�J�i�j���Z�b�g���鏈����ǉ� 2007.06.26 SCS����--------start-----
                this.setAccountInfo(l_strInstitutionCode, l_transferInfo);
                // -----------end-----------------------------------------------------------------------
                
                //���X�|���X�f�[�^�̐���
                l_response = (WEB3AdminInformProfDistVoucherMakeInpResponse)l_request.createResponse();

                //�ȉ��̒ʂ�ɁA�v���p�e�B���Z�b�g����
                //�U������ �F�������������E���z���E���p��
                l_response.transferInfo = l_transferInfo;
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�`�[�쐬�m�F)<BR>
     * �����E���z���U�֌����`�[�쐬�m�F��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate�`�[�쐬�m�F�v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistVoucherMakeCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 46426F0502D9
     */
    public WEB3AdminInformProfDistVoucherMakeCnfResponse validateVoucherMakeCnf(
        WEB3AdminInformProfDistVoucherMakeCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateVoucherMakeCnf(WEB3AdminInformProfDistVoucherMakeCnfRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        //�Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //�����`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "A0105"
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU,
            true);

        //������t�\�����`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N���s��
        l_request.validate();

        //�e��A���C���X�^���X�𐶐�����
        WEB3Inform l_inform = new WEB3Inform(l_request.informInfoUnit);

        //�e��A�����̓��̓`�F�b�N���s��
        l_inform.validateInformDetailInfoUnit();

        //�����U�֓o�^�}�X�^�[���R�[�h�Ƃ̐������`�F�b�N
        //[����]
        //�A�����F ���N�G�X�g�f�[�^.�A�����
        this.validateTransferRegistInfo(l_request.informInfoUnit);

        //�����R�[�h�̑Ó����`�F�b�N���s��
        //[����]
        //�A�����F ���N�G�X�g�f�[�^.�A�����
        this.validateProductCode(l_request.informInfoUnit);

        //�t�������擾����
        WEB3InformAddInfoUnit l_informAddInfoUnit = l_inform.getInformAddInfoUnit();

        //�e����Z�@@�֏��( )(�e����Z�@@�֏��::�e����Z�@@�֏��)
        WEB3AdminInformProfDistSellTransSrcInfo l_adminInformProfDistSellTransSrcInfo =
            new WEB3AdminInformProfDistSellTransSrcInfo();

        //�A�����.�敪�T = 1�F��s�U�� �̏ꍇ�A�ȉ��̏��������s
        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_request.informInfoUnit.div5))
        {
            //�U�֐\���i��s�j�`�[���쐬����ۂ̃p�����[�^�`�F�b�N���s���B
            //�m�����n
            //�A�����F�@@���N�G�X�g�f�[�^.�A�����
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_adminInformTransferApplyFinancialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher();
            l_adminInformTransferApplyFinancialInstitutionVoucher.validateFinancialInstitutionInfo(
                l_request.informInfoUnit);

            //���Z�@@�ցi��s�j�}�X�^������Z�@@�֏����擾����B
            //[����]
            //���Z�@@�փR�[�h�F �A�����.�R�[�h�R
            //�x�X�R�[�h�F �A�����.�R�[�h�S
            //���Z�@@�֏��F ���������e����Z�@@�֏��I�u�W�F�N�g
            this.getFinancialInstitutionInfo(
                l_request.informInfoUnit.code3,
                l_request.informInfoUnit.code4,
                l_adminInformProfDistSellTransSrcInfo);
        }
        else if (WEB3TransferDivDef.POSTAL_TRANSFER.equals(l_request.informInfoUnit.div5))
        {
            //�A�����.�敪�T = 5�F�X���U�� �̏ꍇ�A�ȉ��̏��������s

            //�U�֐\���i�X���j�`�[���쐬����ۂ̃p�����[�^�`�F�b�N���s���B
            //�m�����n
            //�A�����F�@@���N�G�X�g�f�[�^.�A�����
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher();
            l_adminInformTransferApplyPostVoucher.validatePostInfo(l_request.informInfoUnit);
        }

        //���X�|���X�I�u�W�F�N�g�̐���
        WEB3AdminInformProfDistVoucherMakeCnfResponse l_response =
            (WEB3AdminInformProfDistVoucherMakeCnfResponse)l_request.createResponse();

        //�t����� = get�t�����i�j�̖߂�l
        l_response.informAddUnit = l_informAddInfoUnit;
        //���Z�@@�֏�� = �e����Z�@@�֏��I�u�W�F�N�g
        l_response.financialInstitutionInfo = l_adminInformProfDistSellTransSrcInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�`�[�쐬����)<BR>
     * �����E���z���U�֌����`�[�쐬������ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit�`�[�쐬�����v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistVoucherMakeCmpResponse
     * @@throws WEB3BaseException
     * @@roseuid 464270740088
     */
    public WEB3AdminInformProfDistVoucherMakeCmpResponse submitVoucherMakeCmp(
        WEB3AdminInformProfDistVoucherMakeCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitVoucherMakeCmp(WEB3AdminInformProfDistVoucherMakeCmpRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "A0105"
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU,
            true);

        //validate����p�X���[�h(�p�X���[�h : String)
        //����p�X���[�h�����������̃`�F�b�N���s���B
        //[����]
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //validate������t�\()
        //������t�\�����`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate()
        //���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N
        l_request.validate();

        //�e��A��(�e��A�����)
        //�e��A���C���X�^���X�𐶐�����B
        //[����]
        //�A�����F ���N�G�X�g�f�[�^.�A�����
        WEB3Inform l_inform = new WEB3Inform(l_request.informInfoUnit);

        //validate�e��A�����()
        //�e��A�����̓��̓`�F�b�N���s��
        l_inform.validateInformDetailInfoUnit();

        //validate�U����o�^���(�e��A�����)
        //�����U�֓o�^�}�X�^�[���R�[�h�Ƃ̐������`�F�b�N
        //[����]
        //�A�����F ���N�G�X�g�f�[�^.�A�����
        this.validateTransferRegistInfo(l_request.informInfoUnit);

        //validate�����R�[�h(�e��A�����)
        //�����R�[�h�̑Ó����`�F�b�N���s���B
        //[����]
        //�A�����F ���N�G�X�g�f�[�^.�A�����
        this.validateProductCode(l_request.informInfoUnit);

        //get�V�K���ʃR�[�h(String, String)
        //(�A���Ǘ����ʃR�[�h�̔ԃT�[�r�XImpl::get�V�K���ʃR�[�h)
        //�V�K���ʃR�[�h���擾����B
        //[����]
        //�،���ЃR�[�h�F ���N�G�X�g�f�[�^.�A�����.�،���ЃR�[�h
        //�A����ʁF ���N�G�X�g�f�[�^.�A�����.�A�����
        WEB3InformHostReqOrderNumberManageService l_informHostReqOrderNumberManageService =
            (WEB3InformHostReqOrderNumberManageService)Services.getService(
                WEB3InformHostReqOrderNumberManageService.class);
        String l_strNewOrderRequestCode = l_informHostReqOrderNumberManageService.getNewOrderRequestCode(
            l_request.informInfoUnit.institutionCode,
            l_request.informInfoUnit.informType);

        //get�Ǘ��҃R�[�h( )
        //�Ǘ��҃R�[�h���擾����
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //saveNew�e��A��(String, String, String)(�e��A��::saveNew�e��A��)
        //�e��A���e�[�u���Ƀf�[�^��o�^����B
        //[����]
        //�X�V�҃R�[�h�F get�Ǘ��҃R�[�h�i�j�̖߂�l
        //���ʃR�[�h�F get�V�K���ʃR�[�h�i�j�̖߂�l
        //�쐬�󋵁F�@@0:���쐬
        l_inform.saveNewInform(
            l_strAdministratorCode,
            l_strNewOrderRequestCode,
            WEB3VoucherCreateStatusDef.NOT_CREATE);

        //is��s�o�^�`�[(String)(�����E���z���o�^�`�[�쐬�T�[�r�XImpl::is��s�o�^�`�[)
        //�f�[�^����s�o�^�`�[�����ʂ���B
        //[����]
        //�U�֋敪�F���N�G�X�g�f�[�^.�A�����.�敪�T
        boolean l_blnFinancialInstitutionPostVoucher =
            this.isBankRegistVoucher(l_request.informInfoUnit.div5);

        //is�g���K���s
        //SONAR�փg���K�𔭍s�ł��鎞�ԑт��𔻒肷��B
        //[����]
        //���������F 0�iDEFAULT�j
        boolean l_blnSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);

        //get�V�K���ʃR�[�h
        //SONAR�ʒm�L���[�ɏ������ލۂɕK�v�Ȏ��ʃR�[�h���̔Ԃ���B
        //[����]
        //�،���ЃR�[�h�F ���N�G�X�g�f�[�^.�A�����.�،���ЃR�[�h()
        //���X�R�[�h�F ���N�G�X�g�f�[�^.�A�����.���X�R�[�h()
        //�����^�C�v�F ProductTypeEnum.���̑�
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_request.informInfoUnit.institutionCode,
            l_request.informInfoUnit.branchCode,
            ProductTypeEnum.OTHER);

        //MAP�I�u�W�F�N�g�̐���
        //�e��A���e�[�u�����X�V����ׂ�Map�I�u�W�F�N�g�𐶐�����
        Map l_map = new HashMap();
        l_map.put("order_request_number", l_strNewNumber);
        l_map.put("last_updater", l_strAdministratorCode);
        l_map.put("status", WEB3VoucherCreateStatusDef.CREATE_COMPLETE);
        l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //is��s�o�^�`�[�i�j == true �̏ꍇ�A�ȉ��̏��������s
        if (l_blnFinancialInstitutionPostVoucher)
        {
            //�U�֐\���i��s�j�`�[(�e��A��Params, String, String)
            //�R���X�g���N�^
            //[����]
            //�e��A���s�F �e��A���I�u�W�F�N�g.getDataSourceObject�i�j�̖߂�l
            //�`�[���ʃR�[�h�F �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h�i�j�Ŏ擾�����l
            //�e��A�����ʃR�[�h�F �A���Ǘ����ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h�i�j�Ŏ擾�����l
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_adminInformTransferApplyFinancialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(
                        (VariousInformParams)l_inform.getDataSourceObject(),
                        l_strNewNumber,
                        l_strNewOrderRequestCode);

            //save��s�o�^�`�[�L���[
            //�U�֐\���i��s�j�`�[�iG26�j�L���[�e�[�u���Ƀ��R�[�h���쐬����
            l_adminInformTransferApplyFinancialInstitutionVoucher.saveBankRegistVoucherHost();

            //�ȉ��̓��e��Map�ɒǉ�����
            //�f�[�^�R�[�h�F�@@�hGI823�h�i��s�j
            l_map.put("request_code", WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK);

            // is�g���K���s == true �̏ꍇ�A�ȉ��̏��������s
            if (l_blnSubmitMarketTrigger)
            {
                //�g���K���s(String, String)
                //�g���K�𔭍s����B
                //[����]
                //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.�A�����.�،���ЃR�[�h
                //�f�[�^�R�[�h�F�@@�hGI823�h�i��s�j
                this.submitMarketTrigger(
                    l_request.informInfoUnit.institutionCode,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK);
            }
        }
        else
        {
            //�U�֐\���i�X���j�`�[(�e��A��Params, String, String)
            //�R���X�g���N�^
            //[����]
            //�e��A���s�F �e��A���I�u�W�F�N�g.getDataSourceObject�i�j�̖߂�l
            //�`�[���ʃR�[�h�F �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h�i�j�Ŏ擾�����l
            //�e��A�����ʃR�[�h�F �A���Ǘ����ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h�i�j�Ŏ擾�����l
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher(
                    (VariousInformParams)l_inform.getDataSourceObject(),
                    l_strNewNumber,
                    l_strNewOrderRequestCode);

            //save�X���o�^�`�[�L���[( )
            //�U�֐\���i�X���j�`�[�iG26�j�L���[�e�[�u���Ƀ��R�[�h���쐬����
            l_adminInformTransferApplyPostVoucher.savePostRegistVoucherHost();

            //Map�I�u�W�F�N�g�ɒǉ�
            //�f�[�^�R�[�h�F�@@�hGI828�h�i�X���j
            l_map.put("request_code", WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL);

            //is�g���K���s == true �̏ꍇ�A�ȉ��̏��������s
            if (l_blnSubmitMarketTrigger)
            {
                //�g���K���s(String, String)
                //�g���K�𔭍s����B
                //[����]
                //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.�A�����.�،���ЃR�[�h
                //�f�[�^�R�[�h�F�@@�hGI828�h�i�X���j
                this.submitMarketTrigger(
                    l_request.informInfoUnit.institutionCode,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL);
            }
        }

        //update�e��A��(Map)
        //�e��A���e�[�u���̃��R�[�h���X�V����B
        //[����]
        //��������Map�I�u�W�F�N�g
        //DB�X�V�d�l�u���z���U�֌����o�^_�e��A���e�[�u��.xls�v
        //���z���U�֌����o�^_update_DB�X�V�d�l�Q��
        l_inform.updateInform(l_map);

        //���X�|���X�I�u�W�F�N�g�̐���
        WEB3AdminInformProfDistVoucherMakeCmpResponse l_response =
            (WEB3AdminInformProfDistVoucherMakeCmpResponse)l_request.createResponse();

        return l_response;
    }

    /**
     * (get�U�֌������Q�Ɖ��)<BR>
     * �����E���z���U�֌����o�^�󋵎Q�Ɖ�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget�U�֌������Q�Ɖ�ʁv �Q�ƁB<BR>
     * ===============================================<BR>
     * �@@�V�[�P���X�}�@@:get�U�֌������Q�Ɖ��<BR>
     * �@@��̈ʒu�@@�@@�@@:1.5�@@get�e��A���s(String, String, String, String)�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�߂�l��null�̏ꍇ�A��O���X��<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag:�@@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistVoucherInfoRefResponse
     * @@throws WEB3BaseException
     * @@roseuid 4642721701B0
     */
    public WEB3AdminInformProfDistVoucherInfoRefResponse getTransferAccountInfoRefScreen(
        WEB3AdminInformProfDistVoucherInfoRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTransferAccountInfoRefScreen(WEB3AdminInformProfDistVoucherInfoRefRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //�����`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "A0105"
        //is�X�V�F false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU,
            false);

        //���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N
        l_request.validate();

        //�،���ЃR�[�h���擾����
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //��L�[����Ɋe��A���e�[�u������������B
        //�m�����n
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //���X�R�[�h�F  ���N�G�X�g�f�[�^.���X�R�[�h
        //���ʃR�[�h�F  ���N�G�X�g�f�[�^.���ʃR�[�h
        //�A����ʁF  ���N�G�X�g�f�[�^.�A�����
        VariousInformParams l_variousInformParams =
            WEB3Inform.getVariousInform(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber,
                l_request.informType);

        //�߂�l��null�̏ꍇ�A��O���X��
        if (l_variousInformParams == null)
        {
            log.debug(" �Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }

        //�e��A���ڍ׏��C���X�^���X�𐶐�����B
        //�m�����n
        //�e��A���s�F get�e��A���s�i�j�̖߂�l
        WEB3InformDetailHeaderInfoUnit l_informDetailHeaderInfoUnit =
            new WEB3InformDetailHeaderInfoUnit(l_variousInformParams);

        //���X�|���X�I�u�W�F�N�g�̐���
        WEB3AdminInformProfDistVoucherInfoRefResponse l_response =
            (WEB3AdminInformProfDistVoucherInfoRefResponse)l_request.createResponse();

        //�A�����F�@@�e��A���ڍ׏��I�u�W�F�N�g
        l_response.informInfoUnit = l_informDetailHeaderInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�������ύX�m�F)<BR>
     * �����E���z���o�^�󋵕ύX�m�F��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate�������ύX�m�F�v �Q�ƁB<BR>
     * ===============================================<BR>
     * �@@�V�[�P���X�}�@@:validate�������ύX�m�F<BR>
     * �@@��̈ʒu�@@�@@�@@:1.8�@@get�e��A���s(String, String, String, String)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�߂�l��null�̏ꍇ�A��O���X���[<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag:�@@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistVoucherChgCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 4642757D0078
     */
    public WEB3AdminInformProfDistVoucherChgCnfResponse validateAccountInfoChgCnf(
        WEB3AdminInformProfDistVoucherChgCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateAccountInfoChgCnf(WEB3AdminInformProfDistVoucherChgCnfRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        // getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "A0105"
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, true);

        //validate������t�\( )
        //������t�\�����`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // validate( )
        //���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N���s���B
        l_request.validate();

        // �e��A��(�e��A�����)
        //�R���X�g���N�^
        //�m�����n
        //�A�����F�@@���N�G�X�g�f�[�^.�A�����
        WEB3Inform l_inform = new WEB3Inform(l_request.informInfoUnit);

        // validate�e��A�����( )
        //�e��A���f�[�^�̓��̓`�F�b�N���s���B
        l_inform.validateInformDetailInfoUnit();

        // get�،���ЃR�[�h( )
        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //  get�e��A���s(String, String, String, String)
        //��L�[����Ɋe��A���e�[�u������������B
        //�m�����n
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //���X�R�[�h�F  ���N�G�X�g�f�[�^.���X�R�[�h
        //���ʃR�[�h�F  ���N�G�X�g�f�[�^.���ʃR�[�h
        //�A����ʁF  ���N�G�X�g�f�[�^.�A�����
        VariousInformParams l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //�߂�l��null�̏ꍇ�A��O���X���[
        if (l_variousInformParams == null)
        {
            log.debug(" �Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }

        // is�g���K���s(�������� : String)
        //SONAR�փg���K�𔭍s�ł��鎞�ԑт��𔻒肷��B
        //
        //[����]
        //���������F 0�iDEFAULT�j
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        // validate�`�[�ύX(String, boolean)
        //�`�[�ύX���\���`�F�b�N���s���B
        //�쐬�󋵁F�@@get�e��A���s�i�j.get�쐬�󋵁i�j�̖߂�l
        //�g���K�[���s�敪�F�@@is�g���K�[���s�i�j�̖߂�l
        String l_strStatus = l_variousInformParams.getStatus();
        validateVoucherChg(l_strStatus, l_blnIsSubmitMarketTrigger);

        //  validate�U����o�^���(�e��A�����)
        //�����U�֓o�^�}�X�^�[���R�[�h�Ƃ̐������`�F�b�N
        //[����]
        //�A�����F ���N�G�X�g�f�[�^.�A�����
        validateTransferRegistInfo(l_request.informInfoUnit);

        // validate�����R�[�h(�e��A�����)
        //�����R�[�h�̑Ó����`�F�b�N���s���B
        //[����]
        //�A�����F ���N�G�X�g�f�[�^.�A�����
        validateProductCode(l_request.informInfoUnit);

        // get�t�����( )
        WEB3InformAddInfoUnit l_informAddInfoUnit = l_inform.getInformAddInfoUnit();

        // �e����Z�@@�֏��( )
        WEB3AdminInformProfDistSellTransSrcInfo l_informProfDistSellTransSrcInfo =
            new WEB3AdminInformProfDistSellTransSrcInfo();

        // �A�����.�敪�T = 1�F��s�U�� �̏ꍇ�A�ȉ��̏��������s
        String l_strDiv5 = l_request.informInfoUnit.div5;
        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_strDiv5))
        {
            // validate���Z�@@�֏��(�e��A�����)
            //�U�֐\���i��s�j�`�[���쐬����ۂ̃p�����[�^�`�F�b�N���s���B
            //�m�����n
            //�A�����F�@@���N�G�X�g�f�[�^.�A�����
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_informTransferApplyFinancialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher();
            l_informTransferApplyFinancialInstitutionVoucher.validateFinancialInstitutionInfo(
                l_request.informInfoUnit);

            // get���Z�@@�֏��(String, String, �e����Z�@@�֏��)
            //���Z�@@�ցi��s�j�}�X�^������Z�@@�֏����擾����B
            //[����]
            //���Z�@@�փR�[�h�F �A�����.�R�[�h�R
            //�x�X�R�[�h�F �A�����.�R�[�h�S
            //���Z�@@�֏��F ���������e����Z�@@�֏��I�u�W�F�N�g
            getFinancialInstitutionInfo(
                l_request.informInfoUnit.code3,
                l_request.informInfoUnit.code4,
                l_informProfDistSellTransSrcInfo);
        }

        // �A�����.�敪�T = 5�F�X���U�� �̏ꍇ�A�ȉ��̏��������s
        if (WEB3TransferDivDef.POSTAL_TRANSFER.equals(l_strDiv5))
        {
            // validate�X�����(�e��A�����)
            //�U�֐\���i�X���j�`�[���쐬����ۂ̃p�����[�^�`�F�b�N���s���B
            //�m�����n
            //�A�����F�@@���N�G�X�g�f�[�^.�A�����
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher();
            l_adminInformTransferApplyPostVoucher.validatePostInfo(l_request.informInfoUnit);
        }

        // createResponse( )
        //���X�|���X�I�u�W�F�N�g�̐���
        WEB3AdminInformProfDistVoucherChgCnfResponse l_adminInformProfDistVoucherChgCnfResponse =
            (WEB3AdminInformProfDistVoucherChgCnfResponse)l_request.createResponse();

        //�i���j�v���p�e�B�Z�b�g
        //�i���j���X�|���X�I�u�W�F�N�g�Ɉȉ����Z�b�g����B
        //�t����� = get�t�����i�j�̖߂�l
        l_adminInformProfDistVoucherChgCnfResponse.informAddUnit = l_informAddInfoUnit;

        //���Z�@@�֏�� = �e����Z�@@�֏��I�u�W�F�N�g
        l_adminInformProfDistVoucherChgCnfResponse.financialInstitutionInfo =
            l_informProfDistSellTransSrcInfo;

        // return
        log.exiting(STR_METHOD_NAME);
        return l_adminInformProfDistVoucherChgCnfResponse;
    }

    /**
     * (submit�������ύX����)<BR>
     * �����E���z���o�^�󋵕ύX������ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit�������ύX�����v �Q�ƁB<BR>
     * ===============================================<BR>
     * �@@�V�[�P���X�}�@@:validate�������ύX�m�F<BR>
     * �@@��̈ʒu�@@�@@�@@:1.9�@@get�e��A���s(String, String, String, String)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�߂�l��null�̏ꍇ�A��O���X���[<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag:�@@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistVoucherChgCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 46427C490078
     */
    public WEB3AdminInformProfDistVoucherChgCmpResponse submitAccountInfoChgCmp(
        WEB3AdminInformProfDistVoucherChgCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " submitAccountInfoChgCmp(WEB3AdminInformProfDistVoucherChgCmpRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        // getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "A0105"
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, true);

        //validate����p�X���[�h(�p�X���[�h : String)
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //validate������t�\( )
        //������t�\�����`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //�e��A��(�e��A�����)(�e��A��::�e��A��)
        WEB3Inform l_inform = new WEB3Inform(l_request.informInfoUnit);

        //validate�e��A�����( )
        //�e��A���f�[�^�̓��̓`�F�b�N���s���B
        l_inform.validateInformDetailInfoUnit();

        VariousInformParams l_afterChgVariousInformParams =
            (VariousInformParams)l_inform.getDataSourceObject();

        //get�،���ЃR�[�h( )
        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�e��A���s(String, String, String, String)(�e��A��::get�e��A���s)
        //��L�[����Ɋe��A���e�[�u������������B
        //�m�����n
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //���X�R�[�h�F  ���N�G�X�g�f�[�^.���X�R�[�h
        //���ʃR�[�h�F  ���N�G�X�g�f�[�^.���ʃR�[�h
        //�A����ʁF  ���N�G�X�g�f�[�^.�A�����
        VariousInformParams l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //�߂�l��null�̏ꍇ�A
        //��O���X���[
        if (l_variousInformParams == null)
        {
            log.debug(" �Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }

        // is�g���K���s(�������� : String)
        //SONAR�փg���K�𔭍s�ł��鎞�ԑт��𔻒肷��B
        //[����]
        //���������F 0�iDEFAULT�j
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        //validate�`�[�ύX(String, boolean)
        //�`�[�ύX���\���`�F�b�N���s���B
        //�쐬�󋵁F�@@get�e��A���s�i�j.get�쐬�󋵁i�j�̖߂�l
        //�g���K�[���s�敪�F�@@is�g���K�[���s�i�j�̖߂�l
        String l_strStatus = l_variousInformParams.getStatus();
        validateVoucherChg(l_strStatus, l_blnIsSubmitMarketTrigger);

        // validate�U����o�^���(�e��A�����)
        //�����U�֓o�^�}�X�^�[���R�[�h�Ƃ̐������`�F�b�N
        //[����]
        //�A�����F ���N�G�X�g�f�[�^.�A�����
        validateTransferRegistInfo(l_request.informInfoUnit);

        //validate�����R�[�h(�e��A�����)
        //�����R�[�h�̑Ó����`�F�b�N���s���B
        //[����]
        //�A�����F ���N�G�X�g�f�[�^.�A�����
        validateProductCode(l_request.informInfoUnit);

        // is��s�o�^�`�[(String)
        //�f�[�^����s�o�^�`�[�����ʂ���B
        //[����]
        //�U�֋敪�F  ���N�G�X�g�f�[�^.�A�����.�敪�T
        String l_strDiv5 = l_request.informInfoUnit.div5;
        boolean l_blnIsBankRegistVoucher =
        	isBankRegistVoucher(l_strDiv5);

        //get�V�K���ʃR�[�h(�،���ЃR�[�h : String, ���X�R�[�h : String, �����^�C�v : ProductTypeEnum)
        //SONAR�ʒm�L���[�ɏ������ލۂɕK�v�Ȏ��ʃR�[�h���̔Ԃ���B
        //[����]
        //�@@�،���ЃR�[�h�F ���N�G�X�g�f�[�^.�A�����.�،���ЃR�[�h
        //�@@���X�R�[�h�F ���N�G�X�g�f�[�^.�A�����.���X�R�[�h
        //�@@�����^�C�v�F ProductTypeEnum.���̑�
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_request.informInfoUnit.institutionCode,
            l_request.informInfoUnit.branchCode,
            ProductTypeEnum.OTHER);

        //get�e��A���s�i�j.�敪�T
        String l_strExtDiv5 = l_variousInformParams.getExtDiv5();

        //get�e��A���s�i�j.�敪�T == 1:��s�U���̏ꍇ
        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_strExtDiv5))
        {
            //�U�֐\���i��s�j�`�[(�e��A��Params)
            //�e��A���s�F get�e��A���s�i�j�̖߂�l
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_financialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(
                        l_variousInformParams);

            // delete��s�o�^�`�[�L���[( )
            //�U�֐\���i��s�j�`�[�iG26�j�L���[�e�[�u���̃��R�[�h���폜����B
            l_financialInstitutionVoucher.deleteBankRegistVoucherHost();
        }

        //get�e��A���s�i�j.�敪�T == 5:�X���U�ւ̏ꍇ
        if (WEB3TransferDivDef.POSTAL_TRANSFER.equals(l_strExtDiv5))
        {
            //�U�֐\���i�X���j�`�[(�e��A��Params)
            //�m�����n
            //�e��A���s�F get�e��A���s�i�j�̖߂�l
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher(l_variousInformParams);

            // delete�X���o�^�`�[�L���[( )
            l_adminInformTransferApplyPostVoucher.deletePostRegistVoucherHost();
        }

        //is��s�o�^�`�[�i�j == true �̏ꍇ�A�ȉ��̏��������s
        if (l_blnIsBankRegistVoucher)
        {
            //�U�֐\���i��s�j�`�[(�e��A��Params, String, String)
            //[����]
            // �e��A���s�F �e��A���I�u�W�F�N�g.getDataSourceObject�i�j�̖߂�l
            // �`�[���ʃR�[�h�F �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h�i�j�Ŏ擾�����l
            // �e��A�����ʃR�[�h�F get�e��A���s�i�j.get���ʃR�[�h�i�j
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_financialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(
                        l_afterChgVariousInformParams,
                        l_strNewNumber,
                        l_variousInformParams.getRequestNumber());

            // save��s�o�^�`�[�L���[()
            //�U�֐\���i��s�j�`�[�iG26�j�L���[�e�[�u���Ƀ��R�[�h���쐬����B
            l_financialInstitutionVoucher.saveBankRegistVoucherHost();

            //is�g���K���s == true �̏ꍇ�A�ȉ��̏��������s
            if (l_blnIsSubmitMarketTrigger)
            {
                //�g���K���s(String, String)
                //�g���K�𔭍s����B
                //[����]
                //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.�A�����.�،���ЃR�[�h
                //�f�[�^�R�[�h�F�@@�hGI823�h�i��s�j
                submitMarketTrigger(
                    l_request.informInfoUnit.institutionCode,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK);
            }
        }
        //is��s�o�^�`�[�i�j == false �̏ꍇ�A�ȉ��̏��������s
        if (!l_blnIsBankRegistVoucher)
        {
            //�U�֐\���i�X���j�`�[(�e��A��Params, String, String)
            //[����]
            // �e��A���s�F �e��A���I�u�W�F�N�g.getDataSourceObject�i�j�̖߂�l
            // �`�[���ʃR�[�h�F �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h�i�j�Ŏ擾�����l
            // �e��A�����ʃR�[�h�F get�e��A���s�i�j.get���ʃR�[�h�i�j
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher(
                    l_afterChgVariousInformParams,
                    l_strNewNumber,
                    l_variousInformParams.getRequestNumber());

            // save�X���o�^�`�[�L���[( )
            //�U�֐\���i�X���j�`�[�iG26�j�L���[�e�[�u���Ƀ��R�[�h���쐬����B
            l_adminInformTransferApplyPostVoucher.savePostRegistVoucherHost();

            //is�g���K���s == true �̏ꍇ�A�ȉ��̏��������s
            if (l_blnIsSubmitMarketTrigger)
            {
                //�g���K���s(String, String)
                //�g���K�𔭍s����B
                //[����]
                // �،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.�A�����.�،���ЃR�[�h
                // �f�[�^�R�[�h�F�@@�hGI828�h�i�X���j
                submitMarketTrigger(
                    l_request.informInfoUnit.institutionCode,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL);
            }
        }

        // update�e��A���ύX���(�e��A��Params, �e��A��Params, String, String)
        //�e��A���̕ύX�����X�V����B
        //�m�����n
        //�ύX�O�e��A���s�F get�e��A���s�i�j�̖߂�l
        //�ύX��e��A���s�F �e��A���I�u�W�F�N�g.getDataSourceObject�i�j�̖߂�l
        //���ʃR�[�h�F �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h�i�j�Ŏ擾�����l
        //�f�[�^�R�[�h�F�@@is��s�o�^�`�[ == true �̏ꍇ�AGI823�i��s�j
        //                 is��s�o�^�`�[ == false �̏ꍇ�AGI828�i�X���j
        String l_strFinancialInstitutionPostVoucher = null;
        if (l_blnIsBankRegistVoucher)
        {
            l_strFinancialInstitutionPostVoucher = WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK;
        }
        else
        {
            l_strFinancialInstitutionPostVoucher = WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL;
        }
        updateVariousInformChgInfo(
            l_variousInformParams,
            l_afterChgVariousInformParams,
            l_strNewNumber,
            l_strFinancialInstitutionPostVoucher
            );

        //createResponse( )
        //���X�|���X�I�u�W�F�N�g�̐���
        WEB3AdminInformProfDistVoucherChgCmpResponse
            l_informProfDistVoucherChgCmpResponse =
                (WEB3AdminInformProfDistVoucherChgCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_informProfDistVoucherChgCmpResponse;
    }

    /**
     * (validate����������m�F)<BR>
     * �����E���z���o�^�󋵎���m�F��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate����������m�F�v �Q�ƁB<BR>
     * ===============================================<BR>
     * �@@�V�[�P���X�}�@@:validate�������ύX�m�F<BR>
     * �@@��̈ʒu�@@�@@�@@:1.6�@@get�e��A���s(String, String, String, String)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�߂�l��null�̏ꍇ�A��O���X���[<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag:�@@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistVoucherCancCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 46427D1900F5
     */
    public WEB3AdminInformProfDistVoucherCancCnfResponse validateAccountInfoCancCnf(
        WEB3AdminInformProfDistVoucherCancCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateAccountInfoCancCnf(WEB3AdminInformProfDistVoucherCancCnfRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        // getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F "A0105"
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, true);

        //validate������t�\( )
        //������t�\�����`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // validate( )
        //���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N���s���B
        l_request.validate();

        // get�،���ЃR�[�h( )
        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get�e��A���s(String, String, String, String)
        //��L�[����Ɋe��A���e�[�u������������B
        //�m�����n
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //���X�R�[�h�F  ���N�G�X�g�f�[�^.���X�R�[�h
        //���ʃR�[�h�F  ���N�G�X�g�f�[�^.���ʃR�[�h
        //�A����ʁF  ���N�G�X�g�f�[�^.�A�����
        VariousInformParams l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //�߂�l��null�̏ꍇ�A
        //��O���X���[
        if (l_variousInformParams == null)
        {
            log.debug(" �Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }

        // is�g���K���s(�������� : String)
        //SONAR�փg���K�𔭍s�ł��鎞�ԑт��𔻒肷��B
        //[����]
        //���������F 0�iDEFAULT�j
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        //validate�`�[���(String, boolean)
        //�`�[����\���`�F�b�N���s���B
        //[����]
        //�쐬�󋵁F get�e��A���s�i�j.get�`�[�쐬�󋵂̖߂�l
        //�g���K�[���s�敪�F is�g���K�[���s�i�j�̖߂�l
        validateVoucherCanc(
            l_variousInformParams.getStatus(),
            l_blnIsSubmitMarketTrigger);

        //�e��A�����(�e��A��Params)
        //�R���X�g���N�^
        //�m�����n
        //�e��A���s�F get�e��A���s�i�j�̖߂�l
        WEB3InformDetailInfoUnit l_informDetailInfoUnit =
            new WEB3InformDetailInfoUnit(l_variousInformParams);

        //�e��A��(�e��A�����)
        //�R���X�g���N�^
        //[����]
        //�A�����F���������e��A�����I�u�W�F�N�g
        WEB3Inform l_inform = new WEB3Inform(l_informDetailInfoUnit);

        //get�t�����( )
        //�t�������擾����B
        WEB3InformAddInfoUnit l_informAddInfoUnit = l_inform.getInformAddInfoUnit();

        //�e����Z�@@�֏��( )
        WEB3AdminInformProfDistSellTransSrcInfo l_informProfDistSellTransSrcInfo =
            new WEB3AdminInformProfDistSellTransSrcInfo();

        WEB3AdminInformProfDistSellTransSrcInfo l_adminInformProfDistSellTransSrcInfo = null;
        //�e��A��.�e��A���s.get�敪�T = 1�F��s�U�� �̏ꍇ�A�ȉ��̏��������s
        String l_strExtDiv5 = l_variousInformParams.getExtDiv5();
        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_strExtDiv5))
        {
            //get���Z�@@�֏��(String, String, �e����Z�@@�֏��)
            //���Z�@@�ցi��s�j�}�X�^������Z�@@�֏����擾����B
            //[����]
            //���Z�@@�փR�[�h�F �e��A��.�e��A���s.get�R�[�h�R
            //�x�X�R�[�h�F �e��A��.�e��A���s.get�R�[�h�S
            //���Z�@@�֏��F ���������e����Z�@@�֏��I�u�W�F�N�g
            l_adminInformProfDistSellTransSrcInfo =
                getFinancialInstitutionInfo(
                    l_variousInformParams.getExtCode3(),
                    l_variousInformParams.getExtCode4(),
                    l_informProfDistSellTransSrcInfo);
        }

        // createResponse( )
        WEB3AdminInformProfDistVoucherCancCnfResponse
            l_informProfDistVoucherCancCnfResponse =
                (WEB3AdminInformProfDistVoucherCancCnfResponse)l_request.createResponse();

        //�i*�j�v���p�e�B�Z�b�g
        //�i*�j���X�|���X�f�[�^�Ɉȉ����Z�b�g����B
        //�A�����F ���������e��A�����I�u�W�F�N�g
        //�t�����F get�t�����i�j�̖߂�l
        //���Z�@@�֏��F get���Z�@@�֏��i�j�̖߂�l
        l_informProfDistVoucherCancCnfResponse.informInfoUnit = l_informDetailInfoUnit;
        l_informProfDistVoucherCancCnfResponse.informAddUnit = l_informAddInfoUnit;
        l_informProfDistVoucherCancCnfResponse.financialInstitutionInfo =
            l_adminInformProfDistSellTransSrcInfo;

        log.exiting(STR_METHOD_NAME);
        return l_informProfDistVoucherCancCnfResponse;
    }

    /**
     * (submit�������������)<BR>
     * �����E���z���o�^�󋵎��������ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit��������������v �Q�ƁB<BR>
     * ===============================================<BR>
     * �@@�V�[�P���X�}�@@:validate�������ύX�m�F<BR>
     * �@@��̈ʒu�@@�@@�@@:1.8�@@get�e��A���s(String, String, String, String)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�߂�l��null�̏ꍇ�A��O���X���[<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag:�@@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistVoucherCancCmpResponse
     * @@throws WEB3BaseException
     * @@roseuid 46427D6302F9
     */
    public WEB3AdminInformProfDistVoucherCancCmpResponse submitAccountInfoCancCmp(
        WEB3AdminInformProfDistVoucherCancCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " submitAccountInfoCancCmp(WEB3AdminInformProfDistVoucherCancCmpRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL�I");
        }

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�����`�F�b�N���s��
        //[����]
        //�@@�\�J�e�S���R�[�h�F "A0105"
        //is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, true);

        //validate����p�X���[�h(�p�X���[�h : String)
        //����p�X���[�h�����������̃`�F�b�N���s���B
        //[����]
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //validate������t�\( )
        //������t�\�����`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // validate( )
        //���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N
        l_request.validate();

        //get�Ǘ��҃R�[�h( )
        //�Ǘ��҃R�[�h���擾����B
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //get�،���ЃR�[�h( )
        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�e��A���s(String, String, String, String)
        //�m�����n
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //���X�R�[�h�F  ���N�G�X�g�f�[�^.���X�R�[�h
        //���ʃR�[�h�F  ���N�G�X�g�f�[�^.���ʃR�[�h
        //�A����ʁF  ���N�G�X�g�f�[�^.�A�����
        VariousInformParams l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //�߂�l��null�̏ꍇ�A
        //��O���X���[
        if (l_variousInformParams == null)
        {
            log.debug(" �Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }

        //is�g���K���s(�������� : String)
        //SONAR�փg���K�𔭍s�ł��鎞�ԑт��𔻒肷��B
        //[����]
        //���������F 0�iDEFAULT�j
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        //validate�`�[���(String, boolean)
        //�`�[����\���`�F�b�N���s���B
        //[����]
        //�쐬�󋵁F get�e��A���s�i�j..get�`�[�쐬�󋵂̖߂�l
        //�g���K�[���s�敪�F is�g���K�[���s�i�j�̖߂�l
        validateVoucherCanc(
            l_variousInformParams.getStatus(),
            l_blnIsSubmitMarketTrigger);

        // is��s�o�^�`�[(String)
        //�f�[�^����s�o�^�`�[�����ʂ���B
        //[����]
        //�U�֋敪�F  get�e��A���s�i�j.�敪�T
        String l_strExtDiv5 = l_variousInformParams.getExtDiv5();
        boolean l_blnIsBankRegistVoucher =
            isBankRegistVoucher(l_strExtDiv5);

        //is��s�o�^�`�[�i�j == true �̏ꍇ�A�ȉ��̏��������s
        if (l_blnIsBankRegistVoucher)
        {
            //�U�֐\���i��s�j�`�[(�e��A��Params)
            //�m����
            //�e��A���s�F get�e��A���s�i�j�̖߂�l
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_financialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(
                        l_variousInformParams);

            // delete��s�o�^�`�[�L���[( )
            //�U�֐\���i��s�j�`�[�iG26�j�L���[�e�[�u���̃��R�[�h���폜����B
            l_financialInstitutionVoucher.deleteBankRegistVoucherHost();
        }

        //is��s�o�^�`�[�i�j == false �̏ꍇ�A�ȉ��̏��������s
        if (!l_blnIsBankRegistVoucher)
        {
            //�U�֐\���i�X���j�`�[(�e��A��Params)
            //�m�����n
            //�e��A���s�F get�e��A���s�i�j�̖߂�l
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher(l_variousInformParams);

            // delete�X���o�^�`�[�L���[( )
            //�U�֐\���i�X���j�`�[�iG26�j�L���[�e�[�u���̃��R�[�h���폜����B
            l_adminInformTransferApplyPostVoucher.deletePostRegistVoucherHost();
        }

        //�ȉ��̓��e��Map�I�u�W�F�N�g�𐶐�����
        //�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h�i�j�̖߂�l
        //�`�[�쐬�󋵁F�@@0�F���쐬
        //�X�V�����F�@@��������
        Map l_map = new HashMap();
        l_map.put("last_updater", l_strAdministratorCode);
        l_map.put("status", WEB3VoucherCreateStatusDef.NOT_CREATE);
        l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        //�G���[���R�R�[�h      null
        l_map.put("error_reason_code", null);
        //�`�[���ʃR�[�h  null
        l_map.put("order_request_number", null);
        //�f�[�^�R�[�h null
        l_map.put("request_code", null);
        //�`�[���M���� null
        l_map.put("send_timestamp", null);
        //�`�[��M���� null
        l_map.put("receipt_timestamp", null);

        //update�e��A��(Map)
        //�e��A���e�[�u���̃��R�[�h���X�V����B
        //[����]
        //��������Map�I�u�W�F�N�g
        //DB�X�V�d�l�u���z���U�֌����`�[���_�e��A���e�[�u��.xls�v
        //���z���U�֌����`�[���_update�Q��

        //�R���X�g���N�^
        //[����]
        //�e��A���s�Fget�e��A���s�i�j�̖߂�l
        WEB3Inform l_inform = new WEB3Inform(l_variousInformParams);

        //�R���X�g���N�^
        //[����]
        //�e��A���s�Fget�e��A���s�i�j�̖߂�l
        l_inform.updateInform(l_map);

        //createResponse( )
        WEB3AdminInformProfDistVoucherCancCmpResponse
            l_adminInformProfDistVoucherCancCmpResponse =
                (WEB3AdminInformProfDistVoucherCancCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_adminInformProfDistVoucherCancCmpResponse;
    }

    /**
     * (validate�U����o�^���)<BR>
     * �Ώۃf�[�^�Ǝ����U�֓o�^�}�X�^�[���R�[�h�̐������`�F�b�N<BR>
     * <BR>
     * �P�j�@@�o�^�敪�̎擾<BR>
     * <BR>
     * �o�^�敪 = ����:�A�����.�敪4<BR>
     * <BR>
     * �Q�j�@@�����U�֓o�^�}�X�^�[�̌���<BR>
     * <BR>
     * [��������]<BR>
     * �،���ЃR�[�h = ����:�A�����.�،���ЃR�[�h<BR>
     * ���X�R�[�h = ����:�A�����.���X�R�[�h<BR>
     * �ڋq�R�[�h like ����:�A�����.�����ԍ��̓�6��%<BR>
     * �����R�[�h = ����:�A�����.�����R�[�h<BR>
     * ������:�A�����.�����R�[�h��null �̏ꍇ�Ais null<BR>
     * �w��敪 = ����:�A�����.�敪�Q<BR>
     * ���i like ����:�A�����.�敪�R�̓��ꌅ%<BR>
     * ������:�A�����.�敪�R��null �̏ꍇ�Ais null<BR>
     * �U�֋敪 = ����:�A�����.�敪�T<BR>
     * <BR>
     * <BR>
     * �R�j�@@�o�^�敪 = 1�F�V�K �̏ꍇ�A<BR>
     * �@@�Q�j�̌������ʂ����݂���ꍇ�A�G���[<BR>
     * �@@�u�ڋq�_�u���G���[�v<BR>
     * �@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@BUSINESS_ERROR_02783<BR>
     * <BR>
     * �S�j�@@�o�^�敪 = 3�F�폜 �̏ꍇ�A<BR>
     * �@@�Q�j�̌������ʂ����݂��Ȃ��ꍇ�A�G���[<BR>
     * �@@�u�폜�Y�����R�[�h�Ȃ��v<BR>
     * �@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@BUSINESS_ERROR_02784<BR>
     * <BR>
     * @@param l_informDetailInfoUnit - (�A�����)<BR>
     * �A�����
     * @@throws WEB3BaseException
     * @@roseuid 4647C44D0137
     */
    protected void validateTransferRegistInfo(WEB3InformDetailInfoUnit l_informDetailInfoUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateTransferRegistInfo(WEB3InformDetailInfoUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_informDetailInfoUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        // �P�j�@@�o�^�敪�̎擾
        // �o�^�敪 = ����:�A�����.�敪4
        String l_strRegistDiv = l_informDetailInfoUnit.div4;

        List l_lisDirectDebitRow = null;
        // �Q�j�@@�����U�֓o�^�}�X�^�[�̌���
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            // [��������]
            StringBuffer l_sbQuery = new StringBuffer();
            List l_lisValue = new ArrayList();

            // �،���ЃR�[�h = ����:�A�����.�،���ЃR�[�h
            l_sbQuery.append(" institution_code = ? ");
            l_lisValue.add(l_informDetailInfoUnit.institutionCode);

            // ���X�R�[�h = ����:�A�����.���X�R�[�h
            l_sbQuery.append(" and branch_code = ? ");
            l_lisValue.add(l_informDetailInfoUnit.branchCode);

            // �ڋq�R�[�h = ����:�A�����.�����ԍ�
            l_sbQuery.append(" and account_code like ? || '%' ");
            l_lisValue.add(l_informDetailInfoUnit.accountNumber);

            // �����R�[�h = ����:�A�����.�����R�[�h
            // ������:�A�����.�����R�[�h��null �̏ꍇ�Ais null
            if (WEB3StringTypeUtility.isEmpty(l_informDetailInfoUnit.productCode))
            {
                l_sbQuery.append(" and fund_code is null");
            }
            else
            {
                l_sbQuery.append(" and fund_code = ? ");
                l_lisValue.add(l_informDetailInfoUnit.productCode);
            }

            // �w��敪 = ����:�A�����.�敪�Q
            l_sbQuery.append(" and designate_div = ? ");
            l_lisValue.add(l_informDetailInfoUnit.div2);

            // ���i like ����:�A�����.�敪�R�̓��ꌅ%
            // ������:�A�����.�敪�R��null �̏ꍇ�Ais null
            if (WEB3StringTypeUtility.isEmpty(l_informDetailInfoUnit.div3))
            {
                l_sbQuery.append(" and comodity is null");
            }
            else
            {
                l_sbQuery.append(" and comodity like ? || '%'");
                l_lisValue.add(l_informDetailInfoUnit.div3.substring(0, 1));
            }
            //�U�֋敪=����:�A�����.�敪�T
            l_sbQuery.append(" and transfer_div = ? ");
            l_lisValue.add(l_informDetailInfoUnit.div5);

            Object[] l_dataValues = l_lisValue.toArray();
            l_lisDirectDebitRow = l_queryProcessor.doFindAllQuery(
                DirectDebitRow.TYPE,
                l_sbQuery.toString(),
                l_dataValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �R�j�@@�o�^�敪 = 1�F�V�K �̏ꍇ�A
        //�@@ �Q�j�̌������ʂ����݂���ꍇ�A�G���[
        //�@@ �u�ڋq�_�u���G���[�v
        if (WEB3InformRegistDivDef.REGISTRATION.equals(l_strRegistDiv))
        {
            if (!l_lisDirectDebitRow.isEmpty())
            {
                log.debug("�ڋq�_�u���G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02783,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�ڋq�_�u���G���[�B");
            }
        }

        // �S�j�@@�o�^�敪 = 3�F�폜 �̏ꍇ�A
        //�@@ �Q�j�̌������ʂ����݂��Ȃ��ꍇ�A�G���[
        //�@@�u�폜�Y�����R�[�h�Ȃ��v
        if (WEB3InformRegistDivDef.DELETE.equals(l_strRegistDiv))
        {
            if (l_lisDirectDebitRow.isEmpty())
            {
                log.debug("�폜�Y�����R�[�h�Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02784,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�폜�Y�����R�[�h�Ȃ��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�����R�[�h)<BR>
     * �����R�[�h�̑Ó����`�F�b�N���s���B<BR>
     * <BR>
     * �P�j����:�A�����.�敪�R = 2:�����M�� or R: �I�[�v�������R�[�X�̏ꍇ�A<BR>
     * <BR>
     * �@@�P�|�P�j�@@����:�A�����.�R�[�h�P �̃����O�X��7�o�C�g�ȊO�͗�O���X���[<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�u�����R�[�h�G���[�v<BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02785<BR>
     * <BR>
     * �Q�j����:�A�����.�敪�R = 3:���Ѝ̏ꍇ�A<BR>
     * <BR>
     * �@@�Q�|�P�j�@@����:�A�����.�R�[�h�Q �̃����O�X��9�o�C�g�ȊO�͗�O���X���[<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�u�����R�[�h�G���[�v<BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02785<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@����:�A�����.�R�[�h�Q ��ALL���p�O�A�܂��͔��p�u�����N�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�u�����R�[�h�G���[�v<BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02785<BR>
     * <BR>
     * �R�j����:�A�����.�敪�R��null �܂��� �P�j�A�Q�j�ȊO�̏ꍇ�A<BR>
     * <BR>
     * �@@�R�|�P�j����:�A�����.�R�[�h�P != null�@@�܂���<BR>
     * �@@�@@�@@�@@�@@����:�A�����.�R�[�h�Q != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O���X���[<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�u�����R�[�h�G���[�v<BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02785<BR>
     * @@param l_informDetailInfoUnit - (�A�����)<BR>
     * �A�����
     * @@throws WEB3BaseException
     * @@roseuid 46528FA1029C
     */
    protected void validateProductCode(WEB3InformDetailInfoUnit l_informDetailInfoUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateProductCode(WEB3InformDetailInfoUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_informDetailInfoUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j����:�A�����.�敪�R = 2:�����M�� or R: �I�[�v�������R�[�X�̏ꍇ�A
        // �P�|�P�j�@@����:�A�����.�R�[�h�P �̃����O�X��7�o�C�g�ȊO�͗�O���X���[
        //�u�����R�[�h�G���[�v
        if (WEB3InformProductFirstDef.MUTUAL_FUND.equals(l_informDetailInfoUnit.div3)
            || WEB3InformProductFirstDef.OPEN_KABUTOU_COURSE.equals(l_informDetailInfoUnit.div3))
        {
            if (WEB3StringTypeUtility.getByteLength(l_informDetailInfoUnit.code1) != 7)
            {
                log.debug("�����R�[�h�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02785,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����R�[�h�G���[�B");
            }
        }

        //�Q�j����:�A�����.�敪�R = �R:���Ѝ̏ꍇ�A
        else if (WEB3InformProductFirstDef.PUBLIC_AND_CORPORATE_BOODS.equals(
            l_informDetailInfoUnit.div3))
        {
            // �Q�|�P�j�@@����:�A�����.�R�[�h�Q �̃����O�X��9�o�C�g�ȊO�͗�O���X���[
            // �u�����R�[�h�G���[�v
            if (WEB3StringTypeUtility.getByteLength(l_informDetailInfoUnit.code2) != 9)
            {
                log.debug("�����R�[�h�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02785,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����R�[�h�G���[�B");
            }

            //�Q�|�Q�j�@@����:�A�����.�R�[�h�Q ��ALL���p�O�A�܂��͔��p�u�����N�̏ꍇ�A��O���X���[
            //�u�����R�[�h�G���[�v
            if (WEB3InformFundCodeDef.BLANK.equals(l_informDetailInfoUnit.code2.trim())
                || (WEB3InformFundCodeDef.ALL_ZERO.equals(l_informDetailInfoUnit.code2)))
            {
                log.debug("�����R�[�h�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02785,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����R�[�h�G���[�B");
            }
        }
        //�R�j����:�A�����.�敪�R��null �܂��� �P�j�A�Q�j�ȊO�̏ꍇ�A
        //�R�|�P�j����:�A�����.�R�[�h�P != null�@@�܂��́@@����:�A�����.�R�[�h�Q != null�̏ꍇ�A��O���X���[
        //�u�����R�[�h�G���[�v
        else
        {
            if (l_informDetailInfoUnit.code1 != null || l_informDetailInfoUnit.code2 != null)
            {
                log.debug("�����R�[�h�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02785,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����R�[�h�G���[�B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get���Z�@@�֏��)<BR>
     * ���Z�@@�ցi��s�j�}�X�^������Z�@@�֏����擾����B<BR>
     * <BR>
     * �P�j �ȉ��̌��������ŁA���Z�@@�ցi��s�j�}�X�^������<BR>
     * <BR>
     * [����]<BR>
     * ��s�R�[�h = ����:���Z�@@�փR�[�h<BR>
     * �x�X�R�[�h = ����:�x�X�R�[�h <BR>
     * <BR>
     * �����R�[�h�����݂��Ȃ��ꍇ�A��O���X���[<BR>
     * �u���Z�@@�ւ����݂��Ȃ��B�v<BR>
     * �@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@tag:�@@BUSINESS_ERROR_01314<BR>
     * <BR>
     * �Q�j ����:���Z�@@�֏��Ɏ擾�����e�l���Z�b�g<BR>
     * <BR>
     * �R�j�@@�Z�b�g�������Z�@@�֏���ԋp�B<BR>
     * @@param l_strFinancialInstitutionCode - (���Z�@@�փR�[�h)<BR>
     * ���Z�@@�փR�[�h
     * @@param l_strFinancialBranchCode - (�x�X�R�[�h)<BR>
     * �x�X�R�[�h
     * @@param l_financialInstitutionInfo - (���Z�@@�֏��)<BR>
     * ���Z�@@�֏��
     * @@return WEB3AdminInformProfDistSellTransSrcInfo
     * @@throws WEB3BaseException
     * @@roseuid 464968D702CE
     */
    protected WEB3AdminInformProfDistSellTransSrcInfo getFinancialInstitutionInfo(
        String l_strFinancialInstitutionCode,
        String l_strFinancialBranchCode,
        WEB3AdminInformProfDistSellTransSrcInfo l_financialInstitutionInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getfinancialInstitutionInfo("
            + "String, String, WEB3AdminInformProfDistSellTransSrcInfo)";
        log.entering(STR_METHOD_NAME);

        // �P�j �ȉ��̌��������ŁA���Z�@@�ցi��s�j�}�X�^������
        // [����]
        // ��s�R�[�h = ����:���Z�@@�փR�[�h
        // �x�X�R�[�h = ����:�x�X�R�[�h
        List l_lisFinInstitutionBankRow = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            // [��������]
            StringBuffer l_sbQuery = new StringBuffer();
            l_sbQuery.append(" fin_institution_code = ? ");
            l_sbQuery.append(" and fin_branch_code = ? ");
            Object[] l_dataValues = {l_strFinancialInstitutionCode, l_strFinancialBranchCode};
            l_lisFinInstitutionBankRow = l_queryProcessor.doFindAllQuery(
                FinInstitutionBankRow.TYPE,
                l_sbQuery.toString(),
                l_dataValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �����R�[�h�����݂��Ȃ��ꍇ�A��O���X���[
        // �u���Z�@@�ւ����݂��Ȃ��B�v
        if (l_lisFinInstitutionBankRow.isEmpty())
        {
            log.debug("���Z�@@�ւ����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01314,
                this.getClass().getName() + STR_METHOD_NAME,
                "���Z�@@�ւ����݂��Ȃ��B");
        }

        // ����:���Z�@@�֏��Ɏ擾�����e�l���Z�b�g
        if (l_lisFinInstitutionBankRow.size() == 1)
        {
            FinInstitutionBankRow l_finInstitutionBankRow =
                (FinInstitutionBankRow)l_lisFinInstitutionBankRow.get(0);

            //�x�X��
            l_financialInstitutionInfo.financialBranchName =
                l_finInstitutionBankRow.getFinBranchName();

            //�x�X���i�J�i�j
            l_financialInstitutionInfo.financialBranchNameKana =
                l_finInstitutionBankRow.getFinBranchNameKana();

            //���Z�@@�֖�
            l_financialInstitutionInfo.financialInstitutionName =
                l_finInstitutionBankRow.getFinInstitutionName();

            //���Z�@@�֖��i�J�i�j
            l_financialInstitutionInfo.financialInstitutionNameKana =
                l_finInstitutionBankRow.getFinInstitutionNameKana();
        }

        // �Z�b�g�������Z�@@�֏���ԋp�B
        log.exiting(STR_METHOD_NAME);
        return l_financialInstitutionInfo;
    }

    /**
     * (create��������������)<BR>
     * �����U�֓o�^�}�X�^�̌�������������𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     * �@@����������F "institution_code=?"<BR>
     * <BR>
     * �@@��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * <BR>
     * �@@����������F " and branch_code=?"<BR>
     * <BR>
     * �@@��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �S�j�ڋq�R�[�h<BR>
     * <BR>
     * �@@����������F " and account_code like ? %"<BR>
     * <BR>
     * �@@��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �T�j�w��敪<BR>
     * <BR>
     * �@@����������F " and designate_div=?"<BR>
     * <BR>
     * �@@��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �U�j���i<BR>
     * �@@���N�G�X�g�f�[�^.���i != null �̏ꍇ�A<BR>
     * �@@����������F " and comodity like ?  % "<BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^.���i == null �̏ꍇ�A�@@<BR>
     * �@@����������F " and comodity is null"<BR>
     * <BR>
     * �V�j�����R�[�h<BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^.�����R�[�h != null �̏ꍇ�A<BR>
     * �@@����������F " and fund_code=?"<BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^.�����R�[�h == null �̏ꍇ�A�@@<BR>
     * �@@����������F " and fund_code is null"<BR>
     * <BR>
     * <BR>
     * �W�j�U�֋敪<BR>
     * �@@����������F " and transfer_div= ?"<BR>
     * <BR>
     * �@@��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �X�j���������������ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����`�[�쐬���̓��N�G�X�g�N���X
     * @@return String
     * @@roseuid 4643CD0C03A6
     */
    private String createQueryString(WEB3AdminInformProfDistVoucherMakeInpRequest l_request)
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminInformProfDistVoucherMakeInpRequest)";
        log.entering(STR_METHOD_NAME);

        // �P�j��̕�����𐶐�����B
        StringBuffer l_sbSearchCondition = new StringBuffer();

        // �Q�j�،���ЃR�[�h
        //   ����������F "institution_code=?"
        //   ��L��������P�j�̕�����̖����ɒǉ�����B
        l_sbSearchCondition.append(" institution_code = ? ");

        // �R�j���X�R�[�h
        //   ����������F " and branch_code=?"
        //   ��L��������P�j�̕�����̖����ɒǉ�����B
        l_sbSearchCondition.append(" and branch_code = ? ");

        // �S�j�ڋq�R�[�h
        //   ����������F " and account_code like ? %"
        //   ��L��������P�j�̕�����̖����ɒǉ�����B
        l_sbSearchCondition.append(" and account_code like ? || '%' ");

        // �T�j�w��敪
        //   ����������F " and designate_div=?"
        //   ��L��������P�j�̕�����̖����ɒǉ�����B
        l_sbSearchCondition.append(" and designate_div = ? ");

        // �U�j���i
        // �@@���N�G�X�g�f�[�^.���i != null �̏ꍇ�A
        //   ����������F " and comodity like ?  % "
        // �@@���N�G�X�g�f�[�^.���i == null �̏ꍇ�A�@@
        //   ����������F " and comodity is null"
        if (l_request.product != null)
        {
            l_sbSearchCondition.append(" and comodity like ? || '%' ");
        }
        else
        {
            l_sbSearchCondition.append(" and comodity is null ");
        }

        // �V�j�����R�[�h
        // �@@���N�G�X�g�f�[�^.�����R�[�h != null �̏ꍇ�A
        //   ����������F " and fund_code=?"
        // �@@���N�G�X�g�f�[�^.�����R�[�h == null �̏ꍇ�A�@@
        //   ����������F " and fund_code is null"
        if (l_request.productCode != null)
        {
            l_sbSearchCondition.append(" and fund_code = ? ");
        }
        else
        {
            l_sbSearchCondition.append(" and fund_code is null ");
        }

        // �W�j�U�֋敪
        //   ����������F " and transfer_div= ?"
        //   ��L��������P�j�̕�����̖����ɒǉ�����B
        l_sbSearchCondition.append(" and transfer_div = ? ");

        // �X�j���������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbSearchCondition.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A�擾�����̃f�[�^���X�g�𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     * �@@����.�،���ЃR�[�h���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * <BR>
     * �@@����.���N�G�X�g�f�[�^.���X�R�[�h���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �S�j�ڋq�R�[�h<BR>
     * <BR>
     * �@@����.���N�G�X�g�f�[�^.�ڋq�R�[�h���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �T�j�w��敪<BR>
     * <BR>
     * �@@����.���N�G�X�g�f�[�^.�w��敪���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �U�j���i<BR>
     * <BR>
     * �@@����.���N�G�X�g�f�[�^.���i != null �̏ꍇ�A<BR>
     * �@@����.���N�G�X�g�f�[�^.���i�̓��ꌅ���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �V�j�����R�[�h<BR>
     * <BR>
     * �@@����.���N�G�X�g�f�[�^.�����R�[�h != null �̏ꍇ�A<BR>
     * �@@����.���N�G�X�g�f�[�^.�����R�[�h���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �W�j�U�֋敪<BR>
     * <BR>
     * �@@����.���N�G�X�g�f�[�^.�U�֋敪���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �X�j���X�g����z����擾���A�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return Object[]
     * @@roseuid 4643CD1D03D4
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode,
        WEB3AdminInformProfDistVoucherMakeInpRequest l_request)
    {
        final String STR_METHOD_NAME =
            " createQueryDataContainer(String, WEB3AdminInformProfDistVoucherMakeInpRequest)";
        log.entering(STR_METHOD_NAME);

        // �P�j���ArrayList�𐶐�����B
        List l_lisSearchConditionList = new ArrayList();

        // �Q�j�،���ЃR�[�h
        //   ����.�،���ЃR�[�h���P�j�̃��X�g�ɒǉ�����B
        l_lisSearchConditionList.add(l_strInstitutionCode);

        // �R�j���X�R�[�h
        //   ����.���N�G�X�g�f�[�^.���X�R�[�h���P�j�̃��X�g�ɒǉ�����B
        l_lisSearchConditionList.add(l_request.branchCode);

        // �S�j�ڋq�R�[�h
        //   ����.���N�G�X�g�f�[�^.�ڋq�R�[�h���P�j�̃��X�g�ɒǉ�����B
        l_lisSearchConditionList.add(l_request.accountCode);

        // �T�j�w��敪
        //   ����.���N�G�X�g�f�[�^.�w��敪���P�j�̃��X�g�ɒǉ�����B
        l_lisSearchConditionList.add(l_request.specifyDiv);

        // �U�j���i
        // �@@����.���N�G�X�g�f�[�^.���i != null �̏ꍇ�A
        //   ����.���N�G�X�g�f�[�^.���i�̓��ꌅ���P�j�̃��X�g�ɒǉ�����B
        if (!WEB3StringTypeUtility.isEmpty(l_request.product))
        {
            l_lisSearchConditionList.add(l_request.product.substring(0, 1));
        }

        // �V�j�����R�[�h
        // �@@����.���N�G�X�g�f�[�^.�����R�[�h != null �̏ꍇ�A
        //   ����.���N�G�X�g�f�[�^.�����R�[�h���P�j�̃��X�g�ɒǉ�����B
        if (!WEB3StringTypeUtility.isEmpty(l_request.productCode))
        {
            l_lisSearchConditionList.add(l_request.productCode);
        }

        // �W�j�U�֋敪
        //   ����.���N�G�X�g�f�[�^.�U�֋敪���P�j�̃��X�g�ɒǉ�����B
        l_lisSearchConditionList.add(l_request.transferDiv);

        // �X�j���X�g����z����擾���A�ԋp����B
        Object[] l_strArrSearchConditions = new Object[l_lisSearchConditionList.size()];
        l_lisSearchConditionList.toArray(l_strArrSearchConditions);

        log.exiting(STR_METHOD_NAME);

        return l_strArrSearchConditions;

    }

    /**
     * (is��s�o�^�`�[)<BR>
     * �f�[�^����s�o�^�`�[�����ʂ���B<BR>
     * <BR>
     * ����:�U�֋敪 == 1:��s�U���̏ꍇ�Atrue ��ԋp<BR>
     * <BR>
     * 1:��s�U���ȊO�̏ꍇ�Afalse ��ԋp<BR>
     * @@param l_strTransferDiv - (�U�֋敪)<BR>
     * �U�֋敪
     * @@return boolean
     * @@roseuid 464A94520285
     */
    private boolean isBankRegistVoucher(String l_strTransferDiv)
    {

        final String STR_METHOD_NAME = " isBankRegistVoucher(String)";
        log.entering(STR_METHOD_NAME);

        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_strTransferDiv))
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
     * (�g���K���s)<BR>
     * �g���K�𔭍s����B<BR>
     * <BR>
     * �P�jWEB3MQMessageSpec�𐶐�����B <BR>
     * <BR>
     * �@@WEB3MQMessageSpec(�،���ЃR�[�h, �f�[�^�R�[�h,���[�U�f�[�^)<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     * �@@�f�[�^�R�[�h�F�@@����.�f�[�^�R�[�h<BR>
     * �@@���[�U�f�[�^�F�@@"�Q�F�`�[�f�[�^���M�敪.�A���Ǘ��f�[�^���M"<BR>
     * <BR>
     * �Q�jMQ�g���K�𔭍s����B<BR>
     * <BR>
     * �@@WEB3MQGatewayServiceImpl.send(MQ���b�Z�[�W���e)<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@MQ���b�Z�[�W���e�F ��������WEB3MQMessageSpec<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strDataCode - (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h
     * @@roseuid 464AB196032A
     */
    private void submitMarketTrigger(String l_strInstitutionCode, String l_strDataCode)
    {
        final String STR_METHOD_NAME = " submitMarketTrigger(String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�jWEB3MQMessageSpec�𐶐�����B
        // �f�[�^�R�[�h��"T"��t������悤�ɏC�� 2007.06.22 SCS ����
        WEB3MQMessageSpec l_mqMessageSpec =
            new WEB3MQMessageSpec(l_strInstitutionCode,
                l_strDataCode + "T",
                WEB3InformVoucherDataSendDivDef.INFORM_DATA_SEND);

        // �Q�jMQ�g���K�𔭍s����B
        WEB3MQGatewayService l_mqGatewayService =
            (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);

        log.debug("�g���K�𔭍s����........");
        l_mqGatewayService.send(l_mqMessageSpec);
        log.debug("�g���K�𔭍s����........OK!");
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (validate�`�[���)<BR>
     * �`�[����\���`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�g���K�[���s�敪 == true or �쐬�� != 1:�쐬�ς̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@BUSINESS_ERROR_02798<BR>
     * @@param l_strMakeStatus - (�쐬��)<BR>
     * �쐬��
     * @@param l_blnSubmitMarketTriggerDiv - (�g���K�[���s�敪)<BR>
     * �g���K�[���s�敪
     * @@throws WEB3BaseException
     * @@roseuid 464BF90600DD
     */
    private void validateVoucherCanc(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateVoucherCanc(String, boolean)";
        log.entering(STR_METHOD_NAME);
        // �g���K�[���s�敪 == true or �쐬�� != 1:�쐬�ς̏ꍇ�A
        // ��O���X���[
        if (l_blnSubmitMarketTriggerDiv || (!WEB3VoucherCreateStatusDef.CREATE_COMPLETE.equals(l_strMakeStatus)))
        {
            log.debug("�I�����C�����܂��͓`�[���쐬�܂��͓`�[���M�ς݂̏ꍇ�A����s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02798,
                this.getClass().getName() + STR_METHOD_NAME,
                "�I�����C�����܂��͓`�[���쐬�܂��͓`�[���M�ς݂̏ꍇ�A����s�B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�`�[�ύX)<BR>
     * �`�[�ύX���\���`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@����:�g���K�[���s�敪 == true �̏ꍇ<BR>
     * �@@�P�|�P�j�@@�쐬�󋵂��A0�F���쐬�A4�F��t�G���[ �̂�����ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�u���ɓ`�[���쐬�ς݂̂��߁A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�I�����C�����͓`�[�쐬���s���܂���B�v<BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02786<BR>
     * <BR>
     * �Q�j�@@����:�g���K�[���s�敪 == false �̏ꍇ�@@<BR>
     * �@@�Q�|�P�j�@@�쐬�󋵂��A0�F���쐬�A4�F��t�G���[�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@1�F�쐬�ς� �̂�����ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�u�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B�v<BR>
     * �@@�@@�@@�@@�@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02787<BR>
     * @@param l_strMakeStatus - (�쐬��)<BR>
     * �쐬��
     * @@param l_blnSubmitMarketTriggerDiv - (�g���K�[���s�敪)<BR>
     * �g���K�[���s�敪
     * @@throws WEB3BaseException
     * @@roseuid 4652C4F50098
     */
    private void validateVoucherChg(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateVoucherChg(String, boolean)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@����:�g���K�[���s�敪 == true �̏ꍇ
        if (l_blnSubmitMarketTriggerDiv)
        {
            // �P�|�P�j�@@�쐬�󋵂��A0�F���쐬�A4�F��t�G���[ �̂�����ł��Ȃ��ꍇ�A
            // ��O���X���[�u���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B�v
            if (!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus)
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02786,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���ɓ`�[���쐬�ς݂̂��߁A�I�����C�����͓`�[�쐬���s���܂���B");
            }
        }
        // �Q�j�@@����:�g���K�[���s�敪 == false �̏ꍇ
        else
        {
            // �Q�|�P�j�@@�쐬�󋵂��A0�F���쐬�A4�F��t�G���[�A1�F�쐬�ς� �̂�����ł��Ȃ��ꍇ�A
            // ��O���X���[�u�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B�v
            if ((!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.CREATE_COMPLETE.equals(l_strMakeStatus)))
            {
                log.debug("�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02787,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�`�[�������ς݂̂��ߓ`�[�쐬���s���܂���B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�e��A���ύX���)<BR>
     * �e��A���̕ύX�����X�V����B<BR>
     * <BR>
     * DB�X�V�d�l�u���z���U�֌����`�[�ύX_�e��A���e�[�u��.xls�v�Q��<BR>
     * <BR>
     * �m�X�V�����n<BR>
     * �،���ЃR�[�h = �ύX�O�e��A���s.get�،���ЃR�[�h�i�j<BR>
     * �A����� = �ύX�O�e��A���s.get�A����ʁi�j<BR>
     * ���ʃR�[�h = �ύX�O�e��A���s.get���ʃR�[�h�i�j<BR>
     * ���X�R�[�h = �ύX�O�e��A���s.get���X�R�[�h�i�j<BR>
     * @@param l_beforeChgVariousInformParams - (�ύX�O�e��A���s)<BR>
     * �ύX�O�e��A���s
     * @@param l_afterChgVariousInformParams - (�ύX��e��A���s)<BR>
     * �ύX��e��A���s
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     * @@param l_strDataCode - (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 46539C9401E9
     */
    private void updateVariousInformChgInfo(
        VariousInformParams l_beforeChgVariousInformParams,
        VariousInformParams l_afterChgVariousInformParams,
        String l_strRequestNumber,
        String l_strDataCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " updateVariousInformChgInfo("
            + "VariousInformParams, VariousInformParams, String, String)";
        log.entering(STR_METHOD_NAME);

        // DB�X�V�d�l�u���z���U�֌����`�[�ύX_�e��A���e�[�u��.xls�v�Q��
        // �m�X�V�����n
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            VariousInformParams l_updateVariousInformParams = new VariousInformParams();

            l_updateVariousInformParams = l_afterChgVariousInformParams;

            // �،���ЃR�[�h = �ύX�O�e��A���s.get�،���ЃR�[�h�i�j
            l_updateVariousInformParams.setInstitutionCode(
                l_beforeChgVariousInformParams.getInstitutionCode());

            // �A����� = �ύX�O�e��A���s.get�A����ʁi�j
            l_updateVariousInformParams.setInformDiv(
                l_beforeChgVariousInformParams.getInformDiv());

            // ���ʃR�[�h = �ύX�O�e��A���s.get���ʃR�[�h�i�j
            l_updateVariousInformParams.setRequestNumber(
                l_beforeChgVariousInformParams.getRequestNumber());

            // ���X�R�[�h = �ύX�O�e��A���s.get���X�R�[�h�i�j
            l_updateVariousInformParams.setBranchCode(
                l_beforeChgVariousInformParams.getBranchCode());

            // �ڋq�R�[�h
            if (WEB3StringTypeUtility.isNotEmpty(l_afterChgVariousInformParams.getAccountCode()))
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount)((WEB3GentradeAccountManager)l_finApp.getAccountManager()).getMainAccount(
                        l_afterChgVariousInformParams.getInstitutionCode(),
                        l_afterChgVariousInformParams.getBranchCode(),
                        l_afterChgVariousInformParams.getAccountCode());
                //�ڋq�R�[�h
                l_updateVariousInformParams.setAccountCode(l_mainAccount.getAccountCode());
            }

            // ���҃R�[�h
            l_updateVariousInformParams.setTraderCode(
                l_beforeChgVariousInformParams.getTraderCode());

            // �X�V����
            // �X�V�҃R�[�h(�Ǘ��҃R�[�h)
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();

            String l_strAdministratorCode = l_administrator.getAdministratorCode();
            l_updateVariousInformParams.setLastUpdater(l_strAdministratorCode);

            // �쐬����
            l_updateVariousInformParams.setCreatedTimestamp(
                l_beforeChgVariousInformParams.getCreatedTimestamp());

            // �X�V����
            l_updateVariousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            // �`�[�쐬��(1�F�쐬��)
            l_updateVariousInformParams.setStatus(WEB3VoucherCreateStatusDef.CREATE_COMPLETE);

            // �G���[���R�R�[�h
            l_updateVariousInformParams.setErrorReasonCode(null);

            // �`�[���ʃR�[�h(����:���ʃR�[�h)
            l_updateVariousInformParams.setOrderRequestNumber(l_strRequestNumber);

            // �f�[�^�R�[�h(����:�f�[�^�R�[�h)
            l_updateVariousInformParams.setRequestCode(l_strDataCode);

            // �`�[���M����
            l_updateVariousInformParams.setSendTimestamp(null);

            // �`�[��M����
            l_updateVariousInformParams.setReceiptTimestamp(null);

            l_queryProcessor.doUpdateQuery(l_updateVariousInformParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�ڋq���)<BR>
     * �ڋq�����擾���A�����E���z���E���p����U������ɃZ�b�g����<BR>
     * <BR>
     * �P�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A<BR>
     * �@@�@@�@@�@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�F ����.�U������.���X�R�[�h<BR>
     * �@@�����R�[�h�F ����.�U������.�����R�[�h<BR>
     * <BR>
     * �@@���ڋq�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�A���^�[������B<BR>
     * <BR>
     * �Q�j�ڋq.getDataSourceObject()���R�[�����A�ڋqRow���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�Ȃ�<BR>
     * <BR>
     * �R�j�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����.�U������.�ڋq���i�����j = �ڋqRow.���O�i�c���j<BR>
     * �@@����.�U������.�ڋq���i�J�i�j = �ڋqRow.���O�i�c���P�j<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_afterChgVariousInformParams - (�ύX��e��A���s)<BR>
     * �ύX��e��A���s
     * @@param l_transferInfo - (�U������)<BR>
     * �U������
     * @@throws WEB3BaseException
     * @@roseuid 46539C9401E9
     */
    private void setAccountInfo(String l_strInstitutionCode , WEB3AdminInformProfDistTransferInfo l_transferInfo) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " setAccountInfo("
            + "WEB3AdminInformProfDistTransferInfo)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����B
        //�@@[����]
        //�@@�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //�@@���X�R�[�h�F ����.���X�R�[�h
        //�@@�����R�[�h�F ����.�����R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
            		l_strInstitutionCode,
            		l_transferInfo.branchCode,
            		l_transferInfo.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�Q�j�ڋq.getDataSourceObject()���R�[�����A�ڋqRow���擾����B
        MainAccountRow l_mainAcountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�R�j�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //�@@����.�U������.�ڋq���i�����j = �ڋqRow.���O�i�c���j
        //�@@����.�U������.�ڋq���i�J�i�j = �ڋqRow.���O�i�c���P�j
        l_transferInfo.accountName = l_mainAcountRow.getFamilyName();
        l_transferInfo.accountNameKana = l_mainAcountRow.getFamilyNameAlt1();

        
        log.exiting(STR_METHOD_NAME);
    }
}
@
