head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�X�|���X(WEB3SrvRegiReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�X�|���X)<BR>
 * �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�X�|���X�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151411L;
    
    /**
     * (���I�����ꗗ)
     */
    public WEB3SrvRegiNoLotteryGroup[] noLotList;
    
    /**
     * (���I�L���ꗗ)
     */
    public WEB3SrvRegiLotteryGroup[] lotList;
    
    /**
     * (�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F1E4770278
     */
    public WEB3SrvRegiReferenceResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3SrvRegiReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
