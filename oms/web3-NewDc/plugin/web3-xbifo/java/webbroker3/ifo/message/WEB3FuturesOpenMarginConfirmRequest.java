head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�V�K�������m�F���N�G�X�g�N���X(WEB3FuturesOpenMarginConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;

import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���敨�V�K�������m�F���N�G�X�g)<BR>
 * �����w���敨�V�K�������m�F���N�G�X�g�N���X<BR> 
 * 
 * @@author ������
 * @@version 1.0 
 */

public class WEB3FuturesOpenMarginConfirmRequest extends WEB3FuturesCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_OpenMarginConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201455L;

    /**
     * (�����w���敨�����R�[�h)<BR>
     */
    public String futProductCode;

    /**
     * (���敪)<BR>.
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
     * @@roseuid 40F7AE130203
     */
    public WEB3FuturesOpenMarginConfirmRequest()
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
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00263<BR>
     * �@@�Q�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�����h<BR>
     * �@@�@@�@@�@@�E�h2�F�����h<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00264<BR>
     * <BR>
     * �R�j�@@����s��`�F�b�N<BR>
     * �@@�R�|�P�jthis.����s�ꂪnull�̒l�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00265<BR>
     * �@@�R�|�Q)this.����s�ꂪ�ȉ��̒l�ȊO�̒l�̏ꍇ��O���X���[����B<BR> 
     * �@@     �E�h1�F�����h<BR> 
     * �@@     �E�h2�F���h<BR>
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
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00267<BR>
     * �@@�T�|�Q�jthis.�������x�x�x�x�l�l�`���̒l�ȊO�̒l�ł���Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00268<BR>
     * <BR>
     * �U�j�@@�������ʃ`�F�b�N<BR>
     * �@@�U�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00074<BR>
     * �@@�U�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00075<BR>
     * �@@�U�|�R�jthis.�������ʁ��O�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00076<BR>
     * �@@�U�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00077<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A212B90169
     */
    public void validate() throws WEB3BaseException
    {
        //(�P�j�X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();

        //(�Q�j���敪�`�F�b�N
        if (WEB3StringTypeUtility.isEmpty(this.contractType))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263, 
                this.getClass().getName() + "validate",
                "�����R�[�h��null�̒l�ł��A���敪��null�̒l�ł���");
        }

        //�Q�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType) 
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264, 
                this.getClass().getName() + "validate",
                "���敪���h1�F�����h�A�h2�F�����h�ȊO�ł���");
        }

        //(�R�j�@@����s��`�F�b�N
        //this.����s�ꂪnull�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00265, 
                this.getClass().getName() + "validate",
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
                this.getClass().getName() + "validate",
                "����s��̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
        }

        //�i�S�j�@@�w����ʃ`�F�b�N
        //�S�|�P�jthis.�w����ʂ�null�̒l�ł���Η�O���X���[����B
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

        //(�T�j�����`�F�b�N
        //�T�|�P�jthis.������null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.delivaryMonth))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00267, 
                this.getClass().getName() + "validate",
                "�����R�[�h��null�̒l�ł��A������null�̒l�ł���");
        }

        //�T�|�Q�jthis.�������x�x�x�x�l�l�`���̒l�ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isDateStr(this.delivaryMonth, "yyyymm"))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00268, 
                this.getClass().getName() + "validate",
                "�����R�[�h��null�̒l�ł��A.�������x�x�x�x�l�l�`���̒l�ȊO�̒l�ł���");
        }

        //(�U�j�@@�������ʃ`�F�b�N
        //�U�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.futOrderQuantity))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074, 
                this.getClass().getName() + "validate",
                "�������ʂ���͂��Ă��������B");
        }

        //�U�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.futOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075, 
                getClass().getName() + "validate",
                "�������ʂ������ȊO�̒l�ł���B");
        }

        //�U�|�R�jthis.�������ʁ��O�̏ꍇ�A��O���X���[����B
        if (Long.parseLong(this.futOrderQuantity) <= 0)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076, 
                this.getClass().getName() + "validate",
                "�������ʂ�0�ȉ��̒l�ł���B");
        }

        //�U�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(this.futOrderQuantity) > 5)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077, 
                this.getClass().getName() + "validate",
                "�������ʂ��T���𒴂��܂����B");
        }
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOpenMarginConfirmResponse(this);
    }
}
@
