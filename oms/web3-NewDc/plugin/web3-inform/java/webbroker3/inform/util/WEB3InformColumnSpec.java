head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformColumnSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright                : (株)大和総研 証券ソリューションシステム第二部
File Name                : (WEB3InformColumnSpec)
Author Name           : Daiwa Institute of Research
Revesion History     : 2005/01/25 王暁傑(中訊) 作成
*/
package webbroker3.inform.util;

import com.fitechlabs.dbind.gen.ColumnSpec;

import webbroker3.common.define.WEB3ItemCheckModeDef;

/**
 * @@author 王暁傑
 * @@version 1.0
 *
 */
public class WEB3InformColumnSpec extends ColumnSpec
{
    /**
     * 列名(漢字)
     */   
    private String columnNameKana;
    
    /**
     * 説明（有効コード、意味）
     */   
    private String explanation;   

    /**
     * 項目チェック方式（WEB3デフォルト）
     */   
    private boolean isCustomizeAble;
    
    /**
     * 項目チェック方式（WEB3デフォルト）
     */   
    private String defaultCheckMode;
    
    /**
     * コンストラクタ
     * 
     * @@param l_colunmSpec - ColunmSpec
     * @@param l_strColunmName - 列物理名
     * @@param l_strColumnNameKana - 列名(漢字)
     * @@param l_strDefaultCheckMode - 項目チェック方式（WEB3デフォルト）
     * @@param l_strExplanation - 説明（有効コード、意味
     */
    public WEB3InformColumnSpec(ColumnSpec l_colunmSpec, 
            String l_strColunmName, 
            String l_strColumnNameKana,
            String l_strExplanation,
            boolean isCustomizeAble,
            String l_strDefaultCheckMode
            )
    {
        super(l_strColunmName, 
                l_colunmSpec.getSqlType(), 
                l_colunmSpec.columnSize(), 
                l_colunmSpec.decimalDigits(), 
                l_colunmSpec.getIsNullable(),
                l_colunmSpec.getIsPrimaryKeyComponent(), 
                l_colunmSpec.getForeignTableName(), 
                l_colunmSpec.getForeignColumnName());
        
        this.columnNameKana = l_strColumnNameKana;        
        this.explanation = l_strExplanation;
        this.isCustomizeAble = isCustomizeAble;
        this.defaultCheckMode = l_strDefaultCheckMode;
    }
    
    /**
     * get列名(漢字)<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public String getColumnNameKana() 
    {
        return this.columnNameKana;
    }
    
    /**
     * set列名(漢字)<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public void setColumnNameKana(String l_strColumnNameKana) 
    {
        this.columnNameKana = l_strColumnNameKana;
    }
    
    /**
     * get項目チェック方式（WEB3デフォルト）<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public String getDefaultCheckMode() 
    {
        return this.defaultCheckMode;
    }
    
    /**
     * set項目チェック方式（WEB3デフォルト）<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public void setDefaultCheckMode(String l_strDefaultCheckMode) 
    {
        this.defaultCheckMode = l_strDefaultCheckMode;
    }
    
    /**
     * get説明（有効コード、意味）<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public String getExplanation() 
    {
        return this.explanation;
    }
    
    /**
     * set説明（有効コード、意味）<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public void setExplanation(String l_strExplanation) 
    {
        this.explanation = l_strExplanation;
    }
    
    /**
     * isカスタマイズ可能<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public boolean isCustomizeAble() 
    {
        return this.isCustomizeAble;
    }
    
    /**
     * is項目チェック方式設定<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public boolean isDefaultCheckModeSet() 
    {
        if (this.defaultCheckMode == null || WEB3ItemCheckModeDef.DEFAULT.equals(this.defaultCheckMode))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * is説明（有効コード、意味）設定<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public boolean isExplanationSet() 
    {
        if (this.explanation == null || "".equals(this.explanation))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * is区分行目<BR>
     * @@param l_strColumnName - 列物理名
     * @@return String
     * @@roseuid 41BED4470099
     */
    public boolean isDivColumn(String l_strColumnName) 
    {
        if (l_strColumnName.startsWith("ext_div"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * isコード行目<BR>
     * @@param l_strColumnName - 列物理名
     * @@return String
     * @@roseuid 41BED4470099
     */
    public boolean isCodeColumn(String l_strColumnName) 
    {
        if (l_strColumnName.startsWith("ext_code"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * isテキスト行目<BR>
     * @@param l_strColumnName - 列物理名
     * @@return String
     * @@roseuid 41BED4470099
     */
    public boolean isTextColumn(String l_strColumnName) 
    {
        if (l_strColumnName.startsWith("ext_text"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * is数値行目<BR>
     * @@param l_strColumnName - 列物理名
     * @@return String
     * @@roseuid 41BED4470099
     */
    public boolean isNumColumn(String l_strColumnName) 
    {
        if (l_strColumnName.startsWith("ext_value"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * is備考行目<BR>
     * @@param l_strColumnName - 列物理名
     * @@return String
     * @@roseuid 41BED4470099
     */
    public boolean isNoteColumn(String l_strColumnName) 
    {
        if (l_strColumnName.startsWith("ext_note"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
@
