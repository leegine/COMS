head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.16.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	78c4d885ade604a;
filename	WEB3QtpRichPushMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : QTP���b�`�N���C�A���g�v�b�V�����C�����N�G�X�g(WEB3QtpRichPushMainRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 �� (FLJ)�V�K�쐬
 */

package webbroker3.rcp.message;

import webbroker3.common.*;
import webbroker3.common.message.*;
import webbroker3.util.*;

/**
 * �iQTP���b�`�N���C�A���g�v�b�V�����C�����N�G�X�g�j�B<br>
 * <br>
 * ���b�`�N���C�A���g�v�b�V�����C�����N�G�X�g
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3QtpRichPushMainRequest
    extends WEB3BackRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpRichPushMainRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "qtp_rich_push_main";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602060000L;

    /**
     * (From����ID)
     */
    public Long fromAccountId;

    /**
     * (To����ID)
     */
    public Long toAccountId;

    /**
     * �X���b�hNo<BR>
     */
    public Long threadNo;

    /**
     * �f�[�^�^�C�v�z��<BR>
     */
    public String type[];

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3QtpRichPushMainRequest()
    {
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3QtpRichPushMainResponse();
    }

    /**
     * �ivalidate�j<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@this.�X���b�h�m����null�܂��́A�e�����������h�c=null�܂��́ATo�����h�c=null�̏ꍇ�A<BR>
     * ��O��throw����B<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.fromAccountId == null ||
            this.toAccountId == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01291,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.threadNo == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
