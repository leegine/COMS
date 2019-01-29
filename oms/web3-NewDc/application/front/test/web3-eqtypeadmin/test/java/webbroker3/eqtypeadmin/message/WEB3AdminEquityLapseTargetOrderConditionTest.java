head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityLapseTargetOrderConditionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AdminEquityLapseTargetOrderConditionTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/12/27　@趙林鵬(中訊)
*/
package webbroker3.eqtypeadmin.message;


import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 株式失効対象注文条件のテスト<BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public class WEB3AdminEquityLapseTargetOrderConditionTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityLapseTargetOrderConditionTest.class);

    public WEB3AdminEquityLapseTargetOrderConditionTest(String name) 
    {
        super(name);
    }
    
    /**
     * リクエストデータの整合性チェック<BR>
     * 市場コード一覧チェック<BR>
     *３－２）　@this.市場コード一覧に下記の項目以外が設定されていたら、<BR>
     * 　@　@「市場コードが未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・"東京" <BR>
     * 　@　@　@・"大阪" <BR>
     * 　@　@　@・"名古屋" <BR>
     * 　@　@　@・"福岡" <BR>
     * 　@　@　@・"札幌" <BR>
     * 　@　@　@・"NNM" <BR>
     * 　@　@　@・"JASDAQ" <BR>
     * 　@　@　@・"JNX-PTS" <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     */
    public void test_validate_0001()
    {
    	//log
        final String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
    	
    	WEB3AdminEquityLapseTargetOrderCondition l_adminEquityLapseTargetOrderCondition = new WEB3AdminEquityLapseTargetOrderCondition();

    	//部店コード
    	l_adminEquityLapseTargetOrderCondition.branchCode = new String[]{"381"};

    	//銘柄コード
    	l_adminEquityLapseTargetOrderCondition.productCode = "12345";
    	
    	//市場コード
    	l_adminEquityLapseTargetOrderCondition.marketList = new String[]{"JNX-PTS"};

        try
        {
        	l_adminEquityLapseTargetOrderCondition.validate();
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,e.getErrorInfo());
        }
    }

    /**
     * リクエストデータの整合性チェック<BR>
     * 市場コード一覧チェック<BR>
     *３－２）　@this.市場コード一覧に下記の項目以外が設定されていたら、<BR>
     * 　@　@「市場コードが未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・"東京" <BR>
     * 　@　@　@・"大阪" <BR>
     * 　@　@　@・"名古屋" <BR>
     * 　@　@　@・"福岡" <BR>
     * 　@　@　@・"札幌" <BR>
     * 　@　@　@・"NNM" <BR>
     * 　@　@　@・"JASDAQ" <BR>
     * 　@　@　@・"JNX-PTS" <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     */
    public void test_validate_0002()
    {
    	//log
        final String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
    	
    	WEB3AdminEquityLapseTargetOrderCondition l_adminEquityLapseTargetOrderCondition = new WEB3AdminEquityLapseTargetOrderCondition();

    	//部店コード
    	l_adminEquityLapseTargetOrderCondition.branchCode = new String[]{"381"};
    	
    	//銘柄コード
    	l_adminEquityLapseTargetOrderCondition.productCode = "12345";
    	
    	//市場コード
    	l_adminEquityLapseTargetOrderCondition.marketList = new String[]{"11"};
    	
    	//取引区分
    	l_adminEquityLapseTargetOrderCondition.tradingTypeList = new String[]{"1"};
    	
    	//弁済区分
    	l_adminEquityLapseTargetOrderCondition.repaymentList = new String[]{ "EQUITY_BUY"};

        try
        {
        	l_adminEquityLapseTargetOrderCondition.validate();
            fail();
        }
        catch (WEB3BaseException e) 
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02445,e.getErrorInfo());
        }
    }

}
@
