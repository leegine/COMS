head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����o���ʒm���X�|���X(WEB3OptionOrderExecNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ���� (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�����w���I�v�V�����o���ʒm���X�|���X)<BR>
 * �����w���I�v�V�����o���ʒm���X�|���X�N���X<BR>                                                                    
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionOrderExecNotifyResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_orderExecNotify";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406111835L;
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionOrderExecNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
