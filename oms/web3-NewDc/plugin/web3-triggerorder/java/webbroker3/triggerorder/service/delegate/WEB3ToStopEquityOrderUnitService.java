head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�w�l�������������ꌏ�T�[�r�X(WEB3ToStopEquityOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 ������(���u) �V�K�쐬
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (�t�w�l�������������ꌏ�T�[�r�X)<BR>
 * �t�w�l�������������ꌏ�T�[�r�X�C���^�[�t�F�C�X<BR>
 * �i�g�����U�N�V���������FTX_CREATE_NEW�j<BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3ToStopEquityOrderUnitService extends Service 
{
    
    /**
     * (submit���������t�w�l����)<BR>
     * ���������t�w�l�����𔭒�����B<BR>
     * @@param EqTypeOrderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C7A5C021A
     */
    public void submitEquityStopOrder(EqTypeOrderUnit EqTypeOrderUnit) throws WEB3BaseException;
    
    /**
     * (submit�M�p�V�K���t�w�l����)<BR>
     * �M�p�V�K���t�w�l�����𔭒�����B<BR>
     * @@param l_orderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C7A5C0277
     */
    public void submitMarginOpenContractStopOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (submit�M�p�ԍϋt�w�l����)<BR>
     * �M�p�ԍϋt�w�l�����𔭒�����B<BR>
     * @@param l_orderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C7A5C02C6
     */
    public void submitMarginSettleContractStopOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
}
@
