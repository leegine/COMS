head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁEFX�����J�݃A�b�v���[�h���̓��X�|���X(WEB3AdminFXAccOpenUploadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 �A����(���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁEFX�����J�݃A�b�v���[�h���̓��X�|���X)<BR>
 * �Ǘ��ҁEFX�����J�݃A�b�v���[�h���̓��X�|���X�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadInputResponse extends WEB3GenResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602181050L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fx_acc_open_upload_input";
    
    /**
     * (�A�b�v���[�h�����ꗗ)<BR>
     */
    public WEB3FXUploadHistoryUnit uploadHistoryUnit;
    
    /**
     * @@roseuid 43F49A70001F
     */
    public WEB3AdminFXAccOpenUploadInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminFXAccOpenUploadInputResponse(WEB3AdminFXAccOpenUploadInputRequest l_request)
    {
        super(l_request);
    }
}
@
