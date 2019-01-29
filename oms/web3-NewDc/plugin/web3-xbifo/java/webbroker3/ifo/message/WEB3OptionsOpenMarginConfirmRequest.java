head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����V�K�������m�F���N�G�X�g(WEB3OptionsOpenMarginConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���o�� (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;

/**
 * (�����w���I�v�V�����V�K�������m�F���N�G�X�g)<BR>
 * �����w���I�v�V�����V�K�������m�F���N�G�X�g�N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginConfirmRequest extends WEB3OptionsCommonRequest
{
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionsOpenMarginConfirmRequest.class);
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406141500L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_openMarginConfirm";

    /**
     * �����w���I�v�V���������R�[�h<BR>
     */
    public String opProductCode;

    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����<BR>
     */
    public String contractType;

    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���<BR>
     */
    public String marketCode;

    /**
     * (�w�����)<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     */
    public String targetProductCode;

    /**
     * (����)<BR>
     * YYYYMM�`��<BR>
     */
    public String delivaryMonth;

    /**
     * (�I�v�V�������i�敪)<BR>
     * P�F�v�b�g�I�v�V���� C�F�R�[���I�v�V����<BR>
     */
    public String opProductType;

    /**
     * (�s�g���i)<BR>
     */
    public String strikePrice;

    /**
     * @@roseuid 40C0A8ED0261
     */
    public WEB3OptionsOpenMarginConfirmRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��<BR>
     * <BR>
     * �Q�j�@@���敪�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00263<BR>
     * �@@�Q�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�����h<BR>
     * �@@�@@�@@�@@�E�h2�F�����h<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00264<BR>
     * <BR>
     * �R�j�@@����s��`�F�b�N<BR>
     * �@@�R�|�P�jthis.����s�ꂪnull�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00265<BR>
     * �@@�R�|�Q)this.����s�ꂪ�ȉ��̒l�ȊO�̒l�̏ꍇ��O���X���[����B<BR> 
     * �@@�E�h1�F�����h<BR> 
     * �@@�E�h2�F���h<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01087<BR>
     * <BR>
     * �S�j�@@�w����ʃ`�F�b�N<BR>
     *�@@�S�|�P�jthis.�w����ʂ�null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00266<BR>
     *�@@�S�|�Q�jthis.�w����ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02441<BR>
     *�@@�S�|�R�jthis.�w����ʂ̌������S���ȊO�̒l�ł���Η�O���X���[����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02442<BR>
     * <BR>
     * �T�j�@@�����`�F�b�N<BR>
     * �@@�T�|�P�jthis.������null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00267<BR>
     * �@@�T�|�Q�jthis.�������x�x�x�x�l�l�`���̒l�ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00268<BR>
     * <BR>
     * �U�j�@@�I�v�V�������i�敪�`�F�b�N<BR>
     * �@@�U�|�P�jthis.�I�v�V�������i�敪��null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00269<BR>
     * �@@�U�|�Q�jthis.�I�v�V�������i�敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�hP�F�v�b�g�I�v�V�����h<BR>
     * �@@�@@�@@�@@�E�hC�F�R�[���I�v�V�����h<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00270<BR>
     * <BR>
     * �V�j�@@�s�g���i�`�F�b�N<BR>
     * �@@�V�|�P�jthis.�s�g���i��null�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00271<BR>
     * �@@�V�|�Q�jthis.�s�g���i�������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00272<BR>
     * �@@�V�|�R�jthis.�s�g���i�����O�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00273<BR>
     * �@@�V�|�S�jthis.�s�g���i���W���𒴂���l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00274<BR>
     * <BR>
     * �W�j�@@�������ʃ`�F�b�N<BR>
     * �@@�W�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00074<BR>
     * �@@�W�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00075<BR>
     * �@@�W�|�R�jthis.�������ʂ����O�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00076<BR>
     * �@@�W�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00077<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4068CE9D037B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = getClass().getName() + ".validate()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();

        //���敪�`�F�b�N
        //this.���敪��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.contractType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263,
                getClass().getName() + "validate",
                "���敪��null�ł���");   
        }

        //���敪�`�F�b�N
        //this.���敪���h1�F�����h�h2�F����"�ȊO�̏ꍇ��O���X���[����B
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType)
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264,
                getClass().getName() + "validate",
                "���敪���h1�F�����h�A�h2�F�����h�ȊO�ł���");
        }

        //�R�|�P)����s��`�F�b�N
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00265,
                getClass().getName() + "validate",
                "����s�ꂪnull�ł���");
        }

        //�R�|�Q)this.����s�ꂪ�ȉ��̒l�ȊO�̒l�̏ꍇ��O���X���[����B<BR> 
        //�@@�E�h1�F�����h 
        //�@@�E�h2�F���h
        if (!WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
            && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01087,
                getClass().getName() + "validate",
                "����s��̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
        }

        //�S�|�P�w����ʃ`�F�b�N
        //this.�w����ʂ�null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.targetProductCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00266,
                getClass().getName() + "validate",
                "�w����ʂ�null�ł���");
        }

        //�S�|�Q�jthis.�w����ʂ������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.targetProductCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02441,
                getClass().getName() + "validate",
                "�w����ʂ������ȊO�̒l�ł��B");
        }
        
        //�S�|�R�jthis.�w����ʂ̌������S���ȊO�̒l�ł���Η�O���X���[����B
        if (this.targetProductCode.length() != 4)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02442,
                getClass().getName() + "validate",
                "�w����ʂ̃T�C�Y���s���ł��B");
        }

        //�T�j�����`�F�b�N
        //this.�����R�[�h��null�̒l�ł��Athis.������null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.delivaryMonth))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00267,
                getClass().getName() + "validate",
                "������null�ł���");
        }

        //�����`�F�b�N
        //this.�����R�[�h��null�̒l�ł��Athis.�������x�x�x�x�l�l�`���̒l�ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isDateStr(this.delivaryMonth ,"yyyymm"))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00268,
                getClass().getName() + "validate",
                "�������x�x�x�x�l�l�`���œ��͂��Ă�������");
        }

        //�U�j�I�v�V�������i�敪�`�F�b�N
        //this.�I�v�V�������i�敪��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.opProductType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00269,
                getClass().getName() + "validate",
                "�I�v�V�������i�敪��null�ł���");
        }

        //�I�v�V�������i�敪�`�F�b�N
        //�I�v�V�������i�敪���hP�h�A�hC�h�ȊO�̏ꍇ��O���X���[����B
        if (!WEB3IfoProductTypeDef.CALL_OPTIONS.equals(this.opProductType)
                && !WEB3IfoProductTypeDef.PUT_OPTIONS.equals(this.opProductType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                getClass().getName() + "validate",
                "�I�v�V�������i�敪���hP�F�v�b�g�I�v�V�����h�A�hC�F�R�[���I�v�V�����h�ȊO�ł���");
        }

        //�V�j�s�g���i�`�F�b�N
        //this.�����R�[�h��null�̒l�ł��Athis.�s�g���i��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.strikePrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00271,
                getClass().getName() + "validate",
                "�s�g���i��null�ł���");
        }

        //�s�g���i�`�F�b�N
        //this.�s�g���i�������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.strikePrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00272,
                getClass().getName() + "validate",
                "�s�g���i�������ȊO�̒l�ł���");
        }

        //�s�g���i�`�F�b�N
        //this.�s�g���i�����O�̒l�ł���Η�O���X���[����B
        if (Double.parseDouble(this.strikePrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00273,
                getClass().getName() + "validate",
                "�s�g���i��0�ȉ��̒l�ł���");   
        }

        //�s�g���i�`�F�b�N
        //this.�s�g���i���W���𒴂���l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(this.strikePrice) > 8)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00274,
                getClass().getName() + "validate",
                "�s�g���i���W���𒴂��܂����B");
        }

        //�W�j�������ʃ`�F�b�N
        //this.�������ʂ�null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.opOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                getClass().getName() + "validate",
                "�������ʂ���͂��Ă��������B");
        }

        //�������ʃ`�F�b�N
        //this.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.opOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                getClass().getName() + "validate",
                "�������ʂ������ȊO�̒l�ł���B");
        }

        //�������ʃ`�F�b�N
        //this.�������ʂ����O�̒l�ł���Η�O���X���[����B
        if (Long.parseLong(this.opOrderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                getClass().getName() + "validate",
                "�������ʂ�0�ȉ��̒l�ł���B");
        }

        //�������ʃ`�F�b�N
        //this.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(this.opOrderQuantity) > 5)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                getClass().getName() + "validate",
                "�������ʂ��T���𒴂��܂����B");
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
        return new WEB3OptionsOpenMarginConfirmResponse(this);
    }
}
@
