head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�m�F���X�|���X(WEB3AdminSrvRegiUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�m�F���X�|���X)<BR>
 * �T�[�r�X���p�Ǘ��҃A�b�v���[�h�m�F���X�|���X�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiUploadConfirmResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_uploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (�A�b�v���[�h����)
     */
    public String lineCount;
    
    /**
     * (�A�b�v���[�hID)
     */
    public String uploadId;
    
    /**
     * (�����\���敪)
     * null:�\���ΏۊO
     * 0:��
     * 1:�L
     */
    public String firstApplyDiv;
    
    /**
     * (�T�[�r�X���p�Ǘ��҃A�b�v���[�h�m�F���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 41009077002E
     */
    public WEB3AdminSrvRegiUploadConfirmResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminSrvRegiUploadConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
