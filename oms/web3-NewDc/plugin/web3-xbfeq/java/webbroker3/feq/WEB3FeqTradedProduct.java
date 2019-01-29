head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqTradedProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������(WEB3FeqTradedProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  䈋�(���u) �V�K�쐬
Revesion History : 2007/11/19 �g�C��(���u) ���f��No.362
*/
package webbroker3.feq;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqTradedProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TradeStopDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�O�������������) <BR>
 * �O�������������
 *
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3FeqTradedProduct extends FeqTradedProductImpl
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqTradedProduct.class);

     FeqTradedProductRow l_feqTradedProductRow =
        (FeqTradedProductRow)this.getDataSourceObject();

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FeqTradedProduct(TradedProductRow l_trow)
        throws DataFindException
    {
        super(l_trow);
    }

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FeqTradedProduct(FeqTradedProductRow l_row)
        throws DataFindException
    {
        super(l_row);
    }

    /**
     * (is�����ԓ�) <BR>
     * �iisOpen�j <BR>
     *  <BR>
     * �����ԓ����𔻒肷��B <BR>
     *  <BR>
     * �����ԓ��ł����true��ԋp����B�ȊO�Afalse��ԋp����B <BR>
     *  <BR>
     * [�����ԓ��̏���] <BR>
     * this.�O���������Params.���i�o�^�j�� <= �@@<BR>
     * �@@�@@�@@*������ < this.�O���������Params.���(�o�^�j�p�~�� <BR>
     *  <BR>
     * �������F�@@������ԊǗ�.get������()�̖߂�l�B <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 428051EB02A8
     */
    public boolean isOpen() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOpen() ";
        log.entering(STR_METHOD_NAME);

        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        if ((WEB3DateUtility.compare(l_feqTradedProductRow.getListedDate(), l_datBizDate) <= 0)
            && (WEB3DateUtility.compare(l_datBizDate, l_feqTradedProductRow.getUnlistedDate()) < 0))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }


    }

    //�C����
    public static boolean isOpen( Date l_datBizDate,FeqTradedProductRow l_feqTradedProductRow ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOpen() ";
        log.entering(STR_METHOD_NAME);

        if ((WEB3DateUtility.compare(l_feqTradedProductRow.getListedDate(), l_datBizDate) <= 0)
            && (WEB3DateUtility.compare(l_datBizDate, l_feqTradedProductRow.getUnlistedDate()) < 0))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    public static boolean isBuyStop(FeqTradedProductRow l_feqTradedProductRow)
    {
        final String STR_METHOD_NAME = "isBuyStop() ";
        log.entering(STR_METHOD_NAME);
         // �@@�|this.�O���������Params.������~ == 1:��~��(������K��)�C
         // �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B
         //

        if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop()))
            || WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

         // �@@�|this.�O���������Params.���t��~ == 1:��~��(������K��)�C
         // �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B
         //
        else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getBuyStop()))
            || WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getBuyStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
           // �@@�|�ȊO�Afalse��ԋp����B
           log.exiting(STR_METHOD_NAME);
           return false;
        }
    }

    public static boolean isSellStop(FeqTradedProductRow l_feqTradedProductRow)
    {
        final String STR_METHOD_NAME = "isSellStop() ";
        log.entering(STR_METHOD_NAME);
        // �@@�|this.�O���������Params.������~ == 1:��~��(������K��)�C
        // �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B
        //
        if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop()))
            || WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        // �@@�|this.�O���������Params.���t��~ == 1:��~��(������K��)�C
        // �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B
        //
        else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getSellStop()))
            || WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getSellStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
           // �@@�ȊO�Afalse��ԋp����B
           log.exiting(STR_METHOD_NAME);
           return false;
        }

    }
    /**
     * (is����K��) <BR>
     * �iisTradingSuspended�j <BR>
     *  <BR>
     * ����K�������𔻒肷��Btrue�̏ꍇ�A����K�����C <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false�̏ꍇ����\�B <BR>
     *  <BR>
     * ���t�̏ꍇ�iis���t == true�j�Ais����~()�̖߂�l��ԋp����B <BR>
     * ���t�̏ꍇ�iis���t == false�j�Ais����~()�̖߂�l��ԋp����B <BR>
     * @@param l_blnIsBuy - (is���t) <BR>
     * ���t���̔��� <BR>
     *  <BR>
     * ���t�F�@@true <BR>
     * ���t�F�@@false <BR>
     *
     * @@return boolean
     * @@roseuid 42B11DCC0191
     */
    public boolean isTradingSuspended(boolean l_blnIsBuy)
    {
        final String STR_METHOD_NAME = "isTradingSuspended(boolean l_blnIsBuy)";
        log.entering(STR_METHOD_NAME);
        if (l_blnIsBuy)
        {
            log.exiting(STR_METHOD_NAME);
            return this.isBuyStop();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return this.isSellStop();
        }
    }

    /**
     * (is����~) <BR>
     * ���t��~���𔻒肷��Btrue�̏ꍇ�A����~���Cfalse�̏ꍇ����\�B <BR>
     *  <BR>
     * �@@�|this.�O���������Params.������~ == 1:��~��(������K��)�C <BR>
     * �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B <BR>
     *  <BR>
     * �@@�|this.�O���������Params.���t��~ == 1:��~��(������K��)�C <BR>
     * �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B <BR>
     *  <BR>
     * �@@�|�ȊO�Afalse��ԋp����B <BR>
     * @@return boolean
     * @@roseuid 42B11F520087
     */
    public boolean isBuyStop()
    {
        final String STR_METHOD_NAME = "isBuyStop() ";
        log.entering(STR_METHOD_NAME);
         // �@@�|this.�O���������Params.������~ == 1:��~��(������K��)�C
         // �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B
         //

        if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop()))
            || WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

         // �@@�|this.�O���������Params.���t��~ == 1:��~��(������K��)�C
         // �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B
         //
        else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getBuyStop()))
            || WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getBuyStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
           // �@@�|�ȊO�Afalse��ԋp����B
           log.exiting(STR_METHOD_NAME);
           return false;
        }
    }

    /**
     * (is����~) <BR>
     * ���t��~���𔻒肷��Btrue�̏ꍇ�A����~���Cfalse�̏ꍇ����\�B <BR>
     *  <BR>
     * �@@�|this.�O���������Params.������~ == 1:��~��(������K��)�C <BR>
     * �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B  <BR>
     *  <BR>
     * �@@�|this.�O���������Params.���t��~ == 1:��~��(������K��)�C <BR>
     * �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B <BR>
     *  <BR>
     * �@@�|�ȊO�Afalse��ԋp����B <BR>
     * @@return boolean
     * @@roseuid 42B11EF90133
     */
    public boolean isSellStop()
    {
        final String STR_METHOD_NAME = "isSellStop() ";
        log.entering(STR_METHOD_NAME);
        // �@@�|this.�O���������Params.������~ == 1:��~��(������K��)�C
        // �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B
        //
        if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop()))
            || WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        // �@@�|this.�O���������Params.���t��~ == 1:��~��(������K��)�C
        // �@@�@@�܂��� 2:��~���i���ЋK���j�̏ꍇ�Atrue��ԋp����B
        //
        else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getSellStop()))
            || WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getSellStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
           // �@@�ȊO�Afalse��ԋp����B
           log.exiting(STR_METHOD_NAME);
           return false;
        }

    }

    /**
     * (get�I�l) <BR>
     * �I�l���擾����B <BR>
     *  <BR>
     * this.�O���������Params.��l�i�I�l�j��ԋp����B <BR>
     * @@return double
     * @@roseuid 4280755B017F
     */
    public double getLastClosingPrice()
    {
        return l_feqTradedProductRow.getLastClosingPrice();
    }

    /**
     * (get���t�P��) <BR>
     * �igetBuyOrderLotSize�j <BR>
     * this.�O���������Params.���t�P�ʂ�ԋp����B <BR>
     * @@return double
     * @@roseuid 42808EDD0076
     */
    public double getBuyOrderLotSize()
    {
        return l_feqTradedProductRow.getBuyLotSize();
    }

    /**
     * (get���t�P��) <BR>
     * �igetSellOrderLotSize�j <BR>
     * this.�O���������Params.���t�P�ʂ�ԋp����B <BR>
     * @@return double
     * @@roseuid 42808F2D0289
     */
    public double getSellOrderLotSize()
    {
        return l_feqTradedProductRow.getSellLotSize();
    }

    /**
     * (get��n��) <BR>
     * �igetDailyDeliveryDate�̃I�[�o�[���C�h�j <BR>
     *  <BR>
     * �i�{��������̗L����==�����j�ł��钍���̎�n���i�����j��ԋp����B <BR>
     *  <BR>
     * �P�j�@@���������擾 <BR>
     * �@@this.getProduct()�ɂāA�O�������������擾����B <BR>
     * �@@�O����������.get��������()�ɂČ����������擾����B <BR>
     *  <BR>
     * �Q�j�@@�����J�����_�łR�c�Ɠ���擾 <BR>
     * �@@�Q�|�P�j�@@������ԊǗ�.get������()�ɂĔ��������擾����B <BR>
     *  <BR>
     * �@@�Q�|�Q�j <BR>
     * �@@�@@�c�Ɠ��v�Z.calc�c�Ɠ�()�ɂāA�������̂R�c�Ɠ�����擾����B <BR>
     * �@@�@@�������̂R�c�Ɠ��オ���������̏ꍇ�i�������� == �������̂R�c�Ɠ���j�A<BR>
     * �@@�@@�������̂S�c�Ɠ������n���Ƃ���B <BR>
     * �@@�@@�ȊO�A�������̂R�c�Ɠ������n���Ƃ���B <BR>
     * �@@ <BR>
     * �R�j�@@��n���ԋp <BR>
     * �@@��n����ԋp����B <BR>
     * @@return Date
     * @@roseuid 4282B7140245
     */
    public Date getDailyDeliveryDate()
    {
        final String STR_METHOD_NAME = "getDailyDeliveryDate()";
        log.entering(STR_METHOD_NAME);
        // �P�j�@@���������擾
        // �@@this.getProduct()�ɂāA�O�������������擾����B
        // �@@�O����������.get��������()�ɂČ����������擾����B
        //
        WEB3FeqProduct l_product = (WEB3FeqProduct)this.getProduct();
        Date l_datExRightDate = l_product.getExRightDate();
        String l_strInstitutionCode = l_product.getInstitution().getInstitutionCode();
        String l_strMarketCode = l_product.getMarketCode();
        // �Q�j�@@�����J�����_�łR�c�Ɠ���擾
        // �@@�Q�|�P�j�@@������ԊǗ�.get������()�ɂĔ��������擾����B
        //
        Date l_datBizDate = null;
        Timestamp l_timeThreeBizDate = null;
        WEB3GentradeBizDate l_genBizDate = null;
        try
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            // �@@�Q�|�Q�j
            // �@@�@@�c�Ɠ��v�Z.calc�c�Ɠ�()�ɂāA�������̂R�c�Ɠ�����擾����B
            // �@@�@@�������̂R�c�Ɠ��オ���������̏ꍇ�i�������� == �������̂R�c�Ɠ���j�A
            // �@@�@@�������̂S�c�Ɠ������n���Ƃ���B
            // �@@�@@�ȊO�A�������̂R�c�Ɠ������n���Ƃ���B
            //

            l_genBizDate = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
            l_timeThreeBizDate = l_genBizDate.roll(3);
            if (WEB3DateUtility.compareToDay(l_datExRightDate, l_timeThreeBizDate)== 0)
            {
                // �R�j�@@��n���ԋp
                // �@@��n����ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_genBizDate.roll(4);
            }
            else
            {
                // �R�j�@@��n���ԋp
                // �@@��n����ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_timeThreeBizDate;
            }
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                      l_ex.getErrorInfo(),
                      this.getClass().getName() + "." + STR_METHOD_NAME,
                      l_ex.getMessage(), l_ex);
        }
    }
}
@
