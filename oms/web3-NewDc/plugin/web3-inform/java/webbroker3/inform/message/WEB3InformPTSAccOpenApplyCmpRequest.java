head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyCmpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS�����J�ݐ\���������N�G�X�g(WEB3InformPTSAccOpenApplyCmpRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 �Ӑ� (���u) �V�K�쐬 ���f��No.123
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS�����J�ݐ\���������N�G�X�g)<BR>
 * PTS�����J�ݐ\���������N�G�X�g
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyCmpRequest extends WEB3InformPTSAccOpenApplyCommonRequest
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformPTSAccOpenApplyCmpRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "inform_pts_acc_open_apply_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802181637L;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password;

    /**
     * @@roseuid 47B9271A0109
     */
    public WEB3InformPTSAccOpenApplyCmpRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B1690803C3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //super.validate()���R�[������B
        super.validate();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3InformPTSAccOpenApplyCmpResponse(this);
    }
}
@
