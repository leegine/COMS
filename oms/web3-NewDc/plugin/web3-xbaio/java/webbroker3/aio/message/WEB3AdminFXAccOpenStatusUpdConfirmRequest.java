head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenStatusUpdConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�m�F���N�G�X�g(WEB3AdminFXAccOpenStatusUpdConfirmRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
 */

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�m�F���N�G�X�g) <BR>
 * �Ǘ��ҁEFX�����J�ݏ󋵍X�V�m�F���N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenStatusUpdConfirmRequest extends
    WEB3AdminFXAccOpenStatusUpdCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_status_upd_confirm";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * @@roseuid 41E78F65008C
     */
    public WEB3AdminFXAccOpenStatusUpdConfirmRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenStatusUpdConfirmRequest.class);
        
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �Ǘ��ҁEFX�����J�݃��X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904B029F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXAccOpenStatusUpdConfirmResponse(this);
    }
    
    /**
     * (validate) <BR>
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * �P�j �X�[�p�[�N���X.validate()���\�b�h���R�[������B
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        super.validate();
        log.exiting(STR_METHOD_NAME);
    }
}@
