head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�[�`������������UL�m�F���X�|���X(WEB3AdminAioVirtualAccCashinULConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 ������ (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o�[�`������������UL�m�F���X�|���X)<BR>
 * �o�[�`������������UL�m�F���X�|���X�N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0 
 */
 
public class WEB3AdminAioVirtualAccCashinULConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_aio_virtual_acc_cashin_ul_confirm";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200605091454L;

    /**
     * (�A�b�v���[�hID) <BR>
     * �A�b�v���[�hID<BR>
     */
    public String uploadID;
    
    /**
     * (�A�b�v���[�h����) <BR>
     * �A�b�v���[�h����<BR>
     */
    public String uploadNumber;
    
    /**
     * (�w�b�_�[���R�[�h����) <BR>
     * �w�b�_�[���R�[�h����<BR>
     */
    public String headerNumber;

    /**
     * (�f�[�^���R�[�h����) <BR>
     * �f�[�^���R�[�h����<BR>
     */
    public String dataNumber;
   
    /**
     * (�g���[���[���R�[�h����) <BR>
     * �g���[���[���R�[�h����<BR>
     */  
    public String trailerNumber;
    
    /**
     * (�G���h���R�[�h����) <BR>
     * �G���h���R�[�h����<BR>
     */
    public String endNumber;

    /**
     * (�ǂݔ�΂������R�[�h����) <BR>
     * �ǂݔ�΂������R�[�h����<BR>
     */
    public String skipOverNumber;
        
    /**
     * @@roseuid 4158EB64017C
     */
    public WEB3AdminAioVirtualAccCashinULConfirmResponse()
    {
    }
                
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAioVirtualAccCashinULConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
          
}
@
