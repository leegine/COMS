head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C������IP�ꗗ�\�[�g�L�[(WEB3AdminTraderAdminIPControlSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �����F (���u) �V�K�쐬 ���f��004
*/

package webbroker3.trademanagement.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.trademanagement.define.WEB3AdminTMKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE���O�C������IP�ꗗ�\�[�g�L�[)<BR>
 * �Ǘ��ҁE���O�C������IP�ꗗ�\�[�g�L�[�N���X�B<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlSortKey extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlSortKey.class);

    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
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
     * @@roseuid 48D75CD703E6
     */
    public WEB3AdminTraderAdminIPControlSortKey()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�L�[����<BR>
     * <BR>
     * �@@�P�|�P�j�@@this.�L�[���ڂ�null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�L�[���ڂɈȉ��̍��ڈȊO���ݒ肳��Ă���ꍇ�A��O��throw����B<BR>
     * �@@�@@�EIP�A�h���X<BR>
     * �@@�@@�E�K�p�J�n����<BR>
     * �@@�@@�E�X�e�[�^�X<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * �Q�j�@@�����^�~��<BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.�����^�~����null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�����^�~���Ɉȉ��̒l�ȊO���ݒ肳��Ă���ꍇ�A��O��throw����B <BR>
     * �@@�@@�EA�F����<BR>
     * �@@�@@�ED�F�~��<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00088<BR>
     * @@roseuid 48BE53390315
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�L�[����
        //�@@�P�|�P�j�@@this.�L�[���ڂ�null�̏ꍇ�A��O��throw����B
        if (this.keyItem == null)
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
        }

        //�@@�P�|�Q�j�@@this.�L�[���ڂɈȉ��̍��ڈȊO���ݒ肳��Ă���ꍇ�A��O��throw����B
        //�@@�@@�EIP�A�h���X
        //�@@�@@�E�K�p�J�n����
        //�@@�@@�E�X�e�[�^�X
        if (!WEB3AdminTMKeyItemDef.IP_ADDRESS.equals(this.keyItem)
            && !WEB3AdminTMKeyItemDef.START_DATE.equals(this.keyItem)
            && !WEB3AdminTMKeyItemDef.STATUS.equals(this.keyItem))
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�Q�j�@@�����^�~��
        //
        //�@@�Q�|�P�j�@@this.�����^�~����null�̏ꍇ�A��O��throw����B
        if (this.ascDesc == null)
        {
            log.debug("�����^�~�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�~�������w��ł��B");
        }

        //�@@�Q�|�Q�j�@@this.�����^�~���Ɉȉ��̒l�ȊO���ݒ肳��Ă���ꍇ�A��O��throw����B
        //�@@�@@�EA�F����
        //�@@�@@�ED�F�~��
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
