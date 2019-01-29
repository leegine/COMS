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
filename	WEB3MarginSwapOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p��������ʒm�ꌏ�T�[�r�X�C���^�t�F�[�X(WEB3MarginSwapOrderNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 �X�� (SRA) �V�K�쐬
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;


/**
 * �i�M�p��������ʒm�ꌏ�T�[�r�X�j�B<BR>
 * <BR>
 * �M�p����������n�����ʒm�ꌏ�T�[�r�X�C���^�t�F�[�X�B<BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@version 1.0
 */
public interface WEB3MarginSwapOrderNotifyUnitService extends Service 
{
    
    /**
     * (notify�������n����)�B<BR>
     * <BR>
     * �������n�����ʒm���������{����B<BR>
     * <BR>
     * @@param l_params - �������n���͒ʒm�L���[Params�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    public void notifySwapOrder(WEB3MarginSwapOrderNotifyDataAdapter l_adapter)
        throws WEB3BaseException;
    
    /**
     * (notify�������n�������)�B<BR>
     * <BR>
     * �������n����������������{����B <BR>
     * <BR>
     * @@param l_params - �������n���͒ʒm�L���[Params�I�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    public void notifyCancelSwapOrder(WEB3MarginSwapOrderNotifyDataAdapter l_adapter)
        throws WEB3BaseException;
}
@
