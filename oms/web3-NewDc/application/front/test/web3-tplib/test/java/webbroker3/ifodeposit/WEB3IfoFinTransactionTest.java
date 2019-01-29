head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoFinTransactionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : WEB3IfoFinTransactionTest.java
Author Name         : Daiwa Institute of Research
Revision History    : 2008/08/18 張少傑 (中訊) 新規作成
*/

package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoFinTransactionTest extends TestBaseForMock
{
    WEB3LogUtility
       log = WEB3LogUtility.getInstance(WEB3IfoFinTransactionTest.class);
    WEB3IfoFinTransaction l_ifoFinTransaction = null;
    public WEB3IfoFinTransactionTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    //トランザクションカテゴリ==  先物新規取引,trueを返却する
    public void testIsFutures_C001()
    {
        String STR_METHOD_NAME = "testIsFutures_C001"; 
        log.entering(STR_METHOD_NAME);
        
        l_ifoFinTransaction = new WEB3IfoFinTransaction();
        l_ifoFinTransaction.finTransactionCateg = FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN;;       
        boolean l_blReturn  = l_ifoFinTransaction.isFutures();
        assertEquals(true,l_blReturn);
        log.exiting(STR_METHOD_NAME);
    }

    //トランザクションカテゴリ== 先物返済取引,trueを返却する
    public void testIsFutures_C002()
    {
        String STR_METHOD_NAME = "testIsFutures_C002"; 
        log.entering(STR_METHOD_NAME);
        
        l_ifoFinTransaction = new WEB3IfoFinTransaction();
        l_ifoFinTransaction.finTransactionCateg = FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE;;       
        boolean l_blReturn  = l_ifoFinTransaction.isFutures();
        assertEquals(true,l_blReturn);
        log.exiting(STR_METHOD_NAME);
    }
    
    //トランザクションカテゴリ== OP返済取引,falseを返却する
    public void testIsFutures_C003()
    {
        String STR_METHOD_NAME = "testIsFutures_C003"; 
        log.entering(STR_METHOD_NAME);
        
        l_ifoFinTransaction = new WEB3IfoFinTransaction();
        l_ifoFinTransaction.finTransactionCateg = FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE;;       
        boolean l_blReturn  = l_ifoFinTransaction.isFutures();
        assertEquals(false,l_blReturn);
        log.exiting(STR_METHOD_NAME);
    }
}
@
