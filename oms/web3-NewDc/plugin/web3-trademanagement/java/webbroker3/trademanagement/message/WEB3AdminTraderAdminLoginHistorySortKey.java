head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminLoginHistorySortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C�������ꗗ�������ʃ\�[�g�L�[(WEB3AdminTraderAdminLoginHistorySortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �И��� (���u) �V�K�쐬 ���f��No.005
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.trademanagement.define.WEB3AdminTMKeyItemDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�Ǘ��ҁE���O�C�������ꗗ�������ʃ\�[�g�L�[)<BR>
 * �Ǘ��ҁE���O�C�������ꗗ�������ʃ\�[�g�L�[�N���X�B<BR>
 * <BR>
 * @@author �И���(���u)
 * @@version 1.0
 */
public class WEB3AdminTraderAdminLoginHistorySortKey extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminLoginHistorySortKey.class);

    /**
     * (�L�[����)<BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{�������L�[���ڂƂ���B <BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
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
     * @@roseuid 48D75CD601D3
     */
    public WEB3AdminTraderAdminLoginHistorySortKey()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�L�[����<BR>
     * �@@�P�|�P�j�@@this.�L�[���ځ�null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00085<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�L�[���ڂɈȉ��̍��ڈȊO���ݒ肳��Ă���ꍇ�A��O���X���[����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���O�C������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�EIP�A�h���X<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�ڋq�R�[�h<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00086<BR>
     * <BR>
     * �Q�j�����^�~��<BR>
     * <BR>
     * �@@�Q�|�P�j this.�����^�~����null�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00087<BR>
     * <BR>
     * �@@�Q�|�Q�j this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E�hA�F�����h <BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00088<BR>
     * @@roseuid 48BCBDA100B9
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�L�[����
        //�P�|�P�j�@@this.�L�[���ځ�null�̏ꍇ�A
        //�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
        if (this.keyItem == null)
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
        }

        //�P�|�Q�j�@@this.�L�[���ڂɈȉ��̍��ڈȊO���ݒ肳��Ă���ꍇ�A��O���X���[����
        //�E���O�C������
        //�EIP�A�h���X
        //�E�ڋq�R�[�h
        if (!WEB3AdminTMKeyItemDef.LOGIN_DATE.equals(this.keyItem)
            && !WEB3AdminTMKeyItemDef.IP_ADDRESS.equals(this.keyItem)
            && !WEB3AdminTMKeyItemDef.ACCOUNT_CODE.equals(this.keyItem))
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�Q�j�����^�~��
        //�Q�|�P�j this.�����^�~����null�̏ꍇ�A
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

        //�Q�|�Q�j this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A
        //�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
        //�E�hA�F�����h
        //�E�hD�F�~���h
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
