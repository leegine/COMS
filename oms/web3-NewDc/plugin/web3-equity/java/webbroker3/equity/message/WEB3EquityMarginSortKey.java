head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarginSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����E�M�p�\�[�g�L�[(WEB3EquityMarginSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 �֔�(���u) �V�K�쐬
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����E�M�p�\�[�g�L�[)<BR>
 * �����E�M�p�\�[�g�L�[�N���X<BR>
 * @@author  �֔�
 * @@version 1.0
 */
public class WEB3EquityMarginSortKey extends Message
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityMarginSortKey.class);

    /**
     * (�L�[����)<BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{������<BR>
     * �L�[���ڂƂ���B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;

    /**
     * (�����^�~��)<BR>
     * A�F ����<BR>
     * D�F �~��<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 45A33C7B00BB
     */
    public WEB3EquityMarginSortKey()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�jthis.�L�[���� �� null�̏ꍇ�A<BR>
     *    �u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�jthis.�����^�~�� �� null�̏ꍇ�A<BR>
     *    �u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00087<BR>
     * <BR>
     * �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
     *    �u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B<BR>
     * <BR>
     *     �E�h�����h<BR>
     *     �E�h�~���h<BR>
     * <BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws webbroker3.equity.common.WEB3BaseException
     * @@roseuid 455C38990029
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validate()";
        log.entering(STR_METHOD_NAME);

        // �P�jthis.�L�[���� �� null�̏ꍇ�A
        //    �u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
        //
        // �@@�@@class : WEB3BusinessLayerException
        // �@@�@@tag   : BUSINESS_ERROR_00085
        if (this.keyItem == null)
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + STR_METHOD_NAME,
                "�L�[���� = " + this.keyItem);
        }

        // �Q�jthis.�����^�~�� �� null�̏ꍇ�A
        //    �u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B
        //
        // �@@�@@class : WEB3BusinessLayerException
        // �@@�@@tag   : BUSINESS_ERROR_00087
        if (this.ascDesc == null)
        {
            log.debug("�����^�~�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����^�~�� = " + this.ascDesc);
        }

        // �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A
        //    �u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
        //
        //     �E�h�����h
        //     �E�h�~���h
        //
        // �@@�@@class : WEB3BusinessLayerException
        // �@@�@@tag   : BUSINESS_ERROR_00088
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) &&
            !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.debug("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����^�~�� = " + this.ascDesc);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
