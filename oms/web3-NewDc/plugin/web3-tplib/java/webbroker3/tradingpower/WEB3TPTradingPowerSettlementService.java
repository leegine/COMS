head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerSettlementService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ώ���]�̓T�[�r�X(WEB3TPTradingPowerSettlementService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/22 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower;

import java.util.Date;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * (�������ώ���]�̓T�[�r�X)
 * �������ώ���]�̓T�[�r�X�C���^�[�t�F�[�X
 */
public interface WEB3TPTradingPowerSettlementService extends Service
{

    /**
     * �iget�������ϔ��t�\���ʁj<BR>
     * <BR>
     * @@param l_subAccount - �i�⏕�����j
     * @@param l_datSpecifiedDate - �i�w����j
     * @@param l_lngOrderFundId - �i��������ID�j
     * @@param l_lngMarketId - �i�s��ID�j
     * @@param l_dblLimitPrice - �i�w�l�j
     * @@param l_dblLotSize - �i�����P�ʁj
     * @@return double
     */
    public double getSellOrderPossibleQuantity(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datSpecifiedDate,
        long l_lngOrderFundId,
        long l_lngMarketId,
        double l_dblLimitPrice,
        double l_dblLotSize) throws WEB3SystemLayerException;

    /**
     * �iget�������ϔ��t�\�z�j<BR>
     * <BR>
     * @@param l_subAccount - �i�⏕�����j
     * @@param l_datSpecifiedDate - �i�w����j
     * @@param l_lngOrderFundId - �i��������ID�j
     * @@param l_blnTodayRepFund - �i���������Ώۖ����t���O�j
     * �@@�E�w����������������Ώۖ����̏ꍇ��true
     * �@@�E�w����������������Ώۖ����łȂ��ꍇ��false
     * @@return double
     */
    public double getBuyOrderPossibleAmount(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datSpecifiedDate,
        long l_lngOrderFundId,
        boolean l_blnTodayRepFund)
        throws WEB3SystemLayerException;
}@
