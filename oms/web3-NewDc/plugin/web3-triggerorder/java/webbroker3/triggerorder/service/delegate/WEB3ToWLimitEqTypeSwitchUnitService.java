head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEqTypeSwitchUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l���������ؑֈꌏ�T�[�r�X(WEB3ToWLimitEqTypeSwitchUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/17 ���G�� (���u) �V�K�쐬 �i���f���jNo.176
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (W�w�l���������ؑֈꌏ�T�[�r�X)<BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public interface WEB3ToWLimitEqTypeSwitchUnitService extends Service
{
    /**
     * W�w�l���������ؑֈꌏ�������s���B<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * @@throws WEB3BaseException
     */
    public void submit(EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException;
}
@
