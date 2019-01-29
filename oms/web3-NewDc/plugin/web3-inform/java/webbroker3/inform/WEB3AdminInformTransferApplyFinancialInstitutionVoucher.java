head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformTransferApplyFinancialInstitutionVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �U�֐\���i��s�j�`�[(WEB3AdminInformTransferApplyFinancialInstitutionVoucher.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 �Ӑ�(���u) �V�K�쐬 ���f��No.056
Revision History    : 2007/06/22 ����(SCS) �ύX ���f��No.093
Revision History    : 2007/07/11 ����(SCS) �ύX ���f��No.101
*/

package webbroker3.inform;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.HostBankTransVoucherDao;
import webbroker3.accountopen.data.HostBankTransVoucherParams;
import webbroker3.accountopen.data.HostBankTransVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3TransferRangeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�U�֐\���i��s�j�`�[)<BR>
 * �U�֐\���i��s�j�`�[�N���X
 *
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformTransferApplyFinancialInstitutionVoucher
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher.class);

    /**
     * (�e��A���s)<BR>
     * �e��A���s�I�u�W�F�N�g
     */
    private VariousInformParams variousInformParams;

    /**
     * @@roseuid 4663A9D50261
     */
    public WEB3AdminInformTransferApplyFinancialInstitutionVoucher()
    {

    }

    /**
     * (�U�֐\���i��s�j�`�[)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �s�I�u�W�F�N�g��this.�e��A���s�ɃZ�b�g����B<BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s�I�u�W�F�N�g
     * @@roseuid 464D3DD001A4
     */
    public WEB3AdminInformTransferApplyFinancialInstitutionVoucher(VariousInformParams l_variousInformParams)
    {
        // �P�j�s�I�u�W�F�N�g��this.�e��A���s�ɃZ�b�g����B
        this.variousInformParams = l_variousInformParams;
    }

    /**
     * (�U�֐\���i��s�j�`�[)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j�s�I�u�W�F�N�g��this.�e��A���s�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j�e��A�����ʃR�[�h != null �̏ꍇ�A<BR>
     * �@@this.�e��A���s�Ɋe��A�����ʃR�[�h���Z�b�g����B<BR>
     * <BR>
     * �R�j�`�[���ʃR�[�h != null �̏ꍇ�A<BR>
     * �@@this.�e��A���s�ɓ`�[���ʃR�[�h���Z�b�g����B<BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s�I�u�W�F�N�g
     * @@param l_strVoucherRequestNumber - (�`�[���ʃR�[�h)<BR>
     * �`�[���ʃR�[�h
     * @@param l_strVariousInformRequestNumber - (�e��A�����ʃR�[�h)<BR>
     * �e��A�����ʃR�[�h
     * @@roseuid 464AA1B60059
     */
    public WEB3AdminInformTransferApplyFinancialInstitutionVoucher(
        VariousInformParams l_variousInformParams,
        String l_strVoucherRequestNumber,
        String l_strVariousInformRequestNumber)
    {
        // �P�j�s�I�u�W�F�N�g��this.�e��A���s�ɃZ�b�g����B
        this.variousInformParams = l_variousInformParams;

        // �Q�j�e��A�����ʃR�[�h != null �̏ꍇ�A
        // �@@this.�e��A���s�Ɋe��A�����ʃR�[�h���Z�b�g����B
        if (l_strVariousInformRequestNumber != null)
        {
            this.variousInformParams.setRequestNumber(l_strVariousInformRequestNumber);
        }

        // �R�j�`�[���ʃR�[�h != null �̏ꍇ�A
        // �@@this.�e��A���s�ɓ`�[���ʃR�[�h���Z�b�g����B
        if (l_strVoucherRequestNumber != null)
        {
            this.variousInformParams.setOrderRequestNumber(l_strVoucherRequestNumber);
        }
    }

    /**
     * (save��s�o�^�`�[�L���[)<BR>
     * �U�֐\���i��s�j�`�[�iG26�j�L���[�e�[�u���Ƀ��R�[�h���쐬����B<BR>
     * <BR>
     * DB�X�V�d�l�u�U�֐\���i��s�j�`�[�iG26�j�L���[�e�[�u��.xls�v<BR>
     * @@throws WEB3BaseException
     * @@roseuid 464AA4CF002F
     */
    public void saveBankRegistVoucherHost() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveBankRegistVoucherHost()";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            HostBankTransVoucherParams l_hostBankTransVoucherParams = new HostBankTransVoucherParams();

            // ���ʃR�[�h�i�̔Ԃ����l�j
