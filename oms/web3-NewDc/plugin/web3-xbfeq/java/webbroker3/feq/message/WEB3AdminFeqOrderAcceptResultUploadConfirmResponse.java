head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptResultUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������������t���ʃA�b�v���[�h�m�F���X�|���X(WEB3AdminFeqOrderAcceptResultUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO������������t���ʃA�b�v���[�h�m�F���X�|���X)<BR>
 * �Ǘ��ҊO������������t���ʃA�b�v���[�h�m�F���X�|���X�N���X
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptResultUploadConfirmResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAcceptResultUploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�A�b�v���[�h����)<BR>
     * �A�b�v���[�h����
     */
    public String uploadNumber;
    
    /**
     * (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID
     */
    public String uploadId;
    
    /**
     * @@roseuid 42CE39FC03A9
     */
    public WEB3AdminFeqOrderAcceptResultUploadConfirmResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqOrderAcceptResultUploadConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
