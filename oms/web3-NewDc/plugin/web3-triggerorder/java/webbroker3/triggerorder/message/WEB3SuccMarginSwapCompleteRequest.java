head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginSwapCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����������n�����������N�G�X�g�N���X(WEB3SuccMarginSwapCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A����(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p����������n�����������N�G�X�g�N���X)<BR>
 * �i�A���j�M�p����������n�����������N�G�X�g�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3SuccMarginSwapCompleteRequest extends WEB3MarginSwapMarginCompleteRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccMarginSwapCompleteRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginSwapComplete";
    
    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * @@roseuid 4369CC4302AF
     */
    public WEB3SuccMarginSwapCompleteRequest() 
    {
     
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccMarginSwapCompleteResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�A���������ʏ��`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A���������ʏ��w��Ȃ��v�̗�O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_02251<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B<BR>
     * <BR>
     * �@@�P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"�������n�i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"�������n�i�����c�j"<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_02252<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���R�[�h�l�́A�����\�񒍕��P�ʃe�[�u�����Q�ƁB<BR>
     * <BR>
     * �Q�j�@@this.�A���������ʏ��.�A����������敪=="�������n�i�����c�j"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@super.validate()���R�[������B<BR>
     * �@@�@@�@@�ȊO�Asuper.validateAT���Ύ��()���R�[������B<BR>
     * <BR>
     * �R�j�@@this.�A���������ʏ��.�A����������敪=="�������n�i�O�񒍕��j"�̏ꍇ�A <BR>
     * �@@�@@�@@���N�G�X�g.���Ϗ����敪==�inull or "������"�j�̏ꍇ�́A <BR>
     * �@@�@@�@@�u�A�������E���Ύ�����̌��Ϗ����敪�w��s���v�̗�O��throw����B <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_02306<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 432944F403C7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�A���������ʏ��`�F�b�N
        // �@@�P�|�P�j�@@this.�A���������ʏ��==null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
        if (this.succCommonInfo == null)
        {
            log.debug("�A���������ʏ�񂪖��w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A���������ʏ�񂪖��w��ł��B");
        }
        
       //�@@�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
        this.succCommonInfo.validate();
        
        // �P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
        if ((!WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType))
            && (!WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_EXISTING_REMAINDER.equals(
                this.succCommonInfo.succTradingType))) 
        {
            log.debug("�A����������敪�̒l�������ΏۊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A����������敪�̒l�������ΏۊO�ł��B");
        }
        
        // �Q�j�@@this.�A���������ʏ��.�A����������敪=="�������n�i�����c�j"�̏ꍇ�̂݁A
        // �@@�@@�@@super.validate()���R�[������B
        // �@@�@@�@@�ȊO�Asuper.validateAT���Ύ��()���R�[������B
        if (WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_EXISTING_REMAINDER.equals(
            this.succCommonInfo.succTradingType))
        {
            super.validate();
        }
        else
        {
            super.validateAtReverseOrder();
        }
        
        // �R�j�@@���N�G�X�g.���Ϗ����敪�`�F�b�N
        if (WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER.equals(
        this.succCommonInfo.succTradingType))
        {
            if (this.closingOrder == null
            || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02306,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���Ύ�����̌��Ϗ����敪�w��s���B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
