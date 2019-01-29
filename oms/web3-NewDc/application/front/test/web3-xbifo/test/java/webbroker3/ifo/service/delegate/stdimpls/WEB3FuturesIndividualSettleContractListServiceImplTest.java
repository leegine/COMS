head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.44.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesIndividualSettleContractListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物個別返済一覧表示サービスImplTest(WEB3FuturesIndividualSettleContractListServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/26 張騰宇 (中訊) 新規作成 仕様変更モデル
*/
package webbroker3.ifo.service.delegate.stdimpls;

import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesIndividualSettleContractListServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesIndividualSettleContractListServiceImplTest.class);

    public WEB3FuturesIndividualSettleContractListServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesIndividualSettleContractListServiceImpl.createContractUnit(WEB3FuturesContractReferenceUnit)'
     */
    public void testCreateContractUnitCase1()
    {
        final String STR_METHOD_NAME = "testCreateContractUnitCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesIndividualSettleContractListServiceImpl l_impl =
                new WEB3FuturesIndividualSettleContractListServiceImpl();
            WEB3FuturesContractReferenceUnit l_contractReferenceUnit =
                new WEB3FuturesContractReferenceUnit();
            l_contractReferenceUnit.sessionType = "1";
            WEB3FuturesOptionsContractUnit l_contractUnit =
                l_impl.createContractUnit(l_contractReferenceUnit);
            assertEquals(l_contractUnit.sessionType, "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateContractUnitCase2()
    {
        final String STR_METHOD_NAME = "testCreateContractUnitCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesIndividualSettleContractListServiceImpl l_impl =
                new WEB3FuturesIndividualSettleContractListServiceImpl();
            WEB3FuturesContractReferenceUnit l_contractReferenceUnit =
                new WEB3FuturesContractReferenceUnit();
            l_contractReferenceUnit.sessionType = null;
            WEB3FuturesOptionsContractUnit l_contractUnit =
                l_impl.createContractUnit(l_contractReferenceUnit);
            assertNull(l_contractUnit.sessionType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesIndividualSettleContractListServiceImpl.sortContractUnit(WEB3FuturesOptionsContractUnit[], WEB3FuturesOptionsSortKey[])'
     */
    public void testSortContractUnitCase1()
    {
        final String STR_METHOD_NAME = "testSortContractUnitCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesIndividualSettleContractListServiceImpl l_impl =
                new WEB3FuturesIndividualSettleContractListServiceImpl();
            
            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[3];
            WEB3FuturesOptionsContractUnit l_unit1 = new WEB3FuturesOptionsContractUnit();
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.sessionType = null;
            WEB3FuturesOptionsContractUnit l_unit2 = new WEB3FuturesOptionsContractUnit();
            l_unit2.openDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_unit2.sessionType = "1";
            WEB3FuturesOptionsContractUnit l_unit3 = new WEB3FuturesOptionsContractUnit();
            l_unit3.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit3.sessionType = "1";
            l_contractUnits[0] = l_unit1;
            l_contractUnits[1] = l_unit2;
            l_contractUnits[2] = l_unit3;
            
            WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[1];
            WEB3FuturesOptionsSortKey l_sortKey1 = new WEB3FuturesOptionsSortKey();
            l_sortKey1.ascDesc = "A";
            l_sortKey1.keyItem = "openDate";
            l_sortKeys[0] = l_sortKey1;
            
            l_contractUnits = l_impl.sortContractUnit(l_contractUnits,l_sortKeys);
            WEB3FuturesOptionsContractUnit l_unit11 = l_contractUnits[0];
            WEB3FuturesOptionsContractUnit l_unit12 = l_contractUnits[1];
            WEB3FuturesOptionsContractUnit l_unit13 = l_contractUnits[2];
            assertNull(l_unit11.sessionType);
            assertEquals(l_unit12.sessionType, "1");
            assertEquals(l_unit13.sessionType, "1");
            
            assertEquals(WEB3DateUtility.formatDate(l_unit11.openDate, "yyyyMMdd"), "20070618");
            assertEquals(WEB3DateUtility.formatDate(l_unit12.openDate, "yyyyMMdd"), "20070618");
            assertEquals(WEB3DateUtility.formatDate(l_unit13.openDate, "yyyyMMdd"), "20070619");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testSortContractUnitCase2()
    {
        final String STR_METHOD_NAME = "testSortContractUnitCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesIndividualSettleContractListServiceImpl l_impl =
                new WEB3FuturesIndividualSettleContractListServiceImpl();
            
            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[3];
            WEB3FuturesOptionsContractUnit l_unit1 = new WEB3FuturesOptionsContractUnit();
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.sessionType = null;
            WEB3FuturesOptionsContractUnit l_unit2 = new WEB3FuturesOptionsContractUnit();
            l_unit2.openDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_unit2.sessionType = "1";
            WEB3FuturesOptionsContractUnit l_unit3 = new WEB3FuturesOptionsContractUnit();
            l_unit3.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit3.sessionType = "1";
            l_contractUnits[0] = l_unit1;
            l_contractUnits[1] = l_unit2;
            l_contractUnits[2] = l_unit3;
            
            WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[1];
            WEB3FuturesOptionsSortKey l_sortKey1 = new WEB3FuturesOptionsSortKey();
            l_sortKey1.ascDesc = "A";
            l_sortKey1.keyItem = "income";
            l_sortKeys[0] = l_sortKey1;
            
            l_contractUnits = l_impl.sortContractUnit(l_contractUnits,l_sortKeys);
            WEB3FuturesOptionsContractUnit l_unit11 = l_contractUnits[0];
            WEB3FuturesOptionsContractUnit l_unit12 = l_contractUnits[1];
            WEB3FuturesOptionsContractUnit l_unit13 = l_contractUnits[2];
            assertNull(l_unit11.sessionType);
            assertEquals(l_unit12.sessionType, "1");
            assertEquals(l_unit13.sessionType, "1");
            
            assertEquals(WEB3DateUtility.formatDate(l_unit11.openDate, "yyyyMMdd"), "20070618");
            assertEquals(WEB3DateUtility.formatDate(l_unit12.openDate, "yyyyMMdd"), "20070619");
            assertEquals(WEB3DateUtility.formatDate(l_unit13.openDate, "yyyyMMdd"), "20070618");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
