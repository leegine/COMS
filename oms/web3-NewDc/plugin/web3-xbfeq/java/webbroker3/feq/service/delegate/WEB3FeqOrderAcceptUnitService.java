head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������t�P���T�[�r�X(WEB3FeqOrderAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ����� (���u) �V�K�쐬
                   2006/11/20 �����(���u) ���f���@@No.304�Ή�
                   2006/12/14 ꎉ�(���u) ���f���@@No.311�Ή�
                   2006/12/19 ���G��(���u) ���f���@@No.318�Ή�
Revesion History : 2008/02/26 �g�C��(���u) ���f���@@No.401�Ή�
*/

package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.message.WEB3FeqOrderAcceptCancelUnit;
import webbroker3.slebase.data.SleRcvdQParams;

/**
 * (�O������������t�P���T�[�r�X)<BR>
 * �O������������t�P���T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_JOIN_EXISTING�j<BR>
 * 
 * @@author �����
 * @@version 1.0
 */
public interface WEB3FeqOrderAcceptUnitService extends Service 
{
    /**
     * (notify������t)<BR>
     * ������t�P���������s���B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_cancelUnit - (�O������������t������)<BR>
     * �O������������t������<BR>
     * @@param l_sleRvcdQParams - (�O�������RCVD_Q)<BR>
     * SLE_RCVD_Q<BR>
     * @@throws WEB3BaseException
     */
    public void notifyOrderAccept(
        WEB3FeqOrderUnit l_orderUnit,
        WEB3FeqOrderAcceptCancelUnit l_cancelUnit,
        SleRcvdQParams l_sleRvcdQParams) throws WEB3BaseException;
}
@
