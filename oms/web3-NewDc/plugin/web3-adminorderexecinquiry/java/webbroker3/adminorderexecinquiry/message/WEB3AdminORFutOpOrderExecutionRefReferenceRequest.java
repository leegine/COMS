head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFutOpOrderExecutionRefReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�敨OP�������Ɖ�N�G�X�g (WEB3AdminORFutOpOrderExecutionRefReferenceRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;


import webbroker3.adminorderexecinquiry.define.WEB3AdminKeyItemDef;
import webbroker3.adminorderexecinquiry.define.WEB3OptionProductTypeDef;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�敨OP�������Ɖ�N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE�敨OP�������Ɖ�N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminORFutOpOrderExecutionRefReferenceRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORFutOpOrderExecutionRefReferenceRequest
    extends WEB3AdminOROrderExecutionRefCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_fut_op_order_execution_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminORFutOpOrderExecutionRefReferenceRequest.class);

    /**
    * (�w�����)<BR>
    * <BR>
    * �w�����<BR>
    * <BR>
    * 0005�F�@@TOPIX<BR>
    * 0018�F�@@���o225<BR>
    * 0016�F�@@���o300 <BR>
    * 0019�F�~�j���o225<BR>
    * <BR>
    * -----<English>---------------<BR>
    * <BR>
    * targetProductCode<BR>
    * <BR>
    */
    public String targetProductCode = null;

    /**
     * (����)<BR>
     * <BR>
     * ����<BR>
     * (YYYYMM)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * delivaryMonth<BR>
     * (YYYYMM)<BR>
     * <BR>
     */
    public String delivaryMonth = null;

    /**
     * (�Es�g���i)<BR>
     * <BR>
     * �s�g���i<BR>
     * <BR>
     * strikePrice<BR>
     * <BR>
     */
    public String strikePrice = null;

    /**
     * (�I�v�V�������i�敪)<BR>
     * <BR>
     * �I�v�V�������i�敪<BR>
     * <BR>
     * P�F�@@�v�b�g<BR>
     * C�F�@@�R�[��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * opProductType<BR>
     * P: Def.PUT<BR>
     * C: Def.CALL<BR>
     * <BR>
     */
    public String opProductType = null;

    /**
     * @@roseuid 4212FBA202E6
     */
    public WEB3AdminORFutOpOrderExecutionRefReferenceRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���R�[������B<BR>
     * <BR>
     * �Q�j�w����ʃ`�F�b�N<BR>
     * �@@this.�w����ʁ�null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�jthis.�w����ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02441<BR>
     * �@@�Q�|�Q�jthis.�w����ʂ̌������S���ȊO�̒l�ł���Η�O���X���[����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02442<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01464<BR>
     * <BR>
     * �R�j�s�g���i�`�F�b�N <BR>
     * �@@this.�s�g���i != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.�s�g���i���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�s�g���i�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�Ethis.�s�g���i != ���l<BR>
     * �@@�@@�@@�@@�Ethis.�s�g���i < 0<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01475<BR>
     * <BR>
     * �S�j�I�v�V�������i�敪�`�F�b�N<BR>
     * �@@this.�I�v�V�������i�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�EN���s���B<BR>
     * �@@�S�|�P�jthis.�I�v�V�������i�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u�I�v�V�������i�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"�v�b�g"<BR>
     * �@@�@@�@@�@@�E"�R�[��"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00270<BR>
     * <BR>
     * �T�j�\�[�g�L�[�`�F�b�N<BR>
     * �@@�T�|�P�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�T�|�P�|�P�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��<BR>
     * �@@�@@�@@�ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�u���X�R�[�h�v<BR>
     * �@@�@@�@@�E�u�ڋq�R�[�h�v<BR>
     * �@@�@@�@@�E�u���҃R�[�h�iSONAR�j�v<BR>
     * �@@�@@�@@�E�u�����R�[�h�v<BR>
     * �@@�@@�@@�E�u�s��R�[�h�v<BR>
     * �@@�@@�@@�E�u����敪�v<BR>
     * �@@�@@�@@�E�u�������ԁv<BR>
     * �@@�@@�@@�E�u�������v<BR>
     * �@@�@@�@@�E�u���s�����v<BR>
     * �@@�@@�@@�E�u�E��������v<BR>
     * �@@�@@�@@�E�u���������v<BR>
     * �@@�@@�@@�E�u��n���v<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)Call super.validate()<BR>
     * <BR>
     * 2)targetProductCode check<BR>
     * �@@Check the following if this.targetProductCode != null<BR>
     * �@@2-1)If this.targetProductCode meets with the following conditions<BR>
     * �@@�@@�@@�@@Throw the exception "targetProductCode has an undefined value"<BR>
     * �@@�@@�@@�@@�EDef.TOPIX<BR>
     * �@@�@@�@@�@@�EDef.NK225<BR>
     * �@@�@@�@@�@@�EDef.NK300<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01464<BR>
     * <BR>
     * 3)strikePrice check<BR>
     * �@@Check the followings if this.strikePrice != null<BR>
     * �@@3-1)If this.strikePrice meets with the following conditions,<BR>
     * �@@�@@�@@�@@Throw the exception "strikePrice error"<BR>
     * �@@�@@�@@�@@�Ethis.strikePrice != numerical value<BR>
     * �@@�@@�@@�@@�Ethis.strikePrice < 0<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01475<BR>
     * <BR>
     * 4)opProductType check<BR>
     * �@@Check the followings if thisopProductType != null<BR>
     * �@@4-1)If this.opProductType contains values other than the followings,<BR>
     * �@@�@@�@@�@@Throw the exception "opProductType has an undefined value"<BR>
     * �@@�@@�@@�@@�EDef.PUT_OPTIONS<BR>
     * �@@�@@�@@�@@�EDef.CALL_OPTIONS<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00270<BR>
     * <BR>
     * 5)sortKeys check<BR>
     * �@@5-1)Loop for as many times as the number of elements in this.sortKeys<BR>
     * �@@�@@5-1-1)If sortKeys.keyItem contains values other than the followings,<BR>
     * �@@�@@�@@Throw the exception "sortKeys.keyItem has an undefined value"<BR>
     * �@@�@@�@@�EbranchCode<BR>
     * �@@�@@�@@�EaccountCode<BR>
     * �@@�@@�@@�EproductCode<BR>
     * �@@�@@�@@�EmarketCode<BR>
     * �@@�@@�@@�EtradingDiv<BR>
     * �@@�@@�@@�EorderDate<BR>
     * �@@�@@�@@�EorderBizDate<BR>
     * �@@�@@�@@�EexecCondType<BR>
     * �@@�@@�@@�EexpirationDateType<BR>
     * �@@�@@�@@�EorderCondType<BR>
     * �@@�@@�@@�EdeliveryDate<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41ADC837035B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intMin = 0;
        int l_intSortKeysLength = 0;

        //1-1 Call super.validate()
        super.validate();

        // 2-1 if targetProductCode is not any of Def, throw Exception.
        if (this.targetProductCode != null)
        {
            //�@@�Q�|�P�jthis.�w����ʂ������ȊO�̒l�ł���Η�O���X���[����B
            if (!WEB3StringTypeUtility.isNumber(this.targetProductCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02441,
                    getClass().getName() + "validate",
                    "�w����ʂ������ȊO�̒l�ł��B");
            }
            
            //�@@�Q�|�Q�jthis.�w����ʂ̌������S���ȊO�̒l�ł���Η�O���X���[����B
            if (this.targetProductCode.length() != 4)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02442,
                    getClass().getName() + "validate",
                    "�w����ʂ̃T�C�Y���s���ł��B");
            }
        }

        // 3-1 if strikePrice is not numeric & is less than 0, throw Exception.
        if (this.strikePrice != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(strikePrice))
                || (Double.parseDouble(this.strikePrice) < l_intMin))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01475,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //4-1 if opProductType is not any of Def, throw Exception.
        if (this.opProductType != null)
        {
            if ((!WEB3OptionProductTypeDef.PUT_OPTIONS.equals(opProductType))
            && (!WEB3OptionProductTypeDef.CALL_OPTIONS.equals(opProductType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 5-1 if sortKeys.keyItem is equal to Def, throw Exception.
        l_intSortKeysLength = this.sortKeys.length;
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            if ((!WEB3AdminKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.SONAR_TRADER_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_DATE.equals(this.sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem))
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
     * @@see webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest#
     *  createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORFutOpOrderExecutionRefReferenceResponse(this);
    }
}
@
