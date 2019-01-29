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
Copyright                : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name                : (WEB3InformColumnSpec)
Author Name           : Daiwa Institute of Research
Revesion History     : 2005/01/25 ���Ō�(���u) �쐬
*/
package webbroker3.inform.util;

import com.fitechlabs.dbind.gen.ColumnSpec;

import webbroker3.common.define.WEB3ItemCheckModeDef;

/**
 * @@author ���Ō�
 * @@version 1.0
 *
 */
public class WEB3InformColumnSpec extends ColumnSpec
{
    /**
     * ��(����)
     */   
    private String columnNameKana;
    
    /**
     * �����i�L���R�[�h�A�Ӗ��j
     */   
    private String explanation;   

    /**
     * ���ڃ`�F�b�N�����iWEB3�f�t�H���g�j
     */   
    private boolean isCustomizeAble;
    
    /**
     * ���ڃ`�F�b�N�����iWEB3�f�t�H���g�j
     */   
    private String defaultCheckMode;
    
    /**
     * �R���X�g���N�^
     * 
     * @@param l_colunmSpec - ColunmSpec
     * @@param l_strColunmName - �񕨗���
     * @@param l_strColumnNameKana - ��(����)
     * @@param l_strDefaultCheckMode - ���ڃ`�F�b�N�����iWEB3�f�t�H���g�j
     * @@param l_strExplanation - �����i�L���R�[�h�A�Ӗ�
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
     * get��(����)<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public String getColumnNameKana() 
    {
        return this.columnNameKana;
    }
    
    /**
     * set��(����)<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public void setColumnNameKana(String l_strColumnNameKana) 
    {
        this.columnNameKana = l_strColumnNameKana;
    }
    
    /**
     * get���ڃ`�F�b�N�����iWEB3�f�t�H���g�j<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public String getDefaultCheckMode() 
    {
        return this.defaultCheckMode;
    }
    
    /**
     * set���ڃ`�F�b�N�����iWEB3�f�t�H���g�j<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public void setDefaultCheckMode(String l_strDefaultCheckMode) 
    {
        this.defaultCheckMode = l_strDefaultCheckMode;
    }
    
    /**
     * get�����i�L���R�[�h�A�Ӗ��j<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public String getExplanation() 
    {
        return this.explanation;
    }
    
    /**
     * set�����i�L���R�[�h�A�Ӗ��j<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public void setExplanation(String l_strExplanation) 
    {
        this.explanation = l_strExplanation;
    }
    
    /**
     * is�J�X�^�}�C�Y�\<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public boolean isCustomizeAble() 
    {
        return this.isCustomizeAble;
    }
    
    /**
     * is���ڃ`�F�b�N�����ݒ�<BR>
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
     * is�����i�L���R�[�h�A�Ӗ��j�ݒ�<BR>
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
     * is�敪�s��<BR>
     * @@param l_strColumnName - �񕨗���
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
     * is�R�[�h�s��<BR>
     * @@param l_strColumnName - �񕨗���
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
     * is�e�L�X�g�s��<BR>
     * @@param l_strColumnName - �񕨗���
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
     * is���l�s��<BR>
     * @@param l_strColumnName - �񕨗���
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
     * is���l�s��<BR>
     * @@param l_strColumnName - �񕨗���
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
