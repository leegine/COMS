head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformTableSpec.java;


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

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.dbind.gen.DataSpec;
import com.fitechlabs.dbind.gen.TableSpec;

import webbroker3.common.define.WEB3ItemCheckModeDef;
import webbroker3.inform.data.SessionDataSpecInstance;
import webbroker3.inform.data.VariousInformRow;

/**
 * @@author ���Ō�
 * @@version 1.0
 *
 */
public class WEB3InformTableSpec
{
    /**
     *  �e�[�u��ColumnSpec��Map
     */    
    Map informColumnSpecs;    
    
    /**
     *  �R���X�g���N�^
     */
    public WEB3InformTableSpec()
    {
       init();
    }
    
    /**
     *  �������e�e�[�u��
     */
    private void init()
    {
        DataSpec l_dataSpec = SessionDataSpecInstance.getInstance();
        
        //�������e��A���e�[�u��
        TableSpec l_tableSpec = l_dataSpec.getTableNamed(VariousInformRow.TYPE.getTableName());
        
        WEB3InformColumnSpec[] l_variousInformColumnSpec = {
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("institution_code"), "institution_code", "�،���ЃR�[�h", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("inform_div"), "inform_div", "�A�����", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("request_number"), "request_number", "���ʃR�[�h", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("branch_code"), "branch_code", "���X�R�[�h", null, false, WEB3ItemCheckModeDef.HALF_NUMBER),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("account_code"), "account_code", "�ڋq�R�[�h", null, false, WEB3ItemCheckModeDef.HALF_NUMBER),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("trader_code"), "trader_code", "���҃R�[�h", null, false, null),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("account_name"), "account_name", "�ڋq��", null, false, WEB3ItemCheckModeDef.FULL_CHARACTER),                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("email_address"), "email_address", "�ڋq���[���A�h���X", null, false, WEB3ItemCheckModeDef.MAIL_ADDRESS),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div1"), "ext_div1", "�敪�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div2"), "ext_div2", "�敪�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div3"), "ext_div3", "�敪�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div4"), "ext_div4", "�敪�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div5"), "ext_div5", "�敪�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div6"), "ext_div6", "�敪�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div7"), "ext_div7", "�敪�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div8"), "ext_div8", "�敪�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div9"), "ext_div9", "�敪�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div10"), "ext_div10", "�敪�P�O", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div11"), "ext_div11", "�敪�P�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div12"), "ext_div12", "�敪�P�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div13"), "ext_div13", "�敪�P�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div14"), "ext_div14", "�敪�P�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div15"), "ext_div15", "�敪�P�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div16"), "ext_div16", "�敪�P�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div17"), "ext_div17", "�敪�P�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div18"), "ext_div18", "�敪�P�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div19"), "ext_div19", "�敪�P�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div20"), "ext_div20", "�敪�Q�O", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div21"), "ext_div21", "�敪�Q�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div22"), "ext_div22", "�敪�Q�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div23"), "ext_div23", "�敪�Q�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div24"), "ext_div24", "�敪�Q�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div25"), "ext_div25", "�敪�Q�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div26"), "ext_div26", "�敪�Q�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div27"), "ext_div27", "�敪�Q�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div28"), "ext_div28", "�敪�Q�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div29"), "ext_div29", "�敪�Q�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div30"), "ext_div30", "�敪�R�O", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div31"), "ext_div31", "�敪�R�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div32"), "ext_div32", "�敪�R�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div33"), "ext_div33", "�敪�R�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div34"), "ext_div34", "�敪�R�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div35"), "ext_div35", "�敪�R�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div36"), "ext_div36", "�敪�R�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div37"), "ext_div37", "�敪�R�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div38"), "ext_div38", "�敪�R�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div39"), "ext_div39", "�敪�R�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div40"), "ext_div40", "�敪�S�O", null, true, WEB3ItemCheckModeDef.DEFAULT),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code1"), "ext_code1", "�R�[�h�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code2"), "ext_code2", "�R�[�h�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code3"), "ext_code3", "�R�[�h�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code4"), "ext_code4", "�R�[�h�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code5"), "ext_code5", "�R�[�h�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code6"), "ext_code6", "�R�[�h�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code7"), "ext_code7", "�R�[�h�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code8"), "ext_code8", "�R�[�h�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code9"), "ext_code9", "�R�[�h�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code10"), "ext_code10", "�R�[�h10", null, true, WEB3ItemCheckModeDef.DEFAULT),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text1"), "ext_text1", "�e�L�X�g�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text2"), "ext_text2", "�e�L�X�g�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text3"), "ext_text3", "�e�L�X�g�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text4"), "ext_text4", "�e�L�X�g�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text5"), "ext_text5", "�e�L�X�g�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text6"), "ext_text6", "�e�L�X�g�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text7"), "ext_text7", "�e�L�X�g�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text8"), "ext_text8", "�e�L�X�g�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text9"), "ext_text9", "�e�L�X�g�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text10"), "ext_text10", "�e�L�X�g�P�O", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text11"), "ext_text11", "�e�L�X�g�P�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text12"), "ext_text12", "�e�L�X�g�P�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text13"), "ext_text13", "�e�L�X�g�P�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text14"), "ext_text14", "�e�L�X�g�P�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text15"), "ext_text15", "�e�L�X�g�P�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text16"), "ext_text16", "�e�L�X�g�P�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text17"), "ext_text17", "�e�L�X�g�P�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text18"), "ext_text18", "�e�L�X�g�P�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text19"), "ext_text19", "�e�L�X�g�P�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text20"), "ext_text20", "�e�L�X�g�Q�O", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text21"), "ext_text21", "�e�L�X�g�Q�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text22"), "ext_text22", "�e�L�X�g�Q�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text23"), "ext_text23", "�e�L�X�g�Q�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text24"), "ext_text24", "�e�L�X�g�Q�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text25"), "ext_text25", "�e�L�X�g�Q�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text26"), "ext_text26", "�e�L�X�g�Q�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text27"), "ext_text27", "�e�L�X�g�Q�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text28"), "ext_text28", "�e�L�X�g�Q�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text29"), "ext_text29", "�e�L�X�g�Q�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text30"), "ext_text30", "�e�L�X�g�R�O", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text31"), "ext_text31", "�e�L�X�g�R�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text32"), "ext_text32", "�e�L�X�g�R�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text33"), "ext_text33", "�e�L�X�g�R�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text34"), "ext_text34", "�e�L�X�g�R�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text35"), "ext_text35", "�e�L�X�g�R�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text36"), "ext_text36", "�e�L�X�g�R�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text37"), "ext_text37", "�e�L�X�g�R�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text38"), "ext_text38", "�e�L�X�g�R�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text39"), "ext_text39", "�e�L�X�g�R�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text40"), "ext_text40", "�e�L�X�g�S�O", null, true, WEB3ItemCheckModeDef.DEFAULT),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value1"), "ext_value1", "���l�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value2"), "ext_value2", "���l�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value3"), "ext_value3", "���l�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value4"), "ext_value4", "���l�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value5"), "ext_value5", "���l�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value6"), "ext_value6", "���l�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value7"), "ext_value7", "���l�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value8"), "ext_value8", "���l�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value9"), "ext_value9", "���l�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value10"), "ext_value10", "���l�P�O", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value11"), "ext_value11", "���l�P�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value12"), "ext_value12", "���l�P�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value13"), "ext_value13", "���l�P�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value14"), "ext_value14", "���l�P�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value15"), "ext_value15", "���l�P�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value16"), "ext_value16", "���l�P�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value17"), "ext_value17", "���l�P�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value18"), "ext_value18", "���l�P�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value19"), "ext_value19", "���l�P�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value20"), "ext_value20", "���l�Q�O", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value21"), "ext_value21", "���l�Q�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value22"), "ext_value22", "���l�Q�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value23"), "ext_value23", "���l�Q�R", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value24"), "ext_value24", "���l�Q�S", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value25"), "ext_value25", "���l�Q�T", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value26"), "ext_value26", "���l�Q�U", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value27"), "ext_value27", "���l�Q�V", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value28"), "ext_value28", "���l�Q�W", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value29"), "ext_value29", "���l�Q�X", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value30"), "ext_value30", "���l�R�O", null, true, WEB3ItemCheckModeDef.DEFAULT),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_note1"), "ext_note1", "���l�P", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_note2"), "ext_note2", "���l�Q", null, true, WEB3ItemCheckModeDef.DEFAULT),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("last_updater"), "last_updater", "�X�V�҃R�[�h", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("created_timestamp"), "created_timestamp", "�쐬����", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("last_updated_timestamp"), "last_updated_timestamp", "�X�V����", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("fund_code"), "fund_code", "�����R�[�h", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("sonar_trader_code"), "sonar_trader_code", "���҃R�[�h�iSONAR�j", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("status"), "status", "�`�[�쐬��", null, true, WEB3ItemCheckModeDef.DEFAULT),                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("error_reason_code"), "error_reason_code", "�G���[���R�R�[�h", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("order_request_number"), "order_request_number", "�`�[���ʃR�[�h", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("request_code"), "request_code", "�f�[�^�R�[�h", null, true, WEB3ItemCheckModeDef.DEFAULT),                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("send_timestamp"), "send_timestamp", "�`�[���M����", null, true, WEB3ItemCheckModeDef.DEFAULT),                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("receipt_timestamp"), "receipt_timestamp", "�`�[��M����", null, true, WEB3ItemCheckModeDef.DEFAULT),
        };        
        informColumnSpecs = new HashMap();
        informColumnSpecs.put(VariousInformRow.TYPE.getTableName(), l_variousInformColumnSpec);                
    }
    
    /**
     * getColumnSpecsByTableName<BR>
     * @@return WEB3InformColumnSpec[]
     * @@roseuid 41BED4470099
     */
    public WEB3InformColumnSpec[] getColumnSpecsByTableName(String l_strTableName) 
    {
        return (WEB3InformColumnSpec[])informColumnSpecs.get(l_strTableName);
    }
    
    /**
     * getColumnSpec<BR>
     * @@return WEB3InformColumnSpec[]
     * @@roseuid 41BED4470099
     */
    public WEB3InformColumnSpec getColumnSpec(String l_strTableName, String l_strColumnName) 
    {
        WEB3InformColumnSpec[] l_informColumnSpecs = 
            (WEB3InformColumnSpec[])informColumnSpecs.get(l_strTableName);
        
        for (int i = 0; i < l_informColumnSpecs.length; i ++)
        {
            if (l_informColumnSpecs[i].asHeader().equals(l_strColumnName))
            {
                return l_informColumnSpecs[i];
            }                
        }
        return null;
    }
}
@
