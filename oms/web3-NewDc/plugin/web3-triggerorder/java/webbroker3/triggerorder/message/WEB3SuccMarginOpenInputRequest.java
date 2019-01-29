head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginOpenInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����V�K���������̓��N�G�X�g(WEB3SuccMarginOpenInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A����(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginInputRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p����V�K���������̓��N�G�X�g)<BR>
 * �i�A���j�M�p����V�K���������̓��N�G�X�g<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3SuccMarginOpenInputRequest extends WEB3MarginOpenMarginInputRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccMarginOpenInputRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginOpenInput";
    
    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * @@roseuid 4369CBD7030D
     */
    public WEB3SuccMarginOpenInputRequest() 
    {
     
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccMarginOpenInputResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�A���������ʏ��`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�A���������ʏ��==null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A���������ʏ�񂪖��w��v�̗�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02251<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"�M�p�V�K���i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"�M�p�V�K��"<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_02252<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���R�[�h�l�́A�����\�񒍕��P�ʃe�[�u�����Q�ƁB<BR>
     * <BR>
     * �R�j�@@�A����������敪�`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�A���������ʏ��.�A����������敪=="�M�p�V�K���i�O�񒍕��j"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@super.�����R�[�h==null�ł����<BR>
     * �@@�@@�@@�@@�@@�@@�u���Ύ�����͖����R�[�h�w��͕K�{�v�̗�O��throw����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_02256<BR>
     * <BR>
     * �@@�R�|�Q�j�@@this.�A���������ʏ��.�A����������敪=="�M�p�V�K���i�O�񒍕��j"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@super.�s��R�[�h==null�ł����<BR>
     * �@@�@@�@@�@@�@@�@@�u���Ύ�����͎s��R�[�h�w��͕K�{�v�̗�O��throw����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02257<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4328E32E01CC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
         
        //�P�j�@@super.validate()���R�[������B
        super.validate();
         
        //�Q�j�@@�A���������ʏ��`�F�b�N
        // �@@�Q�|�P�j�@@this.�A���������ʏ��==null�̏ꍇ�A
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
        
        //�Q�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
        this.succCommonInfo.validate();
        
        //�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
        if ((!WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType))
            && (!WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT.equals(this.succCommonInfo.succTradingType))) 
        {
            log.debug("�A����������敪�̒l�������ΏۊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A����������敪�̒l�������ΏۊO�ł��B");
        }
        
        //�R�j�@@�A����������敪�`�F�b�N
        if (WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType))
        {
            //  �R�|�P�j�@@this.�A���������ʏ��.�A����������敪==
            //          "�M�p�V�K���i�O�񒍕��j"�̏ꍇ�A
            // �@@�@@�@@�@@�@@�@@super.�����R�[�h==null�ł����
            // �@@�@@�@@�@@�@@�@@���Ύ�����͖����R�[�h�w��͕K�{�v�̗�O��throw����B
            if (super.productCode == null)
            {
                log.debug("���Ύ�����͖����R�[�h�w��͕K�{�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02256,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���Ύ�����͖����R�[�h�w��͕K�{�ł��B");
            }
            
            //�R�|�Q�j�@@this.�A���������ʏ��.�A����������敪==
            //        "�M�p�V�K���i�O�񒍕��j"�̏ꍇ�A
            // �@@�@@�@@�@@ super.�s��R�[�h==null�ł����
            // �@@�@@�@@�@@�u���Ύ�����͎s��R�[�h�w��͕K�{�v�̗�O��throw����B
            if (super.marketCode == null) 
            {
                log.debug("���Ύ�����͎s��R�[�h�w��͕K�{�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02257,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���Ύ�����͎s��R�[�h�w��͕K�{�ł��B"); 
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
