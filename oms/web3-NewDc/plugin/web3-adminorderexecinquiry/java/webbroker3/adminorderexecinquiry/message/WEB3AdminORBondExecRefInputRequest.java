head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������Ɖ�����\�����N�G�X�g(WEB3AdminBondExecRefInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q(���u) �V�K�쐬  
  
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���Ǘ��Ғ������Ɖ�����\�����N�G�X�g)<BR>
 * ���Ǘ��Ғ������Ɖ�����\�����N�G�X�g�N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_or_bond_exec_ref_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171156L; 
    
    /**
     * (���Ǘ��Ғ������Ɖ�����\�����N�G�X�g)<BR>
     * �R���X�g���N�^�B
     * @@roseuid 44B735090219
     */
    public WEB3AdminORBondExecRefInputRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * ���Ǘ��Ғ������Ɖ�����\�����X�|���X�𐶐����Ԃ�
     * @@return WEB3GenResponse
     * @@roseuid 44DA82400154
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminORBondExecRefInputResponse(this);
    }
  
}
@
