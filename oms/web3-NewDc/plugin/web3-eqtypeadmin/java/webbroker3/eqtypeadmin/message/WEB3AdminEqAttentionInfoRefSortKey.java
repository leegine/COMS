head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEqAttentionInfoRefSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӏ��Ɖ�\�[�g�L�[(WEB3AdminEqAttentionInfoRefSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 �И��� (���u) �V�K�쐬 ���f��No.217
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
 * (���ӏ��Ɖ�\�[�g�L�[)<BR>
 * ���ӏ��Ɖ�\�[�g�L�[�N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminEqAttentionInfoRefSortKey extends Message
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEqAttentionInfoRefSortKey.class);

    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     * �E"���ӏ����"<BR>
     * �E"���ӏ��敪�R�[�h"<BR>
     * �E"��񔭐�����"<BR>
     * �E"�����R�[�h"<BR>
     * �E"�s��R�[�h"<BR>
     * �����\�[�g�L�[�́u��񔭐������v�B<BR>
     * <BR>
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
     * @@roseuid 49588AEF02BF
     */
    public WEB3AdminEqAttentionInfoRefSortKey()
    {

    }

    /**
     * �P�jthis.�L�[���� == null�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�j this.�L�[���ڂɉ��L�̍��ڈȊO��<BR>
     * �@@�ݒ肳��Ă�����A�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@�E���ӏ����<BR>
     * �@@�@@�@@�@@�@@�@@�E���ӏ��敪�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�E��񔭐�����<BR>
     * �@@�@@�@@�@@�@@�@@�E�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�E�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00086<BR>
     * <BR>
     * �R�jthis.�����^�~����null�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00087<BR>
     * <BR>
     * �S�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E�hA�F�����h <BR>
     * �@@�@@�@@�E�hD�F�~���h <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@roseuid 4941FFC80263
     * @@throws WEB3BaseException
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
        if (this.keyItem != null)
        {
            if (!WEB3AdminEquitySortKeyItemNameDef.ATTENTION_INFO_TYPE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.ATTENTION_INFO_DIV_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.INFO_OCCURED_DATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.PRODUCT_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.MARKET_CODE.equals(this.keyItem))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
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
        if (this.ascDesc != null)
        {
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
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
