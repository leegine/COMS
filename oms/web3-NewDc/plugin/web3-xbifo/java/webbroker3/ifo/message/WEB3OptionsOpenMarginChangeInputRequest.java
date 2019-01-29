head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������V�K�����͉�ʃ��N�G�X�g(WEB3OptionsOpenMarginChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���I�v�V���������V�K�����͉�ʃ��N�G�X�g)<BR>
 * �����w���I�v�V���������V�K�����͉�ʃ��N�G�X�g�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginChangeInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_openMarginChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141720L;

    /**
     * (�h�c)<BR>
     * �����h�c
     */
    public String id;

    /**
     * @@roseuid 40C0A8E802DE
     */
    public WEB3OptionsOpenMarginChangeInputRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�h�c�`�F�b�N<BR>
     * �@@this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40694B4D025D
     */
    public void validate() throws WEB3BaseException
    {
        //�h�c�`�F�b�N
        //this.�h�c��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + "validate",
                "�h�c��null�̒l�ł���B");
        }
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsOpenMarginChangeInputResponse(this);
    }
}
@
