head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExchangeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ב֏��(WEB3FeqExchangeUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.define.WEB3FeqRateDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�O�������ב֏��)<BR>
 * �O�������ב֏��N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3FeqExchangeUnit extends Message 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqExchangeUnit.class);
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     */
    public String currencyCode;
    
    /**
     * (���[�g�敪)<BR>
     * ���[�g�敪<BR>
     * <BR>
     * 0�F��ב�<BR>
     * 1�F���ב�
     */
    public String rateDiv;
    
    /**
     * (���t�בփ��[�g)<BR>
     * ���t�בփ��[�g<BR>
     * <BR>
     * ���o�^���̓��X�|���X�ŃZ�b�g�����l�́A�ύX�O�̒l�B<BR>
     *    ����ȊO�ŃZ�b�g�����l�́A�ύX��̒l�B<BR>
     *    �i���͂���ĂȂ��ꍇ�́Anull�j
     */
    public String sellExchangeRate;
    
    /**
     * (���t�בփ��[�g)<BR>
     * ���t�בփ��[�g<BR>
     * <BR>
     * ���o�^���̓��X�|���X�ŃZ�b�g�����l�́A�ύX�O�̒l�B<BR>
     *    ����ȊO�ŃZ�b�g�����l�́A�ύX��̒l�B<BR>
     *    �i���͂���ĂȂ��ꍇ�́Anull�j
     */
    public String buyExchangeRate;
    
    /**
     * (�o�^����)<BR>
     * �o�^����
     */
    public Date registDatetime;
    
    /**
     * (�O�������ב֏��)<BR>
     * �R���X�g���N�^
     * @@roseuid 4200A6850060
     */
    public WEB3FeqExchangeUnit() 
    {
     
    }
    
    /**
     * �ב֏��f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���t�בփ��[�g�̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�i���t�בփ��[�g != null�j�̂݁A�ȉ��̏������{<BR>
     * <BR>
     * �@@�P�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02013<BR>
     * �@@�P�|�Q�j�@@���l�ɕϊ��������̗L���������A�������R���C<BR>
     * �@@�������S���͈̔͊O�ł���΁A��O�����X���[����B<BR>
     * �@@�@@�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂���<BR>
     * �@@�@@�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02014<BR>�@@
     * �@@�@@�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02015<BR>�@@
     * �@@�P�|�R�j�@@���t�בփ��[�g <= 0 �̏ꍇ�A��O�����X���[����B<BR>
     * �@@�@@�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂���<BR>
     * �@@�@@�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02014<BR>�@@
     * �@@�@@�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02015<BR>�@@
     * <BR>
     * �Q�j�@@���t�בփ��[�g�̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�i���t�בփ��[�g != null�j�̂݁A�ȉ��̏������{<BR>
     * <BR>
     * �@@�Q�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02016<BR>
     * �@@�Q�|�Q�j�@@���l�ɕϊ��������̗L���������A�������R���C<BR>
     * �������S���͈̔͊O�ł���΁A��O�����X���[����B<BR>
     * �@@�@@�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂���<BR>
     * �@@�@@�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02017<BR>�@@
     * �@@�@@�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02018<BR>�@@
     * �@@�Q�|�R�j�@@���t�בփ��[�g <= 0 �̏ꍇ�A��O�����X���[����B<BR>
     * �@@�@@�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂���<BR>
     * �@@�@@�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02017<BR>�@@
     * �@@�@@�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02018<BR>�@@
     * @@throws WEB3BaseException
     * @@roseuid 42BA5C6E005D
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���t�בփ��[�g�̃`�F�b�N
        //���͂�����ꍇ�i���t�בփ��[�g != null�j�̂݁A�ȉ��̏������{
        if (!WEB3StringTypeUtility.isEmpty(this.sellExchangeRate))
        {
            //�P�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isNumber(this.sellExchangeRate))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02013,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ���t�בփ��[�g�����l�ȊO�̒l�ł�:" + this.sellExchangeRate); 
            }
            
            //�P�|�Q�j�@@���l�ɕϊ��������̗L���������A�������R���C
            //�������S���͈̔͊O�ł���΁A��O�����X���[����B
            int l_intIntCnt = WEB3StringTypeUtility.getIntegerDigits(this.sellExchangeRate);
            int l_intFacCnt = WEB3StringTypeUtility.getFractionDigits(this.sellExchangeRate);
                
            if (l_intIntCnt > 3 || l_intFacCnt > 4)
            {
                //�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂��� 
                //�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02014,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ���t�בփ��[�g(��ב�)���������R���C�������S���͈̔͊O�ł�:" + this.sellExchangeRate); 
                }                   
                //�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02015,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ���t�בփ��[�g(���ב�)���������R���C�������S���͈̔͊O�ł�:" + this.sellExchangeRate); 
                }
            }

            double l_dblSellExecRate = Double.parseDouble(this.sellExchangeRate);
            //�P�|�R�j�@@���t�בփ��[�g <= 0 �̏ꍇ�A��O�����X���[����B
            if (l_dblSellExecRate <= 0)
            {
                //�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂��� 
                //�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02014,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ���t�בփ��[�g(��ב�)��<= 0�ł�:" + this.sellExchangeRate); 
                }                   
                //�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02015,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ���t�בփ��[�g(���ב�)��<= 0�ł�:" + this.sellExchangeRate); 
                }
            }                
        }            
            
        //�Q�j�@@���t�בփ��[�g�̃`�F�b�N
        //���͂�����ꍇ�i���t�בփ��[�g != null�j�̂݁A�ȉ��̏������{
        if (!WEB3StringTypeUtility.isEmpty(this.buyExchangeRate))
        {
            //�Q�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isNumber(this.buyExchangeRate))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02016,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ���t�בփ��[�g�����l�ȊO�̒l�ł�:" + this.buyExchangeRate); 
            }
                
            //�Q�|�Q�j�@@���l�ɕϊ��������̗L���������A�������R���C
            //�������S���͈̔͊O�ł���΁A��O�����X���[����B    
            int l_intIntCnt = WEB3StringTypeUtility.getIntegerDigits(this.buyExchangeRate);
            int l_intFacCnt = WEB3StringTypeUtility.getFractionDigits(this.buyExchangeRate);
                
            if (l_intIntCnt > 3 || l_intFacCnt > 4)
            {
                //�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂��� 
                //�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02017,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ���t�בփ��[�g(��ב�)���������R���C�������S���͈̔͊O�ł�:" + this.buyExchangeRate); 
                }                   
                //�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02018,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ���t�בփ��[�g(���ב�)���������R���C�������S���͈̔͊O�ł�:" + this.buyExchangeRate); 
                }
            }
            
            double l_dblBuyExecRate = Double.parseDouble(this.buyExchangeRate);
            //�Q�|�R�j�@@���t�בփ��[�g <= 0 �̏ꍇ�A��O�����X���[����B
            if (l_dblBuyExecRate <= 0)
            {
                //�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂��� 
                //�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02017,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ���t�בփ��[�g(��ב�)��<= 0�ł�:" + this.buyExchangeRate); 
                }                   
                //�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {                    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02018,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ���t�בփ��[�g(���ב�)��<= 0�ł�:" + this.buyExchangeRate); 
                }
            }
        }                                    
        
        log.exiting(STR_METHOD_NAME);       
    }
}
@
