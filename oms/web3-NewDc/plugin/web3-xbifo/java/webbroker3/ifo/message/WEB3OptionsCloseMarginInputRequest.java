head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍϓ��͉�ʃ��N�G�X�g�N���X(WEB3OptionsCloseMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ������ �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.define.WEB3IfoKeyItemDef;

/**
 * (�����w���I�v�V�����ԍϓ��͉�ʃ��N�G�X�g)<BR>
 * �����w���I�v�V�����ԍϓ��͉�ʃ��N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginInputRequest extends WEB3GenRequest
{

     /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginInput";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406112110L;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionsCloseMarginInputRequest.class);

    /**
    * ����ID
    */
    public String[] id;

    /**
    * (�\�[�g�L�[)<BR>
    * �Ώۍ��ځF���N�����A���P��<BR>
    * <BR>
    * ���ʏڍו\���p�\�[�g�L�[<BR>
    * �f�t�H���g�͌��N�����̏���<BR>
    */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
    * ��������
    */
    public String opOrderQuantity;

    /**
    * @@roseuid 40C0A8F0036B
    */
    public WEB3OptionsCloseMarginInputRequest()
    {

    }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
    * <BR>
    * �P�j�@@�h�c�`�F�b�N<BR>
    * �@@this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00080<BR>
    * <BR>
    * �Q�j�@@�\�[�g�L�[�`�F�b�N<BR>
    * �@@�Q�|�P�jthis.�\�[�g�L�[��null�̒l�ł���Η�O���X���[����B<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00231<BR>
    * �@@�Q�|�Q�jthis.�\�[�g�L�[�̗v�f�����O�ł���Η�O���X���[����B<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00232<BR>
    * �@@�Q�|�R�jthis.�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR>
    * �@@�@@�Q�|�R�|�P�j�\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00085<BR>
    * �@@�Q�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��<BR>
    * �@@�@@�@@�@@�@@�@@�@@���݂������O���X���[����B<BR>
    * �@@�@@�@@�E����<BR>
    * �@@�@@�@@�E���P��<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00277<BR>
    * �@@�@@�Q�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00087<BR>
    * �@@�@@�Q�|�R�|�S�j�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
    * �@@�@@�@@�E�hA�F�����h<BR>
    * �@@�@@�@@�E�hD�F�~���h<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00088<BR>
    * @@throws WEB3BaseException
    * @@roseuid 406A7B7B02BE
    */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B
        if (this.id == null)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "validate",
                "�h�c��null�̒l�ł���B");
        }

        //�Q�j�@@�\�[�g�L�[�`�F�b�N
        if (futOpSortKeys == null)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "validate",
                "�\�[�g�L�[��null�̒l�ł���B");
        }

        //�Q�|�Q�jthis.�\�[�g�L�[�̗v�f�����O�ł���Η�O���X���[����B
        if (futOpSortKeys.length == 0)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "validate",
                "�\�[�g�L�[�̗v�f�����O�ł���B");
        }

        //�Q�|�R�jthis.�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B
        int l_intObjectItemLength = futOpSortKeys.length;
        for (int i = 0; i<l_intObjectItemLength; i++)
        {
            //�Q�|�R�|�P�j�\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].keyItem))
            {
                //��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "validate",
                    "�L�[���ڂ�null�̒l�ł���B");
            }

            //�Q�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l�����݂������O���X���[����B
            if (!WEB3IfoKeyItemDef.OPEN_DATE.equals(futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.CONTRACT_PRICE.equals(futOpSortKeys[i].keyItem))
            {
                //��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "validate",
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }

            //�Q�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].ascDesc))
            {
                //��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "validate",
                    "�����^�~�������w��ł��B");
            }
            //�Q�|�R�|�S�j�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
            if (!WEB3AscDescDef.ASC.equals(futOpSortKeys[i].ascDesc)
                && !WEB3AscDescDef.DESC.equals(futOpSortKeys[i].ascDesc))
            {
                //��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "validate",
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            }

            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    *<BR>
    * @@return ���X�|���X�I�u�W�F�N�g
    */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsCloseMarginInputResponse(this);
    }
}
@
