head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccInfoSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX������񌟍����N�G�X�g(WEB3AdminFXAccInfoSearchRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
 Revesion History : 2008/05/19 �đo�g(���u) �d�l�ύX ���f��No.866
 */

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioInputNumberDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҁEFX������񌟍����N�G�X�g) <BR>
 * �Ǘ��ҁEFX������񌟍����N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccInfoSearchRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_info_search";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (���X�R�[�h) <BR>
     * ��ʂɂđI�����ꂽ���X�R�[�h
     */
    public String branchCode;

    /**
     * (���͔ԍ��敪)<BR>
     * �ȉ��̂����ꂩ�B<BR>
     * <BR>
     * 1�FFX���O�C��ID 2�FFX�����ԍ� 3�F�ڋq�R�[�h
     */
    public String inputNumberDiv;

    /**
     * (���͔ԍ�) <BR>
     * ���͔ԍ��敪��1�iFX���O�C��ID�j�̏ꍇ�A�iFX�j���O�C��ID <BR>
     * ���͔ԍ��敪��2�iFX�����ԍ��j�̏ꍇ�A�iFX�j�����ԍ� <BR>
     * ���͔ԍ��敪��3�i�ڋq�R�[�h�j�̏ꍇ�A�ڋq�R�[�h
     */
    public String inputNumber;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E78FFF001F
     */
    public WEB3AdminFXAccInfoSearchRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccInfoSearchRequest.class);
        
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
     * �P�|�Q�j �ȉ��̏����ɊY������ꍇ�A��O���X���[����B <BR>
     * �E���X�R�[�h != ���l <BR>
     * �E���X�R�[�h�̌��� != 3�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01729 <BR>
     * <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00834 <BR>
     * <BR>
     * �Q�j ���͔ԍ��敪�̃`�F�b�N <BR>
     * �Q�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01763 <BR>
     * <BR>
     * �Q�|�Q�j �ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �E"FX���O�C��ID" <BR>
     * �E"FX�����ԍ�" <BR>
     * �E"�ڋq�R�[�h" <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01764 <BR>
     * <BR>
     * �R�j ���͔ԍ��̃`�F�b�N <BR>
     * �R�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01765 <BR>
     * <BR>
     * �R�|�Q�j �ȉ��̏����ɊY������ꍇ�A��O���X���[����B <BR>
     * �E���͔ԍ� != ���l <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01766 <BR>
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41BD4C83025D
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
                "���X�R�[�h�����w��ł��B");
        }
        
        // �P�|�Q�j�@@�ȉ��̏����ɊY������ꍇ�A��O���X���[����B 
        //�@@ �E���X�R�[�h != ���l 
        if (!WEB3StringTypeUtility.isNumber(this.branchCode))
        {
            log.debug("���X�R�[�h�̒l�����l�ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̒l�����l�ȊO�̒l�ł��B");
        }
        
        //�E���X�R�[�h�̌��� != 3�� 
        if (this.branchCode.getBytes().length != 3)
        {
            log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̃T�C�Y���s���ł��B");
        }
        
        //�Q�j���͔ԍ��敪�̃`�F�b�N 
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.inputNumberDiv))
        {
            log.debug("���͔ԍ��敪�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01763,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͔ԍ��敪�����w��ł��B");
        }
        
        //�Q�|�Q�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
        // �E"FX���O�C��ID" 
        // �E"FX�����ԍ�" 
        // �E"�ڋq�R�[�h" 
        if (!(WEB3AioInputNumberDivDef.FX_LOGINID.equals(this.inputNumberDiv)
            || WEB3AioInputNumberDivDef.FX_ACCOUNT_CODE.equals(this.inputNumberDiv)
            || WEB3AioInputNumberDivDef.ACCOUNT_CODE.equals(this.inputNumberDiv)))
        {
            log.debug("���͔ԍ��敪�����݂��Ȃ��R�[�h�l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01764,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͔ԍ��敪�����݂��Ȃ��R�[�h�l�ł��B");
        }
       
        //�R�j���͔ԍ��̃`�F�b�N 
        //�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.inputNumber))
        {
            log.debug("���͔ԍ������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01765,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͔ԍ������w��ł��B");
        }
        
        //�R�|�Q�j�@@�ȉ��̏����ɊY������ꍇ�A��O���X���[����B 
        //�E���͔ԍ� != ���l 
        if (!WEB3StringTypeUtility.isNumber(this.inputNumber))
        {
            log.debug("���͔ԍ������l�ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͔ԍ������l�ȊO�̒l�ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �Ǘ��ҁEFX������񌟍����X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E78FFF00AB
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXAccInfoSearchResponse(this);
    }
}@
