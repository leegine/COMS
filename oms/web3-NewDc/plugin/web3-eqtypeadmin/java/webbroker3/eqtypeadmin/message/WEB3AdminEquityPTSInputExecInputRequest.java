head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSInputExecInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁE����(PTS)�o�����̓��N�G�X�g�iWEB3AdminEquityPTSInputExecInputRequest.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/23 ���� (���u) �V�K�쐬���f��173
 */
package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�����iPTS�j�o�����̓��N�G�X�g)<BR>
 * �Ǘ��ҁE�����iPTS�j�o�����̓��N�G�X�g�N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputExecInputRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputExecInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_input_exec_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801231022L;

    /**
     * (����ID)<BR>
     */
    public String orderId;

    /**
     * (�Ǘ��ҁE����(PTS)�o�����̓��N�G�X�g)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 4795A0F9018A
     */
    public WEB3AdminEquityPTSInputExecInputRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@����ID�`�F�b�N <BR>
     * �@@this.����ID��null�̏ꍇ�A <BR>
     * �@@�u����ID��null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00600<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4766442A032A
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@����ID�`�F�b�N
        // this.����ID��null�̏ꍇ�A
        //�u����ID��null�v�̗�O���X���[����B
        if (this.orderId == null)
        {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
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
        return new WEB3AdminEquityPTSInputExecInputResponse(this);
    }
}
@
