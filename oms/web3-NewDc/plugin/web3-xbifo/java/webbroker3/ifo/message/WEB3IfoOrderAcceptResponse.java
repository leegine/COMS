head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP������t���X�|���X(WEB3IfoCloseOrderResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ������ �V�K�쐬
*/

package webbroker3.ifo.message;


import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;


/**
 * (�敨OP������t���X�|���X)<BR>
 * �敨OP������t���X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3IfoOrderAcceptResponse extends WEB3BackResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "ifo_orderAccept";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406112150L;
        
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3IfoOrderAcceptResponse(WEB3BackRequest l_request) 
    {
        super(l_request);
    }
}
@
