head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferUploadCancelResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁEFX�U�֒����A�b�v���[�h���~���X�|���X(WEB3AdminFXTransferUploadCancelResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/28 �A����(���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁEFX�U�֒����A�b�v���[�h���~���X�|���X)<BR>
 * �Ǘ��ҁEFX�U�֒����A�b�v���[�h���~���X�|���X�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXTransferUploadCancelResponse extends WEB3GenResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602221850L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fx_transfer_upload_cancel";
    
    /**
     * @@roseuid 43FC1AE40157
     */
    public WEB3AdminFXTransferUploadCancelResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminFXTransferUploadCancelResponse(WEB3AdminFXTransferUploadCancelRequest l_request)
    {
        super(l_request);
    }
}
@
