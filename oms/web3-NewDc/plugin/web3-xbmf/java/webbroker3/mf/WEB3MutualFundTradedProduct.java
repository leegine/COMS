head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundTradedProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�����M��������N���X(WEB3MutualFundTradedProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 ������ (���u) �V�K�쐬
                   2004/12/06 ������ (���u) �c�Ή�
*/
package webbroker3.mf;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundTradedProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3BuyPossibleDivDef;
import webbroker3.common.define.WEB3SellPossibleDivDef;
import webbroker3.common.define.WEB3SwtPossibleDivDef;
import webbroker3.mf.define.WEB3MFRecruitPossibleDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �g�����M��������N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualFundTradedProduct extends MutualFundTradedProductImpl
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundTradedProduct.class);

    /**
     * (�g�����M�������)<BR>
     * �R���X�g���N�^�B<BR>
     * @@param l_tradedProductRow - �������Row
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 40AD8F42006D
     */
    public WEB3MutualFundTradedProduct(TradedProductRow l_tradedProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_tradedProductRow);
    }

    /**
     * (�g�����M�������)<BR>
     * �R���X�g���N�^�B<BR>
     * @@param l_mutualFundTradedProductRow - ���M�������Row
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 40AD8F42004E
     */
    public WEB3MutualFundTradedProduct(MutualFundTradedProductRow l_mutualFundTradedProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_mutualFundTradedProductRow);
    }

    /**
     * (get����)<BR>
     * �������ɑ΂��������Ԃ��B<BR>
     * <BR>
     * �P�j�@@����J�����_�[���擾����<BR>
     * �@@GtlUtils.getFinObjectManager().getTradingCalendar()���R�[�����āA<BR>����J�����_�[���擾����B<BR>
     * �@@�mgetTradingCalendar�ɓn���p�����^�n<BR>
     * �@@�@@�������ID�F this.getTradedProductId()�̖߂�l<BR>
     * <BR>
     * �Q�j�@@���������擾���� <BR>
     * �@@���M������ԊǗ�.get���M������()���R�[�����Ĕ��������擾����B<BR>
     * <BR>
     * �R�j�@@�����ړ������̎擾<BR>
     * �@@this.get�����ړ�����()���R�[�����āA�����ړ��������擾����B<BR>
     * <BR>
     * �S�j�@@�������擾����<BR>
     * �@@����J�����_�[.roll()���R�[�����āA�������擾����B<BR>
     * �@@�mroll�ɓn���p�����^�n<BR>
     * �@@�@@�������F �擾����������<BR>
     * �@@�@@�ړ������F �擾���������ړ�����<BR>
     * <BR>
     * �T�j�@@�擾����������Ԃ��B<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD623D006C
     */
    public Date getExecutedDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutedDate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����J�����_�[���擾����
        TradingCalendar l_tradingCalendar =
            GtlUtils.getFinObjectManager().getTradingCalendar(
                this.getTradedProductId());

        //�Q�j�@@���������擾����
        Date l_datCurrentBizDate = 
            WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();

        //�R�j�@@�����ړ������̎擾
        int intDateShiftDays = this.getExecDateShiftDays();

        //�S�j�@@�������擾����
        Date l_datExecutedDate =
            l_tradingCalendar.roll(l_datCurrentBizDate, intDateShiftDays);

        //�T�j�@@�擾����������Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_datExecutedDate;
    }

    /**
     * (get���ϊ���)<BR>
     * �igetDeliveryDateShiftDays�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * ���ϊ��Ԃ�Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����iis���t�j�� true �̏ꍇ��this.get���ϊ��ԁi���t�j()<BR>
     * �̖߂�l��Ԃ��B<BR>
     * <BR>
     * �Q�j�@@�����łȂ��ꍇ��this.get���ϊ��ԁi���j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@param l_blnIsAcquired - is���t�B<BR>
     * <BR>
     * ���t�̏ꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B<BR>
     * 
     * @@return int
     * @@roseuid 40AD62A203D7
     */
    public int getOutstandingTerm(boolean l_blnIsAcquired)
    {
        final String STR_METHOD_NAME = "getOutstandingTerm(boolean l_blnIsAcquired)";
        log.entering(STR_METHOD_NAME);   
        int l_intOutstandingTerm = 0;
        if (l_blnIsAcquired)
        {
            //�P�j�@@�����iis���t�j�� true �̏ꍇ
            l_intOutstandingTerm = this.getOutstandingTermAcquired();
        }
        else
        {
            //�Q�j�@@�����łȂ��ꍇ
            l_intOutstandingTerm = this.getOutstandingTermSell();
        }
        log.exiting(STR_METHOD_NAME);
        return l_intOutstandingTerm;
    }

    /**
     * (get��n��)<BR>
     * �igetDailyDeliveryDate�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �����ɑ΂����n����Ԃ��B<BR>
     * <BR>
     * �P�j�@@����J�����_�[���擾����<BR>
     * �@@GtlUtils.getFinObjectManager().getTradingCalendar()���R�[�����āA<BR>����J�����_�[���擾����B<BR>
     * �@@�mgetTradingCalendar�ɓn���p�����^�n<BR>
     * �@@�@@�������ID�F this.getTradedProductId()�̖߂�l<BR>
     * <BR>
     * �Q�j�@@�������擾����<BR>
     * �@@this.get����()���R�[�����Ė������擾����B<BR>
     * <BR>
     * �R�j�@@���ϊ��Ԃ̎擾<BR>
     * �@@this.get���ϊ���()���R�[�����āA���ϊ��Ԃ��擾����B<BR>
     * �@@�mget���ϊ��Ԃɓn���p�����^�n<BR>
     * �@@�@@is���t�F ����.is���t<BR>
     * <BR>
     * �S�j�@@��n�����擾����<BR>
     * �@@����J�����_�[.roll()���R�[�����āA��n�����擾����B<BR>
     * �@@�mroll�ɓn���p�����^�n<BR>
     * �@@�@@����F �擾��������<BR>
     * �@@�@@�ړ������F �擾�������ϊ��� - 1<BR>
     * <BR>
     * �T�j�@@�擾������n����Ԃ��B<BR>
     * <BR>
     * @@param l_blnIsAcquired - is���t�B<BR>
     * <BR>
     * ���t�̏ꍇ�� true ���A�����łȂ��ꍇ�� false ���w�肷��B<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD62A3000E
     */
    public Date getDeliveryDate(boolean l_blnIsAcquired) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDeliveryDate(boolean l_blnIsAcquired)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����J�����_�[���擾����
        TradingCalendar l_tradingCalendar =
            GtlUtils.getFinObjectManager().getTradingCalendar(
                this.getTradedProductId());

        //�Q�j�@@�������擾����
        Date l_datExecutedDate = this.getExecutedDate();

        //�R�j�@@���ϊ��Ԃ̎擾
        int intOutstandingTerm = this.getOutstandingTerm(l_blnIsAcquired);

        //�S�j�@@��n�����擾����
        Date l_datDeliveryDate =
            l_tradingCalendar.roll(l_datExecutedDate, intOutstandingTerm - 1);

        //�T�j�@@�擾������n����Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_datDeliveryDate;
    }

    /**
     * (get���ϊ��ԁi���t�j)<BR>
     * ���ϊ��ԁi���t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���ϊ��ԁi���t�j()��Ԃ��B<BR>
     * @@return int
     * @@roseuid 40AD62BF00D9
     */
    public int getOutstandingTermAcquired()
    {
        final String STR_METHOD_NAME = "getOutstandingTermAcquired()";
        log.entering(STR_METHOD_NAME); 
        int l_intOutstandingTermAcquired =
            ((MutualFundTradedProductRow) this.getDataSourceObject()).getBuyUndeliveringTerm();
        log.exiting(STR_METHOD_NAME);        
        return l_intOutstandingTermAcquired;
    }

    /**
     * (get���ϊ��ԁi���j)<BR>
     * ���ϊ��ԁi���j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���ϊ��ԁi���j()��Ԃ��B<BR>
     * @@return int
     * @@roseuid 40AD62BF00E9
     */
    public int getOutstandingTermSell()
    {
        final String STR_METHOD_NAME = "getOutstandingTermSell()";
        log.entering(STR_METHOD_NAME); 
        int l_intOutstandingTermSell =
            ((MutualFundTradedProductRow) this.getDataSourceObject()).getSellUndeliveringTerm();
        log.exiting(STR_METHOD_NAME);
        return l_intOutstandingTermSell;
    }

    /**
     * (get�����ړ�����)<BR>
     * �����ړ�������Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�����ړ�����()��Ԃ��B<BR>
     * @@return int
     * @@roseuid 40AD7BD9030D
     */
    public int getExecDateShiftDays()
    {
        final String STR_METHOD_NAME = "getExecDateShiftDays()";
        log.entering(STR_METHOD_NAME);        
        int l_intExecDateShiftDays =
            ((MutualFundTradedProductRow) this.getDataSourceObject()).getExecDateShiftdays();
        log.exiting(STR_METHOD_NAME);
        return l_intExecDateShiftDays;
    }

    /**
     * (is���t�\)<BR>
     * ���t�\�ȏꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���t�\�敪()�̖߂�l���h0�F���t�s�h<BR>
     * �̏ꍇ�� false ���A�����łȂ��ꍇ�� true ��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40AD940201D4
     */
    public boolean isAcquiredPossible()
    {
        final String STR_METHOD_NAME = "isAcquiredPossible()";
        log.entering(STR_METHOD_NAME); 
        if (WEB3BuyPossibleDivDef.NOT_ACQUIRED.equals(
                ((MutualFundTradedProductRow) this.getDataSourceObject()).getBuyPossibleDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (is���\)<BR>
     * ��񐿋��\�ȏꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���\�敪()�̖߂�l��<BR>
     * �h0�F���s�h�̏ꍇ�� false ���A�����łȂ��ꍇ�� true ��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40AD944E01C5
     */
    public boolean isSellPossible()
    {
        final String STR_METHOD_NAME = "isSellPossible()";
        log.entering(STR_METHOD_NAME); 
        if (WEB3SellPossibleDivDef.NOT_SELL.equals(
                ((MutualFundTradedProductRow) this.getDataSourceObject()).getSellPossibleDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (is�抷�\)<BR>
     * �抷�\�ȏꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�抷�\�敪()�̖߂�l��<BR>
     * �h0�F�抷�s�h�̏ꍇ�� false ���A�����łȂ��ꍇ�� true ��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40AD946A03C8
     */
    public boolean isSwitchingPossible()
    {
        final String STR_METHOD_NAME = "isSwitchingPossible()";
        log.entering(STR_METHOD_NAME); 
        if (WEB3SwtPossibleDivDef.NOT_SWITCHING.equals(
                ((MutualFundTradedProductRow) this.getDataSourceObject()).getSwtPossibleDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }
    
    /**
     * (is��W�\)<BR>
     * ��W�\�ȏꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get��W�\�敪()�̖߂�l���h0�F��W�s�h<BR>
     * �̏ꍇ�� false ���A�����łȂ��ꍇ�� true ��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40AD946A03C8
     */
    public boolean  isRecruitPossible()
    {
        final String STR_METHOD_NAME = " isRecruitPossible()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get��W�\�敪()�̖߂�l���h0�F��W�s�h
        //�̏ꍇ�� false ���A�����łȂ��ꍇ�� true ��Ԃ�
        String l_strRecruitPossibleDiv = 
            ((MutualFundTradedProductRow) this.getDataSourceObject()).getRecruitPossibleDiv();
        if (WEB3MFRecruitPossibleDivDef.NOT_RECRUIT.equals(l_strRecruitPossibleDiv))
        {
            log.exiting(STR_METHOD_NAME); 
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get����)<BR>
     * ����.�������ɑ΂��������Ԃ��B<BR>
     * <BR>
     * �P�j�@@����J�����_�[���擾����<BR>
     * �@@GtlUtils.getFinObjectManager().getTradingCalendar()���R�[�����āA<BR>����J�����_�[���擾����B<BR>
     * �@@�mgetTradingCalendar�ɓn���p�����^�n<BR>
     * �@@�@@�������ID�F this.getTradedProductId()�̖߂�l<BR>
     * <BR>
     * �Q�j�@@�����ړ������̎擾<BR>
     * �@@this.get�����ړ�����()���R�[�����āA�����ړ��������擾����B<BR>
     * <BR>
     * �R�j�@@�������擾����<BR>
     * �@@����J�����_�[.roll()���R�[�����āA�������擾����B<BR>
     * �@@�mroll�ɓn���p�����^�n<BR>
     * �@@�@@�������F ����.������<BR>
     * �@@�@@�ړ������F �擾���������ړ�����<BR>
     * <BR>
     * �S�j�@@�擾����������Ԃ��B<BR>
     * <BR>
     * @@param Date l_datCurrentBizDate -������
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getExecutedDate(Date l_datCurrentBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutedDate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����J�����_�[���擾����
        TradingCalendar l_tradingCalendar =
            GtlUtils.getFinObjectManager().getTradingCalendar(
                this.getTradedProductId());

        //�Q�j�@@�����ړ������̎擾
        int intDateShiftDays = this.getExecDateShiftDays();

        //�R�j�@@�������擾����
        Date l_datExecutedDate =
            l_tradingCalendar.roll(l_datCurrentBizDate, intDateShiftDays);

        //�S�j�@@�擾����������Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_datExecutedDate;
    }
    
    /**
     * (get��n��)<BR>
     * �igetDailyDeliveryDate�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * ����.�����ɑ΂����n����Ԃ��B<BR>
     * <BR>
     * �P�j�@@����J�����_�[���擾����<BR>
     * �@@GtlUtils.getFinObjectManager().getTradingCalendar()���R�[�����āA<BR>����J�����_�[���擾����B<BR>
     * �@@�mgetTradingCalendar�ɓn���p�����^�n<BR>
     * �@@�@@�������ID�F this.getTradedProductId()�̖߂�l<BR>
     * <BR>
     * �Q�j�@@���ϊ��Ԃ̎擾<BR>
     * �@@this.get���ϊ���()���R�[�����āA���ϊ��Ԃ��擾����B<BR>
     * �@@�mget���ϊ��Ԃɓn���p�����^�n<BR>
     * �@@�@@is���t�F ����.is���t<BR>
     * <BR>
     * �R�j�@@��n�����擾����<BR>
     * �@@����J�����_�[.roll()���R�[�����āA��n�����擾����B<BR>
     * �@@�mroll�ɓn���p�����^�n<BR>
     * �@@�@@����F ����.����<BR>
     * �@@�@@�ړ������F �擾�������ϊ��� - 1<BR>
     * <BR>
     * �S�j�@@�擾������n����Ԃ��B<BR>
     * <BR>
     * @@param l_blnIsAcquired - is���t�B<BR>
     * @@param l_datSwtExecutedDate - ���� <BR>
     * <BR>
     * ���t�̏ꍇ�� true ���A�����łȂ��ꍇ�� false ���w�肷��B<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD62A3000E
     */
    public Date getDeliveryDate(boolean l_blnIsAcquired, Date l_datSwtExecutedDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDeliveryDate(boolean l_blnIsAcquired)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����J�����_�[���擾����
        TradingCalendar l_tradingCalendar =
            GtlUtils.getFinObjectManager().getTradingCalendar(
                this.getTradedProductId());

        //�R�j�@@���ϊ��Ԃ̎擾
        int intOutstandingTerm = this.getOutstandingTerm(l_blnIsAcquired);

        //�S�j�@@��n�����擾����
        Date l_datDeliveryDate =
            l_tradingCalendar.roll(l_datSwtExecutedDate, intOutstandingTerm - 1);

        //�T�j�@@�擾������n����Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_datDeliveryDate;
    }
}
@
