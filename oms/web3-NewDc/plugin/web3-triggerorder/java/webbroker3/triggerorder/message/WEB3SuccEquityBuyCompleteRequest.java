head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityBuyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����������t�����������N�G�X�g(WEB3SuccEquityBuyCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �A�C��(���u) �V�K�쐬
                 : 2007/01/10 ���G��(���u) ���f��214
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�A���j�����������t�����������N�G�X�g)<BR>
 * �i�A���j�����������t�����������N�G�X�g�B<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3SuccEquityBuyCompleteRequest extends WEB3EquityBuyCompleteRequest 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquityBuyCompleteRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityBuyComplete";
    
    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * (�P�������l���)<BR>
     * �P�������l���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;

    /**
     * (������P��)<BR>
     * ������P���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 434896060186
     */
    public WEB3SuccEquityBuyCompleteRequest() 
    {
     
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccEquityBuyCompleteResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�A���������ʏ��`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A���������ʏ��w��Ȃ��v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02251<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO��<BR>
     * �@@�@@�@@�@@�@@�@@�ꍇ�A�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02252<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"���t�i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"���t"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���R�[�h�l�́A�����\�񒍕��P�ʃe�[�u�����Q�ƁB<BR>
     * <BR>
     * �R�j�@@�A�������P�������l���`�F�b�N<BR>
     * �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�A���������ʏ��.�A����������敪��<BR>
     * �@@�@@�@@�@@�@@�@@"���t�i�O�񒍕��j"�ł����<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪���A�A�������́}�w�l�w��s�̋敪�v�̗�O<BR>
     * �@@�@@�@@�@@�@@�@@��throw����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02253<BR>
     * <BR>
     * �@@�R�|�R�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪��<BR>
     * �@@�@@�@@�@@�@@�@@�w�肪�s�����v�̗�O��throw����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02254<BR>
     * <BR>
     * �S�j�@@����敪�`�F�b�N<BR>
     * �@@����敪��"�������t����"�̏ꍇ�́A<BR>
     * �@@�u����敪���A�������̏����ΏۊO�v�̗�O��throw����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02255<BR>
     * <BR>
     * �T�j�@@�A�������E���������`�F�b�N<BR>
     * �@@super.validate�A������()���R�[������B<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4326B5980347
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@super.validate()���R�[������B
        super.validate();//WEB3BusinessLayerException
        
        //�Q�j�@@�A���������ʏ��`�F�b�N
        //�@@�Q�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�A���������ʏ��w��Ȃ��v�̗�O���X���[����B
        if (this.succCommonInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                getClass().getName() + STR_METHOD_NAME,
                "�A���������ʏ�񂪖��w��ł��B");
        }

        //�@@�Q�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
        this.succCommonInfo.validate(); //WEB3BusinessLayerException

        //�@@�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO��
        //�@@�@@�@@�@@�@@�@@�ꍇ�A�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
        if (!WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.BUY.equals(this.succCommonInfo.succTradingType))
        {
            String l_strMessage = "�A����������敪�u" 
                + this.succCommonInfo.succTradingType 
                + "�v�̒l�������ΏۊO�ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //�R�j�@@�A�������P�������l���`�F�b�N
        if (this.priceAdjustmentValueInfo != null)
        {
            //�@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            //�@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
            this.priceAdjustmentValueInfo.validate();//WEB3BusinessLayerException

            //�@@�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
            //�@@�@@�@@�@@�@@�@@�A���������ʏ��.�A����������敪��
            //�@@�@@�@@�@@�@@�@@"���t�i�O�񒍕��j"�ł����
            //�@@�@@�@@�@@�@@�@@�u�A����������敪���A�A�������́}�w�l�w��s�̋敪�v�̗�O
            //�@@�@@�@@�@@�@@�@@��throw����B
            if (!WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02253,
                    getClass().getName() + STR_METHOD_NAME,
                    "�A����������敪���A�A�������́}�w�l�w��s�̋敪�ł��B");
            }

            //�@@�R�|�R�j�@@�A�������P�������l���null�̏ꍇ�A
            //�@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪��
            //�@@�@@�@@�@@�@@�@@�w�肪�s�����v�̗�O��throw����B
            if(!WEB3OrderPriceDivDef.MARKET_PRICE.equals(super.orderPriceDiv))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    getClass().getName() + STR_METHOD_NAME,
                    "�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");
            }
        }

        //�S�j�@@����敪�`�F�b�N
        //�@@����敪��"�������t����"�̏ꍇ�́A
        //�@@�u����敪���A�������̏����ΏۊO�v�̗�O��throw����B
        if (!WEB3TradingTypeDef.BUY_ORDER.equals(super.tradingType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02255,
                getClass().getName() + STR_METHOD_NAME,
                "����敪���A�������̏����ΏۊO�ł��B");
        }

        //�T�j�@@�A�������E���������`�F�b�N
        //�@@super.validate�A������()���R�[������B
        super.validateSuccOrder();//WEB3BusinessLayerException

        log.exiting(STR_METHOD_NAME);
     
    }
}
@
