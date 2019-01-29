head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������V�K���m�F���N�G�X�g(WEB3OptionsOpenMarginChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���I�v�V���������V�K���m�F���N�G�X�g)<BR>
 * �����w���I�v�V���������V�K���m�F���N�G�X�g�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginChangeConfirmRequest extends WEB3OptionsCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_openMarginChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141751L;

    /**
     * (�h�c)<BR>
     * �����h�c
     */
    public String id;

    /**
     * @@roseuid 40C0A8E80167
     */
    public WEB3OptionsOpenMarginChangeConfirmRequest()
    {
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��<BR>
     * <BR>
     * �Q�j�@@�h�c�`�F�b�N<BR>
     * �@@this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * �R�j�@@�������ʃ`�F�b�N<BR>
     * �@@�R�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00074<BR>
     * �@@�R�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00075<BR>
     * �@@�R�|�R�jthis.�������ʂ����O�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00076<BR>
     * �@@�R�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00077<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40694BF5002C
     */
    public void validate() throws WEB3BaseException
    {
        //�X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();

        //�h�c�`�F�b�N<BR>
        //this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
        //class: WEB3BusinessLayerException<BR>
        //tag:   BUSINESS_ERROR_00080<BR>
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + "validate",
                "�h�c��null�̒l�ł���B");
        }

        //�R�j�@@�������ʃ`�F�b�N<BR>
        //�@@�R�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00074<BR>
        if (WEB3StringTypeUtility.isEmpty(this.opOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074, 
                getClass().getName() + "validate",
                "�������ʂ���͂��Ă��������B");
        }

        //�@@�R�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00075<BR>
        if (!WEB3StringTypeUtility.isNumber(this.opOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075, 
                getClass().getName() + "validate",
                "�������ʂ������ȊO�̒l�ł���B");
        }

        //�@@�R�|�R�jthis.�������ʂ����O�̒l�ł���Η�O���X���[����B<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00076<BR>
        if (Long.parseLong(this.opOrderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076, 
                getClass().getName() + "validate",
                "�������ʂ�0�ȉ��̒l�ł���B");
        }

        //�@@�R�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00077<BR>
        if (WEB3StringTypeUtility.getByteLength(this.opOrderQuantity) > 5)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077, 
                getClass().getName() + "validate",
                "�������ʂ��T���𒴂��܂����B");
        }
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsOpenMarginChangeConfirmResponse(this);
    }

}
@
