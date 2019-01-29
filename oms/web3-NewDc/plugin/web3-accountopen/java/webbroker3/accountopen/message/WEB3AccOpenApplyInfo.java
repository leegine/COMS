head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenApplyInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐ\�����(WEB3AccOpenApplyInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
                 : 2006/07/13 ���� (���u) �d�l�ύX ���f��078
                 : 2006/08/14 �ęԍg (���u) �d�l�ύX ���f��087�A090
Revesion History : 2009/08/10 �����F(���u) �d�l�ύX ���f��162�A168
*/

package webbroker3.accountopen.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�����J�ݐ\�����)<BR>
 * �����J�ݐ\�����<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenApplyInfo extends Message 
{
    
    /**
     * (����������)<BR>
     * ����������<BR>
     */
    public Date infoClaimDate;
    
    /**
     * (�X�V��)<BR>
     * �X�V��<BR>
     */
    public Date updateDate;
    
    /**
     * (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     */
    public String updaterCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     */
    public String requestNumber;
    
    /**
     * (���͋敪)<BR>
     * ���͋敪<BR>
     * <BR>
     * 0�F�ڋq<BR>
     * 1�F�b�b�I�y���[�^<BR> 
     * 2�F�Ǘ���<BR>
     */
    public String inputDiv;
    
    /**
     * (�쐬�҃R�[�h)<BR>
     * �쐬�҃R�[�h<BR>
     */
    public String creatorCode;
    
    /**
     * (���������t���O)<BR>
     * ���������t���O<BR>
     * <BR>
     * true�F�@@��������<BR>
     * false�F�@@�V�K����<BR>
     */
    public boolean exAccountFlag;
    
    /**
     * (���������R�[�h)<BR>
     * ���������R�[�h<BR>
     */
    public String exAccountCode;
    
    /**
     * (�����������X��)<BR>
     * �����������X��<BR>
     */
    public String exAccountBranchName;
    
    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�l�A�J�E���g�@@1�F�@@�l�A�J�E���g<BR>
     */
    public String accountType;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (���҃R�[�h�iSONAR�j)<BR>
     * ���҃R�[�h�iSONAR�j<BR>
     */
    public String traderCode;
    
    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     */
    public String mailAddress;
    
    /**
     * (���[���A�h���X�i�g�сj)<BR>
     * ���[���A�h���X�i�g�сj<BR>
     */
    public String mobileMailAddress;
    
    /**
     * (�ڋq���i�����j)<BR>
     * �ڋq���i�����j<BR>
     */
    public String accountFamilyName;
    
    /**
     * (�ڋq���i�����j)<BR>
     * �ڋq���i�����j<BR>
     */
    public String accountName;
    
    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */
    public String accountFamilyNameKana;
    
    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */
    public String accountNameKana;
    
    /**
     * (���N�����N��)<BR>
     * ���N�����N��<BR>
     * <BR>
     * 1�F�����@@2�F�吳�@@3�F���a�@@4�F�����@@9�F�s��<BR>
     */
    public String eraBorn;
    
    /**
     * (���N����)<BR>
     * ���N����<BR>
     * <BR>
     * �a��iYYMMDD�j�F6��<BR>
     */
    public String bornDate;
    
    /**
     * (����)<BR>
     * ����<BR>
     * <BR>
     * 1�F�@@�j��<BR>
     * 2�F�@@����<BR>
     */
    public String sex;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     * <BR>
     * �� ���͂̂݁B�ԋp���Ȃ��B<BR>
     */
    public String password;
    
    /**
     * (�X�֔ԍ�)<BR>
     * �X�֔ԍ�<BR>
     */
    public String zipCode;
    
    /**
     * (�Z���P�i�J�i�j)<BR>
     * �Z���P�i�J�i�j<BR>
     */
    public String addressKana1;
    
    /**
     * (�Z���Q�i�J�i�j)<BR>
     * �Z���Q�i�J�i�j<BR>
     */
    public String addressKana2;
    
    /**
     * (�Z���R�i�J�i�j)<BR>
     * �Z���R�i�J�i�j<BR>
     */
    public String addressKana3;
    
    /**
     * (�Z���P�i�����j)<BR>
     * �Z���P�i�����j<BR>
     */
    public String address1;
    
    /**
     * (�Z���Q�i�����j)<BR>
     * �Z���Q�i�����j<BR>
     */
    public String address2;
    
    /**
     * (�Z���R�i�����j)<BR>
     * �Z���R�i�����j<BR>
     */
    public String address3;
    
    /**
     * (�d�b�ԍ�)<BR>
     * �d�b�ԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����<BR>
     */
    public String telephone;
    
    /**
     * (�g�єԍ�)<BR>
     * �g�єԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����<BR>
     */
    public String mobileTelephone;
    
    /**
     * (�e�`�w�ԍ�)<BR>
     * �e�`�w�ԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����<BR>
     */
    public String faxTelephone;
    
    /**
     * (�Ζ�����)<BR>
     * �Ζ�����<BR>
     */
    public WEB3AccOpenOfficeInfo officeInfo;
    
    /**
     * (�A����Z��)<BR>
     * �A����Z��<BR>
     */
    public String contactAddress;
    
    /**
     * (�A����d�b�ԍ�)<BR>
     * �A����d�b�ԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����<BR>
     */
    public String contactTelephone;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String familyRelationDiv;
    
    /**
     * (�������i���̑��j)<BR>
     * �����i���̑��j<BR>
     * <BR>
     * �� ���̑���I�������ꍇ����<BR>
     */
    public String familyRelationEtc;
    
    /**
     * (���ю喼�i�����j)<BR>
     * ���ю喼�i�����j<BR>
     */
    public String houseHolderName;
    
    /**
     * (���ю喼�i�J�i�j)<BR>
     * ���ю喼�i�J�i�j<BR>
     */
    public String houseHolderNameKana;
    
    /**
     * (���ю�Ζ�����)<BR>
     * ���ю�Ζ�����<BR>
     */
    public WEB3AccOpenOfficeInfo houseHolderOfficeInfo;
    
    /**
     * (���Z�i���{�j�^�񋏏Z�i���{�ȊO�j�敪)<BR>
     * ���Z�i���{�j�^�񋏏Z�i���{�ȊO�j�敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String residentDiv;
    
    /**
     * (�U�֋敪)<BR>
     * �U�֋敪<BR>
     * <BR>
     * 1�F�@@��s�U��<BR>
     * 2�F�@@�X���U��<BR>
     */
    public String transferDiv;
    
    /**
     * (�������`�l)<BR>
     * �������`�l<BR>
     */
    public String financialAccountName;
    
    /**
     * (�����ړI�敪)<BR>
     * �����ړI�敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String investPurposeDiv;
    
    /**
     * (������@@�敪)<BR>
     * ������@@�敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String appliMotivatDiv;
    
    /**
     * (�N���敪)<BR>
     * �N���敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String annualIncomeDiv;
    
    /**
     * (���Z���Y�敪)<BR>
     * ���Z���Y�敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String assetValueDiv;
    
    /**
     * (�^�p�\��z�敪)<BR>
     * �^�p�\��z�敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String fundBundgetAmountDiv;
    
    /**
     * (���Y�̐��i�敪)<BR>
     * ���Y�̐��i�敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String fundBundgetDiv;
    
    /**
     * (�����̐��i�i���̑��j)<BR>
     * �����̐��i�i���̑��j<BR>
     * <BR>
     * �� ���̑���I�������ꍇ����<BR>
     */
    public String fundBundgetEtc;
    
    /**
     * (�����p�敪)<BR>
     * �����p�敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String searchDiv;
    
    /**
     * (�{�l�m�F���ދ敪)<BR>
     * �{�l�m�F���ދ敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String idConfirmDocDiv;
    
    /**
     * (�{�l�m�F���ށi���̑��j)<BR>
     * �{�l�m�F���ށi���̑��j<BR>
     * <BR>
     * �� ���̑���I�������ꍇ����<BR>
     */
    public String idConfirmDocEtc;
    
    /**
     * (�������������敪)<BR>
     * �������������敪<BR>
     * <BR>
     * 0�F�@@��ʌ���<BR>
     * 1�F�@@��������i���򒥎��Ȃ��j<BR>
     * 2�F�@@��������i���򒥎�����j<BR>
     */
    public String equityTaxType;
    
    /**
     * (�M�p��������敪)<BR>
     * �M�p��������敪<BR>
     * <BR>
     * 0�F�@@��ʌ���<BR>
     * 1�F�@@��������i���򒥎��Ȃ��j<BR>
     * 2�F�@@��������i���򒥎�����j<BR>
     */
    public String marginTaxType;
    
    /**
     * (�����ғo�^�t���O)<BR>
     * �����ғo�^�t���O<BR>
     * <BR>
     * true�F�@@�����ғo�^����<BR>
     * false�F�@@�����ғo�^�Ȃ�<BR>
     */
    public boolean insiderFlag;
    
    /**
     * (�����Җ�����)<BR>
     * �����Җ�����<BR>
     */
    public String insiderProductName;
    
    /**
     * (���t��X�֔ԍ�)<BR>
     * ���t��X�֔ԍ�<BR>
     */
    public String sendZipCode;
    
    /**
     * (���t��Z���P)<BR>
     * ���t��Z���P<BR>
     */
    public String sendAddress1;
    
    /**
     * (���t��Z���Q)<BR>
     * ���t��Z���Q<BR>
     */
    public String sendAddress2;
    
    /**
     * (���t��Z���R)<BR>
     * ���t��Z���R<BR>
     */
    public String sendAddress3;
    
    /**
     * (�敪�P)<BR>
     * �敪�P<BR>
     */
    public String extItemDiv1;
    
    /**
     * (�敪�Q)<BR>
     * �敪�Q<BR>
     */
    public String extItemDiv2;
    
    /**
     * (�敪�R)<BR>
     * �敪�R<BR>
     */
    public String extItemDiv3;
    
    /**
     * (�敪�S)<BR>
     * �敪�S<BR>
     */
    public String extItemDiv4;
    
    /**
     * (�敪�T)<BR>
     * �敪�T<BR>
     */
    public String extItemDiv5;
    
    /**
     * (�敪�U)<BR>
     * �敪�U<BR>
     */
    public String extItemDiv6;
    
    /**
     * (�敪�V)<BR>
     * �敪�V<BR>
     */
    public String extItemDiv7;
    
    /**
     * (�敪�W)<BR>
     * �敪�W<BR>
     */
    public String extItemDiv8;
    
    /**
     * (�敪�X)<BR>
     * �敪�X<BR>
     */
    public String extItemDiv9;
    
    /**
     * (�敪�P�O)<BR>
     * �敪�P�O<BR>
     */
    public String extItemDiv10;
    
    /**
     * (�t���O�P)<BR>
     * �t���O�P<BR>
     */
    public boolean extItemFlag1;
    
    /**
     * (�t���O�Q)<BR>
     * �t���O�Q<BR>
     */
    public boolean extItemFlag2;
    
    /**
     * (�t���O�R)<BR>
     * �t���O�R<BR>
     */
    public boolean extItemFlag3;
    
    /**
     * (�t���O�S)<BR>
     * �t���O�S<BR>
     */
    public boolean extItemFlag4;
    
    /**
     * (�t���O�T)<BR>
     * �t���O�T<BR>
     */
    public boolean extItemFlag5;
    
    /**
     * (�t���O�U)<BR>
     * �t���O�U<BR>
     */
    public boolean extItemFlag6;
    
    /**
     * (�t���O�V)<BR>
     * �t���O�V<BR>
     */
    public boolean extItemFlag7;
    
    /**
     * (�t���O�W)<BR>
     * �t���O�W<BR>
     */
    public boolean extItemFlag8;
    
    /**
     * (�t���O�X)<BR>
     * �t���O�X<BR>
     */
    public boolean extItemFlag9;
    
    /**
     * (�t���O�P�O)<BR>
     * �t���O�P�O<BR>
     */
    public boolean extItemFlag10;
    
    /**
     * (�e�L�X�g�P)<BR>
     * �e�L�X�g�P<BR>
     */
    public String extItemText1;
    
    /**
     * (�e�L�X�g�Q)<BR>
     * �e�L�X�g�Q<BR>
     */
    public String extItemText2;
    
    /**
     * (�e�L�X�g�R)<BR>
     * �e�L�X�g�R<BR>
     */
    public String extItemText3;
    
    /**
     * (�e�L�X�g�S)<BR>
     * �e�L�X�g�S<BR>
     */
    public String extItemText4;
    
    /**
     * (�e�L�X�g�T)<BR>
     * �e�L�X�g�T<BR>
     */
    public String extItemText5;
    
    /**
     * (�e�L�X�g�U)<BR>
     * �e�L�X�g�U<BR>
     */
    public String extItemText6;
    
    /**
     * (�e�L�X�g�V)<BR>
     * �e�L�X�g�V<BR>
     */
    public String extItemText7;
    
    /**
     * (�e�L�X�g�W)<BR>
     * �e�L�X�g�W<BR>
     */
    public String extItemText8;
    
    /**
     * (�e�L�X�g�X)<BR>
     * �e�L�X�g�X<BR>
     */
    public String extItemText9;
    
    /**
     * (�e�L�X�g�P�O)<BR>
     * �e�L�X�g�P�O<BR>
     */
    public String extItemText10;
    
    /**
     * (�ڋq�������̍쐬�敪)<BR>
     * �ڋq�������̍쐬�敪<BR>
     */
    public String accountRealNameDiv;
    
    /**
     * (�ڋq�������̂P)<BR>
     * �ڋq�������̂P <BR>
     * <BR>
     * ���}�X�^���66byte���邪�A�S�p�����̓��͉\��������20�����̂��ߓ��Y�����O�X�Ƃ���<BR>
     */
    public String accountRealName1;
    
    /**
     * (�ڋq�������̂Q)<BR>
     * �ڋq�������̂Q<BR> 
     * <BR>
     * ���}�X�^���66byte���邪�A�S�p�����̓��͉\��������20�����̂��ߓ��Y�����O�X�Ƃ���<BR>
     */
    public String accountRealName2;
    
    /**
     * (����ƈ��҃R�[�h)<BR>
     * ����ƈ��҃R�[�h<BR>
     */
    public String brokerageCode;
    
    /**
     * (�敪�P�P)<BR>
     * �敪�P�P<BR>
     */
    public String extItemDiv11;
    
    /**
     * (�敪�P�Q)<BR>
     * �敪�P�Q<BR>
     */
    public String extItemDiv12;
    
    /**
     * (�敪�P�R)<BR>
     * �敪�P�R<BR>
     */
    public String extItemDiv13;
    
    /**
     * (�敪�P�S)<BR>
     * �敪�P�S<BR>
     */
    public String extItemDiv14;
    
    /**
     * (�敪�P�T)<BR>
     * �敪�P�T<BR>
     */
    public String extItemDiv15;

    /**
     * (�폜�t���O)<BR>
     * �폜�t���O <BR>
     * <BR>
     * 1�FTRUE/�����i�폜�j <BR>
     * 0�FFALSE/�L���iDEFAULT�j<BR>
     */
    public String deleteFlag;

    /**
     * (�폜����)<BR>
     * �폜����<BR>
     */
    public Date deleteDate;

    /**
     * (����t���O)<BR>
     * ����t���O <BR>
     * <BR>
     * 0�F����� <BR>
     * 1�F����� <BR>
     * 3�F�������iDEFAULT�j<BR>
     */
    public String printFlag;

    /**
     * (��̃t���O)<BR>
     * ��̃t���O <BR>
     * <BR>
     * 1�FTRUE/��̍� <BR>
     * 0�FFALSE/����́iDEFAULT�j<BR>
     */
    public String receiveFlag;

    /**
     * (�����t���O)<BR>
     * �����t���O <BR>
     * <BR>
     * 1�FTRUE/���� <BR>
     * 0�FFALSE/�������iDEFAULT�j<BR>
     */
    public String approveFlag;

    /**
     * (�O���l�t���O)<BR>
     * �O���l�t���O <BR>
     * <BR>
     * 1�FTRUE/���{�ȊO <BR>
     * 0�FFALSE/���{�iDEFAULT�j<BR>
     */
    public String foreignerFlag;

    /**
     * (�U�����s���)<BR>
     * �U�����s���<BR>
     */
    public WEB3AccOpenTransferBankInfo transferBankInfo;
    
    /**
     * (�X�֐U�֏��)<BR>
     * �X�֐U�֏��<BR>
     */
    public WEB3AccOpenPostalTransferInfo postalTransferInfo;
    
    /**
     * (�����o�����)<BR>
     * �����o�����<BR>
     */
    public WEB3AccOpenInvestmentExperienceInfo investExpInfo;
    
    /**
     * (�����̂��������)<BR>
     * �����̂��������<BR>
     */
    public WEB3AccOpenInterestDealingInfo interestDealInfo;
    
    /**
     * �����ҏ��<BR>
     */
    public WEB3AccOpenInsiderInfo insiderInfo;
    
    /**
     * GP���<BR>
     */
    public WEB3AccOpenGpInfo gpInfo;

    /**
     * (�@@�\�ʒm���)<BR>
     * �@@�\�ʒm���<BR>
     */
    public WEB3AccOpenAgencyInfo agencyInfo;

    /**
     * ���O�����<BR>
     */
    public WEB3AccOpenListedFeqInfo listedFeqInfo;
    
    /**
     * �ڋq�R�[�h�����̔ԃt���O<BR>
     */
    public String accountCodeAutoFlag;
    
    /**
     * �O�ݗa���������<BR>
     */
    public WEB3AccOpenForeignSaveInfo foreignSaveInfo;
    
    /**
     * @@roseuid 41B45E7701A5
     */
    public WEB3AccOpenApplyInfo() 
    {
     
    }
}
@
