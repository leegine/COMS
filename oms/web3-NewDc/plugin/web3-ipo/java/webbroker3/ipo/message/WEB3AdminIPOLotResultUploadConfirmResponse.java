head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʱ���۰�ފm�Fڽ��ݽ�N���X(WEB3AdminIPOLotResultUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO���I���ʱ���۰�ފm�Fڽ��ݽ�N���X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultUploadConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotResultUploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131117L;
    
    /**
     * �A�b�v���[�h����
     */
    public String uploadNumber;
    
    /**
     * �A�b�v���[�h�h�c
     */
    public String uploadID;
    
    /**
     * @@roseuid 4112DF8A002E
     */
    public WEB3AdminIPOLotResultUploadConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E11AB4031E
     */
    public WEB3AdminIPOLotResultUploadConfirmResponse(WEB3GenRequest l_request) 
    {
    
        super(l_request);
    
    }
}
@
