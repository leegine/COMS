head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[���s�\�[�g�L�[(WEB3AdminDirSecTriggerIssueSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 ���O(���u) �V�K�쐬 ���f��No.116
*/
package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�g���K�[���s�\�[�g�L�[)<BR>
 * �g���K�[���sJOB�ꗗ��ʂŕ\������郌�R�[�h��<BR>
 * �C�ӂ̏����ŕ��ёւ��邽�߂̒l��ێ�����N���X�B<BR>
 * <BR>
 * @@author ���O
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueSortKey extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueSortKey.class);

    /**
     * (�L�[����)<BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{�������L�[���ڂƂ�<BR>
     * ��B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
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
     * @@roseuid 4806E05303AB
     */
    public WEB3AdminDirSecTriggerIssueSortKey()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�jthis.�L�[���ځ�null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00085<BR>
     * �Q�jthis.�����^�~����null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00087<BR>
     * �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�hA�F�����h<BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47F9ABAB0283
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //this.�L�[���ځ�null�̏ꍇ�A
        //�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
        if (this.keyItem == null)
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
        }

        //this.�����^�~����null�̏ꍇ�A
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

        //this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A
        //�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) &&
            !WEB3AscDescDef.DESC.equals(this.ascDesc))
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
