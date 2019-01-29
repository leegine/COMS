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
filename	WEB3FuturesOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�����ʒmUnitService(WEB3FuturesOrderNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

/**
 * (�敨�����ʒmUnitService)<BR>
 * �����w���敨�����ʒmUnitService�C���^�t�F�C�X<BR>
 * @@author  : ������
 * @@version : 1.0
 */
public interface WEB3FuturesOrderNotifyUnitService extends Service 
{
   
   /**
    * (notify�V�K������)<BR>
    * �V�K�������ʒm���������{����B <BR>
    * @@param l_hostFotypeOrderReceiptParams - (�敨OP�����ʒm�L���[Params)<BR>
    * �y�敨OP�����ʒm�L���[�e�[�u���z��1-Row��\������N���X<BR>
    * @@param l_subAccount - �⏕�����I�u�W�F�N�g
    * @@roseuid 4174F78701F0
    */
   public void notifyOpenContractOrder(HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams, SubAccount l_subAccount) throws WEB3BaseException;
   
   /**
    * (notify�ԍϒ���)<BR>
    * �ԍϒ����ʒm���������{����B <BR>
    * @@param l_hostFotypeOrderReceiptParams - (�敨OP�����ʒm�L���[Params)<BR>
    * �y�敨OP�����ʒm�L���[�e�[�u���z��1-Row��\������N���X<BR>
    * @@param l_subAccount - �⏕�����I�u�W�F�N�g<BR>
    * @@roseuid 4174F787020F
    */
   public void notifySettleContractOrder(HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams, SubAccount l_subAccount) throws WEB3BaseException;
}
@
