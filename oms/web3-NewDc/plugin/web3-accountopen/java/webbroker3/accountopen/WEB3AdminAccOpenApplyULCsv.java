head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyULCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐ\��ULCSV(WEB3AdminAccOpenApplyULCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 ���g (���u) �V�K�쐬 ���f�� No.148,No.150,No.153
Revision History : 2007/12/11 �Ӑ� (���u) �d�l�ύX ���f�� No.155,No.156
Revision History : 2007/12/17 ���g (���u) �d�l�ύX ���f�� No.157
*/

package webbroker3.accountopen;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����J�ݐ\��ULCSV)<BR>
 * �����J�ݐ\��ULCSV<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyULCsv extends WEB3GentradeCsvUploadDataModel
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyULCsv.class);

    /**
     * (�A�b�v���[�h�t�@@�C��ID)<BR>
     * �A�b�v���[�h�t�@@�C��ID<BR>
     */
    public String uploadFileId = "�����J�ݐ\��";

    /**
     * (�A�b�v���[�h���~)<BR>
     * �A�b�v���[�h���~�p�R�����g<BR>
     */
    public String uploadCancel = "���~";

    /**
     * (���R�[�h�ԍ����x��)<BR>
     * �萔��`�v���p�e�B�@@�h���R�[�h�ԍ��h<BR>
     */
    public String recordNumberLabel = "���R�[�h�ԍ�";

    /**
     * (���ʃR�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���ʃR�[�h�h<BR>
     */
    public String requestNumberLabel = "���ʃR�[�h";

    /**
     * (���X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���X�R�[�h�h<BR>
     */
    public String branchCodeLabel = "���X�R�[�h";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq�R�[�h�h<BR>
     */
    public String accountCodeLabel = "�ڋq�R�[�h";

    /**
     * (���҃R�[�h�iSONAR�j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���҃R�[�h�iSONAR�j�h<BR>
     */
    public String sonarTraderCodeLabel = "���҃R�[�h�iSONAR�j";

    /**
     * (�����敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����敪�h<BR>
     */
    public String accountTypeLabel = "�����敪";

    /**
     * (���͋敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���͋敪�h<BR>
     */
    public String inputDivLabel = "���͋敪";

    /**
     * (���������������x��)<BR>
     * �萔��`�v���p�e�B�@@�h�������������h<BR>
     */
    public String infoClaimDateLabel = "������������";

    /**
     * (�����O�@@���i�����j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����O�@@���i�����j�h<BR>
     */
    public String accountFamilyNameLabel = "�����O�@@���i�����j";

    /**
     * (�����O�@@���i�����j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����O�@@���i�����j�h<BR>
     */
    public String accountNameLabel = "�����O�@@���i�����j";

    /**
     * (�����O�@@���i�t���K�i�j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����O�@@���i�t���K�i�j�h<BR>
     */
    public String accountFamilyNameKanaLabel = "�����O�@@���i�t���K�i�j";

    /**
     * (�����O�@@���i�t���K�i�j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����O�@@���i�t���K�i�j�h<BR>
     */
    public String accountNameKanaLabel = "�����O�@@���i�t���K�i�j";

    /**
     * (���ʃ��x��)<BR>
     * �萔��`�v���p�e�B�@@�h���ʁh<BR>
     */
    public String sexLabel = "����";

    /**
     * (���N�����N�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h���N�����N���h<BR>
     */
    public String eraBornLabel = "���N�����N��";

    /**
     * (���N�������x��)<BR>
     * �萔��`�v���p�e�B�@@�h���N�����h<BR>
     */
    public String bornDateLabel = "���N����";

    /**
     * (���[���A�h���X���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���[���A�h���X�h<BR>
     */
    public String mailAddressLabel = "���[���A�h���X";

    /**
     * (�X�֔ԍ����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�X�֔ԍ��h<BR>
     */
    public String zipCodeLabel = "�X�֔ԍ�";

    /**
     * (�Z���P�i�����j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Z���P�i�����j�h<BR>
     */
    public String address1Label = "�Z���P�i�����j";

    /**
     * (�Z���Q�i�����j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Z���Q�i�����j�h<BR>
     */
    public String address2Label = "�Z���Q�i�����j";

    /**
     * (�Z���R�i�����j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Z���R�i�����j�h<BR>
     */
    public String address3Label = "�Z���R�i�����j";

    /**
     * (�Z���P�i�J�i�j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Z���P�i�J�i�j�h<BR>
     */
    public String addressKana1Label = "�Z���P�i�J�i�j";

    /**
     * (�Z���Q�i�J�i�j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Z���Q�i�J�i�j�h<BR>
     */
    public String addressKana2Label = "�Z���Q�i�J�i�j";

    /**
     * (�Z���R�i�J�i�j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Z���R�i�J�i�j�h<BR>
     */
    public String addressKana3Label = "�Z���R�i�J�i�j";

    /**
     * (�d�b�ԍ��i�s�O�ǔԁj���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�d�b�ԍ��i�s�O�ǔԁj�h<BR>
     */
    public String telephoneAreaCodeLabel = "�d�b�ԍ��i�s�O�ǔԁj";

    /**
     * (�d�b�ԍ��i�ǔԁj���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�d�b�ԍ��i�ǔԁj�h<BR>
     */
    public String telephoneExchangeNumberLabel = "�d�b�ԍ��i�ǔԁj";

    /**
     * (�d�b�ԍ��i�ԍ��j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�d�b�ԍ��i�ԍ��j�h<BR>
     */
    public String telephoneNumberLabel = "�d�b�ԍ��i�ԍ��j";

    /**
     * (�g�єԍ��i�s�O�ǔԁj���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�g�єԍ��i�s�O�ǔԁj�h<BR>
     */
    public String mobileAreaCodeLabel = "�g�єԍ��i�s�O�ǔԁj";

    /**
     * (�g�єԍ��i�ǔԁj���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�g�єԍ��i�ǔԁj�h<BR>
     */
    public String mobileExchangeNumberLabel = "�g�єԍ��i�ǔԁj";

    /**
     * (�g�єԍ��i�ԍ��j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�g�єԍ��i�ԍ��j�h<BR>
     */
    public String mobileNumberLabel = "�g�єԍ��i�ԍ��j";

    /**
     * (�E�Ƌ敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�E�Ƌ敪�h<BR>
     */
    public String occupationDivLabel = "�E�Ƌ敪";

    /**
     * (�Ζ��於�̃��x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Ζ��於�́h<BR>
     */
    public String officeLabel = "�Ζ��於��";

    /**
     * (�Ζ���X�֔ԍ����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Ζ���X�֔ԍ��h<BR>
     */
    public String officeZipCodeLabel = "�Ζ���X�֔ԍ�";

    /**
     * (�Ζ���Z�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Ζ���Z���h<BR>
     */
    public String officeAddressLabel = "�Ζ���Z��";

    /**
     * (�Ζ���d�b�ԍ��P���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Ζ���d�b�ԍ��P�h<BR>
     */
    public String officeTelephone1Label = "�Ζ���d�b�ԍ��P";

    /**
     * (�Ζ���d�b�ԍ��Q���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Ζ���d�b�ԍ��Q�h<BR>
     */
    public String officeTelephone2Label = "�Ζ���d�b�ԍ��Q";

    /**
     * (�Ζ���d�b�ԍ��R���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Ζ���d�b�ԍ��R�h<BR>
     */
    public String officeTelephone3Label = "�Ζ���d�b�ԍ��R";

    /**
     * (�����������x��)<BR>
     * �萔��`�v���p�e�B�@@�h���������h<BR>
     */
    public String departmentLabel = "��������";

    /**
     * (��E���x��)<BR>
     * �萔��`�v���p�e�B�@@�h��E�h<BR>
     */
    public String postLabel = "��E";

    /**
     * (���ю�Ƃ̑������x��)<BR>
     * �萔��`�v���p�e�B�@@�h���ю�Ƃ̑����h<BR>
     */
    public String houseHolderFamilyRelationLabel = "���ю�Ƃ̑���";

    /**
     * (���ю喼���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���ю喼�h<BR>
     */
    public String houseHolderLabel = "���ю喼";

    /**
     * (���ю�@@�Ζ��惉�x��)<BR>
     * �萔��`�v���p�e�B�@@�h���ю�@@�Ζ���h<BR>
     */
    public String houseHolderOfficeLabel = "���ю�@@�Ζ���";

    /**
     * (���ю�@@��E���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���ю�@@��E�h<BR>
     */
    public String houseHolderPostLabel = "���ю�@@��E";

    /**
     * (�U���惉�x��)<BR>
     * �萔��`�v���p�e�B�@@�h�U����h<BR>
     */
    public String transferLabel = "�U����";

    /**
     * (��s�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h��s�R�[�h�h<BR>
     */
    public String finInstitutionCodeLabel = "��s�R�[�h";

    /**
     * (��s�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h��s���h<BR>
     */
    public String finInstitutionNameLabel = "��s��";

    /**
     * (�x�X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�x�X�R�[�h�h<BR>
     */
    public String finBranchCodeLabel = "�x�X�R�[�h";

    /**
     * (�x�X�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�x�X���h<BR>
     */
    public String finBranchNameLabel = "�x�X��";

    /**
     * (�a���敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�a���敪�h<BR>
     */
    public String finSaveDivLabel = "�a���敪";

    /**
     * (�����ԍ����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����ԍ��h<BR>
     */
    public String finAccountNoLabel = "�����ԍ�";

    /**
     * (�ʒ��L�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ʒ��L���h<BR>
     */
    public String postalSaveCodeLabel = "�ʒ��L��";

    /**
     * (�ʒ��ԍ����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ʒ��ԍ��h<BR>
     */
    public String postalSaveNoLabel = "�ʒ��ԍ�";

    /**
     * (�����o���i������������j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����o���i������������j�h<BR>
     */
    public String experienceEquityDivLabel = "�����o���i������������j";

    /**
     * (�����o���i�����M�p����j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����o���i�����M�p����j�h<BR>
     */
    public String experienceMarginDivLabel = "�����o���i�����M�p����j";

    /**
     * (�����o���i���j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����o���i���j�h<BR>
     */
    public String experienceBondDivLabel = "�����o���i���j";

    /**
     * (�����o���i�����w���I�v�V�����j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����o���i�����w���I�v�V�����j�h<BR>
     */
    public String experienceOptionsDivLabel = "�����o���i�����w���I�v�V�����j";

    /**
     * (�����o���i�����M���F�����j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����o���i�����M���F�����j�h<BR>
     */
    public String experienceFundSkDivLabel = "�����o���i�����M���F�����j";

    /**
     * (�����o���i�����M���F���Ѝj���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����o���i�����M���F���Ѝj�h<BR>
     */
    public String experienceFundBdDivLabel = "�����o���i�����M���F���Ѝj";

    /**
     * (�����o���i�����w���敨�j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����o���i�����w���敨�j�h<BR>
     */
    public String experienceFuturesDivLabel = "�����o���i�����w���敨�j";

    /**
     * (�����o���i�O���،��j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����o���i�O���،��j�h<BR>
     */
    public String experienceFEquityDivLabel = "�����o���i�O���،��j";

    /**
     * (�����o���i���̑��j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����o���i���̑��j�h<BR>
     */
    public String experienceEtcDivLabel = "�����o���i���̑��j";

    /**
     * (��]��������̎�ށ@@�����i�����j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h��]��������̎�ށ@@�����i�����j�h<BR>
     */
    public String interestEquityFlagLabel = "��]��������̎�ށ@@�����i�����j";

    /**
     * (��]��������̎�ށ@@�����i�M�p�j���x��)<BR>
     * �萔��`�v���p�e�B�@@�h��]��������̎�ށ@@�����i�M�p�j�h<BR>
     */
    public String interestMarginFlagLabel = "��]��������̎�ށ@@�����i�M�p�j";

    /**
     * (��]��������̎�ށ@@���敨���x��)<BR>
     * �萔��`�v���p�e�B�@@�h��]��������̎�ށ@@���敨�h<BR>
     */
    public String interestBondFlagLabel = "��]��������̎�ށ@@���敨";

    /**
     * (��]��������̎�ށ@@�����M�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h��]��������̎�ށ@@�����M���h<BR>
     */
    public String interestFundFlagLabel = "��]��������̎�ށ@@�����M��";

    /**
     * (��]��������̎�ށ@@�����w���敨���x��)<BR>
     * �萔��`�v���p�e�B�@@�h��]��������̎�ށ@@�����w���敨�h<BR>
     */
    public String interestFuturesFlagLabel = "��]��������̎�ށ@@�����w���敨";

    /**
     * (��]��������̎�ށ@@�����w���I�v�V�������x��)<BR>
     * �萔��`�v���p�e�B�@@�h��]��������̎�ށ@@�����w���I�v�V�����h<BR>
     */
    public String interestOptionsFlagLabel = "��]��������̎�ށ@@�����w���I�v�V����";

    /**
     * (�N���敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�N���敪�h<BR>
     */
    public String annualIncomeDivLabel = "�N���敪";

    /**
     * (���Z���Y�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���Z���Y�敪�h<BR>
     */
    public String assetValueDivLabel = "���Z���Y�敪";

    /**
     * (��������敪�������x��)<BR>
     * �萔��`�v���p�e�B�@@�h��������敪�����h<BR>
     */
    public String specialAccEquityLabel = "��������敪����";

    /**
     * (�����ғo�^�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����ғo�^�敪�h<BR>
     */
    public String insiderFlagLabel = "�����ғo�^�敪";

    /**
     * (�����ғo�^�������x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����ғo�^�����h<BR>
     */
    public String productNameLabel = "�����ғo�^����";

    /**
     * (�Ǝ�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Ǝ�敪�h<BR>
     */
    public String typeDivLabel = "�Ǝ�敪";

    /**
     * (�����̌`�ԋ敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����̌`�ԋ敪�h<BR>
     */
    public String incomeDormDivLable = "�����̌`�ԋ敪";

    /**
     * (�����Ҋ֌W�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����Ҋ֌W�敪�h<BR>
     */
    public String insiderRelationDivLabel = "�����Ҋ֌W�敪";

    /**
     * (�����ړI�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����ړI�敪�h<BR>
     */
    public String investPurposeDivLabel = "�����ړI�敪";

    /**
     * (������@@�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h������@@�敪�h<BR>
     */
    public String appliMotivatDivLabel = "������@@�敪";

    /**
     * (�{�l�m�F���ދ敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�{�l�m�F���ދ敪�h<BR>
     */
    public String idConfirmDocDivLabel = "�{�l�m�F���ދ敪";

    /**
     * (�����J�ݐ\��ULCSV)<BR>
     * �R���X�g���N�^<BR>
     * ���@@�A�b�v���[�h���~�̏ꍇ�Ɏg�p����B<BR>
     * <BR>
     * �|�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_lngUploadFileID - (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     * @@roseuid 472AE8F70363
     */
    public WEB3AdminAccOpenApplyULCsv(long l_lngUploadFileID)
    {
        final String STR_METHOD_NAME = "WEB3AdminAccOpenApplyULCsv(long)";
        log.entering(STR_METHOD_NAME);

        //�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B
        this.administratorUploadId = l_lngUploadFileID;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�����J�ݐ\��ULCSV)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �|this.create�J�����w�b�_()���R�[������B<BR>
     * @@roseuid 472AE89D0023
     */
    public WEB3AdminAccOpenApplyULCsv()
    {
        this.createColumnHeader();
    }

    /**
     * (get�����^�C�v)<BR>
     * ProductTypeEnum.���̑� ��ԋp����B<BR>
     * @@return ProductTypeEnum
     * @@roseuid 473923F200C5
     */
    public ProductTypeEnum getProductType()
    {
        //ProductTypeEnum.���̑� ��ԋp����
        return ProductTypeEnum.OTHER;
    }

    /**
     * (get�A�b�v���[�h�t�@@�C��ID)<BR>
     * �����J�ݐ\��ULCSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c�Ɋi�[���镶����<BR>
     * @@return String
     * @@roseuid 47392444039D
     */
    public String getUploadFileId()
    {
        return this.uploadFileId;
    }

    /**
     * (get�A�b�v���[�h�ŐV����)<BR>
     * ���Y�A�b�v���[�h�t�@@�C���Ɋ֘A����A�b�v���[�h�ŐV�������擾����B<BR>
     * <BR>
     * �ȉ��̏����Łu�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���v���������A<BR>
     * �擾�����s�I�u�W�F�N�g��ԋp����B<BR>
     * ���R�[�h���Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * [����]<BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�،���ЃR�[�h = this.get�،���ЃR�[�h()<BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c =<BR>
     * this.get�A�b�v���[�h�t�@@�C���h�c()<BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�����^�C�v = this.get�����^�C�v()<BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�f�[�^�L�[ = ����.�f�[�^�L�[<BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.���l�P != "���~"<BR>
     * �����l�P���r���鎞�A���ڂ�NULL�̏ꍇ�A�������"NULL"��
     * �@@�@@�u�������Ă����r����B(SQL���Fnvl(note1,'NULL') != '���~')<BR>
     * <BR>
     * [�擾��]<BR>
     * �A�b�v���[�h�J�n�����@@�~���i�Fdesc�j<BR>
     * @@param l_lngUploadKey - (�f�[�^�L�[)<BR>
     * �f�[�^�L�[<BR>
     * @@return Object
     * @@roseuid 473035330388
     */
    public Object getUploadNewHistory(long l_lngUploadKey) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUploadNewHistory(long)";
        log.entering(STR_METHOD_NAME);

        //�ȉ��̏����Łu�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���v���������A
        //�擾�����s�I�u�W�F�N�g��ԋp����B
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" AND upload_file_id = ? ");
        l_sbWhere.append(" AND product_type = ? ");
        l_sbWhere.append(" AND upload_key = ? ");
        l_sbWhere.append(" AND nvl(note1,'NULL') != ? ");

        Object[] l_whereValues = new Object[5];
        l_whereValues[0] = this.getInstitutionCode();
        l_whereValues[1] = this.getUploadFileId();
        l_whereValues[2] = this.getProductType();
        l_whereValues[3] = new Long(l_lngUploadKey);
        l_whereValues[4] = this.uploadCancel;

        //[�擾��]
        //�A�b�v���[�h�J�n�����@@�~���i�Fdesc�j
        String l_strSortKey = " upload_start_timestamp DESC ";

        List l_lisAdministratorUploads = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAdministratorUploads = l_queryProcessor.doFindAllQuery(
                AdministratorUploadRow.TYPE,
                l_sbWhere.toString(),
                l_strSortKey,
                null,
                l_whereValues);
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

        //���R�[�h���Ȃ��ꍇ��null��ԋp����B
        if (l_lisAdministratorUploads == null || l_lisAdministratorUploads.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisAdministratorUploads.get(0);
    }

    /**
     * (validate�d���ڋq)<BR>
     * �d���ڋq���ǉ�����Ă��Ȃ����`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@���ʃR�[�h�`�F�b�N<BR>
     * �@@�P�|�P�j�@@get���ʃR�[�h�i�s�ԍ��j�ɂāA�w��s�ԍ��̎��ʃR�[�h���擾����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�擾�������ʃR�[�h�Ǝw��s�ԍ����O�̖��׍s�̎��ʃR�[�h���r����B<BR>
     * <BR>
     * <BR>
     * �@@�P�|�R�j�@@�����l�̍s�i���ʃR�[�h == ���ʃR�[�h�j�����݂���ꍇ�́A<BR>
     * �@@�@@�ڋq���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_02959<BR>
     * <BR>
     * �Q�j�@@���X�E�ڋq�R�[�h�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@get���X�R�[�h�i�s�ԍ��j�Cget�ڋq�R�[�h(�s�ԍ�)�ɂāA<BR>
     * �@@�@@�@@�@@�@@�w��s�ԍ��̕��X�R�[�h�C�ڋq�R�[�h���擾����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@get�ڋq�R�[�h(�s�ԍ�)�̖߂�l��NULL�ȊO�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j�@@�擾�������X�R�[�h�C�ڋq�R�[�h�Ǝw��s�ԍ����O�̖��׍s��<BR>
     * �@@�@@�@@�@@�@@�@@���X�R�[�h�C�ڋq�R�[�h���r����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�@@�����l�̍s�i���X�R�[�h == ���X�R�[�h && �ڋq�R�[�h ==<BR>
     * �@@�@@�@@�@@�@@�@@�ڋq�R�[�h�j�����݂���ꍇ�́A<BR>
     * �@@�@@�@@�ڋq���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_02959<BR>
     * <BR>
     * �R�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B<BR>
     * �@@���b�Z�[�W�@@�@@�@@�F�w�d������ڋq�R�[�h���͎��ʃR�[�h�̐\���݂����݂��܂��B�x<BR>
     * �@@�@@�@@��BUSINESS_ERROR<BR>
     * �@@�ǉ�������@@�F�ȉ��̓��e���Z�b�g����B<BR>
     * �@@�@@�|���ʃR�[�h�`�F�b�N�̏ꍇ�@@�F�@@get���ʃR�[�h()<BR>
     * �@@�@@�|�ڋq�R�[�h�`�F�b�N�̏ꍇ�@@�F�@@get���X�R�[�h() + "�@@"�i�X�y�[�X�j +<BR>
     * �@@�@@�@@�@@�@@get�ڋq�R�[�h()<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 4731592B0009
     */
    public void validateDuplicateAccount(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDuplicateAccount(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���ʃR�[�h�`�F�b�N
        //  �P�|�P�j�@@get���ʃR�[�h�i�s�ԍ��j�ɂāA�w��s�ԍ��̎��ʃR�[�h���擾����B
        String l_strRequestNumber = this.getRequestNumber(l_intLineNo);

        //  �P�|�Q�j�@@�擾�������ʃR�[�h�Ǝw��s�ԍ����O�̖��׍s�̎��ʃR�[�h���r����B
        for (int i = 0; i < l_intLineNo; i++)
        {
            String l_strRequestNumberPrevious = this.getRequestNumber(i);

            //�@@�P�|�R�j�@@�����l�̍s�i���ʃR�[�h == ���ʃR�[�h�j�����݂���ꍇ�́A
            //�@@�@@�ڋq���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B
            if (l_strRequestNumber.equals(l_strRequestNumberPrevious))
            {
                //�R�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B
                //���b�Z�[�W�@@�@@�@@�F�w�d������ڋq�R�[�h���͎��ʃR�[�h�̐\���݂����݂��܂��B�x
                //�ǉ�������@@�F�ȉ��̓��e���Z�b�g����B
                //�@@�|���ʃR�[�h�`�F�b�N�̏ꍇ�@@�F�@@get���ʃR�[�h()
                log.debug("�d������ڋq�R�[�h���͎��ʃR�[�h�̐\���݂����݂��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02959,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    this.getRequestNumber(l_intLineNo));
            }
        }

        //  �Q�j�@@���X�E�ڋq�R�[�h�`�F�b�N
        //  �Q�|�P�j�@@get���X�R�[�h�i�s�ԍ��j�Cget�ڋq�R�[�h(�s�ԍ�)�ɂāA
        //    �w��s�ԍ��̕��X�R�[�h�C�ڋq�R�[�h���擾����B
        String l_strBranchCode = this.getBranchCode(l_intLineNo);
        String l_strAccountCode = this.getAccountCode(l_intLineNo);

        //�Q�|�Q�j�@@get�ڋq�R�[�h(�s�ԍ�)�̖߂�l��NULL�ȊO�̏ꍇ�A�ȉ��̏������s���B
        if (l_strAccountCode != null)
        {
            for (int i = 0; i < l_intLineNo; i++)
            {
                //�Q�|�Q�|�P�j�@@�擾�������X�R�[�h�C�ڋq�R�[�h�Ǝw��s�ԍ�
                //  ���O�̖��׍s�̕��X�R�[�h�C�ڋq�R�[�h���r����B
                String l_strBranchCodePrevious = this.getBranchCode(i);
                String l_strAccountCodePrevious = this.getAccountCode(i);

                //�Q�|�Q�|�Q�j�@@�����l�̍s�i���X�R�[�h == ���X�R�[�h && �ڋq�R�[�h == �ڋq�R�[�h�j�����݂���ꍇ�́A
                //�ڋq���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B
                if (l_strBranchCode.equals(l_strBranchCodePrevious)
                    && l_strAccountCode.equals(l_strAccountCodePrevious))
                {
                    //�R�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B
                    //���b�Z�[�W�@@�@@�@@�F�w�d������ڋq�R�[�h���͎��ʃR�[�h�̐\���݂����݂��܂��B�x
                    //�ǉ�������@@�F�ȉ��̓��e���Z�b�g����B
                    //�@@�|�ڋq�R�[�h�`�F�b�N�̏ꍇ�@@�F�@@get���X�R�[�h() + "�@@"�i�X�y�[�X�j + get�ڋq�R�[�h()
                    log.debug("�d������ڋq�R�[�h���͎��ʃR�[�h�̐\���݂����݂��܂��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02959,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        this.getBranchCode(l_intLineNo) + "�@@" + this.getAccountCode(l_intLineNo));
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�����J�݌����q�o�^)<BR>
     * �����J�݌����q�e�[�u���Ɍڋq���o�^����Ă��Ȃ����`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�d�����ʃR�[�h�̃`�F�b�N<BR>
     * <BR>
     * �@@�P�|�P�jget���ʃR�[�h�i�s�ԍ��j�ɂāA�w��s�ԍ��̎��ʃR�[�h���擾����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�ȉ��̏����Ō����J�݌����q�e�[�u������������B<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h�F �،���ЃR�[�h<BR>
     * �@@�@@���ʃR�[�h�F get���ʃR�[�h()�̖߂�l<BR>
     * <BR>
     * �@@�P�|�R�j�P�|�Q�j�ɂă��R�[�h���擾�ł����ꍇ�́A<BR>
     * �@@�@@�@@�ڋq���o�^����Ă���Ɣ��肵�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_02960<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * <BR>
     * �@@�Q�|�P�j�@@get���X�R�[�h�i�s�ԍ��j�Cget�ڋq�R�[�h(�s�ԍ�)�ɂāA<BR>
     * �@@�@@�@@�w��s�ԍ��̕��X�R�[�h�C�ڋq�R�[�h���擾����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@get�ڋq�R�[�h(�s�ԍ�)�̖߂�l��NULL�ȊO�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j �ȉ��̏����Ō����J�݌����q�e�[�u������������B<BR>
     * <BR>
     * �@@�@@�@@[��������]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F �،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F get���X�R�[�h()�̖߂�l<BR>
     * �@@�@@�@@�����R�[�h�F get�ڋq�R�[�h()�̖߂�l�@@���ŏ���6byte�̂ݔ�r����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�Q�|�Q�|�P�j�ɂă��R�[�h���擾�ł����ꍇ�́A<BR>
     * �@@�@@�@@�@@�ڋq���o�^����Ă���Ɣ��肵�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_02960<BR>
     * <BR>
     * �R�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B<BR>
     * �@@���b�Z�[�W�@@�@@�@@�F�w�w��̌ڋq�R�[�h���͎��ʃR�[�h�͊�<BR>
     * �@@�@@�Ɍ����J�݌����q�ɓo�^����Ă��܂��B�x<BR>
     * ��BUSINESS_ERROR<BR>
     * �@@�ǉ�������@@�F�ȉ��̓��e���Z�b�g����B<BR>
     * �@@�@@�|���ʃR�[�h�`�F�b�N�̏ꍇ�@@�F�@@get���ʃR�[�h()<BR>
     * �@@�@@�|�ڋq�R�[�h�`�F�b�N�̏ꍇ�@@�F�@@get���X�R�[�h() + "�@@"�i�X�y�[�X�j<BR>
     * �@@�@@�@@+ get�ڋq�R�[�h()<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4738FE96004F
     */
    public void validateExpAccountOpenLogin(
        int l_intLineNo, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExpAccountOpenLogin(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�d�����ʃR�[�h�̃`�F�b�N
        //�P�|�P�jget���ʃR�[�h�i�s�ԍ��j�ɂāA�w��s�ԍ��̎��ʃR�[�h���擾����B
        String l_strRequestNumber = this.getRequestNumber(l_intLineNo);

        //�P�|�Q�j�@@�ȉ��̏����Ō����J�݌����q�e�[�u������������B
        //[��������]
        //�،���ЃR�[�h�F �،���ЃR�[�h
        //���ʃR�[�h�F get���ʃR�[�h()�̖߂�l
        List l_lisRecords = null;
        try
        {
            String l_strQueryString = " institution_code = ? and acc_open_request_number = ? ";
            Object[] l_objQueryDataContainer = new Object[]{l_strInstitutionCode, l_strRequestNumber};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainer);

            //�P�|�R�j �P�|�Q�j�ɂă��R�[�h���擾�ł����ꍇ�́A�ڋq���o�^����Ă���Ɣ��肵�A��O���X���[����B
            if (!l_lisRecords.isEmpty())
            {
                //�R�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B
                //���b�Z�[�W�@@�@@�@@�F�w�w��̌ڋq�R�[�h���͎��ʃR�[�h�͊��Ɍ����J�݌����q�ɓo�^����Ă��܂��B�x
                //�@@��BUSINESS_ERROR
                //�ǉ�������@@�F�ȉ��̓��e���Z�b�g����B
                //�@@�|���ʃR�[�h�`�F�b�N�̏ꍇ�@@�F�@@get���ʃR�[�h()
                log.debug("�w��̌ڋq�R�[�h���͎��ʃR�[�h�͊��Ɍ����J�݌����q�ɓo�^����Ă��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02960,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    this.getRequestNumber(l_intLineNo));
            }

            //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N
            //�Q�|�P�j�@@get���X�R�[�h�i�s�ԍ��j�Cget�ڋq�R�[�h(�s�ԍ�)�ɂāA�w��s�ԍ��̕��X�R�[�h�C�ڋq�R�[�h���擾����B
            String l_strBranchCode = this.getBranchCode(l_intLineNo);
            String l_strAccountCode = this.getAccountCode(l_intLineNo);

            //�Q�|�Q�j�@@get�ڋq�R�[�h(�s�ԍ�)�̖߂�l��NULL�ȊO�̏ꍇ�A�ȉ��̏������s���B
            if (l_strAccountCode != null)
            {
                //�Q�|�Q�|�P�j �ȉ��̏����Ō����J�݌����q�e�[�u������������B
                //[��������]
                //�،���ЃR�[�h�F �،���ЃR�[�h
                //���X�R�[�h�F get���X�R�[�h()�̖߂�l
                //�����R�[�h�F get�ڋq�R�[�h()�̖߂�l�@@���ŏ���6byte�̂ݔ�r����B
                l_strQueryString =
                    "institution_code = ? and branch_code = ? and account_code like ? || '%' ";

                l_objQueryDataContainer = new Object[]{l_strInstitutionCode, l_strBranchCode, l_strAccountCode};

                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    ExpAccountOpenRow.TYPE,
                    l_strQueryString,
                    l_objQueryDataContainer);

                //�Q�|�Q�|�Q�j �Q�|�Q�|�P�j�ɂă��R�[�h���擾�ł����ꍇ�́A�ڋq���o�^����Ă���Ɣ��肵�A��O���X���[����B
                if (!l_lisRecords.isEmpty())
                {
                    //�R�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B
                    //���b�Z�[�W�@@�@@�@@�F�w�w��̌ڋq�R�[�h���͎��ʃR�[�h�͊��Ɍ����J�݌����q�ɓo�^����Ă��܂��B�x
                    //�@@��BUSINESS_ERROR
                    //�ǉ�������@@�F�ȉ��̓��e���Z�b�g����B
                    //�@@�|�ڋq�R�[�h�`�F�b�N�̏ꍇ�@@�F�@@get���X�R�[�h() + "�@@"�i�X�y�[�X�j + get�ڋq�R�[�h()
                    log.debug("�w��̌ڋq�R�[�h���͎��ʃR�[�h�͊��Ɍ����J�݌����q�ɓo�^����Ă��܂��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02960,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        this.getBranchCode(l_intLineNo) + "�@@" + this.getAccountCode(l_intLineNo));
                }
            }
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

    /**
     * (update�A�b�v���[�h���~)<BR>
     * �Y���A�b�v���[�h�s�ɃA�b�v���[�h���~���X�V����B<BR>
     * <BR>
     * �@@this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���<BR>
     * �ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B<BR>
     * <BR>
     * �@@�A�b�v���[�h�I������ = System.currentTimeMillis()<BR>
     * �@@�A�b�v���[�h���� = 0<BR>
     * �@@���l�P = this.�A�b�v���[�h���~<BR>
     * <BR>
     *  ���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47325AF101C1
     */
    public void updateUploadCancel() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateUploadCancel()";
        log.entering(STR_METHOD_NAME);

        //this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���
        //�ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B
        AdministratorUploadRow l_administratorUploadRow;
        try
        {
            l_administratorUploadRow =
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());

            if (l_administratorUploadRow == null)
            {
                //���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B
                log.exiting(STR_METHOD_NAME);
                return;
            }

            AdministratorUploadParams l_administratorUploadParams =
                new AdministratorUploadParams(l_administratorUploadRow);

            //�A�b�v���[�h�I������ = System.currentTimeMillis()
            l_administratorUploadParams.setUploadEndTimestamp(new Timestamp(System.currentTimeMillis()));

            //�A�b�v���[�h���� = 0
            l_administratorUploadParams.setUploadCount(0);

            //���l�P = this.�A�b�v���[�h���~
            l_administratorUploadParams.setNote1(this.uploadCancel);

            //�ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B
            Processors.getDefaultProcessor().doUpdateQuery(l_administratorUploadParams);
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

    /**
     * (validate���R�[�h�ԍ�)<BR>
     * ���R�[�h�ԍ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@get���R�[�h�ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���R�[�h�ԍ�()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�P�|�P�j�@@���R�[�h�ԍ����擾�ł��Ȃ��ꍇ�iget���R�[�h�ԍ�() == null�j�A<BR>
     * �@@�@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�w�K�{���ڂ̒l�����͂���Ă��܂���B�x<BR>
     * �@@�@@�@@�@@�@@�@@�i�G���[�R�[�h�FBUSINESS_ERROR_01309�j<BR>
     * <BR>
     * �@@�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�w���ڒl���L���ȃR�[�h�l�ł͂���܂���B�x<BR>
     * �@@�@@�@@�@@�@@�@@�i�G���[�R�[�h�FBUSINESS_ERROR_01738�j<BR>
     * <BR>
     * �Q�j�@@�P�j�ɂė�O�����������ꍇ�A�ǉ�������Ɉȉ����Z�b�g����B<BR>
     * �@@�ǉ�������F�@@�����J�ݐ\��ULCSV.���R�[�h�ԍ����x��<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47325AF101D1
     */
    public void validateRecordNumber(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateRecordNumber(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@get���R�[�h�ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //[get���R�[�h�ԍ�()�Ɏw�肷�����]
        //�s�ԍ��F�@@�s�ԍ�
        String l_strRecordNumber = this.getRecordNumber(l_intLineNo);
        //�P�|�P�j�@@���R�[�h�ԍ����擾�ł��Ȃ��ꍇ�iget���R�[�h�ԍ�() == null�j�A �ȉ��̗�O���X���[����B
        //�w�K�{���ڂ̒l�����͂���Ă��܂���B�x�i�G���[�R�[�h�FBUSINESS_ERROR_01309�j

        //�Q�j�@@�P�j�ɂė�O�����������ꍇ�A�ǉ�������Ɉȉ����Z�b�g����B
        //�ǉ�������F�@@�����J�ݐ\��ULCSV.���R�[�h�ԍ����x��
        if (WEB3StringTypeUtility.isEmpty(l_strRecordNumber))
        {
            log.debug("�K�{���ڂ̒l�����͂���Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.recordNumberLabel);
        }

        //�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A�ȉ��̗�O���X���[����B
        //�w���ڒl���L���ȃR�[�h�l�ł͂���܂���B�x�i�G���[�R�[�h�FBUSINESS_ERROR_01738�j
        if (!WEB3StringTypeUtility.isDigit(l_strRecordNumber))
        {
            log.debug("���ڒl���L���ȃR�[�h�l�ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01738,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.recordNumberLabel);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���ʃR�[�h)<BR>
     * ���ʃR�[�h�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@get���ʃR�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���ʃR�[�h()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�P�|�P�j�@@���ʃR�[�h���擾�ł��Ȃ��ꍇ�iget���ʃR�[�h() == null�j�A<BR>
     * �@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�w�K�{���ڂ̒l�����͂���Ă��܂���B�x<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i�G���[�R�[�h�FBUSINESS_ERROR_01309�j<BR>
     * <BR>
     * �@@�P�|�Q�j�@@13���łȂ��ꍇ�A �ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�w���ڒ������L���Ȕ͈͓��ł͂���܂���B�x<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i�G���[�R�[�h�FBUSINESS_ERROR_01310�j<BR>
     * <BR>
     * �@@�P�|�R�j�@@���p�����ȊO���܂܂��ꍇ�A�ȉ��̗�O���X���[����B  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�w���ڂ��L���Ȓl�ł͂���܂���B�x<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i�G���[�R�[�h�FBUSINESS_ERROR_01312�j<BR>
     * <BR>
     * �Q�j�@@�P�j�ɂė�O�����������ꍇ�A�ǉ�������Ɉȉ����Z�b�g����B<BR>
     * �@@�ǉ�������F�@@�����J�ݐ\��ULCSV.���ʃR�[�h���x��<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@throws WEB3BaseException
     */
    public void validateRequestNumber(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateRequestNumber(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@get���ʃR�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //[get���ʃR�[�h()�Ɏw�肷�����]
        //�s�ԍ��F�@@�s�ԍ�
        //�Q�j�@@�P�j�ɂė�O�����������ꍇ�A�ǉ�������Ɉȉ����Z�b�g����B
        //�ǉ�������F�@@�����J�ݐ\��ULCSV.���ʃR�[�h���x��
        String l_strRequestNumber = this.getRequestNumber(l_intLineNo);

        //�P�|�P�j�@@���ʃR�[�h���擾�ł��Ȃ��ꍇ�iget���ʃR�[�h() == null�j�A�ȉ��̗�O���X���[����B
        //�w�K�{���ڂ̒l�����͂���Ă��܂���B�x�i�G���[�R�[�h�FBUSINESS_ERROR_01309�j
        if (l_strRequestNumber == null)
        {
            log.debug("�K�{���ڂ̒l�����͂���Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.requestNumberLabel);
        }

        //�P�|�Q�j�@@13���łȂ��ꍇ�A �ȉ��̗�O���X���[����B
        //�w���ڒ������L���Ȕ͈͓��ł͂���܂���B�x�i�G���[�R�[�h�FBUSINESS_ERROR_01310�j
        if (WEB3StringTypeUtility.getByteLength(l_strRequestNumber) != 13)
        {
            log.debug("���ڒ������L���Ȕ͈͓��ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01310,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.requestNumberLabel);
        }

        //�P�|�R�j�@@���p�����ȊO���܂܂��ꍇ�A�ȉ��̗�O���X���[����B
        //�w���ڂ��L���Ȓl�ł͂���܂���B�x�i�G���[�R�[�h�FBUSINESS_ERROR_01312�j
        if (!WEB3StringTypeUtility.isDigit(l_strRequestNumber))
        {
            log.debug("���ڂ��L���Ȓl�ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01312,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.requestNumberLabel);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�����敪)<BR>
     * �����敪�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@get�����敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�����敪()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�P�|�P�j�@@�����敪���擾�ł��Ȃ��ꍇ�iget�����敪() == null�j�A<BR>
     * �@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�w�K�{���ڂ̒l�����͂���Ă��܂���B�x<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i�G���[�R�[�h�FBUSINESS_ERROR_01309�j<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�u0�F�l���� 1�F�@@�l�����v�ȊO�̏ꍇ�A �ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�w���ڒl���L���ȃR�[�h�l�ł͂���܂���B�x<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i�G���[�R�[�h�FBUSINESS_ERROR_01738�j<BR>
     * <BR>
     * �Q�j�@@�P�j�ɂė�O�����������ꍇ�A�ǉ�������Ɉȉ����Z�b�g����B<BR>
     * �@@�ǉ�������F�@@�����J�ݐ\��ULCSV.�����敪���x��<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@throws WEB3BaseException
     */
    public void validateAccountType(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountType(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@get�����敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //[get�����敪()�Ɏw�肷�����]
        //�s�ԍ��F�@@�s�ԍ�
        //�Q�j�@@�P�j�ɂė�O�����������ꍇ�A�ǉ�������Ɉȉ����Z�b�g����B
        //�ǉ�������F�@@�����J�ݐ\��ULCSV.�����敪���x��
        String l_strAccountDiv = this.getAccountDiv(l_intLineNo);

        //�P�|�P�j�@@�����敪���擾�ł��Ȃ��ꍇ�iget�����敪() == null�j�A�ȉ��̗�O���X���[����B
        //�w�K�{���ڂ̒l�����͂���Ă��܂���B�x�i�G���[�R�[�h�FBUSINESS_ERROR_01309�j
        if (l_strAccountDiv == null)
        {
            log.debug("�K�{���ڂ̒l�����͂���Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.accountTypeLabel);
        }

        //�P�|�Q�j�@@�u0�F�l���� 1�F�@@�l�����v�ȊO�̏ꍇ�A �ȉ��̗�O���X���[����B
        //�w���ڒl���L���ȃR�[�h�l�ł͂���܂���B�x�i�G���[�R�[�h�FBUSINESS_ERROR_01738�j
        if (!(WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_strAccountDiv)
            || WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_strAccountDiv)))
        {
            log.debug("���ڒl���L���ȃR�[�h�l�ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01738,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.accountTypeLabel);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate������������)<BR>
     * �������������̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@get������������()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get������������()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�P�|�P�j�@@get������������() != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�P�|�P�|�P�j�@@��������������14���łȂ��A���́A����łȂ��ꍇ�A<BR>
     * �@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�w���ڂ��L���Ȓl�ł͂���܂���B�x�i�G���[�R�[�h�FBUSINESS_ERROR_01312�j<BR>
     * <BR>
     * �Q�j�@@�P�j�ɂė�O�����������ꍇ�A�ǉ�������Ɉȉ����Z�b�g����B<BR>
     * �@@�ǉ�������F�@@�����J�ݐ\��ULCSV.���������������x��<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@throws WEB3BaseException
     */
    public void validateInfomationClaimDatetime(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInfomationClaimDatetime(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@get������������()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //�s�ԍ��F�@@�s�ԍ�
        String l_strInfoClaimDate = this.getInfoClaimDate(l_intLineNo);

        //�P�|�P�j�@@get������������() != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (l_strInfoClaimDate != null)
        {
            //�P�|�P�|�P�j�@@��������������14���łȂ��A���́A����łȂ��ꍇ�A �ȉ��̗�O���X���[����B
            //     �@@�w���ڂ��L���Ȓl�ł͂���܂���B�x�i�G���[�R�[�h�FBUSINESS_ERROR_01312�j
            //�Q�j�@@�P�j�ɂė�O�����������ꍇ�A�ǉ�������Ɉȉ����Z�b�g����B
            //      �ǉ�������F�@@�����J�ݐ\��ULCSV.���������������x��
            if (WEB3StringTypeUtility.getByteLength(l_strInfoClaimDate) != 14
                || !WEB3StringTypeUtility.isDateStr(
                    l_strInfoClaimDate,
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS))
            {
                log.debug("���ڂ��L���Ȓl�ł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01312,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    this.infoClaimDateLabel);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (to�����J�݌����q)<BR>
     * ���׍s�̓��e���A�����J�݌����q�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g����<BR>
     * �@@�f�t�H���g�R���X�g���N�^�ɂāA�����J�݌����q�s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�v���p�e�B�Z�b�g<BR>
     * �@@�����J�ݐ\����񃁃b�Z�[�W�I�u�W�F�N�g���A�������������J�݌����q�s�I�u�W�F�N�g��<BR>
     * �@@�ȉ��̒ʂ�l���Z�b�g����B<BR>
     * <BR>
     * �@@�Q�|�P�j�،���ЃR�[�h<BR>
     * �@@�@@�@@�����J�݌����q�s.�،���ЃR�[�h�Ɉ���.�،���ЃR�[�h���Z�b�g����<BR>
     * <BR>
     * �@@�Q�|�Q�j���ʃR�[�h<BR>
     * �@@�@@�@@get���ʃR�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���ʃR�[�h�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���ʃR�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�R�j���X�R�[�h<BR>
     * �@@�@@�@@get���X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���X�R�[�h�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���X�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�S�j�ڋq�R�[�h<BR>
     * �@@�@@�@@get�ڋq�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����R�[�h�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�ڋq�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�T�j���҃R�[�h�iSONAR�j<BR>
     * �@@�@@�@@get���҃R�[�h�iSONAR�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���҃R�[�h�iSONAR�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���҃R�[�h�iSONAR�j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�U�j�����敪<BR>
     * �@@�@@�@@get�����敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����敪�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�V�j���͋敪<BR>
     * �@@�@@�@@get���͋敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���͋敪�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���͋敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�W�j������������<BR>
     * �@@�@@�@@get������������()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�������������ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get������������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�X�j�����O�@@���i�����j<BR>
     * �@@�@@�@@get�����O�@@���i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�ڋq���i�����j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����O�@@���i�����j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�O�j�����O�@@���i�����j<BR>
     * �@@�@@�@@get�����O�@@���i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�ڋq���i�����j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����O�@@���i�����j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�P�j�����O�@@���i�t���K�i�j<BR>
     * �@@�@@�@@get�����O�@@���i�t���K�i�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�ڋq���i�J�i�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����O�@@���i�t���K�i�j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�Q�j�����O�@@���i�t���K�i�j<BR>
     * �@@�@@�@@get�����O�@@���i�t���K�i�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�ڋq���i�J�i�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����O�@@���i�t���K�i�j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�R�j����<BR>
     * �@@�@@�@@get����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���ʂɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�S�j���N�����N��<BR>
     * �@@�@@�@@get���N�����N��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���N�����N���ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���N�����N��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�T�j���N����<BR>
     * �@@�@@�@@get���N����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���N�����ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���N����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�U�j���[���A�h���X<BR>
     * �@@�@@�@@get���[���A�h���X()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.email�A�h���X�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���[���A�h���X()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�V�j�X�֔ԍ�<BR>
     * �@@�@@�@@get�X�֔ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�X�֔ԍ��ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�X�֔ԍ�()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�W�j�Z���P�i�����j<BR>
     * �@@�@@�@@get�Z���P�i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�Z���P�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�Z���P�i�����j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�X�j�Z���Q�i�����j<BR>
     * �@@�@@�@@get�Z���Q�i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�Z���Q�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�Z���Q�i�����j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�Q�O�j�Z���R�i�����j<BR>
     * �@@�@@�@@get�Z���R�i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�Z���R�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�Z���R�i�����j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�Q�P�j�Z���P�i�J�i�j<BR>
     * �@@�@@�@@get�Z���P�i�J�i�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�Z���P�i�J�i�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�Z���P�i�J�i�j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�Q�Q�j�Z���Q�i�J�i�j<BR>
     * �@@�@@�@@get�Z���Q�i�J�i�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�Z���Q�i�J�i�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�Z���Q�i�J�i�j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�Q�R�j�Z���R�i�J�i�j<BR>
     * �@@�@@�@@get�Z���R�i�J�i�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�Z���R�i�J�i�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�Z���R�i�J�i�j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�Q�S�j�d�b�ԍ�<BR>
     * <BR>
     * �@@�@@�Q�|�Q�S�|�P�j�d�b�ԍ��i�s�O�ǔԁj<BR>
     * �@@�@@�@@get�d�b�ԍ��i�s�O�ǔԁj()�ɂāA�w��s�ԍ��̃f�[�^���擾�B<BR>
     * <BR>
     * �@@�@@�@@[get�d�b�ԍ��i�s�O�ǔԁj()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�@@�Q�|�Q�S�|�Q�j�d�b�ԍ��i�ǔԁj<BR>
     * �@@�@@�@@get�d�b�ԍ��i�ǔԁj()�ɂāA�w��s�ԍ��̃f�[�^���擾�B<BR>
     * <BR>
     * �@@�@@�@@[get�d�b�ԍ��i�ǔԁj()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�@@�Q�|�Q�S�|�R�j�d�b�ԍ��i�ԍ��j<BR>
     * �@@�@@�@@get�d�b�ԍ��i�ԍ��j()�ɂāA�w��s�ԍ��̃f�[�^���擾�B<BR>
     * <BR>
     * �@@�@@�@@[get�d�b�ԍ��i�ԍ��j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�@@�Q�|�Q�S�|�S�j�����J�݌����q�s.�d�b�ԍ��Ɉȉ����Z�b�g����B<BR>
     * �@@�@@�@@get�d�b�ԍ��i�s�O�ǔԁj()�̖߂�l + "-" + <BR>
     * �@@�@@�@@get�d�b�ԍ��i�ǔԁj()�̖߂�l + "-" + <BR>
     * �@@�@@�@@get�d�b�ԍ��i�ԍ��j()�̖߂�l<BR>
     * <BR>
     * �@@�Q�|�Q�T�j�g�єԍ�<BR>
     * <BR>
     * �@@�@@�Q�|�Q�T�|�P�j�g�єԍ��i�s�O�ǔԁj<BR>
     * �@@�@@�@@get�g�єԍ��i�s�O�ǔԁj()�ɂāA�w��s�ԍ��̃f�[�^���擾�B<BR>
     * <BR>
     * �@@�@@�@@[get�g�єԍ��i�s�O�ǔԁj()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�@@�Q�|�Q�T�|�Q�j�g�єԍ��i�ǔԁj<BR>
     * �@@�@@�@@get�g�єԍ��i�ǔԁj()�ɂāA�w��s�ԍ��̃f�[�^���擾�B<BR>
     * <BR>
     * �@@�@@�@@[get�g�єԍ��i�ǔԁj()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�@@�Q�|�Q�T�|�R�j�g�єԍ��i�ԍ��j<BR>
     * �@@�@@�@@get�g�єԍ��i�ԍ��j()�ɂāA�w��s�ԍ��̃f�[�^���擾�B<BR>
     * �@@�@@<BR>
     * �@@�@@�@@[get�g�єԍ��i�ԍ��j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * �@@�@@<BR>
     * �@@�@@�Q�|�Q�T�|�S�j�����J�݌����q�s.�A����d�b�ԍ��i�g�сj�Ɉȉ����Z�b�g����B<BR>
     * �@@�@@�@@get�g�єԍ��i�s�O�ǔԁj()�̖߂�l + "-" +<BR>
     * �@@�@@�@@get�g�єԍ��i�ǔԁj()�̖߂�l + "-" +<BR>
     * �@@�@@�@@get�g�єԍ��i�ԍ��j()�̖߂�l<BR>
     * �@@�@@<BR>
     * �@@�Q�|�Q�U�j�E�Ƌ敪<BR>
     * �@@�@@�@@get�E�Ƌ敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�E�Ƌ敪�ɃZ�b�g����B<BR>
     * �@@�@@�@@<BR>
     * �@@�@@�@@[get�E�Ƌ敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�Q�V�j�Ζ��於��<BR>
     * �@@�@@�@@get�Ζ��於��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�Ζ��於�̂ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�Ζ��於��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�Q�W�j�Ζ���X�֔ԍ�<BR>
     * �@@�@@�@@get�Ζ���X�֔ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�Ζ���X�֔ԍ��ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�Ζ���X�֔ԍ�()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�Q�X�j�Ζ���Z��<BR>
     * �@@�@@�@@get�Ζ���Z��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�Ζ���Z���ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�Ζ���Z��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�R�O�j�Ζ���d�b�ԍ�<BR>
     * <BR>
     * �@@�@@�Q�|�R�O�|�P�jget�Ζ���d�b�ԍ��P<BR>
     * �@@�@@�@@get�Ζ���d�b�ԍ��P()�ɂāA�w��s�ԍ��̃f�[�^���擾�B<BR>
     * <BR>
     * �@@�@@�@@[get�Ζ���d�b�ԍ��P()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�@@�Q�|�R�O�|�Q�jget�Ζ���d�b�ԍ��Q<BR>
     * �@@�@@�@@get�Ζ���d�b�ԍ��Q()�ɂāA�w��s�ԍ��̃f�[�^���擾�B<BR>
     * <BR>
     * �@@�@@�@@[get�Ζ���d�b�ԍ��Q()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�@@�Q�|�R�O�|�R�jget�Ζ���d�b�ԍ��P<BR>
     * �@@�@@�@@get�Ζ���d�b�ԍ��R()�ɂāA�w��s�ԍ��̃f�[�^���擾�B<BR>
     * <BR>
     * �@@�@@�@@[get�Ζ���d�b�ԍ��R()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�@@�Q�|�R�O�|�S�j�����J�݌����q�s.�Ζ���d�b�ԍ��ȉ����Z�b�g����B<BR>
     * �@@�@@�@@get�Ζ���d�b�ԍ��P()�̖߂�l + "-" +<BR>
     * �@@�@@�@@get�Ζ���d�b�ԍ��Q()�̖߂�l + "-" +<BR>
     * �@@�@@�@@get�Ζ���d�b�ԍ��R()�̖߂�l<BR>
     * <BR>
     * �@@�Q�|�R�P�j��������<BR>
     * �@@�@@�@@get��������()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���������ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�R�Q�j��E<BR>
     * �@@�@@�@@get��E()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.��E�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get��E()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�R�R�j���ю�Ƃ̑���<BR>
     * �@@�@@�@@get���ю�Ƃ̑���()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����敪�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���ю�Ƃ̑���()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�R�S�j���ю喼<BR>
     * �@@�@@�@@get���ю喼()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���ю喼�i�����j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���ю喼()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�R�T�j���ю�@@�Ζ���<BR>
     * �@@�@@�@@get���ю�@@�Ζ���()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���ю�Ζ���ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���ю�@@�Ζ���()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�R�U�j���ю�@@��E<BR>
     * �@@�@@�@@get���ю�@@��E()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���ю��E�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���ю�@@��E()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�R�V�j�U����<BR>
     * �@@�@@�@@get�U����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �U�֋敪�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�U����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�R�W�j��s�R�[�h<BR>
     * �@@�@@�@@get��s�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� ��s�R�[�h�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get��s�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�R�X�j��s��<BR>
     * �@@�@@�@@get��s��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� ��s���ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get��s��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�S�O�j�x�X�R�[�h<BR>
     * �@@�@@�@@get�x�X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �x�X�R�[�h�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�x�X�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�S�P�j�x�X��<BR>
     * �@@�@@�@@get�x�X��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �x�X���ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�x�X��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�S�Q�j�a���敪<BR>
     * �@@�@@�@@get�a���敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �a���敪�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�a���敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�S�R�j�����ԍ�<BR>
     * �@@�@@�@@get�����ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �����ԍ��ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����ԍ�()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�S�S�j�ʒ��L��<BR>
     * �@@�@@�@@get�ʒ��L��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �ʒ��L���ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�ʒ��L��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�S�T�j�ʒ��ԍ�<BR>
     * �@@�@@�@@get�ʒ��ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �ʒ��ԍ��ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�ʒ��ԍ�()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�S�U�j�����o���i������������j<BR>
     * �@@�@@�@@get�����o���i������������j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����o�� ���������ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����o���i������������j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�S�V�j�����o���i�����M�p����j<BR>
     * �@@�@@�@@get�����o���i�����M�p����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����o�� �M�p����ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����o���i�����M�p����j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�S�W�j�����o���i���j<BR>
     * �@@�@@�@@get�����o���i���j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����o�� ���ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����o���i���j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�S�X�j�����o���i�����w���I�v�V�����j<BR>
     * �@@�@@�@@get�����o���i�����w���I�v�V�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����o�� �]���ЍɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����o���i�����w���I�v�V�����j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�T�O�j�����o���i�����M���F�����j<BR>
     * �@@�@@�@@get�����o���i�����M���F�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����o�� �����M���i�����j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����o���i�����M���F�����j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�T�P�j�����o���i�����M���F���Ѝj<BR>
     * �@@�@@�@@get�����o���i�����M���F���Ѝj()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����o�� �����M���i���Ѝj�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����o���i�����M���F���Ѝj()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�T�Q�j�����o���i�����w���敨�j<BR>
     * �@@�@@�@@get�����o���i�����w���敨�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����o�� �敨�E�I�v�V�����ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����o���i�����w���敨�j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�T�R�j�����o���i�O���،��j<BR>
     * �@@�@@�@@get�����o���i�O���،��j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����o�� �O���،��ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����o���i�O���،��j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�T�S�j�����o���i���̑��j<BR>
     * �@@�@@�@@get�����o���i���̑��j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����o�� ���̑��ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����o���i���̑��j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�T�T�j��]��������̎�ށ@@�����i�����j<BR>
     * �@@�@@�@@get��]��������̎�ށ@@�����i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����̂��邨��� ���������t���O�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get��]��������̎�ށ@@�����i�����j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�T�U�j��]��������̎�ށ@@�����i�M�p�j<BR>
     * �@@�@@�@@get��]��������̎�ށ@@�����i�M�p�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����̂��邨��� �M�p����t���O�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get��]��������̎�ށ@@�����i�M�p�j()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�T�V�j��]��������̎�ށ@@���敨<BR>
     * �@@�@@�@@get��]��������̎�ށ@@���敨()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����̂��邨��� ���t���O�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get��]��������̎�ށ@@���敨()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�T�W�j��]��������̎�ށ@@�����M��<BR>
     * �@@�@@�@@get��]��������̎�ށ@@�����M��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����̂��邨��� �����M���t���O�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get��]��������̎�ށ@@�����M��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�T�X�j��]��������̎�ށ@@�����w���敨<BR>
     * �@@�@@�@@get��]��������̎�ށ@@�����w���敨()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����̂��邨��� �敨�E�I�v�V�����t���O�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get��]��������̎�ށ@@�����w���敨()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�U�O�j��]��������̎�ށ@@�����w���I�v�V����<BR>
     * �@@�@@�@@get��]��������̎�ށ@@�����w���I�v�V����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����̂��邨��� �O���،��t���O�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get��]��������̎�ށ@@�����w���I�v�V����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�U�P�j�N���敪<BR>
     * �@@�@@�@@get�N���敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�N���敪�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�N���敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�U�Q�j���Z���Y�敪<BR>
     * �@@�@@�@@get���Z���Y�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.���Z���Y�敪�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get���Z���Y�敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�U�R�j��������敪����<BR>
     * �@@�@@�@@get��������敪����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.��������敪�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get��������敪����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�U�S�j�����ғo�^�敪<BR>
     * �@@�@@�@@get�����ғo�^�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����ғo�^�t���O�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����ғo�^�敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�U�T�j�����ғo�^����<BR>
     * �@@�@@�@@get�����ғo�^����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�����Җ������ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����ғo�^����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�U�U�j�Ǝ�敪<BR>
     * �@@�@@�@@get�Ǝ�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�P�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�Ǝ�敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�U�V�j�����̌`�ԋ敪<BR>
     * �@@�@@�@@get�����̌`�ԋ敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�Q�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����̌`�ԋ敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�U�W�j�����Ҋ֌W�敪<BR>
     * �@@�@@�@@get�����Ҋ֌W�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�R�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����Ҋ֌W�敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�U�X�j�����ړI�敪<BR>
     * �@@�@@�@@get�����ړI�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�S�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�����ړI�敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�V�O�j������@@�敪<BR>
     * �@@�@@�@@get������@@�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�T�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get������@@�敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�V�P�j�{�l�m�F���ދ敪<BR>
     * �@@�@@�@@get�{�l�m�F���ދ敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�U�j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[get�{�l�m�F���ދ敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�V�Q�j�K�{���ڂɃf�t�H���g�l���Z�b�g<BR>
     * �@@�@@�@@�|�����J�݌����q�s.���������t���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����o���L�� ���������t���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����o���L�� �M�p����t���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����o���L�� ���t���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����o���L�� �]���Ѝt���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����o���L�� �����M���i�����j�t���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����o���L�� �����M���i���Ѝj�t���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����o���L�� �敨�E�I�v�V�����t���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����o���L�� �O���،��t���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����o���L�� ���̑��t���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����̂��邨��� �����~�j�����t���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����̂��邨��� ���̑��t���O == 0�FDEFAULT<BR>
     * �@@�@@�@@�|�����J�݌����q�s.�����p�敪 == 0�FDEFAULT<BR>
     * <BR>
     * �@@�� boolean�^�̍��ڂ́A�Ή�����BooleanEnum�l�ɕϊ����邱�ƁB<BR>
     * <BR>
     * �R�j�@@�I�u�W�F�N�g����<BR>
     * �@@���������s�I�u�W�F�N�g���w�肵�A�����J�݌����q�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return WEB3AccOpenExpAccountOpen
     * @@throws WEB3BaseException
     * @@roseuid 4733C7040043
     */
    public WEB3AccOpenExpAccountOpen toExpAccountOpen(
        int l_intLineNo, String l_strInstitutionCode) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "toExpAccountOpen(int, String)";
    	log.entering(STR_METHOD_NAME);

        BooleanEnum l_insiderFlag = BooleanEnum.FALSE;
    	//�P�j�@@�s�I�u�W�F�N�g����
    	//�@@�f�t�H���g�R���X�g���N�^�ɂāA�����J�݌����q�s�I�u�W�F�N�g�𐶐�����B
    	ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams();

    	//�Q�j�@@�v���p�e�B�Z�b�g
    	//�@@�Q�|�P�j�،���ЃR�[�h
    	//�@@�@@�@@�����J�݌����q�s.�،���ЃR�[�h�Ɉ���.�،���ЃR�[�h���Z�b�g����
    	l_expAccountOpenParams.setInstitutionCode(l_strInstitutionCode);

    	//�@@�Q�|�Q�j���ʃR�[�h
    	//�@@�@@�@@get���ʃR�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
    	//�@@�@@�@@�����J�݌����q�s.���ʃR�[�h�ɃZ�b�g����B
    	//�@@�@@�@@[get���ʃR�[�h()�Ɏw�肷�����]
    	//�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
    	l_expAccountOpenParams.setAccOpenRequestNumber(this.getRequestNumber(l_intLineNo));

    	//�@@�Q�|�R�j���X�R�[�h
    	//�@@�@@�@@get���X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
    	//�@@�@@�@@�����J�݌����q�s.���X�R�[�h�ɃZ�b�g����B
    	//�@@�@@�@@[get���X�R�[�h()�Ɏw�肷�����]
    	//�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
    	l_expAccountOpenParams.setBranchCode(this.getBranchCode(l_intLineNo));

        //�@@�Q�|�S�j�ڋq�R�[�h
        //�@@�@@�@@get�ڋq�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����R�[�h�ɃZ�b�g����B
        //
        //�@@�@@�@@[get�ڋq�R�[�h()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
    	l_expAccountOpenParams.setAccountCode(this.getAccountCode(l_intLineNo));

        //�@@�Q�|�T�j���҃R�[�h�iSONAR�j
        //�@@�@@�@@get���҃R�[�h�iSONAR�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.���҃R�[�h�iSONAR�j�ɃZ�b�g����B
        //
        //�@@�@@�@@[get���҃R�[�h�iSONAR�j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
    	l_expAccountOpenParams.setSonarTraderCode(this.getSonarTraderCode(l_intLineNo));

        //�@@�Q�|�U�j�����敪
        //�@@�@@�@@get�����敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����敪�ɃZ�b�g����B
        //�@@�@@�@@[get�����敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
    	l_expAccountOpenParams.setAccountDiv(this.getAccountDiv(l_intLineNo));

        //�@@�Q�|�V�j���͋敪
        //�@@�@@�@@get���͋敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.���͋敪�ɃZ�b�g����B
        //�@@�@@�@@[get���͋敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
    	l_expAccountOpenParams.setOrderDiv(this.getOrderDiv(l_intLineNo));

        //�@@�Q�|�W�j������������
        //�@@�@@�@@get������������()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�������������ɃZ�b�g����B
        //�@@�@@�@@[get������������()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        String l_strTimeStyle = WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS;
        String l_strInfoClaimDate = this.getInfoClaimDate(l_intLineNo);
        if (l_strInfoClaimDate != null)
        {
            Date l_datInfoClaimDate = WEB3DateUtility.getDate(this.getInfoClaimDate(l_intLineNo), l_strTimeStyle);
            l_expAccountOpenParams.setInfomationClaimDatetime(
                new Timestamp(l_datInfoClaimDate.getTime()));
        }

        //�@@�Q�|�X�j�����O�@@���i�����j
        //�@@�@@�@@get�����O�@@���i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�ڋq���i�����j�ɃZ�b�g����B
        //�@@�@@�@@[get�����O�@@���i�����j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setFamilyName(this.getAccountFamilyName(l_intLineNo));

        //�@@�Q�|�P�O�j�����O�@@���i�����j
        //�@@�@@�@@get�����O�@@���i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�ڋq���i�����j�ɃZ�b�g����B
        //�@@�@@�@@[get�����O�@@���i�����j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setGivenName(this.getAccountName(l_intLineNo));

        //�@@�Q�|�P�P�j�����O�@@���i�t���K�i�j
        //�@@�@@�@@get�����O�@@���i�t���K�i�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�ڋq���i�J�i�j�ɃZ�b�g����B
        //�@@�@@�@@[get�����O�@@���i�t���K�i�j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setFamilyNameAlt1(this.getAccountFamilyNameKana(l_intLineNo));

        //�@@�Q�|�P�Q�j�����O�@@���i�t���K�i�j
        //�@@�@@�@@get�����O�@@���i�t���K�i�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�ڋq���i�J�i�j�ɃZ�b�g����B
        //�@@�@@�@@[get�����O�@@���i�t���K�i�j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setGivenNameAlt1(this.getAccountNameKana(l_intLineNo));

        //�@@�Q�|�P�R�j����
        //�@@�@@�@@get����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.���ʂɃZ�b�g����B
        //�@@�@@�@@[get����()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setSex(this.getSex(l_intLineNo));

        //�@@�Q�|�P�S�j���N�����N��
        //�@@�@@�@@get���N�����N��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.���N�����N���ɃZ�b�g����B
        //�@@�@@�@@[get���N�����N��()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setEraBorn(this.getEraBorn(l_intLineNo));

        //�@@�Q�|�P�T�j���N����
        //�@@�@@�@@get���N����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.���N�����ɃZ�b�g����B
        //�@@�@@�@@[get���N����()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setBornDate(this.getBornDate(l_intLineNo));

        //�@@�Q�|�P�U�j���[���A�h���X
        //�@@�@@�@@get���[���A�h���X()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.email�A�h���X�ɃZ�b�g����B
        //�@@�@@�@@[get���[���A�h���X()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setEmailAddress(this.getMailAddress(l_intLineNo));

        //�@@�Q�|�P�V�j�X�֔ԍ�
        //�@@�@@�@@get�X�֔ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�X�֔ԍ��ɃZ�b�g����B
        //�@@�@@�@@[get�X�֔ԍ�()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setZipCode(this.getZipCode(l_intLineNo));

        //�@@�Q�|�P�W�j�Z���P�i�����j
        //�@@�@@�@@get�Z���P�i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�Z���P�ɃZ�b�g����B
        //�@@�@@�@@[get�Z���P�i�����j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setAddressLine1(this.getAddress1(l_intLineNo));

        //�@@�Q�|�P�X�j�Z���Q�i�����j
        //�@@�@@�@@get�Z���Q�i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�Z���Q�ɃZ�b�g����B
        //�@@�@@�@@[get�Z���Q�i�����j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setAddressLine2(this.getAddress2(l_intLineNo));

        //�@@�Q�|�Q�O�j�Z���R�i�����j
        //�@@�@@�@@get�Z���R�i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�Z���R�ɃZ�b�g����B
        //�@@�@@�@@[get�Z���R�i�����j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setAddressLine3(this.getAddress3(l_intLineNo));

        //�@@�Q�|�Q�P�j�Z���P�i�J�i�j
        //�@@�@@�@@get�Z���P�i�J�i�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�Z���P�i�J�i�j�ɃZ�b�g����B
        //�@@�@@�@@[get�Z���P�i�J�i�j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setAddressLine1Kana(this.getAddressKana1(l_intLineNo));

        //�@@�Q�|�Q�Q�j�Z���Q�i�J�i�j
        //�@@�@@�@@get�Z���Q�i�J�i�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�Z���Q�i�J�i�j�ɃZ�b�g����B
        //�@@�@@�@@[get�Z���Q�i�J�i�j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setAddressLine2Kana(this.getAddressKana2(l_intLineNo));

        //�@@�Q�|�Q�R�j�Z���R�i�J�i�j
        //�@@�@@�@@get�Z���R�i�J�i�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�Z���R�i�J�i�j�ɃZ�b�g����B
        //�@@�@@�@@[get�Z���R�i�J�i�j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setAddressLine3Kana(this.getAddressKana3(l_intLineNo));

        //�@@�Q�|�Q�S�j�d�b�ԍ�
        //
        //�@@�@@�Q�|�Q�S�|�P�j�d�b�ԍ��i�s�O�ǔԁj
        //�@@�@@�@@get�d�b�ԍ��i�s�O�ǔԁj()�ɂāA�w��s�ԍ��̃f�[�^���擾�B
        //�@@�@@�@@[get�d�b�ԍ��i�s�O�ǔԁj()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        String l_strTelephoneAreaCode = this.getTelephoneAreaCode(l_intLineNo);

        //�@@�@@�Q�|�Q�S�|�Q�j�d�b�ԍ��i�ǔԁj
        //�@@�@@�@@get�d�b�ԍ��i�ǔԁj()�ɂāA�w��s�ԍ��̃f�[�^���擾�B
        //�@@�@@�@@[get�d�b�ԍ��i�ǔԁj()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        String l_strTelephoneExchangeNumber = this.getTelephoneExchangeNumber(l_intLineNo);

        //�@@�@@�Q�|�Q�S�|�R�j�d�b�ԍ��i�ԍ��j
        //�@@�@@�@@get�d�b�ԍ��i�ԍ��j()�ɂāA�w��s�ԍ��̃f�[�^���擾�B
        //�@@�@@�@@[get�d�b�ԍ��i�ԍ��j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        String l_strTelephoneNumber = this.getTelephoneNumber(l_intLineNo);

        //�@@�@@�Q�|�Q�S�|�S�j�����J�݌����q�s.�d�b�ԍ��Ɉȉ����Z�b�g����B
        //�@@�@@�@@get�d�b�ԍ��i�s�O�ǔԁj()�̖߂�l + "-" +
        //�@@�@@�@@get�d�b�ԍ��i�ǔԁj()�̖߂�l + "-" +
        //�@@�@@�@@get�d�b�ԍ��i�ԍ��j()�̖߂�l
        if (l_strTelephoneAreaCode != null
            || l_strTelephoneExchangeNumber != null
            || l_strTelephoneNumber != null)
        {
            l_expAccountOpenParams.setTelephone(l_strTelephoneAreaCode + "-"
                + l_strTelephoneExchangeNumber + "-"
                + l_strTelephoneNumber);
        }

        //�@@�Q�|�Q�T�j�g�єԍ�
        //
        //�@@�@@�Q�|�Q�T�|�P�j�g�єԍ��i�s�O�ǔԁj
        //�@@�@@�@@get�g�єԍ��i�s�O�ǔԁj()�ɂāA�w��s�ԍ��̃f�[�^���擾�B
        //�@@�@@�@@[get�g�єԍ��i�s�O�ǔԁj()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        String l_strMobileAreaCode = this.getMobileAreaCode(l_intLineNo);

        //�@@�@@�Q�|�Q�T�|�Q�j�g�єԍ��i�ǔԁj
        //�@@�@@�@@get�g�єԍ��i�ǔԁj()�ɂāA�w��s�ԍ��̃f�[�^���擾�B
        //�@@�@@�@@[get�g�єԍ��i�ǔԁj()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        String l_strMobileExchangeNumber = this.getMobileExchangeNumber(l_intLineNo);

        //�@@�@@�Q�|�Q�T�|�R�j�g�єԍ��i�ԍ��j
        //�@@�@@�@@get�g�єԍ��i�ԍ��j()�ɂāA�w��s�ԍ��̃f�[�^���擾�B
        //�@@�@@�@@[get�g�єԍ��i�ԍ��j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        String l_strMobileNumber = this.getMobileNumber(l_intLineNo);

        //�@@�@@�Q�|�Q�T�|�S�j�����J�݌����q�s.�A����d�b�ԍ��i�g�сj�Ɉȉ����Z�b�g����B
        //�@@�@@�@@get�g�єԍ��i�s�O�ǔԁj()�̖߂�l + "-" +
        //�@@�@@�@@get�g�єԍ��i�ǔԁj()�̖߂�l + "-" +
        //�@@�@@�@@get�g�єԍ��i�ԍ��j()�̖߂�l
        if (l_strMobileAreaCode != null
            || l_strMobileExchangeNumber != null
            || l_strMobileNumber != null)
        {
            l_expAccountOpenParams.setMobile(l_strMobileAreaCode + "-"
                + l_strMobileExchangeNumber + "-" + l_strMobileNumber);
        }

        //�@@�Q�|�Q�U�j�E�Ƌ敪
        //�@@�@@�@@get�E�Ƌ敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�E�Ƌ敪�ɃZ�b�g����B
        //�@@�@@�@@[get�E�Ƌ敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setOccupationDiv(this.getOccupationDiv(l_intLineNo));

        //�@@�Q�|�Q�V�j�Ζ��於��
        //�@@�@@�@@get�Ζ��於��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�Ζ��於�̂ɃZ�b�g����B
        //�@@�@@�@@[get�Ζ��於��()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setOffice(this.getOffice(l_intLineNo));

        //�@@�Q�|�Q�W�j�Ζ���X�֔ԍ�
        //�@@�@@�@@get�Ζ���X�֔ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�Ζ���X�֔ԍ��ɃZ�b�g����B
        //�@@�@@�@@[get�Ζ���X�֔ԍ�()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setOfficeZipCode(this.getOfficeZipCode(l_intLineNo));

        //�@@�Q�|�Q�X�j�Ζ���Z��
        //�@@�@@�@@get�Ζ���Z��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�Ζ���Z���ɃZ�b�g����B
        //�@@�@@�@@[get�Ζ���Z��()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setOfficeAddress(this.getOfficeAddress(l_intLineNo));

        //�@@�Q�|�R�O�j�Ζ���d�b�ԍ�
        //
        //�@@�@@�Q�|�R�O�|�P�jget�Ζ���d�b�ԍ��P
        //�@@�@@�@@get�Ζ���d�b�ԍ��P()�ɂāA�w��s�ԍ��̃f�[�^���擾�B
        //�@@�@@�@@[get�Ζ���d�b�ԍ��P()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        String l_strOfficeTelephone1 = this.getOfficeTelephone1(l_intLineNo);

        //�@@�@@�Q�|�R�O�|�Q�jget�Ζ���d�b�ԍ��Q
        //�@@�@@�@@get�Ζ���d�b�ԍ��Q()�ɂāA�w��s�ԍ��̃f�[�^���擾�B
        //�@@�@@�@@[get�Ζ���d�b�ԍ��Q()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        String l_strOfficeTelephone2 = this.getOfficeTelephone2(l_intLineNo);

        //�@@�@@�Q�|�R�O�|�R�jget�Ζ���d�b�ԍ��P
        //�@@�@@�@@get�Ζ���d�b�ԍ��R()�ɂāA�w��s�ԍ��̃f�[�^���擾�B
        //�@@�@@�@@[get�Ζ���d�b�ԍ��R()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        String l_strOfficeTelephone3 = this.getOfficeTelephone3(l_intLineNo);

        //�@@�@@�Q�|�R�O�|�S�j�����J�݌����q�s.�Ζ���d�b�ԍ��ȉ����Z�b�g����B
        //�@@�@@�@@get�Ζ���d�b�ԍ��P()�̖߂�l + "-" +
        //�@@�@@�@@get�Ζ���d�b�ԍ��Q()�̖߂�l + "-" +
        //�@@�@@�@@get�Ζ���d�b�ԍ��R()�̖߂�l
        if (l_strOfficeTelephone1 != null
            || l_strOfficeTelephone2 != null
            || l_strOfficeTelephone3 != null)
        {
            l_expAccountOpenParams.setOfficeTelephone(l_strOfficeTelephone1 + "-"
                + l_strOfficeTelephone2 + "-" + l_strOfficeTelephone3);
        }

        //�@@�Q�|�R�P�j��������
        //�@@�@@�@@get��������()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.���������ɃZ�b�g����B
        //�@@�@@�@@[get��������()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setDepartment(this.getDepartment(l_intLineNo));

        //�@@�Q�|�R�Q�j��E
        //�@@�@@�@@get��E()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.��E�ɃZ�b�g����B
        //�@@�@@�@@[get��E()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setPost(this.getPost(l_intLineNo));

        //�@@�Q�|�R�R�j���ю�Ƃ̑���
        //�@@�@@�@@get���ю�Ƃ̑���()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����敪�ɃZ�b�g����B
        //�@@�@@�@@[get���ю�Ƃ̑���()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setFamilyRelationship(this.getHouseHolderFamilyRelation(l_intLineNo));

        //�@@�Q�|�R�S�j���ю喼
        //�@@�@@�@@get���ю喼()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.���ю喼�i�����j�ɃZ�b�g����B
        //�@@�@@�@@[get���ю喼()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setHouseholder(this.getHouseHolder(l_intLineNo));

        //�@@�Q�|�R�T�j���ю�@@�Ζ���
        //�@@�@@�@@get���ю�@@�Ζ���()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.���ю�Ζ���ɃZ�b�g����B
        //�@@�@@�@@[get���ю�@@�Ζ���()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setHouseholderOffice(this.getHouseHolderOffice(l_intLineNo));

        //�@@�Q�|�R�U�j���ю�@@��E
        //�@@�@@�@@get���ю�@@��E()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.���ю��E�ɃZ�b�g����B
        //�@@�@@�@@[get���ю�@@��E()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setHouseholderPost(this.getHouseHolderPost(l_intLineNo));

        //�@@�Q�|�R�V�j�U����
        //�@@�@@�@@get�U����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �U�֋敪�ɃZ�b�g����B
        //�@@�@@�@@[get�U����()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setTransferDiv(this.getTransfer(l_intLineNo));

        //�@@�Q�|�R�W�j��s�R�[�h
        //�@@�@@�@@get��s�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� ��s�R�[�h�ɃZ�b�g����B
        //�@@�@@�@@[get��s�R�[�h()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setFinInstitutionCode(this.getFinInstitutionCode(l_intLineNo));

        //�@@�Q�|�R�X�j��s��
        //�@@�@@�@@get��s��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� ��s���ɃZ�b�g����B
        //�@@�@@�@@[get��s��()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setFinInstitutionName(this.getFinInstitutionName(l_intLineNo));

        //�@@�Q�|�S�O�j�x�X�R�[�h
        //�@@�@@�@@get�x�X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �x�X�R�[�h�ɃZ�b�g����B
        //�@@�@@�@@[get�x�X�R�[�h()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setFinBranchCode(this.getFinBranchCode(l_intLineNo));

        //�@@�Q�|�S�P�j�x�X��
        //�@@�@@�@@get�x�X��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �x�X���ɃZ�b�g����B
        //�@@�@@�@@[get�x�X��()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setFinBranchName(this.getFinBranchName(l_intLineNo));

        //�@@�Q�|�S�Q�j�a���敪
        //�@@�@@�@@get�a���敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �a���敪�ɃZ�b�g����B
        //�@@�@@�@@[get�a���敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setFinSaveDiv(this.getFinSaveDiv(l_intLineNo));

        //�@@�Q�|�S�R�j�����ԍ�
        //�@@�@@�@@get�����ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �����ԍ��ɃZ�b�g����B
        //�@@�@@�@@[get�����ԍ�()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setFinAccountNo(this.getFinAccountNo(l_intLineNo));

        //�@@�Q�|�S�S�j�ʒ��L��
        //�@@�@@�@@get�ʒ��L��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �ʒ��L���ɃZ�b�g����B
        //�@@�@@�@@[get�ʒ��L��()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setPostalSaveCode(this.getPostalSaveCode(l_intLineNo));

        //�@@�Q�|�S�T�j�ʒ��ԍ�
        //�@@�@@�@@get�ʒ��ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�U������Z�@@�֏�� �ʒ��ԍ��ɃZ�b�g����B
        //�@@�@@�@@[get�ʒ��ԍ�()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setPostalSaveNo(this.getPostalSaveNo(l_intLineNo));

        //�@@�Q�|�S�U�j�����o���i������������j
        //�@@�@@�@@get�����o���i������������j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����o�� ���������ɃZ�b�g����B
        //�@@�@@�@@[get�����o���i������������j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExperienceDivEquity(this.getExperienceEquityDiv(l_intLineNo));

        //�@@�Q�|�S�V�j�����o���i�����M�p����j
        //�@@�@@�@@get�����o���i�����M�p����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����o�� �M�p����ɃZ�b�g����B
        //�@@�@@�@@[get�����o���i�����M�p����j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExperienceDivMargin(this.getExperienceMarginDiv(l_intLineNo));

        //�@@�Q�|�S�W�j�����o���i���j
        //�@@�@@�@@get�����o���i���j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����o�� ���ɃZ�b�g����B
        //�@@�@@�@@[get�����o���i���j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExperienceDivBond(this.getExperienceBondDiv(l_intLineNo));

        //�@@�Q�|�S�X�j�����o���i�����w���I�v�V�����j
        //�@@�@@�@@get�����o���i�����w���I�v�V�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����o�� �]���ЍɃZ�b�g����B
        //�@@�@@�@@[get�����o���i�����w���I�v�V�����j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExperienceDivWt(this.getExperienceOptionsDiv(l_intLineNo));

        //�@@�Q�|�T�O�j�����o���i�����M���F�����j
        //�@@�@@�@@get�����o���i�����M���F�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����o�� �����M���i�����j�ɃZ�b�g����B
        //�@@�@@�@@[get�����o���i�����M���F�����j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExperienceDivFundSk(this.getExperienceFundSkDiv(l_intLineNo));

        //�@@�Q�|�T�P�j�����o���i�����M���F���Ѝj
        //�@@�@@�@@get�����o���i�����M���F���Ѝj()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����o�� �����M���i���Ѝj�ɃZ�b�g����B
        //�@@�@@�@@[get�����o���i�����M���F���Ѝj()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExperienceDivFundBd(this.getExperienceFundBdDiv(l_intLineNo));

        //�@@�Q�|�T�Q�j�����o���i�����w���敨�j
        //�@@�@@�@@get�����o���i�����w���敨�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����o�� �敨�E�I�v�V�����ɃZ�b�g����B
        //�@@�@@�@@[get�����o���i�����w���敨�j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExperienceDivFo(this.getExperienceFuturesDiv(l_intLineNo));

        //�@@�Q�|�T�R�j�����o���i�O���،��j
        //�@@�@@�@@get�����o���i�O���،��j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����o�� �O���،��ɃZ�b�g����B
        //�@@�@@�@@[get�����o���i�O���،��j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExperienceDivFEquity(this.getExperienceFEquityDiv(l_intLineNo));

        //�@@�Q�|�T�S�j�����o���i���̑��j
        //�@@�@@�@@get�����o���i���̑��j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����o�� ���̑��ɃZ�b�g����B
        //�@@�@@�@@[get�����o���i���̑��j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExperienceDivEtc(this.getExperienceEtcDiv(l_intLineNo));

        //�@@�Q�|�T�T�j��]��������̎�ށ@@�����i�����j
        //�@@�@@�@@get��]��������̎�ށ@@�����i�����j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����̂��邨��� ���������t���O�ɃZ�b�g����B
        //�@@�@@�@@[get��]��������̎�ށ@@�����i�����j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestEquityFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagEquity(l_insiderFlag);

        //�@@�Q�|�T�U�j��]��������̎�ށ@@�����i�M�p�j
        //�@@�@@�@@get��]��������̎�ށ@@�����i�M�p�j()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����̂��邨��� �M�p����t���O�ɃZ�b�g����B
        //�@@�@@�@@[get��]��������̎�ށ@@�����i�M�p�j()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestMarginFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagMargin(l_insiderFlag);

        //�@@�Q�|�T�V�j��]��������̎�ށ@@���敨
        //�@@�@@�@@get��]��������̎�ށ@@���敨()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����̂��邨��� ���t���O�ɃZ�b�g����B
        //�@@�@@�@@[get��]��������̎�ށ@@���敨()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestBondFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagBond(l_insiderFlag);

        //�@@�Q�|�T�W�j��]��������̎�ށ@@�����M��
        //�@@�@@�@@get��]��������̎�ށ@@�����M��()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����̂��邨��� �����M���t���O�ɃZ�b�g����B
        //�@@�@@�@@[get��]��������̎�ށ@@�����M��()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestFundFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagFund(l_insiderFlag);

        //�@@�Q�|�T�X�j��]��������̎�ށ@@�����w���敨
        //�@@�@@�@@get��]��������̎�ށ@@�����w���敨()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����̂��邨��� �敨�E�I�v�V�����t���O�ɃZ�b�g����B
        //�@@�@@�@@[get��]��������̎�ށ@@�����w���敨()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestFuturesFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagFo(l_insiderFlag);

        //�@@�Q�|�U�O�j��]��������̎�ށ@@�����w���I�v�V����
        //�@@�@@�@@get��]��������̎�ށ@@�����w���I�v�V����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����̂��邨��� �O���،��t���O�ɃZ�b�g����B
        //�@@�@@�@@[get��]��������̎�ށ@@�����w���I�v�V����()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestOptionsFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagFEquity(l_insiderFlag);

        //�@@�Q�|�U�P�j�N���敪
        //�@@�@@�@@get�N���敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�N���敪�ɃZ�b�g����B
        //�@@�@@�@@[get�N���敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setAnnualIncomeDiv(this.getAnnualIncomeDiv(l_intLineNo));

        //�@@�Q�|�U�Q�j���Z���Y�敪
        //�@@�@@�@@get���Z���Y�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.���Z���Y�敪�ɃZ�b�g����B
        //�@@�@@�@@[get���Z���Y�敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setAssetValueDiv(this.getAssetValueDiv(l_intLineNo));

        //�@@�Q�|�U�R�j��������敪����
        //�@@�@@�@@get��������敪����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.��������敪�ɃZ�b�g����B
        //�@@�@@�@@[get��������敪����()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setSpecialAcc(this.getSpecialAccEquity(l_intLineNo));

        //�@@�Q�|�U�S�j�����ғo�^�敪
        //�@@�@@�@@get�����ғo�^�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����ғo�^�t���O�ɃZ�b�g����B
        //�@@�@@�@@[get�����ғo�^�敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInsiderFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInsiderFlag(l_insiderFlag);

        //�@@�Q�|�U�T�j�����ғo�^����
        //�@@�@@�@@get�����ғo�^����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�����Җ������ɃZ�b�g����B
        //�@@�@@�@@[get�����ғo�^����()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setProductName(this.getProductName(l_intLineNo));

        //�@@�Q�|�U�U�j�Ǝ�敪
        //�@@�@@�@@get�Ǝ�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�P�j�ɃZ�b�g����B
        //�@@�@@�@@[get�Ǝ�敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExtItemDiv1(this.getTypeDiv(l_intLineNo));

        //�@@�Q�|�U�V�j�����̌`�ԋ敪
        //�@@�@@�@@get�����̌`�ԋ敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�Q�j�ɃZ�b�g����B
        //�@@�@@�@@[get�����̌`�ԋ敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExtItemDiv2(this.getIncomeDormDiv(l_intLineNo));

        //�@@�Q�|�U�W�j�����Ҋ֌W�敪
        //�@@�@@�@@get�����Ҋ֌W�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�R�j�ɃZ�b�g����B
        //�@@�@@�@@[get�����Ҋ֌W�敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExtItemDiv3(this.getInsiderRelationDiv(l_intLineNo));

        //�@@�Q�|�U�X�j�����ړI�敪
        //�@@�@@�@@get�����ړI�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�S�j�ɃZ�b�g����B
        //�@@�@@�@@[get�����ړI�敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExtItemDiv4(this.getInvestPurposeDiv(l_intLineNo));

        //�@@�Q�|�V�O�j������@@�敪
        //�@@�@@�@@get������@@�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�T�j�ɃZ�b�g����B
        //�@@�@@�@@[get������@@�敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExtItemDiv5(this.getAppliMotivatDiv(l_intLineNo));

        //�@@�Q�|�V�P�j�{�l�m�F���ދ敪
        //�@@�@@�@@get�{�l�m�F���ދ敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�@@�@@�@@�����J�݌����q�s.�e�Њg�����ځi�敪�U�j�ɃZ�b�g����B
        //�@@�@@�@@[get�{�l�m�F���ދ敪()�Ɏw�肷�����]
        //�@@�@@�@@�s�ԍ��F�@@�s�ԍ�
        l_expAccountOpenParams.setExtItemDiv6(this.getIDConfirmDocDiv(l_intLineNo));

        //�Q�|�V�Q�j�K�{���ڂɃf�t�H���g�l���Z�b�g
        //�|�����J�݌����q�s.���������t���O == 0�FDEFAULT
        l_expAccountOpenParams.setExAccountFlag(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����o���L�� ���������t���O == 0�FDEFAULT
        l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����o���L�� �M�p����t���O == 0�FDEFAULT
        l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����o���L�� ���t���O == 0�FDEFAULT
        l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����o���L�� �]���Ѝt���O == 0�FDEFAULT
        l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����o���L�� �����M���i�����j�t���O == 0�FDEFAULT
        l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����o���L�� �����M���i���Ѝj�t���O == 0�FDEFAULT
        l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����o���L�� �敨�E�I�v�V�����t���O == 0�FDEFAULT
        l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����o���L�� �O���،��t���O == 0�FDEFAULT
        l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����o���L�� ���̑��t���O == 0�FDEFAULT
        l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����̂��邨��� �����~�j�����t���O == 0�FDEFAULT
        l_expAccountOpenParams.setInterestFlagMinistock(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����̂��邨��� ���̑��t���O == 0�FDEFAULT
        l_expAccountOpenParams.setInterestFlagEtc(BooleanEnum.FALSE);
        //�|�����J�݌����q�s.�����p�敪 == 0�FDEFAULT
        l_expAccountOpenParams.setIdConfirmFlag(BooleanEnum.FALSE);

        // �R�j�@@�I�u�W�F�N�g����
        //  ���������s�I�u�W�F�N�g���w�肵�A�����J�݌����q�I�u�W�F�N�g�𐶐�����B
        //  ���������I�u�W�F�N�g��ԋp����B
    	WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
    		new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
    	log.exiting(STR_METHOD_NAME);

        return l_accOpenExpAccountOpen;
    }

    /**
     * (create�J�����w�b�_)<BR>
     * �ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����A<BR>
     * set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���R�[�h�ԍ����x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ʃR�[�h���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�ڋq�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���҃R�[�h�iSONAR�j���x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����敪���x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���͋敪���x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���������������x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����O�@@���i�����j���x��<BR>
     * �@@�J�����ԍ��F 8<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 9<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����O�@@���i�����j���x��<BR>
     * �@@�J�����ԍ��F 9<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 10<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����O�@@���i�t���K�i�j���x��<BR>
     * �@@�J�����ԍ��F 10<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 11<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����O�@@���i�t���K�i�j���x��<BR>
     * �@@�J�����ԍ��F 11<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 12<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ʃ��x��<BR>
     * �@@�J�����ԍ��F 12<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 13<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���N�����N�����x��<BR>
     * �@@�J�����ԍ��F 13<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 14<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���N�������x��<BR>
     * �@@�J�����ԍ��F 14<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 15<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���[���A�h���X���x��<BR>
     * �@@�J�����ԍ��F 15<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 16<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�X�֔ԍ����x��<BR>
     * �@@�J�����ԍ��F 16<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 17<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���P�i�����j���x��<BR>
     * �@@�J�����ԍ��F 17<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 18<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���Q�i�����j���x��<BR>
     * �@@�J�����ԍ��F 18<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 19<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���R�i�����j���x��<BR>
     * �@@�J�����ԍ��F 19<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 20<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���P�i�J�i�j���x��<BR>
     * �@@�J�����ԍ��F 20<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 21<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���Q�i�J�i�j���x��<BR>
     * �@@�J�����ԍ��F 21<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 22<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���R�i�J�i�j���x��<BR>
     * �@@�J�����ԍ��F 22<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 23<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�d�b�ԍ��i�s�O�ǔԁj���x��<BR>
     * �@@�J�����ԍ��F 23<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 24<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�d�b�ԍ��i�ǔԁj���x��<BR>
     * �@@�J�����ԍ��F 24<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 25<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�d�b�ԍ��i�ԍ��j���x��<BR>
     * �@@�J�����ԍ��F 25<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 26<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�g�єԍ��i�s�O�ǔԁj���x��<BR>
     * �@@�J�����ԍ��F 26<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 27<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�g�єԍ��i�ǔԁj���x��<BR>
     * �@@�J�����ԍ��F 27<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 28<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�g�єԍ��i�ԍ��j���x��<BR>
     * �@@�J�����ԍ��F 28<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 29<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�E�Ƌ敪���x��<BR>
     * �@@�J�����ԍ��F 29<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 30<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ��於�̃��x��<BR>
     * �@@�J�����ԍ��F 30<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 31<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ���X�֔ԍ����x��<BR>
     * �@@�J�����ԍ��F 31<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 32<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ���Z�����x��<BR>
     * �@@�J�����ԍ��F 32<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 33<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��P���x��<BR>
     * �@@�J�����ԍ��F 33<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 34<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��Q���x��<BR>
     * �@@�J�����ԍ��F 34<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 35<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��R���x��<BR>
     * �@@�J�����ԍ��F 35<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 36<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����������x��<BR>
     * �@@�J�����ԍ��F 36<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 37<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��E���x��<BR>
     * �@@�J�����ԍ��F 37<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 38<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ю�Ƃ̑������x��<BR>
     * �@@�J�����ԍ��F 38<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 39<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ю喼���x��<BR>
     * �@@�J�����ԍ��F 39<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 40<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ю�@@�Ζ��惉�x��<BR>
     * �@@�J�����ԍ��F 40<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 41<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ю�@@��E���x��<BR>
     * �@@�J�����ԍ��F 41<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 42<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�U���惉�x��<BR>
     * �@@�J�����ԍ��F 42<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 43<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��s�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 43<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 44<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��s�����x��<BR>
     * �@@�J�����ԍ��F 44<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 45<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�x�X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 45<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 46<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�x�X�����x��<BR>
     * �@@�J�����ԍ��F 46<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 47<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�a���敪���x��<BR>
     * �@@�J�����ԍ��F 47<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 48<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����ԍ����x��<BR>
     * �@@�J�����ԍ��F 48<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 49<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�ʒ��L�����x��<BR>
     * �@@�J�����ԍ��F 49<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 50<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�ʒ��ԍ����x��<BR>
     * �@@�J�����ԍ��F 50<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 51<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i������������j���x��<BR>
     * �@@�J�����ԍ��F 51<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 52<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�����M�p����j���x��<BR>
     * �@@�J�����ԍ��F 52<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 53<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i���j���x��<BR>
     * �@@�J�����ԍ��F 53<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 54<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�����w���I�v�V�����j���x��<BR>
     * �@@�J�����ԍ��F 54<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 55<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�����M���F�����j���x��<BR>
     * �@@�J�����ԍ��F 55<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 56<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�����M���F���Ѝj���x��<BR>
     * �@@�J�����ԍ��F 56<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 57<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�����w���敨�j���x��<BR>
     * �@@�J�����ԍ��F 57<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 58<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�O���،��j���x��<BR>
     * �@@�J�����ԍ��F 58<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 59<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i���̑��j���x��<BR>
     * �@@�J�����ԍ��F 59<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 60<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@�����i�����j���x��<BR>
     * �@@�J�����ԍ��F 60<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 61<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@�����i�M�p�j���x��<BR>
     * �@@�J�����ԍ��F 61<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 62<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@���敨���x��<BR>
     * �@@�J�����ԍ��F 62<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 63<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@�����M�����x��<BR>
     * �@@�J�����ԍ��F 63<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 64<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@�����w���敨���x��<BR>
     * �@@�J�����ԍ��F 64<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 65<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@�����w���I�v�V�������x��
     * <BR>
     * �@@�J�����ԍ��F 65<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 66<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�N���敪���x��<BR>
     * �@@�J�����ԍ��F 66<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 67<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���Z���Y�敪���x��<BR>
     * �@@�J�����ԍ��F 67<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 68<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��������敪�������x��<BR>
     * �@@�J�����ԍ��F 68<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 69<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����ғo�^�敪���x��<BR>
     * �@@�J�����ԍ��F 69<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 70<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����ғo�^�������x��<BR>
     * �@@�J�����ԍ��F 70<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 71<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ǝ�敪���x��<BR>
     * �@@�J�����ԍ��F 71<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 72<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����̌`�ԋ敪���x��<BR>
     * �@@�J�����ԍ��F 72<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 73<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����Ҋ֌W�敪���x��<BR>
     * �@@�J�����ԍ��F 73<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 74<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����ړI�敪���x��<BR>
     * �@@�J�����ԍ��F 74<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 75<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.������@@�敪���x��<BR>
     * �@@�J�����ԍ��F 75<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 76<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�{�l�m�F���ދ敪���x��<BR>
     * �@@�J�����ԍ��F 76<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * @@roseuid 472FD2E603BD
     */
    protected void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 77;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        int l_intIndex = 0;
        //index = 0
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����J�ݐ\��ULCSV.���R�[�h�ԍ����x��
        //�J�����ԍ��F 0
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            recordNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 1
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ʃR�[�h���x��
        //�J�����ԍ��F 1
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            requestNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 2
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����J�ݐ\��ULCSV.���X�R�[�h���x��
        //�J�����ԍ��F 2
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            branchCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 3
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����J�ݐ\��ULCSV.�ڋq�R�[�h���x��
        //�J�����ԍ��F 3
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 4
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����J�ݐ\��ULCSV.���҃R�[�h�iSONAR�j���x��
        //�J�����ԍ��F 4
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            sonarTraderCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 5
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����敪���x��
        //�J�����ԍ��F 5
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountTypeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 6
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����J�ݐ\��ULCSV.���͋敪���x��
        //�J�����ԍ��F 6
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            inputDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 7
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����J�ݐ\��ULCSV.���������������x��
        //�J�����ԍ��F 7
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            infoClaimDateLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 8
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����O�@@���i�����j���x��
        //�J�����ԍ��F 8
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountFamilyNameLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 9
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����O�@@���i�����j���x��
        //�J�����ԍ��F 9
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountNameLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //      �|�@@index = 10
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����O�@@���i�t���K�i�j���x��
        //     �@@�J�����ԍ��F 10
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountFamilyNameKanaLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 11
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����O�@@���i�t���K�i�j���x��
        //     �@@�J�����ԍ��F 11
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountNameKanaLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 12
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ʃ��x��
        //     �@@�J�����ԍ��F 12
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            sexLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 13
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���N�����N�����x��
        //     �@@�J�����ԍ��F 13
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            eraBornLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 14
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���N�������x��
        //     �@@�J�����ԍ��F 14
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            bornDateLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 15
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���[���A�h���X���x��
        //     �@@�J�����ԍ��F 15
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            mailAddressLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 16
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�X�֔ԍ����x��
        //     �@@�J�����ԍ��F 16
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            zipCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 17
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���P�i�����j���x��
        //     �@@�J�����ԍ��F 17
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            address1Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 18
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���Q�i�����j���x��
        //     �@@�J�����ԍ��F 18
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            address2Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 19
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���R�i�����j���x��
        //     �@@�J�����ԍ��F 19
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            address3Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 20
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���P�i�J�i�j���x��
        //     �@@�J�����ԍ��F 20
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            addressKana1Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 21
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���Q�i�J�i�j���x��
        //     �@@�J�����ԍ��F 21
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            addressKana2Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 22
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Z���R�i�J�i�j���x��
        //     �@@�J�����ԍ��F 22
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            addressKana3Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 23
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�d�b�ԍ��i�s�O�ǔԁj���x��
        //     �@@�J�����ԍ��F 23
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            telephoneAreaCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 24
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�d�b�ԍ��i�ǔԁj���x��
        //     �@@�J�����ԍ��F 24
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            telephoneExchangeNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 25
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�d�b�ԍ��i�ԍ��j���x��
        //     �@@�J�����ԍ��F 25
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            telephoneNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 26
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�g�єԍ��i�s�O�ǔԁj���x��
        //     �@@�J�����ԍ��F 26
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            mobileAreaCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 27
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�g�єԍ��i�ǔԁj���x��
        //     �@@�J�����ԍ��F 27
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            mobileExchangeNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 28
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�g�єԍ��i�ԍ��j���x��
        //     �@@�J�����ԍ��F 28
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            mobileNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 29
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�E�Ƌ敪���x��
        //     �@@�J�����ԍ��F 29
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            occupationDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 30
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ��於�̃��x��
        //     �@@�J�����ԍ��F 30
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 31
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ���X�֔ԍ����x��
        //     �@@�J�����ԍ��F 31
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeZipCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 32
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ���Z�����x��
        //     �@@�J�����ԍ��F 32
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeAddressLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 33
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��P���x��
        //     �@@�J�����ԍ��F 33
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeTelephone1Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 34
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��Q���x��
        //     �@@�J�����ԍ��F 34
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeTelephone2Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 35
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��R���x��
        //     �@@�J�����ԍ��F 35
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeTelephone3Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 36
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����������x��
        //     �@@�J�����ԍ��F 36
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            departmentLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 37
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��E���x��
        //     �@@�J�����ԍ��F 37
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            postLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 38
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ю�Ƃ̑������x��
        //     �@@�J�����ԍ��F 38
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            houseHolderFamilyRelationLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 39
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ю喼���x��
        //     �@@�J�����ԍ��F 39
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            houseHolderLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 40
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ю�@@�Ζ��惉�x��
        //     �@@�J�����ԍ��F 40
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            houseHolderOfficeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 41
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���ю�@@��E���x��
        //     �@@�J�����ԍ��F 41
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            houseHolderPostLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 42
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�U���惉�x��
        //     �@@�J�����ԍ��F 42
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            transferLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 43
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��s�R�[�h���x��
        //     �@@�J�����ԍ��F 43
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finInstitutionCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 44
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��s�����x��
        //     �@@�J�����ԍ��F 44
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finInstitutionNameLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 45
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�x�X�R�[�h���x��
        //     �@@�J�����ԍ��F 45
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finBranchCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 46
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�x�X�����x��
        //     �@@�J�����ԍ��F 46
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finBranchNameLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 47
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�a���敪���x��
        //     �@@�J�����ԍ��F 47
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finSaveDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 48
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����ԍ����x��
        //     �@@�J�����ԍ��F 48
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finAccountNoLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 49
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�ʒ��L�����x��
        //     �@@�J�����ԍ��F 49
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            postalSaveCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 50
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�ʒ��ԍ����x��
        //     �@@�J�����ԍ��F 50
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            postalSaveNoLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 51
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i������������j���x��
        //     �@@�J�����ԍ��F 51
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceEquityDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 52
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�����M�p����j���x��
        //     �@@�J�����ԍ��F 52
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceMarginDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 53
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i���j���x��
        //     �@@�J�����ԍ��F 53
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceBondDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 54
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�����w���I�v�V�����j���x��
        //     �@@�J�����ԍ��F 54
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceOptionsDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 55
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�����M���F�����j���x��
        //     �@@�J�����ԍ��F 55
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceFundSkDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 56
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�����M���F���Ѝj���x��
        //     �@@�J�����ԍ��F 56
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceFundBdDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 57
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�����w���敨�j���x��
        //     �@@�J�����ԍ��F 57
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceFuturesDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 58
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i�O���،��j���x��
        //     �@@�J�����ԍ��F 58
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceFEquityDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 59
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����o���i���̑��j���x��
        //     �@@�J�����ԍ��F 59
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceEtcDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 60
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@�����i�����j���x��
        //     �@@�J�����ԍ��F 60
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestEquityFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 61
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@�����i�M�p�j���x��
        //     �@@�J�����ԍ��F 61
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestMarginFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 62
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@���敨���x��
        //     �@@�J�����ԍ��F 62
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestBondFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 63
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@�����M�����x��
        //     �@@�J�����ԍ��F 63
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestFundFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 64
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@�����w���敨���x��
        //     �@@�J�����ԍ��F 64
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestFuturesFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 65
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��]��������̎�ށ@@�����w���I�v�V�������x��
        //     �@@�J�����ԍ��F 65
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestOptionsFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 66
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�N���敪���x��
        //     �@@�J�����ԍ��F 66
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            annualIncomeDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 67
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.���Z���Y�敪���x��
        //     �@@�J�����ԍ��F 67
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            assetValueDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 68
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.��������敪�������x��
        //     �@@�J�����ԍ��F 68
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            specialAccEquityLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 69
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����ғo�^�敪���x��
        //     �@@�J�����ԍ��F 69
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            insiderFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 70
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����ғo�^�������x��
        //     �@@�J�����ԍ��F 70
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            productNameLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 71
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�Ǝ�敪���x��
        //     �@@�J�����ԍ��F 71
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            typeDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 72
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����̌`�ԋ敪���x��
        //     �@@�J�����ԍ��F 72
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            incomeDormDivLable,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 73
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����Ҋ֌W�敪���x��
        //     �@@�J�����ԍ��F 73
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            insiderRelationDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 74
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�����ړI�敪���x��
        //     �@@�J�����ԍ��F 74
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            investPurposeDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 75
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.������@@�敪���x��
        //     �@@�J�����ԍ��F 75
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            appliMotivatDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     �|�@@index = 76
        //     �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //     �@@���ڃ��x���F�@@�����J�ݐ\��ULCSV.�{�l�m�F���ދ敪���x��
        //     �@@�J�����ԍ��F 76
        //     �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //     �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            idConfirmDocDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        this.setColumnHeader(l_models);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get���R�[�h�ԍ�)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̃��R�[�h�ԍ����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂă��R�[�h�ԍ����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���R�[�h�ԍ����x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 473BFD3100B3
     */
    public String getRecordNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getRecordNumber(int)";
    	log.entering(STR_METHOD_NAME);
    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���R�[�h�ԍ����x��)
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.recordNumberLabel);
    	//this.get���ڒl()�ɂă��R�[�h�ԍ����擾���ԋp����B
    	String l_strRecordNumber = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strRecordNumber;
    }

    /**
     * (get���ʃR�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̎��ʃR�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĎ��ʃR�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ʃR�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 473BFD3403BD
     */
    public String getRequestNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getRequestNumber(int)";
    	log.entering(STR_METHOD_NAME);
        //�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ʃR�[�h���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.requestNumberLabel);
    	//this.get���ڒl()�ɂĎ��ʃR�[�h���擾���ԋp����B
    	String l_strRequestNumber = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strRequestNumber;
    }

    /**
     * (get���X�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���X�R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF52F0124
     */
    public String getBranchCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getBranchCode(int)";
    	log.entering(STR_METHOD_NAME);
        //�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���X�R�[�h���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.branchCodeLabel);
    	//this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B
    	String l_strBranchCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strBranchCode;
    }

    /**
     * (get�ڋq�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�ڋq�R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF5D80183
     */
    public String getAccountCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountCode(int)";
    	log.entering(STR_METHOD_NAME);
        //�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�ڋq�R�[�h���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountCodeLabel);
    	//this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B
    	String l_strAccountCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountCode;
    }

    /**
     * (get���҃R�[�h�iSONAR�j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̈��҃R�[�h�iSONAR�j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĈ��҃R�[�h�iSONAR�j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���҃R�[�h�iSONAR�j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF62C0092
     */
    public String getSonarTraderCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getSonarTraderCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���҃R�[�h�iSONAR�j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.sonarTraderCodeLabel);
    	//this.get���ڒl()�ɂĈ��҃R�[�h�iSONAR�j���擾���ԋp����B
    	String l_strSonarTraderCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strSonarTraderCode;

     }

    /**
     * (get�����敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌����敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂČ����敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF62D012E
     */
    public String getAccountDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountTypeLabel);
    	//this.get���ڒl()�ɂČ����敪���擾���ԋp����B
    	String l_strAccountDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountDiv;

    }

    /**
     * (get���͋敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓��͋敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē��͋敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���͋敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF62E014D
     */
    public String getOrderDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOrderDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���͋敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.inputDivLabel);
    	//this.get���ڒl()�ɂē��͋敪���擾���ԋp����B
    	String l_strOrderDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOrderDiv;

    }

    /**
     * (get������������)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̎��������������擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĎ��������������擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���������������x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF62F010F
     */
    public String getInfoClaimDate(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInfoClaimDate(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���������������x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.infoClaimDateLabel);
    	//this.get���ڒl()�ɂĎ��������������擾���ԋp����B
    	String l_strInfoClaimDate = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInfoClaimDate;

    }

    /**
     * (get�����O�@@���i�����j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̂����O�@@���i�����j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĂ����O�@@���i�����j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����O�@@���i�����j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF63001DA
     */
    public String getAccountFamilyName(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountFamilyName(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����O�@@���i�����j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountFamilyNameLabel);
    	//this.get���ڒl()�ɂĂ����O�@@���i�����j���擾���ԋp����B
    	String l_strAccountFamilyName = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountFamilyName;

    }

    /**
     * (get�����O�@@���i�����j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̂����O�@@���i�����j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĂ����O�@@���i�����j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����O�@@���i�����j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF63500FE
     */
    public String getAccountName(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountName(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����O�@@���i�����j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountNameLabel);
    	//this.get���ڒl()�ɂĂ����O�@@���i�����j���擾���ԋp����B
    	String l_strAccountName = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountName;

    }

    /**
     * (get�����O�@@���i�t���K�i�j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̂����O�@@���i�t���K�i�j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĂ����O�@@���i�t���K�i�j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����O�@@���i�t���K�i�j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF635010E
     */
    public String getAccountFamilyNameKana(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountFamilyNameKana(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����O�@@���i�t���K�i�j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountFamilyNameKanaLabel);
    	//this.get���ڒl()�ɂĂ����O�@@���i�t���K�i�j���擾���ԋp����B
    	String l_strAccountFamilyNameKana = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountFamilyNameKana;

    }

    /**
     * (get�����O�@@���i�t���K�i�j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̂����O�@@���i�t���K�i�j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĂ����O�@@���i�t���K�i�j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����O�@@���i�t���K�i�j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF635011E
     */
    public String getAccountNameKana(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountNameKana(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����O�@@���i�t���K�i�j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountNameKanaLabel);
    	//this.get���ڒl()�ɂĂ����O�@@���i�t���K�i�j���擾���ԋp����B
    	String l_strAccountNameKana = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountNameKana;

    }

    /**
     * (get����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐��ʂ��擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĐ��ʂ��擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ʃ��x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6350120
     */
    public String getSex(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getSex(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ʃ��x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.sexLabel);
    	//this.get���ڒl()�ɂĐ��ʂ��擾���ԋp����B
    	String l_strSex = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strSex;

    }

    /**
     * (get���N�����N��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐��N�����N�����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĐ��N�����N�����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���N�����N�����x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF635012D
     */
    public String getEraBorn(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getEraBorn(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���N�����N�����x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.eraBornLabel);
    	//this.get���ڒl()�ɂĐ��N�����N�����擾���ԋp����B
    	String l_strEraBorn = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strEraBorn;

    }

    /**
     * (get���N����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐��N�������擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĐ��N�������擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���N�������x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6360302
     */
    public String getBornDate(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getBornDate(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���N�������x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.bornDateLabel);
    	//this.get���ڒl()�ɂĐ��N�������擾���ԋp����B
    	String l_strBornDate = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strBornDate;

    }

    /**
     * (get���[���A�h���X)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̃��[���A�h���X���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂă��[���A�h���X���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���[���A�h���X���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6360312
     */
    public String getMailAddress(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getMailAddress(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���[���A�h���X���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.mailAddressLabel);
    	//this.get���ڒl()�ɂă��[���A�h���X���擾���ԋp����B
    	String l_strMailAddress = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strMailAddress;

    }

    /**
     * (get�X�֔ԍ�)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̗X�֔ԍ����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂėX�֔ԍ����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�X�֔ԍ����x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6360321
     */
    public String getZipCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getZipCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�X�֔ԍ����x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.zipCodeLabel);
    	//this.get���ڒl()�ɂėX�֔ԍ����擾���ԋp����B
    	String l_strZipCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strZipCode;

    }

    /**
     * (get�Z���P�i�����j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏Z���P�i�����j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂďZ���P�i�����j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���P�i�����j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6360323
     */
    public String getAddress1(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddress1(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���P�i�����j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.address1Label);
    	//this.get���ڒl()�ɂďZ���P�i�����j���擾���ԋp����B
    	String l_strAddress1 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddress1;

    }

    /**
     * (get�Z���Q�i�����j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏Z���Q�i�����j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂďZ���Q�i�����j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���Q�i�����j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6360331
     */
    public String getAddress2(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddress2(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���Q�i�����j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.address2Label);
    	//this.get���ڒl()�ɂďZ���Q�i�����j���擾���ԋp����B
    	String l_strAddress2 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddress2;

    }

    /**
     * (get�Z���R�i�����j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏Z���R�i�����j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂďZ���R�i�����j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���R�i�����j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6400013
     */
    public String getAddress3(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddress3(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���R�i�����j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.address3Label);
    	//this.get���ڒl()�ɂďZ���R�i�����j���擾���ԋp����B
    	String l_strAddress3 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddress3;

    }

    /**
     * (get�Z���P�i�J�i�j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏Z���P�i�J�i�j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂďZ���P�i�J�i�j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���P�i�J�i�j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6400023
     */
    public String getAddressKana1(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddressKana1(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���P�i�J�i�j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.addressKana1Label);
    	//this.get���ڒl()�ɂďZ���P�i�J�i�j���擾���ԋp����B
    	String l_strAddressKana1 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddressKana1;

    }

    /**
     * (get�Z���Q�i�J�i�j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏Z���Q�i�J�i�j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂďZ���Q�i�J�i�j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���Q�i�J�i�j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6400025
     */
    public String getAddressKana2(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddressKana2(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���Q�i�J�i�j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.addressKana2Label);
    	//this.get���ڒl()�ɂďZ���Q�i�J�i�j���擾���ԋp����B
    	String l_strAddressKana2 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddressKana2;

    }

    /**
     * (get�Z���R�i�J�i�j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏Z���R�i�J�i�j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂďZ���R�i�J�i�j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���R�i�J�i�j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6400032
     */
    public String getAddressKana3(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddressKana3(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Z���R�i�J�i�j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.addressKana3Label);
    	//this.get���ڒl()�ɂďZ���R�i�J�i�j���擾���ԋp����B
    	String l_strAddressKana3 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddressKana3;

    }

    /**
     * (get�d�b�ԍ��i�s�O�ǔԁj)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓d�b�ԍ��i�s�O�ǔԁj���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂēd�b�ԍ��i�s�O�ǔԁj���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�d�b�ԍ��i�s�O�ǔԁj���x��)�̖߂�l�l�B <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6400042
     */
    public String getTelephoneAreaCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getTelephoneAreaCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�d�b�ԍ��i�s�O�ǔԁj���x��)�̖߂�l�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.telephoneAreaCodeLabel);
    	//this.get���ڒl()�ɂēd�b�ԍ��i�s�O�ǔԁj���擾���ԋp����B
    	String l_strTelephoneAreaCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strTelephoneAreaCode;

    }

    /**
     * (get�d�b�ԍ��i�ǔԁj)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓d�b�ԍ��i�ǔԁj���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂēd�b�ԍ��i�ǔԁj���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�d�b�ԍ��i�ǔԁj���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6400052
     */
    public String getTelephoneExchangeNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getTelephoneExchangeNumber(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�d�b�ԍ��i�ǔԁj���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.telephoneExchangeNumberLabel);
    	//this.get���ڒl()�ɂēd�b�ԍ��i�ǔԁj���擾���ԋp����B
    	String l_strTelephoneExchangeNumber = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strTelephoneExchangeNumber;

    }

    /**
     * (get�d�b�ԍ��i�ԍ��j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓d�b�ԍ��i�ԍ��j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂēd�b�ԍ��i�ԍ��j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�d�b�ԍ��i�ԍ��j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6400061
     */
    public String getTelephoneNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getTelephoneNumber(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�d�b�ԍ��i�ԍ��j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.telephoneNumberLabel);
    	//this.get���ڒl()�ɂēd�b�ԍ��i�ԍ��j���擾���ԋp����B
    	String l_strTelephoneNumber = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strTelephoneNumber;

    }

    /**
     * (get�g�єԍ��i�s�O�ǔԁj)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌g�єԍ��i�s�O�ǔԁj���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂČg�єԍ��i�s�O�ǔԁj���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�g�єԍ��i�s�O�ǔԁj���x��)�̖߂�l�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6400071
     */
    public String getMobileAreaCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getMobileAreaCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�g�єԍ��i�s�O�ǔԁj���x��)�̖߂�l�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.mobileAreaCodeLabel);
    	//this.get���ڒl()�ɂČg�єԍ��i�s�O�ǔԁj���擾���ԋp����B
    	String l_strMobileAreaCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strMobileAreaCode;

    }

    /**
     * (get�g�єԍ��i�ǔԁj)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌g�єԍ��i�ǔԁj���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂČg�єԍ��i�ǔԁj���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�g�єԍ��i�ǔԁj���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6400080
     */
    public String getMobileExchangeNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getMobileExchangeNumber(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�g�єԍ��i�ǔԁj���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.mobileExchangeNumberLabel);
    	//this.get���ڒl()�ɂČg�єԍ��i�ǔԁj���擾���ԋp����B
    	String l_strMobileExchangeNumbe = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strMobileExchangeNumbe;

    }

    /**
     * (get�g�єԍ��i�ԍ��j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌g�єԍ��i�ԍ��j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂČg�єԍ��i�ԍ��j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�g�єԍ��i�ԍ��j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6400082
     */
    public String getMobileNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getMobileNumber(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�g�єԍ��i�ԍ��j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.mobileNumberLabel);
    	//this.get���ڒl()�ɂČg�єԍ��i�ԍ��j���擾���ԋp����B
    	String l_strMobileNumber = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strMobileNumber;

    }

    /**
     * (get�E�Ƌ敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐E�Ƌ敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĐE�Ƌ敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�E�Ƌ敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6400090
     */
    public String getOccupationDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOccupationDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�E�Ƌ敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.occupationDivLabel);
    	//this.get���ڒl()�ɂĐE�Ƌ敪���擾���ԋp����B
    	String l_strOccupationDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOccupationDiv;

    }

    /**
     * (get�Ζ��於��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋Ζ��於�̂��擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂċΖ��於�̂��擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ��於�̃��x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64000A0
     */
    public String getOffice(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOffice(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ��於�̃��x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeLabel);
    	//this.get���ڒl()�ɂċΖ��於�̂��擾���ԋp����B
    	String l_strOffice = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOffice;

    }

    /**
     * (get�Ζ���X�֔ԍ�)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋Ζ���X�֔ԍ����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂċΖ���X�֔ԍ����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ���X�֔ԍ����x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64000AF
     */
    public String getOfficeZipCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOfficeZipCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ���X�֔ԍ����x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeZipCodeLabel);
    	//this.get���ڒl()�ɂċΖ���X�֔ԍ����擾���ԋp����B
    	String l_strOfficeZipCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOfficeZipCode;

    }

    /**
     * (get�Ζ���Z��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋Ζ���Z�����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂċΖ���Z�����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ���Z�����x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64000BF
     */
    public String getOfficeAddress(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOfficeAddress(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ���Z�����x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeAddressLabel);
    	//this.get���ڒl()�ɂċΖ���Z�����擾���ԋp����B
    	String l_strOfficeAddress = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOfficeAddress;

    }

    /**
     * (get�Ζ���d�b�ԍ��P)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋Ζ���d�b�ԍ��P���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂċΖ���d�b�ԍ��P���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��P���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64101B9
     */
    public String getOfficeTelephone1(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOfficeTelephone1(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��P���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeTelephone1Label);
    	//this.get���ڒl()�ɂċΖ���d�b�ԍ��P���擾���ԋp����B
    	String l_strOfficeTelephone1 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOfficeTelephone1;

    }

    /**
     * (get�Ζ���d�b�ԍ��Q)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋Ζ���d�b�ԍ��Q���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂċΖ���d�b�ԍ��Q���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��Q���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64101C9
     */
    public String getOfficeTelephone2(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOfficeTelephone2(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��Q���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeTelephone2Label);
    	//this.get���ڒl()�ɂċΖ���d�b�ԍ��Q���擾���ԋp����B
    	String l_strOfficeTelephone2 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOfficeTelephone2;

    }

    /**
     * (get�Ζ���d�b�ԍ��R)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋Ζ���d�b�ԍ��R���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂċΖ���d�b�ԍ��R���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��R���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64101E8
     */
    public String getOfficeTelephone3(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOfficeTelephone3(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ζ���d�b�ԍ��R���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeTelephone3Label);
    	//this.get���ڒl()�ɂċΖ���d�b�ԍ��R���擾���ԋp����B
    	String l_strOfficeTelephone3 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOfficeTelephone3;

    }

    /**
     * (get��������)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏����������擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂď����������擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����������x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64101EA
     */
    public String getDepartment(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getDepartment(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����������x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.departmentLabel);
    	//this.get���ڒl()�ɂď����������擾���ԋp����B
    	String l_strDepartment = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strDepartment;

    }

    /**
     * (get��E)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̖�E���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĖ�E���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��E���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64101F7
     */
    public String getPost(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getPost(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��E���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.postLabel);
    	//this.get���ڒl()�ɂĖ�E���擾���ԋp����B
    	String l_strPost = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strPost;

    }

    /**
     * (get���ю�Ƃ̑���)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐��ю�Ƃ̑������擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĐ��ю�Ƃ̑������擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ю�Ƃ̑������x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6410207
     */
    public String getHouseHolderFamilyRelation(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getHouseHolderFamilyRelation(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ю�Ƃ̑������x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.houseHolderFamilyRelationLabel);
    	//this.get���ڒl()�ɂĐ��ю�Ƃ̑������擾���ԋp����B
    	String l_strHouseHolderFamilyRelationship = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strHouseHolderFamilyRelationship;

    }

    /**
     * (get���ю喼)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐��ю喼���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĐ��ю喼���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ю喼���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6410217
     */
    public String getHouseHolder(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getHouseHolder(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ю喼���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.houseHolderLabel);
    	//this.get���ڒl()�ɂĐ��ю�Ƃ̑������擾���ԋp����B
    	String l_strHouseHolder = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strHouseHolder;

    }

    /**
     * (get���ю�@@�Ζ���)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐��ю�@@�Ζ�����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĐ��ю�@@�Ζ�����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ю�@@�Ζ��惉�x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6410226
     */
    public String getHouseHolderOffice(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getHouseHolderOffice(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ю�@@�Ζ��惉�x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.houseHolderOfficeLabel);
    	//this.get���ڒl()�ɂĐ��ю�@@�Ζ�����擾���ԋp����B
    	String l_strHouseHolderOffice = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strHouseHolderOffice;

    }

    /**
     * (get���ю�@@��E)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐��ю�@@��E���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĐ��ю�@@��E���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ю�@@��E���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6410236
     */
    public String getHouseHolderPost(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getHouseHolderPost(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���ю�@@��E���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.houseHolderPostLabel);
    	//this.get���ڒl()�ɂĐ��ю�@@��E���擾���ԋp����B
    	String l_strHouseHolderPost = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strHouseHolderPost;

    }

    /**
     * (get�U����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̐U������擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĐU������擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�U���惉�x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6410245
     */
    public String getTransfer(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getTransfer(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�U���惉�x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.transferLabel);
    	//this.get���ڒl()�ɂĐU������擾���ԋp����B
    	String l_strTransfer = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strTransfer;

    }

    /**
     * (get��s�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋�s�R�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂċ�s�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��s�R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6410255
     */
    public String getFinInstitutionCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinInstitutionCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��s�R�[�h���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finInstitutionCodeLabel);
    	//this.get���ڒl()�ɂċ�s�R�[�h���擾���ԋp����B
    	String l_strFinInstitutionCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strFinInstitutionCode;

    }

    /**
     * (get��s��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋�s�����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂċ�s�����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��s�����x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6410257
     */
    public String getFinInstitutionName(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinInstitutionName(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��s�����x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finInstitutionNameLabel);
    	//this.get���ڒl()�ɂċ�s�����擾���ԋp����B
    	String l_strFinInstitutionName = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strFinInstitutionName;

    }

    /**
     * (get�x�X�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̎x�X�R�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĎx�X�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�x�X�R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6410265
     */
    public String getFinBranchCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinBranchCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�x�X�R�[�h���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finBranchCodeLabel);
    	//this.get���ڒl()�ɂĎx�X�R�[�h���擾���ԋp����B
    	String l_strFinBranchCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strFinBranchCode;

    }

    /**
     * (get�x�X��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̎x�X�����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĎx�X�����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�x�X�����x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF6410274
     */
    public String getFinBranchName(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinBranchName(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�x�X�����x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finBranchNameLabel);
    	//this.get���ڒl()�ɂĎx�X�����擾���ԋp����B
    	String l_strFinBranchName = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strFinBranchName;

    }

    /**
     * (get�a���敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̗a���敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂėa���敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�a���敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B015A
     */
    public String getFinSaveDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinSaveDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�a���敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finSaveDivLabel);
    	//this.get���ڒl()�ɂėa���敪���擾���ԋp����B
    	String l_strFinSaveDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strFinSaveDiv;

    }

    /**
     * (get�����ԍ�)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌����ԍ����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂČ����ԍ����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����ԍ����x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B016A
     */
    public String getFinAccountNo(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinAccountNo(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����ԍ����x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finAccountNoLabel);
    	//this.get���ڒl()�ɂČ����ԍ����擾���ԋp����B
    	String l_strtFinAccountNo = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strtFinAccountNo;

    }

    /**
     * (get�ʒ��L��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̒ʒ��L�����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĒʒ��L�����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�ʒ��L�����x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B017A
     */
    public String getPostalSaveCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getPostalSaveCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�ʒ��L�����x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.postalSaveCodeLabel);
    	//this.get���ڒl()�ɂĒʒ��L�����擾���ԋp����B
    	String l_strPostalSaveCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strPostalSaveCode;

    }

    /**
     * (get�ʒ��ԍ�)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̒ʒ��ԍ����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĒʒ��ԍ����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�ʒ��ԍ����x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B0189
     */
    public String getPostalSaveNo(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getPostalSaveNo(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�ʒ��ԍ����x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.postalSaveNoLabel);
    	//this.get���ڒl()�ɂĒʒ��ԍ����擾���ԋp����B
    	String l_strPostalSaveNo = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strPostalSaveNo;

    }

    /**
     * (get�����o���i������������j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����o���i������������j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����o���i������������j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i������������j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B0199
     */
    public String getExperienceEquityDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceEquityDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i������������j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceEquityDivLabel);
    	//this.get���ڒl()�ɂē����o���i������������j���擾���ԋp����B
    	String l_strExperienceEquityDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceEquityDiv;

    }

    /**
     * (get�����o���i�����M�p����j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����o���i�����M�p����j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����o���i�����M�p����j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�����M�p����j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B01A8
     */
    public String getExperienceMarginDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceMarginDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�����M�p����j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceMarginDivLabel);
    	//this.get���ڒl()�ɂē����o���i�����M�p����j���擾���ԋp����B
    	String l_strExperienceMarginDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceMarginDiv;

    }

    /**
     * (get�����o���i���j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����o���i���j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����o���i���j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i���j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B01B8
     */
    public String getExperienceBondDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceBondDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i���j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceBondDivLabel);
    	//this.get���ڒl()�ɂē����o���i���j���擾���ԋp����B
    	String l_strExperienceBondDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceBondDiv;

    }

    /**
     * (get�����o���i�����w���I�v�V�����j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����o���i�����w���I�v�V�����j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����o���i�����w���I�v�V�����j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�����w���I�v�V�����j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B01C8
     */
    public String getExperienceOptionsDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceOptionsDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�����w���I�v�V�����j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceOptionsDivLabel);
    	//this.get���ڒl()�ɂē����o���i�����w���I�v�V�����j���擾���ԋp����B
    	String l_strExperienceOptionsDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceOptionsDiv;

    }

    /**
     * (get�����o���i�����M���F�����j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����o���i�����M���F�����j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����o���i�����M���F�����j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�����M���F�����j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B01CA
     */
    public String getExperienceFundSkDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceFundSkDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�����M���F�����j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceFundSkDivLabel);
    	//this.get���ڒl()�ɂē����o���i�����M���F�����j���擾���ԋp����B
    	String l_strExperienceFundSkDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceFundSkDiv;

    }

    /**
     * (get�����o���i�����M���F���Ѝj)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����o���i�����M���F���Ѝj���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����o���i�����M���F���Ѝj���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�����M���F���Ѝj���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B01D7
     */
    public String getExperienceFundBdDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceFundBdDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�����M���F���Ѝj���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceFundBdDivLabel);
    	//this.get���ڒl()�ɂē����o���i�����M���F���Ѝj���擾���ԋp����B
    	String l_strExperienceFundBdDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceFundBdDiv;

    }

    /**
     * (get�����o���i�����w���敨�j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����o���i�����w���敨�j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����o���i�����w���敨�j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�����w���敨�j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B01E7
     */
    public String getExperienceFuturesDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceFuturesDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�����w���敨�j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceFuturesDivLabel);
    	//this.get���ڒl()�ɂē����o���i�����w���敨�j���擾���ԋp����B
    	String l_strExperienceFuturesDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceFuturesDiv;

    }

    /**
     * (get�����o���i�O���،��j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����o���i�O���،��j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����o���i�O���،��j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�O���،��j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B01F7
     */
    public String getExperienceFEquityDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceFEquityDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i�O���،��j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceFEquityDivLabel);
    	//this.get���ڒl()�ɂē����o���i�O���،��j���擾���ԋp����B
    	String l_strExperienceFEquityDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceFEquityDiv;

    }

    /**
     * (get�����o���i���̑��j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����o���i���̑��j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����o���i���̑��j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i���̑��j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B0206
     */
    public String getExperienceEtcDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceEtcDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����o���i���̑��j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceEtcDivLabel);
    	//this.get���ڒl()�ɂē����o���i���̑��j���擾���ԋp����B
    	String l_strExperienceEtcDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceEtcDiv;

    }

    /**
     * (get��]��������̎�ށ@@�����i�����j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̊�]��������̎�ށ@@�����i�����j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĊ�]��������̎�ށ@@�����i�����j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ފ����i�����j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64B0216
     */
    public String getInterestEquityFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestEquityFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ފ����i�����j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestEquityFlagLabel);
    	//this.get���ڒl()�ɂĊ�]��������̎�ށ@@�����i�����j���擾���ԋp����B
    	String l_strInterestEquityFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestEquityFlag;

    }

    /**
     * (get��]��������̎�ށ@@�����i�M�p�j)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̊�]��������̎�ށ@@�����i�M�p�j���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĊ�]��������̎�ށ@@�����i�M�p�j���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ފ����i�M�p�j���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C01E7
     */
    public String getInterestMarginFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestMarginFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ފ����i�M�p�j���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestMarginFlagLabel);
    	//this.get���ڒl()�ɂĊ�]��������̎�ށ@@�����i�M�p�j���擾���ԋp����B
    	String l_strInterestMarginFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestMarginFlag;

    }

    /**
     * (get��]��������̎�ށ@@���敨)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̊�]��������̎�ށ@@���敨���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĊ�]��������̎�ށ@@���敨���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ލ��敨���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C01F6
     */
    public String getInterestBondFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestBondFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ލ��敨���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestBondFlagLabel);
    	//this.get���ڒl()�ɂĊ�]��������̎�ށ@@���敨���擾���ԋp����B
    	String l_strInterestBondFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestBondFlag;

    }

    /**
     * (get��]��������̎�ށ@@�����M��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̊�]��������̎�ށ@@�����M�����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĊ�]��������̎�ށ@@�����M�����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ޓ����M�����x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C0206
     */
    public String getInterestFundFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestFundFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ޓ����M�����x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestFundFlagLabel);
    	//this.get���ڒl()�ɂĊ�]��������̎�ށ@@�����M�����擾���ԋp����B
    	String l_strInterestFundFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestFundFlag;

    }

    /**
     * (get��]��������̎�ށ@@�����w���敨)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̊�]��������̎�ށ@@�����w���敨���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĊ�]��������̎�ށ@@�����w���敨���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ފ����w���敨���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C0216
     */
    public String getInterestFuturesFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestFuturesFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ފ����w���敨���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestFuturesFlagLabel);
    	//this.get���ڒl()�ɂĊ�]��������̎�ށ@@�����w���敨���擾���ԋp����B
    	String l_strInterestFuturesFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestFuturesFlag;

    }

    /**
     * (get��]��������̎�ށ@@�����w���I�v�V����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̊�]��������̎�ށ@@�����w���I�v�V�������擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĊ�]��������̎�ށ@@�����w���I�v�V�������擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ފ����w���I�v�V�������x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C0218
     */
    public String getInterestOptionsFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestOptionsFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��]��������̎�ފ����w���I�v�V�������x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestOptionsFlagLabel);
    	//this.get���ڒl()�ɂĊ�]��������̎�ށ@@�����w���I�v�V�������擾���ԋp����B
    	String l_strInterestOptionsFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestOptionsFlag;

    }

    /**
     * (get�N���敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̔N���敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĔN���敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�N���敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C0225
     */
    public String getAnnualIncomeDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAnnualIncomeDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�N���敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.annualIncomeDivLabel);
    	//this.get���ڒl()�ɂĔN���敪���擾���ԋp����B
    	String l_strAnnualIncomeDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAnnualIncomeDiv;

    }

    /**
     * (get���Z���Y�敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋��Z���Y�敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂċ��Z���Y�敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���Z���Y�敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C0235
     */
    public String getAssetValueDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAssetValueDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.���Z���Y�敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.assetValueDivLabel);
    	//this.get���ڒl()�ɂċ��Z���Y�敪���擾���ԋp����B
    	String l_strAssetValueDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAssetValueDiv;

    }

    /**
     * (get��������敪����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓�������敪�������擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē�������敪�������擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��������敪�������x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C0245
     */
    public String getSpecialAccEquity(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getSpecialAccEquity(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.��������敪�������x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.specialAccEquityLabel);
    	//this.get���ڒl()�ɂē�������敪�������擾���ԋp����B
    	String l_strSpecialAcc = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strSpecialAcc;

    }

    /**
     * (get�����ғo�^�敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����ғo�^�敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����ғo�^�敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����ғo�^�敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C0254
     */
    public String getInsiderFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInsiderFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����ғo�^�敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.insiderFlagLabel);
    	//this.get���ڒl()�ɂē����ғo�^�敪���擾���ԋp����B
    	String l_strInsiderFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInsiderFlag;

    }

    /**
     * (get�����ғo�^����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����ғo�^�������擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����ғo�^�������擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����ғo�^�������x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C0264
     */
    public String getProductName(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getProductName(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����ғo�^�������x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.productNameLabel);
    	//this.get���ڒl()�ɂē����ғo�^�������擾���ԋp����B
    	String l_strProductName = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strProductName;

    }

    /**
     * (get�Ǝ�敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋Ǝ�敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂċƎ�敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ǝ�敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C0273
     */
    public String getTypeDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getTypeDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�Ǝ�敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.typeDivLabel);
    	//this.get���ڒl()�ɂċƎ�敪���擾���ԋp����B
    	String l_strTypeDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strTypeDiv;

    }

    /**
     * (get�����̌`�ԋ敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̎����̌`�ԋ敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĎ����̌`�ԋ敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����̌`�ԋ敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C0283
     */
    public String getIncomeDormDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getIncomeDormDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����̌`�ԋ敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.incomeDormDivLable);
    	//this.get���ڒl()�ɂĎ����̌`�ԋ敪���擾���ԋp����B
    	String l_strIncomeDormDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strIncomeDormDiv;

    }

    /**
     * (get�����Ҋ֌W�敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����Ҋ֌W�敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����Ҋ֌W�敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����Ҋ֌W�敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C0293
     */
    public String getInsiderRelationDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInsiderRelationDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����Ҋ֌W�敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.insiderRelationDivLabel);
    	//this.get���ڒl()�ɂē����Ҋ֌W�敪���擾���ԋp����B
    	String l_strInsiderRelationDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInsiderRelationDiv;

    }

    /**
     * (get�����ړI�敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓����ړI�敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē����ړI�敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����ړI�敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64C02A2
     */
    public String getInvestPurposeDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInvestPurposeDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�����ړI�敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.investPurposeDivLabel);
    	//this.get���ڒl()�ɂē����ړI�敪���擾���ԋp����B
    	String l_strInvestPurposeDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInvestPurposeDiv;

    }

    /**
     * (get������@@�敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̎�����@@�敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĎ�����@@�敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.������@@�敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64D015A
     */
    public String getAppliMotivatDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAppliMotivatDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.������@@�敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.appliMotivatDivLabel);
    	//this.get���ڒl()�ɂĎ�����@@�敪���擾���ԋp����B
    	String l_strAppliMotivatDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAppliMotivatDiv;

    }

    /**
     * (get�{�l�m�F���ދ敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̖{�l�m�F���ދ敪���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĖ{�l�m�F���ދ敪���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�{�l�m�F���ދ敪���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 472FF64D016A
     */
    public String getIDConfirmDocDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getIDConfirmDocDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//�J�����F�@@get�J�������f��(�����J�ݐ\��ULCSV.�{�l�m�F���ދ敪���x��)�̖߂�l�B
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.idConfirmDocDivLabel);
    	//this.get���ڒl()�ɂĖ{�l�m�F���ދ敪���擾���ԋp����B
    	String l_strIDConfirmDocDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strIDConfirmDocDiv;

    }
}
@
