head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquitySellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����������t�������̓��N�G�X�g(WEB3SuccEquitySellInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3EquitySellInputRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�A���j�����������t�������̓��N�G�X�g)<BR>
 * �i�A���j�����������t�������̓��N�G�X�g�B
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3SuccEquitySellInputRequest extends WEB3EquitySellInputRequest 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquitySellInputRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equitySellInput";
    
    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * @@roseuid 434896050186
     */
    public WEB3SuccEquitySellInputRequest() 
    {
     
    }
    
    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.equity.message.WEB3EquitySellInputResponse
     * @@roseuid 40602C24036C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccEquitySellInputResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�A���������ʏ��`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�A���������ʏ��==null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A���������ʏ�񂪖��w��v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02251<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B<BR>
     * <BR>
     * �@@�P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02252<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"���t�i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"���t�i�����c�j"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���R�[�h�l�́A�����\�񒍕��P�ʃe�[�u�����Q�ƁB<BR>
     * <BR>
     * �Q�j�@@this.�A���������ʏ��.�A����������敪=="���t�i�O�񒍕��j"�̏ꍇ�A<BR>
     * �@@�@@�@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�j super.�s��R�[�h==null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�u���Ύ�����͎s��R�[�h�w��͕K�{�v�̗�O��throw����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02257<BR>
     * <BR>
     * �R�j�@@this.�A���������ʏ��.�A����������敪=="���t�i�����c�j"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@super.validate()���R�[������B<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4326B05901F3
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�A���������ʏ��`�F�b�N
        //�@@�P�|�P�j�@@this.�A���������ʏ��==null�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
        if (this.succCommonInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                getClass().getName() + STR_METHOD_NAME,
                "�A���������ʏ�񂪖��w��ł��B");
        }

        //�@@�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
        this.succCommonInfo.validate(); //WEB3BusinessLayerException
        
        //�@@�P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
        if (!WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SELL_EXISTING_REMAINDER.equals(this.succCommonInfo.succTradingType))
        {
            String l_strMessage = "�A����������敪�u" 
                + this.succCommonInfo.succTradingType 
                + "�v�̒l�������ΏۊO�ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //�Q�j�@@this.�A���������ʏ��.�A����������敪=="���t�i�O�񒍕��j"�̏ꍇ�A
        //�@@�@@�@@�ȉ��̃`�F�b�N���s���B
        if (WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType))
        {
            //�@@�Q�|�P�j super.�s��R�[�h==null�̏ꍇ�́A
            //�@@�@@�@@�@@�@@�@@�u���Ύ�����͎s��R�[�h�w��͕K�{�v�̗�O��throw����B
            if (super.marketCode == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02257,
                    getClass().getName() + STR_METHOD_NAME,
                    "���Ύ�����͎s��R�[�h�w��͕K�{�ł��B");
            }
        }
        //�R�j�@@this.�A���������ʏ��.�A����������敪=="���t�i�����c�j"�̏ꍇ�̂݁A
        //�@@�@@�@@super.validate()���R�[������B
        else if (WEB3ReserveOrderTradingTypeDef.SELL_EXISTING_REMAINDER.equals(this.succCommonInfo.succTradingType))
        {
            super.validate(); //WEB3BusinessLayerException
        }

        log.exiting(STR_METHOD_NAME);
     
    }
}
@
