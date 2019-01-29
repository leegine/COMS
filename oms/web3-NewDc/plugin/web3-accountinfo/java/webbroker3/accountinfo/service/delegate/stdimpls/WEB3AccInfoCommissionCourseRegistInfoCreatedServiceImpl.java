head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報委託手数料コース変更申込情報作成サービスImpl(WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報委託手数料コース変更申込情報作成サービスImpl)<BR>
 * お客様情報委託手数料コース変更申込情報作成サービス実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl implements WEB3AccInfoCommissionCourseRegistInfoCreatedService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl.class);
        
    /**
     * (create手数料コース変更申込情報)<BR>
     * 委託手数料コース変更申込オブジェクトより、<BR>
     * 手数料コース変更申込情報メッセージデータを作成する。<BR>
     * <BR>
     * 手数料コース変更申込情報インスタンスを生成、以下の通りプロパティに<BR>
     * セットを行い返却する。<BR>
     * <BR>
     * 　@手数料コース変更申込情報.ＩＤ = <BR>
     * 委託手数料コース変更申込.委託手数料コース変更申込ＩＤ<BR>
     * 　@手数料コース変更申込情報.部店コード = <BR>
     * 委託手数料コース変更申込.get部店().getBranchCode()<BR>
     * 　@手数料コース変更申込情報.顧客コード = <BR>
     * 委託手数料コース変更申込.get顧客().get表示顧客コード()<BR>
     * 　@手数料コース変更申込情報.商品コード = <BR>
     * 委託手数料コース変更申込.get手数料商品コード()<BR>
     * 　@手数料コース変更申込情報.手数料コース = <BR>
     * 委託手数料コース変更申込.get手数料コースコード()<BR>
     * 　@手数料コース変更申込情報.申込日 = <BR>
     * 委託手数料コース変更申込.get申込日()<BR>
     * 　@手数料コース変更申込情報.適用開始日 = <BR>
     * 委託手数料コース変更申込.get適用開始日()<BR>
     * 　@手数料コース変更申込情報.ダウンロード済フラグ = <BR>
     * 委託手数料コース変更申込.isダウンロード済()<BR>
     * 　@手数料コース変更申込情報.取消可能フラグ = <BR>
     * 委託手数料コース変更申込.is取消可能()<BR>
     * @@param l_commissionCourseRegist - 委託手数料コース変更申込オブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo
     * @@roseuid 413E8ADD0063
     */
    public WEB3AccInfoCommissionCourseChangeInfo createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist)";
        log.entering(STR_METHOD_NAME);
        
        if (l_commissionCourseRegist == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_commissionCourseRegist] = " + l_commissionCourseRegist
                );
        }
        
        WEB3AccInfoCommissionCourseChangeInfo l_commissionCourseChangeInfo = new WEB3AccInfoCommissionCourseChangeInfo();
        
        CommissionCourseRegistParams l_params = (CommissionCourseRegistParams)l_commissionCourseRegist.getDataSourceObject();
        
        //手数料コース変更申込情報.ＩＤ = 委託手数料コース変更申込.委託手数料コース変更申込ＩＤ
        l_commissionCourseChangeInfo.id = "" + l_params.getCommissionCourseRegistId();
        
        //手数料コース変更申込情報.部店コード = 委託手数料コース変更申込.get部店().getBranchCode()
        l_commissionCourseChangeInfo.branchCode = l_commissionCourseRegist.getBranch().getBranchCode();
        
        //手数料コース変更申込情報.顧客コード = 委託手数料コース変更申込.get顧客().get表示顧客コード()
        l_commissionCourseChangeInfo.accountCode = l_commissionCourseRegist.getMainAccount().getDisplayAccountCode();
        
        //手数料コース変更申込情報.商品コード = 委託手数料コース変更申込.get手数料商品コード()
        l_commissionCourseChangeInfo.instrumentsCode = l_commissionCourseRegist.getCommissionProductCode();
        
        //手数料コース変更申込情報.手数料コース = 委託手数料コース変更申込.get手数料コースコード()
        l_commissionCourseChangeInfo.commissionCourse = l_commissionCourseRegist.getCommissionCourseCode();
         
        //手数料コース変更申込情報.申込日 = 委託手数料コース変更申込.get申込日()
        l_commissionCourseChangeInfo.applyDate = l_commissionCourseRegist.getRegistDate();
         
        //手数料コース変更申込情報.適用開始日 = 委託手数料コース変更申込.get適用開始日()
        l_commissionCourseChangeInfo.trialStartDate = l_commissionCourseRegist.getAppliStartDate();
        
        //手数料コース変更申込情報.ダウンロード済フラグ = 委託手数料コース変更申込.isダウンロード済()
        l_commissionCourseChangeInfo.downloadFlag = l_commissionCourseRegist.isDownloaded();
         
        //手数料コース変更申込情報.取消可能フラグ = 委託手数料コース変更申込.is取消可能()
        l_commissionCourseChangeInfo.cancelFlag = l_commissionCourseRegist.isCancelPossible();
        
        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseChangeInfo;
    }
    
    /**
     * (create手数料コース変更申込情報)<BR>
     * 委託手数料コース変更申込オブジェクトの配列より、<BR>
     * 手数料コース変更申込情報メッセージデータの配列を作成する。<BR>
     * <BR>
     * 委託手数料コース変更申込オブジェクト配列各行について、<BR>
     * this.create手数料コース変更申込情報()をコールする。<BR>
     * <BR>
     * 　@[create手数料コース変更申込情報()に指定する引数]<BR>
     * 　@委託手数料コース変更申込：　@（処理対象要素）<BR>
     * <BR>
     * this.create手数料コース変更申込情報()の返却値を配列で返す。<BR>
     * @@param l_commissionCourseRegists - 委託手数料コース変更申込オブジェクトの配列
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo[]
     * @@roseuid 413E8FE2014D
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_commissionCourseRegists == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_commissionCourseRegists] = " + l_commissionCourseRegists
                );
        }
        
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseChangeInfoes = 
            new WEB3AccInfoCommissionCourseChangeInfo[l_commissionCourseRegists.length];
        
        for (int i = 0; i < l_commissionCourseRegists.length; i++) 
        {
            l_commissionCourseChangeInfoes[i] = createCommissionCourseRegistInfo(l_commissionCourseRegists[i]);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseChangeInfoes;
    }
    
    /**
     * (create株式手数料コース変更申込情報)<BR>
     * 委託手数料コース変更申込オブジェクトの配列より、<BR>
     * 上場株式，店頭株式の手数料コース変更申込情報メッセージデータの配列<BR>
     * を作成する。<BR>
     * <BR>
     * １）　@手数料変更申込情報一覧List（：ArrayList）生成<BR>
     * 　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@手数料コース変更申込情報生成<BR>
     * 　@引数の委託手数料コース変更申込オブジェクト配列各要素について、<BR>
     * ２－１），２－２）を実施する。　@<BR>
     * <BR>
     * 　@２－１）　@上場株式オブジェクト生成<BR>
     * 　@　@this.create手数料コース変更申込情報()をコールする。<BR>
     * <BR>
     * 　@　@[create手数料コース変更申込情報()に指定する引数]<BR>
     * 　@　@委託手数料コース変更申込：　@（処理対象要素）<BR>
     * <BR>
     * 　@２－２）　@手数料変更申込情報一覧List（：ArrayList）にオブジェクト追加<BR>
     * 　@　@２－１）で生成したオブジェクトを手数料変更申込情報一覧List<BR>
     * （：ArrayList）に追加（add）する。<BR>
     * <BR>
     * ３）　@配列返却<BR>
     * 　@手数料変更申込情報一覧List（：ArrayList）を配列に変換（toArray()）し、<BR>
     * 返却する。<BR>
     * @@param l_commissionCourseRegists - 委託手数料コース変更申込オブジェクトの配列
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo[]
     * @@roseuid 41511ADC02FB
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] createEquityCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createEquityCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_commissionCourseRegists == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_commissionCourseRegists] = " + l_commissionCourseRegists
                );
        }
        
        List l_lisCommissionCourseChangeInfoes = new ArrayList();
        
        for (int i = 0; i < l_commissionCourseRegists.length; i++) 
        {
            //上場株式オブジェクト生成
            WEB3AccInfoCommissionCourseChangeInfo l_commissionCourseChangeInfo = createCommissionCourseRegistInfo(l_commissionCourseRegists[i]);
            
            l_lisCommissionCourseChangeInfoes.add(l_commissionCourseChangeInfo);            
        }
        
        
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseChangeInfoes = 
            new WEB3AccInfoCommissionCourseChangeInfo[l_lisCommissionCourseChangeInfoes.size()];            
        l_lisCommissionCourseChangeInfoes.toArray(l_commissionCourseChangeInfoes);
        
        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseChangeInfoes;
    }
}
@
