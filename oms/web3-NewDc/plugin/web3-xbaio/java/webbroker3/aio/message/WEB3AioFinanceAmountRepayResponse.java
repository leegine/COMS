head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFinanceAmountRepayResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Z���z�ԍσ��X�|���X(WEB3AioSecFinanceLoanRepayResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ������ (���u) �V�K�쐬                                     
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�Z���z�ԍσ��X�|���X)<BR>
 * �Z���z�ԍσ��X�|���X�N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AioFinanceAmountRepayResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_finance_amount_repay";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200609202050L;
    
    /**
     * (�Z���z�ԍσ��X�|���X)<BR>
     * @@roseuid 4510F52E0213
     */
    public WEB3AioFinanceAmountRepayResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AioFinanceAmountRepayResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    } 
}
@
