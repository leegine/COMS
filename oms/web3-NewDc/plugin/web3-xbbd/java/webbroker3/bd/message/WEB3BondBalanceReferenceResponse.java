head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ�X�|���X(WEB3BondBalanceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���c���Ɖ�X�|���X)<BR>
 * ���c���Ɖ�X�|���X�N���X
 * 
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3BondBalanceReferenceResponse extends WEB3GenResponse
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "bond_balance_reference";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200609201900L;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    public WEB3BondCurrencyInfo[] currencyList;
    
    public WEB3BondBalanceReferenceDetailUnit[] balanceReference;
    
    /**
     * (���c���Ɖ�X�|���X)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3BondBalanceReferenceResponse()
    {
        
    }
    
    /**
     *�@@�R���X�g���N�^�B<BR>
     *�@@�w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@@@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3BondBalanceReferenceResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }

}
@
