head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRealUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������J��UnitServiceImpl(WEB3AccOpenRealUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/23 �����q (���u) �d�l�ύX ���f�� No.112
                 : 2006/11/27 �����q (���u) �c�a�X�V�d�l  No.024�ANo.025
                 : 2006/11/29 �����q(���u) �V�K�쐬 �d�l�ύX ���f�� No.113�A114�A115
                 : 2006/11/29 �����q(���u) �V�K�쐬 �d�l�ύX ���f�� No.116�A117 �c�a�X�V�d�l  No.026
                 : 2006/12/18 �Ԑi �@@(���u) �c�a�X�V�d�l  No.027
Revesion History : 2007/07/31 ���n�m (���u) �d�l�ύX�E�c�a�X�V�d�l No.030�A�c�a���C�A�E�g No.043
Revesion History : 2007/08/02 ���n�m (���u) �d�l�ύX�E�c�a�X�V�d�l No.031
Revesion History : 2007/09/20 �����F (���u) ���f�� No.146
Revesion History : 2008/08/01 ������ (���u) �c�a�X�V�d�l No.036
                 : 2009/01/26 �O���~��Y (SCS) ���f�� No.162
Revesion History : 2009/08/13 �đo�g (���u) �d�l�ύX ���f��176
Revesion History : 2009/09/04 �����F (���u) �d�l�ύX ���f��211
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAccountManagerParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginGroupMemberParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.PasswordUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.accountopen.WEB3AccOpenBatchBranch;
import webbroker3.accountopen.WEB3AccOpenBranch;
import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenHostAccRegVoucher;
import webbroker3.accountopen.WEB3AccOpenLoginAccountRelation;
import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.accountopen.data.BatchBranchParams;
import webbroker3.accountopen.data.BatchPreferencesRow;
import webbroker3.accountopen.data.ExpAccountOpenDao;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.data.HostAccRegVoucherPK;
import webbroker3.accountopen.data.HostAccRegVoucherParams;
import webbroker3.accountopen.data.HostAccRegVoucherRow;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherPK;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherRow;
import webbroker3.accountopen.data.HostAgreeTransVoucherPK;
import webbroker3.accountopen.data.HostAgreeTransVoucherRow;
import webbroker3.accountopen.data.HostBankTransVoucherPK;
import webbroker3.accountopen.data.HostBankTransVoucherRow;
import webbroker3.accountopen.data.HostChargedInfoVoucherPK;
import webbroker3.accountopen.data.HostChargedInfoVoucherRow;
import webbroker3.accountopen.data.HostConditionRegVoucherPK;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.accountopen.data.HostContMrgVoucherPK;
import webbroker3.accountopen.data.HostContMrgVoucherRow;
import webbroker3.accountopen.data.HostFDepositVoucherPK;
import webbroker3.accountopen.data.HostFDepositVoucherRow;
import webbroker3.accountopen.data.HostGpRegVoucherPK;
import webbroker3.accountopen.data.HostGpRegVoucherRow;
import webbroker3.accountopen.data.HostInsiderRegVoucherPK;
import webbroker3.accountopen.data.HostInsiderRegVoucherRow;
import webbroker3.accountopen.data.HostMrfAccVoucherPK;
import webbroker3.accountopen.data.HostMrfAccVoucherRow;
import webbroker3.accountopen.data.HostPostalTransVoucherPK;
import webbroker3.accountopen.data.HostPostalTransVoucherRow;
import webbroker3.accountopen.data.HostRealnameRegVoucherPK;
import webbroker3.accountopen.data.HostRealnameRegVoucherRow;
import webbroker3.accountopen.data.HostStockholderRegVoucherPK;
import webbroker3.accountopen.data.HostStockholderRegVoucherRow;
import webbroker3.accountopen.define.WEB3AccOpenAccountKindDef;
import webbroker3.accountopen.define.WEB3AccOpenLastUpdaterDef;
import webbroker3.accountopen.define.WEB3AccOpenSubAccountTypeDef;
import webbroker3.accountopen.define.WEB3AccOpenTradingPasswordDef;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRealUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AdditionalDepositStopDef;
import webbroker3.common.define.WEB3AssetEvaluationDivDef;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3BatchPreferencesBranchCodeDef;
import webbroker3.common.define.WEB3BatchPreferencesValueDef;
import webbroker3.common.define.WEB3CumulativeAccountDivDef;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3ExclusiveUseAccountStatusDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HtSettlementWayDef;
import webbroker3.common.define.WEB3InformationMailFlagDef;
import webbroker3.common.define.WEB3InitialSetDivDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginDisabledFlagDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MngLockDef;
import webbroker3.common.define.WEB3NameSerialNoDef;
import webbroker3.common.define.WEB3NewAccountDivDef;
import webbroker3.common.define.WEB3OrderPermissionDef;
import webbroker3.common.define.WEB3PersonIdentifyDef;
import webbroker3.common.define.WEB3PosReportDivDef;
import webbroker3.common.define.WEB3PosReportTermDivDef;
import webbroker3.common.define.WEB3QualifiedInstInvestorDivDef;
import webbroker3.common.define.WEB3QuoteTypeDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3SpecialAccDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TradingPowerStopDef;
import webbroker3.common.define.WEB3TradingReportDivDef;
import webbroker3.common.define.WEB3TradingStopDef;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCheckDigitalUtility;
import webbroker3.gentrade.data.CommCodeChgMstRow;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.ExclusiveUseAccountCondParams;
import webbroker3.gentrade.data.ExclusiveUseAccountCondRow;
import webbroker3.gentrade.data.ExclusiveUseAccountParams;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsRow;
import webbroker3.tradingpower.data.TpCashBalanceDao;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���������J��UnitServiceImpl)<BR>
 * ���������J�݂P���T�[�r�X�����N���X<BR>
 * �i�g�����U�N�V��������=TX_CREATE_NEW�j<BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AccOpenRealUnitServiceImpl implements WEB3AccOpenRealUnitService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenRealUnitServiceImpl.class);
    
    /**
     * �g�����U�N�V�������������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���������J�݁i���A���A�g�jprocess�v�Q��<BR>
     * @@return String
     * @@param l_hostAccOpenAccept - (�����J�ݓ`�[�o�^��t�L���[)<BR>
     * �����J�ݓ`�[�o�^��t�L���[
     * @@throws WEB3BaseException
     * @@roseuid 44D06D220070
     */
    public String process(HostAccOpenAcceptParams l_hostAccOpenAccept) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " process(HostAccOpenAcceptParams)";
        log.entering(STR_METHOD_NAME);

        //[�ڋq�o�^�`�[�i�f�P�P�j�L���[()�Ɏw�肷�����]
        //���ʃR�[�h�F�����J�ݓ`�[�o�^��t�L���[.���ʃR�[�h
        String l_strOrderRequestNumber = l_hostAccOpenAccept.getOrderRequestNumber();

        //�،���ЃR�[�h�F�����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h
        String l_strInstitutionCode = l_hostAccOpenAccept.getInstitutionCode();

        //���X�R�[�h�F�����J�ݓ`�[�o�^��t�L���[.���X�R�[�h
        String l_strBranchCode = l_hostAccOpenAccept.getBranchCode();

        //�ڋq�R�[�h�F�����J�ݓ`�[�o�^��t�L���[.�ڋq�R�[�h
        String l_strAccountCode = l_hostAccOpenAccept.getAccountCode();

        //�f�[�^�R�[�h�F�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h
        String l_strRequestCode = l_hostAccOpenAccept.getRequestCode();

        String[] l_acceptReqCodes =
            new String[]{"GI82A","GI82G","GI82C","GI82H","GI82B","GI83G","GI82E",
                         "GI81I", "GI82D", "GI84I", "GI84H", "GI84E", "GI85D", "GI86E"};
        String[] l_registReqCodes =
            new String[]{"GI821","GI827","GI823","GI828","GI822","GI837","GI825",
                         "GI819", "GI824", "GI849", "GI848", "GI845", "GI854", "GI865"};

        String l_strRegistReqCode = null;
        for (int i = 0; i < l_acceptReqCodes.length; i++)
        {
            if (l_acceptReqCodes[i].equals(l_strRequestCode))
            {
                l_strRegistReqCode = l_registReqCodes[i];
                break;
            }
        }

        try
        {
            //�ڋq�o�^�`�[�i�f11�j�L���[�I�u�W�F�N�g���擾����
            WEB3AccOpenHostAccRegVoucher l_accOpenHostAccRegVoucher =
                new WEB3AccOpenHostAccRegVoucher(
                    l_strOrderRequestNumber,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strRegistReqCode);

            //�ڋq�o�^�`�[�i�f�P�P�j�L���[.�o�^�敪 = "1"�i�V�K�j�ȊO�̏ꍇ�A�������I������B
            HostAccRegVoucherParams l_accRegVoucherParams = (HostAccRegVoucherParams)
                l_accOpenHostAccRegVoucher.getDataSourceObject();
            String l_strRegistDiv = l_accRegVoucherParams.getRegistDiv();
            if (!WEB3RegDivDef.NEW.equals(l_strRegistDiv))
            {
                log.entering(STR_METHOD_NAME);
                return null;
            }

            //[�����J�݌����q()�Ɏw�肷�����]
            //�،���ЃR�[�h�F�ڋq�o�^�`�[�i�f�P�P�j�L���[.�،���ЃR�[�h
            String l_strInstitutionCodeTemp =
                l_accRegVoucherParams.getInstitutionCode();

            //���ʃR�[�h�F�ڋq�o�^�`�[�i�f�P�P�j�L���[.���ʃR�[�h�i�����J�݌����q�j
            String l_strAccOpenRequestNumber =
                l_accRegVoucherParams.getAccOpenRequestNumber();

            //�����J�݌����q�I�u�W�F�N�g���擾����
            WEB3AccOpenExpAccountOpen l_expAccountOpen =
                new WEB3AccOpenExpAccountOpen(
                    l_strInstitutionCodeTemp, l_strAccOpenRequestNumber);
            ExpAccountOpenParams l_params = (ExpAccountOpenParams)
                l_expAccountOpen.getDataSourceObject();

            // �����J�݌����q.�����敪 = "0"(�l)�ȊO�̏ꍇ�A�������I������B
            if (!WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(
                l_params.getAccountDiv()))
            {
                log.entering(STR_METHOD_NAME);
                return null;
            }

            //[���X()�Ɏw�肷�����]
            //�،���ЃR�[�h�F�����J�݌����q.�،���ЃR�[�h
            //���X�R�[�h�F�����J�݌����q.���X�R�[�h
            WEB3AccOpenBranch l_accOpenBranch = new WEB3AccOpenBranch(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

            //���O�C���E�A�J�E���g�E�����[�V�����I�u�W�F�N�g���擾����
            //[���O�C���E�A�J�E���g�E�����[�V����()�Ɏw�肷�����]
            //���O�C���^�C�vID�F���X.�ڋq���O�C���^�C�vID
            WEB3AccOpenLoginAccountRelation l_accOpenLoginAccountRelation =
                new WEB3AccOpenLoginAccountRelation(l_accOpenBranch.getAccountTypeId());

            //�o�b�`���X�I�u�W�F�N�g���擾����
            //[�o�b�`���X()�Ɏw�肷�����]
            //�،���ЃR�[�h�F�����J�݌����q.�،���ЃR�[�h
            //���X�R�[�h�F�����J�݌����q,���X�R�[�h
            WEB3AccOpenBatchBranch l_accOpenBatchBranch = new WEB3AccOpenBatchBranch(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

            //get�`�F�b�N�f�W�b�g(�����R�[�h : String)
            //[get�`�F�b�N�f�W�b�g()�Ɏw�肷�����]
            //�����R�[�h�F�����J�݌����q.�����R�[�h
            String l_strCheckDigital =
                WEB3GentradeCheckDigitalUtility.getCheckDigital(
                    l_params.getAccountCode().substring(0, 6));

            //�v���p�e�B�̃Z�b�g
            //���O�C���h�c�F1"�Œ�l�i�ڋq�j
            StringBuffer l_sbLoginId = new StringBuffer();
            // "1"�Œ�l�i�ڋq�j
            l_sbLoginId.append(WEB3AccOpenAccountKindDef.CUSTOMER);
            //�����J�݌����q.�����R�[�h��5���ڂ�6����
            String l_strAccCod = l_expAccountOpen.getAccountCode();
            l_sbLoginId.append(l_strAccCod.substring(4, 6));
            //�����J�݌����q.�،���Ђh�c
            l_sbLoginId.append(l_params.getInstitutionId());
            //�����J�݌����q.���X�R�[�h
            l_sbLoginId.append(l_expAccountOpen.getBranchCode());
            //�����J�݌����q.�����R�[�h
            l_sbLoginId.append(l_expAccountOpen.getAccountCode());
            //get�`�F�b�N�f�W�b�g�i�j�̖߂�l
            l_sbLoginId.append(l_strCheckDigital);

            //���O�C�����[�U��
            StringBuffer l_sbLoginUsername = new StringBuffer();
            //"1"�Œ�l�i�ڋq)
            l_sbLoginUsername.append(WEB3AccOpenAccountKindDef.CUSTOMER);
            //�����J�݌����q.�،���ЃR�[�h
            l_sbLoginUsername.append(l_expAccountOpen.getInstitutionCode());
            //�����J�݌����q.���X�R�[�h
            l_sbLoginUsername.append(l_expAccountOpen.getBranchCode());
            //�����J�݌����q.�����R�[�h
            l_sbLoginUsername.append(l_expAccountOpen.getAccountCode());

            //�����h�c�F
            StringBuffer l_sbAccountId = new StringBuffer();
            // "1"�Œ�l�i�ڋq�j
            l_sbAccountId.append(WEB3AccOpenAccountKindDef.CUSTOMER);
            //�����J�݌����q.�����R�[�h��5���ڂ�6����
            l_sbAccountId.append(l_strAccCod.substring(4, 6));
            //�����J�݌����q.�،���Ђh�c
            l_sbAccountId.append(l_params.getInstitutionId());
            //�����J�݌����q.���X�R�[�h
            l_sbAccountId.append(l_expAccountOpen.getBranchCode());
            //�����J�݌����q.�����R�[�h
            l_sbAccountId.append(l_expAccountOpen.getAccountCode());
            //get�`�F�b�N�f�W�b�g�i�j�̖߂�l
            l_sbAccountId.append(l_strCheckDigital);

            //�����R�[�h
            StringBuffer l_sbAccountCode = new StringBuffer();
            //�����J�݌����q.�����R�[�h
            l_sbAccountCode.append(l_expAccountOpen.getAccountCode().substring(0, 6));
            //get�`�F�b�N�f�W�b�g�i�j�̖߂�l
            l_sbAccountCode.append(l_strCheckDigital);

            //�⏕�����h�c(�Œ蕔��)
            StringBuffer l_sbSubAccountId = new StringBuffer();
            //�����J�݌����q.�،����ID
            l_sbSubAccountId.append(l_params.getInstitutionId());
            //�����J�݌����q.���X�R�[�h
            l_sbSubAccountId.append(l_expAccountOpen.getBranchCode());
            //�����J�݌����q.�����R�[�h
            l_sbSubAccountId.append(l_expAccountOpen.getAccountCode());
            // get�`�F�b�N�f�W�b�g�i�j�̖߂�l
            l_sbSubAccountId.append(l_strCheckDigital);

            //���O�C���e�[�u�����X�V����B
            //[insert���O�C��()�Ɏw�肷�����]
            //���O�C��ID�F���O�C��ID
            //���O�C���^�C�vID�F���O�C���^�C�vID
            //����ID�F����ID
            this.insertLogin(
                l_expAccountOpen,
                l_sbLoginId.toString(),
                l_accOpenBranch.getAccountTypeId(),
                l_sbAccountId.toString());

            //���O�C���E�A�J�E���g�E�}�l�[�W���e�[�u�����X�V����B
            //[insert���O�C���E�A�J�E���g�E�}�l�[�W��()�Ɏw�肷�����]
            //���O�C��ID�F���O�C��ID
            //����ID�F����ID
            //�����[�V�����h�c�F���O�C���E�A�J�E���g�E�����[�V����.�����[�V����ID
            this.insertLoginAccountManager(l_sbLoginId.toString(),
                l_sbAccountId.toString(),
                l_accOpenLoginAccountRelation.getRelationId());

            //���O�C���E�O���[�v�E�����o�e�[�u�����X�V����B
            //[insert���O�C���E�O���[�v�E�����o()�Ɏw�肷�����]
            //�O���[�v�h�c�F���X.�ڋq���O�C���O���[�v�h�c
            //���O�C���h�c�F���O�C���h�c
            this.insertLoginGroupMember(l_accOpenBranch.getAccountGroupId(),
                l_sbLoginId.toString());

            //�����J�݌����q.�����p�X���[�h = NULL�̏ꍇ�A�������{���Ȃ��B
            if (l_params.getInitialPassword() != null)
            {
                //���O�C�������e�[�u�����X�V����B
                //[insert���O�C������()�Ɏw�肷�����]
                //���O�C���h�c�F���O�C���h�c
                this.insertLoginAttribute(l_expAccountOpen, l_sbLoginId.toString());
            }

            //���O�C�����[�U���e�[�u�����X�V����B
            //[insert���O�C�����[�U��()�Ɏw�肷�����]
            //���O�C�����[�U���F���O�C�����[�U��
            //���O�C���h�c�F���O�C���h�c
            this.insertLoginUsername(
                l_sbLoginUsername.toString(), l_sbLoginId.toString());

            //�ڋq�}�X�^�[�e�[�u�����X�V����B
            //[insert�ڋq�}�X�^�[()�Ɏw�肷�����]
            //�����J�݌����q�F�����J�݌����q�I�u�W�F�N�g
            //�ڋq�o�^�`�[�i�f�P�P�j�L���[�F�ڋq�o�^�`�[�i�f�P�P�j�L���[�I�u�W�F�N�g
            //�o�b�`�p���X�F�o�b�`�p���X�I�u�W�F�N�g
            //�����h�c�F����ID
            //�����R�[�h�F�����R�[�h
            this.insertMainAccount(
                l_expAccountOpen,
                l_accOpenHostAccRegVoucher,
                l_accOpenBatchBranch,
                l_sbAccountId.toString(),
                l_sbAccountCode.toString());

            //�⏕�����^�C�v���Ƃɂ�郋�[�v����
            //�@@�⏕�����^�C�vList(List�^�I�u�W�F�N�g)��p�ӂ���B
            List l_listSubAccountType = new ArrayList();
            //�A�⏕�����^�C�vList�փf�[�^���Z�b�g����B
            //�@@�ڋq�o�^�`�[�i�f�P�P�j�L���[.�����J�݂P�i�ی�a��j = "1"�i�J�݁j�̏ꍇ
            if (WEB3AccountOpenDef.OPEN.equals(
                l_accOpenHostAccRegVoucher.getAccountOpenDiv1()))
            {
                //�@@"01"(�����������) ��⏕�����^�C�vList
                l_listSubAccountType.add(
                    WEB3AccOpenSubAccountTypeDef.EQUITY_SUB_ACCOUNT);
            }

            if (WEB3AccountOpenDef.OPEN.equals(
                    l_accOpenHostAccRegVoucher.getAccountOpenDiv3()))
            {
                //�ڋq�o�^�`�[�i�f�P�P�j�L���[.�����J�݂R�i�M�p����j = "1"�i�J�݁j�̏ꍇ
                //"03"(�M�p���) ��⏕�����^�C�vList
                l_listSubAccountType.add(
                    WEB3AccOpenSubAccountTypeDef.FX_MARGIN_SUB_ACCOUNT);
            }

            if (WEB3AccountOpenDef.OPEN.equals(
                    l_accOpenHostAccRegVoucher.getAccountOpenDiv11()))
            {
                //�@@�ڋq�o�^�`�[�i�f�P�P�j�L���[.�����J�݂P�P�i����I�v�V�����j = "1"�i�J�݁j�̏ꍇ
                //"07"(�敨�I�v�V�����؋�������) ��⏕�����^�C�vList�֒ǉ�����B
                l_listSubAccountType.add(
                    WEB3AccOpenSubAccountTypeDef.EQUITY_OPTIONS_SUB_ACCOUNT);
            }

            int l_intListSize = l_listSubAccountType.size();
            for (int i = 0; i < l_intListSize; i++)
            {
                String l_strSubAccountType = (String)l_listSubAccountType.get(i);
                String l_strSubAccountTypeTemp = l_strSubAccountType;

                if (l_strSubAccountType.startsWith("0"))
                {
                    l_strSubAccountType = l_strSubAccountType.substring(1);
                }
                //[insert�⏕�����i�ڋq����c���j()�Ɏw�肷�����]
                //�����J�݌����q�F�����J�݌����q�I�u�W�F�N�g
                //�����h�c�F����ID
                //�⏕�����^�C�v�F�⏕�����^�C�vList[n]�̒l�i���擪��"0"���폜�����l���Z�b�g����B�j
                //�⏕�����h�c�F�⏕�����h�c(�Œ蕔��) + �⏕�����^�C�vList[n]�̒l
                this.insertSubAccount(
                    l_expAccountOpen,
                    l_sbAccountId.toString(),
                    l_strSubAccountType,
                    l_sbSubAccountId + l_strSubAccountTypeTemp);

                //�ڋq����c���i�}�X�^���j���X�V����B
                //[insert�ڋq����c���i�}�X�^���j()�Ɏw�肷�����]
                //�����h�c�F����ID
                //�⏕�����h�c�F�⏕�����h�c(�Œ蕔��) + �⏕�����^�C�vList[n]�̒l
                this.insertTpCashBalance(l_sbAccountId.toString(),
                    l_sbSubAccountId + l_strSubAccountTypeTemp);
            }

            //�A�C�e���̒�`
            // �ڋq�]�͏������X�V����B
            //[insert�ڋq�]�͏���()�Ɏw�肷�����]
            // �����J�݌����q�F�����J�݌����q�I�u�W�F�N�g
            // ����ID�F����ID
            this.insertTradingpowerCalcCondition(l_expAccountOpen,
                l_sbAccountId.toString());

            //insert��p�U�������(�����J�݌����q, String, String)
            //�����J�݌����q�F�����J�݌����q�I�u�W�F�N�g
            //�����h�c�F����ID
            //�����R�[�h�F�����R�[�h
            String l_strExclusiveUseAccountNo = this.insertExclusiveUseAccount(
                l_expAccountOpen,
                l_sbAccountId.toString(),
                l_sbAccountCode.toString());

            //insert�ϑ��萔���ڋq�����o�^�}�X�^�[(�����J�݌����q, String)
            //�����J�݌����q�F�����J�݌����q�I�u�W�F�N�g
            //����ID�F����ID
            this.insertEquityCommissionAccountCondMaster(
                l_expAccountOpen, l_sbAccountId.toString());

            //�����J�݌����q�e�[�u�����X�V����B
            //[update�����J�݌����q()�Ɏw�肷�����]
            // �،���ЃR�[�h�F�ڋq�o�^�`�[�i�f�P�P�j�L���[.�،���ЃR�[�h
            // ���ʃR�[�h�F�ڋq�o�^�`�[�i�f�P�P�j�L���[.���ʃR�[�h
            HostAccRegVoucherRow l_hostAccRegVoucherRow
                = (HostAccRegVoucherRow)l_accOpenHostAccRegVoucher.getDataSourceObject();
            this.updateExpAccountOpen(
                l_hostAccRegVoucherRow.getInstitutionCode(),
                l_hostAccRegVoucherRow.getAccOpenRequestNumber(),
                l_strExclusiveUseAccountNo);
        }
        catch (NotFoundException l_ex)
        {
            log.error(" �e�[�u���ɊY������f�[�^������܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (insert���O�C��)<BR>
     * ���O�C���e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �X�V�����Z�b�g����B<BR>
     * ���O�C���s�̍��ڂɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�P - 1�j �p�X���[�h�̎擾<BR>
     * <BR>
     * �@@�@@���O�C���e�[�u���̃p�X���[�h�Ɉȉ��̒l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�p�X���[�h = this.get�p�X���[�h�i�j�̖߂�l<BR>
     * <BR>
     * �@@�P - 2) ���O�C���L�����̎擾<BR>
     * <BR>
     * �@@�@@���O�C���e�[�u���̃��O�C���L�����Ɉȉ��̒l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�����J�݌����q.�����p�X���[�h = NULL�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���O�C���L���� = "1"(disabled) <BR>
     * <BR>
     *�@@�@@ �A�����J�݌����q.�����p�X���[�h �� NULL�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���O�C���L���� = "0"(enabled) <BR>
     * <BR>
     * �@@�P - 3�j ��L�Q���ڈȊO�̍���<BR>
     * <BR>
     * �@@�@@DB�X�V�d�l�u���O�C���c�a�X�V�d�l.xls�v�Q��<BR>
     * <BR>
     * �Q�j DB�X�V<BR>
     * �@@���O�C���s�I�u�W�F�N�g�̓��e�ŁA���O�C���e�[�u�����X�V�iinsert�j����B
     * @@param l_expAccountOpen - (�����J�݌����q)<BR>
     * �����J�݌����q<BR>
     * @@param l_strLoginId - (���O�C��ID)<BR>
     * ���O�C��ID<BR>
     * @@param l_strLoginTypeId - (���O�C���^�C�vID)<BR>
     * ���O�C���^�C�vID<BR>
     * @@param l_strAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D0799F018F
     */
    private void insertLogin(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strLoginId,
        String l_strLoginTypeId,
        String l_strAccountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " insertLogin(WEB3AccOpenExpAccountOpen�CString, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_expAccountOpen == null ||
                WEB3StringTypeUtility.isEmpty(l_strLoginId) ||
                WEB3StringTypeUtility.isEmpty(l_strLoginTypeId) ||
                WEB3StringTypeUtility.isEmpty(l_strAccountId))
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //�P - 1�j �p�X���[�h�̎擾
            //�p�X���[�h = this.get�p�X���[�h�i�j�̖߂�l
            String l_strHashedPassword = this.getPassword(l_expAccountOpen);
            //�P - 2) ���O�C���L�����̎擾
            //�@@�����J�݌����q.�����p�X���[�h = NULL�̏ꍇ
            //���O�C���L���� = "1"(disabled)
            //�A�����J�݌����q.�����p�X���[�h �� NULL�̏ꍇ
            //���O�C���L���� = "0"(enabled)
            ExpAccountOpenParams l_expAccountOpenParams =
                (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
            String l_strDisabled = null;
            if (l_expAccountOpenParams.getInitialPassword() == null)
            {
                l_strDisabled = WEB3LoginDisabledFlagDef.ACCINFO_DISABLED;
            }
            else
            {
                l_strDisabled = WEB3LoginDisabledFlagDef.ACCINFO_ENABLED;
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            LoginParams l_loginParams = new LoginParams();

            //�P - 3�j ��L�Q���ڈȊO�̍���
            //���O�C��ID <-- ����.���O�C��ID
            long l_lngLoginId = Long.parseLong(l_strLoginId);
            l_loginParams.setLoginId(l_lngLoginId);

            //���O�C���^�C�vID <-- ����.���O�C���^�C�vID
            long l_lngTypeId = Long.parseLong(l_strLoginTypeId);
            l_loginParams.setTypeId(l_lngTypeId);

            //����ID <-- ����.����ID
            long l_lngAccountId = Long.parseLong(l_strAccountId);
            l_loginParams.setAccountId(l_lngAccountId);

            //�����p�X���[�h <-- �u-�v�F���g�p����NotNull���ڂ̂���
            l_loginParams.setInitialPassword("-");

            //�p�X���[�h <-- �p�X���[�h
            l_loginParams.setHashedPassword(l_strHashedPassword);

            //�A�t�B�j�e�B�L�[ <-- null
            l_loginParams.setAffinityKey(null);

            //���O�C���L���� <-- ���O�C���L����
            l_loginParams.setDisabled(Integer.parseInt(l_strDisabled));

            //���O�C���G���[�� <-- null
            l_loginParams.setFailureCount(null);

            //�ŏI���O�C���G���[���� <-- null
            l_loginParams.setLastFailureTimestamp(null);

            // �Q�j DB�X�V
            l_queryProcessor.doInsertQuery(l_loginParams);

        }
        catch (DataException l_e)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�����J�݌����q)<BR>
     * �����J�݌����q�e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j�����J�݌����q�e�[�u���̌���<BR>
     * <BR>
     * �@@���L�̏������A�����J�݌����q�e�[�u������������B<BR>
     * <BR>
     * [����]�@@ <BR>
     * �@@�����J�݌����q.�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�����J�݌����q.���ʃR�[�h = ����.���ʃR�[�h <BR>
     * <BR>
     * �@@�������ʂɈ�v����s�����݂��Ȃ��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3SystemLayerException <BR>
     * �@@�@@�@@�@@�@@�@@�@@tag�@@: SYSTEM_ERROR_80005 <BR>
     * �@@�������ʂ��P���̏ꍇ�A�����o�����X�V����B<BR>
     * <BR>
     * �Q�j�@@�����o�^���̐ݒ�<BR>
     * �@@�P�j�Ō������ꂽ���R�[�h�Ɉȉ��̒l��ݒ肷��B<BR>
     * <BR>
     * �@@[�ݒ�l]<BR>
     * �@@�X�V�����@@�F���������@@<BR>
     * �@@��p�U��������ԍ��@@�F����.��p�U��������ԍ� <BR>
     * <BR>
     * �R�j�@@�X�V����<BR>
     * �@@�Q�j�̐ݒ�l�Ō����J�݌����q�e�[�u���̍X�V�������s���B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strAccOpenRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * @@param l_strExclusiveUseAccountNo - (��p�U��������ԍ�)<BR>
     * ��p�U��������ԍ� <BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D6C48D036B
     */
    private void updateExpAccountOpen(
        String l_strInstitutionCode,
        String l_strAccOpenRequestNumber,
        String l_strExclusiveUseAccountNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExpAccountOpen(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //[����]
            //�����J�݌����q.�،���ЃR�[�h = ����.�،���ЃR�[�h
            //�����J�݌����q.���ʃR�[�h = ����.���ʃR�[�h

            //�P�j�����J�݌����q�e�[�u���̌���
            ExpAccountOpenRow l_row =
                (ExpAccountOpenRow)ExpAccountOpenDao.findRowByInstitutionCodeAccOpenRequestNumber(
                    l_strInstitutionCode,
                    l_strAccOpenRequestNumber);

            if ( l_row == null)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME);
            }

            //�������ʂ��P���̏ꍇ�A�����o�����X�V����B
            else
            {
                ExpAccountOpenParams l_params = new ExpAccountOpenParams(l_row);

                Date l_datProcessDate = GtlUtils.getSystemTimestamp();
                //�X�V�����@@�F��������
                l_params.setLastUpdatedTimestamp(l_datProcessDate);

                //��p�U��������ԍ��@@�F����.��p�U��������ԍ�
                l_params.setExclusiveUseAccountNo(l_strExclusiveUseAccountNo);

                //�R�j�@@�X�V����
                l_queryProcessor.doUpdateQuery(l_params);
            }
        }
        catch (DataException l_e)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert���O�C���E�A�J�E���g�E�}�l�[�W��)<BR>
     * ���O�C���E�A�J�E���g�E�}�l�[�W���e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �X�V�����Z�b�g����B <BR>
     * �@@���O�C���E�A�J�E���g�E�}�l�[�W���s�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@DB�X�V�d�l�u���O�C���E�A�J�E���g�E�}�l�[�W���c�a�X�V�d�l.xls�v�Q��<BR>
     * <BR>
     * �Q�j DB�X�V <BR>
     * �@@���O�C���E�A�J�E���g�E�}�l�[�W���s�I�u�W�F�N�g�̓��e�ŁA���O�C���E�A�J�E���g�E<BR>
     * �}�l�[�W���e�[�u�����X�V�iinsert�j����B <BR>
     * @@param l_strLoginId - (���O�C��ID)<BR>
     * ���O�C��ID<BR>
     * @@param l_strAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strRelationId - (�����[�V�����h�c)<BR>
     * �����[�V�����h�c<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C320056
     */
    private void insertLoginAccountManager(
        String l_strLoginId,
        String l_strAccountId,
        String l_strRelationId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertLoginAccountManager(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (WEB3StringTypeUtility.isEmpty(l_strLoginId)
                || WEB3StringTypeUtility.isEmpty(l_strAccountId)
                || WEB3StringTypeUtility.isEmpty(l_strRelationId))
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            LoginAccountManagerParams l_loginAccManParams =
                new LoginAccountManagerParams();

            //�P�j �X�V�����Z�b�g����B
            //���O�C��ID <-- ����.���O�C��ID
            long l_lngLoginId = Long.parseLong(l_strLoginId);
            l_loginAccManParams.setLoginId(l_lngLoginId);

            //����ID <-- ����.����ID
            long l_lngAccountId = Long.parseLong(l_strAccountId);
            l_loginAccManParams.setAccountId(l_lngAccountId);

            //�����[�V����ID <-- ����.�����[�V�����h�c
            long l_lngRelationId = Long.parseLong(l_strRelationId);
            l_loginAccManParams.setRelationId(l_lngRelationId);

            //�Q�j DB�X�V
            l_queryProcessor.doInsertQuery(l_loginAccManParams);

        }
        catch (DataException l_e)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert���O�C����O���[�v������o)<BR>
     * ���O�C���E�O���[�v�E�����o�e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �X�V�����Z�b�g����B <BR>
     * �@@���O�C���E�O���[�v�E�����o�s�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@DB�X�V�d�l�u���O�C���E�O���[�v�E�����o�c�a�X�V�d�l.xls�v�Q��<BR>
     * <BR>
     * �Q�j DB�X�V <BR>
     * �@@���O�C���E�O���[�v�E�����o�s�I�u�W�F�N�g�̓��e�ŁA���O�C���E�O���[�v�E�����o�e<BR>
     * �[�u�����X�V�iinsert�j����B<BR>
     * @@param l_strGroupId - (�O���[�vID)<BR>
     * �O���[�v�h�c<BR>
     * @@param l_strLoginId - (���O�C��ID)<BR>
     * ���O�C��ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C5303A4
     */
    private void insertLoginGroupMember(
        String l_strGroupId,
        String l_strLoginId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertLoginGroupMember(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (WEB3StringTypeUtility.isEmpty(l_strGroupId)
                || WEB3StringTypeUtility.isEmpty(l_strLoginId))
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            LoginGroupMemberParams l_loginGroupMemberParams = new LoginGroupMemberParams();

            //�P�j �X�V�����Z�b�g����B
            //�O���[�vID <-- ����.�O���[�v�h�c
            long l_lngGroupId = Long.parseLong(l_strGroupId);
            l_loginGroupMemberParams.setGroupId(l_lngGroupId);

            //���O�C��ID <-- ����.���O�C��ID
            long l_lngLoginId = Long.parseLong(l_strLoginId);
            l_loginGroupMemberParams.setLoginId(l_lngLoginId);

            //�Q�j DB�X�V
            l_queryProcessor.doInsertQuery(l_loginGroupMemberParams);
        }
        catch (DataException l_e)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert���O�C�����[�U��)<BR>
     * ���O�C�����[�U���e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �X�V�����Z�b�g����B <BR>
     * �@@���O�C�����[�U���s�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@DB�X�V�d�l�u���O�C�����[�U���c�a�X�V�d�l.xls�v�Q��<BR>
     * <BR>
     * �Q�j DB�X�V <BR>
     * �@@���O�C�����[�U���s�I�u�W�F�N�g�̓��e�ŁA���O�C�����[�U���e�[�u�����X�V�iinsert�j����B<BR>
     * @@param l_strLoginUsername - (���O�C�����[�U��)<BR>
     * ���O�C�����[�U��<BR>
     * @@param l_strLoginId - (���O�C��ID)<BR>
     * ���O�C��ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C7A03B7
     */
    private void insertLoginUsername(
        String l_strLoginUsername,
        String l_strLoginId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertLoginUsername(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (WEB3StringTypeUtility.isEmpty(l_strLoginId))
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            LoginUsernameParams l_loginUsernameParams = new LoginUsernameParams();

            //�P�j �X�V�����Z�b�g����B
            //���O�C�����[�U�� <-- ����.���O�C�����[�U��
            l_loginUsernameParams.setUsername(l_strLoginUsername);

            //���O�C��ID <-- ����.���O�C��ID
            long l_lngLoginId = Long.parseLong(l_strLoginId);
            l_loginUsernameParams.setLoginId(l_lngLoginId);

            //�Q�j DB�X�V
            l_queryProcessor.doInsertQuery(l_loginUsernameParams);

        }
        catch (DataException l_e)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert���O�C������)<BR>
     * ���O�C�������e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �X�V�����Z�b�g����B <BR>
     * �@@���O�C�������s�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@DB�X�V�d�l�u���O�C�������c�a�X�V�d�l.xls�v�Q��<BR>
     * <BR>
     * �Q�j DB�X�V <BR>
     * �@@���O�C�������s�I�u�W�F�N�g�̓��e�ŁA���O�C�������e�[�u�����X�V�iinsert�j����B<BR>
     * <BR>
     * @@param l_expAccountOpen - (�����J�݌����q)<BR>
     * �����J�݌����q<BR>
     * @@param l_strLoginId - (���O�C��ID)<BR>
     * ���O�C��ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C6B0339
     */
    private void insertLoginAttribute(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strLoginId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertLoginAttribute(WEB3AccOpenExpAccountOpen, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_expAccountOpen == null || WEB3StringTypeUtility.isEmpty(l_strLoginId))
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //���������̎擾
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();
            String l_strProcessDate = WEB3DateUtility.formatDate(l_datProcessDate,
                "yyyy.MM.dd HH:mm:ss");

            ExpAccountOpenParams l_expAccountOpenParams =
                (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String[] l_strAttributeNames = null;
            String[] l_strAttributeValue = null;

            long l_lngLoginId = Long.parseLong(l_strLoginId);
            l_strAttributeNames = new String[]
                {WEB3LoginAttributeKeyDef.INITIAL_PASSWORD,
                 WEB3LoginAttributeKeyDef.PASSWORD,
                 WEB3LoginAttributeKeyDef.LAST_PASSWORDCHANGE_UPDATER,
                 WEB3LoginAttributeKeyDef.LAST_PWDCHANGE};

            l_strAttributeValue = new String[]
                {l_expAccountOpenParams.getInitialPassword(),
                 l_expAccountOpenParams.getInitialPassword(),
                 WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN,
                 l_strProcessDate};

            LoginAttributeParams l_loginAttributeParams = new LoginAttributeParams();
            for(int i = 0; i < 4; i++)
            {
                l_loginAttributeParams.setLoginId(l_lngLoginId);
                l_loginAttributeParams.setAttributeName(l_strAttributeNames[i]);
                l_loginAttributeParams.setAttributeValue(l_strAttributeValue[i]);
                l_queryProcessor.doInsertQuery(l_loginAttributeParams);
            }
        }
        catch (DataException l_e)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert�ڋq�}�X�^�[)<BR>
     * �ڋq�}�X�^�[�e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �X�V�����Z�b�g����B <BR>
     * �@@�ڋq�}�X�^�[�s�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@�P - 1�j ����p�X���[�h�̎擾<BR>
     * <BR>
     * �@@�@@�@@�����J�݌����q.�����p�X���[�h = NULL�̏ꍇ<BR>
     * <BR>
     * ����p�X���[�h = "    "(�u�����N4��) <BR>
     * <BR>
     * �@@�@@�A�����J�݌����q.�����p�X���[�h �� NULL�̏ꍇ <BR>
     * <BR>
     * �@@�@@����p�X���[�h = �����J�݌����q.�����p�X���[�h<BR>
     * <BR>
     * �P - 2�j �{�l�m�F�敪�̎擾 <BR>
     * <BR>
     * �@@�@@�@@�����J�݌����q.�{�l�m�F���ދ敪 = NULL�̏ꍇ<BR>
     * <BR>
     * �@@�@@�{�l�m�F���ދ敪 = "0"�i���m�F�j <BR>
     * <BR>
     * �@@�@@�A�����J�݌����q.�{�l�m�F���ދ敪 �� NULL�̏ꍇ <BR>
     * <BR>
     * �@@�@@�{�l�m�F���ދ敪 = "1"�i�m�F�ρj<BR>
     * <BR>
     * �P - 3�j ��L�ȊO�̍���<BR>
     * <BR>
     * �@@�@@DB�X�V�d�l�u�ڋq�}�X�^�[�c�a�X�V�d�l.xls�v�Q�� <BR>
     * <BR>
     * �Q�j DB�X�V <BR>
     * �@@�ڋq�}�X�^�[�s�I�u�W�F�N�g�̓��e�ŁA�ڋq�}�X�^�[�e�[�u�����X�V�iinsert�j����B<BR>
     * <BR>
     * @@param l_expAccountOpen - (�����J�݌����q)<BR>
     * �����J�݌����q<BR>
     * @@param l_hostAccRegVoucher - (�ڋq�o�^�`�[�i�f�P�P�j�L���[)<BR>
     * �ڋq�o�^�`�[�i�f�P�P�j�L���[<BR>
     * @@param l_batchBranch - (�o�b�`�p���X)<BR>
     * �o�b�`�p���X<BR>
     * @@param l_strAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C8B02CE
     */
    private void insertMainAccount(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        WEB3AccOpenHostAccRegVoucher l_hostAccRegVoucher,
        WEB3AccOpenBatchBranch l_batchBranch,
        String l_strAccountId,
        String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertMainAccount(WEB3AccOpenExpAccountOpen," +
            " WEB3AccOpenHostAccRegVoucher, WEB3AccOpenBatchBranch, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_expAccountOpen == null
                || l_hostAccRegVoucher == null
                || l_batchBranch == null
                || WEB3StringTypeUtility.isEmpty(l_strAccountId)
                || WEB3StringTypeUtility.isEmpty(l_strAccountCode))
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            MainAccountParams l_mainAccountParams = new MainAccountParams();

            //�P�j �X�V�����Z�b�g����B
            //�ڋq�}�X�^�[�s�̍��ڂɒl���Z�b�g����B
            ExpAccountOpenParams l_expAccOpenParams =
                (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
            //����p�X���[�h�̎擾
            //�@@�����J�݌����q.�����p�X���[�h = NULL�̏ꍇ
            String l_strTradingPassword = null;
            if (l_expAccOpenParams.getInitialPassword() == null)
            {
                l_strTradingPassword = WEB3AccOpenTradingPasswordDef.BLANK;
            }
            else
            {
                l_strTradingPassword = l_expAccOpenParams.getInitialPassword();
            }

            //�P - 2�j �{�l�m�F�敪�̎擾
            //�@@�����J�݌����q.�{�l�m�F���ދ敪 = NULL�̏ꍇ
            //�{�l�m�F���ދ敪 = "0"�i���m�F�j
            //�A�����J�݌����q.�{�l�m�F���ދ敪 �� NULL�̏ꍇ
            //�{�l�m�F���ދ敪 = "1"�i�m�F�ρj
            String l_strPersonIdentify = null;
            if (l_expAccOpenParams.getIdConfirmDocDiv() == null)
            {
                l_strPersonIdentify = WEB3PersonIdentifyDef.NOT_IDENTIFY;
            }
            else
            {
                l_strPersonIdentify = WEB3PersonIdentifyDef.ALREADY_IDENTIFY;
            }

            HostAccRegVoucherParams l_hostAccRegVoucherParams =
                (HostAccRegVoucherParams)l_hostAccRegVoucher.getDataSourceObject();
            //�����h�c
            l_mainAccountParams.setAccountId(Long.parseLong(l_strAccountId));

            //�،���ЃR�[�h
            l_mainAccountParams.setInstitutionCode(l_expAccOpenParams.getInstitutionCode());

            //�،����ID
            l_mainAccountParams.setInstitutionId(l_expAccOpenParams.getInstitutionId());

            //�����R�[�h
            l_mainAccountParams.setAccountCode(l_strAccountCode);

            //���X�h�c
            l_mainAccountParams.setBranchId(l_expAccOpenParams.getBranchId());

            //���X�R�[�h
            l_mainAccountParams.setBranchCode(l_expAccOpenParams.getBranchCode());

            //���҃R�[�h�iSONAR�j
            l_mainAccountParams.setSonarTraderCode(l_expAccOpenParams.getSonarTraderCode());

            //�����^�C�v
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);

            //���O�i�c���j
            l_mainAccountParams.setFamilyName(l_hostAccRegVoucherParams.getAccountName());

            //���O(�~�h���l�[���j       null
            //���O�i���O�j              null

            //���O�i�c��1�j
            String l_strFamilyNameAalt1 = WEB3StringTypeUtility.toWbyteKana(
                l_hostAccRegVoucherParams.getAccountNameKana());
            l_mainAccountParams.setFamilyNameAlt1(l_strFamilyNameAalt1);

            //���O(�~�h���l�[��1�j     null
            //���O�i���O1�j            null
            //���O�i�c��2�j            null
            //���O(�~�h���l�[��2�j     null
            //���O�i���O2�j            null

            //�X�֔ԍ�
            l_mainAccountParams.setZipCode(l_expAccOpenParams.getZipCode());

            //�i�⏕�j�X�֔ԍ�          null

            //�Z���P
            l_mainAccountParams.setAddressLine1(l_expAccOpenParams.getAddressLine1());

            //�Z���Q
            l_mainAccountParams.setAddressLine2(l_expAccOpenParams.getAddressLine2());

            //�Z���R
            l_mainAccountParams.setAddressLine3(l_expAccOpenParams.getAddressLine3());

            //�Z���P�i�J�i�j
            l_mainAccountParams.setAddressLine1Kana(l_expAccOpenParams.getAddressLine1Kana());

            //�Z���Q�i�J�i�j
            l_mainAccountParams.setAddressLine2Kana(l_expAccOpenParams.getAddressLine2Kana());

            //�Z���R�i�J�i�j
            l_mainAccountParams.setAddressLine3Kana(l_expAccOpenParams.getAddressLine3Kana());

            //�d�b�ԍ�
            l_mainAccountParams.setTelephone(l_expAccOpenParams.getTelephone());

            //�A����d�b�ԍ��i�g�сj
            l_mainAccountParams.setMobile(l_expAccOpenParams.getMobile());

            //�e�`�w�ԍ�
            l_mainAccountParams.setFax(l_expAccOpenParams.getFax());

            //start �Ζ�����
            //�Ζ��於��
            l_mainAccountParams.setOffice(l_expAccOpenParams.getOffice());

            //�Ζ���X�֔ԍ�
            l_mainAccountParams.setOfficeZipCode(l_expAccOpenParams.getOfficeZipCode());

            //�Ζ���Z��
            l_mainAccountParams.setOfficeAddress(l_expAccOpenParams.getOfficeAddress());

            //�Ζ���d�b�ԍ�
            l_mainAccountParams.setOfficeTelephone(l_expAccOpenParams.getOfficeTelephone());

            //��E
            l_mainAccountParams.setPost(l_expAccOpenParams.getPost());
            //end �Ζ�����

            BatchBranchParams l_batchBranchParams =
                (BatchBranchParams)l_batchBranch.getDataSourceObject();
            //������胁�[�����M�t���O
            if (l_batchBranchParams.getEquityOrderExeMailFlag() == BooleanEnum.TRUE.intValue())
            {
                l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            }
            else if (l_batchBranchParams.getEquityOrderExeMailFlag() == BooleanEnum.FALSE.intValue())
            {
                l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.FALSE);
            }

            //��������胁�[�����M�t���O
            if (l_batchBranchParams.getEquityOrderUnexecMailFlag() == BooleanEnum.TRUE.intValue())
            {
                l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            }
            else if (l_batchBranchParams.getEquityOrderUnexecMailFlag() == BooleanEnum.FALSE.intValue())
            {
                l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.FALSE);
            }

            //�敨OP��胁�[�����M�t���O
            if (l_batchBranchParams.getIfoOrderExecMailFlag() == BooleanEnum.TRUE.intValue())
            {
                l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            }
            else if (l_batchBranchParams.getIfoOrderExecMailFlag() == BooleanEnum.FALSE.intValue())
            {
                l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.FALSE);
            }

            //�敨OP����胁�[�����M�t���O
            if (l_batchBranchParams.getIfoOrderUnexecMailFlag() == BooleanEnum.TRUE.intValue())
            {
                l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            }
            else if (l_batchBranchParams.getIfoOrderUnexecMailFlag() == BooleanEnum.FALSE.intValue())
            {
                l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.FALSE);
            }

            //�ē����[�����M�t���O
            String l_strTemp = WEB3StringTypeUtility.formatNumber(
                l_batchBranchParams.getInformationMailFlag());
            if (WEB3InformationMailFlagDef.FALSE.equals(l_strTemp))
            {
                l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
            }
            else if (WEB3InformationMailFlagDef.TRUE.equals(l_strTemp))
            {
                l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
            }
            else if (WEB3InformationMailFlagDef.EXT_ITEM_FLAG4_PRIORITY_FALSE.equals(l_strTemp) ||
                WEB3InformationMailFlagDef.EXT_ITEM_FLAG4_PRIORITY_TRUE.equals(l_strTemp))
            {
                if (BooleanEnum.FALSE.equals(l_expAccOpenParams.getExtItemFlag4()))
                {
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
                }
                else if (BooleanEnum.TRUE.equals(l_expAccOpenParams.getExtItemFlag4()))
                {
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
                }
                else
                {
                    if (WEB3InformationMailFlagDef.EXT_ITEM_FLAG4_PRIORITY_FALSE.equals(l_strTemp))
                    {
                        l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
                    }
                    else if (WEB3InformationMailFlagDef.EXT_ITEM_FLAG4_PRIORITY_TRUE.equals(l_strTemp))
                    {
                        l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
                    }
                }
            }

            //���Z�^�񋏏Z�敪
            l_mainAccountParams.setResident(l_expAccOpenParams.getResident());

            //�V�K�����敪
            l_mainAccountParams.setNewAccountDiv(WEB3NewAccountDivDef.NOT_NEW_ACCOUNT);

            //�M���o�R�敪
            l_mainAccountParams.setViaTrustBankDiv(l_hostAccRegVoucherParams.getTrustViaDiv());

            //�q�w�敪      null

            //email�A�h���X
            if (l_expAccOpenParams.getEmailAddress() == null)
            {
                l_mainAccountParams.setEmailAddress(l_expAccOpenParams.getEmailAddressAlt1());
            }
            else
            {
                l_mainAccountParams.setEmailAddress(l_expAccOpenParams.getEmailAddress());
            }

            //email�A�h���X�P     null

            //����p�X���[�h
            l_mainAccountParams.setTradingPassword(l_strTradingPassword);

            //�����o�^��
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();
            Date l_datProcessDays = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            l_mainAccountParams.setAccountOpenDate(l_datProcessDays);

            //��������           null

            //�����J�݊������[�����M�X�e�[�^�X
            l_mainAccountParams.setAccOpenSendMailStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);

            //�{�l�m�F�敪
            l_mainAccountParams.setPersonIdentify(l_strPersonIdentify);

            //���N�����N��
            l_mainAccountParams.setEraBorn(l_expAccOpenParams.getEraBorn());

            //���N����
            l_mainAccountParams.setBornDate(l_expAccOpenParams.getBornDate());

            //����
            l_mainAccountParams.setSex(l_expAccOpenParams.getSex());

            //�x�q�敪
            l_mainAccountParams.setYellowCustomer(WEB3YellowCustomerDef.NOT_YELLOW_CUSTOMER);

            //�z�[���g���[�h���ϕ��@@
            l_mainAccountParams.setHtSettlementWay(WEB3HtSettlementWayDef.NORMAL_SETTLEMENT);

            //�U����i��s�����j�o�^�敪
            //�����J�݌����q�e�[�u��.�U�֋敪 = NULL�̏ꍇ�u0�v�i���o�^�j
            //�����J�݌����q�e�[�u��.�U�֋敪 �� NULL�̏ꍇ�u1�v�i�o�^�j
            if (l_expAccOpenParams.getTransferDiv() == null)
            {
                l_mainAccountParams.setBankAccountRegi(WEB3BankAccountRegiDef.NOT_REGISTER);
            }
            else
            {
                l_mainAccountParams.setBankAccountRegi(WEB3BankAccountRegiDef.ALREADY_REGISTER);
            }

            //�����X�e�[�^�X
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);

            //�l�����b�N
            l_mainAccountParams.setExaminLockFlag(WEB3MngLockDef.UNLOCK);

            //�Ǘ����b�N
            l_mainAccountParams.setMngLockFlag(WEB3MngLockDef.UNLOCK);

            //�Ǘ����b�N�����J�n��          null
            //�Ǘ����b�N�����I����          null

            //�Ǘ����b�N���R�t���O�i���֋��j
            l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);

            //�Ǘ����b�N���R�t���O�i�ۏ؋������j
            l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);

            //�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j
            l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);

            //�Ǘ����b�N���R�t���O�i�a��ؒ����������j
            l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);

            //�x�X���b�N
            l_mainAccountParams.setBranchLock(WEB3MngLockDef.UNLOCK);

            //�����F��
            l_mainAccountParams.setOrderPermission(WEB3OrderPermissionDef.NO_PERMISSION);

            //�ŋ敪
            //�ŋ敪�i���N�j
            //����������������J�ݓ�
            if (WEB3SpecialAccDef.NORMAL.equals(l_expAccOpenParams.getSpecialAcc()))
            {
                l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
                l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.NORMAL);
                l_mainAccountParams.setEquitySpAccOpenDate(null);
            }
            else if (WEB3SpecialAccDef.SPECIAL.equals(l_expAccOpenParams.getSpecialAcc()))
            {
                l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
                l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL);
                l_mainAccountParams.setEquitySpAccOpenDate(l_datProcessDays);
            }
            else if (WEB3SpecialAccDef.SPECIAL_WITHHOLD.equals(
                l_expAccOpenParams.getSpecialAcc()))
            {
                l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
                l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
                l_mainAccountParams.setEquitySpAccOpenDate(l_datProcessDays);
            }
            else
            {
                l_mainAccountParams.setTaxType(TaxTypeEnum.UNDEFINED);
                l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.UNDEFINED);
            }

            //�M�p����ŋ敪
            //�M�p����ŋ敪�i���N�j
            //�M�p�����������J�ݓ�
            if (WEB3SpecialAccDef.NORMAL.equals(l_expAccOpenParams.getSpecialAccMargin())
                || l_expAccOpenParams.getSpecialAccMargin() == null)
            {
                l_mainAccountParams.setMarginTaxType(TaxTypeEnum.NORMAL);
                l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.NORMAL);
                l_mainAccountParams.setMarginSpAccOpenDate(null);
            }
            else if (WEB3SpecialAccDef.SPECIAL.equals(
                l_expAccOpenParams.getSpecialAccMargin()))
            {
                l_mainAccountParams.setMarginTaxType(TaxTypeEnum.SPECIAL);
                l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.SPECIAL);
                l_mainAccountParams.setMarginSpAccOpenDate(l_datProcessDays);
            }
            else if (WEB3SpecialAccDef.SPECIAL_WITHHOLD.equals(
                l_expAccOpenParams.getSpecialAccMargin()))
            {
                l_mainAccountParams.setMarginTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
                l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
                l_mainAccountParams.setMarginSpAccOpenDate(l_datProcessDays);
            }
            else
            {
                l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
                l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
                l_mainAccountParams.setMarginSpAccOpenDate(null);
            }

            //�K�i�@@�֓����Ƌ敪
            l_mainAccountParams.setQualifiedInstInvestorDiv(
                WEB3QualifiedInstInvestorDivDef.NO_QUALIFIED_INSTITUTIONAL_INVESTOR);

            //���x�M�p��������J�݋敪
            if (WEB3AccountOpenDef.NOT_OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv3()))
            {
                l_mainAccountParams.setMarginSysAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);
            }
            else if (
                WEB3AccountOpenDef.OPEN.equals(
                    l_hostAccRegVoucherParams.getAccountOpenDiv3()))
            {
                l_mainAccountParams.setMarginSysAccOpenDiv(WEB3AccountOpenDef.OPEN);
            }

            //��ʐM�p��������J�݋敪
            l_mainAccountParams.setMarginGenAccOpenDiv(
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);

            //�U�։\��
            l_mainAccountParams.setTransferCount(l_batchBranchParams.getTransferCount());

            //�O���،������J�݋敪
            if (WEB3ForeignSecAccOpenDiv.NOT_OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv5()))
            {
                l_mainAccountParams.setForeignSecAccOpenDiv(
                    WEB3ForeignSecAccOpenDiv.NOT_OPEN);
            }
            else if (WEB3ForeignSecAccOpenDiv.OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv5()))
            {
                l_mainAccountParams.setForeignSecAccOpenDiv(
                    WEB3ForeignSecAccOpenDiv.OPEN);
            }

            //�敨OP�����J�݋敪�i���؁j
            l_mainAccountParams.setIfoAccOpenDivTokyo(
                l_hostAccRegVoucherParams.getIfoAccOpenDivTokyo());

            //�敨OP�����J�݋敪�i��؁j
            l_mainAccountParams.setIfoAccOpenDivOsaka(
                l_hostAccRegVoucherParams.getIfoAccOpenDivOsaka());

            //�敨OP�����J�݋敪�i���؁j
            l_mainAccountParams.setIfoAccOpenDivNagoya(
                l_hostAccRegVoucherParams.getIfoAccOpenDivNagoya());

            //�ݓ������J�݋敪
            if (WEB3AccountOpenDef.NOT_OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv2()))
            {
                l_mainAccountParams.setRuitoAccOpenDiv(
                    WEB3CumulativeAccountDivDef.NOT_ESTABLISH);
            }
            else if (WEB3AccountOpenDef.OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv2()))
            {
                l_mainAccountParams.setRuitoAccOpenDiv(
                    WEB3CumulativeAccountDivDef.ESTABLISH);
            }

            //�l�q�e�����J�݋敪
            //�ڋq�o�^�`�[(G11)�L���[�e�[�u��.�����J�݂Q�i�ϗ������j = �u0�v�i���J�݁j�̏ꍇ�u0�v�i���J�݁j
            //�ڋq�o�^�`�[(G11)�L���[�e�[�u��.�����J�݂Q�i�ϗ������j = �u1�v�i�J�݁j�̏ꍇ�u1�v�i�J�݁j
            if (WEB3AccountOpenDef.NOT_OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv2()))
            {
                l_mainAccountParams.setMrfAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);
            }
            else if (WEB3AccountOpenDef.OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv2()))
            {
                l_mainAccountParams.setMrfAccOpenDiv(WEB3AccountOpenDef.OPEN);
            }

            //�e�w�����J�݋敪
            l_mainAccountParams.setFxAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);

            //�O�������A�g�����J�݋敪
            l_mainAccountParams.setFeqConAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);

            //�擪���ID
            l_mainAccountParams.setTopPageId(l_batchBranchParams.getTopPageId());

            //�����擾�敪
            l_mainAccountParams.setQuotoType(WEB3QuoteTypeDef.REAL_CUSTOMER);

            //start �d�q����
            //����񍐏���t�敪
            l_mainAccountParams.setTradingReportDiv(
                WEB3TradingReportDivDef.MAIL_DISTRIBUTION);

            //����c���񍐏���t�敪
            l_mainAccountParams.setPositionReportDiv(
                WEB3PosReportDivDef.MAIL_DISTRIBUTION);

            //����c���񍐏��쐬�����敪
            l_mainAccountParams.setPositionReportCycleDiv(
                WEB3PosReportTermDivDef.EVERY_MONTH);

            //����c���񍐏��a��؍쐬�t���O
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.FALSE);

            //����c���񍐏��v�Z���쐬�t���O
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);

            //email�A�h���X�X�V�҃R�[�h
            l_mainAccountParams.setEmailLastUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //email�A�h���X�X�V����
            l_mainAccountParams.setEmailLastUpdatedTimestamp(l_datProcessDate);

            //����p�X���[�h�X�V�҃R�[�h
            l_mainAccountParams.setTradingPasswordUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //����p�X���[�h�X�V����
            l_mainAccountParams.setTrPwdLastUpdateTimestamp(l_datProcessDate);

            //�g�єԍ��E�Ζ�����X�V�҃R�[�h
            l_mainAccountParams.setMbOffLastUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //�g�єԍ��E�Ζ�����X�V����
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(l_datProcessDate);

            //��~�󋵍X�V�҃R�[�h
            l_mainAccountParams.setEnableOrderLastUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //��~�󋵍X�V����
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(l_datProcessDate);

            //�e�w�����J�݋敪�X�V�҃R�[�h         null
            //�e�w�����J�݋敪�X�V����             null
            //�O�������A�g�����J�݋敪�X�V�҃R�[�h  null
            //�O�������A�g�����J�݋敪�X�V����      null

            //�l�q�e�ݒ���                 �u�����N
            l_mainAccountParams.setMrfFundCode(" ");

            //�쐬����
            l_mainAccountParams.setCreatedTimestamp(l_datProcessDate);

            //�X�V����
            l_mainAccountParams.setLastUpdatedTimestamp(l_datProcessDate);

            //����Ǘ������J�݋敪
            l_mainAccountParams.setSpMngAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);

            //��~�󋵓o�^���R
            //�ē����[�����M�t���O�X�V�҃R�[�h                   null
            //�ē����[�����M�t���O�X�V����
            //������胁�[�����M�t���O�X�V�҃R�[�h               null
            //������胁�[�����M�t���O�X�V����
            //��������胁�[�����M�t���O�X�V�҃R�[�h              null
            //��������胁�[�����M�t���O�X�V����                  null
            //��OP��胁�[�����M�t���O�X�V�҃R�[�h               null
            ///��OP��胁�[�����M�t���O�X�V����                  null
            //��OP����胁�[�����M�t���O�X�V�҃R�[�h             null
            //��OP����胁�[�����M�t���O�X�V����                 null
            
            //�ڋq�o�^�`�[(G11)�L���[�e�[�u��.�����J�݂P�Q(TBOND�I�v�V�����j = 
            //�u0�v(���J��)�̏ꍇ�u0�v�i���J�݁j
            //�ڋq�o�^�`�[(G11)�L���[�e�[�u��.�����J�݂P�Q(TBOND�I�v�V�����j = 
            //�u1�v(�J��)�̏ꍇ�u1�v�i�J�݁j
            if (WEB3AccountOpenDef.NOT_OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv12()))
            {
                l_mainAccountParams.setStockOptionAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);
            }
            else if (WEB3AccountOpenDef.OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv12()))
            {
                l_mainAccountParams.setStockOptionAccOpenDiv(WEB3AccountOpenDef.OPEN);
            }

            l_mainAccountParams.setSecuredLoanSecAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);

            //�M�p�����J�ݓ�                                    null
            //�敨OP�����J�ݓ�                                  null

            //���o�C����p�����J�݋敪 = �����J�݌����q�e�[�u��.���o�C����p�����敪
            l_mainAccountParams.setOnlyMobileOpenDiv(l_expAccOpenParams.getExtItemDiv15());

            //���o�C����p�����J�݋敪�X�V�҃R�[�h = �uaccount_open�v�i�Œ�l�j
            l_mainAccountParams.setOnlyMblOpnDivLastUpdater(WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //���o�C����p�����J�݋敪�X�V���� = ��������
            l_mainAccountParams.setOnlyMblOpnDivTimestamp(GtlUtils.getSystemTimestamp());

            //�v���A�}�敪 = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�v���E�A�}�敪
            l_mainAccountParams.setProamDiv(l_hostAccRegVoucherParams.getProamDiv());

            //�����@@ = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�O���l�敪�i�����@@�j
            l_mainAccountParams.setBroadcastLaw(l_hostAccRegVoucherParams.getForeignerDivBroadcast());

            //�q��@@ = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�O���l�敪�i�q��@@�j
            l_mainAccountParams.setAviationLaw(l_hostAccRegVoucherParams.getForeignerDivAviation());

            //�m�s�s�@@ = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�O���l�敪�iNTT�@@�j
            l_mainAccountParams.setNttLaw(l_hostAccRegVoucherParams.getForeignerDivNtt());

            //�z�����U���w��敪 = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�z�����U���w��敪
            l_mainAccountParams.setDividendTransDesignate(l_hostAccRegVoucherParams.getDividendTransferDiv());

            //��C�㗝�l = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�㗝�l�敪�i��C�㗝�l�j
            l_mainAccountParams.setStandingProxy(l_hostAccRegVoucherParams.getAgentDivPermanent());

            //�@@��㗝�l = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�㗝�l�敪�i�@@��㗝�l�j
            l_mainAccountParams.setStatutoryAgent(l_hostAccRegVoucherParams.getAgentDivLegal());

            //�Q�j DB�X�V
            //�@@�ڋq�}�X�^�[�s�I�u�W�F�N�g�̓��e�ŁA�ڋq�}�X�^�[�e�[�u�����X�V�iinsert�j����B
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
        }
        catch (DataException l_ex)
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

    /**
     * (insert�ڋq�]�͏���)_<BR>
     * �ڋq�]�͏����e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �X�V�����Z�b�g����B <BR>
     * �@@�ڋq�]�͏����s�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@�P�j - �@@ �ڋq�]�͏����h�c�̐ݒ�<BR>
     * �@@�@@�ڋq�]�͏���.�ڋq�]�͏����h�c = �V�K�̔ԁi*1�j<BR>
     * �@@�@@<BR>
     * �@@�P�j - �A �P�j - �@@ �ȊO�̐ݒ� <BR>
     * �@@�@@DB�X�V�d�l�u�ڋq�]�͏����c�a�X�V�d�l.xls�v�Q��<BR>
     * <BR>
     * �Q�j DB�X�V <BR>
     * �@@�ڋq�]�͏����s�I�u�W�F�N�g�̓��e�ŁA�ڋq�]�͏����e�[�u�����X�V�iinsert�j����B<BR>
     * <BR>
     * <BR>
     * <BR>
     * �@@�@@�i*1�j �ڋq�]�͏����h�c�̐V�K�̔�<BR>
     * �@@�@@�ڋq�]�͏���DAO.newPkValue()���\�b�h�ɂĎ擾����B<BR>
     * �@@�@@�� �ڋq�]�͏���DAO�N���X��DDL��莩����������B<BR>
     * @@param l_expAccountOpen - (�����J�݌����q)<BR>
     * �����J�݌����q<BR>
     * @@param l_strAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07CBF0273
     */
    private void insertTradingpowerCalcCondition(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strAccountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertTradingpowerCalcCondition(WEB3AccOpenExpAccountOpen, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_expAccountOpen == null || WEB3StringTypeUtility.isEmpty(l_strAccountId))
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            TradingpowerCalcConditionParams l_tradPowCalConParams =
                new TradingpowerCalcConditionParams();
            //�P�j �X�V�����Z�b�g����B
            //�ڋq�]�͏���ID <-- xTrade�ɂ�莩���̔Ԃ��ꂽ�l
            long l_lngCalcConId = TradingpowerCalcConditionDao.newPkValue();
            l_tradPowCalConParams.setCalcConditionId(l_lngCalcConId);

            //����ID <-- ����.����ID
            long l_lngAccountID = Long.parseLong(l_strAccountId);
            l_tradPowCalConParams.setAccountId(l_lngAccountID);

            //���XID <-- �����J�݌����q�e�[�u��.���XID
            ExpAccountOpenParams l_expAccOpenParams =
                (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
            l_tradPowCalConParams.setBranchId(l_expAccOpenParams.getBranchId());

            //�a��،��]�����敪 <-- 0:�����{
            l_tradPowCalConParams.setAssetEvaluationDiv(
                WEB3AssetEvaluationDivDef.NOT_ENFORCEMENT);

            //���ʗ��֋����� <-- 0
            l_tradPowCalConParams.setSpecialDebitAmount(0);

            //�����~�敪 <-- 0:����\
            l_tradPowCalConParams.setTradingStop(WEB3TradingStopDef.TRADING_ENABLE);

            //�M�p�V�K���]�͋敪 <-- 0:�]�͉�
            l_tradPowCalConParams.setMarginOpenPositionStop(
                WEB3TradingPowerStopDef.TRADING_POWER_ENABLE);

            //�敨OP�V�K���]�͋敪 <-- 0:�]�͉�
            l_tradPowCalConParams.setIfoOpenPositionStop(
                WEB3TradingPowerStopDef.TRADING_POWER_ENABLE);

            //�o���]�͋敪 <-- 0:�]�͉�
            l_tradPowCalConParams.setPaymentStop(
                WEB3TradingPowerStopDef.TRADING_POWER_ENABLE);

            //���̑����i���t�]�͋敪 <-- 0:�]�͉�
            l_tradPowCalConParams.setOtherTradingStop(
                WEB3TradingPowerStopDef.TRADING_POWER_ENABLE);

            //�X�V�҃R�[�h <-- �uaccount_open�v(�Œ�l)
            l_tradPowCalConParams.setLastUpdater(WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //���������̎擾
            Date l_datProcessDate = null;

            l_datProcessDate = GtlUtils.getSystemTimestamp();
            //�쐬���t <-- ��������

            l_tradPowCalConParams.setCreatedTimestamp(l_datProcessDate);
            //�X�V���t <-- ��������

            l_tradPowCalConParams.setLastUpdatedTimestamp(l_datProcessDate);

            //�Ǐؖ������敪 <-- 0:�Ǐؖ������Ȃ�
            l_tradPowCalConParams.setAdditionalDepositStop(WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_NOT_STOP);

            //�Q�j DB�X�V
            l_queryProcessor.doInsertQuery(l_tradPowCalConParams);
        }
        catch (DataException l_e)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert�⏕����_�ڋq����c��)<BR>
     * �⏕�����i�ڋq����c���j�e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �X�V�����Z�b�g����B <BR>
     * �@@�⏕�����i�ڋq����c���j�s�̍��ڂɒl���Z�b�g����B<BR>
     * <BR>
     * �@@DB�X�V�d�l�u�⏕�����i�ڋq����c���j�c�a�X�V�d�l.xls�v�Q�� <BR>
     * <BR>
     * �Q�j DB�X�V <BR>
     * �@@�⏕�����i�ڋq����c���j�s�I�u�W�F�N�g�̓��e�ŁA�⏕�����i�ڋq����c���j�e�[�u<BR>
     * �����X�V�iinsert�j����B<BR>
     * @@param l_expAccountOpen - (�����J�݌����q)<BR>
     * �����J�݌����q<BR>
     * @@param l_strAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strSubAccountType - (�⏕�����^�C�v)<BR>
     * �⏕�����^�C�v<BR>
     * @@param l_strSubAccountId - (�⏕����ID)<BR>
     * �⏕����ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C9A000F
     */
    private void insertSubAccount(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strAccountId,
        String l_strSubAccountType,
        String l_strSubAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertSubAccount(WEB3AccOpenExpAccountOpen, " +
            "String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_expAccountOpen == null
                || WEB3StringTypeUtility.isEmpty(l_strAccountId)
                || WEB3StringTypeUtility.isEmpty(l_strSubAccountId))
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            SubAccountParams l_subAccountPara = new SubAccountParams();

            //�P�j �X�V�����Z�b�g����B
            //�����h�c <-- ����.�����h�c
            long l_lngAccountId = Long.parseLong(l_strAccountId);
            l_subAccountPara.setAccountId(l_lngAccountId);

            //�⏕�����h�c <-- ����.�⏕�����h�c
            long l_lngSubAccountId = Long.parseLong(l_strSubAccountId);
            l_subAccountPara.setSubAccountId(l_lngSubAccountId);

            //�⏕�����^�C�v <-- ����.�⏕�����^�C�v
            if (WEB3StringTypeUtility.formatNumber(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.intValue()).equals(
                    l_strSubAccountType))
            {
                l_subAccountPara.setSubAccountType(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            else if (WEB3StringTypeUtility.formatNumber(
                SubAccountTypeEnum.FX_MARGIN_SUB_ACCOUNT.intValue()).equals(
                    l_strSubAccountType))
            {
                l_subAccountPara.setSubAccountType(
                    SubAccountTypeEnum.FX_MARGIN_SUB_ACCOUNT);
            }
            else if (WEB3StringTypeUtility.formatNumber(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.intValue()).equals(
                    l_strSubAccountType))
            {
                l_subAccountPara.setSubAccountType(
                    SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            }

            //�،���ЃR�[�h <-- �����J�݌����q�e�[�u��.�،���ЃR�[�h
            l_subAccountPara.setInstitutionCode(l_expAccountOpen.getInstitutionCode());

            //�،����ID <-- �����J�݌����q�e�[�u��.�،����ID
            ExpAccountOpenParams l_expAccOpenParams =
                (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
            l_subAccountPara.setInstitutionId(l_expAccOpenParams.getInstitutionId());

            //���X�h�c <-- �����J�݌����q�e�[�u��.���XID
            l_subAccountPara.setBranchId(l_expAccOpenParams.getBranchId());

            //�⏕�����X�e�[�^�X <--  �u1�v�i�L���j
            l_subAccountPara.setSubAccountStatus(SubAccountStatusEnum.ACTIVE);

            //�����o�^�� <-- null
            l_subAccountPara.setOpenDate(null);

            //�������� <-- NULL
            l_subAccountPara.setCloseDate(null);

            //�c��(�����j <-- 0
            l_subAccountPara.setCashBalance(0);

            //���������̎擾
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();

            //�쐬���t <-- ��������
            l_subAccountPara.setCreatedTimestamp(l_datProcessDate);

            //�X�V���t <-- ��������
            l_subAccountPara.setLastUpdatedTimestamp(l_datProcessDate);

            //�Q�j DB�X�V
            l_queryProcessor.doInsertQuery(l_subAccountPara);

        }
        catch (DataException l_e)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert�ڋq����c��_�}�X�^���)<BR>
     * �ڋq����c���i�}�X�^���j�e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �X�V�����Z�b�g����B <BR>
     * �@@�ڋq����c���i�}�X�^���j�s�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@DB�X�V�d�l�u�ڋq����c���i�}�X�^���j�c�a�X�V�d�l.xls�v�Q��<BR>
     * <BR>
     * �Q�j DB�X�V <BR>
     * �@@�ڋq����c���i�}�X�^���j�s�I�u�W�F�N�g�̓��e�ŁA�ڋq����c���i�}�X�^���j�e<BR>
     * �[�u�����X�V�iinsert�j����B<BR>
     * <BR>
     * <BR>
     * �@@�i*1�j �ڋq����c���}�X�^���h�c�̐V�K�̔�<BR>
     * �@@�@@�ڋq����c���i�}�X�^���jDAO.newPkValue()���\�b�h�ɂĎ擾����B<BR>
     * �@@�@@�� �ڋq����c���i�}�X�^���jDAO�N���X��DDL��莩����������B<BR>
     * @@param l_strAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strSubAccountId - (�⏕����ID)<BR>
     * �⏕����ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07CD50217
     */
    private void insertTpCashBalance(
        String l_strAccountId,
        String l_strSubAccountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertTpCashBalance(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (WEB3StringTypeUtility.isEmpty(l_strAccountId)
                || WEB3StringTypeUtility.isEmpty(l_strSubAccountId))
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            TpCashBalanceParams l_tpCashBalaPara = new TpCashBalanceParams();

            //�P�j �X�V�����Z�b�g����B
            //�ڋq����c���}�X�^���ID <-- xTrade�ɂ�莩���̔Ԃ��ꂽ�l
            long l_lngTpCashBalanceId = TpCashBalanceDao.newPkValue();
            l_tpCashBalaPara.setTpCashBalanceId(l_lngTpCashBalanceId);

            //�����h�c <-- ����.����ID
            long l_lngAccountId = Long.parseLong(l_strAccountId);
            l_tpCashBalaPara.setAccountId(l_lngAccountId);

            //�⏕�����h�c <-- ����.�⏕����ID
            long l_lngSubAccountId = Long.parseLong(l_strSubAccountId);
            l_tpCashBalaPara.setSubAccountId(l_lngSubAccountId);

            //�c���i����+�@@�O���j <-- 0��ݒ�
            l_tpCashBalaPara.setCashBalance0(0);

            //�c���i����+�@@�P���j <-- 0��ݒ�
            l_tpCashBalaPara.setCashBalance1(0);

            //�c���i����+�@@�Q���j <-- 0��ݒ�
            l_tpCashBalaPara.setCashBalance2(0);

            //�c���i����+�@@�R���j <-- 0��ݒ�
            l_tpCashBalaPara.setCashBalance3(0);

            //�c���i����+�@@�S���j <-- 0��ݒ�
            l_tpCashBalaPara.setCashBalance4(0);

            //�c���i����+�@@�T���ȍ~�j <-- 0��ݒ�
            l_tpCashBalaPara.setCashBalance5(0);

            //�l�q�e�c�� <--0��ݒ�
            l_tpCashBalaPara.setMrfBalance(0);

            //�������n�v���z <-- 0��ݒ�
            l_tpCashBalaPara.setCurrentTermCapitalGain(0);

            //�������n�v���z <--0��ݒ�
            l_tpCashBalaPara.setNextMonthCapitalGain(0);

            //�쐬���t <-- ��������
            //���������̎擾
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();
            l_tpCashBalaPara.setCreatedTimestamp(l_datProcessDate);

            //�X�V���t <-- ��������
            l_tpCashBalaPara.setLastUpdatedTimestamp(l_datProcessDate);

            //�Q�j DB�X�V
            l_queryProcessor.doInsertQuery(l_tpCashBalaPara);
        }
        catch (DataException l_e)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�p�X���[�h )<BR>
     * �����J�݌����q�e�[�u����WEB3�Í����`���̈Ïؔԍ��𕜍������A<BR>
     * xTrade-Hash�`���ɈÍ��������l���擾����B<BR>
     * <BR>
     * �P�j�����J�݌����q.�����p�X���[�h�̎擾 <BR>
     * <BR>
     * �@@�Í����p�X���[�h = �����J�݌����q.�����p�X���[�h<BR>
     * <BR>
     * �@@�P - �P�j�����J�݌����q.�����p�X���[�h = NULL �̏ꍇ�A<BR>
     * <BR>
     * �@@�Í����p�X���[�h = NULL ��߂�l�Ƃ��ĕԂ��A�������I������B<BR>
     * <BR>
     * �Q�jWEB3�`���ňÍ������ꂽ�Ïؔԍ��𕜍�������B<BR>
     * <BR>
     * �@@�Í����p�X���[�h�ɂ��āAWEB3Crypt.decrypt()�ɂĕ��������s���B<BR>
     * <BR>
     * �R�jxTrade-Hash�`���ɈÍ���<BR>
     * <BR>
     * �@@�Í����p�X���[�h�ɂ��āAPasswordTool.hashPassword()�ɂĈÍ������s���B<BR>
     * <BR>
     * �S�j�߂�l�̎擾 <BR>
     * <BR>
     * �@@�Í����p�X���[�h���A�߂�l�ɕԂ��B<BR>
     * @@param l_expAccountOpen - (�����J�݌����q)
     * �����J�݌����q
     * @@return String
     */
    private String getPassword(WEB3AccOpenExpAccountOpen l_expAccountOpen)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPassword(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        if (l_expAccountOpen == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //�����J�݌����q.�����p�X���[�h�̎擾
        //�Í����p�X���[�h = �����J�݌����q.�����p�X���[�h
        String l_strHashPassword = null;
        ExpAccountOpenParams l_expAccountOpenParams =
            (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
        //�@@�P - �P�j�����J�݌����q.�����p�X���[�h = NULL �̏ꍇ�A
        //�@@�Í����p�X���[�h = NULL ��߂�l�Ƃ��ĕԂ��A�������I������B
        if (l_expAccountOpenParams.getInitialPassword() == null)
        {
            log.info("�����J�݌����q.�����p�X���[�h = NULL");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            l_strHashPassword = l_expAccountOpenParams.getInitialPassword();
        }

        //�Q�jWEB3�`���ňÍ������ꂽ�Ïؔԍ��𕜍�������B
        //�Í����p�X���[�h�ɂ��āAWEB3Crypt.decrypt()�ɂĕ��������s���B
        WEB3Crypt l_crypt = new WEB3Crypt();
        String l_strPassword = l_crypt.decrypt(l_strHashPassword);

        //�R�jxTrade-Hash�`���ɈÍ���
        //�Í����p�X���[�h�ɂ��āAPasswordTool.hashPassword()�ɂĈÍ������s���B

        log.exiting(STR_METHOD_NAME);
        return PasswordUtils.hashPassword(l_strPassword);

    }

    /**
     * (insert��p�U�������)<BR>
     * ��p�U��������̃f�[�^���쐬����B<BR>
     * <BR>
     * �P�j��p�U��������f�[�^�L���̊m�F <BR>
     * <BR>
     * �@@���L�̏������A��p�U������������e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�����J�݌����q.�،���ЃR�[�h<BR>
     * <BR>
     * �@@�������ʂɈ�v����s�����݂��Ȃ��ꍇ��<BR>
     * �@@�߂�l��NULL���Z�b�g���A�������I������B<BR>
     * <BR>
     * �Q�j���Z�@@�֊i�[�J���������擾����<BR>
     * <BR>
     * �@@���L�̏������A�o�b�`�p�v���t�@@�����X�e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�����J�݌����q.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = "---"<BR>
     * �@@�v���t�@@�����X�� = "acct_open.exp.bank_code_column"<BR>
     * �@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �@@�������ʂɈ�v����s������ꍇ�A���Z�@@�֊i�[�J�������Ƀv���t�@@�����X�̒l���Z�b�g����B<BR>
     *�@@ �������ʂɈ�v����s���Ȃ��ꍇ�A���Z�@@�֊i�[�J��������NULL���Z�b�g����B<BR>
     * <BR>
     * �R�j�w���s�R�[�h���擾����<BR>
     * <BR>
     * �@@���Z�@@�֊i�[�J��������NULL�ȊO�̏ꍇ<BR>
     * �@@����.�����J�݌����q.get���ڒl(���Z�@@�֊i�[�J������)���A�w���s�R�[�h�ɃZ�b�g����B<BR>
     * <BR>
     * �@@���Z�@@�֊i�[�J��������NULL�̏ꍇ<BR>
     * �@@�w���s�R�[�h��NULL���Z�b�g����B<BR>
     * <BR>
     * �S�j��p�U������������e�[�u���̌���<BR>
     * <BR>
     * �@@���L�̏������A��p�U������������e�[�u������������B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h = ����.�����J�݌����q.�،���ЃR�[�h<BR>
     * �@@��s�R�[�h like �w���s�R�[�h || '%'(*1)<BR>
     * �@@�X�e�[�^�X = "0"<BR>
     * <BR>
     * �@@[���בւ�]<BR>
     * �@@�쐬�����A��s�R�[�h�A�x�X�R�[�h�A�����ԍ�<BR>
     * <BR>
     * �@@�������ʂɈ�v����s�����݂��Ȃ��ꍇ��<BR>
     * �@@�߂�l��NULL���Z�b�g���A�������I������B<BR>
     * <BR>
     * �@@(*1)�w���s�R�[�h��NULL�̏ꍇ�A���̏����͎w�肵�Ȃ�<BR>
     * <BR>
     * �T�j��p�U������������e�[�u���̂P���ڂ̃f�[�^���擾����B<BR>
     * <BR>
     * �@@��p�U������������f�[�^ = �S�j�̌�������[0]<BR>
     * <BR>
     * �U�j���Z�@@�ցi��s�j�}�X�^�̌���<BR>
     * <BR>
     * �@@���L�̏������A���Z�@@�ցi��s�j�}�X�^����������B<BR>
     * <BR>
     * �@@[����]<BR>
     *�@@ ��s�R�[�h = ��p�U������������f�[�^.��s�R�[�h<BR>
     * �@@�x�X�R�[�h = ��p�U������������f�[�^.�x�X�R�[�h<BR>
     * <BR>
     * �@@�������ʂɈ�v����s�����݂��Ȃ��ꍇ��<BR>
     * �@@�߂�l��NULL���Z�b�g���A�������I������B<BR>
     * <BR>
     * �@@�U�|�P�j�擾�����f�[�^�̃`�F�b�N<BR>
     * <BR>
     * �@@�@@���Z�@@�ցi��s�j�}�X�^.��s���i�����j = NULL�A�܂���<BR>
     * �@@�@@���Z�@@�ցi��s�j�}�X�^.�x�X���i�����j = NULL�̏ꍇ�A�߂�l��NULL���Z�b�g���A�������I������B<BR>
     * <BR>
     * �V�j�V�X�e���v���t�@@�����X����A�������`�l���擾����<BR>
     * <BR>
     * �@@���L�̏������A�V�X�e���v���t�@@�����X����������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�w���s�R�[�h��NULL�ȊO�̏ꍇ<BR>
     * �@@�@@���� = ����.�����J�݌����q.�،���ЃR�[�h + "_FIN_ACCOUNT_NAME_" + �w���s�R�[�h<BR>
     * �@@�w���s�R�[�h��NULL�̏ꍇ<BR>
     * �@@�@@���� = ����.�����J�݌����q.�،���ЃR�[�h + "_FIN_ACCOUNT_NAME" <BR>
     * <BR>
     * �@@�������ʂɈ�v����s������ꍇ�A�������`�l�ɃV�X�e���v���t�@@�����X.�l���Z�b�g����B<BR>
     * �@@�������ʂɈ�v����s���Ȃ��ꍇ�A�������`�l��NULL���Z�b�g����B<BR>
     * <BR>
     * �W�j�X�V�����Z�b�g����B<BR>
     * <BR>
     * �@@��p�U��������s�̍��ڂɒl���Z�b�g����B<BR>
     * <BR>
     * �@@DB�X�V�d�l�u��p�U��������c�a�X�V�d�l.xls�v�Q��<BR>
     * <BR>
     * �X�jDB�X�V<BR>
     * <BR>
     * �@@��p�U��������s�I�u�W�F�N�g�̓��e�ŁA��p�U��������e�[�u�����X�V�iinsert�j����B<BR>
     * <BR>
     * �P�O�j�X�V�����Z�b�g����B<BR>
     * <BR>
     * �@@��p�U������������f�[�^�̍��ڂɒl���Z�b�g����B<BR>
     * <BR>
     * �@@DB�X�V�d�l�u��p�U������������c�a�X�V�d�l.xls�v�Q��<BR>
     * <BR>
     * �P�P�jDB�X�V<BR>
     * <BR>
     * �@@��p�U������������f�[�^�̓��e�ŁA��p�U������������e�[�u�����X�V�iupdate�j����B<BR>
     * <BR>
     * �P�Q�j�����ԍ���Ԃ�<BR>
     * <BR>
     * �@@��p�U��������ԍ� = ��p�U������������f�[�^.��s�R�[�h + "-"<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ��p�U������������f�[�^.�x�X�R�[�h + "-"<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ��p�U������������f�[�^.�����ԍ�<BR>
     * <BR>
     * �@@�߂�l�ɐ�p�U������������f�[�^.�����ԍ����Z�b�g���A�������I������B<BR>
     * @@param l_expAccountOpen - (�����J�݌����q)<BR>
     * �����J�݌����q<BR>
     * @@param l_strAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    private String insertExclusiveUseAccount(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strAccountId,
        String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " insertExclusiveUseAccount(WEB3AccOpenExpAccountOpen, String, String)";
        log.entering(STR_METHOD_NAME);
        try
        {
            if (l_expAccountOpen == null || WEB3StringTypeUtility.isEmpty(l_strAccountId))
            {
                log.debug("�p�����[�^�l�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            String l_strColumnName = null;     //���Z�@@�֊i�[�J������
            String l_strBankCode = null;        //�w���s�R�[�h
            String l_strFinAccountName = null;   //�������`�l
            //�P�j��p�U��������f�[�^�L���̊m�F
            //��p�U������������e�[�u������������B
            //[����] �،���ЃR�[�h = ����.�����J�݌����q.�،���ЃR�[�h
            String l_strInstitutionCode = l_expAccountOpen.getInstitutionCode();
            String l_strWhere = " institution_code = ? ";
            Object[] l_objs = {l_strInstitutionCode};
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRecordexcs = l_queryProcessor.doFindAllQuery(
                ExclusiveUseAccountCondRow.TYPE,
                l_strWhere,
                l_objs);

            //�������ʂɈ�v����s�����݂��Ȃ��ꍇ��
            //�߂�l��NULL���Z�b�g���A�������I������B
            if (l_lisRecordexcs == null || l_lisRecordexcs.isEmpty())
            {
                log.info("�������ʂɈ�v����s�����݂��Ȃ��ꍇ");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //�Q�j���Z�@@�֊i�[�J���������擾����
            //�o�b�`�p�v���t�@@�����X�e�[�u������������
            //�@@[����]
            //�،���ЃR�[�h = ����.�����J�݌����q.�،���ЃR�[�h
            //���X�R�[�h = "---"
            //�v���t�@@�����X�� = "acct_open.exp.bank_code_column"
            //�v���t�@@�����X���̘A�� = 1
            l_strWhere =
                " institution_code = ? and " +
                " branch_code = ? and " +
                " name = ? and " +
                " name_serial_no = ? ";

            Object[] l_objBats =
                {l_strInstitutionCode,
                 WEB3BatchPreferencesBranchCodeDef.COMMON_SETTING,
                 WEB3BatchPreferencesValueDef.ACCT_OPEN_EXP_BANK_CODE_COLUMN,
                 WEB3NameSerialNoDef.SERIAL_NO};
            List l_listRecordbats = l_queryProcessor.doFindAllQuery(
                BatchPreferencesRow.TYPE,
                l_strWhere,
                l_objBats);
            //�������ʂɈ�v����s������ꍇ�A���Z�@@�֊i�[�J�������Ƀv���t�@@�����X�̒l���Z�b�g����B
            //�������ʂɈ�v����s���Ȃ��ꍇ�A���Z�@@�֊i�[�J��������NULL���Z�b�g����B
            if (l_listRecordbats != null && !l_listRecordbats.isEmpty())
            {
                log.info("�������ʂɈ�v����s������ꍇ�A���Z�@@�֊i�[�J�������Ƀv���t�@@�����X�̒l���Z�b�g����B");

                BatchPreferencesRow l_batchPreferencesRow =
                    (BatchPreferencesRow)l_listRecordbats.get(0);
                l_strColumnName = l_batchPreferencesRow.getValue();
            }

            //�R�j�w���s�R�[�h���擾����
            //���Z�@@�֊i�[�J��������NULL�ȊO�̏ꍇ
            //����.�����J�݌����q.get���ڒl(���Z�@@�֊i�[�J������)���A�w���s�R�[�h�ɃZ�b�g����B
            //���Z�@@�֊i�[�J��������NULL�̏ꍇ
            //�w���s�R�[�h��NULL���Z�b�g����B
            if (l_strColumnName != null)
            {
                l_strBankCode =
                    (String)l_expAccountOpen.getItemValue(l_strColumnName);
            }

            //�S�j��p�U������������e�[�u���̌���
            //[����]
            //�،���ЃR�[�h = ����.�����J�݌����q.�،���ЃR�[�h
            //��s�R�[�h like �w���s�R�[�h || '%'(*1)
            //�X�e�[�^�X = "0"
            //(*1)�w���s�R�[�h��NULL�̏ꍇ�A���̏����͎w�肵�Ȃ�
            l_strWhere = " institution_code = ? and ";
            Object[] l_objConds = null;
            if (l_strBankCode != null)
            {
                l_strWhere =
                    l_strWhere +  " fin_institution_code like ?||'%' and " + " status = ? ";
                l_objConds =
                    new Object[]{
                        l_strInstitutionCode,
                        l_strBankCode,
                        WEB3ExclusiveUseAccountStatusDef.UNUSED_RECORD};
            }
            else
            {
                l_strWhere = l_strWhere + " status = ? ";
                l_objConds =
                    new Object[]{l_strInstitutionCode, WEB3ExclusiveUseAccountStatusDef.UNUSED_RECORD};
            }
            //�@@[���בւ�]
            //�쐬�����A��s�R�[�h�A�x�X�R�[�h�A�����ԍ�
            String l_strSort = "created_timestamp, fin_institution_code, fin_branch_code, fin_account_no ASC";
            List l_lisRecordConds = l_queryProcessor.doFindAllQuery(
                ExclusiveUseAccountCondRow.TYPE,
                l_strWhere,
                l_strSort,
                null,
                l_objConds);
            //�������ʂɈ�v����s�����݂��Ȃ��ꍇ��
            //�߂�l��NULL���Z�b�g���A�������I������B
            if (l_lisRecordConds == null || l_lisRecordConds.isEmpty())
            {
                log.info("�������ʂɈ�v����s�����݂��Ȃ��ꍇ");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //�T�j��p�U������������e�[�u���̂P���ڂ̃f�[�^���擾����B
            //��p�U������������f�[�^ = �S�j�̌�������[0]
            ExclusiveUseAccountCondRow l_accountCondRow =
                (ExclusiveUseAccountCondRow)l_lisRecordConds.get(0);

            //�U�j���Z�@@�ցi��s�j�}�X�^�̌���
            //���L�̏������A���Z�@@�ցi��s�j�}�X�^����������B
            //[����]
            //��s�R�[�h = ��p�U������������f�[�^.��s�R�[�h
            //�x�X�R�[�h = ��p�U������������f�[�^.�x�X�R�[�h
            String l_strFinInstitutionCode = l_accountCondRow.getFinInstitutionCode();
            String l_strFinBranchCode = l_accountCondRow.getFinBranchCode();
            l_strWhere = " fin_institution_code = ? and " + " fin_branch_code = ? ";
            Object[] l_objBanks = {l_strFinInstitutionCode, l_strFinBranchCode};
            List l_listRecordBanks = l_queryProcessor.doFindAllQuery(
                FinInstitutionBankRow.TYPE,
                l_strWhere,
                l_objBanks);
            //�������ʂɈ�v����s�����݂��Ȃ��ꍇ��
            //�߂�l��NULL���Z�b�g���A�������I������B
            if (l_listRecordBanks == null || l_listRecordBanks.size() == 0)
            {
                log.info("�������ʂɈ�v����s�����݂��Ȃ��ꍇ");
                return null;
            }
            //�U�|�P�j�擾�����f�[�^�̃`�F�b�N
            //���Z�@@�ցi��s�j�}�X�^.��s���i�����j = NULL�A�܂���
            //���Z�@@�ցi��s�j�}�X�^.�x�X���i�����j = NULL�̏ꍇ�A�߂�l��NULL���Z�b�g���A�������I������B
            FinInstitutionBankRow l_finInstitutionBankRow =
                (FinInstitutionBankRow)l_listRecordBanks.get(0);
            String l_finInstitutionName = l_finInstitutionBankRow.getFinInstitutionName();
            String l_finBranchName = l_finInstitutionBankRow.getFinBranchName();
            if (l_finInstitutionName == null || l_finBranchName == null)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            String l_strRegx = "(�@@| )+$";
            l_finInstitutionName = l_finInstitutionName.replaceAll(l_strRegx, "");
            l_finBranchName = l_finBranchName.replaceAll(l_strRegx, "");
            //�V�j�V�X�e���v���t�@@�����X����A�������`�l���擾����
            //���L�̏������A�V�X�e���v���t�@@�����X����������B
            //[����]
            //�w���s�R�[�h��NULL�ȊO�̏ꍇ
            //  ���� = ����.�����J�݌����q.�،���ЃR�[�h + "_FIN_ACCOUNT_NAME_" + �w���s�R�[�h
            //�w���s�R�[�h��NULL�̏ꍇ
            //  ���� = ����.�����J�݌����q.�،���ЃR�[�h + "_FIN_ACCOUNT_NAME"

            String l_strName = null;
            if (l_strBankCode == null)
            {
                l_strName =
                    l_strInstitutionCode + WEB3SystemPreferencesNameDef.FIN_ACCOUNT_NAME;
            }
            else
            {
                l_strName =
                    l_strInstitutionCode + WEB3SystemPreferencesNameDef.FIN_ACCOUNT_NAME + "_" + l_strBankCode;
            }
            SystemPreferencesRow l_sysPreRow = SystemPreferencesDao.findRowByName(l_strName);

            //�������ʂɈ�v����s������ꍇ�A�������`�l�ɃV�X�e���v���t�@@�����X.�l���Z�b�g����B
            //�������ʂɈ�v����s���Ȃ��ꍇ�A�������`�l��NULL���Z�b�g����B
            if (l_sysPreRow != null)
            {
                l_strFinAccountName = l_sysPreRow.getValue();
            }

            //�W�j�X�V�����Z�b�g����B
            //��p�U��������s�̍��ڂɒl���Z�b�g����B
            ExclusiveUseAccountParams l_exclusiveUseAccountParams =
                new ExclusiveUseAccountParams();
            //�����h�c= ����.����ID
            long l_lngAccountId = Long.parseLong(l_strAccountId);
            l_exclusiveUseAccountParams.setAccountId(l_lngAccountId);
            //�،���ЃR�[�h= ����.�����J�݌����q.�،���ЃR�[�h
            l_exclusiveUseAccountParams.setInstitutionCode(l_strInstitutionCode);
            //���X�R�[�h= ����.�����J�݌����q.���X�R�[�h
            l_exclusiveUseAccountParams.setBranchCode(l_expAccountOpen.getBranchCode());
            //�����R�[�h= ����.�����R�[�h
            l_exclusiveUseAccountParams.setAccountCode(l_strAccountCode);
            //��s��= ���Z�@@�ցi��s�j�}�X�^.��s���i�����j
            l_exclusiveUseAccountParams.setFinInstitutionName(l_finInstitutionName);
            //�x�X��= ���Z�@@�ցi��s�j�}�X�^.�x�X���i�����j
            l_exclusiveUseAccountParams.setFinBranchName(l_finBranchName);
            //�x�X�R�[�h= ��p�U������������f�[�^.�x�X�R�[�h
            l_exclusiveUseAccountParams.setFinBranchCode(
                l_accountCondRow.getFinBranchCode());
            //������ޖ�= ��p�U������������f�[�^.������ޖ�
            l_exclusiveUseAccountParams.setFinAccountTypeName(
                l_accountCondRow.getFinAccountTypeName());
            //�����ԍ�= ��p�U������������f�[�^.�����ԍ�
            l_exclusiveUseAccountParams.setFinAccountNo(
                l_accountCondRow.getFinAccountNo());
            //�������`�l= �������`�l
            l_exclusiveUseAccountParams.setFinAccountName(l_strFinAccountName);
            //�X�V�҃R�[�h= �uaccount_open�v�i�Œ�l�j
            l_exclusiveUseAccountParams.setLastUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);
            //���������̎擾
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();
            //�쐬����= ��������
            l_exclusiveUseAccountParams.setCreatedTimestamp(l_datProcessDate);
            //�X�V����= ��������
            l_exclusiveUseAccountParams.setLastUpdatedTimestamp(l_datProcessDate);
            //��s�R�[�h= ��p�U������������f�[�^.��s�R�[�h
            l_exclusiveUseAccountParams.setFinInstitutionCode(
                l_accountCondRow.getFinInstitutionCode());

            //�X�jDB�X�V
            //��p�U��������s�I�u�W�F�N�g�̓��e�ŁA��p�U��������e�[�u�����X�V�iinsert�j����B
            l_queryProcessor.doInsertQuery(l_exclusiveUseAccountParams);

            //�P�O�j�X�V�����Z�b�g����B
            //�@@��p�U������������f�[�^�̍��ڂɒl���Z�b�g����B
            ExclusiveUseAccountCondParams l_exclusiveUseAccountCondParams =
                new ExclusiveUseAccountCondParams(l_accountCondRow);
            //�،���ЃR�[�h   �i�����l�j
            //��s�R�[�h   �i�����l�j
            //�x�X�R�[�h    �i�����l�j
            //������ޖ�   �i�����l�j
            //�����ԍ�     �i�����l�j
            //�쐬����     �i�����l�j

            //�X�e�[�^�X   1:�g�p�ς݃��R�[�h
            l_exclusiveUseAccountCondParams.setStatus(
                WEB3ExclusiveUseAccountStatusDef.USED_RECORD);
            //�X�V�҃R�[�h    �uaccount_open�v�i�Œ�l�j
            l_exclusiveUseAccountCondParams.setLastUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);
            //�X�V����  ��������
            l_exclusiveUseAccountCondParams.setLastUpdatedTimestamp(l_datProcessDate);

            //�P�P�jDB�X�V
            //�@@��p�U������������f�[�^�̓��e�ŁA��p�U������������e�[�u�����X�V�iupdate�j����B
            l_queryProcessor.doUpdateQuery(l_exclusiveUseAccountCondParams);

            //�P�Q�j�����ԍ���Ԃ�
            // ��p�U��������ԍ� = ��p�U������������f�[�^.��s�R�[�h + "-"
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ��p�U������������f�[�^.�x�X�R�[�h + "-"
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ��p�U������������f�[�^.�����ԍ�
            //�@@�߂�l�ɐ�p�U��������ԍ����Z�b�g���A�������I������B
            String l_strFinAccountNo =
                l_accountCondRow.getFinInstitutionCode() + "-"
                + l_accountCondRow.getFinBranchCode() + "-"
                + l_accountCondRow.getFinAccountNo();

            log.exiting(STR_METHOD_NAME);
            return l_strFinAccountNo;
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME, l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME, l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
    }

    /**
     * (insert�ϑ��萔���ڋq�����o�^�}�X�^�[ )<BR>
     *�ϑ��萔���ڋq�����o�^�}�X�^�[�e�[�u�����X�V����B <BR>
     *<BR>
     *�@@�P�j �萔���R�[�X�R�[�h�ϊ��}�X�^�e�[�u���̌����@@ <BR>
     *<BR>
     *�@@���L�̏������A��������B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@�萔���R�[�X�R�[�h�ϊ��}�X�^.���XID = ����.�����J�݌����q.���XID <BR>
     *�@@�萔���R�[�X�R�[�h�ϊ��}�X�^.�����ݒ�敪 = "1" <BR>
     *�@@�萔���R�[�X�R�[�h�ϊ��}�X�^.�K�p�J�n�� �� �����c�Ɠ��iWEB3GentradeBizDate.roll(0)�j  <BR>
     *<BR>
     *�@@[���בւ�] <BR>
     *�@@�K�p�J�n���̍~�� <BR>
     *<BR>
     *�@@�������ʂɈ�v����s�����݂��Ȃ��ꍇ�� <BR>
     *�@@�������I������B <BR>
     *<BR>
     *�@@�Q�j �P�j�Ŏ擾����������Loop�������s���B <BR>
     *<BR>
     *�@@���jLoop�������s���O�ɁAHashMap ���`���Ă����B <BR>
     *<BR>
     *�@@�@@�Q - �P�j �����ݒ�敪�̏d���`�F�b�N <BR>
     *<BR>
     *�@@�@@HashMap �� containsKey(Objet key) ���\�b�h�ɂ��A�P�j�Ŏ擾�����萔�����i�R�[�h��Key�Ɍ������A<BR>
     *�@@�@@�߂�l��true�̏ꍇ�͎萔�����i�R�[�h�����ɑ��݂���ׁA����Loop�������s���B <BR>
     *<BR>
     *�@@�@@�Q - �Q�j �萔�����i�R�[�h�̓o�^ <BR>
     *<BR>
     *�@@�@@�萔�����i�R�[�h��Key�l�ɁA Value = "1"��HashMap �֓o�^����B <BR>
     *<BR>
     *�@@�@@�Q - �R�j ���������̏ꍇ�A�X�V�����Z�b�g����B <BR>
     *<BR>
     *�@@�@@�@@�Q - �R - �P�j �������t�̎擾 <BR>
     *<BR>
     *�@@�@@�@@wk�L���� = ���������iYYYYMMDD�j���AWEB3GentradeBizDate.roll(0)���ݒ肷��B<BR>
     *<BR>
     *�@@�@@�@@�Q - �R - �Q�j �ϑ��萔���ڋq�����o�^�}�X�^�[�s�̍��ڂɒl���Z�b�g����B <BR>
     *<BR>
     *�@@�@@�@@DB�X�V�d�l�u�ϑ��萔���ڋq�����o�^�}�X�^�[�c�a�X�V�d�l.xls�v�Q�� <BR>
     *<BR>
     *�@@�@@�@@�Q - �R - �R�j DB�X�V  <BR>
     *<BR>
     *�@@�@@�@@�ϑ��萔���ڋq�����o�^�}�X�^�[�s�I�u�W�F�N�g�̓��e�ŁA<BR>
     *�@@�@@�@@�ϑ��萔���ڋq�����o�^�}�X�^�[�e�[�u�����X�V�iinsert�j����B  <BR>
     *<BR>
     *�@@�@@�Q - �S�j ���c�Ɠ������̏ꍇ�A�X�V�����Z�b�g����B <BR>
     *<BR>
     *�@@�@@�@@�Q - �S - �P�j ���c�Ɠ����t�̎擾 <BR>
     *<BR>
     *�@@�@@�@@wk�L���� = ���c�Ɠ��iYYYYMMDD�j���AWEB3GentradeBizDate.roll(1)���ݒ肷��B<BR>
     *<BR>
     *<BR>
     *�@@�@@�@@�Q - �S - �Q�j �萔���R�[�X�R�[�h�ϊ��}�X�^�e�[�u���̍Č���<BR>
     *<BR>
     *�@@�@@�@@���L�̏������A�Č�������B<BR>
     *<BR>
     *�@@�@@�@@[����] <BR>
     *�@@�@@�@@�萔���R�[�X�R�[�h�ϊ��}�X�^.�萔�����i�R�[�h = �P�j�Ŏ擾�����萔�����i�R�[�h<BR>
     *�@@�@@�@@�萔���R�[�X�R�[�h�ϊ��}�X�^.���XID = ����.�����J�݌����q.���XID<BR>
     *�@@�@@�@@�萔���R�[�X�R�[�h�ϊ��}�X�^.�����ݒ�敪 = "1" <BR>
     *�@@�@@�@@�萔���R�[�X�R�[�h�ϊ��}�X�^.�K�p�J�n�� = wk�L���� <BR>
     *<BR>
     *�@@�@@�@@�@@�Y���f�[�^�����݂���ꍇ<BR>
     *<BR>
     *�@@�@@�@@�@@�Q - �S - �Q�j �̎擾�f�[�^�����ƂɁA�ϑ��萔���ڋq�����o�^�}�X�^�[�s�̍��ڂɒl���Z�b�g����B<BR>
     *<BR>
     *�@@�@@�@@�A�Y���f�[�^�����݂��Ȃ��ꍇ<BR>
     *<BR>
     *�@@�@@�@@�@@�P�j �̎擾�f�[�^�����ƂɁA�ϑ��萔���ڋq�����o�^�}�X�^�[�s�̍��ڂɒl���Z�b�g����B<BR>
     *<BR>
     *�@@�@@�@@DB�X�V�d�l�u�ϑ��萔���ڋq�����o�^�}�X�^�[�c�a�X�V�d�l.xls�v�Q�� <BR>
     *<BR>
     *�@@�@@�@@�Q - �S - �R�j DB�X�V <BR>
     *<BR>
     *�@@�@@�@@�ϑ��萔���ڋq�����o�^�}�X�^�[�s�I�u�W�F�N�g�̓��e�ŁA<BR>
     *�@@�@@�@@�ϑ��萔���ڋq�����o�^�}�X�^�[�e�[�u�����X�V�iinsert�j����B<BR>
     *@@param l_expAccountOpen - (�����J�݌����q)<BR>
     *�����J�݌����q<BR>
     *@@param l_strAccountId - (����ID)<BR>
     *����ID<BR>
     *@@throws WEB3BaseException
     */
    private void insertEquityCommissionAccountCondMaster(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strAccountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertEquityCommissionAccountCondMaster(WEB3AccOpenExpAccountOpen, String)";
        log.entering(STR_METHOD_NAME);
        if (l_expAccountOpen == null || WEB3StringTypeUtility.isEmpty(l_strAccountId))
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //�P�j �萔���R�[�X�R�[�h�ϊ��}�X�^�e�[�u���̌���
        //���L�̏������A��������B
        //[����]
        //�萔���R�[�X�R�[�h�ϊ��}�X�^.���XID = ����.�����J�݌����q.���XID
        //�萔���R�[�X�R�[�h�ϊ��}�X�^.�����ݒ�敪 = "1"
        //�萔���R�[�X�R�[�h�ϊ��}�X�^.�K�p�J�n�� �� �����c�Ɠ��iWEB3GentradeBizDate.roll(0)�j
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" branch_id = ? and  ");
        l_sbQueryString.append(" initial_set_div = ? and ");
        l_sbQueryString.append(" appli_start_date <= ? ");
        ExpAccountOpenParams l_expAccountOpenParams =
            (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
        Timestamp l_tsBaseDate = GtlUtils.getSystemTimestamp();
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsBaseDate);
        Timestamp l_strDate = l_genBizDate.roll(0);
        String l_strFomateDate = WEB3DateUtility.formatDate(l_strDate, "yyyyMMdd");

        Object[] l_objs =
            {String.valueOf(l_expAccountOpenParams.getBranchId()),
             WEB3InitialSetDivDef.MAKE_OBJECT_WHEN_ACCOUNT_OPEN,
             l_strFomateDate};
        List l_lisResultRow = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisResultRow = l_QueryProcessor.doFindAllQuery(
                CommCodeChgMstRow.TYPE,
                l_sbQueryString.toString(),
                "appli_start_date desc",
                null,
                l_objs);
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

        if (l_lisResultRow != null && !l_lisResultRow.isEmpty())
        {
            HashMap l_hashMap = new HashMap();
            //�Q�j �P�j�Ŏ擾����������Loop�������s���B
            for (int i = 0; i < l_lisResultRow.size(); i ++)
            {
                //HashMap �� containsKey(Objet key) ���\�b�h�ɂ��A�P�j�Ŏ擾�����萔�����i�R�[�h��Key�Ɍ������A
                //�@@�߂�l��true�̏ꍇ�͎萔�����i�R�[�h�����ɑ��݂���ׁA����Loop�������s���B
                CommCodeChgMstRow l_commCodeChgMstRow = (CommCodeChgMstRow)l_lisResultRow.get(i);
                if (!l_hashMap.containsKey(l_commCodeChgMstRow.getCommProductCode()))
                {
                    //�Q - �Q�j �萔�����i�R�[�h�̓o�^
                    //�萔�����i�R�[�h��Key�l�ɁA Value = "1"��HashMap �֓o�^����B
                    l_hashMap.put(l_commCodeChgMstRow.getCommProductCode(), "1");

                    //�Q - �R�j ���������̏ꍇ�A�X�V�����Z�b�g����B
                    //�Q - �R - �P�j �������t�̎擾
                    //wk�L���� = ���������iYYYYMMDD�j���AWEB3GentradeBizDate.roll(0)���ݒ肷��B
                    Timestamp l_strWKValidDate = l_genBizDate.roll(0);
                    String l_strProcessDate = WEB3DateUtility.formatDate(l_strWKValidDate, "yyyyMMdd");

                   //�Q - �R - �Q�j �ϑ��萔���ڋq�����o�^�}�X�^�[�s�̍��ڂɒl���Z�b�g����B
                   //DB�X�V�d�l�u�ϑ��萔���ڋq�����o�^�}�X�^�[�c�a�X�V�d�l.xls�v�Q��
                    EquityCommAccountCondMstParams l_equityCommAccCondMstParams =
                        new EquityCommAccountCondMstParams();
                    // �،���ЃR�[�h = ����.�����J�݌����q.�،���ЃR�[�h
                    l_equityCommAccCondMstParams.setInstitutionCode(
                        l_expAccountOpenParams.getInstitutionCode());
                    // ���X�h�c = ����.�����J�݌����q.���XID
                    l_equityCommAccCondMstParams.setBranchId(
                        l_expAccountOpenParams.getBranchId());
                    // �����h�c = ����.����ID
                    long l_lngAccountId = Long.parseLong(l_strAccountId);
                    l_equityCommAccCondMstParams.setAccountId(l_lngAccountId);
                    // �萔�����i�R�[�h = �萔���R�[�X�R�[�h�ϊ��}�X�^.�萔�����i�R�[�h
                    l_equityCommAccCondMstParams.setCommProductCode(
                        l_commCodeChgMstRow.getCommProductCode());
                    // �L���� = wk�L����
                    l_equityCommAccCondMstParams.setValidUntilBizDate(l_strProcessDate);
                    // �萔��No�D = �萔���R�[�X�R�[�h�ϊ��}�X�^.�萔��No
                    l_equityCommAccCondMstParams.setCommissionNo(
                        l_commCodeChgMstRow.getCommissionNo());
                    // �ڋq������ = �萔���R�[�X�R�[�h�ϊ��}�X�^.�ڋq������
                    l_equityCommAccCondMstParams.setAccountChargeRatio(
                        l_commCodeChgMstRow.getAccountChargeRatio());
                    // �쐬���t = ��������
                    l_equityCommAccCondMstParams.setCreatedTimestamp(l_tsBaseDate);
                    // �쐬���t = ��������
                    l_equityCommAccCondMstParams.setLastUpdatedTimestamp(l_tsBaseDate);

                    //�Q - �R - �R�j DB�X�V
                    try
                    {
                        QueryProcessor l_queryProcessor;
                        l_queryProcessor = Processors.getDefaultProcessor();
                        l_queryProcessor.doInsertQuery(l_equityCommAccCondMstParams);
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

                    //�Q - �S�j ���c�Ɠ������̏ꍇ�A�X�V�����Z�b�g����B
                    //�Q - �S - �P�j ���c�Ɠ����t�̎擾
                    //wk�L���� = ���c�Ɠ��iYYYYMMDD�j���AWEB3GentradeBizDate.roll(1)���ݒ肷��B
                    l_strWKValidDate = l_genBizDate.roll(1);
                    l_strProcessDate = WEB3DateUtility.formatDate(l_strWKValidDate, "yyyyMMdd");
                    //�Q - �S - �Q�j �萔���R�[�X�R�[�h�ϊ��}�X�^�e�[�u���̍Č���
                    //�L�̏������A�Č�������B
                    //[����]
                    //�萔���R�[�X�R�[�h�ϊ��}�X�^.�萔�����i�R�[�h = �P�j�Ŏ擾�����萔�����i�R�[�h
                    //�萔���R�[�X�R�[�h�ϊ��}�X�^.���XID = ����.�����J�݌����q.���XID
                    //�萔���R�[�X�R�[�h�ϊ��}�X�^.�����ݒ�敪 = "1"
                    //�萔���R�[�X�R�[�h�ϊ��}�X�^.�K�p�J�n�� = wk�L����
                    l_sbQueryString = new StringBuffer();
                    l_sbQueryString.append(" comm_product_code = ? and ");
                    l_sbQueryString.append(" branch_id = ? and ");
                    l_sbQueryString.append(" initial_set_div = ? and ");
                    l_sbQueryString.append(" appli_start_date = ? ");

                    Object[] l_objMsts =
                        {l_commCodeChgMstRow.getCommProductCode(),
                         String.valueOf(l_expAccountOpenParams.getBranchId()),
                         WEB3InitialSetDivDef.MAKE_OBJECT_WHEN_ACCOUNT_OPEN,
                         l_strProcessDate};

                    List l_lisResultMstRow = null;
                    try
                    {
                        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
                        l_lisResultMstRow = l_QueryProcessor.doFindAllQuery(
                            CommCodeChgMstRow.TYPE,
                            l_sbQueryString.toString(),
                            l_objMsts);
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

                    if (l_lisResultMstRow != null && !l_lisResultMstRow.isEmpty())
                    {
                        CommCodeChgMstRow l_commCodeChgRow =
                            (CommCodeChgMstRow)l_lisResultMstRow.get(0);
                        //�Q - �S - �Q�j �̎擾�f�[�^�����ƂɁA�ϑ��萔���ڋq�����o�^�}�X�^�[�s�̍��ڂɒl���Z�b�g����B
                        l_equityCommAccCondMstParams.setCommProductCode(
                            l_commCodeChgRow.getCommProductCode());
                        l_equityCommAccCondMstParams.setValidUntilBizDate(l_strProcessDate);
                        l_equityCommAccCondMstParams.setCommissionNo(
                            l_commCodeChgRow.getCommissionNo());
                        l_equityCommAccCondMstParams.setAccountChargeRatio(
                            l_commCodeChgRow.getAccountChargeRatio());
                    }
                    else
                    {
                        //�P�j �̎擾�f�[�^�����ƂɁA�ϑ��萔���ڋq�����o�^�}�X�^�[�s�̍��ڂɒl���Z�b�g����B
                        l_equityCommAccCondMstParams.setValidUntilBizDate(l_strProcessDate);
                    }

                    //�Q - �S - �R�j DB�X�V
                    try
                    {
                        QueryProcessor l_queryProcessor;
                        l_queryProcessor = Processors.getDefaultProcessor();
                        l_queryProcessor.doInsertQuery(l_equityCommAccCondMstParams);
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
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update�����J�ݓo�^��)<BR>
     * �����J�݌����q�e�[�u���̌����J�ݓo�^�����X�V����B<BR>
     * <BR>
     * �P�j�@@�o�^�L���[�f�[�^�擾<BR>
     * �@@�����J�ݓ`�[�o�^��t�L���[.�f�[�^�R�[�h�ɑΉ�����o�^�L���[���ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�o�^�L���[�e�[�u��(*1).���ʃR�[�h = ��t�L���[.���ʃR�[�h And<BR>
     * �@@���o�^�L���[.���ʃR�[�h�́A�`�[�̎��ʃR�[�h�iorder_request_number�j<BR>
     * �@@�o�^�L���[�e�[�u��(*1).�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h And<BR>
     * �@@�o�^�L���[�e�[�u��(*1).�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And<BR>
     * �@@�o�^�L���[�e�[�u��(*1).���X�R�[�h = ��t�L���[.���X�R�[�h And<BR>
     * �@@�o�^�L���[�e�[�u��(*1).�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h<BR>
     * <BR>
     * �@@(*1) [�f�[�^�R�[�h�ɑΉ�����o�^�L���[�e�[�u����]<BR>
     * �@@GI82A�F�ڋq�o�^��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��<BR>
     * �@@GI82G�F�_�񏑒�����t�@@���@@�_�񏑒����`�[�iG1151�j�L���[�e�[�u��<BR>
     * �@@GI82C�F�U�֐\���i��s�j��t�@@���@@�U�֐\���i��s�j�`�[�iG26�j�L���[�e�[�u��<BR>
     * �@@GI82H�F�U�֐\���i�X���j��t�@@���@@�U�֐\���i�X���j�`�[�iG26�j�L���[�e�[�u��<BR>
     * �@@GI82B�F�ېU���ӎ�t�@@���@@�ېU���ӓ`�[�iGA300�j�L���[�e�[�u��<BR>
     * �@@GI83G�F�L������t�@@���@@�L�����`�[�iG5401�j�L���[�e�[�u��<BR>
     * �@@GI82E�FMRF������t�@@���@@MRF�����`�[�iGI601)�`�[�L���[�e�[�u��<BR>
     * �@@GI81I�F�����ғo�^��t�@@���@@�����ғo�^�`�[�iG9801�j�L���[�e�[�u��<BR>
     * �@@GI82D�FGP�����o�^��t�@@���@@GP�����o�^�`�[�iG1220�j�L���[�e�[�u��<BR>
     * �@@GI84I�F���O���E�o�^��t�@@���@@���O���E����o�^�`�[�iG8610�j�L���[�e�[�u��<BR>
     * �@@GI84H�F�ڋq���̓o�^��t�@@���@@�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u��<BR>
     * �@@GI84E�F�ڋq�o�^�i����Ɓj��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��<BR>
     * �@@GI85D�F�O�ݗa�������o�^��t�@@���@@�O�ݗa�������o�^�`�[(G43)�L���[�e�[�u��<BR>
     *   GI84C�F���E��c�d�q��t�E��������o�^��t�@@���@@<BR>
     *   ���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u��<BR>
     *   GI86E�F�@@�\�ʒm���o�^��t�@@���@@�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u��<BR>
     * <BR>
     * �@@�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B<BR>
     * <BR>
     * �Q�j�@@�����J�ݓ`�[�쐬�X�e�[�^�X�`�F�b�N<BR>
     * <BR>
     * �@@�Q - �P�j�@@�I�����C�������̃f�[�^�R�[�h���擾����B<BR>
     * �@@MQ_MESSAGE_ID_MAPPINGS�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�،���ЃR�[�h = �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h<BR>
     * �@@���b�Z�[�WID = 'WB3' || �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h || '10301'<BR>
     * <BR>
     * �@@�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B<BR>
     * �@@�擾���ꂽ�������ʂ̃f�[�^�R�[�h�̓��T�����A �f�[�^�R�[�h[] �̔z��Ɋi�[����B<BR>
     * <BR>
     * �@@�Q - �Q�j�@@�`�[�쐬�X�e�[�^�X�`�F�b�N<BR>
     * �@@�����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u�����A�ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�،���ЃR�[�h = �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h<BR>
     * �@@���ʃR�[�h = �P�j�Ŏ擾�����o�^�L���[.���ʃR�[�h�i�����J�݌����q�j<BR>
     * �@@�f�[�^�R�[�h in �Q - �P�j�Ŏ擾���� �f�[�^�R�[�h[]<BR>
     * <BR>
     * �@@�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B<BR>
     * �@@�擾���ꂽ�������ʂ̓`�[�쐬�X�e�[�^�X���S�āA"4"�i��M�ρj�łȂ��ꍇ�A�㑱�����͍s��Ȃ��B<BR>
     * <BR>
     * �R�j�@@���A���A�g�̏������ʃ`�F�b�N<BR>
     * <BR>
     * �@@�ڋq�}�X�^�[�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�،���ЃR�[�h = �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = �����J�ݓ`�[�o�^��t�L���[.���X�R�[�h<BR>
     * �@@�����R�[�h like �����J�ݓ`�[�o�^��t�L���[.�����R�[�h || '%'<BR>
     * <BR>
     * �@@�擾�ł��Ȃ������ꍇ�́A���A���A�g����������I�����Ă��Ȃ��ׁA�㑱�����͍s��Ȃ��B<BR>
     * <BR>
     * �S�j�@@�����J�݌����q�f�[�^�̎擾<BR>
     * <BR>
     * �@@�Q - �Q�j�Ŏ擾���ꂽ�������ʂ̓`�[�쐬�X�e�[�^�X���S�āA"4"�i��M�ρj�̏ꍇ�A<BR>
     * �@@�����J�݌����q�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�،���ЃR�[�h = �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h<BR>
     * �@@���ʃR�[�h = �P�j�Ŏ擾�����o�^�L���[.���ʃR�[�h�i�����J�݌����q�j<BR>
     * <BR>
     * �@@�擾�ł��Ȃ������ꍇ�A�㑱�����͍s��Ȃ��B<BR>
     * �@@�����J�݌����q.�����o�^����null�łȂ��ꍇ�A�㑱�����͍s��Ȃ��B<BR>
     * <BR>
     * �T�j�@@�����o�^���̐ݒ�<BR>
     * �@@�S�j�Ō������ꂽ���R�[�h�Ɉȉ��̒l��ݒ肷��B<BR>
     * <BR>
     * �@@[�ݒ�l]<BR>
     * �@@�����o�^���F���������iYYYY/MM/DD 00:00:00�j<BR>
     * �@@�X�V�����@@�F��������<BR>
     * <BR>
     * �U�j�@@�X�V����<BR>
     * �@@�T�j�̐ݒ�l�Ō����J�݌����q�e�[�u���̍X�V�������s���B<BR>
     * <BR>
     * �@@�X�V���e�́ADB�X�V�d�l�u�����J�݌����qDB�X�V�i�����J�ݓ��j�d�l.xls�v �Q�ƁB<BR>
     * <BR>
     * @@param l_hostAccOpenAccept - (�����J�ݓ`�[�o�^��t�L���[)<BR>
     * �����J�ݓ`�[�o�^��t�L���[
     * @@throws WEB3BaseException
     */
    public void updateAccOpenOpenDate(
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateAccOpenOpenDate(HostAccOpenAcceptParams)";
        log.entering(STR_METHOD_NAME);

        // [��������]
        // �o�^�L���[�e�[�u��(*1).���ʃR�[�h = ��t�L���[.���ʃR�[�h And�@@���o�^�L���[.���ʃR�[�h�́A�`�[�̎��ʃR�[�h�iorder_request_number�j
        // �o�^�L���[�e�[�u��(*1).�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h And
        // �o�^�L���[�e�[�u��(*1).�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And
        // �o�^�L���[�e�[�u��(*1).���X�R�[�h = ��t�L���[.���X�R�[�h And
        // �o�^�L���[�e�[�u��(*1).�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h
        String l_strOrderRequestNumber = l_hostAccOpenAcceptParams.getOrderRequestNumber();
        String l_strRequestCode = l_hostAccOpenAcceptParams.getRequestCode();
        String l_strInstitutionCode = l_hostAccOpenAcceptParams.getInstitutionCode();
        String l_strBranchCode = l_hostAccOpenAcceptParams.getBranchCode();
        String l_strAccountCode = l_hostAccOpenAcceptParams.getAccountCode();

        String l_strAccOpenRequestNumber = null;
        String[] l_strDataCodes = null;

        String[] l_acceptReqCodes =
            new String[]{"GI82A","GI82G","GI82C","GI82H","GI82B","GI83G","GI82E",
                         "GI81I", "GI82D", "GI84I", "GI84H", "GI84E", "GI85D", "GI84C", "GI86E"};
        String[] l_registReqCodes =
            new String[]{"GI821","GI827","GI823","GI828","GI822","GI837","GI825",
                         "GI819", "GI824", "GI849", "GI848", "GI845", "GI854", "GI843", "GI865"};

        String l_strRegistReqCode = null;
        for (int i = 0; i < l_acceptReqCodes.length; i++)
        {
            if (l_acceptReqCodes[i].equals(l_strRequestCode))
            {
                l_strRegistReqCode = l_registReqCodes[i];
                break;
            }
        }
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            // �P�j�o�^�L���[�f�[�^�擾
            // GI82A�F�ڋq�o�^��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��
            if (WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST_ACCEPT.equals(l_strRequestCode))
            {
                HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAccRegVoucherRow l_row =
                    (HostAccRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI82G�F�_�񏑒�����t�@@���@@�_�񏑒����`�[�iG1151�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_CONTRACT_COLLECT_ACCEPT.equals(l_strRequestCode))
            {
                HostContMrgVoucherPK l_pk = new HostContMrgVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostContMrgVoucherRow l_row =
                    (HostContMrgVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI82C�F�U�֐\���i��s�j��t�@@���@@�U�֐\���i��s�j�`�[�iG26�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK_ACCEPT.equals(l_strRequestCode))
            {
                HostBankTransVoucherPK l_pk = new HostBankTransVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostBankTransVoucherRow l_row =
                    (HostBankTransVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI82H�F�U�֐\���i�X���j��t�@@���@@�U�֐\���i�X���j�`�[�iG26�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL_ACCEPT.equals(l_strRequestCode))
            {
                HostPostalTransVoucherPK l_pk = new HostPostalTransVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostPostalTransVoucherRow l_row =
                    (HostPostalTransVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI82B�F�ېU���ӎ�t�@@���@@�ېU���ӓ`�[�iGA300�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGREE_TRANSFER_ACCEPT.equals(l_strRequestCode))
            {
                HostAgreeTransVoucherPK l_pk = new HostAgreeTransVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAgreeTransVoucherRow l_row =
                    (HostAgreeTransVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI83G�F�L������t�@@���@@�L�����`�[�iG5401�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_CHARGED_INFO_ACCEPT.equals(l_strRequestCode))
            {
                HostChargedInfoVoucherPK l_pk = new HostChargedInfoVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostChargedInfoVoucherRow l_row =
                    (HostChargedInfoVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI82E�FMRF������t�@@���@@MRF�����`�[�iGI601)�`�[�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_MRF_ACCOUNT_ACCEPT.equals(l_strRequestCode))
            {
                HostMrfAccVoucherPK l_pk = new HostMrfAccVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostMrfAccVoucherRow l_row =
                    (HostMrfAccVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI81I�F�����ғo�^��t�@@���@@�����ғo�^�`�[�iG9801�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_INSIDER_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostInsiderRegVoucherPK l_pk = new HostInsiderRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostInsiderRegVoucherRow l_row =
                    (HostInsiderRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }  
            // GI82D�FGP�����o�^��t�@@���@@GP�����o�^�`�[�iG1220�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_GP_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostGpRegVoucherPK l_pk = new HostGpRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostGpRegVoucherRow l_row =
                    (HostGpRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI84I�F���O���E�o�^��t�@@���@@���O���E����o�^�`�[�iG8610�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_STOCKHOLDER_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostStockholderRegVoucherPK l_pk = new HostStockholderRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostStockholderRegVoucherRow l_row =
                    (HostStockholderRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI84H�F�ڋq���̓o�^��t�@@���@@�ڋq�������̓o�^�`�[�iG1190�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_REALNAME_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostRealnameRegVoucherPK l_pk = new HostRealnameRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostRealnameRegVoucherRow l_row =
                    (HostRealnameRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }  
            // GI84E�F�ڋq�o�^�i����Ɓj��t�@@���@@�ڋq�o�^�`�[(G11)�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAccRegVoucherRow l_row =
                    (HostAccRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI85D�F�O�ݗa�������o�^��t�@@���@@�O�ݗa�������o�^�`�[(G43)�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.F_DEPOSIT_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostFDepositVoucherPK l_pk = new HostFDepositVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostFDepositVoucherRow l_row =
                    (HostFDepositVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI84C�F���E��c�d�q��t�E��������o�^��t�@@���@@
            //  ���E��c�d�q��t�E��������o�^�iGI311�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostConditionRegVoucherPK l_pk = new HostConditionRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostConditionRegVoucherRow l_row =
                    (HostConditionRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            //GI86E�F�@@�\�ʒm���o�^��t�@@���@@�@@�\�ʒm���o�^�`�[�iGS103�j�L���[�e�[�u��
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostAgencyNotifyVoucherPK l_voucherPK = new HostAgencyNotifyVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAgencyNotifyVoucherRow l_voucherRow =
                    (HostAgencyNotifyVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_voucherPK);
                l_strAccOpenRequestNumber = l_voucherRow.getAccOpenRequestNumber();
            }
            else
            {
                log.debug("�f�[�^�R�[�h�G���[");
                log.exiting(STR_METHOD_NAME);
                return ;
            }

            log.debug("l_strAccOpenRequestNumber is:" + l_strAccOpenRequestNumber);
            
            // �Q�j�@@�����J�ݓ`�[�쐬�X�e�[�^�X�`�F�b�N
            // �Q - �P�j�@@�I�����C�������̃f�[�^�R�[�h���擾����B
            // MQ_MESSAGE_ID_MAPPINGS�e�[�u�����ȉ��̏����Ō�������B
            List l_lisRecords = null;
            String l_strWhere = " institution_code = ? and message_Id = (? || ? || ?) ";
            Object[] l_objs = {l_strInstitutionCode, "WB3", l_strInstitutionCode, "10301"};
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MqMessageIdMappingsRow.TYPE,
                l_strWhere,
                null,
                null,
                l_objs);

            // �擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                log.debug("MQ_MESSAGE_ID_MAPPINGS�f�[�^�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            List l_lisParam = new ArrayList();
            l_lisParam.add(l_strInstitutionCode);
            l_lisParam.add(l_strAccOpenRequestNumber);
            
            int l_intLength = l_lisRecords.size();
            // �擾���ꂽ�������ʂ̃f�[�^�R�[�h�̓��T�����A �f�[�^�R�[�h[] �̔z��Ɋi�[����B
            for (int i = 0; i < l_intLength; i++)
            {
                l_strDataCodes = new String[l_intLength];
                l_strDataCodes[i] =
                    ((MqMessageIdMappingsRow)l_lisRecords.get(i)).getDataCode().substring(0, 5);
                
                l_lisParam.add(l_strDataCodes[i]);
            }
            
            // �Q - �Q�j�@@�`�[�쐬�X�e�[�^�X�`�F�b�N 
            // �����J�ݓ`�[�쐬�X�e�[�^�X�e�[�u�����A�ȉ��̏����Ō�������B
            // �،���ЃR�[�h = �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h
            // ���ʃR�[�h = �P�j�Ŏ擾�����o�^�L���[.���ʃR�[�h�i�����J�݌����q�j
            // �f�[�^�R�[�h in �Q - �P�j�Ŏ擾���� �f�[�^�R�[�h[]
            List l_lisRecords1 = null;
            String l_strWhere1 =
                " institution_code = ? and acc_open_request_number = ? ";
            l_strWhere1 = l_strWhere1 + " and request_code in (? ";
            
            int l_intRecordLength = l_lisRecords.size();
            for (int i = 1; i < l_intRecordLength; i++)
            {
                l_strWhere1 = l_strWhere1 + ", ?";
            }
            l_strWhere1 = l_strWhere1 + ") ";

            Object[] l_objs1 = new Object[l_lisParam.size()];
            l_lisRecords1 = l_queryProcessor.doFindAllQuery(
                AccOpenVoucherStatusRow.TYPE,
                l_strWhere1,
                null,
                null,
                l_lisParam.toArray(l_objs1));

            // �擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B
            if (l_lisRecords1 == null || l_lisRecords1.isEmpty())
            {
                log.debug("�����J�ݓ`�[�쐬�X�e�[�^�X�f�[�^�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                return;
            }

            // �擾���ꂽ�������ʂ̓`�[�쐬�X�e�[�^�X���S�āA"4"�i��M�ρj�łȂ��ꍇ�A�㑱�����͍s��Ȃ��B
            int l_intFlag = 0;
            String l_strVoucherStatus = null;
            for (int i = 0; i < l_lisRecords1.size(); i++)
            {
                l_strVoucherStatus =
                    ((AccOpenVoucherStatusRow)l_lisRecords1.get(i)).getVoucherStatus();
                if (WEB3VoucherStatusDef.RECEIVE_COMPLETE.equals(l_strVoucherStatus))
                {
                    l_intFlag = 1;
                    break;
                }
            }
            
            int l_intFlagAllFour = 0;
            for (int i = 0; i < l_lisRecords1.size(); i++)
            {
                l_strVoucherStatus =
                    ((AccOpenVoucherStatusRow)l_lisRecords1.get(i)).getVoucherStatus();
                if (!WEB3VoucherStatusDef.RECEIVE_COMPLETE.equals(l_strVoucherStatus))
                {
                    l_intFlagAllFour = 1;
                    break;
                }
            }
            
            if (l_intFlag == 0)
            {
                log.debug("�`�[�쐬�X�e�[�^�X���S�āA4�i��M�ρj�łȂ��ꍇ�A�㑱�����͍s��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                return;
            }

            // �R�j�@@���A���A�g�̏������ʃ`�F�b�N
            // �ڋq�}�X�^�[�e�[�u�����ȉ��̏����Ō�������B
            // [��������]
            // �،���ЃR�[�h = �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h
            // ���X�R�[�h = �����J�ݓ`�[�o�^��t�L���[.���X�R�[�h
            // �����R�[�h like �����J�ݓ`�[�o�^��t�L���[.�����R�[�h || '%'
            List l_lisRecords2 = null;
            String l_strWhere2 =
                " institution_code = ? and branch_code = ? and account_code like ( ? || '%' ) ";
            Object[] l_objs2 =
                {l_strInstitutionCode, l_strBranchCode, l_strAccountCode};
            l_lisRecords2 = l_queryProcessor.doFindAllQuery(
                MainAccountRow.TYPE,
                l_strWhere2,
                null,
                null,
                l_objs2);

            // �擾�ł��Ȃ������ꍇ�́A���A���A�g����������I�����Ă��Ȃ��ׁA�㑱�����͍s��Ȃ��B
            if (l_lisRecords2 == null || l_lisRecords2.isEmpty())
            {
                log.debug("�ڋq�}�X�^�f�[�^�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                return;
            }

            // �S�j�@@�����J�݌����q�f�[�^�̎擾
            // [��������]
            //�@@�،���ЃR�[�h = �����J�ݓ`�[�o�^��t�L���[.�،���ЃR�[�h
            // �@@���ʃR�[�h = �P�j�Ŏ擾�����o�^�L���[.���ʃR�[�h�i�����J�݌����q�j
            // �@@�Q - �Q�j�Ŏ擾���ꂽ�������ʂ̓`�[�쐬�X�e�[�^�X���S�āA"4"�i��M�ρj�̏ꍇ�A
            ExpAccountOpenParams l_params = null;
            if (l_intFlagAllFour == 0)
            {
                ExpAccountOpenRow l_row =
                    (ExpAccountOpenRow)ExpAccountOpenDao.findRowByInstitutionCodeAccOpenRequestNumber(
                        l_strInstitutionCode,
                        l_strAccOpenRequestNumber);

                // �擾�ł��Ȃ������ꍇ�A�㑱�����͍s��Ȃ��B
                // �����J�݌����q.�����o�^����null�łȂ��ꍇ�A�㑱�����͍s��Ȃ��B
                if (l_row == null)
                {
                    log.debug("�擾�ł��Ȃ������ꍇ�A�㑱�����͍s��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    return;    
                }
                
                l_params = new ExpAccountOpenParams(l_row);
                if (l_params.getAccountOpenDate() != null)
                {
                    log.debug("�����J�݌����q.�����o�^����null�łȂ��ꍇ�A�㑱�����͍s��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
            }
            
            if (l_params != null)
            {
                // �T�j�@@�����o�^���̐ݒ�
                // �S�j�Ō������ꂽ���R�[�h�Ɉȉ��̒l��ݒ肷��B
                // [�ݒ�l]
                // ���������̎擾
                Date l_datProcessDate = GtlUtils.getSystemTimestamp();
                Date l_datAccountOpenDate = WEB3DateUtility.toDay(l_datProcessDate);

                // �����o�^���F���������iYYYY/MM/DD 00:00:00�j
                l_params.setAccountOpenDate(l_datAccountOpenDate);

                //�X�V�����@@�F��������
                l_params.setLastUpdatedTimestamp(l_datProcessDate);

                // �U�j�@@�X�V����
                // �T�j�̐ݒ�l�Ō����J�݌����q�e�[�u���̍X�V�������s���B
                // �X�V���e�́ADB�X�V�d�l�u�����J�݌����qDB�X�V�i�����J�ݓ��j�d�l.xls�v �Q�ƁB
                l_queryProcessor.doUpdateQuery(l_params);
            }

        }
        catch (DataFindException l_ex)
        {
            log.debug("�o�^�L���[�f�[�^�擾�ł��Ȃ������ꍇ�́A�㑱�����͍s��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            
            return;
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

        log.exiting(STR_METHOD_NAME);
    }
}@
