head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.44.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӏ��ʒm���N�G�X�g(WEB3AdminEquityAttentionInfoNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 �И��� (���u) �V�K�쐬 ���f��No.218
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (���ӏ��ʒm���N�G�X�g)<BR>
 * ���ӏ��ʒm���N�G�X�g<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoNotifyRequest extends WEB3BackRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_eq_attention_info_notify";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812301414L;

    /**
     * @@roseuid 49588AF00138
     */
    public WEB3AdminEquityAttentionInfoNotifyRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AdminEquityAttentionInfoNotifyResponse(this);
    }
}
@
