head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashoutCancelUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�����UnitService(WEB3AioCashoutCancelUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 ���E (���u) �V�K�쐬                                      
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.common.WEB3BaseException;


/**
 * (�o�����UnitService)<BR>
 * �o�����UnitService�C���^�[�t�F�C�X
 */
public interface WEB3AioCashoutCancelUnitService extends Service 
{
    
    /**
     * �o������������s���B
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param String - �p�X���[�h
     * @@roseuid 4191D98E022B
     */
    public void execute(AioOrderUnit l_orderUnit,String l_strAdminPassword) throws WEB3BaseException;
}
@
