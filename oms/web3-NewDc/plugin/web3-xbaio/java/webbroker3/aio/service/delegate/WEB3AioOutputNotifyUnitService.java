head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.15.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOutputNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�ɒʒmUnitService(WEB3AioOutputNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.common.WEB3BaseException;


/**
 * (�o�ɒʒmUnitService)<BR>
 * �o�ɒʒmUnitService�C���^�[�t�F�C�X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public interface WEB3AioOutputNotifyUnitService extends Service 
{
    
    /**
     * (notify�o�ɒʒm)<BR>
     * �o�ɒʒm�������s���B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strProductCode - �����R�[�h
     * @@param l_strCustodyDiv - �ۊǋ敪
     * @@param l_strSpecificDiv - ��������敪
     * @@param l_lngQuantity - ����
     * @@param l_strCommodityDiv - ���i�敪
     * @@param l_strCancelDiv - ����敪
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4157961901F5
     */
    public String notifyOutputNotify(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strProductCode, 
        String l_strCustodyDiv, 
        String l_strSpecificDiv, 
        Long l_lngQuantity,
	    String l_strCommodityDiv,
	    String l_strCancelDiv) 
        throws WEB3BaseException;
}
@
