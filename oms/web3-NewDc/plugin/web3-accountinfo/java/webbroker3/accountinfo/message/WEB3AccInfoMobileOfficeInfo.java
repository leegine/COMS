head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 携帯番号・勤務先情報(WEB3AccInfoMobileOfficeInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
                   2006/02/22 呉艶飛 (中訊) モデルNo.086
                   2006/03/20 呉艶飛 (中訊) モデルNo.098
                   2006/06/30 丁昭奎 (中訊) 仕様変更管理No.114     
                   2006/10/9  齊珂   (中訊) モデルNo.124     
                   2006/10/30 徐大方 (中訊) 仕様変更モデルNo.139
Revesion History : 2009/02/16 柴双紅 (中訊) モデルNo.256
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (携帯番号・勤務先情報)<BR>
 * 携帯番号・勤務先情報メッセージ<BR>
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeInfo extends Message 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeInfo.class);

    
    /**
     * (携帯番号)<BR>
     * 携帯番号<BR>
     * <BR>
     * ※　@"-"を含む文字列<BR>
     */
    public String mobileTelephone;
    
    /**
     * (勤務先名称)<BR>
     * 勤務先名称<BR>
     */
    public String officeName;
    
    /**
     * (勤務先郵便番号)<BR>
     * 勤務先郵便番号<BR>
     */
    public String officeZipCode;
    
    /**
     * (勤務先住所)<BR>
     * 勤務先住所<BR>
     */
    public String officeAdress;
    
    /**
     * (勤務先電話番号)<BR>
     * 勤務先電話番号<BR>
     * <BR>
     * ※　@"-"を含む文字列<BR>
     */
    public String officeTelephone;
    
    /**
     * (役職名)<BR>
     * 役職名<BR>
     */
    public String position;
    
    /**
     * (連絡先優先順位１位)<BR>
     * 連絡先優先順位１位<BR>
     * 0：なし <BR>
     * 1：自宅/本社代表番号 <BR>
     * 2：勤務先/取引責任者勤務先 <BR>
     * 3：携帯・その他/その他連絡先 <BR>
     * null：未設定<BR>
     */
    public String contactPriority1;
    
    /**
     * (連絡先優先順位２位)<BR>
     * 連絡先優先順位２位<BR>
     * 0：なし <BR>
     * 1：自宅/本社代表番号 <BR>
     * 2：勤務先/取引責任者勤務先 <BR>
     * 3：携帯・その他/その他連絡先 <BR>
     * null：未設定<BR>
     */
    public String contactPriority2;
    
    /**
     * (連絡先優先順位３位)<BR>
     * 連絡先優先順位３位<BR>
     * 0：なし <BR>
     * 1：自宅/本社代表番号 <BR>
     * 2：勤務先/取引責任者勤務先 <BR>
     * 3：携帯・その他/その他連絡先 <BR>
     * null：未設定<BR>
     */
    public String contactPriority3;
    
    /**
     * (所属)<BR>
     * 所属<BR>
     */
    public String department;
    
    /**
     * (FAX番号)<BR>
     * FAX番号<BR>
     */
    public String faxTelephone;
    
    /**
     * (年収)<BR>
     * 年収<BR>
     */
    public String annualIncomeDiv;
    
    /**
     * (金融資産額)<BR>
     * 金融資産額<BR>
     */
    public String assetValueDiv;
    
    /**
     * (運用予定額)<BR>
     * 運用予定額<BR>
     */
    public String fundBundgetAmountDiv;
    
    /**
     * (投資目的)<BR>
     * 投資目的<BR>
     */
    public String investPurposeDiv;
    
    /**
     * (投資予定期間)<BR>
     * 投資予定期間<BR>
     */
    public String investPlanPeriodDiv;
    
    /**
     * (投資経験の有無（１）)<BR>
     * 投資経験の有無（１）<BR>
     */
    public String experienceFlag1;
    
    /**
     * (投資経験の有無（２）)<BR>
     * 投資経験の有無（２）<BR>
     */
    public String experienceFlag2;
    
    /**
     * (投資経験の有無（３）)<BR>
     * 投資経験の有無（３）<BR>
     */
    public String experienceFlag3;
    
    /**
     * (投資経験の有無（４）)<BR>
     * 投資経験の有無（４）<BR>
     */
    public String experienceFlag4;
    
    /**
     * (投資経験の有無（５）)<BR>
     * 投資経験の有無（５）<BR>
     */
    public String experienceFlag5;
    
    /**
     * (投資経験の有無（６）)<BR>
     * 投資経験の有無（６）<BR>
     */
    public String experienceFlag6;
    
    /**
     * (投資経験の有無（７）)<BR>
     * 投資経験の有無（７）<BR>
     */
    public String experienceFlag7;
    
    /**
     * (投資経験の有無（８）)<BR>
     * 投資経験の有無（８）<BR>
     */
    public String experienceFlag8;
    
    /**
     * (投資経験の有無（９）)<BR>
     * 投資経験の有無（９）<BR>
     */
    public String experienceFlag9;
    
    /**
     * (投資経験の有無（１０）)<BR>
     * 投資経験の有無（１０）<BR>
     */
    public String experienceFlag10;
    
    /**
     * (投資経験（１）)<BR>
     * 投資経験（１）<BR>
     */
    public String experienceDiv1;
    
    /**
     * (投資経験（２）)<BR>
     * 投資経験（２）<BR>
     */
    public String experienceDiv2;
    
    /**
     * (投資経験（３）)<BR>
     * 投資経験（３）<BR>
     */
    public String experienceDiv3;
    
    /**
     * (投資経験（４）)<BR>
     * 投資経験（４）<BR>
     */
    public String experienceDiv4;
    
    /**
     * (投資経験（５）)<BR>
     * 投資経験（５）<BR>
     */
    public String experienceDiv5;
    
    /**
     * (投資経験（６）)<BR>
     * 投資経験（６）<BR>
     */
    public String experienceDiv6;
    
    /**
     * (投資経験（７）)<BR>
     * 投資経験（７）<BR>
     */
    public String experienceDiv7;
    
    /**
     * (投資経験（８）)<BR>
     * 投資経験（８）<BR>
     */
    public String experienceDiv8;
    
    /**
     * (投資経験（９）)<BR>
     * 投資経験（９）<BR>
     */
    public String experienceDiv9;
    
    /**
     * (投資経験（１０）)<BR>
     * 投資経験（１０）<BR>
     */
    public String experienceDiv10;
    
    /**
     * (取引の種類（１）)<BR>
     * 取引の種類（１）<BR>
     */
    public String interest1;
    
    /**
     * (取引の種類（２）)<BR>
     * 取引の種類（２）<BR>
     */
    public String interest2;
    
    /**
     * (取引の種類（３）)<BR>
     * 取引の種類（３）<BR>
     */
    public String interest3;
    
    /**
     * (取引の種類（４）)<BR>
     * 取引の種類（４）<BR>
     */
    public String interest4;
    
    /**
     * (取引の種類（５）)<BR>
     * 取引の種類（５）<BR>
     */
    public String interest5;
    
    /**
     * (取引の種類（６）)<BR>
     * 取引の種類（６）<BR>
     */
    public String interest6;
    
    /**
     * (取引の種類（７）)<BR>
     * 取引の種類（７）<BR>
     */
    public String interest7;
    
    /**
     * (取引の種類（８）)<BR>
     * 取引の種類（８）<BR>
     */
    public String interest8;
    
    /**
     * (取引の種類（９）)<BR>
     * 取引の種類（９）<BR>
     */
    public String interest9;
  
    /**
     * (取引の種類（１０）)<BR>
     * 取引の種類（１０）<BR>
     */
    public String interest10;
    
    /**
     * (口座開設の動機@)<BR>
     * 口座開設の動機@<BR>
     */
    public String appliMotivatDiv;
    
    /**
     * (口座開設の動機@の詳細)<BR>
     * 口座開設の動機@の詳細<BR>
     */
    public String appliMotivatDetail;
    
    /**
     * (現在利用している証券会社)<BR>
     * 現在利用している証券会社<BR>
     */
    public String useInstitutionDiv;
    
    /**
     * (インターネット取引区分)<BR>
     * インターネット取引区分<BR>
     */
    public String internetTradeDiv;
    
    /**
     * (紹介支店)<BR>
     * 紹介支店<BR>
     */
    public String introduceBranch;
    
    /**
     * (正式名称1)<BR>
     * 正式名称1<BR>
     */
    public String accountRealName1;
    
    /**
     * (正式名称2)<BR>
     * 正式名称2<BR>
     */
    public String accountRealName2;
    
    /**
     * (職業)<BR>
     * 職業<BR>
     */
    public String occupationDiv;
    
    /**
     * (国籍)<BR>
     * 国籍<BR>
     */
    public String nationality;
    
    /**
     * (国籍その他)<BR>
     * 国籍その他<BR>
     */
    public String nationalityOther;
    
    /**
     * (代表者名（漢字）姓)<BR>
     * 代表者名（漢字）姓<BR>
     */
    public String representFamilyName;
    
    /**
     * (代表者名（漢字）名)<BR>
     * 代表者名（漢字）名<BR>
     */
    public String representName;
    
    /**
     * (代表者名（カナ）姓)<BR>
     * 代表者名（カナ）姓<BR>
     */
    public String representFamilyNameKana;
    
    /**
     * (代表者名（カナ）名)<BR>
     * 代表者名（カナ）名<BR>
     */
    public String representNameKana;
    
    /**
     * (代表者権)<BR>
     * 代表者権<BR>
     */
    public String representPower;
    
    /**
     * (取引責任者名（漢字）姓)<BR>
     * 取引責任者名（漢字）姓<BR>
     */
    public String directorFamilyName;
    
    /**
     * (取引責任者名（漢字）名)<BR>
     * 取引責任者名（漢字）名<BR>
     */
    public String directorName;
    
    /**
     * (取引責任者名（カナ）姓)<BR>
     * 取引責任者名（カナ）姓<BR>
     */
    public String directorFamilyNameKana;
    
    /**
     * (取引責任者名（カナ）名)<BR>
     * 取引責任者名（カナ）名<BR>
     */
    public String directorNameKana;
    
    /**
     * (取引責任者所属部署)<BR>
     * 取引責任者所属部署<BR>
     */
    public String directorDepartment;
    
    /**
     * (取引責任者役職)<BR>
     * 取引責任者役職<BR>
     */
    public String directorPosition;
    
    /**
     * (取引責任者郵便番号)<BR>
     * 取引責任者郵便番号<BR>
     */
    public String directorZipCode;
    
    /**
     * (取引責任者住所1)<BR>
     * 取引責任者住所1<BR>
     */
    public String directorAddress1;
    
    /**
     * (取引責任者住所2)<BR>
     * 取引責任者住所2<BR>
     */
    public String directorAddress2;
    
    /**
     * (取引責任者住所3)<BR>
     * 取引責任者住所3<BR>
     */
    public String directorAddress3;
    
    /**
     * (取引責任者生年月日年号)<BR>
     * 取引責任者生年月日年号<BR>
     */
    public String directorEraBorn;
    
    /**
     * (取引責任者生年月日)<BR>
     * 取引責任者生年月日<BR>
     */
    public String directorBornDate;
    
    /**
     * (取引責任者会社直通番号)<BR>
     * 取引責任者会社直通番号<BR>
     */
    public String directorCorpDirect;
    
    /**
     * (その他連絡先(携帯、自宅等))<BR>
     * その他連絡先(携帯、自宅等)<BR>
     */
    public String directorOtherContact;

    /**
     * (携帯番号・勤務先情報)<BR>
     * デフォルトコンストラクタ<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo
     * @@roseuid 413FFE2002DB
     */
    public WEB3AccInfoMobileOfficeInfo() 
    {
     
    }
    
    /**
     * 携帯番号・勤務先情報データの整合性をチェックする。<BR>
     * <BR>
     * １）　@携帯番号のチェック<BR>
     * 　@入力がある場合は、以下のチェックを行う。<BR>
     * <BR>
     * 　@１－１）　@文字列に半角数字とハイフン文字（’-’）<BR>
     *   以外の文字が含まれる場合は、<BR>
     *   ハイフン（’-’）が２つ含まれていることをチェックする。<BR>
     * 　@（ハイフンの数が２つでない場合、例外をスローする。）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01113<BR>
     * <BR>
     * ２）　@勤務先名称のチェック<BR>
     * 　@入力がある場合は、以下のチェックを行う。<BR>
     * <BR>
     * 　@２－１）　@文字数が25（50byte）より大きい場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01116<BR>
     * <BR>
     * ３）　@勤務先郵便番号のチェック<BR>
     * 　@入力がある場合は、以下のチェックを行う。<BR>
     * <BR>
     * 　@３－１）　@半角数字以外の文字が含まれる場合は、例外をスローする。<BR>
     * 　@３－２）　@文字数が7byteであること。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01117<BR>
     * <BR>
     * ４）　@勤務先住所のチェック<BR>
     * 　@入力がある場合は、以下のチェックを行う。<BR>
     * <BR>
     * 　@４－１）　@文字数が50（100byte）より大きい場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01120<BR>
     * <BR>
     * ５）　@勤務先電話番号のチェック<BR>
     * 　@入力がある場合は、以下のチェックを行う。<BR>
     * <BR>
     * 　@５－１）　@文字列に半角数字とハイフン文字（’-’）<BR>
     *    以外の文字が含まれる場合は、<BR>
     *    ハイフン（’-’）が２つ含まれていることをチェックする。<BR>
     *   （ハイフンの数が２つでない場合、例外をスローする。）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01121<BR>
     * <BR>
     * ６）　@役職名のチェック<BR>
     * 　@入力がある場合は、以下のチェックを行う。<BR>
     * <BR>
     * 　@６－１）　@文字数が18（36byte）より大きい場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01124<BR>
     * ７）　@FAX番号のチェック <BR>
     * 　@入力がある場合は、以下のチェックを行う。<BR>
     * <BR>
     * 　@７－１）　@文字列に半角数字とハイフン文字（’-’）以外の文字が含まれる場合は、
     * 　@　@　@　@　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02658<BR>
     * 　@７－２）　@ハイフン（’-’）が２つ含まれていることをチェックする。<BR>
     * 　@　@　@　@（ハイフンの数が２つでない場合、例外をスローする。）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02671<BR>
     * <BR>
     * ８）　@口座開設の動機@の詳細のチェック <BR>
     * 　@入力がある場合は、以下のチェックを行う。<BR>
     * <BR>
     * 　@８－１）　@文字数が25（50byte）より大きい場合は、例外をスローする。<BR>  
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02659<BR>
     * <BR>
     * ９）　@取引責任者郵便番号のチェック<BR> 
     * 　@入力がある場合は、以下のチェックを行う。<BR> 
     * <BR> 
     *　@９－１）　@半角数字以外の文字が含まれる場合は、例外をスローする。<BR> 
     *　@９－２）　@文字数が7byteであること。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02682<BR>
     * <BR> 
     * １０）　@取引責任者年号+生年月日のチェック<BR> 
     * 　@入力がある場合は、以下のチェックを行う。<BR> 
     * <BR> 
     * 　@１０－１）　@「年号.toDate(取引責任者 年号 , 取引責任者生年月日)」<BR>
     * 　@　@　@　@　@の戻り値がnullの場合は例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02687<BR>
     * <BR>  
     * １１）　@取引責任者会社直通番号のチェック<BR> 
     * 　@入力がある場合は、以下のチェックを行う。<BR> 
     * <BR> 
     * 　@１１－１）　@文字列に半角数字とハイフン文字（’-’）以外の文字が含まれる場合は、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02683<BR>
     * 　@１１－２）　@ハイフン（’-’）が２つ含まれていることをチェックする。<BR> 
     * 　@　@　@　@　@　@（ハイフンの数が２つでない場合、例外をスローする。）<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02684<BR>
     * <BR> 
     * １２）　@その他連絡先(携帯、自宅等)番号のチェック<BR> 
     * 　@入力がある場合は、以下のチェックを行う。<BR> 
     * <BR> 
     * 　@１２－１）　@文字列に半角数字とハイフン文字（’-’）以外の文字が含まれる場合は、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02685<BR>
     * 　@１２－２）　@ハイフン（’-’）が２つ含まれていることをチェックする。<BR> 
     * 　@　@　@　@　@　@（ハイフンの数が２つでない場合、例外をスローする。）<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02686<BR>
     * <BR> 
     * @@throws WEB3BaseException
     * @@roseuid 414022C5022F
     */
    public void validate() throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@携帯番号のチェック<BR>
        //入力がある場合は、以下のチェックを行う。
        if(this.mobileTelephone  != null)
        {
            //１－１）　@文字列に半角数字とハイフン文字（’-’）以外の文字が含まれる場合は、例外をスローする。
            //ハイフン（’-’）が２つ含まれていることをチェックする。（ハイフンの数が２つでない場合、例外をスローする。）
            if(!WEB3StringTypeUtility.isPhoneNumber(this.mobileTelephone))
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01113,
                    this.getClass().getName(),
                    " 携帯番号が半角数字とハイフン文字（’-’）ではありません");
            }            
        }

        // ２）　@勤務先名称のチェック<BR>
        //入力がある場合は、以下のチェックを行う。
        if(this.officeName != null)
        {
                          
            //２－1）　@文字数が25（50byte）より大きい場合は、例外をスローする。
            if(WEB3StringTypeUtility.getByteLength(this.officeName) > 50)
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01116,
                    this.getClass().getName(),
                    " 勤務先名称の文字数が25（50byte）より大きいです");
            }
        }
        
        //
        //３）　@勤務先郵便番号のチェック
        //  入力がある場合は、以下のチェックを行う。
        if(this.officeZipCode != null)
        {
            
            //３－１）　@半角数字以外の文字が含まれる場合は、例外をスローする。
            //３－２）　@文字数が7byteであること。
            if(!WEB3StringTypeUtility.isZipCode(this.officeZipCode))
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01117,
                    this.getClass().getName(),
                    " 勤務先郵便番号が7byte半角数字ではありません。");
            }
        }
        
        //４）　@勤務先住所のチェック<BR>
        //入力がある場合は、以下のチェックを行う。<BR>
        if(this.officeAdress != null)
        {
            
            
            //４－1）　@文字数が50（100byte）より大きい場合は、例外をスローする。
            if(WEB3StringTypeUtility.getByteLength(this.officeAdress) > 100)
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01120,
                    this.getClass().getName(),
                    " 勤務先住所の文字数が50（100byte）より大きいです");
            }            
        }
        
        //５）　@勤務先電話番号のチェック<BR>
        //入力がある場合は、以下のチェックを行う。
        if(this.officeTelephone != null)
        {
            
            //５－１）　@文字列に半角数字とハイフン文字（’-’）以外の文字が含まれる場合は、例外をスローする。
            //ハイフン（’-’）が２つ含まれていることをチェックする。（ハイフンの数が２つでない場合、例外をスローする。）
            if(!WEB3StringTypeUtility.isPhoneNumber(this.officeTelephone))
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01121,
                    this.getClass().getName(),
                    " 勤務先電話番号が半角数字とハイフン文字（’-’）ではありません");
            }
        }
        //６）　@役職名のチェック<BR>
        //入力がある場合は、以下のチェックを行う。<BR>
        if(this.position != null)
        {
                     
            //６－1）　@文字数が18（36byte）より大きい場合は、例外をスローする。
            if(WEB3StringTypeUtility.getByteLength(this.position) > 36)
            {               
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01124,
                    this.getClass().getName(),
                    " 役職名の文字数が18（36byte）より大きいです");
            }
        }
        
        //７）　@FAX番号のチェック 
        if (this.faxTelephone != null)
        {
            //７－１）　@文字列に半角数字とハイフン文字（’-’）以外の文字が含まれる場合は、例外をスローする。
            String l_strFaxTelephone = this.faxTelephone;
            if (!WEB3StringTypeUtility.isSingle(l_strFaxTelephone))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02658,
                    this.getClass().getName(),
                    " FAX番号が半角数字とハイフン文字（’-’）ではありません。");
            }
            else
            {
                int l_intLen = WEB3StringTypeUtility.getByteLength(l_strFaxTelephone);
                for (int i = 0; i < l_intLen; i++) 
                {
                    char l_ch = l_strFaxTelephone.charAt(i);
                    if (!(WEB3StringTypeUtility.isSingleNum(l_ch)
                        || (l_ch == '-')))
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02658,
                            this.getClass().getName(),
                            " FAX番号が半角数字とハイフン文字（’-’）ではありません。");
                    }
                }
            }
            
            //７－２）　@ハイフン（’-’）が２つ含まれていることをチェックする。
            //（ハイフンの数が２つでない場合、例外をスローする。） 
            if (!WEB3StringTypeUtility.isPhoneNumber(this.faxTelephone))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02671,
                    this.getClass().getName(),
                    " ハイフンの数が２つではありません。");
            }
        	
        }
         
        //８）　@口座開設の動機@の詳細のチェック
        //入力がある場合は、以下のチェックを行う。 
        //８－１）　@文字数が25（50byte）より大きい場合は、例外をスローする。
        if (this.appliMotivatDetail != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.appliMotivatDetail) > 50)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02659,
                    this.getClass().getName(),
                    "口座開設の動機@の詳細の文字数が25（50byte）より大きいです。");
            }
        }       
        
        //９）取引責任者郵便番号のチェック  
        //入力がある場合は、以下のチェックを行う。  
        if(this.directorZipCode != null)
        {
            //９－１）　@半角数字以外の文字が含まれる場合は、例外をスローする。
            //９－２）　@文字数が7byteであること。
            if(!WEB3StringTypeUtility.isZipCode(this.directorZipCode))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02682,
                    this.getClass().getName(),
                    "取引責任者郵便番号が7byte半角数字ではありません。");
            }
        }
        
        //10）取引責任者年号+生年月日のチェック
        //入力がある場合は、以下のチェックを行う。
        if (this.directorEraBorn != null && this.directorBornDate != null)
        {
            //１０－１）　@「年号.toDate(取引責任者 年号 , 取引責任者生年月日)」
            //の戻り値がnullの場合は例外をスローする。  
            if (WEB3GentradeEra.toDate(this.directorEraBorn, this.directorBornDate) == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02687,
                    this.getClass().getName(),
                    "取引責任者 年号かつ取引責任者生年月日が未指定(null)または不正な値です。");
            }
        }
        
        //１１）　@取引責任者会社直通番号のチェック  
        //入力がある場合は、以下のチェックを行う。
        if (this.directorCorpDirect != null)
        {
            //１１－１）　@文字列に半角数字とハイフン文字（’-’）以外の文字が含まれる場合は、例外をスローする。
            String l_strDirectorCorpDirecte = this.directorCorpDirect;
            if (!WEB3StringTypeUtility.isSingle(l_strDirectorCorpDirecte))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02683,
                    this.getClass().getName(),
                    "取引責任者会社直通番号が半角数字とハイフン文字（’-’）ではありません。");
            }
            else
            {
                int l_intLen = WEB3StringTypeUtility.getByteLength(l_strDirectorCorpDirecte);
                for (int i = 0; i < l_intLen; i++) 
                {
                    char l_ch = l_strDirectorCorpDirecte.charAt(i);
                    if (!(WEB3StringTypeUtility.isSingleNum(l_ch)
                        || (l_ch == '-')))
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02683,
                            this.getClass().getName(),
                            "取引責任者会社直通番号が半角数字とハイフン文字（’-’）ではありません。");
                    }
                }
            }
            //１１－２）　@ハイフン（’-’）が２つ含まれていることをチェックする。  
            //ハイフンの数が２つでない場合、例外をスローする。）  
            if (!WEB3StringTypeUtility.isPhoneNumber(this.directorCorpDirect))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02684,
                    this.getClass().getName(),
                    "ハイフンの数が２つではありません。");
            }
        }
        
        //１２）　@その他連絡先(携帯、自宅等)番号のチェック  
        //入力がある場合は、以下のチェックを行う。  
        if (this.directorOtherContact != null)
        {
            //１２－１）　@文字列に半角数字とハイフン文字（’-’）以外の文字が含まれる場合は、例外をスローする。
            String l_strDirectorOtherContact = this.directorOtherContact;
            if (!WEB3StringTypeUtility.isSingle(l_strDirectorOtherContact))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02685,
                    this.getClass().getName(),
                    "その他連絡先(携帯、自宅等)番号が半角数字とハイフン文字（’-’）ではありません。");
            }
            else
            {
                int l_intLen = WEB3StringTypeUtility.getByteLength(l_strDirectorOtherContact);
                for (int i = 0; i < l_intLen; i++) 
                {
                    char l_ch = l_strDirectorOtherContact.charAt(i);
                    if (!(WEB3StringTypeUtility.isSingleNum(l_ch)
                        || (l_ch == '-')))
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02685,
                            this.getClass().getName(),
                            "その他連絡先(携帯、自宅等)番号が半角数字とハイフン文字（’-’）ではありません。");
                    }
                }
            }
            //１２－２）　@ハイフン（’-’）が２つ含まれていることをチェックする。  
            //（ハイフンの数が２つでない場合、例外をスローする。）  
            if (!WEB3StringTypeUtility.isPhoneNumber(this.directorOtherContact))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02686,
                    this.getClass().getName(),
                    "ハイフンの数が２つではありません。");
            }
        }
        log.exiting(STR_METHOD_NAME);     
    }
}
@
