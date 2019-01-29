head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O�����\�[�g�L�[(WEB3AdminOffFloorSortKey.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;

/**
 * (�Ǘ��җ���O�����\�[�g�L�[)<BR>
 * <BR>
 * �Ǘ��җ���O�����\�[�g�L�[�B<BR>
 * <BR>
 * WEB3AdminOffFloorSortKey<BR>
 * <BR>
 *  @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorSortKey extends Message
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminOffFloorSortKey.class);

    /**
     * (�L�[����)<BR>
     * <BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{�������L�[���ڂƂ�
     * ��B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * keyItem<BR>
     * <BR>
     * Assume that the symbol names in the response class corresponding to the request
     * class using this class are keyItem<BR>
     * Refer to the description of the request using this class about the key items<BR>
     * <BR>
     */
    public String keyItem;

    /**
     * (�����^�~��)<BR>
     * <BR>
     * A�F�����@@D�F�~��<BR>
     * <BR>
     * A: Def.ASC D: Def.DESC<BR>
     * <BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 421AE2F10254
     */
    public WEB3AdminOffFloorSortKey()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@this.�L�[���ځ�null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�j�@@this.�����^�~����null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * �R�j�@@this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�hA�F�����h<BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00325<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check the correspondence in this class<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)If this.keyItem��null<BR>
     * �@@�@@Throw the exception "sortKeys.keyItem is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * 2)If this.ascDesc��null,<BR>
     * �@@�@@Throw the exception "sortKeys.ascDesc is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * 3)If this.ascDesc has an item other than the following items,<BR>
     * �@@�@@Throw the exception "sortKeys.ascDesc has an undefined value"<BR>
     * �@@�@@�@@�EA: Def.ASC<BR>
     * �@@�@@�@@�ED: Def.DESC<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * �V�X�e�����ʁiweb3-common�j.(web3)�V�X�e�������N���X_common.WEB3BaseException
     * @@roseuid 41BD04290105
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //1-1 if keyItem=null throw exception
        if (this.keyItem == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //   2-1 if ascDesc=null throw exception
        if (this.ascDesc == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            /*
             * 3-1 If this.ascDesc has an item other than the following items  Throw the exception
             * A: Def.ASC
             * D: Def.DESC
             */
            if ((!WEB3AscDescDef.ASC.equals(ascDesc)) && (!WEB3AscDescDef.DESC.equals(ascDesc)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
