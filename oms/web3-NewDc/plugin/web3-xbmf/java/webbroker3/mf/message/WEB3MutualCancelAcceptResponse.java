head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������t���X�|���X�N���X(WEB3MutualCancelAcceptResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/25 ���� (���u) ���r���[
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * �����M�������t���X�|���X�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualCancelAcceptResponse extends WEB3BackResponse 
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
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualCancelAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }  
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A9A18702E9
     */
    public WEB3MutualCancelAcceptResponse() 
    {
     
    }
}
@
