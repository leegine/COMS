head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇������������X�|���X�N���X(WEB3AdminAioCashoutInqCancelCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.message;


/**
 * (�o���\���⍇������������X�|���X)<BR>
 * �o���\���⍇������������X�|���X�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqCancelCompleteResponse extends WEB3AdminAioCashoutInqCompleteCommonResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_cancel_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131410L;        
                
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAioCashoutInqCancelCompleteResponse(WEB3AdminAioCashoutInqCancelCompleteRequest l_request)
    {
        super(l_request);
    }  
}
@
