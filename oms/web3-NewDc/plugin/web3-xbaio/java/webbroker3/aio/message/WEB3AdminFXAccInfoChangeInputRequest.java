head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccInfoChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�������ύX���̓��N�G�X�g(WEB3AdminFXAccInfoChangeInputRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
 Revesion History : 2008/05/19 �đo�g(���u) �d�l�ύX ���f��No.866
 */

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁEFX�������ύX���̓��N�G�X�g) <BR>
 * �Ǘ��ҁEFX�������ύX���̓��N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccInfoChangeInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_info_change_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (���X�R�[�h) <BR>
     * �I�����ꂽ���X�R�[�h
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h) <BR>
     * �ڋq�R�[�h
     */
    public String accountCode;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E78FE20251
     */
    public WEB3AdminFXAccInfoChangeInputRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccInfoChangeInputRequest.class);
        
    /**
     * (validate) <BR>
     * �N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j ���X�R�[�h�̃`�F�b�N <BR>
     * �P�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00833 <BR>
     * <BR>
     * �Q�j �ڋq�R�[�h�̃`�F�b�N <BR>
     * �Q�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00835 <BR>
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���X�R�[�h�̃`�F�b�N 
        // �P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("���X�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX�������������w��ł��B");
        }
        
        //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N 
        //�@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.accountCode))
        {
            log.debug("�ڋq�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �Ǘ��ҁEFX�������ύX���̓��X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E78FE202DE
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXAccInfoChangeInputResponse(this);
    }
}@
