head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityBuyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����������t�������̓��N�G�X�g(WEB3SuccEquityBuyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3EquityBuyInputRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�A���j�����������t�������̓��N�G�X�g)<BR>
 * �i�A���j�����������t�������̓��N�G�X�g�B
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3SuccEquityBuyInputRequest extends WEB3EquityBuyInputRequest 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquityBuyInputRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityBuyInput";
    
    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * @@roseuid 4348960503D8
     */
    public WEB3SuccEquityBuyInputRequest() 
    {
     
    }
    
    /**
     * ���X�|���X�f�[�^���쐬����B <BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderBuyInputResponse
     * @@roseuid 40602B2B00BE
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccEquityBuyInputResponse(this);
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
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02251<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02252<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"���t�i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"���t"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���R�[�h�l�́A�����\�񒍕��P�ʃe�[�u�����Q�ƁB<BR>
     * <BR>
     * �R�j�@@����敪�`�F�b�N<BR>
     * �@@�R�|�P�j�@@����敪��"�������t����"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�u����敪�������ΏۊO�v�̗�O��throw����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02255<BR>
     * <BR>
     * �S�j�@@�A����������敪�`�F�b�N<BR>
     * �@@�S�|�P�j�@@this.�A���������ʏ��.�A����������敪=="���t�i�O�񒍕��j"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@super.�����R�[�h==null�ł����<BR>
     * �@@�@@�@@�@@�@@�@@�u���Ύ�����͖����R�[�h�w��͕K�{�v�̗�O��throw����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02256<BR>
     * <BR>
     * �@@�S�|�Q�j�@@this.�A���������ʏ��.�A����������敪=="���t�i�O�񒍕��j"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@super.�s��R�[�h==null�ł����<BR>
     * �@@�@@�@@�@@�@@�@@�u���Ύ�����͎s��R�[�h�w��͕K�{�v�̗�O��throw����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02257<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4326AFEB036A
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@super.validate()���R�[������B
        super.validate();//WEB3BusinessLayerException
        
        //�Q�j�@@�A���������ʏ��`�F�b�N
        //�@@�Q�|�P�j�@@this.�A���������ʏ��==null�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
        if (this.succCommonInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                getClass().getName() + STR_METHOD_NAME,
                "�A���������ʏ�񂪖��w��ł��B");
        }

        //�@@�Q�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
        this.succCommonInfo.validate(); //WEB3BusinessLayerException

        //�@@�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
        if (!WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.BUY.equals(this.succCommonInfo.succTradingType))
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

        //�R�j�@@����敪�`�F�b�N
        //�@@�R�|�P�j�@@����敪��"�������t����"�̏ꍇ�́A
        //�@@�@@�@@�@@�@@�@@�u����敪�������ΏۊO�v�̗�O��throw����B
        if (!WEB3TradingTypeDef.BUY_ORDER.equals(super.tradingType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02255,
                getClass().getName() + STR_METHOD_NAME,
                "����敪���A�������̏����ΏۊO�ł��B");
        }

        //�S�j�@@�A����������敪�`�F�b�N
        //�@@�S�|�P�j�@@this.�A���������ʏ��.�A����������敪=="���t�i�O�񒍕��j"�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@super.�����R�[�h==null�ł����
        //�@@�@@�@@�@@�@@�@@�u���Ύ�����͖����R�[�h�w��͕K�{�v�̗�O��throw����B
        if (WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && super.productCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02256,
                getClass().getName() + STR_METHOD_NAME,
                "���Ύ�����͖����R�[�h�w��͕K�{�ł��B");
        }
        
        //�@@�S�|�Q�j�@@this.�A���������ʏ��.�A����������敪=="���t�i�O�񒍕��j"�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@super.�s��R�[�h==null�ł����
        //�@@�@@�@@�@@�@@�@@�u���Ύ�����͎s��R�[�h�w��͕K�{�v�̗�O��throw����B
        if (WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(this.succCommonInfo.succTradingType)
            && super.marketCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02257,
                getClass().getName() + STR_METHOD_NAME,
                "���Ύ�����͎s��R�[�h�w��͕K�{�ł��B");
        }

        log.exiting(STR_METHOD_NAME);     
    }
}
@
