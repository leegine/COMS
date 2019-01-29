head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����]�̓T�[�r�X�C���^�t�F�[�X(WEB3TPTradingPowerService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 nakazato(ACT) �V�K�쐬
                   2006/09/11 ���G�� (���u) ���f��No.005
                   2006/11/13 ����� (���u) ���f��No.073
Revesion History : 2007/07/12 ���� (���u) ���f��No.106�A���f��No.108
Revesion History : 2007/07/19 �Ј��� (���u) ���f��No.109
Revesion History : 2007/08/06 ���� (���u) ���f��No.123
Revesion History : 2007/09/20 �Ј��� (���u) ���f��No.169
Revesion History : 2007/10/22 ��іQ�i���u�j���f��No.210
Revesion History : 2008/01/18 �g�E�N�| (���u) ���f��No.247
Revesion History : 2009/12/11 �����F (���u) ���f��No.404 ���f��No.405
Revesion History : 2010/01/13 ���g�@@ (���u)���f��No.440
Revesion History : 2010/01/28 ���g�@@ (���u)���f��No.448
*/
package webbroker3.tradingpower;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailRow;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;

/**
 * (����]�̓T�[�r�X)
 * ����]�̓T�[�r�X�C���^�[�t�F�[�X
 */
public interface WEB3TPTradingPowerService extends Service
{

    /**
     * (validate����]��)<BR>
     * 
     * ����.������ʂɂ����Ďw�肳�ꂽ�����̎���]�̓`�F�b�N�����{����B<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@param l_orderSpecIntercepter - (�������e�C���^�Z�v�^)
     * @@param l_orderSpec - (�������e)
     * @@param l_orderTypeEnum - (�������)
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)
     * true�̎��A�]�͍Čv�Z���������{����
     * false�̎��A�]�͍Čv�Z���������{���Ȃ�
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerResult
     * @@roseuid 41786F070261
     */
    public WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        Object[] l_orderSpecIntercepter,
        Object[] l_orderSpec,
        OrderTypeEnum l_orderTypeEnum,
        boolean l_blnUpdateFlg)
        throws WEB3SystemLayerException;

    /**
     * (get�������t�\�z)<BR>
     * 
     * �����������t�\�z���擾����B<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@return double
     * @@roseuid 41786F070280
     */
    public double getEquityTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get�M�p�����\�z)<BR>
     * 
     * �M�p�����\�z���擾����B<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@return double
     * @@roseuid 41786F07029F
     */
    public double getActualReceiptTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get�M�p�V�K���\�z)<BR>
     * 
     * �M�p�V�K���\�z���擾����B<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@return double
     * @@roseuid 41786F0702CE
     */
    public double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get�I�v�V�����V�K�����\�z)<BR>
     * 
     * �I�v�V�����V�K���\�z���擾����B<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@param l_ifoProduct - (����)
     * @@return Double
     */
    public Double getOptionBuyTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        IfoProduct l_ifoProduct)
        throws WEB3SystemLayerException;

    /**
     * (get�敨�I�v�V�����V�K���\�z)<BR>
     * 
     * �敨�I�v�V�����V�K���\�z���擾����<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@param l_blnLongFlg - (is����)
     * true�F�����@@false�F����
     * @@param l_ifoProduct - (����)
     * �����w�莞�̂ݐݒ�B�ȊO�Anull�B
     * @@return Double
     * @@roseuid 41786F07030D
     */
    public Double getFuturesOptionTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        boolean l_blnLongFlg,
        IfoProduct l_ifoProduct)
        throws WEB3SystemLayerException;

    /**
     * (get���̑����i���t�\�z)<BR>
     * 
     * ���̑����i���t�\�z���擾����<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     * @@roseuid 41786F07032C
     */
    public double getOtherTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException;

    /**
     * (get���̑����i���t�\�z�`0�␳�����`)<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��
     * @@return double
     */
    public double getOtherTradingPowerForCheck(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException;

    /**
     * (get�o���\�z�`0�␳�L��`)<BR>
     * 
     * �o���\�z���擾����i0�␳�L��j<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     */
    public double getPaymentTradingPower(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException;

    /**
     * (get�o���\�z�`0�␳�����`)<BR>
     * 
     * �o���\�z���擾����i0�␳�Ȃ��j<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     */
    public double getPaymentTradingPowerForCheck(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException;
    
    /**
     * (get�����M�����t�\�z)<BR>
     * 
     * ���M���t�\�z���擾����<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_orderTypeEnum - (�������)
     * @@return double
     */
    public double getMutualFundBuyTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate,
        OrderTypeEnum l_orderTypeEnum)
        throws WEB3SystemLayerException;

    /**
     * (get�a����ւ̐U�֊z)<BR>
     * 
     * �a����ւ̐U�֊z���擾����<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@param l_dblNecessaryCash - (�����K�v����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     * @@roseuid 41786F07034B
     */
    public double getTransferAmountToEquitySubAcount(
        WEB3GentradeSubAccount l_subAccount,
        double l_dblNecessaryCash,
        Date l_datDeliveryDate)
        throws WEB3SystemLayerException;

    /**
     * (�]�͍Čv�Z)<BR>
     * 
     * �]�͍Čv�Z���������������{����<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@roseuid 41786F07037A
     */
    public void reCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get���Y�]�͏��<�����ڋq>)<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get���Y�]�͏��<�M�p�ڋq>)<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get���Y�]�͏��<�����ڋq>�`�����]���`)<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquityQuote(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get���Y�]�͏��<�M�p�ڋq>�`�����]���`)<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMarginQuote(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get���Y�]�͏��<�����ڋq>)<BR>
     * 
     * @@param l_lngCalcResultId - (�]�͌v�Z����ID)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(long l_lngCalcResultId)
        throws WEB3SystemLayerException;

    /**
     * (get���Y�]�͏��<�M�p�ڋq>)<BR>
     * 
     * @@param l_lngCalcResultId - (�]�͌v�Z����ID)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(long l_lngCalcResultId)
        throws WEB3SystemLayerException;

    /**
     * (get���Y�]�͏��<�����ڋq>)<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@param l_resultRow - (�]�͌v�Z����<�����ڋq>)
     * @@param l_resultDetailRow - (�]�͌v�Z���ʏڍ�<�����ڋq>)
     * @@return WEB3TPTradingPowerCalcEquity
     */
    public WEB3TPTradingPowerCalcEquity getTradingPowerCalcEquity(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultEquityRow l_resultRow,
        TpCalcResultEquityDetailRow l_resultDetailRow)
        throws WEB3SystemLayerException;

    /**
     * (get���Y�]�͏��<�M�p�ڋq>)<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@param l_resultRow - (�]�͌v�Z����<�M�p�ڋq>)
     * @@param l_resultDetailRow - (�]�͌v�Z���ʏڍ�<�M�p�ڋq>)
     * @@return WEB3TPTradingPowerCalcMargin
     */
    public WEB3TPTradingPowerCalcMargin getTradingPowerCalcMargin(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultMarginRow l_resultRow,
        TpCalcResultMarginDetailRow l_resultDetailRow)
        throws WEB3SystemLayerException;

    /**
     * (get��K�������ꗗ)<BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@return WEB3TPMarginSecurityInfo[]
     */
    public WEB3TPMarginSecurityInfo[] getMarginSecurityInfo(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get���̑����i���t�\�z�`�]�͌v�Z����ROW�w��`)
     * <BR>
     * �]�͌v�Z����Row�Ȃ�тɗ]�͌v�Z���ʏڍ�Row���w�肵��<BR>
     * ���̑����i���t�\�z���擾����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_tpCalcResultEquityRow - (�]�͌v�Z����<�����ڋq>Row)
     * @@param l_tpCalcResultEquityDetailRow - (�]�͌v�Z���ʏڍ�<�����ڋq>Row)
     * @@param l_tpCalcResultMarginRow - (�]�͌v�Z����<�M�p�ڋq>Row)
     * @@param l_tpCalcResultMarginDetailRow - (�]�͌v�Z���ʏڍ�<�M�p�ڋq>Row)l_subAccount - (�⏕����)
     * @@return double
     */
    public double getOtherTradingPower(
            WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate,
            TpCalcResultEquityRow l_tpCalcResultEquityRow,
            TpCalcResultEquityDetailRow l_tpCalcResultEquityDetailRow,
            TpCalcResultMarginRow l_tpCalcResultMarginRow,
            TpCalcResultMarginDetailRow l_tpCalcResultMarginDetailRow)
            throws WEB3SystemLayerException;
    
    /**
     * (get�������t�\�z�`�A�������`)<BR>
     * �A�������p�̊������t�\�z���v�Z���ԋp����B<BR>
     * <BR>
     * �u�ԋp�l >= �T�Z��n����v�ˎ��OK<BR>
     * �u�ԋp�l <  �T�Z��n����v�ˎ��NG<BR>
     * ���j�ԋp�l >= 0 �Ƃ���B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_estimatedPrice - (�����O�T�Z��n���)
     * @@return double
     */
    public double getSuccEquityTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Date l_datDeliveryDate,
            Double l_estimatedPrice) throws WEB3SystemLayerException;

    /**
     * (get�M�p�V�K���\�z�`�A�������`)<BR>
     * �A�������p�̐M�p�V�K���\�z���v�Z���ԋp����B<BR>
     * <BR>
     * �u�ԋp�l >= �T�Z��n����v�ˎ��OK<BR>
     * �u�ԋp�l <  �T�Z��n����v�ˎ��NG<BR>
     * ���j�ԋp�l >= 0 �Ƃ���B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_estimatedPrice - (�����O�T�Z��n���)
     * @@return double
     */
    public double getSuccMarginTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Double l_estimatedPrice) throws WEB3SystemLayerException;

    /**
     * (get�M�p�����\�z�`�A�������`)<BR>
     * �A�������p�̐M�p�����\�z���v�Z���ԋp����B<BR>
     * <BR>
     * �u�ԋp�l >= �T�Z��n����v�ˎ��OK<BR>
     * �u�ԋp�l <  �T�Z��n����v�ˎ��NG<BR>
     * ���j�ԋp�l >= 0 �Ƃ���B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     */
    public double getSuccActualReceiptTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Date l_datDeliveryDate) throws WEB3SystemLayerException;
    
    /**
     * (get��؋��ւ̐U�։\�z) <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getOsakaTransferableTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            Date l_datDeliveryDate) throws WEB3SystemLayerException;

    /**
     * (get�o���\�z&lt;DB����&gt;�`0�␳�����`)<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getPaymentTradingPowerDBQuote(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate) throws WEB3SystemLayerException;
    
    /**
     * (get�ۏ؋��ւ̐U�֊z) <BR>
     * <BR>
     * �ۏ؋��ւ̐U�֊z���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�ۏ؋��ւ̐U�֊z�v�Q�� <BR>
     * <BR>
     * ���w�肳�ꂽ�ڋq���M�p�������J�݂̎��A�ۏ؋��ւ̐U�֊z(=0)��ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_dblPayAmount - (�����z)
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getTransferAmountToDeposit(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate,
        double l_dblPayAmount) throws WEB3BaseException;

    /**
     * (get�O�ݎc�����) <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)
     * @@return WEB3ForeignPositionContract
     * @@throws WEB3SystemLayerException
     */
    public WEB3ForeignPositionContract getForeignPositionContract(
        WEB3GentradeSubAccount l_subAccount,String l_strCurrencyCode) throws WEB3SystemLayerException;
    
    /**
     * (get�S�ۃ��[���U�։\�z) <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    public double getSLChangePossAmt(WEB3GentradeSubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3SystemLayerException;

    /**
     * (validate���ʋ������ρ`�I�����C���J�n�O�`) <BR>
     * validate���ʋ������ρ`�I�����C���J�n�O�`
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettleBefOnline(
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (validate���ʋ������ρ`��ԁ`)<BR>
     * validate���ʋ������ρ`��ԁ`<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettleIntermission(
        WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (get�����s����)<BR>
     * (get�����s����) <BR>
     * <BR>
     * �V�[�P���X�}�u(����]�̓T�[�r�X)get�����s�����v�Q�� <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getNextBizDateShortfall(WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException;

    /**
     * (get�����ۏ؋����o�]��)<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_strDbCurrentPriceCheckDiv - (DB�����]�̓`�F�b�N�敪)<BR>
     * �⏕����<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getTodayDepositWithdrawTradingPower(
        WEB3GentradeSubAccount l_subAccount, String l_strDbCurrentPriceCheckDiv)
        throws WEB3BaseException;

    /**
     * (get�o���\�z�`�o�����͉�ʕ\���p�`)<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getPaymentTradingPowerAioCashoutInput(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datDeliveryDate) throws WEB3BaseException;
}
@
