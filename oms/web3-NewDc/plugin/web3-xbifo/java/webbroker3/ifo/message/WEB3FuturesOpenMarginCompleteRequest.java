head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�V�K�������������N�G�X�g�N���X(WEB3FuturesOpenMarginCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���敨�V�K�������������N�G�X�g)<BR>
 * �����w���敨�V�K�������������N�G�X�g�N���X<BR>
 * 
 * @@author ������
 * @@version 1.0 
 */
public class WEB3FuturesOpenMarginCompleteRequest extends WEB3FuturesCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_OpenMarginComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201655L;

    /**
     * (�����w���敨�����R�[�h)<BR>
     */
    public String futProductCode;

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
     * (�Ïؔԍ�)<BR>
     */
    public String password;

    /**
     * (�m�F���P��)<BR>
     * �m�F���X�|���X�ő������l�B<BR>
     */
    public String checkPrice;

    /**
     * (�m�F��������)<BR>
     * �m�F���X�|���X�ő������l�B<BR>
     */
    public Date checkDate;

    /**
     * (����ID)<BR>
     * �m�F���X�|���X�ő������l�B<BR>
     */
    public String orderId;

    /**
     * (�w�����)<BR>
     *�i�����Y�����R�[�h�j<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     */
    public String targetProductCode;

    /**
     * (����)<BR>
     * YYYYMM�`��<BR>
     */
    public String delivaryMonth;

    /**
     * @@roseuid 40F7AE13004E
     */
    public WEB3FuturesOpenMarginCompleteRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��<BR>
     * <BR>
     * �Q�j�@@�����`�F�b�N<BR>
     * �@@this.�����R�[�h��null�̏ꍇ�A���L�����ȊO�͗�O�u�����w��G���[�v���X���[����B<BR>
     * �@@�Ethis.�w�����!=null�@@&&�@@this.����!=null<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00334<BR>
     * <BR>
     * �R�j�@@���敪�`�F�b�N<BR>
     * �@@�R�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00263<BR>
     * �@@�R�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�����h<BR>
     * �@@�@@�@@�@@�E�h2�F�����h<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00264<BR>
     * <BR>
     * �S�j�@@����s��`�F�b�N<BR>
     * �@@�S�|�P�jthis.����s�ꂪnull�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00265<BR>
     * �@@�S�|�Q)this.����s�ꂪ�ȉ��̒l�ȊO�̒l�̏ꍇ��O���X���[����B<BR> 
     *        �E�h1�F�����h<BR> 
     *        �E�h2�F���h<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01087<BR>
     * <BR>
     * �T�j�@@�������ʃ`�F�b�N<BR>
     * �@@�T�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00074<BR>
     * �@@�T�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00075<BR>
     * �@@�T�|�R�jthis.�������ʁ��O�̏ꍇ�A��O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00076<BR>
     * �@@�T�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00077<BR>
     * <BR>
     * �U�j�@@�m�F���P���`�F�b�N(this.����ID==null�̏ꍇ�A�`�F�b�N���s��Ȃ��j<BR>
     * �@@this.�m�F���P����null�̒l�ł���Η�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * �V�j�@@�m�F���������`�F�b�N(this.����ID==null�̏ꍇ�A�`�F�b�N���s��Ȃ��j<BR>
     * �@@this.�m�F����������null�̒l�ł���Η�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00078<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A214200260
     */
    public void validate() throws WEB3BaseException
    {
        // �P�j�X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();
        
        // �Q�j�@@�����`�F�b�N<BR>
        // �@@this.�����R�[�h��null�̏ꍇ�A���L�����ȊO�͗�O�u�����w��G���[�v���X���[����B
        // �@@�Ethis.�w�����!=null�@@&&�@@this.����!=null
        //      class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00334
        if (WEB3StringTypeUtility.isEmpty(this.futProductCode)
            && (WEB3StringTypeUtility.isEmpty(this.targetProductCode)
            || WEB3StringTypeUtility.isEmpty(this.delivaryMonth)))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00334, 
                this.getClass().getName() + "validate",
                "�����w��G���[�B");
        }

        // �R�j�@@���敪�`�F�b�N
        //�R�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.contractType))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263, 
                this.getClass().getName() + "validate",
                "���敪��null�ł���");
        }

        //�R�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType) 
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264, 
                this.getClass().getName() + "validate",
                "���敪���h1�F�����h�A�h2�F�����h�ȊO�ł���");
        }

        // �S)����s��`�F�b�N
        //�S�|�P�jthis.����s�ꂪnull�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00265, 
                this.getClass().getName() + "validate",
                "����s�ꂪnull�ł���");
        }

        //�S�|�Q)this.����s�ꂪ�ȉ��̒l�ȊO�̒l�̏ꍇ��O���X���[����B
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

        // �T�j�������ʃ`�F�b�N
        //�T�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.futOrderQuantity))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074, 
                this.getClass().getName() + "validate",
                "�������ʂ���͂��Ă��������B");
        }

        //�T�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.futOrderQuantity))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075, 
                this.getClass().getName() + "validate",
                "�������ʂ������ȊO�̒l�ł���B");
        }

        //�T�|�R�jthis.�������ʁ��O�̏ꍇ�A��O���X���[����B
        if (Long.parseLong(this.futOrderQuantity) <= 0)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076, 
                this.getClass().getName() + "validate",
                "�������ʂ�0�ȉ��̒l�ł���B");
        }

        //�T�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(this.futOrderQuantity) > 5)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077, 
                this.getClass().getName() + "validate",
                "�������ʂ��T���𒴂��܂����B");
        }

        // �U�j�@@�m�F���P���`�F�b�N(this.����ID==null�̏ꍇ�A�`�F�b�N���s��Ȃ��j
        //  this.�m�F���P����null�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isEmpty(this.orderId) 
            && WEB3StringTypeUtility.isEmpty(this.checkPrice))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206, 
                this.getClass().getName() + "validate",
                "�m�F���P����null�̒l�ł���");
        }

        // �V�j�@@�m�F���������`�F�b�N(this.����ID==null�̏ꍇ�A�`�F�b�N���s��Ȃ��j
        // this.�m�F����������null�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isEmpty(this.orderId) 
            && this.checkDate == null)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078, 
                this.getClass().getName() + "validate",
                "�m�F����������null�̒l�ł���B");
        }
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOpenMarginCompleteResponse(this);
    }
}
@
