head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerAfterRepayService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍό�]�̓T�[�r�X(WEB3TPTradingPowerAfterRepayService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) �V�K�쐬
*/

package webbroker3.tradingpower;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * (�ԍό�]�̓T�[�r�X)
 */
public interface WEB3TPTradingPowerAfterRepayService extends Service
{

    /**
     * (create�ԍό㎑�Y�]�͏��<�M�p�ڋq>)<BR>
     * <BR>
     * �ԍό�]�̓I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό�]�̓T�[�r�XImpl)create�ԍό㎑�Y�]�͏��v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_orderSpecIntercepter - (�������e�C���^�Z�v�^)
     * @@param l_orderSpec - (�������e)
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerCalcMarginAfterRepay
     * @@roseuid 41BD4B7502FA
     */
    public WEB3TPTradingPowerCalcMarginAfterRepay createWEB3TPTradingPowerCalcAfterRepay(
        WEB3GentradeSubAccount l_subAccount,
        Object l_orderSpecIntercepter,
        Object l_orderSpec)
        throws WEB3SystemLayerException;

    /**
     * (create�ԍώ����ӕ���)<BR>
     * <BR>
     * �ԍώ����ӕ����I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό�]�̓T�[�r�XImpl)create�ԍώ����ӕ����v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_orderSpecIntercepter - (�������e�C���^�Z�v�^)
     * @@param l_orderSpec - (�������e)
     * @@return webbroker3.tradingpower.WEB3TPAttentionObjection
     * @@roseuid 41BD4B75030A
     */
    public WEB3TPAttentionObjection createWEB3TPAttentionObjection(
        WEB3GentradeSubAccount l_subAccount,
        Object l_orderSpecIntercepter,
        Object l_orderSpec)
        throws WEB3SystemLayerException;
}
@
