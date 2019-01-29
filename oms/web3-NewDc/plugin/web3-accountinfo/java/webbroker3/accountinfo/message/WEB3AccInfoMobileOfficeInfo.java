head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�єԍ��E�Ζ�����(WEB3AccInfoMobileOfficeInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
                   2006/02/22 ������ (���u) ���f��No.086
                   2006/03/20 ������ (���u) ���f��No.098
                   2006/06/30 ������ (���u) �d�l�ύX�Ǘ�No.114     
                   2006/10/9  ꎉ�   (���u) ���f��No.124     
                   2006/10/30 ����� (���u) �d�l�ύX���f��No.139
Revesion History : 2009/02/16 �đo�g (���u) ���f��No.256
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�g�єԍ��E�Ζ�����)<BR>
 * �g�єԍ��E�Ζ����񃁃b�Z�[�W<BR>
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeInfo extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeInfo.class);

    
    /**
     * (�g�єԍ�)<BR>
     * �g�єԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����<BR>
     */
    public String mobileTelephone;
    
    /**
     * (�Ζ��於��)<BR>
     * �Ζ��於��<BR>
     */
    public String officeName;
    
    /**
     * (�Ζ���X�֔ԍ�)<BR>
     * �Ζ���X�֔ԍ�<BR>
     */
    public String officeZipCode;
    
    /**
     * (�Ζ���Z��)<BR>
     * �Ζ���Z��<BR>
     */
    public String officeAdress;
    
    /**
     * (�Ζ���d�b�ԍ�)<BR>
     * �Ζ���d�b�ԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����<BR>
     */
    public String officeTelephone;
    
    /**
     * (��E��)<BR>
     * ��E��<BR>
     */
    public String position;
    
    /**
     * (�A����D�揇�ʂP��)<BR>
     * �A����D�揇�ʂP��<BR>
     * 0�F�Ȃ� <BR>
     * 1�F����/�{�Б�\�ԍ� <BR>
     * 2�F�Ζ���/����ӔC�ҋΖ��� <BR>
     * 3�F�g�сE���̑�/���̑��A���� <BR>
     * null�F���ݒ�<BR>
     */
    public String contactPriority1;
    
    /**
     * (�A����D�揇�ʂQ��)<BR>
     * �A����D�揇�ʂQ��<BR>
     * 0�F�Ȃ� <BR>
     * 1�F����/�{�Б�\�ԍ� <BR>
     * 2�F�Ζ���/����ӔC�ҋΖ��� <BR>
     * 3�F�g�сE���̑�/���̑��A���� <BR>
     * null�F���ݒ�<BR>
     */
    public String contactPriority2;
    
    /**
     * (�A����D�揇�ʂR��)<BR>
     * �A����D�揇�ʂR��<BR>
     * 0�F�Ȃ� <BR>
     * 1�F����/�{�Б�\�ԍ� <BR>
     * 2�F�Ζ���/����ӔC�ҋΖ��� <BR>
     * 3�F�g�сE���̑�/���̑��A���� <BR>
     * null�F���ݒ�<BR>
     */
    public String contactPriority3;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String department;
    
    /**
     * (FAX�ԍ�)<BR>
     * FAX�ԍ�<BR>
     */
    public String faxTelephone;
    
    /**
     * (�N��)<BR>
     * �N��<BR>
     */
    public String annualIncomeDiv;
    
    /**
     * (���Z���Y�z)<BR>
     * ���Z���Y�z<BR>
     */
    public String assetValueDiv;
    
    /**
     * (�^�p�\��z)<BR>
     * �^�p�\��z<BR>
     */
    public String fundBundgetAmountDiv;
    
    /**
     * (�����ړI)<BR>
     * �����ړI<BR>
     */
    public String investPurposeDiv;
    
    /**
     * (�����\�����)<BR>
     * �����\�����<BR>
     */
    public String investPlanPeriodDiv;
    
    /**
     * (�����o���̗L���i�P�j)<BR>
     * �����o���̗L���i�P�j<BR>
     */
    public String experienceFlag1;
    
    /**
     * (�����o���̗L���i�Q�j)<BR>
     * �����o���̗L���i�Q�j<BR>
     */
    public String experienceFlag2;
    
    /**
     * (�����o���̗L���i�R�j)<BR>
     * �����o���̗L���i�R�j<BR>
     */
    public String experienceFlag3;
    
    /**
     * (�����o���̗L���i�S�j)<BR>
     * �����o���̗L���i�S�j<BR>
     */
    public String experienceFlag4;
    
    /**
     * (�����o���̗L���i�T�j)<BR>
     * �����o���̗L���i�T�j<BR>
     */
    public String experienceFlag5;
    
    /**
     * (�����o���̗L���i�U�j)<BR>
     * �����o���̗L���i�U�j<BR>
     */
    public String experienceFlag6;
    
    /**
     * (�����o���̗L���i�V�j)<BR>
     * �����o���̗L���i�V�j<BR>
     */
    public String experienceFlag7;
    
    /**
     * (�����o���̗L���i�W�j)<BR>
     * �����o���̗L���i�W�j<BR>
     */
    public String experienceFlag8;
    
    /**
     * (�����o���̗L���i�X�j)<BR>
     * �����o���̗L���i�X�j<BR>
     */
    public String experienceFlag9;
    
    /**
     * (�����o���̗L���i�P�O�j)<BR>
     * �����o���̗L���i�P�O�j<BR>
     */
    public String experienceFlag10;
    
    /**
     * (�����o���i�P�j)<BR>
     * �����o���i�P�j<BR>
     */
    public String experienceDiv1;
    
    /**
     * (�����o���i�Q�j)<BR>
     * �����o���i�Q�j<BR>
     */
    public String experienceDiv2;
    
    /**
     * (�����o���i�R�j)<BR>
     * �����o���i�R�j<BR>
     */
    public String experienceDiv3;
    
    /**
     * (�����o���i�S�j)<BR>
     * �����o���i�S�j<BR>
     */
    public String experienceDiv4;
    
    /**
     * (�����o���i�T�j)<BR>
     * �����o���i�T�j<BR>
     */
    public String experienceDiv5;
    
    /**
     * (�����o���i�U�j)<BR>
     * �����o���i�U�j<BR>
     */
    public String experienceDiv6;
    
    /**
     * (�����o���i�V�j)<BR>
     * �����o���i�V�j<BR>
     */
    public String experienceDiv7;
    
    /**
     * (�����o���i�W�j)<BR>
     * �����o���i�W�j<BR>
     */
    public String experienceDiv8;
    
    /**
     * (�����o���i�X�j)<BR>
     * �����o���i�X�j<BR>
     */
    public String experienceDiv9;
    
    /**
     * (�����o���i�P�O�j)<BR>
     * �����o���i�P�O�j<BR>
     */
    public String experienceDiv10;
    
    /**
     * (����̎�ށi�P�j)<BR>
     * ����̎�ށi�P�j<BR>
     */
    public String interest1;
    
    /**
     * (����̎�ށi�Q�j)<BR>
     * ����̎�ށi�Q�j<BR>
     */
    public String interest2;
    
    /**
     * (����̎�ށi�R�j)<BR>
     * ����̎�ށi�R�j<BR>
     */
    public String interest3;
    
    /**
     * (����̎�ށi�S�j)<BR>
     * ����̎�ށi�S�j<BR>
     */
    public String interest4;
    
    /**
     * (����̎�ށi�T�j)<BR>
     * ����̎�ށi�T�j<BR>
     */
    public String interest5;
    
    /**
     * (����̎�ށi�U�j)<BR>
     * ����̎�ށi�U�j<BR>
     */
    public String interest6;
    
    /**
     * (����̎�ށi�V�j)<BR>
     * ����̎�ށi�V�j<BR>
     */
    public String interest7;
    
    /**
     * (����̎�ށi�W�j)<BR>
     * ����̎�ށi�W�j<BR>
     */
    public String interest8;
    
    /**
     * (����̎�ށi�X�j)<BR>
     * ����̎�ށi�X�j<BR>
     */
    public String interest9;
  
    /**
     * (����̎�ށi�P�O�j)<BR>
     * ����̎�ށi�P�O�j<BR>
     */
    public String interest10;
    
    /**
     * (�����J�݂̓��@@)<BR>
     * �����J�݂̓��@@<BR>
     */
    public String appliMotivatDiv;
    
    /**
     * (�����J�݂̓��@@�̏ڍ�)<BR>
     * �����J�݂̓��@@�̏ڍ�<BR>
     */
    public String appliMotivatDetail;
    
    /**
     * (���ݗ��p���Ă���،����)<BR>
     * ���ݗ��p���Ă���،����<BR>
     */
    public String useInstitutionDiv;
    
    /**
     * (�C���^�[�l�b�g����敪)<BR>
     * �C���^�[�l�b�g����敪<BR>
     */
    public String internetTradeDiv;
    
    /**
     * (�Љ�x�X)<BR>
     * �Љ�x�X<BR>
     */
    public String introduceBranch;
    
    /**
     * (��������1)<BR>
     * ��������1<BR>
     */
    public String accountRealName1;
    
    /**
     * (��������2)<BR>
     * ��������2<BR>
     */
    public String accountRealName2;
    
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
     * (��\�Җ��i�����j��)<BR>
     * ��\�Җ��i�����j��<BR>
     */
    public String representFamilyName;
    
    /**
     * (��\�Җ��i�����j��)<BR>
     * ��\�Җ��i�����j��<BR>
     */
    public String representName;
    
    /**
     * (��\�Җ��i�J�i�j��)<BR>
     * ��\�Җ��i�J�i�j��<BR>
     */
    public String representFamilyNameKana;
    
    /**
     * (��\�Җ��i�J�i�j��)<BR>
     * ��\�Җ��i�J�i�j��<BR>
     */
    public String representNameKana;
    
    /**
     * (��\�Ҍ�)<BR>
     * ��\�Ҍ�<BR>
     */
    public String representPower;
    
    /**
     * (����ӔC�Җ��i�����j��)<BR>
     * ����ӔC�Җ��i�����j��<BR>
     */
    public String directorFamilyName;
    
    /**
     * (����ӔC�Җ��i�����j��)<BR>
     * ����ӔC�Җ��i�����j��<BR>
     */
    public String directorName;
    
    /**
     * (����ӔC�Җ��i�J�i�j��)<BR>
     * ����ӔC�Җ��i�J�i�j��<BR>
     */
    public String directorFamilyNameKana;
    
    /**
     * (����ӔC�Җ��i�J�i�j��)<BR>
     * ����ӔC�Җ��i�J�i�j��<BR>
     */
    public String directorNameKana;
    
    /**
     * (����ӔC�ҏ�������)<BR>
     * ����ӔC�ҏ�������<BR>
     */
    public String directorDepartment;
    
    /**
     * (����ӔC�Җ�E)<BR>
     * ����ӔC�Җ�E<BR>
     */
    public String directorPosition;
    
    /**
     * (����ӔC�җX�֔ԍ�)<BR>
     * ����ӔC�җX�֔ԍ�<BR>
     */
    public String directorZipCode;
    
    /**
     * (����ӔC�ҏZ��1)<BR>
     * ����ӔC�ҏZ��1<BR>
     */
    public String directorAddress1;
    
    /**
     * (����ӔC�ҏZ��2)<BR>
     * ����ӔC�ҏZ��2<BR>
     */
    public String directorAddress2;
    
    /**
     * (����ӔC�ҏZ��3)<BR>
     * ����ӔC�ҏZ��3<BR>
     */
    public String directorAddress3;
    
    /**
     * (����ӔC�Ґ��N�����N��)<BR>
     * ����ӔC�Ґ��N�����N��<BR>
     */
    public String directorEraBorn;
    
    /**
     * (����ӔC�Ґ��N����)<BR>
     * ����ӔC�Ґ��N����<BR>
     */
    public String directorBornDate;
    
    /**
     * (����ӔC�҉�В��ʔԍ�)<BR>
     * ����ӔC�҉�В��ʔԍ�<BR>
     */
    public String directorCorpDirect;
    
    /**
     * (���̑��A����(�g�сA���))<BR>
     * ���̑��A����(�g�сA���)<BR>
     */
    public String directorOtherContact;

    /**
     * (�g�єԍ��E�Ζ�����)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo
     * @@roseuid 413FFE2002DB
     */
    public WEB3AccInfoMobileOfficeInfo() 
    {
     
    }
    
    /**
     * �g�єԍ��E�Ζ�����f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�g�єԍ��̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�P�|�P�j�@@������ɔ��p�����ƃn�C�t�������i�f-�f�j<BR>
     *   �ȊO�̕������܂܂��ꍇ�́A<BR>
     *   �n�C�t���i�f-�f�j���Q�܂܂�Ă��邱�Ƃ��`�F�b�N����B<BR>
     * �@@�i�n�C�t���̐����Q�łȂ��ꍇ�A��O���X���[����B�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01113<BR>
     * <BR>
     * �Q�j�@@�Ζ��於�̂̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@��������25�i50byte�j���傫���ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01116<BR>
     * <BR>
     * �R�j�@@�Ζ���X�֔ԍ��̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�R�|�P�j�@@���p�����ȊO�̕������܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�R�|�Q�j�@@��������7byte�ł��邱�ƁB<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01117<BR>
     * <BR>
     * �S�j�@@�Ζ���Z���̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�S�|�P�j�@@��������50�i100byte�j���傫���ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01120<BR>
     * <BR>
     * �T�j�@@�Ζ���d�b�ԍ��̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�T�|�P�j�@@������ɔ��p�����ƃn�C�t�������i�f-�f�j<BR>
     *    �ȊO�̕������܂܂��ꍇ�́A<BR>
     *    �n�C�t���i�f-�f�j���Q�܂܂�Ă��邱�Ƃ��`�F�b�N����B<BR>
     *   �i�n�C�t���̐����Q�łȂ��ꍇ�A��O���X���[����B�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01121<BR>
     * <BR>
     * �U�j�@@��E���̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�U�|�P�j�@@��������18�i36byte�j���傫���ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01124<BR>
     * �V�j�@@FAX�ԍ��̃`�F�b�N <BR>
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�V�|�P�j�@@������ɔ��p�����ƃn�C�t�������i�f-�f�j�ȊO�̕������܂܂��ꍇ�́A
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02658<BR>
     * �@@�V�|�Q�j�@@�n�C�t���i�f-�f�j���Q�܂܂�Ă��邱�Ƃ��`�F�b�N����B<BR>
     * �@@�@@�@@�@@�i�n�C�t���̐����Q�łȂ��ꍇ�A��O���X���[����B�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02671<BR>
     * <BR>
     * �W�j�@@�����J�݂̓��@@�̏ڍׂ̃`�F�b�N <BR>
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�W�|�P�j�@@��������25�i50byte�j���傫���ꍇ�́A��O���X���[����B<BR>  
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02659<BR>
     * <BR>
     * �X�j�@@����ӔC�җX�֔ԍ��̃`�F�b�N<BR> 
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR> 
     * <BR> 
     *�@@�X�|�P�j�@@���p�����ȊO�̕������܂܂��ꍇ�́A��O���X���[����B<BR> 
     *�@@�X�|�Q�j�@@��������7byte�ł��邱�ƁB<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02682<BR>
     * <BR> 
     * �P�O�j�@@����ӔC�ҔN��+���N�����̃`�F�b�N<BR> 
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR> 
     * <BR> 
     * �@@�P�O�|�P�j�@@�u�N��.toDate(����ӔC�� �N�� , ����ӔC�Ґ��N����)�v<BR>
     * �@@�@@�@@�@@�@@�̖߂�l��null�̏ꍇ�͗�O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02687<BR>
     * <BR>  
     * �P�P�j�@@����ӔC�҉�В��ʔԍ��̃`�F�b�N<BR> 
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR> 
     * <BR> 
     * �@@�P�P�|�P�j�@@������ɔ��p�����ƃn�C�t�������i�f-�f�j�ȊO�̕������܂܂��ꍇ�́A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02683<BR>
     * �@@�P�P�|�Q�j�@@�n�C�t���i�f-�f�j���Q�܂܂�Ă��邱�Ƃ��`�F�b�N����B<BR> 
     * �@@�@@�@@�@@�@@�@@�i�n�C�t���̐����Q�łȂ��ꍇ�A��O���X���[����B�j<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02684<BR>
     * <BR> 
     * �P�Q�j�@@���̑��A����(�g�сA���)�ԍ��̃`�F�b�N<BR> 
     * �@@���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR> 
     * <BR> 
     * �@@�P�Q�|�P�j�@@������ɔ��p�����ƃn�C�t�������i�f-�f�j�ȊO�̕������܂܂��ꍇ�́A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02685<BR>
     * �@@�P�Q�|�Q�j�@@�n�C�t���i�f-�f�j���Q�܂܂�Ă��邱�Ƃ��`�F�b�N����B<BR> 
     * �@@�@@�@@�@@�@@�@@�i�n�C�t���̐����Q�łȂ��ꍇ�A��O���X���[����B�j<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02686<BR>
     * <BR> 
     * @@throws WEB3BaseException
     * @@roseuid 414022C5022F
     */
    public void validate() throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�g�єԍ��̃`�F�b�N<BR>
        //���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B
        if(this.mobileTelephone  != null)
        {
            //�P�|�P�j�@@������ɔ��p�����ƃn�C�t�������i�f-�f�j�ȊO�̕������܂܂��ꍇ�́A��O���X���[����B
            //�n�C�t���i�f-�f�j���Q�܂܂�Ă��邱�Ƃ��`�F�b�N����B�i�n�C�t���̐����Q�łȂ��ꍇ�A��O���X���[����B�j
            if(!WEB3StringTypeUtility.isPhoneNumber(this.mobileTelephone))
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01113,
                    this.getClass().getName(),
                    " �g�єԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���");
            }            
        }

        // �Q�j�@@�Ζ��於�̂̃`�F�b�N<BR>
        //���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B
        if(this.officeName != null)
        {
                          
            //�Q�|1�j�@@��������25�i50byte�j���傫���ꍇ�́A��O���X���[����B
            if(WEB3StringTypeUtility.getByteLength(this.officeName) > 50)
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01116,
                    this.getClass().getName(),
                    " �Ζ��於�̂̕�������25�i50byte�j���傫���ł�");
            }
        }
        
        //
        //�R�j�@@�Ζ���X�֔ԍ��̃`�F�b�N
        //  ���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B
        if(this.officeZipCode != null)
        {
            
            //�R�|�P�j�@@���p�����ȊO�̕������܂܂��ꍇ�́A��O���X���[����B
            //�R�|�Q�j�@@��������7byte�ł��邱�ƁB
            if(!WEB3StringTypeUtility.isZipCode(this.officeZipCode))
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01117,
                    this.getClass().getName(),
                    " �Ζ���X�֔ԍ���7byte���p�����ł͂���܂���B");
            }
        }
        
        //�S�j�@@�Ζ���Z���̃`�F�b�N<BR>
        //���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
        if(this.officeAdress != null)
        {
            
            
            //�S�|1�j�@@��������50�i100byte�j���傫���ꍇ�́A��O���X���[����B
            if(WEB3StringTypeUtility.getByteLength(this.officeAdress) > 100)
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01120,
                    this.getClass().getName(),
                    " �Ζ���Z���̕�������50�i100byte�j���傫���ł�");
            }            
        }
        
        //�T�j�@@�Ζ���d�b�ԍ��̃`�F�b�N<BR>
        //���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B
        if(this.officeTelephone != null)
        {
            
            //�T�|�P�j�@@������ɔ��p�����ƃn�C�t�������i�f-�f�j�ȊO�̕������܂܂��ꍇ�́A��O���X���[����B
            //�n�C�t���i�f-�f�j���Q�܂܂�Ă��邱�Ƃ��`�F�b�N����B�i�n�C�t���̐����Q�łȂ��ꍇ�A��O���X���[����B�j
            if(!WEB3StringTypeUtility.isPhoneNumber(this.officeTelephone))
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01121,
                    this.getClass().getName(),
                    " �Ζ���d�b�ԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���");
            }
        }
        //�U�j�@@��E���̃`�F�b�N<BR>
        //���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
        if(this.position != null)
        {
                     
            //�U�|1�j�@@��������18�i36byte�j���傫���ꍇ�́A��O���X���[����B
            if(WEB3StringTypeUtility.getByteLength(this.position) > 36)
            {               
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01124,
                    this.getClass().getName(),
                    " ��E���̕�������18�i36byte�j���傫���ł�");
            }
        }
        
        //�V�j�@@FAX�ԍ��̃`�F�b�N 
        if (this.faxTelephone != null)
        {
            //�V�|�P�j�@@������ɔ��p�����ƃn�C�t�������i�f-�f�j�ȊO�̕������܂܂��ꍇ�́A��O���X���[����B
            String l_strFaxTelephone = this.faxTelephone;
            if (!WEB3StringTypeUtility.isSingle(l_strFaxTelephone))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02658,
                    this.getClass().getName(),
                    " FAX�ԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���B");
            }
            else
            {
                int l_intLen = WEB3StringTypeUtility.getByteLength(l_strFaxTelephone);
                for (int i = 0; i < l_intLen; i++) 
                {
                    char l_ch = l_strFaxTelephone.charAt(i);
                    if (!(WEB3StringTypeUtility.isSingleNum(l_ch)
                        || (l_ch == '-')))
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02658,
                            this.getClass().getName(),
                            " FAX�ԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���B");
                    }
                }
            }
            
            //�V�|�Q�j�@@�n�C�t���i�f-�f�j���Q�܂܂�Ă��邱�Ƃ��`�F�b�N����B
            //�i�n�C�t���̐����Q�łȂ��ꍇ�A��O���X���[����B�j 
            if (!WEB3StringTypeUtility.isPhoneNumber(this.faxTelephone))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02671,
                    this.getClass().getName(),
                    " �n�C�t���̐����Q�ł͂���܂���B");
            }
        	
        }
         
        //�W�j�@@�����J�݂̓��@@�̏ڍׂ̃`�F�b�N
        //���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B 
        //�W�|�P�j�@@��������25�i50byte�j���傫���ꍇ�́A��O���X���[����B
        if (this.appliMotivatDetail != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.appliMotivatDetail) > 50)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02659,
                    this.getClass().getName(),
                    "�����J�݂̓��@@�̏ڍׂ̕�������25�i50byte�j���傫���ł��B");
            }
        }       
        
        //�X�j����ӔC�җX�֔ԍ��̃`�F�b�N  
        //���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B  
        if(this.directorZipCode != null)
        {
            //�X�|�P�j�@@���p�����ȊO�̕������܂܂��ꍇ�́A��O���X���[����B
            //�X�|�Q�j�@@��������7byte�ł��邱�ƁB
            if(!WEB3StringTypeUtility.isZipCode(this.directorZipCode))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02682,
                    this.getClass().getName(),
                    "����ӔC�җX�֔ԍ���7byte���p�����ł͂���܂���B");
            }
        }
        
        //10�j����ӔC�ҔN��+���N�����̃`�F�b�N
        //���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B
        if (this.directorEraBorn != null && this.directorBornDate != null)
        {
            //�P�O�|�P�j�@@�u�N��.toDate(����ӔC�� �N�� , ����ӔC�Ґ��N����)�v
            //�̖߂�l��null�̏ꍇ�͗�O���X���[����B  
            if (WEB3GentradeEra.toDate(this.directorEraBorn, this.directorBornDate) == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02687,
                    this.getClass().getName(),
                    "����ӔC�� �N��������ӔC�Ґ��N���������w��(null)�܂��͕s���Ȓl�ł��B");
            }
        }
        
        //�P�P�j�@@����ӔC�҉�В��ʔԍ��̃`�F�b�N  
        //���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B
        if (this.directorCorpDirect != null)
        {
            //�P�P�|�P�j�@@������ɔ��p�����ƃn�C�t�������i�f-�f�j�ȊO�̕������܂܂��ꍇ�́A��O���X���[����B
            String l_strDirectorCorpDirecte = this.directorCorpDirect;
            if (!WEB3StringTypeUtility.isSingle(l_strDirectorCorpDirecte))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02683,
                    this.getClass().getName(),
                    "����ӔC�҉�В��ʔԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���B");
            }
            else
            {
                int l_intLen = WEB3StringTypeUtility.getByteLength(l_strDirectorCorpDirecte);
                for (int i = 0; i < l_intLen; i++) 
                {
                    char l_ch = l_strDirectorCorpDirecte.charAt(i);
                    if (!(WEB3StringTypeUtility.isSingleNum(l_ch)
                        || (l_ch == '-')))
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02683,
                            this.getClass().getName(),
                            "����ӔC�҉�В��ʔԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���B");
                    }
                }
            }
            //�P�P�|�Q�j�@@�n�C�t���i�f-�f�j���Q�܂܂�Ă��邱�Ƃ��`�F�b�N����B  
            //�n�C�t���̐����Q�łȂ��ꍇ�A��O���X���[����B�j  
            if (!WEB3StringTypeUtility.isPhoneNumber(this.directorCorpDirect))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02684,
                    this.getClass().getName(),
                    "�n�C�t���̐����Q�ł͂���܂���B");
            }
        }
        
        //�P�Q�j�@@���̑��A����(�g�сA���)�ԍ��̃`�F�b�N  
        //���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s���B  
        if (this.directorOtherContact != null)
        {
            //�P�Q�|�P�j�@@������ɔ��p�����ƃn�C�t�������i�f-�f�j�ȊO�̕������܂܂��ꍇ�́A��O���X���[����B
            String l_strDirectorOtherContact = this.directorOtherContact;
            if (!WEB3StringTypeUtility.isSingle(l_strDirectorOtherContact))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02685,
                    this.getClass().getName(),
                    "���̑��A����(�g�сA���)�ԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���B");
            }
            else
            {
                int l_intLen = WEB3StringTypeUtility.getByteLength(l_strDirectorOtherContact);
                for (int i = 0; i < l_intLen; i++) 
                {
                    char l_ch = l_strDirectorOtherContact.charAt(i);
                    if (!(WEB3StringTypeUtility.isSingleNum(l_ch)
                        || (l_ch == '-')))
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02685,
                            this.getClass().getName(),
                            "���̑��A����(�g�сA���)�ԍ������p�����ƃn�C�t�������i�f-�f�j�ł͂���܂���B");
                    }
                }
            }
            //�P�Q�|�Q�j�@@�n�C�t���i�f-�f�j���Q�܂܂�Ă��邱�Ƃ��`�F�b�N����B  
            //�i�n�C�t���̐����Q�łȂ��ꍇ�A��O���X���[����B�j  
            if (!WEB3StringTypeUtility.isPhoneNumber(this.directorOtherContact))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02686,
                    this.getClass().getName(),
                    "�n�C�t���̐����Q�ł͂���܂���B");
            }
        }
        log.exiting(STR_METHOD_NAME);     
    }
}
@
