head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenApplyDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���X�|���X(WEB3AdminFXAccOpenApplyDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 �]�V�q(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import java.util.Date;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���X�|���X)<BR>
 * �Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���X�|���X�N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenApplyDownloadResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_apply_download";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200602091550L;
    
    /**
     * (�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C��<BR>
     * <BR>
     * �� CSV�t�@@�C���s�̔z��<BR>
     */
    public String[] downloadFile;
    
    /**
     * (���ݓ���)<BR>
     * ���ݓ���<BR>
     */
    public Date currentDate;
    
    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3AdminFXAccOpenApplyDownloadResponse()
    {
        
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFXAccOpenApplyDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
