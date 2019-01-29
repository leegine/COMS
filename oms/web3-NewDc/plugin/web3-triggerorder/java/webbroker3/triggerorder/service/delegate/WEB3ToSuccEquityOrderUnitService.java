head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���������������ꌏ�T�[�r�X(WEB3ToSuccEquityOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;

/**
 * (�A���������������ꌏ�T�[�r�X)<BR>
 * �A���������������ꌏ�T�[�r�X�C���^�t�F�[�X�B<BR>
 * �i�g�����U�N�V���������F�@@TX_CREATE_NEW�j<BR>
 * <BR>
 * @@author ������ <BR>
 * @@version 1.0<BR>
 */
public interface WEB3ToSuccEquityOrderUnitService extends Service 
{
    
    /**
     * (submit������������)<BR>
     * �������������𔭒�����B<BR>
     * @@param l_rsvEqOrderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 432565AE0388
     */
    public void submitEquityOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException;
    
    /**
     * (submit�M�p�V�K������)<BR>
     * �M�p�V�K�������𔭒�����B<BR>
     * @@param l_rsvEqOrderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 43329885027B
     */
    public void submitMarginOpenContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException;
    
    /**
     * (submit�M�p�ԍϒ���)<BR>
     * �M�p�ԍϒ����𔭒�����B<BR>
     * @@param l_rsvEqOrderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 434622680148
     */
    public void submitMarginSettleContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException;
    
    /**
     * (submit�M�p�������n����)<BR>
     * �M�p�������n�����𔭒�����B<BR>
     * @@param l_rsvEqOrderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 43462268014A
     */
    public void submitMarginSwapContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException;
}
@
