head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.05.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToIfoManualOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�蓮����UnitService(WEB3ToIfoManualOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualUnit;

/**
 * (�敨OP�蓮����UnitService)<BR>
 * �敨OP�蓮�����P���T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * �P�����Ƃ̎蓮�������������{����B<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public interface WEB3ToIfoManualOrderUnitService extends Service 
{
    
    /**
     * (exec�蓮����)<BR>
     * ����1�����Ƃ̎蓮�������s���B<BR>
     * @@param l_strProductType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@param l_strTriggerOrderType - (�����������)<BR>
     * �����������<BR>
     * @@param l_strOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_blnIsUpdated - (is�X�V)<BR>
     * is�X�V<BR>
     * @@param l_lngSubmitterLoginId - (�����҃��O�C��ID)<BR>
     * �����҃��O�C��ID<BR>
     * @@param l_strSubmitnotifyType - (�ʒm�o�H)<BR>
     * �ʒm�o�H<BR>
     * @@return WEB3FuturesOptionsManualUnit
     * @@throws WEB3BaseException
     * @@roseuid 43EFF162038A
     */
    public WEB3FuturesOptionsManualUnit execManualOrder(
        String l_strProductType, 
        String l_strTriggerOrderType, 
        String l_strOrderId, 
        boolean l_blnIsUpdated,
        Long l_lngSubmitterLoginId,
        String l_strSubmitnotifyType) throws WEB3BaseException;
}
@
