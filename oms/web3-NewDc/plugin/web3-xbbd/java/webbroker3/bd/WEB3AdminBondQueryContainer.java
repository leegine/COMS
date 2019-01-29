head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondQueryContainer.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 検索条件コンテナ(WEB3AdminBondQueryContainer.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏 (中訊) 新規作成
*/

package webbroker3.bd;


/**
 * (検索条件コンテナ)<BR>
 * 検索条件コンテナクラス<BR>
 * <BR>
 * 検索条件文字列と検索条件データを保持するコンテナ
 * 
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminBondQueryContainer 
{
    
    /**
     * (検索条件文字列)<BR>
     * 検索条件文字列。(SQLのwhere句)
     */
    private String queryString;
    
    /**
     * (条件データ配列)<BR>
     * 条件データ配列
     */
    private Object[] queryData;
    
    /**
     * (検索条件コンテナ)<BR>
     * コンストラクタ。
     * @@roseuid 44CFF7C80126
     */
    public WEB3AdminBondQueryContainer() 
    {
     
    }
    
    /**
     * (get検索条件文字列)<BR>
     * get検索条件文字列<BR>
     * <BR>
     * 　@検索条件文字列を返す
     * @@return String
     * @@roseuid 44D41BE8003D
     */
    public String getQueryString() 
    {
        return this.queryString;
    }
    
    /**
     * (set検索条件文字列)<BR>
     * set検索条件文字列<BR>
     * <BR>
     * 　@検索条件文字列をセットする
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列
     * @@roseuid 44D41C0F000F
     */
    public void  setQueryString(String l_strQueryString) 
    {
        this.queryString = l_strQueryString;
    }
    
    /**
     * (get検索条件データ)<BR>
     * get検索条件データ<BR>
     * <BR>
     * 　@検索条件データを返す
     * @@return String
     * @@roseuid 44D41C3900F9
     */
    public Object[] getQueryData() 
    {
        return this.queryData;
    }
    
    /**
     * (set検索条件データ)<BR>
     * set検索条件データ<BR>
     * <BR>
     * 　@検索条件データをセットする
     * @@param l_objQueryData - (検索条件データ)<BR>
     * 検索条件データ
     * @@roseuid 44D41C390128
     */
    public void setQueryData(Object[] l_objQueryData) 
    {
        this.queryData = l_objQueryData;
    }
}
@
