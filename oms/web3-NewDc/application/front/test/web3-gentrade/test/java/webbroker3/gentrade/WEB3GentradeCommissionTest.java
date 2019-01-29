head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeCommissionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�e�X�g(WEB3GentradeCommissionTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/10  �h�C (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (�萔���e�X�g)<BR>
 * <BR>
 * @@author �h�C (���u)
 * @@version 1.0
 */
public class WEB3GentradeCommissionTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3GentradeCommissionTest.class);

    public WEB3GentradeCommissionTest(String arg0)
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
     * testGetConsumptionTax_Case0001
     */
    public void testGetConsumptionTax_Case0001()
    {
        final String STR_METHOD_NAME = " testGetConsumptionTax_Case0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setConsumptionTax(25.5);
            assertEquals(25.5, l_commission.getConsumptionTax(), 0.1);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetConsumptionTax_Case0002
     */
    public void testGetConsumptionTax_Case0002()
    {
        final String STR_METHOD_NAME = " testGetConsumptionTax_Case0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setConsumptionTax(0);
            assertEquals(0, l_commission.getConsumptionTax(), 0.1);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
