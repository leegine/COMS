head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeCompleteUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֊���UnitService(WEB3AccTransChangeCompleteUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/22 �����(���u) ���r���[                                       
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (�U�֊���UnitService)<BR>
 * �U�֊���UnitService�C���^�[�t�F�C�X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public interface WEB3AccTransChangeCompleteUnitService extends Service
{

    /**
     * (complete�U��)
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)
     * @@throws WEB3BaseException
     * @@roseuid 413C22410253
     */
    public void completeChange(AioOrderUnit l_orderUnit)
        throws WEB3BaseException;
}
@
