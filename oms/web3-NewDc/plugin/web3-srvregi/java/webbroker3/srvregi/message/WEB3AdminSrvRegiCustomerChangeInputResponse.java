head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓��X�|���X(WEB3AdminSrvRegiCustomerChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���w�� �V�K�쐬
*/
package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓��X�|���X)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓��X�|���X�N���X<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerChangeInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerChangeInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151418L;
    
    /**
     * (�T�[�r�X����)
     */
    public String serviceName;
    
    /**
     * (�ύX�ڋq�ꗗ)
     */
    public webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeGroup[ ] chgCustomerList;
    
    /**
     * (�T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F4EC390354
     */
    public WEB3AdminSrvRegiCustomerChangeInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminSrvRegiCustomerChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
