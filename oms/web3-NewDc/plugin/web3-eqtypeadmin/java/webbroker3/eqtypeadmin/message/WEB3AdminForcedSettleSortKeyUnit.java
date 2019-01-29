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
filename	WEB3AdminForcedSettleSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������σ\�[�g�L�[(WEB3AdminForcedSettleSortKeyUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 ��іQ (���u) �V�K�쐬 ���f��No.128
Revision History : 2007/09/11 �đo�g (���u) ���f��No.164
*/

package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�������σ\�[�g�L�[)<BR>
 * �������σ\�[�g�L�[�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminForcedSettleSortKeyUnit extends Message
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminForcedSettleSortKeyUnit.class);

    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     * ���g�p����L�[���ڂ�validate()���\�b�h���ɂĒ�`�B<BR>
     */
    public String keyItem;

    /**
     * (�����^�~��)<BR>
     * �����^�~��<BR>
     * <BR>
     * A�F�@@����<BR>
     * D�F�@@�~��<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 462CA4290227
     */
    public WEB3AdminForcedSettleSortKeyUnit()
    {

    }

    /**
     * �P�jthis.�L�[���� == null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�j this.�L�[���ڂɉ��L�̍��ڈȊO��<BR>
     * �@@�ݒ肳��Ă�����A<BR>
     * �@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�E"���X�R�[�h"<BR>
     * �@@�@@�E"�ڋq�R�[�h"<BR>
     * �@@�@@�E"�������ϗ��R"<BR>
     * �@@�@@�E"�s��R�[�h"<BR>
     * �@@�@@�E"�����R�[�h"<BR>
     * �@@�@@�E"�����敪"<BR>
     * �@@�@@�E"���敪"<BR>
     * �@@�@@�E"�ٍϋ敪"<BR>
     * �@@�@@�E"�ٍϊ����l"<BR>
     * �@@�@@�E"����"<BR>
     * �@@�@@�E"���ϊ���"<BR>
     * �@@�@@�E"�쐬����"<BR>
     * �@@�@@�E"�i��j���F����"<BR>
     * �@@�@@�E"������"<BR>
     * �@@�@@�E"���P��"<BR>
     * �@@�@@�E"�����"<BR>
     * �@@�@@�E"��������"<BR>
     * �@@�@@�E"�ۏ؋��a����"<BR>
     * �@@�@@�E"���F���"<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00086<BR>
     * <BR>
     * �R�jthis.�����^�~����null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00087<BR>
     * <BR>
     * �S�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�hA�F�����h <BR>
     * �@@�@@�@@�E�hD�F�~���h <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws  WEB3BaseException
     * @@roseuid 45FF628E03A9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //this.�L�[���� == null�̏ꍇ�A<BR>
        //�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
        if (this.keyItem == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
        }

        //* �Q�j this.�L�[���ڂɉ��L�̍��ڈȊO��<BR>
        //* �@@�ݒ肳��Ă�����A<BR>
        //* �@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B
        if (this.keyItem != null)
        {
            if (!WEB3AdminEquitySortKeyItemNameDef.BRANCH_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.ACCOUNT_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.FORCED_SETTLE_REASON.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.MARKET_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.PRODUCT_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.TAX_TYPE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CONTRACT_TYPE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.REPAYMENT_DIV.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.REPAYMENTTIME_LIMIT.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.OPEN_DATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CLOSE_DATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CREATE_DATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.APPROVE_DATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CONTRACT_QUANTITY.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CONTRACT_PRICE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CONTRACT_EXEC_PRICE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.ORDER_QUANTITY.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.MARGIN_COLLATERAL_RATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.APPROVE_STATE.equals(this.keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        //this.�����^�~����null�̏ꍇ�A
        //�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B
        if (this.ascDesc == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�~�������w��ł��B");
        }

        //�S�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
        //�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
        if (this.ascDesc != null)
        {
            if (!WEB3AscDescDef.ASC.equals(this.ascDesc)
                && !WEB3AscDescDef.DESC.equals(this.ascDesc))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
