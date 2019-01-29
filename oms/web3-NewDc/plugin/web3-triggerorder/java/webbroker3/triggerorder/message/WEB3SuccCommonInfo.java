head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccCommonInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���������ʏ��(WEB3SuccCommonInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�A���������ʏ��)<BR>
 * �A���������ʏ��B
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3SuccCommonInfo extends Message 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccCommonInfo.class);
    
    /**
     * (�i�e�����j����ID)<BR>
     * �i�e�����j����ID�B
     */
    public String parentOrderId;
    
    /**
     * (�A����������敪)<BR>
     * �A����������敪�B
     */
    public String succTradingType;
    
    /**
     * @@roseuid 4348960601F4
     */
    public WEB3SuccCommonInfo() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�i�e�����j����ID�`�F�b�N<BR>
     * �@@�P�|�P�j�i�e�����j����ID��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�i�e�����j����ID��null�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02258<BR>
     * <BR>
     * �@@�P�|�Q�j�i�e�����j����ID��0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�i�e�����j����ID�̒l���s���v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02259<BR>
     * <BR>
     * �Q�j�@@�A����������敪�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�A����������敪��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�A����������敪��null�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02262<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�A����������敪�̒l������`�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02263<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"���t�i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"���t"<BR>
     * �@@�@@�@@�@@�@@"���t�i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"���t�i�����c�j"<BR>
     * �@@�@@�@@�@@�@@"�M�p�V�K���i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"�M�p�V�K��"<BR>
     * �@@�@@�@@�@@�@@"�M�p�ԍρi�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"�M�p�ԍρi�����c�j"<BR>
     * �@@�@@�@@�@@�@@"�������n�i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"�������n�i�����c�j"<BR>
     * �@@�@@�@@�@@�@@"�敨�V�K���i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"�敨�V�K��"<BR>
     * �@@�@@�@@�@@�@@"�敨�ԍρi�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"�敨�ԍρi�����c�j"<BR>
     * �@@�@@�@@�@@�@@"OP�V�K���i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"OP�V�K��"<BR>
     * �@@�@@�@@�@@�@@"OP�ԍρi�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"OP�ԍρi�����c�j"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���R�[�h�l�́A�����\�񒍕��P�ʃe�[�u�����Q�ƁB<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4326AEBA03E7
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�i�e�����j����ID�`�F�b�N
        //�@@�P�|�P�j�i�e�����j����ID��null�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u�i�e�����j����ID��null�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.parentOrderId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02258,
                getClass().getName() + STR_METHOD_NAME,
                "�i�e�����j����ID�����w��ł��B");
        }

        //�@@�P�|�Q�j�i�e�����j����ID��0�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u�i�e�����j����ID�̒l���s���v�̗�O���X���[����B
        if (Double.parseDouble(this.parentOrderId) < 0)
        {
            String l_strMessage = "�e�����j����ID�u" + this.parentOrderId + "�v���l���s���ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02259,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //�Q�j�@@�A����������敪�`�F�b�N
        //�@@�Q�|�P�jthis.�A����������敪��null�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u�A����������敪��null�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.succTradingType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02262,
                getClass().getName() + STR_METHOD_NAME,
                "�A����������敪��null�ł��B");
        }

        //�@@�Q�|�Q�jthis.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u�A����������敪�̒l������`�v�̗�O���X���[����B
        if (!WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.BUY.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SELL_EXISTING_REMAINDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_EXISTING_REMAINDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_EXISTING_REMAINDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.OPEN_OP.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(this.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(this.succTradingType))
        {
            String l_strMessage = "�A����������敪�u" + this.succTradingType + "�v���s���ȃR�[�h�l�ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02263,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        log.exiting(STR_METHOD_NAME);
     
    }
}
@
