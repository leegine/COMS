head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsProductSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����V�K�����������I����ʃ��N�G�X�g(WEB3OptionsProductSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���o�� (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����w���I�v�V�����V�K�����������I����ʃ��N�G�X�g)<BR>
 * �����w���I�v�V�����V�K�����������I����ʃ��N�G�X�g�N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionsProductSelectRequest extends WEB3GenRequest
{

    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsProductSelectRequest.class);
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406141514L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_productSelect";

    /**
     * ���敪<BR>
     * 1�F�����@@2�F����<BR>
     */
    public String contractType;

    /**
     * @@roseuid 40C0A8EC007D
     */
    public WEB3OptionsProductSelectRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���敪�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00263<BR>
     * �@@�P�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�����h<BR>
     * �@@�@@�@@�@@�E�h2�F�����h<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00264<BR>
     * @@throws WEB3BaseException
     * @@roseuid 407DED33021B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".validate()";
                
        log.entering(STR_METHOD_NAME);
        
        //���敪�`�F�b�N
        //this.���敪��null�̒l�ł���Η�O���X���[����B
        log.debug("���敪�`�F�b�N");
        log.debug("this.contractType = " + this.contractType);
        if (this.contractType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263,
                getClass().getName() + "validate",
                "���敪��null�ł���");
        }

        //���敪�`�F�b�N
        //this.���敪���h1�F�����h�h2�F����"�ȊO�̏ꍇ��O���X���[����B
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType)
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264,
                getClass().getName() + "validate",
                "���敪���h1�F�����h�A�h2�F�����h�ȊO�ł���");
        }
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsProductSelectResponse(this);
    }
}
@
