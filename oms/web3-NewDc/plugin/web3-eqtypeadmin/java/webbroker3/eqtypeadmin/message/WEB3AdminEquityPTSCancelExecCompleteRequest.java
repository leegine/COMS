head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSCancelExecCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����iPTS�j�o������������N�G�X�g(WEB3AdminEquityPTSCancelExecCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 ���n(���u) �V�K�쐬���f��174
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�����iPTS�j�o������������N�G�X�g)<BR>
 * �Ǘ��ҁE�����iPTS�j�o������������N�G�X�g�N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3AdminEquityPTSCancelExecCompleteRequest extends WEB3AdminEquityPTSInputCancelExecCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_cancel_exec_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801231100L;

    /**
     * (�Ïؔԍ�)<BR>
     */
    public String password;

    /**
     * @@roseuid 4795B0860109
     */
    public WEB3AdminEquityPTSCancelExecCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminEquityPTSCancelExecCompleteResponse(this);
    }

}
@