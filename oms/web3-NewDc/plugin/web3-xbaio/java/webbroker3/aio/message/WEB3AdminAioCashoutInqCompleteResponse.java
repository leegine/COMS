head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇���o���������X�|���X�N���X(WEB3AdminAioCashoutInqCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/


package webbroker3.aio.message;


/**
 * (�o���\���⍇���o���������X�|���X)<BR>
 * (�o���\���⍇���o���������X�|���X�N���X)
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqCompleteResponse extends WEB3AdminAioCashoutInqCompleteCommonResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131419L;    

    /**
     * @@roseuid 4158EB650141
     */
    public WEB3AdminAioCashoutInqCompleteResponse(WEB3AdminAioCashoutInqCompleteRequest l_request) 
    {
        super(l_request);
    }
}
@
