head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.41.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioTradingModuleForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g��������W���[��ForMock(WEB3AioTradingModuleForMock.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/03/29 ꎉ� (���u) �V�K�쐬
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketAdapterImplForMock;

/**
 * �g��������W���[��ForMock
 *
 * @@author ꎉ�(���u)
 * @@version 1.0
 */
public class WEB3AioTradingModuleForMock extends WEB3AioTradingModule
{
    /**
     * �R���X�g���N�^�B<BR>
     * �g�����U�N�V�����}�l�[�W����WEB3IfoFinTransactionManagerImplForMark��<BR>
     * �ݒ肷��B<BR>
     */
    public WEB3AioTradingModuleForMock()
    {
        super();
        super.m_marketAdapter = new AioMarketAdapterImplForMock();
    }
}
@
