head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecEndNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�o���I���ʒm���X�|���X�N���X(WEB3IfoExecEndNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ������ �V�K�쐬
*/

package webbroker3.ifo.message;


import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;

/**
 * (�敨OP�o���I���ʒm���X�|���X)<BR>
 * �敨OP�o���I���ʒm���X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3IfoExecEndNotifyResponse extends WEB3BackResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "ifo_execEndNotify";
        
    /**
     * serialVersionUID<BR>
     */
     public final static long serialVersionUID = 200406112120L;
        
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3IfoExecEndNotifyResponse(WEB3BackRequest l_request) 
    {
        super(l_request);
    }
}
@
