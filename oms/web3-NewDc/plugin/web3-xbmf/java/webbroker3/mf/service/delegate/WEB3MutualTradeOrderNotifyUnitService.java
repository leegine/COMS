head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M���������ʒmUnitService(WEB3MutualTradeOrderNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ����  (���u) �V�K�쐬
                   2004/08/24 ����� (���u) ���r���[
*/

package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * (���M���������ʒmUnitService)
 * ���M���������ʒm�P���T�[�r�X�C���^�t�F�[�X���M���������ʒm�P�����Ƃ̏��������{����B<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public interface WEB3MutualTradeOrderNotifyUnitService extends Service 
{
    
    /**
     * (notify���������ʒm)<BR>
     * ���M���������ʒm�������s���B<BR>
     * @@param l_orderNotifyQueueParams - ���M�����ʒm�L���[Params<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40BEF93003E7
     */
    public void notifyTradeOrderNotify(HostXbmfOrderNotifyParams l_orderNotifyQueueParams)
    	throws WEB3BaseException;
}
@
