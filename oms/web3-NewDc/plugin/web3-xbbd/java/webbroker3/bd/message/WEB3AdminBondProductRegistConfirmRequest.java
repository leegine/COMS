head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ������o�^�m�F���N�G�X�g(WEB3AdminBondProductRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҍ������o�^�m�F���N�G�X�g)<BR>
 * �Ǘ��ҍ������o�^�m�F���N�G�X�g�N���X
 * 
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductRegistConfirmRequest extends WEB3AdminBondProductRegistCommonRequest
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_regist_confirm";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * @@roseuid 44E3363C035B
     */
    public WEB3AdminBondProductRegistConfirmRequest() 
    {
     
    }

    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * �������o�^�m�F���X�|���X�𐶐����Ԃ�
     * @@return WEB3GenResponse
     * @@roseuid 44B61F0E00B9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminBondProductRegistConfirmResponse(this);
    }
}
@
