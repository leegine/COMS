head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�ꌏ�T�[�r�X(WEB3EquityOrderNotifyPartService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 �R�w�� (���u) �V�K�쐬
                   2004/12/15 �����a��(SAR) �c�Č��Ή� �m��.�P�T�S
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;

/**
 * �i���������ʒm�ꌏ�T�[�r�X�j�B<br>
 * <br>
 * ���������ʒm�ꌏ�T�[�r�X�C���^�[�t�F�[�X<br>
 * <br>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@version 1.0
 */
public interface WEB3EquityOrderNotifyUnitService extends Service
{

    /**
     * �i�ʒm�ꌏ�����j�B
     * @@param l_hostEqtypeOrderReceiptParams �����������͒ʒm�f�[�^�A�_�v�^
     * @@roseuid 405FF51C0388
     */
    public void notifyPartProcess(WEB3EquityOrderInputNotifyAdapter l_orderInputNotifyAdapter)
        throws WEB3BaseException;
}
@
