head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.05.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・表示設定一覧リクエスト(WEB3AdminPvInfoConditionListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/25 李丁銀(中訊) 作成
Revesion History : 2004/11/2  魏馨(中訊) 修正
Revesion History : 2005/12/8 譚漢江(中訊) 仕様変更No.059修正
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・表示設定一覧リクエスト)<BR>
 * 管理者・表示設定一覧リクエストクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionListRequest extends WEB3GenRequest 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_conditionList";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (表示条件番号)<BR>
     * <BR>
     * 0000：　@ダイレクト指定<BR>
     * 1001：　@入金請求発生&信用口座開設<BR>
     * 1002：　@入金請求発生&信用口座未開設<BR>
     * 1003：　@立替金発生<BR>
     * 1004：　@立替金実績<BR>
     * 1005：　@証拠金不足<BR>
     * 1006：　@決済期限間近（一ヶ月前）の建玉保有<BR>
     * 1007：　@決済期限間近（一週間前）の建玉保有<BR>
     * 1008：　@信用口座開設<BR>
     * 1009：　@信用口座未開設<BR>
     * 1010：　@オプション口座開設<BR>
     * 1011：　@株式保有<BR>
     * 1012：　@信用建玉保有<BR>
     * 1013：　@投信保有<BR>
     * 1014：　@累投保有<BR>
     * 1015：　@オプション建玉保有<BR>
     * 1016：　@ミニ株保有<BR>
     * 1017：　@先物保有<BR>
     * 1018：　@預り金有り&証券残無し<BR>
     * 1019：　@預り金無し&証券残無し<BR>
     * 1020：　@株式・信用注文発生（当日）<BR>
     * 1021：　@株式・信用注文発生（翌日）<BR>
     * 1022：　@株式・信用約定発生<BR>
     * 1023：　@全顧客<BR>
     * 1024：　@メールアドレス未登録<BR>
     * 1025：　@IPO当選<BR>
     * 1026：　@IPO繰上げ当選<BR>
     * 1027：　@取引停止<BR>
     */
    public String conditionNumber;
    
    /**
     * (顧客コード)<BR>
     */
    public String accountCode;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     * ※未入力の場合は、PR層のセッションで保持している<BR>
     * 　@管理者取扱部店コードをセット。<BR>
     */
    public String[] branchCodeList;
    
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
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）顧客コードのチェック<BR>
     * 　@this.顧客コード != nullの場合で、<BR>
     * 　@以下の条件のいづれかに該当する場合は、<BR>
     * 　@「顧客コードエラー」の例外をスローする。<BR>
     * 　@　@・this.顧客コード.length != 6桁<BR>
     * 　@　@・this.顧客コード != 数値<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00780<BR>
     * 
     * ２）部店コードのチェック <BR>
     *  this.部店コード != nullの場合で、<BR> 
     *  this.部店コードの各要素が <BR>
     *  以下の条件のいづれかに該当する場合は、 <BR>
     *  「部店コードエラー」の例外をスローする。 <BR>
     *      ・this.部店コード != 数値 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01729<BR>
     *      ・this.部店コード.length != 3桁 <BR>
     *   class :  WEB3BusinessLayerException <BR>
     *   tag :   　@BUSINESS_ERROR_00834       
     * <BR> 
     * ３）要求ページ番号チェック<BR>
     *　@３－１）this.要求ページ番号 == nullであった場合、<BR>
     *　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     *　@３－２）this.要求ページ番号が数字以外の値であった場合、<BR>
     *　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     *　@３－３）this.要求ページ番号 <= 0であった場合、<BR>
     *　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ４）ページ内表示行数チェック<BR>
     *　@４－１）this.ページ内表示行数 == nullであった場合、<BR>
     *　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_02224<BR>
     * <BR>
     *　@４－２）this.ページ内表示行数が数字以外の値であった場合、<BR>
     *　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     *　@４－３）this.ページ内表示行数 <= 0であった場合、<BR>
     *　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00617<BR>
     * @@roseuid 415BEA4400F6
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）顧客コードのチェック
        if (this.accountCode != null)
        {
            int l_intAcountCode = WEB3StringTypeUtility.getByteLength(this.accountCode);
            boolean l_blnIsNumber = WEB3StringTypeUtility.isNumber(this.accountCode);
            if(l_intAcountCode != 6 || !l_blnIsNumber)
            {
                log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00780.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    getClass().getName() + "." + STR_METHOD_NAME);    
            }
        }
        //２）部店コードのチェック
        if (branchCodeList != null)
        {
            for (int i = 0; i < branchCodeList.length; i++)
            {
                if (!WEB3StringTypeUtility.isNumber(this.branchCodeList[i]))
                {
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                        getClass().getName() + "." + STR_METHOD_NAME); 
                }
                
                if (WEB3StringTypeUtility.getByteLength(this.branchCodeList[i]) != 3)
                {
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        getClass().getName() + "." + STR_METHOD_NAME); 
                }
            }
        }
        
        //３）要求ページ番号チェック
        //３－１）this.要求ページ番号 == nullであった場合
        if (null == this.pageIndex)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        
        //３－２）this.要求ページ番号が数字以外の値であった場合
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        
        //３－３）this.要求ページ番号 <= 0であった場合
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }
        
        //４）ページ内表示行数チェック
        //４－１）this.ページ内表示行数 == nullであった場合
        if (null == this.pageSize)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }
        
        //４－２）this.ページ内表示行数が数字以外の値であった場合
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        
        //４－３）this.ページ内表示行数 <= 0であった場合
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }
        
        log.exiting(STR_METHOD_NAME);       
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 417327C0000F
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPvInfoConditionListResponse(this);
    }
}
@
