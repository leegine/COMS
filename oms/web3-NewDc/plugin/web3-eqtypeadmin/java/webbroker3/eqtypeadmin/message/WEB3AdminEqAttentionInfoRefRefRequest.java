head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEqAttentionInfoRefRefRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���ӏ��Ɖ�N�G�X�g(WEB3AdminEqAttentionInfoRefRefRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 �И��� (���u) �V�K�쐬 ���f��No.217,���f��No.221-224
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AttentionInfoDivCodeDef;
import webbroker3.common.define.WEB3AttentionInfoTypeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE���ӏ��Ɖ�N�G�X�g)<BR>
 * �Ǘ��ҁE���ӏ��Ɖ�N�G�X�g�N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminEqAttentionInfoRefRefRequest extends WEB3GenRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEqAttentionInfoRefRefRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_eq_attention_info_ref_ref";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812301405L;

    /**
     * (���ӏ����)<BR>
     * ���ӏ����<BR>
     */
    public String attentionInfoType;

    /**
     * (���ӏ��敪�R�[�h)<BR>
     * ���ӏ��敪�R�[�h<BR>
     */
    public String attentionInfoDivCode;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (�L����)<BR>
     * �L����<BR>
     */
    public String validDate;

    /**
     * (��񔭐�����From)<BR>
     * ��񔭐�����From<BR>
     */
    public String infoOccuredDateFrom;

    /**
     * (��񔭐�����To)<BR>
     * ��񔭐�����To<BR>
     */
    public String infoOccuredDateTo;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;

    /**
     * (���ӏ��Ɖ�\�[�g�L�[)<BR>
     * ���ӏ��Ɖ�\�[�g�L�[<BR>
     */
    public WEB3AdminEqAttentionInfoRefSortKey[] sortKeys;

    /**
     * @@roseuid 49588AEF0167
     */
    public WEB3AdminEqAttentionInfoRefRefRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j���ӏ���ʃ`�F�b�N<BR>
     * �@@this.���ӏ���� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P-�P�jthis.���ӏ���ʂɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u���ӏ���ʂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"������"<BR>
     * �@@�@@�@@�@@�E"�l���������"<BR>
     * �@@�@@�@@�@@�E"�t���[�t�H�[�}�b�g"<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03147<BR>
     * <BR>
     * �Q�j���ӏ��敪�`�F�b�N<BR>
     * �@@this.���ӏ��敪�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q-�P�jthis.���ӏ��敪�R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u���ӏ��敪�R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"������~�i������t�j"<BR>
     * �@@�@@�@@�@@�E"������~�i������t�s�j"<BR>
     * �@@�@@�@@�@@�E"������~�i������t�j�̎��"<BR>
     * �@@�@@�@@�@@�E"������~�i������t�s�j�̎��"<BR>
     * �@@�@@�@@�@@�E"������~�i������t�j�̉���"<BR>
     * �@@�@@�@@�@@�E"������~�i������t�s�j�̉���"<BR>
     * �@@�@@�@@�@@�E"������~�i������t�j�̉����̎��"<BR>
     * �@@�@@�@@�@@�E"������~�i������t�s�j�̉����̎��"<BR>
     * �@@�@@�@@�@@�E"�������f"<BR>
     * �@@�@@�@@�@@�E"�������f�̎��"<BR>
     * �@@�@@�@@�@@�E"�������f�̉���"<BR>
     * �@@�@@�@@�@@�E"�������f�̉����̎��"<BR>
     * �@@�@@�@@�@@�E"�V�K�������̏��l���t�����ꍇ"<BR>
     * �@@�@@�@@�@@�E"�t���[�t�H�[�}�b�g"<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03149<BR>
     * <BR>
     * �R�j���ӏ����-���ӏ��敪�R�[�h�̑��փ`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ӏ���� != null�@@����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ӏ��敪�R�[�h != null �̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�R�|�P�jthis.���ӏ���� �� "�t���[�t�H�[�}�b�g" ����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ӏ��敪�R�[�h �� "�t���[�t�H�[�}�b�g"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u���ӏ���ʁ^���ӏ��敪�w�肪�s�����v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03148<BR>
     * <BR>
     * �@@�R�|�Q�jthis.���ӏ���� �� "�l���������" ����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ӏ��敪�R�[�h �� "�V�K�������̏��l���t�����ꍇ"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u���ӏ���ʁ^���ӏ��敪�w�肪�s�����v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03148<BR>
     * <BR>
     * �@@�R�|�R�jthis.���ӏ���� �� "������" �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ӏ��敪�R�[�h �� "�t���[�t�H�[�}�b�g"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���邢�́Athis.���ӏ��敪�R�[�h �� "�V�K�������̏��l���t�����ꍇ"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ł���΁A�u���ӏ���ʁ^���ӏ��敪�w�肪�s�����v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03148<BR>
     * <BR>
     * �S�j�s��`�F�b�N<BR>
     * �@@this.�s��R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S-�P�jthis.�s��R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�@@�E"���"<BR>
     * �@@�@@�@@�@@�E"���É�"<BR>
     * �@@�@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�@@�E"�D�y"<BR>
     * �@@�@@�@@�@@�E"NNM"<BR>
     * �@@�@@�@@�@@�E"JASDAQ"<BR>
     * �@@�@@�@@�@@�E"JNX-PTS"<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * �T�j�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�T-�P�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.�����R�[�h != ����<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.�����R�[�h.length != 5<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02785<BR>
     * <BR>
     * �U�j�L�����`�F�b�N<BR>
     * �@@this.�L���� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�U-�P�jthis.�L�������ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�L�����G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.�L������Date�^�ɕϊ��ł��Ȃ������ꍇ�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������t�H�[�}�b�g�uYYYYMMDD�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03150<BR>
     * <BR>
     * �V�j��񔭐�����From�`�F�b�N<BR>
     * �@@this.��񔭐�����From != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�V-�P�jthis.��񔭐�����From���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u��񔭐�����From�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.��񔭐�����From��Date�^�ɕϊ��ł��Ȃ������ꍇ�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������t�H�[�}�b�g�uYYYYMMDDHH24MISS�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03151<BR>
     * <BR>
     * �W�j��񔭐�����To�`�F�b�N<BR>
     * �@@this.��񔭐�����To != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�W-�P�jthis.��񔭐�����To���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u��񔭐�����To�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.��񔭐�����To��Date�^�ɕϊ��ł��Ȃ������ꍇ�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������t�H�[�}�b�g�uYYYYMMDDHH24MISS�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03152<BR>
     * <BR>
     * �X�j��񔭐�����From/To�������`�F�b�N<BR>
     * �@@this.��񔭐�����From != null�@@���@@this.��񔭐�����To != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�X-�P�jthis.��񔭐�����From > this.��񔭐�����To�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͎��Ԑ������G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01481<BR>
     * <BR>
     * �P�O�j�\�[�g�L�[�`�F�b�N<BR>
     * �@@�P�O�|�P�j�@@this.�\�[�g�L�[ == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�P�O�|�Q�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�P�O�|�Q�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �P�P�j�\���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�P�P-�P�jthis.�\���y�[�W�ԍ� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�\���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�P�P-�Q�jthis.�\���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�\���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�P�P-�R�jthis.�\���y�[�W�ԍ� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�\���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �P�Q�j�y�[�W���\���s���`�F�b�N<BR>
     * �@@�P�Q-�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02224<BR>
     * <BR>
     * �@@�P�Q-�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�P�Q-�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@roseuid 4945DC320281
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j���ӏ���ʃ`�F�b�N
        //�@@this.���ӏ���� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�P-�P�jthis.���ӏ���ʂɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        //�@@�@@�@@�@@�u���ӏ���ʂ�����`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E"������"
        //�@@�@@�@@�@@�E"�l���������"
        //�@@�@@�@@�@@�E"�t���[�t�H�[�}�b�g"
        if (this.attentionInfoType != null)
        {
            if (!WEB3AttentionInfoTypeDef.SELL_STOP_INFO.equals(this.attentionInfoType)
                && !WEB3AttentionInfoTypeDef.LIMIT_RANGE_INFO.equals(this.attentionInfoType)
                && !WEB3AttentionInfoTypeDef.FREE_FORMAT.equals(this.attentionInfoType))
            {
                log.debug("���ӏ���ʂ�����`�̒l�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03147,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ӏ���ʂ�����`�̒l�B");
            }
        }

        //�Q�j���ӏ��敪�`�F�b�N
        //�@@this.���ӏ��敪�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�Q-�P�jthis.���ӏ��敪�R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        //�@@�@@�@@�@@�u���ӏ��敪�R�[�h������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E"������~�i������t�j"
        //�@@�@@�@@�@@�E"������~�i������t�s�j"
        //�@@�@@�@@�@@�E"������~�i������t�j�̎��"
        //�@@�@@�@@�@@�E"������~�i������t�s�j�̎��"
        //�@@�@@�@@�@@�E"������~�i������t�j�̉���"
        //�@@�@@�@@�@@�E"������~�i������t�s�j�̉���"
        //�@@�@@�@@�@@�E"������~�i������t�j�̉����̎��"
        //�@@�@@�@@�@@�E"������~�i������t�s�j�̉����̎��"
        //�@@�@@�@@�@@�E"�������f"
        //�@@�@@�@@�@@�E"�������f�̎��"
        //�@@�@@�@@�@@�E"�������f�̉���"
        //�@@�@@�@@�@@�E"�������f�̉����̎��"
        //�@@�@@�@@�@@�E"�V�K�������̏��l���t�����ꍇ"
        //�@@�@@�@@�@@�E"�t���[�t�H�[�}�b�g"
        if (this.attentionInfoDivCode != null)
        {
            if (!WEB3AttentionInfoDivCodeDef.TRADE_STOP_ORDER_ACCEPT_ENABLE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_ORDER_ACCEPT_DISABLE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_CANCEL_ORDER_ACCEPT_ENABLE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_CANCEL_ORDER_ACCEPT_DISABLE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_ORDER_ACCEPT_ENABLE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_ORDER_ACCEPT_DISABLE.equals(
                    this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_ENABLE.equals(
                    this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_DISABLE.equals(
                    this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_CANCEL.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_RELEASE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_RELEASE_CANCEL.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.OPEN_LISTING_PRODUCT_GIVEN_FIRST_VALUE.equals(this.attentionInfoDivCode)
                && !WEB3AttentionInfoDivCodeDef.FREE_FORMAT.equals(this.attentionInfoDivCode))
            {
                log.debug("���ӏ��敪�R�[�h������`�̒l�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03149,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ӏ��敪�R�[�h������`�̒l�B");
            }
        }

        //�R�j���ӏ����-���ӏ��敪�R�[�h�̑��փ`�F�b�N
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ӏ���� != null�@@����
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ӏ��敪�R�[�h != null �̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.attentionInfoType != null && this.attentionInfoDivCode != null)
        {
            //�@@�R�|�P�jthis.���ӏ���� �� "�t���[�t�H�[�}�b�g" ����
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ӏ��敪�R�[�h �� "�t���[�t�H�[�}�b�g"�̏ꍇ�A
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�u���ӏ���ʁ^���ӏ��敪�w�肪�s�����v�̗�O���X���[����B
            if (WEB3AttentionInfoTypeDef.FREE_FORMAT.equals(this.attentionInfoType)
                && !WEB3AttentionInfoDivCodeDef.FREE_FORMAT.equals(this.attentionInfoDivCode))
            {
                log.debug("���ӏ���ʁ^���ӏ��敪�w�肪�s�����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03148,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ӏ���ʁ^���ӏ��敪�w�肪�s�����B");
            }

            //�@@�R�|�Q�jthis.���ӏ���� �� "�l���������" ����
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ӏ��敪�R�[�h �� "�V�K�������̏��l���t�����ꍇ"�̏ꍇ�A
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�u���ӏ���ʁ^���ӏ��敪�w�肪�s�����v�̗�O���X���[����B
            if (WEB3AttentionInfoTypeDef.LIMIT_RANGE_INFO.equals(this.attentionInfoType)
                && !WEB3AttentionInfoDivCodeDef.OPEN_LISTING_PRODUCT_GIVEN_FIRST_VALUE.equals(
                       this.attentionInfoDivCode))
            {
                log.debug("���ӏ���ʁ^���ӏ��敪�w�肪�s�����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03148,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ӏ���ʁ^���ӏ��敪�w�肪�s�����B");
            }

            //�@@�R�|�R�jthis.���ӏ���� �� "������" �̏ꍇ�A
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ӏ��敪�R�[�h �� "�t���[�t�H�[�}�b�g"
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���邢�́Athis.���ӏ��敪�R�[�h �� "�V�K�������̏��l���t�����ꍇ"
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ł���΁A�u���ӏ���ʁ^���ӏ��敪�w�肪�s�����v�̗�O���X���[����B
            if (WEB3AttentionInfoTypeDef.SELL_STOP_INFO.equals(this.attentionInfoType))
            {
                if (WEB3AttentionInfoDivCodeDef.FREE_FORMAT.equals(this.attentionInfoDivCode)
                    || WEB3AttentionInfoDivCodeDef.OPEN_LISTING_PRODUCT_GIVEN_FIRST_VALUE.equals(
                           this.attentionInfoDivCode))
                {
                    log.debug("���ӏ���ʁ^���ӏ��敪�w�肪�s�����B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03148,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ӏ���ʁ^���ӏ��敪�w�肪�s�����B");
                }
            }
        }

        //�S�j�s��`�F�b�N
        //�@@this.�s��R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�S-�P�jthis.�s��R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        //�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E"����"
        //�@@�@@�@@�@@�E"���"
        //�@@�@@�@@�@@�E"���É�"
        //�@@�@@�@@�@@�E"����"
        //�@@�@@�@@�@@�E"�D�y"
        //�@@�@@�@@�@@�E"NNM"
        //�@@�@@�@@�@@�E"JASDAQ"
        //�@@�@@�@@�@@�E"JNX-PTS"
        if (this.marketCode != null)
        {
            if (!WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                && !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                && !WEB3MarketCodeDef.NNM.equals(this.marketCode)
                && !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
                && !WEB3MarketCodeDef.JNX_PTS.equals(this.marketCode))
            {
                log.debug("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        //�T�j�����R�[�h�`�F�b�N
        //�@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�T-�P�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�A
        //�@@�@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B
        //�@@�@@�@@�@@�@@�@@�Ethis.�����R�[�h != ����
        //�@@�@@�@@�@@�@@�@@�Ethis.�����R�[�h.length != 5
        if (this.productCode != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.productCode)
                || this.productCode.length() != 5)
            {
                log.debug("�����R�[�h�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02785,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����R�[�h�G���[�B");
            }
        }

        //�U�j�L�����`�F�b�N
        //�@@this.�L���� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�U-�P�jthis.�L�������ȉ��̏����ɊY������ꍇ�A
        //�@@�@@�@@�@@�@@�u�L�����G���[�v�̗�O���X���[����B
        //�@@�@@�@@�@@�@@�@@�Ethis.�L������Date�^�ɕϊ��ł��Ȃ������ꍇ�B
        //�@@�@@�@@�@@�@@�@@�@@�������t�H�[�}�b�g�uYYYYMMDD�v
        if (this.validDate != null)
        {
            if (WEB3DateUtility.getDate(this.validDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD) == null)
            {
                log.debug("�L�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03150,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�L�����G���[�B");
            }
        }

        //�V�j��񔭐�����From�`�F�b�N
        //�@@this.��񔭐�����From != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�V-�P�jthis.��񔭐�����From���ȉ��̏����ɊY������ꍇ�A
        //�@@�@@�@@�@@�@@�u��񔭐�����From�G���[�v�̗�O���X���[����B
        //�@@�@@�@@�@@�@@�@@�Ethis.��񔭐�����From��Date�^�ɕϊ��ł��Ȃ������ꍇ�B
        //�@@�@@�@@�@@�@@�@@�@@�������t�H�[�}�b�g�uYYYYMMDDHH24MISS�v
        if (this.infoOccuredDateFrom != null)
        {
            if (WEB3DateUtility.getDate(this.infoOccuredDateFrom,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS) == null)
            {
                log.debug("��񔭐�����From�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03151,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��񔭐�����From�G���[�B");
            }
        }

        //�W�j��񔭐�����To�`�F�b�N
        //�@@this.��񔭐�����To != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�W-�P�jthis.��񔭐�����To���ȉ��̏����ɊY������ꍇ�A
        //�@@�@@�@@�@@�@@�u��񔭐�����To�G���[�v�̗�O���X���[����B
        //�@@�@@�@@�@@�@@�@@�Ethis.��񔭐�����To��Date�^�ɕϊ��ł��Ȃ������ꍇ�B
        //�@@�@@�@@�@@�@@�@@�@@�������t�H�[�}�b�g�uYYYYMMDDHH24MISS�v
        if (this.infoOccuredDateTo != null)
        {
            if (WEB3DateUtility.getDate(this.infoOccuredDateTo,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS) == null)
            {
                log.debug("��񔭐�����To�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03152,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��񔭐�����To�G���[�B");
            }
        }

        //�X�j��񔭐�����From/To�������`�F�b�N
        //�@@this.��񔭐�����From != null�@@���@@this.��񔭐�����To != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�X-�P�jthis.��񔭐�����From > this.��񔭐�����To�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u���͎��Ԑ������G���[�v�̗�O���X���[����B
        if (this.infoOccuredDateFrom != null && this.infoOccuredDateTo != null)
        {
            Date l_datFrom =
                WEB3DateUtility.getDate(this.infoOccuredDateFrom,
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            Date l_datTo =
                WEB3DateUtility.getDate(this.infoOccuredDateTo,
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            if (WEB3DateUtility.compareToSecond(l_datFrom, l_datTo) > 0)
            {
                log.debug("���͎��Ԑ������G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͎��Ԑ������G���[�B");
            }
        }

        //�P�O�j�\�[�g�L�[�`�F�b�N
        //�@@�P�O�|�P�j�@@this.�\�[�g�L�[ == null�ł������ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        //�@@�P�O�|�Q�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        //�@@�@@�P�O�|�Q�|�P�j�\�[�g�L�[.validate()���R�[������B
        int l_intSortKeys = this.sortKeys.length;
        for (int i = 0; i < l_intSortKeys; i++)
        {
            this.sortKeys[i].validate();
        }

        //�P�P�j�\���y�[�W�ԍ��`�F�b�N
        //�@@�P�P-�P�jthis.�\���y�[�W�ԍ� == null�ł������ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�\���y�[�W�ԍ���null�v�̗�O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        //�@@�P�P-�Q�jthis.�\���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�\���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        //�@@�P�P-�R�jthis.�\���y�[�W�ԍ� <= 0�ł������ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�\���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        //�P�Q�j�y�[�W���\���s���`�F�b�N
        //�@@�P�Q-�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }

        //�@@�P�Q-�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        //�@@�P�Q-�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminEqAttentionInfoRefRefResponse(this);
    }
}
@
