head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M��������t���N�G�X�g�N���X(WEB3MutualOrderAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ���� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �����M��������t���N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualOrderAcceptRequest extends WEB3BackRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_order_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408121100L;
    
    /**
     * (���M������t���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A9A12D01C1
     */
    public WEB3MutualOrderAcceptRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR> 
     * <BR>
     * ���M������t���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B
     * @@return WEB3BackResponse
     * @@roseuid 40A4324A0260
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3MutualOrderAcceptResponse(this);
    }
}
@
