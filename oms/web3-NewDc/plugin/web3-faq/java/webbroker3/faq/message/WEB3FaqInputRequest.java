head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �⍇���Ǘ����⍇�����̓��N�G�X�g(WEB3FaqInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�⍇���Ǘ����⍇�����̓��N�G�X�g)<BR>
 * �⍇���Ǘ����⍇�����̓��N�G�X�g<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3FaqInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "faq_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171301L;

    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * @@roseuid 41C25C060000
     */
    public WEB3FaqInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FaqInputResponse(this);
    }

}
@
