head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.54.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾘｸｴｽﾄ(WEB3AdminMCCCOperatorListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 範慧琴 (中訊) 新規作成
*/


package webbroker3.adminmc.message;

import webbroker3.adminmc.define.WEB3AdminMCCCOperatorRegistUnitDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾘｸｴｽﾄ)<BR>
 * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾘｸｴｽﾄ<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorListRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (オペレータコード)<BR>
     * オペレータコード<BR>
     */
    public String operatorCode;
    
    /**
     * (オペレータ名)<BR>
     * オペレータ名<BR>
     */
    public String operatorName;
    
    /**
     * (代行注文可能区分)<BR>
     * 代行注文可能区分<BR>
     * <BR>
     * 0：　@不可<BR>
     * 1：　@可能<BR>
     */
    public String accountOrderDiv;
    
    /**
     * (所属コード)<BR>
     * 所属コード<BR>
     */
    public String departmentCode;
    
    /**
     * (エラー回数)<BR>
     * エラー回数<BR>
     * <BR>
     * ※ログインエラー回数<BR>
     * <BR>
     */
    public String errorCount;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;
    
    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3AdminMCSortKeyUnit[] sortKeys;
    
    /**
     * @@roseuid 41986426034B
     */
    public WEB3AdminMCCCOperatorListRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@文字数が3byteでない場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00834           <BR>
     * <BR>
     * ２）　@オペレータ名のチェック<BR>
     * 　@２－１）　@バイト数が40byteより大きい場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01194              <BR>
     * <BR>
     * ３）　@エラー回数のチェック<BR>
     * 　@３－１）　@数字でない場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01211           <BR>
     * 　@３－２）　@有効値が 0～9 の範囲内でない場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01212             <BR>
     * <BR>
     * ４）　@要求ページ番号チェック <BR>
     * 　@４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00090              <BR>
     * 　@４－３）　@マイナス値の場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00616            <BR>
     * <BR>
     * ５）　@ページ内表示行数チェック <BR>
     * 　@５－１）　@未入力の場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00091         <BR>
     * 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00092             <BR>
     * 　@５－３）　@マイナス値の場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00617          <BR>
     * <BR>
     * ６）　@ソートキーのチェック <BR>
     * 　@６－１）　@ソートキーが未入力lの場合、例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00231          <BR>
     * 　@６－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。 <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00232          <BR>
     * 　@６－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
     * 　@　@　@６－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@　@６－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。 <BR>
     * 　@　@　@　@ CCオペレータ登録情報.部店コード <BR>
     * 　@　@　@　@ CCオペレータ登録情報.オペレータコード <BR>
     * 　@　@　@　@ CCオペレータ登録情報.代行注文区分<BR>
     * 　@　@　@　@ CCオペレータ登録情報.所属コード<BR>
     * 　@　@　@　@ CCオペレータ登録情報.エラー回数<BR>
     * 　@　@　@　@ CCオペレータ登録情報.更新日時<BR>
     * 　@　@　@　@ CCオペレータ登録情報.更新者コード<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_00086             <BR>
     * <BR>
     * @@roseuid 417E22190037
     */
    public void validate() throws  WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@部店コードのチェック<BR>
        // 　@１－１）　@文字数が3byteでない場合、例外をスローする。<BR>

        if ((this.branchCode != null) && (!"".equals(this.branchCode)) && WEB3StringTypeUtility.getByteLength(this.branchCode) != 3)
        {
            log.error("「部店コードの文字数が3byteでない」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // ２）　@オペレータ名のチェック<BR>
        // 　@２－１）　@バイト数が40byteより大きい場合、例外をスローする。<BR>
        if ((this.operatorName != null) && (!"".equals(this.operatorName)) && WEB3StringTypeUtility.getByteLength(this.operatorName) > 40)
        {
            log.error("「オペレータ名のバイト数が40byteより大きい」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01194,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }

        // ３）　@エラー回数のチェック<BR>
        // 　@３－１）　@数字でない場合、例外をスローする。<BR>
        if ((this.errorCount != null) && (!"".equals(this.errorCount)) && !WEB3StringTypeUtility.isNumber(this.errorCount))
        {
            log.error("「エラー回数が数字でない」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01211,
                this.getClass().getName() + STR_METHOD_NAME);        
        }
        
        // 　@３－２）　@有効値が 0～9 の範囲内でない場合、例外をスローする。<BR>
        if ((this.errorCount != null) && (!"".equals(this.errorCount)) && ((Double.parseDouble(this.errorCount) < 0) || (Double.parseDouble(this.errorCount) > 9)))
        {
            log.error("「エラー回数の有効値が 0～9 の範囲内でない」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01212,
                this.getClass().getName() + STR_METHOD_NAME);              
        }
        
        // ４）　@要求ページ番号チェック <BR>
        // 　@４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            this.pageIndex = "1";
        }
        
        // 　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error(" 要求ページ番号が数字以外 。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                this.getClass().getName() + STR_METHOD_NAME);         
        }        
        // 　@４－３）　@マイナス値の場合、例外をスローする。 <BR>
        if (Double.parseDouble(this.pageIndex) <= 0)
        {
            log.error(" 要求ページ番号がマイナス値。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                this.getClass().getName() + STR_METHOD_NAME);         
        }         

        // ５）　@ページ内表示行数チェック <BR>
        // 　@５－１）　@未入力の場合、例外をスローする。 <BR>
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            log.error(" ページ内表示行数が未入力 。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                this.getClass().getName() + STR_METHOD_NAME);          
        }                 
        // 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.error(" ページ内表示行数が数字以外の文字が含まれる。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                this.getClass().getName() + STR_METHOD_NAME);         
        }         
        // 　@５－３）　@マイナス値の場合、例外をスローする。 <BR>
        if (Long.parseLong(this.pageSize) <= 0)
        {
             //例外
             log.error("ページ内表示行数がマイナス値。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                 this.getClass().getName() + STR_METHOD_NAME);         
        }        

        // ６）　@ソートキーのチェック <BR>
        // 　@６－１）　@ソートキーが未入力lの場合、例外をスローする。 <BR>
        if (this.sortKeys == null)
        {
            //例外
            log.error(" ソートキーが未入力である 。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME);               
        }

        // 　@６－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。 <BR>
        if (this.sortKeys.length == 0)
        {
            log.error(" ソートキーの要素数が0 ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME);
         
        }        
        // 　@６－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
        // 　@　@　@６－３－１）　@ソートキー.validate()をコールする。 <BR>
        // 　@　@　@６－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。 <BR>
        // 　@　@　@　@ CCオペレータ登録情報.部店コード <BR>
        // 　@　@　@　@ CCオペレータ登録情報.オペレータコード <BR>
        // 　@　@　@　@ CCオペレータ登録情報.代行注文区分<BR>
        // 　@　@　@　@ CCオペレータ登録情報.所属コード<BR>
        // 　@　@　@　@ CCオペレータ登録情報.エラー回数<BR>
        // 　@　@　@　@ CCオペレータ登録情報.更新日時<BR>
        // 　@　@　@　@ CCオペレータ登録情報.更新者コード<BR>
        int l_intLength = this.sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {
            this.sortKeys[i].validate();
            if (!WEB3AdminMCCCOperatorRegistUnitDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.OPERATOR_CODE.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.ACCOUNT_ORDER_DIV.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.DEPARTMENT_CODE.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.ERROR_COUNT.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.UPDATE_TIME_STAMP.equals(this.sortKeys[i].keyItem)&&
                !WEB3AdminMCCCOperatorRegistUnitDef.UPDATER_CODE.equals(this.sortKeys[i].keyItem))
            {
                log.error(" ソートキー.キー項目が未定義の値である ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME);            
            }
        }

        log.exiting(STR_METHOD_NAME);    
    }

    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41986426038A
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCCCOperatorListResponse(this);
    }
}
@
