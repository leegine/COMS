head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.15.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransferAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o����tUnitService�C���^�[�t�F�C�X(WEB3AioCashTransferAcceptUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ���E (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[    
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (���o����tUnitService)<BR>
 * ���o����tUnitService�C���^�[�t�F�C�X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public interface WEB3AioCashTransferAcceptUnitService extends Service 
{
    
    /**
     * (���o����tDB�X�V)<BR>
     * ���o����tDB�X�V�������s���B
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)
     * @@param l_strErrorCode - (�G���[�R�[�h)
     * @@param l_strAcceptNoticeDiv - (��t�ʒm�敪)
     * @@roseuid 40FF5D7A01B5
     */
    public void execute(AioOrderUnit l_orderUnit, String l_strErrorCode, String l_strAcceptNoticeDiv) 
        throws WEB3BaseException;
}
@
