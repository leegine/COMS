head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.42.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOrderManagerUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �iWEB3ToSuccOrderManagerForTest.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/26 �g�E�N�| (���u) �V�K�쐬
*/
package webbroker3.triggerorder.util;

import webbroker3.triggerorder.WEB3ToSuccOrderManagerImplForMock;

public class WEB3ToSuccOrderManagerUtility
{
    public static void changeOrderManager()
    {
        WEB3TriggerOrderTradingModel.orderManager = new WEB3ToSuccOrderManagerImplForMock();
    }
}
@
