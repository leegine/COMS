head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.02.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqForeignCostRegistCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO���������n�萔���o�^���ʃ��N�G�X�g)<BR>
 * �Ǘ��ҊO���������n�萔���o�^���ʃ��N�G�X�g�N���X
 *   
 * @@author ���m�a
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostRegistCommonRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqForeignCostRegistCommonRequestTest.class);

    WEB3AdminFeqForeignCostRegistCommonRequest l_request;

	public WEB3AdminFeqForeignCostRegistCommonRequestTest(String name)
	{
		super(name);
	}

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminFeqForeignCostRegistCommonRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * �ُ�
     * 
     * �����敪=="3"
     * 
     * �Q�j�����敪
     * �@@this.�����敪 != �i�h1:�����h or �h2:����h�j
     * �@@�@@�̏ꍇ�A��O���X���[����B
     * �@@class: WEB3BusinessLayerException
     * �@@tag:   BUSINESS_ERROR_01403<BR>
     */
    public void testValidate_Case001()
    {
        final String STR_METHOD_NAME = "testValidate_Case001()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	l_request.marketCode = "1001";
        	l_request.costDiv="01";
            l_request.dealingType = "3";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01403, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ����
     * 
     * �����敪=="1"
     * 
     * �Q�j�����敪
     * �@@this.�����敪 != �i�h1:�����h or �h2:����h�j
     * �@@�@@�̏ꍇ�A��O���X���[����B
     * �@@class: WEB3BusinessLayerException
     * �@@tag:   BUSINESS_ERROR_01403<BR>
     */
    public void testValidate_Case002()
    {
        final String STR_METHOD_NAME = "testValidate_Case002()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	l_request.marketCode = "1001";
        	l_request.costDiv="01";

        	WEB3AdminFeqForeignCostUnit l_adminFeqForeignCostUnit = new WEB3AdminFeqForeignCostUnit();
        	l_adminFeqForeignCostUnit.additionalAmt = "10";
        	l_adminFeqForeignCostUnit.applyEndDate = WEB3DateUtility.toDay(new Date(2008,05,06));
        	l_adminFeqForeignCostUnit.applyStartDate = WEB3DateUtility.toDay(new Date(2008,01,06));
        	l_adminFeqForeignCostUnit.collectRate = "0.1";
        	l_adminFeqForeignCostUnit.tradingAmtFrom = "10";
        	l_adminFeqForeignCostUnit.tradingAmtTo = "10000000";
        	WEB3AdminFeqForeignCostUnit[] l_adminFeqForeignCostUnits = {l_adminFeqForeignCostUnit};
        	l_request.feqLocalFeeUnit = l_adminFeqForeignCostUnits;
            l_request.dealingType = "1";
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ����
     * 
     * �����敪=="2"
     * 
     * �Q�j�����敪
     * �@@this.�����敪 != �i�h1:�����h or �h2:����h�j
     * �@@�@@�̏ꍇ�A��O���X���[����B
     * �@@class: WEB3BusinessLayerException
     * �@@tag:   BUSINESS_ERROR_01403<BR>
     */
    public void testValidate_Case003()
    {
        final String STR_METHOD_NAME = "testValidate_Case003()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	l_request.marketCode = "1001";
        	l_request.costDiv="01";

        	WEB3AdminFeqForeignCostUnit l_adminFeqForeignCostUnit = new WEB3AdminFeqForeignCostUnit();
        	l_adminFeqForeignCostUnit.additionalAmt = "10";
        	l_adminFeqForeignCostUnit.applyEndDate = WEB3DateUtility.toDay(new Date(2008,05,06));
        	l_adminFeqForeignCostUnit.applyStartDate = WEB3DateUtility.toDay(new Date(2008,01,06));
        	l_adminFeqForeignCostUnit.collectRate = "0.1";
        	l_adminFeqForeignCostUnit.tradingAmtFrom = "10";
        	l_adminFeqForeignCostUnit.tradingAmtTo = "10000000";
        	WEB3AdminFeqForeignCostUnit[] l_adminFeqForeignCostUnits = {l_adminFeqForeignCostUnit};
        	l_request.feqLocalFeeUnit = l_adminFeqForeignCostUnits;
            l_request.dealingType = "2";
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
