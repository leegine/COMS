head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSInputExecConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁE����(PTS)�o�����͊m�F���N�G�X�g�iWEB3AdminEquityPTSInputExecConfirmRequest.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/23 ���� (���u) �V�K�쐬���f��173
 */
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE����(PTS)�o�����͊m�F���N�G�X�g)<BR>
 * �Ǘ��ҁE����(PTS)�o�����͊m�F���N�G�X�g�N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputExecConfirmRequest extends WEB3AdminEquityPTSInputCancelExecCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_input_exec_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801231022L;

    /**
     * (�Ǘ��ҁE����(PTS)�o�����͊m�F���N�G�X�g)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 4795A0F90061
     */
    public WEB3AdminEquityPTSInputExecConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminEquityPTSInputExecConfirmResponse(this);
    }
}
@
