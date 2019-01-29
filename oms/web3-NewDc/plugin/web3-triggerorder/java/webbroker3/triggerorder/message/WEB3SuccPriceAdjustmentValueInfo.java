head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.41.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccPriceAdjustmentValueInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�������P�������l���(WEB3SuccPriceAdjustmentValueInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.define.WEB3ToSuccSignDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�A�������P�������l���)<BR>
 * �A�������P�������l���B
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3SuccPriceAdjustmentValueInfo extends Message 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccPriceAdjustmentValueInfo.class);
    
    /**
     * (����)<BR>
     * �P�������l�̕����B<BR>
     * �i+�F���Z�A-�F���Z�j<BR>
     */
    public String sign;
    
    /**
     * (�P�������l)<BR>
     * �P�������l�B
     */
    public String priceAdjustmentValue;
    
    /**
     * @@roseuid 43489605006D
     */
    public WEB3SuccPriceAdjustmentValueInfo() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N<BR>
     * �@@�P�|�P�jthis.������null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u������null�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02243<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�������ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����̒l������`�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02244<BR>
     * <BR>
     * �@@�@@�@@�@@�E"+"<BR>
     * �@@�@@�@@�@@�E"-"<BR>
     * <BR>
     * �Q�j�@@�P�������l�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�P�������l��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�P�������l��null�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02260<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�P�������l���ȉ��̂����ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException<BR>
     *  �@@    tag:   BUSINESS_ERROR_02261<BR>
     * <BR>
     * �@@�@@�E�����ȊO<BR>
     * �@@�@@�E�}�C�i�X�l<BR>
     * �@@�@@�E�W���𒴂��鐔��<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4326B2500108
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����`�F�b�N
        // �@@�P�|�P�jthis.������null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�u������null�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.sign))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02243,
                getClass().getName() + STR_METHOD_NAME,
                "���������w��ł��B");
        }

        // �@@�P�|�Q�jthis.�������ȉ��̒l�ȊO�̏ꍇ�A
        // �@@�@@�@@�@@�@@�u�����̒l������`�v�̗�O���X���[����B
        if (!WEB3ToSuccSignDivDef.ADD.equals(this.sign)
            && !WEB3ToSuccSignDivDef.SUBTRACT.equals(this.sign))
        {
            String l_strMessage = "�����u" + this.sign + "�v�����݂��Ȃ��R�[�h�l�ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02244,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // �Q�j�@@�P�������l�`�F�b�N
        // �@@�Q�|�P�jthis.�P�������l��null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�u�P�������l��null�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.priceAdjustmentValue))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02260,
                getClass().getName() + STR_METHOD_NAME,
                "�P�������l�������͂ł��B");
        }
        
        // �@@�Q�|�Q�jthis.�P�������l���ȉ��̂����ꂩ�ɊY������ꍇ�́A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.priceAdjustmentValue) 
            || Double.parseDouble(this.priceAdjustmentValue) < 0
            || Double.parseDouble(this.priceAdjustmentValue) > 99999999)
        {
            String l_strMessage = "�P�������l�u" + this.priceAdjustmentValue + "�v���s���ȃR�[�h�l�ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02261,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (get�P�������l)<BR>
     * �P�������l���擾����B<BR>
     * �ithis.�P�������l��this.������t�������l���Adouble�^�ŕԋp����B�j<BR>
     * @@return double
     * @@roseuid 4326C15A0396
     */
    public double getPriceAdjustmentValue() 
    {
        String l_strPriceAdjustmentValue= this.sign + this.priceAdjustmentValue;
        return Double.parseDouble(l_strPriceAdjustmentValue);
    }
    
    /**
     * (set�P�������l)<BR>
     * �P�������l���Z�b�g����B<BR>
     * <BR>
     * this.�����F�@@�����̒P�������l�̕����i�����Ȃ����̓v���X�j���Z�b�g�B<BR>
     * this.�P�������l�F�@@�����̒P�������l�̐��l�������Z�b�g�B<BR>
     * @@param l_dblPriceAdjustmentValue - (�P�������l)<BR>
     * �P�������l�B<BR>
     * �i�����\�񒍕��P�ʃe�[�u���̓����ڂ̒l���Z�b�g�j
     * @@roseuid 4337BB660022
     */
    public void setPriceAdjustmentValue(double l_dblPriceAdjustmentValue) 
    {
        final String STR_METHOD_NAME =" setPriceAdjustmentValue(double )";
        log.entering(STR_METHOD_NAME);
        
        if (l_dblPriceAdjustmentValue < 0)
        {
            this.sign = WEB3ToSuccSignDivDef.SUBTRACT;
        }
        else
        {
            this.sign = WEB3ToSuccSignDivDef.ADD;
        }
        this.priceAdjustmentValue = Math.abs(l_dblPriceAdjustmentValue) + "";            
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
