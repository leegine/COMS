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
Copyright                : (株)大和総研 証券ソリューションシステム第二部
File Name                : (WEB3InformColumnSpec)
Author Name           : Daiwa Institute of Research
Revesion History     : 2005/01/25 王暁傑(中訊) 作成
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
 * @@author 王暁傑
 * @@version 1.0
 *
 */
public class WEB3InformTableSpec
{
    /**
     *  テーブルColumnSpecのMap
     */    
    Map informColumnSpecs;    
    
    /**
     *  コンストラクタ
     */
    public WEB3InformTableSpec()
    {
       init();
    }
    
    /**
     *  初期化各テーブル
     */
    private void init()
    {
        DataSpec l_dataSpec = SessionDataSpecInstance.getInstance();
        
        //初期化各種連絡テーブル
        TableSpec l_tableSpec = l_dataSpec.getTableNamed(VariousInformRow.TYPE.getTableName());
        
        WEB3InformColumnSpec[] l_variousInformColumnSpec = {
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("institution_code"), "institution_code", "証券会社コード", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("inform_div"), "inform_div", "連絡種別", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("request_number"), "request_number", "識別コード", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("branch_code"), "branch_code", "部店コード", null, false, WEB3ItemCheckModeDef.HALF_NUMBER),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("account_code"), "account_code", "顧客コード", null, false, WEB3ItemCheckModeDef.HALF_NUMBER),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("trader_code"), "trader_code", "扱者コード", null, false, null),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("account_name"), "account_name", "顧客名", null, false, WEB3ItemCheckModeDef.FULL_CHARACTER),                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("email_address"), "email_address", "顧客メールアドレス", null, false, WEB3ItemCheckModeDef.MAIL_ADDRESS),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div1"), "ext_div1", "区分１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div2"), "ext_div2", "区分２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div3"), "ext_div3", "区分３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div4"), "ext_div4", "区分４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div5"), "ext_div5", "区分５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div6"), "ext_div6", "区分６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div7"), "ext_div7", "区分７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div8"), "ext_div8", "区分８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div9"), "ext_div9", "区分９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div10"), "ext_div10", "区分１０", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div11"), "ext_div11", "区分１１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div12"), "ext_div12", "区分１２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div13"), "ext_div13", "区分１３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div14"), "ext_div14", "区分１４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div15"), "ext_div15", "区分１５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div16"), "ext_div16", "区分１６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div17"), "ext_div17", "区分１７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div18"), "ext_div18", "区分１８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div19"), "ext_div19", "区分１９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div20"), "ext_div20", "区分２０", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div21"), "ext_div21", "区分２１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div22"), "ext_div22", "区分２２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div23"), "ext_div23", "区分２３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div24"), "ext_div24", "区分２４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div25"), "ext_div25", "区分２５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div26"), "ext_div26", "区分２６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div27"), "ext_div27", "区分２７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div28"), "ext_div28", "区分２８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div29"), "ext_div29", "区分２９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div30"), "ext_div30", "区分３０", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div31"), "ext_div31", "区分３１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div32"), "ext_div32", "区分３２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div33"), "ext_div33", "区分３３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div34"), "ext_div34", "区分３４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div35"), "ext_div35", "区分３５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div36"), "ext_div36", "区分３６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div37"), "ext_div37", "区分３７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div38"), "ext_div38", "区分３８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div39"), "ext_div39", "区分３９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_div40"), "ext_div40", "区分４０", null, true, WEB3ItemCheckModeDef.DEFAULT),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code1"), "ext_code1", "コード１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code2"), "ext_code2", "コード２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code3"), "ext_code3", "コード３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code4"), "ext_code4", "コード４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code5"), "ext_code5", "コード５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code6"), "ext_code6", "コード６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code7"), "ext_code7", "コード７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code8"), "ext_code8", "コード８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code9"), "ext_code9", "コード９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_code10"), "ext_code10", "コード10", null, true, WEB3ItemCheckModeDef.DEFAULT),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text1"), "ext_text1", "テキスト１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text2"), "ext_text2", "テキスト２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text3"), "ext_text3", "テキスト３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text4"), "ext_text4", "テキスト４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text5"), "ext_text5", "テキスト５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text6"), "ext_text6", "テキスト６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text7"), "ext_text7", "テキスト７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text8"), "ext_text8", "テキスト８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text9"), "ext_text9", "テキスト９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text10"), "ext_text10", "テキスト１０", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text11"), "ext_text11", "テキスト１１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text12"), "ext_text12", "テキスト１２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text13"), "ext_text13", "テキスト１３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text14"), "ext_text14", "テキスト１４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text15"), "ext_text15", "テキスト１５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text16"), "ext_text16", "テキスト１６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text17"), "ext_text17", "テキスト１７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text18"), "ext_text18", "テキスト１８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text19"), "ext_text19", "テキスト１９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text20"), "ext_text20", "テキスト２０", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text21"), "ext_text21", "テキスト２１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text22"), "ext_text22", "テキスト２２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text23"), "ext_text23", "テキスト２３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text24"), "ext_text24", "テキスト２４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text25"), "ext_text25", "テキスト２５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text26"), "ext_text26", "テキスト２６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text27"), "ext_text27", "テキスト２７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text28"), "ext_text28", "テキスト２８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text29"), "ext_text29", "テキスト２９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text30"), "ext_text30", "テキスト３０", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text31"), "ext_text31", "テキスト３１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text32"), "ext_text32", "テキスト３２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text33"), "ext_text33", "テキスト３３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text34"), "ext_text34", "テキスト３４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text35"), "ext_text35", "テキスト３５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text36"), "ext_text36", "テキスト３６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text37"), "ext_text37", "テキスト３７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text38"), "ext_text38", "テキスト３８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text39"), "ext_text39", "テキスト３９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_text40"), "ext_text40", "テキスト４０", null, true, WEB3ItemCheckModeDef.DEFAULT),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value1"), "ext_value1", "数値１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value2"), "ext_value2", "数値２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value3"), "ext_value3", "数値３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value4"), "ext_value4", "数値４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value5"), "ext_value5", "数値５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value6"), "ext_value6", "数値６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value7"), "ext_value7", "数値７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value8"), "ext_value8", "数値８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value9"), "ext_value9", "数値９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value10"), "ext_value10", "数値１０", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value11"), "ext_value11", "数値１１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value12"), "ext_value12", "数値１２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value13"), "ext_value13", "数値１３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value14"), "ext_value14", "数値１４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value15"), "ext_value15", "数値１５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value16"), "ext_value16", "数値１６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value17"), "ext_value17", "数値１７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value18"), "ext_value18", "数値１８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value19"), "ext_value19", "数値１９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value20"), "ext_value20", "数値２０", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value21"), "ext_value21", "数値２１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value22"), "ext_value22", "数値２２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value23"), "ext_value23", "数値２３", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value24"), "ext_value24", "数値２４", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value25"), "ext_value25", "数値２５", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value26"), "ext_value26", "数値２６", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value27"), "ext_value27", "数値２７", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value28"), "ext_value28", "数値２８", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value29"), "ext_value29", "数値２９", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_value30"), "ext_value30", "数値３０", null, true, WEB3ItemCheckModeDef.DEFAULT),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_note1"), "ext_note1", "備考１", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("ext_note2"), "ext_note2", "備考２", null, true, WEB3ItemCheckModeDef.DEFAULT),
                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("last_updater"), "last_updater", "更新者コード", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("created_timestamp"), "created_timestamp", "作成日時", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("last_updated_timestamp"), "last_updated_timestamp", "更新日時", null, false, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("fund_code"), "fund_code", "銘柄コード", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("sonar_trader_code"), "sonar_trader_code", "扱者コード（SONAR）", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("status"), "status", "伝票作成状況", null, true, WEB3ItemCheckModeDef.DEFAULT),                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("error_reason_code"), "error_reason_code", "エラー理由コード", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("order_request_number"), "order_request_number", "伝票識別コード", null, true, WEB3ItemCheckModeDef.DEFAULT),
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("request_code"), "request_code", "データコード", null, true, WEB3ItemCheckModeDef.DEFAULT),                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("send_timestamp"), "send_timestamp", "伝票送信日時", null, true, WEB3ItemCheckModeDef.DEFAULT),                
                new WEB3InformColumnSpec(l_tableSpec.getColumnByName("receipt_timestamp"), "receipt_timestamp", "伝票受信日時", null, true, WEB3ItemCheckModeDef.DEFAULT),
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
