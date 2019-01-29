head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginContractListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍό��ʈꗗ���N�G�X�g�N���X(WEB3OptionsCloseMarginContractListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ������ �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���I�v�V�����ԍό��ʈꗗ���N�G�X�g)<BR>
 * �����w���I�v�V�����ԍό��ʈꗗ���N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginContractListRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginContracList";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406110900L;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3OptionsCloseMarginContractListRequest.class);

     /**
     * (�h�c)<BR>
     * �����h�c
     */
    public String id;

    /**
    * @@roseuid 40C0A8E7006D
    */
    public WEB3OptionsCloseMarginContractListRequest()
    {

    }

    /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
    * <BR>
    * �P�j�@@�h�c�`�F�b�N<BR>
    * �@@this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
    *   class: WEB3BusinessLayerException<BR>
    *   tag:   BUSINESS_ERROR_00080<BR>
    * @@throws WEB3BaseException
    * @@roseuid 406A7B540138
    */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�\�[�g�L�[�̃`�F�b�N
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "validate",
                "�h�c��null�̒l�ł���B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    *<BR>
    * @@return ���X�|���X�I�u�W�F�N�g
    */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsCloseMarginContractListResponse(this);
    }
}
@
