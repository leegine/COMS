head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.35.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXAccOpenApplyDownloadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者FX口座開設申込ダウンロードCSVのテストクラス(WEB3AdminFXAccOpenApplyDownloadCsvTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/09/09 許丹 (中訊) 新規作成
*/
package webbroker3.aio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者FX口座開設申込ダウンロードCSV)<BR>
 * 管理者FX口座開設申込ダウンロードCSVのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenApplyDownloadCsvTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFXAccOpenApplyDownloadCsvTest.class);

    /**
     * FXデータ制御サービスImpl
     */
    WEB3AdminFXAccOpenApplyDownloadCsv l_csv = null;

    public WEB3AdminFXAccOpenApplyDownloadCsvTest(String arg0)
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

    public void testCreateColumnHeader_Case001()
    {
        final String STR_METHOD_NAME = "testCreateColumnHeader_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_csv = new WEB3AdminFXAccOpenApplyDownloadCsv();
            
            
            List l_lisColumnLabels = new ArrayList();
            l_lisColumnLabels.add("追加変更区分");
            l_lisColumnLabels.add("利用者コード");
            l_lisColumnLabels.add("利用者名");
            l_lisColumnLabels.add("ログインID");
            l_lisColumnLabels.add("ログインパスワード");
            l_lisColumnLabels.add("発注パスワード");
            l_lisColumnLabels.add("メールアドレス１");
            l_lisColumnLabels.add("メールアドレス２");
            l_lisColumnLabels.add("自己受託区分");
            l_lisColumnLabels.add("利用者属性");
            l_lisColumnLabels.add("決済方法@");
            l_lisColumnLabels.add("手数料区分");
            l_lisColumnLabels.add("ロスカット区分");
            l_lisColumnLabels.add("取引可能区分");
            l_lisColumnLabels.add("電子交付承諾日");
            l_lisColumnLabels.add("取引説明書確認日");
            l_lisColumnLabels.add("約諾書番号");
            l_lisColumnLabels.add("備考");
            l_lisColumnLabels.add("商品コード１");
            l_lisColumnLabels.add("発注上限数量１");
            l_lisColumnLabels.add("商品コード２");
            l_lisColumnLabels.add("発注上限数量２");
            l_lisColumnLabels.add("商品コード３");
            l_lisColumnLabels.add("発注上限数量３");
            l_lisColumnLabels.add("商品コード４");
            l_lisColumnLabels.add("発注上限数量４");
            l_lisColumnLabels.add("商品コード５");
            l_lisColumnLabels.add("発注上限数量５");
            l_lisColumnLabels.add("商品コード６");
            l_lisColumnLabels.add("発注上限数量６");
            l_lisColumnLabels.add("商品コード７");
            l_lisColumnLabels.add("発注上限数量７");
            l_lisColumnLabels.add("商品コード８");
            l_lisColumnLabels.add("発注上限数量８");
            l_lisColumnLabels.add("商品コード９");
            l_lisColumnLabels.add("発注上限数量９");
            l_lisColumnLabels.add("商品コード１０");
            l_lisColumnLabels.add("発注上限数量１０");
            l_lisColumnLabels.add("商品コード１１");
            l_lisColumnLabels.add("発注上限数量１１");
            l_lisColumnLabels.add("商品コード１２");
            l_lisColumnLabels.add("発注上限数量１２");
            l_lisColumnLabels.add("商品コード１３");
            l_lisColumnLabels.add("発注上限数量１３");
            l_lisColumnLabels.add("商品コード１４");
            l_lisColumnLabels.add("発注上限数量１４");
            l_lisColumnLabels.add("商品コード１５");
            l_lisColumnLabels.add("発注上限数量１５");
            l_lisColumnLabels.add("商品コード１６");
            l_lisColumnLabels.add("発注上限数量１６");
            l_lisColumnLabels.add("商品コード１７");
            l_lisColumnLabels.add("発注上限数量１７");
            l_lisColumnLabels.add("商品コード１８");
            l_lisColumnLabels.add("発注上限数量１８");
            l_lisColumnLabels.add("商品コード１９");
            l_lisColumnLabels.add("発注上限数量１９");
            l_lisColumnLabels.add("商品コード２０");
            l_lisColumnLabels.add("発注上限数量２０");
            l_lisColumnLabels.add("商品コード２１");
            l_lisColumnLabels.add("発注上限数量２１");
            l_lisColumnLabels.add("商品コード２２");
            l_lisColumnLabels.add("発注上限数量２２");
            l_lisColumnLabels.add("商品コード２３");
            l_lisColumnLabels.add("発注上限数量２３");
            l_lisColumnLabels.add("商品コード２４");
            l_lisColumnLabels.add("発注上限数量２４");
            l_lisColumnLabels.add("商品コード２５");
            l_lisColumnLabels.add("発注上限数量２５");
            int l_strColumnType;
            DateFormat l_dateFormat = null;
            for (int i = 0; i < l_lisColumnLabels.size();i++)
            {
                String l_strLabel = (String) l_lisColumnLabels.get(i);
                WEB3GentradeCsvColumnModel l_model = l_csv.getColumnModel(l_strLabel);
                l_strColumnType = 0;
                l_dateFormat = null;
                assertEquals(i, l_model.getColumnNumber());
                if ("電子交付承諾日".equals(l_strLabel) || "取引説明書確認日".equals(l_strLabel))
                {
                    l_strColumnType = 20;
                    l_dateFormat = new SimpleDateFormat("yyyyMMdd");
                }
                assertEquals(l_strColumnType, l_model.getColumnType());
                assertEquals(l_dateFormat, l_model.getDateFormat());
            }

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