//            l_hostBankTransVoucherParams.setOrderRequestNumber(
//                WEB3StringTypeUtility.formatNumber(HostBankTransVoucherDao.newPkValue()));
            
            // �`�[���ʃR�[�h�̍X�V��ύX 2007.06.22 SCS����------------- Start
            l_hostBankTransVoucherParams.setOrderRequestNumber(variousInformParams.getOrderRequestNumber());
            //--------------------------------------------------------- end

            // �f�[�^�R�[�h(�U�֐\���i��s�j�F�hGI823�h)
            l_hostBankTransVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK);

            // �،���ЃR�[�h(�e��A��Params.�،���ЃR�[�h)
            l_hostBankTransVoucherParams.setInstitutionCode(variousInformParams.getInstitutionCode());

            // ���X�R�[�h(�e��A��Params.���X�R�[�h)
            l_hostBankTransVoucherParams.setBranchCode(variousInformParams.getBranchCode());

            // �ڋq�R�[�h(�e��A��Params.�ڋq�R�[�h)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)((WEB3GentradeAccountManager)l_finApp.getAccountManager()).getMainAccount(
                    variousInformParams.getInstitutionCode(),
                    variousInformParams.getBranchCode(),
                    variousInformParams.getAccountCode());

            l_hostBankTransVoucherParams.setAccountCode(l_mainAccount.getAccountCode());

            // ���҃R�[�h(�e��A��Params.���҃R�[�h�iSONAR�j)
            l_hostBankTransVoucherParams.setTraderCode(variousInformParams.getSonarTraderCode());

            // ���ʃR�[�h�i�����J�݌����q�j(�e��A��Params.���ʃR�[�h)
