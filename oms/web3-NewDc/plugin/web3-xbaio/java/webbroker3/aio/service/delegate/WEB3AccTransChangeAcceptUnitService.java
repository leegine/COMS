head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֎�tUnitService(WEB3AccTransChangeAcceptUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/22 �����(���u) ���r���[                                       
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (�U�֎�tUnitService)<BR>
 * �U�֎�tUnitService�C���^�[�t�F�C�X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public interface WEB3AccTransChangeAcceptUnitService extends Service
{

    /**
     * �U�֐�����tDB�X�V�������s���B
     * @@param l_request - (�����P�ʃI�u�W�F�N�g)
     * @@param l_strErrorCode - (�G���[�R�[�h)
     * @@param l_strAcceptDiv - (��t�ʒm�敪)
     * @@throws WEB3BaseException
     * @@roseuid 413C2198004F
     */
    public void execute(
        AioOrderUnit l_orderUnit,
        String l_strErrorCode,
        String l_strAcceptDiv)
        throws WEB3BaseException;
}
@
