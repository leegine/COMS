head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.35.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXAccOpenUploadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者入出金一覧ダウンロードCSVテスト(WEB3AdminFXAccOpenUploadCsvTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/09 劉仁和 (中訊) 新規作成
*/

package webbroker3.aio;

import java.util.ArrayList;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （管理者FX口座開設アップロードCSVテスト）<BR>
 *
 * @@author 劉仁和
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadCsvTest extends TestBaseForMock {

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioListDownloadCSVTest.class);

	public WEB3AdminFXAccOpenUploadCsvTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	private int count = 0;

    /**
     * validate明細行(All正常)<BR>
     */
	public void testValidateDetailsLine_Case001()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();
        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("1,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("1,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("20080501,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("20080501,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("2008050112,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("12355621,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try {

			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);

		} catch (WEB3BaseException l_ex) {

			log.error(l_ex.getMessage());
			fail();
		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());

			fail();
		}
		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate明細行(利用者名チェック)<BR>
     */
	public void testValidateDetailsLine_Case002()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();
        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf" +
        		"ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf" +
        		"ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("12,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("1,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("20080501,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("20080501,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("2008050112,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("12355621,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try {

			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);

            fail();
		} catch (WEB3BaseException l_ex) {

			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02369,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());

			fail();
		}
		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate明細行(9利用者属性のチェック)<BR>
     */
	public void testValidateDetailsLine_Case003()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();
        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("12,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("1,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("20080501,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("20080501,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("2008050112,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("12355621,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);

            fail();
		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03111,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate明細行(決済方法@のチェック)<BR>
     */
	public void testValidateDetailsLine_Case004()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("1,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("12,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("20080501,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("20080501,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("2008050112,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("12355621,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try 
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);

            fail();
		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03112,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}	

    /**
     * validate明細行(電子交付承諾日のチェック)<BR>
     */
	public void testValidateDetailsLine_Case005()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("1,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("1,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("200805019,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("20080501,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("2008050112,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("12355621,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();
		} catch (WEB3BaseException l_ex) {

			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03113,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}


    /**
     * validate明細行(15 取引説明書確認日のチェック)<BR>
     */
	public void testValidateDetailsLine_Case006()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック            
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("1,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("1,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("20080501,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("200805019,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("2008050111,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("12355621,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try 
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();

		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03114,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate明細行(16 約諾書番号のチェック)<BR>
     */
	public void testValidateDetailsLine_Case007()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("1,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("1,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("20080501,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("20080501,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("j2008050112,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("12355621,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();

		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03115,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}
		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate明細行(33 発注上限数量8のチェック)<BR>
     */
	public void testValidateDetailsLine_Case008()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("1,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("1,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("20080501,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("20080501,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("200805011,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("1235562145645644,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();
		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02380,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate明細行(33 発注上限数量8のチェック)<BR>
     */
	public void testValidateDetailsLine_Case0011()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("1,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("1,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("20080501,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("20080501,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("200805011,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("dsfsde223,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try
        {

			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();

		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02379,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}

    /**
     * validate明細行(32 商品コード8のチェック)<BR>
     */
	public void testValidateDetailsLine_Case0010()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("1,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("1,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("20080501,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("20080501,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("200805011,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff888,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("12355621,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try
        {
			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();
		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02378,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}


    /**
     * validate明細行(33 発注上限数量8のチェック)<BR>
     */
	public void testValidateDetailsLine_Case009()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();

        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック            da yu 120 
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("1,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("1,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("20080501,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("20080501,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("200805011,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("dsfsde,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try
        {

			l_csv.addRow(l_sbRow.toString());

			l_csv.validateDetailsLine(0);
            fail();

		} catch (WEB3BaseException l_ex) {
			
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02379,l_ex.getErrorInfo());

		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			fail();
		}

		log.exiting("STR_METHOD_NAME");
	}

    /**
     * createカラムヘッダ<BR>
     */
	public void testCreateColumnHeader_Case001()
	{
        final String STR_METHOD_NAME = "testCreateColumnHeader_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminFXAccOpenUploadCsv l_csv = null;
        List l_lisLabels = new ArrayList();
        try {

        l_csv = new WEB3AdminFXAccOpenUploadCsv();

        //管理者FX口座開設アップロードCSV.利用者コードラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_CODE_LABEL);
        //管理者FX口座開設アップロードCSV.利用者名ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_NAME_LABEL);
        //管理者FX口座開設アップロードCSV.ログインIDラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOGINID_LABEL);
        //管理者FX口座開設アップロードCSV.ログインパスワードラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOGIN_PASSWORD_LABEL);
        //管理者FX口座開設アップロードCSV.発注パスワードラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_PASSWORD_LABEL);
        //管理者FX口座開設アップロードCSV.メールアドレス１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS1_LABEL);
        //管理者FX口座開設アップロードCSV.メールアドレス２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS2_LABEL);
        //管理者FX口座開設アップロードCSV.自己受託区分ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.SELF_TRUSTDIV_LABEL);
        //管理者FX口座開設アップロードCSV.利用者属性ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_ATTRIBUTE_LABEL);
        //管理者FX口座開設アップロードCSV.決済方法@ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.SETTLEMENT_METHOD_LABEL);
        //管理者FX口座開設アップロードCSV.手数料区分ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.COMMISSIONDIV_LABEL);
        //管理者FX口座開設アップロードCSV.ロスカット区分ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOSSCUTDIV_LABEL);
        //管理者FX口座開設アップロードCSV.取引可能区分ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.TRADINGDIV_LABEL);
        //管理者FX口座開設アップロードCSV.電子交付承諾日ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ELECTRON_GRANT_PERMISSION_DAY_LABEL);
        //管理者FX口座開設アップロードCSV.取引説明書確認日ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.TRADE_OPERATING_MANUAL_CONFIRMATION_DAY_LABEL);
        //管理者FX口座開設アップロードCSV.約諾書番号ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.AGREEMENT_BOOK_NUMBER_LABEL);
        //管理者FX口座開設アップロードCSV.備考ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.REMARK_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE1_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER1_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE2_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER2_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE3_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER3_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE4_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER4_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE5_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER5_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード６ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE6_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量６ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER6_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード７ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE7_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量７ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER7_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード８ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE8_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量８ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER8_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード９ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE9_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量９ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER9_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１０ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE10_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１０ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER10_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE11_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER11_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE12_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER12_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE13_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER13_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE14_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER14_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE15_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER15_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１６ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE16_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１６ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER16_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１７ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE17_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１７ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER17_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１８ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE18_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１８ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER18_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１９ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE19_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１９ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER19_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２０ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE20_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２０ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER20_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE21_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER21_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE22_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER22_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE23_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER23_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE24_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER24_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE25_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER25_LABEL);

        for (int i = 0; i < l_lisLabels.size();i++)
        {
            String l_strLabel = (String) l_lisLabels.get(i);
            WEB3GentradeCsvColumnModel l_model = l_csv.getColumnModel(l_strLabel);
            String l_strNewLabel = l_model.getColumnLabel();

            assertEquals(i, l_model.getColumnNumber());
            assertEquals(l_strLabel,l_strNewLabel);
        }

    }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
 
	}

	public String getRowDigit()
	{
	  StringBuffer  l_sbRow = new StringBuffer();

	  //１）　@利用者コードのチェック
	  l_sbRow.append("12345678,");

     //２）　@利用者名チェック
     l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");

      //３）　@ログインＩＤのチェック　@
      l_sbRow.append("12456432,");
      //４）　@ログインパスワードのチェック
      l_sbRow.append("afdff1652,");
      //５）　@発注パスワードのチェック
      l_sbRow.append("afdffg652,");
      //６）　@メールアドレス１のチェック
      l_sbRow.append("fgsdgddfsdf453,");
      //７）　@メールアドレス２のチェック
      l_sbRow.append("sdfsfsdff5453,");
      //８）　@自己受託区分のチェック
      l_sbRow.append("1,");
      //９）　@利用者属性のチェック     
      l_sbRow.append("1,");
      //１０）　@決済方法@のチェック     
      l_sbRow.append("1,");
      //１１）　@手数料区分のチェック
      l_sbRow.append("a1,");
      //１２）　@ロスカット区分のチェック
      l_sbRow.append("a1,");
      //１３）　@取引可能区分のチェック
      l_sbRow.append("1,");
      //１４）　@電子交付承諾日のチェック
      l_sbRow.append("20080501,");
      //１５）　@取引説明書確認日のチェック
      l_sbRow.append("20080501,");
      //１６）　@約諾書番号のチェック
      l_sbRow.append("200805011,");
      //１７）　@備考のチェック
      l_sbRow.append("afjsidfer,");
      //１８）　@商品コード１のチェック   
      l_sbRow.append("fgfff,");
      //１９）　@発注上限数量１のチェック 
      l_sbRow.append("12355621,");
      //20）　@商品コード2のチェック   
      l_sbRow.append("fgfff,");
      //21）　@発注上限数量2のチェック 
      l_sbRow.append("12355621,");
      //22）　@商品コード3のチェック   
      l_sbRow.append("fgfff,");
      //23）　@発注上限数量3のチェック 
      l_sbRow.append("12355621,");
      //24）　@商品コード4のチェック   
      l_sbRow.append("fgfff,");
      //25）　@発注上限数量4のチェック 
      l_sbRow.append("12355621,");
      //26）　@商品コード5のチェック   
      l_sbRow.append("fgfff,");
      //27）　@発注上限数量5のチェック 
      l_sbRow.append("12355621,");
      //28）　@商品コード6のチェック   
      l_sbRow.append("fgfff,");
      //29）　@発注上限数量6のチェック 
      l_sbRow.append("12355621,");
      //30）　@商品コード7のチェック   
      l_sbRow.append("fgfff,");
      //31）　@発注上限数量7のチェック 
      l_sbRow.append("12355621,");

      //32）　@商品コード8のチェック   
      l_sbRow.append("3453532,");

      if(count == 0 )
      {
          //33）　@発注上限数量8のチェック 
          l_sbRow.append("fgfdgs,");
      }
      else
      {
          //33）　@発注上限数量8のチェック 
    	  l_sbRow.append("345353253,");
      }

      //34）　@商品コード9のチェック   
      l_sbRow.append("435345,");

      if(count == 1)
      {
          //35）　@発注上限数量9のチェック 
          l_sbRow.append("sdfsdf,");
      }
      else
      {
          //35）　@発注上限数量9のチェック 
    	  l_sbRow.append("345353253,");
      }

      //36）　@商品コード10のチェック   
      l_sbRow.append("435345,");

      if(count == 2)
      {
          //37）　@発注上限数量10のチェック 
          l_sbRow.append("fgfdg,");
      }
      else
      {
          //37）　@発注上限数量10のチェック 
    	  l_sbRow.append("345353253,");
      }

      //38）　@商品コード11のチェック   
      l_sbRow.append("435345,");

      if(count == 3)
      {
          //39）　@発注上限数量11のチェック 
          l_sbRow.append("dfsfs3,");
      }
      else
      {
          //39）　@発注上限数量11のチェック 
    	  l_sbRow.append("345353253,");
      }

      //40）　@商品コード12のチェック   
      l_sbRow.append("435345,");

      if(count == 4)
      {
          //41）　@発注上限数量12のチェック 
          l_sbRow.append("ggeg23,");
      }
      else
      {
          //41）　@発注上限数量12のチェック 
    	  l_sbRow.append("345353253,");
      }

      //42）　@商品コード13のチェック   
      l_sbRow.append("435345,");

      if(count == 5)
      {
          //43）　@発注上限数量13のチェック 
          l_sbRow.append("dfgd345,");
      }
      else
      {
          //43）　@発注上限数量13のチェック 
    	  l_sbRow.append("345353253,");
      }

      //44）　@商品コード14のチェック   
      l_sbRow.append("435345,");

      if(count == 6)
      {
          //45）　@発注上限数量14のチェック 
          l_sbRow.append("sfsfdd2354,");
      }
      else
      {
          //45）　@発注上限数量14のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      //46）　@商品コード15のチェック   
      l_sbRow.append("435345,");

      if(count == 7)
      {
          //47）　@発注上限数量15のチェック 
          l_sbRow.append("dfsfsdf34,");
      }
      else
      {
          //47）　@発注上限数量15のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      //48）　@商品コード16のチェック   
      l_sbRow.append("435345,");

      if(count == 8)
      {
          //49）　@発注上限数量16のチェック 
          l_sbRow.append("sdfsf345,");
      }
      else
      {
          //49）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
       //50）　@商品コード16のチェック   
       l_sbRow.append("435345,");

      if(count == 9)
      {
          //51）　@発注上限数量16のチェック 
          l_sbRow.append("dfgdfg344,");
      }
      else
      {
          //51）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      //52）　@商品コード16のチェック   
      l_sbRow.append("435345,");

      if(count == 10)
      {
          //53）　@発注上限数量16のチェック 
          l_sbRow.append("sfsdf344,");
      }
      else
      {
          //53）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      //54）　@商品コード16のチェック   
      l_sbRow.append("435345,");

      if(count == 11)
      {
          //55）　@発注上限数量16のチェック 
          l_sbRow.append("gdgdf345,");
      }
      else
      {
          //55）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }

      //56）　@商品コード16のチェック   
      l_sbRow.append("435345,");

      if(count == 12)
      {
          //57）　@発注上限数量16のチェック 
          l_sbRow.append("dsfsfs34,");
      }
      else
      {
          //57）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }

      //58）　@商品コード16のチェック   
      l_sbRow.append("435345,");

      if(count == 13)
      {
          //59）　@発注上限数量16のチェック 
          l_sbRow.append("fgsdf355,");
      }
      else
      {
          //59）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }

      //60）　@商品コード16のチェック   
      l_sbRow.append("435345,");

      if(count == 14)
      {
          //61）　@発注上限数量16のチェック 
          l_sbRow.append("fdgdgd34,");
      }
      else
      {
          //61）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      //62）　@商品コード16のチェック   
      l_sbRow.append("435345,");

      if(count == 15)
      {
          //63）　@発注上限数量16のチェック 
          l_sbRow.append("dgdsfgdf,");
      }
      else
      {
          //63）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }

      //64）　@商品コード16のチェック   
      l_sbRow.append("435345,");

      if(count == 16)
      {
          //65）　@発注上限数量16のチェック 
          l_sbRow.append("ffgdfgsd,");
      }
      else
      {
          //65）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }      

      //66）　@商品コード16のチェック   
      l_sbRow.append("435345,");

      if(count == 17)
      {
          //67）　@発注上限数量16のチェック 
          l_sbRow.append("gdsfgsdg45");
      }
      else
      {
          //67）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253");
      }
          return l_sbRow.toString()	;		  
    }

	public String getRow()
	{
	  StringBuffer  l_sbRow = new StringBuffer();

	  //１）　@利用者コードのチェック
	  l_sbRow.append("12345678,");

     //２）　@利用者名チェック
     l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");

      //３）　@ログインＩＤのチェック　@
      l_sbRow.append("12456432,");
      //４）　@ログインパスワードのチェック
      l_sbRow.append("afdff1652,");
      //５）　@発注パスワードのチェック
      l_sbRow.append("afdffg652,");
      //６）　@メールアドレス１のチェック
      l_sbRow.append("fgsdgddfsdf453,");
      //７）　@メールアドレス２のチェック
      l_sbRow.append("sdfsfsdff5453,");
      //８）　@自己受託区分のチェック
      l_sbRow.append("1,");
      //９）　@利用者属性のチェック     
      l_sbRow.append("1,");
      //１０）　@決済方法@のチェック     
      l_sbRow.append("1,");
      //１１）　@手数料区分のチェック
      l_sbRow.append("a1,");
      //１２）　@ロスカット区分のチェック
      l_sbRow.append("a1,");
      //１３）　@取引可能区分のチェック
      l_sbRow.append("1,");
      //１４）　@電子交付承諾日のチェック
      l_sbRow.append("20080501,");
      //１５）　@取引説明書確認日のチェック
      l_sbRow.append("20080501,");
      //１６）　@約諾書番号のチェック
      l_sbRow.append("200805011,");
      //１７）　@備考のチェック
      l_sbRow.append("afjsidfer,");
      //１８）　@商品コード１のチェック   
      l_sbRow.append("fgfff,");
      //１９）　@発注上限数量１のチェック 
      l_sbRow.append("12355621,");
      //20）　@商品コード2のチェック   
      l_sbRow.append("fgfff,");
      //21）　@発注上限数量2のチェック 
      l_sbRow.append("12355621,");
      //22）　@商品コード3のチェック   
      l_sbRow.append("fgfff,");
      //23）　@発注上限数量3のチェック 
      l_sbRow.append("12355621,");
      //24）　@商品コード4のチェック   
      l_sbRow.append("fgfff,");
      //25）　@発注上限数量4のチェック 
      l_sbRow.append("12355621,");
      //26）　@商品コード5のチェック   
      l_sbRow.append("fgfff,");
      //27）　@発注上限数量5のチェック 
      l_sbRow.append("12355621,");
      //28）　@商品コード6のチェック   
      l_sbRow.append("fgfff,");
      //29）　@発注上限数量6のチェック 
      l_sbRow.append("12355621,");
      //30）　@商品コード7のチェック   
      l_sbRow.append("fgfff,");
      //31）　@発注上限数量7のチェック 
      l_sbRow.append("12355621,");
      if(count == 0 )
      {
          //32）　@商品コード8のチェック   
    	  l_sbRow.append("34535325,");
      }
      else
      {
          //32）　@商品コード8のチェック   
          l_sbRow.append("345345,");
      }
      if(count == 1 )
      {
          //33）　@発注上限数量8のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //33）　@発注上限数量8のチェック 
    	  l_sbRow.append("345353253,");
      }
      if(count == 2)
      {
          //34）　@商品コード9のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //34）　@商品コード9のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 3)
      {
          //35）　@発注上限数量9のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //35）　@発注上限数量9のチェック 
    	  l_sbRow.append("345353253,");
      }
      if(count == 4)
      {
          //36）　@商品コード10のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //36）　@商品コード10のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 5)
      {
          //37）　@発注上限数量10のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //37）　@発注上限数量10のチェック 
    	  l_sbRow.append("345353253,");
      }
      if(count == 6)
      {
          //38）　@商品コード11のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //38）　@商品コード11のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 7)
      {
          //39）　@発注上限数量11のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //39）　@発注上限数量11のチェック 
    	  l_sbRow.append("345353253,");
      }
      if(count == 8)
      {
          //40）　@商品コード12のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //40）　@商品コード12のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 9)
      {
          //41）　@発注上限数量12のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //41）　@発注上限数量12のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      if(count == 10)
      {
          //42）　@商品コード13のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //42）　@商品コード13のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 11)
      {
          //43）　@発注上限数量13のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //43）　@発注上限数量13のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 12)
      {
          //44）　@商品コード14のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //44）　@商品コード14のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 13)
      {
          //45）　@発注上限数量14のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //45）　@発注上限数量14のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 14)
      {
          //46）　@商品コード15のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //46）　@商品コード15のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 15)
      {
          //47）　@発注上限数量15のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //47）　@発注上限数量15のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 16)
      {
          //48）　@商品コード16のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //48）　@商品コード16のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 17)
      {
          //49）　@発注上限数量16のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //49）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      if(count == 18)
      {
          //50）　@商品コード16のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //50）　@商品コード16のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 19)
      {
          //51）　@発注上限数量16のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //51）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 20)
      {
          //52）　@商品コード16のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //52）　@商品コード16のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 21)
      {
          //53）　@発注上限数量16のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //53）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 22)
      {
          //54）　@商品コード16のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //54）　@商品コード16のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 23)
      {
          //55）　@発注上限数量16のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //55）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 24)
      {
          //56）　@商品コード16のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //56）　@商品コード16のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 25)
      {
          //57）　@発注上限数量16のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //57）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 26)
      {
          //58）　@商品コード16のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //58）　@商品コード16のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 27)
      {
          //59）　@発注上限数量16のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //59）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 28)
      {
          //60）　@商品コード16のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //60）　@商品コード16のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 29)
      {
          //61）　@発注上限数量16のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //61）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      
      
      if(count == 30)
      {
          //62）　@商品コード16のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //62）　@商品コード16のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 31)
      {
          //63）　@発注上限数量16のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //63）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }
      
      
      if(count == 32)
      {
          //64）　@商品コード16のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //64）　@商品コード16のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 33)
      {
          //65）　@発注上限数量16のチェック 
          l_sbRow.append("3453532535353434,");
      }
      else
      {
          //65）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253,");
      }      
      
      if(count == 34)
      {
          //66）　@商品コード16のチェック   
          l_sbRow.append("34535345,");
      }
      else
      {
          //66）　@商品コード16のチェック   
          l_sbRow.append("435345,");
      }
      if(count == 35)
      {
          //67）　@発注上限数量16のチェック 
          l_sbRow.append("3453532535353434");
      }
      else
      {
          //67）　@発注上限数量16のチェック 
    	  l_sbRow.append("345353253");
      }
          return l_sbRow.toString()	;		  
    }

    /**
     * validate明細行(length)<BR>
     */
	public void testValidateDetailsLine_Case012()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        for(int i = 0; i < 36; i++)
        {
            WEB3AdminFXAccOpenUploadCsv l_csv = null;
            count = i;
            String l_strRow = this.getRow();
            try
            {
            	l_csv = new WEB3AdminFXAccOpenUploadCsv();
				l_csv.addRow(l_strRow);
				l_csv.validateDetailsLine(0);
				fail();
			} 
            catch (WEB3BaseException l_ex)
			{
				if(count%2 == 0)
				{
					assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02378,l_ex.getErrorInfo());
				}
				else
				{
					assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02380,l_ex.getErrorInfo());
				}
				
			}
            catch (Exception l_ex)
            {
            	log.error(l_ex.getMessage());
            	fail();
            }
            log.debug("STR_METHOD_NAME");
        }
	}

    /**
     * validate明細行(digit)<BR>
     */
	public void testValidateDetailsLine_Case013()
	{
        final String STR_METHOD_NAME = "testValidateDetailsLine_Case013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        for(int i = 0; i < 18; i++)
        {
            WEB3AdminFXAccOpenUploadCsv l_csv = null;
            count = i;
            String l_strRow = this.getRowDigit();
            try
            {
            	l_csv = new WEB3AdminFXAccOpenUploadCsv();
				l_csv.addRow(l_strRow);
				l_csv.validateDetailsLine(0);
				fail();
			} 
            catch (WEB3BaseException l_ex)
			{
			    assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02379,l_ex.getErrorInfo());
			}
            catch (Exception l_ex)
            {
            	log.error(l_ex.getMessage());
            	fail();
            }
            log.debug("STR_METHOD_NAME");
        }
	}

    /**
     * get利用者属性<BR>
     */
	public void testGetMethod_Case001()
	{
        final String STR_METHOD_NAME = "testGetMethod_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminFXAccOpenUploadCsv l_csv = new WEB3AdminFXAccOpenUploadCsv();
        StringBuffer l_sbRow = new StringBuffer();

        //１）　@利用者コードのチェック
        l_sbRow.append("12345678,");
        //２）　@利用者名チェック
        l_sbRow.append("ajfinfsidfnsdifnsdifsdifnsdfnsidnfsnfidnfsdnffnf,");
        //３）　@ログインＩＤのチェック　@
        l_sbRow.append("12456432,");
        //４）　@ログインパスワードのチェック
        l_sbRow.append("afdff1652,");
        //５）　@発注パスワードのチェック
        l_sbRow.append("afdffg652,");
        //６）　@メールアドレス１のチェック
        l_sbRow.append("fgsdgddfsdf453,");
        //７）　@メールアドレス２のチェック
        l_sbRow.append("sdfsfsdff5453,");
        //８）　@自己受託区分のチェック
        l_sbRow.append("1,");
        //９）　@利用者属性のチェック     
        l_sbRow.append("1,"); 
        //１０）　@決済方法@のチェック     
        l_sbRow.append("1,");
        //１１）　@手数料区分のチェック
        l_sbRow.append("a1,");
        //１２）　@ロスカット区分のチェック
        l_sbRow.append("a1,");
        //１３）　@取引可能区分のチェック
        l_sbRow.append("1,");
        //１４）　@電子交付承諾日のチェック
        l_sbRow.append("20080501,");
        //１５）　@取引説明書確認日のチェック
        l_sbRow.append("20080501,");
        //１６）　@約諾書番号のチェック
        l_sbRow.append("2008050112,");
        //１７）　@備考のチェック
        l_sbRow.append("afjsidfer,");
        //１８）　@商品コード１のチェック   
        l_sbRow.append("fgfff,");
        //１９）　@発注上限数量１のチェック 
        l_sbRow.append("12355621,");
        //20）　@商品コード2のチェック   
        l_sbRow.append("fgfff,");
        //21）　@発注上限数量2のチェック 
        l_sbRow.append("12355621,");
        //22）　@商品コード3のチェック   
        l_sbRow.append("fgfff,");
        //23）　@発注上限数量3のチェック 
        l_sbRow.append("12355621,");
        //24）　@商品コード4のチェック   
        l_sbRow.append("fgfff,");
        //25）　@発注上限数量4のチェック 
        l_sbRow.append("12355621,");
        //26）　@商品コード5のチェック   
        l_sbRow.append("fgfff,");
        //27）　@発注上限数量5のチェック 
        l_sbRow.append("12355621,");
        //28）　@商品コード6のチェック   
        l_sbRow.append("fgfff,");
        //29）　@発注上限数量6のチェック 
        l_sbRow.append("12355621,");
        //30）　@商品コード7のチェック   
        l_sbRow.append("fgfff,");
        //31）　@発注上限数量7のチェック 
        l_sbRow.append("12355621,");
        //32）　@商品コード8のチェック   
        l_sbRow.append("fgfff,");
        //33）　@発注上限数量8のチェック 
        l_sbRow.append("12355621,");
        //34）　@商品コード9のチェック   
        l_sbRow.append("fgfff,");
        //35）　@発注上限数量9のチェック 
        l_sbRow.append("12355621,");
        //36）　@商品コー ド10のチェック   
        l_sbRow.append("fgfff,");
        //37）　@発注上限数量10のチェック 
        l_sbRow.append("12355621,");
        //38）　@商品コード11のチェック   
        l_sbRow.append("fgfff,");
        //39）　@発注上限数量11のチェック 
        l_sbRow.append("12355621,");
        //40）　@商品コード12のチェック   
        l_sbRow.append("fgfff,");
        //41）　@発注上限数量12のチェック 
        l_sbRow.append("12355621,");
        //42）　@商品コード13のチェック   
        l_sbRow.append("fgfff,");
        //43）　@発注上限数量13のチェック 
        l_sbRow.append("12355621,");
        //44）　@商品コード14のチェック   
        l_sbRow.append("fgfff,");
        //45）　@発注上限数量14のチェック 
        l_sbRow.append("12355621,");
        //46）　@商品コード15のチェック   
        l_sbRow.append("fgfff,");
        //47）　@発注上限数量15のチェック 
        l_sbRow.append("12355621,");
        //48）　@商品コード16のチェック   
        l_sbRow.append("fgfff,");
        //49）　@発注上限数量16のチェック 
        l_sbRow.append("12355621,");
        //50）　@商品コー ド17のチェック   
        l_sbRow.append("fgfff,");
        //51）　@発注上限数量17のチェック 
        l_sbRow.append("12355621,");
        //52）　@商品コード18のチェック   
        l_sbRow.append("fgfff,");
        //53）　@発注上限数量18のチェック 
        l_sbRow.append("12355621,");
        //54）　@商品コード19のチェック   
        l_sbRow.append("fgfff,");
        //55）　@発注上限数量19のチェック 
        l_sbRow.append("12355621,");
        //56）　@商品コード20のチェック   
        l_sbRow.append("fgfff,");
        //57）　@発注上限数量20のチェック 
        l_sbRow.append("12355621,");
        //58）　@商品コード21のチェック   
        l_sbRow.append("fgfff,");
        //59）　@発注上限数量21のチェック 
        l_sbRow.append("12355621,");
        //60）　@商品コード22のチェック   
        l_sbRow.append("fgfff,");
        //61）　@発注上限数量22のチェック 
        l_sbRow.append("12355621,");
        //62）　@商品コード23のチェック   
        l_sbRow.append("fgfff,");
        //63）　@発注上限数量23のチェック 
        l_sbRow.append("12355621,");
        //64）　@商品コー ド24のチェック   
        l_sbRow.append("fgfff,");
        //65）　@発注上限数量24のチェック 
        l_sbRow.append("12355621,");
        //66）　@商品コード25のチェック   
        l_sbRow.append("fgfff,");
        //67）　@発注上限数量25のチェック 
        l_sbRow.append("12355621");
        try
        {

			l_csv.addRow(l_sbRow.toString());

			String l_strAvailAttribte = l_csv.getUserAttribute(0);
			String l_strSettlementMethod = l_csv.getSettlementMethod(0);
			String l_strElectronGrantPermissionDay = l_csv.getElectronGrantPermissionDay(0);
			String l_strTradeOperatingManualConfirmationDay = l_csv.getTradeOperatingManualConfirmationDay(0);
			String l_strAgreementBookNumber = l_csv.getAgreementBookNumber(0);
			String l_strProductCode8 = l_csv.getProductCode8(0);
			String l_strProductCode9 = l_csv.getProductCode9(0);
			String l_strProductCode10 = l_csv.getProductCode10(0);
			String l_strProductCode11 = l_csv.getProductCode11(0);
			String l_strProductCode12 = l_csv.getProductCode12(0);
			String l_strProductCode13 = l_csv.getProductCode13(0);
			String l_strProductCode14 = l_csv.getProductCode14(0);
			String l_strProductCode15 = l_csv.getProductCode15(0);
			String l_strProductCode16 = l_csv.getProductCode16(0);
			String l_strProductCode17 = l_csv.getProductCode17(0);
			String l_strProductCode18 = l_csv.getProductCode18(0);
			String l_strProductCode19 = l_csv.getProductCode19(0);
			String l_strProductCode20 = l_csv.getProductCode20(0);
			String l_strProductCode21 = l_csv.getProductCode21(0);
			String l_strProductCode22 = l_csv.getProductCode22(0);
			String l_strProductCode23 = l_csv.getProductCode23(0);
			String l_strProductCode24 = l_csv.getProductCode24(0);
			String l_strProductCode25 = l_csv.getProductCode25(0);

			String l_strOrderQuantityUpper8 = l_csv.getOrderQuantityUpper8(0);
			String l_strOrderQuantityUpper9 = l_csv.getOrderQuantityUpper9(0);
			String l_strOrderQuantityUpper10 = l_csv.getOrderQuantityUpper8(0);
			String l_strOrderQuantityUpper11 = l_csv.getOrderQuantityUpper11(0);
			String l_strOrderQuantityUpper12 = l_csv.getOrderQuantityUpper12(0);
			String l_strOrderQuantityUpper13 = l_csv.getOrderQuantityUpper13(0);
			String l_strOrderQuantityUpper14 = l_csv.getOrderQuantityUpper14(0);
			String l_strOrderQuantityUpper15 = l_csv.getOrderQuantityUpper15(0);
			String l_strOrderQuantityUpper16 = l_csv.getOrderQuantityUpper16(0);
			String l_strOrderQuantityUpper17 = l_csv.getOrderQuantityUpper17(0);
			String l_strOrderQuantityUpper18 = l_csv.getOrderQuantityUpper18(0);
			String l_strOrderQuantityUpper19 = l_csv.getOrderQuantityUpper19(0);
			String l_strOrderQuantityUpper20 = l_csv.getOrderQuantityUpper20(0);
			String l_strOrderQuantityUpper21 = l_csv.getOrderQuantityUpper21(0);
			String l_strOrderQuantityUpper22 = l_csv.getOrderQuantityUpper22(0);
			String l_strOrderQuantityUpper23 = l_csv.getOrderQuantityUpper23(0);
			String l_strOrderQuantityUpper24 = l_csv.getOrderQuantityUpper24(0);
			String l_strOrderQuantityUpper25 = l_csv.getOrderQuantityUpper25(0);

			assertEquals("1",l_strAvailAttribte);
			assertEquals("1",l_strSettlementMethod);
			assertEquals("20080501",l_strElectronGrantPermissionDay);
			assertEquals("20080501",l_strTradeOperatingManualConfirmationDay);
			assertEquals("2008050112",l_strAgreementBookNumber);
			
			assertEquals("fgfff",l_strProductCode8);
			assertEquals("12355621",l_strOrderQuantityUpper8);
			
			assertEquals("fgfff",l_strProductCode9);
			assertEquals("12355621",l_strOrderQuantityUpper9);
			
			assertEquals("fgfff",l_strProductCode10);
			assertEquals("12355621",l_strOrderQuantityUpper10);
			
			assertEquals("fgfff",l_strProductCode11);
			assertEquals("12355621",l_strOrderQuantityUpper11);
			
			assertEquals("fgfff",l_strProductCode12);
			assertEquals("12355621",l_strOrderQuantityUpper12);
			
			assertEquals("fgfff",l_strProductCode13);
			assertEquals("12355621",l_strOrderQuantityUpper13);
			
			assertEquals("fgfff",l_strProductCode14);
			assertEquals("12355621",l_strOrderQuantityUpper14);
			
			assertEquals("fgfff",l_strProductCode15);
			assertEquals("12355621",l_strOrderQuantityUpper15);
			
			assertEquals("fgfff",l_strProductCode16);
			assertEquals("12355621",l_strOrderQuantityUpper16);
			
			assertEquals("fgfff",l_strProductCode17);
			assertEquals("12355621",l_strOrderQuantityUpper17);
			
			assertEquals("fgfff",l_strProductCode18);
			assertEquals("12355621",l_strOrderQuantityUpper18);
			
			assertEquals("fgfff",l_strProductCode19);
			assertEquals("12355621",l_strOrderQuantityUpper19);
			
			assertEquals("fgfff",l_strProductCode20);
			assertEquals("12355621",l_strOrderQuantityUpper20);
			
			assertEquals("fgfff",l_strProductCode21);
			assertEquals("12355621",l_strOrderQuantityUpper21);
			
			assertEquals("fgfff",l_strProductCode22);
			assertEquals("12355621",l_strOrderQuantityUpper22);
			
			assertEquals("fgfff",l_strProductCode23);
			assertEquals("12355621",l_strOrderQuantityUpper23);
			
			assertEquals("fgfff",l_strProductCode24);
			assertEquals("12355621",l_strOrderQuantityUpper24);
			
			assertEquals("fgfff",l_strProductCode25);
			assertEquals("12355621",l_strOrderQuantityUpper25);
		
		} catch (WEB3BaseException l_ex) {
		    log.error(l_ex.getMessage());

			log.exiting("STR_METHOD_NAME");
			fail();
		}
		catch(Exception l_ex)
		{
		    log.error(l_ex.getMessage());
			log.exiting("STR_METHOD_NAME");
			fail();
		}
 
	}

 }


@
