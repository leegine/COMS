head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.58.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	VariousInformParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * various_informテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link VariousInformRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link VariousInformParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link VariousInformParams}が{@@link VariousInformRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see VariousInformPK 
 * @@see VariousInformRow 
 */
public class VariousInformParams extends Params implements VariousInformRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "various_inform";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = VariousInformRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return VariousInformRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>inform_div</em>カラムの値 
   */
  public  String  inform_div;

  /** 
   * <em>request_number</em>カラムの値 
   */
  public  String  request_number;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>account_name</em>カラムの値 
   */
  public  String  account_name;

  /** 
   * <em>email_address</em>カラムの値 
   */
  public  String  email_address;

  /** 
   * <em>ext_div1</em>カラムの値 
   */
  public  String  ext_div1;

  /** 
   * <em>ext_div2</em>カラムの値 
   */
  public  String  ext_div2;

  /** 
   * <em>ext_div3</em>カラムの値 
   */
  public  String  ext_div3;

  /** 
   * <em>ext_div4</em>カラムの値 
   */
  public  String  ext_div4;

  /** 
   * <em>ext_div5</em>カラムの値 
   */
  public  String  ext_div5;

  /** 
   * <em>ext_div6</em>カラムの値 
   */
  public  String  ext_div6;

  /** 
   * <em>ext_div7</em>カラムの値 
   */
  public  String  ext_div7;

  /** 
   * <em>ext_div8</em>カラムの値 
   */
  public  String  ext_div8;

  /** 
   * <em>ext_div9</em>カラムの値 
   */
  public  String  ext_div9;

  /** 
   * <em>ext_div10</em>カラムの値 
   */
  public  String  ext_div10;

  /** 
   * <em>ext_div11</em>カラムの値 
   */
  public  String  ext_div11;

  /** 
   * <em>ext_div12</em>カラムの値 
   */
  public  String  ext_div12;

  /** 
   * <em>ext_div13</em>カラムの値 
   */
  public  String  ext_div13;

  /** 
   * <em>ext_div14</em>カラムの値 
   */
  public  String  ext_div14;

  /** 
   * <em>ext_div15</em>カラムの値 
   */
  public  String  ext_div15;

  /** 
   * <em>ext_div16</em>カラムの値 
   */
  public  String  ext_div16;

  /** 
   * <em>ext_div17</em>カラムの値 
   */
  public  String  ext_div17;

  /** 
   * <em>ext_div18</em>カラムの値 
   */
  public  String  ext_div18;

  /** 
   * <em>ext_div19</em>カラムの値 
   */
  public  String  ext_div19;

  /** 
   * <em>ext_div20</em>カラムの値 
   */
  public  String  ext_div20;

  /** 
   * <em>ext_div21</em>カラムの値 
   */
  public  String  ext_div21;

  /** 
   * <em>ext_div22</em>カラムの値 
   */
  public  String  ext_div22;

  /** 
   * <em>ext_div23</em>カラムの値 
   */
  public  String  ext_div23;

  /** 
   * <em>ext_div24</em>カラムの値 
   */
  public  String  ext_div24;

  /** 
   * <em>ext_div25</em>カラムの値 
   */
  public  String  ext_div25;

  /** 
   * <em>ext_div26</em>カラムの値 
   */
  public  String  ext_div26;

  /** 
   * <em>ext_div27</em>カラムの値 
   */
  public  String  ext_div27;

  /** 
   * <em>ext_div28</em>カラムの値 
   */
  public  String  ext_div28;

  /** 
   * <em>ext_div29</em>カラムの値 
   */
  public  String  ext_div29;

  /** 
   * <em>ext_div30</em>カラムの値 
   */
  public  String  ext_div30;

  /** 
   * <em>ext_div31</em>カラムの値 
   */
  public  String  ext_div31;

  /** 
   * <em>ext_div32</em>カラムの値 
   */
  public  String  ext_div32;

  /** 
   * <em>ext_div33</em>カラムの値 
   */
  public  String  ext_div33;

  /** 
   * <em>ext_div34</em>カラムの値 
   */
  public  String  ext_div34;

  /** 
   * <em>ext_div35</em>カラムの値 
   */
  public  String  ext_div35;

  /** 
   * <em>ext_div36</em>カラムの値 
   */
  public  String  ext_div36;

  /** 
   * <em>ext_div37</em>カラムの値 
   */
  public  String  ext_div37;

  /** 
   * <em>ext_div38</em>カラムの値 
   */
  public  String  ext_div38;

  /** 
   * <em>ext_div39</em>カラムの値 
   */
  public  String  ext_div39;

  /** 
   * <em>ext_div40</em>カラムの値 
   */
  public  String  ext_div40;

  /** 
   * <em>ext_code1</em>カラムの値 
   */
  public  String  ext_code1;

  /** 
   * <em>ext_code2</em>カラムの値 
   */
  public  String  ext_code2;

  /** 
   * <em>ext_code3</em>カラムの値 
   */
  public  String  ext_code3;

  /** 
   * <em>ext_code4</em>カラムの値 
   */
  public  String  ext_code4;

  /** 
   * <em>ext_code5</em>カラムの値 
   */
  public  String  ext_code5;

  /** 
   * <em>ext_code6</em>カラムの値 
   */
  public  String  ext_code6;

  /** 
   * <em>ext_code7</em>カラムの値 
   */
  public  String  ext_code7;

  /** 
   * <em>ext_code8</em>カラムの値 
   */
  public  String  ext_code8;

  /** 
   * <em>ext_code9</em>カラムの値 
   */
  public  String  ext_code9;

  /** 
   * <em>ext_code10</em>カラムの値 
   */
  public  String  ext_code10;

  /** 
   * <em>ext_text1</em>カラムの値 
   */
  public  String  ext_text1;

  /** 
   * <em>ext_text2</em>カラムの値 
   */
  public  String  ext_text2;

  /** 
   * <em>ext_text3</em>カラムの値 
   */
  public  String  ext_text3;

  /** 
   * <em>ext_text4</em>カラムの値 
   */
  public  String  ext_text4;

  /** 
   * <em>ext_text5</em>カラムの値 
   */
  public  String  ext_text5;

  /** 
   * <em>ext_text6</em>カラムの値 
   */
  public  String  ext_text6;

  /** 
   * <em>ext_text7</em>カラムの値 
   */
  public  String  ext_text7;

  /** 
   * <em>ext_text8</em>カラムの値 
   */
  public  String  ext_text8;

  /** 
   * <em>ext_text9</em>カラムの値 
   */
  public  String  ext_text9;

  /** 
   * <em>ext_text10</em>カラムの値 
   */
  public  String  ext_text10;

  /** 
   * <em>ext_text11</em>カラムの値 
   */
  public  String  ext_text11;

  /** 
   * <em>ext_text12</em>カラムの値 
   */
  public  String  ext_text12;

  /** 
   * <em>ext_text13</em>カラムの値 
   */
  public  String  ext_text13;

  /** 
   * <em>ext_text14</em>カラムの値 
   */
  public  String  ext_text14;

  /** 
   * <em>ext_text15</em>カラムの値 
   */
  public  String  ext_text15;

  /** 
   * <em>ext_text16</em>カラムの値 
   */
  public  String  ext_text16;

  /** 
   * <em>ext_text17</em>カラムの値 
   */
  public  String  ext_text17;

  /** 
   * <em>ext_text18</em>カラムの値 
   */
  public  String  ext_text18;

  /** 
   * <em>ext_text19</em>カラムの値 
   */
  public  String  ext_text19;

  /** 
   * <em>ext_text20</em>カラムの値 
   */
  public  String  ext_text20;

  /** 
   * <em>ext_text21</em>カラムの値 
   */
  public  String  ext_text21;

  /** 
   * <em>ext_text22</em>カラムの値 
   */
  public  String  ext_text22;

  /** 
   * <em>ext_text23</em>カラムの値 
   */
  public  String  ext_text23;

  /** 
   * <em>ext_text24</em>カラムの値 
   */
  public  String  ext_text24;

  /** 
   * <em>ext_text25</em>カラムの値 
   */
  public  String  ext_text25;

  /** 
   * <em>ext_text26</em>カラムの値 
   */
  public  String  ext_text26;

  /** 
   * <em>ext_text27</em>カラムの値 
   */
  public  String  ext_text27;

  /** 
   * <em>ext_text28</em>カラムの値 
   */
  public  String  ext_text28;

  /** 
   * <em>ext_text29</em>カラムの値 
   */
  public  String  ext_text29;

  /** 
   * <em>ext_text30</em>カラムの値 
   */
  public  String  ext_text30;

  /** 
   * <em>ext_text31</em>カラムの値 
   */
  public  String  ext_text31;

  /** 
   * <em>ext_text32</em>カラムの値 
   */
  public  String  ext_text32;

  /** 
   * <em>ext_text33</em>カラムの値 
   */
  public  String  ext_text33;

  /** 
   * <em>ext_text34</em>カラムの値 
   */
  public  String  ext_text34;

  /** 
   * <em>ext_text35</em>カラムの値 
   */
  public  String  ext_text35;

  /** 
   * <em>ext_text36</em>カラムの値 
   */
  public  String  ext_text36;

  /** 
   * <em>ext_text37</em>カラムの値 
   */
  public  String  ext_text37;

  /** 
   * <em>ext_text38</em>カラムの値 
   */
  public  String  ext_text38;

  /** 
   * <em>ext_text39</em>カラムの値 
   */
  public  String  ext_text39;

  /** 
   * <em>ext_text40</em>カラムの値 
   */
  public  String  ext_text40;

  /** 
   * <em>ext_value1</em>カラムの値 
   */
  public  Long  ext_value1;

  /** 
   * <em>ext_value2</em>カラムの値 
   */
  public  Long  ext_value2;

  /** 
   * <em>ext_value3</em>カラムの値 
   */
  public  Long  ext_value3;

  /** 
   * <em>ext_value4</em>カラムの値 
   */
  public  Long  ext_value4;

  /** 
   * <em>ext_value5</em>カラムの値 
   */
  public  Long  ext_value5;

  /** 
   * <em>ext_value6</em>カラムの値 
   */
  public  Long  ext_value6;

  /** 
   * <em>ext_value7</em>カラムの値 
   */
  public  Long  ext_value7;

  /** 
   * <em>ext_value8</em>カラムの値 
   */
  public  Long  ext_value8;

  /** 
   * <em>ext_value9</em>カラムの値 
   */
  public  Long  ext_value9;

  /** 
   * <em>ext_value10</em>カラムの値 
   */
  public  Long  ext_value10;

  /** 
   * <em>ext_value11</em>カラムの値 
   */
  public  Long  ext_value11;

  /** 
   * <em>ext_value12</em>カラムの値 
   */
  public  Long  ext_value12;

  /** 
   * <em>ext_value13</em>カラムの値 
   */
  public  Long  ext_value13;

  /** 
   * <em>ext_value14</em>カラムの値 
   */
  public  Long  ext_value14;

  /** 
   * <em>ext_value15</em>カラムの値 
   */
  public  Long  ext_value15;

  /** 
   * <em>ext_value16</em>カラムの値 
   */
  public  Long  ext_value16;

  /** 
   * <em>ext_value17</em>カラムの値 
   */
  public  Long  ext_value17;

  /** 
   * <em>ext_value18</em>カラムの値 
   */
  public  Long  ext_value18;

  /** 
   * <em>ext_value19</em>カラムの値 
   */
  public  Long  ext_value19;

  /** 
   * <em>ext_value20</em>カラムの値 
   */
  public  Long  ext_value20;

  /** 
   * <em>ext_value21</em>カラムの値 
   */
  public  Long  ext_value21;

  /** 
   * <em>ext_value22</em>カラムの値 
   */
  public  Long  ext_value22;

  /** 
   * <em>ext_value23</em>カラムの値 
   */
  public  Long  ext_value23;

  /** 
   * <em>ext_value24</em>カラムの値 
   */
  public  Long  ext_value24;

  /** 
   * <em>ext_value25</em>カラムの値 
   */
  public  Long  ext_value25;

  /** 
   * <em>ext_value26</em>カラムの値 
   */
  public  Long  ext_value26;

  /** 
   * <em>ext_value27</em>カラムの値 
   */
  public  Long  ext_value27;

  /** 
   * <em>ext_value28</em>カラムの値 
   */
  public  Long  ext_value28;

  /** 
   * <em>ext_value29</em>カラムの値 
   */
  public  Long  ext_value29;

  /** 
   * <em>ext_value30</em>カラムの値 
   */
  public  Long  ext_value30;

  /** 
   * <em>ext_note1</em>カラムの値 
   */
  public  String  ext_note1;

  /** 
   * <em>ext_note2</em>カラムの値 
   */
  public  String  ext_note2;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>fund_code</em>カラムの値 
   */
  public  String  fund_code;

  /** 
   * <em>sonar_trader_code</em>カラムの値 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>error_reason_code</em>カラムの値 
   */
  public  String  error_reason_code;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>request_code</em>カラムの値 
   */
  public  String  request_code;

  /** 
   * <em>send_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  send_timestamp;

  /** 
   * <em>receipt_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  receipt_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean inform_div_is_set = false;
  private boolean inform_div_is_modified = false;
  private boolean request_number_is_set = false;
  private boolean request_number_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean account_name_is_set = false;
  private boolean account_name_is_modified = false;
  private boolean email_address_is_set = false;
  private boolean email_address_is_modified = false;
  private boolean ext_div1_is_set = false;
  private boolean ext_div1_is_modified = false;
  private boolean ext_div2_is_set = false;
  private boolean ext_div2_is_modified = false;
  private boolean ext_div3_is_set = false;
  private boolean ext_div3_is_modified = false;
  private boolean ext_div4_is_set = false;
  private boolean ext_div4_is_modified = false;
  private boolean ext_div5_is_set = false;
  private boolean ext_div5_is_modified = false;
  private boolean ext_div6_is_set = false;
  private boolean ext_div6_is_modified = false;
  private boolean ext_div7_is_set = false;
  private boolean ext_div7_is_modified = false;
  private boolean ext_div8_is_set = false;
  private boolean ext_div8_is_modified = false;
  private boolean ext_div9_is_set = false;
  private boolean ext_div9_is_modified = false;
  private boolean ext_div10_is_set = false;
  private boolean ext_div10_is_modified = false;
  private boolean ext_div11_is_set = false;
  private boolean ext_div11_is_modified = false;
  private boolean ext_div12_is_set = false;
  private boolean ext_div12_is_modified = false;
  private boolean ext_div13_is_set = false;
  private boolean ext_div13_is_modified = false;
  private boolean ext_div14_is_set = false;
  private boolean ext_div14_is_modified = false;
  private boolean ext_div15_is_set = false;
  private boolean ext_div15_is_modified = false;
  private boolean ext_div16_is_set = false;
  private boolean ext_div16_is_modified = false;
  private boolean ext_div17_is_set = false;
  private boolean ext_div17_is_modified = false;
  private boolean ext_div18_is_set = false;
  private boolean ext_div18_is_modified = false;
  private boolean ext_div19_is_set = false;
  private boolean ext_div19_is_modified = false;
  private boolean ext_div20_is_set = false;
  private boolean ext_div20_is_modified = false;
  private boolean ext_div21_is_set = false;
  private boolean ext_div21_is_modified = false;
  private boolean ext_div22_is_set = false;
  private boolean ext_div22_is_modified = false;
  private boolean ext_div23_is_set = false;
  private boolean ext_div23_is_modified = false;
  private boolean ext_div24_is_set = false;
  private boolean ext_div24_is_modified = false;
  private boolean ext_div25_is_set = false;
  private boolean ext_div25_is_modified = false;
  private boolean ext_div26_is_set = false;
  private boolean ext_div26_is_modified = false;
  private boolean ext_div27_is_set = false;
  private boolean ext_div27_is_modified = false;
  private boolean ext_div28_is_set = false;
  private boolean ext_div28_is_modified = false;
  private boolean ext_div29_is_set = false;
  private boolean ext_div29_is_modified = false;
  private boolean ext_div30_is_set = false;
  private boolean ext_div30_is_modified = false;
  private boolean ext_div31_is_set = false;
  private boolean ext_div31_is_modified = false;
  private boolean ext_div32_is_set = false;
  private boolean ext_div32_is_modified = false;
  private boolean ext_div33_is_set = false;
  private boolean ext_div33_is_modified = false;
  private boolean ext_div34_is_set = false;
  private boolean ext_div34_is_modified = false;
  private boolean ext_div35_is_set = false;
  private boolean ext_div35_is_modified = false;
  private boolean ext_div36_is_set = false;
  private boolean ext_div36_is_modified = false;
  private boolean ext_div37_is_set = false;
  private boolean ext_div37_is_modified = false;
  private boolean ext_div38_is_set = false;
  private boolean ext_div38_is_modified = false;
  private boolean ext_div39_is_set = false;
  private boolean ext_div39_is_modified = false;
  private boolean ext_div40_is_set = false;
  private boolean ext_div40_is_modified = false;
  private boolean ext_code1_is_set = false;
  private boolean ext_code1_is_modified = false;
  private boolean ext_code2_is_set = false;
  private boolean ext_code2_is_modified = false;
  private boolean ext_code3_is_set = false;
  private boolean ext_code3_is_modified = false;
  private boolean ext_code4_is_set = false;
  private boolean ext_code4_is_modified = false;
  private boolean ext_code5_is_set = false;
  private boolean ext_code5_is_modified = false;
  private boolean ext_code6_is_set = false;
  private boolean ext_code6_is_modified = false;
  private boolean ext_code7_is_set = false;
  private boolean ext_code7_is_modified = false;
  private boolean ext_code8_is_set = false;
  private boolean ext_code8_is_modified = false;
  private boolean ext_code9_is_set = false;
  private boolean ext_code9_is_modified = false;
  private boolean ext_code10_is_set = false;
  private boolean ext_code10_is_modified = false;
  private boolean ext_text1_is_set = false;
  private boolean ext_text1_is_modified = false;
  private boolean ext_text2_is_set = false;
  private boolean ext_text2_is_modified = false;
  private boolean ext_text3_is_set = false;
  private boolean ext_text3_is_modified = false;
  private boolean ext_text4_is_set = false;
  private boolean ext_text4_is_modified = false;
  private boolean ext_text5_is_set = false;
  private boolean ext_text5_is_modified = false;
  private boolean ext_text6_is_set = false;
  private boolean ext_text6_is_modified = false;
  private boolean ext_text7_is_set = false;
  private boolean ext_text7_is_modified = false;
  private boolean ext_text8_is_set = false;
  private boolean ext_text8_is_modified = false;
  private boolean ext_text9_is_set = false;
  private boolean ext_text9_is_modified = false;
  private boolean ext_text10_is_set = false;
  private boolean ext_text10_is_modified = false;
  private boolean ext_text11_is_set = false;
  private boolean ext_text11_is_modified = false;
  private boolean ext_text12_is_set = false;
  private boolean ext_text12_is_modified = false;
  private boolean ext_text13_is_set = false;
  private boolean ext_text13_is_modified = false;
  private boolean ext_text14_is_set = false;
  private boolean ext_text14_is_modified = false;
  private boolean ext_text15_is_set = false;
  private boolean ext_text15_is_modified = false;
  private boolean ext_text16_is_set = false;
  private boolean ext_text16_is_modified = false;
  private boolean ext_text17_is_set = false;
  private boolean ext_text17_is_modified = false;
  private boolean ext_text18_is_set = false;
  private boolean ext_text18_is_modified = false;
  private boolean ext_text19_is_set = false;
  private boolean ext_text19_is_modified = false;
  private boolean ext_text20_is_set = false;
  private boolean ext_text20_is_modified = false;
  private boolean ext_text21_is_set = false;
  private boolean ext_text21_is_modified = false;
  private boolean ext_text22_is_set = false;
  private boolean ext_text22_is_modified = false;
  private boolean ext_text23_is_set = false;
  private boolean ext_text23_is_modified = false;
  private boolean ext_text24_is_set = false;
  private boolean ext_text24_is_modified = false;
  private boolean ext_text25_is_set = false;
  private boolean ext_text25_is_modified = false;
  private boolean ext_text26_is_set = false;
  private boolean ext_text26_is_modified = false;
  private boolean ext_text27_is_set = false;
  private boolean ext_text27_is_modified = false;
  private boolean ext_text28_is_set = false;
  private boolean ext_text28_is_modified = false;
  private boolean ext_text29_is_set = false;
  private boolean ext_text29_is_modified = false;
  private boolean ext_text30_is_set = false;
  private boolean ext_text30_is_modified = false;
  private boolean ext_text31_is_set = false;
  private boolean ext_text31_is_modified = false;
  private boolean ext_text32_is_set = false;
  private boolean ext_text32_is_modified = false;
  private boolean ext_text33_is_set = false;
  private boolean ext_text33_is_modified = false;
  private boolean ext_text34_is_set = false;
  private boolean ext_text34_is_modified = false;
  private boolean ext_text35_is_set = false;
  private boolean ext_text35_is_modified = false;
  private boolean ext_text36_is_set = false;
  private boolean ext_text36_is_modified = false;
  private boolean ext_text37_is_set = false;
  private boolean ext_text37_is_modified = false;
  private boolean ext_text38_is_set = false;
  private boolean ext_text38_is_modified = false;
  private boolean ext_text39_is_set = false;
  private boolean ext_text39_is_modified = false;
  private boolean ext_text40_is_set = false;
  private boolean ext_text40_is_modified = false;
  private boolean ext_value1_is_set = false;
  private boolean ext_value1_is_modified = false;
  private boolean ext_value2_is_set = false;
  private boolean ext_value2_is_modified = false;
  private boolean ext_value3_is_set = false;
  private boolean ext_value3_is_modified = false;
  private boolean ext_value4_is_set = false;
  private boolean ext_value4_is_modified = false;
  private boolean ext_value5_is_set = false;
  private boolean ext_value5_is_modified = false;
  private boolean ext_value6_is_set = false;
  private boolean ext_value6_is_modified = false;
  private boolean ext_value7_is_set = false;
  private boolean ext_value7_is_modified = false;
  private boolean ext_value8_is_set = false;
  private boolean ext_value8_is_modified = false;
  private boolean ext_value9_is_set = false;
  private boolean ext_value9_is_modified = false;
  private boolean ext_value10_is_set = false;
  private boolean ext_value10_is_modified = false;
  private boolean ext_value11_is_set = false;
  private boolean ext_value11_is_modified = false;
  private boolean ext_value12_is_set = false;
  private boolean ext_value12_is_modified = false;
  private boolean ext_value13_is_set = false;
  private boolean ext_value13_is_modified = false;
  private boolean ext_value14_is_set = false;
  private boolean ext_value14_is_modified = false;
  private boolean ext_value15_is_set = false;
  private boolean ext_value15_is_modified = false;
  private boolean ext_value16_is_set = false;
  private boolean ext_value16_is_modified = false;
  private boolean ext_value17_is_set = false;
  private boolean ext_value17_is_modified = false;
  private boolean ext_value18_is_set = false;
  private boolean ext_value18_is_modified = false;
  private boolean ext_value19_is_set = false;
  private boolean ext_value19_is_modified = false;
  private boolean ext_value20_is_set = false;
  private boolean ext_value20_is_modified = false;
  private boolean ext_value21_is_set = false;
  private boolean ext_value21_is_modified = false;
  private boolean ext_value22_is_set = false;
  private boolean ext_value22_is_modified = false;
  private boolean ext_value23_is_set = false;
  private boolean ext_value23_is_modified = false;
  private boolean ext_value24_is_set = false;
  private boolean ext_value24_is_modified = false;
  private boolean ext_value25_is_set = false;
  private boolean ext_value25_is_modified = false;
  private boolean ext_value26_is_set = false;
  private boolean ext_value26_is_modified = false;
  private boolean ext_value27_is_set = false;
  private boolean ext_value27_is_modified = false;
  private boolean ext_value28_is_set = false;
  private boolean ext_value28_is_modified = false;
  private boolean ext_value29_is_set = false;
  private boolean ext_value29_is_modified = false;
  private boolean ext_value30_is_set = false;
  private boolean ext_value30_is_modified = false;
  private boolean ext_note1_is_set = false;
  private boolean ext_note1_is_modified = false;
  private boolean ext_note2_is_set = false;
  private boolean ext_note2_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean fund_code_is_set = false;
  private boolean fund_code_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean error_reason_code_is_set = false;
  private boolean error_reason_code_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean send_timestamp_is_set = false;
  private boolean send_timestamp_is_modified = false;
  private boolean receipt_timestamp_is_set = false;
  private boolean receipt_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "inform_div=" + inform_div
      + "," + "request_number=" + request_number
      + "," + "branch_code=" + branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "account_name=" +account_name
      + "," + "email_address=" +email_address
      + "," + "ext_div1=" +ext_div1
      + "," + "ext_div2=" +ext_div2
      + "," + "ext_div3=" +ext_div3
      + "," + "ext_div4=" +ext_div4
      + "," + "ext_div5=" +ext_div5
      + "," + "ext_div6=" +ext_div6
      + "," + "ext_div7=" +ext_div7
      + "," + "ext_div8=" +ext_div8
      + "," + "ext_div9=" +ext_div9
      + "," + "ext_div10=" +ext_div10
      + "," + "ext_div11=" +ext_div11
      + "," + "ext_div12=" +ext_div12
      + "," + "ext_div13=" +ext_div13
      + "," + "ext_div14=" +ext_div14
      + "," + "ext_div15=" +ext_div15
      + "," + "ext_div16=" +ext_div16
      + "," + "ext_div17=" +ext_div17
      + "," + "ext_div18=" +ext_div18
      + "," + "ext_div19=" +ext_div19
      + "," + "ext_div20=" +ext_div20
      + "," + "ext_div21=" +ext_div21
      + "," + "ext_div22=" +ext_div22
      + "," + "ext_div23=" +ext_div23
      + "," + "ext_div24=" +ext_div24
      + "," + "ext_div25=" +ext_div25
      + "," + "ext_div26=" +ext_div26
      + "," + "ext_div27=" +ext_div27
      + "," + "ext_div28=" +ext_div28
      + "," + "ext_div29=" +ext_div29
      + "," + "ext_div30=" +ext_div30
      + "," + "ext_div31=" +ext_div31
      + "," + "ext_div32=" +ext_div32
      + "," + "ext_div33=" +ext_div33
      + "," + "ext_div34=" +ext_div34
      + "," + "ext_div35=" +ext_div35
      + "," + "ext_div36=" +ext_div36
      + "," + "ext_div37=" +ext_div37
      + "," + "ext_div38=" +ext_div38
      + "," + "ext_div39=" +ext_div39
      + "," + "ext_div40=" +ext_div40
      + "," + "ext_code1=" +ext_code1
      + "," + "ext_code2=" +ext_code2
      + "," + "ext_code3=" +ext_code3
      + "," + "ext_code4=" +ext_code4
      + "," + "ext_code5=" +ext_code5
      + "," + "ext_code6=" +ext_code6
      + "," + "ext_code7=" +ext_code7
      + "," + "ext_code8=" +ext_code8
      + "," + "ext_code9=" +ext_code9
      + "," + "ext_code10=" +ext_code10
      + "," + "ext_text1=" +ext_text1
      + "," + "ext_text2=" +ext_text2
      + "," + "ext_text3=" +ext_text3
      + "," + "ext_text4=" +ext_text4
      + "," + "ext_text5=" +ext_text5
      + "," + "ext_text6=" +ext_text6
      + "," + "ext_text7=" +ext_text7
      + "," + "ext_text8=" +ext_text8
      + "," + "ext_text9=" +ext_text9
      + "," + "ext_text10=" +ext_text10
      + "," + "ext_text11=" +ext_text11
      + "," + "ext_text12=" +ext_text12
      + "," + "ext_text13=" +ext_text13
      + "," + "ext_text14=" +ext_text14
      + "," + "ext_text15=" +ext_text15
      + "," + "ext_text16=" +ext_text16
      + "," + "ext_text17=" +ext_text17
      + "," + "ext_text18=" +ext_text18
      + "," + "ext_text19=" +ext_text19
      + "," + "ext_text20=" +ext_text20
      + "," + "ext_text21=" +ext_text21
      + "," + "ext_text22=" +ext_text22
      + "," + "ext_text23=" +ext_text23
      + "," + "ext_text24=" +ext_text24
      + "," + "ext_text25=" +ext_text25
      + "," + "ext_text26=" +ext_text26
      + "," + "ext_text27=" +ext_text27
      + "," + "ext_text28=" +ext_text28
      + "," + "ext_text29=" +ext_text29
      + "," + "ext_text30=" +ext_text30
      + "," + "ext_text31=" +ext_text31
      + "," + "ext_text32=" +ext_text32
      + "," + "ext_text33=" +ext_text33
      + "," + "ext_text34=" +ext_text34
      + "," + "ext_text35=" +ext_text35
      + "," + "ext_text36=" +ext_text36
      + "," + "ext_text37=" +ext_text37
      + "," + "ext_text38=" +ext_text38
      + "," + "ext_text39=" +ext_text39
      + "," + "ext_text40=" +ext_text40
      + "," + "ext_value1=" +ext_value1
      + "," + "ext_value2=" +ext_value2
      + "," + "ext_value3=" +ext_value3
      + "," + "ext_value4=" +ext_value4
      + "," + "ext_value5=" +ext_value5
      + "," + "ext_value6=" +ext_value6
      + "," + "ext_value7=" +ext_value7
      + "," + "ext_value8=" +ext_value8
      + "," + "ext_value9=" +ext_value9
      + "," + "ext_value10=" +ext_value10
      + "," + "ext_value11=" +ext_value11
      + "," + "ext_value12=" +ext_value12
      + "," + "ext_value13=" +ext_value13
      + "," + "ext_value14=" +ext_value14
      + "," + "ext_value15=" +ext_value15
      + "," + "ext_value16=" +ext_value16
      + "," + "ext_value17=" +ext_value17
      + "," + "ext_value18=" +ext_value18
      + "," + "ext_value19=" +ext_value19
      + "," + "ext_value20=" +ext_value20
      + "," + "ext_value21=" +ext_value21
      + "," + "ext_value22=" +ext_value22
      + "," + "ext_value23=" +ext_value23
      + "," + "ext_value24=" +ext_value24
      + "," + "ext_value25=" +ext_value25
      + "," + "ext_value26=" +ext_value26
      + "," + "ext_value27=" +ext_value27
      + "," + "ext_value28=" +ext_value28
      + "," + "ext_value29=" +ext_value29
      + "," + "ext_value30=" +ext_value30
      + "," + "ext_note1=" +ext_note1
      + "," + "ext_note2=" +ext_note2
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "fund_code=" +fund_code
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "status=" +status
      + "," + "error_reason_code=" +error_reason_code
      + "," + "order_request_number=" +order_request_number
      + "," + "request_code=" +request_code
      + "," + "send_timestamp=" +send_timestamp
      + "," + "receipt_timestamp=" +receipt_timestamp
      + "}";
  }


  /** 
   * 値が未設定のVariousInformParamsオブジェクトを作成します。 
   */
  public VariousInformParams() {
    institution_code_is_modified = true;
    inform_div_is_modified = true;
    request_number_is_modified = true;
    branch_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のVariousInformRowオブジェクトの値を利用してVariousInformParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するVariousInformRowオブジェクト 
   */
  public VariousInformParams( VariousInformRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    inform_div = p_row.getInformDiv();
    inform_div_is_set = p_row.getInformDivIsSet();
    inform_div_is_modified = p_row.getInformDivIsModified();
    request_number = p_row.getRequestNumber();
    request_number_is_set = p_row.getRequestNumberIsSet();
    request_number_is_modified = p_row.getRequestNumberIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    account_name = p_row.getAccountName();
    account_name_is_set = p_row.getAccountNameIsSet();
    account_name_is_modified = p_row.getAccountNameIsModified();
    email_address = p_row.getEmailAddress();
    email_address_is_set = p_row.getEmailAddressIsSet();
    email_address_is_modified = p_row.getEmailAddressIsModified();
    ext_div1 = p_row.getExtDiv1();
    ext_div1_is_set = p_row.getExtDiv1IsSet();
    ext_div1_is_modified = p_row.getExtDiv1IsModified();
    ext_div2 = p_row.getExtDiv2();
    ext_div2_is_set = p_row.getExtDiv2IsSet();
    ext_div2_is_modified = p_row.getExtDiv2IsModified();
    ext_div3 = p_row.getExtDiv3();
    ext_div3_is_set = p_row.getExtDiv3IsSet();
    ext_div3_is_modified = p_row.getExtDiv3IsModified();
    ext_div4 = p_row.getExtDiv4();
    ext_div4_is_set = p_row.getExtDiv4IsSet();
    ext_div4_is_modified = p_row.getExtDiv4IsModified();
    ext_div5 = p_row.getExtDiv5();
    ext_div5_is_set = p_row.getExtDiv5IsSet();
    ext_div5_is_modified = p_row.getExtDiv5IsModified();
    ext_div6 = p_row.getExtDiv6();
    ext_div6_is_set = p_row.getExtDiv6IsSet();
    ext_div6_is_modified = p_row.getExtDiv6IsModified();
    ext_div7 = p_row.getExtDiv7();
    ext_div7_is_set = p_row.getExtDiv7IsSet();
    ext_div7_is_modified = p_row.getExtDiv7IsModified();
    ext_div8 = p_row.getExtDiv8();
    ext_div8_is_set = p_row.getExtDiv8IsSet();
    ext_div8_is_modified = p_row.getExtDiv8IsModified();
    ext_div9 = p_row.getExtDiv9();
    ext_div9_is_set = p_row.getExtDiv9IsSet();
    ext_div9_is_modified = p_row.getExtDiv9IsModified();
    ext_div10 = p_row.getExtDiv10();
    ext_div10_is_set = p_row.getExtDiv10IsSet();
    ext_div10_is_modified = p_row.getExtDiv10IsModified();
    ext_div11 = p_row.getExtDiv11();
    ext_div11_is_set = p_row.getExtDiv11IsSet();
    ext_div11_is_modified = p_row.getExtDiv11IsModified();
    ext_div12 = p_row.getExtDiv12();
    ext_div12_is_set = p_row.getExtDiv12IsSet();
    ext_div12_is_modified = p_row.getExtDiv12IsModified();
    ext_div13 = p_row.getExtDiv13();
    ext_div13_is_set = p_row.getExtDiv13IsSet();
    ext_div13_is_modified = p_row.getExtDiv13IsModified();
    ext_div14 = p_row.getExtDiv14();
    ext_div14_is_set = p_row.getExtDiv14IsSet();
    ext_div14_is_modified = p_row.getExtDiv14IsModified();
    ext_div15 = p_row.getExtDiv15();
    ext_div15_is_set = p_row.getExtDiv15IsSet();
    ext_div15_is_modified = p_row.getExtDiv15IsModified();
    ext_div16 = p_row.getExtDiv16();
    ext_div16_is_set = p_row.getExtDiv16IsSet();
    ext_div16_is_modified = p_row.getExtDiv16IsModified();
    ext_div17 = p_row.getExtDiv17();
    ext_div17_is_set = p_row.getExtDiv17IsSet();
    ext_div17_is_modified = p_row.getExtDiv17IsModified();
    ext_div18 = p_row.getExtDiv18();
    ext_div18_is_set = p_row.getExtDiv18IsSet();
    ext_div18_is_modified = p_row.getExtDiv18IsModified();
    ext_div19 = p_row.getExtDiv19();
    ext_div19_is_set = p_row.getExtDiv19IsSet();
    ext_div19_is_modified = p_row.getExtDiv19IsModified();
    ext_div20 = p_row.getExtDiv20();
    ext_div20_is_set = p_row.getExtDiv20IsSet();
    ext_div20_is_modified = p_row.getExtDiv20IsModified();
    ext_div21 = p_row.getExtDiv21();
    ext_div21_is_set = p_row.getExtDiv21IsSet();
    ext_div21_is_modified = p_row.getExtDiv21IsModified();
    ext_div22 = p_row.getExtDiv22();
    ext_div22_is_set = p_row.getExtDiv22IsSet();
    ext_div22_is_modified = p_row.getExtDiv22IsModified();
    ext_div23 = p_row.getExtDiv23();
    ext_div23_is_set = p_row.getExtDiv23IsSet();
    ext_div23_is_modified = p_row.getExtDiv23IsModified();
    ext_div24 = p_row.getExtDiv24();
    ext_div24_is_set = p_row.getExtDiv24IsSet();
    ext_div24_is_modified = p_row.getExtDiv24IsModified();
    ext_div25 = p_row.getExtDiv25();
    ext_div25_is_set = p_row.getExtDiv25IsSet();
    ext_div25_is_modified = p_row.getExtDiv25IsModified();
    ext_div26 = p_row.getExtDiv26();
    ext_div26_is_set = p_row.getExtDiv26IsSet();
    ext_div26_is_modified = p_row.getExtDiv26IsModified();
    ext_div27 = p_row.getExtDiv27();
    ext_div27_is_set = p_row.getExtDiv27IsSet();
    ext_div27_is_modified = p_row.getExtDiv27IsModified();
    ext_div28 = p_row.getExtDiv28();
    ext_div28_is_set = p_row.getExtDiv28IsSet();
    ext_div28_is_modified = p_row.getExtDiv28IsModified();
    ext_div29 = p_row.getExtDiv29();
    ext_div29_is_set = p_row.getExtDiv29IsSet();
    ext_div29_is_modified = p_row.getExtDiv29IsModified();
    ext_div30 = p_row.getExtDiv30();
    ext_div30_is_set = p_row.getExtDiv30IsSet();
    ext_div30_is_modified = p_row.getExtDiv30IsModified();
    ext_div31 = p_row.getExtDiv31();
    ext_div31_is_set = p_row.getExtDiv31IsSet();
    ext_div31_is_modified = p_row.getExtDiv31IsModified();
    ext_div32 = p_row.getExtDiv32();
    ext_div32_is_set = p_row.getExtDiv32IsSet();
    ext_div32_is_modified = p_row.getExtDiv32IsModified();
    ext_div33 = p_row.getExtDiv33();
    ext_div33_is_set = p_row.getExtDiv33IsSet();
    ext_div33_is_modified = p_row.getExtDiv33IsModified();
    ext_div34 = p_row.getExtDiv34();
    ext_div34_is_set = p_row.getExtDiv34IsSet();
    ext_div34_is_modified = p_row.getExtDiv34IsModified();
    ext_div35 = p_row.getExtDiv35();
    ext_div35_is_set = p_row.getExtDiv35IsSet();
    ext_div35_is_modified = p_row.getExtDiv35IsModified();
    ext_div36 = p_row.getExtDiv36();
    ext_div36_is_set = p_row.getExtDiv36IsSet();
    ext_div36_is_modified = p_row.getExtDiv36IsModified();
    ext_div37 = p_row.getExtDiv37();
    ext_div37_is_set = p_row.getExtDiv37IsSet();
    ext_div37_is_modified = p_row.getExtDiv37IsModified();
    ext_div38 = p_row.getExtDiv38();
    ext_div38_is_set = p_row.getExtDiv38IsSet();
    ext_div38_is_modified = p_row.getExtDiv38IsModified();
    ext_div39 = p_row.getExtDiv39();
    ext_div39_is_set = p_row.getExtDiv39IsSet();
    ext_div39_is_modified = p_row.getExtDiv39IsModified();
    ext_div40 = p_row.getExtDiv40();
    ext_div40_is_set = p_row.getExtDiv40IsSet();
    ext_div40_is_modified = p_row.getExtDiv40IsModified();
    ext_code1 = p_row.getExtCode1();
    ext_code1_is_set = p_row.getExtCode1IsSet();
    ext_code1_is_modified = p_row.getExtCode1IsModified();
    ext_code2 = p_row.getExtCode2();
    ext_code2_is_set = p_row.getExtCode2IsSet();
    ext_code2_is_modified = p_row.getExtCode2IsModified();
    ext_code3 = p_row.getExtCode3();
    ext_code3_is_set = p_row.getExtCode3IsSet();
    ext_code3_is_modified = p_row.getExtCode3IsModified();
    ext_code4 = p_row.getExtCode4();
    ext_code4_is_set = p_row.getExtCode4IsSet();
    ext_code4_is_modified = p_row.getExtCode4IsModified();
    ext_code5 = p_row.getExtCode5();
    ext_code5_is_set = p_row.getExtCode5IsSet();
    ext_code5_is_modified = p_row.getExtCode5IsModified();
    ext_code6 = p_row.getExtCode6();
    ext_code6_is_set = p_row.getExtCode6IsSet();
    ext_code6_is_modified = p_row.getExtCode6IsModified();
    ext_code7 = p_row.getExtCode7();
    ext_code7_is_set = p_row.getExtCode7IsSet();
    ext_code7_is_modified = p_row.getExtCode7IsModified();
    ext_code8 = p_row.getExtCode8();
    ext_code8_is_set = p_row.getExtCode8IsSet();
    ext_code8_is_modified = p_row.getExtCode8IsModified();
    ext_code9 = p_row.getExtCode9();
    ext_code9_is_set = p_row.getExtCode9IsSet();
    ext_code9_is_modified = p_row.getExtCode9IsModified();
    ext_code10 = p_row.getExtCode10();
    ext_code10_is_set = p_row.getExtCode10IsSet();
    ext_code10_is_modified = p_row.getExtCode10IsModified();
    ext_text1 = p_row.getExtText1();
    ext_text1_is_set = p_row.getExtText1IsSet();
    ext_text1_is_modified = p_row.getExtText1IsModified();
    ext_text2 = p_row.getExtText2();
    ext_text2_is_set = p_row.getExtText2IsSet();
    ext_text2_is_modified = p_row.getExtText2IsModified();
    ext_text3 = p_row.getExtText3();
    ext_text3_is_set = p_row.getExtText3IsSet();
    ext_text3_is_modified = p_row.getExtText3IsModified();
    ext_text4 = p_row.getExtText4();
    ext_text4_is_set = p_row.getExtText4IsSet();
    ext_text4_is_modified = p_row.getExtText4IsModified();
    ext_text5 = p_row.getExtText5();
    ext_text5_is_set = p_row.getExtText5IsSet();
    ext_text5_is_modified = p_row.getExtText5IsModified();
    ext_text6 = p_row.getExtText6();
    ext_text6_is_set = p_row.getExtText6IsSet();
    ext_text6_is_modified = p_row.getExtText6IsModified();
    ext_text7 = p_row.getExtText7();
    ext_text7_is_set = p_row.getExtText7IsSet();
    ext_text7_is_modified = p_row.getExtText7IsModified();
    ext_text8 = p_row.getExtText8();
    ext_text8_is_set = p_row.getExtText8IsSet();
    ext_text8_is_modified = p_row.getExtText8IsModified();
    ext_text9 = p_row.getExtText9();
    ext_text9_is_set = p_row.getExtText9IsSet();
    ext_text9_is_modified = p_row.getExtText9IsModified();
    ext_text10 = p_row.getExtText10();
    ext_text10_is_set = p_row.getExtText10IsSet();
    ext_text10_is_modified = p_row.getExtText10IsModified();
    ext_text11 = p_row.getExtText11();
    ext_text11_is_set = p_row.getExtText11IsSet();
    ext_text11_is_modified = p_row.getExtText11IsModified();
    ext_text12 = p_row.getExtText12();
    ext_text12_is_set = p_row.getExtText12IsSet();
    ext_text12_is_modified = p_row.getExtText12IsModified();
    ext_text13 = p_row.getExtText13();
    ext_text13_is_set = p_row.getExtText13IsSet();
    ext_text13_is_modified = p_row.getExtText13IsModified();
    ext_text14 = p_row.getExtText14();
    ext_text14_is_set = p_row.getExtText14IsSet();
    ext_text14_is_modified = p_row.getExtText14IsModified();
    ext_text15 = p_row.getExtText15();
    ext_text15_is_set = p_row.getExtText15IsSet();
    ext_text15_is_modified = p_row.getExtText15IsModified();
    ext_text16 = p_row.getExtText16();
    ext_text16_is_set = p_row.getExtText16IsSet();
    ext_text16_is_modified = p_row.getExtText16IsModified();
    ext_text17 = p_row.getExtText17();
    ext_text17_is_set = p_row.getExtText17IsSet();
    ext_text17_is_modified = p_row.getExtText17IsModified();
    ext_text18 = p_row.getExtText18();
    ext_text18_is_set = p_row.getExtText18IsSet();
    ext_text18_is_modified = p_row.getExtText18IsModified();
    ext_text19 = p_row.getExtText19();
    ext_text19_is_set = p_row.getExtText19IsSet();
    ext_text19_is_modified = p_row.getExtText19IsModified();
    ext_text20 = p_row.getExtText20();
    ext_text20_is_set = p_row.getExtText20IsSet();
    ext_text20_is_modified = p_row.getExtText20IsModified();
    ext_text21 = p_row.getExtText21();
    ext_text21_is_set = p_row.getExtText21IsSet();
    ext_text21_is_modified = p_row.getExtText21IsModified();
    ext_text22 = p_row.getExtText22();
    ext_text22_is_set = p_row.getExtText22IsSet();
    ext_text22_is_modified = p_row.getExtText22IsModified();
    ext_text23 = p_row.getExtText23();
    ext_text23_is_set = p_row.getExtText23IsSet();
    ext_text23_is_modified = p_row.getExtText23IsModified();
    ext_text24 = p_row.getExtText24();
    ext_text24_is_set = p_row.getExtText24IsSet();
    ext_text24_is_modified = p_row.getExtText24IsModified();
    ext_text25 = p_row.getExtText25();
    ext_text25_is_set = p_row.getExtText25IsSet();
    ext_text25_is_modified = p_row.getExtText25IsModified();
    ext_text26 = p_row.getExtText26();
    ext_text26_is_set = p_row.getExtText26IsSet();
    ext_text26_is_modified = p_row.getExtText26IsModified();
    ext_text27 = p_row.getExtText27();
    ext_text27_is_set = p_row.getExtText27IsSet();
    ext_text27_is_modified = p_row.getExtText27IsModified();
    ext_text28 = p_row.getExtText28();
    ext_text28_is_set = p_row.getExtText28IsSet();
    ext_text28_is_modified = p_row.getExtText28IsModified();
    ext_text29 = p_row.getExtText29();
    ext_text29_is_set = p_row.getExtText29IsSet();
    ext_text29_is_modified = p_row.getExtText29IsModified();
    ext_text30 = p_row.getExtText30();
    ext_text30_is_set = p_row.getExtText30IsSet();
    ext_text30_is_modified = p_row.getExtText30IsModified();
    ext_text31 = p_row.getExtText31();
    ext_text31_is_set = p_row.getExtText31IsSet();
    ext_text31_is_modified = p_row.getExtText31IsModified();
    ext_text32 = p_row.getExtText32();
    ext_text32_is_set = p_row.getExtText32IsSet();
    ext_text32_is_modified = p_row.getExtText32IsModified();
    ext_text33 = p_row.getExtText33();
    ext_text33_is_set = p_row.getExtText33IsSet();
    ext_text33_is_modified = p_row.getExtText33IsModified();
    ext_text34 = p_row.getExtText34();
    ext_text34_is_set = p_row.getExtText34IsSet();
    ext_text34_is_modified = p_row.getExtText34IsModified();
    ext_text35 = p_row.getExtText35();
    ext_text35_is_set = p_row.getExtText35IsSet();
    ext_text35_is_modified = p_row.getExtText35IsModified();
    ext_text36 = p_row.getExtText36();
    ext_text36_is_set = p_row.getExtText36IsSet();
    ext_text36_is_modified = p_row.getExtText36IsModified();
    ext_text37 = p_row.getExtText37();
    ext_text37_is_set = p_row.getExtText37IsSet();
    ext_text37_is_modified = p_row.getExtText37IsModified();
    ext_text38 = p_row.getExtText38();
    ext_text38_is_set = p_row.getExtText38IsSet();
    ext_text38_is_modified = p_row.getExtText38IsModified();
    ext_text39 = p_row.getExtText39();
    ext_text39_is_set = p_row.getExtText39IsSet();
    ext_text39_is_modified = p_row.getExtText39IsModified();
    ext_text40 = p_row.getExtText40();
    ext_text40_is_set = p_row.getExtText40IsSet();
    ext_text40_is_modified = p_row.getExtText40IsModified();
    if ( !p_row.getExtValue1IsNull() )
      ext_value1 = new Long( p_row.getExtValue1() );
    ext_value1_is_set = p_row.getExtValue1IsSet();
    ext_value1_is_modified = p_row.getExtValue1IsModified();
    if ( !p_row.getExtValue2IsNull() )
      ext_value2 = new Long( p_row.getExtValue2() );
    ext_value2_is_set = p_row.getExtValue2IsSet();
    ext_value2_is_modified = p_row.getExtValue2IsModified();
    if ( !p_row.getExtValue3IsNull() )
      ext_value3 = new Long( p_row.getExtValue3() );
    ext_value3_is_set = p_row.getExtValue3IsSet();
    ext_value3_is_modified = p_row.getExtValue3IsModified();
    if ( !p_row.getExtValue4IsNull() )
      ext_value4 = new Long( p_row.getExtValue4() );
    ext_value4_is_set = p_row.getExtValue4IsSet();
    ext_value4_is_modified = p_row.getExtValue4IsModified();
    if ( !p_row.getExtValue5IsNull() )
      ext_value5 = new Long( p_row.getExtValue5() );
    ext_value5_is_set = p_row.getExtValue5IsSet();
    ext_value5_is_modified = p_row.getExtValue5IsModified();
    if ( !p_row.getExtValue6IsNull() )
      ext_value6 = new Long( p_row.getExtValue6() );
    ext_value6_is_set = p_row.getExtValue6IsSet();
    ext_value6_is_modified = p_row.getExtValue6IsModified();
    if ( !p_row.getExtValue7IsNull() )
      ext_value7 = new Long( p_row.getExtValue7() );
    ext_value7_is_set = p_row.getExtValue7IsSet();
    ext_value7_is_modified = p_row.getExtValue7IsModified();
    if ( !p_row.getExtValue8IsNull() )
      ext_value8 = new Long( p_row.getExtValue8() );
    ext_value8_is_set = p_row.getExtValue8IsSet();
    ext_value8_is_modified = p_row.getExtValue8IsModified();
    if ( !p_row.getExtValue9IsNull() )
      ext_value9 = new Long( p_row.getExtValue9() );
    ext_value9_is_set = p_row.getExtValue9IsSet();
    ext_value9_is_modified = p_row.getExtValue9IsModified();
    if ( !p_row.getExtValue10IsNull() )
      ext_value10 = new Long( p_row.getExtValue10() );
    ext_value10_is_set = p_row.getExtValue10IsSet();
    ext_value10_is_modified = p_row.getExtValue10IsModified();
    if ( !p_row.getExtValue11IsNull() )
      ext_value11 = new Long( p_row.getExtValue11() );
    ext_value11_is_set = p_row.getExtValue11IsSet();
    ext_value11_is_modified = p_row.getExtValue11IsModified();
    if ( !p_row.getExtValue12IsNull() )
      ext_value12 = new Long( p_row.getExtValue12() );
    ext_value12_is_set = p_row.getExtValue12IsSet();
    ext_value12_is_modified = p_row.getExtValue12IsModified();
    if ( !p_row.getExtValue13IsNull() )
      ext_value13 = new Long( p_row.getExtValue13() );
    ext_value13_is_set = p_row.getExtValue13IsSet();
    ext_value13_is_modified = p_row.getExtValue13IsModified();
    if ( !p_row.getExtValue14IsNull() )
      ext_value14 = new Long( p_row.getExtValue14() );
    ext_value14_is_set = p_row.getExtValue14IsSet();
    ext_value14_is_modified = p_row.getExtValue14IsModified();
    if ( !p_row.getExtValue15IsNull() )
      ext_value15 = new Long( p_row.getExtValue15() );
    ext_value15_is_set = p_row.getExtValue15IsSet();
    ext_value15_is_modified = p_row.getExtValue15IsModified();
    if ( !p_row.getExtValue16IsNull() )
      ext_value16 = new Long( p_row.getExtValue16() );
    ext_value16_is_set = p_row.getExtValue16IsSet();
    ext_value16_is_modified = p_row.getExtValue16IsModified();
    if ( !p_row.getExtValue17IsNull() )
      ext_value17 = new Long( p_row.getExtValue17() );
    ext_value17_is_set = p_row.getExtValue17IsSet();
    ext_value17_is_modified = p_row.getExtValue17IsModified();
    if ( !p_row.getExtValue18IsNull() )
      ext_value18 = new Long( p_row.getExtValue18() );
    ext_value18_is_set = p_row.getExtValue18IsSet();
    ext_value18_is_modified = p_row.getExtValue18IsModified();
    if ( !p_row.getExtValue19IsNull() )
      ext_value19 = new Long( p_row.getExtValue19() );
    ext_value19_is_set = p_row.getExtValue19IsSet();
    ext_value19_is_modified = p_row.getExtValue19IsModified();
    if ( !p_row.getExtValue20IsNull() )
      ext_value20 = new Long( p_row.getExtValue20() );
    ext_value20_is_set = p_row.getExtValue20IsSet();
    ext_value20_is_modified = p_row.getExtValue20IsModified();
    if ( !p_row.getExtValue21IsNull() )
      ext_value21 = new Long( p_row.getExtValue21() );
    ext_value21_is_set = p_row.getExtValue21IsSet();
    ext_value21_is_modified = p_row.getExtValue21IsModified();
    if ( !p_row.getExtValue22IsNull() )
      ext_value22 = new Long( p_row.getExtValue22() );
    ext_value22_is_set = p_row.getExtValue22IsSet();
    ext_value22_is_modified = p_row.getExtValue22IsModified();
    if ( !p_row.getExtValue23IsNull() )
      ext_value23 = new Long( p_row.getExtValue23() );
    ext_value23_is_set = p_row.getExtValue23IsSet();
    ext_value23_is_modified = p_row.getExtValue23IsModified();
    if ( !p_row.getExtValue24IsNull() )
      ext_value24 = new Long( p_row.getExtValue24() );
    ext_value24_is_set = p_row.getExtValue24IsSet();
    ext_value24_is_modified = p_row.getExtValue24IsModified();
    if ( !p_row.getExtValue25IsNull() )
      ext_value25 = new Long( p_row.getExtValue25() );
    ext_value25_is_set = p_row.getExtValue25IsSet();
    ext_value25_is_modified = p_row.getExtValue25IsModified();
    if ( !p_row.getExtValue26IsNull() )
      ext_value26 = new Long( p_row.getExtValue26() );
    ext_value26_is_set = p_row.getExtValue26IsSet();
    ext_value26_is_modified = p_row.getExtValue26IsModified();
    if ( !p_row.getExtValue27IsNull() )
      ext_value27 = new Long( p_row.getExtValue27() );
    ext_value27_is_set = p_row.getExtValue27IsSet();
    ext_value27_is_modified = p_row.getExtValue27IsModified();
    if ( !p_row.getExtValue28IsNull() )
      ext_value28 = new Long( p_row.getExtValue28() );
    ext_value28_is_set = p_row.getExtValue28IsSet();
    ext_value28_is_modified = p_row.getExtValue28IsModified();
    if ( !p_row.getExtValue29IsNull() )
      ext_value29 = new Long( p_row.getExtValue29() );
    ext_value29_is_set = p_row.getExtValue29IsSet();
    ext_value29_is_modified = p_row.getExtValue29IsModified();
    if ( !p_row.getExtValue30IsNull() )
      ext_value30 = new Long( p_row.getExtValue30() );
    ext_value30_is_set = p_row.getExtValue30IsSet();
    ext_value30_is_modified = p_row.getExtValue30IsModified();
    ext_note1 = p_row.getExtNote1();
    ext_note1_is_set = p_row.getExtNote1IsSet();
    ext_note1_is_modified = p_row.getExtNote1IsModified();
    ext_note2 = p_row.getExtNote2();
    ext_note2_is_set = p_row.getExtNote2IsSet();
    ext_note2_is_modified = p_row.getExtNote2IsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    fund_code = p_row.getFundCode();
    fund_code_is_set = p_row.getFundCodeIsSet();
    fund_code_is_modified = p_row.getFundCodeIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    error_reason_code = p_row.getErrorReasonCode();
    error_reason_code_is_set = p_row.getErrorReasonCodeIsSet();
    error_reason_code_is_modified = p_row.getErrorReasonCodeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    send_timestamp = p_row.getSendTimestamp();
    send_timestamp_is_set = p_row.getSendTimestampIsSet();
    send_timestamp_is_modified = p_row.getSendTimestampIsModified();
    receipt_timestamp = p_row.getReceiptTimestamp();
    receipt_timestamp_is_set = p_row.getReceiptTimestampIsSet();
    receipt_timestamp_is_modified = p_row.getReceiptTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      account_name_is_set = true;
      account_name_is_modified = true;
      email_address_is_set = true;
      email_address_is_modified = true;
      ext_div1_is_set = true;
      ext_div1_is_modified = true;
      ext_div2_is_set = true;
      ext_div2_is_modified = true;
      ext_div3_is_set = true;
      ext_div3_is_modified = true;
      ext_div4_is_set = true;
      ext_div4_is_modified = true;
      ext_div5_is_set = true;
      ext_div5_is_modified = true;
      ext_div6_is_set = true;
      ext_div6_is_modified = true;
      ext_div7_is_set = true;
      ext_div7_is_modified = true;
      ext_div8_is_set = true;
      ext_div8_is_modified = true;
      ext_div9_is_set = true;
      ext_div9_is_modified = true;
      ext_div10_is_set = true;
      ext_div10_is_modified = true;
      ext_div11_is_set = true;
      ext_div11_is_modified = true;
      ext_div12_is_set = true;
      ext_div12_is_modified = true;
      ext_div13_is_set = true;
      ext_div13_is_modified = true;
      ext_div14_is_set = true;
      ext_div14_is_modified = true;
      ext_div15_is_set = true;
      ext_div15_is_modified = true;
      ext_div16_is_set = true;
      ext_div16_is_modified = true;
      ext_div17_is_set = true;
      ext_div17_is_modified = true;
      ext_div18_is_set = true;
      ext_div18_is_modified = true;
      ext_div19_is_set = true;
      ext_div19_is_modified = true;
      ext_div20_is_set = true;
      ext_div20_is_modified = true;
      ext_div21_is_set = true;
      ext_div21_is_modified = true;
      ext_div22_is_set = true;
      ext_div22_is_modified = true;
      ext_div23_is_set = true;
      ext_div23_is_modified = true;
      ext_div24_is_set = true;
      ext_div24_is_modified = true;
      ext_div25_is_set = true;
      ext_div25_is_modified = true;
      ext_div26_is_set = true;
      ext_div26_is_modified = true;
      ext_div27_is_set = true;
      ext_div27_is_modified = true;
      ext_div28_is_set = true;
      ext_div28_is_modified = true;
      ext_div29_is_set = true;
      ext_div29_is_modified = true;
      ext_div30_is_set = true;
      ext_div30_is_modified = true;
      ext_div31_is_set = true;
      ext_div31_is_modified = true;
      ext_div32_is_set = true;
      ext_div32_is_modified = true;
      ext_div33_is_set = true;
      ext_div33_is_modified = true;
      ext_div34_is_set = true;
      ext_div34_is_modified = true;
      ext_div35_is_set = true;
      ext_div35_is_modified = true;
      ext_div36_is_set = true;
      ext_div36_is_modified = true;
      ext_div37_is_set = true;
      ext_div37_is_modified = true;
      ext_div38_is_set = true;
      ext_div38_is_modified = true;
      ext_div39_is_set = true;
      ext_div39_is_modified = true;
      ext_div40_is_set = true;
      ext_div40_is_modified = true;
      ext_code1_is_set = true;
      ext_code1_is_modified = true;
      ext_code2_is_set = true;
      ext_code2_is_modified = true;
      ext_code3_is_set = true;
      ext_code3_is_modified = true;
      ext_code4_is_set = true;
      ext_code4_is_modified = true;
      ext_code5_is_set = true;
      ext_code5_is_modified = true;
      ext_code6_is_set = true;
      ext_code6_is_modified = true;
      ext_code7_is_set = true;
      ext_code7_is_modified = true;
      ext_code8_is_set = true;
      ext_code8_is_modified = true;
      ext_code9_is_set = true;
      ext_code9_is_modified = true;
      ext_code10_is_set = true;
      ext_code10_is_modified = true;
      ext_text1_is_set = true;
      ext_text1_is_modified = true;
      ext_text2_is_set = true;
      ext_text2_is_modified = true;
      ext_text3_is_set = true;
      ext_text3_is_modified = true;
      ext_text4_is_set = true;
      ext_text4_is_modified = true;
      ext_text5_is_set = true;
      ext_text5_is_modified = true;
      ext_text6_is_set = true;
      ext_text6_is_modified = true;
      ext_text7_is_set = true;
      ext_text7_is_modified = true;
      ext_text8_is_set = true;
      ext_text8_is_modified = true;
      ext_text9_is_set = true;
      ext_text9_is_modified = true;
      ext_text10_is_set = true;
      ext_text10_is_modified = true;
      ext_text11_is_set = true;
      ext_text11_is_modified = true;
      ext_text12_is_set = true;
      ext_text12_is_modified = true;
      ext_text13_is_set = true;
      ext_text13_is_modified = true;
      ext_text14_is_set = true;
      ext_text14_is_modified = true;
      ext_text15_is_set = true;
      ext_text15_is_modified = true;
      ext_text16_is_set = true;
      ext_text16_is_modified = true;
      ext_text17_is_set = true;
      ext_text17_is_modified = true;
      ext_text18_is_set = true;
      ext_text18_is_modified = true;
      ext_text19_is_set = true;
      ext_text19_is_modified = true;
      ext_text20_is_set = true;
      ext_text20_is_modified = true;
      ext_text21_is_set = true;
      ext_text21_is_modified = true;
      ext_text22_is_set = true;
      ext_text22_is_modified = true;
      ext_text23_is_set = true;
      ext_text23_is_modified = true;
      ext_text24_is_set = true;
      ext_text24_is_modified = true;
      ext_text25_is_set = true;
      ext_text25_is_modified = true;
      ext_text26_is_set = true;
      ext_text26_is_modified = true;
      ext_text27_is_set = true;
      ext_text27_is_modified = true;
      ext_text28_is_set = true;
      ext_text28_is_modified = true;
      ext_text29_is_set = true;
      ext_text29_is_modified = true;
      ext_text30_is_set = true;
      ext_text30_is_modified = true;
      ext_text31_is_set = true;
      ext_text31_is_modified = true;
      ext_text32_is_set = true;
      ext_text32_is_modified = true;
      ext_text33_is_set = true;
      ext_text33_is_modified = true;
      ext_text34_is_set = true;
      ext_text34_is_modified = true;
      ext_text35_is_set = true;
      ext_text35_is_modified = true;
      ext_text36_is_set = true;
      ext_text36_is_modified = true;
      ext_text37_is_set = true;
      ext_text37_is_modified = true;
      ext_text38_is_set = true;
      ext_text38_is_modified = true;
      ext_text39_is_set = true;
      ext_text39_is_modified = true;
      ext_text40_is_set = true;
      ext_text40_is_modified = true;
      ext_value1_is_set = true;
      ext_value1_is_modified = true;
      ext_value2_is_set = true;
      ext_value2_is_modified = true;
      ext_value3_is_set = true;
      ext_value3_is_modified = true;
      ext_value4_is_set = true;
      ext_value4_is_modified = true;
      ext_value5_is_set = true;
      ext_value5_is_modified = true;
      ext_value6_is_set = true;
      ext_value6_is_modified = true;
      ext_value7_is_set = true;
      ext_value7_is_modified = true;
      ext_value8_is_set = true;
      ext_value8_is_modified = true;
      ext_value9_is_set = true;
      ext_value9_is_modified = true;
      ext_value10_is_set = true;
      ext_value10_is_modified = true;
      ext_value11_is_set = true;
      ext_value11_is_modified = true;
      ext_value12_is_set = true;
      ext_value12_is_modified = true;
      ext_value13_is_set = true;
      ext_value13_is_modified = true;
      ext_value14_is_set = true;
      ext_value14_is_modified = true;
      ext_value15_is_set = true;
      ext_value15_is_modified = true;
      ext_value16_is_set = true;
      ext_value16_is_modified = true;
      ext_value17_is_set = true;
      ext_value17_is_modified = true;
      ext_value18_is_set = true;
      ext_value18_is_modified = true;
      ext_value19_is_set = true;
      ext_value19_is_modified = true;
      ext_value20_is_set = true;
      ext_value20_is_modified = true;
      ext_value21_is_set = true;
      ext_value21_is_modified = true;
      ext_value22_is_set = true;
      ext_value22_is_modified = true;
      ext_value23_is_set = true;
      ext_value23_is_modified = true;
      ext_value24_is_set = true;
      ext_value24_is_modified = true;
      ext_value25_is_set = true;
      ext_value25_is_modified = true;
      ext_value26_is_set = true;
      ext_value26_is_modified = true;
      ext_value27_is_set = true;
      ext_value27_is_modified = true;
      ext_value28_is_set = true;
      ext_value28_is_modified = true;
      ext_value29_is_set = true;
      ext_value29_is_modified = true;
      ext_value30_is_set = true;
      ext_value30_is_modified = true;
      ext_note1_is_set = true;
      ext_note1_is_modified = true;
      ext_note2_is_set = true;
      ext_note2_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      fund_code_is_set = true;
      fund_code_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      error_reason_code_is_set = true;
      error_reason_code_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      request_code_is_set = true;
      request_code_is_modified = true;
      send_timestamp_is_set = true;
      send_timestamp_is_modified = true;
      receipt_timestamp_is_set = true;
      receipt_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof VariousInformRow ) )
       return false;
    return fieldsEqual( (VariousInformRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のVariousInformRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( VariousInformRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( inform_div == null ) {
      if ( row.getInformDiv() != null )
        return false;
    } else if ( !inform_div.equals( row.getInformDiv() ) ) {
        return false;
    }
    if ( request_number == null ) {
      if ( row.getRequestNumber() != null )
        return false;
    } else if ( !request_number.equals( row.getRequestNumber() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( trader_code == null ) {
      if ( row.getTraderCode() != null )
        return false;
    } else if ( !trader_code.equals( row.getTraderCode() ) ) {
        return false;
    }
    if ( account_name == null ) {
      if ( row.getAccountName() != null )
        return false;
    } else if ( !account_name.equals( row.getAccountName() ) ) {
        return false;
    }
    if ( email_address == null ) {
      if ( row.getEmailAddress() != null )
        return false;
    } else if ( !email_address.equals( row.getEmailAddress() ) ) {
        return false;
    }
    if ( ext_div1 == null ) {
      if ( row.getExtDiv1() != null )
        return false;
    } else if ( !ext_div1.equals( row.getExtDiv1() ) ) {
        return false;
    }
    if ( ext_div2 == null ) {
      if ( row.getExtDiv2() != null )
        return false;
    } else if ( !ext_div2.equals( row.getExtDiv2() ) ) {
        return false;
    }
    if ( ext_div3 == null ) {
      if ( row.getExtDiv3() != null )
        return false;
    } else if ( !ext_div3.equals( row.getExtDiv3() ) ) {
        return false;
    }
    if ( ext_div4 == null ) {
      if ( row.getExtDiv4() != null )
        return false;
    } else if ( !ext_div4.equals( row.getExtDiv4() ) ) {
        return false;
    }
    if ( ext_div5 == null ) {
      if ( row.getExtDiv5() != null )
        return false;
    } else if ( !ext_div5.equals( row.getExtDiv5() ) ) {
        return false;
    }
    if ( ext_div6 == null ) {
      if ( row.getExtDiv6() != null )
        return false;
    } else if ( !ext_div6.equals( row.getExtDiv6() ) ) {
        return false;
    }
    if ( ext_div7 == null ) {
      if ( row.getExtDiv7() != null )
        return false;
    } else if ( !ext_div7.equals( row.getExtDiv7() ) ) {
        return false;
    }
    if ( ext_div8 == null ) {
      if ( row.getExtDiv8() != null )
        return false;
    } else if ( !ext_div8.equals( row.getExtDiv8() ) ) {
        return false;
    }
    if ( ext_div9 == null ) {
      if ( row.getExtDiv9() != null )
        return false;
    } else if ( !ext_div9.equals( row.getExtDiv9() ) ) {
        return false;
    }
    if ( ext_div10 == null ) {
      if ( row.getExtDiv10() != null )
        return false;
    } else if ( !ext_div10.equals( row.getExtDiv10() ) ) {
        return false;
    }
    if ( ext_div11 == null ) {
      if ( row.getExtDiv11() != null )
        return false;
    } else if ( !ext_div11.equals( row.getExtDiv11() ) ) {
        return false;
    }
    if ( ext_div12 == null ) {
      if ( row.getExtDiv12() != null )
        return false;
    } else if ( !ext_div12.equals( row.getExtDiv12() ) ) {
        return false;
    }
    if ( ext_div13 == null ) {
      if ( row.getExtDiv13() != null )
        return false;
    } else if ( !ext_div13.equals( row.getExtDiv13() ) ) {
        return false;
    }
    if ( ext_div14 == null ) {
      if ( row.getExtDiv14() != null )
        return false;
    } else if ( !ext_div14.equals( row.getExtDiv14() ) ) {
        return false;
    }
    if ( ext_div15 == null ) {
      if ( row.getExtDiv15() != null )
        return false;
    } else if ( !ext_div15.equals( row.getExtDiv15() ) ) {
        return false;
    }
    if ( ext_div16 == null ) {
      if ( row.getExtDiv16() != null )
        return false;
    } else if ( !ext_div16.equals( row.getExtDiv16() ) ) {
        return false;
    }
    if ( ext_div17 == null ) {
      if ( row.getExtDiv17() != null )
        return false;
    } else if ( !ext_div17.equals( row.getExtDiv17() ) ) {
        return false;
    }
    if ( ext_div18 == null ) {
      if ( row.getExtDiv18() != null )
        return false;
    } else if ( !ext_div18.equals( row.getExtDiv18() ) ) {
        return false;
    }
    if ( ext_div19 == null ) {
      if ( row.getExtDiv19() != null )
        return false;
    } else if ( !ext_div19.equals( row.getExtDiv19() ) ) {
        return false;
    }
    if ( ext_div20 == null ) {
      if ( row.getExtDiv20() != null )
        return false;
    } else if ( !ext_div20.equals( row.getExtDiv20() ) ) {
        return false;
    }
    if ( ext_div21 == null ) {
      if ( row.getExtDiv21() != null )
        return false;
    } else if ( !ext_div21.equals( row.getExtDiv21() ) ) {
        return false;
    }
    if ( ext_div22 == null ) {
      if ( row.getExtDiv22() != null )
        return false;
    } else if ( !ext_div22.equals( row.getExtDiv22() ) ) {
        return false;
    }
    if ( ext_div23 == null ) {
      if ( row.getExtDiv23() != null )
        return false;
    } else if ( !ext_div23.equals( row.getExtDiv23() ) ) {
        return false;
    }
    if ( ext_div24 == null ) {
      if ( row.getExtDiv24() != null )
        return false;
    } else if ( !ext_div24.equals( row.getExtDiv24() ) ) {
        return false;
    }
    if ( ext_div25 == null ) {
      if ( row.getExtDiv25() != null )
        return false;
    } else if ( !ext_div25.equals( row.getExtDiv25() ) ) {
        return false;
    }
    if ( ext_div26 == null ) {
      if ( row.getExtDiv26() != null )
        return false;
    } else if ( !ext_div26.equals( row.getExtDiv26() ) ) {
        return false;
    }
    if ( ext_div27 == null ) {
      if ( row.getExtDiv27() != null )
        return false;
    } else if ( !ext_div27.equals( row.getExtDiv27() ) ) {
        return false;
    }
    if ( ext_div28 == null ) {
      if ( row.getExtDiv28() != null )
        return false;
    } else if ( !ext_div28.equals( row.getExtDiv28() ) ) {
        return false;
    }
    if ( ext_div29 == null ) {
      if ( row.getExtDiv29() != null )
        return false;
    } else if ( !ext_div29.equals( row.getExtDiv29() ) ) {
        return false;
    }
    if ( ext_div30 == null ) {
      if ( row.getExtDiv30() != null )
        return false;
    } else if ( !ext_div30.equals( row.getExtDiv30() ) ) {
        return false;
    }
    if ( ext_div31 == null ) {
      if ( row.getExtDiv31() != null )
        return false;
    } else if ( !ext_div31.equals( row.getExtDiv31() ) ) {
        return false;
    }
    if ( ext_div32 == null ) {
      if ( row.getExtDiv32() != null )
        return false;
    } else if ( !ext_div32.equals( row.getExtDiv32() ) ) {
        return false;
    }
    if ( ext_div33 == null ) {
      if ( row.getExtDiv33() != null )
        return false;
    } else if ( !ext_div33.equals( row.getExtDiv33() ) ) {
        return false;
    }
    if ( ext_div34 == null ) {
      if ( row.getExtDiv34() != null )
        return false;
    } else if ( !ext_div34.equals( row.getExtDiv34() ) ) {
        return false;
    }
    if ( ext_div35 == null ) {
      if ( row.getExtDiv35() != null )
        return false;
    } else if ( !ext_div35.equals( row.getExtDiv35() ) ) {
        return false;
    }
    if ( ext_div36 == null ) {
      if ( row.getExtDiv36() != null )
        return false;
    } else if ( !ext_div36.equals( row.getExtDiv36() ) ) {
        return false;
    }
    if ( ext_div37 == null ) {
      if ( row.getExtDiv37() != null )
        return false;
    } else if ( !ext_div37.equals( row.getExtDiv37() ) ) {
        return false;
    }
    if ( ext_div38 == null ) {
      if ( row.getExtDiv38() != null )
        return false;
    } else if ( !ext_div38.equals( row.getExtDiv38() ) ) {
        return false;
    }
    if ( ext_div39 == null ) {
      if ( row.getExtDiv39() != null )
        return false;
    } else if ( !ext_div39.equals( row.getExtDiv39() ) ) {
        return false;
    }
    if ( ext_div40 == null ) {
      if ( row.getExtDiv40() != null )
        return false;
    } else if ( !ext_div40.equals( row.getExtDiv40() ) ) {
        return false;
    }
    if ( ext_code1 == null ) {
      if ( row.getExtCode1() != null )
        return false;
    } else if ( !ext_code1.equals( row.getExtCode1() ) ) {
        return false;
    }
    if ( ext_code2 == null ) {
      if ( row.getExtCode2() != null )
        return false;
    } else if ( !ext_code2.equals( row.getExtCode2() ) ) {
        return false;
    }
    if ( ext_code3 == null ) {
      if ( row.getExtCode3() != null )
        return false;
    } else if ( !ext_code3.equals( row.getExtCode3() ) ) {
        return false;
    }
    if ( ext_code4 == null ) {
      if ( row.getExtCode4() != null )
        return false;
    } else if ( !ext_code4.equals( row.getExtCode4() ) ) {
        return false;
    }
    if ( ext_code5 == null ) {
      if ( row.getExtCode5() != null )
        return false;
    } else if ( !ext_code5.equals( row.getExtCode5() ) ) {
        return false;
    }
    if ( ext_code6 == null ) {
      if ( row.getExtCode6() != null )
        return false;
    } else if ( !ext_code6.equals( row.getExtCode6() ) ) {
        return false;
    }
    if ( ext_code7 == null ) {
      if ( row.getExtCode7() != null )
        return false;
    } else if ( !ext_code7.equals( row.getExtCode7() ) ) {
        return false;
    }
    if ( ext_code8 == null ) {
      if ( row.getExtCode8() != null )
        return false;
    } else if ( !ext_code8.equals( row.getExtCode8() ) ) {
        return false;
    }
    if ( ext_code9 == null ) {
      if ( row.getExtCode9() != null )
        return false;
    } else if ( !ext_code9.equals( row.getExtCode9() ) ) {
        return false;
    }
    if ( ext_code10 == null ) {
      if ( row.getExtCode10() != null )
        return false;
    } else if ( !ext_code10.equals( row.getExtCode10() ) ) {
        return false;
    }
    if ( ext_text1 == null ) {
      if ( row.getExtText1() != null )
        return false;
    } else if ( !ext_text1.equals( row.getExtText1() ) ) {
        return false;
    }
    if ( ext_text2 == null ) {
      if ( row.getExtText2() != null )
        return false;
    } else if ( !ext_text2.equals( row.getExtText2() ) ) {
        return false;
    }
    if ( ext_text3 == null ) {
      if ( row.getExtText3() != null )
        return false;
    } else if ( !ext_text3.equals( row.getExtText3() ) ) {
        return false;
    }
    if ( ext_text4 == null ) {
      if ( row.getExtText4() != null )
        return false;
    } else if ( !ext_text4.equals( row.getExtText4() ) ) {
        return false;
    }
    if ( ext_text5 == null ) {
      if ( row.getExtText5() != null )
        return false;
    } else if ( !ext_text5.equals( row.getExtText5() ) ) {
        return false;
    }
    if ( ext_text6 == null ) {
      if ( row.getExtText6() != null )
        return false;
    } else if ( !ext_text6.equals( row.getExtText6() ) ) {
        return false;
    }
    if ( ext_text7 == null ) {
      if ( row.getExtText7() != null )
        return false;
    } else if ( !ext_text7.equals( row.getExtText7() ) ) {
        return false;
    }
    if ( ext_text8 == null ) {
      if ( row.getExtText8() != null )
        return false;
    } else if ( !ext_text8.equals( row.getExtText8() ) ) {
        return false;
    }
    if ( ext_text9 == null ) {
      if ( row.getExtText9() != null )
        return false;
    } else if ( !ext_text9.equals( row.getExtText9() ) ) {
        return false;
    }
    if ( ext_text10 == null ) {
      if ( row.getExtText10() != null )
        return false;
    } else if ( !ext_text10.equals( row.getExtText10() ) ) {
        return false;
    }
    if ( ext_text11 == null ) {
      if ( row.getExtText11() != null )
        return false;
    } else if ( !ext_text11.equals( row.getExtText11() ) ) {
        return false;
    }
    if ( ext_text12 == null ) {
      if ( row.getExtText12() != null )
        return false;
    } else if ( !ext_text12.equals( row.getExtText12() ) ) {
        return false;
    }
    if ( ext_text13 == null ) {
      if ( row.getExtText13() != null )
        return false;
    } else if ( !ext_text13.equals( row.getExtText13() ) ) {
        return false;
    }
    if ( ext_text14 == null ) {
      if ( row.getExtText14() != null )
        return false;
    } else if ( !ext_text14.equals( row.getExtText14() ) ) {
        return false;
    }
    if ( ext_text15 == null ) {
      if ( row.getExtText15() != null )
        return false;
    } else if ( !ext_text15.equals( row.getExtText15() ) ) {
        return false;
    }
    if ( ext_text16 == null ) {
      if ( row.getExtText16() != null )
        return false;
    } else if ( !ext_text16.equals( row.getExtText16() ) ) {
        return false;
    }
    if ( ext_text17 == null ) {
      if ( row.getExtText17() != null )
        return false;
    } else if ( !ext_text17.equals( row.getExtText17() ) ) {
        return false;
    }
    if ( ext_text18 == null ) {
      if ( row.getExtText18() != null )
        return false;
    } else if ( !ext_text18.equals( row.getExtText18() ) ) {
        return false;
    }
    if ( ext_text19 == null ) {
      if ( row.getExtText19() != null )
        return false;
    } else if ( !ext_text19.equals( row.getExtText19() ) ) {
        return false;
    }
    if ( ext_text20 == null ) {
      if ( row.getExtText20() != null )
        return false;
    } else if ( !ext_text20.equals( row.getExtText20() ) ) {
        return false;
    }
    if ( ext_text21 == null ) {
      if ( row.getExtText21() != null )
        return false;
    } else if ( !ext_text21.equals( row.getExtText21() ) ) {
        return false;
    }
    if ( ext_text22 == null ) {
      if ( row.getExtText22() != null )
        return false;
    } else if ( !ext_text22.equals( row.getExtText22() ) ) {
        return false;
    }
    if ( ext_text23 == null ) {
      if ( row.getExtText23() != null )
        return false;
    } else if ( !ext_text23.equals( row.getExtText23() ) ) {
        return false;
    }
    if ( ext_text24 == null ) {
      if ( row.getExtText24() != null )
        return false;
    } else if ( !ext_text24.equals( row.getExtText24() ) ) {
        return false;
    }
    if ( ext_text25 == null ) {
      if ( row.getExtText25() != null )
        return false;
    } else if ( !ext_text25.equals( row.getExtText25() ) ) {
        return false;
    }
    if ( ext_text26 == null ) {
      if ( row.getExtText26() != null )
        return false;
    } else if ( !ext_text26.equals( row.getExtText26() ) ) {
        return false;
    }
    if ( ext_text27 == null ) {
      if ( row.getExtText27() != null )
        return false;
    } else if ( !ext_text27.equals( row.getExtText27() ) ) {
        return false;
    }
    if ( ext_text28 == null ) {
      if ( row.getExtText28() != null )
        return false;
    } else if ( !ext_text28.equals( row.getExtText28() ) ) {
        return false;
    }
    if ( ext_text29 == null ) {
      if ( row.getExtText29() != null )
        return false;
    } else if ( !ext_text29.equals( row.getExtText29() ) ) {
        return false;
    }
    if ( ext_text30 == null ) {
      if ( row.getExtText30() != null )
        return false;
    } else if ( !ext_text30.equals( row.getExtText30() ) ) {
        return false;
    }
    if ( ext_text31 == null ) {
      if ( row.getExtText31() != null )
        return false;
    } else if ( !ext_text31.equals( row.getExtText31() ) ) {
        return false;
    }
    if ( ext_text32 == null ) {
      if ( row.getExtText32() != null )
        return false;
    } else if ( !ext_text32.equals( row.getExtText32() ) ) {
        return false;
    }
    if ( ext_text33 == null ) {
      if ( row.getExtText33() != null )
        return false;
    } else if ( !ext_text33.equals( row.getExtText33() ) ) {
        return false;
    }
    if ( ext_text34 == null ) {
      if ( row.getExtText34() != null )
        return false;
    } else if ( !ext_text34.equals( row.getExtText34() ) ) {
        return false;
    }
    if ( ext_text35 == null ) {
      if ( row.getExtText35() != null )
        return false;
    } else if ( !ext_text35.equals( row.getExtText35() ) ) {
        return false;
    }
    if ( ext_text36 == null ) {
      if ( row.getExtText36() != null )
        return false;
    } else if ( !ext_text36.equals( row.getExtText36() ) ) {
        return false;
    }
    if ( ext_text37 == null ) {
      if ( row.getExtText37() != null )
        return false;
    } else if ( !ext_text37.equals( row.getExtText37() ) ) {
        return false;
    }
    if ( ext_text38 == null ) {
      if ( row.getExtText38() != null )
        return false;
    } else if ( !ext_text38.equals( row.getExtText38() ) ) {
        return false;
    }
    if ( ext_text39 == null ) {
      if ( row.getExtText39() != null )
        return false;
    } else if ( !ext_text39.equals( row.getExtText39() ) ) {
        return false;
    }
    if ( ext_text40 == null ) {
      if ( row.getExtText40() != null )
        return false;
    } else if ( !ext_text40.equals( row.getExtText40() ) ) {
        return false;
    }
    if ( ext_value1 == null ) {
      if ( !row.getExtValue1IsNull() )
        return false;
    } else if ( row.getExtValue1IsNull() || ( ext_value1.longValue() != row.getExtValue1() ) ) {
        return false;
    }
    if ( ext_value2 == null ) {
      if ( !row.getExtValue2IsNull() )
        return false;
    } else if ( row.getExtValue2IsNull() || ( ext_value2.longValue() != row.getExtValue2() ) ) {
        return false;
    }
    if ( ext_value3 == null ) {
      if ( !row.getExtValue3IsNull() )
        return false;
    } else if ( row.getExtValue3IsNull() || ( ext_value3.longValue() != row.getExtValue3() ) ) {
        return false;
    }
    if ( ext_value4 == null ) {
      if ( !row.getExtValue4IsNull() )
        return false;
    } else if ( row.getExtValue4IsNull() || ( ext_value4.longValue() != row.getExtValue4() ) ) {
        return false;
    }
    if ( ext_value5 == null ) {
      if ( !row.getExtValue5IsNull() )
        return false;
    } else if ( row.getExtValue5IsNull() || ( ext_value5.longValue() != row.getExtValue5() ) ) {
        return false;
    }
    if ( ext_value6 == null ) {
      if ( !row.getExtValue6IsNull() )
        return false;
    } else if ( row.getExtValue6IsNull() || ( ext_value6.longValue() != row.getExtValue6() ) ) {
        return false;
    }
    if ( ext_value7 == null ) {
      if ( !row.getExtValue7IsNull() )
        return false;
    } else if ( row.getExtValue7IsNull() || ( ext_value7.longValue() != row.getExtValue7() ) ) {
        return false;
    }
    if ( ext_value8 == null ) {
      if ( !row.getExtValue8IsNull() )
        return false;
    } else if ( row.getExtValue8IsNull() || ( ext_value8.longValue() != row.getExtValue8() ) ) {
        return false;
    }
    if ( ext_value9 == null ) {
      if ( !row.getExtValue9IsNull() )
        return false;
    } else if ( row.getExtValue9IsNull() || ( ext_value9.longValue() != row.getExtValue9() ) ) {
        return false;
    }
    if ( ext_value10 == null ) {
      if ( !row.getExtValue10IsNull() )
        return false;
    } else if ( row.getExtValue10IsNull() || ( ext_value10.longValue() != row.getExtValue10() ) ) {
        return false;
    }
    if ( ext_value11 == null ) {
      if ( !row.getExtValue11IsNull() )
        return false;
    } else if ( row.getExtValue11IsNull() || ( ext_value11.longValue() != row.getExtValue11() ) ) {
        return false;
    }
    if ( ext_value12 == null ) {
      if ( !row.getExtValue12IsNull() )
        return false;
    } else if ( row.getExtValue12IsNull() || ( ext_value12.longValue() != row.getExtValue12() ) ) {
        return false;
    }
    if ( ext_value13 == null ) {
      if ( !row.getExtValue13IsNull() )
        return false;
    } else if ( row.getExtValue13IsNull() || ( ext_value13.longValue() != row.getExtValue13() ) ) {
        return false;
    }
    if ( ext_value14 == null ) {
      if ( !row.getExtValue14IsNull() )
        return false;
    } else if ( row.getExtValue14IsNull() || ( ext_value14.longValue() != row.getExtValue14() ) ) {
        return false;
    }
    if ( ext_value15 == null ) {
      if ( !row.getExtValue15IsNull() )
        return false;
    } else if ( row.getExtValue15IsNull() || ( ext_value15.longValue() != row.getExtValue15() ) ) {
        return false;
    }
    if ( ext_value16 == null ) {
      if ( !row.getExtValue16IsNull() )
        return false;
    } else if ( row.getExtValue16IsNull() || ( ext_value16.longValue() != row.getExtValue16() ) ) {
        return false;
    }
    if ( ext_value17 == null ) {
      if ( !row.getExtValue17IsNull() )
        return false;
    } else if ( row.getExtValue17IsNull() || ( ext_value17.longValue() != row.getExtValue17() ) ) {
        return false;
    }
    if ( ext_value18 == null ) {
      if ( !row.getExtValue18IsNull() )
        return false;
    } else if ( row.getExtValue18IsNull() || ( ext_value18.longValue() != row.getExtValue18() ) ) {
        return false;
    }
    if ( ext_value19 == null ) {
      if ( !row.getExtValue19IsNull() )
        return false;
    } else if ( row.getExtValue19IsNull() || ( ext_value19.longValue() != row.getExtValue19() ) ) {
        return false;
    }
    if ( ext_value20 == null ) {
      if ( !row.getExtValue20IsNull() )
        return false;
    } else if ( row.getExtValue20IsNull() || ( ext_value20.longValue() != row.getExtValue20() ) ) {
        return false;
    }
    if ( ext_value21 == null ) {
      if ( !row.getExtValue21IsNull() )
        return false;
    } else if ( row.getExtValue21IsNull() || ( ext_value21.longValue() != row.getExtValue21() ) ) {
        return false;
    }
    if ( ext_value22 == null ) {
      if ( !row.getExtValue22IsNull() )
        return false;
    } else if ( row.getExtValue22IsNull() || ( ext_value22.longValue() != row.getExtValue22() ) ) {
        return false;
    }
    if ( ext_value23 == null ) {
      if ( !row.getExtValue23IsNull() )
        return false;
    } else if ( row.getExtValue23IsNull() || ( ext_value23.longValue() != row.getExtValue23() ) ) {
        return false;
    }
    if ( ext_value24 == null ) {
      if ( !row.getExtValue24IsNull() )
        return false;
    } else if ( row.getExtValue24IsNull() || ( ext_value24.longValue() != row.getExtValue24() ) ) {
        return false;
    }
    if ( ext_value25 == null ) {
      if ( !row.getExtValue25IsNull() )
        return false;
    } else if ( row.getExtValue25IsNull() || ( ext_value25.longValue() != row.getExtValue25() ) ) {
        return false;
    }
    if ( ext_value26 == null ) {
      if ( !row.getExtValue26IsNull() )
        return false;
    } else if ( row.getExtValue26IsNull() || ( ext_value26.longValue() != row.getExtValue26() ) ) {
        return false;
    }
    if ( ext_value27 == null ) {
      if ( !row.getExtValue27IsNull() )
        return false;
    } else if ( row.getExtValue27IsNull() || ( ext_value27.longValue() != row.getExtValue27() ) ) {
        return false;
    }
    if ( ext_value28 == null ) {
      if ( !row.getExtValue28IsNull() )
        return false;
    } else if ( row.getExtValue28IsNull() || ( ext_value28.longValue() != row.getExtValue28() ) ) {
        return false;
    }
    if ( ext_value29 == null ) {
      if ( !row.getExtValue29IsNull() )
        return false;
    } else if ( row.getExtValue29IsNull() || ( ext_value29.longValue() != row.getExtValue29() ) ) {
        return false;
    }
    if ( ext_value30 == null ) {
      if ( !row.getExtValue30IsNull() )
        return false;
    } else if ( row.getExtValue30IsNull() || ( ext_value30.longValue() != row.getExtValue30() ) ) {
        return false;
    }
    if ( ext_note1 == null ) {
      if ( row.getExtNote1() != null )
        return false;
    } else if ( !ext_note1.equals( row.getExtNote1() ) ) {
        return false;
    }
    if ( ext_note2 == null ) {
      if ( row.getExtNote2() != null )
        return false;
    } else if ( !ext_note2.equals( row.getExtNote2() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    if ( fund_code == null ) {
      if ( row.getFundCode() != null )
        return false;
    } else if ( !fund_code.equals( row.getFundCode() ) ) {
        return false;
    }
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( error_reason_code == null ) {
      if ( row.getErrorReasonCode() != null )
        return false;
    } else if ( !error_reason_code.equals( row.getErrorReasonCode() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
    if ( send_timestamp == null ) {
      if ( row.getSendTimestamp() != null )
        return false;
    } else if ( !send_timestamp.equals( row.getSendTimestamp() ) ) {
        return false;
    }
    if ( receipt_timestamp == null ) {
      if ( row.getReceiptTimestamp() != null )
        return false;
    } else if ( !receipt_timestamp.equals( row.getReceiptTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (inform_div!=null? inform_div.hashCode(): 0) 
        + (request_number!=null? request_number.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (account_name!=null? account_name.hashCode(): 0) 
        + (email_address!=null? email_address.hashCode(): 0) 
        + (ext_div1!=null? ext_div1.hashCode(): 0) 
        + (ext_div2!=null? ext_div2.hashCode(): 0) 
        + (ext_div3!=null? ext_div3.hashCode(): 0) 
        + (ext_div4!=null? ext_div4.hashCode(): 0) 
        + (ext_div5!=null? ext_div5.hashCode(): 0) 
        + (ext_div6!=null? ext_div6.hashCode(): 0) 
        + (ext_div7!=null? ext_div7.hashCode(): 0) 
        + (ext_div8!=null? ext_div8.hashCode(): 0) 
        + (ext_div9!=null? ext_div9.hashCode(): 0) 
        + (ext_div10!=null? ext_div10.hashCode(): 0) 
        + (ext_div11!=null? ext_div11.hashCode(): 0) 
        + (ext_div12!=null? ext_div12.hashCode(): 0) 
        + (ext_div13!=null? ext_div13.hashCode(): 0) 
        + (ext_div14!=null? ext_div14.hashCode(): 0) 
        + (ext_div15!=null? ext_div15.hashCode(): 0) 
        + (ext_div16!=null? ext_div16.hashCode(): 0) 
        + (ext_div17!=null? ext_div17.hashCode(): 0) 
        + (ext_div18!=null? ext_div18.hashCode(): 0) 
        + (ext_div19!=null? ext_div19.hashCode(): 0) 
        + (ext_div20!=null? ext_div20.hashCode(): 0) 
        + (ext_div21!=null? ext_div21.hashCode(): 0) 
        + (ext_div22!=null? ext_div22.hashCode(): 0) 
        + (ext_div23!=null? ext_div23.hashCode(): 0) 
        + (ext_div24!=null? ext_div24.hashCode(): 0) 
        + (ext_div25!=null? ext_div25.hashCode(): 0) 
        + (ext_div26!=null? ext_div26.hashCode(): 0) 
        + (ext_div27!=null? ext_div27.hashCode(): 0) 
        + (ext_div28!=null? ext_div28.hashCode(): 0) 
        + (ext_div29!=null? ext_div29.hashCode(): 0) 
        + (ext_div30!=null? ext_div30.hashCode(): 0) 
        + (ext_div31!=null? ext_div31.hashCode(): 0) 
        + (ext_div32!=null? ext_div32.hashCode(): 0) 
        + (ext_div33!=null? ext_div33.hashCode(): 0) 
        + (ext_div34!=null? ext_div34.hashCode(): 0) 
        + (ext_div35!=null? ext_div35.hashCode(): 0) 
        + (ext_div36!=null? ext_div36.hashCode(): 0) 
        + (ext_div37!=null? ext_div37.hashCode(): 0) 
        + (ext_div38!=null? ext_div38.hashCode(): 0) 
        + (ext_div39!=null? ext_div39.hashCode(): 0) 
        + (ext_div40!=null? ext_div40.hashCode(): 0) 
        + (ext_code1!=null? ext_code1.hashCode(): 0) 
        + (ext_code2!=null? ext_code2.hashCode(): 0) 
        + (ext_code3!=null? ext_code3.hashCode(): 0) 
        + (ext_code4!=null? ext_code4.hashCode(): 0) 
        + (ext_code5!=null? ext_code5.hashCode(): 0) 
        + (ext_code6!=null? ext_code6.hashCode(): 0) 
        + (ext_code7!=null? ext_code7.hashCode(): 0) 
        + (ext_code8!=null? ext_code8.hashCode(): 0) 
        + (ext_code9!=null? ext_code9.hashCode(): 0) 
        + (ext_code10!=null? ext_code10.hashCode(): 0) 
        + (ext_text1!=null? ext_text1.hashCode(): 0) 
        + (ext_text2!=null? ext_text2.hashCode(): 0) 
        + (ext_text3!=null? ext_text3.hashCode(): 0) 
        + (ext_text4!=null? ext_text4.hashCode(): 0) 
        + (ext_text5!=null? ext_text5.hashCode(): 0) 
        + (ext_text6!=null? ext_text6.hashCode(): 0) 
        + (ext_text7!=null? ext_text7.hashCode(): 0) 
        + (ext_text8!=null? ext_text8.hashCode(): 0) 
        + (ext_text9!=null? ext_text9.hashCode(): 0) 
        + (ext_text10!=null? ext_text10.hashCode(): 0) 
        + (ext_text11!=null? ext_text11.hashCode(): 0) 
        + (ext_text12!=null? ext_text12.hashCode(): 0) 
        + (ext_text13!=null? ext_text13.hashCode(): 0) 
        + (ext_text14!=null? ext_text14.hashCode(): 0) 
        + (ext_text15!=null? ext_text15.hashCode(): 0) 
        + (ext_text16!=null? ext_text16.hashCode(): 0) 
        + (ext_text17!=null? ext_text17.hashCode(): 0) 
        + (ext_text18!=null? ext_text18.hashCode(): 0) 
        + (ext_text19!=null? ext_text19.hashCode(): 0) 
        + (ext_text20!=null? ext_text20.hashCode(): 0) 
        + (ext_text21!=null? ext_text21.hashCode(): 0) 
        + (ext_text22!=null? ext_text22.hashCode(): 0) 
        + (ext_text23!=null? ext_text23.hashCode(): 0) 
        + (ext_text24!=null? ext_text24.hashCode(): 0) 
        + (ext_text25!=null? ext_text25.hashCode(): 0) 
        + (ext_text26!=null? ext_text26.hashCode(): 0) 
        + (ext_text27!=null? ext_text27.hashCode(): 0) 
        + (ext_text28!=null? ext_text28.hashCode(): 0) 
        + (ext_text29!=null? ext_text29.hashCode(): 0) 
        + (ext_text30!=null? ext_text30.hashCode(): 0) 
        + (ext_text31!=null? ext_text31.hashCode(): 0) 
        + (ext_text32!=null? ext_text32.hashCode(): 0) 
        + (ext_text33!=null? ext_text33.hashCode(): 0) 
        + (ext_text34!=null? ext_text34.hashCode(): 0) 
        + (ext_text35!=null? ext_text35.hashCode(): 0) 
        + (ext_text36!=null? ext_text36.hashCode(): 0) 
        + (ext_text37!=null? ext_text37.hashCode(): 0) 
        + (ext_text38!=null? ext_text38.hashCode(): 0) 
        + (ext_text39!=null? ext_text39.hashCode(): 0) 
        + (ext_text40!=null? ext_text40.hashCode(): 0) 
        + (ext_value1!=null? ext_value1.hashCode(): 0) 
        + (ext_value2!=null? ext_value2.hashCode(): 0) 
        + (ext_value3!=null? ext_value3.hashCode(): 0) 
        + (ext_value4!=null? ext_value4.hashCode(): 0) 
        + (ext_value5!=null? ext_value5.hashCode(): 0) 
        + (ext_value6!=null? ext_value6.hashCode(): 0) 
        + (ext_value7!=null? ext_value7.hashCode(): 0) 
        + (ext_value8!=null? ext_value8.hashCode(): 0) 
        + (ext_value9!=null? ext_value9.hashCode(): 0) 
        + (ext_value10!=null? ext_value10.hashCode(): 0) 
        + (ext_value11!=null? ext_value11.hashCode(): 0) 
        + (ext_value12!=null? ext_value12.hashCode(): 0) 
        + (ext_value13!=null? ext_value13.hashCode(): 0) 
        + (ext_value14!=null? ext_value14.hashCode(): 0) 
        + (ext_value15!=null? ext_value15.hashCode(): 0) 
        + (ext_value16!=null? ext_value16.hashCode(): 0) 
        + (ext_value17!=null? ext_value17.hashCode(): 0) 
        + (ext_value18!=null? ext_value18.hashCode(): 0) 
        + (ext_value19!=null? ext_value19.hashCode(): 0) 
        + (ext_value20!=null? ext_value20.hashCode(): 0) 
        + (ext_value21!=null? ext_value21.hashCode(): 0) 
        + (ext_value22!=null? ext_value22.hashCode(): 0) 
        + (ext_value23!=null? ext_value23.hashCode(): 0) 
        + (ext_value24!=null? ext_value24.hashCode(): 0) 
        + (ext_value25!=null? ext_value25.hashCode(): 0) 
        + (ext_value26!=null? ext_value26.hashCode(): 0) 
        + (ext_value27!=null? ext_value27.hashCode(): 0) 
        + (ext_value28!=null? ext_value28.hashCode(): 0) 
        + (ext_value29!=null? ext_value29.hashCode(): 0) 
        + (ext_value30!=null? ext_value30.hashCode(): 0) 
        + (ext_note1!=null? ext_note1.hashCode(): 0) 
        + (ext_note2!=null? ext_note2.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (fund_code!=null? fund_code.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (error_reason_code!=null? error_reason_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (request_code!=null? request_code.hashCode(): 0) 
        + (send_timestamp!=null? send_timestamp.hashCode(): 0) 
        + (receipt_timestamp!=null? receipt_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("inform_div",inform_div);
		map.put("request_number",request_number);
		map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		if ( account_name != null )
			map.put("account_name",account_name);
		if ( email_address != null )
			map.put("email_address",email_address);
		if ( ext_div1 != null )
			map.put("ext_div1",ext_div1);
		if ( ext_div2 != null )
			map.put("ext_div2",ext_div2);
		if ( ext_div3 != null )
			map.put("ext_div3",ext_div3);
		if ( ext_div4 != null )
			map.put("ext_div4",ext_div4);
		if ( ext_div5 != null )
			map.put("ext_div5",ext_div5);
		if ( ext_div6 != null )
			map.put("ext_div6",ext_div6);
		if ( ext_div7 != null )
			map.put("ext_div7",ext_div7);
		if ( ext_div8 != null )
			map.put("ext_div8",ext_div8);
		if ( ext_div9 != null )
			map.put("ext_div9",ext_div9);
		if ( ext_div10 != null )
			map.put("ext_div10",ext_div10);
		if ( ext_div11 != null )
			map.put("ext_div11",ext_div11);
		if ( ext_div12 != null )
			map.put("ext_div12",ext_div12);
		if ( ext_div13 != null )
			map.put("ext_div13",ext_div13);
		if ( ext_div14 != null )
			map.put("ext_div14",ext_div14);
		if ( ext_div15 != null )
			map.put("ext_div15",ext_div15);
		if ( ext_div16 != null )
			map.put("ext_div16",ext_div16);
		if ( ext_div17 != null )
			map.put("ext_div17",ext_div17);
		if ( ext_div18 != null )
			map.put("ext_div18",ext_div18);
		if ( ext_div19 != null )
			map.put("ext_div19",ext_div19);
		if ( ext_div20 != null )
			map.put("ext_div20",ext_div20);
		if ( ext_div21 != null )
			map.put("ext_div21",ext_div21);
		if ( ext_div22 != null )
			map.put("ext_div22",ext_div22);
		if ( ext_div23 != null )
			map.put("ext_div23",ext_div23);
		if ( ext_div24 != null )
			map.put("ext_div24",ext_div24);
		if ( ext_div25 != null )
			map.put("ext_div25",ext_div25);
		if ( ext_div26 != null )
			map.put("ext_div26",ext_div26);
		if ( ext_div27 != null )
			map.put("ext_div27",ext_div27);
		if ( ext_div28 != null )
			map.put("ext_div28",ext_div28);
		if ( ext_div29 != null )
			map.put("ext_div29",ext_div29);
		if ( ext_div30 != null )
			map.put("ext_div30",ext_div30);
		if ( ext_div31 != null )
			map.put("ext_div31",ext_div31);
		if ( ext_div32 != null )
			map.put("ext_div32",ext_div32);
		if ( ext_div33 != null )
			map.put("ext_div33",ext_div33);
		if ( ext_div34 != null )
			map.put("ext_div34",ext_div34);
		if ( ext_div35 != null )
			map.put("ext_div35",ext_div35);
		if ( ext_div36 != null )
			map.put("ext_div36",ext_div36);
		if ( ext_div37 != null )
			map.put("ext_div37",ext_div37);
		if ( ext_div38 != null )
			map.put("ext_div38",ext_div38);
		if ( ext_div39 != null )
			map.put("ext_div39",ext_div39);
		if ( ext_div40 != null )
			map.put("ext_div40",ext_div40);
		if ( ext_code1 != null )
			map.put("ext_code1",ext_code1);
		if ( ext_code2 != null )
			map.put("ext_code2",ext_code2);
		if ( ext_code3 != null )
			map.put("ext_code3",ext_code3);
		if ( ext_code4 != null )
			map.put("ext_code4",ext_code4);
		if ( ext_code5 != null )
			map.put("ext_code5",ext_code5);
		if ( ext_code6 != null )
			map.put("ext_code6",ext_code6);
		if ( ext_code7 != null )
			map.put("ext_code7",ext_code7);
		if ( ext_code8 != null )
			map.put("ext_code8",ext_code8);
		if ( ext_code9 != null )
			map.put("ext_code9",ext_code9);
		if ( ext_code10 != null )
			map.put("ext_code10",ext_code10);
		if ( ext_text1 != null )
			map.put("ext_text1",ext_text1);
		if ( ext_text2 != null )
			map.put("ext_text2",ext_text2);
		if ( ext_text3 != null )
			map.put("ext_text3",ext_text3);
		if ( ext_text4 != null )
			map.put("ext_text4",ext_text4);
		if ( ext_text5 != null )
			map.put("ext_text5",ext_text5);
		if ( ext_text6 != null )
			map.put("ext_text6",ext_text6);
		if ( ext_text7 != null )
			map.put("ext_text7",ext_text7);
		if ( ext_text8 != null )
			map.put("ext_text8",ext_text8);
		if ( ext_text9 != null )
			map.put("ext_text9",ext_text9);
		if ( ext_text10 != null )
			map.put("ext_text10",ext_text10);
		if ( ext_text11 != null )
			map.put("ext_text11",ext_text11);
		if ( ext_text12 != null )
			map.put("ext_text12",ext_text12);
		if ( ext_text13 != null )
			map.put("ext_text13",ext_text13);
		if ( ext_text14 != null )
			map.put("ext_text14",ext_text14);
		if ( ext_text15 != null )
			map.put("ext_text15",ext_text15);
		if ( ext_text16 != null )
			map.put("ext_text16",ext_text16);
		if ( ext_text17 != null )
			map.put("ext_text17",ext_text17);
		if ( ext_text18 != null )
			map.put("ext_text18",ext_text18);
		if ( ext_text19 != null )
			map.put("ext_text19",ext_text19);
		if ( ext_text20 != null )
			map.put("ext_text20",ext_text20);
		if ( ext_text21 != null )
			map.put("ext_text21",ext_text21);
		if ( ext_text22 != null )
			map.put("ext_text22",ext_text22);
		if ( ext_text23 != null )
			map.put("ext_text23",ext_text23);
		if ( ext_text24 != null )
			map.put("ext_text24",ext_text24);
		if ( ext_text25 != null )
			map.put("ext_text25",ext_text25);
		if ( ext_text26 != null )
			map.put("ext_text26",ext_text26);
		if ( ext_text27 != null )
			map.put("ext_text27",ext_text27);
		if ( ext_text28 != null )
			map.put("ext_text28",ext_text28);
		if ( ext_text29 != null )
			map.put("ext_text29",ext_text29);
		if ( ext_text30 != null )
			map.put("ext_text30",ext_text30);
		if ( ext_text31 != null )
			map.put("ext_text31",ext_text31);
		if ( ext_text32 != null )
			map.put("ext_text32",ext_text32);
		if ( ext_text33 != null )
			map.put("ext_text33",ext_text33);
		if ( ext_text34 != null )
			map.put("ext_text34",ext_text34);
		if ( ext_text35 != null )
			map.put("ext_text35",ext_text35);
		if ( ext_text36 != null )
			map.put("ext_text36",ext_text36);
		if ( ext_text37 != null )
			map.put("ext_text37",ext_text37);
		if ( ext_text38 != null )
			map.put("ext_text38",ext_text38);
		if ( ext_text39 != null )
			map.put("ext_text39",ext_text39);
		if ( ext_text40 != null )
			map.put("ext_text40",ext_text40);
		if ( ext_value1 != null )
			map.put("ext_value1",ext_value1);
		if ( ext_value2 != null )
			map.put("ext_value2",ext_value2);
		if ( ext_value3 != null )
			map.put("ext_value3",ext_value3);
		if ( ext_value4 != null )
			map.put("ext_value4",ext_value4);
		if ( ext_value5 != null )
			map.put("ext_value5",ext_value5);
		if ( ext_value6 != null )
			map.put("ext_value6",ext_value6);
		if ( ext_value7 != null )
			map.put("ext_value7",ext_value7);
		if ( ext_value8 != null )
			map.put("ext_value8",ext_value8);
		if ( ext_value9 != null )
			map.put("ext_value9",ext_value9);
		if ( ext_value10 != null )
			map.put("ext_value10",ext_value10);
		if ( ext_value11 != null )
			map.put("ext_value11",ext_value11);
		if ( ext_value12 != null )
			map.put("ext_value12",ext_value12);
		if ( ext_value13 != null )
			map.put("ext_value13",ext_value13);
		if ( ext_value14 != null )
			map.put("ext_value14",ext_value14);
		if ( ext_value15 != null )
			map.put("ext_value15",ext_value15);
		if ( ext_value16 != null )
			map.put("ext_value16",ext_value16);
		if ( ext_value17 != null )
			map.put("ext_value17",ext_value17);
		if ( ext_value18 != null )
			map.put("ext_value18",ext_value18);
		if ( ext_value19 != null )
			map.put("ext_value19",ext_value19);
		if ( ext_value20 != null )
			map.put("ext_value20",ext_value20);
		if ( ext_value21 != null )
			map.put("ext_value21",ext_value21);
		if ( ext_value22 != null )
			map.put("ext_value22",ext_value22);
		if ( ext_value23 != null )
			map.put("ext_value23",ext_value23);
		if ( ext_value24 != null )
			map.put("ext_value24",ext_value24);
		if ( ext_value25 != null )
			map.put("ext_value25",ext_value25);
		if ( ext_value26 != null )
			map.put("ext_value26",ext_value26);
		if ( ext_value27 != null )
			map.put("ext_value27",ext_value27);
		if ( ext_value28 != null )
			map.put("ext_value28",ext_value28);
		if ( ext_value29 != null )
			map.put("ext_value29",ext_value29);
		if ( ext_value30 != null )
			map.put("ext_value30",ext_value30);
		if ( ext_note1 != null )
			map.put("ext_note1",ext_note1);
		if ( ext_note2 != null )
			map.put("ext_note2",ext_note2);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( fund_code != null )
			map.put("fund_code",fund_code);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( status != null )
			map.put("status",status);
		if ( error_reason_code != null )
			map.put("error_reason_code",error_reason_code);
		if ( order_request_number != null )
			map.put("order_request_number",order_request_number);
		if ( request_code != null )
			map.put("request_code",request_code);
		if ( send_timestamp != null )
			map.put("send_timestamp",send_timestamp);
		if ( receipt_timestamp != null )
			map.put("receipt_timestamp",receipt_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( account_name_is_modified )
			map.put("account_name",account_name);
		if ( email_address_is_modified )
			map.put("email_address",email_address);
		if ( ext_div1_is_modified )
			map.put("ext_div1",ext_div1);
		if ( ext_div2_is_modified )
			map.put("ext_div2",ext_div2);
		if ( ext_div3_is_modified )
			map.put("ext_div3",ext_div3);
		if ( ext_div4_is_modified )
			map.put("ext_div4",ext_div4);
		if ( ext_div5_is_modified )
			map.put("ext_div5",ext_div5);
		if ( ext_div6_is_modified )
			map.put("ext_div6",ext_div6);
		if ( ext_div7_is_modified )
			map.put("ext_div7",ext_div7);
		if ( ext_div8_is_modified )
			map.put("ext_div8",ext_div8);
		if ( ext_div9_is_modified )
			map.put("ext_div9",ext_div9);
		if ( ext_div10_is_modified )
			map.put("ext_div10",ext_div10);
		if ( ext_div11_is_modified )
			map.put("ext_div11",ext_div11);
		if ( ext_div12_is_modified )
			map.put("ext_div12",ext_div12);
		if ( ext_div13_is_modified )
			map.put("ext_div13",ext_div13);
		if ( ext_div14_is_modified )
			map.put("ext_div14",ext_div14);
		if ( ext_div15_is_modified )
			map.put("ext_div15",ext_div15);
		if ( ext_div16_is_modified )
			map.put("ext_div16",ext_div16);
		if ( ext_div17_is_modified )
			map.put("ext_div17",ext_div17);
		if ( ext_div18_is_modified )
			map.put("ext_div18",ext_div18);
		if ( ext_div19_is_modified )
			map.put("ext_div19",ext_div19);
		if ( ext_div20_is_modified )
			map.put("ext_div20",ext_div20);
		if ( ext_div21_is_modified )
			map.put("ext_div21",ext_div21);
		if ( ext_div22_is_modified )
			map.put("ext_div22",ext_div22);
		if ( ext_div23_is_modified )
			map.put("ext_div23",ext_div23);
		if ( ext_div24_is_modified )
			map.put("ext_div24",ext_div24);
		if ( ext_div25_is_modified )
			map.put("ext_div25",ext_div25);
		if ( ext_div26_is_modified )
			map.put("ext_div26",ext_div26);
		if ( ext_div27_is_modified )
			map.put("ext_div27",ext_div27);
		if ( ext_div28_is_modified )
			map.put("ext_div28",ext_div28);
		if ( ext_div29_is_modified )
			map.put("ext_div29",ext_div29);
		if ( ext_div30_is_modified )
			map.put("ext_div30",ext_div30);
		if ( ext_div31_is_modified )
			map.put("ext_div31",ext_div31);
		if ( ext_div32_is_modified )
			map.put("ext_div32",ext_div32);
		if ( ext_div33_is_modified )
			map.put("ext_div33",ext_div33);
		if ( ext_div34_is_modified )
			map.put("ext_div34",ext_div34);
		if ( ext_div35_is_modified )
			map.put("ext_div35",ext_div35);
		if ( ext_div36_is_modified )
			map.put("ext_div36",ext_div36);
		if ( ext_div37_is_modified )
			map.put("ext_div37",ext_div37);
		if ( ext_div38_is_modified )
			map.put("ext_div38",ext_div38);
		if ( ext_div39_is_modified )
			map.put("ext_div39",ext_div39);
		if ( ext_div40_is_modified )
			map.put("ext_div40",ext_div40);
		if ( ext_code1_is_modified )
			map.put("ext_code1",ext_code1);
		if ( ext_code2_is_modified )
			map.put("ext_code2",ext_code2);
		if ( ext_code3_is_modified )
			map.put("ext_code3",ext_code3);
		if ( ext_code4_is_modified )
			map.put("ext_code4",ext_code4);
		if ( ext_code5_is_modified )
			map.put("ext_code5",ext_code5);
		if ( ext_code6_is_modified )
			map.put("ext_code6",ext_code6);
		if ( ext_code7_is_modified )
			map.put("ext_code7",ext_code7);
		if ( ext_code8_is_modified )
			map.put("ext_code8",ext_code8);
		if ( ext_code9_is_modified )
			map.put("ext_code9",ext_code9);
		if ( ext_code10_is_modified )
			map.put("ext_code10",ext_code10);
		if ( ext_text1_is_modified )
			map.put("ext_text1",ext_text1);
		if ( ext_text2_is_modified )
			map.put("ext_text2",ext_text2);
		if ( ext_text3_is_modified )
			map.put("ext_text3",ext_text3);
		if ( ext_text4_is_modified )
			map.put("ext_text4",ext_text4);
		if ( ext_text5_is_modified )
			map.put("ext_text5",ext_text5);
		if ( ext_text6_is_modified )
			map.put("ext_text6",ext_text6);
		if ( ext_text7_is_modified )
			map.put("ext_text7",ext_text7);
		if ( ext_text8_is_modified )
			map.put("ext_text8",ext_text8);
		if ( ext_text9_is_modified )
			map.put("ext_text9",ext_text9);
		if ( ext_text10_is_modified )
			map.put("ext_text10",ext_text10);
		if ( ext_text11_is_modified )
			map.put("ext_text11",ext_text11);
		if ( ext_text12_is_modified )
			map.put("ext_text12",ext_text12);
		if ( ext_text13_is_modified )
			map.put("ext_text13",ext_text13);
		if ( ext_text14_is_modified )
			map.put("ext_text14",ext_text14);
		if ( ext_text15_is_modified )
			map.put("ext_text15",ext_text15);
		if ( ext_text16_is_modified )
			map.put("ext_text16",ext_text16);
		if ( ext_text17_is_modified )
			map.put("ext_text17",ext_text17);
		if ( ext_text18_is_modified )
			map.put("ext_text18",ext_text18);
		if ( ext_text19_is_modified )
			map.put("ext_text19",ext_text19);
		if ( ext_text20_is_modified )
			map.put("ext_text20",ext_text20);
		if ( ext_text21_is_modified )
			map.put("ext_text21",ext_text21);
		if ( ext_text22_is_modified )
			map.put("ext_text22",ext_text22);
		if ( ext_text23_is_modified )
			map.put("ext_text23",ext_text23);
		if ( ext_text24_is_modified )
			map.put("ext_text24",ext_text24);
		if ( ext_text25_is_modified )
			map.put("ext_text25",ext_text25);
		if ( ext_text26_is_modified )
			map.put("ext_text26",ext_text26);
		if ( ext_text27_is_modified )
			map.put("ext_text27",ext_text27);
		if ( ext_text28_is_modified )
			map.put("ext_text28",ext_text28);
		if ( ext_text29_is_modified )
			map.put("ext_text29",ext_text29);
		if ( ext_text30_is_modified )
			map.put("ext_text30",ext_text30);
		if ( ext_text31_is_modified )
			map.put("ext_text31",ext_text31);
		if ( ext_text32_is_modified )
			map.put("ext_text32",ext_text32);
		if ( ext_text33_is_modified )
			map.put("ext_text33",ext_text33);
		if ( ext_text34_is_modified )
			map.put("ext_text34",ext_text34);
		if ( ext_text35_is_modified )
			map.put("ext_text35",ext_text35);
		if ( ext_text36_is_modified )
			map.put("ext_text36",ext_text36);
		if ( ext_text37_is_modified )
			map.put("ext_text37",ext_text37);
		if ( ext_text38_is_modified )
			map.put("ext_text38",ext_text38);
		if ( ext_text39_is_modified )
			map.put("ext_text39",ext_text39);
		if ( ext_text40_is_modified )
			map.put("ext_text40",ext_text40);
		if ( ext_value1_is_modified )
			map.put("ext_value1",ext_value1);
		if ( ext_value2_is_modified )
			map.put("ext_value2",ext_value2);
		if ( ext_value3_is_modified )
			map.put("ext_value3",ext_value3);
		if ( ext_value4_is_modified )
			map.put("ext_value4",ext_value4);
		if ( ext_value5_is_modified )
			map.put("ext_value5",ext_value5);
		if ( ext_value6_is_modified )
			map.put("ext_value6",ext_value6);
		if ( ext_value7_is_modified )
			map.put("ext_value7",ext_value7);
		if ( ext_value8_is_modified )
			map.put("ext_value8",ext_value8);
		if ( ext_value9_is_modified )
			map.put("ext_value9",ext_value9);
		if ( ext_value10_is_modified )
			map.put("ext_value10",ext_value10);
		if ( ext_value11_is_modified )
			map.put("ext_value11",ext_value11);
		if ( ext_value12_is_modified )
			map.put("ext_value12",ext_value12);
		if ( ext_value13_is_modified )
			map.put("ext_value13",ext_value13);
		if ( ext_value14_is_modified )
			map.put("ext_value14",ext_value14);
		if ( ext_value15_is_modified )
			map.put("ext_value15",ext_value15);
		if ( ext_value16_is_modified )
			map.put("ext_value16",ext_value16);
		if ( ext_value17_is_modified )
			map.put("ext_value17",ext_value17);
		if ( ext_value18_is_modified )
			map.put("ext_value18",ext_value18);
		if ( ext_value19_is_modified )
			map.put("ext_value19",ext_value19);
		if ( ext_value20_is_modified )
			map.put("ext_value20",ext_value20);
		if ( ext_value21_is_modified )
			map.put("ext_value21",ext_value21);
		if ( ext_value22_is_modified )
			map.put("ext_value22",ext_value22);
		if ( ext_value23_is_modified )
			map.put("ext_value23",ext_value23);
		if ( ext_value24_is_modified )
			map.put("ext_value24",ext_value24);
		if ( ext_value25_is_modified )
			map.put("ext_value25",ext_value25);
		if ( ext_value26_is_modified )
			map.put("ext_value26",ext_value26);
		if ( ext_value27_is_modified )
			map.put("ext_value27",ext_value27);
		if ( ext_value28_is_modified )
			map.put("ext_value28",ext_value28);
		if ( ext_value29_is_modified )
			map.put("ext_value29",ext_value29);
		if ( ext_value30_is_modified )
			map.put("ext_value30",ext_value30);
		if ( ext_note1_is_modified )
			map.put("ext_note1",ext_note1);
		if ( ext_note2_is_modified )
			map.put("ext_note2",ext_note2);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( fund_code_is_modified )
			map.put("fund_code",fund_code);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( status_is_modified )
			map.put("status",status);
		if ( error_reason_code_is_modified )
			map.put("error_reason_code",error_reason_code);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( request_code_is_modified )
			map.put("request_code",request_code);
		if ( send_timestamp_is_modified )
			map.put("send_timestamp",send_timestamp);
		if ( receipt_timestamp_is_modified )
			map.put("receipt_timestamp",receipt_timestamp);
		if (map.size() == 0) {
			map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			map.put("account_name",account_name);
			map.put("email_address",email_address);
			map.put("ext_div1",ext_div1);
			map.put("ext_div2",ext_div2);
			map.put("ext_div3",ext_div3);
			map.put("ext_div4",ext_div4);
			map.put("ext_div5",ext_div5);
			map.put("ext_div6",ext_div6);
			map.put("ext_div7",ext_div7);
			map.put("ext_div8",ext_div8);
			map.put("ext_div9",ext_div9);
			map.put("ext_div10",ext_div10);
			map.put("ext_div11",ext_div11);
			map.put("ext_div12",ext_div12);
			map.put("ext_div13",ext_div13);
			map.put("ext_div14",ext_div14);
			map.put("ext_div15",ext_div15);
			map.put("ext_div16",ext_div16);
			map.put("ext_div17",ext_div17);
			map.put("ext_div18",ext_div18);
			map.put("ext_div19",ext_div19);
			map.put("ext_div20",ext_div20);
			map.put("ext_div21",ext_div21);
			map.put("ext_div22",ext_div22);
			map.put("ext_div23",ext_div23);
			map.put("ext_div24",ext_div24);
			map.put("ext_div25",ext_div25);
			map.put("ext_div26",ext_div26);
			map.put("ext_div27",ext_div27);
			map.put("ext_div28",ext_div28);
			map.put("ext_div29",ext_div29);
			map.put("ext_div30",ext_div30);
			map.put("ext_div31",ext_div31);
			map.put("ext_div32",ext_div32);
			map.put("ext_div33",ext_div33);
			map.put("ext_div34",ext_div34);
			map.put("ext_div35",ext_div35);
			map.put("ext_div36",ext_div36);
			map.put("ext_div37",ext_div37);
			map.put("ext_div38",ext_div38);
			map.put("ext_div39",ext_div39);
			map.put("ext_div40",ext_div40);
			map.put("ext_code1",ext_code1);
			map.put("ext_code2",ext_code2);
			map.put("ext_code3",ext_code3);
			map.put("ext_code4",ext_code4);
			map.put("ext_code5",ext_code5);
			map.put("ext_code6",ext_code6);
			map.put("ext_code7",ext_code7);
			map.put("ext_code8",ext_code8);
			map.put("ext_code9",ext_code9);
			map.put("ext_code10",ext_code10);
			map.put("ext_text1",ext_text1);
			map.put("ext_text2",ext_text2);
			map.put("ext_text3",ext_text3);
			map.put("ext_text4",ext_text4);
			map.put("ext_text5",ext_text5);
			map.put("ext_text6",ext_text6);
			map.put("ext_text7",ext_text7);
			map.put("ext_text8",ext_text8);
			map.put("ext_text9",ext_text9);
			map.put("ext_text10",ext_text10);
			map.put("ext_text11",ext_text11);
			map.put("ext_text12",ext_text12);
			map.put("ext_text13",ext_text13);
			map.put("ext_text14",ext_text14);
			map.put("ext_text15",ext_text15);
			map.put("ext_text16",ext_text16);
			map.put("ext_text17",ext_text17);
			map.put("ext_text18",ext_text18);
			map.put("ext_text19",ext_text19);
			map.put("ext_text20",ext_text20);
			map.put("ext_text21",ext_text21);
			map.put("ext_text22",ext_text22);
			map.put("ext_text23",ext_text23);
			map.put("ext_text24",ext_text24);
			map.put("ext_text25",ext_text25);
			map.put("ext_text26",ext_text26);
			map.put("ext_text27",ext_text27);
			map.put("ext_text28",ext_text28);
			map.put("ext_text29",ext_text29);
			map.put("ext_text30",ext_text30);
			map.put("ext_text31",ext_text31);
			map.put("ext_text32",ext_text32);
			map.put("ext_text33",ext_text33);
			map.put("ext_text34",ext_text34);
			map.put("ext_text35",ext_text35);
			map.put("ext_text36",ext_text36);
			map.put("ext_text37",ext_text37);
			map.put("ext_text38",ext_text38);
			map.put("ext_text39",ext_text39);
			map.put("ext_text40",ext_text40);
			map.put("ext_value1",ext_value1);
			map.put("ext_value2",ext_value2);
			map.put("ext_value3",ext_value3);
			map.put("ext_value4",ext_value4);
			map.put("ext_value5",ext_value5);
			map.put("ext_value6",ext_value6);
			map.put("ext_value7",ext_value7);
			map.put("ext_value8",ext_value8);
			map.put("ext_value9",ext_value9);
			map.put("ext_value10",ext_value10);
			map.put("ext_value11",ext_value11);
			map.put("ext_value12",ext_value12);
			map.put("ext_value13",ext_value13);
			map.put("ext_value14",ext_value14);
			map.put("ext_value15",ext_value15);
			map.put("ext_value16",ext_value16);
			map.put("ext_value17",ext_value17);
			map.put("ext_value18",ext_value18);
			map.put("ext_value19",ext_value19);
			map.put("ext_value20",ext_value20);
			map.put("ext_value21",ext_value21);
			map.put("ext_value22",ext_value22);
			map.put("ext_value23",ext_value23);
			map.put("ext_value24",ext_value24);
			map.put("ext_value25",ext_value25);
			map.put("ext_value26",ext_value26);
			map.put("ext_value27",ext_value27);
			map.put("ext_value28",ext_value28);
			map.put("ext_value29",ext_value29);
			map.put("ext_value30",ext_value30);
			map.put("ext_note1",ext_note1);
			map.put("ext_note2",ext_note2);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("fund_code",fund_code);
			map.put("sonar_trader_code",sonar_trader_code);
			map.put("status",status);
			map.put("error_reason_code",error_reason_code);
			map.put("order_request_number",order_request_number);
			map.put("request_code",request_code);
			map.put("send_timestamp",send_timestamp);
			map.put("receipt_timestamp",receipt_timestamp);
		}
		return map;
	}


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>inform_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInformDiv()
  {
    return inform_div;
  }


  /** 
   * <em>inform_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformDivIsSet() {
    return inform_div_is_set;
  }


  /** 
   * <em>inform_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformDivIsModified() {
    return inform_div_is_modified;
  }


  /** 
   * <em>request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRequestNumber()
  {
    return request_number;
  }


  /** 
   * <em>request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestNumberIsSet() {
    return request_number_is_set;
  }


  /** 
   * <em>request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestNumberIsModified() {
    return request_number_is_modified;
  }


  /** 
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTraderCode()
  {
    return trader_code;
  }


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsSet() {
    return trader_code_is_set;
  }


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsModified() {
    return trader_code_is_modified;
  }


  /** 
   * <em>account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountName()
  {
    return account_name;
  }


  /** 
   * <em>account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameIsSet() {
    return account_name_is_set;
  }


  /** 
   * <em>account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameIsModified() {
    return account_name_is_modified;
  }


  /** 
   * <em>email_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEmailAddress()
  {
    return email_address;
  }


  /** 
   * <em>email_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressIsSet() {
    return email_address_is_set;
  }


  /** 
   * <em>email_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressIsModified() {
    return email_address_is_modified;
  }


  /** 
   * <em>ext_div1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv1()
  {
    return ext_div1;
  }


  /** 
   * <em>ext_div1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv1IsSet() {
    return ext_div1_is_set;
  }


  /** 
   * <em>ext_div1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv1IsModified() {
    return ext_div1_is_modified;
  }


  /** 
   * <em>ext_div2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv2()
  {
    return ext_div2;
  }


  /** 
   * <em>ext_div2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv2IsSet() {
    return ext_div2_is_set;
  }


  /** 
   * <em>ext_div2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv2IsModified() {
    return ext_div2_is_modified;
  }


  /** 
   * <em>ext_div3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv3()
  {
    return ext_div3;
  }


  /** 
   * <em>ext_div3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv3IsSet() {
    return ext_div3_is_set;
  }


  /** 
   * <em>ext_div3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv3IsModified() {
    return ext_div3_is_modified;
  }


  /** 
   * <em>ext_div4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv4()
  {
    return ext_div4;
  }


  /** 
   * <em>ext_div4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv4IsSet() {
    return ext_div4_is_set;
  }


  /** 
   * <em>ext_div4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv4IsModified() {
    return ext_div4_is_modified;
  }


  /** 
   * <em>ext_div5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv5()
  {
    return ext_div5;
  }


  /** 
   * <em>ext_div5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv5IsSet() {
    return ext_div5_is_set;
  }


  /** 
   * <em>ext_div5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv5IsModified() {
    return ext_div5_is_modified;
  }


  /** 
   * <em>ext_div6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv6()
  {
    return ext_div6;
  }


  /** 
   * <em>ext_div6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv6IsSet() {
    return ext_div6_is_set;
  }


  /** 
   * <em>ext_div6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv6IsModified() {
    return ext_div6_is_modified;
  }


  /** 
   * <em>ext_div7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv7()
  {
    return ext_div7;
  }


  /** 
   * <em>ext_div7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv7IsSet() {
    return ext_div7_is_set;
  }


  /** 
   * <em>ext_div7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv7IsModified() {
    return ext_div7_is_modified;
  }


  /** 
   * <em>ext_div8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv8()
  {
    return ext_div8;
  }


  /** 
   * <em>ext_div8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv8IsSet() {
    return ext_div8_is_set;
  }


  /** 
   * <em>ext_div8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv8IsModified() {
    return ext_div8_is_modified;
  }


  /** 
   * <em>ext_div9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv9()
  {
    return ext_div9;
  }


  /** 
   * <em>ext_div9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv9IsSet() {
    return ext_div9_is_set;
  }


  /** 
   * <em>ext_div9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv9IsModified() {
    return ext_div9_is_modified;
  }


  /** 
   * <em>ext_div10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv10()
  {
    return ext_div10;
  }


  /** 
   * <em>ext_div10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv10IsSet() {
    return ext_div10_is_set;
  }


  /** 
   * <em>ext_div10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv10IsModified() {
    return ext_div10_is_modified;
  }


  /** 
   * <em>ext_div11</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv11()
  {
    return ext_div11;
  }


  /** 
   * <em>ext_div11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv11IsSet() {
    return ext_div11_is_set;
  }


  /** 
   * <em>ext_div11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv11IsModified() {
    return ext_div11_is_modified;
  }


  /** 
   * <em>ext_div12</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv12()
  {
    return ext_div12;
  }


  /** 
   * <em>ext_div12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv12IsSet() {
    return ext_div12_is_set;
  }


  /** 
   * <em>ext_div12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv12IsModified() {
    return ext_div12_is_modified;
  }


  /** 
   * <em>ext_div13</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv13()
  {
    return ext_div13;
  }


  /** 
   * <em>ext_div13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv13IsSet() {
    return ext_div13_is_set;
  }


  /** 
   * <em>ext_div13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv13IsModified() {
    return ext_div13_is_modified;
  }


  /** 
   * <em>ext_div14</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv14()
  {
    return ext_div14;
  }


  /** 
   * <em>ext_div14</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv14IsSet() {
    return ext_div14_is_set;
  }


  /** 
   * <em>ext_div14</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv14IsModified() {
    return ext_div14_is_modified;
  }


  /** 
   * <em>ext_div15</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv15()
  {
    return ext_div15;
  }


  /** 
   * <em>ext_div15</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv15IsSet() {
    return ext_div15_is_set;
  }


  /** 
   * <em>ext_div15</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv15IsModified() {
    return ext_div15_is_modified;
  }


  /** 
   * <em>ext_div16</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv16()
  {
    return ext_div16;
  }


  /** 
   * <em>ext_div16</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv16IsSet() {
    return ext_div16_is_set;
  }


  /** 
   * <em>ext_div16</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv16IsModified() {
    return ext_div16_is_modified;
  }


  /** 
   * <em>ext_div17</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv17()
  {
    return ext_div17;
  }


  /** 
   * <em>ext_div17</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv17IsSet() {
    return ext_div17_is_set;
  }


  /** 
   * <em>ext_div17</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv17IsModified() {
    return ext_div17_is_modified;
  }


  /** 
   * <em>ext_div18</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv18()
  {
    return ext_div18;
  }


  /** 
   * <em>ext_div18</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv18IsSet() {
    return ext_div18_is_set;
  }


  /** 
   * <em>ext_div18</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv18IsModified() {
    return ext_div18_is_modified;
  }


  /** 
   * <em>ext_div19</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv19()
  {
    return ext_div19;
  }


  /** 
   * <em>ext_div19</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv19IsSet() {
    return ext_div19_is_set;
  }


  /** 
   * <em>ext_div19</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv19IsModified() {
    return ext_div19_is_modified;
  }


  /** 
   * <em>ext_div20</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv20()
  {
    return ext_div20;
  }


  /** 
   * <em>ext_div20</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv20IsSet() {
    return ext_div20_is_set;
  }


  /** 
   * <em>ext_div20</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv20IsModified() {
    return ext_div20_is_modified;
  }


  /** 
   * <em>ext_div21</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv21()
  {
    return ext_div21;
  }


  /** 
   * <em>ext_div21</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv21IsSet() {
    return ext_div21_is_set;
  }


  /** 
   * <em>ext_div21</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv21IsModified() {
    return ext_div21_is_modified;
  }


  /** 
   * <em>ext_div22</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv22()
  {
    return ext_div22;
  }


  /** 
   * <em>ext_div22</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv22IsSet() {
    return ext_div22_is_set;
  }


  /** 
   * <em>ext_div22</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv22IsModified() {
    return ext_div22_is_modified;
  }


  /** 
   * <em>ext_div23</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv23()
  {
    return ext_div23;
  }


  /** 
   * <em>ext_div23</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv23IsSet() {
    return ext_div23_is_set;
  }


  /** 
   * <em>ext_div23</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv23IsModified() {
    return ext_div23_is_modified;
  }


  /** 
   * <em>ext_div24</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv24()
  {
    return ext_div24;
  }


  /** 
   * <em>ext_div24</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv24IsSet() {
    return ext_div24_is_set;
  }


  /** 
   * <em>ext_div24</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv24IsModified() {
    return ext_div24_is_modified;
  }


  /** 
   * <em>ext_div25</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv25()
  {
    return ext_div25;
  }


  /** 
   * <em>ext_div25</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv25IsSet() {
    return ext_div25_is_set;
  }


  /** 
   * <em>ext_div25</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv25IsModified() {
    return ext_div25_is_modified;
  }


  /** 
   * <em>ext_div26</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv26()
  {
    return ext_div26;
  }


  /** 
   * <em>ext_div26</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv26IsSet() {
    return ext_div26_is_set;
  }


  /** 
   * <em>ext_div26</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv26IsModified() {
    return ext_div26_is_modified;
  }


  /** 
   * <em>ext_div27</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv27()
  {
    return ext_div27;
  }


  /** 
   * <em>ext_div27</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv27IsSet() {
    return ext_div27_is_set;
  }


  /** 
   * <em>ext_div27</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv27IsModified() {
    return ext_div27_is_modified;
  }


  /** 
   * <em>ext_div28</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv28()
  {
    return ext_div28;
  }


  /** 
   * <em>ext_div28</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv28IsSet() {
    return ext_div28_is_set;
  }


  /** 
   * <em>ext_div28</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv28IsModified() {
    return ext_div28_is_modified;
  }


  /** 
   * <em>ext_div29</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv29()
  {
    return ext_div29;
  }


  /** 
   * <em>ext_div29</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv29IsSet() {
    return ext_div29_is_set;
  }


  /** 
   * <em>ext_div29</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv29IsModified() {
    return ext_div29_is_modified;
  }


  /** 
   * <em>ext_div30</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv30()
  {
    return ext_div30;
  }


  /** 
   * <em>ext_div30</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv30IsSet() {
    return ext_div30_is_set;
  }


  /** 
   * <em>ext_div30</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv30IsModified() {
    return ext_div30_is_modified;
  }


  /** 
   * <em>ext_div31</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv31()
  {
    return ext_div31;
  }


  /** 
   * <em>ext_div31</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv31IsSet() {
    return ext_div31_is_set;
  }


  /** 
   * <em>ext_div31</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv31IsModified() {
    return ext_div31_is_modified;
  }


  /** 
   * <em>ext_div32</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv32()
  {
    return ext_div32;
  }


  /** 
   * <em>ext_div32</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv32IsSet() {
    return ext_div32_is_set;
  }


  /** 
   * <em>ext_div32</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv32IsModified() {
    return ext_div32_is_modified;
  }


  /** 
   * <em>ext_div33</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv33()
  {
    return ext_div33;
  }


  /** 
   * <em>ext_div33</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv33IsSet() {
    return ext_div33_is_set;
  }


  /** 
   * <em>ext_div33</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv33IsModified() {
    return ext_div33_is_modified;
  }


  /** 
   * <em>ext_div34</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv34()
  {
    return ext_div34;
  }


  /** 
   * <em>ext_div34</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv34IsSet() {
    return ext_div34_is_set;
  }


  /** 
   * <em>ext_div34</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv34IsModified() {
    return ext_div34_is_modified;
  }


  /** 
   * <em>ext_div35</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv35()
  {
    return ext_div35;
  }


  /** 
   * <em>ext_div35</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv35IsSet() {
    return ext_div35_is_set;
  }


  /** 
   * <em>ext_div35</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv35IsModified() {
    return ext_div35_is_modified;
  }


  /** 
   * <em>ext_div36</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv36()
  {
    return ext_div36;
  }


  /** 
   * <em>ext_div36</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv36IsSet() {
    return ext_div36_is_set;
  }


  /** 
   * <em>ext_div36</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv36IsModified() {
    return ext_div36_is_modified;
  }


  /** 
   * <em>ext_div37</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv37()
  {
    return ext_div37;
  }


  /** 
   * <em>ext_div37</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv37IsSet() {
    return ext_div37_is_set;
  }


  /** 
   * <em>ext_div37</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv37IsModified() {
    return ext_div37_is_modified;
  }


  /** 
   * <em>ext_div38</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv38()
  {
    return ext_div38;
  }


  /** 
   * <em>ext_div38</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv38IsSet() {
    return ext_div38_is_set;
  }


  /** 
   * <em>ext_div38</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv38IsModified() {
    return ext_div38_is_modified;
  }


  /** 
   * <em>ext_div39</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv39()
  {
    return ext_div39;
  }


  /** 
   * <em>ext_div39</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv39IsSet() {
    return ext_div39_is_set;
  }


  /** 
   * <em>ext_div39</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv39IsModified() {
    return ext_div39_is_modified;
  }


  /** 
   * <em>ext_div40</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtDiv40()
  {
    return ext_div40;
  }


  /** 
   * <em>ext_div40</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv40IsSet() {
    return ext_div40_is_set;
  }


  /** 
   * <em>ext_div40</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtDiv40IsModified() {
    return ext_div40_is_modified;
  }


  /** 
   * <em>ext_code1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtCode1()
  {
    return ext_code1;
  }


  /** 
   * <em>ext_code1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode1IsSet() {
    return ext_code1_is_set;
  }


  /** 
   * <em>ext_code1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode1IsModified() {
    return ext_code1_is_modified;
  }


  /** 
   * <em>ext_code2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtCode2()
  {
    return ext_code2;
  }


  /** 
   * <em>ext_code2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode2IsSet() {
    return ext_code2_is_set;
  }


  /** 
   * <em>ext_code2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode2IsModified() {
    return ext_code2_is_modified;
  }


  /** 
   * <em>ext_code3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtCode3()
  {
    return ext_code3;
  }


  /** 
   * <em>ext_code3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode3IsSet() {
    return ext_code3_is_set;
  }


  /** 
   * <em>ext_code3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode3IsModified() {
    return ext_code3_is_modified;
  }


  /** 
   * <em>ext_code4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtCode4()
  {
    return ext_code4;
  }


  /** 
   * <em>ext_code4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode4IsSet() {
    return ext_code4_is_set;
  }


  /** 
   * <em>ext_code4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode4IsModified() {
    return ext_code4_is_modified;
  }


  /** 
   * <em>ext_code5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtCode5()
  {
    return ext_code5;
  }


  /** 
   * <em>ext_code5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode5IsSet() {
    return ext_code5_is_set;
  }


  /** 
   * <em>ext_code5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode5IsModified() {
    return ext_code5_is_modified;
  }


  /** 
   * <em>ext_code6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtCode6()
  {
    return ext_code6;
  }


  /** 
   * <em>ext_code6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode6IsSet() {
    return ext_code6_is_set;
  }


  /** 
   * <em>ext_code6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode6IsModified() {
    return ext_code6_is_modified;
  }


  /** 
   * <em>ext_code7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtCode7()
  {
    return ext_code7;
  }


  /** 
   * <em>ext_code7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode7IsSet() {
    return ext_code7_is_set;
  }


  /** 
   * <em>ext_code7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode7IsModified() {
    return ext_code7_is_modified;
  }


  /** 
   * <em>ext_code8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtCode8()
  {
    return ext_code8;
  }


  /** 
   * <em>ext_code8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode8IsSet() {
    return ext_code8_is_set;
  }


  /** 
   * <em>ext_code8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode8IsModified() {
    return ext_code8_is_modified;
  }


  /** 
   * <em>ext_code9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtCode9()
  {
    return ext_code9;
  }


  /** 
   * <em>ext_code9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode9IsSet() {
    return ext_code9_is_set;
  }


  /** 
   * <em>ext_code9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode9IsModified() {
    return ext_code9_is_modified;
  }


  /** 
   * <em>ext_code10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtCode10()
  {
    return ext_code10;
  }


  /** 
   * <em>ext_code10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode10IsSet() {
    return ext_code10_is_set;
  }


  /** 
   * <em>ext_code10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtCode10IsModified() {
    return ext_code10_is_modified;
  }


  /** 
   * <em>ext_text1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText1()
  {
    return ext_text1;
  }


  /** 
   * <em>ext_text1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText1IsSet() {
    return ext_text1_is_set;
  }


  /** 
   * <em>ext_text1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText1IsModified() {
    return ext_text1_is_modified;
  }


  /** 
   * <em>ext_text2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText2()
  {
    return ext_text2;
  }


  /** 
   * <em>ext_text2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText2IsSet() {
    return ext_text2_is_set;
  }


  /** 
   * <em>ext_text2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText2IsModified() {
    return ext_text2_is_modified;
  }


  /** 
   * <em>ext_text3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText3()
  {
    return ext_text3;
  }


  /** 
   * <em>ext_text3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText3IsSet() {
    return ext_text3_is_set;
  }


  /** 
   * <em>ext_text3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText3IsModified() {
    return ext_text3_is_modified;
  }


  /** 
   * <em>ext_text4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText4()
  {
    return ext_text4;
  }


  /** 
   * <em>ext_text4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText4IsSet() {
    return ext_text4_is_set;
  }


  /** 
   * <em>ext_text4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText4IsModified() {
    return ext_text4_is_modified;
  }


  /** 
   * <em>ext_text5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText5()
  {
    return ext_text5;
  }


  /** 
   * <em>ext_text5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText5IsSet() {
    return ext_text5_is_set;
  }


  /** 
   * <em>ext_text5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText5IsModified() {
    return ext_text5_is_modified;
  }


  /** 
   * <em>ext_text6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText6()
  {
    return ext_text6;
  }


  /** 
   * <em>ext_text6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText6IsSet() {
    return ext_text6_is_set;
  }


  /** 
   * <em>ext_text6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText6IsModified() {
    return ext_text6_is_modified;
  }


  /** 
   * <em>ext_text7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText7()
  {
    return ext_text7;
  }


  /** 
   * <em>ext_text7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText7IsSet() {
    return ext_text7_is_set;
  }


  /** 
   * <em>ext_text7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText7IsModified() {
    return ext_text7_is_modified;
  }


  /** 
   * <em>ext_text8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText8()
  {
    return ext_text8;
  }


  /** 
   * <em>ext_text8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText8IsSet() {
    return ext_text8_is_set;
  }


  /** 
   * <em>ext_text8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText8IsModified() {
    return ext_text8_is_modified;
  }


  /** 
   * <em>ext_text9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText9()
  {
    return ext_text9;
  }


  /** 
   * <em>ext_text9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText9IsSet() {
    return ext_text9_is_set;
  }


  /** 
   * <em>ext_text9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText9IsModified() {
    return ext_text9_is_modified;
  }


  /** 
   * <em>ext_text10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText10()
  {
    return ext_text10;
  }


  /** 
   * <em>ext_text10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText10IsSet() {
    return ext_text10_is_set;
  }


  /** 
   * <em>ext_text10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText10IsModified() {
    return ext_text10_is_modified;
  }


  /** 
   * <em>ext_text11</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText11()
  {
    return ext_text11;
  }


  /** 
   * <em>ext_text11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText11IsSet() {
    return ext_text11_is_set;
  }


  /** 
   * <em>ext_text11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText11IsModified() {
    return ext_text11_is_modified;
  }


  /** 
   * <em>ext_text12</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText12()
  {
    return ext_text12;
  }


  /** 
   * <em>ext_text12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText12IsSet() {
    return ext_text12_is_set;
  }


  /** 
   * <em>ext_text12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText12IsModified() {
    return ext_text12_is_modified;
  }


  /** 
   * <em>ext_text13</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText13()
  {
    return ext_text13;
  }


  /** 
   * <em>ext_text13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText13IsSet() {
    return ext_text13_is_set;
  }


  /** 
   * <em>ext_text13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText13IsModified() {
    return ext_text13_is_modified;
  }


  /** 
   * <em>ext_text14</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText14()
  {
    return ext_text14;
  }


  /** 
   * <em>ext_text14</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText14IsSet() {
    return ext_text14_is_set;
  }


  /** 
   * <em>ext_text14</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText14IsModified() {
    return ext_text14_is_modified;
  }


  /** 
   * <em>ext_text15</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText15()
  {
    return ext_text15;
  }


  /** 
   * <em>ext_text15</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText15IsSet() {
    return ext_text15_is_set;
  }


  /** 
   * <em>ext_text15</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText15IsModified() {
    return ext_text15_is_modified;
  }


  /** 
   * <em>ext_text16</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText16()
  {
    return ext_text16;
  }


  /** 
   * <em>ext_text16</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText16IsSet() {
    return ext_text16_is_set;
  }


  /** 
   * <em>ext_text16</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText16IsModified() {
    return ext_text16_is_modified;
  }


  /** 
   * <em>ext_text17</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText17()
  {
    return ext_text17;
  }


  /** 
   * <em>ext_text17</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText17IsSet() {
    return ext_text17_is_set;
  }


  /** 
   * <em>ext_text17</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText17IsModified() {
    return ext_text17_is_modified;
  }


  /** 
   * <em>ext_text18</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText18()
  {
    return ext_text18;
  }


  /** 
   * <em>ext_text18</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText18IsSet() {
    return ext_text18_is_set;
  }


  /** 
   * <em>ext_text18</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText18IsModified() {
    return ext_text18_is_modified;
  }


  /** 
   * <em>ext_text19</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText19()
  {
    return ext_text19;
  }


  /** 
   * <em>ext_text19</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText19IsSet() {
    return ext_text19_is_set;
  }


  /** 
   * <em>ext_text19</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText19IsModified() {
    return ext_text19_is_modified;
  }


  /** 
   * <em>ext_text20</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText20()
  {
    return ext_text20;
  }


  /** 
   * <em>ext_text20</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText20IsSet() {
    return ext_text20_is_set;
  }


  /** 
   * <em>ext_text20</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText20IsModified() {
    return ext_text20_is_modified;
  }


  /** 
   * <em>ext_text21</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText21()
  {
    return ext_text21;
  }


  /** 
   * <em>ext_text21</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText21IsSet() {
    return ext_text21_is_set;
  }


  /** 
   * <em>ext_text21</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText21IsModified() {
    return ext_text21_is_modified;
  }


  /** 
   * <em>ext_text22</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText22()
  {
    return ext_text22;
  }


  /** 
   * <em>ext_text22</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText22IsSet() {
    return ext_text22_is_set;
  }


  /** 
   * <em>ext_text22</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText22IsModified() {
    return ext_text22_is_modified;
  }


  /** 
   * <em>ext_text23</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText23()
  {
    return ext_text23;
  }


  /** 
   * <em>ext_text23</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText23IsSet() {
    return ext_text23_is_set;
  }


  /** 
   * <em>ext_text23</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText23IsModified() {
    return ext_text23_is_modified;
  }


  /** 
   * <em>ext_text24</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText24()
  {
    return ext_text24;
  }


  /** 
   * <em>ext_text24</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText24IsSet() {
    return ext_text24_is_set;
  }


  /** 
   * <em>ext_text24</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText24IsModified() {
    return ext_text24_is_modified;
  }


  /** 
   * <em>ext_text25</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText25()
  {
    return ext_text25;
  }


  /** 
   * <em>ext_text25</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText25IsSet() {
    return ext_text25_is_set;
  }


  /** 
   * <em>ext_text25</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText25IsModified() {
    return ext_text25_is_modified;
  }


  /** 
   * <em>ext_text26</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText26()
  {
    return ext_text26;
  }


  /** 
   * <em>ext_text26</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText26IsSet() {
    return ext_text26_is_set;
  }


  /** 
   * <em>ext_text26</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText26IsModified() {
    return ext_text26_is_modified;
  }


  /** 
   * <em>ext_text27</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText27()
  {
    return ext_text27;
  }


  /** 
   * <em>ext_text27</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText27IsSet() {
    return ext_text27_is_set;
  }


  /** 
   * <em>ext_text27</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText27IsModified() {
    return ext_text27_is_modified;
  }


  /** 
   * <em>ext_text28</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText28()
  {
    return ext_text28;
  }


  /** 
   * <em>ext_text28</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText28IsSet() {
    return ext_text28_is_set;
  }


  /** 
   * <em>ext_text28</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText28IsModified() {
    return ext_text28_is_modified;
  }


  /** 
   * <em>ext_text29</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText29()
  {
    return ext_text29;
  }


  /** 
   * <em>ext_text29</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText29IsSet() {
    return ext_text29_is_set;
  }


  /** 
   * <em>ext_text29</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText29IsModified() {
    return ext_text29_is_modified;
  }


  /** 
   * <em>ext_text30</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText30()
  {
    return ext_text30;
  }


  /** 
   * <em>ext_text30</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText30IsSet() {
    return ext_text30_is_set;
  }


  /** 
   * <em>ext_text30</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText30IsModified() {
    return ext_text30_is_modified;
  }


  /** 
   * <em>ext_text31</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText31()
  {
    return ext_text31;
  }


  /** 
   * <em>ext_text31</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText31IsSet() {
    return ext_text31_is_set;
  }


  /** 
   * <em>ext_text31</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText31IsModified() {
    return ext_text31_is_modified;
  }


  /** 
   * <em>ext_text32</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText32()
  {
    return ext_text32;
  }


  /** 
   * <em>ext_text32</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText32IsSet() {
    return ext_text32_is_set;
  }


  /** 
   * <em>ext_text32</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText32IsModified() {
    return ext_text32_is_modified;
  }


  /** 
   * <em>ext_text33</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText33()
  {
    return ext_text33;
  }


  /** 
   * <em>ext_text33</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText33IsSet() {
    return ext_text33_is_set;
  }


  /** 
   * <em>ext_text33</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText33IsModified() {
    return ext_text33_is_modified;
  }


  /** 
   * <em>ext_text34</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText34()
  {
    return ext_text34;
  }


  /** 
   * <em>ext_text34</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText34IsSet() {
    return ext_text34_is_set;
  }


  /** 
   * <em>ext_text34</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText34IsModified() {
    return ext_text34_is_modified;
  }


  /** 
   * <em>ext_text35</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText35()
  {
    return ext_text35;
  }


  /** 
   * <em>ext_text35</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText35IsSet() {
    return ext_text35_is_set;
  }


  /** 
   * <em>ext_text35</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText35IsModified() {
    return ext_text35_is_modified;
  }


  /** 
   * <em>ext_text36</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText36()
  {
    return ext_text36;
  }


  /** 
   * <em>ext_text36</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText36IsSet() {
    return ext_text36_is_set;
  }


  /** 
   * <em>ext_text36</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText36IsModified() {
    return ext_text36_is_modified;
  }


  /** 
   * <em>ext_text37</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText37()
  {
    return ext_text37;
  }


  /** 
   * <em>ext_text37</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText37IsSet() {
    return ext_text37_is_set;
  }


  /** 
   * <em>ext_text37</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText37IsModified() {
    return ext_text37_is_modified;
  }


  /** 
   * <em>ext_text38</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText38()
  {
    return ext_text38;
  }


  /** 
   * <em>ext_text38</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText38IsSet() {
    return ext_text38_is_set;
  }


  /** 
   * <em>ext_text38</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText38IsModified() {
    return ext_text38_is_modified;
  }


  /** 
   * <em>ext_text39</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText39()
  {
    return ext_text39;
  }


  /** 
   * <em>ext_text39</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText39IsSet() {
    return ext_text39_is_set;
  }


  /** 
   * <em>ext_text39</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText39IsModified() {
    return ext_text39_is_modified;
  }


  /** 
   * <em>ext_text40</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtText40()
  {
    return ext_text40;
  }


  /** 
   * <em>ext_text40</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText40IsSet() {
    return ext_text40_is_set;
  }


  /** 
   * <em>ext_text40</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtText40IsModified() {
    return ext_text40_is_modified;
  }


  /** 
   * <em>ext_value1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue1()
  {
    return ( ext_value1==null? ((long)0): ext_value1.longValue() );
  }


  /** 
   * <em>ext_value1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue1IsNull()
  {
    return ext_value1 == null;
  }


  /** 
   * <em>ext_value1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue1IsSet() {
    return ext_value1_is_set;
  }


  /** 
   * <em>ext_value1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue1IsModified() {
    return ext_value1_is_modified;
  }


  /** 
   * <em>ext_value2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue2()
  {
    return ( ext_value2==null? ((long)0): ext_value2.longValue() );
  }


  /** 
   * <em>ext_value2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue2IsNull()
  {
    return ext_value2 == null;
  }


  /** 
   * <em>ext_value2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue2IsSet() {
    return ext_value2_is_set;
  }


  /** 
   * <em>ext_value2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue2IsModified() {
    return ext_value2_is_modified;
  }


  /** 
   * <em>ext_value3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue3()
  {
    return ( ext_value3==null? ((long)0): ext_value3.longValue() );
  }


  /** 
   * <em>ext_value3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue3IsNull()
  {
    return ext_value3 == null;
  }


  /** 
   * <em>ext_value3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue3IsSet() {
    return ext_value3_is_set;
  }


  /** 
   * <em>ext_value3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue3IsModified() {
    return ext_value3_is_modified;
  }


  /** 
   * <em>ext_value4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue4()
  {
    return ( ext_value4==null? ((long)0): ext_value4.longValue() );
  }


  /** 
   * <em>ext_value4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue4IsNull()
  {
    return ext_value4 == null;
  }


  /** 
   * <em>ext_value4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue4IsSet() {
    return ext_value4_is_set;
  }


  /** 
   * <em>ext_value4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue4IsModified() {
    return ext_value4_is_modified;
  }


  /** 
   * <em>ext_value5</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue5()
  {
    return ( ext_value5==null? ((long)0): ext_value5.longValue() );
  }


  /** 
   * <em>ext_value5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue5IsNull()
  {
    return ext_value5 == null;
  }


  /** 
   * <em>ext_value5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue5IsSet() {
    return ext_value5_is_set;
  }


  /** 
   * <em>ext_value5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue5IsModified() {
    return ext_value5_is_modified;
  }


  /** 
   * <em>ext_value6</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue6()
  {
    return ( ext_value6==null? ((long)0): ext_value6.longValue() );
  }


  /** 
   * <em>ext_value6</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue6IsNull()
  {
    return ext_value6 == null;
  }


  /** 
   * <em>ext_value6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue6IsSet() {
    return ext_value6_is_set;
  }


  /** 
   * <em>ext_value6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue6IsModified() {
    return ext_value6_is_modified;
  }


  /** 
   * <em>ext_value7</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue7()
  {
    return ( ext_value7==null? ((long)0): ext_value7.longValue() );
  }


  /** 
   * <em>ext_value7</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue7IsNull()
  {
    return ext_value7 == null;
  }


  /** 
   * <em>ext_value7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue7IsSet() {
    return ext_value7_is_set;
  }


  /** 
   * <em>ext_value7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue7IsModified() {
    return ext_value7_is_modified;
  }


  /** 
   * <em>ext_value8</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue8()
  {
    return ( ext_value8==null? ((long)0): ext_value8.longValue() );
  }


  /** 
   * <em>ext_value8</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue8IsNull()
  {
    return ext_value8 == null;
  }


  /** 
   * <em>ext_value8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue8IsSet() {
    return ext_value8_is_set;
  }


  /** 
   * <em>ext_value8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue8IsModified() {
    return ext_value8_is_modified;
  }


  /** 
   * <em>ext_value9</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue9()
  {
    return ( ext_value9==null? ((long)0): ext_value9.longValue() );
  }


  /** 
   * <em>ext_value9</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue9IsNull()
  {
    return ext_value9 == null;
  }


  /** 
   * <em>ext_value9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue9IsSet() {
    return ext_value9_is_set;
  }


  /** 
   * <em>ext_value9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue9IsModified() {
    return ext_value9_is_modified;
  }


  /** 
   * <em>ext_value10</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue10()
  {
    return ( ext_value10==null? ((long)0): ext_value10.longValue() );
  }


  /** 
   * <em>ext_value10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue10IsNull()
  {
    return ext_value10 == null;
  }


  /** 
   * <em>ext_value10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue10IsSet() {
    return ext_value10_is_set;
  }


  /** 
   * <em>ext_value10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue10IsModified() {
    return ext_value10_is_modified;
  }


  /** 
   * <em>ext_value11</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue11()
  {
    return ( ext_value11==null? ((long)0): ext_value11.longValue() );
  }


  /** 
   * <em>ext_value11</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue11IsNull()
  {
    return ext_value11 == null;
  }


  /** 
   * <em>ext_value11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue11IsSet() {
    return ext_value11_is_set;
  }


  /** 
   * <em>ext_value11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue11IsModified() {
    return ext_value11_is_modified;
  }


  /** 
   * <em>ext_value12</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue12()
  {
    return ( ext_value12==null? ((long)0): ext_value12.longValue() );
  }


  /** 
   * <em>ext_value12</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue12IsNull()
  {
    return ext_value12 == null;
  }


  /** 
   * <em>ext_value12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue12IsSet() {
    return ext_value12_is_set;
  }


  /** 
   * <em>ext_value12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue12IsModified() {
    return ext_value12_is_modified;
  }


  /** 
   * <em>ext_value13</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue13()
  {
    return ( ext_value13==null? ((long)0): ext_value13.longValue() );
  }


  /** 
   * <em>ext_value13</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue13IsNull()
  {
    return ext_value13 == null;
  }


  /** 
   * <em>ext_value13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue13IsSet() {
    return ext_value13_is_set;
  }


  /** 
   * <em>ext_value13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue13IsModified() {
    return ext_value13_is_modified;
  }


  /** 
   * <em>ext_value14</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue14()
  {
    return ( ext_value14==null? ((long)0): ext_value14.longValue() );
  }


  /** 
   * <em>ext_value14</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue14IsNull()
  {
    return ext_value14 == null;
  }


  /** 
   * <em>ext_value14</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue14IsSet() {
    return ext_value14_is_set;
  }


  /** 
   * <em>ext_value14</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue14IsModified() {
    return ext_value14_is_modified;
  }


  /** 
   * <em>ext_value15</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue15()
  {
    return ( ext_value15==null? ((long)0): ext_value15.longValue() );
  }


  /** 
   * <em>ext_value15</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue15IsNull()
  {
    return ext_value15 == null;
  }


  /** 
   * <em>ext_value15</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue15IsSet() {
    return ext_value15_is_set;
  }


  /** 
   * <em>ext_value15</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue15IsModified() {
    return ext_value15_is_modified;
  }


  /** 
   * <em>ext_value16</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue16()
  {
    return ( ext_value16==null? ((long)0): ext_value16.longValue() );
  }


  /** 
   * <em>ext_value16</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue16IsNull()
  {
    return ext_value16 == null;
  }


  /** 
   * <em>ext_value16</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue16IsSet() {
    return ext_value16_is_set;
  }


  /** 
   * <em>ext_value16</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue16IsModified() {
    return ext_value16_is_modified;
  }


  /** 
   * <em>ext_value17</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue17()
  {
    return ( ext_value17==null? ((long)0): ext_value17.longValue() );
  }


  /** 
   * <em>ext_value17</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue17IsNull()
  {
    return ext_value17 == null;
  }


  /** 
   * <em>ext_value17</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue17IsSet() {
    return ext_value17_is_set;
  }


  /** 
   * <em>ext_value17</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue17IsModified() {
    return ext_value17_is_modified;
  }


  /** 
   * <em>ext_value18</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue18()
  {
    return ( ext_value18==null? ((long)0): ext_value18.longValue() );
  }


  /** 
   * <em>ext_value18</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue18IsNull()
  {
    return ext_value18 == null;
  }


  /** 
   * <em>ext_value18</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue18IsSet() {
    return ext_value18_is_set;
  }


  /** 
   * <em>ext_value18</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue18IsModified() {
    return ext_value18_is_modified;
  }


  /** 
   * <em>ext_value19</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue19()
  {
    return ( ext_value19==null? ((long)0): ext_value19.longValue() );
  }


  /** 
   * <em>ext_value19</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue19IsNull()
  {
    return ext_value19 == null;
  }


  /** 
   * <em>ext_value19</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue19IsSet() {
    return ext_value19_is_set;
  }


  /** 
   * <em>ext_value19</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue19IsModified() {
    return ext_value19_is_modified;
  }


  /** 
   * <em>ext_value20</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue20()
  {
    return ( ext_value20==null? ((long)0): ext_value20.longValue() );
  }


  /** 
   * <em>ext_value20</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue20IsNull()
  {
    return ext_value20 == null;
  }


  /** 
   * <em>ext_value20</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue20IsSet() {
    return ext_value20_is_set;
  }


  /** 
   * <em>ext_value20</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue20IsModified() {
    return ext_value20_is_modified;
  }


  /** 
   * <em>ext_value21</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue21()
  {
    return ( ext_value21==null? ((long)0): ext_value21.longValue() );
  }


  /** 
   * <em>ext_value21</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue21IsNull()
  {
    return ext_value21 == null;
  }


  /** 
   * <em>ext_value21</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue21IsSet() {
    return ext_value21_is_set;
  }


  /** 
   * <em>ext_value21</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue21IsModified() {
    return ext_value21_is_modified;
  }


  /** 
   * <em>ext_value22</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue22()
  {
    return ( ext_value22==null? ((long)0): ext_value22.longValue() );
  }


  /** 
   * <em>ext_value22</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue22IsNull()
  {
    return ext_value22 == null;
  }


  /** 
   * <em>ext_value22</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue22IsSet() {
    return ext_value22_is_set;
  }


  /** 
   * <em>ext_value22</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue22IsModified() {
    return ext_value22_is_modified;
  }


  /** 
   * <em>ext_value23</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue23()
  {
    return ( ext_value23==null? ((long)0): ext_value23.longValue() );
  }


  /** 
   * <em>ext_value23</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue23IsNull()
  {
    return ext_value23 == null;
  }


  /** 
   * <em>ext_value23</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue23IsSet() {
    return ext_value23_is_set;
  }


  /** 
   * <em>ext_value23</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue23IsModified() {
    return ext_value23_is_modified;
  }


  /** 
   * <em>ext_value24</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue24()
  {
    return ( ext_value24==null? ((long)0): ext_value24.longValue() );
  }


  /** 
   * <em>ext_value24</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue24IsNull()
  {
    return ext_value24 == null;
  }


  /** 
   * <em>ext_value24</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue24IsSet() {
    return ext_value24_is_set;
  }


  /** 
   * <em>ext_value24</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue24IsModified() {
    return ext_value24_is_modified;
  }


  /** 
   * <em>ext_value25</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue25()
  {
    return ( ext_value25==null? ((long)0): ext_value25.longValue() );
  }


  /** 
   * <em>ext_value25</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue25IsNull()
  {
    return ext_value25 == null;
  }


  /** 
   * <em>ext_value25</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue25IsSet() {
    return ext_value25_is_set;
  }


  /** 
   * <em>ext_value25</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue25IsModified() {
    return ext_value25_is_modified;
  }


  /** 
   * <em>ext_value26</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue26()
  {
    return ( ext_value26==null? ((long)0): ext_value26.longValue() );
  }


  /** 
   * <em>ext_value26</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue26IsNull()
  {
    return ext_value26 == null;
  }


  /** 
   * <em>ext_value26</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue26IsSet() {
    return ext_value26_is_set;
  }


  /** 
   * <em>ext_value26</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue26IsModified() {
    return ext_value26_is_modified;
  }


  /** 
   * <em>ext_value27</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue27()
  {
    return ( ext_value27==null? ((long)0): ext_value27.longValue() );
  }


  /** 
   * <em>ext_value27</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue27IsNull()
  {
    return ext_value27 == null;
  }


  /** 
   * <em>ext_value27</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue27IsSet() {
    return ext_value27_is_set;
  }


  /** 
   * <em>ext_value27</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue27IsModified() {
    return ext_value27_is_modified;
  }


  /** 
   * <em>ext_value28</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue28()
  {
    return ( ext_value28==null? ((long)0): ext_value28.longValue() );
  }


  /** 
   * <em>ext_value28</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue28IsNull()
  {
    return ext_value28 == null;
  }


  /** 
   * <em>ext_value28</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue28IsSet() {
    return ext_value28_is_set;
  }


  /** 
   * <em>ext_value28</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue28IsModified() {
    return ext_value28_is_modified;
  }


  /** 
   * <em>ext_value29</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue29()
  {
    return ( ext_value29==null? ((long)0): ext_value29.longValue() );
  }


  /** 
   * <em>ext_value29</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue29IsNull()
  {
    return ext_value29 == null;
  }


  /** 
   * <em>ext_value29</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue29IsSet() {
    return ext_value29_is_set;
  }


  /** 
   * <em>ext_value29</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue29IsModified() {
    return ext_value29_is_modified;
  }


  /** 
   * <em>ext_value30</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExtValue30()
  {
    return ( ext_value30==null? ((long)0): ext_value30.longValue() );
  }


  /** 
   * <em>ext_value30</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExtValue30IsNull()
  {
    return ext_value30 == null;
  }


  /** 
   * <em>ext_value30</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue30IsSet() {
    return ext_value30_is_set;
  }


  /** 
   * <em>ext_value30</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtValue30IsModified() {
    return ext_value30_is_modified;
  }


  /** 
   * <em>ext_note1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtNote1()
  {
    return ext_note1;
  }


  /** 
   * <em>ext_note1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtNote1IsSet() {
    return ext_note1_is_set;
  }


  /** 
   * <em>ext_note1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtNote1IsModified() {
    return ext_note1_is_modified;
  }


  /** 
   * <em>ext_note2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtNote2()
  {
    return ext_note2;
  }


  /** 
   * <em>ext_note2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtNote2IsSet() {
    return ext_note2_is_set;
  }


  /** 
   * <em>ext_note2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtNote2IsModified() {
    return ext_note2_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * <em>fund_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFundCode()
  {
    return fund_code;
  }


  /** 
   * <em>fund_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundCodeIsSet() {
    return fund_code_is_set;
  }


  /** 
   * <em>fund_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundCodeIsModified() {
    return fund_code_is_modified;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarTraderCode()
  {
    return sonar_trader_code;
  }


  /** 
   * <em>sonar_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsSet() {
    return sonar_trader_code_is_set;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsModified() {
    return sonar_trader_code_is_modified;
  }


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
  }


  /** 
   * <em>error_reason_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getErrorReasonCode()
  {
    return error_reason_code;
  }


  /** 
   * <em>error_reason_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorReasonCodeIsSet() {
    return error_reason_code_is_set;
  }


  /** 
   * <em>error_reason_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorReasonCodeIsModified() {
    return error_reason_code_is_modified;
  }


  /** 
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsSet() {
    return order_request_number_is_set;
  }


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsModified() {
    return order_request_number_is_modified;
  }


  /** 
   * <em>request_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRequestCode()
  {
    return request_code;
  }


  /** 
   * <em>request_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsSet() {
    return request_code_is_set;
  }


  /** 
   * <em>request_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsModified() {
    return request_code_is_modified;
  }


  /** 
   * <em>send_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSendTimestamp()
  {
    return send_timestamp;
  }


  /** 
   * <em>send_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendTimestampIsSet() {
    return send_timestamp_is_set;
  }


  /** 
   * <em>send_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendTimestampIsModified() {
    return send_timestamp_is_modified;
  }


  /** 
   * <em>receipt_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getReceiptTimestamp()
  {
    return receipt_timestamp;
  }


  /** 
   * <em>receipt_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptTimestampIsSet() {
    return receipt_timestamp_is_set;
  }


  /** 
   * <em>receipt_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptTimestampIsModified() {
    return receipt_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new VariousInformPK(institution_code, inform_div, request_number, branch_code);
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>inform_div</em>カラムの値を設定します。 
   *
   * @@param p_informDiv <em>inform_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setInformDiv( String p_informDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    inform_div = p_informDiv;
    inform_div_is_set = true;
    inform_div_is_modified = true;
  }


  /** 
   * <em>request_number</em>カラムの値を設定します。 
   *
   * @@param p_requestNumber <em>request_number</em>カラムの値をあらわす13桁以下のString値 
   */
  public final void setRequestNumber( String p_requestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_number = p_requestNumber;
    request_number_is_set = true;
    request_number_is_modified = true;
  }


  /** 
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>account_code</em>カラムの値を設定します。 
   *
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>trader_code</em>カラムの値を設定します。 
   *
   * @@param p_traderCode <em>trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setTraderCode( String p_traderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_code = p_traderCode;
    trader_code_is_set = true;
    trader_code_is_modified = true;
  }


  /** 
   * <em>account_name</em>カラムの値を設定します。 
   *
   * @@param p_accountName <em>account_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setAccountName( String p_accountName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_name = p_accountName;
    account_name_is_set = true;
    account_name_is_modified = true;
  }


  /** 
   * <em>email_address</em>カラムの値を設定します。 
   *
   * @@param p_emailAddress <em>email_address</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setEmailAddress( String p_emailAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    email_address = p_emailAddress;
    email_address_is_set = true;
    email_address_is_modified = true;
  }


  /** 
   * <em>ext_div1</em>カラムの値を設定します。 
   *
   * @@param p_extDiv1 <em>ext_div1</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv1( String p_extDiv1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div1 = p_extDiv1;
    ext_div1_is_set = true;
    ext_div1_is_modified = true;
  }


  /** 
   * <em>ext_div2</em>カラムの値を設定します。 
   *
   * @@param p_extDiv2 <em>ext_div2</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv2( String p_extDiv2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div2 = p_extDiv2;
    ext_div2_is_set = true;
    ext_div2_is_modified = true;
  }


  /** 
   * <em>ext_div3</em>カラムの値を設定します。 
   *
   * @@param p_extDiv3 <em>ext_div3</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv3( String p_extDiv3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div3 = p_extDiv3;
    ext_div3_is_set = true;
    ext_div3_is_modified = true;
  }


  /** 
   * <em>ext_div4</em>カラムの値を設定します。 
   *
   * @@param p_extDiv4 <em>ext_div4</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv4( String p_extDiv4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div4 = p_extDiv4;
    ext_div4_is_set = true;
    ext_div4_is_modified = true;
  }


  /** 
   * <em>ext_div5</em>カラムの値を設定します。 
   *
   * @@param p_extDiv5 <em>ext_div5</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv5( String p_extDiv5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div5 = p_extDiv5;
    ext_div5_is_set = true;
    ext_div5_is_modified = true;
  }


  /** 
   * <em>ext_div6</em>カラムの値を設定します。 
   *
   * @@param p_extDiv6 <em>ext_div6</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv6( String p_extDiv6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div6 = p_extDiv6;
    ext_div6_is_set = true;
    ext_div6_is_modified = true;
  }


  /** 
   * <em>ext_div7</em>カラムの値を設定します。 
   *
   * @@param p_extDiv7 <em>ext_div7</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv7( String p_extDiv7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div7 = p_extDiv7;
    ext_div7_is_set = true;
    ext_div7_is_modified = true;
  }


  /** 
   * <em>ext_div8</em>カラムの値を設定します。 
   *
   * @@param p_extDiv8 <em>ext_div8</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv8( String p_extDiv8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div8 = p_extDiv8;
    ext_div8_is_set = true;
    ext_div8_is_modified = true;
  }


  /** 
   * <em>ext_div9</em>カラムの値を設定します。 
   *
   * @@param p_extDiv9 <em>ext_div9</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv9( String p_extDiv9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div9 = p_extDiv9;
    ext_div9_is_set = true;
    ext_div9_is_modified = true;
  }


  /** 
   * <em>ext_div10</em>カラムの値を設定します。 
   *
   * @@param p_extDiv10 <em>ext_div10</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv10( String p_extDiv10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div10 = p_extDiv10;
    ext_div10_is_set = true;
    ext_div10_is_modified = true;
  }


  /** 
   * <em>ext_div11</em>カラムの値を設定します。 
   *
   * @@param p_extDiv11 <em>ext_div11</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv11( String p_extDiv11 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div11 = p_extDiv11;
    ext_div11_is_set = true;
    ext_div11_is_modified = true;
  }


  /** 
   * <em>ext_div12</em>カラムの値を設定します。 
   *
   * @@param p_extDiv12 <em>ext_div12</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv12( String p_extDiv12 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div12 = p_extDiv12;
    ext_div12_is_set = true;
    ext_div12_is_modified = true;
  }


  /** 
   * <em>ext_div13</em>カラムの値を設定します。 
   *
   * @@param p_extDiv13 <em>ext_div13</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv13( String p_extDiv13 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div13 = p_extDiv13;
    ext_div13_is_set = true;
    ext_div13_is_modified = true;
  }


  /** 
   * <em>ext_div14</em>カラムの値を設定します。 
   *
   * @@param p_extDiv14 <em>ext_div14</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv14( String p_extDiv14 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div14 = p_extDiv14;
    ext_div14_is_set = true;
    ext_div14_is_modified = true;
  }


  /** 
   * <em>ext_div15</em>カラムの値を設定します。 
   *
   * @@param p_extDiv15 <em>ext_div15</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv15( String p_extDiv15 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div15 = p_extDiv15;
    ext_div15_is_set = true;
    ext_div15_is_modified = true;
  }


  /** 
   * <em>ext_div16</em>カラムの値を設定します。 
   *
   * @@param p_extDiv16 <em>ext_div16</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv16( String p_extDiv16 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div16 = p_extDiv16;
    ext_div16_is_set = true;
    ext_div16_is_modified = true;
  }


  /** 
   * <em>ext_div17</em>カラムの値を設定します。 
   *
   * @@param p_extDiv17 <em>ext_div17</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv17( String p_extDiv17 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div17 = p_extDiv17;
    ext_div17_is_set = true;
    ext_div17_is_modified = true;
  }


  /** 
   * <em>ext_div18</em>カラムの値を設定します。 
   *
   * @@param p_extDiv18 <em>ext_div18</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv18( String p_extDiv18 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div18 = p_extDiv18;
    ext_div18_is_set = true;
    ext_div18_is_modified = true;
  }


  /** 
   * <em>ext_div19</em>カラムの値を設定します。 
   *
   * @@param p_extDiv19 <em>ext_div19</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv19( String p_extDiv19 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div19 = p_extDiv19;
    ext_div19_is_set = true;
    ext_div19_is_modified = true;
  }


  /** 
   * <em>ext_div20</em>カラムの値を設定します。 
   *
   * @@param p_extDiv20 <em>ext_div20</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv20( String p_extDiv20 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div20 = p_extDiv20;
    ext_div20_is_set = true;
    ext_div20_is_modified = true;
  }


  /** 
   * <em>ext_div21</em>カラムの値を設定します。 
   *
   * @@param p_extDiv21 <em>ext_div21</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv21( String p_extDiv21 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div21 = p_extDiv21;
    ext_div21_is_set = true;
    ext_div21_is_modified = true;
  }


  /** 
   * <em>ext_div22</em>カラムの値を設定します。 
   *
   * @@param p_extDiv22 <em>ext_div22</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv22( String p_extDiv22 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div22 = p_extDiv22;
    ext_div22_is_set = true;
    ext_div22_is_modified = true;
  }


  /** 
   * <em>ext_div23</em>カラムの値を設定します。 
   *
   * @@param p_extDiv23 <em>ext_div23</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv23( String p_extDiv23 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div23 = p_extDiv23;
    ext_div23_is_set = true;
    ext_div23_is_modified = true;
  }


  /** 
   * <em>ext_div24</em>カラムの値を設定します。 
   *
   * @@param p_extDiv24 <em>ext_div24</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv24( String p_extDiv24 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div24 = p_extDiv24;
    ext_div24_is_set = true;
    ext_div24_is_modified = true;
  }


  /** 
   * <em>ext_div25</em>カラムの値を設定します。 
   *
   * @@param p_extDiv25 <em>ext_div25</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv25( String p_extDiv25 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div25 = p_extDiv25;
    ext_div25_is_set = true;
    ext_div25_is_modified = true;
  }


  /** 
   * <em>ext_div26</em>カラムの値を設定します。 
   *
   * @@param p_extDiv26 <em>ext_div26</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv26( String p_extDiv26 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div26 = p_extDiv26;
    ext_div26_is_set = true;
    ext_div26_is_modified = true;
  }


  /** 
   * <em>ext_div27</em>カラムの値を設定します。 
   *
   * @@param p_extDiv27 <em>ext_div27</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv27( String p_extDiv27 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div27 = p_extDiv27;
    ext_div27_is_set = true;
    ext_div27_is_modified = true;
  }


  /** 
   * <em>ext_div28</em>カラムの値を設定します。 
   *
   * @@param p_extDiv28 <em>ext_div28</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv28( String p_extDiv28 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div28 = p_extDiv28;
    ext_div28_is_set = true;
    ext_div28_is_modified = true;
  }


  /** 
   * <em>ext_div29</em>カラムの値を設定します。 
   *
   * @@param p_extDiv29 <em>ext_div29</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv29( String p_extDiv29 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div29 = p_extDiv29;
    ext_div29_is_set = true;
    ext_div29_is_modified = true;
  }


  /** 
   * <em>ext_div30</em>カラムの値を設定します。 
   *
   * @@param p_extDiv30 <em>ext_div30</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv30( String p_extDiv30 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div30 = p_extDiv30;
    ext_div30_is_set = true;
    ext_div30_is_modified = true;
  }


  /** 
   * <em>ext_div31</em>カラムの値を設定します。 
   *
   * @@param p_extDiv31 <em>ext_div31</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv31( String p_extDiv31 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div31 = p_extDiv31;
    ext_div31_is_set = true;
    ext_div31_is_modified = true;
  }


  /** 
   * <em>ext_div32</em>カラムの値を設定します。 
   *
   * @@param p_extDiv32 <em>ext_div32</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv32( String p_extDiv32 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div32 = p_extDiv32;
    ext_div32_is_set = true;
    ext_div32_is_modified = true;
  }


  /** 
   * <em>ext_div33</em>カラムの値を設定します。 
   *
   * @@param p_extDiv33 <em>ext_div33</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv33( String p_extDiv33 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div33 = p_extDiv33;
    ext_div33_is_set = true;
    ext_div33_is_modified = true;
  }


  /** 
   * <em>ext_div34</em>カラムの値を設定します。 
   *
   * @@param p_extDiv34 <em>ext_div34</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv34( String p_extDiv34 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div34 = p_extDiv34;
    ext_div34_is_set = true;
    ext_div34_is_modified = true;
  }


  /** 
   * <em>ext_div35</em>カラムの値を設定します。 
   *
   * @@param p_extDiv35 <em>ext_div35</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv35( String p_extDiv35 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div35 = p_extDiv35;
    ext_div35_is_set = true;
    ext_div35_is_modified = true;
  }


  /** 
   * <em>ext_div36</em>カラムの値を設定します。 
   *
   * @@param p_extDiv36 <em>ext_div36</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv36( String p_extDiv36 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div36 = p_extDiv36;
    ext_div36_is_set = true;
    ext_div36_is_modified = true;
  }


  /** 
   * <em>ext_div37</em>カラムの値を設定します。 
   *
   * @@param p_extDiv37 <em>ext_div37</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv37( String p_extDiv37 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div37 = p_extDiv37;
    ext_div37_is_set = true;
    ext_div37_is_modified = true;
  }


  /** 
   * <em>ext_div38</em>カラムの値を設定します。 
   *
   * @@param p_extDiv38 <em>ext_div38</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv38( String p_extDiv38 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div38 = p_extDiv38;
    ext_div38_is_set = true;
    ext_div38_is_modified = true;
  }


  /** 
   * <em>ext_div39</em>カラムの値を設定します。 
   *
   * @@param p_extDiv39 <em>ext_div39</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv39( String p_extDiv39 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div39 = p_extDiv39;
    ext_div39_is_set = true;
    ext_div39_is_modified = true;
  }


  /** 
   * <em>ext_div40</em>カラムの値を設定します。 
   *
   * @@param p_extDiv40 <em>ext_div40</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtDiv40( String p_extDiv40 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_div40 = p_extDiv40;
    ext_div40_is_set = true;
    ext_div40_is_modified = true;
  }


  /** 
   * <em>ext_code1</em>カラムの値を設定します。 
   *
   * @@param p_extCode1 <em>ext_code1</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtCode1( String p_extCode1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_code1 = p_extCode1;
    ext_code1_is_set = true;
    ext_code1_is_modified = true;
  }


  /** 
   * <em>ext_code2</em>カラムの値を設定します。 
   *
   * @@param p_extCode2 <em>ext_code2</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtCode2( String p_extCode2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_code2 = p_extCode2;
    ext_code2_is_set = true;
    ext_code2_is_modified = true;
  }


  /** 
   * <em>ext_code3</em>カラムの値を設定します。 
   *
   * @@param p_extCode3 <em>ext_code3</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtCode3( String p_extCode3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_code3 = p_extCode3;
    ext_code3_is_set = true;
    ext_code3_is_modified = true;
  }


  /** 
   * <em>ext_code4</em>カラムの値を設定します。 
   *
   * @@param p_extCode4 <em>ext_code4</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtCode4( String p_extCode4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_code4 = p_extCode4;
    ext_code4_is_set = true;
    ext_code4_is_modified = true;
  }


  /** 
   * <em>ext_code5</em>カラムの値を設定します。 
   *
   * @@param p_extCode5 <em>ext_code5</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtCode5( String p_extCode5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_code5 = p_extCode5;
    ext_code5_is_set = true;
    ext_code5_is_modified = true;
  }


  /** 
   * <em>ext_code6</em>カラムの値を設定します。 
   *
   * @@param p_extCode6 <em>ext_code6</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtCode6( String p_extCode6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_code6 = p_extCode6;
    ext_code6_is_set = true;
    ext_code6_is_modified = true;
  }


  /** 
   * <em>ext_code7</em>カラムの値を設定します。 
   *
   * @@param p_extCode7 <em>ext_code7</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtCode7( String p_extCode7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_code7 = p_extCode7;
    ext_code7_is_set = true;
    ext_code7_is_modified = true;
  }


  /** 
   * <em>ext_code8</em>カラムの値を設定します。 
   *
   * @@param p_extCode8 <em>ext_code8</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtCode8( String p_extCode8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_code8 = p_extCode8;
    ext_code8_is_set = true;
    ext_code8_is_modified = true;
  }


  /** 
   * <em>ext_code9</em>カラムの値を設定します。 
   *
   * @@param p_extCode9 <em>ext_code9</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtCode9( String p_extCode9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_code9 = p_extCode9;
    ext_code9_is_set = true;
    ext_code9_is_modified = true;
  }


  /** 
   * <em>ext_code10</em>カラムの値を設定します。 
   *
   * @@param p_extCode10 <em>ext_code10</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtCode10( String p_extCode10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_code10 = p_extCode10;
    ext_code10_is_set = true;
    ext_code10_is_modified = true;
  }


  /** 
   * <em>ext_text1</em>カラムの値を設定します。 
   *
   * @@param p_extText1 <em>ext_text1</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText1( String p_extText1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text1 = p_extText1;
    ext_text1_is_set = true;
    ext_text1_is_modified = true;
  }


  /** 
   * <em>ext_text2</em>カラムの値を設定します。 
   *
   * @@param p_extText2 <em>ext_text2</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText2( String p_extText2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text2 = p_extText2;
    ext_text2_is_set = true;
    ext_text2_is_modified = true;
  }


  /** 
   * <em>ext_text3</em>カラムの値を設定します。 
   *
   * @@param p_extText3 <em>ext_text3</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText3( String p_extText3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text3 = p_extText3;
    ext_text3_is_set = true;
    ext_text3_is_modified = true;
  }


  /** 
   * <em>ext_text4</em>カラムの値を設定します。 
   *
   * @@param p_extText4 <em>ext_text4</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText4( String p_extText4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text4 = p_extText4;
    ext_text4_is_set = true;
    ext_text4_is_modified = true;
  }


  /** 
   * <em>ext_text5</em>カラムの値を設定します。 
   *
   * @@param p_extText5 <em>ext_text5</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText5( String p_extText5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text5 = p_extText5;
    ext_text5_is_set = true;
    ext_text5_is_modified = true;
  }


  /** 
   * <em>ext_text6</em>カラムの値を設定します。 
   *
   * @@param p_extText6 <em>ext_text6</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText6( String p_extText6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text6 = p_extText6;
    ext_text6_is_set = true;
    ext_text6_is_modified = true;
  }


  /** 
   * <em>ext_text7</em>カラムの値を設定します。 
   *
   * @@param p_extText7 <em>ext_text7</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText7( String p_extText7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text7 = p_extText7;
    ext_text7_is_set = true;
    ext_text7_is_modified = true;
  }


  /** 
   * <em>ext_text8</em>カラムの値を設定します。 
   *
   * @@param p_extText8 <em>ext_text8</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText8( String p_extText8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text8 = p_extText8;
    ext_text8_is_set = true;
    ext_text8_is_modified = true;
  }


  /** 
   * <em>ext_text9</em>カラムの値を設定します。 
   *
   * @@param p_extText9 <em>ext_text9</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText9( String p_extText9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text9 = p_extText9;
    ext_text9_is_set = true;
    ext_text9_is_modified = true;
  }


  /** 
   * <em>ext_text10</em>カラムの値を設定します。 
   *
   * @@param p_extText10 <em>ext_text10</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText10( String p_extText10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text10 = p_extText10;
    ext_text10_is_set = true;
    ext_text10_is_modified = true;
  }


  /** 
   * <em>ext_text11</em>カラムの値を設定します。 
   *
   * @@param p_extText11 <em>ext_text11</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText11( String p_extText11 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text11 = p_extText11;
    ext_text11_is_set = true;
    ext_text11_is_modified = true;
  }


  /** 
   * <em>ext_text12</em>カラムの値を設定します。 
   *
   * @@param p_extText12 <em>ext_text12</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText12( String p_extText12 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text12 = p_extText12;
    ext_text12_is_set = true;
    ext_text12_is_modified = true;
  }


  /** 
   * <em>ext_text13</em>カラムの値を設定します。 
   *
   * @@param p_extText13 <em>ext_text13</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText13( String p_extText13 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text13 = p_extText13;
    ext_text13_is_set = true;
    ext_text13_is_modified = true;
  }


  /** 
   * <em>ext_text14</em>カラムの値を設定します。 
   *
   * @@param p_extText14 <em>ext_text14</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText14( String p_extText14 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text14 = p_extText14;
    ext_text14_is_set = true;
    ext_text14_is_modified = true;
  }


  /** 
   * <em>ext_text15</em>カラムの値を設定します。 
   *
   * @@param p_extText15 <em>ext_text15</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText15( String p_extText15 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text15 = p_extText15;
    ext_text15_is_set = true;
    ext_text15_is_modified = true;
  }


  /** 
   * <em>ext_text16</em>カラムの値を設定します。 
   *
   * @@param p_extText16 <em>ext_text16</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText16( String p_extText16 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text16 = p_extText16;
    ext_text16_is_set = true;
    ext_text16_is_modified = true;
  }


  /** 
   * <em>ext_text17</em>カラムの値を設定します。 
   *
   * @@param p_extText17 <em>ext_text17</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText17( String p_extText17 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text17 = p_extText17;
    ext_text17_is_set = true;
    ext_text17_is_modified = true;
  }


  /** 
   * <em>ext_text18</em>カラムの値を設定します。 
   *
   * @@param p_extText18 <em>ext_text18</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText18( String p_extText18 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text18 = p_extText18;
    ext_text18_is_set = true;
    ext_text18_is_modified = true;
  }


  /** 
   * <em>ext_text19</em>カラムの値を設定します。 
   *
   * @@param p_extText19 <em>ext_text19</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText19( String p_extText19 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text19 = p_extText19;
    ext_text19_is_set = true;
    ext_text19_is_modified = true;
  }


  /** 
   * <em>ext_text20</em>カラムの値を設定します。 
   *
   * @@param p_extText20 <em>ext_text20</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText20( String p_extText20 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text20 = p_extText20;
    ext_text20_is_set = true;
    ext_text20_is_modified = true;
  }


  /** 
   * <em>ext_text21</em>カラムの値を設定します。 
   *
   * @@param p_extText21 <em>ext_text21</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText21( String p_extText21 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text21 = p_extText21;
    ext_text21_is_set = true;
    ext_text21_is_modified = true;
  }


  /** 
   * <em>ext_text22</em>カラムの値を設定します。 
   *
   * @@param p_extText22 <em>ext_text22</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText22( String p_extText22 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text22 = p_extText22;
    ext_text22_is_set = true;
    ext_text22_is_modified = true;
  }


  /** 
   * <em>ext_text23</em>カラムの値を設定します。 
   *
   * @@param p_extText23 <em>ext_text23</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText23( String p_extText23 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text23 = p_extText23;
    ext_text23_is_set = true;
    ext_text23_is_modified = true;
  }


  /** 
   * <em>ext_text24</em>カラムの値を設定します。 
   *
   * @@param p_extText24 <em>ext_text24</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText24( String p_extText24 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text24 = p_extText24;
    ext_text24_is_set = true;
    ext_text24_is_modified = true;
  }


  /** 
   * <em>ext_text25</em>カラムの値を設定します。 
   *
   * @@param p_extText25 <em>ext_text25</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText25( String p_extText25 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text25 = p_extText25;
    ext_text25_is_set = true;
    ext_text25_is_modified = true;
  }


  /** 
   * <em>ext_text26</em>カラムの値を設定します。 
   *
   * @@param p_extText26 <em>ext_text26</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText26( String p_extText26 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text26 = p_extText26;
    ext_text26_is_set = true;
    ext_text26_is_modified = true;
  }


  /** 
   * <em>ext_text27</em>カラムの値を設定します。 
   *
   * @@param p_extText27 <em>ext_text27</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText27( String p_extText27 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text27 = p_extText27;
    ext_text27_is_set = true;
    ext_text27_is_modified = true;
  }


  /** 
   * <em>ext_text28</em>カラムの値を設定します。 
   *
   * @@param p_extText28 <em>ext_text28</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText28( String p_extText28 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text28 = p_extText28;
    ext_text28_is_set = true;
    ext_text28_is_modified = true;
  }


  /** 
   * <em>ext_text29</em>カラムの値を設定します。 
   *
   * @@param p_extText29 <em>ext_text29</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText29( String p_extText29 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text29 = p_extText29;
    ext_text29_is_set = true;
    ext_text29_is_modified = true;
  }


  /** 
   * <em>ext_text30</em>カラムの値を設定します。 
   *
   * @@param p_extText30 <em>ext_text30</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText30( String p_extText30 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text30 = p_extText30;
    ext_text30_is_set = true;
    ext_text30_is_modified = true;
  }


  /** 
   * <em>ext_text31</em>カラムの値を設定します。 
   *
   * @@param p_extText31 <em>ext_text31</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText31( String p_extText31 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text31 = p_extText31;
    ext_text31_is_set = true;
    ext_text31_is_modified = true;
  }


  /** 
   * <em>ext_text32</em>カラムの値を設定します。 
   *
   * @@param p_extText32 <em>ext_text32</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText32( String p_extText32 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text32 = p_extText32;
    ext_text32_is_set = true;
    ext_text32_is_modified = true;
  }


  /** 
   * <em>ext_text33</em>カラムの値を設定します。 
   *
   * @@param p_extText33 <em>ext_text33</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText33( String p_extText33 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text33 = p_extText33;
    ext_text33_is_set = true;
    ext_text33_is_modified = true;
  }


  /** 
   * <em>ext_text34</em>カラムの値を設定します。 
   *
   * @@param p_extText34 <em>ext_text34</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText34( String p_extText34 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text34 = p_extText34;
    ext_text34_is_set = true;
    ext_text34_is_modified = true;
  }


  /** 
   * <em>ext_text35</em>カラムの値を設定します。 
   *
   * @@param p_extText35 <em>ext_text35</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText35( String p_extText35 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text35 = p_extText35;
    ext_text35_is_set = true;
    ext_text35_is_modified = true;
  }


  /** 
   * <em>ext_text36</em>カラムの値を設定します。 
   *
   * @@param p_extText36 <em>ext_text36</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText36( String p_extText36 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text36 = p_extText36;
    ext_text36_is_set = true;
    ext_text36_is_modified = true;
  }


  /** 
   * <em>ext_text37</em>カラムの値を設定します。 
   *
   * @@param p_extText37 <em>ext_text37</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText37( String p_extText37 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text37 = p_extText37;
    ext_text37_is_set = true;
    ext_text37_is_modified = true;
  }


  /** 
   * <em>ext_text38</em>カラムの値を設定します。 
   *
   * @@param p_extText38 <em>ext_text38</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText38( String p_extText38 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text38 = p_extText38;
    ext_text38_is_set = true;
    ext_text38_is_modified = true;
  }


  /** 
   * <em>ext_text39</em>カラムの値を設定します。 
   *
   * @@param p_extText39 <em>ext_text39</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText39( String p_extText39 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text39 = p_extText39;
    ext_text39_is_set = true;
    ext_text39_is_modified = true;
  }


  /** 
   * <em>ext_text40</em>カラムの値を設定します。 
   *
   * @@param p_extText40 <em>ext_text40</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtText40( String p_extText40 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_text40 = p_extText40;
    ext_text40_is_set = true;
    ext_text40_is_modified = true;
  }


  /** 
   * <em>ext_value1</em>カラムの値を設定します。 
   *
   * @@param p_extValue1 <em>ext_value1</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue1( long p_extValue1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value1 = new Long(p_extValue1);
    ext_value1_is_set = true;
    ext_value1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value1</em>カラムに値を設定します。 
   */
  public final void setExtValue1( Long p_extValue1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value1 = p_extValue1;
    ext_value1_is_set = true;
    ext_value1_is_modified = true;
  }


  /** 
   * <em>ext_value2</em>カラムの値を設定します。 
   *
   * @@param p_extValue2 <em>ext_value2</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue2( long p_extValue2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value2 = new Long(p_extValue2);
    ext_value2_is_set = true;
    ext_value2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value2</em>カラムに値を設定します。 
   */
  public final void setExtValue2( Long p_extValue2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value2 = p_extValue2;
    ext_value2_is_set = true;
    ext_value2_is_modified = true;
  }


  /** 
   * <em>ext_value3</em>カラムの値を設定します。 
   *
   * @@param p_extValue3 <em>ext_value3</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue3( long p_extValue3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value3 = new Long(p_extValue3);
    ext_value3_is_set = true;
    ext_value3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value3</em>カラムに値を設定します。 
   */
  public final void setExtValue3( Long p_extValue3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value3 = p_extValue3;
    ext_value3_is_set = true;
    ext_value3_is_modified = true;
  }


  /** 
   * <em>ext_value4</em>カラムの値を設定します。 
   *
   * @@param p_extValue4 <em>ext_value4</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue4( long p_extValue4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value4 = new Long(p_extValue4);
    ext_value4_is_set = true;
    ext_value4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value4</em>カラムに値を設定します。 
   */
  public final void setExtValue4( Long p_extValue4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value4 = p_extValue4;
    ext_value4_is_set = true;
    ext_value4_is_modified = true;
  }


  /** 
   * <em>ext_value5</em>カラムの値を設定します。 
   *
   * @@param p_extValue5 <em>ext_value5</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue5( long p_extValue5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value5 = new Long(p_extValue5);
    ext_value5_is_set = true;
    ext_value5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value5</em>カラムに値を設定します。 
   */
  public final void setExtValue5( Long p_extValue5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value5 = p_extValue5;
    ext_value5_is_set = true;
    ext_value5_is_modified = true;
  }


  /** 
   * <em>ext_value6</em>カラムの値を設定します。 
   *
   * @@param p_extValue6 <em>ext_value6</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue6( long p_extValue6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value6 = new Long(p_extValue6);
    ext_value6_is_set = true;
    ext_value6_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value6</em>カラムに値を設定します。 
   */
  public final void setExtValue6( Long p_extValue6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value6 = p_extValue6;
    ext_value6_is_set = true;
    ext_value6_is_modified = true;
  }


  /** 
   * <em>ext_value7</em>カラムの値を設定します。 
   *
   * @@param p_extValue7 <em>ext_value7</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue7( long p_extValue7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value7 = new Long(p_extValue7);
    ext_value7_is_set = true;
    ext_value7_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value7</em>カラムに値を設定します。 
   */
  public final void setExtValue7( Long p_extValue7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value7 = p_extValue7;
    ext_value7_is_set = true;
    ext_value7_is_modified = true;
  }


  /** 
   * <em>ext_value8</em>カラムの値を設定します。 
   *
   * @@param p_extValue8 <em>ext_value8</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue8( long p_extValue8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value8 = new Long(p_extValue8);
    ext_value8_is_set = true;
    ext_value8_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value8</em>カラムに値を設定します。 
   */
  public final void setExtValue8( Long p_extValue8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value8 = p_extValue8;
    ext_value8_is_set = true;
    ext_value8_is_modified = true;
  }


  /** 
   * <em>ext_value9</em>カラムの値を設定します。 
   *
   * @@param p_extValue9 <em>ext_value9</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue9( long p_extValue9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value9 = new Long(p_extValue9);
    ext_value9_is_set = true;
    ext_value9_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value9</em>カラムに値を設定します。 
   */
  public final void setExtValue9( Long p_extValue9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value9 = p_extValue9;
    ext_value9_is_set = true;
    ext_value9_is_modified = true;
  }


  /** 
   * <em>ext_value10</em>カラムの値を設定します。 
   *
   * @@param p_extValue10 <em>ext_value10</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue10( long p_extValue10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value10 = new Long(p_extValue10);
    ext_value10_is_set = true;
    ext_value10_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value10</em>カラムに値を設定します。 
   */
  public final void setExtValue10( Long p_extValue10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value10 = p_extValue10;
    ext_value10_is_set = true;
    ext_value10_is_modified = true;
  }


  /** 
   * <em>ext_value11</em>カラムの値を設定します。 
   *
   * @@param p_extValue11 <em>ext_value11</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue11( long p_extValue11 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value11 = new Long(p_extValue11);
    ext_value11_is_set = true;
    ext_value11_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value11</em>カラムに値を設定します。 
   */
  public final void setExtValue11( Long p_extValue11 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value11 = p_extValue11;
    ext_value11_is_set = true;
    ext_value11_is_modified = true;
  }


  /** 
   * <em>ext_value12</em>カラムの値を設定します。 
   *
   * @@param p_extValue12 <em>ext_value12</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue12( long p_extValue12 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value12 = new Long(p_extValue12);
    ext_value12_is_set = true;
    ext_value12_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value12</em>カラムに値を設定します。 
   */
  public final void setExtValue12( Long p_extValue12 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value12 = p_extValue12;
    ext_value12_is_set = true;
    ext_value12_is_modified = true;
  }


  /** 
   * <em>ext_value13</em>カラムの値を設定します。 
   *
   * @@param p_extValue13 <em>ext_value13</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue13( long p_extValue13 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value13 = new Long(p_extValue13);
    ext_value13_is_set = true;
    ext_value13_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value13</em>カラムに値を設定します。 
   */
  public final void setExtValue13( Long p_extValue13 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value13 = p_extValue13;
    ext_value13_is_set = true;
    ext_value13_is_modified = true;
  }


  /** 
   * <em>ext_value14</em>カラムの値を設定します。 
   *
   * @@param p_extValue14 <em>ext_value14</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue14( long p_extValue14 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value14 = new Long(p_extValue14);
    ext_value14_is_set = true;
    ext_value14_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value14</em>カラムに値を設定します。 
   */
  public final void setExtValue14( Long p_extValue14 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value14 = p_extValue14;
    ext_value14_is_set = true;
    ext_value14_is_modified = true;
  }


  /** 
   * <em>ext_value15</em>カラムの値を設定します。 
   *
   * @@param p_extValue15 <em>ext_value15</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue15( long p_extValue15 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value15 = new Long(p_extValue15);
    ext_value15_is_set = true;
    ext_value15_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value15</em>カラムに値を設定します。 
   */
  public final void setExtValue15( Long p_extValue15 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value15 = p_extValue15;
    ext_value15_is_set = true;
    ext_value15_is_modified = true;
  }


  /** 
   * <em>ext_value16</em>カラムの値を設定します。 
   *
   * @@param p_extValue16 <em>ext_value16</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue16( long p_extValue16 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value16 = new Long(p_extValue16);
    ext_value16_is_set = true;
    ext_value16_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value16</em>カラムに値を設定します。 
   */
  public final void setExtValue16( Long p_extValue16 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value16 = p_extValue16;
    ext_value16_is_set = true;
    ext_value16_is_modified = true;
  }


  /** 
   * <em>ext_value17</em>カラムの値を設定します。 
   *
   * @@param p_extValue17 <em>ext_value17</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue17( long p_extValue17 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value17 = new Long(p_extValue17);
    ext_value17_is_set = true;
    ext_value17_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value17</em>カラムに値を設定します。 
   */
  public final void setExtValue17( Long p_extValue17 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value17 = p_extValue17;
    ext_value17_is_set = true;
    ext_value17_is_modified = true;
  }


  /** 
   * <em>ext_value18</em>カラムの値を設定します。 
   *
   * @@param p_extValue18 <em>ext_value18</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue18( long p_extValue18 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value18 = new Long(p_extValue18);
    ext_value18_is_set = true;
    ext_value18_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value18</em>カラムに値を設定します。 
   */
  public final void setExtValue18( Long p_extValue18 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value18 = p_extValue18;
    ext_value18_is_set = true;
    ext_value18_is_modified = true;
  }


  /** 
   * <em>ext_value19</em>カラムの値を設定します。 
   *
   * @@param p_extValue19 <em>ext_value19</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue19( long p_extValue19 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value19 = new Long(p_extValue19);
    ext_value19_is_set = true;
    ext_value19_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value19</em>カラムに値を設定します。 
   */
  public final void setExtValue19( Long p_extValue19 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value19 = p_extValue19;
    ext_value19_is_set = true;
    ext_value19_is_modified = true;
  }


  /** 
   * <em>ext_value20</em>カラムの値を設定します。 
   *
   * @@param p_extValue20 <em>ext_value20</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue20( long p_extValue20 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value20 = new Long(p_extValue20);
    ext_value20_is_set = true;
    ext_value20_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value20</em>カラムに値を設定します。 
   */
  public final void setExtValue20( Long p_extValue20 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value20 = p_extValue20;
    ext_value20_is_set = true;
    ext_value20_is_modified = true;
  }


  /** 
   * <em>ext_value21</em>カラムの値を設定します。 
   *
   * @@param p_extValue21 <em>ext_value21</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue21( long p_extValue21 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value21 = new Long(p_extValue21);
    ext_value21_is_set = true;
    ext_value21_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value21</em>カラムに値を設定します。 
   */
  public final void setExtValue21( Long p_extValue21 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value21 = p_extValue21;
    ext_value21_is_set = true;
    ext_value21_is_modified = true;
  }


  /** 
   * <em>ext_value22</em>カラムの値を設定します。 
   *
   * @@param p_extValue22 <em>ext_value22</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue22( long p_extValue22 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value22 = new Long(p_extValue22);
    ext_value22_is_set = true;
    ext_value22_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value22</em>カラムに値を設定します。 
   */
  public final void setExtValue22( Long p_extValue22 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value22 = p_extValue22;
    ext_value22_is_set = true;
    ext_value22_is_modified = true;
  }


  /** 
   * <em>ext_value23</em>カラムの値を設定します。 
   *
   * @@param p_extValue23 <em>ext_value23</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue23( long p_extValue23 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value23 = new Long(p_extValue23);
    ext_value23_is_set = true;
    ext_value23_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value23</em>カラムに値を設定します。 
   */
  public final void setExtValue23( Long p_extValue23 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value23 = p_extValue23;
    ext_value23_is_set = true;
    ext_value23_is_modified = true;
  }


  /** 
   * <em>ext_value24</em>カラムの値を設定します。 
   *
   * @@param p_extValue24 <em>ext_value24</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue24( long p_extValue24 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value24 = new Long(p_extValue24);
    ext_value24_is_set = true;
    ext_value24_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value24</em>カラムに値を設定します。 
   */
  public final void setExtValue24( Long p_extValue24 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value24 = p_extValue24;
    ext_value24_is_set = true;
    ext_value24_is_modified = true;
  }


  /** 
   * <em>ext_value25</em>カラムの値を設定します。 
   *
   * @@param p_extValue25 <em>ext_value25</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue25( long p_extValue25 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value25 = new Long(p_extValue25);
    ext_value25_is_set = true;
    ext_value25_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value25</em>カラムに値を設定します。 
   */
  public final void setExtValue25( Long p_extValue25 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value25 = p_extValue25;
    ext_value25_is_set = true;
    ext_value25_is_modified = true;
  }


  /** 
   * <em>ext_value26</em>カラムの値を設定します。 
   *
   * @@param p_extValue26 <em>ext_value26</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue26( long p_extValue26 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value26 = new Long(p_extValue26);
    ext_value26_is_set = true;
    ext_value26_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value26</em>カラムに値を設定します。 
   */
  public final void setExtValue26( Long p_extValue26 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value26 = p_extValue26;
    ext_value26_is_set = true;
    ext_value26_is_modified = true;
  }


  /** 
   * <em>ext_value27</em>カラムの値を設定します。 
   *
   * @@param p_extValue27 <em>ext_value27</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue27( long p_extValue27 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value27 = new Long(p_extValue27);
    ext_value27_is_set = true;
    ext_value27_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value27</em>カラムに値を設定します。 
   */
  public final void setExtValue27( Long p_extValue27 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value27 = p_extValue27;
    ext_value27_is_set = true;
    ext_value27_is_modified = true;
  }


  /** 
   * <em>ext_value28</em>カラムの値を設定します。 
   *
   * @@param p_extValue28 <em>ext_value28</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue28( long p_extValue28 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value28 = new Long(p_extValue28);
    ext_value28_is_set = true;
    ext_value28_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value28</em>カラムに値を設定します。 
   */
  public final void setExtValue28( Long p_extValue28 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value28 = p_extValue28;
    ext_value28_is_set = true;
    ext_value28_is_modified = true;
  }


  /** 
   * <em>ext_value29</em>カラムの値を設定します。 
   *
   * @@param p_extValue29 <em>ext_value29</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue29( long p_extValue29 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value29 = new Long(p_extValue29);
    ext_value29_is_set = true;
    ext_value29_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value29</em>カラムに値を設定します。 
   */
  public final void setExtValue29( Long p_extValue29 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value29 = p_extValue29;
    ext_value29_is_set = true;
    ext_value29_is_modified = true;
  }


  /** 
   * <em>ext_value30</em>カラムの値を設定します。 
   *
   * @@param p_extValue30 <em>ext_value30</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExtValue30( long p_extValue30 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value30 = new Long(p_extValue30);
    ext_value30_is_set = true;
    ext_value30_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ext_value30</em>カラムに値を設定します。 
   */
  public final void setExtValue30( Long p_extValue30 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ext_value30 = p_extValue30;
    ext_value30_is_set = true;
    ext_value30_is_modified = true;
  }


  /** 
   * <em>ext_note1</em>カラムの値を設定します。 
   *
   * @@param p_extNote1 <em>ext_note1</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setExtNote1( String p_extNote1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_note1 = p_extNote1;
    ext_note1_is_set = true;
    ext_note1_is_modified = true;
  }


  /** 
   * <em>ext_note2</em>カラムの値を設定します。 
   *
   * @@param p_extNote2 <em>ext_note2</em>カラムの値をあらわす1000桁以下のString値 
   */
  public final void setExtNote2( String p_extNote2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_note2 = p_extNote2;
    ext_note2_is_set = true;
    ext_note2_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>fund_code</em>カラムの値を設定します。 
   *
   * @@param p_fundCode <em>fund_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setFundCode( String p_fundCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_code = p_fundCode;
    fund_code_is_set = true;
    fund_code_is_modified = true;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarTraderCode <em>sonar_trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setSonarTraderCode( String p_sonarTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_trader_code = p_sonarTraderCode;
    sonar_trader_code_is_set = true;
    sonar_trader_code_is_modified = true;
  }


  /** 
   * <em>status</em>カラムの値を設定します。 
   *
   * @@param p_status <em>status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
  }


  /** 
   * <em>error_reason_code</em>カラムの値を設定します。 
   *
   * @@param p_errorReasonCode <em>error_reason_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setErrorReasonCode( String p_errorReasonCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_reason_code = p_errorReasonCode;
    error_reason_code_is_set = true;
    error_reason_code_is_modified = true;
  }


  /** 
   * <em>order_request_number</em>カラムの値を設定します。 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
    order_request_number_is_modified = true;
  }


  /** 
   * <em>request_code</em>カラムの値を設定します。 
   *
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setRequestCode( String p_requestCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_code = p_requestCode;
    request_code_is_set = true;
    request_code_is_modified = true;
  }


  /** 
   * <em>send_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_sendTimestamp <em>send_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSendTimestamp( java.sql.Timestamp p_sendTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_timestamp = p_sendTimestamp;
    send_timestamp_is_set = true;
    send_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>send_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSendTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    send_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    send_timestamp_is_set = true;
    send_timestamp_is_modified = true;
  }


  /** 
   * <em>receipt_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_receiptTimestamp <em>receipt_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setReceiptTimestamp( java.sql.Timestamp p_receiptTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_timestamp = p_receiptTimestamp;
    receipt_timestamp_is_set = true;
    receipt_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>receipt_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setReceiptTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    receipt_timestamp_is_set = true;
    receipt_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("account_name") ) {
                    return this.account_name;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("email_address") ) {
                    return this.email_address;
                }
                else if ( name.equals("ext_div1") ) {
                    return this.ext_div1;
                }
                else if ( name.equals("ext_div2") ) {
                    return this.ext_div2;
                }
                else if ( name.equals("ext_div3") ) {
                    return this.ext_div3;
                }
                else if ( name.equals("ext_div4") ) {
                    return this.ext_div4;
                }
                else if ( name.equals("ext_div5") ) {
                    return this.ext_div5;
                }
                else if ( name.equals("ext_div6") ) {
                    return this.ext_div6;
                }
                else if ( name.equals("ext_div7") ) {
                    return this.ext_div7;
                }
                else if ( name.equals("ext_div8") ) {
                    return this.ext_div8;
                }
                else if ( name.equals("ext_div9") ) {
                    return this.ext_div9;
                }
                else if ( name.equals("ext_div10") ) {
                    return this.ext_div10;
                }
                else if ( name.equals("ext_div11") ) {
                    return this.ext_div11;
                }
                else if ( name.equals("ext_div12") ) {
                    return this.ext_div12;
                }
                else if ( name.equals("ext_div13") ) {
                    return this.ext_div13;
                }
                else if ( name.equals("ext_div14") ) {
                    return this.ext_div14;
                }
                else if ( name.equals("ext_div15") ) {
                    return this.ext_div15;
                }
                else if ( name.equals("ext_div16") ) {
                    return this.ext_div16;
                }
                else if ( name.equals("ext_div17") ) {
                    return this.ext_div17;
                }
                else if ( name.equals("ext_div18") ) {
                    return this.ext_div18;
                }
                else if ( name.equals("ext_div19") ) {
                    return this.ext_div19;
                }
                else if ( name.equals("ext_div20") ) {
                    return this.ext_div20;
                }
                else if ( name.equals("ext_div21") ) {
                    return this.ext_div21;
                }
                else if ( name.equals("ext_div22") ) {
                    return this.ext_div22;
                }
                else if ( name.equals("ext_div23") ) {
                    return this.ext_div23;
                }
                else if ( name.equals("ext_div24") ) {
                    return this.ext_div24;
                }
                else if ( name.equals("ext_div25") ) {
                    return this.ext_div25;
                }
                else if ( name.equals("ext_div26") ) {
                    return this.ext_div26;
                }
                else if ( name.equals("ext_div27") ) {
                    return this.ext_div27;
                }
                else if ( name.equals("ext_div28") ) {
                    return this.ext_div28;
                }
                else if ( name.equals("ext_div29") ) {
                    return this.ext_div29;
                }
                else if ( name.equals("ext_div30") ) {
                    return this.ext_div30;
                }
                else if ( name.equals("ext_div31") ) {
                    return this.ext_div31;
                }
                else if ( name.equals("ext_div32") ) {
                    return this.ext_div32;
                }
                else if ( name.equals("ext_div33") ) {
                    return this.ext_div33;
                }
                else if ( name.equals("ext_div34") ) {
                    return this.ext_div34;
                }
                else if ( name.equals("ext_div35") ) {
                    return this.ext_div35;
                }
                else if ( name.equals("ext_div36") ) {
                    return this.ext_div36;
                }
                else if ( name.equals("ext_div37") ) {
                    return this.ext_div37;
                }
                else if ( name.equals("ext_div38") ) {
                    return this.ext_div38;
                }
                else if ( name.equals("ext_div39") ) {
                    return this.ext_div39;
                }
                else if ( name.equals("ext_div40") ) {
                    return this.ext_div40;
                }
                else if ( name.equals("ext_code1") ) {
                    return this.ext_code1;
                }
                else if ( name.equals("ext_code2") ) {
                    return this.ext_code2;
                }
                else if ( name.equals("ext_code3") ) {
                    return this.ext_code3;
                }
                else if ( name.equals("ext_code4") ) {
                    return this.ext_code4;
                }
                else if ( name.equals("ext_code5") ) {
                    return this.ext_code5;
                }
                else if ( name.equals("ext_code6") ) {
                    return this.ext_code6;
                }
                else if ( name.equals("ext_code7") ) {
                    return this.ext_code7;
                }
                else if ( name.equals("ext_code8") ) {
                    return this.ext_code8;
                }
                else if ( name.equals("ext_code9") ) {
                    return this.ext_code9;
                }
                else if ( name.equals("ext_code10") ) {
                    return this.ext_code10;
                }
                else if ( name.equals("ext_text1") ) {
                    return this.ext_text1;
                }
                else if ( name.equals("ext_text2") ) {
                    return this.ext_text2;
                }
                else if ( name.equals("ext_text3") ) {
                    return this.ext_text3;
                }
                else if ( name.equals("ext_text4") ) {
                    return this.ext_text4;
                }
                else if ( name.equals("ext_text5") ) {
                    return this.ext_text5;
                }
                else if ( name.equals("ext_text6") ) {
                    return this.ext_text6;
                }
                else if ( name.equals("ext_text7") ) {
                    return this.ext_text7;
                }
                else if ( name.equals("ext_text8") ) {
                    return this.ext_text8;
                }
                else if ( name.equals("ext_text9") ) {
                    return this.ext_text9;
                }
                else if ( name.equals("ext_text10") ) {
                    return this.ext_text10;
                }
                else if ( name.equals("ext_text11") ) {
                    return this.ext_text11;
                }
                else if ( name.equals("ext_text12") ) {
                    return this.ext_text12;
                }
                else if ( name.equals("ext_text13") ) {
                    return this.ext_text13;
                }
                else if ( name.equals("ext_text14") ) {
                    return this.ext_text14;
                }
                else if ( name.equals("ext_text15") ) {
                    return this.ext_text15;
                }
                else if ( name.equals("ext_text16") ) {
                    return this.ext_text16;
                }
                else if ( name.equals("ext_text17") ) {
                    return this.ext_text17;
                }
                else if ( name.equals("ext_text18") ) {
                    return this.ext_text18;
                }
                else if ( name.equals("ext_text19") ) {
                    return this.ext_text19;
                }
                else if ( name.equals("ext_text20") ) {
                    return this.ext_text20;
                }
                else if ( name.equals("ext_text21") ) {
                    return this.ext_text21;
                }
                else if ( name.equals("ext_text22") ) {
                    return this.ext_text22;
                }
                else if ( name.equals("ext_text23") ) {
                    return this.ext_text23;
                }
                else if ( name.equals("ext_text24") ) {
                    return this.ext_text24;
                }
                else if ( name.equals("ext_text25") ) {
                    return this.ext_text25;
                }
                else if ( name.equals("ext_text26") ) {
                    return this.ext_text26;
                }
                else if ( name.equals("ext_text27") ) {
                    return this.ext_text27;
                }
                else if ( name.equals("ext_text28") ) {
                    return this.ext_text28;
                }
                else if ( name.equals("ext_text29") ) {
                    return this.ext_text29;
                }
                else if ( name.equals("ext_text30") ) {
                    return this.ext_text30;
                }
                else if ( name.equals("ext_text31") ) {
                    return this.ext_text31;
                }
                else if ( name.equals("ext_text32") ) {
                    return this.ext_text32;
                }
                else if ( name.equals("ext_text33") ) {
                    return this.ext_text33;
                }
                else if ( name.equals("ext_text34") ) {
                    return this.ext_text34;
                }
                else if ( name.equals("ext_text35") ) {
                    return this.ext_text35;
                }
                else if ( name.equals("ext_text36") ) {
                    return this.ext_text36;
                }
                else if ( name.equals("ext_text37") ) {
                    return this.ext_text37;
                }
                else if ( name.equals("ext_text38") ) {
                    return this.ext_text38;
                }
                else if ( name.equals("ext_text39") ) {
                    return this.ext_text39;
                }
                else if ( name.equals("ext_text40") ) {
                    return this.ext_text40;
                }
                else if ( name.equals("ext_value1") ) {
                    return this.ext_value1;
                }
                else if ( name.equals("ext_value2") ) {
                    return this.ext_value2;
                }
                else if ( name.equals("ext_value3") ) {
                    return this.ext_value3;
                }
                else if ( name.equals("ext_value4") ) {
                    return this.ext_value4;
                }
                else if ( name.equals("ext_value5") ) {
                    return this.ext_value5;
                }
                else if ( name.equals("ext_value6") ) {
                    return this.ext_value6;
                }
                else if ( name.equals("ext_value7") ) {
                    return this.ext_value7;
                }
                else if ( name.equals("ext_value8") ) {
                    return this.ext_value8;
                }
                else if ( name.equals("ext_value9") ) {
                    return this.ext_value9;
                }
                else if ( name.equals("ext_value10") ) {
                    return this.ext_value10;
                }
                else if ( name.equals("ext_value11") ) {
                    return this.ext_value11;
                }
                else if ( name.equals("ext_value12") ) {
                    return this.ext_value12;
                }
                else if ( name.equals("ext_value13") ) {
                    return this.ext_value13;
                }
                else if ( name.equals("ext_value14") ) {
                    return this.ext_value14;
                }
                else if ( name.equals("ext_value15") ) {
                    return this.ext_value15;
                }
                else if ( name.equals("ext_value16") ) {
                    return this.ext_value16;
                }
                else if ( name.equals("ext_value17") ) {
                    return this.ext_value17;
                }
                else if ( name.equals("ext_value18") ) {
                    return this.ext_value18;
                }
                else if ( name.equals("ext_value19") ) {
                    return this.ext_value19;
                }
                else if ( name.equals("ext_value20") ) {
                    return this.ext_value20;
                }
                else if ( name.equals("ext_value21") ) {
                    return this.ext_value21;
                }
                else if ( name.equals("ext_value22") ) {
                    return this.ext_value22;
                }
                else if ( name.equals("ext_value23") ) {
                    return this.ext_value23;
                }
                else if ( name.equals("ext_value24") ) {
                    return this.ext_value24;
                }
                else if ( name.equals("ext_value25") ) {
                    return this.ext_value25;
                }
                else if ( name.equals("ext_value26") ) {
                    return this.ext_value26;
                }
                else if ( name.equals("ext_value27") ) {
                    return this.ext_value27;
                }
                else if ( name.equals("ext_value28") ) {
                    return this.ext_value28;
                }
                else if ( name.equals("ext_value29") ) {
                    return this.ext_value29;
                }
                else if ( name.equals("ext_value30") ) {
                    return this.ext_value30;
                }
                else if ( name.equals("ext_note1") ) {
                    return this.ext_note1;
                }
                else if ( name.equals("ext_note2") ) {
                    return this.ext_note2;
                }
                else if ( name.equals("error_reason_code") ) {
                    return this.error_reason_code;
                }
                break;
            case 'f':
                if ( name.equals("fund_code") ) {
                    return this.fund_code;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("inform_div") ) {
                    return this.inform_div;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'r':
                if ( name.equals("request_number") ) {
                    return this.request_number;
                }
                else if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("receipt_timestamp") ) {
                    return this.receipt_timestamp;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                else if ( name.equals("send_timestamp") ) {
                    return this.send_timestamp;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("account_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_name' must be String: '"+value+"' is not." );
                    this.account_name = (String) value;
                    if (this.account_name_is_set)
                        this.account_name_is_modified = true;
                    this.account_name_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("email_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'email_address' must be String: '"+value+"' is not." );
                    this.email_address = (String) value;
                    if (this.email_address_is_set)
                        this.email_address_is_modified = true;
                    this.email_address_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div1' must be String: '"+value+"' is not." );
                    this.ext_div1 = (String) value;
                    if (this.ext_div1_is_set)
                        this.ext_div1_is_modified = true;
                    this.ext_div1_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div2' must be String: '"+value+"' is not." );
                    this.ext_div2 = (String) value;
                    if (this.ext_div2_is_set)
                        this.ext_div2_is_modified = true;
                    this.ext_div2_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div3' must be String: '"+value+"' is not." );
                    this.ext_div3 = (String) value;
                    if (this.ext_div3_is_set)
                        this.ext_div3_is_modified = true;
                    this.ext_div3_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div4' must be String: '"+value+"' is not." );
                    this.ext_div4 = (String) value;
                    if (this.ext_div4_is_set)
                        this.ext_div4_is_modified = true;
                    this.ext_div4_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div5' must be String: '"+value+"' is not." );
                    this.ext_div5 = (String) value;
                    if (this.ext_div5_is_set)
                        this.ext_div5_is_modified = true;
                    this.ext_div5_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div6") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div6' must be String: '"+value+"' is not." );
                    this.ext_div6 = (String) value;
                    if (this.ext_div6_is_set)
                        this.ext_div6_is_modified = true;
                    this.ext_div6_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div7") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div7' must be String: '"+value+"' is not." );
                    this.ext_div7 = (String) value;
                    if (this.ext_div7_is_set)
                        this.ext_div7_is_modified = true;
                    this.ext_div7_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div8") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div8' must be String: '"+value+"' is not." );
                    this.ext_div8 = (String) value;
                    if (this.ext_div8_is_set)
                        this.ext_div8_is_modified = true;
                    this.ext_div8_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div9") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div9' must be String: '"+value+"' is not." );
                    this.ext_div9 = (String) value;
                    if (this.ext_div9_is_set)
                        this.ext_div9_is_modified = true;
                    this.ext_div9_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div10") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div10' must be String: '"+value+"' is not." );
                    this.ext_div10 = (String) value;
                    if (this.ext_div10_is_set)
                        this.ext_div10_is_modified = true;
                    this.ext_div10_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div11") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div11' must be String: '"+value+"' is not." );
                    this.ext_div11 = (String) value;
                    if (this.ext_div11_is_set)
                        this.ext_div11_is_modified = true;
                    this.ext_div11_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div12") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div12' must be String: '"+value+"' is not." );
                    this.ext_div12 = (String) value;
                    if (this.ext_div12_is_set)
                        this.ext_div12_is_modified = true;
                    this.ext_div12_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div13") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div13' must be String: '"+value+"' is not." );
                    this.ext_div13 = (String) value;
                    if (this.ext_div13_is_set)
                        this.ext_div13_is_modified = true;
                    this.ext_div13_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div14") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div14' must be String: '"+value+"' is not." );
                    this.ext_div14 = (String) value;
                    if (this.ext_div14_is_set)
                        this.ext_div14_is_modified = true;
                    this.ext_div14_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div15") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div15' must be String: '"+value+"' is not." );
                    this.ext_div15 = (String) value;
                    if (this.ext_div15_is_set)
                        this.ext_div15_is_modified = true;
                    this.ext_div15_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div16") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div16' must be String: '"+value+"' is not." );
                    this.ext_div16 = (String) value;
                    if (this.ext_div16_is_set)
                        this.ext_div16_is_modified = true;
                    this.ext_div16_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div17") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div17' must be String: '"+value+"' is not." );
                    this.ext_div17 = (String) value;
                    if (this.ext_div17_is_set)
                        this.ext_div17_is_modified = true;
                    this.ext_div17_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div18") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div18' must be String: '"+value+"' is not." );
                    this.ext_div18 = (String) value;
                    if (this.ext_div18_is_set)
                        this.ext_div18_is_modified = true;
                    this.ext_div18_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div19") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div19' must be String: '"+value+"' is not." );
                    this.ext_div19 = (String) value;
                    if (this.ext_div19_is_set)
                        this.ext_div19_is_modified = true;
                    this.ext_div19_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div20") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div20' must be String: '"+value+"' is not." );
                    this.ext_div20 = (String) value;
                    if (this.ext_div20_is_set)
                        this.ext_div20_is_modified = true;
                    this.ext_div20_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div21") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div21' must be String: '"+value+"' is not." );
                    this.ext_div21 = (String) value;
                    if (this.ext_div21_is_set)
                        this.ext_div21_is_modified = true;
                    this.ext_div21_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div22") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div22' must be String: '"+value+"' is not." );
                    this.ext_div22 = (String) value;
                    if (this.ext_div22_is_set)
                        this.ext_div22_is_modified = true;
                    this.ext_div22_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div23") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div23' must be String: '"+value+"' is not." );
                    this.ext_div23 = (String) value;
                    if (this.ext_div23_is_set)
                        this.ext_div23_is_modified = true;
                    this.ext_div23_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div24") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div24' must be String: '"+value+"' is not." );
                    this.ext_div24 = (String) value;
                    if (this.ext_div24_is_set)
                        this.ext_div24_is_modified = true;
                    this.ext_div24_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div25") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div25' must be String: '"+value+"' is not." );
                    this.ext_div25 = (String) value;
                    if (this.ext_div25_is_set)
                        this.ext_div25_is_modified = true;
                    this.ext_div25_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div26") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div26' must be String: '"+value+"' is not." );
                    this.ext_div26 = (String) value;
                    if (this.ext_div26_is_set)
                        this.ext_div26_is_modified = true;
                    this.ext_div26_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div27") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div27' must be String: '"+value+"' is not." );
                    this.ext_div27 = (String) value;
                    if (this.ext_div27_is_set)
                        this.ext_div27_is_modified = true;
                    this.ext_div27_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div28") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div28' must be String: '"+value+"' is not." );
                    this.ext_div28 = (String) value;
                    if (this.ext_div28_is_set)
                        this.ext_div28_is_modified = true;
                    this.ext_div28_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div29") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div29' must be String: '"+value+"' is not." );
                    this.ext_div29 = (String) value;
                    if (this.ext_div29_is_set)
                        this.ext_div29_is_modified = true;
                    this.ext_div29_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div30") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div30' must be String: '"+value+"' is not." );
                    this.ext_div30 = (String) value;
                    if (this.ext_div30_is_set)
                        this.ext_div30_is_modified = true;
                    this.ext_div30_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div31") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div31' must be String: '"+value+"' is not." );
                    this.ext_div31 = (String) value;
                    if (this.ext_div31_is_set)
                        this.ext_div31_is_modified = true;
                    this.ext_div31_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div32") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div32' must be String: '"+value+"' is not." );
                    this.ext_div32 = (String) value;
                    if (this.ext_div32_is_set)
                        this.ext_div32_is_modified = true;
                    this.ext_div32_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div33") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div33' must be String: '"+value+"' is not." );
                    this.ext_div33 = (String) value;
                    if (this.ext_div33_is_set)
                        this.ext_div33_is_modified = true;
                    this.ext_div33_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div34") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div34' must be String: '"+value+"' is not." );
                    this.ext_div34 = (String) value;
                    if (this.ext_div34_is_set)
                        this.ext_div34_is_modified = true;
                    this.ext_div34_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div35") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div35' must be String: '"+value+"' is not." );
                    this.ext_div35 = (String) value;
                    if (this.ext_div35_is_set)
                        this.ext_div35_is_modified = true;
                    this.ext_div35_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div36") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div36' must be String: '"+value+"' is not." );
                    this.ext_div36 = (String) value;
                    if (this.ext_div36_is_set)
                        this.ext_div36_is_modified = true;
                    this.ext_div36_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div37") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div37' must be String: '"+value+"' is not." );
                    this.ext_div37 = (String) value;
                    if (this.ext_div37_is_set)
                        this.ext_div37_is_modified = true;
                    this.ext_div37_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div38") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div38' must be String: '"+value+"' is not." );
                    this.ext_div38 = (String) value;
                    if (this.ext_div38_is_set)
                        this.ext_div38_is_modified = true;
                    this.ext_div38_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div39") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div39' must be String: '"+value+"' is not." );
                    this.ext_div39 = (String) value;
                    if (this.ext_div39_is_set)
                        this.ext_div39_is_modified = true;
                    this.ext_div39_is_set = true;
                    return;
                }
                else if ( name.equals("ext_div40") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_div40' must be String: '"+value+"' is not." );
                    this.ext_div40 = (String) value;
                    if (this.ext_div40_is_set)
                        this.ext_div40_is_modified = true;
                    this.ext_div40_is_set = true;
                    return;
                }
                else if ( name.equals("ext_code1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_code1' must be String: '"+value+"' is not." );
                    this.ext_code1 = (String) value;
                    if (this.ext_code1_is_set)
                        this.ext_code1_is_modified = true;
                    this.ext_code1_is_set = true;
                    return;
                }
                else if ( name.equals("ext_code2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_code2' must be String: '"+value+"' is not." );
                    this.ext_code2 = (String) value;
                    if (this.ext_code2_is_set)
                        this.ext_code2_is_modified = true;
                    this.ext_code2_is_set = true;
                    return;
                }
                else if ( name.equals("ext_code3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_code3' must be String: '"+value+"' is not." );
                    this.ext_code3 = (String) value;
                    if (this.ext_code3_is_set)
                        this.ext_code3_is_modified = true;
                    this.ext_code3_is_set = true;
                    return;
                }
                else if ( name.equals("ext_code4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_code4' must be String: '"+value+"' is not." );
                    this.ext_code4 = (String) value;
                    if (this.ext_code4_is_set)
                        this.ext_code4_is_modified = true;
                    this.ext_code4_is_set = true;
                    return;
                }
                else if ( name.equals("ext_code5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_code5' must be String: '"+value+"' is not." );
                    this.ext_code5 = (String) value;
                    if (this.ext_code5_is_set)
                        this.ext_code5_is_modified = true;
                    this.ext_code5_is_set = true;
                    return;
                }
                else if ( name.equals("ext_code6") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_code6' must be String: '"+value+"' is not." );
                    this.ext_code6 = (String) value;
                    if (this.ext_code6_is_set)
                        this.ext_code6_is_modified = true;
                    this.ext_code6_is_set = true;
                    return;
                }
                else if ( name.equals("ext_code7") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_code7' must be String: '"+value+"' is not." );
                    this.ext_code7 = (String) value;
                    if (this.ext_code7_is_set)
                        this.ext_code7_is_modified = true;
                    this.ext_code7_is_set = true;
                    return;
                }
                else if ( name.equals("ext_code8") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_code8' must be String: '"+value+"' is not." );
                    this.ext_code8 = (String) value;
                    if (this.ext_code8_is_set)
                        this.ext_code8_is_modified = true;
                    this.ext_code8_is_set = true;
                    return;
                }
                else if ( name.equals("ext_code9") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_code9' must be String: '"+value+"' is not." );
                    this.ext_code9 = (String) value;
                    if (this.ext_code9_is_set)
                        this.ext_code9_is_modified = true;
                    this.ext_code9_is_set = true;
                    return;
                }
                else if ( name.equals("ext_code10") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_code10' must be String: '"+value+"' is not." );
                    this.ext_code10 = (String) value;
                    if (this.ext_code10_is_set)
                        this.ext_code10_is_modified = true;
                    this.ext_code10_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text1' must be String: '"+value+"' is not." );
                    this.ext_text1 = (String) value;
                    if (this.ext_text1_is_set)
                        this.ext_text1_is_modified = true;
                    this.ext_text1_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text2' must be String: '"+value+"' is not." );
                    this.ext_text2 = (String) value;
                    if (this.ext_text2_is_set)
                        this.ext_text2_is_modified = true;
                    this.ext_text2_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text3' must be String: '"+value+"' is not." );
                    this.ext_text3 = (String) value;
                    if (this.ext_text3_is_set)
                        this.ext_text3_is_modified = true;
                    this.ext_text3_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text4' must be String: '"+value+"' is not." );
                    this.ext_text4 = (String) value;
                    if (this.ext_text4_is_set)
                        this.ext_text4_is_modified = true;
                    this.ext_text4_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text5' must be String: '"+value+"' is not." );
                    this.ext_text5 = (String) value;
                    if (this.ext_text5_is_set)
                        this.ext_text5_is_modified = true;
                    this.ext_text5_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text6") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text6' must be String: '"+value+"' is not." );
                    this.ext_text6 = (String) value;
                    if (this.ext_text6_is_set)
                        this.ext_text6_is_modified = true;
                    this.ext_text6_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text7") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text7' must be String: '"+value+"' is not." );
                    this.ext_text7 = (String) value;
                    if (this.ext_text7_is_set)
                        this.ext_text7_is_modified = true;
                    this.ext_text7_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text8") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text8' must be String: '"+value+"' is not." );
                    this.ext_text8 = (String) value;
                    if (this.ext_text8_is_set)
                        this.ext_text8_is_modified = true;
                    this.ext_text8_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text9") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text9' must be String: '"+value+"' is not." );
                    this.ext_text9 = (String) value;
                    if (this.ext_text9_is_set)
                        this.ext_text9_is_modified = true;
                    this.ext_text9_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text10") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text10' must be String: '"+value+"' is not." );
                    this.ext_text10 = (String) value;
                    if (this.ext_text10_is_set)
                        this.ext_text10_is_modified = true;
                    this.ext_text10_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text11") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text11' must be String: '"+value+"' is not." );
                    this.ext_text11 = (String) value;
                    if (this.ext_text11_is_set)
                        this.ext_text11_is_modified = true;
                    this.ext_text11_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text12") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text12' must be String: '"+value+"' is not." );
                    this.ext_text12 = (String) value;
                    if (this.ext_text12_is_set)
                        this.ext_text12_is_modified = true;
                    this.ext_text12_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text13") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text13' must be String: '"+value+"' is not." );
                    this.ext_text13 = (String) value;
                    if (this.ext_text13_is_set)
                        this.ext_text13_is_modified = true;
                    this.ext_text13_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text14") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text14' must be String: '"+value+"' is not." );
                    this.ext_text14 = (String) value;
                    if (this.ext_text14_is_set)
                        this.ext_text14_is_modified = true;
                    this.ext_text14_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text15") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text15' must be String: '"+value+"' is not." );
                    this.ext_text15 = (String) value;
                    if (this.ext_text15_is_set)
                        this.ext_text15_is_modified = true;
                    this.ext_text15_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text16") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text16' must be String: '"+value+"' is not." );
                    this.ext_text16 = (String) value;
                    if (this.ext_text16_is_set)
                        this.ext_text16_is_modified = true;
                    this.ext_text16_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text17") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text17' must be String: '"+value+"' is not." );
                    this.ext_text17 = (String) value;
                    if (this.ext_text17_is_set)
                        this.ext_text17_is_modified = true;
                    this.ext_text17_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text18") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text18' must be String: '"+value+"' is not." );
                    this.ext_text18 = (String) value;
                    if (this.ext_text18_is_set)
                        this.ext_text18_is_modified = true;
                    this.ext_text18_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text19") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text19' must be String: '"+value+"' is not." );
                    this.ext_text19 = (String) value;
                    if (this.ext_text19_is_set)
                        this.ext_text19_is_modified = true;
                    this.ext_text19_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text20") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text20' must be String: '"+value+"' is not." );
                    this.ext_text20 = (String) value;
                    if (this.ext_text20_is_set)
                        this.ext_text20_is_modified = true;
                    this.ext_text20_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text21") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text21' must be String: '"+value+"' is not." );
                    this.ext_text21 = (String) value;
                    if (this.ext_text21_is_set)
                        this.ext_text21_is_modified = true;
                    this.ext_text21_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text22") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text22' must be String: '"+value+"' is not." );
                    this.ext_text22 = (String) value;
                    if (this.ext_text22_is_set)
                        this.ext_text22_is_modified = true;
                    this.ext_text22_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text23") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text23' must be String: '"+value+"' is not." );
                    this.ext_text23 = (String) value;
                    if (this.ext_text23_is_set)
                        this.ext_text23_is_modified = true;
                    this.ext_text23_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text24") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text24' must be String: '"+value+"' is not." );
                    this.ext_text24 = (String) value;
                    if (this.ext_text24_is_set)
                        this.ext_text24_is_modified = true;
                    this.ext_text24_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text25") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text25' must be String: '"+value+"' is not." );
                    this.ext_text25 = (String) value;
                    if (this.ext_text25_is_set)
                        this.ext_text25_is_modified = true;
                    this.ext_text25_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text26") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text26' must be String: '"+value+"' is not." );
                    this.ext_text26 = (String) value;
                    if (this.ext_text26_is_set)
                        this.ext_text26_is_modified = true;
                    this.ext_text26_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text27") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text27' must be String: '"+value+"' is not." );
                    this.ext_text27 = (String) value;
                    if (this.ext_text27_is_set)
                        this.ext_text27_is_modified = true;
                    this.ext_text27_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text28") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text28' must be String: '"+value+"' is not." );
                    this.ext_text28 = (String) value;
                    if (this.ext_text28_is_set)
                        this.ext_text28_is_modified = true;
                    this.ext_text28_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text29") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text29' must be String: '"+value+"' is not." );
                    this.ext_text29 = (String) value;
                    if (this.ext_text29_is_set)
                        this.ext_text29_is_modified = true;
                    this.ext_text29_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text30") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text30' must be String: '"+value+"' is not." );
                    this.ext_text30 = (String) value;
                    if (this.ext_text30_is_set)
                        this.ext_text30_is_modified = true;
                    this.ext_text30_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text31") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text31' must be String: '"+value+"' is not." );
                    this.ext_text31 = (String) value;
                    if (this.ext_text31_is_set)
                        this.ext_text31_is_modified = true;
                    this.ext_text31_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text32") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text32' must be String: '"+value+"' is not." );
                    this.ext_text32 = (String) value;
                    if (this.ext_text32_is_set)
                        this.ext_text32_is_modified = true;
                    this.ext_text32_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text33") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text33' must be String: '"+value+"' is not." );
                    this.ext_text33 = (String) value;
                    if (this.ext_text33_is_set)
                        this.ext_text33_is_modified = true;
                    this.ext_text33_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text34") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text34' must be String: '"+value+"' is not." );
                    this.ext_text34 = (String) value;
                    if (this.ext_text34_is_set)
                        this.ext_text34_is_modified = true;
                    this.ext_text34_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text35") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text35' must be String: '"+value+"' is not." );
                    this.ext_text35 = (String) value;
                    if (this.ext_text35_is_set)
                        this.ext_text35_is_modified = true;
                    this.ext_text35_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text36") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text36' must be String: '"+value+"' is not." );
                    this.ext_text36 = (String) value;
                    if (this.ext_text36_is_set)
                        this.ext_text36_is_modified = true;
                    this.ext_text36_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text37") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text37' must be String: '"+value+"' is not." );
                    this.ext_text37 = (String) value;
                    if (this.ext_text37_is_set)
                        this.ext_text37_is_modified = true;
                    this.ext_text37_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text38") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text38' must be String: '"+value+"' is not." );
                    this.ext_text38 = (String) value;
                    if (this.ext_text38_is_set)
                        this.ext_text38_is_modified = true;
                    this.ext_text38_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text39") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text39' must be String: '"+value+"' is not." );
                    this.ext_text39 = (String) value;
                    if (this.ext_text39_is_set)
                        this.ext_text39_is_modified = true;
                    this.ext_text39_is_set = true;
                    return;
                }
                else if ( name.equals("ext_text40") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_text40' must be String: '"+value+"' is not." );
                    this.ext_text40 = (String) value;
                    if (this.ext_text40_is_set)
                        this.ext_text40_is_modified = true;
                    this.ext_text40_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value1' must be Long: '"+value+"' is not." );
                    this.ext_value1 = (Long) value;
                    if (this.ext_value1_is_set)
                        this.ext_value1_is_modified = true;
                    this.ext_value1_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value2' must be Long: '"+value+"' is not." );
                    this.ext_value2 = (Long) value;
                    if (this.ext_value2_is_set)
                        this.ext_value2_is_modified = true;
                    this.ext_value2_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value3' must be Long: '"+value+"' is not." );
                    this.ext_value3 = (Long) value;
                    if (this.ext_value3_is_set)
                        this.ext_value3_is_modified = true;
                    this.ext_value3_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value4' must be Long: '"+value+"' is not." );
                    this.ext_value4 = (Long) value;
                    if (this.ext_value4_is_set)
                        this.ext_value4_is_modified = true;
                    this.ext_value4_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value5") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value5' must be Long: '"+value+"' is not." );
                    this.ext_value5 = (Long) value;
                    if (this.ext_value5_is_set)
                        this.ext_value5_is_modified = true;
                    this.ext_value5_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value6") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value6' must be Long: '"+value+"' is not." );
                    this.ext_value6 = (Long) value;
                    if (this.ext_value6_is_set)
                        this.ext_value6_is_modified = true;
                    this.ext_value6_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value7") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value7' must be Long: '"+value+"' is not." );
                    this.ext_value7 = (Long) value;
                    if (this.ext_value7_is_set)
                        this.ext_value7_is_modified = true;
                    this.ext_value7_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value8") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value8' must be Long: '"+value+"' is not." );
                    this.ext_value8 = (Long) value;
                    if (this.ext_value8_is_set)
                        this.ext_value8_is_modified = true;
                    this.ext_value8_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value9") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value9' must be Long: '"+value+"' is not." );
                    this.ext_value9 = (Long) value;
                    if (this.ext_value9_is_set)
                        this.ext_value9_is_modified = true;
                    this.ext_value9_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value10") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value10' must be Long: '"+value+"' is not." );
                    this.ext_value10 = (Long) value;
                    if (this.ext_value10_is_set)
                        this.ext_value10_is_modified = true;
                    this.ext_value10_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value11") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value11' must be Long: '"+value+"' is not." );
                    this.ext_value11 = (Long) value;
                    if (this.ext_value11_is_set)
                        this.ext_value11_is_modified = true;
                    this.ext_value11_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value12") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value12' must be Long: '"+value+"' is not." );
                    this.ext_value12 = (Long) value;
                    if (this.ext_value12_is_set)
                        this.ext_value12_is_modified = true;
                    this.ext_value12_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value13") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value13' must be Long: '"+value+"' is not." );
                    this.ext_value13 = (Long) value;
                    if (this.ext_value13_is_set)
                        this.ext_value13_is_modified = true;
                    this.ext_value13_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value14") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value14' must be Long: '"+value+"' is not." );
                    this.ext_value14 = (Long) value;
                    if (this.ext_value14_is_set)
                        this.ext_value14_is_modified = true;
                    this.ext_value14_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value15") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value15' must be Long: '"+value+"' is not." );
                    this.ext_value15 = (Long) value;
                    if (this.ext_value15_is_set)
                        this.ext_value15_is_modified = true;
                    this.ext_value15_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value16") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value16' must be Long: '"+value+"' is not." );
                    this.ext_value16 = (Long) value;
                    if (this.ext_value16_is_set)
                        this.ext_value16_is_modified = true;
                    this.ext_value16_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value17") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value17' must be Long: '"+value+"' is not." );
                    this.ext_value17 = (Long) value;
                    if (this.ext_value17_is_set)
                        this.ext_value17_is_modified = true;
                    this.ext_value17_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value18") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value18' must be Long: '"+value+"' is not." );
                    this.ext_value18 = (Long) value;
                    if (this.ext_value18_is_set)
                        this.ext_value18_is_modified = true;
                    this.ext_value18_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value19") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value19' must be Long: '"+value+"' is not." );
                    this.ext_value19 = (Long) value;
                    if (this.ext_value19_is_set)
                        this.ext_value19_is_modified = true;
                    this.ext_value19_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value20") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value20' must be Long: '"+value+"' is not." );
                    this.ext_value20 = (Long) value;
                    if (this.ext_value20_is_set)
                        this.ext_value20_is_modified = true;
                    this.ext_value20_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value21") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value21' must be Long: '"+value+"' is not." );
                    this.ext_value21 = (Long) value;
                    if (this.ext_value21_is_set)
                        this.ext_value21_is_modified = true;
                    this.ext_value21_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value22") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value22' must be Long: '"+value+"' is not." );
                    this.ext_value22 = (Long) value;
                    if (this.ext_value22_is_set)
                        this.ext_value22_is_modified = true;
                    this.ext_value22_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value23") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value23' must be Long: '"+value+"' is not." );
                    this.ext_value23 = (Long) value;
                    if (this.ext_value23_is_set)
                        this.ext_value23_is_modified = true;
                    this.ext_value23_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value24") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value24' must be Long: '"+value+"' is not." );
                    this.ext_value24 = (Long) value;
                    if (this.ext_value24_is_set)
                        this.ext_value24_is_modified = true;
                    this.ext_value24_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value25") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value25' must be Long: '"+value+"' is not." );
                    this.ext_value25 = (Long) value;
                    if (this.ext_value25_is_set)
                        this.ext_value25_is_modified = true;
                    this.ext_value25_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value26") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value26' must be Long: '"+value+"' is not." );
                    this.ext_value26 = (Long) value;
                    if (this.ext_value26_is_set)
                        this.ext_value26_is_modified = true;
                    this.ext_value26_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value27") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value27' must be Long: '"+value+"' is not." );
                    this.ext_value27 = (Long) value;
                    if (this.ext_value27_is_set)
                        this.ext_value27_is_modified = true;
                    this.ext_value27_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value28") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value28' must be Long: '"+value+"' is not." );
                    this.ext_value28 = (Long) value;
                    if (this.ext_value28_is_set)
                        this.ext_value28_is_modified = true;
                    this.ext_value28_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value29") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value29' must be Long: '"+value+"' is not." );
                    this.ext_value29 = (Long) value;
                    if (this.ext_value29_is_set)
                        this.ext_value29_is_modified = true;
                    this.ext_value29_is_set = true;
                    return;
                }
                else if ( name.equals("ext_value30") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ext_value30' must be Long: '"+value+"' is not." );
                    this.ext_value30 = (Long) value;
                    if (this.ext_value30_is_set)
                        this.ext_value30_is_modified = true;
                    this.ext_value30_is_set = true;
                    return;
                }
                else if ( name.equals("ext_note1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_note1' must be String: '"+value+"' is not." );
                    this.ext_note1 = (String) value;
                    if (this.ext_note1_is_set)
                        this.ext_note1_is_modified = true;
                    this.ext_note1_is_set = true;
                    return;
                }
                else if ( name.equals("ext_note2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_note2' must be String: '"+value+"' is not." );
                    this.ext_note2 = (String) value;
                    if (this.ext_note2_is_set)
                        this.ext_note2_is_modified = true;
                    this.ext_note2_is_set = true;
                    return;
                }
                else if ( name.equals("error_reason_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_reason_code' must be String: '"+value+"' is not." );
                    this.error_reason_code = (String) value;
                    if (this.error_reason_code_is_set)
                        this.error_reason_code_is_modified = true;
                    this.error_reason_code_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fund_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_code' must be String: '"+value+"' is not." );
                    this.fund_code = (String) value;
                    if (this.fund_code_is_set)
                        this.fund_code_is_modified = true;
                    this.fund_code_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("inform_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'inform_div' must be String: '"+value+"' is not." );
                    this.inform_div = (String) value;
                    if (this.inform_div_is_set)
                        this.inform_div_is_modified = true;
                    this.inform_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_number' must be String: '"+value+"' is not." );
                    this.request_number = (String) value;
                    if (this.request_number_is_set)
                        this.request_number_is_modified = true;
                    this.request_number_is_set = true;
                    return;
                }
                else if ( name.equals("request_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'receipt_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.receipt_timestamp = (java.sql.Timestamp) value;
                    if (this.receipt_timestamp_is_set)
                        this.receipt_timestamp_is_modified = true;
                    this.receipt_timestamp_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code' must be String: '"+value+"' is not." );
                    this.sonar_trader_code = (String) value;
                    if (this.sonar_trader_code_is_set)
                        this.sonar_trader_code_is_modified = true;
                    this.sonar_trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                else if ( name.equals("send_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'send_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.send_timestamp = (java.sql.Timestamp) value;
                    if (this.send_timestamp_is_set)
                        this.send_timestamp_is_modified = true;
                    this.send_timestamp_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trader_code' must be String: '"+value+"' is not." );
                    this.trader_code = (String) value;
                    if (this.trader_code_is_set)
                        this.trader_code_is_modified = true;
                    this.trader_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
