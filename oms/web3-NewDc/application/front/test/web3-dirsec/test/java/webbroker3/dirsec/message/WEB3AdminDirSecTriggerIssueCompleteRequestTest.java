head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecTriggerIssueCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecTriggerIssueCompleteRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueCompleteRequestTest.class);

    WEB3AdminDirSecTriggerIssueCompleteRequest l_request;

    public WEB3AdminDirSecTriggerIssueCompleteRequestTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminDirSecTriggerIssueCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * トリガー発行レコード詳細 != null の場合に、下記の処理を行う。
     * シェル名称チェック
     * トリガー発行レコード詳細.シェル名称 == null or
     * トリガー発行レコード詳細.シェル名称 == "" の場合、
     * 例外をスローする。
     */
    public void testValidate_Case279()
    {
        final String STR_METHOD_NAME = "testValidate_Case279";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03071, l_ex.getErrorInfo());
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
     * シェル名称チェック
     * トリガー発行レコード詳細.シェル名称 == null or
     * トリガー発行レコード詳細.シェル名称 == "" の場合、
     * 例外をスローする。
     */
    public void testValidate_Case280()
    {
        final String STR_METHOD_NAME = "testValidate_Case280";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03071, l_ex.getErrorInfo());
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
     * トリガー名称チェック
     * トリガー発行レコード詳細.トリガー名称 == null or
     * トリガー発行レコード詳細.トリガー名称 == " の場合、
     * 例外をスローする。
     */
    public void testValidate_Case281()
    {
        final String STR_METHOD_NAME = "testValidate_Case281";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "A";
            l_request.triggerIssueInfo.triggerName = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03072, l_ex.getErrorInfo());
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
     * トリガー名称チェック
     * トリガー発行レコード詳細.トリガー名称 == null or
     * トリガー発行レコード詳細.トリガー名称 == "" の場合、
     * 例外をスローする。
     */
    public void testValidate_Case282()
    {
        final String STR_METHOD_NAME = "testValidate_Case282";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "A";
            l_request.triggerIssueInfo.triggerName = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03072, l_ex.getErrorInfo());
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
     * 再発行可能フラグチェック
     * トリガー発行レコード詳細.再発行可能フラグ == null or
     * トリガー発行レコード詳細.再発行可能フラグ == "" の場合、
     * 例外をスローする。
     */
    public void testValidate_Case283()
    {
        final String STR_METHOD_NAME = "testValidate_Case283";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "A";
            l_request.triggerIssueInfo.triggerName = "AB";
            l_request.triggerIssueInfo.reissuePossibleFlag = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03073, l_ex.getErrorInfo());
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
     * 再発行可能フラグチェック
     * トリガー発行レコード詳細.再発行可能フラグ == null or
     * トリガー発行レコード詳細.再発行可能フラグ == "" の場合、
     * 例外をスローする。
     */
    public void testValidate_Case284()
    {
        final String STR_METHOD_NAME = "testValidate_Case284";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "A";
            l_request.triggerIssueInfo.triggerName = "AB";
            l_request.triggerIssueInfo.reissuePossibleFlag = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03073, l_ex.getErrorInfo());
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
     * ユーザーデータチェック
     * トリガー発行レコード詳細.ユーザーデータ == "" の場合、
     * 例外をスローする。
     */
    public void testValidate_Case285()
    {
        final String STR_METHOD_NAME = "testValidate_Case285";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "A";
            l_request.triggerIssueInfo.triggerName = "AB";
            l_request.triggerIssueInfo.reissuePossibleFlag = "ABC";
            l_request.triggerIssueInfo.userData = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03074, l_ex.getErrorInfo());
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
     * ユーザーデータチェック
     * トリガー発行レコード詳細.ユーザーデータ == "" の場合、
     * 例外をスローする。トリガー発行レコード詳細.ユーザーデータ == null不抛出BUSINESS_ERROR_03074
     */
    public void testValidate_Case286()
    {
        final String STR_METHOD_NAME = "testValidate_Case286";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "A";
            l_request.triggerIssueInfo.triggerName = "AB";
            l_request.triggerIssueInfo.reissuePossibleFlag = "ABC";
            l_request.triggerIssueInfo.userData = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02828, l_ex.getErrorInfo());
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
     * データコードチェック
     * トリガー発行レコード詳細.データコード == null or
     * トリガー発行レコード詳細.データコード == "" の場合、例外をスローする。
     */
    public void testValidate_Case287()
    {
        final String STR_METHOD_NAME = "testValidate_Case287";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "A";
            l_request.triggerIssueInfo.triggerName = "AB";
            l_request.triggerIssueInfo.reissuePossibleFlag = "ABC";
            l_request.triggerIssueInfo.userData = "ABCD";
            l_request.triggerIssueInfo.dataCode = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02828, l_ex.getErrorInfo());
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
     * データコードチェック
     * トリガー発行レコード詳細.データコード == null or
     * トリガー発行レコード詳細.データコード == "" の場合、例外をスローする。
     */
    public void testValidate_Case288()
    {
        final String STR_METHOD_NAME = "testValidate_Case288";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "A";
            l_request.triggerIssueInfo.triggerName = "AB";
            l_request.triggerIssueInfo.reissuePossibleFlag = "ABC";
            l_request.triggerIssueInfo.userData = "ABCD";
            l_request.triggerIssueInfo.dataCode = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02828, l_ex.getErrorInfo());
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
     * トリガー発行レコード詳細 == null  の場合、
     * 「レコードが存在しません。」例外をスローする。
     */
    public void testValidate_Case289()
    {
        final String STR_METHOD_NAME = "testValidate_Case289";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837, l_ex.getErrorInfo());
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
     * 暗証番号チェック
     * this.暗証番号 == null or this.暗証番号 == ""の場合、
     * 「暗証番号が不正です。」の例外をスローする。
     */
    public void testValidate_Case290()
    {
        final String STR_METHOD_NAME = "testValidate_Case290";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "A";
            l_request.triggerIssueInfo.triggerName = "AB";
            l_request.triggerIssueInfo.reissuePossibleFlag = "ABC";
            l_request.triggerIssueInfo.userData = "ABCD";
            l_request.triggerIssueInfo.dataCode = "ABCDE";
            l_request.password = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02832, l_ex.getErrorInfo());
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
     * 暗証番号チェック
     * this.暗証番号 == null or this.暗証番号 == ""の場合、
     * 「暗証番号が不正です。」の例外をスローする。
     */
    public void testValidate_Case291()
    {
        final String STR_METHOD_NAME = "testValidate_Case291";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "A";
            l_request.triggerIssueInfo.triggerName = "AB";
            l_request.triggerIssueInfo.reissuePossibleFlag = "ABC";
            l_request.triggerIssueInfo.userData = "ABCD";
            l_request.triggerIssueInfo.dataCode = "ABCDE";
            l_request.password = "";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02832, l_ex.getErrorInfo());
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
     * トリガー発行レコード詳細 != null
     * 全部正常
     */
    public void testValidate_Case292()
    {
        final String STR_METHOD_NAME = "testValidate_Case292";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "A";
            l_request.triggerIssueInfo.triggerName = "AB";
            l_request.triggerIssueInfo.reissuePossibleFlag = "ABC";
            l_request.triggerIssueInfo.userData = "ABCD";
            l_request.triggerIssueInfo.dataCode = "ABCDE";
            l_request.password = "ABCDEF";
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
