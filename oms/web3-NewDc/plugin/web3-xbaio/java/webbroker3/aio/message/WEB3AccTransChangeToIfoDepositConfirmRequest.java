head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeToIfoDepositConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����ւ̐U�֊m�F���N�G�X�g�N���X(WEB3AccTransChangeToIfoDepositConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ������ (���u) �V�K�쐬
                   2004/10/22 ���z (���u) ���r���[
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
 * (�؋����ւ̐U�֊m�F���N�G�X�g)<BR>
 * �؋����ւ̐U�֊m�F���N�G�X�g�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeToIfoDepositConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeToIfoDepositConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_to_ifo_deposit_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;        
    
    /**
     * (�U�֋��z)<BR>
     * ��ʂ�����͂��ꂽ�U�֋��z
     */
    public String changeAmt;
    
    /**
     * @@roseuid 4158E99103BF
     */
    public WEB3AccTransChangeToIfoDepositConfirmRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * �P�j�U�֋��z�`�F�b�N<BR>
     * �P�|�P�j   ���N�G�X�g�f�[�^.�U�֋��z = null   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00759<BR>
     * <BR>
     * <BR>
     * �P�|�Q�j   ���N�G�X�g�f�[�^.�U�֋��z <= 0   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00809<BR>
     * <BR>
     * <BR>
     * �P�|�R�j   ���N�G�X�g�f�[�^.�U�֋��zlength() > 9   �̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00810<BR>
     * <BR>
     * <BR>
     * �P�|�S�j   ���N�G�X�g�f�[�^.�U�֋��z�ɐ����ȊO�̕������܂܂��   <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00811<BR>
     * <BR>
     * @@throws WEB3BaseException 
     * @@roseuid 413422B900E3
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�U�֋��z�`�F�b�N
        // �P�|�P�j���N�G�X�g�f�[�^.�U�֋��z = null  �̏ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.changeAmt))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00759,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�U�֋��z = null");
        }

        // �P�|�R�j���N�G�X�g�f�[�^.�U�֋��zlength() > 9   �̏ꍇ�A��O���X���[����
        if (this.changeAmt.length() > 9)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00810,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�U�֋��z.length() > 9, " +
                "���N�G�X�g�f�[�^.�U�֋��z.length() = " + this.changeAmt.length());
        }
        
        // �P�|�S�j���N�G�X�g�f�[�^.�U�֋��z�ɐ����ȊO�̕������܂܂��̏ꍇ�A��O���X���[����
        if(!WEB3StringTypeUtility.isNumber(this.changeAmt))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00811,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�U�֋��z�ɐ����ȊO�̕������܂܂��," +
                "���N�G�X�g�f�[�^.�U�֋��z = " + this.changeAmt);
        }
        
        // �P�|�Q�j���N�G�X�g�f�[�^.�U�֋��z <= 0   �̏ꍇ�A��O���X���[����
        if (Double.parseDouble(this.changeAmt) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00809,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�U�֋��z <= 0, " +
                "���N�G�X�g�f�[�^.�U�֋��z = " + this.changeAmt);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreateResponse�̎����j <BR>
     * <BR>
     * �؋����ւ̐U�֊m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E99103D3
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AccTransChangeToIfoDepositConfirmResponse(this);
    }
}
@
