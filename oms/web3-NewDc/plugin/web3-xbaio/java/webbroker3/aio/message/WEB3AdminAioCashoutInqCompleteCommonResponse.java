head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqCompleteCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇���������ʃ��X�|���X�N���X(WEB3AdminAioCashoutInqCompleteCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
                   2004/12/10 ���E (���u) �c�Ή�
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�o���\���⍇���������ʃ��X�|���X)<BR>
 * �o���\���⍇���������ʃ��X�|���X�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqCompleteCommonResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_complete_common";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131338L;
        
    /**
     * (���X�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ���X�R�[�h
     */
    public String[] branchCode;
    
    /**
     * (��n��)<BR>
     * ��ʂɂē��͂��ꂽ��n��
     */
    public Date deliveryDate;
    
    /**
     * (������t�敪)<BR>
     * ��ʂɂđI�����ꂽ������t�敪<BR>
     * <BR>
     * 0�F ��t����<BR>
     * 1�F ��t��<BR>
     * 2�F ��t�G���[<BR>
     * 3�F �S��<BR>
     */
    public String orderDiv;
    
    //===========remain zhou-yong NO.1 begin ========
    
    /**
     * (�U����敪)<BR>
     * ��ʂɂđI�����ꂽ�U����敪<BR>
     * <BR>
     * 0�F �h�S�āh<BR> 
     * 1�F �h�X���h<BR> 
     * 2�F �h���̑��h�i�X���ȊO�j 
     */
    public String transferDiv;
    
    //===========remain zhou-yong NO.1 end ========  
    
    /**
     * @@roseuid 4158EB6403D5
     */
    public WEB3AdminAioCashoutInqCompleteCommonResponse(WEB3AdminAioCashoutInqCommonRequest l_request) 
    {
        super(l_request);
    }
    
    
}
@
