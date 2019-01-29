head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCorporationInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 法@人情報(WEB3AccInfoCorporationInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/30 丁昭奎 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (法@人情報)<BR>
 * 法@人情報<BR>
 * 
 * @@author 丁昭奎
 * @@version 1.0
 */
public class WEB3AccInfoCorporationInfo extends Message 
{     
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
     * (代表者権)<BR>
     * 代表者権<BR>
     */
    public String representPower;
    
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
     * (取引責任者・所属部署)<BR>
     * 取引責任者・所属部署<BR>
     */
    public String directorDepartment;
    
    /**
     * (取引責任者・役職)<BR>
     * 取引責任者・役職<BR>
     */
    public String directorPosition;
    
    /**
     * (取引責任者・生年月日年号)<BR>
     * 取引責任者・生年月日年号<BR>
     */
    public String directorEraBorn;
    
    /**
     * (取引責任者・生年月日)<BR>
     * 取引責任者・生年月日<BR>
     */
    public String directorBornDate;
    
    /**
     * (取引責任者・郵便番号)<BR>
     * 取引責任者・郵便番号<BR>
     */
    public String directorZipCode;
    
    /**
     * (取引責任者・住所1)<BR>
     * 取引責任者・住所1<BR>
     */
    public String directorAddress1;
    
    /**
     * (取引責任者・住所2)<BR>
     * 取引責任者・住所2<BR>
     */
    public String directorAddress2;
    
    /**
     * (取引責任者・住所3)<BR>
     * 取引責任者・住所3<BR>
     */
    public String directorAddress3;
    
    /**
     * (取引責任者・その他連絡先)<BR>
     * 取引責任者・その他連絡先<BR>
     */
    public String directorOtherContact;
    
    /**
     * (取引責任者・会社直通電話)<BR>
     * 取引責任者・会社直通電話<BR>
     */
    public String directorCorpDirect;
    
    /**
     * (法@人情報)<BR>
     * デフォルトコンストラクタ
     */
    public WEB3AccInfoCorporationInfo()
    {
     
    }
    
}
@
