head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEqAttentionInfoRefRefRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・注意情報照会リクエスト(WEB3AdminEqAttentionInfoRefRefRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 孟亞南 (中訊) 新規作成 モデルNo.217,モデルNo.221-224
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEqAttentionInfoRefRefRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEqAttentionInfoRefRefRequestTest.class);
    
    public WEB3AdminEqAttentionInfoRefRefRequestTest(String name)
    {
        super(name);
    }

    /**
     * this.注意情報種別 != nullの場合
     * 「注意情報種別が未定義の値」の例外をスローする。 
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "0";
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03147, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.注意情報種別 != nullの場合
     * ・"売停情報" 
     * this.注意情報区分コード != nullの場合
     * 「注意情報区分コードが未定義の値」の例外をスローする。 
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A000";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03149, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.注意情報種別 != null　@かつ 
     * this.注意情報区分コード != null の場合
     * this.注意情報種別 ＝ "フリーフォーマット" かつ 
     * this.注意情報区分コード ≠ "フリーフォーマット"の場合、 
     * 「注意情報種別／注意情報区分指定が不整合」の例外をスローする。 
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "3";
        l_request.attentionInfoDivCode = "A001";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03148, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.注意情報種別 != null　@かつ 
     * this.注意情報区分コード != null の場合
     * this.注意情報種別 ＝ "値幅制限情報" かつ
     * this.注意情報区分コード ≠ "新規上場銘柄の初値が付いた場合"の場合、
     * 「注意情報種別／注意情報区分指定が不整合」の例外をスローする。 
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "2";
        l_request.attentionInfoDivCode = "A002";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03148, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.注意情報種別 ＝ "売停情報" の場合、 
     * this.注意情報区分コード ＝ "フリーフォーマット"　@ 
     * あるいは、this.注意情報区分コード ＝ "新規上場銘柄の初値が付いた場合"　@ 
     * 「注意情報種別／注意情報区分指定が不整合」の例外をスローする。 
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A081";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03148, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.注意情報種別 ＝ "売停情報" の場合、 
     * this.注意情報区分コード ＝ "フリーフォーマット"　@ 
     * あるいは、this.注意情報区分コード ＝ "新規上場銘柄の初値が付いた場合"　@ 
     * 「注意情報種別／注意情報区分指定が不整合」の例外をスローする。 
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A031";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03148, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード != nullの場合 
     * 「市場コードが未定義の値」の例外をスローする。 
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A003";
        l_request.marketCode = "0";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 1
     * this.銘柄コード != nullの場合 
     * ・this.銘柄コード != 数字 
     * 「銘柄コードエラー」の例外をスローする。 
     */
    public void test_validate_0008()
    {
        String STR_METHOD_NAME = "test_validate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A004";
        l_request.marketCode = "1";
        l_request.productCode = "aaa";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 2
     * this.銘柄コード != nullの場合 
     * ・this.銘柄コード.length != 5 
     * 「銘柄コードエラー」の例外をスローする。 
     */
    public void test_validate_0009()
    {
        String STR_METHOD_NAME = "test_validate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A005";
        l_request.marketCode = "2";
        l_request.productCode = "111111";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 3
     * this.銘柄コード = 11111
     * this.有効日 != nullの場合
     * 「有効日エラー」の例外をスローする。 
     */
    public void test_validate_0010()
    {
        String STR_METHOD_NAME = "test_validate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A006";
        l_request.marketCode = "3";
        l_request.productCode = "11111";
        l_request.validDate = "20022222";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03150, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 6
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From != nullの場合
     * 「情報発生日時Fromエラー」の例外をスローする。
     */
    public void test_validate_0011()
    {
        String STR_METHOD_NAME = "test_validate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A007";
        l_request.marketCode = "6";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "2009010915:00:00";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03151, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 8
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To != nullの場合
     * 「情報発生日時Toエラー」の例外をスローする。
     */
    public void test_validate_0012()
    {
        String STR_METHOD_NAME = "test_validate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A008";
        l_request.marketCode = "8";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "2009010915:00:00";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03152, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 6
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From != null　@かつ　@this.情報発生日時To != nullの場合
     * this.情報発生日時From > this.情報発生日時Toの場合
     * 「入力時間整合性エラー」の例外をスローする。 
     */
    public void test_validate_0013()
    {
        String STR_METHOD_NAME = "test_validate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A011";
        l_request.marketCode = "6";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090110150000";
        l_request.infoOccuredDateTo = "20090109150000";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01481, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 1
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To = 20090109150000
     * this.ソートキー == nullであった場合、
     * 「ソートキーがnull」の例外をスローする。 
     */
    public void test_validate_0014()
    {
        String STR_METHOD_NAME = "test_validate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "9";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = null;
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 10
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To = 20090109150000
     * this.ソートキー != nullであった場合、
     * ソートキー.validate()をコールする。 
     */
    public void test_validate_0015()
    {
        String STR_METHOD_NAME = "test_validate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "10";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        
        WEB3AdminEqAttentionInfoRefSortKey l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey();
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{l_sortKey};
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 11
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To = 20090109150000
     * this.ソートキー != nullであった場合、
     * this.表示ページ番号 == nullであった場合、 
     * 「表示ページ番号がnull」の例外をスローする。 
     */
    public void test_validate_0016()
    {
        String STR_METHOD_NAME = "test_validate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = null;
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 11
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To = 20090109150000
     * this.ソートキー != nullであった場合、
     * this.表示ページ番号が数字以外の値であった場合、 
     * 「表示ページ番号が数字以外」の例外をスローする。
     */
    public void test_validate_0017()
    {
        String STR_METHOD_NAME = "test_validate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "1";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "a";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 11
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To = 20090109150000
     * this.ソートキー != nullであった場合、
     * this.表示ページ番号 <= 0であった場合、 
     * 「表示ページ番号が0以下」の例外をスローする。 
     */
    public void test_validate_0018()
    {
        String STR_METHOD_NAME = "test_validate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "-1";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 11
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To = 20090109150000
     * this.ソートキー != nullであった場合、
     * this.表示ページ番号 <= 0であった場合、 
     * 「表示ページ番号が0以下」の例外をスローする。 
     */
    public void test_validate_0019()
    {
        String STR_METHOD_NAME = "test_validate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "0";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 11
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To = 20090109150000
     * this.ソートキー != nullであった場合、
     * this.表示ページ番号 = 1
     * this.ページ内表示行数 == nullであった場合、 
     * 「ページ内表示行数がnull」の例外をスローする。
     */
    public void test_validate_0020()
    {
        String STR_METHOD_NAME = "test_validate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "1";
        l_request.pageSize = null;
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 11
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To = 20090109150000
     * this.ソートキー != nullであった場合、
     * this.表示ページ番号 = 1
     * this.ページ内表示行数が数字以外の値であった場合、 
     * 「ページ内表示行数が数字以外」の例外をスローする。
     */
    public void test_validate_0021()
    {
        String STR_METHOD_NAME = "test_validate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "1";
        l_request.pageSize = "A";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 11
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To = 20090109150000
     * this.ソートキー != nullであった場合、
     * this.表示ページ番号 = 1
     * this.ページ内表示行数 <= 0であった場合、  
     * 「ページ内表示行数が0以下」の例外をスローする。 
     */
    public void test_validate_0022()
    {
        String STR_METHOD_NAME = "test_validate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "1";
        l_request.pageSize = "-1";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 11
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To = 20090109150000
     * this.ソートキー != nullであった場合、
     * this.表示ページ番号 = 1
     * this.ページ内表示行数 <= 0であった場合、  
     * 「ページ内表示行数が0以下」の例外をスローする。 
     */
    public void test_validate_0023()
    {
        String STR_METHOD_NAME = "test_validate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "1";
        l_request.pageSize = "0";
        try
        {
            l_request.validate();
            fail();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.市場コード = 11
     * this.銘柄コード = 11111
     * this.有効日 = 20020202
     * this.情報発生日時From = 20090109150000
     * this.情報発生日時To = 20090109150000
     * this.ソートキー != nullであった場合、
     * this.表示ページ番号 = 1
     * this.ページ内表示行数 = 1 
     */
    public void test_validate_0024()
    {
        String STR_METHOD_NAME = "test_validate_0024()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
        l_request.attentionInfoType = "1";
        l_request.attentionInfoDivCode = "A013";
        l_request.marketCode = "11";
        l_request.productCode = "11111";
        l_request.validDate = "20020202";
        l_request.infoOccuredDateFrom = "20090109150000";
        l_request.infoOccuredDateTo = "20090109150000";
        l_request.sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        try
        {
            l_request.validate();
        } catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
