head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenStatusUpdCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�������X�|���X(WEB3AdminFXAccOpenStatusUpdCompleteResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�������X�|���X) <BR>
 * �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�������X�|���X�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenStatusUpdCompleteResponse extends
    WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_status_upd_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * @@roseuid 41E78F6502AF
     */
    public WEB3AdminFXAccOpenStatusUpdCompleteResponse()
    {
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFXAccOpenStatusUpdCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@