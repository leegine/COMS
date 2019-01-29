head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���ʃ��N�G�X�g(WEB3AdminSrvRegiServiceChangeCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
Revesion History : 2007/06/06 �юu�� �d�l�ύX���f��251
Revesion History : 2007/06/13 ���� �d�l�ύX���f��262
Revesion History : 2007/06/29 �����Q �d�l�ύX���f��278
Revesion History : 2007/12/03 ���n �d�l�ύX���f��306�A307
Revesion History : 2007/12/04 ���� �d�l�ύX���f��308
Revesion History : 2009/05/27 �đo�g(���u) �d�l�ύX���f��423
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3HashCalHowToDivDef;
import webbroker3.common.define.WEB3InvestDivDef;
import webbroker3.common.define.WEB3SrvRegiOfferingDivDef;
import webbroker3.common.define.WEB3SrvStatusDef;
import webbroker3.common.define.WEB3SrvUsePeriodDivDef;
import webbroker3.common.define.WEB3SupplyDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalOrderDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiMailDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiSendHowToDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���ʃ��N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���ʃ��N�G�X�g�N���X<BR>
 *
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceChangeCommonRequest extends WEB3GenRequest
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceChangeCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceChangeCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151451L;

    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;

    /**
     * (�T�[�r�X����)
     */
    public String serviceName;

    /**
     * (�E�v)
     */
    public String summary;

    /**
     * (�X�e�[�^�X)<BR>
     * 0:��~���@@1:�񋟒��i�\���s�j�@@2:�񋟒�<BR>
     */
    public String serviceStatus;

    /**
     * (�\���敪)<BR>
     * 0:�s�v�@@1:�v<BR>
     */
    public String applyDiv;

    /**
     * (�d�q���ݒ�敪)<BR>
     * true:�ݒ�L�@@false:�ݒ薳<BR>
     */
    public boolean elePigeonDiv;

    /**
     * (���I�敪)<BR>
     * 0:���@@1:�L<BR>
     */
    public String lotteryDiv;

    /**
     * (���p���ԗ������)
     */
    public WEB3SrvRegiChargeInfo[] chargeInfo;

    /**
     * (���p���ԒP�ʋ敪)<BR>
     * 1:�N�@@2:���@@3:��<BR>
     * �i���p���Ԃ������ꍇ�Anull�j<BR>
     */
    public String trialDiv;

    /**
     * (���p����)<BR>
     * �i���p���Ԃ������ꍇ�Anull�j<BR>
     */
    public String trialPeriod;

    /**
     * (�\���\���ԁi���j)
     */
    public String applyAbleStartDate;

    /**
     * (�\���\���ԁi���j)
     */
    public String applyAbleEndDate;

    /**
     * (���ӏ�����)
     */
    public String consentSentence;

    /**
     * (�T�[�r�X���e)
     */
    public String serviceContent;

    /**
     * (�T�[�r�X����URL)
     */
    public String explainURL;

    /**
     * (���[���敪�i�m�F���[���j)<BR>
     * 0:���M���Ȃ��@@1:���M����<BR>
     */
    public String confirmMailDiv;

    /**
     * (���[���敪�i�_��������[���j)<BR>
     * 0:���M���Ȃ��@@1:���M����<BR>
     */
    public String noticeMailDiv;

    /**
     * (���[�����M���i�_��������[���j)
     */
    public String noticeMailDate;

    /**
     * (��W���ԏ��)
     */
    public WEB3SrvRegiLotteryInfo[] applyInfo;

    /**
     * (URL)
     */
    public String url;

    /**
     * (�񋟌`��)<BR>
     * NULL�F�����t�����s��Ȃ�<BR>
     * 0�F�����񋟂̂݁i�����B�����ɖ����񋟁A���B�����ɖ��񋟁j<BR>
     * 1�F�L���^�����񋟁i�����B�����ɖ����񋟁A���B�����ɗL���񋟁j<BR>
     */
    public String offerType;

    /**
     * (�萔����������v�z)
     */
    public String commissionAttainTotal;

    /**
     * (�K�p�萔������)<BR>
     * �K�p���Ă���萔�������̈ꗗ<BR>
     */
    public WEB3SrvRegiApplyCommissionCondition[] applyCommissionConditions;

    /**
     * (��QURL)
     */
    public String url2;

    /**
     * (�n�b�V���v�Z�����敪)<BR>
     * 0:�w�薳 <BR>
     * 1:MD2 <BR>
     * 2:MD5 <BR>
     * 3:SHA-1 <BR>
     * 4:SHA-256 <BR>
     * 5:SHA-384 <BR>
     * 6:SHA-512<BR>
     */
    public String hashCalHowToDiv;

    /**
     * (�n�b�V���v�Z�菇�敪)<BR>
     * 0:�w�薳 <BR>
     * 1:�d�q�� <BR>
     * 2:�ʏ�v�Z�i�P�j <BR>
     * 3:�ʏ�v�Z�i�Q�j<BR>
     * 4:�Q�i�K�v�Z<BR>
     * 5:���O�C���F��<BR>
     * 7:�V���O���T�C���I���A�g<BR>
     */
    public String hashCalOrderDiv;

    /**
     * (���M���@@�敪)<BR>
     * 0:GET <BR>
     * 1:POST <BR>
     * 2:HTTP-REQUEST <BR>
     * 3:����i�P�j�|���e���N���A�،� MULTEX ��p <BR>
     * 4:����i�Q�j�|���e���N���A�،� ���o�e���R��21 ��p<BR>
     */
    public String sendHowToDiv;

    /**
     * (���M�p�����[�^�敪)<BR>
     * 0:�� <BR>
     * 1:�L<BR>
     */
    public String sendParamDiv;

    /**
     * (�Í����ڋq�R�[�h�敪)<BR>
     * 0:�� <BR>
     * 1:�L<BR>
     */
    public String cryptAccountCodeDiv;

    /**
     * (�n�b�V���l�ꗗ)
     */
    public WEB3SrvRegiExecKey[] hashList;

    /**
     * (���M�p�����[�^�ꗗ)
     */
    public WEB3SrvRegiExecKey[] paramList;

    /**
     * (�����Ώۊ���)<BR>
     * �����Ώۊ���
     */
    public String freeTargetPeriod;

    /**
     * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX���ʃ��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F25765033B
     */
    public WEB3AdminSrvRegiServiceChangeCommonRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) �T�[�r�X�敪�̃`�F�b�N<BR>
     *  1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.�\���敪=="�v"�ł���Athis.�T�[�r�X�敪�̌���!=2���̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00880<BR>
     *  1-3) this.�\���敪=="�s�v"�ł���Athis.�T�[�r�X�敪�̌���!=4���̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00881<BR>
     *  1-4) this.�T�[�r�X�敪�ɐ��l�ȊO���i�[����Ă���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00882<BR>
     * <BR>
     * 2) �T�[�r�X���̂̃`�F�b�N<BR>
     *  2-1) this.�T�[�r�X����==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00883<BR>
     *  2-2) 1Byte��this.�T�[�r�X���̂̌�����100Byte�ł͂Ȃ��ꍇ�A��O���X���[����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00884<BR>
     * <BR>
     * 3) �E�v�̃`�F�b�N<BR>
     *  3-1) this.�\���敪=="�v"�ł���A����this.�E�v==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00885<BR>
     *  3-2) this.�\���敪=="�s�v"�ł���A����this.�E�v!=null�̏ꍇ�A<BR>
     *  ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00886<BR>
     *  3-3) this.�E�v�ɔ��p�J�i�ȊO���Z�b�g����Ă���ꍇ�A��O���X���[����B(*1)<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00887<BR>
     *  3-4) ����this.�E�v�̌�����25�����̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00888<BR>
     * <BR>
     * 4) �X�e�[�^�X�̃`�F�b�N<BR>
     *  4-1) this.�X�e�[�^�X==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00889<BR>
     *  4-2) this.�X�e�[�^�X���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"��~��"<BR>
     * �@@�@@�@@"�񋟒��i�\���s�j"<BR>
     * �@@�@@�@@"�񋟒�"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00890<BR>
     * <BR>
     * 5) �\���敪�̃`�F�b�N<BR>
     *  5-1) this.�\���敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00891<BR>
     *  5-2) this.�\���敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�s�v"<BR>
     * �@@�@@�@@"�v"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00892<BR>
     * <BR>
     * 6) ���I�敪�̃`�F�b�N<BR>
     *  6-1) this.�\���敪=="�v"�ł���A����this.���I�敪==null�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00895<BR>
     *  6-2) this.�\���敪=="�v"�ł���A����this.���I�敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * �@@�@@�@@"��"<BR>
     * �@@�@@�@@"�L"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00896<BR>
     * <BR>
     * 7) ���p���ԗ������̃`�F�b�N<BR>
     * �@@this.���p���ԗ������̌������A�ȉ����`�F�b�N����B<BR>
     * 7-1) this.�X�e�[�^�X!="��~��"�ł���Athis.���I�敪="��"�ł���A<BR>
     * �@@�@@this.���p���ԗ������=null�܂��͗v�f��=0���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01827<BR>
     * <BR>
     * �@@�inull�̏ꍇ�A�`�F�b�N���Ȃ��j<BR>
     *  7-2) this.���p���ԗ������.���p���ԒP�ʋ敪==null�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00897<BR>
     *  7-3) this.���p���ԗ������.���p����==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00898<BR>
     *  7-4) this.���p���ԗ������.���p���Ԃ̌���==0�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00899<BR>
     *  7-5) this.���p���ԗ������.���p���Ԃ̌�����2���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00905<BR>
     *  7-6) this.���p���ԗ������.���p����==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00906<BR>
     *  7-7) this.���p���ԗ������.���p�����̌�����9���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00907<BR>
     * <BR>
     * 8) ���p���ԒP�ʋ敪�A���p���Ԃ̃`�F�b�N<BR>
     *  8-1) this.���I�敪!="��"�ł���A����this.���p����!=null�̏ꍇ�ł���<BR>
     * �@@�@@����this.���p���ԒP�ʋ敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00910<BR>
     *  8-2) this.���I�敪=="��"�ł���Athis.���p����!=null�ł���A�����p���Ԃɐ��l�ȊO���ݒ肳��Ă���ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00912<BR>
     *  8-3) this.���I�敪=="��"�ł���Athis.���p���ԁ�0�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00913<BR>
     *  8-4) this.���I�敪=="��"�ł���Athis.���p���ԁ�2���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00914<BR>
     *  8-5) this.���I�敪=="��"�ł���Athis.���p���ԒP�ʋ敪!=null����<BR>
     * �@@�@@this.���p����!=null�ł���A����this.���p���ԒP�ʋ敪�Ɉȉ��̒l�ȊO���Z�b�g����Ă���ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@"�N"<BR>
     * �@@�@@�@@"��"<BR>
     * �@@�@@�@@"��"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00915<BR>
     * <BR>
     * 9) �\���\���ԁi��-���j�̃`�F�b�N<BR>
     *  9-1) this.���I�敪!="��"�ł���A����this.�\���\���ԁi���j==null�ł���ꍇ�A<BR>
     * �@@�@@����this.�\���\���ԁi���j!=null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00916<BR>
     *  9-2) this.���I�敪!="��"�ł���A����this.�\���\���ԁi���j!=null�ł���ꍇ�A<BR>
     * �@@�@@����this.�\���\���ԁi���j==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00916<BR>
     *  9-3) this.�\���\���ԁi���j!=null�ł���A���\���\���ԁi���j!=null�ł���A<BR>
     * �@@�@@����this.�\���\���ԁi���j���\���\���ԁi���j�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00918<BR>
     *  9-4) this.�\���\���ԁi���j==null�ȊO�ł���A���l�����l�ȊO�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00919<BR>
     *  9-5) this.�\���\���ԁi���j==null�ȊO�ł���A���l�����l�ȊO�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00920<BR>
     *  9-6) this.�\���\���ԁi���j==null�ȊO�ł���A���l��0�ł͂Ȃ��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00921<BR>
     *  9-7) this.�\���\���ԁi���j==null�ȊO�ł���A���l��0�ł͂Ȃ��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00922<BR>
     * <BR>
     * 10) �����Ώۊ��Ԃ̃`�F�b�N <BR>
     * 10-1) �����Ώۊ���!=null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     *  10-1-1) this.�����Ώۊ��Ԃɐ��l�ȊO���i�[����Ă���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02802<BR>
     *  10-1-2) this.�����Ώۊ��ԁ�2���̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02803<BR>
     *  10-1-3) this.�����Ώۊ��ԁ�'1' �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02822<BR>
     *  <BR>
     * 10-2) 10-2) this.�񋟌`��="�����񋟂̂�(�E�c�~��)"�j���́Athis.�񋟌`��="�L���^������(�E�c�~��)"�̏ꍇ�A
     * �ȉ��̃`�F�b�N���s���B<BR> 
     *  10-2-1) �����Ώۊ��� ==null �̏ꍇ�A��O���X���[����B<BR>
     *  <BR>
     * 11) ���ӏ������̃`�F�b�N<BR>
     * 11-1) this.�\���敪=="�v" && this.�X�e�[�^�X!="��~��"�ł���A<BR>
     *       this.���ӏ�����=null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01860<BR>
     * 11-2) this.�\���敪=="�s�v"�ł���Athis.���ӏ�����!=null�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00923<BR>
     * 12) �T�[�r�X���e�̃`�F�b�N<BR>
     * �@@this.�\���敪=="�s�v"�ł���Athis.�T�[�r�X���e!=null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00924<BR>
     * <BR>
     * 13) �T�[�r�X�����iURL�j�̃`�F�b�N<BR>
     * �@@this.�\���敪=="�s�v"�ł���Athis.�T�[�r�X�����iURL�j!=null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00925<BR>
     * <BR>
     * 14) ���[���敪�i�m�F���[���j�̃`�F�b�N<BR>
     *  14-1) this.�\���敪=="�v"�ł���Athis.���[���敪�i�m�F���[���j==null�̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00926<BR>
     *  14-2) this.���[���敪�i�m�F���[���j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"���M���Ȃ�"<BR>
     * �@@�@@�@@"���M����"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00927<BR>
     * <BR>
     * 15) ���[���敪�i�_��������[���j�̃`�F�b�N<BR>
     *  15-1) this.�\���敪=="�v"�ł���Athis.���[���敪�i�_��������[���j==null�̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00928<BR>
     *  15-2) this.���[���敪�i�_��������[���j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"���M���Ȃ�"<BR>
     * �@@�@@�@@"���M����"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00929<BR>
     * <BR>
     * 16) ���[�����M���i�_��������[���j�̃`�F�b�N<BR>
     * �@@this.���[���敪�i�_��������[���j=="���M����"�ł���A����<BR>
     * �@@this.���[�����M���i�_��������[���j==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00930<BR>
     * <BR>
     * 17) ��W���ԏ��̃`�F�b�N<BR>
     * �@@this.��W���ԏ��̌������A�ȉ��̃`�F�b�N���s���B<BR>
     * 17-1) this.�X�e�[�^�X!="��~��"�ł���Athis.���I�敪="�L"�ł���A<BR>
     *�@@�@@this.��W���ԏ��=null�܂��͗v�f��=0���̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01829<BR>
     *�@@<BR>
     * �@@�ithis.�X�e�[�^�X!="��~��"�̏ꍇ�A�܂���null�̏ꍇ�A�s��Ȃ��j<BR>
     *  17-2) this.���I�敪=="��"�ł���Athis.��W���ԏ��!=null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00931<BR>
     *  17-3) this.�^�p�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00932<BR>
     *  17-4) this.�^�p�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"���������I"<BR>
     * �@@�@@�@@"�ʏ�^�p�i���I�L�j"<BR>
     * �@@�@@�@@"�ʏ�^�p�i���I�L�I�[�N�V�����j"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00933<BR>
     *  17-5) this.��W���ԏ��̐\�����ԁi���j==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00934<BR>
     *  17-6) this.��W���ԏ��̐\�����ԁi���j==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00935<BR>
     *  17-7) this.��W���ԏ��̓K�p�J�n��==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00936<BR>
     *  17-8) this.��W���ԏ��̓K�p�I����==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00937<BR>
     *  17-9) this.��W���ԏ��̏o����==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00938<BR>
     *  17-10) this.��W���ԏ��.��W�g==null�ȊO�ł���A�����l�ȊO���Z�b�g����Ă���<BR>
     * �@@�@@�ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00939<BR>
     * <BR>
     *  17-11) this.��W���ԏ��.��W�g�̌�����7���̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01861<BR>
     *  17-12) this.��W���ԏ��.�^�p�敪��"���������I"�܂���"�ʏ�^�p�i���I�L�j"<BR>
     * �@@�@@�ł���A����this.��W���ԏ��.��W�g!=null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00940<BR>
     * <BR>
     *  17-13) this.��W���ԏ��.�^�p�敪!="���������I" &&�A<BR>
     * �@@�@@this.��W���ԏ��.��W�g==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00941<BR>
     * <BR>
     *  17-14) this.���I�敪=="�L"�ł���A����this.��W���ԏ��̗��p����==null��<BR>
     * �@@�@@�ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00942<BR>
     * <BR>
     *  17-15) this.��W���ԏ��.�^�p�敪=="���������I"<BR>
     * �@@�@@�ł���A����this.��W���ԏ��.���D�P��==null�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �y�T�[�r�X���p�z�d�l�ύX�Ǘ��䒠 No:107
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00943<BR>
     * <BR>
     *  17-16) this.��W���ԏ��.�^�p�敪=="�ʏ�^�p�i���I�L�I�[�N�V�����j"�ł���A<BR>
     * �@@�@@����this.��W���ԏ��.���D�P��==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00944<BR>
     * <BR>
     *  17-17) this.��W���ԏ��̏o������this.��W���ԏ��̒��I���̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00947<BR>
     * <BR>
     *  17-18) this.��W���ԏ��̐\�����ԁi���j��this.��W���ԏ��̐\�����ԁi���j�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00948<BR>
     * <BR>
     *  17-19) this.��W���ԏ��̓K�p�J�n����this.��W���ԏ��̓K�p�I�����̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00949<BR>
     * <BR>
     *  17-20) this.��W���ԏ��̒��I����null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00950<BR>
     *  17-21) this.��W���ԏ��̐\�����ԁi���j��this.��W���ԏ��.���I���̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00951<BR>
     *  17-22) this.��W���ԏ��̓K�p�J�n����this.��W���ԏ��.���I���̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00952<BR>
     * 18) �񋟌`���̃`�F�b�N <BR>
     *�@@this.�񋟌`��!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@�@@"�ʏ��" <BR>
     *�@@�@@�@@"������" <BR>
     *�@@�@@�@@"�����񋟂̂�(�E�c�~��)"<BR>
     *�@@�@@�@@"�L���^������(�E�c�~��)"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01180<BR>
     *<BR>
     *  19) �萔����������v�z�̃`�F�b�N<BR>
     *�@@19-1) this.�萔����������v�z!=null�ł���A<BR>
     *�@@�@@�@@���l�����l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01181<BR>
     *�@@19-2) this.�񋟌`��!=null�ł���A����this.�萔����������v�z=null�̏ꍇ�A<BR>
     *�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01182<BR>
     *�@@19-3) this.�񋟌`��!=null�ł���A����this.�萔����������v�z��0�̏ꍇ�A<BR>
     *�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01183<BR>
     *<BR>
     *  20) �K�p�萔�������̃`�F�b�N<BR>
     *  20-1) this.�X�e�[�^�X!="��~��"�ł���A����this.�񋟌`��!=null�ł���A<BR>
     *        ����this.�K�p�萔������=null�܂��͗v�f��=0�̏ꍇ�A<BR>
     *        �܂���this.�X�e�[�^�X!="��~��"�ł���A����this.�񋟌`��!=null�ł���A<BR>
     *        this.�K�p�萔�������̖����敪���S��"����"�̏ꍇ�A<BR>
     *     ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01184<BR>
     *�@@20-2) this.�X�e�[�^�X!="��~��"�ł���A����this.�񋟌`��=null�ł���A<BR>
     *�@@�@@    ����this.�K�p�萔������!=null���v�f��>0�ł���A<BR>
     *�@@�@@    ����this.�K�p�萔�������̖����敪���S��"����"�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01185<BR>
     *�@@20-3) this.�K�p�萔�������̌������A�ȉ��̃`�F�b�N���s���B<BR>
     *�@@ 20-3-1) this.�K�p�萔������.���i���ދ敪=null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01186<BR>
     * 21) �X�e�[�^�X�A���p���ԗ������A��W���ԏ��̃`�F�b�N <BR>
     *�@@21-1) this.�X�e�[�^�X!="��~��" && this.���I�敪="��" &&<BR> 
     * �@@�@@�@@(this.�񋟌`��!="�����񋟂̂�" ���́A<BR>
     * �@@�@@�@@this.�񋟌`��!="�����񋟂̂�(�E�c�~��)"�j�̏ꍇ<BR>
     *�@@ 21-1-1) this.���p���ԗ������==0���܂���null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01287<BR>
     *�@@ 21-1-2) this.���p���ԗ������̑S�Ă̖����敪=="����"�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01288<BR>
     * 21-2) this.�X�e�[�^�X!="��~��" && <BR>
     * �@@�@@�@@this.���I�敪="��" && �ithis.�񋟌`��=="�����񋟂̂�" ���́A<BR>
     * �@@�@@�@@this.�񋟌`��=="�����񋟂̂�(�E�c�~��)"�j�̏ꍇ<BR>
     *�@@ 21-2-1) (this.���p���ԗ������>0�� or null�ł͂Ȃ�) &&<BR>
     * �@@�@@�@@�@@�@@(this.���p���ԗ������̖����敪���P�ł�"�L��")�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01906<BR>
     * 21-3) this.�X�e�[�^�X!="��~��" && this.���I�敪="�L"�̏ꍇ<BR>
     * �@@21-3-1) this.��W���ԏ��==0�� or null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01289<BR>
     * �@@21-3-2) this.��W���ԏ��̑S�Ă̖����敪=="����"�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01290<BR>
     * 22) �n�b�V���v�Z�����敪�̃`�F�b�N <BR>
     * 22-1) this.�n�b�V���v�Z�����敪==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01841<BR>
     * 22-2) this.�n�b�V���v�Z�����敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E�w�薳 <BR>
     * �@@�@@�EMD2 <BR>
     * �@@�@@�EMD5 <BR>
     * �@@�@@�ESHA-1 <BR>
     * �@@�@@�ESHA-256 <BR>
     * �@@�@@�ESHA-384 <BR>
     * �@@�@@�ESHA-512<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01842<BR>
     * <BR>
     * 23) �n�b�V���v�Z�菇�敪�̃`�F�b�N <BR>
     * 23-1) this.�n�b�V���v�Z�菇�敪==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01843<BR>
     * 23-2) this.�n�b�V���v�Z�菇�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E�w�薳 <BR>
     * �@@�@@�E�d�q�� <BR>
     * �@@�@@�E�ʏ�v�Z�i�P�j <BR>
     * �@@�@@�E�ʏ�v�Z�i�Q�j<BR>
     * �@@�@@�E�Q�i�K�v�Z<BR>
     * �@@�@@�E���O�C���F��<BR>
     * �@@�@@�E�V���O���T�C���I���A�g<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01844<BR>
     * <BR>
     * 24) ���M���@@�敪�̃`�F�b�N <BR>
     * 24-1) this.���M���@@�敪==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01845<BR>
     * 24-2) this.���M���@@�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�EGET <BR>
     * �@@�@@�EPOST <BR>
     * �@@�@@�EHTTP-REQUEST <BR>
     * �@@�@@�E����i�P�j�|���e���N���A�،� MULTEX ��p <BR>
     * �@@�@@�E����i�Q�j�|���e���N���A�،� ���o�e���R��21 ��p<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01846<BR>
     * <BR>
     * 25) ���M�p�����[�^�敪�̃`�F�b�N <BR>
     * 25-1) this.���M�p�����[�^�敪==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01847<BR>
     * 25-2) this.���M�p�����[�^�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E�� <BR>
     * �@@�@@�E�L<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01848<BR>
     * <BR>
     * 26) �Í����ڋq�R�[�h�敪�̃`�F�b�N <BR>
     * 26-1) this.�Í����ڋq�R�[�h�敪==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01849<BR>
     * 26-2) this.�Í����ڋq�R�[�h�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E�� <BR>
     * �@@�@@�E�L<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01850<BR>
     * <BR>
     * 27) �n�b�V���l�ꗗ�̃`�F�b�N <BR>
     * �@@�n�b�V���l�ꗗ!=null�ł���A���v�f��>0���̏ꍇ�A�ȉ��̃`�F�b�N�����{����B<BR>
     * 27-1) �n�b�V���l�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01851<BR>
     * 27-2) �n�b�V���l�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01852<BR>
     * <BR>
     * 28) ���M�p�����[�^�ꗗ�̃`�F�b�N <BR>
     * �@@���M�p�����[�^�ꗗ!=null�ł���A���v�f��>0���̏ꍇ�A�ȉ��̃`�F�b�N�����{����B<BR>
     * 28-1) ���M�p�����[�^�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01853<BR>
     * 28-2) ���M�p�����[�^�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01854<BR>
     * <BR>
     * 29) �n�b�V���v�Z�����敪�A�n�b�V���v�Z�菇�敪�̃`�F�b�N <BR>
     * 29-1) this.�n�b�V���v�Z�����敪!="�w�薳"�ł���A����this.�n�b�V���v�Z�菇�敪="�w�薳"<BR>
     * �@@�@@�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01855<BR>
     * 29-2) this.�n�b�V���v�Z�����敪="�w�薳"�ł���A����this.�n�b�V���v�Z�菇�敪!="�w�薳"<BR>
     * �@@�@@�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01855<BR>
     * <BR>
     * 30) �X�e�[�^�X�A�n�b�V���v�Z�����敪�A�n�b�V���l�ꗗ�̃`�F�b�N <BR>
     * �@@this.�X�e�[�^�X!="��~��"�̏ꍇ�ɂ̂݁A�ȉ��̃`�F�b�N�����{����B <BR>
     * 30-1) this.�n�b�V���v�Z�����敪!="�w�薳"�ł���A����this.�n�b�V���l�ꗗ=null<BR>
     * �@@�@@�܂��͗v�f��==0���̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01856<BR>
     * 30-2) this.�n�b�V���v�Z�����敪!="�w�薳"�ł���A����this.�n�b�V���l�ꗗ�̑S�Ă�<BR>
     * �@@�@@�����敪="����"�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01857<BR>
     * 30-3) this.���M�p�����[�^�敪="�L"�ł���A����this.���M�p�����[�^�ꗗ=null<BR>
     * �@@�@@�܂��͗v�f��==0���̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01858<BR>
     * 30-4) this.���M�p�����[�^�敪="�L"�ł���A����this.���M�p�����[�^�ꗗ�̑S�Ă�<BR>
     * �@@�@@�����敪="����"�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01859<BR>
     * <BR>
     * 31) �񋟌`���A���p���ԗ������A���p���Ԃ̃`�F�b�N<BR>
     *
     * 31-1) this.�X�e�[�^�X!="��~��" && <BR>
     *     (this.�񋟌`��="�����񋟂̂�" ���́Athis.�񋟌`��="�����񋟂̂�(�E�c�~��)") &&<BR>
     *     this.���p���ԗ������!=null && <BR>
     *     ���p���ԗ������̗v�f����0�� &&<BR>
     *     this.���p���ԗ������̑S�Ă̖����敪��"����"�ł͂Ȃ������ꍇ�A<BR>
     *     ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01906<BR>
     * 31-2)  this.�X�e�[�^�X!="��~��" &&<BR>
     * �@@�@@�@@(this.�񋟌`��=="�����񋟂̂�" ���́A<BR>
     * �@@�@@�@@this.�񋟌`��=="�����񋟂̂�(�E�c�~��)"�j&&<BR>
     * �@@�@@�@@(this.���p���ԒP�ʋ敪!=null or this.���p����!=null)<BR>
     * �@@�@@�@@�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01908<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01909<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F4DC640008
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //1) �T�[�r�X�敪�̃`�F�b�N
        // 1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("1-1) ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + STR_METHOD_NAME);
        }

        // 1-2) this.�\���敪=="�v"�ł���Athis.�T�[�r�X�敪�̌���!=2���̏ꍇ
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            this.serviceDiv.length() != 2)
        {
            log.debug("1-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1-3) this.�\���敪=="�s�v"�ł���Athis.�T�[�r�X�敪�̌���!=4���̏ꍇ�A
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            this.serviceDiv.length() != 4)
        {
            log.debug("1-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1-4) this.�T�[�r�X�敪�ɐ��l�ȊO���i�[����Ă���ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.serviceDiv))
        {
            log.debug("1-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00882,
                getClass().getName() + STR_METHOD_NAME);
        }

        //2) �T�[�r�X���̂̃`�F�b�N
        //2-1) this.�T�[�r�X����==null�̏ꍇ�A��O���X���[����B
        if (this.serviceName == null || "".equals(serviceName.trim()))
        {
            log.debug("2-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00883,
                getClass().getName() + STR_METHOD_NAME);
        }

        //2-2) 1Byte��this.�T�[�r�X���̂̌�����100Byte�ł͂Ȃ��ꍇ�A��O���X���[����
        if (this.serviceName.length() < 1 ||
            WEB3StringTypeUtility.getByteLength(this.serviceName) > 100)
        {
            log.debug("2-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00884,
                getClass().getName() + STR_METHOD_NAME);
        }

        //3) �E�v�̃`�F�b�N
        //3-1) this.�\���敪=="�v"�ł���A����this.�E�v==null�̏ꍇ�A��O���X���[����B
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.summary == null || "".equals(summary.trim())))
        {
            log.debug("3-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00885,
                getClass().getName() + STR_METHOD_NAME);
        }

        //3-2) this.�\���敪=="�s�v"�ł���A����this.�E�v!=null�̏ꍇ�A
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            this.summary != null && summary.trim().length() != 0)
        {
            log.debug("3-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00886,
                getClass().getName() + STR_METHOD_NAME);
        }

        //3-3) this.�E�v�ɔ��p�J�i�ȊO���Z�b�g����Ă���ꍇ�A��O���X���[����B
        if (this.summary != null && summary.trim().length() != 0 && !WEB3StringTypeUtility.is1byteKanaString(this.summary))
        {
            log.debug("3-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00887,
                getClass().getName() + STR_METHOD_NAME);
        }

        //3-4) ����this.�E�v�̌�����25�����̏ꍇ�A��O���X���[����B
        if (this.summary != null && this.summary.length() > 25)
        {
            log.debug("3-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00888,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4) �X�e�[�^�X�̃`�F�b�N
        //4-1) this.�X�e�[�^�X==null�̏ꍇ�A��O���X���[����B
        if (this.serviceStatus == null || "".equals(serviceStatus.trim()))
        {
            log.debug("4-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00889,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4-2) this.�X�e�[�^�X���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            !WEB3SrvStatusDef.PROVIDING_APPLI_IMPOSSIBLE.equals(this.serviceStatus) &&
            !WEB3SrvStatusDef.PROVIDING.equals(this.serviceStatus))
        {
            log.debug("4-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00890,
                getClass().getName() + STR_METHOD_NAME);
        }

        // 5) �\���敪�̃`�F�b�N
        //5-1) this.�\���敪==null�̏ꍇ�A��O���X���[����B
        if (this.applyDiv == null || "".equals(applyDiv.trim()))
        {
            log.debug("5-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00891,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5-2) this.�\���敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            !WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv))
        {
            log.debug("5-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00892,
                getClass().getName() + STR_METHOD_NAME);
        }

        //6) ���I�敪�̃`�F�b�N
        //6-1) this.�\���敪=="�v"�ł���A����this.���I�敪==null�̏ꍇ�A
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.lotteryDiv == null || "".equals(lotteryDiv.trim())))
        {
            log.debug("6-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00895,
                getClass().getName() + STR_METHOD_NAME);
        }

        //6-2) this.�\���敪=="�v"�ł���A����this.���I�敪���ȉ��̒l�ȊO�̏ꍇ�A
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.lotteryDiv))
        {
            log.debug("6-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00844,
                getClass().getName() + STR_METHOD_NAME);
        }

        //7) ���p���ԗ������̃`�F�b�N
        //this.���p���ԗ������̌������A�ȉ����`�F�b�N����B
        //((this.���p���ԗ������=null or �v�f��=0)�̏ꍇ�̓`�F�b�N���Ȃ��j
        if(this.chargeInfo != null && this.chargeInfo.length != 0)
        {
            int l_intLength = chargeInfo.length;
            for (int i = 0; i < l_intLength; i++)
            {
                log.debug("���p���ԗ������̃`�F�b�N");
                //�d�l�ύX NO_197
                //7-0) this.���p���ԗ������.�����敪=="����"�̏ꍇ�́A�ȉ��̏������X�b�L�v��for�������s����B
                if (chargeInfo[i].invalidDiv)
                {
					log.debug("7-0) chargeInfo["+i+"] �����敪�������@@�ȉ��̏������X�L�b�v");
                	continue;
                }
                
                //7-1) this.���p���ԗ������.���p���ԒP�ʋ敪==null�̏ꍇ�A
                if (chargeInfo[i].chargeDiv == null || "".equals(chargeInfo[i].chargeDiv.trim()))
                {
                    log.debug("7-1)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00897,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //7-2) this.���p���ԗ������.���p����==null�̏ꍇ�A��O���X���[����
                if (chargeInfo[i].chargePeriod == null || "".equals(chargeInfo[i].chargePeriod.trim()))
                {
                    log.debug("7-2)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00898,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //7-3) this.���p���ԗ������.���p���Ԃ̌���==0�̏ꍇ�A��O���X���[����B
                if (chargeInfo[i].chargePeriod.length() == 0)
                {
                    log.debug("7-3)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00899,
                        getClass().getName() + STR_METHOD_NAME);
                }
                
                //��Q�Ή� NO_01994
				//7-3-1) this.���p���ԗ������.���p���ԁ��P�̏ꍇ�A��O���X���[����B
				if (!WEB3StringTypeUtility.isNumber(chargeInfo[i].chargePeriod) ||
					 Integer.parseInt(chargeInfo[i].chargePeriod) < 1)
				{
					log.debug("7-3-1)");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00897,
						getClass().getName() + STR_METHOD_NAME);
				}

                //7-4) this.���p���ԗ������.���p���Ԃ̌�����2���̏ꍇ�A��O���X���[����B
                if (chargeInfo[i].chargePeriod.length() > 2)
                {
                    log.debug("7-4)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00905,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //7-5) this.���p���ԗ������.���p����==null�̏ꍇ�A��O���X���[����B
                if (chargeInfo[i].chargeAmt == null || "".equals(chargeInfo[i].chargeAmt.trim()))
                {
                    log.debug("7-5)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00906,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //7-6) this.���p���ԗ������.���p�����̌�����9���̏ꍇ�A��O���X���[����
                if (chargeInfo[i].chargeAmt.length() > 9)
                {
                    log.debug("7-6)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00907,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //8) ���p���ԒP�ʋ敪�A���p���Ԃ̃`�F�b�N<BR>
        //8-1) this.���I�敪=="��"�ł���A����this.���p����!=null�̏ꍇ�ł���
        //����this.���p���ԒP�ʋ敪==null�̏ꍇ�A��O���X���[����
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (this.trialPeriod != null && trialPeriod.trim().length() != 0) &&
            (this.trialDiv == null || "".equals(trialDiv.trim())))
        {
            log.debug("8-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00910,
                getClass().getName() + STR_METHOD_NAME);
        }

        //8-2) this.���I�敪=="��"�ł���Athis.���p����!=null�ł���A����this.���p���Ԃɐ��l�ȊO���ݒ肳��Ă���ꍇ�A
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            this.trialPeriod != null && !WEB3StringTypeUtility.isNumber(this.trialPeriod))
        {
            log.debug("8-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00912,
                getClass().getName() + STR_METHOD_NAME);
        }

        //8-3) this.���I�敪=="��"�ł���Athis.���p���ԁ�0�̏ꍇ�A��O���X���[����
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            this.trialPeriod != null && Integer.parseInt(this.trialPeriod) <= 0)
        {
            log.debug("8-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00913,
                getClass().getName() + STR_METHOD_NAME);
        }

        //8-4) this.���I�敪=="��"�ł���Athis.���p���ԁ�2���̏ꍇ�A��O���X���[����B
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            this.trialPeriod != null && this.trialPeriod.length() > 2)
        {
            log.debug("8-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00914,
                getClass().getName() + STR_METHOD_NAME);
        }

        //8-5) this.���I�敪=="��"�ł���Athis.���p���ԒP�ʋ敪!=null����<
        //this.���p����!=null�ł���A����this.���p���ԒP�ʋ敪�Ɉȉ��̒l�ȊO���Z�b�g����Ă���ꍇ
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (this.trialDiv != null && trialDiv.trim().length() != 0) &&
            (this.trialPeriod != null && trialPeriod.trim().length() != 0) &&
            !WEB3SrvUsePeriodDivDef.YEAR.equals(this.trialDiv) &&
            !WEB3SrvUsePeriodDivDef.MONTH.equals(this.trialDiv) &&
            !WEB3SrvUsePeriodDivDef.DATE.equals(this.trialDiv))
        {
            log.debug("8-5)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00910,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9) �\���\���ԁi��-���j�̃`�F�b�N
        //9-1) this.���I�敪!="��"�ł���A����this.�\���\���ԁi���j==null�ł���ꍇ�A
        //����this.�\���\���ԁi���j!=null�̏ꍇ�A��O���X���[����B
        if (!WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (this.applyAbleStartDate == null || "".equals(applyAbleStartDate.trim())) &&
            (this.applyAbleEndDate != null && applyAbleEndDate.trim().length() != 0))
        {
            log.debug("9-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00916,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-2) this.���I�敪!="��"�ł���A����this.�\���\���ԁi���j!=null�ł���ꍇ�A
        //����this.�\���\���ԁi���j==null�̏ꍇ�A��O���X���[����B<BR>
        if (!WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (this.applyAbleStartDate != null && applyAbleStartDate.trim().length() != 0) &&
            (this.applyAbleEndDate == null || "".equals(applyAbleEndDate.trim())))
        {
            log.debug("9-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00916,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-3) this.�\���\���ԁi���j!=null�ł���A���\���\���ԁi���j!=null�ł���A
        //����this.�\���\���ԁi���j���\���\���ԁi���j�̏ꍇ�A��O���X���[����B
        if ((this.applyAbleStartDate != null && applyAbleStartDate.trim().length() != 0) &&
            (this.applyAbleEndDate != null && applyAbleEndDate.trim().length() != 0) &&
            Integer.parseInt(this.applyAbleStartDate) > Integer.parseInt(this.applyAbleEndDate))
        {
            log.debug("9-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00916,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-4) this.�\���\���ԁi���j==null�ȊO�ł���A���l�����l�ȊO�̏ꍇ�A
        if ((this.applyAbleStartDate != null && applyAbleStartDate.trim().length() != 0) &&
            !WEB3StringTypeUtility.isNumber(this.applyAbleStartDate))
        {
            log.debug("9-4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00919,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-5) this.�\���\���ԁi���j==null�ȊO�ł���A���l�����l�ȊO�̏ꍇ�A
        if ((this.applyAbleEndDate != null && applyAbleEndDate.trim().length() != 0) &&
            !WEB3StringTypeUtility.isNumber(this.applyAbleEndDate))
        {
            log.debug("9-5)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00920,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-6) this.�\���\���ԁi���j==null�ȊO�ł���A���l��0�ł͂Ȃ��ꍇ�A
        if ((this.applyAbleStartDate != null && applyAbleStartDate.trim().length() != 0) &&
            Integer.parseInt(this.applyAbleStartDate) <= 0)
        {
            log.debug("9-6)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00921,
                getClass().getName() + STR_METHOD_NAME);
        }

        //9-7) this.�\���\���ԁi���j==null�ȊO�ł���A���l��0�ł͂Ȃ��ꍇ�A
        if ((this.applyAbleEndDate != null && applyAbleEndDate.trim().length() != 0) &&
            Integer.parseInt(this.applyAbleEndDate) <= 0)
        {
            log.debug("9-7)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00922,
                getClass().getName() + STR_METHOD_NAME);
        }

        //10) �����Ώۊ��Ԃ̃`�F�b�N
        //10-1) �����Ώۊ���!=null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
        if (this.freeTargetPeriod != null)
        {
            // 10-1-1) this.�����Ώۊ��Ԃɐ��l�ȊO���i�[����Ă���ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isNumber(this.freeTargetPeriod))
            {
                log.debug("�����Ώۊ��Ԃ����l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02802,
                    getClass().getName() + STR_METHOD_NAME);
            }

            // 10-1-2) this.�����Ώۊ��ԁ�2���̏ꍇ�A��O���X���[����B
            if (this.freeTargetPeriod.length() > 2)
            {
                log.debug("�����Ώۊ��Ԃ̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02803,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
            // 10-1-3) this.�����Ώۊ��ԁ�'1' �̏ꍇ�A��O���X���[����B
            if (Integer.parseInt(this.freeTargetPeriod) < 1)
            {
                log.debug("�����Ώۊ��Ԃ�0�ȉ��̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02822,
                    getClass().getName() + STR_METHOD_NAME); 
            }
        }
        
        //10-2) this.�񋟌`��="�����񋟂̂�(�E�c�~��)"�j���́Athis.�񋟌`��="�L���^������(�E�c�~��)"
		//�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
		if (WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType) || 
			WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY__UTUMIYA.equals(this.offerType))
		{
			//10-2-1) �����Ώۊ��� ==null �̏ꍇ�A��O���X���[����B
			if (this.freeTargetPeriod == null)
			{
				log.debug("�����Ώۊ��Ԃ���͂��Ă��������B");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02802,
					getClass().getName() + STR_METHOD_NAME);

			}
		}

        //10) ���ӏ������̃`�F�b�N<BR>
        //10-1) this.�\���敪=="�v" && this.�X�e�[�^�X!="��~��"�ł���Athis.���ӏ�����=null�̏ꍇ�A��O���X���[����
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            !WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            this.consentSentence == null)
        {
            log.debug("10)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01860,
                getClass().getName() + STR_METHOD_NAME,
                "�X�e�[�^�X!=��~���ł���Athis.���ӏ�����=null�̏ꍇ");
        }

		//10-2)
        //this.�\���敪=="�s�v"�ł���Athis.���ӏ�����!=null�̏ꍇ�A��O���X���[����B
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            (this.consentSentence != null && consentSentence.trim().length() != 0))
        {
            log.debug("10)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00923,
                getClass().getName() + STR_METHOD_NAME);
        }

        //11-1) �T�[�r�X���e�̃`�F�b�N
        //this.�\���敪=="�s�v"�ł���Athis.�T�[�r�X���e!=null�̏ꍇ�A��O���X���[����B
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            (this.serviceContent != null && serviceContent.trim().length() != 0))
        {
            log.debug("11)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00924,
                getClass().getName() + STR_METHOD_NAME);
        }
        
		//�d�l�ύX NO_196
        //11-2) 
		//this.�T�[�r�X���e!=null�ł���A�T�[�r�X���e�̃T�C�Y�����S�O�O�O�̏ꍇ�A��O���X���[����B
		if (this.serviceContent != null && this.serviceContent.trim().length() != 0 &&
			WEB3StringTypeUtility.getByteLength(this.serviceContent) > 4000)
		{
			log.debug("11-2) �y��O�����I�I�z�T�[�r�X���e �� 4000byte�ȏ�");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}

        //12-1) �T�[�r�X�����iURL�j�̃`�F�b�N
        //this.�\���敪=="�s�v"�ł���Athis.�T�[�r�X�����iURL�j!=null�̏ꍇ�A��O���X���[����B
        if (WEB3SrvRegiOfferingDivDef.NOT_REQUIRE.equals(this.applyDiv) &&
            (this.explainURL != null && explainURL.trim().length() != 0))
        {
            log.debug("12)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00925,
                getClass().getName() + STR_METHOD_NAME);
        }
        
		//�d�l�ύX NO_196
		//12-2) 
		//this.�T�[�r�X����(URL)!=null�ł���A�T�[�r�X����(URL).length()����256�̏ꍇ�A��O���X���[����B
		if (this.explainURL != null && this.explainURL.trim().length() != 0 &&
			WEB3StringTypeUtility.getByteLength(this.explainURL) > 256)
		{
			log.debug("12-2) �y��O�����I�I�z�T�[�r�X����(URL) �� 256byte�ȏ�");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}

        //13) ���[���敪�i�m�F���[���j�̃`�F�b�N<BR>
        //13-1) this.�\���敪=="�v"�ł���Athis.���[���敪�i�m�F���[���j==null�̏ꍇ
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.confirmMailDiv == null || "".equals(confirmMailDiv.trim())))
        {
            log.debug("13-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00926,
                getClass().getName() + STR_METHOD_NAME);
        }

        //13-2) this.���[���敪�i�m�F���[���j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if (this.confirmMailDiv != null &&
            !WEB3SrvRegiMailDivDef.NOT_SEND_MAIL.equals(this.confirmMailDiv) &&
            !WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(this.confirmMailDiv))
        {
            log.debug("13-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00927,
                getClass().getName() + STR_METHOD_NAME);
        }

        //14) ���[���敪�i�_��������[���j�̃`�F�b�N
        //14-1) this.�\���敪=="�v"�ł���Athis.���[���敪�i�_��������[���j==null�̏ꍇ�A
        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.applyDiv) &&
            (this.noticeMailDiv == null || "".equals(noticeMailDiv.trim())))
        {
            log.debug("14-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00928,
                getClass().getName() + STR_METHOD_NAME);
        }

        //14-2) this.���[���敪�i�_��������[���j���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if (this.noticeMailDiv != null &&
            !WEB3SrvRegiMailDivDef.NOT_SEND_MAIL.equals(this.noticeMailDiv) &&
            !WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(this.noticeMailDiv))
        {
            log.debug("14-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00929,
                getClass().getName() + STR_METHOD_NAME);
        }

        //15) ���[�����M���i�_��������[���j�̃`�F�b�N
        //this.���[���敪�i�_��������[���j=="���M����"�ł���A����
        //this.���[�����M���i�_��������[���j==null�̏ꍇ�A��O���X���[����B
        if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(this.noticeMailDiv) &&
            (this.noticeMailDate == null || "".equals(noticeMailDate.trim())))
        {
            log.debug("15)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00930,
                getClass().getName() + STR_METHOD_NAME);
        }

        // 16) ��W���ԏ��̃`�F�b�N
        //this.��W���ԏ��̌������A�ȉ��̃`�F�b�N���s��
        //(this.�X�e�[�^�X!="��~��" or this.�X�e�[�^�X != null)�ł���A����
        //��W���ԏ�� == null �ȊO�̏ꍇ�ɍs���B
        if (!WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            (this.serviceStatus != null && serviceStatus.trim().length() != 0) &&
             (applyInfo != null && applyInfo.length != 0))
        {
			log.debug("��W���ԏ��̃`�F�b�N");
            int l_intLength = this.applyInfo.length;
            for (int i = 0; i < l_intLength; i++)
            {
				//�d�l�ύX NO_197
            	//16-0) this.�����敪=="����"�̏ꍇ�A�ȉ��̏������X�L�b�v������for�������s����B
            	if (applyInfo[i].invalidDiv)
            	{
					log.debug("16-0) applyInfo["+i+"] �����敪�������@@�ȉ��̏������X�L�b�v");
            		continue;
            	}
            	
				//16-1) this.���I�敪=="��"�ł���Athis.��W���ԏ��!=null�̏ꍇ�A��O���X���[����B
				if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
					this.applyInfo != null)
				{
					log.debug("16-1)");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00931,
						getClass().getName() + STR_METHOD_NAME);
				}
            	
                //16-2) this.�^�p�敪==null�̏ꍇ�A��O���X���[����B
                if (this.applyInfo[i].useDiv == null || "".equals(applyInfo[i].useDiv.trim()))
                {
                    log.debug("16-2)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00932,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-3) this.�^�p�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
                if (!WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv) &&
                    !WEB3InvestDivDef.USUAL_SELECTION_LOT.equals(this.applyInfo[i].useDiv) &&
                    !WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(this.applyInfo[i].useDiv))
                {
                    log.debug("16-3)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00933,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-4) this.��W���ԏ��̐\�����ԁi���j==null�̏ꍇ�A��O���X���[����B
                if (this.applyInfo[i].applyStartDate == null)
                {
                    log.debug("16-4)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00934,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-5) this.��W���ԏ��̐\�����ԁi���j==null�̏ꍇ�A��O���X���[����B
                if (this.applyInfo[i].applyEndDate == null)
                {
                    log.debug("16-5)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00934,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-6) this.��W���ԏ��̓K�p�J�n��==null�̏ꍇ�A��O���X���[����B
                if (this.applyInfo[i].trialStartDate == null)
                {
                    log.debug("16-6)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00936,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-7) this.��W���ԏ��̓K�p�I����==null�̏ꍇ�A��O���X���[����B
                if (this.applyInfo[i].trialEndDate == null)
                {
                    log.debug("16-7)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00937,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-8) this.��W���ԏ��.��W�g==null�ȊO�ł���A�����l�ȊO���Z�b�g����Ă���ꍇ
                if ((this.applyInfo[i].applyMax != null && applyInfo[i].applyMax.trim().length() != 0) &&
                    !WEB3StringTypeUtility.isNumber(this.applyInfo[i].applyMax))
                {
                    log.debug("16-8)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00939,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-9) this.��W���ԏ��.��W�g�̌�����7���̏ꍇ�A��O���X���[����B <BR>
                if (this.applyInfo[i].applyMax != null && this.applyInfo[i].applyMax.length() > 7)
                {
                    log.debug("16-9)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01861,
                        getClass().getName() + STR_METHOD_NAME,
                        "��W���ԏ��.��W�g�̌�����7���̏ꍇ");
                }

                //16-10) this.��W���ԏ��.�^�p�敪��"���������I"
                //�ł���A����this.��W���ԏ��.��W�g!=null�̏ꍇ�A��O���X���[����B
                if(WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv) &&
                    (this.applyInfo[i].applyMax != null && applyInfo[i].applyMax.trim().length() != 0))
                {
                    log.debug("16-10)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00940,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-11) this.��W���ԏ��.�^�p�敪!="���������I" &&
                //  this.��W���ԏ��.��W�g==null�̏ꍇ�A��O���X���[����B
                if (!WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv) &&
                    (this.applyInfo[i].applyMax == null || "".equals(applyInfo[i].applyMax.trim())))
                {
                    log.debug("16-11)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00941,
                        getClass().getName() + STR_METHOD_NAME);
                }
                
				//16-12) this.��W���ԏ��.�^�p�敪!="���������I" && 
				//		 this.��W���ԏ��̒��I����null�̏ꍇ�A��O���X���[����B
				//U00871 start
				//��Q�Ή�  NO_U01708
				if (!WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv) &&
				   this.applyInfo[i].lotteryDate == null)
				{
					log.debug("16-12)");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00950,
						getClass().getName() + STR_METHOD_NAME);
				}
				//U00871 end

                //16-13) this.���I�敪=="�L"�ł���A����this.��W���ԏ��̗��p����==null�̏ꍇ
                //U00871 start
                if ((this.applyInfo[i].chargeAmt == null || "".equals(applyInfo[i].chargeAmt.trim())))
                {
                    log.debug("16-13)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01030,
                        getClass().getName() + STR_METHOD_NAME);
                }
                //U00871 end

                //16-14) this.��W���ԏ��.�^�p�敪=="���������I" or "�ʏ�^�p�i���I�L�j"
                //�ł���A����this.��W���ԏ��.���D�P��==null�ȊO�̏ꍇ�A��O���X���[����B
                //�T�[�r�X���p�z�d�l�ύX�Ǘ��䒠 No:107
                if ((WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv) ||
                    WEB3InvestDivDef.USUAL_SELECTION_LOT.equals(this.applyInfo[i].useDiv)) &&
                    this.applyInfo[i].biddingPriceUnit != null && applyInfo[i].biddingPriceUnit.trim().length() != 0)
                {
                    log.debug("16-14)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00943,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-15) this.��W���ԏ��.�^�p�敪=="�ʏ�^�p�i���I�L�I�[�N�V�����j"�ł���A
                //����this.��W���ԏ��.���D�P��==null�̏ꍇ�A��O���X���[����B
                if (WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(this.applyInfo[i].useDiv) &&
                    (this.applyInfo[i].biddingPriceUnit == null || "".equals(applyInfo[i].biddingPriceUnit.trim())))
                {
                    log.debug("16-15)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00944,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-16) this.��W���ԏ��̏o������this.��W���ԏ��̒��I���̏ꍇ�A
                if (WEB3DateUtility.compareToSecond(this.applyInfo[i].paymentDate, this.applyInfo[i].lotteryDate) <= 0)
                {
                    log.debug("16-16)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00947,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-17) this.��W���ԏ��̐\�����ԁi���j��this.��W���ԏ��̐\�����ԁi���j�̏ꍇ�A
                if (WEB3DateUtility.compareToSecond(this.applyInfo[i].applyStartDate, this.applyInfo[i].applyEndDate) > 0)
                {
                    log.debug("16-17)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00934,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //16-18) this.��W���ԏ��̓K�p�J�n����this.��W���ԏ��̓K�p�I�����̏ꍇ
                if (WEB3DateUtility.compareToSecond(this.applyInfo[i].trialStartDate, this.applyInfo[i].trialEndDate) > 0)
                {
                    log.debug("16-18)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00949,
                        getClass().getName() + STR_METHOD_NAME);
                }
				
				//��Q�Ή� NO_U02019
				//��Q�Ή� 05/27���������I���̑Ή�
				if (!(WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(this.applyInfo[i].useDiv)))
				{
					//16-19)this.��W���ԏ��̐\�����ԁi���j>this.��W���ԏ��.���I���̏ꍇ
					if (WEB3DateUtility.compareToSecond(this.applyInfo[i].applyEndDate, this.applyInfo[i].lotteryDate) > 0)
					{
						log.debug("16-19)");
						log.exiting(STR_METHOD_NAME);
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_00951,
							getClass().getName() + STR_METHOD_NAME);
					}

					//16-20) this.��W���ԏ��̓K�p�J�n����this.��W���ԏ��.���I���̏ꍇ
					if (WEB3DateUtility.compareToSecond(this.applyInfo[i].trialStartDate, this.applyInfo[i].lotteryDate) < 0)
					{
						log.debug("16-20)");
						log.exiting(STR_METHOD_NAME);
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_00952,
							getClass().getName() + STR_METHOD_NAME);
					}
				}
            }
        }
        //* 17) �񋟌`���̃`�F�b�N
        //this.�񋟌`��!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        //  �E�����񋟂̂�  
        //�@@�E�L���^������  
        //�@@�E�����񋟂̂�(�E�c�~��) 
        //�@@�E�L���^������(�E�c�~��) 
        if ((this.offerType != null && offerType.trim().length() != 0) &&
            !WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) &&
            !WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY.equals(this.offerType) &&
            !WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType) &&
            !WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY__UTUMIYA.equals(this.offerType))
        {
            log.debug("17)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01180,
                getClass().getName() + STR_METHOD_NAME);
        }

        //18) �萔����������v�z�̃`�F�b�N
        //18-1) this.�萔����������v�z!=null�ł���A���l�����l�ȊO�̏ꍇ�A��O���X���[����B
        if ((this.commissionAttainTotal != null && commissionAttainTotal.trim().length() != 0) &&
            !WEB3StringTypeUtility.isNumber(this.commissionAttainTotal))
        {
            log.debug("18-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01181,
                getClass().getName() + STR_METHOD_NAME);
        }
        //18-2) this.�񋟌`��!=null�ł���A����this.�萔����������v�z=null�̏ꍇ�A
        if ((this.offerType != null && offerType.trim().length() != 0) && this.commissionAttainTotal == null)
        {
            log.debug("18-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01182,
                getClass().getName() + STR_METHOD_NAME);
        }
        //18-3) this.�񋟌`��!=null�ł���A����this.�萔����������v�z��0�̏ꍇ�A
        if ((this.offerType != null && offerType.trim().length() != 0) && Integer.parseInt(this.commissionAttainTotal) <= 0)
        {
            log.debug("18-3)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01183,
                getClass().getName() + STR_METHOD_NAME);
        }

        //19) �K�p�萔�������̃`�F�b�N
        // 19-1) this.�X�e�[�^�X!="��~��"�ł���A����this.�񋟌`��!=null�ł���A
        //����this.�K�p�萔������=null�܂��͗v�f��=0�̏ꍇ�A
        //�܂���this.�X�e�[�^�X!="��~��"�ł���A����this.�񋟌`��!=null�ł���A
        //this.�K�p�萔�������̖����敪���S��"����"�̏ꍇ�A
        //��O���X���[����B

        boolean l_blnIsInvaild = false;
        if (this.applyCommissionConditions != null)
        {
            int l_intCnt = this.applyCommissionConditions.length;
            if (l_intCnt > 0)
            {
                int l_intFlag = 0;
                for (int i = 0; i < l_intCnt; i++)
                {
                    if (this.applyCommissionConditions[i].invalidDiv)
                    {
                        l_intFlag++;
                    }
                }
                if (l_intFlag == l_intCnt)
                {
                    l_blnIsInvaild = true;
                }
            }
        }
        if ((!WEB3SrvStatusDef.STOP.equals(this.serviceStatus)) &&
            (this.offerType != null && offerType.trim().length() != 0) &&
            ((this.applyCommissionConditions == null ||
            this.applyCommissionConditions.length == 0) ||
            l_blnIsInvaild))
        {
            log.debug("19-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01184,
                getClass().getName() + STR_METHOD_NAME);
        }
        //19-2) this.�X�e�[�^�X!="��~��"�ł���A����this.�񋟌`��=null�ł���A
        // ����this.�K�p�萔������!=null���v�f��>0�ł���A
        // ����this.�K�p�萔�������̖����敪���S��"����"�ł͂Ȃ��ꍇ�A��O���X���[����B
        //WEB3-SRVREGI-A-CD-0131
        if ((!WEB3SrvStatusDef.STOP.equals(this.serviceStatus)) &&
            (this.offerType == null || this.offerType.trim().length() == 0) &&
            (this.applyCommissionConditions != null && this.applyCommissionConditions.length > 0) &&
            !l_blnIsInvaild)
        {
            log.debug("19-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01185,
                getClass().getName() + STR_METHOD_NAME);
        }
        //19-3) this.�K�p�萔�������̌������A�ȉ��̃`�F�b�N���s���B
        //19-3-1) this.�K�p�萔������.���i���ދ敪=null�̏ꍇ�A��O���X���[����B
        if (applyCommissionConditions != null)
        {
            int l_intLength = this.applyCommissionConditions.length;
            for (int i = 0; i < l_intLength; i++)
            {
                if (applyCommissionConditions[i].productKindDiv == null ||
                    applyCommissionConditions[i].productKindDiv.trim().length() == 0)
                {
                    log.debug("19-3-1)");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01186,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //20) �X�e�[�^�X�A���p���ԗ������A��W���ԏ��̃`�F�b�N
        //20-1) this.�X�e�[�^�X!="��~��"�ł���A����this.���I�敪="��" && (this.�񋟌`��!="�����񋟂̂�"
        //      ���́Athis.�񋟌`��!="�����񋟂̂�(�E�c�~��)"�j�̏ꍇ 
        if ((!WEB3SrvStatusDef.STOP.equals(this.serviceStatus)) &&
            WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (!WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) &&
            !WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType)))
        {
            //20-1-1) this.���p���ԗ������==0���܂���null�̏ꍇ�A��O���X���[����B
            if (this.chargeInfo == null || this.chargeInfo.length == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01287,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //20-1-2) this.���p���ԗ������̑S�Ă̖����敪=="����"�̏ꍇ�A��O���X���[����B
            int l_int = this.chargeInfo.length;
            int l_intCnt = 0;
            for (int i = 0; i < l_int; i++)
            {
                if (this.chargeInfo[i].invalidDiv)
                {
                    l_intCnt = l_intCnt + 1;
                }
            }
            if (l_intCnt == l_int)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01288,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
        }
        //20-2) this.�X�e�[�^�X!="��~��"�ł���A����this.���I�敪="��" && (this.�񋟌`��="�����񋟂̂�"
        //���́Athis.�񋟌`��="�����񋟂̂�(�E�c�~��)"�j�̏ꍇ
        if ((!WEB3SrvStatusDef.STOP.equals(this.serviceStatus)) &&
            WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.lotteryDiv) &&
            (WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) ||
            WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType)))
        {
			//20-2-1) this.���p���ԗ������>0���A����null�ȊO�̏ꍇ�ŁA
			//this.���p���ԗ������̖����敪����ł��L���̏ꍇ�ɗ�O���X���[����B
			int l_intCnt = 0;
            if(this.chargeInfo != null){
                
                int l_int = this.chargeInfo.length;
                for (int i = 0; i < l_int; i++)
                {
                    if (!this.chargeInfo[i].invalidDiv)
                    {
                        l_intCnt = l_intCnt + 1;
                    }
                }
            }
            
			if ((this.chargeInfo != null && this.chargeInfo.length > 0) && l_intCnt != 0)
			{
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01906,
					getClass().getName() + STR_METHOD_NAME);
			}
        }
        
        //20-3) this.�X�e�[�^�X!="��~��"�ł���A����this.���I�敪="�L"�̏ꍇ
        if ((!WEB3SrvStatusDef.STOP.equals(this.serviceStatus)) &&
            WEB3ConditionsValueDivDef.HAVE.equals(this.lotteryDiv))
        {
            //20-3-1) this.��W���ԏ��==0���܂���null�̏ꍇ�A��O���X���[����B
            if (this.applyInfo == null || this.applyInfo.length == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01289,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //20-3-2) this.��W���ԏ��̑S�Ă̖����敪=="����"�̏ꍇ�A��O���X���[����B
            int l_int = this.applyInfo.length;
            int l_intCnt = 0;
            for (int i = 0; i < l_int; i++)
            {
                if (this.applyInfo[i].invalidDiv)
                {
                    l_intCnt = l_intCnt + 1;
                }
            }
            if (l_intCnt == l_int)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01290,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        //* 21) �n�b�V���v�Z�����敪�̃`�F�b�N <BR>
		//��Q�Ή� NO_U02018
		
        //* 21-1) this.�n�b�V���v�Z�����敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
        //* �@@�@@�E�w�薳 <BR>
        //* �@@�@@�EMD2 <BR>
        //* �@@�@@�EMD5 <BR>
        //* �@@�@@�ESHA-1 <BR>
        //* �@@�@@�ESHA-256 <BR>
        //* �@@�@@�ESHA-384 <BR>
        //* �@@�@@�ESHA-512<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.MD2.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.MD5.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_1.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_256.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_384.equals(this.hashCalHowToDiv) &&
            !WEB3HashCalHowToDivDef.SHA_512.equals(this.hashCalHowToDiv))
        {
            log.debug("�n�b�V���v�Z�����敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01842,
                getClass().getName() + STR_METHOD_NAME,
                "�n�b�V���v�Z�����敪�̒l���s��");
        }

        //* 22) �n�b�V���v�Z�菇�敪�̃`�F�b�N <BR>
		//��Q�Ή� NO_U02018
		
        //22-1) this.�n�b�V���v�Z�菇�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
        // * �@@�@@�E�w�薳 <BR>
        //* �@@�@@�E�d�q�� <BR>
        //* �@@�@@�E�ʏ�v�Z�i�P�j <BR>
        //* �@@�@@�E�ʏ�v�Z�i�Q�j<BR>
        //* �@@�@@�E�Q�i�K�v�Z<BR>
        //* �@@�@@�E���O�C���F�� <BR>
        //*     �E�V���O���T�C���I���A�g <BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.ELE_PIGEON.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.NORMAL1.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.NORMAL2.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.TWO_STEP_CALCULATION.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.LOGIN_CERTIFICATION.equals(this.hashCalOrderDiv) &&
            !WEB3SrvRegiHashCalOrderDivDef.SINGLE_SIGNON_COOPERATION.equals(this.hashCalOrderDiv))
        {
            log.debug("�n�b�V���v�Z�菇�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01844,
                getClass().getName() + STR_METHOD_NAME,
                "�n�b�V���v�Z�菇�敪�̒l���s��");
        }

        //* 23) ���M���@@�敪�̃`�F�b�N <BR>
		//��Q�Ή� NO_U02018

        //* 23-1) this.���M���@@�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
        //* �@@�@@�EGET <BR>
        //* �@@�@@�EPOST <BR>
        //* �@@�@@�EHTTP-REQUEST <BR>
        //* �@@�@@�E����i�P�j�|���e���N���A�،� MULTEX ��p <BR>
        //* �@@�@@�E����i�Q�j�|���e���N���A�،� ���o�e���R��21 ��p<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.GET.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.POST.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.HTTP_REQUEST.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.SPECIAL1.equals(this.sendHowToDiv) &&
            !WEB3SrvRegiSendHowToDivDef.SPECIAL2.equals(this.sendHowToDiv))
        {
            log.debug("���M���@@�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01846,
                getClass().getName() + STR_METHOD_NAME,
                "���M���@@�敪�̒l���s��");
        }

        //* 24) ���M�p�����[�^�敪�̃`�F�b�N <BR>
		//��Q�Ή� NO_U02018

        //* 24-1) this.���M�p�����[�^�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
        //* �@@�@@�E�� <BR>
        //* �@@�@@�E�L<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.sendParamDiv) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.sendParamDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.sendParamDiv))
        {
            log.debug("���M�p�����[�^�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01848,
                getClass().getName() + STR_METHOD_NAME,
                "���M�p�����[�^�敪�̒l���s��");
        }

        //* 25) �Í����ڋq�R�[�h�敪�̃`�F�b�N <BR>
		//��Q�Ή� NO_U02018
		

        //* 25-1) this.�Í����ڋq�R�[�h�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
        //* �@@�@@�E�� <BR>
        //* �@@�@@�E�L<BR>
        if (!WEB3StringTypeUtility.isEmptyOrBlank(this.cryptAccountCodeDiv) &&
            !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.cryptAccountCodeDiv) &&
            !WEB3ConditionsValueDivDef.HAVE.equals(this.cryptAccountCodeDiv))
        {
            log.debug("�Í����ڋq�R�[�h�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01850,
                getClass().getName() + STR_METHOD_NAME,
                "�Í����ڋq�R�[�h�敪�̒l���s��");
        }

        //* 26) �n�b�V���l�ꗗ�̃`�F�b�N <BR>
        //* �@@�n�b�V���l�ꗗ!=null�ł���A���v�f��>0���̏ꍇ�A�ȉ��̃`�F�b�N�����{����B<BR>
        if (this.hashList != null && this.hashList.length > 0)
        {
            int l_int = this.hashList.length;
            for (int i = 0; i < l_int; i++)
            {
                //26-1) �n�b�V���l�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����B<BR>
                if (hashList[i].keyKindDiv == null)
                {
                    log.debug("�n�b�V���l�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01851,
                        getClass().getName() + STR_METHOD_NAME,
                        "�n�b�V���l�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����");
                }

                //26-2) �n�b�V���l�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����B <BR>
                if (hashList[i].key == null)
                {
                    log.debug("�n�b�V���l�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01852,
                        getClass().getName() + STR_METHOD_NAME,
                        "�n�b�V���l�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����");
                }
                
				//�d�l�ύX NO_196
				//26-3)�n�b�V���l�ꗗ.���p�L�[�̃o�C�g����256�o�C�g���ȏ�̏ꍇ�A��O���X���[����B<BR>
				if ((hashList[i].key != null && hashList[i].key.trim().length() != 0) && 
					WEB3StringTypeUtility.getByteLength(hashList[i].key) > 256)
				{
					log.debug("26-3) �n�b�V���l�ꗗ.���p�L�[��256byte�ȏ�");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_01831,
						getClass().getName() + STR_METHOD_NAME);
				}
            }

        }

        //* 27) ���M�p�����[�^�ꗗ�̃`�F�b�N <BR>
        //* �@@���M�p�����[�^�ꗗ!=null�ł���A���v�f��>0���̏ꍇ�A�ȉ��̃`�F�b�N�����{����B<BR>
        if (this.paramList != null && this.paramList.length > 0)
        {
            int l_int = this.paramList.length;
            for (int i = 0; i < l_int; i++)
            {
                //27-1) ���M�p�����[�^�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����B <BR>
                if (paramList[i].keyKindDiv == null)
                {
                    log.debug("���M�p�����[�^�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01853,
                        getClass().getName() + STR_METHOD_NAME,
                        "���M�p�����[�^�ꗗ.���p�L�[��ʋ敪==null�̏ꍇ�A��O���X���[����");
                }

                //27-2) ���M�p�����[�^�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����B<BR>
                if (paramList[i].key == null)
                {
                    log.debug("���M�p�����[�^�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01854,
                        getClass().getName() + STR_METHOD_NAME,
                        "���M�p�����[�^�ꗗ.���p�L�[==null�̏ꍇ�A��O���X���[����");
                }
                
				//�d�l�ύX NO_196
				//27-3)���M�p�����[�^�ꗗ.���p�L�[�̃o�C�g����256�o�C�g���ȏ�̏ꍇ�A��O���X���[����B<BR>
				if ((paramList[i].key != null && paramList[i].key.trim().length() != 0) && 
					 WEB3StringTypeUtility.getByteLength(paramList[i].key) > 256)
				{
					log.debug("27-3) ���M�p�����[�^�ꗗ.���p�L�[��256byte�ȏ�");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_01859,
						getClass().getName() + STR_METHOD_NAME);
				}
            }
        }

		//��Q�Ή� NO_2018
        //28) �n�b�V���v�Z�����敪�A�n�b�V���v�Z�菇�敪�̃`�F�b�N <BR>
        //	(this.�n�b�V���v�Z�����敪="null" and this.�n�b�V���v�Z�菇�敪="null")�ł���ꍇ�Ɉȉ��̃`�F�b�N���s��Ȃ��B
        if(!(this.hashCalHowToDiv == null && this.hashCalOrderDiv == null))
        {
	        //* 28-1)this.�n�b�V���v�Z�����敪!="�w�薳"�ł���A����this.�n�b�V���v�Z�菇�敪="�w�薳"<BR>
	        if (!WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
	            WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv))
	        {
	            log.debug("�n�b�V���v�Z�����敪!=�w�薳�ł���A����this.�n�b�V���v�Z�菇�敪=�w�薳");
	            log.exiting(STR_METHOD_NAME);
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_01855,
	                getClass().getName() + STR_METHOD_NAME,
	                "�n�b�V���v�Z�����敪!=�w�薳�ł���A����this.�n�b�V���v�Z�菇�敪=�w�薳");
	        }
	
	        //* 28-2) this.�n�b�V���v�Z�����敪="�w�薳"�ł���A����this.�n�b�V���v�Z�菇�敪!="�w�薳"<BR>
	        if (WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
	            !WEB3SrvRegiHashCalOrderDivDef.DEFAULT.equals(this.hashCalOrderDiv))
	        {
	            log.debug("�n�b�V���v�Z�����敪=�w�薳�ł���A����this.�n�b�V���v�Z�菇�敪!=�w�薳");
	            log.exiting(STR_METHOD_NAME);
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_01855,
	                getClass().getName() + STR_METHOD_NAME,
	                "�n�b�V���v�Z�����敪=�w�薳�ł���A����this.�n�b�V���v�Z�菇�敪!=�w�薳");
	        }
        }

		//��Q�Ή� NO_2018
        //* 29) �X�e�[�^�X�A�n�b�V���v�Z�����敪�A�n�b�V���l�ꗗ�̃`�F�b�N <BR>
		//	(this.�n�b�V���v�Z�����敪="null" and this.�n�b�V���l�ꗗ="null")�ł���ꍇ�Ɉȉ��̃`�F�b�N���s��Ȃ��B
		if(!(this.hashCalHowToDiv == null && this.hashList == null))
		{
	        //* �@@this.�X�e�[�^�X!="��~��"�̏ꍇ�ɂ̂݁A�ȉ��̃`�F�b�N�����{����B <BR>
	        if (!WEB3SrvStatusDef.STOP.equals(this.serviceStatus))
	        {
	            //* 29-1) this.�n�b�V���v�Z�����敪!="�w�薳"�ł���A����this.�n�b�V���l�ꗗ=null<BR>
	            //* �@@�@@�܂��͗v�f��==0���̏ꍇ�A��O���X���[����B <BR>
	            if (!WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv) &&
	                (this.hashList == null || this.hashList.length == 0))
	            {
	                log.debug("�n�b�V���v�Z�����敪!=�w�薳�ł���A����this.�n�b�V���l�ꗗ=null");
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3BusinessLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_01856,
	                    getClass().getName() + STR_METHOD_NAME,
	                    "�n�b�V���v�Z�����敪!=�w�薳�ł���A����this.�n�b�V���l�ꗗ=null");
	            }
	            //* 29-2) this.�n�b�V���v�Z�����敪!="�w�薳"�ł���A����this.�n�b�V���l�ꗗ�̑S�Ă�<BR>
	            //* �@@�@@�����敪="����"�̏ꍇ�A��O���X���[����B <BR>
	            if (!WEB3HashCalHowToDivDef.DEFAULT.equals(this.hashCalHowToDiv))
	            {
	                int l_int = hashList.length;
	                int l_intCnt = 0;
	                for (int i = 0; i < l_int; i++)
	                {
	                    if (!hashList[i].invalidDiv)
	                    {
	                        l_intCnt = l_intCnt + 1;
	                    }
	                }
	                if (l_intCnt == 0)
	                {
	                    log.debug("�n�b�V���l�ꗗ�̑S�Ă̖����敪=�����̏ꍇ");
	                    log.exiting(STR_METHOD_NAME);
	                    throw new WEB3BusinessLayerException(
	                        WEB3ErrorCatalog.BUSINESS_ERROR_01857,
	                        getClass().getName() + STR_METHOD_NAME,
	                        "�n�b�V���l�ꗗ�̑S�Ă̖����敪=�����̏ꍇ");
	                }
	            }
	            //* 29-3) this.���M�p�����[�^�敪="�L"�ł���A����this.���M�p�����[�^�ꗗ=null<BR>
	            //* �@@�@@�܂��͗v�f��==0���̏ꍇ�A��O���X���[����B <BR>
	            if (WEB3ConditionsValueDivDef.HAVE.equals(this.sendParamDiv) &&
	                (this.paramList == null || this.paramList.length == 0))
	            {
	                log.debug("���M�p�����[�^�敪=�L�ł���A����this.���M�p�����[�^�ꗗ=null");
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3BusinessLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_01858,
	                    getClass().getName() + STR_METHOD_NAME,
	                    "���M�p�����[�^�敪=�L�ł���A����this.���M�p�����[�^�ꗗ=null");
	            }
	            //* 29-4) this.���M�p�����[�^�敪="�L"�ł���A����this.���M�p�����[�^�ꗗ�̑S�Ă�<BR>
	            //* �@@�@@�����敪="����"�̏ꍇ�A��O���X���[����B<BR>
	            if (WEB3ConditionsValueDivDef.HAVE.equals(this.sendParamDiv))
	            {
	                int l_int = this.paramList.length;
	                int l_intCnt = 0;
	                for (int i = 0; i < l_int; i++)
	                {
	                    if (!paramList[i].invalidDiv)
	                    {
	                        l_intCnt = l_intCnt + 1;
	                    }
	                }
	                if (l_intCnt == 0)
	                {
	                    log.debug("���M�p�����[�^�ꗗ�̑S�Ă̖����敪=�����̏ꍇ");
	                    log.exiting(STR_METHOD_NAME);
	                    throw new WEB3BusinessLayerException(
	                        WEB3ErrorCatalog.BUSINESS_ERROR_01859,
	                        getClass().getName() + STR_METHOD_NAME,
	                        "���M�p�����[�^�ꗗ�̑S�Ă̖����敪=�����̏ꍇ");
	                }
	            }
	        }
        }

        //30) �񋟌`���A���p���ԗ������A���p���Ԃ̃`�F�b�N
		//��Q�Ή� NO_U01997 30-1���폜

        // 30-1) this.�X�e�[�^�X!="��~��" && �ithis.�񋟌`��="�����񋟂̂�" ���́A
        // this.�񋟌`��="�����񋟂̂�(�E�c�~��)"�j &&
        // this.���p���ԗ������!=null && ���p���ԗ������̗v�f����0�� &&
        // this.���p���ԗ������̑S�Ă̖����敪��"����"�ł͂Ȃ������ꍇ�A��O���X���[����B
        if(!WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            (WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) ||
            WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType)) &&
            this.chargeInfo != null && this.chargeInfo.length > 0)
        {
            int l_intInvalidDivCnt = this.chargeInfo.length;
            boolean l_blnInvalidDivFlag = true;
            for (int i = 0; i < l_intInvalidDivCnt; i++)
            {
               if (this.chargeInfo[i].invalidDiv)
               {
                   l_blnInvalidDivFlag = false;
               }
            }
            if (l_blnInvalidDivFlag)
            {
                log.debug("�񋟌`���������񋟂̏ꍇ�́A���p���ԗ�����񂪎w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01906,
                    getClass().getName() + STR_METHOD_NAME,
                    "�񋟌`���������񋟂̏ꍇ�́A���p���ԗ�����񂪎w��s�ł��B");
            }
        }
        
        //30-2) this.�X�e�[�^�X!="��~��"�ł���A(this.�񋟌`��="�����񋟂̂�"
        //      ���́Athis.�񋟌`��="�����񋟂̂�(�E�c�~��)"�j &&
        //    (this.���p���ԒP�ʋ敪!=null�܂���this.���p����!=null)�̏ꍇ�A��O���X���[����B
        if (!WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            (WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) ||
            WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType)) &&
            this.trialDiv != null)
        {
            log.debug("30-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01908,
                getClass().getName() + STR_METHOD_NAME,
                "�񋟌`���������񋟂̏ꍇ�́A���p���ԒP�ʋ敪���w��s�ł��B");
        }

        if (!WEB3SrvStatusDef.STOP.equals(this.serviceStatus) &&
            (WEB3SupplyDivDef.FREE_SUPPLY.equals(this.offerType) ||
            WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(this.offerType)) &&
            this.trialPeriod != null)
        {
            log.debug("30-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01909,
                getClass().getName() + STR_METHOD_NAME,
                "�񋟌`���������񋟂̏ꍇ�́A���p���Ԃ��w��s�ł��B");
        }
        
		//�d�l�ύX NO_196
		//31-1)this.URL�̃o�C�g����256�o�C�g���ȏ�̏ꍇ�A��O���X���[����B<BR>
		if ((url != null && url.trim().length() != 0) &&
			 WEB3StringTypeUtility.getByteLength(url) > 256)
		{
			log.debug("31-1) URL �� 256byte�ȏ�");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}
		
		//�d�l�ύX NO_196
		//32-1)this.URL2�̃o�C�g����256�o�C�g���ȏ�̏ꍇ�A��O���X���[����B<BR>
		if ((url2 != null && url2.trim().length() != 0) &&
			 WEB3StringTypeUtility.getByteLength(url2) > 256)
		{
			log.debug("32-1) URL2 �� 256byte�ȏ�");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01053,
				getClass().getName() + STR_METHOD_NAME);
		}

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 416F49810280
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
