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
filename	WEB3AdminEquityPTSInputExecCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁE�����iPTS�j�o�����͊������N�G�X�g�iWEB3AdminEquityPTSInputExecCompleteRequest.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/22 ���� (���u) �V�K�쐬���f��173
 */
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE����(PTS)�o�����͊������N�G�X�g)<BR>
 * �Ǘ��ҁE����(PTS)�o�����͊������N�G�X�g�N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputExecCompleteRequest extends WEB3AdminEquityPTSInputCancelExecCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_input_exec_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801221708L;

    /**
     * (�Ïؔԍ�)<BR>
     */
    public String password;

    /**
     * (�Ǘ��ҁE�����iPTS�j�o�����͊������N�G�X�g)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 4795A0F80311
     */
    public WEB3AdminEquityPTSInputExecCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminEquityPTSInputExecCompleteResponse(this);
    }
}
@
