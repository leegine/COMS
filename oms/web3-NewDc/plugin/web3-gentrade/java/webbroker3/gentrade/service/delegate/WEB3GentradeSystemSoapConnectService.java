head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeSystemSoapConnectService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���V�X�e��SOAP�ڑ��T�[�r�X(WEB3GentradeSystemSoapConnectService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15 ������(���u) �V�K�쐬
*/
package webbroker3.gentrade.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;

/**
 * �O���V�X�e��SOAP�ڑ��T�[�r�X�C���^�[�t�F�C�X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3GentradeSystemSoapConnectService extends Service
{
	/**
	 * SOAP���b�Z�[�W�𐶐����A���b�Z�[�W�̑��M���s���B<BR>
     * <BR>
     * ��Docment/literal�����ł�SOAP�ڑ����s���B<BR>
	 * @@param l_lngBranchId - ���XID
     * @@param l_strConnectDiv - �ڑ��敪
     * @@param l_strParameterlists - �p�����[�^���X�g
	 * @@throws WEB3BaseException
	 * @@return Object[]
	 * @@roseuid 421036A8039E
	 */
	public Object[] sendMessage(long l_lngBranchId, String l_strConnectDiv, String[] l_strParameterlists) throws WEB3BaseException;

    /**
     * RPC������SOAP�ڑ����s���B<BR>
     * <BR>
     * @@param l_lngBranchId - ���XID
     * @@param l_strConnectDiv - �ڑ��敪
     * @@param l_strParameterlists - �p�����[�^���X�g
     * @@throws WEB3BaseException
     * @@return Object
     * @@roseuid 421036A8039E
     */
    public Object rpcCall(long l_lngBranchId, String l_strConnectDiv, String[] l_strParameterlists) throws WEB3BaseException;
}
@
