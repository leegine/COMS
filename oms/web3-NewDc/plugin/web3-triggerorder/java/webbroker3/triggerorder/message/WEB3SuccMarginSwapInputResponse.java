head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.41.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginSwapInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����������n�������̓��X�|���X�N���X(WEB3SuccMarginSwapInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A����(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginSwapMarginInputResponse;

/**
 * (�i�A���j�M�p����������n�������̓��X�|���X�N���X)<BR>
 * �i�A���j�M�p����������n�������̓��X�|���X�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3SuccMarginSwapInputResponse extends WEB3MarginSwapMarginInputResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginSwapInput";
    
    /**
     * (��������)<BR>
     * ��������<BR>
     * �i���Ύ���̏ꍇ�̂݃Z�b�g�j<BR>
     */
    public String orderQuantity;
    
    /**
     * @@roseuid 4369CBEB02AF
     */
    public WEB3SuccMarginSwapInputResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccMarginSwapInputResponse(WEB3SuccMarginSwapInputRequest l_request)
    {
        super(l_request);
    }     
}
@
