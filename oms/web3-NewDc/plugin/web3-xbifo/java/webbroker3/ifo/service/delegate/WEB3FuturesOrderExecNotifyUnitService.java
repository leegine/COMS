head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderExecNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�o���ʒm�P���T�[�r�X�C���^�t�F�C�X(WEB3FuturesOrderExecNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/23 䈋� (���u) �V�K�쐬
*/
package webbroker3.ifo.service.delegate;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (�敨�o���ʒm�P���T�[�r�X)<BR>
 * �����w���敨�o���ʒm�P���T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * �P�����Ƃ̏o���ʒm���������{����B<BR>
 */
public interface WEB3FuturesOrderExecNotifyUnitService extends Service 
{
    
    /**
     * (notify���)<BR>
     * ��菈�������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�o���ʒm�jnotify���v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_tsExecDate - ������<BR>
     * @@param l_dblExecQuantity - ��萔��<BR>
     * @@param l_dblExecPrice - ���P��<BR>
     * @@param l_strExecNotifyDiv - �o���ʒm�敪<BR>
     * @@roseuid 40A842C301C7
     */
    public void notifyExecute(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice, String l_strExecNotifyDiv)
    throws WEB3BaseException;
    
    /**
     * (notify�����)<BR>
     * �o���ʒm���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�o���ʒm�jnotify������v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_tsExecDate - ������<BR>
     * @@param l_dblExecQuantity - ��萔��<BR>
     * @@param l_dblExecPrice - ���P��<BR>
     * @@roseuid 40A842C301E6
     */
    public void notifyExecuteCancel(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice) throws WEB3BaseException; 
}
@
