head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOREquityOrderExecutionRefReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����������Ɖ�N�G�X�g (WEB3AdminOREquityOrderExecutionRefReferenceRequest.java)
Author Name      : Daiwa Institute of Research
                 : 2006/08/30 �����F(���u) ���f�� 057
Revision History : 2007/10/09 �����q(���u) �d�l�ύX ���f��No.106
Revision History : 2008/01/23 �g�E�N�|(���u) �d�l�ύX ���f��No.109
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.adminorderexecinquiry.define.WEB3AdminKeyItemDef;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�����������Ɖ�N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE�����������Ɖ�N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminOREquityOrderExecutionRefReferenceRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOREquityOrderExecutionRefReferenceRequest
    extends WEB3AdminOROrderExecutionRefCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_equity_order_execution_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOREquityOrderExecutionRefReferenceRequest.class);

    /**
     * (�����R�[�h)<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode = null;

    /**
     * (�s��R�[�h)<BR>
     * <BR>
     * �s��R�[�h<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     */
    public String marketCode = null;

    /**
     * (�����敪)<BR>
     * <BR>
     * �����敪<BR>
     * <BR>
     * 0�F�@@���<BR>
     * 1�F�@@����<BR>
     * 5�F�@@�X�g�b�N�I�v�V����<BR>
     * <BR>
     */
    public String taxType = null;

    /**
     * (�ٍϋ敪)<BR>
     * <BR>
     * �ٍϋ敪<BR>
     * <BR>
     * 1�F�@@���x�M�p<BR>
     * 2�F�@@��ʐM�p<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * repaymentDiv<BR>
     * <BR>
     * 1: Def.REPAYMENT_DIV_MARGIN_SYS<BR>
     * 2: Def.REPAYMENT_DIV_MARGIN_GEN<BR>
     * <BR>
     */
    public String repaymentDiv = null;

    /**
     * (�l�i����)<BR>
     * <BR>
     * �l�i����<BR>
     * <BR>
     * 0�F�@@�w��Ȃ�<BR>
     * 1�F�@@���ݒl�w�l����<BR>
     * 3�F�@@�D��w�l����<BR>
     * 5�F�@@���s�c���w�l����<BR>
     * 7�F�@@���s�c���������<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * priceCondType<BR>
     * <BR>
     * 0: Def.DEFAULT<BR>
     * 1: Def.PRESENT_VALUE_LIMIT_PRICE_ORDER <BR>
     * 3: Def.PRIORITY_LIMIT_PRICE_ORDER <BR>
     * 5: Def.PARTIALLY_LIMIT_PRICE_ORDER<BR>
     * 7: Def.PARTIALLY_CANCEL_ORDER <BR>
     * <BR>
     */
    public String priceCondType = null;

    /**
     * (�������ϒ����t���O)<BR>
     * <BR>
     * false:�w��Ȃ�<BR>
     * true:�������ϒ���<BR>
     */
    public boolean forcedSettleFlag;

    /**
     * @@roseuid 4212FB35024A
     */
    public WEB3AdminOREquityOrderExecutionRefReferenceRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���R�[������B<BR>
     * <BR>
     * �Q�j�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�����R�[�h != ����<BR>
     * �@@�@@�@@�@@�E�����R�[�h.length != 5<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * �R�j�s��R�[�h�`�F�b�N<BR>
     * �@@this.�s��R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.�s��R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�@@�E"���"<BR>
     * �@@�@@�@@�@@�E"���É�"<BR>
     * �@@�@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�@@�E"�D�y"<BR>
     * �@@�@@�@@�@@�E"NNM"<BR>
     * �@@�@@�@@�@@�E"JASDAQ"<BR>
     * �@@�@@�@@�@@�E"JNX-PTS"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * �S�j�����敪�`�F�b�N<BR>
     * �@@this.�����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S�|�P�jthis.�����敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u�����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"���"<BR>
     * �@@�@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�@@�E"�X�g�b�N�I�v�V����" <BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00605<BR>
     * <BR>
     * �T�j�ٍϋ敪�`�F�b�N<BR>
     * �@@this.�ٍϋ敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�T�|�P�jthis.�ٍϋ敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u�ٍϋ敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"���x�M�p"<BR>
     * �@@�@@�@@�@@�E"��ʐM�p"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00629<BR>
     * <BR>
     * �U�j�l�i�����`�F�b�N<BR>
     * �@@this.�l�i���� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�U�|�P�jthis.�l�i�����ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u�l�i����������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"�w��Ȃ�"<BR>
     * �@@�@@�@@�@@�E"���ݒl�w�l����"<BR>
     * �@@�@@�@@�@@�E"�D��w�l����"<BR>
     * �@@�@@�@@�@@�E"���s�c���w�l����"<BR>
     * �@@�@@�@@�@@�E"���s�c���������"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01344<BR>
     * <BR>
     * �V�j�\�[�g�L�[�`�F�b�N<BR>
     * �@@�V�|�P�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�V�|�P�|�P�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��<BR>
     * �@@�@@�@@�ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�u���X�R�[�h�v<BR>
     * �@@�@@�@@�E�u�ڋq�R�[�h�v<BR>
     * �@@�@@�@@�E�u���҃R�[�h(SONAR)�v<BR>
     * �@@�@@�@@�E�u�����R�[�h�v<BR>
     * �@@�@@�@@�E�u�s��R�[�h�v<BR>
     * �@@�@@�@@�E�u�����敪�v<BR>
     * �@@�@@�@@�E�u����敪�v<BR>
     * �@@�@@�@@�E�u�������ԁv<BR>
     * �@@�@@�@@�E�u�������v<BR>
     * �@@�@@�@@�E�u�ٍϋ敪�v<BR>
     * �@@�@@�@@�E�u�l�i�����v<BR>
     * �@@�@@�@@�E�u���s�����v<BR>
     * �@@�@@�@@�E�u���������v<BR>
     * �@@�@@�@@�E�u���������v<BR>
     * �@@�@@�@@�E�u��n���v<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41A5BBF101F5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intFive = 5;
        int l_intSortkeysLength  = 0;

        // 1-1 Call super.validate()
        super.validate();

        // 2-1 if productCode is not Numeric & length of productCode is not 5, throw Exception.
        if (this.productCode != null)
        {
            if (!(WEB3StringTypeUtility.isNumber(this.productCode))
                || (WEB3StringTypeUtility.getByteLength(this.productCode) != l_intFive))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if marketCode is not any of Def, throw Exception.
        if (this.marketCode != null)
        {
            if ((!WEB3MarketCodeDef.TOKYO.equals(this.marketCode))
                && (!WEB3MarketCodeDef.OSAKA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.NAGOYA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode))
                && (!WEB3MarketCodeDef.SAPPORO.equals(this.marketCode))
                && (!WEB3MarketCodeDef.NNM.equals(this.marketCode))
                && (!WEB3MarketCodeDef.JASDAQ.equals(this.marketCode))
                && (!WEB3MarketCodeDef.JNX_PTS.equals(this.marketCode)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 if taxType is not any of Def throw Exception.
        if (this.taxType != null)
        {
            if ((!WEB3TaxTypeDef.NORMAL.equals(this.taxType))
                && (!WEB3TaxTypeDef.SPECIAL.equals(this.taxType))
                && (!WEB3TaxTypeDef.STOCK_OPTION.equals(this.taxType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00605,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 5-1 if repaymentDiv is not any of Def throw Exception.
        if (this.repaymentDiv != null)
        {
            if ((!WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(this.repaymentDiv))
                && (!WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(this.repaymentDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00629,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 6-1 if priceCondType is not any of Def, throw Exception.
        if (this.priceCondType != null)
        {
            if ((!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType))
                && (!WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals
                    (this.priceCondType))
                && (!WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(this.priceCondType))
                && (!WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(this.priceCondType))
                && (!WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(this.priceCondType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01344,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 7-1 if sortkeys.keyItem is not any of Def values, throw Exception.
        l_intSortkeysLength = this.sortKeys.length;
        for (int i = 0; i < l_intSortkeysLength; i++)
        {
            if ((!WEB3AdminKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.SONAR_TRADER_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.TAX_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_DATE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.REPAYMENT_DIV.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.PRICE_COND_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.EXEC_COND_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.EXPIRATION_DATE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_COND_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.DELIVERY_DATE.equals(this.sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse WEB3GenResponse
     * @@see webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest#
     * createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminOREquityOrderExecutionRefReferenceResponse(this);
    }

}
@
