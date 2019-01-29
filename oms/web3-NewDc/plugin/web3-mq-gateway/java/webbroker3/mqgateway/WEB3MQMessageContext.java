head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQMessageContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3MQMessageContextクラス(WEB3MQMessageContext.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/08 山田　@卓司(FLJ) 新規作成
                 : 2005/03/07 山田　@卓司(FLJ) 顧客コード（自至）をlongからLongに変更
 */
package webbroker3.mqgateway;

import java.util.Date;

/**
 * ユーザーデータ部に追加する共通のコンテキスト情報
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3MQMessageContext
{
    
    /**
     * SONAR会社コード
     */
    private final String institutionCode;
    
    /**
     * Oracle SID
     */
    private final String sid;
    
    /**
     * 顧客コード（自）
     */
    private final Long accountIdStart;
    
    /**
     * 顧客コード（至）
     */
    private final Long accountIdEnd;
    
    /**
     * 営業日
     */
    private final Date bizDate;
    
    /**
     * コンストラクタ
     * 
     * @@param l_strInstitutionCode 会社コード
     * @@param l_strSid OracleSID
     * @@param l_lngAccountIdStart 顧客コード（自）
     * @@param l_lngAccountIdEnd 顧客コード（至）
     * @@param l_datBizDate 営業日
     */
    public WEB3MQMessageContext(
            String l_strInstitutionCode,
            String l_strSid,
            Long l_lngAccountIdStart,
            Long l_lngAccountIdEnd,
            Date l_datBizDate)
    {
        this.institutionCode = l_strInstitutionCode;
        this.sid = l_strSid;
        this.accountIdStart = l_lngAccountIdStart;
        this.accountIdEnd = l_lngAccountIdEnd;
        this.bizDate = l_datBizDate;
    }

    /**
     * コンストラクタ
     * 
     * @@param l_strInstitutionCode 会社コード
     * @@param l_strSid OracleSID
     * @@param l_lngAccountIdStart 顧客コード（自）
     * @@param l_lngAccountIdEnd 顧客コード（至）
     * @@param l_datBizDate 営業日
     * @@deprecated 顧客コード（自）、顧客コード（至）をLongで設定するコンストラクタを使用。
     */
    public WEB3MQMessageContext(
            String l_strInstitutionCode, 
            String l_strSid,
            long l_lngAccountIdStart, 
            long l_lngAccountIdEnd, 
            Date l_datBizDate)
    {
        this(l_strInstitutionCode, 
            l_strSid, 
            new Long(l_lngAccountIdStart),
            new Long(l_lngAccountIdEnd), 
            l_datBizDate);
    }
    
    /**
     * 顧客コード（自)を取得する。 顧客コード（自)がnullの場合、0を返す。
     * 
     * @@return accountIdStart を取得する。
     */
    public long getAccountIdStart()
    {
        return !getAccountIdStartIsNull() ? accountIdStart.longValue() : 0L;
    }
    
    /**
     * 顧客コード（至)を取得する。
     * 顧客コード（至)がnullの場合、0を返す。
     * 
     * @@return accountIdEnd を取得する。
     */
    public long getAccountIdEnd()
    {
        return !getAccountIdEndIsNull() ? accountIdEnd.longValue() : 0L;
    }
    
    /**
     * @@return bizDate を取得する。
     */
    public Date getBizDate()
    {
        return bizDate;
    }
    
    /**
     * @@return institutionCode を取得する。
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }
    
    /**
     * @@return sid を取得する。
     */
    public String getSid()
    {
        return sid;
    }
    
    /**
     * 顧客コード（自)がnullの場合trueを返す。
     */
    public boolean getAccountIdStartIsNull()
    {
        return accountIdStart == null;
    }
    
    /**
     * 顧客コード（至)がnullの場合trueを返す。
     */
    public boolean getAccountIdEndIsNull()
    {
        return accountIdEnd == null;
    }
    
}
@
