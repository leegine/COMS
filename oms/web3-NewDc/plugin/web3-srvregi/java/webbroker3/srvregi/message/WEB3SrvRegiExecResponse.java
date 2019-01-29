head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiExecResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�T�[�r�X�N�����X�|���X(WEB3SrvRegiExecResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
Revesion History : 2008/02/29 ���V�� �d�l�ύX���f��No.329
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�T�[�r�X�N�����X�|���X)<BR>
 * �T�[�r�X���p�T�[�r�X�N�����X�|���X�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiExecResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_exec";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151415L;
    
    /**
     * (URL)
     */
    public String url;
    
    /**
     * (���M���@@�敪)<BR>
     * 0:GET <BR>
     * 1:POST <BR>
     * 2:HTTP-REQUEST<BR> 
     * 3:����i�P�j�|���e���N���A�،� MULTEX ��p<BR> 
     * 4:����i�Q�j�|���e���N���A�،� ���o�e���R��21 ��p<BR>
     */
    public String sendHowToDiv;
    
    /**
     * (���M�p�����[�^�ꗗ)<BR>
     * ���M�p�����[�^�ꗗ <BR>
     * �i�����F<�p�����[�^��>=<�l>�j<BR>
     */
    public String[] sendParamList;

    /**
     * (�G���[�敪)<BR>
     * �G���[�敪<BR>
     * 1�F���\���G���[�L<BR>
     * null�F�G���[��<BR>
     */
    public String srvRegiExecErrDiv;

    /**
     * (�T�[�r�X���p�T�[�r�X�N�����X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F77E29033E
     */
    public WEB3SrvRegiExecResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3SrvRegiExecResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
