head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpExcutionInformUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QtpExcutionInformUnitクラス(WEB3QtpExcutionInformUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/10 孫(FLJ) 新規作成
                 : 2009/06/03 毛(FTL) 岩井対応
*/

package webbroker3.rcp.service.delegate.stdimpls;

/**
 * 一件約定通知データ保存　@オブジェクト
 * 
 * @@author 孫(FLJ)
 * @@version 1.0
 */
public class WEB3QtpExcutionInformUnit
{
 
    /** 要素　@ルート・通知電文・通知ヘッダの通知電文通番属性 */
    private String srlnum;

    /** 要素　@ルート・通知電文・通知ヘッダの送信先ＩＤ属性*/
    private String sid;

    /** 要素　@ルート・通知電文・タイムスタンプ属性 */
    private String tm;

    /**
     * 要素　@ルート・通知電文・・リンク先属性ののCONTENTS=:<br/>
     * viewデータ order_type | URL | 売買の表示<br/>
     * 001：現物買注文 | CONTENTS=URL4 | 現物/買付<br/>
     * 002：現物売注文 | CONTENTS=URL4 | 現物/売却<br/>
     * 003：新規買建注文 | CONTENTS=URL10 | 信用/買建<br/>
     * 004：新規売建注文 | CONTENTS=URL10 | 信用/売建<br/>
     * 005：買建返済注文（売返済） | CONTENTS=URL10 | 信用/買建返済<br/>
     * 006：売建返済注文（買返済） | CONTENTS=URL10 | 信用/売建返済<br/>
     * 007：現引注文 | CONTENTS=URL10 | 信用/現引<br/>
     * 008：現渡注文 | CONTENTS=URL10 | 信用/現渡<br/>
     * 601：先物新規買建注文 | CONTENTS=URL15 | 先物/買建<br/>
     * 602：先物新規売建注文 | CONTENTS=URL15 | 先物/売建<br/>
     * 603：先物売建買返済注文（買返済） | CONTENTS=URL15 | 先物/売建返済<br/>
     * 604：先物買建売返済注文（売返済） | CONTENTS=URL15 | 先物買建返済<br/>
     * 605：OP新規買建注文 | CONTENTS=URL20 | OP/買建<br/>
     * 606：OP新規売建注文 | CONTENTS=URL20 | OP/売建<br/>
     * 607：OP売建買返済注文（買返済） | CONTENTS=URL20 | OP/売建返済<br/>
     * 608：OP買建売返済注文（売返済） | CONTENTS=URL20 | OP/買建返済<br/>
     */
    private String url;

    /** 要素　@ルート・通知電文・通知メッセージ・通知メッセージタイトル属性 */
    private String title;

    /**
     * 要素　@ルート・通知電文・・リンク先属性のの,QUOTE=:<br/>
     * 銘柄コード（QUOTE）の編集方法@：<br/>
     * 証券固有コード / 取引所・取引種別コード<br/>
     * （取引所・取引種別コード設定しない場合、/ も省略）
     */
    private String urlParams;

    /**
     * 要素　@ルート・通知電文・通知メッセージ・約定銘柄情報・銘柄コード属性<br/>
     * 参照urlParams
     */
    private String qcodeParams;

    /**
     * 要素　@ルート・通知電文・通知メッセージ・約定銘柄情報の本文<br/>
     * viewデータ.銘柄名 + ( + 銘柄コード + / + 市場名 +)
     */
    private String qcodeName;

    /**
     * 要素　@ルート・通知電文・通知メッセージ・単価の説明<br/>
     * 例：　@【信用/買建　@単価：】
     */
    private String orderTypeName;

    /** 要素　@ルート・通知電文・通知メッセージ・単価の本文 */
    private String prc;

    /**
     * 要素　@ルート・通知電文・通知メッセージ・数量の説明<br/>
     * 例：　@【数量：】
     */
    private String quantityText;

    /** 要素　@ルート・通知電文・通知メッセージ・数量の本文 */
    private String vol;

    /**
     * 要素　@ルート・通知電文・通知メッセージ・リンクの本文<br/>
     * 例：　@＜注文照会画面＞
     * 
     */
    private String lnkText;

    /** 要素　@ルート・通知電文・通知メッセージ・フリー・フリー属性 */
    private String tlgNtcNmsgFtagFatt;

    /**
     * @@return lnkText を戻します。
     */
    public String getLnkText()
    {
        return lnkText;
    }
    /**
     * @@param lnkText lnkText を設定。
     */
    public void setLnkText(String lnkText)
    {
        this.lnkText = lnkText;
    }
    /**
     * @@return vol を戻します。
     */
    public String getVol()
    {
        return vol;
    }
    /**
     * @@param vol vol を設定。
     */
    public void setVol(String vol)
    {
        this.vol = vol;
    }
    /**
     * @@return orderTypeName を戻します。
     */
    public String getOrderTypeName()
    {
        return orderTypeName;
    }
    /**
     * @@param orderTypeName orderTypeName を設定。
     */
    public void setOrderTypeName(String buySellText)
    {
        this.orderTypeName = buySellText;
    }

    /**
     * @@return prc を戻します。
     */
    public String getPrc()
    {
        return prc;
    }
    /**
     * @@param prc prc を設定。
     */
    public void setPrc(String prc)
    {
        this.prc = prc;
    }

    /**
     * @@return qcodeName を戻します。
     */
    public String getQcodeName()
    {
        return qcodeName;
    }
    /**
     * @@param qcodeName qcodeName を設定。
     */
    public void setQcodeName(String qcodeName)
    {
        this.qcodeName = qcodeName;
    }

    /**
     * @@return quantityText を戻します。
     */
    public String getQuantityText()
    {
        return quantityText;
    }
    /**
     * @@param quantityText quantityText を設定。
     */
    public void setQuantityText(String quantityText)
    {
        this.quantityText = quantityText;
    }
    /**
     * @@return sid を戻します。
     */
    public String getSid()
    {
        return sid;
    }
    /**
     * @@param sid sid を設定。
     */
    public void setSid(String sid)
    {
        this.sid = sid;
    }
    /**
     * @@return srlnum を戻します。
     */
    public String getSrlnum()
    {
        return srlnum;
    }
    /**
     * @@param srlnum srlnum を設定。
     */
    public void setSrlnum(String srlnum)
    {
        this.srlnum = srlnum;
    }
    /**
     * @@return title を戻します。
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @@param title title を設定。
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    /**
     * @@return tm を戻します。
     */
    public String getTm()
    {
        return tm;
    }
    /**
     * @@param tm tm を設定。
     */
    public void setTm(String tm)
    {
        this.tm = tm;
    }
    /**
     * @@return url を戻します。
     */
    public String getUrl()
    {
        return url;
    }
    /**
     * @@param url url を設定。
     */
    public void setUrl(String url)
    {
        this.url = url;
    }
    /**
     * @@return qcodeQuote を戻します。
     */
    public String getQcodeParams()
    {
        return qcodeParams;
    }
    /**
     * @@param qcodeQuote qcodeQuote を設定。
     */
    public void setQcodeParams(String qcodeParams)
    {
        this.qcodeParams = qcodeParams;
    }
    /**
     * @@return urlQuote を戻します。
     */
    public String getUrlParams()
    {
        return urlParams;
    }
    /**
     * @@param urlQuote urlQuote を設定。
     */
    public void setUrlParams(String urlParams)
    {
        this.urlParams = urlParams;
    }

    /**
     * 要素　@ルート・通知電文・通知メッセージ・フリー・フリー属性を取得する
     * @@return java.lang.String
     */
    public String getTlgNtcNmsgFtagFatt()
    {
        return tlgNtcNmsgFtagFatt;
    }

    /**
     * 要素　@ルート・通知電文・通知メッセージ・フリー・フリー属性を設定する
     * @@param l_value
     */
    public void setTlgNtcNmsgFtagFatt(String l_value)
    {
        this.tlgNtcNmsgFtagFatt = l_value;
    }

    /**
     * 一意特定できるためにの実装。
     * 
     * srlnum　@と　@sid　@が同じであれば、同じの約定通知オブジェクトとする
     * 
     */
    public boolean equals(Object l_obj)
    {
        boolean l_result = true;
        if(!(l_obj instanceof WEB3QtpExcutionInformUnit))
        {
            return false;
        }
        WEB3QtpExcutionInformUnit l_tar = (WEB3QtpExcutionInformUnit)l_obj;

        boolean l_res1=false;
        if(srlnum ==null)
        {
            if(l_tar.getSrlnum() == null)
            {
                l_res1 = true;
            }
        }
        else
        {
            if(srlnum.equals(l_tar.getSrlnum()))
            {
                l_res1 = true;
            }
        }

        boolean l_res2 = false;
        if(sid ==null)
        {
            if(l_tar.getSid() == null)
            {
                l_res2 = true;
            }
        }
        else
        {
            if(sid.equals(l_tar.getSid()))
            {
                l_res2 = true;
            }
        }

        return l_res1 && l_res2;
    }

    /**
     * 一意特定できるためにの実装。
     * 
     * srlnum　@と　@sid　@が同じであれば、同じの約定通知オブジェクトとする
     * 
     */
    public int hashCode()
    {
        StringBuffer l_sb = new StringBuffer();

        if(srlnum!=null)
        {
            l_sb.append(srlnum);
        }
        if(sid!=null)
        {
            l_sb.append(sid);
        }

        return l_sb.toString().hashCode();
    }

    /**
     * 内容の出力
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3QtpExcutionInformUnit, values [");
        l_sb.append("srlnum=").append(srlnum).append(",");
        l_sb.append("sid=").append(sid).append(",");
        l_sb.append("tm=").append(tm).append(",");
        l_sb.append("url=").append(url).append(",");
        l_sb.append("title=").append(title).append(",");
        l_sb.append("urlParams=").append(urlParams).append(",");
        l_sb.append("qcodeParams=").append(qcodeParams).append(",");
        l_sb.append("qcodeName=").append(qcodeName).append(",");
        l_sb.append("orderTypeName=").append(orderTypeName).append(",");
        l_sb.append("prc=").append(prc).append(",");
        l_sb.append("quantityText=").append(quantityText).append(",");
        l_sb.append("vol=").append(vol).append(",");
        l_sb.append("lnkText=").append(lnkText).append(",");
        l_sb.append("tlgNtcNmsgFtagFatt=").append(tlgNtcNmsgFtagFatt);
        l_sb.append("]");

        return l_sb.toString();
    }
}
@
