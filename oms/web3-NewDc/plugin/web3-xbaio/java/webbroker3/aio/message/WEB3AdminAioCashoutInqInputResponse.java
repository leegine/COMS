head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇�����̓��X�|���X�N���X(WEB3AdminAioCashoutInqInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
                   2006/09/04 �Ԑi (���u) ����̍X ���f��No.629
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o���\���⍇�����̓��X�|���X)<BR>
 * �o���\���⍇�����̓��X�|���X�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;     
        
    /**
     * (���X�R�[�h)<BR>
     * �f�t�H���g�\���ƂȂ镔�X�R�[�h
     */
    public String branchCode;
    
    /**
     * (�O�݃R�[�h�ꗗ)<BR>
     * �O�݃R�[�h�ꗗ
     */
    public String[] foreignCurrencyCodeList;
      
    /**
     * @@roseuid 4158EB66008E
     */
    public WEB3AdminAioCashoutInqInputResponse(WEB3AdminAioCashoutInqInputRequest l_request) 
    {
        super(l_request);
    }
}
@
