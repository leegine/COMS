head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X������񃌃X�|���X(WEB3AdminSrvRegiServiceHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X������񃌃X�|���X)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X������񃌃X�|���X�N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceHistoryResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceHistory";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;    

    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;
    
    /**
     * (�T�[�r�X����)
     */
    public String serviceName;
    
    /**
     * (�T�[�r�X�������ꗗ)
     */
    public WEB3AdminSrvRegiHistoryGroup[] histories;
    
    /**
     * (�T�[�r�X���p�Ǘ��҃T�[�r�X������񃌃X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE4F6601BC
     */
    public WEB3AdminSrvRegiServiceHistoryResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminSrvRegiServiceHistoryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }        
    
}
@
