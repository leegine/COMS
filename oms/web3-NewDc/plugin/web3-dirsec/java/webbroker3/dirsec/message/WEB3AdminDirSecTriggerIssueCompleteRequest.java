head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・トリガー発行処理完了リクエスト(WEB3AdminDirSecTriggerIssueCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 許丹(中訊) 新規作成 モデルNo.116、No.118、No.120
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・トリガー発行処理完了リクエスト)<BR>
 * 管理者・トリガー発行処理完了リクエストクラス。<BR>
 * <BR>
 * @@author 許丹
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueCompleteRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dir_sec_trigger_issue_complete";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804171357L;

    /**
     * (トリガー発行情報)<BR>
     * 「トリガー発行情報テーブル」から取得したトリガー発行JOBレコードを保持する。<BR>
     */
    public WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo;

    /**
     * (暗証番号)<BR>
     * 画面から入力した暗証番号を保持。<BR>
     */
    public String password;

    /**
     * @@roseuid 4806E0540179
     */
    public WEB3AdminDirSecTriggerIssueCompleteRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）トリガー発行レコード詳細 != null の場合に、下記の処理を行う。<BR>
     * <BR>
     * 　@１－１）シェル名称チェック<BR>
     * 　@　@　@　@　@トリガー発行レコード詳細.シェル名称 == null or<BR>
     * 　@　@　@　@　@トリガー発行レコード詳細.シェル名称 == "" の場合、<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_03071<BR>
     * <BR>
     * 　@１－２）トリガー名称チェック<BR>
     * 　@　@　@　@　@トリガー発行レコード詳細.トリガー名称 == null or<BR>
     * 　@　@　@　@　@トリガー発行レコード詳細.トリガー名称 == "" の場合、<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_03072<BR>
     * <BR>
     * 　@１－３）再発行可能フラグチェック<BR>
     * 　@　@　@　@　@トリガー発行レコード詳細.再発行可能フラグ == null or<BR>
     * 　@　@　@　@　@トリガー発行レコード詳細.再発行可能フラグ == "" の場合、<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_03073<BR>
     * <BR>
     * 　@１－４）ユーザーデータチェック<BR>
     * 　@　@　@　@　@トリガー発行レコード詳細.ユーザーデータ == "" の場合、<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_03074<BR>
     * <BR>
     * 　@１－５）データコードチェック<BR>
     * 　@　@　@　@　@トリガー発行レコード詳細.データコード == null or<BR>
     * 　@　@　@　@　@トリガー発行レコード詳細.データコード == "" の場合、<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_02828<BR>
     * <BR>
     * ２）トリガー発行レコード詳細 == null の場合、<BR>
     * 　@　@「レコードが存在しません。」例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_02837<BR>
     * <BR>
     * ３）　@暗証番号チェック<BR>
     * 　@３-１）　@this.暗証番号 == null or this.暗証番号 == ""の場合、<BR>
     * 　@　@　@　@　@「暗証番号が不正です。」の例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_02832<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47CFAB3401DD
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //トリガー発行レコード詳細 != null の場合に、下記の処理を行う。
        if (this.triggerIssueInfo != null)
        {
            //シェル名称チェック
            //トリガー発行レコード詳細.シェル名称 == null or
            //トリガー発行レコード詳細.シェル名称 == "" の場合、
            //例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.triggerIssueInfo.shellName))
            {
                log.debug("シェル名称が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03071,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "シェル名称が未指定です。");
            }

            //トリガー名称チェック
            //トリガー発行レコード詳細.トリガー名称 == null or
            //トリガー発行レコード詳細.トリガー名称 == "" の場合、
            //例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.triggerIssueInfo.triggerName))
            {
                log.debug("トリガー名称が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03072,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "トリガー名称が未指定です。");
            }

            //再発行可能フラグチェック
            //トリガー発行レコード詳細.再発行可能フラグ == null or
            //トリガー発行レコード詳細.再発行可能フラグ == "" の場合、
            //例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.triggerIssueInfo.reissuePossibleFlag))
            {
                log.debug("再発行可能フラグが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03073,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "再発行可能フラグが未指定です。");
            }

            //ユーザーデータチェック
            //トリガー発行レコード詳細.ユーザーデータ == "" の場合、
            //例外をスローする。
            if ("".equals(this.triggerIssueInfo.userData))
            {
                log.debug("ユーザーデータが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03074,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ユーザーデータが未指定です。");
            }

            //データコードチェック
            //トリガー発行レコード詳細.データコード == null or
            //トリガー発行レコード詳細.データコード == "" の場合、
            //例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.triggerIssueInfo.dataCode))
            {
                log.debug("データコードが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02828,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "データコードが不正です。");
            }

        }

        //トリガー発行レコード詳細 == null の場合、
        //「レコードが存在しません。」例外をスローする。
        if (this.triggerIssueInfo == null)
        {
            log.debug("レコードが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "レコードが存在しません。");
        }

        //暗証番号チェック
        //this.暗証番号 == null or this.暗証番号 == ""の場合、
        //「暗証番号が不正です。」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.password))
        {
            log.debug("暗証番号が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02832,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "暗証番号が不正です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecTriggerIssueCompleteResponse(this);
    }
}
@
