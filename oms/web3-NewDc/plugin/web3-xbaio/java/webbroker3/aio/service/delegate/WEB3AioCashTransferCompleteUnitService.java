head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.14.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransferCompleteUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o������UnitService�C���^�[�t�F�C�X(WEB3AioCashTransferCompleteUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ���E (���u) �V�K�쐬  
                   2004/10/22 ���� (���u) ���r���[    
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.common.WEB3BaseException;


/**
 * (���o������UnitService�C���^�[�t�F�C�X)
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public interface WEB3AioCashTransferCompleteUnitService extends Service 
{
    
    /**
     * (complete���o��)<BR>
     * ���o�����ʂł̒����f�[�^�̍X�V�ƃg�����U�N�V�����f�[�^�̐������s���B
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)
     * @@roseuid 40FE63070016
     */
    public void completeCashTransfer(AioOrderUnit l_orderUnit);
    
    /**
     * (complete���o�����)<BR>
     * ���o�����ʁi����j�ł̒����f�[�^�ƃg�����U�N�V�����f�[�^�̍X�V���s���B
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)
     * @@throws WEB3BaseException
     * @@roseuid 4105D55F0177
     */
    public void completeCashTransferCancel(AioOrderUnit l_orderUnit) 
        throws WEB3BaseException;
}@
