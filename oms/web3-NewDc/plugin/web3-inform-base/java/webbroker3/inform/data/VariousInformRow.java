head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.58.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	VariousInformRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * VariousInformRowインタフェースは変更不可でリードオンリーである<em>various_inform</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link VariousInformRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see VariousInformPK 
 */
public interface VariousInformRow extends Row {


  /** 
   * この{@@link VariousInformRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "various_inform", "session" );


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsModified();


  /** 
   * <em>inform_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInformDiv();


  /** 
   * <em>inform_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInformDivIsSet();


  /** 
   * <em>inform_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInformDivIsModified();


  /** 
   * <em>request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRequestNumber();


  /** 
   * <em>request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestNumberIsSet();


  /** 
   * <em>request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestNumberIsModified();


  /** 
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchCode();


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeIsSet();


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeIsModified();


  /** 
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountCode();


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsSet();


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsModified();


  /** 
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTraderCode();


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderCodeIsSet();


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderCodeIsModified();


  /** 
   * <em>account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountName();


  /** 
   * <em>account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountNameIsSet();


  /** 
   * <em>account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountNameIsModified();


  /** 
   * <em>email_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEmailAddress();


  /** 
   * <em>email_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailAddressIsSet();


  /** 
   * <em>email_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailAddressIsModified();


  /** 
   * <em>ext_div1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv1();


  /** 
   * <em>ext_div1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv1IsSet();


  /** 
   * <em>ext_div1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv1IsModified();


  /** 
   * <em>ext_div2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv2();


  /** 
   * <em>ext_div2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv2IsSet();


  /** 
   * <em>ext_div2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv2IsModified();


  /** 
   * <em>ext_div3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv3();


  /** 
   * <em>ext_div3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv3IsSet();


  /** 
   * <em>ext_div3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv3IsModified();


  /** 
   * <em>ext_div4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv4();


  /** 
   * <em>ext_div4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv4IsSet();


  /** 
   * <em>ext_div4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv4IsModified();


  /** 
   * <em>ext_div5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv5();


  /** 
   * <em>ext_div5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv5IsSet();


  /** 
   * <em>ext_div5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv5IsModified();


  /** 
   * <em>ext_div6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv6();


  /** 
   * <em>ext_div6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv6IsSet();


  /** 
   * <em>ext_div6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv6IsModified();


  /** 
   * <em>ext_div7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv7();


  /** 
   * <em>ext_div7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv7IsSet();


  /** 
   * <em>ext_div7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv7IsModified();


  /** 
   * <em>ext_div8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv8();


  /** 
   * <em>ext_div8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv8IsSet();


  /** 
   * <em>ext_div8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv8IsModified();


  /** 
   * <em>ext_div9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv9();


  /** 
   * <em>ext_div9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv9IsSet();


  /** 
   * <em>ext_div9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv9IsModified();


  /** 
   * <em>ext_div10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv10();


  /** 
   * <em>ext_div10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv10IsSet();


  /** 
   * <em>ext_div10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv10IsModified();


  /** 
   * <em>ext_div11</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv11();


  /** 
   * <em>ext_div11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv11IsSet();


  /** 
   * <em>ext_div11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv11IsModified();


  /** 
   * <em>ext_div12</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv12();


  /** 
   * <em>ext_div12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv12IsSet();


  /** 
   * <em>ext_div12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv12IsModified();


  /** 
   * <em>ext_div13</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv13();


  /** 
   * <em>ext_div13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv13IsSet();


  /** 
   * <em>ext_div13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv13IsModified();


  /** 
   * <em>ext_div14</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv14();


  /** 
   * <em>ext_div14</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv14IsSet();


  /** 
   * <em>ext_div14</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv14IsModified();


  /** 
   * <em>ext_div15</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv15();


  /** 
   * <em>ext_div15</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv15IsSet();


  /** 
   * <em>ext_div15</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv15IsModified();


  /** 
   * <em>ext_div16</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv16();


  /** 
   * <em>ext_div16</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv16IsSet();


  /** 
   * <em>ext_div16</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv16IsModified();


  /** 
   * <em>ext_div17</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv17();


  /** 
   * <em>ext_div17</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv17IsSet();


  /** 
   * <em>ext_div17</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv17IsModified();


  /** 
   * <em>ext_div18</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv18();


  /** 
   * <em>ext_div18</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv18IsSet();


  /** 
   * <em>ext_div18</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv18IsModified();


  /** 
   * <em>ext_div19</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv19();


  /** 
   * <em>ext_div19</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv19IsSet();


  /** 
   * <em>ext_div19</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv19IsModified();


  /** 
   * <em>ext_div20</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv20();


  /** 
   * <em>ext_div20</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv20IsSet();


  /** 
   * <em>ext_div20</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv20IsModified();


  /** 
   * <em>ext_div21</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv21();


  /** 
   * <em>ext_div21</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv21IsSet();


  /** 
   * <em>ext_div21</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv21IsModified();


  /** 
   * <em>ext_div22</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv22();


  /** 
   * <em>ext_div22</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv22IsSet();


  /** 
   * <em>ext_div22</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv22IsModified();


  /** 
   * <em>ext_div23</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv23();


  /** 
   * <em>ext_div23</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv23IsSet();


  /** 
   * <em>ext_div23</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv23IsModified();


  /** 
   * <em>ext_div24</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv24();


  /** 
   * <em>ext_div24</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv24IsSet();


  /** 
   * <em>ext_div24</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv24IsModified();


  /** 
   * <em>ext_div25</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv25();


  /** 
   * <em>ext_div25</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv25IsSet();


  /** 
   * <em>ext_div25</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv25IsModified();


  /** 
   * <em>ext_div26</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv26();


  /** 
   * <em>ext_div26</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv26IsSet();


  /** 
   * <em>ext_div26</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv26IsModified();


  /** 
   * <em>ext_div27</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv27();


  /** 
   * <em>ext_div27</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv27IsSet();


  /** 
   * <em>ext_div27</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv27IsModified();


  /** 
   * <em>ext_div28</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv28();


  /** 
   * <em>ext_div28</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv28IsSet();


  /** 
   * <em>ext_div28</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv28IsModified();


  /** 
   * <em>ext_div29</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv29();


  /** 
   * <em>ext_div29</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv29IsSet();


  /** 
   * <em>ext_div29</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv29IsModified();


  /** 
   * <em>ext_div30</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv30();


  /** 
   * <em>ext_div30</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv30IsSet();


  /** 
   * <em>ext_div30</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv30IsModified();


  /** 
   * <em>ext_div31</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv31();


  /** 
   * <em>ext_div31</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv31IsSet();


  /** 
   * <em>ext_div31</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv31IsModified();


  /** 
   * <em>ext_div32</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv32();


  /** 
   * <em>ext_div32</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv32IsSet();


  /** 
   * <em>ext_div32</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv32IsModified();


  /** 
   * <em>ext_div33</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv33();


  /** 
   * <em>ext_div33</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv33IsSet();


  /** 
   * <em>ext_div33</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv33IsModified();


  /** 
   * <em>ext_div34</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv34();


  /** 
   * <em>ext_div34</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv34IsSet();


  /** 
   * <em>ext_div34</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv34IsModified();


  /** 
   * <em>ext_div35</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv35();


  /** 
   * <em>ext_div35</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv35IsSet();


  /** 
   * <em>ext_div35</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv35IsModified();


  /** 
   * <em>ext_div36</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv36();


  /** 
   * <em>ext_div36</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv36IsSet();


  /** 
   * <em>ext_div36</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv36IsModified();


  /** 
   * <em>ext_div37</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv37();


  /** 
   * <em>ext_div37</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv37IsSet();


  /** 
   * <em>ext_div37</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv37IsModified();


  /** 
   * <em>ext_div38</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv38();


  /** 
   * <em>ext_div38</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv38IsSet();


  /** 
   * <em>ext_div38</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv38IsModified();


  /** 
   * <em>ext_div39</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv39();


  /** 
   * <em>ext_div39</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv39IsSet();


  /** 
   * <em>ext_div39</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv39IsModified();


  /** 
   * <em>ext_div40</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtDiv40();


  /** 
   * <em>ext_div40</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv40IsSet();


  /** 
   * <em>ext_div40</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtDiv40IsModified();


  /** 
   * <em>ext_code1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtCode1();


  /** 
   * <em>ext_code1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode1IsSet();


  /** 
   * <em>ext_code1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode1IsModified();


  /** 
   * <em>ext_code2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtCode2();


  /** 
   * <em>ext_code2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode2IsSet();


  /** 
   * <em>ext_code2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode2IsModified();


  /** 
   * <em>ext_code3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtCode3();


  /** 
   * <em>ext_code3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode3IsSet();


  /** 
   * <em>ext_code3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode3IsModified();


  /** 
   * <em>ext_code4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtCode4();


  /** 
   * <em>ext_code4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode4IsSet();


  /** 
   * <em>ext_code4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode4IsModified();


  /** 
   * <em>ext_code5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtCode5();


  /** 
   * <em>ext_code5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode5IsSet();


  /** 
   * <em>ext_code5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode5IsModified();


  /** 
   * <em>ext_code6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtCode6();


  /** 
   * <em>ext_code6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode6IsSet();


  /** 
   * <em>ext_code6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode6IsModified();


  /** 
   * <em>ext_code7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtCode7();


  /** 
   * <em>ext_code7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode7IsSet();


  /** 
   * <em>ext_code7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode7IsModified();


  /** 
   * <em>ext_code8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtCode8();


  /** 
   * <em>ext_code8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode8IsSet();


  /** 
   * <em>ext_code8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode8IsModified();


  /** 
   * <em>ext_code9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtCode9();


  /** 
   * <em>ext_code9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode9IsSet();


  /** 
   * <em>ext_code9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode9IsModified();


  /** 
   * <em>ext_code10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtCode10();


  /** 
   * <em>ext_code10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode10IsSet();


  /** 
   * <em>ext_code10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtCode10IsModified();


  /** 
   * <em>ext_text1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText1();


  /** 
   * <em>ext_text1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText1IsSet();


  /** 
   * <em>ext_text1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText1IsModified();


  /** 
   * <em>ext_text2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText2();


  /** 
   * <em>ext_text2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText2IsSet();


  /** 
   * <em>ext_text2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText2IsModified();


  /** 
   * <em>ext_text3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText3();


  /** 
   * <em>ext_text3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText3IsSet();


  /** 
   * <em>ext_text3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText3IsModified();


  /** 
   * <em>ext_text4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText4();


  /** 
   * <em>ext_text4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText4IsSet();


  /** 
   * <em>ext_text4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText4IsModified();


  /** 
   * <em>ext_text5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText5();


  /** 
   * <em>ext_text5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText5IsSet();


  /** 
   * <em>ext_text5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText5IsModified();


  /** 
   * <em>ext_text6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText6();


  /** 
   * <em>ext_text6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText6IsSet();


  /** 
   * <em>ext_text6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText6IsModified();


  /** 
   * <em>ext_text7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText7();


  /** 
   * <em>ext_text7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText7IsSet();


  /** 
   * <em>ext_text7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText7IsModified();


  /** 
   * <em>ext_text8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText8();


  /** 
   * <em>ext_text8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText8IsSet();


  /** 
   * <em>ext_text8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText8IsModified();


  /** 
   * <em>ext_text9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText9();


  /** 
   * <em>ext_text9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText9IsSet();


  /** 
   * <em>ext_text9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText9IsModified();


  /** 
   * <em>ext_text10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText10();


  /** 
   * <em>ext_text10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText10IsSet();


  /** 
   * <em>ext_text10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText10IsModified();


  /** 
   * <em>ext_text11</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText11();


  /** 
   * <em>ext_text11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText11IsSet();


  /** 
   * <em>ext_text11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText11IsModified();


  /** 
   * <em>ext_text12</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText12();


  /** 
   * <em>ext_text12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText12IsSet();


  /** 
   * <em>ext_text12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText12IsModified();


  /** 
   * <em>ext_text13</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText13();


  /** 
   * <em>ext_text13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText13IsSet();


  /** 
   * <em>ext_text13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText13IsModified();


  /** 
   * <em>ext_text14</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText14();


  /** 
   * <em>ext_text14</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText14IsSet();


  /** 
   * <em>ext_text14</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText14IsModified();


  /** 
   * <em>ext_text15</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText15();


  /** 
   * <em>ext_text15</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText15IsSet();


  /** 
   * <em>ext_text15</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText15IsModified();


  /** 
   * <em>ext_text16</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText16();


  /** 
   * <em>ext_text16</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText16IsSet();


  /** 
   * <em>ext_text16</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText16IsModified();


  /** 
   * <em>ext_text17</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText17();


  /** 
   * <em>ext_text17</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText17IsSet();


  /** 
   * <em>ext_text17</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText17IsModified();


  /** 
   * <em>ext_text18</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText18();


  /** 
   * <em>ext_text18</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText18IsSet();


  /** 
   * <em>ext_text18</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText18IsModified();


  /** 
   * <em>ext_text19</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText19();


  /** 
   * <em>ext_text19</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText19IsSet();


  /** 
   * <em>ext_text19</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText19IsModified();


  /** 
   * <em>ext_text20</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText20();


  /** 
   * <em>ext_text20</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText20IsSet();


  /** 
   * <em>ext_text20</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText20IsModified();


  /** 
   * <em>ext_text21</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText21();


  /** 
   * <em>ext_text21</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText21IsSet();


  /** 
   * <em>ext_text21</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText21IsModified();


  /** 
   * <em>ext_text22</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText22();


  /** 
   * <em>ext_text22</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText22IsSet();


  /** 
   * <em>ext_text22</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText22IsModified();


  /** 
   * <em>ext_text23</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText23();


  /** 
   * <em>ext_text23</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText23IsSet();


  /** 
   * <em>ext_text23</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText23IsModified();


  /** 
   * <em>ext_text24</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText24();


  /** 
   * <em>ext_text24</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText24IsSet();


  /** 
   * <em>ext_text24</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText24IsModified();


  /** 
   * <em>ext_text25</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText25();


  /** 
   * <em>ext_text25</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText25IsSet();


  /** 
   * <em>ext_text25</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText25IsModified();


  /** 
   * <em>ext_text26</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText26();


  /** 
   * <em>ext_text26</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText26IsSet();


  /** 
   * <em>ext_text26</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText26IsModified();


  /** 
   * <em>ext_text27</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText27();


  /** 
   * <em>ext_text27</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText27IsSet();


  /** 
   * <em>ext_text27</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText27IsModified();


  /** 
   * <em>ext_text28</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText28();


  /** 
   * <em>ext_text28</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText28IsSet();


  /** 
   * <em>ext_text28</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText28IsModified();


  /** 
   * <em>ext_text29</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText29();


  /** 
   * <em>ext_text29</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText29IsSet();


  /** 
   * <em>ext_text29</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText29IsModified();


  /** 
   * <em>ext_text30</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText30();


  /** 
   * <em>ext_text30</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText30IsSet();


  /** 
   * <em>ext_text30</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText30IsModified();


  /** 
   * <em>ext_text31</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText31();


  /** 
   * <em>ext_text31</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText31IsSet();


  /** 
   * <em>ext_text31</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText31IsModified();


  /** 
   * <em>ext_text32</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText32();


  /** 
   * <em>ext_text32</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText32IsSet();


  /** 
   * <em>ext_text32</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText32IsModified();


  /** 
   * <em>ext_text33</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText33();


  /** 
   * <em>ext_text33</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText33IsSet();


  /** 
   * <em>ext_text33</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText33IsModified();


  /** 
   * <em>ext_text34</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText34();


  /** 
   * <em>ext_text34</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText34IsSet();


  /** 
   * <em>ext_text34</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText34IsModified();


  /** 
   * <em>ext_text35</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText35();


  /** 
   * <em>ext_text35</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText35IsSet();


  /** 
   * <em>ext_text35</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText35IsModified();


  /** 
   * <em>ext_text36</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText36();


  /** 
   * <em>ext_text36</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText36IsSet();


  /** 
   * <em>ext_text36</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText36IsModified();


  /** 
   * <em>ext_text37</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText37();


  /** 
   * <em>ext_text37</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText37IsSet();


  /** 
   * <em>ext_text37</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText37IsModified();


  /** 
   * <em>ext_text38</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText38();


  /** 
   * <em>ext_text38</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText38IsSet();


  /** 
   * <em>ext_text38</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText38IsModified();


  /** 
   * <em>ext_text39</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText39();


  /** 
   * <em>ext_text39</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText39IsSet();


  /** 
   * <em>ext_text39</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText39IsModified();


  /** 
   * <em>ext_text40</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtText40();


  /** 
   * <em>ext_text40</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText40IsSet();


  /** 
   * <em>ext_text40</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtText40IsModified();


  /** 
   * <em>ext_value1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue1();


  /** 
   * <em>ext_value1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue1IsNull();


  /** 
   * <em>ext_value1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue1IsSet();


  /** 
   * <em>ext_value1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue1IsModified();


  /** 
   * <em>ext_value2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue2();


  /** 
   * <em>ext_value2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue2IsNull();


  /** 
   * <em>ext_value2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue2IsSet();


  /** 
   * <em>ext_value2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue2IsModified();


  /** 
   * <em>ext_value3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue3();


  /** 
   * <em>ext_value3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue3IsNull();


  /** 
   * <em>ext_value3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue3IsSet();


  /** 
   * <em>ext_value3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue3IsModified();


  /** 
   * <em>ext_value4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue4();


  /** 
   * <em>ext_value4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue4IsNull();


  /** 
   * <em>ext_value4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue4IsSet();


  /** 
   * <em>ext_value4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue4IsModified();


  /** 
   * <em>ext_value5</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue5();


  /** 
   * <em>ext_value5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue5IsNull();


  /** 
   * <em>ext_value5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue5IsSet();


  /** 
   * <em>ext_value5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue5IsModified();


  /** 
   * <em>ext_value6</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue6();


  /** 
   * <em>ext_value6</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue6IsNull();


  /** 
   * <em>ext_value6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue6IsSet();


  /** 
   * <em>ext_value6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue6IsModified();


  /** 
   * <em>ext_value7</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue7();


  /** 
   * <em>ext_value7</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue7IsNull();


  /** 
   * <em>ext_value7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue7IsSet();


  /** 
   * <em>ext_value7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue7IsModified();


  /** 
   * <em>ext_value8</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue8();


  /** 
   * <em>ext_value8</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue8IsNull();


  /** 
   * <em>ext_value8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue8IsSet();


  /** 
   * <em>ext_value8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue8IsModified();


  /** 
   * <em>ext_value9</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue9();


  /** 
   * <em>ext_value9</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue9IsNull();


  /** 
   * <em>ext_value9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue9IsSet();


  /** 
   * <em>ext_value9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue9IsModified();


  /** 
   * <em>ext_value10</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue10();


  /** 
   * <em>ext_value10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue10IsNull();


  /** 
   * <em>ext_value10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue10IsSet();


  /** 
   * <em>ext_value10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue10IsModified();


  /** 
   * <em>ext_value11</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue11();


  /** 
   * <em>ext_value11</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue11IsNull();


  /** 
   * <em>ext_value11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue11IsSet();


  /** 
   * <em>ext_value11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue11IsModified();


  /** 
   * <em>ext_value12</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue12();


  /** 
   * <em>ext_value12</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue12IsNull();


  /** 
   * <em>ext_value12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue12IsSet();


  /** 
   * <em>ext_value12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue12IsModified();


  /** 
   * <em>ext_value13</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue13();


  /** 
   * <em>ext_value13</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue13IsNull();


  /** 
   * <em>ext_value13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue13IsSet();


  /** 
   * <em>ext_value13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue13IsModified();


  /** 
   * <em>ext_value14</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue14();


  /** 
   * <em>ext_value14</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue14IsNull();


  /** 
   * <em>ext_value14</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue14IsSet();


  /** 
   * <em>ext_value14</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue14IsModified();


  /** 
   * <em>ext_value15</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue15();


  /** 
   * <em>ext_value15</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue15IsNull();


  /** 
   * <em>ext_value15</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue15IsSet();


  /** 
   * <em>ext_value15</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue15IsModified();


  /** 
   * <em>ext_value16</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue16();


  /** 
   * <em>ext_value16</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue16IsNull();


  /** 
   * <em>ext_value16</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue16IsSet();


  /** 
   * <em>ext_value16</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue16IsModified();


  /** 
   * <em>ext_value17</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue17();


  /** 
   * <em>ext_value17</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue17IsNull();


  /** 
   * <em>ext_value17</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue17IsSet();


  /** 
   * <em>ext_value17</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue17IsModified();


  /** 
   * <em>ext_value18</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue18();


  /** 
   * <em>ext_value18</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue18IsNull();


  /** 
   * <em>ext_value18</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue18IsSet();


  /** 
   * <em>ext_value18</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue18IsModified();


  /** 
   * <em>ext_value19</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue19();


  /** 
   * <em>ext_value19</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue19IsNull();


  /** 
   * <em>ext_value19</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue19IsSet();


  /** 
   * <em>ext_value19</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue19IsModified();


  /** 
   * <em>ext_value20</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue20();


  /** 
   * <em>ext_value20</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue20IsNull();


  /** 
   * <em>ext_value20</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue20IsSet();


  /** 
   * <em>ext_value20</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue20IsModified();


  /** 
   * <em>ext_value21</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue21();


  /** 
   * <em>ext_value21</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue21IsNull();


  /** 
   * <em>ext_value21</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue21IsSet();


  /** 
   * <em>ext_value21</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue21IsModified();


  /** 
   * <em>ext_value22</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue22();


  /** 
   * <em>ext_value22</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue22IsNull();


  /** 
   * <em>ext_value22</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue22IsSet();


  /** 
   * <em>ext_value22</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue22IsModified();


  /** 
   * <em>ext_value23</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue23();


  /** 
   * <em>ext_value23</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue23IsNull();


  /** 
   * <em>ext_value23</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue23IsSet();


  /** 
   * <em>ext_value23</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue23IsModified();


  /** 
   * <em>ext_value24</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue24();


  /** 
   * <em>ext_value24</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue24IsNull();


  /** 
   * <em>ext_value24</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue24IsSet();


  /** 
   * <em>ext_value24</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue24IsModified();


  /** 
   * <em>ext_value25</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue25();


  /** 
   * <em>ext_value25</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue25IsNull();


  /** 
   * <em>ext_value25</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue25IsSet();


  /** 
   * <em>ext_value25</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue25IsModified();


  /** 
   * <em>ext_value26</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue26();


  /** 
   * <em>ext_value26</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue26IsNull();


  /** 
   * <em>ext_value26</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue26IsSet();


  /** 
   * <em>ext_value26</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue26IsModified();


  /** 
   * <em>ext_value27</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue27();


  /** 
   * <em>ext_value27</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue27IsNull();


  /** 
   * <em>ext_value27</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue27IsSet();


  /** 
   * <em>ext_value27</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue27IsModified();


  /** 
   * <em>ext_value28</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue28();


  /** 
   * <em>ext_value28</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue28IsNull();


  /** 
   * <em>ext_value28</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue28IsSet();


  /** 
   * <em>ext_value28</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue28IsModified();


  /** 
   * <em>ext_value29</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue29();


  /** 
   * <em>ext_value29</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue29IsNull();


  /** 
   * <em>ext_value29</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue29IsSet();


  /** 
   * <em>ext_value29</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue29IsModified();


  /** 
   * <em>ext_value30</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExtValue30();


  /** 
   * <em>ext_value30</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExtValue30IsNull();


  /** 
   * <em>ext_value30</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue30IsSet();


  /** 
   * <em>ext_value30</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtValue30IsModified();


  /** 
   * <em>ext_note1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtNote1();


  /** 
   * <em>ext_note1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtNote1IsSet();


  /** 
   * <em>ext_note1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtNote1IsModified();


  /** 
   * <em>ext_note2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtNote2();


  /** 
   * <em>ext_note2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtNote2IsSet();


  /** 
   * <em>ext_note2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtNote2IsModified();


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLastUpdater();


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsSet();


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsModified();


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsModified();


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsModified();


  /** 
   * <em>fund_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFundCode();


  /** 
   * <em>fund_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundCodeIsSet();


  /** 
   * <em>fund_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundCodeIsModified();


  /** 
   * <em>sonar_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarTraderCode();


  /** 
   * <em>sonar_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeIsSet();


  /** 
   * <em>sonar_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeIsModified();


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStatus();


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsModified();


  /** 
   * <em>error_reason_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getErrorReasonCode();


  /** 
   * <em>error_reason_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getErrorReasonCodeIsSet();


  /** 
   * <em>error_reason_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getErrorReasonCodeIsModified();


  /** 
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsSet();


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsModified();


  /** 
   * <em>request_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRequestCode();


  /** 
   * <em>request_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestCodeIsSet();


  /** 
   * <em>request_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestCodeIsModified();


  /** 
   * <em>send_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSendTimestamp();


  /** 
   * <em>send_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendTimestampIsSet();


  /** 
   * <em>send_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendTimestampIsModified();


  /** 
   * <em>receipt_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getReceiptTimestamp();


  /** 
   * <em>receipt_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptTimestampIsSet();


  /** 
   * <em>receipt_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptTimestampIsModified();


}
@
