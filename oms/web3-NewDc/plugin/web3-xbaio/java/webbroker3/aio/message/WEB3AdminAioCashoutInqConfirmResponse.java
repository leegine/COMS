head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇���o���m�F���X�|���X�N���X(WEB3AdminAioCashoutInqConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.message;


/**
 * (�o���\���⍇���o���m�F���X�|���X)<BR>
 * �o���\���⍇���o���m�F���X�|���X�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqConfirmResponse extends WEB3AdminAioCashoutInqConfirmCommonResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L; 
        
    /**
     * @@roseuid 4158EB65032C
     */
    public WEB3AdminAioCashoutInqConfirmResponse(WEB3AdminAioCashoutInqConfirmRequest l_request) 
    {
        super(l_request);
    }
}
@
