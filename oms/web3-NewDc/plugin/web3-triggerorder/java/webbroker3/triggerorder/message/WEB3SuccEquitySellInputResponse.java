head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquitySellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����������t�������̓��X�|���X(WEB3SuccEquitySellInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3EquitySellInputResponse;


/**
 * (�i�A���j�����������t�������̓��X�|���X)<BR>
 * �i�A���j�����������t�������̓��X�|���X�B
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3SuccEquitySellInputResponse extends WEB3EquitySellInputResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equitySellInput";
    
    /**
     * @@roseuid 434896050128
     */
    public WEB3SuccEquitySellInputResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccEquitySellInputResponse(WEB3SuccEquitySellInputRequest l_request)
    {
        super(l_request);
    }     
}
@
