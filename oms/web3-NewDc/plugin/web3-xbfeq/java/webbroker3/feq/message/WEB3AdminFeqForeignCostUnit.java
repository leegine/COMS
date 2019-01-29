head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqForeignCostUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���n�萔�����(WEB3AdminFeqForeignCostUnit.java)
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
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���n�萔�����)<BR>
 * ���n�萔�����N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostUnit extends Message 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqForeignCostUnit.class);
        
    /**
     * (�K�p���ԁi���j)<BR>
     * �K�p���ԁi���j
     */
    public Date applyStartDate;
    
    /**
     * (�K�p���ԁi���j)<BR>
     * �K�p���ԁi���j
     */
    public Date applyEndDate;
    
    /**
     * (������z�i���j)<BR>
     * ������z�i���j
     */
    public String tradingAmtFrom;
    
    /**
     * (������z�i���j)<BR>
     * ������z�i���j
     */
    public String tradingAmtTo;
    
    /**
     * (������)<BR>
     * ������<BR>
     * �P�ʁF%
     */
    public String collectRate;
    
    /**
     * (�t�����z)<BR>
     * �t�����z
     */
    public String additionalAmt;
    
    /**
     * (���n�萔�����)<BR>
     * �R���X�g���N�^
     * @@roseuid 4200B8FE0104
     */
    public WEB3AdminFeqForeignCostUnit() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�K�p���ԁi���j<BR>
     * <BR>
     *    this.�K�p���ԁi���j == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02073<BR>
     * <BR>
     * �Q�j�K�p���ԁi���j<BR>
     * <BR>
     *    this.�K�p���ԁi���j != null and<BR>
     *    this.�K�p���ԁi���j > this.�K�p���ԁi���j<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_01446<BR>
     * <BR>
     * �R�j������z�i���j<BR>
     * <BR>
     * �R�|�P�j<BR>
     * <BR>
     *    this.������z�i���j == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02074<BR>
     * <BR>
     * �R�|�Q�j<BR>
     * <BR>
     *    this.������z�i���j < 0<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02075<BR>
     * <BR>
     * �R�|�R�jthis.������z�i���j�ɏ����_���܂܂��ꍇ<BR>
     * �ithis.������z�i���j.indexOf('.') >= 0 �̏ꍇ�j<BR>
     * <BR>
     *    this.������z�i���j.split('.')[0].length() > 11 or<BR>
     *    this.������z�i���j.split('.')[1].length() > 2<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02076<BR>
     * <BR>
     * �R�|�S�jthis.������z�i���j�ɏ����_���܂܂�Ȃ��ꍇ<BR>
     * �ithis.������z�i���j.indexOf('.') = -1 �̏ꍇ�j<BR>
     *    this.������z�i���j.length() > 11<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02076<BR>
     * <BR>
     * �S�j������z�i���j<BR>
     * <BR>
     *    this.������z�i���j != null �̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �S�|�P�j<BR>
     * <BR>
     *    this.������z�i���j < 0<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02077<BR>
     * <BR>
     * �S�|�Q�jthis.������z�i���j�ɏ����_���܂܂��ꍇ<BR>
     * �ithis.������z�i���j.indexOf('.') >= 0 �̏ꍇ�j<BR>
     * <BR>
     *    this.������z�i���j.split('.')[0].length() > 11 or<BR>
     *    this.������z�i���j.split('.')[1].length() > 2<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02078<BR>
     * <BR>
     * �S�|�R�jthis.������z�i���j�ɏ����_���܂܂�Ȃ��ꍇ<BR>
     * �ithis.������z�i���j.indexOf('.') = -1 �̏ꍇ�j<BR>
     * <BR>
     *    this.������z�i���j.length() > 11<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02078<BR>
     * <BR>
     * �S�|�S�j<BR>
     * <BR>
     *    this.������z�i���j > this.������z�i���j<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02079<BR>
     * <BR>
     * �T�j������<BR>
     * <BR>
     * �T�|�P�j<BR>
     * <BR>
     *    this.������ == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02080<BR>
     * <BR>
     * �T�|�Q�j<BR>
     * <BR>
     *    this.������ < 0<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02081<BR>
     * <BR>
     * �T�|�R�jthis.�������ɏ����_���܂܂��ꍇ<BR>
     * �ithis.������.indexOf('.') >= 0 �̏ꍇ�j<BR>
     * <BR>
     *    this.������.split('.')[0].length() > 3 or<BR>
     *    this.������.split('.')[1].length() > 5<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02082<BR>
     * <BR>
     * �T�|�S�jthis.�������ɏ����_���܂܂�Ȃ��ꍇ<BR>
     * �ithis.������.indexOf('.') = -1 �̏ꍇ�j<BR>
     * <BR>
     *    this.������.length() > 3<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02082<BR>
     * <BR>
     * �U�j�t�����z<BR>
     * <BR>
     * �U�|�P�j<BR>
     * <BR>
     *    this.�t�����z == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02083<BR>
     * <BR>
     * �U�|�Q�j<BR>
     * <BR>
     *    this.�t�����z < 0<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02084<BR>
     * <BR>
     * �U�|�R�jthis.�t�����z�ɏ����_���܂܂��ꍇ<BR>
     * �ithis.�t�����z.indexOf('.') >= 0 �̏ꍇ�j<BR>
     * <BR>
     *    this.�t�����z.split('.')[0].length() > 9 or<BR>
     *    this.�t�����z.split('.')[1].length() > 5<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02085<BR>
     * <BR>
     * �U�|�S�jthis.�t�����z�ɏ����_���܂܂�Ȃ��ꍇ<BR>
     * �ithis.�t�����z.indexOf('.') = -1 �̏ꍇ�j<BR>
     * <BR>
     *    this.�t�����z.length() > 9<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02085<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B0E859024B
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�K�p���ԁi���j 
        //this.�K�p���ԁi���j == null �̏ꍇ�A��O���X���[����B 
        if (this.applyStartDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02073,
                this.getClass().getName() + STR_METHOD_NAME,
                " �K�p���ԁi���j�������͂ł��B"); 
        }
        
        //�Q�j�K�p���ԁi���j 
        // this.�K�p���ԁi���j != null and this.�K�p���ԁi���j > this.�K�p���ԁi���j 
        //�̏ꍇ�A��O���X���[����B 
        if (this.applyEndDate != null)
        {
            int l_intFlag = WEB3DateUtility.compareToDay(this.applyStartDate, this.applyEndDate);
            if (l_intFlag > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01446,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �K�p����From/To�������G���[:" + 
                    this.applyStartDate + "(�K�p���ԁi���j), " + 
                    this.applyEndDate + "(�K�p���ԁi���j) "); 
            }
        }

        //�R�j������z�i���j 
        //�R�|�P�j this.������z�i���j == null �̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.tradingAmtFrom))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02074,
                this.getClass().getName() + STR_METHOD_NAME,
                " ������z�i���j�������͂ł��B"); 
        }
        
        double l_dblTradingAmtFrom = Double.parseDouble(this.tradingAmtFrom);
        //�R�|�Q�j this.������z�i���j < 0 �̏ꍇ�A��O���X���[����B 
        if (l_dblTradingAmtFrom < 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02075,
                this.getClass().getName() + STR_METHOD_NAME,
                " ������z�i���j���}�C�i�X�̒l�ł�:" + this.tradingAmtFrom); 
        }
        
        //�R�|�R�jthis.������z�i���j�ɏ����_���܂܂��ꍇ
        //�ithis.������z�i���j.indexOf('.') >= 0 �̏ꍇ�j
        if (this.tradingAmtFrom.indexOf('.') >= 0)            
        {
            // this.������z�i���j.split('.')[0].length() > 11 or 
            //this.������z�i���j.split('.')[1].length() > 2 
            //�̏ꍇ�A��O���X���[����B 
            String[] l_strs = this.tradingAmtFrom.trim().split("\\.");
            if (l_strs[0].length() > 11 || l_strs[1].length() > 2)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02076,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ������z�i���j�̒l���s���ł��B:" + this.tradingAmtFrom); 
            }
        }
        
        //�R�|�S�jthis.������z�i���j�ɏ����_���܂܂�Ȃ��ꍇ�ithis.������z�i���j.indexOf('.') = -1 �̏ꍇ�j         
        if (this.tradingAmtFrom.indexOf('.') == -1)
        {
            //this.������z�i���j.length() > 11 �̏ꍇ�A��O���X���[����B 
            if (WEB3StringTypeUtility.getNubmerLength(this.tradingAmtFrom) > 11)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02076,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ������z�i���j�̒l���s���ł��B:" + this.tradingAmtFrom); 
            }
        }
        
        //�S�j������z�i���j 
        //this.������z�i���j != null �̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (!WEB3StringTypeUtility.isEmpty(this.tradingAmtTo))
        {            
            double l_dblTradingAmtTo = Double.parseDouble(this.tradingAmtTo);
            //�S�|�P�j 
            //this.������z�i���j < 0 �̏ꍇ�A��O���X���[����B 
            if (l_dblTradingAmtTo < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02077,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ������z�i���j���}�C�i�X�̒l�ł��B:" + this.tradingAmtTo);
            }

            //�S�|�Q�jthis.������z�i���j�ɏ����_���܂܂��ꍇ�ithis.������z�i���j.indexOf('.') >= 0 �̏ꍇ�j 
            if (this.tradingAmtTo.indexOf('.') >= 0)
            {
                String[] l_strs = this.tradingAmtTo.trim().split("\\.");
                //this.������z�i���j.split('.')[0].length() > 11 or 
                //this.������z�i���j.split('.')[1].length() > 2 
                //�̏ꍇ�A��O���X���[����B
                if (l_strs[0].length() > 11 || l_strs[1].length() > 2)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02078,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ������z�i���j�̒l���s���ł�:" + this.tradingAmtTo);
                }
            }
 

            //�S�|�R�jthis.������z�i���j�ɏ����_���܂܂�Ȃ��ꍇ�ithis.������z�i���j.indexOf('.') = -1 �̏ꍇ�j 
            //this.������z�i���j.length() > 11 �̏ꍇ�A��O���X���[����B 
            if (this.tradingAmtTo.indexOf('.') == -1)
            {
                //this.������z�i���j.length() > 11 �̏ꍇ�A��O���X���[����B 
                if (WEB3StringTypeUtility.getNubmerLength(this.tradingAmtTo) > 11)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02078,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ������z�i���j�̒l���s���ł�:" + this.tradingAmtTo); 
                }
            }
            
            //�S�|�S�jthis.������z�i���j > this.������z�i���j�̏ꍇ�A��O���X���[����B 
            if (l_dblTradingAmtFrom > l_dblTradingAmtTo)
            {                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02079,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ������z�i���j��������z�i���j�𒴂��܂���:" + 
                    this.tradingAmtFrom + " > " + this.tradingAmtTo); 
            }

        }
         
        //�T�j������ 
        //�T�|�P�jthis.������ == null�̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.collectRate))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02080,
                this.getClass().getName() + STR_METHOD_NAME,
                " �������������͂ł��B"); 
        }        
        
        //�T�|�Q�jthis.������ < 0 �̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isMinus(this.collectRate))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02081,
                this.getClass().getName() + STR_METHOD_NAME,
                " ���������}�C�i�X�̒l�ł�:" + this.collectRate); 
        }
        //�T�|�R�jthis.�������ɏ����_���܂܂��ꍇ�ithis.������.indexOf('.') >= 0 �̏ꍇ�j 
        //this.������.split('.')[0].length() > 3 or this.������.split('.')[1].length() > 5 
        //�̏ꍇ�A��O���X���[����B 
        if (this.collectRate.indexOf('.') >= 0)
        {
            String[] l_strs = this.collectRate.trim().split("\\.");

            if (l_strs[0].length() > 3 || l_strs[1].length() > 5)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �������̒l���s���ł�:" + this.collectRate);
            }
        }

        //�T�|�S�jthis.�������ɏ����_���܂܂�Ȃ��ꍇ�ithis.������.indexOf('.') = -1 �̏ꍇ�j 
        //this.������.length() > 3 /�̏ꍇ�A��O���X���[����B
        if (this.collectRate.indexOf('.') == -1)
        { 
            if (WEB3StringTypeUtility.getNubmerLength(this.collectRate) > 3)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �������̒l���s���ł�:" + this.collectRate); 
            }
        } 

        //�U�j�t�����z 
        //�U�|�P�jthis.�t�����z == null �̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.additionalAmt))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02083,
                this.getClass().getName() + STR_METHOD_NAME,
                " �t�����z�������͂ł��B"); 
        } 
        
        //�U�|�Q�jthis.�t�����z < 0 �̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isMinus(this.additionalAmt))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02084,
                this.getClass().getName() + STR_METHOD_NAME,
                " �t�����z���}�C�i�X�̒l�ł�:" + this.additionalAmt); 
        }
        
        //�U�|�R�jthis.�t�����z�ɏ����_���܂܂��ꍇ�ithis.�t�����z.indexOf('.') >= 0 �̏ꍇ�j         
        //this.�t�����z.split('.')[0].length() > 9 orthis.�t�����z.split('.')[1].length() > 5 
        //�̏ꍇ�A��O���X���[����B
        if (this.additionalAmt.indexOf('.') >= 0)
        {
            String[] l_strs = this.additionalAmt.trim().split("\\.");

            if (l_strs[0].length() > 9 || l_strs[1].length() > 5)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02085,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �t�����z�̒l���s���ł�:" + this.additionalAmt);
            }
        }
         
        //�U�|�S�jthis.�t�����z�ɏ����_���܂܂�Ȃ��ꍇ�ithis.�t�����z.indexOf('.') = -1 �̏ꍇ�j 
        //this.�t�����z.length() > 9 �̏ꍇ�A��O���X���[����B
        if (this.additionalAmt.indexOf('.') == -1)
        {
            if (WEB3StringTypeUtility.getNubmerLength(this.additionalAmt) > 9)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02085,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �t�����z�̒l���s���ł�:" + this.additionalAmt);
            }
        } 

        log.exiting(STR_METHOD_NAME);       
    }
}
@
