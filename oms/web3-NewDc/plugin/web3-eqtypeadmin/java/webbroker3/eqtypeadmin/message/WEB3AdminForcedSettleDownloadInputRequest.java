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
filename	WEB3AdminForcedSettleDownloadInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ����_�E�����[�h���̓��N�G�X�g�iWEB3AdminForcedSettleDownloadInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 ���n(���u) �V�K�쐬���f��175
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�������ϒ����_�E�����[�h���̓��N�G�X�g)<BR>
 * �Ǘ��ҁE�������ϒ����_�E�����[�h���̓��N�G�X�g<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3AdminForcedSettleDownloadInputRequest extends WEB3AdminForcedSettleReferenceRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_download_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801211100L;

    /**
     * @@roseuid 479031F900EA
     */
    public WEB3AdminForcedSettleDownloadInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminForcedSettleDownloadInputResponse(this);
    }

}
@
