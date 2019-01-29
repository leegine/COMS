head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�����J�z�P���T�[�r�X�����N���X(WEB3FuturesOrderCarryOverUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 䈋� (���u) �V�K�쐬
Revesion History : 2008/04/11 ��іQ (���u) ���f��No.277,278
*/
package webbroker3.triggerorder.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (�敨�����J�z�P���T�[�r�X)<BR>
 * �敨�����J�z�P���T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * �P�����Ƃ̒����J�z���������{����B<BR>
 */
public interface WEB3FuturesOrderCarryOverUnitService extends Service 
{
    
    /**
     * (create�V�K����������)<BR>
     * ���������i�V�K���j���쐬����B<BR>
     * <BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_lisRsvIfoOrderUnits - (�\�񒍕��P�ʈꗗ)<BR>
     * �\�񒍕��P�ʈꗗ
     * @@roseuid 409B517B0253
     */
    public void createOpenContractNextOrder(OrderUnit l_orderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException;
    
    /**
     * (create�ԍϗ�������)<BR>
     * ���������i�ԍρj���쐬����B<BR>
     * <BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_lisRsvIfoOrderUnits - (�\�񒍕��P�ʈꗗ)<BR>
     * �\�񒍕��P�ʈꗗ<BR>
     * @@roseuid 409B5420030F
     */
    public void createSettleContractNextOrder(OrderUnit l_orderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException;
    
    /**
     * (update�J�z������)<BR>
     * �J�z���������X�V����B<BR>
     * @@param l_orderUnit - �i�J�z���j�����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strOrderErrorReasonCode - �����G���[���R�R�[�h<BR>
     * @@roseuid 40A0B87B01F3
     */
    public void updateCarryOverOriginOrder(
        OrderUnit l_orderUnit,
        String l_strOrderErrorReasonCode)
        throws DataQueryException, DataNetworkException, IllegalStateException;
        
    /**
     * (expire�J�z������)<BR>
     * <BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@return boolean
     * @@roseuid 409B497C0205
     */
    public void expireCarryOverOriginOrder(OrderUnit l_orderUnit) throws WEB3BaseException;
}
@
