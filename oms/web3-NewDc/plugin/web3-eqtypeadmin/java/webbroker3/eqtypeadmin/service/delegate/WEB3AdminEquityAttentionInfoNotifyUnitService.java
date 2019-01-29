head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӏ��ʒm�ꌏ�T�[�r�X(WEB3AdminEquityAttentionInfoNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 ������(���u) ���f��No.219
Revision History : 2009/01/21 �И���(���u) ���f��No.232
Revision History : 2009/02/12 �И���(���u) �d�l�ύX���f��No.236
*/

package webbroker3.eqtypeadmin.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams;

/**
 * (���ӏ��ʒm�ꌏ�T�[�r�X)<BR>
 * ���ӏ��ʒm�ꌏ�T�[�r�X�C���^�[�t�F�C�X<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3AdminEquityAttentionInfoNotifyUnitService extends Service
{
    /**
     * (notify�����l�����)<BR>
     * ���ӏ��i�����l�����j�������s�Ȃ��B<BR>
     * @@param l_hostAttentionInfoNotifyParams - (���ӏ��ʒm�L���[�e�[�u��)<BR>
     * ���ӏ��ʒm�L���[�e�[�u��<BR>
     * @@param l_eqtypeTradedProductRow - (�����������)<BR>
     * ������������I�u�W�F�N�g<BR>
     * @@param l_eqtypeTradedProductUpdqRow - (�����������updq)<BR>
     * �����������updq<BR>
     * @@param l_productRow - (����)<BR>
     * ����<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4938727A00D4
     */
    public String notifyLimitRangeInfo(
        HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams,
        EqtypeTradedProductRow l_eqtypeTradedProductRow,
        EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow,
        ProductRow l_productRow) throws WEB3BaseException;
}
@
