head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.30.05.57.28;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d92c6286688;
filename	WEB3FPTDocumentGetRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (��)��a�����r�W�l�X�E�C�m�x�[�V����
 File Name           : �����@@���ʏ��擾���N�G�X�g�N���X(WEB3FPTDocumentGetRequest.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/16 �����C(�k�����u) �V�K�쐬 �d�l�ύX���f��No.354
 */
package webbroker3.gentrade.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����@@���ʏ��擾���N�G�X�g)<BR>
 * �����@@���ʏ��擾���N�G�X�g�N���X<BR>
 * <BR>
 * @@author �����C(�k�����u)
 * @@version 1.0
 */
public class WEB3FPTDocumentGetRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FPTDocumentGetRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "fpt_document_get";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 201011161749L;

    /**
     * (��t�Ώ�)<BR>
     * ��t�Ώ�<BR>
     * <BR>
     * 0�F�S�ڋq<BR>
     * 1�F�M�p�J�ݍόڋq<BR>
     * 2�F�敨�E�I�v�V�����J�ݍόڋq<BR>
     */
    public String deliveryTarget;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FPTDocumentGetRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �ȉ��̍��ڂ̂����ꂩ��null�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * ��t�Ώ�<BR>
     * <BR>
     * ��t�Ώۂ���null�̏ꍇ<BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@BUSINESS_ERROR_03222<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);

        if (this.deliveryTarget == null)
        {
            log.debug("��t�Ώۂ������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03223,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��t�Ώۂ������͂ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FPTDocumentGetResponse(this);
    }
}
@
