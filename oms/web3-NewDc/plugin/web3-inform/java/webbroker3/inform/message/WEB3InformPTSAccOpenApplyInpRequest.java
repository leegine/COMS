head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyInpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS�����J�ݐ\�����̓��N�G�X�g(WEB3InformPTSAccOpenApplyInpRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 �Ӑ� (���u) �V�K�쐬 ���f��No.123
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.WEB3Inform;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS�����J�ݐ\�����̓��N�G�X�g)<BR>
 * PTS�����J�ݐ\�����̓��N�G�X�g
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyInpRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformPTSAccOpenApplyInpRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "inform_pts_acc_open_apply_inp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802181641L;

    /**
     * (�A�����)<BR>
     * �A�����
     */
    public String informType;

    /**
     * @@roseuid 47B9271A0222
     */
    public WEB3InformPTSAccOpenApplyInpRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3InformPTSAccOpenApplyInpResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�A����ʃ`�F�b�N<BR>
     * �@@this.�A����� == null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01817<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A2B612028E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�A����ʃ`�F�b�N
        // this.�A����� == null�̏ꍇ�A��O��throw����
        if (this.informType == null)
        {
            log.debug("�A����ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "�A����ʂ����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
