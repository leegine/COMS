head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradedProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���ݓ���������N���X(WEB3RuitoTradedProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/8 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoTradedProductImpl;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductParams;

/**
 * �g���ݓ���������N���X�B<BR>
 */
public class WEB3RuitoTradedProduct extends RuitoTradedProductImpl
{
    /**
     * �R���X�g���N�^�B<BR>
     * @@param l_tradedProductRow - �������Row<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 406BEFFA0399
     */
    public WEB3RuitoTradedProduct(TradedProductRow l_tradedProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_tradedProductRow);
    }

    /**
     * �R���X�g���N�^�B<BR>
     * @@param l_ruitoTradeProductRow - �ݓ��������Row<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 406BEFFA0389
     */
    public WEB3RuitoTradedProduct(RuitoTradedProductRow l_ruitoTradeProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_ruitoTradeProductRow);
    }

    /**
     * �igetDeliveryDateShiftDays�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * ��n��������̉��c�Ɠ��ォ��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����iis���t�j�� true <BR>
     * �̏ꍇ��this.get��n���ړ������i���t�j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * �Q�j�@@�����łȂ��ꍇ��this.get��n���ړ������i���j()�̖߂�l��Ԃ��B<BR>
     * @@param isBuy - is���t�B<BR>
     * <BR>
     * ���t�̏ꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B<BR>
     * @@return int
     * @@roseuid 406BDFA9002E
     */
    protected int getDeliveryDateShiftDays(boolean isBuy)
    {
        if (isBuy)
        {
            return this.getDeliveryDateShiftDaysBuy();
        }
        else
        {
            return this.getDeliveryDateShiftsDaysSell();
        }
    }

    /**
     * �igetDailyDeliveryDate�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �L�����ɑ΂����n����Ԃ��B<BR>
     * <BR>
     * �P�j�@@����J�����_�[���擾����<BR>
     * �@@GtlUtils.getFinObjectManager().getTradingCalendar()���R�[�����āA<BR>
     * ����J�����_�[���擾����B<BR>
     * �@@�mgetTradingCalendar�ɓn���p�����^�n<BR>
     * �@@�@@�������ID�F this.getTradedProductId()�̖߂�l<BR>
     * <BR>
     * �Q�j�@@���������擾����<BR>
     * �@@����J�����_�[.getCurrentBizDate()���R�[�����Ĕ��������擾����B<BR>
     * <BR>
     * �R�j�@@��n���ړ������̎擾<BR>
     * �@@this.get��n���ړ�����()���R�[�����āA��n���ړ��������擾����B<BR>
     * �@@�mget��n���ړ������ɓn���p�����^�n<BR>
     * �@@�@@is���t�F ����.is���t<BR>
     * <BR>
     * �S�j�@@��n�����擾����<BR>
     * �@@����J�����_�[.roll()���R�[�����āA��n�����擾����B<BR>
     * �@@�mroll�ɓn���p�����^�n<BR>
     * �@@�@@�������F �擾����������<BR>
     * �@@�@@�ړ������F �擾������n���ړ�����<BR>
     * <BR>
     * �T�j�@@�擾������n����Ԃ��B<BR>
     * @@param isBuy - is���t�B<BR>
     * <BR>
     * ���t�̏ꍇ�� true ���A�����łȂ��ꍇ�� false ���w�肷��B<BR>
     * @@return Date
     * @@roseuid 406BE2910108
     */
    public Date getDailyDeliveryDate(boolean isBuy)
    {
        //1)����J�����_�[���擾����
        TradingCalendar l_tradeCalendar;
        l_tradeCalendar =
            GtlUtils.getFinObjectManager().getTradingCalendar(
            this.getTradedProductId());

        //2)���������擾����
        Date l_datCurrentBizDate;
            l_datCurrentBizDate = l_tradeCalendar.getCurrentBizDate();

        //3)��n���ړ������̎擾
        int l_intDeliveryShiftDays = this.getDeliveryDateShiftDays(isBuy);
        //4)��n�����擾����
        Date l_datDeliveryDate = l_tradeCalendar.roll(
            l_datCurrentBizDate, l_intDeliveryShiftDays);
        return l_datDeliveryDate;
    }

    /**
     * ���t��~��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���t��~()��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40763DF1008C
     */
    public String getBuyStop()
    {
        String l_strBuyStop =
            ((RuitoTradedProductParams) 
            this.getDataSourceObject()).getBuyStop().trim();
        return l_strBuyStop;
    }

    /**
     * ����~��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get����~()��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40763DFA02DE
     */
    public String getSellStop()
    {
        String l_strSellStop =
            ((RuitoTradedProductParams) 
            this.getDataSourceObject()).getSellStop().trim();
        return l_strSellStop;
    }

    /**
     * ��n���ړ������i���t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get��n�ړ������i���t�j()��Ԃ��B<BR>
     * @@return int
     * @@roseuid 40763E2D02FD
     */
    public int getDeliveryDateShiftDaysBuy()
    {
        int l_intDeliveryDateShiftDaysBuy =
            ((RuitoTradedProductParams) 
            this.getDataSourceObject()).getBuyDeliveryDateShiftDays();
        return l_intDeliveryDateShiftDaysBuy;
    }

    /**
     * ��n���ړ������i���j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get��n�ړ������i���j()��Ԃ��B<BR>
     * @@return int
     * @@roseuid 40763E430186
     */
    public int getDeliveryDateShiftsDaysSell()
    {
        int l_intDeliveryDateShiftDaysSell =
            ((RuitoTradedProductParams) 
            this.getDataSourceObject()).getSellDeliveryDateShiftDays();
        return l_intDeliveryDateShiftDaysSell;
    }
}
@
