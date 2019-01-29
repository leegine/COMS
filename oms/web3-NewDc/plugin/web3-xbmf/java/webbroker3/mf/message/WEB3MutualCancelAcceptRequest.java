head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������t���N�G�X�g�N���X(WEB3MutualCancelAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/25 ���� (���u) ���r���[
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * �����M�������t���N�G�X�g�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualCancelAcceptRequest extends WEB3BackRequest 
{
 
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_cancel_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L; 
   
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A9A10B02F9
     */
    public WEB3MutualCancelAcceptRequest() 
    {
    }
    
    /**
     * �icreateResponse�̎����j <BR>
     * <BR>
     * ���M�����t���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * @@return WEB3BackResponse
     * @@roseuid 40A431EF004D
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3MutualCancelAcceptResponse(this);
    }
}
@
