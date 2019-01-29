head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IfoDepShortageSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����s���󋵃\�[�g�L�[(WEB3IfoDepShortageSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 ���ʗ�(���u) �V�K�쐬 ���f��No.004
*/
package webbroker3.ifoadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoSortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�؋����s���󋵃\�[�g�L�[)<BR>
 * �؋����s���󋵃\�[�g�L�[�N���X<BR>
 * <BR>
 * @@author ���ʗ�(���u)
 * @@version 1.0
 */
public class WEB3IfoDepShortageSortKey extends Message
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepShortageSortKey.class);

    /**
     * (�L�[����)<BR>
     * �L�[���� <BR>
     * <BR>
     * ���g�p����L�[���ڂ�validate()���\�b�h���ɂĒ�`�B<BR>
     * <BR>
     */
    public String keyItem;

    /**
     * (�����^�~��)<BR>
     * �����^�~�� <BR>
     * <BR>
     * A�F�@@���� <BR>
     * D�F�@@�~��<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 49A748560177
     */
    public WEB3IfoDepShortageSortKey()
    {

    }

    /**
     * �P�jthis.�L�[���� == null�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�j this.�L�[���ڂɉ��L�̍��ڈȊO�� <BR>
     * �@@�ݒ肳��Ă�����A <BR>
     * �@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�E"���X�R�[�h" <BR>
     * �@@�@@�E"�ڋq�R�[�h" <BR>
     * �@@�@@�E"�����z" <BR>
     * �@@�@@�E"���ݖ������z" <BR>
     * �@@�@@�E"���ݏ؋������v�z" <BR>
     * �@@�@@�E"���ʗL���t���O" <BR>
     * �@@�@@�E"�����L���t���O" <BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00086<BR>
     * �@@�@@<BR>
     * �R�jthis.�����^�~����null�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00087<BR>
     * <BR>
     * �S�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E�hA�F�����h <BR>
     * �@@�@@�@@�E�hD�F�~���h <BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4990E07D01CF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�jthis.�L�[���� == null�̏ꍇ�A
        //�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
        if (this.keyItem == null)
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
        }

        //�Q�j this.�L�[���ڂɉ��L�̍��ڈȊO��
        // �@@�ݒ肳��Ă�����A
        // �@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B
        if (!WEB3AdminIfoSortKeyItemNameDef.BRANCH_CODE.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.ACCOUNT_CODE.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.CLAIM_AMOUNT.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.CUR_NON_PAY_AMT.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.CUR_IFO_DEPOSIT_NECESSARY_AMT.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.CONTRACT_EXIST_FLAG.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.ORDER_EXIST_FLAG.equals(this.keyItem))
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�R�jthis.�����^�~����null�̏ꍇ�A
        //�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B
        if (this.ascDesc == null)
        {
            log.debug("�����^�~�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�~�������w��ł��B");
        }

        //�S�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A
        //�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc)
            && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.debug("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
