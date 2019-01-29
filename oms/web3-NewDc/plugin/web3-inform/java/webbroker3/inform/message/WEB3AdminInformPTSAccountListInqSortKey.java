head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListInqSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁEPTS�\���q�ꗗ�⍇���\�[�g�L�[�N���X(WEB3AdminInformPTSAccountListInqSortKey.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 �Ӑ�(���u) �V�K�쐬 ���f��No.128
*/

package webbroker3.inform.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.inform.define.WEB3InformKeyItemDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҁEPTS�\���q�ꗗ�⍇���\�[�g�L�[�N���X)<BR>
 * �Ǘ��ҁEPTS�\���q�ꗗ�⍇���\�[�g�L�[�N���X<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListInqSortKey extends Message
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListInqSortKey.class);

    /**
     * (�L�[����)<BR>
     * �L�[����
     */
    public String keyItem;

    /**
     * (�����^�~��)<BR>
     * �����^�~��
     * <BR>
     * A�F ����<BR>
     * D�F �~��<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 47C522D4028D
     */
    public WEB3AdminInformPTSAccountListInqSortKey()
    {

    }

    /**
     * �������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�L�[����<BR>
     * <BR>
     * �@@�P�|�P�j�@@�L�[���ڂ�null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00085<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�L�[���ڂɈȉ��̍��ڈȊO���ݒ肳��Ă���ꍇ�A��O��throw����B<BR>
     * �@@�@@�E���X�R�[�h<BR>
     * �@@�@@�E�ڋq�R�[�h<BR>
     * �@@�@@�E�\������<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00086<BR>
     * <BR>
     * �Q�j�@@�����^�~��<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�����^�~����null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00087<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����^�~���Ɉȉ��̒l�ȊO���ݒ肳��Ă���ꍇ�A��O��throw����B<BR>
     * �@@�@@�EA�F����<BR>
     * �@@�@@�ED�F�~��<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B541FC037C
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�L�[����
        //�P�|�P�j�@@�L�[���ڂ�null�̏ꍇ�A��O��throw����B
        if (this.keyItem == null)
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
        }

        //�P�|�Q�j�@@�L�[���ڂɈȉ��̍��ڈȊO���ݒ肳��Ă���ꍇ�A��O��throw����B
        //�@@�@@�E���X�R�[�h
        //�@@�@@�E�ڋq�R�[�h
        //�@@�@@�E�\������
        if (!WEB3InformKeyItemDef.BRANCH_CODE.equals(this.keyItem)
            && !WEB3InformKeyItemDef.ACCOUNT_CODE.equals(this.keyItem)
            && !WEB3InformKeyItemDef.APPLY_DATE.equals(this.keyItem))
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�Q�j�@@�����^�~��
        //�Q�|�P�j�@@�����^�~����null�̏ꍇ�A��O��throw����B
        if (this.ascDesc == null)
        {
            log.debug("�����^�~�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�~�������w��ł��B");
        }

        //�Q�|�Q�j�@@�����^�~���Ɉȉ��̒l�ȊO���ݒ肳��Ă���ꍇ�A��O��throw����B
        //�@@�EA�F����
        //�@@�ED�F�~��
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
