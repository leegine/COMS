head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁEFX�����J�݃A�b�v���[�h�������X�|���X(WEB3AdminFXAccOpenUploadCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 �A����(���u) �V�K�쐬
                 : 2006/03/09 ���iSRA�j �G���[�����ꗗ�̃V���{�����ύX�Ή�
Revesion History : 2008/09/12 ���m�a (���u) �d�l�ύX�E���f��989
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁEFX�����J�݃A�b�v���[�h�������X�|���X)<BR>
 * �Ǘ��ҁEFX�����J�݃A�b�v���[�h�������X�|���X�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602181050L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fx_acc_open_upload_complete";
    
    /**
     * (�A�b�v���[�h����)<BR>
     * �A�b�v���[�h����<BR>
     */
    public String uploadNumber;
    
    /**
     * (�G���[�����ꗗ)<BR>
     * �G���[�����ꗗ<BR>
     * <BR>
     * ���o�^�G���[�ƂȂ������p�҃R�[�h�̔z��<BR>
     */
    public String[] errorAccountList;
    
    /**
     * @@roseuid 43F49D5C01E4
     */
    public WEB3AdminFXAccOpenUploadCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminFXAccOpenUploadCompleteResponse(WEB3AdminFXAccOpenUploadCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
