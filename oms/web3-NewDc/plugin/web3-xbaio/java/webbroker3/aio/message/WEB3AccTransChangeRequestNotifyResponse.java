head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֐����ʒm���X�|���X�N���X(WEB3AccTransChangeRequestNotifyResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ������ (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (�U�֐����ʒm���X�|���X)<BR>
 * (�U�֐����ʒm���X�|���X�N���X)
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestNotifyResponse extends WEB3BackResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_request_notify";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;

    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4158E8E40045
     */
    public WEB3AccTransChangeRequestNotifyResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AccTransChangeRequestNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    } 
}
@
