head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccInfoChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�������ύX�m�F���N�G�X�g(WEB3AdminFXAccInfoChangeConfirmRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
 */

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁEFX�������ύX�m�F���N�G�X�g) <BR>
 * �Ǘ��ҁEFX�������ύX�m�F���N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccInfoChangeConfirmRequest extends
    WEB3AdminFXAccInfoChangeCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_info_change_confirm";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * @@roseuid 41E78FE30261
     */
    public WEB3AdminFXAccInfoChangeConfirmRequest()
    {
    }

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccInfoChangeConfirmRequest.class);
        
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �Ǘ��ҁEFX�������ύX�m�F���X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXAccInfoChangeConfirmResponse(this);
    }
    
    /**
     * (validate) <BR>
     * �N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j ���͓��e�̃`�F�b�N <BR>
     * �P�|�P�j�X�[�p�[�N���X��validate()���R�[������B
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j ���͓��e�̃`�F�b�N 
        //�P�|�P�j�X�[�p�[�N���X��validate()���R�[������B
        super.validate();
        
        log.exiting(STR_METHOD_NAME);
    }
}@
