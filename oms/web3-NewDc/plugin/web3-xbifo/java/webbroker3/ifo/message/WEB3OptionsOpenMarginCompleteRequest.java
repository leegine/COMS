head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����V�K�������������N�G�X�g(WEB3OptionsOpenMarginCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���o�� (���u) �V�K�쐬
                   2006/12/05 ����� (���u) �d�l�ύX���f��591
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;

/**
 * (�����w���I�v�V�����V�K�������������N�G�X�g)<BR>
 * �����w���I�v�V�����V�K�������������N�G�X�g�N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginCompleteRequest extends WEB3OptionsCommonRequest
{

    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsOpenMarginCompleteRequest.class);
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406141504L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_openMarginComplete";

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
     * �Ïؔԍ�
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
     * (�w�����)
     * �i�����Y�����R�[�h�j<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     */
    public String targetProductCode;

    /**
     * ����<BR>
     * YYYYMM�`��<BR>
     */
    public String delivaryMonth;
    
    /**
     * �I�v�V�������i�敪<BR>
     * P�F�v�b�g�I�v�V���� C�F�R�[���I�v�V����<BR>
     */
    public String opProductType;
    
    /**
     * �s�g���i<BR>
     */
    public String strikePrice;
    
    /**
     * @@roseuid 40C0A8ED00AB
     */
    public WEB3OptionsOpenMarginCompleteRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��<BR>
     * <BR>
     *�Q�j�@@�����`�F�b�N <BR>
     *�@@this.�����R�[�h��null�̏ꍇ�A���L�����ȊO�͗�O�u�����w��G���[�v���X���[����B<BR> 
     *�@@�Ethis.�w�����!=null�@@&&�@@this.����!=null�@@&&�@@<BR>
     *      this.�I�v�V�������i�敪!=null�@@&&�@@this.�s�g���i!=null <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00334<BR>
     * <BR>
     * �R�j�@@���敪�`�F�b�N<BR>
     * �@@�R�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00263<BR>
     * �@@�R�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�����h<BR>
     * �@@�@@�@@�@@�E�h2�F�����h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00264<BR>
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
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00074<BR>
     * �@@�T�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00075<BR>
     * �@@�T�|�R�jthis.�������ʂ����O�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00076<BR>
     * �@@�T�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00077<BR>
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
     * @@roseuid 4068D7BC01D6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".validate()";
                
        log.entering(STR_METHOD_NAME);
        
        // �P�j�X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();
        log.debug("WEB3OptionsCommonRequest.validate()���Ăяo��");

        // �Q�j�@@�����`�F�b�N 
        //�@@this.�����R�[�h��null�̏ꍇ�A���L�����ȊO�͗�O�u�����w��G���[�v���X���[����B 
        //�@@�Ethis.�w�����!=null�@@&&�@@this.����!=null�@@&&�@@
        //      this.�I�v�V�������i�敪!=null�@@&&�@@this.�s�g���i!=null 
        log.debug("�����R�[�h�`�F�b�N");
        log.debug("this.opProductCode = " + this.opProductCode);
        
        if (WEB3StringTypeUtility.isEmpty(this.opProductCode) 
            && (WEB3StringTypeUtility.isEmpty(this.targetProductCode) 
            || WEB3StringTypeUtility.isEmpty(this.delivaryMonth) 
            || WEB3StringTypeUtility.isEmpty(this.opProductType)
            || WEB3StringTypeUtility.isEmpty(this.strikePrice)))
        {
            log.debug("�����R�[�h��null�̒l�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00334,
                getClass().getName() + "validate",
                "�����R�[�h����͂��Ă��������B");
        }

        // �R�j���敪�`�F�b�N
        log.debug("���敪�`�F�b�N");
        log.debug("this.contractType = " + this.contractType);
        
        // �R�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.contractType))
        {
            log.debug("���敪��null�̒l�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263,
                getClass().getName() + "validate",
                "���敪��null�ł���");
        }

        // �R�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        //this.���敪���h1�F�����h�h2�F����"�ȊO�̏ꍇ��O���X���[����B
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType)
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            log.debug("���敪��'1�F����''2�F����'�ȊO�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264,
                getClass().getName() + "validate",
                "���敪���h1�F�����h�A�h2�F�����h�ȊO�ł���");
        }

        //�S�|�P)����s��`�F�b�N
        log.debug("����s��`�F�b�N");
        log.debug("this.marketCode = " + this.marketCode);
        
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            log.debug("����s�ꂪnull�̒l�̏ꍇ"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00265,
                getClass().getName() + "validate",
                "����s�ꂪnull�ł���");
        }

        //�S�|�Q)this.����s�ꂪ�ȉ��̒l�ȊO�̒l�̏ꍇ��O���X���[����B<BR> 
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

        //�T�j�������ʃ`�F�b�N
        //�T�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B
        log.debug("�������ʃ`�F�b�N");
        log.debug("this.opOrderQuantity = " + this.opOrderQuantity);
        
        if (WEB3StringTypeUtility.isEmpty(this.opOrderQuantity))
        {
            log.debug("�������ʂ�null�̒l�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                getClass().getName() + "validate",
                "�������ʂ���͂��Ă��������B");
        }

        //�������ʃ`�F�b�N
        //�T�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.opOrderQuantity))
        {
            log.debug("�������ʂ������ȊO�̒l�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                getClass().getName() + "validate",
                "�������ʂ������ȊO�̒l�ł���B");
        }

        //�������ʃ`�F�b�N
        //�T�|�R�jthis.�������ʂ����O�̒l�ł���Η�O���X���[����B
        if (Long.parseLong(this.opOrderQuantity) <= 0)
        {
            log.debug("�������ʂ����O�̒l�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                getClass().getName() + "validate",
                "�������ʂ�0�ȉ��̒l�ł���B");
        }

        //�������ʃ`�F�b�N
        //�T�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(this.opOrderQuantity) > 5)
        {
            log.debug("�������ʂ��T���𒴂���̒l�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                getClass().getName() + "validate",
                "�������ʂ��T���𒴂��܂����B");
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
        return new WEB3OptionsOpenMarginCompleteResponse(this);
    }
}
@
