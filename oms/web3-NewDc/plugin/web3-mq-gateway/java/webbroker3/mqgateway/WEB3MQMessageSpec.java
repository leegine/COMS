head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQMessageSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : MQに送信するメッセージのスペックを表すクラス(WEB3MQMessageSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2003/02/13 山田　@卓司(FLJ) 新規作成
                 : 2004/11/11 山田　@卓司(FLJ) プロパティ.ユーザーデータを追加
                 : 2005/03/07 山田　@卓司(FLJ) デバック用にtoString()メソッドを実装
*/
package webbroker3.mqgateway;

/**
 * <p>
 * MQに送信するメッセージのスペックを表すクラス。<br>
 * <br>
 * WEB3MQMessageGatewayでMQに送信するメッセージを生成するのに、<br>
 * 必要な以下の情報を保持する。<br>
 * <ul>
 *   <li>会社コード</li>
 *   <li>データコード</li>
 *   <li>ユーザーデータ</li>
 * </ul>
 * </p>
 * 
 * @@see webbroker3.mqgateway.WEB3MQGatewayService#send(WEB3MQMessageSpec)
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3MQMessageSpec
{

    /**
     * 会社コード
     */
    private final String institutionCode;

    /**
     * データコード
     */
    private final String dataCode;
    
    /**
     * ユーザーデータ
     */
    private final String userData;
    
    /**
     * コンストラクタ
     * 
     * @@param institutionCode 会社コード
     * @@param dataCode データコード
     */
    public WEB3MQMessageSpec(
        String institutionCode,
        String dataCode)
    {
        this.institutionCode = institutionCode;
        this.dataCode = dataCode;
        this.userData = null;
    }
    
    /**
     * コンストラクタ
     * 
     * @@param institutionCode 会社コード
     * @@param dataCode データコード
     * @@param userData ユーザーコード
     */
    public WEB3MQMessageSpec(
        String institutionCode,
        String dataCode,
        String userData)
    {
        this.institutionCode = institutionCode;
        this.dataCode = dataCode;
        this.userData = userData;
    }

    /**
     * 会社コードを取得する。
     * 
     * @@return 会社コード
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }

    /**
     * データコードを取得する。
     * 
     * @@return データコード
     */
    public String getDataCode()
    {
        return dataCode;
    }
    
    /**
     * ユーザーデータを取得する。
     * 
     * @@return ユーザーデータ
     */
    public String getUserData()
    {
        return userData;
    }
    
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("{");
        l_sb.append("institutionCode=").append(getInstitutionCode());
        l_sb.append(",dataCode=").append(getDataCode());
        l_sb.append(",userData=").append(getUserData());
        l_sb.append("}");
        return l_sb.toString();
    }
    
}
@
