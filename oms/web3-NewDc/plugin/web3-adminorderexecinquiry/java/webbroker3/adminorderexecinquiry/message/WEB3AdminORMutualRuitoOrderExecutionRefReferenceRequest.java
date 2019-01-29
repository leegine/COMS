head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���M�ݓ��������Ɖ�N�G�X�g(WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/23 ���� (���u) �d�l�ύX�E���f��080
Revesion History : 2007/02/26 �����(���u)�d�l�ύX���f��No.094
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.adminorderexecinquiry.define.WEB3AdminKeyItemDef;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DeliveryMethodDef;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE���M�ݓ��������Ɖ�N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE���M�ݓ��������Ɖ�N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest
    extends WEB3AdminOROrderExecutionRefCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_mutual_ruito_order_execution_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * ����ID<BR>
     * <BR>
     * productId<BR>
     * <BR>
     */
    public String productId = null;

    /**
     * (�����敪)<BR>
     * <BR>
     * �����敪<BR>
     * <BR>
     * 0�F�@@���<BR>
     * 1�F�@@����<BR>
     * 2�F�@@���̑�<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * taxType<BR>
     * 0: Def.NORMAL<BR>
     * 1: Def.SPECIAL<BR>
     * <BR>
     */
    public String taxType = null;

    /**
     * (��n���@@)<BR>
     * <BR>
     * ��n���@@<BR>
     * <BR>
     * 1�F�@@��s�U��<BR>
     * 2�F�@@�،���������<BR>
     * 3�F�@@���֌W<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * deliveryDiv<BR>
     * 1: Def.BANK_TRANSFER<BR>
     * 2: Def.SECURITIES_ACCOUNT_INPUT_SELL<BR>
     * 3: Def.IRRELEVENT_BUY<BR>
     * <BR>
     */
    public String deliveryDiv = null;

    /**
     * (���M�E�O��MMF�\���敪)<BR>
     * ���M��O��MMF�\���敪<BR>
     * <BR>
     * �\���Ώۂ̖������A���M��O��MMF�Ő؂�ւ��邽�߂̋敪<BR>
     * <BR>
     * 0:���M�̂�<BR>
     * 1:�O��MMF�̂�<BR>
     * 2:����<BR>
     * <BR>
     * ��null�̏ꍇ�A�u2:�����v�Ƃ���<BR>
     */
    public String mutualFrgnMmfDisplayDiv = null;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest.class);

    /**
     * @@roseuid 4212FC1C0259
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���R�[������B<BR>
     * <BR>
     * �Q�j�����敪�`�F�b�N<BR>
     * �@@this.�����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�jthis.�����敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E���<BR>
     *             �E����<BR>
     *             �@@�E"���̑�"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00605<BR>
     * <BR>
     * �R�j��n���@@�`�F�b�N<BR>
     * �@@this.��n���@@ != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.��n���@@���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u��n���@@������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E��s�U��<BR>
     * �@@�@@�@@�@@�@@�E �،���������<BR>
     * �@@�@@�@@�@@�@@�E���֌W<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00099<BR>
     * <BR>
     * �S�j�\�[�g�L�[�`�F�b�N<BR>
     * �@@�S�|�P�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�S�|�P�|�P�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��<BR>
     * �@@�@@�@@�ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�u���X�R�[�h�v<BR>
     * �@@�@@�@@�E�u�ڋq�R�[�h�v<BR>
     * �@@�@@�@@�E�u�����R�[�h�v<BR>
     * �@@�@@�@@�E�u����敪�v<BR>
     * �@@�@@�@@�E�u�������ԁv<BR>
     * �@@�@@�@@�E�u�������v<BR>
     * �@@�@@�@@�E�u��n���v<BR>
     * �@@�@@�@@�E�u���҃R�[�h(SONAR)�v<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * 1)Call super.validate()<BR>
     * <BR>
     * 2)taxType check<BR>
     * �@@Check the followings if this.taxType!= null<BR>
     * �@@2-1)If this.taxType contains values other than the following values<BR>
     * �@@�@@�@@�@@Throw the exception "taxType has an undefined value"<BR>
     * �@@�@@�@@�@@�EDef.NORMAL<BR>
     *          �EDef.SPECIAL<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00605<BR>
     * <BR>
     * 3)deliveryDiv check<BR>
     * �@@Check the followings if this.deliveryDiv != nullBR>
     * �@@�@@3�|�P)If this.deliveryDiv contains values other than the followings<BR>
     * �@@�@@�@@�@@Throw the exception "deliveryDiv has an undefined value"<BR>
     * �@@�@@�@@�@@ �EDef.BANK_TRANSFER<BR>
     * �@@        �EDef.SECURITIES_ACCOUNT_INPUT_SELL<BR>
     *           �EDef.IRRELEVENT_BUY<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00099<BR>
     * <BR>
     * 4)sortKeys check<BR>
     * �@@4-1)Loop for as many times as the number of elements in this.sortKeys<BR>
     * �@@�@@4-1-1)If sortKeys.keyItem contains values other than the followings,<BR>
     * �@@�@@�@@Throw the exception "sortKeys.keyItem has an undefined value"<BR>
     * �@@�@@�@@�EbranchCode<BR>
     * �@@�@@�@@�EaccountCode<BR>
     * �@@�@@�@@�EproductCode<BR>
     * �@@�@@�@@�EtradingDiv<BR>
     * �@@�@@�@@�EorderDate<BR>
     * �@@�@@�@@�EorderBizDate<BR>
     * �@@�@@�@@�EdeliveryDate<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     *  @@roseuid 41ADC95F0163
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_intsortKeysLength = 0;

        // 1-1 call super.validate()
        super.validate();

        // 2-1 If taxType is not any of Df files, throw Exception
        if (this.taxType != null)
        {
            if ((!WEB3MFAccountDivDef.NORMAL.equals(this.taxType))
                && (!WEB3MFAccountDivDef.SPECIAL.equals(this.taxType))
                && (!WEB3MFAccountDivDef.OTHER.equals(this.taxType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00605,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if deliveryDiv is not any of Def values, throw Exception
        if (this.deliveryDiv != null)
        {
            if ((!WEB3DeliveryMethodDef.BANK_TRANSFER.equals(deliveryDiv))
                && (!WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL.equals(deliveryDiv))
                && (!WEB3DeliveryMethodDef.IRRELEVENT_BUY.equals(deliveryDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00099,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 if sortKeys[i].keyItem is not any of Def values, throw Exception.
        l_intsortKeysLength = sortKeys.length;
        for (int i = 0; i < l_intsortKeysLength; i++)
        {
            if ((!WEB3AdminKeyItemDef.BRANCH_CODE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ACCOUNT_CODE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.PRODUCT_CODE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.TRADING_TYPE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_DATE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.ORDER_BIZ_DATE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.DELIVERY_DATE.equals(sortKeys[i].keyItem))
                && (!WEB3AdminKeyItemDef.SONAR_TRADER_CODE.equals(sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse(this);
    }
}@
