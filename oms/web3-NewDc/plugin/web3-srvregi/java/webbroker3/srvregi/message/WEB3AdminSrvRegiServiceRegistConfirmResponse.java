head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�m�F���X�|���X(WEB3AdminSrvRegiServiceRegistConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;



/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�m�F���X�|���X)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�m�F���X�|���X�N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceRegistConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceRegistConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;
        
    /**
     * (���o�l�i�m�F���[���j)
     */
    public String confirmMailFrom;
    
    /**
     * (�����i�m�F���[���j)
     */
    public String confirmMailSubject;
    
    /**
     * (���[���w�b�_�[�i�m�F���[���j)
     */
    public String confirmMailHeader;
    
    /**
     * (���[���{���i�m�F���[���j)
     */
    public String confirmMailBody;
    
    /**
     * (���[���t�b�^�[�i�m�F���[���j)
     */
    public String confirmMailFooter;
    
    /**
     * (���o�l�i�_��������[���j)
     */
    public String noticeMailFrom;
    
    /**
     * (�����i�_��������[���j)
     */
    public String noticeMailSubject;
    
    /**
     * (���[���w�b�_�[�i�_��������[���j)
     */
    public String noticeMailHeader;
    
    /**
     * (���[���{���i�_��������[���j)
     */
    public String noticeMailBody;
    
    /**
     * (���[���t�b�^�[�i�_��������[���j)
     */
    public String noticeMailFooter;
    
    /**
     * (�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�m�F���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE324800F0
     */
    public WEB3AdminSrvRegiServiceRegistConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected  WEB3AdminSrvRegiServiceRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }        
    
}
@
