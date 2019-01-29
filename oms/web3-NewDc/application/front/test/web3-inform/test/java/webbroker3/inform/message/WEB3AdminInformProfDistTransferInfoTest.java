head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.31.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistTransferInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 利金・分配金・売却代金振込先情報テストクラス(WEB3AdminInformProfDistTransferInfoTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/31 李木子 (中訊) 新規作成
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.DirectDebitParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (利金・分配金・売却代金振込先情報テストクラス)<BR>
 * 利金・分配金・売却代金振込先情報テストクラス<BR>
 * @@author 李木子
 * @@version 1.0
 */
public class WEB3AdminInformProfDistTransferInfoTest extends TestBaseForMock {
	
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        		WEB3AdminInformProfDistTransferInfoTest.class);
    
    public static StringBuffer expectMethodParam = new StringBuffer(); 

    public WEB3AdminInformProfDistTransferInfoTest(String arg0)
    {
        super(arg0);
    }

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void test_WEB3AdminInformProfDistTransferInfo_0001()
	{
		DirectDebitParams l_directDebitRow = new DirectDebitParams();
		
		l_directDebitRow.setInstitutionCode("0D");
		l_directDebitRow.setSonarLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505", "yyyyMMdd"));
		l_directDebitRow.setSonarCreatedTimestamp(WEB3DateUtility.getDate("20050505", "yyyyMMdd"));
		l_directDebitRow.setBranchCode("381");
		l_directDebitRow.setAccountCode("2512238");
		l_directDebitRow.setTraderCode("11127");
		l_directDebitRow.setDesignateDiv("1");
		l_directDebitRow.setComodity("11");
		l_directDebitRow.setFundCode("38");
		l_directDebitRow.setTransferDiv("1");
		l_directDebitRow.setFinInstitutionCode("1234");
		l_directDebitRow.setFinInstitutionName("lmz");
		l_directDebitRow.setFinBranchCode("00381");
		l_directDebitRow.setFinBranchName("gsyh");
		l_directDebitRow.setFinSaveDiv("1");
		l_directDebitRow.setFinAccountNo("8888");
		l_directDebitRow.setFinAccountName("cai");
		l_directDebitRow.setTransCommission("1");
		l_directDebitRow.setTransDealDiv("2");
		l_directDebitRow.setLastUpdater("01268");
		l_directDebitRow.setCreatedTimestamp(WEB3DateUtility.getDate("20050505", "yyyyMMdd"));
		l_directDebitRow.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505", "yyyyMMdd"));

		try
		{
			WEB3AdminInformProfDistTransferInfo l_WEB3AdminInformProfDistTransferInfo =
				new WEB3AdminInformProfDistTransferInfo(l_directDebitRow);
			
			assertEquals("251223", l_WEB3AdminInformProfDistTransferInfo.accountCode);
			assertEquals(WEB3DateUtility.formatDate(l_WEB3AdminInformProfDistTransferInfo.registDate, "yyyyMMdd"), 
					WEB3DateUtility.formatDate(WEB3DateUtility.getDate("20050505", "yyyyMMdd"), "yyyyMMdd"));
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.branchCode, "381");
			assertNull(l_WEB3AdminInformProfDistTransferInfo.accountName);
			assertNull(l_WEB3AdminInformProfDistTransferInfo.accountNameKana);
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.traderCode, "11127");
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.specifyDiv, "1");
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.product, "11");
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.productCode, "38");
			assertNull(l_WEB3AdminInformProfDistTransferInfo.productName);
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.transferDiv, "1");
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.financialInstitutionCode, "1234");
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.financialInstitutionName, "lmz");
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.financialBranchCode, "00381");
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.financialBranchName, "gsyh");
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.financialAccountDiv, "1");
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.financialAccountCode, "8888");
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.transferAccountDiv, "1");
			assertEquals(l_WEB3AdminInformProfDistTransferInfo.tradeHandleDiv, "2");

		}
		catch (WEB3BaseException e)
		{
			fail();
		}
	}
}
@
