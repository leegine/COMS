head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerReCalcService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͍Čv�Z�T�[�r�X(WEB3TPTradingPowerReCalcService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * (�]�͍Čv�Z�T�[�r�X)<BR>
 */
public interface WEB3TPTradingPowerReCalcService extends Service
{
    /**
     * (�]�͍Čv�Z)<BR>
     * �]�͍Čv�Z�����{���A�����Ŏw�肳�ꂽ�ڋq�̗]�͏�Ԃ��ŐV�ɂ���B<BR>
     * <BR>
     * ���V�[�P���X�}�u(�]�͍Čv�Z�T�[�r�X)�]�͍Čv�Z�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     */
    public void reCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;
}
@
