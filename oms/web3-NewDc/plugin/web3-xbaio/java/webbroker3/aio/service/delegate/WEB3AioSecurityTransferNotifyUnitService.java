head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.17.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityTransferNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֒ʒmUnitService�C���^�[�t�F�C�X(WEB3AioSecurityTransferNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 �����(���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (�،��U�֒ʒmUnitService)<BR>
 * �،��U�֒ʒmUnitService�C���^�[�t�F�C�X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3AioSecurityTransferNotifyUnitService extends Service 
{
    
    /**
     * �،��U�֒ʒm�������s���B
     * @@param l_aioOrderUnit - �����P�ʃI�u�W�F�N�g�̔z��
     * @@param l_errorCode - �G���[�R�[�h
     * @@param l_acceptNotifyDiv - ��t�ʒm�敪
     * @@roseuid 415792E003BA
     */
    public void execute(AioOrderUnit[] l_aioOrderUnit, String l_errorCode, String l_acceptNotifyDiv)
        throws WEB3BaseException;
}
@
