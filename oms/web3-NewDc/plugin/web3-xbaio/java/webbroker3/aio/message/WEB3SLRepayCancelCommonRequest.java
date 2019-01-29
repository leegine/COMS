head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayCancelCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍώ�����ʃ��N�G�X�g(WEB3SLRepayCancelCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.758
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�،��S�ۃ��[���ԍώ�����ʃ��N�G�X�g)<BR>
 * �S�ۃ��[���ԍώ�����ʃ��N�G�X�g�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLRepayCancelCommonRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SLRepayCancelCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_cancel_common";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121520L;

    /**
     * (����ID)<BR>
     * ����ΏۂƂȂ�S�ۃ��[���ԍϒ����̒���ID<BR>
     */
    public String orderId;

    /**
     * @@roseuid 46E89086021F
     */
    public WEB3SLRepayCancelCommonRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j����ID�`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.����ID = null<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00600<BR>
     * <BR>
     * @@roseuid 46CA503B012B
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // ����ID�`�F�b�N
        // ���N�G�X�g�f�[�^.����ID = null
        if (this.orderId == null)
        {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g<BR>
     * @@roseuid 46BC080901A7
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SLRepayCancelCommonResponse(this);
    }
}
@
