head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioForeignCashTransAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�ݓ��o����t���X�|���X(WEB3AioForeignCashTransAcceptResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/03 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�O�ݓ��o����t���X�|���X)<BR>
 * (�O�ݓ��o����t���X�|���X�N���X)<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioForeignCashTransAcceptResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "aio_foreign_cash_trans_accept";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609031518L;      
     
    public WEB3AioForeignCashTransAcceptResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioForeignCashTransAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }  
}
@
