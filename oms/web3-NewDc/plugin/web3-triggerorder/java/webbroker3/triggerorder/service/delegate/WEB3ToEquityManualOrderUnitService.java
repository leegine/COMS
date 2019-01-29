head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToEquityManualOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����蓮����UnitService(WEB3ToEquityManualOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/3/6 ��O��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.message.WEB3EquityManualUnit;

/**
 * (�����蓮����UnitService)<BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j�B<BR>
 * 
 * @@author ��O�� <BR>
 * @@version 1.0<BR>
 */
public interface WEB3ToEquityManualOrderUnitService extends Service 
{

    /**
     * @@param l_strOrderId - (����ID) <BR>
     * @@return WEB3EquityManualUnit <BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 432565AE0388
     */
    public WEB3EquityManualUnit validate(
        String l_strOrderId) throws WEB3BaseException;

    /**
     * @@param l_strOrderId - (����ID) <BR>
     * @@param l_submitterLoginId - (�����҃��O�C��ID) <BR>
     * @@param l_strNotifyType - (�ʒm�o�H) <BR>
     * @@return WEB3EquityManualUnit <BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 43329885027B
     */
    public WEB3EquityManualUnit submit(
        String l_strOrderId, 
        Long l_submitterLoginId, 
        String l_strNotifyType) throws WEB3BaseException;
    
}
@
