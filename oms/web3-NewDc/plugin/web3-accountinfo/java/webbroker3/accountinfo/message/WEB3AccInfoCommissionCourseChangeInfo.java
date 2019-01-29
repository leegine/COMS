head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCourseChangeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料コース変更申込情報(WEB3AccInfoCommissionCourseChangeInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 彭巍 (中訊) 新規作成
Revesion History : 2008/08/22 張少傑 (中訊) 仕様変更・モデルNo.248
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (手数料コース変更申込情報)<BR>
 * 手数料コース変更申込情報メッセージクラス<BR>
 * 
 * @@author　@彭巍
 * @@version 1.0
 */
public class WEB3AccInfoCommissionCourseChangeInfo extends Message 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommissionCourseChangeInfo.class);
    
    /**
     * (ＩＤ)<BR>
     * 委託手数料コース変更申込ＩＤ<BR>
     */
    public String id;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (商品コード)<BR> 
     * 商品コード（手数料商品コード）<BR>
     * <BR>
     * 10：　@上場株式<BR>
     * 12：　@ミニ株式<BR>
     * 40：　@外国株式<BR>
     * 50：　@先物<BR>
     * 51：　@オプション<BR>
     */
    public String instrumentsCode;
    
    /**
     * (手数料コース)<BR>
     * (手数料コース（手数料コースコード）)<BR>
     * <BR>
     * 02：　@定率手数料（スタンダード） <BR>
     * （現物1日注文毎＋信用1日注文毎　@※リテラのみ）<BR> 
     * 03：　@約定代金合計<BR> 
     * （現物1日合計＋信用1日合計　@※リテラのみ）<BR> 
     * 04：　@約定回数 <BR>
     * 05：　@一日定額制<BR> 
     * 06：　@少額ボックス<BR> 
     * 07：　@現物1日合計＋信用1日注文毎<BR> 
     * 08：　@現物1日注文毎＋信用1日合計<BR> 
     * 16：　@少額ボックス（キャンペーン）<BR> 
     * 99：　@上記以外（リテラ・岩井のみ）<BR> 
     * <BR>
     * ※　@各コードの名称については、証券会社によって違う。<BR> 
     * 　@　@Web層にて、名称に変換する。<BR>
     */
    public String commissionCourse;
    
    /**
     * (申込日)<BR>
     * 申込日<BR>
     */
    public Date applyDate;
    
    /**
     * (適用開始日)<BR>
     * 適用開始日<BR>
     */
    public Date trialStartDate;
    
    /**
     * (ダウンロード済フラグ)<BR>
     * ダウンロード済フラグ<BR>
     * <BR>
     * true：ダウンロード済　@false：ダウンロード未済<BR>
     */
    public boolean downloadFlag;
    
    /**
     * (取消可能フラグ)<BR>
     * 取消可能フラグ<BR>
     * <BR>
     * true：　@取消可能　@false：　@取消不可<BR>
     */
    public boolean cancelFlag;
    
    /**
     * (手数料コース変更申込情報)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo
     * @@roseuid 413DB1440151
     */
    public WEB3AccInfoCommissionCourseChangeInfo() 
    {
     
    }
    
    /**
     * 自身のコピーを作成し返却する。<BR>
     * <BR>
     * 手数料コース変更申込情報を生成する。<BR>
     * 生成したオブジェクトに引数の商品コードをセットする。<BR>
     * 以外のプロパティは、自身と同じ値を生成したオブジェクトにセットし返却する。<BR>
     * @@param l_strProductCode - 商品コード
     * @@return Object
     * @@roseuid 413EE5E90109
     */
    public Object clone(String l_strProductCode) 
    {
        final String STR_METHOD_NAME = " clone(String l_strProductCode)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoCommissionCourseChangeInfo l_accInfo = new WEB3AccInfoCommissionCourseChangeInfo();
        l_accInfo.accountCode = this.accountCode;
        l_accInfo.applyDate = this.applyDate;
        l_accInfo.branchCode = this.branchCode;
        l_accInfo.cancelFlag = this.cancelFlag;
        l_accInfo.commissionCourse = this.commissionCourse;
        l_accInfo.downloadFlag = this.downloadFlag;
        l_accInfo.id = this.id;
        l_accInfo.trialStartDate = this.trialStartDate;
        
        if (l_strProductCode.equals(WEB3CommisionProductCodeDef.LISTING_STOCK) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.MINI_STOCK) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.FOREIGN_EQITY) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.INDEX_FUTURES) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.INDEX_OP))
            
        {
            l_accInfo.instrumentsCode = l_strProductCode;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_accInfo;

    }
}
@
