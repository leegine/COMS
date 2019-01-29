head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.56.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountBaseInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq��{��񃁃b�Z�[�W�N���X(WEB3AccInfoAccountBaseInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
Revesion History : 2006/02/22 ������ (���u) ���f��No.086
Revesion History : 2006/06/30 ������ (���u) �d�l�ύX�Ǘ�No.114
Revesion History : 2006/09/07 �Ԑi�@@ (���u) �d�l�ύX�Ǘ�No.121
Revesion History : 2006/12/01 ���� (���u) �d�l�ύX�Ǘ�No.150
Revesion History : 2007/02/26 �g��i (���u) �d�l�ύX�Ǘ�No.200
Revesion History : 2007/03/16 �g��i (���u) �d�l�ύX�Ǘ�No.212
Revesion History : 2007/03/08 �g��i (���u) �d�l�ύX�Ǘ�No.208�A210
Revesion History : 2007/07/13 ���g (���u) �d�l�ύX�Ǘ�No.219
Revesion History : 2007/09/07 ���g (���u) �d�l�ύX�E���f��No.223
Revesion History : 2007/08/28 ���g (���u) �d�l�ύX�E���f��No.217
Revesion History : 2007/11/01 ���g (���u) �d�l�ύX�E���f��No.228
Revesion History : 2008/02/15 �g�C�� (���u) �d�l�ύX�E���f��No.229
Revesion History : 2008/05/22 �Ԑi�@@ (���u) �d�l�ύX�E���f��No.234
Revesion History : 2008/08/01 ������ (���u) �d�l�ύX�E���f��No.238
Revesion History : 2008/08/22 ������ (���u) �d�l�ύX�E���f��No.247
Revesion History : 2008/09/26 ���g (���u) �d�l�ύX�E���f��No.251
Revesion History : 2010/02/21 ���g (���u) �d�l�ύX�E���f��No.263
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�ڋq��{���)<BR>
 * �ڋq��{��񃁃b�Z�[�W�N���X<BR>
 *
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoAccountBaseInfo extends Message
{
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */
    public String accountNameKana;

    /**
     * (�ڋq���i�����j)<BR>
     * �ڋq���i�����j<BR>
     */
    public String accountName;

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
     * <BR>
     */
    public String bornDate;

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
     * (�����J�ݓ�)<BR>
     * �����J�ݓ�<BR>
     */
    public Date accountOpenDate;

    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h<BR>
     */
    public String traderCode;

    /**
     * (�U����w�����)<BR>
     * �U����w�����<BR>
     */
    public WEB3AccInfoAccountInfo transferAccount;

    /**
     * (��p�U�������)<BR>
     * ��p�U�������<BR>
     */
    public WEB3AccInfoAccountInfo exclusiveTransferAccount;

    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     */
    public String mailAddress;

    /**
     * (���[���A�h���X�X�V����)<BR>
     * ���[���A�h���X�X�V����<BR>
     */
    public Date mailAddressUpdateDate;

    /**
     * (������胁�[�����M�t���O)<BR>
     * ������胁�[�����M�t���O<BR>
     * <BR>
     * true�F���M�v�@@false�F���M�s�v<BR>
     */
    public boolean equityExecMailFlag;

    /**
     * (��������胁�[�����M�t���O)<BR>
     * ��������胁�[�����M�t���O<BR>
     * <BR>
     * true�F���M�v�@@false�F���M�s�v<BR>
     */
    public boolean equityUnExecMailFlag;

    /**
     * (�敨OP��胁�[�����M�t���O)<BR>
     * �敨OP��胁�[�����M�t���O<BR>
     * <BR>
     * true�F���M�v�@@false�F���M�s�v<BR>
     */
    public boolean futOpExecMailFlag;

    /**
     * (�敨OP����胁�[�����M�t���O)<BR>
     * �敨OP����胁�[�����M�t���O<BR>
     * <BR>
     * true�F���M�v�@@false�F���M�s�v<BR>
     */
    public boolean futOpUnExecMailFlag;

    /**
     * (�ē����[�����M�t���O)<BR>
     * �ē����[�����M�t���O<BR>
     * <BR>
     * true�F���M�v�@@false�F���M�s�v<BR>
     */
    public boolean guidanceMailFlag;

    /**
     * (���O�C���G���[��)<BR>
     * ���O�C���G���[��<BR>
     */
    public String loginErrorCount;

    /**
     * (�p�X���[�h�X�V����)<BR>
     * �p�X���[�h�X�V����<BR>
     */
    public Date lPasswordUpdateDate;

    /**
     * (�Ïؔԍ��X�V����)<BR>
     * �Ïؔԍ��X�V����<BR>
     */
    public Date passwordUpdateDate;

    /**
     * (�Ïؔԍ��ύX�\�t���O)<BR>
     * �Ïؔԍ��ύX�\�t���O<BR>
     * <BR>
     * �Ïؔԍ��ύX�{�^����\������ꍇ�F�@@true<BR>
     * �Ïؔԍ��ύX�{�^����\�����Ȃ��ꍇ�F�@@false<BR>
     */
    public boolean passwordUpdateAbleFlag;

    /**
     * (�萔���R�[�X)<BR>
     * �萔���R�[�X<BR>
     * ���@@���݂̎萔���R�[�X <BR>
     * �萔���R�[�X�i�萔���R�[�X�R�[�h�j<BR> 
     * <BR>
     * 02�F�@@�藦�萔���i�X�^���_�[�h�j <BR>
     * �i����1���������{�M�p1���������@@�����e���̂݁j<BR> 
     * 03�F�@@��������v <BR>
     * �i����1�����v�{�M�p1�����v�@@�����e���̂݁j<BR> 
     * 04�F�@@���� <BR>
     * 05�F�@@�����z�� <BR>
     * 06�F�@@���z�{�b�N�X <BR>
     * 07�F�@@����1�����v�{�M�p1�������� <BR>
     * 08�F�@@����1���������{�M�p1�����v <BR>
     * 16�F�@@���z�{�b�N�X�i�L�����y�[���j <BR>
     * 99�F�@@��L�ȊO�i���e���E���̂݁j<BR> 
     * <BR>
     * ���@@�e�R�[�h�̖��̂ɂ��ẮA�،���Ђɂ���ĈႤ�B<BR> 
     * �@@�@@Web�w�ɂāA���̂ɕϊ�����B<BR>
     */
    public String commissionCourse;

    /**
     * (�L�����t���O)<BR>
     * �L�����t���O<BR>
     * <BR>
     * true�F�@@�o�^�ς݁@@false�F�@@���o�^<BR>
     */
    public boolean chargedInfoFlag;

    /**
     * (�U����w����Z�@@�֓o�^�t���O)<BR>
     * �U����w����Z�@@�֓o�^�t���O<BR>
     * <BR>
     * true�F�@@�o�^�ς݁@@false�F�@@���o�^<BR>
     */
    public boolean transferFinancialInstitutionFlag;

    /**
     * (�O�ݐU����w����Z�@@�֓o�^�t���O)<BR>
     * �O�ݐU����w����Z�@@�֓o�^�t���O<BR>
     * <BR>
     * true�F�@@�o�^�ς݁@@false�F�@@���o�^<BR>
     */
    public boolean foreignTransferFinancialInstitutionFlag;

    /**
     * (���x�M�p����o�^�t���O)<BR>
     * ���x�M�p����o�^�t���O<BR>
     * <BR>
     * true�F�@@�o�^�ς݁@@false�F�@@���o�^<BR>
     */
    public boolean marketMarginFlag;

    /**
     * (��ʐM�p����o�^�t���O)<BR>
     * ��ʐM�p����o�^�t���O<BR>
     * <BR>
     * true�F�@@�o�^�ς݁@@false�F�@@���o�^<BR>
     */
    public boolean institutionMarginFlag;

    /**
     * (�敨OP����o�^)<BR>
     * �敨OP����o�^<BR>
     * <BR>
     * 1�F�@@�o�^�ς݁iOP��������j<BR>
     * 2�F�@@�o�^�ς݁i�敨����j<BR>
     * 3�F�@@�o�^�ς݁i�敨�^OP����j<BR>
     * <BR>
     */
    public String futOpTradeRegist;

    /**
     * (����F�EMMF�o�^)<BR>
     * ����F�EMMF�o�^<BR>
     * <BR>
     * �� �ۗL���Ă���t�@@���h���̂�z��ɂăZ�b�g����B<BR>
     */
    public String[] ruitoRegist;

    /**
     * (�������������敪)<BR>
     * �������������敪<BR>
     * <BR>
     * 1�F�@@���J�݁i��ʁj<BR>
     * 2�F�@@�J�ݍς݌��򒥎��Ȃ��i���肩���򒥎��Ȃ��j<BR>
     * 3�F�@@�J�ݍς݌��򒥎�����i���肩���򒥎��j<BR>
     * <BR>
     */
    public String equityTaxType;

    /**
     * (�������������敪�i���N�j)<BR>
     * �������������敪�i���N�j<BR>
     * <BR>
     * 1�F�@@���J�݁i��ʁj<BR>
     * 2�F�@@�J�ݍς݌��򒥎��Ȃ��i���肩���򒥎��Ȃ��j<BR>
     * 3�F�@@�J�ݍς݌��򒥎�����i���肩���򒥎��j<BR>
     * <BR>
     */
    public String equityTaxTypeNext;

    /**
     * (����������������J�ݓ�)<BR>
     * ����������������J�ݓ�<BR>
     */
    public Date equityCapitalGainTaxOpenDate;

    /**
     * (�M�p��������敪)<BR>
     * �M�p��������敪<BR>
     * <BR>
     * 1�F�@@���J�݁i��ʁj<BR>
     * 2�F�@@�J�ݍς݌��򒥎��Ȃ��i���肩���򒥎��Ȃ��j<BR>
     * 3�F�@@�J�ݍς݌��򒥎�����i���肩���򒥎��j<BR>
     * <BR>
     */
    public String marginTaxType;

    /**
     * (�M�p��������敪�i���N�j)<BR>
     * �M�p��������敪�i���N�j<BR>
     * <BR>
     * 1�F�@@���J�݁i��ʁj<BR>
     * 2�F�@@�J�ݍς݌��򒥎��Ȃ��i���肩���򒥎��Ȃ��j<BR>
     * 3�F�@@�J�ݍς݌��򒥎�����i���肩���򒥎��j<BR>
     */
    public String marginTaxTypeNext;

    /**
     * (�M�p�����������J�ݓ�)<BR>
     * �M�p�����������J�ݓ�<BR>
     */
    public Date marginCapitalGainTaxOpenDate;

    /**
     * (�g�єԍ��E�Ζ�����ύX��ԋ敪)<BR>
     * �g�єԍ��E�Ζ�����ύX��ԋ敪<BR>
     * <BR>
     * 0�F�@@�\����<BR>
     * 1�F�@@�\����<BR>
     * 2�F�@@�m�F��<BR>
     */
    public String mobileOfficeChangeStateDiv;

    /**
     * (�g�єԍ��E�Ζ�����)<BR>
     * �g�єԍ��E�Ζ�����<BR>
     */
    public WEB3AccInfoMobileOfficeInfo mobileOfficeInfo;

    /**
     * (�萔���R�[�X�ύX�\�����ꗗ)<BR>
     * �萔���R�[�X�ύX�\�����ꗗ<BR>
     * ���@@�萔���R�[�X�ύX�\�����Ȃ��ꍇ��null�B<BR>
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] commissionCourseChangeInfoList;

    /**
     * (�����ҏ��)<BR>
     * �����ҏ��<BR>
     */
    public WEB3AccInfoInsiderInfo[] insiderList;

    /**
     * (��~���)<BR>
     * ��~���<BR>
     */
    public WEB3AccInfoStopInfo stopInfo;

    /**
     * (�d�q����)<BR>
     * �d�q����<BR>
     */
    public WEB3AccInfoBatoInfo batoStatus;
    
    /**
     * (�O�����������ԍ�)<BR>
     *�O�����������ԍ�      
     */
    public String fstkAccountCode;
    
    /**
     * (�ב֕ۏ؋��������)<BR>
     *�ב֕ۏ؋��������<BR>
     */   
    public WEB3AccInfoFxAccountInfo[] fxAccountInfoList; 
    
    /**
     * (�I���b�N�X��p�U�������)<BR>
     *�I���b�N�X��p�U�������<BR>
     */
    public String orixSubAccCode;
    
    /**
     * (�ݓ������J�݋敪)<BR>
     *�ݓ������J�݋敪<BR>
     *<BR>
     * 0�F�@@�J��<BR>
     * 1�F�@@���J��<BR>
     */
    public String ruitoAccountOpenDiv;

    /**
     * (�O���،������J�݋敪)<BR>
     *�O���،������J�݋敪<BR>
     *<BR>
     * 0�F�@@���J��<BR>
     * 1�F�@@�J��<BR>
     */
    public String foreignSecAccOpenDiv;
    
    /**
     * (����Ǘ������J�݋敪�j<BR>
     *����Ǘ������J�݋敪<BR>
     *<BR>
     * 0�F�@@���J��<BR>
     * 1�F�@@�J��<BR>
     */
    public String capitalGainTaxAccOpenDiv;

    /**
     * (MRF�����J�݋敪)<BR>
     * MRF�����J�݋敪 <BR>
     * <BR>
     * 0�F�@@���J�� <BR>
     * 1�F�@@�J�݁@@<BR>
     */
    public String mrfAccOpenDiv;
    
    /**
     * (�����^�C�v)<BR>
     * �����^�C�v <BR>
     * <BR>
     * 0�F���̑� <BR>
     * 1�F�l�A�J�E���g <BR>
     * 2�F���p�A�J�E���g <BR>
     * 3�F�@@�l�A�J�E���g <BR>
     */
    public String accountType;
    
    /**
     * (�ڋq���ύX�敪1)<BR>
     * �ڋq���ύX�敪1<BR>
     */
    public String accountChangeDiv1;
    
    /**
     * (�ڋq���ύX�敪2)<BR>
     * �ڋq���ύX�敪2 <BR>
     */
    public String accountChangeDiv2;
    
    /**
     * (�ڋq���ύX�敪3)<BR>
     * �ڋq���ύX�敪3<BR>
     */
    public String accountChangeDiv3;
    
    /**
     * (�E��)<BR>
     * �E��<BR>
     */
    public String occupationDiv;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String nationality;
    
    /**
     * (���Ђ��̑�)<BR>
     * ���Ђ��̑�<BR>
     */
    public String nationalityOther;
    
    /**
     * (�ڋq��������1)<BR>
     * �ڋq��������1<BR>
     */
    public String accountRealName1;
    
    /**
     * (�ڋq��������2)<BR>
     * �ڋq��������2<BR>
     */
    public String accountRealName2;

    /**
     * (�@@�l���)<BR>
     * �@@�l���<BR>
     */    
    public WEB3AccInfoCorporationInfo corporationInfo;
    
    /**
     * (�X�g�b�N�I�v�V���������J�݋敪)<BR>
     * �X�g�b�N�I�v�V���������J�݋敪<BR>
     */    
    public String stockOptionAccOpenDiv;
    
    /**
     * (�،��S�ۃ��[���敪)<BR>
     * �،��S�ۃ��[���敪<BR>
     */    
    public String comStockLoanDiv;

    /**
     * (����)<BR>
     * ����
     */
    public String sex;

    /**
     * (�X�g�b�N�I�v�V���������J�ݏ��ꗗ)<BR>
     * �X�g�b�N�I�v�V���������J�ݏ��ꗗ<BR>
     */    
    public WEB3AccInfoStockOptionInfo[] stockOptionAccOpenList;
    
    /**
     * (�萔�������L�����y�[�����)<BR>
     * �萔�������L�����y�[�����<BR>
     */    
    public WEB3AccInfoCommissionCampaignInfo[] commissionCampaignInfoList;
    
    /**
     * (�O�ݐU����w������ꗗ)<BR>
     * �O�ݐU����w������ꗗ<BR>
     */    
    public WEB3AccInfoAccountInfo[] foreignTransferAccountList;

    /**
     * (���o�C����p�����J�݋敪)<BR>
     * ���o�C����p�����J�݋敪<BR>
     * 0�F�@@���J��<BR>
     * 1�F�@@�J��<BR>
     */
    public String mobileAccountDiv;

    /**
     * (�،��S�ۃ��[�������J�ݏ��)<BR>
     * �،��S�ۃ��[�������J�ݏ��<BR>
     */
    public WEB3AccInfoStockLoanAccountInfo stockLoanAccountInfo;

    /**
     * (�����A�h���X���)<BR>
     * �����A�h���X���<BR>
     */
    public WEB3AccInfoMultiMailAddressInfo multiMailAddressInfo;

    /**
     * (�v���A�}�敪)<BR>
     * �v���A�}�敪<BR>
     * 0�F�@@�A�}<BR>
     * 1�F�@@�v��<BR>
     */
    public String proAmDiv;

    /**
     * (PTS�����J�݋敪)<BR>
     * PTS�����J�݋敪<BR>
     * 0�F�@@���J��<BR>
     * 1�F�@@�J��<BR>
     */
    public String ptsAccOpenDiv;

    /**
     * (�����@@)<BR>
     * �����@@<BR>
     * <BR>
     * 0�F�Y���Ȃ�<BR>
     * 1�F�Y������<BR>
     */
    public String broadcastLaw;

    /**
     * (�q��@@)<BR>
     * �q��@@<BR>
     * <BR>
     * 0�F�Y���Ȃ�<BR>
     * 1�F�Y������<BR>
     */
    public String aviationLaw;

    /**
     * (NTT�@@)<BR>
     * NTT�@@<BR>
     * <BR>
     * 0�F�Y���Ȃ�<BR>
     * 1�F�Y������<BR>
     */
    public String nttLaw;

    /**
     * (�z�����U���w��敪)<BR>
     * �z�����U���w��敪<BR>
     * <BR>
     * 0�F�w��Ȃ�<BR>
     * 1�F�o�^�z������̌���<BR>
     * 2�F���������z��<BR>
     */
    public String dividendTransDesignate;

    /**
     * (��C�㗝�l)<BR>
     * ��C�㗝�l<BR>
     * <BR>
     * 0�F�I�C�Ȃ�<BR>
     * 1�F�I�C����<BR>
     */
    public String standingProxy;

    /**
     * (�@@��㗝�l)<BR>
     * �@@��㗝�l<BR>
     * <BR>
     * 0�F�I�C�Ȃ�<BR>
     * 1�F�I�C����<BR>
     */
    public String statutoryAgent;

    /**
     * (�����Ҍ����ԍ�)<BR>
     * �����Ҍ����ԍ�<BR>
     */ 
    public String affiliateAccountCode;

    /**
     * (�@@�\�ʒm�����敪)<BR>
     * �@@�\�ʒm�����敪<BR>
     * <BR>
     * 0�F��ʒm<BR>
     * 1�F�ʒm��<BR>
     * 2�F�ʒm��<BR>
     */
    public String agencyNotifyCmpDiv;

    /**
     * (CFD�������)<BR>
     * CFD�������<BR>
     */
    public WEB3AccInfoCfdAccountInfo[] cfdAccountInfoList;

    /**
     * (�����A�h���X���X�g)<BR>
     * �����A�h���X���X�g<BR>
     */
    public WEB3AccInfoMultiMailAddressList multiMailAddressList;

    /**
     * (�ڋq��{���)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@return WEB3AccInfoAccountBaseInfo
     * @@roseuid 4163879A012B
     */
    public WEB3AccInfoAccountBaseInfo()
    {

    }
    

    
}
@
