head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.46.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginSwapConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����������n�����m�F���X�|���X�N���X(WEB3SuccMarginSwapConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A����(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginSwapMarginConfirmResponse;

/**
 * (�i�A���j�M�p����������n�����m�F���X�|���X�N���X)<BR>
 * �i�A���j�M�p����������n�����m�F���X�|���X�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0 
 */
public class WEB3SuccMarginSwapConfirmResponse extends WEB3MarginSwapMarginConfirmResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginSwapConfirm";
    
    /**
     * @@roseuid 4369CC7D03A9
     */
    public WEB3SuccMarginSwapConfirmResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccMarginSwapConfirmResponse(WEB3SuccMarginSwapConfirmRequest l_request)
    {
        super(l_request);
    }     
}
@
