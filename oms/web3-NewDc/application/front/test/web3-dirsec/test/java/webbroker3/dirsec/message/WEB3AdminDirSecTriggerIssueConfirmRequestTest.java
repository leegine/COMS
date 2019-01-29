head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.59.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecTriggerIssueConfirmRequestTest.java;


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

public class WEB3AdminDirSecTriggerIssueConfirmRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueConfirmRequestTest.class);
    
    WEB3AdminDirSecTriggerIssueConfirmRequest l_request;

    public WEB3AdminDirSecTriggerIssueConfirmRequestTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminDirSecTriggerIssueConfirmRequest();
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
    public void testValidate_Case266()
    {
        final String STR_METHOD_NAME = "testValidate_Case266";
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
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * トリガー発行レコード詳細 != null の場合に、下記の処理を行う。
     * シェル名称チェック
     * トリガー発行レコード詳細.シェル名称 == null or
     * トリガー発行レコード詳細.シェル名称 == "" の場合、
     * 例外をスローする。
     */
    public void testValidate_Case267()
    {
        final String STR_METHOD_NAME = "testValidate_Case267";
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
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * トリガー発行レコード詳細 != null の場合に、下記の処理を行う。
     * トリガー名称チェック
     * トリガー発行レコード詳細.トリガー名称 == null or
     * トリガー発行レコード詳細.トリガー名称 == " の場合、
     * 例外をスローする。
     */
    public void testValidate_Case268()
    {
        final String STR_METHOD_NAME = "testValidate_Case268";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "abc";
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
            log.error(l_ex.getMessage(), l_ex);
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
    public void testValidate_Case269()
    {
        final String STR_METHOD_NAME = "testValidate_Case269";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "abc";
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
            log.error(l_ex.getMessage(), l_ex);
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
    public void testValidate_Case270()
    {
        final String STR_METHOD_NAME = "testValidate_Case270";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "abc";
            l_request.triggerIssueInfo.triggerName = "abcd";
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
            log.error(l_ex.getMessage(), l_ex);
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
    public void testValidate_Case271()
    {
        final String STR_METHOD_NAME = "testValidate_Case271";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "abc";
            l_request.triggerIssueInfo.triggerName = "abcd";
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
            log.error(l_ex.getMessage(), l_ex);
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
    public void testValidate_Case272()
    {
        final String STR_METHOD_NAME = "testValidate_Case272";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "abc";
            l_request.triggerIssueInfo.triggerName = "abcd";
            l_request.triggerIssueInfo.reissuePossibleFlag = "abcde";
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
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ユーザーデータチェック
     * トリガー発行レコード詳細.ユーザーデータ == null の場合不抛BUSINESS_ERROR_03074
     */
    public void testValidate_Case273()
    {
        final String STR_METHOD_NAME = "testValidate_Case273";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "abc";
            l_request.triggerIssueInfo.triggerName = "abcd";
            l_request.triggerIssueInfo.reissuePossibleFlag = "abcde";
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
            log.error(l_ex.getMessage(), l_ex);
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
    public void testValidate_Case274()
    {
        final String STR_METHOD_NAME = "testValidate_Case274";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "abc";
            l_request.triggerIssueInfo.triggerName = "abcd";
            l_request.triggerIssueInfo.reissuePossibleFlag = "abcde";
            l_request.triggerIssueInfo.userData = "abcdef";
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
            log.error(l_ex.getMessage(), l_ex);
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
    public void testValidate_Case275()
    {
        final String STR_METHOD_NAME = "testValidate_Case275";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "abc";
            l_request.triggerIssueInfo.triggerName = "abcd";
            l_request.triggerIssueInfo.reissuePossibleFlag = "abcde";
            l_request.triggerIssueInfo.userData = "abcdef";
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
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 全部正常
     */
    public void testValidate_Case276()
    {
        final String STR_METHOD_NAME = "testValidate_Case276";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_request.triggerIssueInfo.shellName = "abc";
            l_request.triggerIssueInfo.triggerName = "abcd";
            l_request.triggerIssueInfo.reissuePossibleFlag = "abcde";
            l_request.triggerIssueInfo.userData = "abcdef";
            l_request.triggerIssueInfo.dataCode = "abcdefg";
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * トリガー発行レコード詳細 == null  の場合、
     * 「レコードが存在しません。」例外をスローする。
     */
    public void testValidate_Case277()
    {
        final String STR_METHOD_NAME = "testValidate_Case277";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerIssueInfo = null;
            l_request.validate();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
