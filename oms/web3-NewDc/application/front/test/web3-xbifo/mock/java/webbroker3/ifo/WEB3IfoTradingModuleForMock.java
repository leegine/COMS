head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoTradingModuleForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g��������W���[�����XForMock(WEB3IfoTradingModuleForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/02 ���G�� (���u) �V�K�쐬
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketAdapterImplForMock;

/**
 * �g��������W���[�����XForMock
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3IfoTradingModuleForMock extends WEB3IfoTradingModule
{
    /**
     * �R���X�g���N�^�B<BR>
     * �g�����U�N�V�����}�l�[�W����WEB3IfoFinTransactionManagerImplForMark��<BR>
     * �ݒ肷��B<BR>
     */
    public WEB3IfoTradingModuleForMock()
    {
        super();
        super.m_marketAdapter = new IfoMarketAdapterImplForMock();
        super.m_finTranManager = new WEB3IfoFinTransactionManagerImplForMock();
    }
}
@
