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
filename	WEB3AdminPMAccTradeStopSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�����ʎ����~�ꗗ�\�[�g�L�[ (WEB3AdminPMAccTradeStopSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 �������F(SRA) �V�K�쐬
*/
package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;

import webbroker3.util.WEB3LogUtility;

import webbroker3.eqtypeadmin.define.WEB3AdminKeyItemDef;

/**
 * (�ڋq�����ʎ����~�ꗗ�\�[�g�L�[)<BR>
 * <BR>
 * �ڋq�����ʎ����~�ꗗ�\�[�g�L�[�N���X<BR>
 * <BR>
 * WEB3AdminPMAccTradeStopSortKey<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccTradeStopSortKey extends Message
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccTradeStopSortKey.class);

    /**
     * �i�L�[���ځj<BR>
     * <BR>
     * �L�[����<BR>
     * <BR>
     * �E"���X�R�[�h"<BR>
     * �E"�ڋq�R�[�h"<BR>
     * �E"�����R�[�h"<BR>
     * �E"�L������From"<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * keyItem<BR>
     * <BR>
     * �E"Def.BRANCH_CODE"<BR>
     * �E"Def.ACCOUNT_CODE"<BR>
     * �E"Def.PRODUCT_CODE"<BR>
     * �E"Def.TERM_FROM"<BR>
     * <BR>
     */
    public String keyItem;

    /**
     * �i�����^�~���j<BR>
     * <BR>
     * �����^�~��<BR>
     * <BR>
     * A�F�@@����<BR>
     * D�F�@@�~��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * ascDesc<BR>
     * <BR>
     * A: Def.ASC<BR>
     * D: Def.DESC<BR>
     * <BR>
     */

    public String ascDesc;

    /**
     * @@roseuid 41FD9301033C
     */
    public WEB3AdminPMAccTradeStopSortKey()
    {

    }

    /**
     * �P�jthis.�L�[���ځ�null�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�jthis.�L�[���ڂɉ��L�̍��ڈȊO�� <BR>
     * �@@�@@�ݒ肳��Ă�����A <BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�E"���X�R�[�h"<BR>
     * �@@�@@�E"�ڋq�R�[�h"<BR>
     * �@@�@@�E"�����R�[�h"<BR>
     * �@@�@@�E"�L������From"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * �R�jthis.�����^�~����null�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * �S�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E�hA�F�����h <BR>
     * �@@�@@�@@�E�hD�F�~���h <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * 1) If this.keyItem��null<BR>
     * �@@�@@Throw the exception "sortKeys.keyItem is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * 2)If one of the following items is not set into this.keyItem<BR>
     * �@@�@@Throw the exception "sortKeys.keyItem is an undefined value"<BR>
     *   �E"Def.BRANCH_CODE"<BR>
     *   �E"Def.ACCOUNT_CODE"<BR>
     *   �E"Def.PRODUCT_CODE"<BR>
     *   �E"Def.TERM_FROM"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * 3) If this.ascDesc��null<BR>
     * �@@�@@Throw the exception "sortKeys.ascDesc is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * �S�j If this.ascDesc is neither of the following items <BR>
     * �@@�@@Throw the exception "sortKeys.ascDesc is an undefined value"<BR>
     *   �E�hA: Def.ASC<BR>�h
     *   �E�hD: Def.DESC�h <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@roseuid 4198886F02F5
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 if keyitem is null throw Exception.
        if (keyItem == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-1 if key item is not set to Def values, throw Exception.
            if (!(WEB3AdminKeyItemDef.BRANCH_CODE.equals(keyItem))
                && !(WEB3AdminKeyItemDef.ACCOUNT_CODE.equals(keyItem))
                && !(WEB3AdminKeyItemDef.PRODUCT_CODE.equals(keyItem))
                && !(WEB3AdminKeyItemDef.TERM_FROM.equals(keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if ascdes is not equal to null, throw Exception.
        if (this.ascDesc == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 4-1 if ascDesc is not of def, throw Exception
            if (!(WEB3AscDescDef.ASC.equals(ascDesc)) && (!(WEB3AscDescDef.DESC.equals(ascDesc))))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
