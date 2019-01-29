head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�������敨OP�����ꌏ�T�[�r�X(WEB3ToSuccIfoOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/06 ���n(���u) �V�K�쐬���f��311
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;

/**
 * (�A�������敨OP�����ꌏ�T�[�r�X)<BR>
 * �A�������敨OP�����ꌏ�T�[�r�X�C���^�t�F�[�X�B<BR>
 * �i�g�����U�N�V���������F�@@TX_JOIN_EXISTING�j<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public interface WEB3ToSuccIfoOrderUnitService extends Service
{

    /**
     * (submit�敨�V�K������)<BR>
     * �敨�V�K�������𔭒�����B<BR>
     * @@param l_rsvIfoOrderUnit - (�敨OP�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF4E2F03C8
     */
    public void submitFuturesOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException;

    /**
     * (submit�敨�ԍϒ���)<BR>
     * �敨�ԍϒ����𔭒�����B<BR>
     * @@param l_rsvIfoOrderUnit - (�敨OP�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF4E2F03D8
     */
    public void submitFuturesSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException;

    /**
     * (submitOP�V�K������)<BR>
     * �I�v�V�����V�K�������𔭒�����B<BR>
     * @@param l_rsvIfoOrderUnit - (�敨OP�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF4E2F03DA
     */
    public void submitOptionsOpenContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException;

    /**
     * (submitOP�ԍϒ���)<BR>
     * �I�v�V�����ԍϒ����𔭒�����B<BR>
     * @@param l_rsvIfoOrderUnit - (�敨OP�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47DF4E2F03DC
     */
    public void submitOptionsSettleContractOrder(WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit)
        throws WEB3BaseException;
}
@
