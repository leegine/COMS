head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiStateResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ���X�|���X(WEB3AdminSrvRegiStateResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ���X�|���X)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ���X�|���X�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiStateResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_state";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (���I�L�T�[�r�X���׏��)
     */
    public WEB3AdminSrvRegiLotteryStateGroup[] lotDetails;
    
    /**
     * (���I���T�[�r�X���׏��)
     */
    public WEB3AdminSrvRegiNoLotteryStateGroup[] noLotDetails;
    
    /**
     * (�T�[�r�X���p�Ǘ��Ҍڋq�\���󋵈ꗗ���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE569A020A
     */
    public WEB3AdminSrvRegiStateResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminSrvRegiStateResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
