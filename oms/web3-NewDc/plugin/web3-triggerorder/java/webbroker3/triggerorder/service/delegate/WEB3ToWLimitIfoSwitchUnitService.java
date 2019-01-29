head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitIfoSwitchUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l�����敨OP�ؑֈꌏ�T�[�r�X(WEB3ToWLimitIfoSwitchUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/23�@@�юu��(���u) �V�K�쐬
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (W�w�l�����敨OP�ؑֈꌏ�T�[�r�X)<BR>
 * W�w�l�����敨OP�ؑֈꌏ�T�[�r�X�C���^�[�t�F�C�X<BR>
 * �i�g�����U�N�V���������FTX_CREATE_NEW�j<BR>
 * 
 * @@author �юu��
 * @@version 1.0
 */
public interface WEB3ToWLimitIfoSwitchUnitService extends Service
{
    
    /**
     * (submit�敨�V�K��W�w�l����)<BR>
     * �敨�V�K��W�w�l�����ؑ֏������s���B<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨�I�v�V���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44923D5403DC
     */
    public void submitFuturesOpenContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (submit�敨�ԍ�W�w�l����)<BR>
     * �敨�ԍ�W�w�l�����ؑ֏������s���B
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨�I�v�V���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44923D5403DE
     */
    public void submitFuturesSettleContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (submitOP�V�K��W�w�l����)<BR>
     * �I�v�V�����V�K��W�w�l�����ؑ֏������s���B<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨�I�v�V���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923D550004
     */
    public void submitOptionOpenContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (submitOP�ԍ�W�w�l����)<BR>
     * �I�v�V�����ԍ�W�w�l�����ؑ֏������s���B<BR>
     * @@param l_orderUnit - (�敨OP�����P��)<BR>
     * �敨�I�v�V���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923D550006
     */
    public void submitOptionSettleContractWLimitOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException;
}
@
