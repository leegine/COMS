head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeCancelNotifyChangeUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�����������ʒm�����ꌏ�T�[�r�X(WEB3MarginChangeCancelNotifyChangeUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 䈋� (���u) �V�K�쐬
                   2004/12/14 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/05 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;

/**
 * �i�M�p�����������ʒm�����ꌏ�T�[�r�X�j�B<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@author 䈋�
 * @@version 1.0
 */
public interface WEB3MarginChangeCancelNotifyChangeUnitService extends Service 
{
    
    /**
     * (notify����)<BR>
     * ���������ʒm���������{����B
     * @@param l_hostEqtypeOrderClmdReceiptParams - (������������ʒm�L���[Params)
     * @@param l_orderUnit - (�����P��)
     * @@throws WEB3BaseException
     */
    public void notifyChange(
        HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;
}
@