//            l_hostBankTransVoucherParams.setAccOpenRequestNumber(variousInformParams.getRequestNumber());

            // ----------- ���ʃR�[�h�i�����J�݌����q�j��ALL9���Z�b�g����悤�C��--------SCS���� ----------
            l_hostBankTransVoucherParams.setAccOpenRequestNumber("9999999999999");
            // ----------------------------- end ------------------------------------------------------

            // �`�[�ʔ�(0)
            l_hostBankTransVoucherParams.setSerialNo("0");

            // �U�o�͈�(�e��A��Params.�敪�Q)
            l_hostBankTransVoucherParams.setTransferRange(variousInformParams.getExtDiv2());

            // �w�菤�i�R�[�h(�e��A��Params.�敪�R)
            l_hostBankTransVoucherParams.setProductTypeCodeSpec(variousInformParams.getExtDiv3());

            // �w������R�[�h(�e��A��Params.�����R�[�h)
            l_hostBankTransVoucherParams.setProductCodeSpec(variousInformParams.getFundCode());

            // �o�^�敪(�e��A��Params.�敪�S)
            l_hostBankTransVoucherParams.setRegistDiv(variousInformParams.getExtDiv4());

            // �U�֋敪(�e��A��Params.�敪�T)
            l_hostBankTransVoucherParams.setTransferDiv(variousInformParams.getExtDiv5());

            // �U�֎萔���敪 (�e��A��Params.�敪�U)
            l_hostBankTransVoucherParams.setTransCommission(variousInformParams.getExtDiv6());

            // �戵�敪(�e��A��Params.�敪�V)
            l_hostBankTransVoucherParams.setTransDealDiv(variousInformParams.getExtDiv7());

            // �U�����s�R�[�h(�e��A��Params.�R�[�h�R)
            l_hostBankTransVoucherParams.setFinInstitutionCode(variousInformParams.getExtCode3());

            // �U�����s�x�X�R�[�h(�e��A��Params.�R�[�h�S)
            l_hostBankTransVoucherParams.setFinBranchCode(variousInformParams.getExtCode4());

            // �a����� (�e��A��Params.�敪�P)
            l_hostBankTransVoucherParams.setFinSaveDiv(variousInformParams.getExtDiv1());

            // �����ԍ�(�e��A��Params.�e�L�X�g�X)
            l_hostBankTransVoucherParams.setFinAccountNo(variousInformParams.getExtText9());

            // �������`(�e��A��Params.�e�L�X�g�P�𔼊p�J�i�ɕҏW�����l)
            l_hostBankTransVoucherParams.setFinAccountName(
                WEB3StringTypeUtility.to1byteKana(variousInformParams.getExtText1()));

            // �����敪(0�F������)
            l_hostBankTransVoucherParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

            // ���M���� (null)
            l_hostBankTransVoucherParams.setSendTimestamp(null);

            // �쐬����(��������)
            l_hostBankTransVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            // �X�V����(��������)
            l_hostBankTransVoucherParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            l_queryProcessor.doInsertQuery(l_hostBankTransVoucherParams);
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
     * (delete��s�o�^�`�[�L���[)<BR>
     * �U�֐\���i��s�j�`�[�iG26�j�L���[�e�[�u���̃��R�[�h���폜����B<BR>
     * <BR>
     * �m�폜�����n<BR>
     * ���ʃR�[�h = �e��A���s.get�`�[���ʃR�[�h�i�j and<BR>
     * �f�[�^�R�[�h = "GI823" and<BR>
     * �،���ЃR�[�h = �e��A���s.get�،���ЃR�[�h�i�j and<BR>
     * ���X�R�[�h = �e��A���s.get���X�R�[�h�i�j and<BR>
     * �ڋq�R�[�h = �e��A���s.get�ڋq�R�[�h�i�j<BR>
     * @@throws WEB3BaseException
     * @@roseuid 464D4A9A0389
     */
    public void deleteBankRegistVoucherHost() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteBankRegistVoucherHost()";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_sbQuery = new StringBuffer();
            l_sbQuery.append(" order_request_number = ? ");
            l_sbQuery.append(" and request_code = ? ");
            l_sbQuery.append(" and institution_code = ? ");
            l_sbQuery.append(" and branch_code = ? ");
            l_sbQuery.append(" and account_code = ? ");

            Object[] l_objValues = {
            	variousInformParams.getOrderRequestNumber(),
                WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK,
                variousInformParams.getInstitutionCode(),
                variousInformParams.getBranchCode(),
                variousInformParams.getAccountCode()};

            l_queryProcessor.doDeleteAllQuery(HostBankTransVoucherRow.TYPE,
                l_sbQuery.toString(),
                l_objValues);
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
     * (validate���Z�@@�֏��)<BR>
     * �U�֐\���i��s�j�`�[���쐬����ۂ̃p�����[�^�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j����:�A�����.�R�[�h�R == null �̏ꍇ�A��O���X���[<BR>
     * �u���Z�@@�փR�[�h���w��G���[�v<BR>
     * �@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@BUSINESS_ERROR_02788<BR>
     * <BR>
     * �Q�j����:�A�����.�R�[�h�S == null �̏ꍇ�A��O���X���[<BR>
     * �u�x�X�R�[�h���w��G���[�v<BR>
     * �@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@BUSINESS_ERROR_02789<BR>
     * <BR>
     * �R�j����:�A�����.�敪�Q = 4:�ϗ��ʌ������͌ʖ����@@����<BR>
     * �@@����:�A�����.�敪�R == null �̏ꍇ�A��O���X���[<BR>
     * <BR>
     * �@@�u���i�敪�G���[�v<BR>
     * �@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@BUSINESS_ERROR_02790<BR>
     * <BR>
     * �S�j����:�A�����.�敪�Q != 4:�ϗ��ʌ������͌ʖ����@@����<BR>
     * �@@����:�A�����.�敪�R != null �̏ꍇ�A��O���X���[<BR>
     * <BR>
     * �@@�u���i�敪�G���[�v<BR>
     * �@@�@@classes:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@BUSINESS_ERROR_02790<BR>
     * @@param l_informInfoUnit - (�A�����)<BR>
     * �A�����
     * @@throws WEB3BaseException
     * @@roseuid 4650F613031B
     */
    public void validateFinancialInstitutionInfo(WEB3InformDetailInfoUnit l_informInfoUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateFinancialInstitutionInfo(WEB3InformDetailInfoUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_informInfoUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�j����:�A�����.�R�[�h�R == null �̏ꍇ�A��O���X���[
        // �u���Z�@@�փR�[�h���w��G���[�v
        if (l_informInfoUnit.code3 == null)
        {
            log.debug(STR_METHOD_NAME + "���Z�@@�փR�[�h���w��G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02788,
                this.getClass().getName() + STR_METHOD_NAME,
                "���Z�@@�փR�[�h���w��G���[�B");
        }

        // �Q�j����:�A�����.�R�[�h�S == null �̏ꍇ�A��O���X���[
        // �u�x�X�R�[�h���w��G���[�v
        if (l_informInfoUnit.code4 == null)
        {
            log.debug(STR_METHOD_NAME + "�x�X�R�[�h���w��G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02789,
                this.getClass().getName() + STR_METHOD_NAME,
                "�x�X�R�[�h���w��G���[�B");
        }

        // �R�j����:�A�����.�敪�Q = 4:�ϗ��ʌ������͌ʖ����@@����
        // �@@����:�A�����.�敪�R == null �̏ꍇ�A��O���X���[
        // �u���i�敪�G���[�v
        if (WEB3TransferRangeDef.RESERVE_INDIVIDUAL_ACCOUNT.equals(l_informInfoUnit.div2)
            && l_informInfoUnit.div3 == null)
        {
            log.debug(STR_METHOD_NAME + "���i�敪�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02790,
                this.getClass().getName() + STR_METHOD_NAME,
                "���i�敪�G���[�B");
        }

        // �S�j����:�A�����.�敪�Q != 4:�ϗ��ʌ������͌ʖ����@@����
        // �@@����:�A�����.�敪�R != null �̏ꍇ�A��O���X���[
        // �u���i�敪�G���[�v
        if (!WEB3TransferRangeDef.RESERVE_INDIVIDUAL_ACCOUNT.equals(l_informInfoUnit.div2)
            && l_informInfoUnit.div3 != null)
        {
            log.debug(STR_METHOD_NAME + "���i�敪�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02790,
                this.getClass().getName() + STR_METHOD_NAME,
                "���i�敪�G���[�B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
