head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCarryOverUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������J�zUnitService(WEB3FeqOrderCarryOverUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[       
*/

package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;


/**
 * (�O�����������J�zUnitService)<BR>
 * �O�����������J�zUnitService�C���^�t�F�C�X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public interface WEB3FeqOrderCarryOverUnitService extends Service 
{
    
    /**
     * (exec�����J�z)<BR>
     * �ڋq�P�ʂŒ����J�z���������{����B
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 42B8A35F0327
     */
    public void execOrderCarryOver(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;
}
@
