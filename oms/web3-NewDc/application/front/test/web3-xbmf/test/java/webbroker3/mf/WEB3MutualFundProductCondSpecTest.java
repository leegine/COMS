head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.15.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundProductCondSpecTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�����������e�f�[�^�N���X�e�X�g(WEB3MutualFundProductCondSpecTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/11  �L���E�ĕ� (���u) �V�K�쐬
*/
package webbroker3.mf;

import webbroker3.mf.WEB3MutualFundProductCondSpec;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�����������e�f�[�^�N���X�e�X�g)<BR>
 * 
 * @@author �L���E�ĕ� (���u)
 * @@version 1.0
 */
public class WEB3MutualFundProductCondSpecTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundProductCondSpecTest.class);

    public WEB3MutualFundProductCondSpecTest(String arg0)
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

    public void testGetSetFrgnAmtBuy0001()
    {
        final String STR_METHOD_NAME = "testGetBuyFrgnMinAmtBuy0001()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

        try
        {
            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setBuyFrgnMinAmtBuy("1234567890");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("2345678901");
            l_productCondSpec.setBuyFrgnMinAmtAdd("3456789012");    
            l_productCondSpec.setBuyFrgnUnitAmtAdd("4567890123");       
            l_productCondSpec.setSellFrgnMinAmtSell("5678901234");
            l_productCondSpec.setSellFrgnUnitAmtSell("6789012345");

            String l_strResult1 = l_productCondSpec.getBuyFrgnMinAmtBuy();
            String l_strResult2 = l_productCondSpec.getBuyFrgnUnitAmtBuy();
            String l_strResult3 = l_productCondSpec.getBuyFrgnMinAmtAdd();       
            String l_strResult4 = l_productCondSpec.getBuyFrgnUnitAmtAdd();
            String l_strResult5 = l_productCondSpec.getSellFrgnMinAmtSell();   
            String l_strResult6 = l_productCondSpec.getSellFrgnUnitAmtSell();

            assertEquals("1234567890", l_strResult1);
            assertEquals("2345678901", l_strResult2);
            assertEquals("3456789012", l_strResult3);
            assertEquals("4567890123", l_strResult4);
            assertEquals("5678901234", l_strResult5);
            assertEquals("6789012345", l_strResult6);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}@
