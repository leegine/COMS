head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n��t�ꌏ�T�[�r�X(WEB3MarginSwapMarginAcceptUnitService.java)
Author Name      : 2004/10/8 Ḗ@@��(���u) �V�K�쐬
*/

package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeSwapAcceptParams;

/**
 * �i�M�p����������n��t�ꌏ�T�[�r�X�j�B<BR>
 * <BR>
 * �M�p����������n��t�ꌏ�T�[�r�X�C���^�t�F�[�X<BR>
 *�i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@author �@@��
 * @@version 1.0
 */
public interface WEB3MarginSwapMarginAcceptUnitService extends Service  
{
    
    /**
     * (notify�������n��t)<BR>
     * �y�������n��t�L���[�e�[�u���z�L���[�f�[�^�ꌏ�ɑ΂��鏈�����s���B
     * @@param l_swapMarginAcceptQueParams - (�������n��t�L���[Params)<BR>
     * @@throws WEB3BaseException
     * @@return void
     * ����������t�L���[Params�B
     * @@roseuid 4101101701D7
     */
    public void notifySwapMarginAccept(HostEqtypeSwapAcceptParams hostEqtypeSwapAcceptParams) throws WEB3BaseException;
}
@
