head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginCloseConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����ԍϒ����m�F���N�G�X�g�N���X(WEB3SuccMarginCloseConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A����(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p����ԍϒ����m�F���N�G�X�g�N���X)<BR>
 * �i�A���j�M�p����ԍϒ����m�F���N�G�X�g�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0 
 */
public class WEB3SuccMarginCloseConfirmRequest extends WEB3MarginCloseMarginConfirmRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccMarginCloseConfirmRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginCloseConfirm";
    
    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * �P�������l���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;
    
    /**
     * @@roseuid 4369CC0B0213
     */
    public WEB3SuccMarginCloseConfirmRequest() 
    {
     
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccMarginCloseConfirmResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�A���������ʏ��`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A���������ʏ��w��Ȃ��v�̗�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02251<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B<BR>
     * <BR>
     * �@@�P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"�M�p�ԍρi�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"�M�p�ԍρi�����c�j"<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02252<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���R�[�h�l�́A�����\�񒍕��P�ʃe�[�u�����Q�ƁB<BR>
     * <BR>
     * �Q�j�@@this.�A���������ʏ��.�A����������敪=="�M�p�ԍρi�����c�j"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@super.validate()���R�[������B<BR>
     * �@@�@@�@@�ȊO�Asuper.validateAT���Ύ��()���R�[������B<BR>
     * <BR>
     * �R�j�@@�A�������P�������l���`�F�b�N<BR>
     * �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�A���������ʏ��.�A����������敪��"�M�p�ԍρi�O�񒍕��j"�ł����<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪���A�A�������́}�w�l�w��s�̋敪�v�̗�O��throw����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02253<BR>
     * <BR>
     * �@@�R�|�R�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��<BR>
     * �@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02254<BR>
     * <BR>
     * �S�j�@@this.�A���������ʏ��.�A����������敪=="�M�p�ԍρi�O�񒍕��j"�̏ꍇ�A <BR>
     * �@@�@@�@@���N�G�X�g.���Ϗ����敪==�inull or "������"�j�̏ꍇ�́A <BR>
     * �@@�@@�@@�u�A�������E���Ύ�����̌��Ϗ����敪�w��s���v�̗�O��throw����B <BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02306<BR>
     * <BR>
     * �T�j�@@�A�������E���������`�F�b�N<BR>
     * �@@super.validate�A������()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43293EBE01C4
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
    
        // �P�j�@@�A���������ʏ��`�F�b�N
        // �@@�P�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�u�A���������ʏ��w��Ȃ��v�̗�O���X���[����B
        if (this.succCommonInfo == null)
        {
            log.debug("�A���������ʏ�񂪖��w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A���������ʏ�񂪖��w��ł��B");
        }
       
       //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
        this.succCommonInfo.validate();
       
        //1�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B

        if ((!WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_ASSUMPTION_ORDER.equals(
            this.succCommonInfo.succTradingType))
            && (!WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_EXISTING_REMAINDER.equals(
            this.succCommonInfo.succTradingType))) 
        {
            log.debug("�A����������敪�̒l�������ΏۊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A����������敪�̒l�������ΏۊO�ł��B");
        }
        
        // �Q�j�@@this.�A���������ʏ��.�A����������敪=="�M�p�ԍρi�����c�j"�̏ꍇ�̂݁A
        // �@@�@@�@@super.validate()���R�[������B
        // �@@�@@�@@�ȊO�Asuper.validateAT���Ύ��()���R�[������B
        if (WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_EXISTING_REMAINDER.equals(
            this.succCommonInfo.succTradingType))
        {
            super.validate();
        }
        else
        {
            super.validateAtReverseOrder();
        }
        
        // �R�j�@@�A�������P�������l���`�F�b�N
        // �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();
            
            //�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
            // �@@�@@�@@�@@�@@�@@�A���������ʏ��.�A����������敪��"�M�p�ԍρi�O�񒍕��j"�ł����
            // �@@�@@�@@�@@�@@�@@�u�A����������敪���A�A�������́}�w�l�w��s�̋敪�v�̗�O��throw����B
            if (!WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_ASSUMPTION_ORDER.equals(
                this.succCommonInfo.succTradingType))
            {
                log.debug("�A����������敪���A�A�������́}�w�l�w��s�̋敪�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02253,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�A����������敪���A�A�������́}�w�l�w��s�̋敪�ł��B");
            }
            
            //�R�|�R�j�@@�A�������P�������l���null�̏ꍇ�A
            //�@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
            //�@@�@@�@@�@@�@@  ��O��throw����B
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.debug("�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");
            }
        }
        
        //�S�j�@@���N�G�X�g.���Ϗ����敪�`�F�b�N
        if (WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_ASSUMPTION_ORDER.equals(
            this.succCommonInfo.succTradingType))
        {
            if (this.closingOrder == null
                || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02306,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���Ύ�����̌��Ϗ����敪�w��s���B");
            }
        }
        
        //�T�j�@@�A�������E���������`�F�b�N
        //     super.validate�A������()���R�[������B
        super.validateSuccOrder();
        log.exiting(STR_METHOD_NAME);
    }    
}
@
