head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopIfoOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�w�l�����敨OP�����ꌏ�T�[�r�X(WEB3ToStopIfoOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/23 ������(���u) �V�K�쐬
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (�t�w�l�����敨OP�����ꌏ�T�[�r�X)<BR>
 *�t�w�l�����敨OP�����ꌏ�T�[�r�X�C���^�[�t�F�C�X<BR>
 *�i�g�����U�N�V���������FTX_CREATE_NEW�j<BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3ToStopIfoOrderUnitService extends Service 
{

    /**
     * (submit�敨�V�K���t�w�l����)<BR>
     * �敨�V�K���t�w�l�����𔭒�����B<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨OP�P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void submitFuturesOpenContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (submit�敨�ԍϋt�w�l����)<BR>
     * �敨�ԍϋt�w�l�����𔭒�����B<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void submitFuturesSettleContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (submitOP�V�K���t�w�l����)<BR>
     * �I�v�V�����V�K���t�w�l�����𔭒�����B<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨OP�P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void submitOptionOpenContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (submitOP�ԍϋt�w�l����)<BR>
     * �I�v�V�����ԍϋt�w�l�����𔭒�����B<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨OP�����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    public void submitOptionSettleContractStopOrder(IfoOrderUnit l_orderUnit) throws WEB3BaseException;
}
@
