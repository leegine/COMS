head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	IfoMarketAdapterImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IfoMarketAdapterImplForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/03 ���� (���u) �V�K�쐬
*/
package com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;

/**
 *
 * @@author ����(���u)
 * @@version 1.0
 */
public class IfoMarketAdapterImplForMock extends IfoMarketAdapterImpl
{
	 public MarketResponseReceiverCallbackService getMarketResponseReceiverCallbackService()
	{
		return new IfoMarketResponseReceiverCallbackServiceImplForMock();
	}
}
@
