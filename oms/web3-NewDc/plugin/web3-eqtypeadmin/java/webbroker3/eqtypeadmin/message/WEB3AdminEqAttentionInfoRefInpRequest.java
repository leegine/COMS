head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEqAttentionInfoRefInpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���ӏ��Ɖ���̓��N�G�X�g(WEB3AdminEqAttentionInfoRefInpRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 �И��� (���u) �V�K�쐬 ���f��No.217
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE���ӏ��Ɖ���̓��N�G�X�g)<BR>
 * �Ǘ��ҁE���ӏ��Ɖ���̓��N�G�X�g�N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminEqAttentionInfoRefInpRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_eq_attention_info_ref_inp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812301402L;

    /**
     * @@roseuid 49588AF0029F
     */
    public WEB3AdminEqAttentionInfoRefInpRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminEqAttentionInfoRefInpResponse(this);
    }
}
@
