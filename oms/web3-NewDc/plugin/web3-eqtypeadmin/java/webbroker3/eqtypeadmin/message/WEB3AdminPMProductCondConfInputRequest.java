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
filename	WEB3AdminPMProductCondConfInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������������ݒ���̓��N�G�X�g(WEB3AdminPMProductCondConfInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.eqtypeadmin.define.WEB3AdminEquityLargeItemDivDef;

/**
 * (�Ǘ��ҁE�������������ݒ���̓��N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE�������������ݒ���̓��N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondConfInputRequest<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_conf_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    public final static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPMProductCondConfInputRequest.class);

    /**
     * �i�����R�[�h�j<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * �i�區�ڋ敪�ꗗ�j <BR>
     * <BR>
     * �區�ڋ敪�ꗗ <BR>
     * <BR>
     * ����`�l�ɂ��Ă�DB���C�A�E�g <BR>
     * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
     * <BR>
     * -----------<English>------------<BR>
     * <BR>
     * largeItemList<BR>
     * <BR>
     * ��Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     */
    public String[] largeItemList;
    /**
     * @@roseuid 41FA29E001E5
     */
    public WEB3AdminPMProductCondConfInputRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�����R�[�h�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�����R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�uthis.�����R�[�h��null�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͖����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�Ethis.�����R�[�h.length != 5<BR>
     * �@@�@@�@@�@@�@@�Ethis.�����R�[�h != ���l<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * �Q�j�區�ڋ敪�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�區�ڋ敪 == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�區�ڋ敪��null�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01447<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�區�ڋ敪.length == 0�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�u�區�ڋ敪�̗v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01448<BR>
     * <BR>
     * �@@�Q�|�R�jthis.�區�ڋ敪�̒l���A�ȉ��̒l�̂��Â�ɂ�<BR>
     * �@@�@@�@@�@@�@@�Y�����Ȃ��ꍇ�A�u�區�ڋ敪������`�̒l�v��<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E"����K��"<BR>
     * �@@�@@�@@�@@�@@�E"��{���"<BR>
     * �@@�@@�@@�@@�@@�E"�M�p�������"<BR>
     * �@@�@@�@@�@@�@@�E"�ϑ��ۏ؋���"<BR>
     * �@@�@@�@@�@@�@@�E"��p�L���،����"<BR>
     * �@@�@@�@@�@@�@@�E"�l�i���"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01449<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1) productCode check<BR>
     * �@@1-1)If this.productCode == null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "this.productCode is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * �@@1-2)If this. productCode meets with the following conditions<BR>
     * �@@�@@�@@�@@�@@Throw the exception "input productCode  error"<BR>
     * �@@�@@�@@�@@�@@�Ethis. productCode.length != 5<BR>
     * �@@�@@�@@�@@�@@�Ethis. productCode != numerical value<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * 2)largeItemList check<BR>
     * �@@2-1) If this.largeItemList == null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "largeItemList is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01447<BR>
     * <BR>
     * �@@2-2) If this.largeItemList.length == 0<BR>
     * �@@�@@�@@�@@�@@Throw the exception "elements of largeItemList is 0"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01448<BR>
     * <BR>
     * �@@2-3)The value of this.largeItemList meets neither of the following values,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "largeItemList is an undefined value"<BR>
     * �@@�@@�@@�@@�@@�E"Def.TRADING_REGULATION"<BR>
     * �@@�@@�@@�@@�@@�E"Def.BASIC_INFO"<BR>
     * �@@�@@�@@�@@�@@�E"Def.MARGIN_PRODUCT_INFO"<BR>
     * �@@�@@�@@�@@�@@�E"Def.DEPOSIT_RATE"<BR>
     * �@@�@@�@@�@@�@@�E"Def.SUBSTITUTE_SECURITY_INFO"<BR>
     * �@@�@@�@@�@@�@@�E"Def.PRICE_INFO"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01449<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 4181A87702E0
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        final int l_intFive = 5;
        int l_productCodeProductByteLength = WEB3StringTypeUtility.getByteLength(this.productCode);
        int l_intlength = 0;

        // 1-1productCode is null throw exception
        if (this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            // 1-2 if productCode length!=5 and productCode is not numeric throw exception
        }
        if ((l_productCodeProductByteLength != l_intFive)
            || (!WEB3StringTypeUtility.isNumber(this.productCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1if largeItemList is null throw exception
        if (this.largeItemList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01447,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            // 2-2 largeItemList length is zero throw exception
        }
        if (this.largeItemList.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01448,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            // 2-3 if largeItemList is not equals to following values throw exception
        }
        l_intlength = largeItemList.length;
        for (int i = 0; i < l_intlength; i++)
        {
            if ((!WEB3AdminEquityLargeItemDivDef.TRADING_REGULATION.equals(largeItemList[i]))
                && (!WEB3AdminEquityLargeItemDivDef.BASIC_INFO.equals(largeItemList[i]))
                && (!WEB3AdminEquityLargeItemDivDef.MARGIN_PRODUCT_INFO.equals(largeItemList[i]))
                && (!WEB3AdminEquityLargeItemDivDef.DEPOSIT_RATE.equals(largeItemList[i]))
                && (!WEB3AdminEquityLargeItemDivDef.SUBSTITUTE_SECURITY_INFO
                .equals(largeItemList[i]))
                && (!WEB3AdminEquityLargeItemDivDef.PRICE_INFO.equals(largeItemList[i])))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01449,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMProductCondConfInputResponse(this);
    }
}
@
