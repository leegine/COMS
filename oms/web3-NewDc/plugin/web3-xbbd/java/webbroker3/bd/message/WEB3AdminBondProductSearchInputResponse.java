head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ������ꗗ��ʕ\�����X�|���X(WEB3AdminBondProductSearchInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҍ������ꗗ��ʕ\�����X�|���X)<BR>
 * �Ǘ��ҍ������ꗗ��ʕ\�����X�|���X
 * 
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductSearchInputResponse extends WEB3GenResponse
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
     * (��ʃR�[�h�ꗗ)<BR>
     * ��ʃR�[�h�ꗗ�B
     */
    public String[] bondKindCodeList;
    
    /**
     * (�ʉ݃R�[�h�ꗗ)<BR>
     * �ʉ݃R�[�h�ꗗ
     */
    public String[] currencyCodeList;
    
    /**
     * �i�戵�敪�ꗗ�j<BR>
     * �戵�敪�ꗗ<BR>
     * <BR>
     * 0�F�s��  1�F�Ǘ��ҁ@@2�F�Ǘ���/�ڋq
     */
    public String[] tradeHandleDivList;
    
    /**
     * @@roseuid 44E3363D03B9
     */
    public WEB3AdminBondProductSearchInputResponse() 
    {
     
    }
    
    /**
     *�@@�R���X�g���N�^�B<BR>
     *�@@�w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@@@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondProductSearchInputResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
}
@
