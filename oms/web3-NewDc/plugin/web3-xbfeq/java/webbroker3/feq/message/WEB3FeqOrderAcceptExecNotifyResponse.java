head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptExecNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������t�o���ʒm���X�|���X(WEB3FeqOrderAcceptExecNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�O������������t�o���ʒm���X�|���X)<BR>
 * �O������������t�o���ʒm���X�|���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3FeqOrderAcceptExecNotifyResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "feq_order_accept_exec_notify";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD005D
     */
    public WEB3FeqOrderAcceptExecNotifyResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FeqOrderAcceptExecNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }  
}
@
