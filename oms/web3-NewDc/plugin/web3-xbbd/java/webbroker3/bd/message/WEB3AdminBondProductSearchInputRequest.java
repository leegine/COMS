head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ������ꗗ��ʕ\�����N�G�X�g(WEB3AdminBondProductSearchInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҍ������ꗗ��ʕ\�����N�G�X�g)<BR>
 * �Ǘ��ҍ������ꗗ��ʕ\�����N�G�X�g�N���X
 * 
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductSearchInputRequest extends WEB3GenRequest
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_search_input";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * @@roseuid 44E3363D02EE
     */
    public WEB3AdminBondProductSearchInputRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     * @@roseuid 44BC636000EF
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminBondProductSearchInputResponse(this);
    }
}
@
